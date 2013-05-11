package com.hhz.ump.web.res.bean;

import java.util.ArrayList;
import java.util.List;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.sc.ScContractTempletInfoManager;
import com.hhz.ump.web.res.baseBean.BaseSupTemplate;
import com.hhz.ump.web.res.baseBean.IStatusModifyListener;

public class BizRentContractApp extends BaseSupTemplate implements IStatusModifyListener{
	//文件标题
	private String fileName;
	//所在项目
	private String projectCd;
	//所在项目名称
	private String projectName;
	//合同双方 甲方
	private String partA;
	private String partB;
	private String partC;
	private String contractNo;//合同编号-Add for import contract by zhuxj on 2012.4.12.
	private String manageBrand;//经营品牌
	private String manageBusiness;//经营业态
	private String rentPosition;//租凭位置
	private String rentArea;//计租面积
	private String rentPeriod;//租期
	private String rentMoney;//租金
	private String manageMoney;//物业管理费或综合管理费
	private String payTime;//交付时间
	private String startTime;//开业时间
	private String fitPeriod;//装修期
	private String rentDownPeriod;//租金优惠期
	private String otherContent;//其它事项
	private String standardContId;//标准合同
	private List<BizRentContractItem> otherProperties = new ArrayList<BizRentContractItem>();
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public String getPartA() {
		return partA;
	}
	public void setPartA(String partA) {
		this.partA = partA;
	}
	public String getPartB() {
		return partB;
	}
	public void setPartB(String partB) {
		this.partB = partB;
	}
	public String getPartC() {
		return partC;
	}
	public void setPartC(String partC) {
		this.partC = partC;
	}
	public String getManageBrand() {
		return manageBrand;
	}
	public void setManageBrand(String manageBrand) {
		this.manageBrand = manageBrand;
	}
	public String getManageBusiness() {
		return manageBusiness;
	}
	public void setManageBusiness(String manageBusiness) {
		this.manageBusiness = manageBusiness;
	}
	public String getRentPosition() {
		return rentPosition;
	}
	public void setRentPosition(String rentPosition) {
		this.rentPosition = rentPosition;
	}
	public String getRentArea() {
		return rentArea;
	}
	public void setRentArea(String rentArea) {
		this.rentArea = rentArea;
	}
	public String getRentMoney() {
		return rentMoney;
	}
	public void setRentMoney(String rentMoney) {
		this.rentMoney = rentMoney;
	}
	public String getManageMoney() {
		return manageMoney;
	}
	public void setManageMoney(String manageMoney) {
		this.manageMoney = manageMoney;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getFitPeriod() {
		return fitPeriod;
	}
	public void setFitPeriod(String fitPeriod) {
		this.fitPeriod = fitPeriod;
	}
	public String getRentDownPeriod() {
		return rentDownPeriod;
	}
	public void setRentDownPeriod(String rentDownPeriod) {
		this.rentDownPeriod = rentDownPeriod;
	}
	public String getOtherContent() {
		return otherContent;
	}
	public void setOtherContent(String otherContent) {
		this.otherContent = otherContent;
	}
	public String getStandardContId() {
		return standardContId;
	}
	public void setStandardContId(String standardContId) {
		this.standardContId = standardContId;
	}
	public String getRentPeriod() {
		return rentPeriod;
	}
	public void setRentPeriod(String rentPeriod) {
		this.rentPeriod = rentPeriod;
	}
	public List<BizRentContractItem> getOtherProperties() {
		return otherProperties;
	}
	public void setOtherProperties(List<BizRentContractItem> otherProperties) {
		this.otherProperties = otherProperties;
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return fileName;
	}
	@Override
	public  void doAutoImport(){
		
	};
	@Override
	public void doImport(){
		
	}
	@Override
	public boolean isAutoImport(){
		return true;
	}
	@Override
	public String getContractNo() {
		return contractNo;
	}
	@Override
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}


	/*
	 * 引用网批的接口，调整标准合同的网批锁定状态字段resApproveInfoId和statusCd
	 * @see com.hhz.ump.web.res.baseBean.IStatusModifyListener#statusModified(java.lang.String, java.lang.String)
	 */
	@Override
	public void statusModified(String oldStatuCd, String newStatuCd) throws Exception {
		ScContractTempletInfoManager  scContractTempletInfoManager = SpringContextHolder.getBean("scContractTempletInfoManager");
		scContractTempletInfoManager.statusModified(oldStatuCd, newStatuCd, this.getResApproveInfoId(),
				this.getResApproveInfo().getDisplayNo(), this.getContractTempletInfoId(), "BizRentContractApp");
	}
}
