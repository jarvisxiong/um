package com.hhz.ump.dao.oa;

import java.sql.Clob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaMeeting;
import com.hhz.ump.entity.oa.OaMeetingComment;

@Service
@Transactional
public class OaMeetingCommentManager extends BaseService<OaMeetingComment, String> {
	@Autowired
	private OaMeetingCommentDao oaMeetingCommentDao;
	
	@Autowired
	private OaMeetingManager oaMeetingManager;

	
	public void saveOaMeetingComment(OaMeetingComment oaMeetingComment) {
		PowerUtils.setEmptyStr2Null(oaMeetingComment);
		oaMeetingCommentDao.save(oaMeetingComment);
	}

	public void deleteOaMeetingComment(String id) {
		oaMeetingCommentDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<OaMeetingComment, String> getDao() {
		return oaMeetingCommentDao;
	}
	
	/**
	 * 保存评论，并刷新总裁例会的最近更新时间
	 * @param comment
	 * @param meeting
	 */
	public void saveMeetingComment(Clob comment, String oaMeetingId) {
	    OaMeeting m = oaMeetingManager.getValidEntity(oaMeetingId);
	    OaMeetingComment oaMeetingComment = new OaMeetingComment();
	    oaMeetingComment.setOaMeeting(m);
	    oaMeetingComment.setContent(comment);
	    
	    saveOaMeetingComment(oaMeetingComment);
	    oaMeetingManager.refreshUpdatedDate(oaMeetingId);
	}
	
}

