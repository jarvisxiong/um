package com.hhz.uums.cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class PlasCache {

	// 在线人数
	private static Set<String> onLineUiidSet = new HashSet<String>();
	
	
	// 跑任务
	private static Map<String, String> quartzIdMap = new HashMap<String, String>();
	
 
	/* ************************************************************** */
	// 在线人员
	public synchronized static void addOnlineCount(String uiid) {
		onLineUiidSet.add(uiid);
	}

	public synchronized static void reduceOnlineCount(String uiid) {
		onLineUiidSet.remove(uiid);
	}

	public static long getUserOnlineCount() {
		return onLineUiidSet.size();
	}

	public static Set<String> getOnlineUiidSet() {
		return onLineUiidSet;
	}
	

	public synchronized static boolean startQuartz(String quartzId, String remoteId) {
		if(quartzIdMap.keySet().contains(quartzId))
			return false;
		else{
			quartzIdMap.put(quartzId, remoteId);
			return true;
		}
	}
	public synchronized static boolean stopQuartz(String quartzId, String remoteId) {
		if(quartzIdMap.keySet().contains(quartzId)){
			quartzIdMap.remove(quartzId);
			return true;
		} else
			return false;
	}

	public synchronized static boolean getQuartzFlg(String quartzId) {
		return quartzIdMap.keySet().contains(quartzId);
	}
 
}
