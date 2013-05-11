package com.hhz.ump.dao.pm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.pm.PmMateModu;
import com.hhz.ump.util.TreePanelNode;

@Service
@Transactional
public class PmMateModuManager extends BaseService<PmMateModu, String> {
	@Autowired
	private PmMateModuDao pmMateModuDao;

	public void savePmMateModu(PmMateModu pmMateModu) {
		PowerUtils.setEmptyStr2Null(pmMateModu);
		pmMateModuDao.save(pmMateModu);
	}

	public void deletePmMateModu(String id) {
		pmMateModuDao.delete(id);
	}
	
	@Override
	public HibernateDao<PmMateModu, String> getDao() {
		return pmMateModuDao;
	}
	
	
	/**
	 * 得到企划案例库分类树
	 * @param treeNode
	 * @param haveChecked
	 * @return
	 */
	public List<TreePanelNode> getChildrenNode(TreePanelNode treeNode,boolean haveChecked,String nodeCheckType){
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		List<HashMap<String,String>> tmpList = new ArrayList<HashMap<String,String>>();
		String hql = " from PmMateModu t where enableFlg = :enableFlg and parentId = :parentId order by t.parentId asc, t.sequenceNo asc";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("enableFlg", Long.valueOf(1));
		values.put("parentId", "0"); //parentId=0 代表是大类的大类
		List<PmMateModu> moduleList = pmMateModuDao.createQuery(hql, values).list();
		if(moduleList != null && moduleList.size()>0){
			for(PmMateModu module : moduleList){
				HashMap<String,String> map = new HashMap<String,String>();
				map.put(module.getPmMateModuId(), module.getModuleName());
				tmpList.add(map);
			}
		}
		List<PmMateModu> moduleChildList = this.getPmMateModuleChild();
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
	public List<TreePanelNode> getModuleChildrenNode(TreePanelNode treeNode,boolean haveChecked,List<PmMateModu> typeList,String nodeCheckType) {
		List<TreePanelNode> children = new ArrayList<TreePanelNode>();
		if (treeNode != null) {
			List<PmMateModu> result = new ArrayList<PmMateModu>();
			//对typeList遍历，等到满足ID、type条件的数据，并将其在typeList删除
			PmMateModu  type = null;
			for(Iterator<PmMateModu> it = typeList.iterator(); it.hasNext();){
				type=it.next();
				if(treeNode.getId().equals(type.getParentId())){
					result.add(type);
					it.remove();
				}
			}
			TreePanelNode childItem = null;
			for (PmMateModu module : result) {
				childItem = new TreePanelNode();
				childItem.setId(module.getPmMateModuId());
				childItem.setText(module.getModuleName());
				childItem.setNodeType("2");
				childItem.setEntityId(module.getPmMateModuId());
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
	 * 得到所有子类
	 * @param enableFlg
	 * @param parentId
	 * @return
	 */
	public List<PmMateModu> getPmMateModuleChild(){
		List<PmMateModu> moduleList = new ArrayList<PmMateModu>();
		String hql = " from PmMateModu t where t.enableFlg = :enableFlg and t.parentId != :parentId";
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("enableFlg", Long.valueOf(1));
		values.put("parentId", "0");
		moduleList = pmMateModuDao.createQuery(hql,values).list();
		return moduleList;
	}
}

