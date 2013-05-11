/**
 * 
 */
package com.hhz.ump.web.cost;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.cost.CostContPlanTplManager;
import com.hhz.ump.entity.cost.CostContPlanTpl;

/**
 * 成本-合约规划模板
 * @author qlb 3/28/2012 verison 1.0.0
 *
 */

@Namespace("/cost")
public class CostContPlanTplAction extends CrudActionSupport<CostContPlanTpl> {

	private static final long serialVersionUID = -891192209444849945L;
	
	@Autowired
	private CostContPlanTplManager costContPlanTplManager;
	
	
	//第几页,主页面分页搜索使用
	private String pageNo;
	
	private Page<CostContPlanTpl> voPage;
	
	//查看明细
	private CostContPlanTpl entity;

	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String list(){
		
		//搜索条件
		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append(" from CostContPlanTpl t ");
		
		//分页搜索
		voPage = new Page<CostContPlanTpl>(15);
		if(StringUtils.isNotBlank(getPageNo())) {
			voPage.setPageNo(Integer.valueOf(getPageNo()).intValue());
		}
		
		voPage = costContPlanTplManager.findPage(voPage, sqlBuf.toString(), values);
		
		return "list";
	}

	@Override
	protected void prepareModel() throws Exception {
		
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CostContPlanTpl getModel() {
		return null;
	}
	
	
	/**
	 * 进入主页面
	 * @return
	 */
	public String main(){	
		
		list();
		
		return "main";
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Page<CostContPlanTpl> getVoPage() {
		return voPage;
	}

	public void setVoPage(Page<CostContPlanTpl> voPage) {
		this.voPage = voPage;
	}
	/**
	 * 查看明细 
	 * @param planTplId 模板ID
	 * @return
	 */
	public String viewPlanTplDetail(){

		String planTplId = Struts2Utils.getParameter("planTplId");
		if(StringUtils.isNotBlank(planTplId)){
			entity = costContPlanTplManager.getEntity(planTplId);
		}
		return "planTplDetail";
	}

	public CostContPlanTpl getEntity() {
		return entity;
	}

	public void setEntity(CostContPlanTpl entity) {
		this.entity = entity;
	}
	
}
