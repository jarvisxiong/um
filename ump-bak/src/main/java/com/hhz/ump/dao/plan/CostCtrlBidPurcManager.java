package com.hhz.ump.dao.plan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateParser;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.jbpm.JbpmTaskCandidateManager;
import com.hhz.ump.dao.jbpm.JbpmTaskManager;
import com.hhz.ump.dao.oa.OaEmailBodyManager;
import com.hhz.ump.entity.jbpm.JbpmTask;
import com.hhz.ump.entity.jbpm.JbpmTaskCandidate;
import com.hhz.ump.entity.plan.CostCtrlBidPurc;
import com.hhz.ump.entity.plan.CostCtrlMessage;
import com.hhz.ump.entity.plan.CostCtrlSchedule;
import com.hhz.ump.entity.plan.CostCtrlSuppliers;
import com.hhz.ump.entity.plan.CostExecPlanDetail;
import com.hhz.ump.entity.plan.ExecPlanDetail;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Constants;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.res.bean.CostZcMonthPlanBisgroup;
import com.hhz.ump.web.res.bean.CostZcMonthPlanBisgroupProperty;
import com.hhz.ump.web.res.bean.CostZcMonthPlanFore;
import com.hhz.ump.web.res.bean.CostZcMonthPlanForeProp;
import com.hhz.ump.web.res.bean.CostZcMonthPlanGroup;
import com.hhz.ump.web.res.bean.CostZcMonthPlanGroupProperty;
import com.hhz.ump.web.res.bean.CostZlMonthPlanBisgroup;
import com.hhz.ump.web.res.bean.CostZlMonthPlanBisgroupProperty;
import com.hhz.ump.web.vo.CostStatistics;
import com.hhz.ump.web.vo.CostStatisticsArea;
import com.hhz.uums.entity.ws.WsPlasRole;
import com.hhz.uums.entity.ws.WsPlasUser;
import com.hhz.uums.service.WSPlasService;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class CostCtrlBidPurcManager extends BaseService<CostCtrlBidPurc, String> {
	@Autowired
	private CostCtrlBidPurcDao costCtrlBidPurcDao;
	@Autowired
	private CostExecPlanDetailManager costExecPlanDetailManager;
	@Autowired
	private ExecPlanDetailManager execPlanDetailManager;
	@Autowired
	private CostCtrlMessageManager costCtrlMessageManager;
	@Autowired
	private CostCtrlScheduleManager costCtrlScheduleManager;
	@Autowired
	private CostCtrlSuppliersManager costCtrlSuppliersManager;
	
	@Autowired
	private JbpmTaskManager jbpmTaskManager;

	@Autowired
	private JbpmTaskCandidateManager jbpmTaskCandidateManager;
	
	public void saveCostCtrlBidPurc(CostCtrlBidPurc costCtrlBidPurc) {
		PowerUtils.setEmptyStr2Null(costCtrlBidPurc);
		
		//若新增
		if(StringUtils.isBlank(costCtrlBidPurc.getCostCtrlBidPurcId())){
			String dataTypeCd = costCtrlBidPurc.getDataTypeCd();
			String prjCd = costCtrlBidPurc.getProjectCd();
			
			costCtrlBidPurc.setSerialType(this.getSerialType(dataTypeCd, prjCd));
			
			Long serialNo = costCtrlBidPurc.getSerialNo();
			if(serialNo == null){
				costCtrlBidPurc.setSerialNo(this.getNextSerialNo(dataTypeCd, prjCd));
			}
		}
		costCtrlBidPurcDao.save(costCtrlBidPurc);
		//若删除一条执行计划、半年计划，则把执行计划、半年计划状态设置完
		if(costCtrlBidPurc.getDeleteFlg()!=null&&"1".equals(costCtrlBidPurc.getDeleteFlg())){
			String typeCd =costCtrlBidPurc.getPlanTypeCd();
			if("2".equals(typeCd)){
				//半年计划
				String yearId =costCtrlBidPurc.getDataSrcId();
				CostExecPlanDetail costDetail =costExecPlanDetailManager.getEntity(yearId);
				if(costDetail!=null&&costDetail.getCostExecPlanDetailId()!=null){
					costDetail.setCostCtrlBidPurcId("");
					costExecPlanDetailManager.saveCostExecPlanDetail(costDetail);
				}
			}else if("3".equals(typeCd)){
				//执行计划
				String execId =costCtrlBidPurc.getDataSrcId();
				ExecPlanDetail planDetail =execPlanDetailManager.getEntity(execId);
				if(planDetail!=null&&planDetail.getExecPlanDetailId()!=null){
					//planDetail.setCostCtrlBidPurcId("");
					execPlanDetailManager.saveExecPlanDetail(planDetail);
				}
				
			}
		}
		
		for(CostCtrlMessage msg : costCtrlBidPurc.getCostCtrlMessages()){
			msg.setCostCtrlBidPurc(costCtrlBidPurc);
			costCtrlMessageManager.saveCostCtrlMessage(msg);
		}
		for(CostCtrlSchedule sche : costCtrlBidPurc.getCostCtrlSchedules()){
			sche.setCostCtrlBidPurc(costCtrlBidPurc);
			costCtrlScheduleManager.saveCostCtrlSchedule(sche);
		}
		for(CostCtrlSuppliers suppliers:costCtrlBidPurc.getCostCtrlSupplierses()){
			suppliers.setCostCtrlBidPurc(costCtrlBidPurc);
			costCtrlSuppliersManager.saveCostCtrlSuppliers(suppliers);
		}
		
		//自动更新待办事项的状态
		update2JbpmTask(costCtrlBidPurc);
	}
	/**
	 * 新增成本计划数据，并回写detailID到执行计划或半年计划
	 * @param costCtrlBidPurc
	 */
	public void saveCostCtrlBidPurcByPlan(CostCtrlBidPurc costCtrlBidPurc){
		this.saveCostCtrlBidPurc(costCtrlBidPurc);
		//回写到执行计划或半年计划
		if(costCtrlBidPurc.getCostCtrlBidPurcId()!=null){
			if(costCtrlBidPurc.getDataSrcTypeCd().equals(Constants.SRC_TYPE_YEAR)){
				String yearId =costCtrlBidPurc.getDataSrcId();
				CostExecPlanDetail costDetail =costExecPlanDetailManager.getEntity(yearId);
				if(costDetail!=null&&costDetail.getCostExecPlanDetailId()!=null){
					costDetail.setCostCtrlBidPurcId(costCtrlBidPurc.getDataSrcId());
					costExecPlanDetailManager.saveCostExecPlanDetail(costDetail);
				}
			}else if(costCtrlBidPurc.getDataSrcTypeCd().equals(Constants.SRC_TYPE_EXEC)){
				String execId =costCtrlBidPurc.getDataSrcId();
				ExecPlanDetail planDetail =execPlanDetailManager.getEntity(execId);
				if(planDetail!=null&&planDetail.getExecPlanDetailId()!=null){
					//planDetail.setCostCtrlBidPurcId(costCtrlBidPurc.getDataSrcId());
					execPlanDetailManager.saveExecPlanDetail(planDetail);
				}
			}
				
		}
	}
	public void batchCostSaveByPlan(List<CostCtrlBidPurc> costCtrlBidList){
		for(CostCtrlBidPurc purc:costCtrlBidList){
			saveCostCtrlBidPurcByPlan(purc);
		}
	}

	public void deleteCostCtrlBidPurc(String id) {
		costCtrlBidPurcDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostCtrlBidPurc, String> getDao() {
		return costCtrlBidPurcDao;
	}
	
	/**
	 * 获取下一个序号
	 * @return
	 */
	public long getNextSerialNo(String dataTypeCd, String prjCd){
		String type = this.getSerialType(dataTypeCd, prjCd);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serialType", type);
		List result = findBySql("select max(serial_no) from cost_ctrl_bid_purc where serial_type=:serialType ",map);
		if(result.size()>0&&result.get(0) != null)
			return ((BigDecimal)result.get(0)).longValue()+1;
		return 1L;
	}
	
	/**
	 * 构造序号类型
	 * @param costCtrlBidPurc
	 * @return
	 */
	public String getSerialType(String dataTypeCd, String prjCd){
		StringBuilder type = new StringBuilder();
		String deptShortName = CodeNameUtil.getDeptShortNameByCd(prjCd);
		if(StringUtils.isNotBlank(deptShortName)){
			type.append(deptShortName).append("-");
		}
		if("0".equals(dataTypeCd)){
			type.append("ZB-");
		}else if("1".equals(dataTypeCd) ){
			type.append("CG-");
		}else{
			type.append("ZL-");
		}
		return type.toString();
	}
	
	/**
	 * 插入数据到代办事项
	 * @param costCtrlBidPurc
	 */
	private void update2JbpmTask(CostCtrlBidPurc ccb){
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
			}else if(PlasCache.getDictDataSplit("ORG_BIS").indexOf(projectCd)>-1){
				jbpmTask.setOtherStatusCd("bis");
			}else{
				jbpmTask.setOtherStatusCd("hotel");
			}
			
			jbpmTaskManager.saveJbpmTask(jbpmTask);
			jbpmTaskCandidateManager.delete(jbpmTask.getJbpmTaskCandidates());

			if(StringUtils.isNotBlank(ccb.getWaitDeptCd())){
				String[] userCds = ccb.getWaitDeptCd().split(";");
				for (String userCd : userCds) {
					JbpmTaskCandidate jbpmTaskCandidate = new JbpmTaskCandidate();
					jbpmTaskCandidate.setUserCd(userCd);
					jbpmTaskCandidate.setUserName(CodeNameUtil.getUserNameByCd(userCd));
					jbpmTaskCandidate.setJbpmTask(jbpmTask);
					jbpmTaskCandidateManager.saveJbpmTaskCandidate(jbpmTaskCandidate);
				}
			}
		}else{
			if (jbpmTask != null) {
				jbpmTaskManager.delete(jbpmTask);
			}
		}
	}
	
	/**
	 * 按照年月统计招采完成情况
	 * @param year
	 * @param month
	 * @return
	 */
	public List<CostStatistics> getCostStatistics(String year,String month){
		
		String distinctUserCds = getDistinctOwnerCds(year,month);
		
		if(StringUtils.isBlank(distinctUserCds))
			return new ArrayList<CostStatistics>();
		
		//统计数据
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, year);
		map.put(2, month);
		map.put(3, distinctUserCds);
		List<CostStatistics> list = (List<CostStatistics>)this.executeFunction("{ call p_zc_statistics(?,?,?,?)}", map, CostStatistics.class);
		return list;
	}
	/**
	 * 区域
	 * @param year
	 * @param month
	 * @return
	 */
	public List<CostStatisticsArea> getCostStatisticsArea(String year,String month){
		
		//招标负责人
		String distinctUserCds = getDistinctOwnerCds(year,month);
		
		//综合部
		String distinctUserCdsZhb = getDistinctOwnerCdsZhb(year,month);
		if(StringUtils.isBlank(distinctUserCds) && StringUtils.isBlank(distinctUserCdsZhb))
			return new ArrayList<CostStatisticsArea>();
		
		//统计数据
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, year);//年份
		map.put(2, month);//月份
		map.put(3, distinctUserCds);//招标
		map.put(4, distinctUserCdsZhb);//采购
		List<CostStatisticsArea> list = (List<CostStatisticsArea>)this.executeFunction("{ call p_zc_statistics_area(?,?,?,?,?)}", map, CostStatisticsArea.class);

		//返回列表
		List<CostStatisticsArea> rtnList = new ArrayList<CostStatisticsArea>();
		if(list == null || list.size() == 0)
			return rtnList;
		
		// 3-合计
		CostStatisticsArea allAreaRow = new CostStatisticsArea();
		allAreaRow.setRowTypeCd("3");
		
		// 2-小计
		CostStatisticsArea tmpAreaRow = new CostStatisticsArea();
		tmpAreaRow.setRowTypeCd("2");
		
		
		CostStatisticsArea tmpPreRow = null;
		if(list != null){
			for (CostStatisticsArea tmp : list) {
				//发现新区域
				if(tmpPreRow != null){
					if(tmpPreRow!= null && tmp!= null && StringUtils.isNotBlank(tmpPreRow.getQy())&&(!tmpPreRow.getQy().equals(tmp.getQy()))){
						rtnList.add(tmpAreaRow);
						tmpAreaRow = new CostStatisticsArea();
						tmpAreaRow.setRowTypeCd("2");//2-小计
					}
				}
				rtnList.add(tmp);
				tmpAreaRow.appendValue(tmp);
				allAreaRow.appendValue(tmp);
				tmpPreRow = tmp;
			}
			rtnList.add(tmpAreaRow);
		}
		
		rtnList.add(allAreaRow);
		return rtnList;
	}

	/**
	 * 总部(招标、采购)
	 * @param year
	 * @param month
	 * @return
	 */
	public List<CostStatisticsArea> getCostStatisticsZc(String year,String month){
		
		//招标负责人
		String distinctUserCds = getDistinctOwnerCds(year,month);
		
		//综合部
		String distinctUserCdsZhb = getDistinctOwnerCdsZhb(year,month);
		if(StringUtils.isBlank(distinctUserCds) && StringUtils.isBlank(distinctUserCdsZhb))
			return new ArrayList<CostStatisticsArea>();
		
		//统计数据
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, year);//年份
		map.put(2, month);//月份
		map.put(3, distinctUserCds);//招标
		map.put(4, distinctUserCdsZhb);//采购
		List<CostStatisticsArea> list = (List<CostStatisticsArea>)this.executeFunction("{ call p_zc_statistics_zc(?,?,?,?,?)}", map, CostStatisticsArea.class);

		//返回列表
		List<CostStatisticsArea> rtnList = new ArrayList<CostStatisticsArea>();
		if(list == null || list.size() == 0)
			return rtnList;
		
		// 3-合计
		CostStatisticsArea allAreaRow = new CostStatisticsArea();
		allAreaRow.setRowTypeCd("3");
		
		// 2-小计
		CostStatisticsArea tmpAreaRow = new CostStatisticsArea();
		tmpAreaRow.setRowTypeCd("2");
		
		
		CostStatisticsArea tmpPreRow = null;
		if(list != null){
			for (CostStatisticsArea tmp : list) {
				//发现新区域
				if(tmpPreRow != null){
					if(tmpPreRow!= null && tmp!= null && StringUtils.isNotBlank(tmpPreRow.getQy())&&(!tmpPreRow.getQy().equals(tmp.getQy()))){
						rtnList.add(tmpAreaRow);
						tmpAreaRow = new CostStatisticsArea();
						tmpAreaRow.setRowTypeCd("2");//2-小计
					}
				}
				rtnList.add(tmp);
				tmpAreaRow.appendValue(tmp);
				allAreaRow.appendValue(tmp);
				tmpPreRow = tmp;
			}
			rtnList.add(tmpAreaRow);
		}
		
		rtnList.add(allAreaRow);
		return rtnList;
	}
	/**
	 * 搜索月度所有的招采负责人,以逗号分隔
	 * eg. "shixy,huangbj,huangjian"
	 * @param year
	 * @param month
	 * @return
	 */
	//招采: 搜索所有的负责人 distinct
	public String getDistinctOwnerCds(String year,String month){
		
		StringBuilder sqlSb = new StringBuilder();
		sqlSb.append("select distinct owner_cds from cost_ctrl_bid_purc ");
		sqlSb.append(" where to_char(bid_date, 'yyyyMM') = :yearMonth ");
		sqlSb.append("   and delete_flg = :deleteFlg ");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("yearMonth", year+month);
		param.put("deleteFlg", "0");
		
	    Query qry = this.getDao().createSQLQuery(sqlSb.toString(), param);
	    List<String> allUserCds = qry.list();
	    List<String> disUserCds = new ArrayList<String>();
	    StringBuilder userCdSb = new StringBuilder();
	    for(String userCds : allUserCds){
	    	if(StringUtils.isNotBlank(userCds)){
	    		String[] cds = userCds.split(";");
	    		for(String cd : cds){
	    			if(!disUserCds.contains(cd)){
	    				disUserCds.add(cd);
	    				userCdSb.append(cd).append(",");
	    			}
	    		}
	    	}
	    }
	    if(disUserCds.size()>0)
			return userCdSb.substring(0, userCdSb.length()-1);
		else
			return "";
	}
	//综合部: 搜索所有的负责人 distinct
	public String getDistinctOwnerCdsZhb(String year,String month){
		StringBuilder sqlSb = new StringBuilder("select distinct principal from plan_work_center ");
		sqlSb.append(" where to_char(target_date, 'yyyyMM') = :yearMonth ");
		sqlSb.append(" and org_cd in('17','141','142','144') ");
		sqlSb.append(" and status_cd in('1','2','3') ");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("yearMonth", year+month);
		
	    Query qry = this.getDao().createSQLQuery(sqlSb.toString(), param);
	    List<String> allUserCds = qry.list();
	    List<String> disUserCds = new ArrayList<String>();
	    StringBuilder userCdSb = new StringBuilder();
	    for(String userCds : allUserCds){
	    	if(StringUtils.isNotBlank(userCds)){
	    		String[] cds = userCds.split(";");
	    		for(String cd : cds){
	    			if(!disUserCds.contains(cd)){
	    				disUserCds.add(cd);
	    				userCdSb.append(cd).append(",");
	    			}
	    		}
	    	}
	    }
	    if(disUserCds.size()>0)
			return userCdSb.substring(0, userCdSb.length()-1);
		else
			return "";
	}
	
	public static String DataTypeCd_ZB = "0";
	public static String DataTypeCd_CG = "1";
	public static String DataTypeCd_ZL = "2";
	
	public static String PlanTypeCd_IN = "0";
	public static String PlanTypeCd_OUT = "1";
	
	public static String BidStatusCd_NEW = "0";
	public static String BidStatusCd_PROCESS = "1";
	public static String BidStatusCd_COMPLETE = "2";
	
	public static String ImportantTypeCd_LOW = "0";
	public static String ImportantTypeCd_NORMAL = "1";
	public static String ImportantTypeCd_HIGH = "2";
	
	public static String DataSrcTypeCd_DEFAULT = "0";
	public static String DataSrcTypeCd_PLAN = "1";
	public static String DataSrcTypeCd_RES = "5";
	
	public static String DataSrcStatusCd_DISABLE = "0";
	public static String DataSrcStatusCd_ENABLE = "1";

	public static String ContractStatusCd_NO = "0";
	public static String ContractStatusCd_YES = "1";
	
	public static String BidCheckStatusCd_NO = "0";
	public static String BidCheckStatusCd_YES = "1";
	
	public static String ScheduleStatusCd_NEW = "0";
	
	public static String SuppCompleteFlag_NO = "0";

	public static String DeleteFlg_NO = "0";
	
	
	
	//网批导入招采计划
	//	完成上述月招标采购计划（总部权限）网批审核后，
	// 	系统自动将网批内容导入成本工作任务招采计划
	//  招采计划(计划编号、类型、所属项目、重要程度、事项、出图时间、定标时间、状态、创建时间、更新时间)
	//  =网批表单(系统生成、表单类别、表单项目、'普通、表单工程名称、表单出图时间、表单定标时间、'新增'、系统时间、系统时间)
	
	public void importZcPlan(CostZcMonthPlanGroup group){
		
		if(group == null || group.getPlanProperties()== null || group.getPlanProperties().size() == 0)
			return;
		
		CostCtrlBidPurc zc = null;
		String srcTitle = null;
		List<CostCtrlSchedule> scheArray = null;
		Map<String, String> scheMap = null;
		CostCtrlSchedule sche = null;

		ResApproveInfo res = group.getResApproveInfo();
		//序号
		Map<String, Long> lastMap = new HashMap<String, Long>();
		for (CostZcMonthPlanGroupProperty tmp : group.getPlanProperties()) {
			if(tmp != null){ 
				zc = new CostCtrlBidPurc();
				
				//系统生成
				//zc.setCostCtrlBidPurcId(null);
				
				//*招标采购类型（0：招标；1：采购）
				if(StringUtils.isNotBlank(group.getBidType()) && StringUtils.equals("true", group.getBidType())){
					zc.setDataTypeCd(DataTypeCd_ZB);
				} 
				if(StringUtils.isNotBlank(group.getPurcType()) && StringUtils.equals("true", group.getPurcType())){
					zc.setDataTypeCd(DataTypeCd_CG);
				} 
				if(StringUtils.isNotBlank(group.getMarketType()) && StringUtils.equals("true", group.getMarketType())){
					zc.setDataTypeCd(DataTypeCd_CG);
				}
				
				//*所属项目
				zc.setProjectCd(tmp.getProjectCd());
				
				//*类型,这里不用设置,实体的保存方法有作判断
				//zc.setSerialType(getSerialType(zc));
				//zc.setSerialNo(getNextSerialNo(zc));
				
				//*紧急度（0-低|1-普通|2-重要）
				zc.setImportantTypeCd(ImportantTypeCd_NORMAL);
				//计划类型（0-计划内、1-计划外、2-半年计划、3-执行计划），在新增的时候，只有可能是计划内和计划外两种
				zc.setPlanTypeCd(PlanTypeCd_IN);
				//计划事项（工程名称+招标范围/采购内容）
				zc.setPlanContentDesc(tmp.getProjectDesc()+ (StringUtils.isNotBlank(tmp.getInvPurDesc())?("/"+tmp.getInvPurDesc()):""));
				
				zc.setOwnerCds(null);
				zc.setOwnerNames(null);
				zc.setIsCompletedFlag(null);
				zc.setUpdateContentDesc(null);
				//供应商为 未确认
				zc.setIsSuppCompleteFlag(SuppCompleteFlag_NO);
				//设置数据状态为 新增
				zc.setScheduleStatusCd(ScheduleStatusCd_NEW);
				zc.setIsWaitProjectFlag(null);
				zc.setWaitProjectDesc(null);
				zc.setIsWaitTechFlag(null);
				zc.setWaitTechDesc(null);
				zc.setIsWaitOtherFlag(null);
				zc.setWaitDeptCd(null);
				zc.setWaitDeptName(null);
				zc.setWaitDesc(null);

				//*定标时间
				if(StringUtils.isNotBlank(tmp.getBidCompleteDate())){
					zc.setBidDate(DateParser.parse(tmp.getBidCompleteDate(),"yyyy-MM-dd"));
				}
				
				//合同状态
				zc.setContractStatusCd(ContractStatusCd_NO);
//				zc.setBidCheckStatusCd(BidCheckStatusCd_NO);
				
				//*招标状态（0-新增、1-进行中、2-已完成）
				zc.setBidStatusCd(BidStatusCd_NEW);
				
				//数据来源类型(0-普通 1-半年计划 5-网批导入)
				zc.setDataSrcTypeCd(DataSrcTypeCd_RES);
				zc.setDataSrcId(group.getResApproveInfo().getResApproveInfoId());
				//是否有效：1-是 0-否; 若数据源已删除,则为0
				zc.setDataSrcStatusCd(DataSrcStatusCd_ENABLE);
				//数据来源描述
				srcTitle = CodeNameUtil.getResAuthTypeNameByCd(group.getResApproveInfo().getAuthTypeCd());
				zc.setDataSrcDesc(StringUtils.isNotBlank(srcTitle)?srcTitle:(group.getPlanYear()+"年" + group.getPlanMonth() + "月招标采购计划(总部)"));
				zc.setDeleteFlg(DeleteFlg_NO);

				//初始化进度数据
				scheArray = new ArrayList<CostCtrlSchedule>();
				scheMap = DictMapUtil.getMapCostBidSche();
				if(scheMap != null){
					scheMap.remove("");
					int i = 1;
					for(String key : scheMap.keySet()){
						sche = new CostCtrlSchedule();
						
						//*出图时间(i=1)
						if(i==1 && StringUtils.isNotBlank(tmp.getPictureDate())){
							sche.setTargetDate(DateParser.parse(tmp.getPictureDate(),"yyyy-MM-dd"));
						}

						//*定标时间(i=6)
						if(i==6 && StringUtils.isNotBlank(tmp.getBidCompleteDate())){
							sche.setTargetDate(DateParser.parse(tmp.getBidCompleteDate(),"yyyy-MM-dd"));
						}
						
						sche.setSerialNo(i);
						sche.setScheduleTypeCd(key);
						sche.setScheduleTypeName(scheMap.get(key));
						sche.setCostCtrlBidPurc(zc);
						scheArray.add(sche);
						i++;
					}
				}
				//设置节点
				zc.setCostCtrlSchedules(scheArray);
				
				//设置留言
				CostCtrlMessage msg = new CostCtrlMessage();
				msg.setCostCtrlBidPurc(zc);
				msg.setMsgContentDesc("【网批导入,查询号:"+ group.getResApproveInfo().getDisplayNo() +"】");
				zc.getCostCtrlMessages().add(msg);
				
				

				//add by huangbijin 2012-06-28  同项目的计划编号一致
				String tmpPrjCd = zc.getProjectCd();
				String tmpDataTypeCd = zc.getDataTypeCd();
				String key = (StringUtils.isBlank(tmpDataTypeCd)?"":tmpDataTypeCd) + "_" + tmpPrjCd;
				
				//序号赋值
				//若本批次不存在，则查询库的最大值；
				Long preSerialNo = lastMap.get(key);
				if(preSerialNo == null){
					//已经+1
					long dbSerialNo = this.getNextSerialNo(tmpDataTypeCd, tmpPrjCd);
					zc.setSerialNo(dbSerialNo);
				}else{
					zc.setSerialNo(preSerialNo+1);
				}
				this.saveCostCtrlBidPurc(zc);
				lastMap.put(key, zc.getSerialNo());

				
				
				
				//发送内部邮件
				String content = new StringBuffer()
				.append("网批导入项目招采任务,请悉知！<br/>")
				.append("网批类型: 成本月招采计划(集团)").append("<br/>")
				.append("网批编号: ").append(res.getApproveCd()).append(res.getSerialNo()).append("<br/>")
				.append("项目名称: ").append(tmp.getProjectName()).append("<br/>")
				.append("招采序号: ").append(zc.getSerialType()).append(zc.getSerialNo()).append("<br/>")
				.append("事          项:").append(tmp.getProjectDesc()).append("/").append(tmp.getInvPurDesc()).append("<br/>")
				.append("<div style='color:red'>烦请确认相关负责人员.</div>")
				.toString();
				
				sendEmail(tmp.getProjectCd(), "【项目招采任务(集团) - 新增通知】网批编号:" + res.getApproveCd() + res.getSerialNo(), content);
			}
		}
	} 
	/**
	 * 商业招采计划
	 * @param group
	 */
    public void importZcPlanByBis(CostZcMonthPlanBisgroup group){
		
		if(group == null || group.getPlanProperties()== null || group.getPlanProperties().size() == 0)
			return;
		
		CostCtrlBidPurc zc = null;
		String srcTitle = null;
		List<CostCtrlSchedule> scheArray = null;
		Map<String, String> scheMap = null;
		CostCtrlSchedule sche = null;

		ResApproveInfo res = group.getResApproveInfo();
		//序号
		Map<String, Long> lastMap = new HashMap<String, Long>();
		for (CostZcMonthPlanBisgroupProperty tmp : group.getPlanProperties()) {
			if(tmp != null){ 
				zc = new CostCtrlBidPurc();
				
				//系统生成
				//zc.setCostCtrlBidPurcId(null);
				
				//*招标采购类型（0：招标；1：采购）
				if(StringUtils.isNotBlank(tmp.getTypeCd1()) && StringUtils.equals("true", tmp.getTypeCd1())){
					zc.setDataTypeCd(DataTypeCd_ZB);
				} 
				if(StringUtils.isNotBlank(tmp.getTypeCd2()) && StringUtils.equals("true", tmp.getTypeCd2())){
					zc.setDataTypeCd(DataTypeCd_CG);
				} 
				
				//*所属项目
				zc.setProjectCd(tmp.getProjectCd());
				
				//*类型,这里不用设置,实体的保存方法有作判断
				//zc.setSerialType(getSerialType(zc));
				//zc.setSerialNo(getNextSerialNo(zc));
				
				//*紧急度（0-低|1-普通|2-重要）
				zc.setImportantTypeCd(ImportantTypeCd_NORMAL);
				//计划类型（0-计划内、1-计划外、2-半年计划、3-执行计划），在新增的时候，只有可能是计划内和计划外两种
				zc.setPlanTypeCd(PlanTypeCd_IN);
				//计划事项（工程名称+招标范围/采购内容）
				zc.setPlanContentDesc(tmp.getProjectDesc()+ (StringUtils.isNotBlank(tmp.getInvPurDesc())?("/"+tmp.getInvPurDesc()):""));
				
				zc.setOwnerCds(null);
				zc.setOwnerNames(null);
				zc.setIsCompletedFlag(null);
				zc.setUpdateContentDesc(null);
				//供应商为 未确认
				zc.setIsSuppCompleteFlag(SuppCompleteFlag_NO);
				//设置数据状态为 新增
				zc.setScheduleStatusCd(ScheduleStatusCd_NEW);
				zc.setIsWaitProjectFlag(null);
				zc.setWaitProjectDesc(null);
				zc.setIsWaitTechFlag(null);
				zc.setWaitTechDesc(null);
				zc.setIsWaitOtherFlag(null);
				zc.setWaitDeptCd(null);
				zc.setWaitDeptName(null);
				zc.setWaitDesc(null);

				//*定标时间
				if(StringUtils.isNotBlank(tmp.getBidCompleteDate())){
					zc.setBidDate(DateParser.parse(tmp.getBidCompleteDate(),"yyyy-MM-dd"));
				}
				
				//合同状态
				zc.setContractStatusCd(ContractStatusCd_NO);
//				zc.setBidCheckStatusCd(BidCheckStatusCd_NO);
				
				//*招标状态（0-新增、1-进行中、2-已完成）
				zc.setBidStatusCd(BidStatusCd_NEW);
				
				//数据来源类型(0-普通 1-半年计划 5-网批导入)
				zc.setDataSrcTypeCd(DataSrcTypeCd_RES);
				zc.setDataSrcId(group.getResApproveInfo().getResApproveInfoId());
				//是否有效：1-是 0-否; 若数据源已删除,则为0
				zc.setDataSrcStatusCd(DataSrcStatusCd_ENABLE);
				//数据来源描述
				srcTitle = CodeNameUtil.getResAuthTypeNameByCd(group.getResApproveInfo().getAuthTypeCd());
				zc.setDataSrcDesc(StringUtils.isNotBlank(srcTitle)?srcTitle:(group.getPlanYear()+"年" + group.getPlanMonth() + "月招标采购计划(总部)"));
				zc.setDeleteFlg(DeleteFlg_NO);

				//初始化进度数据
				scheArray = new ArrayList<CostCtrlSchedule>();
				scheMap = DictMapUtil.getMapCostBisSche();
				if(scheMap != null){
					scheMap.remove("");
					int i = 1;
					for(String key : scheMap.keySet()){
						sche = new CostCtrlSchedule();
						//*立项审批表(i=1)
					    if(i==1 && StringUtils.isNotBlank(tmp.getLxfaProvideDate())){
							sche.setTargetDate(DateParser.parse(tmp.getLxfaProvideDate(),"yyyy-MM-dd"));
						}
					    //预算审批表
					    if(i==2 && StringUtils.isNotBlank(tmp.getYsfyProvideDate())){
					    	sche.setTargetDate(DateParser.parse(tmp.getYsfyProvideDate(),"yyyy-MM-dd"));
					    }
					    //招标方案
					    if(i==3 && StringUtils.isNotBlank(tmp.getZbfaProvideDate()) ){
					    	sche.setTargetDate(DateParser.parse(tmp.getZbfaProvideDate(),"yyyy-MM-dd"));
					    }
						//*定标时间(i=7)
						if(i==7 && StringUtils.isNotBlank(tmp.getBidCompleteDate())){
							sche.setTargetDate(DateParser.parse(tmp.getBidCompleteDate(),"yyyy-MM-dd"));
						}
						
						sche.setSerialNo(i);
						sche.setScheduleTypeCd(key);
						sche.setScheduleTypeName(scheMap.get(key));
						sche.setCostCtrlBidPurc(zc);
						scheArray.add(sche);
						i++;
					}
				}
				//设置节点
				zc.setCostCtrlSchedules(scheArray);
				
				//设置留言
				CostCtrlMessage msg = new CostCtrlMessage();
				msg.setCostCtrlBidPurc(zc);
				msg.setMsgContentDesc("【网批导入,查询号:"+group.getResApproveInfo().getDisplayNo()+"】");
				zc.getCostCtrlMessages().add(msg);
				





				//add by huangbijin 2012-06-28  同项目的计划编号一致
				String tmpPrjCd = zc.getProjectCd();
				String tmpDataTypeCd = zc.getDataTypeCd();
				String key = (StringUtils.isBlank(tmpDataTypeCd)?"":tmpDataTypeCd) + "_" + tmpPrjCd;
				
				//序号赋值
				//若本批次不存在，则查询库的最大值；
				Long preSerialNo = lastMap.get(key);
				if(preSerialNo == null){
					//已经+1
					long dbSerialNo = this.getNextSerialNo(tmpDataTypeCd, tmpPrjCd);
					zc.setSerialNo(dbSerialNo);
				}else{
					zc.setSerialNo(preSerialNo+1);
				}
				this.saveCostCtrlBidPurc(zc);
				lastMap.put(key, zc.getSerialNo());

				
				
				
				
				
				//发送内部邮件
				String content = new StringBuffer()
				.append("网批导入项目招采任务,请悉知！<br/>")
				.append("网批类型: 成本月招采计划(集团)").append("<br/>")
				.append("网批编号: ").append(res.getApproveCd()).append(res.getSerialNo()).append("<br/>")
				.append("项目名称: ").append(tmp.getProjectName()).append("<br/>")
				.append("招采序号: ").append(zc.getSerialType()).append(zc.getSerialNo()).append("<br/>")
				.append("事          项:").append(tmp.getProjectDesc()).append("/").append(tmp.getInvPurDesc()).append("<br/>")
				.append("<div style='color:red'>烦请确认相关负责人员.</div>")
				.toString();
				
				sendEmail(tmp.getProjectCd(), "【项目招采任务(集团) - 新增通知】网批编号:" + res.getApproveCd() + res.getSerialNo(), content);
			}
		}
	} 
	
	/**
	 * 战略计划采购单
	 * @param fore
	 */
	public void importForePlan(CostZcMonthPlanFore fore){
		if(fore == null || fore.getPlanProperties()== null || fore.getPlanProperties().size() == 0)
			return;
		
		CostCtrlBidPurc zc = null;
		String srcTitle = null;
		List<CostCtrlSchedule> scheArray = null;
		Map<String, String> scheMap = null;
		CostCtrlSchedule sche = null;
		//序号
		Map<String, Long> lastMap = new HashMap<String, Long>();
		ResApproveInfo res = fore.getResApproveInfo();
		for (CostZcMonthPlanForeProp tmp : fore.getPlanProperties()) {
			if(tmp != null){ 
				zc = new CostCtrlBidPurc();
				
				//系统生成
				//zc.setCostCtrlBidPurcId(null);
				
				//战略
				zc.setDataTypeCd(DataTypeCd_ZL);
				//*所属项目
				zc.setProjectCd(tmp.getProjectCd());
				
				//*类型,这里不用设置,实体的保存方法有作判断
				//zc.setSerialType(getSerialType(zc));
				//zc.setSerialNo(getNextSerialNo(zc));
				
				//*紧急度（0-低|1-普通|2-重要）
				zc.setImportantTypeCd(ImportantTypeCd_NORMAL);
				//计划类型（0-计划内、1-计划外、2-半年计划、3-执行计划），在新增的时候，只有可能是计划内和计划外两种
				zc.setPlanTypeCd(PlanTypeCd_IN);
				//计划事项（工程名称+招标范围/采购内容）
				zc.setPlanContentDesc(tmp.getProjectDesc()+ (StringUtils.isNotBlank(tmp.getInvPurDesc())?("/"+tmp.getInvPurDesc()):""));
				
				zc.setOwnerCds(null);
				zc.setOwnerNames(null);
				zc.setIsCompletedFlag(null);
				zc.setUpdateContentDesc(null);
				//供应商为 未确认
				zc.setIsSuppCompleteFlag(SuppCompleteFlag_NO);
				//设置数据状态为 新增
				zc.setScheduleStatusCd(ScheduleStatusCd_NEW);
				zc.setIsWaitProjectFlag(null);
				zc.setWaitProjectDesc(null);
				zc.setIsWaitTechFlag(null);
				zc.setWaitTechDesc(null);
				zc.setIsWaitOtherFlag(null);
				zc.setWaitDeptCd(null);
				zc.setWaitDeptName(null);
				zc.setWaitDesc(null);

				//*定标时间
				if(StringUtils.isNotBlank(tmp.getBidCompleteDate())){
					zc.setBidDate(DateParser.parse(tmp.getBidCompleteDate(),"yyyy-MM-dd"));
				}
				
				//合同状态
				zc.setContractStatusCd(ContractStatusCd_NO);
//				zc.setBidCheckStatusCd(BidCheckStatusCd_NO);
				
				//*招标状态（0-新增、1-进行中、2-已完成）
				zc.setBidStatusCd(BidStatusCd_NEW);
				
				//数据来源类型(0-普通 1-半年计划 5-网批导入)
				zc.setDataSrcTypeCd(DataSrcTypeCd_RES);
				zc.setDataSrcId(fore.getResApproveInfo().getResApproveInfoId());
				//是否有效：1-是 0-否; 若数据源已删除,则为0
				zc.setDataSrcStatusCd(DataSrcStatusCd_ENABLE);
				//数据来源描述
				srcTitle = CodeNameUtil.getResAuthTypeNameByCd(fore.getResApproveInfo().getAuthTypeCd());
				zc.setDataSrcDesc(StringUtils.isNotBlank(srcTitle)?srcTitle:(fore.getPlanYear()+"年" + fore.getPlanMonth() + "月招标采购计划(总部)"));
				zc.setDeleteFlg(DeleteFlg_NO);

				//初始化进度数据
				scheArray = new ArrayList<CostCtrlSchedule>();
				scheMap = DictMapUtil.getMapCostStratage();
				if(scheMap != null){
					scheMap.remove("");
					int i = 1;
					for(String key : scheMap.keySet()){
						sche = new CostCtrlSchedule();
						
						//*定标时间(i=2)
						if(i==2 && StringUtils.isNotBlank(tmp.getBidCompleteDate())){
							sche.setTargetDate(DateParser.parse(tmp.getBidCompleteDate(),"yyyy-MM-dd"));
						}
						
						sche.setSerialNo(i);
						sche.setScheduleTypeCd(key);
						sche.setScheduleTypeName(scheMap.get(key));
						sche.setCostCtrlBidPurc(zc);
						scheArray.add(sche);
						i++;
					}
				}
				//设置节点
				zc.setCostCtrlSchedules(scheArray);
				
				//设置留言
				CostCtrlMessage msg = new CostCtrlMessage();
				msg.setCostCtrlBidPurc(zc);
				msg.setMsgContentDesc("【网批导入，查询号:"+fore.getResApproveInfo().getDisplayNo()+"】");
				zc.getCostCtrlMessages().add(msg);
				





				//add by huangbijin 2012-06-28  同项目的计划编号一致
				String tmpPrjCd = zc.getProjectCd();
				String tmpDataTypeCd = zc.getDataTypeCd();
				String key = (StringUtils.isBlank(tmpDataTypeCd)?"":tmpDataTypeCd) + "_" + tmpPrjCd;
				
				//序号赋值
				//若本批次不存在，则查询库的最大值；
				Long preSerialNo = lastMap.get(key);
				if(preSerialNo == null){
					//已经+1
					long dbSerialNo = this.getNextSerialNo(tmpDataTypeCd, tmpPrjCd);
					zc.setSerialNo(dbSerialNo);
				}else{
					zc.setSerialNo(preSerialNo+1);
				}
				this.saveCostCtrlBidPurc(zc);
				lastMap.put(key, zc.getSerialNo());

				
				
				
				
				

				//发送发送内部邮件
				String content = new StringBuffer()
						.append("网批导入项目招采任务,请悉知！<br/>")
						.append("网批类型: 成本月战略计划(集团)").append("<br/>")
						.append("网批编号: ").append(res.getApproveCd()).append(res.getSerialNo()).append("<br/>")
						.append("项目名称: ").append(tmp.getProjectName()).append("<br/>")
						.append("招采序号: ").append(zc.getSerialType()).append(zc.getSerialNo()).append("<br/>")
						.append("事          项:").append(tmp.getProjectDesc()).append("/").append(tmp.getInvPurDesc()).append("<br/>")
						.append("<div style='color:red'>烦请确认相关负责人员.</div>")
						.toString();
			
				sendEmail(tmp.getProjectCd(), "【项目招采任务 - 新增通知】网批编号:" + res.getApproveCd() + res.getSerialNo(), content);
			}
		}
	
	}
	/**
	 * 战略计划—导入成本
	 * @param costPlan
	 */
	public void importZlPlanByBis(CostZlMonthPlanBisgroup costPlan){
		if(costPlan == null || costPlan.getPlanProperties()== null || costPlan.getPlanProperties().size() == 0)
			return;
		
		CostCtrlBidPurc zc = null;
		String srcTitle = null;
		List<CostCtrlSchedule> scheArray = null;
		Map<String, String> scheMap = null;
		CostCtrlSchedule sche = null;
		//序号
		Map<String, Long> lastMap = new HashMap<String, Long>();
		ResApproveInfo res = costPlan.getResApproveInfo();
		for (CostZlMonthPlanBisgroupProperty tmp : costPlan.getPlanProperties()) {
			if(tmp != null){ 
				zc = new CostCtrlBidPurc();
				
				//系统生成
				//zc.setCostCtrlBidPurcId(null);
				
				//战略
				zc.setDataTypeCd(DataTypeCd_ZL);
				//*所属项目
				zc.setProjectCd(tmp.getProjectCd());
				
				//*类型,这里不用设置,实体的保存方法有作判断
				//zc.setSerialType(getSerialType(zc));
				//zc.setSerialNo(getNextSerialNo(zc));
				
				//*紧急度（0-低|1-普通|2-重要）
				zc.setImportantTypeCd(ImportantTypeCd_NORMAL);
				//计划类型（0-计划内、1-计划外、2-半年计划、3-执行计划），在新增的时候，只有可能是计划内和计划外两种
				zc.setPlanTypeCd(PlanTypeCd_IN);
				//计划事项（工程名称+招标范围/采购内容）
				zc.setPlanContentDesc(tmp.getProjectDesc()+ (StringUtils.isNotBlank(tmp.getInvPurDesc())?("/"+tmp.getInvPurDesc()):""));
				
				zc.setOwnerCds(null);
				zc.setOwnerNames(null);
				zc.setIsCompletedFlag(null);
				zc.setUpdateContentDesc(null);
				//供应商为 未确认
				zc.setIsSuppCompleteFlag(SuppCompleteFlag_NO);
				//设置数据状态为 新增
				zc.setScheduleStatusCd(ScheduleStatusCd_NEW);
				zc.setIsWaitProjectFlag(null);
				zc.setWaitProjectDesc(null);
				zc.setIsWaitTechFlag(null);
				zc.setWaitTechDesc(null);
				zc.setIsWaitOtherFlag(null);
				zc.setWaitDeptCd(null);
				zc.setWaitDeptName(null);
				zc.setWaitDesc(null);

				//*定标时间
				if(StringUtils.isNotBlank(tmp.getBidCompleteDate())){
					zc.setBidDate(DateParser.parse(tmp.getBidCompleteDate(),"yyyy-MM-dd"));
				}
				
				//合同状态
				zc.setContractStatusCd(ContractStatusCd_NO);
//				zc.setBidCheckStatusCd(BidCheckStatusCd_NO);
				
				//*招标状态（0-新增、1-进行中、2-已完成）
				zc.setBidStatusCd(BidStatusCd_NEW);
				
				//数据来源类型(0-普通 1-半年计划 5-网批导入)
				zc.setDataSrcTypeCd(DataSrcTypeCd_RES);
				zc.setDataSrcId(costPlan.getResApproveInfo().getResApproveInfoId());
				//是否有效：1-是 0-否; 若数据源已删除,则为0
				zc.setDataSrcStatusCd(DataSrcStatusCd_ENABLE);
				//数据来源描述
				srcTitle = CodeNameUtil.getResAuthTypeNameByCd(costPlan.getResApproveInfo().getAuthTypeCd());
				zc.setDataSrcDesc(StringUtils.isNotBlank(srcTitle)?srcTitle:(costPlan.getPlanYear()+"年" + costPlan.getPlanMonth() + "月招标战略计划(商业)"));
				zc.setDeleteFlg(DeleteFlg_NO);

				//初始化进度数据
				scheArray = new ArrayList<CostCtrlSchedule>();
				scheMap = DictMapUtil.getMapCostStratage();
				if(scheMap != null){
					scheMap.remove("");
					int i = 1;
					for(String key : scheMap.keySet()){
						sche = new CostCtrlSchedule();
						
						//*定标时间(i=2)
						if(i==2 && StringUtils.isNotBlank(tmp.getBidCompleteDate())){
							sche.setTargetDate(DateParser.parse(tmp.getBidCompleteDate(),"yyyy-MM-dd"));
						}
						
						sche.setSerialNo(i);
						sche.setScheduleTypeCd(key);
						sche.setScheduleTypeName(scheMap.get(key));
						sche.setCostCtrlBidPurc(zc);
						scheArray.add(sche);
						i++;
					}
				}
				//设置节点
				zc.setCostCtrlSchedules(scheArray);
				
				//设置留言
				CostCtrlMessage msg = new CostCtrlMessage();
				msg.setCostCtrlBidPurc(zc);
				msg.setMsgContentDesc("【网批导入，查询号:"+costPlan.getResApproveInfo().getDisplayNo()+"】");
				zc.getCostCtrlMessages().add(msg);
				






				//add by huangbijin 2012-06-28  同项目的计划编号一致
				String tmpPrjCd = zc.getProjectCd();
				String tmpDataTypeCd = zc.getDataTypeCd();
				String key = (StringUtils.isBlank(tmpDataTypeCd)?"":tmpDataTypeCd) + "_" + tmpPrjCd;
				
				//序号赋值
				//若本批次不存在，则查询库的最大值；
				Long preSerialNo = lastMap.get(key);
				if(preSerialNo == null){
					//已经+1
					long dbSerialNo = this.getNextSerialNo(tmpDataTypeCd, tmpPrjCd);
					zc.setSerialNo(dbSerialNo);
				}else{
					zc.setSerialNo(preSerialNo+1);
				}
				this.saveCostCtrlBidPurc(zc);
				lastMap.put(key, zc.getSerialNo());

				
				
				
				
				

				//发送发送内部邮件
				String content = new StringBuffer()
						.append("网批导入项目招采任务,请悉知！<br/>")
						.append("网批类型: 成本月战略计划(集团)").append("<br/>")
						.append("网批编号: ").append(res.getApproveCd()).append(res.getSerialNo()).append("<br/>")
						.append("项目名称: ").append(tmp.getProjectName()).append("<br/>")
						.append("招采序号: ").append(zc.getSerialType()).append(zc.getSerialNo()).append("<br/>")
						.append("事          项:").append(tmp.getProjectDesc()).append("/").append(tmp.getInvPurDesc()).append("<br/>")
						.append("<div style='color:red'>烦请确认相关负责人员.</div>")
						.toString();
			
				sendEmail(tmp.getProjectCd(), "【项目招采任务 - 新增通知】网批编号:" + res.getApproveCd() + res.getSerialNo(), content);
			}
		}
	
	}


	/**
	 * 发送内部提醒邮件 
	 */
	private void sendEmail(String projectCd, String title, String content) {
		
		if(StringUtils.isBlank(projectCd))
			return;
		
		WSPlasService service = Util.getPlasService();
		
		//add by huangbijin 2011-10-21
		//项目招采计划:网批中来的招采任务，默认是新增状态,需要副总裁来分配人员, 系统要能自动提醒副总裁来分配
		//获取人员思路: 
		//1.界定当前项目所在区域(例如:南区/北区/上海区)
		//2.根据角色(北区:A_N_BP_CONFIRM,南区:A_S_BP_CONFIRM)查找持有相应南北区确认角色的人员
		//3.逐个用户发送[内部提醒]
		
		//判断项目所属区域,以及相应的角色
		String areaRoleCd = null;
		boolean northFlg = PlasCache.validateContain("ORG_NORTH", projectCd);
		if(northFlg){
			areaRoleCd = "A_N_BP_CONFIRM";//北区-确认
		}else{
			boolean southFlg = PlasCache.validateContain("ORG_SOUTH", projectCd);
			if(southFlg){
				areaRoleCd = "A_S_BP_CONFIRM";//南区-确认
			}/*else{
				//上海归南区
				boolean shFlg = PlasCache.validateContain("SH_SOUTH", projectCd);
				if(shFlg){
					areaRoleCd = "A_S_BP_CONFIRM";//南区-确认
				}
			}*/
		}
		
		if(StringUtils.isBlank(areaRoleCd))
			return;

		OaEmailBodyManager oaEmailBodyManager = SpringContextHolder.getBean("oaEmailBodyManager");
		List<WsPlasRole> allRoleList = PlasCache.getRoleList();
		for (WsPlasRole role : allRoleList) {
			if(areaRoleCd != null && areaRoleCd.equals(role.getRoleCd())){
				List<WsPlasUser> tmpUserList = service.getUserListByRoleId(role.getPlasRoleId());
				if(tmpUserList != null){
					for (WsPlasUser tmpUser : tmpUserList) {
						oaEmailBodyManager.sendData2Email(title, content, "email_admin", "1", tmpUser.getUiid());
					}
				}
				break;
			}
		}
	}
}

