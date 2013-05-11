package com.hhz.ump.web.desk;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.DateParser;
import com.hhz.ump.aop.HttpRequester;
import com.hhz.ump.aop.HttpRespons;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.dly.DlyNoteManager;
import com.hhz.ump.dao.oa.OaAllAttentionManager;
import com.hhz.ump.dao.oa.OaChairmanReserveManager;
import com.hhz.ump.dao.oa.OaEmailManager;
import com.hhz.ump.dao.oa.OaMeetingRoomResManager;
import com.hhz.ump.dao.oa.OaNewsManager;
import com.hhz.ump.dao.oa.OaNotifyManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.dly.DlyNote;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.oa.OaChairmanReserve;
import com.hhz.ump.entity.oa.OaEmail;
import com.hhz.ump.entity.oa.OaMeetingRoomRes;
import com.hhz.ump.entity.oa.OaNews;
import com.hhz.ump.entity.oa.OaNotify;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.web.desk.bean.CoremailCondition;
import com.hhz.ump.web.desk.bean.CoremailResult;
import com.hhz.uums.entity.ws.WsAppDictData;
import com.hhz.uums.entity.ws.WsPlasAcct;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasUser;
import com.opensymphony.xwork2.ActionSupport;

/**
 * <p>新版PD首页action类</p>
 * 
 * @author  hy
 * @version 1.00 2011-12-22
 */
@Namespace("/desk2")
public class DeskNewAction  extends ActionSupport {

	private static final long serialVersionUID = -9040195512243930391L;
	

	@Autowired
	private DlyNoteManager dlyNoteManager;
	
	@Autowired
	private OaAllAttentionManager oaAllAttentionManager;
	
	private Page<DlyNote> pageNote = new Page<DlyNote>(16);

	// 首页邮件列表
	private Page<OaEmail> pageEmail = new Page<OaEmail>(6);

//	 未读提醒
	private int unReadMailNum = 0;
	
//	 未读邮件
//	private int unReadMailOutNum = 0;

	// 首页审批任务总裁会议及专项任务列表
	private Page<JbpmTask> pageJbpmTask = new Page<JbpmTask>(6);

	// 首页公告信息列表
	private Page<OaNotify> pageNotify = new Page<OaNotify>(4);

	private Map<String, String> mapNotifyAttachFileNames = new HashMap<String, String>();

	// 首页新闻信息列表
	private Page<OaNews> pageNews = new Page<OaNews>(4);

	// 实时通信使用参数
	private String webimPreUrl;// 例如 http://127.0.0.1:9080/

	private String webimContext;// 例如webim

	private String uiid;

	private String currentUserCd;

	private String userName;

	private String positionName;

	private String deptName;

	private String enablePresidentMeeting;
	
	//上次登录IP
	private String lastLoginIp;
	//上次登录时间
	private String lastLoginTime;

	// 搜索员工
	private List<WsPlasUser> searchUserResult = new ArrayList<WsPlasUser>();

	// 机构/员工
	private List<WsPlasOrg> orgs = new ArrayList<WsPlasOrg>();

	private List<WsPlasUser> users = new ArrayList<WsPlasUser>();

	private Map<String, String> jbpmTaskSubjMap = new HashMap<String, String>();


	private InputStream is;

	private String downFileName;

	
	//是否修改密码
	private String isModLastDateFlag;
	//邮件相关内容
	private CoremailCondition condition = new CoremailCondition();
	private CoremailResult result;
	@Autowired
	private OaNewsManager oaNewsManager;//新闻
	@Autowired
	private OaNotifyManager oaNotifyManager;//公告
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private OaChairmanReserveManager oaChairmanReserveManager;//总裁预约
	@Autowired
	private OaMeetingRoomResManager resManager;//会议室
	@Autowired
	private OaEmailManager oaEmailManager;//提醒
	// 显示在首页的最新公告列表，每页显示4条
	private Page<OaNotify> deskHomePager = new Page<OaNotify>(5);
	//显示提醒6条
	private Page<OaEmail> deskRemindPager = new Page<OaEmail>(7);
	//新闻list
	private List<OaNews> newsResult =new ArrayList<OaNews>();
	//会议提醒
	List<OaMeetingRoomRes> meetingResult = new ArrayList<OaMeetingRoomRes>();
	//会议提醒
	List<OaChairmanReserve> meetingChairmanResult = new ArrayList<OaChairmanReserve>();
	
