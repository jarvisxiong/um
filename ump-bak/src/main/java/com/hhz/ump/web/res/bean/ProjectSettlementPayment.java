package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.dao.cont.ContSettlementManager;
import com.hhz.ump.dao.ctdb.CtdbBillingAppManager;
import com.hhz.ump.entity.cont.ContLedger;
import com.hhz.ump.entity.cont.ContSettlement;
import com.hhz.ump.entity.ctdb.CtdbBillingApp;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;

/**
 * 工程结算款付款审批表
 * @author baolm
 *
 * 2011-04-28
 */
public class ProjectSettlementPayment extends BaseContLedgerTemplate {
	
	/**
	 * 合同台账存放的网批CD
	 */
	private String resApproveCd;
	/**
	 * 合同台账存放的网批ID
	 */
	private String resApproveId;
	
	/**
	 * 合同编号
	 */
	private String contNo;
	
	/**
	 * 合同名称
	 */
	private String contName;
	
	/**
	 * 项目cd
	 */
	private String projectCd;

	/**
	 * 乙方(收款人)
	 */
	private String partB;
	
	/**
	 * 合同总价
	 */
	private String totalPrice;
	
	/**
	 * 付款方式
	 */
	private String payWay;
	
	/**
	 * 保修期开始日期
	 */
	private String guarBeginDate;
	
	/**
	 * 保修期结束日期
	 */
	private String guarEndDate;
	
	/**
	 * 结算价
	 */
	private String clearPrice;
	
	/**
	 * 变更后的合同总价
	 */
	private String updateTotal;
	
	/**
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * ( )期
	 */
	private String projectPeriod;
	
	/**
	 * 付款单位
	 */
	private String paymentUnit;
	
	/**
	 * 收款人开户行
	 */
	private String payeeOpenBankNo;
	
	/**
	 * 收款人账号
	 */
	private String payeeAccountNo;
	
	
	/**
	 * 本次付款申请金额(元)
	 */
	private String applyAmount;
	
	/**
	 * 计算公式：结算总价
	 */
	private String fuClearPrice;
	
	/**
	 * 计算公式：预留：质保金
	 */
	private String fuGuaranteeMoney;
	
	/**
	 * 计算公式：扣：甲供料款(按暂定价)
	 */
	private String fuMatieralPrice;

	/**
	 * 计算公式：扣：已直接付款累计
	 */
	private String fuCurrentPay;
	
	/**
	 * 计算公式：扣：其他扣款或代付款累计
	 */
	private String fuCurrentAdd;
	
	/**
	 * 计算公式：本次付款金额
	 */
	private String fuPayMoney;
	
	/**
	 * 需说明事项/备注
	 */
	private String description;
	
	/**
	 * 工程结算审批表
	 */
	private String proSettleId;
	
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

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getPartB() {
		return partB;
	}

