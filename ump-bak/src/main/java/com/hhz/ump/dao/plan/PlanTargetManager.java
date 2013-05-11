package com.hhz.ump.dao.plan;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.jbpm.JbpmTaskCandidateManager;
import com.hhz.ump.dao.jbpm.JbpmTaskManager;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.jbpm.JbpmTaskCandidate;
import com.hhz.ump.entity.plan.PlanMessage;
import com.hhz.ump.entity.plan.PlanTarget;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.web.plan.PlanTargetAction;
import com.hhz.uums.entity.ws.WsPlasOrg;

@Service
@Transactional
public class PlanTargetManager extends BaseService<PlanTarget, String> {

	@Autowired
	private PlanTargetDao planTargetDao;
	@Autowired
	private PlanMessageDao planMessageDao;
	@Autowired
	private JbpmTaskManager jbpmTaskManager;
	@Autowired
	private JbpmTaskCandidateManager jbpmTaskCandidateManager;
	
	public static final String SYS_STATUS_0 = "0"; 		// 正常记录
	public static final String SYS_STATUS_1="1";//删除记录
	public static final String STATUS_CD_YINCANG = "8"; 		// 隐藏
	public static final String STATUS_CD_SHANCHU = "10"; 		// 删除(系统状态)
	
	public void savePlanTarget(PlanTarget planTarget) {
		PowerUtils.setEmptyStr2Null(planTarget);
		planTargetDao.save(planTarget);
		for(PlanMessage msg : planTarget.getPlanMessages()) {
			if(msg != null && StringUtils.isNotBlank(msg.getContent())) {
				PowerUtils.setEmptyStr2Null(msg);
				msg.setPlanTarget(planTarget);
				planMessageDao.save(msg);
			}
		}
		update2JbpmTask(planTarget);
	}
	
	public void deletePlanTarget(String id) {
		planTargetDao.delete(id);
	}
	
	/*
	 * 自动判断状态，记录到待办事项中
	 */
	private void update2JbpmTask(PlanTarget pt) {
		try{
			JbpmTask jbpmTask = jbpmTaskManager.getByEntityId(pt.getPlanTargetId());
			int year1 = DateOperator.getYear(new Date());
			int month1 = DateOperator.getMonth12(new Date());
			int year2 = year1;
			int month2 = month1+1;
			if(month2>12){
				year2++;
				month2=1;
			}
			//只有满足一定状态，并且是本月或下月，才会进入待办事项，否则删除
			if ((pt.getStatus().equalsIgnoreCase(PlanTargetAction.STATUS_CD_JINXINGZHONG)
							||pt.getStatus().equalsIgnoreCase(PlanTargetAction.STATUS_CD_YUWANCHENG)
							||pt.getStatus().equalsIgnoreCase(PlanTargetAction.STATUS_CD_SUSPEND)
							||pt.getStatus().equalsIgnoreCase(PlanTargetAction.STATUS_CD_NO_ASSESS)
							||pt.getStatus().equalsIgnoreCase(PlanTargetAction.STATUS_CD_SHENQINGSHANCHU))
							&&(year1==pt.getTargetYear()&&month1==pt.getTargetMonth()
									||year2==pt.getTargetYear()&&month2==pt.getTargetMonth())) {
				if (jbpmTask == null) {
					jbpmTask = new JbpmTask();
					jbpmTask.setModuleCd("planTarget");
					jbpmTask.setModuleName("中心月计划");
					jbpmTask.setDeptCd(pt.getCenter());
					jbpmTask.setUserCd(pt.getCreator());
					jbpmTask.setUserName(CodeNameUtil.getUserNameByCd(pt.getCreator()));
					jbpmTask.setJbpmId(pt.getPlanTargetId());
				}
				jbpmTask.setJbpmCd(pt.getPlanNumber());
				jbpmTask.setApplyDate(pt.getTargetDate());
				jbpmTask.setStatusCd(pt.getStatus());
				jbpmTask.setRemark(pt.getContent());
				jbpmTaskManager.saveJbpmTask(jbpmTask);
				jbpmTaskCandidateManager.delete(jbpmTask.getJbpmTaskCandidates());
				if (pt.getCenter() != null) {
					WsPlasOrg wsUaapOrg = PlasCache.getOrgByCd(pt.getCenter());
				    JbpmTaskCandidate jbpmTaskCandidate = new JbpmTaskCandidate();
					jbpmTaskCandidate.setUserCd(wsUaapOrg.getOrgMgrId());
					jbpmTaskCandidate.setUserName(CodeNameUtil.getUserNameByCd( wsUaapOrg.getOrgMgrId()));
					jbpmTaskCandidate.setJbpmTask(jbpmTask);
					jbpmTaskCandidateManager.saveJbpmTaskCandidate(jbpmTaskCandidate);
				}
			} else {
				if (jbpmTask != null) {
					jbpmTaskManager.delete(jbpmTask);
				}
			}
		}catch(Exception e){}
	}