	//提醒未读统计数
	private int remindNoReadEmailNum = 0;
	//提醒总数
	private int remindEmailNum = 0;
	
	//提醒页码
	private int pageRemindNo = 1;
	
	//邮件页码
	private String pageOutEmailNo = "1";
	
	private Map<String, String> roomMap;
	
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	
	// 公告附件的文件名
	private Map<String, String> mapAttachFileNames = new HashMap<String, String>();

	// 公告附件的真实文件名
	private Map<String, String> mapAttachFileRealNames = new HashMap<String, String>();

	/**
	 * 初始化首页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String main() throws Exception {
		WsPlasAcct acct = SpringSecurityUtils.getCurrentPlasUser();
		userName = acct.getUserName();
		lastLoginTime = DateOperator.formatDate(acct.getLastLoginDate(),true);

		
		// 若"职位"为空,则显示"职务"
		positionName = acct.getRealPositonName();// CodeNameUtil.getPositionNameByCd(wsUaapUser.getPositionCd());
		if (StringUtils.isBlank(positionName)) {
			positionName = (positionName == null) ? "" : positionName.trim();
		}

		deptName = acct.getOrgName();

		// 获取MSN参数
		//this.webimPreUrl = appParamManager.getMsnPreUrl();
		//this.webimContext = appParamManager.getMsnContext();
		this.uiid = SpringSecurityUtils.getCurrentUiid();
		this.currentUserCd = SpringSecurityUtils.getCurrentUserCd();
		
		buildNoteList();
		Struts2Utils.getRequest().setAttribute("db_num_wjcs", "");// 文件传输
		Struts2Utils.getRequest().setAttribute("db_num_zclh", "");//
		Struts2Utils.getRequest().setAttribute("db_num_zxrw", "");//
		Struts2Utils.getRequest().setAttribute("db_num_szjh", "");// 双周计划
		Struts2Utils.getRequest().setAttribute("db_num_qzsp", "");// 权责审批
		Struts2Utils.getRequest().setAttribute("db_num_rwsp", "");// 专项任务,总裁例会,出差审批,报销审批
		Struts2Utils.getRequest().setAttribute("db_num_bxsp", "");//
		 
		isModLastDateFlag = SpringSecurityUtils.isPwdModFlag();
		return "main";
	}
	/**
	 * 首页邮件相关内容
	 * @return
	 * @throws Exception 
	 */
	public String email() throws Exception {

		try {
			String pageNo = Struts2Utils.getParameter("pageNo");
			String[] fields = SpringSecurityUtils.getCoreMailFields();
			String server = fields[0];
			String sid = fields[1];

			condition.setSid(sid);
			condition.setItemPerPage("9");
			if (StringUtils.isNotBlank(pageNo)) {
				pageOutEmailNo = pageNo;
				condition.setPageNo(pageOutEmailNo);
			}

			// HttpRespons respons = new HttpRequester().sendGet(COS);
			HttpRequester requester = new HttpRequester();
			requester.setDefaultContentEncoding("UTF-8");
			HttpRespons respons = requester.sendGet(server + "/coremail/demo/message/listMessages.jsp?"
					+ condition.getParams());
			result = CoremailXmlParser.parserXml(respons.getContent());
				
		} catch (Exception e) {
			System.out.println("搜索邮件异常!" + e.getMessage());
		}
		//搜索提醒数量
		countOaNoReadEmail();
		return "email";
	}
	
	/**
	 * 提醒未读数据统计
	 * @throws Exception
	 */
	public void countOaNoReadEmail() throws Exception{
		String uiid = SpringSecurityUtils.getCurrentUiid();
		//remindEmailNum
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("userCd", uiid);
		StringBuffer sql = new StringBuffer("SELECT e.* ");
		sql.append(" from ump.oa_email e");
		sql.append(" WHERE e.user_cd = :userCd AND e.delete_flg = 0 and e.read_flg = 0 AND e.user_type_cd IN ('1', '2', '3')");
		remindNoReadEmailNum = Long.valueOf(oaEmailManager.countSqlResult(sql.toString(), values)).intValue();
	}
	
