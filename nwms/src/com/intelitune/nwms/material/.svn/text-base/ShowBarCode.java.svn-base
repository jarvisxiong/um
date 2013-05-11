package com.intelitune.nwms.material;

import net.sf.click.Page;

public class ShowBarCode extends Page {

	public String code;
	public String path1;
	
	public ShowBarCode(){
		code = getContext().getRequestParameter("code");
		
		if(code!=null){
			path1 = "<img src='http://www.hmglog.com/barcode/BarcodeServlet?data=" + code +"&width=1&height=50&resolution=200&type=Code128'/>";
			System.out.println(path1);
		}
	}
}
