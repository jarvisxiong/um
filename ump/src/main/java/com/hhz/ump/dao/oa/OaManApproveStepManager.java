package com.hhz.ump.dao.oa;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaManApproveStep;
import com.hhz.ump.entity.oa.OaManNode;

@Service
@Transactional
public class OaManApproveStepManager extends BaseService<OaManApproveStep, String> {
	@Autowired
	private OaManApproveStepDao oaManApproveStepDao;

	public void saveOaManApproveStep(OaManApproveStep oaManApproveStep) {
		PowerUtils.setEmptyStr2Null(oaManApproveStep);
		oaManApproveStepDao.save(oaManApproveStep);
	}

	public void deleteOaManApproveStep(String id) {
		oaManApproveStepDao.delete(id);
	}
	
	@Override
	public HibernateDao<OaManApproveStep, String> getDao() {
		return oaManApproveStepDao;
	}
	
	
	public Map<String,String> getGroupNodeCd(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "--选择--");
		String hql = "from OaManNode";
		List list = (List)( oaManApproveStepDao.createQuery(hql).list());
		for(int i=0;i<list.size();i++){
			OaManNode oaManNode = (OaManNode)list.get(i);
			map.put(oaManNode.getNodeCd(), oaManNode.getNodeName());
		}
		return map;
	}
	
	
}

