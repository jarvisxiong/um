package com.hhz.ump.dao.plan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.DateParser;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.oa.OaEmailBodyManager;
import com.hhz.ump.entity.plan.PlanExecDetailMess;
import com.hhz.ump.entity.plan.PlanExecPlanDetail;
import com.hhz.ump.entity.plan.PlanExecutionPlanMain;
import com.hhz.ump.entity.plan.PlanProjectNodeRel;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasUser;

@Service
@Transactional
public class PlanExecPlanDetailManager extends BaseService<PlanExecPlanDetail, String> {

	private static final Logger logger = Logger.getLogger(PlanExecPlanDetailManager.class);
	private static final String MAIL_ADMIN = "email_admin";
	public static final String MAIL_TYPE_VERIFY = "verify";

	@Autowired
	private PlanExecPlanDetailDao planExecPlanDetailDao;
	@Autowired
	private PlanProjectNodeRelManager nodeRelManager;
	@Autowired
	private PlanExecutionPlanNodeManager planNodeManager;
	@Autowired
	private PlanExecDetailMessManager planExecDetailMessManager;
//	@Autowired
//	private PlanOperationLogManager operationLogManager;
	@Autowired
	private PlanExecutionPlanManager planManager;
	@Autowired
	private AppAttachFileManager attachManager;
	@Autowired
	private OaEmailBodyManager oaEmailBodyManager;

	// PlanExecPlanDetail Manager //
	@Transactional(readOnly = true)
	public PlanExecPlanDetail getPlanExecPlanDetail(String id) {
		return planExecPlanDetailDao.get(id);
	}
	
	public PlanExecPlanDetail getPlanExecPlanDetail(String projNodeId,String projPlanCd) {
		if(StringUtils.isNotBlank(projNodeId) && StringUtils.isNotBlank(projPlanCd)){
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder sb = new StringBuilder("from PlanExecPlanDetail where");

			sb.append(" planProjectNodeRel.planProjectNodeRelId = :projNodeId and executionPlanCd=:projPlanCd order by createdDate asc");
		    params.put("projNodeId", projNodeId);
		    params.put("projPlanCd", projPlanCd);
		    
			List<PlanExecPlanDetail> arr = this.getDao().find(sb.toString(), params);
			if(null!=arr && arr.size()>0)
				return arr.get(0);
			else
				return null;
		} else
			return null;
	}

	public List<PlanExecPlanDetail> getAllPlanExecPlanDetail() {
		return planExecPlanDetailDao.getAll();
	}

	public void savePlanExecPlanDetail(PlanExecPlanDetail planExecPlanDetail) {
		PowerUtils.setEmptyStr2Null(planExecPlanDetail);
		planExecPlanDetailDao.save(planExecPlanDetail);
	}

	public void deletePlanExecPlanDetail(String id) {
		planExecPlanDetailDao.delete(id);
	}

	@Override
	public HibernateDao<PlanExecPlanDetail, String> getDao() {
		return planExecPlanDetailDao;
	}

