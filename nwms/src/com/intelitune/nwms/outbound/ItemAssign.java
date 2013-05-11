package com.intelitune.nwms.outbound;

import java.util.ArrayList;
import java.util.List;

import net.sf.click.control.Column;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Submit;
import net.sf.click.extras.control.DoubleField;
import net.sf.click.extras.control.FieldColumn;

import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.control.FormTableEx;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.OutboundAssigned;
import com.intelitune.nwms.model.OutboundOrderItem;
import com.intelitune.nwms.model.StorageItem;
import com.intelitune.nwms.service.OutboundAssignedService;
import com.intelitune.nwms.service.OutboundAssignedServiceImp;
import com.intelitune.nwms.service.OutboundOrderItemService;
import com.intelitune.nwms.service.OutboundOrderItemServiceImp;
import com.intelitune.nwms.service.StorageItemService;
import com.intelitune.nwms.service.StorageItemServiceImp;

public class ItemAssign extends com.intelitune.nwms.common.BorderPage {
	Menu me = new Menu();
	
	public String menuInclude =me.getOutbound();
	
	public String title;
	public String order_id;	
	public String item_id;
	public float qty=0;
	public OutboundOrderItem ooi=new OutboundOrderItem();
	
	public String qtyerror="";
	public WmsOrder wo=new WmsOrder();
	
	public TableEx table=new TableEx();
	
	public FormTableEx form=new FormTableEx();
	
	public HiddenField hf=new HiddenField("id",String.class);
	public HiddenField hf1=new HiddenField("itemid",String.class);
	public WMSServiceInf service=WMSServiceImp.getInstance();
	public OutboundOrderItemService oois=OutboundOrderItemServiceImp.getInstance();
	public StorageItemService ois=StorageItemServiceImp.getInstance();
	public OutboundAssignedService oas=OutboundAssignedServiceImp.getInstance();
	
	//michael_wang 20091214
	public String tableStr1 = "出库信息";
	public String tableStr2 = "库存分配信息";
	
