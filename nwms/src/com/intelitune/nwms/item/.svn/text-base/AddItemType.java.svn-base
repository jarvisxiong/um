package com.intelitune.nwms.item;

import java.util.List;

import net.sf.click.Context;
import net.sf.click.control.AbstractLink;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Decorator;
import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.TextArea;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.LinkDecorator;
import net.sf.click.extras.control.TableInlinePaginator;

import com.intelitune.control.TableEx;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.ItemType;
import com.intelitune.nwms.model.ItemTypeState;
import com.intelitune.nwms.service.ItemTypeService;
import com.intelitune.nwms.service.ItemTypeServiceImpl;
import com.intelitune.nwms.service.ItemTypeStateService;
import com.intelitune.nwms.service.ItemTypeStateServiceImp;

public class AddItemType extends com.intelitune.nwms.common.BorderPage {
	Menu me= new Menu();
	public String menuInclude= me.getSystemSetting();
	
	public String title=this.getMessage("add_itemtype");
	public String create=this.getMessage("create");
	
	public ItemTypeService its=ItemTypeServiceImpl.getInstance();
	public ItemTypeStateService itss=ItemTypeStateServiceImp.getInstance();
	
	public TableEx table=new TableEx();
	public Form form=new Form();
	
	private AbstractLink[] links;
	public ActionLink edit_link = new ActionLink("edit", getMessage("modify"),
			this, "onModify");
	public ActionLink del_link = new ActionLink("delete", getMessage("delete"),
			this, "onDelete");
	
	
	public TextField code = new TextField("code",this.getMessage("code"));
	public TextField alias = new TextField("alias",this.getMessage("alias"),true);
	public TextArea remark=new TextArea("remark",getMessage("remark"));
	
	public AddItemType(){
		form.add(code);
		form.add(alias);
		form.add(remark);
		form.add(new Submit(this.getMessage("save"),this,"onSave"));
		form.add(new Submit(this.getMessage("reset"),this,"onReset"));
		
		
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		table.setPageSize(10);
		
		table.addColumn(new Column("code",this.getMessage("code")));
		table.addColumn(new Column("alias",this.getMessage("alias")));
		Column column_state=new Column("state",this.getMessage("state"));
		column_state.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				ItemType it= (ItemType) object;
		        return it.getState().getDescription();
			}
		 });
		table.addColumn(column_state);
		Column column = new Column("action",this.getMessage("edit"));
		column.setTextAlign("center");
		links = new AbstractLink[] { edit_link, del_link };
		column.setDecorator(new LinkDecorator(table, links, "id"));
		table.addColumn(column);

		
		del_link.setAttribute("onclick", "return window.confirm('"
				+getMessage("confirm_delete")+"?" + "');");
		
	}
	
	public void onRender(){
		List<ItemType> list=its.findItemTypeList();
		table.setRowList(list);
	}
	
	public boolean onReset()
	{
		form.clearErrors();
		form.clearValues();
		return true;
	}
	
	
	public boolean onSave(){
		if(form.isValid()){
			ItemType it=new ItemType();
			it.setCode(code.getValue());
			it.setAlias(alias.getValue());
			it.setRemark(remark.getValue());
			it.setState(itss.findItemTypeStateByCode((ItemTypeState.NORMAL)));
			its.addItemType(it);
			this.setRedirect(AddItemType.class);
		}
		return true;
	}
	
	public boolean onDelete(){
		String id=del_link.getValue();
		its.delItemTypeById(id);
		this.setRedirect(AddItemType.class);
		return true;
	}
	
	public boolean onModify(){
		String id=edit_link.getValue();
		this.setRedirect("modifyItemType.htm?id="+id);
		return true;
		
	}
	
}