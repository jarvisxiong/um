package com.hhz.ump.dao.plan;

import java.util.ArrayList;
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
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.CostExecutionPlanMain;
import com.hhz.ump.entity.plan.CostExecutionPlanNode;
import com.hhz.ump.entity.plan.CostProjectNodeRel;
import com.hhz.ump.entity.plan.PlanCenterDetailsVO;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.uums.entity.ws.WsPlasOrg;

@Service
@Transactional
public class CostExecutionPlanMainManager extends BaseService<CostExecutionPlanMain, String> {

	private Logger logger = Logger.getLogger(CostExecutionPlanMainManager.class);

	@Autowired
	private CostExecutionPlanMainDao costExecutionPlanMainDao;
	@Autowired
	private CostProjectNodeRelManager costProjectNodeRelManager;
	@Autowired
	private CostExecutionPlanNodeManager costExecPlanNodeManager;
	@Autowired
	private PlanOperationLogManager planOperationLogManager;

	@Transactional(readOnly = true)
	public CostExecutionPlanMain getCostExecutionPlanMain(String id) {
		return costExecutionPlanMainDao.get(id);
	}

	public List<CostExecutionPlanMain> getAllCostExecutionPlanMain() {
		return costExecutionPlanMainDao.getAll();
	}

	public void saveCostExecutionPlanMain(CostExecutionPlanMain costExecutionPlanMain) {
		PowerUtils.setEmptyStr2Null(costExecutionPlanMain);
		costExecutionPlanMainDao.save(costExecutionPlanMain);
	}

	public void deleteCostExecutionPlanMain(String id) {
		costExecutionPlanMainDao.delete(id);
	}

	@Override
	public HibernateDao<CostExecutionPlanMain, String> getDao() {
		return costExecutionPlanMainDao;
	}

