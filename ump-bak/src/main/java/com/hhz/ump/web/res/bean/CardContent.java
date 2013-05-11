package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;


/**
 * 名片印刷内容确认单
 * @author Administrator
 *
 */
public class CardContent extends BaseTemplate {
	
	private String userName;//姓名
	private String userCd;//姓名
	
	private String englishName;//英文名
	
	private String mobilePhone;//手机
	
	private String telephone;//电话
	
	private String fax;//传真
	
	private String email;//Email
	
	private String company;//所属公司 
	
	private String address;//地址
	
	private String department;//部门
	
	private String position;//职位
	
	private String amount;//数量 
	
	private String date;//日期

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return null;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

}
