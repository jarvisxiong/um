package com.hhz.ump.web.plan;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.plan.CostExecutionPlanMainManager;
import com.hhz.ump.dao.plan.CostProjectNodeRelManager;
import com.hhz.ump.dao.plan.PlanOperationLogManager;
import com.hhz.ump.entity.plan.CostExecutionPlanMain;
import com.hhz.ump.entity.plan.CostProjectNodeRel;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.OrgTreeUtil;

/**
 * 类名 CostExecPlanNodeAction 创建者 李劲 创建日期 2010-7-5 描述 执行计划节点配置Action
 */
@Namespace("/plan")
public class CostExecPlanNodeAction extends CrudActionSupport<CostProjectNodeRel> {

	private static final long serialVersionUID = -6809818475110518898L;
 
	@Autowired
	private CostProjectNodeRelManager costProjectNodeRelManager;
	@Autowired
	private CostExecutionPlanMainManager costExecutionPlanMainManager;

	//项目节点列表(按类型)
	private List<CostProjectNodeRel> projNodeList;

	private String projectCd;
	private String filterNodeName;
	private CostProjectNodeRel entity;
	private String resOrgCd;
	private String resOrgName;
	private String corOrgCd;
	private String corOrgName;
	private String projCd;
	private String opType;

	@Autowired
	private PlanOperationLogManager operationLogManager;

	//计划类型: 成本计划
	private String planTypeCd = DictContants.PLAN_TYPE_COST;

	/**
	 * 功能: 根据projectCd,planTypeCd,filterNodeName,搜索节点列表
	 * @param projectCd
	 * @param planTypeCd
	 * @param filterNodeName
	 */
	@Override
	public String list() throws Exception {
		projNodeList = costProjectNodeRelManager.getValidProjNodes(projectCd, planTypeCd, filterNodeName);
		Collections.sort(projNodeList, new Comparator<CostProjectNodeRel>() {
			public int compare(CostProjectNodeRel n1, CostProjectNodeRel n2) {
				String cd1 = n1.getNodeCd();
				String cd2 = n2.getNodeCd();
				return Integer.parseInt(cd1) - Integer.parseInt(cd2);
			}
		});
		return SUCCESS;
	}

	/**
	 * 功能: 保存节点信息
	 * @param id
	 * @param projCd
	 */
	@Override
	public String save() throws Exception {
		costProjectNodeRelManager.saveCostProjectNodeRel(entity);
		Struts2Utils.renderText("succ");

		if (StringUtils.isNotBlank(this.getId())) {
			saveLog();
		} else {
			operationLogManager.addPlanLog(entity.getPlanTypeCd(), entity.getCostProjectNodeRelId(), "节点:" + entity.getNodeName(),
					PlanOperationLogManager.OPERATION_TYPE_ADD, projCd, "新增执行计划节点:" + entity.getNodeName());
		}

		return null;
	}

