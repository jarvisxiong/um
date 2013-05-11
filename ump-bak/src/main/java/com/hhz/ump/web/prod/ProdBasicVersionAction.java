/**  
 * ProdBasicVersionAction.java  
 * com.hhz.ump.web.prod  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-11-8        zhongyubing  
 *  
 * Copyright (c) 2011, PD All Rights Reserved.  
*/  
  
package com.hhz.ump.web.prod;  

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.prod.ProdBasicVersionManager;
import com.hhz.ump.entity.prod.ProdBasicVersion;

/**
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-11-8        上午09:07:05  
 *  
 */
@Namespace("/prod")
public class ProdBasicVersionAction   extends CrudActionSupport<ProdBasicVersion>{
	@Autowired
	protected ProdBasicVersionManager prodBasicVersionManager;
	
	private static final long serialVersionUID = 2593325805988766477L;
	/**
	 * 工料版本
	 */
	private ProdBasicVersion entity;
	/**
	 * 工料版本列表
	 */
	private List <ProdBasicVersion> prodBasicVersions;
	/**
	 * 标记	
	 */
	private boolean flag=true;
	
	
	/**
	 * 确认是否可删除
	 */
	public String canDelete(){
		//版本编号ID
		String basicVersionId=Struts2Utils.getParameter("basicVersionId");
		if(StringUtils.isNotBlank(basicVersionId)){
			//此本版号是否已经被使用
			if(prodBasicVersionManager.hasUsedBasicVersion(basicVersionId)>0){
				Struts2Utils.renderText("false;此版本已经存在相关数据,不能直接删除,如果确实需要删除,请先删除关联数据,再执行此删除操作！");
			}else{
				Struts2Utils.renderText("success;");
			}
		}else{
			Struts2Utils.renderText("false;缺失关键字！");
		}
		
		return null;
	}
	
