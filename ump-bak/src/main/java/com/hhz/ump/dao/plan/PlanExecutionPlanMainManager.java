package com.hhz.ump.dao.plan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.CostCtrlPlanRelVO;
import com.hhz.ump.entity.plan.CostDetailVO;
import com.hhz.ump.entity.plan.PlanCenterDetailsVO;
import com.hhz.ump.entity.plan.PlanDetailVO;
import com.hhz.ump.entity.plan.PlanExecutionPlanMain;
import com.hhz.ump.entity.plan.PlanExecutionPlanNode;
import com.hhz.ump.entity.plan.PlanProjectNodeRel;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.uums.entity.ws.WsPlasOrg;

@Service
@Transactional
public class PlanExecutionPlanMainManager extends BaseService<PlanExecutionPlanMain, String> {

	private Logger logger = Logger.getLogger(PlanExecutionPlanMainManager.class);

	@Autowired
	private PlanExecutionPlanMainDao planExecutionPlanMainDao;
	@Autowired
	private PlanProjectNodeRelManager projNodeRelManager;
	@Autowired
	private PlanExecutionPlanNodeManager planTemplateNodeManager;
	@Autowired
	private PlanOperationLogManager planOperationLogManager;

	@Transactional(readOnly = true)
	public PlanExecutionPlanMain getPlanExecutionPlanMain(String id) {
		return planExecutionPlanMainDao.get(id);
	}

	public List<PlanExecutionPlanMain> getAllPlanExecutionPlanMain() {
		return planExecutionPlanMainDao.getAll();
	}

	public void savePlanExecutionPlanMain(PlanExecutionPlanMain planExecutionPlanMain) {
		PowerUtils.setEmptyStr2Null(planExecutionPlanMain);
		planExecutionPlanMainDao.save(planExecutionPlanMain);
	}

	public void deletePlanExecutionPlanMain(String id) {
		planExecutionPlanMainDao.delete(id);
	}

	@Override
	public HibernateDao<PlanExecutionPlanMain, String> getDao() {
		return planExecutionPlanMainDao;
	}

	/**
	 * 根据项目CD获取该项目的PlanExecutionPlanMain记录，不能多于一条，如果没有则新建一条记录
	 * 
	 * @param projCd
	 * @return
	 */
	public PlanExecutionPlanMain getPlanMainByProjCd(String projCd) {
		List<PlanExecutionPlanMain> list = this.getProjectListByProjectCd(projCd);
		if (list == null || list.size() == 0)
			return null;
		else
			return list.get(0);
	}

	/**
	 * 功能: 根据projectCd,搜索项目计划;若没有项目信息,则初始化数据
	 * 
	 * @param projectCd
	 * @param planTypeCd
	 * @return
	 */
	public PlanExecutionPlanMain getExecutionPlanMainByProjCd(String projectCd, String planTypeCd) {
		planTypeCd = "1";
		if (StringUtils.isBlank(projectCd))
			throw new IllegalArgumentException("项目CD不能为空");

		List<PlanExecutionPlanMain> results = this.getProjectListByProjectCd(projectCd);

		// 如果未建项目计划记录，则新建一条项目计划
		if (results == null || results.size() == 0) {
			PlanExecutionPlanMain pm = new PlanExecutionPlanMain();
			pm.setProjectCd(projectCd);
			pm.setProjectName(CodeNameUtil.getDeptNameByCd(projectCd));
			this.savePlanExecutionPlanMain(pm);
			
			//初始化节点
			initProjectNodeRel(pm, planTypeCd, projectCd);
			
			return pm;
		}
		if (results.size() > 1) {
			logger.warn("一个项目不能有多于一条的PlanExecutionPlanMain记录.");
			throw new RuntimeException("一个项目不能有多于一条的PlanExecutionPlanMain记录.");
		}

		
		//搜索指定类型的节点
		PlanExecutionPlanMain main = results.get(0);
		List<PlanProjectNodeRel> list = projNodeRelManager.getProjectNodeRelList(projectCd, planTypeCd);
		if(list == null || list.size() == 0){
			//logger.info(" 初始化数据项目: " + projectCd + " , planTypeCd = " + planTypeCd);
			initProjectNodeRel(main, planTypeCd, projectCd);
		}
		return main;
	}

