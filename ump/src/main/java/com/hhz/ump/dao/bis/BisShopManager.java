package com.hhz.ump.dao.bis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.bis.BisShop;
import com.hhz.ump.entity.bis.BisShopBackup;
import com.hhz.ump.entity.bis.BisShopConn;
import com.hhz.ump.entity.bis.BisShopCredit;
import com.hhz.ump.entity.bis.BisShopSortRel;
import com.hhz.ump.entity.bis.BisShopVisit;
import com.hhz.ump.entity.bis.BisTenant;

@Service
@Transactional
public class BisShopManager extends BaseService<BisShop, String> {
	@Autowired
	private BisShopDao bisShopDao;
	@Autowired
	private BisShopSortRelDao bisShopSortRelDao;
	@Autowired
	private BisShopConnDao bisShopConnDao;
	@Autowired
	private BisShopCreditDao bisShopCreditDao;
	@Autowired
	private BisShopVisitDao bisShopVisitDao;
	@Autowired
	private BisShopSortRelManager bisShopSortRelManager;
	@Autowired
	private BisTenantManager bisTenantManager;
	@Autowired
	private BisShopBackupManager bisShopBackupManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;

	public void saveBisShop(BisShop bisShop) {
		PowerUtils.setEmptyStr2Null(bisShop);
		bisShopDao.save(bisShop);
		//删除该类别的Rel
		/*if(bisShop.getBisShopSortRels()!=null&&bisShop.getBisShopSortRels().size()>0) {
			bisShopSortRelManager.batchDeleteRel(bisShop.getBisShopId());
		}*/
		List<BisShopSortRel> relList =bisShopSortRelManager.querySortRel(bisShop.getBisShopId());
		for(BisShopSortRel bisRel:bisShop.getBisShopSortRels()){
			if(bisRel == null) {
				continue;
			}
			if(StringUtils.isNotBlank(bisRel.getBisShopSortRelId())){
				//修改 比较
				for(Iterator<BisShopSortRel> it = relList.iterator(); it.hasNext();){
					BisShopSortRel sortRel = it.next();
					if(sortRel.getBisShopSortRelId().equals(bisRel.getBisShopSortRelId() )){
						it.remove();
						break;
					}
				}
			}
			bisRel.setBisShop(bisShop);
		    bisShopSortRelDao.save(bisRel);
		}
		//对新LIST里面没有的REL进行删除
		for(BisShopSortRel rel:relList){
			bisShopSortRelManager.delete(rel);
		}
		for(BisShopConn conn:bisShop.getBisShopConns()){
			if(conn!= null){
				if (StringUtils.isNotBlank(conn.getPartName())||StringUtils.isNotBlank(conn.getPartAddress())||StringUtils.isNotBlank(conn.getPrincipal())||StringUtils.isNotBlank(conn.getPrincipalTel())) {
					conn.setDeleteBl("0");
					conn.setBisShop(bisShop);
					bisShopConnDao.save(conn);
				}
			}
		}
		//新增拜访记录
		for(BisShopVisit visit:bisShop.getBisShopVisits()){
			if(visit!=null){
				if (StringUtils.isNotBlank(visit.getVisitContent())||visit.getVisitTime()!=null) {
					visit.setBisShop(bisShop);
					bisShopVisitDao.save(visit);
				}
			}
		}
		//新增资信资料
		for(BisShopCredit credit:bisShop.getBisShopCredits()){
			if(credit!=null){
				if (StringUtils.isNotBlank(credit.getInforName())||StringUtils.isNotBlank(credit.getSummaryDesc())||credit.getInforTime()!=null||StringUtils.isNotBlank(credit.getAttachBl())) {
					if(StringUtils.isNotBlank(credit.getAttachId())){
						//搜索是否有该附件记录
						List<AppAttachFile> file=appAttachFileManager.getAttaFileByBizEntityId(credit.getAttachId());
						if(file==null||file.size()<=0){
							credit.setAttachId("");
						}
					}
					credit.setBisShop(bisShop);
					bisShopCreditDao.save(credit);
				}
			}
		}
	}
	
