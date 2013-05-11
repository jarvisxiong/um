/**
 * 
 */
package com.hhz.uums.web.plas;

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

import com.hhz.core.web.CrudActionSupport;
import com.hhz.uums.dao.plas.PlasAcctManager;
import com.hhz.uums.dao.plas.PlasOrgManager;
import com.hhz.uums.dao.plas.PlasOrgMgrRelManager;
import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.utils.JsonUtil;

@Results( { @Result(name = CrudActionSupport.RELOAD, location = "plas-search-org.action", type = "redirect") })
public class PlasSearchOrgAction extends CrudActionSupport<PlasOrg> {

	private static final long serialVersionUID = 4853869952748335312L;

	@Autowired
	private PlasAcctManager plasAcctManager;
	@Autowired
	private PlasOrgManager plasOrgManager;
	@Autowired
	private PlasOrgMgrRelManager plasOrgMgrRelManager;
	
	private PlasOrg entity;

	// 物理机构
	private List<PlasOrg> bubblePhysicalOrgs;

	// 逻辑机构
	private List<PlasOrg> bubbleLogicalOrgs;

	// 机构与管理员映射关系表
	private Map<String, String> orgMangerMap;
	
	private String parentOrgNameLogical;
	private String parentOrgNamePhysical;
	private String orgMgrName;
	
	

	
	@Override
	public PlasOrg getModel() {
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
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
		
		page = plasOrgManager.findPage(page, filters);
		JsonUtil.renderJson(page,new String[]{"plasSysPositions","plasUsers","plasOrgMgrRels","plasDimeOrgRels"});
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(StringUtils.isNotBlank(getId())) {
			entity = plasOrgManager.getEntity(getId());
		}else{
			entity = new PlasOrg();
		}
	} 
	/**
	 * 查看机构明细
	 * @return
	 */
	public void prepareDetail() throws Exception{
		prepareModel();
	}
	public String detail(){
		
		
		bubblePhysicalOrgs = plasOrgManager.getBubbleOrgsPhysical(entity.getPlasOrgId());
		bubbleLogicalOrgs = plasOrgManager.getBubbleOrgsLogical(entity.getPlasOrgId());
		
		if(bubblePhysicalOrgs != null && bubblePhysicalOrgs.size() > 0){
			parentOrgNameLogical = ((PlasOrg)bubblePhysicalOrgs.get(0)).getOrgName();
		}
		if(bubbleLogicalOrgs != null && bubbleLogicalOrgs.size() > 0){
			parentOrgNamePhysical = ((PlasOrg)bubbleLogicalOrgs.get(0)).getOrgName();
		}
		PlasAcct acct = plasAcctManager.getPlasAcctByUiid(entity.getOrgMgrId());
		if(acct != null){
			orgMgrName = acct.getPlasUser().getUserName() + "(" + acct.getUiid() +")";
		}

		orgMangerMap = new HashMap<String, String>();
		
		buildOrgManagerMap(bubblePhysicalOrgs, orgMangerMap);
		buildOrgManagerMap(bubbleLogicalOrgs, orgMangerMap);
		
		return "detail";
	}
	/**
	 * 遍历机构,查询对应的管理员列表,存放到映射表中.
	 * 
	 * @param orgList
	 * @param map
	 */
	private void buildOrgManagerMap(List<PlasOrg> orgList, Map<String, String> map) {
		PlasOrg tmpOrg = null;
		PlasAcct tmpAcct = null;
		List<PlasAcct> tmpAcctList = null;
		for (int i = 0; i < orgList.size(); i++) {
			tmpOrg = orgList.get(i);
			String orgId = tmpOrg.getPlasOrgId();
			if (map.containsKey(orgId)) {
				continue;
			}
			tmpAcctList = plasOrgMgrRelManager.getPlasAcctsByOrgId(tmpOrg.getPlasOrgId());
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < tmpAcctList.size(); j++) {
				tmpAcct = tmpAcctList.get(j);
				if (j != 0) {
					sb.append(",");
				}
				sb.append(tmpAcct.getPlasUser().getUserName() + "(" + tmpAcct.getUiid() + ")");
			}
			if(StringUtils.isBlank(sb.toString())){
				map.put(orgId, "-");
			}else{
				map.put(orgId, sb.toString());
			}
		}
	}
	public String toSearch(){
		return SUCCESS;
	}

	public List<PlasOrg> getBubblePhysicalOrgs() {
		return bubblePhysicalOrgs;
	}

	public void setBubblePhysicalOrgs(List<PlasOrg> bubblePhysicalOrgs) {
		this.bubblePhysicalOrgs = bubblePhysicalOrgs;
	}

	public List<PlasOrg> getBubbleLogicalOrgs() {
		return bubbleLogicalOrgs;
	}

	public void setBubbleLogicalOrgs(List<PlasOrg> bubbleLogicalOrgs) {
		this.bubbleLogicalOrgs = bubbleLogicalOrgs;
	}

	public Map<String, String> getOrgMangerMap() {
		return orgMangerMap;
	}

	public void setOrgMangerMap(Map<String, String> orgMangerMap) {
		this.orgMangerMap = orgMangerMap;
	}

	public String getParentOrgNameLogical() {
		return parentOrgNameLogical;
	}

	public void setParentOrgNameLogical(String parentOrgNameLogical) {
		this.parentOrgNameLogical = parentOrgNameLogical;
	}

	public String getParentOrgNamePhysical() {
		return parentOrgNamePhysical;
	}

	public void setParentOrgNamePhysical(String parentOrgNamePhysical) {
		this.parentOrgNamePhysical = parentOrgNamePhysical;
	}

	public String getOrgMgrName() {
		return orgMgrName;
	}

	public void setOrgMgrName(String orgMgrName) {
		this.orgMgrName = orgMgrName;
	}

 
}

