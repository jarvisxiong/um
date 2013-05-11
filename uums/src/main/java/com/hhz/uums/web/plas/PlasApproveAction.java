package com.hhz.uums.web.plas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.RandomUtils;
import com.hhz.uums.dao.app.AppAttachFileManager;
import com.hhz.uums.dao.plas.PlasAcctManager;
import com.hhz.uums.dao.plas.PlasApproveInfoManager;
import com.hhz.uums.dao.plas.PlasOperateLogManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.dao.plas.PlasSysPositionManager;
import com.hhz.uums.dao.plas.PlasUserManager;
import com.hhz.uums.entity.app.AppAttachFile;
import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasApproveInfo;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.utils.Constants;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.RoleUtil;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.vo.log.LogPlasUser;
import com.hhz.uums.vo.log.LogUtil;
import com.hhz.uums.vo.vw.OperResultVo;
import com.hhz.uums.vo.vw.TreePanelNode;
import com.hhz.uums.vo.vw.VoSysPosition;
import com.hhz.uums.vo.ws.WsPlasRole;
import com.hhz.uums.web.CrudActionSupport;

/**
 * @author huangbijin 

 */
@Results({
	@Result(name = "reloadUserMove", location = "plas-approve!userMove.action", type = "redirect", params = {"plasAcctId","${plasAcctId}"}) })

public class PlasApproveAction extends CrudActionSupport<PlasApproveInfo> {

	private static final long serialVersionUID = 5895758805155007729L;
	private static final Log log = LogFactory.getLog(PlasApproveAction.class);
	
	@Autowired
	private PlasApproveInfoManager plasApproveInfoManager;
	@Autowired
	private PlasUserManager plasUserManager;
	@Autowired
	private PlasAcctManager plasAcctManager;
	@Autowired
	private PlasOrgManager plasOrgManager;
	@Autowired
	private PlasSysPositionManager plasSysPositionManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private PlasRoleManager plasRoleManager;
	@Autowired
	private PlasOperateLogManager plasOperateLogManager;
	
	
	
	//编辑用户信息
	private String posId;
	private String plasAcctId;
	private String plasUserId;
	
	private PlasApproveInfo entity;
	
	private String entityTmpId;
	
	//新旧中心
	private String oldCenterOrgName;
	private String newCenterOrgName;

	//职位列表(旧)
	private List<VoSysPosition> acctRelPosListOld;
	//职位列表(新)
	private List<VoSysPosition> acctRelPosList;
	
	
	//实体旧值
	private LogPlasUser logOldUser;
	
	// 照片列表
	private List<AppAttachFile> picAttas;
	
	//系统职位IDs
	private String sysPositionIds;
	private String oldPosNames;
	
	//正在走流程的记录数
	private List<PlasApproveInfo> processingList;
	
	//是否所辖范围的用户  1-是 0/空-否
	private String isUnderCtrlFlg ="1";
	
	
	//是否允许修改更多信息 1-禁止 0/空-可编辑
	private String isForbidModifyMoreFlg = "0";
	
	
	@Override
	public PlasApproveInfo getModel() {
		return entity;
	}
	