	// /**
	// * 把节点信息从节点表导入项目节点关联表，用来做数据迁移
	// * @param pm
	// */
	// private void migrateNodeData(PlanExecutionPlanMain pm) {
	// List<PlanProjectNodeRel> projNodes = pm.getPlanProjectNodeRels();
	//
	// Map<String, PlanExecutionPlanNode> nodes =
	// execPlanNodeManager.getExecPlanNodesMap();
	//
	// for (PlanProjectNodeRel nodeRel : projNodes) {
	// PlanExecutionPlanNode n = nodes.get(nodeRel.getNodeCd());
	// if (n != null) {
	// nodeRel.setNodeName(n.getNodeName());
	// nodeRel.setResOrgCd(n.getResOrgCd());
	// nodeRel.setResOrgName(n.getResOrgName());
	// nodeRel.setCorOrgCd(n.getCorOrgCd());
	// nodeRel.setCorOrgName(n.getCorOrgName());
	// nodeRel.setControlNodeFlg(n.getControlNodeFlg());
	// nodeRel.setOutputFiles(n.getOutputFiles());
	// nodeRel.setDeletedFlg("0");
	// projNodeRelManager.savePlanProjectNodeRel(nodeRel);
	// }
	// }
	// }

	/**
	 * 功能: 初始化节点
	 * @param main
	 * @param planTypeCd
	 * 
	 */
	public void initProjectNodeRel(PlanExecutionPlanMain main, String planTypeCd, String projectCd) {
		if (main == null)
			throw new IllegalArgumentException("参数不能为null.");
		

		List<PlanExecutionPlanNode> allNodes = planTemplateNodeManager.getPlanExecutionNodeList(planTypeCd);
		String tmpCd = null;
		String tmpName = null;
		String projectName = CodeNameUtil.getDeptNameByCd(projectCd);
		for (PlanExecutionPlanNode n : allNodes) {
			PlanProjectNodeRel r = new PlanProjectNodeRel();
			r.setNodeCd(n.getNodeCd());
			r.setNodeName(n.getNodeName());
			
			tmpCd = n.getResOrgCd();
			tmpName = n.getResOrgName();
			//主责方是"地产公司",则默认projectCd
			if(StringUtils.isBlank(tmpCd) && StringUtils.isNotBlank(tmpName) && "地产公司".equals(tmpName.trim())){
				tmpCd = projectCd;
				tmpName = projectName;
			}
			r.setResOrgCd(tmpCd);//主责方CD
			r.setResOrgName(tmpName);//主责方NAME
			
			r.setCorOrgCd(n.getCorOrgCd());		//配合方CD
			r.setCorOrgName(n.getCorOrgName());	//配合方NAME
			r.setControlNodeFlg(n.getControlNodeFlg());
			r.setOutputFiles(n.getOutputFiles());
			r.setDeletedFlg("0");//是否删除:1-是 0-否
			r.setPlanTypeCd(planTypeCd);
			r.setPlanExecutionPlanMain(main);
			
			//增加排序字段和节点级别  2011-03-07 lujunyun
			r.setOrder1(n.getOrder1());
			r.setOrder2(n.getOrder2());
			r.setOrder3(n.getOrder3());
			r.setTreeType(n.getTreeType());
			r.setTreeParentNodeId(n.getTreeParentNodeId());
			
			// 2011-03-22 add by huangbj
			r.setTreeParentNodeName(n.getTreeParentNodeName());
			r.setRelDisplayNodeId(n.getRelDisplayNodeId());;
			r.setRelDisplayNodeName(n.getRelDisplayNodeName());

			projNodeRelManager.savePlanProjectNodeRel(r);
		}
		
		//刷新父节点/显示节点
		refreshRelTemplateNodeId(main, projectCd, planTypeCd);
	}
	
	/**
	 * @param main
	 * @param projectCd
	 * @param planTypeCd
	 */
	private void refreshRelTemplateNodeId(PlanExecutionPlanMain main, String projectCd, String planTypeCd){
		List<PlanProjectNodeRel> list = projNodeRelManager.getProjectNodeRelList(projectCd, planTypeCd);
		String treeParentNodeId = null;
		String relDisplayNodeId = null;
		String tmpId = null;
		for (PlanProjectNodeRel rel : list) {
			treeParentNodeId = rel.getTreeParentNodeId();
			relDisplayNodeId = rel.getRelDisplayNodeId();
			if(StringUtils.isNotBlank(treeParentNodeId)){
				tmpId = projNodeRelManager.getEntityRelIdRefTemplateNode(treeParentNodeId,main.getProjectCd(),rel.getPlanTypeCd());
				if(StringUtils.isNotBlank(tmpId)){
					rel.setTreeParentNodeId(tmpId);
				}else{
					rel.setTreeParentNodeId(null);
					rel.setTreeParentNodeName("*"+rel.getTreeParentNodeName());
				}
			}
			if(StringUtils.isNotBlank(relDisplayNodeId)){
				tmpId = projNodeRelManager.getEntityRelIdRefTemplateNode(relDisplayNodeId,main.getProjectCd(),rel.getPlanTypeCd());
				if(StringUtils.isNotBlank(tmpId)){
					rel.setRelDisplayNodeId(tmpId);
				}else{
					rel.setRelDisplayNodeId(null);
					rel.setRelDisplayNodeName("*"+rel.getRelDisplayNodeName());
				}
			}
			projNodeRelManager.savePlanProjectNodeRel(rel);
		}
	}
	
