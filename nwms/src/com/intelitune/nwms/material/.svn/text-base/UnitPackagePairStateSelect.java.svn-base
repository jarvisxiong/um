package com.intelitune.nwms.material;

import java.util.ArrayList;
import java.util.List;

import net.sf.click.control.Option;
import net.sf.click.control.Select;

import com.intelitune.nwms.model.UnitPackagePairState;
import com.intelitune.nwms.service.UnitPackagePairStateService;
import com.intelitune.nwms.service.UnitPackagePairStateServiceImpl;

public class UnitPackagePairStateSelect extends Select {
	
	public UnitPackagePairStateService uppss =  UnitPackagePairStateServiceImpl.getInstance();
	
	private static final long serialVersionUID = 1L;

	  public final List LIST_UNITPACKAGESTATESELECT = new ArrayList();
	  
	  {
	    	List<UnitPackagePairState> listCurrency = uppss.findALl();
	    	for(int i = 0; i < listCurrency.size(); i++) {
	    		LIST_UNITPACKAGESTATESELECT.add(new Option(String.valueOf(listCurrency.get(i).getId()), listCurrency.get(i).getDescription()));
	    	}
	    }
	  
	  public UnitPackagePairStateSelect(String name) {
	        super(name);
	        setOptionList(LIST_UNITPACKAGESTATESELECT);
	    }

	    /**
	     * Create the Investment option Select control.
	     */
	    public UnitPackagePairStateSelect() {
	        super();
	        setOptionList(LIST_UNITPACKAGESTATESELECT);
	    }
}
