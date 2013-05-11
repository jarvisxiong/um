/**
 *
 */
package com.hhz.ump.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springside.modules.spring.SpringContextHolder;

import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.biz.BizBankAccountManager;

/**
 * @author huangj 2011-1-6
 */
public class DictMapUtil {
	private static AppDictTypeManager appDictTypeManager = SpringContextHolder.getBean("appDictTypeManager");
	private static BizBankAccountManager bizBankAccountManager  = SpringContextHolder.getBean("bizBankAccountManager");

	/**
	 * 交通方式
	 *
	 * @return
	 */
	public static Map<String, String> getMapTravelWay() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.JBPM_TRAVEL_WAY);
	}
	/**
	 * 入账公司
	 *
	 * @return
	 */
	public static Map<String, String> getMapRecordedCompany() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.RES_FEE_RECORDED_COMPANY);
	}

	/**
	 * 是否有效
	 *
	 * @return
	 */
	public static Map<Boolean, String> getMapActive() {
		Map<Boolean, String> mapActive = new HashMap<Boolean, String>();
		mapActive.put(null, "");
		mapActive.put(false, "无效");
		mapActive.put(true, "有效");
		return mapActive;
	}
	public static Map<Boolean, String> getMapActive2() {
		Map<Boolean, String> mapActive = new HashMap<Boolean, String>();
		mapActive.put(null, "");
		mapActive.put(false, "否");
		mapActive.put(true, "是");
		return mapActive;
	}
	public static Map<String, String> getMapEnableFlgNum() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("", "");
		map.put("1", "是");
		map.put("0", "否");
		return map;
	}
	//公寓（住宅底商）收费记录-费用类别
	public static Map<String, String> getMapGyCostType() {
		Map<String, String> mapAll = appDictTypeManager.getDictDataByTypeCdB(DictContants.BIS_CHARGE_TYPE);
		Map<String, String> map = new LinkedHashMap<String, String>();
		//4、5、6分别为物管费、水费、电费
		for (Entry<String, String> entry : mapAll.entrySet()) {
			if("4".equals(entry.getKey())){
				map.put(entry.getKey(), entry.getValue());
			}
			if("5".equals(entry.getKey())){
				map.put(entry.getKey(), entry.getValue());
			}
			if("6".equals(entry.getKey())){
				map.put(entry.getKey(), entry.getValue());
			}
		}
		return map;
	}

	/**
	 * 成本工作招标进度
	 *
	 * @return
	 */
	public static Map<String, String> getMapCostBidSche() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.COST_BID_SCHE);
	}
	
	public static Map<String,String> getMapCostBisSche(){
		return appDictTypeManager.getDictDataByTypeCd(DictContants.COST_BIS_SCHE);
	}
	/**
	 * 假期类别
	 *
	 * @return
	 */
	public static Map<String, String> getMapHolidayType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.RES_HOLIDAY_TYPE);
	}

	/**
	 * 假期单位
	 *
	 * @return
	 */
	public static Map<String, String> getMapHolidayUnit() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.RES_HOLIDAY_UNIT);
	}

	//成本工作采购进度
	public static Map<String, String> getMapCostPurSche() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.COST_PUR_SCHE);
	}
	//战略合作
	public static Map<String, String> getMapCostStratage() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.COST_PUR_STRA);
	}
	//标后核对
	public static Map<String, String> getMapCostChkSche() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.COST_CHK_SCHE);
	}
	public static Map<String, String> getMapCostChkSche(boolean bContainEmptyFlag) {
		Map<String, String> map = appDictTypeManager.getDictDataByTypeCd(DictContants.COST_CHK_SCHE);
		if(!bContainEmptyFlag && map != null){
			map.remove("");
		}
		return map;
	}
	//机构类型
	public static Map<String, String> getMapOrgType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_ORG_TYPE);
	}
	//是否上市
	public static Map<String,String> getMapPubFlg(){
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_PUB_FLG);
	}
	//性别
	public static Map<String,String> getMapSexCd(){
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_SEX);
	}
	//权限职级
	public static Map<String,String> getMapPermLevel(){
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_PERM_LEVEL);
	}
	//用户状态
	public static Map<String,String> getMapUserStatus(){
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_USER_STATUS);
	}
	//用户信息来源
	public static Map<String, String> getMapSourceType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_SOURCE_TYPE);
	}
	//员工状态
	public static Map<String, String> getMapServiceStatus() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_SERVICE_STATUS);
	}

	//婚姻状况
	public static Map<String, String> getMapMarrigeStatus() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_MARRIGE_STATUS);
	}

	//用户类型
	public static Map<String, String> getMapMemberType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_MEMBER_TYPE);
	}

	//民族
	public static Map<String, String> getMapNation() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_NATION);
	}


	//其他
	public static Map<String, String> getMapOtherType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_OTHER_TYPE);
	}

	//政治面貌
	public static Map<String, String> getMapPolitics() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_POLITICS);
	}

	//职位
	public static Map<String, String> getMapPosition() {
		Map<String, String> positionMap = appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_POSITION_TYPE);
		positionMap.keySet().remove(DictContants.COM_KEY_CD_EMPTY);
		return  positionMap;
	}

	//职称
	public static Map<String, String> getMapProfessionType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_PROFESSION_TYPE);
	}

	//学历
	public static Map<String, String> getMapSchoolRecord() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_SCHOOL_RECORD);
	}

	//性别
	public static Map<String, String> getMapSex() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_SEX);
	}

	//账户状态
	public static Map<String, String> getMapAcctStatus() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_USER_STATUS);
	}

	//证件类型
	public static Map<String, String> getMapIdCardType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_ID_CARD_TYPE);
	}

	//特殊用户标识
	public static Map<String, String> getMapSpecialUserFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_SPECIAL_USER_FLG);
	}

	//用户类型
	public static Map<String, String> getMapUserType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_USER_TYPE);
	}


	//认证类型
	public static Map<String, String> getMapAuthenticType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.UAAP_AUTHENTIC_TYPE);
	}
	//菜单类型
	public static Map<String, String> getMapAppMenuType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.APP_MENU_TYPE);
	}
	//菜单是否可用
	public static Map<String, String> getMapEnabledFlg() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.COM_ENABLED_FLG);
	}
