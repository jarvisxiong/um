package com.hhz.ump.dao.planold;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hhz.ump.entity.planold.PlanExecutionPlanNodeOld;

/**
 * 类名 ExecutionPlanNodesCache 创建者 李劲 创建日期 2010-5-28 描述 执行计划的节点Cache
 */
public class ExecutionPlanNodeOldsCache {
	private static Map<String, PlanExecutionPlanNodeOld> nodesCache = null;

	private ExecutionPlanNodeOldsCache() {
	}

	/**
	 * 如果没有初始化，则传入节点列表进行初始化，为防止多线程同时初始化，对该方法进行同步保护
	 * 
	 * @param nodesList
	 */
	public synchronized static void initCache(List<PlanExecutionPlanNodeOld> nodesList) {
		if (nodesList == null || nodesList.size() == 0)
			return;

		// 如果已经初始化了，则直接返回
		if (nodesCache != null)
			return;

		Map<String, PlanExecutionPlanNodeOld> temp = new HashMap<String, PlanExecutionPlanNodeOld>();
		for (PlanExecutionPlanNodeOld n : nodesList) {
			temp.put(n.getNodeCd(), n);
		}
		nodesCache = temp;
	}

	public static Map<String, PlanExecutionPlanNodeOld> getNodesCache() {
		return nodesCache;
	}
}
