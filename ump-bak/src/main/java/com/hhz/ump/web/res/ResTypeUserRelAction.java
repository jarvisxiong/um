/**
 * 
 */
package com.hhz.ump.web.res;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.PowerUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppParamManager;
import com.hhz.ump.dao.res.ResAuthTypeManager;
import com.hhz.ump.dao.res.ResModuleManager;
import com.hhz.ump.dao.res.ResTypeUserRelManager;
import com.hhz.ump.entity.res.ResAuthType;
import com.hhz.ump.entity.res.ResModule;
import com.hhz.ump.entity.res.ResTypeUserRel;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;
import com.hhz.ump.util.TreePanelUtilRes;

/**
 * @author huangjian
 * 
 *         2011-3-7
 */
@Namespace("/res")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "res-type-user-rel.action", type = "redirect") })
public class ResTypeUserRelAction extends CrudActionSupport<ResTypeUserRel> {
	private static final long serialVersionUID = 5902569652233720022L;

	@Autowired
	private ResModuleManager resModuleManager;

	@Autowired
	private ResAuthTypeManager resAuthTypeManager;

	@Autowired
	private AppParamManager appParamManager;
	@Autowired
	private ResTypeUserRelManager resTypeUserRelManager;
	private ResTypeUserRel entity;
	private String chkAuthTypeCds;
	private String relIds;
	private String relTypeCd;

	@Override
	public String delete() throws Exception {
		resTypeUserRelManager.deleteResTypeUserRel(getId());
		return RELOAD;
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
//		page.setPageSize(20);
//		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
//		page.setOrderBy("createdDate");
//		page.setOrder(Page.DESC);
//		page = resTypeUserRelManager.findPage(page, filters);

		return SUCCESS;
	}

	public String buildUserTree() {
		TreePanelNode node = TreePanelUtil.buildPhysicalOrgUserTree(genRootNode(), PlasCache.getOrgEnableList(), PlasCache.getUserActiveList());
		Struts2Utils.renderJson(node);
		return null;
	}

	public String buildResTree() {
		String isChecked = Struts2Utils.getParameter("isChecked");
		String uiid = Struts2Utils.getParameter("uiid");
		Map<String, String> mapCd2Id = new HashMap<String, String>();
		if (StringUtils.isNotBlank(uiid)) {
			Criterion c1 = Restrictions.eq("userCd", uiid);
			Criterion c2 = Restrictions.eq("relTypeCd", relTypeCd);
			List<ResTypeUserRel> rels = resTypeUserRelManager.findBy(c1, c2);
			for (ResTypeUserRel resTypeUserRel : rels) {
				mapCd2Id.put(resTypeUserRel.getAuthTypeCd(), resTypeUserRel.getResTypeUserRelId());
			}
			// lisAuthTypes=PowerUtils.getProptyArray(rels, "authTypeCd");
		}
		List<ResModule> modules;
		List<ResAuthType> authTypes;
		modules = resModuleManager.loadActiveResModule(ResConstants.MODULE_TYPE_CD_RES);
		authTypes = resAuthTypeManager.loadPublishResAuthType();
		TreePanelNode node = TreePanelUtilRes.buildProjectModuleTree(modules, authTypes, "表单模板(按权责审批表分类)", isChecked, mapCd2Id);
		Struts2Utils.renderJson(node);
		return null;
	}

	private TreePanelNode genRootNode() {
		TreePanelNode rootNode = new TreePanelNode();
		rootNode.setId(appParamManager.getAppOrgTreeRootCd());
		rootNode.setText(appParamManager.getAppOrgTreeRootName());
		rootNode.setEntityId(appParamManager.getAppOrgTreeRootCd());// 特殊处理
		rootNode.setEntityCd(appParamManager.getAppOrgTreeRootCd());// 特殊处理
		return rootNode;

	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isBlank(getId())) {
			entity = new ResTypeUserRel();
		} else {
			entity = resTypeUserRelManager.getEntity(getId());
		}
	}

	@Override
	public String save() throws Exception {
		if (chkAuthTypeCds == null)
			return null;
		String uiid = Struts2Utils.getParameter("uiid");
		String[] authTypeCds = chkAuthTypeCds.split(",", Integer.MAX_VALUE);
		relIds = PowerUtils.trimArray(relIds, ",");
		String[] resRelIds = relIds.split(",", Integer.MAX_VALUE);

		List<ResTypeUserRel> list = new ArrayList<ResTypeUserRel>();
		for (int i = 0; i < authTypeCds.length; i++) {
			String authTypeCd = StringUtils.trimToNull(authTypeCds[i]);
			String relId = resRelIds[i];
			if (StringUtils.isBlank(relId)) {
				Criterion c1 = Restrictions.eq("authTypeCd", authTypeCd);
				Criterion c2 = Restrictions.eq("userCd", uiid);
				Criterion c3 = Restrictions.eq("relTypeCd", relTypeCd);
				int cnt = resTypeUserRelManager.countCriteriaResult(c1, c2, c3);
				if (cnt == 0) {
					ResTypeUserRel rel = new ResTypeUserRel();
					rel.setAuthTypeCd(authTypeCd);
					rel.setUserCd(uiid);
					rel.setRelTypeCd(relTypeCd);
					list.add(rel);
				}
			}
		}
		resTypeUserRelManager.deleteBatch(resRelIds);
		resTypeUserRelManager.saveResTypeUserRels(list);
		Struts2Utils.renderText("success");
		return null;
	}

	@Override
	public ResTypeUserRel getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public String getChkAuthTypeCds() {
		return chkAuthTypeCds;
	}

	public void setChkAuthTypeCds(String chkAuthTypeCds) {
		this.chkAuthTypeCds = chkAuthTypeCds;
	}

	public String getRelIds() {
		return relIds;
	}

	public void setRelIds(String relIds) {
		this.relIds = relIds;
	}

	public String getRelTypeCd() {
		return relTypeCd;
	}

	public void setRelTypeCd(String relTypeCd) {
		this.relTypeCd = relTypeCd;
	}

}
