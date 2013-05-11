package com.hhz.uums.web.plas;

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
import com.hhz.uums.dao.plas.PlasRoleGroupManager;
import com.hhz.uums.dao.plas.PlasUserManager;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.utils.TreePanelUtil;
import com.hhz.uums.vo.vw.TreePanelNode;

/**
 * ------------------------------------------------------- date || author ||
 * Description || 2011-2-12 || jiaoxiaofeng || create ||PlasRankAction.java
 * Description::职级模块:指定职级的机构用户树
 * -------------------------------------------------------
 **/
@Results({ @Result(name = CrudActionSupport.RELOAD, location = "plas-rand-tree.action", type = "redirect") })
public class PlasRankTreeAction extends CrudActionSupport<PlasUser> {

	private static final long serialVersionUID = -1972110454751502245L;
	@Autowired
	private PlasUserManager plasUserManager;

	@Autowired
	PlasRoleGroupManager plasRoleGroupManager;
	private PlasUser entity;
	public String dictCd;
	public String rankUserIds = "";// 存储属于指定职级的用户id

	public PlasUser getModel() {
		return entity;
	}

	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	public String getDictCd() {
		return dictCd;
	}

	public void setDictCd(String dictCd) {
		this.dictCd = dictCd;
	}

	public String getRankUserIds() {
		return rankUserIds;
	}

	public void setRankUserIds(String rankUserIds) {
		this.rankUserIds = rankUserIds;
	}

	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	/**
	 * 1.查询选中角色的机构人员(只显示存在已分配人员的机构)
	 * 遗留问题：是否需要显示不属于任何机构的用户 string
	 */
	public String getRankTreeNode() {
		String secGroupId = Struts2Utils.getParameter("dictCd").trim();
		List<String> checkUserIdList = plasUserManager.getUserByPositionLevel(secGroupId);
		TreePanelNode node = TreePanelUtil.getTreeNodePanelUserPhysicalCheck(SpringSecurityUtils.getCurUiid(),checkUserIdList,true);
		Struts2Utils.renderJson(node);
		return null;
	}

	// 2保存分配记录
	public String saveBatchGroupUsers() {
		String addUserIds = Struts2Utils.getParameter("addUserIds").trim();
		String delUserIds = Struts2Utils.getParameter("delUserIds").trim();
		plasUserManager.saveBatchGroupUsers(dictCd, addUserIds, delUserIds);
		Struts2Utils.renderText("success");
		return null;
	}

	// 3.查询选中角色的明细信息
	public String getUserDetail() {
		page.setPageSize(1000);
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		HttpServletRequest request = ServletActionContext.getRequest();
		String plasUserId = request.getParameter("plasUserId").trim();
		filters.add(new PropertyFilter("EQS_plasUserId", plasUserId));
		page = plasUserManager.findPage(page, filters);
		return "userInfo";
	}

}