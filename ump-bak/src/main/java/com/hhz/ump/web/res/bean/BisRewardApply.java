package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
/**
 * <p>商业公司奖金申报表</p>
 * @author huangjian
 * @create 2012-6-3
 */
public class BisRewardApply extends BaseTemplate {

	private String titleName;//标题
	private String projectName;//项目名称
	//类别
	private String feeType1;//招商奖金
	private String feeType2;//开业奖
	private String feeType3;//目标责任书完成奖
	private String feeType4;//其他
	private String other;//其他内容
	
	private String totalFee;//奖金总额(元)
	private String remark;//发放理由及说明(附件)


	@Override
	public String getResTitleName() {
		return titleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
}
