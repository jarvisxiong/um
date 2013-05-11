package com.intelitune.nwms.material;

import java.util.List;

import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.control.TextArea;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.DoubleField;

import com.axis2.services.MCSserviceStub.InttClientDetailWS;
import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Material;
import com.intelitune.nwms.model.Unit;
import com.intelitune.nwms.model.UnitPackage;
import com.intelitune.nwms.model.UnitPackageState;
import com.intelitune.nwms.service.MCSService;
import com.intelitune.nwms.service.MaterialService;
import com.intelitune.nwms.service.MaterialServiceImpl;
import com.intelitune.nwms.service.UnitPackageService;
import com.intelitune.nwms.service.UnitPackageServiceImpl;
import com.intelitune.nwms.service.UnitPackageStateService;
import com.intelitune.nwms.service.UnitPackageStateServiceImpl;
import com.intelitune.nwms.service.UnitService;
import com.intelitune.nwms.service.UnitServiceImpl;
/**
 * 
 * @author Louis
 * 编辑UnitPackage
 *
 */
public class EditUnitPackage extends BorderPage {
	public DoubleField weight = new DoubleField("weight",this.getMessage("weight"));
	public DoubleField length = new DoubleField("length",this.getMessage("length"));
	public DoubleField breadth = new DoubleField("breadth",this.getMessage("breadth"));
	public DoubleField height = new DoubleField("height",this.getMessage("height"));
	public DoubleField volume = new DoubleField("volume",this.getMessage("volume"));
	public Select unit = new Select("unit",this.getMessage("unit"));
	public Select state = new Select("state",this.getMessage("state"));
	public TextArea remark = new TextArea("remark",this.getMessage("remark"));
	public TextField description = new TextField("description",this.getMessage("description"));
	public TextField alias = new TextField("alias",this.getMessage("alias"));
	public Form form = new Form("form");
	public MaterialService ms =  MaterialServiceImpl.getInstance();
	public UnitPackageService ups =  UnitPackageServiceImpl.getInstance(); 
	public UnitPackageStateService upss =  UnitPackageStateServiceImpl.getInstance();
	public MCSService mcs = MCSService.getInstance();
	public UnitService us =  UnitServiceImpl.getInstance();
	public String title = this.getMessage("editunitpackage");
	public String  position = this.getMessage("editUnitPackage");
	
	public String value;
	public String remarks;
	public String descriptions;
	public String aliass;
	public String codes;
	public String owner; 
	public InttClientDetailWS owners;
	public Material material;
	public UnitPackage unitPackage;
	public Menu menu = new Menu();
	public String menuInclude = menu.getMaterial();
	public EditUnitPackage(){
		value = this.getMessage("value");
		remarks = this.getMessage("remark");
		descriptions = this.getMessage("description");
		aliass = this.getMessage("zwalias");
		codes = this.getMessage("code");
		owner = this.getMessage("owner");
		form.add(alias);
		weight.setLabel(weight.getLabel()+"(单位：KG)");
		form.add(length);
		length.setLabel(length.getLabel()+"(单位:M)");
		form.add(breadth);
		breadth.setLabel(breadth.getLabel() +"(单位:M)");
		form.add(height);
		height.setLabel(height.getLabel()+"(单位:M)");
		form.add(volume);
		volume.setLabel(volume.getLabel()+"(单位:M3)");
		form.add(unit);
		form.add(description);
		form.add(remark);
		form.add(state);
		Submit submit = new Submit("submit",this.getMessage("submit"),this,"onSubmit");
		Submit back = new Submit("back",this.getMessage("back"),this,"onBack");
		form.add(submit);
		form.add(back);	
		
		List<Unit> unitList = us.findAll();
		unit.add(Option.EMPTY_OPTION);
		for(Unit unitt:unitList){
			unit.add(new Option(unitt.getId(),unitt.getAlias()));
		}
		
		List<UnitPackageState> unitPackageStateList = upss.findAll();
		state.add(Option.EMPTY_OPTION);
		for(UnitPackageState unitPackageState:unitPackageStateList){
			state.add(new Option(unitPackageState.getId(),unitPackageState.getDescription()));
		}
		String id = (String)getContext().getSessionAttribute("searchMaterialId");
		String unitPackageId = (String)getContext().getSessionAttribute("viewMaterialUnitPackageId");
		unitPackage = ups.getUnitPackage(unitPackageId);
		
		material = ms.findById(id);
		owners = mcs.findClientById(Integer.parseInt(material.getInttClientDetailWSId()));
	}
	
//	public void onInit(){
//		
//	}
	
	public void onRender(){	
		form.copyFrom(unitPackage);	
		unit.setValue(unitPackage.getUnit().getId());
		state.setValue(unitPackage.getState().getId());
	}
	
	public boolean onSubmit(){
		unitPackage.setAlias(alias.getValue());
		unitPackage.setBreadth(breadth.getFloat());
		unitPackage.setDescription(description.getValue());
		unitPackage.setHeight(height.getFloat());
		unitPackage.setLength(length.getFloat());
		unitPackage.setMaterial(material);
		unitPackage.setRemark(remark.getValue());
		unitPackage.setState(upss.getUnitPackageState(UnitPackageState.ZHENGCHANG));
		unitPackage.setUnit(us.getUnit(unit.getValue()));
		unitPackage.setVolume(volume.getFloat());
		unitPackage.setWeight(weight.getFloat());
		ups.updateUnitPackage(unitPackage);
		form.clearValues();
		this.setRedirect("viewMaterial.htm");
		return true;
	}
	
	public boolean onBack(){
		this.setRedirect("viewMaterial.htm");
		return true;
	}
}
