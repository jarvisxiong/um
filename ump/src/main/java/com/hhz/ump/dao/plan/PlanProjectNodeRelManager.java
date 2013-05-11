package com.hhz.ump.dao.plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.PlanProjectNodeRel;
import com.hhz.ump.web.vo.EntityVO;

@Service
@Transactional
public class PlanProjectNodeRelManager extends BaseService<PlanProjectNodeRel, String> {
	
	private static Log log = LogFactory.getLog(PlanProjectNodeRelManager.class);
	
	@Autowired
	private PlanProjectNodeRelDao planProjectNodeRelDao;

	// 是否控制节点
	public static String CONTROL_NODE_FLG_ENABLE = "1";
	public static String CONTROL_NODE_FLG_DISABLE = "0";

	// 是否删除 1-是 0-否
	public static String DELETED_FLG_Y = "1";
	public static String DELETED_FLG_N = "0";

	@Transactional(readOnly = true)
	public PlanProjectNodeRel getPlanProjectNodeRel(String id) {
		return planProjectNodeRelDao.get(id);
	}
	
	public List<PlanProjectNodeRel> getAllPlanProjectNodeRel() {
		return planProjectNodeRelDao.getAll();
	}
	
	public void savePlanProjectNodeRel(PlanProjectNodeRel planProjectNodeRel) {
		PowerUtils.setEmptyStr2Null(planProjectNodeRel);
		planProjectNodeRelDao.save(planProjectNodeRel);
	}

