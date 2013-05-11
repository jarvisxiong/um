/**
 * 
 */
package com.hhz.ump.web.res.baseBean;

/**
 * <p>关联的网批表单</p>
 * @author huangjian
 * @create 2012-6-8
 */
public class ResRelaApproveBill {
	private String resApproveInfoId;
	private String displayNo;
	private String authTypeCd;
	public ResRelaApproveBill(){
		
	}
	public ResRelaApproveBill(String resApproveInfoId,String displayNo,String authTypeCd){
		this.resApproveInfoId=resApproveInfoId;
		this.authTypeCd=authTypeCd;
		this.displayNo=displayNo;
	}
	public String getResApproveInfoId() {
		return resApproveInfoId;
	}
	public void setResApproveInfoId(String resApproveInfoId) {
		this.resApproveInfoId = resApproveInfoId;
	}
	public String getDisplayNo() {
		return displayNo;
	}
	public void setDisplayNo(String displayNo) {
		this.displayNo = displayNo;
	}
	public String getAuthTypeCd() {
		return authTypeCd;
	}
	public void setAuthTypeCd(String authTypeCd) {
		this.authTypeCd = authTypeCd;
	}
}
