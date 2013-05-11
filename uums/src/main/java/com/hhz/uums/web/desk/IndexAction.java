/**
 * 
 */
package com.hhz.uums.web.desk;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.GrantedAuthority;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.app.AppModuleManager;
import com.hhz.uums.dao.app.AppRoleMenuRelManager;
import com.hhz.uums.entity.app.AppMenu;
import com.hhz.uums.entity.app.AppModule;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 为了处理国际化切换专门建立此类
 * 
 * @author huangj 2009-12-4
 */
@Namespace("/desk")
@Results({ @Result(name = "hrApprove", location = "/plas/plas-approve.action", type = "redirect") })
public class IndexAction extends ActionSupport {
	 
	private static final long serialVersionUID = 436060366605262282L;

	@Autowired
	private AppRoleMenuRelManager appRoleMenuRelManager;
	
	@Autowired
	private AppModuleManager appModuleManager;
	
	private String siteTitle;
	private String siteFooter;
	private String userCd;
	public String getUserCd() {
		return userCd;
	}
	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}
	@Override
	public String execute() throws Exception {
		
		String forwordPage = (String)Struts2Utils.getSession().getAttribute("forewordPage");
		if(StringUtils.isNotBlank(forwordPage))
			return forwordPage; 
		
		//载入当前登录用的权限菜单，用于构造后台首页
		GrantedAuthority[] auths =  SpringSecurityUtils.getCurrentUser().getAuthorities();
		StringBuffer roleCd = new StringBuffer();
		for(GrantedAuthority g : auths){
			roleCd.append(g.getAuthority()).append(",");
		}
		List<AppMenu> menus =  appRoleMenuRelManager.searchMenu(roleCd.toString());
		List<AppModule> modules = appModuleManager.getAll("sequenceNo", true);
		Map<String, List<AppMenu>> mapModule = new LinkedHashMap<String, List<AppMenu>>();
		//Map<AppModule, List<AppMenu>> mapModule = new LinkedHashMap<AppModule, List<AppMenu>>();
		for(AppModule appModule:modules){
			mapModule.put(appModule.getModuleName(), new ArrayList<AppMenu>());
		}
		for(AppMenu appMenu : menus){
			mapModule.get(appMenu.getAppModule().getModuleName()).add(appMenu);
			
		}
		
		Struts2Utils.getRequest().setAttribute("mapModule", mapModule);
		
		return super.execute();
	}
	public String getSiteTitle() {
		return siteTitle;
	}
	public void setSiteTitle(String siteTitle) {
		this.siteTitle = siteTitle;
	}
	public String getSiteFooter() {
		return siteFooter;
	}
	public void setSiteFooter(String siteFooter) {
		this.siteFooter = siteFooter;
	}
	
}
