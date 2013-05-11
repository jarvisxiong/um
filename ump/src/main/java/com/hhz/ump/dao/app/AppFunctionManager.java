package com.hhz.ump.dao.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.AppFunction;

@Service
@Transactional
public class AppFunctionManager extends BaseService<AppFunction, String> {
	@Autowired
	private AppFunctionDao appFunctionDao;

	
	public void saveAppFunction(AppFunction appFunction) {
		PowerUtils.setEmptyStr2Null(appFunction);
		appFunctionDao.save(appFunction);
	}

	public void deleteAppFunction(String id) {
		appFunctionDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<AppFunction, String> getDao() {
		return appFunctionDao;
	}
	/**
	 * 构建以pageId为key，List<AppFunction>为value的Map
	 */
	public Map<String,List<AppFunction>> getFunctionMap(){
		String hql = "select t1 from AppFunction t1 " ;
		Map<String,Object> values = new HashMap<String,Object>();
		List list = find(hql,values );
		AppFunction tmp = null;
		List<AppFunction> result =null;
		String tmpParentId = null;
		Map<String,List<AppFunction>> vos = new HashMap<String,List<AppFunction>>();
		for(Object obj : list){
			Object o = (Object) obj;
			tmp = (AppFunction)o;
		
			tmpParentId = tmp.getAppPage().getAppPageId();
			result = vos.get(tmpParentId);
			if(null==result){
				result =   new ArrayList<AppFunction>();
			}
			result.add(tmp);
		
			vos.put(tmpParentId, result);
			
		}
		return vos;
	}
}

