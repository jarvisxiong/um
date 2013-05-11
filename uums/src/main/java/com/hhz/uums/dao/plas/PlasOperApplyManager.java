package com.hhz.uums.dao.plas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasOperApply;

@Service
@Transactional
public class PlasOperApplyManager extends BaseService<PlasOperApply, String> {
	@Autowired
	private PlasOperApplyDao plasOperApplyDao;

	public void savePlasOperApply(PlasOperApply plasOperApply) {
		PowerUtils.setEmptyStr2Null(plasOperApply);
		plasOperApplyDao.save(plasOperApply);
	}

	public void deletePlasOperApply(String id) {
		plasOperApplyDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasOperApply, String> getDao() {
		return plasOperApplyDao;
	}
	
}

