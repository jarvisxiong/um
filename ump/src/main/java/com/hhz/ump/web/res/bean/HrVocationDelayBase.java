/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * <p>年假延期申请单</p>
 * @author huangjian
 * @create 2012-4-19
 */
public class HrVocationDelayBase extends BaseTemplate {
	private String centerOrgName;//中心
	private String centerOrgCd;
	private String userName;//申请人
	private String userCd;
	private String position;//职位
	private String entryDate;//入职日期
	private String reason;//事由
	private String remaindDays;//剩余年假
	//延期时间
	private String delayMonth1;//一个月
	private String delayMonth2;//两个月
	private String delayMonth3;//三个月
	private String delayMonth4;//四个月
	private String delayMonth5;//五个月
	private String delayMonth6;//六个月
	public String getCenterOrgName() {
		return centerOrgName;
	}
	public void setCenterOrgName(String centerOrgName) {
		this.centerOrgName = centerOrgName;
	}
	public String getCenterOrgCd() {
		return centerOrgCd;
	}
	public void setCenterOrgCd(String centerOrgCd) {
		this.centerOrgCd = centerOrgCd;
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRemaindDays() {
		return remaindDays;
	}
	public void setRemaindDays(String remaindDays) {
		this.remaindDays = remaindDays;
	}
	public String getDelayMonth1() {
		return delayMonth1;
	}
	public void setDelayMonth1(String delayMonth1) {
		this.delayMonth1 = delayMonth1;
	}
	public String getDelayMonth2() {
		return delayMonth2;
	}
	public void setDelayMonth2(String delayMonth2) {
		this.delayMonth2 = delayMonth2;
	}
	public String getDelayMonth3() {
		return delayMonth3;
	}
	public void setDelayMonth3(String delayMonth3) {
		this.delayMonth3 = delayMonth3;
	}
	public String getDelayMonth4() {
		return delayMonth4;
	}
	public void setDelayMonth4(String delayMonth4) {
		this.delayMonth4 = delayMonth4;
	}
	public String getDelayMonth5() {
		return delayMonth5;
	}
	public void setDelayMonth5(String delayMonth5) {
		this.delayMonth5 = delayMonth5;
	}
	public String getDelayMonth6() {
		return delayMonth6;
	}
	public void setDelayMonth6(String delayMonth6) {
		this.delayMonth6 = delayMonth6;
	}
}
