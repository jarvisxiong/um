package com.intelitune.nwms.service;

import com.intelitune.nwms.dao.OutboundRePickItemStateHome;
import com.intelitune.nwms.model.OutboundRePickItemState;

public class OutboundRePickItemStateServiceImp implements
		OutboundRePickItemStateService {
	private final static OutboundRePickItemStateServiceImp instance =new OutboundRePickItemStateServiceImp();
	
	private OutboundRePickItemStateServiceImp(){
		
	}
	
	public static final OutboundRePickItemStateServiceImp getInstance() {
		return instance;
	}
	OutboundRePickItemStateHome oh=OutboundRePickItemStateHome.getInstance();
	
	public OutboundRePickItemState findStateByCode(int code){
		return oh.findStateByCode(code);
	}
	

}
