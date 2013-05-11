/**
 * 
 */
package com.hhz.ump.web.plan;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.hhz.ump.dao.plan.PlanWork2Manager;
import com.hhz.ump.dao.plan.PlanWork2MessageManager;
import com.hhz.ump.dao.plan.PlanWork2MonthManager;
import com.hhz.ump.dao.plan.PlanWork2WeightManager;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.plan.PlanWork2;
import com.hhz.ump.entity.plan.PlanWork2Message;
import com.hhz.ump.entity.plan.PlanWork2Month;
import com.hhz.ump.entity.plan.PlanWork2Status;
import com.hhz.ump.entity.plan.PlanWork2Weight;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DictContants;
import com.hhz.uums.entity.ws.WsPlasOrg;

/**
 * 各中心月度工作计划
 * 
 * @author lujy 2011-2-12
 * 
 *         计划的类别planType 1：年度计划分解月度任务 2：月度经营管理例会工作指令 3：项目管理得分 4：工作指令单 5：综合管理
 * 
 *         有涉及到的系统角色： 
 *         A_PLAN_WORK2_ADMIN 	工作计划2_管理员 
 *         A_PLAN_WORK2_CENTER	工作计划2_中心普通用户 
 *         A_PLAN_WORK2_OFFICE 	工作计划2_总裁办用户 
 *         A_PLAN_WORK2_PROJECT	工作计划2_项目管理中心用户 
 *         A_PLAN_WORK2_VICE 	工作计划2_副总裁
 *         A_PLAN_WORK2_CEO 	工作计划2_总裁
 *         A_PLAN_WORK2_VIEW 	工作计划2_浏览用户
 *         A_PLAN_WORK2_DEPT 	工作计划2_部门浏览用户
 * 
 *         状态statusCd 0：未确认 1：进行中 2：预完成 3：申请删除 4：完成 5：删除 6：隐藏 7：非本月任务 8：已延期 9:非考核性延期
 * 
 *         ifSuspend 1：来自延期的计划
 *         ifSuspendCreated 1：由延期产生的计划，即不是实际在用的那个
 *         
 */
@Namespace("/plan")
@Results({
		@Result(name = CrudActionSupport.RELOAD, location = "planWork2.action", type = "redirect", params = {
				"centerCd", "${centerCd}" }),
		@Result(name = CrudActionSupport.RELOAD_SUB, location = "planWork2.action", type = "redirect") })
public class PlanWork2Action extends CrudActionSupport<PlanWork2> {
	private static final long serialVersionUID = 8071052318603147332L;

	public static final String STATUS_CD_WEIQUEREN = "0"; 		// 未确认
	public static final String STATUS_CD_JINXINGZHONG = "1"; 	// 进行中
	public static final String STATUS_CD_YUWANCHENG = "2"; 		// 预完成
	public static final String STATUS_CD_SHENQINGSHANCHU = "3"; // 申请删除
	public static final String STATUS_CD_WANCHENG = "4"; 		// 完成
	public static final String STATUS_CD_SHANGCHU = "5"; 		// 删除
	public static final String STATUS_CD_YINCANG = "6"; 		// 隐藏
	public static final String STATUS_CD_NO_THIS = "7"; 		// 非本月任务
	public static final String STATUS_CD_SUSPEND = "8"; 		// 延期
	public static final String STATUS_CD_NO_ASSESS = "9"; 		// 非考核性延期

	// 某中心的工作计划的操作状态
	public static final String CENTER_ADDING = "0"; 		// 0：各中心新增中
	public static final String CENTER_ADD = "1"; 			// 1：各中心新增完毕
	public static final String CENTER_VICE_CONFIRM = "2"; 	// 2：副总裁确认新增完毕
	public static final String CENTER_OFFICE_CONFIRM = "3"; // 3：计划管理员确认完毕，开始执行
	public static final String CENTER_START_WEIGHT = "4"; 	// 4：开始给权重分
	public static final String CENTER_CEO_WEIGHT = "5"; 	// 5：总裁给权重分完毕
	public static final String CENTER_CENTER_POINT = "6"; 	// 6：各中心自评完毕
	public static final String CENTER_VICE_POINT = "7"; 	// 7：副总裁评分完毕
	public static final String CENTER_OFFICE_POINT = "8"; 	// 8：总裁办或者项目管理中心考评完毕
	public static final String CENTER_FINAL_POINT = "9"; 	// 9：总裁给最终分数完毕
	private String center_status; 	//此中心的月计划操作状态，从planWork2Status表中读取
	private String quarter_status; 	//此中心的季度计划的评分状态，从planWork2Status表中读取

	private PlanWork2 entity;
	private PlanWork2Message message;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private PlanOperationLogManager planOperationLogManager;

	@Autowired
	private JbpmTaskManager jbpmTaskManager;

	@Autowired
	private OaAllAttentionManager oaAllAttentionManager;
	
	@Autowired
	private PlanWork2MonthManager planWork2MonthManager;
	
	@Autowired
	private PlanWork2WeightManager planWork2WeightManager;

	private String operationlogType;

	private int serialOrder;

	private String myTask;

	private String content;

	private String area;

	private Date targetDate;
	
	private String nowDate;

	private boolean if_in_attention; // 是否由关注引进本模块
	private String opened_entityid; // 直接打开的entityId
	private Map<String, String> attentionMap; // 关注id，用来判断是否有关注
	private String isAttentioned; // 修改的记录是否是被关注的，这里直接传这个参数可以不用读取关注表的map出来
	private Map<String, String> attentionMapUnread; // 未读的关注id，关注列表时用，用来判断关注是否已读
	private boolean aOnlyCreator = false;	//是否进入只限上传者查看附件模式(商业用)
	private boolean cannotChangeDept = true;	//是否不能改变中心
	private boolean if_bis_cannot = false;	//是否在商业下，并且不是施小姐
	private String myUiid;	//当期用户的uiid
	
	private String[] chkIds;

	private Integer[] chkOrders;

	private String principal;

	@Autowired
	private PlanWork2Manager planWork2Manager;

	@Autowired
	private PlanWork2MessageManager planWork2MessageManager;

	private List<WsPlasOrg> uaapOrgs;

	private String fgCenterCd;

	private List<PlanWork2Message> messageList;

	private String orderStr1; // 排序字段1

	private String orderStr2; // 排序字段2

	private String orderDir1; // 排序顺序1

	private String orderDir2; // 排序顺序2

	private int if_search_all; // 是否搜索全部

	private String centerCd;

	private int now_year = 0; // 当前的年份

	private int now_month = 0; // 当前的月份
	
	private int now_quarter = 0; // 当前的季度
	
	private int if_in_weight = -1; //是否在评分的状态
	
	private int if_goto_cost = 0; //是否在成本工作管理中

	/**
	 * 新增记录时，附件上传使用的临时ID
	 */
	private String entityTmpId;

	private Map<String, List<WsPlasOrg>> orgMap;

	private String isEditOrg;

	private Map<String, String> mapContentTips = new HashMap<String, String>(); // 保存content的title,包含content和前三条留言记录
	private Map<String, String> mapPointTips = new HashMap<String, String>(); // 保存给分的title,包含selfPoint,selfCheckPoint,evaluatePoint,finalPoint

