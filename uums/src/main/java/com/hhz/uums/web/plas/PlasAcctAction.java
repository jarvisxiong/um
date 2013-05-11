package com.hhz.uums.web.plas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.uums.cache.LocalCache;
import com.hhz.uums.dao.app.AppParamManager;
import com.hhz.uums.dao.bis.BisSmsTmpManager;
import com.hhz.uums.dao.plas.PlasAcctManager;
import com.hhz.uums.dao.plas.PlasDimeOrgRelManager;
import com.hhz.uums.dao.plas.PlasOperateLogManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.dao.plas.PlasSysPosRoleRelManager;
import com.hhz.uums.dao.plas.PlasSysPositionManager;
import com.hhz.uums.dao.plas.PlasUserManager;
import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasSysPosRoleRel;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.service.SmsUtil;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.utils.OperConst;
import com.hhz.uums.utils.TreePanelUtil;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.utils.Util;
import com.hhz.uums.vo.vw.OperResultVo;
import com.hhz.uums.vo.vw.VoAcct;
import com.hhz.uums.vo.vw.VoOrg;
import com.hhz.uums.vo.vw.VoSysPosition;
import com.hhz.uums.web.CrudActionSupport;

public class PlasAcctAction extends CrudActionSupport<PlasAcct> {

	private static final long serialVersionUID = 3092054663564150429L;
	@Autowired
	private Md5PasswordEncoder md5PasswordEncoder;
	@Autowired
	private PlasAcctManager plasAcctManager;
	@Autowired
	public PlasOperateLogManager plasOperateLogManager;
	@Autowired
	public PlasDimeOrgRelManager plasDimeOrgRelManager;
	@Autowired
	public BisSmsTmpManager bisSmsTmpManager;
	@Autowired
	public PlasUserManager plasUserManager;
	@Autowired
	public PlasOrgManager plasOrgManager;
	@Autowired
	public PlasRoleManager plasRoleManager;
	@Autowired
	public AppParamManager appParamManager;
	@Autowired
	private PlasSysPositionManager plasSysPositionManager;
	@Autowired
	private PlasSysPosRoleRelManager plasSysPosRoleRelManager;
	@Autowired
	private PlasDimeOrgRelManager plasDimeOrgRel;
	private List<VoSysPosition> acctRelPosList;

	private PlasAcct entity;
	 
	@Override
	public PlasAcct getModel() {
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	@Override
	public void prepareInput() throws Exception {
		prepareModel();
		if(StringUtils.isNotBlank(getId())){
			acctRelPosList = getRelPosList(getId());
		}
	}

	@Override
	public String input() throws Exception {
		return "input";
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = plasAcctManager.getEntity(getId());
		} else {
			entity = new PlasAcct();
			//默认账号状态：未入职
			entity.setStatusCd(DictContants.PLAS_SERVICE_STATUS_NOENTER);
			entity.setEmailFlg(DictContants.PLAS_EMAIL_FLG_NOOPEN);
			entity.setEasFlg(DictContants.PLAS_EAS_FLG_NOOPEN);
			entity.setMysoftFlg(DictContants.PLAS_MYSOFT_FLG_NOOPEN);
			entity.setCmailFlg(DictContants.CMAIL_FLG_NOOPEN);
		}
	}
	@Override
	public void prepareSave() throws Exception {
		prepareModel();
	}
	@Override
	public String save() throws Exception {
		try {
			
			plasAcctManager.savePlasAcct(entity);
			
			String strLog = new StringBuffer()
				.append("[").append(entity.getCustLoginName()).append("," + entity.getAcctSeqNo() + ",]").append("新增成功")
				.toString();
			
			String operUiid = SpringSecurityUtils.getLoginCode();
			String operUserName = SpringSecurityUtils.getCurUserName();
			plasOperateLogManager.savePlasOperateLog(operUiid, operUserName, OperConst.ACCT, OperConst.ADD, strLog);

			Struts2Utils.renderHtml("success");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Struts2Utils.renderHtml("failure");
		}
		return null;
	}

	@Override
	public String delete() throws Exception {
		return null;
	}


	/**
	 * ajax 获取可用机构与账号树(不含注销)
	 */
	public void loadAcctTreeEnable() {
		Struts2Utils.renderJson(TreePanelUtil.buildOrgAcctTreeNoChecked(SpringSecurityUtils.getCurUiid(),true,false));
	}

