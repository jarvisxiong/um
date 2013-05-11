package com.hhz.ump.dao.prod;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.prod.ProdMaterialPrice;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.web.prod.PriItemVo;
import com.hhz.ump.web.prod.PriceIndexCellVo;
import com.hhz.ump.web.prod.PriceIndexVo;
import com.hhz.ump.web.prod.PriceIndexVotwo;
import com.hhz.ump.web.prod.SerieVo;
import com.hhz.ump.web.prod.UnitEstimateVo;

@Service
@Transactional
public class ProdMaterialPriceManager extends BaseService<ProdMaterialPrice, String> {
	@Autowired
	private ProdMaterialPriceDao prodMaterialPriceDao;
	
	/**
	 * 页面用到的表头
	 */
	public static String TITLE_HEAD="/";
	public static String TITLE_UNIT_QTY="单位消耗量";	
	public static String TITLE_BASIC_PRICE="基准工料价格";
	public static String TITLE_CURRENT_PRICE="当月工料价";
	public static String TITLE_LAST_MONTH_PRICE="上月工料价";
	public static String TITLE_LAST_YEAR_PRICE="上年当月工料价";
	public static String TITLE_YEAR_DIFFRENCE="同比差(年差)";
	public static String TITLE_MONTH_DIFFRENCE="环比差(月差)";
	
	public void saveProdMaterialPrice(ProdMaterialPrice prodMaterialPrice) {
		PowerUtils.setEmptyStr2Null(prodMaterialPrice);
		prodMaterialPriceDao.save(prodMaterialPrice);
	}

	public void deleteProdMaterialPrice(String id) {
		prodMaterialPriceDao.delete(id);
	}
	
	@Override
	public HibernateDao<ProdMaterialPrice, String> getDao() {
		return prodMaterialPriceDao;
	}
	
	/**
	 * 
	 * 搜索是否存在的当月工料价格
	 */
	public List<ProdMaterialPrice> hasProdMaterialPrice(String yearCd,String monthCd,String bussinessCd,String areaCd,String materialZoneCd){

			// 搜索条件
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("yearCd", yearCd.trim());
			map.put("monthCd", monthCd.trim());
			map.put("bussinessCd", bussinessCd.trim());
			map.put("areaCd", areaCd.trim());
			map.put("materialZoneCd", materialZoneCd.trim());
			
			//搜索条件
			StringBuffer sbSql=new StringBuffer();
			sbSql.append(" from ProdMaterialPrice pp where 1=1 ")
			.append(" and pp.yearCd = :yearCd")
			.append(" and pp.monthCd = :monthCd")
			.append(" and pp.bussinessCd = :bussinessCd")
			.append(" and pp.areaCd = :areaCd")
			.append(" and pp.materialZoneCd = :materialZoneCd");
			
			List<ProdMaterialPrice> rs = prodMaterialPriceDao.find(sbSql.toString(), map);
			if(rs!=null&&!rs.isEmpty())
				return rs;
			else
				return null;
		
	}
	