	/**
	 * 计划管理统一入口
	 * 
	 * @return
	 * @throws Exception
	 */
	public String main() throws Exception {
		centerCd = SpringSecurityUtils.getCurrentCenterCd();
		String result = "";
		return result;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		planWork2Manager.deletePlanWork2(getId());
		addActionMessage(getText("common.success"));
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		try {
			planWork2Manager.deleteBatch(getIds());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}

	/*
	 * 主入口
	 */
	public String getAllPlan() throws Exception {
		// getRelationCenters();
		orgMap = this.buildOrgMap();	//获取部门列表
		String from_centerCd = Struts2Utils.getRequest().getParameter(
				"centerCd");
		if (null != from_centerCd && !"".equalsIgnoreCase(from_centerCd)) {
			//如果有from_centerCd属性，当前centerCd为from_centerCd
			centerCd = from_centerCd;
		} else {
			String curCenterCd = SpringSecurityUtils.getCurrentCenterCd();	//当前用户所在的部门cd
			if (curCenterCd == null || "".equals(curCenterCd)) {
				//如果当前用户所在部门cd为空，说明可能未登录，centerCd为空
				centerCd = SpringSecurityUtils.getCurrentDeptCd();
			} else {
				//如果不为空，试着通过另一个方法取当前用户所在的中心，如果取不到，则取当前部门cd
				WsPlasOrg org = SpringSecurityUtils.getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX);
				if (org != null) {
					centerCd = org.getOrgCd();
				} else {
					centerCd = SpringSecurityUtils.getCurrentDeptCd();
				}
			}
		}
		now_year = DateOperator.getYear(new Date());
		now_month = DateOperator.getMonth12(new Date());
		//根据月份判断现在在哪个季度，设置全局变量中的now_quarter
		now_quarter = getQuarterByMonth(now_month);
		
		if(-1==if_in_weight){
			//如果不是直接到评分，判断是否应该直接到评分
			PlanWork2Status planWork2Status = planWork2Manager.getPlanWork2StatusByQuarter(
					new BigDecimal(now_year), new BigDecimal(now_quarter), centerCd, false);
			if(null!=planWork2Status){
				if("4".equalsIgnoreCase(planWork2Status.getStatusCd())
						||"5".equalsIgnoreCase(planWork2Status.getStatusCd())
						||"6".equalsIgnoreCase(planWork2Status.getStatusCd())
						||"7".equalsIgnoreCase(planWork2Status.getStatusCd())
						||"8".equalsIgnoreCase(planWork2Status.getStatusCd())
						||"9".equalsIgnoreCase(planWork2Status.getStatusCd())){
					if_in_weight = 1;
				}else{
					if_in_weight = 0;
				}
			}
		}
		if(SpringSecurityUtils.hasRole("A_PLAN_WORK2_ADMIN")
				|| SpringSecurityUtils.hasRole("A_PLAN_WORK2_CENTER")
				|| SpringSecurityUtils.hasRole("A_PLAN_WORK2_OFFICE")
				|| SpringSecurityUtils.hasRole("A_PLAN_WORK2_VICE")
				|| SpringSecurityUtils.hasRole("A_PLAN_WORK2_PROJECT")
				|| SpringSecurityUtils.hasRole("A_PLAN_WORK2_CEO")
				|| SpringSecurityUtils.hasRole("A_PLAN_WORK2_VIEW")
				|| SpringSecurityUtils.hasRole("A_PLAN_WORK_BISA")){
			cannotChangeDept = false;
		}
		try{
			//如果从待办事项进入，自动判断要打开那个月份的记录
			if(StringUtils.isNotBlank(opened_entityid)){
				int now_val = now_year*12+now_month;
				int min_dis = 0;
				PlanWork2 pw = planWork2Manager.getEntity(opened_entityid);
				List<PlanWork2Month> pwms = pw.getPlanWork2Months();
				for(int i=0; null!=pwms && i<pwms.size(); i++){
					PlanWork2Month pwm = pwms.get(i);
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
	 * 根据月份取得季度
	 */
	public static int getQuarterByMonth(int mymonth){
		int myQuarter = 0;
		if(1==mymonth || 2==mymonth || 3==mymonth){
			myQuarter = 1;
		}else if(4==mymonth || 5==mymonth || 6==mymonth){
			myQuarter = 2;
		}else if(7==mymonth || 8==mymonth || 9==mymonth){
			myQuarter = 3;
		}else if(10==mymonth || 11==mymonth || 12==mymonth){
			myQuarter = 4;
		}
		return myQuarter;
	}

	/*
	 * 根据季度取得月份
	 */
	public static int getMonthByQuarter(int myQuarter){
		int mymonth = 0;
		if(1==myQuarter){
			mymonth = 3;
		}else if(2==myQuarter){
			mymonth = 6;
		}else if(3==myQuarter){
			mymonth = 9;
		}else if(4==myQuarter){
			mymonth = 12;
		}
		return mymonth;
	}
	
	/*
	 * 根据各中心取得计划类别的权重分（主要是3项目管理）
	 */
	public static int getWeightPointByCenter(int myPlanType,String centerCd){
		int top_weightPoint1 = 0;
		int top_weightPoint2 = 0;
		int top_weightPoint3 = 0;
		if("147".equalsIgnoreCase(centerCd)){
			//监审部
			top_weightPoint1 = 50;
			top_weightPoint2 = 40;
			top_weightPoint3 = 0;
		}else if("7".equalsIgnoreCase(centerCd)){
			//总裁办
			top_weightPoint1 = 60;
			top_weightPoint2 = 25;
			top_weightPoint3 = 5;
		}else if("9".equalsIgnoreCase(centerCd)){
			//人力资源管理中心
			top_weightPoint1 = 50;
			top_weightPoint2 = 30;
			top_weightPoint3 = 10;
		}else if("8".equalsIgnoreCase(centerCd)){
			//财务管理中心
			top_weightPoint1 = 50;
			top_weightPoint2 = 30;
			top_weightPoint3 = 10;
		}else if("125".equalsIgnoreCase(centerCd)){
			//投资关系部
			top_weightPoint1 = 50;
			top_weightPoint2 = 40;
			top_weightPoint3 = 0;
		}else if("10".equalsIgnoreCase(centerCd)){
			//资本管理中心
			top_weightPoint1 = 50;
			top_weightPoint2 = 40;
			top_weightPoint3 = 0;
		}else if("11".equalsIgnoreCase(centerCd)){
			//投资发展中心
			top_weightPoint1 = 50;
			top_weightPoint2 = 30;
			top_weightPoint3 = 10;
		}else if("126".equalsIgnoreCase(centerCd)){
			//商业地产研究院
			top_weightPoint1 = 40;
			top_weightPoint2 = 20;
			top_weightPoint3 = 30;
		}else if("12".equalsIgnoreCase(centerCd)){
			//技术研发中心
			top_weightPoint1 = 20;
			top_weightPoint2 = 20;
			top_weightPoint3 = 50;
		}else if("132".equalsIgnoreCase(centerCd)){
			//项目管理中心
			top_weightPoint1 = 70;
			top_weightPoint2 = 10;
			top_weightPoint3 = 10;
		}else if("133".equalsIgnoreCase(centerCd)){
			//营销管理中心
			top_weightPoint1 = 30;
			top_weightPoint2 = 10;
			top_weightPoint3 = 50;
		}else if("17".equalsIgnoreCase(centerCd)){
			//成本控制中心
			top_weightPoint1 = 40;
			top_weightPoint2 = 10;
			top_weightPoint3 = 40;
		}
		if(1==myPlanType)
			return top_weightPoint1;
		else if(2==myPlanType)
			return top_weightPoint2;
		else if(3==myPlanType)
			return top_weightPoint3;
		else if(5==myPlanType)
			return 10;
		else
			return 0;
	}

	/**
	 * 获取大小中心分组
	 * 
	 * @return
	 */
	private Map<String, List<WsPlasOrg>> buildOrgMap() {
		Map<String, List<WsPlasOrg>> map = new LinkedHashMap<String, List<WsPlasOrg>>();
		/*
		List<WsPlasOrg> tempOrgs = PlasCache.getOrgListByTypeCd(Constants.ORG_TYPE_CD_FG);
		CollectionHelper.sortList(tempOrgs, "sequenceNo");// 排序

		for (WsPlasOrg wsUaapOrg : tempOrgs) {
			if (wsUaapOrg.getOrgBizCd().equals(Constants.ORG_BIZ_CD_DCGS)
					||wsUaapOrg.getOrgName().equals("厦门城市公司")
					||wsUaapOrg.getOrgName().equals("投研(已删除)")) {
				continue;
			}
			List<WsPlasOrg> list = PlasCache.getLogicalDirectOrgListByOrgCd(wsUaapOrg.getOrgCd(),Constants.ORG_TYPE_CD_ZX);
			if (null != list && list.size() > 0) {
				map.put(wsUaapOrg.getOrgName(), list);
			} else {
				List<WsPlasOrg> orgList = new ArrayList<WsPlasOrg>();
				orgList.add(wsUaapOrg);
				map.put(wsUaapOrg.getOrgName(), orgList);
			}
		}*/
		WsPlasOrg org = new WsPlasOrg();
		
		List<WsPlasOrg> centerList1 = new ArrayList<WsPlasOrg>();
		org = new WsPlasOrg();
		org.setOrgCd("147");
		org.setOrgName("监审部");
		centerList1.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("868");
		org.setOrgName("公共关系部");
		centerList1.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("7");
		org.setOrgName("行政管理中心");
		centerList1.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("9");
		org.setOrgName("人力资源管理中心");
		centerList1.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("8");
		org.setOrgName("财务管理中心");
		centerList1.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("10");
		org.setOrgName("资本管理中心");
		centerList1.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("11");
		org.setOrgName("投资发展中心");
		centerList1.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("12");
		org.setOrgName("技术研发中心");
		centerList1.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("712");
		org.setOrgName("设计管理中心");
		centerList1.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("710");
		org.setOrgName("营销管理中心");
		centerList1.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("720");
		org.setOrgName("项目管理中心");
		centerList1.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("17");
		org.setOrgName("成本控制中心");
		centerList1.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("132");
		org.setOrgName("营运管理中心");
		centerList1.add(org);
		
		map.put("地产总部", centerList1);
		
		List<WsPlasOrg> centerList2 = new ArrayList<WsPlasOrg>();
		org = new WsPlasOrg();
		org.setOrgCd("134");
		org.setOrgName("酒店开发中心");
		centerList2.add(org);
		
		map.put("酒店总部", centerList2);

		/*List<WsPlasOrg> centerList6 = new ArrayList<WsPlasOrg>();
		org = new WsPlasOrg();
		org.setOrgCd("707");
		org.setOrgName("北区总部");
		centerList6.add(org);
		
		map.put("北方区域", centerList6);*/
		
		List<WsPlasOrg> centerList3 = new ArrayList<WsPlasOrg>();
		org = new WsPlasOrg();
		org.setOrgCd("155");
		org.setOrgName("行政人事部");
		centerList3.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("156");
		org.setOrgName("财务部");
		centerList3.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("154");
		org.setOrgName("招商中心");
		centerList3.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("157");
		org.setOrgName("营运中心");
		centerList3.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("869");
		org.setOrgName("总工办");
		centerList3.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("439");
		org.setOrgName("企划部");
		centerList3.add(org);
		map.put("商业总部", centerList3);
		
		//List<WsPlasOrg> centerList4 = new ArrayList<WsPlasOrg>();
		//map.put("酒店总部", centerList4);

		List<WsPlasOrg> centerList5 = new ArrayList<WsPlasOrg>();
		org = new WsPlasOrg();
		org.setOrgCd("453");
		org.setOrgName("百货管理中心");
		centerList5.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("454");
		org.setOrgName("KTV管理中心");
		centerList5.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("636");
		org.setOrgName("上海合立道建筑设计有限公司");
		centerList5.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("630");
		org.setOrgName("上海茂龙装饰设计工程有限公司");
		centerList5.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("165");
		org.setOrgName("晟合数码科技");
		centerList5.add(org);
		map.put("配套企业", centerList5);
		/*
		List<WsPlasOrg> centerList5 = new ArrayList<WsPlasOrg>();
		org = new WsPlasOrg();
		org.setOrgCd("1066");
		org.setOrgName("办公室");
		centerList5.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("451");
		org.setOrgName("企业管理一部");
		centerList5.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("100000");
		org.setOrgName("企业管理二部");
		centerList5.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("1064");
		org.setOrgName("艺术投资部");
		centerList5.add(org);
		org = new WsPlasOrg();
		org.setOrgCd("897");
		org.setOrgName("上海宝龙拍卖有限公司");
		centerList5.add(org);
		map.put("非上市总部", centerList5);
		*/

		return map;
	}

	/*
	 * 设置排序方式
	 */
	private void buildFilter(Page page,int if_in_weight) {
		if (null != orderStr1 && !"".equalsIgnoreCase(orderStr1)
				&& null != orderStr2 && !"".equalsIgnoreCase(orderStr2)) {
			page.setOrderBy(orderStr1 + "," + orderStr2
					+ ",targetDate");
			page.setOrder(orderDir1 + "," + orderDir2 + "," + Page.ASC);
		} else if (null != orderStr1 && !"".equalsIgnoreCase(orderStr1)) {
			page.setOrderBy(orderStr1 + ",targetDate");
			page.setOrder(orderDir1 + "," + Page.ASC);
		} else if (null != orderStr2 && !"".equalsIgnoreCase(orderStr2)) {
			page.setOrderBy(orderStr2 + ",targetDate");
			page.setOrder(orderDir2 + "," + Page.ASC);
		}

		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			if(1==if_in_weight){
				page.setOrderBy("planType,planMonth,targetDate");
				page.setOrder(Page.ASC + "," + Page.ASC + "," + Page.ASC);
			}else{
				page.setOrderBy("planType,targetDate");
				page.setOrder(Page.ASC + "," + Page.ASC);
			}
		}
	}
	
	//人工排序，按照statusCd排序
	private void sortPageResult(List<PlanWork2> pageResult,int nowYear, int nowMonth){
		/*
		for(int i=0;null!=pageResult && i<pageResult.size();i++){
			PlanWork2 pw = pageResult.get(i);
			int status = getMonthStatus(pw,nowYear,nowMonth);
			pw.setStatusCd(Integer.toString(status));
			pageResult.set(i, pw);
		}
		for(int i=0;null!=pageResult && i<pageResult.size();i++){
			PlanWork2 pw1 = pageResult.get(i);
			for(int j=i+1;j<pageResult.size();j++){
				PlanWork2 pw2 = pageResult.get(j);
				int status1 = Integer.parseInt(pw1.getStatusCd());
				int status2 = Integer.parseInt(pw2.getStatusCd());
				if(status1>status2 && pw1.getPlanType().intValue()==pw2.getPlanType().intValue()){
					PlanWork2 temppw = pw1;
					pw1 = pw2;
					pw2 = temppw;
					pageResult.set(i, pw1);
					pageResult.set(j, pw2);
				}
			}
		}
		*/
	}
	
	//获得month指定某年月的status
	private int getMonthStatus(PlanWork2 pw ,int nowYear, int nowMonth){
		int returnStatus = 999;
		try{
		List<PlanWork2Month> listMonth = pw.getPlanWork2Months();
		for(int i=0;null!=listMonth && i<listMonth.size();i++){
			PlanWork2Month temppm = listMonth.get(i);
			if(temppm.getPlanYear().intValue()==nowYear && temppm.getPlanMonth().intValue()==nowMonth){
				returnStatus = Integer.parseInt(temppm.getStatusCd());
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnStatus;
	}

	@Override
	public String list() throws Exception {
		// page.setPageSize(30);
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(Struts2Utils.getRequest());
		page.setPageSize(999);
		page.setPageNo(1);
		page.setOrderBy("planType,serialOrder");
		page.setOrder(Page.ASC + "," + Page.ASC);
		if (null == centerCd || "".equalsIgnoreCase(centerCd)) {
			centerCd = SpringSecurityUtils.getCurrentCenterCd();
		}
		PlanWork2Status planWork2StatusMonth = planWork2Manager.getPlanWork2StatusByMonth(
				new BigDecimal(now_year), new BigDecimal(now_month), centerCd);
		center_status = planWork2StatusMonth.getStatusCd();
		PlanWork2Status planWork2StatusQuarter = new PlanWork2Status();
		if(1==if_in_weight){
			//如果在评分中，就读取当前的季度状态
			planWork2StatusQuarter = planWork2Manager.getPlanWork2StatusByQuarter(
					new BigDecimal(now_year), new BigDecimal(now_quarter), centerCd, false);
		}else{
			//不在评分中，先根据月份确定季度，再读取当前的季度状态
			now_quarter = getQuarterByMonth(now_month);
			planWork2StatusQuarter = planWork2Manager.getPlanWork2StatusByQuarter(
					new BigDecimal(now_year), new BigDecimal(now_quarter), centerCd, false);
		}
		if(null!=planWork2StatusQuarter){
			quarter_status = planWork2StatusQuarter.getStatusCd();
		}
		if (!if_in_attention) {
			if (null != myTask && "true".equalsIgnoreCase(myTask)) {
				// 从邮件报表中搜索属于我自己的
				// filters.add(new PropertyFilter("LIKES_principal",
				// SpringSecurityUtils.getCurrentUaapUser().getUiid()));
				List<PlanWork2> planWork2List = new ArrayList();
				StringBuffer hql = new StringBuffer(
						"select task from JbpmTask as task, JbpmTaskCandidate as cond ");
				hql.append(" where task.jbpmTaskId=cond.jbpmTask.jbpmTaskId and task.moduleCd like 'planWork2'");
				hql.append(" and (cond.userCd=:userCd1 or cond.userCd like :userCd2) ");

				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userCd1", SpringSecurityUtils.getCurrentUiid());
				param.put("userCd2",
						"%;" + SpringSecurityUtils.getCurrentUiid() + ";%");
				List<JbpmTask> jbpmTaskList = jbpmTaskManager.find(
						hql.toString(), param);
				for (JbpmTask jbpmTask : jbpmTaskList) {
					try {
						String entityId = jbpmTask.getJbpmId();
						PlanWork2 planWork2 = planWork2Manager
								.getEntity(entityId);
						planWork2List.add(planWork2);
					} catch (Exception e) {
					}
				}
				page.setResult(planWork2List);
			} else {
				//这里是常规的入口
				if("153".equalsIgnoreCase(centerCd)){
					//如果是宝龙商业，自动汇总下属中心的记录
					List<WsPlasOrg> listOrg = PlasCache.getLogicalDirectOrgListByOrgCd("153",Constants.ORG_TYPE_CD_ZX);
					if(null!=listOrg){
						List<String> centerList = new ArrayList<String>();
						for(WsPlasOrg org: listOrg){
							centerList.add(org.getOrgCd());
						}
						filters.add(new PropertyFilter("INA_centerCd", centerList.toArray()));
					}else{
						filters.add(new PropertyFilter("EQS_centerCd", centerCd));
					}
					filters.add(new PropertyFilter("EQM_planType", '1'));
				}else{
					filters.add(new PropertyFilter("EQS_centerCd", centerCd));
				}
				filters.add(new PropertyFilter("EQM_planWork2Months.planYear", now_year));
				if(1==if_in_weight){
					//按照季度来搜索，即在评分状态中
					if(1==now_quarter){
						filters.add(new PropertyFilter("INA_planWork2Months.planMonth",new Object[] {
								new BigDecimal(1),new BigDecimal(2),new BigDecimal(3) }));
					}else if(2==now_quarter){
						filters.add(new PropertyFilter("INA_planWork2Months.planMonth",new Object[] {
								new BigDecimal(4),new BigDecimal(5),new BigDecimal(6) }));
					}else if(3==now_quarter){
						filters.add(new PropertyFilter("INA_planWork2Months.planMonth",new Object[] {
								new BigDecimal(7),new BigDecimal(8),new BigDecimal(9) }));
					}else if(4==now_quarter){
						filters.add(new PropertyFilter("INA_planWork2Months.planMonth",new Object[] {
								new BigDecimal(10),new BigDecimal(11),new BigDecimal(12) }));
					}
					filters.add(new PropertyFilter("INA_planWork2Months.statusCd", new Object[] {"1", "2", "3", "4", "8", "9" }));
				}else{
					//按照月度来搜索
					filters.add(new PropertyFilter("EQM_planWork2Months.planMonth", now_month));
					String search_statusCd = Struts2Utils.getParameter("search_statusCd");
					if (search_statusCd != null && !search_statusCd.equalsIgnoreCase("")) {
						filters.add(new PropertyFilter("EQS_planWork2Months.statusCd",search_statusCd));
					} else {
						filters.add(new PropertyFilter("INA_planWork2Months.statusCd",
								new Object[] { "0", "1", "2", "3", "4", "7", "8", "9","10" }));
					}
				}
				buildFilter(page,if_in_weight);

				page = planWork2Manager.findPage(page, filters);
				
				//sortPageResult(page.getResult(),now_year,now_month);

				/*
				if(1==if_in_weight){
					deleteSameRecord(page);
				}
				*/
				// 生成编号
				Struts2Utils.getRequest().setAttribute("newSerialOrder",
						planWork2Manager.getMaxNo(centerCd, now_year, now_month));
				Struts2Utils.getRequest().setAttribute("newSerialNumber",
						"YJH-" + now_year + now_month + "-" + CodeNameUtil.getDeptShortNameByCd(centerCd)+ "-");
			}
		} else {
			//在关注列表里
			List list_planWork2 = new ArrayList();
			attentionMapUnread = new LinkedHashMap<String, String>();
			List list_unread_attention = oaAllAttentionManager.getUnreadIds(
					"planWork2", SpringSecurityUtils.getCurrentUiid(), "PLAN_WORK2", "PLAN_WORK2_ID");
			for (int i = 0; null != list_unread_attention
					&& i < list_unread_attention.size(); i++) {
				String attentionEntityId = (String) list_unread_attention
						.get(i);
				PlanWork2 planWork2 = planWork2Manager
						.getEntity(attentionEntityId);
				list_planWork2.add(planWork2);
				attentionMapUnread.put(attentionEntityId, "unread");
			}
			List list_read_attention = oaAllAttentionManager.getReadIds(
					"planWork2", SpringSecurityUtils.getCurrentUiid(), "PLAN_WORK2", "PLAN_WORK2_ID");
			for (int i = 0; null != list_read_attention
					&& i < list_read_attention.size(); i++) {
				String attentionEntityId = (String) list_read_attention.get(i);
				PlanWork2 planWork2 = planWork2Manager
						.getEntity(attentionEntityId);
				list_planWork2.add(planWork2);
			}
			page.setResult(list_planWork2);
		}
		attentionMap = oaAllAttentionManager.getMyAttention("planWork2",
				SpringSecurityUtils.getCurrentUiid());

		//如果结果集中没有打开的对象，搜索得到打开的对象，作为结果集的第一条插入
		if(null!=opened_entityid && !"".equalsIgnoreCase(opened_entityid)){
			try{
				boolean if_has_opened_entityid = false;
				List<PlanWork2> list_planWork2 = page.getResult();
				for(PlanWork2 planWork22:list_planWork2){
					if(opened_entityid.equalsIgnoreCase(planWork22.getPlanWork2Id())){
						if_has_opened_entityid = true;
						break;
					}
				}
				if(!if_has_opened_entityid){
					PlanWork2 planWork2 = planWork2Manager.getEntity(opened_entityid);
					list_planWork2.add(0, planWork2);
					page.setResult(list_planWork2);
				}
			}catch(Exception e){}
		}

		initIsEditOrg();
		page.setResult(buildAttributes(page.getResult()));
		
		judgeOnlyCreator(centerCd);

		try{
			List<WsPlasOrg> relaOrgs = PlasCache.getLogicalBubbleOrgListByOrgCd(centerCd);
			for(WsPlasOrg wsPlasOrg:relaOrgs){
				if("宝龙商业".equalsIgnoreCase(wsPlasOrg.getOrgName())
						&& !"shisn".equalsIgnoreCase(SpringSecurityUtils.getCurrentUiid())) {
					if_bis_cannot = true;
					break;
				}
			}
		}catch(Exception e){}
		myUiid = SpringSecurityUtils.getCurrentUiid();
		
		return "list";
	}
	
	/*
	 * 判断是否进入附件创建者模式
	 */
	public void judgeOnlyCreator(String centerCd){
		if(!SpringSecurityUtils.hasRole("A_PLAN_WORK2_ADMIN")
				&& !SpringSecurityUtils.hasRole("A_PLAN_WORK_BISA")
				&& !SpringSecurityUtils.hasRole("A_PLAN_WORK2_CEO")
				&& !SpringSecurityUtils.hasRole("A_PLAN_WORK2_OFFICE")
				&& !SpringSecurityUtils.hasRole("A_PLAN_WORK2_VICE")
				&& null!=centerCd && !"".equalsIgnoreCase(centerCd)){
			//如果不是管理用户，进入只限创建者下载附件模式
			/*
			try{
				List<WsPlasOrg> relaOrgs = PlasCache.getLogicalBubbleOrgListByOrgCd(centerCd);
				boolean if_has = false;
				for(WsPlasOrg wsPlasOrg:relaOrgs){
					if("宝龙商业".equalsIgnoreCase(wsPlasOrg.getOrgName())) {
						aOnlyCreator = true;
						if_has = true;
						break;
					}
				}
				if(!if_has){
					aOnlyCreator = false;
				}
			}catch(Exception e){}
			 */
			aOnlyCreator = true;
		}else{
			aOnlyCreator = false;
		}
	}
	
	/**
	 * 成本模块里成本任务所用，月计划
	 * @return
	 */
	public String costCtrl(){

		// page.setPageSize(30);
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(Struts2Utils.getRequest());
		page.setPageSize(999);
		page.setPageNo(1);
		if (null == centerCd || "".equalsIgnoreCase(centerCd)) {
			centerCd = SpringSecurityUtils.getCurrentCenterCd();
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate1 = format.format(DateOperator.getDateNow());
		if(now_year==0){
			
			now_year=Integer.parseInt(nowDate1.substring(0, 4));
		}
		if(now_month==0){
			now_month=Integer.parseInt(nowDate1.substring(5, 7));
		}
		setNowDate(now_year+"-"+now_month);
		PlanWork2Status planWork2StatusMonth = planWork2Manager.getPlanWork2StatusByMonth(
				new BigDecimal(now_year), new BigDecimal(now_month), centerCd);
		center_status = planWork2StatusMonth.getStatusCd();
		PlanWork2Status planWork2StatusQuarter = new PlanWork2Status();
		if(1==if_in_weight){
			planWork2StatusQuarter = planWork2Manager.getPlanWork2StatusByQuarter(
					new BigDecimal(now_year), new BigDecimal(now_quarter), centerCd, false);
		}else{
			now_quarter = getQuarterByMonth(now_month);
			planWork2StatusQuarter = planWork2Manager.getPlanWork2StatusByQuarter(
					new BigDecimal(now_year), new BigDecimal(now_quarter), centerCd, false);
		}
		if(null!=planWork2StatusQuarter){
			quarter_status = planWork2StatusQuarter.getStatusCd();
		}
		if (!if_in_attention) {
			// 正常搜索
			if (null != myTask && "true".equalsIgnoreCase(myTask)) {
				// 从邮件报表中搜索属于我自己的
				// filters.add(new PropertyFilter("LIKES_principal",
				// SpringSecurityUtils.getCurrentUaapUser().getUiid()));
				List<PlanWork2> planWork2List = new ArrayList();
				StringBuffer hql = new StringBuffer(
						"select task from JbpmTask as task, JbpmTaskCandidate as cond ");
				hql.append(" where task.jbpmTaskId=cond.jbpmTask.jbpmTaskId and task.moduleCd like 'planWork2'");
				hql.append(" and (cond.userCd=:userCd1 or cond.userCd like :userCd2) ");

				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userCd1", SpringSecurityUtils.getCurrentUiid());
				param.put("userCd2",
						"%;" + SpringSecurityUtils.getCurrentUiid() + ";%");
				List<JbpmTask> jbpmTaskList = jbpmTaskManager.find(
						hql.toString(), param);
				for (JbpmTask jbpmTask : jbpmTaskList) {
					try {
						String entityId = jbpmTask.getJbpmId();
						PlanWork2 planWork2 = planWork2Manager
								.getEntity(entityId);
						planWork2List.add(planWork2);
					} catch (Exception e) {
					}
				}
				page.setResult(planWork2List);
			} else {
				//这里是常规的入口
				filters.add(new PropertyFilter("EQS_centerCd", centerCd));
				filters.add(new PropertyFilter("EQM_planWork2Months.planYear", now_year));
				if(1==if_in_weight){
					//按照季度来搜索
					if(1==now_quarter){
						filters.add(new PropertyFilter("INA_planWork2Months.planMonth",new Object[] {
								new BigDecimal(1),new BigDecimal(2),new BigDecimal(3) }));
					}else if(2==now_quarter){
						filters.add(new PropertyFilter("INA_planWork2Months.planMonth",new Object[] {
								new BigDecimal(4),new BigDecimal(5),new BigDecimal(6) }));
					}else if(3==now_quarter){
						filters.add(new PropertyFilter("INA_planWork2Months.planMonth",new Object[] {
								new BigDecimal(7),new BigDecimal(8),new BigDecimal(9) }));
					}else if(4==now_quarter){
						filters.add(new PropertyFilter("INA_planWork2Months.planMonth",new Object[] {
								new BigDecimal(10),new BigDecimal(11),new BigDecimal(12) }));
					}
					filters.add(new PropertyFilter("INA_planWork2Months.statusCd", new Object[] {"1", "2", "3", "4", "8" }));
				}else{
					//按照月度来搜索
					filters.add(new PropertyFilter("EQM_planWork2Months.planMonth", now_month));
					String search_statusCd = Struts2Utils.getParameter("search_statusCd");
					if (search_statusCd != null && !search_statusCd.equalsIgnoreCase("")) {
						filters.add(new PropertyFilter("EQS_planWork2Months.statusCd",search_statusCd));
					} else {
						filters.add(new PropertyFilter("INA_planWork2Months.statusCd",
								new Object[] { "0", "1", "2", "3", "4", "7", "8" }));
					}
				}
				buildFilter(page,if_in_weight);

				page = planWork2Manager.findPage(page, filters);

				if(1==if_in_weight){
					deleteSameRecord(page);
				}
				// 生成编号
				Struts2Utils.getRequest().setAttribute("newSerialOrder",
						planWork2Manager.getMaxNo(centerCd, now_year, now_month));
				Struts2Utils.getRequest().setAttribute("newSerialNumber",
						"YJH-" + now_year + now_month + "-" + CodeNameUtil.getDeptShortNameByCd(centerCd)+ "-");
			}
		} else {
			// 在关注里
			List list_planWork2 = new ArrayList();
			attentionMapUnread = new LinkedHashMap<String, String>();
			List list_unread_attention = oaAllAttentionManager.getUnreadIds(
					"planWork2", SpringSecurityUtils.getCurrentUiid(), "PLAN_WORK2", "PLAN_WORK2_ID");
			for (int i = 0; null != list_unread_attention
					&& i < list_unread_attention.size(); i++) {
				String attentionEntityId = (String) list_unread_attention
						.get(i);
				PlanWork2 planWork2 = planWork2Manager
						.getEntity(attentionEntityId);
				list_planWork2.add(planWork2);
				attentionMapUnread.put(attentionEntityId, "unread");
			}
			List list_read_attention = oaAllAttentionManager.getReadIds(
					"planWork2", SpringSecurityUtils.getCurrentUiid(), "PLAN_WORK2", "PLAN_WORK2_ID");
			for (int i = 0; null != list_read_attention
					&& i < list_read_attention.size(); i++) {
				String attentionEntityId = (String) list_read_attention.get(i);
				PlanWork2 planWork2 = planWork2Manager
						.getEntity(attentionEntityId);
				list_planWork2.add(planWork2);
			}
			page.setResult(list_planWork2);
		}
		attentionMap = oaAllAttentionManager.getMyAttention("planWork2",
				SpringSecurityUtils.getCurrentUiid());

		initIsEditOrg();
		page.setResult(buildAttributes(page.getResult()));

		return "costCtrl";
	}

	/*
	 * 权限判断，是否是当前用户可编辑的机构
	 */
	private void initIsEditOrg() {
		if (SpringSecurityUtils.hasRole("A_PLAN_WORK2_CENTER")
				|| SpringSecurityUtils.hasRole("A_PLAN_WORK2_VICE")) {
			//如果是中心用户或者副总裁用户
			//List<WsPlasOrg> relaOrgs = PlasCache.getRelationCenterOrgs(SpringSecurityUtils.getCurrentUiid());
			List<WsPlasOrg> relaOrgs = PlasCache.getRelPosBubbleOrgCdList(SpringSecurityUtils.getCurrentAcctId(),"");//当前用户的上级机构

			if (relaOrgs != null) {
				for (WsPlasOrg wsUaapOrg : relaOrgs) {
					if (wsUaapOrg.getOrgTypeCd() != null
							&& !wsUaapOrg.getOrgTypeCd().equals(
									Constants.ORG_TYPE_CD_FG)) {
						if (wsUaapOrg.getOrgCd().equals(centerCd)) {
							//如果当前机构等于分管，那么具有权限
							isEditOrg = "1";
							break;
						}
					} else if (wsUaapOrg.getOrgTypeCd().equals(
							Constants.ORG_TYPE_CD_FG)) {
							//如果用户在分管下，判断是否是现中心所属的分管
						WsPlasOrg wsUaapOrg2 = PlasCache.getOrgByCd(centerCd);
						if (wsUaapOrg2 != null
								&& wsUaapOrg.getPlasOrgId().equalsIgnoreCase(PlasCache.getLogicalParentId(wsUaapOrg2.getPlasOrgId()))) {
							if(SpringSecurityUtils.hasRole("A_PLAN_WORK2_VICE")){
								isEditOrg = "1";
								break;
							}
						}
					}
				}
			}
		}
		if("shisn".equalsIgnoreCase(SpringSecurityUtils.getCurrentUiid())
				||"pantao".equalsIgnoreCase(SpringSecurityUtils.getCurrentUiid())){
			isEditOrg = "1";
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
			entity = planWork2Manager.getEntity(getId());
			PlanWork2Message wm = new PlanWork2Message();
			wm.setContent(content);
			wm.setPlanWork2(entity);
			planWork2MessageManager.savePlanWork2Message(wm);
			planWork2Manager.save(entity);
			Struts2Utils.renderText("ok");
		} else {
			Struts2Utils.renderText("no");
		}
		return null;
	}
	/**
	 * 成本模块添加留言
	 * @return
	 * @throws Exception
	 */
	public String saveMessageByCtrl() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planWork2Manager.getEntity(getId());
			PlanWork2Message wm = new PlanWork2Message();
			wm.setContent(content);
			wm.setPlanWork2(entity);
			planWork2MessageManager.savePlanWork2Message(wm);
			planWork2Manager.save(entity);
			StringBuffer str =new StringBuffer("");
			if( entity.getPlanWork2Messages()!=null&& entity.getPlanWork2Messages().size()>0){
				for(int i=entity.getPlanWork2Messages().size();i>0;i--){
					PlanWork2Message mes =entity.getPlanWork2Messages().get(i-1);
					String now = DateOperator.formatDate(mes.getCreatedDate(), "MM-dd HH:mm");
					str.append("<pre>"+CodeNameUtil.getUserNameByCd(mes.getCreator()));
					str.append("("+now+"):");
					str.append(mes.getContent());
					str.append("</pre>");
				}
			}
			Struts2Utils.renderHtml(str.toString());
		} else {
			Struts2Utils.renderText("no");
		}
		return null;
	}

	public String fetchContent() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planWork2Manager.getEntity(getId());
			messageList = entity.getPlanWork2Messages();
			// CollectionHelper.sortList(messageList, "createdDate", true);
			return "message";
		} else
			return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = planWork2Manager.getEntity(getId());
		} else {
			entity = new PlanWork2();
			entityTmpId = RandomUtils.generateTmpEntityId();
		}
	}

	@Override
	public String save() throws Exception {
		String statusCd = Struts2Utils.getParameter("statusCd");
		if (STATUS_CD_WANCHENG.equalsIgnoreCase(statusCd)) {
			// 如果是完成，记录完成时间
			entity.setEndDate(new Date());
		}
		if (3==entity.getPlanType().intValue() || 5==entity.getPlanType().intValue()) {
			//如果是项目管理或者综合，自动设置为完成状态
			statusCd = this.STATUS_CD_WANCHENG;
			entity.setStatusCd(this.STATUS_CD_WANCHENG);
		}
		if (4==entity.getPlanType().intValue()) {
			if(null==getId() || "".equalsIgnoreCase(getId())){
				//如果是新增，且是工作指令单，自动设置为进行中状态
				entity.setStatusCd(this.STATUS_CD_JINXINGZHONG);
			}
		}
		planWork2Manager.savePlanWork2(entity, entityTmpId);
		saveStatusCd(entity,entity.getPlanYear().intValue(),entity.getPlanMonth().intValue(),entity.getStatusCd());
		String userDeptCd = Struts2Utils.getParameter("userDeptCd");
		if (userDeptCd != null && !userDeptCd.equals("")) {
			centerCd = userDeptCd;
		}
		setId(entity.getPlanWork2Id());
		//addActionMessage(getText("common.success"));
		Struts2Utils.renderText(String.valueOf(entity.getPlanWork2Id()));

		String newMessage = Struts2Utils.getParameter("newMessage");
		if (null != newMessage && !"".equalsIgnoreCase(newMessage)) {
			PlanWork2Message wm = new PlanWork2Message();
			wm.setContent(newMessage);
			wm.setPlanWork2(entity);
			planWork2MessageManager.savePlanWork2Message(wm);
		}
		
		if (STATUS_CD_SUSPEND.equalsIgnoreCase(statusCd)||STATUS_CD_NO_ASSESS.equalsIgnoreCase(statusCd)) {
			// 如果是延期，下个月新增一条
			int sourcePlanYear = entity.getPlanYear().intValue();
			int sourcePlanMonth = entity.getPlanMonth().intValue();
			int toPlanYear = sourcePlanYear;
			int toPlanMonth = sourcePlanMonth + 1;
			if (13 == toPlanMonth) {
				toPlanMonth = 1;
				toPlanYear++;
			}
			
			/*
			if (null == entity.getRemark() || "".equalsIgnoreCase(entity.getRemark())) {
				// 如果本身没有remark，生成remark，否则不改变
				String remark = "";
				if (sourcePlanYear == toPlanYear) {
					remark = sourcePlanMonth + "月遗留工作";
				} else {
					remark = sourcePlanYear + "年度遗留工作";
				}
				entity.setRemark(remark);
			}*/
			
			saveStatusCd(entity,toPlanYear,toPlanMonth,statusCd);
		}
		if(1==if_in_weight){
			//如果是评分，保存这个季度的评分记录
			saveWeight(entity,now_year,now_quarter);
		}

		try {
			planOperationLogManager.addWorkPlanLog(
					entity.getPlanWork2Id(),
					entity.getSerialNumber() + "-" + entity.getSerialOrder(),
					operationlogType,
					entity.getCenterCd(),
					"保存"
							+ entity.getSerialNumber()
							+ entity.getSerialOrder()
							+ ";"
							+ entity.getContent()
							+ ";"
							+ DateOperator.formatDate(entity.getTargetDate(),
									"yyyy-MM-dd") + ";" + entity.getStatusCd());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/*
	 * 保存planWork2Month子表中的状态的方法
	 */
	public boolean saveStatusCd(PlanWork2 planWork2,int planYear,int planMonth,String statusCd){
		boolean if_has_month = false;
		try{
			List<PlanWork2Month> pms = planWork2.getPlanWork2Months();
			for(PlanWork2Month pm : pms){
				if(planYear==pm.getPlanYear().intValue()
						&&planMonth==pm.getPlanMonth().intValue()){
					pm.setStatusCd(statusCd);
					planWork2MonthManager.savePlanWork2Month(pm);
					if_has_month = true;
				}
			}
		}catch(Exception e){
			if_has_month = false;
		}
		if(!if_has_month){
			//如果找不到，就新增子记录
			try{
				PlanWork2Month pm = new PlanWork2Month();
				pm.setPlanYear(new BigDecimal(planYear));
				pm.setPlanMonth(new BigDecimal(planMonth));
				pm.setStatusCd(statusCd);
				pm.setPlanWork2(planWork2);
				planWork2MonthManager.savePlanWork2Month(pm);
				if_has_month = true;
			}catch(Exception e){
				if_has_month = false;
			}
		}
		return if_has_month;
	}
	
	/*
	 * 保存planWork2Weight子表中的分数信息的方法
	 */
	public boolean saveWeight(PlanWork2 planWork2,int planYear,int planQuarter){
		boolean if_has_weight = false;
		try{
			List<PlanWork2Weight> pms = planWork2.getPlanWork2Weights();
			for(PlanWork2Weight pw : pms){
				if(planYear==pw.getPlanYear().intValue()
						&&planQuarter==pw.getPlanQuarter().intValue()){
					pw.setPlanYear(new BigDecimal(planYear));
					pw.setPlanQuarter(new BigDecimal(planQuarter));
					pw.setWeightPoint(planWork2.getWeightPoint());
					pw.setBogusWeightPoint(planWork2.getBogusWeightPoint());
					pw.setSelfPoint(planWork2.getSelfPoint());
					pw.setSelfCheckPoint(planWork2.getSelfCheckPoint());
					pw.setEvaluatePoint(planWork2.getEvaluatePoint());
					pw.setFinalPoint(planWork2.getFinalPoint());
					pw.setPlanWork2(planWork2);
					planWork2WeightManager.savePlanWork2Weight(pw);
					if_has_weight = true;
				}
			}
		}catch(Exception e){
			if_has_weight = false;
		}
		if(!if_has_weight){
			//如果找不到，就新增子记录
			try{
				PlanWork2Weight pw = new PlanWork2Weight();
				pw.setPlanYear(new BigDecimal(planYear));
				pw.setPlanQuarter(new BigDecimal(planQuarter));
				pw.setWeightPoint(planWork2.getWeightPoint());
				pw.setBogusWeightPoint(planWork2.getBogusWeightPoint());
				pw.setSelfPoint(planWork2.getSelfPoint());
				pw.setSelfCheckPoint(planWork2.getSelfCheckPoint());
				pw.setEvaluatePoint(planWork2.getEvaluatePoint());
				pw.setFinalPoint(planWork2.getFinalPoint());
				pw.setPlanWork2(planWork2);
				planWork2WeightManager.savePlanWork2Weight(pw);
				if_has_weight = true;
			}catch(Exception e){
				if_has_weight = false;
			}
		}
		return if_has_weight;
	}
	
	
	/*
	 * 退回记录
	 */
	public String doCallback() throws Exception {
		try {
			if (getId() != null) {
				entity = planWork2Manager.getEntity(getId());
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
			List<PlanWork2Month> pms = entity.getPlanWork2Months();
			for(PlanWork2Month pm : pms){
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
				
				List<PlanWork2Month> pms2 = entity.getPlanWork2Months();
				int remark_count = 0;
				boolean if_start_count = false;
				int count_year = prevPlanYear;
				int count_month = prevPlanMonth;
				for(PlanWork2Month pm : pms2){
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
					planWork2Manager.save(entity);
				}
				String newMessage = Struts2Utils.getParameter("newMessage");
				if (null != newMessage && !"".equalsIgnoreCase(newMessage)) {
					PlanWork2Message wm = new PlanWork2Message();
					wm.setContent(newMessage);
					wm.setPlanWork2(entity);
					planWork2MessageManager.savePlanWork2Message(wm);
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
	 * 保存某中心的月计划操作状态
	 */
	public String savePlanWork2Status() throws Exception {
		try {
			String if_admin_change = Struts2Utils.getParameter("if_admin_change");
			if(null==if_admin_change){
				if_admin_change = "0";
			}
			if (1==if_in_weight && !"1".equalsIgnoreCase(if_admin_change) && this.CENTER_START_WEIGHT.equalsIgnoreCase(center_status)){
				//如果是开始评分按钮，且不是中心月计划管理员操作的，开始所有中心的评分(排除已经开始评分的)
				Map<String, List<WsPlasOrg>> orgAll = this.buildOrgMap();
				for(Object m: orgAll.keySet()){
					List<WsPlasOrg> subList = (List<WsPlasOrg>)orgAll.get(m);
					for(int j=0;null!=subList&&j<subList.size();j++){
						WsPlasOrg subOrg = (WsPlasOrg)subList.get(j);
						String subCenterCd = subOrg.getOrgCd();
						PlanWork2Status planWork2Status = new PlanWork2Status();
						planWork2Status = planWork2Manager.getPlanWork2StatusByQuarter(new BigDecimal(now_year),new BigDecimal(now_quarter),subCenterCd,true);
						if(this.CENTER_ADDING.equalsIgnoreCase(planWork2Status.getStatusCd())
								||this.CENTER_ADD.equalsIgnoreCase(planWork2Status.getStatusCd())
								||this.CENTER_VICE_CONFIRM.equalsIgnoreCase(planWork2Status.getStatusCd())
								||this.CENTER_OFFICE_CONFIRM.equalsIgnoreCase(planWork2Status.getStatusCd())){
							planWork2Status.setStatusCd(center_status);
							planWork2Manager.savePlanWork2Status(planWork2Status);
							planWork2Manager.generateFixedPlan(now_year,now_quarter,subCenterCd);
						}
					}
				}
				planWork2Manager.sendPointEmail(now_year, now_quarter,this.CENTER_START_WEIGHT, "email_admin");
			}else{
				PlanWork2Status planWork2Status = new PlanWork2Status();
				if(1==if_in_weight){
					planWork2Status = planWork2Manager.getPlanWork2StatusByQuarter(new BigDecimal(now_year),new BigDecimal(now_quarter),centerCd,true);
				}else{
					planWork2Status = planWork2Manager.getPlanWork2StatusByMonth(new BigDecimal(now_year),new BigDecimal(now_month),centerCd);
				}
				planWork2Status.setStatusCd(center_status);
				planWork2Manager.savePlanWork2Status(planWork2Status);
				if (1==if_in_weight && !"1".equalsIgnoreCase(if_admin_change)){
					//发送评分提醒邮件
					planWork2Manager.sendPointEmail(planWork2Status, "email_admin");
				}
			}
			if (1!=if_in_weight && CENTER_OFFICE_CONFIRM.equalsIgnoreCase(center_status)) {
				//如果是未确认的记录，月度会议后自动排成确认
				List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
				filters.add(new PropertyFilter("EQS_statusCd",
						STATUS_CD_WEIQUEREN));
				List<PlanWork2> list = planWork2Manager.find(filters);
				for (PlanWork2 planWork2 : list) {
					planWork2.setStatusCd(STATUS_CD_JINXINGZHONG);
					planWork2Manager.savePlanWork2(planWork2);
				}
			}
			Struts2Utils.renderText("success");
			addActionMessage(getText("common.success"));
		} catch (Exception e) {
			Struts2Utils.renderText("failure");
			addActionMessage(getText("common.failure"));
		}
		return null;
	}

	/*
	 * 对延迟的记录，要去的延迟之前的记录信息替换进来，先不用
	 */
	public void operSuspendRecords(PlanWork2 planWork2) {
		String statusCd = planWork2.getStatusCd();
		if (STATUS_CD_SUSPEND.equalsIgnoreCase(statusCd)) {
			// 如果是延迟的记录，搜索suspendId的记录信息，替换到此记录中
			String suspendId = planWork2.getSuspendId();
			PlanWork2 planWork2_suspend = planWork2Manager.getEntity(suspendId);
			planWork2.setPlanWork2Messages(planWork2_suspend
					.getPlanWork2Messages());
		}
	}

	public void prepareFetchDetail() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planWork2Manager.getEntity(getId());
			refreshPlanWork2Status(entity,now_year,now_month);
			Struts2Utils.getRequest().setAttribute("centerCd",
					entity.getCenterCd());
		}
	}

	public String fetchDetail() throws Exception {
		centerCd = entity.getCenterCd();
		judgeOnlyCreator(centerCd);
		try{
			List<WsPlasOrg> relaOrgs = PlasCache.getLogicalBubbleOrgListByOrgCd(centerCd);
			for(WsPlasOrg wsPlasOrg:relaOrgs){
				if("宝龙商业".equalsIgnoreCase(wsPlasOrg.getOrgName())
						&& !"shisn".equalsIgnoreCase(SpringSecurityUtils.getCurrentUiid())) {
					if_bis_cannot = true;
					break;
				}
			}
		}catch(Exception e){}
		myUiid = SpringSecurityUtils.getCurrentUiid();
		return "detail";
	}

	public void prepareFetchMain() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = planWork2Manager.getEntity(getId());
			refreshPlanWork2Status(entity,now_year,now_month);
			Struts2Utils.getRequest().setAttribute("centerCd",
					entity.getCenterCd());
			myTask = PlasCache.getOrgByCd(entity.getCenterCd()).getOrgName();
			judgeOnlyCreator(entity.getCenterCd());

			String id = entity.getPlanWork2Id();
			// 鼠标放在标题上时，显示标题和最新的3条评论
			StringBuffer tip = new StringBuffer();
			tip.append("<p>" + EncodeUtils.htmlEscape(entity.getContent()) + "</p>");
			List<PlanWork2Message> planWork2Messages = entity.getPlanWork2Messages();
			DateFormat df = new SimpleDateFormat("MM-dd");
			if (planWork2Messages.size() > 0) {
				tip.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
				int size = planWork2Messages.size() >= 3 ? 3
						: planWork2Messages.size();
				for (int i = 0; i < size; i++) {
					PlanWork2Message planWork2Message = planWork2Messages
							.get(i);
					String userName = CodeNameUtil
							.getUserNameByCd(planWork2Message.getCreator());
					tip.append("<li><strong>"
							+ userName
							+ "&nbsp;"
							+ df.format(planWork2Message.getCreatedDate())
							+ "</strong>："
							+ EncodeUtils.htmlEscape(planWork2Message
									.getContent()) + "</li>");
				}
				tip.append("</ul>");
			}
			mapContentTips.put(id, tip.toString());
		}
	}

	public String fetchMain() throws Exception {
		centerCd = entity.getCenterCd();
		judgeOnlyCreator(centerCd);
		try{
			List<WsPlasOrg> relaOrgs = PlasCache.getLogicalBubbleOrgListByOrgCd(centerCd);
			for(WsPlasOrg wsPlasOrg:relaOrgs){
				if("宝龙商业".equalsIgnoreCase(wsPlasOrg.getOrgName())
						&& !"shisn".equalsIgnoreCase(SpringSecurityUtils.getCurrentUiid())) {
					if_bis_cannot = true;
					break;
				}
			}
		}catch(Exception e){}
		myUiid = SpringSecurityUtils.getCurrentUiid();
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

	/*
	 * 批量操作状态
	 */
	public String doUpdateStatusAll() throws Exception {
		try {
			String statusCd = Struts2Utils.getParameter("statusCd");
			for (String planWork2_id : chkIds) {
				entity = planWork2Manager.getEntity(planWork2_id);
				saveStatusCd(entity,entity.getPlanYear().intValue(),entity.getPlanMonth().intValue(),entity.getStatusCd());
				/*
				entity.setStatusCd(statusCd);
				if (STATUS_CD_WANCHENG.equalsIgnoreCase(statusCd)) {
					entity.setEndDate(new Date());
				}
				planWork2Manager.savePlanWork2(entity);
				*/
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
				String planWork2_id = chkIds[i];
				entity = planWork2Manager.getEntity(planWork2_id);
				entity.setSerialOrder(new BigDecimal(chkOrders[i]));
				planWork2Manager.savePlanWork2(entity);
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
	private List<PlanWork2> buildAttributes(List<PlanWork2> planWork2s) {
		String id = null;
		for (int j=0;planWork2s!=null && j<planWork2s.size();j++) {
			PlanWork2 p = planWork2s.get(j);
			if(1==if_in_weight){
				refreshPlanWork2Weight(p,now_year,now_quarter);
			}else{
				refreshPlanWork2Status(p,now_year,now_month);
			}
			id = p.getPlanWork2Id();
			// 鼠标放在标题上时，显示标题和最新的3条评论
			StringBuffer tip = new StringBuffer();
			tip.append("<p>" + EncodeUtils.htmlEscape(p.getContent()) + "</p>");
			List<PlanWork2Message> planWork2Messages = p.getPlanWork2Messages();
			DateFormat df = new SimpleDateFormat("MM-dd");
			if (planWork2Messages.size() > 0) {
				tip.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
				int size = planWork2Messages.size() >= 3 ? 3
						: planWork2Messages.size();
				for (int i = 0; i < size; i++) {
					PlanWork2Message planWork2Message = planWork2Messages
							.get(i);
					String userName = CodeNameUtil
							.getUserNameByCd(planWork2Message.getCreator());
					tip.append("<li><strong>"
							+ userName
							+ "&nbsp;"
							+ df.format(planWork2Message.getCreatedDate())
							+ "</strong>："
							+ EncodeUtils.htmlEscape(planWork2Message
									.getContent()) + "</li>");
				}
				tip.append("</ul>");
			}
			mapContentTips.put(id, tip.toString());

			StringBuffer tipPoint = new StringBuffer();
			if(null!=p.getWeightPoint() && 0!=p.getWeightPoint().floatValue()){
				tipPoint.append("<p>权重分："+p.getWeightPoint()+"</p>");
			}
			if(null!=p.getSelfPoint() && 0!=p.getSelfPoint().floatValue()){
				tipPoint.append("<p>中心自评分："+p.getSelfPoint()+"</p>");
			}
			if(null!=p.getSelfCheckPoint() && 0!=p.getSelfCheckPoint().floatValue()){
				tipPoint.append("<p>副总裁评分："+p.getSelfCheckPoint()+"</p>");
			}
			if(null!=p.getEvaluatePoint() && 0!=p.getEvaluatePoint().floatValue()){
				tipPoint.append("<p>营运管理中心分："+p.getEvaluatePoint()+"</p>");
			}
			if(null!=p.getFinalPoint() && 0!=p.getFinalPoint().floatValue()){
				tipPoint.append("<p><strong>最终考评得分："+p.getFinalPoint()+"</strong></p>");
			}
			mapPointTips.put(id, tipPoint.toString());
			planWork2s.set(j, p);
		}
		Collections.sort(planWork2s, new Comparator<PlanWork2>() {
			public int compare(PlanWork2 pw1, PlanWork2 pw2) {
				return pw1.getPlanMonth().intValue() - pw2.getPlanMonth().intValue();
			}
		});
		List<PlanWork2> returnList = new ArrayList();
		for (int i=0;planWork2s!=null && i<planWork2s.size();i++) {
			PlanWork2 pw2 = planWork2s.get(i);
			if(!pw2.getStatusCd().equalsIgnoreCase("7")){
				returnList.add(pw2);
			}
		}
		for (int i=0;planWork2s!=null && i<planWork2s.size();i++) {
			PlanWork2 pw2 = planWork2s.get(i);
			if(pw2.getStatusCd().equalsIgnoreCase("7")){
				returnList.add(pw2);
			}
		}
		return returnList;
	}
	/*
	 * 根据planWork2Month获得statusCd刷新到planWork2中
	 */
	public void refreshPlanWork2Status(PlanWork2 planWork2,int planYear,int planMonth){
		List<PlanWork2Month> pms = planWork2.getPlanWork2Months();
		String statusCd = "";
		for(PlanWork2Month pm : pms){
			if(planYear==pm.getPlanYear().intValue()
					&&planMonth==pm.getPlanMonth().intValue()){
				statusCd = pm.getStatusCd();
				break;
			}
		}
		planWork2.setStatusCd(statusCd);
		planWork2.setPlanYear(new BigDecimal(planYear));
		planWork2.setPlanMonth(new BigDecimal(planMonth));

		int planQuarter = 0;
		if(1==planMonth || 2==planMonth || 3==planMonth){
			planQuarter = 1;
		}else if(4==planMonth || 5==planMonth || 6==planMonth){
			planQuarter = 2;
		}else if(7==planMonth || 8==planMonth || 9==planMonth){
			planQuarter = 3;
		}else if(10==planMonth || 11==planMonth || 12==planMonth){
			planQuarter = 4;
		}
		List<PlanWork2Weight> pmw = planWork2.getPlanWork2Weights();
		if(null!=pmw && pmw.size()>0){
			for(PlanWork2Weight pm : pmw){
				if(planYear==pm.getPlanYear().intValue()
						&&planQuarter==pm.getPlanQuarter().intValue()){
					planWork2.setWeightPoint(pm.getWeightPoint());
					planWork2.setBogusWeightPoint(pm.getBogusWeightPoint());
					planWork2.setSelfPoint(pm.getSelfPoint());
					planWork2.setSelfCheckPoint(pm.getSelfCheckPoint());
					planWork2.setEvaluatePoint(pm.getEvaluatePoint());
					planWork2.setFinalPoint(pm.getFinalPoint());
					planWork2.setPlanQuarter(pm.getPlanQuarter());
					break;
				}
			}
		}
	}
	
	/*
	 * 根据planWork2Weight获得评分信息刷新到planWork2中
	 */
	public void refreshPlanWork2Weight(PlanWork2 planWork2,int planYear,int planQuarter){
		List<PlanWork2Weight> pmw = planWork2.getPlanWork2Weights();
		if(null!=pmw && pmw.size()>0){
			for(PlanWork2Weight pm : pmw){
				if(planYear==pm.getPlanYear().intValue()
						&&planQuarter==pm.getPlanQuarter().intValue()){
					planWork2.setWeightPoint(pm.getWeightPoint());
					planWork2.setBogusWeightPoint(pm.getBogusWeightPoint());
					planWork2.setSelfPoint(pm.getSelfPoint());
					planWork2.setSelfCheckPoint(pm.getSelfCheckPoint());
					planWork2.setEvaluatePoint(pm.getEvaluatePoint());
					planWork2.setFinalPoint(pm.getFinalPoint());
					planWork2.setPlanYear(pm.getPlanYear());
					planWork2.setPlanQuarter(pm.getPlanQuarter());
					break;
				}
			}
		}else{
			PlanWork2Weight pm = new PlanWork2Weight();
			pm.setPlanYear(new BigDecimal(planYear));
			pm.setPlanQuarter(new BigDecimal(planQuarter));
			pm.setPlanWork2(planWork2);
			planWork2WeightManager.savePlanWork2Weight(pm);
		}
		List<PlanWork2Month> pms = planWork2.getPlanWork2Months();
		ArrayList planMonths = new ArrayList();
		if(1==planQuarter){
			planMonths.add(1);
			planMonths.add(2);
			planMonths.add(3);
		}else if(2==planQuarter){
			planMonths.add(4);
			planMonths.add(5);
			planMonths.add(6);
		}else if(3==planQuarter){
			planMonths.add(7);
			planMonths.add(8);
			planMonths.add(9);
		}else if(4==planQuarter){
			planMonths.add(10);
			planMonths.add(11);
			planMonths.add(12);
		}
		if(null!=pms && 0!=pms.size()){
			for(PlanWork2Month pm : pms){
				for(int i=2;i>=0;i--){
					//循环三次，每个月都判断
					if(planYear==pm.getPlanYear().intValue()
							&&null!=pm.getStatusCd()
							&&(Integer)planMonths.get(i)==pm.getPlanMonth().intValue()
							&&(pm.getStatusCd().equalsIgnoreCase(this.STATUS_CD_JINXINGZHONG)
							||pm.getStatusCd().equalsIgnoreCase(this.STATUS_CD_SHENQINGSHANCHU)
							||pm.getStatusCd().equalsIgnoreCase(this.STATUS_CD_SUSPEND)
							||pm.getStatusCd().equalsIgnoreCase(this.STATUS_CD_WANCHENG)
							||pm.getStatusCd().equalsIgnoreCase(this.STATUS_CD_YUWANCHENG))){
						planWork2.setStatusCd(pm.getStatusCd());
						planWork2.setPlanMonth(pm.getPlanMonth());
						break;
					}
				}
			}
		}
	}

	/**
	 * 季度打分中，删除同一个延迟序列的记录
	 * @param oaMeetings
	 */
	private void deleteSameRecord(Page page) {
		BigDecimal weightPointSum = new BigDecimal(0);
		BigDecimal selfPointSum = new BigDecimal(0);
		BigDecimal selfCheckPointSum = new BigDecimal(0);
		BigDecimal evaluatePointSum = new BigDecimal(0);
		BigDecimal finalPointSum = new BigDecimal(0);
		List<PlanWork2> planWork2s = page.getResult();
		List<PlanWork2> returnList = new ArrayList();
		for(int i=0;null!=planWork2s && i<planWork2s.size();i++){
			PlanWork2 pw1 = (PlanWork2)planWork2s.get(i);
			boolean if_has_same = false;
			for(int j=i+1;null!=planWork2s && j<planWork2s.size();j++){
				PlanWork2 pw2 = (PlanWork2)planWork2s.get(j);
				if(null!=pw1.getSuspendId() && null!=pw2.getSuspendId() && pw2.getSuspendId().equalsIgnoreCase(pw1.getSuspendId())){
					if_has_same = true;
					break;
				}
			}
			if(if_has_same){
				planWork2s.remove(i);
				i--;
			}else{
				if(null!=pw1.getWeightPoint()){
					weightPointSum = weightPointSum.add(pw1.getWeightPoint());
				}
				if(null!=pw1.getSelfPoint()){
					selfPointSum = selfPointSum.add(pw1.getSelfPoint());
				}
				if(null!=pw1.getSelfCheckPoint()){
					selfCheckPointSum = selfCheckPointSum.add(pw1.getSelfCheckPoint());
				}
				if(null!=pw1.getEvaluatePoint()){
					evaluatePointSum = evaluatePointSum.add(pw1.getEvaluatePoint());
				}
				if(null!=pw1.getFinalPoint()){
					finalPointSum = finalPointSum.add(pw1.getFinalPoint());
				}
				returnList.add(pw1);
			}
		}
		Struts2Utils.getRequest().setAttribute("weightPointSum", weightPointSum);
		Struts2Utils.getRequest().setAttribute("selfPointSum", selfPointSum);
		Struts2Utils.getRequest().setAttribute("selfCheckPointSum", selfCheckPointSum);
		Struts2Utils.getRequest().setAttribute("evaluatePointSum", evaluatePointSum);
		Struts2Utils.getRequest().setAttribute("finalPointSum", finalPointSum);
		page.setResult(returnList);
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 提醒功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String remind() throws Exception {
		prepareModel();
		if (planWork2Manager.sendRemindEmail(entity, SpringSecurityUtils.getCurrentUiid())) {
			planOperationLogManager.addWorkPlanLog(entity.getPlanWork2Id(),
					entity.getSerialNumber() + entity.getSerialOrder(),
					PlanOperationLogManager.OPERATION_TYPE_UPDATE,
					entity.getCenterCd(), "发送了一封提醒邮件");
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
	
	public PlanWork2 getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public PlanWork2Message getMessage() {
		return message;
	}

	public void setMessage(PlanWork2Message message) {
		this.message = message;
	}

	public String getCenter_status() {
		return center_status;
	}

	public void setCenter_status(String center_status) {
		this.center_status = center_status;
	}

	public Map<String, String> getMapTargetPointCd() {
		return appDictTypeManager
				.getDictDataByTypeCd(DictContants.WORKPLAN_TAR_POINT);
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

	public List<PlanWork2Message> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<PlanWork2Message> messageList) {
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

	public int getNow_month() {
		return now_month;
	}

	public void setNow_month(int nowMonth) {
		now_month = nowMonth;
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
	

	public Map<String, String> getMapPointTips() {
		return mapPointTips;
	}

	public void setMapPointTips(Map<String, String> mapPointTips) {
		this.mapPointTips = mapPointTips;
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

	public String getQuarter_status() {
		return quarter_status;
	}

	public void setQuarter_status(String quarter_status) {
		this.quarter_status = quarter_status;
	}

	public int getNow_quarter() {
		return now_quarter;
	}

	public void setNow_quarter(int now_quarter) {
		this.now_quarter = now_quarter;
	}

	public int getIf_in_weight() {
		return if_in_weight;
	}

	public void setIf_in_weight(int if_in_weight) {
		this.if_in_weight = if_in_weight;
	}

	public int getIf_goto_cost() {
		return if_goto_cost;
	}

	public void setIf_goto_cost(int if_goto_cost) {
		this.if_goto_cost = if_goto_cost;
	}

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

	public boolean isAOnlyCreator() {
		return aOnlyCreator;
	}

	public boolean isCannotChangeDept() {
		return cannotChangeDept;
	}
	
	public boolean isIf_bis_cannot() {
		return if_bis_cannot;
	}
	
	public String getMyUiid() {
		return myUiid;
	}

}