	/**
	 * 根据节点搜索与该节点关联的详情列表
	 * @param nodeIds
	 * @param treeTypeCd
	 * @return
	 */
	public List<PlanExecPlanDetail> getDetailsByProjPlanNodes(String[] nodeIds,String searchPlans){
		return getDetailsByProjPlanNodes(nodeIds,null,searchPlans,null,null,null,null,null,null,null,null,null);
	}
	public List<PlanExecPlanDetail> getDetailsByProjPlanNodes(String[] nodeIds,String pointLevel,String searchPlans
			,String filter_GED_scheduleStartDate,String filter_LED_scheduleStartDate
			,String filter_GED_scheduleEndDate,String filter_LED_scheduleEndDate
			,String filter_GED_realEndDate,String filter_LED_realEndDate,String search_status,String nowViewStyle,String nowResOrgNames) {
		
		if (nodeIds == null || nodeIds.length == 0)
			return new ArrayList<PlanExecPlanDetail>();
		else {
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder sb = new StringBuilder("from PlanExecPlanDetail where");
			
		    if(StringUtils.isNotBlank(pointLevel)){
				sb.append(" planProjectNodeRel.pointLevel <= :pointLevel and ");
			    params.put("pointLevel", Long.parseLong(pointLevel));
		    }
			if(StringUtils.isNotBlank(searchPlans)){
				if(!"Export".equalsIgnoreCase(searchPlans)){
					String final_plans = "";
					String[] arr_plans = searchPlans.split(",");
					for(String str_plan : arr_plans){
						final_plans += "'"+str_plan+"',";
					}
					if(!"".equalsIgnoreCase(final_plans)){
						final_plans = final_plans.substring(0, final_plans.length()-1);
					}
					sb.append(" executionPlanCd in ("+final_plans+") and ");
				}
			} else
				return new ArrayList<PlanExecPlanDetail>();
			
			if(StringUtils.isNotBlank(filter_GED_scheduleStartDate)){
				sb.append(" scheduleStartDate >= to_date(:filter_GED_scheduleStartDate,'YYYY-MM-DD') and ");
				params.put("filter_GED_scheduleStartDate", filter_GED_scheduleStartDate);
			}
			if(StringUtils.isNotBlank(filter_LED_scheduleStartDate)){
				sb.append(" scheduleStartDate <= to_date(:filter_LED_scheduleStartDate,'YYYY-MM-DD') and ");
				params.put("filter_LED_scheduleStartDate", filter_LED_scheduleStartDate);
			}
			if(StringUtils.isNotBlank(filter_GED_scheduleEndDate)){
				sb.append(" scheduleEndDate >= to_date(:filter_GED_scheduleEndDate,'YYYY-MM-DD') and ");
				params.put("filter_GED_scheduleEndDate", filter_GED_scheduleEndDate);
			}
			if(StringUtils.isNotBlank(filter_LED_scheduleEndDate)){
				sb.append(" scheduleEndDate <= to_date(:filter_LED_scheduleEndDate,'YYYY-MM-DD') and ");
				params.put("filter_LED_scheduleEndDate", filter_LED_scheduleEndDate);
			}
			if(StringUtils.isNotBlank(filter_GED_realEndDate)){
				sb.append(" realEndDate >= to_date(:filter_GED_realEndDate,'YYYY-MM-DD') and ");
				params.put("filter_GED_realEndDate", filter_GED_realEndDate);
			}
			if(StringUtils.isNotBlank(filter_LED_realEndDate)){
				sb.append(" realEndDate <= to_date(:filter_LED_realEndDate,'YYYY-MM-DD') and ");
				params.put("filter_LED_realEndDate", filter_LED_realEndDate);
			}
			if(StringUtils.isNotBlank(search_status) && !"999".equalsIgnoreCase(search_status)){
				if("100".equalsIgnoreCase(search_status)){
					//未确认
					sb.append(" status = '0' and infoConfirmedFlg is not null and infoConfirmedFlg='0' and");
				}else if("101".equalsIgnoreCase(search_status)){
					//过期
					sb.append(" status = '1' and scheduleEndDate <= to_date(:nowdate,'YYYY-MM-DD') and");
					params.put("nowdate", DateOperator.formatDate(DateOperator.getDateNow(), "yyyy-MM-dd"));
				}else{
					sb.append(" status = :search_status and");
					params.put("search_status", search_status);
				}
			}
			
		    if(StringUtils.isNotBlank(nowViewStyle) && "1".equalsIgnoreCase(nowViewStyle)){
		    	//如果是默认状态，显示 1:过期记录；2：计划完成时间在这个月内的进行中任务； 3：计划开始时间在这个月和下个月的记录
		    	Date nextMonthDate = DateOperator.addMonthes(DateOperator.getDateNow(), 1);
		    	Date nextMonth2Date = DateOperator.addMonthes(DateOperator.getDateNow(), 2);
		    	sb.append(" ((status=0 or status=1) and infoConfirmedFlg=1 ");
		    	sb.append(" and (scheduleEndDate<:date1 or scheduleStartDate<:date2)) and");
			    params.put("date1", nextMonthDate);
			    params.put("date2", nextMonth2Date);
		    }
		    if(StringUtils.isNotBlank(nowResOrgNames)){
		    	String[] rons = nowResOrgNames.split(";");
		    	if(null!=rons&&rons.length>0){
		    		sb.append(" (");
			    	for(int i=0;i<rons.length;i++){
			    		String ron = rons[i];
			    		if(0==i){
			    			sb.append("planProjectNodeRel.resOrgName like :resOrgName"+i);
			    		}else{
			    			sb.append(" or planProjectNodeRel.resOrgName like :resOrgName"+i);
			    		}
				  		params.put("resOrgName"+i, "%"+ron+"%");
			    	}
			    	sb.append(") and ");
		    	}
		    }
			
			sb.append(" planProjectNodeRel.planProjectNodeRelId in (");
			int i = 0;
			int length = nodeIds.length;
			for (String id : nodeIds) {
				sb.append("'" + id + "'");
				if (i < length - 1) {
					sb.append(",");
				}
				i++;
			}
			sb.append(")");
			return this.getDao().find(sb.toString(), params);
		}
	}

