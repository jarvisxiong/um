package com.hhz.ump.dao.prod;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.prod.ProdVersionDetail;

@Service
@Transactional
public class ProdVersionDetailManager extends BaseService<ProdVersionDetail, String> {
	@Autowired
	private ProdVersionDetailDao prodVersionDetailDao;

	public void saveProdVersionDetail(ProdVersionDetail prodVersionDetail) {
		PowerUtils.setEmptyStr2Null(prodVersionDetail);
		//搜索原有的基准估算值
		if(prodVersionDetail.getProdBasicVersion()!=null&&prodVersionDetail.getEstimatePrice()!=null) {
			updateEstimatePrice(prodVersionDetail.getProdBasicVersion().getYearCd(),
					prodVersionDetail.getProdBasicVersion().getMonthCd(),prodVersionDetail.getBussinessCd(),prodVersionDetail.getEstimatePrice());
		}
		prodVersionDetailDao.save(prodVersionDetail);
	}
	
	public void findDiffrentEstimatePrice(String yearCd,String monthCd,String bussinessCd){
		// 搜索条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("yearCd", yearCd);
		map.put("monthCd", monthCd);
		map.put("bussinessCd", bussinessCd);
		StringBuffer sb=new StringBuffer();
		sb.append(" from ProdVersionDetail pvd where pvd.prodBasicVersion.yearCd = :yearCd")
		.append(" and pvd.prodBasicVersion.monthCd = :monthCd")
		.append(" and pvd.bussinessCd = :bussinessCd");
		
	}
	
	public void updateEstimatePrice(String yearCd,String monthCd,String bussinessCd,BigDecimal estimatePrice){
		// 搜索条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("yearCd", yearCd);
		map.put("monthCd", monthCd);
		map.put("bussinessCd", bussinessCd);
		map.put("estimatePrice", estimatePrice);
		
		StringBuffer sbSql=new StringBuffer();
		sbSql.append("update prod_version_detail  pvd set pvd.estimate_price= :estimatePrice ")
		.append(" where pvd.prod_version_detail_id in (")
		.append(" select  pd.prod_version_detail_id from prod_version_detail pd join prod_basic_version pv ")
		.append("  on pv.prod_basic_version_id=pd.prod_basic_version_id ")
		.append(" where pd.bussiness_cd = :bussinessCd and pv.year_cd = :yearCd and pv.month_cd = :monthCd )");
//		Session s=prodVersionDetailDao.getSession();
//		Transaction t = s.getTransaction();
//		t.begin();
		prodVersionDetailDao.createSQLQuery(sbSql.toString(), map).executeUpdate();
		
	}
	public void deleteProdVersionDetail(String id) {
		prodVersionDetailDao.delete(id);
	}

	@Override
	public HibernateDao<ProdVersionDetail, String> getDao() {
		return prodVersionDetailDao;
	}

	/**
	 * 
	 * getActiveProdVersionDetail:(搜索) 
	 *  取消地区
	 * @param  @param prodBasicVersionId
	 * @param  @param bussinessCd
	 * @param  @param materialZoneCd
	 * @param  @param areaCd
	 * @param  @return    设定文件  
	 * @return ProdVersionDetail    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public ProdVersionDetail getActiveProdVersionDetail(String prodBasicVersionId, String bussinessCd,
			String materialZoneCd) {
		// 搜索条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("active", ProdBasicVersionManager.ACTIVE_YES);
		map.put("prodBasicVersionId", prodBasicVersionId);
		map.put("bussinessCd", bussinessCd);
		map.put("materialZoneCd", materialZoneCd);
		//取消地区
		//map.put("areaCd", areaCd);

		// 搜索语句
		StringBuffer sbSql = new StringBuffer().append(" from ProdVersionDetail pd where pd.active = :active").append(
				" and pd.prodBasicVersion.prodBasicVersionId = :prodBasicVersionId").append(
				" and pd.bussinessCd = :bussinessCd").append(" and pd.materialZoneCd = :materialZoneCd");
				//.append(" and pd.areaCd = :areaCd");

		List<ProdVersionDetail> rs = prodVersionDetailDao.find(sbSql.toString(), map);
		if (rs != null && !rs.isEmpty())
			return rs.get(0);
		else
			return null;
	}
	
	
	public ProdVersionDetail getProdVersionDetailById(String prodVersionDetailId) {
		// 搜索条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("prodVersionDetailId", prodVersionDetailId);
		// 搜索语句
		StringBuffer sbSql = new StringBuffer().append(" from ProdVersionDetail pd where pd.prodVersionDetailId = :prodVersionDetailId");
		List<ProdVersionDetail> rs = prodVersionDetailDao.find(sbSql.toString(), map);
		if (rs != null && !rs.isEmpty())
			return rs.get(0);
		else
			return null;
	}

	public ProdVersionDetail findProdVersionDetail(Map<String, Object> map,String active) {
		//构建搜索语句
		StringBuffer sbHql=new StringBuffer()
		.append(" from ProdVersionDetail pd where pd.bussinessCd = :bussinessCd");
		if(map.get("materialZoneCd")!=null){
			sbHql.append(" and pd.materialZoneCd = :materialZoneCd");
		}		
		if(map.get("yearCd")!=null){
			sbHql.append(" and pd.prodBasicVersion.yearCd = :yearCd");
		}			
		if(map.get("monthCd")!=null) {
			sbHql.append(" and pd.prodBasicVersion.monthCd = :monthCd");		
		}
		if(map.get("prodBasicVersionId")!=null) {
			sbHql.append(" and pd.prodBasicVersion.prodBasicVersionId = :prodBasicVersionId");		
		}
		if(map.get("areaCd")!=null){
			sbHql.append(" and pd.areaCd = :areaCd");
		}		
		if(StringUtils.isNotBlank(active)){
			sbHql.append(" and pd.prodBasicVersion.active = '1'");
		}
		//执行搜索
		List<ProdVersionDetail> prodVersionDetails=prodVersionDetailDao.find(sbHql.toString(), map);
		if(prodVersionDetails!=null&&prodVersionDetails.size()>0)
			return prodVersionDetails.get(0);
		else
			return null;
		
		
	}

}
