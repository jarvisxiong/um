package com.hhz.ump.web.desk;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.oa.OaNotifyManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.oa.OaNotify;
import com.hhz.ump.util.CodeNameUtil;

/**
 * 类名 DeskNotifyAction 创建者 lijin 创建日期 2010-2-3 描述 为首页提供公告服务的Action
 */
@Namespace("/desk")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "desk-notify.action", type = "redirect") })
public class DeskNotifyAction extends CrudActionSupport<OaNotify> {

	private static final long serialVersionUID = -5833728668625630228L;

	@Autowired
	private OaNotifyManager oaNotifyManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;

	// 显示所有公告的分页类
	private Page<OaNotify> page = new Page<OaNotify>(15);

	// 显示在首页的最新公告列表，每页显示3条
	private Page<OaNotify> deskHomePager = new Page<OaNotify>(4);

	private String filterNotifyStatus; // 公告搜索过滤条件 -- 公告状态(全部/未读)
	private String filterSubject; // 公告搜索过滤条件 -- 标题
	private String filterStartSendTime; // 公告搜索过滤条件 -- 公告发布时间范围 -- 起始时间
	private String filterEndSendTime; // 公告搜索过滤条件 -- 公告发布时间范围 -- 截至时间

	// 把创建者用户名转为中文名字
	private Map<String, String> mapCreatorName = new HashMap<String, String>();

	// 发布范围中文名称
	private Map<String, String> mapToDeptNames = new HashMap<String, String>();

	// 公告附件的文件名
	private Map<String, String> mapAttachFileNames = new HashMap<String, String>();

	// 公告附件的真实文件名
	private Map<String, String> mapAttachFileRealNames = new HashMap<String, String>();

	/**
	 * “公告列表”入口，直接转向页面，动态计算行数后经ajax方式获取实际数据列表
	 */
	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	/**
	 * 分页搜索并显示公告列表
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
		
		page.setOrderBy("topFlg,sendTime,createdDate");
		page.setOrder(Page.DESC + "," + Page.DESC + "," + Page.DESC);
		page = oaNotifyManager.findPage(page, buildCriterion());
		if (page.getResult().size() == 0) {
			page.setTotalCount(0);
			page.setPageNo(0);
		}

		buildCreatorNames(page.getResult());
		buildToDeptNames(page.getResult());
		buildAttachFileNames(page.getResult());
		return "queryList";
	}

	/**
	 * 根据前台参数构造过滤条件
	 * 
	 * @return
	 * @throws ParseException
	 */
	private Criterion[] buildCriterion() throws ParseException {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		criterionList.add(Restrictions.eq("enabledFlg", "1"));
		String orgCd = SpringSecurityUtils.getCurrentDeptCd();
		criterionList.add(Restrictions.sqlRestriction(
				"(TO_DEPT_CDS like lower(?) or TO_DEPT_CDS = 'all')", "%,"
						+ orgCd + ",%", Hibernate.STRING));

		Date today = DateOperator.truncDate(new Date());
		criterionList.add(Restrictions.le("sendTime", today));
		criterionList.add(Restrictions.le("startDate", today));
		criterionList.add(Restrictions.ge("endDate", today));

		if ("new".equalsIgnoreCase(filterNotifyStatus)) {
			criterionList.add(Restrictions.sqlRestriction(
					"(READERS not like lower(?) or READERS is NULL)", "%"
							+ SpringSecurityUtils.getCurrentUiid() + ",%",
					Hibernate.STRING));
		}
		if (StringUtils.isNotBlank(filterSubject)) {
			criterionList.add(Restrictions.like("subject", "%" + filterSubject
					+ "%"));
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isNotBlank(filterStartSendTime)) {
			criterionList.add(Restrictions.ge("sendTime", formatter
					.parse(filterStartSendTime)));
		}
		if (StringUtils.isNotBlank(filterEndSendTime)) {
			criterionList.add(Restrictions.le("sendTime", formatter
					.parse(filterEndSendTime)));
		}
		return criterionList.toArray(new Criterion[criterionList.size()]);
	}

