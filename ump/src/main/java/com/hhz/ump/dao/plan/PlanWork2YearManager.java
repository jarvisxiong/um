package com.hhz.ump.dao.plan;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.PlanWork2;
import com.hhz.ump.entity.plan.PlanWork2Month;
import com.hhz.ump.entity.plan.PlanWork2Year;
import com.hhz.ump.web.plan.PlanWork2Action;

@Service
@Transactional
public class PlanWork2YearManager extends BaseService<PlanWork2Year, String> {
	@Autowired
	private PlanWork2YearDao planWork2YearDao;
	@Autowired
	private PlanWork2Manager planWork2Manager;
	@Autowired
	private PlanWork2MonthManager planWork2MonthManager;

	private boolean if_nextMonth_has;

	/*
	 * ifDelete:是否年计划列表中的删除动作
	 * ifBack:是否年计划列表中的还原动作
	 */
	public void savePlanWork2Year(PlanWork2Year planWork2Year,String ifDelete,String ifBack) {
		PowerUtils.setEmptyStr2Null(planWork2Year);
		planWork2YearDao.save(planWork2Year);
		
		/*
		String planWork2YearId = planWork2Year.getPlanWork2YearId();
		int planYear = planWork2Year.getPlanYear().intValue();
		String centerCd = planWork2Year.getCenterCd();
		if(null==planWork2Year.getDetailStep()||"null".equalsIgnoreCase(planWork2Year.getDetailStep())){
			planWork2Year.setDetailStep("");
		}
		String content = "【"+planWork2Year.getWorkTarget()+"】"+planWork2Year.getDetailStep();
		String serialNumber = planWork2Year.getSerialNumber();
		BigDecimal serialOrder = planWork2Year.getSerialOrder();
		if_nextMonth_has = false;	//是否前个月有记录，用来判断非本月完成的记录
		
		boolean bIfBack = false;
		if("true".equalsIgnoreCase(ifBack)){
			bIfBack = true;
		}
		if(!"true".equalsIgnoreCase(ifDelete)){
			if(null!=planWork2Year.getWeightPoint12() && planWork2Year.getWeightPoint12()>0){
				addPlanWork2ByYear(planWork2YearId,planYear,12,centerCd,content,serialNumber,serialOrder,bIfBack);
			}else{
				deletePlanWork2ByYear(planWork2YearId,planYear,12);
			}
			if(null!=planWork2Year.getWeightPoint11() && planWork2Year.getWeightPoint11()>0){
				addPlanWork2ByYear(planWork2YearId,planYear,11,centerCd,content,serialNumber,serialOrder,bIfBack);
			}else{
				deletePlanWork2ByYear(planWork2YearId,planYear,11);
			}
			if(null!=planWork2Year.getWeightPoint10() && planWork2Year.getWeightPoint10()>0){
				addPlanWork2ByYear(planWork2YearId,planYear,10,centerCd,content,serialNumber,serialOrder,bIfBack);
			}else{
				deletePlanWork2ByYear(planWork2YearId,planYear,10);
			}
			if(null!=planWork2Year.getWeightPoint9() && planWork2Year.getWeightPoint9()>0){
				addPlanWork2ByYear(planWork2YearId,planYear,9,centerCd,content,serialNumber,serialOrder,bIfBack);
			}else{
				deletePlanWork2ByYear(planWork2YearId,planYear,9);
			}
			if(null!=planWork2Year.getWeightPoint8() && planWork2Year.getWeightPoint8()>0){
				addPlanWork2ByYear(planWork2YearId,planYear,8,centerCd,content,serialNumber,serialOrder,bIfBack);
			}else{
				deletePlanWork2ByYear(planWork2YearId,planYear,8);
			}
			if(null!=planWork2Year.getWeightPoint7() && planWork2Year.getWeightPoint7()>0){
				addPlanWork2ByYear(planWork2YearId,planYear,7,centerCd,content,serialNumber,serialOrder,bIfBack);
			}else{
				deletePlanWork2ByYear(planWork2YearId,planYear,7);
			}
			if(null!=planWork2Year.getWeightPoint6() && planWork2Year.getWeightPoint6()>0){
				addPlanWork2ByYear(planWork2YearId,planYear,6,centerCd,content,serialNumber,serialOrder,bIfBack);
			}else{
				deletePlanWork2ByYear(planWork2YearId,planYear,6);
			}
			if(null!=planWork2Year.getWeightPoint5() && planWork2Year.getWeightPoint5()>0){
				addPlanWork2ByYear(planWork2YearId,planYear,5,centerCd,content,serialNumber,serialOrder,bIfBack);
			}else{
				deletePlanWork2ByYear(planWork2YearId,planYear,5);
			}
			if(null!=planWork2Year.getWeightPoint4() && planWork2Year.getWeightPoint4()>0){
				addPlanWork2ByYear(planWork2YearId,planYear,4,centerCd,content,serialNumber,serialOrder,bIfBack);
			}else{
				deletePlanWork2ByYear(planWork2YearId,planYear,4);
			}
			if(null!=planWork2Year.getWeightPoint3() && planWork2Year.getWeightPoint3()>0){
				addPlanWork2ByYear(planWork2YearId,planYear,3,centerCd,content,serialNumber,serialOrder,bIfBack);
			}else{
				deletePlanWork2ByYear(planWork2YearId,planYear,3);
			}
			if(null!=planWork2Year.getWeightPoint2() && planWork2Year.getWeightPoint2()>0){
				addPlanWork2ByYear(planWork2YearId,planYear,2,centerCd,content,serialNumber,serialOrder,bIfBack);
			}else{
				deletePlanWork2ByYear(planWork2YearId,planYear,2);
			}
			if(null!=planWork2Year.getWeightPoint1() && planWork2Year.getWeightPoint1()>0){
				addPlanWork2ByYear(planWork2YearId,planYear,1,centerCd,content,serialNumber,serialOrder,bIfBack);
			}else{
				deletePlanWork2ByYear(planWork2YearId,planYear,1);
			}
		}else if("true".equalsIgnoreCase(ifDelete)){
			deletePlanWork2ByYear(planWork2YearId,planYear,12);
			deletePlanWork2ByYear(planWork2YearId,planYear,11);
			deletePlanWork2ByYear(planWork2YearId,planYear,10);
			deletePlanWork2ByYear(planWork2YearId,planYear,9);
			deletePlanWork2ByYear(planWork2YearId,planYear,8);
			deletePlanWork2ByYear(planWork2YearId,planYear,7);
			deletePlanWork2ByYear(planWork2YearId,planYear,6);
			deletePlanWork2ByYear(planWork2YearId,planYear,5);
			deletePlanWork2ByYear(planWork2YearId,planYear,4);
			deletePlanWork2ByYear(planWork2YearId,planYear,3);
			deletePlanWork2ByYear(planWork2YearId,planYear,2);
			deletePlanWork2ByYear(planWork2YearId,planYear,1);
		}
		*/
	}

