package com.intelitune.ccos.client;

import java.util.Arrays;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
WMSServicePortType wmsService = new WMSServicePortTypeProxy();
		
		
		
		//for(int i=0;i<109-1;i++){		
			//WmsOrder order = (WmsOrder)wmsService.findAll()[107];
//		WmsOrder order;
//		try {
//			order = (WmsOrder)wmsService.findById(730);
//			System.out.println(order.getId());
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		String hql ="from WmsOrder i where i.creationTime<='2009-12-10 23:59:59.999' and i.creationTime>='2009-12-10 00:00:00.0' and i.orderStatus<>4 order by i.id desc";
		List li;
		Object[] objs = wmsService.query(hql);
		 li= Arrays.asList(objs);
		 System.out.println(li.get(0));
		
			//if(order.getHawb()!=null){
				//System.out.println(order.getHawb());
			//}
		//}
	}

}
