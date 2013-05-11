package com.hhz.ump.web.plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.plan.CostExecutionPlanNodeManager;
import com.hhz.ump.dao.plan.PlanExecutionPlanMainManager;
import com.hhz.ump.dao.plan.PlanOperationLogManager;
import com.hhz.ump.dao.plan.PlanProjectNodeRelManager;
import com.hhz.ump.entity.plan.CostExecutionPlanNode;
import com.hhz.ump.entity.plan.PlanExecutionPlanMain;
import com.hhz.ump.entity.plan.PlanProjectNodeRel;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.OrgTreeUtil;

/**
 * 类名 PlanExecPlanNodeAction 创建者 李劲 创建日期 2010-7-5 描述 执行计划节点配置Action
 */
@Namespace("/plan")
public class PlanExecPlanNodeAction extends CrudActionSupport<PlanProjectNodeRel> {

	private static final long serialVersionUID = -6809818475110518898L;
 
	@Autowired
	private PlanExecutionPlanMainManager planExecutionPlanMainManager;
	@Autowired
	private PlanProjectNodeRelManager planProjectNodeRelManager;
	@Autowired
	private PlanOperationLogManager planOperationLogManager;

	@Autowired
	private CostExecutionPlanNodeManager costExecutionPlanNodeManager;
	

	//项目节点列表(按类型)
	private List<PlanProjectNodeRel> projNodeList;

	private String projectCd;
	private String filterNodeName;
	private PlanProjectNodeRel entity;
	private String resOrgCd;
	private String resOrgName;
	private String corOrgCd;
	private String corOrgName;
	private String projCd;
	private String opType;

	//计划类型:执行计划/前期计划
	private String planTypeCd = DictContants.PLAN_TYPE_EXC;

	//关联执行计划节点 
	private Map<String,String> mapExecCostPlan;
	
	private String treeTypeCd;
	
	
	/**
	 * 功能: 根据projectCd,planTypeCd,filterNodeName,搜索节点列表
	 * @param projectCd
	 * @param planTypeCd
	 * @param filterNodeName
	 * @param treeTypeCd
	 */
	@Override
	public String list() throws Exception {
		projNodeList = planProjectNodeRelManager.getValidProjNodes(projectCd, planTypeCd, filterNodeName, null, null, "",true, treeTypeCd, null);
//		for(PlanProjectNodeRel planProjectNodeRel:projNodeList){
//			try{
//				if(null!=planProjectNodeRel.getTreeParentNodeId() && !"".equalsIgnoreCase(planProjectNodeRel.getTreeParentNodeId())){
//					PlanProjectNodeRel planProjectNodeRel2 = planProjectNodeRelManager.getEntity(planProjectNodeRel.getTreeParentNodeId());
//					//临时显示名称
//					planProjectNodeRel.setRemark(planProjectNodeRel2.getNodeName());
//				}else{
//					planProjectNodeRel.setRemark("");
//				}
//			}catch(Exception e){}
//		}
		/*
		Collections.sort(projNodeList, new Comparator<PlanProjectNodeRel>() {
			public int compare(PlanProjectNodeRel n1, PlanProjectNodeRel n2) {
				String cd1 = n1.getNodeCd();
				String cd2 = n2.getNodeCd();
				return Integer.parseInt(cd1) - Integer.parseInt(cd2);
			}
		});
		*/
		
		//映射
		initMapPlanNodes();
		
		return SUCCESS;
	}
	
	/**
	 * 功能: 根据projectCd,planTypeCd,filterNodeName,搜索节点列表
	 * @param projectCd
	 * @param planTypeCd
	 * @param filterNodeName
	 */
	public String selectList() throws Exception {
		String treeType = Struts2Utils.getParameter("treeType");
		projNodeList = planProjectNodeRelManager.getValidProjNodes(projectCd, planTypeCd, filterNodeName,null,null,treeType,true,treeTypeCd, null);
		for(PlanProjectNodeRel planProjectNodeRel:projNodeList){
			try{
				if(null!=planProjectNodeRel.getTreeParentNodeId() && !"".equalsIgnoreCase(planProjectNodeRel.getTreeParentNodeId())){
					PlanProjectNodeRel planProjectNodeRel2 = planProjectNodeRelManager.getEntity(planProjectNodeRel.getTreeParentNodeId());
					planProjectNodeRel.setRemark(planProjectNodeRel2.getNodeName());
				}else{
					planProjectNodeRel.setRemark("");
				}
			}catch(Exception e){}
		}
		/*
		Collections.sort(projNodeList, new Comparator<PlanProjectNodeRel>() {
			public int compare(PlanProjectNodeRel n1, PlanProjectNodeRel n2) {
				String cd1 = n1.getNodeCd();
				String cd2 = n2.getNodeCd();
				return Integer.parseInt(cd1) - Integer.parseInt(cd2);
			}
		});
		*/
		
		//映射
		initMapPlanNodes();
		
		return "select";
	}

