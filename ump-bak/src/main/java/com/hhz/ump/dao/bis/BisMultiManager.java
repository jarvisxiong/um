package com.hhz.ump.dao.bis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisMulti;

@Service
@Transactional
public class BisMultiManager extends BaseService<BisMulti, String> {
	@Autowired
	private BisMultiDao bisMultiDao;

	public void saveBisMulti(BisMulti bisMulti) {
		PowerUtils.setEmptyStr2Null(bisMulti);
		bisMultiDao.save(bisMulti);
	}

	public void deleteBisMulti(String id) {
		bisMultiDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisMulti, String> getDao() {
		return bisMultiDao;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<BisMulti> getBisMultiList(String bisProjectId) {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select f from BisMulti f ").append(" where f.bisProjectId=:bisProjectId order by f.multiName");
		param.put("bisProjectId", bisProjectId);
		List<BisMulti> result = this.find(hql.toString(),param);
		if (result == null)
			return new ArrayList<BisMulti>();
		else
			return result;
	}
	public List<BisMulti> getMultiListNoCont(String bisProjectId){
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select f from BisMulti f   ").append(" where f.bisProjectId=:bisProjectId");
		hql.append(" and not exists(select 1 from BisMultiContRel r where r.bisMulti.bisMultiId=f.bisMultiId)");
		param.put("bisProjectId", bisProjectId);
		List<BisMulti> multiList =this.find(hql.toString(),param);
		return multiList;
	}
	public int updateMulti(String bisMultiId,String coords,String bisFloorId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisMultiId", bisMultiId);
		param.put("bisFloorId", bisFloorId);
		param.put("coords", coords);
		StringBuffer hql = new StringBuffer();
		hql.append(" update BisMulti m set m.coords=:coords, m.bisFloorId=:bisFloorId  where m.bisMultiId =:bisMultiId ");
		return this.getDao().batchExecute(hql.toString(), param);
	}
	public BisMulti getMultiByPiAndName(String bisProjectId,String multiName){
		List<BisMulti> result = multiList(bisProjectId, multiName);
		if(result.size()==1)
			return result.get(0);
		else
			return null;
	}
	public List<BisMulti> multiList(String bisProjectId,String multiName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", bisProjectId);
		param.put("multiName", multiName);
		StringBuffer hql = new StringBuffer();
		hql.append(" from BisMulti a  where 1=1 ");
		if(StringUtils.isNotBlank(multiName)){
			hql.append(" and trim(a.multiName)  =:multiName");
		}
		if(StringUtils.isNotBlank(bisProjectId)){
			hql.append(" and a.bisProjectId =:bisProjectId ");
		}else{
			hql.append(" and a.bisProjectId is null");//防止编辑多经信息时无法获取公用项目
			}
		hql.append(" order by multiName ");
		return find(hql.toString(), param);
	}
}

