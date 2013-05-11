package com.intelitune.nwms.item;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

import net.sf.click.control.Form;
import net.sf.click.control.Label;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.control.TextArea;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.AutoCompleteTextField;
import net.sf.click.extras.control.DoubleField;

import org.apache.commons.lang.StringUtils;

import com.intelitune.ccos.client.WMSServicePortType;
import com.intelitune.ccos.client.WMSServicePortTypeProxy;
import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Invoice;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.ItemType;
import com.intelitune.nwms.model.Material;
import com.intelitune.nwms.model.MaterialState;
import com.intelitune.nwms.model.OrderItem;
import com.intelitune.nwms.model.OutboundOrderItem;
import com.intelitune.nwms.model.StorageItem;
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
import com.intelitune.nwms.service.OutboundOrderItemService;
import com.intelitune.nwms.service.OutboundOrderItemServiceImp;
import com.intelitune.nwms.service.StorageItemService;
import com.intelitune.nwms.service.StorageItemServiceImp;
import com.intelitune.nwms.service.UnitPackageService;
import com.intelitune.nwms.service.UnitPackageServiceImpl;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;

public class AddOutboundOrderItem extends BorderPage
{
	public String adderror="";
	Menu me = new Menu();
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
	public Submit cancel=new Submit("cancel",getMessage("cancel"),this,"onCancel");
	
	public InvoiceService invoiceService= InvoiceServiceImp.getInstance();
	public MaterialService ms= MaterialServiceImpl.getInstance();
	public UnitPackageService ups= UnitPackageServiceImpl.getInstance();
	public OutboundOrderItemService oois=OutboundOrderItemServiceImp.getInstance();
	public OrderItemService ois= OrderItemServiceImp.getInstance();
	public OrderItemStateService oiss= OrderItemStateServiceImp.getInstance();
	public ItemTypeService its=ItemTypeServiceImpl.getInstance();
	public WMSServicePortType wpt=new WMSServicePortTypeProxy();
	public WMSServiceInf wmsService=WMSServiceImp.getInstance();
	public WarehouseService warehouseService= WarehouseServiceImp.getInstance();
	public InvoiceService is= InvoiceServiceImp.getInstance();
	public String invalid=null;
	public Select invoice=new Select("invoice",getMessage("invoice"));
	public Select itemType=new Select("itemType",getMessage("itemtype"));
	
