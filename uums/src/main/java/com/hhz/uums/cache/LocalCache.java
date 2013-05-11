package com.hhz.uums.cache;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hhz.uums.entity.app.AppDictData;
import com.hhz.uums.entity.app.AppDictType;
import com.hhz.uums.web.PdCache;

/**
 * plas缓存(全局参数/字典)
 * @author huangbijin 2011-05-22
 *
 */
@Service
public class LocalCache {
	
	//缓存全局参数
	//缓存数据字典
	/* ***************************** 提供服务 ***********************************/

	public static String getAppDictName(String appDictTypeCd, String dictCd){
		return getAppDictName(appDictTypeCd, dictCd, false);
	}
	public static String getAppDictName(String appDictTypeCd, String dictCd, boolean rtnCodeFlg){
		AppDictType appDictType=(AppDictType) PdCache.getRefEntity(AppDictType.class, "dictTypeCd", appDictTypeCd);
		String dictName = "";
		List<AppDictData> dataList =appDictType.getAppDictDatas();// typeCdDataListMap.get(appDictTypeCd);
		if(dataList == null) {
			dictName = dictCd;
		} else{
			for (AppDictData data : dataList) {
				if(dictCd.equals(data.getDictCd())){
					dictName = data.getDictName();
				}
			}
		}
		return rtnCodeFlg?(dictCd + "-"):"" + dictName;
	}
}