	public List<UnitEstimateVo> getUnitEstimate(Map<String, String> materialZoneCdMap,String year,String month,String areaCd){
		List<UnitEstimateVo> unitEstimateVos=new ArrayList<UnitEstimateVo>();
		List<PriceIndexVo> priceIndexs=getPriceIndex(materialZoneCdMap,year, month, areaCd,null);
		//综合单位估算指标所用变量
		//权重
		BigDecimal totalWeight=BigDecimal.ZERO;
		//初始基准工料价格小计
		BigDecimal totalInitTotal=BigDecimal.ZERO;
		//当月工料价格指数
		BigDecimal totalPriceIndex=BigDecimal.ZERO;
		//基准单位估算指标
		BigDecimal totalBasicEstimate=BigDecimal.ZERO;
		//增加指标
		BigDecimal totalAddPart=BigDecimal.ZERO;
		//单位估算指标
		BigDecimal totalEstimatePrice=BigDecimal.ZERO;
		//单位估算指标
		UnitEstimateVo unitEstimateVo=null;
		for(int i=0;i<priceIndexs.size()-1;i++){			
			PriceIndexVo priceIndexVo=priceIndexs.get(i);
			unitEstimateVo=new UnitEstimateVo();
			//权重
			totalWeight=totalWeight.add(priceIndexVo.getWeight());
			unitEstimateVo.setWeight(priceIndexVo.getWeight());
			//业态
			unitEstimateVo.setBussinessCd(priceIndexVo.getBussinessCd());
			//初始基准工料价格小计
			totalInitTotal=totalInitTotal.add(priceIndexVo.getInitTotal());
			unitEstimateVo.setInitTotal(priceIndexVo.getInitTotal());
			//当月工料价格指数Pi
			totalPriceIndex=totalPriceIndex.add(priceIndexVo.getPriceIndex());
			unitEstimateVo.setPriceIndex(priceIndexVo.getPriceIndex());
			//基准单位估算指标（W0）
			totalBasicEstimate=totalBasicEstimate.add(priceIndexVo.getBasicEstimatePrice());
			unitEstimateVo.setBasicEstimate(priceIndexVo.getBasicEstimatePrice());
			//某时点、地区单方指标增加指标ΔW i
			unitEstimateVo.setAddPart(priceIndexVo.getPriceIndex().subtract(BigDecimal.ONE).multiply(priceIndexVo.getBasicEstimatePrice()));
			totalAddPart=totalAddPart.add(unitEstimateVo.getAddPart());
			//某时点、地区的单位估算指标
			unitEstimateVo.setEstimatePrice(unitEstimateVo.getAddPart().add(unitEstimateVo.getBasicEstimate()));
			totalEstimatePrice=totalEstimatePrice.add(unitEstimateVo.getEstimatePrice());
			unitEstimateVos.add(unitEstimateVo);
			
		}		
		//计算综合单位估算指标设值
		unitEstimateVo=new UnitEstimateVo();
		unitEstimateVo.setBussinessCd("综合单位估算指标");
		unitEstimateVo.setWeight(totalWeight);
		unitEstimateVo.setInitTotal(totalInitTotal);
		unitEstimateVo.setPriceIndex(totalPriceIndex);
		unitEstimateVo.setBasicEstimate(totalBasicEstimate);
		unitEstimateVo.setAddPart(totalAddPart);
		unitEstimateVo.setEstimatePrice(totalEstimatePrice);
		//将计算综合单位估算指标加入列表
		unitEstimateVos.add(unitEstimateVo);
		return unitEstimateVos;
	}
	
	
	/**  
	 *  工料价格指数
	 * @param  @param year
	 * @param  @param month
	 * @param  @param areaCd
	 * @param  @return    设定文件  
	 * @return List<PriceIndexVo>    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1  
	*/  
	public List<PriceIndexVo> getPriceIndex(Map<String, String> materialZoneCdMap,String year,String month,String areaCd,String bussinessCd){
		List<PriceIndexVo> pis=new ArrayList<PriceIndexVo>();
		List<Object[]> rs=null;
		Map<String, Object> map = new HashMap<String, Object>();	
		//Map<String, String> materialZoneCdMap=DictMapUtil.getMapProdMaterialZoneCd();
		rs=prodMaterialPriceDao.findBySql(getEntitySql(materialZoneCdMap,year,month,areaCd,bussinessCd).toString(), map);
		PriceIndexCellVo cell=null;
		//计算综合指数
		PriceIndexVo allPriceIndex=new PriceIndexVo(); 
		BigDecimal totalWeight=BigDecimal.ZERO;
		BigDecimal totalInitTotal=BigDecimal.ZERO;
		BigDecimal totalCurrentMonthTotal=BigDecimal.ZERO;
		if(rs!=null){
			for(int i=0;i<rs.size();i++){
				Object[] o=rs.get(i);
				PriceIndexVo pi=new PriceIndexVo();
				//业态
				pi.setBussinessCd((String)o[0]);
				//权重
				if(o[1]!=null){
					pi.setWeight((BigDecimal)o[1]);						
				}
				//基准单位估算指标
				if(o[2]!=null){
					pi.setBasicEstimatePrice((BigDecimal)o[2]);						
				}else{
					pi.setBasicEstimatePrice(BigDecimal.ZERO);
				}
				//循环获取个工料范围的标量、标价、月价
				if(materialZoneCdMap!=null&&!materialZoneCdMap.isEmpty()){
					List <PriceIndexCellVo> cells=new ArrayList<PriceIndexCellVo>();
					BigDecimal initTotal=BigDecimal.ZERO;
					BigDecimal currentMonthTotal=BigDecimal.ZERO;
					BigDecimal priceIndex=BigDecimal.ZERO;
					Iterator it=materialZoneCdMap.keySet().iterator();
					int j=0;
					while(it.hasNext()){
						String key=it.next().toString();
						if(StringUtils.isNotBlank(key)){
							//工量
							cell=new PriceIndexCellVo();
							if(o[3+j*3]!=null){
								cell.setValue((BigDecimal)o[3+j*3]);
								
							}
							cells.add(cell);
							//标价
							cell=new PriceIndexCellVo();
							if(o[3+1+j*3]!=null){
								
								cell.setValue((BigDecimal)o[3+1+j*3]);
								BigDecimal q=(BigDecimal)o[3+j*3];
								initTotal=initTotal.add(q.multiply(cell.getValue()));
								
							}	
							cells.add(cell);
							//月价
							cell=new PriceIndexCellVo();
							if(o[3+2+j*3]!=null){								
								cell.setValue((BigDecimal)o[3+2+j*3]);
								BigDecimal q=(BigDecimal)o[3+j*3];
								currentMonthTotal=currentMonthTotal.add(q.multiply(cell.getValue()));
								
							}
							cells.add(cell);
							j++;
						}
					}
					pi.setInitTotal(initTotal);
					pi.setCurrentMonthTotal(currentMonthTotal);
					if(!BigDecimal.ZERO.equals(initTotal)&&!BigDecimal.ZERO.equals(currentMonthTotal)) {
						System.out.println("initTotal:"+initTotal);
						System.out.println("currentMonthTotal:"+currentMonthTotal);
						System.out.println("setPriceIndex:"+currentMonthTotal.divide(initTotal, 3, RoundingMode.HALF_UP));
						pi.setPriceIndex(currentMonthTotal.divide(initTotal, 3, RoundingMode.HALF_UP));//.divide(initTotal, RoundingMode.HALF_UP)
					}else{
						pi.setPriceIndex(BigDecimal.ZERO);
					}
					pi.setCells(cells);
				}
				
				pis.add(pi);
				//计算综合指数
				if(pi.getWeight()!=null){
					totalWeight=totalWeight.add(pi.getWeight());
					totalInitTotal=totalInitTotal.add(pi.getWeight().multiply(pi.getInitTotal()));
					totalCurrentMonthTotal=totalCurrentMonthTotal.add(pi.getWeight().multiply(pi.getCurrentMonthTotal()));
				}
				
			}
		}
		if(StringUtils.isBlank(bussinessCd)){
			//开始计算综合指数
			List <PriceIndexCellVo>totalCells=new ArrayList<PriceIndexCellVo>();		
			for(Integer k=0;k<pis.size();k++){
				PriceIndexVo idx=pis.get(k);
				PriceIndexCellVo tcl=null;
				int i=0;
				for(PriceIndexCellVo cl:idx.getCells()){
					if(totalCells.size()>0&&totalCells.size()>i){
						PriceIndexCellVo tcl2=totalCells.get(i);
						totalCells.remove(i);
						tcl2.setValue(tcl2.getValue().add(idx.getWeight().multiply(cl.getValue())));
						totalCells.add(i, tcl2);
					}else{
						tcl=new PriceIndexCellVo();
						tcl.setValue(idx.getWeight().multiply(cl.getValue()));
						totalCells.add(tcl);
					}
					i++;
				}			
			}
			//得出综合指数值
			PriceIndexVo pi=new PriceIndexVo();
			pi.setBussinessCd("综合指数");
			pi.setCells(totalCells);		
			pi.setWeight(totalWeight);
			try {
				pi.setCurrentMonthTotal(totalCurrentMonthTotal.divide(totalWeight, 3, RoundingMode.HALF_UP));
				pi.setInitTotal(totalInitTotal.divide(totalWeight, 3, RoundingMode.HALF_UP)); 
				pi.setPriceIndex(pi.getCurrentMonthTotal().divide(pi.getInitTotal(), 3, RoundingMode.HALF_UP));
			} catch (Exception e) {
				//e.printStackTrace();  
				
			}
			pis.add(pi);
		}
		return pis;
	}
	
