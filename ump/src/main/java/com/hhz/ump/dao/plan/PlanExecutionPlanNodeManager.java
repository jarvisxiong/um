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
import com.hhz.ump.entity.plan.PlanExecutionPlanNode;

@Service
@Transactional
public class PlanExecutionPlanNodeManager extends BaseService<PlanExecutionPlanNode, String> {
	@Autowired
	private PlanExecutionPlanNodeDao planExecutionPlanNodeDao;

	@Transactional(readOnly = true)
	public PlanExecutionPlanNode getPlanExecutionPlanNode(String id) {
		return planExecutionPlanNodeDao.get(id);
	}
	
	public List<PlanExecutionPlanNode> getAllPlanExecutionPlanNode() {
		return planExecutionPlanNodeDao.getAll();
	}
	
	public void savePlanExecutionPlanNode(PlanExecutionPlanNode planExecutionPlanNode) {
		PowerUtils.setEmptyStr2Null(planExecutionPlanNode);
		planExecutionPlanNodeDao.save(planExecutionPlanNode);
	}

	public void deletePlanExecutionPlanNode(String id) {
		planExecutionPlanNodeDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlanExecutionPlanNode, String> getDao() {
		return planExecutionPlanNodeDao;
	}

	/**
	 * 搜索模板节点列表
	 * @param planTypeCd
	 * @param nodeName
	 * @param resOrgName
	 * @param corOrgName
	 * @param controlNodeFlg
	 * @return
	 */
	public List<PlanExecutionPlanNode> getPlanExecutionNodeList(String planTypeCd){
		return getPlanExecutionNodeList(planTypeCd, null, null, null, null);
	}
	public List<PlanExecutionPlanNode> getPlanExecutionNodeList(String planTypeCd,String nodeName, String resOrgName, String corOrgName, String controlNodeFlg){
		
		if (StringUtils.isBlank(planTypeCd))
			return new ArrayList<PlanExecutionPlanNode>();
		
		StringBuffer hql = new StringBuffer(" from PlanExecutionPlanNode n where 1=1 ");

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
		hql.append(" order by n.order1 asc, n.order2 asc, n.order3 asc ");
		return getDao().find(hql.toString(), params);
	}
	
	/**
	 * 获取节点CD和节点对象的映射
	 * @return
	 */
	public Map<String, PlanExecutionPlanNode> getExecPlanNodesMap() {
	    Map<String, PlanExecutionPlanNode> result = ExecutionPlanNodesCache.getNodesCache();
	    
	    if (result == null) {
		List<PlanExecutionPlanNode> allNodes = this.getAllPlanExecutionPlanNode();
		ExecutionPlanNodesCache.initCache(allNodes);
		result = ExecutionPlanNodesCache.getNodesCache();
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
	    
	    Map<String, PlanExecutionPlanNode> m = this.getExecPlanNodesMap();
	    PlanExecutionPlanNode n = m.get(nodeCd);
	    
	    if (n != null) {
		nodeName = n.getNodeName();
	    }
	    
	    return nodeName;
	}

}

