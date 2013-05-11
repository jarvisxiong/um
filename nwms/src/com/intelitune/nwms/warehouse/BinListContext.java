package com.intelitune.nwms.warehouse;

import java.util.List;

import net.sf.click.control.Form;
import net.sf.click.control.HiddenField;
import net.sf.click.control.PageLink;
import net.sf.click.control.Submit;

import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.common.Const;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Bin;
import com.intelitune.nwms.service.BinService;
import com.intelitune.nwms.service.BinServiceImp;
import com.intelitune.nwms.service.BuildingService;
import com.intelitune.nwms.service.BuildingServiceImp;
import com.intelitune.nwms.service.ColumnService;
import com.intelitune.nwms.service.ColumnServiceImp;
import com.intelitune.nwms.service.FloorService;
import com.intelitune.nwms.service.FloorServiceImp;
import com.intelitune.nwms.service.RowService;
import com.intelitune.nwms.service.RowServiceImp;
import com.intelitune.nwms.service.TierService;
import com.intelitune.nwms.service.TierServiceImp;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;

public class BinListContext extends BorderPage  {
	Menu me = new Menu();
	public String menuInclude = me.getWarehouse();

	public String position = this.getMessage("bin_position");

	public PageLink pageLink1 = new PageLink("bin_return", this
			.getMessage("return"), RowList.class);
	
	public String title;
	public String row_id;
	
	public RowService rs =  RowServiceImp.getInstance();
	public FloorService fs= FloorServiceImp.getInstance();
	public WarehouseService ws= WarehouseServiceImp.getInstance();
	public BuildingService bbs= BuildingServiceImp.getInstance();

	
	public BinListContext(){
		
		
		
	}
	
	public void onRender(){
		row_id = this.getContext().getRequestParameter("id");
		if (row_id != null) {
			this.getContext().getSession().setAttribute("rowlist_id", row_id);
		}
		title = ws.findNameById(this.getContext().getSessionAttribute("warehouselist_id").toString())+"----->"+bbs.fingNameById(this.getContext().getSessionAttribute("buildinglist_id").toString())+"----->"+fs.findNameById(this.getContext().getSessionAttribute("floorlist_id").toString())+"----->"+rs.findNameById(this.getContext().getSessionAttribute(
				"rowlist_id").toString());
	}
	
	


}