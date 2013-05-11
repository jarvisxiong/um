package com.hhz.ump.web.vo;

import java.math.BigDecimal;

public class VoFact {
	
	private BigDecimal  rowno;//当前行数
	private BigDecimal  maxrowno;//总记录数
	private BigDecimal  pageNo;//当前页数
	private boolean noPage = false;
	private int mustOrFact;//应收、实收维度 
	private String overdue ;//只看欠费
	private String bisFactYuSId;
	
	private String bisFactId;
	private String bisContId;//合同
	private String contNo;//合同编号
	private String contBigTypeCd;//合同
	private String bisFloorId;
	private String contLayOutCd;//商铺 公寓 多经营
	private String buildingNum;//楼号
	private String floorNum;//楼层
	private String chargeTypeCd;//费用类别
	private String chargeTypeCdName;//费用类别
	private String factYear;//
	private String factMonth;//
	private String reportDateStart;//权责年月开始
	private String reportDateEnd; //权责年月结束
	private BigDecimal mustMoney;//应收
	private String bisMustId;
	private BigDecimal money;//实收
	private BigDecimal overDueMoney;//实收
	private String attach;//附件
	private String statusCd;//审核状态
	private String statusCdName;//审核状态
	private String creator;//录入人
	private String checkUserCd;//审核人
	private String checkUserName;//审核人
	private String checkDate;//审核时间
	private String factDate;//实收时间---收款时间
	
	private String inDateBegin;//收款开始时间
	private String inDateEnd;//收款结束时间
	private String updateDate;//更新时间
	private String createDate;//创建时间
	private String isModify;// 0 不可操作，1：可修改新增，2：可审核驳回 删除
	
	public static final String OPERA_NO = "0";
	public static final String OPERA_Mod = "1";
	public static final String OPERA_Appr = "2";
	
	//高级搜索条件
	private String minMonth="";
	private String maxMonth="";
	private BigDecimal minMoney;
	private BigDecimal maxMoney;
	
	/****************************商铺***************************/
	private String bisStoreId;
	private String storeNo;//商铺编号
	
	private String bisShopId;//商家
	
	private String nameCn;//商家名称
	
	private String bisTenantId="";
	private String bisTenantIds="";
	private String connName;//租户姓名
	 
	private String manageCd="";//经营性质(BisShop)
	private String shopTypeCd="";//租户性质(BisShop)
	/****************************公寓***************************/
	private String layoutCd="";//业态
	
	private String bisFlatId="";
	private String flatNo;//公寓编号
	private String houseStruCd;//房屋结构
	/****************************多径***************************/
	private String bisProjectId="";
	private String operationProjectCd;//经营项目 数据字典
	private String bisMultiId="";
	private String multiName;//多经名称
    private String renterName;//承租方
    private String operationArea;//位置区域
	private String square;//面积
	private String remark;
	private String facts;//应收对应的实收列表
	

