package com.hhz.ump.dao.plan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hhz.ump.entity.plan.CostExecutionPlanNode;

/**
 * 类名 ExecutionPlanNodesCache 
 * 创建者 李劲 
 * 创建日期 2010-5-28 
 * 描述 执行计划的节点Cache
 */
public class CostPlanNodesCache {
	private static Map<String, CostExecutionPlanNode> nodesCache = null;

	private CostPlanNodesCache() {
	}

	/**
	 * 如果没有初始化，则传入节点列表进行初始化，为防止多线程同时初始化，对该方法进行同步保护
	 * 
	 * @param nodesList
	 */
	public synchronized static void initCache(List<CostExecutionPlanNode> nodesList) {
		if (nodesList == null || nodesList.size() == 0)
			return;

		// 如果已经初始化了，则直接返回
		if (nodesCache != null)
			return;

		Map<String, CostExecutionPlanNode> temp = new HashMap<String, CostExecutionPlanNode>();
		for (CostExecutionPlanNode n : nodesList) {
			temp.put(n.getNodeCd(), n);
		}
		nodesCache = temp;
	}

	public static Map<String, CostExecutionPlanNode> getNodesCache() {
		return nodesCache;
	}
}
