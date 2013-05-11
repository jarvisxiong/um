package com.hhz.ump.dao.assm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.DateOperator;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.entity.assm.AssmAccount;
import com.hhz.ump.entity.assm.AssmDepreciation;
import com.hhz.ump.entity.assm.AssmModel;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DateUtil;
import com.hhz.ump.web.res.bean.AssetsChangeSheetSyProp;
import com.hhz.ump.web.res.bean.AssetsSaleSheetSyProp;
import com.hhz.ump.web.res.bean.FixedAssetsMoveSyAsset;
import com.hhz.ump.web.res.bean.FixedAssetsProperty;

@Service
@Transactional
public class AssmAccountManager extends BaseService<AssmAccount, String> {
	
	private static Log log = LogFactory.getLog(AssmAccountManager.class);
	
	@Autowired
	private AssmAccountDao assmAccountDao;
	@Autowired
	private AssmModelDao assmModelDao;
	@Autowired
	private AssmModelManager assmModelManager;
	@Autowired
	private AssmModelStandardManager assmModelStandardManager;
	@Autowired
	private AssmDepreciationManager assmDepreciationManager;
	@Autowired
	private BisProjectManager bisProjectManager;
	

	public void saveAssmAccount(AssmAccount assmAccount) {
		PowerUtils.setEmptyStr2Null(assmAccount);
		assmAccountDao.save(assmAccount);
	}
	
	public void deleteAssmAccount(String id) {
		assmAccountDao.delete(id);
	}
	
	@Override
	public HibernateDao<AssmAccount, String> getDao() {
		return assmAccountDao;
	}
	
	/**
	 * 自动更新资产残值(每月最后一天凌晨5:00自动执行)
	 *  配置详细见：applicationContext-quartz.xml(assmAccountsTrigger)
	 */
	public void assmAccounts(){
		updateDepreYeadNum(null);
	}

	/**
	 * 
	 * 更新折旧后的金额
	 *     折算公式：残值=原值-((原值*(年折旧率/12))*使用月数)
	 * @throws Exception
	 */
	public void updateDepreYeadNum(AssmAccount account){
		long start_dime = System.currentTimeMillis();
		List<AssmAccount> list = new ArrayList<AssmAccount>();
		if (account != null) {
			list.add(account);
		}else{
			list = this.getAssmAccountList();
		}
		if (list != null && list.size() > 0) {
			Date currDate = new Date();
			AssmModel thridModel = null; 
			AssmModel sencodeModel = null; 
			AssmDepreciation depreciation = null;
			for (AssmAccount tAccount : list) {
				//资产原值
				BigDecimal srcValue = tAccount.getSrcValue();
				//资产残值
				//BigDecimal srcRemainValue = tAccount.getRemainVal();
				//开始使用日期
				Date useDate = tAccount.getUseDate();
				
				//开始使用日期在当前日期前才折旧
				if (useDate.before(currDate)) {
					//当前日期隔使用日期的月数
					int monthCount = DateUtil.getMonthCountDate(useDate,currDate);
					if (monthCount > 0) {
						BigDecimal remainVal = new BigDecimal(0);
						thridModel = assmModelDao.get(tAccount.getAssmModel().getPratentId());
						if (thridModel != null) {
							sencodeModel = assmModelDao.get(thridModel.getPratentId());
						}
						if (sencodeModel != null) {
							depreciation = assmDepreciationManager.getAssmDepreciationByModelId(sencodeModel.getAssmModelId());
						}
						if (depreciation != null) {
							BigDecimal monthRateNum = new BigDecimal(0);
							BigDecimal monthNum = new BigDecimal(0);
							//折旧总月
							long deprePeriodCount = depreciation.getDeprePeriod()*12;
							
							//当使用月数总和(当前时间-使用日期)小于折旧月数时才折旧
							if(monthCount < deprePeriodCount){
								//月折旧率
								BigDecimal monthRate = depreciation.getDepreRate().divide(new BigDecimal(100)).divide(new BigDecimal(12),15,RoundingMode.HALF_UP);
								//月折旧金额
								monthRateNum = srcValue.multiply(monthRate);
								monthNum = BigDecimal.valueOf(monthCount);
								// 残值 = 原值-(月折旧金额*已使用月数)
								remainVal = srcValue.subtract(monthRateNum.multiply(monthNum)).setScale(2,BigDecimal.ROUND_HALF_UP);
								
								//如果折旧后的残值等于0或者大于现有的残值则将残值置为0
								if(remainVal.compareTo(srcValue) == 1 || remainVal.compareTo(BigDecimal.ZERO) == -1){
									remainVal = new BigDecimal(0);
								}
							}else{
								//当使用月数总和(当前时间-使用日期)大于等于折旧月数时残值设为0
								remainVal = new BigDecimal(0);
							}
							tAccount.setRemainVal(remainVal);
							saveAssmAccount(tAccount);
						}
					}else if(monthCount==0){
						tAccount.setRemainVal(srcValue);
						saveAssmAccount(tAccount);
					}
				}else{
					tAccount.setRemainVal(srcValue);
					saveAssmAccount(tAccount);
				}
			}
			long end_time = System.currentTimeMillis();
			log.info("[自动更新资产残值成功]: 用时" + (end_time - start_dime)/1000.00 +" 秒!");
		}else{
			log.info("[自动更新资产残值]：无残值更新数据!");
		}
	}
	