	@Override
	protected void prepareModel() throws Exception {

		if (StringUtils.isNotBlank(getId())) {
			
			entity = plasApproveInfoManager.getEntity(getId());
			
			// 获取新、旧职位
			acctRelPosList = getRelPosList(entity.getNewSysPosIds());
			acctRelPosListOld = getRelPosList(entity.getOldSysPosIds());
			
			String tmpCd = entity.getApproveStatusCd();
			//1-未提交,4-驳回
			if(DictContants.APPROVE_STATUS_CREATE.equals(tmpCd) || DictContants.APPROVE_STATUS_REJECT.equals(tmpCd)){
				if(DictContants.APPROVE_APPLY_TYPE_CREATE.equals(entity.getApplyTypeCd())){
					
				}else{
					//补回plasUiid
					String tUiid = entity.getNewUiid();
					PlasAcct tmpAcct = plasAcctManager.getPlasAcctByUiid(tUiid);
					if(tmpAcct!=null){
						plasAcctId = tmpAcct.getPlasAcctId();
						plasUserId = tmpAcct.getPlasUser().getPlasUserId();
						
						//是否禁止修改更多
						isForbidModifyMoreFlg = isForbidModifyMore(tmpAcct.getPlasUser().getPermissionLevelCd());
					}
				}
			}
		} else { 
			entity = new PlasApproveInfo();
			entity.setEffectDate(DateOperator.truncDate(new Date()));//默认当天生效
			entity.setApplyTypeCd(DictContants.APPROVE_APPLY_TYPE_CREATE);//1-新增
			entity.setApproveStatusCd(DictContants.APPROVE_STATUS_CREATE);//1-未提交
			
			entity.setEffectDate(new Date());//默认今天生效
			// 临时变量
			entityTmpId = RandomUtils.generateTmpEntityId();
		}
		 
		
		//很重要
		if(StringUtils.isNotBlank(entity.getOldParentId())){
			isUnderCtrlFlg = isUnder(entity.getOldParentId());
		}

		oldCenterOrgName = processCenterName(entity.getOldParentId());
		newCenterOrgName = processCenterName(entity.getNewParentId());
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void prepareInput() throws Exception {
		prepareModel();
	} 
	@Override
	public String input() throws Exception {
		return INPUT;
	}
	public void prepareView() throws Exception {
		prepareModel();
	} 
	 
	public String view() throws Exception {
		return "view";
	}
	
	@Override
	public void prepareSave() throws Exception {
		prepareModel();
	}
	@Override
	public String save() throws Exception {
		
		try{
			commonSave();
			Struts2Utils.renderText("success," + entity.getPlasApproveInfoId());
		}catch (Exception e) {
			Struts2Utils.renderText(e.getMessage());
			log.error("保存用户管理申请异常!", e);
		}
		return null;
	}
	
	/**
	 * 保存申请信息
	 * 步骤如下
	 * 1.若机构变化,则检查是否有新职位存在新机构下;自动移除旧机构下的职位；
	 * 2.获取对应的流程和申请角色
	 * 3.保存申请信息
	 * 4.更新上传图片与申请的关联(在新增用户的情况下)
	 * @throws Exception
	 */
	private void commonSave() throws Exception{
 
		//调整前所在机构下属的职位，删除
		String tmpNewOrgId = entity.getNewParentId();
		String tmpOldOrgId = entity.getOldParentId();

		if(StringUtils.isNotBlank(tmpNewOrgId) && StringUtils.isNotBlank(tmpOldOrgId)){
			//机构调动
			if(!tmpNewOrgId.equals(tmpOldOrgId)){
				String tmpNewPosIds = entity.getNewSysPosIds();
				if(StringUtils.isNotBlank(tmpNewPosIds)){
					
					StringBuffer sb = new StringBuffer();
					PlasSysPosition tmpNewPos = null;
					String[] arr = tmpNewPosIds.split(",");
					for (String tNewPosId : arr) {
						if(StringUtils.isNotBlank(tNewPosId)){
							tmpNewPos = plasSysPositionManager.getEntity(tNewPosId);
							//若新职位挂在旧机构下,则移除
							if(!tmpOldOrgId.equals(tmpNewPos.getPlasOrg().getPlasOrgId())){
								sb.append(tNewPosId).append(",");
							}
						}
					}
//						System.out.println(">>>>>>>>>>>>>.11 " + entity.getNewSysPosIds());
//						System.out.println(">>>>>>>>>>>>>.22 " + sb.toString());
					entity.setNewSysPosIds(sb.toString());
				}
			}
		}

		//保存信息(必须在更新照片前)
		plasApproveInfoManager.saveOrSubmit(entity);
		
		//更新照片
		if(StringUtils.isNotBlank(entityTmpId)){
			appAttachFileManager.updateTmpFile(entityTmpId, entity.getPlasApproveInfoId());
		}
	}
	
	
	/**
	 * 保存关键申请信息
	 * @throws Exception
	 */
	public void prepareSaveKeyFields() throws Exception{
		prepareModel();
	}
	public String saveKeyFields() throws Exception{

		try{
			commonSave();
			Struts2Utils.renderText("success," + entity.getPlasApproveInfoId());
		}catch (Exception e) {
			Struts2Utils.renderText(e.getMessage());
			log.error("保存用户管理申请异常!", e);
		}
		return null;
	}
	
	/**
	 * 保存非关键申请信息
	 */
	public void prepareSaveComFields(){
		entity = new PlasApproveInfo();
	}
	public String saveComFields(){
		
		PlasUser newUser = plasUserManager.getEntity(getId());

		LogPlasUser oldUser = LogUtil.transfer(newUser);

		newUser.setSexCd(entity.getSexCd());
		newUser.setBirthday(entity.getBirthday());
		newUser.setIdno(entity.getIdno());
		newUser.setNationCd(entity.getNationCd());
		newUser.setSequenceNo(entity.getSequenceNo());
		newUser.setNativeProvinceDesc(entity.getNationCd());
		newUser.setNativePlaceDesc(entity.getNativePlaceDesc());
		newUser.setMarrigeStatusCd(entity.getMarrigeStatusCd());
		newUser.setSchoolRecordCd(entity.getSchoolRecordCd());
		newUser.setGradSchoolDesc(entity.getGradSchoolDesc());
		newUser.setFixedPhone(entity.getFixedPhone());
		newUser.setMajorDesc(entity.getMajorDesc());
		newUser.setMobilePhone(entity.getMobilePhone());
		newUser.setMobilePhone2(entity.getMobilePhone2());
		newUser.setAttendWorkDate(entity.getAttendWorkDate());
		newUser.setIdCardTypeCd(entity.getIdCardTypeCd());
		newUser.setMemberTypeCd(entity.getMemberTypeCd());
		newUser.setResponsibility(entity.getResponsibility());
		newUser.setSpecialUserFlg(entity.getSpecialUserFlg());
		newUser.setUserTypeCd(entity.getUserTypeCd());
		newUser.setProfessionTypeCd(entity.getProfessionTypeCd());
		newUser.setPoliticsCd(entity.getPoliticsCd());
		newUser.setSourceTypeCd(entity.getSourceTypeCd());
		newUser.setDefaultCredenc(entity.getDefaultCredenc());
		newUser.setOtherTypeCd(entity.getOtherTypeCd());
		
		plasUserManager.savePlasUser(newUser);

		//************************** 记录日志 开始 ********************
		String tmpLog = LogUtil.getOperateUser(false, oldUser, newUser);
		if(StringUtils.isNotBlank(tmpLog)){
			String tmpUiid = SpringSecurityUtils.getCurUiid();
			String tmpName = SpringSecurityUtils.getCurUserName();
			plasOperateLogManager.savePlasOperateLog(tmpUiid, tmpName, "用户管理", "修改", "["+ newUser.getUiid()+","+newUser.getUserName()+"]"+tmpLog);
		}
		//************************** 记录日志 结束 ********************
		
		
		//更新系统职位的序号
		List<PlasSysPosition> lst = plasSysPositionManager.getSysPosListByUiid(newUser.getUiid());
		for (PlasSysPosition t : lst) {
			//非兼职,系统职位序号同人员序号
			if(t.getOutStatFlg() == null || (!t.getOutStatFlg().booleanValue())){
//				t.setSequenceNo(newUser.getSequenceNo());//2012-05-07 要求不同步人员序号 非常重要
				plasSysPositionManager.savePlasSysPosition(t);
			}
		}
		
		
		Struts2Utils.renderText("success");
		return null;
	}
	
	/**
	 * 查看结果
	 */
	public void prepareResult(){
		entity = plasApproveInfoManager.getEntity(getId());	
	}
	public String result(){
		return "result";
	}

	@Override
	public String delete() throws Exception {
		return null;
	}

	//获取中心名称
	private String processCenterName(String tmpOrgId){
		if(StringUtils.isNotBlank(tmpOrgId)){
			List<PlasOrg> tmpOrgList = plasOrgManager.getBubbleOrgsByOrgId(tmpOrgId, TreePanelUtil2.TREE_TYPE_PHYSICAL);
			if(tmpOrgList != null && tmpOrgList.size() > 0){
				for (PlasOrg eOrg : tmpOrgList) {
					if(DictContants.PLAS_ORG_TYPE_CENTER.equals(eOrg.getOrgTypeCd()))
						return eOrg.getOrgName();
				}
			}
		}
		return "";
	} 
	
	/**
	 * 职务树: 加载当前用户具有权限的人事树(所辖范围)
	 */
	public void loadHrOrgUser() {
		String tmpIsPosFlg = Struts2Utils.getParameter("isPosFlg");
		TreePanelNode node = getMyCtrlTree(TreePanelUtil2.getRootTreeNodeOrg("职务树"),"true".equals(tmpIsPosFlg));
		//移除其他邮箱
		TreePanelUtil2.refreshMoveOrgOtherEmailGroup(node);
		//正常编制/有效编制
//		TreePanelUtil2.refreshPosOrg(node);

		//是显示
		TreePanelUtil2.refreshVisable(node,true);
		
		Struts2Utils.renderJson(node);
	}

	/**
	 * 职位树: 获取系统职位树  
	 */
	public void loadHrPosTree() {
		TreePanelNode node = TreePanelUtil2.getTreeNodePanelHrPos( SpringSecurityUtils.getCurUiid(),false);
		//移除其他邮箱
		TreePanelUtil2.refreshMoveOrgOtherEmailGroup(node);
		//正常编制/有效编制
		TreePanelUtil2.refreshPosOrg(node);
		
		//移除不显示的系统职位节点(不管是否管理员，都不显示)
		TreePanelUtil2.refreshVisable(node, false);
		
		Struts2Utils.renderJson(node);
	}
	
	/**
	 * 加载集团人事树(用于调入时选择人员)
	 */
	public void loadOrgUserEnableTreePhysical() {
		String tmpIsPosFlg = Struts2Utils.getParameter("isPosFlg");

		TreePanelNode node = TreePanelUtil2.getTreeNodePanelUserPhysicalAll("admin", "true".equals(tmpIsPosFlg),true);
		//移除其他邮箱
		TreePanelUtil2.refreshMoveOrgOtherEmailGroup(node);
		Struts2Utils.renderJson(node);
	}
	/**
	 * 获取有权限的人事树
	 * @param isPosFlg 是否含职位  true-是  false-否
	 * @return
	 */
	private TreePanelNode getMyCtrlTree(TreePanelNode rootNode, boolean isPosFlg){
		return TreePanelUtil2.getTreeNodePanelHr(rootNode,SpringSecurityUtils.getCurUiid(), isPosFlg);
	}
	
	/**
	 * 加载当前用户具有权限的人事机构树(所辖范围)
	 * @return
	 */
	public void loadHrOrg(){
		TreePanelNode node = TreePanelUtil2.getTreeNodePanelHrOrg(TreePanelUtil2.getRootTreeNodeOrg("宝龙集团"), SpringSecurityUtils.getCurUiid());
		//移除其他邮箱
		TreePanelUtil2.refreshMoveOrgOtherEmailGroup(node);
		Struts2Utils.renderJson(node);
	}

	public String getEntityTmpId() {
		return entityTmpId;
	}

	public void setEntityTmpId(String entityTmpId) {
		this.entityTmpId = entityTmpId;
	}
 
	public LogPlasUser getLogOldUser() {
		return logOldUser;
	}

	public void setLogOldUser(LogPlasUser logOldUser) {
		this.logOldUser = logOldUser;
	}
 
	public List<VoSysPosition> getAcctRelPosList() {
		return acctRelPosList;
	}

	public void setAcctRelPosList(List<VoSysPosition> acctRelPosList) {
		this.acctRelPosList = acctRelPosList;
	}

	/**
	 * 检查身份证号码(暂不使用)
	 * @return
	 */
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
	 * 校验uiid是否已存在 true-存在  false-不存在
	 * @param uiid
	 */
	public void isUiidExists() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String tmpUiid = request.getParameter("uiid").trim();
		String tPlasApproveId = request.getParameter("plasApproveInfoId");
		String tAcctStatusCd = request.getParameter("acctStatusCd");

		if(DictContants.PLAS_USER_STATUS_CLOSED.equals(tAcctStatusCd)){
			Struts2Utils.renderText("false");
			return;
		}
		
		PlasAcct acct = plasAcctManager.getPlasAcctByUiid(tmpUiid);
		if(acct != null){
//			if(DictContants.PLAS_USER_STATUS_CLOSED.equals(acct.getStatusCd())){
//				Struts2Utils.renderText("false");
//				return;
//			}
			
			//不管是否注销,都不允许使用
			Struts2Utils.renderText("true");
			return;
		}
		
		if( validateUiidExists(tmpUiid, tPlasApproveId)){
			Struts2Utils.renderText("true");
		}else{
			Struts2Utils.renderText("false");
		}
	}
	
