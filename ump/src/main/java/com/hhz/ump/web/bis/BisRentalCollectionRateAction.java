package com.hhz.ump.web.bis;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bis.BisRentalCollectionRateManager;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisRentalCollectionRate;
import com.hhz.ump.util.BisRentalCollectionRateUtil;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.util.HelperUtil;
/***
 * 租费收缴率action
 * @author Aspenn
 *
 */
public class BisRentalCollectionRateAction  extends CrudActionSupport<BisRentalCollectionRate> {
	private BisRentalCollectionRateManager bisRentalCollectionRateManager;
	private Map<String, List<BisProject>> rateProjects;
	private BisRentalCollectionRate entity;
	private BisRentalCollectionRate bisRentalCollectionRate;
	private Map<String, BisRentalCollectionRate> rentalCollectionRates;
	private Map<String, BisRentalCollectionRateUtil> rentalCollectionRateUtils;
	
	public HelperUtil helper=new HelperUtil();
	
	@Override
	public String execute() throws Exception {
		
		return "main";
	}
	
	@Override
	public String delete() throws Exception {
		
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		
		return null;
	}

	@Override
	public String input() throws Exception {
		rateProjects = bisRentalCollectionRateManager.getBisProjects("1");
		if (StringUtils.isNotBlank(Struts2Utils.getParameter("id"))) {
			entity = bisRentalCollectionRateManager.getEntity(Struts2Utils
					.getParameter("id"));
		} else {
			entity = new BisRentalCollectionRate();
			entity.setBisProjectId(Struts2Utils.getParameter("pId"));
		}
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		rateProjects = bisRentalCollectionRateManager.getBisProjects("1");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("projects", rateProjects);
		param.put("startDay", Struts2Utils.getParameter("startDay"));
		param.put("endDay", Struts2Utils.getParameter("endDay"));
		param.put("method", "rental");
		rentalCollectionRates = bisRentalCollectionRateManager.listBisMainShopReport(param);
		if(rentalCollectionRates.isEmpty()){
			rateProjects.clear();
		}
		return "list";
	}

	@Override
	protected void prepareModel() throws Exception {
		if(StringUtils.isNotBlank(getId())){
			entity =bisRentalCollectionRateManager.getEntity(getId());
			bisRentalCollectionRate = bisRentalCollectionRateManager.getEntity(getId());
		}else{
			entity =new BisRentalCollectionRate();
		}
	}
	/**
	 * 驳回周报
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("hiding")
	public String reject() throws Exception {
		this.setBisRentalCollectionRate();
		for (BisRentalCollectionRate bisRate : rentalCollectionRates.values()) {
			bisRate.setStatusFlg("3");//状态为1 ：标示已经驳回
			bisRentalCollectionRateManager.saveBisRentalCollectionRate(bisRate);
		}
		return list();
	}
	/**
	 * 确认周报
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("hiding")
	public String affirm() throws Exception {
		this.setBisRentalCollectionRate();
		for (BisRentalCollectionRate bisRate : rentalCollectionRates.values()) {
			bisRate.setStatusFlg("2");//状态为1 ：标示已经确认
			bisRentalCollectionRateManager.saveBisRentalCollectionRate(bisRate);
		}
		return list();
	}
	/**
	 * 提交周报
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("hiding")
	public String submit() throws Exception {
		this.setBisRentalCollectionRate();
		for (BisRentalCollectionRate bisRate : rentalCollectionRates.values()) {
			bisRate.setStatusFlg("1");//状态为1 ：标示已经提交
			bisRentalCollectionRateManager.saveBisRentalCollectionRate(bisRate);
		}
		return list();
	}
	/**设置值*/
	private void setBisRentalCollectionRate() {
		rateProjects = bisRentalCollectionRateManager.getBisProjects("1");//1是记录租费收缴率
		Map<String, Object> param = new HashMap<String, Object>();
		String startDay = Struts2Utils.getParameter("startDay");
		String endDay = Struts2Utils.getParameter("endDay");
		if(!rateProjects.isEmpty()&& StringUtils.isNotBlank(startDay)&&StringUtils.isNotBlank(endDay)) {
			param.put("projects", rateProjects);
			param.put("startDay", Struts2Utils.getParameter("startDay"));
			param.put("endDay", Struts2Utils.getParameter("endDay"));
			rentalCollectionRates = bisRentalCollectionRateManager.listBisMainShopReport(param);
		}else{
			rentalCollectionRates.clear();
			rateProjects.clear();
		}
	}

