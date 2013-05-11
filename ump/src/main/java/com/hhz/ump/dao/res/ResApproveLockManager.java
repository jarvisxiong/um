package com.hhz.ump.dao.res;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResApproveLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class ResApproveLockManager extends BaseService<ResApproveLock, String> {
	@Autowired
	private ResApproveLockDao resApproveLockDao;

	public void saveResApproveLock(ResApproveLock resApproveLock) {
		PowerUtils.setEmptyStr2Null(resApproveLock);
		resApproveLockDao.save(resApproveLock);
	}

	public void deleteResApproveLock(String id) {
		resApproveLockDao.delete(id);
	}
	
	@Override
	public HibernateDao<ResApproveLock, String> getDao() {
		return resApproveLockDao;
	}
	
}

