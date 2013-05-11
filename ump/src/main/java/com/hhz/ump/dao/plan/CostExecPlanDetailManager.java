package com.hhz.ump.dao.plan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.plan.CostExecPlanDetail;
import com.hhz.ump.entity.plan.CostExecutionPlanMain;
import com.hhz.ump.entity.plan.CostProjectNodeRel;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.GlobalConstants;
import com.hhz.uums.entity.ws.WsPlasOrg;
import com.hhz.uums.entity.ws.WsPlasUser;

@Service
@Transactional
public class CostExecPlanDetailManager extends BaseService<CostExecPlanDetail, String> {

	private static final Logger logger = Logger.getLogger(CostExecPlanDetailManager.class);
	private static final String MAIL_ADMIN = "email_admin";
	public static final String MAIL_TYPE_VERIFY = "verify";

	@Autowired
	private CostExecPlanDetailDao costExecPlanDetailDao;
	@Autowired
	private CostProjectNodeRelManager costProjectNodeRelManager;
	@Autowired
	private CostExecutionPlanNodeManager costExecutionPlanNodeManager;

	@Autowired
	private PlanExecutionPlanManager planExecutionPlanManager;
	
	@Autowired
	private PlanOperationLogManager operationLogManager;
	@Autowired
	private AppAttachFileManager attachManager;
	@Autowired
	private OaEmailBodyManager oaEmailBodyManager;

	// CostExecPlanDetail Manager //
	@Transactional(readOnly = true)
	public CostExecPlanDetail getCostExecPlanDetail(String id) {
		return costExecPlanDetailDao.get(id);
	}

	public List<CostExecPlanDetail> getAllCostExecPlanDetail() {
		return costExecPlanDetailDao.getAll();
	}

	public void saveCostExecPlanDetail(CostExecPlanDetail costExecPlanDetail) {
		PowerUtils.setEmptyStr2Null(costExecPlanDetail);
		costExecPlanDetailDao.save(costExecPlanDetail);
	}

	public void deleteCostExecPlanDetail(String id) {
		costExecPlanDetailDao.delete(id);
	}

	@Override
	public HibernateDao<CostExecPlanDetail, String> getDao() {
		return costExecPlanDetailDao;
	}

	/**
	 * 根据节点搜索与该节点关联的详情列表
	 * 
	 * @param nodeIds
	 * @return
	 */
	public List<CostExecPlanDetail> getDetailsByProjPlanNodes(String[] nodeIds) {
		List<CostExecPlanDetail> result;

		if (nodeIds == null || nodeIds.length == 0) {
			result = new ArrayList<CostExecPlanDetail>();
		} else {
			String hql = "from CostExecPlanDetail t where t.costProjectNodeRel.costProjectNodeRelId in (";
			StringBuilder sb = new StringBuilder();
			int i = 0;
			int length = nodeIds.length;
			for (String id : nodeIds) {
				sb.append("'" + id + "'");
				if (i < length - 1) {
					sb.append(",");
				}
				i++;
			}
			hql += sb.toString();
			hql += ")";

			Map<String, Object> params = new HashMap<String, Object>();
			result = this.getDao().find(hql, params);
		}

		return result;
	}

	/**
	 * 新增计划详细信息
	 * 
	 * @param scheduleEndDate
	 * @param realEndDate
	 * @param planDetailTempId
	 * @param projNodeId
	 * @param projPlanCd
	 * @throws Exception 
	 */
	public void addPlanDetailInfo(String scheduleEndDate, String realEndDate, String planDetailTempId,
			String projNodeId, String projPlanCd) throws Exception {

		CostExecPlanDetail detail = new CostExecPlanDetail();
		CostProjectNodeRel nodeRel = costProjectNodeRelManager.getEntity(projNodeId);
		detail.setCostProjectNodeRel(nodeRel);
		detail.setExecutionPlanCd(projPlanCd);
		detail.setInfoConfirmedFlg("0");
		detail.setStatus(DictContants.COST_PLAN_DETAIL_STATUS_NOT_COMPLETED);

		String format = "yyyy-MM-dd";
		detail.setScheduleEndDate(DateOperator.parse(scheduleEndDate, format));
		detail.setRealEndDate(DateOperator.parse(realEndDate, format));

		this.saveCostExecPlanDetail(detail);

		// 保存操作记录
		String projCd = nodeRel.getCostExecutionPlanMain().getProjectCd();
		String nodeName = costExecutionPlanNodeManager.getNodeNameByCd(nodeRel.getNodeCd());
		String planName = planExecutionPlanManager.getPlanNameByPlanCd(projPlanCd);
		String operationLog = "新增计划详情: " + nodeName + "-" + planName;
		String operationType = PlanOperationLogManager.OPERATION_TYPE_ADD;
		operationLogManager.addPlanLog(detail.getCostProjectNodeRel().getPlanTypeCd(), detail.getCostExecPlanDetailId(), nodeName + "-" + planName, operationType,
				projCd, operationLog);

		attachManager.updateTmpFile(planDetailTempId, detail.getCostExecPlanDetailId());

		// 记录附件操作日志
		List<AppAttachFile> attachList = attachManager.getAttachFilesByEntityIdAndModuleCd(detail
				.getCostExecPlanDetailId(), "execPlan");
		if (attachList.size() > 0) {
			StringBuilder fnames = new StringBuilder();
			for (AppAttachFile f : attachList) {
				fnames.append(f.getRealFileName() + ",");
			}

			operationLog = "上传附件：" + fnames.toString();
			operationType = PlanOperationLogManager.OPERATION_TYPE_UPLOADATTACH;
			
			operationLogManager.addPlanLog(detail.getCostProjectNodeRel().getPlanTypeCd(), detail.getCostExecPlanDetailId(), nodeName + "-" + planName,
					operationType, projCd, operationLog);
		}
	}

