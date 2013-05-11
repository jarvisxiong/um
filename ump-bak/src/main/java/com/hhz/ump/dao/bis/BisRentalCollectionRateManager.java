package com.hhz.ump.dao.bis;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisRentalCollectionRate;
import com.hhz.ump.util.BisRentalCollectionRateUtil;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.web.vo.VoFact;

@Service
@Transactional
public class BisRentalCollectionRateManager extends BaseService<BisRentalCollectionRate, String> {
	@Autowired
	private BisRentalCollectionRateDao bisRentalCollectionRateDao;
	@Autowired
	private BisMustManager bisMustManager;
	@Autowired
	private BisFactManager bisFactManager;
	@Autowired
	private BisProjectDao bisProjectDao;
	SimpleDateFormat yyyy =new SimpleDateFormat("yyyy");

	public void saveBisRentalCollectionRate(BisRentalCollectionRate bisRentalCollectionRate) {
		PowerUtils.setEmptyStr2Null(bisRentalCollectionRate);
		bisRentalCollectionRateDao.save(bisRentalCollectionRate);
	}

	public void deleteBisRentalCollectionRate(String id) {
		bisRentalCollectionRateDao.delete(id);
	}
	/***
	 * 获取项目列表
	 * @param rentalFlag 租费收缴标识
	 * @return
	 */
	public Map<String ,List<BisProject>> getBisProjects(String rentalFlag) {
 		Map<String, Object> param = new HashMap<String, Object>();
		param.put("rentalCollectionRate", rentalFlag);
		StringBuffer hql = new StringBuffer("from BisProject where rentalCollectionRate = :rentalCollectionRate and openDate is not null ");
		hql.append(" order by projectFlg asc openDate asc sequenceNo desc ");
		List<BisProject> bisProjects = bisProjectDao.find(hql.toString(),param);
		Map<String ,List<BisProject>> rest = new ConcurrentSkipListMap<String ,List<BisProject>>();
		for (BisProject bp : bisProjects) {
			if(StringUtils.isNotBlank(bp.getProjectFlg())){
				if(bp.getProjectFlg().equalsIgnoreCase("0")){
					 groupByProject(rest, bp, "0000");
				}
				if(bp.getProjectFlg().equalsIgnoreCase("1")){
					 groupByProject(rest, bp, yyyy.format(bp.getOpenDate()));
				}
			}
		}
		return rest;
	}
	/***
	 * 获取项目列表
	 * @param rentalFlag 租费收缴标识
	 * @return
	 */
	public Map<String ,List<BisProject>> getBisProjects(String rentalFlag,String projectId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("rentalCollectionRate", rentalFlag);
		StringBuffer hql = new StringBuffer("from BisProject where rentalCollectionRate = :rentalCollectionRate and openDate is not null ");

		if(StringUtils.isNotBlank(projectId)) {
			hql.append(" and  bisProjectId in (:pIds)");
			param.put("pIds", projectId.split(","));
		}
		hql.append(" order by projectFlg asc openDate asc sequenceNo desc ");
		List<BisProject> bisProjects = bisProjectDao.find(hql.toString(),param);
		Map<String ,List<BisProject>> rest = new ConcurrentSkipListMap<String ,List<BisProject>>();
		for (BisProject bp : bisProjects) {
			if(StringUtils.isNotBlank(bp.getProjectFlg())){
				if(bp.getProjectFlg().equalsIgnoreCase("0")){
					groupByProject(rest, bp, "0000");
				}
				if(bp.getProjectFlg().equalsIgnoreCase("1")){
					groupByProject(rest, bp, yyyy.format(bp.getOpenDate()));
				}
			}
		}
		return rest;
	}
	/***
	 * 搜索出集合 供ftl模板使用
	 * @param param 包含开始时间，结束时间，项目列表
	 * @return
	 */
	public Map<String, BisRentalCollectionRate> listBisMainShopReport(Map<String, Object> param) {
		Map<String, BisRentalCollectionRate> rest = new HashMap<String, BisRentalCollectionRate>();
		Map<String ,List<BisProject>> mapProj = (Map<String, List<BisProject>>) param.get("projects");
		param.remove("projects");
		/**操作类型*/
		String method = param.get("method")==null?"":param.get("method").toString();
		List<String> pIds = new ArrayList<String>();
		for (String key : mapProj.keySet()) {
			List<BisProject> list = mapProj.get(key);
			for (BisProject bisProject : list) {
				pIds.add(bisProject.getBisProjectId());
				//如果是新增，周报搜索则不进行此操作
				if("new".equals(method)||"rental".equals(method)){
				}else{
					rest.put(bisProject.getBisProjectId(), new BisRentalCollectionRate());
				}
			}
		}

		if(pIds.size()>0 && StringUtils.isNotBlank((String)param.get("startDay"))&&StringUtils.isNotBlank((String)param.get("endDay"))){
			StringBuffer hql = new StringBuffer("from  BisRentalCollectionRate where bisProjectId in (:pIds) ");
			hql.append(" and startDay =:startDay ");
			hql.append(" and endDay =:endDay ");
			param.put("pIds", pIds);
			List<BisRentalCollectionRate> rentalCollectionRates = bisRentalCollectionRateDao.find(hql.toString(), param);
			for (BisRentalCollectionRate rentalCollectionRate : rentalCollectionRates) {
				rest.put(rentalCollectionRate.getBisProjectId(), rentalCollectionRate);
			}
		}
		return rest;
	}
	
