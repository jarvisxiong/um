package com.hhz.ump.dao.jbpm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.jbpm.JbpmTaskCandidate;

@Service
@Transactional
public class JbpmTaskCandidateManager extends BaseService<JbpmTaskCandidate, String> {
	@Autowired
	private JbpmTaskCandidateDao jbpmTaskCandidateDao;

	// JbpmTaskCandidate Manager //
	@Transactional(readOnly = true)
	public JbpmTaskCandidate getJbpmTaskCandidate(String id) {
		return jbpmTaskCandidateDao.get(id);
	}
	
	public List<JbpmTaskCandidate> getAllJbpmTaskCandidate() {
		return jbpmTaskCandidateDao.getAll();
	}
	
	public void saveJbpmTaskCandidate(JbpmTaskCandidate jbpmTaskCandidate) {
		PowerUtils.setEmptyStr2Null(jbpmTaskCandidate);
		jbpmTaskCandidateDao.save(jbpmTaskCandidate);
	}

	public void deleteJbpmTaskCandidate(String id) {
		jbpmTaskCandidateDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<JbpmTaskCandidate, String> getDao() {
		return jbpmTaskCandidateDao;
	}
	
	/**
	 * 对总裁例会和专项任务保存责任人
	 * @param jbpmTask
	 * @param resPerson
	 */
	public void saveResPerson(JbpmTask jbpmTask, String resPerson) {
	    if (jbpmTask == null)
			throw new IllegalArgumentException("任务不能为空!");
	    
	    List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
	    filters.add(new PropertyFilter("EQS_jbpmTask.jbpmTaskId", jbpmTask.getJbpmTaskId()));
	    List<JbpmTaskCandidate> cands = this.find(filters);
	    JbpmTaskCandidate cand = null;
	    if (cands.size() == 0) {
		cand = new JbpmTaskCandidate();
		cand.setJbpmTask(jbpmTask);
	    } else {
		cand = cands.get(0);
	    }
	    cand.setUserCd(resPerson);
	    saveJbpmTaskCandidate(cand);
	}
	
	/**
	 * 删除某条任务下的所有责任人
	 * @param jbpmTask
	 */
	public void deleteCandOfTask(JbpmTask jbpmTask) {
	    if (jbpmTask == null)
			throw new IllegalArgumentException("任务不能为空!");
	    
	    List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
	    filters.add(new PropertyFilter("EQS_jbpmTask.jbpmTaskId", jbpmTask.getJbpmId()));
	    List<JbpmTaskCandidate> cands = this.find(filters);
	    for (JbpmTaskCandidate c : cands) {
		this.delete(c);
	    }
	}
}