	public ItemAssign(){
		if(this.getContext().getSessionAttribute("qtyerror")!=null){
			qtyerror=this.getContext().getSessionAttribute("qtyerror").toString();
			this.getContext().removeSessionAttribute("qtyerror");
		}
		if(this.getContext().getRequestParameter("itemid")!=null){
			item_id=this.getContext().getRequestParameter("itemid");
		}else{
			item_id=this.getContext().getSessionAttribute("assign_itemId").toString();
		}
		if(this.getContext().getRequestParameter("id")!=null){
			order_id=this.getContext().getRequestParameter("id");
		}else{
			order_id=this.getContext().getSessionAttribute("assign_orderId").toString();
		}
		ooi=oois.findById(item_id);
		qty=ooi.getQty();
		hf.setValue(order_id);
		try{
			 wo=service.findWmsOrderById(Integer.parseInt(order_id));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		table.setWidth("100%");
		
		table.addColumn(new Column("material.alias",this.getMessage("item_materialname")));
		table.addColumn(new Column("material.code",this.getMessage("item_materialcode")));
		table.addColumn(new Column("sn",this.getMessage("order_sn")));
		table.addColumn(new Column("qty",this.getMessage("order_qty")));
		table.addColumn(new Column("invoice.code",this.getMessage("column_invoice_code")));
		table.addColumn(new Column("itemType.alias",this.getMessage("itemtype")));
		
		form.setWidth("100%");	
		form.add(hf);
		form.add(hf1);
		
		//michael 2009 11-19
		form.addColumn(new Column("jobId","业务编号"));
		
		Column material_alias=new Column("material.alias",this.getMessage("item_materialname"));
		material_alias.setWidth("20%");
		form.addColumn(material_alias);
		
		
		
		
		form.addColumn(new Column("material.code",this.getMessage("item_materialcode")));
		form.addColumn(new Column("sn",this.getMessage("order_sn")));
		form.addColumn(new Column("qty",this.getMessage("order_qty")));
		form.addColumn(new Column("invoice.code",this.getMessage("column_invoice_code")));
		
		//michael 2009 11-19
		//Kirin_Lv 2009 12-02
		form.addColumn(new Column("invoice.so","Po"));
		//form.addColumn(new Column("invoice.po","Po"));
		
		form.addColumn(new Column("itemType.alias",this.getMessage("itemtype")));
		form.addColumn(new Column("bin.code",this.getMessage("bin_code")));
		FieldColumn fc = new FieldColumn("bkt_factqty",this.getMessage("getqty"),new DoubleField());
		form.addColumn(fc);
		form.getForm().add(new Submit("submit",this.getMessage("submit"),this,"onSubmit"));
		form.getForm().add(new Submit("cancel",this.getMessage("cancel"),this,"onCancel"));
		
	}
	public void onInit(){
		List<OutboundAssigned> alist=oas.findAssignedByOrderIdAndProductCode(order_id, ooi.getMaterial().getCode());
//		OutboundOrderItem ooi=oois.findById(item_id);
		List<StorageItem> slist=ois.findStorageItembyWarehouseAndProductAndClient(wo.getWarehouseId(), ooi.getMaterial().getCode(), wo.getClientId().toString());
		for(int i=0;i<slist.size();i++){
			StorageItem si=slist.get(i);
			for(int j=0;j<alist.size();j++){
				OutboundAssigned oa=alist.get(j);
				if(oa.getSi().getId().equals(si.getId())){
					si.setBkt_factqty(oa.getAssignedQty());
				}
			}
		}
		form.setRowList(slist);
	}

	public void onRender(){
		title=wo.getJobId()+this.getMessage("item_assign");
//		OutboundOrderItem ooi=oois.findById(item_id);
		List<OutboundOrderItem> list=new ArrayList<OutboundOrderItem>();
		list.add(ooi);
		table.setRowList(list);	
	}
	
	public boolean onSubmit(){
		String page = (String)getContext().getSessionAttribute("nPage");
		oas.delOutboundAssigned(order_id, item_id);//删除原有分配记录
		List<StorageItem> list=form.getRowList();
		float num=0;
		for(int i=0;i<list.size();i++){
			StorageItem si=list.get(i);
			if(si.getQty()<si.getBkt_factqty()){
				this.getContext().setSessionAttribute("qtyerror", this.getMessage("qtyerror"));
				this.setRedirect("itemAssign.htm?itemid="+item_id+"&id="+order_id + "&actionLink=table-controlLink&page=" + page);
				return true;
			}
			num+=si.getBkt_factqty();
		}
		if(qty==num){
			for(int i=0;i<list.size();i++){
				StorageItem si=list.get(i);
				if(si.getBkt_factqty()!=0){
					OutboundAssigned oa=new OutboundAssigned();
					oa.setOoi(ooi);
					oa.setSi(si);
					oa.setAssignedQty(si.getBkt_factqty());
					oa.setIsDelete(0);
					
					//20091214 michael_wang
					oa.setMawb(si.getMawb());
					oa.setHawb(si.getHawb());
					
					oas.addOutboundAssigned(oa);
				}
			}
			//this.setRedirect("orderDetail.htm?id="+order_id);
			this.setRedirect("orderDetail.htm?actionLink=table-controlLink&page="+page+"&id="+order_id);
		}else{
			this.getContext().setSessionAttribute("qtyerror", this.getMessage("qtyerror"));
			this.setRedirect("itemAssign.htm?itemid="+item_id+"&id="+order_id + "&actionLink=table-controlLink&page=" + page);
			return true;
		}
		return true;
	}
	
	
	public boolean onCancel(){
		String page = null;
		if((page=(String) getContext().getSessionAttribute("nPage"))!=null){
			this.setRedirect("orderDetail.htm?actionLink=table-controlLink&page="+page);
		}else{
			this.setRedirect("orderDetail.htm?id="+order_id);
		}
		return true;
	}
}