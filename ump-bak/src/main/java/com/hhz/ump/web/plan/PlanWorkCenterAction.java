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
import com.hhz.ump.dao.oa.OaAllAttentionManager;
import com.hhz.ump.dao.plan.CostExecPlanDetailManager;
import com.hhz.ump.dao.plan.PlanExecPlanDetailManager;
import com.hhz.ump.dao.plan.PlanOperationLogManager;
import com.hhz.ump.dao.plan.PlanWorkCenterManager;
import com.hhz.ump.dao.plan.PlanWorkCenterMessageManager;
import com.hhz.ump.dao.plan.PlanWorkCenterMonthManager;
import com.hhz.ump.entity.plan.PlanWorkCenter;
import com.hhz.ump.entity.plan.PlanWorkCenterMessage;
import com.hhz.ump.entity.plan.PlanWorkCenterMonth;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DictContants;
import com.hhz.uums.entity.ws.WsPlasOrg;

/**
 * 工作计划
 * @author 卢俊云 2010-3-26
 */
@Namespace("/plan")
@Results( {
		@Result(name = CrudActionSupport.RELOAD, location = "planWorkCenter.action", type = "redirect", params = { "centerCd", "${centerCd}" }),
		@Result(name = CrudActionSupport.RELOAD_SUB, location = "planWorkCenter.action", type = "redirect"),
		@Result(name = "admin", location = "plan-work-center-main!list.action", type = "redirect"),
		@Result(name = "manager", location = "plan-work-center-main!list.action", type = "redirect"),
		@Result(name = "user", location = "plan-center-query!queryPlanCenter.action", type = "redirect"),
		@Result(name = "userDc", location = "plan-execution-plan!getCenterPlanRel.action", type = "redirect", params = { "projectCd", "${centerCd}" }),
		@Result(name = "view", location = "plan-work-center!getAllPlan.action", type = "redirect", params = { "centerCd", "${centerCd}" }) })
public class PlanWorkCenterAction extends CrudActionSupport<PlanWorkCenter> {
	private static final long serialVersionUID = 8071052318603147332L;

	public static final String STATUS_CD_WEIQUEREN = "0"; // 未确认

	public static final String STATUS_CD_JINXINGZHONG = "1"; // 进行中

	public static final String STATUS_CD_SHENQINGSHANCHU = "2"; // 预完成

	public static final String STATUS_CD_WANCHENG = "3"; // 完成

	public static final String STATUS_CD_SHANGCHU = "4"; // 删除

	public static final String STATUS_CD_YINCANG = "5"; // 隐藏
	
	public static final String STATUS_CD_NO_THIS = "7"; 		// 非本月任务
	public static final String STATUS_CD_SUSPEND = "8"; 		// 延期
	public static final String STATUS_CD_NO_ASSESS = "9"; 		// 非考核性延期

	private PlanWorkCenter entity;

	private PlanWorkCenterMessage message;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private PlanWorkCenterMonthManager planWorkCenterMonthManager;

	@Autowired
	private PlanOperationLogManager planOperationLogManager;
	
	@Autowired
	private OaAllAttentionManager oaAllAttentionManager;

	private String operationlogType;
	
	private boolean ifFromDaiban = false;	//是否来自首页的待办事项

	private int serialOrder;

	private String content;

	private String area;

	private Date targetDate;

	private boolean if_in_attention;	//是否由关注引进本模块
	private String opened_entityid;	//直接打开的entityId
	private Map<String, String> attentionMap;
	private String isAttentioned;	//修改的记录是否是被关注的，这里直接传这个参数可以不用读取关注表的map出来
	private Map<String,String> attentionMapUnread;	//未读的关注id，关注列表时用，用来判断关注是否已读
	
	private String if_in_center;	//是否在中心搜索内，如果在中心内则内容前面显示部门。refreshMain方法用
	
	private String[] chkIds;

	private Integer[] chkOrders;

	private String principal;

	@Autowired
	private PlanWorkCenterManager planWorkCenterManager;

	@Autowired
	private PlanWorkCenterMessageManager planWorkCenterMessageManager;

	private List<WsPlasOrg> uaapOrgs;

	private String fgCenterCd;

	// 小中心列表
	private List<WsPlasOrg> orgCenterList;

	private List<PlanWorkCenterMessage> messageList;

	// 大中心清单/小中心清单bigCenterOrgList
	private List<WsPlasOrg> bigCenterOrgList;

	private List<List<WsPlasOrg>> smallCenterOrgList;

	private String orderStr1; // 排序字段1
	private String orderStr2; // 排序字段2
	private String orderDir1; // 排序顺序1
	private String orderDir2; // 排序顺序2
	
	private int planYear;	//年
	private int planMonth;	//月

	private int if_search_all; // 是否搜索全部

	private String centerCd;

	private int now_year = 0; // 当前的年份
	private int now_month = 0; // 当前的年份

	private int now_month_of_year = 0; // 当前的周数
	
	private String myTask;	//是否从邮件报告中进入，如果是搜索我的记录

	/**
	 * 新增记录时，附件上传使用的临时ID
	 */
	private String entityTmpId;

	private Map<String, Map<String,List<WsPlasOrg>>> orgMap;

	private String isEditOrg;
	
	private Map<String, String> mapContentTips = new HashMap<String, String>();	//保存content的title,包含content和前三条留言记录
	
	private String costModule;

