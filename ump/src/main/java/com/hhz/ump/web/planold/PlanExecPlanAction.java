package com.hhz.ump.web.planold;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.hhz.ump.dao.plan.PlanWorkCenterManager;
import com.hhz.ump.dao.planold.PlanExecPlanDetailOldManager;
import com.hhz.ump.dao.planold.PlanExecutionPlanMainOldManager;
import com.hhz.ump.dao.planold.PlanExecutionPlanNodeOldManager;
import com.hhz.ump.dao.planold.PlanExecutionPlanOldManager;
import com.hhz.ump.dao.planold.PlanOperationLogOldManager;
import com.hhz.ump.dao.planold.PlanProjectNodeRelOldManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.plan.PlanCenterDetailsVO;
import com.hhz.ump.entity.planold.PlanExecPlanDetailOld;
import com.hhz.ump.entity.planold.PlanExecutionPlanMainOld;
import com.hhz.ump.entity.planold.PlanExecutionPlanNodeOld;
import com.hhz.ump.entity.planold.PlanExecutionPlanOld;
import com.hhz.ump.entity.planold.PlanProjectNodeRelOld;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.util.OrgTreeUtil;
import com.hhz.ump.web.plan.WorkPlanUtil;
import com.hhz.ump.web.vo.EntityVO;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 类名 PlanExecutionPlanAction 创建者 李劲 创建日期 2010-5-25 描述 控制计划模块
 */
@Namespace("/planold")
@Results( { @Result(name = "matrix2", location = "/WEB-INF/content/planold/plan-exec-plan-matrix2.ftl", type = "freemarker") })
public class PlanExecPlanAction extends ActionSupport {
   
	private static final long serialVersionUID = 6980708734848052372L;
	@Autowired
	private PlanExecutionPlanMainOldManager planMainManager;
	@Autowired
	private PlanExecutionPlanNodeOldManager planNodeManager;
	@Autowired
	private PlanExecutionPlanOldManager planManager;
	@Autowired
	private PlanProjectNodeRelOldManager projNodeRelManager;
	@Autowired
	private PlanExecPlanDetailOldManager detailManager;
	@Autowired
	private AppAttachFileManager attachManager;
	@Autowired
	private PlanOperationLogOldManager operationLogManager;
	@Autowired
	private PlanWorkCenterManager planWorkCenterManager;
	
	private String projectCd;
	private String projectName;//发起中心任务
	private PlanExecutionPlanMainOld planExecutionPlanMain;
	private String executionPlanMainId;
	private String projNodeId;
	private String nodeCompleteDate;
	private String projPlanId;
	private String projPlanName;
	private String projPlanState;
	private String projPlanCd;
	List<PlanCenterDetailsVO> detailsList;
	private Map<String, PlanExecPlanDetailOld> planDetailMap;

	private String userPoolPersons;
	private String planDetailId;
	private String planDetailTempId;
	private String planDetailStatus;
	private int planMatrixWidth;
	private PlanExecPlanDetailOld planDetailEntity;

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
	private List<PlanProjectNodeRelOld> planNodes = new ArrayList<PlanProjectNodeRelOld>();
	private List<PlanExecutionPlanOld> plans = new ArrayList<PlanExecutionPlanOld>();
	private List<AppAttachFile> planDetailOutput = new ArrayList<AppAttachFile>();
	private Map<String, String> planDetailStatusMap = new HashMap<String, String>();
	private Map<String, String> mapProjectsType = new HashMap<String, String>();

	private boolean superAdmin = false;
	private boolean projAdmin = false;
	private boolean infoInputor = false;
	private Date currentDate;
	
	
	//默认显示 1-执行计划
	private String planTypeCd = DictContants.PLAN_TYPE_EXC;
	
	
	//所有中心列表
	private List<EntityVO> resOrgListExec = new ArrayList<EntityVO>();
	private List<EntityVO> resOrgListPre = new ArrayList<EntityVO>();
	private List<EntityVO> resOrgListCost = new ArrayList<EntityVO>();
	
	//配合方
	private String resOrgCd;//发起中心任务,前台取值
	private String resOrgName;


	//发起中心任务
	private List<Map<String, String>> planWorkCenterMaps;
	private String costResOrgCd;
	private String linkSource;
	
	//查看模式 1-是,其他 -否
	private String viewModel;
	
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
		resOrgListExec = projNodeRelManager.getResOrgList(DictContants.PLAN_TYPE_EXC);
		resOrgListPre = projNodeRelManager.getResOrgList(DictContants.PLAN_TYPE_PRE);
		resOrgListCost = projNodeRelManager.getResOrgList(DictContants.PLAN_TYPE_COST);

