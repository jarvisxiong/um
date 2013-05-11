/**
 * 
 */
package com.hhz.ump.web.bis;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bis.BisFloorManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.entity.bis.BisFloor;
import com.hhz.ump.entity.bis.BisProject;

/**
 * @author baolm 2011-6-13
 */
@Namespace("/bis")
public class BisFloorAction extends CrudActionSupport<BisFloor> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2562519621355551474L;

	private BisFloor entity;
	
	private String bisProjectId; //项目
	private String bisProjectName;
	private String filter_floorType; //楼宇类型
	
	
	@Autowired
	private BisFloorManager bisFloorManager;
	
	@Autowired
	private BisProjectManager bisProjectManager;
	
	@Override
	public BisFloor getModel() {
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String execute() throws Exception {
		
		initBisProject();
		return SUCCESS;
	}

	@Override
	public String list() throws Exception {
		
		page.setPageSize(20);
		
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from BisFloor where 1=1");
		if(StringUtils.isNotBlank(bisProjectId)) {
			hql.append(" and bisProject.bisProjectId=:bisProjectId");
			param.put("bisProjectId", bisProjectId);
		}
		if(StringUtils.isNotBlank(filter_floorType)) {
			hql.append(" and floorType=:floorType");
			param.put("floorType", filter_floorType);
		}
		hql.append(" order by sequenceNo,bisFloorId");
		
		page = bisFloorManager.findPage(page, hql.toString(), param);
		
		return "list";
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}

	@Override
	public String save() throws Exception {
		
		BisProject bisProject = bisProjectManager.getEntity(bisProjectId);
		entity.setBisProject(bisProject);
		bisFloorManager.saveBisFloor(entity);
		return null;
	}
	
	@Override
	public String delete() throws Exception {
		
		entity = bisFloorManager.getEntity(getId());
		if(!entity.getBisStores().isEmpty() || !entity.getBisFlats().isEmpty()) {
			
			Struts2Utils.renderText("请先删除相关商铺");
			return null;
		}
		
		bisFloorManager.delete(entity);
		Struts2Utils.renderText("success");
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		
		if(StringUtils.isNotBlank(getId())) {
			
			entity = bisFloorManager.getEntity(getId());
			bisProjectId = entity.getBisProject().getBisProjectId();
			bisProjectName = entity.getBisProject().getProjectName();
		} else {
			
			entity = new BisFloor();
			initBisProject();
		}
	}

	private void initBisProject() {
		if(StringUtils.isBlank(bisProjectId)) {
			BisProject bisProject = bisProjectManager.getCurrProject();
			if(bisProject != null) {
				bisProjectId = bisProject.getBisProjectId();
				bisProjectName = bisProject.getProjectName();
			}
		} else {
			bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
		}
	}

	public BisFloor getEntity() {
		return entity;
	}

	public void setEntity(BisFloor entity) {
		this.entity = entity;
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

	public String getFilter_floorType() {
		return filter_floorType;
	}

	public void setFilter_floorType(String filter_floorType) {
		this.filter_floorType = filter_floorType;
	}

}
