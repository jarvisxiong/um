
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/** (总部/地产公司/商业集团)续签合同申请表
 * @author liwei
 *
 * 2012-7-10
 */
public class HrRenewContractApplyBase extends BaseTemplate {

	// 姓名
	private String userName;
	// 用户CD
	private String userCd;
	// 中心/部门
	private String centerName;
	// 中心CD
	private String centerCd;
	// 职位
	private String position;
	// 入职日期
	private String enterDate;
	
	/***
	 * 最近一次合同类型
	 */
	// 合同类型#固定期限
	private String conFixedExpires;
	// 合同类型#无固定期限
	private String conNonFixedExpires;
	// 合同类型#以完成一定工作任务
	private String conCompleteTask;
	// 合同类型#固定期限#日期(从)
	private String conDateFrom;
	// 合同类型#固定期限#日期(至)
	private String conDateTo;
	// 合同类型#无固定日期#日期(从)
	private String conNonDateFrom;
	// 合同类型#完成工作任务输入内容
	private String conCompleteTaskInput;
		
	// 申请续签次数
	private String applyRenewTime;
	// 竣工备案时间
	private String filingDate;
	
	/***
	 * 续签申请
	 */
	// 续签申请#不同意续签
	private String reDisAgreeRenew;
	// 续签申请#申请固定期限
	private String reApplyFixedExpires;
	// 续签申请#以完成一定工作任务
	private String reCompleteTask;
	// 续签申请#无固定期限
	private String reNonFixedExpires;
	// 续签申请#申请固定期限(月)
	private String reMonth;
	// 续签申请#申请固定期限#日期(从)
	private String reDateFrom;
	// 续签申请#申请固定期限#日期(至)
	private String reDateTo;
	// 续签申请#完成工作任务输入内容
	private String reCompleteTaskInput;

	private String attachStr;	//附件

