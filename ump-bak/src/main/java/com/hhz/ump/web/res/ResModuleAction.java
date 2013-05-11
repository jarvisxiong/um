/**
 * 
 */
package com.hhz.ump.web.res;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.res.ResApproveStepManager;
import com.hhz.ump.dao.res.ResAuthMsgManager;
import com.hhz.ump.dao.res.ResAuthTypeManager;
import com.hhz.ump.dao.res.ResAutoNodeSetManager;
import com.hhz.ump.dao.res.ResBillTempletManager;
import com.hhz.ump.dao.res.ResCenterInfoManager;
import com.hhz.ump.dao.res.ResConditonTypeManager;
import com.hhz.ump.dao.res.ResModuleManager;
import com.hhz.ump.dao.res.ResNodeManager;
import com.hhz.ump.entity.res.ResApproveStep;
import com.hhz.ump.entity.res.ResAuthMsg;
import com.hhz.ump.entity.res.ResAuthType;
import com.hhz.ump.entity.res.ResAutoNodeSet;
import com.hhz.ump.entity.res.ResCenterInfo;
import com.hhz.ump.entity.res.ResConditonType;
import com.hhz.ump.entity.res.ResModule;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.JSONUtil;

/**
 * 权责信息配置
 * 
 * @author huangj 2010-6-2
 */
@Namespace("/res")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "res-bill-templet.action", type = "redirect") })
public class ResModuleAction extends CrudActionSupport<ResAuthType> {
	@Autowired
	private ResModuleManager resModuleManager;
	@Autowired
	private ResAuthTypeManager resAuthTypeManager;
	@Autowired
	private ResConditonTypeManager resConditonTypeManager;
	@Autowired
	private ResAuthMsgManager resAuthMsgManager;
	@Autowired
	private ResApproveStepManager resApproveStepManager;
	@Autowired
	private ResBillTempletManager resBillTempletManager;
	@Autowired
	private ResNodeManager resNodeManager;
	@Autowired
	private ResCenterInfoManager resCenterInfoManager;
	@Autowired
	private ResAutoNodeSetManager resAutoNodeSetManager;
	@Autowired
	private AppDictTypeManager appDictTypeManager;

	private Page<ResApproveStep> appStepPage = new Page<ResApproveStep>(100);

	private ResAuthType entity;
	
	private ResModule resModule;
	
	private ResApproveStep resApproveStep;

	private ResConditonType resConditonType;

	private static final long serialVersionUID = 3292263342497858834L;

	private Map<String, String> templetMap = new LinkedHashMap<String, String>();


	/**
	 * 搜索分类界面(主要界面)
	 */
	@Override
	public String input() throws Exception {
		templetMap = resBillTempletManager.getTempletMap();
		Map<String, String> typeMap = appDictTypeManager.getDictDataByTypeCd("SP_RES_TYPE");
		String jsonFlg = Struts2Utils.getParameter("json");
		if (StringUtils.equals(jsonFlg, "cond")) {
			JSONUtil.renderListJson(entity.getResConditonTypes(), "yyyy-MM-dd", "resAuthType", "resApproveSteps","resCenterInfos","resAutoNodeSets");
			return null;
		}
		if (StringUtils.equals(jsonFlg, "msg")) {
			JSONUtil.renderListJson(entity.getResAuthMsgs(), "yyyy-MM-dd","resAuthType");
			return null;
		}
		
		Map<String, String> nodeMap = resNodeManager.getNodeMap();
		Map<String, String> groupNodeMap = resNodeManager.getGroupNodeMap();
		Map<String, String> timeLimitMap = appDictTypeManager.getDictDataByTypeCd("SP_RES_TIME_LIMIT");
		Map<String, String> verifyCdMap = appDictTypeManager.getDictDataByTypeCd("SP_RES_VERIFY_CD");
		Map<String, String> stepOptionCdMap = appDictTypeManager.getDictDataByTypeCd("SP_STEP_OPTION_CD");
		Map<String, String> activeMap = appDictTypeManager.getDictDataByTypeCd(DictContants.COM_ENABLED_FLG);
		//加入一二级审批级别,从字典获取
		Map<String, String> oneTwoMap = appDictTypeManager.getDictDataByTypeCd("SP_STEP_LEVEL_CD");
		
		
		String nodeMapJson = JSONObject.fromObject(nodeMap).toString();
		String groupNodeMapJson = JSONObject.fromObject(groupNodeMap).toString();
		String typeMapJson = JSONObject.fromObject(typeMap).toString();
		String timeLimitMapJson = JSONObject.fromObject(timeLimitMap).toString();
		String verifyCdMapJson = JSONObject.fromObject(verifyCdMap).toString();
		String stepOptionCdMapJson = JSONObject.fromObject(stepOptionCdMap).toString();
		String activeMapJson = JSONObject.fromObject(activeMap).toString();
		
		String oneTwoMapJson = JSONObject.fromObject(oneTwoMap).toString();
		
		Struts2Utils.getRequest().setAttribute("nodeMap", nodeMapJson);
		Struts2Utils.getRequest().setAttribute("groupNodeMap", groupNodeMapJson);
		Struts2Utils.getRequest().setAttribute("typeMap", typeMapJson);
		Struts2Utils.getRequest().setAttribute("timeLimitMap", timeLimitMapJson);
//		Struts2Utils.getRequest().setAttribute("verifyCdMap", verifyCdMapJson);
		Struts2Utils.getRequest().setAttribute("stepOptionCdMap", stepOptionCdMapJson);
		Struts2Utils.getRequest().setAttribute("activeMap", activeMapJson);
		Struts2Utils.getRequest().setAttribute("onetwoMap", oneTwoMapJson);
		
		return INPUT;
	}

