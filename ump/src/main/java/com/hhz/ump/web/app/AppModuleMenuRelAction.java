/**
 * 
 */
package com.hhz.ump.web.app;

import java.util.ArrayList;
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
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.app.AppMenuManager;
import com.hhz.ump.dao.app.AppModuleManager;
import com.hhz.ump.dao.app.AppModuleMenuRelManager;
import com.hhz.ump.dao.app.AppPageManager;
import com.hhz.ump.dao.app.AppSeqManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.app.AppMenu;
import com.hhz.ump.entity.app.AppModule;
import com.hhz.ump.entity.app.AppModuleMenuRel;
import com.hhz.ump.entity.app.AppPage;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;

/**
 * @author huangbj 2009-12-25
 */
/**
 * @author waveflat
 * 
 */
@Namespace("/app")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-module-menu-rel.action", type = "redirect") })
public class AppModuleMenuRelAction extends CrudActionSupport<AppModuleMenuRel> {

	private static final long serialVersionUID = -3445152342227169047L;

	@Autowired
	private AppModuleManager appModuleManager;

	@Autowired
	private AppMenuManager appMenuManager;

	@Autowired
	private AppPageManager appPageManager;

	@Autowired
	private AppAttachFileManager appAttachFileManager;

	@Autowired
	private AppSeqManager appSeqManager;

	@Autowired
	private AppModuleMenuRelManager appModuleMenuRelManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	private AppModuleMenuRel entity;

	private String appModuleId;

	private String appPageId;

	private List<AppModule> treeNodeModuleList;

	private List<AppModuleMenuRel> treeNodeModuleMenuList;

	private String bizEntityId;

	// 人员的照片
	private AppAttachFile userPhotoImg;

	// 新增用户时用于上传照片的临时实体id
	private String entityTmpId;
	
	//父菜单名称
	private String parentMenuName;

	public AppModuleMenuRelAction() {

	}

	/**
	 * 主要功能:
	 * 
	 * 1.前台 显示:左边菜单树(可点击展开),右边菜单详细信息(不可编辑)
	 * 
	 * 2.操作:
	 * 
	 * (1)单击'模块',则按钮('新增菜单')可用,按钮('修改菜单','删除菜单')都不可用
	 * 
	 * (2)单击'菜单',则按钮('新增菜单','修改菜单','删除菜单')可用
	 * 
	 * (二)后台操作
	 * 
	 * 1.显示:搜索所有模块和所有菜单(用于菜单树),并标志右边菜单详细信息不可编辑.
	 * 
	 * 2.操作
	 * 
	 * (1)新增菜单:传入参数moduleID ->初始化菜单明细->显示
	 * 
	 * (2)修改菜单:传入参数menuID ->初始化菜单明细->显示
	 * 
	 * (3)删除菜单:传入参数menuID ->初始化菜单明细->显示
	 */
	
	
	@Override
	protected void prepareModel() throws Exception {
		if (entity == null) {
			entity = new AppModuleMenuRel();
			entity.setAppModule(new AppModule());
			entity.setAppMenu(new AppMenu());
			entity.getAppMenu().setAppPage(new AppPage());
		}
		
		if (StringUtils.isNotBlank(getId())) {
			entity = appModuleMenuRelManager.getEntity(getId());
			buildPhoto(entity.getAppMenu().getAppMenuId());
		} else {
			entityTmpId = RandomUtils.generateTmpEntityId();
		}
	}

	@Override
	public String list() throws Exception {

		prepareModel();
		return SUCCESS;
	}

