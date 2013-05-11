package com.hhz.ump.web.plan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.RandomUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.plan.CostExecPlanDetailManager;
import com.hhz.ump.dao.plan.CostExecutionPlanMainManager;
import com.hhz.ump.dao.plan.CostExecutionPlanNodeManager;
import com.hhz.ump.dao.plan.CostProjectNodeRelManager;
import com.hhz.ump.dao.plan.PlanExecutionPlanMainManager;
import com.hhz.ump.dao.plan.PlanExecutionPlanManager;
import com.hhz.ump.dao.plan.PlanOperationLogManager;
import com.hhz.ump.dao.plan.PlanWorkCenterManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.plan.CostCtrlPlanRelVO;
import com.hhz.ump.entity.plan.CostExecPlanDetail;
import com.hhz.ump.entity.plan.CostExecutionPlanMain;
import com.hhz.ump.entity.plan.CostExecutionPlanNode;
import com.hhz.ump.entity.plan.CostProjectNodeRel;
import com.hhz.ump.entity.plan.PlanCenterDetailsVO;
import com.hhz.ump.entity.plan.PlanExecPlanDetail;
import com.hhz.ump.entity.plan.PlanExecutionPlan;
import com.hhz.ump.entity.plan.PlanProjectNodeRel;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.util.OrgTreeUtil;
import com.hhz.ump.web.vo.EntityVO;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 类名 CostExecutionPlanAction 创建者 李劲 创建日期 2010-5-25 描述 控制计划模块
 */
@Namespace("/plan")
@Results( { @Result(name = "matrix", location = "/WEB-INF/content/plan/cost-exec-plan-matrix.ftl", type = "freemarker") })
public class CostExecPlanAction extends ActionSupport {

	private static final long serialVersionUID = -5833728668625630228L;

	
	@Autowired
	private CostExecutionPlanNodeManager costExecutionPlanNodeManager;
	
	@Autowired
	private CostExecutionPlanMainManager costExecutionPlanMainManager;
	@Autowired
	private CostProjectNodeRelManager costProjectNodeRelManager;
	@Autowired
	private CostExecPlanDetailManager costExecPlanDetailManager;
	@Autowired
	private PlanExecutionPlanMainManager planExecutionPlanMainManager;
	
	@Autowired
	private PlanExecutionPlanManager planExecutionPlanManager;
	@Autowired
	private PlanOperationLogManager operationLogManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;

	private CostExecutionPlanMain costExecutionPlanMain;
	private String projectCd;
	private String projectName;//发起中心任务使用
	private String executionPlanMainId;
	private String projNodeId;
	private String nodeCompleteDate;
	private String projPlanId;
	private String projPlanName;
	private String projPlanState;
	private String projPlanCd;
	List<PlanCenterDetailsVO> detailsList;
	List<CostCtrlPlanRelVO> costCtrlPlanRelList;
	private Map<String, CostExecPlanDetail> planDetailMap;
	private Map<String, PlanExecPlanDetail> planDetailPlanMap;

	private String userPoolPersons;
	private String planDetailId;
	private String planDetailTempId;
	private String planDetailStatus;
	private int planMatrixWidth;
	private CostExecPlanDetail planDetailEntity;

	private String outputBizEntityId;
	private String projReminder;
	private String nodeReminder;

	private String scheduleEndDate;
	private String realEndDate;
	private String planDetailCaption;
	private String outputUpdateType;
	private String deletedOutputFileId;
	private String planType;//控制计划 "control"
	private String centerCd;// 搜索条件的中心CD
	private String outFileTile;// 输出成果提示

