/**  
 * BidProjectRoleVo.java  
 * com.hhz.ump.web.bid  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-12-26        zhongyubing  
 *  
 * Copyright (c) 2011, PD All Rights Reserved.  
 */

package com.hhz.ump.web.bid;

/**
 * ClassName:BidProjectRoleVo 
 * 
 * @author zhongyubing
 * @version
 * @since Ver 1.1
 * @Date 2011-12-26 上午10:23:34
 * 
 */
public class BidProjectRoleVo implements java.io.Serializable {

	private String areaCd;
	private String areaName;
	private String projectCd;
	private String projectName;
	private String userNames;
	private String userCds;
	private String active;
	private String remark;

	public String getAreaCd() {
		return areaCd;
	}

	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getUserCds() {
		return userCds;
	}

	public void setUserCds(String userCds) {
		this.userCds = userCds;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