	/**  
	 * 搜索工料价格指数
	 *  
	 * @param  @param materailZoneCd
	 * @param  @param year
	 * @param  @param month
	 * @param  @param areaCd
	 * @param  @return    设定文件  
	 * @return StringBuffer    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1  
	*/  
	public StringBuffer getEntitySql(Map<String, String> materailZoneCd,String year ,String month,String areaCd,String bussinessCd){		
		
		StringBuffer sqSlq=new StringBuffer();
		StringBuffer selectedCols=new StringBuffer();
		StringBuffer fromBody=new StringBuffer();
		StringBuffer whereSql=new StringBuffer();
		
		//迭代出所有的工料范围
		if(materailZoneCd!=null&&!materailZoneCd.isEmpty()){
			Iterator it=materailZoneCd.keySet().iterator();
			Integer index=1;
			while(it.hasNext()){
				String key=it.next().toString();
				if(StringUtils.isNotBlank(key)){
					//String val=materailZoneCd.get(key);
					if(index==1){
						selectedCols.append(" t.bussiness_cd,t.weight, max(t.ep)");	
						selectedCols.append(caseStr(index,key,"permeter_quantity"))//(",sum(decode(t.material_zone_cd,'"+key+"',t.permeter_quantity, null)) permeter_quantity"+index )
						.append(caseStr(index,key,"price"))//(",sum(decode(t.material_zone_cd,'"+key+"', t.price, null)) price"+index)
						.append(caseStr(index,key,"current_month_price"));//(",sum(decode(t.material_zone_cd,'"+key+"', t.current_month_price, null)) current_month_price"+index);	
					}else{
						selectedCols.append(caseStr(index,key,"permeter_quantity"))//(",sum(decode(t.material_zone_cd,'"+key+"',t.permeter_quantity, null)) permeter_quantity"+index )
									.append(caseStr(index,key,"price"))//(",sum(decode(t.material_zone_cd,'"+key+"', t.price, null)) price"+index)
									.append(caseStr(index,key,"current_month_price"));//(",sum(decode(t.material_zone_cd,'"+key+"', t.current_month_price, null)) current_month_price"+index);							
					}				
					index++;					
				}
			}
			fromBody.append(basicQuery(year,month,areaCd));
			whereSql.append(" t ");
			if(StringUtils.isNotBlank(bussinessCd)){			
				whereSql.append(" where t.bussiness_cd='"+bussinessCd+"'");
			}
			whereSql.append(" group by t.bussiness_cd,t.weight");
			sqSlq.append("select ")
			.append(selectedCols)
			.append(" from ")
			.append(fromBody)
			.append(whereSql);
		}
		
		return sqSlq;
	}
	
	
	private String caseStr(Integer index,String key,String colName){		
		return ", case when sum(decode(t.material_zone_cd,'"+key+"',t."+colName+", null)) is null then 0 else sum(decode(t.material_zone_cd,'"+key+"',t."+colName+", null)) end as "+colName+index;
	}
	
	public StringBuffer basicQuery(String year ,String month,String areaCd){
		StringBuffer basicSql=new StringBuffer();		
		basicSql.append(" ( select b2.version_no,b2.year_cd,b2.month_cd,b2.bussiness_cd,case when  pp.area_cd is null then '"+areaCd+"' else  pp.area_cd  end as area_cd,b2.material_zone_cd, ")
				.append(" b2.permeter_quantity,b2.price,b2.estimate_price,b2.active ,b2.weight,pp.current_month_price,b2.ep ")
				.append(" from ")
				.append(" ( ")
				.append(" select b1.version_no,b1.year_cd,b1.month_cd,b1.bussiness_cd,b1.material_zone_cd,")
				.append(" b1.permeter_quantity,b1.price,b1.estimate_price,b1.active ,pw.weight, b1.ep ")
				.append(" from prod_bussiness_weight pw ,")
				.append(" (")
				.append(" select pv.version_no,pv.year_cd,pv.month_cd,pd.bussiness_cd,pd.material_zone_cd,")
				.append(" nvl(pd.permeter_quantity, 0) as permeter_quantity, nvl(pd.price, 0) as price, nvl(pd.estimate_price, 0) as estimate_price,pd.active,nvl(pd.estimate_price, 0) as ep ")
				.append(" from prod_basic_version pv, prod_version_detail pd ")
				.append(" where pd.prod_basic_version_id=pv.prod_basic_version_id ")
				.append(" and pv.active = '1'  and pd.area_cd='"+areaCd+"' ")
				//.append("  and pv.year_cd='"+year+"' and pv.month_cd='"+month+"' ")
				.append(" )")
				.append(" b1 where pw.bussiness_cd=b1.bussiness_cd ")
				.append(" ) b2")
				.append("  left join prod_material_price pp  ")
				.append(" on pp.year_cd='"+year+"' and pp.month_cd='"+month+"' and pp.bussiness_cd=b2.bussiness_cd and pp.material_zone_cd=b2.material_zone_cd ")
				.append("  and pp.area_cd='"+areaCd+"') ");
		return basicSql;
	}
	
	public StringBuffer onJoinCtr(Integer index){
		StringBuffer onSql=new StringBuffer();	
		onSql.append(" on t1.year_cd=t"+index+".year_cd ")
		.append(" and t1.month_cd=t"+index+".month_cd ")
		.append(" and t1.area_cd=t"+index+".area_cd ")
		.append(" and t1.bussiness_cd=t"+index+".bussiness_cd ");
		return onSql;
	}

