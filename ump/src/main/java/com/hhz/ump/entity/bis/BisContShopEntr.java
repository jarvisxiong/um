// 
package com.hhz.ump.entity.bis;
// Generated 2011-8-3 22:32:28 by Hibernate Tools 3.2.2.GA


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

/**
 * BisContShopEntr generated by hbm2java
 */
@Entity
@Table(name="BIS_CONT_SHOP_ENTR"
    
)
public class BisContShopEntr  implements java.io.Serializable {


     private String bisContShopEntrId;
     private BisCont bisCont;
     private String storeNo;
     private String entrRentYears;
     private String freeRentPeriod;
     private BigDecimal earnestMoney;
     private BigDecimal rent1;
     private BigDecimal rent2;
     private BigDecimal rent3;
     private BigDecimal rent4;
     private BigDecimal rent5;
     private BigDecimal rent6;
     private BigDecimal rent7;
     private BigDecimal rent8;
     private BigDecimal rent9;
     private BigDecimal rent10;
     private BigDecimal rentRatio1;
     private BigDecimal rentRatio2;
     private BigDecimal rentRatio3;
     private BigDecimal rentRatio4;
     private BigDecimal rentRatio5;
     private BigDecimal rentRatio6;
     private BigDecimal rentRatio7;
     private BigDecimal rentRatio8;
     private BigDecimal rentRatio9;
     private BigDecimal rentRatio10;
     private String paymentModeCd;
     private String contDeadline;
     private String beEntrRenter;
     private String paymentTime;
     private String remark;
     private String creator;
     private String createdCenterCd;
     private String createdDeptCd;
     private String createdPositionCd;
     private Date createdDate;
     private String updator;
     private String updatedCenterCd;
     private String updatedDeptCd;
     private String updatedPositionCd;
     private Date updatedDate;
     private long recordVersion;

    public BisContShopEntr() {
    }

	
    public BisContShopEntr(String bisContShopEntrId, BisCont bisCont, String storeNo, long recordVersion) {
        this.bisContShopEntrId = bisContShopEntrId;
        this.bisCont = bisCont;
        this.storeNo = storeNo;
        this.recordVersion = recordVersion;
    }
    public BisContShopEntr(String bisContShopEntrId, BisCont bisCont, String storeNo, String entrRentYears, String freeRentPeriod, BigDecimal earnestMoney, BigDecimal rent1, BigDecimal rent2, BigDecimal rent3, BigDecimal rent4, BigDecimal rent5, BigDecimal rent6, BigDecimal rent7, BigDecimal rent8, BigDecimal rent9, BigDecimal rent10, BigDecimal rentRatio1, BigDecimal rentRatio2, BigDecimal rentRatio3, BigDecimal rentRatio4, BigDecimal rentRatio5, BigDecimal rentRatio6, BigDecimal rentRatio7, BigDecimal rentRatio8, BigDecimal rentRatio9, BigDecimal rentRatio10, String paymentModeCd, String contDeadline, String beEntrRenter, String paymentTime, String remark, String creator, String createdCenterCd, String createdDeptCd, String createdPositionCd, Date createdDate, String updator, String updatedCenterCd, String updatedDeptCd, String updatedPositionCd, Date updatedDate, long recordVersion) {
       this.bisContShopEntrId = bisContShopEntrId;
       this.bisCont = bisCont;
       this.storeNo = storeNo;
       this.entrRentYears = entrRentYears;
       this.freeRentPeriod = freeRentPeriod;
       this.earnestMoney = earnestMoney;
       this.rent1 = rent1;
       this.rent2 = rent2;
       this.rent3 = rent3;
       this.rent4 = rent4;
       this.rent5 = rent5;
       this.rent6 = rent6;
       this.rent7 = rent7;
       this.rent8 = rent8;
       this.rent9 = rent9;
       this.rent10 = rent10;
       this.rentRatio1 = rentRatio1;
       this.rentRatio2 = rentRatio2;
       this.rentRatio3 = rentRatio3;
       this.rentRatio4 = rentRatio4;
       this.rentRatio5 = rentRatio5;
       this.rentRatio6 = rentRatio6;
       this.rentRatio7 = rentRatio7;
       this.rentRatio8 = rentRatio8;
       this.rentRatio9 = rentRatio9;
       this.rentRatio10 = rentRatio10;
       this.paymentModeCd = paymentModeCd;
       this.contDeadline = contDeadline;
       this.beEntrRenter = beEntrRenter;
       this.paymentTime = paymentTime;
       this.remark = remark;
       this.creator = creator;
       this.createdCenterCd = createdCenterCd;
       this.createdDeptCd = createdDeptCd;
       this.createdPositionCd = createdPositionCd;
       this.createdDate = createdDate;
       this.updator = updator;
       this.updatedCenterCd = updatedCenterCd;
       this.updatedDeptCd = updatedDeptCd;
       this.updatedPositionCd = updatedPositionCd;
       this.updatedDate = updatedDate;
       this.recordVersion = recordVersion;
    }
   
