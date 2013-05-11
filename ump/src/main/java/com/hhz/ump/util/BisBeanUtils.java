package com.hhz.ump.util;

import org.apache.commons.lang.StringUtils;

import com.hhz.ump.entity.bis.BisCont;
import com.hhz.ump.entity.bis.BisContAddiHis;
import com.hhz.ump.entity.bis.BisContAdditional;
import com.hhz.ump.entity.bis.BisContCredit;
import com.hhz.ump.entity.bis.BisContCreditHis;
import com.hhz.ump.entity.bis.BisContFlatProp;
import com.hhz.ump.entity.bis.BisContHis;
import com.hhz.ump.entity.bis.BisContMulti;
import com.hhz.ump.entity.bis.BisContShopBack;
import com.hhz.ump.entity.bis.BisContShopEntr;
import com.hhz.ump.entity.bis.BisContShopProp;
import com.hhz.ump.entity.bis.BisContShopRent;
import com.hhz.ump.entity.bis.BisMustFormula;
import com.hhz.ump.entity.bis.BisMustRent;
import com.hhz.ump.entity.bis.BisMustRentHis;


public final class BisBeanUtils {  
	
	private BisBeanUtils() {
		
	}  

	/**
	 * 复制'合同表'到'合同历史表'
	 * 
	 * @param orig 合同表
	 * @param dest 合同历史表
	 */
	public static void copyToHis(BisCont orig, BisContHis dest) {
		
		copyMainContToHis(orig, dest);
		
		copySubContToHis(orig, dest);
		
		if(!orig.getBisMustFormulas().isEmpty()) {
			copyFormulaToHis(orig.getBisMustFormulas().get(0), dest);
		}
	}

	/**
	 * 复制'合同历史表'到'合同表'
	 * 
	 * @param orig 合同历史表
	 * @param dest 合同表
	 */
	public static void copyFromHis(BisContHis orig, BisCont dest) {
		
		copyHisToMainCont(orig, dest);
		
		copyHisToSubCont(orig, dest);
		
		if(StringUtils.isNotBlank(orig.getFormula())) {
			if(dest.getBisMustFormulas().isEmpty()) {
				BisMustFormula formula = new BisMustFormula();
				copyHisToFormula(orig, formula);
				formula.setBisCont(dest);
				dest.getBisMustFormulas().add(formula);
			} else {
				copyHisToFormula(orig, dest.getBisMustFormulas().get(0));
			}
		}
	}

	/**
	 * 复制'补充合同表'到'补充合同历史表'
	 * 
	 * @param orig 补充合同表
	 * @param dest 补充合同历史表
	 */
	public static void copyToAddiHis(BisContAdditional orig, BisContAddiHis dest) {
		
		dest.setSignDate(orig.getSignDate());
		dest.setContAdditionalName(orig.getContAdditionalName());
		dest.setContAdditionalContent(orig.getContAdditionalContent());
		dest.setContAdditionalDesc(orig.getContAdditionalDesc());
		dest.setAttachFlg(orig.getAttachFlg());
		dest.setBisContAdditionalId(orig.getBisContAdditionalId());
		dest.setRemark(orig.getRemark());
		dest.setCreator(orig.getCreator());
		dest.setCreatedCenterCd(orig.getCreatedCenterCd());
		dest.setCreatedDeptCd(orig.getCreatedDeptCd());
		dest.setCreatedPositionCd(orig.getCreatedPositionCd());
		dest.setCreatedDate(orig.getCreatedDate());
		dest.setUpdator(orig.getUpdator());
		dest.setUpdatedCenterCd(orig.getUpdatedCenterCd());
		dest.setUpdatedDeptCd(orig.getUpdatedDeptCd());
		dest.setUpdatedPositionCd(orig.getUpdatedPositionCd());
		dest.setUpdatedDate(orig.getUpdatedDate());
		dest.setRecordVersion(orig.getRecordVersion());
	}
	
	/**
	 * 复制'补充合同历史表'到'补充合同表'
	 * 
	 * @param orig 补充合同历史表
	 * @param dest 补充合同表
	 */
	public static void copyFromAddiHis(BisContAddiHis orig,  BisContAdditional dest, boolean isCopyId) {
		
		dest.setSignDate(orig.getSignDate());
		dest.setContAdditionalName(orig.getContAdditionalName());
		dest.setContAdditionalContent(orig.getContAdditionalContent());
		dest.setContAdditionalDesc(orig.getContAdditionalDesc());
		dest.setAttachFlg(orig.getAttachFlg());
		if(isCopyId) {
			dest.setBisContAdditionalId(orig.getBisContAdditionalId());
		}
		dest.setRemark(orig.getRemark());
		dest.setCreator(orig.getCreator());
		dest.setCreatedCenterCd(orig.getCreatedCenterCd());
		dest.setCreatedDeptCd(orig.getCreatedDeptCd());
		dest.setCreatedPositionCd(orig.getCreatedPositionCd());
		dest.setCreatedDate(orig.getCreatedDate());
		dest.setUpdator(orig.getUpdator());
		dest.setUpdatedCenterCd(orig.getUpdatedCenterCd());
		dest.setUpdatedDeptCd(orig.getUpdatedDeptCd());
		dest.setUpdatedPositionCd(orig.getUpdatedPositionCd());
		dest.setUpdatedDate(orig.getUpdatedDate());
		dest.setRecordVersion(orig.getRecordVersion());
	}

