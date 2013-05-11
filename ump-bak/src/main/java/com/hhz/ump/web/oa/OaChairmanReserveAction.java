package com.hhz.ump.web.oa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.oa.OaChairmanReserveManager;
import com.hhz.ump.dao.oa.OaMeetingRoomResManager;
import com.hhz.ump.entity.oa.OaChairmanReserve;
import com.hhz.ump.entity.oa.OaMeetingRoomRes;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.JspUtil;

@Namespace("/oa")
@Results( {
 @Result(name = CrudActionSupport.RELOAD, location = "oa-meeting-room-res!main.action", type = "redirect")
 })
public class OaChairmanReserveAction extends CrudActionSupport<OaChairmanReserve> {

	private static final long serialVersionUID = 8975008039520443294L;
	@Autowired
	private OaChairmanReserveManager oaChairmanReserveManager;
	@Autowired
	private OaMeetingRoomResManager oaMeetingRoomResManager;

	private OaChairmanReserve entity;

	private Map<String, String> chairmanTypeMap;
	
	public Map<String, List<CeoCalendar>> resMap;
	
	public Page<OaMeetingRoomRes> pageMeetingRoom = new Page<OaMeetingRoomRes>(1000);
	
	public static String CHAIRMAN_ID = "0";
	public static String CEO_ID = "1";

	private String currDay;
	private String flag;
	private String objId;
	private String prevDay;
	private String nextDay;

	public String getCurrDay() {
		return currDay;
	}

	public void setCurrDay(String currDay) {
		this.currDay = currDay;
	}

	/**
	 * 作废预约申请
	 */
	@Override
	public String delete() throws Exception {
		oaChairmanReserveManager.deleteOaChairmanReserve(getId());
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		currDay = sdf.format(new Date());
		return INPUT;
	}

