/**  
 * ProdMateAction.java  
 * com.hhz.ump.web.prod  
 *  
 * Function： TODO   
 *  
 *   ver     date           author  
 * ──────────────────────────────────  
 *           2011-11-7        zhongyubing  
 *  
 * Copyright (c) 2011, TNT All Rights Reserved.  
*/  
  
package com.hhz.ump.web.prod;  

import org.apache.struts2.convention.annotation.Namespace;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.entity.prod.ProdMaterialPrice;

/**
 *  
 * @author   zhongyubing  
 * @version    
 * @since    Ver 1.1  
 * @Date     2011-11-7        下午01:53:37  
 * 
 */

@Namespace("/prod")
public class ProdMateAction extends CrudActionSupport<ProdMaterialPrice> {

	
	
	private static final long serialVersionUID = 6159250049682879444L;

	@Override
	public String delete() throws Exception {
		  
		// TODO Auto-generated method stub  
		return null;  
		
	}

	@Override
	public String deleteBatch() throws Exception {
		  
		// TODO Auto-generated method stub  
		return null;  
		
	}

	@Override
	public String input() throws Exception {
		  
		// TODO Auto-generated method stub  
		return null;  
		
	}

	@Override
	public String list() throws Exception {
		  
		// TODO Auto-generated method stub  
		return "list";  
		
	}

	@Override
	protected void prepareModel() throws Exception {
		  
		// TODO Auto-generated method stub  
		
	}

	@Override
	public String save() throws Exception {
		  
		// TODO Auto-generated method stub  
		return null;  
		
	}

	@Override
	public ProdMaterialPrice getModel() {
		  
		// TODO Auto-generated method stub  
		return null;  
		
	}

}
  
