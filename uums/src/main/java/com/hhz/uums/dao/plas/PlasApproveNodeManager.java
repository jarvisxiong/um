package com.hhz.uums.dao.plas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasApproveNode;

@Service
@Transactional
public class PlasApproveNodeManager extends BaseService<PlasApproveNode, String> {
	@Autowired
	private PlasApproveNodeDao plasApproveNodeDao;

	public void savePlasApproveNode(PlasApproveNode plasApproveNode) {
		PowerUtils.setEmptyStr2Null(plasApproveNode);
		plasApproveNodeDao.save(plasApproveNode);
	}

	public void deletePlasApproveNode(String id) {
		plasApproveNodeDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasApproveNode, String> getDao() {
		return plasApproveNodeDao;
	}
	

	/**
	 * 获取流程的所有节点(升序排列，很重要)
	 * @param flowTypeCd
	 * @return
	 */
	public List<PlasApproveNode> getNodeList(String flowTypeCd){
		Map<String, Object> values = new HashMap<String,Object>();
		values.put("flowTypeCd", flowTypeCd);
		String hql = " from PlasApproveNode t where t.flowTypeCd = :flowTypeCd order by t.sequenceNo asc ";
		List<PlasApproveNode> list = find(hql, values);
		if(list != null && list.size() > 0)
			return list;
		return new ArrayList<PlasApproveNode>();
	} 
	/**
	 * 获取流程的第一个节点
	 * @param flowTypeCd
	 * @return
	 */
	public PlasApproveNode getFirstNode(String flowTypeCd){

		Map<String, Object> values = new HashMap<String,Object>();
		values.put("flowTypeCd", flowTypeCd);
		String hql = " from PlasApproveNode t where t.flowTypeCd = :flowTypeCd order by t.sequenceNo asc ";
		List<PlasApproveNode> list = find(hql, values);
		if(list != null && list.size() > 0)
			return list.get(0);
		else 
			return null;
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtils.split("",",").length);//0
		System.out.println("".split(",").length);//1
	}
}

