package com.hhz.ump.dao.sup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sup.SupApproveRes;

@Service
@Transactional
public class SupApproveResManager extends BaseService<SupApproveRes, String> {
	@Autowired
	private SupApproveResDao supApproveResDao;

	public void saveSupApproveRes(SupApproveRes supApproveRes) {
		PowerUtils.setEmptyStr2Null(supApproveRes);
		supApproveResDao.save(supApproveRes);
	}

	public void deleteSupApproveRes(String id) {
		supApproveResDao.delete(id);
	}
	
	@Override
	public HibernateDao<SupApproveRes, String> getDao() {
		return supApproveResDao;
	}
	
}

