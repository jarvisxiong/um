/**
 * 
 */
package com.hhz.ump.web.res.baseBean;

/**
 * <p>
 * 流程之前处理类
 * </p>
 * 
 * @author huangjian
 * @create 2012-2-24
 */
public interface IBeforeProcess {

	/**
	 * 在流程开始时，自动调用该方法
	 * 
	 * @return
	 */
	public boolean doBeforeProcess() throws Exception;
}
