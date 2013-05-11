package com.hhz.ump.dao.plan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.plan.PlanTrainGolf;

@Service
@Transactional
public class PlanTrainGolfManager extends BaseService<PlanTrainGolf, String> {
	@Autowired
	private PlanTrainGolfDao planTrainGolfDao;

	public void savePlanTrainGolf(PlanTrainGolf planTrainGolf) {
		PowerUtils.setEmptyStr2Null(planTrainGolf);
		planTrainGolfDao.save(planTrainGolf);
	}

	public void deletePlanTrainGolf(String id) {
		planTrainGolfDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlanTrainGolf, String> getDao() {
		return planTrainGolfDao;
	}

	/**
	 * 读取高尔夫记录信息
	 * @param trainPeriod 第几期
	 * @param questionNo 第几题
	 * @param userCd 用户Uiid
	 * @return
	 */
	public String getSameRecordId(long trainPeriod, long questionNo, String userCd) {
		String returnId = "";
		Map<String, Object> map = new HashMap<String ,Object>();
		try{
			String sql = "select PLAN_TRAIN_GOLF_ID from PLAN_TRAIN_GOLF where TRAIN_PERIOD="+trainPeriod
				+" and QUESTION_NO="+questionNo+" and CREATOR like '"+userCd+"'";
			List returnList = planTrainGolfDao.findBySql(sql, map);
			returnId = (String)returnList.get(0);
		}catch(Exception e){
		}
		return returnId;
	}
}

