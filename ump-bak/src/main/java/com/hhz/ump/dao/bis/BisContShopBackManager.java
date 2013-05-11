package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisContShopBack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisContShopBackManager extends BaseService<BisContShopBack, String> {
	@Autowired
	private BisContShopBackDao bisContShopBackDao;

	public void saveBisContShopBack(BisContShopBack bisContShopBack) {
		PowerUtils.setEmptyStr2Null(bisContShopBack);
		bisContShopBackDao.save(bisContShopBack);
	}

	public void deleteBisContShopBack(String id) {
		bisContShopBackDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisContShopBack, String> getDao() {
		return bisContShopBackDao;
	}
	
}

