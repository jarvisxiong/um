package com.intelitune.nwms.exchange;

import java.util.ArrayList;
import java.util.List;

import net.sf.click.Context;
import net.sf.click.control.Column;
import net.sf.click.control.Decorator;
import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.DoubleField;

import com.intelitune.control.TableEx;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Bin;
import com.intelitune.nwms.model.OwnerZone;
import com.intelitune.nwms.model.StorageItem;
import com.intelitune.nwms.model.Zone;
import com.intelitune.nwms.service.BinService;
import com.intelitune.nwms.service.BinServiceImp;
import com.intelitune.nwms.service.OwnerZoneService;
import com.intelitune.nwms.service.OwnerZoneServiceImp;
import com.intelitune.nwms.service.StorageItemService;
import com.intelitune.nwms.service.StorageItemServiceImp;

public class StorageExchange extends com.intelitune.nwms.common.BorderPage {
	
	Menu me = new Menu();
	
	public String menuInclude = me.getExchange();
		
	public String title=this.getMessage("exchange_edit");
	public String error="";
	
	public String sid;
	
	public StorageItemService sis=StorageItemServiceImp.getInstance();
	public BinService bs=BinServiceImp.getInstance();
	public OwnerZoneService ozs=OwnerZoneServiceImp.getInstance();
	public MoveService ms=new MoveService();
	
	public HiddenField hf=new HiddenField("id",String.class);
	public DoubleField num = new DoubleField("num",this.getMessage("num"));
	public TextField bin_code= new TextField("bin_code",this.getMessage("bin_code"));
	
	public Form form=new Form();
	
	public TableEx table=new TableEx();
	
	public StorageExchange(){
		if(this.getContext().getSessionAttribute("exchangeError")!=null){
			error=this.getContext().getSessionAttribute("exchangeError").toString();
			this.getContext().removeSessionAttribute("exchangeError");
		}
		
		
		
		if(this.getContext().getRequestParameter("id")!=null){
			sid=this.getContext().getRequestParameter("id");
		}else{
			sid=this.getContext().getSessionAttribute("storage_id").toString();
		}
		
		
		table.setWidth("100%");
		
		Column column_productcode=new Column("productcode",this.getMessage("product_code"));
		column_productcode.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				StorageItem  si = (StorageItem) object;
				return si.getMaterial().getCode();
			}
		 });
		table.addColumn(column_productcode);
		
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
		form.add(hf);
		form.add(num);
		form.add(bin_code);
		form.add(new Submit(this.getMessage("submit"),this,"onSubmit"));
	}
	
	public void onRender(){
		StorageItem si=sis.findStorageItemById(sid);
		List<StorageItem> list=new ArrayList<StorageItem>();
		list.add(si);
		table.setRowList(list);
	}
	
	public boolean onSubmit(){
		StorageItem si=sis.findStorageItemById(sid);
		String bincode=bin_code.getValue();
		float number=num.getFloat();
		Bin bin=bs.findBinByCode(si.getWarehouse().getId(), bincode.toUpperCase());
		//判断库位是否存在
		if(bin==null){
			this.getContext().setSessionAttribute("exchangeError", this.getMessage("binerror"));
			this.setRedirect("storageExchange.htm?id="+sid);
			return true;
		}else{
			//判断库位是否是该货主
			List<Zone> zones=bin.getZones();
			for(int i=0;i<zones.size();i++){
				OwnerZone zone=ozs.findOwnerZoneById(zones.get(i).getId());
				if(zone!=null){
					if(zone.getInttClientDetailWSId().trim().equals(si.getMaterial().getInttClientDetailWSId().trim())){	
						break;
					}
				}
				
				if(i==zones.size()-1){
					this.getContext().setSessionAttribute("exchangeError", this.getMessage("binerror"));
					this.setRedirect("storageExchange.htm?id="+sid);
					return true;
				}
			}
		}
		if(si.getQty()<number){
			this.getContext().setSessionAttribute("exchangeError", this.getMessage("num_big"));
			this.setRedirect("storageExchange.htm?id="+sid);
			return true;
		}
		if(number<=0){
			this.getContext().setSessionAttribute("exchangeError", this.getMessage("num_big"));
			this.setRedirect("storageExchange.htm?id="+sid);
			return true;
		}
		ms.MoveItem(si, number, bin);
		this.setRedirect("exchangeSearch.htm");
		return true;
	}

}