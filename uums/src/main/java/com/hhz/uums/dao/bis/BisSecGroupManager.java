package com.hhz.uums.dao.bis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.dao.plas.PlasOperateLogManager;
import com.hhz.uums.entity.bis.BisSecGroup;
import com.hhz.uums.utils.OperConst;

@Service
@Transactional
public class BisSecGroupManager extends BaseService<BisSecGroup, String> {
	@Autowired
	private BisSecGroupDao bisSecGroupDao;
	@Autowired
	private PlasOperateLogManager uaapOperateLogManager;
	public void saveBisSecGroup(BisSecGroup bisSecGroup) {
		PowerUtils.setEmptyStr2Null(bisSecGroup);
		bisSecGroupDao.save(bisSecGroup);
	}

	public void deleteBisSecGroup(String id) {
		bisSecGroupDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisSecGroup, String> getDao() {
		return bisSecGroupDao;
	}
	public void deleteSecGroup(String id) {

		BisSecGroup secGroup =getEntity(id);
		String operUiid = SpringSecurityUtils.getLoginCode();
		String operUserName = SpringSecurityUtils.getCurUserName();

		// 记录日志
		uaapOperateLogManager.savePlasOperateLog(operUiid, operUserName, OperConst.SEC, OperConst.DEL,
				new StringBuffer("[").append(secGroup.getBisSecGroupId()).append(",").append(secGroup.getGroupBizCd())
						.append(",").append(secGroup.getGroupName()).append("]删除组! ").toString());

		this.deleteRelsByGroupId(id);
		bisSecGroupDao.delete(id);
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public BisSecGroup getSecGroupByBizCd(String tagTypeCd,String groupBizCd) {
		List<BisSecGroup> result = bisSecGroupDao.createCriteria(BisSecGroup.class)
								.add(Restrictions.eq("tagTypeCd",tagTypeCd))
								.add(Restrictions.eq("groupBizCd", groupBizCd))
								.list();
		if (result == null || result.size() == 0)
			return null;
		else
			return  result.get(0);
	}

	/**
	 * 删除组,同时删除组成员
	 * 
	 * @param secGroupId
	 */
	public int deleteRelsByGroupId(String secGroupId) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("secGroupId", secGroupId);
		int delRecords = getDao().batchExecute(
				" delete from SecGroupUserRel t where t.secGroup.secGroupId = :secGroupId ", values);
		// log.info(" 删除组成员关系 : " + delRecords + " 条记录!");
		return delRecords;
	}
}

