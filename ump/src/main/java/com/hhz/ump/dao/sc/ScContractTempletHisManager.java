package com.hhz.ump.dao.sc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sc.ScContractTempletHis;

@Service
@Transactional
public class ScContractTempletHisManager extends BaseService<ScContractTempletHis, String> {
	@Autowired
	private ScContractTempletHisDao scContractTempletHisDao;

	public void saveScContractTempletHis(ScContractTempletHis scContractTempletHis) {
		PowerUtils.setEmptyStr2Null(scContractTempletHis);
		scContractTempletHisDao.save(scContractTempletHis);
	}

	public void deleteScContractTempletHis(String id) {
		scContractTempletHisDao.delete(id);
	}
	
	@Override
	public HibernateDao<ScContractTempletHis, String> getDao() {
		return scContractTempletHisDao;
	}
	
}

