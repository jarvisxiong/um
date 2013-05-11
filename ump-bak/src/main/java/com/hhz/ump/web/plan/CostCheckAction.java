package com.hhz.ump.web.plan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.plan.CostCheckManager;
import com.hhz.ump.dao.plan.CostCheckMessageManager;
import com.hhz.ump.dao.plan.CostCheckScheduleManager;
import com.hhz.ump.entity.plan.CostCheck;
import com.hhz.ump.entity.plan.CostCheckMessage;
import com.hhz.ump.entity.plan.CostCheckSchedule;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.uums.entity.ws.WsPlasOrg;

/**
 * 标后核对
 * @author shixy
 *
 */
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "cost-check!list.action", type = "redirect",params = { "pageType", "${pageType}" })})
public class CostCheckAction extends CrudActionSupport<CostCheck> {
	
	private static final long serialVersionUID = 5966654083128527742L;

	@Autowired
	private CostCheckManager costCheckManager;
	
	@Autowired
	private CostCheckScheduleManager costCheckScheduleManager;
	
	@Autowired
	private CostCheckMessageManager costCheckMessageManager;
	
	
	//是否按照日期排序
	private static final String ORDERY_DATE_YES = "1";

	private CostCheck entity;
	
	//搜索条件
	private String pageType;//默认cost_check(未使用)
	private String targetId;
	private String matchBidDate;//审批日期
	private String isUpdateFlg;//是否按日期降序显示
	private String delayFlg;//是否延期
	private String waitFlg;//是否等待
	

	private Map<String, List<CostCheck>> dataMap;

	private Map<String, String> scheChkMap;
	
	private String currentUser;
	
	
	@Override
	protected void prepareModel() throws Exception {
		if(StringUtils.isBlank(getId())){
			entity = new CostCheck();
			initEntity();
		}else{
			entity = costCheckManager.getEntity(getId());
		}
	}
	
