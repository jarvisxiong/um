/**
 * 
 */
package com.hhz.ump.aop;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.cache.PlasCache;

/**
 * <p>重写ContextLoaderListener</p>
 * @author huangjian
 * @create 2012-3-12
 */
public class PdContextLoaderListener extends ContextLoaderListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		PlasCache plasCache=  SpringContextHolder.getBean("plasCache");
		plasCache.reloadCache();
	}
}
