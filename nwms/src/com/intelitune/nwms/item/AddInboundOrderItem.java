package com.intelitune.nwms.item;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;

import net.sf.click.control.Form;
import net.sf.click.control.Label;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.control.TextArea;
import net.sf.click.extras.control.AutoCompleteTextField;
import net.sf.click.extras.control.DoubleField;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;

import com.intelitune.ccos.client.WMSServicePortType;
import com.intelitune.ccos.client.WMSServicePortTypeProxy;
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.InboundOrderItem;
import com.intelitune.nwms.model.Invoice;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.ItemType;
import com.intelitune.nwms.model.Material;
import com.intelitune.nwms.service.InboundOrderItemService;
import com.intelitune.nwms.service.InboundOrderItemServiceImp;
import com.intelitune.nwms.service.InvoiceService;
import com.intelitune.nwms.service.InvoiceServiceImp;
import com.intelitune.nwms.service.ItemTypeService;
import com.intelitune.nwms.service.ItemTypeServiceImpl;
import com.intelitune.nwms.service.MaterialService;
import com.intelitune.nwms.service.MaterialServiceImpl;
import com.intelitune.nwms.service.OrderItemService;
import com.intelitune.nwms.service.OrderItemServiceImp;
import com.intelitune.nwms.service.OrderItemStateService;
import com.intelitune.nwms.service.OrderItemStateServiceImp;
import com.intelitune.nwms.service.UnitPackageService;
import com.intelitune.nwms.service.UnitPackageServiceImpl;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;

public class AddInboundOrderItem extends BorderPage
{
	Menu me = new Menu();
	
	public String adderror="";
	public String menuInclude = me.getOrderitem();
	String orderId=(String)this.getContext().getSessionAttribute("orderId");
	Integer clientId=(Integer)this.getContext().getSessionAttribute("clientId");
	String jobId=(String)this.getContext().getSessionAttribute("jobId");
	public AutoCompleteTextField  materialCode;
	public Select unitPackage=new Select("unitPackage",getMessage("unitPackage"));
	public DoubleField qty=new DoubleField("qty",getMessage("qty"),true);
	public Form form=new Form("form");
	public TextArea remark=new TextArea("remark",getMessage("remark"));
	public Label materialAlias=new Label("materialAlias","别名");

	public Label gross_price = new Label("123","123");
	public Label gross_weight = new Label("456","456");
	public Submit cancel=new Submit("cancel",getMessage("cancel"),this,"onCancel");
	
	
	public InvoiceService invoiceService= InvoiceServiceImp.getInstance();
	public MaterialService ms= MaterialServiceImpl.getInstance();
	public UnitPackageService ups= UnitPackageServiceImpl.getInstance();
	public OrderItemService ois= OrderItemServiceImp.getInstance();
	public OrderItemStateService oiss= OrderItemStateServiceImp.getInstance();
	public WMSServicePortType wpt= new WMSServicePortTypeProxy();
	public WarehouseService warehouseService= WarehouseServiceImp.getInstance();
	public InboundOrderItemService iois=InboundOrderItemServiceImp.getInstance();
	public ItemTypeService its=ItemTypeServiceImpl.getInstance();
	public String invalid=null;
	public Select invoice=new Select("invoice",getMessage("invoice"));
	public Select itemType=new Select("itemType",getMessage("itemtype"));
	public DoubleField unit_weight = new DoubleField("unit_weight","单位重量");
	public DoubleField unit_price = new DoubleField("unit_price","单价");
	public Select currency = new Select("currency","币种");
	
	public AddInboundOrderItem() throws NumberFormatException, RemoteException
	{
		Submit save=new Submit("save",getMessage("save"),this,"onSave" );
		materialCode= new AutoCompleteTextField("material", getMessage("material"), true)
		{
			public List getAutoCompleteList(String s)
			{
				List li1= (List)ms.findByHql(" from Material m where inttClientDetailWSId="+clientId +" and m.code like '" + s + "%" + "'");
				for (int i = 0; i < li1.size(); i++) {
					li1.set(i, ((Material)li1.get(i)).getCode());
	        }
				return li1;
			}
		};
		
		currency.add(Option.EMPTY_OPTION);
		currency.add(new Option("RMB","RMB"));
		currency.add(new Option("JPY","JPY"));
		currency.add(new Option("USD","USD"));
		currency.add(new Option("EUR","EUR"));
//		materialAlias.setDisabled(true);
//		materialAlias.setId("materialAlias");
		materialAlias.setLabel("别名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id='form_materialAlias'></span>");
		materialCode.setAttribute("onblur", "changePackage(this);");
		form.add(materialCode);
		form.add(materialAlias);
		form.add(unitPackage);
		form.add(qty);
		form.add(invoice);
		form.add(itemType);
		form.add(unit_price);
		form.add(currency);
		unit_price.setAttribute("onblur", "showPrice();");
		gross_price.setLabel("总价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id='gross_price'></span>");
		form.add(gross_price);
		form.add(unit_weight);
		unit_weight.setAttribute("onblur", "showWeight();");
		gross_weight.setLabel("总重&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id='gross_weight'></span>");
		form.add(gross_weight);
		form.add(remark);
		form.add(save);
		form.add(cancel);

		List<Invoice> li=invoiceService.query("from Invoice i where i.orderId="+orderId+" and i.invoiveState.code='"+0+"'");
		invoice.add(Option.EMPTY_OPTION);
		invoice.addAll(li, "id", "code");
		List<ItemType> list=its.findItemTypeList();
		itemType.add(Option.EMPTY_OPTION);
		itemType.addAll(list, "id", "alias");
		
		

		AccessSelects(materialCode, unitPackage);
		unitPackage.add(new Option("0",""));
		
		
		if(this.getContext().getSessionAttribute("adderror")!=null){
			adderror=this.getContext().getSessionAttribute("adderror").toString();
			this.getContext().removeSessionAttribute("adderror");
		}
	}
	
