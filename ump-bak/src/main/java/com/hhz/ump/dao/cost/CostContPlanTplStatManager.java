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
import com.hhz.ump.entity.cost.CostContPlanTplStat;

@Service
@Transactional
public class CostContPlanTplStatManager extends BaseService<CostContPlanTplStat, String> {
	@Autowired
	private CostContPlanTplStatDao costContPlanTplStatDao;
	
	/**
	 * 保存合约计划模版
	 * @param costContPlanTplStat
	 */
	public void saveCostContPlanTplStat(CostContPlanTplStat costContPlanTplStat) {
		PowerUtils.setEmptyStr2Null(costContPlanTplStat);
		costContPlanTplStatDao.save(costContPlanTplStat);
	}

	public void deleteCostContPlanTplStat(String id) {
		costContPlanTplStatDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostContPlanTplStat, String> getDao() {
		return costContPlanTplStatDao;
	}
	
	
	/**
	 * 描述：搜索合约计划并进行排序
	 * @author wangming 2012-05-08
	 * @return List<CostContPlanTplStat>
	 */
	public List<CostContPlanTplStat> searchCostContPlanStatList(String costContPlanTplId){
		Map<String,Object> values = new HashMap<String,Object>();
		List<CostContPlanTplStat> list=null;
		if(StringUtils.isNotBlank(costContPlanTplId)){
			String hql = "  from CostContPlanTplStat t where t.costContPlanTpl.costContPlanTplId =:costContPlanTplId ORDER BY t.subjectSequNo,t.rowTypeCd,t.subSequNo ASC";
			values.put("costContPlanTplId", costContPlanTplId);
			list = costContPlanTplStatDao.find(hql,values);
		}
		if(null!=list&&list.size()>0)
			return list;
		return null;
	}
	
	/**
	 * 描述：获取条件中最大的子节点中平级No
	 * @author wangming 2012-05-8
	 * @param subjectCd
	 * @param rowTypeCd
	 * @return
	 */
	public long getMaxSubSequNo(String costContPlanTplId,String subjectCd,String rowTypeCd){
		Map<String,Object> map=new HashMap<String,Object>();
		StringBuilder sb=new StringBuilder();
		sb.append(" select max(SUB_SEQU_NO) ");
		sb.append(" from COST_CONT_PLAN_TPL_STAT t ");
		sb.append(" where 1=1");
		if(StringUtils.isNotBlank(costContPlanTplId)){
			sb.append(" and t.COST_CONT_PLAN_TPL_ID = :costContPlanTplId ");
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
		Object obj=costContPlanTplStatDao.createSQLQuery(sb.toString(), map).uniqueResult();
		System.out.println("obj:====================="+obj);
		if(null!=obj)
			return Long.parseLong(String.valueOf(obj));
		return 0;
	}
	
	/**
	 * 描述：用于解析json文件格式方法
	 * @author wangming 2012-05-10
	 */
	public List<CostContPlanTplStat> getJsonStr(String jsonStr){
		List<CostContPlanTplStat> costContPlanTplStatList=new ArrayList<CostContPlanTplStat>();
		CostContPlanTplStat costContPlanTplStat;
		JSONObject jsonObj=JSONObject.fromObject(jsonStr);
		JSONArray jsons= jsonObj.getJSONArray("CostContPlanTplStat");
		
		for (int i = 0; i < jsons.size(); i++) {
			costContPlanTplStat=new CostContPlanTplStat();
			JSONObject tempJson=JSONObject.fromObject(jsons.get(i));
			String subjectCd=StringEscapeUtils.escapeSql(tempJson.getString("subjectCd"));
			String rowTypeCd=StringEscapeUtils.escapeSql(tempJson.getString("rowTypeCd"));
			if(StringUtils.isNotEmpty(subjectCd)&&StringUtils.isNotEmpty(rowTypeCd)){
				costContPlanTplStat.setSubjectCd(subjectCd);
				costContPlanTplStat.setRowTypeCd(rowTypeCd);
				if(tempJson.has("contSubTargAmt")==true){
					String contSubTargAmt=StringEscapeUtils.escapeSql(tempJson.getString("contSubTargAmt"));
					costContPlanTplStat.setContSubTargAmt(BigDecimal.valueOf(StringUtils.isEmpty(contSubTargAmt)?0:Double.valueOf(contSubTargAmt)));
				}
				
				costContPlanTplStatList.add(costContPlanTplStat);
			}
		}
		return costContPlanTplStatList;
	}
	
	/**
	 * 描述：统计所有大类【1-9】中的土地成本（合约）值
	 * @author wangming 2012-05-10
	 * @param jsonStr
	 * @return
	 */
	public BigDecimal calcContractObjAmt(String jsonStr){
		Double sumContractAmt=0.0;
		JSONObject jsonObj=JSONObject.fromObject(jsonStr);
		JSONArray jsons= jsonObj.getJSONArray("CostContPlanTplStat");
		for (int i = 0; i < jsons.size(); i++) {
			JSONObject tempJson=JSONObject.fromObject(jsons.get(i));
			String subjectCd=StringEscapeUtils.escapeSql(tempJson.getString("subjectCd"));
			String rowTypeCd=StringEscapeUtils.escapeSql(tempJson.getString("rowTypeCd"));
			String contSubTargAmt=StringEscapeUtils.escapeSql(tempJson.getString("contSubTargAmt"));
			if(StringUtils.isNotEmpty(subjectCd)&&StringUtils.isNotEmpty(rowTypeCd)){
				sumContractAmt+=StringUtils.isEmpty(contSubTargAmt)?0:Double.valueOf(contSubTargAmt);
			}
		}
		return BigDecimal.valueOf(sumContractAmt);
	}
	
	/**
	 * 描述：根据条件批量更新处理
	 * @author wangming 2012-05-10
	 * @param subjectCd
	 * @param rowTypeCd
	 * @param contSubTargAmt
	 */
	public void updateBatchProcessing(String costContPlanTplId,String subjectCd,String rowTypeCd,BigDecimal contSubTargAmt){
		Map<String,Object> map=new HashMap<String,Object>();
		StringBuilder sb=new StringBuilder();
		sb.append(" UPDATE COST_CONT_PLAN_TPL_STAT t ");
		sb.append(" SET t.cont_sub_targ_amt = :contSubTargAmt ");
		sb.append(" where 1=1");
		map.put("contSubTargAmt", StringUtils.isEmpty(contSubTargAmt.toString())?0.0:Double.valueOf(contSubTargAmt.toString()));
		if(StringUtils.isNotBlank(costContPlanTplId)){
			sb.append(" and t.COST_CONT_PLAN_TPL_ID = :costContPlanTplId ");
			map.put("costContPlanTplId", costContPlanTplId);
		}
		if(StringUtils.isNotBlank(subjectCd)&&StringUtils.isNotBlank(rowTypeCd)){
			sb.append(" and t.subject_cd = :subjectCd ");
			map.put("subjectCd", subjectCd);
			sb.append(" and t.row_type_cd = :rowTypeCd");
			map.put("rowTypeCd", rowTypeCd);
		}
		Query query=costContPlanTplStatDao.createSQLQuery(sb.toString(), map);
		query.executeUpdate();
	}
	

	/**
	* 描述：批量更新处理合约目标与原目标差额：【土地成本（原目标）+ 调减至其他二级科目成本 + 从其他科目调增成本 - 土地成本（合约）】(仅作第一次数据变更的处理)
	 * @author wangming 2012.5.16
	 * @param subjectCd   【父节点1-9中的subjectCD】
	 * @param rowTypeCd1     【父节点1-9中子类rowtypeCd=1】
	 * @param rowTypeCd5     【父节点1-9中子类rowtypeCd=5】
	 * @param rowTypeCd6     【父节点1-9中子类rowtypeCd=6】
	 * @return
	 */
	public Double getSumContractSubTargAmt(String costContPlanTplId,final String subjectCd,final String rowTypeCd1,final String rowTypeCd5,final String rowTypeCd6){
		StringBuilder sb=new StringBuilder();
		sb.append(" select sum(t.CONT_SUB_TARG_AMT) ");
		sb.append(" from COST_CONT_PLAN_TPL_STAT t ");
		sb.append(" where 1=1");
		if(StringUtils.isNotBlank(costContPlanTplId)){
			sb.append(" and t.COST_CONT_PLAN_TPL_ID = '"+costContPlanTplId+"' ");
		}
		if(StringUtils.isNotEmpty(subjectCd)){
			sb.append(" and t.SUBJECT_CD ='"+subjectCd+"'");
		}
		if(StringUtils.isNotEmpty(rowTypeCd1)&&StringUtils.isNotEmpty(rowTypeCd5)&&StringUtils.isNotEmpty(rowTypeCd6)){
			sb.append(" and t.ROW_TYPE_CD  not in ('"+rowTypeCd1+"','"+rowTypeCd5+"','"+rowTypeCd6+"')");
		}
		Object obj=costContPlanTplStatDao.createSQLQuery(sb.toString(), null).uniqueResult();
		if(obj!=null)
			return Double.valueOf(String.valueOf(obj));
		return 0.0;
		
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
		sb.append(" select t.CONT_SUB_TARG_AMT ");
		sb.append(" from COST_CONT_PLAN_TPL_STAT t ");
		sb.append(" where 1=1");
		
		if(StringUtils.isNotBlank(costContPlanTplId)){
			sb.append(" and t.COST_CONT_PLAN_TPL_ID = :costContPlanTplId ");
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
		Object obj=costContPlanTplStatDao.createSQLQuery(sb.toString(), map).uniqueResult();
		if(obj!=null)
			return Double.valueOf(String.valueOf(obj));
		return 0.0;
	}
	
	/**
	 * 描述：搜索合约计划大类为【10】，子类ROWTYPECD为【1031】目标成本调整合计
	 * @author wangming 2012-05-11
	 * @param subjectCd
	 * @param rowTypeCd
	 * @return
	 */
	public Double getSumSubTargAmt(String costContPlanTplId,final String subjectCd,final String rowTypeCd){
		Map<String,Object> map=new HashMap<String,Object>();
		StringBuilder sb=new StringBuilder();
		sb.append(" select sum(t.CONT_SUB_TARG_AMT) ");
		sb.append(" from COST_CONT_PLAN_TPL_STAT t ");
		sb.append(" where 1=1");
		if(StringUtils.isNotBlank(costContPlanTplId)){
			sb.append(" and t.COST_CONT_PLAN_TPL_ID = :costContPlanTplId ");
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
		Object obj=costContPlanTplStatDao.createSQLQuery(sb.toString(), map).uniqueResult();
		if(obj!=null)
			return Double.valueOf(String.valueOf(obj));
		return 0.0;
		
	}
	
}

