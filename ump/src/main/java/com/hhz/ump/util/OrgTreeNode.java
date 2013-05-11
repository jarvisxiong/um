package com.hhz.ump.util;

import java.util.List;

/**
 * 
 * 类名		OrgTreeNode
 * 创建者	lijin
 * 创建日期	2010-3-2
 * 描述		根据机构列表构造机构树的节点Bean.
 */
public class OrgTreeNode {
    // 当前机构信息
    private String orgCd;
    private String orgName;
    // 0-未选中状态, 1-全选中状态, 2-半选中状态
    private String checkedValue;
    
    // 子机构列表
    private List<OrgTreeNode> childrenOrgs;
    
    private OrgTreeNode parentNode;

    public String toJSON() {
	StringBuilder json = new StringBuilder("{");
	json.append("'text': '" + this.getOrgName() + "',");
	json.append("id: '" + this.getOrgCd() + "',");
	
	if (childrenOrgs.size() > 0) {
	    json.append("children: [");
	    for (int i = 0; i < childrenOrgs.size(); i ++) {
		OrgTreeNode child = childrenOrgs.get(i);
		json.append(child.toJSON());
		if (i != childrenOrgs.size() - 1) {
		    json.append(",");
		}
	    }
	    json.append("],");
	}
	json.append("'checked': '" + checkedValue + "'}");
	return json.toString();
    }
    
    public String getOrgCd() {
        return orgCd;
    }

    public void setOrgCd(String orgCd) {
        this.orgCd = orgCd;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public List<OrgTreeNode> getChildrenOrgs() {
        return childrenOrgs;
    }

    public void setChildrenOrgs(List<OrgTreeNode> childrenOrgs) {
        this.childrenOrgs = childrenOrgs;
    }

    public String getCheckedValue() {
        return checkedValue;
    }

    public void setCheckedValue(String checkedValue) {
        this.checkedValue = checkedValue;
    }

    public OrgTreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(OrgTreeNode parentNode) {
        this.parentNode = parentNode;
    }
}