	public void deletePlanProjectNodeRel(String id) {
		planProjectNodeRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlanProjectNodeRel, String> getDao() {
		return planProjectNodeRelDao;
	}

	 
	/**
	 * 功能: 获取某个项目所有的执行计划节点
	 * @param projectCd
	 * @param planTypeCd
	 * 
	 * @return
	 */
	public List<PlanProjectNodeRel> getProjectNodeRelList(String projectCd,String planTypeCd){
		 Map<String, Object> params = new HashMap<String, Object>();
		    params.put("projectCd", projectCd);
		    params.put("planTypeCd", planTypeCd);
		    
		    if (StringUtils.isBlank(projectCd))
				return new ArrayList<PlanProjectNodeRel>();
		    
		    StringBuffer hqlBuf = new StringBuffer()
				    	.append(" from PlanProjectNodeRel n ")
						.append(" where n.planExecutionPlanMain.projectCd=:projectCd ")
						.append("  and  n.planTypeCd=:planTypeCd ")
						.append("  order by n.sequenceNo asc ");
						
		    return getDao().find(hqlBuf.toString(), params);
	}
	
	
	/**
	 * 功能: 有效节点
	 * @param projectCd
	 * @param planTypeCd
	 * @param nodeName
	 * @param resOrgCd
	 * @param resOrgName
	 * @param pointLevel
	 * @return
	 */
	public List<PlanProjectNodeRel> getValidProjNodes(String projectCd, String planTypeCd, String nodeName,String resOrgCd,String resOrgName,String treeType,boolean ifConfig,String nowPointLevel,String nowResOrgNames) {

	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("projectCd", projectCd);
	    params.put("planTypeCd", planTypeCd);
	    params.put("deletedFlg", DELETED_FLG_Y);
	    
	    if (StringUtils.isBlank(projectCd))
			return new ArrayList<PlanProjectNodeRel>();
	    
	    StringBuffer hqlBuf = new StringBuffer()
			    	.append(" from PlanProjectNodeRel n ")
					.append(" where n.planExecutionPlanMain.projectCd=:projectCd ")
					.append("  and  n.planTypeCd=:planTypeCd ")
			    	.append( "  and n.deletedFlg != :deletedFlg ");
			    	
	    if(StringUtils.isNotBlank(nowPointLevel)){
	    	hqlBuf.append(" and n.pointLevel <= :nowPointLevel ");
		    params.put("nowPointLevel", Long.parseLong(nowPointLevel));
	    }
	    
	    if(StringUtils.isNotBlank(resOrgCd)){
	    	hqlBuf.append(" and n.resOrgCd = :resOrgCd ");
		    params.put("resOrgCd", resOrgCd);
	    }
	    if (StringUtils.isNotBlank(resOrgName)) {
	    	hqlBuf.append(" and n.resOrgName like :resOrgName");
			params.put("resOrgName", "%" + resOrgName + "%");
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
	    if (StringUtils.isNotBlank(nodeName)) {
	    	hqlBuf.append(" and n.nodeName like :nodeName");
			params.put("nodeName", "%" + nodeName + "%");
	    }
	    if(StringUtils.isNotBlank(treeType)){
	    	hqlBuf.append(" and (n.treeType like '"+treeType+"')");
	    }
	    if(!ifConfig){
	    	//hqlBuf.append(" and  n.treeType is not null");
	    }
	    hqlBuf.append( " order by n.sequenceNo asc");
	    
	    return getDao().find(hqlBuf.toString(), params);
	}

	//同上
	public List<PlanProjectNodeRel> getValidProjNodesPage(String projectCd, String planTypeCd, String nodeName,String resOrgCd,String resOrgName,String treeType,boolean ifConfig,String treeTypeCd) {

		Page<PlanProjectNodeRel> page = new Page<PlanProjectNodeRel>(15);
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("projectCd", projectCd);
	    params.put("planTypeCd", planTypeCd);
	    params.put("deletedFlg", DELETED_FLG_Y);
	    
	    if (StringUtils.isBlank(projectCd))
			return page.getResult();
	    

	    StringBuffer hqlBuf = new StringBuffer()
			    	.append(" from PlanProjectNodeRel n ")
					.append(" where n.planExecutionPlanMain.projectCd=:projectCd ")
					.append("  and  n.planTypeCd=:planTypeCd ")
			    	.append( "  and n.deletedFlg != :deletedFlg ");
			    	
	    if(StringUtils.isNotBlank(treeTypeCd)){
	    	hqlBuf.append(" and n.treeType <= :treeType ");
		    params.put("treeType", treeTypeCd);
	    }
	    
	    if(StringUtils.isNotBlank(resOrgCd)){
	    	hqlBuf.append(" and n.resOrgCd = :resOrgCd ");
		    params.put("resOrgCd", resOrgCd);
	    }
	    if (StringUtils.isNotBlank(resOrgName)) {
	    	hqlBuf.append(" and n.resOrgName like :resOrgName");
			params.put("resOrgName", "%" + resOrgName + "%");
	    }
	    if (StringUtils.isNotBlank(nodeName)) {
	    	hqlBuf.append(" and n.nodeName like :nodeName");
			params.put("nodeName", "%" + nodeName + "%");
	    }
	    if(null!=treeType && !"".equalsIgnoreCase(treeType)){
	    	hqlBuf.append(" and (n.treeType like '"+treeType+"')");
	    }
	    if(!ifConfig){
	    	hqlBuf.append(" and  n.treeType is not null");
	    }
	    hqlBuf.append( " order by n.sequenceNo asc");
	    
	    return findPage(page, hqlBuf.toString(), params).getResult();
	}

	/*
	 * 获得同个项目下的专项计划所关联的节点
	 */
	public PlanProjectNodeRel getSameProjectNodeRel(PlanProjectNodeRel fromPpnr){
		PlanProjectNodeRel returnPpnr = fromPpnr;
		if("5".equalsIgnoreCase(fromPpnr.getPlanTypeCd())
				&& null!=fromPpnr.getTreeParentNodeId()
				&& !"".equalsIgnoreCase(fromPpnr.getTreeParentNodeId())){
		    Map<String, Object> params = new HashMap<String, Object>();
		    
		    StringBuffer hqlBuf = new StringBuffer()
				    	.append(" from PlanProjectNodeRel n ")
						.append(" where n.planExecutionPlanMain=:planMainId ")
						.append("  and  n.planTypeCd=1 ")
				    	.append( "  and n.nodeCd = :nodeCd ");
		    params.put("planMainId", fromPpnr.getPlanExecutionPlanMain());
		    params.put("nodeCd", fromPpnr.getTreeParentNodeId());
		    List<PlanProjectNodeRel> list = getDao().find(hqlBuf.toString(), params);
		    if(null!=list){
		    	returnPpnr = list.get(0);
		    }
		}
		return returnPpnr;
	}
	
	/*
	 * 获得指定的需要的最大值
	 */
	public long getMaxOrder(String orderName,long order1Value,long order2Value) {
		StringBuilder hql = new StringBuilder();
		String sqlWhere = "";
		if("order2".equalsIgnoreCase(orderName)){
			sqlWhere = "order1="+order1Value;
		}
		if("order3".equalsIgnoreCase(orderName)){
			sqlWhere = "order1="+order1Value + " and order2="+order2Value;
		}
		hql.append("select max("+orderName+") from PlanProjectNodeRel ")
			.append("where "+sqlWhere);
		List lstResult = getDao().find(hql.toString());
		long sn = 0;
		try {
			if (null != lstResult && 0 != lstResult.size()) {
				sn = ((Long) lstResult.get(0)).intValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sn++;
		return sn;
	}

	/**
	 * 功能: 获取某个项目有效的控制计划节点
	 * @param projectCd
	 * @param planTypeCd
	 * @param treeTypeCd
	 * 
	 * @return
	 */
 
	public List<PlanProjectNodeRel> getValidProjControlNodes(String projectCd, String planTypeCd,String resOrgCd,String resOrgName,String nowPointLevel, String nowResOrgNames) {
	    if (StringUtils.isBlank(projectCd))
			return new ArrayList<PlanProjectNodeRel>();

	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("projectCd", projectCd);
	    params.put("planTypeCd", planTypeCd);
		params.put("controlNodeFlg", CONTROL_NODE_FLG_ENABLE);
		params.put("deletedFlg", DELETED_FLG_Y);
	    
	    StringBuffer hqlBuf = new StringBuffer()
				    .append(" from PlanProjectNodeRel n " )
				    .append(" where n.planExecutionPlanMain.projectCd=:projectCd " )
				    .append("   and n.controlNodeFlg = :controlNodeFlg " )
				    .append("   and n.planTypeCd = :planTypeCd " )
				    .append("   and (n.deletedFlg is null or n.deletedFlg != :deletedFlg)" );
	    
	    if(StringUtils.isNotBlank(nowPointLevel)){
	    	hqlBuf.append("   and n.pointLevel <= :nowPointLevel ");
	    	params.put("nowPointLevel", nowPointLevel);
	    }
	    if(StringUtils.isNotBlank(resOrgCd)){
	    	  hqlBuf.append("   and n.resOrgCd = :resOrgCd ");
	  		params.put("resOrgCd", resOrgCd);
	    }
	    if(StringUtils.isNotBlank(resOrgName)){
	    	  hqlBuf.append("   and n.resOrgName = :resOrgName ");
	  		params.put("resOrgName", resOrgName);
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
	    hqlBuf.append(" order by n.sequenceNo asc ");
	    return getDao().find(hqlBuf.toString(), params);
	}

	/**
	 * 功能: 搜索主责中心列表
	 * @param planTypeCd
	 * 
	 * @return
	 */
	public List<EntityVO> getResOrgList(String planTypeCd) {

		Map<String,Object> params = new HashMap<String,Object>();
		params.put("planTypeCd", planTypeCd);
		String hql = " select distinct rel.resOrgCd,rel.resOrgName  from PlanProjectNodeRel rel where rel.planTypeCd =:planTypeCd ";
		List<Object[]> list = this.getDao().find(hql, params);
		if(list == null)
			return new ArrayList<EntityVO>();
		
		EntityVO vo = null;
		Object[] obj = null;
		String tmpCd = null;
		String tmpName = null;
		List<EntityVO> voList = new ArrayList<EntityVO>();
		for (int i = 0; i < list.size(); i++) {
			obj = list.get(i);
			tmpCd = (String)obj[0];
			tmpName = (String)obj[1];
			if(StringUtils.isBlank(tmpName)){
				log.debug("配合中心为空!cd:" + tmpCd + ", name : " + tmpName);
				continue;
			}
			vo= new EntityVO();
			vo.setEntityCd((String)obj[0]);
			vo.setEntityName((String)obj[1]);
			voList.add(vo);
		}
		return voList;
	} 
	
	/**
	 * 获取模板节点对应项目节点ID(用于上级节点字段、显示节点字段使用)
	 * @param templateNodeId
	 * @param projectCd
	 * @param planTypeCd
	 * @return
	 */
	public String getEntityRelIdRefTemplateNode(String templateNodeId,String projectCd,String planTypeCd){
		StringBuffer hql = new StringBuffer()
				.append("select t.planProjectNodeRelId from PlanProjectNodeRel t " )
				.append(" where t.planTypeCd = :planTypeCd ")
				.append(" and t.planExecutionPlanMain.projectCd = :projectCd ")
				.append(" and t.nodeCd in(select t2.nodeCd from PlanExecutionPlanNode t2 where t2.planExecutionPlanNodeId = :planExecutionPlanNodeId) ");

		Map<String,Object> params = new HashMap<String,Object>();
		params.put("planTypeCd", planTypeCd);
		params.put("projectCd", projectCd);
		params.put("planExecutionPlanNodeId", templateNodeId);
		
		List<String> result = getDao().find(hql.toString(), params);
		if(result == null || result.size()==0)
			return null;
		else if(result.size()==1)
			return result.get(0);
		else{
			log.error("多条记录!templateNodeId:" + templateNodeId + ",planTypeCd:" + planTypeCd + ",projectCd:" + projectCd);
			return null;
		}
	}
	 
}

