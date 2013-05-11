package com.hhz.uums.web.plas;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.plas.PlasAcctManager;
import com.hhz.uums.dao.plas.PlasOrgCheckRelManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.dao.plas.PlasSysPositionManager;
import com.hhz.uums.dao.plas.PlasUserManager;
import com.hhz.uums.entity.plas.PlasOrgCheckRel;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.utils.TreePanelUtil;
import com.hhz.uums.web.CrudActionSupport;

public class PlasOrgCheckRelAction extends CrudActionSupport<PlasOrgCheckRel> {
	@Autowired
	private PlasOrgManager plasOrgManager;
	@Autowired
	private PlasUserManager plasUserManager;
	@Autowired
	private PlasAcctManager plasAcctManager;
	@Autowired
	private PlasSysPositionManager plasSysPositionManager;
	@Autowired
	private PlasOrgCheckRelManager plasOrgCheckRelManager;
	private String orgId;
	private String sysPosId;
	private PlasOrgCheckRel entity;
	 
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Override
	public PlasOrgCheckRel getModel() {
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		return SUCCESS;
	}

	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return SUCCESS;
	}



	@Override
	protected void prepareModel() throws Exception {
	}
	@Override
	public String delete() throws Exception {
		String posId = Struts2Utils.getParameter("posId");
		plasOrgCheckRelManager.saveBatch(orgId,"",posId);
		Struts2Utils.renderText("success");
		return null;
	}
	/**
	 * 增加机构审计人员
	 */
	@Override
	public String save() throws Exception {
		String sysPosCd = Struts2Utils.getParameter("sysPosCd");
		PlasSysPosition sysPos = plasSysPositionManager.getEntityBySysPosCd(sysPosCd);
		plasOrgCheckRelManager.saveBatch(orgId,sysPos.getPlasSysPositionId(),"");
		Struts2Utils.renderText("success");
		return null;
	}
	/**
	 * 批量保存机构审计人员
	 */
	public void saveBatch(){
		String addPosAOrgIds = Struts2Utils.getParameter("addPosAOrgIds").trim();
		String delPosAOrgIds = Struts2Utils.getParameter("delPosAOrgIds").trim();
		plasOrgCheckRelManager.saveBatch(orgId, addPosAOrgIds, delPosAOrgIds);
		Struts2Utils.renderText("success");
	}
	/**
	 * 加载机构审计职位树
	 */
	public void loadOrgCheckPhysicalTree(){
		List<PlasSysPosition> sysPosList = plasOrgCheckRelManager.getPlasSysPositionByOrgId(orgId);
		Struts2Utils.renderJson(TreePanelUtil.createOrgPosition(DictContants.TREE_DIME_PHYSICAL,sysPosList,true));
	}
	public void loadOrgCheckLogicalTree(){
		List<PlasSysPosition> sysPosList = plasOrgCheckRelManager.getPlasSysPositionByOrgId(orgId);
		Struts2Utils.renderJson(TreePanelUtil.createOrgPosition(DictContants.TREE_DIME_LOGICAL,sysPosList,true));
	}
	/**
	 * 获取机构审计职位列表
	 */
	public void loadOrgCheckList(){
		pageSet();
		plasOrgCheckRelManager.findOrgCheckPage(page, orgId);
		JsonUtil.renderJson(page, new String[] {});
	}
	private void pageSet(){
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("sequenceNo");
			page.setOrder( Page.ASC);
		} else {
			page.setOrderBy(sortField + ",sequenceNo");
			page.setOrder(order + "," + Page.ASC);
		}
	}
}

