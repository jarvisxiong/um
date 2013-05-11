package com.hhz.ump.dao.bis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.entity.bis.BisMust;
import com.hhz.ump.web.vo.VoFact;

@Service
@Transactional
public class BisMustManager extends BaseService<BisMust, String> {
	@Autowired
	private BisMustDao bisMustDao;
	
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	
	public static final String BIS_MUST_FULL_TYPE = "0";		// 应收明细-已收齐（列表快速检索）
	public static final String BIS_MUST_NON_FULL_TYPE = "1";	// 未收齐
	public static final String BIS_MUST_ALL_TYPE = "2";			// 全部类型
	
	public void saveBisMust(BisMust bisMust) {
		PowerUtils.setEmptyStr2Null(bisMust);
		bisMustDao.save(bisMust);
	}

	public void deleteBisMust(String id) {
		bisMustDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisMust, String> getDao() {
		return bisMustDao;
	}
	
	/**
	 * 根据合同ID删除应收款
	 */
	public void deleteByBisContId(String bisContId) {
		String hql = new String("from BisMust where bisCont.bisContId=? ");
		List<BisMust> list = bisMustDao.find(hql.toString(), bisContId);
		for(BisMust bisMust : list) {
			appAttachFileManager.deleteByBizEntityId(bisMust.getBisMustId(), false);
			delete(bisMust);
		}
	}
	
	public void save(BisMust bisMust) {
		
		String tmpId = bisMust.getEntityTmpId();
		bisMust.setEntityTmpId(null);
		saveBisMust(bisMust);
		
		if (StringUtils.isNotEmpty(tmpId)) {
			appAttachFileManager.updateTmpFile(tmpId, BisMust.class.getSimpleName(), bisMust.getBisMustId());
		}
	}
	
	/**
	 * 删除某合同下不在主键数组ids之外的应收款
	 */
	public void deleteLeaveBatch(String bisContId, String chargeTypeCd, Object[] excludeIds) {
		
		StringBuffer hql = new StringBuffer("from BisMust where bisCont.bisContId=:bisContId and chargeTypeCd=:chargeTypeCd ");
		if(excludeIds.length>0) {
			hql.append("and bisMustId not in (:ids) ");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisContId", bisContId);
		param.put("chargeTypeCd", chargeTypeCd);
		param.put("ids", excludeIds);
		List<BisMust> list = bisMustDao.find(hql.toString(), param);
		
		for(BisMust bisMust : list) {
			appAttachFileManager.deleteByBizEntityId(bisMust.getBisMustId(), false);
			delete(bisMust);
		}
	}

	/**
	 * 审核应收款
	 */
	public void approveMust(String[] ids, String statusCd) {

		String hql = "update BisMust set statusCd=:statusCd where bisMustId in (:ids)";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("statusCd", statusCd);
		param.put("ids", ids);
		bisMustDao.batchExecute(hql, param);
		// TODO 审核-2或驳回-3时修改对应实收
		/*String facthql = "update BisFact set mustmoney=? where bisContId=? and chargeTypeCd=? and factYear=? and factMonth=?";
		for (String mustid : ids) {
			BisMust bisMust = getEntity(mustid);
			getDao().batchExecute(facthql, bisMust.getMoney(), bisMust.getBisCont().getBisContId(),
					bisMust.getChargeTypeCd(), bisMust.getMustYear(), bisMust.getMustMonth());
		}*/
		//return "success";
	}

	/**
	 * 更新租户ID
	 */
	public void updateMustTenant(String bisContId, String bisTenantId) {
		
		StringBuffer hql = new StringBuffer("update BisMust set bisTenantId=? where bisCont.bisContId=?");
		bisMustDao.batchExecute(hql.toString(), bisTenantId, bisContId);
	}
	
	/**
	 * 是否已存在应收(能源费)
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public BisMust getExistsMust(String bisContId, String bisFlatId, String bisMultiId,
			String chargeTypeCd, String mustYear, String mustMonth) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("chargeTypeCd", chargeTypeCd);
		param.put("mustYear", mustYear);
		param.put("mustMonth", mustMonth);
		StringBuffer hql = new StringBuffer("from BisMust ");
		hql.append(" where mustYear=:mustYear and mustMonth=:mustMonth ");
		if(StringUtils.isNotBlank(chargeTypeCd)){
			hql.append(" and chargeTypeCd=:chargeTypeCd ");//and typeCd=:typeCd
			/*if("1".equals(chargeTypeCd) || "3".equals(chargeTypeCd) || "4".equals(chargeTypeCd)) {
				param.put("typeCd", "0");
			} else {
				param.put("typeCd", "1");
			}*/
		}
		if(StringUtils.isNotBlank(bisContId)) {
			hql.append(" and bisCont.bisContId=:bisContId");
			param.put("bisContId", bisContId);
		}
		if(StringUtils.isNotBlank(bisFlatId)) {
			hql.append(" and bisFlatId=:bisFlatId");
			param.put("bisFlatId", bisFlatId);
		}
		if(StringUtils.isNotBlank(bisMultiId)) {
			hql.append(" and bisMultiId=:bisMultiId");
			param.put("bisMultiId", bisMultiId);
		}
//		if ("1".equals(floorTypeCd)) {
//			hql.append(" and bisCont.bisContId=:bisContId");
//			param.put("bisContId", bisContId);
//		} else if ("2".equals(floorTypeCd)) {
//			hql.append(" and bisFlatId=:bisFlatId");
//			param.put("bisFlatId", bisFlatId);
//		} else if ("3".equals(floorTypeCd)) {
//			hql.append(" and bisMultiId=:bisMultiId");
//			param.put("bisMultiId", bisMultiId);
//		} else
//			throw new RuntimeException("floor type error!");

		List<BisMust> list = getDao().find(hql.toString(), param);
		if (list.size() > 1)
			throw new RuntimeException("数据异常，请联系管理员");
		if (list.size() == 1)
			return list.get(0);
		return null;

	}
	/**
	 * 搜索应收记录:有楼号或公寓编号
	 * @param voPage
	 * @param voFact
	 * @return
	 */
	public Page<BisMust> queryMustByCont(Page voPage,VoFact voFact){
		return queryFlatSql(voPage,voFact,"*","");
	}
	   /**
	 * 搜索公寓应收总和
	 * @return
	 */
	public BigDecimal mustSumByFlat(Page voPage,VoFact voFact){
		Page<BisMust> mustPage =queryFlatSql(voPage,voFact,"sum(a.money)","total");
		if(mustPage!=null&&mustPage.getResult()!=null&&mustPage.getResult().size()>0)
			return mustPage.getResult().get(0).getMoney();
		else
			return new BigDecimal(0);
	}
	
