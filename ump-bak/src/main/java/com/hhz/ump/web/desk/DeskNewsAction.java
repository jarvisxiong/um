package com.hhz.ump.web.desk;

import java.text.ParseException;
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
 * 类名		DeskNewsAction 
 * 创建者 	李劲 
 * 创建日期 	2010-2-3 
 * 描述		为首页提供新闻服务的Action
 */
@Namespace("/desk")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "desk-news.action", type = "redirect") })
public class DeskNewsAction extends CrudActionSupport<OaNews> {

	private static final long serialVersionUID = -5833728668625630228L;

	@Autowired
	private OaNewsManager oaNewsManager;

	// 显示所有的新闻的分页列表
	private Page<OaNews> page = new Page<OaNews>();
	private int pageSize = 12;

	// 显示在首页的最新新闻列表，每页4条
	private Page<OaNews> deskHomePager = new Page<OaNews>();

	private String filterNewsStatus; // 新闻搜索过滤条件 -- 新闻状态(全部/未读)
	private String filterTypeCd; // 新闻搜索过滤条件 -- 类型CD
	private String filterSubject; // 新闻搜索过滤条件 -- 标题
	private String filterStartNewsTime; // 新闻搜索过滤条件 -- 新闻发布时间范围 -- 起始时间
	private String filterEndNewsTime; // 新闻搜索过滤条件 -- 新闻发布时间范围 -- 截至时间
	
	private Map<String, String> mapCreatorName = new HashMap<String, String>();
	private Map<String, String> mapNewsType = new HashMap<String, String>();

	/**
	 * 新闻列表入口，直接转向页面，动态计算行数后经ajax方法获取实际数据列表
	 */
	@Override
	public String list() throws Exception {
		mapNewsType = oaNewsManager.buildNewsTypes(OaNewsManager.NEWS_FLG_NEWS);
	    return SUCCESS;
	}
	
	/**
	 * 分页搜索并显示新闻列表
	 */
	public String query() throws ParseException {
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
	    page = oaNewsManager.getNewsByConditions(orgCd, uiid, filterNewsStatus, filterTypeCd, filterSubject, 
		    filterStartNewsTime, filterEndNewsTime, OaNewsManager.NEWS_FLG_NEWS, page.getPageNo(), pageSize);

	    if (page.getResult().size() == 0) {
		page.setTotalCount(0);
	    }

	    mapNewsType = oaNewsManager.buildNewsTypes(OaNewsManager.NEWS_FLG_NEWS);
	    mapCreatorName = oaNewsManager.buildCreatorNames(page.getResult());
	    
	    return "queryList";
	}
	
	

	/**
	 * 在首页中间栏的新闻栏显示最近的新闻列表，每页最多显示4条
	 * 
	 * @return
	 */
	public String frontPageNews() throws Exception {
	    String orgCd = SpringSecurityUtils.getCurrentDeptCd();
		deskHomePager = oaNewsManager.getFrontPageNews(orgCd, deskHomePager.getPageNo(), deskHomePager.getPageSize());
	    mapNewsType = oaNewsManager.buildNewsTypes(OaNewsManager.NEWS_FLG_NEWS);
	    
	    return "frontPageNews";
	}
	
	/**
	 * 把所有的新闻标记为已读
	 * @return
	 */
	public String readAll() throws Exception {
	    String orgCd = SpringSecurityUtils.getCurrentDeptCd();
	    String uiid = SpringSecurityUtils.getCurrentUiid();
	    
	    if (oaNewsManager.setAllNewsAsReaded(orgCd, uiid, OaNewsManager.NEWS_FLG_NEWS)) {
		Struts2Utils.renderText("succ");
	    }
	    
	    return null;
	}

	public Map<String, String> getMapNewsType() {
	    return mapNewsType;
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

	public String getFilterTypeCd() {
		return filterTypeCd;
	}

	public void setFilterTypeCd(String filterTypeCd) {
		this.filterTypeCd = filterTypeCd;
	}

	public String getFilterSubject() {
		return filterSubject;
	}

	public void setFilterSubject(String filterSubject) {
		this.filterSubject = filterSubject;
	}

	public String getFilterStartNewsTime() {
		return filterStartNewsTime;
	}

	public void setFilterStartNewsTime(String filterStartNewsTime) {
		this.filterStartNewsTime = filterStartNewsTime;
	}

	public String getFilterEndNewsTime() {
		return filterEndNewsTime;
	}

	public void setFilterEndNewsTime(String filterEndNewsTime) {
		this.filterEndNewsTime = filterEndNewsTime;
	}

	public String getFilterNewsStatus() {
		return filterNewsStatus;
	}

	public void setFilterNewsStatus(String filterNewsStatus) {
		this.filterNewsStatus = filterNewsStatus;
	}

	public Page<OaNews> getDeskHomePager() {
	    return deskHomePager;
	}

	public void setDeskHomePager(Page<OaNews> deskHomePager) {
	    this.deskHomePager = deskHomePager;
	}

	public Map<String, String> getMapCreatorName() {
	    return mapCreatorName;
	}

	public void setMapCreatorName(Map<String, String> mapCreatorName) {
	    this.mapCreatorName = mapCreatorName;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}