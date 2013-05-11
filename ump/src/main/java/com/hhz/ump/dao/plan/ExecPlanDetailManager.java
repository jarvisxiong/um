package com.hhz.ump.dao.plan;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.oa.OaAllAttentionManager;
import com.hhz.ump.entity.oa.OaAllAttention;
import com.hhz.ump.entity.plan.ExecPlanDetail;
import com.hhz.ump.entity.plan.ExecPlanDetailMess;
import com.hhz.ump.entity.plan.ExecPlanDetailPlus;
import com.hhz.ump.entity.plan.ExecPlanLayout;
import com.hhz.ump.entity.plan.ExecPlanNode;
import com.hhz.ump.entity.plan.PlanCenterDetailsVO;
import com.hhz.ump.entity.plan.PlanDetailVO;
import com.hhz.ump.util.DictContants;

@Service
@Transactional
public class ExecPlanDetailManager extends BaseService<ExecPlanDetail, String> {
	private static final String MAIL_ADMIN = "email_admin";
	public static final String MAIL_TYPE_VERIFY = "verify";

	@Autowired
	private ExecPlanDetailDao execPlanDetailDao;
	@Autowired
	private ExecPlanNodeManager execPlanNodeManager;
	@Autowired
	private ExecPlanLayoutManager execPlanLayoutManager;
	@Autowired
	private ExecPlanDetailMessManager execPlanDetailMessManager;
	@Autowired
	private ExecPlanLayoutManager planManager;
	@Autowired
	private AppAttachFileManager attachManager;
	@Autowired
	private OaAllAttentionManager oaAllAttentionManager;

	@Transactional(readOnly = true)
	public ExecPlanDetail getExecPlanDetail(String id) {
		return execPlanDetailDao.get(id);
	}
	
	public void saveExecPlanDetail(ExecPlanDetail execPlanDetail) {
		PowerUtils.setEmptyStr2Null(execPlanDetail);
		execPlanDetailDao.save(execPlanDetail);
	}

	public void deleteExecPlanDetail(String id) {
		execPlanDetailDao.delete(id);
	}
	
	@Override
	public HibernateDao<ExecPlanDetail, String> getDao() {
		return execPlanDetailDao;
	}
	
	/*
	 * 根据节点相关信息搜索节点
	 */
	public ExecPlanDetail getExecPlanDetail(String projectCd,String nodeId, String layoutId) {
		if(StringUtils.isNotBlank(projectCd) && StringUtils.isNotBlank(layoutId)){
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder sb = new StringBuilder("from ExecPlanDetail where");

			sb.append(" projectCd = :projectCd and execPlanNode.execPlanNodeId=:nodeId and execPlanLayout.execPlanLayoutId=:layoutId order by createdDate asc");
		    params.put("projectCd", projectCd);
		    params.put("nodeId", nodeId);
		    params.put("layoutId", layoutId);
		    
			List<ExecPlanDetail> arr = this.getDao().find(sb.toString(), params);
			if(null!=arr && arr.size()>0)
				return arr.get(0);
			else
				return null;
		} else
			return null;
	}
	
	/*
	 * 获得节点下属的附加节点信息（商业执行计划(次)主力店）
	 */
	public List<ExecPlanDetailPlus> getListExecPlanDetailPlus(String detailId) {
		if(StringUtils.isNotBlank(detailId) && StringUtils.isNotBlank(detailId)){
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder sb = new StringBuilder("from ExecPlanDetailPlus where");

			sb.append(" execPlanDetailId=:detailId and status!=5 order by createdDate desc");
		    params.put("detailId", detailId);
		    
			List<ExecPlanDetailPlus> arr = this.getDao().find(sb.toString(), params);
			return arr;
		} else
			return null;
	}
	