	/**
	 * 保存已经存在的计划详情信息
	 * 
	 * @param scheduleEndDate
	 * @param realEndDate
	 * @param planDetailId
	 * @param projNodeId
	 * @param projPlanCd
	 * @param planDetailStatus:0:未完成、1:完成、2：审核完成
	 * @throws Exception 
	 */
	public void svPlanDetailInfo(String scheduleEndDate, String realEndDate, String planDetailId, String projNodeId,
			String projPlanCd, String planDetailStatus) throws Exception {

		CostExecPlanDetail detail = this.getEntity(planDetailId);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String oriScheDuleEdDate = detail.getScheduleEndDate() == null ? "" : df.format(detail.getScheduleEndDate());
		String oriRealEdDate = detail.getRealEndDate() == null ? "" : df.format(detail.getRealEndDate());
		String oriStatus = detail.getStatus();

		if (oriScheDuleEdDate.equals(scheduleEndDate) && oriRealEdDate.equals(realEndDate)
				&& oriStatus.equals(planDetailStatus))
			return;

		String format = "yyyy-MM-dd";
		detail.setScheduleEndDate(DateOperator.parse(scheduleEndDate, format));
		detail.setRealEndDate(DateOperator.parse(realEndDate, format));
		detail.setStatus(planDetailStatus);
		this.saveCostExecPlanDetail(detail);

		// 保存操作记录
		CostProjectNodeRel nodeRel = costProjectNodeRelManager.getEntity(projNodeId);
		String projCd = nodeRel.getCostExecutionPlanMain().getProjectCd();
		String nodeName = costExecutionPlanNodeManager.getNodeNameByCd(nodeRel.getNodeCd());
		String planName = planExecutionPlanManager.getPlanNameByPlanCd(projPlanCd);
		String operationType = PlanOperationLogManager.OPERATION_TYPE_UPDATE;
		String operationLog = "";

		if (!oriScheDuleEdDate.equals(scheduleEndDate == null ? "" : scheduleEndDate)) {
			operationLog = "计划完成日期被更新为 \"" + scheduleEndDate + "\"";
		}

		if (!oriRealEdDate.equals(realEndDate == null ? "" : realEndDate)) {
			operationLog = "实际完成日期被更新为\"" + realEndDate + "\"";
		}

		if (!oriStatus.equals(planDetailStatus)) {
			Map<String, String> statusMap = this.buildPlanDetailStatusMap();
			operationLog = "计划详情状态被更新为 \"" + statusMap.get(planDetailStatus) + "\"";
		}

		if (StringUtils.isNotBlank(operationLog)) {
			operationLogManager.addPlanLog(nodeRel.getPlanTypeCd(), planDetailId, nodeName + "-" + planName, operationType, projCd,
					operationLog);
		}
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
		CostExecPlanDetail detail = this.getEntity(planDetailId);
		detail.setStatus(newStatus);
		this.saveCostExecPlanDetail(detail);

		Map<String, String> statusMap = this.buildPlanDetailStatusMap();
		CostProjectNodeRel nodeRel = detail.getCostProjectNodeRel();
		String nodeName = costExecutionPlanNodeManager.getNodeNameByCd(nodeRel.getNodeCd());
		String planName = planExecutionPlanManager.getPlanNameByPlanCd(detail.getExecutionPlanCd());
		CostExecutionPlanMain planMain = nodeRel.getCostExecutionPlanMain();
		String projectCd = planMain.getProjectCd();
		String operationLog = "计划状态被更新为 \"" + statusMap.get(newStatus) + "\"";
		operationLogManager.addPlanLog(detail.getCostProjectNodeRel().getPlanTypeCd(), planDetailId, nodeName + "-" + planName,
				PlanOperationLogManager.OPERATION_TYPE_UPDATE, projectCd, operationLog);

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

		CostExecPlanDetail detail = this.getEntity(planDetailId);
		sendRemindEmail(detail, mailType);

		return true;
	}

