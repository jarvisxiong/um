package com.hhz.ump.web.oa;

import java.io.InputStream;
import java.sql.Clob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppParamManager;
import com.hhz.ump.dao.app.UserPoolManager;
import com.hhz.ump.dao.jbpm.JbpmTaskCandidateManager;
import com.hhz.ump.dao.jbpm.JbpmTaskManager;
import com.hhz.ump.dao.oa.OaAllAttentionManager;
import com.hhz.ump.dao.oa.OaMeetingCommentManager;
import com.hhz.ump.dao.oa.OaMeetingManager;
import com.hhz.ump.dao.plan.PlanOperationLogManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.app.UserPool;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.oa.OaMeeting;
import com.hhz.ump.entity.oa.OaMeetingComment;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.util.TreeNode;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.vo.UserDisplayInfo;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasUser;

/**
 * 
 * 类名 OaMeetingAction 创建者 lijin 创建日期 2010-3-30 描述 会议纪要Action类
 */
@Namespace("/oa")
@Results( {
		@Result(name = CrudActionSupport.RELOAD, location = "oa-meeting.action", type = "redirect", params = { "oaMeetingGenren", "${oaMeetingGenren}" }),
		@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName", "is", "contentDisposition",
				"attachment;filename=${downFileName}.xls" }) })
public class OaMeetingAction extends CrudActionSupport<OaMeeting> {

	private static final long serialVersionUID = 4146371892800009617L;

	// 流水号的前缀
	private static final String WATER_NUM_PREFIX = "CEO-";
	private static final String ATT_TYPE_EXIST_RES = "EXIST_RES";
	private static final String ATT_TYPE_EXIST_COR = "EXIST_COR";
	private static final String ATT_TYPE_EXIST_VIEW = "EXIST_VIEW";
	private String currentUiid;

	@Autowired
	private AppParamManager appParamManager;

	@Autowired
	private UserPoolManager userPoolManager;

	@Autowired
	private OaMeetingCommentManager oaMeetingCommentManager;

	@Autowired
	private AppAttachFileManager appAttachFileManager;

	@Autowired
	private JbpmTaskManager jbpmTaskManager;

	@Autowired
	private JbpmTaskCandidateManager jbpmTaskCandidateManager;
	@Autowired
	private PlanOperationLogManager planOperationLogManager;
	@Autowired
	private OaAllAttentionManager oaAllAttentionManager;

	private String opened_entityid; // 直接打开的entityId

	// 每页显示10条指令单
	private Page<OaMeeting> page = new Page<OaMeeting>(10);

	private Page<AppAttachFile> attachPage;

	public OaMeeting entity;

	private String personal;

	@Autowired
	private OaMeetingManager oaMeetingManager;

	public String getCurrentUiid() {
		return currentUiid;
	}

	public void setCurrentUiid(String currentUiid) {
		this.currentUiid = currentUiid;
	}

	// 用于选择会议人员和上传文件的临时实体ID
	private String entityTempId;

	// 反馈
	private Clob comment;

	private boolean oaMeetingAdmin = SpringSecurityUtils.hasRole("A_OAMEET_ADMIN");

	private boolean oaMeetingGenren = false;

	// 用来构造人员选择列表
	private List<WsPlasOrg> orgList;
	private List<WsPlasUser> attendeeList;
	private List<OaMeetingComment> commentList;

	private String orgBizCd;
	private String attendeeType;
	private String attendeeIds;
	private String bizEntityId;
	private String orderBy;
	private String order;

	private Map<String, String> mapResPersonNames = new HashMap<String, String>();
	private Map<String, String> mapCorPersonNames = new HashMap<String, String>();
	private Map<String, String> mapMeetingTips = new HashMap<String, String>();

	// 过滤条件参数
	private String filter_LIKES_waterNumber;
	private String filter_EQS_hiddenFlg;
	private String filter_EQS_status;
	private String filter_GED_createdDate;
	private String filter_LED_createdDate;
	private String filter_GED_targetDate;
	private String filter_LED_targetDate;

	// 用来标识翻页还是搜索
	private String searchMode;
	// 用来标识附件是否被更新了
	private String isAttachUpdated;

	// 标识是查看指令单还是查看专项任务
	private String moduleCd;
	// 标识更新字段,以便记录日志
	private String type;

	private String myTask; // 是否从邮件报告中进入，如果是搜索我的记录

	private String uiid = SpringSecurityUtils.getCurrentUiid();

	private String downFileName;

	private InputStream is;

	private String changeStats;
	private String strModule; // 当前的模块 zc:地产指令单；bis:商业指令单

	private boolean changeFlag;

	public String getChangeStats() {
		return changeStats;
	}

	public void setChangeStats(String changeStats) {
		this.changeStats = changeStats;
	}

	public boolean isChangeFlag() {
		return changeFlag;
	}

