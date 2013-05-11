package com.hhz.ump.dao.cont;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cont.ContSettlement;

@Service
@Transactional
public class ContSettlementManager extends BaseService<ContSettlement, String> {
	@Autowired
	private ContSettlementDao contSettlementDao;

	public void saveContSettlement(ContSettlement contSettlement) {
		PowerUtils.setEmptyStr2Null(contSettlement);
		contSettlementDao.save(contSettlement);
	}

	public void deleteContSettlement(String id) {
		contSettlementDao.delete(id);
	}
	
	@Override
	public HibernateDao<ContSettlement, String> getDao() {
		return contSettlementDao;
	}
	
}

