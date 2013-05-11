package com.hhz.ump.aop;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class BisAspect {
	/*private final Logger logger = LoggerFactory.getLogger(getClass());
	@Pointcut("execution(* com.hhz.ump.dao.bis.BisMustManager.approveMust*(..)) ")
	public void bisMustOpera() {
	}
	@Around("bisMustOpera() && args(ids,..) ")
	public void afterMustOpera(ProceedingJoinPoint point,String[] ids) throws Throwable{
		point.proceed();
		BisFactManager factManager =  SpringContextHolder.getBean("bisFactManager");
		BisMustManager mustManager =  SpringContextHolder.getBean("bisMustManager");
		for(int i=0 ; i<ids.length;i++){
			System.out.println("审核应收"+ids[i]);
			BisMust en = mustManager.getEntity(ids[i]);
			factManager.processFactMoney(null, ids[i]);
		}
		System.out.println("审核应收结束");
	}*/
}

