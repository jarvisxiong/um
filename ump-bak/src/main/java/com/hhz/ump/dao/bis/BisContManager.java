package com.hhz.ump.dao.bis;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.core.utils.RandomUtils;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.entity.app.AppAttachFile;
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
import com.hhz.ump.entity.bis.BisFact;
import com.hhz.ump.entity.bis.BisFlat;
import com.hhz.ump.entity.bis.BisFlatContRel;
import com.hhz.ump.entity.bis.BisMulti;
import com.hhz.ump.entity.bis.BisMultiContRel;
import com.hhz.ump.entity.bis.BisMust;
import com.hhz.ump.entity.bis.BisMustFormula;
import com.hhz.ump.entity.bis.BisMustRent;
import com.hhz.ump.entity.bis.BisMustRentHis;
import com.hhz.ump.entity.bis.BisStore;
import com.hhz.ump.entity.bis.BisStoreContRel;
import com.hhz.ump.entity.bis.BisTenant;
import com.hhz.ump.util.BisBeanUtils;
import com.hhz.ump.util.BisConstants;
import com.hhz.ump.util.DictContants;

@Service
@Transactional
public class BisContManager extends BaseService<BisCont, String> {
	
	private static Log log = LogFactory.getLog(BisProjectOverviewManager.class);
	
	@Autowired
	private BisContDao bisContDao;
	
	@Autowired
	private BisTenantManager bisTenantManager;
	
	@Autowired
	private BisShopManager bisShopManager;
	
	@Autowired
	private BisFlatManager bisFlatManager;

	@Autowired
	private BisFlatContRelManager bisFlatContRelManager;
	
	@Autowired
	private BisStoreManager bisStoreManager;
	
	@Autowired
	private BisStoreContRelManager bisStoreContRelManager;
	
	@Autowired
	private BisMultiManager bisMultiManager;
	
	@Autowired
	private BisMultiContRelManager bisMultiContRelManager;
	
	@Autowired
	private BisMustManager bisMustManager;
	
	@Autowired
	private BisFactManager bisFactManager;
	
	@Autowired
	private BisContShopRentManager bisContShopRentManager;
	
	@Autowired
	private BisContShopBackManager bisContShopBackManager;
	
	@Autowired
	private BisContShopPropManager bisContShopPropManager;
	
	@Autowired
	private BisContShopEntrManager bisContShopEntrManager;
	
	@Autowired
	private BisContFlatPropManager bisContFlatPropManager;
	
	@Autowired
	private BisContCreditManager bisContCreditManager;
	
	@Autowired
	private BisContAdditionalManager bisContAdditionalManager;
	
	@Autowired
	private BisContMultiManager bisContMultiManager;
	
	@Autowired
	private BisContHisManager bisContHisManager;
	
	@Autowired
	private BisContCreditHisManager bisContCreditHisManager;
	
	@Autowired
	private BisContAddiHisManager bisContAddiHisManager;
	
	@Autowired
	private BisMustRentHisManager bisMustRentHisManager;
	
	@Autowired
	private BisMustFormulaManager bisMustFormulaManager;
	
	@Autowired
	private BisMustRentManager bisMustRentManager;
	
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	
	@Autowired
	private AppDictTypeManager appDictTypeManager;

	/**
	 * 保存合同主表
	 */
	public void saveBisCont(BisCont bisCont) {
		PowerUtils.setEmptyStr2Null(bisCont);
		bisCont.setTempHisId(null);
		bisContDao.save(bisCont);
	}
	
