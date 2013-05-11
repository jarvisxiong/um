/**
 * 
 */
package com.hhz.ump.web.desk;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.jbpm.JbpmTaskManager;
import com.hhz.ump.dao.oa.OaAllAttentionManager;
import com.hhz.ump.dao.oa.OaMeetingManager;
import com.hhz.ump.dao.oa.SpecialTaskManager;
import com.hhz.ump.dao.plan.ExecPlanDetailManager;
import com.hhz.ump.dao.plan.ExecPlanLayoutManager;
import com.hhz.ump.dao.plan.ExecPlanNodeManager;
import com.hhz.ump.dao.res.ResAccreditInfoManager;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.jbpm.JbpmTaskCandidate;
import com.hhz.ump.entity.oa.OaAllAttention;
import com.hhz.ump.entity.oa.OaMeeting;
import com.hhz.ump.entity.oa.SpecialTask;
import com.hhz.ump.entity.res.ResAccreditInfo;
import com.hhz.ump.service.AcgiUser;
import com.hhz.ump.uaap.util.RoleUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasRole;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author huangj 2010-3-13
 */
@Namespace("/desk")
public class JbpmTaskAction extends ActionSupport {
	private static final long serialVersionUID = -6674430562551244064L;

	private static Log log = LogFactory.getLog(JbpmTaskAction.class);
	
	@Autowired
	private JbpmTaskManager jbpmTaskManager;

	@Autowired
	private OaMeetingManager oaMeetingManager;

	@Autowired
	private SpecialTaskManager specialTaskManager;

	@Autowired
	protected ResAccreditInfoManager resAccreditInfoManager;

	@Autowired
	protected OaAllAttentionManager oaAllAttentionManager;
	@Autowired
	private ExecPlanNodeManager execPlanNodeManager;
	@Autowired
	private ExecPlanLayoutManager execPlanLayoutManager;
	@Autowired
	private ExecPlanDetailManager detailManager;

	// 显示在首页的待办事项
	private Page<JbpmTask> deskHomePager = new Page<JbpmTask>();
	// 显示在首页的关注事项
	private Page<OaAllAttention> deskAttentionPager = new Page<OaAllAttention>();
	private List<JbpmTask> taskResult = new ArrayList<JbpmTask>();
	private Map<String, String> jbpmTaskSubjMap = new HashMap<String, String>();
	private Map<String, String> mapSubStatusCd = new HashMap<String, String>();
	private Map<String, String> mapMyRec = new HashMap<String, String>();//判断是否我的记录
	private boolean aWritePoint = false; // 能否读写待办节点
	private int execPlanTotalCount = 0;

	private Map<String, Long> mapCount = new HashMap<String, Long>();

	private List oaAllAttentionList;

	private String isAll = "0";

	private String moduleCd;

	//人员管理,代审批总数
	private BigDecimal hrApproveCount;

