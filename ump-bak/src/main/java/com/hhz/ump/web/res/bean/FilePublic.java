package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;


/**
 * 发文签批单
 * @author lujunyun 2010-12-21
 */
public class FilePublic extends BaseTemplate {
	private String title;	//发文标题
	private String serialNumber;	//发文编号
	private String submissionDept;	//报送单位
	private String mainDept;	//主送单位
	private String ccDept;	//抄送单位
	private String publicDept;	//发文单位
	private String publicWay1;	//PD系统公告
	private String publicWay2;	//电子邮件
	private String publicWay3;	//其他方式：
	private String otherWay;	//其他方式
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSubmissionDept() {
		return submissionDept;
	}

	public void setSubmissionDept(String submissionDept) {
		this.submissionDept = submissionDept;
	}

	public String getMainDept() {
		return mainDept;
	}

	public void setMainDept(String mainDept) {
		this.mainDept = mainDept;
	}

	public String getCcDept() {
		return ccDept;
	}

	public void setCcDept(String ccDept) {
		this.ccDept = ccDept;
	}

	public String getPublicDept() {
		return publicDept;
	}

	public void setPublicDept(String publicDept) {
		this.publicDept = publicDept;
	}

	public String getPublicWay1() {
		return publicWay1;
	}

	public void setPublicWay1(String publicWay1) {
		this.publicWay1 = publicWay1;
	}

	public String getPublicWay2() {
		return publicWay2;
	}

	public void setPublicWay2(String publicWay2) {
		this.publicWay2 = publicWay2;
	}

	public String getPublicWay3() {
		return publicWay3;
	}

	public void setPublicWay3(String publicWay3) {
		this.publicWay3 = publicWay3;
	}

	public String getOtherWay() {
		return otherWay;
	}

	public void setOtherWay(String otherWay) {
		this.otherWay = otherWay;
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
		return title;
	}
}