	/**
	 * 删除合同
	 */
	@Transactional
	public void deleteBisCont(String id) {
		
		@SuppressWarnings("deprecation")
		Connection conn = getDao().getSession().connection();
		try {
			log.info("===>>"+id);
			CallableStatement callableStatement = conn.prepareCall("{ call BIS_CONT_DELETE(?)}");
			callableStatement.setString(1, id);
			callableStatement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public HibernateDao<BisCont, String> getDao() {
		return bisContDao;
	}
	
	/**
	 * 保存合同
	 */
	@Transactional
	public String save(BisCont entity, String entityTmpId, String bisShopId, String bisShopConnId, String bisStoreIds, String bisFlatIds, String bisMultiIds) {
		
		if(StringUtils.isBlank(entity.getBisContId())) {
			String existContId = containsConts(entity, bisStoreIds, bisFlatIds, bisMultiIds, bisShopId, bisShopConnId);
			if(StringUtils.isNotEmpty(existContId))
				throw new RuntimeException("conflict:"+existContId);
			
//			String contNo = generateContNo(entity.getBisProjectId(), entity.getContSmallTypeCd());
//			entity.setContNo(contNo);
			
			entity.setActiveBl(true);
			if(StringUtils.isBlank(entity.getStatusCd())) {
				entity.setStatusCd("1");
			}
			//保存合同表
			saveBisCont(entity);
			
			if(StringUtils.equals(entity.getContBigTypeCd(), BisConstants.STORE)) { //商铺合同
				saveStoreCont(entity, bisShopId, bisShopConnId, bisStoreIds);
			} else if(StringUtils.equals(entity.getContBigTypeCd(), BisConstants.FLAT)) { //公寓合同
				saveFlatCont(entity, bisFlatIds);
			} else if(StringUtils.equals(entity.getContBigTypeCd(), BisConstants.MULTI)) { //多经合同
				saveMultiCont(entity, bisMultiIds);
			} else
				throw new RuntimeException("contBigTypeCd ERROR");
			
			//保存副表
			saveSubCont(entity);
			
			//保存资信资料
			for(BisContCredit bisContCredit : entity.getBisContCredits()) {
				if(bisContCredit == null) {
					continue;
				}
				bisContCredit.setBisCont(entity);
				bisContCreditManager.save(bisContCredit);
			}
			
			//保存补充合同表
			for(BisContAdditional bisContAdditional : entity.getBisContAdditionals()) {
				if(bisContAdditional == null) {
					continue;
				}
				bisContAdditional.setBisCont(entity);
				bisContAdditionalManager.save(bisContAdditional);
			}
			
			//更新附件
			if (StringUtils.isNotEmpty(entityTmpId)) {
				appAttachFileManager.updateTmpFile(entityTmpId, BisCont.class.getSimpleName(), entity.getBisContId());
			}
			
			//保存应收生成公式
			saveMustFormula(entity);
			
			//保存租金相关
			saveMustRent(entity);
			
		} else {
			String existContId = containsConts(entity, bisStoreIds, bisFlatIds, bisMultiIds, bisShopId, bisShopConnId);
			if(StringUtils.isNotEmpty(existContId))
				throw new RuntimeException("conflict:"+existContId);
			
			if(StringUtils.equals(entity.getContBigTypeCd(), BisConstants.STORE)) { //商铺合同
				updateStoreCont(entity, bisShopId, bisShopConnId, bisStoreIds);
			} else if(StringUtils.equals(entity.getContBigTypeCd(), BisConstants.FLAT)) { //公寓合同
				updateFlatCont(entity, bisFlatIds);
			} else if(StringUtils.equals(entity.getContBigTypeCd(), BisConstants.MULTI)) { //多经合同
				updateMultiCont(entity, bisMultiIds);
			} else
				throw new RuntimeException("contBigTypeCd ERROR");
			
			//保存合同表
			saveBisCont(entity);
			
			//保存副表
			saveSubCont(entity);
			
			//更新资信资料表
			updateCredits(entity);
			
			//更新补充合同表
			updateAdditinals(entity);
			
			//保存应收生成公式
			saveMustFormula(entity);
			
			//保存租金相关
			saveMustRent(entity);
			
		}
		return entity.getBisContId();
	}

	/**
	 * 网批导入
	 */
	public void doImport(BisCont bisCont, String attachFileId, String bisShopId, String bisShopConnId, String bisStoreIds) {
	
		String existContId = containsConts(bisCont, bisStoreIds, null, null, bisShopId, bisShopConnId);
		if(StringUtils.isNotEmpty(existContId))
			throw new RuntimeException("conflict:"+existContId);
		
		bisCont.setActiveBl(true);
//		if(StringUtils.isBlank(entity.getStatusCd())) {
//			entity.setStatusCd("1");
//		}
		
		//内部批文附件
		if(StringUtils.isNotBlank(bisCont.getContApprovalId())) {
			String contApprovalId = "bisContApproval_"+RandomUtils.generateTmpEntityId();
			copyAttachFile(contApprovalId, bisCont.getContApprovalId());
			bisCont.setContApprovalId(contApprovalId);
		}

		//保存合同表
		saveBisCont(bisCont);
		
		saveStoreCont(bisCont, bisShopId, bisShopConnId, bisStoreIds);
		
		//保存副表
		saveSubCont(bisCont);
		
		//保存资信资料
		for(BisContCredit bisContCredit : bisCont.getBisContCredits()) {
			if(bisContCredit == null) {
				continue;
			}
			bisContCredit.setBisCont(bisCont);
			String creditAttachFileId = bisContCredit.getEntityTmpId();
			if(StringUtils.isNotBlank(creditAttachFileId)) {
				
				bisContCredit.setEntityTmpId(null);
				bisContCredit.setAttachFlg("1");
			}
			bisContCreditManager.saveBisContCredit(bisContCredit);
			
			if(StringUtils.isNotBlank(creditAttachFileId)) {
				
				copyAttachFile(bisContCredit.getBisContCreditId(), creditAttachFileId);
			}
		}
		
		//保存补充合同表
		for(BisContAdditional bisContAdditional : bisCont.getBisContAdditionals()) {
			if(bisContAdditional == null) {
				continue;
			}
			bisContAdditional.setBisCont(bisCont);
			String addiAttachFileId = bisContAdditional.getEntityTmpId();
			if(StringUtils.isNotBlank(addiAttachFileId)) {
				
				bisContAdditional.setEntityTmpId(null);
				bisContAdditional.setAttachFlg("1");
			}
			bisContAdditionalManager.saveBisContAdditional(bisContAdditional);
			
			if(StringUtils.isNotBlank(addiAttachFileId)) {
				
				copyAttachFile(bisContAdditional.getBisContAdditionalId(), addiAttachFileId);
			}
		}
		
		//copy附件
		if(StringUtils.isNotBlank(attachFileId)) {
			
			copyAttachFile(bisCont.getBisContId(), attachFileId);
		}
		
	}

	private void copyAttachFile(String bizEntityId, String attachFileId) {
		if (StringUtils.isNotEmpty(attachFileId)) {
			AppAttachFile appAttachFile = appAttachFileManager.getEntity(attachFileId);
			AppAttachFile attachFile = new AppAttachFile();
			attachFile.setFileName(appAttachFile.getFileName());
			attachFile.setRealFileName(appAttachFile.getRealFileName());
			attachFile.setFilePath(appAttachFile.getFilePath());
			attachFile.setFileSize(appAttachFile.getFileSize());
			attachFile.setFileTypeName(appAttachFile.getFileTypeName());
			attachFile.setStatusCd(appAttachFile.getStatusCd());
			attachFile.setBizModuleCd(BisConstants.BIZ_MODULE_CD);
			attachFile.setBizEntityId(bizEntityId);
			appAttachFileManager.saveAppAttachFile(attachFile);
		}
	}
	
	/**
	 * 保存应收生成公式
	 */
	private void saveMustFormula(BisCont bisCont) {
		if(!bisCont.getBisMustFormulas().isEmpty()) {
			BisMustFormula bisMustFormula = bisCont.getBisMustFormulas().get(0);
			bisMustFormula.setBisCont(bisCont);
			bisMustFormulaManager.saveBisMustFormula(bisMustFormula);
		}
	}
	
	/**
	 * 保存自动生成租金相关
	 */
	private void saveMustRent(BisCont bisCont) {
		for(BisMustRent bisMustRent : bisCont.getBisMustRents()) {
			bisMustRent.setBisCont(bisCont);
			bisMustRentManager.saveBisMustRent(bisMustRent);
		}
	}

	/**
	 * 保存应收
	 */
	@Transactional
	public void saveMust(String bisContId, List<BisMust> bisMusts, String seleChargeType) {
		
		BisCont bisCont = getEntity(bisContId);
		
		containsConflictCharge(bisMusts, seleChargeType, bisCont.getBisContId());
		
		BisTenant bisTenant = null;
		if(StringUtils.isNotBlank(bisCont.getBisTenantId())) {
			bisTenant = bisTenantManager.getEntity(bisCont.getBisTenantId());
		}

		//删除应收款
		List<String> excludeIds = new ArrayList<String>();
		for(BisMust bisMust : bisMusts) {
			if(bisMust == null) {
				continue;
			}
			if(StringUtils.isNotEmpty(bisMust.getBisMustId())) {
				excludeIds.add(bisMust.getBisMustId());
			}
		}
		bisMustManager.deleteLeaveBatch(bisCont.getBisContId(), seleChargeType, excludeIds.toArray());
		//保存应收款
		for(BisMust bisMust : bisMusts) {
			if(bisMust == null) {
				continue;
			}
			
			if(StringUtils.isBlank(bisMust.getBisMustId())) {
				bisMust.setBisProjectId(bisCont.getBisProjectId());
				bisMust.setTypeCd("0");
				bisMust.setFloorType(bisCont.getContBigTypeCd());
			}
			if(StringUtils.isBlank(bisMust.getStatusCd())) {
				bisMust.setStatusCd("1"); //状态为新增
			} else if(StringUtils.equals(bisMust.getStatusCd(), "2")) {
				
				if(StringUtils.isBlank(bisMust.getMustYear())) {
					continue;
				}
				
				//已审核待计算如果修改则状态改为新增
				if(isMustModify(bisMust)) {
					bisMust.setStatusCd("1");
				} else {
					continue;
				}
				
			}
			
			//保存应收款时判断是否存在相关记录
			if(containsMust(bisMust, bisCont.getBisContId()))
				throw new RuntimeException(bisMust.getMustYear()+"年"+bisMust.getMustMonth()+"月<br>的应收款已存在");
			
			bisMust.setBisCont(bisCont);
			if(bisTenant != null) {
				bisMust.setBisTenantId(bisTenant.getBisTenantId());
				bisMust.setBisShopId(bisTenant.getBisShopId());
			}
			
			if(!bisCont.getBisMultiContRels().isEmpty()) {
				bisMust.setBisMultiId(bisCont.getBisMultiContRels().get(0).getBisMulti().getBisMultiId());
			}
			
			bisMust.setFloorType(bisCont.getContBigTypeCd()); //费用类型：商铺/公寓/多经
			//若存在实收数据，则将实收数据插入应收
			BigDecimal totFactMoney = new BigDecimal(0);
			List<BisFact> factList = new ArrayList<BisFact>();
			List<BisFact> bisFact1List = bisFactManager.getFact(bisMust.getBisProjectId(), bisMust.getBisTenantId(), null, null,
					bisMust.getChargeTypeCd(), bisMust.getMustYear(),bisMust.getMustMonth(),bisCont.getBisContId());
			for(BisFact fact:bisFact1List){
				totFactMoney = totFactMoney.add(fact.getMoney());
				fact.setTotalMustMoney(bisMust.getMoney());
				factList.add(fact);
			}
			if(totFactMoney.compareTo(new BigDecimal(0))!=0) {
				bisMust.setTotalFactMoney(totFactMoney);
			}
			bisMustManager.save(bisMust); //保存应收款表
			
			if(factList!=null&&factList.size()>0){
				bisFactManager.batchExecute(factList);
			}
		}

//		return String.valueOf(bisCont.getRecordVersion());
	}
	
	/**
	 * 待计算应收是否修改
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean isMustModify(BisMust bisMust) {
		
		StringBuffer hql = new StringBuffer("from BisMust where bisMustId=:bisMustId");
		if(StringUtils.isBlank(bisMust.getMustYear())) {
			hql.append(" and mustYear is null");
		} else {
			hql.append(" and mustYear=:mustYear");
		}
		if(StringUtils.isBlank(bisMust.getMustMonth())) {
			hql.append(" and mustMonth is null");
		} else {
			hql.append(" and mustMonth=:mustMonth");
		}
		if(bisMust.getMoney() == null) {
			hql.append(" and money is null");
		} else {
			hql.append(" and money=:money");
		}
		if(bisMust.getCollDate() == null) {
			hql.append(" and collDate is null");
		} else {
			hql.append(" and collDate=:collDate");
		}
		if(bisMust.getAttachFlg() == null) {
			hql.append(" and attachFlg is null");
		} else {
			hql.append(" and attachFlg=:attachFlg");
		}
		if(StringUtils.isBlank(bisMust.getDescribe())) {
			hql.append(" and describe is null");
		} else {
			hql.append(" and describe=:describe");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisMustId", bisMust.getBisMustId());
		param.put("mustYear", bisMust.getMustYear());
		param.put("mustMonth", bisMust.getMustMonth());
		param.put("money", bisMust.getMoney());
		param.put("collDate", bisMust.getCollDate());
		param.put("describe", bisMust.getDescribe());
		param.put("attachFlg", bisMust.getAttachFlg());
		List<BisMust> list = bisMustManager.find(hql.toString(), param);
		if(list.isEmpty())
			return true;
		else
			return false;
	}
	
	/**
	 * 判断物业费是否冲突(综合管理费=物业费+水电公摊+空调费)
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void containsConflictCharge(List<BisMust> bisMusts, String chargeType, String bisContId) {
		if(bisMusts.isEmpty())
			return;
		
		StringBuffer hql = new StringBuffer("from BisMust where bisCont.bisContId=? ");
		if(StringUtils.equals(chargeType, "3")) {
			hql.append(" and chargeTypeCd in ('4','7','8')");
		} else if(StringUtils.equals(chargeType, "4") || StringUtils.equals(chargeType, "7") || StringUtils.equals(chargeType, "8")) {
			hql.append(" and chargeTypeCd = '3'");
		} else
			return;
		List<BisMust> list = bisMustManager.find(hql.toString(), bisContId);
		if(list.size() > 0)
			throw new RuntimeException("费用类别冲突");
	}
	
	/**
	 * 审核合同
	 */
	@Transactional
	public String approveCont(String bisContId, String statusCd) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("update BisCont set statusCd=:statusCd ");
		param.put("statusCd", statusCd);
		hql.append(", checkDate=current_date(), checkUserCd=:userCd ");
		param.put("userCd", SpringSecurityUtils.getCurrentUiid());
		hql.append("where bisContId=:bisContId ");
		param.put("bisContId", bisContId);
		bisContDao.batchExecute(hql.toString(), param);
		
		autoIncreaseMust(bisContId);
//		if(StringUtils.equals(BisConstants.STATUS_APPROVED, statusCd)) {
//			bisContHisManager.activeContHis(bisContId);
//		}
		return "success";
	}
	
	/**
	 * 租赁合同自动生成应收
	 */
	private void autoIncreaseMust(String bisContId) {
		
		if(StringUtils.isBlank(bisContId))
			return;
		BisCont bisCont = getEntity(bisContId);
		if(!BisConstants.RENT.equals(bisCont.getContSmallTypeCd()))
			return;
		BisContShopRent rentEntity = bisCont.getBisContShopRents().get(0);
		
		Date contStartDate = bisCont.getContStartDate();
		int startYear = DateOperator.getYear(contStartDate);
		int startMonth = DateOperator.getMonth12(contStartDate);
		int startDay = DateOperator.getDay(contStartDate);
		int startDaysOfMonth = DateOperator.getDaysOfMonth(startYear, startMonth+1);
		Date contEndDate = bisCont.getContEndDate();
		int endYear = DateOperator.getYear(contEndDate);
		int endMonth = DateOperator.getMonth12(contEndDate);
		int endDay = DateOperator.getDay(contEndDate);
		int endDaysOfMonth = DateOperator.getDaysOfMonth(endYear, endMonth+1);
		
		String payCycle = rentEntity.getPayCycleCd(); //租金支付周期
		String payWay = rentEntity.getPayWay(); //月租金方式
		String payMonth = rentEntity.getPayMonth();
		String payDay = rentEntity.getPayDay(); //最晚缴款日
//		String moneyScale = rentEntity.getMoneyScale(); //精度
		int scale = 2;
//		if("2".equals(moneyScale)) {
//			scale = 0;
//		}
		
		if(StringUtils.isBlank(payCycle) || StringUtils.isBlank(payWay) || StringUtils.isBlank(payDay))
			return;
		
		Date mustDate = null;
		Date collDate = null;
		for(int i=0; i<bisCont.getBisMustRents().size(); i++) {
			BisMustRent mustRent = bisCont.getBisMustRents().get(i);
			BigDecimal rentMoney = mustRent.getTotalMoney();
			if(rentMoney==null || rentMoney.compareTo(new BigDecimal(0))==0) {
				continue;
			}
			mustDate = DateOperator.addYears(contStartDate, i);
			collDate = DateOperator.parse(DateOperator.formatDate(DateOperator.addYears(contStartDate, i), "yyyy-MM")+"-"+payDay, "yyyy-MM-dd");
			if("2".equals(payMonth)) { //上一个月
				collDate = DateOperator.addMonthes(collDate, -1);
			} else if("3".equals(payMonth)) { //下一个月
				collDate = DateOperator.addMonthes(collDate, 1);
			} else if("4".equals(payMonth)) { //上两个月
				collDate = DateOperator.addMonthes(collDate, -2);
			} else if("5".equals(payMonth)) { //下两个月
				collDate = DateOperator.addMonthes(collDate, 2);
			} else if("6".equals(payMonth)) { //上三个月
				collDate = DateOperator.addMonthes(collDate, -3);
			} else if("7".equals(payMonth)) { //下三个月
				collDate = DateOperator.addMonthes(collDate, 3);
			}
			
			int months = 13;
			if(i==bisCont.getBisMustRents().size()-1) {
				months = 14;
			}
			
			for(int j=1; j<months; j++) {
				
				int mustYear = DateOperator.getYear(mustDate);
				int mustMonth = DateOperator.getMonth12(mustDate);
				
				// 权责日期在2011-8以前的不生成
//				if(mustYear<2011 || (mustYear==2011 && mustMonth<8)) {
//					mustDate = DateOperator.addMonthes(mustDate, 1);
//					continue;
//				}
				if(mustYear>endYear || (mustYear==endYear && mustMonth>endMonth)) {
					break;
				}
				
				BisMust bisMust = new BisMust();
				bisMust.setBisCont(bisCont);
				bisMust.setBisProjectId(bisCont.getBisProjectId());
				bisMust.setBisTenantId(bisCont.getBisTenantId());
				bisMust.setBisShopId(bisTenantManager.getEntity(bisCont.getBisTenantId()).getBisShopId());
				bisMust.setChargeTypeCd("1"); //租金
				bisMust.setTypeCd("0"); //区分能源费导入
				
				bisMust.setMustYear(String.valueOf(mustYear));
				bisMust.setMustMonth(String.valueOf(mustMonth));
				bisMust.setCollDate(collDate);
				
				if(mustYear==startYear && mustMonth==startMonth) {
					BigDecimal money = rentMoney.multiply(new BigDecimal(startDaysOfMonth-startDay+1)).divide(new BigDecimal(startDaysOfMonth), scale, BigDecimal.ROUND_UP);
					bisMust.setMoney(money);
				} else if(mustYear==endYear && mustMonth==endMonth) {
					BigDecimal money = rentMoney.multiply(new BigDecimal(endDay)).divide(new BigDecimal(endDaysOfMonth), scale, BigDecimal.ROUND_UP);
					bisMust.setMoney(money);
				} else {
					bisMust.setMoney(rentMoney);
				}
				bisMust.setStatusCd("1"); //待审核
				if(!containsMust(bisMust, bisContId)) {
					bisMustManager.saveBisMust(bisMust);
				}
				
				if(StringUtils.equals(payCycle, "1")) {//月付
					collDate = DateOperator.addMonthes(collDate, 1);
				} else if(StringUtils.equals(payCycle, "2")) {//季付
					if(j%3==0) {
						collDate = DateOperator.addMonthes(collDate, 3);
					}
				} else if(StringUtils.equals(payCycle, "4")) {//半年付
					if(j%6==0) {
						collDate = DateOperator.addMonthes(collDate, 6);
					}
				}
				mustDate = DateOperator.addMonthes(mustDate, 1);
				
			}
			
		}
		
	}
	
	/**
	 * 审核变更合同(NOW)
	 */
	@Transactional
	public String approveChange(String bisContId) {
		
		BisCont bisCont = getEntity(bisContId);
		backToHis(bisCont, null); //变更前合同记为历史
		updateContFromHis(bisCont); //变更后合同记为新版合同，并删除变更后合同历史
		
		autoIncreaseMust(bisContId);
		return "success";
	}

	/**
	 * 变更后合同记为新版合同，并删除变更后合同历史
	 */
	private void updateContFromHis(BisCont bisCont) {
		
		// 找出合同历史记录
		List<BisContHis> list = bisContHisManager.find("from BisContHis where bisContId=? and statusCd='6'",
				bisCont.getBisContId());
		if (list.size() != 1)
			throw new RuntimeException("BisCont his not found.");
		BisContHis bisContHis = list.get(0);
		
		/*
		if (StringUtils.equals(bisContHis.getContBigTypeCd(), BisConstants.STORE)
				&& !StringUtils.equals(bisContHis.getContSmallTypeCd(), BisConstants.BACK)) {

			Set<String> changeStoreIdSet = new HashSet<String>();
			Set<String> hisStoreIdSet = new HashSet<String>();
			List<String> changeStoreIdList = getStoreIds(bisCont.getBisContId());
			changeStoreIdSet.addAll(changeStoreIdList);

			String[] hisStoreIdArr = bisContHis.getStoreFlatIds().split(",");
			for (String storeId : hisStoreIdArr) {
				hisStoreIdSet.add(storeId);
			}

			// 租户不同(暂不考虑这种情况)
			// String changeShopId =
			// bisTenantManager.getEntity(bisCont.getBisTenantId()).getBisShopId();
			// if(!StringUtils.equals(changeShopId, bisContHis.getBisShopId())
			// || !changeStoreIdSet.equals(hisStoreIdSet)) {
			// changeTenant(bisContHis, bisCont, changeStoreIdList,
			// hisStoreIdArr);
			// }
		}
		*/
		
		// 复制合同历史到合同
		BisBeanUtils.copyFromHis(bisContHis, bisCont);
		bisCont.setStatusCd(BisConstants.STATUS_APPROVED);
		bisCont.setCheckUserCd(SpringSecurityUtils.getCurrentUiid());
		bisCont.setCheckDate(new Date());
		bisCont.setUpdateBl(false);
		saveBisCont(bisCont);
		
		// 经营性质放到租户中
		if (BisConstants.RENT.equals(bisCont.getContSmallTypeCd())) {
			BisTenant bisTenant = bisTenantManager.getEntity(bisCont.getBisTenantId());
			bisTenant.setManageCd(bisCont.getManageCd());
			bisTenantManager.saveBisTenant(bisTenant);
		}
		saveSubCont(bisCont); // 合同副表

		// 合同关联商铺/公寓/多经
		if (BisConstants.STORE.equals(bisCont.getContBigTypeCd())) { // 商铺

			bisStoreContRelManager.deleteByContAndStore(bisCont.getBisContId(), null);
			for (String storeId : bisContHis.getStoreFlatIds().split(",")) {
				if (StringUtils.isBlank(storeId)) {
					continue;
				}
				BisStore bisStore = bisStoreManager.getEntity(storeId);
				BisStoreContRel ref = new BisStoreContRel();
				ref.setBisCont(bisCont);
				ref.setBisStore(bisStore);
				bisStoreContRelManager.saveBisStoreContRel(ref);
			}
		} else if (BisConstants.FLAT.equals(bisCont.getContBigTypeCd())) { // 公寓

			bisFlatContRelManager.deleteByContAndFlat(bisCont.getBisContId(), null);
			for (String flatId : bisContHis.getStoreFlatIds().split(",")) {
				if (StringUtils.isBlank(flatId)) {
					continue;
				}
				BisFlat bisFlat = bisFlatManager.getEntity(flatId);
				BisFlatContRel rel = new BisFlatContRel();
				rel.setBisCont(bisCont);
				rel.setBisFlat(bisFlat);
				bisFlatContRelManager.saveBisFlatContRel(rel);
			}
		} else { // 多经

			bisMultiContRelManager.deleteByContAndMulti(bisCont.getBisContId(), null);
			for (String multiId : bisContHis.getStoreFlatIds().split(",")) {
				if (StringUtils.isBlank(multiId)) {
					continue;
				}
				BisMulti bisMulti = bisMultiManager.getEntity(multiId);
				BisMultiContRel ref = new BisMultiContRel();
				ref.setBisCont(bisCont);
				ref.setBisMulti(bisMulti);
				bisMultiContRelManager.saveBisMultiContRel(ref);
			}
		}

		// 复制合同资信
		List<String> creditExcludeIds = new ArrayList<String>();
		for (BisContCreditHis creditHis : bisContHis.getBisContCreditHises()) {

			List<BisContCredit> creditList = bisCont.getBisContCredits();
			BisContCredit bisContCredit = containsCredit(creditHis.getBisContCreditId(), creditList);
			if (bisContCredit == null) {
				bisContCredit = new BisContCredit();
			}
			BisBeanUtils.copyFromCreditHis(creditHis, bisContCredit, false);
			bisContCredit.setBisCont(bisCont);
			bisContCreditManager.save(bisContCredit);
			if (StringUtils.isNotEmpty(bisContCredit.getBisContCreditId())) {
				creditExcludeIds.add(bisContCredit.getBisContCreditId());
			}
		}
		bisContCreditManager.deleteLeaveBatch(bisCont.getBisContId(), creditExcludeIds.toArray());

		// 复制补充合同
		List<String> excludeIds = new ArrayList<String>();
		for (BisContAddiHis addiHis : bisContHis.getBisContAddiHises()) {

			List<BisContAdditional> addiList = bisCont.getBisContAdditionals();
			BisContAdditional bisContAdditional = containsContAdditional(addiHis.getBisContAdditionalId(), addiList);
			if (bisContAdditional == null) {
				bisContAdditional = new BisContAdditional();
			}
			BisBeanUtils.copyFromAddiHis(addiHis, bisContAdditional, false);
			bisContAdditional.setBisCont(bisCont);
			bisContAdditionalManager.save(bisContAdditional);
			if (StringUtils.isNotEmpty(bisContAdditional.getBisContAdditionalId())) {
				excludeIds.add(bisContAdditional.getBisContAdditionalId());
			}
		}
		bisContAdditionalManager.deleteLeaveBatch(bisCont.getBisContId(), excludeIds.toArray());

		// 复制自动应收
		List<String> mustRentExcludeIds = new ArrayList<String>();
		for (BisMustRentHis mustRentHis : bisContHis.getBisMustRentHises()) {

			List<BisMustRent> addiList = bisCont.getBisMustRents();
			BisMustRent bisMustRent = containsMustRent(mustRentHis.getBisMustRentId(), addiList);
			if (bisMustRent == null) {
				bisMustRent = new BisMustRent();
			}
			BisBeanUtils.copyFromMustRentHis(mustRentHis, bisMustRent, false);
			bisMustRent.setBisCont(bisCont);
			bisMustRentManager.saveBisMustRent(bisMustRent);
			if (StringUtils.isNotEmpty(bisMustRent.getBisMustRentId())) {
				mustRentExcludeIds.add(bisMustRent.getBisMustRentId());
			}
		}
		bisMustRentManager.deleteLeaveBatch(bisCont.getBisContId(), mustRentExcludeIds.toArray());

		deleteContHis(bisContHis.getBisContHisId()); // 删除历史
	}

	/**
	 * 记录历史
	 */
	private void backToHis(BisCont bisCont, String storeFlatIds) {
		BisContHis bisContHis = new BisContHis();
		BisBeanUtils.copyToHis(bisCont, bisContHis);
		
		if(StringUtils.isNotBlank(bisCont.getBisTenantId())) {
			BisTenant bisTenant = bisTenantManager.getEntity(bisCont.getBisTenantId());
			String bisShopId = bisTenant.getBisShopId();
			if(StringUtils.isNotBlank(bisShopId)) {
				bisContHis.setBisShopId(bisShopId);
				bisContHis.setShopNameCn(bisShopManager.getEntity(bisShopId).getNameCn());
			}
		}
		
		if(StringUtils.isBlank(storeFlatIds)) {
			String[] storeIdNos = getStoreIdAndNo(bisCont.getBisContId());
			bisContHis.setStoreFlatIds(storeIdNos[0]);
//			bisContHis.setStoreFlatNos(storeIdNos[1]);
		} else {
			bisContHis.setStoreFlatIds(storeFlatIds);
		}
		
		//设置变更时间
		if(BisConstants.STATUS_APPROVED.equals(bisContHis.getStatusCd())) {
			bisContHis.setChangeUser(SpringSecurityUtils.getCurrentUiid());
			bisContHis.setChangeDate(new Date());
		}

		bisContHisManager.saveBisContHis(bisContHis);
		
		//资信资料
		for(BisContCredit bisContCredit : bisCont.getBisContCredits()) {
			BisContCreditHis bisContCreditHis = new BisContCreditHis();
			BisBeanUtils.copyToCreditHis(bisContCredit, bisContCreditHis);
			bisContCreditHis.setBisContHis(bisContHis);
			bisContCreditHisManager.saveBisContCreditHis(bisContCreditHis);
		}
		
		//补充合同
		for(BisContAdditional bisContAdditional : bisCont.getBisContAdditionals()) {
			BisContAddiHis bisContAddiHis = new BisContAddiHis();
			BisBeanUtils.copyToAddiHis(bisContAdditional, bisContAddiHis);
			bisContAddiHis.setBisContHis(bisContHis);
			bisContAddiHisManager.saveBisContAddiHis(bisContAddiHis);
		}
		
		//自动生成
		for(BisMustRent bisMustRent : bisCont.getBisMustRents()) {
			BisMustRentHis bisMustRentHis = new BisMustRentHis();
			BisBeanUtils.copyToMustRentHis(bisMustRent, bisMustRentHis);
			bisMustRentHis.setBisContHis(bisContHis);
			bisMustRentHisManager.saveBisMustRentHis(bisMustRentHis);
		}
	}
	
	/**
	 * 驳回合同
	 */
	@Transactional
	public String backCont(String bisContId, String statusCd) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("update BisCont set statusCd=:statusCd ");
		param.put("statusCd", statusCd);
		hql.append("where bisContId=:bisContId ");
		param.put("bisContId", bisContId);
		bisContDao.batchExecute(hql.toString(), param);
		return "success";
	}
	