	/*
	 * 根据项目获得项目下（商业执行计划(次)主力店）的签约/进场数
	 * projectCd:项目的cd
	 * cVarName:变量名称
	 */
	public int getNumberSign(String projectCd, String cVarName) {
		int returnValue=0;
		try{
			StringBuilder sb = new StringBuilder("select count(*) from exec_plan_detail_plus t" +
					" inner join exec_plan_detail s on t.exec_plan_detail_id=s.exec_plan_detail_id" +
					" inner join exec_plan_node r on r.exec_plan_node_id=s.exec_plan_node_id" +
					" where s.project_cd like '"+projectCd+"'");
			if("nMainSigned".equalsIgnoreCase(cVarName)){
				//主力店签约数
				sb.append(" and r.exec_plan_node_id='2416' and t.status=2");
			}else if("nMainUnsign".equalsIgnoreCase(cVarName)){
				//主力店未签约数
				sb.append(" and r.exec_plan_node_id='2416' and (t.status!=2 and t.status!=5)");
			}else if("nSubSigned".equalsIgnoreCase(cVarName)){
				//次主力店签约数
				sb.append(" and r.exec_plan_node_id='2421' and t.status=2");
			}else if("nSubUnsign".equalsIgnoreCase(cVarName)){
				//次力店未签约数
				sb.append(" and r.exec_plan_node_id='2421' and (t.status!=2 and t.status!=5)");
			}else if("nMainIn".equalsIgnoreCase(cVarName)){
				//主力店进场数
				sb.append(" and r.exec_plan_node_id='2461' and t.status=2");
			}else if("nMainUnin".equalsIgnoreCase(cVarName)){
				//主力店未进场数
				sb.append(" and r.exec_plan_node_id='2461' and (t.status!=2 and t.status!=5)");
			}else if("nSubIn".equalsIgnoreCase(cVarName)){
				//次主力店进场数
				sb.append(" and r.exec_plan_node_id='2462' and t.status=2");
			}else if("nSubUnin".equalsIgnoreCase(cVarName)){
				//次力店未进场数
				sb.append(" and r.exec_plan_node_id='2462' and (t.status!=2 and t.status!=5)");
			}
		    
			List resultList = this.getDao().findBySql(sb.toString(),null);
			if(null!=resultList && resultList.size()>0){
				returnValue = ((BigDecimal)resultList.get(0)).intValue();
			}
		}catch(Exception e){}
		return returnValue;
	}

	/**
	 * 根据节点搜索与该节点关联的详情列表
	 * @param nodeIds
	 * @param treeTypeCd
	 * @return
	 */
	public List<ExecPlanDetail> getDetailsByProjPlanNodes(String[] nodeIds,String searchLayouts){
		return getDetailsByProjPlanNodes(nodeIds,null,searchLayouts,null,null,null,null,null,null,null,null,null,null);
	}
	public List<ExecPlanDetail> getDetailsByProjPlanNodes(String[] nodeIds,String pointLevel,String searchLayouts
			,String filter_GED_scheduleStartDate,String filter_LED_scheduleStartDate
			,String filter_GED_scheduleEndDate,String filter_LED_scheduleEndDate
			,String filter_GED_realEndDate,String filter_LED_realEndDate,String search_status,String nowViewStyle,String nowResOrgNames,String planTypeCd) {
		
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder sb = new StringBuilder("from ExecPlanDetail where");
			
		    if(StringUtils.isNotBlank(pointLevel)){
				sb.append(" execPlanNode.pointLevel <= :pointLevel and ");
			    params.put("pointLevel", Long.parseLong(pointLevel));
		    }
		    if(StringUtils.isNotBlank(planTypeCd)){
				sb.append(" execPlanNode.planTypeCd = :planTypeCd and ");
			    params.put("planTypeCd", planTypeCd);
		    }
			if(StringUtils.isNotBlank(searchLayouts)){
				if(!"Export".equalsIgnoreCase(searchLayouts)){
					String final_plans = "";
					String[] arr_plans = searchLayouts.split(",");
					for(String str_plan : arr_plans){
						final_plans += "'"+str_plan+"',";
					}
					if(!"".equalsIgnoreCase(final_plans)){
						final_plans = final_plans.substring(0, final_plans.length()-1);
					}
					sb.append(" execPlanLayout.execPlanLayoutId in ("+final_plans+") and ");
				}
			} else
				return new ArrayList<ExecPlanDetail>();
			
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
					sb.append(" scheduleEndDate < to_date(:nowdate,'YYYY-MM-DD') and ");
					sb.append(" (status = '0' or status='1' or status='3') and infoConfirmedFlg is not null and infoConfirmedFlg='1' and activeBl is not null and activeBl=1 and ");
					params.put("nowdate", DateOperator.formatDate(DateOperator.getDateNow(), "yyyy-MM-dd"));
				}else if("102".equalsIgnoreCase(search_status)){
					//完成曾过期
					sb.append(" scheduleEndDate < realEndDate and ");
					sb.append(" (status = '2') and infoConfirmedFlg is not null and infoConfirmedFlg='1' and activeBl is not null and activeBl=1 and ");
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
		    	sb.append(" ((status=0 or status=1 or status=3) and infoConfirmedFlg=1 ");
		    	sb.append(" and (scheduleEndDate<:date1 or scheduleStartDate<:date2)) and");
			    params.put("date1", nextMonthDate);
			    params.put("date2", nextMonth2Date);
		    }else{
		    	//如果是完整查看，显示所有记录
			    if(null!=nodeIds && nodeIds.length>0){
					sb.append(" execPlanNode.execPlanNodeId in (");
					int i = 0;
					int length = nodeIds.length;
					for (String id : nodeIds) {
						sb.append("'" + id + "'");
						if (i < length - 1) {
							sb.append(",");
						}
						i++;
					}
					sb.append(") and");
			    }
		    }
		    if(StringUtils.isNotBlank(nowResOrgNames)){
		    	String[] rons = nowResOrgNames.split(";");
		    	if(null!=rons&&rons.length>0){
		    		sb.append(" (");
			    	for(int i=0;i<rons.length;i++){
			    		String ron = rons[i];
			    		if(0==i){
			    			sb.append("execPlanNode.resOrgName like :resOrgName"+i);
			    		}else{
			    			sb.append(" or execPlanNode.resOrgName like :resOrgName"+i);
			    		}
				  		params.put("resOrgName"+i, "%"+ron+"%");
			    	}
			    	sb.append(") and ");
		    	}
		    }
			
