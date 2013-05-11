package com.intelitune.nwms.warehouse;

import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;

import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Floor;
import com.intelitune.nwms.service.FloorService;
import com.intelitune.nwms.service.FloorServiceImp;

public class ModifyFloor extends com.intelitune.nwms.common.BorderPage {
	Menu me = new Menu();
	
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("floor_position_modify");
	
	public String title;
	
	public String floor_title =this.getMessage("modify");
	
	public Form form=new Form();
	
	public Floor f;
	
	public FloorService fs= FloorServiceImp.getInstance();
	
	/*当前floor的ID*/
	public String id=this.getContext().getRequestParameter("id");
	
	/*表单项*/
	public TextField tf_code = new TextField(this.getMessage("floor_code"),true);
	public TextField tf_alias = new TextField(this.getMessage("floor_alias"),true);
	
	public ModifyFloor(){
		this.setStateful(true);
		form.setErrorsPosition(Form.POSITION_TOP);
		form.add(tf_code);
		form.add(tf_alias);
		form.add(new Submit(this.getMessage("modify"),this,"onModify"));
		form.add(new Submit(this.getMessage("cancel"),this,"onCancel"));
	}

	public void onRender(){
		f=fs.findFloorById(id);
		tf_code.setValue(f.getCode());
		tf_alias.setValue(f.getAlias());
		title=f.getAlias()+this.getMessage("floor_setting");
	}
	
	//修改
	public boolean onModify(){
		if(form.isValid()){
			f.setCode(tf_code.getValue().toUpperCase());
			f.setAlias(tf_alias.getValue());
			fs.modifyFloor(f);
			this.setRedirect(FloorList.class);
		}
		return true;
	}
	
	public boolean onCancel(){
		this.setRedirect(FloorList.class);
		return true;
	}
}