package com.intelitune.nwms.warehouse;

import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.IntegerField;

import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Warehouse;
import com.intelitune.nwms.service.WarehouseService;
import com.intelitune.nwms.service.WarehouseServiceImp;

public class ModifyWarehouse extends com.intelitune.nwms.common.BorderPage {
	Menu me = new Menu();
	
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("warehouse_position_modify");
	
	public String title=this.getMessage("warehouse_manage");
	
	public WarehouseService wsi= WarehouseServiceImp.getInstance();
	
	public String warehouse_title =this.getMessage("modify");
	
	public Form form = new Form();
	
	public String id=this.getContext().getRequestParameter("id");
	
	public Warehouse w;
	
	/*表单项*/
	public TextField tf_code = new TextField("code",this.getMessage("warehouse_code"),true);
	public TextField tf_alias = new TextField("alias",this.getMessage("warehouse_alias"),true);
	public TextField tf_address = new TextField("address",this.getMessage("warehouse_address"));
	public TextField tf_phone = new TextField("phone",this.getMessage("warehouse_phone"));
	public IntegerField tf_zip = new IntegerField ("zip",this.getMessage("warehouse_zip"));
	public TextField tf_area = new TextField("area",this.getMessage("warehouse_area"));
	public TextField tf_city = new TextField("city",this.getMessage("warehouse_city"));
	public ModifyWarehouse(){
		this.setStateful(true);
		form.setErrorsPosition(Form.POSITION_TOP);
		form.setColumns(3);
		form.add(tf_code,3);
		form.add(tf_alias,3);
		form.add(tf_phone,3);
		form.add(tf_zip,3);
		form.add(tf_area);
		form.add(tf_city);
		form.add(tf_address);
		form.add(new Submit(this.getMessage("modify"),this,"onModify"));
		form.add(new Submit(this.getMessage("cancel"),this,"onCancel"));
		w=wsi.findWarehouseById(id);
		
		
	}
	@Override
	public void onInit()
	{
	}
	public void onRender() {
		w=wsi.findWarehouseById(id);
		tf_code.setValue(w.getCode());
		tf_alias.setValue(w.getAlias());
		tf_address.setValue(w.getAddress());
		tf_phone.setValue(w.getPhone());
		tf_zip.setValue(w.getZip());
		tf_area.setValue(w.getArea());
		tf_city.setValue(w.getCity());
	}
	
	public void onGet(){
	
	}
	
	public boolean onModify(){	
		if(form.isValid()){
			w.setAlias(tf_alias.getValue());
			w.setCode(tf_code.getValue().toUpperCase());
			
			w.setAddress(tf_address.getValue());
			w.setPhone(tf_phone.getValue());
			w.setZip(tf_zip.getValue());
			w.setArea(tf_area.getValue());
			w.setCity(tf_city.getValue());
			wsi.modifyWarehouse(w);
			this.setRedirect(WarehouseList.class);
		}
		return true;
	}
	
	public boolean onCancel()
	{
		this.setRedirect(WarehouseList.class);
		return true;
	}
	
}