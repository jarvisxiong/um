 package com.hhz.uums.web.plas;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.app.AppSeqManager;
import com.hhz.uums.dao.plas.PlasAppManager;
import com.hhz.uums.dao.plas.PlasOperateLogManager;
import com.hhz.uums.entity.plas.PlasApp;
import com.hhz.uums.utils.GlobalConstants;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.utils.OperConst;
import com.hhz.uums.web.CrudActionSupport;

/**
 *-------------------------------------------------------
 * date    			||  author      ||  Description    ||
 * 2011-1-26		|| jiaoxiaofeng ||  create         ||PlasAppAction.java
 * Description:对注册接入的第三方软件的增删改业务信息
 * -------------------------------------------------------
 **/
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "plas-app.action", type = "redirect") })
public class PlasAppAction extends CrudActionSupport<PlasApp> {

	
	private static final long serialVersionUID = -803347029545416529L;
	@Autowired
	private PlasAppManager plasAppmManager;
	@Autowired
	private PlasOperateLogManager logManager;
	private PlasApp entity;
	public void prepareDelete() throws Exception{
		prepareModel();
	}
	@Override
	public String delete() throws Exception {
		plasAppmManager.deletePlasApp(getId());
		
		// 保存操作日志
		String operUiid = SpringSecurityUtils.getLoginCode();
		String operUserName = SpringSecurityUtils.getCurUserName();
		
			logManager.savePlasOperateLog(operUiid, operUserName, OperConst.REALPOS, OperConst.DEL,
					new StringBuffer("[").append(entity.getAppCd()).append(",").append(entity.getAppChnName()).append(
							"]删除应用信息成功!").toString());
		JsonUtil.renderSuccess("删除成功");
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		plasAppmManager.deleteBatch(getIds());
		JsonUtil.renderSuccess("删除成功");
		return null;
	}

	

	@Override
	public String list() throws Exception {
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("sequenceNo");
			page.setOrder(Page.ASC);
		} else {
			page.setOrderBy(sortField + ",sequenceNo");
			page.setOrder(order + "," + Page.ASC);
		}
		
		page = plasAppmManager.findPage(page, filters);
		//将plas_app的子属性plasRoles置为空。由于hibernate设置为延迟加载，故为空。
		JsonUtil.renderJson(page,new String[]{"plasRoles"});
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = plasAppmManager.getEntity(getId());
		} else {
			entity = new PlasApp();
		}
	}
	@Autowired
	private  AppSeqManager appSeqManager;
	@Override
	public void prepareSave() throws Exception{
		prepareModel();
	}
	@Override
	public String save() throws Exception {
		String appCd = entity.getAppCd();
		if (StringUtils.isBlank(appCd)) {
			appCd = appSeqManager.createNextValue(GlobalConstants.SEQ_ROLE_GROUP_CD).toString();
			entity.setAppCd(appCd);
		}
			 
		plasAppmManager.savePlasApp(entity);
		Struts2Utils.renderHtml("success");
		
		// 保存操作日志
		String operUiid = SpringSecurityUtils.getLoginCode();
		String operUserName = SpringSecurityUtils.getCurUserName();
	
		logManager.savePlasOperateLog(operUiid, operUserName, OperConst.REALPOS, OperConst.ADD,
				new StringBuffer("[").append(entity.getAppCd()).append(",").append(entity.getAppChnName()).append(
						"]添加应用信息成功!").toString());
 
		return null;
	}


	public String edit(){
		String sortField = Struts2Utils.getParameter("plasAppId");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("sequenceNo");
			page.setOrder(Page.ASC);
		} else {
			page.setOrderBy(sortField + ",sequenceNo");
			page.setOrder(order + "," + Page.ASC);
		}
		
		page = plasAppmManager.findPage(page, filters);
		//将plas_app的子属性plasRoles置为空。由于hibernate设置为延迟加载，故为空。
		JsonUtil.renderJson(page,new String[]{"plasRoles"});
		return null;
	}
	public String detail(){
		String sortField = Struts2Utils.getParameter("plasAppId");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("sequenceNo");
			page.setOrder(Page.ASC);
		} else {
			page.setOrderBy(sortField + ",sequenceNo");
			page.setOrder(order + "," + Page.ASC);
		}
		
		page = plasAppmManager.findPage(page, filters);
		//将plas_app的子属性plasRoles置为空。由于hibernate设置为延迟加载，故为空。
		JsonUtil.renderJson(page,new String[]{"plasRoles"});
		return null;
	}
	/**
	 * 支持使用Jquery.validate Ajax
	 * 
	 * 检验 角色业务编号不能重复
	 */
	public void isAppBizCdExists() {

		HttpServletRequest request = ServletActionContext.getRequest();
		// String appId = request.getParameter("uaapAppId").trim();
		String newAppBizCd = request.getParameter("appBizCd").trim();
		String oldAppBizCd = request.getParameter("oldAppBizCd").trim();

		// 判断全局唯一
		if (plasAppmManager.isPropertyUnique("appBizCd", newAppBizCd, oldAppBizCd)) {
			JsonUtil.renderSuccess("true");
		} else {
			JsonUtil.renderFailure("false");
		}
	}
	public void isAppEngNameExists() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String newAppEngName = request.getParameter("appEngName").trim();
		String oldAppEngName = request.getParameter("oldAppEngName").trim();

		// 判断全局唯一
		if (plasAppmManager.isPropertyUnique("appEngName", newAppEngName, oldAppEngName)) {
			JsonUtil.renderSuccess("true");
		} else {
			JsonUtil.renderFailure("false");
		}
	}
	public void isAppChnNameExists() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String newAppChnName = request.getParameter("appChnName").trim();
		String oldAppChnName = request.getParameter("oldAppChnName").trim();

		// 判断全局唯一
		if (plasAppmManager.isPropertyUnique("appChnName", newAppChnName, oldAppChnName)) {
			JsonUtil.renderSuccess("true");
		} else {
			JsonUtil.renderFailure("false");
		}
	}
	@Override
	public PlasApp getModel() {
		return entity;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}
	
}
