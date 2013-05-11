package com.hhz.ump.dao.cost;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cost.CostContPlanTplDetail;

@Service
@Transactional
public class CostContPlanTplDetailManager extends BaseService<CostContPlanTplDetail, String> {
	@Autowired
	private CostContPlanTplDetailDao costContPlanTplDetailDao;
	
	//定义以json存储，供合约成本汇总同步更新处理
	private static final String jsonStr = "{'CostContPlanTplDetail':[{'subjectCd':'1','rowTypeCd':'1'},{'subjectCd':'2','rowTypeCd':'1'}," +
																    "{'subjectCd':'3','rowTypeCd':'1'},{'subjectCd':'4','rowTypeCd':'1'}," +
																    "{'subjectCd':'5','rowTypeCd':'1'},{'subjectCd':'6','rowTypeCd':'1'}," +
																    "{'subjectCd':'7','rowTypeCd':'1'},{'subjectCd':'8','rowTypeCd':'1'}," +
																    "{'subjectCd':'9','rowTypeCd':'1'}]}";

	public void saveCostContPlanTplDetail(CostContPlanTplDetail costContPlanTplDetail) {
		PowerUtils.setEmptyStr2Null(costContPlanTplDetail);
		costContPlanTplDetailDao.save(costContPlanTplDetail);
	}

	public void deleteCostContPlanTplDetail(String id) {
		costContPlanTplDetailDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostContPlanTplDetail, String> getDao() {
		return costContPlanTplDetailDao;
	}
	
	/**
	 * 描述：解析json文件,用于处理甲供材料、设备
	 * @author wangming 2012-05-10
	 */
	public List<CostContPlanTplDetail> getPartMateTypeJsonStr(String jsonStr){
		List<CostContPlanTplDetail> costContPlanTplDetailList=new ArrayList<CostContPlanTplDetail>();
		CostContPlanTplDetail costContPlanTplDetail;
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		JSONArray jsons = jsonObj.getJSONArray("CostContPlanTplDetail");
		
		for (int i = 0; i < jsons.size(); i++) {
			costContPlanTplDetail=new CostContPlanTplDetail();
			JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
			String contPartMateTypeCds = StringEscapeUtils.escapeSql(tempJson.getString("contPartMateTypeCds"));
			String contPartMateTypeNames = StringEscapeUtils.escapeSql(tempJson.getString("contPartMateTypeNames"));
			costContPlanTplDetail.setPartMateTypeCds(contPartMateTypeCds);
			costContPlanTplDetail.setPartMateTypeNames(contPartMateTypeNames);
			costContPlanTplDetailList.add(costContPlanTplDetail);
		}
		return costContPlanTplDetailList;
	}
	
	/**
	 * 描述：解析json文件,计算统计
	 * @author wangming 2012-05-10
	 */
	public List<CostContPlanTplDetail> getContPlanTplDetailJsonStr(String jsonStr){
		List<CostContPlanTplDetail> costContPlanTplDetailList=new ArrayList<CostContPlanTplDetail>();
		CostContPlanTplDetail costContPlanTplDetail;
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		JSONArray jsons = jsonObj.getJSONArray("CostContPlanTplDetail");
		
		for (int i = 0; i < jsons.size(); i++) {
			costContPlanTplDetail=new CostContPlanTplDetail();
			JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
			String subjectCds = StringEscapeUtils.escapeSql(tempJson.getString("subjectCd"));
			String rowTypeCds = StringEscapeUtils.escapeSql(tempJson.getString("rowTypeCd"));
			costContPlanTplDetail.setSubjectCd(subjectCds);
			costContPlanTplDetail.setRowTypeCd(rowTypeCds);
			costContPlanTplDetailList.add(costContPlanTplDetail);
		}
		return costContPlanTplDetailList;
	}
	
	
	
	
	/**
	 * 描述：根据subjectCd,rowTypeCd条件,批量更新处理
	 * @author wangming 2012-05-10
	 * @param subjectCd
	 * @param rowTypeCd
	 * @param contSubTargAmt
	 */
	public void updateContSubTargAmt(String costContPlanTplId,String subjectCd,String rowTypeCd,BigDecimal contSubTargAmt){
		Map<String,Object> map=new HashMap<String,Object>();
		StringBuilder sb=new StringBuilder();
		sb.append(" UPDATE COST_CONT_PLAN_TPL_DETAIL t ");
		sb.append(" SET t.cont_sub_target_amt = :contSubTargAmt ");
		sb.append(" where 1=1");
		map.put("contSubTargAmt", StringUtils.isEmpty(contSubTargAmt.toString())?0.0:Double.valueOf(contSubTargAmt.toString()));
		if(StringUtils.isNotBlank(costContPlanTplId)){
			sb.append(" and t.COST_CONT_PLAN_TPL_ID =:costContPlanTplId");
			map.put("costContPlanTplId", costContPlanTplId);
		}
		if(StringUtils.isNotBlank(subjectCd)&&StringUtils.isNotBlank(rowTypeCd)){
			sb.append(" and t.subject_cd = :subjectCd ");
			map.put("subjectCd", subjectCd);
			sb.append(" and t.row_type_cd = :rowTypeCd");
			map.put("rowTypeCd", rowTypeCd);
		}
		Query query = costContPlanTplDetailDao.createSQLQuery(sb.toString(), map);
		query.executeUpdate();
	}
	
	
	/**
	 * 描述：根据条件搜索出所有大类【1-9】中的合约子目标成本
	 * @author wangming 2012-05-11
	 * @param subjectCd
	 * @param rowTypeCd
	 * @return
	 */
	public Double getContSubTargAmt(String costContPlanTplId,final String subjectCd,final String rowTypeCd){
		Map<String,Object> map=new HashMap<String,Object>();
		StringBuilder sb=new StringBuilder();
		sb.append(" select t.cont_sub_target_amt ");
		sb.append(" from COST_CONT_PLAN_TPL_DETAIL t ");
		sb.append(" where 1=1");
		if(StringUtils.isNotBlank(costContPlanTplId)){
			sb.append(" and t.COST_CONT_PLAN_TPL_ID =:costContPlanTplId");
			map.put("costContPlanTplId", costContPlanTplId);
		}
		if(StringUtils.isNotBlank(subjectCd)){
			sb.append(" and t.SUBJECT_CD = :subjectCd ");
			map.put("subjectCd", subjectCd);
		}
		if(StringUtils.isNotBlank(rowTypeCd)){
			sb.append(" and t.ROW_TYPE_CD = :rowTypeCd");
			map.put("rowTypeCd", rowTypeCd);
		}
		Object obj = costContPlanTplDetailDao.createSQLQuery(sb.toString(), map).uniqueResult();
		if(obj!=null)
			return Double.valueOf(String.valueOf(obj));
		return 0.0;
	}
	
	
	public List<CostContPlanTplDetail> getCostContPlanTplDetail(String costContPlanTplId){
		List<CostContPlanTplDetail> costContPLanDetailList = new ArrayList<CostContPlanTplDetail>();
		StringBuffer hql = new StringBuffer();
		Map<String,Object> values = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(costContPlanTplId)){
			hql.append(" from CostContPlanTplDetail t where 1=1 ");
			hql.append(" and t.costContPlanTpl.costContPlanTplId =:costContPlanTplId");
			values.put("costContPlanTplId", costContPlanTplId);
			hql.append(" order by  TO_NUMBER(t.subjectCd),TO_NUMBER(t.rowTypeCd),t.contSequNo,t.subjectSequNo");
		}
		
		costContPLanDetailList = costContPlanTplDetailDao.find(hql.toString(), values);
		if(costContPLanDetailList != null&&costContPLanDetailList.size()>0)
			return costContPLanDetailList;
		else
			return null;
	}
	