	/**
	 * 
	 * queryChartByAreaCd:(以地区为准搜索各个业态在相应时间段的曲线图)  
	 *  
	 * @param  @param areaCd
	 * @param  @param dateFrom
	 * @param  @param dateTo    设定文件  
	 * @return void    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public Map<String,Map<String,BigDecimal>> queryChartByAreaCd(String areaCd, List<String> dateList) {
		List<String> dlist =dateList;
		
		// <年月<业态,价格指数>> {yyyy-MM: {areaCd: priceIndex}}
		Map<String, Map<String, BigDecimal>> yearMonth2PriceIndex = new HashMap<String, Map<String, BigDecimal>>();
		//业态列表
		Map<String, String> materialZoneCdMap=DictMapUtil.getMapProdMaterialZoneCd();
		if(materialZoneCdMap.containsKey("")){
			materialZoneCdMap.remove("");
		}
		
		// 循环月份 获取价格指数
		Map<String, BigDecimal> m = null;
		for (String yearMonth:dlist) {
			String[] tYearMonth = yearMonth.split("-");//yyyy-MM-dd
			List<PriceIndexVo> priceIndexs = getPriceIndex(materialZoneCdMap,tYearMonth[0], tYearMonth[1], areaCd,null);// 年、月、地区 -> 多个业态
			m = new HashMap<String, BigDecimal>();
			
			// 获取业态,价格指数值
			for (int i=0;i<priceIndexs.size()-1;i++) {
				PriceIndexVo piv=priceIndexs.get(i);
				m.put(piv.getBussinessCd(), piv.getPriceIndex());
			}
			// 将整个月份的所有业态的价格指数装载进去
			yearMonth2PriceIndex.put(tYearMonth[0] + "-" + tYearMonth[1], m);
		}
		
		
		return getConvert(yearMonth2PriceIndex);
	}

	/**
	 * 
	 * getConvert:(<业态<年月,价格指数>> {areaCd: {yyyy-MM: priceIndex}})
	 *  
	 * @param  @param map2PriceIndex
	 * @param  @return    设定文件  
	 * @return Map<String,Map<String,BigDecimal>>    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	private Map<String, Map<String, BigDecimal>> getConvert(Map<String, Map<String, BigDecimal>> map2PriceIndex){
		 
		 Map<String, String> materialZoneCdMap=DictMapUtil.getMapProdMaterialZoneCd();
		 Iterator<String> materialZoneCds = materialZoneCdMap.keySet().iterator();
		 Map<String, Map<String, BigDecimal>>  map = new HashMap<String, Map<String,BigDecimal>>();
		 
		 // <年月<业态,价格指数>> {yyyy-MM: {areaCd: priceIndex}}
		 Map<String, BigDecimal> tBiz2PriceIndex = null;
		 //年月
		  Iterator<String> yearMonth = map2PriceIndex.keySet().iterator();
		//根据业态循环
		 while(yearMonth.hasNext()){
			String yearMon=yearMonth.next();
			//业态价格指数<年月,价格指数>
			Map<String,BigDecimal> rsMapInner=new HashMap<String,BigDecimal>();
			 
		 	//<业态,价格指数>
			Map<String,BigDecimal> m1=map2PriceIndex.get(yearMon);			
			Iterator<String> bizCds = m1.keySet().iterator();
			while(bizCds.hasNext()){
				 String bizCd2=bizCds.next();
				 //如果已经有这个key,则往相应的map追加
				 if(map.containsKey(bizCd2)){								 
					 Map<String,BigDecimal> tmp=map.get(bizCd2);
					 tmp.put(yearMon, m1.get(bizCd2));
					 map.remove(bizCd2);
					 map.put(bizCd2, tmp);								 
				 }else{//如果没有这个key,则生成键值对 
					 Map<String,BigDecimal> newMap=new HashMap<String,BigDecimal>();
					 newMap.put(yearMon, m1.get(bizCd2));
					 map.put(bizCd2, newMap);
				 }
			 }
		 }
				
		return map;
	}
	
	/**
	 * 
	 * 搜索数据原型加工为List数据格式
	 * 将相应的数据以 "业态,地区,年月,指数"格式返回（工料指数曲线、工料价格曲线）
	 * 或者将相应的数据以 "业态,地区,年月,建安单价造价"格式返回（建安单价造价曲线）
	 */
	public  List<String>  queryChartByBussinessCd(String bussinessCd,  List<String> dateList,String mateZoneCd,String constructType) {
		List<String> dlist =dateList;
		SerieVo vo=null;
		List<String> list=new ArrayList<String>();
		//循环月份 获取价格指数
		for(String yearMonth:dlist){
			//新建一个VO
			vo=new SerieVo();
			//年月
			String[] tYearMonth = yearMonth.split("-");
			//根据年月业态搜索数据
			List <PriceIndexVotwo> priceIndexs =priceIndexVersionTwo(bussinessCd,tYearMonth[0],tYearMonth[1],tYearMonth[0],tYearMonth[1]);
			//根据结果循环构建结果数据
			for(PriceIndexVotwo priceIndexVotwo:priceIndexs){
				//如果行为当月价格
				if(priceIndexVotwo.getRowName().equals(TITLE_CURRENT_PRICE)){
					//如果是搜索建安单价造价曲线
					if(StringUtils.isNotBlank(constructType)&&"constructType".equals(constructType)){
						//将相应的数据以 "业态,地区,年月,建安单价造价/基准建安单价造价"格式返回
						BigDecimal basicPi=BigDecimal.ZERO;
						//如果基准建安单价造价(元)不为空或空,则用建安单价造价/基准建安单价造价
						String bcup=priceIndexVotwo.getBasiConstructUnitPrice();
						if(bcup!=null&&Double.valueOf(bcup)>0.0){
							try {
								basicPi=BigDecimal.valueOf(Double.valueOf(priceIndexVotwo.getConstructUnitPrice())).divide(BigDecimal.valueOf(Double.valueOf(bcup)),3, RoundingMode.HALF_UP);
							} catch (Exception e) {								 
								e.printStackTrace();  
								
							}
						}
						list.add(bussinessCd+","+priceIndexVotwo.getAreaCd()+","+yearMonth+","+basicPi);
					}
					//如果是搜索工料指数曲线
					else if(StringUtils.isBlank(mateZoneCd)){
						//将相应的数据以 "业态,地区,年月,指数"格式返回
						list.add(bussinessCd+","+priceIndexVotwo.getAreaCd()+","+yearMonth+","+priceIndexVotwo.getPriceIndex());	
					}
					//如果是工料价格曲线
					else{
						String pi="0.0";
						//循环获取相应工料的价格
						for(PriItemVo itme:priceIndexVotwo.getItemVos()){
							//查找到相应的工料，则获取相应工料价格指数，退出
							if(itme.getMaterialCd().equals(mateZoneCd)){
								pi=itme.getMatePriceIndex().toString();
								break;
							}
						}
						//将相应的数据以 "业态,地区,年月,指数"格式返回
						list.add(bussinessCd+","+priceIndexVotwo.getAreaCd()+","+yearMonth+","+pi);
					}
										
				}
			}
		}
		return list;
	}
	
