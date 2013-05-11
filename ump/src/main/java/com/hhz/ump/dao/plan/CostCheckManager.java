package com.hhz.ump.dao.plan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateParser;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.jbpm.JbpmTaskCandidateManager;
import com.hhz.ump.dao.jbpm.JbpmTaskManager;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.jbpm.JbpmTaskCandidate;
import com.hhz.ump.entity.plan.CostCheck;
import com.hhz.ump.entity.plan.CostCheckMessage;
import com.hhz.ump.entity.plan.CostCheckSchedule;
import com.hhz.ump.entity.plan.CostCtrlBidPurc;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictMapUtil;

@Service
@Transactional
public class CostCheckManager extends BaseService<CostCheck, String> {
	@Autowired
	private CostCheckDao costCheckDao;
	@Autowired
	private CostCheckScheduleManager costCheckScheduleManager;
	@Autowired
	private CostCheckMessageManager costCheckMessageManager;

	@Autowired
	private JbpmTaskManager jbpmTaskManager;
	@Autowired
	private JbpmTaskCandidateManager jbpmTaskCandidateManager;
	
	public static String DataSrcTypeCd_DEFAULT = "0";
	public static String DataSrcTypeCd_ZC = "1";
	
	public static String DeleteFlg_NO = "0";
	public static String DeleteFlg_YES = "1";
	

	//默认数据状态为 新增
	public static String SCHEDULE_STATUS_NEW = "0";
	public static String SCHEDULE_STATUS_PROCESS = "1";
	public static String SCHEDULE_STATUS_COMPLETE = "2";

