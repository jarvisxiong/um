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
import com.hhz.ump.entity.plan.ExecPlanNode;

@Service
@Transactional
public class ExecPlanNodeManager extends BaseService<ExecPlanNode, String> {
	@Autowired
	private ExecPlanNodeDao execPlanNodeDao;

	public void saveExecPlanNode(ExecPlanNode execPlanNode) {
		PowerUtils.setEmptyStr2Null(execPlanNode);
		execPlanNodeDao.save(execPlanNode);
	}

	public void deleteExecPlanNode(String id) {
		execPlanNodeDao.delete(id);
	}
	
	@Override
	public HibernateDao<ExecPlanNode, String> getDao() {
		return execPlanNodeDao;
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
	public List<ExecPlanNode> getPlanExecutionNodeList(String planTypeCd){
		return getPlanExecutionNodeList(planTypeCd, null, null, null, null);
	}
	public List<ExecPlanNode> getPlanExecutionNodeList(String planTypeCd,String nodeName, String resOrgName, String corOrgName, String controlNodeFlg){
		
		if (StringUtils.isBlank(planTypeCd))
			return new ArrayList<ExecPlanNode>();
		
		StringBuffer hql = new StringBuffer(" from ExecPlanNode n where 1=1 ");

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
		hql.append(" order by n.sequenceNo asc ");
		return getDao().find(hql.toString(), params);
	}
	

	/**
	 * 搜索节点
	 * @return
	 */
	public List<ExecPlanNode> searchNodes(String planTypeCd,
			String nowPointLevel,String nowResOrgNames) {

	    Map<String, Object> params = new HashMap<String, Object>();
	    
	    StringBuffer hqlBuf = new StringBuffer()
			    	.append(" from ExecPlanNode n ");
	    hqlBuf.append(" where n.planTypeCd=:planTypeCd ");
	    params.put("planTypeCd", planTypeCd);
	    
	    if(StringUtils.isNotBlank(nowPointLevel)
	    		&& ("1".equalsIgnoreCase(planTypeCd)||"2".equalsIgnoreCase(planTypeCd)||"3".equalsIgnoreCase(planTypeCd))){
	    	hqlBuf.append(" and n.pointLevel <= :nowPointLevel ");
		    params.put("nowPointLevel", Long.parseLong(nowPointLevel));
	    }
	    if(StringUtils.isNotBlank(nowResOrgNames)){
	    	String[] rons = nowResOrgNames.split(";");
	    	if(null!=rons&&rons.length>0){
	    		hqlBuf.append(" and (");
		    	for(int i=0;i<rons.length;i++){
		    		String ron = rons[i];
		    		if(0==i){
		    			hqlBuf.append("n.resOrgName like :resOrgName"+i);
		    		}else{
		    			hqlBuf.append(" or n.resOrgName like :resOrgName"+i);
		    		}
			  		params.put("resOrgName"+i, "%"+ron+"%");
		    	}
	    		hqlBuf.append(") ");
	    	}
	    }
	    hqlBuf.append( " order by n.sequenceNo asc");
	    
	    return getDao().find(hqlBuf.toString(), params);
	}

	/*
	 * 获得建设开发计划所关联的节点
	 */
	public ExecPlanNode getSameNode1(ExecPlanNode fromEpn){
		ExecPlanNode returnEpn = fromEpn;
		if(("5".equalsIgnoreCase(fromEpn.getPlanTypeCd())
				||"14".equalsIgnoreCase(fromEpn.getPlanTypeCd())
				||"15".equalsIgnoreCase(fromEpn.getPlanTypeCd())
				||"24".equalsIgnoreCase(fromEpn.getPlanTypeCd())//add by dido(2011.12.13) 解决新的商业四级计划
				||"16".equalsIgnoreCase(fromEpn.getPlanTypeCd()))
				&& null!=fromEpn.getTreeParentNodeId()
				&& !"".equalsIgnoreCase(fromEpn.getTreeParentNodeId())){
		    Map<String, Object> params = new HashMap<String, Object>();
		    StringBuffer hqlBuf = new StringBuffer()
				    	.append(" from ExecPlanNode n ")
						.append(" where n.planTypeCd=1 ")
				    	.append( "  and n.nodeCd = :nodeCd ");
		    params.put("nodeCd", fromEpn.getTreeParentNodeId());
		    List<ExecPlanNode> list = getDao().find(hqlBuf.toString(), params);
		    if(null!=list && list.size()>0){
		    	returnEpn = list.get(0);
		    }
		}
		return returnEpn;
	}
	/*
	 * 获得商业四级计划所关联的节点
	 */
	public ExecPlanNode getSameNode14(ExecPlanNode fromEpn){
		ExecPlanNode returnEpn = fromEpn;
		if(null!=fromEpn.getTreeParentNodeId()
				&& !"".equalsIgnoreCase(fromEpn.getTreeParentNodeId())
				&& null!=fromEpn.getIfParentSame()
				&& fromEpn.getIfParentSame()){
		    Map<String, Object> params = new HashMap<String, Object>();
		    StringBuffer hqlBuf = new StringBuffer()
				    	.append(" from ExecPlanNode n ")
						.append(" where n.planTypeCd=14 ")
				    	.append( "  and n.execPlanNodeId = :nodeId ");
		    params.put("nodeId", fromEpn.getTreeParentNodeId());
		    List<ExecPlanNode> list = getDao().find(hqlBuf.toString(), params);
		    if(null!=list && list.size()>0){
		    	returnEpn = list.get(0);
		    }
		}
		return returnEpn;
	}
	/*
	 * 获得商业开业五级级计划所关联的节点
	 */
	public ExecPlanNode getSameNode15(ExecPlanNode fromEpn){
		ExecPlanNode returnEpn = fromEpn;
	    Map<String, Object> params = new HashMap<String, Object>();
	    StringBuffer hqlBuf = new StringBuffer()
			    	.append(" from ExecPlanNode n ")
					.append(" where n.planTypeCd=15 ")
			    	.append( "  and n.execPlanNodeId = :nodeId ");
	    params.put("nodeId", fromEpn.getParentId15());
	    List<ExecPlanNode> list = getDao().find(hqlBuf.toString(), params);
	    if(null!=list && list.size()>0){
	    	returnEpn = list.get(0);
	    }
		return returnEpn;
	}
	/**
	 * 功能: 搜索主责中心列表
	 * @param planTypeCd
	 * 
	 * @return
	 */
	public List<ExecPlanNode> getResOrgList(String planTypeCd) {

		Map<String,Object> params = new HashMap<String,Object>();
		params.put("planTypeCd", planTypeCd);
		String hql = " select distinct rel.resOrgCd,rel.resOrgName  from ExecPlanNode rel where rel.planTypeCd =:planTypeCd ";
		List<Object[]> list = this.getDao().find(hql, params);
		if(list == null)
			return new ArrayList<ExecPlanNode>();
		
		ExecPlanNode vo = null;
		Object[] obj = null;
		String tmpCd = null;
		String tmpName = null;
		List<ExecPlanNode> voList = new ArrayList<ExecPlanNode>();
		for (int i = 0; i < list.size(); i++) {
			obj = list.get(i);
			tmpCd = (String)obj[0];
			tmpName = (String)obj[1];
			if(StringUtils.isBlank(tmpName)){
				continue;
			}
			vo= new ExecPlanNode();
			vo.setResOrgCd((String)obj[0]);
			vo.setResOrgName((String)obj[1]);
//			vo.setEntityCd((String)obj[0]);
//			vo.setEntityName((String)obj[1]);
			voList.add(vo);
		}
		return voList;
	}
	
	/**
	 * 搜索对应级别的节点
	 * 
	 * @return
	 */
	public List<ExecPlanNode> searchNodesLvel(String planTypeCd,
			String nowPointLevel) {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hqlBuf = new StringBuffer().append(" from ExecPlanNode n ");
		hqlBuf.append(" where n.planTypeCd=:planTypeCd  and n.pointLevel = :nowPointLevel ");
		params.put("planTypeCd", planTypeCd);
		params.put("nowPointLevel", Long.parseLong(nowPointLevel));
		hqlBuf.append(" order by n.sequenceNo asc");
		return getDao().find(hqlBuf.toString(), params);
	}
	

	/*
	 * 根据节点相关信息搜索节点
	 */
	public ExecPlanNode getEntityByNodeCd(String nodeCd,String planTypeCd) {
		if(StringUtils.isNotBlank(nodeCd) && StringUtils.isNotBlank(planTypeCd)){
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder sb = new StringBuilder("from ExecPlanNode where");

			sb.append(" nodeCd = :nodeCd and planTypeCd = :planTypeCd");
		    params.put("nodeCd", nodeCd);
		    params.put("planTypeCd", planTypeCd);
		    
			List<ExecPlanNode> arr = this.getDao().find(sb.toString(), params);
			if(null!=arr && arr.size()>0)
				return arr.get(0);
			else
				return null;
		} else
			return null;
	}
}

