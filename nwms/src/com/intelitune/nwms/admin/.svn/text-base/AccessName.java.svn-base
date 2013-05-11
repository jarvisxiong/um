package com.intelitune.nwms.admin;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.intelitune.nwms.service.RoleService;
import com.intelitune.nwms.service.RoleServiceImp;

public class AccessName extends net.sf.click.Page {
	
	public String result;
	
//	protected static ApplicationContext ctx = new ClassPathXmlApplicationContext("ServiceContext.xml");
	private RoleService rs =RoleServiceImp.getInstance();
	
	public AccessName(){
	}
	
	public void onGet(){
		String accessName = this.getContext().getRequestParameter("accessName");
		try
		{
			accessName = java.net.URLDecoder.decode(accessName, "UTF-8");
		} catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		String hql = "from InttRole ir where ir.roleName='"+accessName+"'";
		List ct = rs.findByquery(hql);
		if(ct.size()==0)
			result = this.getMessage("namecanbeused");
		else
			result = this.getMessage("nameexist");
	}
	
	 public String getContentType() {
	        return "text/xml; charset=UTF-8";
	    }

}