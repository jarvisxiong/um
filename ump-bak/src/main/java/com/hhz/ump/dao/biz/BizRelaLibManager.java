package com.hhz.ump.dao.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.biz.BizRelaLib;

@Service
@Transactional
public class BizRelaLibManager extends BaseService<BizRelaLib, String> {
	@Autowired
	private BizRelaLibDao bizRelaLibDao;

	public void saveBizRelaLib(BizRelaLib bizRelaLib) {
		PowerUtils.setEmptyStr2Null(bizRelaLib);
		bizRelaLibDao.save(bizRelaLib);
	}

	public void deleteBizRelaLib(String id) {
		bizRelaLibDao.delete(id);
	}
	
	@Override
	public HibernateDao<BizRelaLib, String> getDao() {
		return bizRelaLibDao;
	}
	
}

