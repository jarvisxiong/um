/**
 * 
 */
package com.hhz.ump.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hhz.ump.util.LoginUtil;
import com.hhz.ump.util.PdCache;

/**
 * @author huangj 2010-4-8
 */
public class SortInfoFilter extends OncePerRequestFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		String macAddrRemot = request.getHeader("macAddress");
		PdCache.setServerPort(request.getServerPort());
		if (request.getSession() != null ) {
			LoginUtil.setIp(request, request.getRemoteAddr());
			LoginUtil.setMac(request, macAddrRemot);
		}
		String userPassword = request.getParameter("j_password");
		String uiid = request.getParameter("j_username");
		String idNoValid = request.getParameter("idNoValid");
		String loginMobile = request.getParameter("loginMobile");
		if (request.getSession() != null) {
			if (StringUtils.isNotBlank(userPassword)) {
				LoginUtil.setPwd(request, userPassword);
			}
			if (StringUtils.isNotBlank(uiid)) {
				LoginUtil.setUiid(request, uiid);
			}
			if (StringUtils.isNotBlank(idNoValid)) {
				LoginUtil.setIdNo(request, idNoValid);
			}
			if (StringUtils.isNotBlank(loginMobile)) {
				LoginUtil.setLoginMobile(request, loginMobile);
			}else{
				LoginUtil.removeAttribute(request, "loginMobile");
			}
			
			chain.doFilter(request, response);
		}
	}

}
