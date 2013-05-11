package com.intelitune.nwms.outbound;



import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.click.Context;
import net.sf.click.control.AbstractLink;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Decorator;
import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Submit;
import net.sf.click.extras.control.LinkDecorator;
import net.sf.click.extras.control.TableInlinePaginator;

import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.control.TableEx;
import com.intelitune.export.Exporter;
import com.intelitune.export.Template;
import com.intelitune.export.jxls.DocumentImpl;
import com.intelitune.export.jxls.ExporterImpl;
import com.intelitune.export.jxls.TemplateImpl;
import com.intelitune.nwms.common.CreatePickingItem;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.OrderItem;
import com.intelitune.nwms.model.OutboundAssigned;
import com.intelitune.nwms.model.OutboundOrderItem;
import com.intelitune.nwms.model.PickingItem;
import com.intelitune.nwms.service.OrderItemService;
import com.intelitune.nwms.service.OrderItemServiceImp;
import com.intelitune.nwms.service.OutboundAssignedService;
import com.intelitune.nwms.service.OutboundAssignedServiceImp;
import com.intelitune.nwms.service.PickingItemService;
import com.intelitune.nwms.service.PickingItemServiceImp;

public class OrderDetail extends com.intelitune.nwms.common.BorderPage {
	Menu me = new Menu();
	
	public String menuInclude =me.getOutbound();
	
	public String position = this.getMessage("order_position_detail");
	public String assignerror="";
	
	public String title;
	
	public String order_id;
	
	public HiddenField hf=new HiddenField("id",String.class);
	
	public String tabledetail="";//订单详细信息
	
	public TableEx table=new TableEx();
	
	public String assigned=this.getMessage("assigned");
	public String not_assigned=this.getMessage("not_assigned");
	public Form form=new Form();
	
	public WmsOrder wo=new WmsOrder();
	
	private AbstractLink[] links;
	public ActionLink assign_link = new ActionLink("assign", getMessage("assign"),
			this, "onAssign");
	
	public WMSServiceInf service=WMSServiceImp.getInstance();
	
	public OrderItemService ois=OrderItemServiceImp.getInstance();
	public CreatePickingItem cpi=new CreatePickingItem();
	public PickingItemService pis=PickingItemServiceImp.getInstance();
	public OutboundAssignedService oas=OutboundAssignedServiceImp.getInstance();
	
