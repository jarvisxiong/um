package com.hhz.ump.dao.oa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaEmailGroup;

@Service
@Transactional
public class OaEmailGroupManager extends BaseService<OaEmailGroup, String> {
	@Autowired
	private OaEmailGroupDao oaEmailGroupDao;

	public void saveOaEmailGroup(OaEmailGroup oaEmailGroup) {
		PowerUtils.setEmptyStr2Null(oaEmailGroup);
		oaEmailGroupDao.save(oaEmailGroup);
	}

	public void deleteOaEmailGroup(String id) {
		oaEmailGroupDao.delete(id);
	}
	
	@Override
	public HibernateDao<OaEmailGroup, String> getDao() {
		return oaEmailGroupDao;
	}
	
}

