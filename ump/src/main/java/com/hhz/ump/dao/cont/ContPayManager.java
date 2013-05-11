package com.hhz.ump.dao.cont;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cont.ContPay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class ContPayManager extends BaseService<ContPay, String> {
	@Autowired
	private ContPayDao contPayDao;

	public void saveContPay(ContPay contPay) {
		PowerUtils.setEmptyStr2Null(contPay);
		contPayDao.save(contPay);
	}

	public void deleteContPay(String id) {
		contPayDao.delete(id);
	}
	
	@Override
	public HibernateDao<ContPay, String> getDao() {
		return contPayDao;
	}
	
}

