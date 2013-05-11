package com.intelitune.nwms.inbound;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.List;

import net.sf.click.Context;
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
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.common.Const;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.service.CheckingItemService;
import com.intelitune.nwms.service.CheckingItemServiceImpl;
import com.intelitune.nwms.service.InboundOrderItemService;
import com.intelitune.nwms.service.InboundOrderItemServiceImp;
import com.intelitune.nwms.service.OrderItemStateService;
import com.intelitune.nwms.service.OrderItemStateServiceImp;
import com.intelitune.nwms.service.OwnerZoneService;
import com.intelitune.nwms.service.OwnerZoneServiceImp;
/**
 * @author Louis
 *搜索蒋库订单
 */
public class SearchInboundOrder extends BorderPage {

	public Select client = new Select("client",this.getMessage("client"));
	public Form form = new Form("form");
	public TableEx table = new TableEx("table"); 
	public WMSServiceInf ws =  WMSServiceImp.getInstance();
	public OwnerZoneService ozs =  OwnerZoneServiceImp.getInstance();
	public InboundOrderItemService iois =  InboundOrderItemServiceImp.getInstance();
	public CheckingItemService cis =  CheckingItemServiceImpl.getInstance();
	public OrderItemStateService oiss =  OrderItemStateServiceImp.getInstance();
//	public String query;
	public String title = this.getMessage("searchInboundOrder");
	public String position = this.getMessage("SEARCHINBOUNDORDER");
	public ActionLink view = new ActionLink("view",this.getMessage("view"),this,"onView");
//	public ActionLink print = new ActionLink("print",this.getMessage("print"),this,"onPrint");
	public ActionLink check = new ActionLink("check",this.getMessage("check"),this,"onCheck");
	public ActionLink storage = new ActionLink("storage",this.getMessage("storage"),this,"onStorage");
	public ActionLink auto_storage = new ActionLink("auto_storage",this.getMessage("auto_storage"),this,"onAutoStorage");
	public Select inboundStatus = new Select("inboundStatus",this.getMessage("inboundStatus")); 
//	public String sql;
	public String error2;
	public Menu menu = new Menu();
	public String menuInclude = menu.getInbound();
	public TextField jobId = new TextField("jobId",this.getMessage("jobId"));
	public SearchInboundOrder(){
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		table.setPageSize(10);
		Submit submit = new Submit("submit",this.getMessage("search"),this,"onSubmit");
		Column column = null;
		column = new Column("jobId",this.getMessage("jobId"));
		table.addColumn(column);
		column = new Column("crn","客户业务编号");
		table.addColumn(column);
		column = new Column("clientName",this.getMessage("clientName"));
		table.addColumn(column);
		column = new Column("wmsOrderStatus.description",this.getMessage("state"));
		table.addColumn(column);
		column = new Column("isEmergency",this.getMessage("isEmergency"));
		column.setDecorator(new Decorator(){
			public String render(Object object, Context context) {
				WmsOrder wm = (WmsOrder)object;
				if(wm.getIsEmergency()==Const.IS_EMERGENCY){
					return getMessage("yes");
				}else{
					return getMessage("no");
				}
			}		
		});
		table.addColumn(column);
		
		Column column_creationTime=new Column("creationTime",this.getMessage("creationTime"));
		column_creationTime.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				WmsOrder wo = (WmsOrder) object;
				return sdf.format(wo.getCreationTime()).toString();
			}
		 });
		table.addColumn(column_creationTime);
		
		column = new Column("creator.userName",this.getMessage("userName"));
		table.addColumn(column);
		column = new Column("action",this.getMessage("action"));
		column.setTextAlign("center");
		ActionLink [] links = new  ActionLink[]{view,check,storage};
		column.setDecorator(new LinkDecorator(table, links, "id"));
		table.addColumn(column);
		form.add(client);
		form.add(jobId);
		form.add(inboundStatus);
		inboundStatus.add(Option.EMPTY_OPTION);
		inboundStatus.add(new Option("1","新建入库"));
		inboundStatus.add(new Option("2","已验货"));
		inboundStatus.add(new Option("3","已入库"));
		form.add(submit);
		List<InttClientDetailWS> list = ozs.findOwnerList();
		client.add(Option.EMPTY_OPTION);
		for(InttClientDetailWS inttClientDetailWS:list){
			client.add(new Option(inttClientDetailWS.getId(),inttClientDetailWS.getCnName()));
		}