	public PlanTarget getPlanTargetById(String planTargetId) {
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(Struts2Utils.getRequest());
		filters.add(new PropertyFilter("EQS_planTargetId", planTargetId));
		List<PlanTarget> list = this.find(filters);
		return (list != null && list.size() > 0) ? list.get(0) : null;
	}
	
	/***
	 * 根据条件组合搜索中心计划信息
	 * 
	 * @param page 中心计划page
	 * @param plan 高级搜索的条件对象
	 * @return 中心计划信息集合
	 */
	public Page<PlanTarget> findPage(Page<PlanTarget> page, PlanTarget plan) {
		StringBuffer hql = new StringBuffer("from PlanTarget pt where pt.monthFlg='1' ");
		Map<String, Object> params = new HashMap<String, Object>();
		if(plan != null) {
			// 中心
			if(StringUtils.isNotEmpty(plan.getCenter())) {
				//商业集团 显示多个部门中心内容
				if("153".equals(plan.getCenter())){
					hql.append("and pt.center in (:center) ");
					params.put("center", Arrays.asList("155,156,154,157,869,439".split(",")));
	          	}else{
	          		hql.append("and pt.center = :center ");
					params.put("center", plan.getCenter());
	          	}
			}
			// 类型
			if(StringUtils.isNotEmpty(plan.getPlanType())) {
				hql.append("and pt.planType = :planType ");
				params.put("planType", plan.getPlanType());
			}
			// 表示状态
			if(StringUtils.isNotEmpty(plan.getStatus())) {
				hql.append("and pt.status = :status ");
				params.put("status", plan.getStatus());
			}
			// 工作内容
			if(StringUtils.isNotEmpty(plan.getContent())) {
				hql.append("and pt.content like :content ");
				params.put("content", "%" + plan.getContent() + "%");
			}
			// 归属年
			if(plan.getTargetYear() != null) {
				hql.append("and pt.targetYear = :targetYear ");
				params.put("targetYear", plan.getTargetYear());
			}
			// 归属月
			if(plan.getTargetMonth() != null && plan.getTargetMonth() != 0) {
				hql.append("and pt.targetMonth = :targetMonth ");
				params.put("targetMonth", plan.getTargetMonth());
			}

			if(STATUS_CD_YINCANG.equals(plan.getStatus()) == false) { // 没有选择隐藏状态(8)
				// 状态
				hql.append("and pt.status <> " + STATUS_CD_YINCANG + " ");
			}
			if(STATUS_CD_SHANCHU.equals(plan.getStatus()) == false) { // 没有选择删除状态(10)
				// 系统状态
				hql.append("and pt.sysStatusFlg = " + SYS_STATUS_0 + " ");
			}else{//选择删除状态
				hql.append("and pt.sysStatusFlg = " + SYS_STATUS_1 + " ");
			}
		} else {
			// 状态
			hql.append("and pt.status <> " + STATUS_CD_YINCANG + " ");
			// 系统状态
			hql.append("and pt.sysStatusFlg = " + SYS_STATUS_0 + " ");
		}
		hql.append("order by ");
		//新增非本月排序
		hql.append("case when pt.status=7 then 1 else 0 end asc ");
		if(StringUtils.isNotEmpty(page.getOrderBy()) && StringUtils.isNotEmpty(page.getOrder())) {
			String orderStr;
			String order;
			for(int i=0; i<page.getOrderBy().split(",").length; i++) {
				orderStr = page.getOrderBy().split(",")[i];
				order = page.getOrder().split(",")[i];
				if("planType".equals(orderStr)){
					hql.append(",case when pt.planType=1 then 1 when pt.planType=8 then 2 when pt.planType=4 then 3 when pt.planType=6 then 4");
					hql.append(" when pt.planType=2 then 5 end ");
					hql.append(order);
				}else if("targetDate".equals(orderStr)){
					hql.append(",to_char(pt.targetDate,'yyyy-MM-dd')"+" "+order);
				}else if("status".equals(orderStr)){
					hql.append(",case when pt.status=1 then 1 when pt.status=2 then 5 when pt.status=3 then 6 when pt.status=4 then 7");
					hql.append(" when pt.status=5 then 3 when pt.status=6 then 4 when pt.status=9 then 2 end ");
					hql.append(order);
				}else{
					hql.append(", pt." + orderStr + " " +order);
				}			
			}
		}
		return this.findPage(page, hql.toString(), params);
	}
	
