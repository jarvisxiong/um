package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisTenantMess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisTenantMessManager extends BaseService<BisTenantMess, String> {
	@Autowired
	private BisTenantMessDao bisTenantMessDao;

	public void saveBisTenantMess(BisTenantMess bisTenantMess) {
		PowerUtils.setEmptyStr2Null(bisTenantMess);
		bisTenantMessDao.save(bisTenantMess);
	}

	public void deleteBisTenantMess(String id) {
		bisTenantMessDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisTenantMess, String> getDao() {
		return bisTenantMessDao;
	}
	
}