//	//职级
//	public static Map<String, String> getMapPermissionLevel() {
//		return PlasCache.getDictDataMap(DictContants.UAAP_PERM_LEVEL);
//	}

	/**
	 * 加载功能类型列表
	 *
	 * @return
	 */
	public static Map<String, String> getMapAppFunctionType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.APP_FUNCTION_TYPE);
	}
	/**
	 * 公共关系类别
	 */
	public static Map<String, String> getMapRelaType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIZ_RELA_TYPE);
	}
	/**
	 * 细项类别
	 */
	public static Map<String, String> getMapPurchaseItemType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIZ_PURCHASE_ITEM_TYPE);
	}
	/**
	 * 公共关系级别
	 */
	public static Map<String, String> getMapRelaLevel() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIZ_RELA_LEVEL);
	}
	/**
	 * HOE类别
	 */
	public static Map<String, String> getMapHoeType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIZ_HOE_TYPE);
	}
	/**
	 * 宝龙大酒店类别
	 */
	public static Map<String, String> getMapHotelName() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIZ_HOTEL_NAME);
	}
	/**
	 * 省份代号
	 */
	public static Map<String, String> getMapProvinceType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.CITY_PROVINCE_TYPE);
	}
	/**
	 * 商业合同大类
	 */
	public static Map<String, String> getMapContBigType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_CONT_BIG_TYPE);
	}
	public static Map<String, String> getMapContBigTypeNew() {
		return appDictTypeManager.getDictDataByTypeCdA(DictContants.BIS_CONT_BIG_TYPE);
	}

	/**
	 * 商业合同小类
	 */
	public static Map<String, String> getMapContSmallType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_CONT_SMALL_TYPE);
	}

	/**
	 * 商业费用类型(总)
	 */
	public static Map<String, String> getMapChargeType() {
		return appDictTypeManager.getDictDataByTypeCdB(DictContants.BIS_CHARGE_TYPE);
	}

	/**
	 * 商业费用类型(新)
	 */
	public static Map<String, String> getMapChargeTypeNew() {
		return appDictTypeManager.getDictDataByTypeCdA(DictContants.BIS_CHARGE_TYPE_NEW);
	}

	/**
	 * 商业费用类型(商铺报表)
	 */
	public static Map<String, String> getMapChargeTypeSrpt() {
		return appDictTypeManager.getDictDataByTypeCdA(DictContants.BIS_CHARGE_TYPE_SRPT);
	}

	/**
	 * 商业费用类型(商铺报表)
	 */
	public static Map<String, String> getMapChargeTypeFrpt() {
		return appDictTypeManager.getDictDataByTypeCdA(DictContants.BIS_CHARGE_TYPE_FRPT);
	}

	/**
	 * 商业费用类型(多经报表)
	 */
	public static Map<String, String> getMapChargeTypeMrpt() {
		return appDictTypeManager.getDictDataByTypeCdA(DictContants.BIS_CHARGE_TYPE_MRPT);
	}

	public static String getMapChargeType(String value) {
		return getMapChargeType().get(value);
	}

	/**
	 * 退铺原因
	 */
	public static Map<String, String> getMapBisBackReason() {
		return appDictTypeManager.getDictDataByTypeCdA(DictContants.BIS_BACK_REASON);
	}

	/**
	 * 商业合同状态
	 */
	public static Map<String, String> getMapBisContStatus() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_CONT_STATUS);
	}

	/**
	 * 商业应收款状态
	 */
	public static Map<String, String> getMapBisMustStatus() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_MUST_STATUS);
	}

	/**
	 * 商业应收款状态
	 */
	public static Map<String, String> getMapBisOperationProjec() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_OPERATION_PROJEC);
	}

	/**
	 * 商业租金支付方式
	 */
	public static Map<String, String> getMapPaymentMode() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_PAYMENT_MODE);
	}

	/**
	 * 商业月租金方式
	 */
	public static Map<String, String> getMapPayWay() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_PAY_WAY);
	}

	/**
	 * 商业最晚收款月
	 */
	public static Map<String, String> getMapPayLastMonth() {
		return appDictTypeManager.getDictDataByTypeCdA(DictContants.BIS_PAY_LAST_MONTH);
	}
	/**
	 * 商业租金支付周期
	 */
	public static Map<String, String> getMapPayCycle() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_PAY_CYCLE);
	}
	/**
	 * 商业房屋结构
	 */
	public static Map<String, String> getMapHouseStru() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_HOUSE_STRU);
	}
	/**
	 * 商业商铺业态
	 */
	public static Map<String, String> getMapStoreLayout() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_STORE_LAYOUT);
	}
	/**
	 * 产权性质
	 */
	public static Map<String, String> getMapPropertyRight() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_PROPERTY_RIGHT);
	}
	/**
	 * 经营性质
	 */
	public static Map<String, String> getMapShopManageType() {
		/*Map<String, String> map =appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_SHOP_MANAGE_TYPE);
		//如果有新增主、次主力店的角色，则移去小商铺角色
		if(SpringSecurityUtils.hasRole("A_SHOP_INSE_MAIN")&&!SpringSecurityUtils.hasRole("A_SHOP_QUERY_ALL")){
			map.remove("3");
		}
		return map;*/
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_SHOP_MANAGE_TYPE);
	}
	
	/**
	 * 商家级别-商家库
	 */
	public static Map<String, String> getMapShopLevel() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_SHOP_LEVEL);
	}
	/**
	 * 考察级别-商家库
	 */
	public static Map<String, String> getMapShopInvestLevel() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_INVEST_LEVEL);
	}
	/**
	 * 34个省-商家库
	 */
	public static Map<String, String> getMapProvince() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_PROVINCE);
	}
	
	/**
	 * 商业公寓业态
	 */
	public static Map<String, String> getMapFlatLayout() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_FLAT_LAYOUT);
	}

	/**
	 * 商业合作方式
	 */
	public static Map<String, String> getMapBisCoopWay() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_COOP_WAY);
	}

	/**
	 * 商业租户性质
	 */
	public static Map<String, String> getMapBisShopType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_SHOP_TYPE);
	}

	/**
	 * 商业业态图类型
	 */
	public static Map<String, String> getMapBisImgType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_IMG_TYPE);
	}

    public static Map<String, String> getMapMonth() {
    	return appDictTypeManager.getDictDataByTypeCdB(DictContants.COMMON_MONTH);
    }

    public static Map<String, String> getMapYear() {
    	return appDictTypeManager.getDictDataByTypeCdB(DictContants.COMMON_YEAR);
    }

    public static Map<String, String> getMapBisFactStatus() {
    	return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_AUDIT_STATUS);
    }
    public static Map<String, String> getMapExpenseType() {
    	Map<String, String> dictDataByTypeCd = appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_EXPENSE_TYPE);
    	dictDataByTypeCd.remove("B29");
    	return dictDataByTypeCd;
    }

    public static String getMapBisFactStatus(String value) {
    	return getMapBisFactStatus().get(value);
    }
    //商业：经营现状
   public static Map<String, String>  getBisManagementStatus(){
	   return appDictTypeManager.getDictDataByTypeCdB(DictContants.BIS_MANAGEMENT_STATUS);
   }

    // 投标管理供应商级别
    public static Map<String, String> getMapSupBasicEvaluate() {
    	return appDictTypeManager.getDictDataByTypeCd(DictContants.SUP_BASIC_EVALUATE);
    }


    // 成本网上投标
    // 标段状态
    public static Map<String, String> getMapBidStatus() {
    	return appDictTypeManager.getDictDataByTypeCd(DictContants.BID_STATUS);
    }
    // 应标状态
    public static Map<String, String> getMapBid() {
    	return appDictTypeManager.getDictDataByTypeCd(DictContants.BID_STATUS);
    }

    // 供方类型
    public static Map<String, String> getMapSupportType(){
    	return appDictTypeManager.getDictDataByTypeCd(DictContants.SUPPORT_APPOINTMENT_TYPE);
    }
    // 商铺规划业态与商家类别关系
    public static Map<String, String> getMapStoreLayoutShopSort(){
    	return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_STORE_LAYOUT_SHOP_SORT);
    }

    //工料价格指数-地区
    public static Map<String, String> getMapProdAreaCd(){
    	return appDictTypeManager.getDictDataByTypeCd(DictContants.PROD_AREA_CD);
    }
    //工料价格指数-产品业态
    public static Map<String, String> getMapProdBussinessCd(){
    	return appDictTypeManager.getDictDataByTypeCd(DictContants.PROD_BUSSINESS_CD);
    }
    //工料价格指数-工料范围
    public static Map<String, String> getMapProdMaterialZoneCd(){
    	return appDictTypeManager.getDictDataByTypeCd(DictContants.PROD_MATERIAL_ZONE_CD);
    }
    //成本數據庫-根據數據庫字段查詢
    public static Map<String, String> getMapCtdb(String dbcode){
    	return appDictTypeManager.getDictDataByTypeCd(dbcode);
    }


    // ----------------------------会议纪要管理----------------------------
    // 会议类型
    public static Map<String, String> getMesMeetingCategoryCd(){
        return appDictTypeManager.getDictDataByTypeCd(DictContants.MES_MEETING_CATEGORY);
    }

    // ----------------------------会议纪要管理----------------------------
    // 会议类型
    public static Map<String, String> getMesMeetingStatusCd(){
        return appDictTypeManager.getDictDataByTypeCd(DictContants.MES_MEETING_STATUS);
    }
    /***
     * 业态操作类型
     * @return
     */
    public static Map<String , String> getBisOperationTypeCd(){
    	return appDictTypeManager.getDictDataByTypeCd(DictContants.BIS_OPREATION_TYPE);
    }
    
    /**网批模块类别
     * @return
     */
    public static Map<String, String> getMapModuleType() {
    	return appDictTypeManager.getDictDataByTypeCd(DictContants.RES_MODULE_TYPE);
	}
    /**特别费台账状态
     * @return
     */
    public static Map<String, String> getMapRecordStatus() {
    	return appDictTypeManager.getDictDataByTypeCd(DictContants.RES_SPECIAL_RECORD_STATUS);
    }
    /**获取公司列表
     * @return
     */
    public static List<String> loadCompanies() {
    	return bizBankAccountManager.loadCompanies();
    }
    /***
     * 设备所属系统
     * @return
     */
    public static Map<String, String> getMapEquipBelongTo(){
    	return appDictTypeManager.getDictDataByTypeCd(DictContants.EQUIP_BELONGTO_CD);
    }
    /***
     * 设备分类
     * @return
     */
    public static Map<String, String> getMapEquipTypeCd(){
    	return appDictTypeManager.getDictDataByTypeCd(DictContants.EQUIP_TYPE_CD);
    }
}
