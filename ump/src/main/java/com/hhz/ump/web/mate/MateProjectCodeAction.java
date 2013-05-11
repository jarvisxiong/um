package com.hhz.ump.web.mate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cont.ContProjectCodeManager;
import com.hhz.ump.entity.cont.ContProjectCode;
import com.hhz.ump.util.CodeNameUtil;

public class MateProjectCodeAction extends CrudActionSupport<ContProjectCode> {
	private static final long serialVersionUID = 8071052318603147332L;
	@Autowired
	private ContProjectCodeManager contProjectCodeManager;
	private ContProjectCode entity;
	
	private String userCds;
	private String projectName;
	
	/*private Map<String, String> mapToProjectCd = new HashMap<String, String>();
	private List<WsPlasOrg> orgEstateOrgList;*/
	@Override
	public ContProjectCode getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		List<ContProjectCode> projectList =contProjectCodeManager.getAll();
		/*orgEstateOrgList = WorkPlanUtil.getOrgEstateOrgList();
		for(WsPlasOrg wsUaapOrg : orgEstateOrgList){
			mapToProjectCd.put(wsUaapOrg.getOrgCd(), wsUaapOrg.getOrgName());
		}*/
		page.setPageSize(-1);
		page.setResult(projectList);
		return "list";
	}
	/**
	 * 快速搜索合同库项目列表
	 * @return
	 * @throws Exception
	 */
	public void quickSearch() throws Exception {
		projectName=Struts2Utils.getParameter("value");
		String codeType = Struts2Utils.getParameter("codeType");
		if(StringUtils.isBlank(codeType)){
			codeType="1";
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		//搜索合同项目
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_codeType", codeType));
		//模糊搜索工程名称
		if(StringUtils.isNotBlank(projectName)) {
			filters.add(new PropertyFilter("LIKES_projectName",projectName));
		}
		page.setPageSize(100);
		page = contProjectCodeManager .findPage(page, filters);
		for(ContProjectCode code:page.getResult()){
			Map<String, String> map = new HashMap<String, String>();
			map.put("projectCd", code.getProjectCd());// 项目CD
			map.put("projectName", CodeNameUtil.getDeptNameByCd(code.getProjectCd()));
			list.add(map);
		}
		Struts2Utils.renderJson(list);
		
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return "input";
	}
	
	@Override
	public void prepareSave() {
		if (StringUtils.isNotBlank(getId())) {
			entity = contProjectCodeManager.getEntity(getId());
			entity.setRecordVersion(entity.getRecordVersion() + 1);
		} else {
			entity = new ContProjectCode();
		}
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getId())) {
			entity.setMateAuthority(userCds);
			contProjectCodeManager.saveContProjectCode(entity);
			Struts2Utils.renderText("ok");
		}else{
			contProjectCodeManager.saveContProjectCode(entity);
			Struts2Utils.renderText("ok");
		}
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String getUserCds() {
		return userCds;
	}

	public void setUserCds(String userCds) {
		this.userCds = userCds;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	} 

}
