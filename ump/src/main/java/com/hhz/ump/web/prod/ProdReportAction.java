/**  
 * ProdReportAction.java  
 * com.hhz.ump.web.prod  
 *  
 * Function： 价格指数曲线图表   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-11-9        zhongyubing  
 *  
 * Copyright (c) 2011, PD All Rights Reserved.  
*/  
  
package com.hhz.ump.web.prod;  

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.prod.ProdMaterialPriceManager;
import com.hhz.ump.entity.prod.ProdMaterialPrice;
import com.hhz.ump.util.DateUtil;


/**  
 *图表，报表等 
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-11-9        下午06:29:44  
 *   
 */
public class ProdReportAction  extends CrudActionSupport {

	
	private static final long serialVersionUID = -4483139649052038888L;
	
	/**
	 * 时间列表
	 */
	private List<String> dateList;
	/**
	 * 表单类型
	 */
	private String formType;
	/**
	 * 业态
	 */
	private String bussinessCd;
	/**
	 * 时间
	 */
	private String ym;
	/**
	 * 工料价格指数列表
	 */
	private List <PriceIndexVotwo> priceIndexVotwos;
	
	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	@Autowired
	protected ProdMaterialPriceManager prodMaterialPriceManager;
	
	@Override
	public String delete() throws Exception {
		  
		// TODO Auto-generated method stub  
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
		  
		// TODO Auto-generated method stub  
		return null;  
		
	}

	@Override
	protected void prepareModel() throws Exception {
		  
		// TODO Auto-generated method stub  
		
	}

	@Override
	public String save() throws Exception {
		  
		// TODO Auto-generated method stub  
		return null;  
		
	}

	@Override
	public ProdMaterialPrice getModel() {
		  
		// TODO Auto-generated method stub  
		return null;  
		
	}
	
	/**
	 *返回价格指数曲线图预备页面 
	 *
	 */
	public String priceIndexChart(){			
		return "priceIndexChart";
	}
	
	
	/**
	 * 
	 *根据搜索参数，搜索工料指数曲线及工料价格曲线的数据，
	 *通过json格式返回数据
	 *
	 */
	public String jsonChart(){
		//业态
		bussinessCd=Struts2Utils.getParameter("bussinessCd");
		//如果业态参数为空,则默认搜索业态-1（19-32层住宅）
		if(StringUtils.isBlank(bussinessCd)){
			bussinessCd="1";
		}
		//开始时间
		String dateFrom=Struts2Utils.getParameter("dateFrom");
		//结束时间
		String dateTo=Struts2Utils.getParameter("dateTo");
		//根据开始和技术时间算出时间列表
		dateList=prodMaterialPriceManager.getDateList(dateFrom,dateTo);		
		//工料范围
		String mateZoneCd=Struts2Utils.getParameter("mateZoneCd");
		//建安单价造价
		String constructType=Struts2Utils.getParameter("constructType");
		//通过业态、时间列表、工料范围搜索出相应的工料指数曲线或者工料价格曲线数据
		Struts2Utils.renderJson(prodMaterialPriceManager.getPriceIndexData(bussinessCd,dateList,mateZoneCd,constructType));
				
		return null;
	}
	
	
	public List<String> getDateList(){
		return dateList;
	}
	/**
	 * 工料指数曲线，工料价格曲线 获取年月列表
	 */
	public String jsonChartYearMonth(){
		String dateFrom=Struts2Utils.getParameter("dateFrom");		
		String dateTo=Struts2Utils.getParameter("dateTo");
		//时间列表
		dateList=prodMaterialPriceManager.getDateList(dateFrom,dateTo);
		Collections.sort(dateList);
		List<String> ds=new ArrayList<String>();
		String ym="";
		for(String d:dateList){
			ym=d.substring(2);
			ds.add(ym.replaceAll("-", ""));
		}
		Struts2Utils.renderJson(ds);
		return null;
	}
	
	

	
	/**
	 * 
	 * 用于搜索的表单，根据本同的搜索条件返回不同的表单
	 * 
	 */
	public String searchForm(){
		if(formType.equals("espForm"))
			return "espForm";
		else
			return "searchForm";
		
	}
	
	/**
	 * 以业态和时间进行搜索
	 * 搜索规则：默认搜索酒店业态、当前时间,下一个月,上一年当前时间的数据;
	 * 如果时间不为空,则以规定的"ym"时间,计算上一个月,上一年的数据。		
	 */
	public String priceIndexTwo(){
		//业态
		 bussinessCd=Struts2Utils.getParameter("bussinessCd");
		//时间
		 ym=Struts2Utils.getParameter("ym");
		//当前年
		String thisyear="";
		//当月
		String thismonth="";
		//去年
		String lastyear="";
		//上个月
		String lastmonth="";
		//如果选择了年月,则解析年月
		if(StringUtils.isNotBlank(ym)){			
			String[] ymArr=ym.split("-");
			thisyear=ymArr[0];
			thismonth=StringUtils.leftPad(ymArr[1], 2);
			String lastym=DateUtil.getLastYearAndMon(ym);
			lastyear=lastym.split("-")[0];
			String lastmont=DateUtil.getLastMonth(ym);
			lastmonth=lastmont.split("-")[1];
		//如果没有选择时间，则取当前年月进行解析
		}else{
			ym=DateUtil.getCurrentTime();
			String[] ymArr=ym.split("-");
			thisyear=ymArr[0];
			thismonth=StringUtils.leftPad(ymArr[1], 2);
			String lastym=DateUtil.getLastYearAndMon(ym);
			lastyear=lastym.split("-")[0];
			String lastmont=DateUtil.getLastMonth(ym);
			lastmonth=lastmont.split("-")[1];
		}
		//如果业态为空,则默认酒店为搜索
		if(StringUtils.isBlank(bussinessCd)){
			bussinessCd="1";//19-32层住宅
		}
		//执行搜索,返回VO结果
		priceIndexVotwos=prodMaterialPriceManager.priceIndexVersionTwo(bussinessCd, thisyear, thismonth,lastyear,lastmonth);//("1", "7", "2011", "08","2010","07");
		return "priceIndexTwo";
	}

	public List<PriceIndexVotwo> getPriceIndexVotwos() {
		return priceIndexVotwos;
	}

	public void setPriceIndexVotwos(List<PriceIndexVotwo> priceIndexVotwos) {
		this.priceIndexVotwos = priceIndexVotwos;
	}

	public String getBussinessCd() {
		return bussinessCd;
	}

	public void setBussinessCd(String bussinessCd) {
		this.bussinessCd = bussinessCd;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}
	
	

}
  