		return "portal";
	}

	public String getCenterPlanRel() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = format.format(DateOperator.getDateNow());
		String nextMonthDate = format.format(DateOperator.addMonthes(DateOperator.getDateNow(), 1));
		if(null==centerCd || "".equalsIgnoreCase(centerCd)){
			WsPlasOrg my_ZX = SpringSecurityUtils.getCurrentOrgByType(Constants.ORG_TYPE_CD_ZX);
			centerCd = my_ZX.getOrgCd();
		}
		detailsList = planMainManager.fetchCenterDetails(nowDate, nextMonthDate, centerCd, projectCd, false);

		return "centerRel";
	}
	/**
	 * 成本控制计划的执行计划
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

		return "matrix2";
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
		planExecutionPlanMain = planMainManager.getExecutionPlanMainByProjCd(projectCd,planTypeCd);
		
		//节点
		planNodes = buildProjPlanNodes(projectCd, planTypeCd, planType,resOrgCd,resOrgName);
		
		//节点与业态映射关系
		planDetailMap = buildPlanDetailMap(planNodes);
		
		//业态
		plans = planManager.getPlansPerProjCd(projectCd, planTypeCd, true);
		
		//计算业态宽度
		planMatrixWidth = plans.size() * 200 + plans.size() * 3;
		
		//节点与业态映射关系点的状态
		planDetailStatusMap = detailManager.buildPlanDetailStatusMap();
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
		plans = planManager.getPlansPerProjCd(projectCd, planTypeCd, false);
		return "configPlan";
	}

	/**
	 * 功能: 控制计划详情输入
	 * @param planDetailId
	 * @param projNodeId
	 * @param projPlanCd
	 * @param viewModel 是否只读:1-是 其他-否
	 * @param costResOrgCd :成本计划责任机构
	 * @param linkSource : 来源 'cost'
	 * 
	 * @return
	 * @throws Exception
	 */
	public String planDetailInput() throws Exception {
		if (StringUtils.isBlank(planDetailId)) {
			planDetailEntity = new PlanExecPlanDetailOld();
			planDetailTempId = RandomUtils.generateTmpEntityId();
			outputBizEntityId = planDetailTempId;
		} else {
			planDetailEntity = detailManager.getEntity(planDetailId);
			outputBizEntityId = planDetailEntity.getPlanExecPlanDetailId();
		}

		String nodeName = "";
		PlanProjectNodeRelOld nodeRel = projNodeRelManager.getEntity(projNodeId);
		// 增加输出成果提示
		outFileTile = nodeRel.getOutputFiles();
		Map<String, PlanExecutionPlanNodeOld> nodeMap = planNodeManager.getExecPlanNodesMap();
		PlanExecutionPlanNodeOld n = nodeMap.get(nodeRel.getNodeCd());
		if (n != null) {
			nodeName = n.getNodeName();
		}
		String planName = planManager.getPlanNameByPlanCd(projPlanCd);
		planDetailCaption = nodeName + "-" + planName;
		planDetailStatusMap = detailManager.buildPlanDetailStatusMap();
		planDetailOutput = attachManager.getAttachFilesByEntityIdAndModuleCd(outputBizEntityId, "execPlan");

		projectCd = nodeRel.getPlanExecutionPlanMain().getProjectCd();
		projectName = nodeRel.getPlanExecutionPlanMain().getProjectName();
		resOrgCd = nodeRel.getResOrgCd();
		buildPermission(projectCd);

		// 搜索推送中心计划任务信息
		planWorkCenterMaps = planWorkCenterManager.getPlanWorkCenterMapByExec(planDetailId, "2");
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
			detailManager.addPlanDetailInfo(scheduleEndDate, scheduleEndDate, realEndDate, planDetailTempId, projNodeId, projPlanCd); 
		} else {
			PlanExecPlanDetailOld detail = detailManager.getEntity(planDetailId);
			// 若清空计划完成时间，并状态为未开始，则删除该record
			if ("".equals(scheduleEndDate) && "0".equals(detail.getStatus())) {
				detailManager.delete(detail);
				Struts2Utils.renderText("clear");
			} else {
				detailManager.svPlanDetailInfo(scheduleEndDate, scheduleEndDate, realEndDate, planDetailId, projNodeId, projPlanCd,
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
		if (detailManager.updatePlanDetailStatus(planDetailId, planDetailStatus)) {
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
		if (planManager.saveProjPlanInfo(projPlanId, projectCd, planTypeCd,projPlanName)) {
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
		if (planManager.changePlanState(projPlanId, projPlanState)) {
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
		planDetailOutput = attachManager.getAttachFilesByEntityIdAndModuleCd(outputBizEntityId, "execPlan");
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

		PlanExecPlanDetailOld planDetail = null;
		if (StringUtils.isBlank(planDetailId)) {
			planDetail = detailManager.getPlanDetailPerNodeRelIdAndPlanCd(projNodeId, projPlanCd);
		} else {
			planDetail = detailManager.getEntity(planDetailId);
		}

		if (planDetail != null) {
			StringBuilder jsonData = new StringBuilder("{");

			jsonData.append("'id':'" + planDetail.getPlanExecPlanDetailId() + "',");
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
	 * @param outputUpdateType 3-上传附件 4-删除附件
	 * @param deletedOutputFileId 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logOutputUpdateHistory() throws Exception {
		
		if (StringUtils.isNotBlank(planDetailId)) {
			PlanExecPlanDetailOld detail = detailManager.getEntity(planDetailId);
			PlanProjectNodeRelOld n = detail.getPlanProjectNodeRel();
			String nodeCd = n.getNodeCd();
			String nodeName = planNodeManager.getNodeNameByCd(nodeCd);
			String planCd = detail.getExecutionPlanCd();
			String planName = planManager.getPlanNameByPlanCd(planCd);
			String projCd = n.getPlanExecutionPlanMain().getProjectCd();

			String operationLog = "";
			//上传附件
			if (PlanOperationLogOldManager.OPERATION_TYPE_UPLOADATTACH.equalsIgnoreCase(outputUpdateType)) {
				List<AppAttachFile> attachList = attachManager.getAttachFilesByEntityIdAndModuleCd(planDetailId,
						"execPlan");
				String fileName = "";
				if (attachList.size() > 0) {
					AppAttachFile f = attachList.get(0);
					fileName = f.getRealFileName();
				}
				operationLog = "上传附件: " + fileName;
			} 
			//删除附件
			else if (PlanOperationLogOldManager.OPERATION_TYPE_DELETEATTACH.equalsIgnoreCase(outputUpdateType)) {
				AppAttachFile f = attachManager.getEntity(deletedOutputFileId);
				operationLog = "删除附件: " + f.getRealFileName();
			}
			
			operationLogManager.addPlanLog(detail.getPlanProjectNodeRel().getPlanTypeCd(), detail.getPlanExecPlanDetailId(), nodeName + "-" + planName, outputUpdateType, projCd, operationLog);
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
		
		PlanExecutionPlanMainOld planMain = planMainManager.getEntity(executionPlanMainId);
		if(StringUtils.isBlank(planTypeCd)){
			Struts2Utils.renderText("");
			return null;
		}

		String reminder  = planMain.getReminder();
		String reminder2 = planMain.getReminder2();
		
		String tmp = "";
		if(DictContants.PLAN_TYPE_EXC.equals(planTypeCd)){
			tmp = reminder;
		}
		else if(DictContants.PLAN_TYPE_PRE.equals(planTypeCd)){
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
		
		PlanProjectNodeRelOld planNodeRel = projNodeRelManager.getEntity(projNodeId);
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
	 * @param planDetailId
	 * 
	 * @return
	 * @throws Exception
	 */
	public String remindVerify() throws Exception {
		if (detailManager.remindPlanDetail(planDetailId, PlanExecPlanDetailOldManager.MAIL_TYPE_VERIFY)) {
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
	private List<PlanProjectNodeRelOld> buildProjPlanNodes(String projCd, String plTypeCd, String execPlanType,String rOrgCd,String rOrgName) {
		List<PlanProjectNodeRelOld> result = new ArrayList<PlanProjectNodeRelOld>();

		if ("control".equals(execPlanType)) {
			result = projNodeRelManager.getValidProjControlNodes(projCd, plTypeCd, rOrgCd, rOrgName);
		} else {
			result = projNodeRelManager.getValidProjNodes(projCd, plTypeCd, null,rOrgCd, rOrgName);
		}

//		Collections.sort(result, new Comparator<PlanProjectNodeRelOld>() {
//			@Override
//			public int compare(PlanProjectNodeRelOld nr1, PlanProjectNodeRelOld nr2) {
//				String cd1 = nr1.getNodeCd();
//				String cd2 = nr2.getNodeCd();
//				return Integer.parseInt(cd1) - Integer.parseInt(cd2);
//			}
//		});

		return result;
	}

	/**
	 * 功能: 构造计划详情
	 * @param projNodes
	 *
	 * @return
	 */
	private Map<String, PlanExecPlanDetailOld> buildPlanDetailMap(List<PlanProjectNodeRelOld> projNodes) {
		Map<String, PlanExecPlanDetailOld> m = new HashMap<String, PlanExecPlanDetailOld>();
		String[] nodeIds = new String[projNodes.size()];

		PlanProjectNodeRelOld n = null;
		for (int i = 0; i < projNodes.size(); i++) {
			n = projNodes.get(i);
			nodeIds[i] = n.getPlanProjectNodeRelId();
		}

		String key = null;
		for (PlanExecPlanDetailOld d : detailManager.getDetailsByProjPlanNodes(nodeIds)) {
			key = d.getPlanProjectNodeRel().getPlanProjectNodeRelId() + "_" + d.getExecutionPlanCd();
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
		if (SpringSecurityUtils.hasRole(GlobalConstants.A_EXECPLAN_SUP_ADMIN)) {
			superAdmin = true;
		} else if (SpringSecurityUtils.hasRole(GlobalConstants.A_EXECPLAN_INPUTOR)) {
			infoInputor = true;
		} else if (SpringSecurityUtils.hasRole(GlobalConstants.A_EXECPLAN_PJ_ADMIN)) {
			// 如果当前用户有项目管理员角色，则需要根据下面规则判断是否对当前项目有管理权限：
			// 1. 首先判断当前用户是否隶属当前项目，如果是，则是当前项目的项目管理员
			// 2. 如果不是，则判断是否拥有对该项目管理的权限，如果拥有，则是当前项目的管理员
			// 3. 如果1 2都不符合，则不是当前项目的项目管理员

			String orgCd = SpringSecurityUtils.getCurrentDeptCd();
			List<WsPlasOrg> orgList =PlasCache.getPhysicalBubbleOrgListByOrgCd(orgCd);
			for (WsPlasOrg org : orgList) {
				if (projCd.equals(org.getOrgCd())) {
					projAdmin = true;
					return;
				}
			}

			String tmpProjCd = "";
			String[] roleList = SpringSecurityUtils.getCurrentRoleCds();
			for (String role : roleList) {
				if (role.startsWith(GlobalConstants.A_EXECPLAN_PJ_ADMIN_PREFIX)) {
					tmpProjCd = role.substring(GlobalConstants.A_EXECPLAN_PJ_ADMIN_PREFIX.length());
					if (projCd.equals(tmpProjCd)) {
						projAdmin = true;
						return;
					}
				}
			}
		}
	}

	/**
	 * 功能: 获取所有项目公司列表
	 * @param mapProjectsType
	 * @param projectCd
	 * 
	 * @return
	 */
	public List<WsPlasOrg> fetchAllProjects() {
		
		//搜索所有项目
		/*
		WsPlasOrg org = null;
		List<WsPlasOrg> allProjs = new ArrayList<WsPlasOrg>();
		List<PlanExecutionPlanMainOld> allPlanMain = planMainManager.getAll();
		for (PlanExecutionPlanMainOld m : allPlanMain) {
			org = new WsPlasOrg();
			org.setOrgName(m.getProjectName());
			org.setOrgCd(m.getProjectCd());
			if (!"243".equals(m.getProjectCd())) {
				mapProjectsType.put(m.getProjectCd(), m.getProjectName());
				if (SpringSecurityUtils.getCurrentUaapUser().getCenterOrgCd().equals(m.getProjectCd())) {
					projectCd = m.getProjectCd();
				}
			}
			allProjs.add(org);
		}
		*/

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
				if (SpringSecurityUtils.getCurrentDeptCd().equals(m.getOrgCd())) {
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

	public PlanExecutionPlanMainOld getPlanExecutionPlanMain() {
		return planExecutionPlanMain;
	}

	public void setPlanExecutionPlanMain(PlanExecutionPlanMainOld planExecutionPlanMain) {
		this.planExecutionPlanMain = planExecutionPlanMain;
	}

	public List<WsPlasOrg> getProjects() {
		return projects;
	}

	public void setProjects(List<WsPlasOrg> projects) {
		this.projects = projects;
	}

	public List<PlanExecutionPlanOld> getPlans() {
		return plans;
	}

	public void setPlans(List<PlanExecutionPlanOld> plans) {
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

	public Map<String, PlanExecPlanDetailOld> getPlanDetailMap() {
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

	public PlanExecPlanDetailOld getPlanDetailEntity() {
		return planDetailEntity;
	}

	public void setPlanDetailEntity(PlanExecPlanDetailOld planDetailEntity) {
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

	public List<PlanProjectNodeRelOld> getPlanNodes() {
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
}

