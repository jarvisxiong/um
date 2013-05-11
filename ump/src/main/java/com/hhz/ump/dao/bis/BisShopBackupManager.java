package com.hhz.ump.dao.bis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisShopBackup;

@Service
@Transactional
public class BisShopBackupManager extends BaseService<BisShopBackup, String> {
	@Autowired
	private BisShopBackupDao bisShopBackupDao;

	public void saveBisShopBackup(BisShopBackup bisShopBackup) {
		PowerUtils.setEmptyStr2Null(bisShopBackup);
		bisShopBackupDao.save(bisShopBackup);
	}

	public void deleteBisShopBackup(String id) {
		bisShopBackupDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisShopBackup, String> getDao() {
		return bisShopBackupDao;
	}
	
}

