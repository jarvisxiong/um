package com.hhz.ump.dao.mate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.mate.MateCountDetail;

@Service
@Transactional
public class MateCountDetailManager extends BaseService<MateCountDetail, String> {
	@Autowired
	private MateCountDetailDao mateCountDetailDao;

	public void saveMateCountDetail(MateCountDetail mateCountDetail) {
		PowerUtils.setEmptyStr2Null(mateCountDetail);
		mateCountDetailDao.save(mateCountDetail);
	}

	public void deleteMateCountDetail(String id) {
		mateCountDetailDao.delete(id);
	}
	
	@Override
	public HibernateDao<MateCountDetail, String> getDao() {
		return mateCountDetailDao;
	}

}

