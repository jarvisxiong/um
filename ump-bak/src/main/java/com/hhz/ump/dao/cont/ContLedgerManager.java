package com.hhz.ump.dao.cont;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.spring.SpringContextHolder;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.app.AppDictData;
import com.hhz.ump.entity.app.AppDictType;
import com.hhz.ump.entity.cont.ContAddAgreement;
import com.hhz.ump.entity.cont.ContContractType;
import com.hhz.ump.entity.cont.ContDelay;
import com.hhz.ump.entity.cont.ContLedger;
import com.hhz.ump.entity.cont.ContPay;
import com.hhz.ump.entity.cont.ContProjectCode;
import com.hhz.ump.entity.cont.ContRemark;
import com.hhz.ump.entity.cont.ContVisaUpdate;
import com.hhz.ump.entity.res.ResApproveInfo;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.FormatUtil;
import com.hhz.ump.util.LedgerReportUtil;
import com.hhz.ump.web.res.bean.StrategyOnlineOrder;
import com.hhz.ump.web.res.bean.StrategyPurchaseBill;

@Service
@Transactional
public class ContLedgerManager extends BaseService<ContLedger, String> {
	
	private static Log log = LogFactory.getLog(ContLedgerManager.class);
	
	@Autowired
	private ContLedgerDao contLedgerDao;
	@Autowired
	private ContVisaUpdateDao contVisaUpdateDao;
	@Autowired
	private ContPayDao contPayDao;
	@Autowired
	private ContAddAgreementDao contAddAgreementDao;
	@Autowired
	private ContDelayDao contDelayDao;
	@Autowired
	private ContRemarkDao contRemarkDao;
	@Autowired
	private AppDictTypeManager appDictTypeManager;
	@Autowired
	private AppAttachFileManager appAttachFileManager;
	@Autowired
	private ContContractTypeDao contContractTypeDao;
	
	private static String BBHD ="-BBHD-";
	private static String XOBG ="-XOBG-";

	/**
	 * 根据ID得到合同台账
	 * @param contLedgerId
	 * @return
	 */
	public ContLedger getContLedger(String contLedgerId) {
		String hql="from ContLedger where contLedgerId = :contLedgerId";
		HashMap<String, Object> map =new HashMap<String, Object>();
		map.put("contLedgerId", contLedgerId);
		ContLedger contLedger = null;
		List<ContLedger> list = getDao().createQuery(hql, map).list();
		if (list != null && list.size() >0) {
			contLedger = list.get(0);
		}
		return contLedger;
	}
	public void saveContLedger(ContLedger contLedger) {
		PowerUtils.setEmptyStr2Null(contLedger);
		
		//add by huangbijin
		if(StringUtils.isBlank(contLedger.getEnableFlg())){
			contLedger.setEnableFlg("1");//若可用标志为空,则默认可用
		}
		
		contLedgerDao.save(contLedger);
		for(int i=0;i<contLedger.getContVisaUpdates().size();i++){
			if(contLedger.getContVisaUpdates().get(i)!=null){
				ContVisaUpdate contVisaUpdate=contLedger.getContVisaUpdates().get(i);
				if(contVisaUpdate.getAmountUpdate()==null&&contVisaUpdate.getPrepareFee()==null){
					continue;
				}
				contVisaUpdate.setContLedger(contLedger);
				//签证变更动态的附件
				if(null!=contVisaUpdate.getAttaBizId()){
					List<AppAttachFile> file=appAttachFileManager.getAttaFileByBizEntityId(contVisaUpdate.getAttaBizId());
					//没有附件
					if(file==null||file.size()<=0){
						 contVisaUpdate.setAttaBizId("");
					}
				}
				
				contVisaUpdateDao.save(contVisaUpdate);
			}
		}
		for(int i=0;i<contLedger.getContPaies().size();i++){
			if(contLedger.getContPaies().get(i)!=null){
				ContPay contPay =contLedger.getContPaies().get(i);
				contPay.setContLedger(contLedger);
				contPayDao.save(contPay);
			}
		}
		for(int i=0;i<contLedger.getContAddAgreements().size();i++){
			if(contLedger.getContAddAgreements().get(i)!=null){
				ContAddAgreement contAddAgreement=contLedger.getContAddAgreements().get(i);
				if(contAddAgreement.getAmountUpdate()==null&&contAddAgreement.getContTempMoney()==null&&contAddAgreement.getGroupCheckFee()==null){
					continue;
				}
				contAddAgreement.setContLedger(contLedger);
				//如果补充协议动态附件
				List<AppAttachFile> file=appAttachFileManager.getAttaFileByBizEntityId(contAddAgreement.getAttaBizId());
				//没有附件
				if(file==null||file.size()<=0){
					contAddAgreement.setAttaBizId("");
				}
				contAddAgreementDao.save(contAddAgreement);
			}
		}
		for(int i=0;i<contLedger.getContDelaies().size();i++){
			if(contLedger.getContDelaies().get(i)!=null){
				ContDelay contDelay=contLedger.getContDelaies().get(i);
				contDelay.setContLedger(contLedger);
				contDelayDao.save(contDelay);
			}
		}
		for(ContRemark contRemark:contLedger.getContRemarks()){
			if(contRemark==null||contRemark.getRemark()==null||"".equals(contRemark.getRemark())){
				continue;
			}
			contRemark.setContLedger(contLedger);
			contRemarkDao.save(contRemark);
		}
	}

