package com.hhz.uums.dao.plas;

import java.math.BigDecimal;
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
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasSysPosRoleRel;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.OperConst;
import com.hhz.uums.vo.log.ExPlasUserVo;
import com.hhz.uums.vo.vw.OrgTreeVo;
import com.hhz.uums.vo.vw.VoOrg;
import com.hhz.uums.vo.vw.VoRole;
import com.hhz.uums.vo.vw.VoSysPosition;
import com.hhz.uums.vo.ws.WsAcctSysposRel;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class PlasSysPosRoleRelManager extends BaseService<PlasSysPosRoleRel, String> {

	private static Log log = LogFactory.getLog(PlasSysPosRoleRelManager.class);
	@Autowired
	private PlasSysPosRoleRelDao plasUserRoleOrgRelDao;

	@Autowired
	private PlasSysPositionManager sysPositionManager;

	@Autowired
	private PlasRoleManager plasRoleManager;

	@Autowired
	private PlasOrgManager plasOrgManager;

	@Autowired
	private PlasUserManager plasUserManager;
	
	@Autowired
	private PlasOperateLogManager plasOperateLogManager;
	
	@Autowired
	private PlasSysPosRoleRelDao plasSysPosRoleRelDao;

	public void savePlasSysPosRoleRel(PlasSysPosRoleRel plasSysPosRoleRel) {
		PowerUtils.setEmptyStr2Null(plasSysPosRoleRel);
		plasSysPosRoleRelDao.save(plasSysPosRoleRel);
	} 
	
	@Override
	public HibernateDao<PlasSysPosRoleRel, String> getDao() {
		return plasSysPosRoleRelDao;
	}


	/**
	 * 若orgId为空,默认查询全部
	 * 
	 * @param orgId
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasUser> getPlasUserByOrg(String orgId) {
 
		StringBuffer sBuffer = new StringBuffer()
		.append(" select distinct t.plasUserRoleRel.plasUser.plasUserId        , ")
		.append(" 				  t.plasUserRoleRel.plasUser.uiid              , ")
		.append(" 				  t.plasUserRoleRel.plasUser.userCd            , ")
		.append(" 				  t.plasUserRoleRel.plasUser.userName          , ")
		.append(" 				  t.plasUserRoleRel.plasUser.plasOrg.plasOrgId , ")
		.append(" 				  t.plasUserRoleRel.plasUser.plasOrg.orgCd     , ")
		.append(" 				  t.plasUserRoleRel.plasUser.plasOrg.orgBizCd  , ")
		.append(" 				  t.plasUserRoleRel.plasUser.plasOrg.orgName   , ")
		.append(" 				  t.plasUserRoleRel.plasUser.plasPhysicalOrgCd , ")
		.append(" 				  t2.plasOrgId                                 , ")
		.append(" 				  t2.orgName                                   , ")
		.append(" 				  t2.orgBizCd                                  , ")
		.append(" 				  t.plasUserRoleOrgRelId                         ")
		.append(" from PlasSysPosRoleRel t, PlasOrg t2 ")
		.append(" where t.plasUserRoleRel.plasUser.plasPhysicalOrgCd = t2.orgCd");

		if (StringUtils.isNotBlank(orgId)) {
			sBuffer.append(" and t.plasOrg.plasOrgId =  :orgId");
		}

		// Query query = getDao().getSession().createQuery(sBuffer.toString());
		//
		// if (StringUtils.isNotBlank(orgId)) {
		// query.setParameter("orgId", orgId);
		// }
		//
		// List list = query.list();
		Map<String, Object> values = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(orgId)) {
			values.put("orgId", orgId);
		}
		List list = find(sBuffer.toString(), values);
		List<PlasUser> users = new ArrayList<PlasUser>();
		for (int i = 0; i < list.size(); i++) {
			Object[] o = (Object[]) list.get(i);
			
			String plasUserId = (String) o[0];
			String uiid = (String) o[1];
			String userCd = (String) o[2];
			String userName = (String) o[3];
			String plasOrgId = (String) o[4];
			String orgCd = (String) o[5];
			String orgBizCd = (String) o[6];
			String orgName = (String) o[7];
			String plasPhysicalOrgCd = (String) o[8];
			String plasPhysicalOrgId = (String) o[9];
			String plasPhysicalOrgName = (String) o[10];
			String plasPhysicalOrgBizCd = (String) o[11];
			String plasUserRoleOrgRelId = (String) o[12];
			
			ExPlasUserVo user = new ExPlasUserVo();
			user.setPlasUserId(plasUserId);
			user.setUiid(uiid);
//			user.setUserCd(userCd);
			user.setUserName(userName);
//			user.setPlasOrgId(plasOrgId);TODO
			user.setOrgCd(orgCd);
			user.setOrgBizCd(orgBizCd);
			user.setOrgName(orgName);
			//TODO
//			user.setPlasPhysicalOrgCd(plasPhysicalOrgCd);
//			user.setPlasPhysicalOrgId(plasPhysicalOrgId);
//			user.setPlasPhysicalOrgName(plasPhysicalOrgName);
//			user.setPlasPhysicalOrgBizCd(plasPhysicalOrgBizCd);
//			user.setPlasPhysicalOrgBizCd(plasPhysicalOrgBizCd);
			
			PlasOrg plasOrg = new PlasOrg();
			plasOrg.setPlasOrgId(plasOrgId);
			plasOrg.setOrgCd(orgCd);
			plasOrg.setOrgBizCd(orgBizCd);
			plasOrg.setOrgName(orgName);

			// PlasOrg pOrg = new PlasOrg();
			// pOrg.setPlasOrgId(plasPhysicalOrgId);
			// pOrg.setOrgCd(plasPhysicalOrgCd);
			// pOrg.setOrgBizCd(plasPhysicalOrgBizCd);
			// pOrg.setOrgName(plasPhysicalOrgName);

			user.setPlasOrg(plasOrg);
//			user.setPlasSysPosRoleRelId(plasUserRoleOrgRelId);
			
			users.add(user);
		}
		return users;
	}


	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasOrg> getPlasOrgsByUserId(String plasUserId) {
		String hql = " select t.plasOrg from PlasSysPosRoleRel t where t.plasUserRoleRel.plasUser.plasUserId = ? ";
		return getDao().createQuery(hql, plasUserId).list();
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<OrgTreeVo> getOrgTreeVosByUserId(String plasUserId) {
		String hql = " select t.plasOrg.plasOrgId,t.plasOrg.orgCd,t.plasOrg.orgBizCd,t.plasOrg.orgName from PlasSysPosRoleRel t where t.plasUserRoleRel.plasUser.plasUserId = ? ";
		List<OrgTreeVo> result = new ArrayList<OrgTreeVo>();
		List<Object> list = this.getDao().find(hql,plasUserId);
		Object[] obj = null;
		OrgTreeVo tmp = null;
		for(int i = 0 ; i<list.size();i++){
			obj = (Object[]) list.get(i);
			if(obj!=null){
				tmp = new OrgTreeVo();
				tmp.setOrgId((String)obj[0]);
				tmp.setOrgCd((String)obj[1]);
				tmp.setOrgBizCd((String)obj[2]);
				tmp.setOrgName((String)obj[3]);
				result.add(tmp);
			}
		}
		return result;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<VoOrg> getVoOrgsByUserId(String plasUserId) {
		String hql = " select t.plasOrg.plasOrgId,t.plasOrg.orgCd,t.plasOrg.orgBizCd,t.plasOrg.orgName from PlasSysPosRoleRel t where t.plasUserRoleRel.plasUser.plasUserId = ? ";
		List<VoOrg> result = new ArrayList<VoOrg>();
		List<Object> list = this.getDao().find(hql,plasUserId);
		Object[] obj = null;
		VoOrg tmp = null;
		for(int i = 0 ; i<list.size();i++){
			obj = (Object[]) list.get(i);
			if(obj!=null){
				tmp = new VoOrg();
				tmp.setOrgId((String)obj[0]);
				tmp.setOrgCd((String)obj[1]);
				tmp.setOrgBizCd((String)obj[2]);
				tmp.setOrgName((String)obj[3]);
				result.add(tmp);
			}
		}
		return result;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasSysPosRoleRel> getPlasOrgsByUserOrgId(String plasUserId, String plasOrgId) {
		String hql = "from PlasSysPosRoleRel t where t.plasUserRoleRel.plasUser.plasUserId = ? and t.plasOrg.plasOrgId = ?";
		return getDao().createQuery(hql, plasUserId, plasOrgId).list();
	}

	/**
	 * 保存机构管理员
	 */
	public boolean savePlasOrgManagers(String orgId, List<String> newUserIds) {

		// 查询角色对象(固定:机构管理员)
		PlasRole plasRole = plasRoleManager.getPlasRoleByRoleCd(GlobalConstants.A_ADMIN_UAAP_ORG);
		if (plasRole == null) {
			log.warn("未配置机构管理员角色(" + GlobalConstants.A_ADMIN_UAAP_ORG + ")");
			return false;
		}
		String tmpRoleId = plasRole.getPlasRoleId();

		// 查询机构对象
		PlasOrg plasOrg = plasOrgManager.getEntity(orgId);
		
		// 日志使用
		StringBuffer sb = new StringBuffer();
		
		// 查询用户对象
		for (int i = 0; i < newUserIds.size(); i++) {
			String tmpUserId = newUserIds.get(i);

			// 保存用户角色关系
			PlasSysPosRoleRel plasUserRoleRel = null;//TODO:plasUserRoleRelManager.getPlasSysPosRoleRelByUserRoleId(tmpUserId,tmpRoleId);
			if (plasUserRoleRel == null) {
				plasUserRoleRel = new PlasSysPosRoleRel();
				PlasUser plasUser = plasUserManager.getEntity(tmpUserId);
				plasUserRoleRel.setPlasRole(plasRole);
//				plasUserRoleRel.setPlasUser(plasUser);//TODO
//todo
				plasUserRoleOrgRelDao.save(plasUserRoleRel);
				
				sb.append(plasUser.getUserName()).append("(").append(plasUser.getUiid()).append("),");
			}
			
			// 保存用户角色机构关系
			PlasSysPosRoleRel rel = new PlasSysPosRoleRel();
//			rel.setPlasOrg(plasOrg);//MODIFIED:TODO
//			rel.setPlasSysPosRoleRel(plasUserRoleRel);
			this.savePlasSysPosRoleRel(rel);
		}
		
		// 操作日志
		if(newUserIds != null && newUserIds.size() > 0){
			plasOperateLogManager.savePlasOperateLog(
					SpringSecurityUtils.getLoginCode(),
					SpringSecurityUtils.getCurUserName(),
					OperConst.AUTH, OperConst.ADD, new StringBuffer("[").append(
					plasOrg.getOrgCd()).append(",").append(plasOrg.getOrgName()).append("]授权机构管理员成功! 分别是:").append(sb)
					.toString()
			);
		}
		return true;
	}


	/**
	 * Update PlasSysPosRoleRel 没有plasUserRoleRel这个属性，
	 * 	from PlasSysPosRoleRel t where t.plasUserRoleRel.plasUser.plasUserId = ? and
	 * 	to
	 * 	from PlasSysPosRoleRel t where 
	 */
	public List<PlasSysPosRoleRel> getCurSysPosRoleRelList(String plasRealPosId) {
		String hql = "from PlasSysPosRoleRel t where  t.plasSysPosition.plasSysPositionId = ?";
		return getDao().createQuery(hql, plasRealPosId).list();
	}
	/**
	 *  根据系统职位的plasSysPositionId 和角色的plasRoleId
	 */
	public List<PlasSysPosRoleRel> getSysPosRoleRelByPosAndRole(String sysPosId,String roleId){
		String hql = "from PlasSysPosRoleRel t where t.plasSysPosition.plasSysPositionId = ? and t.plasRole.plasRoleId=?";
		return getDao().createQuery(hql, sysPosId,roleId).list();
	}
	
	
	/**
	 * 删除角色与职位关系
	 * @param positionId
	 * @param roleId
	 * @return
	 */
	public boolean deleteSysPosRoleRelById(String positionId, String roleId){
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("positionId", positionId);
		values.put("roleId", roleId);
		String hql = "delete from PlasSysPosRoleRel t where t.plasSysPosition.plasSysPositionId = :positionId and t.plasRole.plasRoleId = :roleId ";
		getDao().batchExecute(hql, values);
		return true;
	}
	
	//注意：修改过,只取有效职位
	public List<PlasSysPosition> getSysPostionByRole(String roleId){
		
		String hql = "from PlasSysPosRoleRel t where t.plasRole.plasRoleId = ? and t.plasSysPosition.activeBl = ? ";
		List<PlasSysPosition> sysPosList = new ArrayList<PlasSysPosition>();
		
		List<PlasSysPosRoleRel> result = getDao().createQuery(hql, roleId, new Boolean(true)).list();
		for(PlasSysPosRoleRel p :result){
			sysPosList.add(p.getPlasSysPosition());
		}
		return sysPosList;
	}
	/**
	 * 查询某角色对应的职位列表
	 * @param roleId
	 * @return
	 */
	public List<VoSysPosition> getVoSysPostionByRole(String roleId){
 
		String hql = new StringBuffer()
			.append(" select t.plas_sys_position_id,")
			.append("  t.plas_acct_id,  " )
			.append("  t.plas_org_id,  " )
			.append("  t2.org_cd,  " )
			.append("  t2.org_name,  " )
			.append("  t.sys_pos_cd,  " )
			.append("  t.sys_pos_name,  " )
			.append("  t.short_name,   " )
			.append("  t.active_bl, " )
			.append("  t1.uiid, " )
			.append("  t1.user_name, " )
			.append("  t1.status_cd   " )
			.append("  from (select t.* from plas_sys_position t where t.plas_sys_position_id in (select a.plas_sys_position_id from plas_sys_pos_role_rel a where a.plas_role_id = :roleId)) t ")
			.append("  left join (select tt1.plas_acct_id,tt1.uiid,tt2.user_name,tt1.status_cd from plas_acct tt1,plas_user tt2 where tt1.plas_user_id = tt2.plas_user_id) t1 on t1.plas_acct_id = t.plas_acct_id ")
			.append("  left join  plas_org t2 on t2.plas_org_id  = t.plas_org_id ")
		.toString();

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleId",roleId);
		
		List result = this.getDao().findBySql(hql,map);
		List<VoSysPosition> rtnList = new ArrayList<VoSysPosition>();
		if( result != null){
			Object[] t = null;
			VoSysPosition tmp = null;
			for (int i = 0; i < result.size(); i++) {
				t = (Object[])result.get(i);
				tmp = new VoSysPosition();
				tmp.setPlasSysPositionId((String)t[0]);
				tmp.setAcctId((String)t[1]);
				tmp.setOrgId((String)t[2]);
				tmp.setOrgCd((String)t[3]);
				tmp.setOrgName((String)t[4]);
				tmp.setSysPosCd((String)t[5]);
				tmp.setSysPosName((String)t[6]);
				tmp.setShortName((String)t[7]);
				tmp.setActiveBl(((BigDecimal)t[8])==null?new Boolean(false): new Boolean(true));
				tmp.setUiid((String)t[9]);
				tmp.setUserName(StringUtils.isBlank((String)t[10])?"":((String)t[10]));
				tmp.setAcctStatusCd((String)t[11]);//账号状态
				
				rtnList.add(tmp);
			}
		}
		return rtnList;
	}
	/**
	 * 查询某角色对应的职位列表
	 * @param roleId
	 * @return
	 */
	public List<VoSysPosition> getVoSysPostionByNodeCd(String nodeCd){
		
		String hql = new StringBuffer()
		.append(" select t.plas_sys_position_id,")
		.append("  t.plas_acct_id,  " )
		.append("  t.plas_org_id,  " )
		.append("  t2.org_cd,  " )
		.append("  t2.org_name,  " )
		.append("  t.sys_pos_cd,  " )
		.append("  t.sys_pos_name,  " )
		.append("  t.short_name,   " )
		.append("  t.active_bl, " )
		.append("  t1.uiid, " )
		.append("  t1.user_name, " )
		.append("  t1.status_cd   " )
		.append("  from (select t.* from plas_sys_position t where t.plas_sys_position_id in (select a.plas_sys_position_id from PLAS_NODE_SYS_POS_REL a where a.node_cd = :nodeCd)) t ")
		.append("  left join (select tt1.plas_acct_id,tt1.uiid,tt2.user_name,tt1.status_cd from plas_acct tt1,plas_user tt2 where tt1.plas_user_id = tt2.plas_user_id) t1 on t1.plas_acct_id = t.plas_acct_id ")
		.append("  left join  plas_org t2 on t2.plas_org_id  = t.plas_org_id ")
		.toString();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("nodeCd",nodeCd);
		
		List result = this.getDao().findBySql(hql,map);
		List<VoSysPosition> rtnList = new ArrayList<VoSysPosition>();
		if( result != null){
			Object[] t = null;
			VoSysPosition tmp = null;
			for (int i = 0; i < result.size(); i++) {
				t = (Object[])result.get(i);
				tmp = new VoSysPosition();
				tmp.setPlasSysPositionId((String)t[0]);
				tmp.setAcctId((String)t[1]);
				tmp.setOrgId((String)t[2]);
				tmp.setOrgCd((String)t[3]);
				tmp.setOrgName((String)t[4]);
				tmp.setSysPosCd((String)t[5]);
				tmp.setSysPosName((String)t[6]);
				tmp.setShortName((String)t[7]);
				tmp.setActiveBl(((BigDecimal)t[8])==null?new Boolean(false): new Boolean(true));
				tmp.setUiid((String)t[9]);
				tmp.setUserName(StringUtils.isBlank((String)t[10])?"":((String)t[10]));
				tmp.setAcctStatusCd((String)t[11]);//账号状态
				
				rtnList.add(tmp);
			}
		}
		return rtnList;
	}

	/**
	 * 保存配置角色与系统职位关系
	 * @param roleId
	 * @param addRoleIds
	 * @param delRoleIds
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public void saveBatchRoleSysPosRel(String roleId,String addRoleIds, String delRoleIds) {
		
		String[] addArray = addRoleIds.split(",");
		String[] delArray = delRoleIds.split(",");

		// 保存操作日志
		String operUiid = SpringSecurityUtils.getLoginCode();
		String operUserName = SpringSecurityUtils.getCurUserName();

		PlasRole role = plasRoleManager.getEntity(roleId);
		
		// 授权系统职位与角色的对应关系
		if (StringUtils.isNotBlank(addRoleIds)) {
			StringBuffer sysPosBuf = new StringBuffer();
			PlasSysPosition tmpSysPos = null;
			PlasSysPosRoleRel tmpRel = null;
			
			for (int i = 0; i < addArray.length; i++) {
				if(!isExists(addArray[i], roleId)){
					tmpSysPos = sysPositionManager.getEntity(addArray[i]);
					tmpRel = new PlasSysPosRoleRel();
					tmpRel.setPlasSysPosition(tmpSysPos);
					tmpRel.setPlasRole(role);
					savePlasSysPosRoleRel(tmpRel);
					sysPosBuf.append(tmpSysPos.getSysPosCd()).append("(").append(tmpSysPos.getSysPosName()).append(")");
				}
			}
			
			plasOperateLogManager.savePlasOperateLog(
					operUiid,
					operUserName,
					OperConst.ROLE,
					OperConst.ADD,
					new StringBuffer("[").append(role.getRoleCd()).append(",").append(role.getRoleName()).append("]")
					.append("授权角色的系统职位如下:").append(sysPosBuf).toString());
		}
		// 收回系统职位和角色的对应关系
		if (StringUtils.isNotBlank(delRoleIds)) {
			PlasSysPosition tmpPos = null;
			StringBuffer sysPosBuf = new StringBuffer();
			for (int i = 0; i < delArray.length; i++) {
				tmpPos = sysPositionManager.getEntity(delArray[i]);
				sysPosBuf.append(tmpPos.getSysPosCd()).append("(").append(tmpPos.getSysPosName()).append(")");
				deleteSysPosRoleRelById(delArray[i], roleId);
			}
			
			plasOperateLogManager.savePlasOperateLog(
					operUiid,
					operUserName,
					OperConst.ROLE,
					OperConst.DEL,
					new StringBuffer("[").append(role.getRoleCd()).append(",").append(role.getRoleName()).append("]")
							.append("收回角色的系统职位如下:").append(sysPosBuf).toString());
		}
	}
	
	/**
	 * 校验是否存在
	 * @param plasSysPosId
	 * @param plasRoleId
	 * @return
	 */
	private boolean isExists(String plasSysPosId, String plasRoleId){

		if(StringUtils.isBlank(plasSysPosId) || StringUtils.isBlank(plasRoleId))
			return true;
		
		String hql = " from PlasSysPosRoleRel t where t.plasSysPosition.plasSysPositionId = ? and t.plasRole.plasRoleId = ? ";
		List list = this.getDao().createQuery(hql, plasSysPosId, plasRoleId).list();
		if(list == null || list.size() == 0 )
			return false;
		else
			return true;
	}
	

	/**
	 * 授权某系统职位拥有多个角色
	 * @param sysPositionId
	 * @param addRoleIds
	 * @param delRoleIds
	 */
	public void saveBatch(String sysPositionId, String addRoleIds, String delRoleIds){
		
		String[] addArray = addRoleIds.split(",");
		String[] delArray = delRoleIds.split(",");
		
		// 保存操作日志
		String operUiid = SpringSecurityUtils.getLoginCode();
		String operUserName = SpringSecurityUtils.getCurUserName();
		
		PlasSysPosition sysPos = sysPositionManager.getEntity(sysPositionId);
		
		//配置系统职位与角色的对应关系
		PlasRole role = null;
		if(StringUtils.isNotBlank(addRoleIds)){
			StringBuffer roleBuf = new StringBuffer();
			PlasSysPosRoleRel plasSysPosRoleRel = null;
			for(int i = 0;i<addArray.length; i++){
				role = plasRoleManager.getEntity(addArray[i]);
				plasSysPosRoleRel = new PlasSysPosRoleRel();
				plasSysPosRoleRel.setPlasSysPosition(sysPos);
				plasSysPosRoleRel.setPlasRole(role);
				savePlasSysPosRoleRel(plasSysPosRoleRel);
				roleBuf.append(role.getRoleCd()).append(",").append(role.getRoleName());
			
			}
			plasOperateLogManager.savePlasOperateLog(operUiid, operUserName, OperConst.SYSPOS, OperConst.ADD,
					new StringBuffer("[").append(sysPos.getSysPosCd()).append(",").append(sysPos.getSysPosName()).append(
					"]授权系统职位的角色如下：").append(roleBuf).toString());
		}
		//收回系统职位和角色的对应关系
			if(StringUtils.isNotBlank(delRoleIds)){
				StringBuffer roleBuf = new StringBuffer();
				for(int i = 0;i<delArray.length; i++){
					role = plasRoleManager.getEntity(delArray[i]);
					deleteSysPosRoleRelById(sysPositionId,delArray[i]);
					roleBuf.append(role.getRoleCd()).append(",").append(role.getRoleName());
				}
				plasOperateLogManager.savePlasOperateLog(operUiid, operUserName, OperConst.SYSPOS, OperConst.ADD,
						new StringBuffer("[").append(sysPos.getSysPosCd()).append(",").append(sysPos.getSysPosName()).append(
						"]解除系统职位的角色如下：").append(roleBuf).toString());
			}
	}
	
	/* *********************************************************************************/
	public List<WsAcctSysposRel> getWsAll(String appBizCd) {

		String hql = new StringBuffer()
				.append(" select distinct ")
				.append(" t.plasSysPosition.plasAcct.plasAcctId,")
				.append(" t.plasSysPosition.plasAcct.uiid,")
				.append(" t.plasSysPosition.plasSysPositionId, ")
				.append(" t.plasSysPosition.sysPosCd, ")
				.append(" t.plasSysPosition.sysPosName, ")
				.append(" t.plasSysPosition.shortName, ")
				.append(" t.plasSysPosition.sequenceNo ")
				.append(" from PlasSysPosRoleRel t ") 
				.append(" where t.plasRole.plasApp.appBizCd = :appBizCd ")
				.append(" order by t.plasSysPosition.sequenceNo asc")
				.toString();
		
		Map<String, Object> values = new HashMap<String,Object>();
		values.put("appBizCd", appBizCd);
		List list = this.getDao().find(hql,values);
		log.info("PlasSysPosRoleRelManager.getWsAll("+appBizCd+"):");
		
		List<WsAcctSysposRel> rtnList = new ArrayList<WsAcctSysposRel>();
		if(list != null){
			Object[] t = null;
			WsAcctSysposRel tmp = null;
			for (int i = 0; i < list.size(); i++) {
				t = (Object[])list.get(i);
				tmp = new WsAcctSysposRel();

				tmp.setAcctId((String)t[0]);
				tmp.setUiid((String)t[1]);
				tmp.setSysPosId((String)t[2]);
				tmp.setSysPosCd((String)t[3]);
				tmp.setSysPosName((String)t[4]);
				tmp.setShortName((String)t[5]);
		        
				rtnList.add(tmp);
			}
		}
		return rtnList;
	}

	/**
	 * 查询系统职位持有的角色列表
	 * @param plasSysPosId
	 * @return
	 */
	public List<PlasRole> getRoleListByPosId(String plasSysPosId) {

		if(StringUtils.isBlank(plasSysPosId))
			return new ArrayList<PlasRole>();
		
		String hql = new StringBuffer()
				.append(" select distinct t2,t2.plasApp.sequenceNo, t3.plasRoleGroupId,t2.sequenceNo " )
				.append("  from PlasSysPosRoleRel t,PlasRole t2, PlasRoleGroup t3 " )
				.append(" where t.plasSysPosition.plasSysPositionId = :plasSysPosId " )
				.append("   and t.plasRole.plasRoleId = t2.plasRoleId " )
				.append("   and t2.plasRoleGroup.plasRoleGroupId = t3.plasRoleGroupId " )
//				.append("   and t.plasSysPosition.activeBl = :activeBl ")
				.append(" order by t2.plasApp.sequenceNo asc, t3.plasRoleGroupId asc, t2.sequenceNo asc")
				.toString();
		
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("activeBl", new Boolean(true));
		values.put("plasSysPosId", plasSysPosId);
		
		List list = this.getDao().createQuery(hql, values).list();
		
		List<PlasRole> rtnList = new ArrayList<PlasRole>();
		if(list != null){
			Object[] t = null;
			for (int i = 0; i < list.size(); i++) {
				t = (Object[])list.get(i);
				rtnList.add((PlasRole)t[0]);
			}
		}
		return rtnList;
	}
	
	/**
	 * 查询系统职位所有的角色列表(包括打包角色)
	 * @param plasSysPosId
	 * @return
	 */
	public List<VoRole> getAllRoleListByPosId(String plasSysPosId,boolean isAll) {
		if(StringUtils.isBlank(plasSysPosId))
			return new ArrayList<VoRole>();
		StringBuffer sql = new StringBuffer();
	    if(isAll){//所有（包含角色包下的角色）
	    	sql.append("select t.roleName,t.appName,t.groupRoleName,t.sequenceNo,t.plasRoleGroupId,t.sequenceNo2");
	    	sql.append(" from (select distinct p1.role_name as roleName,p3.app_chn_name as appName,p4.role_group_name as groupRoleName,");
	    	sql.append(" p3.sequence_no as sequenceNo,p4.plas_role_group_id as plasRoleGroupId,p1.sequence_no as sequenceNo2");
	    	sql.append(" from plas_sys_pos_role_rel p0,plas_role p1,plas_app p3,plas_role_group p4");
	    	sql.append(" where p1.plas_app_id = p3.plas_app_id and p0.plas_sys_position_id = :plasSysPosId");
	    	sql.append(" and p0.plas_role_id = p1.plas_role_id and p1.plas_role_group_id = p4.plas_role_group_id");
	    	sql.append(" union ");
	    	sql.append(" select distinct p1.role_name as roleName,p3.app_chn_name as appName,p4.role_group_name as groupRoleName,");
			sql.append(" p3.sequence_no as sequenceNo,p4.plas_role_group_id as plasRoleGroupId,p1.sequence_no as sequenceNo2");
	    	sql.append(" from plas_sys_pos_role_rel p0,plas_role p1,plas_app p3,plas_role_group p4");
	    	sql.append(" where p1.plas_app_id = p3.plas_app_id and p0.plas_sys_position_id = :plasSysPosId");
	    	sql.append(" and p1.plas_role_group_id = p4.plas_role_group_id and p1.plas_role_id in");
	    	sql.append(" (select t1.plas_role_id from plas_role_pack_rel t1,plas_role_pack_pos_rel t2");
	    	sql.append(" where t1.plas_role_pack_id=t2.plas_role_pack_id and t2.plas_sys_position_id= :plasSysPosId)) t ");
	    	sql.append(" order by t.sequenceNo asc, t.plasRoleGroupId asc ,t.sequenceNo2 asc");
	    }else{
	    	sql.append("select distinct p1.role_name as roleName,p3.app_chn_name as appName,p4.role_group_name as groupRoleName,");
	    	sql.append(" p3.sequence_no as sequenceNo,p4.plas_role_group_id as plasRoleGroupId,p1.sequence_no as sequenceNo2");
	    	sql.append(" from plas_sys_pos_role_rel p0,plas_role p1,plas_app p3,plas_role_group p4");
	    	sql.append(" where p1.plas_app_id = p3.plas_app_id and p0.plas_sys_position_id = :plasSysPosId");
	    	sql.append(" and p0.plas_role_id = p1.plas_role_id and p1.plas_role_group_id = p4.plas_role_group_id");
	    	sql.append(" order by p3.sequence_no asc, p4.plas_role_group_id asc ,p1.sequence_no asc");
	    }
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("plasSysPosId", plasSysPosId);
		
		List list = this.getDao().createSQLQuery(sql.toString(), values).list();
		
		List<VoRole> rtnList = new ArrayList<VoRole>();
		if(list != null){
			VoRole tmpVo = null;
			Object[] obj = null;
			for (int i = 0; i < list.size(); i++) {
				obj = (Object[]) list.get(i);
				tmpVo = new VoRole();
				tmpVo.setRoleName((String) obj[0]);
				tmpVo.setAppName((String) obj[1]);
				tmpVo.setGroupRoleName((String) obj[2]);
				rtnList.add(tmpVo);
			}
		}
		return rtnList;
	}
}

