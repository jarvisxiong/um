package com.hhz.ump.dao.res;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.entity.res.ResApproveShareHis;

@Service
@Transactional
public class ResApproveShareHisManager extends BaseService<ResApproveShareHis, String> {
	@Autowired
	private ResApproveShareHisDao resApproveShareHisDao;

	public void saveResApproveShareHis(ResApproveShareHis resApproveShareHis) {
		PowerUtils.setEmptyStr2Null(resApproveShareHis);
		resApproveShareHisDao.save(resApproveShareHis);
	}

	public void save(ResApproveInfo resApproveInfo, String toUserCds) {

		Map<String, Object> values = new HashMap<String, Object>();
		values.put("p_id", resApproveInfo.getResApproveInfoId());
		values.put("p_nodeCd", resApproveInfo.getNodeCd());
		values.put("p_approveLevel", resApproveInfo.getApproveLevel());
		values.put("p_userCd", SpringSecurityUtils.getCurrentUiid());
		StringBuffer hql = new StringBuffer("from ResApproveShareHis h where h.resApproveInfo.resApproveInfoId=:p_id");
		hql.append(" and h.nodeCd=:p_nodeCd");
		hql.append(" and h.approveLevel=:p_approveLevel");
		hql.append(" and h.userCd=:p_userCd");
		List<ResApproveShareHis> list = find(hql.toString(), values);
		ResApproveShareHis resApproveShareHis;
		if (StringUtils.isEmpty(toUserCds)) {
			if (list.size() == 1) {
				resApproveShareHis = list.get(0);
				delete(resApproveShareHis);
			}
		} else {
			if (list.size() == 1) {
				resApproveShareHis = list.get(0);
			} else {
				resApproveShareHis = new ResApproveShareHis();
				resApproveShareHis.setResApproveInfo(resApproveInfo);
				resApproveShareHis.setApproveLevel(resApproveInfo.getApproveLevel());
				resApproveShareHis.setNodeCd(resApproveInfo.getNodeCd());
				resApproveShareHis.setUserCd(SpringSecurityUtils.getCurrentUiid());
			}
			resApproveShareHis.setToUserCds(toUserCds);
			saveResApproveShareHis(resApproveShareHis);
		}
	}

	public void deleteResApproveShareHis(String id) {
		resApproveShareHisDao.delete(id);
	}

	@Override
	public HibernateDao<ResApproveShareHis, String> getDao() {
		return resApproveShareHisDao;
	}

}
