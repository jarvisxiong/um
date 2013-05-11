package com.hhz.ump.web.bis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.DateOperator;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.bis.BisContManager;
import com.hhz.ump.dao.bis.BisFlatManager;
import com.hhz.ump.dao.bis.BisFloorManager;
import com.hhz.ump.dao.bis.BisMultiManager;
import com.hhz.ump.dao.bis.BisProjectManager;
import com.hhz.ump.dao.bis.BisStoreManager;
import com.hhz.ump.entity.bis.BisCont;
import com.hhz.ump.entity.bis.BisFlat;
import com.hhz.ump.entity.bis.BisFloor;
import com.hhz.ump.entity.bis.BisMulti;
import com.hhz.ump.entity.bis.BisProject;
import com.hhz.ump.entity.bis.BisStore;
import com.hhz.uums.entity.ws.WsPlasOrg;

public class BisProjectAction extends CrudActionSupport<BisProject> {
	@Autowired
	private BisProjectManager bisProjectManager;
	@Autowired
	private BisFloorManager bisFloorManager;
	@Autowired
	private BisFlatManager bisFlatManager;
	@Autowired
	private BisStoreManager bisStoreManager;
	@Autowired
	private BisMultiManager bisMultiManager;
	@Autowired
	private BisContManager bisContManager;
	// private List<BisProject> bisProject;
	private Page<BisStore> pageStore = new Page<BisStore>(10);
	private Page<BisFlat> pageFlat = new Page<BisFlat>(10);
	private Page<BisMulti> pageMulti = new Page<BisMulti>(10);// 多经基础信息列表
	private BisProject entity;
	private String bisFlatId;
	private String bisStoreId;
	private BisFloor bisFloor;
	private BisFlat bisFlat;
	private List<BisMulti> bisMultiLis;
	private List<BisStore> bisStoreLis;
	private List<BisFlat> bisFlatLis;
	private List<BisFloor> bisFloorList;
	private BisMulti bisMulti;
	private BisStore bisStore;
	private String floorType;// 楼宇类型：公寓/商铺
	private String bisProjectId;
	private String bisProjectName;
	private String contBigTypeCd;
	private String bisFloorId;
	private String floorNum;
	private String buildingNum;
	private String ifSubmit;
	private String num;
	private boolean projAdmin = true; // 项目权限
	private String bisStoreIdsTemp;
	private String bisStoreNosTemp;
	private String filter_LIKES_storeNo;
	private Map<String, String> mapFloorSel;
	private Map<String, String> mapBuildSel;
	private Map<String, String> queryIdNoMap;
	private BisFloor virtualFloor;// 逻辑分区
	private int currentPageNo;// 跳转页面

	@Override
	public void prepareSave() throws Exception {
		prepareModel();
	}

	@Override
	protected void prepareModel() throws Exception {
		if (floorType.equals("2")) {
			if (StringUtils.isNotBlank(getBisFlatId())) {
				bisFlat = bisFlatManager.getEntity(getBisFlatId());
			} else {
				bisFlat = new BisFlat();
			}
		} else {
			if (StringUtils.isNotBlank(getBisStoreId())) {
				bisStore = bisStoreManager.getEntity(getBisStoreId());
			} else {
				bisStore = new BisStore();
			}
		}
	}

	@Override
	public BisProject getModel() {
		// TODO Auto-generated method stub
		return entity;
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

	/**
	 * 初始化商铺楼层列表
	 * 
	 * @return
	 */
	public Map<String, String> getMapFloor() {
		Map<String, String> mapFloor = bisFloorManager.getMapFloor();
		return mapFloor;
	}

	/**
	 * 初始化公寓楼号列表
	 * 
	 * @return
	 */
	public Map<String, String> getMapBuilding() {
		Map<String, String> mapBuilding = bisFloorManager.getMapBuilding();
		return mapBuilding;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 唯一性判断搜索语句
	 */
	private String uniqueExistQuery(boolean isExport) {
		String nos = Struts2Utils.getParameter("nos");
		StringBuffer sql = new StringBuffer().append("select * from bis_project a, ");
		if (floorType.equals("1")) {
			sql.append("bis_floor b, bis_store  c where a.bis_project_id=b.bis_project_id and b.bis_project_id=c.bis_project_id ").append(
					" and c.bis_floor_id = b.bis_floor_id ");
		} else if (floorType.equals("2")) {
			sql.append(" bis_floor b, bis_flat  c  where a.bis_project_id=b.bis_project_id and b.bis_project_id=c.bis_project_id ").append(
					" and c.bis_floor_id = b.bis_floor_id ");
		} else {
			sql.append(" BIS_MULTI  c where c.bis_project_id=a.bis_project_id ");
		}
		if (StringUtils.isNotBlank(bisProjectId)) {
			sql.append(" and a.bis_project_id = '").append(bisProjectId).append("'");
		}
		if (StringUtils.isNotBlank(bisFloorId) && (floorType.equals("1") || floorType.equals("2"))) {
			sql.append(" and b.bis_Floor_id = '").append(bisFloorId).append("'");
		}
		if (floorType.equals("1")) {
			sql.append("and c.store_no = '").append(nos).append("'");
		} else if (floorType.equals("2")) {
			sql.append("and c.flat_no = '").append(nos).append("'");
		} else {
			sql.append("and c.MULTI_NAME = '").append(nos).append("'");
		}
		return sql.toString();
	}

	/**
	 * 编号唯一性判断
	 * 
	 * @return
	 * @throws Exception
	 */
	public String uniqueExist() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(bisFloorId)) {
			String[] newbisFloorId = bisFloorId.split("`");
			bisFloorId = newbisFloorId[0];
		}
		String sql = uniqueExistQuery(true);
		List result = bisStoreManager.findBySql(sql, param);
		if (result.size() > 0) {
			Struts2Utils.renderHtml("failure");
		} else {
			Struts2Utils.renderHtml("success");
		}
		return null;
	}

