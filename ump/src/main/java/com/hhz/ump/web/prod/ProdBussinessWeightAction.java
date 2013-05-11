/**  
 * ProdBussinessWeightAction.java  
 * com.hhz.ump.web.prod  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-11-7        zhongyubing  
 *  
 * Copyright (c) 2011, TNT All Rights Reserved.  
 */

package com.hhz.ump.web.prod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.prod.ProdBussinessWeightManager;
import com.hhz.ump.entity.prod.ProdBussinessWeight;

/**
 * 权重，已经弃用
 * 
 * @author zhongyubing
 * @version
 * @since Ver 1.1
 * @Date 2011-11-7 下午03:11:01
 * 
 * 
 */
@Namespace("/prod")
public class ProdBussinessWeightAction extends CrudActionSupport<ProdBussinessWeight> {

	private static final long serialVersionUID = 541178553946045513L;

	@Autowired
	protected ProdBussinessWeightManager prodBussinessWeightManager;
	
	
	
	/**
	 * 权重
	 */
	private ProdBussinessWeight entity;
	
	@Override
	public String delete() throws Exception {
		String bussinessCd=Struts2Utils.getParameter("bussinessCd");
		if (StringUtils.isNotBlank(bussinessCd)) {
			prodBussinessWeightManager.deleteProdBussinessWeightByBussinessCd(bussinessCd);
			Struts2Utils.renderText("success,权重删除成功!");
		}
		return null;

	}
	
	

	@Override
	public String deleteBatch() throws Exception {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public String input() throws Exception {

		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public String list() throws Exception {

		
		
		return "list";

	}
	public String wieghtContent(){
		String pageNo = Struts2Utils.getParameter("page");
		String rows = Struts2Utils.getParameter("rows");
		String sortField = Struts2Utils.getParameter("sort");
		String order = Struts2Utils.getParameter("order");
		String bussnissCd=Struts2Utils.getParameter("bussnissCd");
		
		Map<String, Object> map = new HashMap<String, Object>();
		//准备搜索语句
		StringBuffer sbSql=new StringBuffer();
		
		
		sbSql.append(" from ProdBussinessWeight pw where 1=1 ");
		if(StringUtils.isNotBlank(bussnissCd)){
			sbSql.append(" and pw.bussinessCd = :bussinessCd");
			map.put("bussinessCd", bussnissCd);
		}
		
		page=prodBussinessWeightManager.findPage(page, sbSql.toString(), map);
		return "table";
	}

	@Override
	protected void prepareModel() throws Exception {
		String bussinessCd=Struts2Utils.getParameter("bussinessCd");		
		if (StringUtils.isBlank(bussinessCd)) {
			entity = new ProdBussinessWeight();
			
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bussinessCd", bussinessCd);
			String hql="from ProdBussinessWeight pw where pw.bussinessCd = :bussinessCd";
			List<ProdBussinessWeight> pwList = prodBussinessWeightManager.find(hql, map);
			if(pwList!=null&&pwList.size()>0){
				entity=pwList.get(0);
			}
			else{
				entity = new ProdBussinessWeight();
				entity.setBussinessCd(bussinessCd);
			}
		}

	}
	
	/**
	 * 保存前预备动作
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#prepareSave()
	 */
	@Override
	public void prepareSave() throws Exception {
		prepareModel();
	}
	/**
	 * 保存或编辑业态权重
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#save()
	 */
	@Override
	public String save() throws Exception {
		prodBussinessWeightManager.saveProdBussinessWeight(entity);	
		Struts2Utils.renderText("success");
		return null;

	}
	
	public String hasWeight(){
		String bussinessCd=Struts2Utils.getParameter("bussinessCd");
		if(StringUtils.isNotBlank(bussinessCd)){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bussinessCd", bussinessCd);
			String hql="from ProdBussinessWeight pw where pw.bussinessCd = :bussinessCd";
			List<ProdBussinessWeight> pwList = prodBussinessWeightManager.find(hql, map);
			//已经存在数据
			if(pwList!=null&&pwList.size()>0){
				Struts2Utils.renderText("false,1");
			//不存在数据	
			}else{
				Struts2Utils.renderText("success,0");
			}
		}
		return null;
	}

	@Override
	public ProdBussinessWeight getModel() {

		// TODO Auto-generated method stub
		return null;

	}

	public ProdBussinessWeight getEntity() {
		return entity;
	}

	public void setEntity(ProdBussinessWeight entity) {
		this.entity = entity;
	}
	
	public String weightForm(){
		String bussinessCd=Struts2Utils.getParameter("bussinessCd");
		if(StringUtils.isNotBlank(bussinessCd)){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bussinessCd", bussinessCd);
			String hql="from ProdBussinessWeight pw where pw.bussinessCd = :bussinessCd";
			List<ProdBussinessWeight> pwList = prodBussinessWeightManager.find(hql, map);
			if(pwList!=null&&pwList.size()>0){
				entity=pwList.get(0);
			}
		}
		return "weightForm";
	}
	
	
	

}