	//成本控制中心: 17
	//项目管理中心:132
	//各地产公司     :projectCd

	/**
	 * 获取所有项目公司列表(所有项目)
	 * 
	 * @return
	 */
	public List<WsPlasOrg> fetchAllProjects() {
		List<WsPlasOrg> allProjs = new ArrayList<WsPlasOrg>();
		List<PlanExecutionPlanMain> allPlanMain = getAll();
		WsPlasOrg org = null;
		for (PlanExecutionPlanMain m : allPlanMain) {
			org = new WsPlasOrg();
			org.setOrgName(m.getProjectName());
			org.setOrgCd(m.getProjectCd());
			allProjs.add(org);
		}

		return allProjs;
	}

	/**
	 * 保存项目提醒人列表，保存完成后要保存操作记录
	 * 
	 * @param execPlanMainId
	 * @param reminders
	 * @param planTypeCd
	 * 
	 * @return
	 * @throws Exception 
	 */
	public boolean saveReminders(String execPlanMainId, String reminders, String planTypeCd) throws Exception {
		if (StringUtils.isBlank(execPlanMainId))
			return false;

		PlanExecutionPlanMain planMain = this.getEntity(execPlanMainId);
		String projCd = planMain.getProjectCd();

		String fieldName = "";
		if (DictContants.PLAN_TYPE_EXC.equals(planTypeCd)) {
			fieldName = " reminder ";
		} else if (DictContants.PLAN_TYPE_PRE.equals(planTypeCd)) {
			fieldName = " reminder2 ";
		} else {
			System.out.println("传入的计划类型不合法!" + planTypeCd);
			return false;
		}

		String hql = "update PlanExecutionPlanMain set " + fieldName
				+ " =:reminder where planExecutionPlanMainId=:planMainId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("reminder", reminders);
		params.put("planMainId", execPlanMainId);
		this.getDao().batchExecute(hql, params);

		String curReminderNames = CodeNameUtil.getUserNamesByUiids(reminders, ";");
		String operationLog = "项目提醒人被修改为\"" + curReminderNames + "\"";
		planOperationLogManager.addPlanLog(planTypeCd, execPlanMainId, "项目联系人", PlanOperationLogManager.OPERATION_TYPE_UPDATE,
				projCd, operationLog);

		return true;
	}

