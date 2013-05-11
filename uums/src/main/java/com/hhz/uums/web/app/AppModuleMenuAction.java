/**
 * 
 */
package com.hhz.uums.web.app;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.orm.Page;

import com.hhz.uums.entity.app.AppMenu;
import com.hhz.uums.entity.app.AppModule;
import com.hhz.uums.utils.EasyTree;
import com.hhz.uums.utils.EasyTreeUtil;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.web.CrudActionSupport;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author huangbj 2010-11-16
 */
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-module-menu.action", type = "redirect") })
public class AppModuleMenuAction extends ActionSupport{
	private static final long serialVersionUID = -2117419521974054875L;
 
	private AppMenu entity;
 
	protected Page<AppModule> modulePage = new Page<AppModule>(1000);
 
	
	/**
	 * 加载模块-菜单 树
	 * @return
	 */
	public String loadModuleMenuTree(){
		EasyTree easyTree = EasyTreeUtil.createMenuTree(null);
		JsonUtil.renderTreeJson(easyTree);
		return null;
	} 

	public AppMenu getModel() {
		return entity;
	} 

	public Page<AppModule> getModulePage() {
		return modulePage;
	}
	public void setModulePage(Page<AppModule> modulePage) {
		this.modulePage = modulePage;
	}

}
