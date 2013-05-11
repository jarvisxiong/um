/**
 * 
 */
package com.hhz.ump.web.plan;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.utils.EncodeUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.ServiceException;
import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.jbpm.JbpmTaskManager;
import com.hhz.ump.dao.oa.OaAllAttentionManager;
import com.hhz.ump.dao.plan.PlanOperationLogManager;
import com.hhz.ump.dao.plan.PlanWorkManager;
import com.hhz.ump.dao.plan.PlanWorkMessageManager;
import com.hhz.ump.dao.plan.PlanWorkSnapManager;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.plan.PlanWork;
import com.hhz.ump.entity.plan.PlanWorkMessage;
import com.hhz.ump.entity.plan.PlanWorkSnap;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DictContants;
import com.hhz.uums.entity.ws.WsPlasOrg;

/**
 * 工作计划
 * 
 * @author huangj 2010-3-26
 */
@Namespace("/plan")
@Results( {
		@Result(name = CrudActionSupport.RELOAD, location = "planWork.action", type = "redirect", params = { "centerCd", "${centerCd}" }),
		@Result(name = CrudActionSupport.RELOAD_SUB, location = "planWork.action", type = "redirect"),
		@Result(name = "admin", location = "work-plan-main!list.action", type = "redirect"),
		@Result(name = "manager", location = "work-plan-main!list.action", type = "redirect"),
		@Result(name = "user", location = "plan-center-query!queryPlanCenter.action", type = "redirect"),
		@Result(name = "userDc", location = "plan-execution-plan!getCenterPlanRel.action", type = "redirect", params = { "projectCd", "${centerCd}" }),
		@Result(name = "view", location = "plan-work!getAllPlan.action", type = "redirect", params = { "centerCd", "${centerCd}" }) })
public class PlanWorkAction extends CrudActionSupport<PlanWork> {
	private static final long serialVersionUID = 8071052318603147332L;

	public static final String STATUS_CD_WEIQUEREN = "0"; // 未确认

	public static final String STATUS_CD_JINXINGZHONG = "1"; // 进行中

	public static final String STATUS_CD_SHENQINGSHANCHU = "2"; // 申请删除

	public static final String STATUS_CD_WANCHENG = "3"; // 完成

	public static final String STATUS_CD_SHANGCHU = "4"; // 删除

	public static final String STATUS_CD_YINCANG = "5"; // 隐藏

	public static final String STATUS_CD_YUWANCHENG = "6"; // 预完成

	private PlanWork entity;

	private PlanWorkMessage message;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private PlanOperationLogManager planOperationLogManager;
	
	@Autowired
	private JbpmTaskManager jbpmTaskManager;
	
	@Autowired
	private OaAllAttentionManager oaAllAttentionManager;

	private Page<PlanWorkSnap> pageHistory = new Page<PlanWorkSnap>(9999);

	private String operationlogType;

	private int serialOrder;

	private String myTask;

	private String content;

	private String area;

	private Date targetDate;

	private boolean if_in_attention;	//是否由关注引进本模块
	private String opened_entityid;	//直接打开的entityId
	private Map<String,String> attentionMap;	//关注id，用来判断是否有关注
	private String isAttentioned;	//修改的记录是否是被关注的，这里直接传这个参数可以不用读取关注表的map出来
	private Map<String,String> attentionMapUnread;	//未读的关注id，关注列表时用，用来判断关注是否已读
	
	private String[] chkIds;

	private Integer[] chkOrders;

	private String principal;

	@Autowired
	private PlanWorkManager planWorkManager;

	@Autowired
	private PlanWorkSnapManager planWorkSnapManager;

	@Autowired
	private PlanWorkMessageManager planWorkMessageManager;

	private List<WsPlasOrg> uaapOrgs;

	private String fgCenterCd;

	// 小中心列表
	private List<WsPlasOrg> orgCenterList;

	private List<PlanWorkMessage> messageList;

	// 大中心清单/小中心清单bigCenterOrgList
	private List<WsPlasOrg> bigCenterOrgList;

	private List<List<WsPlasOrg>> smallCenterOrgList;

	private String orderStr1; // 排序字段1