	/**
	 * 
	 * @param nowDate
	 * @param nextMonthDate
	 * @param centerCd
	 * @param projectCd
	 * @param onlyCenCdQuery
	 *            true为只需要搜索中心CD
	 * @return
	 */
	public List<PlanCenterDetailsVO> fetchCenterDetails(String nowDate, String nextMonthDate, String centerCd,
			String projectCd, boolean onlyCenCdQuery) {
		//搜索近期一个月和延期的执行计划
		List<Object> list =queryCenterPlan(nowDate,nextMonthDate,centerCd,projectCd,false);
		// 对list进行遍历，插入PlanCenterDetailsVO中
		if (list != null && list.size() > 0) {
			String nowProject = "";
			List<PlanCenterDetailsVO> listVo = new ArrayList<PlanCenterDetailsVO>();
			// true为中心搜索
			if (onlyCenCdQuery) {
				for (int i = 0; i < list.size(); i++) {
					PlanCenterDetailsVO centerVO = new PlanCenterDetailsVO();
					Object[] obj = (Object[]) list.get(i);
					if (!nowProject.equals(obj[6])) {
						centerVO.setProjectCd((String) obj[10]);
						centerVO.setProjectName((String) obj[6]);
						listVo.add(centerVO);
						nowProject = (String) obj[6];
					}
				}
			} else {
				int preDelaySum = 0;
				int preRecentSum = 0;// 近期数、延期数初始都为0
				int execDelaySum = 0;
				int execRecentSum = 0;
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					if (!nowProject.equals(obj[6])) {
						// 新增一条PlanCenterDetailsVO记录；
						preDelaySum = 0;
						preRecentSum = 0;
						execDelaySum = 0;
						execRecentSum =0;
						//obj[11] 0:前期计划  1：执行计划
						PlanCenterDetailsVO centerVO = new PlanCenterDetailsVO();
						centerVO.setProjectName((String) obj[6]);
						centerVO.setProjectCd((String) obj[10]);
						List<PlanDetailVO> detailList = new ArrayList<PlanDetailVO>();
						PlanDetailVO detailVO = new PlanDetailVO();
						detailVO.setNodeName(((String) obj[0]).replaceAll(" ", "") );
						detailVO.setResOrgName((String) obj[1]);
						detailVO.setExecutionPlanName((String) obj[2]);
						detailVO.setScheduleEndDate((Date) obj[3]);
						detailVO.setNowStatus((Integer) obj[4]);
						detailVO.setStatus((String) obj[5]);
						detailVO.setProjectName((String) obj[6]);
						detailVO.setDetailId((String) obj[7]);
						detailVO.setProjNodeId((String) obj[8]);
						detailVO.setProjPlanCd((String) obj[9]);
						detailVO.setPlanTypeCd((String) obj[11]);
						detailVO.setCostCtrlId((String)obj[12]);
						detailList.add(detailVO);
						//默认为false;
						centerVO.setExecSum(false);
						centerVO.setPlanDetailVO(detailList);
						if(detailVO.getPlanTypeCd()!=null&&"0".equals(detailVO.getPlanTypeCd())){
							//前期计划
							if (detailVO.getNowStatus() != null && detailVO.getNowStatus().compareTo(0) == 0) {
								preDelaySum++;
							} else {
								preRecentSum++;
							}
							centerVO.setPreDelaySum(preDelaySum);
							centerVO.setPreRecentSum(preRecentSum);
						}else{
							if (detailVO.getNowStatus() != null && detailVO.getNowStatus().compareTo(0) == 0) {
								execDelaySum++;
							} else {
								execRecentSum++;
							}
							centerVO.setExecDelaySum(execDelaySum);
							centerVO.setExecRecentSum(execRecentSum);
						}
						listVo.add(centerVO);
						nowProject = (String) obj[6];
					} else {
						PlanCenterDetailsVO centerVO = listVo.get(listVo.size() - 1);
						List<PlanDetailVO> detailList = centerVO.getPlanDetailVO();
						PlanDetailVO detailVO = new PlanDetailVO();
						detailVO.setNodeName(((String) obj[0]).replaceAll(" ", ""));
						detailVO.setResOrgName((String) obj[1]);
						detailVO.setExecutionPlanName((String) obj[2]);
						detailVO.setScheduleEndDate((Date) obj[3]);
						detailVO.setNowStatus((Integer) obj[4]);
						detailVO.setStatus((String) obj[5]);
						detailVO.setProjectName((String) obj[6]);
						detailVO.setDetailId((String) obj[7]);
						detailVO.setProjNodeId((String) obj[8]);
						detailVO.setProjPlanCd((String) obj[9]);
						detailVO.setPlanTypeCd((String) obj[11]);
						detailVO.setCostCtrlId((String)obj[12]);
						if(detailVO.getPlanTypeCd()!=null&&"0".equals(detailVO.getPlanTypeCd())){
							if (detailVO.getNowStatus() != null && detailVO.getNowStatus().compareTo(0) == 0) {
								preDelaySum++;
								centerVO.setPreDelaySum(preDelaySum);
							} else {
								preRecentSum++;
								centerVO.setPreRecentSum(preRecentSum);
							}
						}else{
							if (detailVO.getNowStatus() != null && detailVO.getNowStatus().compareTo(0) == 0) {
								execDelaySum++;
								centerVO.setExecDelaySum(execDelaySum);
							} else {
								execRecentSum++;
								centerVO.setExecRecentSum(execRecentSum);
							}
						}
						
						detailList.add(detailVO);
					}
				}
				for(PlanCenterDetailsVO detailVo:listVo){
					//对每个项目执行计划进行遍历，目的：查找是否有执行计划
					if(detailVo.getExecDelaySum()==0&&detailVo.getExecRecentSum()==0){
						List<Object> execList =queryCenterPlan(nowDate,nextMonthDate,centerCd,projectCd,true);
						if(execList!=null&&execList.size()>0) {
							detailVo.setExecSum(true);
						}
					}
				}
			}

			return listVo;
		}

