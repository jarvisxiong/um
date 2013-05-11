package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisShopCredit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisShopCreditManager extends BaseService<BisShopCredit, String> {
	@Autowired
	private BisShopCreditDao bisShopCreditDao;

	public void saveBisShopCredit(BisShopCredit bisShopCredit) {
		PowerUtils.setEmptyStr2Null(bisShopCredit);
		bisShopCreditDao.save(bisShopCredit);
	}

	public void deleteBisShopCredit(String id) {
		bisShopCreditDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisShopCredit, String> getDao() {
		return bisShopCreditDao;
	}
	
}