	private String orderStr2; // 排序字段2

	private String orderDir1; // 排序顺序1

	private String orderDir2; // 排序顺序2

	private int if_search_all; // 是否搜索全部

	private String centerCd;

	private int now_year = 0; // 当前的年份

	private int now_month_of_year = 0; // 当前的周数

	/**
	 * 新增记录时，附件上传使用的临时ID
	 */
	private String entityTmpId;

	private Map<String, List<WsPlasOrg>> orgMap;

	private String isEditOrg;
	
	private Map<String, String> mapContentTips = new HashMap<String, String>();	//保存content的title,包含content和前三条留言记录


	/**
	 * 计划管理统一入口
	 * 
	 * @return
	 * @throws Exception
	 */
	public String main() throws Exception {
		centerCd = SpringSecurityUtils.getCurrentDeptCd();
		String result = null;
		if (SpringSecurityUtils.hasRole(Constants.A_PLAN_WORK_XMGL)) {
			result = "manager";
		} else if (SpringSecurityUtils.hasRole(Constants.A_PLAN_WORK_ADMIN)) {
			result = "admin";
		} else if (SpringSecurityUtils.hasRole(Constants.A_PLAN_WORK_MANAGER)) {
			result = "manager";
		} else if (SpringSecurityUtils.hasRole(Constants.A_PLAN_WORK_USER)) {
			result = "user";
		} else if (SpringSecurityUtils.hasRole(Constants.A_PLAN_WORK_USER_DC)) {
			result = "userDc";
		} else if (SpringSecurityUtils.hasRole(Constants.A_PLAN_WORK_VIEW)) {
			result = "user";
		}
		return result;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		planWorkManager.deletePlanWork(getId());
		addActionMessage(getText("common.success"));
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		try {
			planWorkManager.deleteBatch(getIds());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}
	
	public String getAllPlan() throws Exception {
		// getRelationCenters();
		orgMap = this.buildOrgMap();
		String from_centerCd = Struts2Utils.getRequest().getParameter("centerCd");
		if (null != from_centerCd && !"".equalsIgnoreCase(from_centerCd)) {
			centerCd = from_centerCd;
		} else {
			String curCenterCd = SpringSecurityUtils.getCurrentDeptCd();
			if (curCenterCd == null||"".equals(curCenterCd)) {
				centerCd = SpringSecurityUtils.getCurrentDeptCd();
			} else {
				WsPlasOrg org = SpringSecurityUtils
				.getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX);
				if(org!=null){
					centerCd = org.getOrgCd();
				}else{
					centerCd = SpringSecurityUtils.getCurrentDeptCd();
				}
			}
		}
		now_year = DateOperator.getYear(new Date());
		now_month_of_year = DateOperator.getMonth12(new Date());
		//Struts2Utils.getRequest().setAttribute("center_name", CodeNameUtil.getDeptNameByCd(centerCd));
		return SUCCESS;
	}

	/**
	 * 获取大小中心分组
	 * 
	 * @return
	 */
	private Map<String, List<WsPlasOrg>> buildOrgMap() {
		Map<String, List<WsPlasOrg>> map = new LinkedHashMap<String, List<WsPlasOrg>>();
		List<WsPlasOrg> uaapOrgs = PlasCache.getOrgListByTypeCd(Constants.ORG_TYPE_CD_FG);
		CollectionHelper.sortList(uaapOrgs, "orgBizCd");// 排序

		for (WsPlasOrg wsUaapOrg : uaapOrgs) {
			if (wsUaapOrg.getOrgBizCd().equals(Constants.ORG_BIZ_CD_DCGS)) {
				continue;
			}
			List<WsPlasOrg> list = PlasCache.getPhysicalDirectOrgListByOrgCd(wsUaapOrg.getOrgCd(),Constants.ORG_TYPE_CD_ZX);
			if (null != list && list.size() > 0) {
				map.put(wsUaapOrg.getOrgName(), list);
			} else {
				List<WsPlasOrg> orgList = new ArrayList<WsPlasOrg>();
				orgList.add(wsUaapOrg);
				map.put(wsUaapOrg.getOrgName(), orgList);
			}
		}

		return map;
	}

