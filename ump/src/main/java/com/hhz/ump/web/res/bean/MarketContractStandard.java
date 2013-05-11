package com.hhz.ump.web.res.bean;

import com.hhz.ump.web.res.baseBean.BaseTemplate;


/**
 * 营销合同杨标准版本审批表
 * @author Administrator
 *
 */
public class MarketContractStandard extends BaseTemplate {
	
	private String projectCd;//项目CD
	
	private String projectName;//项目名称 
	
	private String fileName;//文件名称 
	
	private String fileNum;//文件编号
	
	private String subscribeBook;//预定书
	
	private String modelContract;//商品房买卖合同范本
	
	private String modelContractRide;//合同范本条款
	
	private String makeRoomStandard;//交房装修标准
	
	private String marketContract;//营销合同范本
	
	private String plait;//编制
	
	private String revise;//修订
	
	private String  reviseNum;//修定几次
	
	private String   mainContract;//主要条款

	@Override
	public String getResProjectCd() {
		// TODO Auto-generated method stub
		return projectCd;
	}

	@Override
	public String getResProjectName() {
		// TODO Auto-generated method stub
		return projectName;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return fileName;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	public String getSubscribeBook() {
		return subscribeBook;
	}

	public void setSubscribeBook(String subscribeBook) {
		this.subscribeBook = subscribeBook;
	}

	public String getModelContract() {
		return modelContract;
	}

	public void setModelContract(String modelContract) {
		this.modelContract = modelContract;
	}

	public String getModelContractRide() {
		return modelContractRide;
	}

	public void setModelContractRide(String modelContractRide) {
		this.modelContractRide = modelContractRide;
	}

	public String getMakeRoomStandard() {
		return makeRoomStandard;
	}

	public void setMakeRoomStandard(String makeRoomStandard) {
		this.makeRoomStandard = makeRoomStandard;
	}

	public String getMarketContract() {
		return marketContract;
	}

	public void setMarketContract(String marketContract) {
		this.marketContract = marketContract;
	}

	public String getPlait() {
		return plait;
	}

	public void setPlait(String plait) {
		this.plait = plait;
	}

	public String getRevise() {
		return revise;
	}

	public void setRevise(String revise) {
		this.revise = revise;
	}

	public String getReviseNum() {
		return reviseNum;
	}

	public void setReviseNum(String reviseNum) {
		this.reviseNum = reviseNum;
	}

	public String getMainContract() {
		return mainContract;
	}

	public void setMainContract(String mainContract) {
		this.mainContract = mainContract;
	}

}
