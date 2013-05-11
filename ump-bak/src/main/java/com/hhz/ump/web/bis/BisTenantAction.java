package com.hhz.ump.web.bis;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.bis.BisContManager;
import com.hhz.ump.dao.bis.BisFactManager;
import com.hhz.ump.dao.bis.BisFloorManager;
import com.hhz.ump.dao.bis.BisMultiManager;
import com.hhz.ump.dao.bis.BisMustManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.dao.bis.BisShopConnManager;
import com.hhz.ump.dao.bis.BisShopManager;
import com.hhz.ump.dao.bis.BisStoreManager;
import com.hhz.ump.dao.bis.BisTenantManager;
import com.hhz.ump.entity.bis.BisCont;
import com.hhz.ump.entity.bis.BisFloor;
import com.hhz.ump.entity.bis.BisMulti;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisShop;
import com.hhz.ump.entity.bis.BisShopConn;
import com.hhz.ump.entity.bis.BisStore;
import com.hhz.ump.entity.bis.BisStoreContRel;
import com.hhz.ump.entity.bis.BisTenant;
import com.hhz.ump.entity.bis.MustFactVo;
import com.hhz.ump.entity.bis.ShopStoreVo;
import com.hhz.ump.util.Arith;
import com.hhz.ump.util.BisConstants;
import com.hhz.ump.util.BisShopTenantUtil;
import com.hhz.ump.util.DictMapUtil;
import com.hhz.ump.util.Util;
import com.hhz.ump.web.vo.BisTenantFeeVo;
import com.hhz.ump.web.vo.BisTenantVo;
import com.hhz.ump.web.vo.VoCombo;
import com.hhz.ump.web.vo.VoFact;

public class BisTenantAction extends CrudActionSupport<BisTenant> {

	@Autowired
	private BisTenantManager bisTenantManager;
	@Autowired
	private BisProjectManager bisProjectManager;
	@Autowired
	private BisStoreManager bisStoreManager;
	@Autowired
	private BisMultiManager bisMultiManager;
	@Autowired
	private BisFloorManager bisFloorManager;
	@Autowired
	private BisMustManager bisMustManager;
	@Autowired
	private BisShopManager bisShopManager;
	@Autowired
	private BisFactManager bisFactManager;
	@Autowired
	private BisShopConnManager bisShopConnManager;
	@Autowired
	private BisContManager bisContManager;
	private BisTenant entity;
	private BisCont bisCont;
	private BisFloor bisFloor;
	private BisMulti bisMulti;
	private String bisMultiId;
	private String bisProjectId;
	private String bisProjectName;
	private String bisTenantId;
	private String mustYear;
	private String factYear;
	private String bisStoreNo;
	private String storeShopNo;
	private String adNo;
	private String bisFloorId;
	private String bisFloorVirId;
	private String buildingNum;
	private String floorNum;
	private String buildingFloor;
	private String nameCn;
	private String floorBigPicUrl;
	private BigDecimal SquareAll;
	private List<MustFactVo> resultLis;
	private List<VoFact> overDueList;
	private List<BisStore> listBisStores;
	private List<BisMulti> listBisMultis;
	private List<VoCombo> storeLayoutCds = new ArrayList<VoCombo>();
	private String chargeTypeCd;
	private String reportDate;
	private String manageCd;
	private List<BisShopTenantUtil> shopTenantUtils = new ArrayList<BisShopTenantUtil>();
	private List<ShopStoreVo> shopStoreList;
	private List<BisFloor> floorVirList;
	private Map<String, String> mapBuildSel;
	private int nowMonth; // 当前的月份
	private int nowYear; // 当前的年份
	private ShopStoreVo shopStoreVo;

	private String viewType;
	private String filter_LIKES_storeNo;
	private String filter_LIKES_shopName;
	private String bisStoreIds;
	private String bisStoreNos;
	private String bisShopId;
	private String bisShopName;
	private String[] chkIds;
	private String mergeIds;
	private String standardTenantId;
	
	private String backType; //退铺方式
	private String step; //退铺步骤
	private String backReason;
	private String backDate;
	private String attachFlg;
	private String remark;
	private String owners;//业主名称
	private BisTenantFeeVo totalFeeVo;
	private List<BisCont> tenantConts = new ArrayList<BisCont>(); //租户相关合同
	private List<BisTenantFeeVo> tenantFees = new ArrayList<BisTenantFeeVo>(); //租户相关费用
	
	private List<BisTenantVo> tenantHisList = new ArrayList<BisTenantVo>();
	
	private Map<String, String> mapBisShopConn = new LinkedHashMap<String, String>();
	private Map<String, String> mapBisFloor = new LinkedHashMap<String, String>();
	private Map<String, String> mapSubFloor = new LinkedHashMap<String, String>();

	@Override
	public BisTenant getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String list() throws Exception {

		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
		List<BisFloor> list = bisFloorManager.find(
				"from BisFloor where bisProject.bisProjectId=? and floorType='1' order by sequenceNo,bisFloorId",
				bisProjectId);
		mapBisFloor.put("", "--请选择--");
		for (BisFloor floor : list) {
			mapBisFloor.put(floor.getBisFloorId(), floor.getBuildingNum() + "-" + floor.getFloorNum());
		}

		page.setPageSize(10);

		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from BisTenant bt where bt.bisProjectId=:bisProjectId");
		param.put("bisProjectId", bisProjectId);
		if (StringUtils.isNotBlank(storeShopNo)) {
			hql.append(
					" and exists (select 1 from BisShop bs where bs.bisShopId=bt.bisShopId and (upper(bs.nameCn) like :storeShopNo or upper(bs.nameEn) like :storeShopNo) )")
					.append(" or bt.bisTenantId (select scr.bisCont.bisTenantId from BisStoreContRel scr join scr.bisStore bs where bs.bisProjectId=:bisProjectId and upper(scr.bisStore.storeNo) like :storeShopNo) ");
			param.put("storeShopNo", "%" + storeShopNo.toUpperCase() + "%");
		}
		if (StringUtils.isNotBlank(bisFloorId)) {
			hql.append(" and bt.bisTenantId in (select scr.bisCont.bisTenantId from BisStoreContRel scr join scr.bisStore bs where bs.bisFloor.bisFloorId=:bisFloorId ) ");
			param.put("bisFloorId", bisFloorId);
		}
		if (StringUtils.isNotBlank(filter_LIKES_shopName)) {
			hql.append(
					" and (exists (select 1 from BisShop bs where bs.bisShopId=bt.bisShopId and (upper(bs.nameCn) like :shopName or upper(bs.nameEn) like :shopName) )")
					.append(" or upper(bt.owner) like :shopName )");
			param.put("shopName", "%" + filter_LIKES_shopName.toUpperCase() + "%");
		}
		if (StringUtils.isNotBlank(filter_LIKES_storeNo)) {
			hql.append(" and bt.bisTenantId in (select scr.bisCont.bisTenantId from BisStoreContRel scr join scr.bisStore bs where bs.bisProjectId=:bisProjectId and upper(bs.storeNo) like :storeNo) ");
			param.put("storeNo", "%" + filter_LIKES_storeNo.toUpperCase() + "%");
		}

		hql.append(" order by bt.bisTenantId");

		page = bisTenantManager.findPage(page, hql.toString(), param);

		return "listView";
	}

	@Override
	public String input() throws Exception {

		return INPUT;
	}

	@Override
	public String save() throws Exception {

		bisTenantManager.save(entity);
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String execute() throws Exception {
		if (StringUtils.isBlank(bisProjectId)) {
			BisProject bisProject = bisProjectManager.getCurrProject();
			if (bisProject != null) {
				bisProjectId = bisProject.getBisProjectId();
				bisProjectName = bisProject.getProjectName();
			}
		} else {
			bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
		}
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(getId())) {
			entity = bisTenantManager.getEntity(getId());
			bisProjectId = entity.getBisProjectId();
			bisShopId = entity.getBisShopId();
			BisShop bisShop = bisShopManager.getEntity(bisShopId);
			bisShopName = bisShop.getNameCn();
			if (StringUtils.isBlank(bisShop.getNameCn())) {
				bisShopName = bisShop.getNameEn();
			}
			mapBisShopConn = bisShopConnManager.getMapBisShopConn(bisShopId);
			Object[] storeIdNos = bisStoreManager.getBisStoreIdNos(entity.getBisTenantId());
			bisStoreIds = (String) storeIdNos[0];
			bisStoreNos = (String) storeIdNos[1];

		} else {
			entity = new BisTenant();
		}
		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
	}

	public String changeView() throws Exception {
		if ("1".equals(viewType))
			return list();
		else
			return loadImage();
	}

