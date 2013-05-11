package com.hhz.ump.dao.planold;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.planold.PlanOperationLogOld;
import com.hhz.ump.util.DictContants;

@Service
@Transactional
public class PlanOperationLogOldManager extends BaseService<PlanOperationLogOld, String> {
	@Autowired
	private PlanOperationLogOldDao planOperationLogDao;

	// 操作类型
	/**
	 * 新增
	 */
	public static final String OPERATION_TYPE_ADD = "1";
	/**
	 * 修改
	 */
	public static final String OPERATION_TYPE_UPDATE = "2";

	/**
	 * 上传附件
	 */
	public static final String OPERATION_TYPE_UPLOADATTACH = "3";
	/**
	 * 删除附件
	 */
	public static final String OPERATION_TYPE_DELETEATTACH = "4";
	/**
	 * 删除
	 */
	public static final String OPERATION_TYPE_DELETE = "5";
	/**
	 * 邮件提醒
	 */
	public static final String OPERATION_TYPE_EMAIL = "6";

	/**
	 * 执行计划
	 */
	public static final String MODULE_EXEC_PLAN = "execPlan";
	/**
	 * 前期计划
	 */
	public static final String MODULE_PRE_PLAN = "prePlan";
	/**
	 * 成本计划
	 */
	public static final String MODULE_COST_PLAN = "costPlan";

	/**
	 * 执行计划模板
	 */
	public static final String MODULE_EXEC_PLAN_TEMP = "execPlanTemp";

	/**
	 * 前期计划模板
	 */
	public static final String MODULE_PRE_PLAN_TEMP = "prePlanTemp";
	/**
	 * 成本计划模板
	 */
	public static final String MODULE_COST_PLAN_TEMP = "costPlanTemp";
	/**
	/**
	 * 执行总裁任务
	 */
	public static final String MODULE_CEO_TASK = "ceoTask";
	/**
	 * 专项任务
	 */
	public static final String MODULE_SPECIAL_TASK = "specialTask";
	/**
	 * 计划管理
	 */
	public static final String MODULE_WORK_PLAN = "workPlan";
	/**
	 * 中心内部计划
	 */
	public static final String MODULE_PLAN_WORK_CENTER = "planWorkCenter";
	/**
	 * 短信发送失败
	 */
	public static final String MODULE_SMS_ERROR = "smsError";

	public void savePlanOperationLog(PlanOperationLogOld planOperationLog) {
		PowerUtils.setEmptyStr2Null(planOperationLog);
		planOperationLogDao.save(planOperationLog);
	}

