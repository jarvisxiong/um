package com.hhz.ump.web.res.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.core.utils.DateOperator;
import com.hhz.ump.dao.bis.BisContManager;
import com.hhz.ump.entity.bis.BisCont;
import com.hhz.ump.entity.bis.BisContAdditional;
import com.hhz.ump.entity.bis.BisContCredit;
import com.hhz.ump.entity.bis.BisContShopRent;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.web.res.baseBean.BaseTemplate;
import com.hhz.ump.web.res.baseBean.IAutoImport;

public class BisContApprove extends BaseTemplate implements IAutoImport {

	/**
	 * 项目
	 */
	private String bisProjectId;
	private String bisProjectName;
	/**
	 * 合同编号
	 */
	private String contNo;
	/**
	 * 品牌
	 */
	private String bisShopId;
	private String bisShopName;
	/**
	 * 经销商
	 */
	private String bisShopConnId;
	private String bisShopConnName;
	/**
	 * 楼栋名称
	 */
	private String buildingName;
	/**
	 * 商铺
	 */
	private String bisStoreIds;
	private String bisStoreNos;
	/**
	 * 建筑面积
	 */
	private String square;
	/**
	 * 套内面积
	 */
    private String innerSquare;
    /**
	 * 计租面积
	 */
    private String showSquare;
    /**
     * 实际收费面积
     */
    private String rentSquare;
    /**
     * 产权性质
     */
    private String equityNature;
    /**
     * 签约日期
     */
    private String signDate;
    /**
     * 计租起始日期
     */
    private String rentDate;
    /**
     * 合同开始日期
     */
    private String contStartDate;
    /**
     * 合同结束日期
     */
    private String contEndDate;
    /**
     * 合同期限
     */
    private String rentYears;
    /**
     * 业态
     */
    private String layoutCd;
    /**
     * 经营性质
     */
    private String manageCd;
    /**
     * 联系人
     */
    private String connPeople;
    /**
     * 联系电话
     */
    private String connTel;
    /**
     * 联系传真
     */
    private String connFax;
    /**
     * 联系人银行账号
     */
    private String connAccountNo;
    /**
     * 联系地址
     */
    private String connAddr;
    /**
     * 合作方式
     */
    private String coopWayCd;
    /**
     * 招商人员
     */
    private String bisPeople;
    /**
     * 甲方
     */
    private String partyA;
    /**
     * 乙方
     */
    private String partyB;
    /**
     * 丙方
     */
    private String partyC;//丙方 -Add for part C by zhuxj on 2012.3.31
    /**
     * 支付方式
     */
    private String paymentModeCd;
    /**
     * 租金履约保证金
     */
    private String earnestMoney;
    /**
     * 免租期
     */
    private String freeRentPeriod;
    /**
     * 租金支付周期
     */
    private String payCycleCd;
    
    /**
     * 签约租金单价
     */
    private String rentUnit1;
    private String rentUnit2;
    private String rentUnit3;
    private String rentUnit4;
    private String rentUnit5;
    private String rentUnit6;
    private String rentUnit7;
    private String rentUnit8;
    private String rentUnit9;
    private String rentUnit10;
    
    /**
     * 签约月租金
     */
    private String rentMonth1;
    private String rentMonth2;
    private String rentMonth3;
    private String rentMonth4;
    private String rentMonth5;
    private String rentMonth6;
    private String rentMonth7;
    private String rentMonth8;
    private String rentMonth9;
    private String rentMonth10;
    
