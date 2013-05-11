 package com.hhz.uums.web.plas;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.uums.dao.app.AppSeqManager;
import com.hhz.uums.dao.plas.PlasAppManager;
import com.hhz.uums.dao.plas.PlasOperateLogManager;
import com.hhz.uums.dao.plas.PlasRoleGroupManager;
import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.entity.plas.PlasApp;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasRoleGroup;
import com.hhz.uums.utils.EasyTree;
import com.hhz.uums.utils.EasyTreeUtil;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.JXLExcelUtil;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.utils.OperConst;
import com.hhz.uums.utils.TreePanelUtil;
import com.hhz.uums.vo.vw.TreePanelNode;
import com.hhz.uums.vo.vw.VoRoleUserRel;
import com.hhz.uums.web.CrudActionSupport;

/**
 *-------------------------------------------------------
 * date    			||  author      ||  Description    ||
 * 2011-1-30		|| jiaoxiaofeng ||  create         ||PlasRoleGroupAction.java
 * Description::
 * -------------------------------------------------------
 **/
@Results( 
		{ @Result(name = CrudActionSupport.RELOAD, location = "plas-role-group.action", type = "redirect") ,
		@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName", "is", "contentDisposition","attachment;filename=${downFileName}.xls"})
		})
public class PlasRoleGroupAction  extends CrudActionSupport<PlasRoleGroup>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3522841439933808227L;
	private static Log log = LogFactory.getLog(PlasRoleGroupAction.class);

	@Autowired
	private PlasRoleGroupManager plasRoleGroupManager;
	@Autowired
	private PlasAppManager plasAppManager;
	@Autowired
	private AppSeqManager appSeqManager;
	@Autowired
	private PlasOperateLogManager logManager;
	@Autowired
	private PlasRoleManager plasRoleManager;
	
	
 
	private PlasRoleGroup entity;
	private static String ROOT_MODULE_CD = "0";// 根节点: 0-菜单树
	protected Page<PlasRoleGroup> groupPage = new Page<PlasRoleGroup>(1000);
	protected Page<PlasApp> appPage = new Page<PlasApp>(100);
	
	private String parentName;

	//以下两个属性,导出文件流使用
	private InputStream is;
	private String downFileName;
	

	public PlasRoleGroup getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {

		if (StringUtils.isNotBlank(getId())) {
			entity = plasRoleGroupManager.getEntity(getId());
		}else{
			entity = new PlasRoleGroup();
		}
	} 

	@Override
	public void prepareInput() throws Exception{
		prepareModel();
	}
	@Override
	public String input() throws Exception {
		String tmpParentId = entity.getParentId();
		if(StringUtils.isNotBlank(tmpParentId)){
			PlasApp tmpApp = plasAppManager.getEntity(tmpParentId);
			if(tmpApp!= null){
				parentName = tmpApp.getAppChnName()+"["+tmpApp.getAppBizCd()+"]";
			}
		}
		return INPUT;
	}
	@Override
	public void prepareSave() throws Exception{
		prepareModel();
	}
	@Override
	public String save() {
		 
		//角色组业务编号已经存在
		//角色组名称已经存在
		
		//若无父节点,则默认挂在根上
		if (StringUtils.isBlank(entity.getParentId())) {
			entity.setParentId(ROOT_MODULE_CD);
		}

		//角色组
		String tmpRoleGroupCd = entity.getRoleGroupCd();
		if (StringUtils.isBlank(tmpRoleGroupCd)) {
			tmpRoleGroupCd = appSeqManager.createNextValue(GlobalConstants.SEQ_ROLE_GROUP_CD).toString();
			entity.setRoleGroupCd(tmpRoleGroupCd);
		}
		
		plasRoleGroupManager.savePlasRoleGroup(entity);
		
		// 保存操作日志
		String operUiid = SpringSecurityUtils.getLoginCode();
		String operUserName = SpringSecurityUtils.getCurUserName();
		
			logManager.savePlasOperateLog(operUiid, operUserName, OperConst.ROLE, OperConst.DEL,
					new StringBuffer("[").append(entity.getRoleGroupCd()).append(",").append(entity.getRoleGroupName()).append(
							"]保存角色组成功!").toString());
		Struts2Utils.renderText("success");;
		return null;
	}
	/**
	 * 加载角色组-角色 树
	 * @return
	 */
	public String loadRolesTree(){
		EasyTree easyTree = EasyTreeUtil.createRoleGroupTree();
		JsonUtil.renderTreeJson(easyTree);
		return null;
	} 
	
	/**
	 * 待选择框的树
	 * @return
	 */
	public String loadRolesTreeWithCheck(){
//		List<PlasApp> appList = plasAppManager.getAllOrderedApps();
//		List<PlasRoleGroup> moduleList =  plasRoleGroupManager.getAllOrderedGroups();
//		List<PlasRole> roleList = plasRoleManager.getAllOrderedRoles();
//		TreePanelNode node = TreePanelUtil2.buildAppRoleTree(ConvertVoUtil.getVoAppList(appList), ConvertVoUtil.getVoGroupList(moduleList), ConvertVoUtil.getVoRoleList(roleList));
		TreePanelNode node = TreePanelUtil.createRoleGroupTreeCheck(new ArrayList<PlasRole>());
//		TreePanelNode node = TreePanelUtil2.getRootTreeNodeApp();
		Struts2Utils.renderJson(node);
		return null;
	} 

	public void prepareDelete() throws Exception{
		prepareModel();
	}
	@Override
	public String delete() {
		
		if (entity.getPlasRoles().size() == 0) {
			plasRoleGroupManager.delete(entity);
			
			// 保存操作日志
			String operUiid = SpringSecurityUtils.getLoginCode();
			String operUserName = SpringSecurityUtils.getCurUserName();
			
				logManager.savePlasOperateLog(operUiid, operUserName, OperConst.ROLE_GROUP, OperConst.DEL,
						new StringBuffer("[").append(entity.getRoleGroupCd()).append(",").append(entity.getRoleGroupName()).append(
								"]删除角色组成功!").toString());
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("存在下属角色");
		}

		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}
	@Override
	public String list() throws Exception {
		return null;
	}

	/**
	 * 校验唯一性
	 */
	public void isRoleGroupBizCdExist(){

		HttpServletRequest request = ServletActionContext.getRequest();
		String newroleGroupBizCd = request.getParameter("roleGroupBizCd").trim();
		String oldroleGroupBizCd = StringUtils.trimToNull(request.getParameter("oldRoleGroupBizCd"));

		if (plasRoleGroupManager.isPropertyUnique("roleGroupBizCd", newroleGroupBizCd, oldroleGroupBizCd)) {
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("failure");
		}

	}
	public void isRoleGroupNameExist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String newroleGroupName = request.getParameter("roleGroupName").trim();
		String oldroleGroupName = StringUtils.trimToNull(request.getParameter("oldRoleGroupName"));

		if (plasRoleGroupManager.isPropertyUnique("roleGroupName", newroleGroupName, oldroleGroupName)) {
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("failure");
		}
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Page<PlasApp> getAppPage() {
		return appPage;
	}
	public void setAppPage(Page<PlasApp> appPage) {
		this.appPage = appPage;
	}
	public Page<PlasRoleGroup> getGroupPage() {
		return groupPage;
	}

	public void setGroupPage(Page<PlasRoleGroup> groupPage) {
		this.groupPage = groupPage;
	}
	/**
	 * 导出角色与用户关系
	 * @param roleCds
	 * @return
	 */
	public String exportRoleRelList(){
		
		String roleIds = Struts2Utils.getParameter("roleids");
		try{
			long l1 = System.currentTimeMillis();
			List<VoRoleUserRel> list = plasRoleManager.getRoleRelList(roleIds);
			Map<String,Object> beanMap = new HashMap<String,Object>();
			beanMap.put("list", list);
	
			String fileName = "导出角色与用户关系清单[" + DateOperator.formatDate(new Date(), "yyyy-MM-dd") + "]";
			is = JXLExcelUtil.initJxlsInputStream(beanMap, "roleUserRelList.xls");
			downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");

			long l2 = System.currentTimeMillis();
			log.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + fileName +", 耗时 " + (l2-l1)/1000.00 + " 秒");
		}catch(Exception e){
			log.error("导出角色与用户关系清单异常!", e);
		}
		return "export";
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

}
