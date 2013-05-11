package com.hhz.uums.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhz.uums.cache.PlasCache;
import com.hhz.uums.dao.app.AppAttachFileManager;
import com.hhz.uums.dao.app.AppDictTypeManager;
import com.hhz.uums.dao.app.AppParamManager;
import com.hhz.uums.dao.plas.PlasAcctManager;
import com.hhz.uums.dao.plas.PlasDimeOrgRelManager;
import com.hhz.uums.dao.plas.PlasLoginLogManager;
import com.hhz.uums.dao.plas.PlasNodeSysPosRelManager;
import com.hhz.uums.dao.plas.PlasOrgDimeManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.dao.plas.PlasRealPositionManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.dao.plas.PlasSysPosRoleRelManager;
import com.hhz.uums.dao.plas.PlasSysPositionManager;
import com.hhz.uums.dao.plas.PlasUserManager;
import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasLoginLog;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.service.WSPlasService;
import com.hhz.uums.vo.vw.OperResultVo;
import com.hhz.uums.vo.ws.WsAcctSysposRel;
import com.hhz.uums.vo.ws.WsAppDictData;
import com.hhz.uums.vo.ws.WsAppDictType;
import com.hhz.uums.vo.ws.WsPlasAcct;
import com.hhz.uums.vo.ws.WsPlasNodeSysPosRel;
import com.hhz.uums.vo.ws.WsPlasOrg;
import com.hhz.uums.vo.ws.WsPlasOrgDime;
import com.hhz.uums.vo.ws.WsPlasOrgRel;
import com.hhz.uums.vo.ws.WsPlasRealPosition;
import com.hhz.uums.vo.ws.WsPlasRole;
import com.hhz.uums.vo.ws.WsPlasSysPosition;
import com.hhz.uums.vo.ws.WsPlasUser;

/*
 * 本类提供接入项目调用UAAP接口,模拟用户,角色
 */

@WebService(endpointInterface = "com.hhz.uums.service.WSPlasService")
@Transactional
@Service
public class WSPlasServiceImpl implements WSPlasService {
	
	private static Log log = LogFactory.getLog(WSPlasServiceImpl.class);
	@Autowired
	private PlasOrgDimeManager dimeManager;
	@Autowired
	private PlasOrgManager orgManager;
	@Autowired
	private PlasRealPositionManager realPosManager;
	@Autowired
	private PlasSysPositionManager sysPosManager;
	@Autowired
	private PlasAcctManager acctManager;
	@Autowired
	private PlasUserManager userManager;
	@Autowired
	private PlasRoleManager roleManager;
	@Autowired
	private Md5PasswordEncoder md5Manager;
	@Autowired
	private AppAttachFileManager attachManager;
	@Autowired
	private PlasLoginLogManager loginManager;
	@Autowired
	private AppDictTypeManager dictTypeManager;
	@Autowired
	private PlasDimeOrgRelManager orgRelManager;
	@Autowired
	private PlasSysPosRoleRelManager sysPosRoleRelManager;
	@Autowired
	private AppParamManager appParamManager;
	@Autowired
	private PlasNodeSysPosRelManager plasNodeSysPosRelManager;
	
	//应用业务CD
	private String appBizCd;
	
	//操作人员ID
	private String operatorId;
 
	@Override
	public String getAppBizCd() {
		return this.appBizCd;
	}

	@Override
	public void setAppBizCd(String appBizCd) {
		this.appBizCd = appBizCd;
	}

	@Override
	public String getOperatorId() {
		return this.operatorId;
	}

	@Override
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	@Override
	public List<WsPlasOrgDime> getDimeList() {
		return dimeManager.getWsAll();
	}

	@Override
	public List<WsPlasOrg> getAllOrgList() {
		return orgManager.getWsAll();
	}

	@Override
	public List<WsPlasRealPosition> getRealPositions(){
		return realPosManager.getWsAll();
	}

	@Override
	public List<WsPlasSysPosition> getSysPositionList(){
		return sysPosManager.getWsAll();
	}

	@Override
	public List<WsPlasAcct> getAllAcctList() {
		return acctManager.getWsAll();
	}

	@Override
	public List<WsPlasUser> getAllUserList() {
		return userManager.getWsAll();
	}

	@Override
	public List<WsPlasRole> getRoleList() {
		return roleManager.getWsAll(getAppBizCd());
	}

