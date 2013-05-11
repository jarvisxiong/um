package com.hhz.ump.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;

import com.hhz.uums.entity.ws.WsPlasOrg;


/**
 * 
 * 类名		OrgTreeUtil
 * 创建者	lijin
 * 创建日期	2010-3-2
 * 描述		构造机构数的工具类
 */
public class OrgTreeUtil {
    // 定义常量来标识树节点的三种状态，选中，全选中，半选中
    private static final String UNCHECKED = "0";		// 未选中
    private static final String CHECKED = "1";			// 全选中
    private static final String HALF_CHECKED = "2";		// 半选中
    private static final String UNDEFILNED = "undefined";		// 半选中
    
    /**
     * 构造机构树，返回为JSON字符串，TreePanel.js可以使用该字符串来在前台生成机构数
     * @param orgList			全部机构信息列表
     * @param selectedDeptCds		已选中的机构CD列表，用来初始化树中哪些机构被预先勾选
     * @return
     */
    public static String buildOrgTreeJSON(List<WsPlasOrg> orgList, String selectedDeptCds) {	
	if (orgList == null || orgList.size() == 0)
		return null;
	
	OrgTreeNode root = new OrgTreeNode();
	root.setOrgCd("0");
	root.setOrgName("全部");
	if (StringUtils.isBlank(selectedDeptCds)) {
	    root.setCheckedValue(UNCHECKED);
	} else {
	    if ("all".equalsIgnoreCase(selectedDeptCds)) {
		root.setCheckedValue(CHECKED);
	    }
	}
	root.setChildrenOrgs(buildChildren(root, orgList, selectedDeptCds, CHECKED.equals(root.getCheckedValue())));
	
	return root.toJSON();
    }
    
    /**
     * 构造没有勾选框的机构树JSON数据
     * @param orgList
     * @return
     */
    public static String buildNoCheckOrgTreeJSON(List<WsPlasOrg> orgList) {
	if (orgList == null || orgList.size() == 0)
		return null;
	
	OrgTreeNode root = new OrgTreeNode();
	root.setOrgCd("0");
	root.setOrgName("请选择机构");
	root.setCheckedValue(UNDEFILNED);
	root.setChildrenOrgs(buildNoCheckChildren(root, orgList));
	
	return root.toJSON();
    }
    
    /**
     * 构造没有勾选框的机构树的子节点
     * @param curOrg
     * @param orgList
     * @return
     */
    private static List<OrgTreeNode> buildNoCheckChildren(OrgTreeNode curOrg, List<WsPlasOrg> orgList) {
	if (curOrg == null)
		throw new IllegalArgumentException("当前机构不能为null");
	
	List<OrgTreeNode> children = new ArrayList<OrgTreeNode>();
	for (WsPlasOrg wsPlasOrg : orgList) {
	    String parentOrgCd = wsPlasOrg.getPhysicalOrgCd();
	    if (StringUtils.isBlank(parentOrgCd)) {
	    	parentOrgCd = "0";
	    }
	    if (parentOrgCd.equals(curOrg.getOrgCd())) {
		OrgTreeNode child = new OrgTreeNode();
		child.setOrgCd(wsPlasOrg.getOrgCd());
		child.setOrgName(wsPlasOrg.getOrgName());
		child.setParentNode(curOrg);
		child.setCheckedValue(UNDEFILNED);
		child.setChildrenOrgs(buildNoCheckChildren(child, orgList));
		children.add(child);
	    }
	}
	return children;
    }
    
    /**
     * 构造机构的子机构
     * @param curOrg
     * @param orgList
     * @param isCurOrgSelected
     * @return
     */
    private static List<OrgTreeNode> buildChildren(OrgTreeNode curOrg, List<WsPlasOrg> orgList, String selectedDeptCds, boolean isCurOrgSelected) {
	if (curOrg == null)
		throw new IllegalArgumentException("当前机构不能为null");
	
	if (orgList == null)
		throw new IllegalArgumentException("机构列表不能为null");
	
	List<OrgTreeNode> children = new ArrayList<OrgTreeNode>();
	for (WsPlasOrg wsPlasOrg : orgList) {
	    String parentOrgCd = wsPlasOrg.getPhysicalOrgCd();
	    if (StringUtils.isBlank(parentOrgCd)) {
	    	parentOrgCd = "0";
	    }
	    if (parentOrgCd.equals(curOrg.getOrgCd())) {
		OrgTreeNode child = new OrgTreeNode();
		child.setOrgCd(wsPlasOrg.getOrgCd());
		child.setOrgName(wsPlasOrg.getOrgName());
		child.setParentNode(curOrg);
		if (StringUtils.isBlank(selectedDeptCds)) {
		    child.setCheckedValue(UNCHECKED);
		} else {
		    if (isCurOrgSelected) {
			// 如果父亲节点被选中，则下属所有节点自然都被选中
			child.setCheckedValue(CHECKED);
		    } else {
			if (selectedDeptCds.contains("," + wsPlasOrg.getOrgCd() + ",")) {
			    // 如果父亲节点没被选中，但是某个子节点被选中了，则父亲节及所有的祖先节点都设为半选中
			    child.setCheckedValue(CHECKED);
			    OrgTreeNode parent = child.getParentNode();
			    // 所有的祖先节点都要设置为半选中状态
			    while (parent != null) {
				parent.setCheckedValue(HALF_CHECKED);
				parent = parent.getParentNode();
			    }
			} else {
			    child.setCheckedValue(UNCHECKED);
			}
		    }
		}
		
		child.setChildrenOrgs(buildChildren(child, orgList, selectedDeptCds, CHECKED.equalsIgnoreCase(child.getCheckedValue())));
		children.add(child);
	    }
	}
	return children;
    }
}
