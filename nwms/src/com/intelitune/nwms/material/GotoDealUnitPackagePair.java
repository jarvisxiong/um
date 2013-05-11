package com.intelitune.nwms.material;

import net.sf.click.Page;

public class GotoDealUnitPackagePair extends Page {

	public GotoDealUnitPackagePair(){
		String id = getContext().getRequestParameter("id");
		getContext().setSessionAttribute("dealUnitPackagePairId", id);
		this.setRedirect("dealUnitPackagePair.htm");
	}
}
