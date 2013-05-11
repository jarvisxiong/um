package com.hhz.ump.dao.ct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.ct.CtLand;

@Service
@Transactional
public class CtLandManager extends BaseService<CtLand, String> {
	@Autowired
	private CtLandDao ctLandDao;

	public void saveCtLand(CtLand ctLand) {
		PowerUtils.setEmptyStr2Null(ctLand);
		ctLandDao.save(ctLand);
	}

	public void deleteCtLand(String id) {
		ctLandDao.delete(id);
	}
	
	@Override
	public HibernateDao<CtLand, String> getDao() {
		return ctLandDao;
	}
	
}

