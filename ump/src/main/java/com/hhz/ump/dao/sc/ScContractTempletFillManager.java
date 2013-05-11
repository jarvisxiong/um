package com.hhz.ump.dao.sc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sc.ScContractTempletFill;

@Service
@Transactional
public class ScContractTempletFillManager extends BaseService<ScContractTempletFill, String> {
	@Autowired
	private ScContractTempletFillDao scContractTempletFillDao;

	public void saveScContractTempletFill(ScContractTempletFill scContractTempletFill) {
		PowerUtils.setEmptyStr2Null(scContractTempletFill);
		scContractTempletFillDao.save(scContractTempletFill);
	}

	public void deleteScContractTempletFill(String id) {
		scContractTempletFillDao.delete(id);
	}
	
	@Override
	public HibernateDao<ScContractTempletFill, String> getDao() {
		return scContractTempletFillDao;
	}
	
}