	/***
	 * 新增前初始化
	 * @param startDay
	 * @param endDay
	 * @return
	 */
	public String add()throws Exception {
		String startDay = Struts2Utils.getParameter("startDay");
		String endDay = Struts2Utils.getParameter("endDay");
		String[] tarray = DateUtil.getLastHalfMonth(startDay);
		if(StringUtils.isNotBlank(startDay)&&StringUtils.isNotBlank(endDay)) {
			rateProjects = bisRentalCollectionRateManager.getBisProjects("1");
			/**其实这里开始、结束时间随便传一个就能搜索出2个值*/
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("projects", rateProjects);
			param.put("startDay",tarray[0]);
			param.put("endDay", tarray[1]);
			param.put("method", "new");
			rentalCollectionRates = bisRentalCollectionRateManager.listBisMainShopReport(param);
			
			if(rentalCollectionRates.isEmpty()){
				for (String key : rateProjects.keySet()) {
					List<BisProject> list = rateProjects.get(key);
					for (BisProject bisProject : list) {
						BisRentalCollectionRate rcr = new BisRentalCollectionRate(bisProject.getBisProjectId(), startDay, endDay);
						rcr.setStatusFlg("0");
						bisRentalCollectionRateManager.saveBisRentalCollectionRate(rcr);
					}
				}
			}else{
				for (BisRentalCollectionRate brcr : rentalCollectionRates.values()) {
					String pointRent = brcr.getRentalCollPointRent()==null?"":brcr.getRentalCollPointRent();
					String pointProperty = brcr.getRentalCollPointProperty()==null?"":brcr.getRentalCollPointProperty();
					BisRentalCollectionRate brcrate = new BisRentalCollectionRate(startDay,endDay,brcr.getBisProjectId(),brcr.getCumulativeRecAmountRent(),brcr.getCumulativeColRateRent(),brcr.getCumulativeRecAmountProperty(),brcr.getCumulativeColRateProperty(),helper.clearHtml(pointRent),helper.clearHtml(pointProperty),brcr.getRemark(),brcr.getCumulativeRecAmountCurRent(),brcr.getCumulativeColRateCurRent(),brcr.getCumulativeRecAmountCurPpt(),brcr.getCumulativeColRateCurPpt());
					brcrate.setStatusFlg("0");
					bisRentalCollectionRateManager.saveBisRentalCollectionRate(brcrate);
					
				}
			}
		}
		return list();
	}
	/**
	 * 向web打出审核人信息
	 */
	public String reGetUpdator(){
		Struts2Utils.renderText(bisRentalCollectionRateManager.getProjectFecher(Struts2Utils.getParameter("startDay"),Struts2Utils.getParameter("endDay")));
		return null;
	}
	/**
	 * 获得本周开始时间和结束时间
	 * 给前台使用
	 * @return
	 * @throws Exception
	 */
	public String getStartEndDay() throws Exception {

		String year = Struts2Utils.getParameter("year");
		String month = Struts2Utils.getParameter("month");
		String weekOfMonth = Struts2Utils.getParameter("weekOfMonth");
		if (StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)
				&& StringUtils.isNotBlank(weekOfMonth)) {
			String[] weekStartEndDay = DateUtil.getDoubleWeekStartEndDay(
					Integer.parseInt(year), Integer.parseInt(month),
					Integer.parseInt(weekOfMonth));
			if (weekStartEndDay != null) {
				Struts2Utils.renderText(weekStartEndDay[0] + "~"
						+ weekStartEndDay[1]);
			}
		}
		return null;
	}
	/***
	 * 获取指定日期的周开始时间、结束时间
	 * @param date(yyyy-mm-dd)
	 * @param type 1:开日期，else：结束日期
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getStartEndDayByDate(String date,int type){
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(DateUtil.yyyy_mm_dd.parse(date));
			calendar.add(5, -7);
			if(1==type)
				return DateUtil.getFirstDayOfWeek(calendar);
			else
				return DateUtil.getLastDayWeek(calendar);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}
	/**将日期向前推7天的形式*/
	@SuppressWarnings("unused")
	private String getLastDate(String date) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateUtil.yyyy_mm_dd.parse(date));
			calendar.add(Calendar.DATE, -7);
			return DateUtil.yyyy_mm_dd.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	@Override
	public String save() throws Exception {
		if(StringUtils.isNotBlank(entity.getBisRentalCollectionRatId())&&entity.getBisRentalCollectionRatId()!=null&&!"".equals(entity.getBisRentalCollectionRatId())){
			entity.setStatusFlg("0");//0-标示状态都是新增
		}
			bisRentalCollectionRateManager.saveBisRentalCollectionRate(entity);
		Struts2Utils.renderText("true");
		return null;
	}
	/***
	 * 累计双费收缴率跳转
	 * @author Aspenn
	 * @return
	 */
	public String cumulative(){
		return "cumuMain";
	}
	/***
	 * 搜索两费收缴率汇总报表
	 * @param type 1:累计本月、2：本月、3：上月、4：下月
	 * @param bisProjectId
	 * @return
	 */
	public String cumuList(){
		//当前月份
		rateProjects = bisRentalCollectionRateManager.getBisProjects("1");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("projects", rateProjects);

		param.put("theMonth",Struts2Utils.getParameter("theMonth"));
		param.put("curMonth",Struts2Utils.getParameter("curMonth"));
		param.put("lastMonth", Struts2Utils.getParameter("lastMonth"));
		param.put("beforeMonth", Struts2Utils.getParameter("beforeMonth"));
		
		rentalCollectionRateUtils = bisRentalCollectionRateManager.bisRentSumary(param);
		return "cumuList";
	}
	@Override
	public BisRentalCollectionRate getModel() {
		
		return null;
	}

	public Map<String, BisRentalCollectionRate> getRentalCollectionRates() {
		return rentalCollectionRates;
	}

	public BisRentalCollectionRateManager getBisRentalCollectionRateManager() {
		return bisRentalCollectionRateManager;
	}

	public void setBisRentalCollectionRateManager(
			BisRentalCollectionRateManager bisRentalCollectionRateManager) {
		this.bisRentalCollectionRateManager = bisRentalCollectionRateManager;
	}

	public BisRentalCollectionRate getEntity() {
		return entity;
	}

	public void setEntity(BisRentalCollectionRate entity) {
		this.entity = entity;
	}

	public void setRateProjects(Map<String, List<BisProject>> rateProjects) {
		this.rateProjects = rateProjects;
	}

	public void setRentalCollectionRates(
			Map<String, BisRentalCollectionRate> rentalCollectionRates) {
		this.rentalCollectionRates = rentalCollectionRates;
	}
	
	public BisRentalCollectionRate getBisRentalCollectionRate() {
		return bisRentalCollectionRate;
	}
	
	
	public Map<String, BisRentalCollectionRateUtil> getRentalCollectionRateUtils() {
		return rentalCollectionRateUtils;
	}

	public void setRentalCollectionRateUtils(
			Map<String, BisRentalCollectionRateUtil> rentalCollectionRateUtils) {
		this.rentalCollectionRateUtils = rentalCollectionRateUtils;
	}

	public void setBisRentalCollectionRate(
			BisRentalCollectionRate bisRentalCollectionRate) {
		this.bisRentalCollectionRate = bisRentalCollectionRate;
	}

	public Map<String, List<BisProject>> getRateProjects() {
		return rateProjects;
	}
	
}
