package com.hhz.uums.dao.plas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasOrgMgrRel;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.utils.OperConst;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class PlasOrgMgrRelManager extends BaseService<PlasOrgMgrRel, String> {
	
	@Autowired
	private PlasOrgMgrRelDao plasOrgMgrRelDao;
	
	@Autowired
	private PlasSysPositionManager plasSysPositionManager;
	
	@Autowired
	private PlasOperateLogManager plasOperateLogManager;
	
	
	public void savePlasOrgMgrRel(PlasOrgMgrRel plasOrgMgrRel) {
		PowerUtils.setEmptyStr2Null(plasOrgMgrRel);
		plasOrgMgrRelDao.save(plasOrgMgrRel);
	}

	public void deletePlasOrgMgrRel(String id) {
		plasOrgMgrRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasOrgMgrRel, String> getDao() {
		return plasOrgMgrRelDao;
	}

	/**
	 * 查询用户管理的机构列表
	 * @param plasUserId
	 * @return
	 */

	public List<PlasOrg> getPlasOrgsByAcctId(String plasAcctId) {
		
		String hql = new StringBuffer()
					 .append("  select t.plasOrg from PlasOrgMgrRel t where t.plasSysPosition.plasSysPositionId in(")
					 .append("	select p.plasSysPositionId from PlasSysPosition p where p.plasAcct.plasAcctId = ? ")
					 .append(" 	) ")
					 .toString();
		return getDao().createQuery(hql, plasAcctId).list();
	}
	/**
	 * 查询用户管理的机构列表
	 * @param uiid
	 * @return
	 */
	public List<PlasOrg> getPlasOrgsByUiid(String uiid){
		String hql = new StringBuffer()
		 .append("  select t.plasOrg from PlasOrgMgrRel t where t.plasSysPosition.plasSysPositionId in(")
		 .append("	select p.plasSysPositionId from PlasSysPosition p where p.plasAcct.uiid = ? ")
		 .append(" 	) ")
		 .toString();
		return getDao().createQuery(hql, uiid).list();
	}
	/**
	 * 查询实际职位对应的机构列表
	 * @param plasReasPosId
	 * @return
	 */
	public List<PlasOrg> getPlasOrgsByRealPosId(String plasRealPosId) {
		
		String hql = "  select t2.PlasOrg from PlasOrgMgrRel t2 where t.plasSysPosition.plasSysPositionId = ? ";
		return getDao().createQuery(hql, plasRealPosId).list();
	}
	/**
	 * 查询管理特定机构的账号列表
	 * @param plasOrgId
	 * @return
	 */
	public List<PlasAcct> getPlasAcctsByOrgId(String plasOrgId) {
		
		String hql = " from PlasAcct t where t.plasAcctId in (select t2.plasSysPosition.plasAcct.plasAcctId from PlasOrgMgrRel t2 where t2.plasOrg.plasOrgId = ? )";
		return getDao().createQuery(hql, plasOrgId).list();
	}
	public List<PlasSysPosition> getPlasSysPositionByOrgId(String plasOrgId) {
		
		String hql = "  select t2.plasSysPosition from PlasOrgMgrRel t2 where t2.plasOrg.plasOrgId = ? ";
		return getDao().createQuery(hql, plasOrgId).list();
	}
	/**
	 * Descrption：批量保存机构管理员信息
	 * @param org
	 * @param newSysPosIds
	 * @param oldSysPosIds
	 */
	public void saveBatchOrgMgrs(PlasOrg org,String newSysPosIds,String oldSysPosIds){
		
		String[] addPosIds = StringUtils.split(newSysPosIds, ",");
		String[] delPosIds = StringUtils.split(oldSysPosIds, ",");
		
		PlasOrgMgrRel tmpAddRel = null;
		
		// 新增管理机构对应的职位列表
		PlasSysPosition tmpPos = null;
		if(addPosIds != null && addPosIds.length != 0){
			StringBuffer addBuf = new StringBuffer();
			for (int i = 0; i < addPosIds.length; i++) {
				tmpPos = plasSysPositionManager.getEntity(addPosIds[i]);
				tmpAddRel = new PlasOrgMgrRel();
				tmpAddRel.setPlasOrg(org);
				tmpAddRel.setPlasSysPosition(tmpPos);
				this.savePlasOrgMgrRel(tmpAddRel);
				addBuf.append(tmpPos.getSysPosCd()).append("(").append(tmpPos.getSysPosName()).append(")");
			}

			plasOperateLogManager.savePlasOperateLog(
					SpringSecurityUtils.getLoginCode(),
					SpringSecurityUtils.getCurUserName(),
					OperConst.ORG_MGR,
					OperConst.ADD,
					new StringBuffer("[").append(org.getOrgCd()).append(",").append(org.getOrgName()).append("]")
					.append("授权机构管理员的系统职位如下:").append(addBuf).toString());
		}

		// 收回管理机构对应的职位列表
		if(delPosIds != null && delPosIds.length != 0){
			StringBuffer delBuf = new StringBuffer();
			for (int i = 0; i < delPosIds.length; i++) {
				tmpPos = plasSysPositionManager.getEntity(delPosIds[i]);
				delBuf.append(tmpPos.getSysPosCd()).append("(").append(tmpPos.getSysPosName()).append(")");
				this.deletePlasOrgMgrRel(delPosIds[i], org.getPlasOrgId());
			}

			plasOperateLogManager.savePlasOperateLog(
					SpringSecurityUtils.getLoginCode(),
					SpringSecurityUtils.getCurUserName(),
					OperConst.ORG_MGR,
					OperConst.DEL,
					new StringBuffer("[").append(org.getOrgCd()).append(",").append(org.getOrgName()).append("]")
					.append("收回机构管理员的系统职位如下:").append(delBuf).toString());
		}
	}
	/**
	 * 删除机构管理员
	 * @param sysPosId
	 * @param orgId
	 */
	public void deletePlasOrgMgrRel(String sysPosId, String orgId) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("sysPosId",sysPosId);
		values.put("orgId",orgId);
		String hql = "delete from PlasOrgMgrRel t where t.plasSysPosition.plasSysPositionId = :sysPosId and t.plasOrg.plasOrgId = :orgId ";
		int t = this.getDao().batchExecute(hql, values);
	}

	public PlasOrgMgrRel getEntity(String orgId,String sysPosId){
		String hql = "  select t from PlasOrgMgrRel t where t.plasSysPosition.plasSysPositionId = ?  and t.plasOrg.plasOrgId = ?";
		List list = getDao().createQuery(hql, sysPosId,orgId).list();
		if(list.size() == 0)
			return null;
		else
			return (PlasOrgMgrRel)list.get(0);
	}
	
	/**
	 * 是否机构管理员
	 * @param acctId
	 * @return
	 */
	public boolean validateIsOrgMgr(String acctId){

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("acctId",acctId);
		String hql = "from PlasOrgMgrRel t where t.plasSysPosition.plasAcct.plasAcctId = :acctId ";
		int count = this.getDao().find(hql, values).size();
		return (count>0);
	}

	/**
	 * 收回职位的机构管理员权限
	 * @param org
	 * @param pos
	 */
	public void deleteRel(PlasOrg org, PlasSysPosition pos) {

		String orgName = org.getOrgCd() +","+ org.getOrgName();
		String posName = pos.getSysPosCd() +","+ pos.getSysPosName();
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("posId",pos.getPlasSysPositionId());
		values.put("orgId",org.getPlasOrgId());
		String hql = "delete from PlasOrgMgrRel t where t.plasSysPosition.plasSysPositionId = :posId and t.plasOrg.plasOrgId = :orgId ";
		this.getDao().batchExecute(hql, values);
		
		plasOperateLogManager.saveUaapOperateLog(SpringSecurityUtils.getCurUiid(), SpringSecurityUtils.getCurUserName(),
				OperConst.SYSPOS, "收回机构管理员", new StringBuffer().append("收回职位("+posName+")的机构("+orgName+")管理员 权限!").toString(),
				"");
	}
	
//	public List<VoOrgMgr> getVoOrgMgrList(String orgId){
//		
//		String sql = "select t. " +
//					"  from plas_org_mgr_rel t, plas_sys_position t2, plas_acct t3, plas_user t4" +
//					" where ";
//	}
}