	/**
	 * 描述：统计除【1-9】大类中的rowTypeCd为1的合约子成本额
	 * @author wangming 
	 * @param subjectCd
	 * @param subjectRowTypeCds
	 * @return
	 */
	public Double getContSubTargAmt1_9(String costContPlanTplId,final String subjectCd,String subjectRowTypeCds){
		Map<String,Object> map=new HashMap<String,Object>();
		StringBuilder sb=new StringBuilder();
		sb.append(" select sum(t.cont_sub_target_amt) ");
		sb.append(" from COST_CONT_PLAN_TPL_DETAIL t ");
		sb.append(" where 1=1");
		if(StringUtils.isNotBlank(costContPlanTplId)){
			sb.append(" and t.COST_CONT_PLAN_TPL_ID =:costContPlanTplId");
			map.put("costContPlanTplId", costContPlanTplId);
		}
		if(StringUtils.isNotBlank(subjectCd)){
			sb.append(" and t.SUBJECT_CD = :subjectCd ");
			map.put("subjectCd", subjectCd);
		}
		if(StringUtils.isNotBlank(subjectRowTypeCds)){
			sb.append(" and (trim(t.subject_cd)||trim(t.row_type_cd) not in ('"+subjectRowTypeCds+"'))");
		}
		Object obj = costContPlanTplDetailDao.createSQLQuery(sb.toString(), map).uniqueResult();
		if(obj!=null)
			return Double.valueOf(String.valueOf(obj));
		return 0.0;
	}
	
	
	/**
	 * 合约成本汇总同步更新处理
	 * @return
	 */
	public String getContSubTargAmt(String costContPlanTplId){
		StringBuilder sb = new StringBuilder("{'CostContPlanTplStat':[");
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		JSONArray jsons = jsonObj.getJSONArray("CostContPlanTplDetail");
		for (int i = 0; i < jsons.size(); i++) {
			JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
			String subjectCd = StringEscapeUtils.escapeSql(tempJson.getString("subjectCd"));
			String rowTypeCd = StringEscapeUtils.escapeSql(tempJson.getString("rowTypeCd"));
			//查询出合约规划明细表中对应的合约子成本额
			Double contSubTargAmt = getContSubTargAmt(costContPlanTplId,subjectCd,rowTypeCd);
			if(i == jsons.size()-1){
				sb.append("{'subjectCd':'"+subjectCd+"','rowTypeCd':'"+rowTypeCd+"','contSubTargAmt':'"+contSubTargAmt+"'}]}");
			}else{
				sb.append("{'subjectCd':'"+subjectCd+"','rowTypeCd':'"+rowTypeCd+"','contSubTargAmt':'"+contSubTargAmt+"'},");
			}
		}
		return sb.toString();
	}
	
}