	public List list=new ArrayList();
	public OrderDetail(){
		String page = null;
		if((page=this.getContext().getRequestParameter("page"))!=null){
			this.getContext().setSessionAttribute("nPage", page);
		}
		if(this.getContext().getSessionAttribute("not_all_assigned")!=null){
			assignerror=this.getContext().getSessionAttribute("not_all_assigned").toString();
			this.getContext().removeSessionAttribute("not_all_assigned");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(this.getContext().getRequestParameter("id")!=null){
			order_id=this.getContext().getRequestParameter("id");
		}else{
			order_id=this.getContext().getSessionAttribute("detail_orderId").toString();
		}
//		if(order_id!=null){
//			this.getContext().getSession().setAttribute("orderdetail_id", order_id);
//		}
		hf.setValue(order_id);
		try{
			 wo=service.findWmsOrderById(Integer.parseInt(order_id));
		}catch(Exception e){
			e.printStackTrace();
		}
		tabledetail+=this.getMessage("order_jobid")+":&nbsp;&nbsp;"+wo.getJobId()+"<br>"; 
		tabledetail+=this.getMessage("order_creator")+":&nbsp;&nbsp;"+wo.getCreator().getUserName()+"<br>"; 
		tabledetail+=this.getMessage("order_creationTime")+":&nbsp;&nbsp;";
		if(wo.getCreationTime()!=null){tabledetail+=sdf.format(wo.getCreationTime());};
		tabledetail+="<br>"; 
		tabledetail+=this.getMessage("order_anticipateDate")+":&nbsp;&nbsp;";
		if(wo.getAnticipateDate()!=null){tabledetail+=sdf.format(wo.getAnticipateDate());};
		tabledetail+="<br>"; 
		tabledetail+=this.getMessage("order_achievementDate")+":&nbsp;&nbsp;";
		if(wo.getAchievementDate()!=null){tabledetail+=sdf.format(wo.getAchievementDate());};
		tabledetail+="<br>"; 
		tabledetail+=this.getMessage("order_isEmergency")+":&nbsp;&nbsp;";
		if(wo.getIsEmergency()==0){
			tabledetail+="否";
		}else if(wo.getIsEmergency()==1){
			tabledetail+="是";
		}
		tabledetail+="<br>";
		tabledetail+=this.getMessage("order_alreadyOutbound")+":&nbsp;&nbsp;";
		if(wo.getAlreadyOutbound()=="0"){
			tabledetail+="否";
		}else if(wo.getAlreadyOutbound()=="1"){
			tabledetail+="是";
		}
		tabledetail+="<br>";
		tabledetail+=this.getMessage("order_clientName")+":&nbsp;&nbsp;"+wo.getClientName()+"<br>"; 
		tabledetail+=this.getMessage("order_remark")+":&nbsp;&nbsp;";
		if(wo.getRemark()!=null){ tabledetail+=wo.getRemark();}
		tabledetail+="<br>";
		
		//item表单
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		table.setPageSize(10);
		
		
		Column column_productname= new Column("product_name", this.getMessage("item_materialname"));
		column_productname.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				OutboundOrderItem  ooi= (OutboundOrderItem) object;
				return ooi.getMaterial().getAlias();	
			}
		 });
		table.addColumn(column_productname);
		
		Column column_productcode= new Column("product_code", this.getMessage("item_materialcode"));
		column_productcode.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				OutboundOrderItem  ooi= (OutboundOrderItem) object;
				return ooi.getMaterial().getCode();	
			}
		 });
		table.addColumn(column_productcode);
		table.addColumn(new Column("sn",this.getMessage("order_sn")));
		table.addColumn(new Column("qty",this.getMessage("order_qty")));
		
		Column column_invoice= new Column("item_invoice", this.getMessage("column_invoice_code"));
		column_invoice.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				OutboundOrderItem  ooi= (OutboundOrderItem) object;
				if(ooi.getInvoice()!=null){
					return ooi.getInvoice().getCode();	
				}else{
					return null;
				}
			}
		 });
		table.addColumn(column_invoice);
		
		Column column_itemtype= new Column("itemtype", this.getMessage("itemtype"));
		column_itemtype.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				OutboundOrderItem  ooi= (OutboundOrderItem) object;
				if(ooi.getItemType()!=null){
					return ooi.getItemType().getAlias();	
				}else{
					return null;
				}
			}
		 });
		table.addColumn(column_itemtype);
		
		Column assign = new Column("action",this.getMessage("edit"));
		assign.setTextAlign("center");
		links = new AbstractLink[] {assign_link};
		assign.setDecorator(new LinkDecorator(table, links, "id"));
		table.addColumn(assign);
		
		
		Column result= new Column("result", this.getMessage("result"));
		result.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				OutboundOrderItem  ooi= (OutboundOrderItem) object;
				List<OutboundAssigned> list=oas.findAssignedByOrderIdAndProductCode(order_id, ooi.getMaterial().getCode());
				float num=0;
				for(int i=0;i<list.size();i++){
					num+=list.get(i).getAssignedQty();
				}
				if(ooi.getQty()==num){
					return assigned;
				}else{
					return not_assigned;
				}
			}
		 });
		table.addColumn(result);
		
		
		
		form.add(hf);
		if(wo.getWmsOrderStatus().getId()==4){
			Submit  submit= new Submit("assign",this.getMessage("assign_outbound"),this,"onOutboundAssign");
			form.add(submit);
			submit.setAttribute("onClick", "return window.confirm('"
					+getMessage("confirm_sure")+"?" + "');");
			Submit  submit1= new Submit("auto_locate",this.getMessage("auto_locate"),this,"onLocate");
			form.add(submit1);
			submit1.setAttribute("onClick", "return window.confirm('"
					+getMessage("confirm_sure")+"?" + "');");
			//判断是否有ITEM信息
			int m=ois.findItemNumByOrderId(order_id);
			if(m==0){
				form.setAttribute("onSubmit", "alert('"+this.getMessage("item_null")+"');return false;");
			}
		}
		if(wo.getWmsOrderStatus().getId()==5||wo.getWmsOrderStatus().getId()==6){
			form.add(new Submit("print",this.getMessage("print"),this,"onPrint"));
		}
		
		
		
		
//		if(list.size()==0){
//			form.setAttribute("onSubmit", "alert('"+this.getMessage("item_null")+"');return false;");
//		}else{
		
