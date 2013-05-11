package com.hhz.ump.dao.oa;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaMeetingFolder;

@Service
@Transactional
public class OaMeetingFolderManager extends BaseService<OaMeetingFolder, String> {
	@Autowired
	private OaMeetingFolderDao oaMeetingFolderDao;

	public void saveOaMeetingFolder(OaMeetingFolder oaMeetingFolder) {
		PowerUtils.setEmptyStr2Null(oaMeetingFolder);
		oaMeetingFolderDao.save(oaMeetingFolder);
	}

	public void deleteOaMeetingFolder(String id) {
		oaMeetingFolderDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<OaMeetingFolder, String> getDao() {
		return oaMeetingFolderDao;
	}

	/**
	 * 构造在前台展示目录树所用到的JSON数据
	 * 
	 * @return
	 */
	public String buildFolderTreeJSON(String curFolderId, String bizModuleCd, String visibleToUiid) {
		StringBuilder hql = new StringBuilder("from OaMeetingFolder where deletedFlg='0' and bizModuleCd='"
				+ bizModuleCd + "'");

		if (StringUtils.isNotBlank(visibleToUiid)) {
			hql.append(" and (visibleToPersons is null or visibleToPersons like 'all' or visibleToPersons like '全部' or visibleToPersons like '%" + SpringSecurityUtils.getCurrentUserName() + "%' or visibleToPersons like '%;"
					+ visibleToUiid + ";%')");
		}

		hql.append(" order by sequenceNo,folderName");

		List<OaMeetingFolder> folders = this.find(hql.toString());

		if (folders == null || folders.size() == 0)
			return "";

		FolderTreeNode root = new FolderTreeNode();
		root.setFolderId("-1");
		root.setFolderName("请选择文件目录");

		buildChildrenNodes(root, folders, curFolderId);

		String result = "";
		if (root.getChildren().size() > 0) {
			result = root.toJSON();
		}

		return result;
	}

	/**
	 * 递归构造子节点
	 * 
	 * @param parentNode
	 * @param folders
	 */
	private void buildChildrenNodes(FolderTreeNode parentNode, List<OaMeetingFolder> folders, String curFolderId) {
		for (OaMeetingFolder folder : folders) {
			if (folder.getParentFolderId().equals(parentNode.getFolderId())) {
				if (folder.getOaMeetingFolderId().equals(curFolderId)) {
					continue;
				}

				FolderTreeNode child = new FolderTreeNode();
				child.setFolderId(folder.getOaMeetingFolderId());
				child.setFolderName(folder.getFolderName());
				buildChildrenNodes(child, folders, curFolderId);
				parentNode.addChild(child);
			}
		}
	}

	/**
	 * 
	 * 类名 FolderTreeNode 创建者 lijin 创建日期 2010-4-13 描述 用于构造前台目录树的内部节点类
	 */
	class FolderTreeNode {
		private String folderId;
		private String folderName;
		private List<FolderTreeNode> children = new ArrayList<FolderTreeNode>();

		public String getFolderId() {
			return folderId;
		}

		public void setFolderId(String folderId) {
			this.folderId = folderId;
		}

		public String getFolderName() {
			return folderName;
		}

		public void setFolderName(String folderName) {
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
		 * 
		 * @param child
		 */
		public void addChild(FolderTreeNode child) {
			if (child == null)
				return;

			children.add(child);
		}

		/**
		 * 返回JSON结构
		 * 
		 * @return
		 */
		public String toJSON() {
			StringBuilder json = new StringBuilder("{");

			json.append("'text': '" + this.getFolderName() + "',");
			json.append("id: '" + this.getFolderId() + "'");

			if (children.size() > 0) {
				json.append(", children: [");
				for (int i = 0; i < children.size(); i++) {
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

}
