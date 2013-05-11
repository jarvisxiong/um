/**
 * 
 */
package com.hhz.uums.web.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.ServiceException;
import com.hhz.uums.dao.app.AppDictDataManager;
import com.hhz.uums.dao.app.AppDictTypeManager;
import com.hhz.uums.entity.app.AppDictData;
import com.hhz.uums.entity.app.AppDictType;
import com.hhz.uums.utils.DictMapUtil;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.web.CrudActionSupport;

/**
 * @author huangj 2009-12-25
 */
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "app-dict-type!list.action", type = "redirect"),
		@Result(name = CrudActionSupport.RELOAD_SUB, location = "app-dict-type!input.action", type = "redirect", params = { "id", "${id}" }) })
public class AppDictTypeAction extends CrudActionSupport<AppDictType> {
	private static final long serialVersionUID = -3445152342227169047L;

	private Page<AppDictType> page = new Page<AppDictType>(20);// 每页20条记录

	private AppDictData appDictData;

	private List<AppDictData> lstAppDictData = new ArrayList<AppDictData>();

	private List<AppDictType> entities = new ArrayList<AppDictType>();

	private String subId;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private AppDictDataManager appDictDataManager;

	private AppDictType entity;

	public AppDictTypeAction() {
	}

	public String deleteSub() throws Exception {
		appDictDataManager.deleteAppDictData(getSubId());
		addActionMessage(getText("common.success"));
		return RELOAD_SUB;
	}

	@Override
	public String delete() throws Exception {
		appDictTypeManager.deleteAppDictType(getId());
		addActionMessage(getText("common.success"));
		return null;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	public String listSub() throws Exception {
		entity=appDictTypeManager.getEntity(getId()); 
		List<AppDictData> appDictDatas =appDictDataManager.getDictDataPerDictType(entity.getDictTypeCd());
		JsonUtil.renderListJson(appDictDatas, new String[] { "appDictType" });
		return null;
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
			page.setOrderBy("dictTypeCd,sequenceNo");
			page.setOrder(Page.ASC + "," + Page.ASC);
		} else {
			page.setOrderBy(sortField + ",sequenceNo");
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
			hql.append(" order by sequenceNo asc,dictTypeCd asc");
		} else {
			hql.append(" order by ").append(sortField).append(" ").append(order).append(", sequenceNo asc");
		}
		page = appDictTypeManager.findPage(page, hql.toString(), pram);
		JsonUtil.renderJson(page, new String[] { "appDictDatas" });
		
	 return null;
	}

	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String isTypeExists() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newDictTypeCd = request.getParameter("dictTypeCd").trim();
		String oldDictTypeCd = StringUtils.trimToNull(request.getParameter("oldDictTypeCd"));

		if (appDictTypeManager.isPropertyUnique("dictTypeCd", newDictTypeCd, oldDictTypeCd)) {
			JsonUtil.renderSuccess("不存在，可以用");
		} else {
			JsonUtil.renderFailure("存在，不可用");
		}
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {

		if (getId() != null) {
			entity = appDictTypeManager.getEntity(getId());
		} else {
			entity = new AppDictType();
			int cnt = appDictTypeManager.countCriteriaResult();
			entity.setSequenceNo(cnt + 1l);
		}
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
				appDictDataTmp2.setDictLevelNum(appDictDataTmp.getDictLevelNum());
				appDictDataTmp2.setRemark(appDictDataTmp.getRemark());
				appDictDataTmp2.setSequenceNo(appDictDataTmp.getSequenceNo());
				appDictDataTmp2.setAppDictType(entity);
				appDictDataManager.saveAppDictData(appDictDataTmp2);
			}
		}
	}

	@Override
	public String save() throws Exception {
		appDictTypeManager.saveEntity(entity);
		List<AppDictData> datasInsert = (List<AppDictData>) JsonUtil.getInsertRecords(AppDictData.class);
		List<AppDictData> datasUpdated = (List<AppDictData>) JsonUtil.getUpdatedRecords(AppDictData.class);
		refreshData(datasInsert);
		refreshData(datasUpdated);
		Struts2Utils.renderText(entity.getAppDictTypeId());
		return null;
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

	public List<AppDictType> getEntities() {
		return entities;
	}

	public void setEntities(List<AppDictType> entities) {
		this.entities = entities;
	}

	public Map<Boolean, String> getMapEnableFlg() {
		return DictMapUtil.getMapEnableFlg();
	}
}
