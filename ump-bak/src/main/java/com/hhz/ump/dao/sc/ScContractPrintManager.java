package com.hhz.ump.dao.sc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sc.ScContractPrint;

@Service
@Transactional
public class ScContractPrintManager extends BaseService<ScContractPrint, String> {
	@Autowired
	private ScContractPrintDao scContractPrintDao;

	public void saveScContractPrint(ScContractPrint scContractPrint) {
		PowerUtils.setEmptyStr2Null(scContractPrint);
		scContractPrintDao.save(scContractPrint);
	}

	public void deleteScContractPrint(String id) {
		scContractPrintDao.delete(id);
	}
	
	@Override
	public HibernateDao<ScContractPrint, String> getDao() {
		return scContractPrintDao;
	}
	
}

