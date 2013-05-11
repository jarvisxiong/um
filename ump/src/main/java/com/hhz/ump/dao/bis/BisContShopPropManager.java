package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisContShopProp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisContShopPropManager extends BaseService<BisContShopProp, String> {
	@Autowired
	private BisContShopPropDao bisContShopPropDao;

	public void saveBisContShopProp(BisContShopProp bisContShopProp) {
		PowerUtils.setEmptyStr2Null(bisContShopProp);
		bisContShopPropDao.save(bisContShopProp);
	}

	public void deleteBisContShopProp(String id) {
		bisContShopPropDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisContShopProp, String> getDao() {
		return bisContShopPropDao;
	}
	
}

