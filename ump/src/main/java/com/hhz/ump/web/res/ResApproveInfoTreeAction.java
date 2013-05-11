package com.hhz.ump.web.res;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.res.ResAuthTypeManager;
import com.hhz.ump.dao.res.ResModuleManager;
import com.hhz.ump.entity.res.ResAuthType;
import com.hhz.ump.entity.res.ResModule;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * <p>构造树类</p>
 * 
 * @author  hy
 * @version 1.00 2011-11-15
 */
@Namespace("/res")
public class ResApproveInfoTreeAction extends ActionSupport {

	@Autowired
	private  ResModuleManager resModuleManager;

	@Autowired
	private  ResAuthTypeManager resAuthTypeManager;
	
	//搜索表单
	private List<ResModule> searchModuleList = new ArrayList<ResModule>(); 
	
	
	
	
	
	// 内容
	public static String NODE_RES_MODULE = "module";
	public static String NODE_RES_AUTH = "authType";
	
	/**
	 * 顶级机构节点
	 */
	private List<TreePanelNode> rootNoodes;
	
	private static final long serialVersionUID = 1790111212149137268L;
	private String moduleTypeCdSrh;
	public String getResAuthTypeListByResModule(){
		String result="";

		String orgId=Struts2Utils.getParameter("orgId");
		String moduleCd = Struts2Utils.getParameter("moduleCd");
		if (StringUtils.isNotBlank(moduleCd)) {
			List<ResAuthType> authTypes = resAuthTypeManager.loadActiveChilderResAuthType(orgId);
			for (int i = 0; i < authTypes.size(); i++) {
				if (result.length() > 0) {
					result += ",";
				}
				result += "'" + authTypes.get(i).getAuthTypeCd() + "'";
			}
		}
		Struts2Utils.renderText(result);
		return null;
	}
	
	public String buildTree() {
		String active = Struts2Utils.getParameter("active");
		String isChecked = Struts2Utils.getParameter("isChecked");
		String orgId=Struts2Utils.getParameter("orgId");
		String moduleCd = Struts2Utils.getParameter("moduleCd");
		TreePanelNode rootNode = new TreePanelNode();
		List<ResModule> modules;
		List<ResAuthType> authTypes;
		if(StringUtils.isNotBlank(moduleCd)){
				modules = resModuleManager.loadActiveChilderResModule(moduleCd,getModuleTypeCdSrh());
				authTypes = resAuthTypeManager.loadActiveChilderResAuthType(orgId);
		}else{
				modules = resModuleManager.loadActiveChilderResModule("0",getModuleTypeCdSrh());
				authTypes = new ArrayList<ResAuthType>();
		}
			
		if(StringUtils.isBlank(orgId)){
			rootNode = initTopOrg();
		}else{
			rootNode = getTreePanelModuleNoChild(resModuleManager.getEntity(orgId),isChecked);
		}
		String title="表单模板(按权责审批表分类)";
		if (!getModuleTypeCdSrh().equals(ResConstants.MODULE_TYPE_CD_RES)){
			title="表单模板";
		}
		TreePanelNode node = buildProjectModuleTree(modules, authTypes,title ,rootNode);
		Struts2Utils.renderJson(node);
		return null;
	}
	
	//初始化顶级机构
	public TreePanelNode initTopOrg(){
		String title="表单模板(按权责审批表分类)";
		if (!getModuleTypeCdSrh().equals(ResConstants.MODULE_TYPE_CD_RES)){
			title="表单模板";
		}
		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId("0");
		rootNode.setText(title);
		rootNode.setEntityId("0");// 特殊处理
		rootNode.setEntityCd("0");// 特殊处理
		rootNode.setOrgOrUser(NODE_RES_MODULE);
		rootNode.setNodeType(NODE_RES_MODULE);
		return rootNode;
	}
	
	public String getTreeByOrgId(){
		String orgId=Struts2Utils.getParameter("orgId");
		TreePanelNode node = PlasCache.getTreeNodeByOrgId(orgId);
		Struts2Utils.renderJson(node);
		return null;
	}

	public  TreePanelNode buildProjectModuleTree(List<ResModule> moduleList, List<ResAuthType> authList, String rootTitle,TreePanelNode rootNode) {
		return buildProjectModuleTree(moduleList, authList, rootTitle, TreePanelUtil.NODE_CHECKED_UNDEFINED, null,rootNode);
	}
	
