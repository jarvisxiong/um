/**
 * 
 */
package com.hhz.ump.web.bis;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.bis.BisProjectLayoutManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisProjectLayout;

/**
 * @author baolm 2011-8-1
 */
@Namespace("/bis")
public class BisProjectLayoutAction extends CrudActionSupport<BisProjectLayout> {

	private static final long serialVersionUID = -4395887327960976741L;

	private BisProjectLayout entity;
	
	private String bisProjectId;
	private String bisProjectName;
	
	private String entityTmpId;
	
	@Autowired
	private BisProjectLayoutManager bisProjectLayoutManager;
	
	@Autowired
	private BisProjectManager bisProjectManager;
	
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	
	@Override
	public BisProjectLayout getModel() {
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String execute() throws Exception {
		
		if(StringUtils.isNotBlank(bisProjectId)) {
			bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
		} else {
			BisProject bisProject = bisProjectManager.getCurrProject();
			if(bisProject != null) {
				bisProjectId = bisProject.getBisProjectId();
				bisProjectName = bisProject.getProjectName();
			}
		}
		
		return SUCCESS;
	}

	@Override
	public String list() throws Exception {
		
		page.setPageSize(10);
		Map<String, Object> param = new HashMap<String, Object>();
		
		StringBuffer hql = new StringBuffer("from BisProjectLayout bpl where 1=1");
		if(StringUtils.isNotBlank(bisProjectId)) {
			hql.append(" and bpl.bisProject.bisProjectId=:bisProjectId");
			param.put("bisProjectId", bisProjectId);
		}
		hql.append(" order by bpl.imgDate desc, bpl.updatedDate desc");
		
		page = bisProjectLayoutManager.findPage(page, hql.toString(), param);
		
		return "list";
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}

	@Override
	public void prepareSave() throws Exception {
		
		if(StringUtils.isNotBlank(getId())) {
			entity = bisProjectLayoutManager.getEntity(getId());
		} else {
			entity = new BisProjectLayout();
		}
		
		if(StringUtils.isNotBlank(bisProjectId)) {
			bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
		}
		
		entity.setActiveBl(true);
	}
	
	@Override
	public String save() throws Exception {
		
		entity.setBisProject(bisProjectManager.getEntity(bisProjectId));
		bisProjectLayoutManager.saveBisProjectLayout(entity);
		//更新附件
		if (StringUtils.isNotEmpty(entityTmpId)) {
			appAttachFileManager.updateTmpFile(entityTmpId, BisProjectLayout.class.getSimpleName(), entity.getBisProjectLayoutId());
		}
		return null;
	}

	@Override
	public String delete() throws Exception {

		bisProjectLayoutManager.deleteBisProjectLayout(getId());
		//删除附件
		appAttachFileManager.deleteByBizEntityId(getId(), false);
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		
		if(StringUtils.isNotBlank(getId())) {
			entity = bisProjectLayoutManager.getEntity(getId());
			bisProjectId = entity.getBisProject().getBisProjectId();
			bisProjectName = entity.getBisProject().getProjectName();
		} else {
			entity = new BisProjectLayout();
			if(StringUtils.isBlank(entityTmpId)) {
				entityTmpId = "bisProjectLayout_"+RandomUtils.generateTmpEntityId();
			}
		}
		
		if(StringUtils.isNotBlank(bisProjectId)) {
			bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
		}
	}

	public BisProjectLayout getEntity() {
		return entity;
	}

	public void setEntity(BisProjectLayout entity) {
		this.entity = entity;
	}

	public String getEntityTmpId() {
		return entityTmpId;
	}

	public void setEntityTmpId(String entityTmpId) {
		this.entityTmpId = entityTmpId;
	}

	public String getBisProjectId() {
		return bisProjectId;
	}

	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}

	public String getBisProjectName() {
		return bisProjectName;
	}

	public void setBisProjectName(String bisProjectName) {
		this.bisProjectName = bisProjectName;
	}

}
