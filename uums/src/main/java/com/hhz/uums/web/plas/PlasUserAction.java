package com.hhz.uums.web.plas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.RandomUtils;
import com.hhz.uums.cache.LocalCache;
import com.hhz.uums.dao.app.AppAttachFileManager;
import com.hhz.uums.dao.app.AppSeqManager;
import com.hhz.uums.dao.plas.PlasAcctManager;
import com.hhz.uums.dao.plas.PlasAcctPosRelTmpManager;
import com.hhz.uums.dao.plas.PlasOperateLogManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.dao.plas.PlasSysPositionManager;
import com.hhz.uums.dao.plas.PlasUserManager;
import com.hhz.uums.entity.app.AppAttachFile;
import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasAcctPosRelTmp;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.utils.Constants;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.DictMapUtil;
import com.hhz.uums.utils.EasyTree;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.OperConst;
import com.hhz.uums.utils.RoleUtil;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.vo.log.LogPlasUser;
import com.hhz.uums.vo.log.LogUtil;
import com.hhz.uums.vo.ws.WsPlasAcct;
import com.hhz.uums.web.CrudActionSupport;

/**
 * @author huangbijin 

 */
public class PlasUserAction extends CrudActionSupport<PlasUser> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private PlasOperateLogManager logManager;
	@Autowired
	private PlasUserManager plasUserManager;
	@Autowired
	private PlasOrgManager plasOrgManager;
	@Autowired
	private PlasAcctManager plasAcctManager;
	@Autowired
	private PlasSysPositionManager plasSysPositionManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private AppSeqManager appSeqManager;

	@Autowired
	private AppAttachFileManager attachManager;
	@Autowired
	private PlasAcctPosRelTmpManager plasAcctPosRelTmpManager;

	private PlasUser entity;

	// 保存用户信息使用
	private String plasOrgId;

	private String entityTmpId;

	// 机构名称
	 private String centerOrgName;
	// 实际职位
	private String realPosName;

	// 照片列表
	private List<AppAttachFile> picAttas;
	
	// 人员明细模式  默认"input",区分action请求来源
	private String viewMode;
	
	// 上级中心名称
	private String parentCenterOrgName;
	
	private String todayYyyymmdd;
	//账号状态：未开通、未审核、待启、正常、冻结、销户
	private String acctStatus;
	
	
	//是否admin才允许离职/注销/调动 ,1-是 其他-否
	private String isUnderCtrlFlag;
	private static String UNDER_CTRL_FLAG_Y = "1";
	
	
	//更新日志使用
	private LogPlasUser oldUser;
	
	
	@Override
	public PlasUser getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		
		if (StringUtils.isNotBlank(getId())) {
			
			entity = plasUserManager.getEntity(getId());
			//realPosName = plasRealPositionManager.getPositionName(entity.getPoliticsCd());

			isUnderCtrlFlag = RoleUtil.isUnderCtrlFlg(entity.getPlasOrg().getPlasOrgId())?UNDER_CTRL_FLAG_Y:null;
			
		} else {
			entity = new PlasUser();
			entity.setPlasAccts(new ArrayList<PlasAcct>());
			entity.setSourceTypeCd(DictContants.PLAS_SOURCE_TYPE_CD_0);//默认:管理员手工添加
			//entity.setPermissionLevelCd(DictContants.PLAS_PROFESSION_TYPE_6);//默认:专员
			entity.setServiceStatusCd(DictContants.PLAS_SERVICE_STATUS_ON);//默认：在职
			entity.setActiveBl(new Boolean(true));//默认：可用
			entity.setSequenceNo(new BigDecimal(0));//默认：0
//			entity.setSexCd(DictContants.PLAS_SEX_UNKNOWN);
			entity.setUserTypeCd(DictContants.PLAS_USER_TYPE_1);//内部员工
			
			
			if(StringUtils.isNotBlank(getPlasOrgId())){
				PlasOrg tmpOrg = plasOrgManager.getEntity(getPlasOrgId());
				if(tmpOrg != null){
					entity.setPlasOrg(tmpOrg);
				}
			}else{
				entity.setPlasOrg(new PlasOrg());
			}

			// 临时变量
			entityTmpId = RandomUtils.generateTmpEntityId();
		}
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String delete() throws Exception {
//		try {
//			//plasUserManager.deletePlasUser(getId());
//			//JsonUtil.renderSuccess("删除成功");
//		} catch (ServiceException e) {
//			logger.error(e.getMessage(), e);
//			JsonUtil.renderFailure("删除失败");
//		}
		return null;
	}

	@Override
	public String list() throws Exception {
		return null;
	}

	@Override
	public void prepareInput() throws Exception {
		prepareModel();
		processCenterName();
		processAcctStatus();
	}
	private void processCenterName(){
		//获取中心
		PlasOrg tmpOrg = entity.getPlasOrg();
		if(tmpOrg!= null){
			String tmpOrgId = tmpOrg.getPlasOrgId();
			if(StringUtils.isNotBlank(tmpOrgId)){
				List<PlasOrg> tmpOrgList = plasOrgManager.getBubbleOrgsByOrgId(tmpOrgId, TreePanelUtil2.TREE_TYPE_LOGICAL);
				if(tmpOrgList != null){
					for (PlasOrg eOrg : tmpOrgList) {
						if(DictContants.PLAS_ORG_TYPE_CENTER.equals(eOrg.getOrgTypeCd())){
							parentCenterOrgName = eOrg.getOrgName();
						}
					}
				}
			}
		}
	}
	private void processAcctStatus(){
		List<PlasAcct> accts = entity.getPlasAccts();
		if(null!=accts && accts.size()>0){
			PlasAcct acct =accts.get(0);
			PlasAcctPosRelTmp tmp =plasAcctPosRelTmpManager.getEntityByAcctId(acct.getPlasAcctId());
			acctStatus = tmp==null?"":tmp.getStatusCd();
			if(StringUtils.isBlank(acctStatus)
					||DictContants.PLAS_ACCT_AUDIT_YES.equals(acctStatus)){
				acctStatus = DictMapUtil.getMapAcctStatus(acct.getStatusCd());
			}else{
				acctStatus = DictMapUtil.getMapAcctAuditStatus(acctStatus);
			}
		}else{
			acctStatus = "";
			
		}
	}
	@Override
	public String input() throws Exception {
		viewMode = "input";
		return "input";
	}

	@Override
	public void prepareSave() throws Exception {
		prepareModel();
		if(StringUtils.isNotBlank(getId())){
			//记录日志、事务一致,参考PlasUserManager.updateUser()
			// 日志使用,旧用户信息
			oldUser = LogUtil.transfer(entity);
		}
	}

	@Override
	public String save() throws Exception {
		
		//天哪，这一步很重要
		if(StringUtils.isBlank(entity.getUserCd())){
			entity.setUserCd(appSeqManager.createNextValue(GlobalConstants.SEQ_USER_CD).toString());
		}

		// 记录操作日志
		if(StringUtils.isNotBlank(getId())){
			String desc = LogUtil.getOperateUser(false, oldUser, entity);
			if (StringUtils.isNotBlank(desc)) {
				WsPlasAcct acct = SpringSecurityUtils.getCurPlasAcct();
				String operUiid = acct.getUiid();
				String operName = acct.getUserName();
				logManager.savePlasOperateLog(operUiid, operName, OperConst.USR, OperConst.EDT,
						new StringBuffer("[").append(entity.getUiid()).append(",").append(entity.getUserName())
								.append("]保存用户信息成功!").append(desc).toString());
			}
		}
		plasUserManager.savePlasUser(entity);
		
		//返回id
		Struts2Utils.renderText(entity.getPlasUserId());
		return null;
	}

	
	public String saveBatch() {

//		List<PlasUser> insertedRecords = (List<PlasUser>) JsonUtil.getInsertRecords(PlasUser.class);
//		List<PlasUser> updatedRecords = (List<PlasUser>) JsonUtil.getUpdatedRecords(PlasUser.class);
//		List<PlasUser> deletedRecords = (List<PlasUser>) JsonUtil.getDeletedRecords(PlasUser.class);
//		plasUserManager.saveOrUpdatePlasUsers(insertedRecords, updatedRecords, deletedRecords);
//		JsonUtil.renderSuccess("保存成功");
		return null;
	}


	/**
	 * 物理机构人员树(单选)
	 */
	public void loadOrgUserTreePhysical() {
		String tmpIsPosFlg = Struts2Utils.getParameter("isPosFlg");
		Struts2Utils.renderJson(TreePanelUtil2.getTreeNodePanelUserPhysicalAll( SpringSecurityUtils.getCurUiid(),"true".equals(tmpIsPosFlg)));
	}

	/**
	 * 逻辑机构人员树(单选)
	 */
	public void loadOrgUserTreeLogical() {
		String tmpIsPosFlg = Struts2Utils.getParameter("isPosFlg");
		Struts2Utils.renderJson(TreePanelUtil2.getTreeNodePanelUserLogicalAll( SpringSecurityUtils.getCurUiid(),"true".equals(tmpIsPosFlg)));
	}
	
	/**
	 * 物理机构人员树(复选)
	 */
	public void loadOrgUserTreePhysicalM() {
		Struts2Utils.renderJson(TreePanelUtil2.getTreeNodePanelUserPhysicalAllM( SpringSecurityUtils.getCurUiid()));
	}

	/**
	 * 逻辑机构人员树(复选)
	 */
	public void loadOrgUserTreeLogicalM() {
		Struts2Utils.renderJson(TreePanelUtil2.getTreeNodePanelUserLogicalAllM( SpringSecurityUtils.getCurUiid()));
	}

	public void createUserTree(EasyTree tree) {
		// 得到机构用户
		List<PlasUser> userList = plasUserManager.getPlasUserByOrg(tree.getId());
		// 新增用户结点
		if (userList != null) {
			for (PlasUser user : userList) {
				EasyTree childTree = new EasyTree();
				childTree.setId(user.getPlasUserId());
				childTree.setText(user.getUserName());
				childTree.setAttributes("user");
				tree.addChild(childTree);
			}
		}
		if (tree.getChildren() != null && tree.getChildren().size() > 0) {
			// 到孩子结点里面寻找机构用户
			for (EasyTree easyTree : tree.getChildren()) {
				if (easyTree.getAttributes() != null && !"user".equals(easyTree.getAttributes())) {
					createUserTree(easyTree);
				}
			}
		}
	}

	/**
	 * Description:快速查询账号 
	 * @param value 
	 * @param dimeCd 
	 */
	public void quickSearchUserList() {
		String tmpName = Struts2Utils.getParameter("value").toLowerCase();
		String dimeCd = Struts2Utils.getParameter("dimeCd");
		if(StringUtils.isBlank(dimeCd)){
			dimeCd = TreePanelUtil2.TREE_TYPE_PHYSICAL;
		}
		List<String> tmps = plasOrgManager.getMgrOrgIdList(dimeCd,SpringSecurityUtils.getCurUiid());
		List<PlasUser> result = plasUserManager.getFindUserList(tmpName, "10",tmps);
		List<Map<String, String>> tmpList = new ArrayList<Map<String, String>>();

		// 增加根节点
		Map<String, String> map = new HashMap<String, String>();
		String tmpOrgName = null;
		String tmpCenterName = null;
		for (PlasUser user : result) {
			map = new HashMap<String, String>();
			map.put("uiid", StringUtils.isBlank(user.getUiid())?"(未开通)":user.getUiid());
			map.put("userName", user.getUserName());
			map.put("plasUserId", user.getPlasUserId());
			map.put("serviceStatusCd", LocalCache.getAppDictName(DictContants.PLAS_SERVICE_STATUS, user.getServiceStatusCd()));
			try{
				tmpOrgName = user.getPlasOrg().getOrgName();
				tmpCenterName = plasOrgManager.getCenterOrgNameByOrgId(user.getPlasOrg().getPlasOrgId());
			}catch(Exception e){
			}
			map.put("orgName", tmpOrgName);
			map.put("centerOrgName", tmpCenterName);
			tmpList.add(map);
		}

		Struts2Utils.renderJson(tmpList);
	}

	/**
	 * 精确查询用户名,密码(查找在职人员)
	 */
	public void quickUserList() {
		String tmpName = Struts2Utils.getParameter("value").toLowerCase();
		List<PlasUser> result = plasUserManager.getFindUserList(tmpName, "10");
		List<Map<String, String>> tmpList = new ArrayList<Map<String, String>>();

		// 增加根节点
		Map<String, String> map = new HashMap<String, String>();
		String tmpOrgName = null;
		String tmpCenterName = null;
		for (PlasUser user : result) {
			map = new HashMap<String, String>();
			map.put("uiid", StringUtils.isBlank(user.getUiid())?"(未开通)":user.getUiid());
			map.put("userName", user.getUserName());
			map.put("plasUserId", user.getPlasUserId());
			map.put("serviceStatusCd", LocalCache.getAppDictName(DictContants.PLAS_SERVICE_STATUS, user.getServiceStatusCd(), false));//false-不显示代码
			try{
				tmpOrgName = user.getPlasOrg().getOrgName();
				tmpCenterName = plasOrgManager.getCenterOrgNameByOrgId(user.getPlasOrg().getPlasOrgId());
			}catch(Exception e){
			}
			map.put("orgName", tmpOrgName);
			map.put("centerOrgName", StringUtils.isBlank(tmpCenterName)?"":("["+tmpCenterName+"]"));
			tmpList.add(map);
		}

		Struts2Utils.renderJson(tmpList);
	}
	
	
	/**
	 *
	 * 查询所有用户列表
	 * 
	 */
	public void quickSearchUserAllList() {
		String tmpName = Struts2Utils.getParameter("value").toLowerCase();
		String dimeCd = Struts2Utils.getParameter("dimeCd");
		List<String> tmps = plasOrgManager.getMgrOrgIdList(dimeCd,SpringSecurityUtils.getCurUiid());
		List<PlasUser> result = plasUserManager.getFindUserList(tmpName, "10",tmps);
		List<Map<String, String>> tmpList = new ArrayList<Map<String, String>>();

		// 增加根节点
		Map<String, String> map = new HashMap<String, String>();
		String tmpOrgName = null;
		String tmpCenterName = null;
		for (PlasUser user : result) {
			map = new HashMap<String, String>();
			map.put("uiid", StringUtils.isBlank(user.getUiid())?"(未开通)":user.getUiid());
			map.put("userName", user.getUserName());
			map.put("plasUserId", user.getPlasUserId());
			map.put("serviceStatusCd", LocalCache.getAppDictName(DictContants.PLAS_SERVICE_STATUS, user.getServiceStatusCd()));
			try{
				tmpOrgName = user.getPlasOrg().getOrgName();
				tmpCenterName = plasOrgManager.getCenterOrgNameByOrgId(user.getPlasOrg().getPlasOrgId());
			}catch(Exception e){
			}
			map.put("orgName", tmpOrgName);
			map.put("centerOrgName", tmpCenterName);
			tmpList.add(map);
		}

		Struts2Utils.renderJson(tmpList);
	}
	
	
	public String getPlasOrgId() {
		return plasOrgId;
	}

	public void setPlasOrgId(String plasOrgId) {
		this.plasOrgId = plasOrgId;
	}
  
	 public String getCenterOrgName() {
	 return centerOrgName;
	 }
	
	 public void setCenterOrgName(String centerOrgName) {
	 this.centerOrgName = centerOrgName;
	 } 

	public String getEntityTmpId() {
		return entityTmpId;
	}

	public void setEntityTmpId(String entityTmpId) {
		this.entityTmpId = entityTmpId;
	}

	public String getRealPosName() {
		return realPosName;
	}

	public void setRealPosName(String realPosName) {
		this.realPosName = realPosName;
	}

	/**
	 * 获取照片清单
	 * 
	 * @return
	 */
	public String pics() {
		String tmpId = Struts2Utils.getParameter("id").trim();
		if (StringUtils.isNotBlank(tmpId)) {
			picAttas = appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(tmpId, Constants.APP_ATTACH_FILE_USER);
		}else{
			picAttas = new ArrayList<AppAttachFile>();
		}
		return "pics";
	}

	public List<AppAttachFile> getPicAttas() {
		return picAttas;
	}

	public void setPicAttas(List<AppAttachFile> picAttas) {
		this.picAttas = picAttas;
	}

	/**
	 * 开通账号
	 * 
	 * @return
	 * @throws Exception 
	 */
	public void prepareOpenAcct() throws Exception{
		prepareModel();
		todayYyyymmdd = DateOperator.formatDate(new Date(), "yyyy-MM-dd");
	}
	public String openAcct(){
		return "openAcct";
	}
		
	
	/**
	 * 保存上级机构ID
	 * @param orgId
	 * @param userId
	 * 
	 * @return
	 */
	public String saveParentPhysicalOrgId(){
		String tmpUserId = Struts2Utils.getParameter("userId").trim();
		String tmpOrgId = Struts2Utils.getParameter("orgId").trim();
		
		saveUserMove(tmpUserId, tmpOrgId);
		Struts2Utils.renderText("success");
		
		return null;
	}
	private boolean saveUserMove(String tmpUserId, String tmpOrgId){
		
		if(StringUtils.isBlank(tmpUserId) || StringUtils.isBlank(tmpOrgId))
			return false;

		PlasUser tmpUser = plasUserManager.getEntity(tmpUserId);
		
		try{
			if(tmpOrgId.equals(tmpUser.getPlasOrg().getPlasOrgId()))
				return false;
		}catch (Exception e) {
			
		}
		
		plasUserManager.updateParentOrgId(tmpOrgId, tmpUserId);
		
		//不动职位 hidden by huangbijin 2011-09-02
		//PlasOrg tmpOrg = plasOrgManager.getEntity(tmpOrgId);
		//replaceSysPosOrg(tmpUser,tmpOrg);
		return true;
	}
	//若用户调动，查看是否需要级联调动系统职位。若其只对应一个系统职位，则级联调动系统职位
	private void replaceSysPosOrg(PlasUser tmpUser,PlasOrg tmpOrg){
		List<PlasAcct> tmpAccts = tmpUser.getPlasAccts();
		if(null!=tmpAccts && tmpAccts.size()>0){
			List<PlasSysPosition> tmpSysPoss = tmpAccts.get(0).getPlasSysPositions();
			if(null!= tmpSysPoss && tmpSysPoss.size()==1){
				//调动系统职位所在机构
				PlasSysPosition tmpSysPos = tmpSysPoss.get(0);
				tmpSysPos.setPlasOrg(tmpOrg);
				plasSysPositionManager.redeployPos(tmpSysPos);
			}
		}
	}
	/**
	 * 查看个人信息
	 * @return
	 * @throws Exception 
	 */
	public void prepareMy() throws Exception{
		setId(SpringSecurityUtils.getCurPlasUserId());
		prepareModel();
		processCenterName();
	}
	public String my(){
		viewMode = "my";
		return "my";
	}
	
	/**
	 * 离职
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public String leaveCompany(){
		plasUserManager.leaveCompany(getId());
		Struts2Utils.renderText("success");
		return null;
	}

	public String getViewMode() {
		return viewMode;
	}

	public void setViewMode(String viewMode) {
		this.viewMode = viewMode;
	}
	
	/**
	 * 图片路径
	 * @return
	 */
	public String getPicPath(){
		String userId = Struts2Utils.getParameter("userId").trim();
		Struts2Utils.renderText(attachManager.getPicturePath(userId));
		return null;
	}

	public String getParentCenterOrgName() {
		return parentCenterOrgName;
	}

	public void setParentCenterOrgName(String parentCenterOrgName) {
		this.parentCenterOrgName = parentCenterOrgName;
	}

	//本日
	public String getTodayYyyymmdd() {
		return todayYyyymmdd;
	}

	public void setTodayYyyymmdd(String todayYyyymmdd) {
		this.todayYyyymmdd = todayYyyymmdd;
	}
	
	
	public String getAcctStatus() {
		return acctStatus;
	}

	public void setAcctStatus(String acctStatus) {
		this.acctStatus = acctStatus;
	}

	//检查身份证号码
	public String validateIdno(){

		HttpServletRequest request = ServletActionContext.getRequest();
		String userId = request.getParameter("userId").trim();
		String idno = request.getParameter("idno").trim();
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("userId", userId);
		values.put("idno", idno);
		
		List<PlasUser> list = plasUserManager.queryResult(values);
		if(list == null || list.size() == 0){
			Struts2Utils.renderText("success");
		}else{
			StringBuffer sb = new StringBuffer();
			for (PlasUser user : list) {
				sb.append(user.getUserName());
			}
			Struts2Utils.renderText(sb.toString());
		}
		
		return null;
	}
	
	/**
	 * 批量调动人员
	 * @return
	 */
	public String batchAdjust(){
		return "batchAdjust";
	}
	/**
	 * 保存批量调动列表
	 * 
	 * @return
	 */
	public String saveBatchAdjust(){
		
		String tmpOrgId = Struts2Utils.getParameter("orgId");
		String tmpAddIds = Struts2Utils.getParameter("addIds");
		
		String[] addIdList = StringUtils.split(tmpAddIds,",");
		for (int i = 0; i < addIdList.length; i++) {
			if(StringUtils.isNotBlank(addIdList[i])){
				saveUserMove(addIdList[i], tmpOrgId);
			}
		}
		
		Struts2Utils.renderText("success");
		return null;
	}
	/**
	 * 设置策略
	 * 
	 * @return
	 */
	public String saveChgPwdStrategy(){
		
		String tmpPwdStrategyCd = Struts2Utils.getParameter("pwdStrategyCd");
		String tmpAddIds = Struts2Utils.getParameter("addIds");
		
		String[] addIdList = StringUtils.split(tmpAddIds,",");
		plasAcctManager.saveChgPwdStrategy(tmpPwdStrategyCd, addIdList);
		
		Struts2Utils.renderText("success");
		return null;
	}
	
	
	/**
	 * 验证手机号码
	 * @param plasUserId
	 * 
	 * @return
	 */
	public String validateMobilePhone(){
		String tPlasUserId = Struts2Utils.getParameter("plasUserId");
		if(StringUtils.isBlank(tPlasUserId)){
			Struts2Utils.renderText("传入参数不对!");
			return null;
		}else{
			PlasUser t = plasUserManager.getEntity(tPlasUserId);
			if(t== null){
				Struts2Utils.renderText("未找到职员信息!");
				return null;
			}else{
				if(StringUtils.isBlank(t.getMobilePhone())){
					Struts2Utils.renderText("未填写手机号码，请确认!");
					return null;
				}else{
					if(t.getMobilePhone().trim().length() == 11){
						Struts2Utils.renderText("success");
						return null;
					}else{
						Struts2Utils.renderText("手机号码长度不对，请确认!");
						return null;
					}
				}
			}
		}
	}

	public String getIsUnderCtrlFlag() {
		return isUnderCtrlFlag;
	}

	public void setIsUnderCtrlFlag(String isUnderCtrlFlag) {
		this.isUnderCtrlFlag = isUnderCtrlFlag;
	}

	
	/**
	 * 更新员工的中心字段
	 * @return
	 */
	public String refreshUserCenter(){
		String rtnText = plasUserManager.refreshUserCenter();
		Struts2Utils.renderText(rtnText);
		return null;
	}
	/**
	 * 删除人员
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public String delUserById(){
		String userId = Struts2Utils.getParameter("userId");
		entity = plasUserManager.getEntity(userId);
		
		PlasAcct tmpAcct = plasAcctManager.getPlasAcctByUiid(entity.getUiid());
		if(tmpAcct == null){
			entity.setActiveBl(new Boolean(false));
			plasUserManager.savePlasUser(entity);
			Struts2Utils.renderText("success");
		}else{
			Struts2Utils.renderText("已开通账号,不能删除!");
		}
		return null;
	}

	public LogPlasUser getOldUser() {
		return oldUser;
	}

	public void setOldUser(LogPlasUser oldUser) {
		this.oldUser = oldUser;
	}
	
	
}
