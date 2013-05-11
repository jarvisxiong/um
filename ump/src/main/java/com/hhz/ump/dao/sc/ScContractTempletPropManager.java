package com.hhz.ump.dao.sc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sc.ScContractTempletProp;

@Service
@Transactional
public class ScContractTempletPropManager extends BaseService<ScContractTempletProp, String> {
	@Autowired
	private ScContractTempletPropDao scContractTempletPropDao;

	public void saveScContractTempletProp(ScContractTempletProp scContractTempletProp) {
		PowerUtils.setEmptyStr2Null(scContractTempletProp);
		scContractTempletPropDao.save(scContractTempletProp);
	}

	public void deleteScContractTempletProp(String id) {
		scContractTempletPropDao.delete(id);
	}
	
	@Override
	public HibernateDao<ScContractTempletProp, String> getDao() {
		return scContractTempletPropDao;
	}
	
}

