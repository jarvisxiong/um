package com.hhz.ump.dao.bis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisMainShopReport;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DateUtil;

@Service
@Transactional
public class BisMainShopReportManager extends BaseService<BisMainShopReport, String> {
	@Autowired
	private BisMainShopReportDao bisMainShopReprotDao;
	@Autowired
	private BisProjectDao bisProjectDao;
	SimpleDateFormat yyyy =new SimpleDateFormat("yyyy");	
	/**
	 * 获取项目列表
	 * @return
	 */
	public Map<String ,List<BisProject>> getBisProjects(String projectIds) {
 		Map<String, Object> param = new HashMap<String, Object>();
		
		StringBuffer hql = new StringBuffer("from BisProject where mainShopReport = 1 and openDate is not null ");
	
		if(StringUtils.isNotBlank(projectIds)) {
			hql.append(" and  bisProjectId in (:pIds)");
			param.put("pIds", projectIds.split(","));
		}

		hql.append(" order by projectFlg asc openDate asc sequenceNo desc ");
		List<BisProject> bisProjects = bisProjectDao.find(hql.toString(),param);
		Map<String ,List<BisProject>> rest = new ConcurrentSkipListMap<String ,List<BisProject>>();
		for (BisProject bp : bisProjects) {
			if(StringUtils.isNotBlank(bp.getProjectFlg())){
				if(bp.getProjectFlg().equalsIgnoreCase("0")&&compDate(bp.getOpenDate())){
					 groupByProject(rest, bp, "0000");
				}
				if(bp.getProjectFlg().equalsIgnoreCase("1")){
					 groupByProject(rest, bp, yyyy.format(bp.getOpenDate()));
				}
			}
		}
		return rest;
	}
	
	private void groupByProject(Map<String ,List<BisProject>> rest,  BisProject bp, String key ) {
		List<BisProject> list = rest.get(key);
		if(list==null || list.size()==0){
			list=new ArrayList<BisProject>();
			list.add(bp);
			rest.put(key, list);
		}else{
			rest.get(key).add(bp);	
		}

	}
	
