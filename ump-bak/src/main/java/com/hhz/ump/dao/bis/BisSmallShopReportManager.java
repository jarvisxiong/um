package com.hhz.ump.dao.bis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisSmallShopReport;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.web.bis.vo.VoBisSmallShopReport;

@Service
@Transactional
public class BisSmallShopReportManager extends BaseService<BisSmallShopReport, String> {
	@Autowired
	private BisSmallShopReportDao bisSmallShopReportDao;
	@Autowired
	private BisProjectDao bisProjectDao;
	SimpleDateFormat yyyy =new SimpleDateFormat("yyyy");	
	
	public void saveBisSmallShopReport(BisSmallShopReport bisSmallShopReport) {
		PowerUtils.setEmptyStr2Null(bisSmallShopReport);
		bisSmallShopReportDao.save(bisSmallShopReport);
	}

	public void deleteBisSmallShopReport(String id) {
		bisSmallShopReportDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisSmallShopReport, String> getDao() {
		return bisSmallShopReportDao;
	}
	/**
	 * 获取项目列表
	 * 新需求(2012-6-18)6中项目类型
	 * @param projectIds
	 * @return
	 */
	public List<BisProject> getBisProjectsBeta(String projectIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		//用sql拼接查询
		StringBuffer sql = new StringBuffer("");
		sql.append("select t1.bis_project_id,t2.item_category_id,t2.category_name,t1.city,t1.project_name,t1.open_date,t2.order_num")
        .append(" from bis_item_category t2")
        .append(" left join bis_project t1")
        .append(" on t1.bis_project_id = t2.bis_project_id ");
		if(StringUtils.isNotBlank(projectIds)) {
			sql.append(" and  bis_project_id in (:pIds)");
			param.put("pIds", projectIds.split(","));
		}
		List<Object[]> bisProjects = this.findBySql(sql.toString(), param);
		List<BisProject> rest = new ArrayList<BisProject>();
		//遍历集合查询对象
		if(bisProjects!=null&&!bisProjects.isEmpty()){
			for (int i = 0; i < bisProjects.size(); i++) {
				BisProject bp = new BisProject();
				Object[] obj = bisProjects.get(i);
				bp.setBisProjectId(obj[0].toString());
				bp.setProjectName(obj[4].toString());
				bp.setCity(obj[3].toString());
				rest.add(bp);
			}
		}
		return rest;
	}
	/**
	 * 获取项目列表
	 * @return
	 */
	public List<BisProject> getBisProjects(String projectIds) {
 		Map<String, Object> param = new HashMap<String, Object>();
		
		StringBuffer hql = new StringBuffer("from BisProject where openDate is not null and businessStreet = 1 or smallShopReport =1");
		if(StringUtils.isNotBlank(projectIds)) {
			hql.append(" and  bisProjectId in (:pIds)");
			param.put("pIds", projectIds.split(","));
		}
		hql.append(" order by projectFlg asc openDate asc sequenceNo desc ");
		
		return bisProjectDao.find(hql.toString(),param);
	}
	/**
	 * 获取项目列表
	 * @return
	 */
	public List<BisProject> getAllBisProjects() {
		Map<String, Object> param = new HashMap<String, Object>();
		
		StringBuffer hql = new StringBuffer("from BisProject where openDate is not null ");
		hql.append(" order by projectFlg asc openDate asc sequenceNo desc ");
		
		return bisProjectDao.find(hql.toString(),param);
	}
	/***
	 * 根据条件搜索对单个对象 
	 * @param reportId
	 * @param proId
	 * @param flag
	 * @return
	 */
	@Override
	public BisSmallShopReport getEntity(String reportId){
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = " from BisSmallShopReport t where t.bisSmallShopReportId = :bisSmallShopReportId";
		map.put("bisSmallShopReportId", reportId);
		List<BisSmallShopReport> tlist = bisSmallShopReportDao.find(hql, map);
		if(tlist!=null&&!tlist.isEmpty())
			return tlist.get(0);
		return null;
	}
	/**
	 * 项目报表审核人+审核时间
	 * @return
	 */
	public String getProjectFecher(){
		StringBuffer hql = new StringBuffer(" from BisSmallShopReport where smallShopStatus = 2 ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startDay", DateUtil.getFirstDayOfPreWeek());
		params.put("endDay", DateUtil.getLastDayOfPreWeek());
		hql.append("and startDay =:startDay and endDay =:endDay");
		List<BisSmallShopReport> projects = bisSmallShopReportDao.find(hql.toString(),params);
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
			StringBuffer hql = new StringBuffer(" from BisSmallShopReport where smallShopStatus = 2 ");
			hql.append("and startDay =:startDay and endDay =:endDay");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("startDay", startTime);
			params.put("endDay", endTime);
			List<BisSmallShopReport> projects = bisSmallShopReportDao.find(hql.toString(),params);
			if(projects.isEmpty()||projects.size()<1)
				return "";
			else{
				String str = CodeNameUtil.getUserNameByCd(projects.get(0).getUpdator())+"  "+DateUtil.parseDateToString(projects.get(0).getUpdatedDate());
				return str;	
			}
		}
	}
	/**
	 * 根据条件搜索集合
	 * @param param
	 * param->startDay开始时间
	 * param->endDay 结束时间
	 * param->projects项目集合
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BisSmallShopReport> listBisSmallShopReport(Map<String, Object> param) {
		List<BisSmallShopReport> rtnList = new ArrayList<BisSmallShopReport>();
		if(param.isEmpty())
			return rtnList;
		else {
			StringBuffer hql = new StringBuffer(" from BisSmallShopReport where 1=1 ");
			hql.append("and startDay =:startDay and endDay =:endDay");
			rtnList = bisSmallShopReportDao.find(hql.toString(),param);
		}
		return rtnList;
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
			StringBuffer hql = new StringBuffer(" from BisSmallShopReport where 1=1 ");
			hql.append("and startDay =:startDay and endDay =:endDay");
			List<BisSmallShopReport> projects = bisSmallShopReportDao.find(hql.toString(),params);
			for (BisSmallShopReport report : projects) {
				bisProcIds.append(report.getBisProjectId()).append(",");
			}
			return bisProcIds.toString();
		}
	}

	/**
	 * 查询小商铺周报列表
	 * @param startDay 开始时间
	 * @param endDay 结束时间
	 * @param flag 是否左外连接(左外连接会将没有的也查出来)<值=1开启左外连>
	 * @author Cp
	 * @return
	 */
	public List<VoBisSmallShopReport> getReportVoList(String startDay,String endDay,int flag) {

		List<VoBisSmallShopReport> rtnList = new ArrayList<VoBisSmallShopReport>();
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("startDay", startDay);
		values.put("endDay", endDay);
		StringBuffer sb = new StringBuffer();
		sb.append("select t.bis_project_id,t.item_category_id,t.category_name,t.city,t.project_name,t.order_num,")
		.append("       t3.bis_small_shop_report_id,")
		.append("       t3.merchants_area_2012,")
		.append("       t3.merchants_area,")
		.append("       t3.contract_area_new,")
		.append("       t3.contract_area,")
		.append("       t3.signing_rate,")
		.append("       t3.plan_signing_rate,")
		.append("       t3.opened_rate_shop,")
		.append("       t3.opened_rate,")
		.append("       t3.investment_area,")
		.append("       t3.remark,")
		.append("       t3.header_sign,")
		.append("       t3.small_shop_status")
		.append("  from (select t1.bis_project_id,")
		.append("               t2.item_category_id,")
		.append("               t2.category_name,")
		.append("               t1.city,")
		.append("               t1.project_name,")
		.append("               t2.order_num")
		.append("          from bis_item_category t2")
		.append("          left join bis_project t1")
		.append("            on t1.bis_project_id = t2.bis_project_id) t");
		if(flag==1){//左外连
			sb.append("  left join bis_small_shop_report t3 ")
			.append("  on t.bis_project_id = t3.bis_project_id");
		}else{//内联方式
			sb.append("         ,bis_small_shop_report t3")
			.append(" where t.bis_project_id = t3.bis_project_id");
		}
		sb.append("  and t.item_category_id = t3.bis_item_category_id")
		.append("  and t3.start_day =:startDay and t3.end_day = :endDay ")
		.append("  order by t.order_num");
		
		List<Object[]> list = this.findBySql(sb.toString(), values);
		if(list == null || list.isEmpty())
			return  rtnList;
		else{
			Object[] tmp = null;
			VoBisSmallShopReport vo = null;
			for(int i=0; i<list.size(); i++){
				tmp = list.get(i);
				vo = new VoBisSmallShopReport();
				vo.setBisProjectId(convStr(tmp[0]));
				vo.setBisItemCategoryId(convStr(tmp[1]));
				vo.setBisItemCategoryName(convStr(tmp[2]));
				vo.setCity(convStr(tmp[3]));
				vo.setProjectName(convStr(tmp[4]));
				//tmp[5]排序号
				vo.setBisSmallShopReportId(convStr(tmp[6]));
				vo.setMerchantsArea2012(convStr(tmp[7]));
				vo.setMerchantsArea(convStr(tmp[8]));
				vo.setContractAreaNew(convStr(tmp[9]));
				vo.setContractArea(convStr(tmp[10]));
				vo.setSigningRate(convStr(tmp[11]));
				vo.setPlanSigningRate(convStr(tmp[12]));
				vo.setOpenedRateShop(convStr(tmp[13]));
				vo.setOpenedRate(convStr(tmp[14]));
				vo.setInvestmentArea(convStr(tmp[15]));
				vo.setRemark(convStr(tmp[16]));
				vo.setHeaderSign(convStr(tmp[17]));
				vo.setSmallShopStatus(convStr(tmp[18]));
				vo.setStartDay(startDay);
				vo.setEndDay(endDay);
				rtnList.add(vo);
			}
			return rtnList;
		}
	}
	private String convStr(Object obj){
		if(obj==null)
			return "";
		else
			return obj.toString();
	}
}

