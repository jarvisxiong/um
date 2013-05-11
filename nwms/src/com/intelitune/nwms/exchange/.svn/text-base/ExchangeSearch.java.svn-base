package com.intelitune.nwms.exchange;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.click.Context;
import net.sf.click.control.AbstractLink;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Decorator;
import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.LinkDecorator;
import net.sf.click.extras.control.TableInlinePaginator;

import com.axis2.services.MCSserviceStub.InttClientDetailWS;
import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.control.TableEx;
import com.intelitune.export.Exporter;
import com.intelitune.export.Template;
import com.intelitune.export.jxls.DocumentImpl;
import com.intelitune.export.jxls.ExporterImpl;
import com.intelitune.export.jxls.TemplateImpl;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.ItemType;
import com.intelitune.nwms.model.StorageItem;
import com.intelitune.nwms.model.Warehouse;
import com.intelitune.nwms.service.ItemTypeService;
import com.intelitune.nwms.service.ItemTypeServiceImpl;
import com.intelitune.nwms.service.OutboundAssignedService;
import com.intelitune.nwms.service.OutboundAssignedServiceImp;
import com.intelitune.nwms.service.OwnerZoneService;
import com.intelitune.nwms.service.OwnerZoneServiceImp;
import com.intelitune.nwms.service.StorageItemService;
import com.intelitune.nwms.service.StorageItemServiceImp;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;
import com.intelitune.nwms.service.ZoneStateService;
import com.intelitune.nwms.service.ZoneStateServiceImp;

public class ExchangeSearch extends com.intelitune.nwms.common.BorderPage {
	
	Menu me = new Menu();
	
	public String menuInclude = me.getExchange();
	
	public String position = this.getMessage("exchange_position");
	
	public String title=this.getMessage("exchange_search");
	
	public Form form=new Form();
	
	public TableEx table=new TableEx();
	
	public Select ownerSelect;
	public Select warehouseSelect;
	public Select itemTypeSelect;
	public WMSServiceInf wss =  WMSServiceImp.getInstance();
	public OwnerZoneService ozs= OwnerZoneServiceImp.getInstance();
	public StorageItemService sis= StorageItemServiceImp.getInstance();
	public OutboundAssignedService oas=OutboundAssignedServiceImp.getInstance();
	public ItemTypeService its=ItemTypeServiceImpl.getInstance();
	public ZoneStateService zss= ZoneStateServiceImp.getInstance();
	public WarehouseService ws= WarehouseServiceImp.getInstance();
	public TextField product_code = new TextField("product_code",this.getMessage("product_code"));
//	public TextField warehouse = new TextField("warehouse_id",this.getMessage("warehouse"),true);
	public TextField invoice=new TextField("invoice_code",this.getMessage("invoiceCode"));
	private AbstractLink[] links;
	public ActionLink exchange_link = new ActionLink("exchange", getMessage("exchange"),
			this, "onExchange");
	
	public void onInit(){
		super.onInit();
		List<InttClientDetailWS> list=ozs.findOwnerList();
		ownerSelect.add(new Option(""));
	        for (Iterator<InttClientDetailWS> i = list.iterator(); i.hasNext();) {
	        	InttClientDetailWS icd = (InttClientDetailWS) i.next();
	        	ownerSelect.add(new Option(icd.getId(),icd.getCnName()));
	        }
	    List<Warehouse> list1=ws.findWarehouseList();
	    warehouseSelect.add(new Option(""));
        for (Iterator<Warehouse> i = list1.iterator(); i.hasNext();) {
        	Warehouse w = (Warehouse) i.next();
        	warehouseSelect.add(new Option(w.getId(),w.getAlias()));
        }
	    List<ItemType> list2=its.findItemTypeList();
	    itemTypeSelect.add(new Option(""));
	    for (Iterator<ItemType> i = list2.iterator(); i.hasNext();) {
	    	ItemType it = (ItemType) i.next();
        	itemTypeSelect.add(new Option(it.getId(),it.getAlias()));
        }
	}
	
