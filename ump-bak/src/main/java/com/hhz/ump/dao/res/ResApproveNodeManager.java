package com.hhz.ump.dao.res;

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
import com.hhz.ump.entity.res.ResApproveNode;

@Service
@Transactional
public class ResApproveNodeManager extends BaseService<ResApproveNode, String> {
	@Autowired
	private ResApproveNodeDao resApproveNodeDao;

	public void saveResApproveNode(ResApproveNode resApproveNode) {
		PowerUtils.setEmptyStr2Null(resApproveNode);
		resApproveNodeDao.save(resApproveNode);
	}
	public void saveResApproveNodes(List<ResApproveNode>  approveNodes) {
		for (ResApproveNode resApproveNode : approveNodes) {
			saveResApproveNode(resApproveNode);
		}
	}

	public void deleteResApproveNode(String id) {
		resApproveNodeDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<ResApproveNode, String> getDao() {
		return resApproveNodeDao;
	}
	
	/**
	 * 获取审批节点
	 * 步骤一:查找审批节点，若找到,返回;否则，继续
	 * 步骤二:查找最后一条节点(treeLevel最大),若找到,返回;否则，返回空.
	 * @param resApproveId
	 * @return
	 */
	public ResApproveNode getChiefNode(String resApproveId){ 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("resApproveId", resApproveId);
		map.put("verifyCd", "1");//审核
		String hql = " from ResApproveNode t where t.resApproveInfo.resApproveInfoId = :resApproveId and t.verifyCd = :verifyCd ";
		List<ResApproveNode> list = this.find(hql, map);
		if(list == null || list.size() == 0){
			String hql2 = " from ResApproveNode t where t.resApproveInfo.resApproveInfoId = :resApproveId and t.approveLevel in(select max(t2.approveLevel) from ResApproveNode t2 where t2.resApproveInfo.resApproveInfoId = :resApproveId) ";
			List<ResApproveNode> list2 = this.find(hql2, map);
			if(list2 == null || list2.size() == 0)
				return null;
			else
				return list2.get(0);
		}else
			return list.get(0);
	}
	
	
	/**
	 * 搜索某个审批节点
	 * @param userCd ： uiid 
	 * @param nodeCd ：  
	 * @param approveLevel ：  
	 * @return
	 */
	public ResApproveNode getApproveNodeByUserCd(String resApproveId, String userCd,String nodeCd,Long approveLevel){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("resApproveId", resApproveId);
		map.put("userCd", userCd);
		map.put("nodeCd", nodeCd);
		map.put("approveLevel", approveLevel);
		String hql = " from ResApproveNode t where t.resApproveInfo.resApproveInfoId = :resApproveId and t.userCd = :userCd and (t.nodeCd=:nodeCd or t.groupNodeCd=:nodeCd) and t.approveLevel = :approveLevel) ";
		List<ResApproveNode> list = this.find(hql, map);
		if(list == null || list.size() == 0)
			return null;
		else
			return list.get(0);
	}
	
}