	/**
	 * 功能:编辑关系
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public String input() throws Exception {
		
		if(StringUtils.isNotBlank(getId())){
			entity = appModuleMenuRelManager.getEntity(getId());
	
			if (entity != null) {
				if (entity.getAppMenu() != null) {
					if (StringUtils.isNotBlank(entity.getAppMenu().getParentMenuCd())) {
						AppMenu tmp = appMenuManager.getEntityByMenuCd(entity.getAppMenu().getParentMenuCd());
						if (tmp != null) {
							parentMenuName = tmp.getMenuName();
						}
					}
				}
				if(null!=entity.getAppModule()){
					appModuleId = entity.getAppModule().getAppModuleId();
				}
			}
			buildPhoto(entity.getAppMenu().getAppMenuId());
		}
		
		return "detail";
	}

	@Override
	public void prepareInput() {
		if (entity == null) {
			entity = new AppModuleMenuRel();
			entity.setAppModule(new AppModule());
			entity.setAppMenu(new AppMenu());
			entity.getAppMenu().setAppPage(new AppPage());
		}
		if (StringUtils.isNotBlank(getId())) {
			entity = appModuleMenuRelManager.getEntity(getId());
		}
	}

	@Override
	public void prepareSave() {
		
		if (StringUtils.isNotBlank(getId())) {
			entity = appModuleMenuRelManager.getEntity(getId());
			entity.setAppModule( appModuleManager.getEntity(getAppModuleId()));
			entity.getAppMenu().setAppPage(appPageManager.getEntity(getAppPageId()));
		} else {
			entity = new AppModuleMenuRel();
			entity	.setAppModule(appModuleManager.getEntity(appModuleId));
			
			AppMenu tmpAppMenu = new AppMenu();
			tmpAppMenu.setAppPage(appPageManager.getEntity(getAppPageId()));
			entity.setAppMenu(tmpAppMenu);
		}
	}
	@Override
	public String save() throws Exception {

		AppMenu appMenu = entity.getAppMenu();
		String menuCd = appMenu.getMenuCd();
		if (StringUtils.isBlank(menuCd)) {
			menuCd = appSeqManager.createNextValue(GlobalConstants.SEQ_MENU_CD)
					.toString();
			entity.getAppMenu().setMenuCd(menuCd);
			entity.getAppMenu().setIconPath("1");
		}
		appModuleMenuRelManager.saveAppModuleMenuRel(entity);
		appAttachFileManager.updateTmpFile(entityTmpId, appMenu.getAppMenuId());
		return RELOAD;
	}


	/**
	 * 功能:删除菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public String delete() throws Exception {

		appModuleMenuRelManager.deleteAppModuleMenuRel(getId());
		if (StringUtils.isNotBlank(getId())) {
		entity = appModuleMenuRelManager.getEntity(getId());
		}
		addActionMessage(getText("common.success"));

		return RELOAD;
	}

	public void prepareDelete() {

	}

	@Override
	public String deleteBatch() throws Exception {
	 
		return SUCCESS;
	}

	/**
	 * 加载列表:字典-是否可用
	 * 
	 * @return
	 */
	public Map<String, String> getMapEnabledFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.COM_ENABLED_FLG);
	}

	/**
	 * 加载列表:字典-菜单类型
	 * 
	 * @return
	 */
	public Map<String, String> getMapAppMenuType() {
		return appDictTypeManager
				.getDictDataByTypeCd(DictContants.APP_MENU_TYPE);
	}

	/**
	 * 加载列表:所有页面
	 * 
	 * @return
	 */
	public Map<String, String> getMapAppPage() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<AppPage> list = appPageManager.getAll();
		for (AppPage appPage : list) {
			map.put(appPage.getAppPageId(), appPage.getPageName());
		}
		return map;
	}

	/**
	 * 加载列表:所有模块
	 * 
	 * @return
	 */
	public Map<String, String> getMapModule() {
		return appModuleManager.getModuleData();
	}

	public String getAppPageId() {
		return appPageId;
	}

	public void setAppPageId(String appPageId) {
		this.appPageId = appPageId;
	}

	/**
	 * 支持使用Jquery.validate Ajax
	 * 
	 * 检验 模块名称 不能重复
	 */
	public String isMenuNameExists() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String newMenuName = request.getParameter("menuName").trim();
		String oldMenuName = request.getParameter("oldMenuName").trim();

		if (appMenuManager.isPropertyUnique("menuName", newMenuName,
				oldMenuName)) {
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
	public String loadModuleMenuData() {
		
//		hidden by huangbijin 2012-02-28
//		List<AppModule> moduleList = appModuleManager.getAll();
//		List<AppModuleMenuRel> relList = appModuleMenuRelManager.getAll();
		

		//add by huangbijin 2012-02-28 
		List<AppModule> moduleList = appModuleManager.getAllOrdered();
		List<AppModuleMenuRel> relList = appModuleMenuRelManager.getAllOrdered();

		TreePanelNode node = TreePanelUtil.buildModuleMenuTree(moduleList, relList);
		Struts2Utils.renderJson(node);

		return null;
	}

	/**
	 * 打开上传照片的div
	 * 
	 * @return
	 * @throws Exception
	 */
	public String uploadPhoto() throws Exception {
		buildPhoto(this.getBizEntityId());
		return "uploadPhoto";
	}

	/**
	 * 查找上传的照片
	 * 
	 * @param bizEntityId
	 */
	private void buildPhoto(String entityId) {
		Page<AppAttachFile> attachMentPage = new Page<AppAttachFile>(1);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_bizEntityId", entityId));
		filters.add(new PropertyFilter("EQS_bizModuleCd", "appMenu"));
		filters.add(new PropertyFilter("NEQS_statusCd", "0"));
		attachMentPage = appAttachFileManager.findPage(attachMentPage, filters);

		if (attachMentPage.getResult().size() == 1) {
			userPhotoImg = attachMentPage.getResult().get(0);
		}
	}

	/**
	 * 获取照片的信息用于在前台构造照片链接地址
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchJSONPhotoInfo() throws Exception {
		if (StringUtils.isBlank(this.getId()))
			throw new IllegalArgumentException("传入的entityId不能为空");

		Page<AppAttachFile> attachMentPage = new Page<AppAttachFile>(1);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_bizEntityId", this.getId()));
		filters.add(new PropertyFilter("EQS_bizModuleCd", "appMenu"));
		filters.add(new PropertyFilter("NEQS_statusCd", "0"));
		attachMentPage = appAttachFileManager.findPage(attachMentPage, filters);

		String info = "";
		if (attachMentPage.getResult().size() == 1) {
			userPhotoImg = attachMentPage.getResult().get(0);
			StringBuilder json = new StringBuilder("{");
			json.append("'fileName': '" + userPhotoImg.getFileName() + "',");
			json.append("'realFileName': '" + userPhotoImg.getRealFileName()
					+ "'}");
			info = json.toString();
		}

		Struts2Utils.renderText(info);
		return null;
	}

	public String getBizEntityId() {
		return bizEntityId;
	}

	public void setBizEntityId(String bizEntityId) {
		this.bizEntityId = bizEntityId;
	}
	public String getEntityTmpId() {
		return entityTmpId;
	}

	public void setEntityTmpId(String entityTmpId) {
		this.entityTmpId = entityTmpId;
	}

	public AppAttachFile getUserPhotoImg() {
		return userPhotoImg;
	}

	public void setUserPhotoImg(AppAttachFile userPhotoImg) {
		this.userPhotoImg = userPhotoImg;
	}

	public AppModuleMenuRel getModel() {
		return entity;
	}

	public String getAppModuleId() {
		return appModuleId;
	}

	public void setAppModuleId(String appModuleId) {
		this.appModuleId = appModuleId;
	}
	public List<AppModule> getTreeNodeModuleList() {
		return treeNodeModuleList;
	}

	public void setTreeNodeModuleList(List<AppModule> treeNodeModuleList) {
		this.treeNodeModuleList = treeNodeModuleList;
	}

	public List<AppModuleMenuRel> getTreeNodeModuleMenuList() {
		return treeNodeModuleMenuList;
	}

	public void setTreeNodeModuleMenuList(
			List<AppModuleMenuRel> treeNodeModuleMenuList) {
		this.treeNodeModuleMenuList = treeNodeModuleMenuList;
	}
	
	public String getParentMenuName() {
		return parentMenuName;
	}
	
	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}

}
