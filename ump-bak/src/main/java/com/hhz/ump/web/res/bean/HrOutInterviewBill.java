/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**外地人才面试审批表
 * @author huangj
 * 2010-12-21
 */
public class HrOutInterviewBill  extends BaseTemplate {
	private String userName;//姓名
	private String positionName;//应聘岗位
	private String interviewDate;//面试时间
	private String address;//面试地点
	private String isTicket;//机票
	private String ticketBefore;//机票1
	private String ticketAfter;//机票2
	private String in2000;//2000元以内
	private String out2000;//2000元以上
	private String isLodging;//住宿
	private String lodgingBefore;//住宿1
	private String lodgingAfter;//住宿2
	private String isRepast;//餐饮
	private String repastContent;//餐饮1
	private String isOther;//其他
	private String otherContent;//其他内容
	private String remark;
	
	//新加附件上传
	private String attachmentId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIsTicket() {
		return isTicket;
	}
	public void setIsTicket(String isTicket) {
		this.isTicket = isTicket;
	}
	public String getTicketBefore() {
		return ticketBefore;
	}
	public void setTicketBefore(String ticketBefore) {
		this.ticketBefore = ticketBefore;
	}
	public String getTicketAfter() {
		return ticketAfter;
	}
	public void setTicketAfter(String ticketAfter) {
		this.ticketAfter = ticketAfter;
	}
	public String getIn2000() {
		return in2000;
	}
	public void setIn2000(String in2000) {
		this.in2000 = in2000;
	}
	public String getOut2000() {
		return out2000;
	}
	public void setOut2000(String out2000) {
		this.out2000 = out2000;
	}
	public String getIsLodging() {
		return isLodging;
	}
	public void setIsLodging(String isLodging) {
		this.isLodging = isLodging;
	}
	public String getLodgingBefore() {
		return lodgingBefore;
	}
	public void setLodgingBefore(String lodgingBefore) {
		this.lodgingBefore = lodgingBefore;
	}
	public String getLodgingAfter() {
		return lodgingAfter;
	}
	public void setLodgingAfter(String lodgingAfter) {
		this.lodgingAfter = lodgingAfter;
	}
	public String getIsRepast() {
		return isRepast;
	}
	public void setIsRepast(String isRepast) {
		this.isRepast = isRepast;
	}
	public String getRepastContent() {
		return repastContent;
	}
	public void setRepastContent(String repastContent) {
		this.repastContent = repastContent;
	}
	public String getIsOther() {
		return isOther;
	}
	public void setIsOther(String isOther) {
		this.isOther = isOther;
	}
	public String getOtherContent() {
		return otherContent;
	}
	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
		return positionName;
	}
	/**
	 * @return the attachmentId
	 */
	public String getAttachmentId() {
		return attachmentId;
	}
	/**
	 * @param attachmentId the attachmentId to set
	 */
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
}
