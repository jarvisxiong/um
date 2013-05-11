/**
 * 
 */
package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**礼品申请表
 * @author huangj
 * 2010-12-25
 */
public class GiftApplyBill  extends BaseTemplate {
	private String centerName;//中心
	private String centerCd;//中心Cd
	private String feeUnit;//费用承担单位
	private String givingObject;//赠送对象
	private List<GiftProperty> otherProperties=new ArrayList<GiftProperty>();
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	public String getCenterCd() {
		return centerCd;
	}
	public void setCenterCd(String centerCd) {
		this.centerCd = centerCd;
	}
	@Override
	public String getResProjectCd() {
		return centerCd;
	}
	@Override
	public String getResProjectName() {
		return centerName;
	}
	@Override
	public String getResTitleName() {
		return givingObject;
	}
	public String getFeeUnit() {
		return feeUnit;
	}
	public void setFeeUnit(String feeUnit) {
		this.feeUnit = feeUnit;
	}
	public String getGivingObject() {
		return givingObject;
	}
	public void setGivingObject(String givingObject) {
		this.givingObject = givingObject;
	}
	public List<GiftProperty> getOtherProperties() {
		return otherProperties;
	}
	public void setOtherProperties(List<GiftProperty> otherProperties) {
		this.otherProperties = otherProperties;
	}
}
