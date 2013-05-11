package com.hhz.uums.dao.plas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasOrgDime;
import com.hhz.uums.vo.ws.WsPlasOrgDime;

@Service
@Transactional
public class PlasOrgDimeManager extends BaseService<PlasOrgDime, String> {
	@Autowired
	private PlasOrgDimeDao plasOrgDimeDao;

	public void savePlasOrgDimeRel(PlasOrgDime plasOrgDime) {
		PowerUtils.setEmptyStr2Null(plasOrgDime);
		plasOrgDimeDao.save(plasOrgDime);
	}

	public void deletePlasOrgDime(String id) {
		plasOrgDimeDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasOrgDime, String> getDao() {
		return plasOrgDimeDao;
	}
 
	@Override
	public List<PlasOrgDime> getAll(){
		return getAll("sequenceNo", true);
	}
	
	public PlasOrgDime getEntityByDimeCd(String dimeCd){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("dimeCd", dimeCd);
		List<PlasOrgDime> list= find("from PlasOrgDime where dimeCd = :dimeCd order by dimeCd asc",map);
		if(list == null)
			return null;
		else
			return list.get(0);
	}

	/* *********************************************************************************/

	public List<WsPlasOrgDime> getWsAll(){
		String hql = "select plasOrgDimeId, dimeCd, dimeName, sequenceNo, remark  from  PlasOrgDime ";
		Map<String,Object> values = new HashMap<String, Object>();
		List list = this.getDao().find(hql,values);
		
		List<WsPlasOrgDime> rtnList = new ArrayList<WsPlasOrgDime>();
		if(list != null){
			Object[] t = null;
			WsPlasOrgDime dime = null;
			for (int i = 0; i < list.size(); i++) {
				t = (Object[])list.get(i);
				dime = new WsPlasOrgDime();
				dime.setPlasOrgDimeId((String)t[0]);
				dime.setDimeCd((String)t[1]);
				dime.setDimeName((String)t[2]);
				dime.setSequenceNo((Long)t[3]);
				dime.setRemark((String)t[4]);
				rtnList.add(dime);
			}
		}
		return rtnList;
	}
	/**
	 * 批量保存
	 * @param insertedRecords
	 * @param updatedRecords
	 * @param deletedRecords
	 */
	public void saveOrUpdateDime(List<PlasOrgDime> insertedRecords,List<PlasOrgDime> updatedRecords,List<PlasOrgDime> deletedRecords){
		for(PlasOrgDime PlasOrgDime : insertedRecords){
			this.savePlasOrgDimeRel(PlasOrgDime);
		}
		for(PlasOrgDime PlasOrgDime : updatedRecords){
			this.savePlasOrgDimeRel(PlasOrgDime);
		}
		for(PlasOrgDime PlasOrgDime : deletedRecords){
			this.delete(PlasOrgDime);
		}
		
	}
}

