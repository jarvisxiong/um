package com.hhz.ump.web.oa;

import java.io.InputStream;
import java.sql.Clob;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppParamManager;
import com.hhz.ump.dao.jbpm.JbpmTaskCandidateManager;
import com.hhz.ump.dao.jbpm.JbpmTaskManager;
import com.hhz.ump.dao.oa.SpecialTaskCommentManager;
import com.hhz.ump.dao.oa.SpecialTaskManager;
import com.hhz.ump.dao.plan.PlanOperationLogManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.oa.SpecialTask;
import com.hhz.ump.entity.oa.SpecialTaskComment;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.IExcelExporter;
import com.hhz.ump.util.TreeNode;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.vo.UserDisplayInfo;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasUser;

/**
 * 
 * 类名		SpecialTaskAction
 * 创建者	lijin
 * 创建日期	2010-4-27
 * 描述		专项任务Action类
 */
@Namespace("/oa")
@Results( {
    		@Result(name = CrudActionSupport.RELOAD, location = "special-task.action", type = "redirect"),
    		@Result(name = "Exported", location = "special-task!exportExcel.action", type = "stream", 
    			params = { "contentType", "application/vnd.ms-excel", 
    				   "contentDisposition", "attachment;filename=${excelFileName}.xls",
    				   "inputName", "excelFile"})
    	})
public class SpecialTaskAction extends CrudActionSupport<SpecialTask> {

    // 三种人员类型：发出人，负责人，配合人
    private static final String PERSON_TYPE_EXIST_SEND = "EXIST_SEND";
    private static final String PERSON_TYPE_EXIST_RES = "EXIST_RES";
    private static final String PERSON_TYPE_EXIST_COR = "EXIST_COR";
    private static final String PERSON_TYPE_RES = "RES";
    private static final String PERSON_TYPE_COR = "COR";
    
    // 超级管理员角色名称
    private static final String ROLE_SUPADMIN = "A_SPECTASK_SUPADMIN";
    // 总裁管理员角色名称
    private static final String ROLE_CEOADMIN = "A_SPECTASK_CEOADMIN";
    // 管理层，各副总裁管理员角色名称
    private static final String ROLE_VICADMIN = "A_SPECTASK_VICADMIN";
    // 浏览管理员，可以查看所有的任务记录
    private static final String ROLE_VIEWADMIN = "A_SPECTASK_VIEWADMIN";
    
    @Autowired
    private SpecialTaskManager specialTaskManager;
    @Autowired
    private SpecialTaskCommentManager specialTaskCommentManager;
    @Autowired
    private AppParamManager appParamManager;
    @Autowired
    private AppAttachFileManager appAttachFileManager;
    @Autowired
    private JbpmTaskManager jbpmTaskManager;
    @Autowired
    private JbpmTaskCandidateManager jbpmTaskCandidateManager;
    @Autowired
    private PlanOperationLogManager planOperationLogManager;
    
    private SpecialTask entity;
    
    private static final long serialVersionUID = 1L;
    
    private Page<SpecialTask> page = new Page<SpecialTask>(100);
    private List<WsPlasUser> personList;
    private List<AppAttachFile> attachList;
    private List<SpecialTaskComment> commentList;
    
    private String entityTempId;
    private String bizEntityId;
    private String personType;
    private String orgBizCd;
    private String orderBy;
    private String order;
    private String currentUiid = SpringSecurityUtils.getCurrentUiid();
    private boolean canDeleteAttach;
    private String isAttachUpdated;
    private Clob comment;
    private InputStream excelFile;
    private String excelFileName;
    private String type;
    
    // 搜索过滤条件
    private String searchBySendPerson;
    private String searchByResPerson;
    private String searchBySubject;
    private String searchByAttachName;
    private String searchByStatus;
    private String searchByHiddenFlg;
    
    private Map<String, String> mapSendPersonNames = new HashMap<String, String>();
    private Map<String, String> mapResPersonNames = new HashMap<String, String>();
    private Map<String, String> mapCorPersonNames = new HashMap<String, String>();

