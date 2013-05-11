package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 网批临时授权审批表
 * @author baolm
 *
 * 2011-04-08
 */
public class NetTempAuthorizeApproveSheet extends BaseTemplate {
	
	/**
	 * 授权人姓名
	 */
	private String authorizeUserName;
	
	/**
	 * 授权人CD
	 */
	private String authorizeUserCd;
	
	/**
	 * 职位
	 */
	private String position;
	
	/**
	 * 被授权人姓名
	 */
	private String authorizedUserName;
	
	/**
	 * 被授权人CD
	 */
	private String authorizedUserCd;
	
	/**
	 * 开始日期
	 */
	private String beginDate;
	
	/**
	 * 结束日期
	 */
	private String endDate;
	
	/**
	 * 授权原因
	 */
	private String authorizeReason;
	
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAuthorizeUserName() {
		return authorizeUserName;
	}

	public void setAuthorizeUserName(String authorizeUserName) {
		this.authorizeUserName = authorizeUserName;
	}

	public String getAuthorizeUserCd() {
		return authorizeUserCd;
	}

	public void setAuthorizeUserCd(String authorizeUserCd) {
		this.authorizeUserCd = authorizeUserCd;
	}

	public String getAuthorizedUserName() {
		return authorizedUserName;
	}

	public void setAuthorizedUserName(String authorizedUserName) {
		this.authorizedUserName = authorizedUserName;
	}

	public String getAuthorizedUserCd() {
		return authorizedUserCd;
	}

	public void setAuthorizedUserCd(String authorizedUserCd) {
		this.authorizedUserCd = authorizedUserCd;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getAuthorizeReason() {
		return authorizeReason;
	}

	public void setAuthorizeReason(String authorizeReason) {
		this.authorizeReason = authorizeReason;
	}

}
