package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.cont.ContLedgerManager;
import com.hhz.ump.dao.ctdb.CtdbBillingAppManager;
import com.hhz.ump.entity.cont.ContLedger;
import com.hhz.ump.entity.ctdb.CtdbBillingApp;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.res.baseBean.BaseContLedgerTemplate;

/**
 * 工程结算审批表
 * 
 * @author huangjian
 * 
 *         2011-4-26
 */
public class ProjectBalanceBill extends BaseContLedgerTemplate {

	/**
	 * 合同编号
	 */
	private String contNo;
	/**
	 * 结算编号
	 */
	private String balanceNo;
	/**
	 * 合同名称
	 */
	private String contName;

	/**
	 * 乙方(收款人)
	 */
	private String partB;
	/**
	 * 项目cd
	 */
	private String projectCd;
	private String projectName;
	private String projectPeriod;// 期

	private String area;// 面积
	private String contCharacter;// 合同性质

	/**
	 * 原合同总价
	 */
	private String oriTotalPrice;
	private String oriNorm;// 原合同单方指标（元/m2）
	private String confirmToalPrice;// 已确认合同总价
	private String confirmNorm;// 现单方指标（元/m2）
	private String changeTotal;// 累计变更费用（元）
	private String changeRate;// 累计变更比率（%）
	private String payMoney;// 已支付金额（填数字）add by zhuxj on 2012.4.6
	private String tatalRate;// 占合同总金额（系统自动带出%多少）add by zhuxj on 2012.4.6
	private String applyMoneyB;// 乙方报审金额（元）
	private String normB;// 相应单方指标（元/m2）
	private String landApproveMoney;// 地产公司审核金额（元）
	private String normland;// 相应单方指标（元/m2）
	private String targetMoney;// 目标成本（元）
	private String normTarget;// 相应单方指标（元/m2）
	private String remark;// 工程结算额及说明
	// 附件列表
	private String firstApproveFileId;// 一审结算汇总表（必填）
	private String balanceItemFileId;// 结算争议事项汇总表
	private String leaveProblemFileId;// 工程竣工遗留问题确认单（必填）
	private String balanceApproveFileId;// 结算审核意见书（必填）
	private String jgcSbFileId;// 甲供材/设备结算核对资料
	private String consUnitFileId;// 施工单位预算附件
	private String landCostFileId;// 地产公司成本部审核预算附件
	private String drawingListFileId;// 图纸及目录
	private String oriContractFileId;// 原合同扫描件
	private String curPicId;// 现场照片

	private String land;// 只与地产有关
	private String hotel;// 与酒店有关
	private String trade;// 与行业有关

