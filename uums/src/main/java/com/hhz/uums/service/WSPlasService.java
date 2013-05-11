package com.hhz.uums.service;

import java.util.List;
import java.util.Set;

import javax.jws.WebService;

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

@WebService
public interface WSPlasService {

	// 调用接口的应用业务编号
	public String getAppBizCd();
	public void   setAppBizCd(String appBizCd);

	// 调用接口的当前操作人
	public String getOperatorId();
	public void   setOperatorId(String operatorId);
	
		
	//	***************************** 缓存 ***************************** 
	//	所有维度(有效)
	public List<WsPlasOrgDime> getDimeList();
	
	//	所有机构(含有效、无效)
	public List<WsPlasOrg> getAllOrgList();

	//	所有旧职位
	public List<WsPlasRealPosition> getRealPositions();

	//	所有系统职位(有效)
	public List<WsPlasSysPosition> getSysPositionList();
	
	//	所有账号(含有效、无效)
	public List<WsPlasAcct> getAllAcctList();
	
	//	所有人员	所有(在职、离职)
	public List<WsPlasUser> getAllUserList();
	
	//	角色列表(有效)	
	public List<WsPlasRole> getRoleList();
	
	//  字典类型,代码
	public List<WsAppDictType> getAllTypeList();
	public List<WsAppDictData> getAllDataList();
	
	//  机构关系
	public List<WsPlasOrgRel> getOrgRelList();
	
	//  账号与职位关系
	public List<WsAcctSysposRel> getAcctSysposRelList();
	//网批节点和系统职务对应关系表
	public List<WsPlasNodeSysPosRel> getPlasNodeSysPosRel();
		
	//	***************************** 实时 ***************************** 
	
	//  校验用户名/密码
	public boolean validateUser(String uiid, String md5Password);
	
	//	查询指定用户信息
	public WsPlasUser getUserByUiid(String uiid); 
	public WsPlasAcct getAcctByUiid(String uiid); 
	
	//	查询用户的角色列表
	public List<WsPlasRole> getRoleListByUiid(String uiid);
	
	//	模糊查询用户列表	
	public List<WsPlasUser> getUserList(WsPlasUser user, int pageNo, int pageSize);
	public List<WsPlasUser> getUserListByFilter(WsPlasUser user);

	// 保存账号信息
	public boolean updateAcct(WsPlasAcct acct);
	
	// 保存用户信息
	public boolean updateUser(WsPlasUser user);
	
	//	查找特定角色的用户列表
	public List<WsPlasUser> getUserListByRoleId(String roleId);
	
	//	保存密码信息	
	public boolean savePwdChange(String acctId, String oPassword, String nPassword);
	public boolean synPwdChange(String acctId,String nPassword, String splitSystem);
	
	//	获取照片URL	
	public String getPicturePath(String userId);
	
	//	获取签名	
	public String getEmailSignContent(String userId);
	
	//	保存签名	
	public boolean saveEmailSignContent(String userId, String content);

	//  登录成功
	public String notifyLogin(String uiid, String ip);
	
	//  登录失败
	public int notifyLoginError(String uiid, String ip);
	
	//  登出系统
	public void notifyLogout(String uiid, String logId);
	
	//	获取登录外部系统的参数列表(系统代码)EAS/mysoft
	public String[] getParam(int type, String acctId);
	
	// 上线
	public void addOnlineCount(String uiid);

	// 下线
	public void reduceOnlineCount(String uiid);

	// 获取在线人数
	public long getUserOnlineCount();

	// 获取在线人员清单
	public Set<String> getOnlineUiidSet();

	// 获取账号持有职位所在的机构
	public List<String> getRelPosOrgIdList(String acctId);

	//发送普通短信
	public void sendCommonSms(String title, String[] mobileNos,String... msgs)throws Exception ;
	//发送重要短信
	public void sendImportSms(String title, String[] mobileNos,String... msgs)throws Exception ;
	
	/* **********************************************************************/
	// 同步任务
	
	public boolean startQuarz(String quartzId, String remoteId);
	public boolean stopQuarz(String quartzId, String remoteId);
	public boolean getQuartzFlg(String quartzId);
	// 是否同步Coremail邮箱，同步标识:1-是 0-否
	public boolean isDefaultSynCmailUser();
}