	@Override
	public String execute() throws Exception {
		
		deskHomePager.setOrderBy("applyDate");
		deskHomePager.setOrder(Page.DESC);
		if (isAll.equals("1")) {
			deskHomePager.setPageSize(10000);
		}
		// List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		// PropertyFilter filter = new
		// PropertyFilter("EQS_jbpmTaskCandidates.userCd",
		// SpringSecurityUtils.getCurrentUiid());
		// filters.add(filter);
		// jbpmTaskManager.findPage(deskHomePager, filters);

		StringBuffer hql = new StringBuffer("select task,cond from JbpmTask as task, JbpmTaskCandidate as cond ");
		hql.append("where task.jbpmTaskId=cond.jbpmTask.jbpmTaskId and ");
		hql.append("(");
		hql.append("cond.userCd=:userCd1 or cond.userCd like :userCd2 ");

		Map<String, Object> param = new HashMap<String, Object>();
		// --授权人记录搜索--start--网上审批
		int index = 0;
		List<ResAccreditInfo> accreditInfos = resAccreditInfoManager.loadAccInfoRes(SpringSecurityUtils
				.getCurrentUiid());
		List<String> lstUiid = PowerUtils.getProptyArray(accreditInfos, "userCd");
		if (lstUiid.size() > 0) {
			hql.append(" or (( ");
			for (String accUser : lstUiid) {
				hql.append(" cond.userCd = :accUser").append(index);// 授权的记录
				param.put("accUser" + index, accUser);
				hql.append(" or cond.userCd like :accUserLike").append(index);// 授权的记录
				param.put("accUserLike" + index, "%" + accUser + ";%");
				index++;
			}
			hql.append(" ) and task.moduleCd = 'resApprove' ");
			hql.append(" ) ");
		}
		hql.append(") ");
		// --授权人记录搜索-end
		
		// 搜索类别
		if (null != moduleCd && !"".equalsIgnoreCase(moduleCd)) {
			hql.append(" and task.moduleCd like '" + moduleCd + "'");
		}

		hql.append("order by decode(task.moduleCd,'resApprove','0','travel','1','reimburse','2','ceomeeting','4','planWork','5','mesMeetingInfo','-1') asc,");
		hql.append("case when cond.userCd=:userCd1 or cond.userCd like :userCd2 then 0 else 1 end asc, ");
		hql.append("cond.createdDate desc ");

		param.put("userCd1", SpringSecurityUtils.getCurrentUiid());// 注意：uiid,userCd
		param.put("userCd2", "%;" + SpringSecurityUtils.getCurrentUiid() + ";%");
		deskHomePager = jbpmTaskManager.findPage(deskHomePager, hql.toString(), param);

		buildTaskSubject(deskHomePager.getResult());
		StringBuffer hqlCount = new StringBuffer(
				"select task.moduleCd,count(*) from JbpmTask as task, JbpmTaskCandidate as cond ")
				.append(
						" where task.jbpmTaskId=cond.jbpmTask.jbpmTaskId and (cond.userCd=:userCd1 or cond.userCd like :userCd2) ")
				.append("group by task.moduleCd");
		List lstObject = jbpmTaskManager.find(hqlCount.toString(), param);
		buildCountMap(lstObject);

		//待办事项，提示开发计划
		/*
		try {
			ExecPlanAction ep = new ExecPlanAction();
			String orgCd = SpringSecurityUtils.getCurrentDeptCd();
			List<WsPlasOrg> projects = ep.fetchAllProjects();
			boolean continue_flag = false;
			if (SpringSecurityUtils.hasRole("A_EXEC_PROJ")
					|| SpringSecurityUtils.hasRole("A_EXEC_CENTER")) {
				if (projects != null) {
					for (WsPlasOrg wo : projects) {
						if (orgCd.equals(wo.getOrgCd())|| wo.getOrgCd().equals(SpringSecurityUtils.getCurrentCenterCd())) {
							continue_flag = true;
							break;
						}
					}
				}
				if (continue_flag) {
					ExecPlanAction epa = new ExecPlanAction();
					execPlanTotalCount = epa.getExecPlanTotalCount(
							SpringSecurityUtils.getCurrentUiid(),
							SpringSecurityUtils.getCurrentCenterCd(),
							execPlanNodeManager, execPlanLayoutManager,
							detailManager);
					if (execPlanTotalCount > 0) {
						Struts2Utils.getRequest().setAttribute("moduleCd","execPlan");
						Struts2Utils.getRequest().setAttribute("aWritePoint",true);
						@SuppressWarnings("hiding")
						List<JbpmTask> taskResult = new ArrayList<JbpmTask>();
						JbpmTask jbpmTask = new JbpmTask();
						taskResult.add(jbpmTask);
						deskHomePager.setTotalCount(deskHomePager.getTotalCount() + 1);
						deskHomePager.setResult(taskResult);
					}
				}
			}
		} catch (Exception e) {
		}*/
		

		// 关注
		/*
		oaAllAttentionList = oaAllAttentionManager.getModuleEntitys(SpringSecurityUtils.getCurrentUiid());
		String str_pageSizeAttention = Struts2Utils.getRequest().getParameter("deskHomePager.pageSize");
		int pageSizeAttention = 1;
		int pageAllAttention = 1;
		try {
			pageSizeAttention = Integer.parseInt(str_pageSizeAttention);
			pageAllAttention = oaAllAttentionList.size() / pageSizeAttention;
			int res_temp = oaAllAttentionList.size() - pageSizeAttention * pageAllAttention;
			if (res_temp > 0) {
				pageAllAttention++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Struts2Utils.getRequest().setAttribute("oaAllAttentionList", oaAllAttentionList);
		Struts2Utils.getRequest().setAttribute("oaAllAttentionCount", oaAllAttentionList.size());
		Struts2Utils.getRequest().setAttribute("pageAllAttention", pageAllAttention);
		Struts2Utils.getRequest().setAttribute("pageSizeAttention", pageSizeAttention);
		*/
		
		//人员管理模块
		getHrCount();
		
		return super.execute();
	}