	/**
	 * 驳回变更合同(NOW)
	 */
	@Transactional
	public String backChange(String bisContId) throws Exception {
		
		String hisHql = "update BisContHis set statusCd='7' where bisContId=?";
		bisContDao.batchExecute(hisHql, bisContId);
		
		String hql = "update BisCont set updateBl='0' where bisContId=?";
		bisContDao.batchExecute(hql, bisContId);
		
		return "success";
	}
	
	/**
	 * 驳回合同并回到变更前
	 */
//	@Transactional
//	@Deprecated
//	public String backContVersion(String bisContId, String statusCd) throws Exception {
//		
//		List<BisContHis> list = bisContHisManager.find("from BisContHis where bisContId=? and activeBl='0'", bisContId);
//		if(list.size() != 1)
//			return "error";
//		
//		BisContHis bisContHis = list.get(0);
//		
//		BisCont bisCont = getEntity(bisContId);
//		Set<String> changeStoreIdSet = new HashSet<String>();
//		Set<String> hisStoreIdSet = new HashSet<String>();
//		List<String> changeStoreIdList = getStoreIds(bisContId);
//		changeStoreIdSet.addAll(changeStoreIdList);
//		String changeShopId = bisTenantManager.getEntity(bisCont.getBisTenantId()).getBisShopId();
//		String[] hisStoreIdArr = bisContHis.getStoreFlatIds().split(",");
//		for(String storeId : hisStoreIdArr) {
//			hisStoreIdSet.add(storeId);
//		}
//		
//		//租户不同(暂不考虑这种情况)
//		if(!StringUtils.equals(changeShopId, bisContHis.getBisShopId()) || !changeStoreIdSet.equals(hisStoreIdSet)) {
//			changeTenant(bisContHis, bisCont, changeStoreIdList, hisStoreIdArr);
//		}
//		
//		BisBeanUtils.copyFromHis(bisContHis, bisCont);
//		if(StringUtils.isNotBlank(statusCd)) {
//			bisCont.setStatusCd(statusCd);
//		}
//		saveBisCont(bisCont);
//		saveSubCont(bisCont);
//		
//		//资信资料
//		List<String> creditExcludeIds = new ArrayList<String>();
//		for(BisContCreditHis creditHis : bisContHis.getBisContCreditHises()) {
//			
//			List<BisContCredit> creditList = bisCont.getBisContCredits();
//			BisContCredit bisContCredit = containsCredit(creditHis.getBisContCreditId(), creditList);
//			if(bisContCredit == null) {
//				bisContCredit = new BisContCredit();
//			}
//			BisBeanUtils.copyFromCreditHis(creditHis, bisContCredit, false);
//			bisContCredit.setBisCont(bisCont);
//			bisContCreditManager.save(bisContCredit);
//			if(StringUtils.isNotEmpty(bisContCredit.getBisContCreditId())) {
//				creditExcludeIds.add(bisContCredit.getBisContCreditId());
//			}
//		}
//		bisContCreditManager.deleteLeaveBatch(bisCont.getBisContId(), creditExcludeIds.toArray());
//		
//		//补充合同
//		List<String> excludeIds = new ArrayList<String>();
//		for(BisContAddiHis addiHis : bisContHis.getBisContAddiHises()) {
//			
//			List<BisContAdditional> addiList = bisCont.getBisContAdditionals();
//			BisContAdditional bisContAdditional = containsContAdditional(addiHis.getBisContAdditionalId(), addiList);
//			if(bisContAdditional == null) {
//				bisContAdditional = new BisContAdditional();
//			}
//			BisBeanUtils.copyFromAddiHis(addiHis, bisContAdditional, false);
//			bisContAdditional.setBisCont(bisCont);
//			bisContAdditionalManager.save(bisContAdditional);
//			if(StringUtils.isNotEmpty(bisContAdditional.getBisContAdditionalId())) {
//				excludeIds.add(bisContAdditional.getBisContAdditionalId());
//			}
//		}
//		bisContAdditionalManager.deleteLeaveBatch(bisCont.getBisContId(), excludeIds.toArray());
//		
//		deleteContHis(bisContHis.getBisContHisId());
//		
//		return "success";
//	}

