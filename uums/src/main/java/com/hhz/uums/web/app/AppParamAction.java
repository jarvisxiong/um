package com.hhz.uums.web.app;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.app.AppParamManager;
import com.hhz.uums.entity.app.AppParam;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.web.CrudActionSupport;

/**
 * @author shixy
 * 2011-1-25
 */
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-param.action", type = "redirect") })
public class AppParamAction extends CrudActionSupport<AppParam> {

	private static final long serialVersionUID = -2788990774165099783L;

	@Autowired
	private AppParamManager appParamManager;

	private AppParam entity;
	
 
	@Override
	public String delete() throws Exception {
		appParamManager.deleteAppParam(getId());
		JsonUtil.renderSuccess("删除成功");
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		appParamManager.deleteBatch(getIds());
		JsonUtil.renderSuccess("删除成功");
		return null;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("sequenceNo");
			page.setOrder(Page.ASC);
		} else {
			page.setOrderBy(sortField + ",sequenceNo");
			page.setOrder(order + "," + Page.ASC);
		}
		page = appParamManager.findPage(page, filters);
		
		JsonUtil.renderJson(page);
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = appParamManager.getEntity(getId());
		} else {
			entity = new AppParam();
		}
	}

	@Override
	public String save() throws Exception {
		appParamManager.saveAppParam(entity);
		JsonUtil.renderSuccess("保存成功");
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String saveBatch(){
		List<AppParam> insertedRecords = (List<AppParam>) JsonUtil.getInsertRecords(AppParam.class);
		List<AppParam> updatedRecords =  (List<AppParam>) JsonUtil.getUpdatedRecords(AppParam.class);
		List<AppParam> deletedRecords =  (List<AppParam>) JsonUtil.getDeletedRecords(AppParam.class);
		appParamManager.saveOrUpdateAppParams(insertedRecords, updatedRecords, deletedRecords);
		JsonUtil.renderSuccess("保存成功");
		return null;
	}

	public AppParam getModel() {
		return entity;
	}


}
