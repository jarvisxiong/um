/**
 * 
 */
package com.hhz.uums.aop;

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

/**
 * @author huangj 2010-1-1
 */
@Aspect
public class HibernateAspect {
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
		String userCd = SpringSecurityUtils.getLoginCode();
		if (StringUtils.isEmpty(idValue)) {
			Date createdDate = (Date) PropertyUtils.getProperty(entity, "createdDate");
			if (createdDate == null) {
				BeanUtils.setProperty(entity, "createdDate", now);
			}
			String creator = BeanUtils.getProperty(entity, "creator");
			if (StringUtils.isEmpty(creator)) {
				BeanUtils.setProperty(entity, "creator", userCd);
			}
			BeanUtils.setProperty(entity, "createdDeptCd", "");
		}
		BeanUtils.setProperty(entity, "updatedDate", now);
		BeanUtils.setProperty(entity, "updator", userCd);
		BeanUtils.setProperty(entity, "updatedDeptCd", "");
		point.proceed();
	}
}
