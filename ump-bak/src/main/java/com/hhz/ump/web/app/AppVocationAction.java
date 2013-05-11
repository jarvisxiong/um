/**
 * 
 */
package com.hhz.ump.web.app;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppVocationManager;
import com.hhz.ump.entity.app.AppVocation;
import com.hhz.ump.util.JsonUtil2;

/**
 * 假期管理ACTION
 * 
 * @author qlb 1/5/2012
 * 
 */
@Namespace("/app")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-vocation.action", type = "redirect") })
public class AppVocationAction extends CrudActionSupport<AppVocation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -302446378582981751L;

	private Page<AppVocation> page = new Page<AppVocation>(15);// 每页15条记录
	@Autowired
	private AppVocationManager appVocationManager;

	private AppVocation entity;
	// 搜索条件
	private String filter_EQS_ifOnDuty;
	private Long filter_EQL_inYear;

	/**
	 * @return the filter_EQS_ifOnDuty
	 */
	public String getFilter_EQS_ifOnDuty() {
		return filter_EQS_ifOnDuty;
	}

	/**
	 * @param filterEQSIfOnDuty
	 *            the filter_EQS_ifOnDuty to set
	 */
	public void setFilter_EQS_ifOnDuty(String filterEQSIfOnDuty) {
		filter_EQS_ifOnDuty = filterEQSIfOnDuty;
	}

	@Override
	public String delete() throws Exception {
		appVocationManager.deleteAppVocation(getId());

		JsonUtil2.renderSuccess("删除成功");
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		appVocationManager.deleteBatch(getIds());
		JsonUtil2.renderSuccess("删除成功");
		return null;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public Page<AppVocation> getPage() {
		return page;
	}

	@Override
	public String execute() throws Exception {
		// 默认执行的方法
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String saveBatch() {

		try {
			List<AppVocation> insertedRecords = (List<AppVocation>) JsonUtil2.getInsertRecords(AppVocation.class);
			List<AppVocation> updatedRecords = (List<AppVocation>) JsonUtil2.getUpdatedRecords(AppVocation.class);
			List<AppVocation> deletedRecords = (List<AppVocation>) JsonUtil2.getDeletedRecords(AppVocation.class);
			appVocationManager.saveOrUpdateAppVocations(insertedRecords, updatedRecords, deletedRecords);
			JsonUtil2.renderSuccess("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtil2.renderFailure("保存失败");
		}
		return null;
	}

	@Override
	public String list() throws Exception {
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");

		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		for (int i = 0; i < filters.size(); i++) {

			PropertyFilter pFilter = filters.get(i);
			String propertyName = pFilter.getPropertyName();
			if (propertyName.equals("inYear")) {
				String isYearVal = String.valueOf(pFilter.getPropertyValue());
				if (StringUtils.isNotBlank(isYearVal) && !isYearVal.equals("null")) {
					PropertyFilter newFilter = new PropertyFilter("EQL_inYear", Long.valueOf(isYearVal));
					filters.remove(i);
					filters.add(newFilter);
				}

			}
		}

		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {

			page.setOrderBy("vocationDay");
			page.setOrder(Page.DESC);
		} else {
			page.setOrderBy(sortField + ",vocationDay");
			page.setOrder(order + "," + Page.ASC);
		}
		try {

			page = appVocationManager.findPage(page, filters);
			JsonUtil2.renderJson(page);
		} catch (Exception ee) {
		}
		return null;
	}

	/**
	 * @return the filter_EQL_inYear
	 */
	public Long getFilter_EQL_inYear() {
		return filter_EQL_inYear;
	}

	/**
	 * @param filterEQLInYear
	 *            the filter_EQL_inYear to set
	 */
	public void setFilter_EQL_inYear(Long filterEQLInYear) {
		filter_EQL_inYear = filterEQLInYear;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = appVocationManager.getEntity(getId());
		} else {
			entity = new AppVocation();
		}

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppVocation getModel() {
		return entity;
	}

}
