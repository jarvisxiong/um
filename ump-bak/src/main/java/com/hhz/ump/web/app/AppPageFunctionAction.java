/**
 * 
 */
package com.hhz.ump.web.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppFunctionManager;
import com.hhz.ump.dao.app.AppModuleMenuRelManager;
import com.hhz.ump.dao.app.AppPageManager;
import com.hhz.ump.dao.app.AppSeqManager;
import com.hhz.ump.entity.app.AppFunction;
import com.hhz.ump.entity.app.AppPage;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;
import com.hhz.ump.web.vo.VoModuleMenuPage;


/**
 * @author huangbj 2009-12-25
 */
@Namespace("/app")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-page-function.action", type = "redirect") })
public class AppPageFunctionAction extends CrudActionSupport<AppFunction> {

	private static final long serialVersionUID = -3445152342227169047L;

	@Autowired
	private AppPageManager appPageManager;

	@Autowired
	private AppFunctionManager appFunctionManager;
	@Autowired
	private AppModuleMenuRelManager appModuleMenuRelManager;

	private AppFunction entity;

	private String appPageId;

	private List<AppFunction> treeNodeFunctionList;

	private List<AppPage> treeNodePageList;
	@Autowired
	private AppSeqManager appSeqManager;

	public AppPageFunctionAction() {

	}
	public AppFunction getModel() {
		return entity;
	}

	public String getAppPageId() {
		return appPageId;
	}

	public void setAppPageId(String appPageId) {
		this.appPageId = appPageId;
	}
	public List<AppFunction> getTreeNodeFunctionList() {
		return treeNodeFunctionList;
	}

	public void setTreeNodeFunctionList(List<AppFunction> treeNodeFunctionList) {
		this.treeNodeFunctionList = treeNodeFunctionList;
	}

	public List<AppPage> getTreeNodePageList() {
		return treeNodePageList;
	}

	public void setTreeNodePageList(List<AppPage> treeNodePageList) {
		this.treeNodePageList = treeNodePageList;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (entity == null) {
			entity = new AppFunction();
			entity.setAppPage(new AppPage());
		}
	}

	@Override
	public String list() throws Exception {
		prepareModel();
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {

		return "detail";
	}

	@Override
	public void prepareInput() {

		if (StringUtils.isNotBlank(getId())) {
			entity = appFunctionManager.getEntity(getId());
		}else{
			entity = new AppFunction();
			entity.setAppPage(appPageManager.getEntity(getAppPageId()));
		}
	}


	@Override
	public String save() throws Exception {
		appFunctionManager.saveAppFunction(entity);
		return RELOAD;
	}
	@Override
	public void prepareSave() {

		if (StringUtils.isNotBlank(getId())) {
			entity = appFunctionManager.getEntity(getId());
		} else {
			entity = new AppFunction();
			entity.setAppPage(appPageManager.getEntity(getAppPageId()));
			boolean isNew = false;
			String funcCd = entity.getFunctionCd();
			if (StringUtils.isBlank(funcCd)) {
				funcCd = appSeqManager.createNextValue(GlobalConstants.SEQ_FUNCTION_CD)
						.toString();
				entity.setFunctionCd(funcCd);
				isNew = true;
				
			}
			
		}
	}

	/**
	 * 功能:删除按钮
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public String delete() throws Exception {

		// 是否做前置判断?
		appFunctionManager.deleteAppFunction(getId());
		addActionMessage(getText("common.success"));
		return RELOAD;
	}

	public void prepareDelete() {

	}

	@Override
	public String deleteBatch() throws Exception {
	 
		return RELOAD;
	}



	/**
	 * 加载页面列表
	 * 
	 * @return
	 */
	public Map<String, String> getMapAppPage() {

		Map<String, String> map = new LinkedHashMap<String, String>();
		List<AppPage> list = appPageManager.getAll();
		for (AppPage appPage : list) {
			// map.put(appPage.getAppPageId(), appPage.getPagePath());
			map.put(appPage.getAppPageId(), appPage.getPageName());
		}
		return map;
	}

	/**
	 * 支持使用Jquery.validate Ajax
	 * 
	 * 检验 角色业务编号不能重复
	 */
	public String isFunctionCdExists() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String newFunctionBizCd = request.getParameter("functionCd").trim();
		String oldFunctionBizCd = request.getParameter("oldFunctionCd")
				.trim();

		if (appFunctionManager.isPropertyUnique("functionCd",
				newFunctionBizCd, oldFunctionBizCd)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		// 因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	/**
	 * java加载teepanel树(页面 功能)
	 */
	public String loadPageFunctionData() {


		TreePanelNode node = TreePanelUtil.createAppModuleMenuFuncTree();
		Struts2Utils.renderJson(node);
		return null;
	}
	/**
	 * 
	 * Description:快速搜索账号
	 */
	public void quickSearchPageList() {
		String tmpName = Struts2Utils.getParameter("value").trim().toLowerCase();
		List<VoModuleMenuPage> result = appModuleMenuRelManager.getVoModuleMenuPageList(tmpName, 30);
		List<Map<String, String>> tmpList = new ArrayList<Map<String, String>>();

		// 增加根节点
		Map<String, String> map = new HashMap<String, String>(); 
		
		for (VoModuleMenuPage vo : result) {
			map = new HashMap<String, String>();
			map.put("moduleId", vo.getModuleId());
			map.put("moduleName", vo.getModuleName());
			map.put("menuId", vo.getMenuId());
			map.put("menuName", vo.getMenuName());
			map.put("pageId", vo.getPageId());
			map.put("pageName", vo.getPageName());
			tmpList.add(map);
		}
 
		Struts2Utils.renderJson(tmpList);
	}
}
