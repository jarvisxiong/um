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

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.oa.OaMeetingRoomResManager;
import com.hhz.ump.entity.oa.OaMeetingRoomRes;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DictContants;

@Namespace("/oa")
@Results( {
 @Result(name = CrudActionSupport.RELOAD, location = "oa-meeting-room-res!main.action", type = "redirect")
 })
public class OaMeetingRoomResAction extends CrudActionSupport<OaMeetingRoomRes> {
	
	@Autowired
	private OaMeetingRoomResManager resManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	private OaMeetingRoomRes entity;

	private Map<String, String> roomMap;
	public Map<String, List<OaMeetingRoomRes>> resMap;

	private String currDay;
	private String prevDay;
	private String nextDay;

	private String[] roomIds;
	
	/**
	 * 上班地点类型<br/>
	 * gb    shc
	 */
	private String addrType;
	private String flag;
	private String objId;

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getCurrDay() {
		return currDay;
	}

	public void setCurrDay(String currDay) {
		this.currDay = currDay;
	}

	@Override
	public String delete() throws Exception {
		resManager.deleteOaMeetingRoomRes(getId());
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String input() throws Exception {
		roomMap = this.getMapMeetingRoom();
		roomMap.remove("");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		currDay = sdf.format(new Date());

		if (entity != null && entity.getRoomId() != null) {
			String roomId = entity.getRoomId();
			roomIds = roomId.split(",");
		}

		return INPUT;
	}

	public String main() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		currDay = sdf.format(new Date());
		objId = Struts2Utils.getParameter("id");
		return "main";
	}

