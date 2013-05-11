package com.hhz.ump.dao.ct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.ct.CtLedgerHismark;

@Service
@Transactional
public class CtLedgerHismarkManager extends BaseService<CtLedgerHismark, String> {
	@Autowired
	private CtLedgerHismarkDao ctLedgerHismarkDao;

	public void saveCtLedgerHismark(CtLedgerHismark ctLedgerHismark) {
		PowerUtils.setEmptyStr2Null(ctLedgerHismark);
		ctLedgerHismarkDao.save(ctLedgerHismark);
	}

	public void deleteCtLedgerHismark(String id) {
		ctLedgerHismarkDao.delete(id);
	}
	
	@Override
	public HibernateDao<CtLedgerHismark, String> getDao() {
		return ctLedgerHismarkDao;
	}
	
}

