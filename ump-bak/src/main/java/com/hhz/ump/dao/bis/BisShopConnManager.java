package com.hhz.ump.dao.bis;

import java.util.HashMap;
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
import com.hhz.ump.entity.bis.BisShopConn;
import com.hhz.ump.entity.bis.BisTenant;

@Service
@Transactional
public class BisShopConnManager extends BaseService<BisShopConn, String> {
	@Autowired
	private BisShopConnDao bisShopConnDao;
	@Autowired
	private BisTenantDao bisTenantDao;

	public void saveBisShopConn(BisShopConn bisShopConn) {
		PowerUtils.setEmptyStr2Null(bisShopConn);
		bisShopConnDao.save(bisShopConn);
	}

	public void deleteBisShopConn(String id) {
		bisShopConnDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisShopConn, String> getDao() {
		return bisShopConnDao;
	}
	
	public Map<String, String> getMapBisShopConn(String bisShopId) {
		
		Map<String, String> mapBisShopConn = new LinkedHashMap<String, String>();
		Map<String, String> param = new HashMap<String, String>();
		mapBisShopConn.put("", "--选择--");
		List<BisShopConn> list;
		StringBuffer hql = new StringBuffer("from BisShopConn where 1=1 ");
		if(StringUtils.isNotBlank(bisShopId)) {
			hql.append(" and bisShop.bisShopId=? ");
			param.put("bisShopId", bisShopId);
			list = bisShopConnDao.find(hql.toString(), bisShopId);
		} else {
			list = bisShopConnDao.find(hql.toString());
		}
//		hql.append(" order by createdDate ");
		for(BisShopConn bisShopConn : list){
			mapBisShopConn.put(bisShopConn.getBisShopConnId(), bisShopConn.getPartName());
		}
		return mapBisShopConn;
	}
	public boolean updateShopConn(String deleteId){
		if(StringUtils.isNotBlank(deleteId)){
			//搜索租户里是否有该经销商，若有则不能删除
			String hql =" from BisTenant where 1=1 and bisShopConnId=?";
			List<BisTenant> tenantList =bisTenantDao.find(hql.toString(), deleteId);
			if(tenantList==null||tenantList.size()<=0){
				BisShopConn conn =getEntity(deleteId);
				conn.setDeleteBl("1");
				saveBisShopConn(conn);
				return true;
			} else
				return false;
		}
		return false;
	}
}

