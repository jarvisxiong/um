package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
/**
 * <p>能耗收费标准及调整审批表</p>
 * @author huangjian
 * @create 2012-6-1
 */
public class EnergyChargeAdjust extends BaseTemplate {

	private String titleName;//标题
	private String companyName;//公司名称
	//水
	private String waterPrice1;
	private String waterPrice2;
	//电
	private String electricPrice1;
	private String electricPrice2;
	//供暖
	private String heatingPrice1;
	private String heatingPrice2;
	//其他
	private String otherPrice1;
	private String otherPrice2;
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


	public String getWaterPrice1() {
		return waterPrice1;
	}

	public void setWaterPrice1(String waterPrice1) {
		this.waterPrice1 = waterPrice1;
	}

	public String getWaterPrice2() {
		return waterPrice2;
	}

	public void setWaterPrice2(String waterPrice2) {
		this.waterPrice2 = waterPrice2;
	}

	public String getElectricPrice1() {
		return electricPrice1;
	}

	public void setElectricPrice1(String electricPrice1) {
		this.electricPrice1 = electricPrice1;
	}

	public String getElectricPrice2() {
		return electricPrice2;
	}

	public void setElectricPrice2(String electricPrice2) {
		this.electricPrice2 = electricPrice2;
	}

	public String getHeatingPrice1() {
		return heatingPrice1;
	}

	public void setHeatingPrice1(String heatingPrice1) {
		this.heatingPrice1 = heatingPrice1;
	}

	public String getHeatingPrice2() {
		return heatingPrice2;
	}

	public void setHeatingPrice2(String heatingPrice2) {
		this.heatingPrice2 = heatingPrice2;
	}

	public String getOtherPrice1() {
		return otherPrice1;
	}

	public void setOtherPrice1(String otherPrice1) {
		this.otherPrice1 = otherPrice1;
	}

	public String getOtherPrice2() {
		return otherPrice2;
	}

	public void setOtherPrice2(String otherPrice2) {
		this.otherPrice2 = otherPrice2;
	}
	
	
}
