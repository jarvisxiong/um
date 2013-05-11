package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
/**
 * <p>合同标准版本审批表(新)</p>
 * @author huangjian
 * @create 2012-2-29
 */
public class StandardContractApply extends BaseTemplate {

	private String contractFileName;//合同版本名称
	private String approveType1;//类型：新增
	private String approveType2;//类型：修订
	private String suitArea;//合同试用范围
	private String remark;//内容简述
	public String getApproveType1() {
		return approveType1;
	}
	public void setApproveType1(String approveType1) {
		this.approveType1 = approveType1;
	}
	public String getApproveType2() {
		return approveType2;
	}
	public void setApproveType2(String approveType2) {
		this.approveType2 = approveType2;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String getResTitleName() {
		return contractFileName;
	}
	public String getContractFileName() {
		return contractFileName;
	}
	public void setContractFileName(String contractFileName) {
		this.contractFileName = contractFileName;
	}
	public String getSuitArea() {
		return suitArea;
	}
	public void setSuitArea(String suitArea) {
		this.suitArea = suitArea;
	}
}