	/**
	 * 新增计划详细信息
	 * @param scheduleStartDate
	 * @param scheduleEndDate
	 * @param realEndDate
	 * @param planDetailTempId
	 * @param projNodeId
	 * @param projPlanCd
	 * @throws Exception 
	 */
	public PlanExecPlanDetail addPlanDetailInfo(String scheduleStartDate, String scheduleEndDate, String realEndDate, String planDetailTempId,
			String projNodeId, String projPlanCd, boolean activeBl) throws Exception {
		PlanExecPlanDetail detail = new PlanExecPlanDetail();
		PlanProjectNodeRel nodeRel = nodeRelManager.getEntity(projNodeId);
		detail.setPlanProjectNodeRel(nodeRel);
		detail.setExecutionPlanCd(projPlanCd);
		detail.setActiveBl(activeBl);
		detail.setInfoConfirmedFlg("0");
		detail.setStatus(DictContants.EXEC_PLAN_DETAIL_STATUS_NOT_COMPLETED);

		String format = "yyyy-MM-dd";
		detail.setScheduleStartDate(DateOperator.parse(scheduleStartDate, format));
		detail.setScheduleEndDate(DateOperator.parse(scheduleEndDate, format));
		detail.setRealEndDate(DateOperator.parse(realEndDate, format));

		this.savePlanExecPlanDetail(detail);
		attachManager.updateTmpFile(planDetailTempId, detail.getPlanExecPlanDetailId());
		
		PlanExecDetailMess pm = new PlanExecDetailMess();
		pm.setContent("[新增]"+DateOperator.formatDate(detail.getScheduleStartDate(), "yyyy-MM-dd")+"~"+DateOperator.formatDate(detail.getScheduleEndDate(), "yyyy-MM-dd"));
		pm.setPlanExecPlanDetail(detail);
		pm.setSysType("1");
		planExecDetailMessManager.savePlanExecDetailMess(pm);
		
		return detail;
		// 保存操作记录
//		String projCd = nodeRel.getPlanExecutionPlanMain().getProjectCd();
//		String nodeName = planNodeManager.getNodeNameByCd(nodeRel.getNodeCd());
//		String planName = planManager.getPlanNameByPlanCd(projPlanCd);
//		String operationLog = "新增计划详情: " + nodeName + "-" + planName;
//		String operationType = PlanOperationLogManager.OPERATION_TYPE_ADD;
//		
//		operationLogManager.addPlanLog(detail.getPlanProjectNodeRel().getPlanTypeCd(), detail.getPlanExecPlanDetailId(), nodeName + "-" + planName, operationType,
//				projCd, operationLog);


		// 记录附件操作日志
//		List<AppAttachFile> attachList = attachManager.getAttachFilesByEntityIdAndModuleCd(detail
//				.getPlanExecPlanDetailId(), "execPlan");
//		if (attachList.size() > 0) {
//			StringBuilder fnames = new StringBuilder();
//			for (AppAttachFile f : attachList) {
//				fnames.append(f.getRealFileName() + ",");
//			}
//
//			operationLog = "上传附件：" + fnames.toString();
//			operationType = PlanOperationLogManager.OPERATION_TYPE_UPLOADATTACH;
//			operationLogManager.addPlanLog(detail.getPlanProjectNodeRel().getPlanTypeCd(), detail.getPlanExecPlanDetailId(), nodeName + "-" + planName,
//					operationType, projCd, operationLog);
//		}
	}