    @Override
    public String list() throws Exception {
	page.setOrderBy("waterNum,createdDate");
	page.setOrder(Page.ASC + "," + Page.DESC);
	
	List<Criterion> criterionList = new ArrayList<Criterion>();
	criterionList.add(Restrictions.eq("deletedFlg", "0"));
	criterionList.add(Restrictions.eq("hiddenFlg", "0"));
	if (StringUtils.isNotBlank(getId())) {
	    criterionList.add(Restrictions.eq("specialTaskId", getId()));
	}
	
	// 如果是超级管理员和浏览管理员角色，可以查看全部记录，否则默认只显示跟自己有关的任务记录
	if (!SpringSecurityUtils.hasRole(ROLE_SUPADMIN) && !SpringSecurityUtils.hasRole(ROLE_VIEWADMIN)) {
	    String cond = "%" + SpringSecurityUtils.getCurrentUiid() + "%";
	    Object[] params = {cond, cond, cond};
	    Type[] types = {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING};
	    criterionList.add(Restrictions.sqlRestriction("(SEND_PERSON like ? or RESPONSIBLE_PERSON like ? or COORDINATE_PERSON like ?)", params, types));
	}
	
	page = specialTaskManager.findPage(page, criterionList.toArray(new Criterion[criterionList.size()]));
	
	entityTempId = RandomUtils.generateTmpEntityId();
	
	buildTaskAttrs(page.getResult());
	
	return SUCCESS;
    }
    
    /**
     * 根据前台过滤条件搜索任务记录
     * @return
     * @throws Exception
     */
    public String search() throws Exception {
	
	// 如果前台传过来的搜索条件包含有排序的条件，则按传入的排序条件进行排序。
	if (StringUtils.isNotBlank(orderBy)) {
	    page.setOrderBy(orderBy + ",createdDate");
	    page.setOrder(order + "," + Page.DESC);
	} else {
	    page.setOrderBy("waterNum,createdDate");
	    page.setOrder(Page.ASC + "," + Page.DESC);
	}
	
	page = specialTaskManager.findPage(page, buildCriterion());
	
	buildTaskAttrs(page.getResult());
	entityTempId = RandomUtils.generateTmpEntityId();
	
	return "taskList";
    }
    
    /**
     * 构造过滤条件
     * @return
     * @throws ParseException
     */
    private Criterion[] buildCriterion() throws ParseException {
	List<Criterion> criterionList = new ArrayList<Criterion>();
	criterionList.add(Restrictions.eq("deletedFlg", "0"));
	
	// 只有超级管理员和总裁管理员才有权限查看隐藏的记录
	if (SpringSecurityUtils.hasRole(ROLE_SUPADMIN) || SpringSecurityUtils.hasRole(ROLE_CEOADMIN)) {
	    if (StringUtils.isNotBlank(searchByHiddenFlg)) {
		criterionList.add(Restrictions.eq("hiddenFlg", searchByHiddenFlg));
	    }
	} else {
	    criterionList.add(Restrictions.eq("hiddenFlg", "0"));
	}
	
	// 如果是超级管理员，总裁管理员和浏览管理员角色，可以搜素到全部记录，否则默认只显示跟自己有关的任务记录
	if (!SpringSecurityUtils.hasRole(ROLE_SUPADMIN) 
		&& !SpringSecurityUtils.hasRole(ROLE_CEOADMIN) 
		&& !SpringSecurityUtils.hasRole(ROLE_VIEWADMIN)) {
	    String cond = "%" + SpringSecurityUtils.getCurrentUiid() + "%";
	    Object[] params = {cond, cond, cond};
	    Type[] types = {Hibernate.STRING, Hibernate.STRING, Hibernate.STRING};
	    criterionList.add(Restrictions.sqlRestriction("(SEND_PERSON like ? or RESPONSIBLE_PERSON like ? or COORDINATE_PERSON like ?)", params, types));
	}
	
	if (StringUtils.isNotBlank(searchBySendPerson)) {
	    Criterion e = buildCorPersonSearchCriterion();
	    if (e != null) {
		criterionList.add(e);
	    }
	}
	
	if (StringUtils.isNotBlank(searchByStatus)) {
	    criterionList.add(Restrictions.eq("status", searchByStatus));
	}
	
	if (StringUtils.isNotBlank(searchByResPerson)) {
	    Criterion e = buildResPersonSearchCriterion();
	    if (e != null) {
		criterionList.add(e);
	    }
	}
	
	if (StringUtils.isNotBlank(searchBySubject)) {
	    criterionList.add(Restrictions.like("subject", "%" + searchBySubject + "%"));
	}
	
	if (StringUtils.isNotBlank(searchByAttachName)) {
	    StringBuilder sql = new StringBuilder();
	    sql.append("SPECIAL_TASK_ID IN (SELECT BIZ_ENTITY_ID FROM APP_ATTACH_FILE WHERE BIZ_MODULE_CD = 'specialTask' AND REAL_FILE_NAME LIKE LOWER(?))");
	    criterionList.add(Restrictions.sqlRestriction("(" + sql.toString() + ")", "%" + searchByAttachName + "%", Hibernate.STRING));
	}
	
	return criterionList.toArray(new Criterion[criterionList.size()]);
    }
    