	/**
	 * 在首页中间栏上部显示最新的公告，最多显示10条
	 * 
	 * @return
	 */
	public String frontPageNotify() throws Exception {
		deskHomePager.setOrderBy("topFlg,sendTime,createdDate");
		deskHomePager.setOrder(Page.DESC + "," + Page.DESC + "," + Page.DESC);
		deskHomePager = oaNotifyManager.findPage(deskHomePager,
				buildCriterion());

		buildAttachFileNames(deskHomePager.getResult());

		return "frontPageNotify";
	}

	/**
	 * 获取未读公告条数
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getUnreadedNotifyNum() throws Exception {
		// List<Criterion> criterionList = new ArrayList<Criterion>();
		// criterionList.add(Restrictions.eq("enabledFlg", "1"));
		// criterionList.add(Restrictions.sqlRestriction("(TO_DEPT_CDS like lower(?) or TO_DEPT_CDS = 'all')",
		// "%" + SpringSecurityUtils.getCurrentDeptCd() + "%",
		// Hibernate.STRING));
		// criterionList.add(Restrictions.le("sendTime", new Date()));
		// criterionList.add(Restrictions.sqlRestriction("(READERS not like lower(?) or READERS is NULL)",
		// "%" + SpringSecurityUtils.getCurrentUiid() + "%", Hibernate.STRING));
		// Criteria criteria =
		// oaNotifyManager.getDao().getSession().createCriteria(OaNotify.class);
		// for (Criterion c : criterionList) {
		// criteria.add(c);
		// }
		// Struts2Utils.renderText(String.valueOf(oaNotifyManager.countCriteriaResult(criteria)));
		//
		StringBuffer sbHql = new StringBuffer();
		sbHql.append("from OaNotify where enabledFlg='1' and ")
				.append("(toDeptCds like lower(:ToDeptCds)");
		String curCenter=SpringSecurityUtils.getCurrentCenterCd();
		Map<String, Object> mapPram = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(curCenter)){
			sbHql.append(" or toDeptCds like lower(:ToCenterCd) ");
			mapPram.put("ToCenterCd", "%" + curCenter+ "%");
		}
		sbHql.append("or toDeptCds = 'all')")
				.append("newsTime <=:Now ")
				.append("(readers not like lower(:Readers) or readers is NULL)");
		mapPram.put("ToDeptCds", "%" + SpringSecurityUtils.getCurrentDeptCd()
				+ "%");
		
		mapPram.put("Now", new java.sql.Date(new Date().getTime()));
		mapPram.put("Readers", SpringSecurityUtils.getCurrentUiid());
		long cnt = oaNotifyManager.countHqlResult(sbHql.toString(), mapPram);
		Struts2Utils.renderText(String.valueOf(cnt));
		return null;
	}

	/**
	 * 把所有的公告标记为已读
	 * 
	 * @return
	 * @throws Exception
	 */
	public String readAll() throws Exception {
		String orgCd = SpringSecurityUtils.getCurrentDeptCd();
		String uiid = SpringSecurityUtils.getCurrentUiid();

		if (oaNotifyManager.setAllNotifiesAsReaded(orgCd, uiid)) {
			Struts2Utils.renderText("succ");
		} else {
			Struts2Utils.renderText("fail");
		}

		return null;
	}

	/**
	 * 在公告列表中创建人要显示为中文姓名
	 * 
	 * @param notifyList
	 */
	private void buildCreatorNames(List<OaNotify> notifyList) {
		for (OaNotify notify : notifyList) {
			if (StringUtils.isBlank(mapCreatorName.get(notify.getCreator()))) {
				mapCreatorName.put(notify.getCreator(), CodeNameUtil
						.getUserNameByCd(notify.getCreator()));
			}
		}
	}

	/**
	 * 把发布范围的机构CD转为中文显示
	 */
	private void buildToDeptNames(List<OaNotify> notifyList) {
		for (OaNotify notify : notifyList) {
			String toDeptCds = notify.getToDeptCds();
			if (StringUtils.isNotBlank(toDeptCds)) {
				if ("all".equalsIgnoreCase(toDeptCds)) {
					mapToDeptNames.put(notify.getOaNotifyId(), "全部");
				} else {
					String[] cds = toDeptCds.split(",");
					StringBuilder orgNames = new StringBuilder();
					for (int i = 1; i < cds.length; i++) {
						String cd = cds[i];
						orgNames.append(CodeNameUtil.getDeptNameByCd(cd));
						if (i != cds.length - 1) {
							orgNames.append(",");
						}
					}
					mapToDeptNames.put(notify.getOaNotifyId(), orgNames
							.toString());
				}
			} else {
				mapToDeptNames.put(notify.getOaNotifyId(), "");
			}
		}
	}