	/**
	 * 保存已经存在的计划详情信息
	 * @param scheduleStartDate
	 * @param scheduleEndDate
	 * @param realEndDate
	 * @param planDetailId
	 * @param projNodeId
	 * @param projPlanCd
	 * @param planDetailStatus:0:未完成、1:完成、2：审核完成
	 * @throws Exception 
	 */
	public void svPlanDetailInfo(String scheduleStartDate, String scheduleEndDate, String realEndDate, String planDetailId, String projNodeId,
			String projPlanCd, String planDetailStatus, boolean activeBl) throws Exception {
		PlanExecPlanDetail detail = this.getEntity(planDetailId);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String oriScheDuleStDate = detail.getScheduleStartDate() == null ? "" : df.format(detail.getScheduleStartDate());
		String oriScheDuleEdDate = detail.getScheduleEndDate() == null ? "" : df.format(detail.getScheduleEndDate());
		String oriRealEdDate = detail.getRealEndDate() == null ? "" : df.format(detail.getRealEndDate());
		String oriStatus = detail.getStatus();

		//如果计划完成时间，实际完成时间，状态，计划开始时间，是否可用都没有修改，就不更新记录
		if (oriScheDuleEdDate.equals(scheduleEndDate) && oriRealEdDate.equals(realEndDate)
				&& oriStatus.equals(planDetailStatus) && oriScheDuleStDate.equals(scheduleStartDate)
				&& activeBl==detail.getActiveBl())
			return;

		String format = "yyyy-MM-dd";
		detail.setScheduleStartDate(DateOperator.parse(scheduleStartDate, format));
		detail.setScheduleEndDate(DateOperator.parse(scheduleEndDate, format));
		detail.setRealEndDate(DateOperator.parse(realEndDate, format));
		detail.setStatus(planDetailStatus);
		detail.setActiveBl(activeBl);
		this.savePlanExecPlanDetail(detail);
		
		// 保存操作记录
		String operationLog = "";

		if (!oriScheDuleStDate.equals(scheduleStartDate == null ? "" : scheduleStartDate)) {
			operationLog += "计划开始日期由\""+oriScheDuleStDate+"\"被更新为 \"" + scheduleStartDate + "\"。";
		}
		
		if (!oriScheDuleEdDate.equals(scheduleEndDate == null ? "" : scheduleEndDate)) {
			operationLog += "计划完成日期由\""+oriScheDuleEdDate+"\"被更新为 \"" + scheduleEndDate + "\"。";
		}

		if (!oriRealEdDate.equals(realEndDate == null ? "" : realEndDate)) {
			operationLog += "实际完成日期由\""+oriRealEdDate+"\"被更新为\"" + realEndDate + "\"。";
		}

		if (!oriStatus.equals(planDetailStatus)) {
			Map<String, String> statusMap = this.buildPlanDetailStatusMap();
			operationLog = "节点状态由\""+statusMap.get(oriStatus)+"\"被更新为 \"" + statusMap.get(planDetailStatus) + "\"。";
		}

		PlanExecDetailMess pm = new PlanExecDetailMess();
		pm.setContent("[修改]"+operationLog);
		pm.setPlanExecPlanDetail(detail);
		pm.setSysType("1");
		planExecDetailMessManager.savePlanExecDetailMess(pm);
//		if (StringUtils.isNotBlank(operationLog)) {
//			operationLogManager.addPlanLog(nodeRel.getPlanTypeCd(), planDetailId, nodeName + "-" + planName, operationType, projCd,
//					operationLog);
//		}
	}

	/**
	 * 改变计划详情的状态
	 * 
	 * @param planDetailId
	 * @param newStatus
	 * @return
	 * @throws Exception 
	 */
	public boolean updatePlanDetailStatus(String planDetailId, String newStatus) throws Exception {
		if (StringUtils.isBlank(planDetailId)) {
			logger.warn("计划详情ID不能为空");
			return false;
		}

		if (StringUtils.isBlank(newStatus)) {
			logger.warn("新的状态不能为空");
			return false;
		}
		PlanExecPlanDetail detail = this.getEntity(planDetailId);
		detail.setStatus(newStatus);
		if("-1".equalsIgnoreCase(newStatus)){
			detail.setStatus("0");
			detail.setInfoConfirmedFlg("0");
		}
		this.savePlanExecPlanDetail(detail);

		Map<String, String> statusMap = this.buildPlanDetailStatusMap();
		PlanProjectNodeRel nodeRel = detail.getPlanProjectNodeRel();
		String nodeName = planNodeManager.getNodeNameByCd(nodeRel.getNodeCd());
		String planName = planManager.getPlanNameByPlanCd(detail.getExecutionPlanCd());
		PlanExecutionPlanMain planMain = nodeRel.getPlanExecutionPlanMain();
		String projectCd = planMain.getProjectCd();
		String operationLog = "计划状态被更新为 \"" + statusMap.get(newStatus) + "\"";
//		operationLogManager.addPlanLog(detail.getPlanProjectNodeRel().getPlanTypeCd(), planDetailId, nodeName + "-" + planName,
//				PlanOperationLogManager.OPERATION_TYPE_UPDATE, projectCd, operationLog);
		PlanExecDetailMess pm = new PlanExecDetailMess();
		pm.setContent("[修改]"+operationLog);
		pm.setPlanExecPlanDetail(detail);
		pm.setSysType("1");
		planExecDetailMessManager.savePlanExecDetailMess(pm);

		return true;
	}