	private boolean validateUiidExists(String uiid,String tPlasApproveId){
		if( plasAcctManager.getPlasAcctByUiid(uiid) != null)
			return true;
		else{
			if(plasApproveInfoManager.getPlasApproveInfoByUiid(uiid,tPlasApproveId)!= null)
				return true;
			else
				return false;
		}
	}

	/**
	 * 获取相似的账号列表
	 * @return
	 * String
	 */
	public String getLikeAcctList(){
		String uiid = Struts2Utils.getParameter("uiid").trim();
		
		//正式,帐号信息表
		long l = plasAcctManager.getLikAcctListSize(uiid);
		if(l > 0){
			if(l > 20){
				Struts2Utils.renderText("以 "+ uiid + " 打头的账号过多<br/>(建议在账号后加1,2,3……)");
			}else{
				List<String> list = plasAcctManager.getLikAcctList(uiid);
				StringBuffer sb = new StringBuffer();
				
				for (String t : list) {
					sb.append(" "+t+"<br/>");
				}
				
				if(StringUtils.isNotBlank(sb.toString())){
					Struts2Utils.renderText("以下账号已使用,请错开命名.<br/>(建议在账号后加1,2,3...)<br/>"+sb.toString().substring(1));
				}
			}
		}

		//临时,申请表(所有申请类型和申请状态,需要根据实际情况修改)
		long l2 = plasApproveInfoManager.getLikAcctListSize(uiid);
		if(l2 > 0){
			if(l2 > 20){
				Struts2Utils.renderText("以 "+ uiid + " 打头的账号过多<br/>(建议在账号后加1,2,3……)");
			}else{
				List<String> list = plasApproveInfoManager.getLikAcctList(uiid);
				StringBuffer sb = new StringBuffer();
				
				for (String t : list) {
					sb.append(" "+t+"<br/>");
				}
				
				if(StringUtils.isNotBlank(sb.toString())){
					Struts2Utils.renderText("以下账号已使用,请错开命名.<br/>(建议在账号后加1,2,3...)<br/>"+sb.toString().substring(1));
				}
			}
		}
		
		return null;
	}

