package com.hhz.uums.dao.plas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.DateParser;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.dao.app.AppAttachFileManager;
import com.hhz.uums.dao.app.AppParamManager;
import com.hhz.uums.entity.app.AppAttachFile;
import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasApproveInfo;
import com.hhz.uums.entity.plas.PlasApproveNode;
import com.hhz.uums.entity.plas.PlasApproveNodeArch;
import com.hhz.uums.entity.plas.PlasApproveNodeHis;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.service.SmsUtil;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.DictMapUtil;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.RoleUtil;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.vo.ws.WsPlasRole;

@Service
@Transactional
public class PlasApproveInfoManager extends BaseService<PlasApproveInfo, String> {

	private static final Log log = LogFactory.getLog(PlasApproveInfoManager.class);
	@Autowired
	private PlasApproveInfoDao plasApproveInfoDao;
	@Autowired
	private PlasApproveNodeHisManager plasApproveNodeHisManager;
	@Autowired
	private PlasApproveNodeArchManager plasApproveNodeArchManager;
	@Autowired
	private PlasApproveNodeManager plasApproveNodeManager;
	@Autowired
	private PlasAcctManager plasAcctManager;
	@Autowired
	private PlasUserManager plasUserManager;
	@Autowired
	private PlasOrgManager plasOrgManager;
	@Autowired
	private PlasSysPositionManager plasSysPositionManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	public AppParamManager appParamManager;
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	@Autowired
	private PlasRoleManager plasRoleManager;

	public void savePlasApproveInfo(PlasApproveInfo plasApproveInfo) {
		PowerUtils.setEmptyStr2Null(plasApproveInfo);
		plasApproveInfoDao.save(plasApproveInfo);
	}

	public void deletePlasApproveInfo(String id) {
		plasApproveInfoDao.delete(id);
	}

	@Override
	public HibernateDao<PlasApproveInfo, String> getDao() {
		return plasApproveInfoDao;
	}

	public PlasApproveInfo getPlasApproveInfoByUiid(String uiid,String tPlasApproveId) {

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("newUiid", uiid);
		values.put("plasApproveInfoId", tPlasApproveId);
		values.put("applyTypeCd", DictContants.APPROVE_APPLY_TYPE_CREATE);
		values.put("approveStatusCd", DictContants.APPROVE_STATUS_PROCESS);
		String hql = " from PlasApproveInfo t where t.newUiid = :newUiid " +
				" and t.applyTypeCd = :applyTypeCd " +
				" and t.approveStatusCd = :approveStatusCd " +
				" and t.plasApproveInfoId <> :plasApproveInfoId";
		List<PlasApproveInfo> list = find(hql, values);
		if (list != null && list.size() > 0){
			log.debug("在plasApprove表中一共查到 " + list .size()+" 条");
			return list.get(0);
		}
		else
			return null;
	}

	public long getLikAcctListSize(String uiid) {

		String hql = " from PlasApproveInfo t where t.newUiid like ?";
		return this.getDao().countHqlResult(hql, uiid + "%");
	}

	public List<String> getLikAcctList(String uiid) {

		String hql = "select t.newUiid from PlasApproveInfo t where t.newUiid like ? ";
		List<String> list = this.getDao().createQuery(hql, uiid + "%").list();
		if (list == null || list.size() == 0)
			return list;
		else
			return list;
	}

	/**
	 * 给出意见
	 * 
	 * @param optionCd
	 * @param info
	 * @return
	 * @throws Exception 
	 */
	public boolean doProcess(int optionCd, PlasApproveInfo info) throws Exception {
		return doProcess(optionCd, info, "");
	}
	