	public void findYarForMonth(Page<PlanTarget> page, PlanTarget plan,List<Map<String,String>> monList){
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer("select t.* from plan_target t where 1=1 ");
		sql.append(" and t.year_flg='1' and t.center=:centerCd and t.target_year=:targetYear and t.status in(0,1,2) and t.sys_Status_Flg=:sysStatusFlg");
		params.put("centerCd", plan.getCenter());
		params.put("targetYear", plan.getTargetYear());
		if(StringUtils.isNotBlank(plan.getPlanTargetId())){
			sql.append(" and t.plan_target_id=:planTargetId");
			params.put("planTargetId", plan.getPlanTargetId());
		}
		if(StringUtils.isNotBlank(plan.getStatus())&&"10".equals(plan.getStatus())){
			params.put("sysStatusFlg", "1");
		}else{
			params.put("sysStatusFlg", "0");
		}
		if(StringUtils.isNotBlank(plan.getYearTarget())){
			sql.append(" and t.year_Target like :yearTarget");
			params.put("yearTarget", "%"+plan.getYearTarget()+"%");
		}
		if(StringUtils.isNotBlank(plan.getContent())){
			sql.append(" and t.content like :content");
			params.put("content", "%"+plan.getContent()+"%");
		}
		sql.append(" order by t.sequence_number asc");
		Map<String, Class> mapClazz = new HashMap<String, Class>();
        mapClazz.put("plan", PlanTarget.class);
		Page<PlanTarget> tPage =this.findPageSql(page, sql.toString(),params,mapClazz);
		StringBuffer monHql;
		for(PlanTarget target:tPage.getResult()){
			Map<String,String> targetMap=new HashMap<String,String>();
			targetMap.put("planTargetId", target.getPlanTargetId());
			targetMap.put("sequenceNumber", target.getSequenceNumber());
			targetMap.put("yearTarget", target.getYearTarget());
			targetMap.put("content", target.getContent());
			monHql=new StringBuffer(" select t from PlanTarget t where yearId=:yearId and t.sysStatusFlg=:sysStatusFlg and monthFlg=:monthFlg");
			monHql.append(" order by t.targetMonth asc");
			params = new HashMap<String, Object>();
			params.put("yearId", target.getPlanTargetId());
			params.put("sysStatusFlg", "0");
			params.put("monthFlg", "1");
			List<PlanTarget> tarList =this.find(monHql.toString(), params);
			for(PlanTarget tar:tarList){
				targetMap.put("month"+tar.getTargetMonth(), "1");
			}
			for(int i=1;i<13;i++){
				if(targetMap.get("month"+i)==null){
					targetMap.put("month"+i, "0");
				}
			}
			monList.add(targetMap);
		}
	}

	//更新月度工作计划里面的年计划记录
	public void updateYearForMonth(){
		Map<String, Object> params = new HashMap<String, Object>();
		String sql="select a.plan_target_id from Plan_Target a where a.year_Flg='1'";
		List<Object> idList =this.getDao().findBySql(sql,params);
		for(Object obj:idList){
			//查询plan_work2里面的
			String monSql="select m.PLANWORK2MONTH_ID from plan_work2 w,plan_work2_month m "+
			"where w.plan_work2_id=m.plan_work2_id and w.plan_work2_year_id=:planWork2YearId";
			params = new HashMap<String, Object>();
			params.put("planWork2YearId", obj);
			List<Object> monIdList=this.findBySql(monSql, params);
			for(Object monId:monIdList){
				params = new HashMap<String, Object>();
				params.put("monId", monId);
				Long count =this.countHqlResult("from PlanTarget where planTargetId=:monId", params);
				if(count>0){
					PlanTarget target = this.getEntity((String)monId);
					target.setYearId((String)obj);
					this.savePlanTarget(target);
				}
				
			}
		}
	}
	
	public Long getPlanTargetCount(String centerCd,int year,int month) {
		StringBuffer hql = new StringBuffer("from PlanTarget pt where 1=1 ");
		Map<String, Object> values = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(centerCd)) {
			hql.append("and pt.center = :centerCd ");
			values.put("centerCd", centerCd);
		}
		if(0!=year){
			hql.append(" and pt.targetYear =:targetYear");
			values.put("targetYear",year);
		}
		if(0!=month){
			hql.append(" and pt.targetMonth =:targetMonth");
			values.put("targetMonth", month);
		}
		Long count = countHqlResult(hql.toString(), values);
		return count;
	}

	@Override
	public HibernateDao<PlanTarget, String> getDao() {
		return planTargetDao;
	}
	
}

