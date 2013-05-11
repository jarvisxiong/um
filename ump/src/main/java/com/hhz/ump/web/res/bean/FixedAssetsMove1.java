package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 固定资产调拨单（1）
 * @author lujunyun 2010-12-21
 */
public class FixedAssetsMove1 extends BaseTemplate {
	private String applyDeptName;// 调拨设备申请部门
	private String applyDeptCd;// 调拨设备申请部门

	private String applyUser;// 申请人
	private String totalPrice;// 总价

	private String reason;// 调拨原因

	private List<FixedAssetsMove1Asset> fixedAssetsMove1Assets = new ArrayList<FixedAssetsMove1Asset>();

	
	public String getApplyDeptName() {
		return applyDeptName;
	}

	public void setApplyDeptName(String applyDeptName) {
		this.applyDeptName = applyDeptName;
	}

	public String getApplyDeptCd() {
		return applyDeptCd;
	}

	public void setApplyDeptCd(String applyDeptCd) {
		this.applyDeptCd = applyDeptCd;
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<FixedAssetsMove1Asset> getFixedAssetsMove1Assets() {
		return fixedAssetsMove1Assets;
	}

	public void setFixedAssetsMove1Assets(
			List<FixedAssetsMove1Asset> fixedAssetsMove1Assets) {
		this.fixedAssetsMove1Assets = fixedAssetsMove1Assets;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return applyDeptCd;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return applyDeptName;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return applyUser;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}



}