	/**
	 * 变更合同
	 */
//	@Transactional
//	@Deprecated
//	public String changeCont(String id) throws Exception {
//		
//		BisCont bisCont = getEntity(id);
//		backToHis(bisCont);
//		
//		bisCont.setStatusCd("5"); //修改合同状态(待变更)
//		saveBisCont(bisCont);
//		
//		return "success";
//	}
	
	/**
	 * 变更并提交合同(NOW)
	 */
	@Transactional
	public String submitContChange(BisCont bisCont, String bisStoreIds, String bisFlatIds, String bisMultiIds) throws Exception {
		
		//TODO 变更时改变商铺
		String existContId = containsConts(bisCont, bisStoreIds, bisFlatIds, bisMultiIds, null, null);
		if(StringUtils.isNotEmpty(existContId))
			throw new RuntimeException("conflict:"+existContId);
		
		String storeFlatIds = "";
		if(BisConstants.STORE.equals(bisCont.getContBigTypeCd())) {
			storeFlatIds = bisStoreIds;
		} else if(BisConstants.FLAT.equals(bisCont.getContBigTypeCd())) {
			storeFlatIds = bisFlatIds;
		} else {
			storeFlatIds = bisMultiIds;
		}
		
		bisCont.setStatusCd(BisConstants.STATUS_CHANGE_APPROVE);
		backToHis(bisCont, storeFlatIds);
		
		String sql = "update BisCont set updateBl='1' where bisContId=?";
		getDao().batchExecute(sql, bisCont.getBisContId());
		
		return bisCont.getBisContId();
	}
	
	/**
	 * 变更中合同(NOW)
	 */
	public BisCont getHisCont(String id) throws Exception {
		
		List<BisContHis> list = bisContHisManager.find("from BisContHis where bisContId=? and statusCd='6'", id);
		if(list.size() != 1)
			throw new RuntimeException("Contract history not found.");
		
		BisContHis bisContHis = list.get(0);
		
		BisCont bisCont = new BisCont();
		BisBeanUtils.copyFromHis(bisContHis, bisCont);
		
		//资信资料
		for(BisContCreditHis bisContCreditHis : bisContHis.getBisContCreditHises()) {
			BisContCredit bisContCredit = new BisContCredit();
			BisBeanUtils.copyFromCreditHis(bisContCreditHis, bisContCredit, true);
			bisContCredit.setBisCont(bisCont);
			bisCont.getBisContCredits().add(bisContCredit);
		}
		
		//补充合同
		for(BisContAddiHis bisContAddiHis : bisContHis.getBisContAddiHises()) {
			BisContAdditional bisContAdditional = new BisContAdditional();
			BisBeanUtils.copyFromAddiHis(bisContAddiHis, bisContAdditional, true);
			bisContAdditional.setBisCont(bisCont);
			bisCont.getBisContAdditionals().add(bisContAdditional);
		}
		
		//自动生成
		for(BisMustRentHis bisMustRentHis : bisContHis.getBisMustRentHises()) {
			BisMustRent bisMustRent = new BisMustRent();
			BisBeanUtils.copyFromMustRentHis(bisMustRentHis, bisMustRent, true);
			bisMustRent.setBisCont(bisCont);
			bisCont.getBisMustRents().add(bisMustRent);
		}
		
		return bisCont;
	}
	
	/**
	 * 通过网批ID取得合同
	 */
	public BisCont getByResId(String resId) throws Exception {
		
		List<BisCont> list = find("from BisCont where resApproveInfoId=?", resId);
		if(list.size() == 1)
			return list.get(0);
		return null;
	}
	
