package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.OutboundTxtData;

public interface OutboundTxtDataService {
	public void addOutboundTxtData(OutboundTxtData otd);
	
	
	public List<OutboundTxtData> findOutboundTxtDataByJob_id(String job_id);
	
	
	public List<OutboundTxtData> comformTxtByJobId(String job_id);
}