	/**
	 * 进入租户台账主页面；两个接口 先判断租户，再判断楼层，再判断项目
	 */
	@SuppressWarnings("rawtypes")
	public String loadImage() throws Exception {

		if (StringUtils.isBlank(bisProjectId))
			throw new RuntimeException("项目不能为空");

		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();

		shopStoreList = bisTenantManager.getTenants(bisProjectId, "1");

		// 获取bisFloor
		if (StringUtils.isNotBlank(bisFloorId)) {
			String[] newbisFloorId = bisFloorId.split("`");
			bisFloorId = newbisFloorId[0];
			bisFloor = bisFloorManager.getEntity(bisFloorId);
			floorNum = bisFloor.getFloorNum();
			buildingNum = bisFloor.getBuildingNum() == null ? "" : bisFloor.getBuildingNum();
			bisFloorId = bisFloor.getBisFloorId();
			floorBigPicUrl = bisFloor.getBigPicUrl();
		}

		// 租户入口查看租户所在的项目楼层信息
		if (StringUtils.isBlank(bisFloorId) && StringUtils.isNotBlank(bisTenantId)) {
			Map<String, Object> param = new HashMap<String, Object>();
			List list = bisFloorManager.findBySql(floorPartQuery(), param);
			Object[] obj = null;
			obj = (Object[]) list.get(0);
			bisProjectId = (String) obj[3];
			bisProjectName = (String) obj[1];
			buildingNum = (String) obj[2];
			floorNum = (String) obj[0];
			bisFloorId = (String) obj[4];
			bisFloor = bisFloorManager.getEntity(bisFloorId);
			floorBigPicUrl = bisFloor.getBigPicUrl();
		}

		// 读取该楼层的所有商铺位置的信息
		Map<String, Object> param2 = new HashMap<String, Object>();
		param2.put("bisFloorId", bisFloorId);
		StringBuffer storeSql = new StringBuffer(
				"select t.store_no,t.coords,t.square,t.bis_store_id,t.rent_square,t.equity_nature,t.if_practice from bis_store t where t.bis_floor_id like :bisFloorId and t.coords is not null");

		floorVirList = bisFloorManager.getFloorByProject(bisProjectId, floorNum, null, bisFloorId);
		if (floorVirList.size() > 0) {
			if (null == bisFloorVirId || StringUtils.isBlank(bisFloorVirId)) {
				bisFloorVirId = floorVirList.get(0).getBisFloorId();
			}
			for (BisFloor v : floorVirList) {
				if (v.getBisFloorId().equals(bisFloorVirId)) {
					storeSql.append("   and t.virtual_Area= :virtualArea");
					param2.put("virtualArea", v.getBuildingNum());
					floorBigPicUrl = v.getBigPicUrl();
				}
			}
		}
		listBisStores = storeColl(bisStoreManager.findBySql(storeSql.toString(), param2));

		buildingFloor = consBuildingFloor(bisProjectId);
		nowYear = DateOperator.getYear(DateOperator.getDateNow());

		return "imageView";
	}
	
	public String toMerge() throws Exception {

		return "merge";
	}

	public void prepareToMerge() throws Exception {

		entity = new BisTenant();
		BisTenant standardTenant = null;
		if (StringUtils.isNotBlank(standardTenantId)) {
			standardTenant = bisTenantManager.getEntity(standardTenantId);
		} else {
			standardTenant = bisTenantManager.getEntity(chkIds[0]);
		}
		entity = standardTenant;

		// entity.setBisProjectId(standardTenant.getBisProjectId());
		// entity.setBisShopId(standardTenant.getBisShopId());
		// entity.setBisShopConnId(standardTenant.getBisShopConnId());
		// entity.setRentStartDate(standardTenant.getRentStartDate());
		// entity.setRentEndDate(standardTenant.getRentEndDate());
		// entity.setOwner(standardTenant.getOwner());
		// entity.setManageCd(standardTenant.getManageCd());
		// entity.setLayoutCd(standardTenant.getLayoutCd());
		// entity.setTypeCd(standardTenant.getTypeCd());
		// entity.setActiveBl(standardTenant.getActiveBl());
		//
		// entity.setCreator(standardTenant.getCreator());
		// entity.setCreatedDate(standardTenant.getCreatedDate());
		// entity.setCreatedCenterCd(standardTenant.getCreatedCenterCd());
		// entity.setCreatedDeptCd(standardTenant.getCreatedDeptCd());
		// entity.setCreatedPositionCd(standardTenant.getCreatedPositionCd());
		// entity.setUpdator(standardTenant.getUpdator());
		// entity.setUpdatedDate(standardTenant.getUpdatedDate());
		// entity.setUpdatedCenterCd(standardTenant.getUpdatedCenterCd());
		// entity.setUpdatedDeptCd(standardTenant.getUpdatedDeptCd());
		// entity.setUpdatedPositionCd(standardTenant.getUpdatedPositionCd());

		bisProjectId = entity.getBisProjectId();
		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
		bisShopId = entity.getBisShopId();
		BisShop bisShop = bisShopManager.getEntity(bisShopId);
		bisShopName = bisShop.getNameCn();
		if (StringUtils.isBlank(bisShop.getNameCn())) {
			bisShopName = bisShop.getNameEn();
		}
		mapBisShopConn = bisShopConnManager.getMapBisShopConn(bisShopId);
		// bisStoreIds = "";
		// bisStoreNos = "";
		mergeIds = "";
		for (String tenantId : chkIds) {

			mergeIds = tenantId + "," + mergeIds;
		}

		// Object[] storeIdNos = bisStoreManager.getBisStoreIdNos(chkIds);
		// entity.setStoreIds((String) storeIdNos[0]);
		// entity.setStoreNos((String) storeIdNos[1]);
	}

	public String merge() throws Exception {

		bisTenantManager.merge(entity, mergeIds);
		return null;
	}

	public void prepareMerge() throws Exception {

		prepareModel();
	}

	public String invalid() throws Exception {

		bisTenantManager.invalid(getId());
		return null;
	}

	public String valid() throws Exception {

		bisTenantManager.valid(getId());
		return null;
	}

	public String getProjectName(String projectId) {
		return bisProjectManager.getEntity(projectId).getProjectName();
	}

	public String getStoreNos(String tenantId) {
		return (String) bisStoreManager.getBisStoreIdNos(tenantId)[1];
	}

	public String getShopName(String shopId) {
		BisShop bisShop = bisShopManager.getEntity(shopId);
		if (StringUtils.isBlank(bisShop.getNameCn()))
			return bisShop.getNameEn();
		return bisShop.getNameCn();
	}

	public String getShopConnName(String shopConnId) {
		BisShopConn bisShopConn = bisShopConnManager.getEntity(shopConnId);
		return bisShopConn.getPartName();
	}

	/**
	 * 初始化商铺楼层列表
	 * 
	 * @return
	 */
	public Map<String, String> getMapFloor() throws Exception {
		Map<String, String> mapFloor = bisFloorManager.getMapFloor();
		return mapFloor;
	}

