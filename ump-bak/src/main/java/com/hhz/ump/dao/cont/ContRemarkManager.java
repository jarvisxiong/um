package com.hhz.ump.dao.cont;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cont.ContRemark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class ContRemarkManager extends BaseService<ContRemark, String> {
	@Autowired
	private ContRemarkDao contRemarkDao;

	public void saveContRemark(ContRemark contRemark) {
		PowerUtils.setEmptyStr2Null(contRemark);
		contRemarkDao.save(contRemark);
	}

	public void deleteContRemark(String id) {
		contRemarkDao.delete(id);
	}
	
	@Override
	public HibernateDao<ContRemark, String> getDao() {
		return contRemarkDao;
	}
	
}

