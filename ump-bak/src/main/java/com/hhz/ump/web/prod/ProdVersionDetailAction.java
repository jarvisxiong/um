/**  
 * ProdVersionDetailAction.java  
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

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.prod.ProdBasicVersionManager;
import com.hhz.ump.dao.prod.ProdVersionDetailManager;
import com.hhz.ump.entity.prod.ProdBasicVersion;
import com.hhz.ump.entity.prod.ProdVersionDetail;

/**  
 *版本明细 
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-11-8        上午09:02:08  
 *  
 */
@Namespace("/prod")
public class ProdVersionDetailAction  extends CrudActionSupport<ProdVersionDetail>{

	
	private static final long serialVersionUID = 2347814313482835063L;
	
	/**
	 * 版本号
	 */
	private ProdBasicVersion prodBasicVersion; 
	
	/**
	 * 版本明细（工料价格基准）
	 */
	private ProdVersionDetail entity;
	/**
	 * 标记
	 */
	private boolean validateFlg=true;
	/**
	 * 当前版本号
	 */
	private String currentVersion;
	/**
	 * 请求的表单类型
	 */
	private String formType;
	/**
	 * 版本列表
	 */
	private List<ProdBasicVersion>  versionList;
	
	@Autowired
	protected ProdBasicVersionManager prodBasicVersionManager;
	@Autowired
	protected ProdVersionDetailManager prodVersionDetailManager;

	/**
	 * 删除指定ID的工料基础数据
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#delete()
	 */
	@Override
	public String delete() throws Exception {
		String prodVersionDetailId=Struts2Utils.getParameter("prodVersionDetailId");
		if(StringUtils.isNotBlank(prodVersionDetailId)){
			prodVersionDetailManager.deleteProdVersionDetail(prodVersionDetailId);
		}		
		return null;  
		
	}

	@Override
	public String deleteBatch() throws Exception {
		  
		// TODO Auto-generated method stub  
		return null;  
		
	}

	/**
	 * 搜索工料基准版本
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#input()
	 */
	@Override
	public String input() throws Exception {
		
		
		return "input";  
		
	}
	
