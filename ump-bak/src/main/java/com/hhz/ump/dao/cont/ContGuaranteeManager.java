package com.hhz.ump.dao.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cont.ContGuarantee;

@Service
@Transactional
public class ContGuaranteeManager extends BaseService<ContGuarantee, String> {
	@Autowired
	private ContGuaranteeDao contGuaranteeDao;

	public void saveContGuarantee(ContGuarantee contGuarantee) {
		PowerUtils.setEmptyStr2Null(contGuarantee);
		contGuaranteeDao.save(contGuarantee);
	}

	public void deleteContGuarantee(String id) {
		contGuaranteeDao.delete(id);
	}
	
	@Override
	public HibernateDao<ContGuarantee, String> getDao() {
		return contGuaranteeDao;
	}
	
}