	/**
	 * 执行删除版本号
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#delete()
	 */
	@Override
	public String delete() throws Exception {
		//版本编号ID 
		String basicVersionId=Struts2Utils.getParameter("basicVersionId");
		if(StringUtils.isNotBlank(basicVersionId)){
			try {
				//删除版本
				prodBasicVersionManager.deleteProdBasicVersion(basicVersionId);
				//查找激活的可用版本
				entity=prodBasicVersionManager.findNewestVersion();
				if(entity!=null) {
					Struts2Utils.renderText("success,"+entity.getVersionNo()+","+entity.getProdBasicVersionId());
				} else{
					Struts2Utils.renderText("success,,");
				}
			} catch (Exception e) {
				Log.error(e.getMessage(),e);
				Struts2Utils.renderText("error");
			}
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
	/**
	 * 搜索当前激活的版本
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#list()
	 */
	@Override
	public String list() throws Exception {		
		entity=prodBasicVersionManager.findNewestVersion();
		return "list";  
		
	}
	
	/**
	 * 版本列表
	 */
	public String loadList(){
		String hql="from ProdBasicVersion pbv ";
		//搜索条件
		Map<String, Object> map = new HashMap<String, Object>();		
		page= prodBasicVersionManager.findPage(page, hql, map);
		return "loadList";
	}
	
	/**
	 * 
	 *搜索是否已经存在相应时间见版本
	 */
	public String hasExsitVersion(){
		//时间
		String versionTime=Struts2Utils.getParameter("versionTime");
		if(StringUtils.isNotBlank(versionTime)){
			String []dStr=versionTime.split("-");
			//搜索相应时间的版本是否已经存在
			if(prodBasicVersionManager.hasExsitVersion(dStr[0],StringUtils.leftPad(dStr[1], 2))!=null){
				Struts2Utils.renderText("false,已经存在此年月的版本号.是否覆盖？");
			}
		}
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		//--->>>>>>>>>>>>>开始校验版本号
		String prodBasicVersionId=Struts2Utils.getParameter("prodBasicVersionId");
		//编辑
		if(StringUtils.isNotBlank(prodBasicVersionId)){
			ProdBasicVersion pbv = prodBasicVersionManager.findBasicVersionById(prodBasicVersionId);
			if(pbv!=null&&StringUtils.isNotBlank(entity.getVersionNo())&&StringUtils.isNotBlank(pbv.getVersionNo())){
				if(entity.getVersionNo().equals(pbv.getVersionNo())){
					//验证通过					
				}else{//不和当前版本号一致，且已经存在此版本号,则验证未通过					
					if(prodBasicVersionManager.hasExsitVersionNo(entity.getVersionNo())){
						Struts2Utils.renderText("false,此版本号已经存在.请修改再使用");
						flag=false; 
						return;
					}
				}
			}			
		//新增
		}else{
			//校验版本号是否唯一
			if(StringUtils.isNotBlank(entity.getVersionNo())){
				if(prodBasicVersionManager.hasExsitVersionNo(entity.getVersionNo())){
					Struts2Utils.renderText("false,此版本号已经存在.请修改再使用");
					flag=false;
					return;
				}
			}
		}
		//---<<<<<<<<<<<<<<<<<结束校验版本号
		
		String versionTime=Struts2Utils.getParameter("versionTime");
		if(StringUtils.isNotBlank(versionTime)){
			String []dStr=versionTime.split("-");
			
			//将原有最新设置为不可用
			prodBasicVersionManager.activeLastVersion(prodBasicVersionManager.ACTIVE_YES);
			
			//搜索统一年月是否存在，如存在，则更新
			ProdBasicVersion pv = prodBasicVersionManager.hasExsitVersion(dStr[0], StringUtils.leftPad(dStr[1], 2));
			if(pv!=null) {
				entity=pv;
			}
			//新的版本...
			entity.setActive(prodBasicVersionManager.ACTIVE_YES);
		}
		
		
	}
	
	/**
	 *是否存在激活的版本号
	 */
	public String findNewVersion(){
		if(prodBasicVersionManager.findNewestVersion()!=null){
			Struts2Utils.renderText("success,1");
		}else{
			Struts2Utils.renderText("false,2");
		}
		
		return null;
	}
	
	/**
	 * 保存版本号
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#save()
	 */
	@Override
	public String save() throws Exception {
		//如果前面验证通过
		if(flag){
			//版本时间
			String versionTime=Struts2Utils.getParameter("versionTime");
			if(StringUtils.isNotBlank(versionTime)){
				String []dStr=versionTime.split("-");
				entity.setYearCd(dStr[0]);
				entity.setMonthCd(StringUtils.leftPad(dStr[1], 2));
				//是否可用
				String active=Struts2Utils.getParameter("active");
				if(StringUtils.isNotBlank(active)){
					entity.setActive(active);
				}
				//版本号trim
				if(StringUtils.isNotBlank(entity.getVersionNo())){
					entity.setVersionNo(entity.getVersionNo().trim());
				}
			}
			//保存版本信息
			prodBasicVersionManager.saveProdBasicVersion(entity);
			//搜索当前激活版本号
			ProdBasicVersion newVersion=prodBasicVersionManager.findNewestVersion();
			String newVersionNo="";
			if(newVersion!=null){
				newVersionNo=newVersion.getVersionNo();
			}
			//返回当前激活的版本号
			Struts2Utils.renderText("success,"+entity.getProdBasicVersionId()+","+entity.getVersionNo()+","+newVersionNo);
		}
		return null;  
		
	}

	@Override
	public ProdBasicVersion getModel() {
		  
		// TODO Auto-generated method stub  
		return null;  
		
	}

	public ProdBasicVersion getEntity() {
		return entity;
	}

	public void setEntity(ProdBasicVersion entity) {
		this.entity = entity;
	}

	public List<ProdBasicVersion> getProdBasicVersions() {
		return prodBasicVersions;
	}

	public void setProdBasicVersions(List<ProdBasicVersion> prodBasicVersions) {
		this.prodBasicVersions = prodBasicVersions;
	}
	
	

}
  
