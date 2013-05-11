package com.hhz.ump.web.plan;

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

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.DateParser;
import com.hhz.ump.dao.plan.PlanExecPlanDetailManager;
import com.hhz.ump.dao.plan.PlanExecutionPlanMainManager;
import com.hhz.ump.dao.plan.PlanExecutionPlanManager;
import com.hhz.ump.entity.plan.PlanExecPlanDetail;
import com.hhz.ump.entity.plan.PlanExecutionPlan;
import com.hhz.ump.entity.plan.PlanExecutionPlanMain;
import com.hhz.ump.entity.plan.PlanProjectNodeRel;
import com.hhz.ump.util.DictContants;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 类名 PlanExecutionPlanAction 创建者 李劲 创建日期 2010-5-25 描述 控制计划模块
 */
@Namespace("/plan")
@Results( { @Result(name = "matrix", location = "/WEB-INF/content/plan/plan-exec-plan-stat-matrix.ftl", type = "freemarker") })
public class PlanExecPlanStatAction extends ActionSupport {

	private static final long serialVersionUID = -6849349845964224948L;

	public static String PROCESS_STATUS_DELAY = "delay";
	public static String PROCESS_STATUS_TODO = "todo";
	public static String PROCESS_STATUS_DONE = "done";
	public static String PROCESS_STATUS_IGNORE = "ignore";

	@Autowired
	private PlanExecutionPlanMainManager planExecutionPlanMainManager;
//	@Autowired
//	private PlanExecutionPlanNodeManager planExecutionPlanNodeManager;
	@Autowired
	private PlanExecutionPlanManager planExecutionPlanManager;
//	@Autowired
//	private PlanProjectNodeRelManager planProjectNodeRelManager;
	@Autowired
	private PlanExecPlanDetailManager planExecPlanDetailManager;
//	@Autowired
//	private AppAttachFileManager appAttachFileManager;
//	@Autowired
//	private PlanOperationLogManager planOperationLogManager;

	private List<WsPlasOrg> projects = new ArrayList<WsPlasOrg>();
	private Map<String,WsPlasOrg> mapProjects = new HashMap<String,WsPlasOrg>();
	private List<PlanExecutionPlan> operations = new ArrayList<PlanExecutionPlan>();// 项目的业态清单

	private PlanExecutionPlanMain project;
	private String centerCd;
	private String projectCd;
	private String operationId;

	// 统计结果
	private Map<String, String> flagMap = new HashMap<String, String>();
	private Map<String, String> headMap = new HashMap<String, String>();
	private List<PlanExecPlanDetail> detailNodes = new ArrayList<PlanExecPlanDetail>();
	private List<PlanProjectNodeRel> ctrlNodes = new ArrayList<PlanProjectNodeRel>();
	private String curYear;
	private String curMonth;
	private String nextYear;
	private List<String> curMonths = new ArrayList<String>();
	private List<String> nextMonths = new ArrayList<String>();
	private List<String> months = new ArrayList<String>();
	private List<Integer> thMonths = new ArrayList<Integer>();

	private int nodesDoneCount;
	private int nodesDelayCount;
	private int nodesTodoCount;

	// yyyymm
	private List<String> yearMonths = new ArrayList<String>();

	// 今年，下年月份数
	// private int curMonthsSize;
	// private int nextMonthsSize;

	// private boolean superAdmin = false;
	// private boolean projAdmin = false;
	// private boolean infoInputor = false;
	
	//郑州的内码
	private static String ZZ_PROJECT_CD = "178";
	
	//计划类型: 1-执行计划 0-前期计划 2-前期计划
	private String planTypeCd = DictContants.PLAN_TYPE_EXC;
	private Map<String,String> mapProjectCdName = new HashMap<String,String>();


