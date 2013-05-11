package com.hhz.ump.dao.biz;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.biz.BizHtlHoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BizHtlHoeManager extends BaseService<BizHtlHoe, String> {
	@Autowired
	private BizHtlHoeDao bizHtlHoeDao;

	public void saveBizHtlHoe(BizHtlHoe bizHtlHoe) {
		PowerUtils.setEmptyStr2Null(bizHtlHoe);
		bizHtlHoeDao.save(bizHtlHoe);
	}

	public void deleteBizHtlHoe(String id) {
		bizHtlHoeDao.delete(id);
	}
	
	@Override
	public HibernateDao<BizHtlHoe, String> getDao() {
		return bizHtlHoeDao;
	}
	
}

