package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplatePay;

/**
 * 笔记本购置申请单成本控制中心审核部分
 * @author Administrator
 *
 */
public class NotebookComputer extends BaseTemplatePay {
	//成本控制中心审核
    //配置
    private String configuration;
    //品牌型号
    private String computerType;
    //价格
    private String price;
    //供应商
    private String suppplier;
    //备注
    private String compRemark;
    
	public String getConfiguration() {
		return configuration;
	}
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	public String getComputerType() {
		return computerType;
	}
	public void setComputerType(String computerType) {
		this.computerType = computerType;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSuppplier() {
		return suppplier;
	}
	public void setSuppplier(String suppplier) {
		this.suppplier = suppplier;
	}
	public String getCompRemark() {
		return compRemark;
	}
	public void setCompRemark(String compRemark) {
		this.compRemark = compRemark;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return computerType;
	}
}
