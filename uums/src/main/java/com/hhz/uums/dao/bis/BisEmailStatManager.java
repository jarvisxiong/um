package com.hhz.uums.dao.bis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.dao.plas.PlasAcctManager;
import com.hhz.uums.entity.bis.BisEmailStat;

@Service
@Transactional
public class BisEmailStatManager extends BaseService<BisEmailStat, String> {
	
	private static Log log = LogFactory.getLog(BisEmailStatManager.class);
	
	@Autowired
	private BisEmailStatDao bisEmailStatDao;
	@Autowired
	private PlasAcctManager plasAcctManager;
	

	public void saveBisEmailStat(BisEmailStat bisEmailStat) {
		PowerUtils.setEmptyStr2Null(bisEmailStat);
		bisEmailStatDao.save(bisEmailStat);
	}

	public void deleteBisEmailStat(String id) {
		bisEmailStatDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisEmailStat, String> getDao() {
		return bisEmailStatDao;
	}
	

}

