package com.hhz.uums.dao.plas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.hhz.core.utils.RandomUtils;
import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.utils.DateUtil;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.OperConst;
import com.hhz.uums.vo.vw.VoSysPosition;
import com.hhz.uums.vo.vw.VoUser;
import com.hhz.uums.vo.ws.WsPlasSysPosition;

@Service
@Transactional
@SuppressWarnings("rawtypes")
public class PlasSysPositionManager extends BaseService<PlasSysPosition, String> {
	@Autowired
	private PlasSysPositionDao plasSysPositionDao;
	@Autowired
	private PlasOperateLogManager plasOperateLogManager;
	@Autowired
	private PlasAcctManager plasAcctManager;

	public void savePlasSysPosition(PlasSysPosition plasSysPosition) {
		PowerUtils.setEmptyStr2Null(plasSysPosition);
		plasSysPositionDao.save(plasSysPosition);
	}

	public void deletePlasSysPosition(String id) {
		plasSysPositionDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasSysPosition, String> getDao() {
		return plasSysPositionDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasSysPosition> getAllOrderedPosition() {
		// return uaapUserDao.getAll();
		return find("from PlasSysPosition where 1=1  order by sequenceNo asc , sysPosCd asc ");
	}
	/**
	 * 功能: 查询系统职位角色关系
	 * 
	 * @param roleCd
	 *            
	 */

	@Transactional(propagation = Propagation.SUPPORTS)
	public PlasSysPosition getEntityBySysPosCd(String sysPosCd) {


		List result = getDao().createCriteria(PlasSysPosition.class).add(Restrictions.eq("sysPosCd", sysPosCd)).list();
		if (result == null || result.size() == 0)
			return null;
		else
			return (PlasSysPosition) result.get(0);
	}

	/**
	 * @return
	 */
	public Map<String,List<PlasSysPosition>> getAlls(){
		return getAlls(false);
	}
	/**
	 * @param isAllFlg 是否全部(默认有效)
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Map<String,List<PlasSysPosition>> getAlls(boolean isAllFlg){

		Map<String,Object> values = new HashMap<String,Object>();
		values.put("activeBl", new Boolean(true));
		
		StringBuffer sb = new StringBuffer()
			.append("  from PlasSysPosition t ");
		if(!isAllFlg){
			sb.append(" where t.activeBl = :activeBl ");
		}
			sb.append(" order by t.sequenceNo desc,t.sysPosName asc ");
		
		List<PlasSysPosition> list = find(sb.toString(), values);
		String parentId = null;
		PlasSysPosition tmp = null;

		List<PlasSysPosition> tmpList = new ArrayList<PlasSysPosition>();
		Map<String,List<PlasSysPosition>> result = new HashMap<String,List<PlasSysPosition>>();

		for(int i=0;i<list.size();i++){
			tmp = (PlasSysPosition)list.get(i);
			parentId = tmp.getPlasOrg().getPlasOrgId();
			tmpList = result.get(parentId);
			if(null==tmpList){
				tmpList = new ArrayList<PlasSysPosition>();				
			}
			tmpList.add(tmp);
			result.put(parentId, tmpList);	
		}
		return result;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public Map<String,List<PlasSysPosition>> getAllsAUserName(){
		
		List<Object> list1 = this.getDao().findBySql("select u.user_name,p.sys_pos_name,p.plas_sys_position_id,p.plas_org_id from plas.plas_sys_position p , plas.plas_acct a ,plas.plas_user u " +
				"	where p.plas_acct_id=a.plas_acct_id and a.plas_user_id=u.plas_user_id order by  p.sequence_no desc,p.sys_pos_name asc",new HashMap<String,Object>());
		List<PlasSysPosition> list = new ArrayList<PlasSysPosition>();
		Object[] o = null;
		PlasSysPosition sys = null;
		PlasOrg org = null;
		for(Object vo : list1){
			o = (Object[])vo;
			sys = new PlasSysPosition();
			sys.setSysPosName((String)o[1]+"("+(String)o[0]+")");
			sys.setPlasSysPositionId((String)o[2]);
			org = new PlasOrg();
			org.setPlasOrgId((String)o[3]);
			sys.setPlasOrg(org);
			list.add(sys);
		}
		
		
		String parentId = null;
		PlasSysPosition tmp = null;
		
		List<PlasSysPosition> tmpList = new ArrayList<PlasSysPosition>();
		Map<String,List<PlasSysPosition>> result = new HashMap<String,List<PlasSysPosition>>();
		
		for(int i=0;i<list.size();i++){
			tmp = (PlasSysPosition)list.get(i);
			parentId = tmp.getPlasOrg().getPlasOrgId();
			tmpList = result.get(parentId);
			if(null==tmpList){
				tmpList = new ArrayList<PlasSysPosition>();				
			}
			tmpList.add(tmp);
			result.put(parentId, tmpList);	
		}
		return result;
	}
	/**
	 * 获取系统职位列表（通过uiid）
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasSysPosition> getSysPosListByUiid(String uiid){
		String hql = "from PlasSysPosition  t  where t.plasAcct.uiid =:uiid ";
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("uiid", uiid);
		
		List<PlasSysPosition> list = find(hql,values);
		
		return  list;
	}
	/**'
	 * 获取系统职位列表（WsSysPostion 接口）
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<WsPlasSysPosition> getWsSysPosListByUiid(String uiid){
		List<WsPlasSysPosition> result = new ArrayList<WsPlasSysPosition>();
		WsPlasSysPosition tmp = null;
		for(PlasSysPosition obj : getSysPosListByUiid(uiid)){
			tmp = new WsPlasSysPosition();
			tmp.setSysPosCd(obj.getSysPosCd());
			tmp.setSysPosName(obj.getSysPosName());
			result.add(tmp);
			
		}
		return result;
	}


	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<WsPlasSysPosition> getWsAll(){
		//完全内连接，未分配人员的职位不出来.
//		StringBuffer sb = new StringBuffer()
//			.append(" select t.plasSysPositionId, ")
//			.append("		 t.plasAcct.plasAcctId, ")
//			.append(" 		 t.plasOrg.plasOrgId, ") 
//			.append(" 		 t.sysPosCd, ")
//			.append(" 		 t.sysPosName, ")
//			.append(" 		 t.activeBl, ")
//			.append(" 		 t.sequenceNo, ")
//			.append(" 		 t.remark ")
//			.append(" from PlasSysPosition t ")
//			.append(" order by t.sequenceNo desc ");//降序
//		
//		Map<String,Object> values = new HashMap<String, Object>();
//		List list = this.getDao().find(sb.toString(),values);
		
		StringBuffer sb = new StringBuffer()
//		.append(" select distinct t.plas_sys_position_id, ")
//		.append("		 t.plas_acct_id, ")
//		.append(" 		 t.plas_org_id, ") 
//		.append(" 		 t.sys_pos_cd, ")
//		.append(" 		 t.sys_pos_name, ")
//		.append(" 		 t.active_bl, ")
//		.append(" 		 t3.sequence_no, ")
//		.append(" 		 t.remark ")
//		.append(" from plas_sys_position t,plas_acct t2, plas_user t3 ")
//		.append(" where  t.plas_acct_id = t2.plas_acct_id ")
//		.append("   and t2.plas_user_id = t3.plas_user_id ")
//		.append(" order by t3.sequence_no desc ");//降序
		
		.append("   select distinct t.plas_sys_position_id, ")//0
		.append("          t.plas_acct_id,  ")
		.append("          t.plas_org_id,   ")
		.append("          t.sys_pos_cd,  ")
		.append("          t.sys_pos_name,  ")
		.append("          t.active_bl,  ")//5
		.append("          t3.sequence_no, ")//人员序号 6
		.append("          t3.levelSeq, ")//职级序号 index：7
		.append("          t3.permission_level_cd, ")
		.append("          t.sequence_no, ")
		.append("          t.visable_flg ")//10 add by huangbijin 2012-03-31
		.append("     from plas_sys_position t,plas_acct t2,  ")
		.append("     (")
		.append("        select tt1.plas_user_id, ")
		.append("             tt1.permission_level_cd,")
		.append("             (case when tt1.sequence_no is null then 0 else tt1.sequence_no end) as sequence_no,  ")
		.append("             (case when tt2.sequence_no is null then 0 else tt2.sequence_no end) as levelSeq ")//职级序号
		.append("        from plas_user  tt1  ")
		.append("        left join (")
		.append("              select distinct ttt2.sequence_no, ttt2.dict_cd ")
		.append("               from app_dict_data ttt2, app_dict_type ttt3 ")
		.append("              where ttt3.dict_type_cd = :PermLevel ")
		.append("                and ttt2.app_dict_type_id = ttt3.app_dict_type_id ")
		.append("          ) tt2 ")
		.append("          on tt1.permission_level_cd = tt2.dict_cd  ")
		.append("     )t3  ")
		.append("     where t.plas_acct_id = t2.plas_acct_id  ")
		.append("       and t2.plas_user_id = t3.plas_user_id  ")
//		.append("  order by t3.levelSeq desc, t3.sequence_no desc   ");
		.append("  order by t.sequence_no desc ");//影响： 首页通讯录排序。
		
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("PermLevel", DictContants.PLAS_PERM_LEVEL);
		List list = this.getDao().findBySql(sb.toString(),values);
		
		List<WsPlasSysPosition> rtnList = new ArrayList<WsPlasSysPosition>();
		if(list != null){
			Object[] t = null;
			WsPlasSysPosition tmp = null;
			for (int i = 0; i < list.size(); i++) {
				t = (Object[])list.get(i);
				tmp = new WsPlasSysPosition();
				tmp.setPlasSysPositionId((String)t[0]);
				tmp.setAcctId((String)t[1]);
				tmp.setOrgId((String)t[2]);
				tmp.setSysPosCd((String)t[3]);
				tmp.setSysPosName((String)t[4]);
				tmp.setActiveBl((t[5]== null?new Boolean(false):(((BigDecimal)t[5]).intValue() == 1?new Boolean(true):new Boolean(false))));
				tmp.setSequenceNo((BigDecimal)(t[6]==null?null:((BigDecimal)t[6])));
				tmp.setVisableFlg((t[10]== null?new Boolean(false):(((BigDecimal)t[10]).intValue() == 1?new Boolean(true):new Boolean(false))));//add by huangbijin 2012-03-31
//				System.out.println(">>>>>>>>>>>>>>> posName:"+ tmp.getSysPosName() + "- 是否可见:" + tmp.getVisableFlg().toString());
//				System.out.println(">>>>>>>>>>>>>>> posName:"+ tmp.getSysPosName() + "-字典显示序号:" + String.valueOf((BigDecimal)t[7]) + "-职级:" + String.valueOf((String)t[8]) + "-" + tmp.getSequenceNo());
				rtnList.add(tmp);
			}
		}
		return rtnList;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void savePositionRelAcct(String positionId, PlasAcct newAcct) {

		PlasSysPosition pos = getEntity(positionId);
		String oldUiid = "";
		String oldName = "";
		
		PlasAcct oldAcct = pos.getPlasAcct();
		if(oldAcct!= null){
			oldUiid = oldAcct.getUiid();
			oldName = oldAcct.getPlasUser().getUserName();
		}
		
		String newUiid = newAcct.getUiid();
		String newName = newAcct.getPlasUser().getUserName();
		
		pos.setPlasAcct(newAcct);
		savePlasSysPosition(pos);
		
		plasOperateLogManager.savePlasOperateLog(
				SpringSecurityUtils.getLoginCode(),
				SpringSecurityUtils.getCurUserName(), 
				OperConst.SYSPOS, "切换系统职位对应的账号", 
				new StringBuffer()
				.append("[").append(pos.getSysPosCd()).append(",").append(pos.getSysPosName()).append("]")
				.append(" 旧账号:").append(oldUiid).append("(").append(oldName).append(")")
				.append(" 新账号:").append(newUiid).append("(").append(newName).append(")")
				.toString());
		
	}

	//停用职位
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void disablePos(String positionId) {
		
		Map<String, Object> values = new HashMap<String,Object>();
		values.put("positionId", positionId);
		
		//是失效职位与角色关系
		String hql1 = " delete from PlasSysPosRoleRel t where t.plasSysPosition.plasSysPositionId = :positionId ";
		int t1 = this.getDao().batchExecute(hql1, values);
		
		//是失效职位与机构关系(管理员)
		String hql2 = " delete from PlasOrgMgrRel t where t.plasSysPosition.plasSysPositionId = :positionId ";
		int t2 = this.getDao().batchExecute(hql2, values);
		 
		PlasSysPosition pos =  getEntity(positionId);
		pos.setActiveBl(new Boolean(false));
		savePlasSysPosition(pos);

		plasOperateLogManager.savePlasOperateLog(
				SpringSecurityUtils.getLoginCode(),
				SpringSecurityUtils.getCurUserName(), 
				OperConst.SYSPOS, "停用职位", 
				new StringBuffer()
				.append("[").append(pos.getSysPosCd()).append(",").append(pos.getSysPosName()).append("]停止职位成功!")
				.toString());
	}

	//启用职位
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void enablePos(String positionId) {

		PlasSysPosition pos =  getEntity(positionId);
		pos.setActiveBl(new Boolean(true));
		savePlasSysPosition(pos);
		
		plasOperateLogManager.savePlasOperateLog(
				SpringSecurityUtils.getLoginCode(),
				SpringSecurityUtils.getCurUserName(), 
				OperConst.SYSPOS, "启用职位", 
				new StringBuffer()
				.append("[").append(pos.getSysPosCd()).append(",").append(pos.getSysPosName()).append("]停止职位成功!")
				.toString());
	}
	//调动系统职位
	@Transactional(propagation = Propagation.SUPPORTS)
	public void redeployPos(PlasSysPosition pos) {
		
		savePlasSysPosition(pos);
		
		plasOperateLogManager.savePlasOperateLog(
				SpringSecurityUtils.getLoginCode(),
				SpringSecurityUtils.getCurUserName(), 
				OperConst.SYSPOS, "调动职位", 
				new StringBuffer()
				.append("[").append(pos.getSysPosCd()).append(",").append(pos.getSysPosName()).append("调动至:").append(pos.getPlasOrg().getOrgName()).append("]调动职位成功!")
				.toString());
	}
	//生成系统职位
	@Transactional(propagation = Propagation.SUPPORTS)
	public PlasSysPosition savePlasSysPosition(PlasUser plasUser,PlasAcct plasAcct){
		//20110101XXXX  年月日+4为随机码
		String sysPosCd = DateUtil.getStringYearMonthToday()+RandomUtils.generateString(4);
		while(!this.isPropertyUnique("sysPosCd", sysPosCd, sysPosCd)){
			sysPosCd = DateUtil.getStringYearMonthToday()+RandomUtils.generateString(4);
		}
		
		PlasSysPosition sysPos = new PlasSysPosition();
		sysPos.setPlasAcct(plasAcct);
		sysPos.setPlasOrg(plasUser.getPlasOrg());
		sysPos.setSysPosName(plasUser.getWorkDutyDesc());
		sysPos.setSysPosCd(sysPosCd);
		sysPos.setShortName(plasUser.getWorkDutyDesc());
		sysPos.setRecordVersion(0);
		sysPos.setActiveBl(new Boolean(true));//默认设置1-有效
		savePlasSysPosition(sysPos);
		
		return sysPos;
	}

	/**
	 * 
	 * Description:模糊查询系统职位(匹配字段sysPosName,sysPosCd,uiid)
	 */
	public List<VoSysPosition> getFindSysPositionList(String sysPosName, String sysPosCd, String uiid, String orgId, String dimeTypeCd, String maxNum) {
		return getFindSysPositionList(sysPosName, sysPosCd, uiid, orgId, dimeTypeCd, maxNum, false);
	}
	public List<VoSysPosition> getFindSysPositionList(String sysPosName, String sysPosCd, String uiid, String orgId, String dimeTypeCd, String maxNum, boolean isAllFlg) {
		return getFindSysPositionList(sysPosName, sysPosCd, uiid, orgId, dimeTypeCd, maxNum, isAllFlg, false);
	}
	public List<VoSysPosition> getFindSysPositionList(String sysPosName, String sysPosCd, String uiid, String orgId, String dimeTypeCd, String maxNum, boolean isAllFlg, boolean emptyPosFlg) {
		if(StringUtils.isBlank(maxNum)){
			maxNum = "30";
		}
		
		if(StringUtils.isNotBlank(orgId)){
			maxNum = "100";
		}
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("sysPosName", sysPosName);
		values.put("sysPosCd", sysPosCd);
		values.put("uiid", uiid);
		values.put("userName", uiid);
		
		values.put("orgId", orgId);
		values.put("dimeCd",DictContants.TREE_DIME_LOGICAL);
		values.put("activeBl",new Boolean(true));
//		values.put("visableFlg",new Boolean(true));//只允许查询1-可见
		
		long l1 = System.currentTimeMillis();
		StringBuffer hqlBuf = new StringBuffer()
		.append(" select  t.plas_sys_position_id, ")
		.append("         t.plas_acct_id, ")
		.append("         t.plas_org_id, ")
		.append("         t2.org_cd, ")
		.append("         t2.org_name, ")
		.append("         t.sys_pos_cd, ")
		.append("         t.sys_pos_name, ")  
		.append("         t.short_name, ") 
		.append("         t.active_bl, ") 
		.append("         t3.uiid,  ")
		.append("         t3.user_name,  ")
		.append("         t3.status_cd  ")
		.append(" from plas_sys_position t  ")
		.append(" left join plas_org t2 on t2.plas_org_id = t.plas_org_id ")
		.append(" left join (  ")
		.append("    select a.plas_acct_id,a.uiid,b.user_name,a.status_cd from plas_acct a,plas_user b where a.plas_user_id = b.plas_user_id ")
		.append(" )t3 ")
		.append(" on t.plas_acct_id = t3.plas_acct_id  ")
		.append(" where (t.sys_pos_name like :sysPosName or t.sys_pos_cd like :sysPosCd ")
//		.append("   and t.visable_flg = :visableFlg ")//只允许查询1-可见
		;
		
		if(StringUtils.isNotBlank(uiid)){
			//modify by huangbijin 2012-02-02  精确查询姓名
			hqlBuf.append(" or t.plas_acct_id in(select pa.plas_acct_id from plas_acct pa, plas_user pu where pa.plas_user_id = pu.plas_user_id and (pa.uiid like :uiid or pu.user_name = :userName) ) ");
		}
		hqlBuf.append(" ) ");
		
		if(StringUtils.isNotBlank(orgId)){
			hqlBuf.append(" and t.plas_org_id = :orgId ");
		}
		
		
		if(StringUtils.isNotBlank(dimeTypeCd)){
			hqlBuf.append(" and t.plas_org_id in (select tt2.plas_org_id from plas_org_dime tt1, plas_dime_org_rel tt2 where tt1.plas_org_dime_id = tt2.t.plas_org_dime_id and tt1.dime_cd = :dimeCd)");
		}

		if(!isAllFlg){
			hqlBuf.append(" and t.active_bl = :activeBl ");
		}
		if(emptyPosFlg){
			values.put("approveStatusCd1", DictContants.APPROVE_STATUS_CREATE);//1-申请中
			values.put("approveStatusCd2", DictContants.APPROVE_STATUS_PROCESS);//2-审批中
			hqlBuf.append(" and t.plas_acct_id is null ")
				  .append(" and not exists( select 1 from plas_approve_info tt where tt.new_sys_pos_ids like '%'||t.plas_sys_position_id||',%' and tt.approve_status_cd in (:approveStatusCd1, :approveStatusCd2))  ");
		}
		
		Page page = new Page(30);
		Page page2 = this.getDao().findPageSql(page, hqlBuf.toString(), values, new HashMap<String, Class>());
//		List<Object> result = this.getDao().find(hqlBuf.toString(), values);
		long l2 = System.currentTimeMillis();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 耗时："+ (l2-l1)/1000.00 +" 秒");
		List result = page2.getResult();
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

	//搜索职位列表
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<VoSysPosition> searchPositionList() {
		return searchPositionList(null,true);//含无效职位
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<VoSysPosition> searchPositionListByFlg(boolean isAllPosFlg) {
		return searchPositionList(null,isAllPosFlg,false);
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<VoSysPosition> searchPositionListByAcctId(String acctId) {
		if(StringUtils.isNotBlank(acctId))
			return searchPositionList(acctId, true);
		else
			return new ArrayList<VoSysPosition>();
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<VoSysPosition> searchPositionList(String acctId,boolean isAllPosFlg) {
		return searchPositionList(acctId, true, false);
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<VoSysPosition> searchPositionList(String acctId,boolean isAllPosFlg, boolean isEmptyPosFlg) {
		return searchPositionList(acctId, isAllPosFlg, isEmptyPosFlg, null);
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<VoSysPosition> searchPositionList(String acctId,boolean isAllPosFlg, boolean isEmptyPosFlg, String plasOrgId) {
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("acctId", acctId);
		values.put("activeFlg", new Boolean(true));
		values.put("plasOrgId", plasOrgId);
	
		StringBuffer sqlBuf = new StringBuffer()
		.append(" select t.plas_sys_position_id,")
		.append("  t.plas_acct_id,  " )
		.append("  t.plas_org_id,  " )
		.append("  t2.org_cd,  " )
		.append("  t2.org_name,  " )
		.append("  t.sys_pos_cd,  " )
		.append("  t.sys_pos_name,  " )
		.append("  t.short_name,   " )
		.append("  t.active_bl, " )
		.append("  t.tmp_pos_flg, " )//add by huangbijin 2011-12-21
		.append("  t.beyond_flg, " )//add by huangbijin 2011-12-21
		.append("  t.out_stat_flg, " )//add by huangbijin 2012-02-01
		
		.append("  t1.uiid, " )
		.append("  t1.user_name, " )
		.append("  t1.status_cd,   " )
		.append("  t1.sex_cd,   " )
		.append("  t.visable_flg ")//add by huangbijin 2012-03-31
		
		.append("  from plas_sys_position t ")
		.append("  left join (select tt1.plas_acct_id,tt1.uiid,tt2.user_name,tt1.status_cd,tt2.sex_cd from plas_acct tt1,plas_user tt2 where tt1.plas_user_id = tt2.plas_user_id) t1 on t1.plas_acct_id = t.plas_acct_id ")
		.append("  left join plas_org  t2 on t2.plas_org_id  = t.plas_org_id ")
		
		.append("  where 1=1 ");

		if(!isAllPosFlg){
			sqlBuf.append(" and t.active_bl = :activeFlg ");
		}
		if(isEmptyPosFlg){
			sqlBuf.append(" and t.plas_acct_id is null ");
		}
		
		if(StringUtils.isNotBlank(acctId)){
			sqlBuf.append(" and t.plas_acct_id = :acctId ");
		}
		
		if(StringUtils.isNotBlank(plasOrgId)){
			sqlBuf.append(" and t.plas_org_id = :plasOrgId ");
		}
		
		//2011-12-21 隐藏的帐号不显示
		if(!isAllPosFlg){
			values.put("filterDictTypeCd", DictContants.PLAS_READ_ONLY_USER);
			sqlBuf.append(" and t.plas_acct_id not in(")
				  .append(" 	select tt3.plas_acct_id ")
				  .append("       from app_dict_data tt1, app_dict_type tt2, plas_acct tt3 ")
				  .append("      where tt2.dict_type_cd = :filterDictTypeCd ")
				  .append("        and tt1.app_dict_type_id = tt2.app_dict_type_id ")
				  .append("        and tt1.dict_cd = tt3.uiid ")
				  .append(" ) ");
		}
			sqlBuf.append("   order by t.sequence_no desc ");
			

		List list = this.getDao().findBySql(sqlBuf.toString(),values);
		

		List<VoSysPosition> rtnList = new ArrayList<VoSysPosition>();
		if(list != null){
			Object[] t = null;
			VoSysPosition tmp = null;
			for (int i = 0; i < list.size(); i++) {
				t = (Object[])list.get(i);
				tmp = new VoSysPosition();
				tmp.setPlasSysPositionId((String)t[0]);
				tmp.setAcctId((String)t[1]);
				tmp.setOrgId((String)t[2]);
				tmp.setOrgCd((String)t[3]);
				tmp.setOrgName((String)t[4]);
				tmp.setSysPosCd((String)t[5]);
				tmp.setSysPosName((String)t[6]);
				tmp.setShortName((String)t[7]);
				
				tmp.setActiveBl(t[8] == null?new Boolean(false):(new Boolean(((BigDecimal)t[8]).intValue() == 1)));
				tmp.setTmpPosFlg(t[9] == null?new Boolean(false):(new Boolean(((BigDecimal)t[9]).intValue() == 1)));
				tmp.setBeyondFlg(t[10] == null?new Boolean(false):(new Boolean(((BigDecimal)t[10]).intValue() == 1)));
				tmp.setOutStatFlg(t[11] == null?new Boolean(false):(new Boolean(((BigDecimal)t[11]).intValue() == 1)));
				
				tmp.setUiid((String)t[12]);
				tmp.setUserName((String)t[13]);
				tmp.setAcctStatusCd((String)t[14]);//账号状态
				tmp.setSexCd((String)t[15]);//性别
				tmp.setVisableFlg(t[16] == null?new Boolean(false):(new Boolean(((BigDecimal)t[16]).intValue() == 1)));//add by huangbijin 2012-03-31
				
//				System.out.println("sexCd : " + tmp.getSexCd());
				
				rtnList.add(tmp);
			}
		}
		return rtnList;
	}
	public List<VoUser> searchUsingPosUserList(String orgIds){
		return searchPosUserList(null, false, false, orgIds);
	}
	public List<VoUser> searchPosUserList(String acctId,boolean isAllPosFlg, boolean isEmptyPosFlg, String orgIds) {
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("acctId", acctId);
		values.put("activeFlg", new Boolean(true));
	
		StringBuffer sqlBuf = new StringBuffer()
		
		.append(" select t.plas_sys_position_id,")
		.append("  		 t.plas_org_id,  " ) 
		.append("  		 t.sequence_no,  " ) 
		.append("  		 t1.plas_acct_id,   " )
		.append("  		 t1.plas_user_id,   " )//4
		
		.append("  		 t1.uiid, " )
		.append("  		 t1.user_biz_cd,   " )
		.append("  		 t1.user_cd,   " )
		.append(" 		 t1.user_name, " )
		.append("  		 t1.sex_cd,   " )//9
		
		.append(" 		 t1.work_duty_desc, " )
		.append(" 		 t1.fixed_phone, " )
		.append(" 		 t1.mobile_phone, " )
		.append("  		 t1.mobile_phone2   " )//13
		
		.append("   from plas_sys_position t ")
		.append("   left join ( ")
		.append("         select tt1.plas_acct_id,tt1.plas_user_id,tt1.uiid,tt2.user_biz_cd,tt2.user_cd,tt2.user_name,tt2.sex_cd,tt2.work_duty_desc,tt2.fixed_phone,tt2.mobile_phone,tt2.mobile_phone2 ")
		.append("           from plas_acct tt1, plas_user tt2 where tt1.plas_user_id = tt2.plas_user_id")
		.append("   ) t1 on t1.plas_acct_id = t.plas_acct_id ")
//		.append("   left join plas_org  t2 on t2.plas_org_id  = t.plas_org_id ")
		.append("  where 1=1 ");

		//是否所有
		if(isAllPosFlg){
		}else{
			sqlBuf.append(" and t.active_bl = :activeFlg ");
		} 
		
		if(isEmptyPosFlg){
			sqlBuf.append(" and t.plas_acct_id is null ");
		}else{
			sqlBuf.append(" and t.plas_acct_id is not null ");
		}
		
		if(StringUtils.isNotBlank(acctId)){
			sqlBuf.append(" and t.plas_acct_id = :acctId ");
		}
		
		if(StringUtils.isNotBlank(orgIds)){
			values.put("orgIds", StringUtils.split(orgIds, ","));
			sqlBuf.append(" and t.plas_org_id in ( :orgIds ) ");
		}
		
		//2011-12-21 隐藏的帐号不显示
		if(!isAllPosFlg){
			values.put("filterDictTypeCd", DictContants.PLAS_READ_ONLY_USER);
			sqlBuf.append(" and t.plas_acct_id not in(")
				  .append(" 	select tt3.plas_acct_id ")
				  .append("       from app_dict_data tt1, app_dict_type tt2, plas_acct tt3 ")
				  .append("      where tt2.dict_type_cd = :filterDictTypeCd ")
				  .append("        and tt1.app_dict_type_id = tt2.app_dict_type_id ")
				  .append("        and tt1.dict_cd = tt3.uiid ")
				  .append(" ) ");
		}
			sqlBuf.append("   order by t.sequence_no desc ");
			

		List list = this.getDao().findBySql(sqlBuf.toString(),values);

		List<VoUser> rtnList = new ArrayList<VoUser>();
		if(list != null){
			Object[] t = null;
			VoUser tmp = null;
			for (int i = 0; i < list.size(); i++) {
				t = (Object[])list.get(i);
				
				System.out.println(">>>>>>>>>>>>> "+(String)t[8]+"/"+(String)t[3]+"/"+(String)t[5]+" -- "+t[2]);
				
				tmp = new VoUser();
				tmp.setParentOrgId((String)t[1]);
				tmp.setSequenceNo((t[2]==null? new BigDecimal(0):(BigDecimal)t[2]));
				tmp.setUserId((String)t[4]);
				
//				voUser.setServiceCd(plasUser.getServiceStatusCd());
				
				tmp.setUiid((String)t[5]);
				tmp.setUserBizCd((String)t[6]);
				tmp.setUserCd((String)t[7]);
				tmp.setUserName((String)t[8]);
				tmp.setSexCd((String)t[9]);
				
				tmp.setWorkDutyDesc((String)t[10]);
				tmp.setPhone((String)t[11]);
				tmp.setMobilePhone((String)t[12]);
				tmp.setMobilePhone2((String)t[13]);
				
				
//				System.out.println("sexCd : " + tmp.getSexCd());
				
				rtnList.add(tmp);
			}
		}
		return rtnList;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<VoSysPosition> searchPositionListByIds(String[] arrSysIds) {
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("arrSysIds", arrSysIds);
		values.put("activeFlg", new Boolean(true));
	
		StringBuffer sqlBuf = new StringBuffer()
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
		
		.append("  from plas_sys_position t ")
		.append("  left join (select tt1.plas_acct_id,tt1.uiid,tt2.user_name,tt1.status_cd from plas_acct tt1,plas_user tt2 where tt1.plas_user_id = tt2.plas_user_id) t1 on t1.plas_acct_id = t.plas_acct_id ")
		.append("  left join plas_org  t2 on t2.plas_org_id  = t.plas_org_id ")
		
		.append("  where t.plas_sys_position_id in (:arrSysIds)")
 
		.append("   order by t.sequence_no desc ");
			

		List list = this.getDao().findBySql(sqlBuf.toString(),values);
		

		List<VoSysPosition> rtnList = new ArrayList<VoSysPosition>();
		if(list != null){
			Object[] t = null;
			VoSysPosition tmp = null;
			for (int i = 0; i < list.size(); i++) {
				t = (Object[])list.get(i);
				tmp = new VoSysPosition();
				tmp.setPlasSysPositionId((String)t[0]);
				tmp.setAcctId((String)t[1]);
				tmp.setOrgId((String)t[2]);
				tmp.setOrgCd((String)t[3]);
				tmp.setOrgName((String)t[4]);
				tmp.setSysPosCd((String)t[5]);
				tmp.setSysPosName((String)t[6]);
				tmp.setShortName((String)t[7]);
				tmp.setActiveBl(t[8] == null?new Boolean(false):(new Boolean(((BigDecimal)t[8]).intValue() == 1)));
				tmp.setUiid((String)t[9]);
				tmp.setUserName((String)t[10]);
				tmp.setAcctStatusCd((String)t[11]);//账号状态
				
				rtnList.add(tmp);
			}
		}
		return rtnList;
	}

	/**
	 * 保存账号职位关系
	 * @param acctId
	 * @param addPosIds
	 * @param delPosIds
	 */
	public void saveAcctPosRel(String acctId, String addPosIds, String delPosIds) {
		
		
		if(StringUtils.isBlank(acctId) ||(StringUtils.isBlank(addPosIds) && StringUtils.isBlank(delPosIds)))
			return;
		
		PlasAcct acct = plasAcctManager.getEntity(acctId);
		String operatedUiid = acct.getUiid();
		String operatedName = acct.getPlasUser().getUserName();

		String[] arrAdd = addPosIds.split(",");
		String[] arrDel = delPosIds.split(",");
		

		StringBuffer addBuf = new StringBuffer();
		StringBuffer delBuf = new StringBuffer();

		String tmpPosId = null;
		PlasSysPosition tmpPos = null;
		
		//授权替代
		if(arrAdd!= null){
			for(int i=0; i< arrAdd.length; i++){
				tmpPosId = arrAdd[i];
				if(StringUtils.isNotBlank(tmpPosId)){
					tmpPos = getEntity(tmpPosId);
					addBuf.append(tmpPos.getSysPosCd()).append("(").append(tmpPos.getSysPosName()).append(")");
					
					if(tmpPos.getPlasAcct()!= null && tmpPos.getPlasAcct().getPlasUser()!= null){
						addBuf.append("替代").append(tmpPos.getPlasAcct().getUiid()).append("(").append(tmpPos.getPlasAcct().getPlasUser().getUserName()).append("),");
					}
					
					saveAcctPosRel(acctId, tmpPosId);
				}
			}
		}
		
		//收回
		if(arrDel!= null){
			for(int j=0; j< arrDel.length; j++){
				tmpPosId = arrDel[j];
				if(StringUtils.isNotBlank(tmpPosId)){
					tmpPos = getEntity(tmpPosId);
					addBuf.append(tmpPos.getSysPosCd()).append("(").append(tmpPos.getSysPosName()).append(")");
					deleteAcctPosRel(acctId, tmpPosId);
				}
			}
		}
		
		StringBuffer sb = new StringBuffer().append("[").append(operatedUiid).append(",").append(operatedName).append("]批量授权职位!");
		if(StringUtils.isNotBlank(addBuf.toString())){
			sb.append("授权职位:").append(addBuf);
		}
		if(StringUtils.isNotBlank(delBuf.toString())){
			sb.append("收回职位:").append(delBuf);
		}
		
		plasOperateLogManager.savePlasOperateLog(
			SpringSecurityUtils.getCurUiid(),
			SpringSecurityUtils.getCurUserName(), 
			OperConst.SYSPOS,
			"批量授权职位",
			sb.toString()
		);
	}

	private void saveAcctPosRel(String acctId,String addPosId){
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("addPosId", addPosId);
		values.put("acctId", acctId);
		values.put("curDate", new Date());
		String hql = " update PlasSysPosition t set t.plasAcct.plasAcctId = :acctId,t.updatedDate = :curDate where  t.plasSysPositionId = :addPosId ";
		this.getDao().batchExecute(hql, values);
	}
	private void deleteAcctPosRel(String acctId,String delPosId){
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("delPosId", delPosId);
		values.put("acctId", acctId);
		values.put("curDate", new Date());
		String hql = " update PlasSysPosition t set t.plasAcct.plasAcctId = null,t.updatedDate = :curDate where t.plasAcct.plasAcctId = :acctId and  t.plasSysPositionId = :delPosId ";
		this.getDao().batchExecute(hql, values);
	}

	public void cleanPositionRelAcct(String positionId) {
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("positionId", positionId);
		values.put("curDate", new Date());
		String hql = " update PlasSysPosition t set t.plasAcct.plasAcctId = null,t.updatedDate = :curDate where  t.plasSysPositionId = :positionId ";
		this.getDao().batchExecute(hql, values);
	}

	public Map<String,List<VoSysPosition>> getMapSysPosListByAllFlg(boolean isAllFlg) {
		
		long l1 = System.currentTimeMillis();
		StringBuffer hqlBuf = new StringBuffer()
		.append(" select  t.plas_sys_position_id, ")
		.append("         t.plas_acct_id, ")
		.append("         t.plas_org_id, ")
		.append("         t2.org_cd, ")
		.append("         t2.org_name, ")
		.append("         t.sys_pos_cd, ")
		.append("         t.sys_pos_name, ")  
		.append("         t.short_name, ") 
		.append("         t.active_bl, ") 
		.append("         t3.uiid,  ")
		.append("         t3.user_name,  ")
		.append("         t3.status_cd  ")
		.append(" from plas_sys_position t  ")
		.append(" left join plas_org t2 on t2.plas_org_id = t.plas_org_id ")
		.append(" left join (  ")
		.append("    select a.plas_acct_id,a.uiid,b.user_name,a.status_cd from plas_acct a,plas_user b where a.plas_user_id = b.plas_user_id ")
		.append(" )t3 ")
		.append(" on t.plas_acct_id = t3.plas_acct_id  ");

		Map<String,Object> values = new HashMap<String,Object>();
		values.put("activeBl", new Boolean(true));

		if(!isAllFlg){
			hqlBuf.append(" and t.active_bl = :activeBl ");
		}
		Page page = new Page(30);
		Page page2 = this.getDao().findPageSql(page, hqlBuf.toString(), values, new HashMap<String, Class>());
//		List<Object> result = this.getDao().find(hqlBuf.toString(), values);
		long l2 = System.currentTimeMillis();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 耗时："+ (l2-l1)/1000.00 +" 秒");
		List result = page2.getResult();
		
		Map<String,List<VoSysPosition>> rtnMap = new HashMap<String,List<VoSysPosition>>();
		List<VoSysPosition> tmpList = null;
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
				
				tmpList = rtnMap.get(tmp.getOrgId());
				if(null==tmpList){
					tmpList = new ArrayList<VoSysPosition>();				
				}
				tmpList.add(tmp);
				rtnMap.put(tmp.getOrgId(), tmpList);	
			}
		}
		return rtnMap;
	}

	/**
	 * 释放职位
	 * @param acctId
	 */
	public void cleanAcctPosRel(String acctId) {
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("acctId", acctId);
		String hql = " from PlasSysPosition t where t.plasAcct.plasAcctId = :acctId ";
		List<PlasSysPosition>  list = find(hql, values);
		StringBuffer sb = new StringBuffer();
		if(list != null && list.size() > 0){
			for (PlasSysPosition t : list) {
				sb.append(t.getSysPosName()).append("[").append(t.getPlasSysPositionId()).append("],");
				t.setPlasAcct(null);
				savePlasSysPosition(t);
			}
			System.out.println(">>>>>>>>>>>>>> 离职账号： acctId["+acctId+"],释放职位: "+list.size() +"个职位!如下:"+ sb.toString());
		}
	}
}

