/**
 * 
 */
package com.hhz.ump.web.res;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.res.ResAccreditInfoManager;
import com.hhz.ump.entity.res.ResAccreditInfo;

/**
 * 类名 ResAccreditAction 创建者 黄健 创建日期 2010-6-17 描述 权责审批授权管理
 */
@Namespace("/res")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "res-accredit.action", type = "redirect"),
	@Result(name = "loadByUser", location = "res-accredit!loadByUser.action", type = "redirect")})
public class ResAccreditAction extends CrudActionSupport<ResAccreditInfo> {

	private static final long serialVersionUID = 273914668785223296L;

	@Autowired
	private ResAccreditInfoManager resAccreditInfoManager;

	private ResAccreditInfo entity;

	@Override
	public String delete() throws Exception {
		resAccreditInfoManager.deleteResAccreditInfo(getId());
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		return "input";
	}

	@Override
	public String list() throws Exception {
		page.setPageSize(20);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		page.setOrderBy("createdDate");
		page.setOrder(Page.DESC);
		page = resAccreditInfoManager.findPage(page, filters);

		return SUCCESS;
	}
	
	
	public String loadByUser() throws Exception {
		page.setPageSize(20);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_userCd",SpringSecurityUtils.getCurrentUiid()));
		page.setOrderBy("createdDate");
		page.setOrder(Page.DESC);
		page = resAccreditInfoManager.findPage(page, filters);
		return "assistantList";
	}

	@Override
	public String save() throws Exception {
		resAccreditInfoManager.saveResAccreditInfo(entity);
		return RELOAD;
	}
	
	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isBlank(getId())) {
			entity = new ResAccreditInfo();
			entity.setActiveFlg("1");
		} else {
			entity = resAccreditInfoManager.getEntity(getId());
		}
	}

	public ResAccreditInfo getModel() {
		if(StringUtils.isBlank(getId())){
			entity = new ResAccreditInfo();
		}else{
			entity = resAccreditInfoManager.getEntity(getId());
		}
		return entity;
	}
	
	public void prepareSaveAssistant(){
		if(StringUtils.isBlank(getId())){
			entity = new ResAccreditInfo();
			entity.setActiveFlg("1");
		}else{
			entity = resAccreditInfoManager.getEntity(getId());
		}
	}

	// 新增/更新助理信息 -- 入口为审批流程
	public String saveAssistant() {
		String accUserCd = this.entity.getAccUserCd();
		String curUserCd = SpringSecurityUtils.getCurrentUiid();
		ResAccreditInfo resAccInfo = resAccreditInfoManager.queryAccreditedBy(curUserCd,accUserCd);
		if(getId()==null){
			if(resAccInfo!=null){
				resAccInfo.setUpdator(curUserCd);
				resAccInfo.setUpdatedDate(new Date());
				resAccInfo.setUpdatedDeptCd(SpringSecurityUtils.getCurrentDeptCd());
				resAccInfo.setIsFile(this.entity.getIsFile());
				resAccInfo.setIsJbpm(this.entity.getIsJbpm());
				resAccInfo.setIsRes(this.entity.getIsRes());
				resAccreditInfoManager.saveResAccreditInfo(resAccInfo);
			}else{
				this.entity.setUserCd(curUserCd);
				this.entity.setUserName(SpringSecurityUtils.getCurrentUserName());
				this.entity.setUpdator(curUserCd);
				this.entity.setUpdatedDate(new Date());
				this.entity.setUpdatedDeptCd(SpringSecurityUtils.getCurrentDeptCd());
				if (null == this.entity.getResAccreditInfoId()) {
					this.entity.setCreator(curUserCd);
					this.entity.setCreatedDate(new Date());
					this.entity.setCreatedDeptCd(SpringSecurityUtils.getCurrentDeptCd());
				}
				resAccreditInfoManager.saveResAccreditInfo(this.entity);
			}
		}else{
			resAccreditInfoManager.saveResAccreditInfo(this.entity);
		}
		
		return "loadByUser";
	}

	// 删除助理信息
	public String removeAssistant() {
		resAccreditInfoManager.deleteResAccreditInfo(this.entity.getResAccreditInfoId());
		return RELOAD;
	}

	public ResAccreditInfoManager getResAccreditInfoManager() {
		return resAccreditInfoManager;
	}

	public void setResAccreditInfoManager(ResAccreditInfoManager resAccreditInfoManager) {
		this.resAccreditInfoManager = resAccreditInfoManager;
	}

	public ResAccreditInfo getEntity() {
		return entity;
	}

	public void setEntity(ResAccreditInfo entity) {
		this.entity = entity;
	}
	
	

}
