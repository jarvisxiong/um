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

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.jbpm.JbpmTaskCandidateManager;
import com.hhz.ump.dao.jbpm.JbpmTaskManager;
import com.hhz.ump.dao.oa.OaAllAttentionManager;
import com.hhz.ump.dao.oa.OaEmailBodyManager;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.jbpm.JbpmTaskCandidate;
import com.hhz.ump.entity.plan.PlanWorkCenter;
import com.hhz.ump.entity.plan.PlanWorkCenterMessage;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.web.plan.PlanWorkCenterAction;

@Service
@Transactional
public class PlanWorkCenterManager extends BaseService<PlanWorkCenter, String> {
	@Autowired
	private PlanWorkCenterDao planWorkCenterDao;
	@Autowired
	private OaEmailBodyManager oaEmailBodyManager;

	@Autowired
	private AppAttachFileManager appAttachFileManager;

	@Autowired
	private JbpmTaskManager jbpmTaskManager;

	@Autowired
	private JbpmTaskCandidateManager jbpmTaskCandidateManager;
	
	@Autowired
	private OaAllAttentionManager oaAllAttentionManager;

	private void update2JbpmTask(PlanWorkCenter planWorkCenter) {
		try{
			JbpmTask jbpmTask = jbpmTaskManager.getByEntityId(planWorkCenter.getPlanWorkCenterId());
			if ((planWorkCenter.getPrincipal() != null || planWorkCenter.getCorUser() != null) &&
					(planWorkCenter.getStatusCd().equalsIgnoreCase(PlanWorkCenterAction.STATUS_CD_JINXINGZHONG)
							||planWorkCenter.getStatusCd().equalsIgnoreCase(PlanWorkCenterAction.STATUS_CD_SHENQINGSHANCHU))) {
				if (jbpmTask == null) {
					jbpmTask = new JbpmTask();
					jbpmTask.setModuleCd("planWorkCenter");
					jbpmTask.setModuleName("中心内部任务");
					jbpmTask.setDeptCd(planWorkCenter.getOrgCd());
					jbpmTask.setUserCd(planWorkCenter.getCreator());
					jbpmTask.setUserName(CodeNameUtil.getUserNameByCd(planWorkCenter.getCreator()));
					jbpmTask.setJbpmId(planWorkCenter.getPlanWorkCenterId());
				}
				jbpmTask.setJbpmCd(planWorkCenter.getSerialNumber() + planWorkCenter.getSerialOrder());
				jbpmTask.setApplyDate(planWorkCenter.getTargetDate());
				jbpmTask.setStatusCd(planWorkCenter.getStatusCd());
				jbpmTask.setRemark(planWorkCenter.getContent());
				jbpmTaskManager.saveJbpmTask(jbpmTask);
				jbpmTaskCandidateManager.delete(jbpmTask.getJbpmTaskCandidates());
	
				if(null!=planWorkCenter.getPrincipal()){
					String[] approveUserCds = planWorkCenter.getPrincipal().split(";");
					if(null!=approveUserCds){
						for (String approveCd : approveUserCds) {
							JbpmTaskCandidate jbpmTaskCandidate = new JbpmTaskCandidate();
							jbpmTaskCandidate.setUserCd(approveCd);
							jbpmTaskCandidate.setUserName(CodeNameUtil.getUserNameByCd(approveCd));
							jbpmTaskCandidate.setJbpmTask(jbpmTask);
							jbpmTaskCandidateManager.saveJbpmTaskCandidate(jbpmTaskCandidate);
							try{
								oaAllAttentionManager.saveByParamNoRender("planWorkCenter",
										planWorkCenter.getPlanWorkCenterId(), planWorkCenter.getRecordVersion(), approveCd);
							}catch(Exception e){}
						}
					}
				}
				
				if(null!=planWorkCenter.getCorUser()){
					String[] corUserCds = planWorkCenter.getCorUser().split(";");
					if(null!=corUserCds){
						for (String approveCd : corUserCds) {
							JbpmTaskCandidate jbpmTaskCandidate = new JbpmTaskCandidate();
							jbpmTaskCandidate.setUserCd(approveCd);
							jbpmTaskCandidate.setUserName(CodeNameUtil.getUserNameByCd(approveCd));
							jbpmTaskCandidate.setJbpmTask(jbpmTask);
							jbpmTaskCandidateManager.saveJbpmTaskCandidate(jbpmTaskCandidate);
							try{
								oaAllAttentionManager.saveByParamNoRender("planWorkCenter",
										planWorkCenter.getPlanWorkCenterId(), planWorkCenter.getRecordVersion(), approveCd);
							}catch(Exception e){}
						}
					}
				}
			} else {
				if (jbpmTask != null) {
					jbpmTaskManager.delete(jbpmTask);
				}
			}
		}catch(Exception e){}
	}

