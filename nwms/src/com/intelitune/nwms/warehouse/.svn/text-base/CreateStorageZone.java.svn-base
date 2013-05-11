package com.intelitune.nwms.warehouse;

import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.TextArea;
import net.sf.click.control.TextField;

import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.StorageZone;
import com.intelitune.nwms.model.ZoneState;
import com.intelitune.nwms.service.StorageZoneService;
import com.intelitune.nwms.service.StorageZoneServiceImp;
import com.intelitune.nwms.service.ZoneStateService;
import com.intelitune.nwms.service.ZoneStateServiceImp;

public class CreateStorageZone extends com.intelitune.nwms.common.BorderPage {
	Menu me = new Menu();
	
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("storagezone_position");
	
	public String title=this.getMessage("create");
	
	public ZoneStateService zss= ZoneStateServiceImp.getInstance();
	public StorageZoneService szs= StorageZoneServiceImp.getInstance();
	
	public Form form=new Form();
	
	public TextField tf_alias = new TextField(this.getMessage("zone_alias"),true);
	public TextArea tf_description = new TextArea(this.getMessage("zone_description"));
	public CreateStorageZone(){
		form.add(tf_alias);
		form.add(tf_description);
		form.add(new Submit(this.getMessage("save"),this,"onSave"));
		form.add(new Submit(this.getMessage("cancel"),this,"onCancel"));
	}
	
	public boolean onCancel(){
		this.setRedirect(ZoneList.class);
		return true;
	}
	
	public boolean onSave(){
		if(form.isValid()){
			StorageZone sz=new StorageZone();
			sz.setAlias(tf_alias.getValue());
			sz.setDescription(tf_description.getValue());
			sz.setState(zss.findZoneStateByCode(ZoneState.NORMAL));
			szs.addStorageZone(sz);
			this.setRedirect(ZoneList.class);
		}
		return true;
	}

}