	/**
	 * 得到符合条件的能折旧的资产(报废和残值为0的资产不在折旧)
	 * @param useStatus 使用状态
	 * @param remainVal 残值
	 * @return
	 */
	public List<AssmAccount> getAssmAccountList(){
		Map<String, Object> values = new HashMap<String,Object>();
		String hql = "from AssmAccount t where t.useStatus <> :useStatus and t.remainVal <> :remainVal";
		values.put("useStatus", "3"); //useStatus==3 报废
		values.put("remainVal", new BigDecimal(0));
		List<AssmAccount> list = assmAccountDao.find(hql, values);
		return list;
	}
	
	
	/**
	 * 校验资产编号是否唯一
	 * @param code 
	 * @return
	 */
	public boolean checkAccountByCode(String code){
		boolean flag = false;
		Map<String, Object> values = new HashMap<String,Object>();
		String hql = "from AssmAccount t where t.code= :code";
		values.put("code", code);
		List<AssmAccount> list = assmAccountDao.find(hql, values);
		if (list != null && list.size() > 0) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 校验 同一“保管人员”不能拥有两个或两个以上的设备
	 * @param keeperCd 用户uiid 
	 * @return
	 */
	public boolean checkAccountByKeepCd(String keeperCd){
		boolean flag = false;
		Map<String, Object> values = new HashMap<String,Object>();
		//得到三级设备的‘电脑’类下的子类id
		String[] modelIds = assmModelManager.getComputerTypeModel();
		if (modelIds != null && modelIds.length != 0) {
			String hql = "from AssmAccount t where t.keeperCd= :keeperCd and t.assmModel.assmModelId in(:modelIds)";
			values.put("keeperCd", keeperCd);
			values.put("modelIds", modelIds);
			List<AssmAccount> list = assmAccountDao.find(hql, values);
			if (list != null && list.size() >= 1) {
				flag = true;
			}
		}
		return flag;
	}
	public List<ResApproveInfoVo> loadResInfoByIds(String resIds){
		List<ResApproveInfoVo> resuls=new ArrayList<ResApproveInfoVo>();
		if (StringUtils.isNotBlank(resIds)){
			String[] strResIds=resIds.split(";");
			StringBuffer hql=new StringBuffer("select r.resApproveInfoId,r.displayNo,r.authTypeCd,r.completeDate from ResApproveInfo r where r.resApproveInfoId in (:resIds) order by r.completeDate desc ");
			Map<String,Object> values=new HashMap<String, Object>();
			values.put("resIds", strResIds);
			List list=find(hql.toString(), values);
			for (Object obj : list) {
				Object[] objs=(Object[])obj;
				resuls.add(new ResApproveInfoVo((String)objs[0], (Long)objs[1],CodeNameUtil.getResAuthTypeNameByCd((String)objs[2]),(Date)objs[3]));
			}
		}
		return resuls;
	}
	/**
	 * 根据设备ID和项目CD(商业公司)得到型号编码、折旧年限、
	 * 折旧方式、当前配置、标准配置等
	 * @param modelId
	 * @param projectCd
	 * @return
	 */
	public String getValues(String modelId,String projectCd){
		//第四级设备
		AssmModel model = assmModelDao.get(modelId);
		StringBuffer sb = new StringBuffer();
		String value = "";
		if (model != null) {
			//第三级设备
			AssmModel parentModel = assmModelDao.get(model.getPratentId());
			if (parentModel != null) {
				//搜索商业公司
				BisProject bis = bisProjectManager.getBisProjectByOrgCd(projectCd);
				if (bis != null) {
					//配置
					AssmModelStandardVo vo = assmModelStandardManager.getAssmModelStandardByCont(bis.getBisProjectId(), parentModel.getAssmModelId());
					if (vo!=null) {
						sb.append(model.getAssmCode()+","). //设备型号编码
						   append(vo.getAssmModelStandardId()+","). //配置表ID
						   append(vo.getStanNum().setScale(0,BigDecimal.ROUND_HALF_UP)+","). //标准配置
						   append(vo.getHasNum().setScale(0,BigDecimal.ROUND_HALF_UP)+",");  //当前配置
						//第二级设备
						AssmModel parentModel2 = assmModelDao.get(parentModel.getPratentId());
						if (parentModel2 != null) {
							//第二级设备的折旧
							AssmDepreciation depreciation = assmDepreciationManager.getAssmDepreciationByModelId(parentModel2.getAssmModelId());
							if (depreciation != null) {
								sb.append(depreciation.getDeprePeriod()+","). //折旧年限
								append(parentModel2.getAssmName()+"("+depreciation.getDeprePeriod()+"年)"); //折旧方式(二级设备名称+折旧年限)
							}
						}
						value = sb.toString();
					}
				}else{
					value = "error";
				}
			}
		}
		return value;
	}
	/**
	 * 网批资产调拨单
	 * @param assets
	 * @param resId
	 */
	public void updateUserDeptByRes(List<FixedAssetsMoveSyAsset> assets, String resId) {
		for (FixedAssetsMoveSyAsset asset : assets) {
			if (StringUtils.isNotBlank(asset.getAssmAccountId())) {
				AssmAccount account = getEntity(asset.getAssmAccountId());
				account.setUseDepartment(asset.getInDeptCd());
				account.setUseDate(DateOperator.parse(asset.getMoveDate()));
				account.setAddWay(asset.getAddWay());
				addResId(account,resId);
				saveAssmAccount(account);
			}
		}
	}
	/**
	 * 办公资产维修/更换审批单(商业公司/总部)
	 * @param assets
	 * @param resId
	 */
	public void updateResRef(List<AssetsChangeSheetSyProp> assets, String resId) {
		for (AssetsChangeSheetSyProp asset : assets) {
			if (StringUtils.isNotBlank(asset.getAssmAccountId())) {
				AssmAccount account = getEntity(asset.getAssmAccountId());
				addResId(account,resId);
				saveAssmAccount(account);
			}
		}
	}
	/**
	 * 办公资产出售/报废/遗失申请单-商业
	 * @param assets
	 * @param resId
	 */
	public void updateResRef2(List<AssetsSaleSheetSyProp> assets, String resId) {
		for (AssetsSaleSheetSyProp asset : assets) {
			if (StringUtils.isNotBlank(asset.getAssmAccountId())) {
				AssmAccount account = getEntity(asset.getAssmAccountId());
				String useStatus=account.getUseStatus();
				//出售，报废，遗失
				if (StringUtils.equals(asset.getSaleType1(), "true")){
					useStatus="6";//出售
				}else if (StringUtils.equals(asset.getSaleType2(), "true")){
					useStatus="3";//报废
				}else if (StringUtils.equals(asset.getSaleType3(), "true")){
					useStatus="7";//遗失
				}
				account.setUseStatus(useStatus);//使用情况
				addResId(account,resId);
				saveAssmAccount(account);
			}
		}
	}

	private void addResId(AssmAccount account, String resId) {
		if (!StringUtils.contains(account.getResIds(), resId)) {
			if (StringUtils.isNotBlank(account.getResIds())) {
				account.setResIds(account.getResIds() + ";" + resId);
			} else {
				account.setResIds(resId);
			}
		}
	}
	/**
	 * 网批自动导入资产
	 * @param fixedAssetsProperties
	 * @param resId 网批ID
	 * @throws Exception
	 */
	public void saveAssmAccountForRes(List<FixedAssetsProperty> fixedAssetsProperties,String resId) throws Exception{
		if (fixedAssetsProperties != null && fixedAssetsProperties.size() > 0) {
			AssmAccount assmAccount = null;
			AssmModel assmModel = null;
			String useStatus = null;
			String storageSites = null;
			String addWay = null;
			BigDecimal srcValue = new BigDecimal(0); // 残值
			BigDecimal remainVal = new BigDecimal(0); //净值
			BigDecimal depreYeadNum = new BigDecimal(0);
			for (FixedAssetsProperty tmpFixedAsset : fixedAssetsProperties) {
				assmAccount = new AssmAccount();
				assmAccount.setProjectCd(tmpFixedAsset.getProjectCd());
				if (StringUtils.isNotBlank(tmpFixedAsset.getAssmModelId())) {
					assmModel = assmModelDao.get(tmpFixedAsset.getAssmModelId());
					assmAccount.setAssmModel(assmModel);
				}
				assmAccount.setAssmCode(tmpFixedAsset.getAssmCode());
				assmAccount.setAssmName(tmpFixedAsset.getAssmName());
				assmAccount.setCode(tmpFixedAsset.getCode());
				assmAccount.setUseDepartment(tmpFixedAsset.getUseDepartCd());
				if(StringUtils.isNotBlank(tmpFixedAsset.getUseStatus1())){
					if("true".equals(tmpFixedAsset.getUseStatus1())){
						useStatus = "1";
					}
				}
				if(StringUtils.isNotBlank(tmpFixedAsset.getUseStatus2())){
					if("true".equals(tmpFixedAsset.getUseStatus2())){
						useStatus = "2";
					}
				}
				if(StringUtils.isNotBlank(tmpFixedAsset.getUseStatus3())){
					if("true".equals(tmpFixedAsset.getUseStatus3())){
						useStatus = "3";
					}
				}
				if(StringUtils.isNotBlank(tmpFixedAsset.getUseStatus4())){
					if("true".equals(tmpFixedAsset.getUseStatus4())){
						useStatus = "4";
					}
				}
				if(StringUtils.isNotBlank(tmpFixedAsset.getUseStatus5())){
					if("true".equals(tmpFixedAsset.getUseStatus5())){
						useStatus = "5";
					}
				}
				assmAccount.setUseStatus(useStatus);
				if(StringUtils.isNotBlank(tmpFixedAsset.getStorageSites1())){
					if("true".equals(tmpFixedAsset.getStorageSites1())){
						storageSites = "1";
					}
				}
				if(StringUtils.isNotBlank(tmpFixedAsset.getStorageSites2())){
					if("true".equals(tmpFixedAsset.getStorageSites2())){
						storageSites = "2";
					}
				}
				if(StringUtils.isNotBlank(tmpFixedAsset.getStorageSites3())){
					if("true".equals(tmpFixedAsset.getStorageSites3())){
						storageSites = "3";
					}
				}
				assmAccount.setStorageSites(storageSites);
				if(StringUtils.isNotBlank(tmpFixedAsset.getAddWay1())){
					if("true".equals(tmpFixedAsset.getAddWay1())){
						addWay = "1";
					}
				}
				if(StringUtils.isNotBlank(tmpFixedAsset.getAddWay2())){
					if("true".equals(tmpFixedAsset.getAddWay2())){
						addWay = "2";
					}
				}
				if(StringUtils.isNotBlank(tmpFixedAsset.getAddWay3())){
					if("true".equals(tmpFixedAsset.getAddWay3())){
						addWay = "3";
					}
				}
				if(StringUtils.isNotBlank(tmpFixedAsset.getAddWay4())){
					if("true".equals(tmpFixedAsset.getAddWay4())){
						addWay = "4";
					}
				}
				if(StringUtils.isNotBlank(tmpFixedAsset.getAddWay5())){
					if("true".equals(tmpFixedAsset.getAddWay5())){
						addWay = "5";
					}
				}
				if(StringUtils.isNotBlank(tmpFixedAsset.getAddWay6())){
					if("true".equals(tmpFixedAsset.getAddWay6())){
						addWay = "6";
					}
				}
				if(StringUtils.isNotBlank(tmpFixedAsset.getAddWay7())){
					if("true".equals(tmpFixedAsset.getAddWay7())){
						addWay = "7";
					}
				}
				assmAccount.setAddWay(addWay);
				if(StringUtils.isNotBlank(tmpFixedAsset.getKeeperCd()) && StringUtils.isNotBlank(tmpFixedAsset.getKeeperName())){
					assmAccount.setKeeperCd(tmpFixedAsset.getKeeperCd());
					assmAccount.setKeeperName(tmpFixedAsset.getKeeperName());
				}
				if(StringUtils.isNotBlank(tmpFixedAsset.getKeeperName2())){
					assmAccount.setKeeperName2(tmpFixedAsset.getKeeperName2());
				}
				if(StringUtils.isNotBlank(tmpFixedAsset.getSrcValue())){
					String strSrc = tmpFixedAsset.getSrcValue().replace(",","");
					srcValue = BigDecimal.valueOf(Double.valueOf(strSrc));
				}
				assmAccount.setSrcValue(srcValue);
				if(StringUtils.isNotBlank(tmpFixedAsset.getNetValue())){
					String strNet = tmpFixedAsset.getNetValue().replace(",","");
					remainVal = BigDecimal.valueOf(Double.valueOf(strNet));
				}
				assmAccount.setRemainVal(remainVal);
				if(StringUtils.isNotBlank(tmpFixedAsset.getDepreYeadNum())){
					depreYeadNum = BigDecimal.valueOf(Double.valueOf(tmpFixedAsset.getDepreYeadNum()));
				}
				assmAccount.setDepreYeadNum(depreYeadNum);
				if(StringUtils.isNotBlank(tmpFixedAsset.getUseDate())){
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");        
					Date date = format.parse(tmpFixedAsset.getUseDate());;   
					assmAccount.setUseDate(date);
				}
				assmAccount.setDepreWay(tmpFixedAsset.getDepreWay());
				if(StringUtils.isNotBlank(resId)){
					assmAccount.setResIds(resId);
				}
				this.saveAssmAccount(assmAccount);
				//使用情况为报废的不折旧 useStatus='3'
				if(StringUtils.isNotBlank(assmAccount.getUseStatus()) && !"3".equals(assmAccount.getUseStatus())){
					//保存完成后自动折旧
					this.updateDepreYeadNum(assmAccount);
				}
			}
		}
	}
	
}

