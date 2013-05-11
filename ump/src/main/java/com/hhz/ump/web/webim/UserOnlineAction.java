package com.hhz.ump.web.webim;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.webim.UserOnlineManager;
import com.hhz.ump.entity.webim.UserMessage;

@Namespace("/webim")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "user-online.action", type = "redirect") })
public class UserOnlineAction extends CrudActionSupport<UserMessage> {

	private static final long serialVersionUID = -3445152342227169047L;

	@Autowired
	private UserOnlineManager userOnlineManager;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public UserMessage getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public String resetWebimOnline() {
		try {
			userOnlineManager.deleteAllData();
			Struts2Utils.renderText("success");
		} catch (Exception e) {
			Struts2Utils.renderText("error");
		}
		return null;
	}
}
