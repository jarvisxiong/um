/**
 * 
 */
package com.hhz.ump.aop;

import java.net.InetAddress;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.util.MethodInvoker;

import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.util.Constants;

/**
 * @author huangjian
 * 
 *         2011-5-25
 */
public class AppsMethodInvokingJobDetailFactoryBean extends MethodInvokingJobDetailFactoryBean {
	protected static final Log logger = LogFactory.getLog(AppsMethodInvokingJobDetailFactoryBean.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean
	 * #afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws ClassNotFoundException, NoSuchMethodException {
		super.afterPropertiesSet();

//		logger.info("origin jobClass : " + ((JobDetail) super.getObject()).getJobClass().getName());
		// Consider the concurrent flag to choose between stateful and stateless
		// job.
		if (MethodInvokingJob.class.getName().equals(((JobDetail) super.getObject()).getJobClass().getName())) {
			((JobDetail) super.getObject()).setJobClass(AppsMethodInvokingJob.class);
		} else {
			((JobDetail) super.getObject()).setJobClass(AppsStatefulMethodInvokingJob.class);
		}
//		logger.info("new jobClass : " + ((JobDetail) super.getObject()).getJobClass().getName());
	}

	public static class AppsMethodInvokingJob extends MethodInvokingJob {
		private MethodInvoker methodInvoker;
		private String errorMessage;

		/**
		 * Set the MethodInvoker to use.
		 */
		@Override
		public void setMethodInvoker(MethodInvoker methodInvoker) {
			this.methodInvoker = methodInvoker;
			this.errorMessage = "Could not invoke method '" + this.methodInvoker.getTargetMethod() + "' on target object ["
					+ this.methodInvoker.getTargetObject() + "]";
		}

		/**
		 * Invoke the method via the MethodInvoker.
		 */
		@Override
		protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
			String taskName = methodInvoker.getTargetClass().getName();
			// 事务名称
			String triggerName = context.getTrigger().getName();
			try {
				// 根据当前服务器主机名或者IP判断是否需要执行该任务
				// TODO Code

				String ipAddress = InetAddress.getLocalHost().getHostAddress();
				logger.info("IP:" + ipAddress);
				if (taskName.equals(PlasCache.class.getName())) {
					logger.info("刷新缓存");
					this.methodInvoker.invoke();
				} else if (ipAddress.equals(Constants.PROD_IP)) {
					// 调用具体task执行method代码
					// logger.info("端口:" + PdCache.getServerPort());
					// if (PdCache.getServerPort() == 0) {
					// logger.info("事务端口探测:" + PdCache.getServerPort());
					// ClusterRequest.setAllRequest();
					// }
					// if (PdCache.getServerPort() == ClusterRequest
					// .getQuartzNode()) {
					if (logger.isInfoEnabled()) {
						logger.info(triggerName + " start:" + new Date());
					}
					logger.info("正在执行事务" + triggerName);
					this.methodInvoker.invoke();
					if (logger.isInfoEnabled()) {
						logger.info(triggerName + " end:" + new Date());
					}
					// }
					// }
				}
			} catch (Exception ex) {
				logger.error(triggerName + " accounted a error : " + this.errorMessage, ex);
				throw new JobExecutionException(this.errorMessage, ex, false);
			}
		}
	}

	public static class AppsStatefulMethodInvokingJob extends AppsMethodInvokingJob {
	}
}