	public void save(PlanWorkCenter planWorkCenter) {
		planWorkCenterDao.save(planWorkCenter);
	}
	
	public void savePlanWorkCenter(PlanWorkCenter planWorkCenter) {
		PowerUtils.setEmptyStr2Null(planWorkCenter);
		boolean ifAdd = false;
		if(null==planWorkCenter.getPlanWorkCenterId() || "".equalsIgnoreCase(planWorkCenter.getPlanWorkCenterId())){
			ifAdd = true;
		}
		planWorkCenterDao.save(planWorkCenter);
		
		update2JbpmTask(planWorkCenter);
//		if(ifAdd){
//			try{
//				oaAllAttentionManager.saveByParamNoRender("planWorkCenter",
//						planWorkCenter.getPlanWorkCenterId(), planWorkCenter.getRecordVersion(),
//						(PlasCache.getOrgByCd(planWorkCenter.getOrgCd())).getOrgMgrId());
//			}catch(Exception e){}
//			try{
//				String[] approveUserCds = planWorkCenter.getPrincipal().split(";");
//				for (String approveCd : approveUserCds) {
//					oaAllAttentionManager.saveByParamNoRender("planWorkCenter",
//							planWorkCenter.getPlanWorkCenterId(), planWorkCenter.getRecordVersion(),approveCd);
//				}
//			}catch(Exception e){}
//		}
	}

	public void savePlanWorkCenter(PlanWorkCenter planWorkCenter, String entityTmpId) {
		PowerUtils.setEmptyStr2Null(planWorkCenter);
		boolean ifAdd = false;
		if(null==planWorkCenter.getPlanWorkCenterId() || "".equalsIgnoreCase(planWorkCenter.getPlanWorkCenterId())){
			ifAdd = true;
		}
		planWorkCenterDao.save(planWorkCenter);
		if (StringUtils.isNotEmpty(entityTmpId)) {
			String bizFieldName = "";
			String hql = "select bizFieldName from AppAttachFile where bizEntityId = ? ";
			List lstResult = planWorkCenterDao.find(hql, entityTmpId);
			try {
				bizFieldName = (String)lstResult.get(0);
			} catch (Exception e) {
			}
			if(StringUtils.isNotEmpty(bizFieldName)){
				appAttachFileManager.updateTmpFile(entityTmpId, PlanWorkCenter.class.getSimpleName(), planWorkCenter.getPlanWorkCenterId(), bizFieldName);
			}else{
				appAttachFileManager.updateTmpFile(entityTmpId, PlanWorkCenter.class.getSimpleName(), planWorkCenter.getPlanWorkCenterId());
			}
		}
		
		update2JbpmTask(planWorkCenter);
//		if(ifAdd){
//			try{
//				oaAllAttentionManager.saveByParamNoRender("planWorkCenter",
//						planWorkCenter.getPlanWorkCenterId(), planWorkCenter.getRecordVersion(),
//						(PlasCache.getOrgByCd(planWorkCenter.getOrgCd())).getOrgMgrId());
//			}catch(Exception e){}
//			try{
//				String[] approveUserCds = planWorkCenter.getPrincipal().split(";");
//				for (String approveCd : approveUserCds) {
//					oaAllAttentionManager.saveByParamNoRender("planWorkCenter",
//							planWorkCenter.getPlanWorkCenterId(), planWorkCenter.getRecordVersion(),approveCd);
//				}
//			}catch(Exception e){}
//		}
	}

	public void deletePlanWorkCenter(String id) {
		planWorkCenterDao.delete(id);
		jbpmTaskManager.deleteByEntityId(id);
	}

