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
import com.hhz.ump.entity.bis.BisMustOther;

@Service
@Transactional
public class BisMustOtherManager extends BaseService<BisMustOther, String> {
	@Autowired
	private BisMustOtherDao bisMustOtherDao;

	public void saveBisMustOther(BisMustOther bisMustOther) {
		PowerUtils.setEmptyStr2Null(bisMustOther);
		bisMustOtherDao.save(bisMustOther);
	}

	public void deleteBisMustOther(String id) {
		bisMustOtherDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisMustOther, String> getDao() {
		return bisMustOtherDao;
	}
	
	public BisMustOther getExistsMust(String floorTypeCd, String bisTenantId, String bisFlatId, String bisMultiId, String chargeTypeCd, String mustYear, String mustMonth) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from BisMustOther where chargeTypeCd=:chargeTypeCd and mustYear=:mustYear and mustMonth=:mustMonth");
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
		param.put("mustYear", mustYear);
		param.put("mustMonth", mustMonth);
		
		List<BisMustOther> list = getDao().find(hql.toString(), param);
		if(list.size() > 1)
			throw new RuntimeException("数据异常，请联系管理员");
		if(list.size() == 1)
			return list.get(0);
		return null;
	}
	
}

