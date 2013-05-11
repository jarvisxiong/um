/**
 * 
 */
package com.hhz.uums.web.plas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.uums.dao.plas.PlasDimeOrgRelManager;
import com.hhz.uums.dao.plas.PlasOrgDimeManager;
import com.hhz.uums.entity.plas.PlasOrgDime;
import com.hhz.uums.utils.JsonUtil;
import com.hhz.uums.web.CrudActionSupport;
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "plas-org-dime.action", type = "redirect") })
public class PlasOrgDimeAction extends CrudActionSupport<PlasOrgDime> {

	private static final long serialVersionUID = 6567628193183336623L;

	@Autowired
	private PlasOrgDimeManager plasOrgDimeManager;
	@Autowired
	private PlasDimeOrgRelManager plasDimeOrgRelManager;
	
	private PlasOrgDime entity;

	@Override
	public PlasOrgDime getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = plasOrgDimeManager.getEntity(getId());
		} else {
			entity = new PlasOrgDime();
			entity.setRecordVersion(1);
		}
	}

	@Override
	public String deleteBatch() throws Exception {
		plasOrgDimeManager.deleteBatch(getIds());
		JsonUtil.renderSuccess("删除成功");
		return null;
	}

	@Override
	public String list() throws Exception {
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		if (pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if (rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("createdDate");
			page.setOrder(Page.ASC);
		} else {
			page.setOrderBy(sortField + ",createdDate");
			page.setOrder(order + "," + Page.ASC);
		}
		
		page = plasOrgDimeManager.findPage(page, filters);
		JsonUtil.renderJson(page,new String[]{"plasDimeOrgRels"});
		return null;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String save() throws Exception {
		plasOrgDimeManager.savePlasOrgDimeRel(entity);
		Struts2Utils.renderText("success");
		return null;
	}
	@SuppressWarnings("unchecked")
	public String saveBatch(){
		List<PlasOrgDime> insertedRecords = (List<PlasOrgDime>) JsonUtil.getInsertRecords(PlasOrgDime.class);
		List<PlasOrgDime> updatedRecords =  (List<PlasOrgDime>) JsonUtil.getUpdatedRecords(PlasOrgDime.class);
		List<PlasOrgDime> deletedRecords =  (List<PlasOrgDime>) JsonUtil.getDeletedRecords(PlasOrgDime.class);
		plasOrgDimeManager.saveOrUpdateDime(insertedRecords, updatedRecords, deletedRecords);
		Struts2Utils.renderText("success");
		return null;
	}
	@Override
	public String delete() throws Exception {	
		plasOrgDimeManager.deletePlasOrgDime(getId());
		Struts2Utils.renderText("success");
		return null;
	}
  
	public String loadDimeTypeListJson(){
		List<Map> rtn = new ArrayList<Map>();
		Map<String,Object> map = null;
		int temp = 1;
		for (PlasOrgDime obj : plasOrgDimeManager.getAll()) {
			map = new HashMap<String,Object>();
			map.put("plasOrgDimeId", obj.getPlasOrgDimeId());
			map.put("dimeCd", obj.getDimeCd());
			map.put("dimeName", obj.getDimeName());
			if(temp==1){
				map.put("selected",true);
			}
			temp++;
			rtn.add(map);
		}
		Struts2Utils.renderJson(rtn);
		return null;
	}
	
	/**
	 * 刷新某维度的机构层级
	 * @param dimeCd 维度代码
	 * @return
	 */
	public String refrshDataTreeLevel(){
		String dimeCd = Struts2Utils.getParameter("dimeCd");
		PlasOrgDime tmp = plasOrgDimeManager.getEntityByDimeCd(dimeCd);
		if( tmp!= null){
			plasDimeOrgRelManager.refrshDataTreeLevel(tmp.getPlasOrgDimeId());
			JsonUtil.renderSuccess("刷新完成!");
		}else{
			JsonUtil.renderSuccess("刷新失败!找不到维度!");
		}
		return null;
	}

}