	/**
	 * 功能: 保存节点信息
	 * @param id
	 * @param projectCd
	 * @param order1
	 * @param order2
	 * @param order3
	 * @param nodeName
	 * @param planTypeCd
	 * @param resOrgName
	 * @param corOrgName
	 * @param outputFiles
	 * @param controlNodeFlg
	 * @param treeParentNodeId
	 * @param ifChangeTreeParent
	 */
	@Override
	public String save() throws Exception {
		String ifChangeTreeParent = Struts2Utils.getParameter("ifChangeTreeParent");
		String ifChangeRelDisplay = Struts2Utils.getParameter("ifChangeRelDisplay");
		if("true".equalsIgnoreCase(ifChangeTreeParent)){
			//如果选择了上级节点，根据上级节点自动生成序号
			String tmpId = entity.getTreeParentNodeId();
			if(StringUtils.isNotBlank(tmpId)){
				PlanProjectNodeRel pRel = planProjectNodeRelManager.getEntity(tmpId);
				//add by huangbj 2011-03-22
				entity.setTreeParentNodeName("[" + pRel.getTreeType()+"]" + pRel.getNodeName());
				
//				if("1".equalsIgnoreCase(pRel.getTreeType())){
//					long order2 = planProjectNodeRelManager.getMaxOrder("order2", entity.getOrder1(), entity.getOrder2());
//					entity.setOrder1(pRel.getOrder1());
//					entity.setOrder2(order2);
//					entity.setOrder3(0);
//				}else if("2".equalsIgnoreCase(pRel.getTreeType())){
//					long order3 = planProjectNodeRelManager.getMaxOrder("order3", entity.getOrder1(), entity.getOrder2());
//					entity.setOrder1(pRel.getOrder1());
//					entity.setOrder2(pRel.getOrder2());
//					entity.setOrder3(order3);
//				}
			}else{
				entity.setTreeParentNodeId(null);
				entity.setTreeParentNodeName(null);
			}
		}
		
		if("true".equalsIgnoreCase(ifChangeRelDisplay)){
			String tmpId = entity.getRelDisplayNodeId();
			if(StringUtils.isNotBlank(tmpId)){
				//add by huangbj 2011-03-22
				PlanProjectNodeRel tt = planProjectNodeRelManager.getEntity(tmpId);
				entity.setRelDisplayNodeName("[" + tt.getTreeType()+"]" + tt.getNodeName());
			}else{
				entity.setRelDisplayNodeId(null);
				entity.setRelDisplayNodeName(null);
			}
		}
		
		
		if(StringUtils.isBlank(entity.getNodeCd())){
			entity.setNodeCd(RandomUtils.generateTmpEntityId());
		}
		
		planProjectNodeRelManager.savePlanProjectNodeRel(entity);
		Struts2Utils.renderText("succ");

		if (StringUtils.isNotBlank(this.getId())) {
			saveLog();
		} else {
			planOperationLogManager.addPlanLog(entity.getPlanTypeCd(), entity.getPlanProjectNodeRelId(), "节点:" + entity.getNodeName(),
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
		planProjectNodeRelManager.savePlanProjectNodeRel(entity);
		Struts2Utils.renderText("done");

		String operationLog = "节点\"" + entity.getNodeName() + "\"的主责方修改为:" + entity.getResOrgName();
		
		planOperationLogManager.addPlanLog(entity.getPlanTypeCd(), entity.getPlanProjectNodeRelId(), "节点:" + entity.getNodeName(),
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
		planProjectNodeRelManager.savePlanProjectNodeRel(entity);
		Struts2Utils.renderText("done");

		String operationLog = "节点\"" + entity.getNodeName() + "\"的配合方修改为:" + entity.getCorOrgName();
		
		planOperationLogManager.addPlanLog(entity.getPlanTypeCd(), entity.getPlanProjectNodeRelId(), "节点:" + entity.getNodeName(),
				PlanOperationLogManager.OPERATION_TYPE_UPDATE, projCd, operationLog);
		 
		return null;
	}

	private void prepareEntity() {
		entity = planProjectNodeRelManager.getEntity(this.getId());
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

		planOperationLogManager.addPlanLog(entity.getPlanTypeCd(), entity.getPlanProjectNodeRelId(), modifiedObj,
				PlanOperationLogManager.OPERATION_TYPE_UPDATE, projCd, operationLog);
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isBlank(this.getId())) {
			entity = new PlanProjectNodeRel();
			PlanExecutionPlanMain planMain = planExecutionPlanMainManager.getExecutionPlanMainByProjCd(projectCd, planTypeCd);
			entity.setPlanExecutionPlanMain(planMain);
			entity.setDeletedFlg("0");
		} else {
			entity = planProjectNodeRelManager.getEntity(this.getId());
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

	public List<PlanProjectNodeRel> getProjNodeList() {
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

	public PlanProjectNodeRel getModel() {
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
	 
	public Map<String,String> getMapExecCostPlan(){
		return mapExecCostPlan;
	}
	
	/**
	 * 
	 */
	private void initMapPlanNodes(){
		
		mapExecCostPlan = new HashMap<String,String>();
		CostExecutionPlanNode node = null;
		List<CostExecutionPlanNode> costNodes= costExecutionPlanNodeManager.getRelCostNodeList(projectCd);
		/*
		Collections.sort(costNodes, new Comparator<CostExecutionPlanNode>() {
			public int compare(CostExecutionPlanNode n1, CostExecutionPlanNode n2) {
				String cd1 = n1.getNodeCd();
				String cd2 = n2.getNodeCd();
				return Integer.parseInt(cd1) - Integer.parseInt(cd2);
			}
		});
		*/
		if(costNodes != null){
			for (int i = 0; i < costNodes.size(); i++) {
				node = costNodes.get(i);
				mapExecCostPlan.put(node.getNodeCd(), node.getNodeName());
			}
		}
	}

	public String getTreeTypeCd() {
		return treeTypeCd;
	}

	public void setTreeTypeCd(String treeTypeCd) {
		this.treeTypeCd = treeTypeCd;
	}
	
	
	/**
	 * 搜索节点列表
	 * @param relNodeId
	 * @param filterNodeName
	 * @param projectCd
	 * @param planTypeCd
	 */
	public String searchNodeList(){
		
		String tempId = Struts2Utils.getParameter("relNodeId"); 
		String tmpName = Struts2Utils.getParameter("filterNodeName"); 
		String tmpPrjCd = Struts2Utils.getParameter("projectCd"); 
		String tmpPlanTypeCd = Struts2Utils.getParameter("planTypeCd"); 
		
		List<PlanProjectNodeRel> list = planProjectNodeRelManager.getValidProjNodesPage(tmpPrjCd, tmpPlanTypeCd, tmpName, null, null, "",true, null);
		List<Object> rtn = new ArrayList<Object>();
		Map<String, String> map = null;
		PlanProjectNodeRel t  = null;
		

		map = new HashMap<String, String>();
		map.put("nodeRelId", "");
		map.put("nodeCd", "");
		map.put("nodeName", "清空");
		map.put("treeType", "");
		rtn.add(map);
		
		for (int i = 0; i < list.size(); i++) {
			t = list.get(i);
			if(tempId.equals(t.getPlanProjectNodeRelId())){
				continue;
			}
			map = new HashMap<String, String>();
			map.put("nodeRelId", t.getPlanProjectNodeRelId());
			map.put("nodeName", t.getNodeName());
			map.put("nodeCd", t.getNodeCd());
			map.put("treeType", t.getTreeType());
			rtn.add(map);
		}
		map = new HashMap<String, String>();
		map.put("nodeRelId", "");
		map.put("nodeCd", "");
		map.put("nodeName", "关闭");
		map.put("treeType", "");
		rtn.add(map);
		
		Struts2Utils.renderJson(rtn);
		return null;
	}
}