	public void saveBisShopConn(BisShopConn conn,BisShop bisShop){
		if(conn!= null){
			if (StringUtils.isNotBlank(conn.getPartName())||StringUtils.isNotBlank(conn.getPartAddress())||StringUtils.isNotBlank(conn.getPrincipal())||StringUtils.isNotBlank(conn.getPrincipalTel())) {
				conn.setBisShop(bisShop);
				bisShopConnDao.save(conn);
			}
		}
	}

	public void deleteBisShop(String id) {
		BisShop  bisShop =bisShopDao.get(id);
		List<BisShopSortRel> relList =bisShop.getBisShopSortRels();
		for(BisShopSortRel rel:relList){
			bisShopSortRelDao.delete(rel);
		}
		bisShopDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisShop, String> getDao() {
		return bisShopDao;
	}
	
	/**
	 * 商家下拉列表
	 */
	public Map<String, String> getMapBisShop() {
		Map<String, String> mapBisShop = new LinkedHashMap<String, String>();
		mapBisShop.put("", "--选择--");
		List<BisShop> list = bisShopDao.getAll();
		String shopName = null;
		for(BisShop bisShop : list){
			if(StringUtils.isNotBlank(bisShop.getNameCn())) {
				shopName = StringUtils.trim(bisShop.getNameCn()) + "|" + StringUtils.trim(bisShop.getCompanyName());
			} else {
				shopName = StringUtils.trim(bisShop.getNameEn()) + "|" + StringUtils.trim(bisShop.getCompanyName());
			}
			mapBisShop.put(bisShop.getBisShopId(), shopName);
		}
		return mapBisShop;
	}
	
	/**
	 * 通过租户ID获得商家名称
	 */
	public String getShopName(String bisTenantId) {

		String sql = "select NVL(bs.name_cn,bs.name_en) shop_name " +
				"from bis_tenant bt left join bis_shop bs on bt.bis_shop_id=bs.bis_shop_id " +
				"where bt.bis_tenant_id=:bisTenantId and type_cd='1'";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisTenantId", bisTenantId);

		@SuppressWarnings("unchecked")
		List<String> list = getDao().findBySql(sql, param);
		if (list.size() == 1)
			return list.get(0);
		return "";
	}
	
	/**
	 * 通过租户ID获得经销商名称
	 */
	public String getShopConnName(String bisTenantId) {
		
		String sql = "select bsc.part_name shop_conn_name from bis_tenant bt left join bis_shop_conn bsc on bt.bis_shop_conn_id=bsc.bis_shop_conn_id where bt.bis_tenant_id=:bisTenantId";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisTenantId", bisTenantId);
		
		@SuppressWarnings("unchecked")
		List<String> list = getDao().findBySql(sql, param);
		if(list.size() == 1)
			return list.get(0);
		return "";
	}
	/**
	 * 合并商家信息，步骤：1、将合并的记录deleteBl置为2，2：将被合并子记录加入合并记录下，
	 * 3、搜索租户和合同有关该品牌的id，若有全部替换为合并的ID，
	 * 4、将合并ID，被合并ID，租户ID、合同ID放入历史记录里
	 */
	public String merge(String batchData,String mergeId){
		BisShop mergeEntity =getEntity(mergeId);
		if (batchData != null){
			String[] arr = batchData.split(",");
			for (int i = 0; i < arr.length; i ++) {
			    String shopId = arr[i];
			    String strHql ="select a from BisShop a where a.bisShopId=:bisShopId";
			    Map<String, Object> strParam = new HashMap<String, Object>();
			    strParam.put("bisShopId", shopId);
			    List<BisShop> shopList = find(strHql,strParam);
			    BisShop bisShop=null;
			    if(shopList!=null&&shopList.size()>0){
			    	bisShop =shopList.get(0);
			    }
			    //设置合并，即删除字段为2
			    if(bisShop!=null){
			    	bisShop.setDeleteBl("2");
				    if(bisShop.getBisShopConns()!=null&&bisShop.getBisShopConns().size()>0){
				    	List<BisShopConn> mergConnList = mergeEntity.getBisShopConns();
				    	for(BisShopConn conn:bisShop.getBisShopConns()){
				    		conn.setBisShop(mergeEntity);
				    		mergConnList.add(conn);
				    	}
				    	bisShop.setBisShopConns(new ArrayList<BisShopConn>());
				    }
				    if(bisShop.getBisShopVisits()!=null&&bisShop.getBisShopVisits().size()>0){
				    	List<BisShopVisit> mergVisiList = mergeEntity.getBisShopVisits();
				    	for(BisShopVisit visit:bisShop.getBisShopVisits()){
				    		visit.setBisShop(mergeEntity);
				    		mergVisiList.add(visit);
				    	}
				    	bisShop.setBisShopVisits(new ArrayList<BisShopVisit>());
				    }
				    if(bisShop.getBisShopCredits()!=null&&bisShop.getBisShopCredits().size()>0){
				    	List<BisShopCredit> mergCredList =mergeEntity.getBisShopCredits();
				    	for(BisShopCredit cred:bisShop.getBisShopCredits()){
				    		cred.setBisShop(mergeEntity);
				    		mergCredList.add(cred);
				    	}
				    	bisShop.setBisShopCredits(new ArrayList<BisShopCredit>());
				    }
				    saveBisShop(bisShop);
				    saveBisShop(mergeEntity);
				    //搜索合同台账，是否有被合并的ID，若有则替换
				    Map<String, Object> param = new HashMap<String, Object>();
				    String hql="select a from BisTenant a where a.bisShopId=:bisShopId";
				    param.put("bisShopId", bisShop.getBisShopId());
				    List<BisTenant> tenaList =bisTenantManager.find(hql, param);
				    if(tenaList!=null&&tenaList.size()>0){
				    	//商家历史表list
				    	List<BisShopBackup> backupList =new ArrayList<BisShopBackup>();
				    	for(BisTenant tena:tenaList){
				    		//新建商家历史表
				    		BisShopBackup backup =new BisShopBackup();
				    		backup.setBisTenantId(tena.getBisTenantId());
				    		backup.setMergeShopId(mergeId);
				    		backup.setOrgShopId(bisShop.getBisShopId());
				    		bisShopBackupManager.saveBisShopBackup(backup);
				    		//更新租户表商家ID
				    		tena.setBisShopId(mergeId);
				    		bisTenantManager.saveBisTenant(tena);
				    	}
				    }else{
				    	//新建一条商家历史表
			    		BisShopBackup backup =new BisShopBackup();
			    		backup.setMergeShopId(mergeId);
			    		backup.setOrgShopId(bisShop.getBisShopId());
			    		bisShopBackupManager.saveBisShopBackup(backup);
				    }
				}
			}
		}
		return "ok";
	}
	/**
	 * 通过（中文名称或英文名称+公司名称， 搜索商家唯一性
	 */
	public List<BisShop> shopValidate(String nameCn,String nameEn){
		StringBuffer hql =new StringBuffer(" from BisShop where deleteBl='0'");
		if(StringUtils.isNotBlank(nameCn)){
			if(StringUtils.isNotBlank(nameEn)){
				//既有中文名称又有英文名称
				hql.append(" and (nameCn='").append(nameCn).append("' or nameEn='").append(nameEn).append("')");
			}else{
				//有中文名称没英文名称
				hql.append(" and nameCn='").append(nameCn).append("'");
			}
			
		}else{
			//没中文只有英文名称
			hql.append(" and nameEn='").append(nameEn).append("'");
		}
		//hql.append(" and companyName='").append(companyName).append("'");
		List<BisShop> result =bisShopDao.find(hql.toString());
		return result;
	}
}