	/**
	 * 复制'补充合同表'到'补充合同历史表'
	 * 
	 * @param orig 补充合同表
	 * @param dest 补充合同历史表
	 */
	public static void copyToMustRentHis(BisMustRent orig, BisMustRentHis dest) {
		
		dest.setUnitMoney(orig.getUnitMoney());
		dest.setTotalMoney(orig.getTotalMoney());
		dest.setBisMustRentId(orig.getBisMustRentId());
		dest.setRemark(orig.getRemark());
		dest.setCreator(orig.getCreator());
		dest.setCreatedCenterCd(orig.getCreatedCenterCd());
		dest.setCreatedDeptCd(orig.getCreatedDeptCd());
		dest.setCreatedPositionCd(orig.getCreatedPositionCd());
		dest.setCreatedDate(orig.getCreatedDate());
		dest.setUpdator(orig.getUpdator());
		dest.setUpdatedCenterCd(orig.getUpdatedCenterCd());
		dest.setUpdatedDeptCd(orig.getUpdatedDeptCd());
		dest.setUpdatedPositionCd(orig.getUpdatedPositionCd());
		dest.setUpdatedDate(orig.getUpdatedDate());
		dest.setRecordVersion(orig.getRecordVersion());
	}
	
	/**
	 * 复制'自动应收历史表'到'自动应收表'
	 * 
	 * @param orig 自动应收历史表
	 * @param dest 自动应收表
	 */
	public static void copyFromMustRentHis(BisMustRentHis orig,  BisMustRent dest, boolean isCopyId) {
		
		dest.setUnitMoney(orig.getUnitMoney());
		dest.setTotalMoney(orig.getTotalMoney());
		if(isCopyId) {
			dest.setBisMustRentId(orig.getBisMustRentId());
		}
		dest.setRemark(orig.getRemark());
		dest.setCreator(orig.getCreator());
		dest.setCreatedCenterCd(orig.getCreatedCenterCd());
		dest.setCreatedDeptCd(orig.getCreatedDeptCd());
		dest.setCreatedPositionCd(orig.getCreatedPositionCd());
		dest.setCreatedDate(orig.getCreatedDate());
		dest.setUpdator(orig.getUpdator());
		dest.setUpdatedCenterCd(orig.getUpdatedCenterCd());
		dest.setUpdatedDeptCd(orig.getUpdatedDeptCd());
		dest.setUpdatedPositionCd(orig.getUpdatedPositionCd());
		dest.setUpdatedDate(orig.getUpdatedDate());
		dest.setRecordVersion(orig.getRecordVersion());
	}
	
	/**
	 * 复制'合同资信表'到'合同资信历史表'
	 * 
	 * @param orig 合同资信表
	 * @param dest 合同资信历史表
	 */
	public static void copyToCreditHis(BisContCredit orig, BisContCreditHis dest) {
		
		dest.setBisContCreditId(orig.getBisContCreditId());
		dest.setSequenceNo(orig.getSequenceNo());
		dest.setInfoName(orig.getInfoName());
		dest.setInfoLimit(orig.getInfoLimit());
		dest.setAttachFlg(orig.getAttachFlg());
		dest.setEntityTmpId(orig.getEntityTmpId());
		dest.setRemark(orig.getRemark());
		dest.setCreator(orig.getCreator());
		dest.setCreatedCenterCd(orig.getCreatedCenterCd());
		dest.setCreatedDeptCd(orig.getCreatedDeptCd());
		dest.setCreatedPositionCd(orig.getCreatedPositionCd());
		dest.setCreatedDate(orig.getCreatedDate());
		dest.setUpdator(orig.getUpdator());
		dest.setUpdatedCenterCd(orig.getUpdatedCenterCd());
		dest.setUpdatedDeptCd(orig.getUpdatedDeptCd());
		dest.setUpdatedPositionCd(orig.getUpdatedPositionCd());
		dest.setUpdatedDate(orig.getUpdatedDate());
		dest.setRecordVersion(orig.getRecordVersion());
	}
	
	/**
	 * 复制'合同资信历史表'到'合同资信表'
	 * 
	 * @param orig 合同资信历史表
	 * @param dest 合同资信表
	 */
	public static void copyFromCreditHis(BisContCreditHis orig,  BisContCredit dest, boolean isCopyId) {
		
		if(isCopyId) {
			dest.setBisContCreditId(orig.getBisContCreditId());
		}
		dest.setSequenceNo(orig.getSequenceNo());
		dest.setInfoName(orig.getInfoName());
		dest.setInfoLimit(orig.getInfoLimit());
		dest.setAttachFlg(orig.getAttachFlg());
		dest.setEntityTmpId(orig.getEntityTmpId());
		dest.setRemark(orig.getRemark());
		dest.setCreator(orig.getCreator());
		dest.setCreatedCenterCd(orig.getCreatedCenterCd());
		dest.setCreatedDeptCd(orig.getCreatedDeptCd());
		dest.setCreatedPositionCd(orig.getCreatedPositionCd());
		dest.setCreatedDate(orig.getCreatedDate());
		dest.setUpdator(orig.getUpdator());
		dest.setUpdatedCenterCd(orig.getUpdatedCenterCd());
		dest.setUpdatedDeptCd(orig.getUpdatedDeptCd());
		dest.setUpdatedPositionCd(orig.getUpdatedPositionCd());
		dest.setUpdatedDate(orig.getUpdatedDate());
		dest.setRecordVersion(orig.getRecordVersion());
	}
	
