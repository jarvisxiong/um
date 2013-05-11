package com.hhz.ump.dao.oa;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.lob.ClobImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaPublicAffairs;
import com.hhz.ump.util.Util;

@Service
@Transactional
public class OaPublicAffairsManager extends BaseService<OaPublicAffairs, String> {
	@Autowired
	private OaPublicAffairsDao oaPublicAffairsDao;

	public void saveOaPublicAffairs(OaPublicAffairs oaPublicAffairs) {
		PowerUtils.setEmptyStr2Null(oaPublicAffairs);
		if(oaPublicAffairs.getEnabledFlg() == null){
			oaPublicAffairs.setEnabledFlg("1");
		}
		if(oaPublicAffairs.getDeleteFlg() == null){
			oaPublicAffairs.setDeleteFlg("0");
		}
		oaPublicAffairsDao.save(oaPublicAffairs);
	}

	public void deleteOaPublicAffairs(String id) {
		oaPublicAffairsDao.delete(id);
	}
	
	/**
	 * 阅读帖子时要把当前用户加入到阅读人列表，同时增加点击次数
	 * 
	 * @param newsId
	 * @param userId
	 * @return
	 */
	public OaPublicAffairs readPostDetail(String dataId, String userId) {
		if (StringUtils.isBlank(dataId))
			throw new IllegalArgumentException("帖子ID不能为空.");
		if (StringUtils.isBlank(userId))
			throw new IllegalArgumentException("用户ID不能为空.");

		OaPublicAffairs affairs = this.getEntity(dataId);
		String readers = "";
		if (affairs.getReaders() == null) {
			readers = userId + ",";
		} else {
			try {
				readers = Util.clob2String(affairs.getReaders());
				if (!readers.contains(userId)) {
					readers = readers + userId + ",";
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		long count = affairs.getClickCount() == null ? 0 : affairs.getClickCount();

		String hql = "update OaPublicAffairs set clickCount=:clickCount, readers=:readers where oaPublicAffairsId=:dataId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clickCount", Long.valueOf(count + 1));
		params.put("readers", new ClobImpl(readers));
		params.put("dataId", dataId);
		this.getDao().batchExecute(hql, params);

		return affairs;
	}
	
	@Override
	public HibernateDao<OaPublicAffairs, String> getDao() {
		return oaPublicAffairsDao;
	}
	
}