	/**
	 * 搜索当前有效版本的的基准工料价格
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#list()
	 */
	@Override
	public String list() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		versionList=prodBasicVersionManager.find("from ProdBasicVersion", map);
		for(ProdBasicVersion bversion:versionList ){
			if(ProdBasicVersionManager.ACTIVE_YES.equals(bversion.getActive())){
				//当前有效版本
				if(bversion!=null) {
					prodBasicVersion=bversion;
					currentVersion=bversion.getVersionNo();
				}
				break;
			}
		}
		return "list";  
		
	}

	@Override
	protected void prepareModel() throws Exception {
		
		
	}
	/**
	 * 保存修改前的过滤执行方法
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#prepareSave()
	 */
	@Override
	public void prepareSave() throws Exception{
		String prodVersionDetailId=Struts2Utils.getParameter("prodVersionDetailId");
		
		//如果是新增		
		if(StringUtils.isBlank(prodVersionDetailId)){
			if(StringUtils.isBlank(entity.getBussinessCd())){
				  Struts2Utils.renderText("failure,产品业态不能空！");
				  validateFlg=false;
				  return ;
			  }
			  if(StringUtils.isBlank(entity.getMaterialZoneCd())){
				  Struts2Utils.renderText("failure,工料范围不能空！");
				  validateFlg=false;
				  return ;
			  }
			 
		}
		  
	}
	
	/**
	 * 保存修改基准工料价格数据
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#save()
	 */
	@Override
	public String save() throws Exception {
		String prodBasicVersionId=Struts2Utils.getParameter("prodBasicVersionNo");
		String prodVersionDetailId=Struts2Utils.getParameter("prodVersionDetailId");
		//编辑原有数据
		if(StringUtils.isNotBlank(prodVersionDetailId)){
				//原版本数据
				ProdVersionDetail pd = prodVersionDetailManager.getEntity(prodVersionDetailId);			
				//如果不重复使用原始ID进行更新				
				pd.setPermeterQuantity(entity.getPermeterQuantity());
				pd.setPrice(entity.getPrice());
				pd.setEstimatePrice(entity.getEstimatePrice());
				entity=pd;
			}				
		else{
			//新增数据
			if(StringUtils.isNotBlank(prodBasicVersionId)){
				//判断此版本号是否存在(有可能被删除)
				if(prodBasicVersionManager.findBasicVersionById(prodBasicVersionId)!=null){
					//取消地区
					ProdVersionDetail pd = prodVersionDetailManager.getActiveProdVersionDetail(prodBasicVersionId, entity.getBussinessCd(), entity.getMaterialZoneCd());//,entity.getAreaCd()
					if(pd!=null){
						//已经存在,则更更新
						pd.setPermeterQuantity(entity.getPermeterQuantity());
						pd.setPrice(entity.getPrice());
						pd.setEstimatePrice(entity.getEstimatePrice());
						entity=pd;
					}else{//不存在,增加			
							ProdBasicVersion pb = prodBasicVersionManager.getEntity(prodBasicVersionId);
							entity.setActive(prodBasicVersionManager.ACTIVE_YES);
							entity.setProdBasicVersion(pb);
						}
				}else{
					 Struts2Utils.renderText("failure,此版本号已不存在.请刷新本页面后重新操作！");
				}
							
			}else{				
				 Struts2Utils.renderText("failure,版本号为空.请选择一个可用的版本！");
				  validateFlg=false;
				  return null; 
			}
		}
		//如果是有效的情况（验证通过）,则保存
		if(validateFlg) {
			prodVersionDetailManager.saveProdVersionDetail(entity);
			Struts2Utils.renderText("success,");
		}
		return null;  
		
	}
	
	/**
	 * 
	 * 判断是否已经存在相同的基准工料价格
	 */
	public String hasExsitProdDetail() throws Exception{
		String bussinessCd=Struts2Utils.getParameter("bussinessCd");
		String materialZoneCd=Struts2Utils.getParameter("materialZoneCd");
		String prodBasicVersionId=Struts2Utils.getParameter("prodBasicVersionId");
		String areaCd=Struts2Utils.getParameter("areaCd");
		//新增数据
		if(StringUtils.isNotBlank(prodBasicVersionId)){
			//取消地区
			ProdVersionDetail pd = prodVersionDetailManager.getActiveProdVersionDetail(prodBasicVersionId, bussinessCd, materialZoneCd);//,areaCd
			//如果不为空，则表示已经存在重复数据
			if(pd!=null){
				Struts2Utils.renderText("false,已存在此版本的相同产品业态、相同工料范围的数据.是否覆盖？");
			}else{
				//不存在重复数据
				Struts2Utils.renderText("success,");
			}
		}else{
			Struts2Utils.renderText("success,");
		}
		return null;
	}
	
	/**
	 * 
	 * 获取版本相应的基准工料价格
	 */
	public String getBasicDetailPrice(){
		//业态
		String bussinessCd=Struts2Utils.getParameter("bussinessCd");
		//工料范围
		String materialZoneCd=Struts2Utils.getParameter("materialZoneCd");
		//年月
		String monthAndYear=Struts2Utils.getParameter("monthAndYear");
		//地区
		String areaCd=Struts2Utils.getParameter("areaCd");
		if(StringUtils.isNotBlank(monthAndYear)){
			//拆分时间按为年月数组
			String[] strym=monthAndYear.split("-");
			Map<String, Object> map = new HashMap<String, Object>();			
			map.put("yearCd", strym[0]);
			map.put("monthCd", StringUtils.leftPad(strym[1], 2));
			map.put("bussinessCd", bussinessCd.trim());
			map.put("materialZoneCd", materialZoneCd.trim());
			map.put("areaCd", areaCd.trim());
			ProdVersionDetail pvd=prodVersionDetailManager.findProdVersionDetail(map,"1");
			//返回基准工料价格
			if(pvd!=null) {
				Struts2Utils.renderText("success,"+pvd.getPrice());
			}else{
				Struts2Utils.renderText("false,");
			}
		}else{
			Struts2Utils.renderText("false,");
		}
		
		
		
		return null;
	}
	

	@Override
	public ProdVersionDetail getModel() {
		  
		// TODO Auto-generated method stub  
		return null;  
		
	}
	
	/**
	 * 
	 *根据条件搜索基准工料价格列表
	 */
	public String loadList() throws Exception{
		//当前版本号
		 currentVersion=Struts2Utils.getParameter("versionNo");
		 //如果没有版本，则取激活的版本
		if(StringUtils.isBlank(currentVersion)){
			//当前有效版本
			prodBasicVersion=prodBasicVersionManager.findNewestVersion();
			if(prodBasicVersion!=null){
				currentVersion=prodBasicVersion.getVersionNo();
			}
		}
		//获取参数信息
		//业态
		String bussinessCd=Struts2Utils.getParameter("bussnissCd");
		//工料范围
		String materialZoneCd=Struts2Utils.getParameter("materialZoneCd");
		//版本号ID
		String prodBasicVersionNo=Struts2Utils.getParameter("prodBasicVersionNo");
		//地区
		String areaCd=Struts2Utils.getParameter("areaCd");
		//搜索条件
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("active", ProdBasicVersionManager.ACTIVE_YES);
		//准备搜索语句
		StringBuffer sbSql=new StringBuffer()
					.append(" from ProdVersionDetail pd where pd.active = :active ");
		if(StringUtils.isNotBlank(bussinessCd)){
			sbSql.append(" and pd.bussinessCd = :bussinessCd");
			map.put("bussinessCd", bussinessCd);
		}
		if(StringUtils.isNotBlank(materialZoneCd)){
			sbSql.append(" and pd.materialZoneCd = :materialZoneCd");
			map.put("materialZoneCd", materialZoneCd);
		}
		if(StringUtils.isNotBlank(prodBasicVersionNo)){
			sbSql.append(" and pd.prodBasicVersion.prodBasicVersionId = :prodBasicVersionNo");
			map.put("prodBasicVersionNo", prodBasicVersionNo);
		}
		if(StringUtils.isNotBlank(currentVersion)){
			sbSql.append(" and pd.prodBasicVersion.versionNo = :versionNo");
			map.put("versionNo", currentVersion.trim());
		}
		if(StringUtils.isNotBlank(areaCd)){
			sbSql.append(" and pd.areaCd = :areaCd");
			map.put("areaCd", areaCd.trim());
		}
		//分页搜索
		page=prodVersionDetailManager.findPage(page, sbSql.toString(), map);
		
		return "loadList";
	}
	
	/**
	 * 获取修改,新增的表单
	 */
	public String getForm() throws Exception{
		//如果是基础版本
		if("basicVersionForm".equals(formType)){
			//TODO
			String basicVersionId=Struts2Utils.getParameter("basicVersionId");
			if(StringUtils.isNotBlank(basicVersionId)){
				prodBasicVersion=prodBasicVersionManager.findBasicVersionById(basicVersionId);
			}			
		}
		//如果新增工料基础数据表单
		if("newVersionDetailForm".equals(formType)){			
			//如果存在entity的prodVersionDetailId,则是需要编辑
			String prodVersionDetailId=Struts2Utils.getParameter("prodVersionDetailId");
			if(StringUtils.isNotBlank(prodVersionDetailId)){
				entity=prodVersionDetailManager.getProdVersionDetailById(prodVersionDetailId);
			}else{
				//当前有效版本,新增
				prodBasicVersion=prodBasicVersionManager.findNewestVersion();
			}
		}
		return "detailForm";
	}
	
	/**
	 * 获取基准单位估算价格
	 */
	public String loadEstimatePrice(){
		//获取参数信息
		String bussinessCd=Struts2Utils.getParameter("bussinessCd");
		//String materialZoneCd=Struts2Utils.getParameter("materialZoneCd");
		String versionNo=Struts2Utils.getParameter("versionNo");
		//取消地区
		//String areaCd=Struts2Utils.getParameter("areaCd");
		if(StringUtils.isNotBlank(bussinessCd)//&&StringUtils.isNotBlank(materialZoneCd)
				&&StringUtils.isNotBlank(versionNo)){//&&StringUtils.isNotBlank(areaCd)
			//拆分时间按为年月数组
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bussinessCd", bussinessCd.trim());
			//map.put("materialZoneCd", materialZoneCd.trim());			
			//map.put("areaCd", areaCd.trim());
			map.put("prodBasicVersionId", versionNo.trim());
			ProdVersionDetail pvd=prodVersionDetailManager.findProdVersionDetail(map,null);
			//返回基准工料价格
			if(pvd!=null) {
				Struts2Utils.renderText("success,"+pvd.getEstimatePrice());
			}else{
				Struts2Utils.renderText("false,");
			}
		}else{
			Struts2Utils.renderText("false,");
		}
		return null;
	}

	public ProdBasicVersion getProdBasicVersion() {
		return prodBasicVersion;
	}

	public void setProdBasicVersion(ProdBasicVersion prodBasicVersion) {
		this.prodBasicVersion = prodBasicVersion;
	}

	public ProdVersionDetail getEntity() {
		return entity;
	}

	public void setEntity(ProdVersionDetail entity) {
		this.entity = entity;
	}

	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public List<ProdBasicVersion> getVersionList() {
		return versionList;
	}

	public void setVersionList(List<ProdBasicVersion> versionList) {
		this.versionList = versionList;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}
	
	
	
	
	
	

}
  
