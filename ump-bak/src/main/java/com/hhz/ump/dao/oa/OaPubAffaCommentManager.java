package com.hhz.ump.dao.oa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaPubAffaComment;

@Service
@Transactional
public class OaPubAffaCommentManager extends BaseService<OaPubAffaComment, String> {
	@Autowired
	private OaPubAffaCommentDao oaPubAffaCommentDao;

	public void saveOaPubAffaComment(OaPubAffaComment oaPubAffaComment) {
		PowerUtils.setEmptyStr2Null(oaPubAffaComment);
		oaPubAffaCommentDao.save(oaPubAffaComment);
	}

	public void deleteOaPubAffaComment(String id) {
		oaPubAffaCommentDao.delete(id);
	}
	
	@Override
	public HibernateDao<OaPubAffaComment, String> getDao() {
		return oaPubAffaCommentDao;
	}
	
}

