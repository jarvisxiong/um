package com.hhz.ump.dao.oa;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaManInfoMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class OaManInfoMessageManager extends BaseService<OaManInfoMessage, String> {
	@Autowired
	private OaManInfoMessageDao oaManInfoMessageDao;

	public void saveOaManInfoMessage(OaManInfoMessage oaManInfoMessage) {
		PowerUtils.setEmptyStr2Null(oaManInfoMessage);
		oaManInfoMessageDao.save(oaManInfoMessage);
	}

	public void deleteOaManInfoMessage(String id) {
		oaManInfoMessageDao.delete(id);
	}
	
	@Override
	public HibernateDao<OaManInfoMessage, String> getDao() {
		return oaManInfoMessageDao;
	}
	
}

