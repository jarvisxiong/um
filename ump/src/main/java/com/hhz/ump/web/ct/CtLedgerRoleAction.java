package com.hhz.ump.web.ct;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bid.BidProjectRoleManager;
import com.hhz.ump.dao.ct.CtLedgerRoleManager;
import com.hhz.ump.entity.ct.CtLedgerRole;

public class CtLedgerRoleAction extends CrudActionSupport<CtLedgerRole> {

	private CtLedgerRole entity;
	private List <CtLedgerRoleVo> bovos;
	
	@Autowired
	private CtLedgerRoleManager ctLedgerRoleManager;
	@Override
	public CtLedgerRole getModel() {
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
		//根据地区搜索所有权限
		bovos=ctLedgerRoleManager.getProjectList(); 
		return "list";  
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		if(entity!=null) {
			ctLedgerRoleManager.saveCtLedgerRole(entity);
			Struts2Utils.renderText("success,保存成功！");
		}else{
			Struts2Utils.renderText("false,保存失败！");
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
		if (StringUtils.isNotBlank(getId())) {
			entity = ctLedgerRoleManager.getEntity(getId());
		} else {

			entity = new CtLedgerRole();
		}
	}
	@Override
	public void  prepareSave(){
		String projectCd=Struts2Utils.getParameter("projectCd");
		String userCds=Struts2Utils.getParameter("userCds");
		//如果项目不为空
		if(StringUtils.isNotBlank(projectCd)){ 
			//首先查看是否已经存在这个项目
			List<CtLedgerRole> l=ctLedgerRoleManager.getProjectUserByProjectCd(ctLedgerRoleManager.ACTIVE_YES, projectCd);
			if(l!=null&&!l.isEmpty()){
				CtLedgerRole ctLedgerRole=l.get(0);
				entity=ctLedgerRole;
				entity.setUserCd(userCds);
			}else{
				entity=new CtLedgerRole();
				entity.setActive(BidProjectRoleManager.ACTIVE_YES);
				entity.setProjectCd(projectCd);
				entity.setUserCd(userCds);
			}
		}
		
	}

	public List<CtLedgerRoleVo> getBovos() {
		return bovos;
	}

	public void setBovos(List<CtLedgerRoleVo> bovos) {
		this.bovos = bovos;
	}
}
