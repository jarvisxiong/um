package com.hhz.ump.dao.sc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sc.ScContractInfoAttach;

@Service
@Transactional
public class ScContractInfoAttachManager extends BaseService<ScContractInfoAttach, String> {
	@Autowired
	private ScContractInfoAttachDao scContractInfoAttachDao;

	public void saveScContractInfoAttach(ScContractInfoAttach scContractInfoAttach) {
		PowerUtils.setEmptyStr2Null(scContractInfoAttach);
		scContractInfoAttachDao.save(scContractInfoAttach);
	}

	public void deleteScContractInfoAttach(String id) {
		scContractInfoAttachDao.delete(id);
	}
	
	@Override
	public HibernateDao<ScContractInfoAttach, String> getDao() {
		return scContractInfoAttachDao;
	}
	
}

