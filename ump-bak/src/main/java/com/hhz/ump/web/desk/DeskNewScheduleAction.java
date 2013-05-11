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
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.jbpm.JbpmTaskManager;
import com.hhz.ump.dao.oa.OaAllAttentionManager;
import com.hhz.ump.dao.oa.OaMeetingManager;
import com.hhz.ump.dao.oa.SpecialTaskManager;
import com.hhz.ump.dao.plan.PlanTargetManager;
import com.hhz.ump.dao.res.ResAccreditInfoManager;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.jbpm.JbpmTaskCandidate;
import com.hhz.ump.entity.oa.OaAllAttention;
import com.hhz.ump.entity.oa.OaMeeting;
import com.hhz.ump.entity.oa.SpecialTask;
import com.hhz.ump.entity.plan.PlanTarget;
import com.hhz.ump.entity.res.ResAccreditInfo;
import com.hhz.ump.service.AcgiUser;
import com.hhz.ump.uaap.util.RoleUtil;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.Util;
import com.hhz.uums.entity.ws.WsPlasRole;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author huangj 2010-3-13
 */
@Namespace("/desk2")
public class DeskNewScheduleAction extends ActionSupport {
	private static final long serialVersionUID = -6674430562551244064L;

	private static Log log = LogFactory.getLog(JbpmTaskAction.class);
	
	@Autowired
	private JbpmTaskManager jbpmTaskManager;

	@Autowired
	private OaMeetingManager oaMeetingManager;

	@Autowired
	private SpecialTaskManager specialTaskManager;
	
	@Autowired
	private PlanTargetManager planTargetManager;

	@Autowired
	protected ResAccreditInfoManager resAccreditInfoManager;

	@Autowired
	protected OaAllAttentionManager oaAllAttentionManager;

	// 显示在首页的待办事项，默认8条记录
	private Page<JbpmTask> deskHomePager = new Page<JbpmTask>(10);
	// 显示在首页的关注事项
	private Page<OaAllAttention> deskAttentionPager = new Page<OaAllAttention>();
	//private List<JbpmTask> taskResult = new ArrayList<JbpmTask>();
	//网批list
	private List<JbpmTask> resResult = new ArrayList<JbpmTask>();
	//指令单list
	private List<JbpmTask> ceoResult = new ArrayList<JbpmTask>();
	//中心内部任务
	private List<JbpmTask> centerResult = new ArrayList<JbpmTask>();
	//文件跟踪
	private List<JbpmTask> fileResult = new ArrayList<JbpmTask>();
	//成本招采管理
	private List<JbpmTask> costResult = new ArrayList<JbpmTask>();
	//
	private List<Map<String,Object>> attenList =new ArrayList<Map<String,Object>>();
	
	private Map<String, String> jbpmTaskSubjMap = new HashMap<String, String>();
	private Map<String, String> mapSubStatusCd = new HashMap<String, String>();
	private Map<String, String> mapMyRec = new HashMap<String, String>();//判断是否我的记录
	
	//首页待办事项list
	private List<Map<String,Object>> homeWaitList = new ArrayList<Map<String,Object>>();
	private boolean aWritePoint = false; // 能否读写待办节点
	private int execPlanTotalCount = 0;

	private Map<String, Long> mapCount = new HashMap<String, Long>();

	private String moduleCd;
	//网批总数
	private int resCount;
	//指令单总数
	private int ceoCount;
	//中心内部任务总数
	private int centerCount;
	//文件跟踪总数
	private int fileCount;
	//成本招采管理待办事项
	private int costCount;
	
    //当前页面展示模块
	private String showDivName;
	//人员管理,代审批总数
	private BigDecimal hrApproveCount;
	
	//选中待办事项小类
	private String selectedModuleCd ;
	//页码
	private int pageNo = 1;

