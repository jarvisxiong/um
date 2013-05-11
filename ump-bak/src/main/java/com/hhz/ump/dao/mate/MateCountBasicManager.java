package com.hhz.ump.dao.mate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.mate.MateCountBasic;

@Service
@Transactional
public class MateCountBasicManager extends BaseService<MateCountBasic, String> {
	@Autowired
	private MateCountBasicDao mateCountBasicDao;

	public void saveMateCountBasic(MateCountBasic mateCountBasic) {
		PowerUtils.setEmptyStr2Null(mateCountBasic);
		mateCountBasicDao.save(mateCountBasic);
	}

	public void deleteMateCountBasic(String id) {
		mateCountBasicDao.delete(id);
	}
	
	@Override
	public HibernateDao<MateCountBasic, String> getDao() {
		return mateCountBasicDao;
	}
	
}

