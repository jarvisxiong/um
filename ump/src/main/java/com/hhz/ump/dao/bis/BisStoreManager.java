package com.hhz.ump.dao.bis;

import java.math.BigDecimal;
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
import com.hhz.ump.entity.bis.BisStore;

@Service
@Transactional
public class BisStoreManager extends BaseService<BisStore, String> {
	@Autowired
	private BisStoreDao bisStoreDao;

	public void saveBisStore(BisStore bisStore) {
		PowerUtils.setEmptyStr2Null(bisStore);
		bisStoreDao.save(bisStore);
	}

	public void deleteBisStore(String id) {
		bisStoreDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisStore, String> getDao() {
		return bisStoreDao;
	}
	public int updateStoreCoords(String bisProjectId,String floorNum,String storeNo,String coords,String area){
		StringBuffer sb = new StringBuffer();
		sb.append(" update BisStore s set s.coords=:coords ");
		if(StringUtils.isNotBlank(area)){
			sb.append(" ,s.virtualArea=:area");
		}
		sb.append("  where s.bisFloor.bisFloorId in (select f.bisFloorId from BisFloor f " +
				"where floorType='1' and subFloorType='1'  and f.bisProject.bisProjectId =:bisProjectId and f.floorNum =:floorNum)")
		.append("  and trim(s.storeNo) =:storeNo");
		
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("bisProjectId", bisProjectId);
		values.put("storeNo", storeNo);
		values.put("floorNum", floorNum);
		values.put("coords" , coords);
		if(StringUtils.isNotBlank(area)){
			values.put("area" , area);
		}
		return this.getDao().batchExecute(sb.toString(), values);
	}
	public int getStoreCoords(String bisProjectId,String floorNum,String storeNo ,String area){
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) from bis_floor f,bis_store s" +
				"	where f.floor_Type='1' and f.sub_Floor_Type='1' " +
				"	and f.bis_floor_id=s.bis_floor_id and f.floor_num=:floorNum and " +
				"	s.bis_project_id=:bisProjectId and  trim(s.store_No) =:storeNo");
		if(StringUtils.isNotBlank(area)){
			sb.append(" and trim(s.virtual_Area)=:area"  );
		}
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("bisProjectId", bisProjectId);
		values.put("storeNo", storeNo);
		values.put("floorNum", floorNum);
		if(StringUtils.isNotBlank(area)){
			values.put("area" , area);
		}
		@SuppressWarnings("unchecked")
		List<Object> result =   this.getDao().findBySql(sb.toString(),values);
		return Integer.valueOf(result.get(0).toString());
	}
	public List<Object[]> getStoreInfos(String bisProjectId,String bisFloorId){
		StringBuffer sb = new StringBuffer();
		sb
		.append("select 												")            
		.append("  s.bis_store_id				  				   as	").append("id").append(",  ")            
		.append("  s.store_no                                      as	").append("\"商铺编号\"").append(", ")            
		.append("  to_char(s.square)                               as	").append("\"建筑面积\"").append(",   ")            
		.append("  to_char(s.inner_square)                         as	").append("\"套内面积\"").append(",	")        
		.append("  to_char(s.public_square)                        as	").append("\"公摊面积\"").append(",	")        
		.append("  to_char(s.rent_square)	                       as	").append("\"计租面积\"").append(",	 ")        
		.append("  to_char(s.sell_price)                       	   as	").append("\"销售单价\"").append(",	   ")        
		.append("  to_char(s.open_date)                            as	").append("\"开业日期\"").append(",	")                     
		.append("  to_char(s.rent_standard )                       as	").append("\"租金标准\"").append(",	")            
		.append("  to_char(s.tenem_standard )                      as	").append("\"物业标准\"").append(",    ")         
		.append("  case when s.shop_position='1' then '主力店'			")         
		.append("       when s.shop_position='2' then '次主力店'    		") 
		.append("       when s.shop_position='3' then '小商铺'  			")
		.append("       else ''				  							")
		.append("  end						   					   as   ").append("\"商铺定位\"").append(",	")
		.append(" (	 select d.dict_name 								")
		.append("	from app_dict_type t ,app_dict_data d				")
		.append("	where trim(t.dict_type_cd) = 'BIS_STORE_LAYOUT'		")
		.append("	and t.app_dict_type_id=d.app_dict_type_id      		")
		.append("	and d.dict_cd=s.layout_cd		       				")
		.append("  )                                    	       as   ").append("\"规划业态\"").append(",	 ")                    
		.append("  case when s.equity_nature='2' then '出售'				")                    
		.append("       when s.equity_nature='3' then '返租'				")                    
		.append(" 	when s.equity_nature='4' then '业主自营'				")                  
		.append("       when  s.equity_nature='1' then  '自留'			")                     
		.append("       else ''	end				   				  as    ").append("\"产权性质\"").append(",	  ")                                     
		.append("  case when s.management_status='1'		 			")  
		.append("	and s.equity_nature!='1' then '返租'	 				")
		.append("       when s.equity_nature='2'		 				")              
		.append("	and  s.equity_nature!='1' then '业主自营'			")
		.append("       else ''  end                		   	  as    ").append("\"经营现状\"").append(",	")          
		.append("  case when s.if_practice='1'      					")          
		.append("	 and s.equity_nature='2' then '开业' 				")
		.append("       when s.if_practice!='1'      					")     
		.append("	 and s.equity_nature='2' then '未开业'				")
		.append("        else  '' 				 	")          
		.append("  end                                            as   	").append("\"是否开业\"").append(",	 ")  
		.append("  CASE when s.status_cd ='1' 			        		")    
		.append("		and s.parent_store_id is null then ''			")
		.append("      when s.status_cd ='0'							")     
		.append("	 	and s.parent_store_id is null then '已拆分'		")
		.append("      when s.status_cd ='1' 							")  
		.append("	and s.parent_store_id is not null then '拆分后'		")
		//.append("    --  when s.split_status = '1' then '已拆分'        ")              
		//.append("      --when s.split_status ='0' then '拆分后'			")         
		.append("      else ''					        				")               
		.append("  end                                              as  ").append( "\"是否拆分\"")                      
		.append("  from  bis_store s ,bis_floor f						")         
		.append("  where s.bis_floor_id=f.bis_floor_id					")    
		.append("  and f.bis_project_id =:bisProjectId                  ")
		.append("  and   f.sub_floor_type='1' and f.floor_type='1'      ");
		Map<String,Object> map = new HashMap<String,Object>();
			map.put("bisProjectId", bisProjectId);
		if(StringUtils.isNotBlank(bisFloorId)){
			sb.append("  and   f.bis_floor_id=:bisFloorId       		");
			map.put("bisFloorId", bisFloorId.substring(0, bisFloorId.indexOf("`")));
		}
		
	  sb.append("  and 													")         
		.append("  (													")         
		.append("    (s.status_cd ='1' and s.parent_store_id is null)   ")         
		.append("    or													")         
		.append("    (s.status_cd ='1' and s.parent_store_id is not null)")         
		.append("    or						         					") 
		.append("    (s.status_cd ='0' and s.parent_store_id is null)   ")          
		.append("  )						        					")         
		.append("						        						")         
		.append("  order by s.store_no				        			");
		
		List<Object[]>result = this.findBySql(sb.toString(), map);
		return result;
	}
	
	/**
	 * 根据租户ID获取商铺集合
	 * 
	 * @param bisTenantId
	 * @return Object[] [0]-商铺ID [1]-商铺NO [2]-建筑面积 [3]-套内面积 [4]-计租面积
	 */
	public Object[] getBisStoreIdNos(String bisTenantId) {
		
		StringBuffer hql = new StringBuffer("from BisStore bs where bs.statusCd='1' ");
		hql.append(" and exists (select 1 from BisStoreContRel rel where rel.bisStore.bisStoreId=bs.bisStoreId ")
			.append(" and rel.bisCont.bisTenantId=? )")
			.append(" order by bs.storeNo");
		
		List<BisStore> list = getDao().find(hql.toString(), bisTenantId);
		StringBuffer storeIds = new StringBuffer("");
		StringBuffer storeNos = new StringBuffer("");
		BigDecimal square = new BigDecimal(0);
		BigDecimal innerSquare = new BigDecimal(0);
		BigDecimal rentSquare = new BigDecimal(0);
		
		for(BisStore bisStore : list) {
			storeIds.append(bisStore.getBisStoreId()).append(",");
			storeNos.append(bisStore.getStoreNo()).append(",");
			if(bisStore.getSquare() != null) {
				square = square.add(bisStore.getSquare());
			}
			if(bisStore.getInnerSquare() != null) {
				innerSquare = innerSquare.add(bisStore.getInnerSquare());
			}
			if(bisStore.getRentSquare() != null) {
				rentSquare = rentSquare.add(bisStore.getRentSquare());
			}
		}
		Object[] result = new Object[5];
		if(StringUtils.isNotBlank(storeIds.toString())) {
			result[0] = storeIds.substring(0, storeIds.length()-1); //商铺ID
			result[1] = storeNos.substring(0, storeNos.length()-1); //商铺NO
		} else {
			result[0] = "";
			result[1] = "";
		}
		result[2] = square; //建筑面积
		result[3] = innerSquare; //套内面积
		result[4] = rentSquare; //计租面积
		return result;
	}
	
	/**
	 * 根据租户ID数组获取商铺集合
	 * 
	 * @param String[] tenantIdArr
	 * @return Object[] [0]-商铺ID [1]-商铺NO [2]-建筑面积 [3]-套内面积 [4]-计租面积
	 */
	public Object[] getBisStoreIdNos(String[] tenantIdArr) {
		
		StringBuffer hql = new StringBuffer("from BisStore bs where bs.statusCd='1' ");
		hql.append(" and exists (select 1 from BisStoreContRel rel where rel.bisStore.bisStoreId=bs.bisStoreId ")
			.append(" and rel.bisCont.bisTenantId in (:tenantIds) )")
			.append(" order by bs.storeNo");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tenantIds", tenantIdArr);
		List<BisStore> list = getDao().find(hql.toString(), param);
		StringBuffer storeIds = new StringBuffer("");
		StringBuffer storeNos = new StringBuffer("");
		BigDecimal square = new BigDecimal(0);
		BigDecimal innerSquare = new BigDecimal(0);
		BigDecimal rentSquare = new BigDecimal(0);
		
		for(BisStore bisStore : list) {
			storeIds.append(bisStore.getBisStoreId()).append(",");
			storeNos.append(bisStore.getStoreNo()).append(",");
			square = square.add(bisStore.getSquare());
			innerSquare = innerSquare.add(bisStore.getInnerSquare());
			rentSquare = rentSquare.add(bisStore.getRentSquare());
		}
		Object[] result = new Object[5];
		if(StringUtils.isNotBlank(storeIds.toString())) {
			result[0] = storeIds.substring(0, storeIds.length()-1); //商铺ID
			result[1] = storeNos.substring(0, storeNos.length()-1); //商铺NO
		} else {
			result[0] = "";
			result[1] = "";
		}
		result[2] = square; //建筑面积
		result[3] = innerSquare; //套内面积
		result[4] = rentSquare; //计租面积
		return result;
	}
	
	public String getStoreNosByCont(String bisContId){
		StringBuffer conts = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append(" select s from BisStore s,BisStoreContRel rel  " )
		.append("where rel.bisCont.bisContId =:bisContId  and s.bisStoreId= rel.bisStore.bisStoreId")
		;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisContId", bisContId);
		List<BisStore> result = getDao().find(sql.toString(), param);
		List<String> tmp = new ArrayList<String>();
		if(result.size()>0){
			tmp.add(result.get(0).getBisStoreId());
			conts.append(result.get(0).getStoreNo());
		}
		for(int i = 1; i<result.size();i++){
			BisStore obj = result.get(i);
			if(!tmp.contains(obj.getBisStoreId())){
				tmp.add(obj.getBisStoreId());
				conts.append(",").append(obj.getStoreNo());
			}
		}
		return conts.toString();
	}
	/**
	 * 
	 * Description:获取空商铺坐标列表
	 * author:jiaoxiaofeng  2011-11-3
	 * @return
	 * List<Object>
	 */
	public List<Object[]> getStoreNullCoords(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select p.bis_project_id,p.project_name,s.store_no,s.virtual_area,f.floor_num,f.building_num,s.CREATED_DATE,s.UPDATED_DATE,s.status_Cd ")
		.append("	from bis_store s ,bis_floor f ,bis_project p ")
		.append(" 	where p.bis_project_id= f.bis_project_id and s.bis_floor_id= f.bis_floor_id")
		.append("	and s.coords is null and f.floor_type=1 and f.sub_floor_type=1 and s.status_cd!='0'")
		.append("	order by p.bis_project_id,s.UPDATED_DATE desc,s.CREATED_DATE desc,f.floor_num ")
		;
		Map<String, Object> param = new HashMap<String, Object>();
		List<Object[]> result = this.findBySql(sql.toString(), param);
		return result;
	}
}