	public String main() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		currDay = sdf.format(new Date());
		objId = Struts2Utils.getParameter("id");
		return "main";
	}


	/**
	 * 我的申请
	 * @return
	 * @throws Exception
	 */
	public String myres() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		page.setPageSize(5);
		if (!page.isOrderBySetted()) {
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		}
		PropertyFilter propertyFilter = new PropertyFilter("EQS_creator", SpringSecurityUtils.getCurrentUiid());
		filters.add(propertyFilter);
		page = oaChairmanReserveManager.findPage(page, filters);
		chairmanTypeMap = this.getMapChairmanType();
		return "myres";
	}

	/**
	 * 搜索总裁预约申请列表
	 */
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		List<PropertyFilter> meetingRoomFilters = new ArrayList<PropertyFilter>();

		page.setPageSize(1000);// 不需要分页

		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		}
		//处于申请状态的记录
		PropertyFilter propertyFilter = new PropertyFilter("EQS_status", Constants.OA_MEETING_ROOM_SQ);
		filters.add(propertyFilter);
		
		String applyDate = Struts2Utils.getParameter("applyDate");
		if (StringUtils.isNotEmpty(applyDate)) {
			propertyFilter = new PropertyFilter("GED_createdDate", applyDate);
			filters.add(propertyFilter);
			meetingRoomFilters.add(propertyFilter);
			propertyFilter = new PropertyFilter("LTD_createdDate", applyDate);
			filters.add(propertyFilter);
			meetingRoomFilters.add(propertyFilter);
		}
		
		String chairmanType = "";
		if(SpringSecurityUtils.hasRole(Constants.CHAIRMAN_RES)){
			chairmanType = "0";//总裁预约申请记录
			propertyFilter = new PropertyFilter("EQS_status", Constants.OA_MEETING_ROOM_Z);
			meetingRoomFilters.add(propertyFilter);
		} else if (SpringSecurityUtils.hasRole(Constants.CEO_RES)) {
			chairmanType = "1";//执行总裁预约申请记录
			propertyFilter = new PropertyFilter("EQS_status", Constants.OA_MEETING_ROOM_ZZ);
			meetingRoomFilters.add(propertyFilter);
		}
		
		propertyFilter = new PropertyFilter("EQS_chairmanType", chairmanType);
		filters.add(propertyFilter);

		page = oaChairmanReserveManager.findPage(page, filters);
		pageMeetingRoom = oaMeetingRoomResManager.findPage(pageMeetingRoom, meetingRoomFilters);
		return SUCCESS;
	}

	/**
	 * 搜索已预约列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String res() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());

		page.setPageSize(1000);// 不需要分页

		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("beginTime");
			page.setOrder(Page.ASC);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// 默认取当天
		if (StringUtils.isEmpty(currDay)) {
			currDay = sdf.format(new Date());
		}

		nextDay = sdf.format(DateUtils.addDays(sdf.parse(currDay), 1));
		prevDay = sdf.format(DateUtils.addDays(sdf.parse(currDay), -1));

		PropertyFilter propertyFilter = new PropertyFilter("GED_beginTime", currDay);
		filters.add(propertyFilter);
		propertyFilter = new PropertyFilter("LTD_beginTime", currDay);
		filters.add(propertyFilter);
		propertyFilter = new PropertyFilter("EQS_status", Constants.OA_MEETING_ROOM_YD);
		filters.add(propertyFilter);

		//搜索当天总裁/执行总裁预约情况
		page = oaChairmanReserveManager.findPage(page, filters);
		chairmanTypeMap = this.getMapChairmanType();
		//搜索当天会议室预定情况,然后过滤出总裁/执行总裁参与的会议
		pageMeetingRoom = oaMeetingRoomResManager.findPage(pageMeetingRoom, filters);
		
		resMap = new LinkedHashMap<String, List<CeoCalendar>>();
		for (String key : chairmanTypeMap.keySet()) {
			if (StringUtils.isNotEmpty(key)) {
				resMap.put(key, new ArrayList<CeoCalendar>());
			}
		}

		for (OaChairmanReserve res : page.getResult()) {
			String key = res.getChairmanType();
			CeoCalendar ceoCalendar = new CeoCalendar();
			ceoCalendar.setDataId(res.getOaChairmanReserveId());
			ceoCalendar.setApplicant(res.getApplicant());
			ceoCalendar.setBeginTime(res.getBeginTime());
			ceoCalendar.setEndTime(res.getEndTime());
			ceoCalendar.setSubject(res.getSubject());
			ceoCalendar.setParticipators(res.getParticipators());
			ceoCalendar.setSourceType("0");//源自总裁预约
			resMap.get(key).add(ceoCalendar);
			
		}
		
		for(OaMeetingRoomRes roomRes : pageMeetingRoom.getResult()){
			CeoCalendar ceoCalendar = new CeoCalendar();
			ceoCalendar.setDataId(roomRes.getOaMeetingRoomResId());
			ceoCalendar.setApplicant(roomRes.getApplicant());
			ceoCalendar.setBeginTime(roomRes.getBeginTime());
			ceoCalendar.setEndTime(roomRes.getEndTime());
			ceoCalendar.setSubject(roomRes.getSubject());
			ceoCalendar.setParticipators(roomRes.getParticipators());
			ceoCalendar.setEncryptFlg(roomRes.getEncryptFlg());
			ceoCalendar.setSourceType("1");//源自会议室预定
			
			//抓取总裁今天参与的会议
			if(JspUtil.isBossInMeeting(roomRes.getCompere(),roomRes.getParticipators(),CHAIRMAN_ID)){
				resMap.get(CHAIRMAN_ID).add(ceoCalendar);
			}
			//抓取执行总裁今天参与的会议
			if(JspUtil.isBossInMeeting(roomRes.getCompere(),roomRes.getParticipators(),CEO_ID)){
				resMap.get(CEO_ID).add(ceoCalendar);
			}
		}
		
		CollectionHelper.sortList(resMap.get(CHAIRMAN_ID),"beginTime");
		CollectionHelper.sortList(resMap.get(CEO_ID),"beginTime");
		
		String simpleFlg = Struts2Utils.getParameter("simple");

		if (StringUtils.isNotEmpty(simpleFlg))
			return "simple";

		return "res";
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = oaChairmanReserveManager.getEntity(getId());
		} else {
			entity = new OaChairmanReserve();
		}

	}

	/**
	 * 审核预约申请
	 * @return
	 * @throws Exception
	 */
	public String assign() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		currDay = sdf.format(entity.getBeginTime());
		return "assign";
	}
	
	public String status() throws Exception {
		Struts2Utils.renderText(entity.getStatus());
		return null;
	}

	/**
	 * 查看预约明细
	 * @return
	 * @throws Exception
	 */
	public String look() throws Exception {
		chairmanTypeMap = this.getMapChairmanType();
		chairmanTypeMap.remove("");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		currDay = sdf.format(entity.getBeginTime());
		return "look";
	}

	@Override
	public String save() throws Exception {
		try {
			String meetingDate = Struts2Utils.getParameter("currDay");
			String meetingBegin = Struts2Utils.getParameter("meetingBegin");
			String meetingEnd = Struts2Utils.getParameter("meetingEnd");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date beginTime = sdf.parse(meetingDate + " " + meetingBegin);
			Date endTime = sdf.parse(meetingDate + " " + meetingEnd);
			
			entity.setBeginTime(beginTime);
			entity.setEndTime(endTime);

			String editFlg = Struts2Utils.getParameter("editFlg");
			if (StringUtils.isNotEmpty(editFlg)) {
				//更改预约信息
				oaChairmanReserveManager.editMeeting(entity);
			} else {
				//确认预约信息
				oaChairmanReserveManager.saveOaChairmanReserve(entity);
			}

			Struts2Utils.renderText("success");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error:",e);
			Struts2Utils.renderText("error");
		}
		return null;
	}


	/**
	 * 取消预约
	 * @return
	 * @throws Exception
	 */
	public String cancel() throws Exception {
		oaChairmanReserveManager.cancel(getId());
		return null;
	}

	public void prepareAssign() throws Exception {
		prepareModel();
	}
	public void prepareStatus() throws Exception {
		prepareModel();
	}

	public void prepareLook() throws Exception {
		prepareModel();
	}

	@Override
	public OaChairmanReserve getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public Map<String, String> getMapChairmanType() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(CHAIRMAN_ID, "总裁");
		map.put(CEO_ID, "执行总裁");
		return map;
	}

	public Map<String, String> getChairmanTypeMap() {
		return chairmanTypeMap;
	}

	public Map<String, List<CeoCalendar>> getResMap() {
		return resMap;
	}

	public void setChairmanTypeMap(Map<String, String> chairmanTypeMap) {
		this.chairmanTypeMap = chairmanTypeMap;
	}

	public void setResMap(Map<String, List<CeoCalendar>> resMap) {
		this.resMap = resMap;
	}

	public String getPrevDay() {
		return prevDay;
	}

	public String getNextDay() {
		return nextDay;
	}

	public void setPrevDay(String prevDay) {
		this.prevDay = prevDay;
	}

	public void setNextDay(String nextDay) {
		this.nextDay = nextDay;
	}

	public Page<OaMeetingRoomRes> getPageMeetingRoom() {
		return pageMeetingRoom;
	}

	public void setPageMeetingRoom(Page<OaMeetingRoomRes> pageMeetingRoom) {
		this.pageMeetingRoom = pageMeetingRoom;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @return the objId
	 */
	public String getObjId() {
		return objId;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @param objId the objId to set
	 */
	public void setObjId(String objId) {
		this.objId = objId;
	}


}
