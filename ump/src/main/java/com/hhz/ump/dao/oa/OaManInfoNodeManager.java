package com.hhz.ump.dao.oa;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaManInfoNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class OaManInfoNodeManager extends BaseService<OaManInfoNode, String> {
	@Autowired
	private OaManInfoNodeDao oaManInfoNodeDao;

	public void saveOaManInfoNode(OaManInfoNode oaManInfoNode) {
		PowerUtils.setEmptyStr2Null(oaManInfoNode);
		oaManInfoNodeDao.save(oaManInfoNode);
	}

	public void deleteOaManInfoNode(String id) {
		oaManInfoNodeDao.delete(id);
	}
	
	@Override
	public HibernateDao<OaManInfoNode, String> getDao() {
		return oaManInfoNodeDao;
	}
	
}

