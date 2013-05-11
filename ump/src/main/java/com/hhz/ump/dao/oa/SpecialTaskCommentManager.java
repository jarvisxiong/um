package com.hhz.ump.dao.oa;

import java.sql.Clob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.SpecialTask;
import com.hhz.ump.entity.oa.SpecialTaskComment;

@Service
@Transactional
public class SpecialTaskCommentManager extends BaseService<SpecialTaskComment, String> {
	@Autowired
	private SpecialTaskCommentDao specialTaskCommentDao;
	
	@Autowired
	private SpecialTaskManager specialTaskManager;

	public void saveSpecialTaskComment(SpecialTaskComment specialTaskComment) {
		PowerUtils.setEmptyStr2Null(specialTaskComment);
		specialTaskCommentDao.save(specialTaskComment);
	}
	
	/**
	 * 保存评论，并刷新任务的最近更新时间
	 * @param comment
	 * @param taskId
	 */
	public void saveTaskComment(Clob comment, String taskId) {
	    SpecialTask t = specialTaskManager.getValidSpecialTask(taskId);
	    SpecialTaskComment c = new SpecialTaskComment();
	    c.setSpecialTask(t);
	    c.setContent(comment);
	    
	    saveSpecialTaskComment(c);
	    
	    specialTaskManager.refreshUpdatedDate(taskId);
	}

	public void deleteSpecialTaskComment(String id) {
		specialTaskCommentDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<SpecialTaskComment, String> getDao() {
		return specialTaskCommentDao;
	}
	
}