	/**
	 * 获取照片清单
	 * @param id: 可能是plasUserId, plasApproveInfoId
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

	public String getSysPositionIds() {
		return sysPositionIds;
	}

	public void setSysPositionIds(String sysPositionIds) {
		this.sysPositionIds = sysPositionIds;
	}
 
	/**
	 * 提交操作申请信息,针对3类
	 * @param acct  
	 * @param applyTypeCd 申请类型(其中:3-冻结 4-解冻 5-注销)
	 * @param contentDesc 申请事由
	 * @return
	 * @throws Exception
	 */
	private PlasApproveInfo applyOperate(PlasAcct acct, String applyTypeCd, String contentDesc) throws Exception {
		

		PlasUser user = acct.getPlasUser();
		PlasOrg org = user.getPlasOrg(); 

		PlasApproveInfo info = new PlasApproveInfo();
 
		
		String posIds = getPosIds(user.getUiid());
		
		info.setNewUiid(user.getUiid());
		info.setNewName(user.getUserName());
		
		info.setNewParentId(org.getPlasOrgId());
		info.setNewParentName(org.getOrgName());
		info.setNewLevelCd(user.getPermissionLevelCd());
		info.setNewWorkDuty(user.getWorkDutyDesc());
		info.setNewSysPosIds(posIds);
		
		info.setOldParentId(org.getPlasOrgId());
		info.setOldParentName(org.getOrgName());
		info.setOldLevelCd(user.getPermissionLevelCd());
		info.setOldWorkDuty(user.getWorkDutyDesc());
		info.setOldSysPosIds(posIds);
		
		info.setContentDesc(contentDesc);
		info.setApplyTypeCd(applyTypeCd);
		info.setApproveStatusCd(DictContants.APPROVE_STATUS_PROCESS);
		
		plasApproveInfoManager.saveOrSubmit(info);
		
		return info;
	}
	
