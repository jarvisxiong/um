/**
 * 
 */
package com.hhz.ump.web.res.baseBean;

/**
 * @author huangjian
 * 
 *         2011-5-3
 */
public interface IAutoImport {

	/**
	 * 批完导入供应商
	 */
	public void doImport() throws Exception;

	public boolean isAutoImport();
}
