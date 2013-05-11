package com.hhz.ump.dao.res;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResNode;
import com.hhz.ump.util.PdCache;

@Service
@Transactional
public class ResNodeManager extends BaseService<ResNode, String> {
	@Autowired
	private ResNodeDao resNodeDao;

	public void saveResNode(ResNode resNode) {
		PowerUtils.setEmptyStr2Null(resNode);
		resNodeDao.save(resNode);
	}

	public void deleteResNode(String id) {
		resNodeDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<ResNode, String> getDao() {
		return resNodeDao;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public ResNode getNodeByCd(String nodeCd) {
		return findUniqueBy("nodeCd", nodeCd);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getNodeUserByCd(String nodeCd) {
		ResNode resNode = findUniqueBy("nodeCd", nodeCd);
		if (resNode != null)
			return resNode.getNodeUser();
		return null;
	}

	/**
	 * 取得责任人是我的节点
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<ResNode> getMyNodes() {
		Criterion criterion = Restrictions.eq("nodeUser", SpringSecurityUtils.getCurrentUiid());
		return findBy(criterion);
	}

	/**
	 * 取得节点map，用户Code转Name
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, String> getNodeMap() {
		Map<String, String> mapNode = new LinkedHashMap<String, String>();
		mapNode.put("", "");
		List<ResNode> nodes = PdCache.getEntityList(ResNode.class);
		CollectionHelper.sortList(nodes, "nodeName");
		for (ResNode resNode : nodes) {
			mapNode.put(resNode.getNodeCd(), resNode.getNodeName());
		}
		return mapNode;
	}

	/**
	 * 取得所有小组节点
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, String> getGroupNodeMap() {
		Map<String, String> mapNode = new LinkedHashMap<String, String>();
		mapNode.put("", "");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nodeTypeCd", "1");// 小组
		List<ResNode> nodes = PdCache.getEntityList(ResNode.class, map);
		CollectionHelper.sortList(nodes, "nodeName");
		for (ResNode resNode : nodes) {
			mapNode.put(resNode.getNodeCd(), resNode.getNodeName());
		}
		return mapNode;
	}

	/**
	 * 获取所有自定义审批人节点
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<ResNode> getCustomerNodeMap() {
		Map<String, String> mapNode = new LinkedHashMap<String, String>();
		mapNode.put("", "");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nodeTypeCd", "2");// 自定义
		map.put("isMuti", false);// 单选人员
		map.put("active", true);// 有效节点
		List<ResNode> nodes = PdCache.getEntityList(ResNode.class, map);
		CollectionHelper.sortList(nodes, "nodeName");
		return nodes;
	}

}
