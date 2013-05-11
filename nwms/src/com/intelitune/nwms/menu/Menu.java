package com.intelitune.nwms.menu;

import java.io.Serializable;

import net.sf.click.Page;

import com.intelitune.nwms.common.CommonAccount;
import com.intelitune.nwms.common.Const;

public class Menu extends Page implements Serializable{
	private CommonAccount commonAccount = null;
	
	
	// 系统设置
	private String systemset = "";
	// 仓库管理
	private String warehouse = "";
	
	private String orderitem="";
	
	private String outbound="";
	
	private String material = "";
	
	private String inbound= "";
	
	private String systemSetting="";
	
	private String search="";
	
	private String exchange="";

	
	public void setSystemset(String systemset) {
		this.systemset = systemset;
	}

	public String getSystemSetting() {
		return systemSetting;
	}

	public void setSystemSetting(String systemSetting) {
		this.systemSetting = systemSetting;
	}

	public String getOutbound() {
		return outbound;
	}

	public void setOutbound(String outbound) {
		this.outbound = outbound;
	}

	public Menu() {
//		commonAccount = CommonAccount.getInstance(getContext().getSession());
		checkAccessAndHideMenuLink();
	}

	public String getOrderitem()
	{
		return orderitem;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void setOrderitem(String orderitem)
	{
		this.orderitem= orderitem;
	}

	/**
	 * 根据用户的权限隐藏相应的menu。
	 * 
	 * @author Success
	 * @date 2008-12-16
	 */
	public void checkAccessAndHideMenuLink() {
		
		// 系统设置
//		if (commonAccount.accountLoginType == Const.ACCOUNT_LOGIN_TYPE_STATION) {
			systemset = "<ul>";
			systemset += "<li><a href ='../admin/userList.htm'>" + this.getMessage("userList") + "</a></li>";
			systemset += "<li><a href ='../admin/access.htm'>" + this.getMessage("access") + "</a></li>";
			systemset += "</ul>";
//		}
			
			outbound= "<ul>";
			outbound += "<li><a href ='../outbound/orderList.htm'>" + this.getMessage("print_list") + "</a></li>";
			outbound += "<li><a href ='../outbound/orderCheckout.htm'>" + this.getMessage("checkout_outbound") + "</a></li>";
			outbound += "</ul>";
			
			warehouse= "<ul>";
			warehouse += "<li><a href ='../warehouse/WarehouseList.htm'>" + this.getMessage("warehouseSetting") + "</a></li>";
			warehouse += "<li><a href ='../warehouse/zoneList.htm'>" + this.getMessage("zoneSetting") + "</a></li>";
			warehouse += "</ul>";
			
			
			orderitem= "<ul>";
			orderitem += "<li><a href ='../item/orderList.htm'>" + this.getMessage("orderList") + "</a></li>";
			orderitem += "</ul>";
			
		
			
			material = "<ul>";
			material +="<li><a href='../material/searchMaterial.htm'>" + this.getMessage("searchmaterial") + "</a></li>";
			material +="<li><a href='../material/createMaterial.htm'>" + this.getMessage("creatematerial") + "</a></li>";
			material +="<li><a href='../material/createUnit.htm'>" + this.getMessage("createunit") + "</a></li>";
			material +="</ul>";
			
			inbound = "<ul>";
			inbound += "<li><a href='../inbound/searchInboundOrder.htm'>" + this.getMessage("inboundOrder") +"</a></li>";
			inbound += "<li><a href='../inbound/directInbound.htm'>" + this.getMessage("directInbound") +"</a></li>";
			inbound += "</ul>";
			
			systemSetting = "<ul>";
			systemSetting += "<li><a href='../material/importMaterial.htm'>" + this.getMessage("import_material") +"</a></li>";
			systemSetting += "<li><a href='../upload/excelUpload.htm'>" + this.getMessage("import_storageitem") +"</a></li>";
			systemSetting += "<li><a href='../item/addItemType.htm'>" + this.getMessage("add_itemtype") +"</a></li>";
			systemSetting += "</ul>";
			
			search = "<ul>";
			search += "<li><a href='../search/StorageItemSearch.htm'>" + this.getMessage("storageitem_search") +"</a></li>";
			search += "<li><a href='../search/recordSearch.htm'>" + this.getMessage("record_search") +"</a></li>";
			search += "</ul>";
			
			exchange = "<ul>";
			exchange += "<li><a href='../exchange/exchangeSearch.htm'>" + this.getMessage("exchange") +"</a></li>";
			exchange += "<li><a href='../exchange/exchangeRecord.htm'>" + this.getMessage("exchange_record") +"</a></li>";
			exchange += "</ul>";

			
	}

	public String getSystemset() {
		return systemset;
	}

	public void setSysteset(String systeset) {
		this.systemset = systeset;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	
	public String buildWarehouse(){
		warehouse = "<ul>";
		warehouse += "<li><a href ='../warehouse/WarehouseList.htm'>" + this.getMessage("warehouse_list") + "</a></li>";
		warehouse += "</ul>";
		return warehouse;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getInbound() {
		return inbound;
	}

	public void setInbound(String inbound) {
		this.inbound = inbound;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	
}
