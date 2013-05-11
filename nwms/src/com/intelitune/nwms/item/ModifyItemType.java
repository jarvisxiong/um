package com.intelitune.nwms.item;

import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;

import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.ItemType;
import com.intelitune.nwms.service.ItemTypeService;
import com.intelitune.nwms.service.ItemTypeServiceImpl;
import com.intelitune.nwms.warehouse.RowList;

public class ModifyItemType extends com.intelitune.nwms.common.BorderPage {
	Menu me= new Menu();
	public String menuInclude= me.getSystemSetting();
	public String title=this.getMessage("modify_itemtype");
	public String modify=this.getMessage("modify");
	public String id=this.getContext().getRequestParameter("id");
	
	public ItemTypeService its=ItemTypeServiceImpl.getInstance();
	
	public ItemType it;
	
	public Form form=new Form();
	
	public TextField code = new TextField(this.getMessage("code"));
	public TextField alias = new TextField(this.getMessage("alias"),true);
	public TextField remark = new TextField(this.getMessage("remark"),true);
	
	public ModifyItemType(){
		this.setStateful(true);
		form.add(code);
		form.add(alias);
		form.add(remark);
		form.add(new Submit(this.getMessage("modify"),this,"onModify"));
		form.add(new Submit(this.getMessage("cancel"),this,"onCancel"));
	}
	
	public void onRender(){
		it=its.findItemTypeById(id);
		code.setValue(it.getCode());
		alias.setValue(it.getAlias());
		remark.setValue(it.getRemark());
	}

	
	public boolean onModify(){
		it.setCode(code.getValue());
		it.setAlias(alias.getValue());
		it.setRemark(remark.getValue());
		its.modifyItemType(it);
		this.setRedirect(AddItemType.class);
		return true;
	}
	
	public boolean onCancel(){
		this.setRedirect(AddItemType.class);
		return true;
	}
}