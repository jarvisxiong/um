package com.hhz.ump.web.desk;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.oa.OaNewsManager;
import com.hhz.ump.entity.oa.OaNews;

/**
 * 
 * 类名		EmployeeActivityAction
 * 创建者	李劲
 * 创建日期	2010-5-20
 * 描述		员工天地Action类
 */
@Namespace("/desk")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "employee-activity.action", type = "redirect") })
public class EmployeeActivityAction extends CrudActionSupport<OaNews> {

	private static final long serialVersionUID = -5833728668625630228L;

	@Autowired
	private OaNewsManager oaNewsManager;

	// 显示所有的新闻的分页列表
	private Page<OaNews> page = new Page<OaNews>();
	private int pageSize = 12;

	private String filterStatus; // 搜索过滤条件 -- 状态(全部/未读)
	private String filterSubject; // 搜索过滤条件 -- 标题
	private String filterStartTime; // 搜索过滤条件 -- 发布时间范围 -- 起始时间
	private String filterEndTime; // 搜索过滤条件 -- 发布时间范围 -- 截至时间
	
	private Map<String, String> mapCreatorName = new HashMap<String, String>();

	/**
	 * “员工天地”入口，直接转向页面，动态计算行数后经ajax方式获取实际数据列表
	 */
	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	/**
	 * 分页搜索并显示员工活动数据
	 */
	public String query() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pageSizeStr = request.getParameter("pageSize").trim();
		int pageSize = new Integer(pageSizeStr).intValue();
		page.setPageSize(pageSize);
		String pageNoStr = request.getParameter("pageNo").trim();
		int pageNo = 0;
		try {
			pageNo = new Integer(pageNoStr).intValue();
		} catch (Exception ex) {
			pageNo = 1;
		}
		page.setPageNo(pageNo);
		
		String orgCd = SpringSecurityUtils.getCurrentDeptCd();
		String uiid = SpringSecurityUtils.getCurrentUiid();
		page = oaNewsManager.getNewsByConditions(orgCd, uiid, filterStatus, null, filterSubject, filterStartTime, 
			filterEndTime, OaNewsManager.NEWS_FLG_EMPLOYEE, page.getPageNo(), pageSize);
		
		if (page.getResult().size() == 0) {
		    page.setTotalCount(0);
		}
		mapCreatorName = oaNewsManager.buildCreatorNames(page.getResult());
		return "queryList";
	}
	
	/**
	 * 把所有的新闻标记为已读
	 * @return
	 */
	public String readAll() throws Exception {
	    String orgCd = SpringSecurityUtils.getCurrentDeptCd();
	    String uiid = SpringSecurityUtils.getCurrentUiid();
	    
	    if (oaNewsManager.setAllNewsAsReaded(orgCd, uiid, OaNewsManager.NEWS_FLG_EMPLOYEE)) {
		Struts2Utils.renderText("succ");
	    }
	    
	    return null;
	}
	
	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {

	}

	@Override
	public String save() throws Exception {
		return null;
	}

	public OaNews getModel() {
		return null;
	}
	
	@Override
	public Page<OaNews> getPage() {
		return page;
	}

	public String getFilterSubject() {
		return filterSubject;
	}

	public void setFilterSubject(String filterSubject) {
		this.filterSubject = filterSubject;
	}

	public Map<String, String> getMapCreatorName() {
	    return mapCreatorName;
	}

	public void setMapCreatorName(Map<String, String> mapCreatorName) {
	    this.mapCreatorName = mapCreatorName;
	}

	public String getFilterStatus() {
	    return filterStatus;
	}

	public void setFilterStatus(String filterStatus) {
	    this.filterStatus = filterStatus;
	}

	public String getFilterStartTime() {
	    return filterStartTime;
	}

	public void setFilterStartTime(String filterStartTime) {
	    this.filterStartTime = filterStartTime;
	}

	public String getFilterEndTime() {
	    return filterEndTime;
	}

	public void setFilterEndTime(String filterEndTime) {
	    this.filterEndTime = filterEndTime;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}