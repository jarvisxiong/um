package com.hhz.uums.dao.plas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasDimeOrgRel;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.vo.vw.OrgTreeVo;
import com.hhz.uums.vo.vw.VoOrg;
import com.hhz.uums.vo.ws.WsPlasOrgRel;

@Service
@Transactional
@SuppressWarnings("rawtypes")

public class PlasDimeOrgRelManager extends BaseService<PlasDimeOrgRel, String> {
	
	private static Log log = LogFactory.getLog(PlasDimeOrgRelManager.class);
	
	@Autowired
	private PlasDimeOrgRelDao plasOrgDimeRelDao;
	@Autowired
	private PlasOrgDimeManager plasOrgDimeManager;

	public void savePlasOrgDimeRel(PlasDimeOrgRel plasDimeOrgRel) {
		PowerUtils.setEmptyStr2Null(plasDimeOrgRel);
		plasOrgDimeRelDao.save(plasDimeOrgRel);
	}

	public void deletePlasDimeOrgRel(String id) {
		plasOrgDimeRelDao.delete(id);
	}	
	@Override
	public HibernateDao<PlasDimeOrgRel, String> getDao() {
		return plasOrgDimeRelDao;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasDimeOrgRel> getDimeOrgRelList(String plasOrgId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("plasOrgId", plasOrgId);
		List<PlasDimeOrgRel> list= find("from PlasDimeOrgRel where plasOrg.plasOrgId = :plasOrgId order by plasOrgDime.dimeCd asc",map);
		if(list == null)
			return new ArrayList<PlasDimeOrgRel>();
		else
			return list;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasDimeOrgRel> getAllOrgRelList() {
		List<PlasDimeOrgRel> list= find("from PlasDimeOrgRel order by plasOrgDime.dimeCd asc, plasOrg.sequenceNo asc ");
		if(list == null)
			return new ArrayList<PlasDimeOrgRel>();
		else
			return list;
	}
	
	
	/**
	 * 查询机构ID相关的维度表的关系
	 * @param orgId
	 * @return List{{PlasOrgDime,parentId,parentName}...}
	 */
	public List<Object> getDimeRelList(String orgId){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("plasOrgId", orgId);
		
		StringBuffer sqlBuf = new StringBuffer()
				.append(" select t.plas_org_dime_id,t.dime_cd,t.dime_name,tt.parent_id,tt.org_name,tt.plas_dime_org_rel_id,t.sequence_no from plas_org_dime t ")
				.append(" left join (")
				.append("  	  select t2.plas_dime_org_rel_id,t2.parent_id,t3.org_name,t2.plas_org_dime_id ")
				.append("     from plas_dime_org_rel t2 ")
				.append("     left join plas_org t3 on t3.plas_org_id = t2.parent_id   ")
				.append("     where t2.plas_org_id = :plasOrgId  ")
				.append(" )tt on tt.plas_org_dime_id = t.plas_org_dime_id ")
				.append(" order by t.sequence_no asc ");

		return this.getDao().findBySql(sqlBuf.toString(), map);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public PlasDimeOrgRel getPlasDimeOrgRel(String plasOrgId,String dimeCd) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("plasOrgId", plasOrgId);
		map.put("dimeCd", dimeCd);
		List<PlasDimeOrgRel> list= find("from PlasDimeOrgRel where plasOrg.plasOrgId = :plasOrgId and plasOrgDime.dimeCd = :dimeCd order by plasOrgDime.dimeCd asc",map);
		if(list == null || list.size() == 0)
			return null;
		else
			return list.get(0);
	}
	/**
	 * 查询特定维度,特定机构ID的父亲节点ID值
	 * @param plasOrgId
	 * @param dimeCd
	 * @return
	 */
	public String getParentOrgId(String plasOrgId,String dimeCd) {
		PlasDimeOrgRel rel = getPlasDimeOrgRel(plasOrgId,dimeCd);
		if(rel == null)
			return null;
		else
			return rel.getParentId();
	} 
	@Override
	public List<PlasDimeOrgRel> getAll(){
		return getAll("treeLevel", true);
	}
	
	/**
	 * 查询特定维度的机构列表
	 * @param dimeCd
	 * @return
	 */
	public List<PlasDimeOrgRel> getListByDimeCd(String dimeCd){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("dimeCd", dimeCd);
		List<PlasDimeOrgRel> list= find("from PlasDimeOrgRel where plasOrgDime.dimeCd = :dimeCd order by treeLevel asc",map);
		if(list == null)
			return new ArrayList<PlasDimeOrgRel>();
		else
			return list;
	}
	/**
	 * 查询特定维度的机构列表
	 * @param PlasOrgDimeId
	 * @return
	 */
	public List<PlasDimeOrgRel> getListByPlasOrgDimeId(String PlasOrgDimeId){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("dimeCd", PlasOrgDimeId);
		List<PlasDimeOrgRel> list= find("from PlasDimeOrgRel where plasOrgDime.plasOrgDimeId = :PlasOrgDimeId order by treeLevel asc",map);
		if(list == null)
			return new ArrayList<PlasDimeOrgRel>();
		else
			return list;
	}

	/**
	 * 刷新维度机构的层级
	 * @param dimeCd
	 */
	public void refrshDataTreeLevel(String plasOrgDimeId) {
		refrshDataTreeLevel(plasOrgDimeId,new ArrayList<String>(),1);
	}
	public void refrshDataTreeLevel(String plasOrgDimeId,List<String> orgIdList,long treeLevel){
		
		log.info(" 刷新维度 " + plasOrgDimeId + " 层级:" + treeLevel);
		if(orgIdList == null){
			orgIdList = new ArrayList<String>();
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("treeLevel", treeLevel);
		map.put("PlasOrgDimeId", plasOrgDimeId);

		//更新treeLevel
		StringBuffer includeHql = new StringBuffer();
		if(orgIdList.size() == 0){
			includeHql.append(" and t.parentId is null ");
		}else{
			includeHql.append(" and ( ");
			for (int i = 0; i < orgIdList.size(); i++) {;
				if(i == 0){
					includeHql.append(" t.parentId = :parentId"+i+" ");
				}else{
					includeHql.append(" or t.parentId = :parentId"+i+" ");
				}
				map.put("parentId"+i, orgIdList.get(i));
			}
			includeHql.append(" )");
		}
		int count = this.getDao().batchExecute("update PlasDimeOrgRel t set t.treeLevel = :treeLevel where t.plasOrgDime.plasOrgDimeId = :PlasOrgDimeId " + includeHql.toString(), map);
		log.info("影响:"+ count + "行");
		

		//查询下级
		StringBuffer excludeHql = new StringBuffer();
		if(orgIdList.size() == 0){
			excludeHql.append(" and t.parentId is not null ");
		}else{
			excludeHql.append(" and ( ");
			for (int i = 0; i < orgIdList.size(); i++) {;
				if(i == 0){
					excludeHql.append(" t.plasOrg.plasOrgId != :plasOrgId"+i+" ");
				}else{
					excludeHql.append(" and t.plasOrg.plasOrgId != :plasOrgId"+i+" ");
				}
				map.put("plasOrgId"+i, orgIdList.get(i));
			}
			excludeHql.append(" )");
		}
		
		List<String> list = this.getDao().createQuery("select t.plasOrg.plasOrgId from PlasDimeOrgRel t where t.treeLevel = :treeLevel and t.plasOrgDime.plasOrgDimeId = :PlasOrgDimeId " + includeHql.toString(),map).list();
		
		if(list == null || list.size() == 0)
			return;
		else{
			refrshDataTreeLevel(plasOrgDimeId, list, ++treeLevel);
		}
	}
	/**
	 * 
	 * Description:查询机构
	 * author:jiaoxiaofeng  2011-4-11
	 * @param plasOrgId
	 * @param dimeCd
	 * @return
	 * PlasOrg
	 */
	public PlasOrg getOrg(String plasOrgId,String dimeCd){
		return getEntity(plasOrgId,dimeCd).getPlasOrg();
	}
	public PlasDimeOrgRel getEntity(String plasOrgId,String dimeCd){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("plasOrgId", plasOrgId);
		map.put("dimeCd",dimeCd);
		List<PlasDimeOrgRel> list= find("from PlasDimeOrgRel where plasOrg.plasOrgId = :plasOrgId and plasOrgDime.dimeCd = :dimeCd order by plasOrgDime.dimeCd asc",map);
		if(list == null)
			return new PlasDimeOrgRel();
		else
			return list.get(0);
	}
	
	public List<OrgTreeVo> getOrgTreeVoList(String dimeCd){
		String hql = "select t2.plasOrgId,t2.orgName,t1.parentId,t2.orgCd from PlasDimeOrgRel t1 ,PlasOrg t2" +
				" where t1.plasOrg.plasOrgId = t2.plasOrgId and t1.plasOrgDime.dimeCd = :dimeCd  order by t2.sequenceNo asc";
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("dimeCd", dimeCd);
		List list = find(hql,values );
		OrgTreeVo tmp = null;
		List<OrgTreeVo> result = new ArrayList<OrgTreeVo>();
		String tmpParentId = null;
		for(Object obj : list){
			Object[] o = (Object[]) obj;
			tmp = new OrgTreeVo();
			tmp.setOrgId((String)o[0]);
			tmp.setOrgName((String)o[1]);
			tmpParentId = (String)o[2];
			if(null==tmpParentId) {
				tmpParentId = DictContants.ORG_ROOT_CD;
			}
			tmp.setParentId(tmpParentId);
			tmp.setOrgCd((String)o[3]);
			result.add(tmp);
		}
		return result;
	}
	/**
	 * 获取机构列表
	 * @param dimeCd
	 * @param isAllOrgFlg  true-全部机构 false-有效机构
	 * @return
	 */
	public VoOrg getVoOrg(String dimeCd,boolean isAllOrgFlg, String orgCd){
		List<VoOrg>  list= getVoOrgList(dimeCd, isAllOrgFlg, null);
		if(list== null || list.size() == 0)
			return null;
		else
			return list.get(0);
	}
	public List<VoOrg> getVoOrgList(String dimeCd,boolean isAllOrgFlg){
		return getVoOrgList(dimeCd, isAllOrgFlg, null);
	}
	
	//已移除机构
	public List<VoOrg> getDelOrgList(){
		
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("activeBl", new Boolean(false));
		
		String hql = "select t2.plasOrgId,t2.orgCd,t2.orgBizCd,t2.orgName from PlasOrg t2 where t2.activeBl = :activeBl ";
		List list = find(hql, values);
		List<VoOrg> result = new ArrayList<VoOrg>();
		VoOrg tmp = null;
		Object[] o = null;
		for(Object t : list){
			o = (Object[])t;
			tmp = new VoOrg();
			tmp.setOrgId((String)o[0]);
			tmp.setOrgCd((String)o[1]);
			tmp.setOrgBizCd((String)o[2]);
			tmp.setOrgName((String)o[3]);
			result.add(tmp);
			//System.out.println( tmp.getOrgId() + "/"+tmp.getOrgCd()+ "/"+ tmp.getOrgBizCd()+ "/"+ tmp.getOrgName());
		}
		System.out.println("失效的机构列表,大小: " + result.size());
		return result;
	}
	
	public List<VoOrg> getVoOrgList(String dimeCd,boolean isAllOrgFlg, String orgCd){
		
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("dimeCd", dimeCd);
		values.put("activeBl", new Boolean(true));
		values.put("orgCd", orgCd);
		
		StringBuffer hqlBuf = new StringBuffer()
				.append("select distinct t2.plasOrgId,")
				.append("       t1.parentId, ")
				.append("       t2.orgName, ")
				.append("       t2.orgCd, ")
				.append("       t2.orgBizCd, ")
				.append("       t2.activeBl, ")
				.append("       t2.sequenceNo, ")
				.append("       t2.visableFlg ")
				.append("  from PlasDimeOrgRel t1 ,PlasOrg t2" )
				.append(" where t1.plasOrg.plasOrgId = t2.plasOrgId ")
				.append("   and t1.plasOrgDime.dimeCd = :dimeCd  ");
				
		if(!isAllOrgFlg){
			hqlBuf.append(" and t2.activeBl = :activeBl ");
		}
		if(StringUtils.isNotBlank(orgCd)){
			hqlBuf.append(" and t2.orgCd = :orgCd ");
		}
			hqlBuf.append(" order by t2.sequenceNo asc,t2.orgBizCd asc ");
			
		List list = find(hqlBuf.toString(),values );
		List<VoOrg> result = new ArrayList<VoOrg>();
		VoOrg tmp = null;
		Object[] o = null;
		Long t = 10000L;//从1000开始
		Boolean tmpV = false;
		for(Object obj : list){
			o = (Object[]) obj;
			tmp = new VoOrg();
			tmp.setOrgId((String)o[0]);
			tmp.setParentOrgId((String)o[1]);
			tmp.setOrgName((String)o[2]);
			tmp.setOrgCd((String)o[3]);
			tmp.setOrgBizCd((String)o[4]);
			
			if(StringUtils.isBlank(tmp.getParentOrgId())){
				tmp.setParentOrgId(TreePanelUtil2.DEFAULT_ROOT_ORG_ID);
			}

			//coremail使用
			tmp.setActiveBl((Boolean)o[5]);
			tmp.setSequenceNo((Long)o[6]);
			tmp.setDispOrderNo(++t);
			tmpV = (Boolean)o[7];
			tmp.setVisableFlg((tmpV == null)? new Boolean(true):tmpV);
			
			result.add(tmp);
		}
		return result;
	}
	public Map<String,List<OrgTreeVo>> getOrgTreeVoMap(String dimeCd){
		String hql = "select t2.plasOrgId,t2.orgName,t1.parentId,t2.orgTypeCd from PlasDimeOrgRel t1 ,PlasOrg t2" +
				" where t1.plasOrg.plasOrgId = t2.plasOrgId and t1.plasOrgDime.dimeCd = :dimeCd  order by t2.orgBizCd asc";
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("dimeCd", dimeCd);
		List list = find(hql,values );
		OrgTreeVo tmp = null;
		List<OrgTreeVo> result =null;
		String tmpParentId = null;
		Map<String,List<OrgTreeVo>> vos = new HashMap<String,List<OrgTreeVo>>();
		for(Object obj : list){
			Object[] o = (Object[]) obj;
			tmp = new OrgTreeVo();
			tmp.setOrgId((String)o[0]);
			tmp.setOrgName((String)o[1]);
			tmp.setOrgTypeCd((String)o[3]);
			tmpParentId = (String)o[2];
			if(null==tmpParentId) {
				tmpParentId = DictContants.ORG_ROOT_CD;
			}
			tmp.setParentId(tmpParentId);
			
			result = vos.get(tmpParentId);
			if(null==result){
				result =   new ArrayList<OrgTreeVo>();
			}
			result.add(tmp);

			vos.put(tmp.getParentId(), result);
			
		}
		return vos;
	}
	/**
	 * 查询待处理账号所在机构列表
	 */
	public Map<String,List<OrgTreeVo>> getVoWaitingOrgList(String dimeCd){
		//TODU
		StringBuffer hql = new StringBuffer();
		hql.append("select distinct t.plasUser.plasOrg.plasOrgId,t.plasUser.plasOrg.orgName,d.parentId,t.plasUser.plasOrg.orgTypeCd ")
		.append(" from PlasAcct t,PlasDimeOrgRel d ")
		.append(" where t.plasUser.plasOrg.plasOrgId = d.plasOrg.plasOrgId ")
		.append(" and t.plasAcctId not in (").
		append("	select t1.plasAcct.plasAcctId from PlasSysPosition t1 where t1.plasAcct.plasAcctId is not null) ")
		;
		
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("dimeCd", dimeCd);
		List list = this.getDao().find(hql.toString(),values );
		OrgTreeVo tmp = null;
		List<OrgTreeVo> result =null;
		String tmpParentId = null;
		Map<String,List<OrgTreeVo>> vos = new HashMap<String,List<OrgTreeVo>>();
		for(Object obj : list){
			Object[] o = (Object[]) obj;
			tmp = new OrgTreeVo();
			tmp.setOrgId((String)o[0]);
			tmp.setOrgName((String)o[1]);
			tmp.setOrgTypeCd((String)o[3]);
			tmpParentId = (String)o[2];
			if(null==tmpParentId) {
				tmpParentId = DictContants.ORG_ROOT_CD;
			}
			tmp.setParentId(tmpParentId);
			
			result = vos.get(tmpParentId);
			if(null==result){
				result =   new ArrayList<OrgTreeVo>();
			}
			result.add(tmp);
			
			vos.put(tmp.getParentId(), result);
			
		}
		return vos;
	}
	/**
	 * 
	 * Description 获得机构视图类型
	 */
	public String getOrgDimeType(String plasOrgId){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("activeBl", true);
		params.put("orgId", plasOrgId);
		String hql = "from PlasDimeOrgRel t where t.plasOrg.activeBl = :activeBl and (t.plasOrg.plasOrgId =:orgId or t.parentId =:orgId)";
		List<PlasDimeOrgRel> list = this.find(hql, params);
		if(null==list||list.size()==0)return "0";
		int physical = 0;
		int logical = 0;
		for(PlasDimeOrgRel obj : list){
			if(obj.getPlasOrgDime().getDimeCd().equals(DictContants.TREE_DIME_PHYSICAL)){
				physical++;
			} else {
				logical++;
			}
		}
		if(physical>0&&logical>0)
			return DictContants.TREE_DIME_LOG_PHY;
		else if(physical>0)
			return DictContants.TREE_DIME_PHYSICAL;
		else if(logical>0)
			return DictContants.TREE_DIME_LOGICAL;
		else return "0";
	}

	/**
	 * 保存特定维度的两个机构关系
	 * 注意:保存前需要删除
	 * @param rel
	 */
	public void saveNewDimeOrgRel(PlasDimeOrgRel rel) {
		
		this.deleteOldDimeOrgRel(rel.getPlasOrg().getPlasOrgId(), rel.getParentId(),rel.getPlasOrgDime().getPlasOrgDimeId());
		this.savePlasOrgDimeRel(rel);
	}
	public void saveNewDimeOrgRel1(PlasDimeOrgRel rel) {
		if(DictContants.TREE_DIME_PHYSICAL.equals(rel.getPlasOrgDime().getDimeCd())){
			PlasDimeOrgRel newRel = new PlasDimeOrgRel();
			newRel.setPlasOrg(rel.getPlasOrg());
			newRel.setPlasOrgDime(this.plasOrgDimeManager.getEntityByDimeCd(DictContants.TREE_DIME_LOGICAL));
			newRel.setParentId(rel.getParentId());
			this.savePlasOrgDimeRel(newRel);
		}
	}

	
	/**
	 * 删除机构关联关系 
	 * @param orgId
	 * @param parentId
	 * @param dimeId
	 */
	public void deleteOldDimeOrgRel(String orgId, String parentId, String dimeId){

		//删除旧关系
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("orgId", orgId);
		values.put("parentId", parentId);
		values.put("dimeId", dimeId);
		String hqlDel = " delete from PlasDimeOrgRel t where t.plasOrg.plasOrgId = :orgId and t.plasOrgDime.plasOrgDimeId = :dimeId";
		this.getDao().batchExecute(hqlDel, values);
	}

	/**
	 * 删除特定维度机构关联
	 * @param orgId
	 * @param parentId
	 * @param dimeId
	 * @param relId
	 */
	public void deleteOrgRel(String orgId, String parentId, String dimeId, String relId) {
		
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("orgId", orgId);
		values.put("parentId", parentId);
		values.put("dimeId", dimeId);
		values.put("relId", relId);
		
		String hql = new StringBuffer()
					.append(" delete from PlasDimeOrgRel t " )
					.append(" where t.plasOrg.plasOrgId = :orgId " )
					.append("   and t.parentId = :parentId " )
					.append("   and t.plasOrgDime.plasOrgDimeId = :dimeId " )
					.append("   and t.plasDimeOrgRelId = :relId ")
					.toString();
		
		this.getDao().batchExecute(hql, values);
	}

	/**
	 * 删除机构对应的所有维度机构关系
	 * @param orgId
	 */
	public void deleteRelByOrgId(String orgId) {
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("orgId", orgId);
		String hql = " delete from PlasDimeOrgRel t where t.plasOrg.plasOrgId = :orgId";
		this.getDao().batchExecute(hql, values);
	}
	

	/* *********************************************************************************/

	public List<WsPlasOrgRel> getWsAll(){
		return getWsAll(null);
	}
	public List<WsPlasOrgRel> getWsAll(String dimeCd) {

		Map<String,Object> values = new HashMap<String, Object>();
		StringBuffer sb = new StringBuffer() 
				.append(" select ")
				.append(" t.plasDimeOrgRelId, ")
				
				// plasOrgDime
				.append(" t.plasOrgDime.plasOrgDimeId, ")
				.append(" t.plasOrgDime.dimeCd, ")
				.append(" t.plasOrgDime.dimeName, ")

				// plasOrg
				.append(" t.plasOrg.plasOrgId, ")
				.append(" t.plasOrg.orgCd, ")
				.append(" t.plasOrg.orgName, ")
				
				.append(" t.parentId, ")
				.append(" t.treeLevel, ")
				.append(" t.remark ")
				.append(" from PlasDimeOrgRel t order by t.plasOrg.sequenceNo asc, t.plasOrg.orgBizCd asc ");
		
		if(StringUtils.isNotBlank(dimeCd)){
			sb.append(" where plasOrgDime.dimeCd = :dimeCd ");
			values.put("dimeCd", dimeCd);
		}
		
		sb.append(" order by plasOrgDime.dimeCd asc, plasOrg.sequenceNo asc ");
		
		List list = this.getDao().find(sb.toString(),values);
		
		List<WsPlasOrgRel> rtnList = new ArrayList<WsPlasOrgRel>();
		if(list != null){
			Object[] t = null;
			WsPlasOrgRel tmp = null;
			for (int i = 0; i < list.size(); i++) {
				t = (Object[])list.get(i);
				tmp = new WsPlasOrgRel();

				tmp.setPlasDimeOrgRelId((String)t[0]);

				tmp.setDimeId((String)t[1]);
				tmp.setDimeCd((String)t[2]);
				tmp.setDimeName((String)t[3]);

				// plasOrg
				tmp.setOrgId((String)t[4]);
				tmp.setOrgCd((String)t[5]);
				tmp.setOrgName((String)t[6]);
				
				tmp.setParentId((String)t[7]);
				tmp.setTreeLevel((Long)t[8]);
				tmp.setRemark((String)t[9]);
			 
				rtnList.add(tmp);
			}
		}
		return rtnList;
	}
}

