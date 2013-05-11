package com.hhz.ump.dao.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.hr.HrTipEas;

@Service
@Transactional
public class HrTipEasManager extends BaseService<HrTipEas, String> {
	@Autowired
	private HrTipEasDao hrTipEasDao;

	public void saveHrTipEas(HrTipEas hrTipEas) {
		PowerUtils.setEmptyStr2Null(hrTipEas);
		hrTipEasDao.save(hrTipEas);
	}

	public void deleteHrTipEas(String id) {
		hrTipEasDao.delete(id);
	}
	
	@Override
	public HibernateDao<HrTipEas, String> getDao() {
		return hrTipEasDao;
	}
	
}