	@Override
	public CostCheck getModel() {
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {

		//如果点击的为 已完成 需要做特殊处理
		String tmpScheCd = Struts2Utils.getParameter("filter_EQS_scheduleStatusCd");
		
		
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		//每页显示20条记录
		page.setPageSize(10);
		//加排序，否则可能翻页后还会出现这个项目公司的记录
		
		if(ORDERY_DATE_YES.equals(isUpdateFlg)){
			page.setOrderBy("updatedDate,serialNo");
			page.setOrder(Page.DESC+","+Page.ASC);
		}else{
			page.setOrderBy("projectCd,serialNo");
			page.setOrder(Page.ASC+","+Page.ASC);
		}
		 
		PropertyFilter filter = null;

		//未删除
		filter = new PropertyFilter("EQS_deleteFlg",CostCheckManager.DeleteFlg_NO);
		filters.add(filter);

		//地产公司人员只能查找本公司的数据
		String centerDeptCd = SpringSecurityUtils.getCurrentCenterCd();
		if(StringUtils.isNotBlank(centerDeptCd)){
			WsPlasOrg centerOrg =  PlasCache.getOrgByCd(centerDeptCd);
			//135:各地产公司
			if("135".equals(centerOrg.getPhysicalOrgCd())){
				filter = new PropertyFilter("EQS_projectCd",centerDeptCd);
				filters.add(filter);
			}
		}

		if(StringUtils.isNotBlank(targetId)){
			filter = new PropertyFilter("EQS_costCtrlBidPurcId",targetId);
			filters.add(filter);
		}
		
		//定标月份
		if(StringUtils.isNotBlank(matchBidDate)){
			int tmpYear = Integer.valueOf(matchBidDate.substring(0,4)).intValue();
			int tmpMonth = Integer.valueOf(matchBidDate.substring(5)).intValue();
			
			filter = new PropertyFilter("GED_bidDate",DateOperator.getFirstDayOfMonth12(tmpYear, tmpMonth));
			filters.add(filter);
			filter = new PropertyFilter("LED_bidDate",DateOperator.getLastDayOfMonth12(tmpYear, tmpMonth));
			filters.add(filter);
		}

		//延期
		String tmpDelayFlg = Struts2Utils.getParameter("delayFlg");
		if("1".equals(tmpDelayFlg)){
			Date currDate = DateUtils.truncate(new Date(), Calendar.DATE);
			//因为搜索条件LTD会将日期加上一天，故此日期也需要加上一天
			currDate = DateUtils.addDays(currDate, -1);
			filter = new PropertyFilter("LTD_bidDate",currDate);
			filters.add(filter);
		}
		
		//默认 搜索 未完成的数据
//		if(StringUtils.isBlank(tmpScheCd)){
//			filter = new PropertyFilter("LTS_scheduleStatusCd",SCHEDULE_STATUS_PROCESS);
//			filters.add(filter);
//		}
		
		
		//等待
		String tmpWaitFlg = Struts2Utils.getParameter("waitFlg");
		if("1".equals(tmpWaitFlg)){
			filter = new PropertyFilter("EQS_isWaitProjectFlag_OR_isWaitTechFlag_OR_isWaitOtherFlag","1");
			filters.add(filter);
		}
		
		
		page = costCheckManager.findPage(page, filters);
		
		//数据分组
		//时间
		if(ORDERY_DATE_YES.equals(isUpdateFlg)){
			dataMap = new LinkedHashMap<String, List<CostCheck>>();
			dataMap.put("aaa", page.getResult());
		}else{
			dataMap = this.groupCostCheck(page);
		}
		
		//进度
		scheChkMap = DictMapUtil.getMapCostChkSche(false);
		
		return SUCCESS;
	}

	/**
	 * 对数据按照项目进行分组
	 * @param costPage
	 * @return
	 */
	private Map<String, List<CostCheck>> groupCostCheck(Page<CostCheck> costPage){
		LinkedHashMap<String, List<CostCheck>> map = new LinkedHashMap<String, List<CostCheck>>();
		List<CostCheck> list = null;
		for(CostCheck bidPurc : costPage.getResult() ){
			String prjCd = bidPurc.getProjectCd();
			if(map.containsKey(prjCd)){
				map.get(prjCd).add(bidPurc);
			}else{
				list = new ArrayList<CostCheck>();
				list.add(bidPurc);
				map.put(prjCd, list);
			}
		}
		return map;
	}
	
	@Override
	public String input() throws Exception {
		return null;
	} 
 

	public CostCheck getEntity() {
		return entity;
	}

	public void setEntity(CostCheck entity) {
		this.entity = entity;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	/**
	 * 初始化: 标后核对
	 */
	private void initEntity(){
		
		//设置数据状态为 新增
		entity.setScheduleStatusCd(CostCheckManager.SCHEDULE_STATUS_NEW);
		entity.setDataSrcTypeCd(CostCheckManager.DataSrcTypeCd_DEFAULT);
		entity.setDeleteFlg(CostCheckManager.DeleteFlg_NO);
		 
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
				sche.setCostCheck(entity);
				scheArray.add(sche);
				i++;
			}
		}
		entity.setCostCheckSchedules(scheArray);
		addMsg("新增一条标后核对记录。");
	}
	
	/**
	 * 添加留言
	 * @param content
	 */
	private void addMsg(String content){
		CostCheckMessage msg = new CostCheckMessage();
		msg.setCostCheck(entity);
		msg.setMsgContentDesc("【"+content+"】");
		entity.getCostCheckMessages().add(msg);
	}
	