	private String groupMoney;// 集团核定费用(元)/ 结算价
	private String normGroup;// 单方指标(元/m2)

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
		return contName;
	}

	@Override
	public void doImport() {
		ContLedgerManager ledgerManager = SpringContextHolder.getBean("contLedgerManager");
		ResApproveInfo approveInfo=getResApproveInfo();
		ContLedger ledger =ledgerManager.getEntity(getContLedgerId());
		ledger.setClearResApproveCd(approveInfo.getApproveCd()+approveInfo.getSerialNo());
		ledger.setClearResApproveId(approveInfo.getResApproveInfoId());
		
		//若结算价有值，修改结算价并合同状态改为已完已结，并进度状态改为已竣工
		if(StringUtils.isNotBlank(groupMoney)) {
			BigDecimal clear =new BigDecimal(FormatUtil.formatDouble(groupMoney));
			ledger.setClearPrice(clear);
			ledger.setContStatus("2");//2为已完已结
			ledger.setProcStatus("4");//4为已竣工
		}
		ledgerManager.saveContLedger(ledger);
		try {
			//导入
			importBillingApp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void importBillingApp(){
		CtdbBillingAppManager ctdbBillingAppManager = SpringContextHolder.getBean("ctdbBillingAppManager");
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
		cba.setCtProperty(this.getContCharacter());
		if(StringUtils.isNotBlank(area)){
			try {
				//6.面积（数量，材料结算内无，建议增加）
				cba.setArea(BigDecimal.valueOf(Double.parseDouble(area.trim().replaceAll(",", ""))));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//7.结算价（需审批完成后，由经办人手工导入）
		if(StringUtils.isNotBlank(groupMoney)){
			try {
				BigDecimal billingPrice=BigDecimal.valueOf(Double.valueOf(groupMoney.trim().replaceAll(",", "")));
				cba.setBillingPrice(billingPrice);
			} catch (Exception e) {
					Log.error(e);
				}
		}		
		//8.单位造价（单价=结算价/面积）
		if(StringUtils.isNotBlank(normGroup)){
			cba.setUnitPrice(BigDecimal.valueOf(Double.parseDouble(normGroup.replaceAll(",", ""))));
		}
		
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
	
	public String getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}


	public String getTatalRate() {
		return tatalRate;
	}

	public void setTatalRate(String tatalRate) {
		this.tatalRate = tatalRate;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getContCharacter() {
		return contCharacter;
	}

	public void setContCharacter(String contCharacter) {
		this.contCharacter = contCharacter;
	}

	public String getOriNorm() {
		return oriNorm;
	}

	public void setOriNorm(String oriNorm) {
		this.oriNorm = oriNorm;
	}

	public String getConfirmToalPrice() {
		return confirmToalPrice;
	}

	public void setConfirmToalPrice(String confirmToalPrice) {
		this.confirmToalPrice = confirmToalPrice;
	}

	public String getConfirmNorm() {
		return confirmNorm;
	}

	public void setConfirmNorm(String confirmNorm) {
		this.confirmNorm = confirmNorm;
	}

	public String getChangeTotal() {
		return changeTotal;
	}

	public void setChangeTotal(String changeTotal) {
		this.changeTotal = changeTotal;
	}

	public String getChangeRate() {
		return changeRate;
	}

	public void setChangeRate(String changeRate) {
		this.changeRate = changeRate;
	}

	public String getApplyMoneyB() {
		return applyMoneyB;
	}

	public void setApplyMoneyB(String applyMoneyB) {
		this.applyMoneyB = applyMoneyB;
	}

	public String getNormB() {
		return normB;
	}

	public void setNormB(String normB) {
		this.normB = normB;
	}

	public String getLandApproveMoney() {
		return landApproveMoney;
	}

	public void setLandApproveMoney(String landApproveMoney) {
		this.landApproveMoney = landApproveMoney;
	}

	public String getNormland() {
		return normland;
	}

	public void setNormland(String normland) {
		this.normland = normland;
	}

	public String getTargetMoney() {
		return targetMoney;
	}

	public void setTargetMoney(String targetMoney) {
		this.targetMoney = targetMoney;
	}

	public String getNormTarget() {
		return normTarget;
	}

	public void setNormTarget(String normTarget) {
		this.normTarget = normTarget;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFirstApproveFileId() {
		return firstApproveFileId;
	}

	public void setFirstApproveFileId(String firstApproveFileId) {
		this.firstApproveFileId = firstApproveFileId;
	}

	public String getBalanceItemFileId() {
		return balanceItemFileId;
	}

	public void setBalanceItemFileId(String balanceItemFileId) {
		this.balanceItemFileId = balanceItemFileId;
	}

	public String getLeaveProblemFileId() {
		return leaveProblemFileId;
	}

	public void setLeaveProblemFileId(String leaveProblemFileId) {
		this.leaveProblemFileId = leaveProblemFileId;
	}

	public String getBalanceApproveFileId() {
		return balanceApproveFileId;
	}

	public void setBalanceApproveFileId(String balanceApproveFileId) {
		this.balanceApproveFileId = balanceApproveFileId;
	}

	public String getJgcSbFileId() {
		return jgcSbFileId;
	}

	public void setJgcSbFileId(String jgcSbFileId) {
		this.jgcSbFileId = jgcSbFileId;
	}

	public String getConsUnitFileId() {
		return consUnitFileId;
	}

	public void setConsUnitFileId(String consUnitFileId) {
		this.consUnitFileId = consUnitFileId;
	}

	public String getLandCostFileId() {
		return landCostFileId;
	}

	public void setLandCostFileId(String landCostFileId) {
		this.landCostFileId = landCostFileId;
	}

	public String getDrawingListFileId() {
		return drawingListFileId;
	}

	public void setDrawingListFileId(String drawingListFileId) {
		this.drawingListFileId = drawingListFileId;
	}

	public String getProjectPeriod() {
		return projectPeriod;
	}

	public void setProjectPeriod(String projectPeriod) {
		this.projectPeriod = projectPeriod;
	}

	public String getBalanceNo() {
		return balanceNo;
	}

	public void setBalanceNo(String balanceNo) {
		this.balanceNo = balanceNo;
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

	public String getPartB() {
		return partB;
	}

	public void setPartB(String partB) {
		this.partB = partB;
	}

	public String getProjectCd() {
		return projectCd;
	}

	public void setProjectCd(String projectCd) {
		this.projectCd = projectCd;
	}

	public String getOriTotalPrice() {
		return oriTotalPrice;
	}

	public void setOriTotalPrice(String oriTotalPrice) {
		this.oriTotalPrice = oriTotalPrice;
	}

	@Override
	public boolean isAutoImport() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getGroupMoney() {
		return groupMoney;
	}

	public void setGroupMoney(String groupMoney) {
		this.groupMoney = groupMoney;
	}

	public String getNormGroup() {
		return normGroup;
	}

	public void setNormGroup(String normGroup) {
		this.normGroup = normGroup;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}
	

	public String getOriContractFileId() {
		return oriContractFileId;
	}

	public void setOriContractFileId(String oriContractFileId) {
		this.oriContractFileId = oriContractFileId;
	}

	public String getCurPicId() {
		return curPicId;
	}

	public void setCurPicId(String curPicId) {
		this.curPicId = curPicId;
	}

}
