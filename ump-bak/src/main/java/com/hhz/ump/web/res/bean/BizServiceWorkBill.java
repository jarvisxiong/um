package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

//商业公司营运工作审批表(eg: 商业公司营运工作审批表) 
public class BizServiceWorkBill extends BaseTemplate {
	// 项目名称
	// 审批内容
	// □进退场 □停开业 □装修 □验收 □商户营业时间
	// □商户营业人员聘用 □商户货品价格 □商户进出货
	// □商户经营规范责任书
	// 内容简述（详细内容附后）

	private String projectName;
	private String projectCd;

	private String typeCd1;
	private String typeCd2;
	private String typeCd3;
	private String typeCd4;
	private String typeCd5;
	private String typeCd6;
	private String typeCd7;
	private String typeCd8;
	private String typeCd9;
	private String typeCd10;//其他

	private String contentDesc;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getTypeCd1() {
		return typeCd1;
	}

	public void setTypeCd1(String typeCd1) {
		this.typeCd1 = typeCd1;
	}

	public String getTypeCd2() {
		return typeCd2;
	}

	public void setTypeCd2(String typeCd2) {
		this.typeCd2 = typeCd2;
	}

	public String getTypeCd3() {
		return typeCd3;
	}

	public void setTypeCd3(String typeCd3) {
		this.typeCd3 = typeCd3;
	}

	public String getTypeCd4() {
		return typeCd4;
	}

	public void setTypeCd4(String typeCd4) {
		this.typeCd4 = typeCd4;
	}

	public String getTypeCd5() {
		return typeCd5;
	}

	public void setTypeCd5(String typeCd5) {
		this.typeCd5 = typeCd5;
	}

	public String getTypeCd6() {
		return typeCd6;
	}

	public void setTypeCd6(String typeCd6) {
		this.typeCd6 = typeCd6;
	}

	public String getTypeCd7() {
		return typeCd7;
	}

	public void setTypeCd7(String typeCd7) {
		this.typeCd7 = typeCd7;
	}

	public String getTypeCd8() {
		return typeCd8;
	}

	public void setTypeCd8(String typeCd8) {
		this.typeCd8 = typeCd8;
	}

	public String getTypeCd9() {
		return typeCd9;
	}

	public void setTypeCd9(String typeCd9) {
		this.typeCd9 = typeCd9;
	}

	public String getContentDesc() {
		return contentDesc;
	}

	public void setContentDesc(String contentDesc) {
		this.contentDesc = contentDesc;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTypeCd10() {
		return typeCd10;
	}

	public void setTypeCd10(String typeCd10) {
		this.typeCd10 = typeCd10;
	}

}
