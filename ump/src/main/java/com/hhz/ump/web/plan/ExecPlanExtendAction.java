package com.hhz.ump.web.plan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.oa.OaMeetingRoomResManager;
import com.hhz.ump.dao.plan.ExecPlanDetailManager;
import com.hhz.ump.dao.plan.ExecPlanExtendManager;
import com.hhz.ump.dao.plan.ExecPlanLayoutManager;
import com.hhz.ump.dao.plan.ExecPlanNodeManager;
import com.hhz.ump.entity.oa.OaMeetingRoomRes;
import com.hhz.ump.entity.plan.ExecPlanProcessing;
import com.hhz.ump.entity.plan.ExecPlanProcessingVO;
import com.hhz.ump.util.DictContants;
import com.hhz.uums.entity.ws.WsAppDictData;
import com.hhz.uums.entity.ws.WsPlasOrg;

@Namespace("/plan")
public class ExecPlanExtendAction extends CrudActionSupport<OaMeetingRoomRes> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ExecPlanNodeManager execPlanNodeManager;
	@Autowired
	private ExecPlanLayoutManager execPlanLayoutManager;
	@Autowired
	private ExecPlanDetailManager detailManager;
	@Autowired
	private OaMeetingRoomResManager resManager;
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	@Autowired
	private ExecPlanExtendManager execPlanExtendManager;
	
	private Map<String, String> roomMap;
	private Map<String, String> mapProjectsType;
	private List<ExecPlanProcessingVO> epvoList = new ArrayList<ExecPlanProcessingVO>();
	private List<ExecPlanProcessing> eppList = new ArrayList<ExecPlanProcessing>();
	private String projectCd;
	private String ifFirst = "0" ; //
	private boolean aViewFlag = false; // 能否可以切换
	private List<WsPlasOrg> projects;
	private String currentUiid;
	private int now_month = 0; // 当前的月份
	private int now_year = 0; // 当前的年份
	private String isChange = "";
	
	// 每页显示10条指令单
