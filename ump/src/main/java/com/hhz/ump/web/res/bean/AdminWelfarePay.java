package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
/**
 * <p>员工行政、福利支出申请表</p>
 * @author huangjian
 * @create 2012-6-3
 */
public class AdminWelfarePay extends BaseTemplate {

	private String titleName;//标题
	private String companyName;//公司名称
	//类别
	private String feeType1;//员工活动
	private String feeType2;//工装
	private String feeType3;//非法定类保险
	private String feeType4;//体检
	private String feeType5;//其他
	private String other;//其他内容
	
	private String bringItem;//提审事项
	private String estimatedCost;//预计费用(元)
	private String contentTextArea;//相关说明（附件）
	


	@Override
	public String getResTitleName() {
		return titleName;
	}

	public String getContentTextArea() {
		return contentTextArea;
	}

	public void setContentTextArea(String contentTextArea) {
		this.contentTextArea = contentTextArea;
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

	public String getFeeType1() {
		return feeType1;
	}

	public void setFeeType1(String feeType1) {
		this.feeType1 = feeType1;
	}

	public String getFeeType2() {
		return feeType2;
	}

	public void setFeeType2(String feeType2) {
		this.feeType2 = feeType2;
	}

	public String getFeeType3() {
		return feeType3;
	}

	public void setFeeType3(String feeType3) {
		this.feeType3 = feeType3;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getFeeType4() {
		return feeType4;
	}

	public void setFeeType4(String feeType4) {
		this.feeType4 = feeType4;
	}

	public String getFeeType5() {
		return feeType5;
	}

	public void setFeeType5(String feeType5) {
		this.feeType5 = feeType5;
	}

	public String getBringItem() {
		return bringItem;
	}

	public void setBringItem(String bringItem) {
		this.bringItem = bringItem;
	}

	public String getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(String estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	
}