	/**
	 * 保存节点的主责方
	 * @param id
	 * @param resOrgCd
	 * @param resOrgName
	 * @param projCd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveResOrgInfo() throws Exception {
		prepareEntity();

		entity.setResOrgCd(resOrgCd);
		entity.setResOrgName(resOrgName);
		costProjectNodeRelManager.saveCostProjectNodeRel(entity);
		Struts2Utils.renderText("done");

		String operationLog = "节点\"" + entity.getNodeName() + "\"的主责方修改为:" + entity.getResOrgName();
		
		operationLogManager.addPlanLog(entity.getPlanTypeCd(),entity.getCostProjectNodeRelId(), "节点:" + entity.getNodeName(),
				PlanOperationLogManager.OPERATION_TYPE_UPDATE, projCd, operationLog);

		return null;
	}

	/**
	 * 保存节点的配合方
	 * @param id
	 * @param resOrgCd
	 * @param resOrgName
	 * @param projCd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveCorOrgInfo() throws Exception {
		prepareEntity();

		entity.setCorOrgCd(corOrgCd);
		entity.setCorOrgName(corOrgName);
		costProjectNodeRelManager.saveCostProjectNodeRel(entity);
		Struts2Utils.renderText("done");

		String operationLog = "节点\"" + entity.getNodeName() + "\"的配合方修改为:" + entity.getCorOrgName();
		
		operationLogManager.addPlanLog(entity.getPlanTypeCd(),entity.getCostProjectNodeRelId(), "节点:" + entity.getNodeName(),
				PlanOperationLogManager.OPERATION_TYPE_UPDATE, projCd, operationLog);

		return null;
	}

	private void prepareEntity() {
		entity = costProjectNodeRelManager.getEntity(this.getId());
	}

	/**
	 * 保存更新操作日志
	 * @param id
	 * @param opType
	 * @param projCd
	 * @throws Exception 
	 */
	private void saveLog() throws Exception {
		if (StringUtils.isBlank(opType))
			return;

		String modifiedObj = "节点:" + entity.getNodeName();
		String operationLog = "";
		if ("nodeCd".equalsIgnoreCase(opType)) {
			operationLog = "节点序号修改为:" + entity.getNodeCd();
		} else if ("nodeName".equalsIgnoreCase(opType)) {
			operationLog = "节点名称修改为:" + entity.getNodeName();
		} else if ("outputFiles".equalsIgnoreCase(opType)) {
			operationLog = "节点输出成果修改为:" + entity.getOutputFiles();
		} else if ("controlNodeFlg".equalsIgnoreCase(opType)) {
			if ("1".equals(entity.getControlNodeFlg())) {
				operationLog = "节点被设置为控制节点";
			} else {
				operationLog = "节点被设置为执行计划节点";
			}
		} else if ("delete".equalsIgnoreCase(opType)) {
			operationLog = "节点\"" + entity.getNodeName() + "\"被删除";
		}
		operationLogManager.addPlanLog(entity.getPlanTypeCd(), entity.getCostProjectNodeRelId(), modifiedObj,
				PlanOperationLogManager.OPERATION_TYPE_UPDATE, projCd, operationLog);
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isBlank(this.getId())) {
			entity = new CostProjectNodeRel();
			CostExecutionPlanMain planMain = costExecutionPlanMainManager.getExecutionPlanMainByProjCd(projectCd, planTypeCd);
			entity.setCostExecutionPlanMain(planMain);
			entity.setDeletedFlg("0");
		} else {
			entity = costProjectNodeRelManager.getEntity(this.getId());
		}
	}

	/**
	 * 构造机构树
	 * 
	 * @return
	 * @throws Exception
	 */
	public String buildOrgs() throws Exception {
		Struts2Utils.renderText(OrgTreeUtil.buildNoCheckOrgTreeJSON(PlasCache.getOrgEnableList()));
		return null;
	}

	/**
	 * 构造有勾选框的机构树
	 * 
	 * @return
	 * @throws Exception
	 */
	public String buildCheckOrgs() throws Exception {
		Struts2Utils.renderText(OrgTreeUtil.buildOrgTreeJSON(PlasCache.getOrgEnableList(), null));
		return null;
	}

	public List<CostProjectNodeRel> getProjNodeList() {
		return projNodeList;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
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

	public CostProjectNodeRel getModel() {
		return entity;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public String getFilterNodeName() {
		return filterNodeName;
	}

	public void setFilterNodeName(String filterNodeName) {
		this.filterNodeName = filterNodeName;
	}

	public String getResOrgCd() {
		return resOrgCd;
	}

	public void setResOrgCd(String resOrgCd) {
		this.resOrgCd = resOrgCd;
	}

	public String getResOrgName() {
		return resOrgName;
	}

	public void setResOrgName(String resOrgName) {
		this.resOrgName = resOrgName;
	}

	public String getCorOrgCd() {
		return corOrgCd;
	}

	public void setCorOrgCd(String corOrgCd) {
		this.corOrgCd = corOrgCd;
	}

	public String getCorOrgName() {
		return corOrgName;
	}

	public void setCorOrgName(String corOrgName) {
		this.corOrgName = corOrgName;
	}

	public String getProjCd() {
		return projCd;
	}

	public void setProjCd(String projCd) {
		this.projCd = projCd;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public String getPlanTypeCd() {
		return planTypeCd;
	}

	public void setPlanTypeCd(String planTypeCd) {
		this.planTypeCd = planTypeCd;
	}
}
