package com.hhz.ump.dao.oa;

import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.app.AppParamManager;
import com.hhz.ump.entity.oa.OaMeetingRoomRes;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.JspUtil;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasUser;

@Service
@Transactional
public class OaMeetingRoomResManager extends BaseService<OaMeetingRoomRes, String> {

	/**
	 * 古北国际财富中心
	 */
	public static final String ADDRESS_GB = "GB";
	/**
	 * 虹桥上海城
	 */
	public static final String ADDRESS_SHC = "SHC";

	private static String EMAIL_ADMIN = "email_admin";
	private static String EMAIL_ADMIN_ROLE = "A_MEETING_ROOM_ADMIN";
	private static String EMAIL_ADMIN_ROLE_SHC = "A_MEETING_ROOM_SHC";
	private static String MESSAGE_REMIND = "meeting_room_remind";
	/**
	 * 执行总裁例会参会人员字典Cd
	 */
	private static String CEO_MEETING_PART = "CEO_MEETING_PART";

	@Autowired
	private OaMeetingRoomResDao oaMeetingRoomResDao;
	@Autowired
	private OaEmailBodyManager oaEmailBodyManager;
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	@Autowired
	private AppParamManager appParamManager;

	public void saveOaMeetingRoomRes(OaMeetingRoomRes oaMeetingRoomRes) throws Exception {
		PowerUtils.setEmptyStr2Null(oaMeetingRoomRes);
		String status = oaMeetingRoomRes.getStatus();
		if (status.equals(Constants.OA_MEETING_ROOM_SQ)) {
			if (JspUtil.isBossInMeeting(oaMeetingRoomRes.getCompere(), oaMeetingRoomRes.getParticipators(), "0")) {
				// 给总裁秘书发送短信
				status = Constants.OA_MEETING_ROOM_Z;
				oaMeetingRoomRes.setStatus(status);
				smsNotice(oaMeetingRoomRes, Constants.CHAIRMAN_RES);
			} else if (JspUtil.isBossInMeeting(oaMeetingRoomRes.getCompere(), oaMeetingRoomRes.getParticipators(), "1")) {
				// 给执行总裁秘书发送短信
				status = Constants.OA_MEETING_ROOM_ZZ;
				oaMeetingRoomRes.setStatus(status);
				smsNotice(oaMeetingRoomRes, Constants.CEO_RES);
			} else {
				// 当有新的申请时给会议室管理员发送申请提醒
				smsNotice(oaMeetingRoomRes);
			}
		} else if (status.equals(Constants.OA_MEETING_ROOM_YD)) {
			this.remind(oaMeetingRoomRes, 0,"true");
		} else if (status.equals(Constants.OA_MEETING_ROOM_ZZ) || status.equals(Constants.OA_MEETING_ROOM_Z)) {
			oaMeetingRoomRes.setStatus(Constants.OA_MEETING_ROOM_SQ);
			smsNotice(oaMeetingRoomRes);
		}
		oaMeetingRoomResDao.save(oaMeetingRoomRes);
	}

	public void deleteOaMeetingRoomRes(String id) {
		String hql = "update OaMeetingRoomRes set status = '2' where oaMeetingRoomResId = ? ";
		oaMeetingRoomResDao.batchExecute(hql, id);
	}

	/**
	 * 取消会议
	 * 
	 * @param id
	 */
	public void cancel(String id,String smsFlag) throws Exception {
		String hql = "update OaMeetingRoomRes set status = '3' where oaMeetingRoomResId = ? ";
		oaMeetingRoomResDao.batchExecute(hql, id);
		OaMeetingRoomRes oaMeetingRoomRes = super.getEntity(id);
		this.remind(oaMeetingRoomRes, 1,smsFlag);
	}

	public void editMeeting(OaMeetingRoomRes oaMeetingRoomRes) throws Exception {
		oaMeetingRoomResDao.save(oaMeetingRoomRes);
		this.remind(oaMeetingRoomRes, 2,"true");
	}

