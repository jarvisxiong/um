package com.hhz.uums.service;

import com.hhz.uums.entity.plas.PlasAcct;

public interface IServiceClient {

	public boolean resetPassword(String uiid, String password) throws Exception;

	public boolean createAccount(PlasAcct acct, String password) throws Exception;

	public boolean editAccount(PlasAcct plasAcct) throws Exception;

	public boolean enableAccount(String uiid) throws Exception;
	
	public boolean disableAccount(String uiid) throws Exception;
}