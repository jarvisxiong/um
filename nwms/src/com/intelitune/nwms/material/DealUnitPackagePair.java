package com.intelitune.nwms.material;

import java.util.List;
import net.sf.click.control.Column;
import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.extras.control.DoubleField;
import net.sf.click.extras.control.FieldColumn;
import net.sf.click.extras.control.TableInlinePaginator;
import com.intelitune.control.FormTableEx;
import com.intelitune.control.TableEx;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.UnitPackage;
import com.intelitune.nwms.model.UnitPackagePair;
import com.intelitune.nwms.service.UnitPackagePairService;
import com.intelitune.nwms.service.UnitPackagePairServiceImpl;
import com.intelitune.nwms.service.UnitPackagePairStateService;
import com.intelitune.nwms.service.UnitPackagePairStateServiceImpl;
import com.intelitune.nwms.service.UnitPackageService;
import com.intelitune.nwms.service.UnitPackageServiceImpl;
/**
 * 
 * @author Louis
 * 修改UnitPackagePair,可以修改UnitPackage相应的所有的UnitPackagePaird的比率，状态
 * 由于在UnitPackagePair中添加了一个bkt，由于下拉单如果是对象就会有问题
 *
 */
public class DealUnitPackagePair extends BorderPage {

	public String weight = this.getMessage("weight");
	public String length = this.getMessage("length");
	public String breadth = this.getMessage("breadth");
	public String height = this.getMessage("height");
	public String volume = this.getMessage("volume");
	public String unit = this.getMessage("unit.code");
	public String remark = this.getMessage("remark");
	public String description = this.getMessage("description");
	public String alias = this.getMessage("alias");
	public String title = this.getMessage("unitPackage");
	public String position = this.getMessage("dealUnitPackagePair");
	public UnitPackagePairService upps =  UnitPackagePairServiceImpl.getInstance();
	public UnitPackagePairStateService uppss =  UnitPackagePairStateServiceImpl.getInstance();
	public UnitPackageService ups =  UnitPackageServiceImpl.getInstance();
	public Form form = new Form("form");
	public FormTableEx table = new FormTableEx("table");
	public UnitPackage unitPackage = null;
	public String sTitle = this.getMessage("dealunitpackagepair");
	public Menu menu = new Menu();
	public String menuInclude = menu.getMaterial();
	public DealUnitPackagePair() {
		table.setWidth("100%");
		table.setPaginator(new TableInlinePaginator(table));
		table.setPaginatorAttachment(TableEx.PAGINATOR_INLINE);
		Column column1 = new Column("otherUnitPackage.alias", this
				.getMessage("unitPackage"));
		table.addColumn(column1);
		FieldColumn fcolumn = null;
		fcolumn = new FieldColumn("denomination", this
				.getMessage("denomination"), new DoubleField("denomination"));
		table.addColumn(fcolumn);
		// List<UnitPackagePairState> unitPackageStateList = uppss.findALl();
		// Select state = new Select();
		// state.add(Option.EMPTY_OPTION);
		// for(UnitPackagePairState unitPackagePairState:unitPackageStateList){
		// state.add(new
		// Option(unitPackagePairState.getId(),unitPackagePairState.getDescription()));
		// }
		fcolumn = new FieldColumn("bkt_state", this.getMessage("state"),
				new UnitPackagePairStateSelect());
		table.addColumn(fcolumn);		

		table.getForm().add(
				new Submit("submit", this.getMessage("submit"), this,
						"onSubmit"));
		Submit nnew = new Submit("new", this.getMessage("new"), this, "onNew");
		Submit back = new Submit("back", this.getMessage("back"), this,
				"onBack");
		form.add(nnew);
		form.add(back);
		// Decorator de = new Decorator(){
		// public String render(Object object, Context context) {
		// Select state = (Select)column3.getField();
		// state.add(Option.EMPTY_OPTION);
		// for(UnitPackagePairState unitPackagePairState:unitPackageStateList){
		// state.add(new
		// Option(unitPackagePairState.getId(),unitPackagePairState.getDescription()));
		// }
		// UnitPackagePair unitPackagePair = (UnitPackagePair)object;
		// state.setValue(unitPackagePair.getState().getId());
		// return state.toString();
		// }
		// };
		// column3.setDecorator(new Decorator(){
		// public String render(Object object, Context context) {
		// Select state = (Select)column3.getField();
		// // state.add(Option.EMPTY_OPTION);
		// // for(UnitPackagePairState
		// unitPackagePairState:unitPackageStateList){
		// // state.add(new
		// Option(unitPackagePairState.getId(),unitPackagePairState.getDescription()));
		// // }
		// UnitPackagePair unitPackagePair = (UnitPackagePair)object;
		// state.setValue(unitPackagePair.getState().getId());
		// return state.toString();
		// }
		// });

		// column3.setDecorator(new Decorator(){
		// public String render(Object object, Context context) {
		// UnitPackagePair unitPackagePair = (UnitPackagePair)object;
		// state.setValue(unitPackagePair.getState().getId());
		// return state.toString();
		// }
		// });
	}

	public void onInit() {
		String id = (String) getContext().getSessionAttribute(
				"dealUnitPackagePairId");
		unitPackage = ups.getUnitPackage(id);
		List<UnitPackagePair> list = upps.findByUnitPackageId(unitPackage
				.getId());
		
		// Set<UnitPackagePair> unitPackagePairs =
		// unitPackage.getUnitPackagePairs();
		 for(UnitPackagePair unitPackagePairss:list){
			 unitPackagePairss.setBkt_state(unitPackagePairss.getState().getId());
		 }
		table.setRowList(list);
	}

//	public void onRender() {
//
//	}

	public boolean onSubmit() {
		List<UnitPackagePair> plist = table.getRowList();
		for (int i = 0; i < plist.size(); i++) {
			String id = getContext().getRequestParameter("bkt_state_"+i);
			UnitPackagePair unitPPP = plist.get(i);
			unitPPP.setState(uppss.findUnitPackagePairState(id));
			// UnitPackagePair unitPP = list.get(i);
			// if(unitPP.getDenomination()!=unitPPP.getDenomination()||!unitPP.getState().getId().equals(unitPPP.getState().getId())){
			upps.updateUnitPackagePair(unitPPP);
			// }
		}
//		form.clearValues();
		return true;
	}

	public boolean onNew() {
		this.setRedirect("createUnitPackagePair.htm");
		return true;
	}

	public boolean onBack() {
		this.setRedirect("viewMaterial.htm");
		return true;
	}
}
