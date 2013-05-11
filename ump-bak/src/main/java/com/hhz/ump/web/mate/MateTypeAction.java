package com.hhz.ump.web.mate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cont.ContProjectCodeManager;
import com.hhz.ump.dao.mate.MateOwnerTypeManager;
import com.hhz.ump.entity.cont.ContProjectCode;
import com.hhz.ump.entity.mate.MateOwnerType;
import com.hhz.ump.util.TreeNode;
import com.hhz.ump.util.TreePanelUtil;
import com.hhz.uums.entity.ws.WsPlasOrg;

public class MateTypeAction extends CrudActionSupport<MateOwnerType> {

	private static final long serialVersionUID = 6125041003652580927L;
	@Autowired
	private MateOwnerTypeManager mateTypeManager;
	@Autowired
	private ContProjectCodeManager contProjectCodeManager;
	@Autowired
	private MateOwnerTypeManager mateOwnerTypeManager;
	
	
	private MateOwnerType entity;

	@Override
	public MateOwnerType getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return "input";
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		String ownerParMaterialType =Struts2Utils.getParameter("ownerParMaterialType");
		entity.setOwnerParMaterialType(ownerParMaterialType);
		if (entity.getOwnerMaterialType() == null || "".equals(entity.getOwnerMaterialType())) {
			entity.setOwnerMaterialType(RandomUtils.generateTmpEntityId());
		}
		//若父类为自己，则不允许保存
		if(entity.getOwnerParMaterialType()!=null&&entity.getOwnerMaterialType().equals(entity.getOwnerParMaterialType()))
			return null;
		mateTypeManager.saveMateOwnerType(entity);
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getId())) {
			//entity = contContractTypeManager.getContContractType(getId());
			mateTypeManager.deleteMateOwnerType(getId());
			addActionMessage(getText("common.success"));
		}		
		return null;
	}
	@Override
	public void prepareSave() {
		if (StringUtils.isNotBlank(getId())) {
			entity = mateTypeManager.getMateOwnerType(getId());
			entity.setRecordVersion(entity.getRecordVersion() + 1);
		} else {
			entity = new MateOwnerType();
		}
	}
	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 构建甲供材料类型树
	 * @return
	 * @throws Exception
	 */
	public String getMateTypeTree() throws Exception {
//		TreeNode node = new TreeNode();
//		node.setId("0");
//		node.setFinType("");
//		node.setText("甲供材料类别");
//		node.setOrderNo(new Long(0));
//		node.setChildren(mateTypeManager.getChildrenNode(node, true));
//		Struts2Utils.renderJson(node);

		getMateTreeNode(TreePanelUtil.NODE_CHECKED_NONE);
		
		return null;
	}	
	public String getItemTree() throws Exception {
//		TreeNode node = new TreeNode();
//		node.setId("0");
//		node.setFinType("");
//		node.setText("甲供材料类别");
//		node.setOrderNo(new Long(0));
//		node.setChildren(mateTypeManager.getChildrenNode(node, false));
//		Struts2Utils.renderJson(node);

		getMateTreeNode(TreePanelUtil.NODE_CHECKED_UNDEFINED);
		
		return null;
	}
	
	
	/**
	 * 获取甲供料类型节点
	 * 若是管理员，则查看所有项目
	 * 若非管理员,则仅查看本中心或已授权的项目
	 * @return
	 */
	private String getMateTreeNode(String nodeCheckType){

		//搜索甲供料 授权项目列表
		List<ContProjectCode>  projectList = null;
		
		boolean containFlg = SpringSecurityUtils.hasRole("A_MATE_ADMIN");//A_MATE_ADMIN-甲供料台账管理员
		if(containFlg){
			//获取所有的项目公司
			projectList = contProjectCodeManager.getAllProjectList(null, "1");//1-地产
		}else{
			projectList = contProjectCodeManager.getAuthProjectList(SpringSecurityUtils.getCurrentUiid(), "1");//1-地产
		}
		
		//若获取不到,则授权所有项目
		if( projectList == null ||  projectList.size() == 0){
			projectList = new ArrayList<ContProjectCode>();
		}
		
		//获取甲供料 台账类型列表
		List<MateOwnerType> mateTypeList = mateOwnerTypeManager.getAllMateOwnerTypeList();
		TreeNode node = TreePanelUtil.buildMateTypeTreeNode(nodeCheckType, projectList, mateTypeList);
		Struts2Utils.renderJson(node);
		return null;
	}
	
	public MateOwnerType getEntity() {
		return entity;
	}

	public void setEntity(MateOwnerType entity) {
		this.entity = entity;
	}
	/**
	 * 获取工程项目的所有节点
	 * @return
	 * @throws Exception
	 */
	public String getParentTree() throws Exception {
//		TreeNode node = new TreeNode();
//		node.setId("0");
//		node.setFinType("");
//		node.setText("甲供材料类别");
//		node.setOrderNo(new Long(0));
//		//得到工程节点
//		List<TreeNode> projectNode =new ArrayList<TreeNode>();
//		List<WsPlasOrg> orgList =mateTypeManager.getAllProjectNode();
//		for (WsPlasOrg org : orgList) {
//			TreeNode proItem = new TreeNode();
//			//类型CD
//			proItem.setId(org.getOrgCd());
//			proItem.setFinType(org.getOrgCd());
//			proItem.setText(org.getOrgName());
//			List<MateOwnerType> mateOwnerType = mateTypeManager.getAll();
//			proItem.setChildren(mateTypeManager.getProjectChildrenNode(proItem, false,mateOwnerType));
//			projectNode.add(proItem);
//		}
//		node.setChildren(projectNode);
		

		List<ContProjectCode>  projectList  = new ArrayList<ContProjectCode>();
		//获取所有项目
		List<WsPlasOrg> orgList = mateTypeManager.getAllProjectNode();
		ContProjectCode tmp = null;
		for (WsPlasOrg tOrg : orgList) {
			tmp = new ContProjectCode();
			tmp.setProjectCd(tOrg.getOrgCd());
			tmp.setProjectName(tOrg.getOrgName());
			projectList.add(tmp);
		}

		//所有甲供料类型
		List<MateOwnerType> mateTypeList = mateOwnerTypeManager.getAllMateOwnerTypeList();
		TreeNode node = TreePanelUtil.buildMateTypeTreeNode(TreePanelUtil.NODE_CHECKED_UNDEFINED, projectList, mateTypeList);
		Struts2Utils.renderJson(node);
		return null;
	}	

}
