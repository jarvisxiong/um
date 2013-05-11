package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.Customer;

public interface CustomerService {
	public List<Customer> findAll();

	public void save(Customer c);
	
	public Customer findById(int id);
	
	public void update(Customer c);
	
	public List<Customer> findByQuery(String hql);
}