	public AddOutboundOrderItem() throws NumberFormatException, RemoteException
	{
		Submit save=new Submit("save",getMessage("save"),this,"onSave" );
		materialCode= new AutoCompleteTextField("material", getMessage("material"), true)
		{
			public List getAutoCompleteList(String s)
			{
				List li1= (List)ms.findByHql(" from Material m where inttClientDetailWSId="+clientId +" and m.code like '" + s + "%" + "' and m.state.code='"+MaterialState.NORMAL+"'");
				for (int i = 0; i < li1.size(); i++) {
	            li1.set(i, ((Material)li1.get(i)).getCode());
	        }
				return li1;
			}
		};
		materialAlias.setDisabled(true);
//		materialAlias.setId("materialAlias");
		materialAlias.setLabel("别名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id='form_materialAlias'></span>");
		materialCode.setAttribute("onblur", "changePackage(this);");
		form.add(materialCode);
		form.add(materialAlias);
		form.add(unitPackage);
		form.add(qty);
		form.add(invoice);
		form.add(itemType);
		form.add(remark);
		form.add(save);
		form.add(cancel);
		
		List<Invoice> li=invoiceService.query("from Invoice i where i.orderId="+orderId+" and i.invoiveState.code='"+0+"'");
		invoice.add(Option.EMPTY_OPTION);
		invoice.addAll(li, "id", "code");
		List<ItemType> list=its.findItemTypeList();
		itemType.add(Option.EMPTY_OPTION);
		itemType.addAll(list, "id", "alias");
		
//		unitPackage.add(new Option("0",""));
//		List<UnitPackage> li= ups.findAll();
//		for(UnitPackage _unitPackage : li)
//		{
//			unitPackage.add(new Option(_unitPackage.getId(),_unitPackage.getAlias()));
//		}
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
	
	public boolean onSave() throws Exception
	{
		if(!form.isValid())
			return false;
		List<OutboundOrderItem> list=oois.queryOutboundOrderItem("from OutboundOrderItem o where o.state.code='" + ItemState.NORMAL 
				+ "' and  o.orderId='" + orderId+"'");
		int i=0;
		for(;i<list.size();i++){
			OutboundOrderItem ioi=list.get(i);
			//如果料号相同
			if(ioi.getMaterial().getCode().trim().equalsIgnoreCase(materialCode.getValue().trim())){
				this.getContext().setSessionAttribute("adderror",this.getMessage("adderror"));
				this.setRedirect("addOutboundOrderItem.htm");
				return true;
			}
		}
		if(i==list.size()){
		
				String _material=materialCode.getValue();
				String _unitPackage=unitPackage.getValue();
				 float _qty=qty.getFloat();
				 float totalQtyInOrderItem=0;
				 float totalQtyInStorageItem=0;
				int wmsOrderId=(Integer)this.getContext().getSessionAttribute("wmsOrderId");
				WmsOrder wmsOrder=wmsService.findWmsOrderById(wmsOrderId);
				List<WmsOrder> li= wmsService.queryStr("from WmsOrder w where w.wmsOrderStatus in (4) and w.warehouseId='"+wmsOrder.getWarehouseId() +"' and w.clientId="+clientId);
			   String IdLi="";
		 
			   for(WmsOrder _wmsOrder: li) 
				{
					IdLi+= "'"+_wmsOrder.getId()+"',"; 
				}
				IdLi = IdLi.substring(0, IdLi.length() - 1);
				
				
		 
				String str1="from OutboundOrderItem o where  o.state.code='"
					+ItemState.NORMAL.toString()
					 +"' and  o.material.code='"+_material
					 +"' and o.orderId in (" + IdLi + ")";
			   List<OrderItem> li1	=ois.queryObj(str1);
			   for(OrderItem orderItem: li1)
			   {
			   	totalQtyInOrderItem+=orderItem.getQty();
			   }
				
				
			   StorageItemService  sis=StorageItemServiceImp.getInstance();
				
				 String str2="from StorageItem s where s.state.code='"
				 +ItemState.NORMAL.toString()	 
				 +"' and s.material.code='"+_material
				 +"' and s.warehouse.id='"+wmsOrder.getWarehouseId()+"'";
				 List<StorageItem> li2= sis.findByHql(str2);
				 for(StorageItem storageItem: li2)
				 {
					 totalQtyInStorageItem+=storageItem.getQty();
				 }
		 
				 if(totalQtyInStorageItem-totalQtyInOrderItem<Double.valueOf(qty.getValue()))
				 {
					 float gap=totalQtyInStorageItem-totalQtyInOrderItem;
					 invalid="库存不够"+", 库存:"+gap+"";
					 return false;
				 }
			
				OutboundOrderItem orderItem=new OutboundOrderItem();
		//		orderItem.material.code
				orderItem.setOrderId(orderId);
				orderItem.setCreationTime(new Timestamp(System.currentTimeMillis()));
				orderItem.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
				orderItem.setState(oiss.findByCode(ItemState.NORMAL.toString()));
				orderItem.setJobId(jobId.trim());
				orderItem.setWarehouse(warehouseService.findWarehouseById(wmsOrder.getWarehouseId()));
				
				orderItem.setMaterial(ms.findMaterialByCode(materialCode.getValue(),String.valueOf(clientId)));
				orderItem.setUnitPackage(ups.getUnitPackage(unitPackage.getValue()));
				orderItem.setQty(qty.getFloat());
				orderItem.setRemark(remark.getValue());
				orderItem.setInvoice(is.findById(invoice.getValue()));
				orderItem.setItemType(its.findItemTypeById(itemType.getValue()));
		//		orderItem.setInvoice(invoiceService.findById(invoice.getValue()));
				ois.saveOrderItem(orderItem);
			}
		this.setRedirect("normalOrderInfo.htm");
		return true;
	}
	
	public boolean onCancel()
	{
		this.setRedirect(NormalOrderInfo.class);
		return true;
	}
}
