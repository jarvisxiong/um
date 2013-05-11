package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.InvoiceHome;
import com.intelitune.nwms.model.Invoice;

public class InvoiceServiceImp implements InvoiceService
{
	private final static InvoiceServiceImp instance =new InvoiceServiceImp();
	
	private InvoiceServiceImp(){
		
	}
	
	public static final InvoiceServiceImp getInstance() {
		return instance;
	}
	InvoiceHome home=InvoiceHome.getInstance();
	public Invoice findById(String str)
	{
		return home.findById(str);
	}

	public void save(Invoice invoice)
	{
		home.persist(invoice);
	}

	public void update(Invoice invoice)
	{
		home.merge(invoice);
	}

	public List<Invoice> query(String str)
	{
		return home.query(str);
	}

	public void saveOrUpdate(Invoice invoice)
	{
		home.attachDirty(invoice);
	}

	public Invoice findByCode(String code) {
		return home.findByCode(code);
	}
	
}
