package com.hhz.ump.web.plan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.plan.CostCheckManager;
import com.hhz.ump.dao.plan.CostCtrlBidPurcManager;
import com.hhz.ump.dao.plan.CostCtrlMessageManager;
import com.hhz.ump.dao.plan.ExecPlanDetailManager;
import com.hhz.ump.entity.plan.CostCtrlBidPurc;
import com.hhz.ump.entity.plan.CostCtrlMessage;
import com.hhz.ump.entity.plan.CostCtrlSchedule;
import com.hhz.ump.entity.plan.ExecPlanDetail;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.web.vo.CostStatistics;
import com.hhz.ump.web.vo.CostStatisticsArea;
import com.hhz.uums.entity.ws.WsPlasOrg;

/**
 * 成本计划管理
 * @author shixy
 *
 * 2011-3-18
 */
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "cost-ctrl-bid-purc!list.action", type = "redirect",params = { "pageType", "${pageType}" })})
public class CostCtrlBidPurcAction extends CrudActionSupport<CostCtrlBidPurc> {

	private static final long serialVersionUID = -3148533344532185438L;

	private static final String NORTH = "n";
	private static final String SOURTH = "s";
	private static final String INVITE_BIDS = "inviteBids";
	private static final String PROCURE = "procure";
	private static final String STRATEGY = "strategy";
	private static final String BIS = "bis";
	private static final String HOTEL = "hotel";
	private static final String CONTRACT = "con";
	private static final String SUPPLIER = "sup";
	private static final String BIDCHECK = "check";
	//招标
	private static final String DATA_TYPE_BID = "0";
	//采购
	private static final String DATA_TYPE_PUR = "1";
	//战略
	private static final String DATA_TYPE_STRA ="2"; //stratage
	//默认合同初始状态 0  表示尚未进入合同阶段
	private static final String CONT_STATUS_DEFAULT = "0";
//	默认0-未核对
//	private static final String BID_CHECK_STATUS_DEFAULT = "0";
	//默认数据状态为 新增
	private static final String BID_STATUS_DEFAULT = "0";
	//默认数据来源为新建 
	private static final String SRC_TYPE_DEFAULT = "0";
	
	@Autowired
	private CostCheckManager costCheckManager;
	@Autowired
	private CostCtrlBidPurcManager costCtrlBidPurcManager;
	@Autowired
	private CostCtrlMessageManager costCtrlMessageManager;
	@Autowired
	private ExecPlanDetailManager execPlanDetailManager;

	private CostCtrlBidPurc entity;
	
	private Map<String, List<CostCtrlBidPurc>> dataMap;
	
	/**
	 * 招标流程进度map
	 */
	private Map<String, String> bidScheMap;
	/**
	* 采购流程进度map
	*/
	private Map<String, String> purScheMap;
	
	private String transmitJson;
	
	/**
	 * 计划类型
	 */
	private Map<String, String> planTypeMap;
	
	/**
	 * 页面类型
	 * 北方招采：n
	 * 南方招采：s
	 * 上海招采：sh
	 * 商业：bis
	 * 合同：con
	 */
	private String pageType;
	
	private String supComFlag;
	
	private String currentUser;
	
	private String targetPageType;
	private String targetId;
	
	private List<CostStatistics> costStatistics;
	private List<CostStatisticsArea> costStatisticsArea0;
	private List<CostStatisticsArea> costStatisticsArea;
	private List<CostStatisticsArea> costStatisticsArea2;

	private Date currMonth;//当前月
	private Date nextMonth;
	private Date prevMonth;
	
	//月份
	private Date month1;
	private Date month2;
	private Date month3;
	
	//搜索定标月份
	private String matchBidDate;
	private int now_year;
	private int now_month;
	
	//定标审批表网批ID和网批CD
	//private String resNo;
	//private String resId;
	/**
	 * 进入主页面
	 */
	@Override
	public String execute() throws Exception {
		if (0==now_year) {
			now_year = DateOperator.getYear(new Date());
		}
		if (0==now_month) {
			now_month = DateOperator.getMonth12(new Date());
		}
		return SUCCESS;
	}
	
	/**
	 * 搜索采购、招标、合同
	 */
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		//每页显示20条记录
		page.setPageSize(30);
		//加排序，否则可能翻页后还会出现这个项目公司的记录
		page.setOrderBy("projectCd,serialNo");
		page.setOrder(Page.ASC+","+Page.ASC);
		
		currentUser = SpringSecurityUtils.getCurrentUiid();
		
		//如果点击的为 已完成 需要做特殊处理
		String scheCd = Struts2Utils.getParameter("filter_EQS_scheduleStatusCd");
		
		PropertyFilter filter = null;
		//定标月份
		if(StringUtils.isNotBlank(matchBidDate)){
			int tmpYear = Integer.valueOf(matchBidDate.substring(0,4)).intValue();
			int tmpMonth = Integer.valueOf(matchBidDate.substring(5)).intValue();
			
			filter = new PropertyFilter("GED_bidDate",DateOperator.getFirstDayOfMonth12(tmpYear, tmpMonth));
			filters.add(filter);
			filter = new PropertyFilter("LED_bidDate",DateOperator.getLastDayOfMonth12(tmpYear, tmpMonth));
			filters.add(filter);
		}
		
