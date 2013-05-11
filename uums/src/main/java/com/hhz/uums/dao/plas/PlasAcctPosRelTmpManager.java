package com.hhz.uums.dao.plas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasAcctPosRelTmp;

@Service
@Transactional
public class PlasAcctPosRelTmpManager extends BaseService<PlasAcctPosRelTmp, String> {
	@Autowired
	private PlasAcctPosRelTmpDao plasAcctPosRelTmpDao;

	public void savePlasAcctPosRelTmp(PlasAcctPosRelTmp plasAcctPosRelTmp) {
		PowerUtils.setEmptyStr2Null(plasAcctPosRelTmp);
		plasAcctPosRelTmpDao.save(plasAcctPosRelTmp);
	}

	public void deletePlasAcctPosRelTmp(String id) {
		plasAcctPosRelTmpDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasAcctPosRelTmp, String> getDao() {
		return plasAcctPosRelTmpDao;
	}
	public PlasAcctPosRelTmp getEntityByAcctId(String acctId) {
		String hql = " select distinct t from PlasAcctPosRelTmp t where t.plasAcctId = ?";
		List result = this.getDao().createQuery(hql, acctId).list();
		if (result == null || result.size() == 0)
			return null;
		else
			return (PlasAcctPosRelTmp)result.get(0);
	}
	public PlasAcctPosRelTmp getEntity(String acctId,String sysPosId) {
		return plasAcctPosRelTmpDao.getEntity(acctId, sysPosId);
	}
}

