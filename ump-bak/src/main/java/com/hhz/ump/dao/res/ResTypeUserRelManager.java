package com.hhz.ump.dao.res;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResTypeUserRel;
import com.hhz.ump.util.DictContants;

@Service
@Transactional
public class ResTypeUserRelManager extends BaseService<ResTypeUserRel, String> {
	@Autowired
	private ResTypeUserRelDao resTypeUserRelDao;

	public void saveResTypeUserRel(ResTypeUserRel resTypeUserRel) {
		PowerUtils.setEmptyStr2Null(resTypeUserRel);
		resTypeUserRelDao.save(resTypeUserRel);
	}

	public void saveResTypeUserRels(List<ResTypeUserRel> resTypeUserRels) {
		for (ResTypeUserRel resTypeUserRel : resTypeUserRels) {
			saveResTypeUserRel(resTypeUserRel);
		}
	}

	public void deleteResTypeUserRel(String id) {
		resTypeUserRelDao.delete(id);
	}

	/**
	 * 指定用户可搜索的表单
	 * 
	 * @param uiid
	 * @return
	 */
	public List<ResTypeUserRel> loadMyAuthTypeSrh(String uiid) {
		Criterion c1 = Restrictions.eq("userCd", uiid);
		Criterion c2 = Restrictions.eq("relTypeCd", DictContants.RES_USER_REL_SEARCH);
		List<ResTypeUserRel> rels = resTypeUserRelDao.find(c1, c2);
		return rels;
	}

	/**
	 * 指定用户可自动推送的表单
	 * 
	 * @param uiid
	 * @return
	 */
	public List<ResTypeUserRel> loadMyAuthTypePush(String uiid) {
		Criterion c1 = Restrictions.eq("userCd", uiid);
		Criterion c2 = Restrictions.eq("relTypeCd", DictContants.RES_USER_REL_PUSH);
		List<ResTypeUserRel> rels = resTypeUserRelDao.find(c1, c2);
		return rels;
	}
	/**搜索表单对应的推送人员
	 * @param authTypeCd
	 * @return
	 */
	public List<ResTypeUserRel> loadPushUser(String authTypeCd) {
		Criterion c1 = Restrictions.eq("authTypeCd", authTypeCd);
		Criterion c2 = Restrictions.eq("relTypeCd", DictContants.RES_USER_REL_PUSH);
		List<ResTypeUserRel> rels = resTypeUserRelDao.find(c1, c2);
		return rels;
	}

	@Override
	public HibernateDao<ResTypeUserRel, String> getDao() {
		return resTypeUserRelDao;
	}

}