	public void setChangeFlag(boolean changeFlag) {
		this.changeFlag = changeFlag;
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

	@Override
	public String list() throws Exception {
		if (canViewOaMeetings()) {
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
			List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
			if (null != strModule && !"".equalsIgnoreCase(strModule)) {
				filters.add(new PropertyFilter("EQS_strModule", strModule));
			}
			filters.add(new PropertyFilter("EQS_isDeleted", "0"));
			filters.add(new PropertyFilter("EQS_hiddenFlg", "0"));
			if (StringUtils.isNotBlank(getId())) {
				filters.add(new PropertyFilter("EQS_oaMeetingId", getId()));
			} else {
				if (oaMeetingManager.getPersonalUserId(uiid) && oaMeetingManager.getPersonalUserIdAdmin(uiid) != true) {
					filters.add(new PropertyFilter("LIKES_creator_OR_responsiblePerson_OR_coordinatePerson_OR_visibleToUsers", uiid));
					oaMeetingGenren = true;
				} else if (oaMeetingManager.getPersonalUserId(uiid) != true && oaMeetingAdmin != true && oaMeetingManager.getPersonalUserIdAdmin(uiid) != true) {
					filters.add(new PropertyFilter("LIKES_responsiblePerson_OR_coordinatePerson_OR_visibleToUsers", uiid));
				}
			}
			if (oaMeetingManager.getPersonalUserIdAdmin(uiid) == true && oaMeetingManager.getPersonalUserId(uiid) == true) {
				oaMeetingGenren = true;
			}
			if (null != myTask && "true".equalsIgnoreCase(myTask)) {
				filters.add(new PropertyFilter("LIKES_responsiblePerson_OR_coordinatePerson", uiid));
			}
			Constants.filters = null;
			page = oaMeetingManager.findPage(page, filters);
			entityTempId = RandomUtils.generateTmpEntityId();
			buildMeetingAttributes(page.getResult());
			this.currentUiid = SpringSecurityUtils.getCurrentUiid();
		} else {
			page.setPageSize(0);
			page.setPageNo(0);
			page.setTotalCount(0);
		}
		return SUCCESS;
	}

	/**
	 * 返回指令单列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String meetingList() throws Exception {
		if (canViewOaMeetings()) {
			buildMeetingList();
		} else {
			page.setPageSize(0);
			page.setPageNo(0);
			page.setTotalCount(0);
		}

		return "meetingList";
	}

	/**
	 * 判断当前用户是否能够查看指令单记录
	 * 
	 * @return
	 */
	private boolean canViewOaMeetings() {
		if (SpringSecurityUtils.hasRole("A_OAMEET_ADMIN"))
			return true;
		if (oaMeetingManager.getPersonalUserId(uiid))
			return true;
		if (StringUtils.isNotBlank(getId()))
			return true;

		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_typeCd", "oaMeeting"));
		List<UserPool> userPools = userPoolManager.find(filters);
		String curUserId = SpringSecurityUtils.getCurrentUiid();
		if (userPools.size() > 0) {
			UserPool userPool = userPools.get(0);
			if (userPool.getUserList().indexOf(";" + curUserId + ";") != -1)
				return true;
			else
				return oaMeetingManager.getUserById(curUserId);
		}
		return false;
	}

	/**
	 * 构造指令单列表
	 */
	private void buildMeetingList() {

		if (StringUtils.isNotBlank(orderBy)) {
			page.setOrderBy(orderBy + ",createdDate");
			page.setOrder(order + "," + Page.DESC);
		} else {
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		}

		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// Constants.filters = filters;
		// 录入起止时间 createdDate
		// 目标起止时间 targetDate
		// 完成起止时间
		// 是否显示 hiddenFlg
		// 执行状态 status

		// 若"可见人员"字段不空,则只有当前用户存在设置字段才能看得到;否则所有人都看得到
		// 若"是否显示"字段为空,默认搜索属性为"显示"的记录
		// 默认搜索非删除记录
		PropertyFilter filter = new PropertyFilter("EQS_isDeleted", "0");
		if (null != strModule && !"".equalsIgnoreCase(strModule)) {
			filters.add(new PropertyFilter("EQS_strModule", strModule));
		}
		if (StringUtils.isBlank(filter_EQS_hiddenFlg)) {
			filters.add(new PropertyFilter("EQS_hiddenFlg", "0"));
		}
		if (oaMeetingManager.getPersonalUserId(uiid) && oaMeetingManager.getPersonalUserIdAdmin(uiid) != true) {
			filters.add(new PropertyFilter("LIKES_creator_OR_responsiblePerson_OR_coordinatePerson_OR_visibleToUsers", uiid));
			oaMeetingGenren = true;
		} else if (oaMeetingManager.getPersonalUserId(uiid) != true && oaMeetingAdmin != true && oaMeetingManager.getPersonalUserIdAdmin(uiid) != true) {
			filters.add(new PropertyFilter("LIKES_responsiblePerson_OR_coordinatePerson_OR_visibleToUsers", uiid));
		}
		filters.add(filter);
		if (oaMeetingManager.getPersonalUserIdAdmin(uiid) == true && oaMeetingManager.getPersonalUserId(uiid) == true) {
			oaMeetingGenren = true;
		}
		if ("query".equalsIgnoreCase(searchMode)) {
			page.setPageNo(1);
		}
		if (null != myTask && "true".equalsIgnoreCase(myTask)) {
			filters.add(new PropertyFilter("LIKES_responsiblePerson_OR_coordinatePerson", uiid));
		}

		page = oaMeetingManager.findPage(page, filters);
		entityTempId = RandomUtils.generateTmpEntityId();

		buildMeetingAttributes(page.getResult());

		// 初始化当前用户UIID,前台判断责任人使用
		Constants.filters = filters;
		this.currentUiid = SpringSecurityUtils.getCurrentUiid();
	}

	/**
	 * 遍历指令单列表构造前台展现数据
	 * 
	 * @param oaMeetings
	 */
	private void buildMeetingAttributes(List<OaMeeting> meetings) {
		String id = null;
		for (OaMeeting m : meetings) {
			id = m.getOaMeetingId();
			mapResPersonNames.put(id, CodeNameUtil.getUserNamesByUiids(m.getResponsiblePerson(), ";"));
			mapCorPersonNames.put(id, CodeNameUtil.getUserNamesByUiids(m.getCoordinatePerson(), ";"));
			oaMeetingManager.orderComments(m.getOaMeetingComments());

			// 鼠标放在标题上时，显示标题和最新的3条评论
			StringBuffer tip = new StringBuffer();
			Clob b = m.getBusiness();
			tip.append("<p style='font-size:14px;'>" + EncodeUtils.htmlEscape(Util.clob2String2(b)) + "</p>");
			List<OaMeetingComment> comments = m.getOaMeetingComments();
			DateFormat df = new SimpleDateFormat("MM-dd");
			if (comments.size() > 0) {
				tip.append("<h3 style='margin-top: 10px; font-size: 14px;'>最近回复：</h3><ul class='commentList' style='margin-top: 3px;font-size: 14px;'>");
				int size = comments.size() >= 3 ? 3 : comments.size();
				for (int i = 0; i < size; i++) {
					OaMeetingComment c = comments.get(i);
					String userName = CodeNameUtil.getUserNameByCd(c.getCreator());
					tip.append("<li><strong>" + userName + "&nbsp;" + df.format(c.getCreatedDate()) + "</strong>："
							+ EncodeUtils.htmlEscape(Util.clob2String2(c.getContent())) + "</li>");
				}
				tip.append("</ul>");
			}
			mapMeetingTips.put(id, tip.toString());
		}
	}

	@Override
	public String delete() throws Exception {
		return RELOAD;
	}

	/**
	 * 输入新的指令单
	 */
	@Override
	public String input() throws Exception {
		entity.setIsDeleted("0");
		entity.setHiddenFlg("0");
		entity.setAttentionFlg("0");
		entity.setStatus("1");
		oaMeetingManager.saveOaMeeting(entity);

		// 保存评论
		if (comment.length() > 0) {
			OaMeetingComment oaMeetingComment = new OaMeetingComment();
			oaMeetingComment.setOaMeeting(entity);
			oaMeetingComment.setContent(this.getComment());
			oaMeetingCommentManager.saveOaMeetingComment(oaMeetingComment);
		}

		// 保存附件
		appAttachFileManager.updateTmpFile(entityTempId, entity.getOaMeetingId());

		updateToJbpmTask();

		// 添加日志
		StringBuffer sbLog = new StringBuffer();
		sbLog.append("添加新的任务,编号:");
		sbLog.append(entity.getWaterNumber());
		sbLog.append(",事项：");
		sbLog.append(Util.clob2String(entity.getBusiness()));
		sbLog.append(",负责人：");
		sbLog.append(CodeNameUtil.getUserNamesByUiids(entity.getResponsiblePerson(), ";"));
		sbLog.append(",配合人：");
		sbLog.append(CodeNameUtil.getUserNamesByUiids(entity.getCoordinatePerson(), ";"));
		sbLog.append(",开始日期：");
		sbLog.append(DateUtil.parseDateToString(entity.getCreatedDate()));
		sbLog.append(",目标日期：");
		sbLog.append(DateUtil.parseDateToString(entity.getTargetDate()));
		planOperationLogManager.addCEOTaskLog(entity.getOaMeetingId(), entity.getWaterNumber(), PlanOperationLogManager.OPERATION_TYPE_ADD, sbLog.toString());
		return RELOAD;
	}

	@Override
	public String save() throws Exception {
		prepareEntity();

		oaMeetingManager.saveOaMeeting(entity);
		String status = entity.getStatus();
		if (DictContants.OA_MEETING_STATUS_COMPLETE.equals(status) || "1".equals(entity.getHiddenFlg()) || "1".equals(entity.getIsDeleted())) {
			deleteJbpmTask();
		} else {
			updateToJbpmTask();
		}

		// 添加日志
		saveLog();

		Struts2Utils.renderText("done");
		return null;
	}

	private void prepareEntity() {
		entity = oaMeetingManager.getValidEntity(this.getId());
	}

	/**
	 * 添加保存时的操作日志
	 */
	private void saveLog() throws Exception {
		if (StringUtils.isEmpty(type))
			return;
		String logMsg = "";
		String operType = PlanOperationLogManager.OPERATION_TYPE_UPDATE;
		if ("business".equals(type)) {
			logMsg = "修改事项信息为：" + Util.clob2String(entity.getBusiness());
		} else if ("resPerson".equals(type)) {
			logMsg = "修改责任人为：" + CodeNameUtil.getUserNamesByUiids(entity.getResponsiblePerson(), ";");
		} else if ("corPerson".equals(type)) {
			logMsg = "修改配合人为：" + CodeNameUtil.getUserNamesByUiids(entity.getCoordinatePerson(), ";");
		} else if ("targetDate".equals(type)) {
			logMsg = "修改目标日期为：" + DateUtil.parseDateToString(entity.getTargetDate());
		} else if ("preComplete".equals(type)) {
			logMsg = "设置状态为：预完成";
		} else if ("complete".equals(type)) {
			logMsg = "设置状态为：完成";
		} else if ("revert".equals(type)) {
			logMsg = "恢复状态为：进行中";
		} else if ("delete".equals(type)) {
			operType = PlanOperationLogManager.OPERATION_TYPE_DELETE;
			logMsg = "删除指令单,编号为：" + entity.getWaterNumber();
		} else if ("hide".equals(type)) {
			logMsg = "设置状态为：隐藏";
		} else if ("show".equals(type)) {
			logMsg = "设置状态为：显示";
		}
		planOperationLogManager.addCEOTaskLog(getId(), entity.getWaterNumber(), operType, logMsg);
	}

	public String attachLog() throws Exception {
		prepareEntity();
		String flag = Struts2Utils.getParameter("flag");
		String logMsg = "";
		String operType = "";
		if ("del".equals(flag)) {
			String fileId = Struts2Utils.getParameter("fileId");
			String fileName = appAttachFileManager.getEntity(fileId).getRealFileName();
			logMsg = "删除附件：" + fileName;
			operType = PlanOperationLogManager.OPERATION_TYPE_DELETEATTACH;
		} else {
			String fileName = Struts2Utils.getParameter("fileName");
			logMsg = "上传附件：" + fileName;
			operType = PlanOperationLogManager.OPERATION_TYPE_UPLOADATTACH;
		}
		planOperationLogManager.addCEOTaskLog(getId(), entity.getWaterNumber(), operType, logMsg);
		return null;
	}

	/**
	 * 前台用户池修改时保存用户池
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveUserPool() throws Exception {
		String PersonalAll = Struts2Utils.getParameter("PersonalAll");
		PersonalAll = PersonalAll.trim();
		if (SpringSecurityUtils.hasRole("A_OAMEET_ADMIN") && PersonalAll.equals("manager")) {
			if (userPoolManager.saveUserPoolPerTypeCd(UserPoolManager.USER_POOL_TYPE_OAMEETING, getAttendeeIds(), PersonalAll)) {
				// 添加日志
				String logMsg = "用户池人员更改为：" + CodeNameUtil.getUserNamesByUiids(getAttendeeIds(), ";");
				planOperationLogManager.addCEOTaskLog("用户池", "用户池", PlanOperationLogManager.OPERATION_TYPE_UPDATE, logMsg);
				Struts2Utils.renderText("done");
			} else {
				Struts2Utils.renderText("fail");
			}
		} else {
			if (userPoolManager.saveUserPoolPerTypeCd(UserPoolManager.USER_POOL_TYPE_OAMEETINGGEREN, getAttendeeIds(), PersonalAll)) {
				// 添加日志
				String logMsg = "个人管理员用户池人员更改为：" + CodeNameUtil.getUserNamesByUiids(getAttendeeIds(), ";");
				planOperationLogManager.addCEOTaskLog("个人管理员用户池", "个人管理员用户池", PlanOperationLogManager.OPERATION_TYPE_UPDATE, logMsg);
				Struts2Utils.renderText("done");
			} else {
				Struts2Utils.renderText("fail");
			}
		}

		return null;
	}

	/**
	 * 获取两级用户池用户
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchUserAllPool() throws Exception {
		List<UserPool> userPools = new ArrayList<UserPool>();
		List<UserPool> userPoolsPersons = new ArrayList<UserPool>();
		userPools = userPoolManager.getUserPoolPersonsByTypeCd(UserPoolManager.USER_POOL_TYPE_OAMEETING);
		userPoolsPersons = userPoolManager.getUserPoolPersonsByTypeCd(UserPoolManager.USER_POOL_TYPE_OAMEETINGGEREN);
		String userIds = "";
		String userIdsPerson = "";
		String userAllIds = "";
		String allUserIds = "";
		if (userPools.size() > 0) {
			UserPool userPool = userPools.get(0);
			userIds = userPool.getUserList();
		}
		if (userPoolsPersons.size() > 0) {
			UserPool userPoolsPerson = userPoolsPersons.get(0);
			userIdsPerson = userPoolsPerson.getUserList();
		}
		if ("".equals(userIdsPerson) || userIdsPerson == null) {
			userAllIds = userIds;
		} else {
			userAllIds = userIds.concat(userIdsPerson);
		}

		String[] userIdsAll = userAllIds.split(";");

		for (int i = 0; userIdsAll.length > i; i++) {
			String userId = userIdsAll[i];
			if (!allUserIds.contains(userId) && !"".equals(userId)) {
				allUserIds += userId + ";";
			}
		}

		if (StringUtils.isNotBlank(allUserIds)) {
			String result = userPoolManager.buildPersonInfoData(allUserIds);
			if (StringUtils.isNotBlank(result)) {
				Struts2Utils.renderText(result);
			}
		}

		return null;
	}

	/**
	 * 获取管理用户池
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchUserManagerPool() throws Exception {
		List<UserPool> userPools = new ArrayList<UserPool>();
		userPools = userPoolManager.getUserPoolPersonsByTypeCd(UserPoolManager.USER_POOL_TYPE_OAMEETING);
		if (userPools.size() > 0) {
			UserPool userPool = userPools.get(0);
			String userIds = userPool.getUserList();

			if (StringUtils.isNotBlank(userIds)) {
				String result = userPoolManager.buildPersonInfoData(userIds);
				if (StringUtils.isNotBlank(result)) {
					Struts2Utils.renderText(result);
				}
			}
		}

		return null;
	}

	/**
	 * 获取二级用户池用户池
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchUserPersonPool() throws Exception {
		List<UserPool> userPools = new ArrayList<UserPool>();
		userPools = userPoolManager.getUserPoolPersonsByTypeCd(UserPoolManager.USER_POOL_TYPE_OAMEETINGGEREN);
		if (userPools.size() > 0) {
			UserPool userPool = userPools.get(0);
			String userIds = userPool.getUserList();

			if (StringUtils.isNotBlank(userIds)) {
				String result = userPoolManager.buildPersonInfoData(userIds);
				if (StringUtils.isNotBlank(result)) {
					Struts2Utils.renderText(result);
				}
			}
		}

		return null;
	}

	/**
	 * 获取指定类型的人员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchPerson() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = oaMeetingManager.getEntity(getId());
			String userIds = "";

			if (this.getAttendeeType().equalsIgnoreCase(ATT_TYPE_EXIST_RES)) {
				userIds = entity.getResponsiblePerson();
			} else if (this.getAttendeeType().equalsIgnoreCase(ATT_TYPE_EXIST_COR)) {
				userIds = entity.getCoordinatePerson();
			} else if (this.getAttendeeType().equalsIgnoreCase(ATT_TYPE_EXIST_VIEW)) {
				userIds = entity.getVisibleToUsers();
			}

			if (StringUtils.isNotBlank(userIds)) {
				String result = userPoolManager.buildPersonInfoData(userIds);
				if (StringUtils.isNotBlank(result)) {
					Struts2Utils.renderText(result);
				}
			}
		}

		return null;
	}

	/**
	 * 获取指定类型的人员
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchPersonOn() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = oaMeetingManager.getEntity(getId());
			String userIds = "";
			String type_end = Struts2Utils.getParameter("PerSon");
			if (type_end.equals("res")) {
				type_end = "EXIST_RES";
			} else if (type_end.equals("cor")) {
				type_end = "EXIST_COR";
			} else {
				type_end = "EXIST_VIEW";
			}
			if (type_end.equalsIgnoreCase(ATT_TYPE_EXIST_RES)) {
				userIds = entity.getResponsiblePerson();
			} else if (type_end.equalsIgnoreCase(ATT_TYPE_EXIST_COR)) {
				userIds = entity.getCoordinatePerson();
			} else if (type_end.equalsIgnoreCase(ATT_TYPE_EXIST_VIEW)) {
				userIds = entity.getVisibleToUsers();
			}

			if (StringUtils.isNotBlank(userIds)) {
				String result = userPoolManager.buildPersonInfoData(userIds);
				if (StringUtils.isNotBlank(result)) {
					Struts2Utils.renderText(result);
				}
			}
		}

		return null;
	}

	/**
	 * 保存反馈信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveComment() throws Exception {
		prepareEntity();

		if (StringUtils.isBlank(this.getComment().toString()))
			throw new IllegalArgumentException("评论字段不能为空");

		oaMeetingCommentManager.saveMeetingComment(comment, this.getId());
		OaMeeting oaMeeting = new OaMeeting();
		oaMeeting = oaMeetingManager.getEntity(this.getId());
		oaMeetingManager.saveOaMeeting(oaMeeting);
		// 这里保存一次，更新recordVersion字段，关注模块用
		// 添加日志
		planOperationLogManager.addCEOTaskLog(getId(), entity.getWaterNumber(), PlanOperationLogManager.OPERATION_TYPE_ADD, "发表新留言："
				+ Util.clob2String(comment));

		Struts2Utils.renderText("done");
		return null;
	}

	/**
	 * 删除反馈信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteComment() throws Exception {
		prepareEntity();
		String oaMeetingCommentid = Struts2Utils.getParameter("comId");
		OaMeetingComment oaMeetingComment = new OaMeetingComment();
		String hql = "from OaMeetingComment where oaMeetingCommentId='" + oaMeetingCommentid + "'";
		List list = oaMeetingCommentManager.find(hql);
		if (list != null && list.size() > 0) {
			oaMeetingComment = (OaMeetingComment) list.get(0);
		}

		// 添加日志
		planOperationLogManager.addCEOTaskLog(getId(), entity.getWaterNumber(), PlanOperationLogManager.OPERATION_TYPE_DELETE, "删除留言："
				+ Util.clob2String(oaMeetingComment.getContent()));

		oaMeetingCommentManager.deleteBatch(oaMeetingCommentid);

		Struts2Utils.renderText("done");
		return null;
	}

	/**
	 * 获取留言信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchComments() throws Exception {
		prepareEntity();

		commentList = entity.getOaMeetingComments();

		// 对回复按时间降序排列
		oaMeetingManager.orderComments(commentList);

		return "comment";
	}

	/**
	 * 会议的附件管理页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String attachMents() throws Exception {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		attachPage = new Page<AppAttachFile>(20);

		filters.add(new PropertyFilter("EQS_bizEntityId", bizEntityId));
		filters.add(new PropertyFilter("EQS_statusCd", "1"));
		attachPage.setOrderBy("createdDate");
		attachPage.setOrder(Page.DESC);

		attachPage = appAttachFileManager.findPage(attachPage, filters);

		if ("1".equals(isAttachUpdated)) {
			oaMeetingManager.refreshUpdatedDate(bizEntityId);
			if (attachPage.getResult().size() > 0) {
				oaMeetingManager.updateFileFlg(bizEntityId, "1");
			} else {
				oaMeetingManager.updateFileFlg(bizEntityId, "0");
			}
		}

		return "attach";
	}

	/**
	 * 展现用户列表(用户池树)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String member() throws Exception {
		String personalOrMan = Struts2Utils.getParameter("personal");
		String currentOrgCd = SpringSecurityUtils.getCurrentDeptCd();
		if (StringUtils.isNotBlank(currentOrgCd)) {
			setOrgBizCd(currentOrgCd);
			setAttendeeList(PlasCache.getDirectUserListByCd(currentOrgCd));
			setPersonal(personalOrMan);
		}
		return "member";
	}

	/**
	 * 根据过滤条件获取用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getUsersByFilter() throws Exception {
		String value = Struts2Utils.getParameter("value");
		String maxNum = Struts2Utils.getParameter("maxNum");
		if (StringUtils.isNotEmpty(value)) {
			WsPlasUser wsUaapUser = new WsPlasUser();
			wsUaapUser.setUiid(value);
			wsUaapUser.setUserName(value);
			List<WsPlasUser> result = Util.getPlasService().getUserListByFilter(wsUaapUser);
			List<UserDisplayInfo> list = transf2UserInfo(result);
			if (StringUtils.isNotEmpty(maxNum)) {
				int num = Integer.valueOf(maxNum);
				List<UserDisplayInfo> newList;
				if (list.size() > num) {
					newList = list.subList(0, num);
				} else {
					newList = list.subList(0, list.size());
				}
				Struts2Utils.renderJson(newList);
			} else {
				Struts2Utils.renderJson(list);
			}
		}
		return null;
	}

	/**
	 * 获取机构树
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getOrgTree() throws Exception {
		TreeNode node = new TreeNode();
		node.setId(appParamManager.getAppOrgTreeRootCd());
		node.setText(appParamManager.getAppOrgTreeRootName());
		node.setOrgOrUser("1");
		node.setChildren(this.getChildrenNode(node));
		Struts2Utils.renderJson(node);
		return null;
	}

	private List<TreeNode> getChildrenNode(TreeNode treeNode) {
		List<TreeNode> children = new ArrayList<TreeNode>();
		String parentId = null;
		for (WsPlasOrg org : PlasCache.getOrgEnableList()) {
			parentId = PlasCache.getPhysicalParentId(org.getPlasOrgId());
			if (StringUtils.isNotBlank(parentId) && parentId.equals(treeNode.getId())) {
				TreeNode childOrg = new TreeNode();
				childOrg.setId(org.getOrgCd());
				childOrg.setText(org.getOrgName());
				childOrg.setChecked("0");
				childOrg.setOrgOrUser("1");
				childOrg.setExtParam(org.getOrgBizCd());
				childOrg.setChildren(getChildrenNode(childOrg));
				children.add(childOrg);
			}
		}

		return children;
	}

	/**
	 * 如果指令单被更改或新建时，将指令单的信息更新至JBPM_TASK表中
	 */
	private void updateToJbpmTask() {
		if (entity == null)
			throw new RuntimeException("指令单不能为空");

		JbpmTask t = fetchJbpmTask();
		if (t == null) {
			t = new JbpmTask();
			t.setModuleCd(DictContants.JBPM_TASK_MODULE_CD_CEOMEETING);
			t.setModuleName("<span class='color_red'>指令</span>");
			t.setJbpmCd(entity.getWaterNumber());
			t.setJbpmId(entity.getOaMeetingId());
		}
		t.setApplyDate(entity.getTargetDate());
		jbpmTaskManager.saveJbpmTask(t);

		t = fetchJbpmTask();
		if (t == null)
			throw new RuntimeException("");
		StringBuffer sbUserCds = new StringBuffer(";"+entity.getResponsiblePerson());
		if (StringUtils.isNotEmpty(entity.getCoordinatePerson())) {
			sbUserCds.append(";").append(entity.getCoordinatePerson());
		}
		if (StringUtils.isNotEmpty(entity.getVisibleToUsers())) {
			sbUserCds.append(";").append(entity.getVisibleToUsers());
		}
		jbpmTaskCandidateManager.saveResPerson(t, sbUserCds.toString());
	}

	/**
	 * 如果该记录被删除/隐藏或者状态被设置为非进行中状态，则将JBPM_TASK表中相应的记录也删除
	 */
	private void deleteJbpmTask() {
		if (entity == null)
			throw new RuntimeException("指令单不能为空");

		JbpmTask t = fetchJbpmTask();

		if (t != null) {
			jbpmTaskCandidateManager.deleteCandOfTask(t);
			jbpmTaskManager.delete(t);
		}
	}

	/**
	 * 获取跟当前专项任务相关的Jbpm_task记录
	 * 
	 * @return
	 */
	private JbpmTask fetchJbpmTask() {

		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_moduleCd", DictContants.JBPM_TASK_MODULE_CD_CEOMEETING));
		filters.add(new PropertyFilter("EQS_jbpmId", entity.getOaMeetingId()));
		List<JbpmTask> tasks = jbpmTaskManager.find(filters);
		if (tasks.size() > 1)
			throw new RuntimeException("该条指令单不能在jbpm_task中有两条记录!");

		if (tasks.size() == 0)
			return null;

		return tasks.get(0);
	}

	/**
	 * 获取指定机构下的用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getUsersbyOrg() throws Exception {
		String orgCds = Struts2Utils.getParameter("orgCds");
		if (StringUtils.isNotEmpty(orgCds)) {
			List<UserDisplayInfo> userInfos = new ArrayList<UserDisplayInfo>();
			for (String orgCd : orgCds.split(",")) {
				List<WsPlasUser> users = PlasCache.getDirectUserListByCd(orgCd);
				if (users != null && users.size() > 0) {
					userInfos.addAll(transf2UserInfo(users));
				}
			}
			Struts2Utils.renderJson(userInfos);
		}
		return null;
	}

	/**
	 * 获取指定指令单的更新时间
	 * 
	 * @return
	 * @throws Exception
	 */
	public String fetchUpdatedDate() throws Exception {
		prepareEntity();

		Date updatedDate = entity.getUpdatedDate();
		String fileFlg = entity.getFileFlg();
		String updatedDateStr = DateOperator.formatDate(updatedDate, "MM-dd");
		Struts2Utils.renderText(updatedDateStr);
		// if(null!=fileFlg){
		// Struts2Utils.renderText(fileFlg);
		// }else{
		// Struts2Utils.renderText("0");
		// }

		return null;
	}

	/**
	 * 返回参与会议的人员列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String attendee() throws Exception {
		attendeeList = new ArrayList<WsPlasUser>();
		return "attendee";
	}

	/**
	 * 提醒功能
	 * 
	 * @return
	 * @throws Exception
	 */
	public String remind() throws Exception {
		prepareEntity();

		if (oaMeetingManager.sendRemindEmail(entity)) {
			planOperationLogManager.addCEOTaskLog(getId(), entity.getWaterNumber(), PlanOperationLogManager.OPERATION_TYPE_EMAIL, "发送了一封提醒邮件");
			Struts2Utils.renderText("done");
		}

		return null;
	}

	/**
	 * 构造流水编号 流水号规则：前缀指定，后面数字递增
	 * 
	 * @return
	 */
	public String computeWaterNum() {
		int count = oaMeetingManager.getMaxNo(strModule);
		String water_prefix = "";
		if ("zc".equalsIgnoreCase(strModule)) {
			water_prefix = "令-2012-地-";
		} else if ("bis".equalsIgnoreCase(strModule)) {
			water_prefix = "令-2012-商-";
		}

		Struts2Utils.renderText(water_prefix + (count));

		return null;
	}

	private List<UserDisplayInfo> transf2UserInfo(List<WsPlasUser> users) throws Exception {
		List<UserDisplayInfo> list = new ArrayList<UserDisplayInfo>();
		UserDisplayInfo info = null;
		for (WsPlasUser user : users) {
			info = new UserDisplayInfo();
			info.setCenterOrgCd(PlasCache.getCenterOrgCdByUserId(user.getPlasUserId()));
			info.setCenterOrgName(PlasCache.getCenterOrgNameByUserId(user.getPlasUserId()));
			info.setOrgBizCd(user.getOrgBizCd());
			info.setOrgCd(user.getOrgCd());
			info.setOrgName(user.getOrgName());
			info.setSexCd(user.getSexCd());
			info.setUiid(user.getUiid());
			info.setUserBizCd(user.getUserBizCd());
			info.setUserCd(user.getUserCd());
			info.setUserName(user.getUserName());
			list.add(info);
		}
		return list;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isBlank(getId())) {
			entity = new OaMeeting();
		} else {
			entity = oaMeetingManager.getEntity(getId());
		}
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	/**
	 * 将会议状态转为汉字表示
	 * 
	 * @return
	 */
	public Map<String, String> getMapStatus() {
		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put(DictContants.OA_MEETING_STATUS_NEW, "进行中");
		statusMap.put(DictContants.OA_MEETING_STATUS_PRECOMPLETE, "预完成");
		statusMap.put(DictContants.OA_MEETING_STATUS_COMPLETE, "已完成");
		return statusMap;
	}

	/**
	 * 指令跟踪单--excel导出接口
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String exportExecOMeeting() throws Exception {
		List<OaMeeting> results = new ArrayList<OaMeeting>();
		System.out.println(changeFlag);
		if (canViewOaMeetings()) {
			if (!changeFlag && (changeStats.equals("0") || Constants.filters == null)) {
				results = brancheTwo(results);
			} else {
				results = brancheOne(results);
			}
		}
		Map beans = new HashMap();
		beans.put("oaMeeting", results);
		beans.put("nowDate", DateOperator.formatDate(new Date(), "yyyy-MM-dd"));
		is = JXLExcelUtil.initJxlsInputStream(beans, "oa-meeting.xls");
		String fileName = "指令单跟踪明细表-" + DateOperator.formatDate(new Date(), "MMddHHmm");
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
	}

	/**
	 * 
	 * @param results
	 * @return
	 */
	private List<OaMeeting> brancheOne(List<OaMeeting> results) {
		List<PropertyFilter> filters = Constants.filters;
		// PropertyFilter filter = new PropertyFilter("EQS_isDeleted", "0");
		// if (StringUtils.isBlank(filter_EQS_hiddenFlg)) {
		// filters.add(new PropertyFilter("EQS_hiddenFlg", "0"));
		// }
		// if(oaMeetingManager.getPersonalUserId(uiid)&&
		// oaMeetingManager.getPersonalUserIdAdmin(uiid)!=true){
		// filters.add(new
		// PropertyFilter("LIKES_creator_OR_responsiblePerson_OR_coordinatePerson_OR_visibleToUsers",
		// uiid));
		// oaMeetingGenren=true;
		// }else if(oaMeetingManager.getPersonalUserId(uiid)!=true &&
		// oaMeetingAdmin!=true &&
		// oaMeetingManager.getPersonalUserIdAdmin(uiid)!=true){
		// filters.add(new
		// PropertyFilter("LIKES_responsiblePerson_OR_coordinatePerson_OR_visibleToUsers",
		// uiid));
		// }
		// filters.add(filter);
		// if(oaMeetingManager.getPersonalUserIdAdmin(uiid)==true &&
		// oaMeetingManager.getPersonalUserId(uiid)==true){
		// oaMeetingGenren=true;
		// }
		// if(null!=myTask && "true".equalsIgnoreCase(myTask)){
		// filters.add(new
		// PropertyFilter("LIKES_responsiblePerson_OR_coordinatePerson", uiid));
		// }
		return oaMeetingExcel(oaMeetingManager.find(filters));
	}

	/**
	 * 
	 * @param results
	 * @return
	 */
	private List<OaMeeting> brancheTwo(List<OaMeeting> results) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_isDeleted", "0"));
		filters.add(new PropertyFilter("EQS_hiddenFlg", "0"));
		if (StringUtils.isNotBlank(getId())) {
			filters.add(new PropertyFilter("EQS_oaMeetingId", getId()));
		} else {
			if (oaMeetingManager.getPersonalUserId(uiid) && oaMeetingManager.getPersonalUserIdAdmin(uiid) != true) {
				filters.add(new PropertyFilter("LIKES_creator_OR_responsiblePerson_OR_coordinatePerson_OR_visibleToUsers", uiid));
				oaMeetingGenren = true;
			} else if (oaMeetingManager.getPersonalUserId(uiid) != true && oaMeetingAdmin != true && oaMeetingManager.getPersonalUserIdAdmin(uiid) != true) {
				filters.add(new PropertyFilter("LIKES_responsiblePerson_OR_coordinatePerson_OR_visibleToUsers", uiid));
			}
		}
		if (oaMeetingManager.getPersonalUserIdAdmin(uiid) == true && oaMeetingManager.getPersonalUserId(uiid) == true) {
			oaMeetingGenren = true;
		}
		if (null != myTask && "true".equalsIgnoreCase(myTask)) {
			filters.add(new PropertyFilter("LIKES_responsiblePerson_OR_coordinatePerson", uiid));
		}
		Constants.filters = null;
		return oaMeetingExcel(oaMeetingManager.find(filters));
	}

