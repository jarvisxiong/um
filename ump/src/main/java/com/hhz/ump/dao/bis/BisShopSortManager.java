package com.hhz.ump.dao.bis;

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
import com.hhz.ump.entity.bis.BisShopSort;
import com.hhz.ump.util.TreeNode;

@Service
@Transactional
public class BisShopSortManager extends BaseService<BisShopSort, String> {
	@Autowired
	private BisShopSortDao bisShopSortDao;

	public void saveBisShopSort(BisShopSort bisShopSort) {
		PowerUtils.setEmptyStr2Null(bisShopSort);
		bisShopSortDao.save(bisShopSort);
	}
	/**
	 * 更新供应商种类数据，若改供应商为父类，将其子类的父类名称修改
	 * @param bisShopSort
	 */
	public void updateBisShopSort(BisShopSort bisShopSort){
		//1为孩子结点
		//bisShopSort.setChildBl("1");
		saveBisShopSort(bisShopSort);
		//取出父类;
		String hql = "from " + BisShopSort.class.getSimpleName() + " where parentId=?";
		List<BisShopSort> result = bisShopSortDao.find(hql, bisShopSort.getBisShopSortId());
		for(BisShopSort sort:result){
			sort.setParentName(bisShopSort.getSortName());
			saveBisShopSort(sort);
		}
	}

