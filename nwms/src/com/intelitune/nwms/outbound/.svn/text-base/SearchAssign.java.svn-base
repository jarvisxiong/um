package com.intelitune.nwms.outbound;

import net.sf.click.control.Form;
import net.sf.click.control.Submit;
import net.sf.click.control.TextArea;
import net.sf.click.control.TextField;

import com.intelitune.nwms.service.OutboundAssignedService;
import com.intelitune.nwms.service.OutboundAssignedServiceImp;

public class SearchAssign extends com.intelitune.nwms.common.BorderPage {
	
	public OutboundAssignedService oas=OutboundAssignedServiceImp.getInstance();
	
	public TextArea remark = new TextArea("remark",this.getMessage("remark"));
	public TextField alias = new TextField("alias",this.getMessage("zwalias"));
	public TextField code = new TextField("code",this.getMessage("code"));
	public TextField description = new TextField("description",this.getMessage("description")); 
	
	public Form form = new Form("form");
	public SearchAssign(){
		form.add(remark);
		form.add(alias);
		form.add(code);
		form.add(description);
		Submit save = new Submit("save",this.getMessage("save"),this,"onSave");
		Submit reset = new Submit("reset",this.getMessage("reset"),this,"onReset");
		form.add(save);
		form.add(reset);
	}
	
	public boolean onSave(){
		String str=remark.getValue();
		oas.queryStr(str);
		return true;
	}
}