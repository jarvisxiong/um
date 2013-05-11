package com.hhz.ump.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreePanelNode  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8130587998231799252L;
	private String text;// 节点显示文本
	private String id;// 节点id
	private String orgOrUser;// 是否用户节点或者部门节点 "0"代表用户节点 "1"代表部门节点
	private String sexCd;// 男女标志 1男2女
	private String extParam;// 扩展字段
	private String entityId;// uaapOrgId,uaapUserId
	private String entityCd;
	private String entityBizCd;
	private String entityName;
	private String checked = "undefined";// 默认为undefined 不显示checkbox
	private String nodeType;
	private String title;
	private String entityStatusCd;
	private String isParent; //是否有子节点 add 2012-7-18
	private List<TreePanelNode> children = new ArrayList<TreePanelNode>();// 必须初始化
	
	private String levelCd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOrgOrUser() {
		return orgOrUser;
	}

	public void setOrgOrUser(String orgOrUser) {
		this.orgOrUser = orgOrUser;
	}

	public String getSexCd() {
		return sexCd;
	}

	public void setSexCd(String sexCd) {
		this.sexCd = sexCd;
	}

	public String getExtParam() {
		return extParam;
	}

	public void setExtParam(String extParam) {
		this.extParam = extParam;
	}

	public List<TreePanelNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreePanelNode> children) {
		this.children = children;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public static void main(String[] args) {

	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEntityCd() {
		return entityCd;
	}

	public void setEntityCd(String entityCd) {
		this.entityCd = entityCd;
	}

	public String getEntityBizCd() {
		return entityBizCd;
	}

	public void setEntityBizCd(String entityBizCd) {
		this.entityBizCd = entityBizCd;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityStatusCd() {
		return entityStatusCd;
	}

	public void setEntityStatusCd(String entityStatusCd) {
		if("isChildrens".equals(entityStatusCd)) {
			this.isParent = "true";
		} else {
			this.isParent = "false";
		}
		this.entityStatusCd = entityStatusCd;
	}
	public void addChild(TreePanelNode child){
		this.children.add(child);
	}

	public String getLevelCd() {
		return levelCd;
	}

	public void setLevelCd(String levelCd) {
		this.levelCd = levelCd;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	
	
	
}
