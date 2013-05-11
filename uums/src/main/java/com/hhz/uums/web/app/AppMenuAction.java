/**
 * 
 */
package com.hhz.uums.web.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.app.AppMenuManager;
import com.hhz.uums.dao.app.AppModuleManager;
import com.hhz.uums.dao.app.AppPageManager;
import com.hhz.uums.dao.app.AppRoleMenuRelManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.entity.app.AppMenu;
import com.hhz.uums.entity.app.AppPage;
import com.hhz.uums.entity.app.AppRoleMenuRel;
import com.hhz.uums.web.CrudActionSupport;

/**
 * @author huangj 2010-11-16
 */
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-menu.action", type = "redirect") })
public class AppMenuAction extends CrudActionSupport<AppMenu> {

	private static final long serialVersionUID = 5867754654690881334L;
	private String appModuleId;
	private String appPageId;
	private AppMenu entity;

	@Autowired
	private AppModuleManager appModuleManager;
	@Autowired
	private PlasRoleManager plasRoleManager;
	@Autowired
	private AppMenuManager appMenuManager;
	@Autowired
	private AppPageManager appPageManager;
	@Autowired
	private AppRoleMenuRelManager appRoleMenuRelManager;
	

	@Override
	protected void prepareModel() throws Exception {
		if(StringUtils.isBlank(getId())){
			entity = new AppMenu();
		}else{
			entity = appMenuManager.getEntity(getId());
		}
	}
	public AppMenu getModel() {
		return entity;
	} 
	
	@Override
	public void prepareInput() throws Exception{
		 prepareModel();
	}

	@Override
	public String input() throws Exception {
		if(StringUtils.isNotBlank(getAppModuleId())){
			entity.setAppModule(appModuleManager.getEntity(getAppModuleId()));
		}
	 	List<AppPage> list = appPageManager.getAll("createdDate", false);
		Struts2Utils.getRequest().setAttribute("appPageList", list);
		return INPUT;
	}
 
	@Override
	public String list() throws Exception {
		return null;
	}

	@Override
	public void prepareSave() throws Exception{
		prepareModel();
	}
	@Override
	public String save() throws Exception {
		//关联模块
		if(StringUtils.isNotBlank(getAppModuleId())){
			entity.setAppModule(appModuleManager.getEntity(getAppModuleId()));
		}
		//关联页面
		if(StringUtils.isNotBlank(getAppPageId())){
			entity.setAppPage(appPageManager.getEntity(getAppPageId()));
		}
		appMenuManager.saveAppMenu(entity);
		
		//返回appMenuId
		Struts2Utils.renderHtml(entity.getAppMenuId());
		return null;
	}
	
	/**
	 * 校验菜单是否与角色已建立关系
	 * @param id 
	 * 注意:validateDelete 关键字，不能用
	 */
	public void validateEnableDelete() throws Exception{
		String tmpId = Struts2Utils.getParameter("id").trim();
		entity = appMenuManager.getEntity(tmpId);
		
		StringBuffer tipBuf = new StringBuffer("");
		for(AppRoleMenuRel tmpObj:entity.getAppRoleMenuRels()){
			tipBuf.append(tmpObj.getRoleCd() + "-" +plasRoleManager.getPlasRoleNameByRoleCd(tmpObj.getRoleCd()) +"\n");
		}
		String result = tipBuf.toString();
		if(StringUtils.isBlank(result)){
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("当前页面已授权给如下角色:\n" +result);
		}
	}
	
	@Override
	public String delete() throws Exception {
		String tmpId = Struts2Utils.getParameter("id").trim();
		entity = appMenuManager.getEntity(tmpId);
		
		if(entity.getAppRoleMenuRels()!= null){
			for(AppRoleMenuRel obj:entity.getAppRoleMenuRels()){
				appRoleMenuRelManager.delete(obj);	
			}
		}
		appMenuManager.deleteAppMenu(getId());
		Struts2Utils.renderText("success");
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return RELOAD;
	}
	 
	public Map<String,String> getMapEnableFlg(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("0", String.valueOf(true));
		map.put("1", String.valueOf(false));
		return map;
	}

	public String getAppModuleId() {
		return appModuleId;
	}

	public void setAppModuleId(String appModuleId) {
		this.appModuleId = appModuleId;
	}
	public String getAppPageId() {
		return appPageId;
	}
	public void setAppPageId(String appPageId) {
		this.appPageId = appPageId;
	}

	/**
	 * 删除菜单
	 * @param menuId
	 * 
	 * @return
	 */
	public String removeMenu(){
		String tmpMenuId = Struts2Utils.getParameter("menuId").trim();
		appMenuManager.deleteAppMenu(tmpMenuId);
		Struts2Utils.renderText("success");
		return null;
	}
}
