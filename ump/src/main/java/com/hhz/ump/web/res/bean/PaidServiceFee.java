package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
/**
 * <p>有偿服务收费标准审批表</p>
 * @author huangjian
 * @create 2012-6-1
 */
public class PaidServiceFee extends BaseTemplate {

	private String titleName;//标题
	private String companyName;//公司名称
	//类别
	private String feeType1;//物业服务
	private String feeType2;//商户服务
	private String feeType3;//其他
	private String other;//其他内容
	private String serviceItem;//服务项目
	private String feeStandard;//收费标准
	private String remark;//相关说明（附件）
	



	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
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

	public String getServiceItem() {
		return serviceItem;
	}

	public void setServiceItem(String serviceItem) {
		this.serviceItem = serviceItem;
	}

	public String getFeeStandard() {
		return feeStandard;
	}

	public void setFeeStandard(String feeStandard) {
		this.feeStandard = feeStandard;
	}
	
	
}
