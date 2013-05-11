/**
 * 
 */
package com.hhz.ump.web.res;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.lob.ClobImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.DateParser;
import com.hhz.core.utils.PowerUtils;
import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.cache.PlasCacheUtil;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.biz.BizBankAccountManager;
import com.hhz.ump.dao.res.ResAccreditInfoManager;
import com.hhz.ump.dao.res.ResApproveDelayManager;
import com.hhz.ump.dao.res.ResApproveHisManager;
import com.hhz.ump.dao.res.ResApproveInfoManager;
import com.hhz.ump.dao.res.ResApproveMessageManager;
import com.hhz.ump.dao.res.ResApproveNodeManager;
import com.hhz.ump.dao.res.ResApprovePushManager;
import com.hhz.ump.dao.res.ResApproveShareHisManager;
import com.hhz.ump.dao.res.ResApproveShareManager;
import com.hhz.ump.dao.res.ResAuthTypeManager;
import com.hhz.ump.dao.res.ResConditonTypeManager;
import com.hhz.ump.dao.res.ResModuleManager;
import com.hhz.ump.dao.res.ResNodeManager;
import com.hhz.ump.dao.res.ResTypeUserRelManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.res.ResAccreditInfo;
import com.hhz.ump.entity.res.ResApproveContent;
import com.hhz.ump.entity.res.ResApproveDelay;
import com.hhz.ump.entity.res.ResApproveHis;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.entity.res.ResApproveMessage;
import com.hhz.ump.entity.res.ResApproveNode;
import com.hhz.ump.entity.res.ResApprovePush;
import com.hhz.ump.entity.res.ResApproveShare;
import com.hhz.ump.entity.res.ResApproveStep;
import com.hhz.ump.entity.res.ResAuthType;
import com.hhz.ump.entity.res.ResAutoNodeSet;
import com.hhz.ump.entity.res.ResBillTemplet;
import com.hhz.ump.entity.res.ResConditonType;
import com.hhz.ump.entity.res.ResModule;
import com.hhz.ump.entity.res.ResNode;
import com.hhz.ump.entity.res.ResTypeUserRel;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.util.PdCache;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtilRes;
import com.hhz.ump.web.res.ResConditionParser.MatchType;
import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IAutoImport;
import com.hhz.ump.web.res.baseBean.IBeforeProcess;
import com.hhz.ump.web.res.baseBean.ResApproverUser;
import com.hhz.ump.web.res.bean.BizContractItemApp;
import com.hhz.ump.web.res.bean.BizRentContractApp;
import com.hhz.ump.web.res.bean.ContractItemApprove;
import com.hhz.ump.web.res.bean.InviteFileContactOther;
import com.hhz.ump.web.res.bean.QuestionAndAnswerApprove;
import com.hhz.uums.entity.ws.WsPlasUser;

/**
 * 网上审批主要处理Action
 * @author huangj 2010-6-17
 */
@Namespace("/res")
@Results( {
		@Result(name = CrudActionSupport.RELOAD, location = "res-approve-info!load.action", type = "redirect", params = {
				"resAuthTypeCd", "${resAuthTypeCd}", "approveCdSrh",
				"${approveCdSrh}", "selectNodeCd", "${selectNodeCd}",
				"listMode", "${listMode}", "qsCondition", "${qsCondition}",
				"selectOpinion", "${selectOpinion}", "filter_LIKES_statusCd", "${filter_LIKES_statusCd}", "filter_GED_startDate",
				"${filter_GED_startDate}", "filter_LTD_startDate", "${filter_LTD_startDate}",
				"filter_LIKES_landproject", "${filter_LIKES_landproject}", "filter_LIKES_titlename",
				"${filter_LIKES_titlename}", "filter_LIKES_approveUserName", "${filter_LIKES_approveUserName}",
				"filter_LIKES_approveUserCd", "${filter_LIKES_approveUserCd}", "filter_LIKES_userName", "${filter_LIKES_userName}", "filter_LIKES_randomNo", "${filter_LIKES_randomNo}",
				"filter_LIKES_authTypeCd", "${filter_LIKES_authTypeCd}", "filter_LIKES_authTypeName",
				"${filter_LIKES_authTypeName}","moduleTypeCdSrh", "${moduleTypeCdSrh}", "page.pageNo", "${page.pageNo}" }),
		@Result(name = "sameUser", location = "/res/res-approve-info!agree.action", type = "redirect", params = {
				"resAuthTypeCd", "${resAuthTypeCd}", "filter_LIKES_statusCd", "${filter_LIKES_statusCd}",
				"page.pageNo", "${page.pageNo}", "id", "${id}" }),
		@Result(name = "submit", location = "/res/res-approve-info!submit.action", type = "redirect", params = { "id",
				"${resApproveInfoId}" ,"moduleTypeCdSrh", "${moduleTypeCdSrh}"}),
		@Result(name = CrudActionSupport.RELOAD_SUB, location = "res-approve-info!input.action", type = "redirect", params = {
				"resAuthTypeCd", "${resAuthTypeCd}", "id", "${id}" }),
		@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName",
				"is", "contentDisposition", "attachment;filename=${downFileName}.xls" }),
		@Result(name = "say", location = "/WEB-INF/content/res/res-approve-info-say.ftl", type = "freemarker"),
		@Result(name = "approve", location = "/WEB-INF/content/res/res-approve-info-approve.ftl", type = "freemarker") })
public class ResApproveInfoAction extends CrudActionSupport<ResApproveInfo> {
	private static final long serialVersionUID = 5809866035059094476L;

	// 登陆用户发起的审批
	List<ResApproveInfoVO> byMe = new ArrayList<ResApproveInfoVO>();

	// 非登陆用户发起的审批
	List<ResApproveInfoVO> byOther = new ArrayList<ResApproveInfoVO>();
	//我的审批
	List<ResApproveInfoVO> byApprove = new ArrayList<ResApproveInfoVO>();

	// 共享给我的记录
	List<ResApproveInfoVO> byShare = new ArrayList<ResApproveInfoVO>();

	// 推送给我的记录
	List<ResApproveInfoVO> byPush = new ArrayList<ResApproveInfoVO>();
	//存储查询结果
	private Map<String, List<ResApproveInfoVO>> mapResult = new LinkedHashMap<String, List<ResApproveInfoVO>>();

	private ResApproveInfo entity;
	// 当前审批步骤
	private List<ResApproveStep> steps = new ArrayList<ResApproveStep>();
	//节点对应审批人，填写审批人时调用
	private Map<String, String> mapCustomSteps = new LinkedHashMap<String, String>();
	// 节点是否锁定
	private Map<String, Boolean> mapNodeLock = new LinkedHashMap<String, Boolean>();
	//审批几点列表，填写审批人时调用
	private List<ResApproveNode> approveNodes;
	/**
	 * 自己新增的审批步骤
	 */
	private List<ResApproverUser> approverUsers = new ArrayList<ResApproverUser>();

	// 总裁意见拷贝
	private List<AppAttachFile> chiefCopyList;

	// nodeCd , nodeName , 驳回时，供用户选择驳回到哪个节点
	private Map<String, String> mapReturnToNode = new LinkedHashMap<String, String>();
	//合同跟踪人
	private String contFollower;
	/**
	 * 展示模式：正常模式0，审批模式1
	 */
	private String listMode;
	// nodeCd , nodeName ,审批模式： 审批人的节点列表
	private List<ResNode> userNodes = new ArrayList<ResNode>();
	//过滤条件，在审批模式中，界面选择的审批节点；如果进入审批模式，默认选择第一个节点
	private String selectNodeCd;

	// 快速搜索条件
	private String qsCondition;

	// 我的标签
	private Map<String, String> opinions = new LinkedHashMap<String, String>();
	/**
	 * 过滤条件，选择的子选项，默认选择第一个；如：我的记录-完成
	 */
	private String selectOpinion;

	// 网批权限，在审批记录ftl中使用
	private ResPermission permission = new ResPermission();

	@Autowired
	protected ResTypeUserRelManager resTypeUserRelManager;
	@Autowired
	protected ResAccreditInfoManager resAccreditInfoManager;

	@Autowired
	private ResApproveInfoManager resApproveInfoManager;

	@Autowired
	private ResApproveNodeManager resApproveNodeManager;

	@Autowired
	private ResApproveShareManager resApproveShareManager;
	@Autowired
	private ResApproveShareHisManager resApproveShareHisManager;
	@Autowired
	private ResApproveMessageManager resApproveMessageManager;
	@Autowired
	private ResApproveDelayManager resApproveDelayManager;

	@Autowired
	private ResNodeManager resNodeManager;

	@Autowired
	private ResModuleManager resModuleManager;

	@Autowired
	private ResConditonTypeManager resConditonTypeManager;

	@Autowired
	private ResAuthTypeManager resAuthTypeManager;

	@Autowired
	private ResApproveHisManager approveHisManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private AppAttachFileManager appAttachFileManager;

	private List<ResApproveHis> approveHises = new ArrayList<ResApproveHis>();

	@Autowired
	private AppAttachFileManager attachFileManager;

	@Autowired
	private ResApprovePushManager resApprovePushManager;
	@Autowired
	private BizBankAccountManager bizBankAccountManager;

	/**
	 * 最新可用的申请延期记录
	 */
	private ResApproveDelay gbDelay;

	// 正在处理中的节点
	private ResApproveNode gbProcessNode;

	/**
	 * 新增记录时，附件上传使用的临时ID
	 */
	private String entityTmpId;
	//网批实体bean
	private BaseTemplate templateBean;

	// 类别CD
	private String resAuthTypeCd;
	
	private String resModuleCd;
	
	//被共享人CD，用于界面展示以及存储
	private String shareUserCds;
	//被共享人名称，用于界面展示以及存储
	private String shareUserNames;
	//被推送人Cd
	private String pushUserCds;
	//被推送人名称
	private String pushUserNames;
	//高级查询，编号
	private String approveCdSrh;
	//高级查询，发起人
	private String filter_LIKES_userName;

	private String filter_LIKES_userCd;
	//高级查询，项目名称
	private String filter_LIKES_landproject; // 
	//高级查询，标题
	private String filter_LIKES_titlename; // 
	//高级查询，随机码
	private String filter_LIKES_randomNo; // 
	//高级查询，查询号
	private String filter_LIKES_displayNo; // 
	//高级查询，审批人
	private String filter_LIKES_approveUserName;

	private String filter_LIKES_approveUserCd;
	//高级查询，状态
	private String filter_LIKES_statusCd;
	//高级查询，表单分类
	private String filter_LIKES_authTypeName;
	private String filter_LIKES_authTypeCd;
	private String quicksearchValue;// 快速搜索的值
	//判断下一个审批人是否和当前审批人一致
	private boolean isSameApproveUser = false;
	// 是否是我审批
	private String isMyApprove;
	// 是否能回滚
	private String isCanRollback;
	// 是否是授权人
	private String isAcc;
	// 副总裁级别以上可以直接跳过前面步骤的审批；必须是在流程中，并且未审批
	private String isCanGiveMe;
	// 是否共享
	private String isShare;
	//表单是否上线
	private boolean publish = false;
	// 共享人
	private String shareUser;
	//判断是否是‘跟踪人整理’步骤
	private String myMod;
	//用户‘合同条款审批表’等需要根据发起人选中的部门过滤对应审批人的表单，存储选中的节点
	private String myNode;
	//导出Excel数据流
	private InputStream is;
	//导出文件名称
	private String downFileName;

	// 用户点选的排序字段名
	private String selectedOrderBy;

	// 用户点选的排序方式
	private String selectedOrder;
	private List<ResApproveMessage> messages;
	// 审批单显示名字
	private String displayName;
	//模板文件名称
	private String templetFileName;
	//初始化‘合同条件审批表’等需要发起人选择责任部门的表单
	private Map<String, String> mapGroupNodes;
	//选中的表单分类
	private ResAuthType resAuthType;
	//模块路径
	private String modulePath;
	private String moduleTypeCdSrh;// 0-网上审批,1-合理化建议,2-特别审批
	private boolean aboutInOut;// 预算内、预算外
	private boolean aboutHotel;// 与酒店有关
	private boolean aboutTrade;// 与行业有关
	private boolean aboutEstate;// 与商业有关
	private boolean aboutBefore;// 开业前
	private boolean aboutAfter; // 开业后
	
	private Map<Long, String> mapNodesLevelInfo = new LinkedHashMap<Long, String>();

	public boolean isAboutHotel() {
		return aboutHotel;
	}

	public boolean isAboutTrade() {
		return aboutTrade;
	}

	public boolean isAboutEstate() {
		return aboutEstate;
	}

	public boolean isAboutBefore() {
		return aboutBefore;
	}

	public boolean isAboutAfter() {
		return aboutAfter;
	}

	public Map<Long, String> getMapNodesLevelInfo() {
		return mapNodesLevelInfo;
	}

	@Override
	public String delete() throws Exception {
		resApproveInfoManager.deleteResApproveInfo(getId());
		return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		return RELOAD;
	}

	@Override
	public String input() throws Exception {
		try {
			if (entity != null) {
				//判断当前用户是否被指定人员授权
				if (resAccreditInfoManager.isAccreditedByRes(entity.getApproveUserCd())) {
					isAcc = "1";
				}
				isCanGiveMe();
			}
		
		} catch (ObjectNotFoundException e) {
			Struts2Utils.getRequest().setAttribute("error_msg", new String[] { "该记录已经被删除" });
			return "message";
		}
		parserSteps();
		loadMapGroupNodes();
		// getHotels();
		// add by huangbijin 2011-04-01 最近有效的延迟申请记录
		if (entity != null && StringUtils.isNotBlank(entity.getResApproveInfoId())) {
			gbDelay = resApproveDelayManager.latestProcessDlay(entity.getResApproveInfoId());

		}
		return INPUT;
	}

	/**
	 * 取得剩余的小时数
	 * 在input页面中使用
	 * 
	 * @return
	 */
	public String getTimeReduce() {
		String timeValue = null;
		//根据审批节点、审批级别，取得当前步骤的剩余时间
		for (ResApproveNode approveNode : entity.getResApproveNodes()) {
			if (approveNode.getTimeLimit() != null && approveNode.getTimeLimit() > 0) {
				if (StringUtils.equals(entity.getNodeCd(), approveNode.getNodeCd())
						&& approveNode.getApproveLevel().equals(entity.getApproveLevel())) {
					timeValue = getTimeReduceString(approveNode.getTimeLimit(), entity.getLastApproveDate());
					break;
				}
			}
		}
		return timeValue;
	}

	/**
	 * 在列表中使用,会签节点会显示不准确
	 * 
	 * @param timeLimit
	 * @param lastApproveDate
	 * @return
	 */
	public BigDecimal getTimeReduce(Long timeLimit, Date lastApproveDate) {
		//根据传入时限、最近审批时间，判断当前节点剩余时间。
		BigDecimal timeReduce = null;
		if (timeLimit != null && timeLimit > 0) {
			Date now = new Date();
			timeReduce = new BigDecimal(timeLimit - (float) (now.getTime() - lastApproveDate.getTime())
					/ (1000 * 60 * 60));
			timeReduce = timeReduce.setScale(2, BigDecimal.ROUND_UP);
		}
		return timeReduce;
	}

	/**
	 * 在列表中使用,取得剩余时间。
	 * @param timeLimit
	 * @param lastApproveDate
	 * @return
	 */
	public String getTimeReduceString(Long timeLimit, Date lastApproveDate) {
		//根据传入时限、最近审批时间，判断当前节点剩余时间。直接转成字符串，供列表显示
		Long reduceHour = null;
		StringBuffer timeValue = new StringBuffer();
		if (timeLimit!=null){
			Date dTo = DateOperator.addHours(lastApproveDate, timeLimit.intValue());
			Date now = new Date();
			reduceHour = (dTo.getTime() - now.getTime()) / 1000;
			if (reduceHour > 0) {
				timeValue.append("可用时间还剩余:");
			} else {
				timeValue.append("已过期:");
			}
			timeValue.append(DateOperator.getSecond2Time(Math.abs(reduceHour), false, true));
			reduceHour = DateOperator.getHours(now, dTo);
		}
		return timeValue.toString();
	}

	/**
	 * 判断是否有‘给我’按钮权限
	 * @return
	 */
	private ResApproveNode isCanGiveMe() {
		// 副总裁级别以上可以直接跳过前面步骤的审批;必须是在流程中，并且未审批
		// 返回当前用户所处步骤
		ResApproveNode nodeTmp = null;
		String orgCd = SpringSecurityUtils.getCurrentDeptCd();
		if (entity.getStatusCd().equals(ResConstants.RES_APPROVE_STATUS_ING)
				&& orgCd.equals(GlobalConstants.EXEC_ORG_CD)) {
			for (ResApproveNode approveNode : entity.getResApproveNodes()) {
				if (approveNode.getUserCd().equals(SpringSecurityUtils.getCurrentUiid())
						&& !ResConstants.SP_OPTION_AGREE.equals(approveNode.getApproveOptionCd())) {
					isCanGiveMe = "1";
					nodeTmp = approveNode;
					break;
				}
			}
		}
		return nodeTmp;
	}

	// 将审批记录强行给当前用户
	public String giveMe() throws Exception {
		// 当前用户所处步骤
		ResApproveNode nodeTmp = isCanGiveMe();
		if (nodeTmp != null) {
			StringBuffer sbMsg = new StringBuffer(SpringSecurityUtils.getCurrentUserName()).append("强制审批，系统自动跳过");
			resApproveInfoManager.autoPass(entity, nodeTmp, sbMsg.toString());
		}
		resApproveInfoManager.saveProcess(entity, null, templateBean, true);
		return RELOAD_SUB;
	}

	/**
	 * 构造表单树（旧方法，目前没用）
	 * @return
	 */
	public String buildTree() {
		String active = Struts2Utils.getParameter("active");
		String moduleTypeCdTmp = Struts2Utils.getParameter("moduleTypeCd");
//		String isChecked = Struts2Utils.getParameter("isChecked");
		List<ResModule> modules;
		List<ResAuthType> authTypes;
		if (StringUtils.equals(active, "1")) {
			modules = resModuleManager.loadActiveResModule(moduleTypeCdTmp);
			authTypes = resAuthTypeManager.loadActiveResAuthType();
		} else {
			modules = resModuleManager.loadAllModule(moduleTypeCdTmp);
			authTypes = resAuthTypeManager.getAll("sequenceNo", true);
		}
		TreePanelNode node = TreePanelUtilRes.buildProjectModuleTree(modules, authTypes, "表单模板(按权责审批表分类)");
		Struts2Utils.renderJson(node);
		return null;
	}

	/**
	 * 导出Excel
	 * @return
	 * @throws Exception
	 */
	public String exportExcel() throws Exception {
		// List<PropertyFilter> filters = buildPropertyFilters();
		Map<String, Object> pram = new HashMap<String, Object>();
		String hql = buildNormalHql(pram);
		Map<String, Class> mapClazz = new HashMap<String, Class>();
		mapClazz.put("res", ResApproveInfo.class);
		List<ResApproveInfo> result = resApproveInfoManager.findBySql(hql, pram, mapClazz);
		for (ResApproveInfo resApproveInfo : result) {
			resApproveInfoManager.getDao().evict(resApproveInfo);
			resApproveInfo.setNodeCd(getNodeNameByCd(resApproveInfo.getNodeCd()));
			resApproveInfo.setStatusCd(getMapStatus().get(resApproveInfo.getStatusCd()));
			resApproveInfo.setAuthTypeCd(CodeNameUtil.getResAuthTypeNameByCd(resApproveInfo.getAuthTypeCd()));
			resApproveInfo.setApproveUserCd(CodeNameUtil.getUserNameByCd(resApproveInfo.getApproveUserCd()));
			resApproveInfo.setCompleteUser(CodeNameUtil.getUserNameByCd(resApproveInfo.getCompleteUser()));
		}
		Map<String, List<ResApproveInfo>> beans = new HashMap<String, List<ResApproveInfo>>();
		beans.put("result", result);

		is = JXLExcelUtil.initJxlsInputStream(beans, "resResult.xls");

		String fileName = "搜索结果" + DateOperator.formatDate(new Date(), "MMddHHmm");
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}

