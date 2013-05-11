package com.hhz.ump.dao.bis;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisStoreContRel;
import com.hhz.ump.entity.bis.BisTenant;
import com.hhz.ump.entity.bis.ShopStoreVo;
import com.hhz.ump.util.BisShopTenantUtil;
import com.hhz.ump.util.TenantTypeReportUtil;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.vo.BisArrearsRemindVo;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class BisTenantManager extends BaseService<BisTenant, String> {
	@Autowired
	private BisTenantDao bisTenantDao;
	@Autowired
	private BisStoreContRelDao bisStoreContRelDao;
	
	private static Log log = LogFactory.getLog(BisTenantManager.class);
	public void saveBisTenant(BisTenant bisTenant) {
		PowerUtils.setEmptyStr2Null(bisTenant);
		bisTenantDao.save(bisTenant);
	}

	public void deleteBisTenant(String id) {
		bisTenantDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisTenant, String> getDao() {
		return bisTenantDao;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public Map<String, String> getMapBisTenant() {
		Map<String, String> mapBisTenant = new LinkedHashMap<String, String>();
		mapBisTenant.put("", "--选择--");
		String hql = "from BisTenant";
		List<BisTenant> list = this.getDao().find(hql);
		for(BisTenant bisProject : list){
			mapBisTenant.put(bisProject.getBisTenantId(), bisProject.getConnName());
		}
		return mapBisTenant;
	}
	public Map<String, String> getMapBisTenant(List<BisTenant> tenants) {
		Map<String, String> mapBisTenant = new LinkedHashMap<String, String>();
		mapBisTenant.put("", "--选择--");
		for(BisTenant bisProject : tenants){
			mapBisTenant.put(bisProject.getBisTenantId(), bisProject.getConnName());
		}
		return mapBisTenant;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<BisTenant> getBisTenantList(String bisProjectId) {
		List<BisTenant> result = this.getDao().createCriteria(BisTenant.class).add(Restrictions.eq("bisProjectId", bisProjectId)).list();
		if (result == null)
			return new ArrayList<BisTenant>();
		else
			return result;
	}
	public Map<String, String> getBisTenantMap(String bisProjectId) {
		return getMapBisTenant(getBisTenantList(bisProjectId));
	}
	/**
	 * 搜索商铺报表
	 * @return
	 */
	public List<BisShopTenantUtil> queryShopReport(String bisProjectId,String mustYear,int mustMonth,String factYear,int factMonth,String chargeTypeCd,String manageCd){
		StringBuffer sql =new StringBuffer("");
		Map<String, Object> params = new HashMap<String, Object>();
		//搜索结果内容：租户ID、商铺名称、商铺面积、年实收/应收比较值、月实收/应收比较值、年欠费值、月欠费值、
		//以前年度欠费、费用类别
		sql.append("select A.BIS_TENANT_ID,a.NAME_CN,a.square," +
				"(nvl(d.fact_year_money,0)||'/'||nvl(B.must_Year_Money,0)) as year_money_rel," +
				"(nvl(e.fact_month_money,0)||'/'||nvl(c.must_Month_Money,0)) as month_money_rel," +
				"(nvl(B.must_Year_Money, 0) - nvl(d.fact_year_money, 0))  as year_money_sub," +
				"(nvl(c.must_Month_Money, 0) - nvl(e.fact_month_money, 0))  as month_money_sub," +
				"(nvl(f.bef_must_year, 0) - nvl(g.bef_fact_year, 0))  as bef_year_money," +
				"b.CHARGE_TYPE_CD as charge_type_cd");
		sql.append(" from");
		//租户表
		sql.append(" (select squa.BIS_TENANT_ID,squa.NAME_CN,sum(stor.square) square from " +
				"  (select BT.BIS_TENANT_ID,BT.CONN_NAME ,bsc.bis_store_id,SHOP.NAME_CN " +
				"from bis_tenant bt join bis_cont bc on bt.bis_tenant_id=bc.bis_tenant_id" +
				" join bis_store_cont_rel bsc on bc.bis_cont_id=bsc.bis_cont_id,bis_shop shop" +
				" where BT.BIS_SHOP_ID=SHOP.BIS_SHOP_ID");
		if(StringUtils.isNotBlank(bisProjectId)){
			sql.append(" and bt.bis_project_id='").append(bisProjectId).append("'") ;
		}
		if(StringUtils.isNotBlank(manageCd)){
			sql.append(" and bt.manage_cd='").append(manageCd).append("'") ;
		}
		sql.append(" ) squa join bis_store stor on squa.bis_store_id=stor.bis_store_id" +
				" group by squa.BIS_TENANT_ID,squa.NAME_CN) a left join");
		//本年应收
		sql.append(" (select SUM(MUST.MONEY) as must_Year_Money, MUST.BIS_TENANT_ID,MUST.CHARGE_TYPE_CD" +
				" from bis_must must WHERE 1=1");
			if(StringUtils.isNotBlank(mustYear)){
				sql.append(" and MUST.MUST_YEAR='").append(mustYear).append("'");
			}
			if(mustMonth!=0){
				sql.append(" and MUST.MUST_MONTH<").append(mustMonth);
			}
			if(StringUtils.isNotBlank(chargeTypeCd)){
				sql.append(" and MUST.CHARGE_TYPE_CD IN(").append(chargeTypeCd).append(")");
			}
		sql.append(" GROUP BY MUST.BIS_TENANT_ID,MUST.CHARGE_TYPE_CD) b on  B.BIS_TENANT_ID=A.BIS_TENANT_ID left join");
		//本月应收
		sql.append(" (select SUM(MUST.MONEY) as must_Month_Money, MUST.BIS_TENANT_ID,MUST.CHARGE_TYPE_CD" +
				" from bis_must must WHERE 1=1");
			if(StringUtils.isNotBlank(mustYear)){
				sql.append(" and MUST.MUST_YEAR='").append(mustYear).append("'");
			}
			if(mustMonth!=0){
				sql.append(" and MUST.MUST_MONTH=").append(mustMonth);
			}
			if(StringUtils.isNotBlank(chargeTypeCd)){
				sql.append(" and MUST.CHARGE_TYPE_CD IN(").append(chargeTypeCd).append(")");
			}
		sql.append(" GROUP BY MUST.BIS_TENANT_ID,MUST.CHARGE_TYPE_CD) c" +
				" on C.BIS_TENANT_ID =A.BIS_TENANT_ID and c.CHARGE_TYPE_CD =B.CHARGE_TYPE_CD left join");
		//本年实收
		sql.append(" (select sum(FACT.MONEY) as fact_year_money,FACT.BIS_TENANT_ID,FACT.CHARGE_TYPE_CD" +
				" from bis_fact fact WHERE 1=1");
			if(StringUtils.isNotBlank(factYear)){
				sql.append(" and FACT.FACT_YEAR='").append(factYear).append("'");
			}
			if(factMonth!=0){
				sql.append(" and to_number(decode(FACT.fact_month,' ','13',null,'13',FACT.fact_month)) < ").append(factMonth);
			}
			if(StringUtils.isNotBlank(chargeTypeCd)){
				sql.append(" and FACT.CHARGE_TYPE_CD IN(").append(chargeTypeCd).append(")");
			}
		sql.append(" GROUP BY fact.BIS_TENANT_ID,FACT.CHARGE_TYPE_CD ) d" +
				" on d.BIS_TENANT_ID=C.BIS_TENANT_ID and d.CHARGE_TYPE_CD =B.CHARGE_TYPE_CD left join");
		//本月实收
		sql.append(" (select sum(FACT.MONEY) as fact_month_money,FACT.BIS_TENANT_ID,FACT.CHARGE_TYPE_CD" +
				" from bis_fact fact WHERE 1=1");
			if(StringUtils.isNotBlank(factYear)){
				sql.append(" and FACT.FACT_YEAR='").append(factYear).append("'");
			}
			if(factMonth!=0){
				sql.append(" and FACT.FACT_MONTH='").append(factMonth).append("'");
			}
			if(StringUtils.isNotBlank(chargeTypeCd)){
				sql.append(" and FACT.CHARGE_TYPE_CD IN(").append(chargeTypeCd).append(")");
			}
		sql.append(" GROUP BY fact.BIS_TENANT_ID,FACT.CHARGE_TYPE_CD) e " +
				"on e.BIS_TENANT_ID=d.BIS_TENANT_ID and  e.CHARGE_TYPE_CD =B.CHARGE_TYPE_CD left join");
		//以前年度应收情况
		sql.append(" (select sum(MUST.MONEY) as bef_must_year,MUST.BIS_TENANT_ID,must.CHARGE_TYPE_CD" +
				" from bis_must must where 1=1");
		if(StringUtils.isNotBlank(mustYear)){
			sql.append(" and MUST.MUST_YEAR<'").append(mustYear).append("'");
		}
		if(StringUtils.isNotBlank(chargeTypeCd)){
			sql.append(" and MUST.CHARGE_TYPE_CD IN(").append(chargeTypeCd).append(")");
		}
		sql.append(" GROUP BY MUST.BIS_TENANT_ID,must.CHARGE_TYPE_CD) f" +
				" on f.BIS_TENANT_ID=A.BIS_TENANT_ID and f.CHARGE_TYPE_CD =B.CHARGE_TYPE_CD left join");
		//以前年度实收情况
		sql.append(" (select sum(FACT.MONEY) as bef_fact_year,FACT.BIS_TENANT_ID,FACT.CHARGE_TYPE_CD" +
				" from bis_fact fact where 1=1");
			if(StringUtils.isNotBlank(factYear)){
				sql.append(" and FACT.FACT_YEAR='").append(factYear).append("'");
			}
		sql.append(" GROUP BY fact.BIS_TENANT_ID,FACT.CHARGE_TYPE_CD) g " +
				"on g.BIS_TENANT_ID=A.BIS_TENANT_ID and  g.CHARGE_TYPE_CD =B.CHARGE_TYPE_CD");
		//排序
		sql.append(" order by charge_type_cd");
		List list = bisTenantDao.findBySql(sql.toString(), params);
		
		 //搜索租户对应的商铺
		StringBuffer storeSql =new StringBuffer("");
		storeSql.append(" select cont.BIS_TENANT_ID,STOR.STORE_NO" +
				" from bis_cont cont,bis_store_cont_rel rel,bis_store stor" +
				" where REL.BIS_CONT_ID =CONT.BIS_CONT_ID " +
				" and REL.BIS_STORE_ID =STOR.BIS_STORE_ID ");
		if(StringUtils.isNotBlank(bisProjectId)){
			storeSql.append(" and cont.bis_project_id='").append(bisProjectId).append("'") ;
		}
		storeSql.append(" order by cont.BIS_TENANT_ID ");
		
		List storeList =bisTenantDao.findBySql(storeSql.toString(), params);
		
		//对两个List拼装
		List<BisShopTenantUtil> tenantUtils = new ArrayList<BisShopTenantUtil>();
		String tenantId="";
		if(list!=null&&list.size()>0&&storeList!=null&&storeList.size()>0){
			for(int i=0;i<list.size();i++){
				Object[] obj = (Object[]) list.get(i);
				if(tenantId.equals(obj[0])){
					//若没新增租户则新增一条经营类型记录
					TenantTypeReportUtil typeUtil = new TenantTypeReportUtil();
					typeUtil.setYearMoneyRel((String)obj[3]);
					typeUtil.setMonthMoneyRel((String)obj[4]);
					typeUtil.setYearMoneySub((BigDecimal)obj[5]);
					typeUtil.setMonthMoneySub((BigDecimal)obj[6]);
					typeUtil.setBefYearMoney((BigDecimal)obj[7]);
					if(obj[8]==null){
						typeUtil.setChargeTypeCdRel("00");
					}else{
						typeUtil.setChargeTypeCdRel((String)obj[8]);
					}
					tenantUtils.get(tenantUtils.size()-1).getTenantTypeReportUtils().add(typeUtil);
				}else{
					//租户ID、商铺名称、商铺面积、年实收/应收比较值、月实收/应收比较值、年欠费值、月欠费值、
					tenantId =(String)obj[0];
					BisShopTenantUtil  shopUtil = new BisShopTenantUtil();
					StringBuffer storeName=new StringBuffer("");
					//新增商铺
					boolean haveStore =false;
					for(Iterator<Object[]> it = storeList.iterator(); it.hasNext();){
						Object[] objStore = (Object[])it.next();
						if(tenantId.equals(objStore[0])){
							storeName.append(objStore[1]).append(",");
							haveStore =true;
							it.remove();
						}else if(haveStore){
							break;
						}
					}
					if(StringUtils.isNotBlank(storeName.toString())) {
						shopUtil.setStoreName(storeName.toString().substring(0,storeName.toString().length()-1));
					}
					shopUtil.setTentantId(tenantId);
					shopUtil.setTentantName((String)obj[1]);
					shopUtil.setShopArea((BigDecimal)obj[2]);
					TenantTypeReportUtil typeUtil = new TenantTypeReportUtil();
					typeUtil.setYearMoneyRel((String)obj[3]);
					typeUtil.setMonthMoneyRel((String)obj[4]);
					typeUtil.setYearMoneySub((BigDecimal)obj[5]);
					typeUtil.setMonthMoneySub((BigDecimal)obj[6]);
					typeUtil.setBefYearMoney((BigDecimal)obj[7]);
					if(obj[8]==null){
						typeUtil.setChargeTypeCdRel("00");
					}else{
						typeUtil.setChargeTypeCdRel((String)obj[8]);
					}
					List<TenantTypeReportUtil> typeUtils =new ArrayList<TenantTypeReportUtil>();
					typeUtils.add(typeUtil);
					shopUtil.setTenantTypeReportUtils(typeUtils);
					tenantUtils.add(shopUtil);
				}
			}
		}
		return tenantUtils;
	}
	

	public List<Object[]> getTenantList(String bisProjectId, String bisFloorId, String bisTenantId, String storeShopNo) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", bisProjectId);
		param.put("bisFloorId", bisFloorId);
		param.put("bisTenantId", bisTenantId);
		param.put("storeShopNo", "'%"+storeShopNo+"%'");
		String sql = buildGetTenantSql(bisProjectId, bisFloorId, null, null);
		
		return getDao().findBySql(sql, param);
	}
	/**
	 * 
	 * Description：搜索项目所有组合
	 */
	public List<ShopStoreVo> getTenants(String bisProjectId) {
		return getTenants(bisProjectId, null, null,null,null,"1","0");
	}
	/**
	 * 
	 * Description搜索合同有效租户或全部
	 * @param	contActive	0	全部合同	1生效合同
	 */
	public List<ShopStoreVo> getTenants(String bisProjectId,String contActive) {
		return getTenants(bisProjectId, null, null,null,null,"1",contActive);
	}
	public List<ShopStoreVo> getTenants(String bisProjectId,String name,String storeNo ) {
		return getTenants(bisProjectId, name, storeNo,null,null,null,"0");
	}
	public List<ShopStoreVo> getTenants(String bisProjectId,String name,String storeNo,String bisFloorId) {
		return getTenants(bisProjectId, name, storeNo,bisFloorId,null,null,"0");
	}
	/**
	 * 
	 * Description:搜索项目租户
	 * @param bisProjectId	项目
	 * @param name			商家或业主名称
	 * @param storeNo		商铺编号
	 * @param bisFloorId	楼层
	 * @param pageSize		
	 * @param isIchnography	是否在平面图显示
	 * @param contActive	合同是否生效  	0	全部合同   1	有效合同
	 * @return
	 * List<ShopStoreVo>
	 */
	
	public List<ShopStoreVo> getTenants(String bisProjectId, String name, String storeNo,String bisFloorId,String pageSize,String isIchnography,String contActive ) {
		
			Map<Integer, Object>  param = new HashMap<Integer, Object>();
			param.put(1, bisProjectId);
			param.put(2, name);
			param.put(3, storeNo);
			param.put(4, bisFloorId);
			param.put(5, contActive);
			/*if(pageSize!=null) {
				param.put(5, Integer.valueOf(pageSize));
			}else{
				param.put(5, 10);
				
			}*/
			//param.put(5, isIchnography);
		return (List<ShopStoreVo>)
			this.executeFunction("{ call BIS_FACT_PKG.BIS_TENANT_DETAIL(?,?,?,?,?,?)}",param,ShopStoreVo.class);
	}
	
	/**
	 * 商家--商铺 搜索语句；
	 */
	private String buildGetTenantSql(String bisProjectId, String bisFloorId, String bisTenantId, String storeShopNo){
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.bis_shop_id,a.bis_tenant_id,a.name_en,a.name_cn,a.manage_cd,a.shop_type_cd,c.cont_start_date,c.cont_end_date,c.square,b.store_no,NVL(a.name_cn,a.name_en) ");
		sql.append(" from ( ");
		sql.append(" select bs.bis_shop_id,bt.bis_tenant_id,bs.name_en,bs.name_cn,bs.manage_cd,bs.shop_type_cd,bs.company_name ");
		sql.append(" from bis_tenant bt join bis_shop bs on bt.bis_shop_id=bs.bis_shop_id where 1=1 ");
		if(StringUtils.isNotBlank(bisProjectId)){
			sql.append(" and bt.bis_project_id = :bisProjectId");
		}
		if(StringUtils.isNotBlank(bisTenantId)){
			sql.append(" and bt.bis_tenant_id = :bisTenantId");
		}
		sql.append(" and exists ");
		sql.append("    (select 1 from bis_cont bc ");
		sql.append("    join bis_store_cont_rel scr on bc.bis_cont_id=scr.bis_cont_id ");
		sql.append("    join bis_store bst on scr.bis_store_id=bst.bis_store_id ");
		sql.append("    where bc.bis_tenant_id=bt.bis_tenant_id ");
		 if(StringUtils.isNotBlank(bisProjectId)){
				sql.append(" and bc.bis_project_id = :bisProjectId");
			}	 
		if(StringUtils.isNotBlank(bisFloorId)){
			sql.append(" and bst.bis_floor_id = :bisFloorId");	
		}
		sql.append(" ) ");
		if(StringUtils.isNotBlank(storeShopNo)){
			sql.append(" and bs.name_cn  like :storeShopNo");	
		}
		sql.append(" or exists ");
		sql.append("    (select 1 from bis_cont bc ");
		sql.append("    join bis_store_cont_rel scr on bc.bis_cont_id=scr.bis_cont_id ");
		sql.append("    join bis_store bst on scr.bis_store_id=bst.bis_store_id ");
		sql.append("    where bc.bis_tenant_id=bt.bis_tenant_id ");
		if(StringUtils.isNotBlank(bisProjectId)){
			sql.append(" and bc.bis_project_id = :bisProjectId");
		}	
		if(StringUtils.isNotBlank(bisFloorId)){
			sql.append(" and bst.bis_floor_id = :bisFloorId");
		}
		if(StringUtils.isNotBlank(storeShopNo)){
			sql.append(" and bst.store_no like :storeShopNo");
		}
		sql.append(" ) ");
		sql.append(") a left join ");
		sql.append(" (SELECT c.bis_tenant_id, TRANSLATE(LTRIM(c.text, '/'), '*/', '*,') store_no ");
		sql.append("  from ");
		sql.append("  (  ");
		sql.append("    SELECT ROW_NUMBER() OVER(PARTITION BY b.bis_tenant_id ORDER BY b.bis_tenant_id, num DESC) rn, ");
		sql.append("           b.bis_tenant_id, ");
		sql.append("           b.text ");
		sql.append("      from  ");
		sql.append("      ( ");
		sql.append("        select a.bis_tenant_id, ");
		sql.append("               level num,  ");
		sql.append("               SYS_CONNECT_BY_PATH(a.store_no, '/') text  ");
		sql.append("          from ");
		sql.append("          (   ");
		sql.append("            select bc.bis_tenant_id, ");
		sql.append("                   bs.store_no,");
		sql.append("                   ROW_NUMBER() OVER(PARTITION BY bc.bis_tenant_id ORDER BY bc.bis_tenant_id, bs.store_no) x");
		sql.append("              from bis_cont bc join bis_store_cont_rel scr on bc.bis_cont_id=scr.bis_cont_id join bis_store bs on scr.bis_store_id=bs.bis_store_id");
		sql.append("              group by bc.bis_tenant_id, bs.store_no ");
		sql.append("          ) a ");
		sql.append("          CONNECT BY a.bis_tenant_id = PRIOR a.bis_tenant_id AND a.x - 1 = PRIOR a.x ");
		sql.append("      ) b");
		sql.append("  ) c ");
		sql.append(" WHERE c.rn = 1 ");
		sql.append(" ORDER BY c.bis_tenant_id ");
		sql.append(" ) b on a.bis_tenant_id=b.bis_tenant_id  ");
		sql.append(" left join (  ");
		sql.append("            select bc.bis_tenant_id, sum(bs.square) as square, min(bc.cont_start_date) as cont_start_date, max(bc.cont_end_date) as cont_end_date  ");
		sql.append("            from bis_cont bc join bis_store_cont_rel scr on bc.bis_cont_id=scr.bis_cont_id join bis_store bs on scr.bis_store_id=bs.bis_store_id   ");
		sql.append("            group by bc.bis_tenant_id ");
		sql.append(") c on a.bis_tenant_id=c.bis_tenant_id ");
		sql.append(" where c.cont_start_date<=sysdate and c.cont_end_date>=sysdate");
		sql.append(" order by c.square desc");
		return sql.toString();
	}
	
	public String getStoreNosByTenant(String bisTenantId) {
		
		if(StringUtils.isBlank(bisTenantId))
			return "";
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisTenantId", bisTenantId);
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select TRANSLATE(LTRIM(c.text, '/'), '*/', '*,') store_no")
			.append(" from (")
				.append(" select ROW_NUMBER() OVER(PARTITION BY b.bis_tenant_id ORDER BY b.bis_tenant_id, num DESC) rn,b.bis_tenant_id,b.text")
				.append(" from (")
					.append(" select a.bis_tenant_id,level num,SYS_CONNECT_BY_PATH(a.store_no, '/') text")
					.append(" from (")
						.append(" select bc.bis_tenant_id,bs.store_no,ROW_NUMBER() OVER(PARTITION BY bc.bis_tenant_id ORDER BY bc.bis_tenant_id, bs.store_no) x")
						.append(" from bis_cont bc join bis_store_cont_rel scr on bc.bis_cont_id=scr.bis_cont_id join bis_store bs on scr.bis_store_id=bs.bis_store_id")
						.append(" where bc.bis_tenant_id=:bisTenantId")
						.append(" group by bc.bis_tenant_id, bs.store_no")
					.append(" ) a CONNECT BY a.bis_tenant_id = PRIOR a.bis_tenant_id AND a.x - 1 = PRIOR a.x")
				.append(" ) b")
			.append(" ) c WHERE c.rn = 1");
		
		return (String) getDao().findBySql(sql.toString(), param).get(0);
	}
	public List<BisArrearsRemindVo> getDebtTenants(String bisProjectId ){
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, bisProjectId);
		List<BisArrearsRemindVo> list =null;
		try{
			list = (List<BisArrearsRemindVo>)
			this.executeFunction("{call BIS_FACT_PKG.BIS_DEBT_SHOPS(?,?)}", 
				map, BisArrearsRemindVo.class);
		}catch(Exception e){
			log.error("搜索租户欠费信息错误");
		}
		return list;
	}
	/**
	 * 更新租户欠费信息：本月欠费、本年欠费、往年欠费、累计应收、累计实收、账龄
	 */
	public void refreshTenantDebtInfo(String bisTenantId){
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(0, bisTenantId);
		try{
			Util.executeFunction("{ call BIS_FACT_PKG.BIS_DEBT(?)}", 
				map);
		}catch(Exception e){
			log.error("更新租户欠费信息失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * 搜索商铺编号
	 * 条件：租户id、合同id
	 */
	public String getStoreNos(String bisTenantId,String bisContId){
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(0, null);
		map.put(1, bisContId);
		String result= null;
		StringBuffer sql = new StringBuffer();
		sql
		 .append(" SELECT  TRANSLATE(LTRIM(c.text, '/'), '*/', '*,') store_no,c.rn														    ")
		 .append("                  from (																					    ")
		 .append("                       SELECT ROW_NUMBER() OVER(PARTITION BY b.bis_tenant_id ORDER BY b.bis_tenant_id, num DESC) rn,    b.bis_tenant_id, b.text						    ")
		 .append("                          from (																				    ")
		 .append("                               select a.bis_tenant_id, level num, SYS_CONNECT_BY_PATH(a.store_no, '/') text											    ")
		 .append("                                  from (																			    ")
		 .append("                                       select bc.bis_tenant_id,bf.floor_num||'_'||bs.store_no store_no, ROW_NUMBER() OVER(PARTITION BY bc.bis_tenant_id ORDER BY bc.bis_tenant_id, bs.store_no) x ")
		 .append("                                          from bis_cont      bc,   bis_store_cont_rel scr, bis_store    bs,bis_floor bf									    ")
		 .append("                                         where bc.bis_cont_id = scr.bis_cont_id  and bf.bis_floor_id=bs.bis_floor_id										    ")
		 .append("                                          and scr.bis_store_id = bs.bis_store_id														    ")
		 .append("                                          and bc.bis_cont_id =:p_bis_cont_id															    ")
		 .append("                                         ) a																			    ")
		 .append("                                CONNECT BY a.bis_tenant_id = PRIOR a.bis_tenant_id														    ")
		 .append("                                       AND a.x - 1 = PRIOR a.x																    ")
		 .append("                           ) b																				    ")
		 .append("                        ) c																					    ")
		 .append("                 WHERE c.rn = 1																				    ")
		 ;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("p_bis_cont_id", bisContId);
		List<Object> tmps  = findBySql(sql.toString(), param);
		if(tmps.size()==1){
			Object[] o = (Object[])tmps.get(0);
			result =(String) o[0];
		}
		/*try{
			 result = this.executeFunction("{ call BIS_FACT_PKG.GET_STORENO_FOR_TENANT(?,?)}", 
					map, String.class).toString();
		}catch(Exception e){
			log.error("搜索失败");
			e.printStackTrace();
		}*/
		return result;
	}


	/**
	 * 更新当天有应收的租户欠费信息
	 */
	public  void refreshTenantDebtTotal(){
		log.info("////////////////更新租户欠费信息 ");
		long start = System.currentTimeMillis();
		@SuppressWarnings("deprecation")
		Connection conn = getDao().getSession().connection();
		try{
			//Map<Integer, Object> map = new HashMap<Integer, Object>();
			CallableStatement callableStatement = conn.prepareCall("{ call BIS_FACT_PKG.BIS_DEBT_TOTAL}");
		/*this.executeFunction("{ call BIS_FACT_PKG.BIS_DEBT_TOTAL}", 
					map, String.class);*/
			callableStatement.execute();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		long end_dime = System.currentTimeMillis();
		log.info("////////////////更新租户欠费信息：" + (end_dime - start)/1000.00 +" 秒!");
		
	}
	
	public void save(BisTenant bisTenant) {
		
		if(StringUtils.isNotBlank(bisTenant.getBisShopConnId())) {
			bisTenant.setTypeCd("1");
		} else if(StringUtils.isNotBlank(bisTenant.getBisShopId()) && StringUtils.isBlank(bisTenant.getBisShopConnId())) {
			bisTenant.setTypeCd("2");
		} else {
			bisTenant.setTypeCd("3");
		}
		saveBisTenant(bisTenant);
		
		String hql = "update BisStore bs set bs.bisTenantId=:bisTenantId where bs.bisStoreId in (:storeIds)";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisTenantId", bisTenant.getBisTenantId());
		param.put("storeIds", bisTenant.getStoreIds().split(","));
		getDao().batchExecute(hql, param);
		
	}
	
	@Transactional
	public void merge(BisTenant bisTenant, String chkIds) throws Exception {
		
		save(bisTenant);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisTenantId", bisTenant.getBisTenantId());
		param.put("storeNos", bisTenant.getStoreNos());
		for(String tenantId : chkIds.split(",")) {
			if(StringUtils.isBlank(tenantId) || tenantId.equals(bisTenant.getBisTenantId())) {
				continue;
			}
			param.put("oldTenantId", tenantId);
			String contHql = "update BisCont t set t.bisTenantId=:bisTenantId where t.bisTenantId=:oldTenantId";
			String mustHql = "update BisMust t set t.bisTenantId=:bisTenantId where t.bisTenantId=:oldTenantId";
			String factHql = "update BisFact t set t.bisTenantId=:bisTenantId where t.bisTenantId=:oldTenantId";
			String mustOtherHql = "update BisMustOther t set t.bisTenantId=:bisTenantId,t.bisStoreNos=:storeNos where t.bisTenantId=:oldTenantId";
			String factOtherHql = "update BisFactOther t set t.bisTenantId=:bisTenantId,t.bisStoreId=:storeNos where t.bisTenantId=:oldTenantId";
			
			getDao().batchExecute(contHql, param);
			getDao().batchExecute(mustHql, param);
			getDao().batchExecute(factHql, param);
			getDao().batchExecute(mustOtherHql, param);
			getDao().batchExecute(factOtherHql, param);
			
			String tenantHql = "update BisTenant t set t.activeBl=0 where t.bisTenantId=:oldTenantId";
			getDao().batchExecute(tenantHql, param);
		}
		
//		String mustOtherSql = "select t.must_year,t.must_month,t.charge_type_cd,count(t.bis_must_other_id),sum(t.money) " +
//				"from bis_must_other t where t.bis_tenant_id=:bisTenantId group by t.must_year,t.must_month,t.charge_type_cd";
//		List<Object[]> mustlist = getDao().findBySql(mustOtherSql, param);
//		for(Object[] obj : mustlist) {
//			if(((BigDecimal)obj[3]).compareTo(new BigDecimal(1)) == 1) {
//				String hql = "from BisMustOther t where t.bisTenantId=:bisTenantId and t.mustYear=:mustYear and t.mustMonth=:mustMonth and t.chargeTypeCd=:chargeTypeCd";
//				param.put("mustYear", obj[0]);
//				param.put("mustMonth", obj[1]);
//				param.put("chargeTypeCd", obj[2]);
//				List<BisMustOther> mustOthers = bisMustOtherManager.find(hql, param);
//				for(int i=0; i<mustOthers.size(); i++) {
//					BisMustOther mustOther = mustOthers.get(i);
//					if(i==0) {
//						mustOther.setMoney((BigDecimal) obj[4]);
//						bisMustOtherManager.saveBisMustOther(mustOther);
//					} else {
//						bisMustOtherManager.delete(mustOther);
//					}
//				}
//				
//			}
//		}
//		
//		String factOtherSql = "select t.fact_year,t.fact_month,t.charge_type_cd,count(t.bis_fact_other_id),sum(t.money) " +
//				"from bis_fact_other t where t.bis_tenant_id=:bisTenantId group by t.fact_year,t.fact_month,t.charge_type_cd";
//		List<Object[]> factlist = getDao().findBySql(factOtherSql, param);
//		for(Object[] obj : factlist) {
//			if(((BigDecimal)obj[3]).compareTo(new BigDecimal(1)) == 1) {
//				String hql = "from BisFactOther t where t.bisTenantId=:bisTenantId and t.factYear=:factYear and t.factMonth=:factMonth and t.chargeTypeCd=:chargeTypeCd";
//				param.put("factYear", obj[0]);
//				param.put("factMonth", obj[1]);
//				param.put("chargeTypeCd", obj[2]);
//				List<BisFactOther> factOthers = bisFactOtherManager.find(hql, param);
//				for(int i=0; i<factOthers.size(); i++) {
//					BisFactOther factOther = factOthers.get(i);
//					if(i==0) {
//						factOther.setMoney((BigDecimal) obj[4]);
//						bisFactOtherManager.saveBisFactOther(factOther);
//					} else {
//						bisFactOtherManager.delete(factOther);
//					}
//				}
//				
//			}
//		}
		
	}
	
	/**
	 * 取得租户费用信息
	 */
	public List<Object[]> getTenantTotalFee(String bisTenantId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisTenantId", bisTenantId);
		StringBuffer sql = new StringBuffer();
		sql .append(" select nvl(a.charge_type_cd,b.charge_type_cd),a.m_money,b.f_money")
			.append(" from (")
				.append(" select m.charge_type_cd,sum(m.money) m_money")
				.append(" from bis_must m where m.bis_tenant_id=:bisTenantId")
				.append(" group by m.charge_type_cd")
				.append(" order by m.charge_type_cd) a")
			.append(" full join (")
				.append(" select f.charge_type_cd,sum(f.money) f_money")
				.append(" from bis_fact f where f.bis_tenant_id=:bisTenantId")
				.append(" group by f.charge_type_cd")
				.append(" order by f.charge_type_cd")
				.append(" ) b on a.charge_type_cd=b.charge_type_cd")
			.append(" union all")
			.append(" select nvl(c.charge_type_cd,d.charge_type_cd),NVL(c.m_money,0),NVL(d.f_money,0)")
			.append(" from (")
				.append(" select '999' as charge_type_cd, sum(m.money) m_money")
				.append(" from bis_must m where m.bis_tenant_id=:bisTenantId) c")
			.append(" full join (")
				.append(" select '999'as charge_type_cd, sum(f.money) f_money")
				.append(" from bis_fact f where f.bis_tenant_id=:bisTenantId")
			.append(" ) d on c.charge_type_cd=d.charge_type_cd");
		
		return getDao().findBySql(sql.toString(), param);
	}

	/**
	 * 获得商铺ID集合
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<String> getStoreIds(String bisTenantId) {
		
		List<BisStoreContRel> list = bisStoreContRelDao.find("from BisStoreContRel where bisCont.bisTenantId=?", bisTenantId);
		
		List<String> storeIds = new ArrayList<String>();
		for(BisStoreContRel rel : list) {
			storeIds.add(rel.getBisStore().getBisStoreId());
		}
		return storeIds;
	}
	
	/**
	 * 租户失效
	 */
	public void invalid(String id) throws Exception {
		
		BisTenant bisTenant = getEntity(id);
		bisTenant.setActiveBl(false);
		bisTenant.setImageBl(false);
		saveBisTenant(bisTenant);
	}
	
	/**
	 * 租户失效
	 */
	public void valid(String id) throws Exception {
		
		BisTenant bisTenant = getEntity(id);
		bisTenant.setActiveBl(true);
		bisTenant.setImageBl(true);
		saveBisTenant(bisTenant);
	}
	
	public String getShopName(String bisContId){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("bisContId", bisContId);
		StringBuffer hql = new StringBuffer();
		hql.append("select s.name_Cn from bis_cont c ,bis_tenant t,bis_shop s " )
		.append(" where c.bis_Tenant_Id=t.bis_Tenant_Id and t.bis_Shop_Id=s.bis_Shop_Id " )
		.append(" and c.bis_Cont_Id =:bisContId");
		List<Object> list = this.getDao().findBySql(hql.toString(), param);
		for (int i = 0; i <list.size(); i++)
			return (String)list.get(i);
		return null;
		
	}
}

