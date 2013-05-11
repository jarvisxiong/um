package com.hhz.ump.web.bis;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.util.DictContants;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 商业费用报表
 * 
 * @author jiaoxf
 */
@Namespace("/bis")
public class BisProjectSelectAction extends ActionSupport {

	private static final long serialVersionUID = 2806347865620189538L;
	private boolean multi=false;
	public boolean isMulti() {
		return multi;
	}
	public void setMulti(boolean multi) {
		this.multi = multi;
	}
	@Autowired
	private BisProjectManager bisProjectManager;
	
	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}
	public List<BisProject> getProjectSouth(){
		return bisProjectManager.getMapBisProjectByArea(DictContants.BIS_ORG_SOUTH);
	}
	public List<BisProject> getProjectNorth(){
		return bisProjectManager.getMapBisProjectByArea(DictContants.BIS_ORG_NORTH);
	}
	public List<BisProject> getProjectSh(){
		return bisProjectManager.getMapBisProjectByArea(DictContants.BIS_ORG_SH);
	}
	public List<BisProject> getProjectGroup(){
		return bisProjectManager.getMapBisProjectByArea(DictContants.BIS_ORG_GROUP);
	}
	public String test(){
		return "test";
	}
	public String tmp(){
		return "tmp";
	}
}