//		print.setImageSrc("../image/print_16px.gif");
//		view.setImageSrc("../image/view-16px.gif");
//		check.setImageSrc("../image/edit.gif");
//		storage.setImageSrc("../image/shangjia_16px.gif");
	}
	
	public void onRender(){
		if(getContext().getSessionAttribute("query")!=null){
			String query = (String) getContext().getSessionAttribute("query");
			try {
				table.setRowList(ws.queryStr(query));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		String jobId = (String)this.getContext().getSessionAttribute("error2");
		if(jobId!=null){	
			error2 = this.getMessage("error2",jobId);
		}
		jobId = (String)this.getContext().getSessionAttribute("error6");
		if(jobId!=null){	
			error2 = this.getMessage("error6",jobId);
		}
	}
	
	public boolean onSubmit() throws IOException{
		String clientValue = client.getValue();
		String query = " from WmsOrder i where i.wmsOrderType='"+Const.TYPE_INBOUND+"'";;
		if(!clientValue.equals("")){
			query += " and i.clientId='" + clientValue+"'";
		}
		if(!jobId.getValue().equals("")){
			query += " and i.jobId like '%"+ jobId.getValue() +"%'";
		}
		if(!inboundStatus.getValue().equals("")){
			query += " and i.wmsOrderStatus.id=" + inboundStatus.getValue();
		}
		query += " and i.orderStatus <> 4";
		query +=" order by i.id desc";
//		String hql = ClickUtils.encode(query);
//		table.getControlLink().setParameter("query", hql);
		getContext().setSessionAttribute("query",query);
		return true;
	}
	/**
	 * @return
	 * 打开新窗口，是该Order下所有的进库明细
	 */
	public boolean onView(){
		String id = view.getValue();
		this.getContext().setSessionAttribute("view_orderId", id);
		this.setRedirect("inboundOrderItemDetail.htm?orderId="+id);
		
		return true;
	}
	
//	public boolean onPrint() throws Exception{
//		String id = print.getValue();
//		WmsOrder wo = ws.findWmsOrderById(Integer.parseInt(id));
//		if(wo.getWmsOrderStatus().getId()!=Const.ORDER_TYPE_INBOUND_INIT){
//			return false;
//		}
//		List<CheckingItem> li = cis.findByJobId(jobId);
//		List<InboundOrderItem> list = iois.findByOrderId(id);
//		OrderItem oi;
//		CheckingItem ci;
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		String fName = generator(list.get(0).getJobId(),list);
//		if(li.size()==0){
//			for(int i=0;i<list.size();i++){
//				oi = list.get(i);
//				ci = new CheckingItem();
//				ci.setMaterial(oi.getMaterial());
//				ci.setCreationTime(new Timestamp(System.currentTimeMillis()));
//				ci.setQty(oi.getQty());
//				ci.setState(oiss.findByCode(String.valueOf(ItemState.WEIYANZHENG)));
//				ci.setUnitPackage(oi.getUnitPackage());
//				Set<Item> set =  ci.getLastItems();
//				set.add(oi);
//				cis.saveCheckItem(ci);
//				Set<Item> set1 = oi.getNextItems();
//				set1.add(ci);
//				ois.updateOrderItem(oi);
//			}
//		}
//		this.setRedirect("../Output/" + fName+".xls");
//		return true;
//	}
//	private String generator(String jobId,List<InboundOrderItem> list) throws Exception{
//		DocumentImpl doc = (DocumentImpl)DocumentImpl.getInstance();
//		doc.setName("inboundList");
//		Template tem = new TemplateImpl("examineGoodListTemp.xls");
//		Exporter export = new ExporterImpl();
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		WmsOrder wo = ws.findByJobId(jobId);
//		HashMap map = new HashMap();
//		map.put("date", format.format(ts));
//		map.put("jobId", jobId);
//		map.put("client", wo.getClientName());
//		export.generate(tem, list, map, doc);
//		return doc.getFileName()+doc.getName();
//	}
//	
	/**
	 * 转入验证页面
	 * @throws RemoteException 
	 * @throws NumberFormatException 
	 */
	public boolean onCheck() throws NumberFormatException, RemoteException{
		String id = check.getValue();
		WmsOrder wo = ws.findWmsOrderById(Integer.parseInt(id));
		if(wo.getWmsOrderStatus().getId()!=Const.ORDER_TYPE_INBOUND_INIT){
			this.getContext().setFlashAttribute("error6", wo.getJobId());
			return true;
		}
		this.setRedirect("upLoadCheckItems.htm?orderId=" + id);
		return true;
	}
	
	/**
	 * @return
	 * @throws NumberFormatException
	 * @throws RemoteException
	 * 转入上架页面，注意只有已经查验过的才能上架
	 */
	public boolean onStorage() throws NumberFormatException, RemoteException{
		String id = storage.getValue();
		
		WmsOrder wo = ws.findWmsOrderById(Integer.parseInt(id));
		if(wo.getWmsOrderStatus().getId()!=Const.ORDER_TYPE_INBOUND_CHECKED){
			this.getContext().setFlashAttribute("error2", wo.getJobId());
			return true;
		}
		this.setRedirect("doStorage.htm?orderId=" + id);
		return true;
	}
	
	public boolean onAutoStorage() throws NumberFormatException,RemoteException{
		String id = auto_storage.getValue();
		this.setRedirect("doAutoStorage.htm?orderId"+id);
		return true;
	}
}
