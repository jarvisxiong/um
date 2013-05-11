package com.hhz.ump.dao.planold;

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
import com.hhz.ump.entity.planold.PlanExecutionPlanNodeOld;

@Service
@Transactional
public class PlanExecutionPlanNodeOldManager extends BaseService<PlanExecutionPlanNodeOld, String> {
	@Autowired
	private PlanExecutionPlanNodeOldDao planExecutionPlanNodeDao;

	@Transactional(readOnly = true)
	public PlanExecutionPlanNodeOld getPlanExecutionPlanNode(String id) {
		return planExecutionPlanNodeDao.get(id);
	}
	
	public List<PlanExecutionPlanNodeOld> getAllPlanExecutionPlanNode() {
		return planExecutionPlanNodeDao.getAll();
	}
	
	public void savePlanExecutionPlanNode(PlanExecutionPlanNodeOld planExecutionPlanNode) {
		PowerUtils.setEmptyStr2Null(planExecutionPlanNode);
		planExecutionPlanNodeDao.save(planExecutionPlanNode);
	}

	public void deletePlanExecutionPlanNode(String id) {
		planExecutionPlanNodeDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlanExecutionPlanNodeOld, String> getDao() {
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
	public List<PlanExecutionPlanNodeOld> getPlanExecutionNodeList(String planTypeCd){
		return getPlanExecutionNodeList(planTypeCd, null, null, null, null);
	}
	public List<PlanExecutionPlanNodeOld> getPlanExecutionNodeList(String planTypeCd,String nodeName, String resOrgName, String corOrgName, String controlNodeFlg){
		
		if (StringUtils.isBlank(planTypeCd))
			return new ArrayList<PlanExecutionPlanNodeOld>();
		
		StringBuffer hql = new StringBuffer(" from PlanExecutionPlanNodeOld n where 1=1 ");

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
		hql.append(" order by n.order1 asc ,n.order2 asc, n.order3 asc " );
		return getDao().find(hql.toString(), params);
	}
	
	/**
	 * 获取节点CD和节点对象的映射
	 * @return
	 */
	public Map<String, PlanExecutionPlanNodeOld> getExecPlanNodesMap() {
	    Map<String, PlanExecutionPlanNodeOld> result = ExecutionPlanNodeOldsCache.getNodesCache();
	    
	    if (result == null) {
		List<PlanExecutionPlanNodeOld> allNodes = this.getAllPlanExecutionPlanNode();
		ExecutionPlanNodeOldsCache.initCache(allNodes);
		result = ExecutionPlanNodeOldsCache.getNodesCache();
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
	    
	    Map<String, PlanExecutionPlanNodeOld> m = this.getExecPlanNodesMap();
	    PlanExecutionPlanNodeOld n = m.get(nodeCd);
	    
	    if (n != null) {
		nodeName = n.getNodeName();
	    }
	    
	    return nodeName;
	}

}

