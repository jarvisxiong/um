package com.hhz.ump.dao.bis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.ump.dao.app.CommonDao;
import com.hhz.ump.entity.bis.BisFactOther;
import com.hhz.ump.entity.bis.BisMust;
import com.hhz.ump.entity.bis.BisMustOther;

@Service
@Transactional
@SuppressWarnings("rawtypes")
public class BisIncomeOtherManager extends BaseService {
	
	@Autowired
	private CommonDao commonDao;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao getDao() {
		return commonDao;
	}
	
	@Transactional
	@Deprecated
	public void batchExecute(List<BisMustOther> mustOthers, List<BisFactOther> factOthers) {
		
		Session session = getDao().getSession();
		
		for(int i=0; i<mustOthers.size(); i++) {
			session.saveOrUpdate(mustOthers.get(i));
            if(i%50 == 0){
                session.flush();
                session.clear();
            }
		}
		
		for(int i=0; i<factOthers.size(); i++) {
			session.saveOrUpdate(factOthers.get(i));
            if(i%50 == 0){
                session.flush();
                session.clear();
            }
		}
		
	}
	
	@Transactional
	public void batchExecute(List<BisMust> mustList) {
		
		Session session = getDao().getSession();
		
		for(int i=0; i<mustList.size(); i++) {
			session.saveOrUpdate(mustList.get(i));
			if(i%50 == 0){
				session.flush();
				session.clear();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getStoreList(String bisProjectId, String bisFloorId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", bisProjectId);
		param.put("bisFloorId", bisFloorId);
		String sql = buildStoreSql(bisProjectId, bisFloorId);
		
		return getDao().findBySql(sql, param);
	}
	
	private String buildStoreSql(String bisProjectId, String bisFloorId) {
		
		if(StringUtils.isBlank(bisProjectId))
			return "";
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select j.bis_tenant_id,j.bis_shop_id,j.name_cn,j.part_name,j.bis_cont_id,j.cont_no,j.cont_small_type_cd,k.store_no")
			.append(" from (")
				.append(" select bt.bis_tenant_id,bs.bis_shop_id,bs.name_cn,bsc.part_name,bc.bis_cont_id,bc.cont_no,bc.cont_small_type_cd")
				.append(" from bis_tenant bt,bis_shop bs, bis_shop_conn bsc, bis_cont bc ")
				.append(" where bt.bis_shop_id=bs.bis_shop_id")
				.append(" and bt.bis_shop_conn_id=bsc.bis_shop_conn_id")
				.append(" and bt.bis_tenant_id=bc.bis_tenant_id")
				.append(" and bt.bis_project_id=:bisProjectId")
				/*.append(" and bc.bis_cont_id in (")
					.append(" select NVL(c1.bis_cont_id,c2.bis_cont_id)")
					.append(" from bis_tenant t ")
					.append(" left join (select * from bis_cont c where c.cont_small_type_cd='3') c1 on t.bis_tenant_id=c1.bis_tenant_id")
					.append(" left join (select * from bis_cont c where c.cont_small_type_cd='1') c2 on t.bis_tenant_id=c2.bis_tenant_id")
					.append(" where t.bis_project_id=:bisProjectId)")*/;
		if(StringUtils.isNotBlank(bisFloorId)) {
			sql.append(" and exists (select 1 from bis_store st, bis_store_cont_rel scr where st.bis_store_id=scr.bis_store_id and scr.bis_cont_id=bc.bis_cont_id and st.bis_floor_id=:bisFloorId)");
		}
		sql.append(") j")
			.append(" left join (")
			.append(" SELECT c.bis_cont_id, TRANSLATE(LTRIM(c.text, '/'), '*/', '*,') store_no")
			.append(" FROM (")
				.append(" SELECT ROW_NUMBER() OVER(PARTITION BY b.bis_cont_id ORDER BY b.bis_cont_id, num DESC) rn, b.bis_cont_id, b.text")
				.append(" FROM (")
					.append(" SELECT a.bis_cont_id, level num, SYS_CONNECT_BY_PATH(a.store_no, '/') text")
					.append(" FROM (")
						.append(" select bc.bis_cont_id, bs.store_no, ROW_NUMBER() OVER(PARTITION BY bc.bis_cont_id ORDER BY bc.bis_cont_id, bs.store_no) x")
						.append(" from bis_cont bc, bis_store_cont_rel scr, bis_store bs")
						.append(" where bc.bis_cont_id=scr.bis_cont_id")
						.append(" and scr.bis_store_id=bs.bis_store_id")
						.append(" and bc.bis_project_id=:bisProjectId")
						.append(" and bc.bis_tenant_id is not null")
						.append(" group by bc.bis_cont_id, bs.store_no ")
					.append(" ) a")
					.append(" CONNECT BY a.bis_cont_id = PRIOR a.bis_cont_id AND a.x - 1 = PRIOR a.x ")
				.append(" ) b ")
			.append(" ) c ")
			.append(" WHERE c.rn = 1 ")
			.append(" ORDER BY c.bis_cont_id ")
			.append(" ) k on j.bis_cont_id=k.bis_cont_id")
			.append(" order by k.store_no");
		
		return sql.toString();
//		select j.bis_tenant_id,j.bis_shop_id,j.name_cn,j.part_name,j.bis_cont_id,j.cont_no,j.cont_small_type_cd,k.store_no
//		from (
//		  select bt.bis_tenant_id,bs.bis_shop_id,bs.name_cn,bsc.part_name,bc.bis_cont_id,bc.cont_no,bc.cont_small_type_cd
//		  from bis_tenant bt,bis_shop bs, bis_shop_conn bsc, bis_cont bc 
//		  where bt.bis_shop_id=bs.bis_shop_id
//		  and bt.bis_shop_conn_id=bsc.bis_shop_conn_id
//		  and bt.bis_tenant_id=bc.bis_tenant_id
//		  and bt.bis_project_id='40282b8927a42dff0127a4316b830001'
//		  and bc.bis_cont_id in (
//		    select NVL(c1.bis_cont_id,c2.bis_cont_id)
//		    from bis_tenant t 
//		    left join (select * from bis_cont c where c.cont_small_type_cd='3') c1 on t.bis_tenant_id=c1.bis_tenant_id
//		    left join (select * from bis_cont c where c.cont_small_type_cd='1') c2 on t.bis_tenant_id=c2.bis_tenant_id
//		    where t.bis_project_id='40282b8927a42dff0127a4316b830001')
//		  and exists (select 1 from bis_store st, bis_store_cont_rel scr where st.bis_store_id=scr.bis_store_id and scr.bis_cont_id=bc.bis_cont_id and st.bis_floor_id='20110803FZSY00004001')
//		) j
//		left join (
//		  SELECT c.bis_cont_id, TRANSLATE(LTRIM(c.text, '/'), '*/', '*,') store_no
//		  FROM (
//		    SELECT ROW_NUMBER() OVER(PARTITION BY b.bis_cont_id ORDER BY b.bis_cont_id, num DESC) rn, b.bis_cont_id, b.text
//		    FROM (
//		      SELECT a.bis_cont_id, level num, SYS_CONNECT_BY_PATH(a.store_no, '/') text
//		      FROM (
//		        select bc.bis_cont_id, bs.store_no, ROW_NUMBER() OVER(PARTITION BY bc.bis_cont_id ORDER BY bc.bis_cont_id, bs.store_no) x
//		        from bis_cont bc, bis_store_cont_rel scr, bis_store bs
//		        where bc.bis_cont_id=scr.bis_cont_id
//		        and scr.bis_store_id=bs.bis_store_id
//		        and bc.bis_project_id='40282b8927a42dff0127a4316b830001'
//		        and bc.bis_tenant_id is not null
//		        group by bc.bis_cont_id, bs.store_no 
//		      ) a
//		      CONNECT BY a.bis_cont_id = PRIOR a.bis_cont_id AND a.x - 1 = PRIOR a.x 
//		    ) b 
//		  ) c 
//		  WHERE c.rn = 1 
//		  ORDER BY c.bis_cont_id 
//		) k on j.bis_cont_id=k.bis_cont_id
//		order by k.store_no;
	}
	
}