	public void deleteCostCheck(String id) {
		costCheckDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostCheck, String> getDao() {
		return costCheckDao;
	}
	public void saveCostCheck(CostCheck costCheck) {
		PowerUtils.setEmptyStr2Null(costCheck);
		
		if(StringUtils.isBlank(costCheck.getCostCheckId())){
			costCheck.setSerialType(this.getSerialType(costCheck));
			costCheck.setSerialNo(this.getNextSerialNo(costCheck));
		}
		
		costCheckDao.save(costCheck);
		
		//若删除,则更新招采计划状态.
		if(DeleteFlg_NO.equals(costCheck.getDeleteFlg())){
			
		}
		
		//进度
		for(CostCheckSchedule sche : costCheck.getCostCheckSchedules()){
			sche.setCostCheck(costCheck);
			costCheckScheduleManager.saveCostCheckSchedule(sche);
		} 
		
		//留言
		for(CostCheckMessage msg : costCheck.getCostCheckMessages()){
			msg.setCostCheck(costCheck);
			costCheckMessageManager.saveCostCheckMessage(msg);
		}
		
		//添加代办事项
		//update2JbpmTask(costCheck);
	}
	/**
	 * 插入数据到代办事项
	 * @param costCtrlBidPurc
	 */
	private void update2JbpmTask(CostCheck ccb){
		JbpmTask jbpmTask = jbpmTaskManager.getByEntityId(ccb.getCostCtrlBidPurcId());
		String waitFlg = ccb.getIsWaitOtherFlag();
		if(StringUtils.isNotBlank(waitFlg) && "1".equals(waitFlg)){
			if (jbpmTask == null) {
				jbpmTask = new JbpmTask();
			}
			jbpmTask.setModuleCd("costCtrlPurBid");
			jbpmTask.setModuleName("成本招采管理");
			jbpmTask.setUserCd(ccb.getCreator());
			jbpmTask.setUserName(CodeNameUtil.getUserNameByCd(ccb.getCreator()));
			jbpmTask.setJbpmId(ccb.getCostCtrlBidPurcId());
			jbpmTask.setJbpmCd(ccb.getSerialType()+ccb.getSerialNo());
			jbpmTask.setRemark(ccb.getWaitDesc());
			jbpmTask.setApplyDate(new Date());
			
			String projectCd = ccb.getProjectCd();
			jbpmTask.setDeptCd(ccb.getProjectCd());
			
			if(PlasCache.getDictDataSplit("ORG_NORTH").indexOf(projectCd)>-1){
				jbpmTask.setOtherStatusCd("n");
			}else if(PlasCache.getDictDataSplit("ORG_SOUTH").indexOf(projectCd)>-1){
				jbpmTask.setOtherStatusCd("s");
			}else{
				jbpmTask.setOtherStatusCd("sh");
			}
			
			
			jbpmTaskManager.saveJbpmTask(jbpmTask);
			jbpmTaskCandidateManager.delete(jbpmTask.getJbpmTaskCandidates());

			String[] userCds = ccb.getWaitDeptCd().split(";");
			for (String userCd : userCds) {
				JbpmTaskCandidate jbpmTaskCandidate = new JbpmTaskCandidate();
				jbpmTaskCandidate.setUserCd(userCd);
				jbpmTaskCandidate.setUserName(CodeNameUtil.getUserNameByCd(userCd));
				jbpmTaskCandidate.setJbpmTask(jbpmTask);
				jbpmTaskCandidateManager.saveJbpmTaskCandidate(jbpmTaskCandidate);
			}
			
		}else{
			if (jbpmTask != null) {
				jbpmTaskManager.delete(jbpmTask);
			}
		}
	}

	/**
	 * 招采计划导入标后核对
	 * @param src
	 * @param chiefDate
	 */
	public void importFromPur(CostCtrlBidPurc src, String chiefDate){

		if(StringUtils.isNotBlank(chiefDate)){

			//判断是否已导入,若否,导入!
			if(!validateImported(src.getCostCtrlBidPurcId())){
				CostCheck dest = new CostCheck();
				dest.setPlanContentDesc(src.getPlanContentDesc());//计划事项,可修改
				dest.setCostCtrlBidPurcId(src.getCostCtrlBidPurcId());//关联招采
				
				//数据源类型
				dest.setDataSrcId(src.getCostCtrlBidPurcId());
				dest.setDataSrcTypeCd(DataSrcTypeCd_ZC);
				dest.setDataSrcDesc("招采导入标后核对");
				dest.setDeleteFlg(DeleteFlg_NO);
				dest.setProjectCd(src.getProjectCd());
				dest.setScheduleStatusCd(SCHEDULE_STATUS_NEW);
				dest.setBidDate(DateParser.parse(chiefDate, "yyyy-MM-dd"));//目标时间
				
				//初始化进度数据
				List<CostCheckSchedule> scheArray = new ArrayList<CostCheckSchedule>();
				CostCheckSchedule sche = null;
				Map<String, String> scheMap = DictMapUtil.getMapCostChkSche(false);
				if(scheMap != null){
					int i = 1;
					for(String key : scheMap.keySet()){
						sche = new CostCheckSchedule();
						sche.setSerialNo(i);
						sche.setScheduleTypeCd(key);
						sche.setScheduleTypeName(scheMap.get(key));
						sche.setCostCheck(dest);
						scheArray.add(sche);
						i++;
					}
				}
				dest.setCostCheckSchedules(scheArray);
				
				//设置明细: 审核日期,审核状态
				dest.getCostCheckSchedules().get(CostCheckScheduleManager.IDX_SCHE_CHIEF_4).setTargetDate(DateParser.parse(chiefDate, "yyyy-MM-dd"));
				dest.getCostCheckSchedules().get(CostCheckScheduleManager.IDX_SCHE_CHIEF_4).setCompleteStatusCd(CostCheckScheduleManager.ScheduleStatusCd_NO);
				
				
				//留言
				CostCheckMessage msg = new CostCheckMessage();
				msg.setCostCheck(dest);
				msg.setMsgContentDesc("【招采导入标后核对】");
				dest.getCostCheckMessages().add(msg);
				
				this.saveCostCheck(dest);
			}
		}
	}
	
	/**
	 * 招采ID
	 * @param costCheckBidPurId
	 * @return
	 */
	public boolean validateImported(String costCheckBidPurId){
		String hql = " from CostCheck t where t.costCtrlBidPurcId = ? and t.deleteFlg = ?";
		long count = this.getDao().countHqlResult(hql, costCheckBidPurId, DeleteFlg_NO);
		if(count > 0)
			return true;
		else
			return false;
	}

	/**
	 * 构造序号类型
	 * @param src
	 * @return
	 */
	public String getSerialType(CostCheck src){
		StringBuilder type = new StringBuilder();
		String deptShortName = CodeNameUtil.getDeptShortNameByCd(src.getProjectCd());
		if(StringUtils.isNotBlank(deptShortName)){
			type.append(deptShortName).append("-");
		}
		if("0".equals(src.getDataTypeCd())){
			type.append("ZB-");
		}else if("1".equals(src.getDataTypeCd())){
			type.append("CG-");
		}else{
			type.append("CK-");
		}
		return type.toString();
	}
	/**
	 * 获取下一个序号
	 * @param src
	 * @return
	 */
	public long getNextSerialNo(CostCheck src){
		String type = this.getSerialType(src);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serialType", type);
		List result = findBySql("select max(serial_no) from cost_Check where serial_type=:serialType ",map);
		if(result.size()>0&&result.get(0) != null)
			return ((BigDecimal)result.get(0)).longValue()+1;
		return 1L;
	}
	
	
}

