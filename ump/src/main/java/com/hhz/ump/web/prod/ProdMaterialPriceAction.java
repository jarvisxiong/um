/**  
 * ProdMaterialPriceAction.java  
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.prod.ProdBasicVersionManager;
import com.hhz.ump.dao.prod.ProdMaterialPriceManager;
import com.hhz.ump.dao.prod.ProdVersionDetailManager;
import com.hhz.ump.entity.prod.ProdBasicVersion;
import com.hhz.ump.entity.prod.ProdMaterialPrice;
import com.hhz.ump.entity.prod.ProdVersionDetail;

/**
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-11-8        下午05:31:25  
 *  
 */
public class ProdMaterialPriceAction extends CrudActionSupport<ProdMaterialPrice> {

	
	
	private static final long serialVersionUID = -6006476656993294083L;
	
	/**
	 * 版本号
	 */
	private ProdBasicVersion prodBasicVersion; 
	/**
	 * 当月工料价格
	 */
	private ProdMaterialPrice entity;
	/**
	 * 基准工料价格
	 */
	private ProdVersionDetail prodVersionDetail;
	/**
	 * 表单类型
	 */
	private String formType;
	/**
	 * 验证有效标记
	 */
	private boolean validateFlg=true;
	/**
	 * 地区
	 */
	private String areaCd;
	/**
	 * 时间
	 */
	private String ym;
	
	@Autowired
	protected ProdBasicVersionManager prodBasicVersionManager;
	@Autowired
	protected ProdMaterialPriceManager prodMaterialPriceManager;
	@Autowired
	protected ProdVersionDetailManager prodVersionDetailManager;
	