	public void setPartB(String partB) {
		this.partB = partB;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getGuarBeginDate() {
		return guarBeginDate;
	}

	public void setGuarBeginDate(String guarBeginDate) {
		this.guarBeginDate = guarBeginDate;
	}

	public String getGuarEndDate() {
		return guarEndDate;
	}

	public void setGuarEndDate(String guarEndDate) {
		this.guarEndDate = guarEndDate;
	}

	public String getClearPrice() {
		return clearPrice;
	}

	public void setClearPrice(String clearPrice) {
		this.clearPrice = clearPrice;
	}

	public String getUpdateTotal() {
		return updateTotal;
	}

	public void setUpdateTotal(String updateTotal) {
		this.updateTotal = updateTotal;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectPeriod() {
		return projectPeriod;
	}

	public void setProjectPeriod(String projectPeriod) {
		this.projectPeriod = projectPeriod;
	}

	public String getPaymentUnit() {
		return paymentUnit;
	}

	public void setPaymentUnit(String paymentUnit) {
		this.paymentUnit = paymentUnit;
	}

	public String getPayeeOpenBankNo() {
		return payeeOpenBankNo;
	}

	public void setPayeeOpenBankNo(String payeeOpenBankNo) {
		this.payeeOpenBankNo = payeeOpenBankNo;
	}

	public String getPayeeAccountNo() {
		return payeeAccountNo;
	}

	public void setPayeeAccountNo(String payeeAccountNo) {
		this.payeeAccountNo = payeeAccountNo;
	}

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProSettleId() {
		return proSettleId;
	}

	public void setProSettleId(String proSettleId) {
		this.proSettleId = proSettleId;
	}

	public String getFuClearPrice() {
		return fuClearPrice;
	}

	public void setFuClearPrice(String fuClearPrice) {
		this.fuClearPrice = fuClearPrice;
	}

	public String getFuGuaranteeMoney() {
		return fuGuaranteeMoney;
	}

	public void setFuGuaranteeMoney(String fuGuaranteeMoney) {
		this.fuGuaranteeMoney = fuGuaranteeMoney;
	}

	public String getFuMatieralPrice() {
		return fuMatieralPrice;
	}

	public void setFuMatieralPrice(String fuMatieralPrice) {
		this.fuMatieralPrice = fuMatieralPrice;
	}

	public String getFuPayMoney() {
		return fuPayMoney;
	}

	public void setFuPayMoney(String fuPayMoney) {
		this.fuPayMoney = fuPayMoney;
	}

	public String getFuCurrentAdd() {
		return fuCurrentAdd;
	}

	public void setFuCurrentAdd(String fuCurrentAdd) {
		this.fuCurrentAdd = fuCurrentAdd;
	}

	public String getFuCurrentPay() {
		return fuCurrentPay;
	}

	public void setFuCurrentPay(String fuCurrentPay) {
		this.fuCurrentPay = fuCurrentPay;
	}

	@Override
	public String getResProjectName() {
		return projectName;
	}

	@Override
	public String getResProjectCd() {
		return projectCd;
	}

	@Override
	public String getResTitleName() {
		// TODO Auto-generated method stub
		return contName;
	}

	@Override
	public void doImport() {
		// TODO 需要台账实现相关功能后实现
		ContSettlementManager contSettlementManager = SpringContextHolder.getBean("contSettlementManager");
		ContLedgerManager contLedgerManager = SpringContextHolder.getBean("contLedgerManager");
		ResApproveInfo approveInfo=getResApproveInfo();
		setResApproveCd(approveInfo.getApproveCd()+approveInfo.getSerialNo());
		resApproveId =approveInfo.getResApproveInfoId();
		ContSettlement contSettlement = new ContSettlement();
		contSettlement.setResApproveCd(resApproveCd);
		contSettlement.setResApproveId(resApproveId);
		contSettlement.setClearPrice(new BigDecimal(FormatUtil.formatDouble(fuClearPrice)));
		contSettlement.setCurrentAdd(new BigDecimal(FormatUtil.formatDouble(fuCurrentAdd)));
		contSettlement.setCurrentPay(new BigDecimal(FormatUtil.formatDouble(fuCurrentPay)));
		contSettlement.setGuaranteeMoney(new BigDecimal(FormatUtil.formatDouble(fuGuaranteeMoney)));
		contSettlement.setMatieralPrice(new BigDecimal(FormatUtil.formatDouble(fuMatieralPrice)));
		contSettlement.setPayMoney(new BigDecimal(FormatUtil.formatDouble(fuPayMoney)));
		ContLedger ledger = contLedgerManager.getEntity(getContLedgerId());
		contSettlement.setContLedger(ledger);
		contSettlementManager.saveContSettlement(contSettlement);
		try {
			//导入
			importBillingApp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void importBillingApp(){
		CtdbBillingAppManager ctdbBillingAppManager = SpringContextHolder.getBean("ctdbBillingAppManager");
		ContLedgerManager contLedgerManager = SpringContextHolder.getBean("contLedgerManager");
		CtdbBillingApp cba=new CtdbBillingApp();
		//1.项目名称
		cba.setProjectName(this.getProjectName());
		cba.setProjectCd(this.getProjectCd());
		// 2.合同名称
		cba.setCtName(this.getContName());
			
		//3.乙方
		cba.setImpSupCd(this.getPartB());
		cba.setImpSupName(null);
		//4.承包范围（现结算审批表内无，建议增加，由合同台账自动导入）
		cba.setCtArea(null);
		//5.合同性质
		if(StringUtils.isNotBlank(contNo)){
			ContLedger contLedger=contLedgerManager.findContLedgerByContNo(contNo);
			String pro="";
				if("0".equals(contLedger.getContProperty())){
					pro="总价包干";
				}
				else if("1".equals(contLedger.getContProperty())){
					pro="可调值总价包干";
				}
				else if("2".equals(contLedger.getContProperty())){
					pro="单价包干";
				}
				else if("3".equals(contLedger.getContProperty())){
					pro="定额计价";
				}
			
			cba.setCtProperty(pro);
		}				
		//6.面积（数量，材料结算内无，建议增加）
		cba.setArea(null);
		//7.结算价（需审批完成后，由经办人手工导入）
		if(StringUtils.isNotBlank(clearPrice)){
			try {
				BigDecimal billingPrice=BigDecimal.valueOf(Double.valueOf(clearPrice.trim().replaceAll(",", "")));
				cba.setBillingPrice(billingPrice);
			} catch (Exception e) {
					Log.error(e);
				}
		}		
		//8.单位造价（单价=结算价/面积）
		cba.setUnitPrice(null);
		//9.结算日期（按结算审批表完成的日期导入）
		cba.setBillingDate(this.getResApproveInfo().getCompleteDate());
		//10.网批编号
		cba.setBillingResCd(this.getResApproveInfo().getResApproveInfoId());
		cba.setApproveCd(this.getResApproveInfo().getApproveCd()+this.getResApproveInfo().getSerialNo());
		//10.导入类型
		cba.setImportType(this.getResApproveInfo().getAuthTypeCd());
		//默认系统创建
		cba.setCreator("system");
		cba.setCreatedDate(new Date());
		cba.setAuthTypeCd(this.getResApproveInfo().getAuthTypeCd());
		ctdbBillingAppManager.saveCtdbBillingApp(cba);
	}

	@Override
	public boolean isAutoImport() {
		//批完直接导入台账
		return true;
	}

}