	/**
	 * 查看合同历史
	 */
	public BisCont getHisVersion(String id) throws Exception {
		
		List<BisContHis> list = bisContHisManager.find("from BisContHis where bisContHisId=?", id);
		if(list.size() != 1)
			throw new RuntimeException("Contract history not found.");
		
		BisCont bisCont = new BisCont();
		BisContHis bisContHis = list.get(0);
		BisBeanUtils.copyFromHis(bisContHis, bisCont);
		
		//资信资料
		for(BisContCreditHis bisContCreditHis : bisContHis.getBisContCreditHises()) {
			BisContCredit bisContCredit = new BisContCredit();
			BisBeanUtils.copyFromCreditHis(bisContCreditHis, bisContCredit, true);
			bisContCredit.setBisCont(bisCont);
			bisCont.getBisContCredits().add(bisContCredit);
		}
		
		//补充合同
		for(BisContAddiHis bisContAddiHis : bisContHis.getBisContAddiHises()) {
			BisContAdditional bisContAdditional = new BisContAdditional();
			BisBeanUtils.copyFromAddiHis(bisContAddiHis, bisContAdditional, true);
			bisContAdditional.setBisCont(bisCont);
			bisCont.getBisContAdditionals().add(bisContAdditional);
		}
		
		//自动生成
		for(BisMustRentHis bisMustRentHis : bisContHis.getBisMustRentHises()) {
			BisMustRent bisMustRent = new BisMustRent();
			BisBeanUtils.copyFromMustRentHis(bisMustRentHis, bisMustRent, true);
			bisMustRent.setBisCont(bisCont);
			bisCont.getBisMustRents().add(bisMustRent);
		}
		return bisCont;
	}
	
	/**
	 * 取得可添加的费用类别
	 */
	public Map<String, String> getChargeTypeMap(String bisContId) {
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		BisCont bisCont = getEntity(bisContId);
		
		if(!StringUtils.equals(bisCont.getContBigTypeCd(), BisConstants.STORE))
			return map;
		
//		map = getOtherChargeType(bisCont.getContSmallTypeCd());
		map = appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_CHARGE_TYPE_STORE);
		
		if(!StringUtils.equals(BisConstants.BACK, bisCont.getContSmallTypeCd())) {
			
//			removeTypeInUsed(map, bisCont);
			List<String> typeInUsed = getChargeTypeInUsed(bisCont);
			for (String typeCd : typeInUsed) {
				map.remove(typeCd);
			}
		}
		
		return map;
	}

	/**
	 * 去除相关时间段已使用的费用类别
	 */
	@Deprecated
	public void removeTypeInUsed(Map<String, String> map, BisCont bisCont) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisTenantId", bisCont.getBisTenantId());
		param.put("contStartDate", bisCont.getContStartDate());
		param.put("contEndDate", bisCont.getContEndDate());
		param.put("contBigTypeCd", bisCont.getContBigTypeCd());
		param.put("contSmallTypeCd", bisCont.getContSmallTypeCd());
		StringBuilder hql = new StringBuilder("from BisCont ")
			.append("where bisTenantId=:bisTenantId and contStartDate>=:contStartDate and contEndDate<=:contEndDate ")
			.append("and contBigTypeCd=:contBigTypeCd and contSmallTypeCd!=:contSmallTypeCd");
		List<BisCont> list = getDao().find(hql.toString(), param);
		
		for(BisCont cont : list) {
			String chargerTypeArr = cont.getChargerTypeArr();
			if(StringUtils.isBlank(chargerTypeArr)) {
				continue;
			}
			String[] typeCds = chargerTypeArr.split(",");
			for(int i=0; i<typeCds.length; i++) {
				if(StringUtils.isBlank(typeCds[i])) {
					continue;
				}
				map.remove(typeCds[i]);
			}
		}
	}
	
	/**
	 * 获得合同费用类别
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<String> getContChargeType(String bisContId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		String sql = "select distinct t.charge_type_cd from bis_must t where t.bis_cont_id=:bisContId and t.type_cd='0' order by t.charge_type_cd";
		param.put("bisContId", bisContId);
		
		return getDao().findBySql(sql, param);
	}
	
	/**
	 * 获得已使用的费用类别
	 */
	@SuppressWarnings("unchecked")
	public List<String> getChargeTypeInUsed(BisCont bisCont) {

		Map<String, Object> param = new HashMap<String, Object>();
		String sql = "select distinct t.charge_type_cd from bis_must t where t.bis_tenant_id=:bisTenantId " +
				"and t.bis_cont_id in (select c.bis_cont_id from bis_cont c where c.bis_cont_id != :bisContId and c.cont_small_type_cd != :contSmallTypeCd)";

		param.put("bisTenantId", bisCont.getBisTenantId());
		param.put("bisContId", bisCont.getBisContId());
		param.put("contSmallTypeCd", bisCont.getContSmallTypeCd());

		return getDao().findBySql(sql, param);
	}

	/**
	 * 保存商铺合同
	 */
	private void saveStoreCont(BisCont bisCont, String bisShopId, String bisShopConnId, String bisStoreIds) {

		if (!BisConstants.BACK.equals(bisCont.getContSmallTypeCd())) {

			String bisTenantId = getTenantId(bisCont, bisShopId, bisShopConnId);
			BisTenant bisTenant = setTenantProp(bisCont, bisShopId, bisShopConnId, bisTenantId);
			bisTenantManager.saveBisTenant(bisTenant); // 保存租户表

			bisCont.setBisTenantId(bisTenant.getBisTenantId()); // 更新合同表
		}

		String[] storeIds = bisStoreIds.split(",");
		for (int i = 0; i < storeIds.length; i++) {
			if (StringUtils.isBlank(storeIds[i])) {
				continue;
			}

			BisStore bisStore = bisStoreManager.getEntity(storeIds[i]);
			BisStoreContRel ref = new BisStoreContRel();
			ref.setBisCont(bisCont);
			ref.setBisStore(bisStore);
			bisStoreContRelManager.saveBisStoreContRel(ref); // 保存商铺-合同关联表
		}
	}

	/**
	 * 设置租户属性
	 */
	private BisTenant setTenantProp(BisCont bisCont, String bisShopId, String bisShopConnId, String bisTenantId) {
		
		BisTenant bisTenant = null;
		if(StringUtils.isNotEmpty(bisTenantId)) {
			bisTenant = bisTenantManager.getEntity(bisTenantId);
		} else {
			bisTenant = new BisTenant();
			bisTenant.setBisProjectId(bisCont.getBisProjectId());
			bisTenant.setBisShopId(bisShopId);
			bisTenant.setBisShopConnId(bisShopConnId);
			bisTenant.setActiveBl(true);
			if(BisConstants.OWNER_PROP.equals(bisCont.getContSmallTypeCd())) {
				bisTenant.setTypeCd("3");
				bisTenant.setOwner(bisCont.getOwner());
			} else if(StringUtils.isBlank(bisShopConnId)) {
				bisTenant.setTypeCd("2");
			} else {
				bisTenant.setTypeCd("1");
			}
			bisTenant.setRentStartDate(bisCont.getContStartDate());
			bisTenant.setRentEndDate(bisCont.getContEndDate());
		}
		if(StringUtils.isNotBlank(bisCont.getLayoutCd())) {
			bisTenant.setLayoutCd(bisCont.getLayoutCd());
		}
		if(StringUtils.isNotBlank(bisCont.getManageCd())) {
			bisTenant.setManageCd(bisCont.getManageCd());
		}
		return bisTenant;
	}
	
	/**
	 * 保存公寓合同
	 */
	private void saveFlatCont(BisCont bisCont, String bisFlatIds) {
		
		if(StringUtils.isNotBlank(bisFlatIds)) {
			String[] flatIds = bisFlatIds.split(",");
			for(int i=0; i<flatIds.length; i++) {
				
				if(StringUtils.isNotBlank(flatIds[i])) {
					BisFlat bisFlat = bisFlatManager.getEntity(flatIds[i]);
					BisFlatContRel ref = new BisFlatContRel();
					ref.setBisCont(bisCont);
					ref.setBisFlat(bisFlat);
					bisFlatContRelManager.saveBisFlatContRel(ref); //保存公寓-合同关联表
				}
			}
		}
	}
	
	/**
	 * 保存多经合同
	 */
	private void saveMultiCont(BisCont bisCont, String bisMultiIds) {
		
		if(StringUtils.isNotBlank(bisMultiIds)) {
			String[] multiIds = bisMultiIds.split(",");
			for(int i=0; i<multiIds.length; i++) {
				
				if(StringUtils.isNotBlank(multiIds[i])) {
					BisMulti bisMulti = bisMultiManager.getEntity(multiIds[i]);
					BisMultiContRel rel = new BisMultiContRel();
					rel.setBisCont(bisCont);
					rel.setBisMulti(bisMulti);
					bisMultiContRelManager.saveBisMultiContRel(rel);//保存多经-合同关联表
				}
			}
		}
	}
	
	/**
	 * 更新商铺合同
	 */
	private void updateStoreCont(BisCont bisCont, String bisShopId, String bisShopConnId, String bisStoreIds) {

		if (!BisConstants.BACK.equals(bisCont.getContSmallTypeCd())) {

			String oldTenantId = bisCont.getBisTenantId();
			String existTenantId = getTenantId(bisCont, bisShopId, bisShopConnId);
			BisTenant bisTenant = setTenantProp(bisCont, bisShopId, bisShopConnId, existTenantId);
			bisTenantManager.saveBisTenant(bisTenant); // 保存租户表

			// 租户发生改变
			if (!StringUtils.equals(bisTenant.getBisTenantId(), oldTenantId)) {

				bisCont.setBisTenantId(bisTenant.getBisTenantId());
				if (containsContOfTenant(oldTenantId, bisCont.getBisContId())) {
					bisTenantManager.deleteBisTenant(oldTenantId);
				}
				updateMustTenant(bisCont.getBisContId(), bisTenant.getBisTenantId());
				updateFactTenant(bisCont.getBisContId(), bisTenant.getBisTenantId());
			}
		}

		List<String> oldStoreIds = getStoreIds(bisCont.getBisContId());
		String[] newStoreIdArr = bisStoreIds.split(",");
		for (String newStoreId : newStoreIdArr) {
			if (StringUtils.isBlank(newStoreId)) {
				continue;
			}
			if (oldStoreIds.contains(newStoreId)) {
				oldStoreIds.remove(newStoreId);
				continue;
			}
			BisStore bisStore = bisStoreManager.getEntity(newStoreId);
			BisStoreContRel ref = new BisStoreContRel();
			ref.setBisCont(bisCont);
			ref.setBisStore(bisStore);
			bisStoreContRelManager.saveBisStoreContRel(ref); // 保存商铺-合同关联表
		}

		if (oldStoreIds.size() > 0) {
			bisStoreContRelManager.deleteByContAndStore(bisCont.getBisContId(), oldStoreIds.toArray());
		}
	}
	
	/**
	 * 修改合同相关应收的租户ID
	 */
	private void updateMustTenant(String bisContId, String bisTenantId) {
		StringBuffer hql = new StringBuffer("update BisMust set bisTenantId=? where bisCont.bisContId=?");
		getDao().batchExecute(hql.toString(), bisTenantId, bisContId);
	}

	/**
	 * 修改合同相关实收的租户ID
	 */
	private void updateFactTenant(String bisContId, String bisTenantId) {
		StringBuffer hql = new StringBuffer("update BisFact set bisTenantId=? where bisContId=?");
		getDao().batchExecute(hql.toString(), bisTenantId, bisContId);
	}
	