	public void deleteBisShopSort(String id) {
		bisShopSortDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisShopSort, String> getDao() {
		return bisShopSortDao;
	}

	/**
	 * 构造商家种类树-商家库首页
	 * -Add by liuzhihui 2012-07-06
	 * @param treeNode
	 * @param haveChecked 是否需要选择框
	 * @param sortList 商家树list
	 * @param parentId 父节点ID(项目ID)
	 * @return
	 */
	public List<TreeNode> loadChildrenNode2(TreeNode treeNode,boolean haveChecked,List<BisShopSort> sortList,String deptCd,String parentId){
		List<TreeNode> children = new ArrayList<TreeNode>();
		if(treeNode!=null){
			List<BisShopSort> result = new ArrayList<BisShopSort>();
			for(Iterator<BisShopSort> it = sortList.iterator(); it.hasNext();){
				BisShopSort sort = it.next();
				String nodeId = treeNode.getTrueId();
				if("0".equals(sort.getParentId())){
					nodeId = "0";
				}
				if(nodeId.equals(sort.getParentId())){
					result.add(sort);
					it.remove();
				}
			}
			for(BisShopSort sort:result){
				TreeNode childItem = new TreeNode();
				//TreeNode的id必须唯一，否则前台显示不出来
				childItem.setId(sort.getBisShopSortId()+"-"+parentId);
				childItem.setTrueId(sort.getBisShopSortId());
				//如果项目公司是总部(宝龙商业：orgCd=153),类别后加一个'部'，如‘餐饮部’，其余的商业公司不加
				if("153".equals(parentId)){
					String sortName = sort.getSortName();
					if(("主力店".equals(sortName) && !"主力店".equals(sort.getParentName())) 
							|| "餐饮".equals(sortName) || "服装".equals(sortName) || "配套休闲".equals(sortName)){
						childItem.setText(sortName+"部");
					}else{
						childItem.setText(sortName);
					}
				}else{
					childItem.setText(sort.getSortName());
				}
				String tmpParentId = null;
				if("0".equals(sort.getParentId())){
					tmpParentId = parentId;
				}else{
					tmpParentId = sort.getParentId();
				}
				childItem.setParentId(tmpParentId);
				childItem.setParentName(sort.getParentName());
				childItem.setFinType(sort.getSortType());
				childItem.setOrderNo(sort.getSequenceNo());
				if(StringUtils.isNotBlank(deptCd)){
					if(deptCd.equals(sort.getSortDept())){
						childItem.setChecked("1");
					}else{
						childItem.setChecked("0");
					}
					List<TreeNode> nodeList =this.loadChildrenNode2(childItem,
							haveChecked,sortList,deptCd,null);
					/*if(!"1".equals(sort.getChildBl())&&nodeList==null){
						
					}else{
						childItem.setChildren(nodeList);
						children.add(childItem);
					}*/
					if("1".equals(sort.getChildBl())||(nodeList!=null&&nodeList.size()>0)){
						childItem.setChildren(nodeList);
						children.add(childItem);
					}
					
				}else{
					if (haveChecked) {
						childItem.setChecked("0");
					}
					childItem.setChildren(this.loadChildrenNode2(childItem,
							haveChecked,sortList,deptCd,parentId));
					children.add(childItem);
				}
			}
		}
		return children;
	}
	
	
	/**
	 * 构造商家种类树-配置商家类别
	 * @param treeNode
	 * @param haveChecked 是否需要选择框
	 * @param sortList 商家树list
	 * @return
	 */
	public List<TreeNode> loadChildrenNode(TreeNode treeNode,
			 boolean haveChecked,List<BisShopSort> sortList,String deptCd){
		List<TreeNode> children = new ArrayList<TreeNode>();
		if(treeNode!=null){
			List<BisShopSort> result = new ArrayList<BisShopSort>();
			for(Iterator<BisShopSort> it = sortList.iterator();it.hasNext();){
				BisShopSort sort = it.next();
				if(treeNode.getId().equals(sort.getParentId())){
					result.add(sort);
					it.remove();
				}
			}
			for(BisShopSort sort:result){
				TreeNode childItem = new TreeNode();
				childItem.setId(sort.getBisShopSortId());
				childItem.setTrueId(sort.getBisShopSortId());
				childItem.setText(sort.getSortName());
				childItem.setParentId(sort.getParentId());
				childItem.setParentName(sort.getParentName());
				childItem.setFinType(sort.getSortType());
				childItem.setOrderNo(sort.getSequenceNo());
				if(StringUtils.isNotBlank(deptCd)){
					if(deptCd.equals(sort.getSortDept())){
						childItem.setChecked("1");
					}else{
						childItem.setChecked("0");
					}
					List<TreeNode> nodeList =this.loadChildrenNode(childItem,
							haveChecked,sortList,deptCd);
					/*if(!"1".equals(sort.getChildBl())&&nodeList==null){
						
					}else{
						childItem.setChildren(nodeList);
						children.add(childItem);
					}*/
					if("1".equals(sort.getChildBl())||(nodeList!=null&&nodeList.size()>0)){
						childItem.setChildren(nodeList);
						children.add(childItem);
					}
					
				}else{
					if (haveChecked) {
						childItem.setChecked("0");
					}
					childItem.setChildren(this.loadChildrenNode(childItem,
							haveChecked,sortList,deptCd));
					children.add(childItem);
				}
			}
		}
		return children;
	}
	public List<TreeNode> loadTreeCheckSelect(TreeNode treeNode,
			 List<BisShopSort> sortList,List<BisShopSort> allSortList){
		List<TreeNode> children = new ArrayList<TreeNode>();
		if(treeNode!=null){
			List<BisShopSort> result = new ArrayList<BisShopSort>();
			for(Iterator<BisShopSort> it = allSortList.iterator(); it.hasNext();){
				BisShopSort sort = it.next();
				if(treeNode.getId().equals(sort.getParentId())){
					result.add(sort);
					it.remove();
				}
			}
			for(BisShopSort sort:result){
				TreeNode childItem = new TreeNode();
				childItem.setId(sort.getBisShopSortId());
				childItem.setText(sort.getSortName());
				childItem.setParentId(sort.getParentId());
				childItem.setParentName(sort.getParentName());
				for(Iterator<BisShopSort> it = sortList.iterator(); it.hasNext();){
					BisShopSort shopSort = it.next();
					if(sort.getBisShopSortId().equals(shopSort.getBisShopSortId())){
						childItem.setChecked("1");
						it.remove();
					}
				}
				if(childItem.getChecked()==null||!"1".equals(childItem.getChecked())){
					childItem.setChecked("0");
				}
				childItem.setChildren(this.loadTreeCheckSelect(childItem,
						sortList,allSortList));
				children.add(childItem);
			}
		}
		return children;
	}
	/**
	 * 搜索商家类别 
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public BisShopSort getShopSorts(String sortName) {
		String hql = " from BisShopSort t where trim(t.sortName) = ? ";
		List<BisShopSort> result =getDao().createQuery(hql, sortName.trim()).list();
		if(null==result||result.size()==0)
			return null;
		else
			return result.get(0);
	}
	/**
	 * 搜索商家类别，按序列号排序
	 */
	public List<BisShopSort> querySortBySeq(String deptCd){
		StringBuffer hql =new StringBuffer(" select a from ").append(BisShopSort.class.getSimpleName()).append(" a") ;
		if(StringUtils.isNotBlank(deptCd)){
			hql.append(" where a.sortDept is null or a.sortDept ='").append(deptCd).append("'") ;
		}
		hql.append(" order by a.sequenceNo asc");
		List<BisShopSort> result =bisShopSortDao.find(hql.toString());
		return result;
	}
	public List<BisShopSort> querySortByRes(){
		Map<String, Object> param = new HashMap<String, Object>();
		String hql="select a from  " + BisShopSort.class.getSimpleName() + " a where a.bisShopSortId=:sortId1"
		+" or a.parentId=:parentId1"+" or a.bisShopSortId=:sortId2"
		+" or a.parentId=:parentId2 or a.bisShopSortId=:sortId3";
		param.put("sortId1", "4028347031903d4801319040d07e0008");
		param.put("parentId1", "4028347031903d4801319040d07e0008");
		param.put("sortId2", "402834e53190381101319044139f0024");
		param.put("parentId2", "402834e53190381101319044139f0024");
		param.put("sortId3", "402834e5319038110131903fa112001c");
		List<BisShopSort> result =bisShopSortDao.find(hql,param);
		return result;
	}
	
	/**
	 * 根据父节点ID得到子类别  -add 不要liuzhihui 2012-07-06
	 * @param parentId
	 * @return
	 */
	public List<BisShopSort> queryShopSortByParentId(String parentId){
		Map<String, Object> param = new HashMap<String, Object>();
		String hql="from " + BisShopSort.class.getSimpleName() + " t where t.parentId =:parentId";
		param.put("parentId", parentId);
		List<BisShopSort> result =bisShopSortDao.find(hql,param);
		return result;
	}
}

