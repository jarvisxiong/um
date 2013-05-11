package com.intelitune.ccos.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.intelitune.nwms.model.ItemState;

public class Test3
{

	/**
	 * @author wzz
	 * @lastModify Jan 15, 2009 11:13:01 AM
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		
		int orderId=4;
		String str= "from OutboundOrderItem o where  o.state.code='0' and  o.unitPackage.id='402880ee1ff4c5c4011ff4cda0aa0e66' and o.orderId in ('239','244')";
		

		
		List li= HibernateSessionFactory.getSession().createQuery(str).list();
	System.out.println(li.size());
//		Iterator itq= li.iterator();
//		if (itq.hasNext())
//		{
//			Object[] result= (Object[]) itq.next();
//			System.out.println( Integer.valueOf( result[1].toString()));
//		}
	}

}