	/**
	 * 获取所有职位的ID,用逗号隔开 
	 * 例如：xxxx,yyyy,zzz,若无则为空("")
	 * @param uiid
	 * @return
	 */
	private String getPosIds(String uiid){
		StringBuffer sbIds = new StringBuffer();
		List<PlasSysPosition> tmpList = plasSysPositionManager.getSysPosListByUiid(uiid);
		if(tmpList != null && tmpList.size() > 0){
			for (PlasSysPosition tmp : tmpList) {
				sbIds.append(tmp.getPlasSysPositionId()).append(",");
			}
		}
		return sbIds.toString();
	}


	public String getPlasAcctId() {
		return plasAcctId;
	}


	public void setPlasAcctId(String plasAcctId) {
		this.plasAcctId = plasAcctId;
	}
	
	/**
	 * 查看用户信息(账号信息、用户信息、职位信息)
	 * @param plasUseId 必须传入plasUseId
	 * @throws Exception 
	 */
	public void prepareUserMove() throws Exception{
		PlasUser user = null;
		PlasAcct acct = null;
		
		if(StringUtils.isNotBlank(plasUserId)){
			user = plasUserManager.getEntity(plasUserId);
			acct = plasAcctManager.getPlasAcctByUiid(user.getUiid());
			plasAcctId = acct.getPlasAcctId();
			entity = getConvert(acct,user);
			processingList = plasApproveInfoManager.getProcessingList(entity.getNewUiid());
		}
		
		else if(StringUtils.isNotBlank(plasAcctId)){
			acct = plasAcctManager.getEntity(plasAcctId);
			user = acct.getPlasUser();
			plasUserId = user.getPlasUserId();
			entity = getConvert(acct,user);
			processingList = plasApproveInfoManager.getProcessingList(entity.getNewUiid());
		}
		

		//旧职位
		acctRelPosListOld = getRelPosList(entity.getOldSysPosIds());
		
		//查询是否所辖机构
		if(StringUtils.isNotBlank(plasUserId)){
			isUnderCtrlFlg = isUnder(user.getPlasOrg().getPlasOrgId());
		}
		
		//是否允许编辑更多(副总裁以上的只有 总管理员/hr管理员/总部人事可以编辑)
		if(StringUtils.isNotBlank(plasUserId)){		
			isForbidModifyMoreFlg = isForbidModifyMore(user.getPermissionLevelCd());
		}

		oldCenterOrgName = processCenterName(entity.getOldParentId());
		newCenterOrgName = processCenterName(entity.getNewParentId());
	}
	
	/**
	 * 是否允许编辑更多(副总裁以上的只有 总管理员/hr管理员/总部人事可以编辑)
	 * @param plasUserId
	 * @return
	 */
	private String isForbidModifyMore(String permissionLevelCd){
		
		if(DictContants.PERM_ZC.equals(permissionLevelCd) || DictContants.PERM_FZC.equals(permissionLevelCd)){
			List<WsPlasRole> roleList = plasRoleManager.getWsUserRoles(GlobalConstants.UAAP_BIZ_CD, SpringSecurityUtils.getCurUiid());
			if(RoleUtil.isHrAdmin(roleList) || RoleUtil.isHrBizCenter(roleList) || RoleUtil.isHrGroup(roleList))
				return "0";
			return "1";
		}
		return "0";
	}
	//userMode
	public String userMove(){
		
		return INPUT;
	}
	
