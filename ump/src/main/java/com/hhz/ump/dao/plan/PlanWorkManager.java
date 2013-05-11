package com.hhz.ump.dao.plan;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.jbpm.JbpmTaskCandidateManager;
import com.hhz.ump.dao.jbpm.JbpmTaskManager;
import com.hhz.ump.dao.oa.OaEmailBodyManager;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.jbpm.JbpmTaskCandidate;
import com.hhz.ump.entity.plan.PlanWork;
import com.hhz.ump.entity.plan.PlanWorkMessage;
import com.hhz.ump.entity.plan.PlanWorkSnap;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.web.plan.PlanWorkAction;
import com.hhz.uums.entity.ws.WsPlasOrg;

@Service
@Transactional
public class PlanWorkManager extends BaseService<PlanWork, String> {
	@Autowired
	private PlanWorkDao planWorkDao;
	
	@Autowired
	private OaEmailBodyManager oaEmailBodyManager;

	@Autowired
	private AppAttachFileManager appAttachFileManager;

	@Autowired
	private PlanWorkSnapManager planWorkSnapManager;

	@Autowired
	private JbpmTaskManager jbpmTaskManager;

	@Autowired
	private JbpmTaskCandidateManager jbpmTaskCandidateManager;

	private void update2JbpmTask(PlanWork planWork) {
		JbpmTask jbpmTask = jbpmTaskManager.getByEntityId(planWork.getPlanWorkId());
		if (planWork.getStatusCd().equals(PlanWorkAction.STATUS_CD_JINXINGZHONG)
				|| planWork.getStatusCd().equals(PlanWorkAction.STATUS_CD_SHENQINGSHANCHU)) {
			if (jbpmTask == null) {
				jbpmTask = new JbpmTask();
				jbpmTask.setModuleCd("planWork");
				jbpmTask.setModuleName("中心月计划");
				jbpmTask.setDeptCd(planWork.getOrgCd());
				jbpmTask.setUserCd(planWork.getCreator());
				jbpmTask.setUserName(CodeNameUtil.getUserNameByCd(planWork.getCreator()));
				jbpmTask.setJbpmId(planWork.getPlanWorkId());
			}
			jbpmTask.setJbpmCd(planWork.getSerialNumber() + planWork.getSerialOrder());
			jbpmTask.setApplyDate(planWork.getTargetDate());
			jbpmTask.setStatusCd(planWork.getStatusCd());
			jbpmTask.setRemark(planWork.getContent());
			jbpmTaskManager.saveJbpmTask(jbpmTask);
			jbpmTaskCandidateManager.delete(jbpmTask.getJbpmTaskCandidates());
			if (planWork.getOrgCd() != null) {
				WsPlasOrg org = PlasCache.getOrgByCd(planWork.getOrgCd());
			    JbpmTaskCandidate jbpmTaskCandidate = new JbpmTaskCandidate();
				jbpmTaskCandidate.setUserCd( org.getOrgMgrId());
				jbpmTaskCandidate.setUserName(CodeNameUtil.getUserNameByCd( org.getOrgMgrId()));
				jbpmTaskCandidate.setJbpmTask(jbpmTask);
				jbpmTaskCandidateManager.saveJbpmTaskCandidate(jbpmTaskCandidate);
//				String[] approveUserCds = planWork.getPrincipal().split(";");
//				for (String approveCd : approveUserCds) {
//					JbpmTaskCandidate jbpmTaskCandidate = new JbpmTaskCandidate();
//					jbpmTaskCandidate.setUserCd(approveCd);
//					jbpmTaskCandidate.setUserName(CodeNameUtil.getUserNameByCd(approveCd));
//					jbpmTaskCandidate.setJbpmTask(jbpmTask);
//					jbpmTaskCandidateManager.saveJbpmTaskCandidate(jbpmTaskCandidate);
//				}
			}
		} else {
			if (jbpmTask != null) {
				jbpmTaskManager.delete(jbpmTask);
			}
		}
	}

	public void save(PlanWork planWork) {
		planWorkDao.save(planWork);
	}
	
	public void savePlanWork(PlanWork planWork) {
		PowerUtils.setEmptyStr2Null(planWork);
		planWorkDao.save(planWork);
		update2JbpmTask(planWork);
	}

	public void savePlanWork(PlanWork planWork, String entityTmpId) {
		PowerUtils.setEmptyStr2Null(planWork);
		savePlanWork(planWork);
		if (StringUtils.isNotEmpty(entityTmpId)) {
			appAttachFileManager.updateTmpFile(entityTmpId, PlanWork.class.getSimpleName(), planWork.getPlanWorkId());
		}
	}

	public void deletePlanWork(String id) {
		planWorkDao.delete(id);
		jbpmTaskManager.deleteByEntityId(id);
	}