	@Override
	public HibernateDao<PlanWorkCenter, String> getDao() {
		return planWorkCenterDao;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int getMaxNo(String orgCode) {
		StringBuilder hql = new StringBuilder();
		hql.append("select max(serialOrder) from PlanWorkCenter ").append("where orgCd = ? ");
		List lstResult = planWorkCenterDao.find(hql.toString(), orgCode);
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
	 * 发送提醒邮件
	 * @param meeting
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public boolean sendRemindEmail(PlanWorkCenter planWorkCenter,String sender_cd) throws SQLException, IOException {
	    if (planWorkCenter == null)
			throw new IllegalArgumentException("中心内部任务不能为空");
	    
	    Set<String> toUiids = new HashSet<String>();
	    
	    String resPerson = planWorkCenter.getPrincipal();
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
		String content = planWorkCenter.getContent();
		
		String subject = "【中心内部任务提醒】" + planWorkCenter.getSerialNumber()+planWorkCenter.getSerialOrder() + ":" + content.trim();
		
		oaEmailBodyManager.sendData2Email(subject, buildMailBody(planWorkCenter, content), sender_cd, "0", toUiids.toArray(new String[toUiids.size()]));
	    }
	    
	    return true;
	}
	
	/**
	 * 构造邮件体
	 */
	private String buildMailBody(PlanWorkCenter planWorkCenter, String content) throws SQLException, IOException {
	    StringBuilder mailBody = new StringBuilder();

	    mailBody.append("<div style='font-size: 14px; line-height: 25px'><a href='javascript:" +
	    		"parent.TabUtils.newTab(\"138\",\"中心内部任务\",parent._ctx+\"/plan/plan-work-center!getAllPlan.action?opened_entityid="
	    		+planWorkCenter.getPlanWorkCenterId()+"\",true);' style='text-decoration: underline; color: #000;'>点击查看详细信息>></a></div>");
	    
	    mailBody.append("<div style='font-size: 14px; line-height: 25px'>任务内容：" + content + "</div>");
	    mailBody.append("<div style='font-size: 14px; line-height: 25px'>目标时间：" + DateOperator.formatDate(planWorkCenter.getTargetDate(), "yyyy-MM-dd") + "</div>");
	    
	    mailBody.append("<div style='font-size: 14px;'>回复：");
	    
	    List<PlanWorkCenterMessage> planWorkCenterMessages = planWorkCenter.getPlanWorkCenterMessages();
	    if (planWorkCenterMessages.size() == 0) {
	        mailBody.append("暂无回复</div>");
	    } else {
	        mailBody.append("<br/><ul style='list-style:none; margin-left: 40px;'>");
	        for (PlanWorkCenterMessage c : planWorkCenterMessages) {
	            mailBody.append("<li style='line-height:18px;'><strong>" + CodeNameUtil.getUserNameByCd(c.getCreator()) + "(" + DateOperator.formatDate(c.getCreatedDate(), "yyyy-MM-dd") + ")：</strong>");
	            String comment = c.getContent();
	            mailBody.append(comment + "</li>");
	        }
	        mailBody.append("</ul></div>");
	    }
	    
	    return mailBody.toString();
	}

	/**
	 * 从执行计划或者成本计划中搜索关联的中心内部任务
	 * @param planExecId 来自的计划的ID
	 * @param addFromType 新增的类型。1：从成本计划；2：从执行计划
	 * @return 结果的map
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Map<String, String>> getPlanWorkCenterMapByExec(String planExecId,String addFromType) {
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
		Map<String,String> objMap = new HashMap<String,String>();
		StringBuilder hql = new StringBuilder();
		hql.append("select planWorkCenterId,content,statusCd from PlanWorkCenter ").append("where planExecId = ? and addFromType='"+addFromType+"'");
		List lstResult = planWorkCenterDao.find(hql.toString(), planExecId);
		try {
			for(int i=0;null!=lstResult && i<lstResult.size();i++) {
				Object[] obj=(Object[])lstResult.get(i);
				objMap = new HashMap<String,String>();
				objMap.put("planWorkCenterId", (String)obj[0]);
				objMap.put("content", (String)obj[1]);
				objMap.put("statusCd", (String)obj[2]);
				returnList.add(objMap);
			}
		} catch (Exception e) {
		}
		return returnList;
	}
}
