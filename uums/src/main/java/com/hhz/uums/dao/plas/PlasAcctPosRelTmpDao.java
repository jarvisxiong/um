package com.hhz.uums.dao.plas;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.uums.entity.plas.PlasAcctPosRelTmp;

@Repository
public class PlasAcctPosRelTmpDao extends HibernateDao<PlasAcctPosRelTmp, String> {
	public PlasAcctPosRelTmp getEntity(String acctId,String sysPosId) {
		String hql = " select distinct t from PlasAcctPosRelTmp t where t.plasAcctId = ? and t.plasSysPositionId =?";
		List result = createQuery(hql, acctId,sysPosId).list();
		if (result == null || result.size() == 0)
			return null;
		else
			return (PlasAcctPosRelTmp)result.get(0);
	}
}