	/**
	 * 
	 *搜索相应的图标数据，解析为json的数据格式以显示
	 * sts：数据以 "业态,地区,年月,指数"格式（工料指数曲线、工料价格曲线）
	 *      数据以 "业态,地区,年月,建安单价造价"格式（建安单价造价曲线）
	 */
	public List<SerieVo> parseSerieVo(List<String> sts,String mateZoneCd){
		//json元数据
		List<SerieVo> l=new ArrayList<SerieVo>();
		Map<String,String> map=new HashMap<String,String>();
		//循环解析数据
		//数据以 "业态,地区,年月,指数"格式（工料指数曲线、工料价格曲线）
		//数据以 "业态,地区,年月,建安单价造价"格式（建安单价造价曲线）
		for(String v:sts){
			//分开解析
			String[]vs=v.split(",");
			//如果在map中已经存在"当前业态地区"键值,则通过"当前业态地区"作为键值而获取原有的值，在值的基础上修改
			if(map.containsKey(vs[0]+","+vs[1])){
				//vs[0]+","+vs[1]为业态+地区，值为指数或者建安单价造价
				String value=map.get(vs[0]+","+vs[1]);
				map.put(vs[0]+","+vs[1], value+","+vs[3]);
			}
			//如果在map中不存在"当前业态地区"键值,则加入新的键值
			else{
				//以 业态+地区作为键，值为指数或者建安单价造价
				map.put(vs[0]+","+vs[1], vs[3]);
			}
			
		}
		SerieVo vo=null;
		//如果 map不为空
		if(map!=null){
			//将map的key作为迭代器
			Iterator<String> it = map.keySet().iterator();
			//计数器
			int i=0;
			//以键进行循环
			while(it.hasNext()){
				vo=new SerieVo();
				//获取key, 其值为业态+地区
				String key=it.next();
				//拆分为[0]业态和[1]地区
				String[] keys=key.split(",");
				//如果参数是工料范围级别,则获取业态指数
				//设置VO的名称,值为[地区]	
				if(StringUtils.isBlank(mateZoneCd)){
					//vo.setName(DictMapUtil.getMapProdBussinessCd().get(keys[0])+"("+DictMapUtil.getMapProdAreaCd().get(keys[1])+")");
					vo.setName(DictMapUtil.getMapProdAreaCd().get(keys[1]));
				}
				//如果参数不是工料范围级别,则获取工料的具体指数
				else{
					//vo.setName(DictMapUtil.getMapProdBussinessCd().get(keys[0])+"-"+DictMapUtil.getMapProdMaterialZoneCd().get(mateZoneCd)+"("+DictMapUtil.getMapProdAreaCd().get(keys[1])+")");
					vo.setName(DictMapUtil.getMapProdAreaCd().get(keys[1]));
				}
				//根据键值获取"年月,指数"或者"年月,建安单价造价",然后以","拆分为数组
				String[] vals=map.get(key).split(",");
				//数据集和
				List<BigDecimal> data=new ArrayList<BigDecimal> ();
				//根据key所对用的值数组长度循环，将"指数"或者"建安单价造价"压入数据列表
				for(int k=0;k<vals.length;k++){					
					data.add(BigDecimal.valueOf(Double.parseDouble(vals[k])));
				}
				//设置数据集
				vo.setData(data);
				i++;
				l.add(vo);
			}
		}
		
		return l;
	}
	
