package com.hhz.ump.dao.oa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaEmailBox;

@Service
@Transactional
public class OaEmailBoxManager extends BaseService<OaEmailBox, String> {
	@Autowired
	private OaEmailBoxDao oaEmailBoxDao;
	
	public void saveOaEmailBox(OaEmailBox oaEmailBox) {
		PowerUtils.setEmptyStr2Null(oaEmailBox);
		oaEmailBoxDao.save(oaEmailBox);
	}

	public void deleteOaEmailBox(String id) {
		oaEmailBoxDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<OaEmailBox, String> getDao() {
		return oaEmailBoxDao;
	}
	
}