	/**
	 * 系统职位，逗号隔开
	 * @param plasSysPositionIds
	 * @return
	 */
	private List<VoSysPosition> getRelPosList(String plasSysPositionIds){
		if(StringUtils.isNotBlank(plasSysPositionIds)){
			String[] tmpArr = plasSysPositionIds.split(",");
			if(tmpArr.length > 0){
				List<VoSysPosition> list = plasSysPositionManager.searchPositionListByIds(tmpArr);
				for (VoSysPosition vo : list) {
					vo.setOrgName(bubleName(vo.getOrgId()));
				}
				return list;
			}
		}
		return new ArrayList<VoSysPosition>();
	}
	
	/**
	 * 获取有权调动范围
	 * @param orgId
	 * @return
	 */
	private String isUnder(String orgId){
		
		if(StringUtils.isBlank(orgId))
			return "1";
		
		TreePanelNode rootNode = getMyCtrlTree(TreePanelUtil2.getRootTreeNodeOrg("机构人员树"), false);
		if(TreePanelUtil2.isContainNode(orgId, rootNode))
			return "1";
		else
			return "0";
		
//		try{
//			if(RoleUtil.isAdmin() || RoleUtil.isAdminSupser())
//				return "1";
//			else{
//				//遍历路径
//				List<PlasOrg> list = plasOrgManager.getBubbleOrgsByOrgId(orgId, TreePanelUtil2.TREE_TYPE_PHYSICAL);
//				
//				//获取所辖机构
//				List<String> rtnList = plasOrgManager.getMgrOrgIdList(TreePanelUtil2.TREE_TYPE_PHYSICAL, SpringSecurityUtils.getCurUiid());
//				
//				for (PlasOrg tOrg : list) {
//					if(rtnList.contains(tOrg.getPlasOrgId()))
//						return "1";
//				}
//			}
//			return "0";
//		}catch(Exception e){
//			return "0";
//		}
	}
	
	/**
	 * 将账号、用户信息转为前台显示的实体
	 * @param acct
	 * @param user
	 * @return
	 */
	private PlasApproveInfo getConvert(PlasAcct acct,PlasUser user) {
		
		PlasApproveInfo info = new PlasApproveInfo();
		PlasOrg org = user.getPlasOrg();
		
		//获取系统职位
		StringBuffer sbIds = new StringBuffer();
		StringBuffer sbNames = new StringBuffer();
		List<PlasSysPosition> tmpList = plasSysPositionManager.getSysPosListByUiid(acct.getUiid());
		if(tmpList != null && tmpList.size() > 0){
			acctRelPosList = new ArrayList<VoSysPosition>();
			VoSysPosition tmpVo = null;
			for (PlasSysPosition tmp : tmpList) {
				sbIds.append(tmp.getPlasSysPositionId()).append(",");
				sbNames.append(tmp.getSysPosName()).append(",");
				tmpVo = new VoSysPosition();
				tmpVo.setPlasSysPositionId(tmp.getPlasSysPositionId());
//				tmpVo.setOrgName(tmp.getPlasOrg().getOrgName());
				tmpVo.setOrgName(bubleName(tmp.getPlasOrg().getPlasOrgId()));
				tmpVo.setSysPosName(tmp.getSysPosName());
				tmpVo.setSysPosCd(tmp.getSysPosCd());
				acctRelPosList.add(tmpVo);
			}
		}

		if(DictContants.PLAS_USER_STATUS_CLOSED.equals(acct.getStatusCd())){
			info.setApplyTypeCd(DictContants.APPROVE_APPLY_TYPE_ENABLE);
		}else{
			info.setApplyTypeCd(DictContants.APPROVE_APPLY_TYPE_TRANSFER);//移动
		}
	     info.setNewUiid(acct.getUiid());
	     info.setNewName(user.getUserName());
	     
	     info.setNewLevelCd(user.getPermissionLevelCd());
	     info.setNewParentId(org.getPlasOrgId());
	     info.setNewParentName(org.getOrgName());
	     info.setNewWorkDuty(user.getWorkDutyDesc());
	     info.setNewSysPosIds(sbIds.toString());

	     info.setOldLevelCd(user.getPermissionLevelCd());
	     info.setOldParentId(org.getPlasOrgId());
	     info.setOldParentName(org.getOrgName());
	     info.setOldWorkDuty(user.getWorkDutyDesc());
	     info.setOldSysPosIds(sbIds.toString());
	     oldPosNames = sbNames.toString();
	     
	     info.setContentDesc("");
	     info.setApproveRoleCd("");//无类型
	     info.setApproveStatusCd(DictContants.APPROVE_STATUS_CREATE);
	     info.setActiveBl(new Boolean(true));
	     info.setAcctStatusCd(acct.getStatusCd());
	     info.setEffectDate(acct.getEffectDate());
	     
//	     以下字段,新建使用
		info.setSexCd(user.getSexCd());
		info.setBirthday(user.getBirthday());
		info.setIdno(user.getIdno());
		info.setNationCd(user.getNationCd());
		info.setSequenceNo(user.getSequenceNo());
		info.setNativeProvinceDesc(user.getNationCd());
		info.setActiveBl(new Boolean(true));
		info.setNativePlaceDesc(user.getNativePlaceDesc());
		info.setMarrigeStatusCd(user.getMarrigeStatusCd());
		info.setSchoolRecordCd(user.getSchoolRecordCd());
//			user.setEmail(user.get);//开通邮箱
		info.setGradSchoolDesc(user.getGradSchoolDesc());
		info.setFixedPhone(user.getFixedPhone());
		info.setMajorDesc(user.getMajorDesc());
		info.setMobilePhone(user.getMobilePhone());
		info.setMobilePhone2(user.getMobilePhone2());
		info.setAttendWorkDate(user.getAttendWorkDate());
		info.setIdCardTypeCd(user.getIdCardTypeCd());
		info.setMemberTypeCd(user.getMemberTypeCd());
		info.setResponsibility(user.getResponsibility());
		info.setSpecialUserFlg(user.getSpecialUserFlg());
		info.setUserTypeCd(user.getUserTypeCd());
		info.setProfessionTypeCd(user.getProfessionTypeCd());
		info.setPoliticsCd(user.getPoliticsCd());
		info.setSourceTypeCd(user.getSourceTypeCd());
		info.setDefaultCredenc(user.getDefaultCredenc());
		info.setOtherTypeCd(user.getOtherTypeCd());
		
		
		
		return info;
	}



