package com.hhz.ump.dao.oa;

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
import com.hhz.ump.entity.oa.OaFilingFolder;

@Service
@Transactional
public class OaFilingFolderManager extends BaseService<OaFilingFolder, String> {
	@Autowired
	private OaFilingFolderDao oaFilingFolderDao;

	
	public void saveOaFilingFolder(OaFilingFolder oaFilingFolder) {
		PowerUtils.setEmptyStr2Null(oaFilingFolder);
		oaFilingFolderDao.save(oaFilingFolder);
	}

	public void deleteOaFilingFolder(String id) {
		oaFilingFolderDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<OaFilingFolder, String> getDao() {
		return oaFilingFolderDao;
	}
	
	/**
	 * 构造在前台展示目录树所用到的JSON数据
	 * @return
	 */
	public FolderTreeNode buildFolderTreeNode(String curFolderId, String bizModuleCd, String visibleToUiid) {

	    StringBuilder hql = new StringBuilder("from OaFilingFolder where deletedFlg='0' ");
	    /*
	    if(bizModuleCd.equalsIgnoreCase(DictContants.COMPANY_FILING_MODULE_CD_STANDARD)){
	    	//如果是标准与库，默认同时显示成本文件
	    	hql.append("and (bizModuleCd='" + DictContants.COMPANY_FILING_MODULE_CD_STANDARD + "'" +
	    			" or bizModuleCd='" + DictContants.COMPANY_FILING_MODULE_CD_VI + "') ");
	    }else{
	    	hql.append("and (bizModuleCd='" + bizModuleCd + "') ");
	    }
	    */
	    hql.append("and (bizModuleCd='" + bizModuleCd + "') ");

	    if (StringUtils.isNotBlank(visibleToUiid)) {
		hql.append(" and (visibleToPersons is null or visibleToPersons like 'all' or visibleToPersons like '全部' or visibleToPersons like '%" + SpringSecurityUtils.getCurrentUserName() + "%' or visibleToPersons like '%;" + visibleToUiid + ";%')");
	    }
	    
	    hql.append(" order by sequenceNo,folderName");
	    
	    List<OaFilingFolder> folders = this.find(hql.toString());
	    
	    if (folders == null || folders.size() == 0)
			return null;
	    
	    FolderTreeNode root = new FolderTreeNode();
	    root.setFolderId("-1");
	    root.setFolderName("请选择文件目录");
	    
	    buildChildrenNodes(root, folders, curFolderId);
	    
	    return root;
	}
	
	public String buildFolderTreeJSON(String curFolderId, String bizModuleCd, String visibleToUiid) {
		
		FolderTreeNode root = buildFolderTreeNode(curFolderId, bizModuleCd, visibleToUiid);
	    String result = "";
	    if (root != null && root.getChildren().size() > 0) {
	    	result = root.toJSON();
	    }
	    return result;
	}
	
	public String buildFolderTreeJSON(FolderTreeNode root) {
	    String result = "";
	    if (root != null && root.getChildren().size() > 0) {
	    	result = root.toJSON();
	    }
	    return result;
	}


	public FolderTreeNode getFolderNode(FolderTreeNode root,String directChildNodeId) {
	
		if(StringUtils.isBlank(directChildNodeId))
			return null;
		
	    if (root != null && root.getChildren().size() > 0) {
	    	for (FolderTreeNode node : root.getChildren()) {
	    		if(StringUtils.isNotBlank(directChildNodeId) && directChildNodeId.equals(node.getFolderId()))
					return node;
			}
	    }
	    return null;
	}
	
	/**
	 * 递归构造子节点
	 * @param parentNode
	 * @param folders
	 */
        private void buildChildrenNodes(FolderTreeNode parentNode, List<OaFilingFolder> folders, String curFolderId) {
        	FolderTreeNode child = null;
        	for (OaFilingFolder folder : folders) {
	        	if (folder.getParentFolderId().equals(parentNode.getFolderId())) {
	        	    if (folder.getOaFilingFolderId().equals(curFolderId)) {
	        	    	continue;
	        	    }
	        	    
	        	    child = new FolderTreeNode();
	        	    child.setFolderId(folder.getOaFilingFolderId());
	        	    child.setFolderName(folder.getFolderName());
	        	    buildChildrenNodes(child, folders, curFolderId);
	        	    parentNode.addChild(child);
	        	}
            }
        }
	
}