	/**
	 * 构造邮件信息并发送
	 * 
	 * @param planDetail
	 * @param mailType
	 */
	private void sendRemindEmail(CostExecPlanDetail planDetail, String mailType) {
		CostProjectNodeRel nodeRel = planDetail.getCostProjectNodeRel();
		String nodeName = costExecutionPlanNodeManager.getNodeNameByCd(nodeRel.getNodeCd());
		String planName = planExecutionPlanManager.getPlanNameByPlanCd(planDetail.getExecutionPlanCd());
		CostExecutionPlanMain planMain = nodeRel.getCostExecutionPlanMain();
		String projectName = planMain.getProjectName();

		Set<String> toUiids = new HashSet<String>();
		String[] ids = null;

		if (MAIL_TYPE_VERIFY.equalsIgnoreCase(mailType)) {
			List<WsPlasUser> users = PlasCache.getUserListByRoleCd(GlobalConstants.A_COSTP_SUP_ADMIN);
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
			String subject = "【成本计划提醒】-" + projectName + "项目-" + nodeName + "节点-" + planName;
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
	private String buildMailBody(CostExecPlanDetail planDetail, String mailType) {
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
	public CostExecPlanDetail getPlanDetailPerNodeRelIdAndPlanCd(String nodeRelId, String planCd) {
		String hql = "from CostExecPlanDetail where costProjectNodeRel.costProjectNodeRelId = :nodeRelId and executionPlanCd = :planCd";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nodeRelId", nodeRelId);
		params.put("planCd", planCd);
		List<CostExecPlanDetail> result = this.find(hql, params);

		if (result.size() == 0)
			throw new IllegalArgumentException("找不到指定的计划详情信息.");

		if (result.size() > 1)
			throw new RuntimeException("一个节点和一个计划CD最多确定一个计划详情");

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

		CostExecPlanDetail pd = this.getEntity(planDetailId);
		pd.setInfoConfirmedFlg("1");
		saveCostExecPlanDetail(pd);

		CostProjectNodeRel nodeRel = pd.getCostProjectNodeRel();
		String nodeName = costExecutionPlanNodeManager.getNodeNameByCd(nodeRel.getNodeCd());
		String planName = planExecutionPlanManager.getPlanNameByPlanCd(pd.getExecutionPlanCd());
		CostExecutionPlanMain planMain = nodeRel.getCostExecutionPlanMain();
		String projectCd = planMain.getProjectCd();
		String operationLog = "计划详情信息已被审核确认";
		operationLogManager.addPlanLog(nodeRel.getPlanTypeCd(), planDetailId, nodeName + "-" + planName,
				PlanOperationLogManager.OPERATION_TYPE_UPDATE, projectCd, operationLog);

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
					.append(" update CostExecPlanDetail pd ")
					.append("    set pd.infoConfirmedFlg = :infoConfirmedFlg " )
					.append("  where pd.infoConfirmedFlg = :oriInfoConfirmedFlg " )
					.append("    and pd.costProjectNodeRel.costProjectNodeRelId in  (" )
					.append("        select costProjectNodeRelId " )
					.append("          from CostProjectNodeRel ")
					.append("         where costExecutionPlanMain.projectCd=:projCd and planTypeCd = :planTypeCd")
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
		m.put(DictContants.COST_PLAN_DETAIL_STATUS_NOT_COMPLETED, "未完成");
		m.put(DictContants.COST_PLAN_DETAIL_STATUS_COMPLETED, "预完成");
		m.put(DictContants.COST_PLAN_DETAIL_STATUS_CONFIRM_COMPLETED, "审核确认");
		return m;
	}

	/**
	 * 每日分析所有的成本计划，找到满足定时发送周期邮件条件的节点，给该节点的项目联系人和该节点的主责方的负责人发送提醒邮件
	 */
	public void daylyRemind() {
		List<CostExecPlanDetail> plans = findPlansShouldBeReminded();
		for (CostExecPlanDetail p : plans) {
			sendRemindEmail(p, "dayly");
		}
	}

	/**
	 * 找到所有待提醒的成本计划 提醒规则：从几天起两周后未完成的节点
	 * 
	 * @return
	 */
	public List<CostExecPlanDetail> findPlansShouldBeReminded() {
		Date today = DateOperator.truncDate(new Date());
		Date twoWeeksLater = DateOperator.addDays(today, 14);

		String hql = "from CostExecPlanDetail pd where pd.status != 2 and pd.scheduleEndDate <= :twoWeeksLater";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("twoWeeksLater", twoWeeksLater);
		List<CostExecPlanDetail> plans = this.find(hql, params);
		List<CostExecPlanDetail> result = new ArrayList<CostExecPlanDetail>();

		for (CostExecPlanDetail p : plans) {
			Date d = p.getScheduleEndDate();
			long diff = DateOperator.compareDays(d, twoWeeksLater);
			if (diff % 7 == 0) {
				result.add(p);
			}
		}

		return result;
	}

	/**
	 * 搜索某项目某业态的成本计划清单
	 * 1.只要控制节点 2.到当日应完成却未完成 (红色-延期) 或  今后一年需完成的节点(蓝色-待完成)
	 * @param projectCd  项目编号
	 * @param planTypeCd  项目编号
	 * @param executionPlanCd 业态编号
	 * @return
	 */
	public List<CostExecPlanDetail> getSortedStatPlanNodeDetail(String projectCd,String planTypeCd,String executionPlanCd) {
		if (StringUtils.isBlank(projectCd))
			return new ArrayList<CostExecPlanDetail>();

		String hql = new StringBuffer()
					.append("from CostExecPlanDetail d ")
					.append(" where  d.executionPlanCd = :executionPlanCd ")
					.append("   and  d.costProjectNodeRel.costExecutionPlanMain.projectCd=:projectCd ")
					.append("   and  d.costProjectNodeRel.controlNodeFlg = :controlNodeFlg ")
					.append("   and  d.status = :notCompleted")
					.append("   and  d.costProjectNodeRel.planTypeCd = :planTypeCd ")
					.append("   and  d.costProjectNodeRel.deletedFlg != :deletedFlg  ")//d.costProjectNodeRel.deletedFlg is null or
					.append("   and  d.scheduleEndDate is not null ")
					.append("   and (d.realEndDate     is     null or d.realEndDate >= :currentDate) ")
					.append("order by d.costProjectNodeRel.nodeCd asc")
		.toString();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("executionPlanCd", executionPlanCd);
		params.put("projectCd", projectCd);
		params.put("planTypeCd", planTypeCd);
		params.put("controlNodeFlg", CostProjectNodeRelManager.CONTROL_NODE_FLG_ENABLE);
		params.put("notCompleted", DictContants.COST_PLAN_DETAIL_STATUS_NOT_COMPLETED);
		params.put("deletedFlg", CostProjectNodeRelManager.DELETED_FLG_Y);
		params.put("currentDate",  java.sql.Date.valueOf(DateOperator.formatDate(new Date(),"yyyy-MM-dd")));
		

		List<CostExecPlanDetail> list = getDao().find(hql, params);
		Collections.sort(list, new Comparator<CostExecPlanDetail>() {
			public int compare(CostExecPlanDetail n1, CostExecPlanDetail n2) {
				String cd1 = n1.getCostProjectNodeRel().getNodeCd();
				String cd2 = n2.getCostProjectNodeRel().getNodeCd();
				return Integer.parseInt(cd1) - Integer.parseInt(cd2);
			}
		});
		return list;
	}

	
	/**
	 * 获取成本计划明细简要信息
	 * @param planDetailId
	 * @return
	 */
	public Map<String, String> getDetailBriefMap(String planDetailId){

		Map<String,String>  returnMap = new HashMap<String,String>();
		if(StringUtils.isBlank(planDetailId))
			return returnMap;
		
		CostExecPlanDetail detail = getEntity(planDetailId);
		if(detail == null)
			return returnMap;
		
		String projectCd = detail.getCostProjectNodeRel().getCostExecutionPlanMain().getProjectCd();
		String projectName = detail.getCostProjectNodeRel().getCostExecutionPlanMain().getProjectName();
		String planName = planExecutionPlanManager.getPlanNameByPlanCd(detail.getExecutionPlanCd());
		String nodeName = detail.getCostProjectNodeRel().getNodeName();
		
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
}