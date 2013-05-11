package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import com.hhz.ump.web.res.baseBean.BaseTemplate;

/**
 * 固定资产调拨单（2）
 * @author lujunyun 2010-12-21
 */
public class FixedAssetsMove2 extends BaseTemplate {
	private String outDeptName;// 调出公司
	private String outDeptCd;// 调出公司

	private String outUser;// 调出经办人

	private String totalPrice;// 总价
	
	private String outReason;// 调出固定资产原因

	private List<FixedAssetsMove2Asset> fixedAssetsMove2Assets = new ArrayList<FixedAssetsMove2Asset>();

	private String inDeptName;// 调入公司
	private String inDeptCd;// 调入公司

	private String inUser;// 调入经办人

	private String inReason;// 申请调入固定资产原因及配置需求

	
	public String getOutDeptName() {
		return outDeptName;
	}

	public void setOutDeptName(String outDeptName) {
		this.outDeptName = outDeptName;
	}

	public String getInDeptName() {
		return inDeptName;
	}

	public void setInDeptName(String inDeptName) {
		this.inDeptName = inDeptName;
	}

	public String getOutDeptCd() {
		return outDeptCd;
	}

	public void setOutDeptCd(String outDeptCd) {
		this.outDeptCd = outDeptCd;
	}

	public String getOutReason() {
		return outReason;
	}

	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}



	public List<FixedAssetsMove2Asset> getFixedAssetsMove2Assets() {
		return fixedAssetsMove2Assets;
	}

	public void setFixedAssetsMove2Assets(
			List<FixedAssetsMove2Asset> fixedAssetsMove2Assets) {
		this.fixedAssetsMove2Assets = fixedAssetsMove2Assets;
	}

	public String getInDeptCd() {
		return inDeptCd;
	}

	public void setInDeptCd(String inDeptCd) {
		this.inDeptCd = inDeptCd;
	}


	public String getOutUser() {
		return outUser;
	}

	public void setOutUser(String outUser) {
		this.outUser = outUser;
	}

	public String getInUser() {
		return inUser;
	}

	public void setInUser(String inUser) {
		this.inUser = inUser;
	}

	public String getInReason() {
		return inReason;
	}

	public void setInReason(String inReason) {
		this.inReason = inReason;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return inDeptCd;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return inDeptName;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return outUser;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}


}