	/**
	 * 提交操作申请信息,针对3类(其中:3-冻结 4-解冻 5-注销)
	 * 
	 * @param plasAcctId
	 * @param funcName (acctFreeze-冻结/acctUnFreeze-解冻/acctEnable-启用)这三类需要提交申请 /acctResetPassword-重置密码(立即生效)
	 * @param contentDesc
	 * @throws Exception
	 */
	public String commonProcess() throws Exception{

		try{
			//操作账号
			String plasAcctId = Struts2Utils.getParameter("plasAcctId").trim();
			//方法名称
			String funcName = Struts2Utils.getParameter("funcName");
			//申请事由
			String contentDesc = Struts2Utils.getParameter("contentDesc");
			
			PlasAcct acct = plasAcctManager.getEntity(plasAcctId);
			
			if (acct == null){
				log.error("未找到账号![" + plasAcctId + "]");
				Struts2Utils.renderText("未找到账号");
	//			throw new Exception("未找到账号![" + plasAcctId + "]");
				return null;
			}
			
			PlasApproveInfo info = null;
			//账号(冻结/生效/注销)   //重置密码
			if("acctFreeze".equals(funcName)){
				info = applyOperate(acct, DictContants.APPROVE_APPLY_TYPE_FREEZE, contentDesc);
			}
			else if("acctUnFreeze".equals(funcName)){
				info = applyOperate(acct, DictContants.APPROVE_APPLY_TYPE_UNFREEZE, contentDesc);
			}
			else if("acctClose".equals(funcName)){
				info = applyOperate(acct, DictContants.APPROVE_APPLY_TYPE_CLOSED, contentDesc);
			}
			else if("acctEnable".equals(funcName)){
				info = applyOperate(acct, DictContants.APPROVE_APPLY_TYPE_ENABLE, contentDesc);
			}
			else if("acctResetPassword".equals(funcName)){
				OperResultVo vo = plasAcctManager.acctOperate(plasAcctId, PlasAcctManager.ACCT_RESET_PWD);
				if(vo.getSuccess()){
					Struts2Utils.renderText("success");
					return null;
				}else{
					Struts2Utils.renderText("重置失败!");
					return null;
				}
			}
			else{
				Struts2Utils.renderText("操作类型未知!");
				log.error("操作类型未知![" + funcName + "]");
				//throw new Exception("操作类型未知![" + funcName + "]");
				return null;
			}
			
			Struts2Utils.renderText("success," + info.getPlasApproveInfoId());
			
		}catch(Exception e){
			Struts2Utils.renderText(e.getMessage());
		}
		return null;
	}


	public String getPlasUserId() {
		return plasUserId;
	}


	public void setPlasUserId(String plasUserId) {
		this.plasUserId = plasUserId;
	}


	public String getCurrentUiid() {
		return  SpringSecurityUtils.getCurUiid();
	}

	/**
	 * 加载空人员的职位员树
	 * @param acctId
	 * @param orgId
	 * @return
	 */
	public String loadEmptyPositionTree(){

		String tmpAcctId = null;//Struts2Utils.getParameter("acctId");
		String tmpOrgId = Struts2Utils.getParameter("orgId");
		Struts2Utils.renderJson(TreePanelUtil2.getTreeNodePanelHrPosEmpty(SpringSecurityUtils.getCurUiid(), tmpAcctId, tmpOrgId));
		
		return null;
	}


	public String getOldPosNames() {
		return oldPosNames;
	}


	public void setOldPosNames(String oldPosNames) {
		this.oldPosNames = oldPosNames;
	}

	public String getProcessingCount(){
		if(processingList == null)
			return "0";
		else
			return String.valueOf(processingList.size());
	}


