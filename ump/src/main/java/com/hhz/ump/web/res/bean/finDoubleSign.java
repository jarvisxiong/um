package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
/**
 * <p>财务双签人变动审批表</p>
 * @author huangjian
 * @create 2012-6-3
 */
public class finDoubleSign extends BaseTemplate {

	private String companyName;//公司名称
	private String reason1;//第一双签人（法定双签人）变动原因（附任命通知）
	private String reason2;//第二双签人（指定双签人）变动理由（简明扼要）
	//变动前
	private String oriSigner1;//第一双签人
	private String oriSigner2;//第二双签人
	//变动后
	private String newSigner1;//第一双签人
	private String newSigner2;//第二双签人


	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return companyName;
	}


	@Override
	public String getCompanyName() {
		return companyName;
	}


	@Override
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getReason1() {
		return reason1;
	}


	public void setReason1(String reason1) {
		this.reason1 = reason1;
	}


	public String getReason2() {
		return reason2;
	}


	public void setReason2(String reason2) {
		this.reason2 = reason2;
	}


	public String getOriSigner1() {
		return oriSigner1;
	}


	public void setOriSigner1(String oriSigner1) {
		this.oriSigner1 = oriSigner1;
	}


	public String getOriSigner2() {
		return oriSigner2;
	}


	public void setOriSigner2(String oriSigner2) {
		this.oriSigner2 = oriSigner2;
	}


	public String getNewSigner1() {
		return newSigner1;
	}


	public void setNewSigner1(String newSigner1) {
		this.newSigner1 = newSigner1;
	}


	public String getNewSigner2() {
		return newSigner2;
	}


	public void setNewSigner2(String newSigner2) {
		this.newSigner2 = newSigner2;
	}

	
	
}