	/**
	 * 删除工料价格
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#delete()
	 */
	@Override
	public String delete() throws Exception {
		  String matePriceId=Struts2Utils.getParameter("matePriceId");
		prodMaterialPriceManager.deleteProdMaterialPrice(matePriceId);
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
	 * 获取当前激活的版本
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#list()
	 */
	@Override
	public String list() throws Exception {
		  //获取最新的版本
		prodBasicVersion=prodBasicVersionManager.findNewestVersion();	
		return "list";  
		
	}
	/**
	 *当月工料价格显示列表
	 */
	public String loadList(){
		//获取参数信息
		//业态
		String bussinessCd=Struts2Utils.getParameter("bussinessCd");
		//工料范围
		String materialZoneCd=Struts2Utils.getParameter("materialZoneCd");
		//地区
		String areaCd=Struts2Utils.getParameter("areaCd");	
		//年月
		String monthAndYear=Struts2Utils.getParameter("monthAndYear");	
		//搜索条件
		Map<String, Object> map = new HashMap<String, Object>();
		//准备搜索语句
		StringBuffer sbSql=new StringBuffer()
					.append(" from ProdMaterialPrice pp where 1=1 ");
		if(StringUtils.isNotBlank(bussinessCd)){
			sbSql.append(" and pp.bussinessCd = :bussinessCd");
			map.put("bussinessCd", bussinessCd);
		}
		if(StringUtils.isNotBlank(areaCd)){
			sbSql.append(" and pp.areaCd = :areaCd");
			map.put("areaCd", areaCd);
		}
		if(StringUtils.isNotBlank(materialZoneCd)){
			sbSql.append(" and pp.materialZoneCd = :materialZoneCd");
			map.put("materialZoneCd", materialZoneCd);
		}
		//年月
		if(StringUtils.isNotBlank(monthAndYear)){
			String[] strym=monthAndYear.split("-");
			sbSql.append(" and pp.yearCd = :yearCd");
			map.put("yearCd", strym[0]);
			sbSql.append(" and pp.monthCd = :monthCd");
			map.put("monthCd", StringUtils.leftPad(strym[1], 2));
		}
		//按照年月，地区，工料排序
		sbSql.append(" order by pp.yearCd desc,pp.monthCd desc,pp.areaCd,pp.materialZoneCd");//pp.bussinessCd
		//执行分页搜索
		page=prodMaterialPriceManager.findPage(page, sbSql.toString(), map);
		return "loadList";  
	}

	@Override
	protected void prepareModel() throws Exception {
		  
		// TODO Auto-generated method stub  
		
	}
	
	/**
	 *判断是否存在相同的时间、地区、产品业态、工料范围的工料价格
	 */
	public String hasExsitMaterial(){
		//搜索的4要素
		//时间
		String monthAndYear=Struts2Utils.getParameter("monthAndYear");
		//地区
		String areaCd=Struts2Utils.getParameter("areaCd");
		//产品业态
		String bussinessCd=Struts2Utils.getParameter("bussinessCd");
		//工料范围
		String materialZoneCd=Struts2Utils.getParameter("materialZoneCd");
		//拆分时间按为年月数组
		String[] strym=monthAndYear.split("-");
		//按照条件搜索
		List rs=prodMaterialPriceManager.hasProdMaterialPrice(strym[0], StringUtils.leftPad(strym[1], 2), bussinessCd, areaCd, materialZoneCd);
		//如果按照搜索条件，搜索出结果大于0，则表示已经存在相同数据
		if(rs!=null&&rs.size()>0){
			Struts2Utils.renderText("false;已经存在相同的时间、地区、产品业态、工料范围的工料价格,是否保存覆盖？");
		}else{
			Struts2Utils.renderText("success;");
		}
		return null;
	}
	/**
	 * 保存工料前预备动作，包括验证等
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#prepareSave()
	 */
	@Override
	public void prepareSave(){
		String monthAndYear=Struts2Utils.getParameter("monthAndYear");
		String prodMaterialPriceId=Struts2Utils.getParameter("prodMaterialPriceId");
		//如果为新增则需要验证一下字段
		if(StringUtils.isBlank(prodMaterialPriceId)){
			if(StringUtils.isBlank(monthAndYear)){
				Struts2Utils.renderText("failure,时间不能为空！");
				validateFlg=false;
				return ;
			}
			if(monthAndYear.length()!=7){
				Struts2Utils.renderText("failure,时间格式不正确！");
				validateFlg=false;
				return ;
			}
			if(StringUtils.isBlank(entity.getAreaCd())){
				Struts2Utils.renderText("failure,区域不能为空！");
				validateFlg=false;
				return ;
			}
			if(StringUtils.isBlank(entity.getBussinessCd())){
				Struts2Utils.renderText("failure,产品业态不能为空！");
				validateFlg=false;
				return ;
			}
			if(StringUtils.isBlank(entity.getMaterialZoneCd())){
				Struts2Utils.renderText("failure,工料范围不能为空！");
				validateFlg=false;
				return ;
			}
			
			
		}
		//拆分年月
		String[] strym=monthAndYear.split("-");
		//如果不为空,则表示更新
		if(StringUtils.isNotBlank(prodMaterialPriceId)){
			ProdMaterialPrice pp = prodMaterialPriceManager.getEntity(prodMaterialPriceId);				
			pp.setCurrentMonthPrice(entity.getCurrentMonthPrice());
			entity=pp;
		//新增的情况	
		}else{
			//首先搜索是否已经存在重复数据的情况
			List<ProdMaterialPrice> rs=prodMaterialPriceManager.hasProdMaterialPrice(strym[0], StringUtils.leftPad(strym[1], 2), entity.getBussinessCd(), entity.getAreaCd(), entity.getMaterialZoneCd());
			//如果按照搜索条件，搜索出结果大于0，则表示已经存在相同数据,则更新价格
			if(rs!=null&&rs.size()>0){
				ProdMaterialPrice pp=rs.get(0);
				pp.setCurrentMonthPrice(entity.getCurrentMonthPrice());
				entity=pp;
			//如果不存在重复，将数据保存
			}else{
				entity.setYearCd(strym[0]);
				entity.setMonthCd(StringUtils.leftPad(strym[1], 2));
			}
			
		}
	}
	
	/**
	 * 保存当前工料价格
	 * (non-Javadoc)  
	 * @see com.hhz.core.web.CrudActionSupport#save()
	 */
	@Override
	public String save() throws Exception {
		//如果验证通过
		if(validateFlg) {
			//新增修改前判断是否重复
			prodMaterialPriceManager.saveProdMaterialPrice(entity);				
			Struts2Utils.renderText("success,");
		}			
		return null;  
		
	}

	@Override
	public ProdMaterialPrice getModel() {
		  
		// TODO Auto-generated method stub  
		return null;  
		
	}
	
	/**
	 *获取表单
	 */
	public String getForm(){
		//当月工料价格记录ID
		String prodMaterialPriceId=Struts2Utils.getParameter("prodMaterialPriceId");
		//编辑
		if(StringUtils.isNotBlank(prodMaterialPriceId)){
			entity = prodMaterialPriceManager.getEntity(prodMaterialPriceId);
			if(entity!=null){
				Map<String, Object> map = new HashMap<String, Object>();			
//				map.put("yearCd", entity.getYearCd());
//				map.put("monthCd", StringUtils.leftPad(entity.getMonthCd(), 2));
				map.put("bussinessCd", entity.getBussinessCd());
				map.put("materialZoneCd", entity.getMaterialZoneCd());
				map.put("areaCd", entity.getAreaCd());
				prodVersionDetail=prodVersionDetailManager.findProdVersionDetail(map,"1");
			}
			
		}
		return "form";
	}

	public ProdBasicVersion getProdBasicVersion() {
		return prodBasicVersion;
	}

	public void setProdBasicVersion(ProdBasicVersion prodBasicVersion) {
		this.prodBasicVersion = prodBasicVersion;
	}

	public ProdVersionDetail getProdVersionDetail() {
		return prodVersionDetail;
	}

	public void setProdVersionDetail(ProdVersionDetail prodVersionDetail) {
		this.prodVersionDetail = prodVersionDetail;
	}

	public ProdMaterialPrice getEntity() {
		return entity;
	}

	public void setEntity(ProdMaterialPrice entity) {
		this.entity = entity;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getAreaCd() {
		return areaCd;
	}

	public void setAreaCd(String areaCd) {
		this.areaCd = areaCd;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}
	
	
	

}
  
