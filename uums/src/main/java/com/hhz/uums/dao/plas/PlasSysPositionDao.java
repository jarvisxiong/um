package com.hhz.uums.dao.plas;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.utils.RandomUtils;
import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.entity.plas.PlasUser;
import com.hhz.uums.utils.DateUtil;

@Repository
public class PlasSysPositionDao extends HibernateDao<PlasSysPosition, String> {
	public PlasSysPosition getEntityBySysPosCd(String sysPosCd) {


		List result = this.createCriteria(PlasSysPosition.class).add(Restrictions.eq("sysPosCd", sysPosCd)).list();
		if (result == null || result.size() == 0)
			return null;
		else
			return (PlasSysPosition) result.get(0);
	}
	public PlasSysPosition savePlasSysPosition(PlasUser plasUser,PlasAcct plasAcct){
		//20110101XXXX  年月日+4为随机码
		String sysPosCd = DateUtil.getStringYearMonthToday()+RandomUtils.generateString(4);
		while(!this.isPropertyUnique("sysPosCd", sysPosCd, sysPosCd)){
			sysPosCd = DateUtil.getStringYearMonthToday()+RandomUtils.generateString(4);
		}
		
		PlasSysPosition sysPos = new PlasSysPosition();
		sysPos.setPlasAcct(plasAcct);
		sysPos.setPlasOrg(plasUser.getPlasOrg());
		sysPos.setSysPosName(plasUser.getWorkDutyDesc());
		sysPos.setSysPosCd(sysPosCd);
		sysPos.setShortName(plasUser.getWorkDutyDesc());
		sysPos.setRecordVersion(0);
		sysPos.setActiveBl(new Boolean(true));//默认设置1-有效
		save(sysPos);
		
		return sysPos;
	}
}

