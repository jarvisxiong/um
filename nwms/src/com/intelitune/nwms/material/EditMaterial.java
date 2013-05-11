package com.intelitune.nwms.material;

import java.rmi.RemoteException;
import java.sql.Timestamp;
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
import com.intelitune.nwms.model.MaterialState;
import com.intelitune.nwms.service.MCSService;
import com.intelitune.nwms.service.MaterialService;
import com.intelitune.nwms.service.MaterialServiceImpl;
import com.intelitune.nwms.service.MaterialStateService;
import com.intelitune.nwms.service.MaterialStateServiceImpl;
/**
 * 
 * @author Louis
 * 修改Material，由于material比较多，所以和创建分开来做
 *
 */
public class EditMaterial extends BorderPage {
	public Form form = new Form("form");
	public DoubleField value = new DoubleField("value",this.getMessage("value"));
	public Select owner = new Select("owner",this.getMessage("owner")+"<span style='color:red'>*</span>");
	public TextField description = new TextField("description",this.getMessage("description")); 
	public TextArea remark = new TextArea("remark",this.getMessage("remark"));
	public TextField alias = new TextField("alias",this.getMessage("zwalias"));
	public TextField code = new TextField("code",this.getMessage("code"));
	public MCSService mcs = MCSService.getInstance();
	public MaterialService ms =  MaterialServiceImpl.getInstance();
	public MaterialStateService mss =  MaterialStateServiceImpl.getInstance();
	public String title = this.getMessage("editmaterial");
	public String position = this.getMessage("editMet");
	public Material material = null;
	public Select state = new Select("state",this.getMessage("state"));
	public Menu menu = new Menu();
	public String menuInclude = menu.getMaterial();
	public EditMaterial() throws RemoteException{
		form.add(owner);
		form.add(code);
		form.add(alias);
		form.add(value);
		form.add(description);	
		form.add(state);
		owner.add(Option.EMPTY_OPTION);
		InttClientDetailWS client[] = mcs.getAllClient();
		for(int i=0;i<client.length;i++){
			owner.add(new Option(client[i].getId(),client[i].getCnName()));
		}
		List<MaterialState> list = mss.findAll();
		state.add(Option.EMPTY_OPTION);
		for(int i=0;i<list.size();i++){
			MaterialState mates = list.get(i);
			state.add(new Option(mates.getId(),mates.getDescription()));
		}
		Submit save = new Submit("save",this.getMessage("save"),this,"onSave");
		Submit cancel = new Submit("cancel",this.getMessage("back"),this,"onCancel");
		form.add(save);
		form.add(cancel);
	}
	
	public void onRender(){
		if(getContext().getSessionAttribute("searchMaterialId")!=null){
			String id = (String)getContext().getSessionAttribute("searchMaterialId");
			material = ms.findById(id);
			form.copyFrom(material);
			state.setValue(material.getState().getId());
			owner.setValue(material.getInttClientDetailWSId());
		}else{
			this.setRedirect("searchMaterial.htm");
		}
	}
	
	public boolean onSave(){
		if(owner.getValue()==null){
			owner.setError("pleaseselectaowner");
			return false;
		}
		String id = (String)getContext().getSessionAttribute("searchMaterialId");
		material = ms.findById(id);
		material.setAlias(alias.getValue());
		material.setCode(code.getValue());
		material.setCreationTime(new Timestamp(System.currentTimeMillis()));
		material.setDescription(description.getValue());
		material.setInttClientDetailWSId(owner.getValue());
		MaterialState mater= mss.findMaterialState(state.getValue());
		material.setState(mater);
		material.setRemark(remark.getValue());
		material.setValue(value.getFloat());
		ms.updateMaterial(material);
		form.clearValues();
		this.setRedirect("searchMaterial.htm");
		return true;
	}

	public boolean onCancel(){
		this.setRedirect("searchMaterial.htm");
		return true;
	}
}
