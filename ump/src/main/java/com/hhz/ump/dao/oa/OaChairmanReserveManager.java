package com.hhz.ump.dao.oa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.entity.oa.OaChairmanReserve;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasUser;

@Service
@Transactional
public class OaChairmanReserveManager extends BaseService<OaChairmanReserve, String> {
	@Autowired
	private OaChairmanReserveDao oaChairmanReserveDao;
	
	@Autowired
	private OaEmailBodyManager oaEmailBodyManager;
	
	private static String EMAIL_ADMIN = "email_admin";

	public void saveOaChairmanReserve(OaChairmanReserve oaChairmanReserve) throws Exception {
		PowerUtils.setEmptyStr2Null(oaChairmanReserve);
		oaChairmanReserveDao.save(oaChairmanReserve);
		String status = oaChairmanReserve.getStatus();
		if (status.equals("1")) {
			//修改时发送约会修改提醒
			this.remind(oaChairmanReserve, 0);
		} else if (status.equals("0")) {
			// 当有新的申请时给总裁秘书(管理员)发送申请提醒
			String type = oaChairmanReserve.getChairmanType();
			String roleCd = Constants.CHAIRMAN_RES;
			String smsTitle = "【总裁";
			String strRoom = "总裁办公室";
			if(type.equals("1")){
				roleCd = Constants.CEO_RES;
				smsTitle = "【执行总裁";
				strRoom = "执行总裁办公室";
			}
			List<WsPlasUser> users = PlasCache.getUserListByRoleCd(roleCd);
			String[] mobiles = new String[users.size()];
			for (int i = 0; i < users.size(); i++) {
				mobiles[i] = users.get(i).getMobilePhone();
			}
			String creator = oaChairmanReserve.getCreator();
			if(StringUtils.isBlank(creator)){
				creator = SpringSecurityUtils.getCurrentUiid();
			}
			
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Util.getPlasService().sendCommonSms(
					smsTitle,mobiles,smsTitle+"预约通知】 "
					+ CodeNameUtil.getUserNameByCd(creator) + "提交了预约申请，地点在"+strRoom);
		}
	}

	public void deleteOaChairmanReserve(String id) {
		String hql = "update OaChairmanReserve set status = '2' where oaChairmanReserveId = ? ";
		oaChairmanReserveDao.batchExecute(hql, id);
	}
	
	@Override
	public HibernateDao<OaChairmanReserve, String> getDao() {
		return oaChairmanReserveDao;
	}
	
	/**
	 * 取消会议
	 * 
	 * @param id
	 */
	public void cancel(String id)throws Exception  {
		String hql = "update OaChairmanReserve set status = '3' where oaChairmanReserveId = ? ";
		oaChairmanReserveDao.batchExecute(hql, id);
		OaChairmanReserve oaChairmanReserve = super.getEntity(id);
		this.remind(oaChairmanReserve, 1);
	}
	
	public void editMeeting(OaChairmanReserve oaChairmanReserve)throws Exception  {
		oaChairmanReserveDao.save(oaChairmanReserve);
		this.remind(oaChairmanReserve, 2);
	}
	