	public void deleteContLedger(String id) {
		//删除子记录
		ContLedger ledger = getEntity(id);
		for(ContAddAgreement agree:ledger.getContAddAgreements()){
			contAddAgreementDao.delete(agree);
		}
		for(ContVisaUpdate visa:ledger.getContVisaUpdates()){
			contVisaUpdateDao.delete(visa);
		}
		for(ContPay contPay:ledger.getContPaies()){
			contPayDao.delete(contPay);
		}
		for(ContDelay delay:ledger.getContDelaies()){
			contDelayDao.delete(delay);
		}
		for(ContRemark contRemark:ledger.getContRemarks()){
			contRemarkDao.delete(contRemark);
		}
		contLedgerDao.delete(id);
	}
	public void updateContLedger(String id,String deleteType,String deleteId){
		ContLedger contLedger=getEntity(id);
		PowerUtils.setEmptyStr2Null(contLedger);
		contLedgerDao.save(contLedger);
		if("agree".equals(deleteType)&&deleteId!=null){
			contAddAgreementDao.delete(deleteId);
		}else if("visa".equals(deleteType)&&deleteId!=null){
			contVisaUpdateDao.delete(deleteId);
		}
	}
	//更新合同类别数据
	public void updateData(){
		//得到合同类型
		AppDictType appDictType = appDictTypeManager.findAppDictTypeByCd(DictContants.CONT_ESTATE_TYPE);
		if (appDictType != null) {
			String hql1="from ContContractType where 1=1";
			HashMap<String, Object> map =new HashMap<String, Object>();
			List<ContContractType> typeList = contContractTypeDao.find(hql1,map);
			//List<ContLedger> result = contLedgerDao.find(hql, new HashMap<String, Object>());
			for (AppDictData appDictData : appDictType.getAppDictDatas()) {
				for(Iterator<ContContractType> it = typeList.iterator(); it.hasNext();){
					ContContractType type=it.next();
					if(type.getTypeName().replaceAll(" ", "").equals(appDictData.getDictName().replaceAll(" ", ""))){
						//若果合同类型与数据字典相同，查找相关数据类型的合同台账
						String hql =" from ContLedger where contTypeCd='"+type.getTypeCd()+"'";
						List<ContLedger> result = contLedgerDao.find(hql, map);
						//更新updateCd,再将type的父类CD放到contTypeCd
						if(result!=null&&result.size()>0){
							for(ContLedger ledger:result){
								ledger.setContTypeCd2(appDictData.getDictCd());
								if(type.getTypeParentCd().equals(type.getProjectCd())){
									ledger.setContTypeCd("");
								}else{
									ledger.setContTypeCd(type.getTypeParentCd());
								}
								contLedgerDao.save(ledger);
							}
							//删除type记录
							contContractTypeDao.delete(type.getContContractTypeId());
							it.remove();
						}else{
							//否则，看是否有孩子结点，若没有则删除
							StringBuffer hql2 = new StringBuffer("from ContContractType a where a.typeParentCd='"+type.getTypeCd()+"'");
							List<ContContractType> isTypeList =contContractTypeDao.find(hql2.toString(),map);
							if(isTypeList==null||isTypeList.size()<=0){
								//删除type记录
								contContractTypeDao.delete(type.getContContractTypeId());
								it.remove();
							}
						}
					}
				}
			}
		}
		
		
	}
	