	/**
	 * 提醒数据统计
	 * @throws Exception
	 */
	public void countOaAllEmail() throws Exception{
		String uiid = SpringSecurityUtils.getCurrentUiid();
		//remindEmailNum
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("userCd", uiid);
		StringBuffer sql = new StringBuffer("SELECT e.* ");
		sql.append(" from ump.oa_email e");
		sql.append(" WHERE e.user_cd = :userCd AND e.delete_flg = 0  AND e.user_type_cd IN ('1', '2', '3')");
		remindEmailNum = Long.valueOf(oaEmailManager.countSqlResult(sql.toString(), values)).intValue();
	}
	/**
	 * 统计关注记录
	 * @throws Exception
	 */
	public void countAttention() throws Exception{
		Map<String, Object> values = new HashMap<String, Object>();
		String sql="select a.* from oa_all_attention a,plan_target b"
			+" where a.entity_id =b.plan_target_id and a.user_cd=:userCd";
		values.put("userCd", SpringSecurityUtils.getCurrentUiid());
		Struts2Utils.renderText(Long.valueOf(oaAllAttentionManager.countSqlResult(sql, values)).intValue()+"");
	}
	
	/**
	 * 获取提醒列表统计
	 * @return
	 * @throws Exception
	 */
	public String remind() throws Exception{
		String n = Struts2Utils.getParameter("pageNo");
		if(StringUtils.isNotBlank(n)){
			pageRemindNo = Integer.parseInt(n);
		}
		deskRemindPager.setPageNo(pageRemindNo);
		String uiid = SpringSecurityUtils.getCurrentUiid();
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("userCd", uiid);
		StringBuffer sql = new StringBuffer("SELECT e.* ");
		sql.append(" from ump.oa_email e");
		sql.append(" WHERE e.user_cd = :userCd AND e.delete_flg = 0  AND e.user_type_cd IN ('1', '2', '3')" )
		//.append("order by e.created_date desc")
		;
		
		Map<String, Class> mapClazz = new HashMap<String, Class>();
		mapClazz.put("e", OaEmail.class);
		deskRemindPager = oaEmailManager.findPageSql(deskRemindPager, sql.toString(), values, mapClazz);
		countOaAllEmail();
		return "remind";
	}
	
	/**
	 * 在首页中间栏的新闻栏显示最近的新闻列表，每页最多显示5条
	 * 
	 * @return
	 */
	public String news() throws Exception {
	    String orgCd = SpringSecurityUtils.getCurrentDeptCd();
	    Page<OaNews> pager = oaNewsManager.getFrontPageNews(orgCd,1, 5);
	    newsResult =pager.getResult();
	    return "news";
	}
	/**
	 * 在首页中间栏上部显示最新的公告，最多显示10条
	 * 
	 * @return
	 */
	public String notice() throws Exception {
		deskHomePager.setOrderBy("topFlg,sendTime,createdDate");
		deskHomePager.setOrder(Page.DESC + "," + Page.DESC + "," + Page.DESC);
		deskHomePager = oaNotifyManager.findPage(deskHomePager,
				buildCriterion());
		buildAttachFileNames(deskHomePager.getResult());
		return "notice";
	}
	
	/**
	 * 构造公告列表中每条记录的附件的文件名称
	 * 
	 * @param notifyList
	 */
	private void buildAttachFileNames(List<OaNotify> notifyList) {
		// 一个公告有且仅有一个PDF附件来显示其内容
		for (OaNotify oaNotify : notifyList) {
			Page<AppAttachFile> attachmentPager = new Page<AppAttachFile>(1);
			List<PropertyFilter> filters = new ArrayList<PropertyFilter>();

			filters.add(new PropertyFilter("EQS_bizEntityId", oaNotify
					.getOaNotifyId()));
			filters.add(new PropertyFilter("EQS_bizModuleCd", "oaNotify"));
			filters.add(new PropertyFilter("NEQS_statusCd", "0"));
			attachmentPager.setOrderBy("createdDate");
			attachmentPager.setOrder(Page.DESC);

			attachmentPager = appAttachFileManager.findPage(attachmentPager,
					filters);
			List<AppAttachFile> files = attachmentPager.getResult();
			if (files != null && files.size() > 0) {
				AppAttachFile file = files.get(0);
				mapAttachFileNames.put(oaNotify.getOaNotifyId(), file
						.getFileName());
				mapAttachFileRealNames.put(oaNotify.getOaNotifyId(), file
						.getRealFileName());
			} else {
				mapAttachFileNames.put(oaNotify.getOaNotifyId(), "");
				mapAttachFileRealNames.put(oaNotify.getOaNotifyId(), "");
			}
		}
	}
	
