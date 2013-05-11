/**
 * 
 */
package com.hhz.ump.web.res.baseBean;

/**
 * @author huangj
 *         2010-8-20
 */
public class ResApproverUser {
	private String approverUserCd;
	private String approverUserName;
	private String nodeCd;
	private String fixed;//固定标识:0-否,1-是
	public ResApproverUser(){
		
	}
	public ResApproverUser(String approverUserCd, String approverUserName) {
		this.approverUserCd = approverUserCd;
		this.approverUserName = approverUserName;
	}
	public ResApproverUser(String approverUserCd, String approverUserName,String fixed,String nodeCd) {
		this.approverUserCd = approverUserCd;
		this.approverUserName = approverUserName;
		this.fixed = fixed;
		this.nodeCd = nodeCd;
	}

	public String getApproverUserCd() {
		return approverUserCd;
	}

	public void setApproverUserCd(String approverUserCd) {
		this.approverUserCd = approverUserCd;
	}

	public String getApproverUserName() {
		return approverUserName;
	}

	public void setApproverUserName(String approverUserName) {
		this.approverUserName = approverUserName;
	}
	
	public String getFixed() {
		return fixed;
	}
	public void setFixed(String fixed) {
		this.fixed = fixed;
	}
	public String getNodeCd() {
		return nodeCd;
	}
	public void setNodeCd(String nodeCd) {
		this.nodeCd = nodeCd;
	}
}
