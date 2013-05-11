package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisContShopRent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisContShopRentManager extends BaseService<BisContShopRent, String> {
	@Autowired
	private BisContShopRentDao bisContShopRentDao;

	public void saveBisContShopRent(BisContShopRent bisContShopRent) {
		PowerUtils.setEmptyStr2Null(bisContShopRent);
		bisContShopRentDao.save(bisContShopRent);
	}

	public void deleteBisContShopRent(String id) {
		bisContShopRentDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisContShopRent, String> getDao() {
		return bisContShopRentDao;
	}
	
}