	@Override
	public boolean validateUser(String uiid, String md5Password){
		return acctManager.validateUser(uiid, md5Password);
	}
	@Override
	public WsPlasUser getUserByUiid(String uiid) {
		
		List<WsPlasUser> users = userManager.getWsAll(uiid);
		if(users == null || users.size() == 0)
			return null;
		else
			return users.get(0);
	}
	@Override
	public WsPlasAcct getAcctByUiid(String uiid) {
		return acctManager.getWsAcct(uiid);
	}

	@Override
	public List<WsPlasRole> getRoleListByUiid(String uiid) {
		return roleManager.getWsUserRoles(getAppBizCd(), uiid);
	}

	@Override
	public List<WsPlasUser> getUserList(WsPlasUser user, int pageNo, int pageSize) {
		return userManager.getWsUserList(user, pageNo, pageSize);
	}
	@Override
	public List<WsPlasUser> getUserListByFilter(WsPlasUser user) {
		return userManager.getWsUsersByFilter(user);
	}
	@Override
	public List<WsAppDictType> getAllTypeList(){
//		return ConvertVoUtil.transDictTypeList(dictTypeManager.getAll("sequenceNo", true));
		return dictTypeManager.getWsAll();
	}
	
	@Override
	public List<WsAppDictData> getAllDataList(){
//		return ConvertVoUtil.transDictDataList(dictDataManager.getAll("appDictType.appDictTypeId", true));
		return dictTypeManager.getWsAllData();
	}
	@Override
	public List<WsPlasOrgRel> getOrgRelList(){
//		return ConvertVoUtil.transOrgRelList(orgRelManager.getAllOrgRelList());
		return orgRelManager.getWsAll();
		
	}
	@Override
	public List<WsAcctSysposRel> getAcctSysposRelList(){
		return sysPosRoleRelManager.getWsAll(getAppBizCd());
		
	}
	@Override
	public List<WsPlasNodeSysPosRel> getPlasNodeSysPosRel(){
		return plasNodeSysPosRelManager.getWsAll();
	}
	@Override
	public boolean updateAcct(WsPlasAcct acct) {
		return acctManager.updateAcct(acct);
	}
	@Override
	public boolean updateUser(WsPlasUser user) {
		return userManager.updateUser(user,getOperatorId());
	}

	@Override
	public List<WsPlasUser> getUserListByRoleId(String roleId) {
		return ConvertVoUtil.transUserList(userManager.getWsUserList(roleId));
	}

	@Override
	public boolean savePwdChange(String acctId, String oPassword, String nPassword) {

		PlasAcct acct = acctManager.getEntity(acctId);
		if (acct == null)
			return false; 

		String dbPwd = (StringUtils.isBlank(acct.getLoginInPassword()) ? "": acct.getLoginInPassword());
		String eOPwd = md5Manager.encodePassword(oPassword, "");
 
		//新密码有效性
		if (dbPwd.equals(eOPwd)) {
			OperResultVo vo = acctManager.acctResetPwdValue(acct, nPassword,getOperatorId(),null);
			return vo.getSuccess();
		} else
			return false;
	}
	
	@Override
	public boolean synPwdChange(String acctId,String nPassword, String splitSystem){
		PlasAcct acct = acctManager.getEntity(acctId);
		OperResultVo vo = acctManager.acctResetPwdValue(acct, nPassword,getOperatorId(), splitSystem);
		return vo.getSuccess();
	}

	@Override
	//http://localhost:8080/uaap
	//---/app/download.action?fileName=20100326162515bLEP.png&realFileName=PD快捷图标_32X32.png&bizModuleCd=uaapUser";
	public String getPicturePath(String userId) {
		return attachManager.getPicturePath(userId);
	}

	@Override
	public String getEmailSignContent(String userId) {

		PlasUser user = userManager.getEntity(userId);
		if(user == null)
			return "";
		else
			return user.getEmailSignContent();
	}

	@Override
	public boolean saveEmailSignContent(String userId, String content) {
		return userManager.saveEmailSignContent(userId, content, getOperatorId());
	}


