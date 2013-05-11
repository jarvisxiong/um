/**
 * 
 */
package com.hhz.ump.web.planold;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.plan.CostExecutionPlanMainManager;
import com.hhz.ump.dao.planold.PlanExecutionPlanMainOldManager;
import com.hhz.ump.dao.planold.PlanOperationLogOldManager;
import com.hhz.ump.entity.planold.PlanOperationLogOld;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.uums.entity.ws.WsPlasOrg;

/**
 * 类名 PlanOperationLogAction 创建者 李进 创建日期 2010-6-9 描述 计划操作记录Action
 */
@Namespace("/planold")
public class PlanOperationLogAction extends CrudActionSupport<PlanOperationLogOld> {
  
	private static final long serialVersionUID = -8424035346204554213L;
	@Autowired
	private PlanOperationLogOldManager planOperationLogManager;
	@Autowired
	private PlanExecutionPlanMainOldManager planMainManager;
	@Autowired
	private CostExecutionPlanMainManager costMainManager;

	private Page<PlanOperationLogOld> page = new Page<PlanOperationLogOld>(10);
	private List<WsPlasOrg> projects = new ArrayList<WsPlasOrg>();

	private String projectCd;
	private String operationType;
	private String operationStartDate;
	private String operationEndDate;

	private Map<String, String> operationTypeMap;
	private Map<String, String> operatorMap;
	private Map<String, List<WsPlasOrg>> orgMap;

	// 搜索条件
	private String filter_EQS_bizEntityId;
	private String filter_EQS_modifiedObject;
	private String filter_GED_createdDate;
	private String filter_LTD_createdDate;
	private String filter_LIKES_operationLog;
	private String filter_EQS_projectCd;
	private String filter_EQS_deptCd;

	@Override
	public String list() throws Exception {

		projects = planMainManager.fetchAllProjects();

		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		if (StringUtils.isNotBlank(projectCd)) {
			filters.add(new PropertyFilter("EQS_projectCd", projectCd));
		}
		if (StringUtils.isNotBlank(operationType)) {
			filters.add(new PropertyFilter("EQS_operationType", operationType));
		}
		if (StringUtils.isNotBlank(operationStartDate)) {
			Date stDate = DateOperator.parse(operationStartDate, "yyyy-MM-dd");
			filters.add(new PropertyFilter("GED_createdDate", stDate));
		}
		if (StringUtils.isNotBlank(operationEndDate)) {
			Date endDate = DateOperator.parse(operationEndDate, "yyyy-MM-dd");
			filters.add(new PropertyFilter("LED_createdDate", endDate));
		}
		page.setOrderBy("createdDate");
		page.setOrder(Page.DESC);
		page = planOperationLogManager.findPage(page, filters);

		operationTypeMap = buildOperationMap();
		operatorMap = buildOperatorMap(page.getResult());

		return SUCCESS;
	}

	public String log() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		String moduleCd = Struts2Utils.getParameter("moduleCd");
		if (StringUtils.isEmpty(moduleCd)) {
			moduleCd = "workPlan";
		}

		PropertyFilter filter = new PropertyFilter("EQS_moduleCd", moduleCd);
		filters.add(filter);

		page.setOrderBy("createdDate");
		page.setOrder(Page.DESC);
		page = planOperationLogManager.findPage(page, filters);

		operationTypeMap = planOperationLogManager.getOperTypeMap();
		if (PlanOperationLogOldManager.MODULE_EXEC_PLAN.equals(moduleCd)
				|| PlanOperationLogOldManager.MODULE_PRE_PLAN.equals(moduleCd)) {
			projects = planMainManager.fetchAllProjects();
		} else if (PlanOperationLogOldManager.MODULE_COST_PLAN.equals(moduleCd)) {
			projects = costMainManager.fetchAllProjects();
		} else if ("workPlan".equals(moduleCd)) {
			orgMap = this.buildOrgMap();
		}