	/**
	 * 计算指定人员的网批条数，用户手机推送
	 * 
	 * @return
	 * @throws Exception
	 */
	public String countApprove() throws Exception {
		String uiid = Struts2Utils.getParameter("uiid");
		List<ResAccreditInfo> accreditInfos = resAccreditInfoManager.loadAccInfoRes(uiid);
		List<String> lstUiid = PowerUtils.getProptyArray(accreditInfos, "userCd");
		Map<String, Object> param = new HashMap<String, Object>();
		String hql = buildHqlApprovePattern(param, null, lstUiid);
		param.put("userCd", uiid);
		long count = resApproveInfoManager.countSqlResult(hql, param);
		Struts2Utils.renderText(String.valueOf(count));
		return null;
	}

	/**
	 * 网批查询统一入口
	 * @return
	 * @throws Exception
	 */
	public String load() throws Exception {

		System.out.println("----------------------------------------1   :"+resAuthTypeCd);
		// 首次进入时，如果有审批记录则进审批模式
		if (StringUtils.isBlank(listMode)) {
			//取得授权给当前用户的人员，并查询是否有这些人员的审批记录，如果有，进入审批模式
			List<ResAccreditInfo> accreditInfos = resAccreditInfoManager.loadAccInfoRes(SpringSecurityUtils
					.getCurrentUiid());
			List<String> lstUiid = PowerUtils.getProptyArray(accreditInfos, "userCd");
			Map<String, Object> param = new HashMap<String, Object>();
			String hql = buildHqlApprovePattern(param, null, lstUiid);
			long count = resApproveInfoManager.countSqlResult(hql, param);
			if (count > 0) {
				listMode = "1";
			}
		}

		if (StringUtils.equals(listMode, "1"))
			return goApprovePattern();

		if (StringUtils.equals(ResConstants.QUICK_SEARCH_MYDEAL, qsCondition))
			return queryMyDeal();
		else if (StringUtils.equals(ResConstants.QUICK_SEARCH_MYRECO, qsCondition))
			return queryMyReco();
		else if (StringUtils.equals(ResConstants.QUICK_SEARCH_MYDUTY, qsCondition))
			return queryMyDuty();

		return goNormalPattern();
	}

	/**
	 * 普通查询模式
	 * @return
	 * @throws Exception
	 */
	private String goNormalPattern() throws Exception {
		Date start = new Date();
		page.setPageSize(15);
		Map<String, Object> pram = new HashMap<String, Object>();
		// String hql = buildHql(pram);
		// page = resApproveInfoManager.findPage(page, hql, pram);
		String hql = buildNormalHql(pram);
		System.out.println("----------------------------------------2   :");
		System.out.println(hql);
		Iterator it = pram.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			System.out.println(entry.getKey()+"     :"+entry.getValue());
		}
		Map<String, Class> mapClazz = new HashMap<String, Class>();
		mapClazz.put("res", ResApproveInfo.class);
		page = resApproveInfoManager.findPageSql(page, hql, pram, mapClazz);
		System.out.println("----------"+page.getTotalCount());
		Map<String, ResApproveInfoVO> mapByMe = new HashMap<String, ResApproveInfoVO>();
		Map<String, ResApproveInfoVO> mapByOther = new HashMap<String, ResApproveInfoVO>();
		Map<String, ResApproveInfoVO> mapByShare = new HashMap<String, ResApproveInfoVO>();
		Map<String, ResApproveInfoVO> mapByPush = new HashMap<String, ResApproveInfoVO>();
		Map<String, ResApproveInfoVO> mapMyApprove = new HashMap<String, ResApproveInfoVO>();
		String uiid = SpringSecurityUtils.getCurrentUiid();
		for (ResApproveInfo rai : page.getResult()) {
			if (isMyApprove(rai)) {
				// 我的审批
				addToMap(rai, mapMyApprove);
			} else if (rai.getUserCd().equalsIgnoreCase(uiid)) {
				// 我的申请
				addToMap(rai, mapByMe);
			} else if (isPushUser(rai)) {
				// 推送给我
				addToMap(rai, mapByPush);
			} else if (isSharedUser(rai)) {
				// 共享给我
				addToMap(rai, mapByShare);
			} else {
				// 其他审批
				addToMap(rai, mapByOther);
			}
		}

		byMe.addAll(mapByMe.values());
		byOther.addAll(mapByOther.values());
		byApprove.addAll(mapMyApprove.values());
		byShare.addAll(mapByShare.values());
		byPush.addAll(mapByPush.values());

