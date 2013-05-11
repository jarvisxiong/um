package com.hhz.ump.web.pm;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.pm.PmMateModuManager;
import com.hhz.ump.entity.pm.PmMateModu;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;

@Namespace("/pm")
public class PmMateModuleAction extends CrudActionSupport<PmMateModu> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PmMateModuManager pmMateModuManager;
	private List<PmMateModu> pmMateModuList;
	private PmMateModu entity;
	//父类ID
	private String parentId;
	private String moduleName;
	private String enableFlg;
	
	
	public String main() throws Exception{
		return "main";
	}
	/**
	 * 不带checkbox的企划案例库树
	 * @return
	 * @throws Exception
	 */
	public String getPmMateModuleTreeCheck() throws Exception{
		this.getPmMateModuleTree(TreePanelUtil.NODE_CHECKED_UNDEFINED);
		return null;
	}
	
	/**
	 * 得到企划案例库树
	 * @param nodeCheckType
	 * @return
	 * @throws Exception
	 */
	public String getPmMateModuleTree(String nodeCheckType) throws Exception{
		TreePanelNode node = new TreePanelNode();
		node.setChecked(nodeCheckType);
		node.setId("0");
		node.setNodeType("0");
		node.setText("企划案例库");
		node.setChildren(pmMateModuManager.getChildrenNode(node, true,nodeCheckType));
		Struts2Utils.renderJson(node);
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PmMateModu getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PmMateModu> getPmMateModuList() {
		return pmMateModuList;
	}

	public void setPmMateModuList(List<PmMateModu> pmMateModuList) {
		this.pmMateModuList = pmMateModuList;
	}

	public PmMateModu getEntity() {
		return entity;
	}

	public void setEntity(PmMateModu entity) {
		this.entity = entity;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getEnableFlg() {
		return enableFlg;
	}

	public void setEnableFlg(String enableFlg) {
		this.enableFlg = enableFlg;
	}
	

}
