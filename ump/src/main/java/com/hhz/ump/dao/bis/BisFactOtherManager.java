package com.hhz.ump.dao.bis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisFactOther;

@Service
@Transactional
public class BisFactOtherManager extends BaseService<BisFactOther, String> {
	@Autowired
	private BisFactOtherDao bisFactOtherDao;

	public void saveBisFactOther(BisFactOther bisFactOther) {
		PowerUtils.setEmptyStr2Null(bisFactOther);
		bisFactOtherDao.save(bisFactOther);
	}

	public void deleteBisFactOther(String id) {
		bisFactOtherDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisFactOther, String> getDao() {
		return bisFactOtherDao;
	}
	

	public BisFactOther getExistsFact(String floorTypeCd, String bisTenantId, String bisFlatId, String bisMultiId, String chargeTypeCd, String factYear, String factMonth) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from BisFactOther where chargeTypeCd=:chargeTypeCd and factYear=:factYear and factMonth=:factMonth");
		if("1".equals(floorTypeCd)) {
			hql.append(" and bisTenantId=:bisTenantId");
			param.put("bisTenantId", bisTenantId);
		} else if("2".equals(floorTypeCd)) {
			hql.append(" and bisFlatId=:bisFlatId");
			param.put("bisFlatId", bisFlatId);
		} else if("3".equals(floorTypeCd)) {
			hql.append(" and bisMultiId=:bisMultiId");
			param.put("bisMultiId", bisMultiId);
		} else
			throw new RuntimeException("floor type error!");
		param.put("chargeTypeCd", chargeTypeCd);
		param.put("factYear", factYear);
		param.put("factMonth", factMonth);
		
		List<BisFactOther> list = getDao().find(hql.toString(), param);
		if(list.size() > 1)
			throw new RuntimeException("数据异常，请联系管理员");
		if(list.size() == 1)
			return list.get(0);
		return null;
	}
	
}

