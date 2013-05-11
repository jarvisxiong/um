/**
 * 
 */
package com.hhz.uums.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.web.PdCache;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author huangj
 * 2010-10-19
 */
public class CacheAction extends ActionSupport {
	private static final long serialVersionUID = 1167626109103269501L;
	@Autowired
	private PdCache pdCache;

	@Override
	public String execute() throws Exception {
		pdCache.cleanCache();
		Struts2Utils.renderText("success");
		return null;
	}
}
