package com.intelitune.nwms.warehouse;

import java.util.ArrayList;
import java.util.List;

import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.IntegerField;
import net.sf.click.extras.control.PickList;

import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.OwnerZone;
import com.intelitune.nwms.model.Row;
import com.intelitune.nwms.model.RowState;
import com.intelitune.nwms.model.StorageZone;
import com.intelitune.nwms.model.Zone;
import com.intelitune.nwms.service.FloorService;
import com.intelitune.nwms.service.FloorServiceImp;
import com.intelitune.nwms.service.OwnerZoneService;
import com.intelitune.nwms.service.OwnerZoneServiceImp;
import com.intelitune.nwms.service.RowService;
import com.intelitune.nwms.service.RowServiceImp;
import com.intelitune.nwms.service.RowStateService;
import com.intelitune.nwms.service.RowStateServiceImp;
import com.intelitune.nwms.service.StorageZoneService;
import com.intelitune.nwms.service.StorageZoneServiceImp;
import com.intelitune.nwms.service.ZoneService;
import com.intelitune.nwms.service.ZoneServiceImp;

public class CreateRow extends com.intelitune.nwms.common.BorderPage {
	
	Menu me = new Menu();
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("row_new_position");
	
	public String floor_title=this.getMessage("create");
	
	public String title=this.getMessage("create_row");
	
	public Form form =new Form();
	
	public List<Zone> selected=new ArrayList<Zone>();
	
	
	
	public RowService rs= RowServiceImp.getInstance();
	public RowStateService rss= RowStateServiceImp.getInstance();
	public FloorService fs= FloorServiceImp.getInstance();
	public OwnerZoneService ozs= OwnerZoneServiceImp.getInstance();
	public StorageZoneService szs= StorageZoneServiceImp.getInstance();
	
	public ZoneService zs= ZoneServiceImp.getInstance();
	public List<OwnerZone> list1;
	public List<StorageZone> list2;
	
	public TextField tf_code = new TextField(this.getMessage("row_code"),true);
	public TextField tf_alias = new TextField(this.getMessage("row_alias"),true);
	public IntegerField tf_tier = new IntegerField(this.getMessage("row_tier"),true);
	public IntegerField tf_column = new IntegerField(this.getMessage("row_column"),true);
	
	public PickList pickList = new PickList("picklist",this.getMessage("zone_select"));

	public CreateRow(){
		list1=ozs.findOwnerZoneList();
		list2=szs.findStorageZoneList();
		form.setErrorsPosition(Form.POSITION_TOP);
		form.setColumns(2);
		form.add(tf_code,2);
		form.add(tf_alias,2);
		form.add(tf_tier);
		form.add(tf_column);
        pickList.setHeaderLabel("Zone", "Selected");
        for(int i=0;i<list1.size();i++){
        	OwnerZone o = (OwnerZone) list1.get(i);
        	pickList.add(new Option("o"+o.getId(), o.getAlias()));
        }
        for(int i=0;i<list2.size();i++){
        	StorageZone s = (StorageZone) list2.get(i);
        	pickList.add(new Option("s"+s.getId(), s.getAlias()));
        }
        form.add(pickList);
		form.add(new Submit(this.getMessage("save"),this,"onSave"));
		form.add(new Submit(this.getMessage("reset"),this,"onReset"));
	}
	
	public void onRender(){
		
	}
	
	
	public boolean onSave(){
		if(form.isValid()){
			List picklist=pickList.getSelectedValues();
			for(int i=0;i<picklist.size();i++){
				if(picklist.get(i)!=null){
//					selected.add(zs.findZoneById((String)picklist.get(i)));
					String zid=picklist.get(i).toString().trim();
					if(zid.startsWith("o")){
						String id=zid.substring(1,zid.length());
						selected.add(ozs.findOwnerZoneById(id));
					}
					if(zid.startsWith("s")){
						String id=zid.substring(1,zid.length());
						selected.add(szs.findStorageZoneById(id));
					}
				}
			}
			Row r=new Row();
			r.setCode(tf_code.getValue().toUpperCase());
			r.setAlias(tf_alias.getValue());
			r.setState(rss.findRowStateByCode(RowState.NORMAL));
			r.setFloor(fs.findFloorById((String)this.getContext().getSessionAttribute("floorlist_id")));
			
			
			rs.addRow(r, Integer.parseInt(tf_tier.getValue()), Integer.parseInt(tf_column.getValue()),selected);
			this.setRedirect(RowList.class);
		}
		return true;
	}
	
	public boolean onReset(){
		form.clearErrors();
		form.clearValues();
		return true;
	}

}