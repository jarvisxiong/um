package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;


/**
 * 文件提取申请单
 * @author lujunyun 2010-12-21
 */
public class FileGet extends BaseTemplate {
	private String applyUserName;	//申 请 人
	private String applyUserCd;	//申 请 人
	private String applyCenterName;	//申请中心
	private String applyCenterCd;	//申请中心
	private String applyDate;	//申请日期
	private String fileDetail;	//提取文档明细
	private String returnDate;	//归还日期
	private String reason;	//事由说明
	
	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getApplyUserCd() {
		return applyUserCd;
	}

	public void setApplyUserCd(String applyUserCd) {
		this.applyUserCd = applyUserCd;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getFileDetail() {
		return fileDetail;
	}

	public void setFileDetail(String fileDetail) {
		this.fileDetail = fileDetail;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return applyCenterCd;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return applyCenterName;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getApplyCenterName() {
		return applyCenterName;
	}

	public void setApplyCenterName(String applyCenterName) {
		this.applyCenterName = applyCenterName;
	}

	public String getApplyCenterCd() {
		return applyCenterCd;
	}

	public void setApplyCenterCd(String applyCenterCd) {
		this.applyCenterCd = applyCenterCd;
	}
}
