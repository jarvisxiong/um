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
import com.intelitune.nwms.model.ZoneState;
import com.intelitune.nwms.service.OwnerZoneService;
import com.intelitune.nwms.service.OwnerZoneServiceImp;
import com.intelitune.nwms.service.ZoneStateService;
import com.intelitune.nwms.service.ZoneStateServiceImp;

public class CreateOwnerZone extends com.intelitune.nwms.common.BorderPage {
	Menu me = new Menu();
	
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("ownerzone_position");
	
	public String title=this.getMessage("create");
	
	public Form form=new Form();
	
	public Select ownerSelect;
	public TextField tf_alias = new TextField(this.getMessage("zone_alias"),true);
	
	public OwnerZoneService ozs= OwnerZoneServiceImp.getInstance();
	
	public ZoneStateService zss= ZoneStateServiceImp.getInstance();
	
	public CreateOwnerZone(){
		ownerSelect = new Select(this.getMessage("zone_customer_name"));
		ownerSelect.setRequired(true);
		form.add(ownerSelect);
		form.add(tf_alias);
		form.add(new Submit(this.getMessage("save"),this,"onSave"));
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
	
	public boolean onCancel(){
		this.setRedirect(ZoneList.class);
		return true;
	}
	
	public boolean onSave(){
		if(form.isValid()){
			OwnerZone oz=new OwnerZone();
			oz.setAlias(tf_alias.getValue());
			oz.setState(zss.findZoneStateByCode(ZoneState.NORMAL));
			oz.setOwner(ozs.findOwnerById(Integer.parseInt(ownerSelect.getValue())));
			//oz.setInttClientDetailWSId(ownerSelect.getValue());
			ozs.addOwnerZone(oz);
			this.setRedirect(ZoneList.class);
		}
		return true;
	}

}