	/**
	 * 根据项目CD获取该项目的CostExecutionPlanMain记录，不能多于一条，如果没有则新建一条记录
	 * 
	 * @param projCd
	 * @return
	 */
	public CostExecutionPlanMain getPlanMainByProjCd(String projCd) {
		List<CostExecutionPlanMain> list = this.getProjectListByProjectCd(projCd);
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
	public CostExecutionPlanMain getExecutionPlanMainByProjCd(String projectCd, String planTypeCd) {
		if (StringUtils.isBlank(projectCd))
			throw new IllegalArgumentException("项目CD不能为空");

		List<CostExecutionPlanMain> results = this.getProjectListByProjectCd(projectCd);

		// 如果未建项目计划记录，则新建一条项目计划
		if (results == null || results.size() == 0) {
			CostExecutionPlanMain pm = new CostExecutionPlanMain();
			pm.setProjectCd(projectCd);
			pm.setProjectName(CodeNameUtil.getDeptNameByCd(projectCd));
			this.saveCostExecutionPlanMain(pm);

			// 初始化节点
			initProjectNodeRel(pm, planTypeCd, projectCd);

			return pm;
		}
		if (results.size() > 1) {
			logger.warn("一个项目不能有多于一条的CostExecutionPlanMain记录.");
			throw new RuntimeException("一个项目不能有多于一条的CostExecutionPlanMain记录.");
		}

		// 搜索指定类型的节点
		CostExecutionPlanMain main = results.get(0);
		List<CostProjectNodeRel> list = costProjectNodeRelManager.getProjectNodeRelList(projectCd, planTypeCd);
		if (list == null || list.size() == 0) {
			logger.info(" 初始化数据项目: " + projectCd + " , planTypeCd = " + planTypeCd);
			initProjectNodeRel(main, planTypeCd, projectCd);
		}
		return main;
	}
 
	/**
	 * 功能: 初始化节点
	 * @param main
	 * @param planTypeCd
	 * 
	 */
	public void initProjectNodeRel(CostExecutionPlanMain main, String planTypeCd, String projectCd) {
		if (main == null)
			throw new IllegalArgumentException("参数不能为null.");

		List<CostExecutionPlanNode> allNodes = costExecPlanNodeManager.getCostExecutionNodeList(planTypeCd);
		String tmpCd = null;
		String tmpName = null;
		for (CostExecutionPlanNode n : allNodes) {
			CostProjectNodeRel r = new CostProjectNodeRel();
			r.setNodeCd(n.getNodeCd());
			r.setNodeName(n.getNodeName());

			tmpCd = n.getResOrgCd();
			tmpName = n.getResOrgName();
			//主责方是"地产公司",则默认projectCd
			if(StringUtils.isBlank(tmpCd) && StringUtils.isNotBlank(tmpName) && "地产公司".equals(tmpName.trim())){
				tmpCd = projectCd;
			}
			r.setResOrgCd(tmpCd);//主责方CD
			r.setResOrgName(tmpName);//主责方NAME
			
			r.setCorOrgCd(n.getCorOrgCd());//配合方CD
			r.setCorOrgName(n.getCorOrgName());//配合方NAME
			r.setControlNodeFlg(n.getControlNodeFlg());
			r.setOutputFiles(n.getOutputFiles());
			r.setDeletedFlg("0");//是否删除:1-是 0-否
			r.setPlanTypeCd(n.getPlanTypeCd());
			r.setCostExecutionPlanMain(main);

			costProjectNodeRelManager.saveCostProjectNodeRel(r);
		}
	}

	/**
	 * 获取所有项目公司列表(所有项目)
	 * 
	 * @return
	 */
	public List<WsPlasOrg> fetchAllProjects() {
		List<WsPlasOrg> allProjs = new ArrayList<WsPlasOrg>();
		List<CostExecutionPlanMain> allPlanMain = getAll();
		WsPlasOrg org = null;
		for (CostExecutionPlanMain m : allPlanMain) {
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

		CostExecutionPlanMain planMain = this.getEntity(execPlanMainId);
		String projCd = planMain.getProjectCd();

		String fieldName = "";
		if (DictContants.PLAN_TYPE_COST.equals(planTypeCd)) {
			fieldName = " reminder ";
		} else if (DictContants.PLAN_TYPE_COST_PRE.equals(planTypeCd)) {
			fieldName = " reminder2 ";
		} else {
			System.out.println("传入的计划类型不合法!" + planTypeCd);
			return false;
		}

		String hql = "update CostExecutionPlanMain set " + fieldName + " =:reminder where costExecutionPlanMainId=:planMainId";
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
	 * 功能: 根据中心编号\执行计划,搜索项目清单
	 * 
	 * @param centerCd
	 * @param planTypeCd
	 * @return
	 */
	public List<PlanCenterDetailsVO> getProjectList(String centerCd, String planTypeCd) {

		StringBuffer hql = new StringBuffer()
				.append(" select distinct a.costExecutionPlanMainId,a.projectCd,a.projectName ")
				.append("   from CostExecutionPlanMain a, CostProjectNodeRel b ")
				.append("  where a.costExecutionPlanMainId = b.costExecutionPlanMain.costExecutionPlanMainId ")
				.append("    and b.resOrgCd =:centerCd ").append("    and b.planTypeCd =:planTypeCd ")
				.append("  order by a.projectName");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("centerCd", centerCd);
		params.put("planTypeCd", planTypeCd);

		List<Object> list = costExecutionPlanMainDao.find(hql.toString(), params);
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
				.append("  select distinct a.costExecutionPlanMainId,a.projectCd,a.projectName ")
				.append("    from CostExecutionPlanMain a, CostProjectNodeRel b ")
				.append("   where a.costExecutionPlanMainId = b.costExecutionPlanMain.costExecutionPlanMainId ")
				.append("     and b.resOrgCd =:resOrgCd ").append("     and b.planTypeCd =:planTypeCd ")
				.append(" order by a.projectName ");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("resOrgCd", resOrgCd);
		params.put("planTypeCd", planTypeCd);
		List<Object> list = costExecutionPlanMainDao.find(hql.toString(), params);
		if (list == null)
			return new ArrayList<Object>();
		return list;
	}

	/**
	 * 功能: 根据项目, 搜索项目
	 * 
	 * @param projectCd
	 * @param planTypeCd
	 * 
	 * @return
	 */
	public List<CostExecutionPlanMain> getProjectListByProjectCd(String projectCd) {

		List result = costExecutionPlanMainDao.createCriteria(CostExecutionPlanMain.class)
				.add(Restrictions.eq("projectCd", projectCd)).list();
		if (result == null || result.size() == 0)
			return new ArrayList<CostExecutionPlanMain>();
		return result;
	}
}