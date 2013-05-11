package com.hhz.ump.web.plan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.RandomUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.plan.PlanExecDetailMessManager;
import com.hhz.ump.dao.plan.PlanExecPlanDetailManager;
import com.hhz.ump.dao.plan.PlanExecutionPlanMainManager;
import com.hhz.ump.dao.plan.PlanExecutionPlanManager;
import com.hhz.ump.dao.plan.PlanExecutionPlanNodeManager;
import com.hhz.ump.dao.plan.PlanOperationLogManager;
import com.hhz.ump.dao.plan.PlanProjectNodeRelManager;
import com.hhz.ump.dao.plan.PlanWorkCenterManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.plan.PlanCenterDetailsVO;
import com.hhz.ump.entity.plan.PlanExecDetailMess;
import com.hhz.ump.entity.plan.PlanExecPlanDetail;
import com.hhz.ump.entity.plan.PlanExecutionPlan;
import com.hhz.ump.entity.plan.PlanExecutionPlanMain;
import com.hhz.ump.entity.plan.PlanExecutionPlanNode;
import com.hhz.ump.entity.plan.PlanProjectNodeRel;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.util.OrgTreeUtil;
import com.hhz.ump.web.vo.EntityVO;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 类名 PlanExecutionPlanAction 创建者 李劲 创建日期 2010-5-25 描述 控制计划模块
 * planTypeCd   1:三级计划   5:设计计划    14：商业四级计划   15：商业开业筹备五级计划   16:商业开业前招商五级计划
 * plan_execution_plan		业态表			project_cd:项目
plan_execution_plan_main	项目表
plan_execution_plan_node	节点模板表
plan_exec_plan_detail		节点明细表		execution_plan_cd：业态	plan_project_node_rel_id：关联表ID
plan_project_node_rel		项目-业态-节点关联表	plan_execution_plan_main_id:项目ID	node_cd：模板cd	
 * 
 */
@Namespace("/plan")
@Results( { @Result(name = "matrix", location = "/WEB-INF/content/plan/plan-exec-plan-matrix.ftl", type = "freemarker") })
public class PlanExecPlanAction extends ActionSupport {

	private static final long serialVersionUID = -5833728668625630228L;

	@Autowired
	private PlanExecutionPlanMainManager planMainManager;
	@Autowired
	private PlanExecutionPlanNodeManager planNodeManager;
	@Autowired
	private PlanExecutionPlanManager planManager;
	@Autowired
	private PlanProjectNodeRelManager projNodeRelManager;
	@Autowired
	private PlanExecPlanDetailManager detailManager;
	@Autowired
	private PlanExecDetailMessManager messageManager;
	@Autowired
	private AppAttachFileManager attachManager;
	@Autowired
	private PlanWorkCenterManager planWorkCenterManager;

	private String projectCd;
	private String projectName;// 发起中心任务
	private PlanExecutionPlanMain planExecutionPlanMain;
	private String executionPlanMainId;
	private String projNodeId;
	private String nodeCompleteDate;
	private String projPlanId;
	private String projPlanName;
	private String projPlanState;
	private String projPlanCd;
	List<PlanCenterDetailsVO> detailsList;
	private Map<String, PlanExecPlanDetail> planDetailMap;
	private Map<String, PlanProjectNodeRel> parentRelMap;

	private String userPoolPersons;
	private String planDetailId;
	private String planDetailTempId;
	private String planDetailStatus;
	private int planMatrixWidth;
	private PlanExecPlanDetail planDetailEntity;

	private String outputBizEntityId;
	private String projReminder;
	private String nodeReminder;

	private String scheduleStartDate;
	private String scheduleEndDate;
	private String realEndDate;
	private String planDetailCaption;
	private String outputUpdateType;
	private String deletedOutputFileId;
	private String planType; // 控制计划 "control"
	private String centerCd; // 搜索条件的中心CD
	private String outFileTile; // 输出成果提示
	private boolean activeBl;

	private String searchPlans; // 搜索的业态的字符串

	private String filter_GED_scheduleStartDate; // 高级搜索：计划开始时间
	private String filter_LED_scheduleStartDate; // 高级搜索：计划开始时间
	private String filter_GED_scheduleEndDate; // 高级搜索：计划完成时间
	private String filter_LED_scheduleEndDate; // 高级搜索：计划完成时间
	private String filter_GED_realEndDate; // 高级搜索：实际完成时间
	private String filter_LED_realEndDate; // 高级搜索：实际完成时间
	private String search_status; // 高级搜索：状态

	private List<WsPlasOrg> projects;
	private List<PlanProjectNodeRel> viewPlanNodes; // 显示的节点列表
	private List<PlanExecutionPlan> plans;
	private List<PlanExecutionPlan> searchedPlans;
	private List<AppAttachFile> planDetailOutput;
	private Map<String, String> planDetailStatusMap;
	private Map<String, String> mapProjectsType;
	private Map<String, String> mapPrintHtml; // 列表中显示的HTML

	private boolean aExecSuperAdmin = false; // 是否是超级管理员（能修改所有节点）
	private boolean aExecAdmin = false; // 是否是管理员（能管理业态等）
	private boolean aAddPoint = false; // 能否新增节点
	private boolean aConfirmPoint = false; // 能否确认节点
	private boolean aWritePoint = false; // 能否填写节点
	private boolean aCompletePoint = false; // 能否完成节点
	private boolean aChangeSchedule = false; // 能否修改计划时间

	private Date currentDate;

	private boolean ifProjectChange = false; // 是否切换项目，如果是就启动选择所有业态

	// 默认显示 1-执行计划
	private String planTypeCd = DictContants.PLAN_TYPE_EXC;	//字典已不用，参考文件头部说明

	// 所有中心列表
	private List<EntityVO> resOrgListExec = new ArrayList<EntityVO>();
	private List<EntityVO> resOrgListPre = new ArrayList<EntityVO>();
	private List<EntityVO> resOrgListCost = new ArrayList<EntityVO>();

	// 主责方
	private String resOrgCd;// 发起中心任务,前台取值
	private String resOrgName;

	// 发起中心任务
	private List<Map<String, String>> planWorkCenterMaps;
	private String costResOrgCd;
	private String linkSource;

	// 查看模式 1-是,其他 -否
	private String viewModel;

	// 查看树等级: 一级/二级/三级
	private String nowPointLevel = "1";
	private String nowViewStyle = "1";
	private String nowResOrgNames = "";

	/**
	 * 功能: 前期计划
	 * 
	 * @return
	 * @throws Exception
	 */
	public String portalPre() throws Exception {
		portal();
		planTypeCd = DictContants.PLAN_TYPE_PRE;
		return "portal";
	}