	/**
	 * 项目报表审核人+审核时间
	 * @return
	 */
	public String getProjectFecher(){
		StringBuffer hql = new StringBuffer(" from BisRentalCollectionRate where status_flg = 2 ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startDay", DateUtil.getFirstDayOfPreWeek());
		params.put("endDay", DateUtil.getLastDayOfPreWeek());
		hql.append("and startDay =:startDay and endDay =:endDay");
		List<BisRentalCollectionRate> projects = bisRentalCollectionRateDao.find(hql.toString(),params);
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
			StringBuffer hql = new StringBuffer(" from BisRentalCollectionRate where status_flg = 2 ");
			hql.append("and startDay =:startDay and endDay =:endDay");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("startDay", startTime);
			params.put("endDay", endTime);
			List<BisRentalCollectionRate> projects = bisRentalCollectionRateDao.find(hql.toString(),params);
			if(projects.isEmpty()||projects.size()<1)
				return "";
			else{
				String str = CodeNameUtil.getUserNameByCd(projects.get(0).getUpdator())+"  "+DateUtil.parseDateToString(projects.get(0).getUpdatedDate());
				return str;	
			}
		}
	}
	/****
	 * 将查到的结果进行分类排序
	 * @param rest
	 * @param bp
	 * @param key
	 */
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
	@Override
	public HibernateDao<BisRentalCollectionRate, String> getDao() {
		return bisRentalCollectionRateDao;
	}
	/***
	 * 获得上个半月信息
	 * @param date
	 * @return
	 */
	public String getLastHalfMonth(String date){
		String tDate = "2012-11-11";
		if(!date.isEmpty()){
			tDate = date.substring(date.lastIndexOf("-"));
		}
		return tDate;
	}
	/***
	 * 搜索租费汇总
	 * @param param 包含项目集合;当前月，本月，上一月，上上月	格式:yyyy-mm
	 * @return
	 */
	public Map<String, BisRentalCollectionRateUtil> bisRentSumary(Map<String, Object> param) {
		Map<String, BisRentalCollectionRateUtil> rest = new HashMap<String, BisRentalCollectionRateUtil>();
		Map<String ,List<BisProject>> mapProj = (Map<String, List<BisProject>>) param.get("projects");
		param.remove("projects");
		List<String> pIds = new ArrayList<String>();
		for (String key : mapProj.keySet()) {
			List<BisProject> list = mapProj.get(key);
			for (BisProject bisProject : list) {
				pIds.add(bisProject.getBisProjectId());
				//加这句的原因是就算没有数据也能在前台显示0
				rest.put(bisProject.getBisProjectId(), new BisRentalCollectionRateUtil());
			}
		}

		if(pIds.size()>0 && StringUtils.isNotBlank((String)param.get("theMonth"))&&StringUtils.isNotBlank((String)param.get("curMonth"))&&StringUtils.isNotBlank((String)param.get("beforeMonth"))&&StringUtils.isNotBlank((String)param.get("lastMonth"))){
			List<BisRentalCollectionRateUtil> rentalCollectionRateUtils = getRentalRateUtilList(param,pIds);
			
			/**
			 * 如果是'无锡项目'，则覆盖原有数据，从收费明细中取值。-add by liuzhihui 2012-07-17
			 */
			String wuxiProjectId = "40282b8927a42dff0127a433cd3100d5";
			for (BisRentalCollectionRateUtil rentalCollectionRateUtil : rentalCollectionRateUtils) {
				if(wuxiProjectId.equals(rentalCollectionRateUtil.getBisProjectId())){
					//移除从原来的值
					rentalCollectionRateUtils.remove(rentalCollectionRateUtil);
					break;
				}
			}
			//得到新获取的值
			BisRentalCollectionRateUtil rate =  this.getSingleProjectRate(param, wuxiProjectId);
			if(rate != null){
				rentalCollectionRateUtils.add(rate);
			}
			
			for (BisRentalCollectionRateUtil rentalCollectionRateUtil : rentalCollectionRateUtils) {
				rest.put(rentalCollectionRateUtil.getBisProjectId(), rentalCollectionRateUtil);
			}
		}
		return rest;
	}

	/**
	 * 得到无锡项目的两费收缴汇总数据(从‘收费明细’中取数据，其余项目公司则是从‘两费收缴率双周报’中取数据)
	 * @param param
	 * @param bisProjectId
	 * @return
	 */
	private BisRentalCollectionRateUtil getSingleProjectRate(Map<String, Object> param,String bisProjectId){
		BisRentalCollectionRateUtil vo =new BisRentalCollectionRateUtil();
		Map<String, Object> map = new HashMap<String,Object>();
		String theMonth = (String) param.get("theMonth");
		String lastDate = (String) param.get("lastMonth");
		String lastOnDate = (String) param.get("beforeMonth");
		theMonth = theMonth.substring(0, 7);
		lastDate = lastDate.substring(0, 7);
		lastOnDate = lastOnDate.substring(0, 7);
		String firstMonth = this.getFirstMonth(theMonth);
		map.put("bisProjectId", bisProjectId);
		map.put("statusCd","1"); //审核状态
		map.put("chargeTypeCd", "1"); //租金
		map.put("chargeTypeCds", new String[]{"3","4"}); //物业(综合管理费+物业费)
		
		/**-----------------租金实收-----------------------**/
		StringBuffer sql1 = new StringBuffer("select sum(t.money) from bis_fact t where t.bis_project_id = :bisProjectId")
			.append(" and t.charge_type_cd =:chargeTypeCd and t.bis_tenant_id is not null and t.bis_cont_id is not null and t.status_cd =:statusCd")
			.append(" and (ltrim(t.fact_year) || '-' || lpad(t.fact_month, 2, '0')) >= :startDate")
			.append(" and (ltrim(t.fact_year) || '-' || lpad(t.fact_month, 2, '0')) <= :endDate");
		StringBuffer sql2 = new StringBuffer("select sum(t.money) from bis_fact t where t.bis_project_id = :bisProjectId")
		    .append(" and t.charge_type_cd =:chargeTypeCd and t.bis_tenant_id is not null and t.bis_cont_id is not null and t.status_cd =:statusCd")
		    .append(" and (ltrim(t.fact_year) || '-' || lpad(t.fact_month, 2, '0')) = :endDate");
		map.put("startDate", firstMonth);
		map.put("endDate", theMonth);
		vo.setRentalRentSs(this.getValueBySql(sql1.toString(), map, "1","1")); //租金实收-当月累计
		map.put("endDate", lastDate);
		vo.setLastRentalRentSs(this.getValueBySql(sql1.toString(), map, "1","1"));//租金实收-上月累计
		map.put("endDate", lastOnDate);
		vo.setBeforeRentalRentSs(this.getValueBySql(sql1.toString(), map, "1","1"));//租金实收-上上月累计
		map.put("startDate", theMonth);
		map.put("endDate", theMonth);
		vo.setRentalRentSsCur(this.getValueBySql(sql2.toString(), map, "1","1"));//租金实收-当月
		
		/**-----------------租金应收-----------------------**/
		StringBuffer sql3 = new StringBuffer("select sum(t.money) as rentalRentYs from bis_must t where t.bis_project_id = :bisProjectId")
			.append(" and t.charge_type_cd =:chargeTypeCd and t.bis_tenant_id is not null and t.bis_cont_id is not null")
			.append(" and (ltrim(t.must_year) || '-' || lpad(t.must_month, 2, '0')) >= :startDate")
			.append(" and (ltrim(t.must_year) || '-' || lpad(t.must_month, 2, '0')) <= :endDate");
		StringBuffer sql4 = new StringBuffer("select sum(t.money) as rentalRentYsCur from bis_must t where t.bis_project_id = :bisProjectId")
			.append(" and t.charge_type_cd =:chargeTypeCd and t.bis_tenant_id is not null and t.bis_cont_id is not null")
			.append(" and (ltrim(t.must_year) || '-' || lpad(t.must_month, 2, '0')) = :endDate");
		map.put("startDate", firstMonth);
		map.put("endDate", theMonth);
		vo.setRentalRentYs(this.getValueBySql(sql3.toString(), map, "2","1")); //租金应收-当月累计
		map.put("endDate", lastDate);
		vo.setLastRentalRentYs(this.getValueBySql(sql3.toString(), map, "2","1"));//租金应收-上月累计
		map.put("endDate", lastOnDate);
		vo.setBeforeRentalRentYs(this.getValueBySql(sql3.toString(), map,"2","1"));//租金应收-上上月累计
		map.put("startDate", theMonth);
		map.put("endDate", theMonth);
		vo.setRentalRentYsCur(this.getValueBySql(sql4.toString(), map, "2","1"));//租金应收-当月
		
		/**-----------------物业实收-----------------------**/
		StringBuffer sql5 = new StringBuffer("select sum(t.money) as rentalPropertySs from bis_fact t where t.bis_project_id = :bisProjectId")
		   .append(" and t.charge_type_cd in(:chargeTypeCds) and t.bis_tenant_id is not null and t.bis_cont_id is not null and t.status_cd =:statusCd")
   		   .append(" and (ltrim(t.fact_year) || '-' || lpad(t.fact_month, 2, '0')) >= :startDate")
   		   .append(" and (ltrim(t.fact_year) || '-' || lpad(t.fact_month, 2, '0')) <= :endDate");
		StringBuffer sql6 = new StringBuffer("select sum(t.money) as rentalPropertySsCur from bis_fact t where t.bis_project_id = :bisProjectId")
		   .append(" and t.charge_type_cd in(:chargeTypeCds) and t.bis_tenant_id is not null and t.bis_cont_id is not null and t.status_cd =:statusCd")
		   .append(" and (ltrim(t.fact_year) || '-' || lpad(t.fact_month, 2, '0')) = :endDate");
		map.put("startDate", firstMonth);
		map.put("endDate", theMonth);
		vo.setRentalPropertySs(this.getValueBySql(sql5.toString(), map, "1","2")); //物业实收-当月累计
		map.put("endDate", lastDate);
		vo.setLastRentalPropertySs(this.getValueBySql(sql5.toString(), map, "1","2"));//物业实收-上月累计
		map.put("endDate", lastOnDate);
		vo.setBeforeRentalPropertySs(this.getValueBySql(sql5.toString(), map, "1","2"));//物业实收-上上月累计
		map.put("startDate", theMonth);
		map.put("endDate", theMonth);
		vo.setRentalPropertySsCur(this.getValueBySql(sql6.toString(), map, "1","2"));//物业实收-当月
		
		/**-----------------物业应收-----------------------**/
		StringBuffer sql7 = new StringBuffer("select sum(t.money) as rentalPropertyYs from bis_must t where t.bis_project_id = :bisProjectId")
		   .append(" and t.charge_type_cd in(:chargeTypeCds) and t.bis_tenant_id is not null and t.bis_cont_id is not null")
   		   .append(" and (ltrim(t.must_year) || '-' || lpad(t.must_month, 2, '0')) >= :startDate")
   		   .append(" and (ltrim(t.must_year) || '-' || lpad(t.must_month, 2, '0')) <= :endDate");
		StringBuffer sql8 = new StringBuffer("select sum(t.money) as rentalPropertyYsCur from bis_must t where t.bis_project_id = :bisProjectId")
		   .append(" and t.charge_type_cd in(:chargeTypeCds) and t.bis_tenant_id is not null and t.bis_cont_id is not null")
		   .append(" and (ltrim(t.must_year) || '-' || lpad(t.must_month, 2, '0')) = :endDate");
		map.put("startDate", firstMonth);
		map.put("endDate", theMonth);
		vo.setRentalPropertyYs(this.getValueBySql(sql7.toString(), map, "2","2")); //物业应收-当月累计
		map.put("endDate", lastDate);
		vo.setLastRentalPropertyYs(this.getValueBySql(sql7.toString(), map, "2","2"));//物业应收-上月累计
		map.put("endDate", lastOnDate);
		vo.setBeforeRentalPropertyYs(this.getValueBySql(sql7.toString(), map, "2","2"));//物业应收-上上月累计
		map.put("startDate", theMonth);
		map.put("endDate", theMonth);
		vo.setRentalPropertyYsCur(this.getValueBySql(sql8.toString(), map, "2","2"));//物业应收-当月
		vo.setBisProjectId(bisProjectId);
		return vo;
	}
	
	/**
	 * 查询返回值
	 * @param sql
	 * @param map
	 * @param type 1：实收、2：应收
	 * @param rateType 1：租金类型、2：物管类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String getValueBySql(String sql,Map<String, Object> map,String type,String rateType){
		BigDecimal total = new BigDecimal(0);
		BigDecimal otherTotal = new BigDecimal(0);
		BigDecimal gyTotal = new BigDecimal(0);
		BigDecimal djTotal = new BigDecimal(0);
		BigDecimal gysfjlTotal = new BigDecimal(0);
		VoFact voFact = new VoFact();
		voFact.setBisProjectId(map.get("bisProjectId").toString());
		voFact.setReportDateStart(map.get("startDate").toString());
		voFact.setReportDateEnd(map.get("endDate").toString());
		//商铺(业态)总计
		List<Object> list = bisRentalCollectionRateDao.findBySql(sql.toString(), map); //商铺
		if("1".equals(rateType)){//租金类型
			voFact.setChargeTypeCd("1");
			if("1".equals(type)){ //实收
				voFact.setStatusCd("1"); //只有'实收'才查审核通过的数据，'应收'在收费记录中已经查的是审核通过的数据
				gyTotal = bisFactManager.factSumByFlat(null, voFact); //公寓
				djTotal = bisFactManager.factSumByMulti(null, voFact); //多经
			}else{ //应收
				gyTotal = bisMustManager.mustSumByFlat(null, voFact);
				djTotal = bisMustManager.mustSumByMulti(null, voFact);
			}
		}else{//物管类型(综合管理费+物业费)
			if("1".equals(type)){//实收
				voFact.setStatusCd("1");
				voFact.setChargeTypeCd("3");//综合管理费
				BigDecimal zhgyTotal = bisFactManager.factSumByFlat(null, voFact);
				BigDecimal zhdjTotal = bisFactManager.factSumByMulti(null, voFact);
				voFact.setChargeTypeCd("4");//物业费
				BigDecimal wygyTotal = bisFactManager.factSumByFlat(null, voFact);
				BigDecimal wydjTotal = bisFactManager.factSumByMulti(null, voFact);
				gyTotal = zhgyTotal.add(wygyTotal);
				djTotal = zhdjTotal.add(wydjTotal);
			}else{ //应收
				voFact.setChargeTypeCd("3"); 
				BigDecimal zhgyTotal = bisMustManager.mustSumByFlat(null, voFact);
				BigDecimal zhdjTotal = bisMustManager.mustSumByFlat(null, voFact);
				voFact.setChargeTypeCd("4");
				BigDecimal wygyTotal = bisMustManager.mustSumByFlat(null, voFact);
				BigDecimal wydjTotal = bisMustManager.mustSumByFlat(null, voFact);
				gyTotal = zhgyTotal.add(wygyTotal);
				djTotal = zhdjTotal.add(wydjTotal);
			}
			//公寓收费记录
			gysfjlTotal = this.getBisFlatRecord(map, type);
		}
		otherTotal = gyTotal.add(djTotal).add(gysfjlTotal);
		if(list != null && list.size() > 0){
			Object obj = list.get(0);
			Double value = Double.valueOf(convDecimal(obj));
			BigDecimal spTotal = BigDecimal.valueOf(value);
			total = spTotal.add(otherTotal);
			total = total.divide(new BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP);
			if("0.00".equals(total.toString()) || "0".equals(total.toString()))
				return "0";
		}
		return total.toString();
	}

	/**
	 * 得到'无锡项目'公寓收费记录的物业费
	 * @param map
	 * @param type  1：实收、2：应收
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getBisFlatRecord(Map<String, Object> map,String type){
		StringBuffer sql = new StringBuffer();
		String sumStr = null;
		if("1".equals(type)){
			sumStr = "sum(t.month_ss)";
		}else{
			sumStr = "sum(t.month_ys)";
		}
		sql.append("select "+sumStr+" from bis_flat_record t where 1=1 ")
		   .append(" and t.bis_flat_id in(select t1.bis_flat_id from bis_flat t1 where t1.bis_project_id = :bisProjectId)")
		   .append(" and (ltrim(t.record_year) || '-' || lpad(t.record_month, 2, '0')) >= :startDate")
   		   .append(" and (ltrim(t.record_year) || '-' || lpad(t.record_month, 2, '0')) <= :endDate");
		List<Object> list = this.findBySql(sql.toString(), map);
		BigDecimal spTotal = new BigDecimal(0);
		if(list != null && list.size() > 0){
			Object obj = list.get(0);
			Double value = Double.valueOf(convDecimal(obj));
			spTotal = BigDecimal.valueOf(value);
		} 
		return spTotal;
	}
	
	//得到当前年月的第一个月(如2012-10则返回2012-01)
	private String getFirstMonth(String ym){
		String[] yearMonth = ym.split("-");
		return yearMonth[0]+"-01";
	}
	
	/***
	 * 获得展现对象集合
	 * sql是拼接展现数据对象，包括16个基本字段
	 * 拼接了4条搜索语句，分别是累计当月、当月、上一月、上上月
	 * @param param
	 * @param pIds
	 * @return
	 */
	private List<BisRentalCollectionRateUtil> getRentalRateUtilList(Map<String, Object> param,List<String> pIds){
		param.put("pId", pIds);
		/**更新参数*/
		Map<String, Object> maps = appendSqlByData(param);
		List<BisRentalCollectionRateUtil> brlist = new ArrayList<BisRentalCollectionRateUtil>();
		StringBuffer sql = new StringBuffer()
		.append("select t.bis_project_id,sum(t.rentalRentYs),sum(t.rentalRentSs),sum(t.rentalPropertyYs),")
		.append("sum(t.rentalPropertySs),sum(t.rentalRentYsCur),sum(t.rentalRentSsCur),sum(t.rentalPropertyYsCur),")
		.append("sum(t.rentalPropertySsCur),sum(t.lastRentalRentYs),sum(t.lastRentalRentSs),sum(t.lastRentalPropertyYs),")
		.append("sum(t.lastRentalPropertySs),sum(t.beforeRentalRentYs),sum(t.beforeRentalRentSs),sum(t.beforeRentalPropertyYs),")
		.append("sum(t.beforeRentalPropertySs),")
		
		/**每个union中 增加能耗相关字段 -add by liuzhihui 2012-06-26**/
		//-------start---------//
		.append("sum(t.rentalEnergySs),sum(t.rentalEnergyYs),sum(t.rentalEnergySsCur),sum(t.rentalEnergyYsCur),")
		.append("sum(t.lastRentalEnergySs),sum(t.lastRentalEnergyYs),sum(t.beforeRentalEnergySs),sum(t.beforeRentalEnergyYs)")
		//--------end----------//
		.append(" from( ")
		.append("select r1.bis_project_id,r1.cumulative_col_rate_rent as rentalRentSs,r1.cumulative_rec_amount_rent as rentalRentYs,")
		.append("r1.cumulative_col_rate_property as rentalPropertySs,r1.cumulative_rec_amount_property as rentalPropertyYs,")
		.append("'0' as rentalRentSsCur,'0' as rentalRentYsCur,'0' as rentalPropertySsCur,'0' as rentalPropertyYsCur,")
		.append("'0' as lastRentalRentSs,'0' as lastRentalRentYs,'0' as lastRentalPropertySs,'0' as lastRentalPropertyYs,")
		.append("'0' as beforeRentalRentSs,'0' as beforeRentalRentYs,'0' as beforeRentalPropertySs,'0' as beforeRentalPropertyYs,")
		
		.append("r1.cumulative_col_rate_ener as rentalEnergySs,r1.cumulative_rec_amount_ener as rentalEnergyYs,")
		.append("0 as rentalEnergySsCur,0 as rentalEnergyYsCur,0 as lastRentalEnergySs,")
		.append("0 as lastRentalEnergyYs,0 as beforeRentalEnergySs,0 as beforeRentalEnergyYs")
		
		.append(" from bis_rental_collection_rate r1")
		.append(" where r1.start_day = :theMonth and r1.bis_project_id in (:pId)")
		.append(" union")
		.append(" select r2.bis_project_id,'0' as rentalRentSs,'0' as rentalRentYs,'0' as rentalPropertySs,'0' as rentalPropertyYs,")
		.append(" r2.cumulative_col_rate_cur_rent as rentalRentSsCur,r2.cumulative_rec_amount_cur_rent as rentalRentYsCur,")
		.append(" r2.cumulative_col_rate_cur_ppt as rentalPropertySsCur,r2.cumulative_rec_amount_cur_ppt as rentalPropertyYsCur,")
		.append(" '0' as lastRentalRentSs,'0' as lastRentalRentYs,'0' as lastRentalPropertySs,'0' as lastRentalPropertyYs,'0' as beforeRentalRentSs,")
		.append(" '0' as beforeRentalRentYs,'0' as beforeRentalPropertySs,'0' as beforeRentalPropertyYs,")
		
		.append("0 as rentalEnergySs,0 as rentalEnergyYs,")
		.append("r2.cumulative_col_rate_cur_ener as rentalEnergySsCur,r2.cumulative_rec_amount_cur_ener as rentalEnergyYsCur,")
		.append("0 as lastRentalEnergySs,0 as lastRentalEnergyYs,0 as beforeRentalEnergySs,0 as beforeRentalEnergyYs")
		
		.append(" from bis_rental_collection_rate r2")
		.append(" where r2.start_day = :curMonth and r2.bis_project_id in (:pId)")
		.append(" union")
		.append(" select r3.bis_project_id,'0' as rentalRentSs,'0' as rentalRentYs,'0' as rentalPropertySs,'0' as rentalPropertyYs,")
		.append(" '0' as rentalRentSsCur,'0' as rentalRentYsCur,'0' as rentalPropertySsCur,'0' as rentalPropertyYsCur,")
		.append(" r3.cumulative_col_rate_rent as lastRentalRentSs,r3.cumulative_rec_amount_rent as lastRentalRentYs,")
		.append(" r3.cumulative_col_rate_property as lastRentalPropertySs,r3.cumulative_rec_amount_property as lastRentalPropertyYs,")
		.append(" '0' as beforeRentalRentSs,'0' as beforeRentalRentYs,'0' as beforeRentalPropertySs,'0' as beforeRentalPropertyYs,")
		
		.append("0 as rentalEnergySs,0 as rentalEnergyYs,")
		.append("0 as rentalEnergySsCur,0 as rentalEnergyYsCur,")
		.append("r3.cumulative_col_rate_ener as lastRentalEnergySs,r3.cumulative_rec_amount_ener as lastRentalEnergyYs,")
		.append("0 as beforeRentalEnergySs,0 as beforeRentalEnergyYs")
		
		.append(" from bis_rental_collection_rate r3")
		.append(" where r3.start_day = :lastMonth and r3.bis_project_id in (:pId)")
		.append(" union")
		.append(" select r4.bis_project_id,'0' as rentalRentSs,'0' as rentalRentYs,'0' as rentalPropertySs,'0' as rentalPropertyYs,'0' as rentalRentSsCur,")
		.append(" '0' as rentalRentYsCur,'0' as rentalPropertySsCur,'0' as rentalPropertyYsCur,'0' as lastRentalRentSs,'0' as lastRentalRentYs,")
		.append(" '0' as lastRentalPropertySs,'0' as lastRentalPropertyYs,r4.cumulative_col_rate_rent as beforeRentalRentSs,")
		.append(" r4.cumulative_rec_amount_rent as beforeRentalRentYs,r4.cumulative_col_rate_property as beforeRentalPropertySs,")
		.append(" r4.cumulative_rec_amount_property as beforeRentalPropertyYs,")
		
		.append("0 as rentalEnergySs,0 as rentalEnergyYs,")
		.append("0 as rentalEnergySsCur,0 as rentalEnergyYsCur,")
		.append("0 as lastRentalEnergySs,0 as lastRentalEnergyYs,")
		.append("r4.cumulative_col_rate_ener as beforeRentalEnergySs,r4.cumulative_rec_amount_ener as beforeRentalEnergyYs")
		
		.append(" from bis_rental_collection_rate r4")
		.append(" where r4.start_day = :beforeMonth and r4.bis_project_id in (:pId)")
		.append(" ) t group by t.bis_project_id ");
		List<Object[]> trentlist = bisRentalCollectionRateDao.findBySql(sql.toString(), maps);
		for (int i = 0; i < trentlist.size(); i++) {
			Object[] obj=trentlist.get(i);
			BisRentalCollectionRateUtil vo =new BisRentalCollectionRateUtil();
			vo.setBisProjectId((String)obj[0]);
			/**累计数据*/
			vo.setRentalRentYs(convDecimal(obj[1]));
			vo.setRentalRentSs(convDecimal(obj[2]));
			vo.setRentalPropertyYs(convDecimal(obj[3]));
			vo.setRentalPropertySs(convDecimal(obj[4]));
			/**当月数据*/
			vo.setRentalRentYsCur(convDecimal(obj[5]));
			vo.setRentalRentSsCur(convDecimal(obj[6]));
			vo.setRentalPropertyYsCur(convDecimal(obj[7]));
			vo.setRentalPropertySsCur(convDecimal(obj[8]));
			/**上月数据*/
			vo.setLastRentalRentYs(convDecimal(obj[9]));
			vo.setLastRentalRentSs(convDecimal(obj[10]));
			vo.setLastRentalPropertyYs(convDecimal(obj[11]));
			vo.setLastRentalPropertySs(convDecimal(obj[12]));
			/**上上月数据*/
			vo.setBeforeRentalRentYs(convDecimal(obj[13]));
			vo.setBeforeRentalRentSs(convDecimal(obj[14]));
			vo.setBeforeRentalPropertyYs(convDecimal(obj[15]));
			vo.setBeforeRentalPropertySs(convDecimal(obj[16]));
			
			/**能耗相关字段**/
			vo.setRentalEnergySs(convDecimal2(obj[17]));
			vo.setRentalEnergyYs(convDecimal2(obj[18]));
			vo.setRentalEnergySsCur(convDecimal2(obj[19]));
			vo.setRentalEnergyYsCur(convDecimal2(obj[20]));
			vo.setLastRentalEnergySs(convDecimal2(obj[21]));
			vo.setLastRentalEnergyYs(convDecimal2(obj[22]));
			vo.setBeforeRentalEnergySs(convDecimal2(obj[23]));
			vo.setBeforeRentalEnergyYs(convDecimal2(obj[24]));
			
			brlist.add(vo);
		}
		
		return brlist;
	}
	
	private BigDecimal convDecimal2(Object value){
		BigDecimal ret = new BigDecimal(0);
		if(null != value){
			ret = (BigDecimal)value;
		}
		return ret;
	}
	
	/***
	 * 确定取上半个月还是下半个月的sql数据
	 * 重新组装param
	 * @param args
	 * @param param
	 * @return
	 */
	private Map<String, Object> appendSqlByData(Map<String, Object> param){
		Map<String, Object> maps = param;
		
		String theMonth = param.get("theMonth")==null?"":param.get("theMonth").toString();
		String curMonth = param.get("curMonth")==null?"":param.get("curMonth").toString();
		String lastMonth = param.get("lastMonth")==null?"":param.get("lastMonth").toString();
		String beforeMonth = param.get("beforeMonth")==null?"":param.get("beforeMonth").toString();
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer sb3 = new StringBuffer();
		StringBuffer sb4 = new StringBuffer();
		sb1.append("select r1.bis_project_id,r1.cumulative_col_rate_rent as rentalRentSs,r1.cumulative_rec_amount_rent as rentalRentYs,")
		.append("r1.cumulative_col_rate_property as rentalPropertySs,r1.cumulative_rec_amount_property as rentalPropertyYs,")
		.append("'0' as rentalRentSsCur,'0' as rentalRentYsCur,'0' as rentalPropertySsCur,'0' as rentalPropertyYsCur,")
		.append("'0' as lastRentalRentSs,'0' as lastRentalRentYs,'0' as lastRentalPropertySs,'0' as lastRentalPropertyYs,")
		.append("'0' as beforeRentalRentSs,'0' as beforeRentalRentYs,'0' as beforeRentalPropertySs,'0' as beforeRentalPropertyYs")
		.append(" from bis_rental_collection_rate r1")
		.append(" where r1.start_day = :theMonth ");
		
		sb2.append(" select r2.bis_project_id,'0' as rentalRentSs,'0' as rentalRentYs,'0' as rentalPropertySs,'0' as rentalPropertyYs,")
		.append(" r2.cumulative_col_rate_rent as rentalRentSsCur,r2.cumulative_rec_amount_rent as rentalRentYsCur,")
		.append(" r2.cumulative_col_rate_property as rentalPropertySsCur,r2.cumulative_rec_amount_property as rentalPropertyYsCur,")
		.append(" '0' as lastRentalRentSs,'0' as lastRentalRentYs,'0' as lastRentalPropertySs,'0' as lastRentalPropertyYs,'0' as beforeRentalRentSs,")
		.append(" '0' as beforeRentalRentYs,'0' as beforeRentalPropertySs,'0' as beforeRentalPropertyYs")
		.append(" from bis_rental_collection_rate r2")
		.append(" where r2.start_day = :curMonth ");
		
		sb3.append(" select r3.bis_project_id,'0' as rentalRentSs,'0' as rentalRentYs,'0' as rentalPropertySs,'0' as rentalPropertyYs,")
		.append(" '0' as rentalRentSsCur,'0' as rentalRentYsCur,'0' as rentalPropertySsCur,'0' as rentalPropertyYsCur,")
		.append(" r3.cumulative_col_rate_rent as lastRentalRentSs,r3.cumulative_rec_amount_rent as lastRentalRentYs,")
		.append(" r3.cumulative_col_rate_property as lastRentalPropertySs,r3.cumulative_rec_amount_property as lastRentalPropertyYs,")
		.append(" '0' as beforeRentalRentSs,'0' as beforeRentalRentYs,'0' as beforeRentalPropertySs,'0' as beforeRentalPropertyYs")
		.append(" from bis_rental_collection_rate r3")
		.append(" where r3.start_day = :lastMonth ");
		
		sb4.append(" select r4.bis_project_id,'0' as rentalRentSs,'0' as rentalRentYs,'0' as rentalPropertySs,'0' as rentalPropertyYs,'0' as rentalRentSsCur,")
		.append(" '0' as rentalRentYsCur,'0' as rentalPropertySsCur,'0' as rentalPropertyYsCur,'0' as lastRentalRentSs,'0' as lastRentalRentYs,")
		.append(" '0' as lastRentalPropertySs,'0' as lastRentalPropertyYs,r4.cumulative_rec_amount_rent as beforeRentalRentYs,")
		.append(" r4.cumulative_col_rate_rent as beforeRentalRentSs,r4.cumulative_col_rate_property as beforeRentalPropertySs,")
		.append(" r4.cumulative_rec_amount_property as beforeRentalPropertyYs")
		.append(" from bis_rental_collection_rate r4")
		.append(" where r4.start_day = :beforeMonth ");
		
		/**
		 * 区分2个报表的取值逻  -update by liuzhihui 2012-07-03
		 */
		if(maps.get("isDecison") != null && "true".equals(maps.get("isDecison").toString())){
			//商业收费情况报表(决策平台)，取值逻辑：只查第二个双周的数据
			maps.put("theMonth", theMonth+"-16");
			maps.put("curMonth", curMonth+"-16");
			maps.put("lastMonth", lastMonth+"-16");
			maps.put("beforeMonth", beforeMonth+"-16");
		}else{
			//两费收缴率汇总报表(营运平台)，取值逻辑：如果第二个双周没有数据则查第一个双周的数据
			maps.put("theMonth", theMonth+"-16");
			List<String> list = bisRentalCollectionRateDao.findBySql(sb1.toString(), maps);
			if(list==null||list.size()==0){
				maps.put("theMonth",theMonth+"-01");
			}
			list = null;
			maps.put("curMonth", curMonth+"-16");
			list = bisRentalCollectionRateDao.findBySql(sb2.toString(), maps);
			if(list==null||list.size()==0){
				maps.put("curMonth",curMonth+"-01");
			}
			list = null;
			maps.put("lastMonth", lastMonth+"-16");
			list = bisRentalCollectionRateDao.findBySql(sb3.toString(), maps);
			if(list==null||list.size()==0){
				maps.put("lastMonth", lastMonth+"-01");
			}
			list = null;
			maps.put("beforeMonth", beforeMonth+"-16");
			list = bisRentalCollectionRateDao.findBySql(sb4.toString(), maps);
			if(list==null||list.size()==0){
				maps.put("beforeMonth",beforeMonth+"-01");
			}
		}
		return maps;
	}
	/**配合数据进行类型转换*/
	private String convDecimal(Object value){
		 BigDecimal ret = new BigDecimal(0);
	        if( value != null ) {
	            if( value instanceof BigDecimal ) {
	                ret = (BigDecimal) value;
	            } else if( value instanceof String ) {
	                ret = new BigDecimal( (String) value );
	            } else if( value instanceof BigInteger ) {
	                ret = new BigDecimal( (BigInteger) value );
	            } else if( value instanceof Number ) {
	                ret = new BigDecimal( ((Number)value).doubleValue() );
	            } else
					throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
	        } else
				return "0";
		return ret.toString();
	}
	
	/**
	 * 决策平台-商业收费情况报表
	 * (取值参考上面的bisRentSumary()方法)
	 * 
	 * @author liuzhihui 2012-06-26
	 * @param param
	 * @return
	 */
	public List<BisRentalCollectionRateUtil> bisRentSumaryList(Map<String, Object> param) {
		List<BisRentalCollectionRateUtil> list = new ArrayList<BisRentalCollectionRateUtil>();
		Map<String, BisRentalCollectionRateUtil> rest = new HashMap<String, BisRentalCollectionRateUtil>();
		Map<String ,List<BisProject>> mapProj = (Map<String, List<BisProject>>) param.get("projects");
		param.remove("projects");
		List<String> pIds = new ArrayList<String>();
		for (String key : mapProj.keySet()) {
			List<BisProject> bisList = mapProj.get(key);
			for (BisProject bisProject : bisList) {
				pIds.add(bisProject.getBisProjectId());
				//加这句的原因是就算没有数据也能在前台显示0
				rest.put(bisProject.getBisProjectId(), new BisRentalCollectionRateUtil());
			}
		}

		if(pIds.size()>0 && StringUtils.isNotBlank((String)param.get("theMonth")) 
				&& StringUtils.isNotBlank((String)param.get("curMonth")) 
				&& StringUtils.isNotBlank((String)param.get("beforeMonth")) 
				&& StringUtils.isNotBlank((String)param.get("lastMonth"))){
			//这个参数很重要，由于“两费收缴汇总”报表和“商业收费情况”报表共同调用了getRentalRateUtilList()用于区分2个报表，方便取值
			param.put("isDecison", "true");  //是否是商业收费情况报表的查询
			list = getRentalRateUtilList(param,pIds);
		}
		return list;
	}

	/**
	 * 根据条件查询数据-决策平台导入excle
	 * @param bisProjectId 项目ID 
	 * @param queryYm 年月(yyyy-MM)
	 * @return
	 */
	public BisRentalCollectionRate getExistsBisRental(String bisProjectId,String queryYm) {
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuffer hql = new StringBuffer("from BisRentalCollectionRate t where 1=1");
		if(StringUtils.isNotBlank(bisProjectId)){
			hql.append(" and t.bisProjectId =:bisProjectId");
			params.put("bisProjectId", bisProjectId);
		}
		if(StringUtils.isNotBlank(queryYm)){
			//第一个双周（2012-01-01~2012-01-15）
			//第二个双周（2012-01-16~2012-01-31）
			//这里查第二个双周的
			String startDay = queryYm+"-16";
			String endDay = queryYm+"-31";
			hql.append(" and t.startDay =:startDay and t.endDay =:endDay");
			params.put("startDay", startDay);
			params.put("endDay", endDay);
		}
		hql.append(" order by t.bisRentalCollectionRatId desc");
		List<BisRentalCollectionRate> list = bisRentalCollectionRateDao.find(hql.toString(), params);
		if(!list.isEmpty())
			return list.get(0);
		return null;
	}

	/**
	 * 批量保存修改
	 * @param rateList
	 */
	public void batchExecute(List<BisRentalCollectionRate> rateList) {
		Session session = bisRentalCollectionRateDao.getSession();
		for (int i = 0; i < rateList.size(); i++) {
			session.saveOrUpdate(rateList.get(i));
			if(i%50 == 0){
				session.flush();
				session.clear();
			}
		}
	}
	
	/**
	 * 根据月份查询每个月第二个双周的数据是否存在
	 * 注：规定第二个双周为每月的16~31号，如：2012-01-16~2012-01-31
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String isExistTwoWeek(String startDate,String endDate) throws Exception{
		String retrunStr = "true";
		List<BisRentalCollectionRate> list = new ArrayList<BisRentalCollectionRate>();
		Map<String,Object> values = new HashMap<String,Object>();
		StringBuffer hql = new StringBuffer("from BisRentalCollectionRate t where 1=1");
		//单月
		if(StringUtils.isNotBlank(startDate) && StringUtils.isBlank(endDate)){
			String startDay = startDate+"-16";
			String endDay = startDate+"-31";
			hql.append(" and t.startDay=:startDay and t.endDay=:endDay");
			values.put("startDay", startDay);
			values.put("endDay", endDay);
			list = bisRentalCollectionRateDao.find(hql.toString(), values);
			if(list.isEmpty()){
				retrunStr = startDate;
			}
		}
		//多月
		else if(StringUtils.isNotBlank(startDate) && StringUtils.isNotBlank(endDate)){
			int e1 = Integer.valueOf(startDate.substring(0, startDate.indexOf("-")));
			int e2 = Integer.valueOf(startDate.substring(startDate.indexOf("-") + 1, startDate.length()));
			int s1 = Integer.valueOf(endDate.substring(0, endDate.indexOf("-")));
			int s2 = Integer.valueOf(endDate.substring(endDate.indexOf("-") + 1, endDate.length()));
			int monthSpace = DateUtil.getMonthCountStr(startDate+"-01", endDate+"-01")+1;
			for (int i = 0; i < monthSpace; i++) {
				String e2_rs = String.valueOf(e2);
				if (e2_rs.length() == 1) {
					e2_rs = "0" + e2_rs;
				}
				String ym = String.valueOf(e1) + "-" + e2_rs;
				String startDay = ym+"-16";
				String endDay = ym+"-31";
				hql.append(" and t.startDay=:startDay and t.endDay=:endDay");
				values.put("startDay", startDay);
				values.put("endDay", endDay);
				list = bisRentalCollectionRateDao.find(hql.toString(), values);
				if(list.isEmpty()){
					retrunStr = ym;
					break;
				}
				
				if (e1 == s1 && e2 == s2) {
					break;
				} else if (e1 == s1) {
					if (e2 == s2) {
						break;
					} else {
						e2++;
					}
				} else if (e1 < s1) {
					if (e2 == 12) {
						e1++;
						e2 = 1;
					} else {
						e2++;
					}
				}
			}
		}
		return retrunStr;
	}
	
}
