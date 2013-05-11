package com.hhz.ump.dao.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.app.UserPool;
import com.hhz.ump.util.CodeNameUtil;

import common.Logger;

@Service
@Transactional
public class UserPoolManager extends BaseService<UserPool, String> {
    public static final String USER_POOL_TYPE_OAMEETING = "oaMeeting";
    public static final String USER_POOL_TYPE_OAMEETINGGEREN = "oaMeetingGeren"; 
    public static final String USER_POOL_TYPE_EXECPLAN = "execPlan";
    
    private static final Logger logger = Logger.getLogger(UserPoolManager.class);
    
    @Autowired
    private UserPoolDao userPoolDao;

    public void saveUserPool(UserPool userPool) {
	PowerUtils.setEmptyStr2Null(userPool);
	userPoolDao.save(userPool);
    }

    public void deleteUserPool(String id) {
	userPoolDao.delete(id);
    }

    /**
     * 根据类型CD获取用户池用户列表
     * 
     * @param typeCd
     * @return
     */
    public List<UserPool> getUserPoolPersonsByTypeCd(String typeCd) {
	String hql = "from UserPool where typeCd=:typeCd";
	Map<String, Object> values = new HashMap<String, Object>();
	values.put("typeCd", typeCd);

	return this.find(hql, values);
    }


    /**
     * 保存某类型的用户池
     * 
     * @param typeCd
     * @return
     */
    public boolean saveUserPoolPerTypeCd(String typeCd, String userList,String PersonalAll) {
	UserPool userPool = null;
	List<UserPool> userPools = getUserPoolPersonsByTypeCd(typeCd);
	
	if (userPools != null && userPools.size() > 0) {
	    if (userPools.size() > 1) {
		logger.warn("类型" + typeCd + "有多个用户池列表");
		return false;
	    } else {
		userPool = userPools.get(0);
	    }
	} else {
	    userPool = new UserPool();
	    userPool.setTypeCd(typeCd);
	}
	if(PersonalAll.equals("manager")){
		userPool.setRemark("1");
	}else{
		userPool.setRemark("2");
	}
	userPool.setUserList(userList);
	saveUserPool(userPool);

	return true;
    }

    /**
     * 构造人员信息
     * 
     * @param userIds
     */
    public String buildPersonInfoData(String userIds) {
	String[] ids = userIds.split(";");
	StringBuilder result = new StringBuilder();
	String tempId = null;
	String tempName = null;

	for (int i = 0; i < ids.length; i++) {
	    tempId = ids[i];
	    tempName = CodeNameUtil.getUserNameByCd(tempId);
	    if (StringUtils.isBlank(tempId)) {
		continue;
	    }
	    tempName = CodeNameUtil.getUserNameByCd(tempId);
	    if (StringUtils.isBlank(tempName)) {
		if (i == ids.length - 1) {
		    result = result.deleteCharAt(result.length() - 1);
		}
		continue;
	    }
	    result.append("{\"uiid\":\"" + tempId + "\", \"userName\":\""
		    + tempName + "\"}");
	    if (i < ids.length - 1) {
		result.append("|");
	    }
	}

	return result.toString();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public HibernateDao<UserPool, String> getDao() {
	return userPoolDao;
    }

}
