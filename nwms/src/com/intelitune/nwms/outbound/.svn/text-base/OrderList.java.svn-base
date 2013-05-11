package com.intelitune.nwms.outbound;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.service.OwnerZoneService;
import com.intelitune.nwms.service.OwnerZoneServiceImp;

public class OrderList extends  BorderPage{
	
	Menu me = new Menu();
	
	public String menuInclude = me.getOutbound();
	
	public String position = this.getMessage("order_position");
	
	public String title=this.getMessage("order_manage");
	
	public List list;
	public Select ownerSelect;
	public Select stateSelect;
	public TextField tf_jobid = new TextField("job_id",this.getMessage("job_id"));
	
	public Form form=new Form();
	
	public TableEx table=new TableEx();
	public OwnerZoneService ozs= OwnerZoneServiceImp.getInstance();
	WMSServiceInf service=WMSServiceImp.getInstance();
	
	private AbstractLink[] links;
	public ActionLink enter_link = new ActionLink("enter", getMessage("assignment"),
			this, "onEnter");
	
	public void onInit(){
		super.onInit();
		List<InttClientDetailWS> list=ozs.findOwnerList();
		ownerSelect.add(new Option(0,""));
	        for (Iterator<InttClientDetailWS> i = list.iterator(); i.hasNext();) {
	        	InttClientDetailWS icd = (InttClientDetailWS) i.next();
	        	ownerSelect.add(new Option(icd.getId(),icd.getCnName()));
	        }
	    stateSelect.add(new Option(""));
	    stateSelect.add(new Option(4,"新建出库"));
	    stateSelect.add(new Option(5,"待校验"));
	    stateSelect.add(new Option(6,"已出库"));
	}
	
	
	
	public OrderList(){
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		ownerSelect = new Select(this.getMessage("zone_customer_name"),true);
		stateSelect = new Select(this.getMessage("state"));
		form.add(ownerSelect);
		form.add(stateSelect);
		form.add(tf_jobid);
		form.add(new Submit(this.getMessage("search"),this,"onSearch"));
		form.add(new Submit(this.getMessage("reset"),this,"onReset"));
		
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		table.setPageSize(10);
		
		Column column_jobid= new Column("job_id", this.getMessage("order_jobid"));
		column_jobid.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				WmsOrder wo = (WmsOrder) object;
				 String job_id=wo.getJobId();				
		         return job_id;
			}
		 });
		table.addColumn(column_jobid);
		Column ccolumn = new Column("crn","客户业务编号");
		table.addColumn(ccolumn);

		table.addColumn(new Column("clientName",this.getMessage("order_clientName")));
		
		Column column_isEmergency=new Column("isEmergency",this.getMessage("order_isEmergency"));
		column_isEmergency.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				WmsOrder wo = (WmsOrder) object;
				if(wo.getIsEmergency()==1)
		         return "是";
				else
			       return "否";
			}
		 });
		table.addColumn(column_isEmergency);
		
		Column column_creationTime=new Column("creationTime",this.getMessage("creationTime"));
		column_creationTime.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				WmsOrder wo = (WmsOrder) object;
				return sdf.format(wo.getCreationTime()).toString();
			}
		 });
		table.addColumn(column_creationTime);
		
		Column column_user=new Column("user",this.getMessage("order_user"));
		column_user.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				WmsOrder wo = (WmsOrder) object;
				return wo.getCreator().getUserName();
			}
		 });
		table.addColumn(column_user);
		
		Column column_state=new Column("state",this.getMessage("order_state"));
		column_state.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				WmsOrder wo = (WmsOrder) object;
				if(wo.getWmsOrderStatus().getId()==4)
		         return "新建出库";
				else if(wo.getWmsOrderStatus().getId()==5)
					return "待校验";
				else if(wo.getWmsOrderStatus().getId()==6)
			       return "已出库";
				else{
					return null;
				}
			}
		 });
		table.addColumn(column_state);
		
		
		
		Column column = new Column("action",this.getMessage("edit"));
		column.setTextAlign("center");
		links = new AbstractLink[] {enter_link};
		column.setDecorator(new LinkDecorator(table, links, "id"));
		table.addColumn(column);
	}
	
	public void onRender(){
//		try{
//			list= service.findOutboundType();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		SearchCondition sc=new SearchCondition();
		if(this.getContext().getSessionAttribute("search_contion")!=null){
			sc=(SearchCondition)this.getContext().getSessionAttribute("search_contion");
		}
		List result=new ArrayList();
		if(sc.getClient_id()!=null&&sc.getJob_id()!=null&sc.getState()!=null){
			String hql="from WmsOrder as wo where wo.wmsOrderType='"+2+"' and wo.clientId='"+sc.getClient_id()+"'";
			if(!"".equals(sc.getClient_id())){
				if(!"".equals(sc.getState())){
					hql+=" and wo.wmsOrderStatus.id="+Integer.parseInt(sc.getState());
				}
				if(!"".equals(sc.getJob_id())){
					hql+=" and wo.jobId like '%"+sc.getJob_id()+"%'";
				}
			}
			hql += " and wo.orderStatus <> 4";
			hql+="order by wo.jobId desc";
			try {
				result=service.queryStr(hql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		table.setRowList(result);
	}
	
	public boolean onEnter(){
		String id=enter_link.getValue();
		this.getContext().setSessionAttribute("detail_orderId", id);
		this.setRedirect("orderDetail.htm?id="+id);
		return true;
	}
	
	
	public boolean onReset(){
		form.clearErrors();
		form.clearValues();
		return true;
	}
	
	public boolean onSearch(){
		SearchCondition sc=new SearchCondition();
		sc.setClient_id(ownerSelect.getValue());
		sc.setState(stateSelect.getValue());
		sc.setJob_id(tf_jobid.getValue());
		this.getContext().setSessionAttribute("search_contion", sc);
		return true;
	}

}