	@Override
	public void prepareSave() throws Exception {
		prepareModel();
	}
	@Override
	public String save() throws Exception {
		
		String confirmFlg = Struts2Utils.getParameter("confirmFlg");
//		String conCompleteFlg = Struts2Utils.getParameter("conCompleteFlg");//TODO
//		String conReturnFlg = Struts2Utils.getParameter("conReturnFlg");
 
		//设置数据为进行中(已确认)
		if(StringUtils.isNotBlank(confirmFlg)){
			entity.setScheduleStatusCd(CostCheckManager.SCHEDULE_STATUS_PROCESS);
			addMsg("确认/分配信息！");
		}
		
		//新增记录,若审批时间不空,则写入明细的目标日期
		if(StringUtils.isBlank(getId()) && (entity.getBidDate()!= null)){
			entity.getCostCheckSchedules().get(CostCheckScheduleManager.IDX_SCHE_CHIEF_4).setTargetDate(entity.getBidDate());
		}
		
		if(entity.getCostCheckSchedules().get(CostCheckScheduleManager.IDX_SCHE_CHIEF_4).getTargetDate()!= null){
			entity.setBidDate(entity.getCostCheckSchedules().get(CostCheckScheduleManager.IDX_SCHE_CHIEF_4).getTargetDate());
		}
		
		//第四条完成,则认为完成
		if("1".equals(entity.getCostCheckSchedules().get(CostCheckScheduleManager.IDX_SCHE_CHIEF_4).getCompleteStatusCd())){
			entity.getCostCheckSchedules().get(CostCheckScheduleManager.IDX_SCHE_CHIEF_4).setCompleteDate(new Date());
			entity.setScheduleStatusCd(CostCheckManager.SCHEDULE_STATUS_COMPLETE);
			addMsg("已完成标后核对任务！");
		}
		
		 
		//设置数据为已完成
//		if(StringUtils.isNotBlank(conCompleteFlg)){
//			//主记录状态
//			entity.setScheduleStatusCd(SCHEDULE_STATUS_COMPLETE);
//			//明细状态
//			entity.getCostCheckSchedules().get(CostCheckScheduleManager.IDX_SCHE_CHIEF_4).setCompleteStatusCd(CostCheckScheduleManager.ScheduleStatusCd_YES);//0-否 1-是
//			entity.getCostCheckSchedules().get(CostCheckScheduleManager.IDX_SCHE_CHIEF_4).setCompleteDate(new Date());
//				
//			addMsg("已完成标后核对任务！");
//		}
		
		//将数据退回到未定标前的一个已完成状态
//		if(StringUtils.isNotBlank(conReturnFlg)){
//			CostCheckSchedule schedule = null;
//			String tmpTypeCd = "";
//
//			List<CostCheckSchedule> scheArray = entity.getCostCheckSchedules();
//			for(int i=0;i<scheArray.size();i++){
//				
//				schedule = scheArray.get(i);
//				//若最后一条记录,则设置为0-未完成
//				if(i == scheArray.size()-1) {
//					schedule.setCompleteStatusCd(CostCheckScheduleManager.ScheduleStatusCd_NO);
//					break;
//				}
//				
//				if(CostCheckScheduleManager.ScheduleStatusCd_YES.equals(schedule.getCompleteStatusCd())){
//					tmpTypeCd = schedule.getScheduleTypeCd();
//				}
//			}
//			//进度标识（图纸供应，乙方预算，咨询预算。。等）
//			entity.setBidStatusCd(tmpTypeCd);
//			entity.setScheduleStatusCd(SCHEDULE_STATUS_PROCESS);
//			entity.setBidDate(new Date());
//			
//			addMsg("数据退回到未定标状态！");
//		}
		
		costCheckManager.saveCostCheck(entity);
		 
		return RELOAD;
	}

	public String getMatchBidDate() {
		return matchBidDate;
	}

