/**
 * 
 */
package com.hhz.ump.service;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.FilterChainOrder;
import org.springframework.security.ui.SpringSecurityFilter;

/**
 * @author huangj 2010-4-7
 */
public class CustomerLoginFilter extends SpringSecurityFilter {
	private static Log log = LogFactory.getLog(CustomerLoginFilter.class);

	@Override
	protected void doFilterHttp(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if (SecurityContextHolder.getContext() != null
				&& SecurityContextHolder.getContext().getAuthentication() != null
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			// 已登录
		} else {
			// 未登录
			String localAddr = request.getLocalAddr();
			if (uri != null && uri.indexOf("/res/") != -1) {
				String queryString = request.getQueryString();
				// 网批模块
				if (localAddr.equals("pd.powerlong.com")) {
					//如果电信没登录，切换到网通
					localAddr = "http://wt.powerlong.com";
					log.info("to:"+localAddr);
					response.sendRedirect(localAddr + uri + "?" + queryString);
					return;
				}
				else if (localAddr.equals("wt.powerlong.com")){
					//如果网通没登录，切换到电信
					localAddr = "http://pd.powerlong.com";
					log.info("to:"+localAddr);
					response.sendRedirect(localAddr + uri + "?" + queryString);
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.core.Ordered#getOrder()
	 */
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return FilterChainOrder.AUTHENTICATION_PROCESSING_FILTER;
	}

}
