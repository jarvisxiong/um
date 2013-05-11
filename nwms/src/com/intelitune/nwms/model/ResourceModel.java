package com.intelitune.nwms.model;

public class ResourceModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	public String treeNodeId;

	public String treeParentNodeId;

	public String resourceName;

	public String nameEN;

	public String nameCN;

	public String getTreeParentNodeId() {
		return treeParentNodeId;
	}

	public void setTreeParentNodeId(String treeParentNodeId) {
		this.treeParentNodeId = treeParentNodeId;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getNameCN() {
		return nameCN;
	}

	public void setNameCN(String nameCN) {
		this.nameCN = nameCN;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getTreeNodeId() {
		return treeNodeId;
	}

	public void setTreeNodeId(String treeNodeId) {
		this.treeNodeId = treeNodeId;
	}

}