	@Autowired
	private CostExecPlanDetailManager costExecPlanDetailManager;
	@Autowired
	private PlanExecPlanDetailManager planExecPlanDetailManager;
	
	
	/**
	 * 计划管理统一入口
	 * 
	 * @return
	 * @throws Exception
	 */
	public String main() throws Exception {
		centerCd = SpringSecurityUtils.getCurrentCenterCd();
		String result = null;
		if (SpringSecurityUtils.hasRole(Constants.A_PLAN_WORK_ADMIN)) {
			result = "admin";
		} else if (SpringSecurityUtils.hasRole(Constants.A_PLAN_WORK_MANAGER)) {
			result = "manager";
		} else if (SpringSecurityUtils.hasRole(Constants.A_PLAN_WORK_USER)) {
			result = "user";
		} else if (SpringSecurityUtils.hasRole(Constants.A_PLAN_WORK_USER_DC)) {
			result = "userDc";
		} else if (SpringSecurityUtils.hasRole(Constants.A_PLAN_WORK_VIEW)) {
			result = "view";
		}
		return result;
	}
	
	
	/**
	 * 从执行计划发过来的新增的任务。如果没有已经打开的中心内部任务标签，则新生成一个中心内部任务标签
	 * @return
	 * @throws Exception
	 */
	public String addFromPlanExec() throws Exception{
		String resOrgCd = Struts2Utils.getRequest().getParameter("resOrgCd");
		String planExecId = Struts2Utils.getRequest().getParameter("planExecId");
		String addFromType = Struts2Utils.getRequest().getParameter("addFromType");
		Map<String, String> map =  new HashMap<String, String>();
		if("1".equalsIgnoreCase(addFromType)){
			map = costExecPlanDetailManager.getDetailBriefMap(planExecId);
		}else if("2".equalsIgnoreCase(addFromType)){
			map = planExecPlanDetailManager.getDetailBriefMap(planExecId);
		}
		String projectCd = map.get("projectCd");
		String projectName = map.get("projectName");
		String planName = map.get("planName");
		String nodeName = map.get("nodeName");
		String realStartDate = map.get("realStartDate");
		String realEndDate = map.get("realEndDate");
		String scheduleStartDate = map.get("scheduleStartDate");
		String scheduleEndDate = map.get("scheduleEndDate");
		String statusCd = map.get("statusCd");
		String statusCd2 = "";
		if("0".equalsIgnoreCase(statusCd)){
			statusCd2 = "1";
		}else if("1".equalsIgnoreCase(statusCd)){
			statusCd2 = "2";
		}else if("2".equalsIgnoreCase(statusCd)){
			statusCd2 = "3";
		}
		String content = "[项目成本计划]";
		if(null!=planName && !"".equalsIgnoreCase(planName)){
			content += "业态:"+planName+";";
		}
		if(null!=nodeName && !"".equalsIgnoreCase(nodeName)){
			content += "节点:"+nodeName+";";
		}
		if(null!=realStartDate && !"".equalsIgnoreCase(realStartDate)){
			content += "实际开始时间:"+realStartDate+";";
		}
		if(null!=realEndDate && !"".equalsIgnoreCase(realEndDate)){
			content += "实际完成时间:"+realEndDate+";";
		}
		if(null!=scheduleStartDate && !"".equalsIgnoreCase(scheduleStartDate)){
			content += "计划开始时间:"+scheduleStartDate+";";
		}
		
		Struts2Utils.getRequest().setAttribute("new_planExecId", planExecId);
		Struts2Utils.getRequest().setAttribute("new_projectCd", projectCd);
		Struts2Utils.getRequest().setAttribute("new_content", content);
		Struts2Utils.getRequest().setAttribute("new_targetDate", scheduleEndDate);
		Struts2Utils.getRequest().setAttribute("new_statusCd", statusCd);
		Struts2Utils.getRequest().setAttribute("new_addFromType", addFromType);
		Struts2Utils.getRequest().setAttribute("new_statusCd", statusCd2);
		centerCd = resOrgCd;
		return getAllPlan();
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		planWorkCenterManager.deletePlanWorkCenter(getId());
		addActionMessage(getText("common.success"));
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		try {
			planWorkCenterManager.deleteBatch(getIds());
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
		if("undefined".equalsIgnoreCase(from_centerCd)){
			from_centerCd = "";
		}
		if (null != from_centerCd && !"".equalsIgnoreCase(from_centerCd)) {
			centerCd = from_centerCd;
		} else {
			if(null==centerCd || "".equalsIgnoreCase(centerCd) || "undefined".equalsIgnoreCase(centerCd)){
				centerCd = SpringSecurityUtils.getCurrentDeptCd();
			}
		}
		now_year = DateOperator.getYear(new Date());
		now_month = DateOperator.getMonth12(new Date());
		try{
			//如果从待办事项进入，自动判断要打开那个月份的记录
			if(StringUtils.isNotBlank(opened_entityid)){
				int now_val = now_year*12+now_month;
				int min_dis = 0;
				PlanWorkCenter pw = planWorkCenterManager.getEntity(opened_entityid);
				List<PlanWorkCenterMonth> pwms = pw.getPlanWorkCenterMonths();
				for(int i=0; null!=pwms && i<pwms.size(); i++){
					PlanWorkCenterMonth pwm = pwms.get(i);
					int myYear = pwm.getPlanYear().intValue();
					int myMonth = pwm.getPlanMonth().intValue();
					int myVal = myYear*12+myMonth;
					if(1==pwms.size() || myVal==now_val){
						now_year = myYear;
						now_month = myMonth;
						break;
					}else{
						int myDis = Math.abs(myVal-now_val);
						if(myDis>min_dis && 0!=min_dis){
							now_year = myYear;
							now_month = myMonth;
							break;
						}else{
							min_dis = myDis;
						}
					}
				}
			}
		}catch(Exception e){}
		return SUCCESS;
	}
	
	/*
	 * 获取当前用户指定类型的机构列表
	 */
	public List<WsPlasOrg> getMyOrgByType(String orgTypeCd){
		List<WsPlasOrg> relaOrgs = PlasCache.getRelPosBubbleOrgCdList(SpringSecurityUtils.getCurrentAcctId(),"");
		List<WsPlasOrg> returnList = new ArrayList<WsPlasOrg>();
		for(WsPlasOrg wpo : relaOrgs){
			if(wpo.getOrgTypeCd().equalsIgnoreCase(orgTypeCd)){
				returnList.add(wpo);
			}
		}
		return returnList;
	}
	
	/*
	 * 两个数组判断，lista是否包含listb中的任何一项
	 */
	public boolean ifContain(List<WsPlasOrg> lista, List<WsPlasOrg> listb){
		boolean returnValue = false;
		try{
			for(WsPlasOrg b : listb){
				if(-1 != lista.indexOf(b)){
					returnValue = true;
					break;
				}
			}
		}catch(Exception e){}
		return returnValue;
	}
	
	/*
	 * 判断lista是否包含b
	 */
	public boolean ifContain(List<WsPlasOrg> lista, WsPlasOrg b){
		boolean returnValue = false;
		try{
			if(-1 != lista.indexOf(b)){
				returnValue = true;
			}
		}catch(Exception e){}
		return returnValue;
	}

	/**
	 * 按照职位，获得分管、中心、部门的列表
	 * @return
	 */
	private Map<String, Map<String,List<WsPlasOrg>>> buildOrgMap() {
		Map<String,String> mapCenterSelect = new LinkedHashMap<String,String>();
		Map<String, Map<String,List<WsPlasOrg>>> mapFg = new LinkedHashMap<String, Map<String,List<WsPlasOrg>>>();
		try{
			if(SpringSecurityUtils.hasRole("A_PLAN_CENTER_ADMIN")
					||SpringSecurityUtils.hasRole("A_PLAN_CENTER_VIEW")){
				//如果是管理员，或者全局浏览用户，读取全部机构(最多到三级)
				List<WsPlasOrg> fgs = PlasCache.getOrgListByTypeCd(Constants.ORG_TYPE_CD_FG);
				for (WsPlasOrg fg : fgs) {
					Map<String, List<WsPlasOrg>> mapZx = new LinkedHashMap<String, List<WsPlasOrg>>();
					List<WsPlasOrg> zxs = PlasCache.getLogicalDirectOrgListByOrgCd(fg.getOrgCd());	//取得分管下的机构
					if (null != zxs && zxs.size() > 0) {
						for (WsPlasOrg zx : zxs) {
							List<WsPlasOrg> bms = PlasCache.getLogicalDirectOrgListByOrgCd(zx.getOrgCd());	//取得三级机构
							if (null != bms && bms.size() > 0) {
								mapZx.put(zx.getOrgCd(), bms);
							}else{
								//如果中心下无下属机构，构造该中心和下属的部门结构为自己本身
								List<WsPlasOrg> bmList = new ArrayList<WsPlasOrg>();
								bmList.add(zx);
								mapZx.put(zx.getOrgCd(), bmList);
							}
							mapCenterSelect.put(zx.getOrgCd(), zx.getOrgName());
						}
						mapFg.put(fg.getOrgName(), mapZx);
					}else{
						//如果分管下无下属机构，构造该分管和下属的中心以及部门结构为自己本身
						List<WsPlasOrg> bmList = new ArrayList<WsPlasOrg>();
						bmList.add(fg);
						mapZx.put(fg.getOrgCd(),bmList);
						mapFg.put(fg.getOrgName(), mapZx);
					}
				}
			}else{
				//读取用户具有权限的机构
				List<WsPlasOrg> myOrgs = PlasCache.getRelPosBubbleOrgCdList(SpringSecurityUtils.getCurrentAcctId(),"");
				boolean if_hangye = false;	//如果是行业集团，中心内部任务在行业集团范围内都可看
				boolean if_shangye = false;	//如果是商业集团，中心内部任务在商业集团范围内都可看
				for(int i=0;null!=myOrgs&&i<myOrgs.size();i++){
					WsPlasOrg myOrg = myOrgs.get(i);
					if(myOrg.getOrgName().equalsIgnoreCase("宝龙行业")){
						if_hangye = true;
					}
					if(myOrg.getOrgName().equalsIgnoreCase("宝龙商业")){
						//if_shangye = true;
						//商业不加入透明判断
					}
				}
				List<WsPlasOrg> fgs = getMyOrgByType(Constants.ORG_TYPE_CD_FG);
				for (WsPlasOrg fg : fgs) {
					Map<String, List<WsPlasOrg>> mapZx = new LinkedHashMap<String, List<WsPlasOrg>>();
					List<WsPlasOrg> zxs = PlasCache.getLogicalDirectOrgListByOrgCd(fg.getOrgCd());	//取得分管下的机构
					if (null != zxs && zxs.size() > 0) {
						if(!ifContain(zxs, myOrgs)
								||(fg.getOrgName().equalsIgnoreCase("宝龙行业")&&if_hangye)
								||(fg.getOrgName().equalsIgnoreCase("宝龙商业")&&if_shangye)){
							//如果二级下属机构中无用户的上级机构，说明用户直接在分管下，读取全部二级机构
							//或者属于宝龙行业，宝龙商业下的也都显示
							for (WsPlasOrg zx : zxs) {
								List<WsPlasOrg> bms = PlasCache.getLogicalDirectOrgListByOrgCd(zx.getOrgCd());	//取得三级机构
								if (null != bms && bms.size() > 0) {
									mapZx.put(zx.getOrgCd(), bms);
								}else{
									//如果中心下无下属机构，构造该中心和下属的部门结构为自己本身
									List<WsPlasOrg> bmList = new ArrayList<WsPlasOrg>();
									bmList.add(zx);
									mapZx.put(zx.getOrgCd(), bmList);
								}
								mapCenterSelect.put(zx.getOrgCd(), zx.getOrgName());
							}
						}else{
							for (WsPlasOrg zx : zxs) {
								if(ifContain(myOrgs, zx)){
									List<WsPlasOrg> bms = PlasCache.getLogicalDirectOrgListByOrgCd(zx.getOrgCd());	//取得三级机构
									if (null != bms && bms.size() > 0) {
										if(!ifContain(bms,myOrgs)){
											//如果三级下属机构中无用户的上级机构，说明用户直接在二级机构下，读取全部三级机构
											mapZx.put(zx.getOrgCd(), bms);
										}else{
											for (WsPlasOrg bm : bms){
												//if(ifContain(myOrgs, bm)){
												mapZx.put(zx.getOrgCd(), bms);
												//}
											}
										}
									}else{
										//如果中心下无下属机构，构造该中心和下属的部门结构为自己本身
										List<WsPlasOrg> bmList = new ArrayList<WsPlasOrg>();
										bmList.add(zx);
										mapZx.put(zx.getOrgCd(), bmList);
									}
									mapCenterSelect.put(zx.getOrgCd(), zx.getOrgName());
								}
							}
						}
						mapFg.put(fg.getOrgName(), mapZx);
					}else{
						//如果分管下无下属机构，构造该分管和下属的中心以及部门结构为自己本身
						List<WsPlasOrg> bmList = new ArrayList<WsPlasOrg>();
						bmList.add(fg);
						mapZx.put(fg.getOrgCd(),bmList);
						mapFg.put(fg.getOrgName(), mapZx);
					}
				}
			}
		}catch(Exception e){}

		if("shisn".equalsIgnoreCase(SpringSecurityUtils.getCurrentUiid())){
			mapFg = new LinkedHashMap<String, Map<String,List<WsPlasOrg>>>();
			WsPlasOrg tempOrg = new WsPlasOrg();
			Map<String, List<WsPlasOrg>> map2 = new LinkedHashMap<String, List<WsPlasOrg>>();
			List<WsPlasOrg> orgList = new ArrayList<WsPlasOrg>();
			
			tempOrg.setOrgCd("155");
			tempOrg.setOrgName("行政人事部");
			orgList.add(tempOrg);
			
			tempOrg = new WsPlasOrg();
			tempOrg.setOrgCd("156");
			tempOrg.setOrgName("财务部");
			orgList.add(tempOrg);
			
			tempOrg = new WsPlasOrg();
			tempOrg.setOrgCd("154");
			tempOrg.setOrgName("招商中心");
			orgList.add(tempOrg);
			
			tempOrg = new WsPlasOrg();
			tempOrg.setOrgCd("157");
			tempOrg.setOrgName("营运中心");
			orgList.add(tempOrg);
			
			tempOrg = new WsPlasOrg();
			tempOrg.setOrgCd("869");
			tempOrg.setOrgName("总工办");
			orgList.add(tempOrg);
			
			tempOrg = new WsPlasOrg();
			tempOrg.setOrgCd("439");
			tempOrg.setOrgName("企划部");
			orgList.add(tempOrg);
			
			map2.put("153",orgList);
			mapCenterSelect.put("153", "宝龙商业");
			mapFg.put("宝龙商业", map2);
			
			map2 = new LinkedHashMap<String, List<WsPlasOrg>>();
			orgList = new ArrayList<WsPlasOrg>();
			
			tempOrg = new WsPlasOrg();
			tempOrg.setOrgCd("141");
			tempOrg.setOrgName("综合部");
			orgList.add(tempOrg);
			
			tempOrg = new WsPlasOrg();
			tempOrg.setOrgCd("142");
			tempOrg.setOrgName("招标部");
			orgList.add(tempOrg);
			
			tempOrg = new WsPlasOrg();
			tempOrg.setOrgCd("144");
			tempOrg.setOrgName("造价部");
			orgList.add(tempOrg);
			
			map2.put("17",orgList);
			mapCenterSelect.put("17", "成本控制中心");
			mapFg.put("成本控制中心", map2);
		}
		if("yecm".equalsIgnoreCase(SpringSecurityUtils.getCurrentUiid())){
			mapFg = new LinkedHashMap<String, Map<String,List<WsPlasOrg>>>();
			WsPlasOrg tempOrg = new WsPlasOrg();
			Map<String, List<WsPlasOrg>> map2 = new LinkedHashMap<String, List<WsPlasOrg>>();
			List<WsPlasOrg> orgList = new ArrayList<WsPlasOrg>();
			
			tempOrg.setOrgCd("147");
			tempOrg.setOrgName("监审部");
			orgList.add(tempOrg);
			
			map2.put("监审部",orgList);
			mapFg.put("董事", map2);
			
			orgList = new ArrayList<WsPlasOrg>();
			
			tempOrg = new WsPlasOrg();
			tempOrg.setOrgCd("868");
			tempOrg.setOrgName("公共关系部");
			orgList.add(tempOrg);
			
			map2.put("公共关系部",orgList);
		}
		//新增黄白杨财务部权限（临时）
		if("huangby1".equalsIgnoreCase(SpringSecurityUtils.getCurrentUiid())){
			mapFg = new LinkedHashMap<String, Map<String,List<WsPlasOrg>>>();
			WsPlasOrg tempOrg = new WsPlasOrg();
			Map<String, List<WsPlasOrg>> map2 = new LinkedHashMap<String, List<WsPlasOrg>>();
			List<WsPlasOrg> orgList = new ArrayList<WsPlasOrg>();
			
			tempOrg.setOrgCd("882");
			tempOrg.setOrgName("财务部");
			orgList.add(tempOrg);
			
			map2.put("财务部",orgList);
			mapFg.put("财务部", map2);
			isEditOrg = "1";
		}
		
		//如果是宝龙商业，增加"商业工作联系平台"
		try{
			for(int i=0;null!=mapFg && i<mapFg.size(); i++){
				Map<String,List<WsPlasOrg>> mapZx = mapFg.get("宝龙商业");
				WsPlasOrg tempOrg = new WsPlasOrg();
				List<WsPlasOrg> orgList = new ArrayList<WsPlasOrg>();
				
				tempOrg.setOrgCd("planWorkCenterBis");
				tempOrg.setOrgName("商业工作联系平台");
				orgList.add(tempOrg);
				
				mapZx.put("planWorkCenterBis",orgList);
				mapCenterSelect.put("planWorkCenterBis", "商业工作联系平台");
			}
		}catch(Exception e){}
		
		Struts2Utils.getRequest().setAttribute("mapCenterSelect", mapCenterSelect);	//这个用来显示中心的名称，和orgList中的key:orgCd对应的名称
		return mapFg;
	}


	private void buildFilterOrder(Page page) {

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
			page.setOrderBy("targetDate,createdDate");
			page.setOrder(Page.ASC + "," + Page.ASC);
		}
	}

