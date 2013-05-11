package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 航程变更申请单
 * @author shixy
 *
 * 2010-12-21
 */
public class VoyageChangeApplSheet extends BaseTemplate {

	/**
	 * 姓名
	 */
	private String userName;
	/**
	 * 用户cd
	 */
	private String userCd;
	/**
	 * 原订航程
	 */
	private String originalVoya;
	/**
	 * 需要更改和预订的航程
	 */
	private String changeVoya;
	/**
	 * 机票航程更改原因
	 */
	private String cause;
	
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return userName;
	}
	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return userCd;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return "原订航程："+originalVoya;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCd() {
		return userCd;
	}
	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}
	public String getOriginalVoya() {
		return originalVoya;
	}
	public void setOriginalVoya(String originalVoya) {
		this.originalVoya = originalVoya;
	}
	public String getChangeVoya() {
		return changeVoya;
	}
	public void setChangeVoya(String changeVoya) {
		this.changeVoya = changeVoya;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	
	
}