	/**
	 * 预约提醒
	 * 
	 * @param oaMeetingRoomRes
	 *            会议记录
	 * @param type
	 *            0 正常会议 1 取消会议 2 更改会议
	 */
	private void remind(OaChairmanReserve oaChairmanReserve, int type) throws Exception {
		String[] toUserUiids = oaChairmanReserve.getParticipators().split(";");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
		String date = sdf.format(oaChairmanReserve.getBeginTime());
		String dateBegin = sdfTime.format(oaChairmanReserve.getBeginTime());
		String dateEnd = sdfTime.format(oaChairmanReserve.getEndTime());
		String chairmanType = oaChairmanReserve.getChairmanType();
		String attention = oaChairmanReserve.getAttention();
		String compere = CodeNameUtil.getUserNameByCd(oaChairmanReserve.getCompere());
		String recorder = CodeNameUtil.getUserNameByCd(oaChairmanReserve.getRecorder());
		String applicant = CodeNameUtil.getUserNameByCd(oaChairmanReserve.getApplicant());
		if (attention == null) {
			attention = "";
		}

		String meetingTitle = "";
		String strRoom = "";
		if ("0".equals(chairmanType)) {
			meetingTitle = "【总裁";
			strRoom = "总裁办公室";
		} else {
			meetingTitle = "【执行总裁";
			strRoom = "执行总裁办公室";
		}
		String typeStr = "";
		switch (type) {
		case 0:
			meetingTitle += "会议通知】";
			typeStr = " ";
			break;
		case 1:
			meetingTitle += "会议取消通知】";
			typeStr = "取消";
			break;
		case 2:
			meetingTitle += "会议更改通知】";
			typeStr = "更改至";
			break;
		default:
			meetingTitle = "";
			break;
		}
		
		//发送内部邮件
		String subject = meetingTitle + oaChairmanReserve.getSubject() + "(" + date + ")";
		StringBuffer contentSb = new StringBuffer();
		if (type == 1) {
			contentSb.append("<p>各位好,以下会议已经取消,请相互告知。谢谢！</p>");
		} else if (type == 2) {
			contentSb.append("<p>各位好,原会议时间(地点)已经更改,以下面内容为准,请相互告知。谢谢！</p>");
		}

		contentSb
				.append("<p><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 会议通知</strong></p><table border='1' cellspacing='0' cellpadding='0' width='621'><tbody><tr><td width='121'><p align='center'><strong>会议名称</strong></p></td><td width='501' style='padding-left:5px;'><p>");
		contentSb.append(oaChairmanReserve.getSubject());
		contentSb
				.append("</p></td></tr><tr><td width='121'><p align='center'><strong>主持人</strong></p></td><td width='501' style='padding-left:5px;'><p>");
		contentSb.append(compere);
		contentSb
				.append("</p></td></tr><tr><td width='121'><p align='center'><strong>时间</strong></p></td><td width='501' style='padding-left:5px;'><p>");
		contentSb.append(date + "&nbsp;&nbsp;" + dateBegin + "&nbsp;~&nbsp;" + dateEnd);
		contentSb
				.append("</p></td></tr><tr><td width='121'><p align='center'><strong>地点</strong></p></td><td width='501' style='padding-left:5px;'><p>");
		if ("0".equals(chairmanType)) {
			contentSb.append("总裁办公室");
		} else {
			contentSb.append("执行总裁办公室");
		}
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
		// 发送内部邮件
		oaEmailBodyManager.sendData2Email(subject, contentSb.toString(), EMAIL_ADMIN, "0", toUserUiids);

		

		// 发送手机短信提醒
		String[] mobiles = new String[toUserUiids.length];
		
		WsPlasUser user = null;
		for (int i = 0; i < toUserUiids.length; i++) {
			user = PlasCache.getUserByUiid(toUserUiids[i]);
			
			if(user != null){
				//总裁和执行总裁不发送短信
				if(user.getUserName().equals(Constants.CHAIRMAN)||user.getUserName().equals(Constants.CEO)){
					continue;
				}
				if (StringUtils.isNotBlank(user.getMobilePhone())) {
					mobiles[i] = user.getMobilePhone();
				}
			}
		}

		StringBuffer msgContent = new StringBuffer();
		msgContent.append(meetingTitle);
		msgContent.append(oaChairmanReserve.getSubject());
		msgContent.append(typeStr);
		msgContent.append("  时间:");
		msgContent.append(date + "  ");
		msgContent.append(dateBegin);
		msgContent.append(" ~ ");
		msgContent.append(dateEnd);
		msgContent.append("。主持人：");
		msgContent.append(compere);
		msgContent.append(" 召集人：");
		msgContent.append(applicant);
		msgContent.append(" 地点："+strRoom);
		Util.getPlasService().sendCommonSms(meetingTitle,mobiles,msgContent.toString());
	}
	
	/**
	 * 获取当前最新会议
	 * @param currentUiid
	 * @return
	 */
	public List<OaChairmanReserve> getCurrentAllMeetingRoom(String currentUiid){
		SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String newDate = sdf.format(DateOperator.getDateNow());
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer hqlBuf = new StringBuffer().append(" from OaChairmanReserve o ");
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

