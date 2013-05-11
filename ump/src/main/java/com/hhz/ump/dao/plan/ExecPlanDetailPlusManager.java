package com.hhz.ump.dao.plan;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.ExecPlanDetailPlus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;


@Service
@Transactional
public class ExecPlanDetailPlusManager extends BaseService<ExecPlanDetailPlus, String> {
	@Autowired
	private ExecPlanDetailPlusDao execPlanDetailPlusDao;

	public void saveExecPlanDetailPlus(ExecPlanDetailPlus execPlanDetailPlus) {
		PowerUtils.setEmptyStr2Null(execPlanDetailPlus);
		execPlanDetailPlusDao.save(execPlanDetailPlus);
	}

	public void deleteExecPlanDetailPlus(String id) {
		execPlanDetailPlusDao.delete(id);
	}
	
	@Override
	public HibernateDao<ExecPlanDetailPlus, String> getDao() {
		return execPlanDetailPlusDao;
	}
	
}