	public void AccessSelects( AutoCompleteTextField materialCode, Select unitPackage)
	{
		 

		materialCode.bindRequestValue();

		if (StringUtils.isEmpty(materialCode.getValue()))
		{
			return;
		}

		// If province is selected, proceed to populate citys
		if (!materialCode.getValue().equals("0"))
		{
			makeUnitPackage (unitPackage);
		}
	}
	
	public void makeUnitPackage(Select unitPackage)
	{
//		List<UnitPackage> li= ups.findAll();
//		for (UnitPackage _unitPackage : li)
//			unitPackage.add(new Option(_unitPackage.getId(), _unitPackage.getAlias()));
		
	}
	
	public boolean onSave() throws RemoteException
	{
		if(!form.isValid())
			return false;
			List<InboundOrderItem> list=iois.findNormalByOrderId(orderId);
			int i=0;
			for(;i<list.size();i++){
				InboundOrderItem ioi=list.get(i);
				//如果料号相同
				if(ioi.getMaterial().getCode().trim().equalsIgnoreCase(materialCode.getValue().trim())){
					this.getContext().setSessionAttribute("adderror",this.getMessage("adderror"));
					this.setRedirect("addInboundOrderItem.htm");
					return true;
				}
			}
				if(i==list.size()){
					WmsOrder wmsOrder=wpt.findById(((WmsOrder)this.getContext().getSessionAttribute("wmsOrder")).getId());
					Hibernate.initialize(wmsOrder);
					InboundOrderItem orderItem=new InboundOrderItem();
					orderItem.setOrderId(orderId);
					orderItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
					orderItem.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
					orderItem.setState(oiss.findByCode(ItemState.NORMAL.toString()));
					orderItem.setJobId(jobId.trim());
			//		System.out.println(wmsOrder.getWarehouseId());
					orderItem.setWarehouse(warehouseService.findWarehouseById(wmsOrder.getWarehouseId()));
					orderItem.setMaterial(ms.findMaterialByCode(materialCode.getValue(),String.valueOf(clientId)));
					orderItem.setUnitPackage(ups.getUnitPackage(unitPackage.getValue().trim()));
					orderItem.setQty(qty.getFloat());
					orderItem.setRemark(remark.getValue());
					orderItem.setInvoice(invoiceService.findById(invoice.getValue()));
					orderItem.setItemType(its.findItemTypeById(itemType.getValue()));					
					if(!unit_price.getValue().equals("")){
						orderItem.setUnit_price(Float.parseFloat(unit_price.getValue()));
						orderItem.setF_unit_price(Float.parseFloat(unit_price.getValue()));
					}
					if(!unit_weight.getValue().equals("")){
						orderItem.setUnit_weight(Float.parseFloat(unit_weight.getValue()));
						orderItem.setF_unit_weight(Float.parseFloat(unit_weight.getValue()));
					}		
					DecimalFormat df = new DecimalFormat("0.00");
					if(!unit_price.getValue().equals("")){
						Float allPrice = Float.parseFloat(unit_price.getValue())*qty.getFloat();						
						orderItem.setGross_price(Float.parseFloat(df.format(allPrice)));
						orderItem.setF_gross_price(Float.parseFloat(df.format(allPrice)));
					}
					if(!unit_weight.getValue().equals("")){
						orderItem.setGross_weight(Float.parseFloat(df.format(Float.parseFloat(unit_weight.getValue())*qty.getFloat())));
						orderItem.setF_gross_weight(Float.parseFloat(df.format(Float.parseFloat(unit_weight.getValue())*qty.getFloat())));
					}
					if(!currency.getValue().equals("")){
						orderItem.setCurrency(currency.getValue());
						orderItem.setF_currency(currency.getValue());
					}					
					
					ois.saveOrderItem(orderItem);
					wpt.refWmsOrder(wmsOrder, orderItem.getId());
				}
			
		this.setRedirect("normalOrderInfo.htm");
		return true;
	}
	
	public boolean onCancel()
	{
		this.setRedirect("normalOrderInfo.htm");
		return true;
	}
}
