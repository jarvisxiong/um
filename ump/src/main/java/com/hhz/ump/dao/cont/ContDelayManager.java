package com.hhz.ump.dao.cont;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cont.ContDelay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class ContDelayManager extends BaseService<ContDelay, String> {
	@Autowired
	private ContDelayDao contDelayDao;

	public void saveContDelay(ContDelay contDelay) {
		PowerUtils.setEmptyStr2Null(contDelay);
		contDelayDao.save(contDelay);
	}

	public void deleteContDelay(String id) {
		contDelayDao.delete(id);
	}
	
	@Override
	public HibernateDao<ContDelay, String> getDao() {
		return contDelayDao;
	}
	
}

