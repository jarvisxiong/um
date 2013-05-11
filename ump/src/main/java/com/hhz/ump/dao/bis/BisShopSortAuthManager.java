package com.hhz.ump.dao.bis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisShopSortAuth;

@Service
@Transactional
public class BisShopSortAuthManager extends BaseService<BisShopSortAuth, String> {
	@Autowired
	private BisShopSortAuthDao bisShopSortAuthDao;

	public void saveBisShopSortAuth(BisShopSortAuth bisShopSortAuth) {
		PowerUtils.setEmptyStr2Null(bisShopSortAuth);
		bisShopSortAuthDao.save(bisShopSortAuth);
	}

	public void deleteBisShopSortAuth(String id) {
		bisShopSortAuthDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisShopSortAuth, String> getDao() {
		return bisShopSortAuthDao;
	}
	
}