	private List<WsPlasOrg> projects = new ArrayList<WsPlasOrg>();
	private List<CostProjectNodeRel> planNodes = new ArrayList<CostProjectNodeRel>();
	private List<PlanProjectNodeRel> planNodesPlan = new ArrayList<PlanProjectNodeRel>();
	private List<PlanExecutionPlan> plans = new ArrayList<PlanExecutionPlan>();
	private List<AppAttachFile> planDetailOutput = new ArrayList<AppAttachFile>();
	private Map<String, String> planDetailStatusMap = new HashMap<String, String>();
	private Map<String, String> mapProjectsType = new HashMap<String, String>();
	private List<Map<String, String>> planWorkCenterMaps;

	private boolean superAdmin = false;
	private boolean projAdmin = false;
	private boolean infoInputor = false;
	private Date currentDate;
	
	@Autowired
	private PlanWorkCenterManager planWorkCenterManager;
	
	
	//默认显示 2-成本计划
	private String planTypeCd = DictContants.PLAN_TYPE_COST;
	
	
	//所有中心列表
	private List<EntityVO> resOrgListCost = new ArrayList<EntityVO>();
	
	//配合方
	private String resOrgCd;//发起中心任务,前台取值
	private String resOrgName;

	//关联执行计划节点 
	private Map<String,String> mapExecCostPlan;
	
	//关联多个成本节点的计划节点CD列表
	private List<String> nodesMulCost;
	
	
	/**
	 * 功能: 前期计划
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
	 * @return
	 * @throws Exception
	 */
	public String portal() throws Exception {
		
		//构造项目切换菜单
		projects = fetchAllProjects();
		
		// 默认显示即墨地产
		if (StringUtils.isBlank(projectCd)) {
			projectCd = "288";
		}
		
		currentDate = DateOperator.getDateNow();
		
		// 构造权限
		buildPermission(projectCd);
		
		// 构造中心列表
		resOrgListCost = costProjectNodeRelManager.getResOrgList(DictContants.PLAN_TYPE_COST);

		return "portal";
	}

	public String getCenterPlanRel() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = format.format(DateOperator.getDateNow());
		String nextMonthDate = format.format(DateOperator.addMonthes(DateOperator.getDateNow(), 1));
		detailsList = planExecutionPlanMainManager.fetchCenterDetails(nowDate, nextMonthDate, centerCd, projectCd, false);

