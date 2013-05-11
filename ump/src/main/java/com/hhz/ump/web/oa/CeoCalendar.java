package com.hhz.ump.web.oa;

import java.util.Date;

/**
 * 总裁日程
 * 目前只综合会议室预定和总裁预约
 * @author shixy
 *
 */
public class CeoCalendar {
	/**
	 * 数据Id
	 */
	private String dataId;
	/**
	 * 申请/预约人
	 */
	private String applicant;
	/**
	 * 主题
	 */
	private String subject;
	/**
	 * 参会人员
	 */
	private String participators;
	/**
	 * 开始时间
	 */
	private Date beginTime;
    /**
     * 结束时间
     */
	private Date endTime;
	/**
	 * 是否隐藏
	 */
	private String encryptFlg;
	/**
	 * 来源类型
	 * 0 总裁预约
	 * 1 会议室预定
	 */
    private String sourceType;
    
	public String getDataId() {
		return dataId;
	}
	public String getApplicant() {
		return applicant;
	}
	public String getSubject() {
		return subject;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getParticipators() {
		return participators;
	}
	public void setParticipators(String participators) {
		this.participators = participators;
	}
	public String getEncryptFlg() {
		return encryptFlg;
	}
	public void setEncryptFlg(String encryptFlg) {
		this.encryptFlg = encryptFlg;
	}
    
    
}
