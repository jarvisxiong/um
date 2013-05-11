/**
 * 
 */
package com.hhz.uums.web.plas;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.plas.PlasNodeSysPosRelManager;
import com.hhz.uums.dao.plas.PlasSysPosRoleRelManager;
import com.hhz.uums.entity.plas.PlasNodeSysPosRel;
import com.hhz.uums.utils.TreePanelUtil2;
import com.hhz.uums.utils.Util;
import com.hhz.uums.vo.pd.ResNode;
import com.hhz.uums.vo.vw.VoSysPosition;
import com.hhz.uums.web.CrudActionSupport;

/**
 * <p>网批节点和系统职务对应关系表</p>
 * @author huangjian
 * @create 2012-3-16
 */
public class PlasNodeSysPosRelAction extends CrudActionSupport<PlasNodeSysPosRel> {
	private static final long serialVersionUID = -7294626654599279367L;
	private PlasNodeSysPosRel entity;
	private List<ResNode> resNodes;
	private String nodeCd;
	private String sysPosId;
	private List<String> plasOrgNames;
	@Autowired
	private PlasNodeSysPosRelManager plasNodeSysPosRelManager;

	@Autowired
	private PlasSysPosRoleRelManager sysPosRoleRelManager;
	@Override
	public PlasNodeSysPosRel getModel() {
		// TODO Auto-generated method stub
		return entity;
	}
	@Override
	public String execute() throws Exception {
		try {
			resNodes=Util.getPdService().loadCustomerResNode();
		} catch (Exception e) {
			logger.error("加载网批节点失败！",e);
		}
		return SUCCESS;
	}
	
	public void loadNodeSysPosRelTree() {
		List<VoSysPosition> sysPosList = sysPosRoleRelManager.getVoSysPostionByNodeCd(nodeCd);
		Struts2Utils.renderJson(TreePanelUtil2.getPosTreeNodePanel(
				TreePanelUtil2.TREE_TYPE_PHYSICAL, 
				SpringSecurityUtils.getCurUiid(), 
				true,
				sysPosList, 
				true, false,false));
	}
	public String loadOrdList()throws Exception {
		plasOrgNames= plasNodeSysPosRelManager.loadBySysPosId(sysPosId);
		return "org";
	}

	public void saveBatchRoleSysPosRel() {
		if(StringUtils.isBlank(nodeCd)){
			Struts2Utils.renderText("nodeCd is blank!");
		}else{
			String addRoleIds = Struts2Utils.getParameter("addPosARoleIds");
			String delRoleIds = Struts2Utils.getParameter("delPosARoleIds");
			plasNodeSysPosRelManager.saveBatchNodeSysPosRel(nodeCd, addRoleIds, delRoleIds);
			Struts2Utils.renderText("success"); 
		}
	}
	

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}
	public List<ResNode> getResNodes() {
		return resNodes;
	}
	public String getNodeCd() {
		return nodeCd;
	}
	public void setNodeCd(String nodeCd) {
		this.nodeCd = nodeCd;
	}
	public String getSysPosId() {
		return sysPosId;
	}
	public void setSysPosId(String sysPosId) {
		this.sysPosId = sysPosId;
	}
	public List<String> getPlasOrgNames() {
		return plasOrgNames;
	}
	public void setPlasOrgNames(List<String> plasOrgNames) {
		this.plasOrgNames = plasOrgNames;
	}


}