	/**
	 * ajax 获取所有机构与账号树(含注销)
	 */
	public void loadAcctTreeAll() {
		Struts2Utils.renderJson(TreePanelUtil.buildOrgAcctTreeNoChecked(SpringSecurityUtils.getCurUiid(),true,true));
	}
 
	
	/**
	 * ajax 操作账号
	 * @param plasAcctId 账号ID
	 * @param funcName 操作名
	 * @param newPwd  新密码
	 * @throws Exception 
	 */
	public String commonProcess() throws Exception{

		//操作账号
		String plasAcctId = Struts2Utils.getParameter("plasAcctId").trim();
		//方法名称
		String funcName = Struts2Utils.getParameter("funcName");
		//密码
		String newPwd = Struts2Utils.getParameter("newPwd");
		
		//账号(冻结/生效/注销/重置密码)
		if("acctFreeze".equals(funcName)){
			Struts2Utils.renderJson(plasAcctManager.acctOperate(plasAcctId, PlasAcctManager.ACCT_FREEZE));
		}
		else if("acctEnable".equals(funcName)){
			Struts2Utils.renderJson(plasAcctManager.acctOperate(plasAcctId, PlasAcctManager.ACCT_ENABLE));
		}
		else if("acctClose".equals(funcName)){
			OperResultVo vo = plasAcctManager.acctOperate(plasAcctId, PlasAcctManager.ACCT_CLOSE);

			//add by huangbijin chenjj1要求释放职位 2012-01-18
			if(vo.getSuccess()){
				//释放职位
				plasSysPositionManager.cleanAcctPosRel(plasAcctId);
			}
			
			Struts2Utils.renderJson(vo);
		}
		else if("acctResetPassword".equals(funcName)){
			Struts2Utils.renderJson(plasAcctManager.acctOperate(plasAcctId, PlasAcctManager.ACCT_RESET_PWD));
		}
		else if("acctResetPasswordHand".equals(funcName)){
			Struts2Utils.renderJson(plasAcctManager.acctOperate(
				plasAcctManager.getEntity(plasAcctId), 
				PlasAcctManager.ACCT_RESET_PWD_VALUE, 
				SpringSecurityUtils.getCurUiid(),
				newPwd,
				"cmail,mysoft"//默认同步plas密码,若含同步外部系统，需逗号分开
			));
		}
		
		
//		//email(开通/禁用/启用/同步账号信息/重置密码|同步密码)
//		else if("emailOpen".equals(funcName)){
//			Struts2Utils.renderJson(plasAcctManager.emailOperate(plasAcctId, ExchangeServiceClient.OPER_USER_ADD));
//		}
//		else if("emailDisable".equals(funcName)){
//			Struts2Utils.renderJson(plasAcctManager.emailOperate(plasAcctId, ExchangeServiceClient.OPER_USER_DISABLE));
//		}
//		else if("emailEnable".equals(funcName)){
//			Struts2Utils.renderJson(plasAcctManager.emailOperate(plasAcctId, ExchangeServiceClient.OPER_USER_ENABLE));
//		}
//		else if("emailSynAcct".equals(funcName)){
//			Struts2Utils.renderJson(plasAcctManager.emailOperate(plasAcctId, ExchangeServiceClient.OPER_USER_SAVE));
//		}
//		else if("emailResetPassword".equals(funcName)){
//			Struts2Utils.renderJson(plasAcctManager.emailOperate(plasAcctId, ExchangeServiceClient.OPER_PWD_RESET));
//		}
		
		return null;
	}
 

	/**
	 * 编辑账号：从用户管理模块入口 author:jiaoxiaofeng 2011-3-3
	 * 
	 * @return String
	 * @throws Exception
	 */
	public void prepareEditAcct() throws Exception {
		String uiid = Struts2Utils.getParameter("uiid");

		if (uiid != null && !"".equals(uiid)) {
			entity = plasAcctManager.getPlasAcctByUiid(uiid);

		} else
			throw new Exception("restart user! id is not exsits!");
	}

	public String editAcct() {
		return "edit";
	}

	/**
	 * 开通账号：从用户管理模块入口 author:jiaoxiaofeng 2011-3-3
	 * @param uiid 账号
	 * @param plasUserId 用户ID
	 * @param userName 姓名
	 * 
	 * @return String
	 */

	public void prepareCreateAcct() {
		String uiid = Struts2Utils.getParameter("uiid");
		String userId = Struts2Utils.getParameter("plasUserId");
		String userName = Struts2Utils.getParameter("userName");
		PlasUser user = new PlasUser();
		user.setPlasUserId(userId);
		entity = new PlasAcct();
		entity.setUiid(uiid);
		entity.setPlasUser(user);
		entity.setCustLoginName(userName);
		entity.setRecordVersion(1l);
		entity.setStatusCd(DictContants.PLAS_USER_STATUS_NOENTER);

	}
	public String createAcct() {
		// 判断全局唯一
		if (!plasAcctManager.isPropertyUnique("uiid", entity.getUiid(), "")) {

			JsonUtil.renderFailure("开通账号失败：登录账号已存在");
		}
		try {
			plasAcctManager.savePlasAcct(entity);

			Struts2Utils.renderText("success");
			return null;
		} catch (Exception e) {
			JsonUtil.renderFailure("开通账号失败:原因" + e.getMessage());
			return null;
		}

	}