	/**
	 * 权责信息配置管理首页
	 */
	@Override
	public String list() throws Exception {
		templetMap = resBillTempletManager.getTempletMap();
		return SUCCESS;
	}

	public String showSteps() throws Exception {
		JSONUtil.renderListJson(resConditonType.getResApproveSteps(), "yyyy-MM-dd", "resConditonType");
		return null;
	}

	public String showCenter() throws Exception {
		JSONUtil.renderListJson(resConditonType.getResCenterInfos(), "yyyy-MM-dd", "resConditonType");
		return null;
	}
	public String showAutoNode() throws Exception {
		JSONUtil.renderListJson(entity.getResAutoNodeSets(), "yyyy-MM-dd", "resAuthType");
		return null;
	}

	/**
	 * 删除分类
	 */
	@Override
	public String delete() throws Exception {
		resAuthTypeManager.deleteResAuthType(getId());
		return null;
	}

	/**
	 * 删除模块
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delModule() throws Exception {
		resModuleManager.deleteResModule(getId());
		return null;
	}

	/**
	 * 删除步骤
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delCondition() throws Exception {
		resConditonTypeManager.deleteResConditonType(getId());
		return null;
	}

	/**
	 * 删除步骤
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delStep() throws Exception {
		resApproveStepManager.deleteResApproveStep(getId());
		return null;
	}

	/**
	 * 编辑模块
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		return "edit";
	}

	/**
	 * 保存分类
	 */
	@Override
	public String save() throws Exception {
		String resModuleId = Struts2Utils.getParameter("parent_resModuleId");
		if(StringUtils.isNotBlank(resModuleId)){
			entity.setResModule(resModuleManager.getEntity(resModuleId));
		}
		resAuthTypeManager.saveResAuthType(entity);
		return null;
	}

	/**
	 * 保存模块
	 */
	public String saveModule() throws Exception {
		resModuleManager.saveResModule(resModule);
		return null;
	}

	/**
	 * 保存步骤
	 */
	public String saveStep() throws Exception {
		resApproveStepManager.saveResApproveStep(resApproveStep);
		return null;
	}

