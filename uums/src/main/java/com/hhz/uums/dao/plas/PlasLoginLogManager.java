package com.hhz.uums.dao.plas;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasLoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class PlasLoginLogManager extends BaseService<PlasLoginLog, String> {
	@Autowired
	private PlasLoginLogDao plasLoginLogDao;

	public void savePlasLoginLog(PlasLoginLog plasLoginLog) {
		PowerUtils.setEmptyStr2Null(plasLoginLog);
		plasLoginLogDao.save(plasLoginLog);
	}

	public void deletePlasLoginLog(String id) {
		plasLoginLogDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasLoginLog, String> getDao() {
		return plasLoginLogDao;
	}
	
}

