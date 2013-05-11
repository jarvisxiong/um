/**
 * 
 */
package com.hhz.uums.web;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 为了处理国际化切换专门建立此类
 * 
 * @author huangj 2009-12-4
 */

@Results({ 
	@Result(name = "index", location = "/desk/index.action", type = "redirect")
})
public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = -3445152342227169047L;
	
	//登陆成功
	public String login() throws Exception {
		return "index";
	}
	
	//登出
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
