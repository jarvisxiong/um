package com.hhz.ump.dao.cont;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.cont.ContLedger;

@Repository
public class ContLedgerDao extends HibernateDao<ContLedger, String> {

	/**
	 * “月度资金预算”，根据项目和期数,获取合同台账清单
	 * @param projectCd  : 不允许为空，
	 * @param contTypeCd : 不允许为空， 对应cont_ledger.type_cd 若空或"DEFAULT"，则搜索"一期(默认)";否则，带入type_cd,搜索。 
	 * 
	 */
	public List<ContLedger> getContLedgerList(String projectCd, String contTypeCd) {
		
		if(StringUtils.isBlank(projectCd) || StringUtils.isBlank(contTypeCd))
			return new ArrayList<ContLedger>();
		StringBuffer sb =  new StringBuffer().append(" from ContLedger t where t.projectCd = :projectCd ");
		
		if(StringUtils.isBlank(contTypeCd) || "DEFAULT".equals(contTypeCd)){
			sb.append(" and t.contTypeCd is null ");
		}else{
			sb.append(" and t.contTypeCd = :contTypeCd ");
		}
		
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("projectCd", projectCd);
		values.put("contTypeCd", contTypeCd);
		
		List list = find(sb.toString(), values);
		if(list == null)
			return new ArrayList<ContLedger>();
		else
			return list;
	}
}