	/**
	 * 
	 * 通过业态、时间列表、工料范围搜索出相应的工料指数曲线或者工料价格曲线数据
	 */
	public List<SerieVo> getPriceIndexData(String bussinessCd,  List<String> dateList,String mateZoneCd,String constructType){
		/*//如果是建安造价曲线
		if(StringUtils.isNotBlank(constructType)&&"constructType".equals(constructType))
			return null;
		else{
			//如过是工料指数曲线
			if(StringUtils.isBlank(mateZoneCd))
				return parseSerieVo(queryChartByBussinessCd(bussinessCd,dateList,mateZoneCd,constructType),mateZoneCd,constructType);
			//如果是工料价格曲线
			else
				return parseSerieVo(queryChartByBussinessCd(bussinessCd,dateList,mateZoneCd,constructType),mateZoneCd,constructType);
		}*/
		return parseSerieVo(queryChartByBussinessCd(bussinessCd,dateList,mateZoneCd,constructType),mateZoneCd);
		
	}

	
	/**
	 * 
	 * getDateList:(根据开始日期，结束日期获取年月的集合(yyyy-MM)) 
	 *  
	 * @param  @param dateFrom
	 * @param  @param dateTo
	 * @param  @param sf
	 * @param  @return    设定文件  
	 * @return Map<String,String>    DOM对象  
	 * @throws   
	 * @since  　Ver 1.1
	 */
	public synchronized List<String> getDateList(String dateFrom, String dateTo){
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		String df="";
		String dt="";
		List<String> dateStr=new ArrayList<String>();
		Calendar c = null;
		//如果两个时间段都不为空
		  if(StringUtils.isNotBlank(dateFrom)&&StringUtils.isNotBlank(dateTo)){
			 //不操作
			  df=dateFrom;
			  dt=dateTo;
		  }else{
			  //如果两者都为空
			  if(StringUtils.isBlank(dateFrom)&&StringUtils.isBlank(dateTo)){
				  c = Calendar.getInstance();
				  //获取当前时间,顺序减去12个月
				  System.out.println(sf.format(c.getTime()));
				  dt = sf.format(c.getTime());
				  //顺序减去12个月
				  c.add(Calendar.YEAR, -1);
				  System.out.println(sf.format(c.getTime()));
				  df = sf.format(c.getTime());
			  }else{
				  //如果开始时间不为空,顺序加上12个月
				  if(StringUtils.isNotBlank(dateFrom)){
					  df=dateFrom;
					  c = Calendar.getInstance();
					  String []arrFrom = dateFrom.split("-");
					  c.set(Integer.parseInt(arrFrom[0]), Integer.parseInt(arrFrom[1])-1, 1);
					  //顺序加上12个月
					  c.add(Calendar.MONTH, 12);
					  dt = sf.format(c.getTime());
				  }else{//如果结束时间不为空
					  dt=dateTo;
					  c = Calendar.getInstance();
					  String []arrTo = dateTo.split("-");
					  c.set(Integer.parseInt(arrTo[0]), Integer.parseInt(arrTo[1])-1, 1);
					  //顺序加上12个月
					  c.add(Calendar.MONTH, -12);
					  df = sf.format(c.getTime()); 
				  }
			  }
		  }
		 //拆分年月 
		String []arrFrom = df.split("-");
		String []arrTo = dt.split("-");
		//获取开始时间
		Calendar c1 = Calendar.getInstance();
		c1.set(Integer.parseInt(arrFrom[0]), Integer.parseInt(arrFrom[1])-1, 1);
		//获取结束时间
		Calendar c2 = Calendar.getInstance();
		c2.set(Integer.parseInt(arrTo[0]), Integer.parseInt(arrTo[1])-1, 1);
		c2.add(Calendar.MONTH, 1);
		//循环获取时间段
		for(;c1.before(c2);){
			String[] dStr = sf.format(c1.getTime()).split("-");
			dateStr.add(dStr[0]+"-"+dStr[1]);
			c1.add(Calendar.MONTH, 1);
		}
		
		return dateStr;
	}
	
	/**
	 * 
	 * 价格指数搜索SQL构建及搜索结果
	 */
	public List<Object[]> queryIndex(String areaCd,String bussinessCd,String yearCd,String monthCd,String lastYear,String lastMonth){
		String nextYear=yearCd;
		//防止跨年问题
		if("01".equals(monthCd)){
			nextYear=String.valueOf(Integer.parseInt(yearCd)-1);
		}
		StringBuffer sql=new StringBuffer();
		sql.append("select nvl(pp.area_cd,'"+areaCd+"') area_cd,")
		.append(" t1.year_cd year_cd,t1.month_cd month_cd,t1.bussiness_cd bussiness_cd,")
		.append(" t1.material_zone_cd material_zone_cd,nvl(t1.permeter_quantity,0) permeter_quantity,")
		.append("  nvl(t1.price,0) price, nvl(pp.current_month_price,0)  currentMonthPrice,")
		.append("  nvl(pp2.current_month_price,0) lastMonthPrice,nvl(pp3.current_month_price,0) lastYearPrice,")
		.append("   nvl((nvl(pp.current_month_price,0)-nvl(nvl(pp3.current_month_price,0),0))*nvl(nvl(t1.permeter_quantity,0),0),0) niancha,/**年差**/")
		.append("   nvl(( nvl(pp.current_month_price,0)-nvl(nvl(pp2.current_month_price,0),0))*nvl(nvl(t1.permeter_quantity,0),0),0) yuecha/**月差**/")
		.append("  ,t1.estimate_price estimate_price ,nvl(nvl(pp.current_month_price,0)/nvl(t1.price,0),0) mate_price_index ")
		.append("from (")
		.append("/**根据当前激活的版本搜索基准个工料的标量和标价**/")
		.append("select pv.year_cd,pv.month_cd, pd.bussiness_cd,pd.material_zone_cd,nvl(pd.permeter_quantity, 0) permeter_quantity,")
		.append("nvl(pd.price, 0) price,nvl(pd.estimate_price,0) estimate_price from prod_version_detail pd ")
		.append("join prod_basic_version pv ")
		.append("on pv.prod_basic_version_id = pd.prod_basic_version_id ")
		.append(" where pv.active = '1' ")
		.append(" and pd.bussiness_cd = '"+bussinessCd+"' ")
		.append(") t1 ")
		.append(" left join prod_material_price pp on pp.material_zone_cd = t1.material_zone_cd ")
		.append(" and pp.bussiness_cd = '"+bussinessCd+"' and pp.year_cd = '"+yearCd+"' and pp.month_cd = '"+monthCd+"' and pp.area_cd = '"+areaCd+"' ")
		.append(" left join prod_material_price pp2 on pp2.material_zone_cd = t1.material_zone_cd and pp2.bussiness_cd = '"+bussinessCd+"' ")
		.append(" and pp2.year_cd = '"+nextYear+"' and pp2.month_cd = '"+lastMonth+"' and pp2.area_cd = '"+areaCd+"' ")
		.append("  left join prod_material_price pp3 on pp3.material_zone_cd = t1.material_zone_cd")
		.append("  and pp3.bussiness_cd =  '"+bussinessCd+"' and pp3.year_cd = '"+lastYear+"' and pp3.month_cd = '"+monthCd+"' and pp3.area_cd = '"+areaCd+"'")
		.append(" order by t1.material_zone_cd desc");
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		List<Object[]> rs=this.prodMaterialPriceDao.findBySql(sql.toString(), map);
		return rs;
		
	}
	
