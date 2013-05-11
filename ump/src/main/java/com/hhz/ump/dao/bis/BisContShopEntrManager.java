package com.hhz.ump.dao.bis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisContShopEntr;

@Service
@Transactional
public class BisContShopEntrManager extends BaseService<BisContShopEntr, String> {
	@Autowired
	private BisContShopEntrDao BisContShopEntrDao;

	public void saveBisContShopEntr(BisContShopEntr BisContShopEntr) {
		PowerUtils.setEmptyStr2Null(BisContShopEntr);
		BisContShopEntrDao.save(BisContShopEntr);
	}

	public void deleteBisContShopEntr(String id) {
		BisContShopEntrDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisContShopEntr, String> getDao() {
		return BisContShopEntrDao;
	}
	
}

