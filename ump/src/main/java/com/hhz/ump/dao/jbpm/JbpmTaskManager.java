package com.hhz.ump.dao.jbpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.core.utils.RandomUtils;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.jbpm.JbpmTaskCandidate;

@Service
@Transactional
public class JbpmTaskManager extends BaseService<JbpmTask, String> {
	@Autowired
	private JbpmTaskDao jbpmTaskDao;
	@Autowired
	private JbpmTaskCandidateDao jbpmTaskCandidateDao;

	@Autowired
	private JbpmTaskCandidateManager jbpmTaskCandidateManager;

	// JbpmTask Manager //
	@Transactional(readOnly = true)
	public JbpmTask getJbpmTask(String id) {
		return jbpmTaskDao.get(id);
	}

	/**
	 * 返回未处理的审批任务数
	 * 
	 * @return
	 */
	public long getCurTaskes() {
		String uiid = SpringSecurityUtils.getCurrentUiid();
		StringBuilder sbHQL = new StringBuilder();
		sbHQL.append("from JbpmTask as t1,JbpmTaskCandidate as t2 where t1.jbpmTaskId=t2.jbpmTask.jbpmTaskId ");
		sbHQL.append("where (t1.userCd=:PuserCd or t2.userCd=:PuserCd )");
		Map<String, Object> pram = new HashMap<String, Object>();
		pram.put("PuserCd", uiid);
		long cnt = countHqlResult(sbHQL.toString(), pram);
		return cnt;
	}

	@Override
	public void delete(JbpmTask jbpmTask) {
		if (jbpmTask != null) {
			jbpmTaskCandidateManager.delete(jbpmTask.getJbpmTaskCandidates());
			super.delete(jbpmTask);
		}
	}
	public void deleteByEntityId(String entityId){
		JbpmTask jbpmTask =getByEntityId(entityId);
		delete(jbpmTask);
	}
	public JbpmTask getByEntityId(String entityId) {
		try {
			return findUniqueBy("jbpmId", entityId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public List<JbpmTask> getAllJbpmTask() {
		return jbpmTaskDao.getAll();
	}

	public void saveJbpmTask(JbpmTask jbpmTask) {
		PowerUtils.setEmptyStr2Null(jbpmTask);
		jbpmTaskDao.save(jbpmTask);
	}

	public void deleteJbpmTask(String id) {
		jbpmTaskDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<JbpmTask, String> getDao() {
		return jbpmTaskDao;
	}
	public void createJbpmTask(String uiid,String createdCenterCd) {
		JbpmTask jbpmTask = new JbpmTask();
//		jbpmTask.setJbpmCd("");
		jbpmTask.setUserCd(uiid);
		jbpmTask.setModuleCd("execPlan");
		jbpmTask.setCreatedCenterCd(createdCenterCd);
//		jbpmTask.setStatusCd("");
		jbpmTask.setUserName(uiid);
//		jbpmTask.setApplyDate(null);
		jbpmTask.setModuleName("项目开发计划");
//		jbpmTask.setPositionCd("");
//		jbpmTask.setDeptCd("");
		jbpmTask.setJbpmId(RandomUtils.generateTmpEntityId());
//		jbpmTask.setExecutionId("");
		jbpmTask.setRecordVersion(1);
		jbpmTask.setTaskId(RandomUtils.generateTmpEntityId());
		saveJbpmTask(jbpmTask);
		JbpmTaskCandidate jbpm = new JbpmTaskCandidate();
		jbpm.setJbpmTask(jbpmTask);
		jbpm.setCreator(uiid);
		jbpm.setUserCd(uiid);
		jbpm.setRecordVersion(1);
		jbpmTaskCandidateDao.save(jbpm);
	}

}
