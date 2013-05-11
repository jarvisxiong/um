package com.hhz.ump.dao.plan;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.hhz.ump.dao.oa.OaAllAttentionManager;
import com.hhz.ump.dao.oa.OaEmailBodyManager;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.jbpm.JbpmTaskCandidate;
import com.hhz.ump.entity.plan.PlanWork2;
import com.hhz.ump.entity.plan.PlanWork2Message;
import com.hhz.ump.entity.plan.PlanWork2Status;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.web.plan.PlanWork2Action;
import com.hhz.uums.entity.ws.WsPlasOrg;

@Service
@Transactional
public class PlanWork2Manager extends BaseService<PlanWork2, String> {
	@Autowired
	private PlanWork2Dao planWork2Dao;
	
	@Autowired
	private PlanWork2StatusDao planWork2StatusDao;
	
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

	private void update2JbpmTask(PlanWork2 planWork2) {
		try{
			JbpmTask jbpmTask = jbpmTaskManager.getByEntityId(planWork2.getPlanWork2Id());
			if (planWork2.getStatusCd().equalsIgnoreCase(PlanWork2Action.STATUS_CD_JINXINGZHONG)
							||planWork2.getStatusCd().equalsIgnoreCase(PlanWork2Action.STATUS_CD_SHENQINGSHANCHU)
							||planWork2.getStatusCd().equalsIgnoreCase(PlanWork2Action.STATUS_CD_YUWANCHENG)
							||planWork2.getStatusCd().equalsIgnoreCase(PlanWork2Action.STATUS_CD_SUSPEND)
							||planWork2.getStatusCd().equalsIgnoreCase(PlanWork2Action.STATUS_CD_NO_ASSESS)) {
				if (jbpmTask == null) {
					jbpmTask = new JbpmTask();
					jbpmTask.setModuleCd("planWork2");
					jbpmTask.setModuleName("中心月计划");
					jbpmTask.setDeptCd(planWork2.getCenterCd());
					jbpmTask.setUserCd(planWork2.getCreator());
					jbpmTask.setUserName(CodeNameUtil.getUserNameByCd(planWork2.getCreator()));
					jbpmTask.setJbpmId(planWork2.getPlanWork2Id());
				}
				jbpmTask.setJbpmCd(planWork2.getSerialNumber() + planWork2.getSerialOrder());
				jbpmTask.setApplyDate(planWork2.getTargetDate());
				jbpmTask.setStatusCd(planWork2.getStatusCd());
				jbpmTask.setRemark(planWork2.getContent());
				jbpmTaskManager.saveJbpmTask(jbpmTask);
				jbpmTaskCandidateManager.delete(jbpmTask.getJbpmTaskCandidates());
				if (planWork2.getCenterCd() != null) {
					WsPlasOrg wsUaapOrg = PlasCache.getOrgByCd(planWork2.getCenterCd());
				    JbpmTaskCandidate jbpmTaskCandidate = new JbpmTaskCandidate();
					jbpmTaskCandidate.setUserCd( wsUaapOrg.getOrgMgrId());
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

	public void save(PlanWork2 planWork2) {
		planWork2Dao.save(planWork2);
	}
	
	public void savePlanWork2Status(PlanWork2Status planWork2Status) {
		planWork2StatusDao.save(planWork2Status);
	}
	public void getPlanWork2Status(String planWork2StatusId) {
		planWork2StatusDao.get(planWork2StatusId);
	}
	public PlanWork2Status getPlanWork2StatusByMonth(BigDecimal planYear,BigDecimal planMonth, String centerCd) {
		PlanWork2Status planWork2Status = null;
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQM_planYear", planYear));
		filters.add(new PropertyFilter("EQM_planMonth", planMonth));
		filters.add(new PropertyFilter("INA_isQuarter", new Object[] {"", "0"}));
		filters.add(new PropertyFilter("EQS_centerCd", centerCd));
		List<PlanWork2Status> planWork2Statuss =  planWork2StatusDao.find(filters);
		if(null!=planWork2Statuss && 0!=planWork2Statuss.size()){
			//如果有记录就读取第一个的status
			planWork2Status = planWork2Statuss.get(0);
		}else{
			//如果没有记录就插入一条新记录
			planWork2Status = new PlanWork2Status();
			planWork2Status.setPlanYear(planYear);
			planWork2Status.setPlanMonth(planMonth);
			planWork2Status.setPlanQuarter(new BigDecimal(0));
			planWork2Status.setIsQuarter("0");
			planWork2Status.setStatusCd(PlanWork2Action.CENTER_ADDING);
			planWork2Status.setCenterCd(centerCd);
			planWork2StatusDao.save(planWork2Status);
		}
		return planWork2Status;
	}
	public PlanWork2Status getPlanWork2StatusByQuarter(BigDecimal planYear,BigDecimal planQuarter, String centerCd, boolean if_add) {
		PlanWork2Status planWork2Status = null;
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQM_planYear", planYear));
		filters.add(new PropertyFilter("EQM_planQuarter", planQuarter));
		filters.add(new PropertyFilter("INA_isQuarter", new Object[] {"1"}));
		filters.add(new PropertyFilter("EQS_centerCd", centerCd));
		List<PlanWork2Status> planWork2Statuss =  planWork2StatusDao.find(filters);
		if(null!=planWork2Statuss && 0!=planWork2Statuss.size()){
			//如果有记录就读取第一个的status
			planWork2Status = planWork2Statuss.get(0);
		}else{
			//如果没有记录就插入一条新记录
			if(if_add){
				planWork2Status = new PlanWork2Status();
				planWork2Status.setPlanYear(planYear);
				planWork2Status.setPlanQuarter(planQuarter);
				planWork2Status.setPlanMonth(new BigDecimal(0));
				planWork2Status.setIsQuarter("1");
				planWork2Status.setStatusCd(PlanWork2Action.CENTER_ADDING);
				planWork2Status.setCenterCd(centerCd);
				planWork2StatusDao.save(planWork2Status);
			}
		}
		return planWork2Status;
	}
	
	/*
	 * 总裁办选择开始给权重分时，自动生成项目管理计划和综合计划
	 */
	public boolean generateFixedPlan(int planYear,int planQuarter, String centerCd) {
		PlanWork2 planWork2 = new PlanWork2();
		int planMonth = PlanWork2Action.getMonthByQuarter(planQuarter);
		
		try{
			//搜索项目管理得分的记录，并设置好权重分
			List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
			filters.add(new PropertyFilter("EQM_planType", new BigDecimal(3)));
			filters.add(new PropertyFilter("EQS_centerCd", centerCd));
			filters.add(new PropertyFilter("EQM_planMonth", new BigDecimal(planMonth)));
			filters.add(new PropertyFilter("EQM_planYear", new BigDecimal(planYear)));
			List<PlanWork2> planWork2s =  planWork2Dao.find(filters);
			if(null!=planWork2s && 0!=planWork2s.size()){
				//如果有记录，改到完成状态
				planWork2 = planWork2s.get(0);
				if(!PlanWork2Action.STATUS_CD_WANCHENG.equalsIgnoreCase(planWork2.getStatusCd())){
					planWork2.setStatusCd(PlanWork2Action.STATUS_CD_WANCHENG);
					planWork2Dao.save(planWork2);
				}
			}else{
				//如果没有记录就插入一条新记录
				planWork2 = new PlanWork2();
				planWork2.setPlanYear(new BigDecimal(planYear));
				planWork2.setPlanMonth(new BigDecimal(planMonth));
				planWork2.setStatusCd(PlanWork2Action.CENTER_ADDING);
				planWork2.setCenterCd(centerCd);
				planWork2.setStatusCd(PlanWork2Action.STATUS_CD_WANCHENG);
				planWork2.setPlanType(new BigDecimal(3));
				planWork2.setContent("项目管理");
				planWork2.setWeightPoint(new BigDecimal(PlanWork2Action.getWeightPointByCenter(3, centerCd)));
				planWork2.setBogusWeightPoint(planWork2.getWeightPoint());
				planWork2Dao.save(planWork2);
			}
		}catch(Exception e){}
		try{
			//搜索综合得分的记录，并设置好权重分
			List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
			filters.add(new PropertyFilter("EQM_planType", new BigDecimal(5)));
			filters.add(new PropertyFilter("EQS_centerCd", centerCd));
			filters.add(new PropertyFilter("EQM_planMonth", new BigDecimal(planMonth)));
			filters.add(new PropertyFilter("EQM_planYear", new BigDecimal(planYear)));
			List<PlanWork2> planWork2s =  planWork2Dao.find(filters);
			if(null!=planWork2s && 0!=planWork2s.size()){
				//如果有记录，改到完成状态
				planWork2 = planWork2s.get(0);
				if(!PlanWork2Action.STATUS_CD_WANCHENG.equalsIgnoreCase(planWork2.getStatusCd())){
					planWork2.setStatusCd(PlanWork2Action.STATUS_CD_WANCHENG);
					planWork2Dao.save(planWork2);
				}
			}else{
				//如果没有记录就插入一条新记录
				planWork2 = new PlanWork2();
				planWork2.setPlanYear(new BigDecimal(planYear));
				planWork2.setPlanMonth(new BigDecimal(planMonth));
				planWork2.setStatusCd(PlanWork2Action.CENTER_ADDING);
				planWork2.setCenterCd(centerCd);
				planWork2.setStatusCd(PlanWork2Action.STATUS_CD_WANCHENG);
				planWork2.setPlanType(new BigDecimal(5));
				planWork2.setContent("综合");
				planWork2.setWeightPoint(new BigDecimal(PlanWork2Action.getWeightPointByCenter(5, centerCd)));
				planWork2.setBogusWeightPoint(planWork2.getWeightPoint());
				planWork2Dao.save(planWork2);
			}
		}catch(Exception e){}
		try{
			//判断总裁办的公司总体得分记录
			if("7".equalsIgnoreCase(centerCd)){
				List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
				filters.add(new PropertyFilter("EQM_planType", new BigDecimal(1)));
				filters.add(new PropertyFilter("EQS_centerCd", centerCd));
				filters.add(new PropertyFilter("EQM_planMonth", new BigDecimal(planMonth)));
				filters.add(new PropertyFilter("EQM_planYear", new BigDecimal(planYear)));
				filters.add(new PropertyFilter("EQS_content", "公司总体得分"));
				List<PlanWork2> planWork2s =  planWork2Dao.find(filters);
				if(null!=planWork2s && 0!=planWork2s.size()){
					//如果有记录，改到完成状态
					planWork2 = planWork2s.get(0);
					if(!PlanWork2Action.STATUS_CD_WANCHENG.equalsIgnoreCase(planWork2.getStatusCd())){
						planWork2.setStatusCd(PlanWork2Action.STATUS_CD_WANCHENG);
						planWork2Dao.save(planWork2);
					}
				}else{
					//如果没有记录就插入一条新记录
					planWork2 = new PlanWork2();
					planWork2.setPlanYear(new BigDecimal(planYear));
					planWork2.setPlanMonth(new BigDecimal(planMonth));
					planWork2.setStatusCd(PlanWork2Action.CENTER_ADDING);
					planWork2.setCenterCd(centerCd);
					planWork2.setStatusCd(PlanWork2Action.STATUS_CD_WANCHENG);
					planWork2.setPlanType(new BigDecimal(1));
					planWork2.setContent("公司总体得分");
					planWork2.setWeightPoint(new BigDecimal(30));
					planWork2.setBogusWeightPoint(planWork2.getWeightPoint());
					planWork2Dao.save(planWork2);
				}
			}
		}catch(Exception e){}
		try{
			//判断项目管理中心的项目整体得分记录
			if("132".equalsIgnoreCase(centerCd)){
				List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
				filters.add(new PropertyFilter("EQM_planType", new BigDecimal(1)));
				filters.add(new PropertyFilter("EQS_centerCd", centerCd));
				filters.add(new PropertyFilter("EQM_planMonth", new BigDecimal(planMonth)));
				filters.add(new PropertyFilter("EQM_planYear", new BigDecimal(planYear)));
				filters.add(new PropertyFilter("EQS_content", "项目整体得分"));
				List<PlanWork2> planWork2s =  planWork2Dao.find(filters);
				if(null!=planWork2s && 0!=planWork2s.size()){
					//如果有记录，改到完成状态
					planWork2 = planWork2s.get(0);
					if(!PlanWork2Action.STATUS_CD_WANCHENG.equalsIgnoreCase(planWork2.getStatusCd())){
						planWork2.setStatusCd(PlanWork2Action.STATUS_CD_WANCHENG);
						planWork2Dao.save(planWork2);
					}
				}else{
					//如果没有记录就插入一条新记录
					planWork2 = new PlanWork2();
					planWork2.setPlanYear(new BigDecimal(planYear));
					planWork2.setPlanMonth(new BigDecimal(planMonth));
					planWork2.setStatusCd(PlanWork2Action.CENTER_ADDING);
					planWork2.setCenterCd(centerCd);
					planWork2.setStatusCd(PlanWork2Action.STATUS_CD_WANCHENG);
					planWork2.setPlanType(new BigDecimal(1));
					planWork2.setContent("项目整体得分");
					planWork2.setWeightPoint(new BigDecimal(40));
					planWork2.setBogusWeightPoint(planWork2.getWeightPoint());
					planWork2Dao.save(planWork2);
				}
			}
		}catch(Exception e){}
		return true;
	}
	
	public void savePlanWork2(PlanWork2 planWork2) {
		PowerUtils.setEmptyStr2Null(planWork2);
		planWork2Dao.save(planWork2);
		
		update2JbpmTask(planWork2);
//		boolean ifAdd = false;
//		if(null==planWork2.getPlanWork2Id() || "".equalsIgnoreCase(planWork2.getPlanWork2Id())){
//			ifAdd = true;
//		}
//		if(ifAdd){
//			try{
//				oaAllAttentionManager.saveByParamNoRender("planWork2",
//						planWork2.getPlanWork2Id(), planWork2.getRecordVersion(),
//						(PlasCache.getOrgByCd(planWork2.getCenterCd())).getOrgMgrId());
//			}catch(Exception e){}
//			try{
//				oaAllAttentionManager.saveByParamNoRender("planWork2",
//						planWork2.getPlanWork2Id(), planWork2.getRecordVersion(),
//						"xuhf");
//			}catch(Exception e){}
//			try{
//				oaAllAttentionManager.saveByParamNoRender("planWork2",
//						planWork2.getPlanWork2Id(), planWork2.getRecordVersion(),
//						"wubc");
//			}catch(Exception e){}
//		}
	}

	public void savePlanWork2(PlanWork2 planWork2, String entityTmpId) {
		PowerUtils.setEmptyStr2Null(planWork2);
		boolean ifAdd = false;
		if(null==planWork2.getPlanWork2Id() || "".equalsIgnoreCase(planWork2.getPlanWork2Id())){
			ifAdd = true;
		}
		planWork2Dao.save(planWork2);
		if (StringUtils.isNotEmpty(entityTmpId)) {
			appAttachFileManager.updateTmpFile(entityTmpId, PlanWork2.class.getSimpleName(), planWork2.getPlanWork2Id());
		}

		int now_year = DateOperator.getYear(new Date());
		int now_month = DateOperator.getMonth12(new Date());
		int now_val = now_year*12+now_month;

		int myYear = planWork2.getPlanYear().intValue();
		int myMonth = planWork2.getPlanMonth().intValue();
		int myVal = myYear*12+myMonth;
		if(myVal<=now_val){
			update2JbpmTask(planWork2);
		}
//		if(ifAdd){
//			try{
//				oaAllAttentionManager.saveByParamNoRender("planWork2",
//						planWork2.getPlanWork2Id(), planWork2.getRecordVersion(),
//						(PlasCache.getOrgByCd(planWork2.getCenterCd())).getOrgMgrId());
//			}catch(Exception e){}
//			try{
//				oaAllAttentionManager.saveByParamNoRender("planWork2",
//						planWork2.getPlanWork2Id(), planWork2.getRecordVersion(),"xuhf");
//			}catch(Exception e){}
//			try{
//				oaAllAttentionManager.saveByParamNoRender("planWork2",
//						planWork2.getPlanWork2Id(), planWork2.getRecordVersion(),"wubc");
//			}catch(Exception e){}
//		}
	}

	public void deletePlanWork2(String id) {
		planWork2Dao.delete(id);
		jbpmTaskManager.deleteByEntityId(id);
	}

	@Override
	public HibernateDao<PlanWork2, String> getDao() {
		return planWork2Dao;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public int getMaxNo(String orgCode,int planYear,int planMonth) {
		StringBuilder hql = new StringBuilder();
		hql.append("select max(planWork2.serialOrder) from PlanWork2Month where planYear="+planYear+" and planMonth="+planMonth+" and planWork2.centerCd = ? ");
		List lstResult = planWork2Dao.find(hql.toString(), orgCode);
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
		String hql = "from " + entityName + " where centerCd=?";

		// "SELECT count(*) FROM WorkplanRole WHERE deptCd =? ";

		long sn = countHqlResult(hql, orgCode);
		sn++;
		DateFormat format = new SimpleDateFormat("MMdd");
		Date date = new Date();
		String rtn = orgCode + "-" + format.format(date) + "-" + sn;
		return rtn;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void executeHql(String hql, String values) {
		planWork2Dao.batchExecute(hql, values);
	}

	/**
	 * 发送评分 提醒邮件
	 * @param meeting
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public boolean sendPointEmail(int now_year,int now_quarter,String statusCd,String sender_cd) throws SQLException, IOException {
	    /*
		Set<String> toUiids = new HashSet<String>();
	    String statusCdStr = "";
    	//开始给权重分，发评分提醒邮件给中心月计划总裁用户和中心月计划总裁办用户
	    statusCdStr="开始给权重分";
	    List<WsPlasUser> listWsPlasUser = PlasCache.getUserListByRoleCd("A_PLAN_WORK2_CEO");
	    for(int i=0;null!=listWsPlasUser&&i<listWsPlasUser.size();i++){
	    	WsPlasUser wsUaapUser = (WsPlasUser)listWsPlasUser.get(i);
	    	toUiids.add(wsUaapUser.getUiid());
	    }
	    List<WsPlasUser> listWsPlasUser2 = PlasCache.getUserListByRoleCd("A_PLAN_WORK2_OFFICE");
	    for(int i=0;null!=listWsPlasUser2&&i<listWsPlasUser2.size();i++){
	    	WsPlasUser wsUaapUser = (WsPlasUser)listWsPlasUser2.get(i);
	    	toUiids.add(wsUaapUser.getUiid());
	    }
	    if(!"".equalsIgnoreCase(statusCdStr)){
		    if (toUiids.size() > 0) {
		    	String content = now_year+"年"+now_quarter+"季度开始给权重分";
				String subject = "【中心月计划评分开始提醒】:" + content.trim();
			    StringBuilder mailBody = new StringBuilder();
			    mailBody.append(content);
			    mailBody.append("<div style='font-size: 14px; line-height: 25px'><a href='#' style='text-decoration: underline; color: #000;' onclick='" +
			    		"parent.TabUtils.newTab(\"planWork2\",\"中心月计划\",parent._ctx+\"/plan/plan-work2!getAllPlan.action?if_in_weight=1\",true);'>点击进入评分>></a></div>");
				oaEmailBodyManager.sendData2Email(subject,
						mailBody.toString(), sender_cd, "0", toUiids.toArray(new String[toUiids.size()]));
		    }
	    }
	    */
	    return true;
	}
	
	/**
	 * 发送评分 提醒邮件
	 * @param meeting
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public boolean sendPointEmail(PlanWork2Status planWork2Status,String sender_cd) throws SQLException, IOException {
		/*
		if (planWork2Status == null)
			throw new IllegalArgumentException("中心月计划评分不能为空");
	    
	    Set<String> toUiids = new HashSet<String>();
	    String statusCd = planWork2Status.getStatusCd();
	    String statusCdStr = "";
	    if("4".equalsIgnoreCase(statusCd)){
	    	//开始给权重分，发评分提醒邮件给中心月计划总裁用户和中心月计划总裁办用户
		    statusCdStr="总裁开始给权重分。";
		    List<WsPlasUser> listWsPlasUser = PlasCache.getUserListByRoleCd("A_PLAN_WORK2_CEO");
		    for(int i=0;null!=listWsPlasUser&&i<listWsPlasUser.size();i++){
		    	WsPlasUser wsUaapUser = (WsPlasUser)listWsPlasUser.get(i);
		    	toUiids.add(wsUaapUser.getUiid());
		    }
		    List<WsPlasUser> listWsPlasUser2 = PlasCache.getUserListByRoleCd("A_PLAN_WORK2_OFFICE");
		    for(int i=0;null!=listWsPlasUser2&&i<listWsPlasUser2.size();i++){
		    	WsPlasUser wsUaapUser = (WsPlasUser)listWsPlasUser2.get(i);
		    	toUiids.add(wsUaapUser.getUiid());
		    }
	    }else if("5".equalsIgnoreCase(statusCd)){
	    	//总裁给权重分完毕，发评分提醒邮件给该机构负责人和项目管理中心用户
		    statusCdStr="总裁给权重分完毕，各中心自评。";
			WsPlasOrg org = PlasCache.getOrgByCd(planWork2Status.getCenterCd());
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
		    List<WsPlasUser> listWsPlasUser2 = PlasCache.getUserListByRoleCd("A_PLAN_WORK2_PROJECT");
		    WsPlasUser user = null;
		    for(int i=0;null!=listWsPlasUser2&&i<listWsPlasUser2.size();i++){
		    	user = listWsPlasUser2.get(i);
		    	toUiids.add(user.getUiid());
		    }
	    }else if("6".equalsIgnoreCase(statusCd)){
	    	//各中心自评完毕，发评分提醒邮件给中心月计划副总裁用户
		    statusCdStr="各中心自评完毕，副总裁评分。";
		    List<WsPlasOrg> tmpList = PlasCache.getLogicalBubbleOrgListByOrgCd(planWork2Status.getCenterCd());
		    if(tmpList != null && tmpList.size() > 0){
			    for (WsPlasOrg tmpOrg : tmpList) {
					if(DictContants.UAAP_ORG_TYPE_BRANCH.equals(tmpOrg.getOrgTypeCd())){
					    String resPerson = tmpOrg.getOrgMgrId();
					    String[] ids = null;
					    if (StringUtils.isNotBlank(resPerson)) {
					    	ids = resPerson.split(";");
							for (String id : ids) {
							    if (StringUtils.isNotBlank(id)) {
							    	toUiids.add(id);
							    }
							}
					    }
					    break;
					}
				}
		    }
	    }else if("7".equalsIgnoreCase(statusCd)){
	    	//副总裁评分完毕，发评分提醒邮件给中心月计划总裁办用户
		    statusCdStr="副总裁评分完毕，总裁办或者项目管理中心考评。";
		    List<WsPlasUser> listWsPlasUser = PlasCache.getUserListByRoleCd("A_PLAN_WORK2_OFFICE");
		    WsPlasUser user1 = null;
		    for(int i=0;null!=listWsPlasUser&&i<listWsPlasUser.size();i++){
		    	user1 = listWsPlasUser.get(i);
		    	toUiids.add(user1.getUiid());
		    }
		    WsPlasUser user2 = null;
		    List<WsPlasUser> listWsPlasUser2 = PlasCache.getUserListByRoleCd("A_PLAN_WORK2_PROJECT");
		    for(int i=0;null!=listWsPlasUser2&&i<listWsPlasUser2.size();i++){
		    	user2 = listWsPlasUser2.get(i);
		    	toUiids.add(user2.getUiid());
		    }
	    }else if("8".equalsIgnoreCase(statusCd)){
	    	//总裁办或者项目管理中心考评完毕，发评分提醒邮件给中心月计划总裁用户
		    statusCdStr="总裁办或者项目管理中心考评完毕，总裁给最终分数。";
		    List<WsPlasUser> listWsPlasUser = PlasCache.getUserListByRoleCd("A_PLAN_WORK2_CEO");
		    WsPlasUser user = null;
		    for(int i=0;null!=listWsPlasUser&&i<listWsPlasUser.size();i++){
		    	user = listWsPlasUser.get(i);
		    	toUiids.add(user.getUiid());
		    }
	    }else if("9".equalsIgnoreCase(statusCd)){
	    	//总裁给最终分数完毕，发评分提醒邮件给该机构负责人和中心月计划总裁办用户
		    statusCdStr="总裁给最终分数完毕";
			WsPlasOrg wsUaapOrg = PlasCache.getOrgByCd(planWork2Status.getCenterCd());
		    String resPerson = wsUaapOrg.getOrgMgrId();
		    String[] ids = null;
		    if (StringUtils.isNotBlank(resPerson)) {
		    	ids = resPerson.split(";");
				for (String id : ids) {
				    if (StringUtils.isNotBlank(id)) {
				    	toUiids.add(id);
				    }
				}
		    }
		    WsPlasUser user2 = null;
		    List<WsPlasUser> listWsPlasUser2 = PlasCache.getUserListByRoleCd("A_PLAN_WORK2_OFFICE");
		    for(int i=0;null!=listWsPlasUser2&&i<listWsPlasUser2.size();i++){
		    	user2 = listWsPlasUser2.get(i);
		    	toUiids.add(user2.getUiid());
		    }
	    }
	    if(!"".equalsIgnoreCase(statusCdStr)){
		    if (toUiids.size() > 0) {
		    	String content = CodeNameUtil.getDeptNameByCd(planWork2Status.getCenterCd())+"中心"+planWork2Status.getPlanYear()+"年"+planWork2Status.getPlanQuarter()+"季度评分提醒，现在状态："+statusCdStr;
				String subject = "【中心月计划评分提醒】:" + content.trim();
			    StringBuilder mailBody = new StringBuilder();
			    mailBody.append(content);
			    mailBody.append("<div style='font-size: 14px; line-height: 25px'><a href='javascript:" +
			    		"parent.TabUtils.newTab(\"153\",\"中心月计划\",parent._ctx+\"/plan/plan-work2!getAllPlan.action?if_in_weight=1&centerCd="
			    		+planWork2Status.getCenterCd()+"\",true);' style='text-decoration: underline; color: #000;'>点击进入评分>></a></div>");
				oaEmailBodyManager.sendData2Email(subject,
						mailBody.toString(), sender_cd, "0", toUiids.toArray(new String[toUiids.size()]));
		    }
	    }
	    */
	    return true;
	}
	
	/**
	 * 发送内容提醒邮件
	 * @param meeting
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public boolean sendRemindEmail(PlanWork2 planWork2,String sender_cd) throws SQLException, IOException {
	    if (planWork2 == null)
			throw new IllegalArgumentException("中心月计划记录不能为空");
	    
	    Set<String> toUiids = new HashSet<String>();
		WsPlasOrg org = PlasCache.getOrgByCd(planWork2.getCenterCd());
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
	    	String content = planWork2.getContent();
			String subject = "【中心月计划提醒】" + planWork2.getSerialNumber()+planWork2.getSerialOrder() + ":" + content.trim();
			oaEmailBodyManager.sendData2Email(subject,buildMailBody(planWork2, content), sender_cd, "0", toUiids.toArray(new String[toUiids.size()]));
	    }
	    return true;
	}
	
	/**
	 * 构造内容提醒邮件体
	 */
	private String buildMailBody(PlanWork2 planWork2, String content) throws SQLException, IOException {
	    StringBuilder mailBody = new StringBuilder();
	    mailBody.append("<div style='font-size: 14px; line-height: 25px'><a href='javascript:" +
	    		"parent.TabUtils.newTab(\"153\",\"中心月计划\",parent._ctx+\"/plan/plan-work!getAllPlan.action?opened_entityid="
	    		+planWork2.getPlanWork2Id()+"\",true);' style='text-decoration: underline; color: #000;'>点击查看详细信息>></a></div>");
	    mailBody.append("<div style='font-size: 14px; line-height: 25px'>任务内容：" + content + "</div>");
	    mailBody.append("<div style='font-size: 14px; line-height: 25px'>目标时间：" + DateOperator.formatDate(planWork2.getTargetDate(), "yyyy-MM-dd") + "</div>");
	    mailBody.append("<div style='font-size: 14px;'>回复：");
	    List<PlanWork2Message> planWork2Messages = planWork2.getPlanWork2Messages();
	    if (planWork2Messages.size() == 0) {
	        mailBody.append("暂无回复</div>");
	    } else {
	        mailBody.append("<br/><ul style='list-style:none; margin-left: 40px;'>");
	        for (PlanWork2Message c : planWork2Messages) {
	            mailBody.append("<li style='line-height:18px;'><strong>" + CodeNameUtil.getUserNameByCd(c.getCreator()) + "(" + DateOperator.formatDate(c.getCreatedDate(), "yyyy-MM-dd") + ")：</strong>");
	            String comment = c.getContent();
	            mailBody.append(comment + "</li>");
	        }
	        mailBody.append("</ul></div>");
	    }
	    return mailBody.toString();
	}
}
