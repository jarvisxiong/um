package com.hhz.ump.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.Authentication;
import org.springframework.security.ui.logout.LogoutHandler;

public class PdLogoutHandler implements LogoutHandler {

	public void logout(HttpServletRequest arg0, HttpServletResponse arg1, Authentication arg2) {
		// TODO Auto-generated method stub
		System.out.println("退出系统");
	}

}