	public boolean doProcess(int optionCd, PlasApproveInfo approveInfo, String optionDesc) throws Exception {
		

//		String tmpFlowTypeCd = info.getFlowTypeCd();
		String curRoleCd = approveInfo.getApproveRoleCd();
		
		//2011-12-07 
		//避免重复处理,需要检查角色,若用户没有拥有,当前流程角色则默认已处理.
		if(!RoleUtil.getMyRoleList().contains(curRoleCd))
			throw new Exception("已有人处理!");
		
		

		// 获取上一个节点
		PlasApproveNodeHis tPrevNode = plasApproveNodeHisManager.getPrevApproveNode(approveInfo);
		boolean firstFlg = false;
		if(tPrevNode == null){
			firstFlg = true;
		}
		//是否成功结束
		boolean successEndFlg = false;
		String nextNodeId = null;
		
		// 若走完,结束;否则,获取下一个节点.
		PlasApproveNodeHis nextNode = plasApproveNodeHisManager.getNextApproveNode(approveInfo);
		if (nextNode == null) {
			successEndFlg = true;
			nextNodeId = "end";
			//修复：流程结束,需要申请主表的approveRoleCd去掉,避免重复同意。
			//后面还要判断,驳回的话，这两个字段都要清空
			approveInfo.setNextNodeId(nextNodeId);
			approveInfo.setApproveRoleCd(null);
		}else{
			nextNodeId = nextNode.getPlasApproveNodeHisId();
			// 更新下一个节点的值
			approveInfo.setNextNodeId(nextNodeId);
			
			//获取下个节点 todo
//			getNextApproveNode(String plasApproveInfoId, String curNodeCd){
		}
		

		// 获取当前节点
		PlasApproveNodeHis curNode = plasApproveNodeHisManager.getCurNode(approveInfo, curRoleCd);
		
		if(curNode!= null){
			String tOptionCd = String.valueOf(optionCd);//审批意见
			String tAporoveUiid = SpringSecurityUtils.getCurUiid();
			String tApproveUserName = SpringSecurityUtils.getCurUserName();
			Date   tApproveDate = new Date();
			BigDecimal  tSequenceNo = curNode.getSequenceNo();
			String tOptionName = firstFlg?(" 发起"):((optionCd==1)?" 同意":" 驳回");
			String tAutoName = firstFlg?(successEndFlg?" 自动通过":""):"";
			
			
			// 保存审批记录
			curNode.setApproveRoleCd(curRoleCd);
			curNode.setApproveOptionCd(tOptionCd);// 审批意见
			curNode.setApproveUiid(tAporoveUiid);
			curNode.setApproveUserName(tApproveUserName);
			curNode.setApproveDate(tApproveDate);
			curNode.setSequenceNo(tSequenceNo);
			curNode.setPlasApproveInfo(approveInfo);
			curNode.setRemark(tOptionName+tAutoName);
			plasApproveNodeHisManager.savePlasApproveNodeHis(curNode);
	
			// 保存归档记录,仅含有审批过得节点
			PlasApproveNodeArch tmpArch = new PlasApproveNodeArch();
			tmpArch.setApproveRoleCd(curRoleCd);
			tmpArch.setApproveOptionCd(tOptionCd);// 审批意见
			tmpArch.setApproveUiid(tAporoveUiid);
			tmpArch.setApproveUserName(tApproveUserName);
			tmpArch.setApproveDate(tApproveDate);
			tmpArch.setSequenceNo(tSequenceNo);
			tmpArch.setPlasApproveInfo(approveInfo);
			tmpArch.setRemark(tOptionName+tAutoName);
			plasApproveNodeArchManager.savePlasApproveNodeArch(tmpArch);
	
			// 记录审批历史留言
			String roleName = DictMapUtil.getMapPermType().get(curRoleCd);
			String tRemark = StringUtils.isBlank(approveInfo.getRemark())? "" : approveInfo.getRemark();
			StringBuffer sb = new StringBuffer()
				.append(tRemark)
				.append(tApproveUserName).append(" ")
				.append(roleName)
				.append("[").append(DateParser.formatDate(tApproveDate, "yyyy-MM-dd HH:mm")).append(" ").append(tOptionName).append(" ]")
				.append(tAutoName).append(StringUtils.isBlank(optionDesc)?"":optionDesc)
				.append("<br/>");
			approveInfo.setRemark(sb.toString());
		}

		// 同意
		if (DictContants.APPROVE_STATUS_DO_AGREE == optionCd) {
			if (successEndFlg) {
				approveInfo.setApproveStatusCd(DictContants.APPROVE_STATUS_CLOSE);
			} else {
				approveInfo.setApproveStatusCd(DictContants.APPROVE_STATUS_PROCESS);
				approveInfo.setApproveRoleCd(nextNode.getApproveRoleCd());//设置下一个审批角色
				approveInfo.setSequenceNo(nextNode.getSequenceNo());
			}
		}
		//不同意
		else{
			approveInfo.setApproveStatusCd(DictContants.APPROVE_STATUS_REJECT);
			//修复：流程结束,需要申请主表的approveRoleCd去掉,避免重复同意。
			approveInfo.setApproveRoleCd(null);
			approveInfo.setNextNodeId(null);
		} 
		// 保存主表
		savePlasApproveInfo(approveInfo);
		
		// 不同意
		if(DictContants.APPROVE_STATUS_DO_DEGREE == optionCd){
			plasApproveNodeHisManager.cleanAllHisByApproveId(approveInfo.getPlasApproveInfoId());
			return true;
		}

		//若同意
		if (DictContants.APPROVE_STATUS_DO_AGREE == optionCd) {
			//流程完成
			if (successEndFlg) {

				String tmpApplyTypeCd = approveInfo.getApplyTypeCd();
				String tmpAcctId = null;

				PlasAcct acct = plasAcctManager.getPlasAcctByUiid(approveInfo.getNewUiid());
				if (acct != null) {
					tmpAcctId = acct.getPlasAcctId();
				}

				if (DictContants.APPROVE_APPLY_TYPE_CREATE.equals(tmpApplyTypeCd)) {
					processAcct(approveInfo,true,acct);
				} else if (DictContants.APPROVE_APPLY_TYPE_TRANSFER.equals(tmpApplyTypeCd)) {
					transferAcct(approveInfo, acct);
				} else if (DictContants.APPROVE_APPLY_TYPE_FREEZE.equals(tmpApplyTypeCd)) {
					plasAcctManager.acctFreeze(acct);
				} else if (DictContants.APPROVE_APPLY_TYPE_UNFREEZE.equals(tmpApplyTypeCd)) {
					plasAcctManager.acctEnable(acct);
				} else if (DictContants.APPROVE_APPLY_TYPE_CLOSED.equals(tmpApplyTypeCd)) {
					plasAcctManager.acctClose(acct);
					
					//释放职位
					plasSysPositionManager.cleanAcctPosRel(tmpAcctId);
					
				} else if (DictContants.APPROVE_APPLY_TYPE_ENABLE.equals(tmpApplyTypeCd)) {
					processAcct(approveInfo,false,acct);
					plasAcctManager.acctEnable(acct);
				} else {
					log.error(">>>>>>>>>>>>>>>>>> 人事流程走完! 啥都没干! 流水号: " + approveInfo.getPlasApproveInfoId());
				}
			}
		}
		return true;
	}
 
