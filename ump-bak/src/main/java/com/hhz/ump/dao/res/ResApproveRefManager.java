package com.hhz.ump.dao.res;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResApproveRef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class ResApproveRefManager extends BaseService<ResApproveRef, String> {
	@Autowired
	private ResApproveRefDao resApproveRefDao;

	public void saveResApproveRef(ResApproveRef resApproveRef) {
		PowerUtils.setEmptyStr2Null(resApproveRef);
		resApproveRefDao.save(resApproveRef);
	}

	public void deleteResApproveRef(String id) {
		resApproveRefDao.delete(id);
	}
	
	@Override
	public HibernateDao<ResApproveRef, String> getDao() {
		return resApproveRefDao;
	}
	
}

