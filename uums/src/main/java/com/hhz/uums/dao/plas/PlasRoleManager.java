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
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasSysPosRoleRel;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.service.impl.ConvertVoUtil;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.Util;
import com.hhz.uums.vo.vw.VoRoleUserRel;
import com.hhz.uums.vo.ws.WsPlasRole;
@SuppressWarnings("unchecked")
@Service
@Transactional
public class PlasRoleManager extends BaseService<PlasRole, String> {
	
	private static Log log = LogFactory.getLog(PlasRoleManager.class);

	@Autowired
	private PlasUserManager plasUserManager;
	@Autowired
	private PlasAcctManager plasAcctManager;
	@Autowired
	private PlasOrgMgrRelManager plasOrgMgrRelManager;
	@Autowired
	private PlasRoleDao plasRoleDao;

	public void savePlasRole(PlasRole plasRole) {
		PowerUtils.setEmptyStr2Null(plasRole);
		plasRoleDao.save(plasRole);
	}

	public void deletePlasRole(String id) {
		plasRoleDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasRole, String> getDao() {
		return plasRoleDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasRole> getAllOrderedRoles() {
		String hql = " from PlasRole t order by t.sequenceNo asc ";
		return getDao().createQuery(hql).list();
	}
	/**
	 * 根据应用业务编号,统一登录用户名,查询应用内的角色列表
	 * 
	 * @param appBizCd
	 * @param uiid
	 * @return
	 */

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<WsPlasRole> getWsUserRoles(String appBizCd, String uiid) {

		PlasUser user = plasUserManager.getPlasUserByUiid(uiid);
		if(user == null)
			return new ArrayList<WsPlasRole>();
			
		StringBuffer sBuffer = new StringBuffer();
		sBuffer .append(" select  distinct")
		        .append(" t3.plasRoleId, ")
		        
		        .append(" t2.plasAppId, ")
		        .append(" t2.appCd, ")
		        .append(" t2.appChnName, ")
		        .append(" t2.appEngName, ")
		        
		        .append(" t3.plasRoleGroup.plasRoleGroupId, ")
		        .append(" t3.plasRoleGroup.roleGroupCd, ")
		        .append(" t3.plasRoleGroup.roleGroupName, ")
		        .append(" t3.plasRoleGroup.sequenceNo, ")
		        
		        .append(" t3.roleCd, ")
		        .append(" t3.roleBizCd, ")
		        .append(" t3.roleName, ")
		        .append(" t3.sequenceNo, ")
		        .append(" t3.remark ")
		        
				.append(" from PlasAcct t,PlasSysPosition t1, PlasApp t2, PlasRole t3, PlasSysPosRoleRel t4 ")
				.append(" where  t.uiid        = :uiid  ")
				.append("   and (t2.appBizCd   = :appBizCd or t2.appBizCd   = :appBizCd2)")
				.append("   and t2.plasAppId   = t3.plasApp.plasAppId ")
				.append("   and  t.plasAcctId  = t1.plasAcct.plasAcctId ")
				.append("   and t1.plasSysPositionId = t4.plasSysPosition.plasSysPositionId ")
				.append("   and ( t3.plasRoleId = t4.plasRole.plasRoleId  ")
				//add by liuzh 2012-02-09 含打包的角色 
				.append(" 	      or t3.plasRoleId in(")
				.append("               select p1.plasRole.plasRoleId ")
				.append("                 from PlasRolePackRel p1, PlasRolePackPosRel p2 ")
				.append("	             where p1.plasRolePack.plasRolePackId = p2.plasRolePack.plasRolePackId ")
				.append("	               and p2.plasSysPosition.plasSysPositionId  = t1.plasSysPositionId ")
				.append("            )")
				.append("        )")
				.append("   and t1.activeBl   = :activeBl ")
				.toString();
		
		String sql = sBuffer.toString();
		log.debug(" 查询用户[" + uiid + "]对应在应用[" + appBizCd + "]的角色列表: " + sql);

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("uiid", uiid);
		values.put("appBizCd", appBizCd);
		values.put("appBizCd2", GlobalConstants.UAAP_BIZ_CD);
		values.put("activeBl", new Boolean(true));
		List roles = find(sql, values);
		if (roles == null){
			roles = new ArrayList<WsPlasRole>();
		}

		List<WsPlasRole>  roleList = new ArrayList<WsPlasRole>();
		Object[] obj = null;
		WsPlasRole tmpRole = null;
		for(int i = 0 ; i<roles.size(); i++){
			obj = (Object[])roles.get(i);
			tmpRole = new WsPlasRole();
			
			tmpRole.setPlasRoleId((String)obj[0]);
			
			tmpRole.setAppId((String)obj[1]);
			tmpRole.setAppCd((String)obj[2]);
			tmpRole.setAppChnName((String)obj[3]);
			tmpRole.setAppEngName((String)obj[4]);
			
			tmpRole.setGroupId((String)obj[5]);
			tmpRole.setGroupCd((String)obj[6]);
			tmpRole.setGroupName((String)obj[7]);

			tmpRole.setGroupSeqNo((Long)obj[8]);
			tmpRole.setRoleCd((String)obj[9]);
			tmpRole.setRoleBizCd((String)obj[10]);
			tmpRole.setRoleName((String)obj[11]);

			tmpRole.setSequenceNo((Long)obj[12]);
			tmpRole.setRemark((String)obj[13]);
			
			roleList.add(tmpRole);
		}
		
//		for (WsPlasRole t : roleList) {
//			System.out.println(t.getRoleCd()+"/"+t.getRoleName());
//		}
		//特殊处理
		List<PlasRole> specialList = getSpecialRoleList(uiid);
		for (PlasRole o : specialList) {
			roleList.add(ConvertVoUtil.transRole(o));
		}
//		for (WsPlasRole t : roleList) {
//			System.out.println(t.getRoleCd()+"/"+t.getRoleName());
//		}
		return roleList;
	}

	//特殊处理
	public List<PlasRole> getSpecialRoleList(String uiid){
		List<PlasRole> roleList = new ArrayList<PlasRole>();
		PlasAcct acct = plasAcctManager.getPlasAcctByUiid(uiid);
		if(acct != null){
			PlasRole role = null;
			
			// 手动追加EAS用户角色
			if(Util.easEnable(acct.getEasFlg())){
				role = this.getPlasRoleByRoleCd(GlobalConstants.A_EAS_USER);
				if(role!= null){
					roleList.add(role);
				}
			}

			//Mysoft用户
			if(Util.mysoftEnable(acct.getMysoftFlg())){
				role = this.getPlasRoleByRoleCd(GlobalConstants.A_MYSOFT_USER);
				if(role!= null){
					roleList.add(role);
				}
			}
			
			// 机构管理员(登录时判断权限用)
			boolean flag = plasOrgMgrRelManager.validateIsOrgMgr(acct.getPlasAcctId());
			if(flag){
				role = this.getPlasRoleByRoleCd(GlobalConstants.A_ADMIN_UAAP_ORG);
				if(role!= null){
					roleList.add(role);
				}else{
					log.error("未找到机构管理员角色信息[A_ADMIN_UAAP_ORG]!");
				}
			}

			
			//若什么角色都没有,自动赋只有一个默认角色
			if(roleList.size() == 0){
				role = getPlasRoleByRoleCd(GlobalConstants.A_COM_LOGIN);
				if(role!= null){
					roleList.add(role);
				}else{
					log.info("自动赋只有一个默认角色信息[A_COM_LOGIN]!");
				}
			}
			
			
			//父子角色关系授权
//			List<String> roleCdList = new ArrayList<String>();
//			for (PlasRole tRole : roleList) {
//				roleCdList.add(tRole.getRoleCd());
//			}
//			if(roleCdList.contains(GlobalConstants.A_HR_NQ) || roleCdList.contains(GlobalConstants.A_HR_BQ) 
//						|| roleCdList.contains(GlobalConstants.A_HR_SY) ||roleCdList.contains(GlobalConstants.A_HR_HY)){
//				if(!roleCdList.contains(GlobalConstants.A_HR_AREA)){
//					role = getPlasRoleByRoleCd(GlobalConstants.A_HR_AREA);
//				}
//			}
		}
		return roleList;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	//角色+构造"机构管理员"/EAS/默认角色
	public List<PlasRole> getUserRoles(String uiid) {

		StringBuffer sBuffer = new StringBuffer();
		sBuffer .append(" select distinct t3 ")
		.append(" from PlasAcct t,PlasSysPosition t1, PlasApp t2, PlasRole t3, PlasSysPosRoleRel t4 ")
		.append(" where  t.uiid       = :uiid  ")
		.append("   and t2.plasAppId  = t3.plasApp.plasAppId ")
		.append("   and  t.plasAcctId = t1.plasAcct.plasAcctId ")
		.append("   and t1.plasSysPositionId = t4.plasSysPosition.plasSysPositionId")
		.append("   and t3.plasRoleId = t4.plasRole.plasRoleId ") 
		.toString();

		String hql = sBuffer.toString();
		log.debug(" 查询用户[" + uiid + "]对应的角色列表: " + hql);
 
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("uiid", uiid);
		List<PlasRole> roleList = find(hql, values);
		if (roleList == null) {
			roleList = new ArrayList<PlasRole>();
		}
		
		//特殊处理
		roleList.addAll(getSpecialRoleList(uiid));
		
		return roleList;
	}
 
	/**
	 * 功能: 查询用户所有角色
	 * 
	 * @param roleCd
	 *            : 用户统一登录名
	 */

	@Transactional(propagation = Propagation.SUPPORTS)
	public PlasRole getPlasRoleByRoleCd(String roleCd) {

		return plasRoleDao.getPlasRoleByRoleCd(roleCd);
	} 

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getPlasRoleNameByRoleCd(String roleCd){
		PlasRole role = getPlasRoleByRoleCd(roleCd);
		if(role == null)
			return roleCd;
		else
			return role.getRoleName();
	}

	/**
	 * 查询系统职位对应的角色关系列表
	 * @param plasUserId
	 * @return
	 */
	public List<PlasSysPosRoleRel> getCurSysPosRoleRelList(String plasSysPosId) {
		
		String hql = " from PlasSysPosRoleRel t where t.plasSysPosition.plasSysPositionId = ? ";
		return getDao().createQuery(hql,plasSysPosId).list();
	}
	public List<String> getRoleIdListByPos(String plasSysPosId) {
		
		String hql = " from PlasSysPosRoleRel t where t.plasSysPosition.plasSysPositionId = ? ";
		return getDao().createQuery(hql,plasSysPosId).list();
	}
	
	/**
	 * 根据菜单Id查找对应的角色
	 * @param appMenuId
	 * @return
	 */
	public List<PlasRole> loadPlasRoleByMenu(String appMenuId){
		StringBuffer hql=new StringBuffer();
		hql.append("select t1 from PlasRole as t1,AppRoleMenuRel as t2 where t1.roleCd=t2.roleCd ");
		hql.append("and t2.appMenu.appMenuId=:appMenuId ");
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("appMenuId", appMenuId);
		List<PlasRole> appRoles = find(hql.toString(), values);
		if (appRoles == null)
			return new ArrayList<PlasRole>();
		else
			return appRoles;
	}
	
	public List<PlasRole>  getAllByFields(){
		String hql = "from PlasRole t order by t.sequenceNo asc, t.roleName asc ";
		return find(hql, new HashMap<String, Object>());
	}
	/**
	 * 
	 * Description:查询以角色组id为key的map，为构建应用角色树使用
	 * author:jiaoxiaofeng  2011-4-21
	 * @return
	 * Map<String,List<PlasRole>>
	 */
	public Map<String,List<PlasRole>> getAlls(){
//		List<PlasRole> list = getAll("roleName",true);
		List<PlasRole> list = getAllByFields();
		Map<String,List<PlasRole>> result = new HashMap<String,List<PlasRole>>();
		List<PlasRole> tmp = null;
		PlasRole tmpObj  =null;
		String parentId =null;
		for(int i=0;i<list.size();i++){
			
			tmpObj = list.get(i);
			parentId = tmpObj.getPlasRoleGroup().getPlasRoleGroupId();
			
			tmp = result.get(parentId);
			if(null!=tmp){
				tmp.add(tmpObj);
			}else{
				tmp = new ArrayList<PlasRole>();
				tmp.add(tmpObj);
			}
			result.put(parentId, tmp);
		}
		return result;
	}

	/* *********************************************************************************/
	public List<WsPlasRole> getWsAll(String appBizCd) {

		String hql = new StringBuffer()
				.append(" select distinct ")
				.append(" t.plasRoleId, ")
				
				// plasApp
				.append(" t.plasApp.plasAppId, ")
				.append(" t.plasApp.appCd, ")
				.append(" t.plasApp.appChnName, ")
				.append(" t.plasApp.appEngName, ")

				// PlasRoleGroup
				.append(" t.plasRoleGroup.plasRoleGroupId, ")
				.append(" t.plasRoleGroup.roleGroupCd, ")
				.append(" t.plasRoleGroup.roleGroupName, ")
				.append(" t.plasRoleGroup.sequenceNo, ")
				
				.append(" t.roleCd, ")
				.append(" t.roleBizCd, ")
				.append(" t.roleName, ")
				.append(" t.sequenceNo, ")
				.append(" t.remark ,")
				.append(" t.sequenceNo ")
				.append(" from PlasRole t")
				.append(" where t.plasApp.appBizCd = :appBizCd or t.plasApp.appBizCd = :appBizCd2 ")
				.append(" order by t.sequenceNo asc")
				.toString();
	
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("appBizCd", appBizCd);
		values.put("appBizCd2", GlobalConstants.UAAP_BIZ_CD);
		
		
		
		List list = this.getDao().find(hql,values);
		
		List<WsPlasRole> rtnList = new ArrayList<WsPlasRole>();
		if(list != null){
			Object[] t = null;
			WsPlasRole tmp = null;
			for (int i = 0; i < list.size(); i++) {
				t = (Object[])list.get(i);
				tmp = new WsPlasRole();

				tmp.setPlasRoleId((String)t[0]);
				 
				// plasApp
				tmp.setAppId((String)t[1]);
				tmp.setAppCd((String)t[2]);
				tmp.setAppChnName((String)t[3]);
				tmp.setAppEngName((String)t[4]);

				// PlasRoleGroup
				tmp.setGroupId((String)t[5]);
				tmp.setGroupCd((String)t[6]);
				tmp.setGroupName((String)t[7]);
				tmp.setGroupSeqNo((Long)t[8]);
				
				tmp.setRoleCd((String)t[9]);
				tmp.setRoleBizCd((String)t[10]);
				tmp.setRoleName((String)t[11]);
				tmp.setSequenceNo((Long)t[12]);
				tmp.setRemark((String)t[13]);
		        
				rtnList.add(tmp);
			}
		}
		return rtnList;
	}

	/**
	 * 删除角色,同时删除角色与职位关系
	 * @param roleId
	 */
	public int deleteWithPosRel(String roleId) {

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("roleId", roleId);
		
		//删除关系
		String hql1 = "delete from PlasSysPosRoleRel t where t.plasRole.plasRoleId = :roleId ";
		int t1 = this.getDao().batchExecute(hql1, values);
		log.debug(" 删除角色与职位关系,影响" + t1);
		
		//删除角色
		String hql2 = "delete from PlasRole t where t.plasRoleId = :roleId ";
		int t2 = this.getDao().batchExecute(hql2, values);
		log.debug(" 删除角色与职位关系,影响" + t2);
		
		return t1;
	}
	
	/**
	 * 是否有权限看到失效对象
	 * @param uiid
	 * @return
	 */
	public boolean validateViewDisableObj(String uiid){
		List<PlasRole> roles = getUserRoles(uiid);
		for (PlasRole role : roles) {
			if(GlobalConstants.A_ADMIN_AUDIT.equals(role.getRoleCd())
					|| GlobalConstants.A_ADMIN_SUPER.equals(role.getRoleCd())
							|| GlobalConstants.A_ADMIN.equals(role.getRoleCd())
			)
				return true;
		}
		
		//A_ADMIN_UAAP_ORG
		
		return false;
	}

	//查询角色列表
	public List<Object> getRoleList() {
		
		//appChnName,groupName,roleCd,roleName
		String sql = new StringBuffer()
						.append("select distinct ")
						.append(" t2.app_chn_name, t3.role_group_name, t.role_cd, t.role_name ")
						.append(" from plas_role t ,plas_app t2, plas_role_group t3 ")
						.append(" where t2.plas_app_id = t.plas_app_id ")
						.append("   and t3.plas_role_group_id = t.plas_role_group_id ")
						.append(" order by t2.app_chn_name asc, t3.role_group_name asc, t.role_cd asc ")
						.toString();
		return this.getDao().findBySql(sql, new HashMap<String, Object>());
	}
	
	/**
	 * 快捷查询角色
	 * @param value (roleName,roleCd)
	 * @param maxNum
	 * @return
	 * @author liuzh  2011-12-13
	 */
	public List<PlasRole> getFindRoleList(String value, String maxNum) {
		if (StringUtils.isBlank(maxNum)) {
			maxNum = "30";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleName", "%" + value + "%");
		map.put("roleCd", "%" + value + "%");
		Page<PlasRole> page = new Page<PlasRole>(new Integer(maxNum));
		StringBuffer hql = new StringBuffer(" from PlasRole t where (lower(t.roleName) like :roleName or lower(t.roleCd) like :roleCd) ");

		hql.append("order by t.sequenceNo desc ");
		page = findPage(page, hql.toString(), map);
		if (page.getResult() == null)
			return new ArrayList<PlasRole>();
		else
			return page.getResult();
	}

	/**
	 * 查询角色与用户关系
	 * @param roleIds
	 * @return
	 */
	public List<VoRoleUserRel> getRoleRelList(String roleIds) {
		
		String[] arr = StringUtils.split(roleIds, ",");
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("roleCdList", arr);
		
//		--PLAS——角色管理——工作计划下，所有角色与其对应的名单（姓名、PD账号、所属中心、部门）
		StringBuffer sb = new StringBuffer()
		.append(" select  tt1.app_chn_name,    ")//0
		.append("         tt2.role_group_name, ")
		.append("         tt.role_name,        ")
		.append("         tt3.org_name,        ")
		.append("         tt.org_name,         ")
		
		.append("         tt.uiid,             ")//5
		.append("         tt.user_name,        ")
		.append("         tt.acct_status_cd,   ")
		.append("         tt1.sequence_no,     ")
		.append("         tt2.sequence_no,     ")
		.append("         tt3.sequence_no,     ")
		
		.append("         tt.org_seq,          ")//10
		.append("         tt.user_seq          ")
		.append(" from                         ")
		.append(" (                            ")
		.append(" select    distinct           ")
		.append("           t2.plas_app_id,    ")
		.append("           t2.plas_role_group_id,     ")
		.append("           t2.plas_role_id,           ")
		.append("           t2.role_name,              ")
		.append("           t5.center_cd,              ")
		.append("           t6.org_name,               ")
		.append("           t6.sequence_no as org_seq, ")
		.append("           t5.uiid,                   ")
		.append("           t5.user_name,              ")
		.append("           t4.status_cd   as acct_status_cd, ")
		.append("           t5.sequence_no as user_seq ") 
		.append("      from plas_sys_pos_role_rel t,   ")
		.append("           plas_role t2,              ")
		.append("           plas_sys_position t3,      ")
		.append("           plas_acct t4,              ")
		.append("           plas_user t5,              ")
		.append("           plas_org t6                ")
		.append("     where t.plas_role_id = t2.plas_role_id                 ")
		.append("       and t.plas_sys_position_id = t3.plas_sys_position_id ")
		.append("       and t3.plas_acct_id = t4.plas_acct_id                ")
		.append("       and t4.plas_user_id = t5.plas_user_id                ")
		.append("       and t5.plas_org_id = t6.plas_org_id                  ")
		.append("       and t2.plas_role_id  in ( :roleCdList )")
		.append(" )tt   ")
		.append(" left join plas_app        tt1 on tt.plas_app_id        = tt1.plas_app_id        ")
		.append(" left join plas_role_group tt2 on tt.plas_role_group_id = tt2.plas_role_group_id ")
		.append(" left join plas_org        tt3 on tt.center_cd          = tt3.org_cd             ")
		.append(" order by ")
		.append(" tt3.org_name,        ")
		.append(" tt.org_name,         ")
//	    .append(" tt1.sequence_no asc, ")
//	    .append(" tt2.sequence_no asc, ")
//	    .append(" tt3.sequence_no asc, ")
	    .append(" tt.org_seq      asc, ")
	    .append(" tt.user_seq     asc  ");

		
		List list = this.getDao().createSQLQuery(sb.toString(), values).list();
		
		List<VoRoleUserRel> rtnList = new ArrayList<VoRoleUserRel>();
		VoRoleUserRel tmpVo = null;
		Object[] t = null;
		for (Object o : list) {
			t = (Object[]) o;
			if(t == null){
				continue;
			}
			
			tmpVo = new VoRoleUserRel();
			
			tmpVo.setAppName((String)t[0]);
			tmpVo.setModuleName((String)t[1]);
			tmpVo.setRoleName((String)t[2]);
			tmpVo.setCenterName((String)t[3]);
			tmpVo.setOrgName((String)t[4]);

			tmpVo.setUiid((String)t[5]);
			tmpVo.setUserName((String)t[6]);
			
			rtnList.add(tmpVo);
		}
		
		return rtnList;
	}
}

