package com.hhz.uums.dao.plas;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.dao.app.AppSeqManager;
import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.service.impl.ConvertVoUtil;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.OperConst;
import com.hhz.uums.utils.RoleUtil;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.vo.log.LogPlasUser;
import com.hhz.uums.vo.log.LogUtil;
import com.hhz.uums.vo.vw.UserTreeVo;
import com.hhz.uums.vo.vw.VoUser;
import com.hhz.uums.vo.ws.WsPlasUser;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class PlasUserManager extends BaseService<PlasUser, String> {

	private static Log log = LogFactory.getLog(PlasUserManager.class);

	@Autowired
	private PlasOperateLogManager logManager;

	@Autowired
	private PlasAcctManager plasAcctManager;

	@Autowired
	private PlasUserDao plasOrgDao;

	@Autowired
	private PlasOperateLogManager plasOperateLogManager;

	@Autowired
	private AppSeqManager appSeqManager;

	/**
	 * 查询非离职的用户
	 * 
	 * @return
	 */

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasUser> getAllPlasUser() {
		String hql = " from PlasUser t where t.serviceStatusCd = ? order by sequenceNo desc ";
		return getDao().createQuery(hql, DictContants.PLAS_SERVICE_STATUS_ON).list();
	}

	public List<PlasUser> getPlasUserByOrg(String orgId) {
		String hql = " from PlasUser t where t.plasOrg.plasOrgId = ? ";
		return getDao().createQuery(hql, orgId).list();
	}

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasUser> getAllUsers() {
		String hql = " from PlasUser t order by sequenceNo desc ";
		return getDao().createQuery(hql).list();
	}

	public void savePlasUser(PlasUser plasUser) {
		// 自动生成器
		if(StringUtils.isBlank(plasUser.getUserCd())){
			plasUser.setUserCd(appSeqManager.createNextValue(GlobalConstants.SEQ_USER_CD).toString());
		}
		
		//若空
		if( null == plasUser.getSequenceNo()){
			plasUser.setSequenceNo(new BigDecimal(0));
		}
		PowerUtils.setEmptyStr2Null(plasUser);
		this.getDao().save(plasUser);
	}

	/**
	 * 批量保存
	 * 
	 * @param insertedRecords
	 * @param updatedRecords
	 * @param deletedRecords
	 */
	public void saveOrUpdatePlasUsers(List<PlasUser> insertedRecords, List<PlasUser> updatedRecords,
			List<PlasUser> deletedRecords) {
		for (PlasUser plasUser : insertedRecords) {
			this.savePlasUser(plasUser);
		}
		for (PlasUser plasUser : updatedRecords) {
			this.savePlasUser(plasUser);
		}
		for (PlasUser plasUser : deletedRecords) {
			this.delete(plasUser);
		}

	}

	public PlasUser svPlasUser(PlasUser plasUser) {
		PowerUtils.setEmptyStr2Null(plasUser);
		this.getDao().save(plasUser);
		return plasUser;
	}

	public void deletePlasUser(String id) {
		this.getDao().delete(id);
	}

	@Override
	public HibernateDao<PlasUser, String> getDao() {
		return this.plasOrgDao;
	}

	/**
	 * 查询用户信息
	 * 
	 * @param uiid
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PlasUser getPlasUserByUiid(String uiid) {
		List<PlasUser> result = this.getDao().createCriteria(PlasUser.class).add(Restrictions.eq("uiid", uiid)).list();
		if (result == null || result.size() == 0)
			return null;
		else
			return (PlasUser) result.get(0);
	}

	/**
	 * 查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public PlasUser getPlasUserByPlasUserId(String userId) {
		List<PlasUser> result = this.getDao().createCriteria(PlasUser.class).add(Restrictions.eq("plasUserId", userId))
				.list();
		if (result == null || result.size() == 0)
			return null;
		else
			return (PlasUser) result.get(0);
	}

	/**
	 * 查询用户信息
	 * 
	 * @param userCd
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PlasUser getPlasUserByUserCd(String userCd) {
		List<PlasUser> result = this.getDao().createCriteria(PlasUser.class).add(Restrictions.eq("userCd", userCd))
				.list();
		if (result == null || result.size() == 0)
			return null;
		else
			return (PlasUser) result.get(0);
	}

	/**
	 * 查询用户签名档
	 * 
	 * @param userCd
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getEmailSignContent(String userCd) throws SQLException, IOException {
		PlasUser user = getPlasUserByUserCd(userCd);
		if (user == null)
			return null;
		else
			return user.getEmailSignContent();
	}

	/**
	 * 更新用户签名档
	 * 
	 * @param userId
	 * @param newEmailSignConent
	 * @param operUiid
	 */
	public boolean saveEmailSignContent(String userId, String newEmailSignConent, String operUiid) {

		PlasUser user = getEntity(userId);

		// 日志使用
		String oldSign = String.valueOf(user.getEmailSignContent());

		user.setEmailSignContent(newEmailSignConent);
		savePlasUser(user);

		if (StringUtils.isBlank(oldSign)) {
			oldSign = "";
		}
		if (StringUtils.isBlank(newEmailSignConent)) {
			newEmailSignConent = "";
		}

		String operName = "";
		if (StringUtils.isNotBlank(operUiid)) {
			operName = this.getPlasUserByUiid(operUiid).getUserName();
		} else {
			operUiid = SpringSecurityUtils.getLoginCode();
			operName = SpringSecurityUtils.getCurUserName();
		}
		// 保存操作日志
		if (!newEmailSignConent.equals(oldSign)) {
			logManager.savePlasOperateLog(operUiid, operName, OperConst.USR, OperConst.CHG_SGN, new StringBuffer("[")
					.append(user.getUiid()).append(",").append(user.getUserName()).append("]设置名片成功!").append("")
					.append("->").append(newEmailSignConent).toString());
		}
		return true;
	}

	/**
	 * 模糊查询所有用户列表(仅提供plas端使用)
	 * 
	 * @param uiidOrname
	 * @param maxNum
	 * @param isAdmin
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasUser> getFindUserList(String uiidOrname, String maxNum, List<String> mgrOrgs) {
		if (StringUtils.isBlank(maxNum)) {
			maxNum = "30";
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uiid", "%" + uiidOrname + "%");
		map.put("userName", "%" + uiidOrname + "%");
		Page<PlasUser> page = new Page<PlasUser>(new Integer(maxNum));
		StringBuffer hql = new StringBuffer(" from PlasUser t where (lower(t.uiid) like :uiid or lower(t.userName) like :userName) ");

		boolean isAdmin = false;
		try{
			if(RoleUtil.isAdmin()){
				isAdmin = true; 
			}
		}catch(Exception e){
			log.error("判断是否有管理员用户异常!");
		}
		if(isAdmin){
			//管理员,跳过
		}else{
			// 获得所管理子孙机构列表
			if (null == mgrOrgs) {
				hql.append(" and t.plasOrg.plasOrgId in ()");
			} else if (mgrOrgs.size() == 0) {
				hql.append(" ");
			} else {
				int i = 0;
				hql.append(" and t.plasOrg.plasOrgId in (");
				for (String obj : mgrOrgs) {
					if (i > 0) {
						hql.append(", :orgId" + i + "  ");
					} else {
						hql.append(" :orgId" + i + " ");
					}
					map.put("orgId" + i, obj);
					i++;
				}
				hql.append(")");
			}
		}
		hql.append("order by t.sequenceNo desc ");
		page = findPage(page, hql.toString(), map);
		if (page.getResult() == null)
			return new ArrayList<PlasUser>();
		else
			return page.getResult();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasUser> getFindUserList(String uiidOrname, String maxNum) {
		if (StringUtils.isBlank(maxNum)) {
			maxNum = "30";
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uiid",  uiidOrname);
		map.put("userName",  "%" + uiidOrname +"%" );
		map.put("filterDictTypeCd", DictContants.PLAS_READ_ONLY_USER);
		map.put("activeBl", new Boolean(true));
		map.put("onJob", DictContants.PLAS_SERVICE_STATUS_ON);
		
		Page<PlasUser> page = new Page<PlasUser>(new Integer(maxNum));
		StringBuffer hql = new StringBuffer()
		.append("   from PlasUser t ")
		.append("  where (lower(t.uiid) = :uiid or lower(t.userName) like :userName) ")
		//有效
		.append("    and t.activeBl = :activeBl ")
		//在职
		.append("    and (t.serviceStatusCd =:onJob)  ")
		//过滤用户
		.append("    and t.uiid not in( select t2.dictCd from AppDictData t2 where t2.appDictType.dictTypeCd = :filterDictTypeCd )")
		.append("  order by t.sequenceNo desc ");
		page = findPage(page, hql.toString(), map);
		if (page.getResult() == null)
			return new ArrayList<PlasUser>();
		else
			return page.getResult();
	}
	/**
	 * 查询正常使用的用户摘要信息清单(同步用户到exchange邮箱时用)
	 * 
	 * @return
	 */
	public List<Object> getEnableUsers() {

		// plasUserId
		// uiid
		// userName
		// email
		// palsOrgId
		// orgBizCd
		// orgName

		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userEnable", DictContants.PLAS_SERVICE_STATUS_ON);

		//HQL(不含无机构的用户)
//		String hql = new StringBuffer()
//					.append(" select ")
//					.append(" t.plasUserId, ")
//					.append(" t.uiid, ")
//					.append(" t.userName, ")
//					.append(" t.email, ")
//					.append(" t.plasOrg.plasOrgId, ")
//					.append(" t.plasOrg.orgBizCd, ")
//					.append(" t.plasOrg.orgName ")
//					.append(" from PlasUser t ")
//					.append(" where t.serviceStatusCd = :userEnable ")
//					.append(" order by sequenceNo desc ")
//			.toString();
//		this.getDao().find(hql, map).size();
//		return this.getDao().find(hql, map);

		//SQL(含无机构的用户)
		String sql = new StringBuffer()
					.append(" select  t.plas_user_id,  ")
			        .append("         t.uiid,  ")
			        .append("         t.user_name,  ")
			        .append("         t.email,  ")
			        .append("         t2.plas_org_id,  ")
			        .append("         t2.org_biz_cd,  ")
			        .append("         t2.org_name  ")
			        .append(" from plas_user t  ")
			        .append(" left join plas_org t2 on t.plas_org_id = t2.plas_org_id ")
			        .append(" where t.service_status_cd = :userEnable  ")
			        .append(" order by t.sequence_no desc  ")
					.toString();
//		this.getDao().findBySql(sql, map).size();
		return this.getDao().findBySql(sql, map);
	}

	/**
	 * 查询机构直属用户摘要信息清单(同步用户到exchange邮箱时用)
	 * 
	 * @param plasOrgId
	 * @return
	 */
	public List<Object> getEnableOrgUsers(String plasOrgId) {

		// plasUserId
		// uiid
		// userName
		// email
		// palsOrgId
		// orgBizCd
		// orgName

		String hql = new StringBuffer().append("select ").append(" t.plasUserId, ").append(" t.uiid, ").append(
				" t.userName, ").append(" t.email, ").append(" t.plasOrg.plasOrgId, ").append(" t.plasOrg.orgBizCd, ")
				.append(" t.plasOrg.orgName ").append(" from PlasUser t ").append(
						" where t.plasOrg.plasOrgId = :plasOrgId ").append("   and t.serviceStatusCd = :userEnable ")
				.append(" order by t.sequenceNo desc ").toString();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userEnable", DictContants.PLAS_SERVICE_STATUS_ON);
		map.put("plasOrgId", plasOrgId);

		return this.getDao().find(hql, map);
	}

	/**
	 * 获取用户列表
	 * 
	 * @param positionLevelCd
	 * @return
	 */
	public List<String> getUserByPositionLevel(String positionLevelCd) {

		String hql = " select t.plasUserId from PlasUser t where permissionLevelCd =:positionLevelCd ";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("positionLevelCd", positionLevelCd);
		return getDao().createQuery(hql, map).list();

	}

	/**
	 * author:jiaoxiaofeng 2011-2-16
	 * Descrption：批量修改用户职级信息
	 * 
	 * @param permissionLevelCd
	 * @param newUserIds
	 * @param oldUserIds
	 *            void
	 */
	public void saveBatchGroupUsers(String permissionLevelCd, String newUserIds, String oldUserIds) {

		List<String> arrayAddIds = new ArrayList<String>();
		List<String> arrayDelIds = new ArrayList<String>();
		String[] arraynewIds = StringUtils.split(newUserIds, ",");
		String[] arrayoldIds = StringUtils.split(oldUserIds, ",");
		for (int i = 0; i < arraynewIds.length; i++) {
			String s = arraynewIds[i];
			if (!oldUserIds.endsWith(s)) {
				arrayAddIds.add(s);
			}
		}
		for (int i = 0; i < arrayoldIds.length; i++) {
			String s = arrayoldIds[i];
			if (!newUserIds.endsWith(s)) {
				arrayDelIds.add(s);
			}
		}

		// 新增组员
		PlasUser tmpAdd = null;
		for (int i = 0; i < arrayAddIds.size(); i++) {
			String userId = arrayAddIds.get(i);
			tmpAdd = getPlasUserByPlasUserId(userId);
			tmpAdd.setPermissionLevelCd(permissionLevelCd);
			this.savePlasUser(tmpAdd);
			// sbAdd.append(arrayAddTexts[i]).append("(").append(arrayAddExtParams[i]).append(") ");
		}

		// 收回组员
		PlasUser tmpRomove = null;
		for (int i = 0; i < arrayDelIds.size(); i++) {
			String userId = arrayDelIds.get(i);
			tmpRomove = getPlasUserByPlasUserId(userId);
			tmpRomove.setPermissionLevelCd("");
			this.savePlasUser(tmpRomove);
			// .append(arrayDelTexts[i]).append("(").append(arrayDelExtParams[i]).append(") ");
		}

	}

	/**
	 * 查询所用用户，构建以机构id为key的Map，供加载机构用户树使用(含离职)
	 */
	public Map<String, List<UserTreeVo>> getAlls() {

		String hql = "select t.plasUserId,t.userName,t.plasOrg.plasOrgId,t.serviceStatusCd,t.sexCd,t.uiid "
				+ " from PlasUser t order by t.sequenceNo desc";
		List<Object> allUsers = this.getDao().find(hql, new HashMap<String, Object>());

		Object[] obj = null;
		Map<String, List<UserTreeVo>> userMaps = new TreeMap<String, List<UserTreeVo>>();
		List<UserTreeVo> temp = null;
		UserTreeVo user = null;
		String tmpOrgId = null;
		// 若屏蔽管理机构,修改这里
		for (int i = 0; i < allUsers.size(); i++) {
			obj = (Object[]) allUsers.get(i);
			user = new UserTreeVo();
			user.setPlasUserId((String) obj[0]);
			user.setUserName((String) obj[1]);
			user.setServiceStatusCd((String) obj[3]);
			user.setSexCd((String) obj[4]);
			user.setUiid((String) obj[5]);

			tmpOrgId = (String) obj[2];
			if (tmpOrgId == null) {
				continue;// 有些用户没有机构
			}
			temp = userMaps.get(tmpOrgId);
			if (null != temp) {
				temp.add(user);
			} else {
				temp = new ArrayList<UserTreeVo>();
				temp.add(user);
				userMaps.put(tmpOrgId, temp);
			}

			user.setUserStatusCd((String) obj[3]);

		}
		return userMaps;
	}

	public List<VoUser> getVoUserListEnable() {
		return getVoUserListBy(false, false);
	}

	public List<VoUser> getVoUserListEnable(boolean isPosFlg) {
		return getVoUserListBy(false, isPosFlg);
	}

	public List<VoUser> getVoUserListAll() {
		return getVoUserListBy(true, false);
	}

	public List<VoUser> getVoUserListAll(boolean isPosFlg) {
		return getVoUserListBy(true, isPosFlg);
	}

	/**
	 * 查询用户列表
	 * 
	 * @param isEnable
	 *            true-可用和不可用 false-可用
	 * @param isOnJobOrUnKnown
	 *            用户状态是否为为（在职、未入职）
	 * @return
	 */
	private List<VoUser> getVoUserListBy(boolean isAllFlg, boolean isPosFlg) {
		return getVoUserList2(isAllFlg, false, false, isPosFlg);
	}

	public List<VoUser> getVoUserList(boolean isOnJobOrUnKnown, boolean isLeaveOrFired) {
		return getVoUserList2(true, isOnJobOrUnKnown, isLeaveOrFired, false);
	}

	/**
	 * 查询用户列表
	 * 
	 * @param isAllFlg
	 *            true-可用和不可用 false-可用
	 * @return
	 */

	private List<VoUser> getVoUserList2(boolean isAllFlg, boolean isOnJobOrUnKnown, boolean isLeaveOrFired,
			boolean isPosFlg) {
		// StringBuffer hqlBuf = new StringBuffer()
		// .append("select t.plasUserId, ")
		// .append(" 		t.plasOrg.plasOrgId, ")
		// .append(" 		t.userCd, ")
		// .append(" 		t.userName, ")
		// .append(" 		t.uiid, " )
		// .append(" 		t.serviceStatusCd," )
		// .append(" 		t.sexCd," )
		// .append(" 		t.userBizCd ")
		// .append("  from PlasUser t ");
		//		
		// Map<String,Object> values = new HashMap<String,Object>();
		// hqlBuf.append(" where t.plasUserId!=null  ");
		// if(isOnJobOrUnKnown){
		// hqlBuf.append(" and ( t.serviceStatusCd =:onJob or t.serviceStatusCd =:unKnown)  ");
		// values.put("onJob",DictContants.PLAS_SERVICE_STATUS_ON);
		// values.put("unKnown",DictContants.PLAS_SERVICE_STATUS_NOENTER);
		// }
		// if(isLeaveOrFired){
		// hqlBuf.append(" and ( t.serviceStatusCd =:leave)  ");
		// values.put("leave",DictContants.PLAS_SERVICE_STATUS_OFF);
		// }
		// if(isEnable) {
		// hqlBuf.append(" and t.activeBl = :activeBl ");
		// values.put("activeBl",new Boolean(true));
		// }
		// hqlBuf.append(" order by t.sequenceNo desc ");
		// List<Object> list = this.getDao().find(hqlBuf.toString(), values);

		StringBuffer sqlBuf = new StringBuffer()
				.append("select t.plas_user_id, ")
				.append(" 		t.plas_org_id, ")
				.append(" 		t.user_cd, ")
				.append(" 		t.user_name, ")
				.append(" 		t.uiid, ")
				.append(" 		t.service_status_cd,")
				.append(" 		t.sex_cd,")
				.append(" 		t.user_biz_cd, ")
				.append(" 		t.work_duty_desc, ")
				.append(" 		t.responsibility ");
		if (isPosFlg) {
			sqlBuf.append(" 	,(fn_get_pos_list2field(t.plas_user_id)) ");
		}
		sqlBuf.append("  from plas_user t ");

		Map<String, Object> values = new HashMap<String, Object>();
		sqlBuf.append(" where t.plas_user_id is not null  ");
		if (isOnJobOrUnKnown) {
			sqlBuf.append(" and ( t.service_status_cd =:onJob or t.service_status_cd =:unKnown)  ");
			values.put("onJob", DictContants.PLAS_SERVICE_STATUS_ON);
			values.put("unKnown", DictContants.PLAS_SERVICE_STATUS_NOENTER);
		}
		if (isLeaveOrFired) {
			sqlBuf.append(" and ( t.service_status_cd =:leave)  ");
			values.put("leave", DictContants.PLAS_SERVICE_STATUS_OFF);
		}
		if (isAllFlg) {
			
		}else{
			sqlBuf.append(" and t.active_bl = :activeBl ");
			values.put("activeBl", new Boolean(true));

			sqlBuf.append(" and ( t.service_status_cd =:onJob or t.service_status_cd =:unKnown)  ");
			values.put("onJob", DictContants.PLAS_SERVICE_STATUS_ON);
			values.put("unKnown", DictContants.PLAS_SERVICE_STATUS_NOENTER);
		}
		//2011-12-21 隐藏的帐号不显示
		if(!isAllFlg){
			values.put("filterDictTypeCd", DictContants.PLAS_READ_ONLY_USER);
			sqlBuf.append(" and t.uiid not in(")
				  .append(" 	select tt1.dict_cd ")
				  .append("       from app_dict_data tt1, app_dict_type tt2 ")
				  .append("      where tt2.dict_type_cd = :filterDictTypeCd ")
				  .append("        and tt1.app_dict_type_id = tt2.app_dict_type_id ")
				  .append(" ) ");
		}
		sqlBuf.append(" order by t.sequence_no desc ");

		List<Object> list = this.getDao().findBySql(sqlBuf.toString(), values);
		Object[] obj = null;
		VoUser tmpVo = null;

		List<VoUser> resultList = new ArrayList<VoUser>();
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			if (obj != null) {
				tmpVo = new VoUser();
				tmpVo.setUserId((String) obj[0]);
				tmpVo.setParentOrgId((String) obj[1]);
				tmpVo.setUserCd((String) obj[2]);
				tmpVo.setUserName((String) obj[3]);
				tmpVo.setUiid((String) obj[4]);
				tmpVo.setServiceCd((String) obj[5]);
				tmpVo.setSexCd((String) obj[6]);
				tmpVo.setUserBizCd((String) obj[7]);
				tmpVo.setWorkDutyDesc((String) obj[8]);
				tmpVo.setResponsibility((String) obj[9]);
				if (isPosFlg) {
					tmpVo.setPositionNames((String) obj[10]);
				}

				if (StringUtils.isBlank(tmpVo.getParentOrgId())) {
					tmpVo.setParentOrgId(TreePanelUtil2.DEFAULT_ROOT_ORG_ID);
				}
				resultList.add(tmpVo);
			}
		}
		return resultList;
	}

	/* ******************************************************************************** */
	public List<WsPlasUser> getWsAll() {
		return getWsAll(null);
	}

	public List<WsPlasUser> getWsAll(String uiid) {

		StringBuffer sb = new StringBuffer().append(getSingPreHqlPlasUser());
		Map<String, Object> values = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(uiid)) {
			sb.append(" where t.uiid = :uiid ");
			values.put("uiid", uiid);
		}
		sb.append(" order by t.sequenceNo desc ");

		return getUserList(sb.toString(), values);
	}

	/**
	 * 模糊查询用户列表(仅正常用户)
	 * 
	 * @param uiid
	 * @param userName
	 * @return
	 */
	private String getTrimValue(String val) {
		return StringUtils.isBlank(val) ? "" : val.trim();
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<WsPlasUser> getWsUsersByFilter(WsPlasUser user) {

		String uiid = getTrimValue(user.getUiid());
		String userName = getTrimValue(user.getUserName());

		String email = getTrimValue(user.getEmail());
		String fixedPhone = getTrimValue(user.getFixedPhone());
		String mobilePhone = getTrimValue(user.getMobilePhone());
		String mobilePhone2 = getTrimValue(user.getMobilePhone2());

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("uiid", "%" + uiid + "%");
		values.put("userName", "%" + userName + "%");

		values.put("email", "%" + email + "%");
		values.put("fixedPhone", "%" + fixedPhone + "%");
		values.put("mobilePhone", "%" + mobilePhone + "%");
		values.put("mobilePhone2", "%" + mobilePhone2 + "%");
		values.put("normal", DictContants.PLAS_SERVICE_STATUS_ON);
		values.put("filterDictTypeCd", DictContants.PLAS_READ_ONLY_USER);

		StringBuffer sb = new StringBuffer().append(getSingPreHqlPlasUser()).append(
				" where t.serviceStatusCd = :normal   ").append("   and ( 1!=1 ");

		if (StringUtils.isNotBlank(uiid)) {
			sb.append("   or t.uiid       like :uiid        ");
		}
		if (StringUtils.isNotBlank(userName)) {
			sb.append("   or t.userName   like :userName    ");
		}
		if (StringUtils.isNotBlank(email)) {
			sb.append("   or email 	    like :email  	  ");
		}
		if (StringUtils.isNotBlank(fixedPhone)) {
			sb.append("   or fixedPhone   like :fixedPhone  ");
		}
		if (StringUtils.isNotBlank(mobilePhone)) {
			sb.append("   or mobilePhone  like :mobilePhone ");
		}
		if (StringUtils.isNotBlank(mobilePhone2)) {
			sb.append("   or mobilePhone2 like :mobilePhone2");
		}
		sb
				.append(" ) ")
				// 过滤只读用户
				.append("   and t.uiid not in( select t2.dictCd from AppDictData t2 where t2.appDictType.dictTypeCd = :filterDictTypeCd )")
				.append(" order by t.sequenceNo desc ");

		return getUserList(sb.toString(), values);
	}

	public List<WsPlasUser> getWsUserList(WsPlasUser user, int pageNo, int pageSize) {

		String uiid = user.getUiid();
		String userName = user.getUserName();
		String email = user.getEmail();
		String fixedPhone = user.getFixedPhone();
		String mobilePhone = user.getMobilePhone();
		String mobilePhone2 = user.getMobilePhone2();

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("normal", DictContants.PLAS_SERVICE_STATUS_ON);
		values.put("filterDictTypeCd", DictContants.PLAS_READ_ONLY_USER);

		values.put("uiid", "%" + uiid + "%");
		values.put("userName", "%" + userName + "%");
		values.put("email", "%" + email + "%");
		values.put("fixedPhone", "%" + fixedPhone + "%");
		values.put("mobilePhone", "%" + mobilePhone + "%");
		values.put("mobilePhone2", "%" + mobilePhone2 + "%");

		StringBuffer sb = new StringBuffer().append(" from PlasUser t ").append(
				" where (t.serviceStatusCd = :normal ) ").append("   and ( 1!=1 ");

		if (StringUtils.isNotBlank(uiid)) {
			sb.append("   or t.uiid       like :uiid        ");
		}
		if (StringUtils.isNotBlank(userName)) {
			sb.append("   or t.userName   like :userName    ");
		}
		if (StringUtils.isNotBlank(email)) {
			sb.append("   or email 	    like :email  	  ");
		}
		if (StringUtils.isNotBlank(fixedPhone)) {
			sb.append("   or fixedPhone   like :fixedPhone  ");
		}
		if (StringUtils.isNotBlank(mobilePhone)) {
			sb.append("   or ( mobilePhone  like :mobilePhone and plasOrg.orgName <> '决策层' ) ");
		}
		if (StringUtils.isNotBlank(mobilePhone2)) {
			sb.append("   or mobilePhone2 like :mobilePhone2");
		}
		sb.append("   ) ");

		// 过滤只读用户
		sb
				.append(
						"   and t.uiid not in( select t2.dictCd from AppDictData t2 where t2.appDictType.dictTypeCd = :filterDictTypeCd )")
				.append(" order by t.sequenceNo desc ");

		Page<PlasUser> page = new Page<PlasUser>(pageSize);
		page.setPageNo(pageNo);
		page = this.findPage(page, sb.toString(), values);
		return ConvertVoUtil.transUserList(page.getResult());
	}

	private StringBuffer getSingPreHqlPlasUser() {

		return new StringBuffer()
				.append("select t.plasUserId, ")

				// plasOrg
				.append(" t.plasOrg.plasOrgId, ").append(" t.plasOrg.orgCd, ").append(" t.plasOrg.orgBizCd, ").append(
						" t.plasOrg.orgName, ")

				.append(" t.uiid, ").append(" t.userCd, ").append(" t.userBizCd, ").append(" t.userName, ").append(
						" t.serviceStatusCd, ").append(" t.sexCd, ").append(" t.birthday, ").append(" t.idno, ")
				.append(" t.nationCd, ").append(" t.nativeProvinceDesc, ").append(" t.nativePlaceDesc, ").append(
						" t.marrigeStatusCd, ").append(" t.schoolRecordCd, ").append(" t.gradSchoolDesc, ").append(
						" t.majorDesc, ").append(" t.attendWorkDate, ").append(" t.memberTypeCd, ").append(
						" t.workDutyDesc, ").append(" t.realPosCd, ").append(" t.professionTypeCd, ").append(
						" t.politicsCd, ").append(" t.otherTypeCd, ").append(" t.email, ").append(" t.fixedPhone, ")
				.append(" t.mobilePhone, ").append(" t.mobilePhone2, ").append(" t.idCardTypeCd, ").append(
						" t.specialUserFlg, ").append(" t.userTypeCd, ").append(" t.sourceTypeCd, ").append(
						" t.defaultCredenc, ").append(" t.permissionLevelCd, ").append(" t.emailSignContent, ").append(
						" t.activeBl, ").append(" t.sequenceNo, ").append(" t.remark, ").append(" t.responsibility ")
				.append(" from PlasUser t ");

	}

	private List<WsPlasUser> getUserList(String hql, Map<String, Object> values) {

		List list = this.getDao().find(hql, values);
		List<WsPlasUser> rtnList = new ArrayList<WsPlasUser>();
		if (list != null) {
			Object[] t = null;
			WsPlasUser tmp = null;
			for (int i = 0; i < list.size(); i++) {
				t = (Object[]) list.get(i);
				tmp = new WsPlasUser();

				tmp.setPlasUserId((String) t[0]);

				// plasOrg
				tmp.setOrgId((String) t[1]);
				tmp.setOrgCd((String) t[2]);
				tmp.setOrgBizCd((String) t[3]);
				tmp.setOrgName((String) t[4]);
				// tmp.setcenterOrgCd//plas不回传

				tmp.setUiid((String) t[5]);
				tmp.setUserCd((String) t[6]);
				tmp.setUserBizCd((String) t[7]);
				tmp.setUserName((String) t[8]);
				tmp.setServiceStatusCd((String) t[9]);
				tmp.setSexCd((String) t[10]);
				tmp.setBirthday((Date) t[11]);
				tmp.setIdno((String) t[12]);
				tmp.setNationCd((String) t[13]);
				tmp.setNativeProvinceDesc((String) t[14]);
				tmp.setNativePlaceDesc((String) t[15]);
				tmp.setMarrigeStatusCd((String) t[16]);
				tmp.setSchoolRecordCd((String) t[17]);
				tmp.setGradSchoolDesc((String) t[18]);
				tmp.setMajorDesc((String) t[19]);
				tmp.setAttendWorkDate((Date) t[20]);
				tmp.setMemberTypeCd((String) t[21]);
				tmp.setWorkDutyDesc((String) t[22]);
				tmp.setRealPosCd((String) t[23]);
				tmp.setProfessionTypeCd((String) t[24]);
				tmp.setPoliticsCd((String) t[25]);
				tmp.setOtherTypeCd((String) t[26]);
				tmp.setEmail((String) t[27]);
				tmp.setFixedPhone((String) t[28]);
				tmp.setMobilePhone((String) t[29]);
				tmp.setMobilePhone2((String) t[30]);
				tmp.setIdCardTypeCd((String) t[31]);
				tmp.setSpecialUserFlg((String) t[32]);
				tmp.setUserTypeCd((String) t[33]);
				tmp.setSourceTypeCd((String) t[34]);
				tmp.setDefaultCredenc((String) t[35]);
				tmp.setPermissionLevelCd((String) t[36]);
				// tmp.setEmailSignContent((String)t[37]);
				tmp.setActiveBl((Boolean) t[38]);
				tmp.setSequenceNo((BigDecimal) t[39]);
				tmp.setRemark((String) t[40]);
				tmp.setResponsibility((String) t[41]);

				rtnList.add(tmp);
			}
		}
		return rtnList;
	}

	public boolean updateUser(WsPlasUser user, String operatorId) {

		String userId = user.getPlasUserId();
		if (StringUtils.isBlank(userId))
			// log.error(" 修改用户信息,用户id 为空!");
			return false;

		// 当前操作用户
		PlasUser oper = getPlasUserByUiid(operatorId);// uiid
		if (oper == null)
			// log.error(" 修改用户信息,操作人为空!");
			return false;

		// 新用户信息
		PlasUser updateUser = getEntity(userId);

		// 日志使用,旧用户信息
		LogPlasUser oldUser = LogUtil.transfer(updateUser);

		updateUser.setAttendWorkDate(user.getAttendWorkDate());
		updateUser.setUserName(user.getUserName());
		updateUser.setSexCd(user.getSexCd());
		updateUser.setBirthday(user.getBirthday());//生日
		updateUser.setIdno(user.getIdno());
		updateUser.setNationCd(user.getNationCd());
		updateUser.setNativeProvinceDesc(user.getNativeProvinceDesc());
		updateUser.setNativePlaceDesc(user.getNativePlaceDesc());
		updateUser.setMarrigeStatusCd(user.getMarrigeStatusCd());
		updateUser.setSchoolRecordCd(user.getSchoolRecordCd());
		updateUser.setGradSchoolDesc(user.getGradSchoolDesc());
		updateUser.setMajorDesc(user.getMajorDesc());
		updateUser.setAttendWorkDate(user.getAttendWorkDate());
		updateUser.setMemberTypeCd(user.getMemberTypeCd());
		updateUser.setWorkDutyDesc(user.getWorkDutyDesc());
		updateUser.setProfessionTypeCd(user.getProfessionTypeCd());
		updateUser.setPoliticsCd(user.getPoliticsCd());
		updateUser.setOtherTypeCd(user.getOtherTypeCd());
		updateUser.setEmail(user.getEmail());//邮箱
		updateUser.setFixedPhone(user.getFixedPhone());//固定电话
		updateUser.setMobilePhone(user.getMobilePhone());//移动电话
		updateUser.setMobilePhone2(user.getMobilePhone2());//其他电话
		updateUser.setIdCardTypeCd(user.getIdCardTypeCd());
		updateUser.setSpecialUserFlg(user.getSpecialUserFlg());
		updateUser.setUserTypeCd(user.getUserTypeCd());
		updateUser.setSourceTypeCd(user.getSourceTypeCd());
		updateUser.setRemark(user.getRemark());
		updateUser.setResponsibility(user.getResponsibility());
		savePlasUser(updateUser);

		// 记录操作日志
		String desc = LogUtil.getOperateUser(false, oldUser, updateUser);
		if (StringUtils.isNotBlank(desc)) {
			logManager.savePlasOperateLog(oper.getUiid(), oper.getUserName(), OperConst.USR, OperConst.EDT,
					new StringBuffer("[").append(updateUser.getUiid()).append(",").append(updateUser.getUserName())
							.append("]保存用户信息成功!").append(desc).toString());
		}

		return true;
	}

	/**
	 * 根据角色ID,查询用户列表(仅在职)
	 * 
	 * @param roleId
	 * @return
	 */
	public List<PlasUser> getWsUserList(String roleId) {

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("serviceStatusCd", DictContants.PLAS_SERVICE_STATUS_ON);
		values.put("roleId", roleId);

		String hql = new StringBuffer()
				.append("select distinct(t.plasSysPosition.plasAcct.plasUser) ")
				.append("  from PlasSysPosRoleRel t ")
				.append(" where t.plasRole.plasRoleId = :roleId ")
				.append("   and t.plasSysPosition.plasAcct.plasUser.serviceStatusCd = :serviceStatusCd ")
				.append(" order by t.plasSysPosition.plasAcct.plasUser.sequenceNo desc ").toString();

		List<PlasUser> users = find(hql, values);
		if (users == null)
			return new ArrayList<PlasUser>();
		else
			return users;
	}

	/**
	 * 离职
	 * 
	 * @param userId
	 */
	public void leaveCompany(String userId) {

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("userId", userId);
		values.put("leaveCompany", DictContants.PLAS_SERVICE_STATUS_OFF);
		values.put("closeFlg", DictContants.PLAS_USER_STATUS_CLOSED);
		// 离职
		String hql1 = "update PlasUser t set t.serviceStatusCd = :leaveCompany where t.plasUserId = :userId ";
		this.getDao().batchExecute(hql1, values);

		// 注销
		// String hql2 = "update PlasAcct t set t.statusCd = :closeFlg where t.plasUser.plasUserId = :userId ";
		// this.getDao().batchExecute(hql2, values);

		PlasUser user = getEntity(userId);
		PlasAcct acct = plasAcctManager.getPlasAcctByUiid(user.getUiid());
		if (acct != null) {
			plasAcctManager.acctClose(acct.getPlasAcctId());
		}

		logManager.savePlasOperateLog(SpringSecurityUtils.getCurUiid(), SpringSecurityUtils.getCurUserName(),
				OperConst.USR, OperConst.CLOSE, new StringBuffer().append(" 用户离职操作成功![").append(user.getUiid()).append(
						",").append(user.getUserName()).append("] 设置用户离职完成!").toString());
	}

	/**
	 * 更新上级机构
	 * 
	 * @param orgId
	 * @param userId
	 */
	public void updateParentOrgId(String orgId, String userId) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("orgId", orgId);
		values.put("userId", userId);

		String hql = "update PlasUser t set t.plasOrg.plasOrgId = :orgId where t.plasUserId = :userId ";
		int i = this.getDao().batchExecute(hql, values);
		log.info("更新人员机构ID，影响 :" + i + "行");
	}

	/**
	 * 更新用户机构
	 * 
	 * @param userId
	 * @param orgId
	 * @param orgName
	 * @param posOrgId
	 * @param posOrgName
	 */
	public void moveToOrg(String userId, String orgId, String orgName, String posOrgId, String posOrgName) {
		PlasUser user = getEntity(userId);
		String uiid = user.getUiid();
		String userName = user.getUserName();
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("orgId", posOrgId);
		values.put("userId", userId);

		String hql = " update PlasUser t set t.plasOrg.plasOrgId = :orgId where t.plasUserId = :userId ";
		int t = this.getDao().batchExecute(hql, values);

		plasOperateLogManager.saveUaapOperateLog(SpringSecurityUtils.getCurUiid(),
				SpringSecurityUtils.getCurUserName(), OperConst.USR, "调动职位", new StringBuffer("[").append(uiid).append(
						",").append(userName).append("]").append("归属机构从 ").append(orgName).append(" 调到  ").append(
						posOrgName).append("  ").toString(), "");
	}

	/**
	 * 查询记录数
	 * 
	 * @param values
	 * @return
	 */
	public List<PlasUser> queryResult(Map<String, Object> values) {

		if (values == null || values.keySet().size() == 0)
			return new ArrayList<PlasUser>();

		StringBuffer sb = new StringBuffer("from PlasUser t where t.idno = :idno ");
		if (values.keySet().contains("userId") && StringUtils.isNotBlank((String) values.get("userId"))) {
			sb.append(" and t.plasUserId != :userId ");
		}
		return this.getDao().createQuery(sb.toString(), values).list();
	}

	/**
	 * 定时任务,更新用户中心
	 */
	public String refreshUserCenter() {
		try {
			this.executeFunction("{?=call fn_update_user_center}", new HashMap<Integer, Object>(), String.class);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			log.error("定时任务,更新用户中心异常！", e);
			return e.getMessage();
		}
	}
}
