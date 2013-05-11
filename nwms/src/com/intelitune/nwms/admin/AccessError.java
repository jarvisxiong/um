package com.intelitune.nwms.admin;

import net.sf.click.control.Form;

import com.intelitune.nwms.common.BorderPage;
import com.intelitune.nwms.menu.Menu;

public class AccessError extends BorderPage {
	Menu me = new Menu();
	public String menuInclude = me.getSystemset();
	public Form form = new Form("form");
	public String accessMessage = this.getMessage("accessMessage");
	public AccessError(){
	}

}