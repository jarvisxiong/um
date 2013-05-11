package com.hhz.uums.web.plas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.plas.PlasApproveInfoManager;
import com.hhz.uums.dao.plas.PlasApproveNodeArchManager;
import com.hhz.uums.dao.plas.PlasApproveNodeHisManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.dao.plas.PlasSysPositionManager;
import com.hhz.uums.entity.plas.PlasApproveInfo;
import com.hhz.uums.entity.plas.PlasApproveNodeArch;
import com.hhz.uums.entity.plas.PlasApproveNodeHis;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.RoleUtil;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.vo.vw.VoSysPosition;
import com.hhz.uums.vo.ws.WsPlasRole;
import com.hhz.uums.web.CrudActionSupport;

public class ApproveAction extends CrudActionSupport<PlasApproveInfo> {

	private static final long serialVersionUID = -3828257037815588394L;

	private static Log log = LogFactory.getLog(ApproveAction.class);
	
	@Autowired
	private PlasApproveInfoManager plasApproveInfoManager;
	@Autowired
	private PlasApproveNodeHisManager plasApproveNodeHisManager;
	@Autowired
	private PlasApproveNodeArchManager plasApproveNodeArchManager;
	
	
	@Autowired
	private PlasSysPositionManager plasSysPositionManager;
	@Autowired
	private PlasOrgManager plasOrgManager;
	@Autowired
	private PlasRoleManager plasRoleManager;

	// 申请类型
	public static Map<String, String> applyTypeMap;
	// 审批状态
	public static Map<String, String> approveStatusMap;

	static {
		applyTypeMap = new HashMap<String, String>();
		applyTypeMap.put("新增", "1");
		applyTypeMap.put("调动", "2");
		applyTypeMap.put("冻结", "3");
		applyTypeMap.put("解冻", "4");
		applyTypeMap.put("离职", "5");
		applyTypeMap.put("启用", "6");

		approveStatusMap = new HashMap<String, String>();
		approveStatusMap.put("未提交", "1");
		approveStatusMap.put("审批", "2");
		approveStatusMap.put("完成", "3");
		approveStatusMap.put("驳回", "4");
	}

	private PlasApproveInfo entity;

	// 主题类型: 1-我的申请, 2-我的审批, 3-我的历史, 4-全部
	private String searchTypeCd;
	public static final String Q_APPLY = "1";
	public static final String Q_CHIEF = "2";
	public static final String Q_HISTORY = "3";

	// 万能检索条件
	private String searchMul;

	// 类型:1-未提交  2-审批中 3-完成 4-驳回
	private String condApproveStatusCd;
	
	// 申请类型：1-新增、2-调动、3-冻结、4-解冻、5-离职、6-启用
	private String condApplyTypeCd;
	
	// 9-全部
	private static final String APPLY_STATUS_CD_ALL = "9";
	
	// 审批意见
	private String optionDesc;
	
	// 当前申请流程的所有节点
	public static List<PlasApproveNodeHis> nodeHiss;
	
	// 历史流程节点
	public static List<PlasApproveNodeArch> nodeArchList;
	
	//更多：调动部门(调出，调入）
	private String condOrgCd;
	private String condOrgName;
	
	//被操作人:  姓名（模糊）,账号（精确）
	private String condName;
	private String condUiid;

	// 职级
	private String condPermLevelCd;
	
	// 编制落位 
	private String condPosCd;
	
	// 职位 
	private String condWorkDesc;
	
	// 更新（时间，降序)
	
	//发起人
	private String applyUiid;
	private String applyName;

	//审批人
	private String chiefUiid;
	private String chiefName;
	
	private String isProcessedFlg;
	private String isProcessingFlg;
	private String isWillProcessFlg;
	
	
	private String pageNo;

	//新旧中心
	private String oldCenterOrgName;
	private String newCenterOrgName;
	
