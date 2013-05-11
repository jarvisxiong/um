package com.hhz.uums.dao.plas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasDimeOrgRel;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasOrgMgrRel;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.service.impl.ConvertVoUtil;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.OperConst;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.vo.vw.UserDisplayInfo;
import com.hhz.uums.vo.vw.VoOrg;
import com.hhz.uums.vo.ws.WsPlasOrg;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class PlasOrgManager extends BaseService<PlasOrg, String> {

	private static Log log = LogFactory.getLog(PlasOrgManager.class);

	@Autowired
	private PlasOrgDao plasOrgDao;
	@Autowired
	private PlasOrgMgrRelManager plasOrgMgrRelManager;
	@Autowired
	private PlasOperateLogManager plasOperateLogManager;
	@Autowired
	private PlasRoleManager plasRoleManager;
	//1-是 0-否
	public static String EANBLE_FLG_Y = "1";
	public static String EANBLE_FLG_N = "0";
	
	@Override
	public HibernateDao<PlasOrg, String> getDao() {
		return plasOrgDao;
	}

	@Override
	public List<PlasOrg> getAll(){
		return getAll("sequenceNo", true);
	} 
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasOrg> getAllPlasOrg() {

		List<PlasOrg> list = plasOrgDao.getAll();
		CollectionHelper.sortList(list, "orgBizCd");// 排序
		return list;
	}


	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasOrg> getAllBizOrgs() {

		List<PlasOrg> allOrgs = plasOrgDao.getAll();
		List<PlasOrg> bizOrgs = new ArrayList<PlasOrg>();
		// 若屏蔽管理机构,修改这里
		PlasOrg org = null;
		for (int i = 0; i < allOrgs.size(); i++) {
			org = allOrgs.get(i);
			if (org != null && !DictContants.ORG_ROOT_CD.equals(org.getOrgBizCd())) {
				bizOrgs.add(org);
			}
		}
		CollectionHelper.sortList(bizOrgs, "orgBizCd");// 排序
		return bizOrgs;
	}
	
	/* ********************************************************************* */
	/**
	 * 功能:根据机构业务编号查询机构信息
	 */

	@Transactional(propagation = Propagation.SUPPORTS)
	public PlasOrg getPlasOrgByBizCd(String orgBizCd) {

		List<PlasOrg> result = plasOrgDao.createCriteria(PlasOrg.class)
				.add(Restrictions.eq("orgBizCd", orgBizCd)).list();
		if(result == null || result.size() == 0)
			return null;
		
		return (PlasOrg) result.get(0);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public PlasOrg getPlasOrgByCd(String orgCd) {

		List<PlasOrg> result = plasOrgDao.createCriteria(PlasOrg.class).add(Restrictions.eq("orgCd", orgCd)).list();
		if (result == null || result.size() == 0)
			return null;

		return (PlasOrg) result.get(0);
	}


	@Transactional(propagation = Propagation.SUPPORTS)
	public PlasOrg getPlasOrgById(String orgId) {
  
		String hql = " from PlasOrg t where t.plasOrgId = ?";
		List<PlasOrg> list = this.find(hql, orgId);
		if(list == null || list.size() == 0)
			return null;
		else
			return (PlasOrg)list.get(0);
	}
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void savePlasOrg(PlasOrg plasOrg) {
		PowerUtils.setEmptyStr2Null(plasOrg);
		plasOrgDao.save(plasOrg);
	}
	

	/**
	 * 查询负责机构
	 * @return
	 */

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasOrg> getResponseOrgs(String uiid) {
		return plasOrgDao.createCriteria(PlasOrg.class).add(Restrictions.eq("orgMgrId", uiid)).list();
	}

	/**
	 * 根据机构编号,查询直属员工
	 * @param orgId
	 * @return
	 */

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasUser> getDirectUsersByOrgCd(String orgCd, boolean bAllUserFlg) {
		return getDirectUsers(getPlasOrgByCd(orgCd).getPlasOrgId(), bAllUserFlg);
	}
	public List<PlasUser> getDirectUsers(String orgId, boolean bAllUserFlg) {
		if(bAllUserFlg){
//			所有用户,不分状态
			PlasOrg plasOrg = getEntity(orgId);
			if (plasOrg == null)
				return new ArrayList<PlasUser>();
			return plasOrg.getPlasUsers();
		}else{
			//只查询正常或未知的用户
			Map<String,Object> values = new HashMap<String, Object>();
			values.put("orgId", orgId);
			values.put("noenter", DictContants.PLAS_SERVICE_STATUS_NOENTER);
			values.put("normal", DictContants.PLAS_SERVICE_STATUS_ON);
			String hql = " from PlasUser t where t.plasOrg.plasOrgId = :orgId and (t.serviceStatusCd = :noenter or t.serviceStatusCd = :normal) ";
			return this.getDao().find(hql, values);
		}
	} 
	public List<UserDisplayInfo> getDirectUsers2UserDis(String orgId, boolean bAllUserFlg) {
		StringBuffer hql = new StringBuffer();
		hql.append(" select t.plasOrg.orgCd,t.plasOrg.orgBizCd,t.plasOrg.orgName,")
		.append("	t.uiid,t.userBizCd,t.userCd,t.userName,t.sexCd")
		.append("	from PlasUser t where t.plasOrg.plasOrgId = :orgId ");
		Map<String,Object> values = new HashMap<String, Object>();
		if(!bAllUserFlg){
			//只查询正常或未知的用户
			values.put("noenter", DictContants.PLAS_SERVICE_STATUS_NOENTER);
			values.put("normal", DictContants.PLAS_SERVICE_STATUS_ON);
			hql.append("and (t.serviceStatusCd = :noenter or t.serviceStatusCd = :normal) ");
		}
		values.put("orgId", orgId);
		List list = this.getDao().find(hql.toString(), values);
		List<UserDisplayInfo> result = new ArrayList<UserDisplayInfo>();
		Object[] obj = null;
		UserDisplayInfo info = null;
		for(Object o : list ){
			if(o == null){
				continue;
			}
			obj = (Object[])o;
			info = new UserDisplayInfo();
			info.setOrgCd((String)obj[0]);
			info.setOrgBizCd((String)obj[1]);
			info.setOrgName((String)obj[2]);
			info.setUiid((String)obj[3]);
			info.setUserBizCd((String)obj[4]);
			info.setUserCd((String)obj[5]);
			info.setUserName((String)obj[6]);
			info.setSexCd((String)obj[7]);
			result .add(info);
		}
		return result;
	} 
	/**
	 * 查询系统职位
	 */
	public List<UserDisplayInfo> getDirectPos2PosDis(String orgId, boolean bAllPosFlg) {
		StringBuffer hql = new StringBuffer();
		hql.append(" select t.plasOrg.orgCd,t.plasOrg.orgBizCd,t.plasOrg.orgName,")
		.append("	t.plasAcct.uiid,t.sysPosCd,t.sysPosName")
		.append("	from PlasSysPosition t where t.plasOrg.plasOrgId = :orgId ");
		Map<String,Object> values = new HashMap<String, Object>();
		if(!bAllPosFlg){
			//只查询正常或未知的用户
			values.put("activeBl", true);
			hql.append("and t.activeBl = :activeBl  ");
		}
		values.put("orgId", orgId);
		List list = this.getDao().find(hql.toString(), values);
		List<UserDisplayInfo> result = new ArrayList<UserDisplayInfo>();
		Object[] obj = null;
		UserDisplayInfo info = null;
		for(Object o : list ){
			if(o == null){
				continue;
			}
			obj = (Object[])o;
			info = new UserDisplayInfo();
			info.setOrgCd((String)obj[0]);
			info.setOrgBizCd((String)obj[1]);
			info.setOrgName((String)obj[2]);
			info.setUiid((String)obj[3]);
			info.setSysPosCd((String)obj[4]);
			info.setSysPosName((String)obj[5]);
			result .add(info);
		}
		return result;
	} 
	public List<UserDisplayInfo> getDirectPos2PosDisWithEmpty(String orgId, boolean bAllPosFlg) {
//		StringBuffer hql = new StringBuffer();
//		hql.append(" select t.plasOrg.orgCd,t.plasOrg.orgBizCd,t.plasOrg.orgName,")
//		.append("	t.plasAcct.uiid,t.sysPosCd,t.sysPosName")
//		.append("	from PlasSysPosition t where t.plasOrg.plasOrgId = :orgId ");
//
//		Map<String,Object> values = new HashMap<String, Object>();
//		if(!bAllPosFlg){
//			//只查询正常或未知的用户
//			values.put("activeBl", true);
//			hql.append("and t.activeBl = :activeBl  ");
//		}
//		values.put("orgId", orgId);
//		List list = this.getDao().find(hql.toString(), values);
//		
		

		StringBuffer sb = new StringBuffer()
				.append("select t2.org_cd, t2.org_biz_cd, t2.org_name," )
				.append(" t3.uiid, t.sys_pos_cd, t.sys_pos_name,t.plas_sys_position_id " )
				.append(" from plas_sys_position t" )
				.append(" left join plas_org  t2 on t.plas_org_id = t2.plas_org_id " )
				.append(" left join plas_acct t3 on t.plas_acct_id = t3.plas_acct_id ")
				.append(" where t.plas_org_id = :orgId ");
		

		Map<String,Object> values = new HashMap<String, Object>();
		values.put("orgId", orgId);
		if(!bAllPosFlg){
			//只查询正常或未知的用户
			values.put("activeBl", true);
			sb.append("and t.active_bl = :activeBl  ");
		}
		sb.append(" order by t.sequence_no asc ");

		List list = this.getDao().findBySql(sb.toString(), values);
		List<UserDisplayInfo> result = new ArrayList<UserDisplayInfo>();
		Object[] obj = null;
		UserDisplayInfo info = null;
		for(Object o : list ){
			if(o == null){
				continue;
			}
			obj = (Object[])o;
			info = new UserDisplayInfo();
			info.setOrgCd((String)obj[0]);
			info.setOrgBizCd((String)obj[1]);
			info.setOrgName((String)obj[2]);
			info.setUiid((String)obj[3]);
			info.setSysPosCd((String)obj[4]);
			info.setSysPosName((String)obj[5]);
			info.setSysPosId((String)obj[6]);
			
			if(StringUtils.isBlank(info.getUiid())){
				info.setUiid(info.getSysPosId());
				info.setUserName("[空缺]");
				info.setSysPosName(info.getSysPosName());
			}else{
				info.setSysPosName(info.getSysPosName()+"("+info.getUiid()+")");
				//info.setUserName("["+info.getUiid()+"]");
			}
			
			result .add(info);
		}
		return result;
	} 
	/**
	 * 根据机构编号,查询下属子孙用户
	 */

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasUser> getDescendantUsersByCd(String orgCd) {
		return getDescendantUsers(getPlasOrgByCd(orgCd).getPlasOrgId(), DictContants.TREE_DIME_PHYSICAL, false);
	}
	public List<PlasUser> getDescendantUsers(String orgId) {
		return getDescendantUsers(orgId, DictContants.TREE_DIME_PHYSICAL, false);
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasUser> getDescendantUsers(String orgId,String dimeCd,boolean bAllUserFlg) {

		List<String> orgIdList = new ArrayList<String>();
		orgIdList.add(orgId);
		List<PlasUser> dircUsers = getDirectUsers(orgId, bAllUserFlg);
		List<PlasUser> descUsers = getDescendantUsers(orgIdList, dimeCd, bAllUserFlg);

		List<PlasUser> users = new ArrayList<PlasUser>();
		users.addAll(dircUsers);
		users.addAll(descUsers);

		return users;
	}
	/**
	 * 根据业务机构列表集,查询下属子孙用户
	 * @param orgIdList
	 * @param dimeCd
	 * @param bAllUserFlg true-全部用户 false-在用用户
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasUser> getDescendantUsers(List<String> orgIdList,String dimeCd,boolean bAllUserFlg) {

		List<PlasOrg> orgs = getDescendantOrgs(orgIdList,dimeCd);
		if (orgs == null || orgs.size() == 0)
			return new ArrayList<PlasUser>();

		List<String> rtnList = new ArrayList<String>();
		for (PlasOrg org : orgs) {
			rtnList.add(org.getPlasOrgId());
		}
		return getUserListByOrgIdList(rtnList, dimeCd, bAllUserFlg);
	}
	
	/**
	 * 根据业务机构列表集,查询下属子孙用户
	 * @param orgIdList
	 * @param dimeCd
	 * @param bAllUserFlg true-全部用户 false-在用用户
	 * @return
	 */
	public List<PlasUser> getUserListByOrgIdList(List<String> orgIds,String dimeCd, boolean bAllUserFlg ){
		if (orgIds == null || orgIds.size() == 0)
			return new ArrayList<PlasUser>();

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("dimeCd", dimeCd);
		values.put("filterDictTypeCd", DictContants.PLAS_READ_ONLY_USER);
		
		StringBuffer sb = new StringBuffer()
			.append("select distinct(t)  from PlasUser t, PlasDimeOrgRel t1 ")
			.append(" where t.plasOrg.plasOrgId = t1.plasOrg.plasOrgId ")
			//维度
			.append(" and t1.plasOrgDime.dimeCd =:dimeCd ")
			//过滤用户
			.append(" and t.uiid not in( select t2.dictCd from AppDictData t2 where t2.appDictType.dictTypeCd = :filterDictTypeCd )")
			//机构
			.append(" and (");
		
		String tmpKey = null;
		for (int i = 0; i < orgIds.size(); i++) {
			if (i > 0) {
				sb.append(" or ");
			}
			tmpKey = "orgId" + i;
			sb.append(" t.plasOrg.plasOrgId = :" + tmpKey+" ");// :orgBizCd+i
			values.put(tmpKey, orgIds.get(i));
		}
		sb.append(" ) ");
		
		
		if(bAllUserFlg){
			values.put("noenter", DictContants.PLAS_SERVICE_STATUS_NOENTER);
			values.put("normal", DictContants.PLAS_SERVICE_STATUS_ON);
			sb.append("and (t.serviceStatusCd = :noenter or t.serviceStatusCd = :normal) ");
		}

		sb.append(" order by t.sequenceNo desc ");
		String hql = sb.toString();
		log.debug(" 查询子孙机构 :" + hql);

		List list = find(hql, values);
		if (list == null) {
			list = new ArrayList<PlasUser>();
		}
		return list;
	}
	
	/**
	 * 查询指定的机构IDs 下，存在系统职位对应的人员清单
	 */
	
	public List<PlasUser> getSysUserListByOrgIdList(List<String> orgIds,String dimeCd, boolean bAllUserFlg ){
		if (orgIds == null || orgIds.size() == 0)
			return new ArrayList<PlasUser>();

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("dimeCd", dimeCd);
		values.put("filterDictTypeCd", DictContants.PLAS_READ_ONLY_USER);
		
		StringBuffer sb = new StringBuffer()
			.append("select distinct(t)  from PlasUser t, PlasDimeOrgRel t1, PlasAcct t2, PlasSysPosition t3 ")
			.append(" where t.plasOrg.plasOrgId = t1.plasOrg.plasOrgId ")
			.append("   and t.plasUserId = t2.plasUser.plasUserId ")
			.append("   and t2.plasAcctId = t3.plasAcct.plasAcctId")
			//维度
			.append("   and t1.plasOrgDime.dimeCd =:dimeCd ")
			//过滤“只读用户”
			.append("   and t.uiid not in( select t2.dictCd from AppDictData t2 where t2.appDictType.dictTypeCd = :filterDictTypeCd )")
			//机构 
			.append("   and (");
		
		String tmpKey = null;
		for (int i = 0; i < orgIds.size(); i++) {
			if (i > 0) {
				sb.append(" or ");
			}
			tmpKey = "orgId" + i;
			sb.append(" t.plasOrg.plasOrgId = :" + tmpKey+" ");// :orgBizCd+i
			values.put(tmpKey, orgIds.get(i));
		}
		sb.append(" ) ");
		
		
		if(bAllUserFlg){
			values.put("noenter", DictContants.PLAS_SERVICE_STATUS_NOENTER);
			values.put("normal", DictContants.PLAS_SERVICE_STATUS_ON);
			sb.append("and (t.serviceStatusCd = :noenter or t.serviceStatusCd = :normal) ");
		}

		sb.append(" order by t.sequenceNo desc ");
		String hql = sb.toString();
		log.debug(" 查询子孙机构 :" + hql);

		List list = find(hql, values);
		if (list == null) {
			list = new ArrayList<PlasUser>();
		}
		return list;
	}
	
	
	/**
	 * 根据机构编号集和维度，查询直接下属机构列表（有效机构）
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasOrg> getDirectOrgs(List<String> parentIds,String dimeCd){
		return getDirectOrgs(parentIds, dimeCd,false);
	}
	/**
	 * 根据机构编号,查询下属所有机构
	 * @param parentIdList
	 * @param dimeCd
	 * @param isAllOrgFlg 是否所有机构 true：所有机构，false：有效机构
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasOrg> getDirectOrgs(List<String> parentIdList,String dimeCd,boolean isAllOrgFlg) {

		if (parentIdList == null || parentIdList.size() == 0)
			return new ArrayList<PlasOrg>();
		
		String rootOrgId = TreePanelUtil2.DEFAULT_ROOT_ORG_ID;
		
		StringBuffer hqlBuf = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dimeCd", dimeCd);
		params.put("rootOrgId", rootOrgId);
		
		
		if (parentIdList.contains(rootOrgId)) {
			hqlBuf  .append(" select distinct(t.plasOrg) from PlasDimeOrgRel t ")
					.append("  where t.plasOrgDime.dimeCd = :dimeCd ")
					.append("    and (t.parentId = :rootOrgId or t.parentId is null) ");
		} else {
			hqlBuf  .append(" select distinct(t.plasOrg) " )
					.append("   from PlasDimeOrgRel t " )
					.append("  where t.plasOrgDime.dimeCd = :dimeCd " )
					.append("    and (");
			
			String tmpKey = null;
			String tmpValue = null;
			for (int i = 0; i < parentIdList.size(); i++) {
				if (i > 0) {
					hqlBuf.append(" or ");
				}
				tmpKey = "parentId" + i;
				tmpValue = parentIdList.get(i);
				hqlBuf.append(" t.parentId =:" + tmpKey);
				params.put(tmpKey, tmpValue);
			}
			hqlBuf.append(" )");
		}
		if(!isAllOrgFlg){
			params.put("activeBl", true);
			params.put("visableFlg", true);
			hqlBuf.append(" and t.plasOrg.activeBl = :activeBl");
			hqlBuf.append(" and t.plasOrg.visableFlg = :visableFlg");//add by huangbijin 2012-05-31
		}
		String hql = hqlBuf.toString();
		log.debug(" 查询下属机构 :" + hql);

		List<PlasOrg> list = find(hql, params);

		return list;
	}

	/**
	 * 根据机构列表集和维度，查询下属子孙机构列表（有效机构）
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasOrg> getDescendantOrgs(List<String> orgIds,String dimeCd) {
		return getDescendantOrgs(orgIds, dimeCd,false);
	}
	/**
	 * 根据业务机构列表集,查询下属子孙机构
	 * @param isAllOrgFlg 是否所有机构 true：所有机构，false：有效机构
	 * @return 若无，返回空列表
	 */ 
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasOrg> getDescendantOrgs(List<String> orgIds,String dimeCd,boolean isAllOrgFlg) {
		
		if (orgIds == null || orgIds.size() == 0)
			return new ArrayList<PlasOrg>();
		
		List<PlasOrg> directOrgs = getDirectOrgs(orgIds,dimeCd,isAllOrgFlg);
		List<PlasOrg> orgs = new ArrayList<PlasOrg>();
		List<String> sonOrgIds = new ArrayList<String>();
		
		PlasOrg tOrg = null;
		for (int i = 0; i < directOrgs.size(); i++) {
			tOrg = directOrgs.get(i);
			sonOrgIds.add(tOrg.getPlasOrgId());
			orgs.add(tOrg);
		}
		orgs.addAll(getDescendantOrgs(sonOrgIds,dimeCd));
		return orgs;
	}
	// 取指定机构类型(如中心)的最近上级机构,若无返回null;
	@Transactional(propagation = Propagation.SUPPORTS)
	public PlasOrg getLatestSuperOrg(String superOrgTypeCd, String superOrgId,String dimeTypeCd) {
		if (StringUtils.isBlank(superOrgId) || StringUtils.isBlank(superOrgTypeCd))
			return null;
		else {
			PlasOrg org = this.getEntity(superOrgId);
			if (org == null || StringUtils.isBlank(org.getOrgCd()))
				return null;
			else {
				for (PlasDimeOrgRel rel : org.getPlasDimeOrgRels()) {
					if(dimeTypeCd.equals(rel.getPlasOrgDime().getDimeCd())){
						if (superOrgTypeCd.equals(rel.getPlasOrg().getOrgTypeCd()))// 这里可能报异常,必须将superOrgType置前
							return org;
						else
							return getLatestSuperOrg(superOrgTypeCd, rel.getParentId(), dimeTypeCd);
					}
				}
				return null;
			}
		}
	}
	/**
	 * 
	 * Description:查询用户祖先机构及对应管理员信息
	 */
	public Map<String,String > getBubbleOrgsAndOrgUser(String plasOrgCd,String dimeCd){
		List<PlasOrg> orgs = getBubbleOrgs(plasOrgCd,dimeCd);
		Map<String,String> orgUserMap = new TreeMap<String,String>();
		String orgUsers = "";
		for(PlasOrg org : orgs){
			for(PlasOrgMgrRel e:org.getPlasOrgMgrRels()){
				orgUsers+=e.getPlasSysPosition().getPlasAcct().getCustLoginName();
			}
			orgUserMap.put(orgUserMap.size()+"."+org.getOrgName(), orgUsers);
		}
		return orgUserMap;
	}
	
	/**
	 * 机构名称以/隔开返回
	 * @param orgList
	 * @return
	 */
	public static String getOrgPath(List<PlasOrg> orgList){
		
		
		if(orgList == null || orgList.size() ==0)
			return "";
		
		List<String> list = new ArrayList<String>();
		PlasOrg org = null;
		for(int i=0; i<orgList.size(); i++){
			org = (PlasOrg)orgList.get(i);
			list.add(org.getOrgName()+"(" + org.getOrgCd() + ")");
		}
		
		StringBuffer sb = new StringBuffer();
		for(int i=(list.size()-1); i>=0; i--){
			sb.append(list.get(i));
			if(i>0){
				sb.append("<br/>");
			}
		}
		return sb.toString();
	}
	/**
	 * 机构名称以－隔开返回
	 * @param orgList
	 * @return
	 * @author liuzhihui | 2012-02-07
	 */
	public static String getOrgPath2(List<PlasOrg> orgList){
		if(orgList == null || orgList.size() ==0)
			return "";
		
		List<String> list = new ArrayList<String>();
		PlasOrg org = null;
		for(int i=0; i<orgList.size(); i++){
			org = (PlasOrg)orgList.get(i);
			list.add(org.getOrgName()+"－");
		}
		StringBuffer sb = new StringBuffer();
		for(int i=(list.size()-1); i>=0; i--){
			sb.append(list.get(i));
		}
		String tmpStr = sb.toString();
		String str = "";
		if(tmpStr.endsWith("－")){
			str = "("+tmpStr.substring(0,tmpStr.length()-1)+")";
		}
		return str;
	}
	public String getBubbleOrgNamesPhysical(String plasOrgId){
		StringBuffer sb = new StringBuffer();
		for(PlasOrg org : getBubbleOrgsPhysical(plasOrgId)){
			sb.append(org.getOrgName()).append(" ");
		}
		return sb.toString();
	}
	/**
	 * 
	 * Description:冒泡查询物理上级机构(包括其自身)
	 */
	public List<PlasOrg> getBubbleOrgsPhysical(String plasOrgId) {
		return getBubbleOrgsByOrgId(plasOrgId, DictContants.TREE_DIME_PHYSICAL);
	}

	public List<PlasOrg> getBubbleOrgsLogical(String plasOrgId) {
		return getBubbleOrgsByOrgId(plasOrgId, DictContants.TREE_DIME_LOGICAL);
	}
	
	// 取祖先机构列表,组级向上
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasOrg> getBubbleOrgs(String orgCd,String treeDimeCd) {

		PlasOrg org = this.getPlasOrgByCd(orgCd);
		if(org == null)
			return new ArrayList<PlasOrg>();
		return getBubbleOrgsByOrgId(org.getPlasOrgId(),treeDimeCd);
	} 
	/**
	 * 取祖先机构列表，逐级向上
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasOrg> getBubbleOrgsByOrgId(String orgId,String treeDimeCd) {
		List<PlasOrg> orgList = new ArrayList<PlasOrg>();
		if (StringUtils.isNotBlank(orgId)&&!DictContants.ORG_ROOT_CD.equals(orgId)) {
			PlasOrg parentOrg = this.getEntity(orgId);
		
			if (null != parentOrg ) {
				orgList.add(parentOrg);
				for (PlasDimeOrgRel appRel : parentOrg.getPlasDimeOrgRels()) {
					if(treeDimeCd.equals(appRel.getPlasOrgDime().getDimeCd())){

						orgList.addAll(getBubbleOrgsByOrgId(appRel.getParentId(),treeDimeCd));
					}
				}
			}
		}
		return orgList;
	} 

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PlasOrg getCenterOrgByOrgCd(String orgCd){
		List<PlasOrg> orgList = getBubbleOrgsByOrgId(getPlasOrgByCd(orgCd).getPlasOrgId(),DictContants.TREE_DIME_PHYSICAL);
		for (PlasOrg org : orgList) {
			if(DictContants.PLAS_ORG_TYPE_CENTER.equals(org.getOrgTypeCd()))
				return org;
		}
		return null;
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PlasOrg getCenterOrgByOrgId(String orgId){
		List<PlasOrg> orgList = getBubbleOrgsByOrgId(orgId,DictContants.TREE_DIME_LOGICAL);
		for (PlasOrg org : orgList) {
			if(DictContants.PLAS_ORG_TYPE_CENTER.equals(org.getOrgTypeCd()))
				return org;
		}
		return null;
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getCenterOrgIdByOrgId(String orgId){
		PlasOrg org = getCenterOrgByOrgId(orgId);
		if(org == null)
			return "";
		else
			return org.getPlasOrgId();
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getCenterOrgCdByOrgId(String orgId){
		PlasOrg org = getCenterOrgByOrgId(orgId);
		if(org == null)
			return "";
		else
			return org.getOrgCd();
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getCenterOrgNameByOrgId(String orgId){
		PlasOrg org = getCenterOrgByOrgId(orgId);
		if(org == null)
			return "";
		else
			return org.getOrgName();
	}

	// 同步机构清单
	public List<Object> getEnableOrgs() {

		//plasOrgId
		//orgBizCd
		//orgName
		//parentOrgId
		//parentOrgBizCd
		//parentOrgName
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("physicaltypecd", DictContants.TREE_DIME_PHYSICAL);
		map.put("defaultorgcd",TreePanelUtil2.DEFAULT_ROOT_ORG_ID);
		
		StringBuffer hqlBuf = new StringBuffer()
				.append(" select  tt1.plas_org_id, tt1.org_biz_cd, tt1.org_name, tt1.parent_id,tt2.org_biz_cd,tt2.org_name ")
				.append(" from ( ")
				.append(" 	select t.plas_org_id, t.org_biz_cd, t.org_name, (case t1.parent_id  when null  then :defaultorgcd  else t1.parent_id end) as parent_id ")
				.append(" 	from  plas_org t  ")
				.append(" 	left join plas_dime_org_rel t1  on t1.plas_org_id = t.plas_org_id and t1.plas_org_dime_id in(select tt.plas_org_dime_id from plas_org_dime tt where tt.dime_cd = :physicaltypecd) ")  
				.append(" )  tt1")
				.append(" left join plas_org tt2 on tt2.plas_org_id = tt1.parent_id ");
		
		return this.getDao().findBySql(hqlBuf.toString(), map);
	}
	

	/**
	 * 
	 * Description:模糊查询机构(匹配字段orgBizCd,orgName,orgMgrId,isAllFlg)
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasOrg> getFindOrgList(String value, String dimeTypeCd, String maxNum,String uiid, boolean isAllFlg) {
		
		
		if(StringUtils.isBlank(maxNum)){
			maxNum = "30";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dimeCd",dimeTypeCd);
		map.put("orgBizCd", "%" + value + "%");
		map.put("orgName", "%" + value + "%");
		map.put("orgMgrId", "%" + value + "%");
		Page<PlasOrg> page = new Page<PlasOrg>(new Integer(maxNum));
		StringBuffer hqlBuf = new StringBuffer(" from PlasOrg t where (lower(t.orgBizCd) like :orgBizCd or lower(t.orgName) like :orgName or lower(t.orgMgrId) like :orgMgrId) ");
		if(StringUtils.isNotBlank(dimeTypeCd) && (!isAllFlg)){
			hqlBuf.append(" and t.plasOrgId in (select t2.plasOrg.plasOrgId from PlasDimeOrgRel t2 where t2.plasOrgDime.dimeCd = :dimeCd)");
		}
		
		//获得所管理子孙机构列表
		List<String> mgrOrgs = getMgrOrgIdList(dimeTypeCd,uiid);
		if(null==mgrOrgs){
			hqlBuf.append(" and t.plasOrgId in ()");
		}else if(mgrOrgs.size()==0){
			hqlBuf.append("");
		}else {
			int i = 0;
			hqlBuf.append(" and t.plasOrgId in (");
			for(String s : mgrOrgs){
				if(i>0){
					hqlBuf.append(", :orgId"+i+" ");
				}else{
					hqlBuf.append(" :orgId"+i+" ");
				}
				map.put("orgId"+i, s);
				i++;
			}
			hqlBuf.append(")");
		}
		hqlBuf.append(" order by t.sequenceNo asc ");
		page = this.getDao().findPage(page, hqlBuf.toString(), map);
		if (page.getResult() == null)
			return new ArrayList<PlasOrg>();
		else
			return page.getResult();
	}

	//是失效机构
	public int disableOrg(String orgId){
		
		PlasOrg org = getEntity(orgId);
		
		//失效自己
		org.setActiveBl(new Boolean(false));
		this.savePlasOrg(org);

		//删除机构关联
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId",orgId);
		
		//自己是儿子
		String hql = " delete from PlasDimeOrgRel t where t.plasOrg.plasOrgId = :orgId ";
		int t1 = this.getDao().batchExecute(hql, map);
		
		//自己是父亲
		String hql2 = " delete from PlasDimeOrgRel t where t.parentId = :orgId ";
		int t2 = this.getDao().batchExecute(hql2, map); 
		
		
		//机构与机构管理员关系
		//TODO

		// 保存操作日志
		plasOperateLogManager.savePlasOperateLog(
				SpringSecurityUtils.getCurUiid(),
				SpringSecurityUtils.getCurUserName(), 
				OperConst.ORG, OperConst.DEL, 
				new StringBuffer("[").append(org.getOrgCd()).append(",").append(org.getOrgName()).append("]删除机构成功!").toString());
		
		
		return t1 + t2;
	} 
	
	/* *********************************************************************************/

	/**
	 * 获取特定维度的可用机构列表
	 * @param dimeTypeCd
	 * @return
	 */
	public List<WsPlasOrg> getWsAll() {
		return getWsAll(null,null);
	}
	public List<WsPlasOrg> getWsAll(String dimeTypeCd) {
		return getWsAll(dimeTypeCd, null);
	}
	public List<WsPlasOrg> getWsAll(String dimeTypeCd,String orgCd) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("activeBl", new Boolean(true));
		params.put("dimeCdPhycical", TreePanelUtil2.TREE_TYPE_PHYSICAL);
		params.put("dimeCdLogical", TreePanelUtil2.TREE_TYPE_LOGICAL);
		params.put("orgCd", orgCd);
		 
		StringBuffer hqlBuf = new StringBuffer()
		.append(" select t.plas_org_id, ")	// 0
		.append(" t.org_cd, ")				// 1
		.append(" t.org_biz_cd, ")			// 2
		.append(" t.org_name, ")				// 3       
		.append(" t.short_org_name, ")		// 4     
		.append(" t.phone_desc, ") 			// 5        
		.append(" t.fax_desc, ") 				// 6         
		.append(" t.org_mgr_id, ")  			// 7       
		.append(" t.active_bl, ")   			// 8       
		.append(" t.org_type_cd, ") 			// 9      
		.append(" t.sequence_no, ") 			//10       
		
		.append(" t.creator, ")     			//11       
		.append(" t.created_center_cd, ") 	//12
		.append(" t.created_position_cd, ")	//13 
		.append(" t.created_date, ")			//14        
		.append(" t.updator, ")     			//15        
		.append(" t.updated_center_cd, ")		//16   
		.append(" t.updated_position_cd, ") 	//17
		.append(" t.updated_date, ")			//18        
		.append(" t.record_version, ") 		//19     
		.append(" t.remark, ")     			//20         
		.append(" t.created_dept_cd, ")  		//21   
		.append(" t.updated_dept_cd, ") 	 	//22
		
		.append(" t1.physicalOrgId, t1.physicalOrgCd, t1.physicalOrgBizCd, t1.physicalOrgName, ")//23-26
		.append(" t2.logicalOrgId, t2.logicalOrgCd, t2.logicalOrgBizCd, t2.logicalOrgName, ")//27-30
		
		.append(" t.node_level_cd, t.org_kind_cd, ")//31 32
		.append(" t.org_biz_cd, ")//33
		.append(" t.visable_flg ")//34
		
		.append(" from plas_org t ")
		.append(" left join ( ")
		.append("      select a1.plas_org_id,a2.plas_org_id as physicalOrgId,a2.org_cd as physicalOrgCd ,a2.org_biz_Cd as physicalOrgBizCd,a2.org_name as physicalOrgName ")
		.append("      from plas_dime_org_rel a1,plas_org a2 ")
		.append("      where a1.parent_id = a2.plas_org_id  ")
		.append("        and a1.plas_org_dime_id in ( ")
		.append("            select a3.plas_org_dime_id from plas_org_dime a3 where a3.dime_cd = :dimeCdPhycical ")
		.append("        ) ")
		.append(" )t1 on t1.plas_org_id = t.plas_org_id ")
		.append(" left join ( ")
		.append("      select aa1.plas_org_id,aa2.plas_org_id as logicalOrgId,aa2.org_cd as logicalOrgCd,aa2.org_biz_Cd as logicalOrgBizCd,aa2.org_name as logicalOrgName ")
		.append("      from plas_dime_org_rel aa1,plas_org aa2 ")
		.append("      where aa1.parent_id = aa2.plas_org_id  ")
		.append("        and aa1.plas_org_dime_id in ( ")
		.append("            select aa3.plas_org_dime_id from plas_org_dime aa3 where aa3.dime_cd = :dimeCdLogical ")
		.append("        ) ")
		.append(" )t2 on t2.plas_org_id = t.plas_org_id ");
//		.append(" where t.active_bl = :activeBl ");//不可以在这里过滤，否则PD一些机构中文看不到。
		
		if(StringUtils.isNotBlank(orgCd)){
			hqlBuf.append(" and t.org_cd = :orgCd ");
			
		}
		hqlBuf.append("  order by t.sequence_no asc,t.org_biz_cd asc ")
		.toString();
		
		@SuppressWarnings("rawtypes")
		List list = null;

		list =  findBySql(hqlBuf.toString(), params);
		if(list == null)
			return new ArrayList<WsPlasOrg>();

		List<WsPlasOrg> orgList = new ArrayList<WsPlasOrg>();
		
		WsPlasOrg org = null; 
		
		Object[] o = null;
		BigDecimal tmpVisableFlg = null;
		for (Object obj : list) {
			if(obj == null){
				continue;
			} 
			o = (Object[]) obj;
			
			if(StringUtils.isNotBlank(dimeTypeCd)){
			    if(DictContants.TREE_DIME_PHYSICAL.equals(dimeTypeCd)){
			    	if(StringUtils.isBlank((String)o[23])){
			    		continue;
			    	}
			    }
			    if(DictContants.TREE_DIME_LOGICAL.equals(dimeTypeCd)){
			    	if(StringUtils.isBlank((String)o[27])){
			    		continue;
			    	}
			    }
			}
			
			
			org = new WsPlasOrg(); 
			org.setPlasOrgId((String)o[0]);			//    .append(" select t.plas_org_id, ")  // 0
		    org.setOrgCd((String)o[1]);				//    .append(" org_cd, ")        // 1
		    org.setOrgBizCd((String)o[2]);			//    .append(" org_biz_cd, ")      // 2
		    org.setOrgName((String)o[3]);			//    .append(" org_name, ")        // 3       
		    org.setShortOrgName((String)o[4]);		//    .append(" short_org_name, ")    // 4     
		    org.setPhoneDesc((String)o[5]);			//    .append(" phone_desc, ")       // 5        
		    org.setFaxDesc((String)o[6]);			//    .append(" fax_desc, ")         // 6         
		    org.setOrgMgrId((String)o[7]);			//    .append(" org_mgr_id, ")        // 7       
		    org.setActiveBl(ConvertVoUtil.getBoolean((BigDecimal)o[8]));			//    .append(" active_bl, ")         // 8       
		    org.setOrgTypeCd((String)o[9]);			//    .append(" org_type_cd, ")       // 9      
//		    org.setSequenceNo((Long)o[10]);			//    .append(" sequence_no, ")       //10       
//		    org.setCreator((String)o[11]);			//    .append(" creator, ")           //11       
		    										//    .append(" created_center_cd, ")   //12
		  											//    .append(" created_position_cd, ")  //13 
//		    org.setCreatedDate((Date)o[14]);		//    .append(" created_date, ")      //14        
//		    org.setUpdator((String)o[15]);			//    .append(" updator, ")           //15        
													//    .append(" updated_center_cd, ")    //16   
		    										//	  .append(" updated_position_cd, ")   //17
		    										//    .append(" updated_date, ")      //18        
//		    org.setRecordVersion(((BigDecimal)o[19]).longValue());		//    .append(" record_version, ")     //19     
		    org.setRemark((String)o[20]);			//    .append(" remark, ")           //20         
													//    .append(" created_dept_cd, ")      //21   
													//    .append(" updated_dept_cd, ")      //22 
	
		    org.setPhysicalOrgId((String)o[23]);//    .append(" t1.physicalOrgId, t1.physicalOrgCd, t1.physicalOrgBizCd, t1.physicalOrgName, ")//23-26
		    org.setPhysicalOrgCd((String)o[24]);
		    org.setPhysicalOrgBizCd((String)o[25]);
		    org.setPhysicalOrgName((String)o[26]);  
		    
		    org.setLogicalOrgId((String)o[27]);//    .append(" t2.logicalOrgId, t2.logicalOrgCd, t2.logicalOrgBizCd, t2.logicalOrgName ")//27-30
		    org.setLogicalOrgCd((String)o[28]);
		    org.setLogicalOrgBizCd((String)o[29]);
		    org.setLogicalOrgName((String)o[30]);
		    org.setNodeLevelCd((String)o[31]);
		    org.setOrgKindCd((String)o[32]);
		    
		    tmpVisableFlg = (BigDecimal)o[34];
		    
		    //防止意外,若空，默认可见
		    org.setVisableFlg(tmpVisableFlg == null?new Boolean(true):new Boolean(tmpVisableFlg.intValue() ==1));
			orgList.add(org);
		}
		return orgList;
	}

	public List<VoOrg> getVoOrg(String dimeCd, List orgCds){
		
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("dimeCd", dimeCd);
		values.put("orgCds", orgCds);

		StringBuffer sqlBuf = new StringBuffer()
				.append("select distinct t2.plas_org_id,")
				.append("       t1.parent_id, ")
				.append("       t2.org_name, ")
				.append("       t2.org_cd, ")
				.append("       t2.org_biz_cd, ")
				.append("       t2.sequence_no ")
				.append("  from plas_dime_org_rel t1 ,plas_org_dime m,plas_org t2 ")
				.append(" where t1.plas_org_id = t2.plas_org_id ")
				.append("   and m.dime_cd =:dimeCd  ")
				.append("	and t1.plas_org_dime_id = m.plas_org_dime_id ")
				.append("   and t2.org_cd in (:orgCds) ")
				.append(" order by t2.sequence_no asc ");
		
		List list = this.findBySql(sqlBuf.toString(), values);
		List<VoOrg> result = new ArrayList<VoOrg>();
		VoOrg tmp = null;
		Object[] o = null;
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
			result.add(tmp);
			System.out.println(tmp.getOrgName());
		}
		return result;
	}
	/**
	 * 查询机构管理员管理的机构以及子孙机构列表
	 * @param dimeCd
	 * @param uiid
	 * @param isAllOrgFlg
	 * @param orgIds
	 * @return
	 */
	public List<VoOrg> getVoOrgList(String dimeCd,String uiid,boolean isAllOrgFlg){
		return getVoOrgList(dimeCd, uiid, isAllOrgFlg, null);
	}
	public List<VoOrg> getVoOrgList(String dimeCd,String uiid,boolean isAllOrgFlg, String orgIds){
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("dimeCd", dimeCd);
		values.put("uiid", uiid);
		values.put("activeBl", new Boolean(true));
		
		StringBuffer sqlBuf = new StringBuffer()
				.append("select distinct t2.plas_org_id,")
				.append("       t1.parent_id, ")
				.append("       t2.org_name, ")
				.append("       t2.org_cd, ")
				.append("       t2.org_biz_cd, ")
				.append("       t2.sequence_no ")
				.append("  from plas_dime_org_rel t1 ,plas_org_dime m,plas_org t2, plas_org_mgr_rel t3 " )
				.append(" where t1.plas_org_id = t2.plas_org_id ")
				.append("   and m.dime_cd =:dimeCd  ")
				.append("	and t1.plas_org_dime_id = m.plas_org_dime_id ")
				.append(" 	and t3.plas_org_id = t2.plas_org_id ");
		
		if(StringUtils.isNotBlank(uiid)){
			sqlBuf.append("   and t3.plas_sys_position_id in ( select t4.plas_sys_position_id from plas_sys_position  t4,plas_acct t5  where t4.plas_acct_id = t5.plas_acct_id and t5.uiid =:uiid  )");
		}
				
		if(!isAllOrgFlg){
			sqlBuf.append(" and t2.active_bl = :activeBl ");
		}
		
		//若限定机构
		if(StringUtils.isNotBlank(orgIds)){
			values.put("orgIds", StringUtils.split(orgIds,","));
			sqlBuf.append(" and t2.plas_org_id in( :orgIds ) ");
		}
		
			sqlBuf.append(" order by t2.sequence_no asc ");
			
		List list = this.findBySql(sqlBuf.toString(), values);
		List<VoOrg> result = new ArrayList<VoOrg>();
		VoOrg tmp = null;
		Object[] o = null;
//		List<VoOrg> tmpDesc = null;
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
			result.add(tmp);
		}
		return result;
	}
	/**
	 * 
	 * Description  查询机构管理员管理的机构以及子孙机构列表
	 * return 
	 * 		null：普通用户，无权限
	 * 		List<String>:XXX... ：机构管理员，管理机构列表
	 * 		List<String>:size=0:  应用管理员或者超级管理员，无限制
	 * 
	 */
	public List<PlasOrg> getMgrOrgList(String dimeCd,String uiid){
		
		List<PlasOrg> result = null;
		List<String> tmpOrgIds = null;
		for(PlasRole obj : plasRoleManager.getUserRoles(uiid)){
			if(GlobalConstants.A_ADMIN_UAAP_ORG.equals(obj.getRoleCd())){
				//查询直接管辖机构列表
				result = plasOrgMgrRelManager.getPlasOrgsByUiid(uiid);
				//添加所有子孙机构列表
				tmpOrgIds = transferOrgsToIds(result);
				
				result.addAll(getDescendantOrgs(tmpOrgIds,dimeCd));
			}else  if(GlobalConstants.A_ADMIN.equals(obj.getRoleCd())||GlobalConstants.A_ADMIN_SUPER.equals(obj.getRoleCd())){
				result = new ArrayList<PlasOrg>();
			}
		}
		
		return result;
	}
	public List<String> getMgrOrgIdList(String dimeCd,String uiid){
		return transferOrgsToIds(getMgrOrgList(dimeCd, uiid));
	}
	public static List<String> transferOrgsToIds(List<PlasOrg> orgs){
		List<String> result = new ArrayList<String>();
		for(PlasOrg obj:orgs){
			result.add(obj.getPlasOrgId());
		}
		return result;
	}
	public static List<VoOrg> transferOrgToVoList(List<PlasOrg> orgs,String parentId){
		List<VoOrg> result = new ArrayList<VoOrg>();
		VoOrg tmpObj = new VoOrg();
		for(PlasOrg obj:orgs){
			tmpObj = new VoOrg();
			tmpObj.setOrgId(obj.getPlasOrgId());
			tmpObj.setOrgCd(obj.getOrgCd());
			tmpObj.setOrgBizCd(obj.getOrgBizCd());
			tmpObj.setOrgName(obj.getOrgName());
			tmpObj.setParentOrgId(parentId);
			result.add(tmpObj);
		}
		return result;
	}
	public List<String> getVoWaitingOrgList(String dimeCd,boolean isAllOrg){
		//TODU
		StringBuffer hql = new StringBuffer();
		hql.append("select ")
		.append(" distinct t.plasUser.plasOrg.plasOrgId")
		.append(" from PlasAcct  t ,PlasUser u ,PlasOrg o ")
		.append(" where t.plasUser = u and u.plasOrg = o ")
		.append(" and t.plasAcctId not in (")
		.append("	select t1.plasAcct.plasAcctId from PlasSysPosition t1 where t1.plasAcct.plasAcctId is not null ")
		.append(" 		and t.plasAcctId = t1.plasAcct.plasAcctId)");
		
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("dimeCd", dimeCd);
		if(!isAllOrg) {
			values.put("activeBl", true);//TODU
		} else {
			values.put("activeBl", isAllOrg);
		}
		List list = this.getDao().findBySql(hql.toString(),values );
		List<String> result = new ArrayList<String>();
		Object[] o = null;
		for(Object obj : list){
			o = (Object[]) obj;
			result.add((String)o[0]);
		}
		return result;
	}

	//更新机构顺序
	public void updateOrgSeq(String plasOrgId, String sequenceNo) {
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("sequenceNo", Long.valueOf(sequenceNo));
		values.put("plasOrgId", plasOrgId);
		String hql = " update PlasOrg t set t.sequenceNo = :sequenceNo where t.plasOrgId = :plasOrgId  ";
		this.getDao().batchExecute(hql, values);
	}
	
	/**
	 * <p>组织机构转换VoOrg</p>
	 * @param orgIdLists	组织id集合
	 * @return List<VoOrg> 返回过滤后组织id集合
	 * @author hy 2011/09/20
	 */
	public List<VoOrg> transVoOrgList(List<String> orgIdList){
		StringBuffer hql = new StringBuffer();
		List<VoOrg> rtnList = new ArrayList<VoOrg>();
		Object[] o = null;
		hql.append(" select distinct t.plasOrgId, t1.parentId, t.orgName, t.orgCd, t.orgBizCd, t.activeBl,t.sequenceNo  ")
		.append(" from PlasOrg t ,PlasDimeOrgRel t1 where t.plasOrgId = t1.plasOrg.plasOrgId and t.activeBl = :activeBl ");
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("activeBl", new Boolean(true));
		String tmpKey = null;
		if(orgIdList!=null){
			hql.append(" and ( ");
			for(int i = 0; i < orgIdList.size(); i++){
				hql.append(i>0?" or ":"");
				tmpKey = "orgId" + i;
				hql.append(" t.plasOrgId = :").append(tmpKey).append(" ");
				values.put(tmpKey, orgIdList.get(i));
			}
			hql.append(" ) ");
		}
		hql.append(" order by t.sequenceNo asc ,t.orgBizCd asc");
		
		List list =this.getDao().find(hql.toString(),values);
		
		for(Object obj : list){
			o = (Object[])obj;
			VoOrg voOrg = new VoOrg();
			voOrg.setOrgId((String)o[0]);
			voOrg.setParentOrgId((String)o[1]);
			voOrg.setOrgName((String)o[2]);
			voOrg.setOrgCd((String)o[3]);
			voOrg.setOrgBizCd((String)o[4]);
			voOrg.setActiveBl((Boolean)o[5]);
			voOrg.setSequenceNo((Long)o[6]);
			rtnList.add(voOrg);
		}
				
			
		
		return rtnList==null?new ArrayList<VoOrg>():rtnList;
	}
	
	/**
	 * 根据机构编号,查询下属所有机构（带排序）
	 * @param parentIdList
	 * @param dimeCd
	 * @param isAllOrgFlg 是否所有机构 true：所有机构，false：有效机构
	 * @author hy 2011/09/22
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasOrg> getDirectOrgsOrder(List<String> parentIdList,String dimeCd,boolean isAllOrgFlg) {

		if (parentIdList == null || parentIdList.size() == 0)
			return new ArrayList<PlasOrg>();
		
		String rootOrgId = TreePanelUtil2.DEFAULT_ROOT_ORG_ID;
		
		StringBuffer hqlBuf = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dimeCd", dimeCd);
		params.put("rootOrgId", rootOrgId);
		
		
		if (parentIdList.contains(rootOrgId)) {
			hqlBuf  .append(" select distinct(t.plasOrg) from PlasDimeOrgRel t ")
					.append("  where t.plasOrgDime.dimeCd = :dimeCd ")
					.append("    and (t.parentId = :rootOrgId or t.parentId is null) ");
		} else {
			hqlBuf  .append(" select distinct(t.plasOrg) " )
					.append("   from PlasDimeOrgRel t " )
					.append("  where t.plasOrgDime.dimeCd = :dimeCd " )
					.append("    and (");
			
			String tmpKey = null;
			String tmpValue = null;
			for (int i = 0; i < parentIdList.size(); i++) {
				if (i > 0) {
					hqlBuf.append(" or ");
				}
				tmpKey = "parentId" + i;
				tmpValue = parentIdList.get(i);
				hqlBuf.append(" t.parentId =:" + tmpKey);
				params.put(tmpKey, tmpValue);
			}
			hqlBuf.append(" )");
		}
		if(!isAllOrgFlg){
			params.put("activeBl", true);
			hqlBuf.append(" and t.plasOrg.activeBl = :activeBl");
		}
		hqlBuf.append(" order by t.plasOrg.sequenceNo asc,t.plasOrg.orgBizCd asc ");
		String hql = hqlBuf.toString();
		log.debug(" 查询下属机构 :" + hql);

		List<PlasOrg> list = find(hql, params);

		return list;
	}
	
	
	/**
	 * 查看启用机构
	 * @return
	 */
	public List<PlasOrg> getDisableOrgList(){
		String hql = " from PlasOrg t where t.activeBl <> :activeBl order by t.sequenceNo asc ";
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("activeBl", new Boolean(true));
		List list = this.find(hql, values);
		if(list == null)
			return new ArrayList<PlasOrg>();
		else 
			return list;
	}
}