	private void buildCountMap(List lstObject) {
		for (Object object : lstObject) {
			Object[] elem = (Object[]) object;
			//特殊处理项目开发计划
			if(elem[0].toString().equals("execPlan")){
				mapCount.put((String) elem[0], 1l);
			}else{
				mapCount.put((String) elem[0], (Long) elem[1]);
			}
		}
	}

	/**
	 * 如果是总裁例会或者专项任务记录，构造它们的标题
	 * 
	 * @param result
	 * @throws SQLException
	 * @throws IOException
	 */
	private void buildTaskSubject(List tasks) throws SQLException, IOException {
		if (tasks.size() == 0)
			return;

		for (Object t : tasks) {
			try{
				Object[] objs = (Object[]) t;
				JbpmTask task = (JbpmTask) objs[0];
				JbpmTaskCandidate candidate = (JbpmTaskCandidate) objs[1];
				taskResult.add(task);
				mapSubStatusCd.put(task.getJbpmTaskId(), candidate.getStatusCd());
				if (StringUtils.equals(candidate.getUserCd(), SpringSecurityUtils.getCurrentUiid())){
					mapMyRec.put(task.getJbpmTaskId(), "1");
				}else{
					mapMyRec.put(task.getJbpmTaskId(), "0");
				}
				String moduleCd = task.getModuleCd();
				// 如果是总裁例会则它的content作为它的标题
				if (DictContants.JBPM_TASK_MODULE_CD_CEOMEETING.equalsIgnoreCase(moduleCd)) {
					OaMeeting m = oaMeetingManager.getEntity(task.getJbpmId());
					if (m == null)
						throw new RuntimeException("找不到当前总裁例会记录,id=" + task.getJbpmId());
					String content = Util.clob2String(m.getBusiness());
					jbpmTaskSubjMap.put(task.getJbpmTaskId(), content.trim());
				} else if (DictContants.JBPM_TASK_MODULE_CD_SPECIALTASK.equalsIgnoreCase(moduleCd)) {
					SpecialTask st = specialTaskManager.getEntity(task.getJbpmId());
					if (st == null)
						throw new RuntimeException("找不到当前专项任务记录, id=" + task.getJbpmId());
					jbpmTaskSubjMap.put(task.getJbpmTaskId(), st.getSubject());
				}
			}catch(Exception e){}
		}
	}
	public List<JbpmTask> getTaskResult() {
		return taskResult;
	}

	public Page<JbpmTask> getDeskHomePager() {
		return deskHomePager;
	}

	public void setDeskHomePager(Page<JbpmTask> deskHomePager) {
		this.deskHomePager = deskHomePager;
	}

	public Page<OaAllAttention> getDeskAttentionPager() {
		return deskAttentionPager;
	}

	public void setDeskAttentionPager(Page<OaAllAttention> deskAttentionPager) {
		this.deskAttentionPager = deskAttentionPager;
	}

	public String getIsAll() {
		return isAll;
	}

	public void setIsAll(String isAll) {
		this.isAll = isAll;
	}

	public Map<String, String> getJbpmTaskSubjMap() {
		return jbpmTaskSubjMap;
	}

