package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.assm.AssmAccountManager;
import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IAutoImport;

/**
 * 办公资产调拨单-商业
 */
public class FixedAssetsMoveSy extends BaseTemplate implements IAutoImport {
	private String applyDeptName;// 调拨设备申请部门
	private String applyDeptCd;// 调拨设备申请部门

	private String applyUser;// 申请人
	private String totalPrice;// 总价

	private String reason;// 调拨原因

	private List<FixedAssetsMoveSyAsset> fixedAssetsMove1Assets = new ArrayList<FixedAssetsMoveSyAsset>();

	
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

	public List<FixedAssetsMoveSyAsset> getFixedAssetsMove1Assets() {
		return fixedAssetsMove1Assets;
	}

	public void setFixedAssetsMove1Assets(
			List<FixedAssetsMoveSyAsset> fixedAssetsMove1Assets) {
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

	@Override
	public void doImport() throws Exception {
		// TODO Auto-generated method stub
		AssmAccountManager accountManager=SpringContextHolder.getBean("assmAccountManager"); 
		accountManager.updateUserDeptByRes(getFixedAssetsMove1Assets(), getResApproveInfoId());
	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return true;
	}



}