	/****************************实收增加***************************/
	private String sumMustMoney = "0";//累计应收
	private String sumFactMoney = "0";//累计实收
	private String sumYuMoney = "0";	//累计预收
	private String sumQianMoney = "0";//累计欠费
	private String splitStatus;		//店铺状态
	
	
	public String getSumMustMoney() {
		return sumMustMoney;
	}
	public void setSumMustMoney(String sumMustMoney) {
		this.sumMustMoney = sumMustMoney;
	}
	public String getSumFactMoney() {
		return sumFactMoney;
	}
	public void setSumFactMoney(String sumFactMoney) {
		this.sumFactMoney = sumFactMoney;
	}
	public String getSumYuMoney() {
		return sumYuMoney;
	}
	public void setSumYuMoney(String sumYuMoney) {
		this.sumYuMoney = sumYuMoney;
	}
	/***
	 * 返回"应收"-"实收"
	 * @return
	 */
	public String getSumQianMoney() {
		return sumQianMoney;
	}
	public void setSumQianMoney(String sumQianMoney) {
		this.sumQianMoney = sumQianMoney;
	}
	public String getSplitStatus() {
		return splitStatus;
	}
	public void setSplitStatus(String splitStatus) {
		this.splitStatus = splitStatus;
	}
	/*	private String 
	private String */
	public String getBisFloorId() {
		return bisFloorId;
	}
	public void setBisFloorId(String bisFloorId) {
		this.bisFloorId = bisFloorId;
	}
	public String getFloorNum() {
		return floorNum;
	}
	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}
	public String getChargeTypeCd() {
		return chargeTypeCd;
	}
	public void setChargeTypeCd(String chargeTypeCd) {
		this.chargeTypeCd = chargeTypeCd;
	}
	
	public String getFactYear() {
		return factYear;
	}
	public void setFactYear(String factYear) {
		this.factYear = factYear;
	}
	public String getFactMonth() {
		return factMonth;
	}
	public void setFactMonth(String factMonth) {
		this.factMonth = factMonth;
	}
	public BigDecimal getMustMoney() {
		return mustMoney;
	}
	public void setMustMoney(BigDecimal mustMoney) {
		this.mustMoney = mustMoney;
	}
	public String getMinMonth() {
		return minMonth;
	}
	public void setMinMonth(String minMonth) {
		this.minMonth = minMonth;
	}
	public String getMaxMonth() {
		return maxMonth;
	}
	public void setMaxMonth(String maxMonth) {
		this.maxMonth = maxMonth;
	}
	public BigDecimal getMinMoney() {
		return minMoney;
	}
	public void setMinMoney(BigDecimal minMoney) {
		this.minMoney = minMoney;
	}
	public BigDecimal getMaxMoney() {
		return maxMoney;
	}
	public void setMaxMoney(BigDecimal maxMoney) {
		this.maxMoney = maxMoney;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getCheckUserCd() {
		return checkUserCd;
	}
	public void setCheckUserCd(String checkUserCd) {
		this.checkUserCd = checkUserCd;
	}
	public String getCheckUserName() {
		return checkUserName;
	}
	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getFactDate() {
		return factDate;
	}
	public void setFactDate(String factDate) {
		this.factDate = factDate;
	}
	public String getLayoutCd() {
		return layoutCd;
	}
	public void setLayoutCd(String layoutCd) {
		this.layoutCd = layoutCd;
	}
	public String getBisStoreId() {
		return bisStoreId;
	}
	public void setBisStoreId(String bisStoreId) {
		this.bisStoreId = bisStoreId;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getBisTenantId() {
		return bisTenantId;
	}
	public void setBisTenantId(String bisTenantId) {
		this.bisTenantId = bisTenantId;
	}
	public String getBisTenantIds() {
		return bisTenantIds;
	}
	public void setBisTenantIds(String bisTenantIds) {
		this.bisTenantIds = bisTenantIds;
	}
	public String getConnName() {
		return connName;
	}
	public void setConnName(String connName) {
		this.connName = connName;
	}
	public String getFlatNo() {
		return flatNo;
	}
	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}
	public String getHouseStruCd() {
		return houseStruCd;
	}
	public void setHouseStruCd(String houseStruCd) {
		this.houseStruCd = houseStruCd;
	}
	public String getBisMustId() {
		return bisMustId;
	}
	public void setBisMustId(String bisMustId) {
		this.bisMustId = bisMustId;
	}
	public String getManageCd() {
		return manageCd;
	}
	public void setManageCd(String manageCd) {
		this.manageCd = manageCd;
	}
	public String getShopTypeCd() {
		return shopTypeCd;
	}
	public void setShopTypeCd(String shopTypeCd) {
		this.shopTypeCd = shopTypeCd;
	}
	public String getBisContId() {
		return bisContId;
	}
	public void setBisContId(String bisContId) {
		this.bisContId = bisContId;
	}
	public String getBisShopId() {
		return bisShopId;
	}
	public void setBisShopId(String bisShopId) {
		this.bisShopId = bisShopId;
	}
	public String getNameCn() {
		return nameCn;
	}
	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}
	public String getBisFlatId() {
		return bisFlatId;
	}
	public void setBisFlatId(String bisFlatId) {
		this.bisFlatId = bisFlatId;
	}
	public String getBisProjectId() {
		return bisProjectId;
	}
	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}
	
	public String getOperationProjectCd() {
		return operationProjectCd;
	}
	public void setOperationProjectCd(String operationProjectCd) {
		this.operationProjectCd = operationProjectCd;
	}
	public String getBisMultiId() {
		return bisMultiId;
	}
	public void setBisMultiId(String bisMultiId) {
		this.bisMultiId = bisMultiId;
	}
	public String getMultiName() {
		return multiName;
	}
	public void setMultiName(String multiName) {
		this.multiName = multiName;
	}
	public String getOperationArea() {
		return operationArea;
	}
	public void setOperationArea(String operationArea) {
		this.operationArea = operationArea;
	}
	public String getSquare() {
		return square;
	}
	public void setSquare(String square) {
		this.square = square;
	}
	public String getBisFactId() {
		return bisFactId;
	}
	public void setBisFactId(String bisFactId) {
		this.bisFactId = bisFactId;
	}
	public String getBuildingNum() {
		return buildingNum;
	}
	public void setBuildingNum(String buildingNum) {
		this.buildingNum = buildingNum;
	}
	public String getRenterName() {
		return renterName;
	}
	public void setRenterName(String renterName) {
		this.renterName = renterName;
	}
	public String getIsModify() {
		return isModify;
	}
	public void setIsModify(String isModify) {
		this.isModify = isModify;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getChargeTypeCdName() {
		return chargeTypeCdName;
	}
	public void setChargeTypeCdName(String chargeTypeCdName) {
		this.chargeTypeCdName = chargeTypeCdName;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getStatusCdName() {
		return statusCdName;
	}
	public String getContLayOutCd() {
		return contLayOutCd;
	}
	public void setContLayOutCd(String contLayOutCd) {
		this.contLayOutCd = contLayOutCd;
	}
	public void setStatusCdName(String statusCdName) {
		this.statusCdName = statusCdName;
	}
    public BigDecimal getRowno() {
		return rowno;
	}
	public void setRowno(BigDecimal rowno) {
		this.rowno = rowno;
	}
	public BigDecimal getMaxrowno() {
		return maxrowno;
	}
	public void setMaxrowno(BigDecimal maxrowno) {
		this.maxrowno = maxrowno;
	}
	public String getOverdue() {
		return overdue;
	}
	public void setOverdue(String overdue) {
		this.overdue = overdue;
	}
	public int getMustOrFact() {
		return mustOrFact;
	}
	public void setMustOrFact(int mustOrFact) {
		this.mustOrFact = mustOrFact;
	}
	public BigDecimal getPageNo() {
		return pageNo;
	}
	public void setPageNo(BigDecimal pageNo) {
		this.pageNo = pageNo;
	}
	public String getInDateBegin() {
		return inDateBegin;
	}
	public void setInDateBegin(String inDateBegin) {
		this.inDateBegin = inDateBegin;
	}
	public String getInDateEnd() {
		return inDateEnd;
	}
	public void setInDateEnd(String inDateEnd) {
		this.inDateEnd = inDateEnd;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getContNo() {
		return contNo;
	}
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	public String getContBigTypeCd() {
		return contBigTypeCd;
	}
	public void setContBigTypeCd(String contBigTypeCd) {
		this.contBigTypeCd = contBigTypeCd;
	}
	public String getFacts() {
		return facts;
	}
	public void setFacts(String facts) {
		this.facts = facts;
	}
	public boolean isNoPage() {
		return noPage;
	}
	public BigDecimal getOverDueMoney() {
		return overDueMoney;
	}
	public void setOverDueMoney(BigDecimal overDueMoney) {
		this.overDueMoney = overDueMoney;
	}
	public void setNoPage(boolean noPage) {
		this.noPage = noPage;
	}
	public String getBisFactYuSId() {
		return bisFactYuSId;
	}
	public void setBisFactYuSId(String bisFactYuSId) {
		this.bisFactYuSId = bisFactYuSId;
	}
	public String getReportDateStart() {
		return reportDateStart;
	}
	public void setReportDateStart(String reportDateStart) {
		this.reportDateStart = reportDateStart;
	}
	public String getReportDateEnd() {
		return reportDateEnd;
	}
	public void setReportDateEnd(String reportDateEnd) {
		this.reportDateEnd = reportDateEnd;
	}
	
}