	/** 功能:项目清单(默认显示郑州地产)
	 * @param centerCd
	 * @param projectCd
	 * @param planTypeCd
	 * 
	 * @return
	 * @throws Exception
	 */
	public String portal() throws Exception {
		
		// 中心
		if (StringUtils.isNotBlank(centerCd)) {
			List<Object> list = planExecutionPlanMainManager.getProjectListByResOrgCd(centerCd,planTypeCd);
			WsPlasOrg org = null;
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				org = new WsPlasOrg();
				org.setOrgCd((String) obj[1]);
				org.setOrgName((String) obj[2]);
				projects.add(org);
			}
			if (StringUtils.isBlank(projectCd)) {
				projectCd = ZZ_PROJECT_CD;
//					if (list != null && list.size() > 0) {
//						projectCd = (String) ((Object[]) list.get(0))[1];// 取机构CD
//					}
			}
		}
		// 项目公司
		else if (StringUtils.isNotBlank(projectCd)) {
			List<PlanExecutionPlanMain> list = planExecutionPlanMainManager.getProjectListByProjectCd(projectCd);
			PlanExecutionPlanMain obj = null;
			for (int i = 0; i < list.size(); i++) {
				obj = list.get(i);
				WsPlasOrg org = new WsPlasOrg();
				org.setOrgCd(obj.getProjectCd());
				org.setOrgName(obj.getProjectName());
				projects.add(org);

			}
		}
		// 管理员/所有用户
		else{
			projects = planExecutionPlanMainManager.fetchAllProjects();
			if (StringUtils.isBlank(projectCd)) {
				projectCd = ZZ_PROJECT_CD;
			}
		}