	/**
	 * 构造公告列表中每条记录的附件的文件名称
	 * 
	 * @param notifyList
	 */
	private void buildAttachFileNames(List<OaNotify> notifyList) {
		// 一个公告有且仅有一个PDF附件来显示其内容
		for (OaNotify oaNotify : notifyList) {
			Page<AppAttachFile> attachmentPager = new Page<AppAttachFile>(1);
			List<PropertyFilter> filters = new ArrayList<PropertyFilter>();

			filters.add(new PropertyFilter("EQS_bizEntityId", oaNotify
					.getOaNotifyId()));
			filters.add(new PropertyFilter("EQS_bizModuleCd", "oaNotify"));
			filters.add(new PropertyFilter("NEQS_statusCd", "0"));
			attachmentPager.setOrderBy("createdDate");
			attachmentPager.setOrder(Page.DESC);

			attachmentPager = appAttachFileManager.findPage(attachmentPager,
					filters);
			List<AppAttachFile> files = attachmentPager.getResult();
			if (files != null && files.size() > 0) {
				AppAttachFile file = files.get(0);
				mapAttachFileNames.put(oaNotify.getOaNotifyId(), file
						.getFileName());
				mapAttachFileRealNames.put(oaNotify.getOaNotifyId(), file
						.getRealFileName());
			} else {
				mapAttachFileNames.put(oaNotify.getOaNotifyId(), "");
				mapAttachFileRealNames.put(oaNotify.getOaNotifyId(), "");
			}
		}
	}

	/**
	 * 根据公告类别CD显示公告类别的中文名称
	 * 
	 * @return
	 */
	public Map<String, String> getMapNewsType() {
		return null;
	}

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
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public OaNotify getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<OaNotify> getPage() {
		return page;
	}

	public void setPage(Page<OaNotify> page) {
		this.page = page;
	}

	public String getFilterNotifyStatus() {
		return filterNotifyStatus;
	}

	public void setFilterNotifyStatus(String filterNotifyStatus) {
		this.filterNotifyStatus = filterNotifyStatus;
	}

	public String getFilterSubject() {
		return filterSubject;
	}

	public void setFilterSubject(String filterSubject) {
		this.filterSubject = filterSubject;
	}

	public String getFilterStartSendTime() {
		return filterStartSendTime;
	}

	public void setFilterStartSendTime(String filterStartSendTime) {
		this.filterStartSendTime = filterStartSendTime;
	}

	public String getFilterEndSendTime() {
		return filterEndSendTime;
	}

	public void setFilterEndSendTime(String filterEndSendTime) {
		this.filterEndSendTime = filterEndSendTime;
	}

	public Map<String, String> getMapCreatorName() {
		return mapCreatorName;
	}

	public void setMapCreatorName(Map<String, String> mapCreatorName) {
		this.mapCreatorName = mapCreatorName;
	}

	public Map<String, String> getMapToDeptNames() {
		return mapToDeptNames;
	}

	public void setMapToDeptNames(Map<String, String> mapToDeptNames) {
		this.mapToDeptNames = mapToDeptNames;
	}

	public Map<String, String> getMapAttachFileNames() {
		return mapAttachFileNames;
	}

	public void setMapAttachFileNames(Map<String, String> mapAttachFileNames) {
		this.mapAttachFileNames = mapAttachFileNames;
	}

	public Map<String, String> getMapAttachFileRealNames() {
		return mapAttachFileRealNames;
	}

	public void setMapAttachFileRealNames(
			Map<String, String> mapAttachFileRealNames) {
		this.mapAttachFileRealNames = mapAttachFileRealNames;
	}

	public Page<OaNotify> getDeskHomePager() {
		return deskHomePager;
	}

	public void setDeskHomePager(Page<OaNotify> deskHomePager) {
		this.deskHomePager = deskHomePager;
	}

}