	private void processAcct(PlasApproveInfo approveInfo, boolean newFlg,PlasAcct acct) {
		
		String newUiid = approveInfo.getNewUiid();
		PlasUser user = null;
		
		if(newFlg){
			acct = new PlasAcct();
			user = new PlasUser();
		}else{
			acct = plasAcctManager.getPlasAcctByUiid(newUiid);
			user = acct.getPlasUser();
		}

		user.setUiid(approveInfo.getNewUiid());
		user.setUserName(approveInfo.getNewName());

		user.setPermissionLevelCd(approveInfo.getNewLevelCd());
		user.setPlasOrg(plasOrgManager.getEntity(approveInfo.getNewParentId()));
		user.setWorkDutyDesc(approveInfo.getNewWorkDuty());

		user.setServiceStatusCd(DictContants.PLAS_SERVICE_STATUS_ON);// 在职

		user.setSexCd(approveInfo.getSexCd());
		user.setBirthday(approveInfo.getBirthday());
		user.setIdno(approveInfo.getIdno());
		user.setNationCd(approveInfo.getNationCd());
		user.setSequenceNo(approveInfo.getSequenceNo());
		user.setNativeProvinceDesc(approveInfo.getNationCd());
		user.setActiveBl(new Boolean(true));
		user.setNativePlaceDesc(approveInfo.getNativePlaceDesc());
		user.setMarrigeStatusCd(approveInfo.getMarrigeStatusCd());
		user.setSchoolRecordCd(approveInfo.getSchoolRecordCd());
		// user.setEmail(info.get);//开通邮箱
		user.setGradSchoolDesc(approveInfo.getGradSchoolDesc());
		user.setFixedPhone(approveInfo.getFixedPhone());
		user.setMajorDesc(approveInfo.getMajorDesc());
		user.setMobilePhone(approveInfo.getMobilePhone());
		user.setMobilePhone2(approveInfo.getMobilePhone2());
		user.setAttendWorkDate(approveInfo.getAttendWorkDate());
		user.setIdCardTypeCd(approveInfo.getIdCardTypeCd());
		user.setMemberTypeCd(approveInfo.getMemberTypeCd());
		user.setResponsibility(approveInfo.getResponsibility());
		user.setSpecialUserFlg(approveInfo.getSpecialUserFlg());
		user.setUserTypeCd(approveInfo.getUserTypeCd());
		user.setProfessionTypeCd(approveInfo.getProfessionTypeCd());
		user.setPoliticsCd(approveInfo.getPoliticsCd());
		user.setSourceTypeCd(approveInfo.getSourceTypeCd());
		user.setDefaultCredenc(approveInfo.getDefaultCredenc());
		user.setOtherTypeCd(approveInfo.getOtherTypeCd());

//		user.setRemark(info.getRemark());//这个是留言历史,不拷贝
		user.setCreator(approveInfo.getCreator());
		user.setCreatedCenterCd(approveInfo.getCreatedCenterCd());
		user.setCreatedPositionCd(approveInfo.getCreatedPositionCd());
		user.setCreatedDate(approveInfo.getCreatedDate());

		plasUserManager.savePlasUser(user);

		//如不是新增，先替换图片后保存
		if(newFlg){
			// 拷贝员工照片
			List<AppAttachFile> tmpFileList = appAttachFileManager.getAttaFileByBizEntityId(approveInfo.getPlasApproveInfoId());
			AppAttachFile newFile = null;
			for (AppAttachFile tFile : tmpFileList) {
				newFile = new AppAttachFile();
				newFile.setBizEntityId(user.getPlasUserId());
				newFile.setBizModuleCd(com.hhz.uums.utils.Constants.APP_ATTACH_FILE_USER);
				newFile.setFileName(tFile.getFileName());
				newFile.setRealFileName(tFile.getRealFileName());
				newFile.setFilePath(tFile.getFilePath());
				newFile.setFileTypeName(tFile.getFileTypeName());
				newFile.setFileSize(tFile.getFileSize());
				newFile.setMainFlg(tFile.getMainFlg());
				newFile.setSmallPicName(tFile.getSmallPicName());
				newFile.setStatusCd(tFile.getStatusCd());
				newFile.setRemark("从plasApprove模块复制的图片");
				appAttachFileManager.saveAppAttachFile(newFile);
			}
		}
		
		// 账号
		// 设置密码,发送短信
		String oriPwd = appParamManager.getPassword();
		acct.setLoginInPassword(md5PasswordEncoder.encodePassword(oriPwd, ""));
		acct.setUiid(approveInfo.getNewUiid());
		
		//是否立即启用
		boolean enableFlg = false;
		if(approveInfo.getEffectDate()!= null){
			//生效日期
			Date effectDate = DateOperator.truncDate(approveInfo.getEffectDate());
			//今天
			Date today = DateOperator.truncDate(new Date());
			Long days = DateOperator.compareDays(effectDate, today);
			
			enableFlg = (days >= 0);
			
			acct.setStatusCd(enableFlg? "1":"0");//1-启用 0-未入职
		}else{
			acct.setStatusCd("0");
		}
		
		acct.setEffectDate(approveInfo.getEffectDate());
		acct.setFailureTimes(new BigDecimal(0));
		acct.setActiveBl(new Boolean(true));
		if(newFlg){
			acct.setAcctSeqNo("0");// 最后一个
			// 外部系统
			acct.setEmailFlg(DictContants.PLAS_EMAIL_FLG_NOOPEN);
			acct.setEmailPasswordSetFlg(DictContants.PLAS_EMAIL_PASSWORD_SET_FLG_NO);
			acct.setEasFlg(DictContants.PLAS_EAS_FLG_NOOPEN);
			acct.setEasPasswordSetFlg(DictContants.PLAS_EAS_PASSWORD_SET_FLG_NO);
			acct.setMysoftFlg(DictContants.PLAS_MYSOFT_FLG_NOOPEN);
			acct.setMysoftPasswordSetFlg(DictContants.PLAS_SYN_MYSOFT_USER_NO);
			acct.setCmailFlg(DictContants.CMAIL_FLG_NOOPEN);
			acct.setCmailPasswordSetFlg(DictContants.CMAIL_PASSWORD_SET_FLG_NO);
		}

		acct.setPlasUser(user);
		plasAcctManager.savePlasAcct(acct);

		// 删除无用职位,职位不删除,将职位上的账号设置为空.
		processPosList(acct, approveInfo.getOldSysPosIds(), approveInfo.getNewSysPosIds());

		// 若未入职,不发短信;否则,发送PD密码短信;
		// 每日定时任务: 开通帐号时，生效/创建邮箱.
		
		if ((!DictContants.PLAS_USER_STATUS_NOENTER.equals(acct.getStatusCd()))
				&& (!DictContants.PLAS_USER_STATUS_CLOSED.equals(acct.getStatusCd()))) {
			
			try{
	
				//修复： 只有启用的账号，才发送短信.
				if(enableFlg){
					SmsUtil.sendRandomPassword(acct.getUiid(), user.getUserName(), user.getSexCd(), user.getMobilePhone(), oriPwd, "PD");
				}
			}catch(Exception ex){
				log.error("开通账号: " + acct.getUiid() +",出现异常!", ex);
			}
		}
	}

	
	/**
	 * 删除无用职位,职位不删除,将职位上的账号设置为空.
	 * @param acct
	 * @param oldPosIds
	 * @param newPosIds
	 */
	private void processPosList(PlasAcct acct, String oldPosIds, String newPosIds){

		String tmpOldPosIds = StringUtils.isBlank(oldPosIds) ? "" : oldPosIds;
		String tmpNewPosIds = StringUtils.isBlank(newPosIds) ? "" : newPosIds;
		
		if (!tmpOldPosIds.equals(tmpNewPosIds)) {
			List<String> oldSysIdList = PowerUtils.array2List(tmpOldPosIds.split(","));
			List<String> newSysIdList = PowerUtils.array2List(tmpNewPosIds.split(","));

			// 删除无用职位,职位不删除,将职位上的账号设置为空.
			PlasSysPosition tSys = null;
			for (String tOldSysId : oldSysIdList) {
				if (StringUtils.isNotBlank(tOldSysId)) {
//					if (!newSysList.contains(tOldSysId)) {
						tSys = plasSysPositionManager.getEntity(tOldSysId);
						if(tSys != null){
							tSys.setPlasAcct(null);
							plasSysPositionManager.savePlasSysPosition(tSys);
						}
//					}
				}
			}

			// 更新职位
			PlasSysPosition tmpPos = null;
			for (String tNewSysId : newSysIdList) {
				//修复：若为空,同意时抛异常
				if(StringUtils.isNotBlank(tNewSysId)){
					tmpPos = plasSysPositionManager.getEntity(tNewSysId);
					if (tmpPos != null) {
						tmpPos.setPlasAcct(acct);
						plasSysPositionManager.savePlasSysPosition(tmpPos);
					}
				}
			}
		}
	}
	/**
	 * 更新账号信息
	 * 
	 * @param info
	 * @param acct
	 */
	public void transferAcct(PlasApproveInfo info, PlasAcct acct) {

		PlasUser user = acct.getPlasUser();

		if (!info.getNewLevelCd().equals(info.getOldLevelCd())) {
			user.setPermissionLevelCd(info.getNewLevelCd());
		}
		if (!info.getNewParentId().equals(info.getOldParentId())) {
			user.setPlasOrg(plasOrgManager.getEntity(info.getNewParentId()));
		}
		if (!info.getNewWorkDuty().equals(info.getOldWorkDuty())) {
			user.setWorkDutyDesc(info.getNewWorkDuty());
		}

		// 用户
		plasUserManager.savePlasUser(user);
		// 账号
		plasAcctManager.savePlasAcct(acct);


		processPosList(acct, info.getOldSysPosIds(), info.getNewSysPosIds());
	}