	public  TreePanelNode buildProjectModuleTree(List<ResModule> moduleList, List<ResAuthType> authList, String rootTitle, String checked,
			Map<String, String> mapCd2Id,TreePanelNode rootNode) {

		

		// 模块与分类
		Map<String, List<ResAuthType>> moduleAuthsMap = getModuleAuthsMap(authList);

		// 模块与模块关系
		Map<String, List<ResModule>> moduleModulesMap = getModuleModulesMap(moduleList);

		// 设置子孙节点
		rootNode.setChildren(getChildrenNode(rootNode, moduleModulesMap, moduleAuthsMap, checked, mapCd2Id));
		return rootNode;
	}
	

	// 遍历人员,设置模块与分类关系
	private  Map<String, List<ResAuthType>> getModuleAuthsMap(List<ResAuthType> auths) {

		Map<String, List<ResAuthType>> orgUsersMap = new HashMap<String, List<ResAuthType>>();
		for (ResAuthType auth : auths) {
			String parentId = auth.getResModule().getResModuleId();

			if (StringUtils.isNotBlank(parentId)) {
				if (orgUsersMap.containsKey(parentId)) {
					orgUsersMap.get(parentId).add(auth);
				} else {
					List<ResAuthType> newUserList = new ArrayList<ResAuthType>();
					newUserList.add(auth);
					orgUsersMap.put(parentId, newUserList);
				}
			}
		}
		return orgUsersMap;
	}

	// 遍历机构,设置模块与模块关系
	private  Map<String, List<ResModule>> getModuleModulesMap(List<ResModule> modules) {

		Map<String, List<ResModule>> moduleModulesMap = new HashMap<String, List<ResModule>>();
		for (ResModule module : modules) {
			String parentCd = module.getParentModuleCd();
			if (StringUtils.isNotBlank(parentCd)) {
				if (moduleModulesMap.containsKey(parentCd)) {
					moduleModulesMap.get(parentCd).add(module);
				} else {
					List<ResModule> newModuleList = new ArrayList<ResModule>();
					newModuleList.add(module);
					moduleModulesMap.put(parentCd, newModuleList);
				}
			}
		}
		return moduleModulesMap;
	}
	
	private  List<TreePanelNode> getChildrenNode(TreePanelNode treeNode, Map<String, List<ResModule>> moduleModulesMap,
			Map<String, List<ResAuthType>> moduleAuthsMap, String checked, Map<String, String> mapCd2Id) {
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		String parentId = treeNode.getId();
		String parentCd = treeNode.getEntityCd();
		// 先分类
		List<ResAuthType> auths = moduleAuthsMap.get(parentId);
		if (auths != null && auths.size() > 0) {
			for (ResAuthType auth : auths) {
				TreePanelNode authNodes = getTreePanelAuthNoChild(auth, checked);
				children.add(authNodes);
			}
		}else{
			
		}
		// 后模块
		List<ResModule> modules = moduleModulesMap.get(parentCd);
		if (modules != null && modules.size() > 0) {
			for (ResModule module : modules) {
				TreePanelNode moduleNode = getTreePanelModuleNoChild(module, checked);
				
				//搜索该模块是否有子模块
				long isChildrens = resModuleManager.countActiveChilderResModule(module.getModuleCd(),getModuleTypeCdSrh());
				if(isChildrens > 0 ){
					moduleNode.setEntityStatusCd("isChildrens");
				}
				//是否有分类
				long authsChildrens = resAuthTypeManager.countActiveChilderResAuthType(module.getResModuleId());;
				if(authsChildrens > 0 ){
					moduleNode.setEntityStatusCd("isChildrens");
				}
				
				children.add(moduleNode);
			}
		}
		return children;
	}
	
	private  TreePanelNode getTreePanelAuthNoChild(ResAuthType auth, String checked) {

		TreePanelNode userNode = new TreePanelNode();
		userNode.setId(auth.getResAuthTypeId());
		userNode.setText(auth.getAuthTypeName());
		userNode.setEntityCd(auth.getAuthTypeCd());
		userNode.setEntityName(auth.getDisplayName());
		userNode.setOrgOrUser(NODE_RES_AUTH);
		userNode.setNodeType(NODE_RES_AUTH);
		userNode.setChecked(checked);
		// TODO 如果当前用户不能发起该表单，就将此属性设置成false
		userNode.setEntityStatusCd(BooleanUtils.toStringTrueFalse(auth.getPublish()));//
		// 表单是否上线
		if (null == auth.getPublish()) {
			userNode.setExtParam("0");
		} else {
			userNode.setExtParam(BooleanUtils.toString(auth.getPublish(), "1", "0"));
		}
		return userNode;
	}
	