			sb.append(" 1=1 order by execPlanNode.sequenceNo asc");
			return this.getDao().find(sb.toString(), params);
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
	public ExecPlanDetail addPlanDetailInfo(String scheduleStartDate, String scheduleEndDate, String realEndDate, String planDetailTempId,
			String nodeId, String layoutId, boolean activeBl, String projectCd) throws Exception {
		ExecPlanDetail detail = new ExecPlanDetail();
		detail.setProjectCd(projectCd);
		
		ExecPlanNode execPlanNode = execPlanNodeManager.getEntity(nodeId);
		detail.setExecPlanNode(execPlanNode);
		
		ExecPlanLayout execPlanLayout = execPlanLayoutManager.getEntity(layoutId);
		detail.setExecPlanLayout(execPlanLayout);
		
		detail.setActiveBl(activeBl);
		detail.setInfoConfirmedFlg("0");
		
		detail.setStatus(DictContants.EXEC_PLAN_DETAIL_STATUS_NOT_COMPLETED);

		String format = "yyyy-MM-dd";
		detail.setScheduleStartDate(DateOperator.parse(scheduleStartDate, format));
		detail.setScheduleEndDate(DateOperator.parse(scheduleEndDate, format));
		detail.setRealEndDate(DateOperator.parse(realEndDate, format));

		this.saveExecPlanDetail(detail);
		attachManager.updateTmpFile(planDetailTempId, detail.getExecPlanDetailId());
		
		ExecPlanDetailMess pm = new ExecPlanDetailMess();
		pm.setMessContent("[新增]"+DateOperator.formatDate(detail.getScheduleStartDate(), "yyyy-MM-dd")+"~"+DateOperator.formatDate(detail.getScheduleEndDate(), "yyyy-MM-dd"));
		pm.setExecPlanDetail(detail);
		pm.setSysType("1");
		execPlanDetailMessManager.saveExecPlanDetailMess(pm);
		
		return detail;
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
		ExecPlanDetail detail = this.getEntity(planDetailId);
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
		this.saveExecPlanDetail(detail);
		
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

		ExecPlanDetailMess pm = new ExecPlanDetailMess();
		pm.setMessContent("[修改]"+operationLog);
		pm.setExecPlanDetail(detail);
		pm.setSysType("1");
		execPlanDetailMessManager.saveExecPlanDetailMess(pm);
	}