	/*
	 * 首页用，显示有更新的每日会议提醒
	 */
	public String meeting() throws Exception {
		String userCd = SpringSecurityUtils.getCurrentUiid();
		List<OaMeetingRoomRes> meetingRoomList = resManager.getCurrentAllMeetingRoom(userCd);
		List<OaChairmanReserve> chairmanReserveList = oaChairmanReserveManager.getCurrentAllMeetingRoom(userCd);
		//最新总裁会议
		OaChairmanReserve oaChairMan = null;
		//最新会议古北
		OaMeetingRoomRes oaMeeting = null;
		String dictTypeCd = "";
		//先取总裁会议
		if(chairmanReserveList != null && !chairmanReserveList.isEmpty()){
			OaChairmanReserve orm = chairmanReserveList.get(0);
			oaChairMan = orm;
			//meetingChairmanResult.add(orm);
		}
		//获取会议室
		if(meetingRoomList!=null && !meetingRoomList.isEmpty()){
			//只获取一条记录
			OaMeetingRoomRes orm =meetingRoomList.get(0);
			if(OaMeetingRoomResManager.ADDRESS_GB.equals(orm.getAddrType())){
				dictTypeCd = DictContants.OA_MEETING_ROOM;
			}else{
				dictTypeCd = DictContants.OA_MEETING_ROOM_SHC;
			}
			List<WsAppDictData> listDict = PlasCache.getDictDataList(dictTypeCd);
			for(int i=0;null!=listDict && i<listDict.size();i++){
				WsAppDictData dict = listDict.get(i);
				orm.setRelatedDept(dict.getRemark());
			}
			roomMap =  appDictTypeManager.getDictDataByTypeCd(dictTypeCd);
			roomMap.remove("");
			String roomId = orm.getRoomId();
			String roomName = roomMap.get(roomId);
			orm.setRemark(roomName);
			oaMeeting = orm;
			//meetingResult.add(orm);
		}
		
		//判断哪个时间最新
		if(oaChairMan != null && oaMeeting != null){
			//Long p = DateParser.getMinutes(oaChairMan.getEndTime(), oaMeeting.getEndTime());
			if(DateParser.getMinutes(oaChairMan.getEndTime(), oaMeeting.getEndTime()) >=0){
				meetingChairmanResult.add(oaChairMan);
			}else{
				meetingResult.add(oaMeeting);
			}
		}else if(oaChairMan != null){
			meetingChairmanResult.add(oaChairMan);
		}else if(oaMeeting != null){
			meetingResult.add(oaMeeting);
		}
		return "meeting";
	}
	/**
	 * 获取部门代码
	 * @return
	 * @throws ParseException
	 */
	private Criterion[] buildCriterion() throws ParseException {
		List<Criterion> criterionList = new ArrayList<Criterion>();
		criterionList.add(Restrictions.eq("enabledFlg", "1"));
		String orgCd = SpringSecurityUtils.getCurrentDeptCd();
		criterionList.add(Restrictions.sqlRestriction(
				"(TO_DEPT_CDS like lower(?) or TO_DEPT_CDS = 'all')", "%,"
						+ orgCd + ",%", Hibernate.STRING));

		Date today = DateOperator.truncDate(new Date());
		criterionList.add(Restrictions.le("sendTime", today));
		criterionList.add(Restrictions.le("startDate", today));
		criterionList.add(Restrictions.ge("endDate", today));
		return criterionList.toArray(new Criterion[criterionList.size()]);
	}
	public CoremailResult getResult() {
		return result;
	}

