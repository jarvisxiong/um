package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

//物业服务合同审批表(eg:  物业服务合同审批表（主办店/次主力店）)	
public class StrataServiceContractBill extends BaseTemplate {
  
	//合同双方	
	//    甲方		签约人	
	//    乙方		签约人	
	//合同主要内容

	private String companyName1;
	private String companyCd1;
	private String companyName2;
	private String companyCd2;
	private String companyName3;// 丙方签约人 -Add for part C by zhuxj on 2012.3.31
	private String companyCd3;// 丙方签约人 -Add for part C by zhuxj on 2012.3.31
	
	private String signerName1;
	private String signerCd1;
	private String signerName2;
	private String signerCd2;
	private String signerName3;// 丙方签约人 -Add for part C by zhuxj on 2012.3.31
	private String signerCd3;// 丙方签约人 -Add for part C by zhuxj on 2012.3.31
	
	private String contentDesc;

	public String getCompanyName1() {
		return companyName1;
	}

	public void setCompanyName1(String companyName1) {
		this.companyName1 = companyName1;
	}

	public String getCompanyCd1() {
		return companyCd1;
	}

	public void setCompanyCd1(String companyCd1) {
		this.companyCd1 = companyCd1;
	}

	public String getCompanyName2() {
		return companyName2;
	}

	public void setCompanyName2(String companyName2) {
		this.companyName2 = companyName2;
	}

	public String getCompanyCd2() {
		return companyCd2;
	}

	public void setCompanyCd2(String companyCd2) {
		this.companyCd2 = companyCd2;
	}

	public String getCompanyName3() {
		return companyName3;
	}

	public void setCompanyName3(String companyName3) {
		this.companyName3 = companyName3;
	}

	public String getCompanyCd3() {
		return companyCd3;
	}

	public void setCompanyCd3(String companyCd3) {
		this.companyCd3 = companyCd3;
	}

	public String getSignerName3() {
		return signerName3;
	}

	public void setSignerName3(String signerName3) {
		this.signerName3 = signerName3;
	}

	public String getSignerCd3() {
		return signerCd3;
	}

	public void setSignerCd3(String signerCd3) {
		this.signerCd3 = signerCd3;
	}

	public String getContentDesc() {
		return contentDesc;
	}

	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}

	public String getSignerName1() {
		return signerName1;
	}

	public void setSignerName1(String signerName1) {
		this.signerName1 = signerName1;
	}

	public String getSignerCd1() {
		return signerCd1;
	}

	public void setSignerCd1(String signerCd1) {
		this.signerCd1 = signerCd1;
	}

	public String getSignerName2() {
		return signerName2;
	}

	public void setSignerName2(String signerName2) {
		this.signerName2 = signerName2;
	}

	public String getSignerCd2() {
		return signerCd2;
	}

	public void setSignerCd2(String signerCd2) {
		this.signerCd2 = signerCd2;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return companyName2;
	}
	
}
