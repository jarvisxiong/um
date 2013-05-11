package com.hhz.uums.dao.bis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.bis.BisEmailAcctTmp;

@Service
@Transactional
public class BisEmailAcctTmpManager extends BaseService<BisEmailAcctTmp, String> {
	@Autowired
	private BisEmailAcctTmpDao bisEmailAcctTmpDao;

	public void saveBisEmailAcctTmp(BisEmailAcctTmp bisEmailAcctTmp) {
		PowerUtils.setEmptyStr2Null(bisEmailAcctTmp);
		bisEmailAcctTmpDao.save(bisEmailAcctTmp);
	}

	public void deleteBisEmailAcctTmp(String id) {
		bisEmailAcctTmpDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisEmailAcctTmp, String> getDao() {
		return bisEmailAcctTmpDao;
	}
	
}

