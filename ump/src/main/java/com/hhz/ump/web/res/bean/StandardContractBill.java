package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;
/**
 * <p>标准合同版本审批表</p>
 * @author huangjian
 * @create 2011-9-14
 */
public class StandardContractBill extends BaseTemplate {

	private String contractFileName;//合同版本名称
	private String fileType1;//文本类型：租赁意向书、租赁合同
	private String fileType2;//文本类型：其他各类协议
	private String approveType1;//审批类型：新增
	private String approveType2;//审批类型：修订
	private String remark;//合同试用范围
	public String getContractFileName() {
		return contractFileName;
	}
	public void setContractFileName(String contractFileName) {
		this.contractFileName = contractFileName;
	}
	public String getFileType1() {
		return fileType1;
	}
	public void setFileType1(String fileType1) {
		this.fileType1 = fileType1;
	}
	public String getFileType2() {
		return fileType2;
	}
	public void setFileType2(String fileType2) {
		this.fileType2 = fileType2;
	}
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
		// TODO Auto-generated method stub
		return contractFileName;
	}
}
