package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/***
 * 辞职审批表base
 * @author liwei
 *
 */
public class HrResignApproveBase extends BaseTemplate {

	// 姓名
	private String userName;
	// 用户CD
	private String userCd;
	// 中心/部门
	private String center;
	// 中心CD
	private String centerCd;
	// 职位
	private String position;
	// 入职日期
	private String enterDate;
	// 合同期限
	private String contractExpires;
	// 最后工作日
	private String lastWorkDay;
	// 辞职原因
	private String resignReason;

	private String attachStr;	//附件
	
	@Override
	public String getResTitleName() {
		return contractExpires;
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
	 * @return the center
	 */
	public String getCenter() {
		return center;
	}
	/**
	 * @param center the center to set
	 */
	public void setCenter(String center) {
		this.center = center;
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
	 * @return the contractExpires
	 */
	public String getContractExpires() {
		return contractExpires;
	}
	/**
	 * @param contractExpires the contractExpires to set
	 */
	public void setContractExpires(String contractExpires) {
		this.contractExpires = contractExpires;
	}
	/**
	 * @return the lastWorkDay
	 */
	public String getLastWorkDay() {
		return lastWorkDay;
	}
	/**
	 * @param lastWorkDay the lastWorkDay to set
	 */
	public void setLastWorkDay(String lastWorkDay) {
		this.lastWorkDay = lastWorkDay;
	}
	/**
	 * @return the resignReason
	 */
	public String getResignReason() {
		return resignReason;
	}
	/**
	 * @param resignReason the resignReason to set
	 */
	public void setResignReason(String resignReason) {
		this.resignReason = resignReason;
	}


	public String getAttachStr() {
		return attachStr;
	}

	public void setAttachStr(String attachStr) {
		this.attachStr = attachStr;
	}

	
}