	/**
	 * 获取流程中得申请记录
	 * 
	 * @param newUiid
	 * @return
	 */
	public List<PlasApproveInfo> getProcessingList(String newUiid) {

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("newUiid", newUiid);
		values.put("apply", DictContants.APPROVE_STATUS_CREATE);
		values.put("process", DictContants.APPROVE_STATUS_PROCESS);
//		values.put("reject", DictContants.APPROVE_STATUS_REJECT);
		String hql = " from PlasApproveInfo t where t.newUiid = :newUiid and t.approveStatusCd in (:apply, :process)";//:reject,
		List list = find(hql, values);
		if (list == null)
			return new ArrayList<PlasApproveInfo>();
		else
			return list;
	}

	/**
	 * 获取职级较高的那一个
	 * 比对新旧职级
	 * @param newPermCd
	 * @param oldPermCd
	 * @return
	 */
	private String getMaxPermCd(String newPermCd, String oldPermCd){
		
		Map<String, String> tmpMapPerm = DictMapUtil.getMapPermLevel();//注意：一定要升序排列
		int idxNew = 0;
		int idxOld = 0;
		int i =0;
		
		//获取高级别的职级
		if(tmpMapPerm!= null){
			//从小到大
			for (String tmpKey : tmpMapPerm.keySet()) {
				if(StringUtils.isNotBlank(newPermCd)){
					if(newPermCd.equals(tmpKey)){
						idxNew = i;
					}
				}
				if(StringUtils.isNotBlank(oldPermCd)){
					if(oldPermCd.equals(tmpKey)){
						idxOld = i;
					}
				}
				i++;
			}
		}
		String tmpCd = null;
		if(idxNew >= idxOld){
			tmpCd = newPermCd;
		}else{
			tmpCd = oldPermCd;
		}
		return tmpCd;
	}
	