    /**
     * 构造配合人过滤条件
     * @return
     */
    private Criterion buildCorPersonSearchCriterion() {
	StringBuilder sql = new StringBuilder();
	String[] ids = searchBySendPerson.split(";");
	
	String id = null;
	int length = ids.length;
	if (length > 10) {
	    length = 10;
	}
	Object[] params = new Object[length - 1];
	Type[] types = new Type[length - 1];
	for (int i = 1; i < length; i ++) {
	    id = ids[i];		
	    sql.append("COORDINATE_PERSON like ?");
	    params[i - 1] = "%" + id +"%";
	    types[i - 1] = Hibernate.STRING;
	    
	    if (i < length -1) {
		sql.append(" or ");
	    }
	}
	
	if (StringUtils.isNotBlank(sql.toString()))
	    return Restrictions.sqlRestriction("(" + sql.toString() + ")", params, types);
	
	return null;
    }
    
    /**
     * 构造负责人过滤条件
     * @return
     */
    private Criterion buildResPersonSearchCriterion() {
	StringBuilder sql = new StringBuilder();
	String[] ids = searchByResPerson.split(";");
	
	String id = null;
	int length = ids.length;
	if (length > 10) {
	    length = 10;
	}
	Object[] params = new Object[length - 1];
	Type[] types = new Type[length - 1];
	for (int i = 1; i < length; i ++) {
	    id = ids[i];		
	    sql.append("RESPONSIBLE_PERSON like ?");
	    params[i - 1] = "%" + id +"%";
	    types[i - 1] = Hibernate.STRING;
	    
	    if (i < length -1) {
		sql.append(" or ");
	    }
	}
	
	if (StringUtils.isNotBlank(sql.toString()))
	    return Restrictions.sqlRestriction("(" + sql.toString() + ")", params, types);
	
	return null;
    }
    
    /**
     * 打开选择人员页面
     * @return
     * @throws Exception
     */
    public String selPerson() throws Exception {
	String orgCd = SpringSecurityUtils.getCurrentDeptCd();
	if (StringUtils.isNotBlank(orgCd)) {
	    setOrgBizCd(orgCd);
	    setPersonList(PlasCache.getDirectUserListByCd(orgCd));
	}
	return "person";
    }
    
    /**
     * 获取指定任务的发送人/负责人/配合人
     * @return
     * @throws Exception
     */
    public String fetchPerson() throws Exception {
	prepareEntity();
	
	String userIds = "";
	String tempId = null;
	String tempName = null;
	
	if (PERSON_TYPE_EXIST_SEND.equalsIgnoreCase(personType)) {
	    userIds = entity.getSendPerson();
	} else if (PERSON_TYPE_EXIST_RES.equalsIgnoreCase(personType)) {
	    userIds = entity.getResponsiblePerson();
	} else if (PERSON_TYPE_EXIST_COR.equalsIgnoreCase(personType)) {
	    userIds = entity.getCoordinatePerson();
	}
	
	if (StringUtils.isNotBlank(userIds)) {
	    String[] ids = userIds.split(";");
	    StringBuilder result = new StringBuilder();
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
	    String t = result.toString();
	    if (StringUtils.isNotBlank(t)) {
		Struts2Utils.renderText(t);
	    }
	}
	
	return null;
    }
    
