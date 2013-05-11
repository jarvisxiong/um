 package com.hhz.uums.web.plas;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.uums.dao.app.AppDictDataManager;
import com.hhz.uums.entity.app.AppDictData;
import com.hhz.uums.entity.plas.PlasApp;

/**
 *-------------------------------------------------------
 * date    			||  author      ||  Description    ||
 * 2011-2-12		|| jiaoxiaofeng ||  create         ||PlasRankAction.java
 * Description::职级模块职级列表
 * -------------------------------------------------------
 **/
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "plas-rank.action", type = "redirect") })
public class PlasRankAction extends CrudActionSupport<AppDictData> {

	private static final long serialVersionUID = -1972110454751502245L;


	@Autowired
	private AppDictDataManager appDictDataManager;

	private AppDictData entity;

	// 当前选中机构
	private PlasApp selectedPlasApp;
	
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
	public String list() throws Exception {
		return SUCCESS;
	}
	
	public String getPlasRankList(){

		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = appDictDataManager.getEntity(getId());
		} else {
			entity = new AppDictData();
			int cnt = appDictDataManager.countCriteriaResult();
			entity.setSequenceNo(cnt + 1l);
		}
		
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	public AppDictData getModel() {
		return entity;
	}

	

	public PlasApp getSelectedPlasApp() {
		return selectedPlasApp;
	}

	public void setSelectedPlasApp(PlasApp selectedPlasApp) {
		this.selectedPlasApp = selectedPlasApp;
	}
}
