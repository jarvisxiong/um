package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 账套建立申请表
 * @author shixy
 *
 * 2010-12-23
 */
public class EasAccountCreateSheet extends BaseTemplate {
	/**
	 * 申请人		
	 */
	private String applicant;
	/**
	 * 联系电话	
	 */
	private String tel;
	/**
	 * 帐套单位名称		
	 */
	private String accountName;
	/**
	 * 是否上市帐套	□是   □否
	 */
	private String listedAccountFlg1;
	private String listedAccountFlg2;
	/**
	 * 初始建账日期		
	 */
	private String initCreatedDate;
	/**
	 * 是否有科目初始余额	□是       □否
	 */
	private String initBalanceFlag1; 
	private String initBalanceFlag2; 
	/**
	 * 帐套操作用户姓名\职位\权限说明	
	 */
	private String accountDesc;
	
	
	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return applicant;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return tel;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return accountName;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getInitCreatedDate() {
		return initCreatedDate;
	}

	public void setInitCreatedDate(String initCreatedDate) {
		this.initCreatedDate = initCreatedDate;
	}


	public String getListedAccountFlg1() {
		return listedAccountFlg1;
	}

	public void setListedAccountFlg1(String listedAccountFlg1) {
		this.listedAccountFlg1 = listedAccountFlg1;
	}

	public String getListedAccountFlg2() {
		return listedAccountFlg2;
	}

	public void setListedAccountFlg2(String listedAccountFlg2) {
		this.listedAccountFlg2 = listedAccountFlg2;
	}

	public String getInitBalanceFlag1() {
		return initBalanceFlag1;
	}

	public void setInitBalanceFlag1(String initBalanceFlag1) {
		this.initBalanceFlag1 = initBalanceFlag1;
	}

	public String getInitBalanceFlag2() {
		return initBalanceFlag2;
	}

	public void setInitBalanceFlag2(String initBalanceFlag2) {
		this.initBalanceFlag2 = initBalanceFlag2;
	}

	public String getAccountDesc() {
		return accountDesc;
	}

	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}

}