	/**
	 * 实收选取租户接口
	 * 
	 * @return
	 */
	public String mustFactInterface() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		shopStoreList = shopStoreColl(bisFloorManager.findBySql(shopStoreQuery(), param));
		return "factInterface";
	}

	public String searchTenant() {
		// shopStoreList =bisTenantManager.getTenants(bisProjectId );
		return "factInterface";
	}

	/**
	 * 合同中模糊搜索租户
	 */
	public void quickSearch() throws Exception {
		String value = Struts2Utils.getParameter("value");
		page.setPageSize(10);
		String hql = "select bt from BisTenant bt, BisShop bs where bt.bisShopId=bs.bisShopId and (upper(bs.nameCn) like :value or upper(bs.nameEn) like :value or upper(bt.owner) like :value) ";
		// 商铺LIKE搜索暂不启用(搜索太慢)
		// hql+="or exists (select 1 from BisStore bs where bs.bisTenantId=bt.bisTenantId and bs.storeNo like :value)";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("value", "%" + value.toUpperCase() + "%");
		page = bisTenantManager.findPage(page, hql, param);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String shopName = null;
		for (BisTenant bisTenant : page.getResult()) {
			Map<String, Object> map = new HashMap<String, Object>();
			BisShop bisShop = bisShopManager.getEntity(bisTenant.getBisShopId());
			if (StringUtils.isNotBlank(bisShop.getNameCn())) {
				shopName = bisShop.getNameCn();
			} else {
				shopName = bisShop.getNameEn();
			}
			map.put("bisTenantId", bisTenant.getBisTenantId()); // 主键
			map.put("shopName", StringUtils.trim(shopName.trim())); // 品牌
			if (StringUtils.isNotBlank(bisTenant.getBisShopConnId())) {
				map.put("shopConnName", bisShopConnManager.getEntity(bisTenant.getBisShopConnId()).getPartName()); // 经销商
			}
			map.put("owner", bisTenant.getOwner()); // 业主
			map.put("manageCd", bisTenant.getManageCd()); // 经营性质
			map.put("layoutCd", bisTenant.getLayoutCd()); // 业态

			Object[] idnos = bisStoreManager.getBisStoreIdNos(bisTenant.getBisTenantId());
			map.put("storeIds", idnos[0]); // 商铺ID
			map.put("storeNos", idnos[1]); // 商铺NO
			map.put("square", idnos[2]); // 建筑面积
			map.put("innerSquare", idnos[3]); // 套内面积
			map.put("rentSquare", idnos[4]); // 计租面积
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}

	public void quickSearchTenant() {
		String value = Struts2Utils.getParameter("value").trim();
		String type = Struts2Utils.getParameter("type").trim();
		String shopName = "";
		if (type.equals("shop")) {
			storeShopNo = "%";

			shopName = value;
		} else if (type.equals("store")) {
			storeShopNo = value;
			shopName = "%";
		}
		List<ShopStoreVo> result = null;

		result = bisTenantManager.getTenants(bisProjectId, shopName, storeShopNo);

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		// 增加根节点
		Map<String, String> map = new HashMap<String, String>();
		map.put("bisTenantId", "");
		map.put("nameCn", "");
		map.put("storeNo", "");
		list.add(map);
		int num = 0;
		for (ShopStoreVo tmp : result) {

			map = new HashMap<String, String>();
			map.put("bisTenantId", tmp.getBisTenantId());
			map.put("nameCn", tmp.getNameCn());
			map.put("storeNo", tmp.getStoreNo());
			list.add(map);
			num++;
			if (num > 10) {
				break;
			}
		}
		Struts2Utils.renderJson(list);
	}

	/**
	 * 进入租户台账主页面
	 *  入口参数： bisProjectId, bisFloorId, bisTenantId
	 *  条件1   bisProjectId,bisFloorId   加载此楼层所有租户和商铺号
	 *  条件2   bisProjectId,bisTenantId  加载此租户所在楼层以及初始化此租户所在区域
	 */
	@SuppressWarnings("rawtypes")
	public String main() throws Exception {

		if (StringUtils.isBlank(bisProjectId))
			throw new RuntimeException("项目不能为空");

		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();

		shopStoreList = bisTenantManager.getTenants(bisProjectId, "1");
		//过滤租户中重复加载的商铺
		String[] stores;
		StringBuffer sb;
		for (ShopStoreVo vo : shopStoreList) {
			stores = vo.getStoreNo().split(",");
			sb = new StringBuffer(stores[0]);
			for (int i = 1; i < stores.length; i++) {
				if (sb.indexOf(stores[i]) < 0) {
					sb.append(",").append(stores[i]);
				}
			}
			vo.setStoreNo(sb.toString());
		}
		// 获取bisFloor
		if (StringUtils.isNotBlank(bisFloorId)) {
			String[] newbisFloorId = bisFloorId.split("`");
			bisFloorId = newbisFloorId[0];
			bisFloor = bisFloorManager.getEntity(bisFloorId);
			floorNum = bisFloor.getFloorNum();
			buildingNum = bisFloor.getBuildingNum() == null ? "" : bisFloor.getBuildingNum();
			bisFloorId = bisFloor.getBisFloorId();
			floorBigPicUrl = bisFloor.getBigPicUrl();
		}

		if (StringUtils.isBlank(bisFloorId) && StringUtils.isNotBlank(bisTenantId)) {// 租户入口查看租户所在的项目楼层信息
			Map<String, Object> param = new HashMap<String, Object>();
			List list = bisFloorManager.findBySql(floorPartQuery(), param);
			Object[] obj = null;
			obj = (Object[]) list.get(0);
			bisProjectId = (String) obj[3];
			bisProjectName = (String) obj[1];
			buildingNum = (String) obj[2];
			floorNum = (String) obj[0];
			bisFloorId = (String) obj[4];
			bisFloor = bisFloorManager.getEntity(bisFloorId);
			floorBigPicUrl = bisFloor.getBigPicUrl();
		}

		// 读取该楼层的所有商铺位置的信息
		Map<String, Object> param2 = new HashMap<String, Object>();
		param2.put("bisFloorId", bisFloorId);
		StringBuffer storeSql = new StringBuffer(
				"select t.store_no,t.coords,t.square,t.bis_store_id,t.rent_square,t.equity_nature,t.if_practice from bis_store t where t.status_cd='1' and t.bis_floor_id like :bisFloorId and t.coords is not null");

		floorVirList = bisFloorManager.getFloorByProject(bisProjectId, floorNum, null, bisFloorId);
		if (floorVirList.size() > 0) {
			if (null == bisFloorVirId || StringUtils.isBlank(bisFloorVirId)) {
				bisFloorVirId = floorVirList.get(0).getBisFloorId();
			}
			for (BisFloor v : floorVirList) {
				if (v.getBisFloorId().equals(bisFloorVirId)) {
					storeSql.append("   and t.virtual_Area= :virtualArea");
					param2.put("virtualArea", v.getBuildingNum());
					floorBigPicUrl = v.getBigPicUrl();
				}
			}
		}
		listBisStores = storeColl(bisStoreManager.findBySql(storeSql.toString(), param2));

		buildingFloor = consBuildingFloor(bisProjectId);
		nowYear = DateOperator.getYear(DateOperator.getDateNow());
		return "main";
	}

	// 根据bisProjectId组合该项目的buildingFloor
	@SuppressWarnings("rawtypes")
	public String consBuildingFloor(String projectId) {
		StringBuffer returnSb = new StringBuffer();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisProjectId", projectId);
		String mySql = "select t.building_num,t.floor_num,t.bis_floor_id from bis_floor t"
				+ " where t.bis_project_id = :bisProjectId and t.floor_type='1'	and	t.sub_floor_type='1'	"
				+ " order by t.building_num,t.floor_num";

		Object[] obj = null;
		returnSb.append("<select id='selectBuildingFloor' onChange='buildingFloorChange()' class='box'>");
		List list = bisStoreManager.findBySql(mySql, param);
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			String strBuildingNum = (String) obj[0];
			if (StringUtils.isNotBlank(strBuildingNum)) {
				strBuildingNum = strBuildingNum + "-";
			}
			String strFloorNum = (String) obj[1];
			String mybisFloorId = (String) obj[2];
			String selectedStr = "";
			if (mybisFloorId.equalsIgnoreCase(bisFloorId)) {
				selectedStr = " selected";
			}
			returnSb.append("<option value='" + mybisFloorId + "'" + selectedStr + ">" + strBuildingNum + strFloorNum
					+ "</option>");
		}
		returnSb.append("</select>");
		return returnSb.toString();
	}

	/*
	 * 租户信息里的快速搜索
	 */
	public String storeShopQuickSe() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		shopStoreList = shopStoreColl(bisFloorManager.findBySql(shopStoreQuery(), param));

		if (null != shopStoreList && 1 == shopStoreList.size()) {
			// 如果租户中有唯一的包含的记录，直接跳过去
			Struts2Utils.renderText(shopStoreList.get(0).getBisTenantId());
			return null;
		} else
			// 读取该楼层的所有相关商铺位置的信息
			/*
			 * Map<String, Object> param2 = new HashMap<String, Object>();
			 * param2.put("bisFloorId", bisFloorId); param2.put("storeShopNo",
			 * storeShopNo+"%"); String storeSql =
			 * "select t.store_no,t.coords,t.square from bis_store t" +
			 * " where t.bis_floor_id like :bisFloorId and t.store_no like :storeShopNo"
			 * +" order by t.store_no"; listBisStores =
			 * storeColl(bisStoreManager.findBySql(storeSql,param2));
			 */
			return "storeShopQuickSe";
	}

	@SuppressWarnings("rawtypes")
	private List<BisStore> storeColl(List list) {
		List<BisStore> bisStoreList = new ArrayList<BisStore>();
		Object[] obj = null;
		BisStore bisStore = null;
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			bisStore = new BisStore();
			bisStore.setStoreNo((String) obj[0]);
			bisStore.setCoords((String) obj[1]);
			bisStore.setSquare((BigDecimal) obj[2]);
			bisStore.setBisStoreId((String) obj[3]);
			bisStore.setRentSquare((BigDecimal) obj[4]);
			bisStore.setEquityNature((String)  obj[5]);
			bisStore.setIfPractice((String)  obj[6]);
			bisStoreList.add(bisStore);
		}
		return bisStoreList;
	}

	/**
	 * 商铺--商家 产生的租户信息
	 */
	@SuppressWarnings("rawtypes")
	private List<ShopStoreVo> shopStoreColl(List list) {
		List<ShopStoreVo> resultList = new ArrayList<ShopStoreVo>();
		Object[] obj = null;
		ShopStoreVo shopStore = null;
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			shopStore = new ShopStoreVo();
			shopStore.setBisShopId((String) obj[0]);
			shopStore.setBisTenantId((String) obj[1]);
			shopStore.setNameEn((String) obj[2]);
			shopStore.setNameCn((String) obj[3]);
			shopStore.setManageCd((String) obj[4]);
			shopStore.setShopTypeCd((String) obj[5]);
			shopStore.setRentStartDate((Date) obj[6]);
			shopStore.setRentEndDate((Date) obj[7]);
			shopStore.setFactSquare(obj[8] == null ? new BigDecimal(0) : (BigDecimal) obj[8]);
			shopStore.setStoreNo((String) obj[9]);
			shopStore.setUniqueId(i + ("uniqueId"));
			resultList.add(shopStore);
		}
		return resultList;
	}

	/**
	 * 商家--商铺 搜索语句；
	 */
	private String shopStoreQuery() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.bis_shop_id,a.bis_tenant_id,a.name_en,a.name_cn,a.manage_cd,a.shop_type_cd,c.cont_start_date,c.cont_end_date,c.square,b.store_no  ");
		sql.append(" from ( ");
		sql.append(" select bs.bis_shop_id,bt.bis_tenant_id,bs.name_en,bs.name_cn,bs.manage_cd,bs.shop_type_cd,bs.company_name ");
		sql.append(" from bis_tenant bt join bis_shop bs on bt.bis_shop_id=bs.bis_shop_id where 1=1 ");
		if (StringUtils.isNotBlank(bisProjectId)) {
			sql.append(" and bt.bis_project_id = '").append(bisProjectId).append("'");
		}
		if (StringUtils.isNotBlank(bisTenantId)) {
			sql.append(" and bt.bis_tenant_id = '").append(bisTenantId).append("'");
		}
		sql.append(" and exists ");
		sql.append("    (select 1 from bis_cont bc ");
		sql.append("    join bis_store_cont_rel scr on bc.bis_cont_id=scr.bis_cont_id ");
		sql.append("    join bis_store bst on scr.bis_store_id=bst.bis_store_id ");
		sql.append("    where bc.bis_tenant_id=bt.bis_tenant_id ");
		if (StringUtils.isNotBlank(bisProjectId)) {
			sql.append(" and bc.bis_project_id = '").append(bisProjectId).append("'");
		}
		if (StringUtils.isNotBlank(bisFloorId)) {
			sql.append(" and bst.bis_floor_id = '").append(bisFloorId).append("'");
		}
		sql.append(" ) ");
		if (StringUtils.isNotBlank(storeShopNo)) {
			sql.append(" and bs.name_cn  like '%").append(storeShopNo).append("%'");
		}
		sql.append(" or exists ");
		sql.append("    (select 1 from bis_cont bc ");
		sql.append("    join bis_store_cont_rel scr on bc.bis_cont_id=scr.bis_cont_id ");
		sql.append("    join bis_store bst on scr.bis_store_id=bst.bis_store_id ");
		sql.append("    where bc.bis_tenant_id=bt.bis_tenant_id ");
		if (StringUtils.isNotBlank(bisProjectId)) {
			sql.append(" and bc.bis_project_id = '").append(bisProjectId).append("'");
		}
		if (StringUtils.isNotBlank(bisFloorId)) {
			sql.append(" and bst.bis_floor_id = '").append(bisFloorId).append("'");
		}
		if (StringUtils.isNotBlank(storeShopNo)) {
			sql.append(" and bst.store_no like '%").append(storeShopNo).append("%'");
		}
		sql.append(" ) ");
		sql.append(") a left join ");
		sql.append(" (SELECT c.bis_tenant_id, TRANSLATE(LTRIM(c.text, '/'), '*/', '*,') store_no ");
		sql.append("  from ");
		sql.append("  (  ");
		sql.append("    SELECT ROW_NUMBER() OVER(PARTITION BY b.bis_tenant_id ORDER BY b.bis_tenant_id, num DESC) rn, ");
		sql.append("           b.bis_tenant_id, ");
		sql.append("           b.text ");
		sql.append("      from  ");
		sql.append("      ( ");
		sql.append("        select a.bis_tenant_id, ");
		sql.append("               level num,  ");
		sql.append("               SYS_CONNECT_BY_PATH(a.store_no, '/') text  ");
		sql.append("          from ");
		sql.append("          (   ");
		sql.append("            select bc.bis_tenant_id, ");
		sql.append("                   bs.store_no,");
		sql.append("                   ROW_NUMBER() OVER(PARTITION BY bc.bis_tenant_id ORDER BY bc.bis_tenant_id, bs.store_no) x");
		sql.append("              from bis_cont bc join bis_store_cont_rel scr on bc.bis_cont_id=scr.bis_cont_id join bis_store bs on scr.bis_store_id=bs.bis_store_id");
		sql.append("              group by bc.bis_tenant_id, bs.store_no ");
		sql.append("          ) a ");
		sql.append("          CONNECT BY a.bis_tenant_id = PRIOR a.bis_tenant_id AND a.x - 1 = PRIOR a.x ");
		sql.append("      ) b");
		sql.append("  ) c ");
		sql.append(" WHERE c.rn = 1 ");
		sql.append(" ORDER BY c.bis_tenant_id ");
		sql.append(" ) b on a.bis_tenant_id=b.bis_tenant_id  ");
		sql.append(" left join (  ");
		sql.append("            select bc.bis_tenant_id, sum(bs.square) as square, min(bc.cont_start_date) as cont_start_date, max(bc.cont_end_date) as cont_end_date  ");
		sql.append("            from bis_cont bc join bis_store_cont_rel scr on bc.bis_cont_id=scr.bis_cont_id join bis_store bs on scr.bis_store_id=bs.bis_store_id   ");
		sql.append("            group by bc.bis_tenant_id ");
		sql.append(") c on a.bis_tenant_id=c.bis_tenant_id ");
		sql.append(" where c.cont_start_date<=sysdate and c.cont_end_date>=sysdate ");
		return sql.toString();
	}

	private void getShopStoreByStoreId(String storeId) {
		ShopStoreVo returnObj = new ShopStoreVo();
		Map<String, String> tmpStoreLays = new HashMap<String, String>();
		// 搜索商铺号的组合的字符串
		String sqlStr2 = "select distinct s.store_no,s.layout_cd,s.owner" + " from  bis_store s where s.bis_store_id='"
				+ storeId + "'  order by s.store_no desc";
		List resultList2 = bisStoreManager.findBySql(sqlStr2, null);
		String storeNos = "";
		 owners="";
		for (int i = 0; null != resultList2 && i < resultList2.size(); i++) {
			Object[] tmps = (Object[]) resultList2.get(i);
			storeNos += tmps[0] + ",";
			if(null!=tmps[2]) {
		    	owners +=tmps[2] + ",";
			}
			if (null != tmpStoreLays.get(tmps[1])) {
				tmpStoreLays.put((String) tmps[1], tmpStoreLays.get(tmps[1]) + "," + tmps[0]);
			} else {
				tmpStoreLays.put((String) tmps[1], (String) tmps[0]);
			}
			if(!(";".equals(owners)||"".equals(owners))){
				owners = owners.substring(0, owners.length() - 1);
			}
		}
		Set<String> key = tmpStoreLays.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			VoCombo vo = new VoCombo();
			vo.setText(tmpStoreLays.get(s));
			vo.setKey(DictMapUtil.getMapStoreLayout().get(s));
			vo.setId(DictMapUtil.getMapStoreLayoutShopSort().get(vo.getKey()));
			storeLayoutCds.add(vo);
		}

	}

	/**
	 * 根据某租户ID获得改租户在租户台账中显示的信息
	 */
	@SuppressWarnings("rawtypes")
	private ShopStoreVo getShopStoreByBisTenantId(String tenantId) {
		ShopStoreVo returnObj = new ShopStoreVo();
		returnObj.setBisTenantId(tenantId);
		String sqlStr1 = "select bs.name_cn,bs.name_en,bc.manage_cd,bc.layout_cd,bc.cont_start_date,bc.cont_end_date"
				+ " ,bc.square,bc.rent_square,bc.cont_small_type_cd,bc.equity_nature,bs.bis_shop_id"
				+ " from bis_tenant bt left join bis_cont bc on bt.bis_tenant_id=bc.bis_tenant_id"
				+ " left join bis_shop bs on bt.bis_shop_id=bs.bis_shop_id " + " where bt.bis_tenant_id like '"
				+ tenantId + "'";

		List resultList1 = bisTenantManager.findBySql(sqlStr1, null);
		Object[] obj = null;
		for (int i = 0; null != resultList1 && i < resultList1.size(); i++) {
			obj = (Object[]) resultList1.get(i);
			boolean if_rent = false;
			if (StringUtils.isNotBlank((String) obj[8]) && BisConstants.RENT.equalsIgnoreCase((String) obj[8])) {
				if_rent = true;
			}
			returnObj.setNameCn((String) obj[0]);
			returnObj.setNameEn((String) obj[1]);
			if (StringUtils.isBlank(returnObj.getNameCn())) {
				returnObj.setNameCn(returnObj.getNameEn());
			}
			if (i > 0 && if_rent || 0 == i) {
				returnObj.setManageCd((String) obj[2]);
			}
			if (i > 0 && if_rent || 0 == i) {
				returnObj.setLayoutCd((String) obj[3]);
			}
			if (i > 0 && if_rent || 0 == i) {
				returnObj.setRentStartDate((Date) obj[4]);
			}
			if (i > 0 && if_rent || 0 == i) {
				returnObj.setRentEndDate((Date) obj[5]);
			}
			if (i > 0 && if_rent || 0 == i) {
				returnObj.setPowerSquare(obj[6] == null ? new BigDecimal(0) : (BigDecimal) obj[6]);
			}
			if (i > 0 && if_rent || 0 == i) {
				returnObj.setFactSquare(obj[7] == null ? new BigDecimal(0) : (BigDecimal) obj[7]);
			}
			if (i > 0 && if_rent || 0 == i) {
				returnObj.setEquityNature((String) obj[9]);
			}
			if (i > 0 && if_rent || 0 == i) {
				returnObj.setBisShopId((String) obj[10]);
			}
		}

		Map<String, String> tmpStoreLays = new HashMap<String, String>();
		// 搜索商铺号的组合的字符串
		String sqlStr2 = "select distinct bstore.store_no,bstore.layout_cd, bstore.owner"
				+ " from bis_tenant bt left join bis_cont bc on bt.bis_tenant_id=bc.bis_tenant_id"
				+ " left join bis_store_cont_rel bscr on bc.bis_cont_id=bscr.bis_cont_id"
				+ " left join bis_store bstore on bscr.bis_store_id=bstore.bis_store_id"
				+ " where bt.bis_tenant_id like '" + bisTenantId + "' order by bstore.store_no asc";
		List resultList2 = bisTenantManager.findBySql(sqlStr2, null);
		//商铺编号
		String storeNos = "";
		//业主名称
		owners="";
		for (int i = 0; null != resultList2 && i < resultList2.size(); i++) {
			Object[] tmps = (Object[]) resultList2.get(i);
			storeNos += tmps[0] + ",";
			if(null!=tmps[2]) {
				owners +=tmps[2] + ",";
			}else{
				if("1".equals(returnObj.getEquityNature())){
					owners +="宝龙,";
				}else{
					owners +="未填写,";
				}
			}
			if (null != tmpStoreLays.get(tmps[1])) {
				tmpStoreLays.put((String) tmps[1], tmpStoreLays.get(tmps[1]) + "," + tmps[0]);
			} else {
				tmpStoreLays.put((String) tmps[1], (String) tmps[0]);
			}
		}
		if (!"".equalsIgnoreCase(storeNos)) {
			storeNos = storeNos.substring(0, storeNos.length() - 1);
			returnObj.setStoreNo(storeNos);
		}
		if(!(";".equals(owners)||"".equals(owners))){
			owners = owners.substring(0, owners.length() - 1);
		}
		
		Set<String> key = tmpStoreLays.keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			VoCombo vo = new VoCombo();
			vo.setText(tmpStoreLays.get(s));
			vo.setKey(DictMapUtil.getMapStoreLayout().get(s));
			vo.setId(DictMapUtil.getMapStoreLayoutShopSort().get(vo.getKey()));
			storeLayoutCds.add(vo);
		}

		return returnObj;
	}

	/**
	 * 商铺信息
	 * 
	 * @return
	 */
	public String storeInfo() {
		entity = bisTenantManager.getEntity(bisTenantId);
		bisCont = bisContManager.getEntity(entity.getFirstContId());
		for (BisStoreContRel rel : bisCont.getBisStoreContRels()) {
			if (bisFloorId.equals(rel.getBisStore().getBisFloor().getBisFloorId())) {
				bisStoreNo = rel.getBisStore().getStoreNo();
				SquareAll = rel.getBisStore().getSquare();
			}
		}
		return "storeInfo";
	}

	/**
	 * 租户费用 应收、 实收搜索
	 */
	public String feeSearch() {
		if (StringUtils.isNotBlank(bisTenantId)) {
			Map<String, Object> param = new HashMap<String, Object>();
			resultLis = mustFactMoneyColl(bisMustManager.findBySql(mustFactQuery(), param));
			shopStoreVo = getShopStoreByBisTenantId(bisTenantId);
		} else if (StringUtils.isNotBlank(bisStoreIds)) {
			getShopStoreByStoreId(bisStoreIds);
		}

		nowMonth = DateOperator.getMonth12(DateOperator.getDateNow());
		return "feeSearch";
	}
	public String feeOverDue(){
		if (StringUtils.isNotBlank(bisTenantId)) {
			overDueList = bisFactManager.searchFactForShore(bisTenantId);
		}
		return "overDue";
	}

	@SuppressWarnings("rawtypes")
	private List<MustFactVo> mustFactMoneyColl(List list) {
		List<MustFactVo> mustFactList = new ArrayList<MustFactVo>();
		MustFactVo sumMustFact = new MustFactVo();
		sumMustFact.setChargeTypeCd("合计");
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			MustFactVo mustFact = new MustFactVo();
			mustFact.setChargeTypeCd((String) obj[0]);
			mustFact.setOneMustMoney(obj[1] == null ? new BigDecimal(0) : (BigDecimal) obj[1]);
			mustFact.setOneFactMoney(obj[2] == null ? new BigDecimal(0) : (BigDecimal) obj[2]);
			mustFact.setOneMoneyRate(obj[3] == null ? new BigDecimal(0) : (BigDecimal) obj[3]);
			mustFact.setTwoMustMoney(obj[4] == null ? new BigDecimal(0) : (BigDecimal) obj[4]);
			mustFact.setTwoFactMoney(obj[5] == null ? new BigDecimal(0) : (BigDecimal) obj[5]);
			mustFact.setTwoMoneyRate(obj[6] == null ? new BigDecimal(0) : (BigDecimal) obj[6]);
			mustFact.setThrMustMoney(obj[7] == null ? new BigDecimal(0) : (BigDecimal) obj[7]);
			mustFact.setThrFactMoney(obj[8] == null ? new BigDecimal(0) : (BigDecimal) obj[8]);
			mustFact.setThrMoneyRate(obj[9] == null ? new BigDecimal(0) : (BigDecimal) obj[9]);
			mustFact.setFouMustMoney(obj[10] == null ? new BigDecimal(0) : (BigDecimal) obj[10]);
			mustFact.setFouFactMoney(obj[11] == null ? new BigDecimal(0) : (BigDecimal) obj[11]);
			mustFact.setFouMoneyRate(obj[12] == null ? new BigDecimal(0) : (BigDecimal) obj[12]);
			mustFact.setFivMustMoney(obj[13] == null ? new BigDecimal(0) : (BigDecimal) obj[13]);
			mustFact.setFivFactMoney(obj[14] == null ? new BigDecimal(0) : (BigDecimal) obj[14]);
			mustFact.setFivMoneyRate(obj[15] == null ? new BigDecimal(0) : (BigDecimal) obj[15]);
			mustFact.setSixMustMoney(obj[16] == null ? new BigDecimal(0) : (BigDecimal) obj[16]);
			mustFact.setSixFactMoney(obj[17] == null ? new BigDecimal(0) : (BigDecimal) obj[17]);
			mustFact.setSixMoneyRate(obj[18] == null ? new BigDecimal(0) : (BigDecimal) obj[18]);
			mustFact.setSevMustMoney(obj[19] == null ? new BigDecimal(0) : (BigDecimal) obj[19]);
			mustFact.setSevFactMoney(obj[20] == null ? new BigDecimal(0) : (BigDecimal) obj[20]);
			mustFact.setSevMoneyRate(obj[21] == null ? new BigDecimal(0) : (BigDecimal) obj[21]);
			mustFact.setEigMustMoney(obj[22] == null ? new BigDecimal(0) : (BigDecimal) obj[22]);
			mustFact.setEigFactMoney(obj[23] == null ? new BigDecimal(0) : (BigDecimal) obj[23]);
			mustFact.setEigMoneyRate(obj[24] == null ? new BigDecimal(0) : (BigDecimal) obj[24]);
			mustFact.setNinMustMoney(obj[25] == null ? new BigDecimal(0) : (BigDecimal) obj[25]);
			mustFact.setNinFactMoney(obj[26] == null ? new BigDecimal(0) : (BigDecimal) obj[26]);
			mustFact.setNinMoneyRate(obj[27] == null ? new BigDecimal(0) : (BigDecimal) obj[27]);
			mustFact.setTenMustMoney(obj[28] == null ? new BigDecimal(0) : (BigDecimal) obj[28]);
			mustFact.setTenFactMoney(obj[29] == null ? new BigDecimal(0) : (BigDecimal) obj[29]);
			mustFact.setTenMoneyRate(obj[30] == null ? new BigDecimal(0) : (BigDecimal) obj[30]);
			mustFact.setEleMustMoney(obj[31] == null ? new BigDecimal(0) : (BigDecimal) obj[31]);
			mustFact.setEleFactMoney(obj[32] == null ? new BigDecimal(0) : (BigDecimal) obj[32]);
			mustFact.setEleMoneyRate(obj[33] == null ? new BigDecimal(0) : (BigDecimal) obj[33]);
			mustFact.setTweMustMoney(obj[34] == null ? new BigDecimal(0) : (BigDecimal) obj[34]);
			mustFact.setTweFactMoney(obj[35] == null ? new BigDecimal(0) : (BigDecimal) obj[35]);
			mustFact.setTweMoneyRate(obj[36] == null ? new BigDecimal(0) : (BigDecimal) obj[36]);

			sumMustFact.setOneMustMoney(sumMustFact.getOneMustMoney().add(mustFact.getOneMustMoney()));
			sumMustFact.setOneFactMoney(sumMustFact.getOneFactMoney().add(mustFact.getOneFactMoney()));
			sumMustFact.setTwoMustMoney(sumMustFact.getTwoMustMoney().add(mustFact.getTwoMustMoney()));
			sumMustFact.setTwoFactMoney(sumMustFact.getTwoFactMoney().add(mustFact.getTwoFactMoney()));
			sumMustFact.setThrMustMoney(sumMustFact.getThrMustMoney().add(mustFact.getThrMustMoney()));
			sumMustFact.setThrFactMoney(sumMustFact.getThrFactMoney().add(mustFact.getThrFactMoney()));
			sumMustFact.setFouMustMoney(sumMustFact.getFouMustMoney().add(mustFact.getFouMustMoney()));
			sumMustFact.setFouFactMoney(sumMustFact.getFouFactMoney().add(mustFact.getFouFactMoney()));
			sumMustFact.setFivMustMoney(sumMustFact.getFivMustMoney().add(mustFact.getFivMustMoney()));
			sumMustFact.setFivFactMoney(sumMustFact.getFivFactMoney().add(mustFact.getFivFactMoney()));
			sumMustFact.setSixMustMoney(sumMustFact.getSixMustMoney().add(mustFact.getSixMustMoney()));
			sumMustFact.setSixFactMoney(sumMustFact.getSixFactMoney().add(mustFact.getSixFactMoney()));
			sumMustFact.setSevMustMoney(sumMustFact.getSevMustMoney().add(mustFact.getSevMustMoney()));
			sumMustFact.setSevFactMoney(sumMustFact.getSevFactMoney().add(mustFact.getSevFactMoney()));
			sumMustFact.setEigMustMoney(sumMustFact.getEigMustMoney().add(mustFact.getEigMustMoney()));
			sumMustFact.setEigFactMoney(sumMustFact.getEigFactMoney().add(mustFact.getEigFactMoney()));
			sumMustFact.setNinMustMoney(sumMustFact.getNinMustMoney().add(mustFact.getNinMustMoney()));
			sumMustFact.setNinFactMoney(sumMustFact.getNinFactMoney().add(mustFact.getNinFactMoney()));
			sumMustFact.setTenMustMoney(sumMustFact.getTenMustMoney().add(mustFact.getTenMustMoney()));
			sumMustFact.setTenFactMoney(sumMustFact.getTenFactMoney().add(mustFact.getTenFactMoney()));
			sumMustFact.setEleMustMoney(sumMustFact.getEleMustMoney().add(mustFact.getEleMustMoney()));
			sumMustFact.setEleFactMoney(sumMustFact.getEleFactMoney().add(mustFact.getEleFactMoney()));
			sumMustFact.setTweMustMoney(sumMustFact.getTweMustMoney().add(mustFact.getTweMustMoney()));
			sumMustFact.setTweFactMoney(sumMustFact.getTweFactMoney().add(mustFact.getTweFactMoney()));
			mustFactList.add(mustFact);
		}
		sumMustFact.setOneMoneyRate(Arith.div2Rate(sumMustFact.getOneFactMoney(), sumMustFact.getOneMustMoney()));
		sumMustFact.setTwoMoneyRate(Arith.div2Rate(sumMustFact.getTwoFactMoney(), sumMustFact.getTwoMustMoney()));
		sumMustFact.setThrMoneyRate(Arith.div2Rate(sumMustFact.getThrFactMoney(), sumMustFact.getThrMustMoney()));
		sumMustFact.setFouMoneyRate(Arith.div2Rate(sumMustFact.getFouFactMoney(), sumMustFact.getFouMustMoney()));
		sumMustFact.setFivMoneyRate(Arith.div2Rate(sumMustFact.getFivFactMoney(), sumMustFact.getFivMustMoney()));
		sumMustFact.setSixMoneyRate(Arith.div2Rate(sumMustFact.getSixFactMoney(), sumMustFact.getSixMustMoney()));
		sumMustFact.setSevMoneyRate(Arith.div2Rate(sumMustFact.getSevFactMoney(), sumMustFact.getSevMustMoney()));
		sumMustFact.setEigMoneyRate(Arith.div2Rate(sumMustFact.getEigFactMoney(), sumMustFact.getEigMustMoney()));
		sumMustFact.setNinMoneyRate(Arith.div2Rate(sumMustFact.getNinFactMoney(), sumMustFact.getNinMustMoney()));
		sumMustFact.setTenMoneyRate(Arith.div2Rate(sumMustFact.getTenFactMoney(), sumMustFact.getTenMustMoney()));
		sumMustFact.setEleMoneyRate(Arith.div2Rate(sumMustFact.getEleFactMoney(), sumMustFact.getEleMustMoney()));
		sumMustFact.setTweMoneyRate(Arith.div2Rate(sumMustFact.getTweFactMoney(), sumMustFact.getTweMustMoney()));
		mustFactList.add(sumMustFact);
		return mustFactList;
	}

	/**
	 * 租户费用 应收、 实收搜索
	 */
	private String mustFactQuery() {
		StringBuffer sql = new StringBuffer().append(" select nvl(d.charge_type_cd,c.charge_type_cd),")
				.append("d.must1,c.fact1,round(decode(d.must1,0,0,c.fact1/d.must1*100), 2),")
				.append("d.must2,c.fact2,round(decode(d.must2,0,0,c.fact2/d.must2*100), 2),")
				.append("d.must3,c.fact3,round(decode(d.must3,0,0,c.fact3/d.must3*100), 2),")
				.append("d.must4,c.fact4,round(decode(d.must4,0,0,c.fact4/d.must4*100), 2),")
				.append("d.must5,c.fact5,round(decode(d.must5,0,0,c.fact5/d.must5*100), 2),")
				.append("d.must6,c.fact6,round(decode(d.must6,0,0,c.fact6/d.must6*100), 2),")
				.append("d.must7,c.fact7,round(decode(d.must7,0,0,c.fact7/d.must7*100), 2),")
				.append("d.must8,c.fact8,round(decode(d.must8,0,0,c.fact8/d.must8*100), 2),")
				.append("d.must9,c.fact9,round(decode(d.must9,0,0,c.fact9/d.must9*100), 2),")
				.append("d.must10,c.fact10,round(decode(d.must10,0,0,c.fact10/d.must10*100), 2),")
				.append("d.must11,c.fact11,round(decode(d.must11,0,0,c.fact11/d.must11*100), 2),")
				.append("d.must12,c.fact12,round(decode(d.must12,0,0,c.fact12/d.must12*100), 2)")
				.append(" from (select charge_type_cd,").append("sum(decode(fact_month,'1',money,null)) fact1,")
				.append("sum(decode(fact_month,'2',money,null)) fact2,")
				.append("sum(decode(fact_month,'3',money,null)) fact3,")
				.append("sum(decode(fact_month,'4',money,null)) fact4,")
				.append("sum(decode(fact_month,'5',money,null)) fact5,")
				.append("sum(decode(fact_month,'6',money,null)) fact6,")
				.append("sum(decode(fact_month,'7',money,null)) fact7,")
				.append("sum(decode(fact_month,'8',money,null)) fact8,")
				.append("sum(decode(fact_month,'9',money,null)) fact9,")
				.append("sum(decode(fact_month,'10',money,null)) fact10,")
				.append("sum(decode(fact_month,'11',money,null)) fact11,")
				.append("sum(decode(fact_month,'12',money,null)) fact12 ").append(" from bis_fact ");
		sql.append(" where fact_year= '").append(factYear).append("'");
		if(StringUtils.isNotBlank(bisTenantId)) {
			sql.append("and bis_tenant_id ='").append(bisTenantId).append("'");
		}
		if(StringUtils.isNotBlank(bisMultiId)) {
			sql.append("and bis_multi_id ='").append(bisMultiId).append("'");
		}
		sql.append(" group by charge_type_cd ) c full join");
		sql.append("(select charge_type_cd,").append("sum(decode(must_month,'1',money,null)) must1,")
				.append("sum(decode(must_month,'2',money,null)) must2,")
				.append("sum(decode(must_month,'3',money,null)) must3,")
				.append("sum(decode(must_month,'4',money,null)) must4,")
				.append("sum(decode(must_month,'5',money,null)) must5,")
				.append("sum(decode(must_month,'6',money,null)) must6,")
				.append("sum(decode(must_month,'7',money,null)) must7,")
				.append("sum(decode(must_month,'8',money,null)) must8,")
				.append("sum(decode(must_month,'9',money,null)) must9,")
				.append("sum(decode(must_month,'10',money,null)) must10,")
				.append("sum(decode(must_month,'11',money,null)) must11,")
				.append("sum(decode(must_month,'12',money,null)) must12 ");
		sql.append(" from bis_must ");
		sql.append(" where must_year= '").append(factYear).append("'");
		if(StringUtils.isNotBlank(bisTenantId)) {
			sql.append("and bis_tenant_id ='").append(bisTenantId).append("'");
		}
		if(StringUtils.isNotBlank(bisMultiId)) {
			sql.append("and bis_multi_id ='").append(bisMultiId).append("'");
		}
		sql.append(" group by charge_type_cd ) d on c.charge_type_cd=d.charge_type_cd ");
		sql.append(" order by nvl(d.charge_type_cd, c.charge_type_cd)");
		return sql.toString();
	}

	private String storePartQuery() {
		StringBuffer sql = new StringBuffer();
		sql.append("select store_no from bis_store where bis_store_id not in (select bis_store_id from bis_store_cont_rel ) ");
		if (StringUtils.isNotBlank(bisProjectId)) {
			sql.append(" and bis_project_id = '").append(bisProjectId).append("'");
		}
		if (StringUtils.isNotBlank(bisFloorId)) {
			sql.append(" and bis_floor_id = '").append(bisFloorId).append("'");
		}
		return sql.toString();
	}

	private String floorPartQuery() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from ( ")
				.append(" select distinct (bf.floor_num), bp.project_name ,bf.building_num,bf.bis_project_id,bf.bis_floor_id,max(bs.square)  ")
				.append(" from bis_cont bc, bis_store_cont_rel bscr,bis_store bs, bis_floor bf,bis_project bp ")
				.append(" where bc.bis_cont_id = bscr.bis_cont_id ")
				.append(" and  bscr.bis_store_id=bs.bis_store_id ")
				.append(" and bs.bis_floor_id=bf.bis_floor_id ")
				.append(" and bf.bis_project_id = bp.bis_project_id ")
				.append(" and bc.bis_tenant_id = '")
				.append(bisTenantId)
				.append("'")
				.append(" group by bc.bis_tenant_id,bf.floor_num,  bp.project_name,bf.building_num, bf.bis_project_id,bf.bis_floor_id")
				.append(" order by max(bs.square) desc").append(" )where rownum = 1");
		return sql.toString();
	}

	/**
	 * 项目经营情况商铺租户汇总表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String storeOperate() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		List bisStore = bisStoreManager.findBySql(storePartQuery(), param);
		shopStoreList = storeColl(bisFloorManager.findBySql(shopStoreQuery(), param), bisStore);
		return "storeOperate";
	}

	@SuppressWarnings("rawtypes")
	private List<ShopStoreVo> storeColl(List list, List lists) {
		List<ShopStoreVo> resultList = new ArrayList<ShopStoreVo>();
		// Object[] obj = null;
		ShopStoreVo shopStore = null;
		for (int i = 0; i < lists.size(); i++) {
			// Object[] objs =(Object[])lists.get(i);
			// obj =(Object[])lists.get(i);
			shopStore = new ShopStoreVo();
			shopStore.setStoreNo((String) lists.get(i));
			shopStore.setUniqueId(i + ("uniqueId"));
			resultList.add(shopStore);
		}
		for (int i = 0; i < list.size(); i++) {
			Object[] obj = (Object[]) list.get(i);
			// obj =(Object[])list.get(i);
			shopStore = new ShopStoreVo();
			shopStore.setBisShopId((String) obj[0]);
			shopStore.setBisTenantId((String) obj[1]);
			shopStore.setNameEn((String) obj[2]);
			shopStore.setNameCn((String) obj[3]);
			shopStore.setManageCd((String) obj[4]);
			shopStore.setShopTypeCd((String) obj[5]);
			shopStore.setRentStartDate((Date) obj[6]);
			shopStore.setRentEndDate((Date) obj[7]);
			shopStore.setFactSquare(obj[8] == null ? new BigDecimal(0) : (BigDecimal) obj[8]);
			shopStore.setStoreNo((String) obj[9]);
			shopStore.setUniqueId(i + ("uniqueId"));
			resultList.add(shopStore);
		}
		return resultList;
	}

	private void prepareLoad() {
		if (StringUtils.isBlank(bisProjectId))
			throw new RuntimeException("项目不能为空");
		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();

		// 加载使用广告租户收费明细
		nowYear = DateOperator.getYear(DateOperator.getDateNow());
	}

	public void prepareLoadAdImage() {
		prepareLoad();
	}
	private void loadAdInfo(){
		mapSubFloor = bisFloorManager.getGgSbFloor(bisProjectId);
		// 加载某一级分类广告位列表
		// 加载当前楼层图
		if (StringUtils.isNotBlank(bisFloorId)) {
			String[] newbisFloorId = bisFloorId.split("`");
			bisFloorId = newbisFloorId[0];
			bisFloor = bisFloorManager.getEntity(bisFloorId);

		} else {
			bisFloorId = Util.getMapFirstKey(mapSubFloor);
			bisFloor =bisFloorManager.getEntity(bisFloorId);
		}
		if("0".equals(bisFloor.getSubFloorType())){
			floorVirList = bisFloorManager.getGgFloorByProject(bisProjectId,null, bisFloorId);
			if(floorVirList.size()>0) {
				bisFloor = floorVirList.get(0);
				bisFloorId = bisFloor.getBisFloorId();
			}
		}else{
			floorVirList = bisFloorManager.getGgFloorByProject(bisProjectId,null, bisFloor.getParentId());
			
		}
		bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
		floorNum = bisFloor.getFloorNum();
		buildingNum = bisFloor.getBuildingNum() == null ? "" : bisFloor.getBuildingNum();
		floorBigPicUrl = bisFloor.getBigPicUrl();

		// 读取该楼层多经广告项目
		listBisMultis =searchMulti(bisFloorId,null);

		// 加载多经租户信息 没有
	}
	//加载广告位入口
	public String loadAdMain() {
		loadAdInfo();
		return "adImageMain";
	}
	public String loadAdImage() {
		loadAdInfo();
		return "adImage";
	}
	/*快速搜索广告位信息
	 */
	public String quickAd() throws Exception {
		listBisMultis =searchMulti(null,adNo);

		if (null != listBisMultis && 1 == listBisMultis.size()) {
			// 如果租户中有唯一的包含的记录，直接跳过去
			Struts2Utils.renderText(listBisMultis.get(0).getBisMultiId());
			return null;
		} else
			return "quickAd";
	}
	/**
	 * 广告位费用 应收、 实收搜索
	 */
	public String adFeeSearch() {
		if (StringUtils.isNotBlank(bisMultiId)) {
			Map<String, Object> param = new HashMap<String, Object>();
			resultLis = mustFactMoneyColl(bisMustManager.findBySql(mustFactQuery(), param));
		} 

		nowMonth = DateOperator.getMonth12(DateOperator.getDateNow());
		return "feeSearch";
	}
	private List<BisMulti> searchMulti(String bisFloorId,String adNo){
		Map<String, Object> param2 = new HashMap<String, Object>();
		StringBuffer multiSql = new StringBuffer(" from BisMulti t where  t.coords is not null");
		if (StringUtils.isNotBlank(bisFloorId)) {
			param2.put("bisFloorId", bisFloorId);
			multiSql.append(" and t.bisFloorId like :bisFloorId ");
		}
		if (StringUtils.isNotBlank(adNo)) {
			param2.put("adNo", "%"+adNo+"%");
			multiSql.append(" and t.multiName like :adNo ");
		}
		if (StringUtils.isNotBlank(bisMultiId)) {
			param2.put("bisMultiId",bisMultiId);
			multiSql.append(" and t.bisMultiId = :bisMultiId ");
		}
		//return bisMultiManager.find(multiSql.toString(), param2);
		List<BisMulti> result = bisMultiManager.find(multiSql.toString(), param2);
		for(BisMulti vo : result){
			vo.setMultiName(vo.getMultiName().replace(",", "-"));
		}
		return result;
	}
	/**
	 */
	public String loadMulti(){
		bisMulti = bisMultiManager.getEntity(bisMultiId);
		return "adDetail";
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

	public String getBisTenantId() {
		return bisTenantId;
	}

	public void setBisTenantId(String bisTenantId) {
		this.bisTenantId = bisTenantId;
	}

	public String getMustYear() {
		return mustYear;
	}

	public void setMustYear(String mustYear) {
		this.mustYear = mustYear;
	}

	public String getFactYear() {
		return factYear;
	}

	public void setFactYear(String factYear) {
		this.factYear = factYear;
	}

	public List<MustFactVo> getResultLis() {
		return resultLis;
	}

	public void setResultLis(List<MustFactVo> resultLis) {
		this.resultLis = resultLis;
	}

	public String shopReport() {
		String yearQuery;
		String monthQuery;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isBlank(reportDate)) {
			yearQuery = format.format(DateOperator.getDateNow()).substring(0, 4);
			monthQuery = format.format(DateOperator.getDateNow()).substring(5, 7);
		} else {
			yearQuery = format.format(reportDate).substring(0, 4);
			monthQuery = format.format(reportDate).substring(5, 7);
		}
		if (StringUtils.isBlank(bisProjectId)) {
			// bisProjectId ="40282b8927a42dff0127a4316b830001";
		}
		if (StringUtils.isBlank(chargeTypeCd)) {
			setChargeTypeCd("01,02");
		}
		Integer month = Integer.parseInt(monthQuery);
		shopTenantUtils = bisTenantManager.queryShopReport(bisProjectId, yearQuery, month, yearQuery, month,
				chargeTypeCd, manageCd);

		return "shop";
	}

	public String shopList() {
		// 搜索条件
		if (StringUtils.isBlank(chargeTypeCd)) {
			setChargeTypeCd("1,4");
		}
		String yearQuery;
		String monthQuery;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtils.isBlank(reportDate)) {
			yearQuery = format.format(DateOperator.getDateNow()).substring(0, 4);
			monthQuery = format.format(DateOperator.getDateNow()).substring(5, 7);
		} else {
			yearQuery = reportDate.substring(0, 4);
			monthQuery = reportDate.substring(5, 7);
		}
		if (StringUtils.isBlank(bisProjectId)) {
			bisProjectId = "40282b8927a42dff0127a4316b830001";
		}
		Integer month = Integer.parseInt(monthQuery);
		shopTenantUtils = bisTenantManager.queryShopReport(bisProjectId, yearQuery, month, yearQuery, month,
				chargeTypeCd, manageCd);

		return "shoplist";
	}

	/**
	 * 退铺发起
	 */
	public String shopBack() {
		
		nameCn = bisShopManager.getShopName(bisTenantId);
		bisStoreNos = bisTenantManager.getStoreNosByTenant(bisTenantId);
		
		return "shopBack";
	}
	
	public void prepareShopBackApprove() throws Exception {
		
		entity = bisTenantManager.getEntity(bisTenantId);
		
		tenantConts = bisContManager.find("from BisCont where bisTenantId=?", bisTenantId);

		List<Object[]> list = bisTenantManager.getTenantTotalFee(bisTenantId);
		for(Object[] obj : list) {
			BisTenantFeeVo vo = new BisTenantFeeVo();
			if("999".equals(obj[0])) {
				totalFeeVo = new BisTenantFeeVo();
				totalFeeVo.setMustTotal((BigDecimal) obj[1]);
				totalFeeVo.setFactTotal((BigDecimal) obj[2]);
			} else {
				vo.setChargeTypeCd((String) obj[0]);
				vo.setMustTotal((BigDecimal) obj[1]);
				vo.setFactTotal((BigDecimal) obj[2]);
				tenantFees.add(vo);
			}
		}
	}

	/**
	 * 退铺审核
	 */
	public String shopBackApprove() {
		
		nameCn = bisShopManager.getShopName(bisTenantId);
		bisStoreNos = bisTenantManager.getStoreNosByTenant(bisTenantId);
		
		return "shopBackApprove";
	}
	
	/**
	 * 退铺审核
	 */
	public String doShopBackApprove() {
		
		BisTenant bisTenant = bisTenantManager.getEntity(bisTenantId);
		bisTenant.setActiveBl(false);
		bisTenant.setStatusCd("2");
		
		bisTenantManager.saveBisTenant(bisTenant);
		
		return null;
	}
	
	/**
	 * 退铺驳回
	 */
	public String doShopBackReject() {
		
		BisTenant bisTenant = bisTenantManager.getEntity(bisTenantId);
		bisTenant.setStatusCd("3");
		
		bisTenantManager.saveBisTenant(bisTenant);
		
		return null;
	}


	public void prepareShopBackView() throws Exception {
		
		entity = bisTenantManager.getEntity(bisTenantId);
		
		tenantConts = bisContManager.find("from BisCont where bisTenantId=?", bisTenantId);

		List<Object[]> list = bisTenantManager.getTenantTotalFee(bisTenantId);
		for(Object[] obj : list) {
			BisTenantFeeVo vo = new BisTenantFeeVo();
			if("999".equals(obj[0])) {
				totalFeeVo = new BisTenantFeeVo();
				totalFeeVo.setMustTotal((BigDecimal) obj[1]);
				totalFeeVo.setFactTotal((BigDecimal) obj[2]);
			} else {
				vo.setChargeTypeCd((String) obj[0]);
				vo.setMustTotal((BigDecimal) obj[1]);
				vo.setFactTotal((BigDecimal) obj[2]);
				tenantFees.add(vo);
			}
		}
	}
	
	/**
	 * 退铺查看
	 */
	public String shopBackView() {
		
		nameCn = bisShopManager.getShopName(bisTenantId);
		bisStoreNos = bisTenantManager.getStoreNosByTenant(bisTenantId);
		
		return "shopBackApprove";
	}
	
	public void prepareGoStep() throws Exception {
		
		if("1".equals(step)) {
			tenantConts = bisContManager.find("from BisCont where bisTenantId=? order by contSmallTypeCd", bisTenantId);
		} else if("2".equals(step)) {

			List<Object[]> list = bisTenantManager.getTenantTotalFee(bisTenantId);
			for(Object[] obj : list) {
				BisTenantFeeVo vo = new BisTenantFeeVo();
				if("999".equals(obj[0])) {
					totalFeeVo = new BisTenantFeeVo();
					totalFeeVo.setMustTotal((BigDecimal) obj[1]);
					totalFeeVo.setFactTotal((BigDecimal) obj[2]);
				} else {
					vo.setChargeTypeCd((String) obj[0]);
					vo.setMustTotal((BigDecimal) obj[1]);
					vo.setFactTotal((BigDecimal) obj[2]);
					tenantFees.add(vo);
				}
			}
		} else if("3".equals(step)) {
			//TODO
			entity = bisTenantManager.getEntity(bisTenantId);
		}
	}

	/**
	 * 退铺流程
	 */
	public String goStep() {
		if("1".equals(step))
			return "sbStep1";
		else if("2".equals(step))
			return "sbStep2";
		else if("3".equals(step))
			return "sbStep3";
		return null;
	}
	
	/**
	 * 退铺提醒
	 */
	public String validateStep() {
		String msg = "success";
		if("1".equals(step)) {
			List<BisCont> list = bisContManager.find("from BisCont where bisTenantId=?", bisTenantId);
			for(BisCont obj : list) {
				if(obj.isActiveBl()) {
					msg = "有未失效合同，是否继续？";
				}
			}
		} else if("2".equals(step)) {
			
			List<Object[]> list = bisTenantManager.getTenantTotalFee(bisTenantId);
			for(Object[] obj : list) {
				if("999".equals(obj[0])) {
					BigDecimal mustTotal = (BigDecimal) obj[1];
					BigDecimal factTotal = (BigDecimal) obj[2];
					if(mustTotal.compareTo(factTotal) == 1) {
						msg = "费用未全部收齐，是否继续？";
					}
				}
			}
		}
		
		Struts2Utils.renderText(msg);
		return null;
	}
	
	/**
	 * 退铺完成
	 */
	public String shopBackComplete() {
		
		BisTenant bisTenant = bisTenantManager.getEntity(bisTenantId);
		bisTenant.setStatusCd("1");
		bisTenant.setAttachFlg(attachFlg);
		bisTenant.setBackReason(backReason);
		bisTenant.setBackDate(DateOperator.parse(backDate, "yyyy-MM-dd"));
		bisTenant.setRemark(remark);
		
		bisTenantManager.saveBisTenant(bisTenant);
		
		return null;
	}
	
	/**
	 * 查看商家历史
	 */
	public String viewShopHis() {
		
		bisShopId = bisTenantManager.getEntity(bisTenantId).getBisShopId();
		nameCn = bisShopManager.getShopName(bisTenantId);
		bisStoreNos = bisTenantManager.getStoreNosByTenant(bisTenantId);
		StringBuffer hql = new StringBuffer();
		hql .append(" from BisTenant t where t.bisTenantId!=:bisTenantId")
			.append(" and t.bisShopId = (")
			.append(" select ti.bisShopId from BisTenant ti where ti.bisTenantId=:bisTenantId)")
			.append(" order by t.rentStartDate desc");
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisTenantId", bisTenantId);
		List<BisTenant> list = bisTenantManager.find(hql.toString(), param);
		for(BisTenant bisTenant : list) {
			BisTenantVo vo = new BisTenantVo();
			vo.setBisTenant(bisTenant);
//			vo.setShopName(bisShopManager.getShopName(bisTenant.getBisTenantId()));
//			vo.setShopConnName(bisShopManager.getShopConnName(bisTenant.getBisTenantId()));
			vo.setBisStoreNos(bisTenantManager.getStoreNosByTenant(bisTenant.getBisTenantId()));
			tenantHisList.add(vo);
		}
		
		return "shopHis";
	}
	
	/**
	 * 查看商铺历史
	 */
	public String viewStoreHis() {
		
		StringBuffer hql = new StringBuffer();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisTenantId", bisTenantId);
		
		if(StringUtils.isNotBlank(bisTenantId)) {
			
			bisShopId = bisTenantManager.getEntity(bisTenantId).getBisShopId();
			nameCn = bisShopManager.getShopName(bisTenantId);
			bisStoreNos = bisTenantManager.getStoreNosByTenant(bisTenantId);
			Object storeIds = bisTenantManager.getStoreIds(bisTenantId).toArray();
			param.put("bisStoreIds", storeIds);
			
			hql .append(" from BisTenant t where t.bisTenantId!=:bisTenantId and t.bisTenantId in (")
				.append(" select bc.bisTenantId from BisStoreContRel rel join rel.bisCont bc ")
				.append(" where rel.bisStore.bisStoreId in (:bisStoreIds))")
				.append(" order by t.rentStartDate desc");

		} else if(StringUtils.isNotBlank(bisStoreIds)) {
			bisStoreNos = bisStoreManager.getEntity(bisStoreIds).getStoreNo();
			
			hql .append(" from BisTenant t where t.bisTenantId in (")
				.append(" select bc.bisTenantId from BisStoreContRel rel join rel.bisCont bc ")
				.append(" where rel.bisStore.bisStoreId = :bisStoreId)")
				.append(" order by t.rentStartDate desc");
			
			param.put("bisStoreId", bisStoreIds);
		}
		
		List<BisTenant> list = bisTenantManager.find(hql.toString(), param);
		for(BisTenant bisTenant : list) {
			BisTenantVo vo = new BisTenantVo();
			vo.setBisTenant(bisTenant);
			vo.setShopName(bisShopManager.getShopName(bisTenant.getBisTenantId()));
//			vo.setShopConnName(bisShopManager.getShopConnName(bisTenant.getBisTenantId()));
			vo.setBisStoreNos(bisTenantManager.getStoreNosByTenant(bisTenant.getBisTenantId()));
			tenantHisList.add(vo);
		}
		
		return "storeHis";
	}
	
	public String getContStoreNos(String bisContId) {
		
		return bisContManager.getStoreIdAndNo(bisContId)[1];
	}
	
	public String getChargeTypeCd() {
		return chargeTypeCd;
	}

	public void setChargeTypeCd(String chargeTypeCd) {
		this.chargeTypeCd = chargeTypeCd;
	}

	public List<BisShopTenantUtil> getShopTenantUtils() {
		return shopTenantUtils;
	}

	public void setShopTenantUtils(List<BisShopTenantUtil> shopTenantUtils) {
		this.shopTenantUtils = shopTenantUtils;
	}

	/**
	 * 初始化项目列表
	 * 
	 * @return
	 */
	public Map<String, String> getMapBisProject() {
		Map<String, String> mapBisProject = bisProjectManager.getMapBisProject();
		return mapBisProject;
	}

	public BigDecimal getSquareAll() {
		return SquareAll;
	}

	public void setSquareAll(BigDecimal squareAll) {
		SquareAll = squareAll;
	}

	public BisTenant getEntity() {
		return entity;
	}

	public void setEntity(BisTenant entity) {
		this.entity = entity;
	}

	public Map<String, String> getMapBuildSel() {
		return mapBuildSel;
	}

	public void setMapBuildSel(Map<String, String> mapBuildSel) {
		this.mapBuildSel = mapBuildSel;
	}

	public String getBisFloorId() {
		return bisFloorId;
	}

	public void setBisFloorId(String bisFloorId) {
		this.bisFloorId = bisFloorId;
	}

	public List<ShopStoreVo> getShopStoreList() {
		return shopStoreList;
	}

	public void setShopStoreList(List<ShopStoreVo> shopStoreList) {
		this.shopStoreList = shopStoreList;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public BisFloor getBisFloor() {
		return bisFloor;
	}

	public void setBisFloor(BisFloor bisFloor) {
		this.bisFloor = bisFloor;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getBuildingNum() {
		return buildingNum;
	}

	public void setBuildingNum(String buildingNum) {
		this.buildingNum = buildingNum;
	}

	public String getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}

	public List<BisStore> getListBisStores() {
		return listBisStores;
	}

	public void setListBisStores(List<BisStore> listBisStores) {
		this.listBisStores = listBisStores;
	}

	public String getBisStoreNo() {
		return bisStoreNo;
	}

	public void setBisStoreNo(String bisStoreNo) {
		this.bisStoreNo = bisStoreNo;
	}

	public String getStoreShopNo() {
		return storeShopNo;
	}

	public void setStoreShopNo(String storeShopNo) {
		this.storeShopNo = storeShopNo;
	}

	public int getNowMonth() {
		return nowMonth;
	}

	public int getNowYear() {
		return nowYear;
	}

	public String getBuildingFloor() {
		return buildingFloor;
	}

	public ShopStoreVo getShopStoreVo() {
		return shopStoreVo;
	}

	public String getManageCd() {
		return manageCd;
	}

	public void setManageCd(String manageCd) {
		this.manageCd = manageCd;
	}

	public String getFloorBigPicUrl() {
		return floorBigPicUrl;
	}

	public void setFloorBigPicUrl(String floorBigPicUrl) {
		this.floorBigPicUrl = floorBigPicUrl;
	}

	public List<BisFloor> getFloorVirList() {
		return floorVirList;
	}

	public void setFloorVirList(List<BisFloor> floorVirList) {
		this.floorVirList = floorVirList;
	}

	public String getBisFloorVirId() {
		return bisFloorVirId;
	}

	public void setBisFloorVirId(String bisFloorVirId) {
		this.bisFloorVirId = bisFloorVirId;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public String getFilter_LIKES_storeNo() {
		return filter_LIKES_storeNo;
	}

	public void setFilter_LIKES_storeNo(String filter_LIKES_storeNo) {
		this.filter_LIKES_storeNo = filter_LIKES_storeNo;
	}

	public String getFilter_LIKES_shopName() {
		return filter_LIKES_shopName;
	}

	public void setFilter_LIKES_shopName(String filter_LIKES_shopName) {
		this.filter_LIKES_shopName = filter_LIKES_shopName;
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

	public String[] getChkIds() {
		return chkIds;
	}

	public void setChkIds(String[] chkIds) {
		this.chkIds = chkIds;
	}

	public String getMergeIds() {
		return mergeIds;
	}

	public void setMergeIds(String mergeIds) {
		this.mergeIds = mergeIds;
	}

	public String getStandardTenantId() {
		return standardTenantId;
	}

	public String getBackType() {
		return backType;
	}

	public void setBackType(String backType) {
		this.backType = backType;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getBackReason() {
		return backReason;
	}

	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}

	public String getBackDate() {
		return backDate;
	}

	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}

	public String getAttachFlg() {
		return attachFlg;
	}

	public void setAttachFlg(String attachFlg) {
		this.attachFlg = attachFlg;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BisTenantFeeVo getTotalFeeVo() {
		return totalFeeVo;
	}

	public List<BisCont> getTenantConts() {
		return tenantConts;
	}

	public List<BisTenantFeeVo> getTenantFees() {
		return tenantFees;
	}

	public List<BisTenantVo> getTenantHisList() {
		return tenantHisList;
	}

	public void setStandardTenantId(String standardTenantId) {
		this.standardTenantId = standardTenantId;
	}

	public Map<String, String> getMapBisShopConn() {
		return mapBisShopConn;
	}

	public Map<String, String> getMapBisFloor() {
		return mapBisFloor;
	}

	public String getAdNo() {
		return adNo;
	}

	public void setAdNo(String adNo) {
		this.adNo = adNo;
	}

	public List<VoCombo> getStoreLayoutCds() {
		return storeLayoutCds;
	}

	public void setStoreLayoutCds(List<VoCombo> storeLayoutCds) {
		this.storeLayoutCds = storeLayoutCds;
	}

	public List<BisMulti> getListBisMultis() {
		return listBisMultis;
	}

	public void setListBisMultis(List<BisMulti> listBisMultis) {
		this.listBisMultis = listBisMultis;
	}

	public Map<String, String> getMapSubFloor() {
		return mapSubFloor;
	}

	public BisMulti getBisMulti() {
		return bisMulti;
	}

	public void setBisMulti(BisMulti bisMulti) {
		this.bisMulti = bisMulti;
	}

	public String getBisMultiId() {
		return bisMultiId;
	}

	public void setBisMultiId(String bisMultiId) {
		this.bisMultiId = bisMultiId;
	}

	public List<VoFact> getOverDueList() {
		return overDueList;
	}

	public String getOwners() {
		return owners;
	}

	public void setOwners(String owners) {
		this.owners = owners;
	}

}
