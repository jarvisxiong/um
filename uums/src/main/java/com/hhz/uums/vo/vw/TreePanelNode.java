package com.hhz.uums.vo.vw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreePanelNode  implements Serializable {

	private static final long serialVersionUID = 8130587998231799252L;
	private String text;// 节点显示文本
	private String text0;
	private String text1;
	private String text2;
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
	private List<TreePanelNode> children = new ArrayList<TreePanelNode>();// 必须初始化
	private String mobile;	//导出通讯录专用字段 例：固定电话|||手机1,手机2|||职位
	
	//是否显示 1-是 0-否
	private String v;
	
	private String c1;
	
	public void addChild(TreePanelNode tree) {
		children.add(tree);
	}
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

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setEntityStatusCd(String entityStatusCd) {
		this.entityStatusCd = entityStatusCd;
	}
	public String getText0() {
		return text0;
	}
	public void setText0(String text0) {
		this.text0 = text0;
	}
	public String getText1() {
		return text1;
	}
	public void setText1(String text1) {
		this.text1 = text1;
	} 
	public String getText2() {
		return text2;
	}
	public void setText2(String text2) {
		this.text2 = text2;
	}
	public String getC1() {
		return c1;
	}
	public void setC1(String c1) {
		this.c1 = c1;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	
}
