package com.intelitune.nwms.admin;

import java.util.List;

import net.sf.click.Page;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.intelitune.nwms.service.UserService;
import com.intelitune.nwms.service.UserServiceImp;

public class AjaxCheckUserCreate extends Page {

	public String warn = "";
	
//	public static ApplicationContext ctx = new ClassPathXmlApplicationContext("ServiceContext.xml");
	public UserService userService = UserServiceImp.getInstance();;
	
	public AjaxCheckUserCreate() {
		
	}
	
	@SuppressWarnings("unchecked")
	public void onGet() {
		String userName = this.getContext().getRequestParameter("userName");
		if(userName != null && userName.trim().length() > 0) {
			List l = userService.findByQuery("from InttUser as iu where iu.userName = '" + userName + "' ");
			if(l.size() > 0) {
				warn = "<img src=\"../image/userWarn/3.jpg\"></img>";//"该用户已经存在";
			}
			else {
				warn = "<img src=\"../image/userWarn/1.jpg\"></img>";//"可以使用该用户";
			}
		}
		else {
			warn = "<img src=\"../image/userWarn/4.jpg\"></img>";//"必须填写用户名";
		}
	}
	
}
