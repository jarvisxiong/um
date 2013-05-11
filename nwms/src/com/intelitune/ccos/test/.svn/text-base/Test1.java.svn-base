package com.intelitune.ccos.test;

import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import com.intelitune.ccos.increasedWebService.WMSServiceImp;
import com.intelitune.ccos.increasedWebService.WMSServiceInf;
import com.intelitune.ccos.model.WmsOrder;

public class Test1
{
	public static void main(String[] args) throws Exception
	{

		WMSServiceInf service= WMSServiceImp.getInstance();
		// WmsOrder wmsOrder=new WmsOrder();
		// wmsOrder.setId(6);
		// service.deleteItemfromOrder(wmsOrder,
		// "402880da1ef86ef3011ef86fefae0001");

		// WmsOrder wmsOrder= service.findWmsOrderById(1);
		//
		// System.out.println(wmsOrder.getId());
		// System.out.println(wmsOrder.getWarehouseAlias());
		// System.out.println(wmsOrder.getOrderStatus().getName());
//		FileWriter out= new FileWriter("c:\\2.txt",true);
//		
//			List<WmsOrder> li2= service.findAllWmsOrder();
//
//			for (WmsOrder w : li2)
//			{
//				String str=w.getWmsOrderStatus().getName();
//				out.write( str+ "\n");
//				System.out.println(str);
//			}
//
//		
//		out.close();
		for (int i= 0; i < 1; i++)
		{
			new ThreadTest().start();
		}
		
	}
}

/**
 *	@author     wzz
 *	@lastModify Feb 3, 2009  6:34:12 PM
 */
class ThreadTest extends Thread
{

	WMSServiceInf service= WMSServiceImp.getInstance();
	public void run() 
	{
		FileWriter out;
		try
		{
			out= new FileWriter("c:\\2.txt",true);
			
			List<WmsOrder> li2= service.findAllWmsOrder();

			for (WmsOrder w : li2)
			{
				String str=w.getOrderStatus().getName();
				out.write( str+ "\n");
				
			}
			out.close();
		} catch (RemoteException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}