	public void setJbpmTaskSubjMap(Map<String, String> jbpmTaskSubjMap) {
		this.jbpmTaskSubjMap = jbpmTaskSubjMap;
	}

	public Map<String, Long> getMapCount() {
		return mapCount;
	}

	public String getModuleCd() {
		return moduleCd;
	}

	public void setModuleCd(String moduleCd) {
		this.moduleCd = moduleCd;
	}

	public List getOaAllAttentionList() {
		return oaAllAttentionList;
	}

	public Map<String, String> getMapSubStatusCd() {
		return mapSubStatusCd;
	}

	public Map<String, String> getMapMyRec() {
		return mapMyRec;
	}

	public void setMapMyRec(Map<String, String> mapMyRec) {
		this.mapMyRec = mapMyRec;
	}
	public boolean isAWritePoint() {
		return aWritePoint;
	}

	public int getExecPlanTotalCount() {
		return execPlanTotalCount;
	}

	public void setExecPlanTotalCount(int execPlanTotalCount) {
		this.execPlanTotalCount = execPlanTotalCount;
	}
	
	/**
	 * 查看人员管理模块代办事宜
	 */
	private BigDecimal getHrCount(){

		// 	角色编码			角色名称
		//1	A_HR_GROUP		总部人事
		//2	A_BIZ_CENTER	营运中心管理员
		//3	A_HR_ADMIN		HR管理员
		//4	A_HR_AREA		区域人事(A_HR_NQ/A_HR_BQ/A_HR_SY/A_HR_HY/A_HR_YS/A_HR_JD)
		//5	A_HR_ESTATE		地产人事

		try{	
		
			//涉及的角色清单
			AcgiUser user = (AcgiUser) SpringSecurityUtils.getCurrentUser();
			List<WsPlasRole> roles = user.getRoleList();
			
			List<String> roleCdList = new ArrayList<String>();
			if(roles != null){
				for (WsPlasRole tmp : roles) {
					roleCdList.add(tmp.getRoleCd());
				}
			}
			
			//有权限的角色
			List<String> hrCdList = new ArrayList<String>();
			hrCdList.add("A_BIZ_CENTER");
			hrCdList.add("A_HR_ADMIN");
			hrCdList.add("A_HR_GROUP");

			hrCdList.add("A_HR_NQ");
			hrCdList.add("A_HR_BQ");
			hrCdList.add("A_HR_SY");
			hrCdList.add("A_HR_HY");
			hrCdList.add("A_HR_YS");
			hrCdList.add("A_HR_JD");
			
			boolean containFlg = RoleUtil.containAnyRole(roleCdList,hrCdList);
			if(containFlg){
				Long start = System.currentTimeMillis();
				//等待我审批的角色
				Map<String, Object> values = new HashMap<String, Object>();
				values.put("roleCdList", roleCdList);
				values.put("approveStatusCd", "2");
				//待办事宜
				String sql = new StringBuffer()
						.append(" select count(distinct(t.plas_approve_info_id)) ")
						.append("   from uums.plas_approve_info t, uums.plas_approve_node_his t2 ")
						.append("  where 1=1  ")
						.append("    and t2.plas_approve_node_his_id = t.next_node_id ")
						.append("    and t.approve_status_cd = :approveStatusCd")
						.append("    and t2.approve_role_cd in (:roleCdList) ")
						.toString();
				
				List t = oaMeetingManager.findBySql(sql, values);
				hrApproveCount = (BigDecimal)t.get(0);
				Long end = System.currentTimeMillis();
				log.debug("执行搜索人员审核模块, 当前用户uiid:" + SpringSecurityUtils.getCurrentUiid() +", 耗时间: " + (end-start)/1000.00 +" 秒!");
			}else{
				hrApproveCount = new BigDecimal(-1);
			}
	
		}catch(Exception e){
			log.error("执行搜索人员审核模块 出现异常! 当前用户uiid:" + SpringSecurityUtils.getCurrentUiid() ,e);
			hrApproveCount = new BigDecimal(-1);
		}
		return hrApproveCount;
	}

	public BigDecimal getHrApproveCount() {
		return hrApproveCount;
	}
}