	/**
	 * 
	 *搜索价格指数表
	 * 
	 */
	public  List <PriceIndexVotwo> priceIndexVersionTwo(String bussinessCd,String yearCd,String monthCd,String lastYear,String lastMonth){
		List <PriceIndexVotwo> rs=new ArrayList<PriceIndexVotwo>();
		List <PriceIndexVotwo> tmpRs = null;
		//搜索地区字典数据
		Map areaCdMap=DictMapUtil.getMapProdAreaCd();
		List list=new ArrayList(areaCdMap.keySet());
		Collections.sort(list);
		if(!areaCdMap.isEmpty()){
			for(int i=0;i<areaCdMap.size();i++){
				String key=list.get(i).toString();
				if(i==1){
					rs.addAll(makePageVo(queryIndex(key,bussinessCd,yearCd,monthCd,lastYear,lastMonth)));
				}else if(i>1){
					tmpRs=makePageVo(queryIndex(key,bussinessCd,yearCd,monthCd,lastYear,lastMonth));
					if(tmpRs!= null){
						for(PriceIndexVotwo tmpVo:tmpRs){
							if(!tmpVo.getRowType().equals("basic")){
								rs.add(tmpVo);
							}
						}
					}
				}
			}
		}		
		return rs;
	}

	/**
	 * 
	 *价格指数行数据构建
	 */
	public List <PriceIndexVotwo> makePageVo(List<Object[]> rs){
		
		 List <PriceIndexVotwo> pivts=new ArrayList<PriceIndexVotwo>();
		PriceIndexVotwo pivt=null;
		//构建标准消耗量
		Integer height=0;
		//获取高度和深度
		if(rs!=null&&rs.size()>0){
			height=rs.size();
			//构建表头
			pivt = new PriceIndexVotwo();
			makeRow(pivt,rs,height,4,TITLE_HEAD);
			pivt.setRowType("basic");
			pivts.add(pivt);
			//构建消耗量行
			pivt = new PriceIndexVotwo();			
			makeRow(pivt,rs,height,5,TITLE_UNIT_QTY);
			pivt.setRowType("basic");
			pivts.add(pivt);
			//构建基准工料价格
			pivt = new PriceIndexVotwo();			
			makeRow(pivt,rs,height,6,TITLE_BASIC_PRICE);
			pivt.setRowType("basic");
			pivts.add(pivt);
			//构建当月工料价
			pivt = new PriceIndexVotwo();
			pivt.setRowType("0");
			makeRow(pivt,rs,height,7,TITLE_CURRENT_PRICE);
			pivts.add(pivt);
			//构建上月工料价
			pivt = new PriceIndexVotwo();
			pivt.setRowType("0");
			makeRow(pivt,rs,height,8,TITLE_LAST_MONTH_PRICE);
			pivts.add(pivt);
			//构建上年当月工料价
			pivt = new PriceIndexVotwo();
			pivt.setRowType("0");
			makeRow(pivt,rs,height,9,TITLE_LAST_YEAR_PRICE);
			pivts.add(pivt);
			//构建同比差(年差)
			pivt = new PriceIndexVotwo();
			pivt.setRowType("0");
			makeRow(pivt,rs,height,10,TITLE_YEAR_DIFFRENCE);
			pivts.add(pivt);
			//构建环比差(月差)
			pivt = new PriceIndexVotwo();
			pivt.setRowType("0");
			makeRow(pivt,rs,height,11,TITLE_MONTH_DIFFRENCE);
			pivts.add(pivt);			
		}
		return pivts;
	}
	
	
	/**
	 * 
	 * 构建各个行
	 */
	public void makeRow(PriceIndexVotwo pivt,List<Object[]> rs,Integer height,Integer colIndex,String titleType){
		
		List<PriItemVo> itemVos=new ArrayList<PriItemVo>();
		PriItemVo itemVo=null;
		for(int i=0;i<height;i++){
			itemVo=new PriItemVo();
			itemVo.setAreaCd((String)(rs.get(i)[0]));
			//工料范围
			itemVo.setMaterialCd((String)(rs.get(i)[4]));
			//单位消耗量
			itemVo.setTitleType(titleType);
			//值
			String val=rs.get(i)[colIndex].toString();
			itemVo.setItemVal(BigDecimal.valueOf(Double.valueOf(val)));
			//工料对应的指数（如：人工指数=当月/基准价格）
			val=rs.get(i)[13].toString();
			itemVo.setMatePriceIndex(BigDecimal.valueOf(Double.valueOf(val)).setScale(3, RoundingMode.HALF_UP));			
			//加入列表
			itemVos.add(itemVo);
		}
		//设置消耗量
		pivt.setItemVos(itemVos);
		pivt.setAreaCd((String)rs.get(0)[0]);
		pivt.setYearCd((String)rs.get(0)[1]);
		pivt.setMonthCd((String)rs.get(0)[2]);		
		pivt.setBussinessCd((String)rs.get(0)[4]);
		pivt.setYm((String)rs.get(0)[1]+"-"+(String)rs.get(0)[2]);
		pivt.setRowName(titleType);
		//单位消耗量
		if(TITLE_UNIT_QTY.equals(titleType)){
			modifyRowAsUnitQty(pivt,rs,height);
		}
		//基准工料价格
		else if(TITLE_BASIC_PRICE.equals(titleType)){
			modifyRowAsBasicPrice(pivt,rs,height);
		}
		//当月工料价
		else if(TITLE_CURRENT_PRICE.equals(titleType)){
			modifyRowAsCurrentPrice(pivt,rs,height,7);
			pivt.setStartMerge("merge");
		}
		//上月工料价
		else if(TITLE_LAST_MONTH_PRICE.equals(titleType)){
			modifyRowAsLastMonthPrice(pivt,rs,height,8);
		}
		//上年当月工料价
		else if(TITLE_LAST_YEAR_PRICE.equals(titleType)){
			modifyRowAsLastYearPrice(pivt,rs,height,9);
		}
		//同比差(年差)
		else if(TITLE_YEAR_DIFFRENCE.equals(titleType)){
			modifyRowAsYearDiffrence(pivt,rs,height,9,7);
		}
		//环比差(月差)
		else if(TITLE_MONTH_DIFFRENCE.equals(titleType)){
			modifyRowAsMonthDiffrence(pivt,rs,height,8,7);
		}		
		
	}
	
