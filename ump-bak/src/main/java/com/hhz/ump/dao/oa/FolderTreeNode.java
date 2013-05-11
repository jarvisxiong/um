package com.hhz.ump.dao.oa;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 类名		FolderTreeNode
 * 创建者	lijin
 * 创建日期	2010-4-13
 * 描述		用于构造前台目录树的内部节点类
 */
public class FolderTreeNode{
	
	private static final long serialVersionUID = 8928919868014174470L;
	//以下3字段,add by huangbijin 2011-07-06
	private String id;
	private String text;
	private String checked = "undefined";
	
	private String folderId;
    private String folderName;
    private List<FolderTreeNode> children = new ArrayList<FolderTreeNode>();
    
    public String getFolderId() {
        return folderId;
    }
    public void setFolderId(String folderId) {
    	setId(folderId);
        this.folderId = folderId;
    }
    public String getFolderName() {
        return folderName;
    }
    public void setFolderName(String folderName) {
    	setText(folderName);
        this.folderName = folderName;
    }
	public List<FolderTreeNode> getChildren() {
        return children;
    }
    public void setChildren(List<FolderTreeNode> children) {
        this.children = children;
    }
    
    /**
     * 增加一个子节点
     * @param child
     */
    public void addChild(FolderTreeNode child) {
	if (child == null) 
	    return;
	
	children.add(child);
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
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	/**
     * 返回JSON结构
     * @return
     */
    public String toJSON() {
	StringBuilder json = new StringBuilder("{");
	
	json.append("'text': '" + this.getFolderName() + "',");
	json.append("id: '" + this.getFolderId() + "'");
	
	if (children.size() > 0) {
	    json.append(", children: [");
	    for (int i = 0; i < children.size(); i ++) {
		FolderTreeNode child = children.get(i);
		json.append(child.toJSON());
		if (i != children.size() - 1) {
		    json.append(",");
		}
	    }
	    json.append("]");
	}
	
	json.append("}");
	return json.toString();
    }
}