	@Override
	public String list() throws Exception {
		//page.setPageSize(30);
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		if (1 == if_search_all) {
			page.setPageSize(999);
		}
		String search_orgCd = Struts2Utils.getRequest().getParameter("search_orgCd");
		if(!if_in_attention){
			//正常搜索
			/*
			String search_statusCd = Struts2Utils.getParameter("search_statusCd");
			if (search_statusCd != null && !search_statusCd.equalsIgnoreCase("")) {
				filters.add(new PropertyFilter("EQS_statusCd", search_statusCd));
			} else {
				filters.add(new PropertyFilter("INA_statusCd", new Object[] { "0", "1", "2" }));
			}
			*/

			filters.add(new PropertyFilter("EQM_planWorkCenterMonths.planYear", now_year));
			filters.add(new PropertyFilter("EQM_planWorkCenterMonths.planMonth", now_month));
			String search_statusCd = Struts2Utils.getParameter("search_statusCd");
			if (search_statusCd != null && !search_statusCd.equalsIgnoreCase("")) {
				filters.add(new PropertyFilter("EQS_planWorkCenterMonths.statusCd",search_statusCd));
			} else {
				filters.add(new PropertyFilter("INA_planWorkCenterMonths.statusCd",
						new Object[] { "0", "1", "2", "3", "7", "8", "9","10" }));
			}
			
			if((null!=myTask && "true".equalsIgnoreCase(myTask))
					||(null!=search_orgCd && "ifFromDaiban".equalsIgnoreCase(search_orgCd))){
				//从邮件报表中搜索属于我自己的，或者从“我的待办事项”里搜索我自己的
				filters.add(new PropertyFilter("LIKES_principal_OR_corUser", SpringSecurityUtils.getCurrentUiid()));
			}else{
				//商业计划平台特殊处理招商中心和营运中心
				if("154".equalsIgnoreCase(search_orgCd)){
					search_orgCd = "154,159,160,161,1047,1048,164,1049";
				}else if("157".equalsIgnoreCase(search_orgCd)){
					search_orgCd = "157,671,672";
				}
				String[] list_center = search_orgCd.split(",");
				filters.add(new PropertyFilter("INA_orgCd", list_center));
				if(list_center.length>1){
					if_in_center = "true";
				}else{
					if_in_center = "false";
				}
			}
			buildFilterOrder(page);

			page = planWorkCenterManager.findPage(page, filters);
		}else{
			//在关注里
			List list_planWorkCenter = new ArrayList();
			attentionMapUnread = new LinkedHashMap<String,String>();
			List list_unread_attention = oaAllAttentionManager.getUnreadIds("planWorkCenter", 
					SpringSecurityUtils.getCurrentUiid(), "PLAN_WORK_CENTER", "PLAN_WORK_CENTER_ID");
			for(int i=0;null!=list_unread_attention && i<list_unread_attention.size();i++){
				String attentionEntityId = (String)list_unread_attention.get(i);
				PlanWorkCenter planWorkCenter = planWorkCenterManager.getEntity(attentionEntityId);
				list_planWorkCenter.add(planWorkCenter);
				attentionMapUnread.put(attentionEntityId, "unread");
			}
			List list_read_attention = oaAllAttentionManager.getReadIds("planWorkCenter", 
					SpringSecurityUtils.getCurrentUiid(), "PLAN_WORK_CENTER", "PLAN_WORK_CENTER_ID");
			for(int i=0;null!=list_read_attention && i<list_read_attention.size();i++){
				String attentionEntityId = (String)list_read_attention.get(i);
				PlanWorkCenter planWorkCenter = planWorkCenterManager.getEntity(attentionEntityId);
				list_planWorkCenter.add(planWorkCenter);
			}
			page.setResult(list_planWorkCenter);
		}
		//关注的记录信息记录到map中，前台读取判断
		//attentionMap=oaAllAttentionManager.getMyAttention("planWorkCenter", SpringSecurityUtils.getCurrentUiid());
		
		if(null!=opened_entityid && !"".equalsIgnoreCase(opened_entityid)){
			try{
				boolean if_has_opened_entityid = false;
				PlanWorkCenter planWorkCenter2 = planWorkCenterManager.getEntity(opened_entityid);
				List<PlanWorkCenter> list_planWorkCenter = page.getResult();
				for(PlanWorkCenter planWorkCenter:list_planWorkCenter){
					if(opened_entityid.equalsIgnoreCase(planWorkCenter.getPlanWorkCenterId())){
						if_has_opened_entityid = true;
						break;
					}
				}
				if(!if_has_opened_entityid){
					list_planWorkCenter.add(0, planWorkCenter2);
					page.setResult(list_planWorkCenter);
				}
				if((null!=search_orgCd && "ifFromDaiban".equalsIgnoreCase(search_orgCd))){
					//如果是看待办，是否显示成商业计划平台，由选择的记录判断
					centerCd = planWorkCenter2.getOrgCd();
				}
			}catch(Exception e){}
		}
		try{
			//来自 执行计划的判断
			List<PlanWorkCenter> list_planWorkCenter = page.getResult();
			for(int i=0;i<list_planWorkCenter.size();i++){
				PlanWorkCenter planWorkCenter = list_planWorkCenter.get(i);
				String addFromType = planWorkCenter.getAddFromType();
				if(null!=addFromType && !"0".equalsIgnoreCase(addFromType)){
					String plan_exec_id = planWorkCenter.getPlanExecId();
					Map<String, String> map =  new HashMap<String, String>();
					if("1".equalsIgnoreCase(addFromType)){
						map = costExecPlanDetailManager.getDetailBriefMap(plan_exec_id);
					}else if("2".equalsIgnoreCase(addFromType)){
						map = planExecPlanDetailManager.getDetailBriefMap(plan_exec_id);
					}
					
					String projectCd = map.get("projectCd");
					String projectName = map.get("projectName");
					String planName = map.get("planName");
					String nodeName = map.get("nodeName");
					String realStartDate = map.get("realStartDate");
					String realEndDate = map.get("realEndDate");
					String scheduleStartDate = map.get("scheduleStartDate");
					String scheduleEndDate = map.get("scheduleEndDate");
					String statusCd = map.get("statusCd");
					String statusCd2 = planWorkCenter.getStatusCd();
					if(this.STATUS_CD_WEIQUEREN.equalsIgnoreCase(statusCd)
							||this.STATUS_CD_JINXINGZHONG.equalsIgnoreCase(statusCd)
							||this.STATUS_CD_SHENQINGSHANCHU.equalsIgnoreCase(statusCd)
							||this.STATUS_CD_WANCHENG.equalsIgnoreCase(statusCd)){
						//如果是非隐藏或者非删除状态，要根据执行计划的状态来判断
						if(this.STATUS_CD_WEIQUEREN.equalsIgnoreCase(statusCd)){
							statusCd2 = this.STATUS_CD_JINXINGZHONG;
						}else if(this.STATUS_CD_JINXINGZHONG.equalsIgnoreCase(statusCd)){
							statusCd2 = this.STATUS_CD_SHENQINGSHANCHU;
						}else if(this.STATUS_CD_SHENQINGSHANCHU.equalsIgnoreCase(statusCd)){
							statusCd2 = this.STATUS_CD_WANCHENG;
						}
					}
					String content = "[项目成本计划]";
					if(null!=projectName && !"".equalsIgnoreCase(projectName)){
						content += "业态:"+planName+";";
					}
					if(null!=nodeName && !"".equalsIgnoreCase(nodeName)){
						content += "节点:"+nodeName+";";
					}
					if(null!=realStartDate && !"".equalsIgnoreCase(realStartDate)){
						content += "实际开始时间:"+realStartDate+";";
					}
					if(null!=realEndDate && !"".equalsIgnoreCase(realEndDate)){
						content += "实际完成时间:"+realEndDate+";";
					}
					if(null!=scheduleStartDate && !"".equalsIgnoreCase(scheduleStartDate)){
						content += "计划开始时间:"+scheduleStartDate+";";
					}
					planWorkCenter.setPrincipal(projectCd);
					planWorkCenter.setContent(content);
					planWorkCenter.setTargetDate(DateOperator.parse(scheduleEndDate));
					planWorkCenter.setStatusCd(statusCd2);
					list_planWorkCenter.set(i, planWorkCenter);
				}
			}
			page.setResult(list_planWorkCenter);
		}catch(Exception e){}
		
		//如果是成本，取项目公司的map
		if("17".equalsIgnoreCase(centerCd)||"141".equalsIgnoreCase(centerCd)
				||"142".equalsIgnoreCase(centerCd)||"143".equalsIgnoreCase(centerCd)||"144".equalsIgnoreCase(centerCd)){
			Struts2Utils.getRequest().setAttribute("if_chengben", "true");
		}
		
		// 生成编号
		if (null == centerCd || "".equalsIgnoreCase(centerCd)) {
			centerCd = SpringSecurityUtils.getCurrentCenterCd();
		}
		if("costCtrl".equals(costModule)){
			Struts2Utils.getRequest().setAttribute("if_chengben", "true");
			centerCd = "17";
		}
		Struts2Utils.getRequest().setAttribute("newSerialOrder", planWorkCenterManager.getMaxNo(centerCd));
		Struts2Utils.getRequest().setAttribute("newSerialNumber", "ZXJH-");
		initIsEditOrg();
		page.setResult(buildAttributes(page.getResult()));
		if("costCtrl".equals(costModule))
			return "costCtrl";
		else
			return "list";
	}

