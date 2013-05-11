package com.hhz.ump.web.res.bean;
/**
 * 意向投标公司申请单元表
 * @author Administrator
 *
 */
public class SupInviUnitProperty {
	//意向投标公司
    private String signProject;
    //注册资金
    private String signMoney;
    //联系人
    private String signPeople;
    //联系电话
    private String signPhone;
    //电子邮件
    private String signMail;
    //备注
    private String remark;
    //删除标志
    private String deleteBl;
    
	public String getSignProject() {
		return signProject;
	}
	public void setSignProject(String signProject) {
		this.signProject = signProject;
	}
	public String getSignMoney() {
		return signMoney;
	}
	public void setSignMoney(String signMoney) {
		this.signMoney = signMoney;
	}
	public String getSignPeople() {
		return signPeople;
	}
	public void setSignPeople(String signPeople) {
		this.signPeople = signPeople;
	}
	public String getSignPhone() {
		return signPhone;
	}
	public void setSignPhone(String signPhone) {
		this.signPhone = signPhone;
	}
	public String getSignMail() {
		return signMail;
	}
	public void setSignMail(String signMail) {
		this.signMail = signMail;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeleteBl() {
		return deleteBl;
	}
	public void setDeleteBl(String deleteBl) {
		this.deleteBl = deleteBl;
	}
}
