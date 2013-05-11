package com.intelitune.nwms.item;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.sf.click.Page;

import com.intelitune.nwms.model.Material;
import com.intelitune.nwms.model.MaterialState;
import com.intelitune.nwms.model.UnitPackage;
import com.intelitune.nwms.service.MaterialService;
import com.intelitune.nwms.service.MaterialServiceImpl;
import com.intelitune.nwms.service.UnitPackageService;
import com.intelitune.nwms.service.UnitPackageServiceImpl;

public class MaterialFilter extends Page {
	
	UnitPackageService ups= UnitPackageServiceImpl.getInstance();
	public MaterialService ms= MaterialServiceImpl.getInstance();
	public List<UnitPackage> list = new ArrayList<UnitPackage>();
	public String materialAlias;
	public void onGet(){
		String message = getContext().getRequestParameter("sendMessage");
		String b = null;
		try {
			b = new String(message.getBytes("ISO8859_1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		list = ups.findByMateriaCode(b);
		List<Material> li1= ms.findByHql(" from Material m where  m.code ='" + b+ "' and m.state.code='"+MaterialState.NORMAL+"'");
		if(li1!=null&&li1.size()!=0){
			if(li1.get(0).getDescription()!=null){
				materialAlias=li1.get(0).getAlias()+ "    " +li1.get(0).getDescription();
			}else{
				materialAlias=li1.get(0).getAlias();
			}
		}
	}
	
	public String getContentType() {
		return "text/xml; charset=UTF-8";
	} 
}