		Collections.sort(byMe);
		Collections.sort(byOther);
		mapResult.put("我的审批", byApprove);
		mapResult.put("我的申请", byMe);
		mapResult.put("我的共享", byShare);
		mapResult.put("我的推送", byPush);
		mapResult.put("其他审批", byOther);
		Date end = new Date();
		logger.info("耗时:" + (end.getTime() - start.getTime()) / 1000d);
		return "load";
	}

	/**
	 * 审批模式
	 */
	private String goApprovePattern() throws Exception {
		Date start = new Date();

		page.setPageSize(15);
		String userCd = SpringSecurityUtils.getCurrentUiid();
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_nodeUser", userCd));
		String nodeHql = "from ResNode rn where rn.nodeUser=? order by rn.sequenceNo";
		// 取得所有节点
		userNodes = resNodeManager.find(nodeHql, userCd);
		ResNode other = new ResNode();
		other.setNodeCd(ResConstants.OTHER_NODE_CD);
		other.setNodeName(ResConstants.OTHER_NODE_NAME);
		other.setNodeUser(userCd);
		// 增加"其他"节点
		userNodes.add(other);

		List<ResAccreditInfo> accreditInfos = resAccreditInfoManager.loadAccInfoRes(SpringSecurityUtils
				.getCurrentUiid());
		List<String> lstUiid = PowerUtils.getProptyArray(accreditInfos, "userCd");
		Map<String, Object> param = new HashMap<String, Object>();

		// 统计每个节点的审批总数
		for (int i = 0; i < userNodes.size(); i++) {
			ResNode node = userNodes.get(i);
			// if(StringUtils.equals(node.getNodeCd(), selectNodeCd)) {
			// continue;
			// }
			String hql = buildHqlApprovePattern(param, node.getNodeCd(), lstUiid);
			long count = resApproveInfoManager.countSqlResult(hql, param);
			if (count == 0) {

				// 当前节点已经审批完
				if (StringUtils.isNotBlank(selectNodeCd) && StringUtils.equals(selectNodeCd, node.getNodeCd())) {
					selectNodeCd = "";
				}

				userNodes.remove(i--);
			} else {
				node.setRemark(String.valueOf(count));
			}
		}

		// 默认选择第一个节点
		if (StringUtils.isBlank(selectNodeCd) && userNodes.size() > 0) {
			selectNodeCd = userNodes.get(0).getNodeCd();
		}

		if (StringUtils.isNotBlank(selectNodeCd)) {
			String hql = buildHqlApprovePattern(param, selectNodeCd, lstUiid);
			Map<String, Class> mapClazz = new HashMap<String, Class>();
			mapClazz.put("res", ResApproveInfo.class);
			page = resApproveInfoManager.findPageSql(page, hql, param, mapClazz);
		} else {
			page.setTotalCount(0);
		}

		Date end = new Date();
		logger.info("耗时:" + (end.getTime() - start.getTime()) / 1000d);
		return "approving";
	}

	/**
	 * 我的历史
	 */
	public String queryMyDeal() {
		filter_LIKES_statusCd = "";
		Date start = new Date();
		page.setPageSize(15);
		Map<String, Object> param = new HashMap<String, Object>();
		//分别查询各个状态的记录数，显示第一个有记录的状态
		for (Iterator<Entry<String, String>> iter = buildDealOpinions().entrySet().iterator(); iter.hasNext();) {
			Map.Entry<String, String> e = iter.next();
			String hql = buildMyDealHql(param, e.getKey());
			long count = resApproveInfoManager.countSqlResult(hql, param);
			if (count > 0) {
				opinions.put(e.getKey(), e.getValue() + "(" + count + ")");
			}
		}

		if (StringUtils.isBlank(selectOpinion) && opinions.size() > 0) {
			selectOpinion = opinions.entrySet().iterator().next().getKey();
		}

		if (StringUtils.isNotBlank(selectOpinion)) {
			String hql = buildMyDealHql(param, selectOpinion);
			Map<String, Class> mapClazz = new HashMap<String, Class>();
			mapClazz.put("res", ResApproveInfo.class);
			page = resApproveInfoManager.findPageSql(page, hql, param, mapClazz);
		} else {
			page.setTotalCount(0);
		}

		Date end = new Date();
		logger.info("耗时:" + (end.getTime() - start.getTime()) / 1000d);
		return "myReco";
	}

	/**
	 * 我的记录
	 */
	public String queryMyReco() {

		filter_LIKES_statusCd = "";
		Date start = new Date();
		page.setPageSize(15);
		Map<String, Object> param = new HashMap<String, Object>();
		//分别查询各个状态的记录数，显示第一个有记录的状态
		for (Iterator<Entry<String, String>> iter = buildRecoOpinions().entrySet().iterator(); iter.hasNext();) {
			Map.Entry<String, String> e = iter.next();
			String hql = buildMyRecoHql(param, e.getKey());
			long count = resApproveInfoManager.countSqlResult(hql, param);
			if (count > 0) {
				opinions.put(e.getKey(), e.getValue() + "(" + count + ")");
			}
		}

		if (StringUtils.isBlank(selectOpinion) && opinions.size() > 0) {
			selectOpinion = opinions.entrySet().iterator().next().getKey();
		}

		if (StringUtils.isNotBlank(selectOpinion)) {
			String hql = buildMyRecoHql(param, selectOpinion);
			Map<String, Class> mapClazz = new HashMap<String, Class>();
			mapClazz.put("res", ResApproveInfo.class);
			page = resApproveInfoManager.findPageSql(page, hql, param, mapClazz);
		} else {
			page.setTotalCount(0);
		}

		Date end = new Date();
		logger.info("耗时:" + (end.getTime() - start.getTime()) / 1000d);
		return "myReco";
	}

	/**
	 * 我的审批
	 */
	public String queryMyDuty() {

		filter_LIKES_statusCd = "";
		Date start = new Date();
		page.setPageSize(15);
		Map<String, Object> param = new HashMap<String, Object>();

		//分别查询各个状态的记录数，显示第一个有记录的状态
		for (Iterator<Entry<String, String>> iter = buildDutyOpinions().entrySet().iterator(); iter.hasNext();) {
			Map.Entry<String, String> e = iter.next();
			String hql = buildMyDutyHql(param, e.getKey());
			long count = resApproveInfoManager.countSqlResult(hql, param);
			if (count > 0) {
				opinions.put(e.getKey(), e.getValue() + "(" + count + ")");
			}
		}

		if (StringUtils.isBlank(selectOpinion) && opinions.size() > 0) {
			selectOpinion = opinions.entrySet().iterator().next().getKey();
		}

		if (StringUtils.isNotBlank(selectOpinion)) {
			String hql = buildMyDutyHql(param, selectOpinion);
			Map<String, Class> mapClazz = new HashMap<String, Class>();
			mapClazz.put("res", ResApproveInfo.class);
			page = resApproveInfoManager.findPageSql(page, hql, param, mapClazz);
		} else {
			page.setTotalCount(0);
		}

		Date end = new Date();
		logger.info("耗时:" + (end.getTime() - start.getTime()) / 1000d);
		return "myReco";
	}

	/**
	 * 我的历史中的节点
	 */
	private Map<String, String> buildDealOpinions() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(ResConstants.RES_APPROVE_STATUS_ENDORSE, "签批");
		map.put(ResConstants.RES_APPROVE_STATUS_BACK, "驳回");
		map.put(ResConstants.RES_APPROVE_STATUS_DELAY, "延期处理");
		map.put(ResConstants.RES_APPROVE_STATUS_SAID, "留言");
		return map;
	}

	/**
	 * 我的记录中的节点
	 */
	private Map<String, String> buildRecoOpinions() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(ResConstants.RES_APPROVE_STATUS_NEW, "新增");
		map.put(ResConstants.RES_APPROVE_STATUS_ING, "审批中");
		map.put(ResConstants.RES_APPROVE_STATUS_FINISHL, "完成");
		map.put(ResConstants.RES_APPROVE_STATUS_BACK, "驳回");
		map.put(ResConstants.RES_APPROVE_STATUS_PUSH, "推送给我");
		return map;
	}

	/**
	 * 我的审批中的节点
	 */
	protected Map<String, String> buildDutyOpinions() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(ResConstants.RES_APPROVE_STATUS_ING, "审批中");
		map.put(ResConstants.RES_APPROVE_STATUS_MYDUTY, "即将审批");
		map.put(ResConstants.RES_APPROVE_STATUS_SHARE, "共享给我");
		map.put(ResConstants.RES_APPROVE_STATUS_ACCT, "其他");
		return map;
	}

	/**
	 * 我的历史HQL
	 */
	private String buildMyDealHql(Map<String, Object> pram, String dealOpinion) {
		StringBuilder hql = new StringBuilder(
				"select res.* from Res_Approve_Info res where 1=1 ");
		wrapConditionSql(hql, pram);
		hql.append(" and res.status_Cd <>:statusCdDel ");
		pram.put("statusCdDel", ResConstants.RES_APPROVE_STATUS_DELETED);

		String userCd = SpringSecurityUtils.getCurrentUiid();
		if (StringUtils.isNotEmpty(filter_LIKES_approveUserCd)) {
			userCd = filter_LIKES_approveUserCd;
		}
		pram.put("userCd", userCd);
		if (!SpringSecurityUtils.hasRole(Constants.A_QZSP_ADMIN)
				&& !SpringSecurityUtils.hasRole(Constants.A_QZSP_V_ADMIN)) {
			// 普通用户：申请人或者审批人为当前登录用户
			pram.put("userCd", SpringSecurityUtils.getCurrentUiid());
		}
		// 签批
		StringBuffer myApprove = new StringBuffer(
				" exists(select 1 from Res_Approve_His ran where res.res_Approve_Info_Id = ran.res_Approve_Info_Id and ran.user_Cd=:userCd and ran.approve_Option_Cd = :myAgree) ");
		// 发言
		StringBuffer mySaid = new StringBuffer(
				" exists(select 1 from Res_Approve_Message ram where res.res_Approve_Info_Id = ram.res_Approve_Info_Id and ram.creator=:userCd )");
		// 驳回
		StringBuffer myBack = new StringBuffer(
				"exists(select 1 from Res_Approve_His ran where res.res_Approve_Info_Id = ran.res_Approve_Info_Id and ran.user_Cd=:userCd and ran.approve_Option_Cd =:myReject) ");
		// 延期
		StringBuffer myDelayDeal = new StringBuffer(
				" exists(select 1 from Res_Approve_Delay rad where res.res_Approve_Info_Id = rad.res_Approve_Info_Id and (rad.approve_User_Cd=:userCd and rad.approve_Date is not null or rad.confirm_User_Cd=:userCd and rad.confirm_Date is not null )) ");

		hql.append(" and ");
		if (StringUtils.equals(dealOpinion, ResConstants.RES_APPROVE_STATUS_ENDORSE)) {
			hql.append(myApprove);
		} else if (StringUtils.equals(dealOpinion, ResConstants.RES_APPROVE_STATUS_BACK)) {
			hql.append(myBack);
		} else if (StringUtils.equals(dealOpinion, ResConstants.RES_APPROVE_STATUS_DELAY)) {
			hql.append(myDelayDeal);
		} else if (StringUtils.equals(dealOpinion, ResConstants.RES_APPROVE_STATUS_SAID)) {
			hql.append(mySaid);
		}

		pram.put("myAgree", ResConstants.SP_OPTION_AGREE);
		pram.put("myReject", ResConstants.SP_OPTION_BACK);

		// order by，默认按到达时间降序显示
		hql.append(" order by ");
		if (StringUtils.isNotEmpty(selectedOrderBy)) {
			hql.append(selectedOrderBy + " " + selectedOrder);
		} else {
			hql.append("res.last_Approve_Date desc ");
		}
		return hql.toString();
	}

	/**
	 * 我的记录HQL
	 */
	private String buildMyRecoHql(Map<String, Object> pram, String recoOpinion) {
		StringBuilder hql = new StringBuilder("select res.* from Res_Approve_Info res where 1=1 ");
		wrapConditionSql(hql, pram);

		// 推送给我
		if (StringUtils.equals(recoOpinion, ResConstants.RES_APPROVE_STATUS_PUSH)) {
			hql.append(" and exists(select 1 from Res_Approve_Push rap where res.res_Approve_Info_Id = rap.res_Approve_Info_Id and rap.user_Cd=:userCd )");
		} else {
			// 新增、审批中、完成、驳回
			hql.append(" and res.status_Cd=:statusCd and res.user_Cd=:userCd");
		}

		pram.put("statusCd", recoOpinion);
		pram.put("userCd", SpringSecurityUtils.getCurrentUiid());

		// order by，默认按到达时间降序显示
		hql.append(" order by ");
		if (StringUtils.isNotEmpty(selectedOrderBy)) {
			hql.append(selectedOrderBy + " " + selectedOrder);
		} else {
			hql.append("last_Approve_Date desc ");
		}
		return hql.toString();
	}

	/**
	 * 我的审批HQL
	 */
	private String buildMyDutyHql(Map<String, Object> pram, String dutyOpinion) {

		StringBuilder hql = new StringBuilder("select res.* from Res_Approve_Info res where 1=1 ");
		boolean hasFilter = wrapConditionSql(hql, pram);
		hql.append(" and res.status_Cd =:statusCd ");
		pram.put("statusCd", ResConstants.RES_APPROVE_STATUS_ING);

		// 当前审批人
		StringBuffer myApprove = new StringBuffer(
				" exists( select 1 from Res_Approve_User u where u.res_Approve_Info_Id = res.res_Approve_Info_Id and u.user_Cd=:userCd )");
		// 即将审批
		StringBuffer myDuty = new StringBuffer(
				" exists(select 1 from Res_Approve_Node node where res.res_Approve_Info_Id = node.res_Approve_Info_Id and node.approve_Option_Cd ='-1' and node.user_Cd=:userCd) ")
				.append("and not  exists( select 1 from Res_Approve_User u where u.res_Approve_Info_Id = res.res_Approve_Info_Id and u.user_Cd=:userCd and u.USER_TYPE_CD in ('0','1') )");

		// 共享给我
		StringBuffer myShare = new StringBuffer(
				" exists(select 1 from Res_Approve_Share ras where res.res_Approve_Info_Id = ras.res_Approve_Info_Id and ras.user_Cd=:userCd )");// and
		// 其他审批(授权人)
		StringBuffer myAcct = new StringBuffer(
				" exists(select 1 from Res_Approve_User u,Res_Accredit_Info a where u.res_Approve_Info_Id = res.res_Approve_Info_Id AND U.USER_CD = A.USER_CD and A.ACC_USER_CD=:userCd )");// and
		// (ras.replied
		// is
		// null
		// or
		// ras.replied=0
		// )

		hql.append(" and ");
		if (StringUtils.equals(dutyOpinion, ResConstants.RES_APPROVE_STATUS_ING)) {
			hql.append(myApprove);
		} else if (StringUtils.equals(dutyOpinion, ResConstants.RES_APPROVE_STATUS_MYDUTY)) {
			hql.append(myDuty);
		} else if (StringUtils.equals(dutyOpinion, ResConstants.RES_APPROVE_STATUS_SHARE)) {
			hql.append(myShare);
		} else if (StringUtils.equals(dutyOpinion, ResConstants.RES_APPROVE_STATUS_ACCT)) {
			hql.append(myAcct);
		}

		String userCd = SpringSecurityUtils.getCurrentUiid();
		pram.put("userCd", userCd);

		// order by，默认按到达时间升序显示
		hql.append(" order by ");
		if (StringUtils.isNotEmpty(selectedOrderBy)) {
			hql.append(selectedOrderBy + " " + selectedOrder);
		} else {
			hql.append("res.LAST_APPROVE_DATE asc ");
		}
		return hql.toString();
	}
	private void initModuleType(StringBuilder hql,Map<String, Object> param){
		hql.append(" and module_Type_Cd=:moduleTypeCd ");
		if (StringUtils.isNotBlank(moduleTypeCdSrh)){
			param.put("moduleTypeCd", moduleTypeCdSrh);
		}else{
			param.put("moduleTypeCd", ResConstants.MODULE_TYPE_CD_RES);
		}
	}
	/**
	 * 审批模式下的HQL
	 */
	private String buildHqlApprovePattern(Map<String, Object> param, String nodeCd, List<String> lstUiid) {
		StringBuilder hql = new StringBuilder("select res.* from ");
		hql.append("( ");
		hql.append("select res.*,1 rec_status from Res_Approve_Info res where ");
		hql.append("exists(");
		hql.append(" select 1 from Res_Approve_User u where u.res_Approve_Info_Id = res.res_Approve_Info_Id and u.user_Cd=:userCd ");
		hql.append(")");
		hql.append("union ");
		hql.append("select res.*,9 rec_status from Res_Approve_Info res where ");
		hql.append("exists(");
		hql.append(" select 1 from Res_Approve_User u,Res_Accredit_Info a where u.res_Approve_Info_Id = res.res_Approve_Info_Id AND U.USER_CD = A.USER_CD and A.ACC_USER_CD=:userCd ");
		hql.append(" )");
		hql.append(") res where 1=1 and res.status_cd = '1' ");
		initModuleType(hql,param);
		// 加入nodeCd条件
		if (StringUtils.isBlank(nodeCd)) {

		} else if (StringUtils.equals(ResConstants.OTHER_NODE_CD, nodeCd)) {
			if (userNodes.size() > 1) {

				hql.append(" and not exists").append(" ( select 1 from Res_Approve_Node node")
						.append(" where res.res_Approve_Info_Id=node.res_Approve_Info_Id")
						.append(" and (res.node_Cd=node.node_Cd or res.node_Cd=node.group_Node_Cd) ")
						.append(" and node.node_Cd in (:nodeCdArr) )");
				String[] nodeCdArr = new String[userNodes.size() - 1];
				for (int i = 0; i < userNodes.size() - 1; i++) {
					ResNode node = userNodes.get(i);
					nodeCdArr[i] = node.getNodeCd();
				}
				param.put("nodeCdArr", nodeCdArr);
			}
		} else {
			hql.append(" and exists").append(" ( select 1 from Res_Approve_Node node")
					.append(" where res.res_Approve_Info_Id=node.res_Approve_Info_Id")
					.append(" and node.node_Cd=:nodeCd")
					.append(" and (res.node_Cd=node.node_Cd or res.node_Cd=node.group_Node_Cd) )");
			param.put("nodeCd", nodeCd);
		}

		String userCd = SpringSecurityUtils.getCurrentUiid();
		param.put("userCd", userCd);
		// --授权人记录搜索--start
		// --授权人记录搜索-end
		// order by，默认按到达时间升序显示
		hql.append(" order by rec_status,");
		if (StringUtils.isNotEmpty(selectedOrderBy)) {
			hql.append(selectedOrderBy + " " + selectedOrder);
		} else {
			hql.append("res.LAST_APPROVE_DATE asc ");
		}
		return hql.toString();
	}

	private void addToMap(ResApproveInfo rai, Map<String, ResApproveInfoVO> map) {
		String statusCd = rai.getStatusCd();
		if (!map.keySet().contains(statusCd)) {
			map.put(statusCd, new ResApproveInfoVO(statusCd));
		}
		map.get(statusCd).add(rai);
	}

	/**
	 * 是否有传入搜索条件
	 * 
	 * @return
	 */
	private boolean hasSearchCondition() {
		boolean hasFilter = false;
		if (StringUtils.isNotBlank(filter_LIKES_statusCd) || StringUtils.isNotBlank(resAuthTypeCd)
				|| StringUtils.isNotBlank(filter_LIKES_authTypeCd) || StringUtils.isNotBlank(filter_LIKES_userCd)
				|| StringUtils.isNotBlank(filter_LIKES_approveUserCd) || StringUtils.isNotBlank(approveCdSrh)
				|| StringUtils.isNotBlank(filter_LIKES_landproject) || StringUtils.isNotBlank(filter_LIKES_titlename)
				|| StringUtils.isNotBlank(filter_LIKES_randomNo) || StringUtils.isNotBlank(filter_LIKES_displayNo)
				|| StringUtils.isNotBlank(quicksearchValue)) {
			hasFilter = true;
		}
		return hasFilter;
	}

	/**
	 * 拼装界面传入‘高级查询’条件
	 * @param hql
	 * @param pram
	 * @return
	 */
	private boolean wrapConditionSql(StringBuilder hql, Map<String, Object> pram) {
		initModuleType(hql,pram);
		boolean hasFilter = false;
		// ----拼装搜索条件--start
		// 选中的审批分类
		if (StringUtils.isNotBlank(resAuthTypeCd)) {
			if(resAuthTypeCd.indexOf(",")>=0){
				hql.append("and res.auth_Type_Cd in ("+resAuthTypeCd+") ");
			}else{
				hql.append("and res.auth_Type_Cd=:authTypeCd ");
				pram.put("authTypeCd", resAuthTypeCd);
			}
			
		}
		if (StringUtils.isNotBlank(filter_LIKES_authTypeCd)) {
			hql.append("and res.auth_Type_Cd=:authTypeCd ");
			pram.put("authTypeCd", filter_LIKES_authTypeCd);
		}
		// 发起人
		if (StringUtils.isNotBlank(filter_LIKES_userCd)) {
			hql.append("and res.user_Cd=:userCdSrh ");
			pram.put("userCdSrh", filter_LIKES_userCd);
			hasFilter = true;
		}
		// 审批人
		if (StringUtils.isNotBlank(filter_LIKES_approveUserCd)) {
			if (StringUtils.equals(filter_LIKES_statusCd, ResConstants.RES_APPROVE_STATUS_ING)
					&& (SpringSecurityUtils.hasRole(Constants.A_QZSP_ADMIN) || SpringSecurityUtils
							.hasRole(Constants.A_QZSP_V_ADMIN))) {
				hql.append("and exists(");
				hql.append(" select 1 from Res_Approve_User u where u.res_Approve_Info_Id = res.res_Approve_Info_Id and u.user_Cd=:userCd ");
				hql.append(")");
				pram.put("userCd", filter_LIKES_approveUserCd);
			}
		}
		// 审批编号
		if (StringUtils.isNotBlank(approveCdSrh)) {
			approveCdSrh = approveCdSrh.trim().toUpperCase();
			hasFilter = true;
			String approveCdTmp = approveCdSrh.substring(0, approveCdSrh.lastIndexOf("_") + 1);
			String serialNoTmpStr = approveCdSrh.substring(approveCdSrh.lastIndexOf("_") + 1);
			serialNoTmpStr = StringUtils.trim(serialNoTmpStr);
			if (NumberUtils.isNumber(serialNoTmpStr)) {
				hql.append("and res.serial_No=:serialNo ");
				pram.put("serialNo", Long.valueOf(serialNoTmpStr));
			} else {
				approveCdTmp = approveCdSrh;
			}
			hql.append("and res.approve_Cd like:approveCd ");
			pram.put("approveCd", "%" + approveCdTmp + "%");
		}

		if (StringUtils.isNotBlank(filter_LIKES_landproject)) {
			hql.append("and res.land_Project like :landProject ");
			pram.put("landProject", "%" + filter_LIKES_landproject + "%");
		}

		if (StringUtils.isNotBlank(filter_LIKES_titlename)) {
			hql.append("and res.title_Name like :titleName ");
			pram.put("titleName", "%" + filter_LIKES_titlename + "%");
		}

		if (StringUtils.isNotBlank(filter_LIKES_randomNo)) {
			hasFilter = true;
			hql.append("and res.random_No=:randomNo ");
			pram.put("randomNo", filter_LIKES_randomNo);
		}
		if (StringUtils.isNotBlank(filter_LIKES_displayNo)) {
			hasFilter = true;
			hql.append("and res.display_No=:displayNo ");
			pram.put("displayNo", filter_LIKES_displayNo);
		}
		if (StringUtils.isNotBlank(quicksearchValue)) {
			quicksearchValue = StringUtils.trim(quicksearchValue);
			hasFilter = true;
			hql.append("and (res.display_No=:displayNo or res.title_name like :titleName or res.approve_Cd like:approveCd  ");

			BigDecimal srhDisplayNo = BigDecimal.ZERO;
			if (Pattern.matches(Constants.PATTERN_NUM, quicksearchValue)) {
				srhDisplayNo = new BigDecimal(quicksearchValue);
			}
			pram.put("displayNo", srhDisplayNo);
			pram.put("titleName", "%" + quicksearchValue + "%");

			String tmpApproveCdSrh = quicksearchValue.trim().toUpperCase();
			String approveCdTmp = tmpApproveCdSrh.substring(0, tmpApproveCdSrh.lastIndexOf("_") + 1);
			String serialNoTmpStr = tmpApproveCdSrh.substring(tmpApproveCdSrh.lastIndexOf("_") + 1);
			serialNoTmpStr = StringUtils.trim(serialNoTmpStr);
			if (NumberUtils.isNumber(serialNoTmpStr)) {
				hql.append("and res.serial_No=:serialNo ");
				pram.put("serialNo", Long.valueOf(serialNoTmpStr));
			} else {
				approveCdTmp = tmpApproveCdSrh;
			}
			pram.put("approveCd", "%" + approveCdTmp + "%");
			hql.append(" ) ");
		}
		// ----拼装搜索条件--end
		return hasFilter;
	}

	// 普通用户语句
	private void buildSelfHql(StringBuilder hql, Map<String, Object> pram) {
		// if (StringUtils.isBlank(filter_LIKES_approveUserCd)){
		hql.append("(");
		hql.append("select res.* from Res_Approve_Info res where ");
		hql.append("res.status_Cd in (:statusCd) ");
		hql.append("and res.user_Cd = :userCd ");
		hql.append(" union ");
		hql.append("select res.* from Res_Approve_Info res where ");
		hql.append("exists(");
		hql.append(" select 1 from Res_Approve_User u where u.res_Approve_Info_Id = res.res_Approve_Info_Id and u.user_Cd=:userCd ");
		hql.append(")");
		hql.append(" union ");
		hql.append("select res.* from Res_Approve_Info res where ");
		hql.append("exists(");
		hql.append(" select 1 from Res_Approve_User u,Res_Accredit_Info a where u.res_Approve_Info_Id = res.res_Approve_Info_Id AND U.USER_CD = A.USER_CD and A.ACC_USER_CD=:userCd ");
		hql.append(")");
		// 设置可以搜索表单
		List<ResTypeUserRel> rels = resTypeUserRelManager.loadMyAuthTypeSrh(SpringSecurityUtils.getCurrentUiid());
		if (rels.size() > 0) {
			hql.append(" union ");
			hql.append("select res.* from Res_Approve_Info res where ");
			hql.append("res.status_Cd in (:statusCd) ");
			hql.append("and res.auth_Type_Cd in (:authTypeCds) ");
			List<String> lstAuthTypeCd = PowerUtils.getProptyArray(rels, "authTypeCd");
			String[] authTypeCds = new String[lstAuthTypeCd.size()];
			lstAuthTypeCd.toArray(authTypeCds);
			pram.put("authTypeCds", authTypeCds);
		}
		
		if (SpringSecurityUtils.hasRole(Constants.A_QZSP_V_ADMIN)) {
			// 项目/中心管理员，可以搜索本项目/中心的数据
			String curCenterCd = getCurCenterCd();
			if (StringUtils.isNotEmpty(curCenterCd)) {
				hql.append(" union ");
				hql.append("select res.* from RES_APPROVE_INFO res where res.status_Cd in (:statusCd) ");
				hql.append("and res.land_Project_Cd=:curCenterCd ");
				pram.put("curCenterCd", curCenterCd);
				pram.put("statusCd", new Object[] { ResConstants.RES_APPROVE_STATUS_ING });
			}
		}
		
		hql.append(")res where 1=1 ");
		// }else{
		// hql.append(" Res_Approve_Info res where res.user_cd=:userCd ");
		// }
	}

	// 已经手
	private void buildYjsHql(StringBuilder hql, Map<String, Object> pram) {
		hql.append("(");
		// 我审批过
		hql.append("select res.* from Res_Approve_Info res where ");
		hql.append("res.status_Cd in (:statusCd) ");
		hql.append("and exists(select 1 from res_approve_his h where h.res_Approve_Info_Id = res.res_Approve_Info_Id and h.user_cd=:userCd and h.approve_Option_Cd in (:myAgree,:myReject) )");
		// 我审批延期过
		hql.append("union ");
		hql.append("select res.* from Res_Approve_Info res where ");
		hql.append("res.status_Cd in (:statusCd) ");
		hql.append("and exists(");
		hql.append(" select 1 from Res_Approve_Delay d where d.res_Approve_Info_Id = res.res_Approve_Info_Id and (d.approve_User_Cd=:userCd AND d.approve_Date IS NOT NULL or d.confirm_User_Cd = :userCd and d.confirm_Date IS NOT NULL)");
		hql.append(")");
		// 当前我审批
		hql.append(" union ");
		hql.append("select res.* from Res_Approve_Info res where ");
		hql.append("exists(");
		hql.append(" select 1 from Res_Approve_User u where u.res_Approve_Info_Id = res.res_Approve_Info_Id and u.user_Cd=:userCd ");
		hql.append(")");
		// 我发起
		hql.append(" union ");
		hql.append("select res.* from Res_Approve_Info res where ");
		hql.append("res.status_Cd in (:statusCd) and res.user_Cd = :userCd ");
		// 我留言过
		hql.append("union ");
		hql.append("select res.* from Res_Approve_Info res where ");
		hql.append("res.status_Cd in (:statusCd) ");
		hql.append("and exists(");
		hql.append(" select 1 from Res_Approve_Message m where m.res_Approve_Info_Id = res.res_Approve_Info_Id AND m.creator=:userCd ");
		hql.append(")");
		// 推送给我
		hql.append("union ");
		hql.append("select res.* from Res_Approve_Info res where ");
		hql.append("res.status_Cd in (:statusCd) ");
		hql.append("and exists(");
		hql.append(" select 1 from Res_Approve_push m where m.res_Approve_Info_Id = res.res_Approve_Info_Id AND m.user_Cd=:userCd ");
		hql.append(")");
		// 设置可以搜索表单
		List<ResTypeUserRel> rels = resTypeUserRelManager.loadMyAuthTypeSrh(SpringSecurityUtils.getCurrentUiid());
		if (rels.size() > 0) {
			hql.append(" union ");
			hql.append("select res.* from Res_Approve_Info res where ");
			hql.append("res.status_Cd in (:statusCd) ");
			hql.append("and res.auth_Type_Cd in (:authTypeCds) ");
			List<String> lstAuthTypeCd = PowerUtils.getProptyArray(rels, "authTypeCd");
			String[] authTypeCds = new String[lstAuthTypeCd.size()];
			lstAuthTypeCd.toArray(authTypeCds);
			pram.put("authTypeCds", authTypeCds);
		}
		hql.append(")res where 1=1 ");
	}

	// 普通搜索语句，普通模式
	private String buildNormalHql(Map<String, Object> pram) {
		StringBuffer myApprove = new StringBuffer(
				" exists(select 1 from Res_Approve_His ran where res.res_Approve_Info_Id = ran.res_Approve_Info_Id and ran.user_Cd=:userCd and ran.approve_Option_Cd in (:myAgree,:myReject)) ");
		StringBuffer myDuty = new StringBuffer(
				" exists(select 1 from Res_Approve_Node node where res.res_Approve_Info_Id = node.res_Approve_Info_Id and node.approve_Option_Cd ='-1' and node.user_Cd=:userCd) ");
		StringBuffer mySaid = new StringBuffer(
				" exists(select 1 from Res_Approve_Message ram where res.res_Approve_Info_Id = ram.res_Approve_Info_Id and ram.creator=:userCd )");
		StringBuffer myShare = new StringBuffer(
				" exists(select 1 from Res_Approve_Share ras where res.res_Approve_Info_Id = ras.res_Approve_Info_Id and ras.user_Cd=:userCd )");// and
		StringBuffer myPush = new StringBuffer(
				"exists(select 1 from Res_Approve_Push rap where res.res_Approve_Info_Id = rap.res_Approve_Info_Id and rap.user_Cd=:userCd )");
		StringBuffer myBack = new StringBuffer(
				"exists(select 1 from Res_Approve_His ran where res.res_Approve_Info_Id = ran.res_Approve_Info_Id and ran.user_Cd=:userCd and ran.approve_Option_Cd =:myReject) ");

		StringBuilder hql = new StringBuilder("select res.* from ");
		String userCd = SpringSecurityUtils.getCurrentUiid();
		// 普通用户
		boolean isCommonUser = !SpringSecurityUtils.hasRole(Constants.A_QZSP_ADMIN);
		if (!isCommonUser && StringUtils.isNotEmpty(filter_LIKES_approveUserCd)) {
			userCd = filter_LIKES_approveUserCd;
		}
		Object[] statusCds = new Object[] { ResConstants.RES_APPROVE_STATUS_NEW, ResConstants.RES_APPROVE_STATUS_ING,
				ResConstants.RES_APPROVE_STATUS_BACK };
		Object[] statusCdsAll = new Object[] { ResConstants.RES_APPROVE_STATUS_NEW,
				ResConstants.RES_APPROVE_STATUS_ING, ResConstants.RES_APPROVE_STATUS_BACK,
				ResConstants.RES_APPROVE_STATUS_FINISHL };

		pram.put("statusCd", statusCds);
		pram.put("userCd", userCd);
		boolean hasFilter = hasSearchCondition();
		if (StringUtils.isNotBlank(filter_LIKES_approveUserCd) && StringUtils.isBlank(filter_LIKES_statusCd)) {
			// 审批人不为空，状态为空，搜索出所有与审批人有关的记录
			hql.append(" RES_APPROVE_INFO res where");
			hql.append(myApprove);
		} else if (hasFilter && isCommonUser || ResConstants.RES_APPROVE_STATUS_MYDEAL.equals(filter_LIKES_statusCd)) {// 已经手
			pram.put("statusCd", statusCdsAll);
			buildYjsHql(hql, pram);
		} else {
			if (isCommonUser) {
				// 普通用户：申请人或者审批人为当前登录用户
				// if (!RES_APPROVE_STATUS_ING.equals(filter_LIKES_statusCd)){
				// hql.append(" RES_APPROVE_INFO res where res.status_Cd in (:statusCd) and res.user_cd =:curUserCd");
				// }else{
				buildSelfHql(hql, pram);
				// }

			} else if (SpringSecurityUtils.hasRole(Constants.A_QZSP_ADMIN)) {
				if (!ResConstants.RES_APPROVE_STATUS_MYDEAL.equals(filter_LIKES_statusCd)) {// 管理员，非"已经手"
					hql.append(" RES_APPROVE_INFO res where res.status_Cd in (:statusCd) ");
					pram.put("statusCd", new Object[] { ResConstants.RES_APPROVE_STATUS_ING });
				} else {

				}
			}
		}
		// 界面传入的搜索条件
		wrapConditionSql(hql, pram);
		if (hasFilter) {
			pram.put("statusCd", statusCdsAll);
		}
		pram.put("myAgree", ResConstants.SP_OPTION_AGREE);
		pram.put("myReject", ResConstants.SP_OPTION_BACK);
		if (StringUtils.isNotBlank(filter_LIKES_statusCd)
				&& !ResConstants.RES_APPROVE_STATUS_MYDEAL.equals(filter_LIKES_statusCd)) {
			StringBuffer sb = new StringBuffer();
			sb.append(" and (");
			if (ResConstants.RES_APPROVE_STATUS_MYDUTY.equals(filter_LIKES_statusCd)) { // "即将审批"
				// 我需要审批但目前还没到我审批的（即将审批）
				sb.append(myDuty);
				// sb.append(" and  res.status_Cd = '1' ");
				pram.put("statusCd", new Object[] { ResConstants.RES_APPROVE_STATUS_ING });
			} else if (ResConstants.RES_APPROVE_STATUS_SAID.equals(filter_LIKES_statusCd)) { // "我留言过"
				// 我留言过的记录
				pram.put("statusCd", statusCdsAll);
				sb.append(mySaid);
			} else if (ResConstants.RES_APPROVE_STATUS_NEW.equals(filter_LIKES_statusCd)) { // "我新增"
				// 我新增的记录
				pram.put("statusCd", new Object[] { filter_LIKES_statusCd });
				if (!isCommonUser) {
					// 管理员
					if (StringUtils.isNotBlank(filter_LIKES_approveUserCd)) {
						sb.append(" res.user_cd=:userCd ");
					} else {
						sb.append(" 1=1 ");
					}
				} else {
					sb.append(" res.user_cd=:userCd ");
				}
				// sb.append(" 1=1 ");
			} else if (ResConstants.RES_APPROVE_STATUS_SHARE.equals(filter_LIKES_statusCd)) { // "共享给我"
				// 共享给我,当前审批中的共享记录
				sb.append(myShare);
			} else if (ResConstants.RES_APPROVE_STATUS_PUSH.equals(filter_LIKES_statusCd)) { // "推送给我"
				// 推送给我
				pram.put("statusCd", statusCdsAll);
				sb.append(myPush);
			} else if (ResConstants.RES_APPROVE_STATUS_BACK.equals(filter_LIKES_statusCd)) {// 驳回
				// 我驳回

				if (!isCommonUser && StringUtils.isEmpty(filter_LIKES_approveUserCd)) {
					pram.put("statusCd", new Object[] { filter_LIKES_statusCd });
					sb.append(" 1=1 ");
				} else {
					pram.put("statusCd", statusCdsAll);
					sb.append(myBack);
				}
			} else if (ResConstants.RES_APPROVE_STATUS_FINISHL.equals(filter_LIKES_statusCd)) {
				sb.append(" res.status_Cd=:statusCd ");
				pram.put("statusCd", filter_LIKES_statusCd);
				if (StringUtils.isNotBlank(filter_LIKES_approveUserCd)) {
					// 我完成
					sb.append("and res.complete_User=:userCd ");
				} else if (isCommonUser) {
					// sb.append(" and res.user_cd=:userCd ");
				}
			} else if (ResConstants.RES_APPROVE_STATUS_DELAY.equals(filter_LIKES_statusCd)) { // "延期申请"
				// 延期申请
				sb.append("res.delay_Approve_User is not null ");
			} else if (ResConstants.RES_APPROVE_STATUS_ING.equals(filter_LIKES_statusCd)) { // 审批中
				sb.append("res.status_Cd=:statusCd ");
				pram.put("statusCd", filter_LIKES_statusCd);
			}
			sb.append(") ");
			hql.append(sb);
		}
		hql.append(" and res.status_Cd in (:statusCdAll) ");
		pram.put("statusCdAll", statusCdsAll);
		StringBuffer o_case = new StringBuffer();
		o_case.append("(case when ");
		o_case.append("exists(");
		o_case.append(" select 1 from Res_Approve_User u where u.res_Approve_Info_Id = res.res_Approve_Info_Id and u.user_Cd=:userCd ");
		o_case.append(")");
		o_case.append(" then 1 when USER_CD =:userCd then 2 ");
		// o_case.append("when ").append(myShare).append(" then 3 ");
		// o_case.append("when ").append(myPush).append(" then 4 ");
		o_case.append("else 5 end) asc ");
		StringBuffer o_customer = null;
		if (StringUtils.isNotEmpty(selectedOrderBy)) {
			o_customer = new StringBuffer();
			o_customer.append(selectedOrderBy + " " + selectedOrder);
		}
		StringBuffer o_statusCd = new StringBuffer(" STATUS_CD asc ");
		StringBuffer o_completeDate = new StringBuffer(" COMPLETE_DATE desc");
		// 若搜索"完成"记录,则将按完成时间降序",completeDate
		hql.append(" order by ");
		if (ResConstants.RES_APPROVE_STATUS_FINISHL.equals(filter_LIKES_statusCd)) {
			if (StringUtils.isEmpty(selectedOrderBy)) {
				hql.append(o_case);
				// hql.append(",").append(o_statusCd);
				hql.append(",").append(o_completeDate);
			} else {
				if (selectedOrderBy.equalsIgnoreCase("APPROVE_USER_CD") || selectedOrderBy.equalsIgnoreCase("USER_CD")) {
					hql.append(o_customer).append(",").append(o_case).append(",").append(o_completeDate);
				} else {
					hql.append(o_case).append(",").append(o_customer).append(",").append(o_completeDate);
				}
			}
		}
		// 否则,startDate
		else {
			if (StringUtils.isEmpty(selectedOrderBy)) {
				hql.append(o_case);
				// hql.append(",").append(o_statusCd);
			} else {
				if (selectedOrderBy.equalsIgnoreCase("APPROVE_USER_CD") || selectedOrderBy.equalsIgnoreCase("USER_CD")) {
					hql.append(o_customer).append(",").append(o_case);
				} else {
					hql.append(o_case).append(",").append(o_customer);
				}
			}
			hql.append(" , LAST_APPROVE_DATE asc ");
		}
		System.out.println("----------------------------------------3   :");
		System.out.println(hql);
		
		return hql.toString();
	}

	/**
	 * 取得当前用户所在中心
	 * @return
	 */
	private String getCurCenterCd() {
		String curCenterCd = SpringSecurityUtils.getCurrentCenterCd();
		if (Constants.ORG_CD_DCGS.equals(curCenterCd)) {
			curCenterCd = SpringSecurityUtils.getCurrentCenterCd();
		}
		return curCenterCd;
	}

	@Override
	public String list() throws Exception {
		return SUCCESS;
	}

	public String viewSteps() throws Exception {
		if (StringUtils.isNotEmpty(resAuthTypeCd)) {
			resAuthType = resAuthTypeManager.getResAuthTypeByCd(resAuthTypeCd);
		}
		return "steps";
	}

	/**
	 * 判断当前记录是否是我审批
	 * @param approveInfo
	 * @return
	 */
	private boolean isMyApprove(ResApproveInfo approveInfo) {
		boolean flag = false;
		if (approveInfo != null) {
			if (StringUtils.equals(approveInfo.getApproveUserCd(), SpringSecurityUtils.getCurrentUiid())) {
				flag = true;
			}
			if (approveInfo.getApproveUserCd() != null
					&& approveInfo.getApproveUserCd().contains(SpringSecurityUtils.getCurrentUiid() + ";")) {
				flag = true;
			}
			//新增二级借点判断
			if(approveInfo.getApproveUserCd2()!=null
					&& approveInfo.getApproveUserCd2().contains(SpringSecurityUtils.getCurrentUiid())) {
				flag = true;

			}
			System.out.println(flag +"....."+SpringSecurityUtils.getCurrentUiid()+"..."+approveInfo.getApproveUserCd2());
//			//新增一二级判断,暂时不用，因为二级节点时，一级也有写值，上面2个条件还是满足。
//			List<ResApproveNode> approveNode = approveInfo.getResApproveNodes();
//			for(ResApproveNode node:approveNode){
//				if(node.getApproveRank()==DictContants.RES_APPROVE_RANK_ONE&&StringUtils.endsWith(approveInfo.getApproveUserCd(), SpringSecurityUtils.getCurrentUiid())){
//		            flag =true;
//				    break;
//				}
//				if(node.getApproveRank()==DictContants.RES_APPROVE_RANK_TWO&&()
//				
//			}
//			ResApproveNode nodeFirst = getFirstApproveNode(approveNode);
////			if (null != nodeFirst) {
////				if (StringUtils.equals(approveInfo.getNodeCd(), nodeFirst.getNodeCd())&&nodeFirst.getApproveRank()==) {
////                     
////				}
////			}
			
		} else {
			logger.warn("ResApproveInfo is null");
		}
		return flag;
	}

	/**
	 * 初始化一些状态值
	 */
	private void genBtnStatus() {
		boolean isGroup = false;
	
		if (isMyApprove(entity)) {
			isMyApprove = "1";
		}
 
		// 当前是否是会签节点
		ResNode resNode = resNodeManager.getNodeByCd(entity.getNodeCd());
		if (resNode != null && "1".equals(resNode.getNodeTypeCd()) && !isLastNode()) {
			isGroup = true;
		}
		if (StringUtils.equals(isMyApprove, "1") && isGroup) {
			// 是否是会签人员
			myMod = ResConstants.SP_MODIFY_MOD_HQ;
		}
		if (ResConstants.SP_NODE_CD_SZL.equals(entity.getNodeCd())) {
			// 是否是发起人整理
			myMod = ResConstants.SP_MODIFY_MOD_FQ;
		}
		
	}
	/**
	 * 根据表单内容，过滤审批节点
	 */
	private void initIsMyApprove() {
		genBtnStatus();
		// 生成我的审批节点,用于在审批过程中需要指定部门审批的表单
		StringBuilder sb = new StringBuilder(";");
		for (ResApproveNode approveNode : entity.getResApproveNodes()) {
			if (StringUtils.equals(approveNode.getUserCd(), SpringSecurityUtils.getCurrentUiid())) {
				sb.append(approveNode.getNodeCd()).append(";");
			}
		}
		myNode = sb.toString();
	}

	/**
	 * 是否是最后一个节点
	 * 
	 * @return
	 */
	private boolean isLastNode() {
		boolean flag = false;
		ResApproveNode lastNode = null;
		for (ResApproveNode approveNodeTmp : entity.getResApproveNodes()) {
			if (lastNode == null) {
				lastNode = approveNodeTmp;
			} else {
				if (approveNodeTmp.getApproveLevel() > lastNode.getApproveLevel()) {
					lastNode = approveNodeTmp;
				}
			}
		}
		if (lastNode != null
				&& (lastNode.getNodeCd().equals(entity.getNodeCd()) || StringUtils.equals(lastNode.getGroupNodeCd(),
						entity.getNodeCd()))) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 例外事项审批单，审批人员步骤
	 */
	private void parserSteps() {
		if (templateBean != null) {
			//selfStep和selfStepName是旧属性，目前用approverUsers代替
			String strSelfSteps = templateBean.getSelfStep();
			String strSelfStepsNames = templateBean.getSelfStepName();
			if (strSelfSteps != null && strSelfStepsNames != null) {
				String[] steps_array = strSelfSteps.split(",");
				String[] stepNames = strSelfStepsNames.split(",");
				//初始化旧的审批人
				for (int i = 0; i < steps_array.length; i++) {
					String strStep = StringUtils.trim(steps_array[i]);
					String strStepName = StringUtils.trim(stepNames[i]);
					if (StringUtils.isNotEmpty(strStep)) {
						approverUsers.add(new ResApproverUser(strStep, strStepName));
					}
				}
			}
			//添加传入的自定义审批人
			approverUsers.addAll(templateBean.getApproverUsers());
		}

	}

	/**
	 * 根据分类CD+条件CD，初始化自定义审批人的步骤，以及默认的审批人员；存在mapCustomSteps中；在填写审批时调用
	 * @param authTypeCd
	 * @param conditionCd
	 */
	private void initCustomStep(String authTypeCd, String conditionCd) {
		Set<String> customSteps = new LinkedHashSet<String>();
		ResConditonType conditonType = resConditonTypeManager.getConditionByCd(authTypeCd, conditionCd);
		List<ResApproveStep> resApproveSteps = conditonType.getResApproveSteps();
		for (ResApproveStep resApproveStep : resApproveSteps) {
			if (ResConstants.allCustomNode.contains(resApproveStep.getNodeCd())) {
				customSteps.add(resApproveStep.getNodeCd());
			}
		}
		// 额外节点
		resAuthType = resAuthTypeManager.getResAuthTypeByCd(authTypeCd);
		List<ResAutoNodeSet> autoNodeSets = resAuthType.getResAutoNodeSets();
		for (ResAutoNodeSet autoNodeSet : autoNodeSets) {
			if (autoNodeSet.getActive() == null || autoNodeSet.getActive()) {
				if (ResConstants.allCustomNode.contains(autoNodeSet.getExtraNodeCd())
						&& StringUtils.isBlank(autoNodeSet.getExtraUserCd())) {
					customSteps.add(autoNodeSet.getExtraNodeCd());
				}
			}
		}
		for (String nodeCd : customSteps) {
			setCustomValue(nodeCd);
		}
	}

	/**
	 * 设置指定节点的默认审批人，用于填写审批人时显示。
	 * @param nodeCd
	 */
	private void setCustomValue(String nodeCd) {
		Set<String> uiids = PlasCacheUtil.getResUiidByNodeCd(nodeCd);
		String strUiids = PowerUtils.array2String(uiids);
		mapCustomSteps.put(nodeCd, strUiids);
	}

	/**
	 * 搜索共享信息
	 */
	private void initShareInfo() {
		List<ResApproveShare> shares = entity.getResApproveShares();
		StringBuffer sbShareCd = new StringBuffer();
		StringBuffer sbShareName = new StringBuffer();
		for (ResApproveShare shareTmp : shares) {
			if (shareTmp.getCreator().equalsIgnoreCase(SpringSecurityUtils.getCurrentUiid())) {
				sbShareCd.append(shareTmp.getUserCd()).append(";");
				sbShareName.append(shareTmp.getUserName()).append(";");
			}
		}
		shareUserCds = sbShareCd.toString();
		shareUserNames = sbShareName.toString();
	}

	/**
	 * 搜索推送信息
	 */
	private void initPushInfo() {
		StringBuffer sbPushCd = new StringBuffer();
		StringBuffer sbPushName = new StringBuffer();
		for (ResApprovePush pushTmp : entity.getResApprovePushs()) {
			sbPushCd.append(pushTmp.getUserCd()).append(";");
			sbPushName.append(pushTmp.getUserName()).append(";");
		}
		pushUserCds = sbPushCd.toString();
		pushUserNames = sbPushName.toString();
	}

	/**
	 * 初始化当前用户的权限
	 */
	private void initPermission() {
		if (SpringSecurityUtils.hasRole("A_QZSP_ADMIN")) {
			permission.setQzspAdmin(true);
		}
		if (SpringSecurityUtils.hasRole("A_ADMIN")) {
			permission.setAdmin(true);
		}
		if (SpringSecurityUtils.hasRole("A_RES_BACK2LAST")) {
			permission.setBack2Las(true);
		}
		if (SpringSecurityUtils.hasRole("A_QZSP_MSG")) {
			permission.setQzspMsg(true);
		}
		if (SpringSecurityUtils.hasRole("A_RES_PERROR")) {
			permission.setResPerror(true);
		}
		if (SpringSecurityUtils.hasRole("A_RES_TOMEETING")) {
			permission.setResTomeeting(true);
		}
		if (SpringSecurityUtils.hasRole("A_RES_TOCEO")) {
			permission.setResToceo(true);
		}
		if (SpringSecurityUtils.hasRole("A_RES_TOPRESIDENT")) {
			permission.setResTopresident(true);
		}
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = resApproveInfoManager.getEntity(getId());
			resAuthType = resAuthTypeManager.getResAuthTypeByCd(entity.getAuthTypeCd());
			ResBillTemplet templet = PdCache.getRefEntity(ResBillTemplet.class, "templetCd", entity.getTempletCd());// resBillTempletManager.getTempletByCd(entity.getTempletCd());
			if (templet != null) {
				templetFileName = templet.getTempletFileName();
				templateBean = ResXmlParser.parserXml(entity, templet.getEntityName());
				if (entity.getStatusCd().equals(ResConstants.RES_APPROVE_STATUS_ING)) {
					initShareInfo();
					isSharedUser(entity);
					isCanRollback = isCanRollback() ? "1" : "0";
					initIsMyApprove();
				}
				if (entity.getStatusCd().equals(ResConstants.RES_APPROVE_STATUS_FINISHL)) {
					initPushInfo();
				}
				contFollower = templateBean.getMapCustomNode().get(ResConstants.SP_NODE_CD_GZR);// 合同跟踪人templateBean.getContFollower();
			}
		} else {
			if (StringUtils.isNotEmpty(resAuthTypeCd)) {
				resAuthType = resAuthTypeManager.getResAuthTypeByCd(resAuthTypeCd);
			}
			// 初始化审批数据
			if (resAuthType != null) {
				entity = new ResApproveInfo();
				entity.setModuleTypeCd(resAuthType.getResModule().getModuleTypeCd());
				entity.setTempletCd(resAuthType.getTempletCd());
				entity.setAuthTypeCd(resAuthType.getAuthTypeCd());
				entity.setUserCd(SpringSecurityUtils.getCurrentUiid());
				entity.setUserName(CodeNameUtil.getUserNameByCd(SpringSecurityUtils.getCurrentUiid()));
				// 初始状态0-新增
				entity.setStatusCd(ResConstants.RES_APPROVE_STATUS_NEW);
				// 默认延期状态0：无延期
				entity.setDelayStatusCd(ResConstants.DELAY_STATUS_CD_NORMAL);
				entity.setIsImported(false);
				entity.setIsSync(false);
				entity.setCreatedDeptCd(getCurCenterCd());
				ResBillTemplet templet = PdCache.getRefEntity(ResBillTemplet.class, "templetCd", entity.getTempletCd());// resBillTempletManager.getTempletByCd(entity.getTempletCd());
				templetFileName = templet.getTempletFileName();
				if (StringUtils.isNotBlank(templet.getEntityName())) {
					templateBean = (BaseTemplate) Class.forName(templet.getEntityName()).newInstance();
//					Map prop=new HashMap();
//					prop.put("test", String.class);
//					DynamicBean bean=new DynamicBean((Class<? extends BaseTemplate>)Class.forName(templet.getEntityName()),prop);
//					bean.setValue("test", "test001");
//					templateBean = bean.getObject();
				}
				
				entityTmpId = RandomUtils.generateTmpEntityId();
				initFixedUsers();
			}
		}
		if (resAuthType != null) {
			if (entity.getStatusCd().equals(ResConstants.RES_APPROVE_STATUS_NEW)
					|| entity.getStatusCd().equals(ResConstants.RES_APPROVE_STATUS_BACK)) {
				// TODO 改成提交后填写审批人
				// initCustomStep();
			}
			displayName = resAuthType.getDisplayName();
			publish = resAuthType.getPublish();
			initModulePath(resAuthType.getResModule().getResModuleId());
			initAbout();
		}
		// resApproveInfoManager.saveXml2Table();
	}

	/**
	 * 初始化固定审批人
	 */
	private void initFixedUsers() {
		if (entity != null) {
			if (ArrayUtils.contains(ResConstants.MANUAL_STEP_TEMP, entity.getTempletCd())) {
				for (ResConditonType conditonType : resAuthType.getResConditonTypes()) {
					for (ResApproveStep approveStep : conditonType.getResApproveSteps()) {
						if (!approveStep.getNodeCd().equals(ResConstants.SP_NODE_CD_SADD)) {
							String userCd = getSingleApproveUser(approveStep.getNodeCd());
							approverUsers.add(new ResApproverUser(userCd, CodeNameUtil.getUserNameByCd(userCd), "1",
									approveStep.getNodeCd()));
						}
					}
				}
			}
		}
	}

	// 初始化关于与酒店有关，与行业有关，与商业有关，开业前，开业后
	private void initAbout() {
		for (ResAutoNodeSet autoNodeSet : resAuthType.getResAutoNodeSets()) {
			if (autoNodeSet.getActive() == null || autoNodeSet.getActive()) {
				if (StringUtils.isNotBlank(autoNodeSet.getOtherCondtion())) {
					if (autoNodeSet.getOtherCondtion().equals("hotel")) {
						aboutHotel = true;
					} else if (autoNodeSet.getOtherCondtion().equals("trade")) {
						aboutTrade = true;
					} else if (autoNodeSet.getOtherCondtion().equals("estate")) {
						aboutEstate = true;
					} else if (autoNodeSet.getOtherCondtion().equals("before")) {
						aboutBefore = true;
					} else if (autoNodeSet.getOtherCondtion().equals("after")) {
						aboutAfter = true;
					}
				}
			}
		}
	}

	public void prepareSay() throws Exception {
		if (getId() != null) {

		}
	}

	public void prepareDetail() throws Exception {
		prepareModel();
	}

	public void prepareAddExtendUsers() throws Exception {
		prepareSave();
	}

	public void prepareConfirm() throws Exception {
		prepareSave();
	}

	public void prepareLock() throws Exception {
		prepareSave();
	}

	public void prepareUnlock() throws Exception {
		prepareSave();
	}

	public void prepareCopyZB2HT() throws Exception {
		prepareModel();
	}

	public void prepareAppendRemark() throws Exception {
		prepareSave();
	}

	public void prepareLoadApprove() throws Exception {
		prepareSave();
	}

	/**
	 * 追加审批意见
	 * 
	 * @return
	 * @throws Exception
	 */
	public String appendRemark() throws Exception {
		String approveRemark_append = Struts2Utils.getParameter("approveRemark_append");
		String approveNodeId = Struts2Utils.getParameter("approveNodeId");
		if (StringUtils.isNotBlank(approveRemark_append)) {
			ResApproveNode resApproveNode = resApproveNodeManager.getEntity(approveNodeId);
			StringBuffer sbApproveRemark = new StringBuffer(StringUtils.trimToEmpty(resApproveNode.getRemark()));
			sbApproveRemark.append("\n");
			sbApproveRemark.append(approveRemark_append);
			resApproveNode.setRemark(sbApproveRemark.toString());
			resApproveNodeManager.saveResApproveNode(resApproveNode);
		}

		Struts2Utils.getRequest().setAttribute("codeNameUtil", new CodeNameUtil());
		return "approve";
	}

	public String loadApprove() throws Exception {
		initPermission();
		initIsMyApprove();
		Struts2Utils.getRequest().setAttribute("codeNameUtil", new CodeNameUtil());
		return "approve";
	}

	private void saveMsg(String content, String referMsgId) {
		if (StringUtils.isNotEmpty(content)) {
			entity = resApproveInfoManager.getEntity(getId());
			ResApproveMessage message = new ResApproveMessage();
			message.setMsgContent(content);
			message.setReferMsgId(referMsgId);
			message.setResApproveInfo(entity);
			if (isSharedUser(entity)) {
				// 被共享人发言，只有共享人、同时被共享的人可以看见、以及当前节点的审批人可见
				List<String> shareUsers = loadShare(shareUser);
				String toUserCds = PowerUtils.array2String(shareUsers, ";");
				if (StringUtils.isNotBlank(toUserCds)) {
					toUserCds = toUserCds + ";" + shareUser;
				} else {
					toUserCds = shareUser;
				}
				message.setToUserCds(toUserCds);
			}
			resApproveMessageManager.saveResApproveMessage(message);
		}
	}

	/**
	 * 留言
	 * @return
	 * @throws Exception
	 */
	public String say() throws Exception {
		initPermission();
		String content = Struts2Utils.getParameter("content");
		// 引用的留言
		String referMsgId = Struts2Utils.getParameter("referMsgId");
		saveMsg(content, referMsgId);
		if (StringUtils.equals(isShare, "1") && StringUtils.isNotEmpty(content)) {
			completeShare();
		}
		messages = resApproveMessageManager.loadMsgList(getId());
		return "say";
	}

	private void genTitleName() throws Exception {
		entity.setTitleName(templateBean.getResTitleName());
		if (StringUtils.isEmpty(templateBean.getResProjectCd())) {
			String curCenterCd = getCurCenterCd();
			entity.setLandProjectCd(curCenterCd);
			entity.setLandProject(CodeNameUtil.getDeptNameByCd(curCenterCd));
		} else {
			entity.setLandProject(templateBean.getResProjectName());
			entity.setLandProjectCd(templateBean.getResProjectCd());
		}
	}

	@Override
	public void prepareSave() throws Exception {
		if (getId() != null) {
			entity = resApproveInfoManager.getEntity(getId());
		} else {
			// 初始化审批数据
			resAuthType = resAuthTypeManager.getResAuthTypeByCd(resAuthTypeCd);

			entity = new ResApproveInfo();
			if (resAuthType != null) {
				entity.setModuleTypeCd(resAuthType.getResModule().getModuleTypeCd());
				entity.setTempletCd(resAuthType.getTempletCd());
				entity.setAuthTypeCd(resAuthType.getAuthTypeCd());
			}
			entity.setUserCd(SpringSecurityUtils.getCurrentUiid());
			entity.setUserName(CodeNameUtil.getUserNameByCd(SpringSecurityUtils.getCurrentUiid()));
			// 初始状态0-新增
			entity.setStatusCd(ResConstants.RES_APPROVE_STATUS_NEW);
			entity.setCreatedDeptCd(SpringSecurityUtils.getCurrentCenterCd());
		}
		ResBillTemplet templet = PdCache.getRefEntity(ResBillTemplet.class, "templetCd", entity.getTempletCd());// resBillTempletManager.getTempletByCd(entity.getTempletCd());
		if (templet != null) {
			templetFileName = templet.getTempletFileName();
			templateBean = (BaseTemplate) Class.forName(templet.getEntityName()).newInstance();
//			Map prop=new HashMap();
//			prop.put("test", String.class);
//			DynamicBean bean=new DynamicBean((Class<? extends BaseTemplate>)Class.forName(templet.getEntityName()),prop);
//			templateBean = bean.getObject();
		}
	}
	/**
	 * 用于合同文本库中调用保存网批信息
	 * qilb 6/8/2012
	 */
	public String saveContent() throws Exception {
		ResApproveContent approveContent = getResApproveContent();
		approveContent.setApproveContent(new ClobImpl(ResXmlParser.initXml(templateBean)));
		approveContent.setResApproveInfo(entity);	
		//设置状态为已导入
		entity.setIsImported(true);
		resApproveInfoManager.saveResApproveInfo(entity, approveContent);
		Struts2Utils.renderText("true");
		return null;
	}
	@Override
	public String save() throws Exception {
		// 保存到主表
		genTitleName();
		// 如果是合同（其他情况），按照条款号排序。
		if (templateBean instanceof InviteFileContactOther) {
			InviteFileContactOther contactOther = (InviteFileContactOther) templateBean;
			CollectionHelper.trim(contactOther.getOtherProperties());
			CollectionHelper.sortList(contactOther.getOtherProperties(), "itemNo");
		}
		// entity.setApproveContent(new ClobImpl(ResXmlParser.initXml(templateBean)));
		ResApproveContent approveContent = getResApproveContent();
		approveContent.setApproveContent(new ClobImpl(ResXmlParser.initXml(templateBean)));
		approveContent.setResApproveInfo(entity);
		resApproveInfoManager.saveResApproveInfo(entity, entityTmpId, approveContent);
		return RELOAD;
	}

	private ResApproveContent getResApproveContent() {
		if (entity.getResApproveContents().size() > 0)
			return entity.getResApproveContents().get(0);
		return new ResApproveContent();
	}

	/**
	 * 功能: 总裁意见副本
	 * 
	 * @param id
	 * @return
	 */
	public String chiefCopy() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		String bizModuleCd = Struts2Utils.getRequest().getParameter("bizModuleCd");
		filters.add(new PropertyFilter("EQS_bizEntityId", getId()));
		filters.add(new PropertyFilter("EQS_bizModuleCd", bizModuleCd));
		filters.add(new PropertyFilter("EQS_statusCd", "1"));// 1-正常
		chiefCopyList = appAttachFileManager.find(filters);
		Struts2Utils.getRequest().setAttribute("bizModuleCd", bizModuleCd);
		return "chiefCopy";
	}

	/**
	 * 判断是否上传过附件
	 * 
	 * @return
	 */
	public void isUploaded() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		String bizModuleCd = Struts2Utils.getRequest().getParameter("bizModuleCd");
		filters.add(new PropertyFilter("EQS_bizEntityId", getId()));
		filters.add(new PropertyFilter("EQS_bizModuleCd", bizModuleCd));
		filters.add(new PropertyFilter("EQS_statusCd", "1"));// 1-正常
		int cnt = appAttachFileManager.countByPropertyFilter(filters);
		Struts2Utils.getRequest().setAttribute("bizModuleCd", bizModuleCd);
		if (cnt > 0) {
			Struts2Utils.renderText("true");
			return;
		}
		Struts2Utils.renderText("false");
	}

	/**
	 * 是否要导入合同台账
	 * 
	 * @return
	 */
	public boolean isMust2Cont() {
		boolean flag = false;
		if (templateBean instanceof IAutoImport) {
			IAutoImport iAutoImport = (IAutoImport) templateBean;
			if (!iAutoImport.isAutoImport() && (entity.getIsImported() == null || !entity.getIsImported())) {
				flag = true;
			}
		}
		return flag;
	}

	// 导入台账、供应商
	public String doImport() throws Exception {
		if (templateBean instanceof IAutoImport) {
			IAutoImport iAutoImport = (IAutoImport) templateBean;
			if (!iAutoImport.isAutoImport() || SpringSecurityUtils.hasRole("A_ADMIN")) {
				iAutoImport.doImport();
				entity.setIsImported(true);
				// entity.setApproveContent(new ClobImpl(ResXmlParser.initXml(templateBean)));
				ResApproveContent approveContent = getResApproveContent();
				approveContent.setApproveContent(new ClobImpl(ResXmlParser.initXml(templateBean)));
				approveContent.setResApproveInfo(entity);
				resApproveInfoManager.saveResApproveInfo(entity, approveContent);
				Struts2Utils.renderText("success");
			}
		}
		return null;
	}

	public void prepareApply() throws Exception {
		prepareSave();
	}

	public void prepareUser() throws Exception {
		prepareSave();
	}

	public void prepareGiveMe() throws Exception {
		prepareSave();
	}

	public void prepareDoImport() throws Exception {
		prepareModel();
	}

	public void prepareAgree() throws Exception {
		if (getId() != null) {
			entity = resApproveInfoManager.getEntity(getId());
			ResBillTemplet templet = PdCache.getRefEntity(ResBillTemplet.class, "templetCd", entity.getTempletCd());// resBillTempletManager.getTempletByCd(entity.getTempletCd());
			templetFileName = templet.getTempletFileName();
			// boolean isEdit = BooleanUtils.toBoolean(Struts2Utils.getParameter("isEdit"));
			// TODO 【非常重要】审批过程中会出现内容丢失 所以注释以下代码，但是会引发成本月计划网批无法在过程中删除，by HuangJian 2012-04-24 17:12
			// if (isEdit){
			// templateBean = (BaseTemplate) Class.forName(templet.getEntityName()).newInstance();
			// }else{
			templateBean = ResXmlParser.parserXml(entity, templet.getEntityName());
			// }
		}
	}

	public void prepareSaveContent() throws Exception {
		prepareAgree();
	}
	public void prepareBack() throws Exception {
		prepareAgree();
	}

	public void prepareRollback() throws Exception {
		prepareAgree();
	}

	public void prepareBack2Last() throws Exception {
		prepareAgree();
	}

	public void prepareShare() throws Exception {
		if (getId() != null) {
			entity = resApproveInfoManager.getEntity(getId());
			initShareInfo();
		}
	}

	public void preparePush() throws Exception {
		prepareSave();
		initPushInfo();
	}

	public void prepareReceive() throws Exception {
		prepareSave();
	}

	/**
	 * 初始化审批节点
	 * 
	 * @param steps
	 */
	private List<ResApproveNode> initApproveNodes(List<ResApproveStep> steps) {
		List<ResApproveNode> approveNodes = new ArrayList<ResApproveNode>();
		Set<String> addedNodes = new HashSet<String>();
		if (!ArrayUtils.contains(ResConstants.MANUAL_STEP_TEMP, entity.getTempletCd())) {
			for (ResApproveStep resApproveStep : steps) {
				if (!resApproveStep.getNodeCd().equals(ResConstants.SP_NODE_CD_SADD)) {
					// 流程配置表定义好的节点处理人员
					if (StringUtils.equals(resApproveStep.getStepOptionCd(), ResConstants.SP_STEP_OPTION_BX)) {
						// 步骤类型等于必须
						createApproveNode(resApproveStep, ResConstants.SP_OPTION_NOT, approveNodes, addedNodes);
					}
				}
			}
		}
		int i = 1;
		// 自定义审批步骤处理
		for (Iterator<ResApproverUser> iterator = approverUsers.iterator(); iterator.hasNext();) {
			ResApproverUser step = iterator.next();
			if (step != null) {
				ResApproveStep approveStep = new ResApproveStep();
				approveStep.setApproveLevel(Long.valueOf(i * 10));
				approveStep.setBackFlg(DictContants.COM_ENABLED_TRUE);
				approveStep.setApproveRank(DictContants.RES_APPROVE_RANK_ONE);//自定义节点设置为默认一级
				if (!StringUtils.equals(step.getApproverUserCd(), Constants.XUJK)) {
					approveStep.setTimeLimit(getTimeLimit(24l));// 审批时限24小时
				}
				if (iterator.hasNext()) {
					approveStep.setVerifyCd(ResConstants.SP_RES_VERIFY_SH);
				} else {
					approveStep.setVerifyCd(ResConstants.SP_RES_VERIFY_SP);
				}
				String nodeCd = step.getNodeCd();
				if (StringUtils.isBlank(nodeCd)) {
					nodeCd = step.getApproverUserName() + ":" + String.valueOf(i);
				}
				approveStep.setNodeCd(nodeCd);
				i++;
				createApproveNode(approveStep, ResConstants.SP_OPTION_NOT, approveNodes, addedNodes);
			}
		}
		return approveNodes;
	}

	// 文件审批，人员包含校验
	private boolean containUser(String uiid) {
		boolean flag = false;
		if (templateBean != null) {
			String strSelfSteps = templateBean.getSelfStep();
			String strSelfStepsNames = templateBean.getSelfStepName();
			if (strSelfSteps != null && strSelfStepsNames != null) {
				String[] steps = strSelfSteps.split(",");
				for (int i = 0; i < steps.length; i++) {
					String strStep = StringUtils.trim(steps[i]);
					if (StringUtils.equals(strStep, uiid)) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 在指定的人员后面增加指定节点和审批人
	 */
	private void addNode(List<ResApproveNode> approveNodesNew, String destUser, String uiid, String nodeCd,
			boolean isBefore) {
		boolean isExistDest = false;
		boolean isExistTar = false;
		Long approveLevel = null;
		String groupNodeCd = null;
		Long sequenceNo = null;
		long offset = 1l;
		if (isBefore) {
			offset = -1l;
		}
		// 判断是否存在目标节点
		for (ResApproveNode approveNode : approveNodesNew) {
			if (approveNode.getUserCd().equals(destUser)) {
				isExistDest = true;
				approveLevel = approveNode.getApproveLevel() + offset;
				groupNodeCd = approveNode.getGroupNodeCd();
				if (groupNodeCd != null) {
					approveLevel = approveNode.getApproveLevel();
					sequenceNo = approveNode.getSequenceNo() + offset;
				}
				break;
			}
		}
		if (isExistDest) {
			// 如果存在，判断流程中是否已经存在指定节点
			for (ResApproveNode approveNode : approveNodesNew) {
				if (approveNode.getUserCd().equals(uiid)) {
					isExistTar = true;
					break;
				}
			}
			if (!isExistTar) {
				// 如果不存在，增加指定节点
				ResApproveNode approveNode_new = new ResApproveNode();
				approveNode_new.setNodeCd(nodeCd);
				approveNode_new.setApproveLevel(approveLevel);
				approveNode_new.setGroupNodeCd(groupNodeCd);
				approveNode_new.setSequenceNo(sequenceNo);
				approveNode_new.setUserCd(uiid);
				approveNode_new.setTimeLimit(getTimeLimit(24l));
				approveNode_new.setApproveOptionCd(ResConstants.SP_OPTION_NOT);
				approveNode_new.setResApproveInfo(entity);
				WsPlasUser t = PlasCache.getUserByUiid(uiid);
				approveNode_new.setWorkDutyDesc(t.getWorkDutyDesc());
				approveNode_new.setUserName(t.getUserName());
				approveNodesNew.add(approveNode_new);
			}
		}
	}

	// 过滤没有选中的会签节点
	private void filterSteps(List<ResApproveStep> steps) throws Exception {
		if (templateBean instanceof InviteFileContactOther || templateBean instanceof QuestionAndAnswerApprove
				|| templateBean instanceof ContractItemApprove || templateBean instanceof BizRentContractApp
				|| templateBean instanceof BizContractItemApp) {
			List otherProperties = (List) PropertyUtils.getProperty(templateBean, "otherProperties");
			List<String> lstNodeCd = PowerUtils.getProptyArray(otherProperties, "resDeptCd");
			for (Iterator<ResApproveStep> it = steps.iterator(); it.hasNext();) {
				ResApproveStep step = it.next();
				if (StringUtils.equals(step.getGroupNodeCd(), ResConstants.SP_NODE_CD_XGZYPS)
						|| StringUtils.equals(step.getGroupNodeCd(), ResConstants.SP_NODE_CD_XGZXHQ)) {
					if (!lstNodeCd.contains(step.getNodeCd())) {
						it.remove();
					}
				}
			}
		}
	}

	/**
	 * 添加额外审批人
	 * <b>功能未启用</b>
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addExtendUsers() throws Exception {
		resApproveInfoManager.addExtendUsers(entity);
		Struts2Utils.getRequest().setAttribute("codeNameUtil", new CodeNameUtil());
		// Struts2Utils.renderText("success");
		return "approve";
	}

	/**
	 * 填写审批人
	 * @return
	 * @throws Exception
	 */
	public String user() throws Exception {
		parserSteps();

		steps = getApplyStep();
		if (steps.size() == 0) {
			Struts2Utils.renderText("错误:找不到对应的审批路径，请检查数据是否填写完整!");
			return null;
		}
		String tmp_conditionCd = steps.get(0).getResConditonType().getConditionCd();
		entity.setStatusCd(ResConstants.RES_APPROVE_STATUS_NEW);
		entity.setConditionCd(tmp_conditionCd);
		// 保存到主表
		genTitleName();
		ResApproveContent approveContent = getResApproveContent();
		approveContent.setApproveContent(new ClobImpl(ResXmlParser.initXml(templateBean)));
		approveContent.setResApproveInfo(entity);
		resApproveInfoManager.saveResApproveInfo(entity, entityTmpId, approveContent);
		// TODO
		initCustomStep(entity.getAuthTypeCd(), tmp_conditionCd);
		filterSteps(steps);
		approveNodes = initApproveNodes(steps);
		for (ResApproveNode node : approveNodes) {
			ResNode resNode = PdCache.getRefEntity(ResNode.class, "nodeCd", node.getNodeCd());
			if (resNode != null) {
				mapNodeLock.put(node.getNodeCd(), resNode.getIsLock());
			}
		}
		CollectionHelper.sortList(approveNodes, "approveLevel",true);
		//CollectionHelper.sortList(approveNodes, "approveRank",false);//按approvelel升序，按rank降序
		//不是例外事项、视觉任务书、立项申请单
		if (!getModuleTypeCdSrh().equals(ResConstants.MODULE_TYPE_CD_TP) && !ArrayUtils.contains(ResConstants.MANUAL_STEP_TEMP, entity.getTempletCd()))
			return "customer";
		else
			return "submit";
		// createNodes(entity.getAuthTypeCd(), tmp_conditionCd);
	}

	public void prepareSubmit() throws Exception {
		if (getId() != null) {
			entity = resApproveInfoManager.getEntity(getId());
			ResBillTemplet templet = PdCache.getRefEntity(ResBillTemplet.class, "templetCd", entity.getTempletCd());
			if (templet != null) {
				templateBean = ResXmlParser.parserXml(entity, templet.getEntityName());
			}
			if (templateBean != null) {
				if (templateBean instanceof IBeforeProcess) {
					((IBeforeProcess) templateBean).doBeforeProcess();
				}
			}
		}
	}

	/**
	 * 发起
	 * @return
	 * @throws Exception
	 */
	public String submit() throws Exception {
		parserSteps();
		createNodes(entity.getAuthTypeCd(), entity.getConditionCd());
		return RELOAD;
	}
	/**
	 * 取得审批时限
	 * @param timeLimit
	 * @return
	 */
	private Long getTimeLimit(Long timeLimit){
		if (getModuleTypeCdSrh().equals(ResConstants.MODULE_TYPE_CD_TP))
			return null;
		else
			return timeLimit;
	}
	/**
	 * 根据编号和条件CD，创建审批节点（resApproveNode），在发起时调用
	 * @param authTypeCd
	 * @param conditionCd
	 * @throws Exception
	 */
	private void createNodes(String authTypeCd, String conditionCd) throws Exception {
		ResConditonType conditonType = resConditonTypeManager.getConditionByCd(authTypeCd, conditionCd);
		List<ResApproveStep> resApproveSteps = conditonType.getResApproveSteps();
		filterSteps(resApproveSteps);
		List<ResApproveNode> approveNodesNew = initApproveNodes(resApproveSteps);
		// 如果审批人是潘涛，并且审批步骤中不包含施思妮，在后面增加施思妮审批步骤
		addNode(approveNodesNew, "pantao", "shisn", ResConstants.SP_NODE_CD_ZXDS_SY, false);
		ResApproveNode nodeFirst = null;
		boolean isRankTwo =  false;
		if (resApproveSteps.size() > 0) {
			nodeFirst = getFirstApproveNode(approveNodesNew);
			// start 如果是节假日、周末；延迟24小时
			resApproveInfoManager.dealTimeLimit(nodeFirst);
			// end
		}
        
		if (nodeFirst != null) {
			String approveUserCd = resApproveInfoManager.getApproveUser(nodeFirst, approveNodesNew,DictContants.RES_APPROVE_RANK_ONE);
			String approveUseCd2 = resApproveInfoManager.getApproveUser(nodeFirst, approveNodesNew,DictContants.RES_APPROVE_RANK_TWO);
			entity.setApproveUserCd(approveUserCd);
			
			// 下一个节点Cd，如果该节点属于某个小组，则取小组
			String nextNodeCd = nodeFirst.getNodeCd();
			if (StringUtils.isNotEmpty(nodeFirst.getGroupNodeCd())) {
				nextNodeCd = nodeFirst.getGroupNodeCd();
			}
			if(StringUtils.isNotEmpty(approveUseCd2)){ //如果是二级，则将level和node写入二级
				entity.setApproveUserCd2(approveUseCd2);
				entity.setApproveLevel2(nodeFirst.getApproveLevel());
				entity.setNodeCd2(nodeFirst.getNodeCd());
				entity.setNodeCd(resApproveInfoManager.getApproveNodeCd(nodeFirst,approveNodesNew));
				entity.setApproveLevel(resApproveInfoManager.getApproveLevel(nodeFirst, approveNodesNew));

				isRankTwo = true;
			}else{
				entity.setNodeCd(nextNodeCd);
				entity.setApproveLevel(nodeFirst.getApproveLevel());
			}
		 
			entity.setBackFlg(nodeFirst.getBackFlg());
			entity.setTimeLimit(getTimeLimit(nodeFirst.getTimeLimit()));
			entity.setConditionCd(conditionCd);
		}
		// 创建申请历史记录
		Date now = new Date();
		ResApproveNode approveNode = new ResApproveNode();
		approveNode.setResApproveInfo(entity);
		approveNode.setUserCd(SpringSecurityUtils.getCurrentUiid());
		approveNode.setUserName(SpringSecurityUtils.getCurrentUserName());
		approveNode.setApproveDate(now);
		approveNode.setApproveOptionCd(ResConstants.SP_OPTION_APPLY);
		approveNode.setApproveRank((isRankTwo)?DictContants.RES_APPROVE_RANK_TWO:DictContants.RES_APPROVE_RANK_ONE);
		approveHises.add(resApproveInfoManager.addApproveHis(null, approveNode));
		entity.setStartDate(now);// 发起日期
		entity.setLastApproveDate(now);// 发起日期
		// 审批中:1
		entity.setStatusCd(ResConstants.RES_APPROVE_STATUS_ING);
		resApproveInfoManager.saveResApproveInfoBySubmit(entity, approveNodesNew, approveHises, templateBean);
	}

	/**
	 * 根据节点CD，取得对应的审批人Cd
	 * @param nodeCd
	 * @return
	 */
	private String getSingleApproveUser(String nodeCd) {
		return getSingleApproveUser(nodeCd, false);
	}

	/**
	 * 取得节点对应的审批人
	 * 
	 * @param nodeCd
	 * @param isDefault
	 *            是否只取默认的审批人，不从用户输入值中取（用户在界面填写的审批人）
	 * @return
	 */
	private String getSingleApproveUser(String nodeCd, boolean isDefault) {
		String approveUserCd = null;
		if (templateBean != null) {
			if (ArrayUtils.contains(ResConstants.CONT_RELA_NODES, nodeCd)) {
				approveUserCd = templateBean.getMapCustomNode().get(ResConstants.SP_NODE_CD_GZR);
				if (StringUtils.isEmpty(approveUserCd)) {
					// 如果找不到跟踪人，则取合同的发起人
					approveUserCd = entity.getUserCd();
				}
			} else {
				approveUserCd = resNodeManager.getNodeUserByCd(nodeCd);// 节点中配置的审批人
				if (StringUtils.isEmpty(approveUserCd)) {
					if (!isDefault) {
						if (ResConstants.allCustomNode.contains(nodeCd)) {
							approveUserCd = templateBean.getMapCustomNode().get(nodeCd);// 自定义审批人，用户在前台手动填写
						}
					}
					if (StringUtils.isEmpty(approveUserCd)
							&& ArrayUtils.contains(ResConstants.MANUAL_STEP_TEMP, entity.getTempletCd())) {
						// 例外事项审批表，用户手动新增的审批步骤，审批人也是用户手动新增
						for (Iterator<ResApproverUser> iterator = approverUsers.iterator(); iterator.hasNext();) {
							ResApproverUser step = iterator.next();
							if (nodeCd.equals(step.getNodeCd()) || nodeCd.contains(step.getApproverUserName())) {
								approveUserCd = step.getApproverUserCd();
								break;
							}
						}
					}
					if (StringUtils.isEmpty(approveUserCd)) {
						// 在plas网批节点配置中配置好的审批人
						approveUserCd = PlasCacheUtil.getResUiidOneByNodeCd(nodeCd);
					}
				}
			}
		} else {
			logger.error("getSingleApproveUser:templateBean is null");
		}
		return approveUserCd;
	}

	/**
	 * 初始化‘合同条件审批表’等需要发起人选择责任部门的表单
	 * @return
	 * @throws Exception
	 */
	private String loadMapGroupNodes() throws Exception {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (templateBean instanceof InviteFileContactOther || templateBean instanceof QuestionAndAnswerApprove
				|| templateBean instanceof ContractItemApprove || templateBean instanceof BizRentContractApp
				|| templateBean instanceof BizContractItemApp) {
			List<ResApproveStep> approveSteps = getApplyStep();
			for (ResApproveStep resApproveStep : approveSteps) {
				// 相关专业评审
				if (ResConstants.SP_NODE_CD_XGZYPS.equals(resApproveStep.getGroupNodeCd())) {
					map.put(resApproveStep.getNodeCd(), CodeNameUtil.getResNodeNameByCd(resApproveStep.getNodeCd()));
				}
				// 相关中心会签
				if (ResConstants.SP_NODE_CD_XGZXHQ.equals(resApproveStep.getGroupNodeCd())) {
					map.put(resApproveStep.getNodeCd(), CodeNameUtil.getResNodeNameByCd(resApproveStep.getNodeCd()));
				}
			}
		}
		mapGroupNodes = map;
		return "groupNode";
	}

	/**
	 * 根据表单内容取得审批的路径
	 * 
	 * @return
	 * @throws Exception
	 */
	private List<ResApproveStep> getApplyStep() throws Exception {
		List<ResApproveStep> stepsTmp = new ArrayList<ResApproveStep>();
		if (entity != null) {
			ResAuthType authType = resAuthTypeManager.getResAuthTypeByCd(entity.getAuthTypeCd());
			if (authType != null) {
				boolean isDefault = false;
				if (authType.getResConditonTypes().size() == 1) {
					ResConditonType conditonType = authType.getResConditonTypes().get(0);
					stepsTmp = conditonType.getResApproveSteps();
					isDefault = true;
				}
				if (!isDefault) {
					for (ResConditonType conditonType : authType.getResConditonTypes()) {
						String conditionValue = conditonType.getConditionValue();
						boolean isPass = false;
						List<List<List<ConditionField>>> lstConsOR = ResConditionParser.parse(conditionValue);
						for (List<List<ConditionField>> listAnd : lstConsOR) {
							boolean isPassAnd = true;
							for (List<ConditionField> lstItem : listAnd) {
								if (!passSingle(lstItem)) {
									isPassAnd = false;
									break;
								}
							}
							if (isPassAnd) {
								isPass = true;
							}

						}
						if (isPass) {
							stepsTmp = conditonType.getResApproveSteps();
							break;
						}
					}
				}
			}else{
				logger.error("authType is null:"+entity.getAuthTypeCd());
			}
		} else {
			logger.warn("entity is null");
		}
		return stepsTmp;
	}

	/**
	 * 搜索当前表单所在位置
	 * 
	 * @param moduleId
	 */
	private void initModulePath(String moduleId) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, moduleId);
		map.put(2, resAuthType.getResModule().getModuleTypeCd());
		Object obj = resApproveInfoManager.executeFunction("{?= call fn_get_module_path(?,?)}", map, String.class);
		modulePath = obj.toString();
	}

	private boolean passSingle(List<ConditionField> lstItem) throws Exception {
		boolean isPass = false;
		for (ConditionField conditionField : lstItem) {
			String value;

			if (ArrayUtils.contains(ResConditionParser.IN_OUT, conditionField.getFieldName())) {
				if (ArrayUtils.contains(ResConditionParser.IN_OUT_ONLY, conditionField.getFieldName())) {
					templateBean.setInOut(ResConditionParser.TRUE);
				}
				// 预算内、预算外、员工工资
				value = (String) PropertyUtils.getProperty(templateBean, conditionField.getFieldName() + "Flag");
				if (StringUtils.equals(value, ResConditionParser.TRUE)) {
					isPass = true;
					break;
				}
			} else if (conditionField.getValue().equals(ResConditionParser.TRUE)) {
				// 自定义勾选框过滤条件
				value = (String) PropertyUtils.getProperty(templateBean, conditionField.getFieldName());
				if (StringUtils.equals(value, ResConditionParser.TRUE)) {
					isPass = true;
					break;
				}
			} else {
				value = (String) PropertyUtils.getProperty(templateBean, conditionField.getFieldName());
				if (conditionField.getMatchType() == MatchType.EQ) {
					if (StringUtils.equals(value, conditionField.getValue())) {
						isPass = true;
						break;
					}
				} else {
					//判断数值
					Double f_Condi = FormatUtil.formatDouble(conditionField.getValue());
					Double f_Value = Math.abs( FormatUtil.formatDouble(value));
					if (conditionField.getMatchType() == MatchType.GE) {
						if ((f_Value >= f_Condi)) {
							isPass = true;
							break;
						}
					} else if (conditionField.getMatchType() == MatchType.GT) {
						if ((f_Value > f_Condi)) {
							isPass = true;
							break;
						}
					} else if (conditionField.getMatchType() == MatchType.LE) {
						if ((f_Value <= f_Condi)) {
							isPass = true;
							break;
						}
					} else if (conditionField.getMatchType() == MatchType.LT) {
						if ((f_Value < f_Condi)) {
							isPass = true;
							break;
						}
					}
				}
			}
		}
		return isPass;
	}

	/**
	 * 取得第一个审批节点，在发起时调用
	 * @param nodes
	 * @return
	 */
	private ResApproveNode getFirstApproveNode(List<ResApproveNode> nodes) {
		ResApproveNode approveNode = null;
		for (ResApproveNode approveNodeTmp : nodes) {
			if (approveNode == null) {
				approveNode = approveNodeTmp;
			} else {
				if (approveNodeTmp.getApproveLevel() < approveNode.getApproveLevel()) {
					approveNode = approveNodeTmp;
				}
			}
		}
		return approveNode;
	}

	/**
	 * 取得节点Map，用于界面Code转Name
	 * @return
	 */
	public Map<Long, String> getNodeMap() {
		Map<Long, String> mapNodes = new LinkedHashMap<Long, String>();
		long nodeFrom = 0;
		for (ResApproveNode nodeTmp : entity.getResApproveNodes()) {
			if(1==nodeTmp.getApproveRank()){
				String nodeCd = StringUtils.isEmpty(nodeTmp.getGroupNodeCd()) ? nodeTmp.getNodeCd() : nodeTmp
					.getGroupNodeCd();
				mapNodes.put(nodeTmp.getApproveLevel(), nodeCd);
				
				int index2 = 0;
				boolean reachToNextLevel1 = false;
				for (ResApproveNode nodeTmp2 : entity.getResApproveNodes()) {
					if(nodeTmp2.getApproveRank()==1 && nodeTmp2.getApproveLevel()>nodeFrom && !reachToNextLevel1){
						reachToNextLevel1 = true;
					}
					if(nodeTmp2.getApproveRank()==2 && nodeTmp2.getApproveLevel()>nodeFrom && !reachToNextLevel1){
						index2++;
					}
				}
				nodeFrom = nodeTmp.getApproveLevel();
				if(index2>0){
					mapNodesLevelInfo.put(nodeTmp.getApproveLevel(), "(+"+Integer.toString(index2)+")");
				}
			}
		}
		return mapNodes;
	}

	/**
	 * 创建单个节点
	 */
	private void createSingleNode(List<ResApproveNode> approveNodesTmp, ResApproveStep curStep, String approveUser,
			Long timeLimit, String option, boolean isMult, long index) {
		ResApproveNode approveNodeTmp = new ResApproveNode();
		approveNodeTmp.setApproveLevel(curStep.getApproveLevel());
		approveNodeTmp.setApproveRank(curStep.getApproveRank());//写入级别by bo.wu
		approveNodeTmp.setApproveDate(null);
		String nodeCd = curStep.getNodeCd();
		approveNodeTmp.setNodeCd(nodeCd);
		String approveUserCd = getSingleApproveUser(nodeCd, true);
		if (approveUserCd == null || StringUtils.equals(approveUserCd, approveUser)) {
			approveNodeTmp.setIsDefault(true);// 如果当前审批人和默认的审批人一致。
		} else {
			approveNodeTmp.setIsDefault(false);
		}
		approveNodeTmp.setUserCd(approveUser);
		approveNodeTmp.setUserName(CodeNameUtil.getUserNameByCd(approveUser));
		WsPlasUser t = PlasCache.getUserByUiid(approveUser);
		if (t != null) {
			approveNodeTmp.setWorkDutyDesc(t.getWorkDutyDesc());
		}
		Long timeLimitTmp = timeLimit;
		if (timeLimitTmp == null) {
			timeLimitTmp = curStep.getTimeLimit();
		}
		approveNodeTmp.setTimeLimit(getTimeLimit(timeLimitTmp));// 审批时限
		approveNodeTmp.setVerifyCd(curStep.getVerifyCd());// 审批、审核标识
		approveNodeTmp.setBackFlg(curStep.getBackFlg());
		if (isMult && StringUtils.isBlank(curStep.getGroupNodeCd())) {
			// 如果是多人，将该节点设置成节点组
			approveNodeTmp.setGroupNodeCd(curStep.getNodeCd());
		} else {
			approveNodeTmp.setGroupNodeCd(curStep.getGroupNodeCd());
		}
		if (curStep.getSequenceNo() != null && curStep.getSequenceNo() > 0) {
			approveNodeTmp.setSequenceNo(curStep.getSequenceNo());
		} else {
			approveNodeTmp.setSequenceNo(index);
		}
		// 同意:1,不同意:0,未审核:-1
		approveNodeTmp.setApproveOptionCd(option);
		approveNodeTmp.setResApproveInfo(entity);
		approveNodesTmp.add(approveNodeTmp);
	}

	/**
	 * 判断传入的额外条件，是否满足条件
	 * @param resAutoNodeSet
	 * @return
	 */
	private boolean isMatchCondition(ResAutoNodeSet resAutoNodeSet) {
		boolean isPass = false;
		String[] conditions = resAutoNodeSet.getOtherCondtion().split(ResConditionParser.AND);
		for (String cond : conditions) {
			isPass = isMatch(cond);
			if (!isPass) {
				break;
			}
		}
		return isPass;
	}

	/**
	 * 判断单个条件是否满足
	 * @param condition
	 * @return
	 */
	private boolean isMatch(String condition) {
		boolean isPass = false;
		String fieldName = condition;
		String fileValue = ResConditionParser.TRUE;
		try {
			String[] str = fieldName.split("=");
			if (str.length == 2) {
				fieldName = str[0];
				fileValue = str[1];
			}
			String value = (String) PropertyUtils.getProperty(templateBean, fieldName);
			if (StringUtils.equals(value, fileValue)) {
				isPass = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isPass;
	}

	/**
	 * 增加额外审批节点
	 * 
	 * @param curStep
	 * @param curUser
	 * @param approveNodesTmp
	 */
	private void addExtraNode(ResApproveStep curStep, String curUser, List<ResApproveNode> approveNodesTmp,
			Set<String> addedNodes) {
		//
		ResConditonType resConditonType = curStep.getResConditonType();
		if (resConditonType != null) {
			ResAuthType authType = resConditonType.getResAuthType();
			// ResAutoNodeSet autoNodeSet =
			// resAutoNodeSetManager.getResAutoNodeSetBy(authType.getResAuthTypeId(),
			// resConditonType.getConditionCd());
			for (ResAutoNodeSet resAutoNodeSet : authType.getResAutoNodeSets()) {
				// 判断步骤和审批人是否一致，如果符合条件，增加额外审批步骤
				// 如果条件CD为空，表示匹配所有条件
				if (resAutoNodeSet.getActive() == null || resAutoNodeSet.getActive()) {
					if (StringUtils.isBlank(resAutoNodeSet.getConditionCd())
							|| StringUtils.equals(resAutoNodeSet.getConditionCd(), resConditonType.getConditionCd())) {
						boolean isGroup = false;
						boolean isSingle = false;
						if (resAutoNodeSet.getLevelOffset() == 0
								&& resAutoNodeSet.getApproveNodeCd().equals(curStep.getGroupNodeCd())) {
							isGroup = true;
						} else if (resAutoNodeSet.getLevelOffset() != 0
								&& resAutoNodeSet.getApproveNodeCd().equals(curStep.getNodeCd())) {
							isSingle = true;
						}
						if (isSingle || isGroup) {
							String approveUserCd = resAutoNodeSet.getApproveUserCd();
							boolean isPass = false;
							// 只设置审批人条件
							if (StringUtils.isNotBlank(approveUserCd) && approveUserCd.equals(curUser)) {
								isPass = true;
							}
							// 只设置其他条件
							if (StringUtils.isNotBlank(resAutoNodeSet.getOtherCondtion())) {
								isPass = isMatchCondition(resAutoNodeSet);
							}
							// 同时设置两个条件
							if (StringUtils.isNotBlank(approveUserCd)
									&& StringUtils.isNotBlank(resAutoNodeSet.getOtherCondtion())) {
								isPass = false;
								if (approveUserCd.equals(curUser)) {
									isPass = isMatchCondition(resAutoNodeSet);
								}
							}
							/**
							 * 相关中心会签自定义新增用户审批人
							 */
							if (isGroup && StringUtils.isNotBlank(resAutoNodeSet.getApproveUserCd())) {
								Map<String, String> map = templateBean.getMapCustomNode();
								Set<String> key = map.keySet();
								for (String str : key) {
									if (resAutoNodeSet.getApproveUserCd().equals(map.get(str))) {
										isPass = true;
										break;
									}

								}
							}

							if (isPass) {
								if (isSingle) {
									addedNodes.add(curStep.getNodeCd());
								} else if (isGroup) {
									addedNodes.add(curStep.getGroupNodeCd());
								}

								ResApproveStep approveStep = new ResApproveStep();
								approveStep.setTimeLimit(getTimeLimit(24l));
								approveStep.setApproveLevel(curStep.getApproveLevel() + resAutoNodeSet.getLevelOffset());
								if (resAutoNodeSet.getLevelOffset() == 0) {
									// 如果偏移量=0，在当前会签节点中增加
									approveStep.setGroupNodeCd(curStep.getGroupNodeCd());
								}
								approveStep.setNodeCd(resAutoNodeSet.getExtraNodeCd());
								approveStep.setSequenceNo(resAutoNodeSet.getSequenceNo());
								String extraUserCd = resAutoNodeSet.getExtraUserCd();
								String nodeUserCd = resNodeManager.getNodeUserByCd(resAutoNodeSet.getExtraNodeCd());

								if (StringUtils.isNotBlank(nodeUserCd)) {
									extraUserCd = nodeUserCd;
								} else {
									if (StringUtils.isBlank(extraUserCd)) {
										// 设计变更审批表使用
										// 在设计变更上增加
										// 判断是否涉及园林景观、公共区域装修、精装修（销售楼盘）和“三点一线”（售楼处、样板房、景观示范区和看房通道）的勾选项
										// 只要勾选以上选项，则应该有 地产公司营销部、区域公司营销管理部参与审核。
										String nodeUser = getSingleApproveUser(resAutoNodeSet.getExtraNodeCd());
										extraUserCd = nodeUser;
									}
								}
								Long timeLimit = 24l;
								if (resAutoNodeSet.getLevelOffset() == 0) {
									timeLimit = 0l;
								}
								createSingleNode(approveNodesTmp, approveStep, extraUserCd, timeLimit,
										ResConstants.SP_OPTION_NOT, false, 0l);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 创建审批节点，在填写审批人时调用
	 * @param curStep
	 * @param option
	 * @param approveNodesTmp
	 * @param addedNodes
	 */
	private void createApproveNode(ResApproveStep curStep, String option, List<ResApproveNode> approveNodesTmp,
			Set<String> addedNodes) {
		// String nodeCd=parseNode(curStep.getNodeCd());
		String nodeUser = getSingleApproveUser(curStep.getNodeCd());
		String[] nodeUsers;
		if (nodeUser == null) {
			nodeUsers = new String[] { null };
		} else {
			nodeUsers = nodeUser.split(";");
		}
		long index = 1;
		for (String tmpUser : nodeUsers) {
			createSingleNode(approveNodesTmp, curStep, tmpUser, null, option, nodeUsers.length > 1, index);
			index++;
			if (!(addedNodes.contains(curStep.getNodeCd()) || addedNodes.contains(curStep.getGroupNodeCd()))) {
				// 如果还没处理过额外节点
				addExtraNode(curStep, tmpUser, approveNodesTmp, addedNodes);
			}
		}
	}
    /**
     * 二级审批人不同意方法，同意则在agree()中补全
     * @return
	 * @throws Exception
     */
	public String cd2Unagree() throws Exception{
		if (!isMyApprove(entity)) {
			Struts2Utils.renderText("fail");
			return null;
		}
		parserSteps();
		// 如果记录未解锁，自动解锁
		if (StringUtils.equals(entity.getLockUser(), SpringSecurityUtils.getCurrentUiid())) {
			resApproveInfoManager.unlock(entity);
		}
		String contFollower_tmp = templateBean.getMapCustomNode().get(ResConstants.SP_NODE_CD_GZR);
		for (ResApproveNode approveNode : entity.getResApproveNodes()) {
			if (approveNode.getNodeCd().equals(ResConstants.SP_NODE_CD_GZR)
					|| ArrayUtils.contains(ResConstants.CONT_RELA_NODES, approveNode.getNodeCd())) {
				if (approveNode.getUserCd() == null && contFollower_tmp != null) {
					approveNode.setUserCd(contFollower_tmp);
					approveNode.setUserName(CodeNameUtil.getUserNameByCd(contFollower_tmp));
					WsPlasUser t = PlasCache.getUserByUiid(contFollower_tmp);
					if (t != null) {
						approveNode.setWorkDutyDesc(t.getWorkDutyDesc());
					}
				}
			}
		}
		boolean isEdit = BooleanUtils.toBoolean(Struts2Utils.getParameter("isEdit"));
		ResApproveContent approveContent = null;
		if (isEdit) {
			// entity.setApproveContent(new ClobImpl(ResXmlParser.initXml(templateBean)));
			approveContent = getResApproveContent();
			approveContent.setApproveContent(new ClobImpl(ResXmlParser.initXml(templateBean)));
			approveContent.setResApproveInfo(entity);
		}
		resApproveInfoManager.agree(entity, approveHises, templateBean, approveContent);
		return RELOAD;
	}
			
	/**
	 * 同意
	 * 
	 * @return
	 * @throws Exception
	 */
	public String agree() throws Exception {
		if (!isMyApprove(entity)) {
			Struts2Utils.renderText("fail");
			return null;
		}
		parserSteps();

		// 如果记录未解锁，自动解锁
		if (StringUtils.equals(entity.getLockUser(), SpringSecurityUtils.getCurrentUiid())) {
			resApproveInfoManager.unlock(entity);
		}
		String contFollower_tmp = templateBean.getMapCustomNode().get(ResConstants.SP_NODE_CD_GZR);
		for (ResApproveNode approveNode : entity.getResApproveNodes()) {
			if (approveNode.getNodeCd().equals(ResConstants.SP_NODE_CD_GZR)
					|| ArrayUtils.contains(ResConstants.CONT_RELA_NODES, approveNode.getNodeCd())) {
				if (approveNode.getUserCd() == null && contFollower_tmp != null) {
					approveNode.setUserCd(contFollower_tmp);
					approveNode.setUserName(CodeNameUtil.getUserNameByCd(contFollower_tmp));
					WsPlasUser t = PlasCache.getUserByUiid(contFollower_tmp);
					if (t != null) {
						approveNode.setWorkDutyDesc(t.getWorkDutyDesc());
					}
				}
			}
		}
		boolean isEdit = BooleanUtils.toBoolean(Struts2Utils.getParameter("isEdit"));
		ResApproveContent approveContent = null;
		if (isEdit) {
			// entity.setApproveContent(new ClobImpl(ResXmlParser.initXml(templateBean)));
			approveContent = getResApproveContent();
			approveContent.setApproveContent(new ClobImpl(ResXmlParser.initXml(templateBean)));
			approveContent.setResApproveInfo(entity);
		}
		boolean[] isSameAndPass = resApproveInfoManager.agree(entity, approveHises, templateBean, approveContent);
		isSameApproveUser = isSameAndPass[0];
		if (isSameApproveUser)
			return "sameUser";
		return RELOAD;
	}

	private String toNodeCd = null;
	// 记录收回节点级别;
	private Long toApproveLevel = null;

	/**
	 * 判断是否有权限收回
	 * 
	 * @return
	 */
	public boolean isCanRollback() {
		boolean flag = false;
		// 审批中的记录才可以追回，已完成的记录不能追回
		if (entity.getStatusCd().equals(ResConstants.RES_APPROVE_STATUS_ING)) {
			if (entity.getUserCd().equals(SpringSecurityUtils.getCurrentUiid())) {
				// 我发起，并且当前还没有人审批过，可以追回
				flag = true;
				for (Iterator<ResApproveNode> it = entity.getResApproveNodes().iterator(); it.hasNext();) {
					ResApproveNode approveNode = it.next();
					if (!approveNode.getApproveOptionCd().equals(ResConstants.SP_OPTION_NOT)) {
						flag = false;
						break;
					}
				}
			}
			for (Iterator<ResApproveNode> it = entity.getResApproveNodes().iterator(); it.hasNext();) {
				ResApproveNode approveNode = it.next();
				if (approveNode.getUserCd().equals(SpringSecurityUtils.getCurrentUiid())
						&& !approveNode.getApproveOptionCd().equals(ResConstants.SP_OPTION_NOT)
						&& StringUtils.isEmpty(approveNode.getGroupNodeCd())) {
					// 我签批，并且下步审核人还未处理，可以追回，驳回的记录不能追回
					if (approveNode.getApproveOptionCd().equals(ResConstants.SP_OPTION_AGREE)) {
						if (it.hasNext()) {
							ResApproveNode approveNodeNext = it.next();
							if (approveNodeNext.getApproveOptionCd().equals(ResConstants.SP_OPTION_NOT)) {
								toNodeCd = approveNode.getNodeCd();
								// 记录返回节点级别
								toApproveLevel = approveNode.getApproveLevel();
								flag = true;
							}
						}
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 撤销 ， 用户通过此操作来撤销自己所做的最后一步审批操作（在下一步审批操作被执行之前） 适用下列情况 1.申请人在提交申请后，
	 * 撤销提交动作进行修改 2.具有驳回权限的用户，撤销自己最后一次的审批动作，重新进行审批
	 * 
	 * @return
	 * @throws Exception
	 */
	public String rollback() throws Exception {
		if (isCanRollback()) {
			parserSteps();
			resApproveInfoManager.rollBack(entity, approveHises, templateBean, toNodeCd, toApproveLevel);
		} else {
			Struts2Utils.renderText("fail");
			return null;
		}
		return RELOAD;
	}

	/**
	 * 用于完成的记录驳回到最后一步
	 * 
	 * @return
	 * @throws Exception
	 */
	public String back2Last() throws Exception {
		// 完成的记录可以驳回到最后一步。
		if (entity.getStatusCd().equals(ResConstants.RES_APPROVE_STATUS_FINISHL)) {
			ResApproveNode lastNodeCd = null;
			for (ResApproveNode nodeTmp : entity.getResApproveNodes()) {
				if (lastNodeCd == null) {
					lastNodeCd = nodeTmp;
				} else {
					if (nodeTmp.getApproveLevel() > lastNodeCd.getApproveLevel()) {
						lastNodeCd = nodeTmp;
					}
				}
			}
			if (lastNodeCd != null) {
				resApproveInfoManager.back2Last(entity, approveHises, templateBean, lastNodeCd);
			}
		} else {
			Struts2Utils.renderText("fail");
			return null;
		}
		return RELOAD;
	}

	/**
	 * 驳回
	 * 
	 * @return
	 * @throws Exception
	 */
	public String back() throws Exception {
		if (!SpringSecurityUtils.hasRole(GlobalConstants.A_ADMIN)
				&& !SpringSecurityUtils.hasRole(Constants.A_QZSP_ADMIN) && !isMyApprove(entity)) {
			Struts2Utils.renderText("fail");
			return null;
		}
		parserSteps();
		String rejectTo = Struts2Utils.getParameter("rejectTo");// 驳回到指定步骤
		resApproveInfoManager.back(entity, approveHises, templateBean, rejectTo);
		return RELOAD;
	}

	/**
	 * 初始化可被驳回的节点，在驳回时调用
	 */
	private void initMapReturnToNode() {
		entity = resApproveInfoManager.getEntity(getId());
		mapReturnToNode = new LinkedHashMap<String, String>();

		for (ResApproveNode nodeTmp : entity.getResApproveNodes()) {
			String nodeCd = StringUtils.isEmpty(nodeTmp.getGroupNodeCd()) ? nodeTmp.getNodeCd() : nodeTmp
					.getGroupNodeCd();
			// 加入级别
			Long approveLevel = nodeTmp.getApproveLevel();
			if ("1".equalsIgnoreCase(nodeTmp.getApproveOptionCd())&&nodeTmp.getApproveRank().equals(DictContants.RES_APPROVE_RANK_ONE)) {//只能驳回到一级已经审批的节点
				mapReturnToNode.put(nodeCd + "||" + approveLevel, getNodeNameByCd(nodeCd));
				// mapReturnToNode.put(nodeCd, getNodeNameByCd(nodeCd));
			}
		}
	}

	/**
	 * 根据Cd，取得节点名称
	 * @param nodeCd
	 * @return
	 */
	private String getNodeNameByCd(String nodeCd) {
		String nodeName = getMapNode().get(nodeCd);
		if (StringUtils.isEmpty(nodeName)) {
			nodeName = nodeCd;
		}
		return nodeName;
	}

	/**
	 * 显示驳回时，人员选择弹出框
	 * @return
	 * @throws Exception
	 */
	public String showRejectTo() throws Exception {
		initMapReturnToNode();
		return "showRejectTo";
	}

	/**
	 * 推送
	 * 
	 * @return
	 * @throws Exception
	 */
	public String push() throws Exception {
		String[] userCds = pushUserCds.split(";");
		resApproveInfoManager.push(entity, userCds, SpringSecurityUtils.getCurrentUserName());
		Struts2Utils.renderText("success");
		return null;
	}

	/**
	 * 取得共享人CD，在留言时调用
	 * @return
	 */
	private String getShareUserCd() {
		String creator = SpringSecurityUtils.getCurrentUiid();
		if (StringUtils.equals(isAcc, "1")) {
			List<ResAccreditInfo> accreditInfos = resAccreditInfoManager.loadAccInfoRes(SpringSecurityUtils
					.getCurrentUiid());
			if (accreditInfos.size() == 1) {
				creator = accreditInfos.get(0).getUserCd();
			} else {
				for (ResAccreditInfo resAccreditInfo : accreditInfos) {
					if (entity.getApproveUserCd().equals(resAccreditInfo.getUserCd())
							|| entity.getApproveUserCd().contains(resAccreditInfo.getUserCd() + ";")) {
						creator = resAccreditInfo.getUserCd();
						break;
					}
				}
			}
		}
		return creator;
	}

	/**
	 * 共享
	 * 
	 * @return
	 * @throws Exception
	 */
	public String share() throws Exception {
		String shareMsg = Struts2Utils.getParameter("shareMsg");
		StringBuffer emailTitle = new StringBuffer();
		emailTitle.append(entity.getLandProject());
		if (StringUtils.isNotBlank(entity.getTitleName())) {
			emailTitle.append(entity.getTitleName());
		}
		String[] userCds = shareUserCds.split(";");
		String creator = getShareUserCd();

		List<ResApproveShare> shares = new ArrayList<ResApproveShare>();
		for (String cdTmp : userCds) {
			if (StringUtils.isNotEmpty(cdTmp)) {
				ResApproveShare approveShare = new ResApproveShare();
				approveShare.setUserCd(cdTmp);
				approveShare.setUserName(CodeNameUtil.getUserNameByCd(cdTmp));
				approveShare.setResApproveInfo(entity);
				approveShare.setCreator(creator);
				approveShare.setReplied(false);
				shares.add(approveShare);
			}
		}
		List<ResApproveShare> sharesRemove = new ArrayList<ResApproveShare>();

		for (Iterator<ResApproveShare> it = entity.getResApproveShares().iterator(); it.hasNext();) {
			ResApproveShare resApproveShare = it.next();
			if (resApproveShare.getCreator().equals(creator)) {
				sharesRemove.add(resApproveShare);
				it.remove();
			}
		}
		resApproveShareHisManager.save(entity, shareUserCds);
		StringBuffer mailMsg = new StringBuffer();
		mailMsg.append(SpringSecurityUtils.getCurrentUserName());
		mailMsg.append("已将网批记录:").append(emailTitle.toString()).append(" 共享给您，请及时处理<br/>");
		mailMsg.append("留言: ").append(shareMsg);
		resApproveInfoManager.sendEmail(entity, "【网上审批共享】" + emailTitle.toString(), mailMsg.toString(), userCds);
		// 刷新共享人
		resApproveShareManager.refeshShares(sharesRemove, shares);
		entity.getResApproveShares().addAll(shares);
		if (shares.size() > 0) {
			// 已共享
			entity.setShareStatusCd("0");
		} else {
			entity.setShareStatusCd(null);
		}
		resApproveInfoManager.update2JbpmTask(entity);
		// 添加留言记录
		if (StringUtils.isNotBlank(shareUserNames)) {
			ResApproveMessage message = new ResApproveMessage();
			message.setMsgContent(shareMsg);
			message.setResApproveInfo(entity);
			message.setToUserCds(shareUserCds);
			
			//记录共享人的留言到某个节点下
			List<ResApproveNode> listResApproveNode = new ArrayList();
			String resApproveNodeId = "";
			for (ResApproveNode approveNode : entity.getResApproveNodes()) {
				if(approveNode.getUserCd().equalsIgnoreCase(SpringSecurityUtils.getCurrentUiid())){
					listResApproveNode.add(approveNode);
				}
			}
			if(listResApproveNode.size()>1){
				//获得共享人的节点，如果有多个节点，则优先选择最近未审核过的节点
				boolean ifSelected = false;
				for(int i=0;i<listResApproveNode.size();i++){
					ResApproveNode approveNode = (ResApproveNode)listResApproveNode.get(i);
					if(approveNode.getApproveOptionCd().equalsIgnoreCase("-1")){
						resApproveNodeId = approveNode.getResApproveNodeId();
						ifSelected = true;
						break;
					}
				}
				if(!ifSelected){
					//如果没有未审核节点，选择最大的已审核节点
					for(int i=0;i<listResApproveNode.size();i++){
						ResApproveNode approveNode = (ResApproveNode)listResApproveNode.get(i);
						if(!(approveNode.getApproveOptionCd().equalsIgnoreCase("-1"))){
							resApproveNodeId = approveNode.getResApproveNodeId();
						}
					}
				}
			}else if(listResApproveNode.size()==1){
				ResApproveNode approveNode = (ResApproveNode)listResApproveNode.get(0);
				resApproveNodeId = approveNode.getResApproveNodeId();
			}
			message.setResApproveNodeId(resApproveNodeId);
			resApproveMessageManager.saveResApproveMessage(message);
		}
		// 添加留言结束
		Struts2Utils.renderText("success");
		return null;
	}

	/**
	 * 完成留言操作
	 * @throws Exception
	 */
	private void completeShare() throws Exception {
		StringBuffer emailTitle = new StringBuffer();
		emailTitle.append(entity.getLandProject());
		if (StringUtils.isNotBlank(entity.getTitleName())) {
			emailTitle.append(entity.getTitleName());
		}
		List<String> lstUserCds = new ArrayList<String>();
		boolean isAllReplied = true;

		for (Iterator<ResApproveShare> it = entity.getResApproveShares().iterator(); it.hasNext();) {
			ResApproveShare approveShare = it.next();
			if (approveShare.getUserCd().equals(SpringSecurityUtils.getCurrentUiid())) {
				approveShare.setReplied(true);
				lstUserCds.add(approveShare.getCreator());
				resApproveShareManager.saveResApproveShare(approveShare);
			}
			if (approveShare == null || !approveShare.getReplied()) {
				isAllReplied = false;
			}
		}
		if (!entity.getResApproveShares().isEmpty()) {
			if (isAllReplied) {
				entity.setShareStatusCd("1");// 已回复
			}
		}
		resApproveInfoManager.update2JbpmTask(entity);
		String[] userCds = new String[lstUserCds.size()];
		lstUserCds.toArray(userCds);
		resApproveInfoManager.sendEmail(entity, "【网上审批共享人回复】" + emailTitle.toString(),
				SpringSecurityUtils.getCurrentUserName() + "已经回复，请查看", userCds);
		setIsShare(null);
	}

	/**
	 * 判断当前网批，是否已共享出去，并且对方未回复
	 * @param bizEntityId
	 * @return
	 */
	public boolean isResSharedUser(String bizEntityId) {
		return resApproveInfoManager.isResSharedUser(bizEntityId, true);
	}

	/**
	 * 取得同时被共享的其他人员
	 * 
	 * @param uiid
	 * @return
	 */
	private List<String> loadShare(String uiid) {
		List<String> listUserCds = new ArrayList<String>();
		if (StringUtils.isNotBlank(uiid)) {
			List<ResApproveShare> shares = entity.getResApproveShares();
			for (ResApproveShare s : shares) {
				if (s.getCreator().equalsIgnoreCase(uiid)) {
					if (!s.getUserCd().equals(SpringSecurityUtils.getCurrentUiid())) {
						listUserCds.add(s.getUserCd());
					}
				}
			}
		}
		loadParentShare(uiid, listUserCds);
		return listUserCds;
	}

	/**
	 * 递归获取共享人指定uiid的所有人员
	 * @param uiid
	 * @param listUserCds
	 * @return
	 */
	private List<String> loadParentShare(String uiid, List<String> listUserCds) {
		if (StringUtils.isNotBlank(uiid)) {
			List<ResApproveShare> shares = entity.getResApproveShares();
			for (ResApproveShare s : shares) {
				if (s.getUserCd().equalsIgnoreCase(uiid)) {
					if (!s.getCreator().equals(SpringSecurityUtils.getCurrentUiid())) {
						if (!listUserCds.contains(s.getCreator())) {
							listUserCds.add(s.getCreator());
							loadParentShare(s.getCreator(), listUserCds);
						}
					}
				}
			}
		}
		return listUserCds;
	}

	/**
	 * 取得被共享人
	 * @return
	 */
	public String loadShare() {
		List<ResApproveShare> shares = resApproveShareManager.loadShareList(getId());
		StringBuffer sbShareName = new StringBuffer("");
		String uiid = SpringSecurityUtils.getCurrentUiid();
		for (ResApproveShare shareTmp : shares) {
			if (shareTmp.getCreator().equalsIgnoreCase(uiid)) {
				sbShareName.append(shareTmp.getUserName()).append("  ");
			}
		}
		shareUserNames = sbShareName.toString();
		return "loadShare";
	}

	/**
	 * 判断当前人员是否是被共享人
	 * 
	 * @param approveInfo
	 * @return
	 */
	public boolean isSharedUser(ResApproveInfo approveInfo) {
		boolean isShared = false;
		if (approveInfo != null && approveInfo.getResApproveShares() != null) {
			for (ResApproveShare s : approveInfo.getResApproveShares()) {
				// 不管是否回复，都可以再次共享给其他人
				if (s.getUserCd().equals(SpringSecurityUtils.getCurrentUiid())) {
					isShared = true;
					isShare = "1";
					shareUser = s.getCreator();
					break;
				}
			}
		}
		return isShared;
	}

	/**
	 * 判断当前记录是否别人推送给我的。
	 * @param approveInfo
	 * @return
	 */
	private boolean isPushUser(ResApproveInfo approveInfo) {
		boolean isShared = false;
		if (approveInfo != null && approveInfo.getResApprovePushs() != null) {
			for (ResApprovePush s : approveInfo.getResApprovePushs()) {
				if (s.getUserCd().equals(SpringSecurityUtils.getCurrentUiid())) {
					isShared = true;
					break;
				}
			}
		}
		return isShared;
	}

	/**
	 * 审批状态
	 * 
	 * @return
	 */
	public Map<String, String> getMapStatus() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.SP_STATUS_QZ);
	}

	public Map<String, String> getMapNode() {

		return resNodeManager.getNodeMap();
	}

	/**
	 * 审批意见
	 * 
	 * @return
	 */
	public Map<String, String> getMapOption() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.SP_OPTION);
	}

	public Map<String, String> getMapOptionZyps() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.SP_OPTION_ZYPS);
	}

	@Override
	public ResApproveInfo getModel() {
		return entity;
	}

	public BaseTemplate getTemplateBean() {
		return templateBean;
	}

	public void setTemplateBean(BaseTemplate contentBean) {
		this.templateBean = contentBean;
	}

	
	public String getResModuleCd() {
		return resModuleCd;
	}

	public void setResModuleCd(String resModuleCd) {
		this.resModuleCd = resModuleCd;
	}

	public String getResAuthTypeCd() {
		return resAuthTypeCd;
	}

	public void setResAuthTypeCd(String resAuthTypeId) {
		this.resAuthTypeCd = resAuthTypeId;
	}

	public String getEntityTmpId() {
		return entityTmpId;
	}

	public void setEntityTmpId(String entityTmpId) {
		this.entityTmpId = entityTmpId;
	}

	public String getShareUserCds() {
		return shareUserCds;
	}

	public void setShareUserCds(String shareUserCds) {
		this.shareUserCds = shareUserCds;
	}

	public String getShareUserNames() {
		return shareUserNames;
	}

	public void setShareUserNames(String shareUserNames) {
		this.shareUserNames = shareUserNames;
	}

	public String getApproveCdSrh() {
		return approveCdSrh;
	}

	public void setApproveCdSrh(String approveCdSrh) {
		this.approveCdSrh = approveCdSrh;
	}

	public String getFilter_LIKES_userName() {
		return filter_LIKES_userName;
	}

	public void setFilter_LIKES_userName(String filterLIKESUserName) {
		filter_LIKES_userName = filterLIKESUserName;
	}

	public String getFilter_LIKES_statusCd() {
		return filter_LIKES_statusCd;
	}

	public void setFilter_LIKES_statusCd(String filterLIKESStatusCd) {
		filter_LIKES_statusCd = filterLIKESStatusCd;
	}

	public String getIsMyApprove() {
		return isMyApprove;
	}

	public void setIsMyApprove(String isMyApprove) {
		this.isMyApprove = isMyApprove;
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

	public String getMyNode() {
		return myNode;
	}

	public void setMyNode(String myNode) {
		this.myNode = myNode;
	}

	public List<ResApproverUser> getApproverUsers() {
		return approverUsers;
	}

	public void setApproverUsers(List<ResApproverUser> approverUsers) {
		this.approverUsers = approverUsers;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getMyMod() {
		return myMod;
	}

	/**
	 * 查看审批历史
	 */
	public String showApproveHis() {
		String hql = "from ResApproveHis rah where rah.resApproveInfo.resApproveInfoId=? and rah.approveOptionCd!='3' order by rah.createdDate";
		approveHises = approveHisManager.find(hql, getId());
		return "showHis";
	}

	/**
	 * 推送退回
	 */
	public String doPushBack() throws Exception {

		String backMsg = Struts2Utils.getParameter("backMsg");
		entity = resApproveInfoManager.getEntity(getId());

		for (ResApprovePush push : entity.getResApprovePushs()) {
			if (StringUtils.equals(SpringSecurityUtils.getCurrentUiid(), push.getUserCd())) {
				resApprovePushManager.delete(push);
			}
		}

		StringBuffer emailTitle = new StringBuffer();
		emailTitle.append(entity.getLandProject());
		if (StringUtils.isNotBlank(entity.getTitleName())) {
			emailTitle.append(entity.getTitleName());
		}
		StringBuffer mailMsg = new StringBuffer();
		mailMsg.append(SpringSecurityUtils.getCurrentUserName());
		mailMsg.append("已将您推送的网批：").append(emailTitle.toString()).append(" 退回给您，请查看<br/>");
		mailMsg.append("原　因: ").append(backMsg);
		resApproveInfoManager.sendEmail(entity, "【网上审批推送退回】" + emailTitle.toString(), mailMsg.toString(),
				new String[] { entity.getUserCd() });

		Struts2Utils.renderText("success");
		return null;
	}

	/**
	 * 快速搜索网批数据
	 * 
	 * @return
	 */
	public void queryAppAuth() {
		// 输入合同编号
		String resNo = Struts2Utils.getParameter("value");
		if (StringUtils.isBlank(resNo))
			return;
		resNo = StringUtils.trim(resNo);
		// List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer(
				"select a from ResApproveInfo a where a.statusCd=:statusCd and a.templetCd in(:templetCd) ");
		param.put("statusCd", "2");
		// 其中： ZCGL_HTQS_53 营销类
		// ZCGL_HTQS_55
		// CBGFGL_YBDW_35 营销(策划公司、销售代理公司、市调机构)
		/*param.put("queryAuthTypeCd", new Object[] { "ZCGL_HTQS_30", "ZCGL_HTQS_40", "ZCGL_HTQS_50", "ZCGL_HTQS_60",
				"ZCGL_HTQS_53", "ZCGL_HTQS_55", "CBGFGL_YBDW_35" ,
				"SYGS_ZCGL_DB_10","SYGS_ZCGL_DB_20","SYGS_ZCGL_DB_30","SYGS_ZCGL_DB_40","SYGS_ZCGL_DB_50","SYGS_ZCGL_DB_60","SYGS_ZCGL_DB_70"});
		*/
		param.put("templetCd", new Object[] {"116","117","253","254","255","244","118","272","141","16"});
		hql.append(" and (a.approveCd || a.serialNo like :p1 or a.titleName like :p1 or a.displayNo =:p2)");
		param.put("p1", "%" + resNo + "%");
		Long srhDisplayNo = 0l;
		if (Pattern.matches(Constants.PATTERN_NUM, resNo)) {
			srhDisplayNo = Long.valueOf(resNo);
		}
		param.put("p2", srhDisplayNo);
		page.setPageSize(10);
		page = resApproveInfoManager.findPage(page, hql.toString(), param);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (ResApproveInfo info : page.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("resNo", info.getDisplayNo().toString());// 查询号
			map.put("titleName", info.getTitleName());// 标题
			map.put("resId", info.getResApproveInfoId());
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}

	/**
	 * 网批表单间关联时调用，快速搜索
	 * @throws Exception
	 */
	public void quickSearch() throws Exception {
		String value = Struts2Utils.getParameter("value");
		String authTypeCd = Struts2Utils.getParameter("authTypeCd");
		String statusCd = Struts2Utils.getParameter("statusCd");
		String templateCd = Struts2Utils.getParameter("templateCd");
		page.setPageSize(15);
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select r from ResApproveInfo r where ");
		hql.append("(");
		hql.append(" exists (select '' from ResApproveShareHis s where r.resApproveInfoId = s.resApproveInfo.resApproveInfoId and s.toUserCds like :userCd_like )");
		hql.append(" or ");
		hql.append(" exists (select '' from ResApprovePush s where r.resApproveInfoId = s.resApproveInfo.resApproveInfoId and s.userCd = :userCd )");
		hql.append(" or ");
		hql.append(" exists (select '' from ResApproveHis s where r.resApproveInfoId = s.resApproveInfo.resApproveInfoId and s.userCd = :userCd )");
		hql.append(" or ");
		hql.append(" r.userCd = :userCd ");
		hql.append(")");
		if (StringUtils.isNotBlank(authTypeCd)) {
			hql.append(" and authTypeCd in (:authTypeCd)");
			param.put("authTypeCd", authTypeCd.split(","));
		}
		if (StringUtils.isNotBlank(templateCd)) {
			hql.append(" and templetCd = :templateCd");
		}
		hql.append(" and statusCd = :statusCd ");
		hql.append(" and (r.approveCd || r.serialNo like :p1 or r.titleName like :p1 or r.displayNo =:p2)");
		String curUiid = SpringSecurityUtils.getCurrentUiid();
		param.put("userCd", curUiid);
		param.put("userCd_like", "%" + curUiid + ";%");
		param.put("p1", "%" + value + "%");
		Long srhDisplayNo = 0l;
		if (Pattern.matches(Constants.PATTERN_NUM, value)) {
			srhDisplayNo = Long.valueOf(value);
		}
		param.put("p2", srhDisplayNo);
		param.put("templateCd", templateCd);
		param.put("statusCd", statusCd);
		page = resApproveInfoManager.findPage(page, hql.toString(), param);

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for (ResApproveInfo info : page.getResult()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("approveCd", info.getDisplayNo().toString());// 查询号
			map.put("titleName", info.getTitleName());// 标题
			map.put("id", info.getResApproveInfoId());
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}

	public String detail() throws Exception {
		return "detail";
	}

	public List<ResApproveHis> getApproveHises() {
		return approveHises;
	}

	public void setApproveHises(List<ResApproveHis> approveHises) {
		this.approveHises = approveHises;
	}

	public List<ResApproveInfoVO> getByMe() {
		return byMe;
	}

	public void setByMe(List<ResApproveInfoVO> byMe) {
		this.byMe = byMe;
	}

	public List<ResApproveInfoVO> getByOther() {
		return byOther;
	}

	public void setByOther(List<ResApproveInfoVO> byOther) {
		this.byOther = byOther;
	}

	public AppAttachFileManager getAttachFileManager() {
		return attachFileManager;
	}

	public void setAttachFileManager(AppAttachFileManager attachFileManager) {
		this.attachFileManager = attachFileManager;
	}

	public List<AppAttachFile> getChiefCopyList() {
		return chiefCopyList;
	}

	public void setChiefCopyList(List<AppAttachFile> chiefCopyList) {
		this.chiefCopyList = chiefCopyList;
	}

	public String getFilter_LIKES_approveUserCd() {
		return filter_LIKES_approveUserCd;
	}

	public void setFilter_LIKES_approveUserCd(String filterLIKESApproveUserCd) {
		filter_LIKES_approveUserCd = filterLIKESApproveUserCd;
	}

	public List<ResApproveInfoVO> getByApprove() {
		return byApprove;
	}

	public String getPushUserCds() {
		return pushUserCds;
	}

	public void setPushUserCds(String pushUserCds) {
		this.pushUserCds = pushUserCds;
	}

	public String getPushUserNames() {
		return pushUserNames;
	}

	public void setPushUserNames(String pushUserNames) {
		this.pushUserNames = pushUserNames;
	}

	public String getFilter_LIKES_approveUserName() {
		return filter_LIKES_approveUserName;
	}

	public void setFilter_LIKES_approveUserName(String filterLIKESApproveUserName) {
		filter_LIKES_approveUserName = filterLIKESApproveUserName;
	}

	public String getFilter_LIKES_landproject() {
		return filter_LIKES_landproject;
	}

	public void setFilter_LIKES_landproject(String filterLIKESLandproject) {
		filter_LIKES_landproject = filterLIKESLandproject;
	}

	public String getFilter_LIKES_titlename() {
		return filter_LIKES_titlename;
	}

	public void setFilter_LIKES_titlename(String filterLIKESTitlename) {
		filter_LIKES_titlename = filterLIKESTitlename;
	}

	public String getFilter_LIKES_randomNo() {
		return filter_LIKES_randomNo;
	}

	public void setFilter_LIKES_randomNo(String filterLIKESRandomNo) {
		filter_LIKES_randomNo = filterLIKESRandomNo;
	}

	public List<ResApproveInfoVO> getByShare() {
		return byShare;
	}

	public List<ResApproveInfoVO> getByPush() {
		return byPush;
	}

	public Map<String, List<ResApproveInfoVO>> getMapResult() {
		return mapResult;
	}

	public String getSelectedOrderBy() {
		return selectedOrderBy;
	}

	public void setSelectedOrderBy(String selectedOrderBy) {
		this.selectedOrderBy = selectedOrderBy;
	}

	public String getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(String selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	public String getFilter_LIKES_userCd() {
		return filter_LIKES_userCd;
	}

	public void setFilter_LIKES_userCd(String filterLIKESUserCd) {
		filter_LIKES_userCd = filterLIKESUserCd;
	}

	public Map<String, String> getMapReturnToNode() {
		return mapReturnToNode;
	}

	public void setMapReturnToNode(Map<String, String> mapReturnToNode) {
		this.mapReturnToNode = mapReturnToNode;
	}

	public String getIsCanRollback() {
		return isCanRollback;
	}

	public void setIsCanRollback(String isCanRollback) {
		this.isCanRollback = isCanRollback;
	}

	public String getIsAcc() {
		return isAcc;
	}

	public void setIsAcc(String isAcc) {
		this.isAcc = isAcc;
	}

	public String getShareUser() {
		return shareUser;
	}

	public String getIsShare() {
		return isShare;
	}

	public String getTempletFileName() {
		return templetFileName;
	}

	public void setTempletFileName(String templetFileName) {
		this.templetFileName = templetFileName;
	}

	public Map<String, String> getMapGroupNodes() {
		return mapGroupNodes;
	}

	public String getIsCanGiveMe() {
		return isCanGiveMe;
	}

	public boolean isMutiNode(String nodeCd) {
		return ResConstants.allCustomNode_muti.contains(nodeCd);
	}

	public boolean isPublish() {
		return publish;
	}

	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}

	public ResApproveDelay getGbDelay() {
		return gbDelay;
	}

	public void setGbDelay(ResApproveDelay gbDelay) {
		this.gbDelay = gbDelay;
	}

	public ResApproveNode getGbProcessNode() {
		return gbProcessNode;
	}

	public void setGbProcessNode(ResApproveNode gbProcessNode) {
		this.gbProcessNode = gbProcessNode;
	}

	public String getFilter_LIKES_authTypeName() {
		return filter_LIKES_authTypeName;
	}

	public void setFilter_LIKES_authTypeName(String filterLIKESAuthTypeName) {
		filter_LIKES_authTypeName = filterLIKESAuthTypeName;
	}

	public String getFilter_LIKES_authTypeCd() {
		return filter_LIKES_authTypeCd;
	}

	public void setFilter_LIKES_authTypeCd(String filterLIKESAuthTypeCd) {
		filter_LIKES_authTypeCd = filterLIKESAuthTypeCd;
	}

	public String getListMode() {
		return listMode;
	}

	public void setListMode(String listMode) {
		this.listMode = listMode;
	}

	public String getQsCondition() {
		return qsCondition;
	}

	public void setQsCondition(String qsCondition) {
		this.qsCondition = qsCondition;
	}

	public ResAuthType getResAuthType() {
		return resAuthType;
	}

	public String getModulePath() {
		return modulePath;
	}

	public ResPermission getPermission() {
		return permission;
	}

	public void setPermission(ResPermission permission) {
		this.permission = permission;
	}

	public boolean isAboutInOut() {
		return aboutInOut;
	}

	public List<ResApproveMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ResApproveMessage> messages) {
		this.messages = messages;
	}

	public Map<String, String> getMapCustomSteps() {
		return mapCustomSteps;
	}

	public void setMapCustomSteps(Map<String, String> mapCustomSteps) {
		this.mapCustomSteps = mapCustomSteps;
	}

	public List<ResApproveStep> getSteps() {
		return steps;
	}

	public void setSteps(List<ResApproveStep> steps) {
		this.steps = steps;
	}

	public List<ResApproveNode> getApproveNodes() {
		return approveNodes;
	}

	public void setApproveNodes(List<ResApproveNode> approveNodes) {
		this.approveNodes = approveNodes;
	}

	public Map<String, Boolean> getMapNodeLock() {
		return mapNodeLock;
	}

	public String getFilter_LIKES_displayNo() {
		return filter_LIKES_displayNo;
	}

	public void setFilter_LIKES_displayNo(String filter_LIKES_displayNo) {
		this.filter_LIKES_displayNo = filter_LIKES_displayNo;
	}

	public String getQuicksearchValue() {
		return quicksearchValue;
	}

	public void setQuicksearchValue(String quicksearchValue) {
		this.quicksearchValue = quicksearchValue;
	}

	public Map<String, String> getOpinions() {
		return opinions;
	}

	public String getSelectOpinion() {
		return selectOpinion;
	}

	public void setSelectOpinion(String selectOpinion) {
		this.selectOpinion = selectOpinion;
	}

	/**
	 * 取得模块类别Cd，默认取‘网上审批’
	 * @return
	 */
	public String getModuleTypeCdSrh() {
		if (StringUtils.isBlank(moduleTypeCdSrh)){
			moduleTypeCdSrh=ResConstants.MODULE_TYPE_CD_RES;
		}
		return moduleTypeCdSrh;
	}

	public void setModuleTypeCdSrh(String moduleTypeCd) {
		this.moduleTypeCdSrh = moduleTypeCd;
	}

	public String getSelectNodeCd() {
		return selectNodeCd;
	}

	public void setSelectNodeCd(String selectNodeCd) {
		this.selectNodeCd = selectNodeCd;
	}

	public List<ResNode> getUserNodes() {
		return userNodes;
	}

	public void setUserNodes(List<ResNode> userNodes) {
		this.userNodes = userNodes;
	}

	public String getContFollower() {
		return contFollower;
	}

	public void setContFollower(String contFollower) {
		this.contFollower = contFollower;
	}

	/**
	 * 锁定记录（该方法已弃用）
	 * @return
	 * @throws Exception
	 */
	public String lock() throws Exception {
		if (StringUtils.isNotEmpty(entity.getLockUser())) {
			Struts2Utils.renderText("该记录已经被 " + CodeNameUtil.getUserNameByCd(entity.getLockUser()) + " 锁定！");
		} else {
			resApproveInfoManager.lock(entity);
			Struts2Utils.renderText("success");
		}
		return null;
	}

	/**
	 * 解锁记录（该方法已弃用）
	 * @return
	 * @throws Exception
	 */
	public String unlock() throws Exception {
		resApproveInfoManager.unlock(entity);
		Struts2Utils.renderText("success");
		return null;
	}

	/**
	 * ajax 提示： xxx 于 yyyy-mm-dd hh:mm:ss 锁定!
	 * 
	 * @return
	 */
	public String loadLockTip() {

		HttpServletRequest request = ServletActionContext.getRequest();
		String id = StringUtils.trimToEmpty(request.getParameter("id"));
		ResApproveInfo tmp = resApproveInfoManager.getEntity(id);
		if (tmp != null) {
			try {
				String lockUserUiid = tmp.getLockUser();
				Date lockDate = tmp.getLockDate();
				if (lockDate != null) {
					Struts2Utils.renderText(CodeNameUtil.getUserNameByCd(lockUserUiid) + " 于 "
							+ DateParser.formatDate(lockDate, DateParser.FORMAT_STR_WITH_TIME_S) + " 锁定! ");
				}
			} catch (Exception e) {
			}
		}
		Struts2Utils.renderText("");
		return null;
	}
	/**
	 * 取得银行账号列表，在‘银行付款单’使用
	 * @return
	 */
	public String loadBankNo(){
		String companyName = Struts2Utils.getParameter("companyName");
		Map<String, String> mapAccout  = bizBankAccountManager.loadAccouts(companyName);
		Struts2Utils.renderJson(mapAccout);
		return null;
	}
}