	@SuppressWarnings("unchecked")
	public Map<String, BisMainShopReport> listBisMainShopReport(Map<String, Object> param) {
		Map<String, BisMainShopReport> rest = new HashMap<String, BisMainShopReport>();
		Map<String ,List<BisProject>> mapProj = (Map<String, List<BisProject>>) param.get("projects");
		param.remove("projects");
		List<String> pIds = new ArrayList<String>();
		for (String key : mapProj.keySet()) {
			List<BisProject> list = mapProj.get(key);
			for (BisProject bisProject : list) {
				pIds.add(bisProject.getBisProjectId());
			}
		}

		if(pIds.size()>0 && StringUtils.isNotBlank((String)param.get("startDay"))&&StringUtils.isNotBlank((String)param.get("endDay"))){
			StringBuffer hql = new StringBuffer("from  BisMainShopReport where bisProjectId in (:pIds) ");
			hql.append(" and startDay =:startDay ");
			hql.append(" and endDay =:endDay ");
			hql.append(" order by bisProjectId desc");
			param.put("pIds", pIds);
			List<BisMainShopReport> mainShopReports = bisMainShopReprotDao.find(hql.toString(), param);
			for (BisMainShopReport mainShopReport : mainShopReports) {
			
				rest.put(mainShopReport.getBisProjectId(), mainShopReport);
			}
		}
		return rest;
	}
	/**
	 * 项目报表审核人+审核时间
	 * @return
	 */
	public String getProjectFecher(){
		StringBuffer hql = new StringBuffer(" from BisMainShopReport where status_flg = 2 ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startDay", DateUtil.getFirstDayOfPreWeek());
		params.put("endDay", DateUtil.getLastDayOfPreWeek());
		hql.append("and startDay =:startDay and endDay =:endDay");
		List<BisMainShopReport> projects = bisMainShopReprotDao.find(hql.toString(),params);
		if(projects==null||projects.isEmpty())
			return "";
		else{
			String str = CodeNameUtil.getUserNameByCd(projects.get(0).getUpdator())+"  "+DateUtil.parseDateToString(projects.get(0).getUpdatedDate());
			return str;
		}
	}
	/**
	 * 项目报表审核人+审核时间
	 * 
	 * @return
	 */
	public String getProjectFecher(String startTime,String endTime){
		if(StringUtils.isEmpty(startTime)||StringUtils.isEmpty(endTime))
			return "";
		else{
			StringBuffer hql = new StringBuffer(" from BisMainShopReport where status_flg = 2 ");
			hql.append("and startDay =:startDay and endDay =:endDay");
			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("startDay", DateUtil.parseStringToDate(startTime));
//			params.put("endDay", DateUtil.parseStringToDate(endTime));
			params.put("startDay", startTime);
			params.put("endDay", endTime);
			List<BisMainShopReport> projects = bisMainShopReprotDao.find(hql.toString(),params);
			if(projects.isEmpty()||projects.size()<1)
				return "";
			else{
				String str = CodeNameUtil.getUserNameByCd(projects.get(0).getUpdator())+"  "+DateUtil.parseDateToString(projects.get(0).getUpdatedDate());
				return str;	
			}
		}
	}
	/***
	 * 查询当前时间条件下主力店项目编号
	 * @param param
	 * @return 项目编号集合,以逗号分割
	 */
	public String getBisProjectIds(Map<String, Object> params){
		StringBuffer bisProcIds = new StringBuffer();
		if(params.isEmpty())
			return "";
		else {
			StringBuffer hql = new StringBuffer(" from BisMainShopReport where 1=1 ");
			hql.append("and startDay =:startDay and endDay =:endDay");
			List<BisMainShopReport> projects = bisMainShopReprotDao.find(hql.toString(),params);
			for (BisMainShopReport report : projects) {
				bisProcIds.append(report.getBisProjectId()).append(",");
			}
			return bisProcIds.toString();
		}
	}
	/**
	 * 周开始时间日期
	 * @param calendar
	 * @return
	 */
	public Date getFirstDayOfWeek(Calendar calendar)
	{
	    calendar = (calendar == null) ? GregorianCalendar.getInstance() : calendar;
	    int curDay = calendar.get(7);
	    if (curDay == 1) {
			calendar.add(5, -6);
		} else {
			calendar.add(5, 2 - curDay);
		}
	    return calendar.getTime();
  }
	/**
	 * 周结束日期
	 * @param calendar
	 * @return
	 */
	public Date getLastDayWeek(Calendar calendar)
	{
	    calendar = (calendar == null) ? GregorianCalendar.getInstance() : calendar;
	    int curDay = calendar.get(7);
	    if (curDay != 1) {
			calendar.add(5, 8 - curDay);
		}
	    return calendar.getTime();
	}
	/***
	 * 判断是否是老商铺
	 * @param date
	 * @return
	 */
	private boolean compDate(Date date){
		if(date==null)
			return true;
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			int tint = Integer.parseInt(sdf.format(date));
			if(tint<2011)
				return true;
			else
				return false;
		}
	}
	
	/**
	 * 决策平台-查询得到主力店报表数据
	 * @param param 时间段参数
	 * @return
	 */
	public List<BisMainShopReport> getBisMainShopReportList(Map<String, Object> param) {
		Map<String ,List<BisProject>> mapProj = (Map<String, List<BisProject>>) param.get("projects");
		param.remove("projects");
		List<String> pIds = new ArrayList<String>();
		for (String key : mapProj.keySet()) {
			List<BisProject> list = mapProj.get(key);
			for (BisProject bisProject : list) {
				pIds.add(bisProject.getBisProjectId());
			}
		}
		List<BisMainShopReport> mainShopReports = null;
		if(pIds.size()>0 && StringUtils.isNotBlank((String)param.get("startDay"))&&StringUtils.isNotBlank((String)param.get("endDay"))){
			StringBuffer hql = new StringBuffer("from  BisMainShopReport t where t.bisProjectId in (:pIds) ");
			hql.append(" and t.startDay =:startDay ");
			hql.append(" and t.endDay =:endDay ");
			hql.append(" and t.statusFlg =:statusFlg");
			hql.append(" order by t.bisProjectId asc");
			param.put("pIds", pIds);
			param.put("statusFlg", "2"); // 2确认状态
			mainShopReports = bisMainShopReprotDao.find(hql.toString(), param);
		}
		return mainShopReports;
	}
	
	public void saveBisMainShopReprot(BisMainShopReport bisMainShopReprot) {
		PowerUtils.setEmptyStr2Null(bisMainShopReprot);
		bisMainShopReprotDao.save(bisMainShopReprot);
	}

	public void deleteBisMainShopReprot(String id) {
		bisMainShopReprotDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisMainShopReport, String> getDao() {
		return bisMainShopReprotDao;
	}
	
}