	private Page<BisMust> queryFlatSql(Page voPage,VoFact voFact,String queryCont,String type){
		//param参数
		Map<String, Object> param = new HashMap<String, Object>();
		//取与合同关联的公寓应收收记录
		StringBuffer sql = new StringBuffer("select ");
		sql.append(queryCont);
		sql.append(" from ((select fc.* from Bis_Must fc where fc.bis_Project_Id=:bisProjectId ");
		sql.append(" and exists (select 1 from Bis_Flat_Cont_Rel r where r.bis_Cont_Id=fc.bis_Cont_Id ");
		sql.append(" and exists (select 1 from Bis_Flat ft where ft.bis_Flat_Id=r.bis_Flat_Id");
		//公寓编号
		if(StringUtils.isNotBlank(voFact.getFlatNo())){
			sql.append(" and ft.flat_No like :flatNo ");
		}
		//楼号
		if(StringUtils.isNotBlank(voFact.getBuildingNum())){
			sql.append(" and exists (select 1 from Bis_Floor fr where fr.bis_Floor_Id=ft.bis_Floor_Id and fr.building_Num like :buildingNum) ");
		}
		sql.append("))");
		
		//类别
		if(StringUtils.isNotBlank(voFact.getChargeTypeCd())){
			sql.append(" and fc.CHARGE_TYPE_CD =:chargeTypeCd");
		}
		//年
		if(StringUtils.isNotBlank(voFact.getFactYear())){
			sql.append(" and fc.must_year =:mustYear");
		}
		//月
		if(StringUtils.isNotBlank(voFact.getFactMonth())){
			sql.append(" and fc.must_month =:mustMonth");
		}
		
		if (StringUtils.isNotBlank(voFact.getReportDateStart())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.must_year)||'-'||lpad(fc.must_month,2,'0'))>=:reportDateStart");
        	param.put("reportDateStart", voFact.getReportDateStart());
        }
        if (StringUtils.isNotBlank(voFact.getReportDateEnd())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.must_year)||'-'||lpad(fc.must_month,2,'0'))<=:reportDateEnd");
            param.put("reportDateEnd", voFact.getReportDateEnd());
        }
		//审核状态
		if (StringUtils.isNotBlank(voFact.getStatusCd())) {
			sql.append(" AND fc.status_cd = :statusCd");
		}
		//高级搜索的实收年、月
		if(StringUtils.isNotBlank(voFact.getMinMonth())){
			sql.append(" and fc.COLL_DATE >=to_date(:minMonth,'yyyy-MM-dd')");
		}
		if(StringUtils.isNotBlank(voFact.getMaxMonth())){
			sql.append(" and fc.COLL_DATE <=to_date(:maxMonth,'yyyy-MM-dd')");
		}
		//创建人
		if(StringUtils.isNotBlank(voFact.getCreator())){
			sql.append(" and fc.CREATOR =:creator");
		}
		//审核人
		if(StringUtils.isNotBlank(voFact.getCheckUserCd())){
			sql.append(" and fc.CHECK_USER_CD =:checkUserCd");
		}
		//实收金额
		if(voFact.getMinMoney()!=null){
			sql.append(" and fc.money >=:minMoney");
		}
		if(voFact.getMaxMoney()!=null){
			sql.append(" and fc.money<=:maxMoney");
		}
		//取无合同的公寓应收记录
		sql.append(") union select bfc.* from bis_Must bfc where bfc.bis_Project_Id=:bisProjectId and bfc.BIS_FLAT_ID is not null ");
		
		sql.append(" and exists (select 1 from Bis_Flat ft where ft.bis_Flat_Id=bfc.bis_Flat_Id");
		//公寓编号
		if(StringUtils.isNotBlank(voFact.getFlatNo())){
			sql.append(" and ft.flat_No like :flatNo");
		}
		//楼号
		if(StringUtils.isNotBlank(voFact.getBuildingNum())){
			sql.append(" and exists (select 1 from Bis_Floor fr where fr.bis_Floor_Id=bfc.bis_Floor_Id and fr.building_Num like :buildingNum) ");
		}
		sql.append(")");
		//类别
		if(StringUtils.isNotBlank(voFact.getChargeTypeCd())){
			sql.append(" and bfc.CHARGE_TYPE_CD =:chargeTypeCd");
		}
		//年
		if(StringUtils.isNotBlank(voFact.getFactYear())){
			sql.append(" and bfc.must_year =:mustYear");
		}
		//月
		if(StringUtils.isNotBlank(voFact.getFactMonth())){
			sql.append(" and bfc.must_month =:mustMonth");
		}
		
        //实收权责年月起止时间 add by liuzhihui at 2012-07-18
		if (StringUtils.isNotBlank(voFact.getReportDateStart())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(bfc.must_year)||'-'||lpad(bfc.must_month,2,'0'))>=:reportDateStart");
        }
        if (StringUtils.isNotBlank(voFact.getReportDateEnd())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(bfc.must_year)||'-'||lpad(bfc.must_month,2,'0'))<=:reportDateEnd");
        }
		
		//审核状态
		if (StringUtils.isNotBlank(voFact.getStatusCd())) {
			sql.append(" AND bfc.status_cd = :statusCd");
		}
		//高级搜索的实收年、月
		if(StringUtils.isNotBlank(voFact.getMinMonth())){
			sql.append(" and bfc.COLL_DATE >=to_date(:minMonth,'yyyy-MM-dd')");
		}
		if(StringUtils.isNotBlank(voFact.getMaxMonth())){
			sql.append(" and bfc.COLL_DATE <=to_date(:maxMonth,'yyyy-MM-dd')");
		}
		//创建人
		if(StringUtils.isNotBlank(voFact.getCreator())){
			sql.append(" and bfc.CREATOR =:creator");
		}
		//审核人
		if(StringUtils.isNotBlank(voFact.getCheckUserCd())){
			sql.append(" and bfc.CHECK_USER_CD =:checkUserCd");
		}
		//实收金额
		if(voFact.getMinMoney()!=null){
			sql.append(" and bfc.money >=:minMoney");
		}
		if(voFact.getMaxMoney()!=null){
			sql.append(" and bfc.money<=:maxMoney");
		}
		sql.append(") a where 1=1");
		param.put("bisProjectId", voFact.getBisProjectId());
		//公寓编号
		if(StringUtils.isNotBlank(voFact.getFlatNo())){
			param.put("flatNo", "%"+voFact.getFlatNo()+"%");
		}
		//楼号
		if(StringUtils.isNotBlank(voFact.getBuildingNum())){
			param.put("buildingNum", "%"+voFact.getBuildingNum()+"%");
		}
		//类别
		if(StringUtils.isNotBlank(voFact.getChargeTypeCd())){
			param.put("chargeTypeCd", voFact.getChargeTypeCd());
		}
		/*//年
		if(StringUtils.isNotBlank(voFact.getFactYear())){
			param.put("mustYear", voFact.getFactYear());
		}
		//月
		if(StringUtils.isNotBlank(voFact.getFactMonth())){
			param.put("mustMonth", voFact.getFactMonth());
		}*/
		//审核状态
		if (StringUtils.isNotBlank(voFact.getStatusCd())) {
			param.put("statusCd", voFact.getStatusCd());
		}
		//高级搜索的实收年、月
		if(StringUtils.isNotBlank(voFact.getMinMonth())){
			param.put("minMonth", voFact.getMinMonth());
		}
		if(StringUtils.isNotBlank(voFact.getMaxMonth())){
			param.put("maxMonth", voFact.getMaxMonth());
		}
		//创建人
		if(StringUtils.isNotBlank(voFact.getCreator())){
			param.put("creator", voFact.getCreator());
		}
		//审核人
		if(StringUtils.isNotBlank(voFact.getCheckUserCd())){
			param.put("checkUserCd", voFact.getCheckUserCd());
		}
		//实收金额
		if(voFact.getMinMoney()!=null){
			param.put("minMoney", voFact.getMinMoney());
		}
		if(voFact.getMaxMoney()!=null){
			param.put("maxMoney", voFact.getMaxMoney());
		}
		if("total".equals(type)){
			List<Object> list =this.findBySql(sql.toString(), param);
			if(list!=null&&list.size()>0&&list.get(0)!=null){
				BisMust must=new BisMust();
				must.setMoney(new BigDecimal(list.get(0).toString()));
				List<BisMust> mustList = new ArrayList<BisMust>();
				mustList.add(must);
				Page<BisMust> mustPage =new Page<BisMust>();
				mustPage.setResult(mustList);
				return mustPage;
			} else
				return null;
		}else{
			Map<String, Class> mapClazz=new HashMap<String, Class>();
			mapClazz.put("bis", BisMust.class);
			Page<BisMust> mustPage =this.findPageSql(voPage, sql.toString(), param, mapClazz);
			return mustPage;
		}
	}
	
	public Page<BisMust> queryMultiByMust(Page voPage,VoFact voFact){
		return queryMultiSql(voPage,voFact,"*","");
	}
	public BigDecimal mustSumByMulti(Page voPage,VoFact voFact){
		Page<BisMust> mustPage =queryMultiSql(voPage,voFact,"sum(a.money)","total");
		if(mustPage!=null&&mustPage.getResult()!=null&&mustPage.getResult().size()>0)
			return mustPage.getResult().get(0).getMoney();
		else
			return new BigDecimal(0);
	}
	
	private Page<BisMust> queryMultiSql(Page voPage, VoFact voFact,String queryCont,String type){
		Map<String, Object> param = new HashMap<String, Object>();
		//取与合同关联的多经应收收记录
		StringBuffer sql = new StringBuffer("select ");
		sql.append(queryCont);
		sql.append(" from ((select fc.* from Bis_Must fc where fc.bis_Project_Id=:bisProjectId and fc.bis_multi_id is null ");
		sql.append(" and exists (select 1 from Bis_Multi_Cont_Rel r where r.bis_Cont_Id=fc.bis_Cont_Id ");
		sql.append(" and exists (select 1 from bis_multi mu where mu.bis_multi_id =r.bis_multi_id ");
		sql.append("))");
		//类别
		if(StringUtils.isNotBlank(voFact.getChargeTypeCd())){
			sql.append(" and fc.CHARGE_TYPE_CD =:chargeTypeCd");
		}
		//如果有多经合同，则搜索多经合同内容
		if(StringUtils.isNotBlank(voFact.getBisContId())){
			sql.append(" and fc.bis_Cont_Id =:bisContId");
		}else if(StringUtils.isNotBlank(voFact.getBisMultiId())){
			sql.append(" and fc.bis_Multi_Id =:bisMultiId");
		}
		//普通搜索里面的年月搜索
		if(StringUtils.isNotBlank(voFact.getFactYear())){
			sql.append(" and fc.must_year =:mustYear");
		}
		//月
		if(StringUtils.isNotBlank(voFact.getFactMonth())){
			sql.append(" and fc.must_month =:mustMonth");
		}
		if (StringUtils.isNotBlank(voFact.getReportDateStart())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.must_year)||'-'||lpad(fc.must_month,2,'0'))>=:reportDateStart");
        	param.put("reportDateStart", voFact.getReportDateStart());
        }
        if (StringUtils.isNotBlank(voFact.getReportDateEnd())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.must_year)||'-'||lpad(fc.must_month,2,'0'))<=:reportDateEnd");
            param.put("reportDateEnd", voFact.getReportDateEnd());
        }
		//高级搜索的实收年、月
		if(StringUtils.isNotBlank(voFact.getMinMonth())){
			sql.append(" and fc.COLL_DATE >=to_date(:minMonth,'yyyy-MM-dd')");
		}
		if(StringUtils.isNotBlank(voFact.getMaxMonth())){
			sql.append(" and fc.COLL_DATE <=to_date(:maxMonth,'yyyy-MM-dd')");
		}
		//创建人
		if(StringUtils.isNotBlank(voFact.getCreator())){
			sql.append(" and fc.CREATOR =:creator");
		}
		//审核人
		if(StringUtils.isNotBlank(voFact.getCheckUserCd())){
			sql.append(" and fc.CHECK_USER_CD =:checkUserCd");
		}
		//审核状态
		if (StringUtils.isNotBlank(voFact.getStatusCd())) {
			sql.append(" AND fc.status_cd = :statusCd");
		}
		//实收金额
		if(voFact.getMinMoney()!=null){
			sql.append(" and fc.money >=:minMoney");
		}
		if(voFact.getMaxMoney()!=null){
			sql.append(" and fc.money<=:maxMoney");
		}
		if (StringUtils.isNotBlank(voFact.getStatusCd())) {
            sql.append(" and fc.status_cd=:statusCd");
        }
		//年月从
		//取无合同的商铺应收记录
		sql.append(") union select bfc.* from bis_Must bfc where bfc.bis_Project_Id=:bisProjectId and bfc.BIS_Multi_ID is not null ");
		//类别
		if(StringUtils.isNotBlank(voFact.getChargeTypeCd())){
			sql.append(" and bfc.CHARGE_TYPE_CD =:chargeTypeCd");
		}
		//如果有多经合同，则搜索多经合同内容
		if(StringUtils.isNotBlank(voFact.getBisContId())){
			sql.append(" and bfc.bis_Cont_Id =:bisContId");
		}else if(StringUtils.isNotBlank(voFact.getBisMultiId())){
			sql.append(" and bfc.bis_Multi_Id =:bisMultiId");
		}
		//年
		if(StringUtils.isNotBlank(voFact.getFactYear())){
			sql.append(" and bfc.must_year =:mustYear");
		}
		//月
		if(StringUtils.isNotBlank(voFact.getFactMonth())){
			sql.append(" and bfc.must_month =:mustMonth");
		}
		if (StringUtils.isNotBlank(voFact.getReportDateStart())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(bfc.must_year)||'-'||lpad(bfc.must_month,2,'0'))>=:reportDateStart");
        }
        if (StringUtils.isNotBlank(voFact.getReportDateEnd())) {
        	//由于实收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(bfc.must_year)||'-'||lpad(bfc.must_month,2,'0'))<=:reportDateEnd");
        }
		//高级搜索的实收年、月
		if(StringUtils.isNotBlank(voFact.getMinMonth())){
			sql.append(" and bfc.COLL_DATE >=to_date(:minMonth,'yyyy-MM-dd')");
		}else  if(StringUtils.isNotBlank(voFact.getMaxMonth())){
			sql.append(" and bfc.COLL_DATE <=to_date(:maxMonth,'yyyy-MM-dd')");
		}
		//创建人
		if(StringUtils.isNotBlank(voFact.getCreator())){
			sql.append(" and bfc.CREATOR =:creator");
		}
		//审核人
		if(StringUtils.isNotBlank(voFact.getCheckUserCd())){
			sql.append(" and bfc.CHECK_USER_CD =:checkUserCd");
		}
		//审核状态
		if (StringUtils.isNotBlank(voFact.getStatusCd())) {
			sql.append(" AND bfc.status_cd = :statusCd");
		}
		//实收金额
		if(voFact.getMinMoney()!=null){
			sql.append(" and bfc.money >=:minMoney");
		}
		if(voFact.getMaxMoney()!=null){
			sql.append(" and bfc.money<=:maxMoney");
		}
		if (StringUtils.isNotBlank(voFact.getStatusCd())) {
            sql.append(" and bfc.status_cd=:statusCd");
        }
		sql.append(")a where 1=1");
		
		//取与合同关联的多经应收收记录
    	param.put("bisProjectId", voFact.getBisProjectId());
		//类别
		if(StringUtils.isNotBlank(voFact.getChargeTypeCd())){
			param.put("chargeTypeCd", voFact.getChargeTypeCd());
		}
		//如果有多经合同，则搜索多经合同内容
		if(StringUtils.isNotBlank(voFact.getBisContId())){
			param.put("bisContId", voFact.getBisContId());
		}else if(StringUtils.isNotBlank(voFact.getBisMultiId())){
			param.put("bisMultiId", voFact.getBisMultiId());
		}
		//普通搜索里面的年月搜索
		if(StringUtils.isNotBlank(voFact.getFactYear())){
			param.put("mustYear", voFact.getFactYear());
		}
		//月
		if(StringUtils.isNotBlank(voFact.getFactMonth())){
			param.put("mustMonth", voFact.getFactMonth());
		}
		//高级搜索的实收年、月
		if(StringUtils.isNotBlank(voFact.getMinMonth())){
			param.put("minMonth", voFact.getMinMonth());
		}
		if(StringUtils.isNotBlank(voFact.getMaxMonth())){
			param.put("maxMonth", voFact.getMaxMonth());
		}
		//创建人
		if(StringUtils.isNotBlank(voFact.getCreator())){
			param.put("creator", voFact.getCreator());
		}
		//审核人
		if(StringUtils.isNotBlank(voFact.getCheckUserCd())){
			param.put("checkUserCd", voFact.getCheckUserCd());
		}
		if(StringUtils.isNotBlank(voFact.getStatusCd())){
			param.put("statusCd", voFact.getStatusCd());
		}
		//实收金额
		if(voFact.getMinMoney()!=null){
			param.put("minMoney", voFact.getMinMoney());
		}
		if(voFact.getMaxMoney()!=null){
			param.put("maxMoney", voFact.getMaxMoney());
		}
		if (StringUtils.isNotBlank(voFact.getStatusCd())) {
            param.put("statusCd", voFact.getStatusCd());
        }
		if("total".equals(type)){
			List<Object> list =this.findBySql(sql.toString(), param);
			if(list!=null&&list.size()>0&&list.get(0)!=null){
				BisMust must=new BisMust();
				must.setMoney(new BigDecimal(list.get(0).toString()));
				List<BisMust> mustList = new ArrayList<BisMust>();
				mustList.add(must);
				Page<BisMust> mustPage =new Page<BisMust>();
				mustPage.setResult(mustList);
				return mustPage;
			} else
				return null;
		}else{
			//年月从
			//取无合同的商铺应收记录
			Map<String, Class> mapClazz=new HashMap<String, Class>();
			mapClazz.put("bis", BisMust.class);
			Page<BisMust> mustPage =this.findPageSql(voPage, sql.toString(), param, mapClazz);
			return mustPage;
		}
	}
	
	public void batchDelete(Object[] ids) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ids", ids);
		String hql = "delete from BisMust where bisMustId in (:ids)";
		bisMustDao.batchExecute(hql, param);
	}
	/**
	 * 搜索商铺应收
	 * @param voFact
	 * @return
	 */
	public Page<BisMust> mustStoreBySql(Page voPage, VoFact voFact){
		Map<String, Object> param = new HashMap<String, Object>();
		//若跟合同关联，则取与合同关联的商铺实收记录
		StringBuffer sql = new StringBuffer("select fc.* from Bis_Must fc where fc.bis_Project_Id=:bisProjectId ");
		param.put("bisProjectId", voFact.getBisProjectId());
		sql.append(" and fc.bis_Tenant_Id is not null  and fc.bis_cont_id is not null ");//and fc.bis_shop_id is not null 
		//类别
		if(StringUtils.isNotBlank(voFact.getChargeTypeCd())){
			sql.append(" and fc.CHARGE_TYPE_CD =:chargeTypeCd");
			param.put("chargeTypeCd", voFact.getChargeTypeCd());
		}
		//如果合同，则搜索多经合同内容
		/*if(StringUtils.isNotBlank(voFact.getBisContId())){
			sql.append(" and fc.bis_Cont_Id =:bisContId");
			param.put("bisContId", voFact.getBisContId());
		}*/
		//租户
		if(StringUtils.isNotBlank(voFact.getBisTenantIds())){
			sql.append(" and fc.Bis_Tenant_Id in (");
			String[] tenantIds = voFact.getBisTenantIds().split(",");
			for(int i=0;i<tenantIds.length;i++){
				if(i==tenantIds.length-1){
					sql.append("'").append(tenantIds[i]).append("'");
				}else{
					sql.append("'").append(tenantIds[i]).append("'").append(",");
				}
			}
			sql.append(")");
		}
		//审核状态
		if (StringUtils.isNotBlank(voFact.getStatusCd())) {
			sql.append(" AND fc.status_cd = :statusCd");
			param.put("statusCd", voFact.getStatusCd());
		}
		/*//年
		if(StringUtils.isNotBlank(voFact.getFactYear())){
			sql.append(" and fc.must_year =:factYear");
			param.put("factYear", voFact.getFactYear());
		}
		//月
		if(StringUtils.isNotBlank(voFact.getFactMonth())){
			sql.append(" and fc.must_month =:factMonth");
			param.put("factMonth", voFact.getFactMonth());
		}*/
		
		//权责月份起止时间 add by zhengyi at 2012-06-29
        
        if (StringUtils.isNotBlank(voFact.getReportDateStart())) {
        	//由于权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.must_year)||'-'||lpad(fc.must_month,2,'0'))>=:reportDateStart");
        	param.put("reportDateStart", voFact.getReportDateStart());
        }
        if (StringUtils.isNotBlank(voFact.getReportDateEnd())) {
        	//由于权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.must_year)||'-'||lpad(fc.must_month,2,'0'))<=:reportDateEnd");
            param.put("reportDateEnd", voFact.getReportDateEnd());
        }
		
		
		//高级搜索的实收年、月
		if(StringUtils.isNotBlank(voFact.getMinMonth())){
			sql.append(" and fc.COLL_DATE >=to_date(:minMonth,'yyyy-MM-dd')");
			param.put("minMonth", voFact.getMinMonth());
		}
		if(StringUtils.isNotBlank(voFact.getMaxMonth())){
			sql.append(" and fc.COLL_DATE <=to_date(:maxMonth,'yyyy-MM-dd')");
			param.put("maxMonth",voFact.getMaxMonth());
		}
		//创建人
		if(StringUtils.isNotBlank(voFact.getCreator())){
			sql.append(" and fc.CREATOR =:creator");
			param.put("creator", voFact.getCreator());
		}
		//审核人
		if(StringUtils.isNotBlank(voFact.getCheckUserCd())){
			sql.append(" and fc.CHECK_USER_CD =:checkUserCd");
			param.put("checkUserCd", voFact.getCheckUserCd());
		}
		//实收金额
		if(voFact.getMinMoney()!=null){
			sql.append(" and fc.money >=:minMoney");
			param.put("minMoney", voFact.getMinMoney());
		}
		if(voFact.getMaxMoney()!=null){
			sql.append(" and fc.money<=:maxMoney");
			param.put("maxMoney", voFact.getMaxMoney());
		}
		//检索（全部，已收齐，未收齐）
		if (StringUtils.isNotBlank(voFact.getOverdue())) {
			String overDue = voFact.getOverdue();
			if (BIS_MUST_FULL_TYPE.equals(overDue)) {
				sql.append(" and fc.money <= fc.total_fact_money ");
			} else if (BIS_MUST_NON_FULL_TYPE.equals(overDue)) {
				sql.append(" and (fc.money > fc.total_fact_money or fc.total_fact_money is null) ");
			}
		}
		if(3==voFact.getMustOrFact()){
			//如果是欠费，搜索当前欠费的记录
			sql.append(" and fc.status_cd = '2' and fc.COLL_DATE <=to_date(:nowdate,'yyyy-MM-dd')");
			param.put("nowdate",DateOperator.formatDate(DateOperator.getDateNow(), "yyyy-MM-dd"));
		}else if(2==voFact.getMustOrFact()){
			//如果是应收，默认提前一个月
			/*
			sql.append(" and fc.COLL_DATE <=to_date(:nowdate,'yyyy-MM-dd')");
			Calendar calendarIn = Calendar.getInstance();
			calendarIn.setTime(new Date());
			calendarIn.roll(Calendar.MONTH,+1);
			param.put("nowdate",DateOperator.formatDate(calendarIn.getTime(), "yyyy-MM-dd"));
			*/
		}
		sql.append(" order by fc.must_year,fc.must_month,fc.charge_type_cd desc");
		Map<String, Class> mapClazz=new HashMap<String, Class>();
		mapClazz.put("bis", BisMust.class);
		Page<BisMust> factPage =this.findPageSql(voPage, sql.toString(), param, mapClazz);
		return factPage;
	}
	//搜索应收的TOTAL值
   public BigDecimal mustMoneySum(VoFact voFact){
	   Map<String, Object> param = new HashMap<String, Object>();
	   StringBuffer sql = new StringBuffer("select sum(fc.money) from Bis_Must fc where fc.bis_Project_Id=:bisProjectId ");
		param.put("bisProjectId", voFact.getBisProjectId());
		sql.append(" and fc.bis_Tenant_Id is not null  and fc.bis_cont_id is not null");
    	 //类别
		if(StringUtils.isNotBlank(voFact.getChargeTypeCd())){
			sql.append(" and fc.CHARGE_TYPE_CD =:chargeTypeCd");
			param.put("chargeTypeCd", voFact.getChargeTypeCd());
		}
		if(StringUtils.isNotBlank(voFact.getStatusCd())){
			sql.append(" and fc.STATUS_CD =:statusCd");
			param.put("statusCd", voFact.getStatusCd());
		}
		/*//年
		if(StringUtils.isNotBlank(voFact.getFactYear())){
			sql.append(" and fc.must_year =:factYear");
			param.put("factYear", voFact.getFactYear());
		}
		//月
		if(StringUtils.isNotBlank(voFact.getFactMonth())){
			sql.append(" and fc.must_month =:factMonth");
			param.put("factMonth", voFact.getFactMonth());
		}*/
		
		//应收权责年月起止时间 add by liuzhihui at 2012-07-17
		if (StringUtils.isNotBlank(voFact.getReportDateStart())) {
        	//由于应收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.must_year)||'-'||lpad(fc.must_month,2,'0'))>=:reportDateStart");
        	param.put("reportDateStart", voFact.getReportDateStart());
        }
        if (StringUtils.isNotBlank(voFact.getReportDateEnd())) {
        	//由于应收权责年月在设计时是分开的，所以处理起来比较麻烦需要先合并年月
        	sql.append(" and (ltrim(fc.must_year)||'-'||lpad(fc.must_month,2,'0'))<=:reportDateEnd");
            param.put("reportDateEnd", voFact.getReportDateEnd());
        }
		
		//租户
		if(StringUtils.isNotBlank(voFact.getBisTenantIds())){
			sql.append(" and fc.Bis_Tenant_Id in (");
			String[] tenantIds = voFact.getBisTenantIds().split(",");
			for(int i=0;i<tenantIds.length;i++){
				if(i==tenantIds.length-1){
					sql.append("'").append(tenantIds[i]).append("'");
				}else{
					sql.append("'").append(tenantIds[i]).append("'").append(",");
				}
			}
			sql.append(")");
		}
		//高级搜索的实收年、月
		if(StringUtils.isNotBlank(voFact.getMinMonth())){
			sql.append(" and fc.COLL_DATE >=to_date(:minMonth,'yyyy-MM-dd')");
			param.put("minMonth", voFact.getMinMonth());
		}
		if(StringUtils.isNotBlank(voFact.getMaxMonth())){
			sql.append(" and fc.COLL_DATE <=to_date(:maxMonth,'yyyy-MM-dd')");
			param.put("maxMonth", voFact.getMaxMonth());
		}
		//创建人
		if(StringUtils.isNotBlank(voFact.getCreator())){
			sql.append(" and fc.CREATOR =:creator");
			param.put("creator", voFact.getCreator());
		}
		//审核人
		if(StringUtils.isNotBlank(voFact.getCheckUserCd())){
			sql.append(" and fc.CHECK_USER_CD =:checkUserCd");
			param.put("checkUserCd", voFact.getCheckUserCd());
		}
		//实收金额
		if(voFact.getMinMoney()!=null){
			sql.append(" and fc.money >=:minMoney");
			param.put("minMoney", voFact.getMinMoney());
		}
		if(voFact.getMaxMoney()!=null){
			sql.append(" and fc.money<=:maxMoney");
			param.put("maxMoney", voFact.getMaxMoney());
		}
	    List<Object> list = this.findBySql(sql.toString(), param);
	    if(list!=null&&list.size()>0&&list.get(0)!=null)
			return new BigDecimal(list.get(0).toString());
		else
			return new BigDecimal(0);
   }
	   
   
   /**
    * @author wangming
    *  获取应收金额
    *  1. 必须是审核通过
    *  2. 唯一主键：商家名 + 费用类别 + 年份 + 月份
    * @param voFact
    * @return
    */
   public BisMust getBisMustMoney(VoFact voFact){
	   	Map<String, Object> param = new HashMap<String, Object>();
	   	StringBuffer sql = new StringBuffer(" from BisMust fc where fc.bisProjectId=:bisProjectId ");
		param.put("bisProjectId", voFact.getBisProjectId());
		sql.append(" and fc.bisTenantId is not null  and fc.bisCont.bisContId is not null");
		sql.append(" and fc.statusCd =2 "); //审核通过
		//商家名
		if(StringUtils.isNotBlank(voFact.getBisTenantId())){
			sql.append(" and fc.bisTenantId =:bisTenantId");
			param.put("bisTenantId", voFact.getBisTenantId());
		}
		//类别
		if(StringUtils.isNotBlank(voFact.getChargeTypeCd())){
			sql.append(" and fc.chargeTypeCd =:chargeTypeCd");
			param.put("chargeTypeCd", voFact.getChargeTypeCd());
		}
		//年
		if(StringUtils.isNotBlank(voFact.getFactYear())){
			sql.append(" and fc.mustYear =:factYear");
			param.put("factYear", voFact.getFactYear());
		}
		//月
		if(StringUtils.isNotBlank(voFact.getFactMonth())){
			sql.append(" and fc.mustMonth =:factMonth");
			param.put("factMonth", voFact.getFactMonth());
		}
		List<BisMust> bisMust = this.getDao().find(sql.toString(), param);
		if(null !=bisMust && bisMust.size()>0)
			return (BisMust)bisMust.get(0);
		else
			return new BisMust();
   }
   
   
   /**
    * @author wangming
    *  更新应收金额
    *  1. 必须是审核通过
    *  2. 唯一主键：商家名 + 费用类别 + 年份 + 月份
    * @param voFact
    * @return
    */
   public void updateBisMustMoney(VoFact voFact){
	   	Map<String, Object> param = new HashMap<String, Object>();
	   	StringBuffer sql = new StringBuffer("update Bis_Must fc set fc.TOTAL_FACT_MONEY =:money  where fc.bis_Project_Id=:bisProjectId ");
	   	param.put("money", voFact.getMoney()==null?"":voFact.getMoney());
		param.put("bisProjectId", voFact.getBisProjectId());
		sql.append(" and fc.bis_Tenant_Id is not null  and fc.bis_cont_id is not null");
		sql.append(" and fc.status_cd =2 "); //审核通过
		//商家名
		if(StringUtils.isNotBlank(voFact.getBisTenantId())){
			sql.append(" and fc.bis_Tenant_Id =:bisTenantId");
			param.put("bisTenantId", voFact.getBisTenantId());
		}
		//类别
		if(StringUtils.isNotBlank(voFact.getChargeTypeCd())){
			sql.append(" and fc.CHARGE_TYPE_CD =:chargeTypeCd");
			param.put("chargeTypeCd", voFact.getChargeTypeCd());
		}
		//年
		if(StringUtils.isNotBlank(voFact.getFactYear())){
			sql.append(" and fc.must_year =:factYear");
			param.put("factYear", voFact.getFactYear());
		}
		//月
		if(StringUtils.isNotBlank(voFact.getFactMonth())){
			sql.append(" and fc.must_month =:factMonth");
			param.put("factMonth", voFact.getFactMonth());
		}
		Query query = this.getDao().createSQLQuery(sql.toString(), param);
		query.executeUpdate();
   }
}