	/**
	 * Copy BisCont to BisContHis
	 */
	private static void copyMainContToHis(BisCont orig, BisContHis dest) {
		dest.setBisContId(orig.getBisContId());
		dest.setContNo(orig.getContNo());
		dest.setBisTenantId(orig.getBisTenantId());
		dest.setBisProjectId(orig.getBisProjectId());
		dest.setContName(orig.getContName());
		dest.setBisPeople(orig.getBisPeople());
		dest.setBrand(orig.getBrand());
		dest.setPartyA(orig.getPartyA());
		dest.setPartyB(orig.getPartyB());
		dest.setPartyC(orig.getPartyC());
		dest.setPartyD(orig.getPartyD());
		dest.setConnAddr(orig.getConnAddr());
		dest.setConnTel(orig.getConnTel());
		dest.setConnPeople(orig.getConnPeople());
		dest.setBuildingName(orig.getBuildingName());
		dest.setRentDate(orig.getRentDate());
		dest.setContStartDate(orig.getContStartDate());
		dest.setContEndDate(orig.getContEndDate());
		dest.setCoopWayCd(orig.getCoopWayCd());
		dest.setContBigTypeCd(orig.getContBigTypeCd());
		dest.setContSmallTypeCd(orig.getContSmallTypeCd());
		dest.setContMoney(orig.getContMoney());
		dest.setSignDate(orig.getSignDate());
		dest.setContContent(orig.getContContent());
		dest.setCheckUserCd(orig.getCheckUserCd());
		dest.setCheckDate(orig.getCheckDate());
		dest.setAttachFlg(orig.getAttachFlg());//
		dest.setActiveBl(orig.isActiveBl());//
		dest.setStatusCd(orig.getStatusCd());
		dest.setContToFailDate(orig.getContToFailDate());
		dest.setChargerTypeArr(orig.getChargerTypeArr());
		dest.setRemark(orig.getRemark());
		dest.setCreator(orig.getCreator());
		dest.setCreatedCenterCd(orig.getCreatedCenterCd());
		dest.setCreatedDeptCd(orig.getCreatedDeptCd());
		dest.setCreatedPositionCd(orig.getCreatedPositionCd());
		dest.setCreatedDate(orig.getCreatedDate());
		dest.setUpdator(orig.getUpdator());
		dest.setUpdatedCenterCd(orig.getUpdatedCenterCd());
		dest.setUpdatedDeptCd(orig.getUpdatedDeptCd());
		dest.setUpdatedPositionCd(orig.getUpdatedPositionCd());
		dest.setUpdatedDate(orig.getUpdatedDate());
		dest.setRecordVersion(orig.getRecordVersion());
		dest.setConnFax(orig.getConnFax());
		dest.setConnAccountNo(orig.getConnAccountNo());
		dest.setSquare(orig.getSquare());
		dest.setInnerSquare(orig.getInnerSquare());
		dest.setRentSquare(orig.getRentSquare());
		dest.setEquityNature(orig.getEquityNature());
		dest.setLayoutCd(orig.getLayoutCd());
		dest.setRentYears(orig.getRentYears());
		dest.setManageCd(orig.getManageCd());
		dest.setOwner(orig.getOwner());
		dest.setContApproval(orig.getContApproval());
		dest.setContApprovalId(orig.getContApprovalId());
		dest.setApprovalAttachFlg(orig.getApprovalAttachFlg());
	}

