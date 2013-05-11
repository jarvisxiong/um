package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**行政事物申请表
 * @author huangj
 * 2010-12-21
 */
public class TransationBill extends BaseTemplate{
	//申请中心
	private String appCenterCd;
	private String appCenterName;
	private String finUseWay;
	private String subjects;
	private String finAmount;
	private String useTime;
	private String appReason;
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return finUseWay;
	}

	public String getFinUseWay() {
		return finUseWay;
	}

	public void setFinUseWay(String finUseWay) {
		this.finUseWay = finUseWay;
	}

	public String getSubjects() {
		return subjects;
	}

	public void setSubjects(String subjects) {
		this.subjects = subjects;
	}

	public String getFinAmount() {
		return finAmount;
	}

	public void setFinAmount(String finAmount) {
		this.finAmount = finAmount;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public String getAppReason() {
		return appReason;
	}

	public void setAppReason(String appReason) {
		this.appReason = appReason;
	}

	public String getAppCenterCd() {
		return appCenterCd;
	}

	public void setAppCenterCd(String appCenterCd) {
		this.appCenterCd = appCenterCd;
	}

	public String getAppCenterName() {
		return appCenterName;
	}

	public void setAppCenterName(String appCenterName) {
		this.appCenterName = appCenterName;
	}

}
