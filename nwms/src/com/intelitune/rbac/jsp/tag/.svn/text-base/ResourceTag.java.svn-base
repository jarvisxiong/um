package com.intelitune.rbac.jsp.tag;

//$Author: Srufle $
//$Revision: 17 $
//$Modtime: 9/26/04 10:55a $
/////////////////////////

import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.intelitune.rbac.base.AccessController;
import com.intelitune.rbac.base.Resource;
import com.intelitune.rbac.impl.AccessControllerXmlAcl;
import com.intelitune.rbac.impl.SimpleOperate;
import com.intelitune.nwms.common.CommonAccount;

/**
 * This tag is used to implement the Html End Body tag
 */

public class ResourceTag extends TagSupport implements Resource{

	private String _name;
		
	public int doStartTag() throws JspException {
		try {
			AccessController accessC = new AccessControllerXmlAcl();
			if (!accessC.checkAccess(CommonAccount.getInstance(pageContext.getSession()), this.getClass().toString(), new SimpleOperate("VIEW"))) {
				pageContext.getOut().print("<slms:disabled />");
			}
		}
		catch (Exception e) {
			
		}
		return Tag.EVAL_BODY_INCLUDE;
	}
	
	public boolean check(HttpSession session, String name) {
		
		return false;
	}
	
	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public Set getOperate() {
		  
		return null;
	}
	
}
