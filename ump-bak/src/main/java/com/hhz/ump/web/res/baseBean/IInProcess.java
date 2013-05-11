/**
 * 
 */
package com.hhz.ump.web.res.baseBean;

/**
 * <p>
 * 进入流程处理接口
 * </p>
 * 
 * @author huangjian
 * @create 2012-2-24
 */
public interface IInProcess {

	/**
	 * 进入流程时，自动调用改方法
	 * 
	 * @return
	 */
	public boolean doInProcess() throws Exception;
}
