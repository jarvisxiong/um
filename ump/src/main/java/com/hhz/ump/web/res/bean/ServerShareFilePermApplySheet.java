/**
 * 
 */
package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 服务器共享文件权限申请单
 * 
 */
public class ServerShareFilePermApplySheet extends BaseTemplatePay {
	
	// 申请人 所属中心 日期
	// 文件夹名称  权限 只读 读写 (5)
	// 申请理由
	
	private String applyUserName;
	private String applyUserCd;
	private String applyCenterOrgName;
	private String applyCenterOrgCd;
	private String applyDeptOrgName;
	private String applyDeptOrgCd;
	private String applyDate;
	
	private String fileName1;
	private String fileName2;
	private String fileName3;
	private String fileName4;
	private String fileName5;
	
	private String selectFileName1Read;
	private String selectFileName1Write;
	private String selectFileName2Read;
	private String selectFileName2Write;
	private String selectFileName3Read;
	private String selectFileName3Write;
	private String selectFileName4Read;
	private String selectFileName4Write;
	private String selectFileName5Read;
	private String selectFileName5Write;
	private String contentDesc;
	   
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
	public String getApplyCenterOrgName() {
		return applyCenterOrgName;
	}
	public void setApplyCenterOrgName(String applyCenterOrgName) {
		this.applyCenterOrgName = applyCenterOrgName;
	}
	public String getApplyCenterOrgCd() {
		return applyCenterOrgCd;
	}
	public void setApplyCenterOrgCd(String applyCenterOrgCd) {
		this.applyCenterOrgCd = applyCenterOrgCd;
	}
	public String getApplyDeptOrgName() {
		return applyDeptOrgName;
	}
	public void setApplyDeptOrgName(String applyDeptOrgName) {
		this.applyDeptOrgName = applyDeptOrgName;
	}
	public String getApplyDeptOrgCd() {
		return applyDeptOrgCd;
	}
	public void setApplyDeptOrgCd(String applyDeptOrgCd) {
		this.applyDeptOrgCd = applyDeptOrgCd;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getFileName1() {
		return fileName1;
	}
	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}
	public String getFileName2() {
		return fileName2;
	}
	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}
	public String getFileName3() {
		return fileName3;
	}
	public void setFileName3(String fileName3) {
		this.fileName3 = fileName3;
	}
	public String getFileName4() {
		return fileName4;
	}
	public void setFileName4(String fileName4) {
		this.fileName4 = fileName4;
	}
	public String getFileName5() {
		return fileName5;
	}
	public void setFileName5(String fileName5) {
		this.fileName5 = fileName5;
	}
	public String getSelectFileName1Read() {
		return selectFileName1Read;
	}
	public void setSelectFileName1Read(String selectFileName1Read) {
		this.selectFileName1Read = selectFileName1Read;
	}
	public String getSelectFileName1Write() {
		return selectFileName1Write;
	}
	public void setSelectFileName1Write(String selectFileName1Write) {
		this.selectFileName1Write = selectFileName1Write;
	}
	public String getSelectFileName2Read() {
		return selectFileName2Read;
	}
	public void setSelectFileName2Read(String selectFileName2Read) {
		this.selectFileName2Read = selectFileName2Read;
	}
	public String getSelectFileName2Write() {
		return selectFileName2Write;
	}
	public void setSelectFileName2Write(String selectFileName2Write) {
		this.selectFileName2Write = selectFileName2Write;
	}
	public String getSelectFileName3Read() {
		return selectFileName3Read;
	}
	public void setSelectFileName3Read(String selectFileName3Read) {
		this.selectFileName3Read = selectFileName3Read;
	}
	public String getSelectFileName3Write() {
		return selectFileName3Write;
	}
	public void setSelectFileName3Write(String selectFileName3Write) {
		this.selectFileName3Write = selectFileName3Write;
	}
	public String getSelectFileName4Read() {
		return selectFileName4Read;
	}
	public void setSelectFileName4Read(String selectFileName4Read) {
		this.selectFileName4Read = selectFileName4Read;
	}
	public String getSelectFileName4Write() {
		return selectFileName4Write;
	}
	public void setSelectFileName4Write(String selectFileName4Write) {
		this.selectFileName4Write = selectFileName4Write;
	}
	public String getSelectFileName5Read() {
		return selectFileName5Read;
	}
	public void setSelectFileName5Read(String selectFileName5Read) {
		this.selectFileName5Read = selectFileName5Read;
	}
	public String getSelectFileName5Write() {
		return selectFileName5Write;
	}
	public void setSelectFileName5Write(String selectFileName5Write) {
		this.selectFileName5Write = selectFileName5Write;
	}
	public String getContentDesc() {
		return contentDesc;
	}
	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return this.applyCenterOrgName;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return this.applyCenterOrgCd;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return this.contentDesc;
	}
}
