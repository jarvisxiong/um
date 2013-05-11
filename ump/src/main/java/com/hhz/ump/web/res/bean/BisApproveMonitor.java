package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

public class BisApproveMonitor extends BaseTemplatePay {
	
	/**
	 * 总经理费用报销审批表
	 * @author zhuxj
	 *
	 * 2012-04-11
	 */
	/**
	 * 是否提供(是/否)为急
	 */
	private String haierFlg;
	/**
	 * 标题
	 */
	private String titleName;
	/**
	 * 发起公司
	 */
	private String companyName;
	/**
	 * 费用名称
	 */
	private String changeName;
	/**
	 * 金额
	 */
	private String changeCrash;
	/**
	 * 文件内容详述
	 */
	private String fileDetail;
	/**
	 * 详见扫描件(上传附件)
	 */
	private String detailFileId;//费用报销单及对应的发票
	private String detailFileId2;//出差申请单或会议通知
	private String detailFileId3;//出差人员接受招待用餐申报单
	
	public String getHaierFlg() {
		return haierFlg;
	}
	public void setHaierFlg(String haierFlg) {
		this.haierFlg = haierFlg;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	@Override
	public String getCompanyName() {
		return companyName;
	}
	@Override
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public String getChangeCrash() {
		return changeCrash;
	}
	public void setChangeCrash(String changeCrash) {
		this.changeCrash = changeCrash;
	}
	public String getFileDetail() {
		return fileDetail;
	}
	public void setFileDetail(String fileDetail) {
		this.fileDetail = fileDetail;
	}
	public String getDetailFileId() {
		return detailFileId;
	}
	public void setDetailFileId(String detailFileId) {
		this.detailFileId = detailFileId;
	}
	public String getDetailFileId2() {
		return detailFileId2;
	}
	public void setDetailFileId2(String detailFileId2) {
		this.detailFileId2 = detailFileId2;
	}
	public String getDetailFileId3() {
		return detailFileId3;
	}
	public void setDetailFileId3(String detailFileId3) {
		this.detailFileId3 = detailFileId3;
	}
	
	
}