	/**
	 * 改变计划详情的状态
	 * @param planDetailId
	 * @param newStatus
	 * @return
	 * @throws Exception 
	 */
	public boolean updatePlanDetailStatus(String planDetailId, String newStatus) throws Exception {
		if (StringUtils.isBlank(planDetailId))
			return false;

		if (StringUtils.isBlank(newStatus))
			return false;
		ExecPlanDetail detail = this.getEntity(planDetailId);
		detail.setStatus(newStatus);
		if("-1".equalsIgnoreCase(newStatus)){
			detail.setStatus("0");
			detail.setInfoConfirmedFlg("0");
		}
		this.saveExecPlanDetail(detail);

		Map<String, String> statusMap = this.buildPlanDetailStatusMap();
		String operationLog = "计划状态被更新为 \"" + statusMap.get(newStatus) + "\"";
		ExecPlanDetailMess pm = new ExecPlanDetailMess();
		pm.setMessContent("[修改]"+operationLog);
		pm.setExecPlanDetail(detail);
		pm.setSysType("1");
		execPlanDetailMessManager.saveExecPlanDetailMess(pm);

		return true;
	}
	
	/**
	 * 根据nodeRelID和PlanCd获取计划详情信息
	 * @param nodeRelId
	 * @param planCd
	 * @return
	 */
	public ExecPlanDetail getPlanDetailPerNodeRelIdAndPlanCd(String projectCd, String nodeId, String layoutId) {
		String hql = "from ExecPlanDetail where projectCd=:projectCd" +
				" and execPlanNode.execPlanNodeId = :nodeId" +
				" and execPlanLayout.execPlanLayoutId = :layoutId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectCd", projectCd);
		params.put("nodeId", nodeId);
		params.put("layoutId", layoutId);
		List<ExecPlanDetail> result = this.find(hql, params);

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
	 * @param planDetailId
	 * @return
	 * @throws Exception 
	 */
	public boolean confirmPlanDetailInfo(String planDetailId,String confirmFlg) throws Exception {
		if (StringUtils.isBlank(planDetailId))
			throw new IllegalArgumentException("计划详情ID不能为空");

		ExecPlanDetail pd = this.getEntity(planDetailId);
		pd.setInfoConfirmedFlg(confirmFlg);
		saveExecPlanDetail(pd);
		
		ExecPlanDetailMess pm = new ExecPlanDetailMess();
		if("1".equalsIgnoreCase(confirmFlg)){
			pm.setMessContent("[确认]");
		}else{
			pm.setMessContent("[取消确认]");
		}
		pm.setExecPlanDetail(pd);
		pm.setSysType(confirmFlg);
		execPlanDetailMessManager.saveExecPlanDetailMess(pm);
		return true;
	}

	/**
	 * 批量确认指定项目的计划的计划信息
	 * @param projectCd
	 * @param planTypeCd
	 * @return
	 */
	public boolean batchConfirmPlanDetailInfo(String projectCd, String planTypeCd) {

		String hql = new StringBuffer()
					.append(" update ExecPlanDetail pd ")
					.append("    set pd.infoConfirmedFlg = :infoConfirmedFlg " )
					.append("  where pd.infoConfirmedFlg = :oriInfoConfirmedFlg " )
					.append("    and pd.projectCd=:projectCd and planTypeCd = :planTypeCd")
					.toString();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("planTypeCd", planTypeCd);
		params.put("infoConfirmedFlg", "1");
		params.put("oriInfoConfirmedFlg", "0");
		params.put("projectCd", projectCd);
		getDao().batchExecute(hql, params);

		return true;
	}

	/**
	 * 构造计划详情状态Map
	 * @return
	 */
	public Map<String, String> buildPlanDetailStatusMap() {
		Map<String, String> m = new HashMap<String, String>();
		m.put(DictContants.EXEC_PLAN_DETAIL_STATUS_NOT_COMPLETED, "未完成");
		m.put(DictContants.EXEC_PLAN_DETAIL_STATUS_COMPLETED, "预完成");
		m.put(DictContants.EXEC_PLAN_DETAIL_STATUS_CONFIRM_COMPLETED, "完成");
		m.put(DictContants.EXEC_PLAN_DETAIL_STATUS_CONFIRM_PRECOMPLETED, "预完成");
		return m;
	}

