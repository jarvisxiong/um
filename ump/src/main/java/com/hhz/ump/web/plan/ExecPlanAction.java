package com.hhz.ump.web.plan;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.utils.EncodeUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.RandomUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.plan.ExecPlanDetailManager;
import com.hhz.ump.dao.plan.ExecPlanDetailMessManager;
import com.hhz.ump.dao.plan.ExecPlanDetailPlusManager;
import com.hhz.ump.dao.plan.ExecPlanLayoutManager;
import com.hhz.ump.dao.plan.ExecPlanNodeManager;
import com.hhz.ump.dao.plan.PlanWorkCenterManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.oa.OaAllAttention;
import com.hhz.ump.entity.plan.ExecPlanAllVo;
import com.hhz.ump.entity.plan.ExecPlanDetail;
import com.hhz.ump.entity.plan.ExecPlanDetailMess;
import com.hhz.ump.entity.plan.ExecPlanDetailPlus;
import com.hhz.ump.entity.plan.ExecPlanExcelBisVO;
import com.hhz.ump.entity.plan.ExecPlanLayout;
import com.hhz.ump.entity.plan.ExecPlanNode;
import com.hhz.ump.entity.plan.ExecPlanNodeParentVo;
import com.hhz.ump.entity.plan.PlanCenterDetailsVO;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.util.OrgTreeUtil;
import com.hhz.ump.util.PlanExecExportUtil;
import com.hhz.ump.web.vo.EntityVO;
import com.hhz.uums.entity.ws.WsAppDictData;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 类名ExecPlanAction 创建者 卢俊云 创建日期 2011-8-18 描述 执行计划模块 planTypeCd: 1:三级计划 5:建设开发计划
 * 10：商业四级计划汇总 11：商业四级计划和开业筹备五级计划 14：商业四级计划 15：商业开业筹备五级计划 16:商业开业前招商五级计划
 * exec_plan_layout 业态表 exec_plan_detail 节点明细表 exec_plan_node 节点模板表
 */
@Namespace("/plan")
@Results( {
		@Result(name = "matrix", location = "/WEB-INF/content/plan/exec-plan-matrix.ftl", type = "freemarker"),
		@Result(name = "matrixBis", location = "/WEB-INF/content/plan/exec-plan-matrixBis.ftl", type = "freemarker"),
		@Result(name = "matrixBisAll", location = "/WEB-INF/content/plan/exec-plan-matrixBisAll.ftl", type = "freemarker"),
		@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName", "is", "contentDisposition",
				"attachment;filename=${downFileName}.xls" }) })
public class ExecPlanAction extends ActionSupport {

	private static final long serialVersionUID = -5833728668625630228L;

	@Autowired
	private ExecPlanNodeManager execPlanNodeManager;
	@Autowired
	private ExecPlanLayoutManager execPlanLayoutManager;
	@Autowired
	private ExecPlanDetailManager detailManager;
	@Autowired
	private ExecPlanDetailMessManager execPlanDetailMessManager;
	@Autowired
	private ExecPlanDetailPlusManager execPlanDetailPlusManager;
	@Autowired
	private AppAttachFileManager attachManager;
	@Autowired
	private PlanWorkCenterManager planWorkCenterManager;

	List<PlanCenterDetailsVO> detailsList;
	private Map<String, ExecPlanDetail> planDetailMap;
	private Map<String, ExecPlanDetail> planDetailMap16;

	public Map<String, ExecPlanDetail> getPlanDetailMap16() {
		return planDetailMap16;
	}

	public void setPlanDetailMap16(Map<String, ExecPlanDetail> planDetailMap16) {
		this.planDetailMap16 = planDetailMap16;
	}

	private Map<String, ExecPlanNode> parentNodeMap;
	private Map<String, ExecPlanDetail> parentDetailMap;
	private Map<String, ExecPlanNodeParentVo> parentNodeVoMap;
	private Map<String, String> mapNodeName;
	private Map<String, String> mapNodeName16;

	public Map<String, String> getMapNodeName16() {
		return mapNodeName16;
	}

	public void setMapNodeName16(Map<String, String> mapNodeName16) {
		this.mapNodeName16 = mapNodeName16;
	}

	private Map<String, String> mapTips = new HashMap<String, String>(); // 节点的title，包含前三条留言记录
	private Map<String, String> mapTips2 = new HashMap<String, String>(); // 父节点的title，包含前三条留言记录
	private Map<String, String> tipMap;
	private List<ExecPlanAllVo> execPlanAllVoList;
	private ExecPlanDetail planDetailEntity;

	private boolean if_bis = false; // 是否是商业计划
	private String projectCd;
	private String projectName;
	private String executionPlanMainId;
	private String nodeId;
	private String nodeCompleteDate;
	private String projPlanId;
	private String projPlanName;
	private String projPlanState;
	private String layoutId;

	private String userPoolPersons;
	private String planDetailId;
	private String planDetailTempId;
	private String planDetailStatus;
	private int planMatrixWidth;

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
	private List<ExecPlanNode> viewPlanNodes; // 显示的节点列表
	private List<ExecPlanLayout> execPlanLayouts; // 全部的业态
	private List<ExecPlanLayout> searchedPlans; // 现在正在用的业态
	private List<AppAttachFile> planDetailOutput;
	private List<ExecPlanDetailPlus> listExecPlanDetailPlus; // 节点对应的(次)主力店
	private Map<String, String> planDetailStatusMap;
	private LinkedHashMap<String, String> mapProjectsType= new LinkedHashMap<String,String>();
	private Map<String, String> mapPrintHtml; // 列表中显示的HTML
	private Map<String, String> mapPrintStartDate; // 列表中显示的HTML
	private Map<String, String> mapPrintEndDate; // 列表中显示的HTML
	private Map<String, String> mapPrintStartDate1; // 
	private Map<String, String> mapPrintEndDate1; // 
	private Map<String, String> mapPrintStartDate116; // 
	private Map<String, String> mapPrintEndDate116; // 
	private Map<String, String> mapExecPlanTips = new HashMap<String, String>();

	public Map<String, String> getMapExecPlanTips() {
		return mapExecPlanTips;
	}

	public Map<String, String> getMapPrintStartDate116() {
		return mapPrintStartDate116;
	}

	public void setMapPrintStartDate116(Map<String, String> mapPrintStartDate116) {
		this.mapPrintStartDate116 = mapPrintStartDate116;
	}

	public Map<String, String> getMapPrintEndDate116() {
		return mapPrintEndDate116;
	}

	public void setMapPrintEndDate116(Map<String, String> mapPrintEndDate116) {
		this.mapPrintEndDate116 = mapPrintEndDate116;
	}

	private boolean aExecSuperAdmin = false; // 是否是超级管理员（能修改所有节点）
	private boolean aExecAdmin = false; // 是否是管理员（能管理业态等）
	private boolean aAddPoint = false; // 能否新增节点
	private boolean aConfirmPoint = false; // 能否确认节点
	private boolean aWritePoint = false; // 能否填写节点
	private boolean aCompletePoint = false; // 能否完成节点
	private boolean aChangeSchedule = false; // 能否修改计划时间
	private boolean aShowStandard = false; // 能否查看标准(商业计划用)
	private boolean aAddFlg = false; // 能否新增节点(主/次主力店签约)
	private boolean aModFlg = false; // 能否修改节点(主/次主力店签约)
	private boolean aOnlyCreator = false; // 是否进入只限上传者查看附件模式(商业用)
	private String myUiid = ""; // 当前用户的uiid，用于aDownload的前台判断

	private boolean aAttention = false;
	// 每页显示10条指令单
	private Page<ExecPlanNode> page = new Page<ExecPlanNode>(2);

	private Date currentDate;

	private boolean ifProjectChange = false; // 是否切换项目，如果是就启动选择所有业态

	// 默认显示 1-执行计划
	private String planTypeCd = DictContants.PLAN_TYPE_EXC; // 字典已不用，参考文件头部说明

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

	private int now_view_style = 2;//当前页面显示按钮：精简、完整

	private int nMainSigned = 1; // (商业执行计划)主力店已签约数
	private int nMainUnsign = 1; // (商业执行计划)主力店未签约数
	private int nSubSigned = 1; // (商业执行计划)次主力店已签约数
	private int nSubUnsign = 1; // (商业执行计划)次主力店未签约数
	private int nMainIn = 1; // (商业执行计划)主力店已进场数
	private int nMainUnin = 1; // (商业执行计划)主力店未进场数
	private int nSubIn = 1; // (商业执行计划)次主力店已进场数
	private int nSubUnin = 1; // (商业执行计划)次主力店未进场数
	private int nRecordVersion = 1;
	private String flag;

	private InputStream is;
	private String downFileName;
	private List<ExecPlanNode> viewPlanNodes16; // 显示的节点列表
	private List<ExecPlanNode> messageViewExecPlanNodeList = new ArrayList<ExecPlanNode>();;// 今日留言节点

	public List<ExecPlanNode> getMessageViewExecPlanNodeList() {
		return messageViewExecPlanNodeList;
	}

	public void setMessageViewExecPlanNodeList(List<ExecPlanNode> messageViewExecPlanNodeList) {
		this.messageViewExecPlanNodeList = messageViewExecPlanNodeList;
	}

	public List<ExecPlanNode> getViewPlanNodes16() {
		return viewPlanNodes16;
	}

	public void setViewPlanNodes16(List<ExecPlanNode> viewPlanNodes16) {
		this.viewPlanNodes16 = viewPlanNodes16;
	}

	public String getDownFileName() {
		return downFileName;
	}

	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	/**
	 * 功能: 商业计划平台》商业四级计划(与项目开发计划中的四级计划一致)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String portalforward() throws Exception {
	    	portal();
		return "portalforward";
	}
	/**
	 * 功能: 项目开发计划主入口
	 * 
	 * @return
	 * @throws Exception
	 */
	public String portal() throws Exception {
		// 对商业四级进行特殊处理 start
		if ("24".equalsIgnoreCase(planTypeCd)) {
			List<WsAppDictData> listDict = PlasCache.getDictDataList("LAND_BIS_REL");
			for (int i = 0; null != listDict && i < listDict.size(); i++) {
				WsAppDictData dict = listDict.get(i);
				if (!GlobalConstants.WYS_ORG_CD.equals(dict.getRemark())) {
					if (SpringSecurityUtils.getCurrentCenterCd().equals(dict.getDictCd())) {
						projectCd = dict.getRemark();
						break;
					}
				}
			}
			if (SpringSecurityUtils.hasRole("A_EXEC_BIS_CENTER")) {
				aAddPoint = true;
			}
			// 
			if (StringUtils.isBlank(projectCd)) {
				projectCd = "0000";
			}
		}
		// end
		// start 2012-1-6
		if ("1".equalsIgnoreCase(planTypeCd)) {
			List<WsPlasOrg> list = WorkPlanUtil.getOrgEstateOrgList();
			for (WsPlasOrg m : list) {
				if (!GlobalConstants.WYS_ORG_CD.equals(m.getOrgCd())) {
					if (SpringSecurityUtils.getCurrentCenterCd().equals(m.getOrgCd())) {
						projectCd = m.getOrgCd();
						break;
					}
				}
			}
			if (StringUtils.isBlank(projectCd)) {
				projectCd = "0000";
			}
		}// end
		// 构造项目切换菜单
		projects = fetchAllProjects();
		// 默认显示即墨地产
		if (StringUtils.isBlank(projectCd)) {
			projectCd = "288";
		}
		currentDate = DateOperator.getDateNow();

		// 构造权限
		buildPermission(projectCd, null);

		//execPlanLayouts = execPlanLayoutManager.getLayouts(projectCd, planTypeCd, true, if_bis);

		if ("5".equalsIgnoreCase(planTypeCd) || "10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd) || "14".equalsIgnoreCase(planTypeCd)
				|| "15".equalsIgnoreCase(planTypeCd) || "16".equalsIgnoreCase(planTypeCd) || "24".equalsIgnoreCase(planTypeCd)){
			//now_view_style = 2;
		}
		myUiid = SpringSecurityUtils.getCurrentUiid();
		return "portal";
	}

