/**
 * 
 */
package com.hhz.ump.web.oa;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.ServiceException;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.oa.OaAllAttentionManager;
import com.hhz.ump.entity.oa.OaAllAttention;

/**
 * 关注
 * @author lujunyun 2010-1-5
 */
@Namespace("/oa")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "oa-all-attention.action", type = "redirect"),
    	    @Result(name = "read", location = "oa-all-attention!read.action", type = "redirect", params = { "id", "${id}" }),
		@Result(name = CrudActionSupport.RELOAD_SUB, location = "oa-all-attention!input.action", type = "redirect", params = { "id", "${id}" }) })
public class OaAllAttentionAction extends CrudActionSupport<OaAllAttention> {
	private static final long serialVersionUID = 8255399345919335946L;

	@Autowired
	private OaAllAttentionManager oaAllAttentionManager;
	
	private OaAllAttention entity;

	private String commentId;
	private String moduleCd;
	private String entityId;
	private long moduleRecordVersion;
	
	private String[] entityIds;	//批量操作时传入的entityId参数数组
	private String[] recordVersions;	//批量操作时传入的id参数数组
	private String[] ifDeletes;	//批量操作时传入的是否查看完删除参数数组

	@Override
	public String delete() throws Exception {
		try {
			oaAllAttentionManager.delete(moduleCd, SpringSecurityUtils.getCurrentUiid(), entityId);
			Struts2Utils.renderText("success");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			Struts2Utils.renderText("删除失败");
		}
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
	    try {
			oaAllAttentionManager.deleteBatch(getIds());
			Struts2Utils.renderText("success");
	    } catch (ServiceException e) {
			logger.error(e.getMessage(), e);
	    }
	    return RELOAD;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("moduleCd,updatedDate");
			page.setOrder(Page.DESC+","+Page.DESC);
		}
		page = oaAllAttentionManager.findPage(page, filters);
		return SUCCESS;
	}
	
	/*
	 * 首页用的，显示有更新的关注记录
	 */
	public String forHome() throws Exception {
		String userCd = SpringSecurityUtils.getCurrentUiid();
		Struts2Utils.getRequest().setAttribute("planWork2Count",
				Integer.toString(oaAllAttentionManager.getHomeByModuleCd(
						"planWork2", userCd, "PLAN_WORK2", "PLAN_WORK2_ID")));
		Struts2Utils.getRequest().setAttribute("planWorkCenterCount",
				Integer.toString(oaAllAttentionManager.getHomeByModuleCd(
						"planWorkCenter", userCd, "PLAN_WORK_CENTER", "PLAN_WORK_CENTER_ID")));
		Struts2Utils.getRequest().setAttribute("oaMeetingCount",
				Integer.toString(oaAllAttentionManager.getHomeByModuleCd(
						"oaMeeting", userCd, "OA_MEETING", "OA_MEETING_ID")));
//		Struts2Utils.getRequest().setAttribute("planExecutionPlanNodeCount",
//				Integer.toString(oaAllAttentionManager.getHomeByModuleCd(
//						"planExecutionPlanNode", userCd,"PLAN_EXECUTION_PLAN_NODE", "PLAN_EXECUTION_PLAN_NODE_ID")));
		Struts2Utils.getRequest().setAttribute("planExecutionPlanNodeCount",0);
		return "forHome";
	}
	
	/*
	 * 批量设置已读过
	 */
	public String setAttentionRead() throws Exception {
		try {
			String userCd = SpringSecurityUtils.getCurrentUiid();
			for (int i=0;null!=entityIds&&i<entityIds.length;i++) {
				String temp_entityId = entityIds[i];
				String temp_recordVersion = recordVersions[i];
				String temp_ifDelete = ifDeletes[i];
				oaAllAttentionManager.setAttentionRead(moduleCd,temp_entityId,temp_recordVersion,temp_ifDelete,userCd);
			}
			addActionMessage(getText("common.success"));
			Struts2Utils.renderText("done");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
			Struts2Utils.renderText("error");
		}
		return null;
	}
	
	@Override
	protected void prepareModel() throws Exception {
	    if (getId() != null) {
	    	entity = oaAllAttentionManager.getEntity(getId());
	    }else{
	    	entity = new OaAllAttention();
	    }
	}
	
	@Override
	public String save() throws Exception {
		oaAllAttentionManager.saveByParam(moduleCd,entityId,moduleRecordVersion,SpringSecurityUtils.getCurrentUiid());
		return null;
	}
	

	/**
	 * 在save()前执行二次绑定.
	 */
	public void prepareRead() throws Exception {
		prepareModel();
	}

	public OaAllAttention getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getModuleCd() {
		return moduleCd;
	}

	public void setModuleCd(String moduleCd) {
		this.moduleCd = moduleCd;
	}


	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public long getModuleRecordVersion() {
		return moduleRecordVersion;
	}

	public void setModuleRecordVersion(long moduleRecordVersion) {
		this.moduleRecordVersion = moduleRecordVersion;
	}

	public OaAllAttention getEntity() {
		return entity;
	}

	public void setEntity(OaAllAttention entity) {
		this.entity = entity;
	}

	public String[] getEntityIds() {
		return entityIds;
	}

	public void setEntityIds(String[] entityIds) {
		this.entityIds = entityIds;
	}

	public String[] getRecordVersions() {
		return recordVersions;
	}

	public void setRecordVersions(String[] recordVersions) {
		this.recordVersions = recordVersions;
	}

	public String[] getIfDeletes() {
		return ifDeletes;
	}

	public void setIfDeletes(String[] ifDeletes) {
		this.ifDeletes = ifDeletes;
	}

}
