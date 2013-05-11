package com.hhz.ump.web.oa;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppParamManager;
import com.hhz.ump.util.TreeNode;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.vo.UserDisplayInfo;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasUser;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 类名 OaUserSelectionAction 创建者 lijin 创建日期 2010-5-17 描述 OA 模块人员选择树
 */
@Namespace("/oa")
public class OaUserSelectionAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AppParamManager appParamManager;

    private List<WsPlasUser> personList;
    
    /**
     * 打开选择人员页面
     * @return
     * @throws Exception
     */
    public String selPerson() throws Exception {
	String orgCd = SpringSecurityUtils.getCurrentDeptCd();
	if (StringUtils.isNotEmpty(orgCd)) {
	    List<WsPlasUser> users = PlasCache.getDirectUserListByCd(orgCd);
	    setPersonList(users);
	}
	return "person";
    }

    /**
     * 获取机构树
     * 
     * @return
     * @throws Exception
     */
    public String getOrgTree() throws Exception {
	Struts2Utils.renderJson(constructAllOrgTree());
	return null;
    }

    /**
     * 构造包含所有机构的树
     * 
     * @return
     */
    private TreeNode constructAllOrgTree() {
	TreeNode node = new TreeNode();
	node.setId(appParamManager.getAppOrgTreeRootCd());
	node.setText(appParamManager.getAppOrgTreeRootName());
	node.setExtParam(appParamManager.getAppOrgTreeRootCd());
	node.setOrgOrUser("1");
	node.setChildren(getChildrenNode(node));

	return node;
    }

    /**
     * 构造子机构
     * 
     * @param treeNode
     * @return
     */
    private List<TreeNode> getChildrenNode(TreeNode treeNode) {
	List<TreeNode> children = new ArrayList<TreeNode>();
	
	String parentId =  null;
	for (WsPlasOrg org : PlasCache.getOrgEnableList()) {
		parentId = PlasCache.getPhysicalParentId(org.getPlasOrgId());
		
	    if (StringUtils.isNotBlank(parentId)) {
			if (parentId.equals(treeNode.getId())) {
			    TreeNode childOrg = new TreeNode();
			    childOrg.setId(org.getPlasOrgId());
			    childOrg.setText(org.getOrgName());
			    childOrg.setChecked("0");
			    childOrg.setOrgOrUser("1");
			    childOrg.setExtParam(org.getOrgCd());
			    childOrg.setChildren(getChildrenNode(childOrg));
			    children.add(childOrg);
			}
	    }
	}

	return children;
    }
    
    /**
     * 获取指定机构下的用户列表
     * @return
     * @throws Exception
     */
    public String getUsersbyOrg() throws Exception {
	String orgCds = Struts2Utils.getParameter("orgCds");
	if (StringUtils.isNotEmpty(orgCds)) {
	    List<UserDisplayInfo> userInfos = new ArrayList<UserDisplayInfo>();
	    for (String orgCd : orgCds.split(",")) {
		List<WsPlasUser> users = PlasCache.getDirectUserListByCd(orgCd);
		if (users != null && users.size() > 0) {
		    userInfos.addAll(transf2UserInfo(users));
		}
	    }
	    Struts2Utils.renderJson(userInfos);
	}
	return null;
    }
    
    /**
     * 根据过滤条件搜索用户
     * @return
     * @throws Exception
     */
    public String getUsersByFilter() throws Exception {
	String value = Struts2Utils.getParameter("value");
	String maxNum = Struts2Utils.getParameter("maxNum");
	if (StringUtils.isNotEmpty(value)) {
	    WsPlasUser user = new WsPlasUser();
	    user.setUiid(value);
	    user.setUserName(value);
	    List<WsPlasUser> result = Util.getPlasService().getUserListByFilter(user);
	    List<UserDisplayInfo> list = transf2UserInfo(result);
	    if (StringUtils.isNotEmpty(maxNum)) {
		int num = Integer.valueOf(maxNum);
		List<UserDisplayInfo> newList;
		if (list.size() > num) {
		    newList = list.subList(0, num);
		} else {
		    newList = list.subList(0, list.size());
		}
		Struts2Utils.renderJson(newList);
	    } else {
		Struts2Utils.renderJson(list);
	    }
	}
	return null;
    }
    
    /**
     * 转换用户信息
     * @param users
     * @return
     * @throws Exception
     */
    private List<UserDisplayInfo> transf2UserInfo(List<WsPlasUser> users) throws Exception {
	List<UserDisplayInfo> list = new ArrayList<UserDisplayInfo>();
	
	UserDisplayInfo info = null;
	for (WsPlasUser user : users) {
	    info = new UserDisplayInfo();
	    info.setCenterOrgCd(PlasCache.getCenterOrgCdByUserId(user.getPlasUserId()));
	    info.setOrgBizCd(user.getOrgBizCd());
	    info.setOrgCd(user.getOrgCd());
	    info.setOrgName(user.getOrgName());
	    info.setSexCd(user.getSexCd());
	    info.setUiid(user.getUiid());
	    info.setUserBizCd(user.getUserBizCd());
	    info.setUserCd(user.getUserCd());
	    info.setUserName(user.getUserName());
	    list.add(info);
	}
	return list;
    }

    public List<WsPlasUser> getPersonList() {
        return personList;
    }

    public void setPersonList(List<WsPlasUser> personList) {
        this.personList = personList;
    }
}