	/**
	 * 批量保存步骤
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveSteps() throws Exception {
		String parentDataId = (String) JSONUtil.getExtrKey("parentDataId");
		resConditonType = resConditonTypeManager.getEntity(parentDataId);
		List<ResApproveStep> insertRecords = (List<ResApproveStep>) JSONUtil.getInsertRecords(ResApproveStep.class);
		List<ResApproveStep> updateRecords = (List<ResApproveStep>) JSONUtil.getUpdateRecords(ResApproveStep.class);
		List<ResApproveStep> deleteRecords = (List<ResApproveStep>) JSONUtil.getDeleteRecords(ResApproveStep.class);
		resApproveStepManager.saveOrDeleteResApproveSteps(insertRecords, updateRecords, deleteRecords, resConditonType);
		JSONUtil.renderSuccessText();
		return null;
	}

	/**
	 * 批量保存责任中心
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveCenter() throws Exception {
		String parentDataId = (String) JSONUtil.getExtrKey("parentDataId");
		resConditonType = resConditonTypeManager.getEntity(parentDataId);
		List<ResCenterInfo> insertRecords = (List<ResCenterInfo>) JSONUtil.getInsertRecords(ResCenterInfo.class);
		List<ResCenterInfo> updateRecords = (List<ResCenterInfo>) JSONUtil.getUpdateRecords(ResCenterInfo.class);
		List<ResCenterInfo> deleteRecords = (List<ResCenterInfo>) JSONUtil.getDeleteRecords(ResCenterInfo.class);
		resCenterInfoManager.saveOrDeleteResCenterInfos(insertRecords, updateRecords, deleteRecords, resConditonType);
		JSONUtil.renderSuccessText();
		return null;
	}
	/**
	 * 批量保存额外节点
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveAutoNode() throws Exception {
		String parentDataId = (String) JSONUtil.getExtrKey("parentDataId");
		entity = resAuthTypeManager.getEntity(parentDataId);
		List<ResAutoNodeSet> insertRecords = (List<ResAutoNodeSet>) JSONUtil.getInsertRecords(ResAutoNodeSet.class);
		List<ResAutoNodeSet> updateRecords = (List<ResAutoNodeSet>) JSONUtil.getUpdateRecords(ResAutoNodeSet.class);
		List<ResAutoNodeSet> deleteRecords = (List<ResAutoNodeSet>) JSONUtil.getDeleteRecords(ResAutoNodeSet.class);
		resAutoNodeSetManager.saveOrDeleteResAutoNodeSets(insertRecords, updateRecords, deleteRecords, entity);
		JSONUtil.renderSuccessText();
		return null;
	}

	/**
	 * 保存权限
	 */
	public String saveCondition() throws Exception {
		resConditonTypeManager.saveResConditonType(resConditonType);
		return null;
	}

	/**
	 * 批量保存权限
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveConditions() throws Exception {
		String parentDataId = (String) JSONUtil.getExtrKey("parentDataId");
		entity = resAuthTypeManager.getEntity(parentDataId);
		List<ResConditonType> insertRecords = (List<ResConditonType>) JSONUtil.getInsertRecords(ResConditonType.class);
		List<ResConditonType> updateRecords = (List<ResConditonType>) JSONUtil.getUpdateRecords(ResConditonType.class);
		List<ResConditonType> deleteRecords = (List<ResConditonType>) JSONUtil.getDeleteRecords(ResConditonType.class);
		resConditonTypeManager.saveOrDeleteResConditionTypes(insertRecords, updateRecords, deleteRecords, entity);
		JSONUtil.renderSuccessText();
		return null;
	}
	public String saveMsgs() throws Exception {
		String parentDataId = (String) JSONUtil.getExtrKey("parentDataId");
		entity = resAuthTypeManager.getEntity(parentDataId);
		List<ResAuthMsg> insertRecords = (List<ResAuthMsg>) JSONUtil.getInsertRecords(ResAuthMsg.class);
		List<ResAuthMsg> updateRecords = (List<ResAuthMsg>) JSONUtil.getUpdateRecords(ResAuthMsg.class);
		List<ResAuthMsg> deleteRecords = (List<ResAuthMsg>) JSONUtil.getDeleteRecords(ResAuthMsg.class);
		resAuthMsgManager.saveOrDeleteResAuthMsgs(insertRecords, updateRecords, deleteRecords, entity);
		JSONUtil.renderSuccessText();
		return null;
	}
	
	/**
	 * 验证cd是否已经存在
	 * 
	 * @return
	 */
	public String checkCd() {
		String newCd = Struts2Utils.getParameter("newCd");
		String oldCd = Struts2Utils.getParameter("oldCd");
		String type = Struts2Utils.getParameter("type");
		boolean isUnique;
		if (type.equals("module")) {
			isUnique = resModuleManager.isPropertyUnique("moduleCd", newCd, oldCd);
		}else{
			isUnique = resAuthTypeManager.isPropertyUnique("authTypeCd", newCd, oldCd);
		}
		if (isUnique) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		return null;
	}

