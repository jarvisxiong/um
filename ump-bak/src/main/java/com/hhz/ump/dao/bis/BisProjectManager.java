package com.hhz.ump.dao.bis;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.entity.bis.BisBudget;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisReport;
import com.hhz.ump.web.vo.BisFlatReportVo;
import com.hhz.ump.web.vo.BisManageProjectVo;
import com.hhz.ump.web.vo.BisMultiReportVo;
import com.hhz.ump.web.vo.BisReportTypeDetailVo;
import com.hhz.ump.web.vo.BisStoreReportVo;
import com.hhz.uums.entity.ws.WsPlasOrg;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class BisProjectManager extends BaseService<BisProject, String> {
	@Autowired
	private BisProjectDao bisProjectDao;
//	private LinkedHashMap<BisProject> bisProject;
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	
	public void saveBisProject(BisProject bisProject) {
		PowerUtils.setEmptyStr2Null(bisProject);
		bisProjectDao.save(bisProject);
	}

	public void deleteBisProject(String id) {
		bisProjectDao.delete(id);
	}
	
	public Map<String, String> getMapBisProject() {
		Map<String, String> mapBisProject = new LinkedHashMap<String, String>();
		mapBisProject.put("", "--选择--");
		String hql = "from BisProject where  order by sequenceNo ";
		List<BisProject> list = bisProjectDao.find(hql);
		for(BisProject bisProject : list){
			mapBisProject.put(bisProject.getBisProjectId(), bisProject.getProjectName());
		}
		return mapBisProject;
	}
	
	public Map<String, String> getMapBisProject(Object[] orgCds) {
		Map<String, String> mapBisProject = new LinkedHashMap<String, String>();
		mapBisProject.put("", "--选择--");
		Map<String, Object> param = new HashMap<String, Object>();
		String hql = "from BisProject where orgCd in (:orgCds) order by sequenceNo ";
		param.put("orgCds", orgCds);
		List<BisProject> list = bisProjectDao.find(hql, param);
		for(BisProject bisProject : list){
			mapBisProject.put(bisProject.getBisProjectId(), bisProject.getProjectName());
		}
		return mapBisProject;
	}
	public List<BisProject> getBisProjectList(Object[] projectIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		String hql = "from BisProject where bisProjectId in (:projectIds) order by sequenceNo ";
		param.put("projectIds", projectIds);
		return bisProjectDao.find(hql, param);
	}
	public List<BisProject> getMapBisProjectByArea(String areaCd) {
		Map<String, String> mapBisProject = new HashMap<String, String>();
		Map<String, Object> param = new HashMap<String, Object>();
		String hql = "from BisProject where areaCd =:areaCd order by sequenceNo ";
		param.put("areaCd", areaCd);
		List<BisProject> list = bisProjectDao.find(hql, param);
		/*for(BisProject bisProject : list){
			mapBisProject.put(bisProject.getBisProjectId(), bisProject.getProjectName());
		}*/
		return list;
	}
	
	/**
	 * 获得登录人员所在项目ID，如果为空或有多个则返回""
	 */
	public BisProject getCurrProject() {
		String currCentCd = SpringSecurityUtils.getCurrentCenterCd();
		Map<String, Object> param = new HashMap<String, Object>();
		String hql="from BisProject where orgCd =:orgCd order by sequenceNo";
		param.put("orgCd", currCentCd);
		List<BisProject> list = bisProjectDao.find(hql, param);
		if(list!=null&&list.size()==1)
			return list.get(0);
		else{
			String currOrgCd = SpringSecurityUtils.getCurrentDeptCd();
			List<WsPlasOrg> orgList = PlasCache.getPhysicalBubbleOrgListByOrgCd(currOrgCd);
			List<String> orgCdList = PowerUtils.getProptyArray(orgList, "orgCd");
			orgCdList.add(currOrgCd);
			param = new HashMap<String, Object>();
			hql = "from BisProject where orgCd in (:orgCds) order by sequenceNo ";
			param.put("orgCds", orgCdList.toArray());
			list = bisProjectDao.find(hql, param);
			if(list.size() >1)
				return list.get(0);
			return null;
		}
	}
	
	@Override
	public HibernateDao<BisProject, String> getDao() {
		return bisProjectDao;
	}
	
//	public String getProjectName(String bisProjectId){
//		String hql="from BisProject where bisProjectId="+"'"+bisProjectId+"'";
//		String projectName;
//		return projectName;
//	}
	
	/**
	 * 搜索经营情况报表支出汇总
	 */
	public List<BisManageProjectVo> getProjectOutTotal(String year,String month){
		Map<String,Integer> tableMap = new HashMap<String, Integer>();
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_106, 9);//违约金
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_107, 10);//成本费用支出
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_108, 11);//广告宣传费
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_109, 12);//人工福利费
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_110, 13);//项目能源费
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_111, 14);//日常办公支出
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_112, 15);//支出合计
		List<BisManageProjectVo> outlist = new LinkedList<BisManageProjectVo>();
		for(int i = 0 ; i<7 ; i++){
			BisManageProjectVo o = new BisManageProjectVo();
			outlist.add(o);
		}
		return outlist;
	}
	/**
	 * 搜索经营情况报表收入汇总
	 */
	public List<BisManageProjectVo> getProjectInTotal(String year,String month){
		List<BisManageProjectVo> outlist = new LinkedList<BisManageProjectVo>();
		for(int i = 0 ; i<42 ; i++){
			BisManageProjectVo o = new BisManageProjectVo();
			outlist.add(o);
		}
		return outlist;
		/*//统计数据
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, year);
		map.put(2, month);
		List<BisManageProjectVo> list = (List<BisManageProjectVo>)
			this.executeFunction("{ call BIS_MANAGER_TOTAL(?,?,?)}", 
				map, BisManageProjectVo.class);
		BigDecimal no = new BigDecimal(0);
		//将结果对号入座
		List<BisManageProjectVo> result = new LinkedList<BisManageProjectVo>();
		Map<String,Integer> tableMap = new HashMap<String, Integer>();
		tableMap.put(DictContants.BIS_CHARGE_TYPE_01, 0);//租金
		tableMap.put(DictContants.BIS_CHARGE_TYPE_32, 1);//综合管理费
		tableMap.put(DictContants.BIS_CHARGE_TYPE_33, 2);//水费
		tableMap.put(DictContants.BIS_CHARGE_TYPE_34, 3);//电费
		tableMap.put(BisManageProjectVo.BIS_CHARGE_TYPE_101, 4);//日常收入合计
		tableMap.put(DictContants.BIS_CHARGE_TYPE_42, 5);//车位管理费
		tableMap.put(BisManageProjectVo.BIS_CHARGE_TYPE_103, 6);//招商代理费
		tableMap.put(			 BisManageProjectVo.BIS_CHARGE_TYPE_104, 7);//经营合计
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_105, 8);//收入总计

		//计算收入总计
		BisManageProjectVo  allChargeCd = new BisManageProjectVo("100","总计",no,no,no,no,no,no);
		BisManageProjectVo[] tmp = new BisManageProjectVo[9];
		for(BisManageProjectVo vo : list){
			allChargeCd.setMy(allChargeCd.getMy().add(vo.getMy()));
			allChargeCd.setFy(allChargeCd.getFy().add(vo.getFy()));
			allChargeCd.setMm(allChargeCd.getMm().add(vo.getMm()));
			allChargeCd.setFm(allChargeCd.getFm().add(vo.getFm()));
			allChargeCd.setRy(allChargeCd.getRy().add(vo.getRy()));
			allChargeCd.setRy(allChargeCd.getRy().add(vo.getRy()));
			//按照map，将相应费用类别放入指定位置
			if(null!=tableMap.get(vo.getCtc())) {
				tmp[tableMap.get(vo.getCtc())] = vo;
			}
		}
		tmp[8]=allChargeCd;
		for(int i = 0 ; i<tmp.length ; i++){
			BisManageProjectVo vo = tmp[i];
			if(vo==null){
				tmp[i]= new BisManageProjectVo();
			}
		}
		list.add(allChargeCd);
		result= Arrays.asList(tmp);
		return result;*/
	}
	/**
	 * 搜索指定项目经营情况汇总(年度、月度)
	 */
	public List<BisManageProjectVo> getProjectInDetail(String year,String month,String bisProjectIds){
		List<BisManageProjectVo> outlist = new LinkedList<BisManageProjectVo>();
		for(int i = 0 ; i<9 ; i++){
			BisManageProjectVo o = new BisManageProjectVo();
			outlist.add(o);
		}
		return outlist;
		/*
		String[] projectIds = bisProjectIds.split(",");
		//统计数据
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, year);
		map.put(2, month);
		map.put(3, projectIds);
		List<BisManageProjectVo> list = (List<BisManageProjectVo>)this.executeFunction("{ call BIS_MANAGER_PROJECT(?,?,?,?)}", map, BisProjectReportVo.class);
		
		//将结果对号入座
		List<BisManageProjectVo> result = new LinkedList<BisManageProjectVo>();
		Map<String,Integer> tableMap = new HashMap<String, Integer>();
		tableMap.put(DictContants.BIS_CHARGE_TYPE_01, 0);//租金
		tableMap.put(DictContants.BIS_CHARGE_TYPE_32, 1);//综合管理费
		tableMap.put(DictContants.BIS_CHARGE_TYPE_33, 2);//水费
		tableMap.put(DictContants.BIS_CHARGE_TYPE_34, 3);//电费
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_101, 4);//日常收入合计
		tableMap.put(DictContants.BIS_CHARGE_TYPE_42, 5);//车位管理费
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_103, 6);//招商代理费
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_104, 7);//经营合计
		tableMap.put(BisManageProjectVo	.		 BIS_CHARGE_TYPE_105, 8);//收入总计
		//计算收入总计
		BigDecimal no = new BigDecimal(0);
		BisManageProjectVo  allChargeCd = new BisManageProjectVo("100","总计",no,no,no,no,no,no);
		BisManageProjectVo[] tmp = new BisManageProjectVo[9];
		Map<String,List<BisManageProjectVo>> projectMap = new HashMap<String, List<BisManageProjectVo>>();
		//将所有结果按照项目存入map\
		List<BisManageProjectVo> tmpList= null;
		for(BisManageProjectVo vo : list){
			tmpList = projectMap.get(vo.getProjectid());
			if(null==tmpList){
				tmpList = new ArrayList<BisManageProjectVo>();
			}
			tmpList.add(vo);
			projectMap.put(vo.getProjectid(), tmpList);
		}
		//Iterator it = projectMap.entrySet()
		BisManageVo projectList = null;
		for(BisManageProjectVo vo : list){
			allChargeCd.setMy(allChargeCd.getMy().add(vo.getMy()));
			allChargeCd.setFy(allChargeCd.getFy().add(vo.getFy()));
			allChargeCd.setMm(allChargeCd.getMm().add(vo.getMm()));
			allChargeCd.setFm(allChargeCd.getFm().add(vo.getFm()));
			allChargeCd.setRy(allChargeCd.getRy().add(vo.getRy()));
			allChargeCd.setRy(allChargeCd.getRy().add(vo.getRy()));
			//按照map，将相应费用类别放入指定位置
			if(null!=tableMap.get(vo.getCtc())) {
				tmp[tableMap.get(vo.getCtc())] = vo;
			}
			if(StringUtils.isBlank(allChargeCd.getProjectid())) {
				allChargeCd.setProjectid(vo.getProjectid());
			}
			//projectList = projectMap.get(vo.getProjectid());
			if(null==projectList){
				projectList = new BisManageVo();
			}
			projectList.getList().add(vo);
			//projectMap.put(vo.getCtc(), projectList);
		}
		tmp[8]=allChargeCd;
		for(int i = 0 ; i<tmp.length ; i++){
			BisManageProjectVo vo = tmp[i];
			if(vo==null){
				tmp[i]= new BisManageProjectVo();
			}
		}
		list.add(allChargeCd);
		result= Arrays.asList(tmp);
		return result;*/
	}
	/**
	 * 搜索执行项目经营情况报表支出汇总
	 */
	public List<BisManageProjectVo> getProjectOutDetail(String year,String month,String bisProjectIds){
		Map<String,Integer> tableMap = new HashMap<String, Integer>();
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_106, 9);//违约金
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_107, 10);//成本费用支出
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_108, 11);//广告宣传费
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_109, 12);//人工福利费
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_110, 13);//项目能源费
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_111, 14);//日常办公支出
		tableMap.put(BisManageProjectVo.			 BIS_CHARGE_TYPE_112, 15);//支出合计
		List<BisManageProjectVo> outlist = new LinkedList<BisManageProjectVo>();
		for(int i = 0 ; i<7 ; i++){
			BisManageProjectVo o = new BisManageProjectVo();
			outlist.add(o);
		}
		return outlist;
	}
	
	public List<BisReport> getReportData(String reportMonth, String statDimansion, String reportType, String bisProjectId) {
		
//		String hqlYear ="from BisReport where to_char(reportMonth, 'yyyy')=:reportYear and statDimansion=:statDimansion and reportType=:reportType and timeDimansion='1'";
		String hqlMonth ="from BisReport where to_char(reportMonth, 'yyyy-MM')=:reportMonth and statDimansion=:statDimansion and reportType=:reportType ";//and timeDimansion='2'
		if(StringUtils.isNotBlank(bisProjectId)) {
//			hqlYear  += " and bisProjectId=:bisProjectId";
			hqlMonth += " and bisProjectId=:bisProjectId";
		}
		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("reportYear", reportMonth.substring(0, 4));
		param.put("reportMonth", reportMonth);
		param.put("statDimansion", statDimansion);
		param.put("reportType", reportType);
		param.put("bisProjectId", bisProjectId);
//		List<BisReport> list = getDao().find(hqlYear, param);
		List<BisReport> list2 = getDao().find(hqlMonth, param);
//		list.addAll(list2);
		
		return list2;
	}
	
	//======================================= add by zhengyi 2012/6/7 =================================================//
	/**
	/**
	 *  查询预算表，找到本月预算数
	 * 
	 */
	
	public List<BisBudget> getBisBudgetByMonth(String reportMonth,String bisProjectId){
		
		String hqlMonth ="from BisBudget where to_char(month_str, 'yyyy-MM')=:reportMonth "; //查询指定月份的数据
		if(StringUtils.isNotBlank(bisProjectId)) {   //如果项目id部位空则查询时带上该条件
			hqlMonth += " and bisProjectId=:bisProjectId";
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("reportMonth", reportMonth);
		param.put("bisProjectId", bisProjectId);
		List<BisBudget> list = getDao().find(hqlMonth, param);
		return list;
		
	}
	
	/**
	 * 查询预算表，找到本年累计预算数
	 */
	public List<BisBudget> getBisBudgetCaculateByYear(String reportMonth,String bisProjectId){
		
    	
		String hqlMonth ="from BisBudget  where  where month_str<= :reportMonth"; //查询指定月份的数据
		if(StringUtils.isNotBlank(bisProjectId)) {   //如果项目id部位空则查询时带上该条件
			hqlMonth += " and bisProjectId=:bisProjectId";
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("reportMonth", reportMonth);
		param.put("bisProjectId", bisProjectId);
		List<BisBudget> list = getDao().find(hqlMonth, param); 
		return list;
	}
	
	/**
	 * 查询预算表，找到本年经营预算数
	 */
	public List<BisBudget> getBisBudgetByYear(String reportMonth,String bisProjectId){
		
		String regex = "-";
    	String params[] = reportMonth.split(regex);
    	String year = params[0];
		
		String hqlMonth ="from BisBudget  where substr(month_str,0,4) = :year "; //查询指定月份的数据
		if(StringUtils.isNotBlank(bisProjectId)) {   //如果项目id部位空则查询时带上该条件
			hqlMonth += " and bisProjectId=:bisProjectId";
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("year", year);
		param.put("bisProjectId", bisProjectId);
		List<BisBudget> list = getDao().find(hqlMonth, param); 
		return list;
	}
	
	
	/**
	 * 获得本月权责实收+本月回收欠款
	 */
    private List<Object[]> getNowMonthFact(String reportMonth,String bisProjectId){
    	
    	String regex = "-";
    	String params[] = reportMonth.split(regex);
    	
    	String year = params[0];
    	String month = params[1];
    	String sql = createSqlFactMonth(bisProjectId,year,month);
    	Map<String, Object> param = new HashMap<String, Object>();
    	List<Object[]> list = getDao().findBySql(sql, param);
    	return list;
    	
    }
    
   /**
    * 获得(本年权责实收+本年回收欠款)
    * @param reportMonth
    * @param bisProjectId
    * @return
    */
   private List<Object[]> getNowYearFact(String reportMonth,String bisProjectId){
    	
    	String regex = "-";
    	String params[] = reportMonth.split(regex);
    	
    	String year = params[0];
    	String sql = createSqlFactYear(year,bisProjectId);
    	Map<String, Object> param = new HashMap<String, Object>();
    	List<Object[]> list = getDao().findBySql(sql, param);
    	return list;
    	
    }
  
    /**
     * 查询实际收入表,获得（本月权责实收+本月回收欠款）的sql语句
     * @param bisProjectId
     * @param year
     * @param month
     * @return
     */
    private String createSqlFactMonth(String bisProjectId,String year,String month){
    	
    	StringBuffer sql = new StringBuffer();
    	Map<String,Object> param = new HashMap<String,Object>();
    	
    	//为条件赋值
    	param.put("bisProjectId", bisProjectId);
    	param.put("fact_year", year);
    	param.put("fact_month", month);
    	//本月的起止时间
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
    	String startDate = sdf.format(new Date());
    	String endDate = sdf.format(new Date());
		param.put("startDate", startDate);
		param.put("endDate", endDate);
    	
		sql.append("select f.charge_type_cd,sum(f.money) from bis_fact f");
    	sql.append("where fact_year<= :fact_year and fact_month <= :fact_month");
    	sql.append(" and to_char(fact_date,'yyyy/MM/dd') between :startDate and :endDate");
    	sql.append(" and f.bis_project_id = :bisProjectId");
    	sql.append("group by f.charge_type_cd");
    	
    	return sql.toString();
    }
    
    /**
     * 查询实际收入表,获得（本年权责实收+本年回收欠款）的sql语句
     * @param bisProjectId
     * @param year
     * @param month
     * @return
     */
    private String createSqlFactYear(String year,String bisProjectId){
    	StringBuffer sql = new StringBuffer();
    	Map<String,Object> param = new HashMap<String,Object>();
    	
    	//为条件赋值
    	param.put("bisProjectId", bisProjectId);
    	param.put("fact_year", year);
    	//本月的起止时间
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
    	String startDate = sdf.format(new Date());
    	String endDate = sdf.format(new Date());
		param.put("startDate", startDate);
		param.put("endDate", endDate);
    	
		sql.append("select f.charge_type_cd,sum(f.money) from bis_fact f");
    	sql.append("where fact_year <= :fact_year ");
    	sql.append(" and to_char(fact_date,'yyyy/MM/dd') between :startDate and :endDate");
    	sql.append(" and f.bis_project_id = :bisProjectId");
    	sql.append("group by f.charge_type_cd");
    	
    	return sql.toString();
    	
    }
    
	
	/**
	 * 获取MALL、步行街报表数据
	 */
	public List<BisStoreReportVo> getStoreReportData(String bisProjectId, String year, String month,
			String chargeTypeCd, String manageCd, String floorType) {
		
		Map<String, BisStoreReportVo> resultMap = new LinkedHashMap<String, BisStoreReportVo>();
		List<BisStoreReportVo> tenantUtils = new ArrayList<BisStoreReportVo>();

		Map<String, Object> param = new HashMap<String, Object>();
		String sql = buildStoreReportSql(bisProjectId, year, month, chargeTypeCd, manageCd, floorType, param);
		List<Object[]> list = getDao().findBySql(sql, param);
		
		for(Object[] obj : list) {
			
			String tenantId = (String) obj[0];
			if(resultMap.get(tenantId) != null){
				
				BisStoreReportVo bisStoreReportVo = resultMap.get(tenantId);
				BisReportTypeDetailVo detailVo = new BisReportTypeDetailVo();
				String chargeType = (String)obj[4];
				if(chargeType == null) {
					chargeType = "00";
				}
				detailVo.setChargeTypeCdRel(chargeType);
				detailVo.setYearMoneyRel((String)obj[5]);
				detailVo.setMonthMoneyRel((String)obj[6]);
				detailVo.setYearMoneySub((BigDecimal)obj[7]);
				detailVo.setMonthMoneySub((BigDecimal)obj[8]);
				detailVo.setBefYearMoney((BigDecimal)obj[9]);
				bisStoreReportVo.getDetailVoList().add(detailVo);
				
			}else{
				
				BisStoreReportVo bisStoreReportVo = new BisStoreReportVo();
				bisStoreReportVo.setTentantId(tenantId);
				bisStoreReportVo.setTentantName((String)obj[1]);
				bisStoreReportVo.setStoreName((String)obj[2]);
				bisStoreReportVo.setShopArea((BigDecimal)obj[3]);
				BisReportTypeDetailVo detailVo = new BisReportTypeDetailVo();
				String chargeType = (String)obj[4];
				if(chargeType == null) {
					chargeType = "00";
				}
				detailVo.setChargeTypeCdRel(chargeType);
				detailVo.setYearMoneyRel((String)obj[5]);
				detailVo.setMonthMoneyRel((String)obj[6]);
				detailVo.setYearMoneySub((BigDecimal)obj[7]);
				detailVo.setMonthMoneySub((BigDecimal)obj[8]);
				detailVo.setBefYearMoney((BigDecimal)obj[9]);
				bisStoreReportVo.getDetailVoList().add(detailVo);
				resultMap.put(tenantId, bisStoreReportVo);
			}
		}
		
		tenantUtils.addAll(resultMap.values());
		return tenantUtils;
	}

	private String buildStoreReportSql(String bisProjectId, String year, String month, String chargeTypeCd,
			String manageCd, String floorType, Map<String, Object> param) {
		
		param.put("bisProjectId", bisProjectId);
		param.put("year", year);
		param.put("yearInt", Integer.valueOf(year));
		param.put("month", Integer.valueOf(month).toString());
		param.put("monthInt", Integer.valueOf(month));
		param.put("chargeTypeCd", chargeTypeCd.split(","));
		param.put("manageCd", manageCd);
		param.put("floorType", floorType);
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select a.bis_tenant_id,a.name_cn,s.store_no,a.square,b.charge_type_cd,")
				.append(" (nvl(d.fact_year_money, 0) || '/' || nvl(b.must_year_money, 0)) as year_money_rel,")
				.append(" (nvl(e.fact_month_money, 0) || '/' || nvl(c.must_month_money, 0)) as month_money_rel,")
				.append(" (nvl(B.must_Year_Money, 0) - nvl(d.fact_year_money, 0)) as year_money_sub,")
				.append(" (nvl(c.must_Month_Money, 0) - nvl(e.fact_month_money, 0)) as month_money_sub,")
				.append(" (nvl(f.bef_must_year, 0) - nvl(g.bef_fact_year, 0)) as bef_year_money")
			.append(" from (")
				//租户主信息
				.append(" select a.bis_tenant_id, a.name_cn, sum(b.square) square")
				.append(" from (")
					.append(" select bt.bis_tenant_id, bs.name_cn, bsc.bis_store_id")
					.append(" from bis_tenant bt")
					.append(" join bis_shop bs on bt.bis_shop_id=bs.bis_shop_id")
					.append(" join bis_cont bc on bt.bis_tenant_id = bc.bis_tenant_id")
					.append(" join bis_store_cont_rel bsc on bc.bis_cont_id = bsc.bis_cont_id")
					.append(" where bt.bis_project_id=:bisProjectId");
					if(StringUtils.isNotBlank(manageCd)) {
						sql.append(" and bc.manage_cd=:manageCd");
					}
				sql .append(" group by bt.bis_tenant_id, bs.name_cn, bsc.bis_store_id")	
				.append(" ) a")
				.append(" join bis_store b on a.bis_store_id = b.bis_store_id")
				.append(" where b.status_cd='1'")
				.append(" and exists (select 1 from bis_floor f where b.bis_floor_id=f.bis_floor_id and f.floor_type='1' and f.sub_floor_type=:floorType)")
				.append(" group by a.bis_tenant_id, a.name_cn")
			.append(" ) a")
			.append(" left join (")
				//商铺信息
				.append(" select c.bis_tenant_id, TRANSLATE(LTRIM(c.text, '/'), '*/', '*,') store_no")
				.append(" from (")
				.append(" select ROW_NUMBER() OVER(PARTITION BY b.bis_tenant_id ORDER BY b.bis_tenant_id, num DESC) rn, b.bis_tenant_id, b.text")
				.append(" from (")
				.append(" select a.bis_tenant_id, level num, SYS_CONNECT_BY_PATH(a.store_no, '/') text")
				.append(" from (")
					.append(" select bt.bis_tenant_id, bs.store_no, ROW_NUMBER() OVER(PARTITION BY bt.bis_tenant_id ORDER BY bt.bis_tenant_id, bs.store_no) x")
					.append(" from bis_tenant bt")
					.append(" join bis_cont bc on bt.bis_tenant_id=bc.bis_tenant_id")
					.append(" join bis_store_cont_rel scr on bc.bis_cont_id=scr.bis_cont_id")
					.append(" join bis_store bs on scr.bis_store_id=bs.bis_store_id")
					.append(" where bt.bis_project_id=:bisProjectId");
					if(StringUtils.isNotBlank(manageCd)) {
						sql.append(" and bc.manage_cd=:manageCd");
					}
				sql .append(" and bs.status_cd='1'")
					.append(" and exists (select 1 from bis_floor f where bs.bis_floor_id=f.bis_floor_id and f.floor_type='1' and f.sub_floor_type=:floorType)")
					.append(" group by bt.bis_tenant_id, bs.store_no")
					.append(" order by bs.store_no")
				.append(" ) a")
				.append(" CONNECT BY a.bis_tenant_id = PRIOR a.bis_tenant_id AND a.x - 1 = PRIOR a.x ")
				.append(" ) b ) c ")
				.append(" where c.rn = 1")
			.append(" ) s on a.bis_tenant_id=s.bis_tenant_id")
			.append(" left join (")
				//本年应收
				.append(" select m.bis_tenant_id,m.charge_type_cd,TRUNC(sum(m.money),0) as must_year_money")
				.append(" from bis_must m")
				.append(" where m.must_year = :year")
				.append(" and m.must_month <= :month")
				.append(" and m.status_cd='2' ")
				.append(" and m.charge_type_cd in (:chargeTypeCd)")
				.append(" group by m.bis_tenant_id,m.charge_type_cd")
			.append(" ) b on a.bis_tenant_id=b.bis_tenant_id")
			.append(" left join (")
				//本月应收
				.append(" select m.bis_tenant_id,m.charge_type_cd,TRUNC(sum(m.money),0) as must_month_money")
				.append(" from bis_must m")
				.append(" where m.must_year = :year")
				.append(" and m.must_month = :month")
				.append(" and m.status_cd='2' ")
				.append(" and m.charge_type_cd in (:chargeTypeCd)")
				.append(" group by m.bis_tenant_id,m.charge_type_cd")
			.append(" ) c on b.bis_tenant_id=c.bis_tenant_id and b.charge_type_cd=c.charge_type_cd")
			.append(" left join (")
				//本年实收
				.append(" select f.bis_tenant_id,f.charge_type_cd,TRUNC(sum(f.money),0) as fact_year_money")
				.append(" from bis_fact f")
				.append(" where f.fact_year = :year")
				.append(" and f.fact_month <= :month") //TODO实收中有年为空的，所有暂时不用monthInt
				.append(" and f.status_cd='1' ")
				.append(" and f.charge_type_cd in (:chargeTypeCd)")
				.append(" group by f.bis_tenant_id,f.charge_type_cd")
			.append(" ) d on b.bis_tenant_id=d.bis_tenant_id and b.charge_type_cd=d.charge_type_cd")
			.append(" left join (")
				//本月实收
				.append(" select f.bis_tenant_id,f.charge_type_cd,TRUNC(sum(f.money),0) as fact_month_money")
				.append(" from bis_fact f")
				.append(" where f.fact_year = :year")
				.append(" and f.fact_month = :month") //TODO实收中有年为空的，所有暂时不用monthInt
				.append(" and f.status_cd='1' ")
				.append(" and f.charge_type_cd in (:chargeTypeCd)")
				.append(" group by f.bis_tenant_id,f.charge_type_cd")
			.append(" ) e on b.bis_tenant_id=e.bis_tenant_id and b.charge_type_cd=e.charge_type_cd")
			.append(" left join (")
				//以前年度应收情况
				.append(" select m.bis_tenant_id,m.charge_type_cd,TRUNC(sum(m.money),0) as bef_must_year")
				.append(" from bis_must m")
				.append(" where m.must_year < :yearInt")
				.append(" and m.status_cd='2' ")
				.append(" and m.charge_type_cd in (:chargeTypeCd)")
				.append(" group by m.bis_tenant_id,m.charge_type_cd")
			.append(" ) f on b.bis_tenant_id=f.bis_tenant_id and b.charge_type_cd=f.charge_type_cd")
			.append(" left join (")
				//以前年度实收情况
				.append(" select f.bis_tenant_id,f.charge_type_cd,TRUNC(sum(f.money),0) as bef_fact_year")
				.append(" from bis_fact f")
				.append(" where f.fact_year < :year") //TODO实收中有年为空的，所有暂时不用yearInt
				.append(" and f.status_cd='1' ")
				.append(" and f.charge_type_cd in (:chargeTypeCd)")
				.append(" group by f.bis_tenant_id,f.charge_type_cd")
			.append(" ) g on b.bis_tenant_id=g.bis_tenant_id and b.charge_type_cd=g.charge_type_cd")
			.append(" order by a.square desc");
		
		return sql.toString();
	}
	
	/**
	 * 获取公寓报表数据
	 */
	public List<BisFlatReportVo> getFlatReportData(String bisProjectId, String year, String month, String chargeTypeCd) {
		
		Map<String, BisFlatReportVo> resultMap = new LinkedHashMap<String, BisFlatReportVo>();
		List<BisFlatReportVo> tenantUtils = new ArrayList<BisFlatReportVo>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		String sql = buildFlatReportSql(bisProjectId, year, month, chargeTypeCd, param);
		List<Object[]> list = getDao().findBySql(sql, param);
		
		for(Object[] obj : list) {
			
			String flatId = (String) obj[0];
			if(resultMap.get(flatId) != null){
				
				BisFlatReportVo bisFlatReportVo = resultMap.get(flatId);
				BisReportTypeDetailVo detailVo = new BisReportTypeDetailVo();
				String chargeType = (String)obj[5];
				if(chargeType == null) {
					chargeType = "00";
				}
				detailVo.setChargeTypeCdRel(chargeType);
				detailVo.setYearMoneyRel((String)obj[6]);
				detailVo.setMonthMoneyRel((String)obj[7]);
				detailVo.setYearMoneySub((BigDecimal)obj[8]);
				detailVo.setMonthMoneySub((BigDecimal)obj[9]);
				detailVo.setBefYearMoney((BigDecimal)obj[10]);
				bisFlatReportVo.getDetailVoList().add(detailVo);
				
			}else{
				
				BisFlatReportVo bisFlatReportVo = new BisFlatReportVo();
				bisFlatReportVo.setBisFlatId(flatId);
				bisFlatReportVo.setFlatNo((String) obj[1]);
				bisFlatReportVo.setSquare((BigDecimal) obj[2]);
				bisFlatReportVo.setInnerSquare((BigDecimal)obj[3]);
				bisFlatReportVo.setPublicSquare((BigDecimal) obj[4]);
				BisReportTypeDetailVo detailVo = new BisReportTypeDetailVo();
				String chargeType = (String)obj[5];
				if(chargeType == null) {
					chargeType = "00";
				}
				detailVo.setChargeTypeCdRel(chargeType);
				detailVo.setYearMoneyRel((String)obj[6]);
				detailVo.setMonthMoneyRel((String)obj[7]);
				detailVo.setYearMoneySub((BigDecimal)obj[8]);
				detailVo.setMonthMoneySub((BigDecimal)obj[9]);
				detailVo.setBefYearMoney((BigDecimal)obj[10]);
				bisFlatReportVo.getDetailVoList().add(detailVo);
				resultMap.put(flatId, bisFlatReportVo);
			}
		}
		
		tenantUtils.addAll(resultMap.values());
		return tenantUtils;
	}
	
	private String buildFlatReportSql(String bisProjectId, String year, String month, String chargeTypeCd, 
			Map<String, Object> param) {
		
		param.put("bisProjectId", bisProjectId);
		param.put("year", year);
		param.put("yearInt", Integer.valueOf(year));
		param.put("month", Integer.valueOf(month).toString());
		param.put("monthInt", Integer.valueOf(month));
		param.put("chargeTypeCd", chargeTypeCd.split(","));
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select a.bis_flat_id,a.flat_no,a.square,a.inner_square,a.public_square,b.charge_type_cd,")
				.append(" (nvl(d.fact_year_money, 0) || '/' || nvl(b.must_year_money, 0)) as year_money_rel,")
				.append(" (nvl(e.fact_month_money, 0) || '/' || nvl(c.must_month_money, 0)) as month_money_rel,")
				.append(" (nvl(B.must_Year_Money, 0) - nvl(d.fact_year_money, 0)) as year_money_sub,")
				.append(" (nvl(c.must_Month_Money, 0) - nvl(e.fact_month_money, 0)) as month_money_sub,")
				.append(" (nvl(f.bef_must_year, 0) - nvl(g.bef_fact_year, 0)) as bef_year_money")
			.append(" from bis_flat a")
			.append(" left join (")
				//本年应收
				.append(" select m.bis_flat_id, m.charge_type_cd, sum(m.money) as must_year_money")
				.append(" from bis_must m")
				.append(" where m.must_year = :year")
				.append(" and m.must_month <= :month") //TODO实收中有年为空的，所有暂时不用monthInt
				.append(" and m.status_cd='2' ")
				.append(" and m.charge_type_cd in (:chargeTypeCd)")
				.append(" and m.bis_flat_id is not null")
				.append(" group by m.bis_flat_id, m.charge_type_cd")
				.append(" ) b on a.bis_flat_id=b.bis_flat_id")
			.append(" left join (")
				//本月应收
				.append(" select m.bis_flat_id,m.charge_type_cd,sum(m.money) as must_month_money")
				.append(" from bis_must m")
				.append(" where m.must_year = :year")
				.append(" and m.must_month = :month") //TODO实收中有年为空的，所有暂时不用monthInt
				.append(" and m.status_cd='2' ")
				.append(" and m.charge_type_cd in (:chargeTypeCd)")
				.append(" and m.bis_flat_id is not null")
				.append(" group by m.bis_flat_id,m.charge_type_cd")
				.append(" ) c on b.bis_flat_id=c.bis_flat_id and b.charge_type_cd=c.charge_type_cd")
			.append(" left join (")
				//本年实收
				.append(" select f.bis_flat_id,f.charge_type_cd,sum(f.money) as fact_year_money")
				.append(" from bis_fact f")
				.append(" where f.fact_year = :year")
				.append(" and f.fact_month <= :month")
				.append(" and f.status_cd='1' ")
				.append(" and f.charge_type_cd in (:chargeTypeCd)")
				.append(" and f.bis_flat_id is not null")
				.append(" group by f.bis_flat_id,f.charge_type_cd")
				.append(" ) d on b.bis_flat_id=d.bis_flat_id and b.charge_type_cd=d.charge_type_cd")
			.append(" left join (")
				//本月实收
				.append(" select f.bis_flat_id,f.charge_type_cd,sum(f.money) as fact_month_money")
				.append(" from bis_fact f")
				.append(" where f.fact_year = :year")
				.append(" and f.fact_month = :month")
				.append(" and f.status_cd='1' ")
				.append(" and f.charge_type_cd in (:chargeTypeCd)")
				.append(" and f.bis_flat_id is not null")
				.append(" group by f.bis_flat_id,f.charge_type_cd")
				.append(" ) e on b.bis_flat_id=e.bis_flat_id and b.charge_type_cd=e.charge_type_cd")
			.append(" left join (")
				//以前年度应收情况
				.append(" select m.bis_flat_id,m.charge_type_cd,sum(m.money) as bef_must_year")
				.append(" from bis_must m")
				.append(" where m.must_year < :yearInt")
				.append(" and m.status_cd='2' ")
				.append(" and m.charge_type_cd in (:chargeTypeCd)")
				.append(" and m.bis_flat_id is not null")
				.append(" group by m.bis_flat_id,m.charge_type_cd")
				.append(" ) f on b.bis_flat_id=f.bis_flat_id and b.charge_type_cd=f.charge_type_cd")
			.append(" left join (")
				//以前年度实收情况
				.append(" select f.bis_flat_id,f.charge_type_cd,sum(f.money) as bef_fact_year")
				.append(" from bis_fact f")
				.append(" where f.fact_year < :year") //TODO实收中有年为空的，所有暂时不用yearInt
				.append(" and f.status_cd='1' ")
				.append(" and f.charge_type_cd in (:chargeTypeCd)")
				.append(" and f.bis_flat_id is not null")
				.append(" group by f.bis_flat_id,f.charge_type_cd")
				.append(" ) g on b.bis_flat_id=g.bis_flat_id and b.charge_type_cd=g.charge_type_cd")
			.append(" where a.bis_project_id = :bisProjectId")
			//排序
			.append(" order by a.flat_no");

		return sql.toString();
	}
	
	/**
	 * 获取多经报表数据
	 */
	public List<BisMultiReportVo> getMultiReportData(String bisProjectId, String year, String month, String chargeTypeCd) {
		
		Map<String, BisMultiReportVo> resultMap = new LinkedHashMap<String, BisMultiReportVo>();
		List<BisMultiReportVo> resultList = new ArrayList<BisMultiReportVo>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		String sql = buildMultiReportSql(bisProjectId, year, month, chargeTypeCd, param);
		List<Object[]> list = getDao().findBySql(sql, param);
		
		for(Object[] obj : list) {
			
			String multi = (String) obj[0];
			if(resultMap.get(multi) != null){
				
				BisMultiReportVo bisMultiReportVo = resultMap.get(multi);
				BisReportTypeDetailVo detailVo = new BisReportTypeDetailVo();
				String chargeType = (String)obj[4];
				if(chargeType == null) {
					chargeType = "00";
				}
				detailVo.setChargeTypeCdRel(chargeType);
				detailVo.setYearMoneyRel((String)obj[5]);
				detailVo.setMonthMoneyRel((String)obj[6]);
				detailVo.setYearMoneySub((BigDecimal)obj[7]);
				detailVo.setMonthMoneySub((BigDecimal)obj[8]);
				detailVo.setBefYearMoney((BigDecimal)obj[9]);
				bisMultiReportVo.getDetailVoList().add(detailVo);
				
			}else{
				
				BisMultiReportVo bisMultiReportVo = new BisMultiReportVo();
				bisMultiReportVo.setBisMultiId(multi);
				bisMultiReportVo.setMultiName((String) obj[1]);
				bisMultiReportVo.setOperationArea((String) obj[2]);
				bisMultiReportVo.setSquare((BigDecimal) obj[3]);
				BisReportTypeDetailVo detailVo = new BisReportTypeDetailVo();
				String chargeType = (String)obj[4];
				if(chargeType == null) {
					chargeType = "00";
				}
				detailVo.setChargeTypeCdRel(chargeType);
				detailVo.setYearMoneyRel((String)obj[5]);
				detailVo.setMonthMoneyRel((String)obj[6]);
				detailVo.setYearMoneySub((BigDecimal)obj[7]);
				detailVo.setMonthMoneySub((BigDecimal)obj[8]);
				detailVo.setBefYearMoney((BigDecimal)obj[9]);
				bisMultiReportVo.getDetailVoList().add(detailVo);
				resultMap.put(multi, bisMultiReportVo);
			}
		}
		
		resultList.addAll(resultMap.values());
		return resultList;
	}
	
	private String buildMultiReportSql(String bisProjectId, String year, String month, String chargeTypeCd, 
			Map<String, Object> param) {
		
		param.put("bisProjectId", bisProjectId);
		param.put("year", year);
		param.put("yearInt", Integer.valueOf(year));
		param.put("month", Integer.valueOf(month).toString());
		param.put("monthInt", Integer.valueOf(month));
		param.put("chargeTypeCd", chargeTypeCd.split(","));
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select a.bis_multi_id,a.multi_name,a.operation_area,a.square,b.charge_type_cd,")
				.append(" (nvl(d.fact_year_money, 0) || '/' || nvl(b.must_year_money, 0)) as year_money_rel,")
				.append(" (nvl(e.fact_month_money, 0) || '/' || nvl(c.must_month_money, 0)) as month_money_rel,")
				.append(" (nvl(B.must_Year_Money, 0) - nvl(d.fact_year_money, 0)) as year_money_sub,")
				.append(" (nvl(c.must_Month_Money, 0) - nvl(e.fact_month_money, 0)) as month_money_sub,")
				.append(" (nvl(f.bef_must_year, 0) - nvl(g.bef_fact_year, 0)) as bef_year_money")
			.append(" from bis_multi a")
			.append(" left join (")
				//本年应收
				.append(" select m.bis_multi_id, m.charge_type_cd, sum(m.money) as must_year_money")
				.append(" from bis_must m")
				.append(" where m.must_year = :year")
				.append(" and m.must_month <= :month")
				.append(" and m.status_cd='2' ")
				.append(" and m.charge_type_cd in (:chargeTypeCd)")
				.append(" and m.bis_multi_id is not null")
				.append(" group by m.bis_multi_id, m.charge_type_cd")
				.append(" ) b on a.bis_multi_id=b.bis_multi_id")
			.append(" left join (")
				//本月应收
				.append(" select m.bis_multi_id,m.charge_type_cd,sum(m.money) as must_month_money")
				.append(" from bis_must m")
				.append(" where m.must_year = :year")
				.append(" and m.must_month = :month")
				.append(" and m.status_cd='2' ")
				.append(" and m.charge_type_cd in (:chargeTypeCd)")
				.append(" and m.bis_multi_id is not null")
				.append(" group by m.bis_multi_id,m.charge_type_cd")
				.append(" ) c on b.bis_multi_id=c.bis_multi_id and b.charge_type_cd=c.charge_type_cd")
			.append(" left join (")
				//本年实收
				.append(" select f.bis_multi_id,f.charge_type_cd,sum(f.money) as fact_year_money")
				.append(" from bis_fact f")
				.append(" where f.fact_year = :year")
				.append(" and f.fact_month <= :month") //TODO实收中有年为空的，所有暂时不用monthInt
				.append(" and f.status_cd='1' ")
				.append(" and f.charge_type_cd in (:chargeTypeCd)")
				.append(" and f.bis_multi_id is not null")
				.append(" group by f.bis_multi_id,f.charge_type_cd")
				.append(" ) d on b.bis_multi_id=d.bis_multi_id and b.charge_type_cd=d.charge_type_cd")
			.append(" left join (")
				//本月实收
				.append(" select f.bis_multi_id,f.charge_type_cd,sum(f.money) as fact_month_money")
				.append(" from bis_fact f")
				.append(" where f.fact_year = :year")
				.append(" and f.fact_month = :month") //TODO实收中有年为空的，所有暂时不用monthInt
				.append(" and f.status_cd='1' ")
				.append(" and f.charge_type_cd in (:chargeTypeCd)")
				.append(" and f.bis_multi_id is not null")
				.append(" group by f.bis_multi_id,f.charge_type_cd")
				.append(" ) e on b.bis_multi_id=e.bis_multi_id and b.charge_type_cd=e.charge_type_cd")
			.append(" left join (")
				//以前年度应收情况
				.append(" select m.bis_multi_id,m.charge_type_cd,sum(m.money) as bef_must_year")
				.append(" from bis_must m")
				.append(" where m.must_year < :yearInt")
				.append(" and m.status_cd='2' ")
				.append(" and m.charge_type_cd in (:chargeTypeCd)")
				.append(" and m.bis_multi_id is not null")
				.append(" group by m.bis_multi_id,m.charge_type_cd")
				.append(" ) f on b.bis_multi_id=f.bis_multi_id and b.charge_type_cd=f.charge_type_cd")
			.append(" left join (")
				//以前年度实收情况
				.append(" select f.bis_multi_id,f.charge_type_cd,sum(f.money) as bef_fact_year")
				.append(" from bis_fact f")
				.append(" where f.fact_year < :year") //TODO实收中有年为空的，所有暂时不用yearInt
				.append(" and f.status_cd='1' ")
				.append(" and f.charge_type_cd in (:chargeTypeCd)")
				.append(" and f.bis_multi_id is not null")
				.append(" group by f.bis_multi_id,f.charge_type_cd")
				.append(" ) g on b.bis_multi_id=g.bis_multi_id and b.charge_type_cd=g.charge_type_cd")
			.append(" where a.bis_project_id = :bisProjectId")
			//排序
			.append(" order by a.multi_name");
		
		return sql.toString();
	}
	
	/**
	 * 搜索所有的项目
	 * @return
	 */
	public List<BisProject> findAllProject(){
		String hql=" from BisProject bs ";
		Map<String,Object> values=new HashMap();
		return bisProjectDao.find(hql, values);
	}
	
	/**
	 * 根据部门cd搜索
	 * @param orgCd
	 * @return
	 */
	public BisProject getBisProjectByOrgCd(String orgCd) {
		BisProject bisProject = null;
		Map<String, Object> param = new HashMap<String, Object>();
		String hql = "from BisProject where orgCd =:orgCd order by sequenceNo ";
		param.put("orgCd", orgCd);
		List<BisProject> list = bisProjectDao.find(hql, param);
		for (int i = 0; i < list.size(); i++) {
			bisProject = list.get(0);
		}
		return bisProject;
	}
	
	
	/**
	 * 根据部门cd搜索
	 * @param orgCd
	 * @return
	 */
	public BisProject getBisProjectByProjId(String projId) {
		BisProject bisProject = null;
		Map<String, Object> param = new HashMap<String, Object>();
		String hql = "from BisProject where bisProjectId =:bisProjectId order by sequenceNo ";
		param.put("bisProjectId", projId);
		List<BisProject> list = bisProjectDao.find(hql, param);
		for (int i = 0; i < list.size(); i++) {
			bisProject = list.get(0);
		}
		return bisProject;
	}
	

	/***
	 * 判断是否是总部公司人登陆
	 * @return 总部的人就是true
	 */
	public boolean ifHeadquartered(){
		String hql = " from BisProject where orgCd in(:currOrgCd) and orgCd <> 153 ";
		String currCentCd = SpringSecurityUtils.getCurrentCenterCd();
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("currOrgCd", currCentCd);
		List<Object> list = bisProjectDao.find(hql,param);
		return list.isEmpty();
	}
	/**
	 * 根据orgCd过滤
	 * @param cdList
	 * @return
	 */
	public List<BisProject> filterOrgCd153(){
		String hql = " from BisProject where orgCd <> 153";
		Map<String, Object> param = new HashMap<String, Object>();
		return bisProjectDao.find(hql,param);
	}
	
}

