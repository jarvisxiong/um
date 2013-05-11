/**
 * 
 */
package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**办公资产借用单
 * @author Administrator
 *
 */
public class OfficeAssetsBorrowBill  extends BaseTemplate {
	private String borrowPeriod;//借用期间
	private String operator;//经办人
	private String centerName;//借用公司/中心
	private String centerCd;//
	private String borrowPerson;//借用责任人
	private String totalMoney;//金额
	private String reason;//申请借用原因
	private List<OfficeAssetsProperty> otherProperties=new ArrayList<OfficeAssetsProperty>();
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
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
	public String getBorrowPerson() {
		return borrowPerson;
	}
	public void setBorrowPerson(String borrowPerson) {
		this.borrowPerson = borrowPerson;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public List<OfficeAssetsProperty> getOtherProperties() {
		return otherProperties;
	}
	public void setOtherProperties(List<OfficeAssetsProperty> otherProperties) {
		this.otherProperties = otherProperties;
	}
	public String getBorrowPeriod() {
		return borrowPeriod;
	}
	public void setBorrowPeriod(String borrowPeriod) {
		this.borrowPeriod = borrowPeriod;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return centerName;
	}
}
