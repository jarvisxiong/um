package com.hhz.ump.dao.pm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.pm.PmExecDetail;

@Service
@Transactional
public class PmExecDetailManager extends BaseService<PmExecDetail, String> {
	@Autowired
	private PmExecDetailDao pmExecDetailDao;

	public void savePmExecDetail(PmExecDetail pmExecDetail) {
		PowerUtils.setEmptyStr2Null(pmExecDetail);
		pmExecDetailDao.save(pmExecDetail);
	}

	public void deletePmExecDetail(String id) {
		pmExecDetailDao.delete(id);
	}
	
	@Override
	public HibernateDao<PmExecDetail, String> getDao() {
		return pmExecDetailDao;
	}
	
}