	/**
	 * 发送提醒审核确认的邮件
	 * 
	 * @param planDetailId
	 * @return
	 */
	public boolean remindPlanDetail(String planDetailId, String mailType) {
		if (StringUtils.isBlank(planDetailId)) {
			logger.warn("计划详情ID不能为空");
			return false;
		}

		PlanExecPlanDetail detail = this.getEntity(planDetailId);
		sendRemindEmail(detail, mailType);

		return true;
	}

	/**
	 * 构造邮件信息并发送
	 * 
	 * @param planDetail
	 * @param mailType
	 */
	private void sendRemindEmail(PlanExecPlanDetail planDetail, String mailType) {
		PlanProjectNodeRel nodeRel = planDetail.getPlanProjectNodeRel();
		String nodeName = planNodeManager.getNodeNameByCd(nodeRel.getNodeCd());
		String planName = planManager.getPlanNameByPlanCd(planDetail.getExecutionPlanCd());
		PlanExecutionPlanMain planMain = nodeRel.getPlanExecutionPlanMain();
		String projectName = planMain.getProjectName();

		Set<String> toUiids = new HashSet<String>();
		String[] ids = null;

		if (MAIL_TYPE_VERIFY.equalsIgnoreCase(mailType)) {
			List<WsPlasUser> users = PlasCache.getUserListByRoleCd(GlobalConstants.A_EXECPLAN_SUP_ADMIN);
			for (WsPlasUser u : users) {
				toUiids.add(u.getUiid());
			}
		} else {
			// 把项目联系人作为提醒对象
			String projReminders = planMain.getReminder();
			if (StringUtils.isNotBlank(projReminders)) {
				ids = projReminders.split(";");
				for (String id : ids) {
					if (StringUtils.isNotBlank(id)) {
						toUiids.add(id);
					}
				}
			}
			// 把主责方机构的负责人作为提醒对象
			if (StringUtils.isNotBlank(nodeRel.getResOrgCd())) {
				WsPlasOrg org = PlasCache.getOrgByCd(nodeRel.getResOrgCd());
				String nodeReminders = org.getOrgMgrId();
				if (StringUtils.isNotBlank(nodeReminders)) {
					ids = nodeReminders.split(";");
					for (String id : ids) {
						if (StringUtils.isNotBlank(id)) {
							toUiids.add(id);
						}
					}
				}
			}
		}

		if (toUiids.size() > 0) {
			String subject = "【执行计划提醒】-" + projectName + "项目-" + nodeName + "节点-" + planName;
			oaEmailBodyManager.sendData2Email(subject, buildMailBody(planDetail, mailType), MAIL_ADMIN, "0", toUiids
					.toArray(new String[toUiids.size()]));
		}

	}

	/**
	 * 构造邮件体
	 * 
	 * @param planDetail
	 * @return
	 */
	private String buildMailBody(PlanExecPlanDetail planDetail, String mailType) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, String> statusMap = this.buildPlanDetailStatusMap();

		String scheduleEdDate = planDetail.getScheduleEndDate() == null ? "无" : df.format(planDetail
				.getScheduleEndDate());
		String realEdDate = planDetail.getRealEndDate() == null ? "无" : df.format(planDetail.getRealEndDate());

		StringBuilder body = new StringBuilder();
		body.append("<div>计划完成时间为：" + scheduleEdDate + "</div>");
		body.append("<div>实际完成时间为：" + realEdDate + "</div>");
		body.append("<div>当前状态：" + statusMap.get(planDetail.getStatus()) + "</div>");

