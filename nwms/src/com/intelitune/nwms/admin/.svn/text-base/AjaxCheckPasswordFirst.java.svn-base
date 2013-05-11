package com.intelitune.nwms.admin;

import net.sf.click.Page;

public class AjaxCheckPasswordFirst extends Page {

	public String warn = "";

	public AjaxCheckPasswordFirst() {

	}

	@SuppressWarnings("unchecked")
	public void onGet() {
		String password = this.getContext().getRequestParameter("password");
		if (password == null || password.trim().length() == 0) {
			warn = "<img src=\"../image/userWarn/6.jpg\"></img>";// "密码不可为空";
		}
	}

}
