package com.hhz.ump.web.oa;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.oa.OaEmailBodyManager;
import com.hhz.ump.dao.oa.OaEmailManager;
import com.hhz.ump.entity.oa.OaEmailBody;

@Namespace("/oa")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "oa-email-body!list.action", type = "redirect") })
public class OaEmailBodyAction extends CrudActionSupport<OaEmailBody> {

	private static final long serialVersionUID = -4075294463166517739L;

	@Autowired
	private OaEmailBodyManager oaEmailBodyManager;
	@Autowired
	private OaEmailManager oaEmailManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;

	private OaEmailBody entity;


	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 已经发送的邮件
		PropertyFilter newFilter = new PropertyFilter("EQS_sendFlg", "1");
		filters.add(newFilter);

		//page.setOrderBy("sendTime");
		//page.setOrder(page.DESC);
		page = oaEmailBodyManager.findPage(page, filters);
		return "list";
	}

	/**
	 * 替换用户的邮件, 旧用户的邮件会被转移到新的用户
	 * 
	 * @param oldUiid
	 *            旧的用户id
	 * @param newUiid
	 *            新的用户id <br/>
	 * @return
	 * @throws Exception
	 */
	public String resetUser() throws Exception {
		String oldUiid = Struts2Utils.getParameter("oldUiid");
		String newUiid = Struts2Utils.getParameter("newUiid");
		oaEmailManager.updateUser4Email(oldUiid, newUiid);
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String delete() throws Exception {
		oaEmailBodyManager.deleteOaEmailBody(getId());
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = oaEmailBodyManager.getEntity(getId());
		} else {
			entity = new OaEmailBody();
		}

	}

	public OaEmailBody getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