		//招标
		if(INVITE_BIDS.equals(pageType)){
			filter = new PropertyFilter("EQS_dataTypeCd", "0");
			filters.add(filter);
			filter = new PropertyFilter("INA_projectCd",
					(PlasCache.getDictDataSplit("ORG_NORTH")+","+PlasCache.getDictDataSplit("ORG_SOUTH")).split(","));
			filters.add(filter);
			addComFilter(filters,scheCd);
		}
		//采购
		else if(PROCURE.equals(pageType)){
			filter = new PropertyFilter("EQS_dataTypeCd", "1");
			filters.add(filter);
			filter = new PropertyFilter("INA_projectCd",
					(PlasCache.getDictDataSplit("ORG_NORTH")+","+PlasCache.getDictDataSplit("ORG_SOUTH")).split(","));
			filters.add(filter);
			addComFilter(filters,scheCd);
		}
		//战略
		else if(STRATEGY.equals(pageType)){
			filter = new PropertyFilter("EQS_dataTypeCd", "2");
			filters.add(filter);
			filter = new PropertyFilter("INA_projectCd",
					(PlasCache.getDictDataSplit("ORG_NORTH")+","+PlasCache.getDictDataSplit("ORG_SOUTH")).split(","));
			filters.add(filter);
			addComFilter(filters,scheCd);
		}
		//商业
		else if(BIS.equals(pageType)){
			filter = new PropertyFilter("INA_projectCd",PlasCache.getDictDataSplit("ORG_BIS").split(","));
			filters.add(filter);
			addComFilter(filters,scheCd);
		}
		//酒店
		else if(HOTEL.equals(pageType)){
			filter = new PropertyFilter("INA_projectCd",PlasCache.getDictDataSplit("ORG_HOTEL").split(","));
			filters.add(filter);
			addComFilter(filters,scheCd);
		}
		//合同
		else if(CONTRACT.equals(pageType)){
			filter = new PropertyFilter("GTS_contractStatusCd",CONT_STATUS_DEFAULT);
			filters.add(filter);
		}
		//标后核对
		else if(BIDCHECK.equals(pageType)){
			//filter = new PropertyFilter("GTS_bidCheckStatusCd",BID_CHECK_STATUS_DEFAULT);
			//filters.add(filter);
		}
		//供应商
		else if(SUPPLIER.equals(pageType)){
			//前台快速搜索特殊处理
			String isSupComFlg = Struts2Utils.getParameter("filter_EQS_isSuppCompleteFlag");
			if(StringUtils.isBlank(isSupComFlg)){
				filter = new PropertyFilter("EQS_isSuppCompleteFlag","1");
				filters.add(filter);
			}
			/*对是否已到合同状态不做控制
			 * filter = new PropertyFilter("EQS_contractStatusCd",CONT_STATUS_DEFAULT);
			filters.add(filter);*/
		}
		
		//默认 搜索 未完成的数据
		if(StringUtils.isBlank(scheCd)){
			filter = new PropertyFilter("LTS_scheduleStatusCd","2");
			filters.add(filter);
		}
		
		//延期
		String delayFlg = Struts2Utils.getParameter("delayFlg");
		if("1".equals(delayFlg)){
			Date currDate = DateUtils.truncate(new Date(), Calendar.DATE);
			//因为搜索条件LTD会将日期加上一天，故此日期也需要加上一天
			currDate = DateUtils.addDays(currDate, -1);
			filter = new PropertyFilter("LTD_bidDate",currDate);
			filters.add(filter);
			filter = new PropertyFilter("EQS_contractStatusCd",CONT_STATUS_DEFAULT);
			filters.add(filter);
			
		}
		//等待
		String waitFlg = Struts2Utils.getParameter("waitFlg");
		if("1".equals(waitFlg)){
			filter = new PropertyFilter("EQS_isWaitProjectFlag_OR_isWaitTechFlag_OR_isWaitOtherFlag","1");
			filters.add(filter);
			
		}
		
		//未删除
		filter = new PropertyFilter("EQS_deleteFlg","0");
		filters.add(filter);
		
		//地产公司人员只能查找本公司的数据
		String centerDeptCd = SpringSecurityUtils.getCurrentCenterCd();
		if(StringUtils.isNotBlank(centerDeptCd)){
			//特殊处理，如果是倪海波，则将玉祁与旺庄数据分配给他
			if("nihb".equals(SpringSecurityUtils.getCurrentUiid())){
				
				filter = new PropertyFilter("INA_projectCd",new String[]{"236","228"});
				filters.add(filter);
			}else{
				WsPlasOrg centerOrg =  PlasCache.getOrgByCd(centerDeptCd);
				//135:各地产公司
				if("135".equals(centerOrg.getPhysicalOrgCd())){
					if("296".equals(centerDeptCd)){
						filter = new PropertyFilter("INA_projectCd",new Object[]{centerDeptCd,"303"});
					}else{
						filter = new PropertyFilter("EQS_projectCd",centerDeptCd);
					}
					filters.add(filter);
				}
			}
		}
		
		if(StringUtils.isNotBlank(targetId)){
			filter = new PropertyFilter("EQS_costCtrlBidPurcId",targetId);
			filters.add(filter);
		}
		
		
		page = costCtrlBidPurcManager.findPage(page, filters);
		
		dataMap = this.groupCostCtrlBidPurc(page);
		
