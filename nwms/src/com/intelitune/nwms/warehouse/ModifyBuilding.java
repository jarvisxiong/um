package com.intelitune.nwms.warehouse;

import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;

import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Building;
import com.intelitune.nwms.service.BuildingService;
import com.intelitune.nwms.service.BuildingServiceImp;

public class ModifyBuilding extends com.intelitune.nwms.common.BorderPage {
	
	Menu me = new Menu();
	
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("building_position_modify");
	
	public String title;
	
	public String building_title =this.getMessage("modify");
	
	public Form form=new Form();
	
	public Building b;
	
	public BuildingService bs= BuildingServiceImp.getInstance();
	
	/*当前building的ID*/
	public String id=this.getContext().getRequestParameter("id");
	
	/*表单项*/
	public TextField tf_code = new TextField(this.getMessage("building_code"),true);
	public TextField tf_alias = new TextField(this.getMessage("building_alias"),true);
	
	public ModifyBuilding(){
		this.setStateful(true);
		form.setErrorsPosition(Form.POSITION_TOP);
		form.add(tf_code);
		form.add(tf_alias);
		form.add(new Submit(this.getMessage("modify"),this,"onModify"));
		form.add(new Submit(this.getMessage("cancel"),this,"onCancel"));
	}

	@Override
	public void onRender() {
		b=bs.findBuildingById(id);
		tf_code.setValue(b.getCode());
		tf_alias.setValue(b.getAlias());
		title=b.getAlias()+this.getMessage("building_setting");
	}

	public boolean onModify(){
		if(form.isValid()){
			b.setCode(tf_code.getValue().toUpperCase());
			b.setAlias(tf_alias.getValue());
			bs.modifyBuilding(b);
			this.setRedirect(BuildingList.class);
		}
		return true;
	}
	
	public boolean onCancel(){
		this.setRedirect(BuildingList.class);
		return true;
	}
	
}