//		}
	}

	
	public void onRender(){
//		if(this.getContext().getSessionAttribute("test_list")==null){
//			list=ois.findOrderItemsByOrderId((this.getContext().getSessionAttribute("orderdetail_id").toString()));
//			this.getContext().getSession().setAttribute("test_list", list);
//		}else{
//			list = (List)this.getContext().getSessionAttribute("test_list");
//		}
		list=ois.findOrderItemsByOrderId(order_id);
		title=wo.getJobId()+this.getMessage("order_detail");
		table.setRowList(list);
	}
	
	public boolean onLocate(){
			List<OutboundOrderItem> list1=ois.findOrderItemsByOrderId(order_id);
			cpi.transferPickingItem(list1, Integer.parseInt(order_id));
			this.setRedirect("orderDetail.htm?id="+order_id);
			return true;
	}
	
//	public boolean onPrint() throws Exception{
//		List<OutboundOrderItem> list1=ois.findOrderItemsByOrderId(order_id);
//		int i=Integer.parseInt(order_id);
//		String fName = generator(i,list1);
//		this.setRedirect("../Output/" + fName+".xls");
//		//处理打印
//		return true;
//	}
//	
//	
//	private String generator(int orderid,List<OutboundOrderItem> list) throws Exception{
//		List<PickingItem> result=cpi.transferPickingItem(list, orderid);
//		WmsOrder wmsorder=new WmsOrder();;
//		//改变订单状态至下架中
//		service.alterToOutboundInPicking(orderid);
//		try{
//			 wmsorder=service.findWmsOrderById(orderid);
//			}catch(Exception e){
//				e.printStackTrace();
//		}
//		DocumentImpl doc = (DocumentImpl)DocumentImpl.getInstance();
//		doc.setName("outboundList");
//		Template tem = new TemplateImpl("outboundGoodListTemp.xls");
//		Exporter export = new ExporterImpl();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		HashMap map = new HashMap();
//		map.put("date", format.format(ts));
//		map.put("jobId", wmsorder.getJobId());
//		map.put("clientName", wmsorder.getClientName());
//		export.generate(tem, result, map, doc);
//		return doc.getFileName()+doc.getName();
//	}
	
	public boolean onPrint() throws Exception{
		String fName = generator(order_id);
		this.setRedirect("../Output/" + fName+".xls");
		//处理打印
		return true;
	}
	
	
	
	
	
	private String generator(String order_id) throws Exception{
		List<PickingItem> result=pis.findPickingItemByOrderId(order_id);
		WmsOrder wmsorder=new WmsOrder();;
		try{
			 wmsorder=service.findWmsOrderById(Integer.parseInt(order_id));
			}catch(Exception e){
				e.printStackTrace();
		}
		DocumentImpl doc = (DocumentImpl)DocumentImpl.getInstance();
		doc.setName("outboundList");
		Template tem = new TemplateImpl("outboundGoodListTemp.xls");
		Exporter export = new ExporterImpl();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		HashMap map = new HashMap();
		map.put("clientcode", wmsorder.getCrn());
		map.put("date", format.format(ts));
		map.put("jobId", wmsorder.getJobId());
		map.put("clientName", wmsorder.getClientName());
		if(wmsorder.getOrderStatus().getId()==5){
			export.generate(tem, result, map, doc);
		}else{
			result = pis.findPickingItemByOrderIdIngoreStatus(order_id);
			export.generate(tem, result, map, doc);
		}
		return doc.getFileName()+doc.getName();
	}
	
	public boolean onAssign(){
		String itemid=assign_link.getValue();
		this.getContext().setSessionAttribute("assign_orderId", order_id);
		this.getContext().setSessionAttribute("assign_itemId", itemid);
		this.setRedirect("itemAssign.htm?itemid="+itemid+"&id="+order_id);
		return true;
	}
	
	public boolean onOutboundAssign(){
		//判断是否全部分配
		List<OutboundOrderItem> olist=ois.findOrderItemsByOrderId(order_id);
		for(int i=0;i<olist.size();i++){
			OutboundOrderItem ooi=olist.get(i);
			List<OutboundAssigned> list=oas.findAssignedByOrderIdAndProductCode(order_id, ooi.getMaterial().getCode());
			int num=0;
			for(int j=0;j<list.size();j++){
				num+=list.get(j).getAssignedQty();
			}
			if(num==ooi.getQty()){
				continue;
			}else{
				this.getContext().setSessionAttribute("not_all_assigned", this.getMessage("not_all_assigned"));
				this.setRedirect("orderDetail.htm?id="+order_id);
				return true;
			}
		}
		try {
			pis.transferPickingItemByAssigned(order_id, wo.getJobId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setRedirect("orderDetail.htm?id="+order_id);
		return true;
	}
}