		if (MAIL_TYPE_VERIFY.equalsIgnoreCase(mailType)) {
			body.append("<div style='color: red;'>请审核确认</div>");
		}

		return body.toString();
	}

	/**
	 * 根据nodeRelID和PlanCd获取计划详情信息
	 * 
	 * @param nodeRelId
	 * @param planCd
	 * @return
	 */
	public PlanExecPlanDetail getPlanDetailPerNodeRelIdAndPlanCd(String nodeRelId, String planCd) {
		String hql = "from PlanExecPlanDetail where planProjectNodeRel.planProjectNodeRelId = :nodeRelId and executionPlanCd = :planCd";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nodeRelId", nodeRelId);
		params.put("planCd", planCd);
		List<PlanExecPlanDetail> result = this.find(hql, params);

		if (result.size() == 0)
			//throw new IllegalArgumentException("找不到指定的计划详情信息.");
			return null;

		if (result.size() > 1)
			//throw new RuntimeException("一个节点和一个计划CD最多确定一个计划详情");
			return null;

		return result.get(0);
	}

	/**
	 * 审核确认计划详情信息
	 * 
	 * @param planDetailId
	 * @return
	 * @throws Exception 
	 */
	public boolean confirmPlanDetailInfo(String planDetailId) throws Exception {
		if (StringUtils.isBlank(planDetailId))
			throw new IllegalArgumentException("计划详情ID不能为空");

		PlanExecPlanDetail pd = this.getEntity(planDetailId);
		pd.setInfoConfirmedFlg("1");
		savePlanExecPlanDetail(pd);
		
		PlanExecDetailMess pm = new PlanExecDetailMess();
		pm.setContent("[确认]");
		pm.setPlanExecPlanDetail(pd);
		pm.setSysType("1");
		planExecDetailMessManager.savePlanExecDetailMess(pm);

//		PlanProjectNodeRel nodeRel = pd.getPlanProjectNodeRel();
//		String nodeName = planNodeManager.getNodeNameByCd(nodeRel.getNodeCd());
//		String planName = planManager.getPlanNameByPlanCd(pd.getExecutionPlanCd());
//		PlanExecutionPlanMain planMain = nodeRel.getPlanExecutionPlanMain();
//		String projectCd = planMain.getProjectCd();
//		String operationLog = "计划详情信息已被审核确认";
//		operationLogManager.addPlanLog(nodeRel.getPlanTypeCd(), planDetailId, nodeName + "-" + planName,
//				PlanOperationLogManager.OPERATION_TYPE_UPDATE, projectCd, operationLog);

		return true;
	}

	/**
	 * 批量确认指定项目的计划的计划信息
	 * 
	 * @param projectCd
	 * @param planTypeCd
	 * 
	 * @return
	 */
	public boolean batchConfirmPlanDetailInfo(String projectCd, String planTypeCd) {

		String hql = new StringBuffer()
					.append(" update PlanExecPlanDetail pd ")
					.append("    set pd.infoConfirmedFlg = :infoConfirmedFlg " )
					.append("  where pd.infoConfirmedFlg = :oriInfoConfirmedFlg " )
					.append("    and pd.planProjectNodeRel.planProjectNodeRelId in  (" )
					.append("        select planProjectNodeRelId " )
					.append("          from PlanProjectNodeRel ")
					.append("         where planExecutionPlanMain.projectCd=:projCd and planTypeCd = :planTypeCd")
					.append("    )")
					.toString();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("planTypeCd", planTypeCd);
		params.put("infoConfirmedFlg", "1");
		params.put("oriInfoConfirmedFlg", "0");
		params.put("projCd", projectCd);
		getDao().batchExecute(hql, params);

		return true;
	}

	/**
	 * 构造计划详情状态Map
	 * 
	 * @return
	 */
	public Map<String, String> buildPlanDetailStatusMap() {
		Map<String, String> m = new HashMap<String, String>();
		m.put(DictContants.EXEC_PLAN_DETAIL_STATUS_NOT_COMPLETED, "未完成");
		m.put(DictContants.EXEC_PLAN_DETAIL_STATUS_COMPLETED, "预完成");
		m.put(DictContants.EXEC_PLAN_DETAIL_STATUS_CONFIRM_COMPLETED, "完成");
		return m;
	}

	/**
	 * 每日分析所有的执行计划，找到满足定时发送周期邮件条件的节点，给该节点的项目联系人和该节点的主责方的负责人发送提醒邮件
	 */
	public void daylyRemind() {
		List<PlanExecPlanDetail> plans = findPlansShouldBeReminded();
		for (PlanExecPlanDetail p : plans) {
			sendRemindEmail(p, "dayly");
		}
	}

	/**
	 * 找到所有待提醒的执行计划 提醒规则：从几天起两周后未完成的节点
	 * 
	 * @return
	 */
	public List<PlanExecPlanDetail> findPlansShouldBeReminded() {
		Date today = DateOperator.truncDate(new Date());
		Date twoWeeksLater = DateOperator.addDays(today, 14);

		String hql = "from PlanExecPlanDetail pd where pd.status != 2 and pd.scheduleEndDate <= :twoWeeksLater";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("twoWeeksLater", twoWeeksLater);
		List<PlanExecPlanDetail> plans = this.find(hql, params);
		List<PlanExecPlanDetail> result = new ArrayList<PlanExecPlanDetail>();
		Date d = null;
		long diff = 0;
		for (PlanExecPlanDetail p : plans) {
			d = p.getScheduleEndDate();
			diff = DateOperator.compareDays(d, twoWeeksLater);
			if (diff % 7 == 0) {
				result.add(p);
			}
		}

		return result;
	}

	/**
	 * 搜索某项目某业态的执行计划清单
	 * 1.只要控制节点 2.到当日应完成却未完成 (红色-延期) 或  今后一年需完成的节点(蓝色-待完成)
	 * @param projectCd  项目编号
	 * @param planTypeCd  项目编号
	 * @param executionPlanCd 业态编号
	 * @return
	 */
	public List<PlanExecPlanDetail> getSortedStatPlanNodeDetail(String projectCd,String planTypeCd,String executionPlanCd) {
		if (StringUtils.isBlank(projectCd))
			return new ArrayList<PlanExecPlanDetail>();

		String hql = new StringBuffer()
					.append("from PlanExecPlanDetail d ")
					.append(" where  d.executionPlanCd = :executionPlanCd ")
					.append("   and  d.planProjectNodeRel.planExecutionPlanMain.projectCd=:projectCd ")
					.append("   and  d.planProjectNodeRel.controlNodeFlg = :controlNodeFlg ")
					.append("   and  d.status = :notCompleted")
					.append("   and  d.planProjectNodeRel.planTypeCd = :planTypeCd ")
					.append("   and  d.planProjectNodeRel.deletedFlg != :deletedFlg  ")//d.planProjectNodeRel.deletedFlg is null or
					.append("   and  d.scheduleEndDate is not null ")
					.append("   and (d.realEndDate     is     null or d.realEndDate >= :currentDate) ")
					.append("order by d.planProjectNodeRel.nodeCd asc")
		.toString();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("executionPlanCd", executionPlanCd);
		params.put("projectCd", projectCd);
		params.put("planTypeCd", planTypeCd);
		params.put("controlNodeFlg", PlanProjectNodeRelManager.CONTROL_NODE_FLG_ENABLE);
		params.put("notCompleted", DictContants.EXEC_PLAN_DETAIL_STATUS_NOT_COMPLETED);
		params.put("deletedFlg", PlanProjectNodeRelManager.DELETED_FLG_Y);
		params.put("currentDate",  java.sql.Date.valueOf(DateOperator.formatDate(new Date(),"yyyy-MM-dd")));
		

		List<PlanExecPlanDetail> list = getDao().find(hql, params);
//		根据nodeRel.nodeCd排序,若nodeCd存在字符则报错
//		Collections.sort(list, new Comparator<PlanExecPlanDetail>() {
//			public int compare(PlanExecPlanDetail n1, PlanExecPlanDetail n2) {
//				String cd1 = n1.getPlanProjectNodeRel().getNodeCd();
//				String cd2 = n2.getPlanProjectNodeRel().getNodeCd();
//				return Integer.parseInt(cd1) - Integer.parseInt(cd2);
//			}
//		});
		return list;
	}
	
	/**
	 * 获取明细简要信息
	 * @param planDetailId
	 * @return
	 */
	public Map<String, String> getDetailBriefMap(String planDetailId){

		Map<String,String>  returnMap = new HashMap<String,String>();
		if(StringUtils.isBlank(planDetailId))
			return returnMap;
		
		PlanExecPlanDetail detail = getEntity(planDetailId);
		if(detail == null)
			return returnMap;
		
		String projectCd = detail.getPlanProjectNodeRel().getPlanExecutionPlanMain().getProjectCd();
		String projectName = detail.getPlanProjectNodeRel().getPlanExecutionPlanMain().getProjectName();
		String planName = planManager.getPlanNameByPlanCd(detail.getExecutionPlanCd());
		String nodeCd = detail.getPlanProjectNodeRel().getNodeCd();
		String nodeName = detail.getPlanProjectNodeRel().getNodeName();
		
		returnMap.put("planExecId",planDetailId);
		returnMap.put("projectCd", projectCd);
		returnMap.put("projectName", projectName);
		returnMap.put("planName", planName);
		returnMap.put("nodeName", nodeName);
		returnMap.put("realStartDate", DateParser.formatDate(detail.getRealStartDate(), DateParser.defaultDateFormatter));
		returnMap.put("realEndDate",  DateParser.formatDate(detail.getRealEndDate(), DateParser.defaultDateFormatter));
		returnMap.put("scheduleStartDate", DateParser.formatDate(detail.getScheduleStartDate(), DateParser.defaultDateFormatter));
		returnMap.put("scheduleEndDate", DateParser.formatDate(detail.getScheduleEndDate(), DateParser.defaultDateFormatter));
		returnMap.put("statusCd", detail.getStatus());
		
		return returnMap; 
	}

	/**
	 * 统计执行计划数据
	 * @return
	 */
	public List<Object> getStatData(String yearMonth) {

		String sql = new StringBuffer()
		//按项目分组,显示: 项目名称 总任务数 已完成 未完成 完成率(%)  
		.append(" select distinct plan_execution_plan_main_id,project_cd,project_name,res_org_cd,res_org_name, ")
		.append(" sum(p_completed)             as pc, ")
		.append(" sum(p_delaied)               as pd, ")
		.append(" sum(c_completed)             as cc, ")
		.append(" sum(c_delaied)               as cd, ")
		.append(" sum(p_completed+p_delaied+c_completed+c_delaied) as 应完成, ")
		.append(" sum(p_completed+c_completed) as 已完成, ")
		.append(" sum(p_delaied+c_delaied)     as 未完成, ")
		.append(" sum(p_completed+c_completed-c_delaied) as 实际完成, ")
		.append(" sum((p_completed+c_completed-c_delaied)/(decode((p_completed+p_delaied+c_completed+c_delaied),0,9999,(p_completed+p_delaied+c_completed+c_delaied)))) as 完成率 ")
		.append(" from( ")
		.append("     select t3.plan_execution_plan_main_id,t3.project_cd,t3.project_name, ")
		.append("     t2.res_org_cd,t2.res_org_name,   ")
		.append("     (case when t2.control_node_flg = '0' and (t.schedule_end_date >= t.real_end_date or t.real_end_date is not null) then 1 else 0 end ) as p_completed, ") 
		.append("     (case when t2.control_node_flg = '0' and (t.schedule_end_date <  t.real_end_date or t.real_end_date is     null) then 1 else 0 end ) as p_delaied, ")
		.append("     (case when t2.control_node_flg = '1' and (t.schedule_end_date >= t.real_end_date or t.real_end_date is not null) then 1 else 0 end ) as c_completed,  ")
		.append("     (case when t2.control_node_flg = '1' and (t.schedule_end_date <  t.real_end_date or t.real_end_date is     null) then 1 else 0 end ) as c_delaied ")
		.append("     from plan_exec_plan_detail t ,plan_project_node_rel t2, PLAN_EXECUTION_PLAN_MAIN t3 ")
		.append("     where to_char(t.schedule_end_date,'yyyyMM') = :yearMonth ")
		.append("     and t.plan_project_node_rel_id = t2.plan_project_node_rel_id ")
		.append("     and t2.plan_execution_plan_main_id = t3.plan_execution_plan_main_id ")
		.append(" )t ")
		.append(" group by plan_execution_plan_main_id,project_cd,project_name,res_org_cd,res_org_name ")
		.toString();
					
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("yearMonth", yearMonth);

		return this.getDao().findBySql(sql, map);
	}
}