     @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex") 
    
    @Column(name="BIS_CONT_SHOP_ENTR_ID", unique=true, nullable=false, length=50)
    public String getBisContShopEntrId() {
        return this.bisContShopEntrId;
    }
    
    public void setBisContShopEntrId(String bisContShopEntrId) {
        this.bisContShopEntrId = bisContShopEntrId;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BIS_CONT_ID", nullable=false)
    public BisCont getBisCont() {
        return this.bisCont;
    }
    
    public void setBisCont(BisCont bisCont) {
        this.bisCont = bisCont;
    }
    
    @Column(name="STORE_NO", nullable=false, length=100)
    public String getStoreNo() {
        return this.storeNo;
    }
    
    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }
    
    @Column(name="ENTR_RENT_YEARS", length=100)
    public String getEntrRentYears() {
        return this.entrRentYears;
    }
    
    public void setEntrRentYears(String entrRentYears) {
        this.entrRentYears = entrRentYears;
    }
    
    @Column(name="FREE_RENT_PERIOD", length=100)
    public String getFreeRentPeriod() {
        return this.freeRentPeriod;
    }
    
    public void setFreeRentPeriod(String freeRentPeriod) {
        this.freeRentPeriod = freeRentPeriod;
    }
    
    @Column(name="EARNEST_MONEY", precision=10)
    public BigDecimal getEarnestMoney() {
        return this.earnestMoney;
    }
    
    public void setEarnestMoney(BigDecimal earnestMoney) {
        this.earnestMoney = earnestMoney;
    }
    
    @Column(name="RENT1", precision=10)
    public BigDecimal getRent1() {
        return this.rent1;
    }
    
    public void setRent1(BigDecimal rent1) {
        this.rent1 = rent1;
    }
    
    @Column(name="RENT2", precision=10)
    public BigDecimal getRent2() {
        return this.rent2;
    }
    
    public void setRent2(BigDecimal rent2) {
        this.rent2 = rent2;
    }
    
    @Column(name="RENT3", precision=10)
    public BigDecimal getRent3() {
        return this.rent3;
    }
    
    public void setRent3(BigDecimal rent3) {
        this.rent3 = rent3;
    }
    
    @Column(name="RENT4", precision=10)
    public BigDecimal getRent4() {
        return this.rent4;
    }
    
    public void setRent4(BigDecimal rent4) {
        this.rent4 = rent4;
    }
    
    @Column(name="RENT5", precision=10)
    public BigDecimal getRent5() {
        return this.rent5;
    }
    
    public void setRent5(BigDecimal rent5) {
        this.rent5 = rent5;
    }
    
    @Column(name="RENT6", precision=10)
    public BigDecimal getRent6() {
        return this.rent6;
    }
    
    public void setRent6(BigDecimal rent6) {
        this.rent6 = rent6;
    }
    
    @Column(name="RENT7", precision=10)
    public BigDecimal getRent7() {
        return this.rent7;
    }
    
    public void setRent7(BigDecimal rent7) {
        this.rent7 = rent7;
    }
    
    @Column(name="RENT8", precision=10)
    public BigDecimal getRent8() {
        return this.rent8;
    }
    
    public void setRent8(BigDecimal rent8) {
        this.rent8 = rent8;
    }
    
    @Column(name="RENT9", precision=10)
    public BigDecimal getRent9() {
        return this.rent9;
    }
    
    public void setRent9(BigDecimal rent9) {
        this.rent9 = rent9;
    }
    
    @Column(name="RENT10", precision=10)
    public BigDecimal getRent10() {
        return this.rent10;
    }
    
    public void setRent10(BigDecimal rent10) {
        this.rent10 = rent10;
    }
    
    @Column(name="RENT_RATIO1", precision=10)
    public BigDecimal getRentRatio1() {
        return this.rentRatio1;
    }
    
    public void setRentRatio1(BigDecimal rentRatio1) {
        this.rentRatio1 = rentRatio1;
    }
    
    @Column(name="RENT_RATIO2", precision=10)
    public BigDecimal getRentRatio2() {
        return this.rentRatio2;
    }
    
    public void setRentRatio2(BigDecimal rentRatio2) {
        this.rentRatio2 = rentRatio2;
    }
    
    @Column(name="RENT_RATIO3", precision=10)
    public BigDecimal getRentRatio3() {
        return this.rentRatio3;
    }
    
    public void setRentRatio3(BigDecimal rentRatio3) {
        this.rentRatio3 = rentRatio3;
    }
    
    @Column(name="RENT_RATIO4", precision=10)
    public BigDecimal getRentRatio4() {
        return this.rentRatio4;
    }
    
    public void setRentRatio4(BigDecimal rentRatio4) {
        this.rentRatio4 = rentRatio4;
    }
    
