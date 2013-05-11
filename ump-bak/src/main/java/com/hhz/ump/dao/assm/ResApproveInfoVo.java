/**
 * 
 */
package com.hhz.ump.dao.assm;

import java.util.Date;

/**
 * <p>
 * 网批数据VO
 * </p>
 * 
 * @author huangjian
 * @create 2012-7-26
 */
public class ResApproveInfoVo {
	private String resApproveInfoId;
	private Long displayNo;
	private String authTypeName;
	private Date completeDate;
	public ResApproveInfoVo(String resApproveInfoId, Long displayNo, String authTypeName,Date completeDate) {
		this.resApproveInfoId = resApproveInfoId;
		this.displayNo = displayNo;
		this.authTypeName = authTypeName;
		this.completeDate = completeDate;
	}

	public String getResApproveInfoId() {
		return resApproveInfoId;
	}

	public void setResApproveInfoId(String resApproveInfoId) {
		this.resApproveInfoId = resApproveInfoId;
	}

	public Long getDisplayNo() {
		return displayNo;
	}

	public void setDisplayNo(Long displayNo) {
		this.displayNo = displayNo;
	}

	public String getAuthTypeName() {
		return authTypeName;
	}

	public void setAuthTypeName(String authTypeName) {
		this.authTypeName = authTypeName;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

}