		return moduleCd;
	}

	private Map<String, String> buildOperatorMap(List<PlanOperationLogOld> result) {
		Map<String, String> m = new HashMap<String, String>();

		for (PlanOperationLogOld log : result) {
			m.put(log.getCreator(), CodeNameUtil.getUserNameByCd(log.getCreator()));
		}

		return m;
	}

	/**
	 * 获取大小中心分组
	 * 
	 * @return
	 */
	private Map<String, List<WsPlasOrg>> buildOrgMap() {
		Map<String, List<WsPlasOrg>> map = new LinkedHashMap<String, List<WsPlasOrg>>();
		List<WsPlasOrg> uaapOrgs = PlasCache.getOrgListByTypeCd(Constants.ORG_TYPE_CD_FG);
		CollectionHelper.sortList(uaapOrgs, "orgBizCd");// 排序

		List<WsPlasOrg> list = null;
		for (WsPlasOrg wsUaapOrg : uaapOrgs) {
			list = PlasCache.getLogicalDirectOrgListByOrgCd(wsUaapOrg.getOrgCd(),Constants.ORG_TYPE_CD_ZX);
			if (null != list && list.size() > 0) {
				map.put(wsUaapOrg.getOrgName(), list);
			} else {
				List<WsPlasOrg> orgList = new ArrayList<WsPlasOrg>();
				orgList.add(wsUaapOrg);
				map.put(wsUaapOrg.getOrgName(), orgList);
			}
		}

		return map;
	}

	/**
	 * 构造操作类型的中文名称
	 * 
	 * @return
	 */
	private Map<String, String> buildOperationMap() {
		Map<String, String> m = new HashMap<String, String>();
		m.put(PlanOperationLogOldManager.OPERATION_TYPE_ADD, "新增");
		m.put(PlanOperationLogOldManager.OPERATION_TYPE_UPDATE, "更新");
		m.put(PlanOperationLogOldManager.OPERATION_TYPE_UPLOADATTACH, "上传附件");
		m.put(PlanOperationLogOldManager.OPERATION_TYPE_DELETEATTACH, "删除附件");

		return m;
	}

	public Map<String, String> getOperationTypeMap() {
		return operationTypeMap;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanOperationLogOld getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PlanOperationLogOld> getPage() {
		return page;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public List<WsPlasOrg> getProjects() {
		return projects;
	}

	public Map<String, String> getOperatorMap() {
		return operatorMap;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationStartDate() {
		return operationStartDate;
	}

	public void setOperationStartDate(String operationStartDate) {
		this.operationStartDate = operationStartDate;
	}

	public String getOperationEndDate() {
		return operationEndDate;
	}

	public void setOperationEndDate(String operationEndDate) {
		this.operationEndDate = operationEndDate;
	}

	public String getFilter_EQS_bizEntityId() {
		return filter_EQS_bizEntityId;
	}

	public String getFilter_EQS_modifiedObject() {
		return filter_EQS_modifiedObject;
	}

	public String getFilter_GED_createdDate() {
		return filter_GED_createdDate;
	}

	public String getFilter_LTD_createdDate() {
		return filter_LTD_createdDate;
	}

	public String getFilter_LIKES_operationLog() {
		return filter_LIKES_operationLog;
	}

	public String getFilter_EQS_projectCd() {
		return filter_EQS_projectCd;
	}

	public String getFilter_EQS_deptCd() {
		return filter_EQS_deptCd;
	}

	public void setFilter_EQS_bizEntityId(String filterEQSBizEntityId) {
		filter_EQS_bizEntityId = filterEQSBizEntityId;
	}

	public void setFilter_EQS_modifiedObject(String filterEQSModifiedObject) {
		filter_EQS_modifiedObject = filterEQSModifiedObject;
	}

	public void setFilter_GED_createdDate(String filterGEDCreatedDate) {
		filter_GED_createdDate = filterGEDCreatedDate;
	}

	public void setFilter_LTD_createdDate(String filterLTDCreatedDate) {
		filter_LTD_createdDate = filterLTDCreatedDate;
	}

	public void setFilter_LIKES_operationLog(String filterLIKESOperationLog) {
		filter_LIKES_operationLog = filterLIKESOperationLog;
	}

	public void setFilter_EQS_projectCd(String filterEQSProjectCd) {
		filter_EQS_projectCd = filterEQSProjectCd;
	}

	public void setFilter_EQS_deptCd(String filterEQSDeptCd) {
		filter_EQS_deptCd = filterEQSDeptCd;
	}

	public Map<String, List<WsPlasOrg>> getOrgMap() {
		return orgMap;
	}

	public void setOrgMap(Map<String, List<WsPlasOrg>> orgMap) {
		this.orgMap = orgMap;
	}
}
