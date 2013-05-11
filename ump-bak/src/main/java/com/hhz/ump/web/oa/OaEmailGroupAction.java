package com.hhz.ump.web.oa;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.oa.OaEmailGroupManager;
import com.hhz.ump.entity.oa.OaEmailGroup;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.Util;

@Namespace("/oa")
public class OaEmailGroupAction extends CrudActionSupport<OaEmailGroup> {
	private static final long serialVersionUID = -3821190853066206779L;

	private OaEmailGroup entity;
	private List<OaEmailGroup> oaEmailGroups;

	@Autowired
	private OaEmailGroupManager oaEmailGroupManager;

	@Override
	public String delete() throws Exception {
		oaEmailGroupManager.deleteOaEmailGroup(getId());
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter filter = new PropertyFilter("EQS_creator", SpringSecurityUtils.getCurrentUiid());
		filters.add(filter);
		page.setPageSize(100);
		page.setOrderBy("dispOrderNo");
		page.setOrder(Page.ASC);
		page = oaEmailGroupManager.findPage(page, filters);
		oaEmailGroups = page.getResult();
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = oaEmailGroupManager.getEntity(getId());
		} else {
			entity = new OaEmailGroup();
		}
	}

	@Override
	public String save() throws Exception {
		oaEmailGroupManager.saveOaEmailGroup(entity);
		return RELOAD;
	}

	public String load() throws Exception {
		String[] names = Util.clob2String(entity.getGroupMemberNames()).split(";");
		String[] ids = Util.clob2String(entity.getGroupMemberIds()).split(";");
		StringBuffer jsonStr = new StringBuffer();
		jsonStr.append("[");
		
		String uiid = null;
		String statusCd = null;
		for (int i = 0; i < names.length; i++) {
			uiid = ids[i];
			statusCd = PlasCache.getAcctByUiid(uiid).getStatusCd();
			if(DictContants.UAAP_USER_STATUS_CREATE.equalsIgnoreCase(statusCd)
					|| DictContants.UAAP_USER_STATUS_UNFREEZE.equalsIgnoreCase(statusCd)){
				if (i != 0) {
					jsonStr.append(",");
				}
				jsonStr.append("{");
				jsonStr.append("'userName':");
				jsonStr.append("'" + names[i] + "'");
				jsonStr.append(",");
				jsonStr.append("'uiid':");
				jsonStr.append("'" + ids[i] + "'");
				jsonStr.append("}");
			}
		}
		jsonStr.append("]");
		Struts2Utils.renderText(jsonStr.toString());
		return null;
	}

	@Override
	public OaEmailGroup getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public void prepareLoad() throws Exception {
		prepareModel();
	}

	public List<OaEmailGroup> getOaEmailGroups() {
		return oaEmailGroups;
	}

	public void setOaEmailGroups(List<OaEmailGroup> oaEmailGroups) {
		this.oaEmailGroups = oaEmailGroups;
	}

}
