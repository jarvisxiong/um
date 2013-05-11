package com.hhz.ump.web.cost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.cost.CostBudgetAuthManager;
import com.hhz.ump.dao.cost.CostProjectSectionManager;
import com.hhz.ump.entity.cost.CostBudgetAuth;
import com.hhz.ump.entity.cost.CostProjectSection;
import com.hhz.ump.web.vo.CostBudgetSectionVo;

@Namespace("/cost")
public class CostBudgetAuthAction extends CrudActionSupport<CostBudgetAuth> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CostBudgetAuthManager costBudgetAuthManager;
	@Autowired
	private CostProjectSectionManager costProjectSectionManager;

	// 授权列表
	private List<CostBudgetSectionVo> costBudgetAuthVoList;

	private CostBudgetAuth entity;
	// 授权ID
	private String id;

	// 项目列表
	private Page<CostProjectSection> pageCostProjectSection;

	/**
	 * 授权主页
	 * 
	 * @return
	 * @throws Exception
	 */
	public String main() throws Exception {
		return "main";
	}

	/**
	 * 搜索授权列表
	 * 
	 */
	@Override
	public String list() throws Exception {

		// 搜索项目对应的人员
		costBudgetAuthVoList = new ArrayList<CostBudgetSectionVo>();
		// 获取SQL和SQL的条件
		Map<String, Object> sqlMap = makeListSqlMap();
		
		String hql = sqlMap.get("sql").toString();
		Map<String, Object> values = (Map<String, Object>) sqlMap.get("values");
		// 搜索
		List<Object[]> rs = costProjectSectionManager.findBySql(hql, values);
		// 如果结果不为空，封装结果对象
		if (rs != null) {
			Integer size = rs.size();
			CostBudgetSectionVo tSection = null;
			Object[] tmp  = null;
			for (int i = 0; i < size; i++) {
				tmp = rs.get(i);
				tSection = new CostBudgetSectionVo();
				// 项目ID
				tSection.setCostProjectSectionId((String) tmp[0]);
				
				if (tmp[1] != null) {
					tSection.setAuthUiids((String) tmp[1]);// 授权的用户
					tSection.setAuthUserNames(authUserName((String) tmp[1]));
					if(tmp.length >= 3){
						tSection.setRemark((String) tmp[5]);//备注
					}
				}
				// 项目名称
				tSection.setSectionName((String) tmp[4]);
				costBudgetAuthVoList.add(tSection);
			}
		}
		return "list";
	}
	
	/**
	 * 获取已经授权的用户名
	 * @return
	 */
	private String authUserName(String authUiids){
		StringBuffer rs=new StringBuffer();
		if(StringUtils.isNotBlank(authUiids)){
			String[] uiids=authUiids.split(";");
			if(uiids!=null){
				for(String st:uiids){
					rs.append(PlasCache.getUserByUiid(st).getUserName()+";");
				}
			}
		}
		return rs.toString();
	}

	/**
	 * 构建搜索语句及搜索条件
	 * @param costProjectSectionId
	 *            项目期数ID
	 * @param authUiid
	 *            授权人uiid
	 * @param sectionName
	 *            项目名称
	 * @param remark
	 *           备注，关键字
	 * @return
	 */
	public Map makeListSqlMap() {
		Map <String,Object> rs=new HashMap<String,Object>();
		String[] tmpSectionIds = Struts2Utils.getRequest().getParameterValues("costProjectSectionId");
		String authUiid = Struts2Utils.getParameter("authUiid");
		String sectionNames = Struts2Utils.getParameter("sectionName");// 多个逗号隔开
		String remark = Struts2Utils.getParameter("remark");
		
		// 核心SQL,用于行转列合并字段：wm_concat(a.auth_uiid)
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer()
		.append("select * from (")
		.append(" 	select p.cost_project_section_id,t1.uiid ,p.project_cd,p.project_name,p.section_name,(case p.remark when null then '-' else p.remark end) as remark,p.sequence_no ")
		.append(" 	from cost_project_section p ")
		.append(" 	left join (")
		.append(" 		select a.cost_project_section_id as cost_project_section_id,replace(wm_concat(a.auth_uiid),',',';') uiid ")
		.append(" 		from cost_budget_auth a group by a.cost_project_section_id ")
		.append(" 	) t1 ")
		.append(" 	on p.cost_project_section_id=t1.cost_project_section_id ")
		.append(") t2 where 1=1 ");
		
		if (StringUtils.isNotBlank(authUiid)) {
			values.put("authUiid", "%"+authUiid+"%");
			sql.append(" and exists(select 1 from cost_budget_auth a1 where  t2.cost_project_section_id = a1.cost_project_section_id ")
			.append(" and t2.uiid like :authUiid )");
		}
		if (tmpSectionIds != null && tmpSectionIds.length > 0) {
			if (StringUtils.isNotBlank(tmpSectionIds[0])) {
				values.put("tmpSectionIdList", tmpSectionIds);
				sql.append(" and t2.cost_project_section_id in(:tmpSectionIdList)");
			}
		}
		if (StringUtils.isNotBlank(sectionNames)) {
			String[] arr = sectionNames.split(",");
			sql.append(" and (");
			for (int i = 0; i < arr.length; i++) {
				if (i > 0) {
					sql.append(" or t2.section_name like :sectionName" + i);
				} else {
					sql.append("    t2.section_name like :sectionName" + i);
				}
				values.put(("sectionName" + i), "%" + arr[i] + "%");
			}
			sql.append(" )");
		}
		if (StringUtils.isNotBlank(remark)) {
			sql.append(" and t2.remark like :remark");
			values.put("remark", "%" + remark + "%");
		}

		sql.append(" order by  t2.remark asc,  t2.project_name asc, t2.sequence_no asc  ");
		rs.put("sql", sql);
		rs.put("values", values);
		return rs;
	}

	private String appendStr(String s1, String s2) {
		return (StringUtils.isBlank(s1) ? "" : s1.trim()) + (StringUtils.isBlank(s2) ? "" : s2.trim());
	}

	@Override
	public String save() throws Exception {
		// 项目ID
		String sectionId = Struts2Utils.getParameter("sectionId");
		// 新的uiids
		String authUiid = Struts2Utils.getParameter("authUiid");
		// 旧的uiids
		String authUiidOld = Struts2Utils.getParameter("authUiidOld");
		// 备注（关键字)
		String tRemark = Struts2Utils.getParameter("remark");
		
		try {
			
			
			// 先删除，后保存授权
			if (StringUtils.isNotBlank(sectionId)) {
				costProjectSectionManager.saveCostProjectSection(sectionId, authUiid, authUiidOld, tRemark);
			}
			Struts2Utils.renderText("success");
		} catch (Exception e) {
			e.printStackTrace();
			Struts2Utils.renderText("fail");
		}
		return null;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String delete() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = costBudgetAuthManager.getEntity(getId());
			if (entity != null) {
				costBudgetAuthManager.deleteCostBudgetAuth(getId());
				Struts2Utils.renderHtml("success");
			}
		}
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = costBudgetAuthManager.getEntity(getId());
		} else {
			entity = new CostBudgetAuth();
		}
	}

	public Map<String, String> getMapCostProjectSection() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<CostProjectSection> costProjectSectionList = costProjectSectionManager.getAll();
		for (CostProjectSection t : costProjectSectionList) {
			map.put(t.getCostProjectSectionId(), t.getSectionName());
		}
		return map;
	}

	@Override
	public CostBudgetAuth getModel() {
		return entity;
	}

	public CostBudgetAuth getEntity() {
		return entity;
	}

	public void setEntity(CostBudgetAuth entity) {
		this.entity = entity;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public List<CostBudgetSectionVo> getCostBudgetAuthVoList() {
		return costBudgetAuthVoList;
	}

	public void setCostBudgetAuthVoList(List<CostBudgetSectionVo> costBudgetAuthVoList) {
		this.costBudgetAuthVoList = costBudgetAuthVoList;
	}

	public Page<CostProjectSection> getPageCostProjectSection() {
		return pageCostProjectSection;
	}

	public void setPageCostProjectSection(Page<CostProjectSection> pageCostProjectSection) {
		this.pageCostProjectSection = pageCostProjectSection;
	}

}
