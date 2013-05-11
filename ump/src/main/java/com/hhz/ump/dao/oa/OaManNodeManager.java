package com.hhz.ump.dao.oa;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaManNode;
import com.hhz.ump.util.PdCache;

@Service
@Transactional
public class OaManNodeManager extends BaseService<OaManNode, String> {
	@Autowired
	private OaManNodeDao oaManNodeDao;

	public void saveOaManNode(OaManNode oaManNode) {
		PowerUtils.setEmptyStr2Null(oaManNode);
		oaManNodeDao.save(oaManNode);
	}

	public void deleteOaManNode(String id) {
		oaManNodeDao.delete(id);
	}
	
	@Override
	public HibernateDao<OaManNode, String> getDao() {
		return oaManNodeDao;
	}
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, String> getNodeMap() {
		Map<String, String> mapNode = new LinkedHashMap<String, String>();
		mapNode.put("","");
		List<OaManNode> nodes =PdCache.getEntityList(OaManNode.class);
		CollectionHelper.sortList(nodes, "sequenceNo");
		for (OaManNode oaNode : nodes) {
			mapNode.put(oaNode.getNodeCd(), oaNode.getNodeName());
		}
		return mapNode;
	}
}

