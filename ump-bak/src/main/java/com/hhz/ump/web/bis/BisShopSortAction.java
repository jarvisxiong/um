package com.hhz.ump.web.bis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.dao.bis.BisShopSortAuthManager;
import com.hhz.ump.dao.bis.BisShopSortManager;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisShopSort;
import com.hhz.ump.entity.bis.BisShopSortAuth;
import com.hhz.ump.util.TreeNode;
import com.hhz.uums.entity.ws.WsPlasOrg;

public class BisShopSortAction extends CrudActionSupport<BisShopSort> {

	private BisShopSort entity;
	@Autowired
	private BisShopSortManager bisShopSortManager;
	@Autowired
	private BisProjectManager bisProjectManager;
	@Autowired
	private BisShopSortAuthManager bisShopSortAuthManager;
	private boolean haveChecked;
	private String deptCd;
	private String shopSortId;
	private List<BisShopSortAuth> authList;
	@Override
	public BisShopSort getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = bisShopSortManager.getEntity(getId());
			if(entity.getSortType()!=null&&"1".equals(entity.getSortType())){
				Struts2Utils.renderText("1");
				return null;
			}
			if(entity.getBisShopSortRels()!=null&&entity.getBisShopSortRels().size()>0){
				Struts2Utils.renderText("2");
				return null;
			}
			bisShopSortManager.delete(entity);
		}
		return "input";
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return RELOAD;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = bisShopSortManager.getEntity(getId());
			entity.setRecordVersion(entity.getRecordVersion() + 1);
		}else{
			entity = new BisShopSort();
		}
		
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		if(entity.getParentId()==null||"".equals(entity.getParentId())){
			entity.setParentId("0");
		}else{
			if(entity.getBisShopSortId()!=null&&!"".equals(entity.getBisShopSortId())){
				if(entity.getBisShopSortId().equals(entity.getParentId())){
					Struts2Utils.renderText("不能选择自己为父结点");
					return null;
				}
			}
			//更新父结点的childBl为2
			BisShopSort sort =bisShopSortManager.getEntity(entity.getParentId());
			/*if(sort.getChildBl()==null||!"2".equals(sort.getChildBl())){
				//2为父结点
				sort.setChildBl("2");
				bisShopSortManager.saveBisShopSort(sort);
			}*/
		}
		if(StringUtils.isBlank(entity.getSortType())){
			entity.setSortType("0");
		}
		if(StringUtils.isBlank(entity.getBisShopSortId())) {
			//1为孩子结点
			/*entity.setChildBl("1");*/
			bisShopSortManager.saveBisShopSort(entity);
		} else{
			bisShopSortManager.updateBisShopSort(entity);
		}
		return null;
	}
	
	public void saveAuth(){
		String userCd = Struts2Utils.getParameter("userCd");
		String userName = Struts2Utils.getParameter("userName");
		String userId = Struts2Utils.getParameter("userId");
		String oldSortId ="";
		//if(StringUtils.isNotBlank(userId)){
			BisShopSortAuth auth =bisShopSortAuthManager.getEntity(userId);
			auth.setUsersCd(userCd);
			auth.setUsersName(userName);
			deptCd =auth.getDeptCd();
			if(StringUtils.isNotBlank(auth.getShopSortId())){
				oldSortId=auth.getShopSortId();
			}
			if(StringUtils.isNotBlank(shopSortId)){
				auth.setShopSortId(shopSortId);
			}else{
				auth.setShopSortId("");
			}
			bisShopSortAuthManager.saveBisShopSortAuth(auth);
		//}
		//若新商家类别有值，则将该商家的类别赋予该部门
		if(StringUtils.isNotBlank(shopSortId)){
			String[] sorts =shopSortId.split(",");
			for(int i=0;i<sorts.length;i++){
				BisShopSort sort =bisShopSortManager.getEntity(sorts[i]);
				if("1".equals(sort.getChildBl())){
					sort.setSortDept(deptCd);
					sort.setDeptPerson(userCd);
					bisShopSortManager.saveBisShopSort(sort);
					if(oldSortId.indexOf(sort.getBisShopSortId())>=0){
						oldSortId =oldSortId.replace(sort.getBisShopSortId(),"");
					}
				}
			}
		}
		//删除原商家类别赋予的部门值
		if(StringUtils.isNotBlank(oldSortId)){
			String[] oldSorts =oldSortId.split(",");
			for(int i=0;i<oldSorts.length;i++){
				if(!"".equals(oldSorts[i])){
					BisShopSort sort =bisShopSortManager.getEntity(oldSorts[i]);
					if("1".equals(sort.getChildBl())){
						sort.setSortDept("");
						sort.setDeptPerson("");
						bisShopSortManager.saveBisShopSort(sort);
					}
				}
			}
		}
		Struts2Utils.renderText("0");
	}
	
	/**
	 * 配置商家类别-树加载方法(配置商家类别及增加页面的树)
	 */
	public void loadSortTree(){
		TreeNode node = new TreeNode();
		node.setId("0");
		node.setText("商家类别");
		node.setOrderNo(new Long(0));
		node.setFinType("1");
		List<BisShopSort> sortList =bisShopSortManager.querySortBySeq(deptCd);
		node.setChildren(bisShopSortManager.loadChildrenNode(node,haveChecked,sortList,deptCd));
		Struts2Utils.renderJson(node);
	}
	
	/**
	 * 商家库首页-单独某个商业公司树
	 * -Add by liuzhihui 2012-07-06
	 */
	public void loadSortTreeHQ(){
		List<BisShopSort> sortList =bisShopSortManager.querySortBySeq(deptCd);
		TreeNode node = new TreeNode();
		/*
		 * 拥有A_SHOP_ALL_VIEW(商家库-查阅全部)角色的人，左边的树可看到所有项目公司，
		 * 否则只能看到自己所属的项目公司，且对于项目公司没有'主力店'节点
		 */
		String orgCd = null;
		if (SpringSecurityUtils.hasRole("A_SHOP_ALL_VIEW")) {
			orgCd = "153";
			node.setText("商业总部");
		}else{
			orgCd = SpringSecurityUtils.getCurrentCenterCd();
			BisProject pro = bisProjectManager.getBisProjectByOrgCd(orgCd);
			if(pro != null){
				node.setText(pro.getProjectName());
				//则移除"主力店"及其子节点
				sortList = this.removeMainShop(sortList);
			}else{
				orgCd = "153";
				node.setText("商业总部");
			}
		}
		node.setId("0");
		node.setOrderNo(new Long(0));
		node.setFinType("0");
		node.setTrueId("0");
		node.setChildren(bisShopSortManager.loadChildrenNode2(node,haveChecked,sortList,deptCd,orgCd));
		Struts2Utils.renderJson(node);
	}
	
	/**
	 * 商家库首页-加载各商业公司树
	 * -Add by liuzhihui 2012-07-06
	 */
	public void loadSortTreePro(){
		TreeNode node = new TreeNode();
		node.setId("0");
		node.setOrderNo(new Long(0));
		node.setFinType("0");
		node.setText("各商业公司");
		node.setChildren(loadChildNodeByRootPro(node));
		Struts2Utils.renderJson(node);
	}
	
	/**
	 * 加载商家库-各商业公司商家类别树
	 * -Add by liuzhihui 2012-07-06
	 * @param root
	 * @return
	 */
	private List<TreeNode> loadChildNodeByRootPro(TreeNode root) {
		List<TreeNode> children = new ArrayList<TreeNode>();
		//查询除商业总部外的所有商业公司
		List<BisProject> projectList = new ArrayList<BisProject>();
		Map<String, Object> param = new HashMap<String, Object>();
		String hql = "select a from BisProject a where a.orgCd !=:orgCd order by a.projectName asc, a.sequenceNo asc";
		param.put("orgCd", "153");
		projectList = bisProjectManager.find(hql.toString(), param);
		List<BisShopSort> sortList = null;
		if(!projectList.isEmpty()){
			for (BisProject bisProject : projectList) {
				sortList = bisShopSortManager.querySortBySeq(deptCd);
				//如果项目公司不是总部(宝龙商业，orgCd="153")，则移除"主力店"及其子节点
				if(!"153".equals(bisProject.getOrgCd())){
					sortList = this.removeMainShop(sortList);
				}
				TreeNode childItem = new TreeNode();
				childItem.setId(bisProject.getBisProjectId());
				childItem.setParentId(root.getId());
				childItem.setParentName(root.getText());
				childItem.setFinType("1");
				childItem.setOrderNo(new Long(0));
				childItem.setTrueId(bisProject.getBisProjectId());
				childItem.setText(bisProject.getProjectName());
				childItem.setChecked("0");// 需要设置默认checked属性
				childItem.setChildren(bisShopSortManager.loadChildrenNode2(childItem,true,sortList,deptCd,bisProject.getOrgCd()));
				children.add(childItem);
			}
		}
		return children;
	}
	
	/**
	 * 移除'主力店'及其子节点
	 *
	 * @param sortList
	 * @return
	 */
	public List<BisShopSort> removeMainShop(List<BisShopSort> sortList){
		List<BisShopSort> tmpList = new ArrayList<BisShopSort>();
		tmpList = sortList;
		for (BisShopSort tmp : sortList) {
			String sortId = tmp.getBisShopSortId();
			String sortName = tmp.getSortName();
			if(StringUtils.isNotBlank(sortName) && "主力店".equals(sortName)){
				tmpList = getShopSortByParentId(sortId, tmpList); //移除主力店的子类
				tmpList.remove(tmp); //移除主力店自身
				break;
			}
		}
		return tmpList;
	}
	
	public List<BisShopSort> getShopSortByParentId(String parentId,List<BisShopSort> sortList){
		List<BisShopSort> chirldList = bisShopSortManager.queryShopSortByParentId(parentId);
		List<BisShopSort> tmpList = sortList;
		for (BisShopSort sort : chirldList) {
			tmpList.remove(sort);
			getShopSortByParentId(sort.getBisShopSortId(), tmpList);
		}
		return tmpList;
	}
	
	/**
	 * 只带主力店、次主力店的东西
	 */
	public void loadSortTreeByRes(){
		TreeNode node = new TreeNode();
		node.setId("0");
		node.setText("商家类别");
		node.setOrderNo(new Long(0));
		node.setFinType("1");
		List<BisShopSort> sortList =bisShopSortManager.querySortByRes();
		node.setChildren(bisShopSortManager.loadChildrenNode(node,haveChecked,sortList,""));
		Struts2Utils.renderJson(node);
	}
	/**
	 * load商家搜索里面的部门树有 三类：大客户中心690、招商中心154、各商业公司
	 */
	public void loadDeptTree(){
		TreeNode node = new TreeNode();
		node.setId("0");
		node.setText("部门");
		node.setOrderNo(new Long(0));
		node.setFinType("1");
		node.setChildren(loadChildNodeByRoot(node));
	
		Struts2Utils.renderJson(node);
	}
	
	private List<TreeNode> loadChildNodeByRoot(TreeNode node){
		List<TreeNode> children = new ArrayList<TreeNode>();
		String[] dept ={"690","154"};//各商业公司的不要："512"
		//搜索dept下得部门或公司结点，放入treeNode里面
		for(int i=0;i<dept.length;i++){
			TreeNode childItem = new TreeNode();
			childItem.setId(dept[i]);
			WsPlasOrg o = PlasCache.getOrgByCd(dept[i]);
			childItem.setText(o.getOrgName());
			childItem.setChildren(loadChildrenNode(node,dept[i]));
			//childItem.setChecked("0");//需要设置默认checked属性
			children.add(childItem);
		}
		return children;
	}
	
	private List<TreeNode> loadChildrenNode(TreeNode treeNode,String orgCd){
		List<TreeNode> children = new ArrayList<TreeNode>();
		if(treeNode!=null){
			if(StringUtils.isNotBlank(orgCd)){
				//取中心所在的部门
				if("512".equals(orgCd)){
					Map<String, Object> param = new HashMap<String, Object>();
					String hql="select a from BisProject a where 1=1 order by a.sequenceNo";
					List<BisProject> projectList =bisProjectManager.find(hql, param);
					for(BisProject proj:projectList){
						TreeNode childItem = new TreeNode();
						childItem.setId(proj.getOrgCd());
						childItem.setText(proj.getProjectName());
						//childItem.setChecked("0");//需要设置默认checked属性
						children.add(childItem);
					}
					
				}else{
					List<WsPlasOrg> orgList =PlasCache.getLogicalDirectOrgListByOrgCd(orgCd);
					if(orgList!=null&&orgList.size()>0){
						for (WsPlasOrg org :orgList) {
							TreeNode childItem = new TreeNode();
							childItem.setId(org.getOrgCd());
							childItem.setText(org.getOrgName());
							//childItem.setChecked("0");//需要设置默认checked属性
							children.add(childItem);
						}
					}
				}
			}
		}
		return children;
	}
	
	public String authority(){
		authList =bisShopSortAuthManager.getAll();
		return "authority";
	}

	public boolean isHaveChecked() {
		return haveChecked;
	}

	public void setHaveChecked(boolean haveChecked) {
		this.haveChecked = haveChecked;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public List<BisShopSortAuth> getAuthList() {
		return authList;
	}

	public void setAuthList(List<BisShopSortAuth> authList) {
		this.authList = authList;
	}

	public String getShopSortId() {
		return shopSortId;
	}

	public void setShopSortId(String shopSortId) {
		this.shopSortId = shopSortId;
	}
}
