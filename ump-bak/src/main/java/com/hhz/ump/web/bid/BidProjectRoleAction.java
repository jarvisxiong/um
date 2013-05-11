/**  
 * BidProjectRoleAction.java  
 * com.hhz.ump.web.bid  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-12-26        zhongyubing  
 *  
 * Copyright (c) 2011, PD All Rights Reserved.  
*/  
  
package com.hhz.ump.web.bid;  

import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bid.BidProjectRoleManager;
import com.hhz.ump.entity.bid.BidProjectRole;

/**  
 * ClassName:BidProjectRoleAction  
 * Function: TODO ADD FUNCTION  
 * Reason:   TODO ADD REASON  
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-12-26        上午10:09:31  
  
 */
public class BidProjectRoleAction extends CrudActionSupport<BidProjectRole>{

	
	private static final long serialVersionUID = -8665326814990499799L;
	@Autowired
	BidProjectRoleManager bidProjectRoleManager;
	private List <BidProjectRoleVo> bovos;
	private BidProjectRole entity;
	
	//搜索条件
	private String searchProjectName;
	private String searchRemark;
	private String curUserName;
	private String authUiid;
	
	
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
	public String list() {	
		//根据地区搜索所有权限
		bovos=bidProjectRoleManager.getProjectList(authUiid,searchRemark); 
		return "list";  
		
	}

	@Override
	protected void prepareModel() throws Exception {
	}
	
	

	public List<BidProjectRoleVo> getBovos() {
		return bovos;
	}

	public void setBovos(List<BidProjectRoleVo> bovos) {
		this.bovos = bovos;
	}

	@Override
	public void prepareSave() {
		String projectCd = Struts2Utils.getParameter("projectCd");
		String userCds = Struts2Utils.getParameter("userCds");
		String remark = Struts2Utils.getParameter("remark");
		// 如果项目不为空
		if (StringUtils.isNotBlank(projectCd)) {
			// 首先查看是否已经存在这个项目
			List<BidProjectRole> l = bidProjectRoleManager.getProjectUserByProjectCd(BidProjectRoleManager.ACTIVE_YES,
					projectCd);
			if (l != null && !l.isEmpty()) {
				BidProjectRole bidProjectRole = l.get(0);
				entity = bidProjectRole;
				entity.setUserCd(userCds);
				entity.setRemark(remark);//备注(标签或关键字)
			} else {
				entity = new BidProjectRole();
				entity.setActive(BidProjectRoleManager.ACTIVE_YES);
				entity.setProjectCd(projectCd);
				entity.setUserCd(userCds);
				entity.setRemark(remark);//备注(标签或关键字)
			}
		}

	}
	@Override
	public String save() throws Exception {
		if(entity!=null) {
			bidProjectRoleManager.saveBidProjectRole(entity);
			Struts2Utils.renderText("success,保存成功！");
		}else{
			Struts2Utils.renderText("false,保存失败！");
		}
		return null;  
		
	}

	@Override
	public BidProjectRole getModel() {
		  
		// TODO Auto-generated method stub  
		return null;  
		
	}

	public BidProjectRole getEntity() {
		return entity;
	}

	public void setEntity(BidProjectRole entity) {
		this.entity = entity;
	}
	
	
	/**
	 * 进入授权项目页面
	 * @return
	 */
	public String main(){
		list();
		return "main";
	}

	public String getSearchProjectName() {
		return searchProjectName;
	}

	public void setSearchProjectName(String searchProjectName) {
		this.searchProjectName = searchProjectName;
	}

	public String getSearchRemark() {
		return searchRemark;
	}

	public void setSearchRemark(String searchRemark) {
		this.searchRemark = searchRemark;
	}

	public String getCurUserName() {
		return curUserName;
	}

	public void setCurUserName(String curUserName) {
		this.curUserName = curUserName;
	}

	public String getAuthUiid() {
		return authUiid;
	}

	public void setAuthUiid(String authUiid) {
		this.authUiid = authUiid;
	}
	
}
  
