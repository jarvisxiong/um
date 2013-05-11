package com.hhz.ump.dao.plan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.CostCheckSchedule;

@Service
@Transactional
public class CostCheckScheduleManager extends BaseService<CostCheckSchedule, String> {
	@Autowired
	private CostCheckScheduleDao costCheckScheduleDao;
	
	//index
	public static int IDX_SCHE_TZGY_0 = 0; //图纸提供
	public static int IDX_SCHE_YFYS_1 = 1; //乙方预算
	public static int IDX_SCHE_ZXYS_2 = 2; //咨询预算
	public static int IDX_SCHE_SFHD_3 = 3; //三方核对
	public static int IDX_SCHE_CHIEF_4= 4; //*审批
	public static int IDX_SCHE_QSBC_5 = 5; //签署补充
	
	//明细的完成状态:1-是 0-否
	public static String ScheduleStatusCd_NO = "0"; 
	public static String ScheduleStatusCd_YES = "1"; 

	public void saveCostCheckSchedule(CostCheckSchedule costCheckSchedule) {
		PowerUtils.setEmptyStr2Null(costCheckSchedule);
		costCheckScheduleDao.save(costCheckSchedule);
	}

	public void deleteCostCheckSchedule(String id) {
		costCheckScheduleDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostCheckSchedule, String> getDao() {
		return costCheckScheduleDao;
	}
	
}

