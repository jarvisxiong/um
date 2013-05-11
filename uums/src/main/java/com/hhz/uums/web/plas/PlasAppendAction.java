package com.hhz.uums.web.plas; 

import org.apache.commons.lang.StringUtils;

import com.hhz.uums.entity.plas.PlasAcct;
import com.hhz.uums.web.CrudActionSupport;

public class PlasAppendAction extends CrudActionSupport<PlasAcct> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 52616232374774202L;

	private String status; 
	private String pages;
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
	@Override
	public PlasAcct getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBatch() throws Exception {
		return "deleteBatch";
	}

	public String load() throws Exception{
		if(StringUtils.equals("1", status))
			return "hrLoad";
		else
			return "load";
	}
	@Override
	public String list() throws Exception {
		// TODO Auto-generated method stub
		if(StringUtils.equals("0", status))
			return "hrList";
		else if(StringUtils.equals("1", status))
			return "offList";
		else if(StringUtils.equals("2", status))
			return "eduList";
		else if(StringUtils.equals("3", status))
			return "jobList";
		else
			return "socList";
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("input!!");
		if(StringUtils.equals("1", status))
			return "hrInput";
		else
			return "input";
	}
	public String eduInput() throws Exception {
		// TODO Auto-generated method stub
		return "eduInput";
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}


}