	/**
	 * 修改密码
	 * 
	 * @param acctId  账号ID
	 * @param oldPwd  旧密码
	 * @param newPwd  新密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String changePwd() throws Exception {
		
		String acctId = Struts2Utils.getParameter("acctId");
		String oldPwd = Struts2Utils.getParameter("oldPwd");
		String newPwd = Struts2Utils.getParameter("newPwd");

		String md5OldPwd = md5PasswordEncoder.encodePassword(oldPwd, "");
		String md5NewPwd = md5PasswordEncoder.encodePassword(newPwd, "");

		entity = plasAcctManager.getEntity(acctId);

		if (!md5OldPwd.equals(entity.getLoginInPassword())) {
			Struts2Utils.renderText("error1");
			return null;
		}
		try {
			entity.setLoginInPassword(md5NewPwd);
			plasAcctManager.savePlasAcct(entity);

			OperResultVo vo = plasAcctManager.acctResetPwdValue(entity, newPwd, SpringSecurityUtils.getCurUiid(), "");
			if(vo!= null && vo.getSuccess()){
				//更新缓存用户的密码
				SpringSecurityUtils.getCurPlasAcct().setLoginInPassword(md5NewPwd);
				Struts2Utils.renderText("success");
			}else{
				Struts2Utils.renderText(vo == null?"":vo.getDesc());
			}
			return null;
		} catch (Exception e) {
			Struts2Utils.renderText("error");
			return null;
		}
	}

	/**
	 * Description:快速查询账号
	 * @param value 
	 * @param value
	 * 
	 */
	public void quickSearchAcctList() {
		String tmpName = Struts2Utils.getParameter("value").trim().toLowerCase();
		List<String> tmpOrgIdList = plasOrgManager.getMgrOrgIdList(DictContants.TREE_DIME_LOGICAL,SpringSecurityUtils.getCurUiid());
		List<PlasAcct> result = plasAcctManager.getFindAcctList(tmpName, "10",tmpOrgIdList);
		List<Map<String, String>> tmpList = new ArrayList<Map<String, String>>();

		// 增加根节点
		Map<String, String> map = new HashMap<String, String>(); 

		String tmpOrgName = null;
		String tmpCenterName = null;
		for (PlasAcct acct : result) {
			map = new HashMap<String, String>();
			map.put("uiid", acct.getUiid());
			map.put("userName", acct.getPlasUser().getUserName());
			map.put("custLoginName", acct.getCustLoginName());
			map.put("plasAcctId", acct.getPlasAcctId());
			map.put("statusCd", LocalCache.getAppDictName(DictContants.PLAS_USER_STATUS, acct.getStatusCd()));
			try{
				tmpOrgName = acct.getPlasUser().getPlasOrg().getOrgName();
				tmpCenterName = plasOrgManager.getCenterOrgNameByOrgId(acct.getPlasUser().getPlasOrg().getPlasOrgId());
			}catch(Exception e){
			}
			map.put("orgName", tmpOrgName);
			map.put("centerOrgName", tmpCenterName);
			tmpList.add(map);
		}
 
		Struts2Utils.renderJson(tmpList);
	}