    @Column(name="RENT_RATIO5", precision=10)
    public BigDecimal getRentRatio5() {
        return this.rentRatio5;
    }
    
    public void setRentRatio5(BigDecimal rentRatio5) {
        this.rentRatio5 = rentRatio5;
    }
    
    @Column(name="RENT_RATIO6", precision=10)
    public BigDecimal getRentRatio6() {
        return this.rentRatio6;
    }
    
    public void setRentRatio6(BigDecimal rentRatio6) {
        this.rentRatio6 = rentRatio6;
    }
    
    @Column(name="RENT_RATIO7", precision=10)
    public BigDecimal getRentRatio7() {
        return this.rentRatio7;
    }
    
    public void setRentRatio7(BigDecimal rentRatio7) {
        this.rentRatio7 = rentRatio7;
    }
    
    @Column(name="RENT_RATIO8", precision=10)
    public BigDecimal getRentRatio8() {
        return this.rentRatio8;
    }
    
    public void setRentRatio8(BigDecimal rentRatio8) {
        this.rentRatio8 = rentRatio8;
    }
    
    @Column(name="RENT_RATIO9", precision=10)
    public BigDecimal getRentRatio9() {
        return this.rentRatio9;
    }
    
    public void setRentRatio9(BigDecimal rentRatio9) {
        this.rentRatio9 = rentRatio9;
    }
    
    @Column(name="RENT_RATIO10", precision=10)
    public BigDecimal getRentRatio10() {
        return this.rentRatio10;
    }
    
    public void setRentRatio10(BigDecimal rentRatio10) {
        this.rentRatio10 = rentRatio10;
    }
    
    @Column(name="PAYMENT_MODE_CD", length=20)
    public String getPaymentModeCd() {
        return this.paymentModeCd;
    }
    
    public void setPaymentModeCd(String paymentModeCd) {
        this.paymentModeCd = paymentModeCd;
    }
    
    @Column(name="CONT_DEADLINE", length=100)
    public String getContDeadline() {
        return this.contDeadline;
    }
    
    public void setContDeadline(String contDeadline) {
        this.contDeadline = contDeadline;
    }
    
    @Column(name="BE_ENTR_RENTER", length=100)
    public String getBeEntrRenter() {
        return this.beEntrRenter;
    }
    
    public void setBeEntrRenter(String beEntrRenter) {
        this.beEntrRenter = beEntrRenter;
    }

    @Column(name="PAYMENT_TIME", length=100)
    public String getPaymentTime() {
        return this.paymentTime;
    }
    
    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }
    
    @Column(name="REMARK", length=200)
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="CREATOR", length=20)
    public String getCreator() {
        return this.creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    @Column(name="CREATED_CENTER_CD", length=20)
    public String getCreatedCenterCd() {
        return this.createdCenterCd;
    }
    
    public void setCreatedCenterCd(String createdCenterCd) {
        this.createdCenterCd = createdCenterCd;
    }
    
    @Column(name="CREATED_DEPT_CD", length=20)
    public String getCreatedDeptCd() {
        return this.createdDeptCd;
    }
    
    public void setCreatedDeptCd(String createdDeptCd) {
        this.createdDeptCd = createdDeptCd;
    }
    
    @Column(name="CREATED_POSITION_CD", length=20)
    public String getCreatedPositionCd() {
        return this.createdPositionCd;
    }
    
    public void setCreatedPositionCd(String createdPositionCd) {
        this.createdPositionCd = createdPositionCd;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED_DATE", length=7)
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    @Column(name="UPDATOR", length=20)
    public String getUpdator() {
        return this.updator;
    }
    
    public void setUpdator(String updator) {
        this.updator = updator;
    }
    
    @Column(name="UPDATED_CENTER_CD", length=20)
    public String getUpdatedCenterCd() {
        return this.updatedCenterCd;
    }
    
    public void setUpdatedCenterCd(String updatedCenterCd) {
        this.updatedCenterCd = updatedCenterCd;
    }
    
    @Column(name="UPDATED_DEPT_CD", length=20)
    public String getUpdatedDeptCd() {
        return this.updatedDeptCd;
    }
    
    public void setUpdatedDeptCd(String updatedDeptCd) {
        this.updatedDeptCd = updatedDeptCd;
    }
    
    @Column(name="UPDATED_POSITION_CD", length=20)
    public String getUpdatedPositionCd() {
        return this.updatedPositionCd;
    }
    
    public void setUpdatedPositionCd(String updatedPositionCd) {
        this.updatedPositionCd = updatedPositionCd;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UPDATED_DATE", length=7)
    public Date getUpdatedDate() {
        return this.updatedDate;
    }
    
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    @Column(name="RECORD_VERSION", nullable=false, precision=10, scale=0)
    @Version 
    public long getRecordVersion() {
        return this.recordVersion;
    }
    
    public void setRecordVersion(long recordVersion) {
        this.recordVersion = recordVersion;
    }




}


