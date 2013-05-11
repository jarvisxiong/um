package com.intelitune.nwms.inbound;

import java.util.List;

import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.extras.control.LinkDecorator;
import net.sf.click.extras.control.TableInlinePaginator;

import com.axis2.services.MCSserviceStub.InttClientDetailWS;
import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.service.CheckingItemService;
import com.intelitune.nwms.service.CheckingItemServiceImpl;
import com.intelitune.nwms.service.OrderItemService;
import com.intelitune.nwms.service.OrderItemServiceImp;
import com.intelitune.nwms.service.OrderItemStateService;
import com.intelitune.nwms.service.OrderItemStateServiceImp;
import com.intelitune.nwms.service.OwnerZoneService;
import com.intelitune.nwms.service.OwnerZoneServiceImp;
/**
 * @author Louis
 *搜索CheckingItem
 */
public class SearchCheckItems extends BorderPage {
	public Select client = new Select("client",this.getMessage("client"));
	public Form form = new Form("form");
	public TableEx table = new TableEx("table"); 
	public WMSServiceInf ws =  WMSServiceImp.getInstance();
	public OwnerZoneService ozs =  OwnerZoneServiceImp.getInstance();
	public OrderItemService ois =  OrderItemServiceImp.getInstance();
	public CheckingItemService cis =  CheckingItemServiceImpl.getInstance();
	public OrderItemStateService oiss =  OrderItemStateServiceImp.getInstance();
	public String query;
	public String title = this.getMessage("searchcheckitems");
	public String position = this.getMessage("SEARCHCHECKITEMS");
	public ActionLink view = new ActionLink("view",this.getMessage("view"),this,"onView");
	
	public SearchCheckItems(){
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		table.setPageSize(10);
		Submit submit = new Submit("submit",this.getMessage("submit"),this,"onSubmit");
		Column column = null;
		column = new Column("jobId",this.getMessage("jobId"));
		table.addColumn(column);
		column = new Column("clientName",this.getMessage("clientName"));
		table.addColumn(column);
		column = new Column("isEmergency",this.getMessage("isEmergency"));
		table.addColumn(column);
		column = new Column("creator.userName",this.getMessage("userName"));
		table.addColumn(column);
		column = new Column("action",this.getMessage("action"));
		ActionLink [] links = new  ActionLink[]{view};
		column.setDecorator(new LinkDecorator(table, links, "jobId"));
		table.addColumn(column);
		form.add(client);
		form.add(submit);
		List<InttClientDetailWS> list = ozs.findOwnerList();
		client.add(Option.EMPTY_OPTION);
		for(InttClientDetailWS inttClientDetailWS:list){
			client.add(new Option(inttClientDetailWS.getId(),inttClientDetailWS.getCnName()));
		}
		view.setImageSrc("../image/view-16px.gif");
	}
	
	public boolean onvView(){
		String jobId = view.getValue();
		this.setRedirect("upLoadCheckItems.htm?jobId=" + jobId);
		return true;
	}
}
