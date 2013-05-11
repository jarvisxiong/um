package com.intelitune.nwms.warehouse;

import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;

import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Row;
import com.intelitune.nwms.service.RowService;
import com.intelitune.nwms.service.RowServiceImp;

public class ModifyRow extends com.intelitune.nwms.common.BorderPage {
	Menu me = new Menu();
	
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("row_position_modify");
	
	public String title;
	
	public String row_title =this.getMessage("modify");
	
	public Form form =new Form();
	/*当前row的ID*/
	public String id=this.getContext().getRequestParameter("id");
	
	public Row r;
	public RowService rs= RowServiceImp.getInstance();
	/*表单项*/
	public TextField tf_code = new TextField(this.getMessage("row_code"),true);
	public TextField tf_alias = new TextField(this.getMessage("row_alias"),true);
	
	public ModifyRow(){
		this.setStateful(true);
		form.setErrorsPosition(Form.POSITION_TOP);
		form.add(tf_code);
		form.add(tf_alias);
		form.add(new Submit(this.getMessage("modify"),this,"onModify"));
		form.add(new Submit(this.getMessage("cancel"),this,"onCancel"));
	}
	
	public void onRender(){
		r=rs.findRowById(id);
		tf_code.setValue(r.getCode().toUpperCase());
		tf_alias.setValue(r.getAlias());
		title=r.getAlias()+this.getMessage("row_setting");
	}
	
	public boolean onCancel(){
		this.setRedirect(RowList.class);
		return true;
	}
	
	//修改
	public boolean onModify(){
		r.setCode(tf_code.getValue().toUpperCase());
		r.setAlias(tf_alias.getValue());
		rs.modifyRow(r);
		this.setRedirect(RowList.class);
		return true;
	}
}