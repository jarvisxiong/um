package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 机票订购申请单
 * @author shixy
 *
 * 2010-12-21
 */
public class AirTicketsBookingSheet extends BaseTemplate {

	/**
	 * 出行人员
	 */
	private String userName;
	/**
	 * 出行人员
	 */
	private String userCd;
	/**
	 * 起迄地
	 */
	private String beginEndPlace;
	/**
	 * 身份证号
	 */
	private String idCardNo;
	/**
	 * 出行事由
	 */
	private String tripCause;
	/**
	 * 建议航班时间
	 */
	private String tripDate;
	/**
	 * 费用承担部门
	 */
	private String costDept;
	
	/**
	 * 费用承担部门cd
	 */
	private String costDeptCd;
	
	//有审批记录(出差申请表人员面试申请表)
	private String isRecord;
	
	private String attachmentId;
	
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return costDept;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return costDeptCd;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return beginEndPlace;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCd() {
		return userCd;
	}
	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}
	public String getBeginEndPlace() {
		return beginEndPlace;
	}
	public void setBeginEndPlace(String beginEndPlace) {
		this.beginEndPlace = beginEndPlace;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getTripCause() {
		return tripCause;
	}
	public void setTripCause(String tripCause) {
		this.tripCause = tripCause;
	}
	public String getTripDate() {
		return tripDate;
	}
	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}
	public String getCostDept() {
		return costDept;
	}
	public void setCostDept(String costDept) {
		this.costDept = costDept;
	}
	/**
	 * @return the isRecord
	 */
	public String getIsRecord() {
		return isRecord;
	}
	/**
	 * @param isRecord the isRecord to set
	 */
	public void setIsRecord(String isRecord) {
		this.isRecord = isRecord;
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
