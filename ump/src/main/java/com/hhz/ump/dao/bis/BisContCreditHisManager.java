package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisContCreditHis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisContCreditHisManager extends BaseService<BisContCreditHis, String> {
	@Autowired
	private BisContCreditHisDao bisContCreditHisDao;

	public void saveBisContCreditHis(BisContCreditHis bisContCreditHis) {
		PowerUtils.setEmptyStr2Null(bisContCreditHis);
		bisContCreditHisDao.save(bisContCreditHis);
	}

	public void deleteBisContCreditHis(String id) {
		bisContCreditHisDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisContCreditHis, String> getDao() {
		return bisContCreditHisDao;
	}
	
}

