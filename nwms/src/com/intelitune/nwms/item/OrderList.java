
package com.intelitune.nwms.item;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import net.sf.click.Context;
import net.sf.click.control.AbstractLink;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Checkbox;
import net.sf.click.control.Column;
import net.sf.click.control.Decorator;
import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.control.Table;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.DateField;
import net.sf.click.extras.control.LinkDecorator;

import com.intelitune.ccos.client.WMSServicePortType;
import com.intelitune.ccos.client.WMSServicePortTypeProxy;
import com.intelitune.ccos.model.OrderStatus;
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.ccos.model.WmsOrderStatus;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;



public class OrderList extends BorderPage implements Serializable
{
	Menu me = new Menu();
	public String menuInclude = me.getOrderitem();
	private WMSServicePortType wmsService = new WMSServicePortTypeProxy();

	public Integer id;
	public Form form = new Form("form");
	public TableEx table = new TableEx();
	public ActionLink view = new ActionLink("view",getMessage("view"), this, "onView");
	

	

	public TextField jobId = new TextField("jobId",getMessage("jobId"));
	public Select wmsOrderStatus=new Select("wmsOrderStatus",getMessage("wmsOrderStatus"));
	public Select wmsOrderType=new Select("wmsOrderType", getMessage("wmsOrderType"));
	public Select orderStatus = new Select("orderStatus",getMessage("orderStatus"));
	public DateField df_creationFormDate = new DateField("df_creationFormDate",getMessage("df_creationFormDate"));
	public DateField df_creationToDate = new DateField("df_creationToDate", "~");
	public Checkbox isEx = new Checkbox("isEx",getMessage("isEx"));
	public StringBuffer qyStr=null;
	public String orderlist = getMessage("orderList");
	Submit sb_latest = new Submit("latest",  getMessage("latest"),"onLatestClick");

	// ----------------------------------------------------------- Constructors

	



	

	public OrderList() throws RemoteException
	{  
		setStateful(true);

		
		form.setColumns(2);
		form.add(jobId);
		form.add(wmsOrderStatus);
		form.add(wmsOrderType);
		form.add(orderStatus);
		form.add(df_creationFormDate);
		form.add(df_creationToDate);
		form.add(isEx);
		form.add(new Submit("Search",getMessage("search") ,this, "onSearch"));
		form.add(new Submit("latest",getMessage("latest"), this,"onLatestClick"));
		form.add(new Submit("Clear",getMessage("clear"), this, "onClearClick"));
		//form.add(new Submit("toTest","测试",this,"ontest"));
	
		df_creationFormDate.setFormatPattern("yyyy-MM-dd");
		df_creationToDate.setFormatPattern("yyyy-MM-dd");
		// Setup customers table

		table.setPageSize(10);
		table.setWidth("100%");
		
		// table.setShowBanner(false);
		table.setSortable(false);
	
		table.setPaginatorAttachment(Table.PAGINATOR_INLINE);
		Column col = new Column("col",getMessage("isEmergency"));
		col.setDecorator(new Decorator()
		{
			public String render(Object object, Context context)
			{
				WmsOrder order = (WmsOrder) object;
				String a = "";
				if (order.getIsEmergency() == 1)
					a += "<img src=\"../image/prongix.gif\" >&nbsp";

				return a;
			}
		});
		table.addColumn(col);

		Column column = new Column("jobId",getMessage("jobId"));
		table.addColumn(column);
		column.setSortable(true);
		
		Column rcn = new Column("crn",getMessage("client_jobid"));
		rcn.setDecorator(new Decorator()
		{
			public String render(Object object, Context context)
			{
				WmsOrder order = (WmsOrder) object;
				return order.getCrn();
			}
		});
		table.addColumn(rcn);
		Column clientName = new Column("client_name",getMessage("client_name"));
		clientName.setDecorator(new Decorator()
		{
			public String render(Object object, Context context)
			{
				WmsOrder order = (WmsOrder) object;
				return order.getClientName();
			}
		});
		table.addColumn(clientName);
		
		
		column = new Column("orderStatus.name",getMessage("orderStatus"));
		column.setWidth("10%");
		table.addColumn(column);
		
		column = new Column("wmsOrderType",getMessage("wmsOrderType"));
		column.setWidth("10%");
		column.setDecorator(new Decorator()
		{
			public String render(Object object, Context context)
			{
				WmsOrder order = (WmsOrder) object;
				String a = "";
				if (order.getWmsOrderType().trim().equals("1"))
					a += "入库";
				else
					a+="出库";
				return a;
			}
		});
		table.addColumn(column);
		
		
		
		column = new Column("wmsOrderStatus.name",getMessage("wmsOrderStatus"));
		column.setWidth("10%");
		table.addColumn(column);

		column = new Column("creationTime",getMessage("creationTime"));
		column.setWidth("15%");
		column.setFormat("{0,date,yyyy-MM-dd HH:mm:ss}");
		table.addColumn(column);

		
		
		column = new Column("creator.userName",getMessage("creator"));
		column.setTextAlign("center");
		column.setWidth("10%");
		table.addColumn(column);

		
		// /////////////////////////////////////////////////////

		// /////////////////////////////////////////////////////
		

		column = new Column("action",getMessage("action"));
		column.setTextAlign("center");
		AbstractLink[] links = new AbstractLink[] { view};
		column.setDecorator(new LinkDecorator(table, links, "id"));

		column.setSortable(false);
		table.addColumn(column);
		Object[] objs = (Object[]) wmsService.query("from OrderStatus");		
		List orderStatuses= Arrays.asList(objs); 
		List<OrderStatus> li=orderStatuses;
		orderStatus.add(new Option(0, ""));
		for (OrderStatus _orderStatus : li)
		{

			orderStatus.add(new Option(_orderStatus.getId(), _orderStatus
					.getName()));
		}
		
//============================================
		List objList= Arrays.asList(wmsService.query("from WmsOrderStatus"));
	   List<WmsOrderStatus>  li2=objList;
		wmsOrderStatus.add(new Option(0,""));
	
		
		for(WmsOrderStatus _wmsOrderStatus: li2)
		{
			wmsOrderStatus.add(new Option(_wmsOrderStatus.getId(),_wmsOrderStatus.getName()));
		}
		wmsOrderType.add(new Option(0,("")));
		wmsOrderType.add(new Option(1,"进库"));
		wmsOrderType.add(new Option(2,"出库"));

	}
	