    /**
     * 合同内部批文
     */
    private String contApproval;
    /**
     * 合同内部批文附件
     */
    private String contApprovalId;
    /**
     * 合同条款
     */
    private String contContent;
    /**
     * 合同条款附件
     */
    private String contContentId;
	/**
	 * 资信资料
	 */
    private List<BisContCreditProperty>  creditProperties = new ArrayList<BisContCreditProperty>();
    /**
     * 补充合同
     */
    private List<BisContAddiProperty>  addiProperties = new ArrayList<BisContAddiProperty>();
    
    
	@Override
	public String getResTitleName() {
		return contNo;
	}
	@Override
	public void doImport() {
		BisContManager bisContManager = SpringContextHolder.getBean("bisContManager");
		
		BisCont bisCont = new BisCont();
		bisCont.setContBigTypeCd("1"); // 商铺
		bisCont.setContSmallTypeCd("1"); // 租赁
		bisCont.setStatusCd("3"); // 已审核
		bisCont.setCreator(getResApproveInfo().getCreator()); // 创建人
		bisCont.setCreatedDate(getResApproveInfo().getCreatedDate());
		bisCont.setCreatedCenterCd(getResApproveInfo().getCreatedCenterCd());
		bisCont.setCreatedDeptCd(getResApproveInfo().getCreatedDeptCd());
		bisCont.setCreatedPositionCd(getResApproveInfo().getCreatedPositionCd());
		bisCont.setUpdator(getResApproveInfo().getUpdator());
		bisCont.setUpdatedDate(getResApproveInfo().getUpdatedDate());
		bisCont.setUpdatedCenterCd(getResApproveInfo().getUpdatedCenterCd());
		bisCont.setUpdatedDeptCd(getResApproveInfo().getUpdatedDeptCd());
		bisCont.setUpdatedPositionCd(getResApproveInfo().getUpdatedPositionCd());
		bisCont.setCheckDate(new Date());
		
		bisCont.setResApproveInfoId(getResApproveInfo().getResApproveInfoId());//网批ID
		
		if(StringUtils.isNotBlank(contContentId)) {
			bisCont.setAttachFlg("1");
		}
		bisCont.setContApproval(contApproval);
		bisCont.setContApprovalId(contApprovalId);
		if(StringUtils.isNotBlank(contApprovalId)) {
			bisCont.setApprovalAttachFlg("1");
		}

		bisCont.setBisProjectId(bisProjectId);
		bisCont.setContNo(contNo);
		bisCont.setBuildingName(buildingName);
		if(StringUtils.isNotBlank(square)) {
			bisCont.setSquare(new BigDecimal(FormatUtil.formatDouble(square)));
		}
		if(StringUtils.isNotBlank(innerSquare)) {
			bisCont.setInnerSquare(new BigDecimal(FormatUtil.formatDouble(innerSquare)));
		}
		if(StringUtils.isNotBlank(rentSquare)) {
			bisCont.setRentSquare(new BigDecimal(FormatUtil.formatDouble(rentSquare)));
		}
		bisCont.setSignDate(DateOperator.parse(signDate, "yyyy-MM-dd"));
		bisCont.setRentDate(DateOperator.parse(rentDate, "yyyy-MM-dd"));
		bisCont.setContStartDate(DateOperator.parse(contStartDate, "yyyy-MM-dd"));
		bisCont.setContEndDate(DateOperator.parse(contEndDate, "yyyy-MM-dd"));
		bisCont.setEquityNature(equityNature);
		bisCont.setRentYears(rentYears);
		bisCont.setLayoutCd(layoutCd);
		bisCont.setManageCd(manageCd);
		bisCont.setConnPeople(connPeople);
		bisCont.setConnTel(connTel);
		bisCont.setConnFax(connFax);
		bisCont.setConnAccountNo(connAccountNo);
		bisCont.setConnAddr(connAddr);
		bisCont.setCoopWayCd(coopWayCd);
		bisCont.setBisPeople(bisPeople);
		bisCont.setPartyA(partyA);
		bisCont.setPartyB(partyB);
		bisCont.setPartyC(partyC);
		bisCont.setContContent(contContent);

		BisContShopRent contRent = new BisContShopRent();
		contRent.setPaymentModeCd(paymentModeCd);
		if (StringUtils.isNotBlank(earnestMoney)) {
			contRent.setEarnestMoney(new BigDecimal(FormatUtil.formatDouble(earnestMoney)));
		}
		contRent.setFreeRentPeriod(freeRentPeriod);
		contRent.setPayCycleCd(payCycleCd);
		contRent.setStoreNo(bisStoreNos);
		if (StringUtils.isNotBlank(rentUnit1)) {
			contRent.setRentUnit1(new BigDecimal(FormatUtil.formatDouble(rentUnit1)));
		}
		if (StringUtils.isNotBlank(rentUnit2)) {
			contRent.setRentUnit2(new BigDecimal(FormatUtil.formatDouble(rentUnit2)));
		}
		if (StringUtils.isNotBlank(rentUnit3)) {
			contRent.setRentUnit3(new BigDecimal(FormatUtil.formatDouble(rentUnit3)));
		}
		if (StringUtils.isNotBlank(rentUnit4)) {
			contRent.setRentUnit4(new BigDecimal(FormatUtil.formatDouble(rentUnit4)));
		}
		if (StringUtils.isNotBlank(rentUnit5)) {
			contRent.setRentUnit5(new BigDecimal(FormatUtil.formatDouble(rentUnit5)));
		}
		if (StringUtils.isNotBlank(rentUnit6)) {
			contRent.setRentUnit6(new BigDecimal(FormatUtil.formatDouble(rentUnit6)));
		}
		if (StringUtils.isNotBlank(rentUnit7)) {
			contRent.setRentUnit7(new BigDecimal(FormatUtil.formatDouble(rentUnit7)));
		}
		if (StringUtils.isNotBlank(rentUnit8)) {
			contRent.setRentUnit8(new BigDecimal(FormatUtil.formatDouble(rentUnit8)));
		}
		if (StringUtils.isNotBlank(rentUnit9)) {
			contRent.setRentUnit9(new BigDecimal(FormatUtil.formatDouble(rentUnit9)));
		}
		if (StringUtils.isNotBlank(rentUnit10)) {
			contRent.setRentUnit10(new BigDecimal(FormatUtil.formatDouble(rentUnit10)));
		}
		if (StringUtils.isNotBlank(rentMonth1)) {
			contRent.setRentMonth1(new BigDecimal(FormatUtil.formatDouble(rentMonth1)));
		}
		if (StringUtils.isNotBlank(rentMonth2)) {
			contRent.setRentMonth2(new BigDecimal(FormatUtil.formatDouble(rentMonth2)));
		}
		if (StringUtils.isNotBlank(rentMonth3)) {
			contRent.setRentMonth3(new BigDecimal(FormatUtil.formatDouble(rentMonth3)));
		}
		if (StringUtils.isNotBlank(rentMonth4)) {
			contRent.setRentMonth4(new BigDecimal(FormatUtil.formatDouble(rentMonth4)));
		}
		if (StringUtils.isNotBlank(rentMonth5)) {
			contRent.setRentMonth5(new BigDecimal(FormatUtil.formatDouble(rentMonth5)));
		}
		if (StringUtils.isNotBlank(rentMonth6)) {
			contRent.setRentMonth6(new BigDecimal(FormatUtil.formatDouble(rentMonth6)));
		}
		if (StringUtils.isNotBlank(rentMonth7)) {
			contRent.setRentMonth7(new BigDecimal(FormatUtil.formatDouble(rentMonth7)));
		}
		if (StringUtils.isNotBlank(rentMonth8)) {
			contRent.setRentMonth8(new BigDecimal(FormatUtil.formatDouble(rentMonth8)));
		}
		if (StringUtils.isNotBlank(rentMonth9)) {
			contRent.setRentMonth9(new BigDecimal(FormatUtil.formatDouble(rentMonth9)));
		}
		if (StringUtils.isNotBlank(rentMonth10)) {
			contRent.setRentMonth10(new BigDecimal(FormatUtil.formatDouble(rentMonth10)));
		}
		bisCont.getBisContShopRents().add(contRent);

		for (BisContCreditProperty credit : creditProperties) {
			BisContCredit contCredit = new BisContCredit();
			if(StringUtils.isNotBlank(credit.getSequenceNo())) {
				contCredit.setSequenceNo(Long.valueOf(credit.getSequenceNo()));
			}
			contCredit.setInfoName(credit.getInfoName());
			contCredit.setInfoLimit(credit.getInfoLimit());
			contCredit.setRemark(credit.getRemark());
			contCredit.setEntityTmpId(credit.getContCreditId());
			bisCont.getBisContCredits().add(contCredit);
		}

		for (BisContAddiProperty addi : addiProperties) {
			BisContAdditional contAddi = new BisContAdditional();
			contAddi.setContAdditionalName(addi.getName());
			contAddi.setSignDate(DateOperator.parse(addi.getSignDate(), "yyyy-MM-dd"));
			contAddi.setContAdditionalContent(addi.getContent());
			contAddi.setContAdditionalDesc(addi.getDesc());
			contAddi.setEntityTmpId(addi.getContAddiId());
			bisCont.getBisContAdditionals().add(contAddi);
		}
		
		bisContManager.doImport(bisCont, contContentId, bisShopId, bisShopConnId, bisStoreIds);
	}
	@Override
	public boolean isAutoImport() {
		return true;
	}
	public String getBisProjectId() {
		return bisProjectId;
	}
	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}
	public String getBisProjectName() {
		return bisProjectName;
	}
	public void setBisProjectName(String bisProjectName) {
		this.bisProjectName = bisProjectName;
	}
	public String getContNo() {
		return contNo;
	}
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	public String getBisShopId() {
		return bisShopId;
	}
	public void setBisShopId(String bisShopId) {
		this.bisShopId = bisShopId;
	}
	public String getBisShopName() {
		return bisShopName;
	}
	public void setBisShopName(String bisShopName) {
		this.bisShopName = bisShopName;
	}
	public String getBisShopConnId() {
		return bisShopConnId;
	}
	public void setBisShopConnId(String bisShopConnId) {
		this.bisShopConnId = bisShopConnId;
	}
	public String getBisShopConnName() {
		return bisShopConnName;
	}
	public void setBisShopConnName(String bisShopConnName) {
		this.bisShopConnName = bisShopConnName;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getBisStoreIds() {
		return bisStoreIds;
	}
	public void setBisStoreIds(String bisStoreIds) {
		this.bisStoreIds = bisStoreIds;
	}
	public String getBisStoreNos() {
		return bisStoreNos;
	}
	public void setBisStoreNos(String bisStoreNos) {
		this.bisStoreNos = bisStoreNos;
	}
	public String getSquare() {
		return square;
	}
	public void setSquare(String square) {
		this.square = square;
	}
	public String getInnerSquare() {
		return innerSquare;
	}
	public void setInnerSquare(String innerSquare) {
		this.innerSquare = innerSquare;
	}
	public String getShowSquare() {
		return showSquare;
	}
	public void setShowSquare(String showSquare) {
		this.showSquare = showSquare;
	}
	public String getRentSquare() {
		return rentSquare;
	}
	public void setRentSquare(String rentSquare) {
		this.rentSquare = rentSquare;
	}
	public String getEquityNature() {
		return equityNature;
	}
	public void setEquityNature(String equityNature) {
		this.equityNature = equityNature;
	}
	public String getSignDate() {
		return signDate;
	}
	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	public String getRentDate() {
		return rentDate;
	}
	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}
	public String getContStartDate() {
		return contStartDate;
	}
	public void setContStartDate(String contStartDate) {
		this.contStartDate = contStartDate;
	}
	public String getContEndDate() {
		return contEndDate;
	}
	public void setContEndDate(String contEndDate) {
		this.contEndDate = contEndDate;
	}
	public String getRentYears() {
		return rentYears;
	}
	public void setRentYears(String rentYears) {
		this.rentYears = rentYears;
	}
	public String getLayoutCd() {
		return layoutCd;
	}
	public void setLayoutCd(String layoutCd) {
		this.layoutCd = layoutCd;
	}
	public String getManageCd() {
		return manageCd;
	}
	public void setManageCd(String manageCd) {
		this.manageCd = manageCd;
	}
	public String getConnPeople() {
		return connPeople;
	}
	public void setConnPeople(String connPeople) {
		this.connPeople = connPeople;
	}
	public String getConnTel() {
		return connTel;
	}
	public void setConnTel(String connTel) {
		this.connTel = connTel;
	}
	public String getConnFax() {
		return connFax;
	}
	public void setConnFax(String connFax) {
		this.connFax = connFax;
	}
	public String getConnAccountNo() {
		return connAccountNo;
	}
	public void setConnAccountNo(String connAccountNo) {
		this.connAccountNo = connAccountNo;
	}
	public String getConnAddr() {
		return connAddr;
	}
	public void setConnAddr(String connAddr) {
		this.connAddr = connAddr;
	}
	public String getCoopWayCd() {
		return coopWayCd;
	}
	public void setCoopWayCd(String coopWayCd) {
		this.coopWayCd = coopWayCd;
	}
	public String getBisPeople() {
		return bisPeople;
	}
	public void setBisPeople(String bisPeople) {
		this.bisPeople = bisPeople;
	}
	public String getPartyA() {
		return partyA;
	}
	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}
	public String getPartyB() {
		return partyB;
	}
	public void setPartyB(String partyB) {
		this.partyB = partyB;
	}
	public String getPartyC() {
		return partyC;
	}
	public void setPartyC(String partyC) {
		this.partyC = partyC;
	}
	public String getPaymentModeCd() {
		return paymentModeCd;
	}
	public void setPaymentModeCd(String paymentModeCd) {
		this.paymentModeCd = paymentModeCd;
	}
	public String getEarnestMoney() {
		return earnestMoney;
	}
	public void setEarnestMoney(String earnestMoney) {
		this.earnestMoney = earnestMoney;
	}
	public String getFreeRentPeriod() {
		return freeRentPeriod;
	}
	public void setFreeRentPeriod(String freeRentPeriod) {
		this.freeRentPeriod = freeRentPeriod;
	}
	public String getPayCycleCd() {
		return payCycleCd;
	}
	public void setPayCycleCd(String payCycleCd) {
		this.payCycleCd = payCycleCd;
	}
	public String getRentUnit1() {
		return rentUnit1;
	}
	public void setRentUnit1(String rentUnit1) {
		this.rentUnit1 = rentUnit1;
	}
	public String getRentUnit2() {
		return rentUnit2;
	}
	public void setRentUnit2(String rentUnit2) {
		this.rentUnit2 = rentUnit2;
	}
	public String getRentUnit3() {
		return rentUnit3;
	}
	public void setRentUnit3(String rentUnit3) {
		this.rentUnit3 = rentUnit3;
	}
	public String getRentUnit4() {
		return rentUnit4;
	}
	public void setRentUnit4(String rentUnit4) {
		this.rentUnit4 = rentUnit4;
	}
	public String getRentUnit5() {
		return rentUnit5;
	}
	public void setRentUnit5(String rentUnit5) {
		this.rentUnit5 = rentUnit5;
	}
	public String getRentUnit6() {
		return rentUnit6;
	}
	public void setRentUnit6(String rentUnit6) {
		this.rentUnit6 = rentUnit6;
	}
	public String getRentUnit7() {
		return rentUnit7;
	}
	public void setRentUnit7(String rentUnit7) {
		this.rentUnit7 = rentUnit7;
	}
	public String getRentUnit8() {
		return rentUnit8;
	}
	public void setRentUnit8(String rentUnit8) {
		this.rentUnit8 = rentUnit8;
	}
	public String getRentUnit9() {
		return rentUnit9;
	}
	public void setRentUnit9(String rentUnit9) {
		this.rentUnit9 = rentUnit9;
	}
	public String getRentUnit10() {
		return rentUnit10;
	}
	public void setRentUnit10(String rentUnit10) {
		this.rentUnit10 = rentUnit10;
	}
	public String getRentMonth1() {
		return rentMonth1;
	}
	public void setRentMonth1(String rentMonth1) {
		this.rentMonth1 = rentMonth1;
	}
	public String getRentMonth2() {
		return rentMonth2;
	}
	public void setRentMonth2(String rentMonth2) {
		this.rentMonth2 = rentMonth2;
	}
	public String getRentMonth3() {
		return rentMonth3;
	}
	public void setRentMonth3(String rentMonth3) {
		this.rentMonth3 = rentMonth3;
	}
	public String getRentMonth4() {
		return rentMonth4;
	}
	public void setRentMonth4(String rentMonth4) {
		this.rentMonth4 = rentMonth4;
	}
	public String getRentMonth5() {
		return rentMonth5;
	}
	public void setRentMonth5(String rentMonth5) {
		this.rentMonth5 = rentMonth5;
	}
	public String getRentMonth6() {
		return rentMonth6;
	}
	public void setRentMonth6(String rentMonth6) {
		this.rentMonth6 = rentMonth6;
	}
	public String getRentMonth7() {
		return rentMonth7;
	}
	public void setRentMonth7(String rentMonth7) {
		this.rentMonth7 = rentMonth7;
	}
	public String getRentMonth8() {
		return rentMonth8;
	}
	public void setRentMonth8(String rentMonth8) {
		this.rentMonth8 = rentMonth8;
	}
	public String getRentMonth9() {
		return rentMonth9;
	}
	public void setRentMonth9(String rentMonth9) {
		this.rentMonth9 = rentMonth9;
	}
	public String getRentMonth10() {
		return rentMonth10;
	}
	public void setRentMonth10(String rentMonth10) {
		this.rentMonth10 = rentMonth10;
	}
	public String getContApproval() {
		return contApproval;
	}
	public void setContApproval(String contApproval) {
		this.contApproval = contApproval;
	}
	public String getContApprovalId() {
		return contApprovalId;
	}
	public void setContApprovalId(String contApprovalId) {
		this.contApprovalId = contApprovalId;
	}
	public String getContContent() {
		return contContent;
	}
	public void setContContent(String contContent) {
		this.contContent = contContent;
	}
	public String getContContentId() {
		return contContentId;
	}
	public void setContContentId(String contContentId) {
		this.contContentId = contContentId;
	}
	public List<BisContCreditProperty> getCreditProperties() {
		return creditProperties;
	}
	public void setCreditProperties(List<BisContCreditProperty> creditProperties) {
		this.creditProperties = creditProperties;
	}
	public List<BisContAddiProperty> getAddiProperties() {
		return addiProperties;
	}
	public void setAddiProperties(List<BisContAddiProperty> addiProperties) {
		this.addiProperties = addiProperties;
	}
}
