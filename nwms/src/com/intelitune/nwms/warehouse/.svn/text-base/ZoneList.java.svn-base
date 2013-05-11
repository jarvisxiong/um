package com.intelitune.nwms.warehouse;

import java.util.Iterator;
import java.util.List;

import net.sf.click.Context;
import net.sf.click.control.AbstractLink;
import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Decorator;
import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.PageLink;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.control.TextArea;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.LinkDecorator;
import net.sf.click.extras.control.TableInlinePaginator;

import com.axis2.services.MCSserviceStub.InttClientDetailWS;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.OwnerZone;
import com.intelitune.nwms.model.StorageZone;
import com.intelitune.nwms.model.ZoneState;
import com.intelitune.nwms.service.OwnerZoneService;
import com.intelitune.nwms.service.OwnerZoneServiceImp;
import com.intelitune.nwms.service.StorageZoneService;
import com.intelitune.nwms.service.StorageZoneServiceImp;
import com.intelitune.nwms.service.ZoneStateService;
import com.intelitune.nwms.service.ZoneStateServiceImp;

public class ZoneList extends com.intelitune.nwms.common.BorderPage {
	Menu me = new Menu();
	
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("zonelist_position");
	
	public String title=this.getMessage("create_ownerzone");
	public String title1=this.getMessage("create_storagezone");
	public TableEx table1=new TableEx();
	public TableEx table2=new TableEx();
	
	public OwnerZoneService ozs= OwnerZoneServiceImp.getInstance();
	public StorageZoneService szs= StorageZoneServiceImp.getInstance();
	
	private AbstractLink[] links1;
	public ActionLink edit_link1 = new ActionLink("edit1", getMessage("modify"),
			this, "onModifyOwner");
	public ActionLink del_link1 = new ActionLink("delete1", getMessage("delete"),
			this, "onDeleteOwner");
	
	private AbstractLink[] links2;
	public ActionLink edit_link2 = new ActionLink("edit2", getMessage("modify"),
			this, "onModifyStorage");
	public ActionLink del_link2 = new ActionLink("delete2", getMessage("delete"),
		this, "onDeleteStorage");
	
//	public PageLink pageLink1 = new PageLink("pageLink1",this.getMessage("create_ownerzone"), CreateOwnerZone.class);
//	public PageLink pageLink2= new PageLink("pageLink2",this.getMessage("create_storagezone"), CreateStorageZone.class);
	
	
	
	public Form form=new Form();
	
	public Select ownerSelect;
	public TextField tf_alias = new TextField("alias",this.getMessage("zone_alias"),true);
	
	
	
	public ZoneStateService zss= ZoneStateServiceImp.getInstance();
	
	
	
	public Form form1=new Form();
	
	public TextField tf_alias1 = new TextField("alias",this.getMessage("zone_alias"),true);
	public TextArea tf_description1 = new TextArea(this.getMessage("zone_description"));
	
	
	
	
	public ZoneList(){
		
		ownerSelect = new Select(this.getMessage("zone_customer_name"));
		ownerSelect.setRequired(true);
		form.add(ownerSelect);
		form.add(tf_alias);
		form.add(new Submit(this.getMessage("save"),this,"onSave"));
//		form.add(new Submit(this.getMessage("cancel"),this,"onCancel"));
		
		
		
		//货主区域表
		table1.setWidth("100%");
		table1.setPaginator(new TableInlinePaginator(table1));
		table1.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		table1.setPageSize(10);
		Column al=new Column("alias",this.getMessage("zone_alias"));
		al.setWidth("35%");
		table1.addColumn(al);	
		
		Column column_company=new Column("state",this.getMessage("company_name"));
		column_company.setWidth("35%");
		column_company.setDecorator(new Decorator() {
			public String render(Object object, Context context) {
				OwnerZone  oz = (OwnerZone) object;
		         return oz.getOwner().getCnName();
			}
		 });
		table1.addColumn(column_company);
		
		Column column1= new Column("action",this.getMessage("edit"));
		column1.setTextAlign("center");
		column1.setWidth("%30");
		links1 = new AbstractLink[] { edit_link1, del_link1 };
		column1.setDecorator(new LinkDecorator(table1, links1, "id"));
		table1.addColumn(column1);

		del_link1.setAttribute("onclick", "return window.confirm('"
				+getMessage("confirm_delete")+"?" + "');");
		
		form1.add(tf_alias1);
		form1.add(tf_description1);
		form1.add(new Submit(this.getMessage("save"),this,"onSave1"));
//		form1.add(new Submit(this.getMessage("cancel"),this,"onCancel1"));
		
		//储存区域表
		table2.setWidth("100%");
		table2.setPaginator(new TableInlinePaginator(table2));
		table2.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		table2.setPageSize(10);
		Column al1=new Column("alias",this.getMessage("zone_alias"));
		al1.setWidth("35%");
		table2.addColumn(al1);
		Column col=new Column("description",this.getMessage("zone_description"));
		col.setWidth("35%");
		table2.addColumn(col);
		Column column2 = new Column("action",this.getMessage("edit"));
		column2.setTextAlign("center");
		column2.setWidth("30%");
		links2 = new AbstractLink[] { edit_link2, del_link2 };
		column2.setDecorator(new LinkDecorator(table2, links2, "id"));
		table2.addColumn(column2);

		del_link2.setAttribute("onclick", "return window.confirm('"
				+getMessage("confirm_delete")+"?" + "');");
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
		List<OwnerZone> list1=ozs.findOwnerZoneList();
		table1.setRowList(list1);
		List<StorageZone> list2=szs.findStorageZoneList();
		table2.setRowList(list2);
	}
	
	//删除货主区域
	public boolean onDeleteOwner(){
		String id=del_link1.getValue();
		ozs.delOwnerZone(id);
		this.setRedirect(ZoneList.class);
		return true;
	}
	//修改货主区域
	public boolean onModifyOwner(){
		String id=edit_link1.getValue();
		this.getContext().setSessionAttribute("modify_ownerzone_id", id);
		this.setRedirect("modifyOwnerZone.htm");
		return true;
	}
	
	
	//删除存储区域
	public boolean onDeleteStorage(){
		String id=del_link2.getValue();
		szs.delStorageZoneById(id);
		this.setRedirect(ZoneList.class);
		return true;
	}
	
	//修改存储区域
	public boolean onModifyStorage(){
		String id=edit_link2.getValue();
		this.getContext().setSessionAttribute("modify_storagezone_id", id);
		this.setRedirect("modifyStorageZone.htm");
		return true;
	}
	
	public boolean onCancel(){
		form.clearErrors();
		form.clearValues();
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
	
	

	public boolean onCancel1(){
		form1.clearErrors();
		form1.clearValues();
		return true;
	}
	
	public boolean onSave1(){
		if(form1.isValid()){
			StorageZone sz=new StorageZone();
			sz.setAlias(tf_alias1.getValue());
			sz.setDescription(tf_description1.getValue());
			sz.setState(zss.findZoneStateByCode(ZoneState.NORMAL));
			szs.addStorageZone(sz);
			this.setRedirect(ZoneList.class);
		}
		return true;
	}
	
}