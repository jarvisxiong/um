/**
 * 
 */
package com.hhz.ump.web.plan;

import java.util.Date;
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

import com.hhz.core.service.ServiceException;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.plan.PlanPeriodManager;
import com.hhz.ump.entity.plan.PlanPeriod;

/**
 * @author huangj 2010-3-31
 */
@Namespace("/plan")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "planPeriod!list.action", type = "redirect"),
		@Result(name = CrudActionSupport.RELOAD_SUB, location = "planPeriod!list.action", type = "redirect") })
public class PlanPeriodAction extends CrudActionSupport<PlanPeriod> {
	private static final long serialVersionUID = -4351025260577645427L;

	private PlanPeriod entity;

	@Autowired
	private PlanPeriodManager planPeriodManager;

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = planPeriodManager.getEntity(getId());
		} else {
			entity = new PlanPeriod();
		}
	}
	public PlanPeriod getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String delete() throws Exception {
		planPeriodManager.deletePlanPeriod(getId());
		addActionMessage(getText("common.success"));
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		try {
			planPeriodManager.deleteBatch(getIds());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		if (getId() == null) {
			Date nowdate = new Date();
			int nowyear = Integer.parseInt(DateOperator.formatDate(nowdate,"yyyy").toString());
			Struts2Utils.getRequest().setAttribute("periodSerialNumber", Integer.toString(planPeriodManager.getMaxPeriodSerialNumber("0")));
			Struts2Utils.getRequest().setAttribute("planYear", nowyear);
			Struts2Utils.getRequest().setAttribute("yearNumber", Integer.toString(planPeriodManager.getMaxYearNumber("0",nowyear)));
		}
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		page.setPageSize(50);
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("periodSerialNumber");
			page.setOrder(Page.DESC);
		}
		page = planPeriodManager.findPage(page, filters);
		return SUCCESS;
	}

	@Override
	public void prepareSave() {
		if (StringUtils.isNotBlank(getId())) {
			entity = planPeriodManager.getEntity(getId());
		} else {
			entity = new PlanPeriod();
		}
	}
	
	@Override
	public String save() throws Exception {
		planPeriodManager.savePlanPeriod(entity);
		return list();
	}
	
}
