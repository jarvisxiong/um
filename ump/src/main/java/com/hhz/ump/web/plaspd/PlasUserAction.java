/**
 * 
 */
package com.hhz.ump.web.plaspd;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.hhz.core.web.CrudActionSupport;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author huangbj 2009-12-25
 */
@Namespace("/plaspd")
@Results( 
	{
		@Result(name = CrudActionSupport.RELOAD, location = "plas-user.action", type = "redirect")
	})
				
public class PlasUserAction extends ActionSupport{
	
	private static final long serialVersionUID = -2509901364581697290L;

	/**
	 * 跳转至plas系统
	 * @return
	 */
	public String toPlasOrg(){
		return "toPlasOrg";
	}
	public String hrApprove(){
		return "hrApprove";
	}
}