		return "centerRel";
	}
	/**
	 * 得到成本半年计划相关招标采购内容：按照招标\采购、时间Group By显示，事项按【项目】业态#节点显示。
	 * @return
	 */
	public String getCostCtrlPlanRel() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//取得本月的最后一天
		Date lastMonthDate=DateOperator.getLastDayOfMonth12(DateOperator.getYear(DateOperator.getDateNow()),DateOperator.getMonth12(DateOperator.getDateNow()));
		String nowDate =format.format(lastMonthDate);
		String nextMonthDate = format.format(DateOperator.addMonthes(lastMonthDate, 1));
		costCtrlPlanRelList = planExecutionPlanMainManager.fetchCostCtrlPlanRel(nowDate, nextMonthDate,lastMonthDate, centerCd, false);

		return "centerRel";
	}

	/**
	 * 功能: 显示某地产公司的执行计划信息表格
	 * @param projectCd
	 * @param planTypeCd
	 * @param planType
	 * @param resOrgCd
	 * @param resOrgName
	 * 
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
			projectCd = "178";
		}
		buildPermission(projectCd);

		//计划主表
		costExecutionPlanMain = costExecutionPlanMainManager.getExecutionPlanMainByProjCd(projectCd,planTypeCd);
		
		//节点
		planNodes = buildProjPlanNodes(projectCd, planTypeCd, planType,resOrgCd,resOrgName);
		//planNodesPlan = buildProjPlanNodesPlan(projectCd, planTypeCd, planType,resOrgCd,resOrgName);
		
		//节点与业态映射关系
		planDetailMap = buildPlanDetailMap(planNodes);
		planDetailPlanMap = costExecutionPlanNodeManager.getCostNodePlanDetailMap(projectCd);
		
		//业态
		plans = planExecutionPlanManager.getPlansPerProjCd(projectCd, DictContants.PLAN_TYPE_EXC, true);
		
		//计算业态宽度
		planMatrixWidth = plans.size() * 200 + plans.size() * 3;
		
		//节点与业态映射关系点的状态
		planDetailStatusMap = costExecPlanDetailManager.buildPlanDetailStatusMap();
		
		//构造关联节点
		mapExecCostPlan = initMapPlanNodes(projectCd);
		nodesMulCost = initMulCost();
	}
	/**
	 * 构造关联节点(成本节点)
	 */
	private Map<String,String> initMapPlanNodes(String prjCd){
		
		Map<String,String> tmpMapExecCostPlan = new HashMap<String,String>();
		CostExecutionPlanNode node = null;
		List<CostExecutionPlanNode> costNodes= costExecutionPlanNodeManager.getRelCostNodeList(prjCd);
//		Collections.sort(costNodes, new Comparator<CostExecutionPlanNode>() {
//			@Override
//			public int compare(CostExecutionPlanNode n1, CostExecutionPlanNode n2) {
//				String cd1 = n1.getNodeCd();
//				String cd2 = n2.getNodeCd();
//				return Integer.parseInt(cd1) - Integer.parseInt(cd2);
//			}
//		});
		if(costNodes != null){
			for (int i = 0; i < costNodes.size(); i++) {
				node = costNodes.get(i);
				tmpMapExecCostPlan.put(node.getNodeCd(), node.getNodeName());
			}
		}
		
		return tmpMapExecCostPlan;
	}
	
	/**
	 * 成本节点(还有其他成本节点和它对应到同一个执行计划节点)
	 * @return
	 */
	private List<String> initMulCost(){
		List<String> tmpList = costExecutionPlanNodeManager.getRelCostNodeListCount();
		if(tmpList == null)
			return new ArrayList<String>();

		return tmpList;
	}

	/**
	 * 功能: 进入项目计划配置界面
	 * @param projectCd
	 * @param planTypeCd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String configPlan() throws Exception {
		plans = planExecutionPlanManager.getPlansPerProjCd(projectCd, DictContants.PLAN_TYPE_EXC, false);
		return "configPlan";
	}

	/**
	 * 功能: 查看业态(真正是执行计划的可用业态) 进入项目计划配置界面
	 * @param projectCd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String configPlanSearch() throws Exception {
		plans = planExecutionPlanManager.getPlansPerProjCd(projectCd, DictContants.PLAN_TYPE_EXC, true);
		return "configPlanSearch";
	}
	/**
	 * 功能: 控制计划详情输入
	 * @param planDetailId
	 * @param projNodeId
	 * @param viewModel 是否只读:1-是 其他-否
	 * @param resOrgCd 主责方
	 * @param viewModel 是否只读:1-是 其他-否
	 * 
	 * @return
	 * @throws Exception
	 */
	public String planDetailInput() throws Exception {
		if (StringUtils.isBlank(planDetailId)) {
			planDetailEntity = new CostExecPlanDetail();
			planDetailTempId = RandomUtils.generateTmpEntityId();
			outputBizEntityId = planDetailTempId;
		} else {
			planDetailEntity = costExecPlanDetailManager.getEntity(planDetailId);
			Hibernate.initialize(planDetailEntity);
			outputBizEntityId = planDetailEntity.getCostExecPlanDetailId();
		}

		String nodeName = "";
		CostProjectNodeRel nodeRel = costProjectNodeRelManager.getEntity(projNodeId);
		
		// 增加输出成果提示
		outFileTile = nodeRel.getOutputFiles();
		Map<String, CostExecutionPlanNode> nodeMap = costExecutionPlanNodeManager.getExecPlanNodesMap();
		CostExecutionPlanNode n = nodeMap.get(nodeRel.getNodeCd());
		if (n != null) {
			nodeName = n.getNodeName();
		}
		String planName = planExecutionPlanManager.getPlanNameByPlanCd(projPlanCd);
		planDetailCaption = nodeName + "-" + planName;
		planDetailStatusMap = costExecPlanDetailManager.buildPlanDetailStatusMap();
		planDetailOutput = appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(outputBizEntityId, "costPlan");

		projectCd = nodeRel.getCostExecutionPlanMain().getProjectCd();
		projectName = nodeRel.getCostExecutionPlanMain().getProjectName();
		resOrgCd = nodeRel.getResOrgCd();
		buildPermission(getProjectCd());
		
		// 搜索推送中心计划任务信息
		planWorkCenterMaps = planWorkCenterManager.getPlanWorkCenterMapByExec(planDetailId, "1");
		
		Struts2Utils.getRequest().setAttribute("nodeName", nodeName);
		Struts2Utils.getRequest().setAttribute("planName", planName);
		
		return "detailInfo";
	}

	/**
	 * 功能: 保存控制计划信息
	 * @param planDetailId
	 * @param scheduleEndDate
	 * @param realEndDate
	 * @param planDetailTempId
	 * @param projNodeId
	 * @param projPlanCd
	 * @param planDetailStatus
	 * 
	 * @return
	 * @throws Exception
	 */
	public String savePlanDetail() throws Exception {
		if (StringUtils.isBlank(planDetailId)) {
			costExecPlanDetailManager.addPlanDetailInfo(scheduleEndDate, realEndDate, planDetailTempId, projNodeId, projPlanCd);
		} else {
			CostExecPlanDetail detail = costExecPlanDetailManager.getEntity(planDetailId);
			// 若清空计划完成时间，并状态为未开始，则删除该record
			if ("".equals(scheduleEndDate) && "0".equals(detail.getStatus())) {
				costExecPlanDetailManager.delete(detail);
				Struts2Utils.renderText("clear");
			} else {
				costExecPlanDetailManager.svPlanDetailInfo(scheduleEndDate, realEndDate, planDetailId, projNodeId, projPlanCd,
						planDetailStatus);
			}
		}

		return null;
	}

	/**
	 * 功能: 改变计划详情状态
	 * @param planDetailId
	 * @param planDetailStatus
	 * 
	 * @return
	 * @throws Exception
	 */
	public String changePlanDetailStatus() throws Exception {
		if (costExecPlanDetailManager.updatePlanDetailStatus(planDetailId, planDetailStatus)) {
			Struts2Utils.renderText("succ");
		}

		return null;
	}

	/**
	 * 功能: 保存项目计划配置信息
	 * @param projPlanId
	 * @param projectCd
	 * @param planTypeCd
	 * @param projPlanName
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveProjPlan() throws Exception {
		if (planExecutionPlanManager.saveProjPlanInfo(projPlanId, projectCd, planTypeCd,projPlanName)) {
			Struts2Utils.renderText("succ");
		} else {
			Struts2Utils.renderText("fail");
		}
		return null;
	}

	/**
	 * 功能: 激活/禁用控制计划
	 * @param projPlanId
	 * @param projPlanState
	 * 
	 * @return
	 * @throws Exception
	 */
	public String changePlanState() throws Exception {
		if (planExecutionPlanManager.changePlanState(projPlanId, projPlanState)) {
			Struts2Utils.renderText("succ");
		} else {
			Struts2Utils.renderText("fail");
		}

		return null;
	}

	/**
	 * 功能: 获取成果输出列表
	 * @param outputBizEntityId
	 * @param projectCd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchOutputList() throws Exception {
		planDetailOutput = appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(outputBizEntityId, "costPlan");
		buildPermission(projectCd);
		return "outputList";
	}

	/**
	 * 功能: 获取计划信息
	 * @param planDetailId
	 * @param projNodeId
	 * @param projPlanCd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchPlanDetailInfo() throws Exception {

		CostExecPlanDetail planDetail = null;
		if (StringUtils.isBlank(planDetailId)) {
			planDetail = costExecPlanDetailManager.getPlanDetailPerNodeRelIdAndPlanCd(projNodeId, projPlanCd);
		} else {
			planDetail = costExecPlanDetailManager.getEntity(planDetailId);
		}

		if (planDetail != null) {
			StringBuilder jsonData = new StringBuilder("{");

			jsonData.append("'id':'" + planDetail.getCostExecPlanDetailId() + "',");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			String scheEdDate = planDetail.getScheduleEndDate() == null ? "" : df.format(planDetail.getScheduleEndDate());
			String realEdDate = planDetail.getRealEndDate() == null ? "" : df.format(planDetail.getRealEndDate());
			String infoConfirmedFlg = StringUtils.isBlank(planDetail.getInfoConfirmedFlg())?"":planDetail.getInfoConfirmedFlg();
			
			jsonData.append("'scheduleEdDate':'" + scheEdDate + "',");
			jsonData.append("'realEdDate':'" + realEdDate + "',");
			jsonData.append("'infoConfirmedFlg':'" + infoConfirmedFlg + "',");
			jsonData.append("'status':'" + planDetail.getStatus() + "'}");

			Struts2Utils.renderText(jsonData.toString());
		}

		return null;
	}

	/**
	 * 功能: 当输出成果有变化时记录到操作日志里面
	 * @param planDetailId
	 * @param outputUpdateType
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logOutputUpdateHistory() throws Exception {
		
		if (StringUtils.isNotBlank(planDetailId)) {
			CostExecPlanDetail detail = costExecPlanDetailManager.getEntity(planDetailId);
			CostProjectNodeRel n = detail.getCostProjectNodeRel();
			String nodeCd = n.getNodeCd();
			String nodeName = costExecutionPlanNodeManager.getNodeNameByCd(nodeCd);
			String planCd = detail.getExecutionPlanCd();
			String planName = planExecutionPlanManager.getPlanNameByPlanCd(planCd);
			String projCd = n.getCostExecutionPlanMain().getProjectCd();

			String operationLog = "";
			if (PlanOperationLogManager.OPERATION_TYPE_UPLOADATTACH.equalsIgnoreCase(outputUpdateType)) {
				List<AppAttachFile> attachList = appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(planDetailId,
						"costPlan");
				String fileName = "";
				if (attachList.size() > 0) {
					AppAttachFile f = attachList.get(0);
					fileName = f.getRealFileName();
				}
				operationLog = "上传附件: " + fileName;
			} else if (PlanOperationLogManager.OPERATION_TYPE_DELETEATTACH.equalsIgnoreCase(outputUpdateType)) {
				AppAttachFile f = appAttachFileManager.getEntity(deletedOutputFileId);
				operationLog = "删除附件: " + f.getRealFileName();
			}

			operationLogManager.addPlanLog(detail.getCostProjectNodeRel().getPlanTypeCd(), detail.getCostExecPlanDetailId(), nodeName + "-" + planName,
					outputUpdateType, projCd, operationLog);
		}

		Struts2Utils.renderText("succ");

		return null;
	}

	/**
	 * 功能: 获取项目提醒人列表
	 * @param executionPlanMainId
	 * @param planTypeCd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchProjReminders() throws Exception {
		
		CostExecutionPlanMain planMain = costExecutionPlanMainManager.getEntity(executionPlanMainId);
		if(StringUtils.isBlank(planTypeCd)){
			Struts2Utils.renderText("");
			return null;
		}

		String reminder  = planMain.getReminder();
		String reminder2 = planMain.getReminder2();
		
		String tmp = "";
		if(DictContants.PLAN_TYPE_COST.equals(planTypeCd)){
			tmp = reminder;
		}
		else if(DictContants.PLAN_TYPE_COST_PRE.equals(planTypeCd)){
			tmp = reminder2;
		}
		else{
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
	 * @param projNodeId
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchNodeReminders() throws Exception {
		
		CostProjectNodeRel planNodeRel = costProjectNodeRelManager.getEntity(projNodeId);
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
	 * @param executionPlanMainId
	 * @param projReminder
	 * @param planTypeCd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveProjReminders() throws Exception {
		if (StringUtils.isNotBlank(executionPlanMainId)) {
			if (costExecutionPlanMainManager.saveReminders(executionPlanMainId, projReminder, planTypeCd)) {
				Struts2Utils.renderText("done");
			}
		}
		return null;
	}

	// 功能: 删除detail
	public String deleteCostExecPlanDetail() {
		if (StringUtils.isNotBlank(planDetailId)) {
			costExecPlanDetailManager.deleteCostExecPlanDetail(planDetailId);
			Struts2Utils.renderText("ok");
		}
		return null;
	}

	/**
	 * 功能: 判断项目管理员是否可以点击完成按钮
	 * @param planDetailId
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkIfCanComplete() throws Exception {
		
		List<AppAttachFile> attachList = appAttachFileManager.getAttachFilesByEntityIdAndModuleCd(planDetailId, "costPlan");
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
	 * @param planDetailId
	 * 
	 * @return
	 * @throws Exception
	 */
	public String remindVerify() throws Exception {
		if (costExecPlanDetailManager.remindPlanDetail(planDetailId, CostExecPlanDetailManager.MAIL_TYPE_VERIFY)) {
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
	 * 
	 * @return
	 */
	private List<CostProjectNodeRel> buildProjPlanNodes(String projCd, String plTypeCd, String execPlanType,String rOrgCd,String rOrgName) {
		List<CostProjectNodeRel> result = new ArrayList<CostProjectNodeRel>();

		if ("control".equals(execPlanType)) {
			result = costProjectNodeRelManager.getValidProjControlNodes(projCd, plTypeCd, rOrgCd, rOrgName);
		} else {
			result = costProjectNodeRelManager.getValidProjNodes(projCd, plTypeCd, null,rOrgCd, rOrgName);
		}

		Collections.sort(result, new Comparator<CostProjectNodeRel>() {
			public int compare(CostProjectNodeRel nr1, CostProjectNodeRel nr2) {
				String cd1 = nr1.getNodeCd();
				String cd2 = nr2.getNodeCd();
				return Integer.parseInt(cd1) - Integer.parseInt(cd2);
			}
		});

		return result;
	} 
	/**
	 * 功能: 构造计划详情(成本)
	 * @param projNodes
	 *
	 * @return
	 */
	private Map<String, CostExecPlanDetail> buildPlanDetailMap(List<CostProjectNodeRel> projNodes) {
		Map<String, CostExecPlanDetail> m = new HashMap<String, CostExecPlanDetail>();
		String[] nodeIds = new String[projNodes.size()];

		for (int i = 0; i < projNodes.size(); i++) {
			CostProjectNodeRel n = projNodes.get(i);
			nodeIds[i] = n.getCostProjectNodeRelId();
		}

		List<CostExecPlanDetail> details = costExecPlanDetailManager.getDetailsByProjPlanNodes(nodeIds);
		for (CostExecPlanDetail d : details) {
			String key = d.getCostProjectNodeRel().getCostProjectNodeRelId() + "_" + d.getExecutionPlanCd();
			m.put(key, d);
		}

		return m;
	} 
	/**
	 * 功能: 构造人员信息
	 * @param userIds
	 * 
	 */
	private String buildPersonInfoData(String userIds) {
		if(StringUtils.isBlank(userIds))
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
	 * @param projCd
	 * 
	 */
	private void buildPermission(String projCd) {
		if (SpringSecurityUtils.hasRole(GlobalConstants.A_COSTP_SUP_ADMIN)) {
			superAdmin = true;
		} else if (SpringSecurityUtils.hasRole(GlobalConstants.A_COSTP_INPUTOR)) {
			infoInputor = true;
		} else if (SpringSecurityUtils.hasRole(GlobalConstants.A_COSTP_PJ_ADMIN)) {
			// 如果当前用户有项目管理员角色，则需要根据下面规则判断是否对当前项目有管理权限：
			// 1. 首先判断当前用户是否隶属当前项目，如果是，则是当前项目的项目管理员
			// 2. 如果不是，则判断是否拥有对该项目管理的权限，如果拥有，则是当前项目的管理员
			// 3. 如果1 2都不符合，则不是当前项目的项目管理员

			String orgCd = SpringSecurityUtils.getCurrentDeptCd();
			List<WsPlasOrg> orgList = PlasCache.getPhysicalBubbleOrgListByOrgCd(orgCd);
			for (WsPlasOrg org : orgList) {
				if (projCd.equals(org.getOrgCd())) {
					projAdmin = true;
					return;
				}
			}

			String tmpProjCd = "";
			String[] roleList = SpringSecurityUtils.getCurrentRoleCds();
			for (String role : roleList) {
				if (role.startsWith(GlobalConstants.A_COSTP_PJ_ADMIN_PREFIX)) {
					tmpProjCd = role.substring(GlobalConstants.A_COSTP_PJ_ADMIN_PREFIX.length());
					if (projCd.equals(tmpProjCd)) {
						projAdmin = true;
						return;
					}
				}
			}
		}
	}

	/**
	 * 功能: 获取所有项目公司列表(与中心一样)
	 * 
	 * @return
	 */
	public List<WsPlasOrg> fetchAllProjects() {
		
		//搜索所有项目
		WsPlasOrg org = null;
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

	public CostExecutionPlanMain getCostExecutionPlanMain() {
		return costExecutionPlanMain;
	}

	public void setCostExecutionPlanMain(CostExecutionPlanMain costExecutionPlanMain) {
		this.costExecutionPlanMain = costExecutionPlanMain;
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

	public Map<String, CostExecPlanDetail> getPlanDetailMap() {
		return planDetailMap;
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

	public CostExecPlanDetail getPlanDetailEntity() {
		return planDetailEntity;
	}

	public void setPlanDetailEntity(CostExecPlanDetail planDetailEntity) {
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

	public boolean isSuperAdmin() {
		return superAdmin;
	}

	public boolean isProjAdmin() {
		return projAdmin;
	}

	public boolean isInfoInputor() {
		return infoInputor;
	}

	public List<CostProjectNodeRel> getPlanNodes() {
		return planNodes;
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
	
	public Map<String,String> getMapExecCostPlan(){
		return mapExecCostPlan;
	}
	public Map<String, PlanExecPlanDetail> getPlanDetailPlanMap() {
		return planDetailPlanMap;
	}
	public void setPlanDetailPlanMap(Map<String, PlanExecPlanDetail> planDetailPlanMap) {
		this.planDetailPlanMap = planDetailPlanMap;
	}

	public List<PlanProjectNodeRel> getPlanNodesPlan() {
		return planNodesPlan;
	}
	public void setPlanNodesPlan(List<PlanProjectNodeRel> planNodesPlan) {
		this.planNodesPlan = planNodesPlan;
	}
	public List<String> getNodesMulCost() {
		return nodesMulCost;
	}
	public void setNodesMulCost(List<String> nodesMulCost) {
		this.nodesMulCost = nodesMulCost;
	}
	public List<Map<String, String>> getPlanWorkCenterMaps() {
		return planWorkCenterMaps;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public List<CostCtrlPlanRelVO> getCostCtrlPlanRelList() {
		return costCtrlPlanRelList;
	}
	public void setCostCtrlPlanRelList(List<CostCtrlPlanRelVO> costCtrlPlanRelList) {
		this.costCtrlPlanRelList = costCtrlPlanRelList;
	}
	
}

