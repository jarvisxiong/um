/**
 * 
 */
package com.hhz.uums.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author huangj 2010-4-8
 */
public class SortInfoFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		String forewordPage = request.getParameter("forewordPage");
		if (request.getSession() != null) {
			if (StringUtils.isNotBlank(forewordPage)) {
				request.getSession().setAttribute("forewordPage",forewordPage);
			}
			chain.doFilter(request, response);
		}
	}

}
