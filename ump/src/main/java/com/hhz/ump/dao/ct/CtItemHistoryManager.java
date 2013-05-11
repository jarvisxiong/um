package com.hhz.ump.dao.ct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.ct.CtItemHistory;

@Service
@Transactional
public class CtItemHistoryManager extends BaseService<CtItemHistory, String> {
	@Autowired
	private CtItemHistoryDao ctItemHistoryDao;

	public void saveCtItemHistory(CtItemHistory ctItemHistory) {
		PowerUtils.setEmptyStr2Null(ctItemHistory);
		ctItemHistoryDao.save(ctItemHistory);
	}

	public void deleteCtItemHistory(String id) {
		ctItemHistoryDao.delete(id);
	}
	
	@Override
	public HibernateDao<CtItemHistory, String> getDao() {
		return ctItemHistoryDao;
	}
	
}

