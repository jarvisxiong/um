package com.hhz.ump.dao.sc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sc.ScContractInfoFlow;

@Service
@Transactional
public class ScContractInfoFlowManager extends BaseService<ScContractInfoFlow, String> {
	@Autowired
	private ScContractInfoFlowDao scContractInfoFlowDao;

	public void saveScContractInfoFlow(ScContractInfoFlow scContractInfoFlow) {
		PowerUtils.setEmptyStr2Null(scContractInfoFlow);
		scContractInfoFlowDao.save(scContractInfoFlow);
	}

	public void deleteScContractInfoFlow(String id) {
		scContractInfoFlowDao.delete(id);
	}
	
	@Override
	public HibernateDao<ScContractInfoFlow, String> getDao() {
		return scContractInfoFlowDao;
	}
	
}

