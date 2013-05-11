package com.intelitune.nwms.service;

import com.axis2.services.MCSserviceStub;
import com.intelitune.ccos.increasedWebService.WMSServiceImp;

public class MCSService {
	public static PropertyService ps;
	public  static String serviceURL;
	public static MCSserviceStub stub;
	private final static MCSService instance =new MCSService();
	
	private MCSService(){

	}
	
	public static final MCSService getInstance() {
		return instance;
	}
	
	static{
		 ps=new PropertyService();
		 serviceURL = ps.getPropertyValue("mcs_service");
		 try{
			 stub = new MCSserviceStub(serviceURL);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
	 

	public MCSserviceStub.InttClientDetailWS[] getAllClient() {
		try {
//			MCSserviceStub stub = new MCSserviceStub(serviceURL);
			MCSserviceStub.GetAllClientResponse res = stub.getAllClient();
			return res.get_return();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public MCSserviceStub.InttClientDetailWS findClientById(int id) {
		try {
//			MCSserviceStub stub = new MCSserviceStub(serviceURL);
			MCSserviceStub.FindClientById req_FindClientById = new MCSserviceStub.FindClientById();
			req_FindClientById.setId(id);
			MCSserviceStub.FindClientByIdResponse res_FindClientById = stub.findClientById(req_FindClientById);
			MCSserviceStub.InttClientDetailWS icd = res_FindClientById.get_return();
			return icd;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
