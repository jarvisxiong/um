/**
 * 
 */
package com.hhz.ump.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppModuleManager;
import com.hhz.ump.dao.app.AppModuleMenuRelManager;
import com.hhz.ump.dao.app.AppParamManager;
import com.hhz.ump.entity.app.AppModule;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.vo.ExAppModuleMenuRelVo;
import com.hhz.uums.entity.ws.WsPlasRole;

/**
 * @author huangbj 2009-12-25
 */
@Namespace("/")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "left-frame.action", type = "redirect") })
public class LeftFrameAction extends CrudActionSupport<AppModule> {
	private static final long serialVersionUID = -3445152342227169047L;

	private Page<AppModule> page = new Page<AppModule>(20);// 每页10条记录

	@Autowired
	private AppModuleManager appModuleManager;

	@Autowired
	private AppModuleMenuRelManager appModuleMenuRelManager;

	@Autowired
	private AppParamManager appParamManager;

	private AppModule entity;

	private String tmpAppModuleId;

	private List<AppModule> appModuleList;
	private List<ExAppModuleMenuRelVo> appModuleMenuRelList;

	public LeftFrameAction() {

	}

	public AppModule getModel() {
		return entity;
	}

	@Override
	public Page<AppModule> getPage() {
		return page;
	}

	public List<AppModule> getAppModuleList() {
		return appModuleList;
	}

	public void setAppModuleList(List<AppModule> appModuleList) {
		this.appModuleList = appModuleList;
	}


	public List<ExAppModuleMenuRelVo> getAppModuleMenuRelList() {
		return appModuleMenuRelList;
	}

	public void setAppModuleMenuRelList(
			List<ExAppModuleMenuRelVo> appModuleMenuRelList) {
		this.appModuleMenuRelList = appModuleMenuRelList;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (entity == null) {
			entity = new AppModule();
		}
	}

	@Override
	public String list() throws Exception {

		String uiid = SpringSecurityUtils.getCurrentUiid();

		List<WsPlasRole> roleList = Util.getPlasService().getRoleListByUiid(uiid);
		List<String> roleCdList = new ArrayList<String>();
		for (WsPlasRole role : roleList) {
			roleCdList.add(role.getRoleCd());
		}
		
		// 获取当前用户有权限模块列表(所有角色)
		appModuleList = appModuleManager.getModuleListByRoles(roleCdList);

		// 获取当前用户有权限菜单列表(所有角色)
		appModuleMenuRelList = appModuleMenuRelManager.getModuleMenuRelListByRoles(roleCdList);

		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		return SUCCESS;
	}

	@Override
	public String save() throws Exception {

		return SUCCESS;
	}

	@Override
	public String delete() throws Exception {

		return SUCCESS;
	}

	@Override
	public String deleteBatch() throws Exception {

		return SUCCESS;
	}

	public String getTmpAppModuleId() {
		return tmpAppModuleId;
	}

	public void setTmpAppModuleId(String tmpAppModuleId) {
		this.tmpAppModuleId = tmpAppModuleId;
	}

}