	public boolean onSearch()
	{
		String _jobId = jobId.getValue().trim();
		String _orderStatus = orderStatus.getValue().trim();
		String from_creationTime = df_creationFormDate.getValue().trim();
		String to_creationTime = df_creationToDate.getValue().trim();
		String _wmsOrderStatus=wmsOrderStatus.getValue().trim();
		String _wmsOrderType=wmsOrderType.getValue().trim();
		qyStr = new StringBuffer("from WmsOrder c where c.id>0 and  c.orderStatus<>4");
		if (!_jobId.equals(""))
		{
			qyStr.append(" and c.jobId like '%" + _jobId + "%' ");
		}
		if (!_orderStatus.equals("") && !_orderStatus.equals("0"))
		{
			qyStr.append(" and c.orderStatus=" + _orderStatus + "");
		}
		if (_orderStatus.equals("0") || _orderStatus.equals(""))
		{
			qyStr.append(" and c.orderStatus <> 4");
		}
		if (!from_creationTime.equals(""))
			qyStr.append(" and c.creationTime>'" + from_creationTime + "'");
		if (!to_creationTime.equals(""))
			qyStr.append(" and c.creationTime<'" + to_creationTime + "'");
		if (isEx.isChecked())
			qyStr.append(" and c.isEmergency=1");
		
		if (!_wmsOrderStatus.equals("0") )
		{
			qyStr.append(" and c.wmsOrderStatus ="+_wmsOrderStatus);
		}
		if(!_wmsOrderType.equals("0"))
			qyStr.append("  and c.wmsOrderType='"+_wmsOrderType+"'");
		

		qyStr.append(" order by c.creationTime desc");
		
		return true;
	}

	public void onInit()
	{
//		super.onInit();
//		super.checkAccess(form, this.getClass().getName(), null);

	}

	// --------------------------------------------------------- Event Handlers

	/**
	 * Handle the clear button click event.
	 * 
	 * @return true
	 */
	public boolean onClearClick()
	{
		form.clearErrors();
		form.clearValues();
		return true;
	}

	/**
	 * Handle the new button click event.
	 * 
	 * @return false
	 */

	/**
	 * Handle the delete link click event.
	 * @throws RemoteException 
	 * 
	 */
	public boolean onView() throws RemoteException
	{
		id = view.getValueInteger();
		WmsOrder wmsOrder = (WmsOrder) wmsService.findById(id);
		Integer orderStatusId = wmsOrder.getOrderStatus().getId();
		this.getContext().setSessionAttribute("wmsOrderId", id);
		this.getContext().setSessionAttribute("wmsOrder", wmsOrder);
		
			setRedirect("normalOrderInfo.htm");

		return true;
	}



	@SuppressWarnings("unchecked")
	public void onRender()
	{
		if (qyStr == null)
		{
			Calendar calendar1 = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance();
			calendar1.set(Calendar.HOUR_OF_DAY, 0);
			calendar1.set(Calendar.MINUTE, 0);
			calendar1.set(Calendar.SECOND, 0);
			calendar1.set(Calendar.MILLISECOND, 0);
			calendar2.set(Calendar.HOUR_OF_DAY, 23);
			calendar2.set(Calendar.MINUTE, 59);
			calendar2.set(Calendar.SECOND, 59);
			calendar2.set(Calendar.MILLISECOND, 999);
			Timestamp timestamp1 = new Timestamp(calendar1.getTimeInMillis());
			Timestamp timestamp2 = new Timestamp(calendar2.getTimeInMillis());
			qyStr = new StringBuffer(
					"from WmsOrder i where i.creationTime<='"
							+ timestamp2
							+ "' and i.creationTime>='"
							+ timestamp1
							+"'"
							);
		
			qyStr.append(" and i.orderStatus<>4 order by i.id desc");
			
		}
		else{
			
		}
		List li=null;
		try
		{			
			Object[] objs = wmsService.queryWmsOrder(qyStr.toString());
		   li= Arrays.asList(objs);
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.setRowList(li);
	}

	public boolean onLatestClick()
	{
		// 今日创建并已审核的订单
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.set(Calendar.HOUR_OF_DAY, 0);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);
		calendar1.set(Calendar.MILLISECOND, 0);
		calendar2.set(Calendar.HOUR_OF_DAY, 23);
		calendar2.set(Calendar.MINUTE, 59);
		calendar2.set(Calendar.SECOND, 59);
		calendar2.set(Calendar.MILLISECOND, 999);
		Timestamp timestamp1 = new Timestamp(calendar1.getTimeInMillis());
		Timestamp timestamp2 = new Timestamp(calendar2.getTimeInMillis());
		qyStr = new StringBuffer(
				"from WmsOrder i where i.creationTime<='"
						+ timestamp2
						+ "' and i.creationTime>='"
						+ timestamp1
						+"'"
						);
	
		qyStr.append(" and i.orderStatus<>4 order by i.id desc");
		return true;
	}
	
	public boolean ontest(){	
		this.setRedirect(CommitOutBoundStatePage.class);
		return true;
	}

}