	/**
	 * 精确查询已离职人员,前20条
	 * @param value 
	 * 
	 */
	public void quickSearchLeaveAcctList() {
		String tmpName = Struts2Utils.getParameter("value").toLowerCase();
		List<PlasAcct> result = plasAcctManager.getFindLeaveAcctList(tmpName, "20");
		List<Map<String, String>> tmpList = new ArrayList<Map<String, String>>();

		// 增加根节点
		Map<String, String> map = new HashMap<String, String>(); 

		String tmpOrgName = null;
		String tmpCenterName = null;
		for (PlasAcct acct : result) {
			map = new HashMap<String, String>();
			map.put("uiid", acct.getUiid());
			map.put("userName", acct.getPlasUser().getUserName());
			map.put("custLoginName", acct.getCustLoginName());
			map.put("plasAcctId", acct.getPlasAcctId());
			map.put("statusCd", LocalCache.getAppDictName(DictContants.PLAS_USER_STATUS, acct.getStatusCd()));
			try{
				tmpOrgName = acct.getPlasUser().getPlasOrg().getOrgName();
				tmpCenterName = plasOrgManager.getCenterOrgNameByOrgId(acct.getPlasUser().getPlasOrg().getPlasOrgId());
			}catch(Exception e){
			}
			map.put("orgName", tmpOrgName);
			map.put("centerOrgName", tmpCenterName);
			tmpList.add(map);
		}
 
		Struts2Utils.renderJson(tmpList);
	}
	
	
	/**
	 * 校验是否开通
	 * @param funcName 方法名
	 * @param plasAcctId 账号ID
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String validateOpen() throws Exception{
		
		String tmpFuncName = Struts2Utils.getParameter("funcName");
		String tmpPlasAcctId = Struts2Utils.getParameter("plasAcctId");
		
		PlasAcct acct = plasAcctManager.getEntity(tmpPlasAcctId);
		if(acct == null){
			Struts2Utils.renderText("未找到用户,请检查账号!");
			return null;
		}
		String uiid = acct.getUiid(); 
		boolean rtnFlg = false;
		
		
		if(rtnFlg){
			Struts2Utils.renderText("用户("+uiid+")已开通EAS");
			return null;
		}else{
			Struts2Utils.renderText("用户("+uiid+")未开通EAS");
			return null;
		}
	}

	/**
	 * 手动关联开通(eas/mysoft)
	 * @param funcName 方法名称
	 * @param plasAcctId  账号ID
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String markOpen() throws Exception{
		
		String tmpFuncName = Struts2Utils.getParameter("funcName");
		String tmpPlasAcctId = Struts2Utils.getParameter("plasAcctId");
		
		PlasAcct acct = plasAcctManager.getEntity(tmpPlasAcctId);
		String uiid = acct.getUiid();
		boolean bExistFlg = false;
		String moduleName = "";
		
		if(bExistFlg){
			if("eas".equals(tmpFuncName)){
				if(Util.easOpen(acct.getEasFlg())){
					Struts2Utils.renderText("对不起,用户("+uiid+")已关联EAS!");
					return null;
				}else{
					acct.setEasFlg(DictContants.PLAS_EAS_FLG_ENABLE);
					acct.setEasPasswordSetFlg(DictContants.PLAS_EAS_PASSWORD_SET_FLG_NO);
				}
			}
			else if("mysoft".equals(tmpFuncName)){
				if(Util.mysoftOpen(acct.getMysoftFlg())){
					Struts2Utils.renderText("对不起,用户("+uiid+")已关联mysoft!");
					return null;
				}else{
					acct.setMysoftFlg(DictContants.PLAS_MYSOFT_FLG_ENABLE);
					acct.setMysoftPasswordSetFlg(DictContants.PLAS_MYSOFT_PASSWORD_SET_FLG_NO);
				}
			}
			else if("email".equals(tmpFuncName)){
				if(Util.emailOpen(acct.getEmailFlg())){
					Struts2Utils.renderText("对不起,用户("+uiid+")已关联email!");
					return null;
				}else{
					acct.setMysoftFlg(DictContants.PLAS_MYSOFT_FLG_ENABLE);
					acct.setMysoftPasswordSetFlg(DictContants.PLAS_MYSOFT_PASSWORD_SET_FLG_NO);
				}
			}
			else if("cmail".equals(tmpFuncName)){
				if(Util.cmailOpen(acct.getCmailFlg())){
					Struts2Utils.renderText("对不起,用户("+uiid+")已关联coremail!");
					return null;
				}else{
					acct.setMysoftFlg(DictContants.PLAS_MYSOFT_FLG_ENABLE);
					acct.setMysoftPasswordSetFlg(DictContants.PLAS_MYSOFT_PASSWORD_SET_FLG_NO);
				}
			}
			
			plasAcctManager.savePlasAcct(acct);
			String sb2 = new StringBuffer("[").append(acct.getUiid()).append(",").append(acct.getPlasUser().getUserName()).append("]关联开通" + tmpFuncName + "!").toString();
			// 保存操作日志
			plasOperateLogManager.savePlasOperateLog(uiid, SpringSecurityUtils.getCurUserName(), moduleName, OperConst.EDT, sb2);
			
			Struts2Utils.renderText("success");
		}else{
			Struts2Utils.renderText("不存在,无法关联!");
		}
		return null;
	} 
	
	/**
	 * 查看账户信息
	 * @param uiid  账号
	 * 
	 * @throws Exception
	 */
	public String viewAcct(){
		String uiid = Struts2Utils.getParameter("uiid").trim();
		entity = plasAcctManager.getPlasAcctByUiid(uiid);
		return SUCCESS;
	}
	

