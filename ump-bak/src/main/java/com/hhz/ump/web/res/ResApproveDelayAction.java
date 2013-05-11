package com.hhz.ump.web.res;

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.res.ResApproveDelayManager;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.entity.res.ResApproveDelay;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.uums.entity.ws.WsPlasAcct;
import com.hhz.uums.entity.ws.WsPlasUser;

public class ResApproveDelayAction extends CrudActionSupport<ResApproveDelay> {

	private static final long serialVersionUID = 2926195759467772827L;

	@Autowired
	private ResApproveInfoManager resApproveInfoManager;

	@Autowired
	protected ResApproveDelayManager resApproveDelayManager;

	protected ResApproveDelay entity;

	private String resApproveId;

	// 申请人姓名
	private String applyUserName;
	// 审批人姓名
	private String approveUserName;
	// 确认人姓名
	private String confirmUserName;

	// 当前用户CD
	private String curUserCd;
	// 是否申请
	private String appliedFlg;

	// 页面类型 1-申请 2-审批
	private String pageType;
	public static String PAGE_TYPE_APPLY = "1";
	public static String PAGE_TYPE_CHIEF = "3";

	// 延期申请历史
	public List<ResApproveDelay> historyList;

	// 申请类型:0-发起中心负责人 1-企管部审批 2-发起人审批 3-直接通过(决策层)
	private String applyTypeCd;

	public static String APPLY_TYPE_CD_CM = "0";
	public static String APPLY_TYPE_CD_QGB = "1";
	public static String APPLY_TYPE_CD_APPLY = "2";
	public static String APPLY_TYPE_CD_EXEC = "3";

	// 申请天数、小时
	private String delayDay;
	private String delayHour;

	// 驳回类型1-审核同意 2-审核人不同意 3-企管部同意 4-企管部不同意
	private String delayChiefTypeCd;

	@Override
	public ResApproveDelay getModel() {
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
	}

	@Override
	public String input() throws Exception {
		doApply();
		return INPUT;
	}

	/**
	 * 申请
	 */
	protected void doApply() {
		setPageType(PAGE_TYPE_APPLY);

		WsPlasAcct acct = SpringSecurityUtils.getCurrentPlasUser();
		if (GlobalConstants.EXEC_ORG_CD.equals(acct.getOrgCd())) {
			// 决策层申请经过企管部
			// entity.setApplyTypeCd(APPLY_TYPE_CD_QGB);

			// 决策层申请直接通过
			entity.setApplyTypeCd(APPLY_TYPE_CD_EXEC);
		} else {
			entity.setApplyTypeCd(APPLY_TYPE_CD_CM);
		}

	}

	@Override
	public void prepareSave() throws Exception {
		prepareModel();
	}

	@Override
	public String save() throws Exception {

		ResApproveInfo tempResApproveInfo = resApproveInfoManager.getEntity(getResApproveId());
		// 是否存在审批记录
		if (tempResApproveInfo == null) {
			Struts2Utils.renderText("未找到网批记录,请联系管理员.");
			return null;
		} else {
			// 是否已申请延期
			if (resApproveDelayManager.latestProcessDlay(getResApproveId()) != null) {
				Struts2Utils.renderText("已经申请延期,等待处理中...");
				return null;
			}

			// 是否存在审核节点
			// ResApproveNode chiefNode = resApproveNodeManager.getChiefNode(getResApproveId());
			// if(chiefNode == null){
			// Struts2Utils.renderText("未找到审批人,请联系管理员.");
			// return null;
			// }

			entity.setResApproveInfo(tempResApproveInfo);
			entity.setNodeCd(tempResApproveInfo.getNodeCd());
			entity.setApplyUserCd(SpringSecurityUtils.getCurrentUiid());// uiid

			// 经中心总经理
			if (APPLY_TYPE_CD_CM.equals(entity.getApplyTypeCd())) {
				entity.setStatusCd(ResApproveDelayManager.STATUS_APPLY);
			}
			// 表单发起人
			else if (APPLY_TYPE_CD_APPLY.equals(entity.getApplyTypeCd())) {
				entity.setStatusCd(ResApproveDelayManager.STATUS_APPLY);
				entity.setApproveUserCd(tempResApproveInfo.getUserCd());
			}
			// 企管部
			else if (APPLY_TYPE_CD_QGB.equals(entity.getApplyTypeCd())) {
				entity.setStatusCd(ResApproveDelayManager.STATUS_PROCESS);
			}
			// 直接通过
			else if (APPLY_TYPE_CD_EXEC.equals(entity.getApplyTypeCd())) {
				entity.setStatusCd(ResApproveDelayManager.STATUS_OK);
			} else {
				entity.setStatusCd(ResApproveDelayManager.STATUS_APPLY);
			}

			// hidden by huangbijin 2011-10-20 不需要企管部确认
			// 企管部
			// entity.setConfirmUserCd("qgb");

			// 冗余主表的层级
			entity.setApproveLevel(tempResApproveInfo.getApproveLevel());

			long lDelayDay = 0;
			long lDelayHour = 0;
			if (StringUtils.isNotBlank(delayDay)) {
				lDelayDay = Long.valueOf(delayDay).longValue();
			}
			if (StringUtils.isNotBlank(delayHour)) {
				lDelayHour = Long.valueOf(delayHour).longValue();
			}
			// 计算申请延期的小时数
			entity.setDelayTime(lDelayDay * 24 + lDelayHour);
			resApproveDelayManager.saveResApproveDelayWithApply(entity);
			Struts2Utils.renderText("success");
			return null;
		}
	}