	public ExchangeSearch(){
		ownerSelect = new Select(this.getMessage("zone_customer_name"),true);
		ownerSelect.setRequired(true);
		warehouseSelect = new Select(this.getMessage("warehouse"),true);
		warehouseSelect.setRequired(true);
		itemTypeSelect = new Select(this.getMessage("itemtype"));
		form.add(ownerSelect);
		form.add(warehouseSelect);
		form.add(itemTypeSelect);
		form.add(product_code);
		form.add(invoice);
		form.add(new Submit(this.getMessage("search"),this,"onSearch"));
		form.add(new Submit(this.getMessage("reset"),this,"onReset"));
		form.add(new Submit(this.getMessage("print"),this,"onPrint"));
		//form.add(new Submit(this.getMessage("printcheck"),this,"onPrintCheck"));
		
		
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		table.setPageSize(10);
		
		Column column_productcode=new Column("productcode",this.getMessage("product_code"));
		column_productcode.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				StorageItem  si = (StorageItem) object;
				return si.getMaterial().getCode();
			}
		 });
		table.addColumn(column_productcode);
//		Column ccolumn = new Column("crn","客户业务编号");
//		table.addColumn(ccolumn);
//		ccolumn.setDecorator(new Decorator(){
//			public String render(Object object, Context context) {
//				StorageItem si = (StorageItem)object;
//				try {
//					return wss.findByJobId(si.getJobId()).getRn();
//				} catch (NumberFormatException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (RemoteException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				return null;
//			}				
//		});
		Column column_productname=new Column("productname",this.getMessage("product_name"));
		column_productname.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				StorageItem  si = (StorageItem) object;
				return si.getMaterial().getAlias();
			}
		 });
		table.addColumn(column_productname);
		
		Column column_bin=new Column("bin",this.getMessage("bin_code"));
		column_bin.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				StorageItem  si = (StorageItem) object;
				if(si.getBin()==null){
					return "";
				}else{
					return si.getBin().getCode();
				}
			}
		 });
		table.addColumn(column_bin);
		
		Column column_sn=new Column("sn",this.getMessage("sn"));
		column_sn.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				StorageItem  si = (StorageItem) object;
				return si.getSn();
			}
		 });
		table.addColumn(column_sn);
		
		Column column_qty=new Column("qty",this.getMessage("qty"));
		column_qty.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				StorageItem  si = (StorageItem) object;
				return String.valueOf(si.getQty());
			}
		 });
		table.addColumn(column_qty);
		
		Column column_invoice=new Column("invoice",this.getMessage("invoiceCode"));
		column_invoice.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				StorageItem  si = (StorageItem) object;
				if(si.getInvoice()==null){
					return "";
				}else{
					return si.getInvoice().getCode();
				}
			}
		 });
		table.addColumn(column_invoice);
		Column column = new Column("action",this.getMessage("edit"));
		column.setTextAlign("center");
		links = new AbstractLink[] {exchange_link};
		column.setDecorator(new LinkDecorator(table, links, "id"));
		table.addColumn(column);
		
	}
	
	public void onRender(){
		String hql="";
		List<StorageItem> list=new ArrayList<StorageItem>();
		if(this.getContext().getSessionAttribute("storage_search_hql")!=null){
			hql=this.getContext().getSessionAttribute("storage_search_hql").toString();
			list=sis.findStorageItemByProductCodeAndClient_id(hql);
		}
		List<StorageItem> result=new ArrayList<StorageItem>();
//		List<StorageItem> result1=new ArrayList<StorageItem>();
//		if(list!=null){
//			if(list.size()>0){
//				StorageItem list_result = null;
//				result.add(list.get(0));
//				float startq=list.get(0).getQty();
//				result.get(0).setBkt_sum(startq);
//				for(int i=1;i<list.size();i++){
//					StorageItem list_item=list.get(i);
//					if(list_item.getBin()!=null&&list_item.getMaterial()!=null){
//					//如果无SN
//						if("N/A".equals(list_item.getSn())||"".equals(list_item.getSn())){
//							int j=0;
//							for(;j<result.size();j++){
//								list_result=result.get(j);
//								//如果库位相同
//									if(list_item.getBin().getCode().trim().toUpperCase().equals(list_result.getBin().getCode().trim().toUpperCase())&&list_item.getMaterial().getCode().toUpperCase().trim().equals(list_result.getMaterial().getCode().toUpperCase().trim())){
//										float q=list_item.getQty()+list_result.getBkt_sum();
//	//									list_result.setQty(q);
//										list_result.setBkt_sum(q);
//										break;
//									}
//								
//							}
//							if(j==result.size()){
//								list_item.setBkt_sum(list_item.getQty());
//								result.add(list_item);
//							}
//						}else{//如果有SN
//							list_item.setBkt_sum(list_item.getQty());
//							result.add(list_item);
//						}
//					}
//				}
//			}
//		}
		for(int i=0;i<list.size();i++){
			if(list.get(i).getQty()!=0){
				result.add(list.get(i));
			}
		}
		table.setRowList(result);
		
	}
	
	
	public boolean onSearch(){
		if(form.isValid()){
			String client_id=ownerSelect.getValue();
			String productcode=product_code.getValue();
			String warehouse_id=warehouseSelect.getValue();
			String itemtype_id=itemTypeSelect.getValue();
			String invoice_code=invoice.getValue();
			String hql="";
			if(client_id!=null&&!"".equals(client_id)&&warehouse_id!=null&&!"".equals(warehouse_id)){
				hql="from StorageItem as s where s.material.inttClientDetailWSId='"+client_id+"' and s.warehouse.id='"+warehouse_id+"' and s.state.code='"+ItemState.NORMAL+"'";
				if(!"".equals(productcode)){
					hql+=" and s.material.code like '%"+productcode+"%'";
				}
				if(!"".equals(itemtype_id)){
					hql+=" and s.itemType.id='"+itemtype_id+"'";
				}
				if(!"".equals(invoice_code)){
					hql+=" and s.invoice.code like '%"+invoice_code+"%'";
				}
			}
			this.getContext().setSessionAttribute("storage_search_hql", hql);
		}
		return true;
	}
	public boolean onReset(){
		form.clearErrors();
		form.clearValues();
		return true;
	}
	
	
	public boolean onPrint(){
		if(form.isValid()){
			String warehouse_id=warehouseSelect.getValue();
			String owner_id=ownerSelect.getValue();
			String fName="";
			try {
				fName = generator(owner_id,warehouse_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setRedirect("../Output/" + fName+".xls");
		//处理打印
		}
		return true;
	}
	
	
	private String generator(String owner_id,String warehouse_id) throws Exception{
		List<StorageItem> result=sis.findStorageItemByWarehouseAndClient(owner_id, warehouse_id);
		InttClientDetailWS idws=ozs.findOwnerById(Integer.parseInt(owner_id));
		DocumentImpl doc = (DocumentImpl)DocumentImpl.getInstance();
		doc.setName("storageList");
		Template tem = new TemplateImpl("storageListTemp.xls");
		Exporter export = new ExporterImpl();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		HashMap map = new HashMap();
		map.put("clientName", idws.getCnName());
		map.put("date", ts);
		export.generate(tem, result, map, doc);
		return doc.getFileName()+doc.getName();
	}
	
	
//	public boolean onPrintCheck(){
//		if(form.isValid()){
//			String warehouse_id=warehouseSelect.getValue();
//			String owner_id=ownerSelect.getValue();
//			String fName="";
//			String str=invoice.getValue();
//			if(str.length()>15){
//				oas.queryStr(str);
//			}
//			try {
//				fName = generatorCheck(owner_id,warehouse_id);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			this.setRedirect("../Output/" + fName+".xls");
//		//处理打印
//		}
//		return true;
//	}
	
	private String generatorCheck(String owner_id,String warehouse_id) throws Exception{
		List<StorageItem> result=sis.findStorageItemByWarehouseAndClient(owner_id, warehouse_id);
		InttClientDetailWS idws=ozs.findOwnerById(Integer.parseInt(owner_id));
		DocumentImpl doc = (DocumentImpl)DocumentImpl.getInstance();
		doc.setName("storageListForCheck");
		Template tem = new TemplateImpl("storageListForCheckTemp.xls");
		Exporter export = new ExporterImpl();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		HashMap map = new HashMap();
		export.generate(tem, result, map, doc);
		return doc.getFileName()+doc.getName();
	}
	
	
	public boolean onExchange(){
		String id=exchange_link.getValue();
		this.getContext().setSessionAttribute("storage_id", id);
		this.setRedirect("storageExchange.htm?id="+id);
		return true;
	}
	
}