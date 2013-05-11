package com.hhz.ump.dao.assm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.assm.AssmModel;

@Service
@Transactional
public class AssmModelManager extends BaseService<AssmModel, String> {
	@Autowired
	private AssmModelDao assmModelDao;

	public void saveAssmModel(AssmModel assmModel) {
		PowerUtils.setEmptyStr2Null(assmModel);
		assmModelDao.save(assmModel);
	}

	public void deleteAssmModel(String id) {
		assmModelDao.delete(id);
	}
	
	@Override
	public HibernateDao<AssmModel, String> getDao() {
		return assmModelDao;
	}
	
	/**
	 * 搜索非叶子节点的的设备
	 * @return
	 */
	public List<AssmModel> findAll(){
		String hql=" from AssmModel t where t.assmLevel != :assmLevel";
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("assmLevel", Short.valueOf("4"));
		return assmModelDao.find(hql, values);
	}
	
	public boolean modelExsit(AssmModel model){
		boolean flg=false;
		
		StringBuffer hql=new StringBuffer().append(" from AssmModel t where t.assmLevel = :assmLevel");
		hql.append(" and t.pratentId = :pratentId");
		hql.append(" and t.assmCode = :assmCode");
		//搜索条件
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("assmLevel", Short.valueOf("4"));
		values.put("pratentId", model.getPratentId());
		values.put("assmCode", model.getAssmCode());
		
		List l=assmModelDao.find(hql.toString(), values);
		if(l!=null&&l.size()>0){
			flg=true;
		}
		return flg;
	}
	
	public boolean assmNameExsit(String assmName){
		boolean flg = false;
		StringBuffer hql = new StringBuffer();
		hql.append(" from AssmModel t where t.assmName = :assmName");
		//搜索条件
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("assmName", assmName.trim());
		List list = assmModelDao.find(hql.toString(), values);
		if(list != null && list.size() > 0){
			flg = true;
		}
		return flg;
	}
	
	public List<AssmModel> findAllByAssmLevel(String assmLevel){
		String hql=" from AssmModel t where t.assmLevel = :assmLevel order by assmModelId asc";
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("assmLevel", Short.valueOf(assmLevel));
		return assmModelDao.find(hql, values);
	}
	
	/**
	 * 得到某一等级的设备数据
	 * 
	 * @param assmLevel 设备等级
	 * @return
	 */
	public Map<String, String> getMapAssmModel(String assmLevel) {
		Map<String, String> mapAssmModel = new LinkedHashMap<String, String>();
		List<AssmModel> list = this.findAllByAssmLevel(assmLevel);
		for (AssmModel tmp : list) {
			mapAssmModel.put(tmp.getAssmModelId(), tmp.getAssmName());
		}
		return mapAssmModel;
	}
	
	/**
	 * 根据父类ID得到子类列表
	 * 
	 * @param pratentId
	 * @return
	 */
	public List<AssmModel> getAssmModelByPratentId(String pratentId) {
		String hql=" from AssmModel t where t.pratentId = :pratentId order by assmModelId asc";
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("pratentId", pratentId);
		return assmModelDao.find(hql, values);
	}
	
	/**
	 * 得到”电脑“类的设备
	 * @param pratentId
	 * @return
	 */
	public String[] getComputerTypeModel() {
		String[] modelIds = null;
		Map<String, Object> values = new HashMap<String,Object>();
		String hql = " from AssmModel t where t.assmName = :assmName and t.assmLevel = :assmLevel";
		values.put("assmName", "电脑");
		values.put("assmLevel", Short.valueOf("3"));
		List<AssmModel> ls = assmModelDao.find(hql, values);
		if (ls != null && ls.size() > 0) {
			AssmModel thridModel = ls.get(0);
			List<AssmModel> list = this.getAssmModelByPratentId(thridModel.getAssmModelId());
			if (list != null && list.size() > 0) {
				List<String> strList = new ArrayList<String>();  
				for (AssmModel tmp : list) {
					strList.add(tmp.getAssmModelId());
				}
				modelIds = strList.toArray(new String[strList.size()]); 
			}
		}
		return modelIds;
	}
}

