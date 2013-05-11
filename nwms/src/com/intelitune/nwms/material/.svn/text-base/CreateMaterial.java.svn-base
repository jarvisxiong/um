package com.intelitune.nwms.material;

import java.sql.Timestamp;

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
import com.intelitune.nwms.model.MaterialState;
import com.intelitune.nwms.model.UnitPackage;
import com.intelitune.nwms.model.UnitPackageState;
import com.intelitune.nwms.service.MCSService;
import com.intelitune.nwms.service.MaterialService;
import com.intelitune.nwms.service.MaterialServiceImpl;
import com.intelitune.nwms.service.MaterialStateService;
import com.intelitune.nwms.service.MaterialStateServiceImpl;
import com.intelitune.nwms.service.UnitPackageService;
import com.intelitune.nwms.service.UnitPackageServiceImpl;
import com.intelitune.nwms.service.UnitPackageStateService;
import com.intelitune.nwms.service.UnitPackageStateServiceImpl;
import com.intelitune.nwms.service.UnitService;
import com.intelitune.nwms.service.UnitServiceImpl;
/**
 * 
 * @author Louis
 * 创建Material，由于material可能较多，所以将没有将增删改放在一起
 *
 */
public class CreateMaterial extends BorderPage {

	public Form form = new Form("form");
	public DoubleField value = new DoubleField("value",this.getMessage("value"));
	public Select owner = new Select("owner",this.getMessage("owner")+"<span style='color:red'>*</span>");
	public TextField description = new TextField("description",this.getMessage("description")); 
	public TextArea remark = new TextArea("remark",this.getMessage("remark"));
	public TextField alias = new TextField("alias",this.getMessage("zwalias"));
	public TextField code = new TextField("code",this.getMessage("code"));
	public MaterialService ms =  MaterialServiceImpl.getInstance();
	public MaterialStateService mss =  MaterialStateServiceImpl.getInstance();
	public MCSService mcs = MCSService.getInstance();
	public UnitPackageService ups =  UnitPackageServiceImpl.getInstance(); 
	public UnitPackageStateService upss =  UnitPackageStateServiceImpl.getInstance();
	public UnitService us =  UnitServiceImpl.getInstance();
	public String title = this.getMessage("creatematerial");
	public String position = this.getMessage("createMet");
	public Menu menu = new Menu();
	public String menuInclude = menu.getMaterial();
	
	//materialstate，shelffile unitPakage
	
	public CreateMaterial(){
		form.add(owner);
		form.add(code);
		form.add(alias);
		form.add(value);
		form.add(description);
		
		
		owner.add(Option.EMPTY_OPTION);
		InttClientDetailWS client[] = mcs.getAllClient();
		for(int i=0;i<client.length;i++){
			owner.add(new Option(client[i].getId(),client[i].getCnName()));
		}
		form.add(remark);
		Submit save = new Submit("save",this.getMessage("save"),this,"onSave");
		Submit reset = new Submit("reset",this.getMessage("reset"),this,"onReset");
		form.add(save);
		form.add(reset);
	}
	
	public boolean onSave(){
		if(form.isValid()){
			if(owner.getValue()==null){
				owner.setError("pleaseselectaowner");
				return false;
			}
		
			Material mat = ms.findMaterialByCode(code.getValue(), owner.getValue());
			if(mat!=null){
				code.setError(this.getMessage("error13"));
				return false;
			}
			Material material = new Material();
			material.setAlias(alias.getValue());
			material.setCode(code.getValue());
			material.setCreationTime(new Timestamp(System.currentTimeMillis()));
			material.setDescription(description.getValue());
			material.setInttClientDetailWSId(owner.getValue());
			MaterialState materialState = mss.getMaterialState(MaterialState.ZHENGCHANG);
			material.setState(materialState);
			material.setRemark(remark.getValue());
			material.setValue(value.getFloat());
			ms.saveMaterial(material);
			UnitPackage unitPackage = new UnitPackage();
			unitPackage.setAlias(alias.getValue()+"_默认SKU");
			unitPackage.setBreadth(1f);
			unitPackage.setHeight(1f);
			unitPackage.setLength(1f);
		
//			unitPackage.setRemark(remark.getValue());
			unitPackage.setState(upss.getUnitPackageState(UnitPackageState.ZHENGCHANG));
			unitPackage.setUnit(us.findUnitByCode("0").get(0));
			unitPackage.setVolume(1f);
			unitPackage.setWeight(1f);	
			unitPackage.setMaterial(material);		
			ups.saveUnitPackage(unitPackage);
			if(material.getUnitPackages() != null){
				unitPackage.setIndexId(material.getUnitPackages().size()+1);
			}else{
				unitPackage.setIndexId(1);
			}
			ups.saveUnitPackage(unitPackage);
			form.clearValues();
		}
		return true;
	}
	
	public boolean onReset(){
		this.setRedirect("createMaterial.htm");
		return true;
	}
}
