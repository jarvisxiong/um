package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.entity.app.AppDictData;
import com.hhz.ump.entity.app.AppDictType;
import com.hhz.ump.entity.cont.ContLedger;
import com.hhz.ump.entity.cont.ContVisaUpdate;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;

/**
 * 设计变更审批表
 * @author Administrator
 *
 */
public class DesignVisaApprove extends BaseContLedgerTemplate {
	
	/**
	 * 涉及园林景观、公共区域装修、精装修（销售楼盘）和“三点一线”（售楼处、样板房、景观示范区和看房通道）
	 */
	private String isThreeLine;
	/**
	 * 重大设计变更
	 */
	private String importDesignVisa;
	/**
	 * 其他设计变更
	 */
	private String guraDesignVisa;
	/**
	* 其他设计变更(酒店室内设计、专项设计)
	*/
	private String guraDesignVisaHotel;
	/**
	 * 物业移交前商户提出
	 */
	private String tenantsHadVisaBefore;
	/**
	 * 物业移交后商户提出
	 */
	private String tenantsHadVisa;
	/**
	 * 合同编号
	 */
	private String contNo;
	/**
	 * 变更编号
	 */
	private String visaNo;
	/**
	 * 合同名称
	 */
	private String contName;
	/**
	 * 乙方
	 */
	private String partB;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 变更内容
	 */
	private String visaContent;
	
	private String visaCause;//变更原因
	private String designWrong;//设计错漏  变更原因
	private String saleCause;//招商、销售原因
	private String constructCause;//施工原因
	private String partACause;//甲方原因
	private String otherCause;//其它
	private String period;
	/**
	 * 拟签发变更单编号
	 */
	private String issueVisaNo;
	/**
	 * 原图纸编号
	 */
	private String drawingNo;
	/**
	 * 合同总价
	 */
	private String totalPrice;
	/**
	 * 已确认合同总价
	 */
	private String updateTotal;
	/**
	 * 已累计变更预估费用（元）
	 */
	private String prepareFeeTotal;
	/**
	 * 已预估变更比例
	 */
	private String preFeeTotRate;
	/**
	 * 本次预估费用（元）
	 */
	private String prepareFee;
	/**
	 * 本次预估变更比例
	 */
	private String preFeeRate;
	/**
	 * 备注说明
	 */
	private String remark;
	/**
	 * 拟签发设计变更图
	 */
	private String issueVisaDrawId;
	/**
	 * 变更预算
	 */
	private String updateBudgetId;
	/**
	 * 提出部门的文件
	 */
	private String adjureFileId;
	
	private String land;//仅与地产有关
	private String hotel;//与酒店有关
	private String trade;//与行业有关
	private String estate;//与物业有关
	
	private String resApproveCd;
	
	private String resApproveId;

	public String getImportDesignVisa() {
		return importDesignVisa;
	}

	public void setImportDesignVisa(String importDesignVisa) {
		this.importDesignVisa = importDesignVisa;
	}

	public String getGuraDesignVisa() {
		return guraDesignVisa;
	}

	public void setGuraDesignVisa(String guraDesignVisa) {
		this.guraDesignVisa = guraDesignVisa;
	}

	public String getTenantsHadVisaBefore() {
		return tenantsHadVisaBefore;
	}

	public void setTenantsHadVisaBefore(String tenantsHadVisaBefore) {
		this.tenantsHadVisaBefore = tenantsHadVisaBefore;
	}

	public String getTenantsHadVisa() {
		return tenantsHadVisa;
	}

	public void setTenantsHadVisa(String tenantsHadVisa) {
		this.tenantsHadVisa = tenantsHadVisa;
	}

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getVisaNo() {
		return visaNo;
	}

