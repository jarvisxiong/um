package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisContMulti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisContMultiManager extends BaseService<BisContMulti, String> {
	@Autowired
	private BisContMultiDao bisContMultiDao;

	public void saveBisContMulti(BisContMulti bisContMulti) {
		PowerUtils.setEmptyStr2Null(bisContMulti);
		bisContMultiDao.save(bisContMulti);
	}

	public void deleteBisContMulti(String id) {
		bisContMultiDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisContMulti, String> getDao() {
		return bisContMultiDao;
	}
	
}

