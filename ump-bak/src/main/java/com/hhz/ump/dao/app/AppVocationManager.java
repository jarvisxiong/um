package com.hhz.ump.dao.app;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.AppVocation;

@Service
@Transactional
public class AppVocationManager extends BaseService<AppVocation, String> {
	@Autowired
	private AppVocationDao appVocationDao;

	public void saveAppVocation(AppVocation appVocation) {
		PowerUtils.setEmptyStr2Null(appVocation);
		appVocationDao.save(appVocation);
	}

	public void deleteAppVocation(String id) {
		appVocationDao.delete(id);
	}

	@Override
	public HibernateDao<AppVocation, String> getDao() {
		return appVocationDao;
	}

	/**
	 * 搜索是否包含假期、周末
	 * 普通假期
	 * 
	 * @return
	 */
	public boolean isContainHoliday(Date dFrom, Date dTo) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer("from app_vocation v where V.VOCATION_DAY >= :p_from and V.VOCATION_DAY <= :p_To and V.IF_ON_DUTY = 0 ");
		sql.append("or contain_wkend (:p_from, :p_To) = 1");
		Map<String, Object> pram = new HashMap<String, Object>();
		pram.put("p_from", DateOperator.truncDate(dFrom));
		pram.put("p_To", DateOperator.truncDate(dTo));
		long cnt = appVocationDao.countSqlResult(sql.toString(), pram);
		if (cnt > 0) {
			flag = true;
		}
		return flag;
	}
	/**
	 * 判断是否包含重要节日（春节）
	 * @param dFrom
	 * @param dTo
	 * @return
	 */
	public boolean isContainImpDay(Date dFrom, Date dTo) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer("from app_vocation v where V.VOCATION_DAY >= :p_from and V.VOCATION_DAY <= :p_To and V.IF_ON_DUTY = 0 and V.IS_IMPORTANT = 1 ");
		//sql.append("or contain_wkend (:p_from, :p_To) = 1");
		Map<String, Object> pram = new HashMap<String, Object>();
		pram.put("p_from", DateOperator.truncDate(dFrom));
		pram.put("p_To", DateOperator.truncDate(dTo));
		long cnt = appVocationDao.countSqlResult(sql.toString(), pram);
		if (cnt > 0) {
			flag = true;
		}
		return flag;
	}
	/**
	 * 是否假期
	 * 
	 * @return
	 */
	public boolean isHoliday(Date dDate) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer("from app_vocation v where trunc(V.VOCATION_DAY) = :p_date and V.IF_ON_DUTY = 0 ");
		Map<String, Object> pram = new HashMap<String, Object>();
		pram.put("p_date", DateOperator.truncDate(dDate));
		long cnt = appVocationDao.countSqlResult(sql.toString(), pram);
		if (cnt > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 判断是否是假期
	 * 
	 * @param date
	 * @return
	 */
	public boolean isVocation(Date date) {
		boolean flag = false;
		// 搜索假期
		StringBuffer hql = new StringBuffer("from AppVocation v where v.vocationDay=? and v.ifOnDuty=0");
		long cnt = appVocationDao.countHqlResult(hql.toString(), date);
		if (cnt > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 判断是否是工作日
	 * 
	 * @param date
	 * @return
	 */
	public boolean isDuty(Date date) {
		boolean flag = false;
		// 搜索假期
		StringBuffer hql = new StringBuffer("from AppVocation v where v.vocationDay=? and v.ifOnDuty=1");
		long cnt = appVocationDao.countHqlResult(hql.toString(), date);
		if (cnt > 0) {
			flag = true;
		}
		return flag;
	}

	public void saveOrUpdateAppVocations(List<AppVocation> insertedRecords, List<AppVocation> updatedRecords, List<AppVocation> deletedRecords) {
		// TODO Auto-generated method stub
		for(AppVocation appParam : insertedRecords){
			this.saveAppVocation(appParam);
		}
		for(AppVocation appParam : updatedRecords){
			this.saveAppVocation(appParam);
		}
		for(AppVocation appParam : deletedRecords){
			this.delete(appParam);
		}
	}
}