	@Override
	public String getResTitleName() {
		return applyRenewTime;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAttachStr() {
		return attachStr;
	}

	public void setAttachStr(String attachStr) {
		this.attachStr = attachStr;
	}
	
	/**
	 * @return the userCd
	 */
	public String getUserCd() {
		return userCd;
	}

	/**
	 * @param userCd the userCd to set
	 */
	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	/**
	 * @return the centerName
	 */
	public String getCenterName() {
		return centerName;
	}

	/**
	 * @param centerName the centerName to set
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	/**
	 * @return the centerCd
	 */
	public String getCenterCd() {
		return centerCd;
	}

	/**
	 * @param centerCd the centerCd to set
	 */
	public void setCenterCd(String centerCd) {
		this.centerCd = centerCd;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the enterDate
	 */
	public String getEnterDate() {
		return enterDate;
	}

	/**
	 * @param enterDate the enterDate to set
	 */
	public void setEnterDate(String enterDate) {
		this.enterDate = enterDate;
	}

	/**
	 * @return the conFixedExpires
	 */
	public String getConFixedExpires() {
		return conFixedExpires;
	}

	/**
	 * @param conFixedExpires the conFixedExpires to set
	 */
	public void setConFixedExpires(String conFixedExpires) {
		this.conFixedExpires = conFixedExpires;
	}

	/**
	 * @return the conNonFixedExpires
	 */
	public String getConNonFixedExpires() {
		return conNonFixedExpires;
	}

	/**
	 * @param conNonFixedExpires the conNonFixedExpires to set
	 */
	public void setConNonFixedExpires(String conNonFixedExpires) {
		this.conNonFixedExpires = conNonFixedExpires;
	}

	/**
	 * @return the conCompleteTask
	 */
	public String getConCompleteTask() {
		return conCompleteTask;
	}

	/**
	 * @param conCompleteTask the conCompleteTask to set
	 */
	public void setConCompleteTask(String conCompleteTask) {
		this.conCompleteTask = conCompleteTask;
	}

	/**
	 * @return the conDateFrom
	 */
	public String getConDateFrom() {
		return conDateFrom;
	}

	/**
	 * @param conDateFrom the conDateFrom to set
	 */
	public void setConDateFrom(String conDateFrom) {
		this.conDateFrom = conDateFrom;
	}

	/**
	 * @return the conDateTo
	 */
	public String getConDateTo() {
		return conDateTo;
	}

	/**
	 * @param conDateTo the conDateTo to set
	 */
	public void setConDateTo(String conDateTo) {
		this.conDateTo = conDateTo;
	}

	/**
	 * @return the conNonDateFrom
	 */
	public String getConNonDateFrom() {
		return conNonDateFrom;
	}

	/**
	 * @param conNonDateFrom the conNonDateFrom to set
	 */
	public void setConNonDateFrom(String conNonDateFrom) {
		this.conNonDateFrom = conNonDateFrom;
	}

	/**
	 * @return the conCompleteTaskInput
	 */
	public String getConCompleteTaskInput() {
		return conCompleteTaskInput;
	}

	/**
	 * @param conCompleteTaskInput the conCompleteTaskInput to set
	 */
	public void setConCompleteTaskInput(String conCompleteTaskInput) {
		this.conCompleteTaskInput = conCompleteTaskInput;
	}

	/**
	 * @return the applyRenewTime
	 */
	public String getApplyRenewTime() {
		return applyRenewTime;
	}

	/**
	 * @param applyRenewTime the applyRenewTime to set
	 */
	public void setApplyRenewTime(String applyRenewTime) {
		this.applyRenewTime = applyRenewTime;
	}

	/**
	 * @return the filingDate
	 */
	public String getFilingDate() {
		return filingDate;
	}

	/**
	 * @param filingDate the filingDate to set
	 */
	public void setFilingDate(String filingDate) {
		this.filingDate = filingDate;
	}

	/**
	 * @return the reDisAgreeRenew
	 */
	public String getReDisAgreeRenew() {
		return reDisAgreeRenew;
	}

	/**
	 * @param reDisAgreeRenew the reDisAgreeRenew to set
	 */
	public void setReDisAgreeRenew(String reDisAgreeRenew) {
		this.reDisAgreeRenew = reDisAgreeRenew;
	}

	/**
	 * @return the reApplyFixedExpires
	 */
	public String getReApplyFixedExpires() {
		return reApplyFixedExpires;
	}

	/**
	 * @param reApplyFixedExpires the reApplyFixedExpires to set
	 */
	public void setReApplyFixedExpires(String reApplyFixedExpires) {
		this.reApplyFixedExpires = reApplyFixedExpires;
	}

	/**
	 * @return the reCompleteTask
	 */
	public String getReCompleteTask() {
		return reCompleteTask;
	}

	/**
	 * @param reCompleteTask the reCompleteTask to set
	 */
	public void setReCompleteTask(String reCompleteTask) {
		this.reCompleteTask = reCompleteTask;
	}

	/**
	 * @return the reNonFixedExpires
	 */
	public String getReNonFixedExpires() {
		return reNonFixedExpires;
	}

	/**
	 * @param reNonFixedExpires the reNonFixedExpires to set
	 */
	public void setReNonFixedExpires(String reNonFixedExpires) {
		this.reNonFixedExpires = reNonFixedExpires;
	}

	/**
	 * @return the reMonth
	 */
	public String getReMonth() {
		return reMonth;
	}

	/**
	 * @param reMonth the reMonth to set
	 */
	public void setReMonth(String reMonth) {
		this.reMonth = reMonth;
	}

	/**
	 * @return the reDateFrom
	 */
	public String getReDateFrom() {
		return reDateFrom;
	}

	/**
	 * @param reDateFrom the reDateFrom to set
	 */
	public void setReDateFrom(String reDateFrom) {
		this.reDateFrom = reDateFrom;
	}

	/**
	 * @return the reDateTo
	 */
	public String getReDateTo() {
		return reDateTo;
	}

	/**
	 * @param reDateTo the reDateTo to set
	 */
	public void setReDateTo(String reDateTo) {
		this.reDateTo = reDateTo;
	}

	/**
	 * @return the reCompleteTaskInput
	 */
	public String getReCompleteTaskInput() {
		return reCompleteTaskInput;
	}

	/**
	 * @param reCompleteTaskInput the reCompleteTaskInput to set
	 */
	public void setReCompleteTaskInput(String reCompleteTaskInput) {
		this.reCompleteTaskInput = reCompleteTaskInput;
	}


}
