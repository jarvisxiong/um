package com.hhz.ump.dao.sc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.sc.ScContractTempletUser;

@Service
@Transactional
public class ScContractTempletUserManager extends BaseService<ScContractTempletUser, String> {
	@Autowired
	private ScContractTempletUserDao scContractTempletUserDao;

	public void saveScContractTempletUser(ScContractTempletUser scContractTempletUser) {
		PowerUtils.setEmptyStr2Null(scContractTempletUser);
		scContractTempletUserDao.save(scContractTempletUser);
	}

	public void deleteScContractTempletUser(String id) {
		scContractTempletUserDao.delete(id);
	}
	
	@Override
	public HibernateDao<ScContractTempletUser, String> getDao() {
		return scContractTempletUserDao;
	}
	
}