	private  TreePanelNode getTreePanelModuleNoChild(ResModule module, String checked) {
		TreePanelNode node = new TreePanelNode();
		node.setId(module.getResModuleId());
		node.setText(module.getModuleName());
		node.setEntityCd(module.getModuleCd());
		node.setEntityName(module.getModuleName());
		node.setChecked(checked);
		node.setOrgOrUser(NODE_RES_MODULE);
		node.setNodeType(NODE_RES_MODULE);
		return node;
	}
	

/**
 * 功能: 模糊搜索表单列表
 * 
 * @return
 * @throws Exception
 */
	public String searchTreeList()  throws Exception{
		String value = Struts2Utils.getParameter("value");
		Map<String, Object> param = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(value)){
			StringBuffer hql = new StringBuffer();
			hql.append("select new ResModule(t1.resModuleId,t1.moduleCd,t1.moduleName,t2) from ResModule t1 ,ResAuthType t2");
			hql.append(" where t1.resModuleId = t2.resModule.resModuleId ");
			hql.append(" and t1.active = :active");
			hql.append(" and t2.active = :authActive");
			hql.append(" and t2.displayName like :authTypeName");
			hql.append(" order by t2.publish desc");
			//param.put("publish", true);
			//排序按宝龙地产、宝龙商业、行业、酒店、事业排序 start
			hql.append(" , ");
			hql.append(" case when ");
			//商业
			hql.append(" t2.authTypeCd like :BLSY1");
			hql.append(" or t2.authTypeCd like :BLSY2");
			hql.append(" then 2");
			//行业
			hql.append(" when t2.authTypeCd like :BLHY1");
			hql.append(" or t2.authTypeCd like :BLHY2");
			hql.append(" then 3");
			//酒店
			hql.append(" when t2.authTypeCd like :BLJD1");
			hql.append(" then 4");
			//事业
			hql.append(" when t2.authTypeCd like :BLPM1");
			hql.append(" then 5");
			//宝龙地产
			hql.append(" else 1");
			hql.append(" end ");
			
			param.put("BLSY1", "BLSY%");
			param.put("BLSY2", "SY%");
			param.put("BLHY1", "BLHY%");
			param.put("BLHY2", "HY%");
			param.put("BLJD1", "JD%");
			param.put("BLPM1", "BLPM%");
			//排序按宝龙地产、宝龙商业、行业、酒店、事业排序 end
			
			param.put("active", true);
			param.put("authActive", true);
			param.put("authTypeName", "%"+value+"%");
			List<ResModule> tList = resModuleManager.find(hql.toString(), param);
			for(ResModule rm : tList){
				String t = getModulePath(rm.getResModuleId());
				if(t != null){
					rm.setRemark(t);
					searchModuleList.add(rm);
				}
				
			}
		}
		
		return "search";
	}

	/**
	 * 搜索当前表单所在位置
	 * 
	 * @param moduleId
	 */
	private String getModulePath(String moduleId) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, moduleId);
		Object obj;
		try{
			//存在垃圾数据,暂时先捕获异常
			obj = resModuleManager.executeFunction("{?= call fn_get_module_path(?)}", map, String.class);
			return obj.toString();
		}catch(Exception e){
			return null;
		}
		
		
	}

	/**
	 * @return the searchModuleList
	 */
	public List<ResModule> getSearchModuleList() {
		return searchModuleList;
	}

	/**
	 * @param searchModuleList the searchModuleList to set
	 */
	public void setSearchModuleList(List<ResModule> searchModuleList) {
		this.searchModuleList = searchModuleList;
	}

	public String getModuleTypeCdSrh() {
		if (StringUtils.isBlank(moduleTypeCdSrh)){
			moduleTypeCdSrh=ResConstants.MODULE_TYPE_CD_RES;
		}
		return moduleTypeCdSrh;
	}

	public void setModuleTypeCdSrh(String moduleTypeCd) {
		this.moduleTypeCdSrh = moduleTypeCd;
	}

	

}