    /**
     * 获取机构树
     * @return
     * @throws Exception
     */
    public String getOrgTree() throws Exception {
	if (SpringSecurityUtils.hasRole(ROLE_VICADMIN) && 
		(PERSON_TYPE_RES.equals(personType) 
			|| PERSON_TYPE_COR.equals(personType)
			|| PERSON_TYPE_EXIST_SEND.equals(personType)
			|| PERSON_TYPE_EXIST_RES.equals(personType) 
			|| PERSON_TYPE_EXIST_COR.equals(personType))) {
	    Struts2Utils.renderJson(constructOrgTree(false));
	} else {
	    Struts2Utils.renderJson(constructOrgTree(true));
	}
	return null;
    }
    
    /**
     * 构造机构树，如果是副总裁管理员，在选择负责人和配合人时只能选择自己中心下的人
     * @param allOrg  是否需要列出所有的人员
     * @return
     */
    private TreeNode constructOrgTree(boolean allOrg) {
	TreeNode root = new TreeNode();
	root.setId(appParamManager.getAppOrgTreeRootCd());
	root.setOrgOrUser("1");
	List<TreeNode> allChildren = getChildrenNode(root);
	if (allOrg) {
	    root.setText(appParamManager.getAppOrgTreeRootName());
	    root.setChildren(allChildren);
	} else {
	    root.setText("负责的中心");
	    List<TreeNode> result = new ArrayList<TreeNode>();
	    filterChildren(result, allChildren, SpringSecurityUtils.getCurrentUiid());
	    root.setChildren(result);
	}
	
	return root;
    }

