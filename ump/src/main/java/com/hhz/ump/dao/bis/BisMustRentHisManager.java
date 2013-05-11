package com.hhz.ump.dao.bis;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisMustRentHis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class BisMustRentHisManager extends BaseService<BisMustRentHis, String> {
	@Autowired
	private BisMustRentHisDao bisMustRentHisDao;

	public void saveBisMustRentHis(BisMustRentHis bisMustRentHis) {
		PowerUtils.setEmptyStr2Null(bisMustRentHis);
		bisMustRentHisDao.save(bisMustRentHis);
	}

	public void deleteBisMustRentHis(String id) {
		bisMustRentHisDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisMustRentHis, String> getDao() {
		return bisMustRentHisDao;
	}
	
}

