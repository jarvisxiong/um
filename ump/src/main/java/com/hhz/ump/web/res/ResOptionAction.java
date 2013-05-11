package com.hhz.ump.web.res;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.res.ResOptionManager;
import com.hhz.ump.dao.res.ResOptionValueManager;
import com.hhz.ump.entity.res.ResOption;
import com.hhz.ump.entity.res.ResOptionValue;
import com.hhz.ump.util.JsonUtil2;

/***
 * 网批选项
 * @author liwei
 * 2012-07-16
 */
@Namespace("/res")
public class ResOptionAction extends CrudActionSupport<ResOption> {

	private static final long serialVersionUID = 947808106125661207L;

	@Autowired
	private ResOptionManager resOptionManager;
	@Autowired
	private ResOptionValueManager resOptionValueManager;
	
	private ResOption entity;
    private List<ResOption> resOptions = new ArrayList<ResOption>();
    private List<ResOptionValue> resOptionValues = new ArrayList<ResOptionValue>();
    	
	@Override
	public ResOption getModel() {
		if(entity == null) {
			entity = new ResOption();
		}
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String execute() throws Exception {
		String flag = Struts2Utils.getParameter("flag");
		if("1".equals(flag))
			return "select";
		return SUCCESS;
	}
	
	/***
	 * 网批选项(ResOption)集合
	 * @return
	 * @throws Exception
	 */
	@Override
	public String list() throws Exception {
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		if(pageNo != null) {
			page.setPageNo(Integer.valueOf(pageNo));
		}
		if(rows != null) {
			page.setPageSize(Integer.valueOf(rows));
		}
		// 设置默认排序方式
		if (StringUtils.isEmpty(sortField)) {
			page.setOrderBy("optionName,optionType");
			page.setOrder(Page.ASC + "," + Page.ASC);
		} else {
			page.setOrderBy(sortField + ",optionType");
			page.setOrder(order + "," + Page.ASC);
		}
		page = resOptionManager.findPage(page, entity);
		JsonUtil2.renderJson(page);
		return null;
	}
	
	/***
	 * 网批选项内容(ResOptionValue)集合
	 * @return
	 * @throws Exception
	 */
	public String listSub() throws Exception {
		// 网批选项ID
		String resOptionId = Struts2Utils.getParameter("id");
		if(StringUtils.isNotBlank(resOptionId)) {
			List<ResOptionValue> valueList = resOptionValueManager.getEntityListByOptId(resOptionId);
			// 设置网批选项内容
			resOptionValues = valueList;
		}
		JsonUtil2.renderListJson(resOptionValues);
		return null;
	}
	
	@Override
	public void prepareInput() throws Exception {
		//很重要
		prepareModel();
	}

	/***
	 * 网批选项内容(ResOptionValue)集合
	 * @return
	 * @throws Exception
	 */
	@Override
	public String input() throws Exception { 
		
		return INPUT;
	}
	
	public void prepareValueSelect() throws Exception{
		prepareModel();
		
	}
	
     public String valueSelect() throws Exception { 
		
		return "valueSelect";
	}
	
	@Override
	public String save() throws Exception {
		// 网批选项
		resOptionManager.saveResOption(entity);
		
		// 网批选项内容
		List<ResOptionValue> optValInsert = (List<ResOptionValue>)JsonUtil2.getInsertRecords(ResOptionValue.class);
		List<ResOptionValue> optValUpdate = (List<ResOptionValue>)JsonUtil2.getUpdatedRecords(ResOptionValue.class);
		List<ResOptionValue> optValDelete = (List<ResOptionValue>)JsonUtil2.getDeletedRecords(ResOptionValue.class);
		refreshOptionValue(optValInsert, optValUpdate, optValDelete);
		if(StringUtils.isNotBlank(getId())) {
			Struts2Utils.renderText("execute_update");
		} else {
			Struts2Utils.renderText("execute_save");
		}
		return null;
	}
	
	/***
	 * 更新选项内容
	 * @param optVals
	 * @throws Exception
	 */
	private void refreshOptionValue(List<ResOptionValue> optValInsert, List<ResOptionValue> optValUpdate, List<ResOptionValue> optValDelete) throws Exception {
		for(ResOptionValue optVal : optValInsert) {
			optVal.setResOptionId(entity.getResOptionId());
			resOptionValueManager.saveResOptionValue(optVal);
		}
		for(ResOptionValue optVal : optValUpdate) {
			resOptionValueManager.saveResOptionValue(optVal);
		}
		for(ResOptionValue optVal : optValDelete) {
			resOptionValueManager.delete(optVal);
		}
	}

	/***
	 * 删除网批选项
	 * @return
	 * @throws Exception
	 */
	@Override
	public String delete() throws Exception {
		// 网批选项ID
		String resOptionId = Struts2Utils.getParameter("id");
		// 删除异常提示信息
		String exceptionMsg = "";
		try {
			ResOption option = resOptionManager.getEntity(resOptionId);
			resOptionManager.delete(option);
		} catch (Exception e) {
			if(e.getCause().getCause().getMessage().indexOf("已找到子记录") > -1) {
				exceptionMsg = "be_used_error";
			}
			if(e.getMessage().indexOf("No row with the given identifier exists") > -1) {
				exceptionMsg = "no_row_error";
			}
		}
		Struts2Utils.renderText(exceptionMsg);
		return null;
	}

	/***
	 * 删除网批选项内容
	 * @return
	 * @throws Exception
	 */
	public String deleteSub() throws Exception {
		// 网批选项内容ID
		String subId = Struts2Utils.getParameter("subId");
		// 删除异常提示信息
		String exceptionMsg = "";
		try {
			ResOptionValue optVal = resOptionValueManager.getEntity(subId);
			resOptionValueManager.delete(optVal);
			exceptionMsg = "execute_deleteSub";
		} catch (Exception e) {
			if(e.getCause().getCause().getMessage().indexOf("已找到子记录") > -1) {
				exceptionMsg = "be_used_error";
			}
			if(e.getMessage().indexOf("No row with the given identifier exists") > -1) {
				exceptionMsg = "no_row_error";
			}
		}
		Struts2Utils.renderText(exceptionMsg);
		return null;
	}

	/***
	 * 批量删除网批选项内容
	 * @return
	 * @throws Exception
	 */
	public String deleteSubBatch() throws Exception {
		// 网批选项ID
		String optionId = Struts2Utils.getParameter("id");
		// 删除异常提示信息
		String exceptionMsg = "";
		try {
		resOptionValueManager.deleteByOptionId(optionId);
		exceptionMsg = "execute_deleteSubBatch";
		} catch (Exception e) {
			if(e.getCause().getCause().getMessage().indexOf("已找到子记录") > -1) {
				exceptionMsg = "be_used_error";
			}
			if(e.getMessage().indexOf("No row with the given identifier exists") > -1) {
				exceptionMsg = "no_row_error";
			}
		}
		Struts2Utils.renderText(exceptionMsg);
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if(StringUtils.isNotBlank(getId())){
			entity = resOptionManager.getEntity(getId());
		}else{
			entity = new ResOption();
		}
	}

	/**
	 * @return the resOptions
	 */
	public List<ResOption> getResOptions() {
		return resOptions;
	}

	/**
	 * @param resOptions the resOptions to set
	 */
	public void setResOptions(List<ResOption> resOptions) {
		this.resOptions = resOptions;
	}

	/**
	 * @return the resOptionValues
	 */
	public List<ResOptionValue> getResOptionValues() {
		return resOptionValues;
	}

	/**
	 * @param resOptionValues the resOptionValues to set
	 */
	public void setResOptionValues(List<ResOptionValue> resOptionValues) {
		this.resOptionValues = resOptionValues;
	}


}
