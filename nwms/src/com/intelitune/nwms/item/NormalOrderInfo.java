package com.intelitune.nwms.item;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.click.control.AbstractLink;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.FileField;
import net.sf.click.control.Form;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.extras.control.LinkDecorator;
import net.sf.click.extras.control.TableInlinePaginator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.intelitune.ccos.client.WMSServicePortType;
import com.intelitune.ccos.client.WMSServicePortTypeProxy;
import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.control.RichTextArea;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.common.Const;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.ItemState;
import com.intelitune.nwms.model.OrderItem;
import com.intelitune.nwms.service.InboundOrderItemService;
import com.intelitune.nwms.service.InboundOrderItemServiceImp;
import com.intelitune.nwms.service.ItemStateService;
import com.intelitune.nwms.service.ItemStateServiceImp;
import com.intelitune.nwms.service.OrderItemService;
import com.intelitune.nwms.service.OrderItemServiceImp;
import com.intelitune.nwms.service.OutboundOrderItemService;
import com.intelitune.nwms.service.OutboundOrderItemServiceImp;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;

public class NormalOrderInfo extends BorderPage implements Serializable
{
	// /////////////////////////////////
	Menu me= new Menu();
	public String menuInclude= me.getOrderitem();
	// //////////////////////////////////
	private WMSServicePortType wmsService= new WMSServicePortTypeProxy();
	private InboundOrderItemService iois=  InboundOrderItemServiceImp.getInstance();
	private OutboundOrderItemService oois=  OutboundOrderItemServiceImp.getInstance();
	private OrderItemService ois= OrderItemServiceImp.getInstance();
	private WarehouseService warehouseService=  WarehouseServiceImp.getInstance();
	private ItemStateService itemStateService= ItemStateServiceImp.getInstance();
	private WMSServiceInf wmsServiceImp= WMSServiceImp.getInstance();

	public Integer wmsOrderId;
	public WmsOrder wmsOrder;
	public Form form= new Form("form");
	public Form warehouseForm= new Form("warehouseForm");
	public Form itemForm= new Form("itemForm");
	public Select warehouse= new Select("warehouse", getMessage("warehouse"), true);
	public ActionLink deleteLink= new ActionLink("Delete",getMessage("delete"), this, "onDeleteClick");
	public ActionLink modifyLink= new ActionLink("modify",getMessage("modify"), this, "onModifyClick");
	public ActionLink view= new ActionLink("view", getMessage("view"), this, "onView");

	public Submit toList= new Submit("toList", getMessage("back"), this, "toList");
	public Submit toNewItem= new Submit("toNewItem", getMessage("addItem"), this, "toNewItem");
	public Submit addInvoice=new Submit("addInvoice",getMessage("addInvoice"),this,"addInvoice");
	public Submit addType=new Submit("addType",getMessage("addType"),this,"addType");
	
	public FileField ff = new FileField("checkFile","导入入库清单");
	public Submit importSubmit = new Submit("import","导入",this,"onImport");
	
	public TableEx itemTable= new TableEx("itemTable");

	public RichTextArea remark= new RichTextArea("noderemark");
	public List list;
	public List li= null;
	public String invalid=null;

	public String orderinfo= getMessage("orderinfo");
	
	public String message="";

