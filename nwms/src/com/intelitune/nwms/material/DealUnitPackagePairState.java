package com.intelitune.nwms.material;

import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.LinkDecorator;
import net.sf.click.extras.control.TableInlinePaginator;

import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.UnitPackagePairState;
import com.intelitune.nwms.service.UnitPackagePairStateService;
import com.intelitune.nwms.service.UnitPackagePairStateServiceImpl;
/**
 * 
 * @author Louis
 * 创建修改UnitPackagePairState
 *
 */
public class DealUnitPackagePairState extends BorderPage {
	public TextField code = new TextField("code",this.getMessage("code"));
	public TextField description = new TextField("description",this.getMessage("description"));
	
	public ActionLink edit = new  ActionLink("edit",this.getMessage("edit"),this,"onEdit");
	public Form form = new Form("form");
	public TableEx table = new TableEx("table");
	public String title = this.getMessage("dealunitpackagepairstate");
	public String position = this.getMessage("dealUnitPackagePairState");
	public UnitPackagePairStateService uppss =  UnitPackagePairStateServiceImpl.getInstance();
	public Menu menu = new Menu();
	public String menuInclude = menu.getMaterial();
	public DealUnitPackagePairState(){
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		form.add(code);
		form.add(description);
		Submit submit = new Submit("submit",this.getMessage("submit"),this,"onSubmit");
		Submit reset = new Submit("reset",this.getMessage("reset"),this,"onReset");
		form.add(submit);
		form.add(reset);
		Column column1 = new Column("code",this.getMessage("code"));
		Column column2 = new Column("description",this.getMessage("description"));
		Column column3 = new Column("action",this.getMessage("action"));
//		edit.setImageSrc("../image/edit.gif");
		column3.setDecorator(new LinkDecorator(table,edit,"id"));
		table.addColumn(column1);
		table.addColumn(column2);
		table.addColumn(column3);
		column3.setTextAlign("center");
	}
	
	public void onRender(){
		table.setRowList(uppss.findALl());
	}
	
	public boolean onSubmit(){
		if(getContext().getSessionAttribute("dealUnitPackagePairStateId")!=null){
			String id = (String)getContext().getSessionAttribute("dealUnitPackagePairStateId");
			UnitPackagePairState unitPackagePairState = uppss.findUnitPackagePairState(id);
			unitPackagePairState.setCode(code.getValue());
			unitPackagePairState.setDescription(description.getValue());
			uppss.saveUnitPackagePairState(unitPackagePairState);
			form.clearValues();
		}else{
			UnitPackagePairState unitPackagePairState = new UnitPackagePairState();
			unitPackagePairState.setCode(code.getValue());
			unitPackagePairState.setDescription(description.getValue());
			uppss.saveUnitPackagePairState(unitPackagePairState);
			form.clearValues();
		}
		getContext().removeSessionAttribute("dealUnitPackagePairStateId");
		return true;	
	}
	
	public boolean onEdit(){
		String id = edit.getValue();
		UnitPackagePairState unitPackagePairState = uppss.findUnitPackagePairState(id);
		form.copyFrom(unitPackagePairState);
		getContext().setSessionAttribute("dealUnitPackagePairStateId",id);
		return true;
	}
	
	public boolean onReset(){
		getContext().removeSessionAttribute("dealUnitPackagePairStateId");
		this.setRedirect("dealUnitPackagePairState.htm");
		return true;
	}
}
