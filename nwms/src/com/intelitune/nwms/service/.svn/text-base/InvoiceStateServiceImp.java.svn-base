package com.intelitune.nwms.service;

import com.intelitune.nwms.dao.InvoiceStateHome;
import com.intelitune.nwms.model.Invoice;
import com.intelitune.nwms.model.InvoiceState;

/**
 *	@author     wzz
 *	@lastModify Mar 8, 2009  5:08:57 PM
 */
public class InvoiceStateServiceImp implements InvoiceStateService
{

	private final static InvoiceStateServiceImp instance =new InvoiceStateServiceImp();
	
	private InvoiceStateServiceImp(){
		
	}
	
	public static final InvoiceStateServiceImp getInstance() {
		return instance;
	}
	InvoiceStateHome home=InvoiceStateHome.getInstance();
	public InvoiceState query(String str)
	{
		return home.query(str).get(0);
	}

	public void save(InvoiceState st)
	{
		home.persist(st);
	}

	public void saveOrUpdate(InvoiceState st)
	{
		home.attachDirty(st);
	}

	public void update(InvoiceState st)
	{
		home.merge(st);
	}
	
}
