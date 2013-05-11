package com.intelitune.nwms.service;

import java.util.ArrayList;
import java.util.List;

import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.nwms.model.OutboundOrderItem;
import com.intelitune.nwms.model.StorageItem;

public class CreatePickingItem {
	public  StorageItemService sis=StorageItemServiceImp.getInstance();
	public WMSServiceInf wsi= WMSServiceImp.getInstance();
	
	public void CreateByFifo(OutboundOrderItem ooi,int order_id){
		List<StorageItem> slist=new ArrayList<StorageItem>();
		WmsOrder wo=new WmsOrder();
		try{
			 wo=wsi.findWmsOrderById(order_id);
		}catch(Exception e){
			e.printStackTrace();
		}
		slist=sis.findStorageItemByWmsOrder(wo,ooi.getMaterial().getCode());
		
		
		
		
		
	}

}
