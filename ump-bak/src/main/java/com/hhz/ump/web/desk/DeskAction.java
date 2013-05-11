package com.hhz.ump.web.desk;

import java.io.InputStream;
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
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.dly.DlyNoteManager;
import com.hhz.ump.entity.dly.DlyNote;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.oa.OaEmail;
import com.hhz.ump.entity.oa.OaNews;
import com.hhz.ump.entity.oa.OaNotify;
import com.hhz.ump.util.JXLExcelUtil;
import com.hhz.ump.util.TreePanelNode;
import com.hhz.ump.util.TreePanelUtil;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasAcct;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasUser;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author huangbj
 *
 */
@Namespace("/desk")
@Results({
	@Result(name = "main2", location = "../desk2/desk-new!main.action", type = "redirect", params = {}),
	@Result(name = "export", type = "stream", params = { "contentType", "application/vnd.ms-excel", "inputName", "is", "contentDisposition",
		"attachment;filename=${downFileName}.xls" }) })
public class DeskAction extends ActionSupport {
	private static final long serialVersionUID = -5833728668625630228L;
 
	@Autowired
	private DlyNoteManager dlyNoteManager;

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
	
	
	public String mainContainer() throws Exception {
		return main();
	}

	/**
	 * 初始化首页
	 * @return
	 * @throws Exception
	 */
	public String main3() throws Exception {
		//Date start = new Date();
		//logger.info("start main:" + start);
		WsPlasAcct acct = SpringSecurityUtils.getCurrentPlasUser();
		if (acct != null) {
			userName = acct.getUserName();
			lastLoginTime = DateOperator.formatDate(acct.getLastLoginDate(), true);

			// 若"职位"为空,则显示"职务"
			positionName = acct.getRealPositonName();// CodeNameUtil.getPositionNameByCd(wsUaapUser.getPositionCd());
			if (StringUtils.isBlank(positionName)) {
				positionName = (positionName == null) ? "" : positionName.trim();
			}

			deptName = acct.getOrgName();

			// 获取MSN参数
			// this.webimPreUrl = appParamManager.getMsnPreUrl();
			// this.webimContext = appParamManager.getMsnContext();
			this.uiid = SpringSecurityUtils.getCurrentUiid();
			this.currentUserCd = SpringSecurityUtils.getCurrentUserCd();

			buildNoteList();
			// buildEmailList();
			// buildJbpmTaskList();
			// buildNotifyList();
			// buildNewsList();

			// enablePresidentMeeting();
			// Date end = new Date();
			// logger.info("end main:" + end);
			// long cost = (end.getTime() - start.getTime());
			// logger.info("cost:" + cost);
			Struts2Utils.getRequest().setAttribute("db_num_wjcs", "");// 文件传输
			Struts2Utils.getRequest().setAttribute("db_num_zclh", "");//
			Struts2Utils.getRequest().setAttribute("db_num_zxrw", "");//
			Struts2Utils.getRequest().setAttribute("db_num_szjh", "");// 双周计划
			Struts2Utils.getRequest().setAttribute("db_num_qzsp", "");// 权责审批
			Struts2Utils.getRequest().setAttribute("db_num_rwsp", "");// 专项任务,总裁例会,出差审批,报销审批
			Struts2Utils.getRequest().setAttribute("db_num_bxsp", "");//

			isModLastDateFlag = SpringSecurityUtils.isPwdModFlag();
		}
		return "main";
	}
	
	public String main() throws Exception {
		return "main2";
	}

	// 在线人数、总数
	public long getOnlineCount(){
		return Util.getPlasService().getUserOnlineCount();
	}
	public long getTotalCount(){
		return PlasCache.getAcctActiveList().size();
	}
	
	public String exportExcel() throws Exception {
		// List<PropertyFilter> filters = buildPropertyFilters();
		TreePanelNode panelNode = TreePanelUtil.getCachePhysicalTree();
		Map beans = new HashMap();
		beans.put("emp", panelNode);

		is = JXLExcelUtil.initJxlsInputStream(beans, "empList.xls");

		String fileName = "员工通讯录" + DateOperator.formatDate(new Date(), "MMddHHmm");
		downFileName = new String(fileName.getBytes("GBK"), "ISO8859-1");
		return "export";
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

	/**
	 * 调用 ajax 刷新搜索结果清单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getUsersByFilter() throws Exception {

		String value = Struts2Utils.getParameter("value");
		String strPageNo = Struts2Utils.getParameter("pageNo");
		if (StringUtils.isNotEmpty(value)) {
			WsPlasUser wsUaapUser = new WsPlasUser();
			wsUaapUser.setUiid(value);
			wsUaapUser.setUserName(value);
			wsUaapUser.setEmail(value);
			wsUaapUser.setFixedPhone(value);
			wsUaapUser.setMobilePhone(value);
			if (StringUtils.isBlank(strPageNo)) {
				strPageNo = "1";
			}
			int iPageNo = Integer.valueOf(strPageNo).intValue();
			searchUserResult = Util.getPlasService().getUserList(wsUaapUser, iPageNo,30);

			// 前台匹配打钩
			Struts2Utils.getRequest().setAttribute("queryCondition", value);
		}
		return "user";
	}

	// 打开文件共享
	public String openShare() throws Exception {

		return "share";
	}
	
	public String dwr() throws Exception {
		return "dwr";
	}
	public String send() throws Exception {
		return "send";
	}

	/**
	 * 根据新闻类别CD显示新闻类别中文名称列表
	 * 
	 * @return
	 */
	// public Map<String, String> getMapNewsType() {
	// return mapNewsType;
	// }

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
}
