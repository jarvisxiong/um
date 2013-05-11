package com.intelitune.nwms.material;

import java.util.List;

import net.sf.click.control.ActionLink;
import net.sf.click.control.Column;
import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.LinkDecorator;
import net.sf.click.extras.control.TableInlinePaginator;

import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Unit;
import com.intelitune.nwms.model.UnitState;
import com.intelitune.nwms.service.UnitService;
import com.intelitune.nwms.service.UnitServiceImpl;
import com.intelitune.nwms.service.UnitStateService;
import com.intelitune.nwms.service.UnitStateServiceImpl;
/**
 * 
 * @author Louis
 * 创建Unit，Unit不会太多所以做在了一张页面里面
 *
 */
public class CreateUnit extends BorderPage {
	public TextField alias = new TextField("alias",this.getMessage("alias"));
	public TextField code = new TextField("code",this.getMessage("code"));
	public Select state = new Select("state",this.getMessage("state"));
	public Form form = new Form("form");
	public TableEx table = new TableEx("table");
	public ActionLink edit = new ActionLink("edit",this.getMessage("edit"),this,"onEdit");
	public UnitService us =  UnitServiceImpl.getInstance();
	public UnitStateService uss =  UnitStateServiceImpl.getInstance();
	public String title = this.getMessage("createunit");
	public String position = this.getMessage("unitCreate");
	public Menu menu = new Menu();
	public String menuInclude = menu.getMaterial();
	public CreateUnit(){
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);	
		table.setPageSize(10);
		form.add(alias);
		form.add(code);
		form.add(state);
		Submit submit = new Submit("submit",this.getMessage("submit"),this,"onSubmit");
		Submit reset = new Submit("reset",this.getMessage("reset"),this,"onReset");
		form.add(submit);
		form.add(reset);
		Column column1 = new Column("code",this.getMessage("code"));
		Column column3 = new Column("alias",this.getMessage("alias"));
		Column column4 = new Column("state.description",this.getMessage("state"));
		Column column5 = new Column("action",this.getMessage("action"));
		column5.setDecorator(new LinkDecorator(table,edit,"id"));
//		edit.setImageSrc("../image/edit.gif");
		table.addColumn(column3);
		table.addColumn(column1);
		table.addColumn(column4);
		table.addColumn(column5);
		column5.setTextAlign("center");
		state.add(Option.EMPTY_OPTION);
		List<UnitState> unitStates = uss.findAll();
		for(UnitState unitState:unitStates){
			state.add(new Option(unitState.getId(),unitState.getDescription()));
		}
	}
	
	public void onRender(){
		table.setRowList(us.findAll());
	}
	
	public boolean onEdit(){
		String id = edit.getValue();
		Unit unit = us.getUnit(id);
		form.copyFrom(unit);
		state.setValue(unit.getState().getId());
		getContext().setSessionAttribute("createUnitId", id);
		return true;
	}
	
	public boolean onSubmit(){
		if(getContext().getSessionAttribute("createUnitId")!=null){
			String id = (String)getContext().getSessionAttribute("createUnitId");
			Unit unit = us.getUnit(id);
			unit.setAlias(alias.getValue());
			unit.setCode(code.getValue());
			unit.setState(uss.findUnitState(state.getValue()));
			us.updateUnit(unit);
			form.clearValues();
		}else{
			Unit unit = new Unit();
			unit.setAlias(alias.getValue());
			unit.setCode(code.getValue());
			unit.setState(uss.findUnitState(state.getValue()));
			us.saveUnit(unit);
			form.clearValues();
		}
		getContext().removeSessionAttribute("createUnitId");
		return true;
	}
	
	public boolean onReset(){
		getContext().removeSessionAttribute("createUnitId");
		this.setRedirect("createUnit.htm");
		return true;
	}

}
