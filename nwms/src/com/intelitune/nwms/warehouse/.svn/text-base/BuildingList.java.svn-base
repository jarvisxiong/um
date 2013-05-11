package com.intelitune.nwms.warehouse;

import java.util.List;

import net.sf.click.Context;
import net.sf.click.control.AbstractLink;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Decorator;
import net.sf.click.control.Form;
import net.sf.click.control.PageLink;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.LinkDecorator;
import net.sf.click.extras.control.TableInlinePaginator;

import com.intelitune.control.TableEx;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Building;
import com.intelitune.nwms.model.BuildingState;
import com.intelitune.nwms.model.Warehouse;
import com.intelitune.nwms.service.BuildingService;
import com.intelitune.nwms.service.BuildingServiceImp;
import com.intelitune.nwms.service.BuildingStateService;
import com.intelitune.nwms.service.BuildingStateServiceImp;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;

public class BuildingList extends com.intelitune.nwms.common.BorderPage {
	
	Menu me = new Menu();
	
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("building_position");
	
	public String building_title=this.getMessage("create");
	
	public WarehouseService ws= WarehouseServiceImp.getInstance();
	
	public String warehouse_id;
	
	public String title;
	
	public PageLink pageLink = new PageLink("building_return",this.getMessage("return"), WarehouseList.class);
	
	public Form form = new Form();
	
	public TableEx table=new TableEx();
	
	public BuildingService bs= BuildingServiceImp.getInstance();
	public BuildingStateService bss= BuildingStateServiceImp.getInstance();
	
	private AbstractLink[] links;
	public ActionLink enter_link = new ActionLink("enter", getMessage("setting"),
			this, "onEnter");
	public ActionLink edit_link = new ActionLink("edit", getMessage("modify"),
			this, "onModify");
	public ActionLink del_link = new ActionLink("delete", getMessage("delete"),
			this, "onDelete");
	
	
	
	/*表单项*/
	public TextField tf_code = new TextField(this.getMessage("building_code"),true);
	public TextField tf_alias = new TextField(this.getMessage("building_alias"),true);
	
	public BuildingList(){
		form.setErrorsPosition(Form.POSITION_TOP);
		form.add(tf_code);
		form.add(tf_alias);
		form.add(new Submit(this.getMessage("save"),this,"onSave"));
		form.add(new Submit(this.getMessage("reset"),this,"onReset"));
		
		/*数据列表*/
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		table.setPageSize(10);
		
		table.addColumn(new Column("code",this.getMessage("building_code")));	
		
		Column column_alias = new Column("alias", this.getMessage("warehouse_alias"));
//		column_alias.setDecorator(new Decorator() {
//			public String render(Object object, Context context) {
//				Building b = (Building) object;
//				 String id = b.getId();
//				 String alias=b.getAlias();
//		         return "<a href='floorList.htm?id="+ id + "'>" + alias + "</a>";
//			}
//		 });
		table.addColumn(column_alias);
		Column column_state=new Column("state",this.getMessage("buliding_state"));
		column_state.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				Building  b = (Building) object;
		         return b.getState().getDescription();
			}
		 });
		table.addColumn(column_state);
		
		Column column = new Column("action",this.getMessage("edit"));
		column.setTextAlign("center");
		links = new AbstractLink[] {enter_link, edit_link, del_link };
		column.setDecorator(new LinkDecorator(table, links, "id"));
		table.addColumn(column);
		
		del_link.setAttribute("onclick", "return window.confirm('"
				+getMessage("confirm_delete")+"?" + "');");
	}
	//保存
	public boolean onSave(){
		if(form.isValid()){
			Building b=new Building();
			b.setCode(tf_code.getValue().toUpperCase());
			b.setAlias(tf_alias.getValue());
			Warehouse w=ws.findWarehouseById(this.getContext().getSessionAttribute("warehouselist_id").toString());
			b.setWarehouse(w);
			b.setState(bss.findBuildingStateByCode(BuildingState.NORMAL));
			bs.addBuilding(b);
			this.setRedirect("BuildingList.htm");
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
	@Override
	public void onRender() {
		warehouse_id=this.getContext().getRequestParameter("id");
		if(warehouse_id!=null){
			this.getContext().getSession().setAttribute("warehouselist_id", warehouse_id);
		}
		title=ws.findNameById(this.getContext().getSessionAttribute("warehouselist_id").toString());
		List list=bs.findBuildingListByWarehouse_id(this.getContext().getSessionAttribute("warehouselist_id").toString());
		table.setRowList(list);
		
	}
	
	public boolean onModify(){
		String id = edit_link.getValue();
		this.setRedirect("modifyBuilding.htm?id="+id);
		return true;
		
	}
	
	public boolean onDelete(){
		String id=del_link.getValue();
		bs.delBuilding(id);
		this.setRedirect(BuildingList.class);
		return true;
	}
	
	public boolean onEnter(){
		String id=enter_link.getValue();
		this.setRedirect("floorList.htm?id="+id);
		return true;
	}
	

}