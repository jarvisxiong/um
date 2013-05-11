package com.hhz.ump.dao.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;

@Service
@Transactional
public class CommonManager extends BaseService {
	@Autowired
	private CommonDao commonDao;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao getDao() {
		return commonDao;
	}
	
}
