package com.hhz.ump.dao.bis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisFlat;
import com.hhz.ump.entity.bis.BisFloor;
import com.hhz.ump.util.BisFlatReportUtil;
import com.hhz.ump.util.TenantTypeReportUtil;
import com.hhz.ump.web.vo.VoFlatCount;

@Service
@Transactional
public class BisFlatManager extends BaseService<BisFlat, String> {
	@Autowired
	private BisFlatDao bisFlatDao;
	
	public void saveBisFlat(BisFlat bisFlat) {
		PowerUtils.setEmptyStr2Null(bisFlat);
		bisFlatDao.save(bisFlat);
	}

	public void deleteBisFlat(String id) {
		bisFlatDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisFlat, String> getDao() {
		return bisFlatDao;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<BisFlat> getBisFlatList(String bisProjectId,String floorId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from BisFlat f   ").append(" where f.bisProjectId=?");
		if(StringUtils.isNotBlank(floorId)){
			hql.append(" and f.bisFloor.bisFloorId='"+floorId+"'");
		}
		hql.append(" order by f.bisFloor.buildingNum,f.bisFloor.floorNum,f.flatNo")	
		;
		List<BisFlat> result = this.getDao().createQuery(hql.toString(),
				bisProjectId).list();
		
		if (result == null)
			return new ArrayList<BisFlat>();
		else
			return result;
	}
	/**
	 * 搜索没有合同的公寓
	 */
	public List<BisFlat> getFlatNotCont(String bisProjectId,String floorId){
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select f from BisFlat f   ").append(" where f.bisProjectId=:bisProjectId");
		if(StringUtils.isNotBlank(floorId)){
			hql.append(" and f.bisFloor.bisFloorId=:floorId");
			param.put("floorId", floorId);
		}
		hql.append(" and not exists(select 1 from BisFlatContRel r where r.bisFlat.bisFlatId=f.bisFlatId)");
		hql.append(" order by f.bisFloor.buildingNum,f.bisFloor.floorNum,f.flatNo");
		param.put("bisProjectId", bisProjectId);
		List<BisFlat> flatList =this.find(hql.toString(),param);
		return flatList;
	}
	/**
	 */
	public List<BisFloor> getBisFloor(String bisProjectId){
		StringBuffer hql = new StringBuffer();
		hql.append("from BisFloor f   ") 
		.append(" where f.bisProject.bisProjectId=? and f.floorType='2'")		//2为公寓
		.append(" order by f.floorNum ");
		List<BisFloor> result =this.getDao().createQuery(hql.toString(), bisProjectId).list();
		if (result == null)
			return new ArrayList<BisFloor>();
		else
			return result;
	}
	
	public List<BisFlatReportUtil> queryFlatReport(String bisProjectId,String mustYear,int mustMonth,String factYear,int factMonth,String chargeTypeCd){
		StringBuffer sql =new StringBuffer("");
		Map<String, Object> params = new HashMap<String, Object>();
		//搜索结果内容：租户ID,租户编号、面积、室内面积、年实收/应收比较值、月实收/应收比较值、年欠费值、月欠费值、
		//以前年度欠费、费用类别
		sql.append("select A.BIS_FLAT_ID,a.FLAT_NO,a.square,A.INNER_SQUARE,A.PUBLIC_SQUARE," +
				"(nvl(d.fact_year_money,0)||'/'||nvl(B.must_Year_Money,0)) as year_money_rel," +
				"(nvl(e.fact_month_money,0)||'/'||nvl(c.must_Month_Money,0)) as month_money_rel," +
				"(nvl(B.must_Year_Money, 0) - nvl(d.fact_year_money, 0))  as year_money_sub," +
				"(nvl(c.must_Month_Money, 0) - nvl(e.fact_month_money, 0))  as month_money_sub," +
				"(nvl(f.bef_must_year, 0) - nvl(g.bef_fact_year, 0))  as bef_year_money," +
				"b.CHARGE_TYPE_CD as charge_type_cd from(");
		sql.append(" select FLAT.BIS_FLAT_ID,FLAT.FLAT_NO,FLAT.SQUARE,FLAT.INNER_SQUARE," +
				"FLAT.PUBLIC_SQUARE from BIS_FLAT flat where 1=1 ");
		if(StringUtils.isNotBlank(bisProjectId)){
			sql.append("AND FLAT.BIS_PROJECT_ID='").append(bisProjectId).append("'");
		}
		sql.append(" ) a left join(");
		//本年应收
		sql.append(" select FLAT.BIS_FLAT_ID,sum(MUST.MONEY) as must_Year_Money,MUST.CHARGE_TYPE_CD" +
				" from bis_flat flat,bis_flat_cont_rel rel,bis_must must" +
				" where FLAT.BIS_FLAT_ID=REL.BIS_FLAT_ID and REL.BIS_CONT_ID =MUST.BIS_CONT_ID");
		if(StringUtils.isNotBlank(mustYear)){
			sql.append(" and MUST.MUST_YEAR='").append(mustYear).append("'");
		}
		if(mustMonth!=0){
			sql.append(" and MUST.MUST_MONTH<").append(mustMonth);
		}
		if(StringUtils.isNotBlank(chargeTypeCd)){
			sql.append(" and MUST.CHARGE_TYPE_CD IN(").append(chargeTypeCd).append(")");
		}
		sql.append(" group by flat.bis_flat_id,MUST.CHARGE_TYPE_CD)b");
		sql.append(" on b.bis_flat_id=a.BIS_FLAT_ID left join(");
		//本月应收
		sql.append("select FLAT.BIS_FLAT_ID,sum(MUST.MONEY) AS must_month_money,MUST.CHARGE_TYPE_CD" +
				" from bis_flat flat,bis_flat_cont_rel rel,bis_must must " +
				" where FLAT.BIS_FLAT_ID=REL.BIS_FLAT_ID and REL.BIS_CONT_ID =MUST.BIS_CONT_ID");
		if(StringUtils.isNotBlank(mustYear)){
			sql.append(" and MUST.MUST_YEAR='").append(mustYear).append("'");
		}
		if(mustMonth!=0){
			sql.append(" and MUST.MUST_MONTH=").append(mustMonth);
		}
		if(StringUtils.isNotBlank(chargeTypeCd)){
			sql.append(" and MUST.CHARGE_TYPE_CD IN(").append(chargeTypeCd).append(")");
		}
		sql.append(" group by flat.bis_flat_id,MUST.CHARGE_TYPE_CD");
		sql.append(")c on a.BIS_FLAT_ID=c.bis_flat_id left join(");
		//本年实收
		sql.append("select FACT.BIS_FLAT_ID,sum(FACT.MONEY) as fact_Year_Money,FACT.CHARGE_TYPE_CD" +
				" from bis_fact fact WHERE 1=1 ");
		if(StringUtils.isNotBlank(factYear)){
			sql.append(" and FACT.FACT_YEAR='").append(factYear).append("'");
		}
		if(factMonth!=0){
			sql.append(" and FACT.FACT_MONTH<").append(factMonth);
		}
		if(StringUtils.isNotBlank(chargeTypeCd)){
			sql.append(" and FACT.CHARGE_TYPE_CD IN(").append(chargeTypeCd).append(")");
		}
		sql.append(" GROUP BY FACT.BIS_FLAT_ID,FACT.CHARGE_TYPE_CD ) d");
		sql.append(" on a.bis_flat_id =d.bis_flat_id left join(");
		//本月实收
		sql.append("select FACT.BIS_FLAT_ID,sum(FACT.MONEY) as fact_month_money,FACT.CHARGE_TYPE_CD" +
				" from bis_fact fact WHERE 1=1");
		if(StringUtils.isNotBlank(factYear)){
			sql.append(" and FACT.FACT_YEAR='").append(factYear).append("'");
		}
		if(factMonth!=0){
			sql.append(" and FACT.FACT_MONTH=").append(factMonth);
		}
		if(StringUtils.isNotBlank(chargeTypeCd)){
			sql.append(" and FACT.CHARGE_TYPE_CD IN(").append(chargeTypeCd).append(")");
		}
		sql.append(" GROUP BY FACT.BIS_FLAT_ID,FACT.CHARGE_TYPE_CD ) e ");
		sql.append(" on a.bis_flat_id =e.bis_flat_id left join(");
		//以前年度应收
		sql.append("select FLAT.BIS_FLAT_ID,sum(MUST.MONEY) as bef_must_year,MUST.CHARGE_TYPE_CD" +
				" from bis_flat flat,bis_flat_cont_rel rel,bis_must must where" +
				" FLAT.BIS_FLAT_ID=REL.BIS_FLAT_ID and REL.BIS_CONT_ID =MUST.BIS_CONT_ID ");
		if(StringUtils.isNotBlank(mustYear)){
			sql.append(" and MUST.MUST_YEAR<'").append(mustYear).append("'");
		}
		if(StringUtils.isNotBlank(chargeTypeCd)){
			sql.append(" and MUST.CHARGE_TYPE_CD IN(").append(chargeTypeCd).append(")");
		}
		sql.append(" group by flat.bis_flat_id,MUST.CHARGE_TYPE_CD");
		sql.append(") f on f.bis_flat_id =a.bis_flat_id left join(");
		//以前年度实收
		sql.append("select FACT.BIS_FLAT_ID,sum(FACT.MONEY) as bef_fact_year,FACT.CHARGE_TYPE_CD" +
				" from bis_fact fact WHERE 1=1");
		if(StringUtils.isNotBlank(factYear)){
			sql.append(" and FACT.FACT_YEAR='").append(factYear).append("'");
		}
		if(StringUtils.isNotBlank(chargeTypeCd)){
			sql.append(" and FACT.CHARGE_TYPE_CD IN(").append(chargeTypeCd).append(")");
		}
		sql.append(" GROUP BY FACT.BIS_FLAT_ID,FACT.CHARGE_TYPE_CD");
		sql.append(") g on g.bis_flat_id =a.bis_flat_id ");
		String flatId="";
		List<BisFlatReportUtil> flatUtils =new ArrayList<BisFlatReportUtil>();
		List list =bisFlatDao.findBySql(sql.toString(), params);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				if(flatId.equals(obj[0])){
					//若没新增租户则新增一条经营类型记录
					TenantTypeReportUtil typeUtil = new TenantTypeReportUtil();
					typeUtil.setYearMoneyRel((String)obj[5]);
					typeUtil.setMonthMoneyRel((String)obj[6]);
					typeUtil.setYearMoneySub((BigDecimal)obj[7]);
					typeUtil.setMonthMoneySub((BigDecimal)obj[8]);
					typeUtil.setBefYearMoney((BigDecimal)obj[9]);
					if(obj[10]==null){
						typeUtil.setChargeTypeCdRel("00");
					}else{
						typeUtil.setChargeTypeCdRel((String)obj[10]);
					}
					flatUtils.get(flatUtils.size()-1).getTenantTypeReportUtils().add(typeUtil);
				}else{
					flatId =(String)obj[0];
					BisFlatReportUtil flatUtil =new BisFlatReportUtil();
					flatUtil.setBisFlatId((String)obj[0]);
					flatUtil.setFlatNo((String)obj[1]);
					flatUtil.setSquare((BigDecimal)obj[2]);
					flatUtil.setInnerSquare((BigDecimal)obj[3]);
					flatUtil.setPublicSquare((BigDecimal)obj[4]);
					TenantTypeReportUtil typeUtil = new TenantTypeReportUtil();
					typeUtil.setYearMoneyRel((String)obj[5]);
					typeUtil.setMonthMoneyRel((String)obj[6]);
					typeUtil.setYearMoneySub((BigDecimal)obj[7]);
					typeUtil.setMonthMoneySub((BigDecimal)obj[8]);
					typeUtil.setBefYearMoney((BigDecimal)obj[9]);
					if(obj[10]==null){
						typeUtil.setChargeTypeCdRel("00");
					}else{
						typeUtil.setChargeTypeCdRel((String)obj[10]);
					}
					List<TenantTypeReportUtil> typeUtils =new ArrayList<TenantTypeReportUtil>();
					typeUtils.add(typeUtil);
					flatUtil.setTenantTypeReportUtils(typeUtils);
					flatUtils.add(flatUtil);
				}
			}
		}
		return flatUtils;
	}
	/**
	 * 查询公寓的物业管理费
	 * @param reportDate 查询年月 ex. 2012-06
	 * @param bisProjectId 项目id
	 * @param isMonth 是否是查询本月数据标志位
	 * @param caculateYear 是否是年度累计标记位
	 * @return BigDecimal 物业管理费
	 */
	public List<BigDecimal> getPropManageForFlat(String reportDate,String bisProjectId,boolean isMonth,boolean caculateYear){
		
		List<BigDecimal> list = new ArrayList<BigDecimal>();
        int index = reportDate.indexOf("-");
        
        if(index>0){
        	String year = reportDate.substring(0,index);
        	String month = reportDate.substring(index+1);
        	
          StringBuffer sql = new StringBuffer();
          Map<String, Object> params = new HashMap<String, Object>();
          params.put("month", month);
          params.put("year", year);
          params.put("bisProjectId", bisProjectId);
    	  sql.append("select sum(r.MONTH_SS) from bis_flat_record r left join bis_flat t ");
          sql.append(" on t.bis_flat_id = r.bis_flat_id");
          sql.append(" where t.bis_project_id = :bisProjectId");
          sql.append(" and r.cost_type ='4' ");
          if(isMonth){
        	  sql.append(" and r.record_year = :year ");
        	  sql.append(" and r.record_month = :month ");
          }else{
	          if(caculateYear){
	        	  sql.append(" and r.record_year = :year ");
	        	  sql.append(" and r.record_month <= :month ");
	          }else{
	        	  sql.append(" and r.record_year = :year ");
	          }
          }
           list = getDao().findBySql(sql.toString(), params);
          return list;
        }
        return list;
	}
	
	
	
