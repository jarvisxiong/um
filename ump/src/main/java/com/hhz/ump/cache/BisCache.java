package com.hhz.ump.cache;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.bis.BisStoreContRelManager;
import com.hhz.ump.dao.bis.BisStoreManager;
import com.hhz.ump.entity.bis.BisCont;
import com.hhz.ump.entity.bis.BisStore;
import com.hhz.ump.entity.bis.BisStoreContRel;

/**
 * @author RS
 *
 */
/**
 * @author RS
 * 
 */
@Service
public class BisCache {

	private static Log log = LogFactory.getLog(BisCache.class);

	/*  ******************** 定时 ********************** */
	private static Map<String, List<BisStore>> contStoreMap = new HashMap<String, List<BisStore>>();

	/**
	 * 刷新cache,控制台调用
	 */
	public static boolean reloadCache() {

		try {
			processContStoreMap();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("//////////////////////////////////// BisCache.reloadCache() exception! ", e);
			return false;
		}
	}

	private static void processContStoreMap() {
		Map<String, List<BisStore>> tmpContStoreMap = new HashMap<String, List<BisStore>>();
	    BisStoreContRelManager bisStoreContRelManager = SpringContextHolder.getBean("bisStoreContRelManager");
		List<BisStoreContRel> contList =bisStoreContRelManager.find("from BisStoreContRel c ", new HashMap<String, Object>());

		BisStoreManager bisStoreManager = SpringContextHolder.getBean("bisStoreManager");
		StringBuffer hql=new StringBuffer();
		hql.append("	from BisStore c");
		List<BisStore> storeList =bisStoreManager.find(hql.toString(), new HashMap<String, Object>());

		Map<String, List<String>> tmpStoreContMap = new HashMap<String, List<String>>();
		for (BisStoreContRel rel : contList) {
			String storeId = rel.getBisStore().getBisStoreId();
			List<String> tmpConts = tmpStoreContMap.get(storeId);
			if (tmpConts == null) {
				tmpConts = new ArrayList<String>();
			}
			if (!tmpConts.contains(rel.getBisCont().getBisContId())) {
				tmpConts.add(rel.getBisCont().getBisContId());
			}
			tmpStoreContMap.put(storeId, tmpConts);
		}

		for (BisStore store : storeList) {
			List<String> tmpConts = tmpStoreContMap.get(store.getBisStoreId());
			if (null == tmpConts) {
				continue;
			}
			for (String contId : tmpConts) {

				List<BisStore> stores = tmpContStoreMap.get(contId);
				if (null == stores) {
					stores = new ArrayList<BisStore>();
				}
				stores.add(store);

				tmpContStoreMap.put(contId, stores);
			}
		}
		contStoreMap = tmpContStoreMap;
	}

	private static List<BisStore> getStoreList(BisCont cont) {
		List<BisStore> stores = new ArrayList<BisStore>();
		for (BisStoreContRel rel : cont.getBisStoreContRels()) {
			stores.add(rel.getBisStore());
		}
		return stores;
	}

	public static String getStoreNos(String bisContId) {
		StringBuffer result = new StringBuffer();
		List<BisStore> stores = contStoreMap.get(bisContId);
		if (null == stores) {
			log.error("加载合同商铺异常【" + bisContId + "】");
			// System.out.println(bisContId);
			return "";
		}
		int count = 0;
		for (int i = 0; i < stores.size(); i++) {
			BisStore store = contStoreMap.get(bisContId).get(i);
			// 如果商铺编号为空则不添加
			if (StringUtils.isBlank(store.getStoreNo())) {
				continue;
			}
			if (count == 0) {
				result.append(store.getStoreNo());
			} else {
				result.append(",").append(store.getStoreNo());
			}
			count++;
		}
		return result.toString();
	}

	public static BigDecimal getStoreSquares(String bisContId) {
		BigDecimal sumSquare = new BigDecimal(0);
		List<BisStore> stores = contStoreMap.get(bisContId);
		for (int i = 0; i < stores.size(); i++) {
			BisStore store = contStoreMap.get(bisContId).get(i);
			sumSquare = sumSquare.add(store.getSquare());
		}
		return sumSquare;
	}
}
