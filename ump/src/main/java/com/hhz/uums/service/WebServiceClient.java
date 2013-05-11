package com.hhz.uums.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.util.GlobalConstants;

/**
 * 
 * @author lijin
 * 
 */
public class WebServiceClient {

	private static Log log = LogFactory.getLog(WebServiceClient.class);

	public WSPlasService instance = null;
	
	private static final String PROJECT_BIZ_CD = GlobalConstants.PD_BIZ_CD;
	private static final String UAAP_CLIENT = "plas-client";


	public WSPlasService getPlasService() {
		return getInstance();
	}  
	
	public WSPlasService getInstance() {

		if (instance == null) {
			instance = SpringContextHolder.getBean(UAAP_CLIENT);
		}
		instance.setAppBizCd(PROJECT_BIZ_CD);
		String operateId = "";
		try {
			operateId = SpringSecurityUtils.getCurrentUiid();
		} catch (Exception e) {
			// 测试并发情况下,operatorId是否会一样
			log.error(" ************************ current opreatorId :" + operateId + ",异常!" + e.getCause());
		}
		
		instance.setOperatorId(operateId);

		return instance;
	}
}
