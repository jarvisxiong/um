package com.hhz.ump.dao.sup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sup.SupItem;
import com.hhz.ump.util.TreeNode;

@Service
@Transactional
public class SupItemManager extends BaseService<SupItem, String> {
	@Autowired
	private SupItemDao supItemDao;

	@Transactional(readOnly = true)
	public SupItem getSupItem(String id) {
		return supItemDao.get(id);
	}

	public void saveSupItem(SupItem supItem) {
		PowerUtils.setEmptyStr2Null(supItem);
		supItemDao.save(supItem);
	}

	public void deleteSupItem(String id) {
		supItemDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<SupItem, String> getDao() {
		return supItemDao;
	}

	public List<SupItem> query(String itemCd) {
		String hql = "select supItemId  from SupItem where supItemCd=:itemCd";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemCd", itemCd);
		List<SupItem> result = supItemDao.find(hql, map);
		return result;
	}

	/**
	 * 搜索父类ID=parentItemCd的现金流项目
	 * 
	 * @param parentItemCd
	 * @return
	 */
	public List<SupItem> getParentItem(String parentItemCd) {
		String hql = "from " + SupItem.class.getSimpleName() + " where parentItemCd=? ORDER BY dispOrderNum";
		List<SupItem> result = supItemDao.find(hql, parentItemCd);
		return result;
	}

	/**
	 * 从孩子结点到父结点的搜索
	 * 
	 * @param itemList
	 * @param childItemCd
	 * @return
	 */
	public List<SupItem> getSelectParentItem(List<SupItem> itemList, String childItemCd) {
		String hql = "from " + SupItem.class.getSimpleName() + " where supItemCd=?";
		List<SupItem> result = supItemDao.find(hql, childItemCd);
		if (result != null && result.size() > 0) {
			String parent = result.get(0).getParentItemCd();
			itemList.add(result.get(0));
			if (parent != null && !"".equals(parent)) {
				getSelectParentItem(itemList, parent);
			}
		}
		return itemList;
	}

	public List<TreeNode> getChildrenNode(TreeNode treeNode, boolean haveChecked, List<SupItem> itemList) {
		List<TreeNode> children = new ArrayList<TreeNode>();
		if (treeNode != null) {
			List<SupItem> result = new ArrayList<SupItem>();
			for (Iterator<SupItem> it = itemList.iterator(); it.hasNext();) {
				SupItem type = it.next();
				if (treeNode.getId().equals(type.getParentItemCd())) {
					result.add(type);
					it.remove();
				}
			}
			for (SupItem item : result) {
				TreeNode childItem = new TreeNode();
				childItem.setId(item.getSupItemCd());
				childItem.setText(item.getSupItemName());
				childItem.setOrderNo(item.getDispOrderNum());
				childItem.setParentId(treeNode.getId());
				childItem.setParentName(treeNode.getText());
				childItem.setFinType(item.getSupItemTypeCd());
				childItem.setFinItemCd(item.getSupItemCd());
				childItem.setTrueId(item.getSupItemId());
				if (haveChecked) {
					childItem.setChecked("0");
				}
				childItem.setChildren(this.getChildrenNode(childItem, haveChecked, itemList));
				children.add(childItem);
			}
		}
		return children;
	}

	/**
	 * @param treeNode
	 * @param haveChecked
	 * @param selList
	 *            : aa,bb,cc
	 * @return
	 */
	public List<TreeNode> getChildrenNode(TreeNode treeNode, boolean haveChecked, List<String> selList,
			List<SupItem> itemList) {
		List<TreeNode> children = new ArrayList<TreeNode>();
		if (treeNode != null) {
			List<SupItem> result = new ArrayList<SupItem>();
			for (Iterator<SupItem> it = itemList.iterator(); it.hasNext();) {
				SupItem type = it.next();
				if (treeNode.getId().equals(type.getParentItemCd())) {
					result.add(type);
					it.remove();
				}
			}
			for (SupItem item : result) {
				String cd = item.getSupItemCd();
				TreeNode childItem = new TreeNode();
				childItem.setId(item.getSupItemCd());
				childItem.setText(item.getSupItemName());
				childItem.setOrderNo(item.getDispOrderNum());
				childItem.setParentId(treeNode.getId());
				childItem.setParentName(treeNode.getText());
				childItem.setFinType(item.getSupItemTypeCd());
				childItem.setFinItemCd(item.getSupItemCd());
				childItem.setTrueId(item.getSupItemId());
				if (haveChecked) {
					childItem.setChecked("0");
				}

				if (StringUtils.isNotBlank(cd) && selList.size() > 0) {
					for (Iterator<String> itStr = selList.iterator(); itStr.hasNext();) {
						String str = itStr.next();
						if (cd.equals(str)) {
							childItem.setChecked("1");
							itStr.remove();
						}
					}
					/*
					 * for (int i = 0; i < selList.size(); i++) { String sn = selList.get(i); if (cd.equals(sn)) { childItem.setChecked("1"); } }
					 */
				}

				childItem.setChildren(this.getChildrenNode(childItem, haveChecked, selList, itemList));
				children.add(childItem);
			}
		}
		return children;
	}

	public List<TreeNode> queryChildNode(List<TreeNode> children, List<TreeNode> newTreeNode) {
		for (TreeNode node : children) {
			newTreeNode.add(node);
			if (node.getChildren() != null && node.getChildren().size() > 0) {
				queryChildNode(node.getChildren(), newTreeNode);
			}
		}
		return newTreeNode;
	}

	public List<String> getAllParentChecked(String typeSn, List<TreeNode> tree, List<String> allCheckList) {
		for (TreeNode node : tree) {
			if (typeSn.equals(node.getId())) {
				allCheckList.add(node.getId());
				getAllParentChecked(node.getParentId(), tree, allCheckList);
			}
		}
		return allCheckList;
	}

	/**
	 * 通过supItemCd得到item name
	 * 
	 * @param parentItemCd
	 * @return item name 所有名称
	 */
	public List<String> getItemName(String itemCd, List<String> itemName) {
		String hql = "from " + SupItem.class.getSimpleName() + " where supItemCd=?";
		List<SupItem> result = supItemDao.find(hql, itemCd);
		if (result != null && result.size() > 0) {
			String name = result.get(0).getSupItemName();
			String parent = result.get(0).getParentItemCd();
			itemName.add(name);
			if (parent != null && !"".equals(parent)) {
				getItemName(parent, itemName);
			}
		}
		return itemName;
	}

	/**
	 * 通过supItemCd得到item cd
	 * 
	 * @param parentItemCd
	 * @return item name 所有名称
	 */
	public List<String> getItemCd(String itemCd, List<String> itemCdList) {
		String hql = "from " + SupItem.class.getSimpleName() + " where supItemCd=?";
		List<SupItem> result = supItemDao.find(hql, itemCd);
		if (result != null && result.size() > 0) {
			String cd = result.get(0).getSupItemCd();
			String parent = result.get(0).getParentItemCd();
			boolean haveItem = false;
			for (int i = 0; i < itemCdList.size(); i++) {
				if (cd.equals(itemCdList.get(i))) {
					haveItem = true;
				}
			}
			if (!haveItem) {
				itemCdList.add(cd);
			}
			if (parent != null && !"".equals(parent)) {
				getItemCd(parent, itemCdList);
			}
		}
		return itemCdList;
	}

	/**
	 * 更新节点状态
	 * 
	 * @param node
	 * @return
	 */
	public TreeNode refreshNodeStatus(String[] typeSn, TreeNode node) {

		int totalCount = 0;
		int checkedCount = 0;

		if (node != null && node.getChildren() != null) {
			totalCount = node.getChildren().size();
			for (int i = 0; i < totalCount; i++) {
				TreeNode tNode = node.getChildren().get(i);
				if (tNode == null) {
					continue;
				}
				tNode = refreshNodeStatus(typeSn, tNode);
				if ("1".equals(tNode.getChecked())) {
					checkedCount++;
				}
			}
		}

		if (totalCount == 0)
			return node;

		String nodeStatus = "0";
		if (checkedCount == 0) {
			nodeStatus = "0";
		} else if (totalCount == checkedCount) {
			nodeStatus = "1";
		} else if (totalCount > checkedCount && checkedCount > 0) {
			nodeStatus = "0";
		}
		node.setChecked(nodeStatus);

		return node;

	}

}
