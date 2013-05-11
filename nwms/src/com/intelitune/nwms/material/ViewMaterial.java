package com.intelitune.nwms.material;

import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.extras.control.LinkDecorator;
import net.sf.click.extras.control.TableInlinePaginator;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Material;
import com.intelitune.nwms.service.MCSService;
import com.intelitune.nwms.service.MaterialService;
import com.intelitune.nwms.service.MaterialServiceImpl;
import com.intelitune.nwms.service.UnitPackageService;
import com.intelitune.nwms.service.UnitPackageServiceImpl;
/**
 * 
 * @author Louis
 * 查看某个materila，table中列出该Material中所有的UnitPackage
 *
 */
public class ViewMaterial extends BorderPage {

	public String value  = this.getMessage("value");
	public String owner = this.getMessage("owner");
	public String description = this.getMessage("description");
	public String remark = this.getMessage("remark");
	public String alias = this.getMessage("zwalias");
	public String code = this.getMessage("code");
	public MaterialService ms =  MaterialServiceImpl.getInstance();
	public UnitPackageService upps =  UnitPackageServiceImpl.getInstance();
	public MCSService mcs = MCSService.getInstance();
	public String title = this.getMessage("viewmaterial");
	public String position = this.getMessage("viewMet");
	public TableEx table = new TableEx("table");
	public Form form = new Form("form");
	public ActionLink edit = new ActionLink("edit",this.getMessage("edit"),this,"onEdit");
	public ActionLink view = new ActionLink("view",this.getMessage("view"),this,"onView");
	public Material mat = null;
	public String client;
	public String sTitle = this.getMessage("unitPackage");
	public Menu menu = new Menu();
	public String menuInclude = menu.getMaterial();
	public ViewMaterial(){
		if(getContext().getSessionAttribute("searchMaterialId")!=null){
			String id = (String)getContext().getSessionAttribute("searchMaterialId");
			mat = ms.findById(id);
		}
		client = mcs.findClientById(Integer.parseInt(mat.getInttClientDetailWSId())).getCnName();
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		Column column11 = new Column("alias",this.getMessage("alias"));
		Column column1 = new Column("weight",this.getMessage("weight"));
		Column column2 = new Column("length",this.getMessage("length"));
		Column column3 = new Column("breadth",this.getMessage("breadth"));
		Column column4 = new Column("height",this.getMessage("height"));
		Column column5 = new Column("volume",this.getMessage("volume"));
		Column column6 = new Column("unit.alias",this.getMessage("unit"));
//		Column column7 = new Column("remark",this.getMessage("remark"));
//		Column column8 = new Column("description",this.getMessage("description"));
//		Column column9 = new Column("alias",this.getMessage("alias"));
		Column column10 = new Column("action",this.getMessage("action"));
		Submit newUnitPackage = new Submit("newUnitPackage",this.getMessage("newUnitPackage"),this,"onNew");
		Submit cancel = new Submit("cancel",this.getMessage("cancel"),this,"onCancel");
		form.add(newUnitPackage);
		form.add(cancel);
		table.addColumn(column11);
		table.addColumn(column1);
		table.addColumn(column2);
		table.addColumn(column3);
		table.addColumn(column4);
		table.addColumn(column5);
		table.addColumn(column6);
//		table.addColumn(column7);
//		table.addColumn(column8);
//		table.addColumn(column9);
		table.addColumn(column10);
//		column9.setDecorator(new Decorator(){
//			public String render(Object object, Context context) {
//				UnitPackage unitPackage = (UnitPackage)object;
//				return "<a href='gotoDealUnitPackagePair.htm?id="+  unitPackage.getId() + "'>" + unitPackage.getAlias() +"</a>";
//			}	
//		});
		ActionLink [] links = {edit,view};
		column10.setDecorator(new LinkDecorator(table,links,"id"));
		column10.setTextAlign("center");
//		edit.setImageSrc("../image/edit.gif");
//		view.setImageSrc("../image/view-16px.gif");
	}
	
	public void onRender(){
		table.setRowList(upps.findByMateialId(mat.getId()));
	}
	
	public boolean onNew(){
		this.setRedirect("createUnitPackage.htm");
		return true;
	}
	
	public boolean onCancel(){
		getContext().removeSessionAttribute("searchMaterialId");
		getContext().removeSessionAttribute("viewMaterialUnitPackageId");
		this.setRedirect("searchMaterial.htm");
		return true;
	}
	
	public boolean onEdit(){
		String id = edit.getValue();
		getContext().setSessionAttribute("viewMaterialUnitPackageId", id);
		this.setRedirect("editUnitPackage.htm");
		return true;
	}
	
	public boolean onView(){
		String id = view.getValue();
		getContext().setSessionAttribute("dealUnitPackagePairId", id);
		this.setRedirect("dealUnitPackagePair.htm");
		return true;
	}
}
