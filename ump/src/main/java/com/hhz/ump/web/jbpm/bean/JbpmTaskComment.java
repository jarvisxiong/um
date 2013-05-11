/**
 * 
 */
package com.hhz.ump.web.jbpm.bean;

import java.util.Date;

/**
 * 审批意见
 * 
 * @author huangj 2010-2-25
 */
public class JbpmTaskComment implements java.io.Serializable {
	private static final long serialVersionUID = 1660481059089127502L;

	private String creator;

	private Date createdDate;

	private String activityName;
	/**
	 * 职务
	 */
	private String positionName;

	/**
	 * 意见
	 */
	private String opinion;

	/**
	 * 信息
	 */
	private String message;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
}