	@Override
	public HibernateDao<ContLedger, String> getDao() {
		return contLedgerDao;
	}
	/**
	 * 若当前日期>计划竣工日期,进度状态为： 延期竣工
	 */
	public void updateContDelayTime(){
		StringBuffer hql=new StringBuffer("select A from ");
		hql.append(ContLedger.class.getSimpleName()+" A where A.procStatus in(0,1,2) ");
		hql.append("and A.planEndDate<:dateNow");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("dateNow", new Date());
		List<ContLedger> result = contLedgerDao.find(hql.toString(), param);
		for(ContLedger ledger:result){
			//3延期竣工
			ledger.setProcStatus("3");
			contLedgerDao.save(ledger);
		}
	}
	/**
	 * 合同审批的台账录入
	 * @param ledgerId:合同台账ID
	 * @param clearPrice:结算价
	 * @param contVisaUpdate:签证变更表
	 * @param contAddAgreement:补充协议表
	 * @param contPay:付款动态表
	 */
	public void updateContByRes(String ledgerId,String ledgerCd,String clearPrice,ContVisaUpdate contVisaUpdate,ContAddAgreement contAddAgreement,ContPay contPay){
		if(StringUtils.isNotBlank(ledgerId)){
			ContLedger contLedger =getEntity(ledgerId);
			/**若签证变更动态有值，则新增签证变更动态数据，并修改合同总价，计算方式：若合同性质为0和1，合同总价为totalPrice+visaTotal+agreeTotal
			 *变更比率为：visaTotal/(totalPrice+agreeTotal)*100；若合同性质为2和3，合同总价：visaTotal+agreeTotal，
			 *变更比率：visaTotal/(agreeTotal)*100
			 **/
			if(StringUtils.isBlank(ledgerCd)){
				ledgerCd ="";
			}
			//重新计算变更后费用
			BigDecimal upTotal =new BigDecimal(0);
			if(contVisaUpdate!=null){
				
				if(StringUtils.isBlank(contVisaUpdate.getVisaUpdateNo())){
					
					int visaCount = 0;
					
					if(contLedger.getContVisaUpdates() != null){
						visaCount = contLedger.getContVisaUpdates().size();
					}
					
					
//					if(contLedger.getContVisaUpdates()!=null){
//						  contVisaUpdate.setVisaUpdateNo(ledgerCd+XOBG+(contLedger.getContVisaUpdates().size()+1));
//					}else{
//						   contVisaUpdate.setVisaUpdateNo(ledgerCd+XOBG+1);
//					}
					String visaUpdateNo = StringUtils.leftPad(String.valueOf(visaCount+1),4, "0");//4位，不够补0;
					contVisaUpdate.setVisaUpdateNo(visaUpdateNo);
					
					  //新增一条记录
					contLedger.getContVisaUpdates().add(contVisaUpdate);
				}
				
				//签证变更累加
				for(int i=0;i<contLedger.getContVisaUpdates().size();i++){
					if(contLedger.getContVisaUpdates().get(i)!=null){
						ContVisaUpdate visa=contLedger.getContVisaUpdates().get(i);
						upTotal =upTotal.add(visa.getAmountUpdate()==null?new BigDecimal(0):visa.getAmountUpdate());
					}
				}
				//若为核定单，在签证变更里新增一条记录
				if(contVisaUpdate.getAmountUpdate()!=null&&contVisaUpdate.getAmountUpdate().compareTo(new BigDecimal(0))!=0
						&&StringUtils.isNotBlank(contVisaUpdate.getVisaUpdateNo())){
					contLedger.getContVisaUpdates().add(contVisaUpdate);
				}
				//更新签证变更合计值
				contLedger.setVisaTotal(upTotal);
			}
			/**
			 * 补充协议若有值，则与上面情况一样，修改合同总价和变更比率
			 */
			//补充协议费用
			BigDecimal agreeTotal =new BigDecimal(0);
			if(contAddAgreement!=null){
				if(StringUtils.isBlank(contAddAgreement.getAddAgreementNo())){
					if(contLedger.getContAddAgreements()!=null){
						contAddAgreement.setAddAgreementNo(ledgerCd+BBHD+(contLedger.getContAddAgreements().size()+1)); 
					}else{
						contAddAgreement.setAddAgreementNo(ledgerCd+BBHD+1); 
					}
				}
				//补充协议累加
				for(int i=0;i<contLedger.getContAddAgreements().size();i++){
					if(contLedger.getContAddAgreements().get(i)!=null){
						ContAddAgreement agree =contLedger.getContAddAgreements().get(i);
						agreeTotal =agreeTotal.add(agree.getAmountUpdate()==null?new BigDecimal(0):agree.getAmountUpdate());
					}
				}
				//若为补充协议核定单，则累加补充协议价
				if(contAddAgreement.getAmountUpdate()!=null&&contAddAgreement.getAmountUpdate().compareTo(new BigDecimal(0))!=0){
					contLedger.getContAddAgreements().add(contAddAgreement);
					//补充协议累计值
					contLedger.setAgreeTotal(agreeTotal);
				}
			}
			
			if(contVisaUpdate!=null||contAddAgreement!=null){
				//已确认合同总价
				BigDecimal totalPrice =new BigDecimal(0);
				upTotal=contLedger.getVisaTotal();
				agreeTotal=contLedger.getAgreeTotal();
				totalPrice = contLedger.getTotalPrice()==null?new BigDecimal(0):contLedger.getTotalPrice();
				contLedger.setUpdateTotal(totalPrice.add(getFormat(upTotal)).add(getFormat(agreeTotal)));
				//变更比率
				BigDecimal updateRate=new BigDecimal(0);
				if(contLedger.getUpdateTotal()!=null&&contLedger.getUpdateTotal().intValue()!=0&&upTotal!=null&&upTotal.intValue()!=0){
					updateRate =upTotal.divide(contLedger.getUpdateTotal(),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
				}
				contLedger.setUpdateRate(updateRate);
			}
			//若付款动态,要注意的是付款小计与付款比率，
			//当期完成产值比率=（甲供+当期支付+付款性质）/已完成产值*100分比
			if(contPay!=null){
				BigDecimal completePay=contPay.getMatieralNum()==null?new BigDecimal(0):contPay.getMatieralNum();
				completePay =completePay.add(contPay.getCurrentPay()==null?new BigDecimal(0):contPay.getCurrentPay());
				completePay =completePay.add(contPay.getCurrentAdd()==null?new BigDecimal(0):contPay.getCurrentAdd());
				contPay.setPayMoney(completePay);
				if(contPay.getCompleteNum()!=null&&contPay.getCompleteNum().compareTo(new BigDecimal(0))!=0){
					contPay.setPayRate(completePay.divide(contPay.getCompleteNum(),2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
				}else{
					contPay.setPayRate(new BigDecimal(0));
				}
				contLedger.getContPaies().add(contPay);
			}
			//若结算价有值，修改结算价并合同状态改为已完已结，并进度状态改为已竣工
			if(clearPrice!=null&&!"".equals(clearPrice)) {
				BigDecimal clear =new BigDecimal(FormatUtil.formatDouble(clearPrice));
				contLedger.setClearPrice(clear);
				contLedger.setContStatus("2");//2为已完已结
				contLedger.setProcStatus("4");//4为已竣工
			}
			saveContLedger(contLedger);
		}
	}
	
	private BigDecimal getFormat(BigDecimal t){
		if(t == null)
			return new BigDecimal(0);
		return t;
	}
	/**
	 * 合同状态总份数，其中：未完未结、已完已结、已完未结各份数
	 * @return
	 */
	public List<LedgerReportUtil> statiContStatus(String projectCd,String ledgerType){
		StringBuffer sql = new StringBuffer();
		sql.append("       select a.cont_status,count(*) from cont_ledger a,cont_project_code b where a.enable_flg = '1' and a.cont_status='0' and a.project_cd = b.project_cd");
		if(StringUtils.isNotBlank(ledgerType)){
			sql.append(" and B.CODE_TYPE  ='").append(ledgerType).append("'");
		}
		if(StringUtils.isNotBlank(projectCd)){
			sql.append(" and a.project_Cd ='").append(projectCd).append("'");
		}
		sql.append("group by a.cont_Status");
		sql.append(" union select a.cont_status,count(*) from cont_ledger a,cont_project_code b where a.enable_flg = '1' and a.cont_status='1' and a.project_cd = b.project_cd");
		if(StringUtils.isNotBlank(ledgerType)){
			sql.append(" and B.CODE_TYPE  ='").append(ledgerType).append("'");
		}
		if(StringUtils.isNotBlank(projectCd)){
			sql.append(" and a.project_Cd ='").append(projectCd).append("'");
		}
		sql.append("group by a.cont_Status");
		sql.append(" union select a.cont_status,count(*) from cont_ledger a,cont_project_code b where a.enable_flg = '1' and a.cont_status='2' and a.project_cd = b.project_cd");
		if(StringUtils.isNotBlank(ledgerType)){
			sql.append(" and B.CODE_TYPE='").append(ledgerType).append("'");
		}
		if(StringUtils.isNotBlank(projectCd)){
			sql.append(" and a.project_Cd='").append(projectCd).append("'");
		}
		sql.append("group by a.cont_Status");
		List<Object[]> result = contLedgerDao.findBySql(sql.toString(), new HashMap<String, Object>());
		List<LedgerReportUtil> objList =new ArrayList<LedgerReportUtil>();
		if(result!=null&&result.size()>0){
			BigDecimal bigTotal =new BigDecimal(0);
			for(Object[] obj:result){
				if(obj[1]!=null){
					bigTotal =bigTotal.add((BigDecimal)obj[1]);
				}
			}
			/*for(int i=0;i<result.size();i++){
				if(result.get(i)==null){
					result.set(i, new BigDecimal(0));
				} else {
					bigTotal =bigTotal.add((BigDecimal)result.get(i));
				}
			}*/
			for(int i=0;i<result.size();i++){
				LedgerReportUtil reportUtil=new LedgerReportUtil();
				Object[] obj=result.get(i);
				if("0".equals(obj[0])){
					reportUtil.setName("未完未结 ,"+obj[1]+"份 ");
				}else if("1".equals(obj[0])){
					reportUtil.setName("已完未结,"+obj[1]+"份 ");
				}else{
					reportUtil.setName("已完已结,"+obj[1]+"份 ");
				}
				if(bigTotal.compareTo(new BigDecimal(0))==0) {
					reportUtil.setY(new BigDecimal(0));
				} else {
					reportUtil.setY(((BigDecimal)obj[1]).divide(bigTotal,2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
				}
				objList.add(reportUtil);
			}
		}
		return objList;
	}
	/**
	 * 合同类别总份数
	 * @return
	 */
	public List<LedgerReportUtil> statiContType(String projectCd,String ledgerType){
		StringBuffer sql =new StringBuffer();
		sql.append(" select a.cont_type_cd2,count(trim(a.cont_ledger_id))");
		sql.append("   from cont_ledger a,cont_project_code b");
		sql.append("  where a.enable_flg = '1' and a.project_cd = b.project_cd");
		if(StringUtils.isNotBlank(projectCd)){
			sql.append(" and a.project_Cd='").append(projectCd).append("'");
		}
		if(StringUtils.isNotBlank(ledgerType)){
			sql.append(" and b.code_Type='").append(ledgerType).append("'");
		}
		sql.append(" group by a.cont_Type_Cd2");
		List result = contLedgerDao.findBySql(sql.toString(), new HashMap<String, Object>());
		List<LedgerReportUtil> objList =new ArrayList<LedgerReportUtil>();
		if(result!=null&&result.size()>0){
			BigDecimal bigTotal =new BigDecimal(0);
			for(int i=0;i<result.size();i++){
				Object[] obj=(Object[])result.get(i);
				if(obj[1]!=null){
					bigTotal =bigTotal.add((BigDecimal)obj[1]);
				}
			}
			Map<String,String> map;
			if("2".equals(ledgerType)){
				map=appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_BIS_TYPE);
			}else if("3".equals(ledgerType)){
				map=appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_HOTEL_TYPE);
			}else{
				map=appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_ESTATE_TYPE);
			}
			for(int i=0;i<result.size();i++){
				LedgerReportUtil reportUtil=new LedgerReportUtil();
				Object[] obj=(Object[])result.get(i);
				if(null==obj[0]){
					reportUtil.setName("无选择类型,"+obj[1]+"份  ");
				}else{
					reportUtil.setName(map.get(obj[0])+","+obj[1]+"份  ");
				}
				if(bigTotal.compareTo(new BigDecimal(0))==0||
						((BigDecimal)obj[1]).compareTo(new BigDecimal(0))==0) {
					reportUtil.setY(new BigDecimal(0));
				} else {
					reportUtil.setY(((BigDecimal)obj[1]).divide(bigTotal,2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
				}
				objList.add(reportUtil);
			}
		}
		return objList;
	}
	/**
	 * 	合同金额统计
	 * @return
	 */
	public List<LedgerReportUtil> statiContTotal(String projectCd,String ledgerType){
		
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("enableFlg", "1");//1-可用
		values.put("projectCd", projectCd);
		values.put("ledgerType", ledgerType);
		
		StringBuffer sql =new StringBuffer();
		sql.append("select a.cont_type_cd2 ,sum(a.update_total)");
		sql.append("  from cont_ledger a,cont_project_code b");
		sql.append(" where a.enable_flg = :enableFlg and a.project_cd = b.project_cd");
		if(StringUtils.isNotBlank(projectCd)){
			sql.append(" and a.project_cd = :projectCd ");
		}
		if(StringUtils.isNotBlank(ledgerType)){
			sql.append(" and b.code_type = :ledgerType ");
		}
		sql.append(" group by a.cont_Type_Cd2");
		
		List result = contLedgerDao.findBySql(sql.toString(), values);
		List<LedgerReportUtil> objList =new ArrayList<LedgerReportUtil>();
		if(result!=null&&result.size()>0){
			BigDecimal bigTotal =new BigDecimal(0);
			for(int i=0;i<result.size();i++){
				Object[] obj=(Object[])result.get(i);
				if(obj[1]!=null){
					bigTotal =bigTotal.add((BigDecimal)obj[1]);
				}
			}
			Map<String,String> map;
			if("2".equals(ledgerType)){
				map=appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_BIS_TYPE);
			}else if("3".equals(ledgerType)){
				map=appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_HOTEL_TYPE);
			}else{
				map=appDictTypeManager.getDictDataByTypeCd(DictContants.CONT_ESTATE_TYPE);
			}
			for(int i=0;i<result.size();i++){
				LedgerReportUtil reportUtil=new LedgerReportUtil();
				Object[] obj=(Object[])result.get(i);
				if(null==obj[0]){
					reportUtil.setName("无选择类型,"+obj[1]+"元  ");
				}else{
					reportUtil.setName(map.get(obj[0])+","+obj[1]+"元  ");
				}
				if(bigTotal.compareTo(new BigDecimal(0))==0||
						((BigDecimal)obj[1]).compareTo(new BigDecimal(0))==0) {
					reportUtil.setY(new BigDecimal(0));
				} else {
					reportUtil.setY(((BigDecimal)obj[1]).divide(bigTotal,2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
				}
				objList.add(reportUtil);
			}
		}
		return objList;
	}
	public List<LedgerReportUtil> statiContProc(String projectCd,String ledgerType){
		
		Map<String,Object> values = new HashMap<String, Object>();
		values.put("enableFlg", "1");//1-可用
		values.put("projectCd", projectCd);
		values.put("ledgerType",  ledgerType);
		
		StringBuffer sql =new StringBuffer();
		sql.append("select trim(a.proc_Status),count(A.cont_ledger_id)");
		sql.append("  from cont_ledger a,Cont_Project_Code b ");
		sql.append(" where a.enable_flg = :enableFlg  and a.project_Cd = b.project_Cd ");
		if(StringUtils.isNotBlank(projectCd)){
			sql.append(" and a.project_Cd = :projectCd ");
		}
		if(StringUtils.isNotBlank(ledgerType)){
			sql.append(" and b.code_Type  = :ledgerType ");
		}
		sql.append(" group by a.proc_Status order by a.proc_Status");
		List result = contLedgerDao.findBySql(sql.toString(), values);
		List<LedgerReportUtil> objList =new ArrayList<LedgerReportUtil>();
		if(result!=null&&result.size()>0){
			BigDecimal bigTotal =new BigDecimal(0);
			for(int i=0;i<result.size();i++){
				Object[] obj=(Object[])result.get(i);
				if(obj[1]!=null){
					bigTotal =bigTotal.add((BigDecimal)obj[1]);
				}
			}
			for(int i=0;i<result.size();i++){
				LedgerReportUtil reportUtil=new LedgerReportUtil();
				Object[] obj=(Object[])result.get(i);
				String name =(String)obj[0];
				if("0".equals(name)){
					reportUtil.setName("未开工"+","+obj[1]+"份  ");
				}else if("1".equals(name)){
					reportUtil.setName("按期"+","+obj[1]+"份  ");
				}else if("2".equals(name)){
					reportUtil.setName("滞后"+","+obj[1]+"份  ");
				}else if("3".equals(name)){
					reportUtil.setName("延期竣工"+","+obj[1]+"份  ");
				}else if("4".equals(name)){
					reportUtil.setName("已竣工"+","+obj[1]+"份  ");
				}
				
				if(bigTotal.compareTo(new BigDecimal(0))==0||
						((BigDecimal)obj[1]).compareTo(new BigDecimal(0))==0) {
					reportUtil.setY(new BigDecimal(0));
				} else {
					reportUtil.setY(((BigDecimal)obj[1]).divide(bigTotal,2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
				}
				objList.add(reportUtil);
			}
		}
		return objList;
	}
	
	public ContLedger findContLedgerByContNo(String contNo) {
		String hql=" from ContLedger a where a.contNo = :contNo";
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("contNo", contNo);
		List<ContLedger> l=contLedgerDao.find(hql, map);
		if(l!=null&&l.size()>0)
			return l.get(0);
		else
			return null;
		
	}
	
	/**
	 * 网批导入合同台账
	 * @param bill 网批表单(战略采购订单审批表)
	 */
	public void importBill(StrategyPurchaseBill bill) {

		String contractNo= bill.getContractNo().trim();
		String contractPrice = bill.getContractPrice().trim();
		
		String contractNo2= bill.getContractNo2().trim();
		String contractPrice2 = bill.getContractPrice2().trim();

		ContProjectCodeManager projectCodeManager = SpringContextHolder.getBean("contProjectCodeManager");
		List<ContProjectCode> projectCodeList = projectCodeManager.getAll();
		
		//若合同编号不空
		if(StringUtils.isNotBlank(contractNo)){
			saveContLedger(bill, contractNo, contractPrice, projectCodeList);
		}
		//若合同编号2不空
		if(StringUtils.isNotBlank(contractNo2)){
			saveContLedger(bill, contractNo2, contractPrice2, projectCodeList);
		}
	}
	
	private void saveContLedger(StrategyPurchaseBill bill, String contractNo,String contractPrice,List<ContProjectCode> projectCodeList){

		String projectCd = bill.getProjectCd();
		String titleName = bill.getTitleName();

		String partA = bill.getPartA();
		String partB = bill.getPartB();
		String partC = bill.getPartC();
		String partBReal = bill.getPartBReal();
//		String stargeFlg = bill.getStargeFlg();
		
		String enterDate = bill.getEnterDate();
		
		ContLedger tmpLedger = new ContLedger();
		// 寻找projectCd，若在合同类别里有projectCd,则新增该项目，若无则不setProjectCd
		boolean haveProject = false;
		for (ContProjectCode code : projectCodeList) {
			if (StringUtils.isNotBlank(projectCd) && projectCd.equals(code.getProjectCd())) {
				haveProject = true;
				break;
			}
		}
		if (haveProject) {
			tmpLedger.setProjectCd(projectCd);
		}
		
		//合同名称
		tmpLedger.setContName(titleName);
		//合同编号
		tmpLedger.setContNo(contractNo);
		
		tmpLedger.setPartA(partA);
		tmpLedger.setPartB(partB);
		tmpLedger.setPartC(partC);

		tmpLedger.setRealProvName(partBReal); // 实际供方  -Add by liuzhihui 2012-04-01
		tmpLedger.setStrageFlg("1");//1-战略 //是否战略   -Add by liuzhihui 2012-04-01
		
		//合同总价 ：审定价
		if(StringUtils.isNotBlank(contractPrice)){
			String totalPrice = contractPrice.replace(",", "");
			//合同总价
			tmpLedger.setTotalPrice(new BigDecimal(totalPrice));
			//已确认合同总价
			tmpLedger.setUpdateTotal(tmpLedger.getTotalPrice());
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			//计划开工日期
			tmpLedger.setPlanBeginDate(format.parse(enterDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//共多少天
		//contLedger.setPeriod(totalDay);
		//付款方式
//		contLedger.setPayWay(paymentType);
		//中标单位
		//contLedger.setPartB(bidUnit);
		//中标价successfulBidPrice
		//进度状态默认为未开工
		tmpLedger.setProcStatus("0");
		//合同状态默认为未完未结
		tmpLedger.setContStatus("0");
		//带入网批CD和ID
		

		ResApproveInfo res = bill.getResApproveInfo();
		tmpLedger.setResApproveCd(res.getApproveCd() + res.getSerialNo());
		tmpLedger.setResApproveId(res.getResApproveInfoId());
		
		saveContLedger(tmpLedger);
	}
	
	/**
	 * 导入合同台账(战略材料设备网上下单)
	 * @param bean
	 * @param projectCd
	 * @return
	 */
	public boolean importStrageBill(StrategyOnlineOrder bean, String projectCd){
		
		if(StringUtils.isNotBlank(projectCd)){
			bean.setProjectCd(projectCd);
		}

		//合同1
		importStrageBill(bean, bean.getContractNo(), bean.getContractPrice());
		//合同2
		importStrageBill(bean, bean.getContractNo2(), bean.getContractPrice2());
		
		return true;
	}
	
	private void importStrageBill(StrategyOnlineOrder bean, String contNo, String contAmt){
		
		if(StringUtils.isBlank(contNo)){
			log.error("保存合同台账，传入contNo为空!");
			return;
		}

//		合同名称
//		contLedger.setContName(bean.getTitleName()
//		contLedger.setRealProvName(bean.getPartBReal());
//		if (StringUtils.isNotBlank(stargeFlg1) && "true".equals(stargeFlg1)) {
//			contLedger.setStrageFlg("1");
//		}
//		if (StringUtils.isNotBlank(stargeFlg2) && "true".equals(stargeFlg2)) {
//			contLedger.setStrageFlg("0");
//		}
		//共多少天
		//contLedger.setPeriod(totalDay);
		//付款方式
		//contLedger.setPayWay(paymentType);
		//中标单位
		//contLedger.setPartB(bidUnit);
		//中标价successfulBidPrice

		ContLedger contLedger = new ContLedger();
		contLedger.setProjectCd(bean.getProjectCd());

		//合同编号
		contLedger.setContNo(contNo);
		//合同总价 ：审定价
		String totalPrice = null;
		if(StringUtils.isNotBlank(contAmt)){
			totalPrice = contAmt.replace(",", "");
			//合同总价
			contLedger.setTotalPrice(new BigDecimal(totalPrice));
			//已确认合同总价
			contLedger.setUpdateTotal(contLedger.getTotalPrice());
		}

		contLedger.setPartA(bean.getPartA());
		contLedger.setPartB(bean.getPartB());
		contLedger.setPartC(bean.getPartC());
		contLedger.setRealProvName(bean.getPartBReal());
		contLedger.setStrageFlg("1");//1-战略
		
		if(StringUtils.isNotBlank(bean.getEnterDate())){
			//计划开工日期
			try {
				contLedger.setPlanBeginDate(new SimpleDateFormat("yyyy-MM-dd").parse(bean.getEnterDate()));
			} catch (ParseException e) {
				e.printStackTrace();
				log.error("计划开工日期 转换异常!",e);
			}
		}
		//带入网批CD和ID
		ResApproveInfo t = bean.getResApproveInfo();
		contLedger.setResApproveCd(t.getApproveCd() + t.getSerialNo());
		contLedger.setResApproveId(t.getResApproveInfoId());

		
		//进度状态默认为未开工
		contLedger.setProcStatus("0");
		//合同状态默认为未完未结
		contLedger.setContStatus("0");
		
		//保存合同台账
		saveContLedger(contLedger); 
	}

	/**
	 * 验证"合同"是否重复
	 * @param contLedgerId
	 * @param contNo
	 * @return true -重复  false-不重复
	 */
	public boolean validateContNo(String contLedgerId, String contNo){
		StringBuffer sb = new StringBuffer();
		sb.append(" from ContLedger t where t.contNo = :contNo ");
		if(StringUtils.isNotBlank(contLedgerId)){
			sb.append(" and t.contLedgerId <> :contLedgerId ");
		}
		Map<String,Object> values = new HashMap<String,Object>();
		values.put("contNo", contNo);
		values.put("contLedgerId", contLedgerId);
		long t = getDao().countHqlResult(sb.toString(), values);
		if(t == 0)
			return false;
		else
			return true;
	}
}

