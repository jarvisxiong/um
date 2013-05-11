/**
 * 
 */
package com.hhz.uums.web.bis;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.uums.dao.bis.BisSecGroupManager;
import com.hhz.uums.dao.bis.BisSecGroupUserRelManager;
import com.hhz.uums.dao.plas.PlasAppManager;
import com.hhz.uums.entity.bis.BisSecGroup;
import com.hhz.uums.entity.bis.BisSecGroupUserRel;
import com.hhz.uums.entity.plas.PlasApp;
import com.hhz.uums.utils.TreePanelUtil;
import com.hhz.uums.vo.vw.TreePanelNode;

/**
 * @author huangbj 2009-12-25
 */
@Results( {
 @Result(name = CrudActionSupport.RELOAD, location = "sec-group-user-rel.action", type = "redirect") })
public class BisSecGroupUserRelAction extends CrudActionSupport<BisSecGroupUserRel> {

	private static final long serialVersionUID = -3445152342227169047L;

	@Autowired
	private BisSecGroupManager secGroupManager;
	
	@Autowired
	private BisSecGroupUserRelManager secGroupUserRelManager;

	@Autowired
	private PlasAppManager uaapAppManager;

	// 当前选中机构
	private BisSecGroup selectedGroup;
	
	// 当前选中机构
	private PlasApp selectedUaapApp;
	
	@Override
	public String delete() throws Exception {
		secGroupManager.deleteBisSecGroup(getId());
		Struts2Utils.renderText("success");
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {

		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		/*
		 * // 设置默认排序方式 if (!page.isOrderBySetted()) {
		 * page.setOrderBy("dispOrderNo"); page.setOrder(Page.ASC); }
		 */
		
		page.setPageSize(1000);
		page = secGroupUserRelManager.findPage(page, filters);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public BisSecGroupUserRel getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	// 2.查询选中角色的机构人员(只显示存在已分配人员的机构)
	public String getDispatchedOrgUserNode() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String secGroupId = request.getParameter("secGroupId").trim();
		List<String> userIdList = secGroupUserRelManager.getDispatchedOrgUsers(secGroupId);
		
		//TODO:
		TreePanelNode node = TreePanelUtil.getTreeNodePanelUserPhysicalCheck(SpringSecurityUtils.getCurUiid(),userIdList,true);

		return null;
	}

	// 3.查询选中角色的明细信息
	public String getAppDetail() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String uaapAppId = request.getParameter("uaapAppId").trim();
		selectedUaapApp = uaapAppManager.getEntity(uaapAppId);

		return "appdetail";
	}

	public String getGroupDetail() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String secGroupId = request.getParameter("secGroupId").trim();
		selectedGroup = secGroupManager.getEntity(secGroupId);

		return "groupdetail";
	}
	

	// 保存分配记录
	public String saveBatchGroupUsers() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String secGroupId = request.getParameter("secGroupId").trim();
		String addUserIds = request.getParameter("addUserIds").trim();
		String addUserTexts = request.getParameter("addUserTexts").trim();
		String delUserIds = request.getParameter("delUserIds").trim();
		String delUserTexts = request.getParameter("delUserTexts").trim();
		String addExtParams = request.getParameter("addExtParams").trim();
		String delExtParams = request.getParameter("delExtParams").trim();

		secGroupUserRelManager.saveBatchGroupUsers(secGroupId, addUserIds, addUserTexts, delUserIds, delUserTexts,addExtParams,delExtParams);
		Struts2Utils.renderText("true");

		return null;
	}

	public PlasApp getSelectedUaapApp() {
		return selectedUaapApp;
	}

	public void setSelectedUaapApp(PlasApp selectedUaapApp) {
		this.selectedUaapApp = selectedUaapApp;
	}


	public BisSecGroup getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(BisSecGroup selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

}