	/**
	 * 会议提醒
	 * 
	 * @param oaMeetingRoomRes
	 *            会议记录
	 * @param type
	 *            0 正常会议 1 取消会议 2 更改会议
	 */
	private void remind(OaMeetingRoomRes oaMeetingRoomRes, int type,String smsFlag) throws Exception {
		String[] toUserUiids = oaMeetingRoomRes.getParticipators().split(";");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
		String date = sdf.format(oaMeetingRoomRes.getBeginTime());
		String dateBegin = sdfTime.format(oaMeetingRoomRes.getBeginTime());
		String dateEnd = sdfTime.format(oaMeetingRoomRes.getEndTime());
		String compere = CodeNameUtil.getUserNameByCd(oaMeetingRoomRes.getCompere());
		String recorder = CodeNameUtil.getUserNameByCd(oaMeetingRoomRes.getRecorder());
		String applicant = CodeNameUtil.getUserNameByCd(oaMeetingRoomRes.getApplicant());
		if (recorder == null) {
			recorder = "";
		}
		String attention = oaMeetingRoomRes.getAttention();
		if (attention == null) {
			attention = "";
		}
		String roomName = CodeNameUtil.getDictNameByCds(DictContants.OA_MEETING_ROOM, oaMeetingRoomRes.getRoomId());
		roomName += CodeNameUtil.getDictNameByCds(DictContants.OA_MEETING_ROOM_SHC, oaMeetingRoomRes.getRoomId());

		String meetingTitle = "【会议通知】";
		switch (type) {
		case 0:
			meetingTitle = "【会议通知】";
			break;
		case 1:
			meetingTitle = "【会议取消通知】";
			break;
		case 2:
			meetingTitle = "【会议更改通知】";
			break;
		default:
			break;
		}

		String subject = meetingTitle + oaMeetingRoomRes.getSubject() + "(" + date + ")";

		StringBuffer contentSb = new StringBuffer();

		if (type == 1) {
			contentSb.append("<p>各位好,以下会议已经取消,请相互告知。谢谢！</p>");
		} else if (type == 2) {
			contentSb.append("<p>各位好,原会议时间(地点)已经更改,以下面内容为准,请相互告知。谢谢！</p>");
		}

		contentSb
				.append("<p><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 会议通知</strong></p><table border='1' cellspacing='0' cellpadding='0' width='621'><tbody><tr><td width='121'><p align='center'><strong>会议名称</strong></p></td><td width='501' style='padding-left:5px;'><p>");
		contentSb.append(oaMeetingRoomRes.getSubject());
		contentSb
				.append("</p></td></tr><tr><td width='121'><p align='center'><strong>主持人</strong></p></td><td width='501' style='padding-left:5px;'><p>");
		contentSb.append(compere);
		contentSb
				.append("</p></td></tr><tr><td width='121'><p align='center'><strong>时间</strong></p></td><td width='501' style='padding-left:5px;'><p>");
		contentSb.append(date + "&nbsp;&nbsp;" + dateBegin + "&nbsp;~&nbsp;" + dateEnd);
		contentSb
				.append("</p></td></tr><tr><td width='121'><p align='center'><strong>地点</strong></p></td><td width='501' style='padding-left:5px;'><p>");
		contentSb.append(roomName);
		contentSb
				.append("</p></td></tr><tr><td width='121'><p align='center'><strong>召集人</strong></p></td><td width='501' style='padding-left:5px;'><p>");
		contentSb.append(applicant);
		contentSb
				.append("</p></td></tr><tr><td width='121'><p align='center'><strong>记录人</strong></p></td><td width='501' style='padding-left:5px;'><p>");
		contentSb.append(recorder);
		contentSb
				.append("</p></td></tr><tr><td width='121'><p align='center'><strong>注意事项</strong></p></td><td valign='top' width='501' style='padding-left:5px;'><p>");
		contentSb.append(attention);

		contentSb.append("</p></td></tr></tbody></table>");

		// 发送手机短信提醒
		// SMS sms = SMS.getInstanceCommon();
		String[] mobiles = new String[toUserUiids.length];
		WsPlasUser user = null;
		for (int i = 0; i < toUserUiids.length; i++) {
			user = PlasCache.getUserByUiid(toUserUiids[i]);
			if (user != null && StringUtils.isNotBlank(user.getMobilePhone())) {
				mobiles[i] = user.getMobilePhone();
			}
		}

		StringBuffer msgContent = new StringBuffer(meetingTitle);
		msgContent.append(oaMeetingRoomRes.getSubject());
		msgContent.append("  时间:");
		msgContent.append(date + "  ");
		msgContent.append(dateBegin);
		msgContent.append(" ~ ");
		msgContent.append(dateEnd);
		msgContent.append("  地点:");
		msgContent.append(roomName);
		msgContent.append("。主持人：");
		msgContent.append(compere);
		msgContent.append(" 召集人：");
		msgContent.append(applicant);
		// 发送内部邮件
		oaEmailBodyManager.sendData2Email(subject, contentSb.toString(), EMAIL_ADMIN, "0", toUserUiids);
		if (smsFlag.equals("true")){
			Util.getPlasService().sendCommonSms(meetingTitle, mobiles, msgContent.toString());
		}
	}