	/**
	 * 撤销申请,直接删除记录
	 * 
	 * @param
	 * @return
	 */
	public String cancelApproveDelay() {
		ResApproveDelay delay = resApproveDelayManager.latestProcessDlay(getResApproveId());
		if (delay == null) {
			Struts2Utils.renderText("process");
			return null;
		}
		if (ResApproveDelayManager.STATUS_OK.equals(delay.getStatusCd())) {
			Struts2Utils.renderText("对不起,流程已结束,不能撤销!");
			return null;
		} else {
			resApproveDelayManager.cancelDelay(delay.getResApproveDelayId());
			Struts2Utils.renderText("success");
			return null;
		}
	}

	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = resApproveDelayManager.getEntity(getId());
		} else {
			entity = new ResApproveDelay();
		}
	}

	public String getResApproveId() {
		return resApproveId;
	}

	public void setResApproveId(String resApproveId) {
		this.resApproveId = resApproveId;
	}

	/**
	 * 校验是否申请审批中
	 * 
	 * @param resApproveId
	 * @return
	 */
	public String validateProcessing() {
		if (resApproveDelayManager.latestProcessDlay(getResApproveId()) != null) {
			Struts2Utils.renderText("已经申请延期,等待处理中...");
			return null;
		} else {
			Struts2Utils.renderText("success");
			return null;
		}
	}

	/**
	 * 加载审批信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepareLoadChief() throws Exception {
		entity = resApproveDelayManager.latestProcessDlay(getResApproveId());
	}

	public String loadChief() {
		curUserCd = SpringSecurityUtils.getCurrentUiid();
		setPageType(PAGE_TYPE_CHIEF);
		if (entity != null) {
			appliedFlg = "1";// 申请过
			if (StringUtils.isNotBlank(entity.getApplyUserCd())) {
				WsPlasUser user = PlasCache.getUserByUiid(entity.getApplyUserCd());// uiid
				if (user != null) {
					applyUserName = user.getUserName();
				}
			}
			long tmpDelayTime = entity.getDelayTime();
			delayDay = String.valueOf(tmpDelayTime / 24);
			delayHour = String.valueOf(tmpDelayTime % 24);
		}

		return INPUT;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getApproveUserName() {
		return approveUserName;
	}

	public void setApproveUserName(String approveUserName) {
		this.approveUserName = approveUserName;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getConfirmUserName() {
		return confirmUserName;
	}

	public void setConfirmUserName(String confirmUserName) {
		this.confirmUserName = confirmUserName;
	}

	public String getCurUserCd() {
		return curUserCd;
	}

	public void setCurUserCd(String curUserCd) {
		this.curUserCd = curUserCd;
	}

	public String getAppliedFlg() {
		return appliedFlg;
	}

	public void setAppliedFlg(String appliedFlg) {
		this.appliedFlg = appliedFlg;
	}

	/**
	 * (发起人)审批同意
	 * 
	 * @param resApproveDelayId
	 * @param reason
	 * @return
	 */
	public String chiefAgree() throws Exception {
		String resApproveDelayId = Struts2Utils.getParameter("resApproveDelayId");
		String reason = Struts2Utils.getParameter("reason");
		resApproveDelayManager.chiefAgree(resApproveDelayId, reason);
		Struts2Utils.renderText("success");
		return null;
	}

	/**
	 * (发起人)审批不同意
	 * 
	 * @param resApproveDelayId
	 * @param reason
	 * @return
	 */
	public String chiefDegree() {
		String resApproveDelayId = Struts2Utils.getParameter("resApproveDelayId");
		String reason = Struts2Utils.getParameter("reason");
		resApproveDelayManager.chiefDegree(resApproveDelayId, reason);
		Struts2Utils.renderText("success");
		return null;
	}

	/**
	 * (企管部)审批同意
	 * 
	 * @param resApproveDelayId
	 * @param reason
	 * @return
	 */
	public String confirmAgree() {
		String resApproveDelayId = Struts2Utils.getParameter("resApproveDelayId");
		String reason = Struts2Utils.getParameter("reason");
		resApproveDelayManager.confirmAgree(resApproveDelayId, reason);
		Struts2Utils.renderText("success");
		return null;
	}

	/**
	 * (企管部)审批不同意
	 * 
	 * @param resApproveDelayId
	 * @param reason
	 * @return
	 */
	public String confirmDegree() {
		String resApproveDelayId = Struts2Utils.getParameter("resApproveDelayId");
		String reason = Struts2Utils.getParameter("reason");
		resApproveDelayManager.confirmDegree(resApproveDelayId, reason);
		Struts2Utils.renderText("success");
		return null;
	}

	/**
	 * 查看延期申请历史
	 * 
	 * @param resApproveDelayId
	 * @return
	 */
	public String history() {
		historyList = resApproveDelayManager.history(getResApproveId());
		return "history";
	}

	// 写驳回意见
	public void prepareReason() throws Exception {
		this.prepareModel();
	}

	public String reason() {
		return "reason";
	}

	public List<ResApproveDelay> getHistoryList() {
		return historyList;
	}

	public void setHistoryList(List<ResApproveDelay> historyList) {
		this.historyList = historyList;
	}

	public String getApplyTypeCd() {
		return applyTypeCd;
	}

	public void setApplyTypeCd(String applyTypeCd) {
		this.applyTypeCd = applyTypeCd;
	}

	public String getDelayDay() {
		return delayDay;
	}

	public void setDelayDay(String delayDay) {
		this.delayDay = delayDay;
	}

	public String getDelayHour() {
		return delayHour;
	}

	public void setDelayHour(String delayHour) {
		this.delayHour = delayHour;
	}

	public String getDelayChiefTypeCd() {
		return delayChiefTypeCd;
	}

	public void setDelayChiefTypeCd(String delayChiefTypeCd) {
		this.delayChiefTypeCd = delayChiefTypeCd;
	}
}