	private void modifyRowAsMonthDiffrence(PriceIndexVotwo pivt, List<Object[]> rs, Integer height,Integer firstColIndex,Integer secondColIndex) {
		  //9-8
		modifyRowAsYearDiffrence(pivt,rs,height,firstColIndex,secondColIndex);
		pivt.setLineNo(6);
		
	}

	private void modifyRowAsYearDiffrence(PriceIndexVotwo pivt, List<Object[]> rs, Integer height,Integer firstColIndex,Integer secondColIndex) {
		  
		pivt.setRowType("1");
		pivt.setLineNo(5);
		//工料加权价(元)=(上年当月工料价-当月工料价) 9-7 
		BigDecimal totalPrice=getTotalPrice(rs,height,secondColIndex).subtract(getTotalPrice(rs,height,firstColIndex));
		pivt.setMateTotalPrice(totalPrice.toString());
		//工料加权差额
		pivt.setMateTotalDifference("/");		
		//建安单价造价(元)=(上年当月工料价-当月工料价)
		pivt.setConstructUnitPrice(totalPrice.toString());
		//工料价格指数
		pivt.setPriceIndex("/");
		
	}

	private void modifyRowAsLastYearPrice(PriceIndexVotwo pivt, List<Object[]> rs, Integer height,Integer curtColIndex) {
		  
		modifyRowAsCurrentPrice(pivt,rs,height,curtColIndex);
		pivt.setLineNo(4);
		
	}

	private void modifyRowAsLastMonthPrice(PriceIndexVotwo pivt, List<Object[]> rs, Integer height,Integer curtColIndex) {
		  
		modifyRowAsCurrentPrice(pivt,rs,height,curtColIndex);
		pivt.setLineNo(3);
		
	}

	/**
	 * 
	 * 当月工料价
	 */
	private void modifyRowAsCurrentPrice(PriceIndexVotwo pivt, List<Object[]> rs, Integer height,Integer curtColIndex) { 
		  
		pivt.setRowType("1");
		pivt.setLineNo(2);
		//工料加权价(元)
		BigDecimal totalPrice=getTotalPrice(rs,height,curtColIndex);
		pivt.setMateTotalPrice(totalPrice.toString());
		//工料加权差额
		BigDecimal totalDifference=BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		//工料加权价(元)大于0,才计算工料加权差额
		if(totalPrice.doubleValue()>0.0){
			totalDifference=totalPrice.subtract(getTotalPrice(rs,height,6));
		}		
		pivt.setMateTotalDifference(totalDifference.toString());		
		//建安单价造价(元)
		//工料加权价(元)大于0,才计算建安单价造价(元)
		BigDecimal constructUnitPrice=BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		if(totalPrice.doubleValue()>0.0){
			constructUnitPrice=(BigDecimal)rs.get(0)[12];
			pivt.setBasiConstructUnitPrice(constructUnitPrice.toString());
		}		
		pivt.setConstructUnitPrice(String.valueOf(constructUnitPrice.add(totalDifference)));
		//基准工料加权价(元),为计算价格指数做准备
		BigDecimal basicTotalPrice=getTotalPrice(rs,height,6);
		//工料价格指数
		if(BigDecimal.ZERO.equals(basicTotalPrice)||BigDecimal.ZERO.equals(totalPrice)){
			pivt.setPriceIndex(String.valueOf(BigDecimal.ZERO));
		}else{			
			BigDecimal pindex=totalPrice.divide(basicTotalPrice, 3, RoundingMode.HALF_UP);
			pivt.setPriceIndex(String.valueOf(pindex));
		}
	
		
	}
	/**
	 * 
	 *基准工料价格
	 */
	private void modifyRowAsBasicPrice(PriceIndexVotwo pivt, List<Object[]> rs, Integer height) {
		  
		pivt.setRowType("1");
		pivt.setLineNo(2);
		//工料加权价(元)
		BigDecimal totalPrice=getTotalPrice(rs,height,6);
		pivt.setMateTotalPrice(totalPrice.toString());
		//工料加权差额
		pivt.setMateTotalDifference("/");
		//建安单价造价(元)
		pivt.setConstructUnitPrice(String.valueOf((BigDecimal)rs.get(0)[12]));
		//工料价格指数
		pivt.setPriceIndex("1.00");
		
	}
	
	

	

	/**
	 * 
	 * 修改消耗量行属性
	 */
	private void modifyRowAsUnitQty(PriceIndexVotwo pivt, List<Object[]> rs, Integer height) {		
		pivt.setRowType("1");
		pivt.setLineNo(1);
		//工料加权价(元)
		pivt.setMateTotalPrice("/");
		//工料加权差额
		pivt.setMateTotalDifference("/");
		//建安单价造价(元)
		pivt.setConstructUnitPrice("/");
		//工料价格指数
		pivt.setPriceIndex("/");
	}
	
	
	
	

	private BigDecimal getTotalPrice(List<Object[]> rs, Integer height,Integer colIndex){
		BigDecimal totalPrice=BigDecimal.ZERO;
		BigDecimal qty=BigDecimal.ZERO;
		BigDecimal price=BigDecimal.ZERO;
		for(int i=0;i<height;i++){
			qty=(BigDecimal)rs.get(i).clone()[5];
			price=(BigDecimal)rs.get(i).clone()[colIndex];
			totalPrice=totalPrice.add(qty.multiply(price));
		}
		return totalPrice;
	}
	
	 
	
}

