package com.hhz.ump.web.planold;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.planold.PlanExecutionPlanNodeOldManager;
import com.hhz.ump.dao.planold.PlanOperationLogOldManager;
import com.hhz.ump.entity.planold.PlanExecutionPlanNodeOld;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.OrgTreeUtil;

/**
 * 类名 PlanNodeTemplateAction 创建者 李劲 创建日期 2010-7-5 描述 模板节点配置Action
 */
@Namespace("/planold")
@Results( {
	@Result(name = CrudActionSupport.RELOAD, location = "plan-node-template.action", type = "redirect")})
public class PlanNodeTemplateAction extends CrudActionSupport<PlanExecutionPlanNodeOld> {

	private static final long serialVersionUID = -5323918483595152310L;

	@Autowired
	private PlanExecutionPlanNodeOldManager planExecutionPlanNodeManager;

	@Autowired 
	private PlanOperationLogOldManager planOperationLogManager;
	

	private PlanExecutionPlanNodeOld entity;


	//模板节点列表(按类型)
	private List<PlanExecutionPlanNodeOld> templateNodeList;

	//计划类型:计划/前期计划
	private String planTypeCd = DictContants.PLAN_TYPE_EXC;
	private String filter_LIKES_nodeName;
	private String filter_LIKES_resOrgName;
	private String filter_LIKES_corOrgName;
	private String filter_EQS_controlNodeFlg;
	
	private String resOrgName;
	private String corOrgName;

	//更新节点字段信息
	private String opType;
	private String nodeCd;
	private String nodeName;
	private String outputFiles;
	private String controlNodeFlg;
	
	/**
	 * 功能: 搜索节点列表
	 * @param filter_LIKES_nodeName
	 * @param filter_LIKES_resOrgName
	 * @param filter_LIKES_corOrgName
	 * @param filter_EQS_controlNodeFlg
	 */
	@Override
	public String list() throws Exception {
	 
		templateNodeList = planExecutionPlanNodeManager.getPlanExecutionNodeList(planTypeCd, filter_LIKES_nodeName, filter_LIKES_resOrgName, filter_LIKES_corOrgName, filter_EQS_controlNodeFlg);
//		Collections.sort(templateNodeList, new Comparator<PlanExecutionPlanNodeOld>() {
//			@Override
//			public int compare(PlanExecutionPlanNodeOld n1, PlanExecutionPlanNodeOld n2) {
//				String cd1 = n1.getNodeCd();
//				String cd2 = n2.getNodeCd();
//				return Integer.parseInt(cd1) - Integer.parseInt(cd2);
//			}
//		});
		return SUCCESS;
	}

	/**
	 * 功能: 保存节点信息
	 * @param id
	 * @param planTypeCd
	 */
	@Override
	public String save() throws Exception {
		
		planExecutionPlanNodeManager.savePlanExecutionPlanNode(entity);
		Struts2Utils.renderText("succ");

		if (StringUtils.isNotBlank(this.getId())) {
			saveLog();
		} else {
			planOperationLogManager.addTemplateLog(entity.getPlanTypeCd(), entity.getPlanExecutionPlanNodeId(), "节点:" + entity.getNodeName(),
					PlanOperationLogOldManager.OPERATION_TYPE_ADD, entity.getNodeCd(), "新增模板节点:" + entity.getNodeName());
		}

		return null;
	}

