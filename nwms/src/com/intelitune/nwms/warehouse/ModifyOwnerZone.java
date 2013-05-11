package com.intelitune.nwms.warehouse;

import java.util.Iterator;
import java.util.List;

import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;

import com.axis2.services.MCSserviceStub.InttClientDetailWS;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.OwnerZone;
import com.intelitune.nwms.service.OwnerZoneService;
import com.intelitune.nwms.service.OwnerZoneServiceImp;
import com.intelitune.nwms.service.ZoneStateService;
import com.intelitune.nwms.service.ZoneStateServiceImp;

public class ModifyOwnerZone extends com.intelitune.nwms.common.BorderPage {
	Menu me = new Menu();
	
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("ownerzone_position_modify");
	
	public String title=this.getMessage("modify");
	
	public Form form=new Form();
	
	public Select ownerSelect;
	public TextField tf_alias = new TextField(this.getMessage("zone_alias"),true);
	
	public OwnerZoneService ozs= OwnerZoneServiceImp.getInstance();
	
	public ZoneStateService zss= ZoneStateServiceImp.getInstance();
	
	public ModifyOwnerZone(){
		ownerSelect = new Select(this.getMessage("zone_customer_name"));
		ownerSelect.setRequired(true);
		form.add(ownerSelect);
		form.add(tf_alias);
		form.add(new Submit(this.getMessage("modify"),this,"onModify"));
		form.add(new Submit(this.getMessage("cancel"),this,"onCancel"));
	}
	
	public void onInit(){
		super.onInit();
		List<InttClientDetailWS> list=ozs.findOwnerList();
		ownerSelect.add(new Option(""));
	        for (Iterator<InttClientDetailWS> i = list.iterator(); i.hasNext();) {
	        	InttClientDetailWS icd = (InttClientDetailWS) i.next();
	        	ownerSelect.add(new Option(icd.getId(),icd.getCnName()));
	        }
	   
	}
	
	public void onRender(){
		String ownerzone_id=(String)this.getContext().getSessionAttribute("modify_ownerzone_id");
		OwnerZone oz1=ozs.findOwnerZoneById(ownerzone_id);
		ownerSelect.setValue(oz1.getOwner().getId()+"");
		 tf_alias.setValue(oz1.getAlias());
	}
	
	public boolean onCancel(){
		this.setRedirect(ZoneList.class);
		return true;
	}
	
	public boolean onModify(){
		OwnerZone oz2=ozs.findOwnerZoneById((String)this.getContext().getSessionAttribute("modify_ownerzone_id"));
		oz2.setAlias(tf_alias.getValue());
		oz2.setOwner(ozs.findOwnerById(Integer.parseInt(ownerSelect.getValue())));
		ozs.modifyOwnerZone(oz2);
		this.setRedirect(ZoneList.class);
		return true;
	}
}