	/**
	 * 招采计划中搜索成本中心近期一个月和延期的项目执行计划
	 * @param nowDate
	 * @param nextMonthDate
	 * @param centerCd
	 * @param projectCd
	 * @param onlyCenCdQuery true为只需要搜索中心CD
	 * @return
	 */
	public List<PlanCenterDetailsVO> fetchCenterDetails(String nextMonthDate,String prevMonthDate) {
		//try{
			List<Object> list =queryCenterPlan(nextMonthDate,prevMonthDate);
			// 对list进行遍历，插入PlanCenterDetailsVO中
			if (list != null && list.size() > 0) {
				List<PlanCenterDetailsVO> listVo = new ArrayList<PlanCenterDetailsVO>();
				PlanCenterDetailsVO centerVO = new PlanCenterDetailsVO();
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					boolean ifSameCenter = false;
					
					if(null!=centerVO.getProjectCd()
							&& centerVO.getProjectCd().equalsIgnoreCase((String)obj[5])){
						//如果是相同的
						ifSameCenter = true;
					}else{
						centerVO = new PlanCenterDetailsVO();
						centerVO.setProjectName(PlasCache.getOrgByCd((String)obj[5]).getOrgName());
						centerVO.setProjectCd((String)obj[5]);
					}
					
					List<PlanDetailVO> detailList = centerVO.getPlanDetailVO();
					if(null==detailList){
						detailList = new ArrayList<PlanDetailVO>();
					}
					PlanDetailVO detailVO = new PlanDetailVO();
					detailVO.setNodeName(((String)obj[0]).replaceAll(" ", ""));
					detailVO.setResOrgName((String)obj[1]);
					detailVO.setExecutionPlanName((String)obj[2]);
					detailVO.setScheduleEndDate((Date)obj[3]);
					detailVO.setStatus((String)obj[4]);
					detailVO.setProjectName(PlasCache.getOrgByCd((String)obj[5]).getOrgName());
					detailVO.setDetailId((String)obj[6]);
					detailList.add(detailVO);
					centerVO.setPlanDetailVO(detailList);
					
					if(!ifSameCenter){
						listVo.add(centerVO);
					}
				}
				return listVo;
			}
		//}catch(Exception e){}
		return null;
	}
	/**
	 * 搜索成本计划的任务
	 */
	public List<Object> queryCenterPlan(String nextMonthDate,String prevMonthDate){
		Map<String, Object> params = new HashMap<String, Object>();
		//根据条件搜索到相关执行计划的相关数据
		StringBuffer sql = new StringBuffer("select b.node_name,b.res_org_name,c.execution_plan_name" +
				",a.schedule_end_date,a.status,a.project_cd,a.exec_plan_detail_id");
		sql.append(" from exec_plan_detail a");
		sql.append(" inner join exec_plan_node b on a.exec_plan_node_id = b.exec_plan_node_id");
		sql.append(" inner join exec_plan_layout c on a.exec_plan_layout_id = c.exec_plan_layout_id");
		sql.append(" where b.res_org_name like '%成本%'");
		sql.append(" and (a.status='0' or a.status='1')");
		sql.append(" and a.schedule_end_date<:nextMonthDate");
		sql.append(" and a.schedule_end_date>:prevMonthDate");
		sql.append(" and a.info_confirmed_flg ='1' and c.active_flg ='1'");
		sql.append(" and (select count(*) from cost_ctrl_bid_purc d where d.data_src_id=a.exec_plan_detail_id)=0");
		sql.append(" order by a.project_cd, c.plan_type_cd asc, a.schedule_end_date asc");
		params.put("nextMonthDate", DateOperator.parse(nextMonthDate, "yyyy-MM-dd"));
		params.put("prevMonthDate", DateOperator.parse(prevMonthDate, "yyyy-MM-dd"));
		List<Object> list = execPlanDetailDao.findBySql(sql.toString(), params);
		return list;
	}
	
	/**
	 * 根据planDetailId和currenUser获取该节点是否被当前用户
	 * @param planDetailId
	 * @param currenUser
	 * @return
	 */
	public boolean confirmAttention(String planDetailId, String currenUser) {
		String hql = "from OaAllAttention where entityId=:entityId"
				+ " and creator = :creator";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entityId", planDetailId);
		params.put("creator", currenUser);
		List<ExecPlanDetail> result = this.find(hql, params);
		boolean attentionFlag = false;
		if (result.size() == 0) {
			attentionFlag = false;
		}
		if (result.size() > 0) {
			attentionFlag = true;
		}
		return attentionFlag;
	}
	
	/**
	 * 
	 * @param module
	 * @param planDetailId
	 * @return
	 */
	public OaAllAttention getOaAllAttention(String module, String planDetailId,String userCd) {
		if (StringUtils.isNotBlank(planDetailId)) {
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder sb = new StringBuilder("from OaAllAttention where");
			sb.append(" moduleCd = :moduleCd and entityId=:entityId and userCd=:userCd");
			params.put("moduleCd", module);
			params.put("entityId", planDetailId);
			params.put("userCd", userCd);
			List<OaAllAttention> arr = this.getDao()
					.find(sb.toString(), params);
			if (null != arr && arr.size() > 0)
				return arr.get(0);
			else
				return null;
		} else
			return null;
	}
	
	/**
	 * 
	 * @param moduleCd
	 * @param entityId
	 * @param moduleRecordVersion
	 */
	public void setAttentionChange(String moduleCd,String entityId){
		oaAllAttentionManager.setAttentionChange(moduleCd, entityId);
	}
	
	/**
	 * 
	 * @param entityId
	 * @param moduleCd
	 * @param moduleRecordVersion
	 */
	public void updateAttention(String entityId, String moduleCd,
			long moduleRecordVersion) {
		String sql = "";
		sql = "update OaAllAttention set moduleRecordVersion="
				+ moduleRecordVersion + " where entityId=? and moduleCd=?";
		execPlanDetailDao.batchExecute(sql, entityId, moduleCd);
	}
	
	/**
	 * 
	 * @param entityId
	 * @return
	 */
	public int getAttentionRemarkVersion(String entityId){
		int moduleRecordVersion = 1;
		if (StringUtils.isNotBlank(entityId)) {
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder sb = new StringBuilder("from OaAllAttention where");
			sb.append(" entityId = :entityId order by updatedDate desc");
			params.put("entityId", entityId);
			List<OaAllAttention> arr = this.getDao()
					.find(sb.toString(), params);
			if (null != arr && arr.size() > 0){
				OaAllAttention mess = arr.get(0);
				if(mess.getRemark()!=null){
					moduleRecordVersion = Integer.valueOf(mess.getRemark());
				}
			}else{
				moduleRecordVersion = 1;
			}
		}
		return moduleRecordVersion;
	}
	
	/**
	 * 搜索出对应的所有节点
	 * 
	 * @param execPlanLayouts
	 * @param viewPlanNodes
	 * @return
	 */
	public List<ExecPlanDetail> getListExecPlanDetail(
			List<ExecPlanLayout> execPlanLayouts,
			List<ExecPlanNode> viewPlanNodes) {
		if (execPlanLayouts != null && !execPlanLayouts.isEmpty()
				&& viewPlanNodes != null && !viewPlanNodes.isEmpty()) {
			StringBuffer sufEpl = new StringBuffer();
			StringBuffer sufEpn = new StringBuffer();
			for(ExecPlanLayout epl : execPlanLayouts){
				sufEpl.append("'"+epl.getExecPlanLayoutId()+"',");
			}
			for(ExecPlanNode epn : viewPlanNodes){
				sufEpn.append("'"+epn.getExecPlanNodeId()+"',");
			}
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuilder sb = new StringBuilder("from ExecPlanDetail where");
			sb.append(" execPlanNode.execPlanNodeId  in ("+sufEpn.substring(0, sufEpn.length()-1)+")");
			sb.append("   and execPlanLayout.execPlanLayoutId in ("+sufEpl.substring(0, sufEpl.length()-1)+")");
			List<ExecPlanDetail> arr = this.getDao().find(sb.toString(), params);
			return arr;
		} else
			return null;
	}
}