	/**
	 * 单个商铺详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String storeDetail() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			bisStore = bisStoreManager.getEntity(getId());
			Log.debug(getId() + "=========" + bisStore.getVirtualArea());
			List<BisFloor> result = bisFloorManager.getFloorByProject(bisStore.getBisFloor().getBisProject().getBisProjectId(), bisStore.getBisFloor()
					.getFloorNum(), null, bisStore.getBisFloor().getBisFloorId(), bisStore.getVirtualArea());
			if (result.size() == 1) {
				virtualFloor = result.get(0);
			}
		}

		Log.debug(getId() + "=========" + bisStore.getVirtualArea());
		return "storeDetail";
	}

	/**
	 * 单个公寓详细信息搜索
	 * 
	 * @return
	 * @throws Exception
	 */
	public String flatDetail() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			bisFlat = bisFlatManager.getEntity(getId());
		}
		return "flatDetail";
	}

	/**
	 * 多经详细信息搜索
	 * 
	 * @return
	 * @throws Exception
	 */
	public String multiDetail() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			bisMulti = bisMultiManager.getEntity(getId());
		}
		return "multiDetail";
	}

	/**
	 * 商铺信息列表搜索
	 * 
	 * @param isExport
	 * @return
	 */
	private String bisStoreQuery(boolean isExport) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from BisStore a  where 1=1 and (splitStatus is null or splitStatus = '0') and(ifSubmit='1' or ifSubmit='2' or ifSubmit is null ) ");
		if (StringUtils.isNotBlank(bisProjectId)) {
			hql.append(" and a.bisProjectId = '").append(bisProjectId).append("'");
		}
		if (StringUtils.isNotBlank(num)) {
			hql.append(" and a.storeNo  like '%").append(num).append("%'");
		}
		if (StringUtils.isNotBlank(bisFloorId)) {
			hql.append(" and  a.bisFloor.bisFloorId = '").append(bisFloorId).append("'");// 此处bisFloorId为‘null’有效防止编辑信息时无法获取公用项目
		}
		hql.append(" order by storeNo, a.bisFloor.bisFloorId desc");
		return hql.toString();
	}

	/**
	 * 商铺信息列表搜索
	 * 
	 * @param isExport
	 * @return
	 */
	// private String bisStoreSql(boolean isExport){
	// StringBuffer hql = new StringBuffer();
	// hql.append(" select store_no , bis_floor_id from Bis_Store a  where 1=1 ");
	// if(StringUtils.isNotBlank(bisProjectId)){
	// hql.append(" and a.bis_Project_Id = '").append(bisProjectId).append("'");
	// }
	// if(StringUtils.isNotBlank(bisFloorId)){
	// hql.append(" and  a.bis_Floor_Id = '").append(bisFloorId).append("'");//此处bisFloorId为‘null’有效防止编辑信息时无法获取公用项目
	// }
	// return hql.toString();
	// }
	/**
	 * 公寓信息列表搜索
	 * 
	 * @param isExport
	 * @return
	 */
	private String bisFlatQuery(boolean isExport) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from BisFlat a  where 1=1 ");
		if (StringUtils.isNotBlank(bisProjectId)) {
			hql.append(" and a.bisProjectId = '").append(bisProjectId).append("'");
		}
		if (StringUtils.isNotBlank(num)) {
			hql.append(" and a.flatNo like '%").append(num).append("%'");
		}
		if (StringUtils.isNotBlank(bisFloorId)) {
			hql.append(" and  a.bisFloor.bisFloorId = '").append(bisFloorId).append("'");// 此处bisFloorId为‘null’有效防止编辑信息时无法获取公用项目
		}
		hql.append(" order by flatNo, a.bisFloor.bisFloorId desc");
		return hql.toString();
	}

	/**
	 * 多经信息列表搜索
	 * 
	 * @param isExport
	 * @return
	 */
	private String bisMultiQuery(boolean isExport) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from BisMulti a  where 1=1 ");
		if (StringUtils.isNotBlank(num)) {
			hql.append(" and a.multiName  like '%").append(num).append("%'");
		}
		if (StringUtils.isNotBlank(bisProjectId)) {
			hql.append(" and a.bisProjectId = '").append(bisProjectId).append("'");
		} else {
			hql.append(" and a.bisProjectId is null");// 防止编辑多经信息时无法获取公用项目
		}
		hql.append(" order by multiName ");
		return hql.toString();
	}

	/**
	 * 多经基础信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String multiList() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		pageMulti = bisMultiManager.findPage(pageMulti, bisMultiQuery(false), param);
		bisMultiLis = pageMulti.getResult();
		return "multiList";
	}

	/**
	 * 接口方法,合同管理选择商铺、多经、公寓,编号
	 * 
	 * @return
	 * @throws Exception
	 */
	public String selectList() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		if (null != contBigTypeCd) {
			floorType = contBigTypeCd;
		}
		if (floorType.equals("1")) {
			String hql = bisStoreQuery(false);
			bisStoreLis = bisStoreManager.find(hql, param);
		} else if (floorType.equals("2")) {
			String hql = bisFlatQuery(false);
			bisFlatLis = bisFlatManager.find(hql, param);
		} else {
			String hql = bisMultiQuery(false);
			bisMultiLis = bisMultiManager.find(hql, param);
		}
		return "selectList";
	}

	public String selectFloorNum() throws Exception {
		// TODO Auto-generated method stub
		if (null != contBigTypeCd) {
			floorType = contBigTypeCd;
		}
		if (floorType.equals("1")) {
			mapFloorSel = new HashMap<String, String>();
			mapFloorSel = bisFloorManager.getMapFloorSel(getBisProjectId(), getFloorType());
		} else {
			mapBuildSel = new HashMap<String, String>();
			mapBuildSel = bisFloorManager.getMapBuildSel(getBisProjectId(), getFloorType());
		}
		return "floorNum";
	}

	/**
	 * 选择商铺/公寓/多经
	 */
	public String doStoreSelect() throws Exception {

		if ("1".equals(floorType)) {
			mapFloorSel = bisFloorManager.getMapFloorSel(bisProjectId, floorType);
			mapFloorSel.remove("");
		} else if ("2".equals(floorType)) {
			mapBuildSel = bisFloorManager.getMapBuildSel(bisProjectId, floorType);
			mapBuildSel.remove("");
		}

		return "storeSelect";
	}

	public String doFloorSelect() throws Exception {

		if ("1".equals(floorType)) {
			mapFloorSel = bisFloorManager.getMapFloorSel(bisProjectId, floorType);
			mapFloorSel.remove("");
		} else if ("2".equals(floorType)) {
			mapBuildSel = bisFloorManager.getMapBuildSel(bisProjectId, floorType);
			mapBuildSel.remove("");
		}

		return "floorSelect";
	}

	/**
	 * 根据楼层加载商铺/公寓/多经列表
	 */
	public String loadStoreList() throws Exception {

		Map<String, Object> param = new HashMap<String, Object>();
		queryIdNoMap = new LinkedHashMap<String, String>();
		if ("1".equals(floorType)) {
			StringBuffer hql = new StringBuffer(" from BisStore bs where statusCd='1' ");
			if (StringUtils.isNotBlank(bisProjectId)) {
				hql.append(" and bs.bisProjectId=:bisProjectId");
				param.put("bisProjectId", bisProjectId);
			}
			if (StringUtils.isNotBlank(bisFloorId)) {
				hql.append(" and bs.bisFloor.bisFloorId=:bisFloorId");
				param.put("bisFloorId", bisFloorId);
			}
			if (StringUtils.isNotBlank(filter_LIKES_storeNo)) {
				hql.append(" and upper(bs.storeNo) like :storeNo");
				param.put("storeNo", "%" + filter_LIKES_storeNo.toUpperCase() + "%");
			}
			hql.append(" order by bs.storeNo");

			List<BisStore> list = bisStoreManager.find(hql.toString(), param);
			for (BisStore store : list) {
				queryIdNoMap.put(store.getBisStoreId(), store.getStoreNo());
			}

		} else if ("2".equals(floorType)) {
			StringBuffer hql = new StringBuffer(" from BisFlat bf where 1=1 ");
			if (StringUtils.isNotBlank(bisProjectId)) {
				hql.append(" and bf.bisProjectId=:bisProjectId");
				param.put("bisProjectId", bisProjectId);
			}
			if (StringUtils.isNotBlank(bisFloorId)) {
				hql.append(" and bf.bisFloor.bisFloorId=:bisFloorId");
				param.put("bisFloorId", bisFloorId);
			}
			if (StringUtils.isNotBlank(filter_LIKES_storeNo)) {
				hql.append(" and bf.flatNo like :flatNo");
				param.put("flatNo", "%" + filter_LIKES_storeNo.toUpperCase() + "%");
			}
			hql.append(" order by flatNo");
			List<BisFlat> list = bisFlatManager.find(hql.toString(), param);
			for (BisFlat flat : list) {
				queryIdNoMap.put(flat.getBisFlatId(), flat.getFlatNo());
			}

		} else {
			StringBuffer hql = new StringBuffer(" from BisMulti bm where 1=1 ");
			if (StringUtils.isNotBlank(bisProjectId)) {
				hql.append(" and bm.bisProjectId=:bisProjectId");
				param.put("bisProjectId", bisProjectId);
			}
			if (StringUtils.isNotBlank(filter_LIKES_storeNo)) {
				hql.append(" and bm.multiName like :multiName");
				param.put("multiName", "%" + filter_LIKES_storeNo.toUpperCase() + "%");
			}
			hql.append(" order by multiName");
			List<BisMulti> list = bisMultiManager.find(hql.toString(), param);
			for (BisMulti multi : list) {
				queryIdNoMap.put(multi.getBisMultiId(), multi.getMultiName());
			}
		}
		return "storeList";
	}

	public String tenantFloor() throws Exception {
		// TODO Auto-generated method stub
		mapFloorSel = new HashMap<String, String>();
		mapFloorSel = bisFloorManager.getMapFloorSel(getBisProjectId(), getFloorType());
		return "tenantFloor";
	}

	public String tenantFloorInfo() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		String hql = bisStoreQuery(false);
		bisStoreLis = bisStoreManager.find(hql, param);
		for (int i = 0; i < bisStoreLis.size(); i++) {

			// bisStoreContRel=bisStoreContRelManager.get
		}
		return "tenantInfo";
	}

	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(bisFloorId)) {
			String[] newbisFloorId = bisFloorId.split("`");
			bisFloorId = newbisFloorId[0];
		}
		if (floorType.equals("1")) {
			if (currentPageNo != 0) {
				pageStore.setPageNo(currentPageNo);
			}
			pageStore = bisStoreManager.findPage(pageStore, bisStoreQuery(false), param);
			bisStoreLis = pageStore.getResult();
			// 搜索是否可以拆分商铺
			for (BisStore store : bisStoreLis) {
				List<BisCont> contList = bisContManager.storeSplitValidate(store.getBisStoreId());
				// 若没LIST可以直接拆分，若有list，则判断合同日期有没过期，若没有，则不能加值，
				if (contList == null || contList.size() <= 0) {
					store.setRemark(storeRemark(store));

				} else {
					// 若有合同，判断该合同是否失效
					boolean haveActive = haveActiveBl(contList);
					if (!haveActive) {
						store.setRemark("1");
					} else {
						// 若有合同关联，且合同失效，则判断是否有兄弟结点被合同引用，若被引用，则不能还原
						store.setRemark(storeRemark(store));
					}
				}
			}
		} else {
			pageFlat = bisFlatManager.findPage(pageFlat, bisFlatQuery(false), param);
			bisFlatLis = pageFlat.getResult();
		}
		// 显示公司名称
		// bisProject=page.getResult();

		return "list";
	}

	/**
	 * 判断商铺能否拆分还原，1：不能拆分还原; "":可以拆分还原
	 * 
	 * @param store
	 * @return
	 */
	private String storeRemark(BisStore store) {
		String remark = "";
		// 若该商铺为孩子结点，则搜索其兄弟是否有关联合同,若有关联合同，则不允许有还原按钮
		if ("1".equals(store.getIfChild())) {
			String storeHql = "from BisStore where parentStoreId=:parentId and bisStoreId!=:bisStoreId";
			Map<String, Object> paramHql = new HashMap<String, Object>();
			paramHql.put("parentId", store.getParentStoreId());
			paramHql.put("bisStoreId", store.getBisStoreId());
			List<BisStore> storeList = bisStoreManager.find(storeHql, paramHql);
			List<BisCont> bisContList = new ArrayList<BisCont>();
			Boolean haveChildCont = false;// 是否有合同,默认为没有
			for (BisStore st : storeList) {
				bisContList = bisContManager.storeSplitValidate(st.getBisStoreId());
				if (bisContList == null || bisContList.size() <= 0) {
					break;
				} else {
					if (!haveActiveBl(bisContList)) {
						haveChildCont = true;
						/*
						 * //若合同失效，则可以修改 store.setRemark("");
						 */
					}
				}
			}
			if (haveChildCont) {
				remark = "1";
			}
		}
		return remark;
	}

	/**
	 * 合同是否失效，true为无用合同
	 * 
	 * @param contList
	 * @return
	 */
	private boolean haveActiveBl(List<BisCont> contList) {
		boolean haveActive = false;
		for (BisCont cont : contList) {
			// 若合同失效，直接退出，可以拆分
			if (cont.isActiveBl() == false) {
				haveActive = true;
				break;
			} else {
				if (cont.getContEndDate() != null && cont.getContEndDate().compareTo(DateOperator.getDateNow()) < 0) {
					haveActive = true;
					break;
				}
			}
		}
		return haveActive;
	}

	@Override
	public void prepareInput() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = bisProjectManager.getEntity(getId());
		} else {
			// entity = new BisProject();
		}
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	public void prepareSaveProject() throws Exception {
		prepareInput();
	}

	public String saveProject() throws Exception {

		bisProjectManager.saveBisProject(entity);
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isNotBlank(bisFloorId)) {
			String[] newbisFloorId = bisFloorId.split("`");
			bisFloorId = newbisFloorId[0];
		}
		if (floorType.equals("1")) {
			BisFloor bisFloor = bisFloorManager.getEntity(bisFloorId);
			// 新建statusCd为1
			bisStore.setStatusCd("1");
			bisStore.setBisFloor(bisFloor);
			bisStore.setBisProjectId(bisProjectId);
			bisStoreManager.saveBisStore(bisStore);
		} else if (floorType.equals("2")) {
			BisFloor bisFloor = bisFloorManager.getEntity(bisFloorId);
			bisFlat.setBisFloor(bisFloor);
			bisFlat.setBisProjectId(bisProjectId);
			bisFlatManager.saveBisFlat(bisFlat);
		} else {
			bisMulti.setBisProjectId(bisProjectId);
			bisMultiManager.saveBisMulti(bisMulti);
		}
		Struts2Utils.renderHtml("success");
		return "main";
	}

	// public void prepareSaveStore() throws Exception{
	// prepareModel();
	// }
	public void prepareStore() throws Exception {
		if (StringUtils.isNotBlank(getBisStoreId())) {
			bisStore = bisStoreManager.getEntity(getBisStoreId());
		} else {
			bisStore = new BisStore();
		}

	}

	public String store() throws Exception {
		// TODO Auto-generated method stub
		Log.debug(bisStore.getVirtualArea());
		// if (bisStore != null &&
		// StringUtils.isNotBlank(bisStore.getRentStandard())) {
		// bisStore.setRentStandard(bisStore.getRentStandard().replace("'",
		// "‘"));
		// }
		bisStoreManager.saveBisStore(bisStore);
		return null;
	}

	public String saveFlat() throws Exception {
		// TODO Auto-generated method stub
		bisFlatManager.saveBisFlat(bisFlat);
		return null;
	}

	public String saveMulit() throws Exception {
		// TODO Auto-generated method stub
		bisMultiManager.saveBisMulti(bisMulti);
		return null;
	}

	@Override
	public String delete() throws Exception {
		if ((floorType == null || "".equals(floorType)) && StringUtils.isNotBlank(getId())) {// 商铺拆分时子商铺的类型为空
			bisStoreManager.deleteBisStore(getId());
			// bisStore.setSplitStatus("1");
			// bisStore.setIfSubmit("0");
			// bisStoreManager.saveBisStore(bisStore);
			// Map<String, Object> param = new HashMap<String, Object>();
			// StringBuffer hql = new
			// StringBuffer("from BisStore  where splitStatus !='1' AND parentStoreId= '");
			// hql.append(bisStoreManager.getEntity(getId()).getParentStoreId()).append("'");
			// bisStoreLis = bisStoreManager.find(hql.toString(), param);
			// if(bisStoreLis==null||bisStoreLis.size()==0){
			// String parentStoreId =
			// bisStoreManager.getEntity(getId()).getParentStoreId();
			// bisStore=bisStoreManager.getEntity(parentStoreId);
			// // bisStore.setIfSubmit("0");
			// bisStore.setSplitStatus("0");
			// bisStoreManager.saveBisStore(bisStore);
			// }
			Struts2Utils.renderHtml("success");
		} else {
			if (StringUtils.isNotBlank(getId())) {
				if (floorType.equals("1")) {
					bisStoreManager.deleteBisStore(getId());
					Struts2Utils.renderHtml("success");
				} else if (floorType.equals("2")) {
					bisFlatManager.deleteBisFlat(getId());
					Struts2Utils.renderHtml("success");
				} else if (floorType.equals("3")) {
					bisMultiManager.deleteBisMulti(getId());
					Struts2Utils.renderHtml("success");
				} else {

					Struts2Utils.renderHtml("failure");
				}
			}
		}
		return null;
	}

	/**
	 * 功能: 构造用户权限
	 */
	private void buildPermission() {
		List<WsPlasOrg> orgList = PlasCache.getPhysicalBubbleOrgListByOrgCd(SpringSecurityUtils.getCurrentDeptCd());
		if (StringUtils.isNotBlank(getId())) {
			String orgCd = bisProjectManager.getEntity(entity.getBisProjectId()).getOrgCd();
			for (WsPlasOrg org : orgList) {
				if (StringUtils.equals(orgCd, org.getOrgCd())) {
					projAdmin = false;
					break;
				}
			}
		}
	}

	public String main() throws Exception {

        Struts2Utils.getRequest().setAttribute("isProjectBusinessCompany", bisProjectManager.getCurrProject() != null);

		buildPermission();
		if (StringUtils.isNotBlank(bisStoreId)) {
			bisStore = bisStoreManager.getEntity(bisStoreId);
			bisProjectId = bisStore.getBisProjectId();
			floorType = bisStore.getBisFloor().getFloorType();

		}
		if (StringUtils.isBlank(bisProjectId)) {
			BisProject bisProject = bisProjectManager.getCurrProject();
			if (bisProject != null) {
				bisProjectId = bisProject.getBisProjectId();
				bisProjectName = bisProject.getProjectName();
			}
		} else {
			bisProjectName = bisProjectManager.getEntity(bisProjectId).getProjectName();
		}
		return "main";
	}

	/**
	 * 项目经营汇总楼层信息搜索
	 * 
	 * @param isExport
	 * @return
	 */
	private String bisFloorQuery(boolean isExport) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from BisFloor   where floorType='1' ");
		if (bisProjectId != null && !"".equals(bisProjectId)) {
			hql.append(" and bisProject.bisProjectId = '").append(bisProjectId).append("'");
		} else {
			hql.append(" and bisProject.bisProjectId is null");// 防止编辑多经信息时无法获取公用项目
		}
		hql.append(" order by floorNum,bisFloorId ");
		return hql.toString();
	}

	/**
	 * 项目经营情况汇总表接口
	 * 
	 * @return
	 * @throws Exception
	 */
	public String projectOperate() throws Exception {
		// Map<String, Object> param = new HashMap<String, Object>();
		// String hql =bisFloorQuery(false);
		// bisFloorList = bisFloorManager.find(hql,param);
		bisFloorList = bisFloorManager.getFloorByProject(bisProjectId);

		return "projOperate";
	}

	public String storeSplit() throws Exception {
		if (StringUtils.isNotBlank(bisStoreId)) {
			bisStore = bisStoreManager.getEntity(bisStoreId);
			if (StringUtils.isNotBlank(bisStore.getParentStoreId())) {
				bisStore = bisStoreManager.getEntity(bisStore.getParentStoreId());

			}
			if (bisStore != null) {
				bisProjectId = bisStore.getBisProjectId();
				bisProjectName = bisStore.getBisFloor().getBisProject().getProjectName();
				floorNum = bisStore.getBisFloor().getFloorNum().toString();
				buildingNum = bisStore.getBisFloor().getBuildingNum();
			}
		}
		return "storeSplit";
	}

	public String storeSplitSave() throws Exception {
		// Map<String, Object> param = new HashMap<String, Object>();
		// StringBuffer hql = new
		// StringBuffer("from BisStore  where splitStatus !='1'   AND parentStoreId= '");
		// hql.append(bisStore.getParentStoreId()).append("'");
		// bisStoreLis = bisStoreManager.find(hql.toString(), param);
		// BigDecimal square =bisStore.getSquare();
		// BigDecimal innerSquare =bisStore.getInnerSquare();
		// BigDecimal publicSquare =bisStore.getPublicSquare();
		// BigDecimal squareAll=new BigDecimal(0) ;
		// BigDecimal innerSquareAll=new BigDecimal(0) ;
		// BigDecimal publicSquareAll=new BigDecimal(0) ;
		// if(bisStoreLis!=null&&bisStoreLis.size()!=0){
		// for(BisStore sqr:bisStoreLis){
		// squareAll =squareAll.add(sqr.getSquare()==null?new
		// BigDecimal(0):sqr.getSquare());
		// innerSquareAll =innerSquareAll.add(sqr.getInnerSquare()==null?new
		// BigDecimal(0):sqr.getInnerSquare());
		// publicSquareAll =publicSquareAll.add(sqr.getPublicSquare()==null?new
		// BigDecimal(0):sqr.getPublicSquare());
		// }
		// }
		// 若商铺拆分，未提交时，statusCd置为0
		if (StringUtils.isNotBlank(bisStore.getParentStoreId())) {
			bisStore.setStatusCd("0");
		}
		bisStoreManager.saveBisStore(bisStore);
		// bisStore=bisStoreManager.getEntity(bisStore.getParentStoreId());
		// if(!(bisStore.getSplitStatus().equals("1"))){
		// bisStore.setSplitStatus("1");
		// bisStoreManager.saveBisStore(bisStore);
		// }
		Struts2Utils.renderHtml("success");
		return "storeSplit";
	}

	/**
	 * 商铺拆分提交(有建筑面积、套内面积、计租面积校验)
	 * 
	 * @return
	 * @throws Exception
	 */
	public String splitSubmit() throws Exception {
		bisStore = bisStoreManager.getEntity(bisStoreId);
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from BisStore  where splitStatus !='1'   AND parentStoreId= '");
		hql.append(bisStoreId).append("'");
		bisStoreLis = bisStoreManager.find(hql.toString(), param);
		BigDecimal square = bisStore.getSquare();
		// 建筑面积㎡(实测)
		BigDecimal squareReal = bisStore.getSquareReal();

		BigDecimal innerSquare = bisStore.getInnerSquare();
		// 套内面积㎡(实测)
		BigDecimal innerSquareReal = bisStore.getInnerSquareReal();
		// BigDecimal publicSquare =bisStore.getPublicSquare();
		BigDecimal rentSquare = bisStore.getRentSquare() == null ? new BigDecimal(0) : bisStore.getRentSquare();
		BigDecimal squareAll = new BigDecimal(0);
		BigDecimal squareRealAll = new BigDecimal(0);
		BigDecimal innerSquareAll = new BigDecimal(0);
		BigDecimal innerSquareRealAll = new BigDecimal(0);
		BigDecimal publicSquareAll = new BigDecimal(0);
		BigDecimal rentSquareAll = new BigDecimal(0);

		if (bisStoreLis != null && bisStoreLis.size() != 0) {
			for (BisStore sqr : bisStoreLis) {
				squareAll = squareAll.add(sqr.getSquare() == null ? new BigDecimal(0) : sqr.getSquare());
				squareRealAll = squareRealAll.add(sqr.getSquareReal() == null ? new BigDecimal(0) : sqr.getSquareReal());
				innerSquareAll = innerSquareAll.add(sqr.getInnerSquare() == null ? new BigDecimal(0) : sqr.getInnerSquare());
				innerSquareRealAll = innerSquareRealAll.add(sqr.getInnerSquareReal() == null ? new BigDecimal(0) : sqr.getInnerSquareReal());
				publicSquareAll = publicSquareAll.add(sqr.getPublicSquare() == null ? new BigDecimal(0) : sqr.getPublicSquare());
				rentSquareAll = rentSquareAll.add(sqr.getRentSquare() == null ? new BigDecimal(0) : sqr.getRentSquare());

			}
		}
		boolean isNMustCond = true;
		// 非必段条件是否验证验过
		if ((squareReal != null && !squareReal.equals(squareRealAll)) || (innerSquareReal != null && !innerSquareReal.equals(innerSquareRealAll))) {

			isNMustCond = false;
		}
		if ((square.equals(square) && innerSquare.equals(innerSquareAll) && rentSquare.equals(rentSquareAll)) && isNMustCond) {
			bisStore.setSplitStatus("1");
			// 若提交了，则父商铺状态为0
			bisStore.setStatusCd("0");
			bisStore.setIfSubmit("1");
			bisStoreManager.saveBisStore(bisStore);
			for (int i = 0; i < bisStoreLis.size(); i++) {
				bisStore = bisStoreLis.get(i);
				// 拆分商铺状态为1
				bisStore.setStatusCd("1");
				bisStore.setIfSubmit("1");
				bisStoreManager.saveBisStore(bisStore);
			}
			// StringBuffer hqls = new
			// StringBuffer("from BisStore  where splitStatus ='1' and ifSubmit='0'  AND parentStoreId= '");
			// hqls.append(bisStoreId).append("'");
			// bisStoreLis = bisStoreManager.find(hqls.toString(), param);
			// if(bisStoreLis!=null&&bisStoreLis.size()!=0){
			// for(int i=0;i<bisStoreLis.size();i++){
			// bisStore = bisStoreLis.get(i);
			// bisStore.setIfSubmit("1");
			// bisStoreManager.saveBisStore(bisStore);
			// }
			// }
			Struts2Utils.renderHtml("success");
		} else {
			if (!(square.equals(squareAll))) {
				Struts2Utils.renderHtml("建筑面积与拆分前不符");
				return null;
			} else if (!(innerSquareAll.equals(innerSquare))) {
				Struts2Utils.renderHtml("套内面积与拆分前不符");
				return null;
			} else if (!(rentSquare.equals(rentSquareAll))) {
				Struts2Utils.renderHtml("计租面积与拆分前不符");
			} else if (squareReal != null && !squareReal.equals(squareRealAll)) {
				Struts2Utils.renderHtml("建筑面(实测)与拆分前不符");
			} else if (innerSquareReal != null && !innerSquareReal.equals(innerSquareRealAll)) {
				Struts2Utils.renderHtml("套内面积(实测)与拆分前不符!");
			}
		}

		return null;
	}

	public String storeSplitSecrch() throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from BisStore  where splitStatus !='1'   AND parentStoreId= '");
		hql.append(bisStoreId).append("'");
		bisStoreLis = bisStoreManager.find(hql.toString(), param);
		return "storeSplitList";
	}

	public String splitRevarte() throws Exception {
		List<BisStore>saveBisStoreList=new ArrayList<BisStore>();
		
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer("from BisStore  where splitStatus ='0' and ifSubmit='1'  AND parentStoreId= '");
		hql.append(bisStoreId).append("'");
		//是否可以还原
		boolean isCanRevarte=true;
		bisStoreLis = bisStoreManager.find(hql.toString(), param);
		for (int i = 0; i < bisStoreLis.size(); i++) {
			bisStore = bisStoreLis.get(i);
			bisStore.setSplitStatus("1");
			if(bisStore.getBisStoreContRels()!=null && bisStore.getBisStoreContRels().size()>0){
				
				isCanRevarte=false;
				break;
			}
			// 还原，则子数据设为0
			bisStore.setStatusCd("0");
			
			saveBisStoreList.add(bisStore);
			//bisStoreManager.saveBisStore(bisStore);
		}	
		if(!isCanRevarte){
			//商铺已经被占用不允许还原
			Struts2Utils.renderHtml("notrevarte&"+bisStore.getStoreNo());
			return null;
		}
	
		bisStore = bisStoreManager.getEntity(bisStoreId);
		bisStore.setSplitStatus("0");
		// 还原，则主数据设为1
		bisStore.setStatusCd("1");
		bisStore.setIfSubmit("2");
		bisStoreManager.saveBisStore(bisStore);
		
		int count=saveBisStoreList.size();
		// 插入数据
		Session session = bisStoreManager.getDao().getSession();
		int submitTime = count % 100;// 获取插入记录个数
		for (int i = 0; i < count; i++) {
			session.saveOrUpdate(saveBisStoreList.get(i));
			if (i % 100 == 0) { // 每100条提交一次数据
				session.flush();
				session.clear();
			} else if (submitTime > 0 && i == count - 1) {
				// 如果余数不为0则说明需要多提交一次数据，否则数据则会少生成数据
				session.flush();
				session.clear();
			}

		}
		
		
		Struts2Utils.renderHtml("success");
		return null;
	}

	/**
	 * 联动获取楼层
	 */
	public void getFloorNo() throws Exception {

		List<BisFloor> list = bisFloorManager.find("from BisFloor where bisProject.bisProjectId=? and floorType=? order by sequenceNo,bisFloorId",
				bisProjectId, floorType);
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (BisFloor floor : list) {
			if ("1".equals(floorType)) {
				map.put(floor.getBisFloorId(), floor.getBuildingNum() + "~" + floor.getFloorNum());
			} else {
				map.put(floor.getBisFloorId(), floor.getBuildingNum());
			}
		}

		Struts2Utils.renderJson(map);
	}

	// public List<BisProject> getBisProject() {
	// return bisProject;
	// }
	//
	// public void setBisProject(List<BisProject> bisProject) {
	// this.bisProject = bisProject;
	// }

	public BisProject getEntity() {
		return entity;
	}

	public void setEntity(BisProject entity) {
		this.entity = entity;
	}

	public BisFloor getBisFloor() {
		return bisFloor;
	}

	public void setBisFloor(BisFloor bisFloor) {
		this.bisFloor = bisFloor;
	}

	// public String getPrjectName() {
	// return prjectName;
	// }
	//
	// public void setPrjectName(String prjectName) {
	// this.prjectName = prjectName;
	// }

	public String getFloorType() {
		return floorType;
	}

	public String getBisFlatId() {
		return bisFlatId;
	}

	public void setBisFlatId(String bisFlatId) {
		this.bisFlatId = bisFlatId;
	}

	public BisFlat getBisFlat() {
		return bisFlat;
	}

	public void setBisFlat(BisFlat bisFlat) {
		this.bisFlat = bisFlat;
	}

	public BisStore getBisStore() {
		return bisStore;
	}

	public void setBisStore(BisStore bisStore) {
		this.bisStore = bisStore;
	}

	public void setFloorType(String floorType) {
		this.floorType = floorType;
	}

	public String getBisProjectId() {
		return bisProjectId;
	}

	public void setBisProjectId(String bisProjectId) {
		this.bisProjectId = bisProjectId;
	}

	public String getBisStoreId() {
		return bisStoreId;
	}

	public String getBisProjectName() {
		return bisProjectName;
	}

	public void setBisProjectName(String bisProjectName) {
		this.bisProjectName = bisProjectName;
	}

	public void setBisStoreId(String bisStoreId) {
		this.bisStoreId = bisStoreId;
	}

	public Page<BisStore> getPageStore() {
		return pageStore;
	}

	public void setPageStore(Page<BisStore> pageStore) {
		this.pageStore = pageStore;
	}

	public Page<BisFlat> getPageFlat() {
		return pageFlat;
	}

	public void setPageFlat(Page<BisFlat> pageFlat) {
		this.pageFlat = pageFlat;
	}

	public String getBisFloorId() {
		return bisFloorId;
	}

	public void setBisFloorId(String bisFloorId) {
		this.bisFloorId = bisFloorId;
	}

	public Page<BisMulti> getPageMulti() {
		return pageMulti;
	}

	public void setPageMulti(Page<BisMulti> pageMulti) {
		this.pageMulti = pageMulti;
	}

	public BisMulti getBisMulti() {
		return bisMulti;
	}

	public void setBisMulti(BisMulti bisMulti) {
		this.bisMulti = bisMulti;
	}

	public List<BisMulti> getBisMultiLis() {
		return bisMultiLis;
	}

	public void setBisMultiLis(List<BisMulti> bisMultiLis) {
		this.bisMultiLis = bisMultiLis;
	}

	public List<BisFlat> getBisFlatLis() {
		return bisFlatLis;
	}

	public void setBisFlatLis(List<BisFlat> bisFlatLis) {
		this.bisFlatLis = bisFlatLis;
	}

	public List<BisStore> getBisStoreLis() {
		return bisStoreLis;
	}

	public void setBisStoreLis(List<BisStore> bisStoreLis) {
		this.bisStoreLis = bisStoreLis;
	}

	public String getContBigTypeCd() {
		return contBigTypeCd;
	}

	public void setContBigTypeCd(String contBigTypeCd) {
		this.contBigTypeCd = contBigTypeCd;
	}

	public Map<String, String> getMapFloorSel() {
		return mapFloorSel;
	}

	public void setMapFloorSel(Map<String, String> mapFloorSel) {
		this.mapFloorSel = mapFloorSel;
	}

	public Map<String, String> getMapBuildSel() {
		return mapBuildSel;
	}

	public void setMapBuildSel(Map<String, String> mapBuildSel) {
		this.mapBuildSel = mapBuildSel;
	}

	public List<BisFloor> getBisFloorList() {
		return bisFloorList;
	}

	public void setBisFloorList(List<BisFloor> bisFloorList) {
		this.bisFloorList = bisFloorList;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}

	public String getBuildingNum() {
		return buildingNum;
	}

	public void setBuildingNum(String buildingNum) {
		this.buildingNum = buildingNum;
	}

	public String getIfSubmit() {
		return ifSubmit;
	}

	public void setIfSubmit(String ifSubmit) {
		this.ifSubmit = ifSubmit;
	}

	public boolean isProjAdmin() {
		return projAdmin;
	}

	public void setProjAdmin(boolean projAdmin) {
		this.projAdmin = projAdmin;
	}

	public String getBisStoreIdsTemp() {
		return bisStoreIdsTemp;
	}

	public void setBisStoreIdsTemp(String bisStoreIdsTemp) {
		this.bisStoreIdsTemp = bisStoreIdsTemp;
	}

	public String getBisStoreNosTemp() {
		return bisStoreNosTemp;
	}

	public void setBisStoreNosTemp(String bisStoreNosTemp) {
		this.bisStoreNosTemp = bisStoreNosTemp;
	}

	public String getFilter_LIKES_storeNo() {
		return filter_LIKES_storeNo;
	}

	public void setFilter_LIKES_storeNo(String filter_LIKES_storeNo) {
		this.filter_LIKES_storeNo = filter_LIKES_storeNo;
	}

	public Map<String, String> getQueryIdNoMap() {
		return queryIdNoMap;
	}

	public BisFloor getVirtualFloor() {
		return virtualFloor;
	}

	public void setVirtualFloor(BisFloor virtualFloor) {
		this.virtualFloor = virtualFloor;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

}
