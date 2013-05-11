package com.hhz.ump.dao.plan;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.PlanPeriod;

@Service
@Transactional
public class PlanPeriodManager extends BaseService<PlanPeriod, String> {
	@Autowired
	private PlanPeriodDao planPeriodDao;

	@Transactional(propagation = Propagation.SUPPORTS)
	public PlanPeriod getPlanPeriod(Date nowdate, String periodTypeCd) {
		PlanPeriod planPeriod = new PlanPeriod();
		StringBuilder hql = new StringBuilder();
		hql.append("from PlanPeriod ").append("where startDate<=? ").append("and endDate>=?").append("and periodTypeCd=?");

		java.sql.Date date = new java.sql.Date(DateOperator.truncDate(nowdate).getTime());
		List lstResult = planPeriodDao.find(hql.toString(), date, date, periodTypeCd);
		if (null != lstResult && lstResult.size() > 0) {
			planPeriod = (PlanPeriod) lstResult.get(0);
		}
		return planPeriod;
	}

	/*
	 * 根据一个period的periodSerialNumber取得它附近的period
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PlanPeriod getPlanPeriodNear(int periodSerialNumber, String periodTypeCd) {
		PlanPeriod planPeriod = new PlanPeriod();
		StringBuilder hql = new StringBuilder();
		hql.append("from PlanPeriod ").append("where periodSerialNumber=? ").append("and periodTypeCd=?");

		List lstResult = planPeriodDao.find(hql.toString(), periodSerialNumber, periodTypeCd);
		if (null != lstResult && lstResult.size() > 0) {
			planPeriod = (PlanPeriod) lstResult.get(0);
		}
		return planPeriod;
	}

	/*
	 * 取得最大的序号值
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int getMaxPeriodSerialNumber(String periodTypeCd) {
		StringBuilder hql = new StringBuilder();
		hql.append("select max(periodSerialNumber) from PlanPeriod ").append("where periodTypeCd=? ");

		List lstResult = planPeriodDao.find(hql.toString(), periodTypeCd);
		int sn = 0;
		try {
			if (null != lstResult && 0 != lstResult.size()) {
				sn = (Integer) lstResult.get(0);
			}
		} catch (Exception e) {
		}
		sn++;
		return sn;
	}

	/*
	 * 取得最大的年编号数值
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int getMaxYearNumber(String periodTypeCd, int yearStr) {
		StringBuilder hql = new StringBuilder();
		hql.append("select max(yearNumber) from PlanPeriod ").append("where planYear=? ").append("and periodTypeCd=?");

		List lstResult = planPeriodDao.find(hql.toString(), yearStr, periodTypeCd);
		int sn = 0;
		try {
			if (null != lstResult && 0 != lstResult.size()) {
				sn = (Integer) lstResult.get(0);
			}
		} catch (Exception e) {
		}
		sn++;
		return sn;
	}


	public void savePlanPeriod(PlanPeriod planPeriod) {
		PowerUtils.setEmptyStr2Null(planPeriod);
		planPeriodDao.save(planPeriod);
	}

	public void deletePlanPeriod(String id) {
		planPeriodDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<PlanPeriod, String> getDao() {
		return planPeriodDao;
	}

}
