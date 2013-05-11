/**
 * 
 */
package com.hhz.ump.web.wap;

import org.apache.struts2.convention.annotation.Namespace;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.ump.web.res.ResApproveDelayAction;

/**
 * <p>
 * 手机版延期申请
 * </p>
 * 
 * @author huangjian
 * @create 2011-10-27
 */
@Namespace("/wap")
public class WapApproveDelayAction extends ResApproveDelayAction {

	private static final long serialVersionUID = 6413041248750528586L;

	public void prepareApply() throws Exception {
		prepareModel();
	}

	/**
	 * 申请
	 * 
	 * @return
	 * @throws Exception
	 */
	public String apply() throws Exception {
		if (resApproveDelayManager.latestProcessDlay(getResApproveId()) != null) {
			Struts2Utils.renderText("已经申请延期,等待处理中...");
			return null;
		} else {
			super.doApply();

		}
		return "apply";
	}
	@Override
	public String loadChief() {
		super.loadChief();
		return "load";
	}
}
