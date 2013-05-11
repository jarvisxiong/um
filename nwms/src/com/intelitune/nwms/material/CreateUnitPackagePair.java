package com.intelitune.nwms.material;

import java.util.List;

import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.extras.control.DoubleField;

import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.UnitPackage;
import com.intelitune.nwms.model.UnitPackagePair;
import com.intelitune.nwms.model.UnitPackagePairState;
import com.intelitune.nwms.service.UnitPackagePairService;
import com.intelitune.nwms.service.UnitPackagePairServiceImpl;
import com.intelitune.nwms.service.UnitPackagePairStateService;
import com.intelitune.nwms.service.UnitPackagePairStateServiceImpl;
import com.intelitune.nwms.service.UnitPackageService;
import com.intelitune.nwms.service.UnitPackageServiceImpl;
/**
 * 
 * @author Louis
 *创建料件单位包装组合
 */
public class CreateUnitPackagePair extends BorderPage {
	public String weight = this.getMessage("weight");
	public String length = this.getMessage("length");
	public String breadth = this.getMessage("breadth");
	public String height = this.getMessage("height");
	public String volume = this.getMessage("volume");
	public String unit = this.getMessage("unit.code");
	public String remark = this.getMessage("remark");
	public String description = this.getMessage("description");
	public String alias = this.getMessage("alias");
	public String title = this.getMessage("createunitpackagepair");
	public String position = this.getMessage("createUnitPackagePair");
	public UnitPackagePairService upps =  UnitPackagePairServiceImpl.getInstance();
	public UnitPackagePairStateService uppss =  UnitPackagePairStateServiceImpl.getInstance();
	public UnitPackageService ups =  UnitPackageServiceImpl.getInstance();
	
	public Select state = new Select("state",this.getMessage("state"));
	public DoubleField denomination = new DoubleField("denomination",this.getMessage("denomination"));
	public Select unitPackage = new Select("unitPackage",this.getMessage("unitPackage"));
	public Form form = new Form("form");
	public UnitPackage unitPackagea = null;
	public Menu menu = new Menu();
	public String menuInclude = menu.getMaterial();
	public CreateUnitPackagePair(){
		String id = (String)getContext().getSessionAttribute("dealUnitPackagePairId");
		unitPackagea = ups.getUnitPackage(id);
		Submit submit = new Submit("submit",this.getMessage("submit"),this,"onSubmit");
		Submit back = new Submit("back",this.getMessage("back"),this,"onBack");
		form.add(denomination);
		form.add(unitPackage);
		form.add(state);
		form.add(submit);
		form.add(back);
		List<UnitPackagePairState> unitPackageStateList = uppss.findALl();
		state.add(Option.EMPTY_OPTION);
		for(UnitPackagePairState unitPackagePairState:unitPackageStateList){
			state.add(new Option(unitPackagePairState.getId(),unitPackagePairState.getDescription()));
		}
		List<UnitPackage> list = ups.findByMateialId(unitPackagea.getMaterial().getId());
		unitPackage.add(Option.EMPTY_OPTION);
		for(UnitPackage unitPackagess:list){
			if(!unitPackagess.getId().equals(unitPackagea.getId())){
				unitPackage.add(new Option(unitPackagess.getId(),unitPackagess.getAlias()));
			}
		}
		
	}
	
	public boolean onSubmit(){
		UnitPackagePair unitPackagePair = new UnitPackagePair();
		unitPackagePair.setDenomination(denomination.getFloat());
		unitPackagePair.setState(uppss.findUnitPackagePairState(state.getValue()));
		unitPackagePair.setUnitPackage(unitPackagea);
		unitPackagePair.setOtherUnitPackage(ups.getUnitPackage(unitPackage.getValue()));
		upps.saveUnitPackagePair(unitPackagePair);
		form.clearValues();
		this.setRedirect("dealUnitPackagePair.htm");
		return true;
	}
	
	public boolean onBack(){
		this.setRedirect("dealUnitPackagePair.htm");
		return true;
	}
}