	// 保存用户信息(最近一次登录)
	public String notifyLogin(String uiid, String ip) {  
		
		PlasAcct acct = acctManager.getPlasAcctByUiid(uiid);
		if(acct == null)
			return "";
		
		acct.setLastLoginDate(new Date());
		acct.setLastLoginIp(ip);
		
		// 不收集MAC地址
		// 重置错误次数
		acct.setFailureTimes(BigDecimal.ZERO);
		acctManager.savePlasAcct(acct);
	
		// 保存登陆日志
		PlasLoginLog loginLog = new PlasLoginLog();
		PlasUser user = acct.getPlasUser();
		loginLog.setUiid(acct.getUiid());
		loginLog.setUserCd(user.getUserCd());
		loginLog.setUserName(user.getUserName());
		loginLog.setIp(ip);
	//	loginLog.setMacAddress(macAddress);
		loginLog.setLoginDate(new Date());
		loginLog.setRemark("登录成功!");
	
		loginManager.savePlasLoginLog(loginLog);

		return loginLog.getPlasLoginLogId();
	}
	@Override
	public int notifyLoginError(String uiid, String ip) {
		return acctManager.notifyLoginError(uiid,ip);
	}

	@Override 
	public void notifyLogout(String uiid, String logId){

		//更新登出时间
		PlasAcct acct = acctManager.getPlasAcctByUiid(uiid);
		if(acct == null)
			return;
		
		acct.setLastLogoutDate(new Date());
		acctManager.savePlasAcct(acct);
	
		//保存登出日志
		PlasLoginLog loginLog = loginManager.getEntity(logId);
		loginLog.setLogoutDate(new Date());
		loginManager.savePlasLoginLog(loginLog);
	}
	
	@Override
	public String[] getParam(int type, String acctId) {

		try{
			PlasAcct acct = acctManager.getEntity(acctId);
			switch (type) {
//				case 1:
//					return EasServiceClient.getEasSsoFields(acct.getUiid());
//				case 2:
//					String field = MysoftYServiceClient.getMysoftFields(acct.getUiid());
//					return new String[]{field};
//				case 3:
//					String[] arr= CoremailServiceClient.getFields(acct.getUiid());
//					return arr;
				default :
					return new String[3];
			}
		}catch(Exception e){
			log.error("获取外部系统参数异常!",e);
			return new String[]{"-","-","-"};
		}
	}

	@Override
	public void addOnlineCount(String uiid){
		PlasCache.addOnlineCount(uiid);
	}

	@Override
	public void reduceOnlineCount(String uiid){
		PlasCache.reduceOnlineCount(uiid);
	}

	@Override
	public long getUserOnlineCount(){
		return PlasCache.getUserOnlineCount();
	}

	@Override
	public Set<String> getOnlineUiidSet(){
		return PlasCache.getOnlineUiidSet();
	}
	

	@Override
	public boolean startQuarz(String quartzId, String remoteId){
		return PlasCache.startQuartz(quartzId, remoteId);
	}
	@Override
	public boolean stopQuarz(String quartzId, String remoteId){
		return PlasCache.stopQuartz(quartzId, remoteId);
	}

	@Override
	public List<String> getRelPosOrgIdList(String acctId){
		return acctManager.getRelPosOrgIdList(acctId);
	}

	@Override
	public boolean getQuartzFlg(String quartzId) {
		return PlasCache.getQuartzFlg(quartzId);
	}

	@Override
	public void sendCommonSms(final String title, final String[] mobileNos,final String... msgs) throws Exception {
		//SMS sms=SMS.getInstanceCommon();
		//sms.send(title, mobileNos, msgs);
		
		// 另起线程处理
		log.info("开始发送");
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
//				SMS sms = SMS.getInstanceCommon();
//				try {
//					sms.send(title, mobileNos, msgs);
//					log.info("发送成功："+mobileNos.length);
//				} catch (Exception e) {
//					log.error("发送短信异常：", e);
//				}
			}
		});
		t.start();
		log.info("开始完成");
	}


	@Override
	public void sendImportSms(final String title, final String[] mobileNos, final String... msgs) throws Exception {
		// 另起线程处理
		log.info("开始发送");
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
//				SMS sms = SMS.getInstanceImport();
//				try {
//					sms.send(title, mobileNos, msgs);
//					log.info("发送成功："+mobileNos.length);
//				} catch (Exception e) {
//					log.error("发送短信异常：", e);
//				}
			}
		});
		t.start();
		log.info("开始完成");
	}
	// 是否同步Coremail邮箱，同步标识:1-是 0-否
	@Override
	public boolean isDefaultSynCmailUser(){
		try {
			return appParamManager.isDefaultSynCmailUser();
		} catch (Exception e) {
			return false;
		}
	}
}