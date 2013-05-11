package com.intelitune.nwms.warehouse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.click.control.Form;
import net.sf.click.control.Option;
import net.sf.click.control.Select;
import net.sf.click.control.Submit;
import net.sf.click.control.TextField;
import net.sf.click.extras.control.DoubleField;
import net.sf.click.extras.control.PickList;

import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Bin;
import com.intelitune.nwms.model.BinState;
import com.intelitune.nwms.model.Zone;
import com.intelitune.nwms.service.BinService;
import com.intelitune.nwms.service.BinServiceImp;
import com.intelitune.nwms.service.BinStateService;
import com.intelitune.nwms.service.BinStateServiceImp;
import com.intelitune.nwms.service.ZoneService;
import com.intelitune.nwms.service.ZoneServiceImp;

public class ModifyOneBin extends com.intelitune.nwms.common.BorderPage {
	Menu me = new Menu();
	
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("bin_position_modify");
	
	public Form form =new Form();
	
	public String bin_title =this.getMessage("modify");
	
	public String bin_id;
	
	public BinStateService bss= BinStateServiceImp.getInstance();
	public BinService bs= BinServiceImp.getInstance();
	public ZoneService zs= ZoneServiceImp.getInstance();
	
	public Select stateSelect;
	
	public DoubleField tf_weight=new DoubleField(this.getMessage("bin_weight"));
	public DoubleField tf_length=new DoubleField(this.getMessage("bin_length"));
	public DoubleField tf_breadth=new DoubleField(this.getMessage("bin_breadth"));
	public DoubleField tf_height=new DoubleField(this.getMessage("bin_height"));
	public DoubleField tf_volume=new DoubleField(this.getMessage("bin_volumn"));
	public TextField tf_remark=new TextField(this.getMessage("bin_remark"));
	
	public PickList pickList = new PickList("picklist",this.getMessage("zone_select"));
	public List<Zone> selected=new ArrayList<Zone>();
	public List<Zone> list;
	
	public ModifyOneBin(){
		list=zs.findZoneList();
	
		stateSelect = new Select("bin_state", this.getMessage("bin_state"));
		stateSelect.setRequired(true);
		form.add(stateSelect);
		form.add(tf_weight);
		form.add(tf_length);
		form.add(tf_breadth);
		form.add(tf_height);
		form.add(tf_volume);
		form.add(tf_remark);
        pickList.setHeaderLabel("Zone", "Selected");
        for(int i=0;i<list.size();i++){
        	Zone z = (Zone) list.get(i);
        	pickList.add(new Option(z.getId(), z.getAlias()));
        }
        form.add(pickList);
		form.add(new Submit("modifiy",this.getMessage("modify"),this,"onModify"));
		form.add(new Submit("cancel",this.getMessage("cancel"),this,"onCancel"));
		
	}
	
	public void onInit(){
		super.onInit();
		List<BinState> list=bss.findBinStateList();
		 stateSelect.add(new Option(""));
	        for (Iterator<BinState> i = list.iterator(); i.hasNext();) {
	            BinState bs = (BinState) i.next();
	            stateSelect.add(new Option(bs.getCode(), bs.getDescription()));
	        }
	}
	
	public void onRender(){
		bin_id=this.getContext().getRequestParameter("id");
		if(bin_id!=null){
			this.getContext().getSession().setAttribute("modify_bin_id", bin_id);
		}
		Bin b=bs.findBinById((String)this.getContext().getSessionAttribute("modify_bin_id"));
//		List<String> list=new ArrayList<String>();
//		for(int i=0;i<b.getZones().size();i++){
//			list.add(b.getZones().get(i).getId());
//		}
		List<Zone> list=b.getZones();
		stateSelect.setValue(b.getState().getCode());
		tf_weight.setValue(b.getWeight().toString());
		tf_length.setValue(b.getLength().toString());
		tf_breadth.setValue(b.getBreadth().toString());
		tf_height.setValue(b.getHeight().toString());
		tf_volume.setValue(b.getVolume().toString());
		tf_remark.setValue(b.getRemark());
		pickList.setSelectedValues(b.getZones(),"id");	
	}
	
	public boolean onCancel(){
		this.setRedirect(BinListContext.class);
		return true;
	}
	
	public boolean onModify(){
		if(form.isValid()){
			List picklist=pickList.getSelectedValues();
			for(int i=0;i<picklist.size();i++){
				if(picklist.get(i)!=null){
					selected.add(zs.findZoneById((String)picklist.get(i)));
				}
			}
			Bin b=bs.findBinById((String)this.getContext().getSessionAttribute("modify_bin_id"));
			if(stateSelect.getValue()!=null&&stateSelect.getValue()!=""){
				b.setState(bss.findBinStateByCode(Integer.parseInt(stateSelect.getValue())));
			}
			if(tf_weight.getValue().length()>0){
				b.setWeight(Float.parseFloat(tf_weight.getValue()));
			}
			if(tf_length.getValue().length()>0){
				b.setLength(Float.parseFloat(tf_length.getValue()));
			}
			if(tf_breadth.getValue().length()>0){
				b.setBreadth(Float.parseFloat(tf_breadth.getValue()));
			}
			if(tf_height.getValue().length()>0){
				b.setHeight(Float.parseFloat(tf_height.getValue()));
			}
			if(tf_volume.getValue().length()>0){
				b.setVolume(Float.parseFloat(tf_volume.getValue()));
			}
			if(tf_remark.getValue().length()>0){
				b.setRemark(tf_remark.getValue());
			}
			if(selected.size()>0){
				b.setZones(selected);
			}
			bs.modifyBin(b);
			this.setRedirect(BinListContext.class);
		}
		return true;
	}

}