	/*
	 * 判断是否具有当前部门的权限
	 */
	private void initIsEditOrg() {
		boolean if_true = false;
		if(centerCd.equalsIgnoreCase(SpringSecurityUtils.getCurrentDeptCd())){
			//如果是当前机构
			if_true=true;
		}else{
			//判断是否用户的上级机构
			//判断是否机构的
			String orgCd = SpringSecurityUtils.getCurrentCenterCd();
			if(null!=orgCd && !"".equalsIgnoreCase(orgCd)){
				List<WsPlasOrg> myOrgs = PlasCache.getLogicalDesantOrgListByOrgCd(orgCd);
				//如果不是当前机构，判断是否在用户的下级机构内
				WsPlasOrg org = null;
				for(int i=0;null!=myOrgs&&i<myOrgs.size();i++){
					org = myOrgs.get(i);
					if(centerCd.equalsIgnoreCase(org.getOrgCd())){
						if_true=true;
						break;
					}
				}
			}
		}
		List<WsPlasOrg> relaOrgs = PlasCache.getRelPosBubbleOrgCdList(SpringSecurityUtils.getCurrentAcctId(),"");
		if (relaOrgs != null) {
			for (WsPlasOrg wsUaapOrg : relaOrgs) {
				//如果在用户的上级机构内，就能编辑
				if (wsUaapOrg.getOrgCd().equals(centerCd)) {
					if_true=true;
					break;
				}
			}
		}
		if("planWorkCenterBis".equalsIgnoreCase(centerCd)
				&& !SpringSecurityUtils.hasRole("A_PLAN_CENTER_ADMIN")){
			//如果是商业工作联系平台，并且不是管理员
			if_true = false;
		}
		if("17".equalsIgnoreCase(centerCd)||"141".equalsIgnoreCase(centerCd)
				||"142".equalsIgnoreCase(centerCd)||"143".equalsIgnoreCase(centerCd)||"144".equalsIgnoreCase(centerCd)){
			//如果是成本，只有管理员有管理权限
			if(!SpringSecurityUtils.hasRole("A_PLAN_CENTER_ADMIN")){
				if_true = false;
			}
		}
		if(if_true) {
			isEditOrg = "1";
		} else {
			isEditOrg = "0";
		}
		//临时新增黄白杨的修改权限
		if("huangby1".equalsIgnoreCase(SpringSecurityUtils.getCurrentUiid())){
			isEditOrg = "1";
		}
	}
//	private void initIsEditOrg() {
//		if (SpringSecurityUtils.hasRole("A_PLAN_CENTER_PRE") || SpringSecurityUtils.hasRole("A_PLAN_CENTER_USER")) {
//			List<WsPlasOrg> relaOrgs = Util.getUaapService().getRelationCenterOrgs(SpringSecurityUtils.getCurrentUiid());
//			if (relaOrgs!=null) {
//				for (WsPlasOrg wsUaapOrg : relaOrgs) {
//					if (wsUaapOrg.getOrgTypeCd() != null && !wsUaapOrg.getOrgTypeCd().equals(Constants.ORG_TYPE_CD_FG)) {
//						if (wsUaapOrg.getOrgCd().equals(centerCd)) {
//							isEditOrg = "1";
//							break;
//						}
//					}else if(wsUaapOrg.getOrgTypeCd().equals(Constants.ORG_TYPE_CD_FG)){
//						//如果用户在分管下，判断是否是现中心所属的分管
//						WsPlasOrg wsUaapOrg2 = Util.getUaapService().getUaapOrgByOrgCd(centerCd);
//						if(wsUaapOrg2!=null && wsUaapOrg.getOrgCd().equalsIgnoreCase(wsUaapOrg2.getParentOrgCd())){
//							isEditOrg = "1";
//							break;
//						}
//					}else if(wsUaapOrg.getOrgTypeCd() != null && wsUaapOrg.getOrgTypeCd().equals(Constants.ORG_TYPE_CD_JT)){
//						isEditOrg = "1";
//						break;
//					}
//				}
//			}
//		}
//	}

