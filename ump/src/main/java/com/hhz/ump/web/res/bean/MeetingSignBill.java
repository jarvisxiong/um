/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 会议签到情况审批表
 * 
 * @author huangj 2010-9-20
 */
public class MeetingSignBill extends BaseTemplate {
	// 会议主题
	private String topic;

	// 会议时间
	private String meetingDate;
	private String meetingTime;

	// 会前请假
	private String isBefore;

	// 会后说明
	private String isAfter;

	// 姓名
	private String userName;

	// 无法签到原因说明
	private String reason;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getIsBefore() {
		return isBefore;
	}

	public void setIsBefore(String isBefore) {
		this.isBefore = isBefore;
	}

	public String getIsAfter() {
		return isAfter;
	}

	public void setIsAfter(String isAfter) {
		this.isAfter = isAfter;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return topic;
	}
}
