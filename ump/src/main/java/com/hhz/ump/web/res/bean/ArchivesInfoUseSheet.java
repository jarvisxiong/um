package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 档案资料使用审批表
 * @author hy
 *
 * 2012-01-06
 */
public class ArchivesInfoUseSheet extends BaseTemplate {

	//申请人
	private String applyUser;
	private String applyUserCd;

	//部门
	private String applyUserDept;
	
	//借阅性质
	private String borrowerProperties1;
	private String borrowerProperties2;
	private String borrowerProperties3;
	private String borrowerProperties4;
	private String borrowerProperties5;
	private String borrowerOther;
	
	//档案密级
	private String securityLevel1;
	private String securityLevel2;
	private String securityLevel3;
	private String securityLevel4;
	
	//借阅日期
	private String borrowerTime;
	
	//归还日期
	private String returnTime;
	
	//借阅用途说明
	private String borrowerState;
	
	/**
	 * 档案名称
	 */
	private List<ArchivesInfo> otherProperties = new ArrayList<ArchivesInfo>();

	/**
	 * @return the applyUser
	 */
	public String getApplyUser() {
		return applyUser;
	}

	/**
	 * @param applyUser the applyUser to set
	 */
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	/**
	 * @return the applyUserDept
	 */
	public String getApplyUserDept() {
		return applyUserDept;
	}

	/**
	 * @param applyUserDept the applyUserDept to set
	 */
	public void setApplyUserDept(String applyUserDept) {
		this.applyUserDept = applyUserDept;
	}


	/**
	 * @return the borrowerTime
	 */
	public String getBorrowerTime() {
		return borrowerTime;
	}

	/**
	 * @param borrowerTime the borrowerTime to set
	 */
	public void setBorrowerTime(String borrowerTime) {
		this.borrowerTime = borrowerTime;
	}

	/**
	 * @return the returnTime
	 */
	public String getReturnTime() {
		return returnTime;
	}

	/**
	 * @param returnTime the returnTime to set
	 */
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	/**
	 * @return the borrowerState
	 */
	public String getBorrowerState() {
		return borrowerState;
	}

	/**
	 * @param borrowerState the borrowerState to set
	 */
	public void setBorrowerState(String borrowerState) {
		this.borrowerState = borrowerState;
	}

	/**
	 * @return the otherProperties
	 */
	public List<ArchivesInfo> getOtherProperties() {
		return otherProperties;
	}

	/**
	 * @param otherProperties the otherProperties to set
	 */
	public void setOtherProperties(List<ArchivesInfo> otherProperties) {
		this.otherProperties = otherProperties;
	}

	/**
	 * @return the borrowerProperties1
	 */
	public String getBorrowerProperties1() {
		return borrowerProperties1;
	}

	/**
	 * @param borrowerProperties1 the borrowerProperties1 to set
	 */
	public void setBorrowerProperties1(String borrowerProperties1) {
		this.borrowerProperties1 = borrowerProperties1;
	}

	/**
	 * @return the borrowerProperties2
	 */
	public String getBorrowerProperties2() {
		return borrowerProperties2;
	}

	/**
	 * @param borrowerProperties2 the borrowerProperties2 to set
	 */
	public void setBorrowerProperties2(String borrowerProperties2) {
		this.borrowerProperties2 = borrowerProperties2;
	}

	/**
	 * @return the borrowerProperties3
	 */
	public String getBorrowerProperties3() {
		return borrowerProperties3;
	}

	/**
	 * @param borrowerProperties3 the borrowerProperties3 to set
	 */
	public void setBorrowerProperties3(String borrowerProperties3) {
		this.borrowerProperties3 = borrowerProperties3;
	}

	/**
	 * @return the borrowerProperties4
	 */
	public String getBorrowerProperties4() {
		return borrowerProperties4;
	}

	/**
	 * @param borrowerProperties4 the borrowerProperties4 to set
	 */
	public void setBorrowerProperties4(String borrowerProperties4) {
		this.borrowerProperties4 = borrowerProperties4;
	}

	/**
	 * @return the borrowerProperties5
	 */
	public String getBorrowerProperties5() {
		return borrowerProperties5;
	}

	/**
	 * @param borrowerProperties5 the borrowerProperties5 to set
	 */
	public void setBorrowerProperties5(String borrowerProperties5) {
		this.borrowerProperties5 = borrowerProperties5;
	}

	/**
	 * @return the borrowerOther
	 */
	public String getBorrowerOther() {
		return borrowerOther;
	}

	/**
	 * @param borrowerOther the borrowerOther to set
	 */
	public void setBorrowerOther(String borrowerOther) {
		this.borrowerOther = borrowerOther;
	}

	/**
	 * @return the securityLevel1
	 */
	public String getSecurityLevel1() {
		return securityLevel1;
	}

	/**
	 * @param securityLevel1 the securityLevel1 to set
	 */
	public void setSecurityLevel1(String securityLevel1) {
		this.securityLevel1 = securityLevel1;
	}

	/**
	 * @return the securityLevel2
	 */
	public String getSecurityLevel2() {
		return securityLevel2;
	}

	/**
	 * @param securityLevel2 the securityLevel2 to set
	 */
	public void setSecurityLevel2(String securityLevel2) {
		this.securityLevel2 = securityLevel2;
	}

	/**
	 * @return the securityLevel3
	 */
	public String getSecurityLevel3() {
		return securityLevel3;
	}

	/**
	 * @param securityLevel3 the securityLevel3 to set
	 */
	public void setSecurityLevel3(String securityLevel3) {
		this.securityLevel3 = securityLevel3;
	}

	/**
	 * @return the securityLevel4
	 */
	public String getSecurityLevel4() {
		return securityLevel4;
	}

	/**
	 * @param securityLevel4 the securityLevel4 to set
	 */
	public void setSecurityLevel4(String securityLevel4) {
		this.securityLevel4 = securityLevel4;
	}

	/**
	 * @return the applyUserCd
	 */
	public String getApplyUserCd() {
		return applyUserCd;
	}

	/**
	 * @param applyUserCd the applyUserCd to set
	 */
	public void setApplyUserCd(String applyUserCd) {
		this.applyUserCd = applyUserCd;
	}
	
	
	
}
