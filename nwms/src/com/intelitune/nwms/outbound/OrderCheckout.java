package com.intelitune.nwms.outbound;

import java.util.List;

import net.sf.click.Context;
import net.sf.click.control.AbstractLink;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Decorator;
import net.sf.click.extras.control.LinkDecorator;
import net.sf.click.extras.control.TableInlinePaginator;

import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.menu.Menu;

public class OrderCheckout extends com.intelitune.nwms.common.BorderPage {
	
	Menu me = new Menu();
	
	public String menuInclude = me.getOutbound();
	
	public String position = this.getMessage("order_checkout_position");
	
	public String title=this.getMessage("order_checkout");
	
	public List<WmsOrder> list;
	
	public TableEx table=new TableEx();
	
	public WMSServiceInf service=WMSServiceImp.getInstance();
	
	private AbstractLink[] links;
	public ActionLink check_link = new ActionLink("check", getMessage("check"),
			this, "onCheck");
	
	public OrderCheckout(){
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		table.setPageSize(10);
		

		Column column_jobid= new Column("job_id", this.getMessage("order_jobid"));
		column_jobid.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				WmsOrder wo = (WmsOrder) object;
			return wo.getJobId();
		}
		});
		table.addColumn(column_jobid);
		
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
		
		Column column_user=new Column("user",this.getMessage("order_user"));
		column_user.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				WmsOrder wo = (WmsOrder) object;
				return wo.getCreator().getUserName();
			}
		 });
		table.addColumn(column_user);
		
	
		
		
		Column column = new Column("action",this.getMessage("check"));
		column.setTextAlign("center");
		links = new AbstractLink[] {check_link };
		column.setDecorator(new LinkDecorator(table, links, "id"));
		table.addColumn(column);
		
		
	}
	
	
	public void onRender(){
		list=service.findOutboundInPicking();
		table.setRowList(list);
	}

	public boolean onCheck(){
		String id=check_link.getValue();
		this.setRedirect("uploadCheckFile.htm?id="+id);
		return true;
	}
	
	
}