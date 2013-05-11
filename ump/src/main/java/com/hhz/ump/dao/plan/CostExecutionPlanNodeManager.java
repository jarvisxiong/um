package com.hhz.ump.dao.plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.CostExecutionPlanNode;
import com.hhz.ump.entity.plan.PlanExecPlanDetail;
import com.hhz.ump.util.DictContants;

@Service
@Transactional
public class CostExecutionPlanNodeManager extends BaseService<CostExecutionPlanNode, String> {
	@Autowired
	private CostExecutionPlanNodeDao costExecutionPlanNodeDao;

	@Transactional(readOnly = true)
	public CostExecutionPlanNode getCostExecutionPlanNode(String id) {
		return costExecutionPlanNodeDao.get(id);
	}

	public List<CostExecutionPlanNode> getAllCostExecutionPlanNode() {
		return costExecutionPlanNodeDao.getAll();
	}

	public void saveCostExecutionPlanNode(CostExecutionPlanNode costExecutionPlanNode) {
		PowerUtils.setEmptyStr2Null(costExecutionPlanNode);
		costExecutionPlanNodeDao.save(costExecutionPlanNode);
	}

	public void deleteCostExecutionPlanNode(String id) {
		costExecutionPlanNodeDao.delete(id);
	}

	@Override
	public HibernateDao<CostExecutionPlanNode, String> getDao() {
		return costExecutionPlanNodeDao;
	}
	/**
	 * 功能: 搜索模板节点列表
	 * @param planTypeCd
	 * @param nodeName
	 * @param resOrgName
	 * @param corOrgName
	 * @param controlNodeFlg
	 * @return
	 */
	public List<CostExecutionPlanNode> getCostExecutionNodeList(String planTypeCd){
		return getCostExecutionNodeList(planTypeCd, null, null, null, null);
	}
	public List<CostExecutionPlanNode> getCostExecutionNodeList(String planTypeCd,String nodeName, String resOrgName, String corOrgName, String controlNodeFlg){
		
		if (StringUtils.isBlank(planTypeCd))
			return new ArrayList<CostExecutionPlanNode>();
		
		StringBuffer hql = new StringBuffer(" from CostExecutionPlanNode n where 1=1 ");

		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(planTypeCd)){
			hql.append(" and n.planTypeCd =:planTypeCd " );
			params.put("planTypeCd", planTypeCd);
		}
		if(StringUtils.isNotBlank(nodeName)){
			hql.append(" and n.nodeName like :nodeName " );
			params.put("nodeName", "%"+nodeName+"%");
		}
		if(StringUtils.isNotBlank(resOrgName)){
			hql.append(" and n.resOrgName like :resOrgName " );
			params.put("resOrgName", "%"+resOrgName+"%");
		}
		if(StringUtils.isNotBlank(corOrgName)){
			hql.append(" and n.corOrgName like :corOrgName " );
			params.put("corOrgName", "%"+corOrgName+"%");
		} 
		if(StringUtils.isNotBlank(controlNodeFlg)){
			hql.append(" and n.controlNodeFlg =:controlNodeFlg " );
			params.put("controlNodeFlg", controlNodeFlg);
		}
		
  
		return getDao().find(hql.toString(), params);
	}
	
	/**
	 * 获取节点CD和节点对象的映射
	 * @return
	 */
	public Map<String, CostExecutionPlanNode> getExecPlanNodesMap() {
	    Map<String, CostExecutionPlanNode> result = CostPlanNodesCache.getNodesCache();
	    
	    if (result == null) {
		List<CostExecutionPlanNode> allNodes = this.getAllCostExecutionPlanNode();
		CostPlanNodesCache.initCache(allNodes);
		result = CostPlanNodesCache.getNodesCache();
	    }
	    
	    return result;
	}
	
	/**
	 * 根据节点CD获取节点名称
	 * @param nodeCd
	 * @return
	 */
	public String getNodeNameByCd(String nodeCd) {
	    String nodeName = "";
	    
	    Map<String, CostExecutionPlanNode> m = this.getExecPlanNodesMap();
	    CostExecutionPlanNode n = m.get(nodeCd);
	    
	    if (n != null) {
		nodeName = n.getNodeName();
	    }
	    
	    return nodeName;
	}

	/**
	 * 搜索某项目的成本节点列表
	 * @param projectCd
	 * 
	 * @return
	 */
	public List<CostExecutionPlanNode> getRelCostNodeList(String projectCd) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectCd", projectCd);
		params.put("planTypeCd_Exec", DictContants.PLAN_TYPE_EXC);
		params.put("planTypeCd_Cost", DictContants.PLAN_TYPE_COST);
		
		StringBuffer hql = new StringBuffer()
		.append(" select distinct t4 ")
		.append(" from PlanProjectNodeRel t, PlanExecutionPlanNode t2, CostProjectNodeRel t3, CostExecutionPlanNode t4 ")
		.append(" where  t.planExecutionPlanMain.projectCd  = :projectCd ")
		.append("   and t3.costExecutionPlanMain.projectCd = :projectCd ")
		.append("   and  t.nodeCd  = t2.nodeCd  ")
		.append("   and t3.nodeCd = t4.nodeCd ")
		.append("   and t2.planExecutionPlanNodeId = t4.planExecutionPlanNodeId ")
		.append("   and  t.planTypeCd = :planTypeCd_Exec ")
		.append("   and t3.planTypeCd = :planTypeCd_Cost ");		

		return getDao().find(hql.toString(), params);
	}

	/**
	 * {计划节点,关联次数} 注意:若已生成项目节点,则模板节点设置无效.只有对未初始化成本节点的项目有效.
	 * @param projectCd
	 * @return
	 */
	public List<String> getRelCostNodeListCount() {

		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("projectCd", projectCd);
		params.put("planTypeCd_Exec", DictContants.PLAN_TYPE_EXC);
		params.put("planTypeCd_Cost", DictContants.PLAN_TYPE_COST);
		
//		StringBuffer hql = new StringBuffer()
//		.append(" select distinct t2,cout(t2) ")
//		.append(" from PlanProjectNodeRel t, PlanExecutionPlanNode t2, CostProjectNodeRel t3, CostExecutionPlanNode t4 ")
//		.append(" where  t.planExecutionPlanMain.projectCd  = :projectCd ")
//		.append("   and t3.costExecutionPlanMain.projectCd = :projectCd ")
//		.append("   and  t.nodeCd  = t2.nodeCd  ")
//		.append("   and t3.nodeCd = t4.nodeCd ")
//		.append("   and t2.planExecutionPlanNodeId = t4.planExecutionPlanNodeId ")
//		.append("   and  t.planTypeCd = :planTypeCd_Exec ")
//		.append("   and t3.planTypeCd = :planTypeCd_Cost ");		

		StringBuffer sql = new StringBuffer()
		.append(" select t.node_Cd from cost_execution_plan_node t ,  ")
		.append(" (select distinct(t2.plan_execution_plan_node_id),count(t2.plan_execution_plan_node_id)  ")
		.append("    from cost_execution_plan_node t2 ")
		.append("    where t2.plan_type_cd = :planTypeCd_Cost ")
		.append("    group by t2.plan_execution_plan_node_id ")
		.append("    having count(t2.plan_execution_plan_node_id) > 1 ")
		.append(" )t3 ")
		.append("  where t.plan_type_cd = :planTypeCd_Cost ")
		.append("    and t.plan_execution_plan_node_id = t3.plan_execution_plan_node_id ");
		return this.getDao().findBySql(sql.toString(), params);
	}
	

	public Map<String, PlanExecPlanDetail> getCostNodePlanDetailMap(String projectCd) {
		Map<String, PlanExecPlanDetail> retMap = new HashMap<String, PlanExecPlanDetail>();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectCd", projectCd);
		params.put("planTypeCd_Exec", DictContants.PLAN_TYPE_EXC);
		params.put("planTypeCd_Cost", DictContants.PLAN_TYPE_COST);
		

		// StringBuffer hql = new StringBuffer()
		// .append(" select distinct t.nodeCd,t5 ")
		// .append(" from PlanProjectNodeRel t, PlanExecutionPlanNode t2, CostProjectNodeRel t3, CostExecutionPlanNode t4, PlanExecPlanDetail t5 ")
		// .append(" where t.planExecutionPlanMain.projectCd  = :projectCd ")
		// .append("   and t3.costExecutionPlanMain.projectCd = :projectCd ")
		// .append("   and t.nodeCd  = t2.nodeCd  ")
		// .append("   and t3.nodeCd = t4.nodeCd ")
		// .append("   and t2.planExecutionPlanNodeId = t4.planExecutionPlanNodeId ")
		// .append("   and t5.planProjectNodeRel.planProjectNodeRelId = t.id ");
		
		
		StringBuffer hql = new StringBuffer()
		.append(" select t3.nodeCd, t0 ")
		.append("  from PlanExecPlanDetail    t0, ")
		.append("       PlanProjectNodeRel    t1, ")
		.append("       PlanExecutionPlanNode t2, ")
		.append("       CostExecutionPlanNode t3, ")
		.append("       CostProjectNodeRel    t4  ")
		.append(" where t0.planProjectNodeRel.planProjectNodeRelId = t1.planProjectNodeRelId ")
		.append("   and t1.nodeCd = t2.nodeCd ")
		.append("   and t2.planExecutionPlanNodeId = t3.planExecutionPlanNodeId ")
		.append("   and t3.nodeCd = t4.nodeCd ")
		.append("   and t4.costExecutionPlanMain.projectCd = :projectCd ")
		.append("   and t1.planExecutionPlanMain.projectCd = :projectCd ")
		.append("   and t1.planTypeCd = :planTypeCd_Exec ")
		.append("   and t4.planTypeCd = :planTypeCd_Cost ");		

		List<Object> list= getDao().find(hql.toString(), params);
		if(list == null || list.size() == 0)
			return retMap;
		
		String tPlanNodeCd = null;
		PlanExecPlanDetail tPlanExecPlanDetail = null;
		for (int i = 0; i < list.size(); i++) {
			Object[] tmpObj = (Object[])list.get(i);
			tPlanNodeCd = (String)tmpObj[0];
			tPlanExecPlanDetail = (PlanExecPlanDetail)tmpObj[1];
			retMap.put(tPlanNodeCd+"_"+ tPlanExecPlanDetail.getExecutionPlanCd(), tPlanExecPlanDetail);
		}
		
		return retMap;
	}
}