	/**
	 * 功能: 显示某地产公司的执行计划信息表格，主搜索
	 * 
	 * @param projectCd
	 * @param planTypeCd
	 * @param planType
	 * @param resOrgCd
	 * @param resOrgName
	 * @throws Exception
	 */
	public String planMatrix() throws Exception {
		currentDate = DateOperator.getDateNow();

		if (StringUtils.isBlank(projectCd)) {
			projectCd = "288";
		}
		buildPermission(projectCd, null);

		// 所有业态
		execPlanLayouts = execPlanLayoutManager.getLayouts(projectCd, planTypeCd, true, if_bis);
		if (ifProjectChange) {
			// 如果是部门切换，选择所有业态
			searchPlans = "";
			for (ExecPlanLayout epl : execPlanLayouts) {
				searchPlans += epl.getExecPlanLayoutId() + ",";
			}
			if (!"".equalsIgnoreCase(searchPlans)) {
				searchPlans = searchPlans.substring(0, searchPlans.length() - 1);
			}
		}
		// 所选业态
		try {
			if (null != searchPlans) {
				searchedPlans = new ArrayList<ExecPlanLayout>();
				String[] arr_plans = searchPlans.split(",");
				for (ExecPlanLayout epl : execPlanLayouts) {
					for (String compare_plan : arr_plans) {
						if (compare_plan.equalsIgnoreCase(epl.getExecPlanLayoutId())) {
							searchedPlans.add(epl);
							break;
						}
					}
				}
			} else {
				searchedPlans = new ArrayList<ExecPlanLayout>();
			}
		} catch (Exception e) {
			searchedPlans = execPlanLayouts;
		}

		// 所有节点
		// planNodes = buildProjPlanNodes(projectCd, planTypeCd,
		// planType,resOrgCd,resOrgName,null);
		// 显示节点
		if ("10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd) || "24".equalsIgnoreCase(planTypeCd))
		{
			// 如果是商业计划汇总，同时显示四级、五级计划；如果是商业四级计划，同时显示四级、开业筹备五级计划
			execPlanAllVoList = new ArrayList<ExecPlanAllVo>();
			List<ExecPlanNode> nodeList14 = execPlanNodeManager.searchNodes(planTypeCd, nowPointLevel, nowResOrgNames);
			/*
			 * 注释 by dido(2011.12.13) 解决新的商业四级计划 List<ExecPlanNode> nodeList14 =
			 * execPlanNodeManager.searchNodes( "14", nowPointLevel,
			 * nowResOrgNames); List<ExecPlanNode> nodeList15 =
			 * execPlanNodeManager.searchNodes( "15", nowPointLevel,
			 * nowResOrgNames); List<ExecPlanNode> nodeList16 =
			 * execPlanNodeManager.searchNodes( "16", nowPointLevel,
			 * nowResOrgNames);
			 */
			viewPlanNodes = nodeList14; // 商业四级节点才搜索项目三级关联节点
			int now_allList_i = 0; // 用来设置相同节点的i，每个循环都用
			for (int i = 0; null != nodeList14 && i < nodeList14.size(); i++) {
				ExecPlanNode epn14 = nodeList14.get(i);
				ExecPlanAllVo allvo14 = new ExecPlanAllVo();
				allvo14.setExecPlanNodeId(epn14.getExecPlanNodeId());
				allvo14.setSequenceNo(epn14.getSequenceNo());
				allvo14.setSequenceNoHide(epn14.getSequenceNo());
				allvo14.setNodeName14(epn14.getNodeName());
				allvo14.setResOrgName(epn14.getResOrgName());
				allvo14.setOutputFiles(epn14.getOutputFiles());
				allvo14.setStandardStartDate(epn14.getStandardStartDate());
				allvo14.setStandardEndDate(epn14.getStandardEndDate());
				execPlanAllVoList.add(allvo14);
				now_allList_i = Integer.valueOf(execPlanAllVoList.size() - 1);
				/*
				 * 注释 by dido(2011.12.13) 解决新的商业四级计划 for(int
				 * j=0;null!=nodeList15 && j<nodeList15.size();j++){
				 * ExecPlanNode epn15 = nodeList15.get(j);
				 * if(StringUtils.isNotBlank(epn15.getTreeParentNodeId()) &&
				 * epn14
				 * .getExecPlanNodeId().equalsIgnoreCase(epn15.getTreeParentNodeId
				 * ())){ if(null!=epn15.getIfParentSame() &&
				 * epn15.getIfParentSame()){ //如果是相同节点，不做操作 // ExecPlanAllVo
				 * allvo14Temp = execPlanAllVoList.get(now_allList_i); //
				 * allvo14Temp.setNodeName15(epn15.getNodeName()); //
				 * allvo14Temp.setExecPlanNodeId15(epn15.getExecPlanNodeId());
				 * // execPlanAllVoList.set(now_allList_i, allvo14Temp); }else{
				 * //如果是不同节点，增加一个Vo对象 ExecPlanAllVo allvo15 = new
				 * ExecPlanAllVo();
				 * allvo15.setExecPlanNodeId(epn15.getExecPlanNodeId());
				 * allvo15.setSequenceNoHide(epn15.getSequenceNo());
				 * allvo15.setNodeName14("&nbsp;"+epn15.getNodeName());
				 * allvo15.setResOrgName(epn15.getResOrgName());
				 * allvo15.setOutputFiles(epn15.getOutputFiles());
				 * allvo15.setStandardStartDate(epn15.getStandardStartDate());
				 * allvo15.setStandardEndDate(epn15.getStandardEndDate());
				 * allvo15.setExecPlanNodeId(epn15.getExecPlanNodeId());
				 * 
				 * //如果是招商5级计划，相同的节点，删除招商5级计划的节点 for(int k=0;null!=nodeList16 &&
				 * k<nodeList16.size();k++){ ExecPlanNode epn16 =
				 * nodeList16.get(k);
				 * if(StringUtils.isNotBlank(epn16.getParentId15())
				 * &&epn15.getExecPlanNodeId
				 * ().equalsIgnoreCase(epn16.getParentId15())){
				 * allvo15.setSequenceNoHide(epn16.getSequenceNo());
				 * allvo15.setNodeName16(epn16.getNodeName());
				 * nodeList16.remove(k); break; } }
				 * execPlanAllVoList.add(allvo15); } nodeList15.remove(j); j--;
				 * } } if("10".equalsIgnoreCase(planTypeCd)){ for(int
				 * k=0;null!=nodeList16 && k<nodeList16.size();k++){
				 * ExecPlanNode epn16 = nodeList16.get(k);
				 * if(StringUtils.isNotBlank(epn16.getTreeParentNodeId()) &&
				 * epn14
				 * .getExecPlanNodeId().equalsIgnoreCase(epn16.getTreeParentNodeId
				 * ())){ if(null!=epn16.getIfParentSame() &&
				 * epn16.getIfParentSame()){ //如果是相同节点，不做操作 // ExecPlanAllVo
				 * allvo14Temp = execPlanAllVoList.get(now_allList_i); //
				 * allvo14Temp.setNodeName16(epn16.getNodeName()); //
				 * execPlanAllVoList.set(now_allList_i, allvo14Temp); }else{
				 * //如果是不同节点，增加一个Vo对象 ExecPlanAllVo allvo16 = new
				 * ExecPlanAllVo();
				 * allvo16.setExecPlanNodeId(epn16.getExecPlanNodeId());
				 * allvo16.setSequenceNoHide(epn16.getSequenceNo());
				 * allvo16.setNodeName14("&nbsp;&nbsp;"+epn16.getNodeName());
				 * allvo16.setResOrgName(epn16.getResOrgName());
				 * allvo16.setOutputFiles(epn16.getOutputFiles());
				 * allvo16.setStandardStartDate(epn16.getStandardStartDate());
				 * allvo16.setStandardEndDate(epn16.getStandardEndDate());
				 * execPlanAllVoList.add(allvo16); } nodeList16.remove(k); k--;
				 * } } }
				 */
			}
		} else {
			viewPlanNodes = execPlanNodeManager.searchNodes(planTypeCd, nowPointLevel, nowResOrgNames);
		}

		mapNodeName = new HashMap<String, String>();
		mapNodeName16 = new HashMap<String, String>();
		parentNodeMap = new HashMap<String, ExecPlanNode>();
		parentNodeVoMap = new HashMap<String, ExecPlanNodeParentVo>(); // 商业的汇总界面用到
		if ("5".equalsIgnoreCase(planTypeCd) || "10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd) || "14".equalsIgnoreCase(planTypeCd)
				|| "15".equalsIgnoreCase(planTypeCd) || "16".equalsIgnoreCase(planTypeCd) || "24".equalsIgnoreCase(planTypeCd)) {
			// 如果是专项计划和商业计划，处理关联节点
			// boolean flag = true;
			List<ExecPlanNode> viewPlanNodesList = execPlanNodeManager.searchNodes(planTypeCd, nowPointLevel, nowResOrgNames);
			Constants.viewPlanNodes16 = viewPlanNodesList;
			for (int i = 0; null != viewPlanNodes && i < viewPlanNodes.size(); i++) {
				ExecPlanNode ppnr = (ExecPlanNode) viewPlanNodes.get(i);
				mapNodeName16.put(ppnr.getExecPlanNodeId(), ppnr.getNodeName());
				// if("16".equalsIgnoreCase(planTypeCd)){
				// if(flag){
				// mapNodeName16.put(ppnr.getExecPlanNodeId(),ppnr.getNodeName());
				// // Constants.viewPlanNodes16 = viewPlanNodes;
				// }
				// }
				// flag = false;
				if ("14".equalsIgnoreCase(planTypeCd) || "15".equalsIgnoreCase(planTypeCd) || "24".equalsIgnoreCase(planTypeCd)) {
					mapNodeName.put(ppnr.getExecPlanNodeId(), ppnr.getNodeName());
					Constants.viewPlanNodes16 = viewPlanNodes;
				}
				if (null != ppnr.getTreeParentNodeId()) {
					if ("5".equalsIgnoreCase(planTypeCd)) {
						// 专项计划
						ExecPlanNode tempppnr = execPlanNodeManager.getSameNode1(ppnr);
						viewPlanNodes.set(i, tempppnr);
						parentNodeMap.put(tempppnr.getExecPlanNodeId(), ppnr);
					} else {
						// 商业执行计划
						if ("16".equalsIgnoreCase(planTypeCd)) {
							if (null != ppnr.getParentId15() && !"".equalsIgnoreCase(ppnr.getParentId15())) {
								// 如果在五级开业中能找得到，就找五级开业
								ExecPlanNode tempppnr = execPlanNodeManager.getSameNode15(ppnr);
								viewPlanNodes.set(i, tempppnr);
								mapNodeName.put(tempppnr.getExecPlanNodeId(), ppnr.getNodeName());
							} else {
								// 寻找相同的四级节点，覆盖该对象，同时把原来的名字替换过去
								ExecPlanNode tempppnr = execPlanNodeManager.getSameNode14(ppnr);
								viewPlanNodes.set(i, tempppnr);
								mapNodeName.put(tempppnr.getExecPlanNodeId(), ppnr.getNodeName());
							}
						}
						ExecPlanNode tempppnr = execPlanNodeManager.getSameNode1(ppnr);
						ExecPlanNodeParentVo epnpv = new ExecPlanNodeParentVo();
						epnpv.setExecPlanNodeId(tempppnr.getExecPlanNodeId());
						epnpv.setNodeName(tempppnr.getNodeName());
						epnpv.setSequenceNo(tempppnr.getSequenceNo().intValue());
						if (null != execPlanLayouts && execPlanLayouts.size() > 0) {
							ExecPlanLayout epl = execPlanLayouts.get(0);
							ExecPlanDetail epd = detailManager.getPlanDetailPerNodeRelIdAndPlanCd(projectCd, tempppnr.getExecPlanNodeId(), epl
									.getExecPlanLayoutId());
							if (null != epd) {
								epnpv.setStartDate(getPrintDate(epd, "startDate"));
								epnpv.setEndDate(getPrintDate(epd, "endDate"));
								epnpv.setExecPlanDetailId(epd.getExecPlanDetailId());
							}
						}
						parentNodeVoMap.put(ppnr.getExecPlanNodeId(), epnpv);
						// 组装mapTips2
						try {
							ExecPlanDetail epd2 = detailManager.getEntity(epnpv.getExecPlanDetailId());
							List<ExecPlanDetailMess> epdms = epd2.getExecPlanDetailMesses();
							StringBuffer tip = new StringBuffer();
							DateFormat df = new SimpleDateFormat("MM-dd");
							if (epdms.size() > 0) {
								tip.append("当前状态："+this.getPrintStatusCn(epd2,true));
								tip.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
								int size = epdms.size() >= 3 ? 3 : epdms.size();
								for (int im = 0; im < size; im++) {
									ExecPlanDetailMess epdm = epdms.get(im);
									String userName = CodeNameUtil.getUserNameByCd(epdm.getCreator());
									tip.append("<li><strong>" + userName + "&nbsp;" + df.format(epdm.getCreatedDate()) + "</strong>："
											+ EncodeUtils.htmlEscape(epdm.getMessContent()) + "</li>");
								}
								tip.append("</ul>");
							}
								mapTips2.put(epd2.getExecPlanDetailId(), tip.toString());
							
						} catch (Exception e) {
						}
					}
				}
			}
		}

		if ("10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd) || "14".equalsIgnoreCase(planTypeCd) || "15".equalsIgnoreCase(planTypeCd)
				|| "24".equalsIgnoreCase(planTypeCd)|| "16".equalsIgnoreCase(planTypeCd)) {
			// 商业执行计划，还需要读取主力店和次主力店的签约情况
			nMainSigned = detailManager.getNumberSign(projectCd, "nMainSigned");
			nMainUnsign = detailManager.getNumberSign(projectCd, "nMainUnsign");
			nSubSigned = detailManager.getNumberSign(projectCd, "nSubSigned");
			nSubUnsign = detailManager.getNumberSign(projectCd, "nSubUnsign");
			nMainIn = detailManager.getNumberSign(projectCd, "nMainIn");
			nMainUnin = detailManager.getNumberSign(projectCd, "nMainUnin");
			nSubIn = detailManager.getNumberSign(projectCd, "nSubIn");
			nSubUnin = detailManager.getNumberSign(projectCd, "nSubUnin");

			// 设置节点名称后加上统计签约情况的数据
			if ("14".equalsIgnoreCase(planTypeCd)
					|| "15".equalsIgnoreCase(planTypeCd)
					|| "16".equalsIgnoreCase(planTypeCd)
					|| "24".equalsIgnoreCase(planTypeCd)) {
				Set<Map.Entry<String, String>> set = mapNodeName.entrySet();
				for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
					if ("2416".equalsIgnoreCase(entry.getKey())) {
						entry.setValue(entry.getValue() + "&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-weight:bolder;'>(<span class='color_green'>" + nMainSigned
								+ "</span>/<span class='color_red'>" + nMainUnsign + "</span>)</span>");
					} else if ("2421".equalsIgnoreCase(entry.getKey())) {
						entry.setValue(entry.getValue() + "&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-weight:bolder;'>(<span class='color_green'>" + nSubSigned
								+ "</span>/<span class='color_red'>" + nSubUnsign + "</span>)</span>");
					} else if ("2461".equalsIgnoreCase(entry.getKey())) {
						entry.setValue(entry.getValue() + "&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-weight:bolder;'>(<span class='color_green'>" + nMainIn
								+ "</span>/<span class='color_red'>" + nMainUnin + "</span>)</span>");
					} else if ("2462".equalsIgnoreCase(entry.getKey())) {
						entry.setValue(entry.getValue() + "&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-weight:bolder;'>(<span class='color_green'>" + nSubIn
								+ "</span>/<span class='color_red'>" + nSubUnin + "</span>)</span>");
					}
				}
			} else if ("10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd)) {
				// 商业计划汇总，从execPlanAllVoList中判断节点
				for (int i = 0; null != execPlanAllVoList && i < execPlanAllVoList.size(); i++) {
					ExecPlanAllVo epav = execPlanAllVoList.get(i);
					if (1639 == epav.getSequenceNoHide()) {
						epav.setNodeName14(epav.getNodeName14() + "&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-weight:bolder;'>(<span class='color_green'>"
								+ nMainSigned + "</span>/<span class='color_red'>" + nMainUnsign + "</span>)</span>");
						execPlanAllVoList.set(i, epav);
					} else if (1640 == epav.getSequenceNoHide()) {
						epav.setNodeName14(epav.getNodeName14() + "&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-weight:bolder;'>(<span class='color_green'>"
								+ nSubSigned + "</span>/<span class='color_red'>" + nSubUnsign + "</span>)</span>");
						execPlanAllVoList.set(i, epav);
					}
				}
			}
		}

		// 节点与业态映射关系
		String[] nodeIds;
		String[] nodeIdss = null;
		if ("10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd)) {
			// 商业的汇总的情况
			nodeIds = new String[execPlanAllVoList.size()];
			ExecPlanAllVo n = null;
			for (int i = 0; i < execPlanAllVoList.size(); i++) {
				n = execPlanAllVoList.get(i);
				nodeIds[i] = n.getExecPlanNodeId();
			}
		} else {
			nodeIds = new String[viewPlanNodes.size()];
			ExecPlanNode n = null;
			for (int i = 0; i < viewPlanNodes.size(); i++) {
				n = viewPlanNodes.get(i);
				nodeIds[i] = n.getExecPlanNodeId();
			}
			//
			// nodeIdss = new String[Constants.viewPlanNodes16.size()];
			// ExecPlanNode nn = null;
			// for (int i = 0; i < Constants.viewPlanNodes16.size(); i++) {
			// nn = Constants.viewPlanNodes16.get(i);
			// nodeIdss[i] = nn.getExecPlanNodeId();
			// }
			// planDetailMap16 = buildPlanDetailMap(nodeIdss, "", searchPlans,
			// filter_GED_scheduleStartDate, filter_LED_scheduleStartDate,
			// filter_GED_scheduleEndDate, filter_LED_scheduleEndDate,
			// filter_GED_realEndDate, filter_LED_realEndDate, search_status,
			// nowViewStyle, nowResOrgNames);
			// mapPrintStartDate116 = new HashMap<String, String>();
			// mapPrintEndDate116 = new HashMap<String, String>();
			// Set<Map.Entry<String, ExecPlanDetail>> sets =
			// planDetailMap16.entrySet();
			// for (Iterator<Map.Entry<String, ExecPlanDetail>> it =
			// sets.iterator(); it.hasNext();) {
			// try {
			// Map.Entry<String, ExecPlanDetail> entry = (Map.Entry<String,
			// ExecPlanDetail>) it.next();
			// ExecPlanDetail pepd = entry.getValue();
			// if (null != pepd) {
			// //生成显示的map
			// if("16".equalsIgnoreCase(planTypeCd)){
			// mapPrintStartDate116.put(entry.getKey(),
			// getPrintStartDateYMD(pepd));
			// mapPrintEndDate116.put(entry.getKey(), getPrintEndDateYMD(pepd));
			// }
			// }
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// }
		}
		planDetailMap = buildPlanDetailMap(nodeIds, "", searchPlans, filter_GED_scheduleStartDate, filter_LED_scheduleStartDate, filter_GED_scheduleEndDate,
				filter_LED_scheduleEndDate, filter_GED_realEndDate, filter_LED_realEndDate, search_status, nowViewStyle, nowResOrgNames, planTypeCd);

		// 整合成页面上的显示，放到map里传到页面上
		mapPrintHtml = new HashMap<String, String>();
		mapPrintStartDate = new HashMap<String, String>();
		mapPrintEndDate = new HashMap<String, String>();
		mapPrintStartDate1 = new HashMap<String, String>();
		mapPrintEndDate1 = new HashMap<String, String>();
		Set<Map.Entry<String, ExecPlanDetail>> set = planDetailMap.entrySet();
		for (Iterator<Map.Entry<String, ExecPlanDetail>> it = set.iterator(); it.hasNext();) {
			try {
				Map.Entry<String, ExecPlanDetail> entry = (Map.Entry<String, ExecPlanDetail>) it.next();
				ExecPlanDetail pepd = entry.getValue();
				if (null != pepd) {
					// 生成显示的map
					if ("10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd) || "14".equalsIgnoreCase(planTypeCd)
							|| "15".equalsIgnoreCase(planTypeCd) || "16".equalsIgnoreCase(planTypeCd) || "24".equalsIgnoreCase(planTypeCd)// add
																																			// by
																																			// dido(2011.12.13)
																																			// 解决新的商业四级计划
					) {
						mapPrintHtml.put(entry.getKey(), getPrintStatusCn(pepd,false));
						mapPrintStartDate.put(entry.getKey(), getPrintStartDate(pepd));
						mapPrintEndDate.put(entry.getKey(), getPrintEndDate(pepd));
						mapPrintStartDate1.put(entry.getKey(), getPrintStartDateYMD(pepd));
						mapPrintEndDate1.put(entry.getKey(), getPrintEndDateYMD(pepd));
					} else {
						mapPrintHtml.put(entry.getKey(), getPrintHtml(pepd));
					}
				}

				// 拼装组合tip
				List<ExecPlanDetailMess> epdms = pepd.getExecPlanDetailMesses();
				StringBuffer tip = new StringBuffer();
				DateFormat df = new SimpleDateFormat("MM-dd");
				if (epdms.size() > 0) {
					tip.append("当前状态："+this.getPrintStatusCn(pepd,true));
					tip.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
					int size = epdms.size() >= 3 ? 3 : epdms.size();
					for (int i = 0; i < size; i++) {
						ExecPlanDetailMess epdm = epdms.get(i);
						String userName = CodeNameUtil.getUserNameByCd(epdm.getCreator());
						tip.append("<li><strong>" + userName + "&nbsp;" + df.format(epdm.getCreatedDate()) + "</strong>："
								+ EncodeUtils.htmlEscape(epdm.getMessContent()) + "</li>");
					}
					tip.append("</ul>");
				}
				mapTips.put(pepd.getExecPlanDetailId(), tip.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("1".equalsIgnoreCase(nowViewStyle)) {
			// 如果是精简模式，去掉没有detail的节点
			if (!"10".equalsIgnoreCase(planTypeCd) && !"11".equalsIgnoreCase(planTypeCd)) {
				// 如果不是商业的汇总界面，从viewPlanNodes取数判断
				boolean if_has = false;
				for (int i = 0; null != viewPlanNodes && i < viewPlanNodes.size(); i++) {
					ExecPlanNode prel = (ExecPlanNode) viewPlanNodes.get(i);
					if_has = false;
					for (Iterator<Map.Entry<String, ExecPlanDetail>> it = set.iterator(); it.hasNext();) {
						Map.Entry<String, ExecPlanDetail> entry = (Map.Entry<String, ExecPlanDetail>) it.next();
						ExecPlanDetail pepd = entry.getValue();
						if (null != pepd && pepd.getExecPlanNode().getExecPlanNodeId().equals(prel.getExecPlanNodeId())) {
							if_has = true;
							break;
						}
					}
					if (!if_has) {
						viewPlanNodes.remove(i);
						i--;
					}
				}
			} else {
				// 如果是商业的汇总界面，从execPlanAllVoList取数判断,(不用)
				boolean if_has = false;
				for (int i = 0; null != execPlanAllVoList && i < execPlanAllVoList.size(); i++) {
					ExecPlanAllVo epav = (ExecPlanAllVo) execPlanAllVoList.get(i);
					if_has = false;
					for (Iterator<Map.Entry<String, ExecPlanDetail>> it = set.iterator(); it.hasNext();) {
						Map.Entry<String, ExecPlanDetail> entry = (Map.Entry<String, ExecPlanDetail>) it.next();
						ExecPlanDetail pepd = entry.getValue();
						if (null != pepd && pepd.getExecPlanNode().getExecPlanNodeId().equals(epav.getExecPlanNodeId())) {
							if_has = true;
							break;
						}
					}
					if (!if_has) {
						execPlanAllVoList.remove(i);
						i--;
					}
				}
			}
		}
		//
		Constants.searchedPlansNew = searchedPlans;
		if ("16".equalsIgnoreCase(planTypeCd)) {
			Constants.mapNodeNameNew = mapNodeName16;
		} else {
			Constants.mapNodeNameNew = mapNodeName;
		}

		Constants.planDetailMapNew = planDetailMap;
		Constants.mapPrintStartDateNew = mapPrintStartDate1;
		Constants.mapPrintEndDateNew = mapPrintEndDate1;
		Constants.parentNodeVoMapNew = parentNodeVoMap;
		Constants.mapPrintStartDate116 = mapPrintStartDate;
		Constants.mapPrintEndDate116 = mapPrintEndDate;
		//
		if (if_bis) {
			if ("10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd))
				return "matrixBisAll";
			else
				return "matrixBis";
		} else
			return "matrix";
	}

	/**
	 * 遍历记录列表构造前台展现数据
	 * 
	 * @param oaMeetings
	 */
	private void buildAttributes(List<ExecPlanDetail> execPlanDetails) {
		String id = null;
		for (int j = 0; execPlanDetails != null && j < execPlanDetails.size(); j++) {
			ExecPlanDetail epd = execPlanDetails.get(j);
			// 鼠标放在标题上时，显示3条评论
			StringBuffer tip = new StringBuffer();
			List<ExecPlanDetailMess> epdms = epd.getExecPlanDetailMesses();
			DateFormat df = new SimpleDateFormat("MM-dd");
			if (epdms.size() > 0) {
				int size = epdms.size() >= 3 ? 3 : epdms.size();
				for (int i = 0; i < size; i++) {
					ExecPlanDetailMess epdm = epdms.get(i);
					String userName = CodeNameUtil.getUserNameByCd(epdm.getCreator());
					tip.append("<li><strong>" + userName + "&nbsp;" + df.format(epdm.getCreatedDate()) + "</strong>："
							+ EncodeUtils.htmlEscape(epdm.getMessContent()) + "</li>");
				}
				tip.append("</ul>");
			}
			tipMap.put(id, tip.toString());
		}
	}

	/**
	 * 功能: 控制计划详情输入
	 * 
	 * @param planDetailId
	 * @param nodeId
	 * @param layoutId
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
			planDetailEntity = detailManager.getExecPlanDetail(projectCd, nodeId, layoutId);
			if (null == planDetailEntity) {
				planDetailEntity = new ExecPlanDetail();
				planDetailEntity.setProjectCd(projectCd);
				planDetailTempId = RandomUtils.generateTmpEntityId();
				outputBizEntityId = planDetailTempId;
			} else {
				planDetailId = planDetailEntity.getExecPlanDetailId();
				outputBizEntityId = planDetailEntity.getExecPlanDetailId();
			}
		} else {
			planDetailEntity = detailManager.getEntity(planDetailId);
			outputBizEntityId = planDetailEntity.getExecPlanDetailId();
		}

		String nodeName = "";
		ExecPlanNode nodeRel = planDetailEntity.getExecPlanNode();
		if (null == nodeRel) {
			nodeRel = execPlanNodeManager.getEntity(nodeId);
			if (null == nodeRel) {
				nodeRel = new ExecPlanNode();
			}
		}
		// 增加输出成果提示
		outFileTile = nodeRel.getOutputFiles();
		nodeName = nodeRel.getNodeName();
		// String planName =
		// PlasCache.getOrgByCd(planDetailEntity.getProjectCd()).getOrgName();
		if (null == layoutId) {
			layoutId = planDetailEntity.getExecPlanLayout().getExecPlanLayoutId();
		}
		String planName = execPlanLayoutManager.getLayoutNameById(layoutId);
		planDetailCaption = planName + "--" + nodeName;
		if (null != nodeRel && null != nodeRel.getResOrgName() && !"".equalsIgnoreCase(nodeRel.getResOrgName())) {
			planDetailCaption += "--" + nodeRel.getResOrgName();
		}
		planDetailStatusMap = detailManager.buildPlanDetailStatusMap();
		planDetailOutput = attachManager.getAttachFilesByEntityIdAndModuleCd(outputBizEntityId, "execPlan");

		projectCd = planDetailEntity.getProjectCd();
		projectName = planName;
		resOrgCd = nodeRel.getResOrgCd();
		buildPermission(projectCd, planDetailId);

		// 判断该节点是否被当前用户‘关注’
		String currenUser = SpringSecurityUtils.getCurrentUiid();// 获取当前用户
		aAttention = detailManager.confirmAttention(planDetailId, currenUser);
		// 被关注的节点执行如下
		if (aAttention) {
			OaAllAttention attention = detailManager.getOaAllAttention("execPlan", planDetailId, currenUser);
			if (attention != null) {
				nRecordVersion = (int) attention.getModuleRecordVersion();
			}
			int remarkVersion = detailManager.getAttentionRemarkVersion(planDetailId);
			if (nRecordVersion < remarkVersion) {
				detailManager.updateAttention(planDetailId, "execPlan", remarkVersion);
			}
		}
		// 搜索推送中心计划任务信息
		planWorkCenterMaps = planWorkCenterManager.getPlanWorkCenterMapByExec(planDetailId, "2");
		Struts2Utils.getRequest().setAttribute("nodeName", nodeName);
		Struts2Utils.getRequest().setAttribute("planName", planName);

		// 读取附加表，并判断状态
		listExecPlanDetailPlus = detailManager.getListExecPlanDetailPlus(planDetailId);
		if (null != listExecPlanDetailPlus) {
			for (ExecPlanDetailPlus epdp : listExecPlanDetailPlus) {
				if (null != epdp.getScheduleEndDate()) {
					if (DateOperator.getDateNow().after(epdp.getScheduleEndDate())) {
						epdp.setRemark("suspend");
					} else {
						epdp.setRemark("going");
					}
				}
			}
		}

		if ("24".equalsIgnoreCase(planTypeCd)) {
			if_bis = true;
		}
		return "detailInfo";
	}

	/**
	 * 功能: 保存控制计划信息
	 * 
	 * @param planDetailId
	 * @param scheduleEndDate
	 * @param realEndDate
	 * @param planDetailTempId
	 * @param nodeId
	 * @param layoutId
	 * @param planDetailStatus
	 * @return
	 * @throws Exception
	 */
	public String savePlanDetail() throws Exception {
		ExecPlanDetail detail = null;
		if (StringUtils.isBlank(planDetailId)) {
			if (StringUtils.isNotBlank(scheduleStartDate) || StringUtils.isNotBlank(scheduleStartDate) || StringUtils.isNotBlank(scheduleEndDate)
					|| StringUtils.isNotBlank(realEndDate) || !activeBl) {
				detail = detailManager.addPlanDetailInfo(scheduleStartDate, scheduleEndDate, realEndDate, planDetailTempId, nodeId, layoutId, activeBl,
						projectCd);
			}
		} else {
			detail = detailManager.getEntity(planDetailId);
			// 若清空计划完成时间，并状态为未开始，则删除该record
			if ("".equals(scheduleEndDate) && "0".equals(detail.getStatus())) {
				try {
					execPlanDetailMessManager.delete(detail.getExecPlanDetailMesses());
				} catch (Exception e) {
				}
				detailManager.delete(detail);
				Struts2Utils.renderText("clear");
			} else {
				detailManager.svPlanDetailInfo(scheduleStartDate, scheduleEndDate, realEndDate, planDetailId, nodeId, layoutId, planDetailStatus, activeBl);
			}
		}

		if (null != detail) {
			String newMessage = Struts2Utils.getParameter("newMessage");
			if (StringUtils.isNotBlank(newMessage)) {
				ExecPlanDetailMess pm = new ExecPlanDetailMess();
				pm.setMessContent(newMessage);
				pm.setSysType("0");
				pm.setExecPlanDetail(detail);
				execPlanDetailMessManager.saveExecPlanDetailMess(pm);
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
			ExecPlanDetail execPlanDetail = detailManager.getEntity(planDetailId);
			ExecPlanDetailMess pm = new ExecPlanDetailMess();
			pm.setMessContent(Struts2Utils.getParameter("newMessage"));
			pm.setSysType("0");
			pm.setExecPlanDetail(execPlanDetail);
			execPlanDetailMessManager.saveExecPlanDetailMess(pm);
			detailManager.saveExecPlanDetail(execPlanDetail);

			StringBuffer strb = new StringBuffer("");
			if (execPlanDetail.getExecPlanDetailMesses() != null && execPlanDetail.getExecPlanDetailMesses().size() > 0) {
				for (int i = 0; i < execPlanDetail.getExecPlanDetailMesses().size(); i++) {
					ExecPlanDetailMess mes = execPlanDetail.getExecPlanDetailMesses().get(i);
					String insertStr = "";
					if (null != mes.getSysType() && "1".equalsIgnoreCase(mes.getSysType())) {
						insertStr = " color_black";
					}
					String now = DateOperator.formatDate(mes.getCreatedDate(), "MM-dd HH:mm");
					strb.append("<div class='divMessContent"+ mes.getSysType() +" detail_message_div" + insertStr + "'><pre>" + CodeNameUtil.getUserNameByCd(mes.getCreator()));
					strb.append("(" + now + "):");
					strb.append(mes.getMessContent());
					strb.append("</pre></div>");
				}
			}
			Struts2Utils.renderText(strb.toString());
			// 主要为关注模块，节点发生变化时，往关注库做标志 start (2011-11-22)
			detailManager.setAttentionChange("execPlan", planDetailId);
			// end (2011-11-22)
		} else {
			Struts2Utils.renderText("no");
		}
		return null;
	}

	/**
	 * 审核确认计划详情信息，只有超级管理员有这个权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String confirmPlanInfo() throws Exception {
		String confirmFlg = Struts2Utils.getRequest().getParameter("confirmFlg");
		if (detailManager.confirmPlanDetailInfo(planDetailId, confirmFlg)) {
			// 主要为关注模块，节点发生变化时，往关注库做标志 start (2011-11-22)
			detailManager.setAttentionChange("execPlan", planDetailId);
			// end (2011-11-22)
			Struts2Utils.renderText("succ");
		}
		return null;
	}

	/**
	 * 功能: 批量确认计划详情信息，只有管理员才有此权限
	 * 
	 * @param projectCd
	 * @param planTypeCd
	 * @return
	 * @throws Exception
	 */
	public String bachConfirmPlanInfo() throws Exception {
		if (detailManager.batchConfirmPlanDetailInfo(projectCd, planTypeCd)) {
			Struts2Utils.renderText("succ");
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
			// 主要为关注模块，节点发生变化时，往关注库做标志 start (2011-11-22)
			detailManager.setAttentionChange("execPlan", planDetailId);
			// end (2011-11-22)
			Struts2Utils.renderText("succ");
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
		try {
			ExecPlanDetail planDetail = null;
			planDetail = detailManager.getEntity(outputBizEntityId);
			planTypeCd = planDetail.getExecPlanNode().getPlanTypeCd();
		} catch (Exception e) {
		}
		buildPermission(projectCd, null);
		return "outputList";
	}

	/**
	 * 功能: 获取计划信息
	 * 
	 * @param planDetailId
	 * @param nodeId
	 * @param layoutId
	 * @return
	 * @throws Exception
	 */
	public String fetchPlanDetailInfo() throws Exception {
		ExecPlanDetail planDetail = null;
		try {
			if (StringUtils.isBlank(planDetailId)) {
				planDetail = detailManager.getPlanDetailPerNodeRelIdAndPlanCd(projectCd, nodeId, layoutId);
			} else {
				planDetail = detailManager.getEntity(planDetailId);
			}
		} catch (Exception e) {
		}
		if (null != planDetail) {
			try {
				if ("10".equalsIgnoreCase(planDetail.getExecPlanNode().getPlanTypeCd()) || "14".equalsIgnoreCase(planDetail.getExecPlanNode().getPlanTypeCd())
						|| "15".equalsIgnoreCase(planDetail.getExecPlanNode().getPlanTypeCd())
						|| "16".equalsIgnoreCase(planDetail.getExecPlanNode().getPlanTypeCd())
						|| "24".equalsIgnoreCase(planDetail.getExecPlanNode().getPlanTypeCd())) {
					Struts2Utils.renderText(getPrintStatusCn(planDetail,false) + "*" + planDetail.getExecPlanDetailId() + "*"
							+ DateOperator.formatDate(planDetail.getScheduleStartDate(), "yy-MM-dd") + "*"
							+ DateOperator.formatDate(planDetail.getScheduleEndDate(), "yy-MM-dd") + "*"
							+ planDetail.getAttachFlg());
					// 主要为关注模块，节点发生变化时，往关注库做标志 start (2011-11-22)
					detailManager.setAttentionChange("execPlan", planDetailId);
					// end (2011-11-22)
				} else {
					Struts2Utils.renderText(getPrintHtml(planDetail) + "*" + planDetail.getExecPlanDetailId());
					// 主要为关注模块，节点发生变化时，往关注库做标志 start (2011-11-22)
					detailManager.setAttentionChange("execPlan", planDetailId);
					// end (2011-11-22)
				}
			} catch (Exception e) {
				Struts2Utils.renderText("failure");
			}
		} else {
			Struts2Utils.renderText("failure");
		}

		return null;
	}

	// 功能: 删除detail
	public String deletePlanExecPlanDetail() {
		if (StringUtils.isNotBlank(planDetailId)) {
			detailManager.deleteExecPlanDetail(planDetailId);
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
	 * 功能: 构造计划详情
	 * 
	 * @param projNodes
	 * @param pTreeTypeCd
	 * 
	 * @return
	 */
	private Map<String, ExecPlanDetail> buildPlanDetailMap(String[] nodeIds, String pTreeTypeCd, String searchLayouts, String filter_GED_scheduleStartDate,
			String filter_LED_scheduleStartDate, String filter_GED_scheduleEndDate, String filter_LED_scheduleEndDate, String filter_GED_realEndDate,
			String filter_LED_realEndDate, String search_status, String nowViewStyle, String nowResOrgNames, String planTypeCd) {
		Map<String, ExecPlanDetail> m = new HashMap<String, ExecPlanDetail>();

		List<ExecPlanDetail> details = detailManager.getDetailsByProjPlanNodes(nodeIds, pTreeTypeCd, searchLayouts, filter_GED_scheduleStartDate,
				filter_LED_scheduleStartDate, filter_GED_scheduleEndDate, filter_LED_scheduleEndDate, filter_GED_realEndDate, filter_LED_realEndDate,
				search_status, nowViewStyle, nowResOrgNames, planTypeCd);
		String key = null;
		for (ExecPlanDetail d : details) {
			key = d.getExecPlanNode().getExecPlanNodeId() + "_" + d.getExecPlanLayout().getExecPlanLayoutId();

			// 如果是相同key，就不进入结果的map
			boolean has_same = false;
			try {
				Set<Map.Entry<String, ExecPlanDetail>> set = m.entrySet();
				for (Iterator<Map.Entry<String, ExecPlanDetail>> it = set.iterator(); it.hasNext();) {
					Map.Entry<String, ExecPlanDetail> entry = (Map.Entry<String, ExecPlanDetail>) it.next();
					String compareStr = entry.getKey();
					if (key.equalsIgnoreCase(compareStr)) {
						has_same = true;
						break;
					}
				}
			} catch (Exception e) {
			}
			if (!has_same) {
				m.put(key, d);
			}
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
		// 项目三级计划
		if (SpringSecurityUtils.hasRole("A_EXEC_HQ") || SpringSecurityUtils.hasRole("A_EXEC_AREA")) {
			// 普通管理员，能修改业态
			aExecAdmin = true;
			aAddPoint = true;
		}
		if (StringUtils.isBlank(planDetailId)) {
			if (SpringSecurityUtils.hasRole("A_EXEC_AREA")) {
				// 列表中能新增节点，如果是区域判断是否当前区域
				//aAddPoint = PlasCache.ifMyAreaByProjectCd(projCd, SpringSecurityUtils.getCurrentAcctId(), "", "ORG_AREA");
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
				//项目公司可写、上传附件
				aWritePoint=true;
			}
		}
		if (SpringSecurityUtils.hasRole("A_EXEC_BIS_ADMIN")) {
			aExecAdmin = true;
			aExecSuperAdmin = true;
			aAddPoint = true;
			aConfirmPoint = true;
			aWritePoint = true;
			aCompletePoint = true;
			aChangeSchedule = true;
			aShowStandard = true;
			aAddFlg = true;
		}
		if (SpringSecurityUtils.hasRole("A_EXEC_SUPER_ADMIN")) {
			// 超级管理员
			aExecSuperAdmin = true;
			aExecAdmin = true;
			aAddPoint = true;
			aConfirmPoint = true;
			aWritePoint = true;
			aChangeSchedule = true;
			aCompletePoint = true;
		}

		if (StringUtils.isNotBlank(planDetailId)) {
			// 如果输入的detailId不为空，即进入查看明细页面
			ExecPlanDetail pepd = detailManager.getEntity(planDetailId);
			if (SpringSecurityUtils.hasRole("A_EXEC_AREA")) {
				aAddPoint = true;
				aWritePoint = true;
			}
			if (SpringSecurityUtils.hasRole("A_EXEC_PROJ")) {
				// 判断是否能新增，即可以修改计划开始时间和计划完成时间
				if (null != pepd && "1".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
					aAddPoint = false;
				} else {
					aAddPoint = true;
				}
			}
			if (SpringSecurityUtils.hasRole("A_EXEC_AREA")) {
				// 如果是区域操作员，判断当前项目是否属于他的区域
				if (null != pepd && !"1".equalsIgnoreCase(pepd.getInfoConfirmedFlg()) && "0".equalsIgnoreCase(pepd.getStatus())) {
					String project_cd = pepd.getProjectCd();
					aConfirmPoint = PlasCache.ifMyAreaByProjectCd(project_cd, SpringSecurityUtils.getCurrentAcctId(), "", "ORG_AREA");
				}
			}

			if (null != pepd && ("0".equalsIgnoreCase(pepd.getStatus()) || "1".equalsIgnoreCase(pepd.getStatus()) || "3".equalsIgnoreCase(pepd.getStatus()))
					&& "1".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
				// 进行中节点
				if (SpringSecurityUtils.hasRole("A_EXEC_PROJ")) {
					aWritePoint = true;
				}
				if ("地产公司".equalsIgnoreCase(pepd.getExecPlanNode().getResOrgName())) {
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
					if (SpringSecurityUtils.hasRole("A_EXEC_CENTER") || SpringSecurityUtils.hasRole("A_EXEC_BIS_CENTER")
							|| SpringSecurityUtils.hasRole("A_EXEC_BIS_PROJECT")) {
						aWritePoint = true;
						aAddFlg = true;
						/*
						 * String[] compareOrgCds =
						 * pepd.getExecPlanNode().getResOrgCd().split(";");
						 * 
						 * String tempPosOrgCd = null; String compareOrgCd =
						 * null; List<WsPlasOrg> userOrgs = null;
						 * 
						 * for (int j = 0; null != compareOrgCds && j <
						 * compareOrgCds.length; j++) { compareOrgCd =
						 * compareOrgCds[j]; userOrgs =
						 * PlasCache.getRelPosBubbleOrgCdList
						 * (SpringSecurityUtils.getCurrentAcctId(), ""); for
						 * (int i = 0; null != userOrgs && i < userOrgs.size();
						 * i++) { tempPosOrgCd = (userOrgs.get(i)).getOrgCd();
						 * if (compareOrgCd.equals(tempPosOrgCd)) { aWritePoint
						 * = true; break; } } }
						 */
					}
				}
			}

			// 总部审核一级节点，区域审核二级节点
			if (null != pepd) {
				if (null != pepd.getExecPlanNode().getPointLevel() && 1 == pepd.getExecPlanNode().getPointLevel()) {
					if (SpringSecurityUtils.hasRole("A_EXEC_HQ")) {
						aCompletePoint = true;
					}
					if (SpringSecurityUtils.hasRole("A_EXEC_ADMIN_HQ")) {
						aChangeSchedule = true;
					}
				} else {
					if (SpringSecurityUtils.hasRole("A_EXEC_AREA")) {
						// String project_cd =
						// pepd.getPlanProjectNodeRel().getPlanExecutionPlanMain().getProjectCd();
						// aCompletePoint =
						// PlasCache.ifMyAreaByProjectCd(project_cd,
						// SpringSecurityUtils.getCurrentAcctId(), "",
						// "ORG_AREA");
						aCompletePoint = true;
					}
					if (SpringSecurityUtils.hasRole("A_EXEC_ADMIN_AREA")) {
						aChangeSchedule = true;
						aCompletePoint = true;
						// String project_cd =
						// pepd.getPlanProjectNodeRel().getPlanExecutionPlanMain().getProjectCd();
						// aChangeSchedule =
						// PlasCache.ifMyAreaByProjectCd(project_cd,
						// SpringSecurityUtils.getCurrentAcctId(), "",
						// "ORG_AREA");
					}
				}
				if (SpringSecurityUtils.hasRole("A_EXEC_PROJ")) {
					//临时，如果是项目，能修改三级节点
					if(null != pepd.getExecPlanNode().getPointLevel() && 3 == pepd.getExecPlanNode().getPointLevel()){
						aChangeSchedule = true;
					}
				}
				if("24".equalsIgnoreCase(pepd.getExecPlanNode().getPlanTypeCd())){
					if (!SpringSecurityUtils.hasRole("A_EXEC_BIS_ADMIN")) {
						// 如果是商业执行计划的非管理员用户，进入附件只限上传者下载模式
						aOnlyCreator = true;
						myUiid = SpringSecurityUtils.getCurrentUiid();
					} else {
						aOnlyCreator = false;
					}
					/*if("shisn".equalsIgnoreCase(SpringSecurityUtils.getCurrentUiid())){
						aCompletePoint = true;
					}else{
						aCompletePoint = false;
					}*/
				}
			}
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
		//playTypdCd目前只有两个值可用，1:项目开发计划;  24:商业四级计划
		WsPlasOrg org = null;
		//mapProjectsType = new TreeMap<String, String>();
		List<WsPlasOrg> allProjs = new ArrayList<WsPlasOrg>();
		if ("10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd) || "14".equalsIgnoreCase(planTypeCd) || "15".equalsIgnoreCase(planTypeCd)
				|| "16".equalsIgnoreCase(planTypeCd) || "24".equalsIgnoreCase(planTypeCd)) {
			if ("24".equalsIgnoreCase(planTypeCd)) {
				if (!mapProjectsType.containsKey("0000")) {
					mapProjectsType.put("0000", "请选择项目");
				}
			}
			List<WsAppDictData> listDict = PlasCache.getDictDataList("LAND_BIS_REL");
			for (int i = 0; null != listDict && i < listDict.size(); i++) {
				WsAppDictData dict = listDict.get(i);
				org = new WsPlasOrg();
				org.setOrgName(dict.getDictName());
				org.setOrgCd(dict.getRemark());
				if (!GlobalConstants.WYS_ORG_CD.equals(dict.getRemark())) {
					mapProjectsType.put(dict.getRemark(), dict.getDictName());
					if (SpringSecurityUtils.getCurrentCenterCd().equals(dict.getDictCd())) {
						projectCd = dict.getRemark();
					}
				}
				allProjs.add(org);
			}
		} else {
			if ("1".equalsIgnoreCase(planTypeCd)) {
				if (!mapProjectsType.containsKey("0000")) {
					mapProjectsType.put("0000", "请选择项目");
				}
			}
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
		}

		return allProjs;
	}

	/*
	 * 获取单个节点的显示的状态
	 */
	public String getPrintStatus(ExecPlanDetail pepd) {
		String returnStr = "";
		if (null == pepd || new ExecPlanDetail() == pepd || null == pepd.getStatus())
			return "";
		if (!pepd.getActiveBl()) {
			returnStr = "noActive";
		} else if ("0".equalsIgnoreCase(pepd.getStatus()) && "0".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			returnStr = "noConfirm";
		} else if (("0".equalsIgnoreCase(pepd.getStatus()) || "1".equalsIgnoreCase(pepd.getStatus())) && "1".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			if (DateOperator.getDateNow().after(pepd.getScheduleEndDate())) {
				returnStr = "suspend";
			} else {
				returnStr = "going";
			}
		} else if ("2".equalsIgnoreCase(pepd.getStatus())) {
			if (null != pepd.getRealEndDate() && null != pepd.getScheduleEndDate() && pepd.getRealEndDate().after(pepd.getScheduleEndDate())) {
				returnStr = "completeSuspend";
			} else {
				returnStr = "complete";
			}
		} else if ("3".equalsIgnoreCase(pepd.getStatus())) {
			returnStr = "preComplete";
		}
		return returnStr;
	}

	/*
	 * 获取单个节点的显示的状态
	 */
	public String getPrintStatusCn(ExecPlanDetail pepd, boolean if_fortip) {
		String returnStr = "";
		try{
		if (null == pepd || new ExecPlanDetail() == pepd)
			return "";
		if (!pepd.getActiveBl()) {
			if(!if_fortip){
				returnStr = "<span class='color_black'>未启用</span>";
			}else{
				returnStr = "未启用";
			}
		} else if ("0".equalsIgnoreCase(pepd.getStatus()) && "0".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			if(!if_fortip){
				returnStr = "<img src='../resources/images/common/status_unconfirm.gif' title='未确认'>";
			}else{
				returnStr = "未确认";
			}
		} else if (("0".equalsIgnoreCase(pepd.getStatus()) || "1".equalsIgnoreCase(pepd.getStatus())) && "1".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			if (null!=pepd.getScheduleEndDate() && DateOperator.getDateNow().after(pepd.getScheduleEndDate())) {
				if(!if_fortip){
					returnStr = "<img src='../resources/images/common/status_suspend.gif' title='已过期'>";
				}else{
					returnStr = "过期";
				}
			} else {
				if(!if_fortip){
					returnStr = "<img src='../resources/images/common/status_confirm.gif' title='进行中'>";
				}else{
					returnStr = "进行中";
				}
			}
		} else if ("2".equalsIgnoreCase(pepd.getStatus())) {
			if (pepd.getRealEndDate() != null && pepd.getRealEndDate().after(pepd.getScheduleEndDate())) {
				if(!if_fortip){
					returnStr = "<img src='../resources/images/common/status_completeDely.gif' title='完成但曾过期'>";
				}else{
					returnStr = "已完成(曾过期)";
				}
			} else {
				if(!if_fortip){
					returnStr = "<img src='../resources/images/common/status_finish.gif' title='已完成'>";
				}else{
					returnStr = "已完成";
				}
			}
		} else if ("3".equalsIgnoreCase(pepd.getStatus())) {
			if(!if_fortip){
				returnStr = "<img src='../resources/images/common/status_prefinish.gif' title='预完成'>";
			}else{
				returnStr = "预完成";
			}
		}
		if("1".equalsIgnoreCase(pepd.getAttachFlg())){
			if(!if_fortip){
				returnStr += "<img src='../resources/images/common/atta_y.gif'/>";
			}
		}
		}catch(Exception e){}
		/*
		 * //判断该节点是否被当前用户关注 start //判断该节点是否被当前用户‘关注’ String currenUser =
		 * SpringSecurityUtils.getCurrentUiid();//获取当前用户 aAttention =
		 * detailManager.confirmAttention(pepd.getExecPlanDetailId(),
		 * currenUser); //被关注的节点执行如下 String remarkStr = ""; if(aAttention){
		 * OaAllAttention attention =
		 * detailManager.getOaAllAttention("execPlan",
		 * pepd.getExecPlanDetailId(),currenUser); if(attention!=null){
		 * nRecordVersion = (int) attention.getModuleRecordVersion(); } int
		 * remarkVersion =
		 * detailManager.getAttentionRemarkVersion(pepd.getExecPlanDetailId());
		 * if(nRecordVersion < remarkVersion){ remarkStr =
		 * "<img id='attention_"+pepd.getExecPlanDetailId()+
		 * "' src='http://localhost:8080/PowerDesk/resources/images/common/new.gif' style='cursor:pointer;'/>"
		 * ; } }
		 */
		return returnStr;
	}
	

	/*
	 * 获取单个节点的显示的HTML
	 */
	public String getPrintHtml(ExecPlanDetail pepd) {
		Date myDate = DateOperator.getDateNow();
		int myYear = DateOperator.getYear(myDate);
		String printStatus = getPrintStatus(pepd);
		String returnStr = "";
		if ("noActive".equalsIgnoreCase(printStatus)) {
			returnStr = "<span title='此节点关闭'>/</span>";
		} else if ("going".equalsIgnoreCase(printStatus) || ("preComplete".equalsIgnoreCase(printStatus))) {
			if (null != pepd.getScheduleStartDate()) {
				returnStr = "<span class='color_blue' title='进行中'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "~"
						+ getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			} else {
				returnStr = "<span class='color_blue' title='进行中'>" + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			}
		} else if ("suspend".equalsIgnoreCase(printStatus)) {
			if (null != pepd.getScheduleStartDate()) {
				returnStr = "<span class='color_red' title='过期'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "~"
						+ getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			} else {
				returnStr = "<span class='color_red' title='过期'>" + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			}
		} else if ("noConfirm".equalsIgnoreCase(printStatus)) {
			if (null != pepd.getScheduleStartDate()) {
				returnStr = "<span class='color_red' title='未确认'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "~"
						+ getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			} else {
				returnStr = "<span class='color_red' title='未确认'>" + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			}
		} else if ("complete".equalsIgnoreCase(printStatus)) {
			returnStr = "<span class='color_green' title='完成'>" + getDateStrJudgeYear(myYear, pepd.getRealEndDate()) + "</span>";
		} else if ("completeSuspend".equalsIgnoreCase(printStatus)) {
			returnStr = "<span class='color_dark_green' title='完成(曾过期)'>" + getDateStrJudgeYear(myYear, pepd.getRealEndDate()) + "(<span class='color_red'>"
					+ getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>)</span>";
		} else if ("preComplete".equalsIgnoreCase(printStatus)) {
			returnStr = "<span class='color_yellow' title='预完成'>" + getDateStrJudgeYear(myYear, pepd.getRealEndDate()) + "</span>";
		}
		// 判断该节点是否被当前用户关注 start
		// 判断该节点是否被当前用户‘关注’
		String currenUser = SpringSecurityUtils.getCurrentUiid();// 获取当前用户
		aAttention = detailManager.confirmAttention(pepd.getExecPlanDetailId(), currenUser);
		// 被关注的节点执行如下
		String remarkStr = "";
		if (aAttention) {
			OaAllAttention attention = detailManager.getOaAllAttention("execPlan", pepd.getExecPlanDetailId(), currenUser);
			if (attention != null) {
				nRecordVersion = (int) attention.getModuleRecordVersion();
			}
			int remarkVersion = detailManager.getAttentionRemarkVersion(pepd.getExecPlanDetailId());
			if (nRecordVersion < remarkVersion) {
				remarkStr = "<img id='attention_" + pepd.getExecPlanDetailId()
						+ "' src='http://localhost:8080/PowerDesk/resources/images/common/new.gif' style='cursor:pointer;'/>";
			}
		}
		// end
		return returnStr + remarkStr;
	}

	/*
	 * 获取单个节点的显示的开始时间
	 */
	public String getPrintStartDate(ExecPlanDetail pepd) {
		Date myDate = DateOperator.getDateNow();
		int myYear = DateOperator.getYear(myDate);
		String returnStr = "";
		if (null == pepd || new ExecPlanDetail() == pepd)
			return "";
		if (!pepd.getActiveBl()) {
			returnStr = "<span class='color_black'>/</span>";
		} else if ("0".equalsIgnoreCase(pepd.getStatus()) && "0".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			returnStr = "<span class='color_red'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "</span>";
		} else if (("0".equalsIgnoreCase(pepd.getStatus()) || "1".equalsIgnoreCase(pepd.getStatus())) && "1".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			if (DateOperator.getDateNow().after(pepd.getScheduleEndDate())) {
				returnStr = "<span class='color_red'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "</span>";
			} else {
				returnStr = "<span class='color_blue'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "</span>";
			}
		} else if ("2".equalsIgnoreCase(pepd.getStatus())) {
			if (pepd.getRealEndDate().after(pepd.getScheduleEndDate())) {
				returnStr = "<span class='color_dark_green'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "</span>";
			} else {
				returnStr = "<span class='color_green'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "</span>";
			}
		} else if ("3".equalsIgnoreCase(pepd.getStatus())) {
			returnStr = "<span class='color_yellow'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "</span>";
		}
		return returnStr;
	}

	/*
	 * 获取单个节点的显示的开始时间
	 */
	public String getPrintStartDateYMD(ExecPlanDetail pepd) {
		String returnStr = "";
		if (null == pepd || new ExecPlanDetail() == pepd)
			return "";
		if (!pepd.getActiveBl()) {
			returnStr = "<span class='color_black'>/</span>";
		} else if ("0".equalsIgnoreCase(pepd.getStatus()) && "0".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			returnStr = "<span class='color_red'>" + getDateStrJudgeYearMmDd(pepd.getScheduleStartDate()) + "</span>";
		} else if (("0".equalsIgnoreCase(pepd.getStatus()) || "1".equalsIgnoreCase(pepd.getStatus())) && "1".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			if (DateOperator.getDateNow().after(pepd.getScheduleEndDate())) {
				returnStr = "<span class='color_red'>" + getDateStrJudgeYearMmDd(pepd.getScheduleStartDate()) + "</span>";
			} else {
				returnStr = "<span class='color_blue'>" + getDateStrJudgeYearMmDd(pepd.getScheduleStartDate()) + "</span>";
			}
		} else if ("2".equalsIgnoreCase(pepd.getStatus())) {
			if (pepd.getRealEndDate().after(pepd.getScheduleEndDate())) {
				returnStr = "<span class='color_dark_green'>" + getDateStrJudgeYearMmDd(pepd.getScheduleStartDate()) + "</span>";
			} else {
				returnStr = "<span class='color_green'>" + getDateStrJudgeYearMmDd(pepd.getScheduleStartDate()) + "</span>";
			}
		} else if ("3".equalsIgnoreCase(pepd.getStatus())) {
			returnStr = "<span class='color_yellow'>" + getDateStrJudgeYearMmDd(pepd.getScheduleStartDate()) + "</span>";
		}
		return returnStr;
	}

	/*
	 * 获取单个节点的显示的结束时间
	 */
	public String getPrintEndDate(ExecPlanDetail pepd) {
		Date myDate = DateOperator.getDateNow();
		int myYear = DateOperator.getYear(myDate);
		String returnStr = "";
		if (null == pepd || new ExecPlanDetail() == pepd)
			return "";
		if (!pepd.getActiveBl()) {
			returnStr = "<span class='color_black'>/</span>";
		} else if ("0".equalsIgnoreCase(pepd.getStatus()) && "0".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			returnStr = "<span class='color_red'>" + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
		} else if (("0".equalsIgnoreCase(pepd.getStatus()) || "1".equalsIgnoreCase(pepd.getStatus())) && "1".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			if (DateOperator.getDateNow().after(pepd.getScheduleEndDate())) {
				returnStr = "<span class='color_red'>" + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			} else {
				returnStr = "<span class='color_blue'>" + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			}
		} else if ("2".equalsIgnoreCase(pepd.getStatus())) {
			if (pepd.getRealEndDate().after(pepd.getScheduleEndDate())) {
				returnStr = "<span class='color_dark_green'>" + getDateStrJudgeYear(myYear, pepd.getRealEndDate()) + "</span>";
			} else {
				returnStr = "<span class='color_green'>" + getDateStrJudgeYear(myYear, pepd.getRealEndDate()) + "</span>";
			}
		} else if ("3".equalsIgnoreCase(pepd.getStatus())) {
			returnStr = "<span class='color_yellow'>" + getDateStrJudgeYear(myYear, pepd.getRealEndDate()) + "</span>";
		}
		return returnStr;
	}

	/*
	 * 获取单个节点的显示的结束时间
	 */
	public String getPrintEndDateYMD(ExecPlanDetail pepd) {
		Date myDate = DateOperator.getDateNow();
		int myYear = DateOperator.getYear(myDate);
		String returnStr = "";
		if (null == pepd || new ExecPlanDetail() == pepd)
			return "";
		if (!pepd.getActiveBl()) {
			returnStr = "<span class='color_black'>/</span>";
		} else if ("0".equalsIgnoreCase(pepd.getStatus()) && "0".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			returnStr = "<span class='color_black'>" + getDateStrJudgeYearMmDd(pepd.getScheduleEndDate()) + "</span>";
		} else if (("0".equalsIgnoreCase(pepd.getStatus()) || "1".equalsIgnoreCase(pepd.getStatus())) && "1".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			if (DateOperator.getDateNow().after(pepd.getScheduleEndDate())) {
				returnStr = "<span class='color_red'>" + getDateStrJudgeYearMmDd(pepd.getScheduleEndDate()) + "</span>";
			} else {
				returnStr = "<span class='color_blue'>" + getDateStrJudgeYearMmDd(pepd.getScheduleEndDate()) + "</span>";
			}
		} else if ("2".equalsIgnoreCase(pepd.getStatus())) {
			if (pepd.getRealEndDate().after(pepd.getScheduleEndDate())) {
				returnStr = "<span class='color_dark_green'>" + getDateStrJudgeYearMmDd(pepd.getRealEndDate()) + "</span>";
			} else {
				returnStr = "<span class='color_green'>" + getDateStrJudgeYearMmDd(pepd.getRealEndDate()) + "</span>";
			}
		} else if ("3".equalsIgnoreCase(pepd.getStatus())) {
			returnStr = "<span class='color_yellow'>" + getDateStrJudgeYearMmDd(pepd.getRealEndDate()) + "</span>";
		}
		return returnStr;
	}

	/*
	 * 获取单个节点的时间的显示的HTML
	 */
	public String getPrintDate(ExecPlanDetail pepd, String strType) {
		Date myDate = DateOperator.getDateNow();
		int myYear = DateOperator.getYear(myDate);
		String printStatus = getPrintStatus(pepd);
		String returnStr = "";
		if ("noActive".equalsIgnoreCase(printStatus)) {
			returnStr = "<span title='此节点关闭'>/</span>";
		} else if ("going".equalsIgnoreCase(printStatus) || ("preComplete".equalsIgnoreCase(printStatus))) {
			if ("startDate".equalsIgnoreCase(strType) && null != pepd.getScheduleStartDate()) {
				returnStr = "<span class='color_blue' title='进行中'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "</span>";
			} else if ("endDate".equalsIgnoreCase(strType) && null != pepd.getScheduleEndDate()) {
				returnStr = "<span class='color_blue' title='进行中'>" + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			}
		} else if ("suspend".equalsIgnoreCase(printStatus)) {
			if ("startDate".equalsIgnoreCase(strType) && null != pepd.getScheduleStartDate()) {
				returnStr = "<span class='color_red' title='过期'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "</span>";
			} else if ("endDate".equalsIgnoreCase(strType) && null != pepd.getScheduleEndDate()) {
				returnStr = "<span class='color_red' title='过期'>" + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			}
		} else if ("noConfirm".equalsIgnoreCase(printStatus)) {
			if ("startDate".equalsIgnoreCase(strType) && null != pepd.getScheduleStartDate()) {
				returnStr = "<span class='color_black' title='未确认'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "</span>";
			} else if ("endDate".equalsIgnoreCase(strType) && null != pepd.getScheduleEndDate()) {
				returnStr = "<span class='color_black' title='未确认'>" + getDateStrJudgeYear(myYear, pepd.getScheduleEndDate()) + "</span>";
			}
		} else if ("complete".equalsIgnoreCase(printStatus)) {
			if ("startDate".equalsIgnoreCase(strType) && null != pepd.getScheduleStartDate()) {
				returnStr = "<span class='color_green' title='完成'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "</span>";
			} else if ("endDate".equalsIgnoreCase(strType) && null != pepd.getRealEndDate()) {
				returnStr = "<span class='color_green' title='完成'>" + getDateStrJudgeYear(myYear, pepd.getRealEndDate()) + "</span>";
			}
		} else if ("completeSuspend".equalsIgnoreCase(printStatus)) {
			if ("startDate".equalsIgnoreCase(strType) && null != pepd.getScheduleStartDate()) {
				returnStr = "<span class='color_dark_green' title='完成(曾过期)'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "</span>";
			} else if ("endDate".equalsIgnoreCase(strType) && null != pepd.getRealEndDate()) {
				returnStr = "<span class='color_dark_green' title='完成(曾过期)'>" + getDateStrJudgeYear(myYear, pepd.getRealEndDate()) + "</span>";
			}
		} else if ("preComplete".equalsIgnoreCase(printStatus)) {
			if ("startDate".equalsIgnoreCase(strType) && null != pepd.getScheduleStartDate()) {
				returnStr = "<span class='color_yellow' title='预完成'>" + getDateStrJudgeYear(myYear, pepd.getScheduleStartDate()) + "</span>";
			} else if ("endDate".equalsIgnoreCase(strType) && null != pepd.getRealEndDate()) {
				returnStr = "<span class='color_yellow' title='预完成'>" + getDateStrJudgeYear(myYear, pepd.getRealEndDate()) + "</span>";
			}
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

	/*
	 * 根据当前年份获取date的字符串，如果是同一年，则不显示前面的年份
	 */
	public String getDateStrJudgeYearMmDd(Date date) {
		String returnStr = "";
		if (null != date) {
			returnStr = DateOperator.formatDate(date, "yyyy-MM-dd");
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
		execPlanLayouts = execPlanLayoutManager.getPlansPerProjCd(projectCd, planTypeCd, false);
		return "configPlan";
	}

	/**
	 * 功能: 激活/禁用控制计划
	 * 
	 * @param projPlanId
	 * @param projPlanState
	 * @return
	 * @throws Exception
	 */
	public String changePlanState() throws Exception {
		if (execPlanLayoutManager.changePlanState(projPlanId, projPlanState)) {
			Struts2Utils.renderText("succ");
		} else {
			Struts2Utils.renderText("fail");
		}
		return null;
	}

	/**
	 * 成本控制计划的执行计划
	 * 
	 * @return
	 */
	public String getCostCtrl() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = format.format(DateOperator.getDateNow());
		String nextMonthDate = format.format(DateOperator.addMonthes(DateOperator.getDateNow(), 2));
		String prevMonthDate = format.format(DateOperator.addMonthes(DateOperator.getDateNow(), -1));
		detailsList = detailManager.fetchCenterDetails(nextMonthDate, prevMonthDate);

		return "costCtrl";
	}

	/**
	 * 功能: 保存项目计划配置信息
	 * 
	 * @param projPlanId
	 * @param projectCd
	 * @param planTypeCd
	 * @param projPlanName
	 * @return
	 * @throws Exception
	 */
	public String saveProjPlan() throws Exception {
		String sequenceNo_str = Struts2Utils.getRequest().getParameter("sequenceNo");
		long sequenceNo = 0;
		try{
			sequenceNo = Long.parseLong(sequenceNo_str);
		}catch(Exception e){}
		if (execPlanLayoutManager.saveProjPlanInfo(projPlanId, projectCd, planTypeCd, projPlanName,sequenceNo)) {
			Struts2Utils.renderText("succ");
		} else {
			Struts2Utils.renderText("fail");
		}
		return null;
	}

	/*
	 * 新增主力店签约信息
	 */
	public String doSavePlus() throws Exception {
		String execPlanDetailPlusId = Struts2Utils.getParameter("execPlanDetailPlusId");
		String shopName = Struts2Utils.getParameter("shopName");
		String scheduleEndDate = Struts2Utils.getParameter("scheduleEndDate");
		String realEndDate = Struts2Utils.getParameter("realEndDate");
		String status = Struts2Utils.getParameter("status");
		planTypeCd = "15";
		if (StringUtils.isNotBlank(execPlanDetailPlusId)) {
			// 修改
			ExecPlanDetailPlus epdp = execPlanDetailPlusManager.getEntity(execPlanDetailPlusId);
			epdp.setShopName(shopName);
			try {
				if (StringUtils.isNotBlank(scheduleEndDate)) {
					epdp.setScheduleEndDate(DateOperator.parse(scheduleEndDate, "yyyy-MM-dd"));
				}
			} catch (Exception e) {
			}
			try {
				if (StringUtils.isNotBlank(realEndDate)) {
					epdp.setRealEndDate(DateOperator.parse(realEndDate, "yyyy-MM-dd"));
				}
			} catch (Exception e) {
			}
			if (status.length() == 1) {
				epdp.setStatus(status);
			}
			execPlanDetailPlusManager.saveExecPlanDetailPlus(epdp);
		} else {
			// 新增
			ExecPlanDetailPlus epdp = new ExecPlanDetailPlus();
			epdp.setShopName(shopName);
			try {
				if (StringUtils.isNotBlank(scheduleEndDate)) {
					epdp.setScheduleEndDate(DateOperator.parse(scheduleEndDate, "yyyy-MM-dd"));
				}
			} catch (Exception e) {
			}
			try {
				if (StringUtils.isNotBlank(realEndDate)) {
					epdp.setRealEndDate(DateOperator.parse(realEndDate, "yyyy-MM-dd"));
				}
			} catch (Exception e) {
			}
			epdp.setStatus("0");
			epdp.setExecPlanDetailId(planDetailId);
			execPlanDetailPlusManager.saveExecPlanDetailPlus(epdp);
		}
		return planDetailInput();
	}

	/**
	 * 项目开发计划明细表
	 * 
	 * @return
	 */
	public String exportPlanExecPlanDetail() throws UnsupportedEncodingException {

		// 明细状态 [3=预完成, 2=完成, 1=预完成, 0=未完成]
		planDetailStatusMap = detailManager.buildPlanDetailStatusMap();
		// 项目开发计划列表
		projects = fetchAllProjects();
		String projectCd = Struts2Utils.getParameter("projectCd");
		String resOrgName = "";
		if (projects != null) {
			for (WsPlasOrg wo : projects) {
				if (projectCd.equals(wo.getOrgCd())) {
					resOrgName = wo.getOrgName();
					break;
				}
			}
		}
		// 列表出所有节点
		viewPlanNodes = execPlanNodeManager.searchNodes("1", "3", "");
		// 搜索出所对应的所有业态
		execPlanLayouts = execPlanLayoutManager.getLayouts(projectCd, planTypeCd, true, if_bis);
		Map<String, String> nodeMap = new HashMap<String, String>();
		// 明细数据
		String[] nodeIds = new String[viewPlanNodes.size()];
		ExecPlanNode n = null;
		for (int i = 0; i < viewPlanNodes.size(); i++) {
			n = viewPlanNodes.get(i);
			nodeIds[i] = n.getExecPlanNodeId();
			// 映射
			nodeMap.put(n.getExecPlanNodeId(), "[" + n.getPointLevel() + "]" + n.getNodeName());
		}

		@SuppressWarnings("hiding")
		Map<String, ExecPlanDetail> planDetailMap = new HashMap<String, ExecPlanDetail>();
		String key = null;
		for (ExecPlanDetail d : detailManager.getDetailsByProjPlanNodes(nodeIds, "Export")) {
			// {relId_planCd: detail}
			key = d.getExecPlanNode().getExecPlanNodeId() + "_" + d.getExecutionPlanCd();
			planDetailMap.put(key, d);
		}
		String fileName = "" + resOrgName + " 执行计划明细";
		is = new PlanExecExportUtil().createExcelPlanDetailNew(fileName, viewPlanNodes, execPlanLayouts, planDetailMap, planDetailStatusMap, nodeMap);
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");

		return "export";
	}

	/**
	 * 商业四级计划：导出功能
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String exportPlanExecBiz4() throws Exception {
		String planTypeCd = Struts2Utils.getParameter("planTypeCd");	//商铺类型
		String projectCd = Struts2Utils.getRequest().getParameter("projectCd");	//项目cd
		String projectName = PlasCache.getOrgByCd(projectCd).getOrgName();	//项目名称
		List<ExecPlanExcelBisVO> excelResult = new ArrayList<ExecPlanExcelBisVO>();	//列表的结果集
		execPlanLayouts = execPlanLayoutManager.getLayouts(projectCd, planTypeCd, true, true);
		if(null!=execPlanLayouts && execPlanLayouts.size()>0){
			searchPlans = execPlanLayouts.get(0).getExecPlanLayoutId();
		}
		nowViewStyle = "2";	//查看全部
		List<ExecPlanDetail> details = detailManager.getDetailsByProjPlanNodes(null, "", searchPlans, filter_GED_scheduleStartDate, filter_LED_scheduleStartDate, filter_GED_scheduleEndDate,
				filter_LED_scheduleEndDate, filter_GED_realEndDate, filter_LED_realEndDate, search_status, nowViewStyle, nowResOrgNames,"24");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		for(int i=0;null!=details && i<details.size();i++){
			ExecPlanDetail epd = details.get(i);
			ExecPlanExcelBisVO epebv = new ExecPlanExcelBisVO();
			if(null!=epd.getExecPlanNode().getPeriodName()){
				epebv.setPeriodName(epd.getExecPlanNode().getPeriodName());
			}
			if(StringUtils.isNotBlank(epd.getExecPlanNode().getTreeParentNodeId())){
				ExecPlanNode epn3 = execPlanNodeManager.getEntityByNodeCd(epd.getExecPlanNode().getTreeParentNodeId(),"1");
				if(null!=epn3){
					epebv.setNodeName3(epn3.getNodeName());
					epebv.setSequenceNo3(epn3.getSequenceNo());
				}
			}
			epebv.setSequenceNo(epd.getExecPlanNode().getSequenceNo());
			epebv.setNodeName(epd.getExecPlanNode().getNodeName());
			epebv.setResOrgName(epd.getExecPlanNode().getResOrgName());
			epebv.setStandardStartDate(epd.getExecPlanNode().getStandardStartDate());
			epebv.setStandardEndDate(epd.getExecPlanNode().getStandardEndDate());
			epebv.setOutputFiles(epd.getExecPlanNode().getOutputFiles());
			if(null!=epd.getScheduleStartDate()){
				epebv.setScheduleStartDate(formatter.format(epd.getScheduleStartDate()));
			}
			if(null!=epd.getScheduleEndDate()){
				epebv.setScheduleEndDate(formatter.format(epd.getScheduleEndDate()));
			}
			String st = getPrintStatus(epd);
			if("complete".equalsIgnoreCase(st) || "completeSuspend".equalsIgnoreCase(st)){
				if(null!=epd.getRealEndDate()){
					epebv.setRealEndDate(formatter.format(epd.getRealEndDate()));
				}
			}
			epebv.setStatus(getPrintStatusCn(epd,true));
			excelResult.add(epebv);
		}
		String excelName = "";
		if (planTypeCd.equals("14") || planTypeCd.equals("24")) {
			excelName = "商业四级计划";
		} else if (planTypeCd.equals("15")) {
			excelName = "开业筹备五级";
		} else if (planTypeCd.equals("16")) {
			excelName = "招商五级";
		}
		Map beans = new HashMap();
		beans.put("tempObj", excelResult);
		beans.put("nowDate", DateOperator.formatDate(new Date(), "yyyy/MM/dd"));
		beans.put("projectName", projectName);
		is = JXLExcelUtil.initJxlsInputStream(beans, "biz.xls");
		String fileName = excelName + "-" + projectName + "-" +DateOperator.formatDate(new Date(), "MMdd");
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}

	@SuppressWarnings("unchecked")
	public String exportPlanExecBiz16() throws Exception {
		currentDate = DateOperator.getDateNow();
		projects = fetchAllProjects();
		if (StringUtils.isBlank(projectCd)) {
			projectCd = "288";
		}
		buildPermission(projectCd, null);

		// 所有业态
		execPlanLayouts = execPlanLayoutManager.getLayouts(projectCd, planTypeCd, true, true);
		if (true) {
			// 如果是部门切换，选择所有业态
			searchPlans = "";
			for (ExecPlanLayout epl : execPlanLayouts) {
				searchPlans += epl.getExecPlanLayoutId() + ",";
			}
			if (!"".equalsIgnoreCase(searchPlans)) {
				searchPlans = searchPlans.substring(0, searchPlans.length() - 1);
			}
		}
		// 所选业态
		try {
			if (null != searchPlans) {
				searchedPlans = new ArrayList<ExecPlanLayout>();
				String[] arr_plans = searchPlans.split(",");
				for (ExecPlanLayout epl : execPlanLayouts) {
					for (String compare_plan : arr_plans) {
						if (compare_plan.equalsIgnoreCase(epl.getExecPlanLayoutId())) {
							searchedPlans.add(epl);
							break;
						}
					}
				}
			} else {
				searchedPlans = new ArrayList<ExecPlanLayout>();
			}
		} catch (Exception e) {
			searchedPlans = execPlanLayouts;
		}

		// 所有节点
		// planNodes = buildProjPlanNodes(projectCd, planTypeCd,
		// planType,resOrgCd,resOrgName,null);
		// 显示节点
		if ("10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd)) {
			// 如果是商业计划汇总，同时显示四级、五级计划；如果是商业四级计划，同时显示四级、开业筹备五级计划
			execPlanAllVoList = new ArrayList<ExecPlanAllVo>();
			List<ExecPlanNode> nodeList14 = execPlanNodeManager.searchNodes("14", nowPointLevel, nowResOrgNames);
			List<ExecPlanNode> nodeList15 = execPlanNodeManager.searchNodes("15", nowPointLevel, nowResOrgNames);
			List<ExecPlanNode> nodeList16 = execPlanNodeManager.searchNodes("16", nowPointLevel, nowResOrgNames);
			viewPlanNodes = nodeList14; // 商业四级节点才搜索项目三级关联节点
			int now_allList_i = 0; // 用来设置相同节点的i，每个循环都用
			for (int i = 0; null != nodeList14 && i < nodeList14.size(); i++) {
				ExecPlanNode epn14 = nodeList14.get(i);
				ExecPlanAllVo allvo14 = new ExecPlanAllVo();
				allvo14.setExecPlanNodeId(epn14.getExecPlanNodeId());
				allvo14.setSequenceNo(epn14.getSequenceNo());
				allvo14.setSequenceNoHide(epn14.getSequenceNo());
				allvo14.setNodeName14(epn14.getNodeName());
				allvo14.setResOrgName(epn14.getResOrgName());
				allvo14.setOutputFiles(epn14.getOutputFiles());
				allvo14.setStandardStartDate(epn14.getStandardStartDate());
				allvo14.setStandardEndDate(epn14.getStandardEndDate());
				execPlanAllVoList.add(allvo14);
				now_allList_i = Integer.valueOf(execPlanAllVoList.size() - 1);

				for (int j = 0; null != nodeList15 && j < nodeList15.size(); j++) {
					ExecPlanNode epn15 = nodeList15.get(j);
					if (StringUtils.isNotBlank(epn15.getTreeParentNodeId()) && epn14.getExecPlanNodeId().equalsIgnoreCase(epn15.getTreeParentNodeId())) {
						if (null != epn15.getIfParentSame() && epn15.getIfParentSame()) {
							// 如果是相同节点，不做操作
							// ExecPlanAllVo allvo14Temp =
							// execPlanAllVoList.get(now_allList_i);
							// allvo14Temp.setNodeName15(epn15.getNodeName());
							// allvo14Temp.setExecPlanNodeId15(epn15.getExecPlanNodeId());
							// execPlanAllVoList.set(now_allList_i,
							// allvo14Temp);
						} else {
							// 如果是不同节点，增加一个Vo对象
							ExecPlanAllVo allvo15 = new ExecPlanAllVo();
							allvo15.setExecPlanNodeId(epn15.getExecPlanNodeId());
							allvo15.setSequenceNoHide(epn15.getSequenceNo());
							allvo15.setNodeName14("&nbsp;" + epn15.getNodeName());
							allvo15.setResOrgName(epn15.getResOrgName());
							allvo15.setOutputFiles(epn15.getOutputFiles());
							allvo15.setStandardStartDate(epn15.getStandardStartDate());
							allvo15.setStandardEndDate(epn15.getStandardEndDate());
							allvo15.setExecPlanNodeId(epn15.getExecPlanNodeId());

							// 如果是招商5级计划，相同的节点，删除招商5级计划的节点
							for (int k = 0; null != nodeList16 && k < nodeList16.size(); k++) {
								ExecPlanNode epn16 = nodeList16.get(k);
								if (StringUtils.isNotBlank(epn16.getParentId15()) && epn15.getExecPlanNodeId().equalsIgnoreCase(epn16.getParentId15())) {
									allvo15.setSequenceNoHide(epn16.getSequenceNo());
									allvo15.setNodeName16(epn16.getNodeName());
									nodeList16.remove(k);
									break;
								}
							}
							execPlanAllVoList.add(allvo15);
						}
						nodeList15.remove(j);
						j--;
					}
				}
				if ("10".equalsIgnoreCase(planTypeCd)) {
					for (int k = 0; null != nodeList16 && k < nodeList16.size(); k++) {
						ExecPlanNode epn16 = nodeList16.get(k);
						if (StringUtils.isNotBlank(epn16.getTreeParentNodeId()) && epn14.getExecPlanNodeId().equalsIgnoreCase(epn16.getTreeParentNodeId())) {
							if (null != epn16.getIfParentSame() && epn16.getIfParentSame()) {
								// 如果是相同节点，不做操作
								// ExecPlanAllVo allvo14Temp =
								// execPlanAllVoList.get(now_allList_i);
								// allvo14Temp.setNodeName16(epn16.getNodeName());
								// execPlanAllVoList.set(now_allList_i,
								// allvo14Temp);
							} else {
								// 如果是不同节点，增加一个Vo对象
								ExecPlanAllVo allvo16 = new ExecPlanAllVo();
								allvo16.setExecPlanNodeId(epn16.getExecPlanNodeId());
								allvo16.setSequenceNoHide(epn16.getSequenceNo());
								allvo16.setNodeName14("&nbsp;&nbsp;" + epn16.getNodeName());
								allvo16.setResOrgName(epn16.getResOrgName());
								allvo16.setOutputFiles(epn16.getOutputFiles());
								allvo16.setStandardStartDate(epn16.getStandardStartDate());
								allvo16.setStandardEndDate(epn16.getStandardEndDate());
								execPlanAllVoList.add(allvo16);
							}
							nodeList16.remove(k);
							k--;
						}
					}
				}
			}
		} else {
			viewPlanNodes = execPlanNodeManager.searchNodes(planTypeCd, nowPointLevel, nowResOrgNames);
		}

		mapNodeName = new HashMap<String, String>();
		mapNodeName16 = new HashMap<String, String>();
		parentNodeMap = new HashMap<String, ExecPlanNode>();
		parentNodeVoMap = new HashMap<String, ExecPlanNodeParentVo>(); // 商业的汇总界面用到
		if ("5".equalsIgnoreCase(planTypeCd) || "10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd) || "14".equalsIgnoreCase(planTypeCd)
				|| "15".equalsIgnoreCase(planTypeCd) || "16".equalsIgnoreCase(planTypeCd) || "24".equalsIgnoreCase(planTypeCd)) {
			// 如果是专项计划和商业计划，处理关联节点
			// boolean flag = true;
			List<ExecPlanNode> viewPlanNodesList = execPlanNodeManager.searchNodes(planTypeCd, nowPointLevel, nowResOrgNames);
			Constants.viewPlanNodes16 = viewPlanNodesList;
			for (int i = 0; null != viewPlanNodes && i < viewPlanNodes.size(); i++) {
				ExecPlanNode ppnr = (ExecPlanNode) viewPlanNodes.get(i);
				mapNodeName16.put(ppnr.getExecPlanNodeId(), ppnr.getNodeName());
				// if("16".equalsIgnoreCase(planTypeCd)){
				// if(flag){
				// mapNodeName16.put(ppnr.getExecPlanNodeId(),ppnr.getNodeName());
				// // Constants.viewPlanNodes16 = viewPlanNodes;
				// }
				// }
				// flag = false;
				if ("14".equalsIgnoreCase(planTypeCd) || "15".equalsIgnoreCase(planTypeCd) || "24".equalsIgnoreCase(planTypeCd)) {
					mapNodeName.put(ppnr.getExecPlanNodeId(), ppnr.getNodeName());
					Constants.viewPlanNodes16 = viewPlanNodes;
				}
				if (null != ppnr.getTreeParentNodeId()) {
					if ("5".equalsIgnoreCase(planTypeCd)) {
						// 专项计划
						ExecPlanNode tempppnr = execPlanNodeManager.getSameNode1(ppnr);
						viewPlanNodes.set(i, tempppnr);
						parentNodeMap.put(tempppnr.getExecPlanNodeId(), ppnr);
					} else {
						// 商业执行计划
						if ("16".equalsIgnoreCase(planTypeCd)) {
							if (null != ppnr.getParentId15() && !"".equalsIgnoreCase(ppnr.getParentId15())) {
								// 如果在五级开业中能找得到，就找五级开业
								ExecPlanNode tempppnr = execPlanNodeManager.getSameNode15(ppnr);
								viewPlanNodes.set(i, tempppnr);
								mapNodeName.put(tempppnr.getExecPlanNodeId(), ppnr.getNodeName());
							} else {
								// 寻找相同的四级节点，覆盖该对象，同时把原来的名字替换过去
								ExecPlanNode tempppnr = execPlanNodeManager.getSameNode14(ppnr);
								viewPlanNodes.set(i, tempppnr);
								mapNodeName.put(tempppnr.getExecPlanNodeId(), ppnr.getNodeName());
							}
						}
						ExecPlanNode tempppnr = execPlanNodeManager.getSameNode1(ppnr);
						ExecPlanNodeParentVo epnpv = new ExecPlanNodeParentVo();
						epnpv.setExecPlanNodeId(tempppnr.getExecPlanNodeId());
						epnpv.setNodeName(tempppnr.getNodeName());
						epnpv.setSequenceNo(tempppnr.getSequenceNo().intValue());
						if (null != execPlanLayouts && execPlanLayouts.size() > 0) {
							ExecPlanLayout epl = execPlanLayouts.get(0);
							ExecPlanDetail epd = detailManager.getPlanDetailPerNodeRelIdAndPlanCd(projectCd, tempppnr.getExecPlanNodeId(), epl
									.getExecPlanLayoutId());
							if (null != epd) {
								epnpv.setStartDate(getPrintDate(epd, "startDate"));
								epnpv.setEndDate(getPrintDate(epd, "endDate"));
								epnpv.setExecPlanDetailId(epd.getExecPlanDetailId());
							}
						}
						parentNodeVoMap.put(ppnr.getExecPlanNodeId(), epnpv);

						// 组装mapTips2
						try {
							ExecPlanDetail epd2 = detailManager.getEntity(epnpv.getExecPlanDetailId());
							List<ExecPlanDetailMess> epdms = epd2.getExecPlanDetailMesses();
							StringBuffer tip = new StringBuffer();
							DateFormat df = new SimpleDateFormat("MM-dd");
							if (epdms.size() > 0) {
								tip.append("当前状态："+this.getPrintStatusCn(epd2,true));
								tip.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
								int size = epdms.size() >= 3 ? 3 : epdms.size();
								for (int im = 0; im < size; im++) {
									ExecPlanDetailMess epdm = epdms.get(im);
									String userName = CodeNameUtil.getUserNameByCd(epdm.getCreator());
									tip.append("<li><strong>" + userName + "&nbsp;" + df.format(epdm.getCreatedDate()) + "</strong>："
											+ EncodeUtils.htmlEscape(epdm.getMessContent()) + "</li>");
								}
								tip.append("</ul>");
							}
							mapTips2.put(epd2.getExecPlanDetailId(), tip.toString());
						} catch (Exception e) {
						}
					}
				}
			}
		}

		if ("10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd) || "14".equalsIgnoreCase(planTypeCd) || "15".equalsIgnoreCase(planTypeCd)
				|| "16".equalsIgnoreCase(planTypeCd)) {
			// 商业执行计划，还需要读取主力店和次主力店的签约情况
			nMainSigned = detailManager.getNumberSign(projectCd, "nMainSigned");
			nMainUnsign = detailManager.getNumberSign(projectCd, "nMainUnsign");
			nSubSigned = detailManager.getNumberSign(projectCd, "nSubSigned");
			nSubUnsign = detailManager.getNumberSign(projectCd, "nSubUnsign");
			nMainIn = detailManager.getNumberSign(projectCd, "nMainIn");
			nMainUnin = detailManager.getNumberSign(projectCd, "nMainUnin");
			nSubIn = detailManager.getNumberSign(projectCd, "nSubIn");
			nSubUnin = detailManager.getNumberSign(projectCd, "nSubUnin");

			// 设置节点名称后加上统计签约情况的数据
			if ("14".equalsIgnoreCase(planTypeCd) || "15".equalsIgnoreCase(planTypeCd) || "16".equalsIgnoreCase(planTypeCd)) {
				Set<Map.Entry<String, String>> set = mapNodeName.entrySet();
				for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
					if ("54".equalsIgnoreCase(entry.getKey())) {
						entry.setValue(entry.getValue() + "&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-weight:bolder;'>(<span class='color_green'>" + nMainSigned
								+ "</span>/<span class='color_red'>" + nMainUnsign + "</span>)</span>");
					} else if ("55".equalsIgnoreCase(entry.getKey())) {
						entry.setValue(entry.getValue() + "&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-weight:bolder;'>(<span class='color_green'>" + nSubSigned
								+ "</span>/<span class='color_red'>" + nSubUnsign + "</span>)</span>");
					}
				}
			} else if ("10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd)) {
				// 商业计划汇总，从execPlanAllVoList中判断节点
				for (int i = 0; null != execPlanAllVoList && i < execPlanAllVoList.size(); i++) {
					ExecPlanAllVo epav = execPlanAllVoList.get(i);
					if (1639 == epav.getSequenceNoHide()) {
						epav.setNodeName14(epav.getNodeName14() + "&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-weight:bolder;'>(<span class='color_green'>"
								+ nMainSigned + "</span>/<span class='color_red'>" + nMainUnsign + "</span>)</span>");
						execPlanAllVoList.set(i, epav);
					} else if (1640 == epav.getSequenceNoHide()) {
						epav.setNodeName14(epav.getNodeName14() + "&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-weight:bolder;'>(<span class='color_green'>"
								+ nSubSigned + "</span>/<span class='color_red'>" + nSubUnsign + "</span>)</span>");
						execPlanAllVoList.set(i, epav);
					}
				}
			}
		}

		// 节点与业态映射关系
		String[] nodeIds;
		String[] nodeIdss = null;
		if ("10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd)) {
			// 商业的汇总的情况
			nodeIds = new String[execPlanAllVoList.size()];
			ExecPlanAllVo n = null;
			for (int i = 0; i < execPlanAllVoList.size(); i++) {
				n = execPlanAllVoList.get(i);
				nodeIds[i] = n.getExecPlanNodeId();
			}
		} else {
			nodeIds = new String[viewPlanNodes.size()];
			ExecPlanNode n = null;
			for (int i = 0; i < viewPlanNodes.size(); i++) {
				n = viewPlanNodes.get(i);
				nodeIds[i] = n.getExecPlanNodeId();
			}
		}
		planDetailMap = buildPlanDetailMap(nodeIds, "", searchPlans, filter_GED_scheduleStartDate, filter_LED_scheduleStartDate, filter_GED_scheduleEndDate,
				filter_LED_scheduleEndDate, filter_GED_realEndDate, filter_LED_realEndDate, "999", "2", nowResOrgNames, planTypeCd);

		// 整合成页面上的显示，放到map里传到页面上
		mapPrintHtml = new HashMap<String, String>();
		mapPrintStartDate = new HashMap<String, String>();
		mapPrintEndDate = new HashMap<String, String>();
		mapPrintStartDate1 = new HashMap<String, String>();
		mapPrintEndDate1 = new HashMap<String, String>();
		Set<Map.Entry<String, ExecPlanDetail>> set = planDetailMap.entrySet();
		for (Iterator<Map.Entry<String, ExecPlanDetail>> it = set.iterator(); it.hasNext();) {
			try {
				Map.Entry<String, ExecPlanDetail> entry = (Map.Entry<String, ExecPlanDetail>) it.next();
				ExecPlanDetail pepd = entry.getValue();
				if (null != pepd) {
					// 生成显示的map
					if ("10".equalsIgnoreCase(planTypeCd) || "11".equalsIgnoreCase(planTypeCd) || "14".equalsIgnoreCase(planTypeCd)
							|| "15".equalsIgnoreCase(planTypeCd) || "16".equalsIgnoreCase(planTypeCd) || "24".equalsIgnoreCase(planTypeCd)) {
						mapPrintHtml.put(entry.getKey(), getPrintStatusCn(pepd,false));
						mapPrintStartDate.put(entry.getKey(), getPrintStartDate(pepd));
						mapPrintEndDate.put(entry.getKey(), getPrintEndDate(pepd));
						mapPrintStartDate1.put(entry.getKey(), getPrintStartDateYMD(pepd));
						mapPrintEndDate1.put(entry.getKey(), getPrintEndDateYMD(pepd));
					} else {
						mapPrintHtml.put(entry.getKey(), getPrintHtml(pepd));
					}
				}

				// 拼装组合tip
				List<ExecPlanDetailMess> epdms = pepd.getExecPlanDetailMesses();
				StringBuffer tip = new StringBuffer();
				DateFormat df = new SimpleDateFormat("MM-dd");
				if (epdms.size() > 0) {
					tip.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
					int size = epdms.size() >= 3 ? 3 : epdms.size();
					for (int i = 0; i < size; i++) {
						ExecPlanDetailMess epdm = epdms.get(i);
						String userName = CodeNameUtil.getUserNameByCd(epdm.getCreator());
						tip.append("<li><strong>" + userName + "&nbsp;" + df.format(epdm.getCreatedDate()) + "</strong>："
								+ EncodeUtils.htmlEscape(epdm.getMessContent()) + "</li>");
					}
					tip.append("</ul>");
				}
				mapTips.put(pepd.getExecPlanDetailId(), tip.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("1".equalsIgnoreCase("2")) {
			// 如果是精简模式，去掉没有detail的节点
			if (!"10".equalsIgnoreCase(planTypeCd) && !"11".equalsIgnoreCase(planTypeCd)) {
				// 如果不是商业的汇总界面，从viewPlanNodes取数判断
				boolean if_has = false;
				for (int i = 0; null != viewPlanNodes && i < viewPlanNodes.size(); i++) {
					ExecPlanNode prel = (ExecPlanNode) viewPlanNodes.get(i);
					if_has = false;
					for (Iterator<Map.Entry<String, ExecPlanDetail>> it = set.iterator(); it.hasNext();) {
						Map.Entry<String, ExecPlanDetail> entry = (Map.Entry<String, ExecPlanDetail>) it.next();
						ExecPlanDetail pepd = entry.getValue();
						if (null != pepd && pepd.getExecPlanNode().getExecPlanNodeId().equals(prel.getExecPlanNodeId())) {
							if_has = true;
							break;
						}
					}
					if (!if_has) {
						viewPlanNodes.remove(i);
						i--;
					}
				}
			} else {
				// 如果是商业的汇总界面，从execPlanAllVoList取数判断
				boolean if_has = false;
				for (int i = 0; null != execPlanAllVoList && i < execPlanAllVoList.size(); i++) {
					ExecPlanAllVo epav = (ExecPlanAllVo) execPlanAllVoList.get(i);
					if_has = false;
					for (Iterator<Map.Entry<String, ExecPlanDetail>> it = set.iterator(); it.hasNext();) {
						Map.Entry<String, ExecPlanDetail> entry = (Map.Entry<String, ExecPlanDetail>) it.next();
						ExecPlanDetail pepd = entry.getValue();
						if (null != pepd && pepd.getExecPlanNode().getExecPlanNodeId().equals(epav.getExecPlanNodeId())) {
							if_has = true;
							break;
						}
					}
					if (!if_has) {
						execPlanAllVoList.remove(i);
						i--;
					}
				}
			}
		}
		//
		Constants.searchedPlansNew = searchedPlans;
		if ("16".equalsIgnoreCase(planTypeCd)) {
			Constants.mapNodeNameNew = mapNodeName16;
		} else {
			Constants.mapNodeNameNew = mapNodeName;
		}

		Constants.planDetailMapNew = planDetailMap;
		Constants.mapPrintStartDateNew = mapPrintStartDate1;
		Constants.mapPrintEndDateNew = mapPrintEndDate1;
		Constants.parentNodeVoMapNew = parentNodeVoMap;
		Constants.mapPrintStartDate116 = mapPrintStartDate;
		Constants.mapPrintEndDate116 = mapPrintEndDate;

		List<ExecPlanNode> results = new ArrayList<ExecPlanNode>();
		for (ExecPlanNode planNode : viewPlanNodes) {
			ExecPlanNode obj = new ExecPlanNode();
			String key = planNode.getExecPlanNodeId();
			if (mapNodeName.get(key) != "" && !mapNodeName.get(key).isEmpty() && mapNodeName.get(key).indexOf("&nbsp") >= 0) {
				obj.setNodeName(mapNodeName.get(key).substring(0, mapNodeName.get(key).indexOf("&nbsp")));
			} else {
				obj.setNodeName(mapNodeName.get(key));
			}
			obj.setSequenceNo(planNode.getSequenceNo());
			obj.setResOrgName(planNode.getResOrgName());// planNode.resOrgName
			for (ExecPlanLayout plan : searchedPlans) {
				String key2 = planNode.getExecPlanNodeId() + "_" + plan.getExecPlanLayoutId();
				ExecPlanDetail planDetail = planDetailMap.get(key2);
				String printStartDate = mapPrintStartDate.get(key2);
				if (printStartDate != null && !printStartDate.isEmpty()) {
					// System.out.println(printStartDate);
					String strStart = printStartDate.substring(printStartDate.indexOf(">") + 1, printStartDate.indexOf("</"));
					if (!strStart.isEmpty() && strStart.length() >= 8) {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
						ParsePosition pos = new ParsePosition(0);
						Date strtodate = formatter.parse("20" + strStart.substring(0, 2) + strStart.substring(2), pos);
						String dateString = formatter1.format(strtodate);
						obj.setStandardStartDate(dateString);
					} else {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
						ParsePosition pos = new ParsePosition(0);
						if (!strStart.isEmpty() && strStart != null) {
							Date strtodate = formatter.parse("2011/" + strStart.substring(0), pos);
							String dateString = formatter1.format(strtodate);
							obj.setStandardStartDate(dateString);
						} else {
							obj.setStandardStartDate("");
						}
					}
				}
				String printEndDate = mapPrintEndDate.get(key2);
				if (printEndDate != null && !printEndDate.isEmpty()) {
					String strEnd = printEndDate.substring(printEndDate.indexOf(">") + 1, printEndDate.indexOf("</"));
					if (strEnd.length() >= 8) {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
						ParsePosition pos = new ParsePosition(0);
						Date strtodate = formatter.parse("20" + strEnd.substring(0, 2) + strEnd.substring(2), pos);
						String dateString = formatter1.format(strtodate);
						obj.setStandardEndDate(dateString);
					} else {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
						ParsePosition pos = new ParsePosition(0);
						Date strtodate = formatter.parse("2011/" + strEnd.substring(0), pos);
						String dateString = formatter1.format(strtodate);
						obj.setStandardEndDate(dateString);
					}
				}
				if (planDetail != null) {
					planDetailEntity = detailManager.getEntity(planDetail.getExecPlanDetailId());
					String htmStr = getPrintStatusCn(planDetail,false);
					if (htmStr != null && !htmStr.isEmpty()) {
						String statusRemarks = htmStr.substring(htmStr.indexOf(">") + 1, htmStr.indexOf("</"));
						obj.setRemark(statusRemarks);
					} else {
						obj.setRemark("");
					}
					ExecPlanDetail epd2 = detailManager.getEntity(planDetail.getExecPlanDetailId());
					List<ExecPlanDetailMess> epdms = epd2.getExecPlanDetailMesses();
					StringBuffer tip = new StringBuffer();
					DateFormat df = new SimpleDateFormat("MM-dd");
					if (epdms.size() > 0) {
						int size = epdms.size() >= 3 ? 3 : epdms.size();
						for (int im = 0; im < size; im++) {
							ExecPlanDetailMess epdm = epdms.get(im);
							String userName = CodeNameUtil.getUserNameByCd(epdm.getCreator());
							tip.append(userName + df.format(epdm.getCreatedDate()) + ":" + epdm.getMessContent() + " \r\n");
						}
					}
					obj.setOutputFiles(tip.toString());
				}
			}
			ExecPlanNodeParentVo vo = parentNodeVoMap.get(key);
			if (vo != null) {
				if (vo.getStartDate() != null && !vo.getStartDate().isEmpty()) {
					String startTime = vo.getStartDate();
					String startDate = startTime.substring(startTime.indexOf(">") + 1, startTime.indexOf("</"));
					if (startDate.length() >= 8) {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
						ParsePosition pos = new ParsePosition(0);
						Date strtodate = formatter.parse("20" + startDate.substring(0, 2) + startDate.substring(2), pos);
						String dateString = formatter1.format(strtodate);
						obj.setCreatedCenterCd(dateString);
					} else {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
						ParsePosition pos = new ParsePosition(0);
						Date strtodate = formatter.parse("2011/" + startDate.substring(0), pos);
						String dateString = formatter1.format(strtodate);
						obj.setCreatedCenterCd(dateString);
					}
				}
				if (vo.getEndDate() != null && !vo.getEndDate().isEmpty()) {
					String endTime = vo.getEndDate();
					String endDate = endTime.substring(endTime.indexOf(">") + 1, endTime.indexOf("</"));
					if (endDate.length() >= 8) {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
						ParsePosition pos = new ParsePosition(0);
						Date strtodate = formatter.parse("20" + endDate.substring(0, 2) + endDate.substring(2), pos);
						String dateString = formatter1.format(strtodate);
						obj.setCreatedPositionCd(dateString);
					} else {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
						SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
						ParsePosition pos = new ParsePosition(0);
						Date strtodate = formatter.parse("2011/" + endDate.substring(0), pos);
						String dateString = formatter1.format(strtodate);
						obj.setCreatedPositionCd(dateString);
					}
				}
			}
			results.add(obj);
			// System.out.println("开业筹备五级计划:"+obj.getNodeName()+",开始时间:"+obj.getStandardStartDate()+",完成时间:"+obj.getStandardEndDate()+",状态:"+obj.getRemark()+",主责方:"+obj.getResOrgName()+",地产现状况开始时间:"+obj.getCreatedCenterCd()+",地产现状况完成时间:"+obj.getCreatedPositionCd()+"\n\r");
		}
		String resOrgName = "";
		if (projects != null) {
			for (WsPlasOrg wo : projects) {
				if (projectCd.equals(wo.getOrgCd())) {
					resOrgName = wo.getOrgName();
					break;
				}
			}
		}
		Map beans = new HashMap();
		beans.put("tempObj", results);
		beans.put("nowDate", DateOperator.formatDate(new Date(), "yyyy-MM-dd"));
		is = JXLExcelUtil.initJxlsInputStream(beans, "biz.xls");
		String fileName = "招商五级-" + resOrgName;// +"-" +
												// DateOperator.formatDate(new
												// Date(), "MMddHHmm");
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}

	/**
	 * 功能: 项目开发计划中，有X1个近期节点与当前用户相关
	 * 
	 * @return
	 */
	public String getAllRecentExecPlan() {

		return "";
	}

	/**
	 * 功能: 项目开发计划中，其中X2条已过期节点与当前用户相关
	 * 
	 * @return
	 */
	public String getAllSuspendExecPlan() {
		List<ExecPlanNode> suspendExecPlanList = new ArrayList<ExecPlanNode>();
		String uiid = SpringSecurityUtils.getCurrentUiid();
		String orgCd = SpringSecurityUtils.getCurrentCenterCd();
		flag = Struts2Utils.getParameter("flags");
		if (flag != null && flag.equals("1")) {
			List<ExecPlanNode> excePlanInfoMessageView = this.excePlanInfoMessageView(uiid, orgCd);
			if (excePlanInfoMessageView != null && !excePlanInfoMessageView.isEmpty()) {
				for (ExecPlanNode planNodeObj : excePlanInfoMessageView) {
					suspendExecPlanList.add(planNodeObj);
				}
			}
		} else {
			// 正常搜索
			List<ExecPlanNode> newExecPlanNodeList = this.excePlanInfo(uiid, orgCd);
			if (newExecPlanNodeList != null && !newExecPlanNodeList.isEmpty()) {
				for (ExecPlanNode planNodeObj : newExecPlanNodeList) {
					suspendExecPlanList.add(planNodeObj);
				}
			}
		}
		Struts2Utils.getRequest().setAttribute("flag", flag);
		page.setResult(suspendExecPlanList);
		buildExecPlanAttributes(page.getResult());
		return "suspendExecPlan";
	}
	
	private String now_year="0", now_month="0";


	private Map<String, Map<String, Object>> levelFourMap;
	
	
	public Map<String, Map<String, Object>> getLevelFourMap() {
		return levelFourMap;
	}

	public void setLevelFourMap(Map<String, Map<String, Object>> levelFourMap) {
		this.levelFourMap = levelFourMap;
	}

	public String getSearchPlans(String pcd){

		String search = "";
		 List<ExecPlanLayout> layouts = execPlanLayoutManager.getLayouts(pcd, "24", true, true);
		 
			for (ExecPlanLayout epl : layouts) {
				search += epl.getExecPlanLayoutId() + ",";
			}
			if (!"".equalsIgnoreCase(search)) {
				search = search.substring(0, search.length() - 1);
			}
		return search;
	}
	/**
	 *  
	 * @return
	 */
	public String levelFour()throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		Date nowDate = new Date();
		if(StringUtils.isBlank(now_year)){
			now_year=DateOperator.formatDate(nowDate, "yyyy");
		}
		if(StringUtils.isBlank(now_month)){
			now_month =DateOperator.formatDate(nowDate, "MM");
		}
		String yyyymm =Integer.valueOf(now_month)<10? now_year+"-0"+Integer.valueOf(now_month):now_year+"-"+now_month;
		levelFourMap = new HashMap<String, Map<String, Object>>();

		if(nowResOrgNames.equals("商业集团")){
			nowResOrgNames="";
		}
			
		List<ExecPlanNode> nodes = execPlanNodeManager.searchNodes("24", "1", nowResOrgNames);
		String[] nodeIds =new String[nodes.size()];
		for (int i = 0; i < nodeIds.length; i++) {
			nodeIds[i]=nodes.get(i).getExecPlanNodeId();
		}
		mapPrintHtml = new HashMap<String, String>();
		mapPrintStartDate = new HashMap<String, String>();
		mapPrintEndDate = new HashMap<String, String>();
		mapPrintEndDate1 = new HashMap<String, String>();
		
		for (WsAppDictData proj : PlasCache.getDictDataList("LAND_BIS_REL")) {
			if (!GlobalConstants.WYS_ORG_CD.equals(proj.getRemark())) {
			String search = getSearchPlans(proj.getRemark());
			List<ExecPlanDetail> detailsAll = detailManager.getDetailsByProjPlanNodes(nodeIds, "", search, "", "", "", "", "", "", "", "", "",null);
			List<ExecPlanDetail> details =new ArrayList<ExecPlanDetail>();
			for (ExecPlanDetail detail : detailsAll) {
				ExecPlanDetail detailTemp=null;
				String status = getPrintStatusCn(detail, true);
			
				if((status.indexOf("过期")>-1 || status.indexOf("进行中")>-1 || status.indexOf("预完成")>-1)&&
				((detail.getScheduleStartDate()!=null && yyyymm.equals(dateFormat.format(detail.getScheduleStartDate())))||
				 (detail.getScheduleEndDate()!=null &&	yyyymm.equals(dateFormat.format(detail.getScheduleEndDate()))))){
					detailTemp=detail;
				}
				if(status.indexOf("已完成")>-1 && detail.getRealEndDate()!=null && yyyymm.equals(dateFormat.format(detail.getRealEndDate()))){
					detailTemp=detail;
				}
				if(detailTemp!=null){
					details.add(detailTemp);
					// 拼装 日期、 状态
					mapPrintHtml.put(detailTemp.getExecPlanDetailId(), getPrintStatusCn(detailTemp, false));
					mapPrintStartDate.put(detailTemp.getExecPlanDetailId(), getPrintStartDate(detailTemp));
					mapPrintEndDate.put(detailTemp.getExecPlanDetailId(), getPrintEndDate(detailTemp));
					mapPrintEndDate1.put(detailTemp.getExecPlanDetailId(), getPrintEndDate(detailTemp));
					
					// 拼装组合tip
					List<ExecPlanDetailMess> epdms = detailTemp.getExecPlanDetailMesses();
					StringBuffer tip = new StringBuffer();
					DateFormat df = new SimpleDateFormat("MM-dd");
					if (epdms.size() > 0) {
						tip.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
						int size = epdms.size() >= 3 ? 3 : epdms.size();
						for (int i = 0; i < size; i++) {
							ExecPlanDetailMess epdm = epdms.get(i);
							String userName = CodeNameUtil.getUserNameByCd(epdm.getCreator());
							tip.append("<li><strong>" + userName + "&nbsp;" + df.format(epdm.getCreatedDate()) + "</strong>："
									+ EncodeUtils.htmlEscape(epdm.getMessContent()) + "</li>");
						}
						tip.append("</ul>");
					}
					mapTips.put(detailTemp.getExecPlanDetailId(), tip.toString());
				}
				
			}
			if(details.size()>0) {
				Map<String, Object> detailmap= new HashMap<String, Object>();
				detailmap.put("details", details);
				detailmap.put("name", proj.getDictName());
				levelFourMap.put(proj.getRemark(), detailmap);
			}
			
		}
		}
		return "levelFour";
		
	}
	
	/**
	 * 商业计划平台-->商业四级计划(近期)
	 * @return
	 */
	@SuppressWarnings({ "cast", "null" })
	public String portalBusinessList() throws Exception{
		

		planTypeCd = "24";
		mapNodeName = new HashMap<String, String>();
		mapNodeName16 = new HashMap<String, String>();
		parentNodeMap = new HashMap<String, ExecPlanNode>();
		parentNodeVoMap = new HashMap<String, ExecPlanNodeParentVo>(); // 商业的汇总界面用到
		if (StringUtils.isBlank(projectCd)) {
			projectCd = "252";
			//projectCd = "196";
		}
		List<WsPlasOrg> fetchAllProjects = fetchAllProjects();
		
		for (WsPlasOrg wsPlasOrg : fetchAllProjects) {
			if(wsPlasOrg.getOrgCd().equals(projectCd)){
				projectName=wsPlasOrg.getOrgName();
			}
		}
		
		
	
		// 所有业态
		execPlanLayouts = execPlanLayoutManager.getLayouts(projectCd, planTypeCd, true, true);
		if (true) {
			// 如果是部门切换，选择所有业态
			searchPlans = "";
			for (ExecPlanLayout epl : execPlanLayouts) {
				searchPlans += epl.getExecPlanLayoutId() + ",";
			}
			if (!"".equalsIgnoreCase(searchPlans)) {
				searchPlans = searchPlans.substring(0, searchPlans.length() - 1);
			}
		}
		// 所选业态
		try {
			if (null != searchPlans) {
				searchedPlans = new ArrayList<ExecPlanLayout>();
				String[] arr_plans = searchPlans.split(",");
				for (ExecPlanLayout epl : execPlanLayouts) {
					for (String compare_plan : arr_plans) {
						if (compare_plan.equalsIgnoreCase(epl.getExecPlanLayoutId())) {
							searchedPlans.add(epl);
							break;
						}
					}
				}
			} else {
				searchedPlans = new ArrayList<ExecPlanLayout>();
			}
		} catch (Exception e) {
			searchedPlans = execPlanLayouts;
		}
		
		
		if("24".equalsIgnoreCase(planTypeCd)){
			execPlanAllVoList = new ArrayList<ExecPlanAllVo>();
			List<ExecPlanNode> nodeList14 = execPlanNodeManager.searchNodes(planTypeCd, nowPointLevel, nowResOrgNames);
		
			viewPlanNodes = nodeList14; // 商业四级节点才搜索项目三级关联节点

		
			int now_allList_i = 0; // 用来设置相同节点的i，每个循环都用
			for (int i = 0; null != nodeList14 && i < nodeList14.size(); i++) {
				ExecPlanNode epn14 = nodeList14.get(i);
				ExecPlanAllVo allvo14 = new ExecPlanAllVo();
				allvo14.setExecPlanNodeId(epn14.getExecPlanNodeId());
				allvo14.setSequenceNo(epn14.getSequenceNo());
				allvo14.setSequenceNoHide(epn14.getSequenceNo());
				allvo14.setNodeName14(epn14.getNodeName());
				allvo14.setResOrgName(epn14.getResOrgName());
				allvo14.setOutputFiles(epn14.getOutputFiles());
				allvo14.setStandardStartDate(epn14.getStandardStartDate());
				allvo14.setStandardEndDate(epn14.getStandardEndDate());
				execPlanAllVoList.add(allvo14);
				now_allList_i = Integer.valueOf(execPlanAllVoList.size() - 1);
			}
			/*******************************************************************************************************************************/
			
			// 如果是专项计划和商业计划，处理关联节点
			// boolean flag = true;
			for (int i = 0; null != viewPlanNodes && i < viewPlanNodes.size(); i++) {
				ExecPlanNode ppnr = (ExecPlanNode) viewPlanNodes.get(i);
				mapNodeName16.put(ppnr.getExecPlanNodeId(), ppnr.getNodeName());
				
				if ( "24".equalsIgnoreCase(planTypeCd)) {
					mapNodeName.put(ppnr.getExecPlanNodeId(), ppnr.getNodeName());
					Constants.viewPlanNodes16 = viewPlanNodes;
				}
				if (null != ppnr.getTreeParentNodeId()) {
						
						ExecPlanNode tempppnr = execPlanNodeManager.getSameNode1(ppnr);
						ExecPlanNodeParentVo epnpv = new ExecPlanNodeParentVo();
						epnpv.setExecPlanNodeId(tempppnr.getExecPlanNodeId());
						epnpv.setNodeName(tempppnr.getNodeName());
						epnpv.setSequenceNo(tempppnr.getSequenceNo().intValue());
						if (null != execPlanLayouts && execPlanLayouts.size() > 0) {
							ExecPlanLayout epl = execPlanLayouts.get(0);
							ExecPlanDetail epd = detailManager.getPlanDetailPerNodeRelIdAndPlanCd(projectCd, tempppnr.getExecPlanNodeId(), epl
									.getExecPlanLayoutId());
							if (null != epd) {
								epnpv.setStartDate(getPrintDate(epd, "startDate"));
								epnpv.setEndDate(getPrintDate(epd, "endDate"));
								epnpv.setExecPlanDetailId(epd.getExecPlanDetailId());
							}
						}
						parentNodeVoMap.put(ppnr.getExecPlanNodeId(), epnpv);

						// 组装mapTips2
						try {
							ExecPlanDetail epd2 = detailManager.getEntity(epnpv.getExecPlanDetailId());
							List<ExecPlanDetailMess> epdms = epd2.getExecPlanDetailMesses();
							StringBuffer tip = new StringBuffer();
							DateFormat df = new SimpleDateFormat("MM-dd");
							if (epdms.size() > 0) {
								tip
										.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
								int size = epdms.size() >= 3 ? 3 : epdms.size();
								for (int im = 0; im < size; im++) {
									ExecPlanDetailMess epdm = epdms.get(im);
									String userName = CodeNameUtil.getUserNameByCd(epdm.getCreator());
									tip.append("<li><strong>" + userName + "&nbsp;" + df.format(epdm.getCreatedDate()) + "</strong>："
											+ EncodeUtils.htmlEscape(epdm.getMessContent()) + "</li>");
								}
								tip.append("</ul>");
							}
							mapTips2.put(epd2.getExecPlanDetailId(), tip.toString());
						} catch (Exception e) {
						}
					}
			}
			
			/*******************************************************************************************************************************/
			// 商业执行计划，还需要读取主力店和次主力店的签约情况
			nMainSigned = detailManager.getNumberSign(projectCd, "nMainSigned");
			nMainUnsign = detailManager.getNumberSign(projectCd, "nMainUnsign");
			nSubSigned = detailManager.getNumberSign(projectCd, "nSubSigned");
			nSubUnsign = detailManager.getNumberSign(projectCd, "nSubUnsign");
			nMainIn = detailManager.getNumberSign(projectCd, "nMainIn");
			nMainUnin = detailManager.getNumberSign(projectCd, "nMainUnin");
			nSubIn = detailManager.getNumberSign(projectCd, "nSubIn");
			nSubUnin = detailManager.getNumberSign(projectCd, "nSubUnin");
			
			Set<Map.Entry<String, String>> set1 = mapNodeName.entrySet();
			for (Iterator<Map.Entry<String, String>> it = set1.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
				if ("2416".equalsIgnoreCase(entry.getKey())) {
					entry.setValue(entry.getValue() + "&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-weight:bolder;'>(<span class='color_green'>" + nMainSigned
							+ "</span>/<span class='color_red'>" + nMainUnsign + "</span>)</span>");
				} else if ("2421".equalsIgnoreCase(entry.getKey())) {
					entry.setValue(entry.getValue() + "&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-weight:bolder;'>(<span class='color_green'>" + nSubSigned
							+ "</span>/<span class='color_red'>" + nSubUnsign + "</span>)</span>");
				} else if ("2461".equalsIgnoreCase(entry.getKey())) {
					entry.setValue(entry.getValue() + "&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-weight:bolder;'>(<span class='color_green'>" + nMainIn
							+ "</span>/<span class='color_red'>" + nMainUnin + "</span>)</span>");
				} else if ("2462".equalsIgnoreCase(entry.getKey())) {
					entry.setValue(entry.getValue() + "&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-weight:bolder;'>(<span class='color_green'>" + nSubIn
							+ "</span>/<span class='color_red'>" + nSubUnin + "</span>)</span>");
				}
			}
			/*******************************************************************************************************************************/
			
			// 节点与业态映射关系
			String[] nodeIds;
			String[] nodeIdss = null;
			nodeIds = new String[viewPlanNodes.size()];
			ExecPlanNode n = null;
			for (int i = 0; i < viewPlanNodes.size(); i++) {
				n = viewPlanNodes.get(i);
				nodeIds[i] = n.getExecPlanNodeId();
			}
			
			planDetailMap = buildPlanDetailMap(nodeIds, "", searchPlans, filter_GED_scheduleStartDate, filter_LED_scheduleStartDate, filter_GED_scheduleEndDate,
					filter_LED_scheduleEndDate, filter_GED_realEndDate, filter_LED_realEndDate, search_status, nowViewStyle, nowResOrgNames, planTypeCd);

			// 整合成页面上的显示，放到map里传到页面上
			mapPrintHtml = new HashMap<String, String>();
			mapPrintStartDate = new HashMap<String, String>();
			mapPrintEndDate = new HashMap<String, String>();
			mapPrintStartDate1 = new HashMap<String, String>();
			mapPrintEndDate1 = new HashMap<String, String>();
			Set<Map.Entry<String, ExecPlanDetail>> set2 = planDetailMap.entrySet();
			for (Iterator<Map.Entry<String, ExecPlanDetail>> it = set2.iterator(); it.hasNext();) {
				try {
					Map.Entry<String, ExecPlanDetail> entry = (Map.Entry<String, ExecPlanDetail>) it.next();
					ExecPlanDetail pepd = entry.getValue();
					if (null != pepd) {
						// 生成显示的map
						if ("24".equalsIgnoreCase(planTypeCd)) {
							mapPrintHtml.put(entry.getKey(), getPrintStatusCn(pepd,false));
							mapPrintStartDate.put(entry.getKey(), getPrintStartDate(pepd));
							mapPrintEndDate.put(entry.getKey(), getPrintEndDate(pepd));
							mapPrintStartDate1.put(entry.getKey(), getPrintStartDateYMD(pepd));
							mapPrintEndDate1.put(entry.getKey(), getPrintEndDateYMD(pepd));
						} else {
							mapPrintHtml.put(entry.getKey(), getPrintHtml(pepd));
						}
					}
					
					
					String status = mapPrintHtml.get(entry.getKey());
					if(status.indexOf("未启用")>-1 || status.indexOf("未确认")>-1){

						 List<ExecPlanNode> tempNodes = new ArrayList<ExecPlanNode>();
						 ExecPlanNode node= null;
						String removeNodeId = pepd.getExecPlanNode().getExecPlanNodeId();
						for (int i = 0; i < viewPlanNodes.size(); i++) {
							  node = viewPlanNodes.get(i);
							  if(!node.getExecPlanNodeId().equals(removeNodeId)) {
								//viewPlanNodes.remove(i);
								tempNodes.add(node);
							  }
						}
						viewPlanNodes=tempNodes;
					}
					String yyyymm =Integer.valueOf(now_month)<10? now_year+"/0"+Integer.valueOf(now_month):now_year+"/"+now_month;
			
					if(status.indexOf("过期")>-1 || status.indexOf("进行中")>-1 || status.indexOf("预完成")>-1){
						
						String startDate = mapPrintStartDate.get(entry.getKey());
						startDate = startDate.substring(startDate.indexOf(">")+1, startDate.lastIndexOf("<"));
						String endDate = mapPrintEndDate.get(entry.getKey());
						endDate = endDate.substring(endDate.indexOf(">")+1, endDate.lastIndexOf("<"));
						startDate= startDate.length()==5 ? DateUtil.getCurrYear()+"/"+startDate:startDate.length()==8 ? "20"+startDate:startDate;
						endDate= endDate.length()==5 ? DateUtil.getCurrYear()+"/"+endDate:endDate.length()==8 ? "20"+endDate:endDate;
					
						boolean  startBool =startDate.length()>7 && yyyymm.equalsIgnoreCase(startDate.substring(0, 7));
						boolean endBool=endDate.length()>7 && yyyymm.equalsIgnoreCase(endDate.substring(0, 7));
						String removeNodeId = pepd.getExecPlanNode().getExecPlanNodeId();
						if(!(startBool || endBool)){

							 List<ExecPlanNode> tempNodes = new ArrayList<ExecPlanNode>();
							 ExecPlanNode node= null;
								for (int i = 0; i < viewPlanNodes.size(); i++) {
									node = viewPlanNodes.get(i);
									if(!node.getExecPlanNodeId().equals(removeNodeId)) {
										//viewPlanNodes.remove(i);
										tempNodes.add(node);
									}
								}
								viewPlanNodes=tempNodes;
						}
					}
					
					if(status.indexOf("已完成")>-1){
						String removeNodeId = pepd.getExecPlanNode().getExecPlanNodeId();
						ExecPlanNodeParentVo execPlanNodeParentVo = parentNodeVoMap.get(removeNodeId);
						if(execPlanNodeParentVo!=null && execPlanNodeParentVo.getEndDate()!=null) {
							String endDate = execPlanNodeParentVo.getEndDate();
							int beginIndex = endDate.indexOf(">")+1;
							int endIndex=endDate.lastIndexOf("<");
							endDate = endDate.substring(beginIndex, endIndex);
							boolean bool= (endDate.length()==5 && (Integer.valueOf(now_month)==Integer.valueOf(endDate.substring(0, 2))))||
										  (endDate.length()==8 && (now_year.equalsIgnoreCase("20"+endDate.substring(0, 2)) && Integer.valueOf(now_month)==Integer.valueOf(endDate.substring(3, 5))));
							if(!bool) {

								 List<ExecPlanNode> tempNodes = new ArrayList<ExecPlanNode>();
								 ExecPlanNode node= null;
								for (int i = 0; i < viewPlanNodes.size(); i++) {
									node = viewPlanNodes.get(i);
									if(!node.getExecPlanNodeId().equals(removeNodeId)) {
										tempNodes.add(node);
									}
								}
								viewPlanNodes= tempNodes;
							}
					}
					}
				
					// 拼装组合tip
					List<ExecPlanDetailMess> epdms = pepd.getExecPlanDetailMesses();
					StringBuffer tip = new StringBuffer();
					DateFormat df = new SimpleDateFormat("MM-dd");
					if (epdms.size() > 0) {
						tip
								.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
						int size = epdms.size() >= 3 ? 3 : epdms.size();
						for (int i = 0; i < size; i++) {
							ExecPlanDetailMess epdm = epdms.get(i);
							String userName = CodeNameUtil.getUserNameByCd(epdm.getCreator());
							tip.append("<li><strong>" + userName + "&nbsp;" + df.format(epdm.getCreatedDate()) + "</strong>："
									+ EncodeUtils.htmlEscape(epdm.getMessContent()) + "</li>");
						}
						tip.append("</ul>");
					}
					mapTips.put(pepd.getExecPlanDetailId(), tip.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			List<ExecPlanNode> tempNodes = new ArrayList<ExecPlanNode>();
			System.out.println(viewPlanNodes.size());
			for (int i = 0; i < viewPlanNodes.size(); i++) {
				ExecPlanNode node = viewPlanNodes.get(i);
				for (int j = 0; j < searchedPlans.size(); j++) {
					ExecPlanLayout execPlanLayout = searchedPlans.get(j);
					String key =node.getExecPlanNodeId()+"_"+execPlanLayout.getExecPlanLayoutId();
					if(mapPrintHtml.containsKey(key)) {
						tempNodes.add(node);
					}
				}
			}
			viewPlanNodes = tempNodes;
			
			Constants.searchedPlansNew = searchedPlans;
			if ("16".equalsIgnoreCase(planTypeCd)) {
				Constants.mapNodeNameNew = mapNodeName16;
			} else {
				Constants.mapNodeNameNew = mapNodeName;
			}

			Constants.planDetailMapNew = planDetailMap;
			Constants.mapPrintStartDateNew = mapPrintStartDate1;
			Constants.mapPrintEndDateNew = mapPrintEndDate1;
			Constants.parentNodeVoMapNew = parentNodeVoMap;
			Constants.mapPrintStartDate116 = mapPrintStartDate;
			Constants.mapPrintEndDate116 = mapPrintEndDate;
		}
		//filterPortalBusinessList();
		return "portalBusinessList";
	}
	

	/**
	 * 遍历指令单列表构造前台展现数据
	 * 
	 * @param oaMeetings
	 */
	private void buildExecPlanAttributes(List<ExecPlanNode> nodes) {
		String id = null;
		for (ExecPlanNode m : nodes) {
			id = m.getNodeCd();
			// 鼠标放在标题上时，显示标题和最新的3条评论
			mapExecPlanTips.put(id, m.getRemark());
		}
	}

	/**
	 * 功能: 项目开发计划中，今日未回复进度的节点有X3条与当前用户相关
	 * 
	 * @return
	 */
	public String getAllNoRepliesExecPlan() {

		return "";
	}

	/**
	 * 
	 * @param uiid
	 * @param orgCd
	 * @param execPlanNodeManager
	 * @param execPlanLayoutManager
	 * @param detailManager
	 * @return
	 */
	public int getExecPlanTotalCount(String uiid, String orgCd, @SuppressWarnings("hiding") ExecPlanNodeManager execPlanNodeManager,
			@SuppressWarnings("hiding") ExecPlanLayoutManager execPlanLayoutManager, @SuppressWarnings("hiding") ExecPlanDetailManager detailManager) {
		this.execPlanNodeManager = execPlanNodeManager;
		this.execPlanLayoutManager = execPlanLayoutManager;
		this.detailManager = detailManager;
		List<ExecPlanNode> results = excePlanInfo(uiid, orgCd);
		return results.size();
	}

	/**
	 * 
	 * @param uiid
	 * @param orgCd
	 * @return
	 */
	private List<ExecPlanNode> excePlanInfoMessageView(String uiid, String orgCd) {
		List<ExecPlanNode> messageViewExecPlanNodeLists = new ArrayList<ExecPlanNode>();
		// 列表出所有节点
		viewPlanNodes = execPlanNodeManager.searchNodes("1", "3", "");
		// 搜索出所对应的所有业态
		execPlanLayouts = execPlanLayoutManager.getLayouts(orgCd, "1", true, false);
		Map<String, String> nodeMap = new HashMap<String, String>();
		// 明细数据
		String[] nodeIds = new String[viewPlanNodes.size()];
		ExecPlanNode n = null;
		for (int i = 0; i < viewPlanNodes.size(); i++) {
			n = viewPlanNodes.get(i);
			nodeIds[i] = n.getExecPlanNodeId();
			// 映射
			nodeMap.put(n.getExecPlanNodeId(), "[" + n.getPointLevel() + "]" + n.getNodeName());
		}

		planDetailMap = new HashMap<String, ExecPlanDetail>();
		String key = null;
		for (ExecPlanDetail d : detailManager.getDetailsByProjPlanNodes(nodeIds, "Export")) {
			key = d.getExecPlanNode().getExecPlanNodeId() + "_" + d.getExecutionPlanCd();
			planDetailMap.put(key, d);
		}
		projects = fetchAllProjects();
		String resOrgName = "";
		if (projects != null) {
			for (WsPlasOrg wo : projects) {
				if (SpringSecurityUtils.getCurrentCenterCd().equals(wo.getOrgCd())) {
					resOrgName = wo.getOrgName();
					break;
				}
			}
		}
		int suspendInt = 0;
		int goingInt = 0;
		for (int i = 0; i < viewPlanNodes.size(); i++) {
			ExecPlanNode tmpRel = viewPlanNodes.get(i);
			String tmpRelId = tmpRel.getExecPlanNodeId();
			String tmpRelDisplayId = tmpRel.getExecPlanNodeId();
			for (int j = 0; j < execPlanLayouts.size(); j++) {
				ExecPlanDetail tmpDetail = new ExecPlanDetail();
				if (StringUtils.isBlank(tmpRelDisplayId)) {
					tmpDetail = planDetailMap.get(tmpRelId + "_" + execPlanLayouts.get(j).getExecutionPlanCd());
				} else {
					tmpDetail = planDetailMap.get(tmpRelDisplayId + "_" + execPlanLayouts.get(j).getExecutionPlanCd());
				}
				if (tmpDetail != null) {
					// if(tmpRel.getSequenceNo().toString().equals("66")){
					if (getPrintStatusCnCover(tmpDetail).equals("已过期") && tmpDetail.getUpdator().equals(uiid)) {
						// 判断当天是否有更新
						if (formateDate2YMd(tmpDetail.getUpdatedDate()).equals(formateDate2YMd(DateOperator.getDateNow()))) {
							// 今日留言
							ExecPlanNode tmpRelNews = new ExecPlanNode();
							tmpRelNews.setExecPlanNodeId(tmpRel.getExecPlanNodeId());
							tmpRelNews.setActiveFlg("0");// 临时 该值为已过期状态
							// tmpRelNews.setCorOrgCd(SpringSecurityUtils.getCurrentDeptName());//地产
							tmpRelNews.setCorOrgCd(resOrgName);// 地产
							tmpRelNews.setCorOrgName(tmpDetail.getExecPlanLayout().getExecutionPlanName());// 业态
							tmpRelNews.setCreatedDate(tmpDetail.getScheduleEndDate());// 计划完成时间
							tmpRelNews.setPlanTypeCd(getPrintStatusCnCover(tmpDetail));// 当前状态
							tmpRelNews.setSequenceNo(tmpRel.getSequenceNo());// 序号
							tmpRelNews.setNodeName(tmpRel.getNodeName());// 节点名称
							tmpRelNews.setResOrgName(tmpRel.getResOrgName());// 主责方
							tmpRelNews.setTreeParentNodeId(tmpDetail.getExecPlanLayout().getExecPlanLayoutId());// 业态id
							tmpRelNews.setParentId15(tmpDetail.getExecPlanDetailId());// DetailId
							suspendInt++;
							// 获取留言
							List<ExecPlanDetailMess> epdms = tmpDetail.getExecPlanDetailMesses();
							if (epdms.size() > 0) {
								int size = epdms.size() >= 1 ? 1 : epdms.size();
								for (int im = 0; im < size; im++) {
									ExecPlanDetailMess epdm = epdms.get(im);
									tmpRelNews.setRemark(epdm.getMessContent());
								}
							}
							tmpRelNews.setNodeCd(tmpRel.getExecPlanNodeId() + suspendInt);
							messageViewExecPlanNodeLists.add(tmpRelNews);
						}
					}
					if (getPrintStatusCnCover(tmpDetail).equals("进行中") && tmpDetail.getUpdator().equals(uiid)) {
						if (formateDate2YMd(tmpDetail.getUpdatedDate()).equals(formateDate2YMd(DateOperator.getDateNow()))) {
							// 今日留言
							ExecPlanNode tmpRelNews = new ExecPlanNode();
							tmpRelNews.setExecPlanNodeId(tmpRel.getExecPlanNodeId());
							tmpRelNews.setActiveFlg("1");// 临时 该值为已过期状态
							// tmpRelNews.setCorOrgCd(SpringSecurityUtils.getCurrentDeptName());//地产
							tmpRelNews.setCorOrgCd(resOrgName);// 地产
							tmpRelNews.setCorOrgName(tmpDetail.getExecPlanLayout().getExecutionPlanName());// 业态
							tmpRelNews.setCreatedDate(tmpDetail.getScheduleEndDate());// 计划完成时间
							tmpRelNews.setPlanTypeCd(getPrintStatusCnCover(tmpDetail));// 当前状态
							tmpRelNews.setSequenceNo(tmpRel.getSequenceNo());// 序号
							tmpRelNews.setNodeName(tmpRel.getNodeName());// 节点名称
							tmpRelNews.setResOrgName(tmpRel.getResOrgName());// 主责方
							tmpRelNews.setTreeParentNodeId(tmpDetail.getExecPlanLayout().getExecPlanLayoutId());// 业态id
							tmpRelNews.setParentId15(tmpDetail.getExecPlanDetailId());// DetailId
							suspendInt++;
							// 获取留言
							List<ExecPlanDetailMess> epdms = tmpDetail.getExecPlanDetailMesses();
							if (epdms.size() > 0) {
								int size = epdms.size() >= 1 ? 1 : epdms.size();
								for (int im = 0; im < size; im++) {
									ExecPlanDetailMess epdm = epdms.get(im);
									tmpRelNews.setRemark(epdm.getMessContent());
								}
							}
							tmpRelNews.setNodeCd(tmpRel.getExecPlanNodeId() + suspendInt);
							messageViewExecPlanNodeLists.add(tmpRelNews);
						}
					}
					// }
				}
			}
		}
		return messageViewExecPlanNodeLists;
	}

	/**
	 * 每周自动统计计划数据
	 * @param uiid
	 * @param orgCd
	 * @return
	 */
	private List<ExecPlanNode> excePlanInfo(String uiid, String orgCd) {
		List<ExecPlanNode> newExecPlanNodeList = new ArrayList<ExecPlanNode>();
		// 列表出所有节点
		viewPlanNodes = execPlanNodeManager.searchNodes("1", "3", "");
		// 搜索出所对应的所有业态
		execPlanLayouts = execPlanLayoutManager.getLayouts(orgCd, "1", true, false);
		Map<String, String> nodeMap = new HashMap<String, String>();
		// 明细数据
		String[] nodeIds = new String[viewPlanNodes.size()];
		ExecPlanNode n = null;
		for (int i = 0; i < viewPlanNodes.size(); i++) {
			n = viewPlanNodes.get(i);
			nodeIds[i] = n.getExecPlanNodeId();
			// 映射
			nodeMap.put(n.getExecPlanNodeId(), "[" + n.getPointLevel() + "]" + n.getNodeName());
		}

		planDetailMap = new HashMap<String, ExecPlanDetail>();
		String key = null;
		for (ExecPlanDetail d : detailManager.getDetailsByProjPlanNodes(nodeIds, "Export")) {
			key = d.getExecPlanNode().getExecPlanNodeId() + "_" + d.getExecutionPlanCd();
			planDetailMap.put(key, d);
		}
		projects = fetchAllProjects();
		String resOrgName = "";
		if (projects != null) {
			for (WsPlasOrg wo : projects) {
				if (SpringSecurityUtils.getCurrentCenterCd().equals(wo.getOrgCd())) {
					resOrgName = wo.getOrgName();
					break;
				}
			}
		}
		int suspendInt = 0;
		int goingInt = 0;
		for (int i = 0; i < viewPlanNodes.size(); i++) {
			ExecPlanNode tmpRel = viewPlanNodes.get(i);
			String tmpRelId = tmpRel.getExecPlanNodeId();
			String tmpRelDisplayId = tmpRel.getExecPlanNodeId();
			for (int j = 0; j < execPlanLayouts.size(); j++) {
				ExecPlanDetail tmpDetail = new ExecPlanDetail();
				if (StringUtils.isBlank(tmpRelDisplayId)) {
					tmpDetail = planDetailMap.get(tmpRelId + "_" + execPlanLayouts.get(j).getExecutionPlanCd());
				} else {
					tmpDetail = planDetailMap.get(tmpRelDisplayId + "_" + execPlanLayouts.get(j).getExecutionPlanCd());
				}
				if (tmpDetail != null) {
					// if(tmpRel.getSequenceNo().toString().equals("66")){
					if (getPrintStatusCnCover(tmpDetail).equals("已过期")) {
						// 判断当天是否有更新
						if (!formateYYYYWW(tmpDetail.getUpdatedDate()).equals(formateYYYYWW(DateOperator.getDateNow()))) {
							ExecPlanNode tmpRelNews = new ExecPlanNode();
							tmpRelNews.setExecPlanNodeId(tmpRel.getExecPlanNodeId());
							tmpRelNews.setActiveFlg("0");// 临时 该值为已过期状态
							// tmpRelNews.setCorOrgCd(SpringSecurityUtils.getCurrentDeptName());//地产
							tmpRelNews.setCorOrgCd(resOrgName);// 地产
							tmpRelNews.setCorOrgName(tmpDetail.getExecPlanLayout().getExecutionPlanName());// 业态
							tmpRelNews.setCreatedDate(tmpDetail.getScheduleEndDate());// 计划完成时间
							tmpRelNews.setPlanTypeCd(getPrintStatusCnCover(tmpDetail));// 当前状态
							tmpRelNews.setSequenceNo(tmpRel.getSequenceNo());// 序号
							tmpRelNews.setNodeName(tmpRel.getNodeName());// 节点名称
							tmpRelNews.setResOrgName(tmpRel.getResOrgName());// 主责方
							tmpRelNews.setTreeParentNodeId(tmpDetail.getExecPlanLayout().getExecPlanLayoutId());// 业态id
							tmpRelNews.setParentId15(tmpDetail.getExecPlanDetailId());// DetailId
							suspendInt++;
							// 获取留言
							List<ExecPlanDetailMess> epdms = tmpDetail.getExecPlanDetailMesses();
							StringBuffer tip = new StringBuffer();
							DateFormat df = new SimpleDateFormat("MM-dd");
							tip.append("<p style='font-size:14px;'>" + EncodeUtils.htmlEscape(tmpRel.getNodeName()) + "</p>");
							if (epdms.size() > 0) {
								tip
										.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
								int size = epdms.size() >= 3 ? 3 : epdms.size();
								for (int im = 0; im < size; im++) {
									ExecPlanDetailMess epdm = epdms.get(im);
									String userName = CodeNameUtil.getUserNameByCd(epdm.getCreator());
									tip.append("<li><strong>" + userName + "&nbsp;" + df.format(epdm.getCreatedDate()) + "</strong>："
											+ EncodeUtils.htmlEscape(epdm.getMessContent()) + "</li>");
								}
								tip.append("</ul>");
							}
							tmpRelNews.setNodeCd(tmpRel.getExecPlanNodeId() + suspendInt);
							tmpRelNews.setRemark(tip.toString());
							newExecPlanNodeList.add(tmpRelNews);
						} else if (formateYYYYWW(tmpDetail.getUpdatedDate()).equals(formateYYYYWW(DateOperator.getDateNow()))) {
							// 今日留言
							ExecPlanNode tmpRelNews = new ExecPlanNode();
							tmpRelNews.setExecPlanNodeId(tmpRel.getExecPlanNodeId());
							tmpRelNews.setActiveFlg("0");// 临时 该值为已过期状态
							// tmpRelNews.setCorOrgCd(SpringSecurityUtils.getCurrentDeptName());//地产
							tmpRelNews.setCorOrgCd(resOrgName);// 地产
							tmpRelNews.setCorOrgName(tmpDetail.getExecPlanLayout().getExecutionPlanName());// 业态
							tmpRelNews.setCreatedDate(tmpDetail.getScheduleEndDate());// 计划完成时间
							tmpRelNews.setPlanTypeCd(getPrintStatusCnCover(tmpDetail));// 当前状态
							tmpRelNews.setSequenceNo(tmpRel.getSequenceNo());// 序号
							tmpRelNews.setNodeName(tmpRel.getNodeName());// 节点名称
							tmpRelNews.setResOrgName(tmpRel.getResOrgName());// 主责方
							tmpRelNews.setTreeParentNodeId(tmpDetail.getExecPlanLayout().getExecPlanLayoutId());// 业态id
							tmpRelNews.setParentId15(tmpDetail.getExecPlanDetailId());// DetailId
							suspendInt++;
							// 获取留言
							List<ExecPlanDetailMess> epdms = tmpDetail.getExecPlanDetailMesses();
							StringBuffer tip = new StringBuffer();
							DateFormat df = new SimpleDateFormat("MM-dd");
							tip.append("<p style='font-size:14px;'>" + EncodeUtils.htmlEscape(tmpRel.getNodeName()) + "</p>");
							if (epdms.size() > 0) {
								tip
										.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
								int size = epdms.size() >= 1 ? 1 : epdms.size();
								for (int im = 0; im < size; im++) {
									ExecPlanDetailMess epdm = epdms.get(im);
									String userName = CodeNameUtil.getUserNameByCd(epdm.getCreator());
									tip.append("<li><strong>" + userName + "&nbsp;" + df.format(epdm.getCreatedDate()) + "</strong>："
											+ EncodeUtils.htmlEscape(epdm.getMessContent()) + "</li>");
								}
								tip.append("</ul>");
							}
							tmpRelNews.setNodeCd(tmpRel.getExecPlanNodeId() + suspendInt);
							tmpRelNews.setRemark(tip.toString());
							messageViewExecPlanNodeList.add(tmpRelNews);
						}
					}
					if (getPrintStatusCnCover(tmpDetail).equals("进行中")) {
						if (!formateYYYYWW(tmpDetail.getUpdatedDate()).equals(formateYYYYWW(DateOperator.getDateNow()))) {
							ExecPlanNode tmpRelNews = new ExecPlanNode();
							tmpRelNews.setExecPlanNodeId(tmpRel.getExecPlanNodeId());
							tmpRelNews.setActiveFlg("1");// 临时 该值为进行中状态
							// tmpRelNews.setCorOrgCd(SpringSecurityUtils.getCurrentDeptName());//地产
							tmpRelNews.setCorOrgCd(resOrgName);// 地产
							tmpRelNews.setCorOrgName(tmpDetail.getExecPlanLayout().getExecutionPlanName());// 业态
							tmpRelNews.setCreatedDate(tmpDetail.getScheduleEndDate());// 计划完成时间
							tmpRelNews.setPlanTypeCd(getPrintStatusCnCover(tmpDetail));// 当前状态
							tmpRelNews.setSequenceNo(tmpRel.getSequenceNo());// 序号
							tmpRelNews.setNodeName(tmpRel.getNodeName());// 节点名称
							tmpRelNews.setResOrgName(tmpRel.getResOrgName());// 主责方
							tmpRelNews.setTreeParentNodeId(tmpDetail.getExecPlanLayout().getExecPlanLayoutId());// 业态id
							tmpRelNews.setParentId15(tmpDetail.getExecPlanDetailId());// DetailId
							goingInt++;

							// 获取留言
							List<ExecPlanDetailMess> epdms = tmpDetail.getExecPlanDetailMesses();
							StringBuffer tip = new StringBuffer();
							DateFormat df = new SimpleDateFormat("MM-dd");
							DateFormat dft = new SimpleDateFormat("MM-dd");
							boolean update_flag = true;
							tip.append("<p style='font-size:14px;'>" + EncodeUtils.htmlEscape(tmpRel.getNodeName()) + "</p>");
							if (epdms.size() > 0) {
								tip
										.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
								int size = epdms.size() >= 3 ? 3 : epdms.size();
								for (int im = 0; im < size; im++) {
									ExecPlanDetailMess epdm = epdms.get(im);
									String userName = CodeNameUtil.getUserNameByCd(epdm.getCreator());
									tip.append("<li><strong>" + userName + "&nbsp;" + df.format(epdm.getCreatedDate()) + "</strong>："
											+ EncodeUtils.htmlEscape(epdm.getMessContent()) + "</li>");//
									if (dft.format(epdm.getCreatedDate()).equals(dft.format(DateOperator.getDateNow()))) {
										update_flag = false;
									}
								}
								tip.append("</ul>");
							}
							tmpRelNews.setNodeCd(tmpRel.getExecPlanNodeId() + goingInt);
							tmpRelNews.setRemark(tip.toString());
							newExecPlanNodeList.add(tmpRelNews);
						} else if (formateYYYYWW(tmpDetail.getUpdatedDate()).equals(formateYYYYWW(DateOperator.getDateNow()))) {
							// 今日留言
							ExecPlanNode tmpRelNews = new ExecPlanNode();
							tmpRelNews.setExecPlanNodeId(tmpRel.getExecPlanNodeId());
							tmpRelNews.setActiveFlg("0");// 临时 该值为已过期状态
							// tmpRelNews.setCorOrgCd(SpringSecurityUtils.getCurrentDeptName());//地产
							tmpRelNews.setCorOrgCd(resOrgName);// 地产
							tmpRelNews.setCorOrgName(tmpDetail.getExecPlanLayout().getExecutionPlanName());// 业态
							tmpRelNews.setCreatedDate(tmpDetail.getScheduleEndDate());// 计划完成时间
							tmpRelNews.setPlanTypeCd(getPrintStatusCnCover(tmpDetail));// 当前状态
							tmpRelNews.setSequenceNo(tmpRel.getSequenceNo());// 序号
							tmpRelNews.setNodeName(tmpRel.getNodeName());// 节点名称
							tmpRelNews.setResOrgName(tmpRel.getResOrgName());// 主责方
							tmpRelNews.setTreeParentNodeId(tmpDetail.getExecPlanLayout().getExecPlanLayoutId());// 业态id
							tmpRelNews.setParentId15(tmpDetail.getExecPlanDetailId());// DetailId
							suspendInt++;
							// 获取留言
							List<ExecPlanDetailMess> epdms = tmpDetail.getExecPlanDetailMesses();
							StringBuffer tip = new StringBuffer();
							DateFormat df = new SimpleDateFormat("MM-dd");
							tip.append("<p style='font-size:14px;'>" + EncodeUtils.htmlEscape(tmpRel.getNodeName()) + "</p>");
							if (epdms.size() > 0) {
								tip
										.append("<h3 style='margin-top: 10px; font-size: 12px;'>最近回复：</h3><ul style='list-style: none;margin:3px 10px 0px 10px;margin-top: 3px;font-size: 12px;'>");
								int size = epdms.size() >= 1 ? 1 : epdms.size();
								for (int im = 0; im < size; im++) {
									ExecPlanDetailMess epdm = epdms.get(im);
									String userName = CodeNameUtil.getUserNameByCd(epdm.getCreator());
									tip.append("<li><strong>" + userName + "&nbsp;" + df.format(epdm.getCreatedDate()) + "</strong>："
											+ EncodeUtils.htmlEscape(epdm.getMessContent()) + "</li>");
								}
								tip.append("</ul>");
							}
							tmpRelNews.setNodeCd(tmpRel.getExecPlanNodeId() + suspendInt);
							tmpRelNews.setRemark(tip.toString());
							messageViewExecPlanNodeList.add(tmpRelNews);
						}
					}
					// }
				}
			}
		}
		return newExecPlanNodeList;
	}

	/*
	 * 获取单个节点的显示的状态
	 */
	public String getPrintStatusCnCover(ExecPlanDetail pepd) {
		String returnStr = "";
		if (null == pepd || new ExecPlanDetail() == pepd)
			return "";
		if (!pepd.getActiveBl()) {
			returnStr = "未启用";
		} else if ("0".equalsIgnoreCase(pepd.getStatus()) && "0".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			returnStr = "未确认";
		} else if (("0".equalsIgnoreCase(pepd.getStatus()) || "1".equalsIgnoreCase(pepd.getStatus())) && "1".equalsIgnoreCase(pepd.getInfoConfirmedFlg())) {
			if (DateOperator.getDateNow().after(pepd.getScheduleEndDate())) {
				returnStr = "已过期";
			} else {
				returnStr = "进行中";
			}
		} else if ("2".equalsIgnoreCase(pepd.getStatus())) {
			returnStr = "已完成";
		} else if ("3".equalsIgnoreCase(pepd.getStatus())) {
			returnStr = "预完成";
		}
		return returnStr;
	}

	/**
	 * 得到当前月的下一个月
	 */
	private Date getNextDateNow() {
		Calendar calendarIns = Calendar.getInstance();
		calendarIns.add(Calendar.MONTH, 1); // 得到下一个月
		return calendarIns.getTime();
	}

	private String formateDate2YMd(Date date) {
		SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormatter2.format(date);
	}

	private  String formateYYYYWW(Date date){
		 Calendar calendar =  Calendar.getInstance();
		 calendar.setTime(date);
		return calendar.get(Calendar.YEAR)+"-"+calendar.get(Calendar.WEEK_OF_YEAR);
	}
	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public List<WsPlasOrg> getProjects() {
		return projects;
	}

	public void setProjects(List<WsPlasOrg> projects) {
		this.projects = projects;
	}

	public List<ExecPlanLayout> getExecPlanLayouts() {
		return execPlanLayouts;
	}

	public void setExecPlanLayouts(List<ExecPlanLayout> execPlanLayouts) {
		this.execPlanLayouts = execPlanLayouts;
	}

	public List<ExecPlanLayout> getSearchedPlans() {
		return searchedPlans;
	}

	public void setSearchedPlans(List<ExecPlanLayout> searchedPlans) {
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

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
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

	public Map<String, ExecPlanDetail> getPlanDetailMap() {
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

	public ExecPlanDetail getPlanDetailEntity() {
		return planDetailEntity;
	}

	public void setPlanDetailEntity(ExecPlanDetail planDetailEntity) {
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

	public String getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
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

	public boolean isAShowStandard() {
		return aShowStandard;
	}

	public boolean isAConfirmPoint() {
		return aConfirmPoint;
	}

	public boolean isAWritePoint() {
		return aWritePoint;
	}

	public boolean isAAddFlg() {
		return aAddFlg;
	}

	public boolean isAAttention() {
		return aAttention;
	}

	public boolean isAModFlg() {
		return aModFlg;
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

	public List<ExecPlanNode> getViewPlanNodes() {
		return viewPlanNodes;
	}

	public void setViewPlanNodes(List<ExecPlanNode> viewPlanNodes) {
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

	public List<ExecPlanDetailPlus> getListExecPlanDetailPlus() {
		return listExecPlanDetailPlus;
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

	public Map<String, String> getMapTips() {
		return mapTips;
	}

	public Map<String, String> getMapTips2() {
		return mapTips2;
	}

	public Map<String, String> getMapPrintStartDate() {
		return mapPrintStartDate;
	}

	public Map<String, String> getMapPrintEndDate() {
		return mapPrintEndDate;
	}

	public Map<String, String> getMapPrintStartDate1() {
		return mapPrintStartDate1;
	}

	public void setMapPrintStartDate1(Map<String, String> mapPrintStartDate1) {
		this.mapPrintStartDate1 = mapPrintStartDate1;
	}

	public Map<String, String> getMapPrintEndDate1() {
		return mapPrintEndDate1;
	}

	public void setMapPrintEndDate1(Map<String, String> mapPrintEndDate1) {
		this.mapPrintEndDate1 = mapPrintEndDate1;
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

	public Map<String, ExecPlanNode> getParentNodeMap() {
		return parentNodeMap;
	}

	public Map<String, ExecPlanNodeParentVo> getParentNodeVoMap() {
		return parentNodeVoMap;
	}

	public Map<String, String> getMapNodeName() {
		return mapNodeName;
	}

	public Map<String, ExecPlanDetail> getParentDetailMap() {
		return parentDetailMap;
	}

	public boolean getIf_bis() {
		return if_bis;
	}

	public void setIf_bis(boolean ifBis) {
		if_bis = ifBis;
	}

	public int getNow_view_style() {
		return now_view_style;
	}

	public int getNMainSigned() {
		return nMainSigned;
	}

	public int getNMainUnsign() {
		return nMainUnsign;
	}

	public int getNSubSigned() {
		return nSubSigned;
	}

	public int getNSubUnsign() {
		return nSubUnsign;
	}

	public int getNMainIn() {
		return nMainIn;
	}

	public int getNMainUnin() {
		return nMainUnin;
	}

	public int getNSubIn() {
		return nSubIn;
	}

	public int getNSubUnin() {
		return nSubUnin;
	}

	public int getNRecordVersion() {
		return nRecordVersion;
	}

	public void setNow_view_style(int nowViewStyle) {
		now_view_style = nowViewStyle;
	}

	public List<ExecPlanAllVo> getExecPlanAllVoList() {
		return execPlanAllVoList;
	}

	public Map<String, String> getTipMap() {
		return tipMap;
	}

	public Page<ExecPlanNode> getPage() {
		return page;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public boolean isAOnlyCreator() {
		return aOnlyCreator;
	}

	public String getMyUiid() {
		return myUiid;
	}

	public String getNow_year() {
		return now_year;
	}

	public void setNow_year(String nowYear) {
		now_year = nowYear;
	}

	public String getNow_month() {
		return now_month;
	}

	public void setNow_month(String nowMonth) {
		now_month = nowMonth;
	}
	
	

}