	/**
	 * ajax 校验uiid是否存在
	 * 
	 * @param uiid
	 */
	public void isUiidExists() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String uiid = request.getParameter("uiid").trim();
		if( validateUiidExists(uiid)){
			Struts2Utils.renderText("true");
		}else{
			Struts2Utils.renderText("false");
		}
	}
	
	private boolean validateUiidExists(String uiid){
		if( plasAcctManager.getPlasAcctByUiid(uiid) != null)
			return true;
		else
			return false;
	}
	
	/**
	 * 开通账号
	 * 		1 人力资源管理权限：开通账号状态为审核通过
	 * 		2 项目公司管理权限：开通账号为为通过审核状态
	 * @param modeCd
	 * 
	 * @return
	 * @throws Exception 
	 */
	
	public String saveOpenAcct(){
		
		String tmpModeCd = Struts2Utils.getParameter("modeCd").trim();
		//开通账号,同时产生职位
		if("1".equals(tmpModeCd))
			return openAcctWithDefaultPos();
		else if ("2".equals(tmpModeCd))
			return openAcctNoDefaultPos();
		else
			return null;
		
		//开通账号,同时关联选择的职位(需经人力资源部确认生效)
		//return openAcctWithTmpPos();
	}
	
	//开通账号，无默认职位 或者有默认职位且必选账号类型
	public String openAcctNoDefaultPos(){

		String tmpUiid = Struts2Utils.getParameter("uiid").trim();
		String tmpUserId = Struts2Utils.getParameter("plasUserId");
		String tmpStatusCd = Struts2Utils.getParameter("statusCd");
		String tmpEffectDate = Struts2Utils.getParameter("effectDate");
		String tmpAcctTypeCd = Struts2Utils.getParameter("acctTypeCd");
		String tmpPosId = Struts2Utils.getParameter("posId");
		
		/*
		if(StringUtils.isBlank(tmpPosId)){
			Struts2Utils.renderText("请选择职位:" + tmpPosId);
			return null;
		}
		*/
		
		PlasUser tmpUser = plasUserManager.getEntity(tmpUserId);
		if( tmpUser == null){
			Struts2Utils.renderText("未找到人员信息:" + tmpUserId);
			return null;
		}
		
//		if( StringUtils.isBlank(posId)){
//			Struts2Utils.renderText("请选择职位!");
//			return null;
//		}
		if( StringUtils.isBlank(tmpStatusCd)){
			Struts2Utils.renderText("请选择账号状态!");
			return null;
		}
		
		if( validateUiidExists(tmpUiid)){
			Struts2Utils.renderText("账号重复!");
			return null;
		}else{

			tmpUser.setUiid(tmpUiid);//user对应最新的acct.uiid
			tmpUser.setUserTypeCd(tmpAcctTypeCd);
			plasUserManager.savePlasUser(tmpUser);

			
			PlasAcct acct = new PlasAcct();
			//设置密码,发送短信
			String oriPwd = appParamManager.getPassword();
			acct.setLoginInPassword(md5PasswordEncoder.encodePassword(oriPwd,""));
			acct.setUiid(tmpUiid);
			
			if(StringUtils.isNotBlank(tmpEffectDate)){
				acct.setEffectDate(DateOperator.parse(tmpEffectDate, "yyyy-MM-dd"));
			}
			acct.setStatusCd(tmpStatusCd);
			
			acct.setFailureTimes(new BigDecimal(0));
			
			acct.setEmailFlg(DictContants.PLAS_EMAIL_FLG_NOOPEN);
			acct.setEmailPasswordSetFlg(DictContants.PLAS_EMAIL_PASSWORD_SET_FLG_NO);
			
			acct.setEasFlg(DictContants.PLAS_EAS_FLG_NOOPEN);
			acct.setEasPasswordSetFlg(DictContants.PLAS_EAS_PASSWORD_SET_FLG_NO);
			
			acct.setMysoftFlg(DictContants.PLAS_MYSOFT_FLG_NOOPEN);
			acct.setMysoftPasswordSetFlg(DictContants.PLAS_SYN_MYSOFT_USER_NO);
			
			acct.setActiveBl(new Boolean(true));
			acct.setAcctSeqNo("0");//最后一个

			acct.setPlasUser(tmpUser);
			plasAcctManager.savePlasAcct(acct);
	
			boolean pdFlg = false;
			//普通用户,与职位关联
			if((DictContants.PLAS_USER_TYPE_1.equals(tmpAcctTypeCd)||DictContants.PLAS_USER_TYPE_2.equals(tmpAcctTypeCd)) && StringUtils.isNotBlank(tmpPosId)){

				// add by huangbijin 2011-09-02
				// 保存职位关联账号
				if(StringUtils.isNotBlank(tmpPosId)){
					PlasSysPosition pos = plasSysPositionManager.getEntity(tmpPosId);
					pos.setPlasAcct(acct);
					plasSysPositionManager.savePlasSysPosition(pos);
				}

				// 保存操作日志
				plasOperateLogManager.savePlasOperateLog(SpringSecurityUtils.getCurUiid(), SpringSecurityUtils.getCurUserName(), OperConst.ACCT, OperConst.ADD, 
						new StringBuffer().append("[").append(tmpUiid).append(",").append(tmpUser.getUserName()).append("] ")
						.append("开通账号! ")
						.append(StringUtils.isBlank(tmpPosId)?"无职位!":("建立职位与账号的关联!["+tmpPosId+"]"))
						.toString()
				);

				/* hidden by huangbijin 2011-09-02 默认长生职位
				PlasAcctPosRelTmp rel = null;
				PlasSysPosition pos = plasSysPositionManager.getEntity(tmpPosId);
				pos.setPlasAcct(acct);
				
				//临时表,待审核
				rel = new PlasAcctPosRelTmp();
				rel.setPlasSysPositionId(tmpPosId);
				
				//直接关联
				PlasSysPosRoleRel rel = new PlasSysPosRoleRel();
				rel.setPlasSysPosition(plasSysPositionManager.savePlasSysPosition(tmpUser,acct));
				rel.setPlasRole(plasRoleManager.getPlasRoleByRoleCd(GlobalConstants.A_USER));
				plasSysPosRoleRelManager.savePlasSysPosRoleRel(rel);
				
				// 保存操作日志
				plasOperateLogManager.savePlasOperateLog(SpringSecurityUtils.getCurUiid(), SpringSecurityUtils.getCurUserName(), OperConst.ACCT, OperConst.ADD, 
						new StringBuffer().append("[").append(tmpUiid).append(",").append(tmpUser.getUserName()).append("] 开通账号! 建立职位与普通用户关联!").toString()
				);
				 */
				
				
				pdFlg = true;
			}
			//邮箱用户,不与职位关联
			else if(DictContants.PLAS_USER_TYPE_3.equals(tmpAcctTypeCd)){
				
			}
			
			
			// 保存操作日志
			if(!pdFlg){
				plasOperateLogManager.savePlasOperateLog(SpringSecurityUtils.getCurUiid(), SpringSecurityUtils.getCurUserName(), OperConst.ACCT, OperConst.ADD, 
						new StringBuffer().append("[").append(tmpUiid).append(",").append(tmpUser.getUserName()).append("] 开通账号! 不关联角色!").toString()
				);
			}
			
			Struts2Utils.renderText("success");
			
			return null;
		}
	}
	
	//开通账号，将其与系统职位临时关联暂存(需确认)
	public String openAcctWithTmpPos(){
		String tmpUiid = Struts2Utils.getParameter("uiid").trim();
		String tmpUserId = Struts2Utils.getParameter("plasUserId").trim();
		String tmpStatusCd = Struts2Utils.getParameter("statusCd").trim();
		String tmpEffectDate = Struts2Utils.getParameter("effectDate").trim();
		String tmpAcctTypeCd = Struts2Utils.getParameter("acctTypeCd");
		String sysPosCd = Struts2Utils.getParameter("posId").trim();
		
		PlasUser tmpUser = plasUserManager.getEntity(tmpUserId);
		if(tmpUser == null){
			Struts2Utils.renderText("未找到人员信息:" + tmpUserId);
			return null;
		}
		
		if(validateUiidExists(tmpUiid)){
			Struts2Utils.renderText("账号重复!");
			return null;
		}else{

			tmpUser.setUiid(tmpUiid);//user对应最新的acct.uiid
			tmpUser.setUserTypeCd(tmpAcctTypeCd);
			
			PlasAcct acct = new PlasAcct();
			if(StringUtils.isNotBlank(tmpEffectDate)){
				acct.setEffectDate(DateOperator.parse(tmpEffectDate, "yyyy-MM-dd"));
			}
			acct.setUiid(tmpUiid);
			acct.setPlasUser(tmpUser);
			acct.setStatusCd(tmpStatusCd);
			OperResultVo result =plasAcctManager.acctCreate(acct,sysPosCd);
			if(result.getSuccess()){
				Struts2Utils.renderText("success");
			}else{
				Struts2Utils.renderText("请联系管理员");
			}
		}
		return null;
	}
	/**
	 * 开通账号，同时创建默认职位
	 * 更新表: 人员表/账号表/开通邮箱/关联角色(a. pd账号,则职位关联普通用户 b.邮箱用户,不关联角色)
	 * @param uiid
	 * @param plasUserId
	 * @param statusCd
	 * @param effectDate
	 * @param acctTypeCd
	 * 
	 * @return
	 */
	public String openAcctWithDefaultPos(){

		String tmpUiid = Struts2Utils.getParameter("uiid").trim();
		String tmpUserId = Struts2Utils.getParameter("plasUserId").trim();
		String tmpStatusCd = Struts2Utils.getParameter("statusCd").trim();
		String tmpEffectDate = Struts2Utils.getParameter("effectDate").trim();
		String tmpAcctTypeCd = Struts2Utils.getParameter("acctTypeCd");//管理员(选择)/项目机构管理员(默认pd)
		
		
		PlasUser tmpUser = plasUserManager.getEntity(tmpUserId);
		if(tmpUser == null){
			Struts2Utils.renderText("未找到人员信息:" + tmpUserId);
			return null;
		}
		
		if(validateUiidExists(tmpUiid)){
			Struts2Utils.renderText("账号重复!");
			return null;
		}else{

			tmpUser.setUiid(tmpUiid);//user对应最新的acct.uiid
			tmpUser.setUserTypeCd(tmpAcctTypeCd);
			
			PlasAcct acct = new PlasAcct();
			acct.setPlasUser(tmpUser);

			//设置密码,发送短信
			String oriPwd = appParamManager.getPassword();
			acct.setLoginInPassword(md5PasswordEncoder.encodePassword(oriPwd,""));
			acct.setUiid(tmpUiid);
			
			if(StringUtils.isNotBlank(tmpEffectDate)){
				acct.setEffectDate(DateOperator.parse(tmpEffectDate, "yyyy-MM-dd"));
			}

			if(StringUtils.isNotBlank(tmpStatusCd)){
				acct.setStatusCd(tmpStatusCd);
			}else{
				acct.setStatusCd(DictContants.PLAS_USER_STATUS_CREATE);//开通账号
			}
			acct.setFailureTimes(new BigDecimal(0));
			
			acct.setEmailFlg(DictContants.PLAS_EMAIL_FLG_NOOPEN);
			acct.setEmailPasswordSetFlg(DictContants.PLAS_EMAIL_PASSWORD_SET_FLG_NO);
			
			acct.setEasFlg(DictContants.PLAS_EAS_FLG_NOOPEN);
			acct.setEasPasswordSetFlg(DictContants.PLAS_EAS_PASSWORD_SET_FLG_NO);
			
			acct.setMysoftFlg(DictContants.PLAS_MYSOFT_FLG_NOOPEN);
			acct.setMysoftPasswordSetFlg(DictContants.PLAS_SYN_MYSOFT_USER_NO);
			
			acct.setCmailFlg(DictContants.CMAIL_FLG_NOOPEN);
			acct.setCmailPasswordSetFlg(DictContants.CMAIL_SYN_USER_NO);
			
			acct.setActiveBl(new Boolean(true));
			acct.setAcctSeqNo("0");//最后一个

			//将uiid保存至用户PlasUser
			plasUserManager.savePlasUser(tmpUser);
			plasAcctManager.savePlasAcct(acct);
			
			//若未入职,不发短信;否则,发送PD密码短信
			if((!DictContants.PLAS_USER_STATUS_NOENTER.equals(acct.getStatusCd())) && (!DictContants.PLAS_USER_STATUS_CLOSED.equals(acct.getStatusCd()))){
				SmsUtil.sendRandomPassword(acct.getUiid(), tmpUser.getUserName(), tmpUser.getSexCd(), tmpUser.getMobilePhone(), oriPwd, "PD");
			}
			
			//新账号,默认开通邮箱

			
			boolean pdFlg = false;
			//普通用户,与职位关联
			if((DictContants.PLAS_USER_TYPE_1.equals(tmpAcctTypeCd)||DictContants.PLAS_USER_TYPE_2.equals(tmpAcctTypeCd))){
				PlasSysPosRoleRel rel = new PlasSysPosRoleRel();
				rel.setPlasSysPosition(plasSysPositionManager.savePlasSysPosition(tmpUser,acct));
				rel.setPlasRole(plasRoleManager.getPlasRoleByRoleCd(GlobalConstants.A_USER));
				plasSysPosRoleRelManager.savePlasSysPosRoleRel(rel);
				// 保存操作日志
				plasOperateLogManager.savePlasOperateLog(SpringSecurityUtils.getCurUiid(), SpringSecurityUtils.getCurUserName(), OperConst.ACCT, OperConst.ADD, 
					new StringBuffer().append("[").append(tmpUiid).append(",").append(tmpUser.getUserName()).append("] 开通账号! 产生职位,并与普通用户关联!").toString()
				);
				pdFlg = true;
			}
			//邮箱用户,不与职位关联
			else if(DictContants.PLAS_USER_TYPE_3.equals(tmpAcctTypeCd)){
				
			}
			
			// 保存操作日志
			if(!pdFlg){
				plasOperateLogManager.savePlasOperateLog(SpringSecurityUtils.getCurUiid(), SpringSecurityUtils.getCurUserName(), OperConst.ACCT, OperConst.ADD, 
						new StringBuffer().append("[").append(tmpUiid).append(",").append(tmpUser.getUserName()).append("] 开通账号! 不关联角色!").toString()
				);
			}
			
			Struts2Utils.renderText("success");
			return null;
		}
	}
	
	/**
	 * 设置不要登录验证
	 * @param acctId 账号ID
	 * @return
	 */
	public String ignoreLoginValidate(){

		String tmpAcctId = Struts2Utils.getParameter("acctId").trim();
		boolean flag = plasAcctManager.ignoreLoginValidate(tmpAcctId);
		if(flag){
			Struts2Utils.renderText("success");
		}
		return null;
	}
	
	/**
	 * 同步邮箱至通讯录
	 * @param acctId 账号ID
	 * @return
	 */
	public String synEmailToContact(){
		String tmpAcctId = Struts2Utils.getParameter("acctId").trim();
		boolean flag = plasAcctManager.synEmailToContact(tmpAcctId);
		if(flag){
			Struts2Utils.renderText("success");
		}
		return null;
	}
	
	//查询职位
	public String searchPositionList(){
		String tmpAcctId = Struts2Utils.getParameter("acctId").trim();
		acctRelPosList = getRelPosList(tmpAcctId);
		return "positionList";
	}
	
	private List<VoSysPosition> getRelPosList(String acctId){
		return plasSysPositionManager.searchPositionListByAcctId(acctId);
	}

	public List<VoSysPosition> getAcctRelPosList() {
		return acctRelPosList;
	}

	public void setAcctRelPosList(List<VoSysPosition> acctRelPosList) {
		this.acctRelPosList = acctRelPosList;
	}
	
	/**
	 * 加载机构树
	 * @param acctId 账号ID
	 * @param isAllPosFlg 是否所有职位
	 * @return
	 */
	public String loadPositionTree(){

		String tmpAcctId = Struts2Utils.getParameter("acctId");
		String tmpIsAllPosFlg = Struts2Utils.getParameter("isAllPosFlg");
		List<String> checkedIdList = new ArrayList<String>();
		for (VoSysPosition pos : getRelPosList(tmpAcctId)) {
			checkedIdList.add(pos.getPlasSysPositionId());
		} ;
		List<VoOrg> voOrgList = plasDimeOrgRel.getVoOrgList(TreePanelUtil2.TREE_TYPE_LOGICAL, false);
		List<VoSysPosition> voPosList = plasSysPositionManager.searchPositionList(null,"1".equals(tmpIsAllPosFlg));
		Struts2Utils.renderJson(TreePanelUtil2.buildOrgPosTree(voOrgList, voPosList, checkedIdList));
		
		return null;
	}
	
	/**
	 * 保存账号与职位关系
	 * @param acctId 账号ID
	 * @param addIds 增加职位ID
	 * @param delIds 删除职位ID
	 * @return
	 */
	public String saveAcctPosRel(){
		
		String tmpAcctId = Struts2Utils.getParameter("acctId").trim();
		String tmpAddIds = Struts2Utils.getParameter("addIds").trim();
		String tmpDelIds = Struts2Utils.getParameter("delIds").trim();
	
		plasSysPositionManager.saveAcctPosRel(tmpAcctId, tmpAddIds, tmpDelIds);
		Struts2Utils.renderText("success");
		return null;
	}
	/**
	 * 待处理账号：
	 * 	1	由项目公司申请，未经人力资源部门审核确认
	 * 	2	未分配系统职位的账号
	 */
	public String waitingAcct(){
		return "approval";
	}
	/**
	 * 加载待审核账号所在机构树：
	 * @param statusCd 账号状态
	 * @param orgId 
	 * @param orgIds
	 * @param sort
	 * @param order
	 * @param page
	 * @param rows

	 */
	public void loadApprovalAcctList(){
		Page<VoAcct> tmpPage = null;
		String statusCd = Struts2Utils.getParameter("statusCd");
		String orgId = Struts2Utils.getParameter("orgId");
		String orgIds = Struts2Utils.getParameter("orgIds");
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("sequenceNo");
			page.setOrder( Page.ASC);
		} else {
			page.setOrderBy(sortField + ",sequenceNo");
			page.setOrder(order + "," + Page.ASC);
		}
		tmpPage = plasAcctManager.getVoWaitingAcctPage(page, orgIds,statusCd);
		JsonUtil.renderJson(tmpPage,new String[]{""});

	}
	public void approvalPass(){
		 plasAcctManager.acctAuditPass(getId());
	}
	public void approvalReject(){
		plasAcctManager.acctAuditReject(getId());
		
	}
	/**
	 * 加载待审核账号所在机构树：
	 */
	public void loadOrgWaiting(){
		String sysPosId = plasSysPositionManager.getSysPosListByUiid(SpringSecurityUtils.getCurUiid()).get(0).getPlasSysPositionId();
		Struts2Utils.renderJson(TreePanelUtil.buildOrgAcctApplyTree(sysPosId));
	}
	
	/**
	 * 获取账号列表
	 * @param uiid 账号
	 * Description
	 * author:jiaoxiaofeng  2011-6-14
	 * @return
	 * String
	 */
	public String getLikeAcctList(){
		String uiid = Struts2Utils.getParameter("uiid").trim();
		long l = plasAcctManager.getLikAcctListSize(uiid);
		if(l > 20){
			Struts2Utils.renderText("以 "+ uiid + " 打头的账号过多<br/>(建议在账号后加1,2,3……)");
			return null;
		}else{
			List<String> list = plasAcctManager.getLikAcctList(uiid);
			StringBuffer sb = new StringBuffer();
			
			for (String t : list) {
				sb.append(" "+t+"<br/>");
			}

			if(StringUtils.isNotBlank(sb.toString())){
				Struts2Utils.renderText("以下账号已使用,请错开命名.<br/>(建议在账号后加1,2,3...)<br/>"+sb.toString().substring(1));
			}
			return null;
		}
	}

	
	/**
	 * 修改账号
	 * @param oldUiid 旧账号
	 * @param newUiid 新账号
	 * @return
	 */
	public String chgUiid(){
		
		String oldUiid = Struts2Utils.getParameter("oldUiid").trim();
		String newUiid = Struts2Utils.getParameter("newUiid").trim();
		
		if(StringUtils.isBlank(oldUiid)){
			Struts2Utils.renderText("old uiid is empty!");
			return null;
		}
		if(StringUtils.isBlank(newUiid)){
			Struts2Utils.renderText("new uiid is empty!");
			return null;
		}
		
		PlasAcct tmpAcct = plasAcctManager.getPlasAcctByUiid(oldUiid);
		if(tmpAcct == null){
			Struts2Utils.renderText("no user founded by old uiid!"+oldUiid);
			return null;
		}
		PlasAcct tmpAcct2 = plasAcctManager.getPlasAcctByUiid(newUiid);
		if(tmpAcct2 != null){
			Struts2Utils.renderText("user founded by new uiid!"+newUiid +",please change another uiid!");
			return null;
		}
		
		//coremail是否可用
//		boolean tmpFlg = Util.cmailEnable(tmpAcct.getCmailFlg());
//		String newEmail = newUiid+"@powerlong";
		
		PlasUser tmpUser = tmpAcct.getPlasUser();
//		if(tmpFlg){
//			tmpUser.setEmail(newEmail);
//		}
		tmpUser.setUiid(newUiid);
		plasUserManager.savePlasUser(tmpUser);
		tmpUser.setEmail(null);
		
//		if(tmpFlg){
//			tmpAcct.setEmail(newEmail);
//		}
		tmpAcct.setUiid(newUiid);
		tmpAcct.setEmail(null);
		tmpAcct.setCmailFlg("0");//1-未同步
		tmpAcct.setCmailPasswordSetFlg("0");//0-未同步
		
		plasAcctManager.savePlasAcct(tmpAcct);
		Struts2Utils.renderText("success");
		return null;
	}
}

