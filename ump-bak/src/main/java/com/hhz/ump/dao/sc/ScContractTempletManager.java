package com.hhz.ump.dao.sc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sc.ScContractTemplet;

@Service
@Transactional
public class ScContractTempletManager extends BaseService<ScContractTemplet, String> {
	@Autowired
	private ScContractTempletDao scContractTempletDao;

	public void saveScContractTemplet(ScContractTemplet scContractTemplet) {
		PowerUtils.setEmptyStr2Null(scContractTemplet);
		scContractTempletDao.save(scContractTemplet);
	}

	public void deleteScContractTemplet(String id) {
		scContractTempletDao.delete(id);
	}
	
	@Override
	public HibernateDao<ScContractTemplet, String> getDao() {
		return scContractTempletDao;
	}
	
}