	public void setResult(CoremailResult result) {
		this.result = result;
	}

	public CoremailCondition getCondition() {
		return condition;
	}

	public void setCondition(CoremailCondition condition) {
		this.condition = condition;
	}

	
	/**
	 * 构造首页便签列表
	 */
	private void buildNoteList() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter filter = new PropertyFilter("EQS_creator", SpringSecurityUtils.getCurrentUiid());
		filters.add(filter);
		pageNote.setOrderBy("seq");
		pageNote.setOrder(Page.ASC);
		pageNote = dlyNoteManager.findPage(pageNote, filters);
		if (pageNote.getTotalCount() < 16) {
			filter = new PropertyFilter("GEL_seq", 0);
			filters.add(filter);
			dlyNoteManager.initDlyNotes(SpringSecurityUtils.getCurrentUiid());
			pageNote = dlyNoteManager.findPage(pageNote, filters);
		}
	}
	
	public String getWebimPreUrl() {
		return webimPreUrl;
	}

	public void setWebimPreUrl(String webimPreUrl) {
		this.webimPreUrl = webimPreUrl;
	}

	public String getWebimContext() {
		return webimContext;
	}

	public void setWebimContext(String webimContext) {
		this.webimContext = webimContext;
	}

	public String getUiid() {
		return uiid;
	}

	public void setUiid(String uiid) {
		this.uiid = uiid;
	}

	public List<WsPlasUser> getSearchUserResult() {
		return searchUserResult;
	}

	public void setSearchUserResult(List<WsPlasUser> searchUserResult) {
		this.searchUserResult = searchUserResult;
	}

	public List<WsPlasUser> getUsers() {
		return users;
	}

	public void setUsers(List<WsPlasUser> users) {
		this.users = users;
	}

	public Page<DlyNote> getPageNote() {
		return pageNote;
	}

	public void setPageNote(Page<DlyNote> pageNote) {
		this.pageNote = pageNote;
	}

	public List<WsPlasOrg> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<WsPlasOrg> orgs) {
		this.orgs = orgs;
	}

	public String getUserName() {
		return userName;
	}

	public String getPositionName() {
		return positionName;
	}

	public String getDeptName() {
		return deptName;
	}

	public String getCurrentUserCd() {
		return currentUserCd;
	}

	public void setCurrentUserCd(String currentUserCd) {
		this.currentUserCd = currentUserCd;
	}

	public Page<OaNotify> getPageNotify() {
		return pageNotify;
	}

	public void setPageNotify(Page<OaNotify> pageNotify) {
		this.pageNotify = pageNotify;
	}

	public Map<String, String> getMapNotifyAttachFileNames() {
		return mapNotifyAttachFileNames;
	}

	public void setMapNotifyAttachFileNames(Map<String, String> mapNotifyAttachFileNames) {
		this.mapNotifyAttachFileNames = mapNotifyAttachFileNames;
	}

	public Page<OaNews> getPageNews() {
		return pageNews;
	}

	public void setPageNews(Page<OaNews> pageNews) {
		this.pageNews = pageNews;
	}

	public Page<OaEmail> getPageEmail() {
		return pageEmail;
	}

	public void setPageEmail(Page<OaEmail> pageEmail) {
		this.pageEmail = pageEmail;
	}

	public int getUnReadMailNum() {
		return unReadMailNum;
	}

	public void setUnReadMailNum(int unReadMailNum) {
		this.unReadMailNum = unReadMailNum;
	}

	public Page<JbpmTask> getPageJbpmTask() {
		return pageJbpmTask;
	}

	public void setPageJbpmTask(Page<JbpmTask> pageJbpmTask) {
		this.pageJbpmTask = pageJbpmTask;
	}

	public String getEnablePresidentMeeting() {
		return enablePresidentMeeting;
	}

	public Map<String, String> getJbpmTaskSubjMap() {
		return jbpmTaskSubjMap;
	}

	public void setJbpmTaskSubjMap(Map<String, String> jbpmTaskSubjMap) {
		this.jbpmTaskSubjMap = jbpmTaskSubjMap;
	}
 
	/**
	 * desk-orguser.jsp 每隔10分钟刷新在线人数
	 * 
	 * @return
	 */
	public String getCurrentOnlineCount() {
		Struts2Utils.renderText("success," + PlasCache.getUserOnlineCount());
		return null;
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

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
 
	// 检查密码过期 1-是 0-否
	public String isPwdExpired(){
		Struts2Utils.renderText(SpringSecurityUtils.isPwdModFlag());
		return null;
	}

	public String getIsModLastDateFlag() {
		return isModLastDateFlag;
	}

	public void setIsModLastDateFlag(String isModLastDateFlag) {
		this.isModLastDateFlag = isModLastDateFlag;
	}
	public List<OaNews> getNewsResult() {
		return newsResult;
	}
	public Page<OaNotify> getDeskHomePager() {
		return deskHomePager;
	}
	public void setDeskHomePager(Page<OaNotify> deskHomePager) {
		this.deskHomePager = deskHomePager;
	}
	public List<OaMeetingRoomRes> getMeetingResult() {
		return meetingResult;
	}
	/**
	 * @return the remindEmailNum
	 */
	public int getRemindEmailNum() {
		return remindEmailNum;
	}
	/**
	 * @param remindEmailNum the remindEmailNum to set
	 */
	public void setRemindEmailNum(int remindEmailNum) {
		this.remindEmailNum = remindEmailNum;
	}
	/**
	 * @return the pageRemindNo
	 */
	public int getPageRemindNo() {
		return pageRemindNo;
	}
	/**
	 * @param pageRemindNo the pageRemindNo to set
	 */
	public void setPageRemindNo(int pageRemindNo) {
		this.pageRemindNo = pageRemindNo;
	}
	/**
	 * @return the deskRemindPager
	 */
	public Page<OaEmail> getDeskRemindPager() {
		return deskRemindPager;
	}
	/**
	 * @param deskRemindPager the deskRemindPager to set
	 */
	public void setDeskRemindPager(Page<OaEmail> deskRemindPager) {
		this.deskRemindPager = deskRemindPager;
	}
	/**
	 * @return the pageOutEmailNo
	 */
	public String getPageOutEmailNo() {
		return pageOutEmailNo;
	}
	/**
	 * @param pageOutEmailNo the pageOutEmailNo to set
	 */
	public void setPageOutEmailNo(String pageOutEmailNo) {
		this.pageOutEmailNo = pageOutEmailNo;
	}
	/**
	 * @return the remindNoReadEmailNum
	 */
	public int getRemindNoReadEmailNum() {
		return remindNoReadEmailNum;
	}
	/**
	 * @param remindNoReadEmailNum the remindNoReadEmailNum to set
	 */
	public void setRemindNoReadEmailNum(int remindNoReadEmailNum) {
		this.remindNoReadEmailNum = remindNoReadEmailNum;
	}
	/**
	 * @return the mapAttachFileNames
	 */
	public Map<String, String> getMapAttachFileNames() {
		return mapAttachFileNames;
	}
	/**
	 * @return the mapAttachFileRealNames
	 */
	public Map<String, String> getMapAttachFileRealNames() {
		return mapAttachFileRealNames;
	}
	/**
	 * @param mapAttachFileNames the mapAttachFileNames to set
	 */
	public void setMapAttachFileNames(Map<String, String> mapAttachFileNames) {
		this.mapAttachFileNames = mapAttachFileNames;
	}
	/**
	 * @param mapAttachFileRealNames the mapAttachFileRealNames to set
	 */
	public void setMapAttachFileRealNames(Map<String, String> mapAttachFileRealNames) {
		this.mapAttachFileRealNames = mapAttachFileRealNames;
	}
	/**
	 * @return the meetingChairmanResult
	 */
	public List<OaChairmanReserve> getMeetingChairmanResult() {
		return meetingChairmanResult;
	}
	/**
	 * @param meetingChairmanResult the meetingChairmanResult to set
	 */
	public void setMeetingChairmanResult(List<OaChairmanReserve> meetingChairmanResult) {
		this.meetingChairmanResult = meetingChairmanResult;
	}
	
}
