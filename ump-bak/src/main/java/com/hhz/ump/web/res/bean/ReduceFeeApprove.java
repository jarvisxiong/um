package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.entity.cont.ContVisaUpdate;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;


/**
 * 扣减费用核定单
 * @author baolm
 *
 * 2011-04-27
 */
public class ReduceFeeApprove extends BaseContLedgerTemplate {
	
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
	 * 项目名称
	 */
	private String projectName;
	
	/**
	 * ( )期
	 */
	private String projectPeriod;
	
	/**
	 * 扣减编号
	 */
	private String serialNo;
	
	/**
	 * 扣减内容
	 */
	private String reduceContent;
	
	/**
	 * □另行委托
	 */
	private String reduceReason1;
	
	/**
	 * □设计变更减少
	 */
	private String reduceReason2;
	
	/**
	 * □未施工甩项，且经同意
	 */
	private String reduceReason3;
	
	/**
	 * 地产公司扣减费用(元)
	 */
	private String reduceMoney;
	
	/**
	 * 核价编制说明：第几条款
	 */
	private String itemNo;
	
	/**
	 * 核价编制说明：第几种方法约定
	 */
	private String functionNo;
	
	/**
	 * 核价编制说明：工程量
	 */
	private String projectQuantity;
	
	/**
	 * □合同综合单价
	 */
	private String unitPriceType1;
	
	/**
	 * □参考合同综合单价
	 */
	private String unitPriceType2;
	
	/**
	 * □定额计价
	 */
	private String unitPriceType3;
	
	/**
	 * 核价编制说明：下浮率
	 */
	private String reduceRate;
	
	/**
	 * 核减的预算
	 */
	private String reduceBudgetId;
	
	/**
	 * 图纸或现场签证
	 */
	private String liveVisaId;
	
	/**
	 * 工程量确认单
	 */
	private String quantityConformId;
	
	/**
	 * 集团核定费用(元)
	 */
	private String groupMoney;
	
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

	public String getReduceContent() {
		return reduceContent;
	}

	public void setReduceContent(String reduceContent) {
		this.reduceContent = reduceContent;
	}

	public String getReduceReason1() {
		return reduceReason1;
	}

	public void setReduceReason1(String reduceReason1) {
		this.reduceReason1 = reduceReason1;
	}

	public String getReduceReason2() {
		return reduceReason2;
	}

	public void setReduceReason2(String reduceReason2) {
		this.reduceReason2 = reduceReason2;
	}

	public String getReduceReason3() {
		return reduceReason3;
	}

	public void setReduceReason3(String reduceReason3) {
		this.reduceReason3 = reduceReason3;
	}

	public String getReduceMoney() {
		return reduceMoney;
	}

	public void setReduceMoney(String reduceMoney) {
		this.reduceMoney = reduceMoney;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getFunctionNo() {
		return functionNo;
	}

	public void setFunctionNo(String functionNo) {
		this.functionNo = functionNo;
	}

	public String getProjectQuantity() {
		return projectQuantity;
	}

	public void setProjectQuantity(String projectQuantity) {
		this.projectQuantity = projectQuantity;
	}

	public String getUnitPriceType1() {
		return unitPriceType1;
	}

	public void setUnitPriceType1(String unitPriceType1) {
		this.unitPriceType1 = unitPriceType1;
	}

	public String getUnitPriceType2() {
		return unitPriceType2;
	}

	public void setUnitPriceType2(String unitPriceType2) {
		this.unitPriceType2 = unitPriceType2;
	}

	public String getUnitPriceType3() {
		return unitPriceType3;
	}

	public void setUnitPriceType3(String unitPriceType3) {
		this.unitPriceType3 = unitPriceType3;
	}

	public String getReduceRate() {
		return reduceRate;
	}

	public void setReduceRate(String reduceRate) {
		this.reduceRate = reduceRate;
	}

	public String getReduceBudgetId() {
		return reduceBudgetId;
	}

	public void setReduceBudgetId(String reduceBudgetId) {
		this.reduceBudgetId = reduceBudgetId;
	}

	public String getLiveVisaId() {
		return liveVisaId;
	}

	public void setLiveVisaId(String liveVisaId) {
		this.liveVisaId = liveVisaId;
	}

	public String getQuantityConformId() {
		return quantityConformId;
	}

	public void setQuantityConformId(String quantityConformId) {
		this.quantityConformId = quantityConformId;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getGroupMoney() {
		return groupMoney;
	}

	public void setGroupMoney(String groupMoney) {
		this.groupMoney = groupMoney;
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
		return contName;
	}

	@Override
	public void doImport() {
		ContLedgerManager contLedgerManager = SpringContextHolder.getBean("contLedgerManager");
		ResApproveInfo approveInfo = getResApproveInfo();
		
		//+审批的ID
		ContVisaUpdate contVisaUpdate = new ContVisaUpdate();
		
		//核定单网批ID
		contVisaUpdate.setApproveCheckId(approveInfo.getResApproveInfoId());
		
		//add by huangbijin 2012-06-07 
		try{

//			<s:checkbox name="templateBean.reduceReason1"  cssClass="group"></s:checkbox>另行委托
//			<s:checkbox name="templateBean.reduceReason2"  cssClass="group"></s:checkbox>设计变更减少
//			<s:checkbox name="templateBean.reduceReason3"  cssClass="group"></s:checkbox>未施工甩项，且经同意
//			</option><option value="1">设计错漏</option>
//			<option value="2">招商原因</option>
//			<option value="3">施工原因</option>
//			<option value="4">甲方原因</option>
//			<option value="5">其它</option>
			
			//变更原因
			if("true".equals(reduceReason1)){
				contVisaUpdate.setCause("7");
			}
			else if("true".equals(reduceReason2)){
				contVisaUpdate.setCause("8");
			}
			else if("true".equals(reduceReason3)){
				contVisaUpdate.setCause("9");
			}
			else{
				
			}
			
			//变更内容
			contVisaUpdate.setContent(reduceContent);
			
		}catch(Exception e){
//			e.printStackTrace();
		} 
		
		
		
		BigDecimal bg = new BigDecimal(FormatUtil.formatDouble(groupMoney));
		if(bg.compareTo(new BigDecimal(0))<0){
			contVisaUpdate.setAmountUpdate(new BigDecimal(FormatUtil.formatDouble(groupMoney)));
		}else{
			contVisaUpdate.setAmountUpdate(new BigDecimal(0).subtract(new BigDecimal(FormatUtil.formatDouble(groupMoney))));
		}
		contLedgerManager.updateContByRes(getContLedgerId(),resApproveCd, "", contVisaUpdate, null, null);
	}

	@Override
	public boolean isAutoImport() {
		return false;
	}

}