    /**
     * 构造所有的子节点
     * @param treeNode
     * @return
     */
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
			childOrg.setOrgMgrCd(org.getOrgMgrId());
			childOrg.setChildren(getChildrenNode(childOrg));
			children.add(childOrg);
	    }
	}
	
	return children;
    }
    
    /**
     * 构造当前人员所负责的中心机构树
     * @param allChildren
     * @return
     */
    private void filterChildren(List<TreeNode> result, List<TreeNode> allChildren, final String curUiid) {
	for (TreeNode t : allChildren) {
	    if (curUiid.equals(t.getOrgMgrCd())) {
		result.add(t);
		continue;
	    } else {
		filterChildren(result, t.getChildren(), curUiid);
	    }
	}
    }
    
    /**
     * 获取指定机构下的用户列表
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
     * 根据过滤条件搜索用户
     * @return
     * @throws Exception
     */
    public String getUsersByFilter() throws Exception {
	String value = Struts2Utils.getParameter("value");
	String maxNum = Struts2Utils.getParameter("maxNum");
	if (StringUtils.isNotEmpty(value)) {
	    WsPlasUser user = new WsPlasUser();
	    user.setUiid(value);
	    user.setUserName(value);
	    List<WsPlasUser> result = Util.getPlasService().getUserListByFilter(user);
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
    
    @Override
    public String input() throws Exception {
	entity.setDeletedFlg("0");
	entity.setHiddenFlg("0");
	entity.setAttentionFlg("0");
	entity.setStatus(DictContants.SPECIAL_TASK_STATUS_NEW);
	specialTaskManager.saveSpecialTask(entity);
	
	// 保存评论
	if (comment.length() > 0) {
	    SpecialTaskComment taskComment = new SpecialTaskComment();
	    taskComment.setSpecialTask(entity);
	    taskComment.setContent(getComment());
	    specialTaskCommentManager.saveSpecialTaskComment(taskComment);
	}

	// 保存附件
	appAttachFileManager.updateTmpFile(entityTempId, entity.getSpecialTaskId());
	
	updateToJbpmTask();
	
	//添加日志
	StringBuffer sbLog = new StringBuffer();
	sbLog.append("添加新的任务,编号:");
	sbLog.append(entity.getWaterNum());
	sbLog.append(",标题：");
	sbLog.append(entity.getSubject());
	sbLog.append(",详细内容：");
	sbLog.append(Util.clob2String(entity.getDetail()));
	sbLog.append(",负责人：");
	sbLog.append(CodeNameUtil.getUserNamesByUiids(entity.getResponsiblePerson(), ";"));
	sbLog.append(",配合人：");
	sbLog.append(CodeNameUtil.getUserNamesByUiids(entity.getCoordinatePerson(), ";"));
	sbLog.append(",开始日期：");
	sbLog.append(DateUtil.parseDateToString(entity.getCreatedDate()));
	sbLog.append(",目标日期：");
	sbLog.append(DateUtil.parseDateToString(entity.getTargetDate()));
	planOperationLogManager.addSpecialTaskLog(entity.getSpecialTaskId(), entity.getWaterNum(), 
		PlanOperationLogManager.OPERATION_TYPE_ADD,sbLog.toString());
	
	return RELOAD;
    }

    public String attachMents() throws Exception {
	canDeleteAttach = false;
	SpecialTask task = specialTaskManager.getEntity(bizEntityId);
	if (task != null) {
	    if (SpringSecurityUtils.hasRole(ROLE_CEOADMIN) || SpringSecurityUtils.hasRole(ROLE_SUPADMIN) || SpringSecurityUtils.hasRole(ROLE_VICADMIN)) {
		canDeleteAttach = true;
	    } else {
		String resPerson = task.getResponsiblePerson();
		if (StringUtils.isNotBlank(resPerson) && resPerson.contains(";" + SpringSecurityUtils.getCurrentUiid() + ";")) {
		    canDeleteAttach = true;
		}
	    }
	} else {
	    canDeleteAttach = true;
	}
	if ("1".equals(isAttachUpdated)) {
	    specialTaskManager.refreshUpdatedDate(bizEntityId);
	}
	attachList = buildAttachList(bizEntityId);
	return "attachList";
    }
    
    /**
     * 发邮件提醒
     * @return
     * @throws Exception
     */
    public String remind() throws Exception {
	prepareEntity();
	
	if (specialTaskManager.sendRemindEmail(entity)) {
	    
	    planOperationLogManager.addSpecialTaskLog(getId(), entity.getWaterNum(), 
		    PlanOperationLogManager.OPERATION_TYPE_EMAIL, "发送了一封提醒邮件");
	    
	    Struts2Utils.renderText("done");
	}
	
	return null;
    }
    
    /**
     * 获取指定文件的附件列表
     * @param entityId
     * @return
     */
    private List<AppAttachFile> buildAttachList(String entityId) {
	List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
	filters.add(new PropertyFilter("EQS_bizEntityId", entityId));
	filters.add(new PropertyFilter("NEQS_statusCd", "0"));
	
	return appAttachFileManager.find(filters);
    }
    
    @Override
    public String save() throws Exception {
	if (StringUtils.isNotBlank(getId())) {
	    entity = specialTaskManager.getEntity(getId());
	    specialTaskManager.saveSpecialTask(entity);
	}
	
	String status = entity.getStatus();
	if (DictContants.SPECIAL_TASK_STATUS_COMPLETE.equals(status)
		|| DictContants.SPECIAL_TASK_STATUS_PRECOMPLETE.equals(status)
		|| "1".equals(entity.getHiddenFlg())
		|| "1".equals(entity.getDeletedFlg())) {
	    deleteJbpmTask();
	} else {
	    updateToJbpmTask();
	}
	
	//添加日志
	saveLog();
	
	Struts2Utils.renderText("done");
	return null;
    }
    
	/**
	 * 添加保存时的操作日志
	 */
	private void saveLog() throws Exception{
	    if(StringUtils.isEmpty(type))return;
	    String logMsg = "";
	    String operType = PlanOperationLogManager.OPERATION_TYPE_UPDATE;
	    if("waterNum".equals(type)){
		logMsg = "修改编号为："+entity.getWaterNum();
	    }else if("subject".equals(type)){
		logMsg = "修改标题为："+entity.getSubject();
	    }else if("detail".equals(type)){
		logMsg = "修改详细内容为："+Util.clob2String(entity.getDetail());
	    }else if("sendPerson".equals(type)){
		logMsg = "修改发送人为："+CodeNameUtil.getUserNamesByUiids(entity.getSendPerson(), ";") ;
	    }else if("resPerson".equals(type)){
		logMsg = "修改责任人为："+CodeNameUtil.getUserNamesByUiids(entity.getResponsiblePerson(), ";") ;
	    }else if("corPerson".equals(type)){
		logMsg = "修改配合人为："+CodeNameUtil.getUserNamesByUiids(entity.getCoordinatePerson(), ";");
	    }else if("targetDate".equals(type)){
		logMsg = "修改目标日期为："+ DateUtil.parseDateToString(entity.getTargetDate());
	    }else if("preComplete".equals(type)){
		logMsg = "设置状态为：预完成";
	    }else if("complete".equals(type)){
		logMsg = "设置状态为：完成";
	    }else if("revert".equals(type)){
		logMsg = "恢复状态为：进行中";
	    }else if("delete".equals(type)){
		operType = PlanOperationLogManager.OPERATION_TYPE_DELETE;
		logMsg = "删除会议记录,编号为："+entity.getWaterNum();
	    }else if("hide".equals(type)){
		logMsg = "设置状态为：隐藏";
	    }else if("show".equals(type)){
		logMsg = "设置状态为：显示";
	    }
	    planOperationLogManager.addSpecialTaskLog(getId(), entity.getWaterNum(), operType, logMsg);
	}
    
    /**
     * 保存指定任务的留言信息
     * @return
     * @throws Exception
     */
    public String saveComment() throws Exception {
	prepareEntity();
	specialTaskCommentManager.saveTaskComment(comment, getId());
	//添加日志
	    planOperationLogManager.addSpecialTaskLog(getId(), entity.getWaterNum(), 
		    PlanOperationLogManager.OPERATION_TYPE_ADD,  "发表新留言："+Util.clob2String(comment));
	Struts2Utils.renderText("done");
	
	return null;
    }
    
    public String attachLog() throws Exception{
	    prepareEntity();
	    String flag = Struts2Utils.getParameter("flag");
	    String logMsg = "";
	    String operType = "";
	    if("del".equals(flag)){
		String fileId = Struts2Utils.getParameter("fileId");
		String fileName = appAttachFileManager.getEntity(fileId).getRealFileName();
		logMsg = "删除附件："+fileName;
		operType = PlanOperationLogManager.OPERATION_TYPE_DELETEATTACH;
	    }else{
		String fileName = Struts2Utils.getParameter("fileName");
		logMsg = "上传附件："+fileName;
		operType = PlanOperationLogManager.OPERATION_TYPE_UPLOADATTACH;
	    }
	    planOperationLogManager.addSpecialTaskLog(getId(), entity.getWaterNum(), operType, logMsg);
	    return null;
	}
    
    /**
     * 获取指定任务的留言信息
     * @return
     * @throws Exception
     */
    public String fetchComments() throws Exception {
	prepareEntity();
	
	commentList = entity.getSpecialTaskComments();
	specialTaskManager.orderComments(commentList);
	
	return "commentList";
    }
    
    /**
     * 获取指定任务的更新时间
     * @return
     * @throws Exception
     */
    public String fetchUpdatedDate() throws Exception {
	prepareEntity();
	
	Date updatedDate = entity.getUpdatedDate();
	String updatedDateStr = DateOperator.formatDate(updatedDate, "MM-dd");
	Struts2Utils.renderText(updatedDateStr);
	
	return null;
    }

    /**
     * 构造有效的实体对象
     */
    private void prepareEntity() {
	entity = specialTaskManager.getValidSpecialTask(this.getId());
    }
    
    @Override
    protected void prepareModel() throws Exception {
	if (StringUtils.isBlank(getId())) {
	    entity = new SpecialTask();
	} else {
	    entity = specialTaskManager.getEntity(getId());
	}
    }
    
    /**
     * 将状态码转为汉字表示
     * @return
     */
    public Map<String, String> getMapStatus() {
	Map<String, String> statusMap = new HashMap<String, String>();
	statusMap.put(DictContants.SPECIAL_TASK_STATUS_NEW, "进行中");
	statusMap.put(DictContants.SPECIAL_TASK_STATUS_PRECOMPLETE, "预完成");
	statusMap.put(DictContants.SPECIAL_TASK_STATUS_COMPLETE, "已完成");
	return statusMap;
    }
    
    /**
     * 转换用户信息
     * @param users
     * @return
     * @throws Exception
     */
    private List<UserDisplayInfo> transf2UserInfo(List<WsPlasUser> users) throws Exception {
	List<UserDisplayInfo> list = new ArrayList<UserDisplayInfo>();
	
	UserDisplayInfo info = null;
	for (WsPlasUser user : users) {
	    info = new UserDisplayInfo();
	    info.setCenterOrgCd(PlasCache.getCenterOrgCdByUserId(user.getPlasUserId()));
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
    
    /**
     * 构造任务记录的发出人，责任人，配合人中文名称，对评论按时间降序排列
     * @param tasks
     */
    private void buildTaskAttrs(List<SpecialTask> tasks) {
	String id = null;
	for (SpecialTask t : tasks) {
	    id = t.getSpecialTaskId();
	    mapResPersonNames.put(id, CodeNameUtil.getUserNamesByUiids(t.getResponsiblePerson(), ";"));
	    mapCorPersonNames.put(id, CodeNameUtil.getUserNamesByUiids(t.getCoordinatePerson(), ";"));
	    specialTaskManager.orderComments(t.getSpecialTaskComments());
	}
    }
    
    /**
     * 如果会议记录被更改或新建时，将会议记录的信息更新至JBPM_TASK表中
     */
    private void updateToJbpmTask() {
	if (entity == null)
	    throw new RuntimeException("专项任务记录不能为空");
	
	JbpmTask t = fetchJbpmTask();

	if (t == null) {
	    t = new JbpmTask();
	    t.setModuleCd(DictContants.JBPM_TASK_MODULE_CD_SPECIALTASK);
	    t.setModuleName("专项任务");
	    t.setJbpmId(entity.getSpecialTaskId());
	}
	
	t.setJbpmCd(entity.getWaterNum());
	t.setApplyDate(entity.getTargetDate());
	jbpmTaskManager.saveJbpmTask(t);
	
	t = fetchJbpmTask();
	if (t == null)
	    throw new RuntimeException("不能找到相应的任务");
	
	jbpmTaskCandidateManager.saveResPerson(t, entity.getResponsiblePerson());
    }
    
    /**
     * 如果该记录被删除/隐藏或者状态被设置为非进行中状态，则将JBPM_TASK表中相应的记录也删除
     */
    private void deleteJbpmTask() {
	if (entity == null)
	    throw new RuntimeException("会议记录不能为空");
	    
	JbpmTask task = fetchJbpmTask();
	    
	if (task != null) {
	    jbpmTaskCandidateManager.deleteCandOfTask(task);
	    jbpmTaskManager.delete(task);
	}
    }
    
    /**
     * 获取跟当前专项任务相关的Jbpm_task记录
     * @return
     */
    private JbpmTask fetchJbpmTask() {
	
	List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
	filters.add(new PropertyFilter("EQS_moduleCd", DictContants.JBPM_TASK_MODULE_CD_SPECIALTASK));
	filters.add(new PropertyFilter("EQS_jbpmId", entity.getSpecialTaskId()));
	List<JbpmTask> tasks = jbpmTaskManager.find(filters);
	if (tasks.size() > 1)
	    throw new RuntimeException("该条会议记录不能在jbpm中有两条记录!");
	
	if (tasks.size() == 0)
	    return null;
	
	return tasks.get(0);
    }
    
    /**
     * 导出Excel
     * @return
     * @throws Exception
     */
    public String exportExcel() throws Exception {
	List<SpecialTask> taskList = specialTaskManager.findBy(buildCriterion());
	IExcelExporter specialTaskExcelExporter = new SpecialTaskExcelExporter(taskList);
	excelFile = specialTaskExcelExporter.buildExportedExcelInputStream();
	Date now = Calendar.getInstance().getTime();
	String d = DateOperator.formatDate(now, "yyyy-MM-dd");
	String fileName = "专项任务列表_" + d;
	excelFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
	
	return "Exported";
    }
    
    @Override
    public String delete() throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String deleteBatch() throws Exception {
	// TODO Auto-generated method stub
	return null;
    }

    public SpecialTask getModel() {
	return entity;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getOrgBizCd() {
        return orgBizCd;
    }

    public void setOrgBizCd(String orgBizCd) {
        this.orgBizCd = orgBizCd;
    }

    public List<WsPlasUser> getPersonList() {
        return personList;
    }

    public void setPersonList(List<WsPlasUser> personList) {
        this.personList = personList;
    }

    public Clob getComment() {
        return comment;
    }

    public void setComment(Clob comment) {
        this.comment = comment;
    }

    public String getEntityTempId() {
        return entityTempId;
    }

    public void setEntityTempId(String entityTempId) {
        this.entityTempId = entityTempId;
    }

    public String getBizEntityId() {
        return bizEntityId;
    }

    public void setBizEntityId(String bizEntityId) {
        this.bizEntityId = bizEntityId;
    }

    public List<AppAttachFile> getAttachList() {
        return attachList;
    }

    public void setAttachList(List<AppAttachFile> attachList) {
        this.attachList = attachList;
    }
    
    @Override
    public Page<SpecialTask> getPage() {
	return page;
    }

    public List<SpecialTaskComment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<SpecialTaskComment> commentList) {
        this.commentList = commentList;
    }

    public Map<String, String> getMapSendPersonNames() {
        return mapSendPersonNames;
    }

    public Map<String, String> getMapResPersonNames() {
        return mapResPersonNames;
    }

    public Map<String, String> getMapCorPersonNames() {
        return mapCorPersonNames;
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

    public String getSearchBySendPerson() {
        return searchBySendPerson;
    }

    public void setSearchBySendPerson(String searchBySendPerson) {
        this.searchBySendPerson = searchBySendPerson;
    }

    public String getSearchByResPerson() {
        return searchByResPerson;
    }

    public void setSearchByResPerson(String searchByResPerson) {
        this.searchByResPerson = searchByResPerson;
    }

    public String getSearchBySubject() {
        return searchBySubject;
    }

    public void setSearchBySubject(String searchBySubject) {
        this.searchBySubject = searchBySubject;
    }

    public String getSearchByAttachName() {
        return searchByAttachName;
    }

    public void setSearchByAttachName(String searchByAttachName) {
        this.searchByAttachName = searchByAttachName;
    }

    public String getCurrentUiid() {
        return currentUiid;
    }

    public boolean isCanDeleteAttach() {
        return canDeleteAttach;
    }

    public void setCanDeleteAttach(boolean canDeleteAttach) {
        this.canDeleteAttach = canDeleteAttach;
    }

    public String getSearchByStatus() {
        return searchByStatus;
    }

    public void setSearchByStatus(String searchByStatus) {
        this.searchByStatus = searchByStatus;
    }

    public String getSearchByHiddenFlg() {
        return searchByHiddenFlg;
    }

    public void setSearchByHiddenFlg(String searchByHiddenFlg) {
        this.searchByHiddenFlg = searchByHiddenFlg;
    }

    public String getIsAttachUpdated() {
        return isAttachUpdated;
    }

    public void setIsAttachUpdated(String isAttachUpdated) {
        this.isAttachUpdated = isAttachUpdated;
    }

    public InputStream getExcelFile() {
        return excelFile;
    }

    public String getExcelFileName() {
        return excelFileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
