package com.intelitune.nwms.item;

import java.util.List;

import net.sf.click.Page;
import net.sf.click.control.AbstractLink;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.Submit;
import net.sf.click.control.Table;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.LinkDecorator;

import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Invoice;
import com.intelitune.nwms.model.InvoiceState;
import com.intelitune.nwms.service.InvoiceService;
import com.intelitune.nwms.service.InvoiceServiceImp;
import com.intelitune.nwms.service.InvoiceStateService;
import com.intelitune.nwms.service.InvoiceStateServiceImp;
import com.intelitune.nwms.service.OrderItemService;
import com.intelitune.nwms.service.OrderItemServiceImp;

public class AddInvoice extends BorderPage
{
	// /////////////////////////////////////
	Menu me= new Menu();
	public String menuInclude= me.getOrderitem();


	public Form form= new Form("form");
	public TextField code=new TextField("code",getMessage("code"));
	public Submit reset= new Submit("reset", this, "onReset");
	public Submit cancel= new Submit("cancel",getMessage("cancel"), this, "onCancel");

	public Page page= new Page();
	public ActionLink edit= new ActionLink("edit", this, "onEdit");
//	public ActionLink addValue= new ActionLink("addValue", getMessage("addValue"), this, "onAddValue");

	public ActionLink deleteLink= new ActionLink("Delete", this, "onDeleteClick");
	public OrderItemService ois= OrderItemServiceImp.getInstance();
	public InvoiceService invoiceService= InvoiceServiceImp.getInstance();
	public InvoiceStateService stateService= InvoiceStateServiceImp.getInstance();
	public HiddenField hiddenId= new HiddenField("hiddenId", String.class);
	public TableEx table= new TableEx("table");
	public String invalid;
	public List list;
	public List li= null;
	public String id;
	public Integer wmsOrderId;
	public WmsOrder wmsOrder;
	
	//michael_ wang 2009 1030
	//public TextField txtPo = new TextField("po","采购订单号(Po)");
	//Kirin_Lv 2009-11-27
	public TextField txtSo =  new TextField("so","采购订单号(Po)");

	public AddInvoice()
	{
		wmsOrder=(WmsOrder)this.getContext().getSessionAttribute("wmsOrder");
		wmsOrderId=(Integer)this.getContext().getSessionAttribute("wmsOrderId");

		Submit save= new Submit("save", getMessage("save"), this, "onSave");
		 
		Submit reset=new Submit("reset",getMessage("reset") ,this,"onReset");

		form.setJavaScriptValidation(true);
		form.add(code);
		
		//michael_ wang 2009 1030
		form.add(txtSo);
		//Kirin_Lv 2009-11-27
		//form.add(txtPo);
		
		form.add(hiddenId);

		// form.add(add);

		deleteLink.setAttribute("onclick", "return window.confirm('"
				+ "确定删除,与此发票相关联的item都将失去发票" + "');");



		form.add(save);
		form.add(reset);
		form.add(cancel);

		table.setPageSize(10);
		table.setWidth("100%");

		table.setSortable(false);
		table.setPaginatorAttachment(Table.PAGINATOR_INLINE);

		Column column= new Column("id");
		 

		column= new Column("code",getMessage("invoiceCode"));
		column.setWidth("10%");
		table.addColumn(column);
	
		// Kirin_Lv 2009-11-27
		column= new Column("so","采购订单号(Po)");
		column.setWidth("10%");
		table.addColumn(column);
		

		//column= new Column("po","采购订单号(Po)");
		//column.setWidth("10%");
		//table.addColumn(column);

		column= new Column("createTime",getMessage("creationTime"));
		column.setWidth("30%");
		column.setFormat("{0,date,yyyy-MM-dd HH:mm:ss}");
		table.addColumn(column);
		
		
		column= new Column("lastTime",getMessage("lastModify"));
		column.setWidth("30%");
		column.setFormat("{0,date,yyyy-MM-dd HH:mm:ss}");
		table.addColumn(column);
		

//		column= new Column("creator.userName");
//		column.setTextAlign("center");
//		column.setWidth("20%");
//		table.addColumn(column);

		// /////////////////////////////////////////////////////

		// /////////////////////////////////////////////////////
		deleteLink.setAttribute("onclick", "return window.confirm('" + "确认删除?" + "');");

		column= new Column("action");
		column.setTextAlign("center");
		AbstractLink[] links= new AbstractLink[] { edit, deleteLink };
		column.setDecorator(new LinkDecorator(table, links, "id"));

		column.setSortable(false);
		table.addColumn(column);
	}

	public boolean onEdit()
	{
		id= edit.getValue();
		hiddenId.setValue(edit.getValue());

		return true;
	}


	@SuppressWarnings("unchecked")
	public void onInit()
	{
		super.onInit();

	}

	public void onRender()
	{
		if (id != null)
		{
			Invoice invoice= (Invoice)invoiceService.findById(id);
			form.copyFrom(invoice);
		}
		StringBuffer sb=new StringBuffer();
		sb.append("from Invoice i where i.orderId="+wmsOrderId+"  and i.invoiveState.code='"+0+"'");
		 List li= invoiceService.query(sb.toString());
		 table.setRowList(li);
	}

	public void onGet()
	{
		super.onGet();
	}

	public boolean onDeleteClick()
	{
		String del= deleteLink.getValue();
		String str1="from Item i where i.invoice.id='"+del+"'";
//		if(ois.queryObj(str1).size()!=0)
//		{	
//			invalid="确定删除,与此发票相关联的item都将失去发票";
//			return false;	
//		}
	   InvoiceState invoiceState=	stateService.query("from InvoiceState s where s.code='"+1+"'");
	   Invoice invoice =invoiceService.findById(del);
	   invoice.setInvoiveState(invoiceState);
	   invoice.setItems(null);
	   invoiceService.saveOrUpdate(invoice);
		
		return true;
	}


	@SuppressWarnings("unchecked")
	public boolean onSave() 
	{
		if (form.isValid())
		{
			Invoice invoice= null;
			if (hiddenId.getValue().equals(""))
			{
				invoice= new Invoice();
				invoice.setCreateTime(this.getCurrentTime());
				
			}
			else
			{
				invoice= (Invoice)invoiceService.findById( hiddenId.getValue() );
			}
			InvoiceState invoiceState=	stateService.query("from InvoiceState s where s.code='"+0+"'");
				invoice.setInvoiveState(invoiceState);
				invoice.setLastTime(this.getCurrentTime());
				invoice.setOrderId(wmsOrderId);
				invoice.setCode(code.getValue());			
				
				
				//michael_wang 2009 10 30
				//Kirin_Lv 2009-12-02
				invoice.setSo(txtSo.getValue());
				//invoice.setSo(txtSo.getValue());
				
				invoiceService.saveOrUpdate(invoice);
		}
	 	return true;
		 
	}

	public void cleanSession()
	{
		getContext().removeSessionAttribute("fileList");
		getContext().removeSessionAttribute("fileName");
		getContext().removeSessionAttribute("prefix");
		getContext().removeSessionAttribute("red");
		getContext().removeSessionAttribute("em");
		getContext().removeSessionAttribute("re");
	}

	public boolean onReset()
	{
		form.clearValues();
		return true;
	}

	public boolean onCancel()
	{
		this.setRedirect("normalOrderInfo.htm");
		return true;
	}
}