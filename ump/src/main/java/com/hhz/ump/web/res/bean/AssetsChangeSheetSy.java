package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.assm.AssmAccountManager;
import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IAutoImport;

/**
 * 办公资产维修/更换审批单(商业公司/总部)
 *
 * 2011-1-13
 */
public class AssetsChangeSheetSy extends BaseTemplate implements IAutoImport {

	private String applyDate;//申请日期
	private String applyNo;//单号
	private String applyUserCd;//申请人Cd
	private String applyUserName;//申请人Name
	private String orgName;//所在部门
	private String orgCd;//部门cd
	private List<AssetsChangeSheetSyProp> subProperties= new ArrayList<AssetsChangeSheetSyProp>();
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return applyNo;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public String getApplyUserCd() {
		return applyUserCd;
	}
	public void setApplyUserCd(String applyUserCd) {
		this.applyUserCd = applyUserCd;
	}
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public List<AssetsChangeSheetSyProp> getSubProperties() {
		return subProperties;
	}
	public void setSubProperties(List<AssetsChangeSheetSyProp> subProperties) {
		this.subProperties = subProperties;
	}
	@Override
	public void doImport() throws Exception {
		// TODO Auto-generated method stub
		AssmAccountManager accountManager=SpringContextHolder.getBean("assmAccountManager"); 
		accountManager.updateResRef(getSubProperties(), getResApproveInfoId());
	}
	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