		//显示项目中文名称
		mapProjectCdName.clear();
		for (int i = 0; i < projects.size(); i++) {
			WsPlasOrg org = projects.get(i);
			mapProjectCdName.put(org.getOrgCd(),org.getOrgName());
		}
		return "portal";
	}

	/**功能:切换项目,显示某项目名称/业态清单
	 * @param projectCd
	 * @param planTypeCd
	 * @param operationId
	 * 
	 * @return
	 */
	public String switchProject() {

//		boolean[] permissions = WorkPlanUtil.getWorkPlanPermission(projectCd);
//		superAdmin = permissions[0];
//		projAdmin = permissions[1];
//		infoInputor = permissions[2];

		operations = planExecutionPlanManager.getPlansPerProjCd(projectCd, planTypeCd, true);//默认只有执行计划才看进度.
		if (StringUtils.isBlank(operationId) && operations != null && operations.size() > 0) {
			operationId = operations.get(0).getPlanExecutionPlanId();
		}
		return "operations";
	}

	/**
	 * 功能:切换业态,显示某业态的统计结果(控制节点清单/当前年月/起止年月/各个列完成情况)
	 * @param projectCd
	 * @param planTypeCd
	 * @param operationId
	 * 
	 */
	public String switchOperation() {

		curMonths.clear();
		nextMonths.clear();
		months.clear();
		yearMonths.clear();

		// 控制节点清单
		project = planExecutionPlanMainManager.getPlanMainByProjCd(projectCd);
		PlanExecutionPlan operationPlan = planExecutionPlanManager.getEntity(operationId);
		detailNodes = planExecPlanDetailManager.getSortedStatPlanNodeDetail(project.getProjectCd(),planTypeCd,operationPlan.getExecutionPlanCd());

		
		// 当前年月
		Date today = DateParser.getDateNow();
		curYear = DateParser.formatDate(today, "yyyy");
		curMonth = DateParser.formatDate(today, "MM");
		
		// 起止年月
		String[] conMonths = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"}; 
		int indexPos = -1;
		boolean enablePush = false;
		yearMonths.add("node");
		for (int i = 0; i < conMonths.length; i++) {
			if (enablePush) {
				curMonths.add(conMonths[i]);
				yearMonths.add(curYear + conMonths[i]);
			} else {
				if (curMonth.equals(conMonths[i])) {
					curMonths.add(conMonths[i]);
					yearMonths.add(curYear + conMonths[i]);
					indexPos = i;
					enablePush = true;
				}
			}
		}

		// 若跨年
		if (indexPos > 0) {
			nextYear = DateOperator.formatDate(DateOperator.addYears(today, 1), "yyyy");
			for (int i = 0; i < indexPos; i++) {
				nextMonths.add(conMonths[i]);
				yearMonths.add(nextYear + conMonths[i]);
			}
		}
		
		// 构造映射,打标记(0-延期 1-完成 2-待完成
		PlanExecPlanDetail d =null;
		for(int i=0;i< detailNodes.size();i++){
			d = detailNodes.get(i);
			String detailId = d.getPlanExecPlanDetailId();
			String nodeId = d.getPlanProjectNodeRel().getPlanProjectNodeRelId();
			Date planEndDate = d.getScheduleEndDate();
			Date realEndDate = d.getRealEndDate();
			
			String flag = PROCESS_STATUS_TODO;
			if (planEndDate == null) {
				if (realEndDate == null) {
					flag = PROCESS_STATUS_TODO;
				} else {
					if (realEndDate.compareTo(today) <= 0) {
						flag = "1";
					} else {
						flag = PROCESS_STATUS_TODO;
					}
				}
			} else {
				if (planEndDate.compareTo(today) <= 0) {
					if (realEndDate == null) {
						flag = PROCESS_STATUS_DELAY;
					} else {
						if (realEndDate.compareTo(planEndDate) <= 0) {
							flag = PROCESS_STATUS_DONE;
						} else {
							flag = PROCESS_STATUS_TODO;
						}
					}
				} else {
					if (realEndDate == null) {
						flag = PROCESS_STATUS_TODO;
					} else {
						if (realEndDate.compareTo(planEndDate) <= 0) {
							flag = PROCESS_STATUS_DONE;
						} else {
							flag = PROCESS_STATUS_TODO;
						}
					}
				}
			}
			//key-value: nodeId_yearMonth-flag

			if (PROCESS_STATUS_DELAY.equals(flag)) {
				flagMap.put(detailId + "_node", flag);
				flagMap.put(nodeId + "_node", flag);
			}
			flagMap.put(detailId + "_" + DateOperator.formatDate(planEndDate, "yyyyMM"), flag);
			flagMap.put(nodeId + "_" + DateOperator.formatDate(planEndDate, "yyyyMM"), flag);

			if (PROCESS_STATUS_DONE.equals(flag)) {
				nodesDoneCount++;
			}
			if (PROCESS_STATUS_DELAY.equals(flag)) {
				nodesDelayCount++;
			}
			if (PROCESS_STATUS_TODO.equals(flag)) {
				nodesTodoCount++;
			}
		} 
		months.addAll(curMonths);
		months.addAll(nextMonths);

		for (int i = 0; i < curMonths.size(); i++) {
			thMonths.add(Integer.valueOf(curMonths.get(i)));
		}
		for (int i = 0; i < nextMonths.size(); i++) {
			thMonths.add(Integer.valueOf(nextMonths.get(i)));
		}

		return "matrix";
	}

	public List<String> getYearMonths() {
		return yearMonths;
	}

	public void setYearMonths(List<String> yearMonths) {
		this.yearMonths = yearMonths;
	}

	public List<Integer> getThMonths() {
		return thMonths;
	}

	public void setThMonths(List<Integer> thMonths) {
		this.thMonths = thMonths;
	}


	public List<WsPlasOrg> getProjects() {
		return projects;
	}

	public void setProjects(List<WsPlasOrg> projects) {
		this.projects = projects;
	}

	public PlanExecutionPlanMain getProject() {
		return project;
	}

	public void setProject(PlanExecutionPlanMain project) {
		this.project = project;
	}

	public List<PlanExecutionPlan> getOperations() {
		return operations;
	}

	public void setOperations(List<PlanExecutionPlan> operations) {
		this.operations = operations;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public Map<String, String> getFlagMap() {
		return flagMap;
	}

	public void setFlagMap(Map<String, String> flagMap) {
		this.flagMap = flagMap;
	}

	public Map<String, String> getHeadMap() {
		return headMap;
	}

	public void setHeadMap(Map<String, String> headMap) {
		this.headMap = headMap;
	}

	public List<String> getCurMonths() {
		return curMonths;
	}

	public void setCurMonths(List<String> curMonths) {
		this.curMonths = curMonths;
	}

	public List<String> getNextMonths() {
		return nextMonths;
	}

	public void setNextMonths(List<String> nextMonths) {
		this.nextMonths = nextMonths;
	}

	public String getCurYear() {
		return curYear;
	}

	public void setCurYear(String curYear) {
		this.curYear = curYear;
	}

	public String getCurMonth() {
		return curMonth;
	}

	public void setCurMonth(String curMonth) {
		this.curMonth = curMonth;
	}

	public String getNextYear() {
		return nextYear;
	}

	public void setNextYear(String nextYear) {
		this.nextYear = nextYear;
	}

	public List<PlanExecPlanDetail> getDetailNodes() {
		return detailNodes;
	}

	public void setDetailNodes(List<PlanExecPlanDetail> detailNodes) {
		this.detailNodes = detailNodes;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public List<String> getMonths() {
		return months;
	}

	public void setMonths(List<String> months) {
		this.months = months;
	}

	public int getCurMonthsSize() {
		// return curMohthsSize;
		if (curMonths == null)
			return 0;
		else
			return curMonths.size();
	}

	// public void setCurMonthsSize(int curMonthsSize) {
	// this.curMonthsSize = curMonthsSize;
	// }

	public int getNextMonthsSize() {
		// return nextMohthsSize;
		if (nextMonths == null)
			return 0;
		else
			return nextMonths.size();
	}

	// public void setNextMonthsSize(int nextMonthsSize) {
	// this.nextMonthsSize = nextMonthsSize;
	// }

	public List<PlanProjectNodeRel> getCtrlNodes() {
		return ctrlNodes;
	}

	public void setCtrlNodes(List<PlanProjectNodeRel> ctrlNodes) {
		this.ctrlNodes = ctrlNodes;
	}

	public int getNodesDoneCount() {
		return nodesDoneCount;
	}

	public void setNodesDoneCount(int nodesDoneCount) {
		this.nodesDoneCount = nodesDoneCount;
	}

	public int getNodesDelayCount() {
		return nodesDelayCount;
	}

	public void setNodesDelayCount(int nodesDelayCount) {
		this.nodesDelayCount = nodesDelayCount;
	}

	public int getNodesTodoCount() {
		return nodesTodoCount;
	}

	public void setNodesTodoCount(int nodesTodoCount) {
		this.nodesTodoCount = nodesTodoCount;
	}

	public String getCenterCd() {
		return centerCd;
	}

	public void setCenterCd(String centerCd) {
		this.centerCd = centerCd;
	}

	public Map<String, WsPlasOrg> getMapProjects() {
		//return mapProjects;
		if(projects == null)
			return new HashMap<String,WsPlasOrg>();
		if(mapProjects == null){
			mapProjects = new HashMap<String,WsPlasOrg>();
		}
		for (int i = 0; i < projects.size(); i++) {
			WsPlasOrg org = projects.get(i);
			mapProjects.put(org.getOrgCd(), org);
		}
		return mapProjects;
	}

	public void setMapProjects(Map<String, WsPlasOrg> mapProjects) {
		this.mapProjects = mapProjects;
	}

	public String getPlanTypeCd() {
		return planTypeCd;
	}

	public void setPlanTypeCd(String planTypeCd) {
		this.planTypeCd = planTypeCd;
	}

	public Map<String, String> getMapProjectCdName() {
		return mapProjectCdName;
	}

	public void setMapProjectCdName(Map<String, String> mapProjectCdName) {
		this.mapProjectCdName = mapProjectCdName;
	}

}
