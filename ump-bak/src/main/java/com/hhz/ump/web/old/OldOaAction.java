package com.hhz.ump.web.old;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.ump.dao.old.NewsManager;
import com.hhz.ump.dao.old.NotifyManager;
import com.hhz.ump.entity.old.News;
import com.hhz.ump.entity.old.Notify;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/old")
public class OldOaAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private NewsManager newsManager;

	@Autowired
	private NotifyManager notifyManager;

	private Page<News> pageNews = new Page<News>(14);

	private Page<Notify> pageNotifys = new Page<Notify>(14);

	private String id;

	private News news;

	private Notify notify;

	public Map<String, String> mapNewsType = new HashMap<String, String>();

	public Map<String, String> mapAtta = new HashMap<String, String>();

	private String realFileName;

	private InputStream is;

	private String contentType;
	
	private String filter_LIKES_subjectme;
	private String filter_LIKES_subject;

	public OldOaAction() {
		// mapNewsType.put("", value)
	}

	/**
	 * “旧OA新闻”入口，直接转向页面，动态计算行数后经ajax方式获取实际数据列表
	 */
	public String news() throws Exception {
		return "news";
	}
	/**
	 * “旧OA公告”入口，直接转向页面，动态计算行数后经ajax方式获取实际数据列表
	 */
	public String notifys() throws Exception {
		return "notify";
	}
	
	/**
	 * 分页搜索并显示旧OA新闻列表
	 */
	public String queryNews() throws Exception {
		setPageSizeAndNo(pageNews);
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		pageNews.setOrderBy("newsTime");
		pageNews.setOrder(Page.DESC);
		pageNews = newsManager.findPage(pageNews, filters);
		return "newsQueryList";
	}
	
	/**
	 * 分页搜索并显示旧OA公告列表
	 */
	public String queryNotify() throws Exception {
		setPageSizeAndNo(pageNotifys);
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		pageNotifys.setOrderBy("sendTimet");
		pageNotifys.setOrder(Page.DESC);
		pageNotifys = notifyManager.findPage(pageNotifys, filters);
		return "notifyQueryList";
	}
	
	/**
	 * 按照request中的参数设置page相关页大小以及当前页属性
	 * @param page
	 */
	private <T> void setPageSizeAndNo(Page<T> page){
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
		return ;
	}
	
	
	public String newsDetail() throws Exception {
		news = newsManager.getEntity(getId());
		if (news.getAttachmentId() != null) {
			String[] attaIds = news.getAttachmentId().split(",");
			String[] attaNames = news.getAttachmentName().split("\\*");

			for (int i = 0; i < attaIds.length; i++) {
				mapAtta.put(attaIds[i], attaNames[i]);
			}

		}
		return "newsDetail";
	}

	public String notiDetail() throws Exception {
		notify = notifyManager.getEntity(getId());
		if (notify.getAttachment() != null) {
			String[] attaIds = notify.getAttachment().split(",");
			String[] attaNames = notify.getAttachmen1().split("\\*");

			for (int i = 0; i < attaIds.length; i++) {
				mapAtta.put(attaIds[i], attaNames[i]);
			}

		}
		return "notiDetail";
	}

	public Page<News> getPageNews() {
		return pageNews;
	}

	public Page<Notify> getPageNotifys() {
		return pageNotifys;
	}

	public void setPageNews(Page<News> pageNews) {
		this.pageNews = pageNews;
	}

	public void setPageNotifys(Page<Notify> pageNotifys) {
		this.pageNotifys = pageNotifys;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Map<String, String> getMapNewsType() {
		return mapNewsType;
	}

	public void setMapNewsType(Map<String, String> mapNewsType) {
		this.mapNewsType = mapNewsType;
	}

	public String getRealFileName() {
		return realFileName;
	}

	/**
	 * 提供下载的数据流
	 * 
	 * @return
	 * @throws Exception
	 */

	public InputStream getTargetFile() throws Exception {
		return is;
	}

	public String getContentType() {
		return contentType;
	}

	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Notify getNotify() {
		return notify;
	}

	public void setNotify(Notify notify) {
		this.notify = notify;
	}

	public String getFilter_LIKES_subjectme() {
	    return filter_LIKES_subjectme;
	}

	public void setFilter_LIKES_subjectme(String filterLIKESSubjectme) {
	    filter_LIKES_subjectme = filterLIKESSubjectme;
	}

	public String getFilter_LIKES_subject() {
	    return filter_LIKES_subject;
	}

	public void setFilter_LIKES_subject(String filterLIKESSubject) {
	    filter_LIKES_subject = filterLIKESSubject;
	}

}
