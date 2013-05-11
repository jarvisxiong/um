package com.hhz.ump.dao.plan;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.PlanWorkSnap;

@Service
@Transactional
public class PlanWorkSnapManager extends BaseService<PlanWorkSnap, String> {
	@Autowired
	private PlanWorkSnapDao planWorkSnapDao;

	public void savePlanWorkSnap(PlanWorkSnap planWorkSnap) {
		PowerUtils.setEmptyStr2Null(planWorkSnap);
		planWorkSnapDao.save(planWorkSnap);
	}

	public void savePlanWorkSnaps(List<PlanWorkSnap> planWorkSnaps) {
		for (PlanWorkSnap planWorkSnap : planWorkSnaps) {
			savePlanWorkSnap(planWorkSnap);
		}
	}

	public boolean isConfirmed(String centerCd,Short planYear,Short planMonth) {
		List<PropertyFilter> filtersSnap = new ArrayList<PropertyFilter>();
		filtersSnap.add(new PropertyFilter("EQs_planYear", planYear));
		filtersSnap.add(new PropertyFilter("EQs_planMonth", planMonth));
		filtersSnap.add(new PropertyFilter("EQS_orgCd", centerCd));
		int cnt = countByPropertyFilter(filtersSnap);
		return cnt > 0;
	}

	public void deletePlanWorkSnap(String id) {
		planWorkSnapDao.delete(id);
	}

	@Override
	public HibernateDao<PlanWorkSnap, String> getDao() {
		return planWorkSnapDao;
	}

}