	@Override
	public String execute() throws Exception {
		selectedModuleCd = Struts2Utils.getParameter("moduleCd");
		String pno = Struts2Utils.getParameter("pageNo");
		if(StringUtils.isNotBlank(pno)){
			pageNo = Integer.valueOf(Struts2Utils.getParameter("pageNo"));
			deskHomePager.setPageNo(pageNo);
		}
		
		//统计首页待办事项
		countTask();
		//人员管理模块
		getHrCount();
		if(StringUtils.isNotBlank(selectedModuleCd)){
			showDivName = selectedModuleCd;
		}
		//默认取第一个
		else if(homeWaitList != null && homeWaitList.size() >0){
			showDivName = homeWaitList.get(0).get("moduleCd").toString();
			selectedModuleCd = showDivName;
		}else{
			//没有待办直接返回
			showDivName="zero";
			selectedModuleCd="";
			return SUCCESS;
		}
		resResult = detail(showDivName);
		
		
		return SUCCESS;
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
						.append("   from plas.plas_approve_info t, plas.plas_approve_node_his t2 ")
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
	
	
	/**
	 * 待办事项细节
	 * @param moduleCdByExec
	 * @return
	 * @throws Exception
	 */
	private List<JbpmTask> detail(String moduleCdByExec) throws Exception {
		//设置是哪个模块使用待办事项
		if(StringUtils.isNotBlank(moduleCdByExec)){
			setModuleCd(moduleCdByExec);
		}
		deskHomePager.setOrderBy("applyDate");
		deskHomePager.setOrder(Page.DESC);
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
		if (null != moduleCd && !"".equalsIgnoreCase(moduleCd) && "planTarget".equalsIgnoreCase(moduleCd)) {
			//合并查出计划与内部任务
			hql.append(" and (task.moduleCd = '" + moduleCd + "'")
				.append(" or task.moduleCd = 'planWorkCenter'")
				.append(" )");
		}else if (null != moduleCd && !"".equalsIgnoreCase(moduleCd)) {
			hql.append(" and task.moduleCd = '" + moduleCd + "'");
		}

		hql.append("order by decode(task.moduleCd,'mesMeetingInfo','0','resApprove','1','travel','2','reimburse','3','oaFileFollowed','4','ceomeeting','5','planTarget','6') asc,");
		//hql.append("order by decode(task.moduleCd,'resApprove','0','travel','1','reimburse','2','oaFileFollowed','3','ceomeeting','4','planWork','5') asc,");
		hql.append("case when cond.userCd=:userCd1 or cond.userCd like :userCd2 then 0 else 1 end asc, ");
		hql.append("cond.createdDate desc ");
		param.put("userCd1", SpringSecurityUtils.getCurrentUiid());// 注意：uiid,userCd
		param.put("userCd2", "%;" + SpringSecurityUtils.getCurrentUiid() + ";%");
		deskHomePager = jbpmTaskManager.findPage(deskHomePager, hql.toString(), param);

		List<JbpmTask> taskResult = buildTaskSubject(deskHomePager.getResult());

		return taskResult;
	}
	
	/**
	 * 统计首页待办事项
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void countTask() throws Exception{
		List list = null;
		Map<String, Object> param = new HashMap<String, Object>();
		//计划包括内部任务
		int waitNum = 0;
		boolean waitCdFlag = true;
		
		StringBuffer sql = new StringBuffer("select t1.module_cd,count(t1.jbpm_task_id) num ")
		.append("from jbpm_task t1,jbpm_task_candidate t2 ")
		.append("where t1.jbpm_task_id=t2.jbpm_task_id  ")
		.append("and (t2.user_cd=:userCd1 or t2.user_cd like :userCd2 ")
		//.append("group by t1.module_cd ,t1.module_name ")
		//.append("order by decode(t1.module_cd,'resApprove','0','travel','1','reimburse','2','oaFileFollowed','3','ceomeeting','4','planWork','5') asc")
		;
		param.put("userCd1", SpringSecurityUtils.getCurrentUiid());// 注意：uiid,userCd
		param.put("userCd2", "%;" + SpringSecurityUtils.getCurrentUiid() + ";%");
		//homeWaitList = jbpmTaskManager.findBySql(sql.toString(), param);
		
		
		// --授权人记录搜索--start--网上审批
		int index = 0;
		List<ResAccreditInfo> accreditInfos = resAccreditInfoManager.loadAccInfoRes(SpringSecurityUtils
				.getCurrentUiid());
		List<String> lstUiid = PowerUtils.getProptyArray(accreditInfos, "userCd");
		if (lstUiid.size() > 0) {
			sql.append(" or (( ");
			for (String accUser : lstUiid) {
				sql.append(" t2.user_cd = :accUser").append(index);// 授权的记录
				param.put("accUser" + index, accUser);
				sql.append(" or t2.user_cd like :accUserLike").append(index);// 授权的记录
				param.put("accUserLike" + index, "%" + accUser + ";%");
				index++;
			}
			sql.append(" ) and t1.module_cd = 'resApprove' ");
			sql.append(" ) ");
		}
		sql.append(") ");
		// --授权人记录搜索-end
		
		sql.append("group by t1.module_cd ")
		.append("order by decode(t1.module_cd,'mesMeetingInfo','0','resApprove','1','travel','2','reimburse','3','oaFileFollowed','4','ceomeeting','5','planTarget','6') asc");
		
		list =  jbpmTaskManager.findBySql(sql.toString(), param);
		
		
		
		for(Object obj : list){
			Object[] objs = (Object[]) obj;
			String moduleCd = objs[0].toString();
			Map<String,Object> m =  new HashMap<String, Object>();
			m.put("moduleCd", objs[0]);
			m.put("moduleName", "");
			m.put("waitNum", objs[1]);
			
			if("planTarget".equals(moduleCd) || "planWorkCenter".equals(moduleCd)){
				int tNum = Integer.valueOf(objs[1].toString());
				waitNum  += tNum;
				
				m.put("moduleCd", "planTarget");
				m.put("moduleName", "计划");
				m.put("waitNum", waitNum);
				
				if("planTarget".equals(moduleCd)){
					waitCdFlag = false;
				}
				String targetModuleCd = "planTarget";
				if(waitCdFlag && "planWorkCenter".equals(moduleCd)){
					targetModuleCd = moduleCd;
					m.put("targetCd", targetModuleCd);
				}else{
					m.put("targetCd", targetModuleCd);
				}
				
				
			}
			
			homeWaitList.add(m);
		}
	}


	/**
	 * 如果是总裁例会或者专项任务记录，构造它们的标题
	 * 
	 * @param result
	 * @throws SQLException
	 * @throws IOException
	 */
	private List<JbpmTask> buildTaskSubject(List tasks) throws SQLException, IOException {
		List<JbpmTask> taskResult =new ArrayList<JbpmTask>();
		if (tasks.size() == 0)
			return taskResult;

		for (Object t : tasks) {
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
		}
		return taskResult;
	}
	public String oaAttention() throws Exception{
		Map<String, Object> param = new HashMap<String, Object>();
		// 设置默认排序方式
		String hql="select a from OaAllAttention a,PlanTarget b where a.userCd=:userCd and a.entityId=b.planTargetId"+
		" order by a.updatedDate desc";
		param.put("userCd", SpringSecurityUtils.getCurrentUiid());
		List<OaAllAttention> oaList= oaAllAttentionManager.find(hql, param);
		int fromNo=0;
		int toNo=0;
        if(pageNo!=0){
        	fromNo=9*(pageNo-1);
        	toNo=9*pageNo;
        }
		if(oaList!=null&&oaList.size()>0){
			Map<String,Object> map =  new HashMap<String, Object>();
			if(toNo>oaList.size()){
				toNo=oaList.size();
			}
			for(int i=fromNo;i<toNo;i++){
				OaAllAttention atten=oaList.get(i);
				if("planTarget".equals(atten.getModuleCd())){
					PlanTarget target=planTargetManager.getEntity(atten.getEntityId());
					map =  new HashMap<String, Object>();
					if(target.getContent()!=null&&target.getContent().length()>20){
						map.put("subContent", target.getContent().substring(0, 20)+"...");
					}else{
						map.put("subContent", target.getContent());
					}
					if(target.getRecordVersion()!=atten.getModuleRecordVersion()){
						map.put("newa", "1");
					}else{
						map.put("newa", "0");
					}
					map.put("content1", target.getContent());
					map.put("id", atten.getEntityId());
					map.put("center", CodeNameUtil.getDeptNameByCd(target.getCenter()));
					map.put("centerCd", target.getCenter());
					map.put("entityId", atten.getEntityId());
					map.put("date", DateOperator.formatDate(target.getTargetDate(), "yy-MM"));
					attenList.add(map);
				
				}
			}
		}
		
		return "atten";
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

	public BigDecimal getHrApproveCount() {
		return hrApproveCount;
	}

	public List<JbpmTask> getResResult() {
		return resResult;
	}

	public List<JbpmTask> getCeoResult() {
		return ceoResult;
	}

	public List<JbpmTask> getCenterResult() {
		return centerResult;
	}

	public List<JbpmTask> getFileResult() {
		return fileResult;
	}

	public int getResCount() {
		return resCount;
	}

	public int getCeoCount() {
		return ceoCount;
	}

	public int getCenterCount() {
		return centerCount;
	}

	public int getFileCount() {
		return fileCount;
	}
	public String getShowDivName() {
		return showDivName;
	}
	public List<JbpmTask> getCostResult() {
		return costResult;
	}
	public int getCostCount() {
		return costCount;
	}
	/**
	 * @return the homeWaitList
	 */
	public List<Map<String, Object>> getHomeWaitList() {
		return homeWaitList;
	}
	/**
	 * @param homeWaitList the homeWaitList to set
	 */
	public void setHomeWaitList(List<Map<String, Object>> homeWaitList) {
		this.homeWaitList = homeWaitList;
	}
	/**
	 * @return the selectedModuleCd
	 */
	public String getSelectedModuleCd() {
		return selectedModuleCd;
	}
	/**
	 * @param selectedModuleCd the selectedModuleCd to set
	 */
	public void setSelectedModuleCd(String selectedModuleCd) {
		this.selectedModuleCd = selectedModuleCd;
	}
	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public List<Map<String, Object>> getAttenList() {
		return attenList;
	}


	public void setAttenList(List<Map<String, Object>> attenList) {
		this.attenList = attenList;
	}
}
