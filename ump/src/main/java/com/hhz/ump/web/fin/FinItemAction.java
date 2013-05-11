package com.hhz.ump.web.fin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.fin.FinItemManager;
import com.hhz.ump.entity.fin.FinItem;
import com.hhz.ump.util.TreeNode;

@Namespace("/fin")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "fin-item!list.action", type = "redirect") })
public class FinItemAction extends CrudActionSupport<FinItem> {

	private static final long serialVersionUID = 8071052318603147332L;
	@Autowired
	private FinItemManager finItemManager;

	private FinItem entity;

	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = finItemManager.getFinItem(getId());
			finItemManager.delete(entity);
			addActionMessage(getText("common.success"));
		}
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return "input";
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	public String getItemTree() throws Exception {
		/*
		 * List<FinItem> result = finItemManager.getParentItem("0"); for
		 * (FinItem item : result) { TreeNode node = new TreeNode();
		 * node.setId(item.getFinItemId()); node.setText(item.getFinItemName());
		 * node.setOrderNo(item.getDispOrderNum());
		 * node.setChildren(this.getChildrenNode(node));
		 * Struts2Utils.renderJson(node); }
		 */
		TreeNode node = new TreeNode();
		node.setId("0");
		node.setText("现金流量项目");
		node.setOrderNo(new Long(0));
		node.setChildren(this.getChildrenNode(node));
		Struts2Utils.renderJson(node);

		return null;
	}

	public List<TreeNode> getChildrenNode(TreeNode treeNode) {
		List<TreeNode> children = new ArrayList<TreeNode>();
		if (treeNode != null) {
			List<FinItem> result = finItemManager.getParentItem(treeNode
					.getId());
			for (FinItem item : result) {
				TreeNode childItem = new TreeNode();
				childItem.setId(item.getFinItemCd());
				childItem.setText(item.getFinItemName());
				childItem.setOrderNo(item.getDispOrderNum());
				childItem.setParentId(treeNode.getId());
				childItem.setParentName(treeNode.getText());
				childItem.setFinType(item.getFinItemTypeCd());
				childItem.setFinItemCd(item.getFinItemCd());
				childItem.setTrueId(item.getFinItemId());
				childItem.setChildren(this.getChildrenNode(childItem));
				children.add(childItem);
			}
		}
		return children;
	}

	@Override
	public void prepareSave() {
		if (StringUtils.isNotBlank(getId())) {
			entity = finItemManager.getFinItem(getId());
			entity.setRecordVersion(entity.getRecordVersion() + 1);
		} else {
			entity = new FinItem();
		}
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		finItemManager.saveFinItem(entity);

		return null;
	}

	public FinItem getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = finItemManager.getFinItem(getId());
		} else {
			entity = new FinItem();
		}
	}

}
