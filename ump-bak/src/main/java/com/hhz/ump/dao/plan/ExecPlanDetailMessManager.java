package com.hhz.ump.dao.plan;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.ExecPlanDetailMess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class ExecPlanDetailMessManager extends BaseService<ExecPlanDetailMess, String> {
	@Autowired
	private ExecPlanDetailMessDao execPlanDetailMessDao;

	public void saveExecPlanDetailMess(ExecPlanDetailMess execPlanDetailMess) {
		PowerUtils.setEmptyStr2Null(execPlanDetailMess);
		execPlanDetailMessDao.save(execPlanDetailMess);
	}

	public void deleteExecPlanDetailMess(String id) {
		execPlanDetailMessDao.delete(id);
	}
	
	@Override
	public HibernateDao<ExecPlanDetailMess, String> getDao() {
		return execPlanDetailMessDao;
	}
	
}