	public void prepareEdit() throws Exception {
		preModule();
	}

	public void prepareSaveModule() throws Exception {
		preModule();
	}

	public void prepareShowSteps() throws Exception {
		preCondition();
	}

	public void prepareShowCenter() throws Exception {
		preCondition();
	}
	public void prepareShowAutoNode() throws Exception {
		prepareModel();
	}

	public void prepareSaveStep() throws Exception {
		if (getId() != null) {
			resApproveStep = resApproveStepManager.getEntity(getId());
		} else {
			resApproveStep = new ResApproveStep();
		}
	}

	public void prepareSaveCondition() throws Exception {
		preCondition();
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = resAuthTypeManager.getEntity(getId());
		} else {
			entity = new ResAuthType();
		}
	}

	private void preModule() {
		if (getId() != null) {
			resModule = resModuleManager.getEntity(getId());
		} else {
			resModule = new ResModule();
		}
	}

	private void preCondition() {
		if (getId() != null) {
			resConditonType = resConditonTypeManager.getEntity(getId());
		} else {
			resConditonType = new ResConditonType();
		}
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return RELOAD;
	}

	public ResAuthType getModel() {
		// TODO Auto-generated method stub
		return entity;
	}
	public Map<String, String> getMapModuleType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.RES_MODULE_TYPE);
	}

	public String quickSearch() throws Exception {
		String value = Struts2Utils.getParameter("value");
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select t2 from ResModule t1 ,ResAuthType t2");
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
		
		page.setPageSize(10);
		page = resAuthTypeManager.findPage(page,  hql.toString(), param);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(ResAuthType authType : page.getResult()){
			Map<String, String> map=new HashMap<String, String>();
			map.put("authTypeCd", authType.getAuthTypeCd());
			map.put("authTypeName", authType.getAuthTypeName());
			String modulePath=getModulePath(authType.getResModule().getResModuleId());
			map.put("modulePath", modulePath);
			list.add(map);
		}
		Struts2Utils.renderJson(list);
		return null;
	}
	
	/**
	 * 搜索当前表单所在位置
	 * 
	 * @param moduleId
	 */
	private String getModulePath(String moduleId) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, moduleId);
		Object obj = resAuthTypeManager.executeFunction("{?= call fn_get_module_path(?)}", map, String.class);
		return obj.toString();
	}
	public String searchModule() throws Exception {
		String value = Struts2Utils.getParameter("value");
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("LIKES_moduleName",value));
		List<ResModule> resModules = resModuleManager.find(filters);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		//增加根节点
		Map<String, String> map=new HashMap<String, String>();
		map.put("resModuleId", "0");
		map.put("parentModuleCd", "0");
		map.put("moduleCd", "0");
		map.put("moduleName", "根节点");
		list.add(map);
		
		for(ResModule rm : resModules){
			map=new HashMap<String, String>();
			map.put("resModuleId", rm.getResModuleId());
			map.put("parentModuleCd", rm.getParentModuleCd());
			map.put("moduleCd", rm.getModuleCd());
			map.put("moduleName", rm.getModuleName());
			list.add(map);
		}
		Struts2Utils.renderJson(list);
		return null;
	}

	public Page<ResApproveStep> getAppStepPage() {
		return appStepPage;
	}

	public void setAppStepPage(Page<ResApproveStep> appStepPage) {
		this.appStepPage = appStepPage;
	}

	public ResModule getResModule() {
		return resModule;
	}

	public ResApproveStep getResApproveStep() {
		return resApproveStep;
	}

	public void setResModule(ResModule resModule) {
		this.resModule = resModule;
	}

	public void setResApproveStep(ResApproveStep resApproveStep) {
		this.resApproveStep = resApproveStep;
	}

	public Map<String, String> getTempletMap() {
		return templetMap;
	}

	public void setTempletMap(Map<String, String> templetMap) {
		this.templetMap = templetMap;
	}

	public ResConditonType getResConditonType() {
		return resConditonType;
	}

	public void setResConditonType(ResConditonType resConditonType) {
		this.resConditonType = resConditonType;
	}

}
