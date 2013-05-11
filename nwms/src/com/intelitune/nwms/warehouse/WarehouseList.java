package com.intelitune.nwms.warehouse;

import java.util.List;

import net.sf.click.Context;
import net.sf.click.control.AbstractLink;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Decorator;
import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.IntegerField;
import net.sf.click.extras.control.LinkDecorator;
import net.sf.click.extras.control.TableInlinePaginator;

import com.intelitune.control.TableEx;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Warehouse;
import com.intelitune.nwms.model.WarehouseState;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;
import com.intelitune.nwms.service.WarehouseStateService;
import com.intelitune.nwms.service.WarehouseStateServiceImp;

public class WarehouseList extends com.intelitune.nwms.common.BorderPage {
	Menu me = new Menu();
	
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("warehouse_position");
	
	public String title=this.getMessage("warehouse_manage");
	
	public Form form = new Form();
	
	public TableEx table=new TableEx();
	
	public WarehouseService wsi =  WarehouseServiceImp.getInstance();
	
	public WarehouseStateService wss= WarehouseStateServiceImp.getInstance();
	
	public String warehouse_title =this.getMessage("create");
	
	private AbstractLink[] links;
	public ActionLink enter_link = new ActionLink("enter", getMessage("setting"),
			this, "onEnter");
	public ActionLink edit_link = new ActionLink("edit", getMessage("modify"),
			this, "onModify");
	public ActionLink del_link = new ActionLink("delete", getMessage("delete"),
			this, "onDelete");
	
	/*表单项*/
	public TextField tf_code = new TextField("code",this.getMessage("warehouse_code"),true);
	public TextField tf_alias = new TextField("alias",this.getMessage("warehouse_alias"),true);
	public TextField tf_address = new TextField("address",this.getMessage("warehouse_address"));
	public TextField tf_phone = new TextField("phone",this.getMessage("warehouse_phone"));
	public IntegerField tf_zip = new IntegerField ("zip",this.getMessage("warehouse_zip"));
	public TextField tf_area = new TextField("area",this.getMessage("warehouse_area"));
	public TextField tf_city = new TextField("city",this.getMessage("warehouse_city"));
	public WarehouseList(){
		me.buildWarehouse();
		form.setErrorsPosition(Form.POSITION_TOP);
		form.setColumns(3);
		form.add(tf_code,3);
		form.add(tf_alias,3);
		form.add(tf_phone,3);
		form.add(tf_zip,3);
		form.add(tf_area);
		form.add(tf_city);
		form.add(tf_address);
		form.add(new Submit(this.getMessage("save"),this,"onSave"));
		form.add(new Submit(this.getMessage("reset"),this,"onReset"));
		
		/*数据列表*/
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		table.setPageSize(10);
		
		table.addColumn(new Column("code",this.getMessage("warehouse_code")));	
		
		Column column_alias = new Column("alias", this.getMessage("warehouse_alias"));
//		column_alias.setDecorator(new Decorator() {
//			public String render(Object object, Context context) {
//				Warehouse wh = (Warehouse) object;
//				 String id = wh.getId();
//				 String alias=wh.getAlias();				
//		         return "<a href='BuildingList.htm?id="+ id + "'>" + alias + "</a>";
//			}
//		 });
		table.addColumn(column_alias);
		
		table.addColumn(new Column("phone",this.getMessage("warehouse_phone")));
		table.addColumn(new Column("zip",this.getMessage("warehouse_zip")));
		
		Column column1 = new Column(
				this.getMessage("warehouse_address"));
		column1.setTextAlign("center");
		column1.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				String address = null;
				Warehouse wh = (Warehouse) object;
				
				address = wh.area + wh.city + wh.address;
				
				return address;
				
			}
		});
		table.addColumn(column1);
		Column column_state=new Column("state",this.getMessage("warehouse_state"));
		column_state.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				Warehouse w = (Warehouse) object;
		        return w.getState().getDescription();
			}
		 });
		table.addColumn(column_state);
//		Column column2 = new Column(
//				this.getMessage("edit"));
//		table.addColumn(column2);

		Column column = new Column("action",this.getMessage("edit"));
		column.setTextAlign("center");
		links = new AbstractLink[] {enter_link, edit_link, del_link };
		column.setDecorator(new LinkDecorator(table, links, "id"));
		table.addColumn(column);

		
		del_link.setAttribute("onclick", "return window.confirm('"
				+getMessage("confirm_delete")+"?" + "');");
	}
	
	public boolean onSave(){
		if(form.isValid()){
		Warehouse w=new Warehouse();
		w.setCode(tf_code.getValue().toUpperCase());
		w.setAlias(tf_alias.getValue());
		w.setAddress(tf_address.getValue());
		w.setPhone(tf_phone.getValue());
		w.setZip(tf_zip.getValue());
		w.setArea(tf_area.getValue());
		w.setCity(tf_city.getValue());
		w.setState(wss.findWarehouseStateByCode(WarehouseState.NORMAL));
		wsi.addWarehouse(w);
		this.setRedirect(WarehouseList.class);
	
		}
		return true;
	}
	/*重置*/
	public boolean onReset()
	{
		form.clearErrors();
		form.clearValues();
		return true;
	}
	
	public boolean onEnter(){
		String id=enter_link.getValue();
		this.setRedirect("BuildingList.htm?id="+id);
		return true;
	}
	
	//删除
	public boolean onDelete(){
		String id = del_link.getValue();
		wsi.delWarehouseById(id);
		this.setRedirect(WarehouseList.class);
		return true;
	}
	//修改
	public boolean onModify(){
		String id = edit_link.getValue();
		this.setRedirect("ModifyWarehouse.htm?id="+id);
		return true;
	}
	 public void onRender() {
		 List list=wsi.findWarehouseList();
		 table.setRowList(list);
	 }

}