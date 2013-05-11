package com.hhz.ump.dao.webim;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.webim.UserOnline;
import com.hhz.ump.util.DictContants;

@Service
@Transactional
public class UserOnlineManager extends BaseService<UserOnline, String> {

	private static Log log = LogFactory.getLog(UserOnlineManager.class);

	@Autowired
	private UserOnlineDao userOnlineDao;
	
	public void saveUserOnline(UserOnline userOnline) {
		PowerUtils.setEmptyStr2Null(userOnline);
		userOnlineDao.save(userOnline);
	}

	public void deleteUserOnline(String id) {
		userOnlineDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<UserOnline, String> getDao() {
		return userOnlineDao;
	}

	/**
	 * 搜索在线用户
	 * 
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public long getUserOnlineSize() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("online", new BigDecimal(DictContants.WEBIM_USER_ONLINE_ON));
		long count = countHqlResult("select count(0) from UserOnline t where t.onLine = :online",map);
		return count;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public Map<String, String> getUserOnlineMap() {
		Map<String, String> map = new HashMap<String, String>();
		List<UserOnline> result = userOnlineDao.createCriteria(UserOnline.class).add(
				Restrictions.eq("onLine",new BigDecimal(DictContants.WEBIM_USER_ONLINE_ON)))
				.list();
		if (result != null || result.size() > 0) {
			UserOnline u = null;
			for (int i = 0; i < result.size(); i++) {
				u = (UserOnline) result.get(i);
				if (u != null) {
					map.put(u.getUserCd(), String.valueOf(u.getOnLine()));
				}
			}
		}
		return map;
	}

	// 目前用户下线经常捕获不到,因此需要晚上定时清除用户在线人数.
	// 提供定时任务调用
	public void deleteAllData() {
		int count = this.getDao().batchExecute(" delete from UserOnline ", new HashMap<String, Object>());
		log.debug(" 运行定时任务(清除用户在线人数),影响" + count + "行");
//		UaapOrgCache.setUserOnlineCount(0);
	}

	// 刷新缓存
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void refreshUserOnlineCount() {
		long count = getUserOnlineSize();
//		UaapOrgCache.setUserOnlineCount(count);
		log.debug(" 运行定时任务(刷新在线人数),影响" + count + "行");
	}
	// uaap
	// UaapUserManager.resetFailureTimes
	//
	// powerdesk
	// UserOnlineManager.deleteAllData
}

