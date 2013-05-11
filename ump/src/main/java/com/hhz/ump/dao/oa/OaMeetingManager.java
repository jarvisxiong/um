package com.hhz.ump.dao.oa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.UserPool;
import com.hhz.ump.entity.oa.OaMeeting;
import com.hhz.ump.entity.oa.OaMeetingComment;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.Util;

@Service
@Transactional
public class OaMeetingManager extends BaseService<OaMeeting, String> {
    
    	private static final String MAIL_ADMIN = "email_admin";
    
	@Autowired
	private OaMeetingDao oaMeetingDao;
	
	@Autowired
	private OaEmailBodyManager oaEmailBodyManager;
	
	@Autowired
	private OaAllAttentionManager oaAllAttentionManager;

	
	public void saveOaMeeting(OaMeeting oaMeeting) {
		PowerUtils.setEmptyStr2Null(oaMeeting);
		boolean ifAdd = false;
		if(null==oaMeeting.getOaMeetingId() || "".equalsIgnoreCase(oaMeeting.getOaMeetingId())){
			ifAdd = true;
		}
		oaMeetingDao.save(oaMeeting);
		if(ifAdd){
			try{
				String[] strs = oaMeeting.getResponsiblePerson().split(";");
				for(int i=0;null!=strs&&i<strs.length;i++){
					if(null!=strs[i] && !strs[i].equalsIgnoreCase("")){
						oaAllAttentionManager.saveByParamNoRender("oaMeeting",
								oaMeeting.getOaMeetingId(), oaMeeting.getRecordVersion(),strs[i]);
					}
				}
			}catch(Exception e){}
			try{
				String[] strs = oaMeeting.getCoordinatePerson().split(";");
				for(int i=0;null!=strs&&i<strs.length;i++){
					if(null!=strs[i] && !strs[i].equalsIgnoreCase("")){
						oaAllAttentionManager.saveByParamNoRender("oaMeeting",
								oaMeeting.getOaMeetingId(), oaMeeting.getRecordVersion(),strs[i]);
					}
				}
			}catch(Exception e){}
			try{
				oaAllAttentionManager.saveByParamNoRender("oaMeeting",
						oaMeeting.getOaMeetingId(), oaMeeting.getRecordVersion(),"xuhf");
			}catch(Exception e){}
			try{
				oaAllAttentionManager.saveByParamNoRender("oaMeeting",
						oaMeeting.getOaMeetingId(), oaMeeting.getRecordVersion(),"wubc");
			}catch(Exception e){}
			try{
				oaAllAttentionManager.saveByParamNoRender("oaMeeting",
						oaMeeting.getOaMeetingId(), oaMeeting.getRecordVersion(),"chenmx1");
			}catch(Exception e){}
			try{
				oaAllAttentionManager.saveByParamNoRender("oaMeeting",
						oaMeeting.getOaMeetingId(), oaMeeting.getRecordVersion(),"tianyuan");
			}catch(Exception e){}
		}
	}

