package com.hhz.uums.dao.bis;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.bis.BisSynEmailUser;

@Service
@Transactional
public class BisSynEmailUserManager extends BaseService<BisSynEmailUser, String> {
	@Autowired
	private BisSynEmailUserDao bisSynEmailUserDao;

	public void saveBisSynEmailUser(BisSynEmailUser bisSynEmailUser) {
		PowerUtils.setEmptyStr2Null(bisSynEmailUser);
		bisSynEmailUserDao.save(bisSynEmailUser);
	}

	public void deleteBisSynEmailUser(String id) {
		bisSynEmailUserDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisSynEmailUser, String> getDao() {
		return bisSynEmailUserDao;
	}
	public long deleteAll() {
		return bisSynEmailUserDao.batchExecute(" delete from BisSynEmailUser ", new HashMap<String,Object>());
	}
}