	/**
	 * 得到公寓4个累计值总和
	 * 
	 * @author liuzhihui 2012-06-07
	 * @param bisProjectId 项目ID
	 * @param costType 费用类别
	 * @return
	 */
	public VoFlatCount getAnnualCount(String bisProjectId,String costType,String bisFloorId, String flatNo){
		if(StringUtils.isNotBlank(bisProjectId) && StringUtils.isNotBlank(costType)){
			Map<String, Object> params = new HashMap<String, Object>();
			StringBuffer sql = new StringBuffer();
			if("4".equals(costType)){
				sql.append("select sum(t.ANNUAL_YS_MAN) as annualYsCount, sum(t.ANNUAL_SS_MAN) as annualSsCount,")
				   .append("sum(t.ANNUAL_YUS_MAN) as annualYusCount, sum(t.ANNUAL_QS_MAN) as annualQsCount");
			}else if("5".equals(costType)){
				sql.append("select sum(t.ANNUAL_YS_WATER) as annualYsCount, sum(t.ANNUAL_SS_WATER) as annualSsCount,")
				   .append("sum(t.ANNUAL_YUS_WATER) as annualYusCount, sum(t.ANNUAL_QS_WATER) as annualQsCount");
			}else if("6".equals(costType)){
				sql.append("select sum(t.ANNUAL_YS_ELEC) as annualYsCount, sum(t.ANNUAL_SS_ELEC) as annualSsCount,")
				   .append("sum(t.ANNUAL_YUS_ELEC) as annualYusCount, sum(t.ANNUAL_QS_ELEC) as annualQsCount");
			}
			sql.append(" from bis_flat t where t.bis_project_id = :bisProjectId");
			params.put("bisProjectId", bisProjectId);
			if (StringUtils.isNotBlank(flatNo)) {
				sql.append(" and t.FLAT_NO like :flatNo");
				params.put("flatNo", "%"+flatNo.trim()+"%");
			}
			if (StringUtils.isNotBlank(bisFloorId)) {
				String[] floorIds = bisFloorId.split(",");
				List<String> list = new ArrayList<String>(); 
				if(floorIds != null && floorIds.length > 0){
					for (int i = 0; i < floorIds.length; i++) {
						list.add(floorIds[i]);
					}
				}
				String[] bisFloorIds = list.toArray(new String[list.size()]); 
				sql.append(" and t.BIS_FLOOR_ID in(:bisFloorIds)");
				if(list.size() > 0){
					params.put("bisFloorIds", bisFloorIds);
				}else{
					params.put("bisFloorIds", "");
				}
			}
			List list = bisFlatDao.findBySql(sql.toString(), params);
			Object[] obj = null;
			VoFlatCount vo = null;
			for (int i = 0; i < list.size(); i++) {
				obj = (Object[])list.get(0);
				vo = new VoFlatCount();
				vo.setAnnualYsCount(fromateBigDecimal((BigDecimal)obj[0]));
				vo.setAnnualSsCount(fromateBigDecimal((BigDecimal)obj[1]));
				vo.setAnnualYusCount(fromateBigDecimal((BigDecimal)obj[2]));
				vo.setAnnualQsCount(fromateBigDecimal((BigDecimal)obj[3]));
			}
			return vo;
		}
		return null;
	}
	
	private BigDecimal fromateBigDecimal(BigDecimal val){
		if(val == null)
			return new BigDecimal(0);
		else
			return val;
	}
}