	/**
	 * 对需要导出的元素进行导出excel之前的操作
	 * 
	 * @param list
	 * @return
	 */
	private List<OaMeeting> oaMeetingExcel(List<OaMeeting> list) {
		// 对list中对象进行按创建时间排序 start
		Map<Date, OaMeeting> map = new HashMap<Date, OaMeeting>();
		if (list != null && !list.isEmpty()) {
			for (OaMeeting m : list) {
				map.put(m.getCreatedDate(), m);
			}
		}
		ArrayList<Entry<Date, OaMeeting>> ll = new ArrayList<Entry<Date, OaMeeting>>(map.entrySet());

		Collections.sort(ll, new Comparator<Map.Entry<Date, OaMeeting>>() {
			public int compare(Map.Entry<Date, OaMeeting> o1, Map.Entry<Date, OaMeeting> o2) {
				@SuppressWarnings("hiding")
				int flag = 0;
				if (o2.getKey().getTime() > o1.getKey().getTime()) {
					flag = 1;
				}
				return flag;
			}
		});
		List<OaMeeting> newList = new ArrayList<OaMeeting>();
		for (Entry<Date, OaMeeting> e : ll) {
			newList.add(e.getValue());
		}
		// 对list中对象进行按创建时间排序 end
		List<OaMeeting> results = new ArrayList<OaMeeting>();
		for (OaMeeting m : newList) {
			OaMeeting newOaMeeting = new OaMeeting();
			newOaMeeting.setResponsiblePerson(CodeNameUtil.getUserNamesByUiids(m.getResponsiblePerson(), ";"));// 负责人
			newOaMeeting.setCoordinatePerson(CodeNameUtil.getUserNamesByUiids(m.getCoordinatePerson(), ";"));// 配合人
			newOaMeeting.setWaterNumber(m.getWaterNumber());// 编号
			newOaMeeting.setPriority(m.getTargetDate() == null ? " " : DateOperator.formatDate(m.getTargetDate(), "yyyy-MM-dd").toString());// 目标时间
			newOaMeeting.setCreatedDeptCd(m.getCreatedDate() == null ? " " : DateOperator.formatDate(m.getCreatedDate(), "yyyy-MM-dd").toString());
			if (m.getStatus() != null && !m.getStatus().isEmpty()) {
				if (m.getStatus().equals("1")) {
					newOaMeeting.setUpdatedDeptCd("进行中");
				} else if (m.getStatus().equals("2")) {
					newOaMeeting.setUpdatedDeptCd("预完成");
				} else if (m.getStatus().equals("3")) {
					newOaMeeting.setUpdatedDeptCd("已完成");
				}
			}

			// 最新的3条评论
			StringBuffer tip = new StringBuffer();
			Clob b = m.getBusiness();
			newOaMeeting.setRemark(Util.clob2String2(b));// 事项
			List<OaMeetingComment> comments = m.getOaMeetingComments();
			DateFormat df = new SimpleDateFormat("MM-dd");
			if (comments.size() > 0) {
				int size = comments.size() >= 3 ? 3 : comments.size();
				for (int i = 0; i < size; i++) {
					OaMeetingComment c = comments.get(i);
					String userName = CodeNameUtil.getUserNameByCd(c.getCreator());
					tip.append(userName + " " + df.format(c.getCreatedDate()) + "：" + Util.clob2String2(c.getContent()) + " \r\n");
				}
				newOaMeeting.setVisibleToUsers(tip.toString());
			}
			results.add(newOaMeeting);
		}
		return results;
	}

