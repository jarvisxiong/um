package com.hhz.uums.web.plas;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.plas.PlasRoleManager;
import com.hhz.uums.dao.plas.PlasSysPosRoleRelManager;
import com.hhz.uums.entity.plas.PlasRole;
import com.hhz.uums.entity.plas.PlasSysPosRoleRel;
import com.hhz.uums.utils.TreePanelUtil;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.vo.vw.VoSysPosition;
import com.hhz.uums.web.CrudActionSupport;

/**
 * ------------------------------------------------------- date || author ||
 * Description || 2011-2-19 || jiaoxiaofeng || create ||PlasSysPosRoleRel.java
 * Description::系统职位与角色关联
 * -------------------------------------------------------
 **/
public class PlasSysPosRoleRelAction extends CrudActionSupport<PlasSysPosRoleRel> {

	private static final long serialVersionUID = -5651730807363468582L;

	@Autowired
	private PlasSysPosRoleRelManager sysPosRoleRelManager;
	
	@Autowired
	private PlasRoleManager plasRoleManager;
	
	private String plasSysPositionId;
	private String posName;
	private String roleId;

	public PlasSysPosRoleRel entity;
	
	public PlasSysPosRoleRel getEntity() {
		return entity;
	}

	public void setEntity(PlasSysPosRoleRel entity) {
		this.entity = entity;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = sysPosRoleRelManager.getEntity(getId());
		} else {
			entity = new PlasSysPosRoleRel();
		}
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public PlasSysPosRoleRel getModel() {
		return entity;
	}

	/**
	 * 加载指定系统职位相对应的应用与角色树
	 * 
	 */
	public void loadSysPosRoleRelTree() {
		List<PlasRole> roleCheckList = new ArrayList<PlasRole>();
		List<PlasSysPosRoleRel> sysPosRelList = plasRoleManager.getCurSysPosRoleRelList(plasSysPositionId);
		for (PlasSysPosRoleRel sysPosRoleRel : sysPosRelList) {
			roleCheckList.add(sysPosRoleRel.getPlasRole());
		}
		Struts2Utils.renderJson(TreePanelUtil.createRoleGroupTreeCheck(roleCheckList));
	}

	/**
	 * 
	 * 
	 */
	/**
	 * Descrption：加载指定角色相对应的逻辑机构系统职位树（复选框） 
	 * voidauthor:jiaoxiaofeng 2011-2-19 
	 * @param roleId ： 角色ID
	 * @param isAllFlg ： 是否所有职位
	 */
	public void loadRoleSysPosRelTree() {
//		List<PlasSysPosition> sysPosList = sysPosRoleRelManager.getSysPostionByRole(roleId);
//		Struts2Utils.renderJson(TreePanelUtil.createOrgPosition(DictContants.TREE_DIME_LOGICAL,sysPosList, true));

		
		String tmpAllFlg = Struts2Utils.getParameter("isAllFlg");
		List<VoSysPosition> sysPosList = sysPosRoleRelManager.getVoSysPostionByRole(roleId);
		Struts2Utils.renderJson(TreePanelUtil2.getPosTreeNodePanel(
				TreePanelUtil2.TREE_TYPE_PHYSICAL, 
				SpringSecurityUtils.getCurUiid(), 
				true,
				sysPosList, 
				true, "true".equals(tmpAllFlg),false));
	}
	
	/**
	 * Descrption：查看角色对应的职位清单
	 * @param roleId ： 角色ID
	 * @param isAllFlg ： 是否所有职位
	 * @author liuzh 2011-12-15
	 */
	public void loadRoleSysPosRelTreeDetail() {
		String tmpAllFlg = Struts2Utils.getParameter("isAllFlg");
		List<VoSysPosition> sysPosList = sysPosRoleRelManager.getVoSysPostionByRole(roleId);
		Struts2Utils.renderJson(TreePanelUtil2.getPosTreeNodePanel(
				TreePanelUtil2.TREE_TYPE_PHYSICAL, 
				SpringSecurityUtils.getCurUiid(),
				true, sysPosList, true, "true".equals(tmpAllFlg),true));
				
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String toLoadRoleSysPosRelTree() {
		return "loadRoleSysPosRelTree";
	}

	public String getPlasSysPositionId() {
		return plasSysPositionId;
	}

	public void setPlasSysPositionId(String plasSysPositionId) {
		this.plasSysPositionId = plasSysPositionId;
	}

 	/**
	 * 系统职位管理模块：授权某系统职位拥有多个角色
	 */
	public void prepareSaveBatch() throws Exception {
		prepareModel();
	}
	public void saveBatch(){
		
		if(StringUtils.isBlank(plasSysPositionId)){
			Struts2Utils.renderText("system position id is blank!");
		}else{
			String addRoleIds = Struts2Utils.getParameter("addRoleIds");
			String delRoleIds = Struts2Utils.getParameter("delRoleIds");
			sysPosRoleRelManager.saveBatch(plasSysPositionId, addRoleIds, delRoleIds);
			Struts2Utils.renderText("success");
		}
	}
	/**
	 * 角色模块：保存配置角色与系统职位关系
	 * @param roleId
	 * @param addPosARoleIds
	 * @param delPosARoleIds
	 * 
	 */

	public void prepareSaveBatchRoleSysPosRel() throws Exception {
		prepareModel();
	}

	public void saveBatchRoleSysPosRel() {

		if(StringUtils.isBlank(roleId)){
			Struts2Utils.renderText("system role id is blank!");
		}else{
			String addRoleIds = Struts2Utils.getParameter("addPosARoleIds");
			String delRoleIds = Struts2Utils.getParameter("delPosARoleIds");
			sysPosRoleRelManager.saveBatchRoleSysPosRel(roleId, addRoleIds, delRoleIds);
			Struts2Utils.renderText("success"); 
		}
	}
	
	/**
	 * 查询角色对应的职位清单
	 * @param roleId
	 */
	public void viewRelRoleList(){
		String roleId = Struts2Utils.getParameter("roleId");
		if(StringUtils.isBlank(roleId)){
			Struts2Utils.renderText("参数不对!无角色!");
			return;
		}
		//TODO
		
	}
}