	public void setMatchBidDate(String matchBidDate) {
		this.matchBidDate = matchBidDate;
	}
	public Map<String, List<CostCheck>> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, List<CostCheck>> dataMap) {
		this.dataMap = dataMap;
	}

	public Map<String, String> getScheChkMap() {
		return scheChkMap;
	}

	public void setScheChkMap(Map<String, String> scheChkMap) {
		this.scheChkMap = scheChkMap;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
	
	/**
	 * 保存留言
	 */
	public void prepareSaveMsg() throws Exception{
		prepareModel();
	}
	public void saveMsg() {
		
		String msgContent = Struts2Utils.getParameter("msg");
		CostCheckMessage msg = new CostCheckMessage();
		msg.setCostCheck(entity);
		msg.setMsgContentDesc(msgContent);
		costCheckMessageManager.saveCostCheckMessage(msg);
		
		String curUser = SpringSecurityUtils.getCurrentUserName();
		String now = DateOperator.formatDate(new Date(), "MM-dd HH:mm");
		Struts2Utils.renderText(curUser+"("+now+"):"+msgContent);
	}
	/**
	 * 删除"标后核对"记录
	 */
	public void prepareDelete() throws Exception{
		prepareModel();
	}
	@Override
	public String delete() throws Exception {
	 
		entity.setDeleteFlg(CostCheckManager.DeleteFlg_YES);
		addMsg("删除记录!");
		costCheckManager.saveCostCheck(entity);
		Struts2Utils.renderText("success");
		return null;
	}

	public String getIsUpdateFlg() {
		return isUpdateFlg;
	}

	public void setIsUpdateFlg(String isUpdateFlg) {
		this.isUpdateFlg = isUpdateFlg;
	}

	public String getDelayFlg() {
		return delayFlg;
	}

	public void setDelayFlg(String delayFlg) {
		this.delayFlg = delayFlg;
	}

	public String getWaitFlg() {
		return waitFlg;
	}

	public void setWaitFlg(String waitFlg) {
		this.waitFlg = waitFlg;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	

	/**
	 * 完成(不适用)
	 * id
	 * @return
	 */
	public String complete(){
		
		if(StringUtils.isBlank(getId())){
			Struts2Utils.renderText("failure");
			return null;
		}
		
		entity = costCheckManager.getEntity(getId());
		if(entity == null){
			Struts2Utils.renderText("failure");
			return null;
		}
		
		entity.setScheduleStatusCd(CostCheckManager.SCHEDULE_STATUS_COMPLETE);
		entity.getCostCheckSchedules().get(CostCheckScheduleManager.IDX_SCHE_CHIEF_4).setCompleteStatusCd(CostCheckScheduleManager.ScheduleStatusCd_YES);//0-否 1-是
		entity.getCostCheckSchedules().get(CostCheckScheduleManager.IDX_SCHE_CHIEF_4).setCompleteDate(new Date());
		addMsg("已完成标后核对任务！");
		
		costCheckManager.saveCostCheck(entity);
		Struts2Utils.renderText("success");
		
		return null;
	}
	
	/**
	 * 退回
	 * id
	 * @return
	 */
	public String back(){
		
		if(StringUtils.isBlank(getId())){
			Struts2Utils.renderText("failure");
			return null;
		}
		
		entity = costCheckManager.getEntity(getId());
		if(entity == null){
			Struts2Utils.renderText("failure");
			return null;
		}
		//将数据退回到未定标前的一个已完成状态
		List<CostCheckSchedule> scheArray = entity.getCostCheckSchedules();
		String scheCd = "";
		CostCheckSchedule schedule = null;
		for(int i=0;i<scheArray.size();i++){
			schedule = scheArray.get(i);
			if(i == scheArray.size()-2) {
				schedule.setCompleteStatusCd("0");
				schedule.setCompleteDate(null);
				break;
			}else{
				if("1".equals(schedule.getCompleteStatusCd())){
					scheCd = schedule.getScheduleTypeCd();
				}
			}
		}

		entity.setBidStatusCd(scheCd);
		entity.setScheduleStatusCd(CostCheckManager.SCHEDULE_STATUS_PROCESS);
		addMsg("数据退回到进行中状态！");
		
		costCheckManager.saveCostCheck(entity);
		Struts2Utils.renderText("success");
		
		return null;
	}
}