	public void setVisaNo(String visaNo) {
		this.visaNo = visaNo;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getPartB() {
		return partB;
	}

	public void setPartB(String partB) {
		this.partB = partB;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getVisaContent() {
		return visaContent;
	}

	public void setVisaContent(String visaContent) {
		this.visaContent = visaContent;
	}

	public String getIssueVisaNo() {
		return issueVisaNo;
	}

	public void setIssueVisaNo(String issueVisaNo) {
		this.issueVisaNo = issueVisaNo;
	}

	public String getDrawingNo() {
		return drawingNo;
	}

	public void setDrawingNo(String drawingNo) {
		this.drawingNo = drawingNo;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getUpdateTotal() {
		return updateTotal;
	}

	public void setUpdateTotal(String updateTotal) {
		this.updateTotal = updateTotal;
	}

	public String getPrepareFeeTotal() {
		return prepareFeeTotal;
	}

	public void setPrepareFeeTotal(String prepareFeeTotal) {
		this.prepareFeeTotal = prepareFeeTotal;
	}

	public String getPreFeeTotRate() {
		return preFeeTotRate;
	}

	public void setPreFeeTotRate(String preFeeTotRate) {
		this.preFeeTotRate = preFeeTotRate;
	}

	public String getPrepareFee() {
		return prepareFee;
	}

	public void setPrepareFee(String prepareFee) {
		this.prepareFee = prepareFee;
	}

	public String getPreFeeRate() {
		return preFeeRate;
	}

	public void setPreFeeRate(String preFeeRate) {
		this.preFeeRate = preFeeRate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String getHotel() {
		return hotel;
	}

	@Override
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	@Override
	public String getTrade() {
		return trade;
	}

	@Override
	public void setTrade(String trade) {
		this.trade = trade;
	}

	@Override
	public String getEstate() {
		return estate;
	}

	@Override
	public void setEstate(String estate) {
		this.estate = estate;
	}

	public String getDesignWrong() {
		return designWrong;
	}

	public void setDesignWrong(String designWrong) {
		this.designWrong = designWrong;
	}

	public String getSaleCause() {
		return saleCause;
	}

	public void setSaleCause(String saleCause) {
		this.saleCause = saleCause;
	}

	public String getConstructCause() {
		return constructCause;
	}

	public void setConstructCause(String constructCause) {
		this.constructCause = constructCause;
	}

	public String getPartACause() {
		return partACause;
	}

	public void setPartACause(String partACause) {
		this.partACause = partACause;
	}

	public String getOtherCause() {
		return otherCause;
	}

	public void setOtherCause(String otherCause) {
		this.otherCause = otherCause;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getVisaCause() {
		return visaCause;
	}

	public void setVisaCause(String visaCause) {
		this.visaCause = visaCause;
	}
	
	public String getIssueVisaDrawId() {
		return issueVisaDrawId;
	}

	public void setIssueVisaDrawId(String issueVisaDrawId) {
		this.issueVisaDrawId = issueVisaDrawId;
	}

	public String getUpdateBudgetId() {
		return updateBudgetId;
	}

	public void setUpdateBudgetId(String updateBudgetId) {
		this.updateBudgetId = updateBudgetId;
	}

	public String getAdjureFileId() {
		return adjureFileId;
	}

	public void setAdjureFileId(String adjureFileId) {
		this.adjureFileId = adjureFileId;
	}

	public String getResApproveCd() {
		return resApproveCd;
	}

	public void setResApproveCd(String resApproveCd) {
		this.resApproveCd = resApproveCd;
	}

	public String getResApproveId() {
		return resApproveId;
	}

	public void setResApproveId(String resApproveId) {
		this.resApproveId = resApproveId;
	}

	@Override
	public void doImport() {
		ContLedgerManager ledgerManager = SpringContextHolder.getBean("contLedgerManager");
		AppDictTypeManager appDictTypeManager = SpringContextHolder.getBean("appDictTypeManager");
		ContLedger ledger =ledgerManager.getEntity(getContLedgerId());
		ContVisaUpdate visaUpdate =new ContVisaUpdate();
		visaUpdate.setContent(visaContent);
		//变更原因
		AppDictType appDictType = appDictTypeManager.findAppDictTypeByCd(DictContants.CONT_VISA_CONTENT);
		if (appDictType != null) {
			if("true".equals(designWrong)){
				for (AppDictData appDictData : appDictType.getAppDictDatas()) {
					if(appDictData.getDictName().equals("设计错漏")){
						visaUpdate.setCause(appDictData.getDictCd());
						break;
					}
				}
			}else if("true".equals(saleCause)){
				for (AppDictData appDictData : appDictType.getAppDictDatas()) {
					if(appDictData.getDictName().equals("招商/销售原因")){
						visaUpdate.setCause(appDictData.getDictCd());
						break;
					}
				}
			}else if("true".equals(constructCause)){
				for (AppDictData appDictData : appDictType.getAppDictDatas()) {
					if(appDictData.getDictName().equals("施工原因")){
						visaUpdate.setCause(appDictData.getDictCd());
						break;
					}
				}
			}else if("true".equals(partACause)){
				for (AppDictData appDictData : appDictType.getAppDictDatas()) {
					if(appDictData.getDictName().equals("甲方原因")){
						visaUpdate.setCause(appDictData.getDictCd());
						break;
					}
				}
			}else if("true".equals(otherCause)){
				for (AppDictData appDictData : appDictType.getAppDictDatas()) {
					if(appDictData.getDictName().equals("其他")){
						visaUpdate.setCause(appDictData.getDictCd());
						break;
					}
				}
			}
			
		}
		ResApproveInfo approveInfo=getResApproveInfo();
		setResApproveCd(approveInfo.getApproveCd()+approveInfo.getSerialNo());
		resApproveId =approveInfo.getResApproveInfoId();
		visaUpdate.setResApproveCd(resApproveCd);
		visaUpdate.setResApproveId(resApproveId);
		visaUpdate.setPrepareFee(new BigDecimal(FormatUtil.formatDouble(prepareFee)));//本次预估费用
		//输入签证变更编号
		int visaCount = 0;
		if(ledger.getContVisaUpdates() != null){
			visaCount = ledger.getContVisaUpdates().size();
		}
		String visaUpdateNo = StringUtils.leftPad(String.valueOf(visaCount+1),4, "0");//4位，不够补0;
		visaUpdate.setVisaUpdateNo(visaUpdateNo);
		
		ledger.getContVisaUpdates().add(visaUpdate);
		ledgerManager.saveContLedger(ledger);
	}
	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return visaContent;
	}
	@Override
	public boolean isAutoImport() {
		// TODO 自动导入合同台账
		return true;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getGuraDesignVisaHotel() {
		return guraDesignVisaHotel;
	}

	public void setGuraDesignVisaHotel(String guraDesignVisaHotel) {
		this.guraDesignVisaHotel = guraDesignVisaHotel;
	}

	public String getIsThreeLine() {
		return isThreeLine;
	}

	public void setIsThreeLine(String isThreeLine) {
		this.isThreeLine = isThreeLine;
	}
}