	/*
	 * 根据年计划新增月计划中的年度计划类别
	 */
	public void addPlanWork2ByYear(String planWork2YearId,int planYear, int planMonth, String centerCd, String content,String serialNumber,BigDecimal serialOrder,boolean ifBack){
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_planWork2YearId", planWork2YearId));
		List<PlanWork2> planWork2s = planWork2Manager.find(filters);
		PlanWork2 planWork2 = new PlanWork2();
		if(null==planWork2s || 0==planWork2s.size()){
			//如果没有记录，就新增一条记录
			planWork2.setPlanWork2YearId(planWork2YearId);
			planWork2.setPlanYear(new BigDecimal(planYear));
			planWork2.setPlanMonth(new BigDecimal(planMonth));
			planWork2.setPlanType(new BigDecimal(1));
			planWork2.setCenterCd(centerCd);
			planWork2.setContent(content);
			planWork2.setSerialNumber(serialNumber);
			int mySerialOrder = planWork2Manager.getMaxNo(centerCd,planYear,planMonth);
			planWork2.setSerialOrder(new BigDecimal(mySerialOrder));
			planWork2.setTargetDate(DateOperator.getLastDayOfMonth12(planYear, planMonth));
			planWork2Manager.save(planWork2);
		}else{
			planWork2 = (PlanWork2)planWork2s.get(0);
		}
		
		List<PropertyFilter> filters2 = new ArrayList<PropertyFilter>();
		filters2.add(new PropertyFilter("EQS_planWork2YearId", planWork2YearId));
		filters2.add(new PropertyFilter("EQM_planWork2Months.planYear", planYear));
		filters2.add(new PropertyFilter("EQM_planWork2Months.planMonth", planMonth));
		
		List<PlanWork2> planWork2s2 = planWork2Manager.find(filters2);
		if(null==planWork2s2 || 0==planWork2s2.size()){
			PlanWork2Month pm = new PlanWork2Month();
			pm.setPlanYear(new BigDecimal(planYear));
			pm.setPlanMonth(new BigDecimal(planMonth));
			if(if_nextMonth_has){
				//下个月有记录，那么这个月就是非本月完成的记录
				pm.setStatusCd(PlanWork2Action.STATUS_CD_NO_THIS);
			}else{
				pm.setStatusCd(PlanWork2Action.STATUS_CD_JINXINGZHONG);
			}
			pm.setPlanWork2(planWork2);
			planWork2MonthManager.savePlanWork2Month(pm);
		}else if(ifBack){
			//如果有记录，要是年计划中的还原动作，才能还原中心计划中的记录
			List<PlanWork2Month> pms = planWork2s2.get(0).getPlanWork2Months();
			for(PlanWork2Month pm : pms){
				if(planYear==pm.getPlanYear().intValue()
						&&planMonth==pm.getPlanMonth().intValue()){
					if(if_nextMonth_has){
						//下个月有记录，那么这个月就是非本月完成的记录
						pm.setStatusCd(PlanWork2Action.STATUS_CD_NO_THIS);
					}else{
						pm.setStatusCd(PlanWork2Action.STATUS_CD_JINXINGZHONG);
					}
					planWork2MonthManager.savePlanWork2Month(pm);
				}
			}
		}
		if_nextMonth_has = true;
	}
	