	@Override
	public PlasApproveInfo getModel() {
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String execute() throws Exception {
		return list();
	}

	@Override
	public String list() throws Exception { 
		return "list";
	}

	// 旧职位
	public String getOldSysPosNames() {
		return getPosNames(entity.getOldSysPosIds());
	}

	// 新职位
	public String getNewSysPosNames() {
		return getPosNames(entity.getNewSysPosIds());
	}

	private String getPosNames(String posIds) {

		if (StringUtils.isBlank(posIds))
			return "";

		List<VoSysPosition> sys = plasSysPositionManager.searchPositionListByIds(posIds.split(","));
		StringBuffer sb = new StringBuffer();
		for (VoSysPosition vo : sys) {
			sb.append(vo.getSysPosName()).append(",");
		}
		return sb.toString();
	}

	/**
	 * 查看申请明细
	 * 
	 * @return
	 */
	public void prepareLoadApproveDetail() {
		entity = plasApproveInfoManager.getEntity(getId());
		entity.setOldParentName(bubleName(entity.getOldParentId()));
		entity.setNewParentName(bubleName(entity.getNewParentId()));

		oldCenterOrgName = processCenterName(entity.getOldParentId());
		newCenterOrgName = processCenterName(entity.getNewParentId());
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
	
	public String bubleName(String orgId){
		StringBuffer sb = new StringBuffer();
		List<PlasOrg>  list = plasOrgManager.getBubbleOrgsPhysical(orgId);
		for(int i=(list.size()-1); i>=0; i--){
			sb.append(list.get(i).getOrgName());
			if(i >0){
				sb.append("<br/>");
			}
		}
		return sb.toString();
	}
	
	public String loadApproveDetail() {

		String applyTypeCd = entity.getApplyTypeCd();
		if (DictContants.APPROVE_APPLY_TYPE_TRANSFER.equals(applyTypeCd))
			return "transfer";
		else if (DictContants.APPROVE_APPLY_TYPE_FREEZE.equals(applyTypeCd))
			return "freeze";
		else if (DictContants.APPROVE_APPLY_TYPE_UNFREEZE.equals(applyTypeCd))
			return "unfreeze";
		else if (DictContants.APPROVE_APPLY_TYPE_CLOSED.equals(applyTypeCd))
			return "closed";
		else if (DictContants.APPROVE_APPLY_TYPE_ENABLE.equals(applyTypeCd))
			return "enable";
		return null;
	}

	/**
	 * 审批历史
	 */
	public String loadApproves() {

		if (StringUtils.isNotBlank(getId())) {
			
			entity = plasApproveInfoManager.getEntity(getId());
			
			// 查询已审批过得历史节点
			nodeHiss = plasApproveNodeHisManager.getApproveHisList(entity.getPlasApproveInfoId());
			
			// 历史审批过得节点
			nodeArchList = plasApproveNodeArchManager.getNodeArchList(entity.getPlasApproveInfoId());
		}
		return "list";
	}

	/**
	 * 重新提交申请
	 * 
	 * @return
	 */
	public void approve() {

		if (StringUtils.isBlank(getId())) {
			Struts2Utils.renderText("对不起，未传入申请单ID，请确认!");
			return;
		}
		try {
			entity = plasApproveInfoManager.getEntity(getId());

			// 要求:被驳回的操作，应该不再占据这个人的提交判断。现在是被驳回就无法再发起调整了。
			// 检查当前被操作人是否有审批记录在流转
			List<PlasApproveInfo> processList = plasApproveInfoManager.getProcessingList(entity.getNewUiid());
			if (processList != null && processList.size() > 0) {
				Struts2Utils.renderText("对不起，当前用户已有申请记录正在走流程，请确认!");
				return;
			}

			entity.setApproveStatusCd(DictContants.APPROVE_STATUS_PROCESS);
			plasApproveInfoManager.saveOrSubmit(entity, " 发起原因:"+optionDesc);

			Struts2Utils.renderText("success");
		} catch (Exception e) {
			Struts2Utils.renderText(e.getMessage());
			log.error("提交申请异常!", e);
		}
	}

	/**
	 * 同意
	 */
	public void pass() {
		try{
			if (StringUtils.isBlank(getId())) {
				Struts2Utils.renderText("对不起，未传入申请单ID，请确认!");
				return;
			}
			entity = plasApproveInfoManager.getEntity(getId());
		
			boolean flag = plasApproveInfoManager.doProcess(DictContants.APPROVE_STATUS_DO_AGREE, entity, optionDesc);
			if (flag) {
				Struts2Utils.renderText("success");
			} else {
				Struts2Utils.renderText("error");
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("同意异常!id("+getId() + ")", e);
			Struts2Utils.renderText(e.getMessage());
		}
	}

	/**
	 * 驳回
	 */
	public void reject() {

		try{
			if (StringUtils.isBlank(getId())) {
				Struts2Utils.renderText("对不起，未传入申请单ID，请确认!");
				return;
			}
			entity = plasApproveInfoManager.getEntity(getId());
			boolean flag = plasApproveInfoManager.doProcess(DictContants.APPROVE_STATUS_DO_DEGREE, entity, optionDesc);
			if (flag) {
				Struts2Utils.renderText("success");
			} else {
				Struts2Utils.renderText("error");
			}
		}catch(Exception e){
			Struts2Utils.renderText(e.getMessage());
		}
	}

	/**
	 * 批量同意
	 */
	public void checkAll() {
		String bisFactIds = Struts2Utils.getParameter("ids");
		String[] tmp = bisFactIds.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tmp.length; i++) {
			if (StringUtils.isNotBlank(tmp[i])) {
				entity = plasApproveInfoManager.getEntity(tmp[i]);
				try{
					if (!plasApproveInfoManager.doProcess(DictContants.APPROVE_STATUS_DO_AGREE, entity, "同意")) {
						sb.append(entity.getNewName()).append(",");
					}
				}catch(Exception e){
					sb.append(entity.getNewName()).append(",");
				}
			}
		}
		if (StringUtils.isNotBlank(sb.toString())) {
			Struts2Utils.renderText("操作不成功！【被审批人:" + sb.toString() + "】");
		} else {
			Struts2Utils.renderText("success");
		}
	}

	/**
	 * 批量驳回
	 */

	public void rejectAll() {
		String bisFactIds = Struts2Utils.getParameter("ids");
		String[] tmp = bisFactIds.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tmp.length; i++) {
			if (StringUtils.isNotBlank(tmp[i])) {
				entity = plasApproveInfoManager.getEntity(tmp[i]);
				try{
					if (!plasApproveInfoManager.doProcess(DictContants.APPROVE_STATUS_DO_DEGREE, entity, "驳回")) {
						sb.append(entity.getNewName()).append(",");
					}
				}catch (Exception e) {
					sb.append(entity.getNewName()).append(",");
				}
			}
		}
		if (StringUtils.isNotBlank(sb.toString())) {
			Struts2Utils.renderText("操作不成功！【被审批人:" + sb.toString() + "】");
		} else {
			Struts2Utils.renderText("success");
		}
	}
  
	@Override
	public void prepareInput() throws Exception {
		prepareModel();
	}

	@Override
	public String input() throws Exception {
		return "input";
	}

	@Override
	public void prepareSave() throws Exception {
		prepareModel();
	}

	@Override
	public String save() throws Exception {
		plasApproveInfoManager.savePlasApproveInfo(entity);
		return null;
	}

	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = plasApproveInfoManager.getEntity(getId());
		} else {
			entity = new PlasApproveInfo();
		}
	}

	public static List<PlasApproveNodeHis> getNodeHiss() {
		return nodeHiss;
	}

	public static void setNodeHiss(List<PlasApproveNodeHis> nodeHiss) {
		ApproveAction.nodeHiss = nodeHiss;
	}
	

	/**
	 * 查看申请、审批、历史列表
	 * @param searchTypeCd ：主题类型
	 * @param applyTypeCd ：申请类型
	 * @return
	 */
	public String panel(){

		return "panel";
	}
	
	private String[] getArrList(String src){
		if(StringUtils.isBlank(src))
			return null;
		return StringUtils.split(src, ";");
	}
	
	/**
	 * @return
	 */
	public String panelList(){
		try{
			
			Long start = System.currentTimeMillis();
			
			Map<String, Object> values = new HashMap<String, Object>();
	
			//若空,默认查看历史
			if (StringUtils.isBlank(searchTypeCd)) {
				searchTypeCd = Q_CHIEF;
			}
			
			StringBuffer hqlBuf = new StringBuffer(" from PlasApproveInfo t where 1=1 ");
			//查询我的申请
			if(Q_APPLY.equals(searchTypeCd)){
				
				List<WsPlasRole> roleList = plasRoleManager.getWsUserRoles(GlobalConstants.UAAP_BIZ_CD, SpringSecurityUtils.getCurUiid());
				//总部/总管理员/人事管理员
				List<String> firstGroupCdList = new ArrayList<String>();
				firstGroupCdList.add("DEFAULT");
				if(RoleUtil.isHrGroup(roleList)){
					firstGroupCdList.add(GlobalConstants.A_HR_GROUP);
				}
				if(RoleUtil.isHrBizCenter(roleList)){
					firstGroupCdList.add(GlobalConstants.A_BIZ_CENTER);
				}
				if(RoleUtil.isHrAdmin(roleList)){
					firstGroupCdList.add(GlobalConstants.A_HR_ADMIN);
				}
				//区域
				if(RoleUtil.isHrNq(roleList)){
					firstGroupCdList.add(GlobalConstants.A_HR_NQ);
				}
				if(RoleUtil.isHrBq(roleList)){
					firstGroupCdList.add(GlobalConstants.A_HR_BQ);
				}
				if(RoleUtil.isHrSy(roleList)){
					firstGroupCdList.add(GlobalConstants.A_HR_SY);
				}
//				if(RoleUtil.isHrDw(roleList)){
//					firstGroupCdList.add(GlobalConstants.A_HR_DW);
//				}
				if(RoleUtil.isHrJd(roleList)){
					firstGroupCdList.add(GlobalConstants.A_HR_JD);
				}
				
				//我申请,范围扩大到同角色&同机构
				values.put("applyRoleCdList", firstGroupCdList); 
				values.put("creator", SpringSecurityUtils.getCurUiid());
					hqlBuf.append(" and (t.applyRoleCd in( :applyRoleCdList ) or t.creator = :creator)");//or t.newRelOrgCd in(:relOrgCdList)
				
				if(StringUtils.isNotBlank(searchMul)){
					values.put("newUiid", searchMul);
					values.put("newName", "%"+searchMul+"%");
					hqlBuf.append(" and (t.newUiid = :newUiid or t.newName like :newName) ");
				}
			}
			//查询我的审批
			else if(Q_CHIEF.equals(searchTypeCd)){

				//等待我审批的角色
				values.put("roleCdList", RoleUtil.getMyRoleList());
				hqlBuf	.append(" and exists( ")
						.append("     select 1 from PlasApproveNodeHis t2 ")
						.append("      where t2.plasApproveInfo.plasApproveInfoId = t.plasApproveInfoId ")
						.append("        and t2.plasApproveNodeHisId = t.nextNodeId ")
						.append("        and t2.approveRoleCd in (:roleCdList) ")
						.append(" )");
			}
			//查询所有历史(
			else{
				
			}

			//申请状态
			if (APPLY_STATUS_CD_ALL.equals(condApproveStatusCd)){
				condApproveStatusCd ="";
			}else{
				//默认查询未提交
				if (StringUtils.isBlank(condApproveStatusCd)){
					condApproveStatusCd = "1";
				}
			}
			List<String> approveStatusCdList = new ArrayList<String>();
			String[] arrApproveStatusCd = StringUtils.split(condApproveStatusCd,",");
			for(int i=0; i<arrApproveStatusCd.length; i++){
				approveStatusCdList.add(arrApproveStatusCd[i]);
			}
			if(approveStatusCdList != null && approveStatusCdList.size() > 0){
				values.put("approveStatusCdList", approveStatusCdList);
				hqlBuf.append(" and t.approveStatusCd in (:approveStatusCdList) ");
			}

			// 账号，姓名
			boolean flag = false;
			if(StringUtils.isNotBlank(condUiid)){
				values.put("condUiid", getArrList(condUiid));
				hqlBuf.append(" and t.newUiid in (:condUiid) ");
				flag = true;
			}
			if(StringUtils.isNotBlank(condName)){
				values.put("condName", getArrList(condName));
				hqlBuf.append(" and t.newName like (:condName) ");
				flag = true;
			}
			
			if(!flag && StringUtils.isNotBlank(searchMul)){
				if(StringUtils.isNotBlank(searchMul)){
					values.put("searchMul", getArrList(searchMul));
					values.put("newName", "%"+searchMul+"%");
					hqlBuf.append(" and (t.newUiid in (:searchMul) or t.newName like :newName) ");
				}
			}
			
			// 申请类型
			if(StringUtils.isNotBlank(condApplyTypeCd)){
				values.put("applyTypeCd", condApplyTypeCd);
				hqlBuf.append(" and t.applyTypeCd = :applyTypeCd ");
			}

			// 调动部门
			if(StringUtils.isNotBlank(condOrgCd)){
				values.put("condOrgCd", getArrList(condOrgCd));
				hqlBuf	.append(" and ( t.newParentId = (select org.plasOrgId from PlasOrg org where org.orgCd in (:condOrgCd) ) ")
						.append(" 	 or t.oldParentId = (select org.plasOrgId from PlasOrg org where org.orgCd in (:condOrgCd) ) ")
						.append(" )");
			}
			// 职级
			if(StringUtils.isNotBlank(condPermLevelCd)){
				values.put("condPermLevelCd", condPermLevelCd);
				hqlBuf.append(" and (t.newLevelCd = :condPermLevelCd or t.oldLevelCd = :condPermLevelCd) ");
			}
			
			// 编制落位 
			if(StringUtils.isNotBlank(condPosCd)){
				values.put("condPosCd", condPosCd);
				hqlBuf.append(" and (t.newSysPosIds like :condPosCd or t.oldSysPosIds = :condPosCd) ");
			}
			
			// 职位 
			if(StringUtils.isNotBlank(condWorkDesc)){
				values.put("condWorkDesc", "%"+condWorkDesc+"%");
				hqlBuf.append(" and (t.newWorkDuty like :condWorkDesc or t.oldWorkDuty like :condWorkDesc) ");
			}
			
			// 更新（时间，降序)
			
			//发起人
			if(StringUtils.isNotBlank(applyUiid)){
				values.put("applyUiid", getArrList(applyUiid));
				hqlBuf.append(" and t.creator in (:applyUiid) ");
			}
			//已审批
			if("on".equals(isProcessedFlg)){
				if(StringUtils.isNotBlank(chiefUiid)){
					values.put("chiefUiid", getArrList(chiefUiid));
					hqlBuf.append(" and exists(")
						  .append(" 	select 1 from PlasApproveNodeArch t3 ")
						  .append("  	 where t3.plasApproveInfo.plasApproveInfoId = t.plasApproveInfoId ")
					 	  .append("   	   and t3.approveUiid in (:chiefUiid) ")
					 	  .append(" ) ");
				}
			}
			//正在等待审批
			else if("on".equals(isProcessingFlg)){
				if(StringUtils.isNotBlank(chiefUiid)){
					if(StringUtils.isNotBlank(chiefUiid) && getArrList(chiefUiid).length >0){
						List<WsPlasRole> lstRole = plasRoleManager.getWsUserRoles(GlobalConstants.UAAP_BIZ_CD, getArrList(chiefUiid)[0]);
						values.put("roleCdList", RoleUtil.getRoleCdList(lstRole));
						hqlBuf.append("   and exists(")
						.append("       select 1 from PlasApproveNodeHis t2 ")
						.append("        where t2.plasApproveNodeHisId = t.nextNodeId ")
						.append("          and t2.plasApproveInfo.plasApproveInfoId = t.plasApproveInfoId")
						.append("          and t2.approveRoleCd in (:roleCdList) ")
						.append(" ) ");
					}
				}
			}
			hqlBuf.append(" order by t.createdDate desc ");
			
			page = new Page<PlasApproveInfo>(30);
			if(StringUtils.isBlank(pageNo)){
				pageNo = "1";
			}
			
			page.setPageNo(Integer.valueOf(pageNo).intValue());
			page = plasApproveInfoManager.findPage(page, hqlBuf.toString(), values);
			
			Long end = System.currentTimeMillis();
			logger.error(">>>>>>>>> 执行查询,耗时:" + (end - start) / 1000 +" 秒！");
			
		}catch(Exception e){
			log.error("查询列表异常!", e);
		}
		
		
		return "panelList";
		
	}

	public String getSearchTypeCd() {
		return searchTypeCd;
	}

	public void setSearchTypeCd(String searchTypeCd) {
		this.searchTypeCd = searchTypeCd;
	}
 

	public String getCondApproveStatusCd() {
		return condApproveStatusCd;
	}

	public void setCondApproveStatusCd(String condApproveStatusCd) {
		this.condApproveStatusCd = condApproveStatusCd;
	}

	public String getCondApplyTypeCd() {
		return condApplyTypeCd;
	}

	public void setCondApplyTypeCd(String condApplyTypeCd) {
		this.condApplyTypeCd = condApplyTypeCd;
	}

	public String getOptionDesc() {
		return optionDesc;
	}

	public void setOptionDesc(String optionDesc) {
		this.optionDesc = optionDesc;
	}

	public String getSearchMul() {
		return searchMul;
	}

	public void setSearchMul(String searchMul) {
		this.searchMul = searchMul;
	}

	public String getCondName() {
		return condName;
	}

	public void setCondName(String condName) {
		this.condName = condName;
	}

	public String getCondUiid() {
		return condUiid;
	}

	public void setCondUiid(String condUiid) {
		this.condUiid = condUiid;
	}

	public String getCondOrgCd() {
		return condOrgCd;
	}

	public void setCondOrgCd(String condOrgCd) {
		this.condOrgCd = condOrgCd;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public static List<PlasApproveNodeArch> getNodeArchList() {
		return nodeArchList;
	}

	public static void setNodeArchList(List<PlasApproveNodeArch> nodeArchList) {
		ApproveAction.nodeArchList = nodeArchList;
	}

	public String getCondOrgName() {
		return condOrgName;
	}

	public void setCondOrgName(String condOrgName) {
		this.condOrgName = condOrgName;
	}

	public String getApplyUiid() {
		return applyUiid;
	}

	public void setApplyUiid(String applyUiid) {
		this.applyUiid = applyUiid;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getChiefUiid() {
		return chiefUiid;
	}

	public void setChiefUiid(String chiefUiid) {
		this.chiefUiid = chiefUiid;
	}

	public String getChiefName() {
		return chiefName;
	}

	public void setChiefName(String chiefName) {
		this.chiefName = chiefName;
	}

	public String getIsProcessedFlg() {
		return isProcessedFlg;
	}

	public void setIsProcessedFlg(String isProcessedFlg) {
		this.isProcessedFlg = isProcessedFlg;
	}

	public String getIsProcessingFlg() {
		return isProcessingFlg;
	}

	public void setIsProcessingFlg(String isProcessingFlg) {
		this.isProcessingFlg = isProcessingFlg;
	}

	public String getIsWillProcessFlg() {
		return isWillProcessFlg;
	}

	public void setIsWillProcessFlg(String isWillProcessFlg) {
		this.isWillProcessFlg = isWillProcessFlg;
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
	public String getCurrentUiid(){
		return SpringSecurityUtils.getCurUiid();
	}

	public String getCondPermLevelCd() {
		return condPermLevelCd;
	}

	public void setCondPermLevelCd(String condPermLevelCd) {
		this.condPermLevelCd = condPermLevelCd;
	}

	public String getCondPosCd() {
		return condPosCd;
	}

	public void setCondPosCd(String condPosCd) {
		this.condPosCd = condPosCd;
	}

	public String getCondWorkDesc() {
		return condWorkDesc;
	}

	public void setCondWorkDesc(String condWorkDesc) {
		this.condWorkDesc = condWorkDesc;
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