	/**
	 * 作废会议室申请
	 * 
	 * @return
	 * @throws Exception
	 */
	public String invalid() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		currDay = sdf.format(new Date());
		return null;
	}

	public String myres() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		page.setPageSize(5);
		if (!page.isOrderBySetted()) {
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		}
		PropertyFilter propertyFilter = new PropertyFilter("EQS_creator", SpringSecurityUtils.getCurrentUiid());
		filters.add(propertyFilter);
		propertyFilter = new PropertyFilter("EQS_addrType", addrType);
		filters.add(propertyFilter);
		page = resManager.findPage(page, filters);
		return "myres";
	}

	/**
	 * 搜索预定申请列表
	 */
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());

		page.setPageSize(1000);// 不需要分页

		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		}
		PropertyFilter propertyFilter = new PropertyFilter("EQS_status", Constants.OA_MEETING_ROOM_SQ);
		filters.add(propertyFilter);
		propertyFilter = new PropertyFilter("EQS_addrType", addrType);
		filters.add(propertyFilter);

		String applyDate = Struts2Utils.getParameter("applyDate");
		if (StringUtils.isNotEmpty(applyDate)) {
			propertyFilter = new PropertyFilter("GED_createdDate", applyDate);
			filters.add(propertyFilter);
			propertyFilter = new PropertyFilter("LTD_createdDate", applyDate);
			filters.add(propertyFilter);
		}

		page = resManager.findPage(page, filters);
		return SUCCESS;
	}

	/**
	 * 搜索已预订会议室列表
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
		propertyFilter = new PropertyFilter("EQS_addrType", addrType);
		filters.add(propertyFilter);

		page = resManager.findPage(page, filters);
		roomMap = this.getMapMeetingRoom();
		resMap = new LinkedHashMap<String, List<OaMeetingRoomRes>>();
		for (String key : roomMap.keySet()) {
			if (StringUtils.isNotEmpty(key)) {
				resMap.put(key, new ArrayList<OaMeetingRoomRes>());
			}
		}

		for (OaMeetingRoomRes res : page.getResult()) {
			String key = res.getRoomId();
			String[] keys = key.split(",");
			if (keys.length > 1) {
				for (String str : keys) {
					resMap.get(str).add(res);
				}
			} else {
				List<OaMeetingRoomRes> list=resMap.get(key);
				if (list!=null){
					list.add(res);
				}else{
					List<OaMeetingRoomRes> roomRes=new ArrayList<OaMeetingRoomRes>();
					roomRes.add(res);
					resMap.put(key, roomRes);
				}
			}
		}
		String simpleFlg = Struts2Utils.getParameter("simple");

		if (StringUtils.isNotEmpty(simpleFlg))
			return "simple";

		return "res";
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = resManager.getEntity(getId());
		} else {
			entity = new OaMeetingRoomRes();
		}

	}

	public String assign() throws Exception {
		roomMap = this.getMapMeetingRoom();
		roomMap.remove("");
		String roomId = entity.getRoomId();
		roomIds = roomId.split(",");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		currDay = sdf.format(entity.getBeginTime());
		return "assign";
	}
	
	public String status() throws Exception{
		Struts2Utils.renderText(entity.getStatus());
		return null;
	}

	public String look() throws Exception {
		roomMap = this.getMapMeetingRoom();
		roomMap.remove("");
		String roomId = entity.getRoomId();
		roomIds = roomId.split(",");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		currDay = sdf.format(entity.getBeginTime());
		if(Struts2Utils.getParameter("flag")!=null){
			if(Struts2Utils.getParameter("flag").equals("true")){
				flag = "true";
			}else{
				flag = "false";
			}
		}
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
			StringBuffer roomId = new StringBuffer();
			for (int i = 0 ;i<roomIds.length ; i++) {
				if (i != 0) {
					roomId.append(",");
				}
				roomId.append(roomIds[i]);
			}

			entity.setRoomId(roomId.toString());
			entity.setBeginTime(beginTime);
			entity.setEndTime(endTime);

			if (StringUtils.isEmpty(entity.getApplicant())) {
				entity.setApplicant(SpringSecurityUtils.getCurrentUiid());
			}

			String editFlg = Struts2Utils.getParameter("editFlg");
			if (StringUtils.isNotEmpty(editFlg)) {
				resManager.editMeeting(entity);
			} else {
				resManager.saveOaMeetingRoomRes(entity);
			}

			Struts2Utils.renderText("success");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error:",e);
			Struts2Utils.renderText("error");
		}
		return null;
	}

	public String cancel() throws Exception {
		String smsFlag = Struts2Utils.getParameter("smsFlag");
		resManager.cancel(getId(),smsFlag);
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

	public OaMeetingRoomRes getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	/**
	 * 获取会议室列表
	 * @return
	 */
	public Map<String, String> getMapMeetingRoom() {
		String dictTypeCd = "";
		if(OaMeetingRoomResManager.ADDRESS_GB.equals(addrType)){
			dictTypeCd = DictContants.OA_MEETING_ROOM;
		}
		else if(OaMeetingRoomResManager.ADDRESS_SHC.equals(addrType)){
			dictTypeCd = DictContants.OA_MEETING_ROOM_SHC;
		}
			
		return appDictTypeManager.getDictDataByTypeCd(dictTypeCd);
	}

	public Map<String, String> getRoomMap() {
		return roomMap;
	}

	public void setRoomMap(Map<String, String> roomMap) {
		this.roomMap = roomMap;
	}

	public String[] getRoomIds() {
		return roomIds;
	}

	public void setRoomIds(String[] roomIds) {
		this.roomIds = roomIds;
	}

	public Map<String, List<OaMeetingRoomRes>> getResMap() {
		return resMap;
	}

	public String getPrevDay() {
		return prevDay;
	}

	public String getNextDay() {
		return nextDay;
	}

	public void setResMap(Map<String, List<OaMeetingRoomRes>> resMap) {
		this.resMap = resMap;
	}

	public void setPrevDay(String prevDay) {
		this.prevDay = prevDay;
	}

	public void setNextDay(String nextDay) {
		this.nextDay = nextDay;
	}

	public String getAddrType() {
		return addrType;
	}

	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}
	

}
