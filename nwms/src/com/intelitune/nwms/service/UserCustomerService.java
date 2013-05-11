package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.InttUserCustomer;

public interface UserCustomerService {

	public void save(InttUserCustomer uc);
	
	public List<InttUserCustomer> findByQuery(String hql);
	
	public void delete(InttUserCustomer uc);
}