	public void deleteOaMeeting(String id) {
		oaMeetingDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<OaMeeting, String> getDao() {
		return oaMeetingDao;
	}
	
	/**
	 * 定时检查数据库中的记录，给目标日期为第二天切处于非完成状态的会议记录的人员发信
	 */
	public void daylyRemind() {
		Date date = DateOperator.addDays(new Date(), 1);
		if (!Util.isHoliday(date)) {
			List<PropertyFilter> filters = new ArrayList<PropertyFilter>();

			filters.add(new PropertyFilter("EQS_isDeleted", "0"));
			filters.add(new PropertyFilter("EQS_hiddenFlg", "0"));

			filters.add(new PropertyFilter("EQD_targetDate", date));

			List<OaMeeting> results = this.find(filters);
			for (OaMeeting m : results) {
				if (DictContants.OA_MEETING_STATUS_COMPLETE.equals(m.getStatus())) {
					continue;
				}

				try {
					sendRemindEmail(m);
				} catch (Exception e) {
					throw new RuntimeException("发送邮件失败--" + m.getWaterNumber(), e);
				}
			}
		}
	}
	
	/**
	 * 发送提醒邮件
	 * @param meeting
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public boolean sendRemindEmail(OaMeeting meeting) throws SQLException, IOException {
	    if (meeting == null)
			throw new IllegalArgumentException("会议记录不能为空");
	    
	    Set<String> toUiids = new HashSet<String>();
	    
	    // 把负责人和配合人列入收信人列表
	    String resPerson = meeting.getResponsiblePerson();
	    String[] ids = null;
	    if (StringUtils.isNotBlank(resPerson)) {
		ids = resPerson.split(";");
		for (String id : ids) {
		    if (StringUtils.isNotBlank(id)) {
			toUiids.add(id);
		    }
		}
	    }
	    
	    String corPerson = meeting.getCoordinatePerson();
	    if (StringUtils.isNotBlank(corPerson)) {
		ids = corPerson.split(";");
		for (String id : ids) {
		    if (StringUtils.isNotBlank(id)) {
			toUiids.add(id);
		    }
		}
	    }
	    
	    if (toUiids.size() > 0) {
		String content = Util.clob2String(meeting.getBusiness());
		
		String subject = "【指令单提醒】-" + meeting.getWaterNumber() + ":" + content.trim();
		
		oaEmailBodyManager.sendData2Email(subject, buildMailBody(meeting, content), MAIL_ADMIN, "0", toUiids.toArray(new String[toUiids.size()]));
	    }
	    
	    return true;
	}

	/**
	 * 构造邮件体
	 * @param meeting
	 * @param content
	 * @return
	 * @throws SQLException 
	 * @throws IOException 
	 */
	private String buildMailBody(OaMeeting meeting, String content) throws SQLException, IOException {
	    StringBuilder mailBody = new StringBuilder();
	    
	    mailBody.append("<div style='font-size: 14px; line-height: 25px'><a href='#' style='text-decoration: underline; color: #000;' onclick='parent.openTask(\"\", \"" 
		    + meeting.getOaMeetingId() + "\", \"\", \"ceomeeting\"); return false;'>点击查看详细信息>></a></div>");
	    
	    mailBody.append("<div style='font-size: 14px; line-height: 25px'>任务内容：" + content + "</div>");
	    mailBody.append("<div style='font-size: 14px; line-height: 25px'>目标时间：" + DateOperator.formatDate(meeting.getTargetDate(), "yyyy-MM-dd") + "</div>");
	    
	    mailBody.append("<div style='font-size: 14px;'>回复：");
	    
	    List<OaMeetingComment> comments = meeting.getOaMeetingComments();
	    if (comments.size() == 0) {
	        mailBody.append("暂无回复</div>");
	    } else {
	        mailBody.append("<br/><ul style='list-style:none; margin-left: 40px;'>");
	        orderComments(comments);
	        for (OaMeetingComment c : comments) {
	            mailBody.append("<li style='line-height:18px;'><strong>" + CodeNameUtil.getUserNameByCd(c.getCreator()) + "(" + DateOperator.formatDate(c.getCreatedDate(), "yyyy-MM-dd") + ")：</strong>");
	            String comment = Util.clob2String(c.getContent());
	            mailBody.append(comment + "</li>");
	        }
	        mailBody.append("</ul></div>");
	    }
	    
	    return mailBody.toString();
	}
	
	/**
	 * 对评论进行排序
	 * @param comments
	 */
	public void orderComments(List<OaMeetingComment> comments) {
//	    if (comments == null || comments.size() == 0)
//			return;
//	    Comparator<OaMeetingComment> c = new Comparator<OaMeetingComment>() {
//		public int compare(OaMeetingComment c1, OaMeetingComment c2){
//		    // 按留言时间降序排序
//	            return c2.getCreatedDate().compareTo(c1.getCreatedDate());
//		}
//	    };
//	    Collections.sort(comments, c);
	}

	/**
	 * 获取有效的会议记录
	 * @param oaMeetingId
	 */
	public OaMeeting getValidEntity(String oaMeetingId) {
	    if (StringUtils.isBlank(oaMeetingId))
			throw new IllegalArgumentException("会议ID不能为空.");
	
	    OaMeeting m = getEntity(oaMeetingId);
	
	    if (m == null)
			throw new IllegalArgumentException("传入的会议ID非法.");
	    
	    return m;
	}
	
    /**
     * 查找总裁会议表里面的负责人与配合人是否包含
     * @param uiid
     * @return
     */
    public boolean getUserById(String uiid){
    	Boolean flag=false;
    	StringBuffer hql =new StringBuffer("from OaMeeting");
    	hql.append(" where responsiblePerson like '%"+uiid+"%' or coordinatePerson like '%"+uiid+"%' ");
    	hql.append("or visibleToUsers like '%"+uiid+"%'");
    	List<OaMeeting> oaMeeting=  oaMeetingDao.find(hql.toString());
    	if (oaMeeting.size()>0 && oaMeeting!=null) {
    		flag = true;
		}
    	return flag;
    }
    /**
     * 判断用户池中的类型为个人是否包含当前用户。真为包含，假为不包含
     * @param uiid
     * @return
     */
    public boolean getPersonalUserId(String uiid){
    	Boolean flag = false;
    	StringBuffer hql =new StringBuffer("from UserPool");
    	hql.append(" where userList like '%"+uiid+"%' and typeCd='oaMeetingGeren'");
    	List<UserPool> UserPool=  oaMeetingDao.find(hql.toString());
    	if (UserPool.size()>0 && UserPool!=null) {
    		flag = true;
		}
    	return flag;
    }
    
    /**
     * 判断用户池中的类型为个人是否包含当前用户。真为包含，假为不包含
     * @param uiid
     * @return
     */
    public boolean getPersonalUserIdAdmin(String uiid){
    	Boolean flag = false;
    	StringBuffer hql =new StringBuffer("from UserPool");
    	hql.append(" where userList like '%"+uiid+"%' and typeCd='oaMeeting'");
    	List<UserPool> UserPool=  oaMeetingDao.find(hql.toString());
    	if (UserPool.size()>0 && UserPool!=null) {
    		flag = true;
		}
    	return flag;
    }
    /**
     * 查找附件标志位 1:有附件 0：没有附件
     * @param oaMeetingId
     * @return
     */
    public boolean getfileFlg(String oaMeetingId){
    	boolean flag = false;
    	StringBuffer hql =new StringBuffer("from OaMeeting");
    	hql.append(" where oaMeetingId like '%"+oaMeetingId+"%'");
    	List<OaMeeting> oaMeeting=  oaMeetingDao.find(hql.toString());
    	if (oaMeeting.size()>0 && oaMeeting!=null) {
    		flag = true;
		}
    	return flag;
    }
    
    /**
     * 更新附件标志位
     * @param oaMeetingId
     * @param flg
     */
    public void updateFileFlg(String oaMeetingId,String flg){
    	StringBuffer hql =new StringBuffer("update  OaMeeting set fileFlg='"+flg+"'");
    	hql.append("where oaMeetingId ='"+oaMeetingId+"'");
    	oaMeetingDao.batchExecute(hql.toString());
    }

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int getMaxNo(String strModule) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(*) from OaMeeting where strModule=?");
		List lstResult = oaMeetingDao.find(hql.toString(), strModule);
		int sn = 0;
		try {
			if (null != lstResult && 0 != lstResult.size()) {
				sn = ((Long) lstResult.get(0)).intValue();
			}
		} catch (Exception e) {
		}
		sn++;
		return sn;
	}
}

