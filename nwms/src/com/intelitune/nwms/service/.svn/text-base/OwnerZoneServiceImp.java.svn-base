package com.intelitune.nwms.service;

import java.util.ArrayList;
import java.util.List;

import com.axis2.services.MCSserviceStub;
import com.axis2.services.MCSserviceStub.InttClientDetailWS;
import com.intelitune.nwms.dao.OwnerZoneHome;
import com.intelitune.nwms.model.OwnerZone;
import com.intelitune.nwms.model.ZoneState;


public class OwnerZoneServiceImp implements OwnerZoneService {
	
	private final static OwnerZoneServiceImp instance =new OwnerZoneServiceImp();
	
	private OwnerZoneServiceImp(){
		
	}
	
	public static final OwnerZoneServiceImp getInstance() {
		return instance;
	}
 
public  OwnerZoneHome ozh=OwnerZoneHome.getInstance();
public ZoneStateService zss=ZoneStateServiceImp.getInstance();
MCSService mcsService =  MCSService.getInstance();
	 
 public List<InttClientDetailWS> findOwnerList() {
	 MCSserviceStub.InttClientDetailWS[] result=null;
	 try{
			result=mcsService.getAllClient();
	 }catch(Exception e){
		 e.printStackTrace();
	 }
		List<InttClientDetailWS> list=new ArrayList<InttClientDetailWS>();

		for(int i=0;i<result.length;i++){
			list.add((InttClientDetailWS)result[i]);
		}
		return list;
	}

	public InttClientDetailWS findOwnerById(int id) {
		MCSserviceStub.InttClientDetailWS icd1=null;
		try{
			icd1=mcsService.findClientById(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return icd1;
	}

	public void addOwnerZone(OwnerZone oz) {
		ozh.persist(oz);
	}

	public List<OwnerZone> findOwnerZoneList() {
		return ozh.findOwnerZoneList();
	}

	public void delOwnerZone(String id) {
		OwnerZone oz=ozh.findById(id);
		oz.setState(zss.findZoneStateByCode(ZoneState.DELETE));
		ozh.attachDirty(oz);
	}

	public OwnerZone findOwnerZoneById(String id) {
		return ozh.findOwnerZoneById(id);
	}

	public void modifyOwnerZone(OwnerZone oz) {
		ozh.attachDirty(oz);
	}


	
	
}
