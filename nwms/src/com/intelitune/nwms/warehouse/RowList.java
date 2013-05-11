package com.intelitune.nwms.warehouse;

import java.util.ArrayList;
import java.util.List;

import net.sf.click.Context;
import net.sf.click.control.AbstractLink;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Decorator;
import net.sf.click.control.Form;
import net.sf.click.control.PageLink;
import net.sf.click.control.Submit;
import net.sf.click.extras.control.LinkDecorator;
import net.sf.click.extras.control.TableInlinePaginator;

import com.intelitune.control.TableEx;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Bin;
import com.intelitune.nwms.model.OwnerZone;
import com.intelitune.nwms.model.Row;
import com.intelitune.nwms.model.StorageZone;
import com.intelitune.nwms.model.Zone;
import com.intelitune.nwms.service.BinService;
import com.intelitune.nwms.service.BinServiceImp;
import com.intelitune.nwms.service.BuildingService;
import com.intelitune.nwms.service.BuildingServiceImp;
import com.intelitune.nwms.service.FloorService;
import com.intelitune.nwms.service.FloorServiceImp;
import com.intelitune.nwms.service.RowService;
import com.intelitune.nwms.service.RowServiceImp;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;

public class RowList extends com.intelitune.nwms.common.BorderPage {
	Menu me = new Menu();
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("row_position");
	
	public String floor_title=this.getMessage("create");
	
	public PageLink pageLink = new PageLink("pageLink",this.getMessage("create_row"), CreateRow.class);
	
	public PageLink pageLink1=new PageLink("row_return",this.getMessage("return"), FloorList.class);
	
	public String title;
	
	public TableEx table=new TableEx();
	
	public String floor_id;
	
	public FloorService fs= FloorServiceImp.getInstance();
	public RowService rs= RowServiceImp.getInstance();
	public WarehouseService ws= WarehouseServiceImp.getInstance();
	public BuildingService bs= BuildingServiceImp.getInstance();
	public BinService bbs= BinServiceImp.getInstance();
	
	private AbstractLink[] links;
	public ActionLink enter_link = new ActionLink("enter", getMessage("setting"),
			this, "onEnter");
	public ActionLink edit_link = new ActionLink("edit", getMessage("modify"),
			this, "onModify");
	public ActionLink del_link = new ActionLink("delete", getMessage("delete"),
			this, "onDelete");
	
	public Form form=new Form();
	
	
	
	public RowList(){
		/*数据列表*/
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		table.setPageSize(10);
		table.addColumn(new Column("code",this.getMessage("row_code")));
		table.addColumn(new Column("alias",this.getMessage("row_alias")));
		

		Column column_state=new Column("state",this.getMessage("row_state"));
		column_state.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				Row  r = (Row) object;
		         return r.getState().getDescription();
			}
		 });
		table.addColumn(column_state);
		
		Column column_zone=new Column("zone",this.getMessage("row_property"));
		column_zone.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				String result="";
				Row  r = (Row) object;
				List<Bin> list=bbs.findBinByRow_id(r.getId());
				List<String> str=new ArrayList<String>();
				for(int i=0;i<list.size();i++){
					List<Zone> zones=list.get(i).getZones();
					for(int j=0;j<zones.size();j++){
						Zone z=zones.get(j);
						int k=0;
						for(;k<str.size();k++){
							String string=str.get(k);
							if(z.getAlias().trim().equals(string.trim())){
								break;
							}
						}
						if(k==str.size()){
							str.add(z.getAlias());
						}
					}
				}
				for(int i=0;i<str.size();i++){
					result+=str.get(i)+",";
				}
		         return result;
			}
		 });
		table.addColumn(column_zone);
		
		
		
		Column column = new Column("action",this.getMessage("edit"));
		column.setTextAlign("center");
		links = new AbstractLink[] {enter_link, edit_link, del_link };
		column.setDecorator(new LinkDecorator(table, links, "id"));
		table.addColumn(column);
		
		del_link.setAttribute("onclick", "return window.confirm('"
				+getMessage("confirm_delete")+"?" + "');");
		
		Submit  submit= new Submit("submit",this.getMessage("create_row"),this,"onCreate");
		form.add(submit);
	}
		
		
	

	public void onRender(){
		floor_id=this.getContext().getRequestParameter("id");
		if(floor_id!=null){
			this.getContext().getSession().setAttribute("floorlist_id", floor_id);
		}
		title=ws.findNameById(this.getContext().getSessionAttribute("warehouselist_id").toString())+"----->"+bs.fingNameById(this.getContext().getSessionAttribute("buildinglist_id").toString())+"----->"+fs.findNameById(this.getContext().getSessionAttribute("floorlist_id").toString());
		List list=rs.findRowListByFloor_id(this.getContext().getSessionAttribute("floorlist_id").toString());
		table.setRowList(list);
	}
	


	public boolean onDelete(){
		String id=del_link.getValue();
		rs.delRowById(id);
		return true;
	}
	
	public boolean onModify(){
		String id=edit_link.getValue();
		this.setRedirect("modifyRow.htm?id="+id);
		return true;
	}
	
	public boolean onEnter(){
		String id=enter_link.getValue();
		this.setRedirect("binListContext.htm?id="+id);
		return true;
	}
	
	public boolean onCreate(){
		this.setRedirect( CreateRow.class);
		return true;
	}
	
}