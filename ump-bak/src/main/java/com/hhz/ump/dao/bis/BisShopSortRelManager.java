package com.hhz.ump.dao.bis;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisShopSortRel;

@Service
@Transactional
public class BisShopSortRelManager extends BaseService<BisShopSortRel, String> {
	@Autowired
	private BisShopSortRelDao bisShopSortRelDao;

	public void saveBisShopSortRel(BisShopSortRel bisShopSortRel) {
		PowerUtils.setEmptyStr2Null(bisShopSortRel);
		bisShopSortRelDao.save(bisShopSortRel);
	}

	public void deleteBisShopSortRel(String id) {
		bisShopSortRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisShopSortRel, String> getDao() {
		return bisShopSortRelDao;
	}
	public void batchDeleteRel(String shopId){
		if(StringUtils.isNotBlank(shopId)){
			String hql ="delete from "+ BisShopSortRel.class.getSimpleName() + " where bisShop.bisShopId=?";
			bisShopSortRelDao.batchExecute(hql, shopId);
		}
	}
	public List<BisShopSortRel> querySortRel(String shopId){
		StringBuffer hql =new StringBuffer("from "+ BisShopSortRel.class.getSimpleName() + " where 1=1");
		if(StringUtils.isNotBlank(shopId)){
			hql.append("and bisShop.bisShopId='").append(shopId).append("'");
		}
		return bisShopSortRelDao.find(hql.toString());
	}
}

