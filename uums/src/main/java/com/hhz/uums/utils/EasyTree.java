package com.hhz.uums.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于构造生成jquery_easyui_tree所需要的json
 * 
 * @author shixy
 * 
 */
public class EasyTree {
	public static final String STATE_OPEN = "open";

	public static final String STATE_CLOSE = "close";

	/**
	 * 节点ID
	 */
	private String id;

	/**
	 * 节点显示文本
	 */
	private String text;

	/**
	 * 是否选中
	 */
	private boolean checked;

	/**
	 * 节点状态 open-展开 close-关闭
	 */
	private String state = STATE_CLOSE;

	/**
	 * 节点图标样式
	 */
	private String iconCls;

	/**
	 * 自定义对象(推荐自行构造,减少网络传输量)
	 */
	private Object attributes;

	/**
	 * 子节点
	 */
	private List<EasyTree> children = new ArrayList<EasyTree>();

	public EasyTree() {

	}

	public EasyTree(String id, String text) {
		this.id = id;
		this.text = text;
	}

	public EasyTree(String id, String text, Object data) {
		this.id = id;
		this.text = text;
		this.attributes = data;
	}

	public EasyTree(String id, String text, boolean checked, String state, String iconCls, Object data) {
		this.id = id;
		this.text = text;
		this.checked = checked;
		this.state = state;
		this.iconCls = iconCls;
		this.attributes = data;
	}

	public void addChild(EasyTree easyTree) {
		children.add(easyTree);
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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean getChecked(){
		return this.checked;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public List<EasyTree> getChildren() {
		return children;
	}

	public void setChildren(List<EasyTree> children) {
		this.children = children;
	}
	/**
	 * 遍历树，查找节点
	 * author:jiaoxiaofeng  2011-3-8
	 * @param id
	 * @return
	 * EasyTree
	 */
	public  EasyTree getChildById( String id){

		EasyTree result = null;
		for(EasyTree e: getChildren()){
			if(e.getId().equals(id)){
				result = e;
				return result;
			}else if(null!=e.getChildren()&&e.getChildren().size()>0){
				result = getChildById(e.getId());
				if(result!=null)
					return result;
			}
		}
		return result;
	}
}
