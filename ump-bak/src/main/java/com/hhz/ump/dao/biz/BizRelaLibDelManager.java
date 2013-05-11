package com.hhz.ump.dao.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.biz.BizRelaLibDel;

@Service
@Transactional
public class BizRelaLibDelManager extends BaseService<BizRelaLibDel, String> {
	@Autowired
	private BizRelaLibDelDao bizRelaLibDelDao;

	public void saveBizRelaLibDel(BizRelaLibDel bizRelaLibDel) {
		PowerUtils.setEmptyStr2Null(bizRelaLibDel);
		bizRelaLibDelDao.save(bizRelaLibDel);
	}

	public void deleteBizRelaLibDel(String id) {
		bizRelaLibDelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BizRelaLibDel, String> getDao() {
		return bizRelaLibDelDao;
	}
	
}

