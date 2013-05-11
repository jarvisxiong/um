package com.intelitune.nwms.service;

import java.util.ArrayList;
import java.util.List;

import com.intelitune.nwms.inbound.TableInbound;
import com.intelitune.nwms.inbound.TxtData;
import com.intelitune.nwms.model.InboundOrderItem;

public class AutoStorageService {
	public InboundOrderItemService iois=InboundOrderItemServiceImp.getInstance();
	
	public List<TableInbound> autoStorage(List<TxtData> list_txt,String orderId){
		List<TableInbound> result=new ArrayList<TableInbound>();
		
		List<InboundOrderItem> list_item=iois.findNormalByOrderId(orderId);
		
		for(int i=0;i<list_item.size();i++){
			InboundOrderItem ioi=list_item.get(i);
			for(int j=0;j<list_txt.size();j++){
				TxtData td=list_txt.get(j);
				//有SN的情况
				if(!"N/A".equalsIgnoreCase(ioi.getSn())&&!"".equals(ioi.getSn())){
					if(ioi.getSn().equalsIgnoreCase(td.getSn())){
//						TableInbound ti=new TableInbound();
//						ti.setBincode(td.getBin_code());
//						ti.setFact_qty(td.getQty());
//						ti.setInvoice(ioi.getInvoice());
//						ti.setItemtype(ioi.getItemType());
//						ti.setProductcode(td.getProduct_code());
//						ti.setQty(ioi.getQty());
//						ti.setSn(ioi.getSn());
//						ti.set
					}
				}
			}
		}
		
		return result;
	}
}
