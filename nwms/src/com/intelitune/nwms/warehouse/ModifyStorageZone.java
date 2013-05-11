package com.intelitune.nwms.warehouse;

import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.TextArea;
import net.sf.click.control.TextField;

import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.OwnerZone;
import com.intelitune.nwms.model.StorageZone;
import com.intelitune.nwms.service.StorageZoneService;
import com.intelitune.nwms.service.StorageZoneServiceImp;

public class ModifyStorageZone extends com.intelitune.nwms.common.BorderPage {
	
	Menu me = new Menu();
	
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("storagezone_position_modify");
	
	public String title=this.getMessage("modify");
	public Form form=new Form();
	
	public StorageZoneService szs= StorageZoneServiceImp.getInstance();
	
	public TextField tf_alias = new TextField(this.getMessage("zone_alias"),true);
	public TextArea tf_description = new TextArea(this.getMessage("zone_description"));
	
	public ModifyStorageZone(){
		form.add(tf_alias);
		form.add(tf_description);
		form.add(new Submit(this.getMessage("modify"),this,"onModify"));
		form.add(new Submit(this.getMessage("cancel"),this,"onCancel"));
		
	}
	
	public void onRender(){
		String storagezone_id=(String)this.getContext().getSessionAttribute("modify_storagezone_id");
		StorageZone sz1=szs.findStorageZoneById(storagezone_id);
		tf_alias.setValue(sz1.getAlias());
		tf_description.setValue(sz1.getDescription());
	}
	
	
	public boolean onCancel(){
		this.setRedirect(ZoneList.class);
		return true;
	}
	
	public boolean onModify(){
		StorageZone sz2=szs.findStorageZoneById((String)this.getContext().getSessionAttribute("modify_storagezone_id"));
		sz2.setAlias(tf_alias.getValue());
		sz2.setDescription(tf_description.getValue());
		szs.modifyStorageZone(sz2);
		this.setRedirect(ZoneList.class);
		return true;
	}

}