//	@SuppressWarnings("unused")
//	@Deprecated
//	private void updateFirstContId(BisCont bisCont) {
//		
//		if(StringUtils.isBlank(bisCont.getBisContId()) || StringUtils.isBlank(bisCont.getBisTenantId()))
//			return;
//		
//		Map<String, Object> param = new HashMap<String, Object>();
//		StringBuffer sql = new StringBuffer();
//		sql.append(" select bis_cont_id from (")
//			.append(" select bis_cont_id from bis_cont")
//			.append(" where bis_tenant_id=:bis_tenant_id and bis_cont_id <> :bis_cont_id")
//			.append(" order by created_date")
//			.append(") where rownum=1");
//				
//		param.put("bis_tenant_id", bisCont.getBisTenantId());
//		param.put("bis_cont_id", bisCont.getBisContId());
//		
//		@SuppressWarnings("unchecked")
//		List<String> list = findBySql(sql.toString(), param);
//		if(list.size() > 0) {
//			String bisContId = list.get(0);
//			String hql = "update BisTenant set firstContId=? where bisTenantId=?";
//			getDao().batchExecute(hql, bisContId, bisCont.getBisTenantId());
//		}
//		
//	}
	
	/**
	 * 更新公寓合同
	 */
	private void updateFlatCont(BisCont bisCont, String bisFlatIds) {
		if(StringUtils.isBlank(bisFlatIds))
			throw new RuntimeException("bisFlatIds is empty!");
		
		List<String> oldFlatIds = getFlatIds(bisCont.getBisContId());
		
		String[] newFlatIdArr = bisFlatIds.split(",");
		for(String flatId : newFlatIdArr) {
			if(StringUtils.isBlank(flatId)) {
				continue;
			}
			if(oldFlatIds.contains(flatId)) {
				oldFlatIds.remove(flatId);
				continue;
			}
			
			BisFlat bisFlat = bisFlatManager.getEntity(flatId);
			BisFlatContRel ref = new BisFlatContRel();
			ref.setBisCont(bisCont);
			ref.setBisFlat(bisFlat);
			bisFlatContRelManager.saveBisFlatContRel(ref); //保存公寓-合同关联表
		}

		if(oldFlatIds.size() > 0) {
			bisFlatContRelManager.deleteByContAndFlat(bisCont.getBisContId(), oldFlatIds.toArray());
		}
	}
	
	/**
	 * 更新多经合同
	 */
	private void updateMultiCont(BisCont bisCont, String bisMultiIds) {
		if(StringUtils.isBlank(bisMultiIds))
			throw new RuntimeException("bisMultiIds is empty!");
		
		List<String> oldMultiIds = getMultiIds(bisCont.getBisContId());
		
		String[] newMultiIdArr = bisMultiIds.split(",");
		for(String multiId : newMultiIdArr) {
			if(StringUtils.isBlank(multiId)) {
				continue;
			}
			if(oldMultiIds.contains(multiId)) {
				oldMultiIds.remove(multiId);
				continue;
			}
			
			BisMulti bisMulti = bisMultiManager.getEntity(multiId);
			BisMultiContRel rel = new BisMultiContRel();
			rel.setBisCont(bisCont);
			rel.setBisMulti(bisMulti);
			bisMultiContRelManager.saveBisMultiContRel(rel); //保存多经-合同关联表
		}
		
		if(oldMultiIds.size() > 0) {
			bisMultiContRelManager.deleteByContAndMulti(bisCont.getBisContId(), oldMultiIds.toArray());
		}
	}
	
	/**
	 * 更新补充合同表
	 */
	private void updateCredits(BisCont entity) {
		List<String> excludeIds = new ArrayList<String>();
		for(BisContCredit bisContCredit : entity.getBisContCredits()) {
			if(bisContCredit == null) {
				continue;
			}
			bisContCredit.setBisCont(entity);
			bisContCreditManager.save(bisContCredit);
			if(StringUtils.isNotEmpty(bisContCredit.getBisContCreditId())) {
				excludeIds.add(bisContCredit.getBisContCreditId());
			}
		}
		bisContCreditManager.deleteLeaveBatch(entity.getBisContId(), excludeIds.toArray());
	}
	
	/**
	 * 更新补充合同表
	 */
	private void updateAdditinals(BisCont entity) {
		List<String> excludeIds = new ArrayList<String>();
		for(BisContAdditional bisContAdditional : entity.getBisContAdditionals()) {
			if(bisContAdditional == null) {
				continue;
			}
			bisContAdditional.setBisCont(entity);
			bisContAdditionalManager.save(bisContAdditional);
			if(StringUtils.isNotEmpty(bisContAdditional.getBisContAdditionalId())) {
				excludeIds.add(bisContAdditional.getBisContAdditionalId());
			}
		}
		bisContAdditionalManager.deleteLeaveBatch(entity.getBisContId(), excludeIds.toArray());
	}

//	@Deprecated
//	private void changeTenant(BisContHis bisContHis, BisCont bisCont,
//			List<String> changeStoreIdList, String[] hisStoreIdArr) {
//		if(!containsContOfTenant(bisCont.getBisTenantId(), bisCont.getBisContId())) {
//			bisTenantManager.deleteBisTenant(bisCont.getBisTenantId());
//		}
//
//		String existTenantId = getExistTenantId(bisCont.getBisProjectId(), bisCont.getBisContId(), bisContHis.getStoreFlatIds());
//		if(StringUtils.isNotEmpty(existTenantId)) {
//			
//			BisTenant existBisTenant = bisTenantManager.getEntity(existTenantId);
//			bisContHis.setBisTenantId(existBisTenant.getBisTenantId());
//			
//		} else {
//			
//			BisTenant hisTenant = new BisTenant();
//			BisShop bisShop = bisShopManager.getEntity(bisContHis.getBisShopId());
//			hisTenant.setRentStartDate(bisContHis.getContStartDate());
//			hisTenant.setRentEndDate(bisContHis.getContEndDate());
//			hisTenant.setFirstContId(bisContHis.getBisContId());
//			
//			hisTenant.setBisProjectId(bisContHis.getBisProjectId());
//			hisTenant.setBisShopId(bisContHis.getBisShopId());
//			hisTenant.setConnName(bisContHis.getConnPeople());
//			hisTenant.setConnTel(bisContHis.getConnTel());
//			hisTenant.setBrand(bisContHis.getBrand());
//			hisTenant.setManageCd(bisContHis.getManageCd());
////				bisTenant.setLayoutCd();//业态
////				bisTenant.setStatusCd();//状态
////				bisTenant.setOwner();//出租方
////				bisTenant.setRenterName(bisShop.getNameCn());//承租方
////				bisTenant.setActiveUnionManage(true);//是否联营
//			if(StringUtils.equals(bisContHis.getContSmallTypeCd(), BisConstants.BACK)) {//是否返租?
//				hisTenant.setActiveBackrent(true);
//			}
//			hisTenant.setActiveBl(true);
//			hisTenant.setShopTypeCd(bisShop.getShopTypeCd());
//			
//			bisTenantManager.saveBisTenant(hisTenant); //保存租户表
//			bisContHis.setBisTenantId(hisTenant.getBisTenantId());
//		}
//		
//		for(String storeId : hisStoreIdArr) {
//			if(StringUtils.isBlank(storeId)) {
//				continue;
//			}
//			if(changeStoreIdList.contains(storeId)) {
//				changeStoreIdList.remove(storeId);
//				continue;
//			}
//			BisStore bisStore = bisStoreManager.getEntity(storeId);
////				bisStore.setBisContId(bisCont.getBisContId());//更新商铺表
//			BisStoreContRel ref = new BisStoreContRel();
//			ref.setBisCont(bisCont);
//			ref.setBisStore(bisStore);
//			bisStoreContRelManager.saveBisStoreContRel(ref); //保存商铺-合同关联表
//		}
//		if(changeStoreIdList.size() > 0) {
//			bisStoreContRelManager.deleteByContAndStore(bisCont.getBisContId(), changeStoreIdList.toArray());
//		}
//		
////		bisMustManager.updateMustTenant(bisCont.getBisContId(), bisTenant.getBisTenantId());
//	}
	
	/**
	 * 删除历史
	 */
	private void deleteContHis(String hisId) {
		
		String mustRentHisHql = "delete from BisMustRentHis where bisContHis.bisContHisId=?";
		String addiHisHql = "delete from BisContAddiHis where bisContHis.bisContHisId=?";
		String creditHisHql = "delete from BisContCreditHis where bisContHis.bisContHisId=?";
		String hisHql = "delete from BisContHis where bisContHisId=?";
		
		getDao().batchExecute(mustRentHisHql, hisId);
		getDao().batchExecute(addiHisHql, hisId);
		getDao().batchExecute(creditHisHql, hisId);
		getDao().batchExecute(hisHql, hisId);
	}
	
	/**
	 * 根据合同、费用类别、时间来判断是否存在相同应收记录
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	private boolean containsMust(BisMust bisMust, String bisContId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisContId", bisContId);
		param.put("chargeTypeCd", bisMust.getChargeTypeCd());
		param.put("mustYear", bisMust.getMustYear());
		param.put("mustMonth", bisMust.getMustMonth());
		
		StringBuffer sql = new StringBuffer("select t.bis_must_id from bis_must t ")
			.append(" where t.bis_cont_id=:bisContId and t.charge_type_cd=:chargeTypeCd and t.must_year=:mustYear and t.must_month=:mustMonth ");
		if(StringUtils.isNotBlank(bisMust.getBisMustId())) {
			sql.append("and t.bis_must_id!=:bisMustId ");
			param.put("bisMustId", bisMust.getBisMustId());
		}
		List<Object[]> list = bisMustManager.findBySql(sql.toString(), param);
		
		if(list.isEmpty())
			return false;
		else 
			return true;
	}
	
	/**
	 * 根据bisShopId、bisStoreIds取得已存在租户Id,无则返回""
	 */
