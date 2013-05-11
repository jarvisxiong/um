/**
 * 
 */
package com.hhz.ump.web.res.baseBean;

/**
 * <p>网批状态改变监听</p>
 * @author huangjian
 * @create 2012-3-29
 */
public interface IStatusModifyListener {
	
	/**
	 * 新增:0,审批中:1,完成审批:2,驳回:3,已删除:4
	 * 
	 * @param oldStatuCd	旧状态
	 * @param newStatuCd	新状态
	 */
	public void statusModified(String oldStatuCd,String newStatuCd) throws Exception;

}
