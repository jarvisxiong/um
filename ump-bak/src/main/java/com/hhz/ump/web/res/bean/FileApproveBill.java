/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 1.文件审批表
 * 
 */
public class FileApproveBill extends BaseTemplate {

	// 文件标题
	private String fileTitle;

	// 密级
	private String secretLevel1;

	private String secretLevel2;

	private String secretLevel3;

	private String secretLevel4;

	// 急缓
	private String urgencyLevel;

	// 发起中心
	private String sendOrgName;
	// 发起中心CD
	private String sendOrgCd;

	// 文件内容简述
	private String fileDesc;

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getUrgencyLevel() {
		return urgencyLevel;
	}

	public void setUrgencyLevel(String urgencyLevel) {
		this.urgencyLevel = urgencyLevel;
	}

	public String getSendOrgName() {
		return sendOrgName;
	}

	public void setSendOrgName(String sendOrgName) {
		this.sendOrgName = sendOrgName;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public String getSecretLevel1() {
		return secretLevel1;
	}

	public void setSecretLevel1(String secretLevel1) {
		this.secretLevel1 = secretLevel1;
	}

	public String getSecretLevel2() {
		return secretLevel2;
	}

	public void setSecretLevel2(String secretLevel2) {
		this.secretLevel2 = secretLevel2;
	}

	public String getSecretLevel3() {
		return secretLevel3;
	}

	public void setSecretLevel3(String secretLevel3) {
		this.secretLevel3 = secretLevel3;
	}

	public String getSecretLevel4() {
		return secretLevel4;
	}

	public void setSecretLevel4(String secretLevel4) {
		this.secretLevel4 = secretLevel4;
	}

	public String getSendOrgCd() {
		return sendOrgCd;
	}

	public void setSendOrgCd(String sendOrgCd) {
		this.sendOrgCd = sendOrgCd;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return sendOrgCd;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return sendOrgName;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return fileTitle;
	}
}
