package com.hhz.ump.dao.sc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sc.ScContractMark;

@Service
@Transactional
public class ScContractMarkManager extends BaseService<ScContractMark, String> {
	@Autowired
	private ScContractMarkDao scContractMarkDao;

	public void saveScContractMark(ScContractMark scContractMark) {
		PowerUtils.setEmptyStr2Null(scContractMark);
		scContractMarkDao.save(scContractMark);
	}

	public void deleteScContractMark(String id) {
		scContractMarkDao.delete(id);
	}
	
	@Override
	public HibernateDao<ScContractMark, String> getDao() {
		return scContractMarkDao;
	}
	
}

