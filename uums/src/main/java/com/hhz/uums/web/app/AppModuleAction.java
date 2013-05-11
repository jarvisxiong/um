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

import com.hhz.uums.dao.app.AppModuleManager;
import com.hhz.uums.entity.app.AppModule;
import com.hhz.uums.web.CrudActionSupport;

/**
 * @author huangj 2010-11-16
 */
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-module.action", type = "redirect") })
public class AppModuleAction extends CrudActionSupport<AppModule> {

	private static final long serialVersionUID = 5867754654690881334L;

	private static String ROOT_MODULE_CD = "0";// 根节点: 0-菜单树

	private AppModule entity;

	@Autowired
	private AppModuleManager appModuleManager;
	
	private String parentId;

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isBlank(getId())) {
			entity = new AppModule();
			entity.setParentId(getParentId());
			entity.setTreeLevel(0l);
		} else {
			entity = appModuleManager.getEntity(getId());
			entity.setTreeLevel(0l);
		} 
	}

	public AppModule getModel() {
		return entity;
	}

	@Override
	public void prepareInput() throws Exception {
		prepareModel();
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		return null;
	}


	@Override
	public void prepareSave() throws Exception { 
		prepareModel();
	}

	@Override
	public String save() {
		if (StringUtils.isBlank(entity.getParentId())) {
			entity.setParentId(ROOT_MODULE_CD);
		}

		if (StringUtils.isNotEmpty(entity.getParentId()) && !ROOT_MODULE_CD.equals(entity.getParentId())) {
			AppModule moduleParent = appModuleManager.getEntity(entity.getParentId());
			entity.setTreeLevel(moduleParent.getTreeLevel().longValue() + 1);
		} else {
			entity.setTreeLevel(0l);
		}

		appModuleManager.saveAppModule(entity);
		
		//返回appModuleId
		Struts2Utils.renderHtml(entity.getAppModuleId());
		return null;
	}
 

	public void prepareDelete() throws Exception{
		prepareModel();
	}
	@Override
	public String delete() throws Exception {
		if(entity.getAppMenus().size() == 0){
			appModuleManager.deleteAppModule(getId());
			Struts2Utils.renderText("true");
		}else{
			Struts2Utils.renderText("请先删除该模块下的所有菜单,再删除该模块！");
		}
		return null;
	}

	public void prepareDeleteBatch() throws Exception{
		prepareModel();
	}
	@Override
	public String deleteBatch() throws Exception {
		return RELOAD;
	}

	public Map<String, String> getMapEnableFlg() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("0", String.valueOf(true));
		map.put("1", String.valueOf(false));
		return map;
	}
	/**
	 * 获取列表
	 * @return
	 */
	public Map<String, String> getMapModule() {
		Map<String, String> map = new HashMap<String, String>();
		List<AppModule> list = appModuleManager.getAll("sequenceNo", true);
		for (AppModule tmpModule : list) {
			map.put(tmpModule.getAppModuleId(), tmpModule.getModuleName());
		}
		return map;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 删除模块
	 * @param moduleId
	 * 
	 * @return
	 */
	public String removeModule(){
		String tmpMenuId = Struts2Utils.getParameter("moduleId").trim();
		appModuleManager.deleteAppModule(tmpMenuId);
		Struts2Utils.renderText("success");
		return null;
	}
}
