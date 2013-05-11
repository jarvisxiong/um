package com.intelitune.ccos.increasedWebService;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.intelitune.ccos.client.WMSServicePortTypeProxy;
import com.intelitune.ccos.model.WmsOrder;
import com.intelitune.nwms.common.Const;
import com.intelitune.nwms.dao.BinHome;
import com.intelitune.nwms.model.ItemState;

public class WMSServiceImp  extends WMSServicePortTypeProxy implements WMSServiceInf
{

	private final static WMSServiceImp instance =new WMSServiceImp();
	
	private WMSServiceImp(){
		super();
	}
	
	public static final WMSServiceImp getInstance() {
		return instance;
	}
	
	
//	public WMSServiceImp()
//	{
//		super();
//		
//	}

	public WMSServiceImp(String endpoint)
	{
		super(endpoint);
		
	}
	

	public List findAllWmsOrder() throws RemoteException
	{
		List li =Arrays.asList(super.findAll());
		return li;
	}


	
	public void alterToOutboundInPicking(int wmsOrderId)
	{
		String str="update WmsOrder w  set w.wmsOrderStatus="+Const.ORDER_TYPE_OUTBOUND_IN_PICKING +" where w.id="+wmsOrderId;
		try
		{
			super.update(str);
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterToOutboundComplete(int wmsOrderId)
	{
		String str="update WmsOrder w  set w.wmsOrderStatus="+Const.ORDER_TYPE_OUTBOUND_COMPLETE +" where w.id="+wmsOrderId;
		try
		{
			super.update(str);
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WmsOrder findWmsOrderById(int in0) throws RemoteException
	{
		
		return super.findById(in0);
	}

	public WmsOrder findByJobId(String in0) throws RemoteException
	{
		return super.findByJobId(in0);
	}

	
	
	public  List findOutboundType() throws RemoteException
	{ 
		String str="from WmsOrder w where w.wmsOrderType='"+Const.TYPE_OUTBOUND+"' order by creationTime desc";
		Object[] wmsOrderArray =(Object[])this.queryWmsOrder(str);
		 
		return Arrays.asList(wmsOrderArray);
	}
	public  List  findInboundType() throws RemoteException
	{
		String str="from WmsOrder w where w.wmsOrderType='"+Const.TYPE_INBOUND+"'  order by creationTime desc";
		Object[] wmsOrderArray =(Object[])this.queryWmsOrder(str);

		return Arrays.asList(wmsOrderArray);
	}



	@SuppressWarnings("unchecked")


	public List queryStr(String s) throws RemoteException
	{
		//this.queryWmsOrder(s);
		return Arrays.asList(this.queryWmsOrder(s));
	}

	public List<WmsOrder> findAlreadyOutboundOrder() throws RemoteException
	{
		String str="from WmsOrder w where w.wmsOrderStatus=6 order by creationTime desc";
		List<WmsOrder> li =this.queryStr(str);
		return li;
	}

	public List findNotOutboundOrder() throws RemoteException
	{
		
		String str="from WmsOrder w where w.wmsOrderStatus=4 order by creationTime desc";
		List<WmsOrder> li =this.queryStr(str);
		return li;
	}

	public void addItemToOrder(WmsOrder wmsOrder, String itemId)
	{
		try
		{
			super.refWmsOrder(wmsOrder, itemId);
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteItemfromOrder(WmsOrder wmsOrder, String itemId)
	{
		try
		{
			super.deRefWmsOrder(wmsOrder, itemId);
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<WmsOrder> findOutboundInPicking()
	{
		String str="from WmsOrder w where w.wmsOrderStatus="+Const.ORDER_TYPE_OUTBOUND_IN_PICKING+" order by creationTime desc";
		 List  li=null;
		try
		{
			li= Arrays.asList(super.queryWmsOrder(str));
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return li;
	}

	public void alterToInboundChecked(int wmsOrderId)
	{
		String str="update WmsOrder w  set w.wmsOrderStatus="+Const.ORDER_TYPE_INBOUND_CHECKED+" where w.id="+wmsOrderId+" ";
		try
		{
			super.update(str);
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterToInboundComplete(int wmsOrderId)
	{
		String str="update WmsOrder w  set w.wmsOrderStatus="+Const.ORDER_TYPE_INBOUND_COMPLETE +" where w.id="+wmsOrderId;
		try
		{
			super.update(str);
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveOrUpdate(Object obj)
	{
		try
		{
			super.save(obj);
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	


	
	
	
}