//	@SuppressWarnings("unused")
//	@Transactional(propagation = Propagation.NOT_SUPPORTED)
//	@Deprecated
//	private String getExistTenantId(String projectId, String bisContId, String bisStoreIds) {
//		Set<String> storeIds = new HashSet<String>();
//		for(String storeId : bisStoreIds.split(",")) {
//			storeIds.add(storeId);
//		}
//		
//		StringBuffer hql = new StringBuffer("");
//		hql.append("select bc from BisCont bc ")
//			.append("where bc.bisProjectId=? ")
//			.append("and bc.bisContId != ? ");
////			.append("and exists (select 1 from BisTenant bt where bc.bisTenantId=bt.bisTenantId and bt.bisShop.bisShopId=? and bt.bisShopConnId=?) ");
//		List<BisCont> list = getDao().find(hql.toString(), projectId, bisContId);
//		
//		for(BisCont bisCont : list) {
//			Set<String> stores = getStoreIds(bisCont);
//			if(storeIds.equals(stores))
//				return bisCont.getBisTenantId();
//		}
////		if(list.size() == 1)
////			return list.get(0).getBisTenantId();
//		
//		return "";
//	}
	
	/**
	 * 根据bisShopId、bisShopConnId取得已存在租户Id,无则返回""
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	private String getTenantId(BisCont bisCont, String bisShopId, String bisShopConnId) {
		
		List<BisTenant> list = null;
		if(BisConstants.OWNER_PROP.equals(bisCont.getContSmallTypeCd())) {
			String hql = "from BisTenant bt where bt.bisProjectId=? and bt.typeCd='3' and bt.owner=? ";
			list = getDao().find(hql, bisCont.getBisProjectId(), bisCont.getOwner());
		} else {
			String hql = "from BisTenant bt where bt.bisProjectId=? and bt.bisShopId=? and bt.bisShopConnId=? and typeCd='1' ";
			list = getDao().find(hql, bisCont.getBisProjectId(), bisShopId, bisShopConnId);
		}
		
		if(list.size() == 1)
			return list.get(0).getBisTenantId();
		else if(list.size() > 1)
			throw new RuntimeException("租户有重复");
		return "";
	}
	
	/**
	 * 根据合同ID、项目、商铺、合同类别、时间段判断合同是否冲突
	 */
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	private String containsConts(BisCont bisCont, String storeIds, String flatIds, String multiIds, String bisShopId,
			String bisShopConnId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", bisCont.getBisProjectId());
		param.put("contBigTypeCd", bisCont.getContBigTypeCd());
		param.put("contSmallTypeCd", bisCont.getContSmallTypeCd());
		param.put("startDate", bisCont.getContStartDate());
		param.put("endDate", bisCont.getContEndDate());

		StringBuilder sql = new StringBuilder(
				"select bc.bis_cont_id from bis_cont bc where bis_project_id=:bisProjectId ");

		if (StringUtils.isNotEmpty(bisCont.getBisContId())) {
			sql.append(" and bc.bis_cont_id != :bisContId");
			param.put("bisContId", bisCont.getBisContId());
		}
		if (StringUtils.equals(bisCont.getContBigTypeCd(), BisConstants.STORE)) {

			sql.append(" and exists ( select 1 from bis_store_cont_rel scr where bc.bis_cont_id=scr.bis_cont_id and scr.bis_store_id in (:bisStoreIds) )");
			param.put("bisStoreIds", storeIds.split(","));

		} else if (StringUtils.equals(bisCont.getContBigTypeCd(), BisConstants.FLAT)) {

			sql.append(" and exists ( select 1 from bis_flat_cont_rel fcr where bc.bis_cont_id=fcr.bis_cont_id and fcr.bis_flat_id in (:bisFlatIds) ) ");
			param.put("bisFlatIds", flatIds.split(","));
		} else if (StringUtils.equals(bisCont.getContBigTypeCd(), BisConstants.MULTI)) {
			sql.append(" and exists ( select 1 from bis_multi_cont_rel mcr where bc.bis_cont_id=mcr.bis_cont_id and mcr.bis_multi_id in (:bisMultiIds) ) ");
			param.put("bisMultiIds", multiIds.split(","));
		} else
			throw new RuntimeException("containsConts().contBigTypeCd ERROR");

		sql.append(" and bc.cont_big_type_cd=:contBigTypeCd and bc.cont_small_type_cd=:contSmallTypeCd ")
			.append(" and decode(bc.active_bl,'1',bc.cont_end_date,bc.cont_to_fail_date) >= :startDate ");
		if(bisCont.getContEndDate() != null) {
			sql.append(" and bc.cont_start_date <= :endDate ");
		}

		List<Object> list = findBySql(sql.toString(), param);

		List<Object> list2 = new ArrayList<Object>();
		if (BisConstants.STORE.equals(bisCont.getContBigTypeCd())
				&& !BisConstants.BACK.equals(bisCont.getContSmallTypeCd())
				&& !BisConstants.OWNER_PROP.equals(bisCont.getContSmallTypeCd())
				&& StringUtils.isNotBlank(bisShopId)
				&& StringUtils.isNotBlank(bisShopConnId)) {

			StringBuffer sql2 = new StringBuffer();
			sql2.append("select bc.bis_cont_id from bis_cont bc ")
				.append(" where bis_project_id=:bisProjectId and bc.cont_big_type_cd='1' and bc.cont_small_type_cd <> '2' and bc.cont_small_type_cd <> :contSmallTypeCd")
				.append(" and exists ( select 1 from bis_store_cont_rel scr where bc.bis_cont_id=scr.bis_cont_id and scr.bis_store_id in (:bisStoreIds) )")
				.append(" and exists ( select 1 from bis_tenant bt where bc.bis_tenant_id=bt.bis_tenant_id and bt.bis_shop_id!=:bisShopId and bt.bis_shop_conn_id!=:bisShopConnId )")
				.append(" and bc.cont_start_date <= :endDate")
				.append(" and decode(bc.active_bl,'1',bc.cont_end_date,bc.cont_to_fail_date) >= :startDate");

			param.put("bisShopId", bisShopId);
			param.put("bisShopConnId", bisShopConnId);

			list2 = findBySql(sql2.toString(), param);
		}

		if (!list.isEmpty())
			return list.get(0).toString();
		if (!list2.isEmpty())
			return list2.get(0).toString();
		
		return "";
	}
	
	public String containsConts(BisCont bisCont, String storeIds, String bisShopId, String bisShopConnId) {
		
		return containsConts(bisCont, storeIds, null, null, bisShopId, bisShopConnId);
	}
	
	/**
	 * 判断此租户下是否有其他合同
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	private boolean containsContOfTenant(String bisTenantId, String bisContId) {
		
		String hql = "from BisCont where bisTenantId=? and bisContId!=?";
		List<BisCont> list = find(hql, bisTenantId, bisContId);
		if(list.isEmpty())
			return false;
		else 
			return true;
	}
	
	/**
	 * 根据ID判断是否有此资信资料
	 */
	private BisContCredit containsCredit(String id, List<BisContCredit> creditList) {
		
		for(BisContCredit credit : creditList) {
			if(StringUtils.equals(id, credit.getBisContCreditId()))
				return credit;
		}
		return null;
	}
	
	/**
	 * 根据ID判断是否有此补充合同
	 */
	private BisContAdditional containsContAdditional(String id, List<BisContAdditional> addiList) {
		
		for(BisContAdditional addi : addiList) {
			if(StringUtils.equals(id, addi.getBisContAdditionalId()))
				return addi;
		}
		return null;
	}
	
	/**
	 * 根据ID判断是否有此自动生成
	 */
	private BisMustRent containsMustRent(String id, List<BisMustRent> list) {
		
		for(BisMustRent mustRent : list) {
			if(StringUtils.equals(id, mustRent.getBisMustRentId()))
				return mustRent;
		}
		return null;
	}
	
	/**
	 * 获得已保存的费用类别集合
	 */
	@SuppressWarnings("unused")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Deprecated
	private String getChargerTypeArr(String bisContId) {
		
		String sql = "select CHARGER_TYPE_ARR from BIS_CONT where BIS_CONT_ID=:bis_cont_id";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bis_cont_id", bisContId);
		return (String) findBySql(sql, param).get(0);
	}
	
	/**
	 * 保存合同副表
	 */
	private void saveSubCont(BisCont bisCont) {
		
		String bigType = bisCont.getContBigTypeCd();
		String smallType = bisCont.getContSmallTypeCd();
		if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.RENT)) {
			BisContShopRent shopRent = bisCont.getBisContShopRents().get(0);
			shopRent.setBisCont(bisCont);
			bisContShopRentManager.saveBisContShopRent(shopRent);
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.BACK)) {
			BisContShopBack shopBack = bisCont.getBisContShopBacks().get(0);
			shopBack.setBisCont(bisCont);
			bisContShopBackManager.saveBisContShopBack(shopBack);
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.PROP)) {
			BisContShopProp shopProp = bisCont.getBisContShopProps().get(0);
			shopProp.setBisCont(bisCont);
			bisContShopPropManager.saveBisContShopProp(shopProp);
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.ENTR)) {
			BisContShopEntr shopEntr = bisCont.getBisContShopEntrs().get(0);
			shopEntr.setBisCont(bisCont);
			bisContShopEntrManager.saveBisContShopEntr(shopEntr);
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.OWNER_PROP)) {
			BisContShopProp shopProp = bisCont.getBisContShopProps().get(0);
			shopProp.setBisCont(bisCont);
			bisContShopPropManager.saveBisContShopProp(shopProp);
		} else if(StringUtils.equals(bigType, BisConstants.FLAT)) {
			BisContFlatProp flatProp = bisCont.getBisContFlatProps().get(0);
			flatProp.setBisCont(bisCont);
			bisContFlatPropManager.saveBisContFlatProp(flatProp);
		} else if(StringUtils.equals(bigType, BisConstants.MULTI)) {
			BisContMulti multi = bisCont.getBisContMultis().get(0);
			multi.setBisCont(bisCont);
			bisContMultiManager.saveBisContMulti(multi);
		}
		
	}
	
	/**
	 * 删除副表
	 */
	@SuppressWarnings("unused")
	private void deleteSubCont(BisCont bisCont) {
		
		String bigType = bisCont.getContBigTypeCd();
		String smallType = bisCont.getContSmallTypeCd();
		if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.RENT)) {
			BisContShopRent shopRent = bisCont.getBisContShopRents().get(0);
			bisContShopRentManager.delete(shopRent);
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.BACK)) {
			BisContShopBack shopBack = bisCont.getBisContShopBacks().get(0);
			bisContShopBackManager.delete(shopBack);
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.PROP)) {
			BisContShopProp shopProp = bisCont.getBisContShopProps().get(0);
			bisContShopPropManager.delete(shopProp);
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.ENTR)) {
			BisContShopEntr shopEntr = bisCont.getBisContShopEntrs().get(0);
			bisContShopEntrManager.delete(shopEntr);
		} else if(StringUtils.equals(bigType, BisConstants.STORE) && StringUtils.equals(smallType, BisConstants.OWNER_PROP)) {
			BisContShopProp shopProp = bisCont.getBisContShopProps().get(0);
			bisContShopPropManager.delete(shopProp);
		} else if(StringUtils.equals(bigType, BisConstants.FLAT)) {
			BisContFlatProp flatProp = bisCont.getBisContFlatProps().get(0);
			bisContFlatPropManager.delete(flatProp);
		} else if(StringUtils.equals(bigType, BisConstants.MULTI)) {
			BisContMulti multi = bisCont.getBisContMultis().get(0);
			bisContMultiManager.delete(multi);
		}
		
	}
	
	/**
	 * 获得商铺ID集合
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<String> getStoreIds(String bisContId) {
		
		List<BisStoreContRel> list = bisStoreContRelManager.find("from BisStoreContRel where bisCont.bisContId=?", bisContId);
		
		List<String> storeIds = new ArrayList<String>();
		for(BisStoreContRel rel : list) {
			storeIds.add(rel.getBisStore().getBisStoreId());
		}
		return storeIds;
	}

	/**
	 * 获得公寓ID集合
	 */
	public List<String> getFlatIds(String bisContId) {
		
		List<BisFlatContRel> list = bisFlatContRelManager.find("from BisFlatContRel where bisCont.bisContId=?", bisContId);
		List<String> flatIds = new ArrayList<String>();
		for(BisFlatContRel rel : list) {
			flatIds.add(rel.getBisFlat().getBisFlatId());
		}
		return flatIds;
	}
	
	/**
	 * 获得公寓ID集合
	 */
	public List<String> getMultiIds(String bisContId) {
		
		List<BisMultiContRel> list = bisMultiContRelManager.find("from BisMultiContRel where bisCont.bisContId=?", bisContId);
		List<String> multiIds = new ArrayList<String>();
		for(BisMultiContRel rel : list) {
			multiIds.add(rel.getBisMulti().getBisMultiId());
		}
		return multiIds;
	}
	
