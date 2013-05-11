package com.hhz.ump.dao.cost;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.cost.CostStrageMate;
import com.hhz.ump.entity.cost.CostStrageMateDetail;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.web.res.bean.StrategyMatePrice;
import com.hhz.ump.web.res.bean.StrategyOnlineOrder;

@Service
@Transactional
public class CostStrageMateDetailManager extends BaseService<CostStrageMateDetail, String> {
	@Autowired
	private CostStrageMateDao costStrageMateDao;
	@Autowired
	private CostStrageMateDetailDao costStrageMateDetailDao;

	public void saveCostStrageMateDetail(CostStrageMateDetail costStrageMateDetail) {
		PowerUtils.setEmptyStr2Null(costStrageMateDetail);
		costStrageMateDetailDao.save(costStrageMateDetail);
	}

	public void deleteCostStrageMateDetail(String id) {
		costStrageMateDetailDao.delete(id);
	}
	
	@Override
	public HibernateDao<CostStrageMateDetail, String> getDao() {
		return costStrageMateDetailDao;
	}
	
	/**
	 * @param bean
	 */
	public void importStrageBill(StrategyOnlineOrder bean,String projectCd){

		if(StringUtils.isNotBlank(projectCd)){
			bean.setProjectCd(projectCd);
		}

		importstrageBill(bean, bean.getContractNo(), bean.getContractPrice());
		
		importstrageBill(bean, bean.getContractNo2(), bean.getContractPrice2());
	}
	private void importstrageBill(StrategyOnlineOrder bean, String contractNo, String contAmt){
		
		if(StringUtils.isBlank(contractNo))
			return;

		//导入战略采购库
		CostStrageMate mate = new CostStrageMate();
		
//		strageMate.setTitle(titleName);
//		strageMate.setProjectName(projectName);
//		strageMate.setContName(contactName);
//		strageMate.setContStrageName(contactName);
//		strageMate.setRealProvName(partBReal);
//		if (StringUtils.isNotBlank(stargeFlg1) && "true".equals(stargeFlg1)) {
//			strageMate.setStrageFlg("1");
//		}
//		if (StringUtils.isNotBlank(stargeFlg2) && "true".equals(stargeFlg2)) {
//			strageMate.setStrageFlg("0");
//		}
//		strageMate.setBuyCycleDesc(purchasePreiod);
//		//调整量
//		if (StringUtils.isNotBlank(budgetAdjustNum)) {
//			mate.setBudgetAdjustNum(new BigDecimal(budgetAdjustNum));
//		}
//		mate.setProvTradeContAttachId(supplyTradeFileId);
//		mate.setTradeProjContAttachId(tradeProjectFileId);
		

		mate.setProjectName(bean.getProjectName());
		
		mate.setParta(bean.getPartA());
		mate.setPartb(bean.getPartB());
		
		mate.setTakeUnitId(bean.getTakeUnitId());
		mate.setTakeUnitName(bean.getTakeUnitName());
		mate.setTakeUnitContNo(bean.getTakeUnitContNo());
		
		mate.setCostMateId(bean.getMaterialId());
		mate.setMateName(bean.getMaterialName());
		
		mate.setUseAeraDesc(bean.getUseCoverage());
		mate.setOutStrageAttachId(bean.getBeyondStraAmtId());
		mate.setEnterDate(bean.getEnterDate());
		
		//采购总价
		String totalAmt = bean.getPurchaseTotalAmt();
		if (StringUtils.isNotBlank(totalAmt)) {
			totalAmt = totalAmt.replace(",", "");
			mate.setBuyTotalAmt(new BigDecimal(totalAmt));
		}

		//战略内总价 
		String inAmt = bean.getPurchaseStrategyTotalAmt();
		if (StringUtils.isNotBlank(inAmt)) {
			inAmt = inAmt.replace(",", "");			
			mate.setStrageTotalAmt(new BigDecimal(inAmt));
		}
		
		//战略外
		String outAmt = bean.getStrategyOutTotalAmt();
		if (StringUtils.isNotBlank(outAmt)) {
			outAmt = outAmt.replace(",", "");
			mate.setOutStragePrice(new BigDecimal(outAmt));
		}
		
		//本期累计使用总量
		String toThisNum = bean.getToThisTotalNum();
		if (StringUtils.isNotBlank(toThisNum)) {
			mate.setToThisTotalNum(new BigDecimal(toThisNum.trim()));
		}
		
		//总量
		String totalNum = bean.getContTotalNum();
		if (StringUtils.isNotBlank(totalNum)) {
			mate.setContTotalNum(new BigDecimal(totalNum));
		}
		
		//订单说明
		mate.setOrderDesc(bean.getOrderDesc());
		
		//参数附件
		mate.setPurchaseTechConfirmFileId(bean.getPurchaseTechConfirmFileId());
		
		//列头
		mate.setHeadNames(bean.getHeadNames());

		//合同编号
		mate.setContNo(contractNo);
		
		//总价浮动比例  -add by liuzhihui 2012-05-14
		String totalFloatRate = bean.getTotalFloatRate();
		if(StringUtils.isNotBlank(totalFloatRate) && (!"0".equals(totalFloatRate))){
			BigDecimal tmpTotalFloatRate = BigDecimal.valueOf(Double.valueOf(totalFloatRate));
			mate.setTotalFloatRate(tmpTotalFloatRate.multiply(new BigDecimal(100)));
		}
		
		//很重要：网批ID
		ResApproveInfo info = bean.getResApproveInfo();
		if(info!= null){
			mate.setResApproveInfoId(info.getResApproveInfoId());
		}

		//保存主表
		PowerUtils.setEmptyStr2Null(mate);
		costStrageMateDao.save(mate);
		
		
		//导入战略采购库明细
		List<StrategyMatePrice> tmpList = bean.getStrategyMatePrices();
		if (tmpList != null && tmpList.size() > 0) {
			
			//临时变量
			CostStrageMateDetail tDetail = null;
			
			//需要保存的明细
			for (StrategyMatePrice tPrice: tmpList) {
				
				tDetail = new CostStrageMateDetail();
				
				//父表
				tDetail.setCostStrageMate(mate);
				
				//很重要:关联价格表ID
				if(StringUtils.isNotBlank(tPrice.getCostMatePriceId())){
					tDetail.setCostMatePriceId(tPrice.getCostMatePriceId());
				}
				//规格
				tDetail.setModelName(tPrice.getModelName());
				//型号
				tDetail.setSpecName(tPrice.getSpecName());
				//价格计算方式
				tDetail.setCalcTypeCd(tPrice.getCalcTypeCd());
				
				BigDecimal price = new BigDecimal(0);
				BigDecimal number = new BigDecimal(0);
				if (StringUtils.isNotBlank(tPrice.getPrice()) && !"0".equals(tPrice.getPrice())) {
					price = BigDecimal.valueOf(Double.valueOf(tPrice.getPrice()));
				}
				if (StringUtils.isNotBlank(tPrice.getBuyNum()) && !"0".equals(tPrice.getBuyNum())) {
					number = BigDecimal.valueOf(Double.valueOf(tPrice.getBuyNum()));
				}
				//单价
				tDetail.setPrice(price);
				//数量
				tDetail.setOrderNum(number);
				
				//自定义咧
				tDetail.setF01(tPrice.getF01());
				tDetail.setF02(tPrice.getF02());
				tDetail.setF02(tPrice.getF03());
				tDetail.setF04(tPrice.getF04());
				tDetail.setF05(tPrice.getF05());
				tDetail.setF06(tPrice.getF06());
				tDetail.setF07(tPrice.getF07());
				tDetail.setF08(tPrice.getF08());
				tDetail.setF09(tPrice.getF09());
				tDetail.setF10(tPrice.getF10());
				tDetail.setF11(tPrice.getF11());
				tDetail.setF12(tPrice.getF12());
				tDetail.setF13(tPrice.getF13());
				tDetail.setF14(tPrice.getF14());
				tDetail.setF15(tPrice.getF15());
				
				//保存明细
				saveCostStrageMateDetail(tDetail);
			}
		}
	}
}

