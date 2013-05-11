package com.hhz.uums.dao.plas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasOrgCheckRel;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.OperConst;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.vo.vw.VoOrg;
import com.hhz.uums.vo.vw.VoSysPosition;

@Service
@Transactional
public class PlasOrgCheckRelManager extends BaseService<PlasOrgCheckRel, String> {
	@Autowired
	private PlasSysPositionDao  plasSysPositionDao;
	@Autowired
	private PlasOrgDao  plasOrgDao;
	@Autowired
	private PlasOperateLogManager plasOperateLogManager;
	@Autowired
	private PlasOrgCheckRelDao plasOrgCheckRelDao;
	@Autowired
	private PlasOrgManager plasOrgManager;

	public void savePlasOrgCheckRel(PlasOrgCheckRel plasOrgCheckRel) {
		PowerUtils.setEmptyStr2Null(plasOrgCheckRel);
		plasOrgCheckRelDao.save(plasOrgCheckRel);
	}

	public void deletePlasOrgCheckRel(String id) {
		plasOrgCheckRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasOrgCheckRel, String> getDao() {
		return plasOrgCheckRelDao;
	}
	public void saveBatch(String orgId,String newSysPosIds,String delSysPosIds){
		String[] addPosIds = StringUtils.split(newSysPosIds, ",");
		String[] delPosIds = StringUtils.split(delSysPosIds, ",");
		PlasOrgCheckRel tmpAddRel = null;
		PlasOrg org = plasOrgDao.get(orgId);
		PlasSysPosition tmpPos = null;
		// 新增管理机构对应的职位列表
		if(addPosIds != null && addPosIds.length != 0){
			StringBuffer addBuf = new StringBuffer();
			for (int i = 0; i < addPosIds.length; i++) {
				tmpAddRel = new PlasOrgCheckRel();
				tmpAddRel.setPlasOrgId(orgId);
				tmpAddRel.setPlasSysPositionId(addPosIds[i]);
				plasOrgCheckRelDao.save(tmpAddRel);
				//addBuf.append(tmpPos.getSysPosCd()).append("(").append(tmpPos.getSysPosName()).append(")");
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
				tmpPos = plasSysPositionDao.get(delPosIds[i]);
				//delBuf.append(tmpPos.getSysPosCd()).append("(").append(tmpPos.getSysPosName()).append(")");
				this.delete(delPosIds[i], orgId);
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
	 * 删除机构审计职位
	 * @param sysPosId
	 * @param orgId
	 */
	public void delete(String sysPosId, String orgId) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("sysPosId",sysPosId);
		values.put("orgId",orgId);
		String hql = "delete from PlasOrgCheckRel t where t.plasSysPositionId = :sysPosId and t.plasOrgId = :orgId ";
		int t = this.getDao().batchExecute(hql, values);
		
	}
	public List<PlasSysPosition> getPlasSysPositionByOrgId(String plasOrgId) {
		
		String hql = "  select t1 from PlasOrgCheckRel t,PlasSysPosition t1 where t1.plasSysPositionId=t.plasSysPositionId and t.plasOrgId = ? ";
		return getDao().createQuery(hql, plasOrgId).list();
	}
	public List<VoOrg> getCheckOrgs(String sysPosId ) {
		return getCheckOrgs(sysPosId,DictContants.TREE_DIME_PHYSICAL);
	}
	public List<VoOrg> getCheckOrgs(String sysPosId,String dimeCd) {
		StringBuffer sqlBuf = new StringBuffer()
		.append("select distinct t2.plasOrgId,")
		.append("       t1.parentId, ")
		.append("       t1.plasOrg.orgName, ")
		.append("       t1.plasOrg.orgCd, ")
		.append("       t1.plasOrg.orgBizCd, ")
		.append("       t1.plasOrg.sequenceNo ")
		.append("  from PlasDimeOrgRel t1 , PlasOrgCheckRel t2 " )
		.append(" where t1.plasOrg.plasOrgId = t2.plasOrgId ")
		.append("   and t1.plasOrg.activeBl =:activeBl ")
		.append("   and t1.plasOrgDime.dimeCd=:dimeCd  ")
		.append("   and t2.plasSysPositionId=:sysPosId  ")
		;
		sqlBuf.append(" order by t1.plasOrg.sequenceNo asc");
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("dimeCd", dimeCd);
		values.put("sysPosId", sysPosId);
		values.put("activeBl", new Boolean(true));
		List list = this.find(sqlBuf.toString(), values);
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
	public Page<VoSysPosition> findOrgCheckPage(Page page , String orgId){
		return findOrgCheckPage(page, orgId,true);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page<VoSysPosition> findOrgCheckPage(Page page , String orgId,boolean active){
		StringBuffer hql = new StringBuffer()
		.append(" select  t.plasSysPositionId, ")
		.append("         t1.plasOrgId, ")
		.append("         t.plasOrg.orgCd, ")
		.append("         t.plasOrg.orgName, ")
		.append("         t.sysPosCd, ")
		.append("         t.sysPosName, ")  
		.append("         t.shortName, ") 
		.append("         t.activeBl ") 
		.append("	from PlasOrgCheckRel t1,PlasSysPosition t where t.plasSysPositionId=t1.plasSysPositionId ");
		Map<String,Object> values = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(orgId)){
			hql.append(" and t1.plasOrgId=:orgId ");
			values.put("orgId", orgId);
		}
/*		if(active){
			hql.append(" and t.activeBl =:activeBl ");
			values.put("activeBl", true);
			
		}*/
		Page tmpPage = 
			findPage(page, hql.toString(), values);
		List<VoSysPosition> list = new ArrayList<VoSysPosition>();
		if (tmpPage != null && tmpPage != null) {
			Object[] tmpO = null;
			VoSysPosition tmpVo = null;
			for (int i = 0; i < tmpPage.getResult().size(); i++) {
				tmpO = (Object[])tmpPage.getResult().get(i);
				tmpVo = new VoSysPosition();
				tmpVo.setPlasSysPositionId((String)tmpO[0]);
				tmpVo.setOrgId((String)tmpO[1]);
				tmpVo.setOrgCd((String)tmpO[2]);
				tmpVo.setOrgName((String)tmpO[3]);
				tmpVo.setSysPosCd((String)tmpO[4]);
				tmpVo.setSysPosName((String)tmpO[5]);
				tmpVo.setShortName((String)tmpO[6]);
				tmpVo.setActiveBl((Boolean)tmpO[7]);
				list.add(tmpVo);
			}
		}
		tmpPage.setResult(list);
		return tmpPage;
	}
}

