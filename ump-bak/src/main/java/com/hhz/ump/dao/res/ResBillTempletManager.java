package com.hhz.ump.dao.res;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResBillTemplet;

@Service
@Transactional
public class ResBillTempletManager extends BaseService<ResBillTemplet, String> {
	@Autowired
	private ResBillTempletDao resBillTempletDao;

	@Transactional(propagation = Propagation.SUPPORTS)
	public ResBillTemplet getTempletByCd(String templetCd) {
		return findUniqueBy("templetCd", templetCd);
	}

	public void saveResBillTemplet(ResBillTemplet resBillTemplet) {
		PowerUtils.setEmptyStr2Null(resBillTemplet);
		resBillTempletDao.save(resBillTemplet);
	}

	public void deleteResBillTemplet(String id) {
		resBillTempletDao.delete(id);
	}

	/**
	 * 搜索有效的模板
	 * @return
	 */
	public List<ResBillTemplet> loadActiveTemplate() {
		StringBuffer hql = new StringBuffer("from ResBillTemplet r where r.activeBl = ? ");
		List<ResBillTemplet> list = find(hql.toString(), true);
		return list;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<ResBillTemplet, String> getDao() {
		return resBillTempletDao;
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public Map<String, String> getTempletMap() {
		Map<String, String> templetMap = new LinkedHashMap<String, String>();
		List<ResBillTemplet> templets = getAll("templetName", false);
		for (ResBillTemplet templet : templets) {
			templetMap.put(templet.getTempletCd(), templet.getTempletName());
		}
		return templetMap;
	}

}