	/**
	 * 添加留言
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveMessage() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planWorkCenterManager.getEntity(getId());
			PlanWorkCenterMessage wm = new PlanWorkCenterMessage();
			wm.setContent(content);
			wm.setPlanWorkCenter(entity);
			planWorkCenterMessageManager.savePlanWorkCenterMessage(wm);
			planWorkCenterManager.save(entity); //为了更新recordVersion，关注模块使用
			Struts2Utils.renderText("ok");
			try {
				planOperationLogManager.addPlanWorkCenterLog(entity.getPlanWorkCenterId(), entity.getSerialNumber() + entity.getSerialOrder(),
						PlanOperationLogManager.OPERATION_TYPE_UPDATE, wm.getPlanWorkCenter().getOrgCd(), "留言：" + content);
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
			entity = planWorkCenterManager.getEntity(getId());
			messageList = entity.getPlanWorkCenterMessages();
			// CollectionHelper.sortList(messageList, "createdDate", true);
			return "message";
		} else
			return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = planWorkCenterManager.getEntity(getId());
		} else {
			entity = new PlanWorkCenter();
			entityTmpId = RandomUtils.generateTmpEntityId();
		}
	}

	@Override
	public String save() throws Exception {
		boolean ifAdd = false;
		if(null==entity || null==entity.getPlanWorkCenterId()){
			ifAdd = true;
		}
		String statusCd = Struts2Utils.getParameter("statusCd");
		if (this.STATUS_CD_WANCHENG.equalsIgnoreCase(statusCd)) {
			entity.setEndDate(new Date());
		}
		if (this.STATUS_CD_WEIQUEREN.equalsIgnoreCase(statusCd)) {
			entity.setStatusCd("1");
		}
		planWorkCenterManager.savePlanWorkCenter(entity, entityTmpId);
		if(ifAdd){
			//如果是新增，判断目标时间和当前月份的差距，生成非本月
			Date targetDate = entity.getTargetDate();
			planYear = DateOperator.getYear(targetDate);
			planMonth = DateOperator.getMonth12(targetDate);
			saveStatusCd(entity,planYear,planMonth,STATUS_CD_JINXINGZHONG);
			/*
			Date nowDate = DateOperator.createDate(planYear, planMonth-1, 1);
			int disMonth = DateOperator.compareMonthes(nowDate, targetDate);
			if(disMonth>=0){
				//如果目标日期不小月当前月份，到最后一月增加进行中
				for(int i=0;i<disMonth;i++){
					int toYear = planYear;
					int toMonth = planMonth+i;
					if(toMonth>12){
						toMonth=1;
						toYear ++;
					}
					//取消非本月的增加
					//saveStatusCd(entity,toYear,toMonth,STATUS_CD_NO_THIS);
				}
				int toYear = planYear;
				int toMonth = planMonth+disMonth;
				if(toMonth>12){
					toMonth=1;
					toYear ++;
				}
				saveStatusCd(entity,toYear,toMonth,STATUS_CD_JINXINGZHONG);
			}
			*/
		}else{
			//如果不是新增，更新当前操作月的状态
			saveStatusCd(entity,planYear,planMonth,entity.getStatusCd());
		}
		centerCd = entity.getOrgCd();
		
		//如果是成本，取项目公司的map
		if("17".equalsIgnoreCase(centerCd)||"141".equalsIgnoreCase(centerCd)
				||"142".equalsIgnoreCase(centerCd)||"143".equalsIgnoreCase(centerCd)||"144".equalsIgnoreCase(centerCd)){
			Struts2Utils.getRequest().setAttribute("if_chengben", "true");
		}
		setId(entity.getPlanWorkCenterId());
		addActionMessage(getText("common.success"));
		Struts2Utils.renderText(String.valueOf(entity.getPlanWorkCenterId()));

		String newMessage = Struts2Utils.getParameter("newMessage");
		if(null!=newMessage && !"".equalsIgnoreCase(newMessage)){
			PlanWorkCenterMessage wm = new PlanWorkCenterMessage();
			wm.setContent(newMessage);
			wm.setPlanWorkCenter(entity);
			planWorkCenterMessageManager.savePlanWorkCenterMessage(wm);
		}
		
		if ((STATUS_CD_SUSPEND.equalsIgnoreCase(statusCd)||STATUS_CD_NO_ASSESS.equalsIgnoreCase(statusCd))
				&& 0!=planYear && 0!=planMonth) {
			//如果是延期，下个月新增一条
			int sourcePlanYear = planYear;
			int sourcePlanMonth = planMonth;
			int toPlanYear = sourcePlanYear;
			int toPlanMonth = sourcePlanMonth + 1;
			if (13 == toPlanMonth) {
				toPlanMonth = 1;
				toPlanYear++;
			}
			saveStatusCd(entity,toPlanYear,toPlanMonth,statusCd);
		}

		try {
			planOperationLogManager.addPlanWorkCenterLog(entity.getPlanWorkCenterId(), entity.getSerialNumber() + "-" + entity.getSerialOrder(),
					operationlogType, entity.getOrgCd(), "保存" + entity.getSerialNumber() + entity.getSerialOrder() + ";" + entity.getContent() + ";"
							+ DateOperator.formatDate(entity.getTargetDate(), "yyyy-MM-dd") + ";" + entity.getStatusCd());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public void prepareFetchDetail() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planWorkCenterManager.getEntity(getId());
			Struts2Utils.getRequest().setAttribute("centerCd", entity.getOrgCd());
			refreshPlanWorkCenterStatus(entity,now_year,now_month);
		}
	}

	public String fetchDetail() throws Exception {
		return "detail";
	}

	public void prepareFetchMain() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planWorkCenterManager.getEntity(getId());
			refreshPlanWorkCenterStatus(entity,now_year,now_month);
			Struts2Utils.getRequest().setAttribute("centerCd", entity.getOrgCd());

			String id = entity.getPlanWorkCenterId();
			// 鼠标放在标题上时，显示标题和最新的3条评论
			StringBuffer tip = new StringBuffer();
			tip.append("<p>" + EncodeUtils.htmlEscape(entity.getContent()) + "</p>");
			List<PlanWorkCenterMessage> planWorkCenterMessages = entity.getPlanWorkCenterMessages();
			DateFormat df = new SimpleDateFormat("MM-dd");
			if (planWorkCenterMessages.size() > 0) {
				tip.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
				int size = planWorkCenterMessages.size() >= 3 ? 3
						: planWorkCenterMessages.size();
				for (int i = 0; i < size; i++) {
					PlanWorkCenterMessage planWorkCenterMessage = planWorkCenterMessages
							.get(i);
					String userName = CodeNameUtil
							.getUserNameByCd(planWorkCenterMessage.getCreator());
					tip.append("<li><strong>"
							+ userName
							+ "&nbsp;"
							+ df.format(planWorkCenterMessage.getCreatedDate())
							+ "</strong>："
							+ EncodeUtils.htmlEscape(planWorkCenterMessage
									.getContent()) + "</li>");
				}
				tip.append("</ul>");
			}
			mapContentTips.put(id, tip.toString());
		}
	}

	public String fetchMain() throws Exception {
		String if_chengben = Struts2Utils.getRequest().getParameter("if_chengben");
		if(null!=if_chengben && "true".equalsIgnoreCase(if_chengben)){
			Struts2Utils.getRequest().setAttribute("if_chengben", if_chengben);
		}
		centerCd = entity.getOrgCd();
		//如果是成本，取项目公司的map
		if("17".equalsIgnoreCase(centerCd)||"141".equalsIgnoreCase(centerCd)
				||"142".equalsIgnoreCase(centerCd)||"143".equalsIgnoreCase(centerCd)||"144".equalsIgnoreCase(centerCd)){
			Struts2Utils.getRequest().setAttribute("if_chengben", "true");
		}
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
		for (int i = 0; null != uaapOrgs && i < uaapOrgs.size(); i++) {
			WsPlasOrg wsUaapOrg = (WsPlasOrg) uaapOrgs.get(i);
			if (wsUaapOrg.getOrgBizCd().equals("1099")) {
				DC_order = i;
				fgCenters_ordered.add(wsUaapOrg);
			}
		}
		for (int i = 0; null != uaapOrgs && i < uaapOrgs.size(); i++) {
			if (i != DC_order) {
				WsPlasOrg wsUaapOrg = (WsPlasOrg) uaapOrgs.get(i);
				fgCenters_ordered.add(wsUaapOrg);
			}
		}

		// if
		// (SpringSecurityUtils.getCurrentUaapUser().getCenterOrgCd().equals("DC"))
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
			List<WsPlasOrg> list = PlasCache.getLogicalDirectOrgListByOrgCd(orgCd, Constants.ORG_TYPE_CD_ZX);
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
			for (String planWorkCenter_id : chkIds) {
				entity = planWorkCenterManager.getEntity(planWorkCenter_id);
				entity.setStatusCd(statusCd);
				if (STATUS_CD_WANCHENG.equalsIgnoreCase(statusCd)) {
					entity.setEndDate(new Date());
				}
				planWorkCenterManager.savePlanWorkCenter(entity);
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
				String planWorkCenter_id = chkIds[i];
				entity = planWorkCenterManager.getEntity(planWorkCenter_id);
				entity.setSerialOrder(new BigDecimal(chkOrders[i]));
				planWorkCenterManager.savePlanWorkCenter(entity);
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
	private List<PlanWorkCenter> buildAttributes(List<PlanWorkCenter> planWorkCenters) {
	    String id = null;
	    try{
		for (int j=0;planWorkCenters!=null && j<planWorkCenters.size();j++) {
			PlanWorkCenter p = planWorkCenters.get(j);
			id = p.getPlanWorkCenterId();
			refreshPlanWorkCenterStatus(p,now_year,now_month);
			
			// 鼠标放在标题上时，显示标题和最新的3条评论
			StringBuffer tip = new StringBuffer();
			tip.append("<p>" + EncodeUtils.htmlEscape(p.getContent()) + "</p>");
			List<PlanWorkCenterMessage> planWorkCenterMessages = p.getPlanWorkCenterMessages();
			DateFormat df = new SimpleDateFormat("MM-dd");
			if (planWorkCenterMessages.size() > 0) {
			    tip.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
			    int size = planWorkCenterMessages.size() >= 3 ? 3 : planWorkCenterMessages.size();
			    for (int i = 0; i < size; i ++) {
			    PlanWorkCenterMessage planWorkCenterMessage = planWorkCenterMessages.get(i);
				String userName = CodeNameUtil.getUserNameByCd(planWorkCenterMessage.getCreator());
				tip.append("<li><strong>" + userName + "&nbsp;" + df.format(planWorkCenterMessage.getCreatedDate()) + "</strong>：" 
					+ EncodeUtils.htmlEscape(planWorkCenterMessage.getContent()) + "</li>");
			    }
			    tip.append("</ul>");
			}
			mapContentTips.put(id, tip.toString());
			planWorkCenters.set(j, p);
	    }
	    }catch(Exception e){}
	    return planWorkCenters;
	}

	/*
	 * 保存planWorkCenterMonth子表中的状态的方法
	 */
	public boolean saveStatusCd(PlanWorkCenter planWorkCenter,int planYear,int planMonth,String statusCd){
		boolean if_has_month = false;
		try{
			List<PlanWorkCenterMonth> pms = planWorkCenter.getPlanWorkCenterMonths();
			for(PlanWorkCenterMonth pm : pms){
				if(planYear==pm.getPlanYear().intValue()
						&&planMonth==pm.getPlanMonth().intValue()){
					pm.setStatusCd(statusCd);
					planWorkCenterMonthManager.savePlanWorkCenterMonth(pm);
					if_has_month = true;
				}
			}
		}catch(Exception e){
			if_has_month = false;
		}
		if(!if_has_month){
			//如果找不到，就新增子记录
			try{
				PlanWorkCenterMonth pm = new PlanWorkCenterMonth();
				pm.setPlanYear(new BigDecimal(planYear));
				pm.setPlanMonth(new BigDecimal(planMonth));
				pm.setStatusCd(statusCd);
				pm.setPlanWorkCenter(planWorkCenter);
				planWorkCenterMonthManager.savePlanWorkCenterMonth(pm);
				if_has_month = true;
			}catch(Exception e){
				if_has_month = false;
			}
		}
		return if_has_month;
	}
	
	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
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

	public PlanWorkCenter getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public PlanWorkCenterMessage getMessage() {
		return message;
	}

	public void setMessage(PlanWorkCenterMessage message) {
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
	
	public Map<String, String> getMapXM() {
		List<WsPlasOrg> uaapOrgs = PlasCache.getLogicalDirectOrgListByOrgCd("135",Constants.ORG_TYPE_CD_ZX);
		Map<String,String> mapXM = new LinkedHashMap<String,String>();
		//取得分管的列表，并排序
		mapXM.put("", "--选择--");
		for (WsPlasOrg wsUaapOrg : uaapOrgs) {
			mapXM.put(wsUaapOrg.getOrgCd(), wsUaapOrg.getOrgName());
		}
		mapXM.put("1", "战略");
		mapXM.put("2", "中心");
		return mapXM;
	}

	public int getIf_search_all() {
		return if_search_all;
	}
	
	/**
	 * 提醒功能
	 * @return
	 * @throws Exception
	 */
	public String remind() throws Exception {
		prepareModel();
	    if (planWorkCenterManager.sendRemindEmail(entity,SpringSecurityUtils.getCurrentUiid())) {
			planOperationLogManager.addPlanWorkCenterLog(entity.getPlanWorkCenterId(), entity.getSerialNumber() + entity.getSerialOrder(),
					PlanOperationLogManager.OPERATION_TYPE_UPDATE, entity.getOrgCd(), "发送了一封提醒邮件");
			Struts2Utils.renderText("done");
	    }
	    
	    return null;
	}

	/*
	 * 退回记录
	 */
	public String doCallback() throws Exception {
		try {
			if (getId() != null) {
				entity = planWorkCenterManager.getEntity(getId());
			}
			int sourcePlanYear = now_year;
			int sourcePlanMonth = now_month;
			int prevPlanYear = sourcePlanYear;
			int prevPlanMonth = sourcePlanMonth - 1;
			if (0 == prevPlanMonth) {
				prevPlanMonth = 12;
				prevPlanYear--;
			}
			int nextPlanYear = sourcePlanYear;
			int nextPlanMonth = sourcePlanMonth + 1;
			if (13 == nextPlanMonth) {
				nextPlanMonth = 1;
				nextPlanYear++;
			}

			boolean if_has_prev = false;
			List<PlanWorkCenterMonth> pms = entity.getPlanWorkCenterMonths();
			for(PlanWorkCenterMonth pm : pms){
				if(!(STATUS_CD_SHANGCHU.equalsIgnoreCase(pm.getStatusCd()))){
					if(prevPlanYear==pm.getPlanYear().intValue()
							&&prevPlanMonth==pm.getPlanMonth().intValue()){
						if_has_prev = true;
					}
				}
			}
			if(if_has_prev){
				//前个月必须有记录，才能驳回
				saveStatusCd(entity,prevPlanYear,prevPlanMonth,STATUS_CD_JINXINGZHONG);
				saveStatusCd(entity,sourcePlanYear,sourcePlanMonth,STATUS_CD_SHANGCHU);
				
				List<PlanWorkCenterMonth> pms2 = entity.getPlanWorkCenterMonths();
				int remark_count = 0;
				boolean if_start_count = false;
				int count_year = prevPlanYear;
				int count_month = prevPlanMonth;
				for(PlanWorkCenterMonth pm : pms2){
					//如果找到当前记录，开始倒数计算，如果连贯的记录只有一个，删除remark
					if(sourcePlanYear==pm.getPlanYear().intValue()
							&& sourcePlanMonth==pm.getPlanMonth().intValue()){
						if_start_count = true;
						continue;
					}
					if(if_start_count){
						if(!(STATUS_CD_SHANGCHU.equalsIgnoreCase(pm.getStatusCd()))
								&& !(this.STATUS_CD_NO_THIS.equalsIgnoreCase(pm.getStatusCd()))){
							if(count_year==pm.getPlanYear().intValue()
									&& count_month==pm.getPlanMonth().intValue()){
								//判断是否连贯记录，如果是，连贯计数器向上一个月，累计计数器加1
								count_month = count_month - 1;
								if (0 == count_month) {
									count_month = 12;
									count_year--;
								}
								remark_count++;
								if(remark_count>1){
									//如果累计计数器多余一个，说明不取消remark
									break;
								}
							}else{
								break;
							}
						}
					}
				}
				if(1==remark_count){
					//如果累计个数只有一个，删除remark
					entity.setRemark("");
					planWorkCenterManager.save(entity);
				}
				String newMessage = Struts2Utils.getParameter("newMessage");
				if (null != newMessage && !"".equalsIgnoreCase(newMessage)) {
					PlanWorkCenterMessage wm = new PlanWorkCenterMessage();
					wm.setContent(newMessage);
					wm.setPlanWorkCenter(entity);
					planWorkCenterMessageManager.savePlanWorkCenterMessage(wm);
				}
				Struts2Utils.renderText("success");
			}else{
				Struts2Utils.renderText("failure");
			}
		} catch (Exception e) {
			Struts2Utils.renderText("failure");
			addActionMessage(getText("common.failure"));
		}
		return null;
	}

	/*
	 * 根据planWorkCenterMonth获得statusCd刷新到planWorkCenter中
	 */
	public void refreshPlanWorkCenterStatus(PlanWorkCenter planWorkCenter,int planYear,int planMonth){
		List<PlanWorkCenterMonth> pms = planWorkCenter.getPlanWorkCenterMonths();
		String statusCd = "-1";	//如果在这个月找不到，就显示-1
		for(PlanWorkCenterMonth pm : pms){
			if(planYear==pm.getPlanYear().intValue()
					&&planMonth==pm.getPlanMonth().intValue()){
				statusCd = pm.getStatusCd();
				break;
			}
		}
		planWorkCenter.setStatusCd(statusCd);
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

	public List<PlanWorkCenterMessage> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<PlanWorkCenterMessage> messageList) {
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

	
	public Map<String, Map<String, List<WsPlasOrg>>> getOrgMap() {
		return orgMap;
	}

	public void setOrgMap(Map<String, Map<String, List<WsPlasOrg>>> orgMap) {
		this.orgMap = orgMap;
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

	public String getIf_in_center() {
		return if_in_center;
	}

	public void setIf_in_center(String if_in_center) {
		this.if_in_center = if_in_center;
	}

	public Map<String, String> getAttentionMapUnread() {
		return attentionMapUnread;
	}

	public void setAttentionMapUnread(Map<String, String> attentionMapUnread) {
		this.attentionMapUnread = attentionMapUnread;
	}


	public String getMyTask() {
		return myTask;
	}

	public void setMyTask(String myTask) {
		this.myTask = myTask;
	}

	public int getSerialOrder() {
		return serialOrder;
	}

	public void setSerialOrder(int serialOrder) {
		this.serialOrder = serialOrder;
	}


	public String getCostModule() {
		return costModule;
	}

	public void setCostModule(String costModule) {
		this.costModule = costModule;
	}

	public boolean isIfFromDaiban() {
		return ifFromDaiban;
	}

	public void setIfFromDaiban(boolean ifFromDaiban) {
		this.ifFromDaiban = ifFromDaiban;
	}


	public int getPlanYear() {
		return planYear;
	}


	public void setPlanYear(int planYear) {
		this.planYear = planYear;
	}


	public int getPlanMonth() {
		return planMonth;
	}


	public void setPlanMonth(int planMonth) {
		this.planMonth = planMonth;
	}


	public int getNow_month() {
		return now_month;
	}


	public void setNow_month(int nowMonth) {
		now_month = nowMonth;
	}
	
}
