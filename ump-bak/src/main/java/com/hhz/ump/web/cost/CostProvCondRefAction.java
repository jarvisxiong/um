/**
 * 
 */
package com.hhz.ump.web.cost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cost.CostProvCondRefManager;
import com.hhz.ump.entity.cost.CostProvCondRef;

/**
 * 入库试用条件审核
 * @author lbqi 3/19/2012 version 1.0.0
 *
 */
public class CostProvCondRefAction  extends CrudActionSupport<CostProvCondRef>{
	
	private static final long serialVersionUID = -4824002968652696786L;

	@Autowired
	private CostProvCondRefManager costProvCondRefManager;
	/**搜索集合的时候接受数据的集合*/
	private List<CostProvCondRef> costProvCondRefList;
	/**保存数据的时候实体dto*/
	private CostProvCondRef costProvCondRef;


	@Override
	public String delete() throws Exception {
		costProvCondRefManager.deleteCostProvCondRef(costProvCondRef.getCostProvCondRefId());
		Struts2Utils.renderText("success");
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	/**
	 * 编辑入库条件 查看详细
	 * @param id 编号
	 */
	@Override
	public String input() throws Exception {
		if(StringUtils.isNotBlank(Struts2Utils.getParameter("id"))){
			costProvCondRef = costProvCondRefManager.getEntity(Struts2Utils.getParameter("id"));
			return "inputView";
		}
		return "input";
	}


	/**
	 * 进入主界面
	 * @return
	 */
	public String main(){
		
		return "main";
	}
	
	

	/**
	 * 分页搜索
	 * 
	 * @param typename 分类
	 * @param provdesc 供方内容
	 */
	
	@Override
	public String list() throws Exception {
		String[] ids = Struts2Utils.getRequest().getParameterValues("ids");
		String typeName = Struts2Utils.getParameter("typename");
		String provDesc = Struts2Utils.getParameter("provdesc");
		String profQualDesc = Struts2Utils.getParameter("profqualdesc");
		String induRankDesc = Struts2Utils.getParameter("indurankdesc");
		String entePerfDesc = Struts2Utils.getParameter("enteperfdesc");
		String systCertDesc = Struts2Utils.getParameter("systcertdesc");
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from CostProvCondRef t where 1=1 ");
		if(ids != null && ids.length > 0){
			if(StringUtils.isNotBlank(ids[0])){
				hql.append(" and t.costProvCondRefId in(:costProvCondRefId)");
				values.put("costProvCondRefId", ids);
			}
		}
		if(StringUtils.isNotBlank(typeName)){
			hql.append(" and t.typeName like :typeName");
			values.put("typeName","%" + typeName.trim() + "%");
		}
		if(StringUtils.isNotBlank(provDesc)){
			hql.append(" and t.provDesc like :provDesc");
			values.put("provDesc","%" + provDesc.trim() + "%");
		}
		if(StringUtils.isNotBlank(profQualDesc)){
			hql.append(" and t.profQualDesc like :profQualDesc");
			values.put("profQualDesc","%" + profQualDesc.trim() + "%");
		}
		if(StringUtils.isNotBlank(induRankDesc)){
			hql.append(" and t.induRankDesc like :induRankDesc");
			values.put("induRankDesc","%" + induRankDesc.trim() + "%");
		}
		if(StringUtils.isNotBlank(entePerfDesc)){
			hql.append(" and t.entePerfDesc like :entePerfDesc");
			values.put("entePerfDesc","%" + entePerfDesc.trim() + "%");
		}
		if(StringUtils.isNotBlank(systCertDesc)){
			hql.append(" and t.systCertDesc like :systCertDesc");
			values.put("systCertDesc","%" + systCertDesc.trim() + "%");
		}
		hql.append(" order by t.sequenceNo asc");
		//设置分页20条
		page.setPageSize(20);
		costProvCondRefList = costProvCondRefManager.findPage(page, hql.toString(), values).getResult();
		return "list";
	}

	@Override
	protected void prepareModel() throws Exception {
		
	}

	/**
	 * 保存入库条件信息
	 * @param method 区别修改、新增
	 */
	@Override
	public void prepareSave() throws Exception {
		if(StringUtils.isNotBlank(getId())){
			costProvCondRef = costProvCondRefManager.getEntity(getId());
		}else{
			costProvCondRef = new CostProvCondRef();
		}
	}
	@Override
	public String save() throws Exception {
		costProvCondRefManager.saveCostProvCondRef(costProvCondRef);
		Struts2Utils.renderText("success");
		return null;
	}

	@Override
	public CostProvCondRef getModel() {
		return null;
	}

	
	/**
	 * 入库供方试用评级资格条件快速搜索
	 * 只显示前20条数据，未显示的可输入关键字精确搜索
	 * @param value  对应 [provDesc供方分类,typeName类型]
	 * @return 返回满足条件的JSON数据
	 * @throws Exception
	 */
	public void quickSearch() throws Exception {
		
		String value = Struts2Utils.getParameter("value");

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, Object> param = new HashMap<String, Object>();

		StringBuffer hql2 = new StringBuffer("");
		hql2 = new StringBuffer("from CostProvCondRef where (lower(provDesc) like :provDesc or lower(typeName) like :provDesc) order  by sequenceNo asc ");

		
		if (StringUtils.isNotBlank(value)) {
			value = value.toLowerCase();
		}
		param.put("provDesc", "%" + value + "%");
		
		page.setPageSize(20);
		page = costProvCondRefManager.findPage(page, hql2.toString(), param);

		Map<String, String> map  = null;
		for (CostProvCondRef costProvCondRef : page.getResult()) {
			map = new HashMap<String, String>();
			map.put("costProvCondRefId", StringUtils.trim(costProvCondRef.getCostProvCondRefId()));
			map.put("typeName", costProvCondRef.getTypeName());
			map.put("provDesc", StringUtils.trim(costProvCondRef.getProvDesc()));
			map.put("profQualDesc", costProvCondRef.getProfQualDesc()); 
			map.put("induRankDesc", costProvCondRef.getInduRankDesc());
			map.put("entePerfDesc", costProvCondRef.getEntePerfDesc());
			map.put("systCertDesc", costProvCondRef.getSystCertDesc());
			list.add(map);
			
		}
		Struts2Utils.renderJson(list);
	}
	/***
	 * 搜索页面显示的树形
	 * @return
	 */
	public String getCostProvCondrefTree(){
		Struts2Utils.renderJson(costProvCondRefManager.getProvTree());
		return null;
	}
	/**
	 * 验证同一分类下是否有相同的供方
	 * @param typeName 分类
	 * @param provDesc 供方
	 * @return
	 */
	public String valiExistProvDesc() {
		String typeName = costProvCondRef.getTypeName();
		String provDesc = costProvCondRef.getProvDesc();
		
		if(costProvCondRefManager.valiExistProvDesc(typeName, provDesc)){
			Struts2Utils.renderText("falure");
		}else{
			Struts2Utils.renderText("success");
		}
		return null;
	}
	
	public List<CostProvCondRef> getCostProvCondRefList() {
		return costProvCondRefList;
	}

	public void setCostProvCondRefList(List<CostProvCondRef> costProvCondRefList) {
		this.costProvCondRefList = costProvCondRefList;
	}

	public CostProvCondRef getCostProvCondRef() {
		return costProvCondRef;
	}

	public void setCostProvCondRef(CostProvCondRef costProvCondRef) {
		this.costProvCondRef = costProvCondRef;
	}
	
}