	/**
	 * 保存节点的主责方
	 * @param id
	 * @param resOrgCd
	 * @param resOrgName
	 * @param planTypeCd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveResOrgInfo() throws Exception {
		prepareEntity();
		entity.setResOrgName(resOrgName);
		planExecutionPlanNodeManager.savePlanExecutionPlanNode(entity);
		Struts2Utils.renderText("done");

		String operationLog = "节点\"" + entity.getNodeName() + "\"的主责方修改为:" + entity.getResOrgName();
		
		planOperationLogManager.addTemplateLog(entity.getPlanTypeCd(), entity.getPlanExecutionPlanNodeId(), "节点:" + entity.getNodeName(),
				PlanOperationLogOldManager.OPERATION_TYPE_UPDATE, entity.getNodeCd(), operationLog);

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

		entity.setCorOrgName(corOrgName);
		planExecutionPlanNodeManager.savePlanExecutionPlanNode(entity);
		Struts2Utils.renderText("done");

		String operationLog = "节点\"" + entity.getNodeName() + "\"的配合方修改为:" + entity.getCorOrgName();
		
		planOperationLogManager.addTemplateLog(entity.getPlanTypeCd(), entity.getPlanExecutionPlanNodeId(), "节点:" + entity.getNodeName(),
				PlanOperationLogOldManager.OPERATION_TYPE_UPDATE, entity.getNodeCd(), operationLog);
		 
		return null;
	}

	private void prepareEntity() {
		entity = planExecutionPlanNodeManager.getEntity(this.getId());
	}

	/**
	 * 保存更新操作日志
	 * @param id
	 * @param opType
	 * @param planTypeCd
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
				operationLog = "节点被设置为模板节点";
			}
		} else if ("delete".equalsIgnoreCase(opType)) {
			operationLog = "节点\"" + entity.getNodeName() + "\"被删除";
		}

		planOperationLogManager.addTemplateLog(entity.getPlanTypeCd(), entity.getPlanExecutionPlanNodeId(), modifiedObj,
				PlanOperationLogOldManager.OPERATION_TYPE_UPDATE, entity.getNodeCd(), operationLog);
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isBlank(this.getId())) {
			entity = new PlanExecutionPlanNodeOld(); 
			entity.setPlanTypeCd(planTypeCd);
		} else {
			entity = planExecutionPlanNodeManager.getEntity(this.getId());
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
	public PlanExecutionPlanNodeOld getModel() {
		return entity;
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

	public List<PlanExecutionPlanNodeOld> getTemplateNodeList() {
		return templateNodeList;
	}

	public void setTemplateNodeList(List<PlanExecutionPlanNodeOld> templateNodeList) {
		this.templateNodeList = templateNodeList;
	}

	public String getFilter_LIKES_nodeName() {
		return filter_LIKES_nodeName;
	}

	public void setFilter_LIKES_nodeName(String filter_LIKES_nodeName) {
		this.filter_LIKES_nodeName = filter_LIKES_nodeName;
	}

	public String getFilter_LIKES_resOrgName() {
		return filter_LIKES_resOrgName;
	}

	public void setFilter_LIKES_resOrgName(String filter_LIKES_resOrgName) {
		this.filter_LIKES_resOrgName = filter_LIKES_resOrgName;
	}

	public String getFilter_LIKES_corOrgName() {
		return filter_LIKES_corOrgName;
	}

	public void setFilter_LIKES_corOrgName(String filter_LIKES_corOrgName) {
		this.filter_LIKES_corOrgName = filter_LIKES_corOrgName;
	}

	public String getFilter_EQS_controlNodeFlg() {
		return filter_EQS_controlNodeFlg;
	}

	public void setFilter_EQS_controlNodeFlg(String filter_EQS_controlNodeFlg) {
		this.filter_EQS_controlNodeFlg = filter_EQS_controlNodeFlg;
	}

	public String getResOrgName() {
		return resOrgName;
	}

	public void setResOrgName(String resOrgName) {
		this.resOrgName = resOrgName;
	}

	public String getCorOrgName() {
		return corOrgName;
	}

	public void setCorOrgName(String corOrgName) {
		this.corOrgName = corOrgName;
	}

	public String getNodeCd() {
		return nodeCd;
	}

	public void setNodeCd(String nodeCd) {
		this.nodeCd = nodeCd;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getOutputFiles() {
		return outputFiles;
	}

	public void setOutputFiles(String outputFiles) {
		this.outputFiles = outputFiles;
	}

	public String getControlNodeFlg() {
		return controlNodeFlg;
	}

	public void setControlNodeFlg(String controlNodeFlg) {
		this.controlNodeFlg = controlNodeFlg;
	}
 
}
