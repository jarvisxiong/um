package com.hhz.ump.dao.ct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.ct.CtOperation;

@Service
@Transactional
public class CtOperationManager extends BaseService<CtOperation, String> {
	@Autowired
	private CtOperationDao ctOperationDao;

	public void saveCtOperation(CtOperation ctOperation) {
		PowerUtils.setEmptyStr2Null(ctOperation);
		ctOperationDao.save(ctOperation);
	}

	public void deleteCtOperation(String id) {
		ctOperationDao.delete(id);
	}
	
	@Override
	public HibernateDao<CtOperation, String> getDao() {
		return ctOperationDao;
	}
	
}

