package com.hhz.ump.dao.oa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaNewsComment;

@Service
@Transactional
public class OaNewsCommentManager extends BaseService<OaNewsComment, String> {
	@Autowired
	private OaNewsCommentDao oaNewsCommentDao;
	
	public void saveOaNewsComment(OaNewsComment oaNewsComment) {
		PowerUtils.setEmptyStr2Null(oaNewsComment);
		oaNewsCommentDao.save(oaNewsComment);
	}

	public void deleteOaNewsComment(String id) {
		oaNewsCommentDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<OaNewsComment, String> getDao() {
		return oaNewsCommentDao;
	}
	
}