	/**
	 * 暂时保存/提交申请（必须有flow_type_cd值)
	 * @param info
	 * @param uiid
	 * @throws Exception
	 */
	public void saveOrSubmit(PlasApproveInfo info) throws Exception{
		saveOrSubmit(info, "");
	}
	public void saveOrSubmit(PlasApproveInfo info, String optionDesc) throws Exception{

		String uiid = SpringSecurityUtils.getCurUiid();
		//获取流程类型
		String tmpNewPermCd = info.getNewLevelCd();
		String tmpOldPermCd = info.getOldLevelCd();
		String tmpFlowTypeCd = null;
		
		//获取流程
		//注意：这里是新机构的id
		String[] tmpArray = RoleUtil.getFlowTypeCd(info.getNewParentId(), getMaxPermCd(tmpNewPermCd, tmpOldPermCd), uiid);
		if(tmpArray!= null && tmpArray.length == 2){
			tmpFlowTypeCd = tmpArray[0];
			info.setApplyRoleCd(tmpArray[1]);//申请的角色
			info.setFlowTypeCd(tmpFlowTypeCd);//流程角色
		}
		
//		System.out.println(">>>>>>>>>>>>>>>> 获取流程编号: " + tmpFlowTypeCd);
		info.setFlowTypeCd(tmpFlowTypeCd);

		if(info == null || StringUtils.isBlank(info.getFlowTypeCd()))
			throw new Exception("未找到流程!");
		
		//我有用的角色
		List<WsPlasRole> myRoleList = plasRoleManager.getWsUserRoles(GlobalConstants.UAAP_BIZ_CD, uiid);
		List<String> myRoleCdList = new ArrayList<String>();
		for (WsPlasRole t : myRoleList) {
			myRoleCdList.add(t.getRoleCd());
		}

		//调入、调出人员所在 区域
		Map<String,String> orgMapOld = TreePanelUtil2.getCornerOrgCd(info.getOldParentId());
		Map<String,String> orgMapNew = TreePanelUtil2.getCornerOrgCd(info.getNewParentId());
		
		//新旧机构
		info.setOldCornerOrgCd(orgMapOld.get("orgCd"));
		info.setOldCornerOrgName(orgMapOld.get("orgName"));
		info.setOldRelOrgCd(orgMapOld.get("relOrgCd"));
		
		info.setNewCornerOrgCd(orgMapNew.get("orgCd"));
		info.setNewCornerOrgName(orgMapNew.get("orgName"));
		info.setNewRelOrgCd(orgMapNew.get("relOrgCd"));
		
		//取操作人最高角色对应在审批流程节点
		String topRoleCd = null;
		PlasApproveNode tmp = null;
		
		if(RoleUtil.FLOW_DEFAULT.equals(info.getFlowTypeCd())){
//			topRoleCd = "DEFAULT";
			topRoleCd = info.getApplyRoleCd();//这里很重要，默认流程必须手动设置
		}

		//从所有节点(升序)，获取最后一个有权限的节点.
		else{
			

			//获取当前用户的角色
			String applyRoleCd = info.getApplyRoleCd();
			//是否发起流程所在角色
			boolean applyLevelFlg = false;
			
			String tmpAppRoleCd = null;
			String[] tmpArr = null;
			
			List<PlasApproveNode> nodeList = plasApproveNodeManager.getNodeList(info.getFlowTypeCd());
			for(int i=0; i < nodeList.size(); i++){
				tmp = nodeList.get(i);
				tmpAppRoleCd = tmp.getApproveRoleCd();
				if(StringUtils.isNotBlank(tmp.getApproveRoleCd())){
					tmpArr = StringUtils.split(tmpAppRoleCd,",");//流程模板中得角色,以逗号隔开
					if(tmpArr!= null){
						//很重要
						applyLevelFlg = false;
						//是否发起流程角色层级,若是,跳过
						for(int j=0; j<tmpArr.length; j++){
							if(StringUtils.isNotBlank(tmpArr[j])){
								if(applyRoleCd.equals(tmpArr[j])){
									//默认发起角色
									topRoleCd = applyRoleCd;
									applyLevelFlg = true;
									break;
								}
							}
						}
						
						if(applyLevelFlg){
							break;
						}
						
						//获取流程级别
						for(int j=0; j<tmpArr.length; j++){
							if(StringUtils.isNotBlank(tmpArr[j])){
								if(myRoleCdList.contains(tmpArr[j])){
									topRoleCd = tmpArr[j];
								}
							}
						}
					}
				}
			}
		}
		if(StringUtils.isBlank(topRoleCd)){
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>> 用户["+uiid+"]未在流程["+info.getFlowTypeCd()+"]中找到对应的角色!");
			topRoleCd = info.getApplyRoleCd();
		}else{
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>> 用户["+uiid+"]在流程["+info.getFlowTypeCd()+"]中找到对应的角色!["+topRoleCd+"]");
		}

		//设置角色
		info.setApproveRoleCd(topRoleCd);

		//保存主表
		savePlasApproveInfo(info);
		
		//若提交流程中
		if(DictContants.APPROVE_STATUS_PROCESS.equals(info.getApproveStatusCd())){
			//初始化节点
			initalNodes(info, orgMapOld,myRoleCdList);
			//执行同意
			doProcess(DictContants.APPROVE_STATUS_DO_AGREE, info,optionDesc);
		}
	} 
	