	public NormalOrderInfo()
	{
		wmsOrderId= (Integer) this.getContext().getSessionAttribute("wmsOrderId");
		try
		{
			wmsOrder= wmsServiceImp.findWmsOrderById(wmsOrderId);
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			form.add(toNewItem);
			form.add(addInvoice);
//		Submit add= new Submit("add", this, "onAdd");
//		Submit upload= new Submit("upload", this, "onUpload");
//		Submit warehouseSave= new Submit("warehouseSave", getMessage("warehouseSave"), this, "onWarehouseSave");
//
//		Submit confirm= new Submit("confirm", this, "confirm");
//		confirm.setAttribute("onclick", "return window.confirm('" + "是否确认,确认后将不能再编辑" + "');");

//		warehouseForm.add(warehouse);
//		warehouseForm.add(warehouseSave);

		form.add(ff);
		form.add(importSubmit);
		form.add(toList);

		itemTable.setWidth("100%");
		itemTable.setPaginator(new TableInlinePaginator(itemTable));
		itemTable.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		itemTable.setPageSize(20);

		itemTable.setHoverRows(true);


		Column code= new Column("material.code", getMessage("materialCode"));

		Column material= new Column("material.alias", getMessage("materialAlias"));
		Column description = new Column("material.description","英文名称");

		Column unitPackage= new Column("unitPackage.alias", "SKU");
		Column qty= new Column("qty", getMessage("qty"));
		 
		Column LastModifiedTime= new Column("LastModifiedTime", getMessage("LastModifiedTime"));
		Column invoiceCode= new Column("invoice.code", getMessage("invoiceCode"));
		Column itemtype= new Column("itemType.alias", getMessage("itemtype"));
		
		deleteLink.setAttribute("onclick", "return window.confirm('" + "确认废弃?" + "');");

		Column column= new Column("action",getMessage("action"));
		column.setTextAlign("center");
	
		AbstractLink[] links1= new AbstractLink[] { deleteLink };
		AbstractLink[] links2= new AbstractLink[] { modifyLink,deleteLink };
		if(Integer.parseInt(wmsOrder.getWmsOrderType())==1){
			column.setDecorator(new LinkDecorator(itemTable, links2, "id"));
		}
		if(Integer.parseInt(wmsOrder.getWmsOrderType())==2){
			column.setDecorator(new LinkDecorator(itemTable, links1, "id"));
		}
		Column unit_price = new Column("unit_price","单价");
		Column unit_weight = new Column("unit_weight","单位重量");
		Column gross_price = new Column("gross_price","总价");
		Column gross_weight = new Column("gross_weight","总重");
		Column currency = new Column("currency","币种");
		LastModifiedTime.setFormat("{0,date,yyyy-MM-dd HH:mm:ss}");
		itemTable.addColumn(code);
		itemTable.addColumn(invoiceCode);
		itemTable.addColumn(itemtype);
		itemTable.addColumn(material);
		itemTable.addColumn(description);
		itemTable.addColumn(unitPackage);
		itemTable.addColumn(qty);
		itemTable.addColumn(unit_price);
		itemTable.addColumn(unit_weight);
		itemTable.addColumn(gross_price);
		itemTable.addColumn(gross_weight);
		itemTable.addColumn(currency);
		itemTable.addColumn(LastModifiedTime);
		itemTable.addColumn(column);
		

		// /////////////////////////////////////////////////////
		/*
		 * Column column= new Column("action", getMessage("action"));
		 * column.setTextAlign("center"); AbstractLink[] links= new AbstractLink[] {
		 * view }; column.setDecorator(new LinkDecorator(itemTable, links, "id"));
		 * column.setSortable(false); itemTable.addColumn(column);
		 */
		// ///////////////////////////////////////////////////////////////
		 
	}
	
	public boolean addInvoice()
	{
		this.setRedirect("addInvoice.htm");
		return true;
	}
	
	
	public boolean onDeleteClick()
	{
		if(wmsOrder.getWmsOrderStatus().getId()!=1&&wmsOrder.getWmsOrderStatus().getId()!=4)
		{
			List li;
			if (wmsOrder.getWmsOrderType().equals(Const.TYPE_INBOUND))
			{
				li= iois.queryInboundOrderItem("from InboundOrderItem i where  i.state.code='" + ItemState.NORMAL
						+ "' and  i.orderId=" + wmsOrder.getId() + " order by i.creationTime desc");
			} else
			{
				li= oois.queryOutboundOrderItem("from OutboundOrderItem o where o.state.code='" + ItemState.NORMAL
						+ "' and  o.orderId=" + wmsOrder.getId() + " order by o.creationTime desc");
			}
			itemTable.setRowList(li);
			invalid="item已进入操作中,不能删除";
			return false;
			
		}
		String del = deleteLink.getValue();
		System.out.println(del);
	   OrderItem  orderItem= ois.findById(del);
	   ItemState itemState=itemStateService.findItemStateByCode(ItemState.DELETE);
	   orderItem.setState(itemState);
	   ois.saveOrderItem(orderItem);
	
		return true;

	}
	
	public boolean onModifyClick(){
		if(wmsOrder.getWmsOrderStatus().getId()!=1&&wmsOrder.getWmsOrderStatus().getId()!=4){
			List li;
			if (wmsOrder.getWmsOrderType().equals(Const.TYPE_INBOUND))
			{
				li= iois.queryInboundOrderItem("from InboundOrderItem i where  i.state.code='" + ItemState.NORMAL
						+ "' and  i.orderId=" + wmsOrder.getId() + " order by i.creationTime desc");
			} else
			{
				li= oois.queryOutboundOrderItem("from OutboundOrderItem o where o.state.code='" + ItemState.NORMAL
						+ "' and  o.orderId=" + wmsOrder.getId() + " order by o.creationTime desc");
			}
			itemTable.setRowList(li);
			invalid="item已进入操作中,不能删除";
			return false;
		}
		String modify=modifyLink.getValue();
		this.setRedirect("modifyNum.htm?id="+modify);
		return true;
	}

	public boolean onWarehouseSave() throws RemoteException
	{
		if (warehouseForm.isValid())
		{
			String alias= warehouseService.findNameById(warehouse.getValue());
			wmsOrder.setWarehouseAlias(alias);
			wmsOrder.setWarehouseId(warehouse.getValue());
			this.setRedirect(this.getClass());
		 
			wmsService.save(wmsOrder);

		} else
			return false;
		return true;
	}

	public boolean toNewItem()
	{
		this.getContext().setSessionAttribute("jobId", wmsOrder.getJobId());
		this.getContext().setSessionAttribute("clientId", wmsOrder.getClientId());
		this.getContext().setSessionAttribute("orderId", wmsOrder.getId().toString());
		if (wmsOrder.getWmsOrderType().equals(Const.TYPE_INBOUND))
			this.setRedirect(AddInboundOrderItem.class);
		else
			this.setRedirect(AddOutboundOrderItem.class);
		return true;
	}

	public void cleanSession()
	{
		getContext().removeSessionAttribute("inf_fileList");
		getContext().removeSessionAttribute("inf_fileName");

	}

	public void onInit()
	{
		super.onInit();
		
		// super.checkAccess(form, this.getClass().getName(), null);

	}

	@SuppressWarnings("unchecked")
	public void onRender()
	{

		if (wmsOrder.getWarehouseId() != null)
		{
			warehouse.setValue(wmsOrder.getWarehouseId());
		}
		Integer id= wmsOrder.getId();
		List li;
		if (wmsOrder.getWmsOrderType().equals(Const.TYPE_INBOUND))
		{
			li= iois.queryInboundOrderItem("from InboundOrderItem i where  i.state.code='" + ItemState.NORMAL
					+ "' and  i.orderId=" + wmsOrder.getId() + " order by i.creationTime desc");
		} else
		{
			li= oois.queryOutboundOrderItem("from OutboundOrderItem o where o.state.code='" + ItemState.NORMAL
					+ "' and  o.orderId=" + wmsOrder.getId() + " order by o.creationTime desc");
		}
		itemTable.setRowList(li);

	}

	public boolean onView()
	{
		this.getContext().setSessionAttribute("editOrderItemId", "editOrderItemId");
		if (wmsOrder.getWmsOrderType().equals(Const.TYPE_INBOUND))
			this.setRedirect(EditInboundOrderItem.class);
		else
			this.setRedirect("");
		return true;
	}

	public boolean toList()
	{
		this.setRedirect("orderList.htm");
		return true;
	}
	
	public boolean addType(){
		this.setRedirect(AddItemType.class);
		return true;
	}

	
	public boolean onImport(){
		message="";
		java.text.DecimalFormat   formatter   =   new   java.text.DecimalFormat("##############");
		String url="";
		
		InputStream is;
		String path = getContext().getServletContext().getRealPath("/item/importList");
		
		
		int a = ff.getFileItem().getName().length();
		int b = ff.getFileItem().getName().lastIndexOf("\\");
		String fName = ff.getFileItem().getName().substring(b+1);
		Date t = new java.util.Date(System.currentTimeMillis());
		SimpleDateFormat f = new SimpleDateFormat(
				"yyyyMMddHHmmssSSS");
		String timeStr = f.format(t);
		fName = timeStr+ fName;
		File file = new File(path,fName);
		FileOutputStream fos;	
		try {
			is = ff.getFileItem().getInputStream();
			byte[] buffer = new byte[1024];
			fos = new FileOutputStream(file);
			int c = 0;
			while ((c = is.read(buffer)) != -1) {
				fos.write(buffer);
			}
			fos.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		url= file.getPath();
		
		List<ImportItemModel> lstExcelModel = new ArrayList<ImportItemModel>();
		List<String> list_title = new ArrayList<String>();
				
		HSSFWorkbook workbook;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(url));
			HSSFSheet childSheet = workbook.getSheetAt(0);
			for(int i=1;i<childSheet.getRow(1).getPhysicalNumberOfCells();i++){
				if(childSheet.getRow(i)==null||childSheet.getRow(i).getCell(1)==null||"".equals(formatCell(childSheet.getRow(i).getCell(0)))){
					break;
				}
				ImportItemModel model = new ImportItemModel();
				String mcode = formatCell(childSheet.getRow(i).getCell(0)).trim();
				String invoice = formatCell(childSheet.getRow(i).getCell(1)).trim();
				String po = formatCell(childSheet.getRow(i).getCell(2)).trim();
				String itemType = formatCell(childSheet.getRow(i).getCell(3)).trim();
				
				
				try{
					int number = Integer.parseInt(formatCell(childSheet.getRow(i).getCell(4)).trim());
					model.setNumber(number);
				}catch(java.lang.NumberFormatException nfe){
					message += "第"+i+"行&nbsp;数量&nbsp;有误!<br>";
				}
				try{
					float unitPrice = Float.parseFloat(formatCell(childSheet.getRow(i).getCell(5)).trim());
					model.setUnitPrice(unitPrice);
				}catch(NumberFormatException nfe){
					message += "第"+i+"行&nbsp;单价&nbsp;有误!<br>";					
				}
				
				try{
					float unitWeight = Float.parseFloat(formatCell(childSheet.getRow(i).getCell(6)).trim());
					model.setUnitWeight(unitWeight);
				}catch(NumberFormatException nfe){
					message += "第"+i+"行&nbsp;单位重量&nbsp;有误!<br>";	
				}
				String currency = formatCell(childSheet.getRow(i).getCell(7)).trim();
				
				
				model.setCurrency(currency);
				model.setInvoiceNo(invoice);
				model.setPo(po);
				model.setItemTypeCode(itemType);				
				model.setMcode(mcode);				
				lstExcelModel.add(model);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return true;
	}
	
	public String formatCell(HSSFCell cell){
		String str="";
		
		if(cell==null) return str;
		
		if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
			   BigDecimal value =  new BigDecimal(cell.getNumericCellValue());
			  str=value.toString();
		}else{
			str=cell.getStringCellValue();
		}
		return str;
	}
	
}