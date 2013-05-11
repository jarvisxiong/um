/**
 * 
 */
package com.hhz.ump.web.app;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.app.AppParamManager;
import com.hhz.ump.dao.app.AppSeqManager;
import com.hhz.ump.entity.app.AppParam;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.util.JsonUtil2;

/**
 * @author huangbj 2009-12-25
 */
@Namespace("/app")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-param.action", type = "redirect") })
public class AppParamAction extends CrudActionSupport<AppParam> {
	private static final long serialVersionUID = -3445152342227169047L;

	private Page<AppParam> page = new Page<AppParam>(15);// 每页15条记录

	@Autowired
	private AppParamManager appParamManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private AppSeqManager appSeqManager;

	private AppParam entity;

	private String filter_LIKES_appEnName;

	private String filter_LIKES_appChnName;

	private String filter_EQS_appCd;
	//搜索条件
	private String filter_EQS_paramCd;
	private String filter_LIKES_paramName;
	private String filter_LIKES_paramValue;
	private String filter_EQS_defaultFlg;
	

	public AppParamAction() {
	}

	public AppParam getModel() {
		return entity;
	}

	public String getFilter_LIKES_appEnName() {
		return filter_LIKES_appEnName;
	}

	public void setFilter_LIKES_appEnName(String filterLIKESAppEnName) {
		filter_LIKES_appEnName = filterLIKESAppEnName;
	}

	public String getFilter_LIKES_appChnName() {
		return filter_LIKES_appChnName;
	}

	public void setFilter_LIKES_appChnName(String filterLIKESPageName) {
		filter_LIKES_appChnName = filterLIKESPageName;
	}

	public String getFilter_EQS_appCd() {
		return filter_EQS_appCd;
	}

	public void setFilter_EQS_appCd(String filterEQSAppCd) {
		filter_EQS_appCd = filterEQSAppCd;
	}

	@Override
	public Page<AppParam> getPage() {
		return page;
	}

	@Override
	protected void prepareModel() throws Exception {
		String appParamId = getId();
		if (StringUtils.isNotBlank(appParamId)) {
			entity = appParamManager.getEntity(appParamId);
		} else {
			entity = new AppParam();
		}
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("paramCd,dispOrderNo");
			page.setOrder(Page.ASC + "," + Page.ASC);
		} else {
			page.setOrderBy(sortField + ",dispOrderNo");
			page.setOrder(order + "," + Page.ASC);
		}

		page = appParamManager.findPage(page, filters);
		JsonUtil2.renderJson(page);
		return null;
	}
	@SuppressWarnings("unchecked")
	public String saveBatch(){

		try {
			List<AppParam> insertedRecords = (List<AppParam>) JsonUtil2.getInsertRecords(AppParam.class);
			List<AppParam> updatedRecords =  (List<AppParam>) JsonUtil2.getUpdatedRecords(AppParam.class);
			List<AppParam> deletedRecords =  (List<AppParam>) JsonUtil2.getDeletedRecords(AppParam.class);
			appParamManager.saveOrUpdateAppParams(insertedRecords, updatedRecords, deletedRecords);
			JsonUtil2.renderSuccess("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtil2.renderFailure("保存失败");
		}
		return null;
	}
	@Override
	public String input() throws Exception {

		return INPUT;
	}

	@Override
	public String save() throws Exception {

		String paramCd = entity.getParamCd();
		if (StringUtils.isBlank(paramCd)) {
			paramCd = appSeqManager.createNextValue(GlobalConstants.SEQ_PARAM_CD)
					.toString();
			entity.setParamCd(paramCd);
		}

		appParamManager.saveAppParam(entity);
		addActionMessage(getText("common.success"));
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		return RELOAD;
	}

	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String isParamCdExists() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newParamCd = request.getParameter("paramCd").trim();
		String oldParamCd = request.getParameter("oldParamCd").trim();

		if (appParamManager.isPropertyUnique("paramCd", newParamCd, oldParamCd)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		// 因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String isParamNameExists() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newParamName = request.getParameter("paramName").trim();
		String oldParamName = request.getParameter("oldParamName").trim();

		if (appParamManager.isPropertyUnique("paramName", newParamName, oldParamName)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		// 因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	// 是否默认参数
	public Map<String, String> getMapDefaultFlg() {
		return appDictTypeManager
				.getDictDataByTypeCd(DictContants.UAAP_IS_DEFAULT_PARAM);
	}

	public String getFilter_EQS_paramCd() {
		return filter_EQS_paramCd;
	}

	public void setFilter_EQS_paramCd(String filter_EQS_paramCd) {
		this.filter_EQS_paramCd = filter_EQS_paramCd;
	}

	public String getFilter_LIKES_paramName() {
		return filter_LIKES_paramName;
	}

	public void setFilter_LIKES_paramName(String filter_LIKES_paramName) {
		this.filter_LIKES_paramName = filter_LIKES_paramName;
	}

	public String getFilter_LIKES_paramValue() {
		return filter_LIKES_paramValue;
	}

	public void setFilter_LIKES_paramValue(String filter_LIKES_paramValue) {
		this.filter_LIKES_paramValue = filter_LIKES_paramValue;
	}

	public String getFilter_EQS_defaultFlg() {
		return filter_EQS_defaultFlg;
	}

	public void setFilter_EQS_defaultFlg(String filter_EQS_defaultFlg) {
		this.filter_EQS_defaultFlg = filter_EQS_defaultFlg;
	}
 
}
