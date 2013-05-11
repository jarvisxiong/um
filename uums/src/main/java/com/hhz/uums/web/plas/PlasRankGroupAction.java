 package com.hhz.uums.web.plas;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.uums.dao.app.AppDictDataManager;
import com.hhz.uums.dao.app.AppDictTypeManager;
import com.hhz.uums.dao.plas.PlasRoleGroupManager;
import com.hhz.uums.entity.app.AppDictData;
import com.hhz.uums.entity.app.AppDictType;
import com.hhz.uums.entity.plas.PlasApp;
import com.hhz.uums.utils.DictContants;
import com.hhz.uums.vo.ws.WsAppDictData;

/**
 *-------------------------------------------------------
 * date    			||  author      ||  Description    ||
 * 2011-2-12		|| jiaoxiaofeng ||  create         ||PlasRankAction.java
 * Description::职级模块职级组详细信息
 * -------------------------------------------------------
 **/
public class PlasRankGroupAction extends CrudActionSupport<AppDictData> {

	private static final long serialVersionUID = -1972110454751502245L;

	@Autowired
	private AppDictDataManager appDictDataManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	PlasRoleGroupManager plasRoleGroupManager;
	
	
	private List<WsAppDictData> dataList;
	
	private AppDictData entity;
	// 当前选中机构

	public PlasApp selectedPlasApp;
	public String dictCd;
	public String dictName;
	public String remark;
	public String updatedDepCd;
	public String udpatedDate;
	


	/**
	 * 获取职级列表
	 */
	@Override
	public String list() throws Exception {

//		AppDictType dictType = appDictTypeManager.findAppDictTypeByCd(DictContants.PLAS_POSITION_LEVEL);
//		if(null==dictType){
//			page.setResult(new ArrayList<AppDictData>()); 
//		}else{
//			page.setResult(dictType.getAppDictDatas());
//		}

		dataList = appDictTypeManager.getWsAllData(DictContants.PLAS_PERM_LEVEL);
		
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

	/**
	 * 显示职级组的详细信息：名称，序号，更新部门等信息
	 */
	public String getGroupDetail() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String dictCd = request.getParameter("dictCd").trim();
		
		AppDictType dictType = appDictTypeManager.findAppDictTypeByCd(DictContants.PLAS_PERM_LEVEL);
		List<AppDictData> l=dictType.getAppDictDatas();
		List<AppDictData> data = new ArrayList<AppDictData>();
		for(AppDictData dictData:l){
			if(dictData.getDictCd().equals(dictCd)){		
				data.add(dictData);
				break;
			}
		}
		l = dictType.getAppDictDatas();
		page.setResult(data);

		return "detail";
	}
	

	

	public String getDictCd() {
		return dictCd;
	}

	public void setDictCd(String dictCd) {
		this.dictCd = dictCd;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUpdatedDepCd() {
		return updatedDepCd;
	}

	public void setUpdatedDepCd(String updatedDepCd) {
		this.updatedDepCd = updatedDepCd;
	}

	public String getUdpatedDate() {
		return udpatedDate;
	}

	public void setUdpatedDate(String udpatedDate) {
		this.udpatedDate = udpatedDate;
	}
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

	public List<WsAppDictData> getDataList() {
		return dataList;
	}

	public void setDataList(List<WsAppDictData> dataList) {
		this.dataList = dataList;
	}
}