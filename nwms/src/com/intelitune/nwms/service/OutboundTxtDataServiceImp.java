package com.intelitune.nwms.service;

import java.util.ArrayList;
import java.util.List;

import com.intelitune.nwms.dao.OutboundTxtDataHome;
import com.intelitune.nwms.model.OutboundTxtData;

public class OutboundTxtDataServiceImp implements OutboundTxtDataService{
	private final static OutboundTxtDataServiceImp instance =new OutboundTxtDataServiceImp();
	
	private OutboundTxtDataServiceImp(){
		
	}
	
	public static final OutboundTxtDataServiceImp getInstance() {
		return instance;
	}
	public OutboundTxtDataHome otdh=OutboundTxtDataHome.getInstance();
	public void addOutboundTxtData(OutboundTxtData otd) {
		otdh.persist(otd);
	}
	
	
	
	public List<OutboundTxtData> findOutboundTxtDataByJob_id(String job_id) {
		return otdh.findOutboundTxtDataByJob_id(job_id);
	}
	
	
	public List<OutboundTxtData> comformTxtByJobId(String job_id) {
		List<OutboundTxtData> list=otdh.findOutboundTxtDataByJob_id(job_id);
		List<OutboundTxtData> result=new ArrayList<OutboundTxtData>();
		if(list!=null&&list.size()!=0){
			result.add(list.get(0));
			for(int i=1;i<list.size();i++){
				OutboundTxtData otd_list=list.get(i);
				int j=0;
				for(;j<result.size();j++){
					OutboundTxtData otd_result=result.get(j);
					if("N/A".equals(otd_list.getSN())&&"N/A".equals(otd_result.getSN())&&otd_list.getBinCode().equals(otd_result.getBinCode())&&otd_list.getProductCode().equals(otd_result.getProductCode())){
						if(otd_list.getOpType()==OutboundTxtData.outbound){
							float a=Float.parseFloat(otd_result.getQty());
							float b=Float.parseFloat(otd_list.getQty());
							otd_result.setQty((a+b)+"");
							break;
						}
						if(otd_list.getOpType()==OutboundTxtData.inbound){
							float a=Float.parseFloat(otd_result.getQty());
							float b=Float.parseFloat(otd_list.getQty());
							otd_result.setQty((a-b)+"");
							break;
						}
					}
				}
				if(j==result.size()){
					result.add(otd_list);
				}
			}
			//删除数量为0的项
//			for(int k=0;k<result.size();k++){
//				OutboundTxtData otd=result.get(k);
//				if(Float.parseFloat(otd.getQty())==0){
//					result.remove(k);
//				}
//			}
		}
		return result;
	}






	
}
