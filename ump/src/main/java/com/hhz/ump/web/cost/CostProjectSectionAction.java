package com.hhz.ump.web.cost;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cost.CostBudgetAuthManager;
import com.hhz.ump.dao.cost.CostProjectSectionManager;
import com.hhz.ump.entity.cost.CostBudgetAuth;
import com.hhz.ump.entity.cost.CostProjectSection;
import com.hhz.ump.util.CodeNameUtil;

@Namespace("/cost")
@Results( { @Result(name = CostProjectSectionAction.RELOAD, location = "cost-project-section!main.action", type = "redirect")})
public class CostProjectSectionAction extends CrudActionSupport<CostProjectSection>{
	private static final long serialVersionUID = 1L;

	@Autowired
	private CostProjectSectionManager costProjectSectionManager;
	
	
	@Autowired
	private CostBudgetAuthManager costBudgetAuthManager;
	private List<CostProjectSection> costProjectSectionList;
	
	//授权表
	private CostBudgetAuth costBudgetAuth;
	private CostProjectSection entity;
	private String id;
	//授权ID
	private String costBudgetAuthId;
	//授权人员uiid
	private String authUiid; 
	//授权人员名称
	private String authUiidName; 
	
	/**
	 * 项目维护主页
	 * @return
	 * @throws Exception
	 */
	public String main() throws Exception{
		return "main";
	}
	
	@Override
	public String list() throws Exception {
		String[] ids = Struts2Utils.getRequest().getParameterValues("ids");
		String sectionName = Struts2Utils.getParameter("sectionName");
		String remark = Struts2Utils.getParameter("remark");
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("from CostProjectSection t where 1=1 ");
		if(ids != null && ids.length > 0){
			if(StringUtils.isNotBlank(ids[0])){
				hql.append(" and t.costProjectSectionId in(:costProjectSectionId)");
				values.put("costProjectSectionId", ids);
			}
		}
		if(StringUtils.isNotBlank(sectionName)){
			hql.append(" and t.sectionName like :sectionName");
			values.put("sectionName","%" + sectionName.trim() + "%");
		}
		if(StringUtils.isNotBlank(remark)){
			hql.append(" and t.remark like :remark");
			values.put("remark","%" + remark.trim() + "%");
		}
		hql.append(" order by t.remark asc,t.projectName asc, t.sequenceNo asc");//t.projectCd asc , 
		
		//设置分页20条
		page.setPageSize(20);
		costProjectSectionList = costProjectSectionManager.findPage(page, hql.toString(),values).getResult();
		return "list";
	}
	
	@Override
	public void prepareSave() throws Exception {
		prepareModel();
	}
	@Override
	public String save() throws Exception {
		try {
			//保存项目
			costProjectSectionManager.saveCostProjectSection(entity);
			
			//先删除，后保存授权
			if(StringUtils.isNotBlank(costBudgetAuthId)){
				String[] authIds = costBudgetAuthId.split(";");
				
				CostBudgetAuth budgetAuth = null;
				for (int j = 0; j < authIds.length; j++) {
					if(StringUtils.isNotBlank(authIds[j])){
						budgetAuth = costBudgetAuthManager.getEntity(authIds[j]);
						costBudgetAuthManager.delete(budgetAuth);
					}
				}
			}
			if (StringUtils.isNotBlank(authUiid)) {
				String[] uiids = authUiid.split(";");
				CostBudgetAuth auth = null;
				for (int i = 0; i < uiids.length; i++) {

					if(StringUtils.isNotBlank(uiids[i])){
						auth = new CostBudgetAuth();
						auth.setCostProjectSectionId(entity.getCostProjectSectionId());
						auth.setAuthUiid(uiids[i]);
						costBudgetAuthManager.saveCostBudgetAuth(auth);
					}
				}
			}
			Struts2Utils.renderText(entity.getCostProjectSectionId());
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
			entity = costProjectSectionManager.getEntity(getId());
			if(entity != null){
				if(entity.getCostBudgetYears().size() > 0 || entity.getCostBudgetMonths().size() > 0){
					Struts2Utils.renderHtml("realy");
				}else{
					try {
						costProjectSectionManager.delete(entity);
						Struts2Utils.renderHtml("success");
					} catch (Exception e) {
						e.printStackTrace();
						Struts2Utils.renderHtml("fail");
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * 构造项目树
	 * @return
	 * @throws Exception
	 */
	public String getSectionTree() throws Exception{
		 
		//获取有权限项目
		List<CostProjectSection> tList = costProjectSectionManager.getPermSectionList();
		//构造项目树
		Struts2Utils.renderJson(costProjectSectionManager.getSectionTreePanelNode(tList));
		return null;
	}
	
	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (StringUtils.isNotBlank(getId())) {
			entity = costProjectSectionManager.getEntity(getId());
			initSection(entity);
		}else{
			entity = new CostProjectSection();
		}
	}

	/**
	 * 根据项目id得到授权列表，并将授权表的uiid转成用户名称
	 * @param section
	 */
	public void initSection(CostProjectSection section){
		List<CostBudgetAuth> authList = costBudgetAuthManager.getBudgetAuthBySectionId(section.getCostProjectSectionId());
		if (authList != null && authList.size() > 0) {
			StringBuffer sb = new StringBuffer();
			StringBuffer sectionId = new StringBuffer();
			for (CostBudgetAuth t : authList) {
				sb.append(t.getAuthUiid()).append(";");
				sectionId.append(t.getCostBudgetAuthId()).append(";");
			}
			authUiidName = CodeNameUtil.getUserNamesByUiids(sb.toString(), ";");
			authUiid = sb.toString();
			costBudgetAuthId = sectionId.toString();
		}
	}
	@Override
	public CostProjectSection getModel() {
		return entity;
	}

	public CostProjectSection getEntity() {
		return entity;
	}

	public void setEntity(CostProjectSection entity) {
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

	public List<CostProjectSection> getCostProjectSectionList() {
		return costProjectSectionList;
	}

	public void setCostProjectSectionList(
			List<CostProjectSection> costProjectSectionList) {
		this.costProjectSectionList = costProjectSectionList;
	}

	public CostBudgetAuth getCostBudgetAuth() {
		return costBudgetAuth;
	}

	public void setCostBudgetAuth(CostBudgetAuth costBudgetAuth) {
		this.costBudgetAuth = costBudgetAuth;
	}

	public String getAuthUiid() {
		return authUiid;
	}

	public void setAuthUiid(String authUiid) {
		this.authUiid = authUiid;
	}

	public String getCostBudgetAuthId() {
		return costBudgetAuthId;
	}

	public void setCostBudgetAuthId(String costBudgetAuthId) {
		this.costBudgetAuthId = costBudgetAuthId;
	}

	public String getAuthUiidName() {
		return authUiidName;
	}

	public void setAuthUiidName(String authUiidName) {
		this.authUiidName = authUiidName;
	}
	
	
	/**
	 * 初始化项目(从合同台账导入，仅执行一次)
	 */
	public String importSection(){
		try{
			String result = costProjectSectionManager.importSection();
			Struts2Utils.renderText(result);
		}catch(Exception e){
			e.printStackTrace();
			Struts2Utils.renderText(e.getMessage());
		}
		return null;
	}
}