		purScheMap = DictMapUtil.getMapCostPurSche();
		if(purScheMap != null){
			purScheMap.remove("");
		}
		bidScheMap = DictMapUtil.getMapCostBidSche();
		if(bidScheMap != null){
			bidScheMap.remove("");
		}
		
		planTypeMap = initPlanTypeMap();
		
		return 	pageType;
	}
	
	//北方招采
	public String listByUpdate(){
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		//每页显示20条记录
		page.setPageSize(30);
		//加排序，否则可能翻页后还会出现这个项目公司的记录
		page.setOrderBy("updatedDate,serialNo");
		page.setOrder(Page.DESC+","+Page.ASC);
		
		currentUser = SpringSecurityUtils.getCurrentUiid();
		
		
		//如果点击的为 已完成 需要做特殊处理
		String scheCd = Struts2Utils.getParameter("filter_EQS_scheduleStatusCd");
		
		PropertyFilter filter;
		
		//定标月份
		if(StringUtils.isNotBlank(matchBidDate)){
			int tmpYear = Integer.valueOf(matchBidDate.substring(0,4)).intValue();
			int tmpMonth = Integer.valueOf(matchBidDate.substring(5)).intValue();

			filter = new PropertyFilter("GED_bidDate",DateOperator.getFirstDayOfMonth12(tmpYear, tmpMonth));
			filters.add(filter);
			filter = new PropertyFilter("LED_bidDate",DateOperator.getLastDayOfMonth12(tmpYear, tmpMonth));
			filters.add(filter);
		}
		
		if(NORTH.equals(pageType)){
			filter = new PropertyFilter("INA_projectCd",PlasCache.getDictDataSplit("ORG_NORTH").split(","));
			filters.add(filter);
			addComFilter(filters,scheCd);
		}else if(SOURTH.equals(pageType)){
			filter = new PropertyFilter("INA_projectCd",PlasCache.getDictDataSplit("ORG_SOUTH").split(","));
			filters.add(filter);
			addComFilter(filters,scheCd);
		}else if(BIS.equals(pageType)){
			filter = new PropertyFilter("INA_projectCd",PlasCache.getDictDataSplit("ORG_BIS").split(","));
			filters.add(filter);
			addComFilter(filters,scheCd);
		}else if(CONTRACT.equals(pageType)){
			filter = new PropertyFilter("GTS_contractStatusCd",CONT_STATUS_DEFAULT);
			filters.add(filter);
		}else if(SUPPLIER.equals(pageType)){
			//前台快速搜索特殊处理
			String isSupComFlg = Struts2Utils.getParameter("filter_EQS_isSuppCompleteFlag");
			if(StringUtils.isBlank(isSupComFlg)){
				filter = new PropertyFilter("EQS_isSuppCompleteFlag","1");
				filters.add(filter);
			}
			/*对是否已到合同状态不做控制
			 * filter = new PropertyFilter("EQS_contractStatusCd",CONT_STATUS_DEFAULT);
			filters.add(filter);*/
		}
		
		//默认 搜索 未完成的数据
		if(StringUtils.isBlank(scheCd)){
			filter = new PropertyFilter("LTS_scheduleStatusCd","2");
			filters.add(filter);
		}
		
		//延期
		String delayFlg = Struts2Utils.getParameter("delayFlg");
		if("1".equals(delayFlg)){
			Date currDate = DateUtils.truncate(new Date(), Calendar.DATE);
			//因为搜索条件LTD会将日期加上一天，故此日期也需要加上一天
			currDate = DateUtils.addDays(currDate, -1);
			filter = new PropertyFilter("LTD_bidDate",currDate);
			filters.add(filter);
			filter = new PropertyFilter("EQS_contractStatusCd",CONT_STATUS_DEFAULT);
			filters.add(filter);
			
		}
		//等待
		String waitFlg = Struts2Utils.getParameter("waitFlg");
		if("1".equals(waitFlg)){
			filter = new PropertyFilter("EQS_isWaitProjectFlag_OR_isWaitTechFlag_OR_isWaitOtherFlag","1");
			filters.add(filter);
			
		}
		
		//未删除
		filter = new PropertyFilter("EQS_deleteFlg","0");
		filters.add(filter);
		
		//地产公司人员只能查找本公司的数据
		String centerDeptCd = SpringSecurityUtils.getCurrentCenterCd();
		if(StringUtils.isNotBlank(centerDeptCd)){
			WsPlasOrg centerOrg =  PlasCache.getOrgByCd(centerDeptCd);
			if("135".equals(centerOrg.getPhysicalOrgCd())){
				filter = new PropertyFilter("EQS_projectCd",centerDeptCd);
				filters.add(filter);
			}
		}
		
		if(StringUtils.isNotBlank(targetId)){
			filter = new PropertyFilter("EQS_costCtrlBidPurcId",targetId);
			filters.add(filter);
		}
		
		
		page = costCtrlBidPurcManager.findPage(page, filters);
		
		//对map进行按时间排序
		dataMap =new LinkedHashMap<String, List<CostCtrlBidPurc>>();
		dataMap.put("aaa", page.getResult());
		//dataMap = this.groupCostCtrlBidPurc(page);
		
		purScheMap = DictMapUtil.getMapCostPurSche();
		if(purScheMap != null){
			purScheMap.remove("");
		}
		bidScheMap = DictMapUtil.getMapCostBidSche();
		if(bidScheMap != null){
			bidScheMap.remove("");
		}
		
		planTypeMap = initPlanTypeMap();
		
		return 	pageType;
	
	}
	/**
	 * 对【已完成】进行特殊处理
	 * 招采需要搜索出 已经定标和已完成的数据
	 * 默认搜索出尚未定标的数据
	 */
	private void addComFilter(List<PropertyFilter> filters,String scheCd){
		if("2".equals(scheCd)){
			PropertyFilter filter = new PropertyFilter("GTS_contractStatusCd",CONT_STATUS_DEFAULT);
			filters.add(filter);
			for(PropertyFilter f : filters){
				if(f.getPropertyName().equals("scheduleStatusCd")){
					filters.remove(f);
					break;
				}
			}
			filter = new PropertyFilter("EQS_scheduleStatusCd","1");
			filters.add(filter);
		}else{
			PropertyFilter filter = new PropertyFilter("EQS_contractStatusCd",CONT_STATUS_DEFAULT);
			filters.add(filter);
			
		}
	}
	
	/**
	 * 保存采购、招标、合同
	 */
	@Override
	public String save() throws Exception {
		String confirmFlg = Struts2Utils.getParameter("confirmFlg");
		if(StringUtils.isNotBlank(confirmFlg)){
			//设置数据为进行中(已确认)
			entity.setScheduleStatusCd("1");
			addMsg("确认/分配信息！");
		}
		
		String conStatusChangeFlg = Struts2Utils.getParameter("conStatusChangeFlg");
		if(StringUtils.isNotBlank(conStatusChangeFlg)){
			//设置数据进入合同签订
			entity.setContractStatusCd("1");
			addMsg("定标完成，进入合同签订阶段！");
		}
		/*if(StringUtils.isNotBlank(resId)){
			entity.setResApproveId(resId);
			entity.setResApproveId(resNo);
		}*/
		
		String conCompleteFlg = Struts2Utils.getParameter("conCompleteFlg");
		if(StringUtils.isNotBlank(conCompleteFlg)){
			//设置数据为已完成
			entity.setScheduleStatusCd("2");
			String dataType = entity.getDataTypeCd();
			//设置合同签署的日期和状态
			List<CostCtrlSchedule> scheList = entity.getCostCtrlSchedules();
			scheList.get(scheList.size()-1).setCompleteStatusCd("1");
			scheList.get(scheList.size()-1).setCompleteDate(new Date());
			if("0".equals(dataType)){
				//设置状态为合同签订
				entity.setBidStatusCd("8");
			}else if("2".equals(dataType)){
				//3为战略的合同签订
				entity.setBidStatusCd("3");
			}else{
				//设置状态为合同签订
				entity.setBidStatusCd("6");
			}
			addMsg("已完成合同签订！");
		}
		
		String conReturnFlg = Struts2Utils.getParameter("conReturnFlg");
		if(StringUtils.isNotBlank(conReturnFlg)){
			//将数据退回到未定标前的一个已完成状态
			List<CostCtrlSchedule> scheArray = entity.getCostCtrlSchedules();
			String scheCd = "";
			for(int i=0;i<scheArray.size();i++){
				CostCtrlSchedule schedule = scheArray.get(i);
				if(i == scheArray.size()-1) {
					schedule.setCompleteStatusCd("0");
					break;
				}
				if("1".equals(schedule.getCompleteStatusCd())){
					scheCd = schedule.getScheduleTypeCd();
				}
			}

			//标段状态: 最后一个环节
			entity.setBidStatusCd(scheCd);
			entity.setBidDate(null);
			entity.setContractStatusCd("0");
			entity.setScheduleStatusCd("1");
			
			addMsg("数据退回到未定标状态！");
		}
		
		if(StringUtils.isBlank(getId())){
			Date bidDate = entity.getBidDate();
			//如果是商业模块，则定标时间为
			String projectCd = Struts2Utils.getParameter("projectCd");
			if(isBisProject(projectCd)){
				if("0".equals(entity.getDataTypeCd())||"1".equals(entity.getDataTypeCd())){
					entity.getCostCtrlSchedules().get(6).setTargetDate(bidDate);
				}else{
					entity.getCostCtrlSchedules().get(1).setTargetDate(bidDate);
				}
				
			}else{
				//非商业
				if("0".equals(entity.getDataTypeCd())||"1".equals(entity.getDataTypeCd())){
					entity.getCostCtrlSchedules().get(5).setTargetDate(bidDate);
				}else{
					entity.getCostCtrlSchedules().get(1).setTargetDate(bidDate);
				}
			}
		}
		
		//add by huangbijin 2011/08/25
		//若填写审核时间,生成标后核对记录
		String chiefDate = Struts2Utils.getParameter("chiefDate");
		if(StringUtils.isNotBlank(chiefDate)){
			entity.setBidCheckStatusCd("1");//是否标后核对: 1-是 0-否
		}
		
		costCtrlBidPurcManager.saveCostCtrlBidPurc(entity);
		
		//若填写审核日期,生成标后核对
		if(StringUtils.isNotBlank(chiefDate)){
			costCheckManager.importFromPur(entity, chiefDate);
		}
		
		return RELOAD;
	}

	/**
	 * 删除采购、招标
	 */
	@Override
	public String delete() throws Exception {
		entity.setDeleteFlg("1");
		costCtrlBidPurcManager.saveCostCtrlBidPurc(entity);
		return null;
	}
	
	public void saveMsg() {
		String msgContent = Struts2Utils.getParameter("msg");
		CostCtrlMessage msg = new CostCtrlMessage();
		msg.setCostCtrlBidPurc(entity);
		msg.setMsgContentDesc(msgContent);
		costCtrlMessageManager.saveCostCtrlMessage(msg);
		String curUser = SpringSecurityUtils.getCurrentUserName();
		String now = DateOperator.formatDate(new Date(), "MM-dd HH:mm");
		Struts2Utils.renderText(curUser+"("+now+"):"+msgContent);
	}
	public void saveFlg(){
		if(StringUtils.isNotBlank(supComFlag)){
			entity.setIsSuppCompleteFlag(supComFlag);
			costCtrlBidPurcManager.saveCostCtrlBidPurc(entity);
		}
	}
	
	/**
	 * 招采完成情况统计(方法停用)
	 */
	public String stat(){
		if(currMonth == null){
			currMonth = new Date();
		}
		String year = String.valueOf(DateOperator.getYear(currMonth));
		String month = String.valueOf(DateOperator.getMonth12(currMonth));
		nextMonth = DateOperator.addMonthes(currMonth, 1);
		prevMonth = DateOperator.addMonthes(currMonth, -1);
		if(month.length()<2){
			month = "0"+month;
		}
		
		costStatistics = costCtrlBidPurcManager.getCostStatistics(year,month);
		return "stat";
	}

	/**
	 * 分区域(方法停用)
	 * @return
	 */
	public String statArea(){
		
		if(currMonth == null){
			month1 = new Date();
			month2 = DateOperator.addMonthes(new Date(), -1);
			month3 = DateOperator.addMonthes(new Date(), -2);
			
			prevMonth = DateOperator.addMonthes(month1, -1);
			nextMonth = DateOperator.addMonthes(month1, 1);
		}else{
			month1 = currMonth;
			month2 = DateOperator.addMonthes(month1, -1);
			month3 = DateOperator.addMonthes(month1, -2);
			
			prevMonth = DateOperator.addMonthes(month1, -1);
			nextMonth = DateOperator.addMonthes(month1, 1);
		}

		//第一月
		String strY1 = String.valueOf(DateOperator.getYear(month1));
		String strM1 = String.valueOf(DateOperator.getMonth12(month1));
		if(strM1.length()<2){
			strM1 = "0"+strM1;
		}
		costStatisticsArea0 = costCtrlBidPurcManager.getCostStatisticsArea(strY1,strM1);
		
		//第二月
		String strY2 = String.valueOf(DateOperator.getYear(month2));
		String strM2 = String.valueOf(DateOperator.getMonth12(month2));
		if(strM2.length()<2){
			strM2 = "0"+strM2;
		}
		costStatisticsArea = costCtrlBidPurcManager.getCostStatisticsArea(strY2,strM2);
	
		//第三月
		String strY3 = String.valueOf(DateOperator.getYear(month3));
		String strM3 = String.valueOf(DateOperator.getMonth12(month3));
		if(strM3.length()<2){
			strM3 = "0"+strM3;
		}
		costStatisticsArea2 = costCtrlBidPurcManager.getCostStatisticsArea(strY3,strM3);

		
		return "statArea";
	}


	/**
	 * 不分区域(总部分招标、采购)
	 * add by huangbijin 2012-050-31 
	 * @return
	 */
	public String statZc(){
		
		if(currMonth == null){
			month1 = new Date();
			month2 = DateOperator.addMonthes(new Date(), -1);
			month3 = DateOperator.addMonthes(new Date(), -2);
			
			prevMonth = DateOperator.addMonthes(month1, -1);
			nextMonth = DateOperator.addMonthes(month1, 1);
		}else{
			month1 = currMonth;
			month2 = DateOperator.addMonthes(month1, -1);
			month3 = DateOperator.addMonthes(month1, -2);
			
			prevMonth = DateOperator.addMonthes(month1, -1);
			nextMonth = DateOperator.addMonthes(month1, 1);
		}

		//第一月
		String strY1 = String.valueOf(DateOperator.getYear(month1));
		String strM1 = String.valueOf(DateOperator.getMonth12(month1));
		if(strM1.length()<2){
			strM1 = "0"+strM1;
		}
		costStatisticsArea0 = costCtrlBidPurcManager.getCostStatisticsZc(strY1,strM1);
		
		//第二月
		String strY2 = String.valueOf(DateOperator.getYear(month2));
		String strM2 = String.valueOf(DateOperator.getMonth12(month2));
		if(strM2.length()<2){
			strM2 = "0"+strM2;
		}
		costStatisticsArea = costCtrlBidPurcManager.getCostStatisticsZc(strY2,strM2);
	
		//第三月
		String strY3 = String.valueOf(DateOperator.getYear(month3));
		String strM3 = String.valueOf(DateOperator.getMonth12(month3));
		if(strM3.length()<2){
			strM3 = "0"+strM3;
		}
		costStatisticsArea2 = costCtrlBidPurcManager.getCostStatisticsZc(strY3,strM3);

		
		return "statZc";
	}
	
	/**
	 * 对数据按照项目进行分组
	 * @param costPage
	 * @return
	 */
	private Map<String, List<CostCtrlBidPurc>> groupCostCtrlBidPurc(Page<CostCtrlBidPurc> costPage){
		LinkedHashMap<String, List<CostCtrlBidPurc>> map = new LinkedHashMap<String, List<CostCtrlBidPurc>>();
		for(CostCtrlBidPurc bidPurc : costPage.getResult() ){
			String prjCd = bidPurc.getProjectCd();
			if(map.containsKey(prjCd)){
				map.get(prjCd).add(bidPurc);
			}else{
				List<CostCtrlBidPurc> list = new ArrayList<CostCtrlBidPurc>();
				list.add(bidPurc);
				map.put(prjCd, list);
			}
		}
		return map;
	}
	
	/**
	 * 添加留言
	 * @param content
	 */
	private void addMsg(String content){
		CostCtrlMessage msg = new CostCtrlMessage();
		msg.setCostCtrlBidPurc(entity);
		msg.setMsgContentDesc("【"+content+"】");
		entity.getCostCtrlMessages().add(msg);
	}

	@Override
	protected void prepareModel() throws Exception {
		if(getId() != null){
			entity = costCtrlBidPurcManager.getEntity(getId());
		}else{
			entity = new CostCtrlBidPurc();
			initEntity();
		}
	}
	
	private Map<String, String> initPlanTypeMap(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("0", "计划内");
		map.put("1", "计划外");
		map.put("2", "半年计划");
		map.put("3", "执行计划");
		return map;
	}
	
	public void prepareSaveMsg() throws Exception{
		prepareModel();
	}
	public void prepareDelete() throws Exception{
		prepareModel();
	}
	/**
	 * 更新供应商状态
	 * @throws Exception
	 */
	public void prepareSaveFlg() throws Exception{
		prepareModel();
	}
	/**
	 * 新增招标、采购时初始化数据
	 */
	private void initEntity(){
		StringBuilder newMsg = new StringBuilder();
		Map<String, String> scheMap = new HashMap<String, String>();
		//是否进入合同管理 默认为0 不进入
		entity.setContractStatusCd(CONT_STATUS_DEFAULT);
//		entity.setBidCheckStatusCd(BID_CHECK_STATUS_DEFAULT);
		//设置数据状态为 新增
		entity.setScheduleStatusCd(BID_STATUS_DEFAULT);
		//设置数据来源为新增
		entity.setDataSrcTypeCd(SRC_TYPE_DEFAULT);
		//设置供应商为 未确认
		entity.setIsSuppCompleteFlag("0");
		entity.setDeleteFlg("0");
		
		String dataTypeCd = Struts2Utils.getParameter("dataTypeCd");
		//先判断是否商业公司，如果是商业公司，则scheMap为商业公司的数据字典数据，否则为其他
		String projectCd = Struts2Utils.getParameter("projectCd");
		
		if(isBisProject(projectCd)&&!DATA_TYPE_STRA.equals(dataTypeCd)){
			scheMap = DictMapUtil.getMapCostBisSche();
		}else{
			if(DATA_TYPE_BID.equals(dataTypeCd)){
				scheMap = DictMapUtil.getMapCostBidSche();
				newMsg.append("新增一条招标记录。");
			}else if(DATA_TYPE_PUR.equals(dataTypeCd)){
				scheMap = DictMapUtil.getMapCostPurSche();
				newMsg.append("新增一条采购记录。");
			}else if(DATA_TYPE_STRA.equals(dataTypeCd)){
				scheMap = DictMapUtil.getMapCostStratage();
				newMsg.append("新增一条战略记录。");
			}
		}
		
		//初始化进度数据
		List<CostCtrlSchedule> scheArray = new ArrayList<CostCtrlSchedule>();
		if(scheMap != null){
			scheMap.remove("");
			int i = 1;
			for(String key : scheMap.keySet()){
				CostCtrlSchedule sche = new CostCtrlSchedule();
				sche.setSerialNo(i);
				sche.setScheduleTypeCd(key);
				sche.setScheduleTypeName(scheMap.get(key));
				sche.setCostCtrlBidPurc(entity);
				scheArray.add(sche);
				i++;
			}
		}
		entity.setCostCtrlSchedules(scheArray);
		addMsg(newMsg.toString());
	}
	/**
	 * 新增从半年计划来源的计划
	 */
	public String yearPlanSave() throws Exception {
		Map<String, String> scheMap = new HashMap<String, String>();
		//JSONArray arr = fetchData(transmitJson);
		//List<CostCtrlBidPurc> costCtrlBidList =new ArrayList<CostCtrlBidPurc>();
		//for (int i = 0; i < arr.size(); i ++) {
			StringBuilder newMsg = new StringBuilder();
		    //JSONObject o = arr.getJSONObject(i);
		    String execDetailId = Struts2Utils.getRequest().getParameter("execDetailId");
		    ExecPlanDetail epd = execPlanDetailManager.getEntity(execDetailId);
		    String planType = "3";
		    String content = epd.getExecPlanLayout().getExecutionPlanName()+"-"+epd.getExecPlanNode().getNodeName();
		    String projectCd = epd.getProjectCd();
		    String nowPageType = "bid";
		    entity =new CostCtrlBidPurc();
		    entity.setBidDate(epd.getScheduleEndDate());
		    entity.setPlanContentDesc(content);
		    entity.setDataSrcId(execDetailId);
		    entity.setProjectCd(projectCd);
		    entity.setImportantTypeCd("1");
		    //合同状态为0，表示尚未进入合同状态
			entity.setContractStatusCd(CONT_STATUS_DEFAULT);
			//数据状态
			entity.setScheduleStatusCd(BID_STATUS_DEFAULT);
			//设置供应商为 未确认
			entity.setIsSuppCompleteFlag("0");
			//状态：半年计划或执行计划
			entity.setPlanTypeCd(planType);
			entity.setDataSrcStatusCd("0");
			entity.setDataSrcTypeCd(planType);
			entity.setDeleteFlg("0");
			//招标、采购
			if("bid".equals(nowPageType)){
				entity.setDataTypeCd(DATA_TYPE_BID);
				scheMap = DictMapUtil.getMapCostBidSche();
				newMsg.append("推送一条招标记录。");
			}else if("pur".equals(nowPageType)){
				entity.setDataTypeCd(DATA_TYPE_PUR);
				scheMap = DictMapUtil.getMapCostPurSche();
				newMsg.append("推送一条采购记录。");
			}
			//初始化进度数据
			List<CostCtrlSchedule> scheArray = new ArrayList<CostCtrlSchedule>();
			CostCtrlSchedule sche  = null;
			if(scheMap != null){
				scheMap.remove("");
				int j = 1;
				for(String key : scheMap.keySet()){
					sche = new CostCtrlSchedule();
					sche.setSerialNo(j);
					sche.setScheduleTypeCd(key);
					sche.setScheduleTypeName(scheMap.get(key));
					sche.setCostCtrlBidPurc(entity);
					scheArray.add(sche);
					j++;
				}
			}
			entity.setCostCtrlSchedules(scheArray);
			//costCtrlBidList.add(entity);
			addMsg(newMsg.toString());
			costCtrlBidPurcManager.saveCostCtrlBidPurcByPlan(entity);
		//}
		//costCtrlBidPurcManager.batchCostSaveByPlan(costCtrlBidList);
		Struts2Utils.renderText("ok");
		return null;
	}
	/**
	 * 判断是否是商业招采
	 * @param projectCd
	 * @return
	 */
	private boolean isBisProject(String projectCd){
		String[] projStr = PlasCache.getDictDataSplit("ORG_BIS").split(",");
		boolean isBisAdd =false;
		if(projStr!=null&&projStr.length>0){
			for(int i=0;i<projStr.length;i++){
				if(projStr[i].equals(projectCd)){
					isBisAdd =true;
				}
			}
		}
		return isBisAdd;
	}
	
	private JSONArray fetchData(String transmitData){
		if (StringUtils.isNotBlank(transmitData)) {
		    JSONObject jo = (JSONObject) JSONSerializer.toJSON(transmitData);
		    if (jo != null) {
				JSONArray jsonArray = jo.getJSONArray("data");
				return jsonArray;
			}
		}
		return null;
	}
	public String supply() throws Exception{
		if(StringUtils.isNotBlank(getId())){
			entity =costCtrlBidPurcManager.getEntity(getId());
		}
		return "suppliers";
	} 
	
	public void prepareSupply() throws Exception{
		this.prepareModel();
	}
	
	@Override
	public CostCtrlBidPurc getModel() {
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}
	@Override
	public String input() throws Exception {
		return null;
	}

	public CostCtrlBidPurc getEntity() {
		return entity;
	}

	public void setEntity(CostCtrlBidPurc entity) {
		this.entity = entity;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public Map<String, List<CostCtrlBidPurc>> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, List<CostCtrlBidPurc>> dataMap) {
		this.dataMap = dataMap;
	}


	public Map<String, String> getBidScheMap() {
		return bidScheMap;
	}

	public void setBidScheMap(Map<String, String> bidScheMap) {
		this.bidScheMap = bidScheMap;
	}

	public Map<String, String> getPurScheMap() {
		return purScheMap;
	}

	public void setPurScheMap(Map<String, String> purScheMap) {
		this.purScheMap = purScheMap;
	}

	public String getTransmitJson() {
		return transmitJson;
	}

	public void setTransmitJson(String transmitJson) {
		this.transmitJson = transmitJson;
	}


	public Map<String, String> getPlanTypeMap() {
		return planTypeMap;
	}

	public void setPlanTypeMap(Map<String, String> planTypeMap) {
		this.planTypeMap = planTypeMap;
	}

	public String getSupComFlag() {
		return supComFlag;
	}

	public void setSupComFlag(String supComFlag) {
		this.supComFlag = supComFlag;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public String getTargetPageType() {
		return targetPageType;
	}

	public void setTargetPageType(String targetPageType) {
		this.targetPageType = targetPageType;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public List<CostStatistics> getCostStatistics() {
		return costStatistics;
	}

	public void setCostStatistics(List<CostStatistics> costStatistics) {
		this.costStatistics = costStatistics;
	}

	public Date getCurrMonth() {
		return currMonth;
	}

	public void setCurrMonth(Date currMonth) {
		this.currMonth = currMonth;
	} 
	public List<CostStatisticsArea> getCostStatisticsArea() {
		return costStatisticsArea;
	}

	public void setCostStatisticsArea(List<CostStatisticsArea> costStatisticsArea) {
		this.costStatisticsArea = costStatisticsArea;
	}

	public List<CostStatisticsArea> getCostStatisticsArea2() {
		return costStatisticsArea2;
	}

	public void setCostStatisticsArea2(List<CostStatisticsArea> costStatisticsArea2) {
		this.costStatisticsArea2 = costStatisticsArea2;
	} 

	public List<CostStatisticsArea> getCostStatisticsArea0() {
		return costStatisticsArea0;
	}

	public void setCostStatisticsArea0(List<CostStatisticsArea> costStatisticsArea0) {
		this.costStatisticsArea0 = costStatisticsArea0;
	}

	public Date getNextMonth() {
		return nextMonth;
	}

	public void setNextMonth(Date nextMonth) {
		this.nextMonth = nextMonth;
	}

	public Date getPrevMonth() {
		return prevMonth;
	}

	public void setPrevMonth(Date prevMonth) {
		this.prevMonth = prevMonth;
	}

	public Date getMonth1() {
		return month1;
	}

	public void setMonth1(Date month1) {
		this.month1 = month1;
	}

	public Date getMonth2() {
		return month2;
	}

	public void setMonth2(Date month2) {
		this.month2 = month2;
	}

	public Date getMonth3() {
		return month3;
	}

	public void setMonth3(Date month3) {
		this.month3 = month3;
	}

	public String getMatchBidDate() {
		return matchBidDate;
	}

	public void setMatchBidDate(String matchBidDate) {
		this.matchBidDate = matchBidDate;
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
	
	private String formatStr(String t){
		return StringUtils.isBlank(t)?"":t.trim();
	}

	/**
	 * 网批使用查询"招标采购计划表"
	 * @value 招标采购编号（完全匹配)/事项描述
	 */
	public String quickSearchCcbp(){

		String value = Struts2Utils.getParameter("value");
		String projectCd = Struts2Utils.getParameter("projectCd");
		String dataTypeCd = Struts2Utils.getParameter("dataTypeCd");//0-招标 1-采购 2-战略

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("deleteFlg", "0");//0-正常  
		
		StringBuffer hql = new StringBuffer(" from CostCtrlBidPurc t where t.deleteFlg = :deleteFlg ");
		
		if(StringUtils.isNotBlank(value)){
			values.put("serialTypeNo", formatStr(value));//编号完全匹配
			values.put("contentDesc", "%" + formatStr(value)+"%");//名称模糊匹配
			hql.append(" and (t.serialType||serialNo = :serialTypeNo or t.planContentDesc like :contentDesc) ");
		}
		if(StringUtils.isNotBlank(projectCd)){
			values.put("projectCd", formatStr(projectCd));
			hql.append(" and (t.projectCd = :projectCd) ");
		}
		if(StringUtils.isNotBlank(dataTypeCd)){
			values.put("dataTypeCd", formatStr(dataTypeCd));
			hql.append(" and (t.dataTypeCd = :dataTypeCd) ");
		}		
		
		hql.append(" order by t.createdDate desc ");
		page.setPageSize(20);
		page = costCtrlBidPurcManager.findPage(page, hql.toString(),values);
		
		
		Map<String,String> importMap = new HashMap<String,String>();
		importMap.put("0","低");
		importMap.put("1","普通");
		importMap.put("2","重要");
		
		Map<String,String> dataTypeMap = new HashMap<String,String>();
		dataTypeMap.put("0","招标");
		dataTypeMap.put("1","采购");
		dataTypeMap.put("2","战略");

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		//标题行
		Map<String, String> map = new HashMap<String,String>();
		//标题行
		map.put("headFlg", "1");
		//招采编号
		map.put("ccbpNo", "计划编号");
		map.put("ccbpProjectName", "项目名称");
		//重要等级
		map.put("ccbpImportName", "重要等级");
		//计划类型
		map.put("ccbpPlanTypeName", "计划类型");
		//计划描述
		map.put("ccbpPlanContentDesc", "事项");
		list.add(map);
		
		
		//列： 招采ID，项目cd,项目名称,重要等级,计划类型,事项
		String projectName = null;
		WsPlasOrg torg = null; 
		for (CostCtrlBidPurc tmp : page.getResult()) {
			map = new HashMap<String, String>();
			torg = PlasCache.getOrgByCd(tmp.getProjectCd());
			if(torg == null){
				projectName = "";
			}else{
				projectName = formatStr(torg.getOrgName());
			}
			//招采ID
			map.put("ccbpId", tmp.getCostCtrlBidPurcId());
			//招采编号
			map.put("ccbpNo", formatStr(tmp.getSerialType()) + formatStr(String.valueOf(tmp.getSerialNo())));
			map.put("ccbpProjectCd", formatStr(tmp.getProjectCd()));
			map.put("ccbpProjectName", projectName);
			//重要等级
			map.put("ccbpImportName", formatStr(importMap.get(tmp.getImportantTypeCd())));
			//类型
			map.put("ccbpDataTypeName", formatStr(dataTypeMap.get(tmp.getDataTypeCd())));
			//计划描述
			map.put("ccbpPlanContentDesc", formatStr(tmp.getPlanContentDesc()));
			
			list.add(map);
		}
		Struts2Utils.renderJson(list);
		
		return null;
	}
}