	@Override
	public HibernateDao<PlanWork, String> getDao() {
		return planWorkDao;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int getMaxNo(String orgCode) {
		StringBuilder hql = new StringBuilder();
		hql.append("select max(serialOrder) from PlanWork ").append("where (statusCd in ('0', '1', '2', '3')) and orgCd = ? ");
		List lstResult = planWorkDao.find(hql.toString(), orgCode);
		int sn = 0;
		try {
			if (null != lstResult && 0 != lstResult.size()) {
				sn = ((BigDecimal) lstResult.get(0)).intValue();
			}
		} catch (Exception e) {
		}
		sn++;
		return sn;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getScheSeriNum(String entityName, String orgCode) {
		// String deptBizCd = wsUaapUser.getOrgBizCd();
		String hql = "from " + entityName + " where orgCd=?";

		// "SELECT count(*) FROM WorkplanRole WHERE deptCd =? ";

		long sn = countHqlResult(hql, orgCode);
		sn++;
		DateFormat format = new SimpleDateFormat("MMdd");
		Date date = new Date();
		String rtn = orgCode + "-" + format.format(date) + "-" + sn;
		return rtn;
	}

	/**
	 * 确认
	 * 
	 * @param centerCd
	 * @param planYear
	 * @param planWeek
	 * @throws Exception
	 */
	public void confirm(String centerCd) throws Exception {
		Date now=new Date();
		Short planYear = (short) DateOperator.getYear(now);
		Short planWeek = (short) DateOperator.getWeekOfYear(now);
		Short planMonth= (short) DateOperator.getMonth12(now);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_orgCd", centerCd));
		filters.add(new PropertyFilter("INA_statusCd", new Object[] { "0", "1", "2", "3" }));
		List<PlanWork> planWorks = find(filters);
		List<PlanWorkSnap> planWorkSnaps = new ArrayList<PlanWorkSnap>();
		for (PlanWork planWork : planWorks) {
			PlanWorkSnap workSnap = new PlanWorkSnap();
			PropertyUtils.copyProperties(workSnap, planWork);
			workSnap.setPlanYear(planYear);
			workSnap.setPlanWeek(planWeek);
			workSnap.setPlanMonth(planMonth);
			planWorkSnaps.add(workSnap);
			if (StringUtils.equals(planWork.getStatusCd(), PlanWorkAction.STATUS_CD_WANCHENG)) {
				planWork.setStatusCd(PlanWorkAction.STATUS_CD_YINCANG);
			}
			savePlanWork(planWork);
		}
		planWorkSnapManager.savePlanWorkSnaps(planWorkSnaps);
	}

	/**
	 * 解锁
	 * 
	 * @param centerCd
	 * @param planYear
	 * @param planWeek
	 * @throws Exception
	 */
	public void unConfirm(String centerCd, Short planYear, Short planMonth) throws Exception {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQs_planYear", planYear));
		filters.add(new PropertyFilter("EQs_planMonth", planMonth));
		filters.add(new PropertyFilter("EQS_orgCd", centerCd));
		List<PlanWorkSnap> planWorkSnaps = planWorkSnapManager.find(filters);
		List<String> lstPlanWorkId = PowerUtils.getProptyArray(planWorkSnaps, "planWorkId");
		Object[] planWorkids = new Object[lstPlanWorkId.size()];
		lstPlanWorkId.toArray(planWorkids);
		filters.clear();
		filters.add(new PropertyFilter("INA_planWorkId", planWorkids));
		filters.add(new PropertyFilter("EQS_statusCd", PlanWorkAction.STATUS_CD_YINCANG));
		List<PlanWork> planWorks = find(filters);
		for (PlanWork planWork : planWorks) {
			planWork.setStatusCd(PlanWorkAction.STATUS_CD_WANCHENG);
			savePlanWork(planWork);
		}
		planWorkSnapManager.delete(planWorkSnaps);
	}
	
	//当前总任务数
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int getCountAll(String orgCode) {
//		Date now_date = new Date();
//		String search_created_date = DateOperator.formatDate(now_date, "yyyy-MM")+"-01";
		StringBuilder hql = new StringBuilder();
		hql.append("select count(*) from PlanWork ").append("where orgCd = ? and statusCd in ('0', '1', '2', '3')");
		// and createdDate>=to_date('"+search_created_date+"','yyyy-mm-dd')
		List lstResult = planWorkDao.find(hql.toString(), orgCode);
		int returnInt = 0;
		try {
			returnInt = ((Long)lstResult.get(0)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnInt;
	}
	//这个月新增的任务完成数
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int getCountCompleted(String orgCode) {
		Date now_date = new Date();
		String search_created_date = DateOperator.formatDate(now_date, "yyyy-MM")+"-01";
		StringBuilder hql = new StringBuilder();
		hql.append("select count(*) from PlanWork ")
			.append(" where orgCd = ? and createdDate>=to_date('"+search_created_date+"','yyyy-mm-dd')")
			.append(" and statusCd like '3' ");
		List lstResult = planWorkDao.find(hql.toString(), orgCode);
		int returnInt = 0;
		try {
			returnInt = ((Long)lstResult.get(0)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnInt;
	}
	//来自上个月的任务数
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int getCountFromPrev(String orgCode) {
		Date now_date = new Date();
		Date search_date = DateOperator.addMonthes(now_date, -1);
		int search_year = DateOperator.getYear(search_date);
		int search_month = DateOperator.getMonth12(search_date);
		StringBuilder hql = new StringBuilder();
		hql.append("select count(*) from PlanWorkSnap ")
			.append(" where orgCd = ? and planYear="+search_year+" and planMonth="+search_month)
			.append(" and statusCd in ('0', '1', '2')");
		List lstResult = planWorkDao.find(hql.toString(), orgCode);
		int returnInt = 0;
		try {
			returnInt = ((Long)lstResult.get(0)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnInt;
	}
	//来自上个月的完成的任务数
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int getCountFromPrevCompleted(String orgCode) {
		Map<String, Object> map = new HashMap<String ,Object>();
		Date now_date = new Date();
		Date search_date = DateOperator.addMonthes(now_date, -1);
		int search_year = DateOperator.getYear(search_date);
		int search_month = DateOperator.getMonth12(search_date);
		
		StringBuilder hql = new StringBuilder();
		hql.append("select count(*) from PLAN_WORK_SNAP a left join PLAN_WORK b on a.PLAN_WORK_ID=b.PLAN_WORK_ID")
			.append(" where a.ORG_CD like '"+orgCode+"' and a.PLAN_YEAR="+search_year+" and a.PLAN_MONTH="+search_month)
			.append(" and b.STATUS_CD like '3'");
		List lstResult = planWorkDao.findBySql(hql.toString(), map);
		int returnInt = 0;
		try {
			returnInt = ((BigDecimal)lstResult.get(0)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnInt;
	}
	
	/**
	 * 发送提醒邮件
	 * @param meeting
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public boolean sendRemindEmail(PlanWork planWork,String sender_cd) throws SQLException, IOException {
	    if (planWork == null)
			throw new IllegalArgumentException("会议记录不能为空");
	    
	    Set<String> toUiids = new HashSet<String>();
	    
		WsPlasOrg org = PlasCache.getOrgByCd(planWork.getOrgCd());
	    String resPerson = org.getOrgMgrId();
	    String[] ids = null;
	    if (StringUtils.isNotBlank(resPerson)) {
		ids = resPerson.split(";");
		for (String id : ids) {
		    if (StringUtils.isNotBlank(id)) {
			toUiids.add(id);
		    }
		}
	    }
	    
	    
	    if (toUiids.size() > 0) {
		String content = planWork.getContent();
		
		String subject = "【执行总裁会议提醒】" + planWork.getSerialNumber()+planWork.getSerialOrder() + ":" + content.trim();
		
		oaEmailBodyManager.sendData2Email(subject, buildMailBody(planWork, content), sender_cd, "0", toUiids.toArray(new String[toUiids.size()]));
	    }
	    
	    return true;
	}
	
	/**
	 * 构造邮件体
	 */
	private String buildMailBody(PlanWork planWork, String content) throws SQLException, IOException {
	    StringBuilder mailBody = new StringBuilder();
	    
	    mailBody.append("<div style='font-size: 14px; line-height: 25px'><a href='#' style='text-decoration: underline; color: #000;' onclick='" +
	    		"parent.TabUtils.newTab(\"planWork\",\"中心月计划\",parent._ctx+\"/plan/plan-work!getAllPlan.action?opened_entityid="
	    		+planWork.getPlanWorkId()+"\",true);'>点击查看详细信息>></a></div>");
	    
	    mailBody.append("<div style='font-size: 14px; line-height: 25px'>任务内容：" + content + "</div>");
	    mailBody.append("<div style='font-size: 14px; line-height: 25px'>目标时间：" + DateOperator.formatDate(planWork.getTargetDate(), "yyyy-MM-dd") + "</div>");
	    
	    mailBody.append("<div style='font-size: 14px;'>回复：");
	    
	    List<PlanWorkMessage> planWorkMessages = planWork.getPlanWorkMessages();
	    if (planWorkMessages.size() == 0) {
	        mailBody.append("暂无回复</div>");
	    } else {
	        mailBody.append("<br/><ul style='list-style:none; margin-left: 40px;'>");
	        for (PlanWorkMessage c : planWorkMessages) {
	            mailBody.append("<li style='line-height:18px;'><strong>" + CodeNameUtil.getUserNameByCd(c.getCreator()) + "(" + DateOperator.formatDate(c.getCreatedDate(), "yyyy-MM-dd") + ")：</strong>");
	            String comment = c.getContent();
	            mailBody.append(comment + "</li>");
	        }
	        mailBody.append("</ul></div>");
	    }
	    
	    return mailBody.toString();
	}
}