	public OaMeeting getModel() {
		return entity;
	}

	public String getEntityTempId() {
		return entityTempId;
	}

	public void setEntityTempId(String entityTempId) {
		this.entityTempId = entityTempId;
	}

	public List<WsPlasOrg> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<WsPlasOrg> orgList) {
		this.orgList = orgList;
	}

	public List<WsPlasUser> getAttendeeList() {
		return attendeeList;
	}

	public void setAttendeeList(List<WsPlasUser> attendeeList) {
		this.attendeeList = attendeeList;
	}

	public String getOrgBizCd() {
		return orgBizCd;
	}

	public void setOrgBizCd(String orgBizCd) {
		this.orgBizCd = orgBizCd;
	}

	public String getAttendeeType() {
		return attendeeType;
	}

	public void setAttendeeType(String attendeeType) {
		this.attendeeType = attendeeType;
	}

	@Override
	public Page<OaMeeting> getPage() {
		return page;
	}

	public Clob getComment() {
		return comment;
	}

	public void setComment(Clob comment) {
		this.comment = comment;
	}

	public String getAttendeeIds() {
		return attendeeIds;
	}

	public void setAttendeeIds(String attendeeIds) {
		this.attendeeIds = attendeeIds;
	}

	public Map<String, String> getMapResPersonNames() {
		return mapResPersonNames;
	}

	public void setMapResPersonNames(Map<String, String> mapResPersonNames) {
		this.mapResPersonNames = mapResPersonNames;
	}

	public Map<String, String> getMapCorPersonNames() {
		return mapCorPersonNames;
	}

	public void setMapCorPersonNames(Map<String, String> mapCorPersonNames) {
		this.mapCorPersonNames = mapCorPersonNames;
	}

	public List<OaMeetingComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<OaMeetingComment> commentList) {
		this.commentList = commentList;
	}

	public String getBizEntityId() {
		return bizEntityId;
	}

	public void setBizEntityId(String bizEntityId) {
		this.bizEntityId = bizEntityId;
	}

	public Page<AppAttachFile> getAttachPage() {
		return attachPage;
	}

	public void setAttachPage(Page<AppAttachFile> attachPage) {
		this.attachPage = attachPage;
	}

	public String getFilter_EQS_hiddenFlg() {
		return filter_EQS_hiddenFlg;
	}

	public void setFilter_EQS_hiddenFlg(String filterEQSHiddenFlg) {
		filter_EQS_hiddenFlg = filterEQSHiddenFlg;
	}

	public String getFilter_EQS_status() {
		return filter_EQS_status;
	}

	public void setFilter_EQS_status(String filterEQSStatus) {
		filter_EQS_status = filterEQSStatus;
	}

	public String getFilter_LIKES_waterNumber() {
		return filter_LIKES_waterNumber;
	}

	public void setFilter_LIKES_waterNumber(String filterLIKESWaterNumber) {
		filter_LIKES_waterNumber = filterLIKESWaterNumber;
	}

	public String getFilter_GED_createdDate() {
		return filter_GED_createdDate;
	}

	public void setFilter_GED_createdDate(String filterGEDCreatedDate) {
		filter_GED_createdDate = filterGEDCreatedDate;
	}

	public String getFilter_LED_createdDate() {
		return filter_LED_createdDate;
	}

	public void setFilter_LED_createdDate(String filterLEDCreatedDate) {
		filter_LED_createdDate = filterLEDCreatedDate;
	}

	public String getFilter_GED_targetDate() {
		return filter_GED_targetDate;
	}

	public void setFilter_GED_targetDate(String filterGEDTargetDate) {
		filter_GED_targetDate = filterGEDTargetDate;
	}

	public String getFilter_LED_targetDate() {
		return filter_LED_targetDate;
	}

	public void setFilter_LED_targetDate(String filterLEDTargetDate) {
		filter_LED_targetDate = filterLEDTargetDate;
	}

	public boolean isOaMeetingAdmin() {
		return oaMeetingAdmin;
	}

	public String getSearchMode() {
		return searchMode;
	}

	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getIsAttachUpdated() {
		return isAttachUpdated;
	}

	public void setIsAttachUpdated(String isAttachUpdated) {
		this.isAttachUpdated = isAttachUpdated;
	}

	public String getModuleCd() {
		return moduleCd;
	}

	public void setModuleCd(String moduleCd) {
		this.moduleCd = moduleCd;
	}

	public Map<String, String> getMapMeetingTips() {
		return mapMeetingTips;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isOaMeetingGenren() {
		return oaMeetingGenren;
	}

	public void setOaMeetingGenren(boolean oaMeetingGenren) {
		this.oaMeetingGenren = oaMeetingGenren;
	}

	public String getPersonal() {
		return personal;
	}

	public void setPersonal(String personal) {
		this.personal = personal;
	}

	public String getOpened_entityid() {
		return opened_entityid;
	}

	public void setOpened_entityid(String opened_entityid) {
		this.opened_entityid = opened_entityid;
	}

	public String getMyTask() {
		return myTask;
	}

	public void setMyTask(String myTask) {
		this.myTask = myTask;
	}

	public String getStrModule() {
		return strModule;
	}

	public void setStrModule(String strModule) {
		this.strModule = strModule;
	}

}