	/*
	 * 根据年计划删除月计划中的年度计划类别
	 */
	public void deletePlanWork2ByYear(String planWork2YearId,int planYear, int planMonth){
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_planWork2YearId", planWork2YearId));
		filters.add(new PropertyFilter("EQM_planWork2Months.planYear", planYear));
		filters.add(new PropertyFilter("EQM_planWork2Months.planMonth", planMonth));
		
		//搜索未确认，进行中，申请删除的记录
		//filters.add(new PropertyFilter("INA_statusCd", new Object[] { "0", "1", "3" }));
		
		List<PlanWork2> planWork2s = planWork2Manager.find(filters);
		if(null!=planWork2s && 0!=planWork2s.size()){
			//如果有记录，就删除(该状态为“删除”)
			for(PlanWork2 planWork2:planWork2s){
				//planWork2.setStatusCd("5");
				List<PlanWork2Month> pms = planWork2.getPlanWork2Months();
				for(PlanWork2Month pm : pms){
					if(planYear==pm.getPlanYear().intValue()
							&&planMonth==pm.getPlanMonth().intValue()){
						pm.setStatusCd(PlanWork2Action.STATUS_CD_SHANGCHU);
						planWork2MonthManager.savePlanWork2Month(pm);
					}
				}
				//planWork2Manager.save(planWork2);
			}
		}
		if_nextMonth_has = false;
	}
	
	public void deletePlanWork2Year(String id) {
		planWork2YearDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlanWork2Year, String> getDao() {
		return planWork2YearDao;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public int getMaxNo(String centerCd,int planYear) {
		// String deptBizCd = wsUaapUser.getOrgBizCd();
		StringBuilder hql = new StringBuilder();
		hql.append("select max(serialOrder) from PlanWork2Year ")
			.append(" where centerCd=? and planYear="+planYear);

		// "SELECT count(*) FROM WorkplanRole WHERE deptCd =? ";
		List lstResult = planWork2YearDao.find(hql.toString(), centerCd);
		int sn = 0;
		try{
			if(null!=lstResult && 0!=lstResult.size()){
				sn = ((BigDecimal) lstResult.get(0)).intValue();
			}
		}catch(Exception e){
		}
		sn++;
		return sn;
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String getScheSeriNum(String entityName, String orgCode) {
		// String deptBizCd = wsUaapUser.getOrgBizCd();
		String hql = "from " + entityName + " where centerCd=?";

		// "SELECT count(*) FROM WorkplanRole WHERE deptCd =? ";

		long sn = countHqlResult(hql, orgCode);
		sn++;
		DateFormat format = new SimpleDateFormat("MMdd");
		Date date = new Date();
		String rtn = orgCode + "-" + format.format(date) + "-" + sn;
		return rtn;
	}
	
}