	/**
	 * 功能: 项目计划
	 * 
	 * @return
	 * @throws Exception
	 */
	public String portal() throws Exception {

		// 构造项目切换菜单
		projects = fetchAllProjects();

		// 默认显示即墨地产
		if (StringUtils.isBlank(projectCd)) {
			projectCd = "288";
		}

		currentDate = DateOperator.getDateNow();

		// 构造权限
		buildPermission(projectCd, null);

		// 构造中心列表
		resOrgListExec = projNodeRelManager.getResOrgList(DictContants.PLAN_TYPE_EXC);
		resOrgListPre = projNodeRelManager.getResOrgList(DictContants.PLAN_TYPE_PRE);
		resOrgListCost = projNodeRelManager.getResOrgList(DictContants.PLAN_TYPE_COST);

		plans = planManager.getPlansPerProjCd(projectCd, planTypeCd, true);

		return "portal";
	}

	public String getCenterPlanRel() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = format.format(DateOperator.getDateNow());
		String nextMonthDate = format.format(DateOperator.addMonthes(DateOperator.getDateNow(), 1));
		if (null == centerCd || "".equalsIgnoreCase(centerCd)) {
			WsPlasOrg my_ZX = SpringSecurityUtils.getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX);
			centerCd = my_ZX.getOrgCd();
		}
		detailsList = planMainManager.fetchCenterDetails(nowDate, nextMonthDate, centerCd, projectCd, false);

		return "centerRel";
	}

	/**
	 * 成本控制计划的执行计划
	 * 
	 * @return
	 */
	public String getCostCtrl() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = format.format(DateOperator.getDateNow());
		String nextMonthDate = format.format(DateOperator.addMonthes(DateOperator.getDateNow(), 1));
		detailsList = planMainManager.fetchCenterDetails(nowDate, nextMonthDate, "17", projectCd, false);

		return "costCtrl";
	}

	/**
	 * 功能: 显示某地产公司的执行计划信息表格
	 * 
	 * @param projectCd
	 * @param planTypeCd
	 * @param planType
	 * @param resOrgCd
	 * @param resOrgName
	 * @return
	 * @throws Exception
	 */
	public String planMatrix() throws Exception {
		currentDate = DateOperator.getDateNow();
		preparePlanMatrixData();
		return "matrix";
	}

	/**
	 * 功能: 为显示某地产公司的计划信息准备数据
	 * 
	 * @param projectCd
	 * @param planTypeCd
	 * @param planType
	 * @param resOrgCd
	 * @param resOrgName
	 * 
	 */
	private void preparePlanMatrixData() {
		// 默认显示郑州地产
		if (StringUtils.isBlank(projectCd)) {
			projectCd = "288";
		}
		buildPermission(projectCd, null);

		// 所有业态
		plans = planManager.getPlansPerProjCd(projectCd, planTypeCd, true);
		if (ifProjectChange) {
			// 如果是部门切换，选择所有业态
			searchPlans = "";
			for (PlanExecutionPlan pep : plans) {
				searchPlans += pep.getExecutionPlanCd() + ",";
			}
			if (!"".equalsIgnoreCase(searchPlans)) {
				searchPlans = searchPlans.substring(0, searchPlans.length() - 1);
			}
		}
		// 所选业态
		try {
			if (null != searchPlans) {
				searchedPlans = new ArrayList<PlanExecutionPlan>();
				String[] arr_plans = searchPlans.split(",");
				for (PlanExecutionPlan pep : plans) {
					for (String compare_plan : arr_plans) {
						if (compare_plan.equalsIgnoreCase(pep.getExecutionPlanCd())) {
							searchedPlans.add(pep);
							break;
						}
					}
				}
			} else {
				searchedPlans = new ArrayList<PlanExecutionPlan>();
			}
		} catch (Exception e) {
			searchedPlans = plans;
		}

		// 计划主表
		planExecutionPlanMain = planMainManager.getExecutionPlanMainByProjCd(projectCd, planTypeCd);

		// 所有节点
		// planNodes = buildProjPlanNodes(projectCd, planTypeCd, planType,resOrgCd,resOrgName,null);
		// 显示节点
		viewPlanNodes = buildProjPlanNodes(projectCd, planTypeCd, planType, resOrgCd, resOrgName, nowPointLevel, nowResOrgNames);

		parentRelMap = new HashMap<String, PlanProjectNodeRel>();
	    if("5".equalsIgnoreCase(planTypeCd)){
	    	//如果专项计划，处理关联节点
	    	for(int i=0;null!=viewPlanNodes && i<viewPlanNodes.size();i++){
	    		PlanProjectNodeRel ppnr = (PlanProjectNodeRel)viewPlanNodes.get(i);
	    		if(null!=ppnr.getTreeParentNodeId()){
	    			PlanProjectNodeRel tempppnr = projNodeRelManager.getSameProjectNodeRel(ppnr);
	    			viewPlanNodes.set(i, tempppnr);
	    			parentRelMap.put(tempppnr.getPlanProjectNodeRelId(), ppnr);
	    		}
	    	}
	    }
	    
		// 节点与业态映射关系
		planDetailMap = buildPlanDetailMap(viewPlanNodes, null, searchPlans, filter_GED_scheduleStartDate, filter_LED_scheduleStartDate,
				filter_GED_scheduleEndDate, filter_LED_scheduleEndDate, filter_GED_realEndDate, filter_LED_realEndDate, search_status, nowViewStyle, nowResOrgNames);

		mapPrintHtml = new HashMap<String, String>();
		Set<Map.Entry<String, PlanExecPlanDetail>> set = planDetailMap.entrySet();
		for (Iterator<Map.Entry<String, PlanExecPlanDetail>> it = set.iterator(); it.hasNext();) {
			try {
				Map.Entry<String, PlanExecPlanDetail> entry = (Map.Entry<String, PlanExecPlanDetail>) it.next();
				PlanExecPlanDetail pepd = entry.getValue();
				if (null != pepd) {
					mapPrintHtml.put(entry.getKey(), getPrintHtml(pepd));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if("1".equalsIgnoreCase(nowViewStyle)){
			//如果是精简模式，去掉没有detail的节点
			boolean if_has = false;
			for(int i=0;null!=viewPlanNodes && i<viewPlanNodes.size();i++){
				PlanProjectNodeRel prel = (PlanProjectNodeRel)viewPlanNodes.get(i);
				if_has = false;
				for (Iterator<Map.Entry<String, PlanExecPlanDetail>> it = set.iterator(); it.hasNext();) {
					Map.Entry<String, PlanExecPlanDetail> entry = (Map.Entry<String, PlanExecPlanDetail>) it.next();
					PlanExecPlanDetail pepd = entry.getValue();
					if (null != pepd && pepd.getPlanProjectNodeRel().getPlanProjectNodeRelId().equals(prel.getPlanProjectNodeRelId())) {
						if_has = true;
						break;
					}
				}
				if(!if_has){
					viewPlanNodes.remove(i);
					i--;
				}
			}
		}
	}

	/*
	 * 获取单个节点的显示的状态
	 */
	public String getPrintStatus(PlanExecPlanDetail pepd) {
		String returnStr = "";
		if(null==pepd || new PlanExecPlanDetail()==pepd)
			return "";
		if (!pepd.getActiveBl()) {
			returnStr = "noActive";
		} else if ("0".equalsIgnoreCase(pepd.getStatus()) && "0".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			returnStr = "noConfirm";
		} else if (("0".equalsIgnoreCase(pepd.getStatus()) || "1".equalsIgnoreCase(pepd.getStatus())) && "1".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			if (DateOperator.getDateNow().before(pepd.getScheduleEndDate())) {
				returnStr = "going";
			} else {
				returnStr = "suspend";
			}
		} else if ("2".equalsIgnoreCase(pepd.getStatus())) {
			if (pepd.getRealEndDate().before(pepd.getScheduleEndDate())) {
				returnStr = "completeSuspend";
			} else {
				returnStr = "complete";
			}
		}
		return returnStr;
	}

	/*
	 * 获取单个节点的显示的HTML
	 */
	public String getPrintHtml(PlanExecPlanDetail pepd) {
		Date myDate = DateOperator.getDateNow();
		int myYear = DateOperator.getYear(myDate);
		Date alertDate = DateOperator.addDays(myDate, -7);
		String printStatus = getPrintStatus(pepd);
		String returnStr = "";
		if ("noActive".equalsIgnoreCase(printStatus)) {
			returnStr = "<span title='此节点关闭'>/</span>";
		} else if ("going".equalsIgnoreCase(printStatus) || ("preComplete".equalsIgnoreCase(printStatus))) {
			if(null!=pepd.getScheduleStartDate()){
				returnStr = "<span class='color_blue' title='进行中'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "~"
					+ getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			}else{
				returnStr = "<span class='color_blue' title='进行中'>" + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			}
			// if(pepd.getUpdatedDate().before(alertDate)){
			// returnStr =
			// "<div style='float:left;height:46px; line-height:46px;width:154px;'>"+returnStr
			// +"</div><div style='float:right; height:46px;width:4px; background-color:#548dd4;''></div>";
			// }
		} else if ("suspend".equalsIgnoreCase(printStatus)) {
			if(null!=pepd.getScheduleStartDate()){
				returnStr = "<span class='color_red' title='过期'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "~"
				+ getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			}else{
				returnStr = "<span class='color_red' title='过期'>" + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			}
			// if(pepd.getUpdatedDate().before(alertDate)){
			// returnStr =
			// "<div style='float:left;height:46px; line-height:46px;width:154px;'>"+returnStr
			// +"</div><div style='float:right; height:46px;width:4px; background-color:#d99694;''></div>";
			// }
		} else if ("noConfirm".equalsIgnoreCase(printStatus)) {
			if(null!=pepd.getScheduleStartDate()){
				returnStr = "<span class='color_black' title='未确认'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate())
					+ "~" + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			}else{
				returnStr = "<span class='color_black' title='未确认'>" + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			}
			// if(pepd.getUpdatedDate().before(alertDate)){
			// returnStr =
			// "<div style='float:left;height:46px; line-height:46px;width:154px;'>"+returnStr
			// +"</div><div style='float:right; height:46px;width:4px; background-color:grey;''></div>";
			// }
		} else if ("complete".equalsIgnoreCase(printStatus)) {
			returnStr = "<span class='color_green' title='完成'>" + getDateStrJudgeYear(myYear, pepd.getRealEndDate()) + "</span>";
		} else if ("completeSuspend".equalsIgnoreCase(printStatus)) {
			returnStr = "<span class='color_dark_green' title='完成(曾过期)'>" + getDateStrJudgeYear(myYear, pepd.getRealEndDate())
				+ "(<span class='color_red'>"+getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>)</span>";
		}
		return returnStr;
	}

	/*
	 * 根据当前年份获取date的字符串，如果是同一年，则不显示前面的年份
	 */
	public String getDateStrJudgeYear(int myYear, Date date) {
		String returnStr = "";
		if (null != date) {
			if (myYear == DateOperator.getYear(date)) {
				returnStr = DateOperator.formatDate(date, "MM/dd");
			} else {
				returnStr = DateOperator.formatDate(date, "yy/MM/dd");
			}
		}
		return returnStr;
	}

	/**
	 * 功能: 进入项目计划配置界面
	 * 
	 * @param projectCd
	 * @param planTypeCd
	 * @return
	 * @throws Exception
	 */
	public String configPlan() throws Exception {
		plans = planManager.getPlansPerProjCd(projectCd, planTypeCd, false);
		return "configPlan";
	}

	/**
	 * 功能: 控制计划详情输入
	 * 
	 * @param planDetailId
	 * @param projNodeId
	 * @param projPlanCd
	 * @param viewModel
	 *            是否只读:1-是 其他-否
	 * @param costResOrgCd
	 *            :成本计划责任机构
	 * @param linkSource
	 *            : 来源 'cost'
	 * @return
	 * @throws Exception
	 */
	public String planDetailInput() throws Exception {
		if (StringUtils.isBlank(planDetailId)) {
			planDetailEntity = detailManager.getPlanExecPlanDetail(projNodeId, projPlanCd);
			if (null == planDetailEntity) {
				planDetailEntity = new PlanExecPlanDetail();
				planDetailTempId = RandomUtils.generateTmpEntityId();
				outputBizEntityId = planDetailTempId;
			} else {
				planDetailId = planDetailEntity.getPlanExecPlanDetailId();
				outputBizEntityId = planDetailEntity.getPlanExecPlanDetailId();
			}
		} else {
			planDetailEntity = detailManager.getEntity(planDetailId);
			outputBizEntityId = planDetailEntity.getPlanExecPlanDetailId();
		}

		String nodeName = "";
		PlanProjectNodeRel nodeRel = projNodeRelManager.getEntity(projNodeId);
		// 增加输出成果提示
		outFileTile = nodeRel.getOutputFiles();
		Map<String, PlanExecutionPlanNode> nodeMap = planNodeManager.getExecPlanNodesMap();
		PlanExecutionPlanNode n = nodeMap.get(nodeRel.getNodeCd());
		if (n != null) {
			nodeName = n.getNodeName();
		}
		String planName = planManager.getPlanNameByPlanCd(projPlanCd);
		planDetailCaption = planName + "--" + nodeName + "--" + nodeRel.getResOrgName();
		planDetailStatusMap = detailManager.buildPlanDetailStatusMap();
		planDetailOutput = attachManager.getAttachFilesByEntityIdAndModuleCd(outputBizEntityId, "execPlan");

		projectCd = nodeRel.getPlanExecutionPlanMain().getProjectCd();
		projectName = nodeRel.getPlanExecutionPlanMain().getProjectName();
		resOrgCd = nodeRel.getResOrgCd();
		buildPermission(projectCd, planDetailId);

		// 搜索推送中心计划任务信息
		planWorkCenterMaps = planWorkCenterManager.getPlanWorkCenterMapByExec(planDetailId, "2");
		Struts2Utils.getRequest().setAttribute("nodeName", nodeName);
		Struts2Utils.getRequest().setAttribute("planName", planName);

		return "detailInfo";
	}

	/**
	 * 功能: 保存控制计划信息
	 * 
	 * @param planDetailId
	 * @param scheduleEndDate
	 * @param realEndDate
	 * @param planDetailTempId
	 * @param projNodeId
	 * @param projPlanCd
	 * @param planDetailStatus
	 * @return
	 * @throws Exception
	 */
	public String savePlanDetail() throws Exception {
		PlanExecPlanDetail detail = null;
		if (StringUtils.isBlank(planDetailId)) {
			if (StringUtils.isNotBlank(scheduleStartDate) || StringUtils.isNotBlank(scheduleStartDate) || StringUtils.isNotBlank(scheduleEndDate)
					|| StringUtils.isNotBlank(realEndDate) || !activeBl) {
				detail = detailManager.addPlanDetailInfo(scheduleStartDate, scheduleEndDate, realEndDate, planDetailTempId, projNodeId, projPlanCd, activeBl);
			}
		} else {
			detail = detailManager.getEntity(planDetailId);
			// 若清空计划完成时间，并状态为未开始，则删除该record
			if ("".equals(scheduleEndDate) && "0".equals(detail.getStatus())) {
				try{
					messageManager.delete(detail.getPlanExecDetailMesses());
				}catch(Exception e){}
				detailManager.delete(detail);
				Struts2Utils.renderText("clear");
			} else {
				detailManager.svPlanDetailInfo(scheduleStartDate, scheduleEndDate, realEndDate, planDetailId, projNodeId, projPlanCd, planDetailStatus,
						activeBl);
			}
		}

		if (null != detail) {
			String newMessage = Struts2Utils.getParameter("newMessage");
			if (StringUtils.isNotBlank(newMessage)) {
				PlanExecDetailMess pm = new PlanExecDetailMess();
				pm.setContent(newMessage);
				pm.setSysType("0");
				pm.setPlanExecPlanDetail(detail);
				messageManager.savePlanExecDetailMess(pm);
			}
		}

		return null;
	}

	/**
	 * 添加留言
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveMessage() throws Exception {
		if (!StringUtils.isBlank(planDetailId)) {
			PlanExecPlanDetail planExecPlanDetail = detailManager.getEntity(planDetailId);
			PlanExecDetailMess pm = new PlanExecDetailMess();
			pm.setContent(Struts2Utils.getParameter("newMessage"));
			pm.setSysType("0");
			pm.setPlanExecPlanDetail(planExecPlanDetail);
			messageManager.savePlanExecDetailMess(pm);
			detailManager.savePlanExecPlanDetail(planExecPlanDetail);

			StringBuffer strb = new StringBuffer("");
			if (planExecPlanDetail.getPlanExecDetailMesses() != null && planExecPlanDetail.getPlanExecDetailMesses().size() > 0) {
				for (int i = 0; i < planExecPlanDetail.getPlanExecDetailMesses().size(); i++) {
					PlanExecDetailMess mes = planExecPlanDetail.getPlanExecDetailMesses().get(i);
					String insertStr = "";
					if (null != mes.getSysType() && "1".equalsIgnoreCase(mes.getSysType())) {
						insertStr = " color_black";
					}
					String now = DateOperator.formatDate(mes.getCreatedDate(), "MM-dd HH:mm");
					strb.append("<div class='detail_message_div" + insertStr + "'><pre>" + CodeNameUtil.getUserNameByCd(mes.getCreator()));
					strb.append("(" + now + "):");
					strb.append(mes.getContent());
					strb.append("</pre></div>");
				}
			}
			Struts2Utils.renderText(strb.toString());
		} else {
			Struts2Utils.renderText("no");
		}
		return null;
	}

	/**
	 * 功能: 改变计划详情状态
	 * 
	 * @param planDetailId
	 * @param planDetailStatus
	 * @return
	 * @throws Exception
	 */
	public String changePlanDetailStatus() throws Exception {
		if (detailManager.updatePlanDetailStatus(planDetailId, planDetailStatus)) {
			Struts2Utils.renderText("succ");
		}

		return null;
	}

	/**
	 * 功能: 保存项目计划配置信息
	 * 
	 * @param projPlanId
	 * @param projectCd
	 * @param planTypeCd
	 * @param projPlanName
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveProjPlan() throws Exception {
		if (planManager.saveProjPlanInfo(projPlanId, projectCd, planTypeCd, projPlanName)) {
			Struts2Utils.renderText("succ");
		} else {
			Struts2Utils.renderText("fail");
		}
		return null;
	}

	/**
	 * 功能: 激活/禁用控制计划
	 * 
	 * @param projPlanId
	 * @param projPlanState
	 * 
	 * @return
	 * @throws Exception
	 */
	public String changePlanState() throws Exception {
		if (planManager.changePlanState(projPlanId, projPlanState)) {
			Struts2Utils.renderText("succ");
		} else {
			Struts2Utils.renderText("fail");
		}

		return null;
	}

	/**
	 * 功能: 获取成果输出列表
	 * 
	 * @param outputBizEntityId
	 * @param projectCd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchOutputList() throws Exception {
		planDetailOutput = attachManager.getAttachFilesByEntityIdAndModuleCd(outputBizEntityId, "execPlan");
		buildPermission(projectCd, null);
		return "outputList";
	}

	/**
	 * 功能: 获取计划信息
	 * 
	 * @param planDetailId
	 * @param projNodeId
	 * @param projPlanCd
	 * @return
	 * @throws Exception
	 */
	public String fetchPlanDetailInfo() throws Exception {
		PlanExecPlanDetail planDetail = null;
		try{
			if (StringUtils.isBlank(planDetailId)) {
				planDetail = detailManager.getPlanDetailPerNodeRelIdAndPlanCd(projNodeId, projPlanCd);
			} else {
				planDetail = detailManager.getEntity(planDetailId);
			}
		}catch(Exception e){}
		if (null!=planDetail) {
			try{
				Struts2Utils.renderText(getPrintHtml(planDetail) + "*" + planDetail.getPlanExecPlanDetailId());
			}catch(Exception e){Struts2Utils.renderText("failure");}
		}else{
			Struts2Utils.renderText("failure");
		}

		return null;
	}

	/**
	 * 功能: 当输出成果有变化时记录到操作日志里面
	 * 
	 * @param planDetailId
	 * @param outputUpdateType
	 *            3-上传附件 4-删除附件
	 * @param deletedOutputFileId
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logOutputUpdateHistory() throws Exception {

		try{
			if (StringUtils.isNotBlank(planDetailId)) {
				PlanExecPlanDetail detail = detailManager.getEntity(planDetailId);
				PlanProjectNodeRel n = detail.getPlanProjectNodeRel();
				String nodeCd = n.getNodeCd();
				String nodeName = planNodeManager.getNodeNameByCd(nodeCd);
				String planCd = detail.getExecutionPlanCd();
				String planName = planManager.getPlanNameByPlanCd(planCd);
				String projCd = n.getPlanExecutionPlanMain().getProjectCd();
	
				String operationLog = "";
				// 上传附件
				if (PlanOperationLogManager.OPERATION_TYPE_UPLOADATTACH.equalsIgnoreCase(outputUpdateType)) {
					List<AppAttachFile> attachList = attachManager.getAttachFilesByEntityIdAndModuleCd(planDetailId, "execPlan");
					String fileName = "";
					if (attachList.size() > 0) {
						AppAttachFile f = attachList.get(0);
						fileName = f.getRealFileName();
					}
					operationLog = "上传附件: " + fileName;
				}
				// 删除附件
				else if (PlanOperationLogManager.OPERATION_TYPE_DELETEATTACH.equalsIgnoreCase(outputUpdateType)) {
					AppAttachFile f = attachManager.getEntity(deletedOutputFileId);
					operationLog = "删除附件: " + f.getRealFileName();
				}
	
			}
	
			Struts2Utils.renderText("succ");
		}catch(Exception e){
			
		}
		
		return null;
	}

	/**
	 * 功能: 获取项目提醒人列表
	 * 
	 * @param executionPlanMainId
	 * @param planTypeCd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchProjReminders() throws Exception {

		PlanExecutionPlanMain planMain = planMainManager.getEntity(executionPlanMainId);
		if (StringUtils.isBlank(planTypeCd)) {
			Struts2Utils.renderText("");
			return null;
		}

		String reminder = planMain.getReminder();
		String reminder2 = planMain.getReminder2();

		String tmp = "";
		if (DictContants.PLAN_TYPE_EXC.equals(planTypeCd)) {
			tmp = reminder;
		} else if (DictContants.PLAN_TYPE_PRE.equals(planTypeCd)) {
			tmp = reminder2;
		} else {
			System.out.println("调用fetchProjReminders(),工作类型不对!");
			Struts2Utils.renderText("");
			return null;
		}

		if (StringUtils.isNotBlank(tmp)) {
			String result = buildPersonInfoData(tmp);
			if (StringUtils.isNotBlank(result)) {
				Struts2Utils.renderText(result);
			}
		}

		return null;
	}

	/**
	 * 功能: 获取节点提醒人列表
	 * 
	 * @param projNodeId
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchNodeReminders() throws Exception {

		PlanProjectNodeRel planNodeRel = projNodeRelManager.getEntity(projNodeId);
		String reminder = planNodeRel.getReminder();
		if (StringUtils.isNotBlank(reminder)) {
			String result = buildPersonInfoData(reminder);
			if (StringUtils.isNotBlank(result)) {
				Struts2Utils.renderText(result);
			}
		}

		return null;
	}

	/**
	 * 功能: 保存项目提醒人列表
	 * 
	 * @param executionPlanMainId
	 * @param projReminder
	 * @param planTypeCd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveProjReminders() throws Exception {
		if (StringUtils.isNotBlank(executionPlanMainId)) {
			if (planMainManager.saveReminders(executionPlanMainId, projReminder, planTypeCd)) {
				Struts2Utils.renderText("done");
			}
		}
		return null;
	}

	// 功能: 删除detail
	public String deletePlanExecPlanDetail() {
		if (StringUtils.isNotBlank(planDetailId)) {
			detailManager.deletePlanExecPlanDetail(planDetailId);
			Struts2Utils.renderText("ok");
		}
		return null;
	}

	/**
	 * 功能: 判断项目管理员是否可以点击完成按钮
	 * 
	 * @param planDetailId
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkIfCanComplete() throws Exception {

		List<AppAttachFile> attachList = attachManager.getAttachFilesByEntityIdAndModuleCd(planDetailId, "execPlan");
		// 如果已经有附件，则可以完成
		if (attachList != null && attachList.size() > 0) {
			Struts2Utils.renderText("ok");
		} else {
			Struts2Utils.renderText("no");
		}

		return null;
	}

	/**
	 * 功能: 给超级管理员发送审核通过的提醒邮件
	 * 
	 * @param planDetailId
	 * 
	 * @return
	 * @throws Exception
	 */
	public String remindVerify() throws Exception {
		if (detailManager.remindPlanDetail(planDetailId, PlanExecPlanDetailManager.MAIL_TYPE_VERIFY)) {
			Struts2Utils.renderText("succ");
		}

		return null;
	}

	/**
	 * 功能: 构造机构树
	 * 
	 * @return
	 * @throws Exception
	 */
	public String buildOrgs() throws Exception {
		Struts2Utils.renderText(OrgTreeUtil.buildNoCheckOrgTreeJSON(PlasCache.getOrgEnableList()));
		return null;
	}

	/**
	 * 功能: 构造当前项目在页面显示的节点信息
	 * @param projCd
	 * @param plTypeCd
	 * @param execPlanType
	 * @param rOrgCd
	 * @param rOrgName
	 * @return
	 */
	private List<PlanProjectNodeRel> buildProjPlanNodes(String projCd, String plTypeCd, String execPlanType, String rOrgCd, String rOrgName,
			String nowPointLevel, String nowResOrgNames) {
		List<PlanProjectNodeRel> result = new ArrayList<PlanProjectNodeRel>();

		if ("control".equals(execPlanType)) {
			result = projNodeRelManager.getValidProjControlNodes(projCd, plTypeCd, rOrgCd, rOrgName, nowPointLevel, nowResOrgNames);
		} else {
			result = projNodeRelManager.getValidProjNodes(projCd, plTypeCd, null, rOrgCd, rOrgName, "", false, nowPointLevel, nowResOrgNames);
		}

		/*
		 * Collections.sort(result, new Comparator<PlanProjectNodeRel>() {
		 * public int compare(PlanProjectNodeRel nr1, PlanProjectNodeRel nr2) {
		 * String cd1 = nr1.getNodeCd(); String cd2 = nr2.getNodeCd(); return
		 * Integer.parseInt(cd1) - Integer.parseInt(cd2); } });
		 */

		return result;
	}

	/**
	 * 功能: 构造计划详情
	 * 
	 * @param projNodes
	 * @param pTreeTypeCd
	 * 
	 * @return
	 */
	private Map<String, PlanExecPlanDetail> buildPlanDetailMap(List<PlanProjectNodeRel> projNodes, String pTreeTypeCd, String searchPlans,
			String filter_GED_scheduleStartDate, String filter_LED_scheduleStartDate, String filter_GED_scheduleEndDate, String filter_LED_scheduleEndDate,
			String filter_GED_realEndDate, String filter_LED_realEndDate, String search_status, String nowViewStyle, String nowResOrgNames) {
		Map<String, PlanExecPlanDetail> m = new HashMap<String, PlanExecPlanDetail>();
		String[] nodeIds = new String[projNodes.size()];

		PlanProjectNodeRel n = null;
		for (int i = 0; i < projNodes.size(); i++) {
			n = projNodes.get(i);
			nodeIds[i] = n.getPlanProjectNodeRelId();
		}

		List<PlanExecPlanDetail> details = detailManager.getDetailsByProjPlanNodes(nodeIds, pTreeTypeCd, searchPlans, filter_GED_scheduleStartDate,
				filter_LED_scheduleStartDate, filter_GED_scheduleEndDate, filter_LED_scheduleEndDate, filter_GED_realEndDate, filter_LED_realEndDate,
				search_status, nowViewStyle, nowResOrgNames);
		String key = null;
		for (PlanExecPlanDetail d : details) {
			key = d.getPlanProjectNodeRel().getPlanProjectNodeRelId() + "_" + d.getExecutionPlanCd();
			m.put(key, d);
		}

		return m;
	}

	/**
	 * 功能: 构造人员信息
	 * 
	 * @param userIds
	 * 
	 */
	private String buildPersonInfoData(String userIds) {
		if (StringUtils.isBlank(userIds))
			return "";

		String[] ids = userIds.split(";");
		StringBuilder result = new StringBuilder();
		String tempId = null;
		String tempName = null;

		for (int i = 0; i < ids.length; i++) {
			tempId = ids[i];
			if (StringUtils.isBlank(tempId)) {
				continue;
			}
			tempName = CodeNameUtil.getUserNameByCd(tempId);
			if (StringUtils.isBlank(tempName)) {
				if (i == ids.length - 1) {
					result = result.deleteCharAt(result.length() - 1);
				}
				continue;
			}
			result.append("{\"uiid\":\"" + tempId + "\", \"userName\":\"" + tempName + "\"}");
			if (i < ids.length - 1) {
				result.append("|");
			}
		}

		return result.toString();
	}

	/**
	 * 功能: 构造用户权限
	 * 
	 * @param projCd
	 */
	private void buildPermission(String projCd, String planDetailId) {
		if (SpringSecurityUtils.hasRole("A_EXEC_HQ") || SpringSecurityUtils.hasRole("A_EXEC_AREA")) {
			// 普通管理员，能修改业态
			aExecAdmin = true;
		}

		if (StringUtils.isBlank(planDetailId)) {
			if (SpringSecurityUtils.hasRole("A_EXEC_AREA")) {
				// 列表中能新增节点，如果是区域判断是否当前区域
				aAddPoint = PlasCache.ifMyAreaByProjectCd(projCd, SpringSecurityUtils.getCurrentAcctId(), "", "ORG_AREA");
			}
			if (SpringSecurityUtils.hasRole("A_EXEC_PROJ")) {
				// 列表中能新增节点，如是是地产公司判断是否当前公司
				List<WsPlasOrg> userOrgs = PlasCache.getRelPosBubbleOrgCdList(SpringSecurityUtils.getCurrentAcctId(), "");
				String tempPosOrgCd = null;
				for (int i = 0; null != userOrgs && i < userOrgs.size(); i++) {
					tempPosOrgCd = ((WsPlasOrg) (userOrgs.get(i))).getOrgCd();
					if (projCd.equals(tempPosOrgCd)) {
						aAddPoint = true;
						break;
					}
				}
			}
		}

		if (StringUtils.isNotBlank(planDetailId)) {
			// 如果输入的detailId不为空，即进入查看明细页面
			PlanExecPlanDetail pepd = detailManager.getEntity(planDetailId);

			if (SpringSecurityUtils.hasRole("A_EXEC_AREA") || SpringSecurityUtils.hasRole("A_EXEC_PROJ")) {
				// 判断是否能新增，即可以修改计划开始时间和计划完成时间
				if (null != pepd && "1".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
					aAddPoint = false;
				}else{
					aAddPoint = true;
				}
			}
			if (SpringSecurityUtils.hasRole("A_EXEC_AREA")) {
				// 如果是区域操作员，判断当前项目是否属于他的区域
				if (null != pepd && !"1".equalsIgnoreCase(pepd.getInfoConfirmedFlg()) && "0".equalsIgnoreCase(pepd.getStatus())) {
					String project_cd = pepd.getPlanProjectNodeRel().getPlanExecutionPlanMain().getProjectCd();
					aConfirmPoint = PlasCache.ifMyAreaByProjectCd(project_cd, SpringSecurityUtils.getCurrentAcctId(), "", "ORG_AREA");
				}
			}

			if (null != pepd && "0".equalsIgnoreCase(pepd.getStatus()) && "1".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
				if (SpringSecurityUtils.hasRole("A_EXEC_PROJ")) {
					aWritePoint = true;
				}
				if ("地产公司".equalsIgnoreCase(pepd.getPlanProjectNodeRel().getResOrgName())) {
					// 判断当前节点的主责方名称，如是地产公司，判断当前用户是否隶属当前项目且角色为三级计划项目角色，如是就觉有填写节点权限
					if (SpringSecurityUtils.hasRole("A_EXEC_PROJ")) {
						List<WsPlasOrg> userOrgs = PlasCache.getRelPosBubbleOrgCdList(SpringSecurityUtils.getCurrentAcctId(), "");
						String tempPosOrgCd = null;
						for (int i = 0; null != userOrgs && i < userOrgs.size(); i++) {
							tempPosOrgCd = ((WsPlasOrg) (userOrgs.get(i))).getOrgCd();
							if (projCd.equals(tempPosOrgCd)) {
								aWritePoint = true;
								break;
							}
						}
					}
				} else {
					// 如是各中心，判断用户是否属于改中心且角色为三级计划中心角色，如是就有具有填写节点权限
					if (SpringSecurityUtils.hasRole("A_EXEC_CENTER")) {
						String[] compareOrgCds = pepd.getPlanProjectNodeRel().getResOrgCd().split(";");

						String tempPosOrgCd = null;
						String compareOrgCd = null;
						List<WsPlasOrg> userOrgs = null;

						for (int j = 0; null != compareOrgCds && j < compareOrgCds.length; j++) {
							compareOrgCd = compareOrgCds[j];
							userOrgs = PlasCache.getRelPosBubbleOrgCdList(SpringSecurityUtils.getCurrentAcctId(), "");
							for (int i = 0; null != userOrgs && i < userOrgs.size(); i++) {
								tempPosOrgCd = (userOrgs.get(i)).getOrgCd();
								if (compareOrgCd.equals(tempPosOrgCd)) {
									aWritePoint = true;
									break;
								}
							}
						}
					}
				}
			}

			// 总部审核一级节点，区域审核二级节点
			if (null != pepd && !"2".equalsIgnoreCase(pepd.getStatus())) {
				if (1 == pepd.getPlanProjectNodeRel().getPointLevel()) {
					if (SpringSecurityUtils.hasRole("A_EXEC_HQ")) {
						aCompletePoint = true;
					}
					if (SpringSecurityUtils.hasRole("A_EXEC_ADMIN_HQ")) {
						aChangeSchedule = true;
					}
				} else {
					if (SpringSecurityUtils.hasRole("A_EXEC_AREA")) {
						//String project_cd = pepd.getPlanProjectNodeRel().getPlanExecutionPlanMain().getProjectCd();
						//aCompletePoint = PlasCache.ifMyAreaByProjectCd(project_cd, SpringSecurityUtils.getCurrentAcctId(), "", "ORG_AREA");
						aCompletePoint = true;
					}
					if (SpringSecurityUtils.hasRole("A_EXEC_ADMIN_AREA")) {
						aChangeSchedule = true;
						//String project_cd = pepd.getPlanProjectNodeRel().getPlanExecutionPlanMain().getProjectCd();
						//aChangeSchedule = PlasCache.ifMyAreaByProjectCd(project_cd, SpringSecurityUtils.getCurrentAcctId(), "", "ORG_AREA");
					}
				}
			}
		}
		if (SpringSecurityUtils.hasRole("A_EXEC_SUPER_ADMIN")) {
			// 超级管理员
			aExecSuperAdmin = true;
			aExecAdmin = true;
			aAddPoint = true;
			aConfirmPoint = true;
			aWritePoint = true;
			aCompletePoint = true;
			aChangeSchedule = true;
		}
	}

	/**
	 * 功能: 获取所有项目公司列表
	 * 
	 * @param mapProjectsType
	 * @param projectCd
	 * @return
	 */
	public List<WsPlasOrg> fetchAllProjects() {

		// 搜索所有项目
		/*
		 * WsPlasOrg org = null; List<WsPlasOrg> allProjs = new
		 * ArrayList<WsPlasOrg>(); List<PlanExecutionPlanMain> allPlanMain =
		 * planMainManager.getAll(); for (PlanExecutionPlanMain m : allPlanMain)
		 * { org = new WsPlasOrg(); org.setOrgName(m.getProjectName());
		 * org.setOrgCd(m.getProjectCd()); if (!"243".equals(m.getProjectCd()))
		 * { mapProjectsType.put(m.getProjectCd(), m.getProjectName()); if
		 * (SpringSecurityUtils
		 * .getCurrentUaapUser().getCenterOrgCd().equals(m.getProjectCd())) {
		 * projectCd = m.getProjectCd(); } } allProjs.add(org); }
		 */

		// 搜索所有项目
		WsPlasOrg org = null;
		mapProjectsType = new HashMap<String, String>();
		List<WsPlasOrg> allProjs = new ArrayList<WsPlasOrg>();
		List<WsPlasOrg> list = WorkPlanUtil.getOrgEstateOrgList();
		for (WsPlasOrg m : list) {
			org = new WsPlasOrg();
			org.setOrgName(m.getOrgName());
			org.setOrgCd(m.getOrgCd());
			if (!GlobalConstants.WYS_ORG_CD.equals(m.getOrgCd())) {
				mapProjectsType.put(m.getOrgCd(), m.getOrgName());
				if (SpringSecurityUtils.getCurrentCenterCd().equals(m.getOrgCd())) {
					projectCd = m.getOrgCd();
				}
			}
			allProjs.add(org);
		}

		return allProjs;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public PlanExecutionPlanMain getPlanExecutionPlanMain() {
		return planExecutionPlanMain;
	}

	public void setPlanExecutionPlanMain(PlanExecutionPlanMain planExecutionPlanMain) {
		this.planExecutionPlanMain = planExecutionPlanMain;
	}

	public List<WsPlasOrg> getProjects() {
		return projects;
	}

	public void setProjects(List<WsPlasOrg> projects) {
		this.projects = projects;
	}

	public List<PlanExecutionPlan> getPlans() {
		return plans;
	}

	public void setPlans(List<PlanExecutionPlan> plans) {
		this.plans = plans;
	}

	public List<PlanExecutionPlan> getSearchedPlans() {
		return searchedPlans;
	}

	public void setSearchedPlans(List<PlanExecutionPlan> searchedPlans) {
		this.searchedPlans = searchedPlans;
	}

	public int getPlanMatrixWidth() {
		return planMatrixWidth;
	}

	public void setPlanMatrixWidth(int planMatrixWidth) {
		this.planMatrixWidth = planMatrixWidth;
	}

	public String getExecutionPlanMainId() {
		return executionPlanMainId;
	}

	public void setExecutionPlanMainId(String executionPlanMainId) {
		this.executionPlanMainId = executionPlanMainId;
	}

	public String getProjNodeId() {
		return projNodeId;
	}

	public void setProjNodeId(String projNodeId) {
		this.projNodeId = projNodeId;
	}

	public String getNodeCompleteDate() {
		return nodeCompleteDate;
	}

	public void setNodeCompleteDate(String nodeCompleteDate) {
		this.nodeCompleteDate = nodeCompleteDate;
	}

	public String getProjPlanName() {
		return projPlanName;
	}

	public void setProjPlanName(String projPlanName) {
		this.projPlanName = projPlanName;
	}

	public String getProjPlanId() {
		return projPlanId;
	}

	public void setProjPlanId(String projPlanId) {
		this.projPlanId = projPlanId;
	}

	public String getProjPlanState() {
		return projPlanState;
	}

	public void setProjPlanState(String projPlanState) {
		this.projPlanState = projPlanState;
	}

	public String getUserPoolPersons() {
		return userPoolPersons;
	}

	public void setUserPoolPersons(String userPoolPersons) {
		this.userPoolPersons = userPoolPersons;
	}

	public Map<String, PlanExecPlanDetail> getPlanDetailMap() {
		return planDetailMap;
	}

	public Map<String, PlanProjectNodeRel> getParentRelMap() {
		return parentRelMap;
	}

	public String getPlanDetailId() {
		return planDetailId;
	}

	public void setPlanDetailId(String planDetailId) {
		this.planDetailId = planDetailId;
	}

	public String getPlanDetailTempId() {
		return planDetailTempId;
	}

	public void setPlanDetailTempId(String planDetailTempId) {
		this.planDetailTempId = planDetailTempId;
	}

	public List<AppAttachFile> getPlanDetailOutput() {
		return planDetailOutput;
	}

	public void setPlanDetailOutput(List<AppAttachFile> planDetailOutput) {
		this.planDetailOutput = planDetailOutput;
	}

	public PlanExecPlanDetail getPlanDetailEntity() {
		return planDetailEntity;
	}

	public void setPlanDetailEntity(PlanExecPlanDetail planDetailEntity) {
		this.planDetailEntity = planDetailEntity;
	}

	public String getOutputBizEntityId() {
		return outputBizEntityId;
	}

	public void setOutputBizEntityId(String outputBizEntityId) {
		this.outputBizEntityId = outputBizEntityId;
	}

	public String getProjReminder() {
		return projReminder;
	}

	public void setProjReminder(String projReminder) {
		this.projReminder = projReminder;
	}

	public String getNodeReminder() {
		return nodeReminder;
	}

	public void setNodeReminder(String nodeReminder) {
		this.nodeReminder = nodeReminder;
	}

	public String getProjPlanCd() {
		return projPlanCd;
	}

	public void setProjPlanCd(String projPlanCd) {
		this.projPlanCd = projPlanCd;
	}

	public String getScheduleStartDate() {
		return scheduleStartDate;
	}

	public void setScheduleStartDate(String scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}

	public String getScheduleEndDate() {
		return scheduleEndDate;
	}

	public void setScheduleEndDate(String scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	public String getRealEndDate() {
		return realEndDate;
	}

	public void setRealEndDate(String realEndDate) {
		this.realEndDate = realEndDate;
	}

	public Map<String, String> getPlanDetailStatusMap() {
		return planDetailStatusMap;
	}

	public String getPlanDetailCaption() {
		return planDetailCaption;
	}

	public String getOutputUpdateType() {
		return outputUpdateType;
	}

	public void setOutputUpdateType(String outputUpdateType) {
		this.outputUpdateType = outputUpdateType;
	}

	public String getDeletedOutputFileId() {
		return deletedOutputFileId;
	}

	public void setDeletedOutputFileId(String deletedOutputFileId) {
		this.deletedOutputFileId = deletedOutputFileId;
	}

	public String getPlanDetailStatus() {
		return planDetailStatus;
	}

	public void setPlanDetailStatus(String planDetailStatus) {
		this.planDetailStatus = planDetailStatus;
	}

	public boolean isAExecSuperAdmin() {
		return aExecSuperAdmin;
	}

	public boolean isAExecAdmin() {
		return aExecAdmin;
	}

	public boolean isAAddPoint() {
		return aAddPoint;
	}
	
	public boolean isAChangeSchedule() {
		return aChangeSchedule;
	}

	public boolean isAConfirmPoint() {
		return aConfirmPoint;
	}

	public boolean isAWritePoint() {
		return aWritePoint;
	}

	public boolean isACompletePoint() {
		return aCompletePoint;
	}

	public List<PlanCenterDetailsVO> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<PlanCenterDetailsVO> detailsList) {
		this.detailsList = detailsList;
	}

	public String getCenterCd() {
		return centerCd;
	}

	public void setCenterCd(String centerCd) {
		this.centerCd = centerCd;
	}

	public String getOutFileTile() {
		return outFileTile;
	}

	public Map<String, String> getMapProjectsType() {
		return mapProjectsType;
	}

	public boolean getActiveBl() {
		return activeBl;
	}

	public void setActiveBl(boolean activeBl) {
		this.activeBl = activeBl;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public String getPlanTypeCd() {
		return planTypeCd;
	}

	public void setPlanTypeCd(String planTypeCd) {
		this.planTypeCd = planTypeCd;
	}

	public String getResOrgCd() {
		return resOrgCd;
	}

	public void setResOrgCd(String resOrgCd) {
		this.resOrgCd = resOrgCd;
	}

	public String getResOrgName() {
		return resOrgName;
	}

	public void setResOrgName(String resOrgName) {
		this.resOrgName = resOrgName;
	}

	public List<EntityVO> getResOrgListExec() {
		return resOrgListExec;
	}

	public void setResOrgListExec(List<EntityVO> resOrgListExec) {
		this.resOrgListExec = resOrgListExec;
	}

	public List<EntityVO> getResOrgListPre() {
		return resOrgListPre;
	}

	public void setResOrgListPre(List<EntityVO> resOrgListPre) {
		this.resOrgListPre = resOrgListPre;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getPlanType() {
		return planType;
	}

	public List<EntityVO> getResOrgListCost() {
		return resOrgListCost;
	}

	public void setResOrgListCost(List<EntityVO> resOrgListCost) {
		this.resOrgListCost = resOrgListCost;
	}

	public List<Map<String, String>> getPlanWorkCenterMaps() {
		return planWorkCenterMaps;
	}

	public void setPlanWorkCenterMaps(List<Map<String, String>> planWorkCenterMaps) {
		this.planWorkCenterMaps = planWorkCenterMaps;
	}

	public String getViewModel() {
		return viewModel;
	}

	public void setViewModel(String viewModel) {
		this.viewModel = viewModel;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCostResOrgCd() {
		return costResOrgCd;
	}

	public void setCostResOrgCd(String costResOrgCd) {
		this.costResOrgCd = costResOrgCd;
	}

	public String getLinkSource() {
		return linkSource;
	}

	public void setLinkSource(String linkSource) {
		this.linkSource = linkSource;
	}

	public String getNowPointLevel() {
		return nowPointLevel;
	}

	public void setNowPointLevel(String nowPointLevel) {
		this.nowPointLevel = nowPointLevel;
	}

	public String getNowViewStyle() {
		return nowViewStyle;
	}

	public void setNowViewStyle(String nowViewStyle) {
		this.nowViewStyle = nowViewStyle;
	}

	public List<PlanProjectNodeRel> getViewPlanNodes() {
		return viewPlanNodes;
	}

	public void setViewPlanNodes(List<PlanProjectNodeRel> viewPlanNodes) {
		this.viewPlanNodes = viewPlanNodes;
	}

	public String getSearchPlans() {
		return searchPlans;
	}

	public void setSearchPlans(String searchPlans) {
		this.searchPlans = searchPlans;
	}

	public String getFilter_GED_scheduleStartDate() {
		return filter_GED_scheduleStartDate;
	}

	public void setFilter_GED_scheduleStartDate(String filter_GED_scheduleStartDate) {
		this.filter_GED_scheduleStartDate = filter_GED_scheduleStartDate;
	}

	public String getFilter_LED_scheduleStartDate() {
		return filter_LED_scheduleStartDate;
	}

	public void setFilter_LED_scheduleStartDate(String filter_LED_scheduleStartDate) {
		this.filter_LED_scheduleStartDate = filter_LED_scheduleStartDate;
	}

	public String getFilter_GED_scheduleEndDate() {
		return filter_GED_scheduleEndDate;
	}

	public void setFilter_GED_scheduleEndDate(String filter_GED_scheduleEndDate) {
		this.filter_GED_scheduleEndDate = filter_GED_scheduleEndDate;
	}

	public String getFilter_LED_scheduleEndDate() {
		return filter_LED_scheduleEndDate;
	}

	public void setFilter_LED_scheduleEndDate(String filter_LED_scheduleEndDate) {
		this.filter_LED_scheduleEndDate = filter_LED_scheduleEndDate;
	}

	public String getFilter_GED_realEndDate() {
		return filter_GED_realEndDate;
	}

	public void setFilter_GED_realEndDate(String filter_GED_realEndDate) {
		this.filter_GED_realEndDate = filter_GED_realEndDate;
	}

	public String getFilter_LED_realEndDate() {
		return filter_LED_realEndDate;
	}

	public void setFilter_LED_realEndDate(String filter_LED_realEndDate) {
		this.filter_LED_realEndDate = filter_LED_realEndDate;
	}

	public String getSearch_status() {
		return search_status;
	}

	public void setSearch_status(String search_status) {
		this.search_status = search_status;
	}

	public Map<String, String> getMapPrintHtml() {
		return mapPrintHtml;
	}

	public String getNowResOrgNames() {
		return nowResOrgNames;
	}

	public void setNowResOrgNames(String nowResOrgNames) {
		this.nowResOrgNames = nowResOrgNames;
	}

	public boolean isIfProjectChange() {
		return ifProjectChange;
	}

	public void setIfProjectChange(boolean ifProjectChange) {
		this.ifProjectChange = ifProjectChange;
	}
	
}