	/**
	 * Copy SubCont to BisContHis
	 */
	private static void copySubContToHis(BisCont orig, BisContHis dest) {
		String bigType = orig.getContBigTypeCd();
		String smallType = orig.getContSmallTypeCd();
		if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.RENT)) {
			copyShopRentToHis(orig.getBisContShopRents().get(0), dest);
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.BACK)) {
			copyShopBackToHis(orig.getBisContShopBacks().get(0), dest);
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.PROP)) {
			copyShopPropToHis(orig.getBisContShopProps().get(0), dest);
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.ENTR)) {
			copyShopEntrToHis(orig.getBisContShopEntrs().get(0), dest);
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.OWNER_PROP)) {
			copyShopPropToHis(orig.getBisContShopProps().get(0), dest);
		} else if(StringUtils.equals(bigType, BisConstants.FLAT)) {
			copyFlatPropToHis(orig.getBisContFlatProps().get(0), dest);
		} else if(StringUtils.equals(bigType, BisConstants.MULTI)) {
			copyMultiToHis(orig.getBisContMultis().get(0), dest);
		}
	}
	
	/**
	 * Copy BisContHis to BisCont
	 */
	private static void copyHisToMainCont(BisContHis orig, BisCont dest) {
		dest.setBisContId(orig.getBisContId());
		dest.setContNo(orig.getContNo());
		dest.setBisTenantId(orig.getBisTenantId());
		dest.setBisProjectId(orig.getBisProjectId());
		dest.setContName(orig.getContName());
		dest.setBisPeople(orig.getBisPeople());
		dest.setBrand(orig.getBrand());
		dest.setPartyA(orig.getPartyA());
		dest.setPartyB(orig.getPartyB());
		dest.setPartyC(orig.getPartyC());
		dest.setPartyD(orig.getPartyD());
		dest.setConnAddr(orig.getConnAddr());
		dest.setConnTel(orig.getConnTel());
		dest.setConnPeople(orig.getConnPeople());
		dest.setBuildingName(orig.getBuildingName());
		dest.setRentDate(orig.getRentDate());
		dest.setContStartDate(orig.getContStartDate());
		dest.setContEndDate(orig.getContEndDate());
		dest.setCoopWayCd(orig.getCoopWayCd());
		dest.setContBigTypeCd(orig.getContBigTypeCd());
		dest.setContSmallTypeCd(orig.getContSmallTypeCd());
		dest.setContMoney(orig.getContMoney());
		dest.setSignDate(orig.getSignDate());
		dest.setContContent(orig.getContContent());
		dest.setCheckUserCd(orig.getCheckUserCd());
		dest.setCheckDate(orig.getCheckDate());
		dest.setAttachFlg(orig.getAttachFlg());//
		dest.setActiveBl(orig.isActiveBl());//
		dest.setStatusCd(orig.getStatusCd());
		dest.setContToFailDate(orig.getContToFailDate());
		dest.setChargerTypeArr(orig.getChargerTypeArr());
		dest.setRemark(orig.getRemark());
		dest.setCreator(orig.getCreator());
		dest.setCreatedCenterCd(orig.getCreatedCenterCd());
		dest.setCreatedDeptCd(orig.getCreatedDeptCd());
		dest.setCreatedPositionCd(orig.getCreatedPositionCd());
		dest.setCreatedDate(orig.getCreatedDate());
		dest.setUpdator(orig.getUpdator());
		dest.setUpdatedCenterCd(orig.getUpdatedCenterCd());
		dest.setUpdatedDeptCd(orig.getUpdatedDeptCd());
		dest.setUpdatedPositionCd(orig.getUpdatedPositionCd());
		dest.setUpdatedDate(orig.getUpdatedDate());
		dest.setRecordVersion(orig.getRecordVersion());
		dest.setConnFax(orig.getConnFax());
		dest.setConnAccountNo(orig.getConnAccountNo());
		dest.setSquare(orig.getSquare());
		dest.setInnerSquare(orig.getInnerSquare());
		dest.setRentSquare(orig.getRentSquare());
		dest.setEquityNature(orig.getEquityNature());
		dest.setLayoutCd(orig.getLayoutCd());
		dest.setRentYears(orig.getRentYears());
		dest.setManageCd(orig.getManageCd());
		dest.setOwner(orig.getOwner());
		dest.setChangeUser(orig.getChangeUser());
		dest.setChangeDate(orig.getChangeDate());
		dest.setTempHisId(orig.getBisContHisId());
		dest.setContApproval(orig.getContApproval());
		dest.setContApprovalId(orig.getContApprovalId());
		dest.setApprovalAttachFlg(orig.getApprovalAttachFlg());
	}
	
	/**
	 * Copy BisContHis to SubCont
	 */
	private static void copyHisToSubCont(BisContHis orig, BisCont dest) {
		String bigType = orig.getContBigTypeCd();
		String smallType = orig.getContSmallTypeCd();
		if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.RENT)) {
			if(dest.getBisContShopRents().size() == 0) {
				BisContShopRent subDest = new BisContShopRent();
				copyHisToShopRent(orig, subDest);
				subDest.setBisCont(dest);
				dest.getBisContShopRents().add(subDest);
			} else {
				copyHisToShopRent(orig, dest.getBisContShopRents().get(0));
			}
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.BACK)) {
			if(dest.getBisContShopBacks().size() == 0) {
				BisContShopBack subDest = new BisContShopBack();
				copyHisToShopBack(orig, subDest);
				subDest.setBisCont(dest);
				dest.getBisContShopBacks().add(subDest);
			} else {
				copyHisToShopBack(orig, dest.getBisContShopBacks().get(0));
			}
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.PROP)) {
			if(dest.getBisContShopProps().size() == 0) {
				BisContShopProp subDest = new BisContShopProp();
				copyHisToShopProp(orig, subDest);
				subDest.setBisCont(dest);
				dest.getBisContShopProps().add(subDest);
			} else {
				copyHisToShopProp(orig, dest.getBisContShopProps().get(0));
			}
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.ENTR)) {
			if(dest.getBisContShopEntrs().size() == 0) {
				BisContShopEntr subDest = new BisContShopEntr();
				copyHisToShopEntr(orig, subDest);
				subDest.setBisCont(dest);
				dest.getBisContShopEntrs().add(subDest);
			} else {
				copyHisToShopEntr(orig, dest.getBisContShopEntrs().get(0));
			}
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.OWNER_PROP)) {
			if(dest.getBisContShopProps().size() == 0) {
				BisContShopProp subDest = new BisContShopProp();
				copyHisToShopProp(orig, subDest);
				subDest.setBisCont(dest);
				dest.getBisContShopProps().add(subDest);
			} else {
				copyHisToShopProp(orig, dest.getBisContShopProps().get(0));
			}
		} else if(StringUtils.equals(bigType, BisConstants.FLAT)) {
			if(dest.getBisContFlatProps().size() == 0) {
				BisContFlatProp subDest = new BisContFlatProp();
				copyHisToFlatProp(orig, subDest);
				subDest.setBisCont(dest);
				dest.getBisContFlatProps().add(subDest);
			} else {
				copyHisToFlatProp(orig, dest.getBisContFlatProps().get(0));
			}
		} else if(StringUtils.equals(bigType, BisConstants.MULTI)) {
			if(dest.getBisContMultis().size() == 0) {
				BisContMulti subDest = new BisContMulti();
				copyHisToMulti(orig, subDest);
				subDest.setBisCont(dest);
				dest.getBisContMultis().add(subDest);
			} else {
				copyHisToMulti(orig, dest.getBisContMultis().get(0));
			}
		}
	}
	
	private static void copyShopRentToHis(BisContShopRent orig, BisContHis dest) {
		dest.setStoreFlatNos(orig.getStoreNo());
//		dest.setShopNameCn(orig.getShopNameCn());
		dest.setFreeRentPeriod(orig.getFreeRentPeriod());
//		dest.setLayoutCd(orig.getLayoutCd());
		dest.setPaymentModeCd(orig.getPaymentModeCd());
		dest.setPayCycleCd(orig.getPayCycleCd());
		dest.setEarnestMoney(orig.getEarnestMoney());
//		dest.setRentYears(orig.getRentYears());
		dest.setSignTime(orig.getSignTime());
		dest.setRentUnit1(orig.getRentUnit1());
		dest.setRentUnit2(orig.getRentUnit2());
		dest.setRentUnit3(orig.getRentUnit3());
		dest.setRentUnit4(orig.getRentUnit4());
		dest.setRentUnit5(orig.getRentUnit5());
		dest.setRentUnit6(orig.getRentUnit6());
		dest.setRentUnit7(orig.getRentUnit7());
		dest.setRentUnit8(orig.getRentUnit8());
		dest.setRentUnit9(orig.getRentUnit9());
		dest.setRentUnit10(orig.getRentUnit10());
		dest.setRentMonth1(orig.getRentMonth1());
		dest.setRentMonth2(orig.getRentMonth2());
		dest.setRentMonth3(orig.getRentMonth3());
		dest.setRentMonth4(orig.getRentMonth4());
		dest.setRentMonth5(orig.getRentMonth5());
		dest.setRentMonth6(orig.getRentMonth6());
		dest.setRentMonth7(orig.getRentMonth7());
		dest.setRentMonth8(orig.getRentMonth8());
		dest.setRentMonth9(orig.getRentMonth9());
		dest.setRentMonth10(orig.getRentMonth10());
		dest.setPayWay(orig.getPayWay());
		dest.setPayMonth(orig.getPayMonth());
		dest.setPayDay(orig.getPayDay());
		dest.setMoneyScale(orig.getMoneyScale());
	}
	
	private static void copyHisToShopRent(BisContHis orig, BisContShopRent dest) {
		dest.setStoreNo(orig.getStoreFlatNos());
//		dest.setShopNameCn(orig.getShopNameCn());
		dest.setFreeRentPeriod(orig.getFreeRentPeriod());
//		dest.setLayoutCd(orig.getLayoutCd());
		dest.setPaymentModeCd(orig.getPaymentModeCd());
		dest.setPayCycleCd(orig.getPayCycleCd());
		dest.setEarnestMoney(orig.getEarnestMoney());
//		dest.setRentYears(orig.getRentYears());
		dest.setSignTime(orig.getSignTime());
		dest.setRentUnit1(orig.getRentUnit1());
		dest.setRentUnit2(orig.getRentUnit2());
		dest.setRentUnit3(orig.getRentUnit3());
		dest.setRentUnit4(orig.getRentUnit4());
		dest.setRentUnit5(orig.getRentUnit5());
		dest.setRentUnit6(orig.getRentUnit6());
		dest.setRentUnit7(orig.getRentUnit7());
		dest.setRentUnit8(orig.getRentUnit8());
		dest.setRentUnit9(orig.getRentUnit9());
		dest.setRentUnit10(orig.getRentUnit10());
		dest.setRentMonth1(orig.getRentMonth1());
		dest.setRentMonth2(orig.getRentMonth2());
		dest.setRentMonth3(orig.getRentMonth3());
		dest.setRentMonth4(orig.getRentMonth4());
		dest.setRentMonth5(orig.getRentMonth5());
		dest.setRentMonth6(orig.getRentMonth6());
		dest.setRentMonth7(orig.getRentMonth7());
		dest.setRentMonth8(orig.getRentMonth8());
		dest.setRentMonth9(orig.getRentMonth9());
		dest.setRentMonth10(orig.getRentMonth10());
		dest.setPayWay(orig.getPayWay());
		dest.setPayMonth(orig.getPayMonth());
		dest.setPayDay(orig.getPayDay());
		dest.setMoneyScale(orig.getMoneyScale());
	}
	
	private static void copyShopBackToHis(BisContShopBack orig, BisContHis dest) {
		dest.setStoreFlatNos(orig.getStoreNo());
		dest.setBackRentYears(orig.getBackRentYears());
		dest.setPaymentModeCd(orig.getPaymentModeCd());
		dest.setContDeadline(orig.getContDeadline());
		dest.setBeEntrustedRenter(orig.getBeEntrustedRenter());
		dest.setPaymentTime(orig.getPaymentTime());
		dest.setOwner(orig.getOwner());
		dest.setSellPrice(orig.getSellPrice());
		dest.setFreeRentPeriod(orig.getFreeRentPeriod());
		dest.setRent1(orig.getRent1());
		dest.setRent2(orig.getRent2());
		dest.setRent3(orig.getRent3());
		dest.setRent4(orig.getRent4());
		dest.setRent5(orig.getRent5());
		dest.setRent6(orig.getRent6());
		dest.setRent7(orig.getRent7());
		dest.setRent8(orig.getRent8());
		dest.setRent9(orig.getRent9());
		dest.setRent10(orig.getRent10());
		dest.setRentRatio1(orig.getRentRatio1());
		dest.setRentRatio2(orig.getRentRatio2());
		dest.setRentRatio3(orig.getRentRatio3());
		dest.setRentRatio4(orig.getRentRatio4());
		dest.setRentRatio5(orig.getRentRatio5());
		dest.setRentRatio6(orig.getRentRatio6());
		dest.setRentRatio7(orig.getRentRatio7());
		dest.setRentRatio8(orig.getRentRatio8());
		dest.setRentRatio9(orig.getRentRatio9());
		dest.setRentRatio10(orig.getRentRatio10());
	}

	private static void copyHisToShopBack(BisContHis orig, BisContShopBack dest) {
		dest.setStoreNo(orig.getStoreFlatNos());
		dest.setBackRentYears(orig.getBackRentYears());
		dest.setPaymentModeCd(orig.getPaymentModeCd());
		dest.setContDeadline(orig.getContDeadline());
		dest.setBeEntrustedRenter(orig.getBeEntrustedRenter());
		dest.setPaymentTime(orig.getPaymentTime());
		dest.setOwner(orig.getOwner());
		dest.setSellPrice(orig.getSellPrice());
		dest.setFreeRentPeriod(orig.getFreeRentPeriod());
		dest.setRent1(orig.getRent1());
		dest.setRent2(orig.getRent2());
		dest.setRent3(orig.getRent3());
		dest.setRent4(orig.getRent4());
		dest.setRent5(orig.getRent5());
		dest.setRent6(orig.getRent6());
		dest.setRent7(orig.getRent7());
		dest.setRent8(orig.getRent8());
		dest.setRent9(orig.getRent9());
		dest.setRent10(orig.getRent10());
		dest.setRentRatio1(orig.getRentRatio1());
		dest.setRentRatio2(orig.getRentRatio2());
		dest.setRentRatio3(orig.getRentRatio3());
		dest.setRentRatio4(orig.getRentRatio4());
		dest.setRentRatio5(orig.getRentRatio5());
		dest.setRentRatio6(orig.getRentRatio6());
		dest.setRentRatio7(orig.getRentRatio7());
		dest.setRentRatio8(orig.getRentRatio8());
		dest.setRentRatio9(orig.getRentRatio9());
		dest.setRentRatio10(orig.getRentRatio10());
	}
	
	private static void copyShopPropToHis(BisContShopProp orig, BisContHis dest) {
		dest.setStoreFlatNos(orig.getStoreNo());
		dest.setPerformanceBond(orig.getPerformanceBond());
		dest.setOperationBond(orig.getOperationBond());
		dest.setIntegMoneyStandard(orig.getIntegMoneyStandard());
		dest.setIntegMoneyFirstRentDay(orig.getIntegMoneyFirstRentDay());
		dest.setIntegMoneyFreeRentPeriod(orig.getIntegMoneyFreeRentPeriod());
		dest.setContDeadline(orig.getContDeadline());
		dest.setPaymentModeCd(orig.getPaymentModeCd());
		dest.setPaymentTime(orig.getPaymentTime());
		dest.setManageFee1(orig.getManageFee1());
		dest.setManageFee2(orig.getManageFee2());
		dest.setManageFee3(orig.getManageFee3());
		dest.setManageFee4(orig.getManageFee4());
		dest.setManageFee5(orig.getManageFee5());
		dest.setManageFee6(orig.getManageFee6());
		dest.setManageFee7(orig.getManageFee7());
		dest.setManageFee8(orig.getManageFee8());
		dest.setManageFee9(orig.getManageFee9());
		dest.setManageFee10(orig.getManageFee10());
		dest.setInPoolBl(orig.getInPoolBl());
	}

	private static void copyHisToShopProp(BisContHis orig, BisContShopProp dest) {
		dest.setStoreNo(orig.getStoreFlatNos());
		dest.setPerformanceBond(orig.getPerformanceBond());
		dest.setOperationBond(orig.getOperationBond());
		dest.setIntegMoneyStandard(orig.getIntegMoneyStandard());
		dest.setIntegMoneyFirstRentDay(orig.getIntegMoneyFirstRentDay());
		dest.setIntegMoneyFreeRentPeriod(orig.getIntegMoneyFreeRentPeriod());
		dest.setContDeadline(orig.getContDeadline());
		dest.setPaymentModeCd(orig.getPaymentModeCd());
		dest.setPaymentTime(orig.getPaymentTime());
		dest.setManageFee1(orig.getManageFee1());
		dest.setManageFee2(orig.getManageFee2());
		dest.setManageFee3(orig.getManageFee3());
		dest.setManageFee4(orig.getManageFee4());
		dest.setManageFee5(orig.getManageFee5());
		dest.setManageFee6(orig.getManageFee6());
		dest.setManageFee7(orig.getManageFee7());
		dest.setManageFee8(orig.getManageFee8());
		dest.setManageFee9(orig.getManageFee9());
		dest.setManageFee10(orig.getManageFee10());
		dest.setInPoolBl(orig.getInPoolBl());
	}
	
	private static void copyShopEntrToHis(BisContShopEntr orig, BisContHis dest) {
		dest.setStoreFlatNos(orig.getStoreNo());
		dest.setEntrRentYears(orig.getEntrRentYears());
		dest.setPaymentModeCd(orig.getPaymentModeCd());
		dest.setContDeadline(orig.getContDeadline());
		dest.setBeEntrRenter(orig.getBeEntrRenter());
		dest.setPaymentTime(orig.getPaymentTime());
		dest.setEarnestMoney(orig.getEarnestMoney());
		dest.setFreeRentPeriod(orig.getFreeRentPeriod());
		dest.setRent1(orig.getRent1());
		dest.setRent2(orig.getRent2());
		dest.setRent3(orig.getRent3());
		dest.setRent4(orig.getRent4());
		dest.setRent5(orig.getRent5());
		dest.setRent6(orig.getRent6());
		dest.setRent7(orig.getRent7());
		dest.setRent8(orig.getRent8());
		dest.setRent9(orig.getRent9());
		dest.setRent10(orig.getRent10());
		dest.setRentRatio1(orig.getRentRatio1());
		dest.setRentRatio2(orig.getRentRatio2());
		dest.setRentRatio3(orig.getRentRatio3());
		dest.setRentRatio4(orig.getRentRatio4());
		dest.setRentRatio5(orig.getRentRatio5());
		dest.setRentRatio6(orig.getRentRatio6());
		dest.setRentRatio7(orig.getRentRatio7());
		dest.setRentRatio8(orig.getRentRatio8());
		dest.setRentRatio9(orig.getRentRatio9());
		dest.setRentRatio10(orig.getRentRatio10());

	}

	private static void copyHisToShopEntr(BisContHis orig, BisContShopEntr dest) {
		dest.setStoreNo(orig.getStoreFlatNos());
		dest.setEntrRentYears(orig.getEntrRentYears());
		dest.setPaymentModeCd(orig.getPaymentModeCd());
		dest.setContDeadline(orig.getContDeadline());
		dest.setBeEntrRenter(orig.getBeEntrRenter());
		dest.setPaymentTime(orig.getPaymentTime());
		dest.setEarnestMoney(orig.getEarnestMoney());
		dest.setFreeRentPeriod(orig.getFreeRentPeriod());
		dest.setRent1(orig.getRent1());
		dest.setRent2(orig.getRent2());
		dest.setRent3(orig.getRent3());
		dest.setRent4(orig.getRent4());
		dest.setRent5(orig.getRent5());
		dest.setRent6(orig.getRent6());
		dest.setRent7(orig.getRent7());
		dest.setRent8(orig.getRent8());
		dest.setRent9(orig.getRent9());
		dest.setRent10(orig.getRent10());
		dest.setRentRatio1(orig.getRentRatio1());
		dest.setRentRatio2(orig.getRentRatio2());
		dest.setRentRatio3(orig.getRentRatio3());
		dest.setRentRatio4(orig.getRentRatio4());
		dest.setRentRatio5(orig.getRentRatio5());
		dest.setRentRatio6(orig.getRentRatio6());
		dest.setRentRatio7(orig.getRentRatio7());
		dest.setRentRatio8(orig.getRentRatio8());
		dest.setRentRatio9(orig.getRentRatio9());
		dest.setRentRatio10(orig.getRentRatio10());

	}
	
	private static void copyFlatPropToHis(BisContFlatProp orig, BisContHis dest) {
		dest.setStoreFlatNos(orig.getFlatNo());
		dest.setOwner(orig.getOwner());
		dest.setIdCardNo(orig.getIdCardNo());
		dest.setHousingTypeCd(orig.getHousingTypeCd());
		dest.setFirstCycleMustRent(orig.getFirstCycleMustRent());
		dest.setPropertyDerateMoney(orig.getPropertyDerateMoney());
		dest.setIntegMoneyStandard(orig.getIntegMoneyStandard());
		dest.setIntegMoneyFreeRentPeriod(orig.getIntegMoneyFreeRentPeriod());
		dest.setIntegMoneyFirstRentDay(orig.getIntegMoneyFirstRentDay());
		dest.setPaymentModeCd(orig.getPaymentModeCd());
		dest.setContDeadline(orig.getContDeadline());
		dest.setPaymentTime(orig.getPaymentTime());
		dest.setManageFee1(orig.getManageFee1());
		dest.setManageFee2(orig.getManageFee2());
		dest.setManageFee3(orig.getManageFee3());
		dest.setManageFee4(orig.getManageFee4());
		dest.setManageFee5(orig.getManageFee5());
		dest.setManageFee6(orig.getManageFee6());
		dest.setManageFee7(orig.getManageFee7());
		dest.setManageFee8(orig.getManageFee8());
		dest.setManageFee9(orig.getManageFee9());
		dest.setManageFee10(orig.getManageFee10());
	}

	private static void copyHisToFlatProp(BisContHis orig, BisContFlatProp dest) {
		dest.setFlatNo(orig.getStoreFlatNos());
		dest.setOwner(orig.getOwner());
		dest.setIdCardNo(orig.getIdCardNo());
		dest.setHousingTypeCd(orig.getHousingTypeCd());
		dest.setFirstCycleMustRent(orig.getFirstCycleMustRent());
		dest.setPropertyDerateMoney(orig.getPropertyDerateMoney());
		dest.setIntegMoneyStandard(orig.getIntegMoneyStandard());
		dest.setIntegMoneyFreeRentPeriod(orig.getIntegMoneyFreeRentPeriod());
		dest.setIntegMoneyFirstRentDay(orig.getIntegMoneyFirstRentDay());
		dest.setPaymentModeCd(orig.getPaymentModeCd());
		dest.setContDeadline(orig.getContDeadline());
		dest.setPaymentTime(orig.getPaymentTime());
		dest.setManageFee1(orig.getManageFee1());
		dest.setManageFee2(orig.getManageFee2());
		dest.setManageFee3(orig.getManageFee3());
		dest.setManageFee4(orig.getManageFee4());
		dest.setManageFee5(orig.getManageFee5());
		dest.setManageFee6(orig.getManageFee6());
		dest.setManageFee7(orig.getManageFee7());
		dest.setManageFee8(orig.getManageFee8());
		dest.setManageFee9(orig.getManageFee9());
		dest.setManageFee10(orig.getManageFee10());
	}
	
	private static void copyMultiToHis(BisContMulti orig, BisContHis dest) {
//		dest.setStoreFlatNos(orig.getStoreNo());
		dest.setPaymentTime(orig.getPaymentTime());
		dest.setPaymentModeCd(orig.getPaymentModeCd());
		dest.setRenterName(orig.getRenterName());
		dest.setOperationProjectCd(orig.getOperationProjectCd());
		dest.setOperationArea(orig.getOperationArea());
		dest.setContPromiseBond(orig.getContPromiseBond());
		dest.setReceivedBond(orig.getReceivedBond());
	}

	private static void copyHisToMulti(BisContHis orig, BisContMulti dest) {
//		dest.setFlatNo(orig.getStoreFlatNos());
		dest.setPaymentTime(orig.getPaymentTime());
		dest.setPaymentModeCd(orig.getPaymentModeCd());
		dest.setRenterName(orig.getRenterName());
		dest.setOperationProjectCd(orig.getOperationProjectCd());
		dest.setOperationArea(orig.getOperationArea());
		dest.setContPromiseBond(orig.getContPromiseBond());
		dest.setReceivedBond(orig.getReceivedBond());
	}
	
	private static void copyFormulaToHis(BisMustFormula orig, BisContHis dest) {
		dest.setChargeTypeCd(orig.getChargeTypeCd());
		dest.setFormula(orig.getFormula());
		dest.setUnitPrice(orig.getUnitPrice());
		dest.setFormulaSquare(orig.getFormulaSquare());
		dest.setCountPrice(orig.getCountPrice());
		dest.setTotalPrice(orig.getTotalPrice());
		dest.setPayDay(orig.getPayDay());
	}
	
	private static void copyHisToFormula(BisContHis orig, BisMustFormula dest) {
		dest.setChargeTypeCd(orig.getChargeTypeCd());
		dest.setFormula(orig.getFormula());
		dest.setUnitPrice(orig.getUnitPrice());
		dest.setFormulaSquare(orig.getFormulaSquare());
		dest.setCountPrice(orig.getCountPrice());
		dest.setTotalPrice(orig.getTotalPrice());
		dest.setPayDay(orig.getPayDay());
	}
}  