package com.hhz.ump.dao.sc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sc.ScContractTempletType;

@Service
@Transactional
public class ScContractTempletTypeManager extends BaseService<ScContractTempletType, String> {
	@Autowired
	private ScContractTempletTypeDao scContractTempletTypeDao;

	public void saveScContractTempletType(ScContractTempletType scContractTempletType) {
		PowerUtils.setEmptyStr2Null(scContractTempletType);
		scContractTempletTypeDao.save(scContractTempletType);
	}

	public void deleteScContractTempletType(String id) {
		scContractTempletTypeDao.delete(id);
	}
	
	@Override
	public HibernateDao<ScContractTempletType, String> getDao() {
		return scContractTempletTypeDao;
	}
	
}