	public void deletePlanOperationLog(String id) {
		planOperationLogDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<PlanOperationLogOld, String> getDao() {
		return planOperationLogDao;
	}

	 /**
	 * 保存一条操作记录
	 * @param modifiedObj 被修改对象
	 * @param operationType 操作类型
	 * @param projCd 项目CD
	 * @param operationLog 操作记录
	 public void addNewlog(String modifiedObj, String operationType, String projCd, String operationLog) {
		 PlanOperationLogOld opLog = new PlanOperationLogOld();
		 opLog.setModifiedObject(modifiedObj);
		 opLog.setOperationType(operationType);
		 opLog.setProjectCd(projCd);
		 opLog.setOperationLog(operationLog);
		 savePlanOperationLog(opLog);
	 }
	 */

	/**
	 * 添加模块操作日志 - 执行计划/前期计划/成本计划
	 * 
	 * @param planTypeCd
	 *            业务类型
	 * @param bizEntityId
	 *            业务ID
	 * @param modifiedObj
	 *            被修改对象
	 * @param operationType
	 *            操作类型
	 * @param projCd
	 *            项目CD
	 * @param operationLog
	 *            操作记录
	 * @throws Exception 
	 */
	            
	public void addPlanLog(String planTypeCd, String bizEntityId, String modifiedObj, String operationType, String projCd,
			String operationLog) throws Exception {

		if(DictContants.PLAN_TYPE_PRE.equals(planTypeCd)){
			this.addLog(PlanOperationLogOldManager.MODULE_PRE_PLAN, bizEntityId, modifiedObj, operationType, projCd, null,
					operationLog);
		}
		else if(DictContants.PLAN_TYPE_EXC.equals(planTypeCd)){
			this.addLog(PlanOperationLogOldManager.MODULE_EXEC_PLAN, bizEntityId, modifiedObj, operationType, projCd, null,
					operationLog);
		}
		else if(DictContants.PLAN_TYPE_COST.equals(planTypeCd)){
			this.addLog(PlanOperationLogOldManager.MODULE_COST_PLAN, bizEntityId, modifiedObj, operationType, projCd, null,
					operationLog);
		} 
		else
			throw new Exception("记录日志,参数 计划类型不合法!");
	}

	public void addTemplateLog(String planTypeCd, String bizEntityId, String modifiedObj, String operationType, String projCd,
			String operationLog) throws Exception {
		if(DictContants.PLAN_TYPE_EXC.equals(planTypeCd)){
			this.addLog(PlanOperationLogOldManager.MODULE_EXEC_PLAN_TEMP, bizEntityId, modifiedObj, operationType, projCd, null,
					operationLog);
		}
		else if(DictContants.PLAN_TYPE_PRE.equals(planTypeCd)){
			this.addLog(PlanOperationLogOldManager.MODULE_PRE_PLAN_TEMP, bizEntityId, modifiedObj, operationType, projCd, null,
					operationLog);
		}
		else if(DictContants.PLAN_TYPE_COST.equals(planTypeCd)){
			this.addLog(PlanOperationLogOldManager.MODULE_COST_PLAN_TEMP, bizEntityId, modifiedObj, operationType, projCd, null,
					operationLog);
		}
		else
			throw new Exception("记录日志,参数 模板类型不合法!");
	}

	/**
	 * 添加 模块操作日志 - 执行总裁任务
	 * 
	 * @param bizEntityId
	 *            业务ID
	 * @param modifiedSn
	 *            编号
	 * @param operationType
	 *            操作类型
	 * @param operationLog
	 *            操作记录
	 */
	public void addCEOTaskLog(String bizEntityId, String modifiedSn, String operationType, String operationLog) {
		this.addLog(PlanOperationLogOldManager.MODULE_CEO_TASK, bizEntityId, modifiedSn, operationType, null, null,
				operationLog);
	}

	/**
	 * 添加模块操作日志 - 专项任务
	 * 
	 * @param bizEntityId
	 *            业务ID
	 * @param modifiedSn
	 *            编号
	 * @param operationType
	 *            操作类型
	 * @param operationLog
	 *            操作记录
	 */
	public void addSpecialTaskLog(String bizEntityId, String modifiedSn, String operationType, String operationLog) {
		this.addLog(PlanOperationLogOldManager.MODULE_SPECIAL_TASK, bizEntityId, modifiedSn, operationType, null, null,
				operationLog);
	}

	/**
	 * 添加 模块操作日志 - 计划管理
	 * 
	 * @param bizEntityId
	 *            业务ID
	 * @param modifiedSn
	 *            被修改对象的编号
	 * @param operationType
	 *            操作类型
	 * @param projCd
	 *            部门CD
	 * @param operationLog
	 *            操作记录
	 */
	public void addWorkPlanLog(String bizEntityId, String modifiedSn, String operationType, String deptCd,
			String operationLog) {
		this.addLog(PlanOperationLogOldManager.MODULE_WORK_PLAN, bizEntityId, modifiedSn, operationType, null, deptCd,
				operationLog);
	}

	public void addPlanWorkCenterLog(String bizEntityId, String modifiedSn, String operationType, String deptCd,
			String operationLog) {
		this.addLog(PlanOperationLogOldManager.MODULE_PLAN_WORK_CENTER, bizEntityId, modifiedSn, operationType, null,
				deptCd, operationLog);
	}

	public void addLog(String moduleCd, String bizEntityId, String modifiedObj, String operationType, String projCd,
			String deptCd, String operationLog) {
		PlanOperationLogOld opLog = new PlanOperationLogOld();
		opLog.setBizEntityId(bizEntityId);
		opLog.setModuleCd(moduleCd);
		opLog.setModifiedObject(modifiedObj);
		opLog.setOperationType(operationType);
		opLog.setProjectCd(projCd);
		opLog.setDeptCd(deptCd);
		opLog.setOperationLog(operationLog);
		savePlanOperationLog(opLog);
	}

	public Map<String, String> getOperTypeMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(OPERATION_TYPE_ADD, "新增");
		map.put(OPERATION_TYPE_UPDATE, "修改");
		map.put(OPERATION_TYPE_DELETE, "删除");
		map.put(OPERATION_TYPE_UPLOADATTACH, "上传附件");
		map.put(OPERATION_TYPE_DELETEATTACH, "删除附件");
		map.put(OPERATION_TYPE_EMAIL, "邮件提醒");
		return map;
	}
}
