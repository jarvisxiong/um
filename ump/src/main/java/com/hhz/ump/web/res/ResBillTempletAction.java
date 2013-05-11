/**
 * 
 */
package com.hhz.ump.web.res;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.hhz.ump.dao.res.ResBillTempletManager;
import com.hhz.ump.entity.res.ResBillTemplet;

/**
 * 审批单模板
 * 
 * @author huangj 2010-5-31
 */
@Namespace("/res")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "res-bill-templet.action", type = "redirect") })
public class ResBillTempletAction extends CrudActionSupport<ResBillTemplet> {

	private static final long serialVersionUID = 4095696798263657460L;

	private ResBillTemplet entity;

	@Autowired
	private ResBillTempletManager resBillTempletManager;

	@Override
	public String delete() throws Exception {
		resBillTempletManager.deleteResBillTemplet(getId());
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
		return INPUT;
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
		page = resBillTempletManager.findPage(page, filters);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = resBillTempletManager.getEntity(getId());
			// parserXml(placeBill,
			// Util.clob2String(entity.getTempletContent()));
		} else {
			entity = new ResBillTemplet();
		}

	}

	@Override
	public String save() throws Exception {
		// entity.setTempletContent(new ClobImpl(initXml()));
		resBillTempletManager.saveResBillTemplet(entity);
		return RELOAD;
	}

	public String checkCd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newCd = request.getParameter("templetCd");
		String oldCd = request.getParameter("oldTempletCd");
		if (resBillTempletManager.isPropertyUnique("templetCd", newCd, oldCd)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		return null;
	}

	public ResBillTemplet getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public String quickSearch() throws Exception {
		String templetName = Struts2Utils.getParameter("value");
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("LIKES_templetCd_OR_templetName", templetName));

		page.setPageSize(10);
		page = resBillTempletManager.findPage(page, filters);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (ResBillTemplet billTemplet : page.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("templetCd", billTemplet.getTempletCd());
			map.put("templetName", billTemplet.getTempletName());
			list.add(map);
		}
		Struts2Utils.renderJson(list);
		return null;
	}

}
