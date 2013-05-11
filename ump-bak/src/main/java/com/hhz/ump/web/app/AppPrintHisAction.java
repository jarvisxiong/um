/**
 * 
 */
package com.hhz.ump.web.app;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppPrintHisManager;
import com.hhz.ump.entity.app.AppPrintHis;
import com.hhz.ump.util.CodeNameUtil;

/**
 * <p>
 * 打印历史
 * </p>
 * 
 * @author huangjian
 * @create 2012-2-14
 */
@Namespace("/app")
public class AppPrintHisAction extends CrudActionSupport<AppPrintHis> {
	private static final long serialVersionUID = -2227034338234195521L;
	private AppPrintHis entity;
	@Autowired
	private AppPrintHisManager appPrintHisManager;

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
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
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrder(Page.ASC);
		}
		page.setOrderBy("pagePath");
		page.setOrder(Page.ASC);

		page = appPrintHisManager.findPage(page, filters);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getId())) {
			entity = appPrintHisManager.getEntity(getId());
		} else {
			entity = new AppPrintHis();
			entity.setStatusCd("1");
		}
	}

	/**
	 * 记录打印历史
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public String save() throws Exception {
		appPrintHisManager.saveAppPrintHis(entity);
		StringBuffer msg = new StringBuffer();
		msg.append("打印人：").append(CodeNameUtil.getUserNameByCd(entity.getCreator())).append("，").append(
				DateOperator.formatDate(entity.getCreatedDate(),true)).append("，版本号：").append(entity.getPrintCount());
		Struts2Utils.renderText(msg.toString());
		return null;
	}

	@Override
	public AppPrintHis getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

}
