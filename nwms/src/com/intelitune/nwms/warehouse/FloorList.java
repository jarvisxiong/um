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
import com.intelitune.nwms.model.Floor;
import com.intelitune.nwms.model.FloorState;
import com.intelitune.nwms.service.BuildingService;
import com.intelitune.nwms.service.BuildingServiceImp;
import com.intelitune.nwms.service.FloorService;
import com.intelitune.nwms.service.FloorServiceImp;
import com.intelitune.nwms.service.FloorStateService;
import com.intelitune.nwms.service.FloorStateServiceImp;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;

public class FloorList extends com.intelitune.nwms.common.BorderPage {
	
Menu me = new Menu();
	
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("floor_position");
	
	public String floor_title=this.getMessage("create");
	
	public String title;
	
	public String building_id;
	
	public PageLink pageLink = new PageLink("floor_return",this.getMessage("return"), BuildingList.class);
	
	public BuildingService bs= BuildingServiceImp.getInstance();
	public FloorStateService fss= FloorStateServiceImp.getInstance();
	public FloorService fs= FloorServiceImp.getInstance();
	public WarehouseService ws= WarehouseServiceImp.getInstance();
	
	public Form form=new Form();
	public TableEx table=new TableEx();
	
	private AbstractLink[] links;
	public ActionLink enter_link = new ActionLink("enter", getMessage("setting"),
			this, "onEnter");
	public ActionLink edit_link = new ActionLink("edit", getMessage("modify"),
			this, "onModify");
	public ActionLink del_link = new ActionLink("delete", getMessage("delete"),
			this, "onDelete");
	
	
	/*表单项*/
	public TextField tf_code = new TextField(this.getMessage("floor_code"),true);
	public TextField tf_alias = new TextField(this.getMessage("floor_alias"),true);
	
	public FloorList(){
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
		
		table.addColumn(new Column("code",this.getMessage("floor_code")));	
		
		Column column_alias = new Column("alias", this.getMessage("floor_alias"));
//		column_alias.setDecorator(new Decorator() {
//			public String render(Object object, Context context) {
//				Floor f = (Floor) object;
//				 String id = f.getId();
//				 String alias=f.getAlias();
//		         return "<a href='rowList.htm?id="+ id + "'>" + alias + "</a>";
//			}
//		 });
		table.addColumn(column_alias);
		Column column_state=new Column("state",this.getMessage("floor_state"));
		column_state.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				Floor  f = (Floor) object;
		         return f.getState().getDescription();
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
	
	public void onRender(){
		building_id=this.getContext().getRequestParameter("id");
		if(building_id!=null){
			this.getContext().getSession().setAttribute("buildinglist_id", building_id);
		}
		title=ws.findNameById(this.getContext().getSessionAttribute("warehouselist_id").toString())+"----->"+bs.fingNameById(this.getContext().getSessionAttribute("buildinglist_id").toString());
		List<Floor> list=fs.findFloorListByBuilding_id((String)this.getContext().getSessionAttribute("buildinglist_id"));
		table.setRowList(list);
	}
	
	//保存
	public boolean onSave(){
		if(form.isValid()){
			Floor f=new Floor();
			f.setCode(tf_code.getValue().toUpperCase());
			f.setAlias(tf_alias.getValue());
			Building b=bs.findBuildingById((String)this.getContext().getSessionAttribute("buildinglist_id"));
			f.setBuilding(b);
			f.setState(fss.findFloorStateByCode(FloorState.NORMAL));
			fs.addFloor(f);
		}
		return true;
	}
	
	//重置
	public boolean onReset(){
		form.clearErrors();
		form.clearValues();
		return true;
	}
	
	//删除
	public boolean onDelete(){
		String id=del_link.getValue();
		fs.delFloorById(id);
		this.setRedirect(FloorList.class);
		return true;
	}
	//修改
	public boolean onModify(){
		String id=edit_link.getValue();
		this.setRedirect("modifyFloor.htm?id="+id);
		return true;
	}
	
	public boolean onEnter(){
		String id=enter_link.getValue();
		this.setRedirect("rowList.htm?id="+id);
		return true;
	}
	
}