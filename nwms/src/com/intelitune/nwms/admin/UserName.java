package com.intelitune.nwms.admin;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.intelitune.nwms.service.UserService;
import com.intelitune.nwms.service.UserServiceImp;

public class UserName extends net.sf.click.Page {

	public String result;
//	protected static ApplicationContext ctx = new ClassPathXmlApplicationContext(
//			"ServiceContext.xml");

	private UserService us = UserServiceImp.getInstance();;

	public void onGet() {
		String name = this.getContext().getRequestParameter("userName");
		String aName = null;
		try {
			aName = java.net.URLDecoder.decode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String hql = "from InttUser iu where iu.userName='" + aName + "'";
		List ct = us.findByQuery(hql);
		if (ct.size() == 0)
			result = this.getMessage("namecanbeused");
		else
			result = this.getMessage("nameexist");
	}

	public String getContentType() {
		return "text/xml; charset=UTF-8";
	}

}