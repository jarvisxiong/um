/**
 * 
 */
package com.hhz.ump.web.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.ServiceException;
import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppDictDataManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.entity.app.AppDictData;
import com.hhz.ump.entity.app.AppDictType;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.JsonUtil2;

/**
 * @author huangj 2009-12-25
 */
@Namespace("/app")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-dict-type.action", type = "redirect"),
		@Result(name = CrudActionSupport.RELOAD_SUB, location = "app-dict-type!input.action", type = "redirect", params = { "id", "${id}" }) })
public class AppDictTypeAction extends CrudActionSupport<AppDictType> {
	private static final long serialVersionUID = -3445152342227169047L;

	private Page<AppDictType> page = new Page<AppDictType>(20);// 每页20条记录

	private AppDictData appDictData;

	private List<AppDictData> lstAppDictData = new ArrayList<AppDictData>();

	private String subId;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private AppDictDataManager appDictDataManager;

	private AppDictType entity;


	public AppDictTypeAction() {
	}

	public Map<String, String> getTypeCd() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.FIN_ITEM_TYPE);
	}

	public String deleteSub() throws Exception {
		try {
			appDictDataManager.deleteAppDictData(getSubId());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD_SUB;
	}

	@Override
	public String delete() throws Exception {
		try {

			appDictTypeManager.deleteAppDictType(getId());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}

	@Override
	public String input() throws Exception {
		CollectionHelper.sortList(entity.getAppDictDatas(), "dispOrderNo");
		if("1".equals(entity.getDefaultFlg())){
			entity.setDefaultFlg("true");
		}else {
			entity.setDefaultFlg("false");
		}
		return INPUT;
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	@Override
	public String list() throws Exception {
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		String filter_LIKES_dictTypeCd = Struts2Utils.getParameter("filter_LIKES_dictTypeCd");
		String filter_LIKES_dictTypeName = Struts2Utils.getParameter("filter_LIKES_dictTypeName");
		String defaultFlg = Struts2Utils.getParameter("filter_EQB_defaultFlg");
		Boolean filter_EQB_defaultFlg=BooleanUtils.toBooleanObject(defaultFlg);
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("dictTypeCd,dispOrderNo");
			page.setOrder(Page.ASC + "," + Page.ASC);
		} else {
			page.setOrderBy(sortField + ",dispOrderNo");
			page.setOrder(order + "," + Page.ASC);
		}
		StringBuffer hql = new StringBuffer("from AppDictType as a where 1=1 ");
		Map<String, Object> pram = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(filter_LIKES_dictTypeCd)) {
			hql.append(" and dictTypeCd like :dictTypeCd");
			pram.put("dictTypeCd", "%" + filter_LIKES_dictTypeCd + "%");
		}
		if (StringUtils.isNotEmpty(filter_LIKES_dictTypeName)) {
			hql.append(" and dictTypeName like :dictTypeName");
			pram.put("dictTypeName", "%" + filter_LIKES_dictTypeName + "%");
		}
		if (filter_EQB_defaultFlg != null) {
			hql.append(" and defaultFlg =:defaultFlg");
			pram.put("defaultFlg", filter_EQB_defaultFlg);
		}
		if (StringUtils.isEmpty(sortField)) {
			hql.append(" order by dispOrderNo asc,dictTypeCd asc");
		} else {
			hql.append(" order by ").append(sortField).append(" ").append(order).append(", dispOrderNo asc");
		}
		page = appDictTypeManager.findPage(page, hql.toString(), pram);
		JsonUtil2.renderJson(page,new String[]{"appDictDatas"});
		return null;
	}
	public String listSub() throws Exception {
		List<AppDictData> appDictDatas  = new ArrayList<AppDictData>();
		if(StringUtils.isNotBlank(getId())){
			entity=appDictTypeManager.getEntity(getId()); 
			appDictDatas =entity.getAppDictDatas();
		}
		JsonUtil2.renderListJson(appDictDatas, new String[] { "appDictType" });
		return null;
	}
	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String isTypeExists() {
		String newDictTypeCd = Struts2Utils.getParameter("dictTypeCd").trim();
		String oldDictTypeCd = Struts2Utils.getParameter("oldDictTypeCd").trim();

		if (appDictTypeManager.isPropertyUnique("dictTypeCd", newDictTypeCd, oldDictTypeCd)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		// 因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// prepareSubDetail();

		if (StringUtils.isNotBlank(getId())) {
			entity = appDictTypeManager.getEntity(getId());
			
		} else {
			entity = new AppDictType();
		}
	}
	@Override
	public void prepareSave ()throws Exception{
		prepareModel();
		if("true".equals(entity.getDefaultFlg())){
			entity.setDefaultFlg("1");
		}else {
			entity.setDefaultFlg("0");
		}
	}
	@Override
	public String save() throws Exception {
/*		if (lstAppDictData.size() > 0) {
			for (AppDictData appDictDataTmp : lstAppDictData) {
				appDictDataTmp.setAppDictType(entity);
				for (AppDictData appDictDataTmp2 : entity.getAppDictDatas()) {
					if (StringUtils.equals(appDictDataTmp.getAppDictDataId(), appDictDataTmp2.getAppDictDataId())) {
						PropertyUtils.copyProperties(appDictDataTmp2, appDictDataTmp);
					}
				}
				if (StringUtils.isEmpty(appDictDataTmp.getAppDictDataId())) {
					entity.getAppDictDatas().add(appDictDataTmp);
				}
			}
		}*/
		if("true".equals(entity.getDefaultFlg())){
			entity.setDefaultFlg("1");
		}else {
			entity.setDefaultFlg("0");
		}
		appDictTypeManager.saveEntity(entity);
		List<AppDictData> datasInsert = (List<AppDictData>) JsonUtil2.getInsertRecords(AppDictData.class);
		List<AppDictData> datasUpdated = (List<AppDictData>) JsonUtil2.getUpdatedRecords(AppDictData.class);
		refreshData(datasInsert);
		refreshData(datasUpdated);
		setId(entity.getAppDictTypeId());
		addActionMessage(getText("common.success"));
		return RELOAD_SUB;
	}
	private void refreshData(List<AppDictData> datas) throws Exception {
		if (datas.size() > 0) {
			AppDictData appDictDataTmp2 = null;
			for (AppDictData appDictDataTmp : datas) {
				if (StringUtils.isNotBlank(appDictDataTmp.getAppDictDataId())) {
					appDictDataTmp2 = appDictDataManager.getEntity(appDictDataTmp.getAppDictDataId());
				}else{
					entity.getAppDictDatas().add(appDictDataTmp);
					appDictDataTmp2 = new AppDictData();
				}
				appDictDataTmp2.setDictCd(appDictDataTmp.getDictCd());
				appDictDataTmp2.setDictName(appDictDataTmp.getDictName());
				appDictDataTmp2.setRemark(appDictDataTmp.getRemark());
				appDictDataTmp2.setDispOrderNo(appDictDataTmp.getDispOrderNo());
				appDictDataTmp2.setAppDictType(entity);
				
				appDictDataManager.saveAppDictData(appDictDataTmp2);
			}
		}
	}
	/**
	 * 数据字典quickSearch
	 * @param value
	 * @throws Exception
	 */
	public void quickSearch() throws Exception {
		Page<AppDictData> pageAppData = new Page<AppDictData>(15);
		Map<String, Object> param = new HashMap<String, Object>();
		String value = Struts2Utils.getParameter("value");
		String dictTypeCd = Struts2Utils.getParameter("dictTypeCd");
		StringBuffer hql = new StringBuffer().append(" from AppDictData where  1=1 ");
		hql.append(" and appDictType.appDictTypeId =");
		hql.append("(select appDictTypeId from AppDictType t where t.dictTypeCd=:dictTypeCd )");
		hql.append(" and dictName like :dictName");
		param.put("dictTypeCd", dictTypeCd);
		param.put("dictName", value);
		pageAppData = appDictDataManager.findPage(pageAppData, hql.toString(), param);
		List<Map<String, String> > list = new ArrayList<Map<String, String> >();
		for(AppDictData cl : pageAppData.getResult()){
			Map<String, String> map=new HashMap<String, String>();
			map.put("dictName", cl.getDictName());
			map.put("dictCd", cl.getDictCd());
			list.add(map);
		}
		Struts2Utils.renderJson(list);
	}
	public String saveItem() throws Exception {
		AppDictData dictData = appDictDataManager.getEntity(subId);
		dictData.setAppDictType(entity);
		return RELOAD_SUB;
	}

	public String subDetail() throws Exception {
		return "detail";
	}

	public AppDictType getModel() {
		return entity;
	}

	@Override
	public Page<AppDictType> getPage() {
		return page;
	}

	@Override
	public String deleteBatch() throws Exception {
		try {
			appDictTypeManager.deleteBatch(getIds());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}


	public AppDictData getAppDictData() {
		return appDictData;
	}

	public void setAppDictData(AppDictData appDictData) {
		this.appDictData = appDictData;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public List<AppDictData> getLstAppDictData() {
		return lstAppDictData;
	}

	public void setLstAppDictData(List<AppDictData> lstAppDictData) {
		this.lstAppDictData = lstAppDictData;
	}
}