	/**
	 * 搜索拍照历史
	 * 
	 * @return
	 * @throws Exception
	 */
	public String listHistory() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		if (!pageHistory.isOrderBySetted()) {
			pageHistory.setOrderBy("targetPointCd,statusCd,serialOrder,createdDate");
			pageHistory.setOrder(Page.ASC + "," + Page.ASC + "," + Page.ASC + "," + Page.DESC);
		}
		Short planYear = Short.valueOf(Struts2Utils.getParameter("planYear"));
		Short planMonth = Short.valueOf(Struts2Utils.getParameter("planMonth"));
		filters.add(new PropertyFilter("EQs_planYear", planYear));
		filters.add(new PropertyFilter("EQs_planMonth", planMonth));
		filters.add(new PropertyFilter("EQS_orgCd", centerCd));
		buildFilter(pageHistory);
		pageHistory = planWorkSnapManager.findPage(pageHistory, filters);
		return "history";
	}

	private void buildFilter(Page page) {

		if (null != orderStr1 && !"".equalsIgnoreCase(orderStr1) && null != orderStr2 && !"".equalsIgnoreCase(orderStr2)) {
			page.setOrderBy(orderStr1 + "," + orderStr2 + ",serialOrder,createdDate");
			page.setOrder(orderDir1 + "," + orderDir2 + "," + Page.ASC + "," + Page.DESC);
		} else if (null != orderStr1 && !"".equalsIgnoreCase(orderStr1)) {
			page.setOrderBy(orderStr1 + ",serialOrder,createdDate");
			page.setOrder(orderDir1 + "," + Page.ASC + "," + Page.DESC);
		} else if (null != orderStr2 && !"".equalsIgnoreCase(orderStr2)) {
			page.setOrderBy(orderStr2 + ",serialOrder,createdDate");
			page.setOrder(orderDir2 + "," + Page.ASC + "," + Page.DESC);
		}

		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("targetPointCd,statusCd,serialOrder,createdDate");
			page.setOrder(Page.ASC + "," + Page.ASC + "," + Page.ASC + "," + Page.DESC);
		}
	}

	@Override
	public String list() throws Exception {
		//page.setPageSize(30);
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		if (1 == if_search_all) {
			page.setPageSize(999);
		} 
		if (null == centerCd || "".equalsIgnoreCase(centerCd)) {
			centerCd = SpringSecurityUtils.getCurrentDeptCd();
		}
		if(!if_in_attention){
			//正常搜索
			if(null!=myTask && "true".equalsIgnoreCase(myTask)){
				//从邮件报表中搜索属于我自己的
				//filters.add(new PropertyFilter("LIKES_principal", SpringSecurityUtils.getCurrentUiid()));
				List<PlanWork> planWorkList = new ArrayList();
				StringBuffer hql = new StringBuffer("select task from JbpmTask as task, JbpmTaskCandidate as cond ");
				hql.append(" where task.jbpmTaskId=cond.jbpmTask.jbpmTaskId and task.moduleCd like 'planWork'");
				hql.append(" and (cond.userCd=:userCd1 or cond.userCd like :userCd2) ");
				
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userCd1", SpringSecurityUtils.getCurrentUiid());
				param.put("userCd2", "%;" + SpringSecurityUtils.getCurrentUiid() + ";%");
				List<JbpmTask> jbpmTaskList = jbpmTaskManager.find(hql.toString(), param);
				for(JbpmTask jbpmTask:jbpmTaskList){
					try {
						String entityId = jbpmTask.getJbpmId();
						PlanWork planWork = planWorkManager.getEntity(entityId);
						planWorkList.add(planWork);
					} catch (Exception e) {
					}
				}
				page.setResult(planWorkList);
			}else{
				PropertyFilter filter = new PropertyFilter("EQS_orgCd", centerCd);
				filters.add(filter);
				String search_statusCd = Struts2Utils.getParameter("search_statusCd");
				if (search_statusCd != null && !search_statusCd.equalsIgnoreCase("")) {
					filters.add(new PropertyFilter("EQS_statusCd", search_statusCd));
				} else {
					filters.add(new PropertyFilter("INA_statusCd", new Object[] { "0", "1", "2", "3","6" }));
				}
				buildFilter(page);

				// 判断是否搜索旧的记录，如果是当周并且当周没有拍照过，显示双周计划，否则显示拍照的记录
				int source_now_year = DateOperator.getYear(new Date());
				int source_now_month_of_year = DateOperator.getMonth12(new Date());
				if (source_now_year != now_year && source_now_month_of_year != now_month_of_year) {
					// 这里搜索拍照的记录
				} else {
					// 在这里要先判断当周是否拍照过
					page = planWorkManager.findPage(page, filters);
				}
				// 生成编号
				Struts2Utils.getRequest().setAttribute("newSerialOrder", planWorkManager.getMaxNo(centerCd));
				Struts2Utils.getRequest().setAttribute("newSerialNumber",
						"YJH-" + CodeNameUtil.getDeptShortNameByCd(centerCd) + "-" + now_year + now_month_of_year + "-");
				
				int countAll = planWorkManager.getCountAll(centerCd);	//当前总任务数
				int countCompleted = planWorkManager.getCountCompleted(centerCd);	//这个月新增的任务完成数
				int countFromPrev = planWorkManager.getCountFromPrev(centerCd);	//来自上个月的任务数
				int countFromPrevCompleted = planWorkManager.getCountFromPrevCompleted(centerCd);	//来自上个月的完成的任务数
				int completedRate = 0;
				if(0!=countFromPrev){
					completedRate = countFromPrevCompleted*100/countFromPrev;	//上个月任务完成率
				}
				Struts2Utils.getRequest().setAttribute("countAll", countAll);
				Struts2Utils.getRequest().setAttribute("countComplete", countCompleted+countFromPrevCompleted);
				Struts2Utils.getRequest().setAttribute("countFromPrev", countFromPrev);
				Struts2Utils.getRequest().setAttribute("countFromPrevCompleted", countFromPrevCompleted);
				Struts2Utils.getRequest().setAttribute("completedRate", completedRate);
			}
		}else{
			//在关注里
			List list_planWork = new ArrayList();
			attentionMapUnread = new LinkedHashMap<String,String>();
			List list_unread_attention = oaAllAttentionManager.getUnreadIds("planWork", 
					SpringSecurityUtils.getCurrentUiid(), "PLAN_WORK", "PLAN_WORK_ID");
			for(int i=0;null!=list_unread_attention && i<list_unread_attention.size();i++){
				String attentionEntityId = (String)list_unread_attention.get(i);
				PlanWork planWork = planWorkManager.getEntity(attentionEntityId);
				list_planWork.add(planWork);
				attentionMapUnread.put(attentionEntityId, "unread");
			}
			List list_read_attention = oaAllAttentionManager.getReadIds("planWork", 
					SpringSecurityUtils.getCurrentUiid(), "PLAN_WORK", "PLAN_WORK_ID");
			for(int i=0;null!=list_read_attention && i<list_read_attention.size();i++){
				String attentionEntityId = (String)list_read_attention.get(i);
				PlanWork planWork = planWorkManager.getEntity(attentionEntityId);
				list_planWork.add(planWork);
			}
			page.setResult(list_planWork);
		}
		attentionMap=oaAllAttentionManager.getMyAttention("planWork", SpringSecurityUtils.getCurrentUiid());
		
		initIsEditOrg();
		buildAttributes(page.getResult());
		
		return "list";
	}

	private void initIsEditOrg() {
		if (SpringSecurityUtils.hasRole(Constants.A_PLAN_WORK_MANAGER) || SpringSecurityUtils.hasRole(Constants.A_PLAN_WORK_USER)) {
			List<WsPlasOrg> relaOrgs = PlasCache.getRelationCenterOrgs(SpringSecurityUtils.getCurrentUiid());
			if (relaOrgs!=null) {
				for (WsPlasOrg wsUaapOrg : relaOrgs) {
					if (wsUaapOrg.getOrgTypeCd() != null && !wsUaapOrg.getOrgTypeCd().equals(Constants.ORG_TYPE_CD_FG)) {
						if (wsUaapOrg.getOrgCd().equals(centerCd)) {
							isEditOrg = "1";
							break;
						}
					}else if(wsUaapOrg.getOrgTypeCd().equals(Constants.ORG_TYPE_CD_FG)){
						//如果用户在分管下，判断是否是现中心所属的分管
						WsPlasOrg wsUaapOrg2 = PlasCache.getOrgByCd(centerCd);
						if(wsUaapOrg2!=null && wsUaapOrg.getPlasOrgId().equalsIgnoreCase(
								PlasCache.getLogicalParentId(wsUaapOrg2.getPlasOrgId())
								)){
							isEditOrg = "1";
							break;
						}
					}else if(wsUaapOrg.getOrgTypeCd() != null && wsUaapOrg.getOrgTypeCd().equals(Constants.ORG_TYPE_CD_JT)){
						isEditOrg = "1";
						break;
					}
				}
			}
		}
	}

	/**
	 * 添加留言
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveMessage() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planWorkManager.getEntity(getId());
			PlanWorkMessage wm = new PlanWorkMessage();
			wm.setContent(content);
			wm.setPlanWork(entity);
			planWorkMessageManager.savePlanWorkMessage(wm);
			planWorkManager.save(entity);
			Struts2Utils.renderText("ok");
			try {
				planOperationLogManager.addWorkPlanLog(entity.getPlanWorkId(), entity.getSerialNumber() + entity.getSerialOrder(),
						PlanOperationLogManager.OPERATION_TYPE_UPDATE, wm.getPlanWork().getOrgCd(), "留言：" + content);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			Struts2Utils.renderText("no");
		}
		return null;
	}

	public String fetchContent() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planWorkManager.getEntity(getId());
			messageList = entity.getPlanWorkMessages();
			// CollectionHelper.sortList(messageList, "createdDate", true);
			return "message";
		} else
			return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = planWorkManager.getEntity(getId());
		} else {
			entity = new PlanWork();
			entityTmpId = RandomUtils.generateTmpEntityId();
		}
	}

	@Override
	public String save() throws Exception {
		String statusCd = Struts2Utils.getParameter("statusCd");
		if ("3".equalsIgnoreCase(statusCd)) {
			entity.setEndDate(new Date());
		}
		if ("0".equalsIgnoreCase(statusCd)) {
			entity.setStatusCd("1");
		}
		planWorkManager.savePlanWork(entity, entityTmpId);
		String userDeptCd = Struts2Utils.getParameter("userDeptCd");
		if (userDeptCd != null && !userDeptCd.equals("")) {
			centerCd = userDeptCd;
		}
		setId(entity.getPlanWorkId());
		addActionMessage(getText("common.success"));
		Struts2Utils.renderText(String.valueOf(entity.getPlanWorkId()));

		String newMessage = Struts2Utils.getParameter("newMessage");
		if(null!=newMessage && !"".equalsIgnoreCase(newMessage)){
			PlanWorkMessage wm = new PlanWorkMessage();
			wm.setContent(newMessage);
			wm.setPlanWork(entity);
			planWorkMessageManager.savePlanWorkMessage(wm);
		}

		try {
			planOperationLogManager.addWorkPlanLog(entity.getPlanWorkId(), entity.getSerialNumber() + "-" + entity.getSerialOrder(),
					operationlogType, entity.getOrgCd(), "保存" + entity.getSerialNumber() + entity.getSerialOrder() + ";" + entity.getContent() + ";"
							+ DateOperator.formatDate(entity.getTargetDate(), "yyyy-MM-dd") + ";" + entity.getStatusCd());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * 确认，拍照功能
	 * @return
	 * @throws Exception
	 */
	public String confirm() throws Exception {
		Date now=new Date();
		Short planYear = (short) DateOperator.getYear(now);
		Short planMonth= (short) DateOperator.getMonth12(now);
		if (!planWorkSnapManager.isConfirmed(centerCd,planYear,planMonth)) {
			planWorkManager.confirm(centerCd);
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("已经归档过");
		}
		return null;
	}

	/**
	 * 解锁
	 * 
	 * @return
	 * @throws Exception
	 */
	public String unConfirm() throws Exception {
		Short planYear = Short.valueOf(Struts2Utils.getParameter("planYear"));
		Short planMonth = Short.valueOf(Struts2Utils.getParameter("planMonth"));
		if (planWorkSnapManager.isConfirmed(centerCd, planYear, planMonth)) {
			planWorkManager.unConfirm(centerCd, planYear, planMonth);
			Struts2Utils.renderText("success");
		} else {
			Struts2Utils.renderText("暂未锁定，无法解锁");
		}
		return null;
	}

	public void prepareFetchDetail() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planWorkManager.getEntity(getId());
			Struts2Utils.getRequest().setAttribute("centerCd", entity.getOrgCd());
		}
	}

	public String fetchDetail() throws Exception {
		return "detail";
	}

	public void prepareFetchMain() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planWorkManager.getEntity(getId());
			Struts2Utils.getRequest().setAttribute("centerCd", entity.getOrgCd());
		}
	}

	public String fetchMain() throws Exception {
		return "main";
	}

	/*
	 * 获取大中心列表
	 */
	public List<WsPlasOrg> getListFgCenters() {
		// List<WsPlasOrg> fgCenters_return = new ArrayList<WsPlasOrg>();
		List<WsPlasOrg> fgCenters_ordered = new ArrayList<WsPlasOrg>();
		List<WsPlasOrg> uaapOrgs = PlasCache.getOrgListByTypeCd(Constants.ORG_TYPE_CD_FG);
		CollectionHelper.sortList(uaapOrgs, "orgBizCd");// 排序
		int DC_order = -1; // 地产公司在列表中的序号，要把地产公司提取出来排第一个
		
		WsPlasOrg wsUaapOrg = null;
		for (int i = 0; null != uaapOrgs && i < uaapOrgs.size(); i++) {
			wsUaapOrg = uaapOrgs.get(i);
			if (wsUaapOrg.getOrgBizCd().equals("1099")) {
				DC_order = i;
				fgCenters_ordered.add(wsUaapOrg);
			}
		}
		for (int i = 0; null != uaapOrgs && i < uaapOrgs.size(); i++) {
			if (i != DC_order) {
				wsUaapOrg = uaapOrgs.get(i);
				fgCenters_ordered.add(wsUaapOrg);
			}
		}

		// if
		// (SpringSecurityUtils.CurrentOrgCd().equals("DC"))
		// {
		// for (WsPlasOrg wsUaapOrg : fgCenters_ordered) {
		// // 如果是地产公司进来，只出现1099地产公司、1004开发分管机构
		// if (wsUaapOrg.getOrgBizCd().equals("1099") ||
		// wsUaapOrg.getOrgBizCd().equals("1004")) {
		// fgCenters_return.add(wsUaapOrg);
		// }
		// }
		// return fgCenters_return;
		// } else
		return fgCenters_ordered;
	}

	/**
	 * 分别获取大小中心的清单，同时建立大中心与小中心列表的映射关系
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getRelationCenters() {

		// 清空列表
		if (bigCenterOrgList == null) {
			this.bigCenterOrgList = new ArrayList<WsPlasOrg>();
		} else {
			this.bigCenterOrgList.clear();
		}

		if (smallCenterOrgList == null) {
			this.smallCenterOrgList = new ArrayList<List<WsPlasOrg>>();
		} else {
			this.smallCenterOrgList.clear();
		}

		// 加载大中心
		this.bigCenterOrgList = this.getListFgCenters();
		// 加载小中心
		for (WsPlasOrg org : bigCenterOrgList) {
			String orgCd = org.getOrgCd();
			List<WsPlasOrg> list = PlasCache.getPhysicalDirectOrgListByOrgCd(orgCd, Constants.ORG_TYPE_CD_ZX);
			if (list == null) {
				list = new ArrayList<WsPlasOrg>();
			}
			this.smallCenterOrgList.add(list);
		}

		return "";
	}

	/*
	 * 批量操作状态
	 */
	public String doUpdateStatusAll() throws Exception {
		try {
			String statusCd = Struts2Utils.getParameter("statusCd");
			for (String planWork_id : chkIds) {
				entity = planWorkManager.getEntity(planWork_id);
				entity.setStatusCd(statusCd);
				if (STATUS_CD_WANCHENG.equalsIgnoreCase(statusCd)) {
					entity.setEndDate(new Date());
				}
				planWorkManager.savePlanWork(entity);
			}
			addActionMessage(getText("common.success"));
			Struts2Utils.renderText("done");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
			Struts2Utils.renderText("error");
		}
		return null;
	}

	/*
	 * 交换顺序
	 */
	public String doExchangeOrder() throws Exception {
		try {
			for (int i = 0; null != chkIds && i < chkIds.length; i++) {
				String planWork_id = chkIds[i];
				entity = planWorkManager.getEntity(planWork_id);
				entity.setSerialOrder(new BigDecimal(chkOrders[i]));
				planWorkManager.savePlanWork(entity);
			}
			addActionMessage(getText("common.success"));
			Struts2Utils.renderText("done");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
			Struts2Utils.renderText("error");
		}
		return null;
	}

	/**
	 * 遍历记录列表构造前台展现数据
	 * @param oaMeetings
	 */
	private void buildAttributes(List<PlanWork> planWorks) {
	    String id = null;
	    for (PlanWork p : planWorks) {
		id = p.getPlanWorkId();
		
		// 鼠标放在标题上时，显示标题和最新的3条评论
		StringBuffer tip = new StringBuffer();
		tip.append("<p>" + EncodeUtils.htmlEscape(p.getContent()) + "</p>");
		List<PlanWorkMessage> planWorkMessages = p.getPlanWorkMessages();
		DateFormat df = new SimpleDateFormat("MM-dd");
		if (planWorkMessages.size() > 0) {
		    tip.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
		    int size = planWorkMessages.size() >= 3 ? 3 : planWorkMessages.size();
		    for (int i = 0; i < size; i ++) {
		    PlanWorkMessage planWorkMessage = planWorkMessages.get(i);
			String userName = CodeNameUtil.getUserNameByCd(planWorkMessage.getCreator());
			tip.append("<li><strong>" + userName + "&nbsp;" + df.format(planWorkMessage.getCreatedDate()) + "</strong>：" 
				+ EncodeUtils.htmlEscape(planWorkMessage.getContent()) + "</li>");
		    }
		    tip.append("</ul>");
		}
		mapContentTips.put(id, tip.toString());
	    }
	}
	
	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 提醒功能
	 * @return
	 * @throws Exception
	 */
	public String remind() throws Exception {
		prepareModel();
	    if (planWorkManager.sendRemindEmail(entity,SpringSecurityUtils.getCurrentUiid())) {
		planOperationLogManager.addWorkPlanLog(entity.getPlanWorkId(), entity.getSerialNumber() + entity.getSerialOrder(),
				PlanOperationLogManager.OPERATION_TYPE_UPDATE, entity.getOrgCd(), "发送了一封提醒邮件");
		Struts2Utils.renderText("done");
	    }
	    
	    return null;
	}
	
	/**
	 * 获取机构信息
	 * 
	 * @return
	 */
	public List<WsPlasOrg> getUaapOrgs() {
		return uaapOrgs;
	}

	public PlanWork getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public PlanWorkMessage getMessage() {
		return message;
	}

	public void setMessage(PlanWorkMessage message) {
		this.message = message;
	}

	public List<WsPlasOrg> getOrgCenterList() {
		return orgCenterList;
	}

	public void setOrgCenterList(List<WsPlasOrg> orgCenterList) {
		this.orgCenterList = orgCenterList;
	}

	public List<WsPlasOrg> getBigCenterOrgList() {
		return bigCenterOrgList;
	}

	public void setBigCenterOrgList(List<WsPlasOrg> bigCenterOrgList) {
		this.bigCenterOrgList = bigCenterOrgList;
	}

	public List<List<WsPlasOrg>> getSmallCenterOrgList() {
		return smallCenterOrgList;
	}

	public void setSmallCenterOrgList(List<List<WsPlasOrg>> smallCenterOrgList) {
		this.smallCenterOrgList = smallCenterOrgList;
	}

	public Map<String, String> getMapTargetPointCd() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.WORKPLAN_TAR_POINT);
	}

	public int getIf_search_all() {
		return if_search_all;
	}

	public void setIf_search_all(int ifSearchAll) {
		if_search_all = ifSearchAll;
	}

	public String getOrderStr1() {
		return orderStr1;
	}

	public void setOrderStr1(String orderStr1) {
		this.orderStr1 = orderStr1;
	}

	public String getOrderStr2() {
		return orderStr2;
	}

	public void setOrderStr2(String orderStr2) {
		this.orderStr2 = orderStr2;
	}

	public String getOrderDir1() {
		return orderDir1;
	}

	public void setOrderDir1(String orderDir1) {
		this.orderDir1 = orderDir1;
	}

	public String getOrderDir2() {
		return orderDir2;
	}

	public void setOrderDir2(String orderDir2) {
		this.orderDir2 = orderDir2;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer[] getChkOrders() {
		return chkOrders;
	}

	public void setChkOrders(Integer[] chkOrders) {
		this.chkOrders = chkOrders;
	}

	public String[] getChkIds() {
		return chkIds;
	}

	public void setChkIds(String[] chkIds) {
		this.chkIds = chkIds;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public String getFgCenterCd() {
		return fgCenterCd;
	}

	public void setFgCenterCd(String fgCenterCd) {
		this.fgCenterCd = fgCenterCd;
	}

	public String getCenterCd() {
		return centerCd;
	}

	public void setCenterCd(String centerCd) {
		this.centerCd = centerCd;
	}

	public List<PlanWorkMessage> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<PlanWorkMessage> messageList) {
		this.messageList = messageList;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public int getNow_year() {
		return now_year;
	}

	public void setNow_year(int nowYear) {
		now_year = nowYear;
	}

	public int getNow_month_of_year() {
		return now_month_of_year;
	}

	public void setNow_month_of_year(int nowMonthOfYear) {
		now_month_of_year = nowMonthOfYear;
	}

	public String getEntityTmpId() {
		return entityTmpId;
	}

	public void setEntityTmpId(String entityTmpId) {
		this.entityTmpId = entityTmpId;
	}

	public Map<String, List<WsPlasOrg>> getOrgMap() {
		return orgMap;
	}

	public Page<PlanWorkSnap> getPageHistory() {
		return pageHistory;
	}

	public String getIsEditOrg() {
		return isEditOrg;
	}

	public void setIsEditOrg(String isEditOrg) {
		this.isEditOrg = isEditOrg;
	}
	public Map<String, String> getMapContentTips() {
		return mapContentTips;
	}

	public void setMapContentTips(Map<String, String> mapContentTips) {
		this.mapContentTips = mapContentTips;
	}

	public boolean isIf_in_attention() {
		return if_in_attention;
	}

	public void setIf_in_attention(boolean if_in_attention) {
		this.if_in_attention = if_in_attention;
	}

	public String getOpened_entityid() {
		return opened_entityid;
	}

	public void setOpened_entityid(String opened_entityid) {
		this.opened_entityid = opened_entityid;
	}

	public Map<String, String> getAttentionMap() {
		return attentionMap;
	}

	public void setAttentionMap(Map<String, String> attentionMap) {
		this.attentionMap = attentionMap;
	}

	public String getIsAttentioned() {
		return isAttentioned;
	}

	public void setIsAttentioned(String isAttentioned) {
		this.isAttentioned = isAttentioned;
	}

	public Map<String, String> getAttentionMapUnread() {
		return attentionMapUnread;
	}

	public void setAttentionMapUnread(Map<String, String> attentionMapUnread) {
		this.attentionMapUnread = attentionMapUnread;
	}
	public int getSerialOrder() {
		return serialOrder;
	}

	public void setSerialOrder(int serialOrder) {
		this.serialOrder = serialOrder;
	}

	public String getMyTask() {
		return myTask;
	}

	public void setMyTask(String myTask) {
		this.myTask = myTask;
	}
}
