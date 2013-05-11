package com.intelitune.nwms.warehouse;

import net.sf.click.control.PageLink;
import net.sf.click.control.Table;

import com.intelitune.nwms.menu.Menu;
import com.intelitune.nwms.model.Bin;
import com.intelitune.nwms.service.BinService;
import com.intelitune.nwms.service.BinServiceImp;

public class BinShow extends com.intelitune.nwms.common.BorderPage {
Menu me = new Menu();
	
	public String menuInclude = me.getWarehouse();
	
	public String position = this.getMessage("bin_position_show");
	
	public String barcode="";
	
	public String bin_title =this.getMessage("bin_show");
	
	public String bin_id;
	
	public PageLink pageLink1 = new PageLink("bin_return", this
			.getMessage("return"), BinListContext.class);
	
	public Bin b;
	
	public BinService bs= BinServiceImp.getInstance();
	
	public String showDesc="";
	
	public String bcode="";
	
	
	public BinShow(){
		bin_id=this.getContext().getRequestParameter("id").trim();
		if(bin_id!=null){
			this.getContext().getSession().setAttribute("bin_show_id", bin_id);
		}
		b=bs.findBinById(this.getContext().getSessionAttribute("bin_show_id").toString());	
		bcode=b.getCode();
		barcode="http://www.hmglog.com/barcode/BarcodeServlet?data="+b.getCode()+"&width=1&height=50&resolution=200&type";
		showDesc+="<br>";
		showDesc+=this.getMessage("bin_code")+"&nbsp;&nbsp;"+b.getCode()+"<br>";
		showDesc+=this.getMessage("bin_state")+"&nbsp;&nbsp;"+b.getState().getDescription()+"<br>";
		showDesc+=this.getMessage("bin_weight")+"&nbsp;&nbsp;"+b.getWeight()+"<br>";
		showDesc+=this.getMessage("bin_height")+"&nbsp;&nbsp;"+b.getHeight()+"<br>";
		showDesc+=this.getMessage("bin_length")+"&nbsp;&nbsp;"+b.getLength()+"<br>";
		showDesc+=this.getMessage("bin_breadth")+"&nbsp;&nbsp;"+b.getBreadth()+"<br>";
		showDesc+=this.getMessage("bin_volumn")+"&nbsp;&nbsp;"+b.getVolume()+"<br>";
		showDesc+=this.getMessage("bin_remark")+"&nbsp;&nbsp;";
		if(b.getRemark()!=null){
			showDesc+=b.getRemark()+"<br>";
				
		}
		showDesc+="<br>"+this.getMessage("zone")+"&nbsp;&nbsp;";
		for(int i=0;i<b.getZones().size();i++){
			showDesc+=b.getZones().get(i).getAlias()+"&nbsp;&nbsp;";
		}
		
	}

	
	public void onRender(){
		
	}
}