	/**
	 * 初始化流程节点
	 * @param info
	 * @param orgMapNew
	 * @param myRoleCdList
	 */
	private void initalNodes(PlasApproveInfo info,Map<String,String> orgMapOld,List<String> myRoleCdList){
		
		//若新增的，不需要清空已有节点; 若驳回的，在驳回时已经全部清空;
		boolean findBeginFlg = false;
		boolean enableInsertDynFlg = false;
		boolean addSeqFlg = false;
		boolean isFirstRowFlg = false;
		PlasApproveNodeHis tmpNode = null;
		
		
		//保存所有流程节点
		List<PlasApproveNode> nodeList = plasApproveNodeManager.getNodeList(info.getFlowTypeCd());
		BigDecimal lastSeq = new BigDecimal(0);
		if( nodeList != null){
			
			String tMainRoleCd = info.getApplyRoleCd();
			String tNodeRoleCd = null;
			
			for (PlasApproveNode tNode : nodeList) {
				
				tNodeRoleCd = tNode.getApproveRoleCd();
				
				//是否当前节点的角色含主流程角色.
				boolean containRoleFlg = false;
				String [] tArr = tNodeRoleCd.split(",");
				for (String tRoleCd : tArr) {
					if(tMainRoleCd.equals(tRoleCd)){
						containRoleFlg = true;
					}
				}
				
				//若特定流程
				boolean isDefaultFlowFlg = RoleUtil.FLOW_DEFAULT.equals(tNodeRoleCd);//是否不需要走流程
				//找第一个节点
				if( containRoleFlg || isDefaultFlowFlg){//若默认流程,解决默认通过，没有流程记录的问题.不能用RoleUtil.FLOW_DEFAULT.equals(info.getFlowTypeCd())
					
					isFirstRowFlg = true;
					findBeginFlg = true;
					if(StringUtils.isNotBlank(info.getNewCornerOrgCd()) 
							&&  StringUtils.isNotBlank(info.getOldCornerOrgCd())
							&& (!info.getNewCornerOrgCd().equals(info.getOldCornerOrgCd()))){
						
						//仅对"0203"打头的流程插入动态节点
						if(info.getFlowTypeCd().indexOf("0203") == 0){
							enableInsertDynFlg = true; 
						}
					}
				}
				
				//若找到对应节点,则拷贝模板节点到正式的流程中
				if( findBeginFlg){
					tmpNode = new PlasApproveNodeHis();
					tmpNode.setPlasApproveInfo(info);
					tmpNode.setApproveUiid(null);
					tmpNode.setApproveUserName(null);
					//第一个节点可能含有逗号
					if(isFirstRowFlg){
						if(isDefaultFlowFlg){
							tmpNode.setApproveRoleCd(info.getApproveRoleCd());
						}else{
							tmpNode.setApproveRoleCd(info.getApplyRoleCd());
						}
						isFirstRowFlg = false;
					}else{
						tmpNode.setApproveRoleCd(tNode.getApproveRoleCd());
					}
					tmpNode.setPositionCd(null);
					tmpNode.setWorkDutyDesc(null);
					tmpNode.setApproveDate(null);
					tmpNode.setApproveOptionCd(null);
					
					lastSeq = addSeqFlg? (tNode.getSequenceNo().add(new BigDecimal(1))):tNode.getSequenceNo();
					tmpNode.setSequenceNo(lastSeq);
					plasApproveNodeHisManager.savePlasApproveNodeHis(tmpNode);
				}

				//插入动态节点,取决于调出区域的cd
				if(enableInsertDynFlg){
					String tmpOldRoleCd = orgMapOld.get("roleCd");
					//若当前发起用户有调出中心的角色,那么不动态插入审批节点
					if(!myRoleCdList.contains(tmpOldRoleCd)){
						tmpNode = new PlasApproveNodeHis();
						tmpNode.setPlasApproveInfo(info);
						tmpNode.setApproveUiid(null);
						tmpNode.setApproveUserName(null);
						tmpNode.setApproveRoleCd(tmpOldRoleCd);
						tmpNode.setPositionCd(null);
						tmpNode.setWorkDutyDesc(null);
						tmpNode.setApproveDate(null);
						tmpNode.setApproveOptionCd(null);
						tmpNode.setSequenceNo(lastSeq.add(new BigDecimal(1)));
						plasApproveNodeHisManager.savePlasApproveNodeHis(tmpNode);
						addSeqFlg = true;
						enableInsertDynFlg = false;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(DateOperator.compareDays(new Date(), DateParser.parse("2012-04-20","yyyy-MM-dd")));
		System.out.println(DateOperator.compareDays(DateParser.parse("2012-04-18","yyyy-MM-dd"), DateParser.parse("2012-04-20","yyyy-MM-dd")));
	}
}
