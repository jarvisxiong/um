package com.intelitune.nwms.service;

import com.intelitune.nwms.dao.OutboundRePutAwayItemStateHome;
import com.intelitune.nwms.model.OutboundRePutAwayItemState;

public class OutboundRePutAwayItemStateServiceImp implements
		OutboundRePutAwayItemStateService {
	private final static OutboundRePutAwayItemStateServiceImp instance =new OutboundRePutAwayItemStateServiceImp();
	
	private OutboundRePutAwayItemStateServiceImp(){
		
	}
	
	public static final OutboundRePutAwayItemStateServiceImp getInstance() {
		return instance;
	}
	OutboundRePutAwayItemStateHome oh=OutboundRePutAwayItemStateHome.getInstance();
	public OutboundRePutAwayItemState findStateByCode(int code) {
		return oh.findStateByCode(code);
	}

}