//	private Set<String> getStoreIds(BisCont bisCont) {
//		
//		Set<String> result = new HashSet<String>();
//		for(BisStoreContRel rel : bisCont.getBisStoreContRels()) {
//			result.add(rel.getBisStore().getBisStoreId());
//		}
//		return result;
//	}
	
	/**
	 * 获得合同关联的'商铺ID|NO'或者'公寓ID|NO'
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public String[] getHisStoreIdAndNo(String bisContHisId) {
		
		List<BisContHis> list = bisContHisManager.find("from BisContHis where bisContHisId=?", bisContHisId);
		if(list.size() != 1)
			throw new RuntimeException("Contract history not found.");
		
		BisContHis bisContHis = list.get(0);
		
		String[] result = new String[2];
		result[0] = bisContHis.getStoreFlatIds();
		result[1] = bisContHis.getStoreFlatNos();
		return result;
	}
	
	/**
	 * 获得合同关联的'商铺ID|NO'或者'公寓ID|NO'
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public String[] getStoreIdAndNo(String bisContId) {
		
		BisCont bisCont = getEntity(bisContId);
		
		String[] result = new String[2];
		StringBuffer storeIds = new StringBuffer("");
		StringBuffer storeNos = new StringBuffer("");
		StringBuffer hql = new StringBuffer();
		if(StringUtils.equals(bisCont.getContBigTypeCd(), BisConstants.STORE)) { //商铺合同
			hql.append("from BisStore bs")
				.append(" where exists (select 1 from BisStoreContRel rel where rel.bisStore.bisStoreId=bs.bisStoreId and rel.bisCont.bisContId=?)")
//				.append(" and bs.statusCd='1' ")
				.append(" order by bs.storeNo");
			List<BisStore> list = bisStoreManager.find(hql.toString(), bisContId);
			for(BisStore store : list) {
				storeIds.append(store.getBisStoreId()).append(",");
				storeNos.append(store.getStoreNo()).append(",");
			}
		} else if(StringUtils.equals(bisCont.getContBigTypeCd(), BisConstants.FLAT)) { //公寓合同
			hql.append("from BisFlat bf")
				.append(" where exists (select 1 from BisFlatContRel rel where rel.bisFlat.bisFlatId=bf.bisFlatId and rel.bisCont.bisContId=?)")
				.append(" order by bf.flatNo");
			
			List<BisFlat> list = bisFlatManager.find(hql.toString(), bisContId);
			for(BisFlat flat : list) {
				storeIds.append(flat.getBisFlatId()).append(",");
				storeNos.append(flat.getFlatNo()).append(",");
			}
		} else if(StringUtils.equals(bisCont.getContBigTypeCd(), BisConstants.MULTI)) { //多经合同
			hql.append("from BisMulti bm")
				.append(" where exists (select 1 from BisMultiContRel rel where rel.bisMulti.bisMultiId=bm.bisMultiId and rel.bisCont.bisContId=?)")
				.append(" order by bm.multiName");
			
			List<BisMulti> list = bisMultiManager.find(hql.toString(), bisContId);
			for(BisMulti multi : list) {
				storeIds.append(multi.getBisMultiId()).append(",");
				storeNos.append(multi.getMultiName()).append(",");
			}
//			for(BisMultiContRel rel : bisCont.getBisMultiContRels()) {
//				storeIds.append(rel.getBisMulti().getBisMultiId()).append(",");
//				storeNos.append(rel.getBisMulti().getMultiName()).append(",");
//			}
		}
		if(StringUtils.isNotBlank(storeIds.toString())) {
			result[0] = storeIds.substring(0, storeIds.length()-1);
		} else {
			result[0] = "";
		}
		if(StringUtils.isNotBlank(storeNos.toString())) {
			result[1] = storeNos.substring(0, storeNos.length()-1);
		} else {
			result[1] = "";
		}
		return result;
	}
	/**
	 * 搜索有公寓的合同
	 * @param bisProjectId
	 * @param floorId
	 * @return
	 */
	public List<BisCont> getBisContList(String bisProjectId,String floorId){
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql =new StringBuffer();
		hql.append("select a from BisCont a,BisFlatContRel b").append(" where a.bisProjectId=:bisProjectId and b.bisCont.bisContId=a.bisContId");
		param.put("bisProjectId", bisProjectId);
		if(StringUtils.isNotBlank(floorId)){
			hql.append(" and  b.bisFlat.bisFloor.bisFloorId=:floorId order by  b.bisFlat.flatNo asc");
			//hql.append(" and exists(select 1 from BisFlatContRel b where) ");
			//hql.append(" exists(select 1 from bis_flat f where F.BIS_FLAT_ID=b.bis_flat_id and  f.bis_floor_id=:floorId)");
			//hql.append(" and b.bis_cont_id =a.bis_cont_id )");
			param.put("floorId", floorId);
		}
		List<BisCont> contList =this.find(hql.toString(), param);
		return contList;
	}
	/**
	 * 搜索有多经的合同
	 */
	public List<BisCont> getContListByMulti(String bisProjectId){
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql =new StringBuffer();
		hql.append("select a from BisCont a").append(" where a.bisProjectId=:bisProjectId");
		hql.append(" and exists(select 1 from BisMultiContRel r where r.bisCont.bisContId=a.bisContId )");
		param.put("bisProjectId", bisProjectId);
		List<BisCont> contList =this.find(hql.toString(), param);
		return contList;
	}
	
	/**
	 * 生成合同编号
	 */
//	@Deprecated
//	@SuppressWarnings("unused")
//	private String generateContNo(String bisProjectId, String smallType) {
//		
//		StringBuffer contNo = new StringBuffer("SY_");
//		String psname = bisProjectManager.getEntity(bisProjectId).getProjectShortName();
//		if(StringUtils.isNotBlank(psname)) {
//			contNo.append(psname).append("_");
//		}
//		
//		if(StringUtils.equals(smallType, BisConstants.RENT)) {
//			contNo.append("ZL_");
//		} else if(BisConstants.BACK.equals(smallType)) {
//			contNo.append("FZ_");
//		} else if(BisConstants.PROP.equals(smallType)) {
//			contNo.append("WY_");
//		} else if(BisConstants.ENTR.equals(smallType)) {
//			contNo.append("WT_");
//		} else {
//			contNo.append("DJ_");
//		}
//		
//		String sql = "select seq_bisCont_no.nextval from dual";
//		BigDecimal seq_cont_no = (BigDecimal) getDao().findBySql(sql, null).get(0);
//		String seqno = "0000000000"+String.valueOf(seq_cont_no);
//		contNo.append(seqno.substring(seqno.length()-10));
//		
//		return contNo.toString();
//	}
	
	/**
	 * 商铺拆分校验，取出商铺关联合同
	 */
	public List<BisCont> storeSplitValidate(String bisStoreId){
		String hql ="select a from BisCont a,BisStoreContRel b,BisStore c where a.bisContId=b.bisCont.bisContId" +
		" and c.bisStoreId=b.bisStore.bisStoreId and c.bisStoreId=:bisStoreId and a.contBigTypeCd=:contBigTypeCd";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisStoreId", bisStoreId);
		param.put("contBigTypeCd", "1");//商铺合同
		List<BisCont> list = this.find(hql, param);
		return list;
	}
	
	/**
	 * 原用来导入修改合同条款
	 */
	public void updateCont(String bisContId, String contContent) {
		String hql = "update BisCont set contContent=? where bisContId=?";
		getDao().batchExecute(hql, contContent,bisContId);
	}

	/**
	 * 定时调用(每天00:45)
	 */
	public void runInvalid() {
		
		try {
			String sql = "update bis_cont t set t.active_bl=0, t.cont_to_fail_date=t.cont_end_date, t.invalid_by='0' " +
					"where t.active_bl=1 and t.cont_end_date < to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd')";
			getDao().createSQLQuery(sql, new HashMap<String, Object>()).executeUpdate();
			log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>> 商业系统：合同失效 success! ");
		} catch(Exception e) {
			log.error("/////////////////////////// 商业系统：合同失效 exception! " + e.getCause());
		}
	}
	
}
