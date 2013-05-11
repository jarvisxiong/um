package com.hhz.ump.dao.cost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cost.CostMateModule;
import com.hhz.ump.util.TreePanelNode;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class CostMateModuleManager extends BaseService<CostMateModule, String> {
	@Autowired
	private CostMateModuleDao costMateModuleDao;

	public void saveCostMateModule(CostMateModule costMateModule) {
		PowerUtils.setEmptyStr2Null(costMateModule);
		costMateModuleDao.save(costMateModule);
	}

	public void deleteCostMateModule(String id) {
		costMateModuleDao.delete(id);
	}
	
	public CostMateModule getCostMateModule(String id) {
		String hql = " from CostMateModule t where t.costMateModuleId = :costMateModuleId";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("costMateModuleId", id);
		List<CostMateModule> list = getDao().createQuery(hql,values).list();
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	/**
	 * 根据parentId搜索类型
	 * @param parentId
	 * @param enableFlg
	 * @return
	 */
	public List<CostMateModule> getCostMateModuleByParentId(String parentId){
		List<CostMateModule> moduleList = new ArrayList<CostMateModule>();
		String hql = " from CostMateModule t where t.parentId = :parentId";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("parentId", parentId);
		moduleList = getDao().createQuery(hql,values).list();
		return moduleList;
	}
	
	/**
	 * 得到所有子类
	 * @param enableFlg
	 * @param parentId
	 * @return
	 */
	public List<CostMateModule> getCostMateModuleChild(){
		List<CostMateModule> moduleList = new ArrayList<CostMateModule>();
		String hql = " from CostMateModule t where t.enableFlg = :enableFlg and t.parentId != :parentId";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("enableFlg", Long.valueOf(1));
		values.put("parentId", "0");
		moduleList = getDao().createQuery(hql,values).list();
		return moduleList;
	}
	
	/**
	 * 根据类型名称搜索
	 * @param moduleName
	 * @return
	 */
	public List<CostMateModule> checkMoudleName(String moduleName){
		List<CostMateModule> moduleList = new ArrayList<CostMateModule>();
		String hql = " from CostMateModule t where t.moduleName = :moduleName order by t.sequenceNo asc";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("moduleName", moduleName.trim());
		moduleList = getDao().createQuery(hql,values).list();
		return moduleList;
	}
	
	/**
	 * 得到材料设备分类树
	 * @param treeNode
	 * @param haveChecked
	 * @return
	 */
	public List<TreePanelNode> getChildrenNode(TreePanelNode treeNode,boolean haveChecked,String nodeCheckType){
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		List<HashMap<String,String>> tmpList = new ArrayList<HashMap<String,String>>();
		String hql = " from CostMateModule t where enableFlg = :enableFlg and parentId = :parentId order by t.parentId asc, t.sequenceNo asc";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("enableFlg", Long.valueOf(1));
		values.put("parentId", "0"); //parentId=0 代表是大类的大类
		List<CostMateModule> moduleList = getDao().createQuery(hql, values).list();
		if(moduleList != null && moduleList.size()>0){
			for(CostMateModule module : moduleList){
				HashMap<String,String> map = new HashMap<String,String>();
				map.put(module.getCostMateModuleId(), module.getModuleName());
				tmpList.add(map);
			}
		}
		List<CostMateModule> moduleChildList = this.getCostMateModuleChild();
		Iterator iter = tmpList.iterator(); 
		Map map = null;
		while (iter.hasNext()) { 
			map =  (Map) iter.next(); 
		    TreePanelNode proItem = new TreePanelNode();

		    String key = map.keySet().iterator().next().toString();
		    String value = (String)map.get(key);
		    proItem.setId(key);
			proItem.setText(value);
			proItem.setNodeType("1");
			proItem.setEntityId(key);
			proItem.setEntityName(value);
			proItem.setChildren(getModuleChildrenNode(proItem, haveChecked,moduleChildList,nodeCheckType));
			if (haveChecked) {
				proItem.setChecked(nodeCheckType);
			}
			children.add(proItem);
		} 
		return children;
	}
	//设置子节点
	public List<TreePanelNode> getModuleChildrenNode(TreePanelNode treeNode,boolean haveChecked,List<CostMateModule> typeList,String nodeCheckType) {
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		if (treeNode != null) {
			List<CostMateModule> result = new ArrayList<CostMateModule>();
			//对typeList遍历，等到满足ID、type条件的数据，并将其在typeList删除
			CostMateModule  type = null;
			for(Iterator<CostMateModule> it = typeList.iterator(); it.hasNext();){
				type=it.next();
				if(treeNode.getId().equals(type.getParentId())){
					result.add(type);
					it.remove();
				}
			}
			TreePanelNode childItem = null;
			for (CostMateModule module : result) {
				childItem = new TreePanelNode();
				childItem.setId(module.getCostMateModuleId());
				childItem.setText(module.getModuleName());
				childItem.setNodeType("2");
				childItem.setEntityId(module.getCostMateModuleId());
				childItem.setEntityName(module.getModuleName());
				if (haveChecked) {
					childItem.setChecked(nodeCheckType);
				}
				childItem.setChildren(this.getModuleChildrenNode(childItem,haveChecked,typeList,nodeCheckType));
				children.add(childItem);
			}
		}
		return children;
	}
	
	
	/**
	 * 得到材料设备分类树
	 * @param treeNode
	 * @param haveChecked
	 * @return
	 */
	/*public List<TreeNode> getChildrenNode(TreeNode treeNode,boolean haveChecked,String nodeCheckType){
		List<TreeNode> children = new ArrayList<TreeNode>();
		List<HashMap<String,String>> tmpList = new ArrayList<HashMap<String,String>>();
		String hql = " from CostMateModule where enableFlg = :enableFlg and parentId = :parentId order by parentId asc";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("enableFlg", Long.valueOf(1));
		values.put("parentId", "0"); //parentId=0 代表是大类的大类
		List<CostMateModule> moduleList = getDao().createQuery(hql, values).list();
		if(moduleList != null && moduleList.size()>0){
			for(CostMateModule module : moduleList){
				HashMap<String,String> map = new HashMap<String,String>();
				map.put(module.getCostMateModuleId(), module.getModuleName());
				tmpList.add(map);
			}
		}
		List<CostMateModule> moduleChildList = this.getCostMateModuleChild();
		
		Iterator iter = tmpList.iterator(); 
		while (iter.hasNext()) { 
		    Map map = (Map) iter.next(); 
		    TreeNode proItem = new TreeNode();
		    String key = map.keySet().iterator().next().toString();
		    String value = (String)map.get(key);

		    proItem.setId(key);
			proItem.setFinType(key);
			proItem.setText(value);
			proItem.setChildren(getModuleChildrenNode(proItem, haveChecked,moduleChildList,nodeCheckType));
			if (haveChecked) {
				proItem.setChecked(nodeCheckType);
			}
			children.add(proItem);
		    
		} 
		
		return children;
	}
	
	public List<TreeNode> getModuleChildrenNode(TreeNode treeNode,boolean haveChecked,List<CostMateModule> typeList,String nodeCheckType) {
		List<TreeNode> children = new ArrayList<TreeNode>();
		if (treeNode != null) {
			List<CostMateModule> result = new ArrayList<CostMateModule>();
			//对typeList遍历，等到满足ID、type条件的数据，并将其在typeList删除
			for(Iterator<CostMateModule> it = typeList.iterator(); it.hasNext();){
				CostMateModule type=it.next();
				if(treeNode.getId().equals(type.getParentId())){
					result.add(type);
					it.remove();
				}
			}
			for (CostMateModule module : result) {
				TreeNode childItem = new TreeNode();
				childItem.setId(module.getCostMateModuleId());
				childItem.setText(module.getModuleName());
				childItem.setParentId(treeNode.getId());
				childItem.setParentName(treeNode.getText());
				if (haveChecked) {
					childItem.setChecked(nodeCheckType);
				}
				childItem.setChildren(this.getModuleChildrenNode(childItem,haveChecked,typeList,nodeCheckType));
				children.add(childItem);
			}
		}
		return children;
	}*/
	
	@Override
	public HibernateDao<CostMateModule, String> getDao() {
		return costMateModuleDao;
	}

	/**
	 * 
	 * @param moduleId
	 * @return
	 */
	public String getBubblePath(String moduleId) {
		
		if(StringUtils.isBlank(moduleId) || "0".equals(moduleId))
			return "-";

		StringBuffer sb = new StringBuffer();
		
		CostMateModule module = getEntity(moduleId);
		if(module == null)
			return "";
		else{
			sb.append("/" + module.getModuleName());
			sb.insert(0, getBubblePath(module.getParentId()));
		}
		return sb.toString();
	} 
}

