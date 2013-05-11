package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.assm.AssmAccountManager;
import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IAutoImport;


/**
 * 固定资产申购单
 * @author lujunyun 2010-12-21
 */
public class FixedAssetsApplyForBuy extends BaseTemplate implements IAutoImport{
	private String applyDate;	//申购日期
	private String needDate;	//需用日期
	private String applyDept;	//申购部门
	private String applyUser;	//申请人
	private String applyContent;	//申购设备及配置需求
	private String totalMoney;//金额
	private String reason;	//申购理由
	private String applyNumber;//申购总量
	private String applyNumber2;//金额≥500元的资产数量

	
	private String contractTempletInfoId;
	//合同历史版id
	private String contractTempletHisId;
	
	//使用合同库
	private String contlib;
	//不使用合同库
	private String noncontlib;
	//合同编号
	private String contractNo;
	//合同名称
	private String contractName;
	//标准合同
	private String standard;
	
	//非标准合同
	private String nonstandard;
	//非标
	private String nostandard;
	
	//固定资产属性列表  -Add by liuzhihui 2012-05-15
	List<FixedAssetsProperty> fixedAssetsProperties = new ArrayList<FixedAssetsProperty>();
	
	
	/**
	 * @return the contractTempletInfoId
	 */
	public String getContractTempletInfoId() {
		return contractTempletInfoId;
	}

	/**
	 * @param contractTempletInfoId the contractTempletInfoId to set
	 */
	public void setContractTempletInfoId(String contractTempletInfoId) {
		this.contractTempletInfoId = contractTempletInfoId;
	}

	/**
	 * @return the contractTempletHisId
	 */
	public String getContractTempletHisId() {
		return contractTempletHisId;
	}

	/**
	 * @param contractTempletHisId the contractTempletHisId to set
	 */
	public void setContractTempletHisId(String contractTempletHisId) {
		this.contractTempletHisId = contractTempletHisId;
	}

	/**
	 * @return the contlib
	 */
	public String getContlib() {
		return contlib;
	}

	/**
	 * @param contlib the contlib to set
	 */
	public void setContlib(String contlib) {
		this.contlib = contlib;
	}

	/**
	 * @return the noncontlib
	 */
	public String getNoncontlib() {
		return noncontlib;
	}

	/**
	 * @param noncontlib the noncontlib to set
	 */
	public void setNoncontlib(String noncontlib) {
		this.noncontlib = noncontlib;
	}

	/**
	 * @return the contractNo
	 */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * @param contractNo the contractNo to set
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * @return the contractName
	 */
	public String getContractName() {
		return contractName;
	}

	/**
	 * @param contractName the contractName to set
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	/**
	 * @return the nonstandard
	 */
	public String getNonstandard() {
		return nonstandard;
	}

	/**
	 * @param nonstandard the nonstandard to set
	 */
	public void setNonstandard(String nonstandard) {
		this.nonstandard = nonstandard;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getNeedDate() {
		return needDate;
	}

	public void setNeedDate(String needDate) {
		this.needDate = needDate;
	}

	public String getApplyDept() {
		return applyDept;
	}

	public void setApplyDept(String applyDept) {
		this.applyDept = applyDept;
	}

	public String getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}

	public String getApplyContent() {
		return applyContent;
	}

	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return applyDept;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return applyUser;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	/**
	 * @return the standard
	 */
	public String getStandard() {
		return standard;
	}

	/**
	 * @param standard the standard to set
	 */
	public void setStandard(String standard) {
		this.standard = standard;
	}

	/**
	 * @return the nostandard
	 */
	public String getNostandard() {
		return nostandard;
	}

	/**
	 * @param nostandard the nostandard to set
	 */
	public void setNostandard(String nostandard) {
		this.nostandard = nostandard;
	}

	
	public List<FixedAssetsProperty> getFixedAssetsProperties() {
		return fixedAssetsProperties;
	}

	public void setFixedAssetsProperties(
			List<FixedAssetsProperty> fixedAssetsProperties) {
		this.fixedAssetsProperties = fixedAssetsProperties;
	}

	/**
	 * 自动导入资产台账
	 */
	@Override
	public void doImport() {
		AssmAccountManager assmAccountManager = SpringContextHolder.getBean("assmAccountManager");
		try {
			assmAccountManager.saveAssmAccountForRes(fixedAssetsProperties,getResApproveInfo().getResApproveInfoId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getApplyNumber() {
		return applyNumber;
	}

	public void setApplyNumber(String applyNumber) {
		this.applyNumber = applyNumber;
	}

	public String getApplyNumber2() {
		return applyNumber2;
	}

	public void setApplyNumber2(String applyNumber2) {
		this.applyNumber2 = applyNumber2;
	}
}
