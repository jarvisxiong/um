package com.hhz.ump.web.oa;

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
import com.hhz.ump.dao.oa.OaManApproveStepManager;
import com.hhz.ump.entity.oa.OaManApproveStep;
import com.hhz.ump.util.DictContants;

/**
 * 审批节点配置
 * 
 * @author zmwu 2010-6-8
 */
@Namespace("/oa")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "oa-man-approve-step.action", type = "redirect") })
public class OaManApproveStepAction extends CrudActionSupport<OaManApproveStep> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OaManApproveStep entity;

	@Autowired
	private OaManApproveStepManager oaApproveStepManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	private String nodeTypeCd;
	
	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		oaApproveStepManager.deleteOaManApproveStep(getId());
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
			page.setOrderBy("createdDate");
			page.setOrder(Page.ASC);
		}
		page = oaApproveStepManager.findPage(page, filters);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (getId() != null) {
			entity = oaApproveStepManager.getEntity(getId());
			// parserXml(placeBill,
			// Util.clob2String(entity.getTempletContent()));
		} else {
			entity = new OaManApproveStep();
		}

	}

	/**
	 * 模块名称 
	 * @return
	 */
	public Map<String, String> getMapDictCdType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.SP_NODE_TYPE_MAN);
	}
	
	/**
	 * 节点名称
	 * @return
	 */
	public Map<String,String> getMapNodeCdType(){
		return oaApproveStepManager.getGroupNodeCd();
	}

	public String checkCd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newCd = request.getParameter("newCd");
		String oldCd = request.getParameter("oldCd");
		if (oaApproveStepManager.isPropertyUnique("nodeCd", newCd, oldCd)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		oaApproveStepManager.saveOaManApproveStep(entity);
		return RELOAD;
	}

	public OaManApproveStep getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public String getNodeTypeCd() {
		return nodeTypeCd;
	}

	public void setNodeTypeCd(String nodeTypeCd) {
		this.nodeTypeCd = nodeTypeCd;
	}

}
