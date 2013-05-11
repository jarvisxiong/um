package com.intelitune.nwms.admin;

import net.sf.click.Page;

public class AjaxCheckPassword extends Page {

	public String warn = "";

	public AjaxCheckPassword() {

	}

	@SuppressWarnings("unchecked")
	public void onGet() {
		String password = this.getContext().getRequestParameter("password");
		String passwordAgain = this.getContext().getRequestParameter("passwordAgain");
		if (password != null && passwordAgain != null && password.trim().length() > 0 && passwordAgain.trim().length() > 0) {
			if (password.equals(passwordAgain)) {
				warn = "<img src=\"../image/userWarn/2.jpg\"></img>";// "密码一致";
			} else {
				warn = "<img src=\"../image/userWarn/5.jpg\"></img>";// "密码不一致";
			}
		} else {
			warn = "<img src=\"../image/userWarn/6.jpg\"></img>";// "密码不可为空";
		}
	}

}
