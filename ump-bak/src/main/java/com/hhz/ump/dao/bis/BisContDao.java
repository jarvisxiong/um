package com.hhz.ump.dao.bis;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.bis.BisCont;

@Repository
public class BisContDao extends HibernateDao<BisCont, String> {
	public List<BisCont> getContsByTenantId(String bisTenantId){
		List<BisCont> result = this.createCriteria(BisCont.class).add(Restrictions.eq("bisTenantId", bisTenantId)).list();
		if (result == null)
			return new ArrayList<BisCont>();
		else
			return result;
	}
}

