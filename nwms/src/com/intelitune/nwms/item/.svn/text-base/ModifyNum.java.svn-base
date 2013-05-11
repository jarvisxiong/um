package com.intelitune.nwms.item;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Label;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.DoubleField;

import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Invoice;
import com.intelitune.nwms.model.ItemType;
import com.intelitune.nwms.model.OrderItem;
import com.intelitune.nwms.service.InvoiceService;
import com.intelitune.nwms.service.InvoiceServiceImp;
import com.intelitune.nwms.service.ItemTypeService;
import com.intelitune.nwms.service.ItemTypeServiceImpl;
import com.intelitune.nwms.service.OrderItemService;
import com.intelitune.nwms.service.OrderItemServiceImp;
import com.intelitune.nwms.warehouse.FloorList;

public class ModifyNum extends com.intelitune.nwms.common.BorderPage {
	
	
	Menu me = new Menu();
	public String orderItemId;
	
	public String menuInclude = me.getOrderitem();
	
	public String title=this.getMessage("modify_num");
	
	public Form form=new Form();
	
	public TextField productcode = new TextField("materialcode",this.getMessage("product_code"));
	public TextField productname = new TextField("productname",this.getMessage("product_name"));
	public DoubleField qty=new DoubleField("qty",getMessage("qty"),true);
	public HiddenField hf=new HiddenField("id",String.class);
	public Select invoice=new Select("invoice",getMessage("invoice"));
	public Select itemType=new Select("itemType",getMessage("itemtype"));
	
	public InvoiceService invoiceService= InvoiceServiceImp.getInstance();
	public ItemTypeService its=ItemTypeServiceImpl.getInstance();
	public OrderItemService ois=OrderItemServiceImp.getInstance();
	private OrderItem oi = null;
	
	public Label gross_price = new Label("123","123");
	public Label gross_weight = new Label("456","456");
	public DoubleField unit_weight = new DoubleField("unit_weight","单位重量");
	public DoubleField unit_price = new DoubleField("unit_price","单价");
	public Select currency = new Select("currency","币种");
	
	public ModifyNum(){
		currency.add(Option.EMPTY_OPTION);
		currency.add(new Option("RMB","RMB"));
		currency.add(new Option("JPY","JPY"));
		currency.add(new Option("USD","USD"));
		currency.add(new Option("EUR","EUR"));
		orderItemId=this.getContext().getRequestParameter("id");
		oi= ois.findById(orderItemId);
		hf.setValue(orderItemId);
		form.add(productcode);
		form.add(productname);
		form.add(hf);
		form.add(qty);
		form.add(invoice);
		form.add(itemType);
		form.add(unit_price);
		unit_price.setAttribute("onblur", "showPrice();");
		form.add(currency);
		gross_price.setLabel("总价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id='gross_price'></span>");
		form.add(gross_price);
		form.add(unit_weight);
		unit_weight.setAttribute("onblur", "showWeight();");
		gross_weight.setLabel("总重&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id='gross_weight'></span>");
		form.add(gross_weight);
		form.add(new Submit(this.getMessage("modify"),this,"onModify"));
		form.add(new Submit(this.getMessage("cancel"),this,"onCancel"));
		List<Invoice> li=invoiceService.query("from Invoice i where i.orderId="+ oi.getOrderId() +" and i.invoiveState.code='"+0+"'");
		invoice.add(Option.EMPTY_OPTION);
		invoice.addAll(li, "id", "code");
		List<ItemType> list=its.findItemTypeList();
		itemType.add(Option.EMPTY_OPTION);
		itemType.addAll(list, "id", "alias");
	}
	
	public void onRender(){
		productcode.setDisabled(true);
		productname.setDisabled(true);
		OrderItem oi=ois.findById(orderItemId);
		productcode.setValue(oi.getMaterial().getCode());
		productname.setValue(oi.getMaterial().getAlias());
		if(oi.getCurrency()!=null){
			currency.setValue(oi.getCurrency());
		}
		if(oi.getUnit_price()!=null){
			unit_price.setValue(String.valueOf(oi.getUnit_price()));
		}
		if(oi.getUnit_weight()!=null){
			unit_weight.setValue(String.valueOf(oi.getUnit_weight()));
		}
		if(oi.getUnit_price()!=null){
			gross_price.setLabel("总价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id='gross_price'>"+ oi.getGross_price()+"</span>");
		}
		if(oi.getUnit_weight()!=null){
			gross_weight.setLabel("总重&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id='gross_weight'>" + oi.getGross_weight() +"</span>");
		}
		qty.setValue(oi.getQty().toString());
		if(oi.getInvoice()!=null){
			invoice.setValue(oi.getInvoice().getId());
		}
		if(oi.getItemType()!=null){
			itemType.setValue(oi.getItemType().getId());
		}
	}
	
	
	public boolean onModify(){
		if(form.isValid()){
			float qty1=qty.getFloat();
			String itemid=hf.getValue();
//			ois.modifyNum(itemid, qty1);
			oi.setInvoice(invoiceService.findById(invoice.getValue()));
			oi.setItemType(its.findItemTypeById(itemType.getValue()));
			oi.setQty(qty1);
			if(!unit_price.getValue().equals("")){
				oi.setUnit_price(Float.parseFloat(unit_price.getValue()));
				oi.setF_unit_price(Float.parseFloat(unit_price.getValue()));
			}
			if(!unit_weight.getValue().equals("")){
				oi.setUnit_weight(Float.parseFloat(unit_weight.getValue()));
				oi.setF_unit_weight(Float.parseFloat(unit_weight.getValue()));
			}			
			
			DecimalFormat df = new DecimalFormat("0.00");
			if(!unit_price.getValue().equals("")){
				Float allPrice = Float.parseFloat(unit_price.getValue())*qty.getFloat();						
				oi.setGross_price(Float.parseFloat(df.format(allPrice)));
				oi.setF_gross_price(Float.parseFloat(df.format(allPrice)));
			}
			if(!unit_weight.getValue().equals("")){
				oi.setGross_weight(Float.parseFloat(df.format(Float.parseFloat(unit_weight.getValue())*qty.getFloat())));
				oi.setF_gross_weight(Float.parseFloat(df.format(Float.parseFloat(unit_weight.getValue())*qty.getFloat())));
			}				
			
//			NumberFormat nf = NumberFormat.getInstance();
//			nf.setMaximumFractionDigits(2);
//			if(!unit_price.getValue().equals("")){
//				oi.setGross_price(Float.parseFloat(nf.format(Float.parseFloat(unit_price.getValue())*qty.getFloat())));
//			}
//			if(!unit_weight.getValue().equals("")){
//				oi.setGross_weight(Float.parseFloat(nf.format(Float.parseFloat(unit_weight.getValue())*qty.getFloat())));
//			}
			if(!currency.getValue().equals("")){
				oi.setCurrency(currency.getValue());
				oi.setF_currency(currency.getValue());
			}
//			oi.setGross_price(qty1*Float.parseFloat(unit_price.getValue()));
//			oi.setGross_weight(qty1*Float.parseFloat(unit_weight.getValue()));
			ois.updateOrderItem(oi);
			this.setRedirect(NormalOrderInfo.class);
		}
		return true;
	}
	
	public boolean onCancel(){
		this.setRedirect(NormalOrderInfo.class);
		return true;
	}

}