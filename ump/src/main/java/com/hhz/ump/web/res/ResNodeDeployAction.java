package com.hhz.ump.web.res;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.res.ResNodeManager;
import com.hhz.ump.entity.res.ResNode;
import com.hhz.ump.util.DictContants;

/**
 * 审批节点配置
 * 
 * @author zmwu 2010-6-8
 */
@Namespace("/res")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "res-node-deploy.action", type = "redirect" ,params={"filter_EQS_nodeCd", "${filter_EQS_nodeCd}" ,"filter_LIKES_nodeName", "${filter_LIKES_nodeName}" ,"filter_EQS_nodeTypeCd", "${filter_EQS_nodeTypeCd}" 	,"filter_EQS_orgKindCd", "${filter_EQS_orgKindCd}","filter_EQS_userKindCd", "${filter_EQS_userKindCd}","filter_EQS_nodeLevelCd", "${filter_EQS_nodeLevelCd}","page.pageNo", "${page.pageNo}" }) })
public class ResNodeDeployAction extends CrudActionSupport<ResNode> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ResNode entity;

	@Autowired
	private ResNodeManager resNodeManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;
	private String filter_EQS_nodeCd;
	private String filter_LIKES_nodeName;
	private String filter_EQS_nodeTypeCd;
	private String filter_EQS_orgKindCd;
	private String filter_EQS_userKindCd;
	private String filter_EQS_nodeLevelCd;
	private Boolean filter_EQB_active;
	
	

	
	/**
	 * @return the filter_EQS_orgKindCd
	 */
	public String getFilter_EQS_orgKindCd() {
		return filter_EQS_orgKindCd;
	}

	/**
	 * @param filterEQSOrgKindCd the filter_EQS_orgKindCd to set
	 */
	public void setFilter_EQS_orgKindCd(String filterEQSOrgKindCd) {
		filter_EQS_orgKindCd = filterEQSOrgKindCd;
	}

	/**
	 * @return the filter_EQS_userKindCd
	 */
	public String getFilter_EQS_userKindCd() {
		return filter_EQS_userKindCd;
	}

	/**
	 * @param filterEQSUserKindCd the filter_EQS_userKindCd to set
	 */
	public void setFilter_EQS_userKindCd(String filterEQSUserKindCd) {
		filter_EQS_userKindCd = filterEQSUserKindCd;
	}

	/**
	 * @return the filter_EQS_nodeLevelCd
	 */
	public String getFilter_EQS_nodeLevelCd() {
		return filter_EQS_nodeLevelCd;
	}

	/**
	 * @param filterEQSNodeLevelCd the filter_EQS_nodeLevelCd to set
	 */
	public void setFilter_EQS_nodeLevelCd(String filterEQSNodeLevelCd) {
		filter_EQS_nodeLevelCd = filterEQSNodeLevelCd;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		resNodeManager.deleteResNode(getId());
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return RELOAD;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return RELOAD;
	}

	@Override
	public String list() throws Exception {
		page.setPageSize(20);
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("sequenceNo");
			page.setOrder(Page.ASC);
		}
		page = resNodeManager.findPage(page, filters);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (getId() != null) {
			entity = resNodeManager.getEntity(getId());
			// parserXml(placeBill,
			// Util.clob2String(entity.getTempletContent()));
		} else {
			entity = new ResNode();
			entity.setActive(true);
			entity.setIsLock(true);
		}

	}

	public Map<String, String> getMapNodeType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.SP_NODE_TYPE);
	}

	
	public Map<String, String> getMapOrgKindCd() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.ORG_KIND_CD);
	}
	
	
	public Map<String, String> getMapUserKindCd() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.USER_KIND_CD);
	}
	
	public Map<String, String> getMapNodeLevelCd() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.NODE_LEVEL_CD);
	}

	public String checkCd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newCd = request.getParameter("newCd");
		String oldCd = request.getParameter("oldCd");
		if (resNodeManager.isPropertyUnique("nodeCd", newCd, oldCd)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		return null;
	}

	@Override
	public String save() throws Exception {
	
		filter_EQS_nodeCd=Struts2Utils.getParameter("filter_EQS_nodeCd1");
		setFilter_LIKES_nodeName(Struts2Utils.getParameter("filter_LIKES_nodeName1"));
		filter_EQS_nodeTypeCd=Struts2Utils.getParameter("filter_EQS_nodeTypeCd1");
		Struts2Utils.getSession().setAttribute("filter",Struts2Utils.getParameter("filter_LIKES_nodeName1"));
		// TODO Auto-generated method stub
		resNodeManager.saveResNode(entity);
		return RELOAD;
	}

	public ResNode getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public String getFilter_EQS_nodeCd() {
		return filter_EQS_nodeCd;
	}

	public void setFilter_EQS_nodeCd(String filterEQSNodeCd) {
		filter_EQS_nodeCd = filterEQSNodeCd;
	}

	public String getFilter_LIKES_nodeName() {
		
		String filter=""+Struts2Utils.getSession().getAttribute("filter");
		if(filter.equals("null")){
		Struts2Utils.getSession().setAttribute("filter", filter_LIKES_nodeName);
		}
		return filter_LIKES_nodeName;
	}

	public void setFilter_LIKES_nodeName(String filterLIKESNodeName) {
		filter_LIKES_nodeName = filterLIKESNodeName;
	
	}

	public String getFilter_EQS_nodeTypeCd() {
		return filter_EQS_nodeTypeCd;
	}

	public void setFilter_EQS_nodeTypeCd(String filterEQSNodeTypeCd) {
		filter_EQS_nodeTypeCd = filterEQSNodeTypeCd;
	}

	public Boolean getFilter_EQB_active() {
		return filter_EQB_active;
	}

	public void setFilter_EQB_active(Boolean filter_EQB_active) {
		this.filter_EQB_active = filter_EQB_active;
	}

}
