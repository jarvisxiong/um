package com.hhz.ump.dao.bis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisFlat;
import com.hhz.ump.entity.bis.BisFlatRecord;
import com.hhz.ump.web.vo.BisFlatRecordVo;

@Service
@Transactional
public class BisFlatRecordManager extends BaseService<BisFlatRecord, String> {
	@Autowired
	private BisFlatRecordDao bisFlatRecordDao;
	@Autowired
	private BisFlatDao bisFlatDao;

	public void saveBisFlatRecord(BisFlatRecord bisFlatRecord) {
		PowerUtils.setEmptyStr2Null(bisFlatRecord);
		bisFlatRecordDao.save(bisFlatRecord);
	}

	public void deleteBisFlatRecord(String id) {
		bisFlatRecordDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisFlatRecord, String> getDao() {
		return bisFlatRecordDao;
	}
	
	/**
	 * 批量修改公寓信息及批量增加公寓收费记录
	 * @param bisFlatList 公寓信息列表
	 * @param recordList 公寓收费记录列表
	 */
	public void batchExecute(List<BisFlat> bisFlatList,List<BisFlatRecord> recordList){
		Session session = bisFlatDao.getSession();
		for (int i = 0; i < bisFlatList.size(); i++) {
			session.saveOrUpdate(recordList.get(i));
			if(i%50 == 0){
				session.flush();
				session.clear();
			}
		}
		
		Session session2 = getDao().getSession();
		for(int i=0; i<recordList.size(); i++) {
			session2.saveOrUpdate(recordList.get(i));
			if(i%50 == 0){
				session2.flush();
				session2.clear();
			}
		}
	}

	/**
	 * 查询数据是否已经存在(导入数据用)
	 * @param flatId 公寓ID
	 * @param year  年
	 * @param month 月
	 * @return
	 */
	public BisFlatRecord getExistsFlatRecord(String flatId,String costType, String year,String month) {
		StringBuffer hql = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" from BisFlatRecord t where 1=1");
		if (StringUtils.isNotBlank(flatId) && StringUtils.isNotBlank(costType) 
					&& StringUtils.isNotBlank(year) && StringUtils.isNotBlank(month)) {
			hql.append(" and t.bisFlat.bisFlatId =:bisFlatId and t.costType =:costType")
			  .append(" and t.recordYear =:recordYear and t.recordMonth =:recordMonth");
			params.put("bisFlatId", flatId);
			params.put("costType", costType);
			params.put("recordYear", Long.valueOf(year));
			params.put("recordMonth", Long.valueOf(month));
			List<BisFlatRecord> list = bisFlatRecordDao.find(hql.toString(), params);
			if(list != null && list.size() > 0)
				return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询公寓收费记录合计
	 * @param values
	 * @return
	 */
	public BisFlatRecordVo getBisFlatRecordCount(Map<String, Object> values){
		if(values != null){
			String year = null;
			String month = null;
			StringBuffer sql = new StringBuffer();
			Map<String, Object> params = new HashMap<String, Object>();
			sql.append("select sum(t.WATER_TONNAGE) as waterTonnage,sum(t.MONTH_YS) as monthYs,sum(t.MONTH_YIS) as monthYis,")
			   .append("sum(t.MONTH_MONTH) as monthMonth,sum(t.MONTH_YUS) as monthYus,sum(t.CURR_YEAR_DEBT) as currYearDebt,")
			   .append("sum(t.AGO_YEAR_DEBT) as agoYearDebt,sum(t.OWE_PROP_RATE) as owePropRate,sum(t.OWE_WATER_RATE) as oweWaterRate,")
			   .append("sum(t.OWE_ELEC_RATE) as oweElecRate,sum(t.CURR_YEAR_YS_TOTAL) as currYearYsTotal,sum(t.YS_TOTAL) as ysTotal,")
			   .append("sum(t.CURR_YEAR_SS_TOTAL) as currYearSsTotal,sum(t.SS_TOTAL) as ssTotal,sum(t.CURR_YEAR_PROP_RATE) as currYearPropRate,")
			   .append("sum(t.CURR_YEAR_YUS_PROP_RATE) as currYearYusPropRate,sum(t.CURR_YEAR_WATER_RATE) as currYearWaterRate,")
			   .append("sum(t.CURR_YEAR_YUS_WATER_RATE) as currYearYusWaterRate,sum(t.CURR_YEAR_ELEC_RATE) as currYearElecRate,")
			   .append("sum(t.CURR_YEAR_YUS_ELEC_RATE) as currYearYusElecRate,sum(t.AGO_YEAR_OWNFEE_TOTAL) as agoYearOwnfeeTotal,")
			   .append("sum(t.YUS_TOTAL) as yusTotal,sum(t.CURR_YEAR_QS_TOTAL) as currYearQsTotal,sum(t.QS_TOTAL) as qsTotal,sum(t.MONTH_SS) as monthSs")
			   .append(" from bis_flat_record t where 1=1");
			if(values.get("bisProjectId") != null){
				sql.append(" and t.BIS_FLAT_ID in(select t1.BIS_FLAT_ID from bis_flat t1 where t1.BIS_PROJECT_ID =:bisProjectId)");
				params.put("bisProjectId", values.get("bisProjectId").toString());
			}
			if(values.get("flatNo") != null){
				sql.append(" and t.BIS_FLAT_ID in(select t1.BIS_FLAT_ID from bis_flat t1 where t1.FLAT_NO like :flatNo)");
				params.put("flatNo", "%"+values.get("flatNo").toString()+"%");
			}
			if(values.get("costType") != null){
				sql.append(" and t.COST_TYPE =:costType");
				params.put("costType", values.get("costType").toString());
			}
			if(values.get("yearMonth") != null){
				String yearMonth = values.get("yearMonth").toString();
				String[] date = yearMonth.split("-");
				year = date[0];
				month = date[1];
				sql.append(" and t.RECORD_YEAR =:recordYear and t.RECORD_MONTH =:recordMonth");
				params.put("recordYear", year);
				params.put("recordMonth", month);
			}
			if(values.get("bisFloorId") != null){
				String[] floorIds = values.get("bisFloorId").toString().split(",");
				List<String> list = new ArrayList<String>(); 
				if(floorIds != null && floorIds.length > 0){
					for (int i = 0; i < floorIds.length; i++) {
						list.add(floorIds[i]);
					}
				}
				String[] bisFloorIds = list.toArray(new String[list.size()]); 
				sql.append(" and t.BIS_FLAT_ID in(select t1.BIS_FLAT_ID from bis_flat t1 where t1.BIS_FLOOR_ID in(:bisFloorIds))");
				if(list.size() > 0){
					params.put("bisFloorIds", bisFloorIds);
				}else{
					params.put("bisFloorIds", "");
				}
			}
			List list = bisFlatRecordDao.findBySql(sql.toString(), params);
			Object[] obj = null;
			BisFlatRecordVo vo = null;
			if(list !=null && list.size() > 0){
				obj = (Object[])list.get(0);
				vo = new BisFlatRecordVo();
				vo.setRecordYear(Long.valueOf(year));
				vo.setRecordMonth(Long.valueOf(month));
				vo.setWaterTonnage(fromateBigDecimal((BigDecimal)obj[0]));
				vo.setMonthYs(fromateBigDecimal((BigDecimal)obj[1]));
				vo.setMonthYis(fromateBigDecimal((BigDecimal)obj[2]));
				vo.setMonthMonth(fromateBigDecimal((BigDecimal)obj[3]));
				vo.setMonthYus(fromateBigDecimal((BigDecimal)obj[4]));
				vo.setCurrYearDebt(fromateBigDecimal((BigDecimal)obj[5]));
				vo.setAgoYearDebt(fromateBigDecimal((BigDecimal)obj[6]));
				vo.setOwePropRate(fromateBigDecimal((BigDecimal)obj[7]));
				vo.setOweWaterRate(fromateBigDecimal((BigDecimal)obj[8]));
				vo.setOweElecRate(fromateBigDecimal((BigDecimal)obj[9]));
				vo.setCurrYearYsTotal(fromateBigDecimal((BigDecimal)obj[10]));
				vo.setYsTotal(fromateBigDecimal((BigDecimal)obj[11]));
				vo.setCurrYearSsTotal(fromateBigDecimal((BigDecimal)obj[12]));
				vo.setSsTotal(fromateBigDecimal((BigDecimal)obj[13]));
				vo.setCurrYearPropRate(fromateBigDecimal((BigDecimal)obj[14]));
				vo.setCurrYearYusPropRate(fromateBigDecimal((BigDecimal)obj[15]));
				vo.setCurrYearWaterRate(fromateBigDecimal((BigDecimal)obj[16]));
				vo.setCurrYearYusWaterRate(fromateBigDecimal((BigDecimal)obj[17]));
				vo.setCurrYearElecRate(fromateBigDecimal((BigDecimal)obj[18]));
				vo.setCurrYearYusElecRate(fromateBigDecimal((BigDecimal)obj[19]));
				vo.setAgoYearOwnfeeTotal(fromateBigDecimal((BigDecimal)obj[20]));
				vo.setYusTotal(fromateBigDecimal((BigDecimal)obj[21]));
				vo.setCurrYearQsTotal(fromateBigDecimal((BigDecimal)obj[22]));
				vo.setQsTotal(fromateBigDecimal((BigDecimal)obj[23]));
				vo.setMonthSs(fromateBigDecimal((BigDecimal)obj[24]));
				return vo;
			}
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