		return null;
	}
	/**
	 * 成本计划要推送的内容
	 * @param nowDate
	 * @param nextMonthDate
	 * @param centerCd
	 * @param projectCd
	 * @param onlyCenCdQuery
	 * @return
	 */
	public List<CostCtrlPlanRelVO> fetchCostCtrlPlanRel(String nowDate, String nextMonthDate,Date lastMonthDate,String centerCd,
			 boolean onlyCenCdQuery) {
		//搜索近期一个月和延期的成本计划
		List<Object> listCb =queryCostCtrlPlanRel(nowDate,nextMonthDate,false);
		//搜索近期一个月和延期的执行计划  17为成本控制中心
		List<Object> list =queryCostPlanExecRel(nowDate,nextMonthDate,"17");
		List<CostCtrlPlanRelVO> listPlanRelVO =new ArrayList<CostCtrlPlanRelVO>();
		//对成本计划进行遍历，插入CostCtrlPlanRelVO中
		if(listCb!=null&&listCb.size()>0){
			String dept="";
			for(int i=0;i<listCb.size();i++){
				Object[] obj = (Object[]) listCb.get(i);
				if(!dept.equals(obj[0].toString())){
					//如果部门不一样，则新增记录 obj[0]\[1]为部门CD\NAME
					//0:部门CD；1:部门名；2:计划完成时间 ;3:延期或近期时间；4:项目公司名称；5:业态；6:结点名称； 7:成本半年计划ID；8:成本计划ID
					CostCtrlPlanRelVO relVO= new CostCtrlPlanRelVO();
					relVO.setCostCd((String)obj[0]);
					relVO.setCostName((String)obj[1]);
					relVO.setCostSize(1);
					List<CostDetailVO> costDetailList =new ArrayList<CostDetailVO>();
					CostDetailVO costDetailVO =createCostDetailVo(lastMonthDate, obj,2);
					costDetailList.add(costDetailVO);
					//本月或下月记录数加1
					if(((Date)obj[2]).before(lastMonthDate)){
						relVO.setCurrentMonth(1);
						relVO.setNextMonth(0);
					}else{
						relVO.setCurrentMonth(0);
						relVO.setNextMonth(1);
					}
					relVO.setCostDetailList(costDetailList);
					listPlanRelVO.add(relVO);
					dept=(String)obj[0];
				}else{
					CostDetailVO costDetailVO =createCostDetailVo(lastMonthDate, obj,2);
					for(CostCtrlPlanRelVO relVO :listPlanRelVO){
						if(relVO.getCostCd().equals(obj[0])){
							relVO.setCostSize(relVO.getCostSize()+1);
							//本月或下月记录数加1
							if(((Date)obj[2]).before(lastMonthDate)){
								relVO.setCurrentMonth(relVO.getCurrentMonth()+1);
							}else{
								relVO.setNextMonth(relVO.getNextMonth()+1);
							}
							List<CostDetailVO> detailList =relVO.getCostDetailList();
							detailList.add(costDetailVO);
						}
					}
				}
			}
		}
		// 对list进行遍历，插入CostCtrlPlanRelVO中
		if (list != null && list.size() > 0) {
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				boolean isNewCostCd =true;
				//如果部门不一样，则在listPlanRelVO中遍历，找到相同的CD插入,否则，新建
				//0:部门CD；1:部门名；2:计划完成时间 ;3:延期或近期时间；4:项目公司名称；5:业态；6:结点名称； 7:成本半年计划ID；8:成本计划ID
				for(CostCtrlPlanRelVO relVO :listPlanRelVO){
					if(relVO.getCostCd().equals(obj[0].toString())){
						//如果相等，则在此加入detailList数据
						CostDetailVO costDetailVO =createCostDetailVo(lastMonthDate, obj,3);
						relVO.setCostSize(relVO.getCostSize()+1);
						//本月或下月记录数加1
						if(((Date)obj[2]).before(lastMonthDate)){
							relVO.setCurrentMonth(relVO.getCurrentMonth()+1);
						}else{
							relVO.setNextMonth(relVO.getNextMonth()+1);
						}
						relVO.getCostDetailList().add(costDetailVO);
						isNewCostCd =false;
						break;
					}
				}
				if(isNewCostCd){
					CostCtrlPlanRelVO relVO= new CostCtrlPlanRelVO();
					relVO.setCostCd((String)obj[0]);
					relVO.setCostSize(1);
					//本月或下月记录数加1
					if(((Date)obj[2]).before(lastMonthDate)){
						relVO.setCurrentMonth(1);
						relVO.setNextMonth(0);
					}else{
						relVO.setCurrentMonth(0);
						relVO.setNextMonth(1);
					}
					relVO.setCostName((String)obj[1]);
					List<CostDetailVO> costDetailList =new ArrayList<CostDetailVO>();
					CostDetailVO costDetailVO =createCostDetailVo(lastMonthDate, obj,3);
					costDetailList.add(costDetailVO);
					relVO.setCostDetailList(costDetailList);
					listPlanRelVO.add(relVO);
				}
				
			}
		}
		for(CostCtrlPlanRelVO vo:listPlanRelVO){
			Collections.sort(vo.getCostDetailList(), new Comparator<CostDetailVO>(){
			  public int compare(CostDetailVO a1, CostDetailVO a2) {
				  return Integer.parseInt(DateOperator.compareDays(a2.getPlanCompleteTime(),a1.getPlanCompleteTime()).toString());
			  }
			});
		}
		return listPlanRelVO;
	}
	private CostDetailVO createCostDetailVo(Date lastMonthDate, Object[] obj,int planType){
		CostDetailVO costDetailVO =new CostDetailVO();
		//计划完成时间,
		if(((Date)obj[2]).before(lastMonthDate)){
			costDetailVO.setMonthDesc("本月");
		}else{
			costDetailVO.setMonthDesc("下月");
		}
		if(planType==2){
			costDetailVO.setPlanType("2");
		}else{
			costDetailVO.setPlanType("3");
		}
		costDetailVO.setDelayDesc(((Integer)obj[3]).toString());
		costDetailVO.setContent("【"+(String)obj[4]+"】"+(String)obj[5]+"#"+(String)obj[6]);
		costDetailVO.setPlanCompleteTime(((Date)obj[2]));
		costDetailVO.setDetailId((String)obj[7]);
		costDetailVO.setCostCtrlBidPurcId((String)obj[8]);
		costDetailVO.setProjectCd((String)obj[9]);
		return costDetailVO;
	}
	/**
	 * 搜索成本半年计划数据
	 * @param nowDate
	 * @param nextMonthDate
	 * @param centerCd
	 * @param projectCd
	 * @param isExecPlan  是否有成本计划
	 * @return 0:部门CD；1:部门名；2:计划完成时间 ;3:延期或近期时间；4:项目公司名称；5:业态；6:结点名称；
	 * 7:成本半年计划ID；8:成本计划ID；9:project Cd
	 */
	public List<Object> queryCostCtrlPlanRel(String nowDate, String nextMonthDate,boolean isExecPlan){
		Map<String, Object> params = new HashMap<String, Object>();
		//显示字段
		StringBuffer hql = new StringBuffer(" select c.resOrgCd,c.resOrgName,a.scheduleEndDate,");
		hql.append("(case when ((a.status =0 or a.status=1) and a.scheduleEndDate<:nowDate) then 0 else  1 end)  as nowStatus,");
		hql.append(" e.projectName,d.executionPlanName,c.nodeName,a.costExecPlanDetailId,a.costCtrlBidPurcId,e.projectCd");
		//来源表
		hql.append(" from CostExecPlanDetail a,CostExecutionPlanNode b,");
		hql.append(" CostProjectNodeRel c,PlanExecutionPlan  d,CostExecutionPlanMain e");
		//过滤条件
		hql.append(" where b.planExecutionPlanNodeId  is null and (b.resOrgCd='142' or b.resOrgCd='143')");
		//plan_type_cd为成本半年计划的类型，分为前期计划和执行计划(2)
		hql.append(" and c.nodeCd =b.nodeCd and b.planTypeCd='2'");
		hql.append(" and a.executionPlanCd =d.executionPlanCd");
		hql.append(" and c.costExecutionPlanMain.costExecutionPlanMainId =e.costExecutionPlanMainId");
		hql.append(" and a.costProjectNodeRel.costProjectNodeRelId =c.costProjectNodeRelId");
		//状态为2表示已完成
		hql.append(" and a.status!=2 and a.infoConfirmedFlg='1'");
		hql.append(" and  a.scheduleEndDate<:nextMonthDate");
		hql.append(" order by c.resOrgCd asc,(case when ((a.status =0 or a.status=1) and a.scheduleEndDate<:nowDate) then 0 else  1 end) asc,e.projectCd asc,a.scheduleEndDate asc");
		params.put("nowDate", DateOperator.parse(nowDate, "yyyy-MM-dd"));
		params.put("nextMonthDate", DateOperator.parse(nextMonthDate, "yyyy-MM-dd"));
		List<Object> list = planExecutionPlanMainDao.find(hql.toString(), params);
		return list;
	}
	/**
	 * 成本计划里面放的执行计划
	 * @param nowDate
	 * @param nextMonthDate
	 * @param isExecPlan
	 * @return
	 */
	private List<Object> queryCostPlanExecRel(String nowDate, String nextMonthDate,String centerCd){
		Map<String, Object> params = new HashMap<String, Object>();
		//根据条件搜索到相关执行计划的相关数据
		StringBuffer hql = new StringBuffer(" select b.resOrgCd,b.resOrgName,a.scheduleEndDate,");
		hql.append(" (case when ((a.status =0 or a.status=1) and a.scheduleEndDate<:nowDate) then 0 else  1 end)  as nowStatus,");
		hql.append(" e.projectName,d.executionPlanName,c.nodeName,a.planExecPlanDetailId,a.costCtrlBidPurcId,e.projectCd");
		hql.append(" from  PlanExecPlanDetail a,");
		hql.append(" PlanProjectNodeRel c,");
		hql.append(" PlanExecutionPlan d,");
		hql.append(" PlanExecutionPlanMain e,");
		hql.append(" CostExecutionPlanNode b,");
		hql.append(" PlanExecutionPlanNode f");
		//f.planExecutionPlanNodeId =e.planProjectNodeRels.planExecutionPlanNodeId and
		hql.append(" where a.planProjectNodeRel.planProjectNodeRelId =c.planProjectNodeRelId");
		hql.append(" and a.executionPlanCd =d.executionPlanCd");
		if (centerCd != null && !"".equals(centerCd)) {
			hql.append(" and c.resOrgCd =:centerCd");
			params.put("centerCd", centerCd);
		}
		hql.append(" and c.nodeCd=f.nodeCd and f.planExecutionPlanNodeId =b.planExecutionPlanNodeId");
		hql.append(" and (b.resOrgCd='142' or b.resOrgCd='143')");
		hql.append(" and c.planExecutionPlanMain.planExecutionPlanMainId =e.planExecutionPlanMainId");
		hql.append(" and d.projectCd=e.projectCd");
		hql.append(" and a.scheduleEndDate<:nextMonthDate");
		//1为执行计划
		hql.append(" and c.planTypeCd ='1' and d.planTypeCd='1' ");
		hql.append(" and a.infoConfirmedFlg =:flg and d.activeFlg =:activeFlg ");
		hql.append(" and a.status!=2");
		
		hql.append(" and e.projectCd!='243' order by b.resOrgCd asc,(case when ((a.status =0 or a.status=1) and a.scheduleEndDate<:nowDate) then 0 else  1 end) asc,e.projectCd asc,a.scheduleEndDate asc");
		
		params.put("nowDate", DateOperator.parse(nowDate, "yyyy-MM-dd"));
		params.put("nextMonthDate", DateOperator.parse(nextMonthDate, "yyyy-MM-dd"));
		params.put("flg", "1");
		params.put("activeFlg", "1");
		List<Object> list = planExecutionPlanMainDao.find(hql.toString(), params);
		return list;
	}
	/**
	 * 搜索成本计划的任务
	 */
	public List<Object> queryCenterPlan(String nowDate, String nextMonthDate, String centerCd,
			String projectCd, boolean isExecPlan){
		Map<String, Object> params = new HashMap<String, Object>();
		//根据条件搜索到相关执行计划的相关数据
		StringBuffer hql = new StringBuffer("select b.nodeName,b.resOrgName,c.executionPlanName,a.scheduleEndDate,");
		if(!isExecPlan){
			//若不要搜索执行计划，则将所有的近期计划搜索出来
			hql.append("(case when ((a.status =0 or a.status=1) and a.scheduleEndDate<:nowDate");
			hql.append(") then 0 else  1 end)  as nowStatus,");
		}
		hql.append(" a.status, d.projectName,a.planExecPlanDetailId,b.planProjectNodeRelId,c.executionPlanCd,d.projectCd,c.planTypeCd,a.costCtrlBidPurcId");
		hql.append(" from  PlanExecPlanDetail a,");
		hql.append(" PlanProjectNodeRel b, ");
		hql.append("PlanExecutionPlan c,");
		hql.append("PlanExecutionPlanMain d ");
		hql.append("where b.planProjectNodeRelId = a.planProjectNodeRel.planProjectNodeRelId ");
		hql.append(" and a.executionPlanCd =c.executionPlanCd ");
		if(!isExecPlan){
			hql.append(" and a.scheduleEndDate<:nextMonthDate ");
		}else{
			//1为执行计划
			hql.append(" and c.planTypeCd ='1' ");
		}
		hql.append(" and a.infoConfirmedFlg =:flg and c.activeFlg =:activeFlg ");
		hql.append(" and a.status!=2 and d.planExecutionPlanMainId=b.planExecutionPlanMain.planExecutionPlanMainId");
		if (centerCd != null && !"".equals(centerCd)) {
			hql.append(" and b.resOrgCd =:centerCd");
			params.put("centerCd", centerCd);
		}
		if (projectCd != null && !"".equals(projectCd)) {
			hql.append(" and d.projectCd =:projectCd");
			params.put("projectCd", projectCd);
		}
		hql.append(" and d.projectCd!='243' order by c.planTypeCd asc,d.planExecutionPlanMainId asc,a.scheduleEndDate asc");
		if(!isExecPlan){
			params.put("nowDate", DateOperator.parse(nowDate, "yyyy-MM-dd"));
			params.put("nextMonthDate", DateOperator.parse(nextMonthDate, "yyyy-MM-dd"));
		}
		params.put("flg", "1");
		params.put("activeFlg", "1");
		List<Object> list = planExecutionPlanMainDao.find(hql.toString(), params);
		return list;
	}

	/**
	 * 功能: 根据中心编号\执行计划,搜索项目清单
	 * 
	 * @param centerCd
	 * @param planTypeCd
	 * @return
	 */
	public List<PlanCenterDetailsVO> getProjectList(String centerCd, String planTypeCd) {

		StringBuffer hql = new StringBuffer()
				.append(" select distinct a.planExecutionPlanMainId,a.projectCd,a.projectName ")
				.append("   from PlanExecutionPlanMain a, PlanProjectNodeRel b ")
				.append("  where a.planExecutionPlanMainId = b.planExecutionPlanMain.planExecutionPlanMainId ")
				.append("    and b.resOrgCd =:centerCd ").append("    and b.planTypeCd =:planTypeCd ")
				.append("  order by a.projectName");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("centerCd", centerCd);
		params.put("planTypeCd", planTypeCd);

		List<Object> list = planExecutionPlanMainDao.find(hql.toString(), params);
		if (list != null && list.size() > 0) {
			List<PlanCenterDetailsVO> listVo = new ArrayList<PlanCenterDetailsVO>();
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);
				PlanCenterDetailsVO centerVO = new PlanCenterDetailsVO();
				centerVO.setMainId((String) obj[0]);
				centerVO.setProjectCd((String) obj[1]);
				centerVO.setProjectName((String) obj[2]);
				listVo.add(centerVO);
			}
			return listVo;
		}
		return null;
	}

	/**
	 * 功能: 根据主责方机构CD\计划类型,搜索所有项目
	 * 
	 * @param resOrgCd
	 * @param planTypeCd
	 * 
	 * @return
	 */
	public List<Object> getProjectListByResOrgCd(String resOrgCd, String planTypeCd) {

		StringBuffer hql = new StringBuffer()
				.append("  select distinct a.planExecutionPlanMainId,a.projectCd,a.projectName ")
				.append("    from PlanExecutionPlanMain a, PlanProjectNodeRel b ")
				.append("   where a.planExecutionPlanMainId = b.planExecutionPlanMain.planExecutionPlanMainId ")
				.append("     and b.resOrgCd =:resOrgCd ").append("     and b.planTypeCd =:planTypeCd ")
				.append(" order by a.projectName ");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("resOrgCd", resOrgCd);
		params.put("planTypeCd", planTypeCd);
		List<Object> list = planExecutionPlanMainDao.find(hql.toString(), params);
		if (list == null)
			return new ArrayList<Object>();
		return list;
	}

	/**
	 * 功能: 根据项目, 搜索项目
	 * @param projectCd
	 * @param planTypeCd
	 * 
	 * @return
	 */
	public List<PlanExecutionPlanMain> getProjectListByProjectCd(String projectCd) {

		List result = planExecutionPlanMainDao.createCriteria(PlanExecutionPlanMain.class)
											  .add(Restrictions.eq("projectCd", projectCd))
											  .list();
		if (result == null || result.size() == 0)
			return new ArrayList<PlanExecutionPlanMain>();
		return result;
	}
}