//	private Page<ExecPlanProcessing> pages = new Page<ExecPlanProcessing>(10);
	
	
	public Map<String, String> getRoomMap() {
		return roomMap;
	}

	public int getNow_month() {
		return now_month;
	}

	public int getNow_year() {
		return now_year;
	}

	public String getIsChange() {
		return isChange;
	}

	public void setIsChange(String isChange) {
		this.isChange = isChange;
	}

	public void setNow_year(int nowYear) {
		now_year = nowYear;
	}

	public void setNow_month(int nowMonth) {
		now_month = nowMonth;
	}

	public Map<String, String> getMapProjectsType() {
		return mapProjectsType;
	}

	public void setRoomMap(Map<String, String> roomMap) {
		this.roomMap = roomMap;
	}

	private Map<String, String> mapExecPlanTips = new HashMap<String, String>();
	public Map<String, String> getMapExecPlanTips() {
		return mapExecPlanTips;
	}

	public void setMapExecPlanTips(Map<String, String> mapExecPlanTips) {
		this.mapExecPlanTips = mapExecPlanTips;
	}

	
	/*
	 * 首页用，显示有更新的每日会议提醒
	 */
	public String forHome() throws Exception {
		String userCd = SpringSecurityUtils.getCurrentUiid();
		//获取当前所有的没有过期的会议记录
		String dictTypeCd = "";
		List<OaMeetingRoomRes> meetingRoomList = resManager.getCurrentAllMeetingRoom(userCd);
		List<OaMeetingRoomRes> meetingRoomListNew = new ArrayList<OaMeetingRoomRes>();
		//获取会议室
		if(meetingRoomList!=null && !meetingRoomList.isEmpty()){
			for(OaMeetingRoomRes orm :meetingRoomList){
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
				meetingRoomListNew.add(orm);
			}
		}
		page.setPageSize(5);
		page.setResult(meetingRoomList);
		Struts2Utils.getRequest().setAttribute("meetingDay",meetingRoomListNew.size());
		Struts2Utils.getRequest().setAttribute("meetingRoomList",meetingRoomListNew);
		return "forHome";
	}
	
	/**
	 * 手动测试用
	 * @return
	 */
	public String test(){
		String createDate = Struts2Utils.getParameter("createDate");
		String typecd_select = Struts2Utils.getParameter("typecd_select");
		SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormatter2.parse(createDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ifFirst = "1";
		execPlanExtendManager.delProcessingData(typecd_select, getDateYesterday(date));
		execPlanExtendManager.handProcessingTasks(typecd_select,date);
		//插入数据前清除记录
//		execPlanExtendManager.delProcessingData(typecd_select, getDateYesterday(date));
//		execPlanExtendManager.processingTasks(typecd_select,date);
		projectCd = typecd_select;
		return this.dataMissingAnalysis();
	}

	/**
	 * 判断当前用户是否有权限查看
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean buildPermission(){
		boolean flag = false;
		List<WsPlasOrg> list = WorkPlanUtil.getOrgEstateOrgList();
		if(list!=null && !list.isEmpty()){
			for(WsPlasOrg wo :list){
				if(SpringSecurityUtils.getCurrentCenterCd().equals(wo.getOrgCd())){
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
	@SuppressWarnings({ "deprecation", "null", "unchecked" })
//	public String dataMissingAnalysis() {
//			if (!buildPermission()) {
//				// 执行计划超级管理员、三级计划-总部管理员可以切换查看
//				aViewFlag = true;
//			}else{
//				projectCd = SpringSecurityUtils.getCurrentCenterCd();
//			}
//			//手工测试用
//			this.currentUiid = SpringSecurityUtils.getCurrentUiid();
//			// 默认
//			if (StringUtils.isBlank(projectCd)) {
//				ifFirst = "0";
//				projectCd = "1004";
//			}
//			ifFirst = Struts2Utils.getParameter("ifFirst");
//			if(ifFirst==null){
//				ifFirst = "0";
//			}
//			String projectName = "";
//			List<WsPlasOrg> list = WorkPlanUtil.getOrgEstateOrgList();
//			mapProjectsType = new TreeMap<String, String>();
//			for (WsPlasOrg m : list) {
//				mapProjectsType.put(m.getOrgCd(), m.getOrgName());
//				if(projectCd.trim().equals(m.getOrgCd().trim())){
//					projectName = m.getOrgName();
//				}
//			}
//			java.util.Date dDate = new java.util.Date(); 
//			//搜索出以当前时间为准往回30天的数据
//			for(int i=1;i<31;i++){
//				ExecPlanProcessingVO epvo = new ExecPlanProcessingVO();
//				java.util.Date newDate = new java.sql.Date(dDate.getYear(),dDate.getMonth(),dDate.getDate()-i);
//				List<ExecPlanProcessing> allList = execPlanExtendManager.getAllExecPlanProcessing(projectCd,newDate);
//			    if (allList != null && allList.size() != 0){
//			    	Comparator<ExecPlanProcessing> c = new Comparator<ExecPlanProcessing>() {
//						public int compare(ExecPlanProcessing c1, ExecPlanProcessing c2){
//						    // 按当天未回复降序排序
//					        return Integer.valueOf(c2.getNoReplyStatus()).compareTo(Integer.valueOf(c1.getNoReplyStatus()));
//						}
//					    };
//					    Collections.sort(allList, c);
//			    }
//				int noConfirm = 0;
//				int going = 0;
//				int suspend = 0;
//				int complete = 0;
//				int noReply = 0;
//				if(allList!=null && !allList.isEmpty()){
//					for(ExecPlanProcessing execPlanProcessing :allList){
//						projectName = execPlanProcessing.getProjectName();
//						noConfirm = noConfirm+Integer.valueOf(execPlanProcessing.getNoConfirmStatus());
//						going = going+Integer.valueOf(execPlanProcessing.getGoingStatus());
//						suspend = suspend+Integer.valueOf(execPlanProcessing.getSuspendStatus());
//						complete = complete+Integer.valueOf(execPlanProcessing.getCompleteStatus());
//						noReply = noReply+Integer.valueOf(execPlanProcessing.getNoReplyStatus());
//					}
//				}
//				epvo.setExecPlanProcessingId(String.valueOf(i));
//				epvo.setProjectName(projectName);//地产
//				if(noReply!=0){
//					epvo.setNoReplyStatus("<font color='red'>"+String.valueOf(noReply)+"</font>");//未回复
//				}else{
//					epvo.setNoReplyStatus(String.valueOf(noReply));//未回复
//				}
//				if(noConfirm!=0){
//					epvo.setNoConfirmStatus("<font color='#000'>"+String.valueOf(noConfirm)+"</font>");//未确认
//				}else{
//					epvo.setNoConfirmStatus(String.valueOf(noConfirm));//未确认
//				}
//				if(going!=0){
//					epvo.setGoingStatus("<font color='#366092'>"+String.valueOf(going)+"</font>");//进行中
//				}else{
//					epvo.setGoingStatus(String.valueOf(going));//进行中
//				}
//				if(suspend!=0){
//					epvo.setSuspendStatus("<font color='#953734'>"+String.valueOf(suspend)+"</font>");//过期
//				}else{
//					epvo.setSuspendStatus(String.valueOf(suspend));//过期
//				}
//				if(complete!=0){
//					epvo.setCompleteStatus("<font color='#79923c'>"+String.valueOf(complete)+"</font>");//完成
//				}else{
//					epvo.setCompleteStatus(String.valueOf(complete));//完成
//				}
//				epvo.setCreatedDate(newDate);//创建时间
//				epvo.setExecPlanProcessing(allList);
//				epvoList.add(epvo);
//			}
//			Struts2Utils.getRequest().setAttribute("ifFirst", ifFirst);
//		return "automaticProcessing";
//	}
	
	/**
	 * 
	 */
	public String dataMissingAnalysis() {
		//execPlanExtendManager.automaticProcessingTasks();
			if (!buildPermission()) {
				// 执行计划超级管理员、三级计划-总部管理员可以切换查看
				aViewFlag = true;
			}else{
				projectCd = SpringSecurityUtils.getCurrentCenterCd();
			}
			//手工测试用
			this.currentUiid = SpringSecurityUtils.getCurrentUiid();
			// 默认
			if (StringUtils.isBlank(projectCd)) {
				ifFirst = "0";
				projectCd = "1004";
			}
			ifFirst = Struts2Utils.getParameter("ifFirst");
			if(ifFirst==null){
				ifFirst = "0";
			}
			String projectName = "";
			List<WsPlasOrg> list = WorkPlanUtil.getOrgEstateOrgList();
			mapProjectsType = new TreeMap<String, String>();
			for (WsPlasOrg m : list) {
				mapProjectsType.put(m.getOrgCd(), m.getOrgName());
				if(projectCd.trim().equals(m.getOrgCd().trim())){
					projectName = m.getOrgName();
				}
			}
			if (!isChange.equals("true")){
				now_year = DateOperator.getYear(new Date());
				now_month = DateOperator.getMonth12(new Date());
			}
			//搜索出当前月的数据
			List<ExecPlanProcessing> curList = execPlanExtendManager.gettMonthExecPlanProcessing(projectCd,DateOperator.getFirstDayOfMonth12(now_year,now_month),DateOperator.getLastDayOfMonth12(now_year,now_month));
			//筛选日期
			String key = null;
			Map<String, ExecPlanProcessing> mm = new TreeMap<String, ExecPlanProcessing>();
			for(ExecPlanProcessing epp : curList){
				key = formateDate2YMd(epp.getCreatedDate());
				//如果是相同key，就不进入结果的map
				boolean has_same = false;
				try {
					Set<Map.Entry<String, ExecPlanProcessing>> set = mm.entrySet();
					for (Iterator<Map.Entry<String, ExecPlanProcessing>> it = set.iterator(); it.hasNext();) {
							Map.Entry<String, ExecPlanProcessing> entry = (Map.Entry<String, ExecPlanProcessing>) it.next();
							String compareStr = entry.getKey();
							if (key.equalsIgnoreCase(compareStr)) {
								has_same = true;
								break;
							}
					}
				}catch(Exception e){}
				if(!has_same){
					mm.put(key, epp);
				}
			}
			List<String> listEPP = new ArrayList<String>();
			Iterator it = mm.keySet().iterator();
			while (it.hasNext()) {
				String keys = it.next().toString();
				listEPP.add(keys);
			} 
			if (listEPP!=null && !listEPP.isEmpty()){
				for(int i=0;i<listEPP.size();i++){
					ExecPlanProcessingVO epvo = new ExecPlanProcessingVO();
//					java.util.Date newDate = new java.sql.Date(dDate.getYear(),dDate.getMonth(),dDate.getDate()-i);
					List<ExecPlanProcessing> allList = execPlanExtendManager.getAllExecPlanProcessing(projectCd,DateOperator.parse(listEPP.get(i), "yyyy-MM-dd"));
				    if (allList != null && allList.size() != 0){
				    	Comparator<ExecPlanProcessing> c = new Comparator<ExecPlanProcessing>() {
							public int compare(ExecPlanProcessing c1, ExecPlanProcessing c2){
							    // 按当天未回复降序排序
						        return Integer.valueOf(c2.getNoReplyStatus()).compareTo(Integer.valueOf(c1.getNoReplyStatus()));
							}
						    };
						    Collections.sort(allList, c);
				    }
					int noConfirm = 0;
					int going = 0;
					int suspend = 0;
					int complete = 0;
					int noReply = 0;
					if(allList!=null && !allList.isEmpty()){
						for(ExecPlanProcessing execPlanProcessing :allList){
							projectName = execPlanProcessing.getProjectName();
							noConfirm = noConfirm+Integer.valueOf(execPlanProcessing.getNoConfirmStatus());
							going = going+Integer.valueOf(execPlanProcessing.getGoingStatus());
							suspend = suspend+Integer.valueOf(execPlanProcessing.getSuspendStatus());
							complete = complete+Integer.valueOf(execPlanProcessing.getCompleteStatus());
							noReply = noReply+Integer.valueOf(execPlanProcessing.getNoReplyStatus());
						}
					}
					epvo.setExecPlanProcessingId(String.valueOf(i));
					epvo.setProjectName(projectName);//地产
					if(noReply!=0){
						epvo.setNoReplyStatus("<font color='red'>"+String.valueOf(noReply)+"</font>");//未回复
					}else{
						epvo.setNoReplyStatus(String.valueOf(noReply));//未回复
					}
					if(noConfirm!=0){
						epvo.setNoConfirmStatus("<font color='#000'>"+String.valueOf(noConfirm)+"</font>");//未确认
					}else{
						epvo.setNoConfirmStatus(String.valueOf(noConfirm));//未确认
					}
					if(going!=0){
						epvo.setGoingStatus("<font color='#366092'>"+String.valueOf(going)+"</font>");//进行中
					}else{
						epvo.setGoingStatus(String.valueOf(going));//进行中
					}
					if(suspend!=0){
						epvo.setSuspendStatus("<font color='#953734'>"+String.valueOf(suspend)+"</font>");//过期
					}else{
						epvo.setSuspendStatus(String.valueOf(suspend));//过期
					}
					if(complete!=0){
						epvo.setCompleteStatus("<font color='#79923c'>"+String.valueOf(complete)+"</font>");//完成
					}else{
						epvo.setCompleteStatus(String.valueOf(complete));//完成
					}
					epvo.setCreatedDate(DateOperator.parse(listEPP.get(i), "yyyy-MM-dd"));//创建时间
					epvo.setExecPlanProcessing(allList);
					epvoList.add(epvo);
				}
			}else{
				epvoList = new ArrayList<ExecPlanProcessingVO>();
				Struts2Utils.getRequest().setAttribute("epvoList", epvoList);
			}
			Struts2Utils.getRequest().setAttribute("ifFirst", ifFirst);
		return "automaticProcessing";
	}
	
	public String dataMissingAnalysisDetails(){
		@SuppressWarnings("hiding")
		String projectCd = Struts2Utils.getParameter("projectCd");
		String createdDate = Struts2Utils.getParameter("createdDate");
		eppList = execPlanExtendManager.getAllExecPlanProcessing("288",DateOperator.parse("2011-12-27"));
		Struts2Utils.getRequest().setAttribute("eppList",eppList);
		return "myDetails";
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	private String formateDate2YMd(Date date) {
		SimpleDateFormat dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormatter2.format(date);
	}
	
	/**  
	 * 得到本月的第一天  
	 * @return  
	 */  
	public Date getMonthFirstDay() {   
	    Calendar calendar = Calendar.getInstance();   
	    calendar.set(Calendar.DAY_OF_MONTH, calendar   
	            .getActualMinimum(Calendar.DAY_OF_MONTH));   
	    return calendar.getTime();   
	}   
	  
	/**  
	 * 得到本月的最后一天  
	 *   
	 * @return  
	 */  
	public Date getMonthLastDay() {   
	    Calendar calendar = Calendar.getInstance();   
	    calendar.set(Calendar.DAY_OF_MONTH, calendar   
	            .getActualMaximum(Calendar.DAY_OF_MONTH));   
	    return calendar.getTime();   
	} 
	
	@SuppressWarnings("unused")
	private Date getDateYesterday(Date newDate) {
		Calendar calendarIn = Calendar.getInstance();
		calendarIn.setTime(newDate);
		calendarIn.add(Calendar.DAY_OF_MONTH, -1);
		calendarIn.roll(Calendar.MONTH, 0);
		return calendarIn.getTime();
	}
	
	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public OaMeetingRoomRes getModel() {
		return null;
	}

	public List<ExecPlanProcessingVO> getEpvoList() {
		return epvoList;
	}

	public void setEpvoList(List<ExecPlanProcessingVO> epvoList) {
		this.epvoList = epvoList;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getIfFirst() {
		return ifFirst;
	}

	public void setIfFirst(String ifFirst) {
		this.ifFirst = ifFirst;
	}

	public List<ExecPlanProcessing> getEppList() {
		return eppList;
	}

	public void setEppList(List<ExecPlanProcessing> eppList) {
		this.eppList = eppList;
	}

	public boolean isAViewFlag() {
		return aViewFlag;
	}

	public void setAViewFlag(boolean aViewFlag) {
		this.aViewFlag = aViewFlag;
	}

	public List<WsPlasOrg> getProjects() {
		return projects;
	}

	public void setProjects(List<WsPlasOrg> projects) {
		this.projects = projects;
	}

	public String getCurrentUiid() {
		return currentUiid;
	}

	public void setCurrentUiid(String currentUiid) {
		this.currentUiid = currentUiid;
	}
}