	public String getIsUnderCtrlFlg() {
		return isUnderCtrlFlg;
	}


	public void setIsUnderCtrlFlg(String isUnderCtrlFlg) {
		this.isUnderCtrlFlg = isUnderCtrlFlg;
	}
	
	/**
	 * 校验机构和职位关系
	 * 
	 * 1.在保存关键信息前，若新旧机构不一样,则调用
	 * 2.在新增人员信息前，调用 
	 * @return
	 */
	public String validatePosPosRel(){

		String newOrgId = Struts2Utils.getParameter("newOrgId");
		String posIds = Struts2Utils.getParameter("posIds");
		
		if(StringUtils.isBlank(newOrgId)){
			Struts2Utils.renderText("请选择部门!");
			return null;
		}
		
		if(StringUtils.isBlank(posIds)){
			Struts2Utils.renderText("请选择职位!");
			return null;
		}
		
		String[] arrIds = posIds.split(",");
		PlasSysPosition tPos = null;
		for (String tPosId : arrIds) {
			if(StringUtils.isNotBlank(tPosId)){
				tPos = plasSysPositionManager.getEntity(tPosId);
				if(newOrgId.equals(tPos.getPlasOrg().getPlasOrgId())){
					Struts2Utils.renderText("success");
					return null;
				}
			}
		}
//		PlasOrg tmpOrg = plasOrgManager.getEntity(newOrgId);
//		String orgName = tmpOrg.getOrgName();
//		Struts2Utils.renderText("您未选择部门: "+orgName+" 下的职位!");
		Struts2Utils.renderText("请选择 部门 下的职位!");
		return null;
	}


	public List<VoSysPosition> getAcctRelPosListOld() {
		return acctRelPosListOld;
	}


	public void setAcctRelPosListOld(List<VoSysPosition> acctRelPosListOld) {
		this.acctRelPosListOld = acctRelPosListOld;
	}
	

	/**
	 * 点击空职位，用于新增人员
	 */
	public void preparePosMove(){
		//注意：这个代码在前
		PlasSysPosition tmpSysPos = plasSysPositionManager.getEntity(posId);
		PlasOrg tmpOrg = tmpSysPos.getPlasOrg();
		PlasAcct tmpAcct = tmpSysPos.getPlasAcct();
		if(tmpAcct != null){
			setPlasAcctId(tmpAcct.getPlasAcctId());
			//跳到人员编辑
		}else{
			//初始化申请状态，申请类型,上级部门，部门名称，默认职位
			entity = new PlasApproveInfo();
			entity.setApproveStatusCd(DictContants.APPROVE_STATUS_CREATE);
			entity.setApplyTypeCd(DictContants.APPROVE_APPLY_TYPE_CREATE);
			entity.setNewParentId(tmpOrg == null ? "" : tmpOrg.getPlasOrgId());
			entity.setNewParentName(tmpOrg == null ? "" : tmpOrg.getOrgName());
			//设置
			newCenterOrgName = processCenterName(entity.getNewParentId());
	
			entity.setNewSysPosIds(posId+",");
			acctRelPosList = getRelPosList(entity.getNewSysPosIds());
		}
	}
	public String posMove(){
		
		if(StringUtils.isNotBlank(getPlasAcctId()))
			return "reloadUserMove";
		else
			return INPUT;
	}


	public String getPosId() {
		return posId;
	}


	public void setPosId(String posId) {
		this.posId = posId;
	}

	public String bubleName(String orgId){
		StringBuffer sb = new StringBuffer();
		List<PlasOrg>  list = plasOrgManager.getBubbleOrgsPhysical(orgId);
		for(int i=(list.size()-1); i>=0; i--){
			sb.append(list.get(i).getOrgName());
			if(i >0){
				sb.append(" -> ");
			}
		}
		return sb.toString();
	}

	public String getBubNewOrgName() {
//		return bubNewOrgName;
		if(entity != null){
			if(StringUtils.isNotBlank(entity.getNewParentId()))
				return bubleName(entity.getNewParentId());
		}
		return "";
	}


	public String getBubOldOrgName() {
//		return bubOldOrgName;
		if(entity != null){
			if(StringUtils.isNotBlank(entity.getOldParentId()))
				return bubleName(entity.getOldParentId());
		}
		return "";
	}


	public String getIsForbidModifyMoreFlg() {
		return isForbidModifyMoreFlg;
	}


	public void setIsForbidModifyMoreFlg(String isForbidModifyMoreFlg) {
		this.isForbidModifyMoreFlg = isForbidModifyMoreFlg;
	}


	public String getOldCenterOrgName() {
		return oldCenterOrgName;
	}


	public void setOldCenterOrgName(String oldCenterOrgName) {
		this.oldCenterOrgName = oldCenterOrgName;
	}


	public String getNewCenterOrgName() {
		return newCenterOrgName;
	}


	public void setNewCenterOrgName(String newCenterOrgName) {
		this.newCenterOrgName = newCenterOrgName;
	}
	
}
