/**
 * 
 */
package com.hhz.ump.aop;

import java.lang.reflect.Field;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.orm.hibernate.SimpleHibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.ump.util.Constants;

/**
 * @author huangj 2010-1-1
 */
@Aspect
public class HibernateAspect {
	private static final String UPDATOR = "updator";
	private static final String UPDATEDDATE = "updatedDate";
	private static final String CREATOR = "creator";
	private static final String CREATEDDATE = "createdDate";
	private static final String CREATEDDEPTCD = "createdDeptCd";
	private static final String UPDATEDDEPTCD = "updatedDeptCd";
	private static final String CREATEDCENTERCD = "createdCenterCd";
	private static final String CREATEDPOSITIONCD = "createdPositionCd";
	private static final String UPDATEDCENTERCD = "updatedCenterCd";
	private static final String UPDATEDPOSITIONCD = "updatedPositionCd";
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Pointcut("execution(* save*(..)) ")
	public void saveOrUpdate() {
	}

	@Around("saveOrUpdate() && target(tar) && args(entity) && @target(org.springframework.stereotype.Repository)")
	public void aroundSave(ProceedingJoinPoint point, Object tar, Object entity) throws Throwable {
		// logger.info(tar.toString());
		SimpleHibernateDao dao = (SimpleHibernateDao) tar;
		String idValue = (String) dao.getIdValue(entity);
		Date now = new Date();
		String userCd = SpringSecurityUtils.getCurrentUiid();
		// 如果当然登录用户用空，取Admin
		if (StringUtils.isEmpty(userCd)) {
			userCd = Constants.ADMIN;
		}
		String deptCd= SpringSecurityUtils.getCurrentDeptCd();
		String centerCd= SpringSecurityUtils.getCurrentCenterCd();
		String positionCd= SpringSecurityUtils.getCurrentPositionCd();
		if (StringUtils.isEmpty(idValue)) {
			// 创建时间
			Date createdDate = (Date) PropertyUtils.getProperty(entity, CREATEDDATE);
			if (createdDate == null) {
				BeanUtils.setProperty(entity, CREATEDDATE, now);
			}
			// 创建人
			String creator = getValue(entity, CREATOR);
			if (StringUtils.isEmpty(creator)) {
				BeanUtils.setProperty(entity, CREATOR, userCd);
			}
			// 创建部门
			String createdDeptCd = getValue(entity, CREATEDDEPTCD);
			if (StringUtils.isEmpty(createdDeptCd)) {
				setValue(entity, CREATEDDEPTCD, deptCd);
			}
			// 创建中心
			String createdCenterCd = getValue(entity, CREATEDCENTERCD);
			if (StringUtils.isEmpty(createdCenterCd)) {
				setValue(entity, CREATEDCENTERCD, centerCd);
			}
			// 创建系统职位
			String createdPositionCd = getValue(entity, CREATEDPOSITIONCD);
			if (StringUtils.isEmpty(createdPositionCd)) {
				setValue(entity, CREATEDPOSITIONCD, positionCd);
			}
		}
		setValue(entity, UPDATEDDATE, now); // 更新时间
		setValue(entity, UPDATOR, userCd);// 更新人
	
		setValue(entity, UPDATEDDEPTCD,deptCd);// 更新部门
		setValue(entity, UPDATEDCENTERCD, centerCd);// 更新中心
		setValue(entity, UPDATEDPOSITIONCD, positionCd);// 更新系统职位
		point.proceed();
	}

	private void setValue(Object entity, String fieldName, Object fieldValue) {
		try {
			BeanUtils.setProperty(entity, fieldName, fieldValue);
		} catch (Exception e) {
		}
	}
	
	private String getValue(Object entity, String fieldName) {
		String fieldValue=null;
		try {
			fieldValue=BeanUtils.getProperty(entity, fieldName);
		} catch (Exception e) {
		}
		return fieldValue;
	}
	
	private boolean isExistsProperty(Object entity, String propertyName) {
		boolean flag = false;
		Field[] fields = entity.getClass().getFields();
		for (Field field : fields) {
			if (field.getName().equals(propertyName)) {
				flag = true;
			}
		}
		return flag;
	}
}