	/**
	 * 根据办公地址不一样,发送短信通知不同的管理员
	 * 
	 * @param oaMeetingRoomRes
	 */
	private void smsNotice(OaMeetingRoomRes oaMeetingRoomRes) throws Exception {
		String addrType = oaMeetingRoomRes.getAddrType();
		if (StringUtils.isBlank(addrType) || addrType.equals(ADDRESS_GB)) {
			smsNotice(oaMeetingRoomRes, EMAIL_ADMIN_ROLE);
		} else if (addrType.equals(ADDRESS_SHC)) {
			smsNotice(oaMeetingRoomRes, EMAIL_ADMIN_ROLE_SHC);
		}
	}

	/**
	 * 会议申请短信提醒,依据角色不同,短信的流向不一样
	 * 
	 * @param oaMeetingRoomRes
	 *            会议信息
	 * @param roleCd
	 *            角色代码
	 */
	private void smsNotice(OaMeetingRoomRes oaMeetingRoomRes, String roleCd) throws Exception {
		List<WsPlasUser> users = PlasCache.getUserListByRoleCd(roleCd);
		String creator = oaMeetingRoomRes.getCreator();
		String[] mobiles = new String[users.size()];
		for (int i = 0; i < users.size(); i++) {
			mobiles[i] = users.get(i).getMobilePhone();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date d = new Date();
		if (oaMeetingRoomRes.getCreatedDate() != null) {
			d = oaMeetingRoomRes.getCreatedDate();
		}
		String appDate = sdf.format(d);
		// SMS sms = SMS.getInstanceCommon();
		if (StringUtils.isBlank(creator)) {
			creator = SpringSecurityUtils.getCurrentUiid();
		}
		String title = "[会议申请提醒]" + CodeNameUtil.getUserNameByCd(creator);
		String smsMsg = title + "在" + appDate + "提交了一个会议申请";
		Util.getPlasService().sendCommonSms(title, mobiles, smsMsg);
	}

	/**
	 * 每周一下班后(19:00)自动执行此方法,自动生成周二的【执行总裁例会】，并自动短信通知
	 * 
	 * @Deprecated 每周二凌晨自动执行此方法，自动生成下周二的【执行总裁例会】
	 */
	public void autoCEOMeeting() throws Exception {
		String ipAddress = InetAddress.getLocalHost().getHostAddress();
		if (ipAddress.equals(Constants.PROD_IP)) {
			Date nextTud = DateOperator.addDays(new Date(), 1);
			if (!Util.isHoliday(nextTud)&& StringUtils.equals(appParamManager.getCeoMeetingNotify(),"1")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String dateStr = sdf.format(nextTud);
				Date begin = sdf1.parse(dateStr + " 08:50");
				Date end = sdf1.parse(dateStr + " 09:30");
				OaMeetingRoomRes meetingRoomRes = new OaMeetingRoomRes();
				meetingRoomRes.setApplicant("admin");
				meetingRoomRes.setBeginTime(begin);
				meetingRoomRes.setEndTime(end);
				meetingRoomRes.setCompere("xuhf");
				meetingRoomRes.setCreator("admin");
				Map<String, String> mapUser = appDictTypeManager
						.getDictDataByTypeCd(CEO_MEETING_PART);
				String participators = addSplit(mapUser.keySet(), ";");
				meetingRoomRes.setParticipators(participators);
				meetingRoomRes.setPartNum(11);
				meetingRoomRes.setRecorder("wubc");
				meetingRoomRes.setRoomId("5");
				meetingRoomRes.setStatus("1");
				meetingRoomRes.setAddrType("GB");
				meetingRoomRes.setSubject("执行总裁例会");
				PowerUtils.setEmptyStr2Null(meetingRoomRes);
				getDao().save(meetingRoomRes);
				remind(meetingRoomRes, 0, "true");
			}
		}
	}

	/**
	 * 数组转化为字符串
	 * 
	 * @param a
	 * @param split
	 * @return
	 */
	public static String addSplit(Set<String> a, String split) {
		StringBuffer sb = new StringBuffer();
		for (String str : a) {
			if (StringUtils.isNotBlank(str)) {
				sb.append(str).append(split);
			}
		}
		return sb.toString();
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<OaMeetingRoomRes, String> getDao() {
		return oaMeetingRoomResDao;
	}

	/**
	 * 会议前15分钟自动短信提醒
	 */
	public void messageRemind() throws Exception {
		Map<String, String> map = appDictTypeManager.getDictDataByTypeCd(DictContants.QUARTZ_TYPE_CD);
		if ("0".equals(map.get(MESSAGE_REMIND)))
			return;
		Date now = new Date();
		Date next10m = DateUtils.addMinutes(now, 10);
		Date next15m = DateUtils.addMinutes(now, 15);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("GED_updatedDate", next10m));
		filters.add(new PropertyFilter("LTD_updatedDate", next15m));
		List<OaMeetingRoomRes> list = super.find(filters);

		for (OaMeetingRoomRes res : list) {

			String[] toUserUiids = res.getParticipators().split(";");

			String roomName = CodeNameUtil.getDictNameByCds(DictContants.OA_MEETING_ROOM, res.getRoomId());

			// 发送手机短信提醒
			// SMS sms = SMS.getInstanceCommon();
			String[] mobiles = new String[toUserUiids.length];

			WsPlasUser user = null;
			for (int i = 0; i < toUserUiids.length; i++) {
				user = PlasCache.getUserByUiid(toUserUiids[i]);
				if (user != null && StringUtils.isNotBlank(user.getMobilePhone())) {
					mobiles[i] = user.getMobilePhone();
				}
			}
			String titleMsg = "[会议前15分钟提醒]" + roomName;
			StringBuffer msg = new StringBuffer("在");
			msg.append(roomName);
			msg.append("的会议将于10分钟后开始，请准时出席。会议主题：");
			msg.append(res.getSubject());
			Util.getPlasService().sendCommonSms(titleMsg, mobiles, msg.toString());
		}
	}
	
	/**
	 * 
	 * @param planTypeCd
	 * @param nowPointLevel
	 * @return
	 */
	public List<OaMeetingRoomRes> getCurrentAllMeetingRoom(String currentUiid) {
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newDate = sdf.format(DateOperator.getDateNow());
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hqlBuf = new StringBuffer().append(" from OaMeetingRoomRes o ");
		hqlBuf.append(" where (o.compere=:currentUiid  or o.recorder=:currentUiid  or o.creator=:currentUiid or o.participators Like '%"+currentUiid+"%')" +
				" and o.status = '1' and o.endTime>=:newDate and o.endTime is not null" );
		params.put("currentUiid", currentUiid);
		try {
			params.put("newDate",sdf.parse(newDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		hqlBuf.append(" order by o.beginTime asc");
		return getDao().find(hqlBuf.toString(), params);
	}

}
