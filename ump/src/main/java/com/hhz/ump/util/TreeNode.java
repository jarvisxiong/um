package com.hhz.ump.util;

import java.util.List;

public class TreeNode {
	private String text;// 节点显示文本
	private String id;// 节点id
	private String orgOrUser;// 是否用户节点或者部门节点 "0"代表用户节点 "1"代表部门节点
	private String sexCd;// 男女标志 1男2女
	private String extParam;// 扩展字段
	private String checked = "undefined";// 默认为undefined 不显示checkbox
	private String orgMgrCd; // 负责人的Uiid
	private List<TreeNode> children;// 子节点
	private Long orderNo;// 序号
	private String parentId;// 上级结点
	private String parentName;// 上级结点名称
	private String finType;
	private String finItemCd;
	private String trueId;

	private String online;


	public String getText() {
		return text;
	}

	public String getId() {
		return id;
	}

	public String getOrgOrUser() {
		return orgOrUser;
	}

	public String getExtParam() {
		return extParam;
	}

	public String getSexCd() {
		return sexCd;
	}

	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setOrgOrUser(String orgOrUser) {
		this.orgOrUser = orgOrUser;
	}

	public void setExtParam(String extParam) {
		this.extParam = extParam;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getOrgMgrCd() {
	    return orgMgrCd;
	}

	public void setOrgMgrCd(String orgMgrCd) {
	    this.orgMgrCd = orgMgrCd;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getFinType() {
		return finType;
	}

	public void setFinType(String finType) {
		this.finType = finType;
	}

	public String getFinItemCd() {
		return finItemCd;
	}

	public void setFinItemCd(String finItemCd) {
		this.finItemCd = finItemCd;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String getTrueId() {
		return trueId;
	}

	public void setTrueId(String trueId) {
		this.trueId = trueId;
	}

}
