package com.hhz.uums.dao.plas;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasPub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class PlasPubManager extends BaseService<PlasPub, String> {
	@Autowired
	private PlasPubDao plasPubDao;

	public void savePlasPub(PlasPub plasPub) {
		PowerUtils.setEmptyStr2Null(plasPub);
		plasPubDao.save(plasPub);
	}

	public void deletePlasPub(String id) {
		plasPubDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasPub, String> getDao() {
		return plasPubDao;
	}
	
}

