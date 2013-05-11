package com.hhz.ump.dao.oa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.SpecialTask;
import com.hhz.ump.entity.oa.SpecialTaskComment;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Util;

@Service
@Transactional
public class SpecialTaskManager extends BaseService<SpecialTask, String> {
    
    	public static final String DELIMITER = ";";
    	
    	private static final String MAIL_ADMIN = "email_admin";
    
	@Autowired
	private SpecialTaskDao specialTaskDao;
	
	@Autowired
	private OaEmailBodyManager oaEmailBodyManager;

	
	public void saveSpecialTask(SpecialTask specialTask) {
		PowerUtils.setEmptyStr2Null(specialTask);
		specialTaskDao.save(specialTask);
	}

	public void deleteSpecialTask(String id) {
		specialTaskDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<SpecialTask, String> getDao() {
		return specialTaskDao;
	}
	
	/**
	 * 发送提醒到责任人
	 * @param task
	 * @param sender
	 * @return
	 */
	public boolean sendRemindEmail(SpecialTask task) throws SQLException, IOException {
	    String resPerson = task.getResponsiblePerson();
	    String[] ids = null;
	    Set<String> toUiids = new HashSet<String>();
	    
	    if (StringUtils.isNotBlank(resPerson)) {
		ids = resPerson.split(";");
		for (String id : ids) {
		    if (StringUtils.isNotBlank(id)) {
			toUiids.add(id);
		    }
		}
		
		if (toUiids.size() > 0) {
		    String subject = "【专项任务提醒】-" + task.getWaterNum() + "：" + task.getSubject();
		    
		    oaEmailBodyManager.sendData2Email(subject, buildMailBody(task), MAIL_ADMIN, "0", toUiids.toArray(new String[toUiids.size()]));
		    
		    return true;
		}
	    }
	    
	    return false;
	}

	private String buildMailBody(SpecialTask task) throws SQLException, IOException {
	    String content = Util.clob2String(task.getDetail());
	    
	    StringBuilder mailBody = new StringBuilder();
	    
	    mailBody.append("<div style='font-size: 14px; line-height: 25px'><a href='#' style='text-decoration: underline; color: #000;' onclick='parent.openTask(\"\", \"" 
		    + task.getSpecialTaskId() + "\", \"\", \"specialtask\"); return false;'>点击查看详细信息>></a></div>");
	    
	    mailBody.append("<div style='font-size: 14px; line-height: 25px'>任务内容：</div>");
	    mailBody.append("<div style='font-size: 14px; line-height: 20px; margin-left: 40px; margin-bottom: 10px;'><pre>" + content + "</pre></div>");
	    mailBody.append("<div style='font-size: 14px; line-height: 25px'>目标时间：" + DateOperator.formatDate(task.getTargetDate(), "yyyy-MM-dd") + "</div>");
	    mailBody.append("<div style='font-size: 14px;'>回复：");
	    
	    List<SpecialTaskComment> comments = task.getSpecialTaskComments();
	    if (comments.size() == 0) {
		mailBody.append("暂无回复</div>");
	    } else {
		mailBody.append("<br/><ul style='list-style:none; margin-left: 40px;'>");
		orderComments(comments);
		for (SpecialTaskComment c : comments) {
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
         * 
         * @param comments
         */
        public void orderComments(List<SpecialTaskComment> comments) {
        	if (comments == null || comments.size() == 0)
				return;
        	Comparator<SpecialTaskComment> c = new Comparator<SpecialTaskComment>() {
        	    public int compare(SpecialTaskComment c1, SpecialTaskComment c2) {
        		// 按时间降序排列
        		return c2.getCreatedDate().compareTo(c1.getCreatedDate());
        	    }
        	};
        	Collections.sort(comments, c);
        }
        
        /**
         * 获取有效的专项任务记录，如果找不到则抛异常
         * @param taskId
         * @return
         */
        public SpecialTask getValidSpecialTask(String taskId) {
            if (StringUtils.isBlank(taskId))
				throw new IllegalArgumentException("请传入任务记录ID");
            
            SpecialTask t = getEntity(taskId);
            
            if (t == null)
				throw new IllegalArgumentException("未找到指定的任务记录:" + taskId);
            
            return t;
        }
}

