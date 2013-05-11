package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisTenantAttach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisTenantAttachManager extends BaseService<BisTenantAttach, String> {
	@Autowired
	private BisTenantAttachDao bisTenantAttachDao;

	public void saveBisTenantAttach(BisTenantAttach bisTenantAttach) {
		PowerUtils.setEmptyStr2Null(bisTenantAttach);
		bisTenantAttachDao.save(bisTenantAttach);
	}

	public void deleteBisTenantAttach(String id) {
		bisTenantAttachDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisTenantAttach, String> getDao() {
		return bisTenantAttachDao;
	}
	
}

