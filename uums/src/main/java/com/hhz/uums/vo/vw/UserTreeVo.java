package com.hhz.uums.vo.vw;

import com.hhz.uums.entity.plas.PlasUser;

public class UserTreeVo extends PlasUser{
	private String plasUserId;
	private String userName;
	private String userStatusCd;
	public String getPlasUserId() {
		return plasUserId;
	}
	public void setPlasUserId(String plasUserId) {
		this.plasUserId = plasUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserStatusCd() {
		return userStatusCd;
	}
	public void setUserStatusCd(String userStatusCd) {
		this.userStatusCd = userStatusCd;
	}

	
}

