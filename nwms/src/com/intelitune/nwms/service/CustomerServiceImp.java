package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.CustomerHome;
import com.intelitune.nwms.model.Customer;

public class CustomerServiceImp implements CustomerService {
private final static CustomerServiceImp instance =new CustomerServiceImp();
	
	private CustomerServiceImp(){
		
	}
	
	public static final CustomerServiceImp getInstance() {
		return instance;
	}
	
	public CustomerHome ch=CustomerHome.getInstance();
	
	public List<Customer> findAll() {
		return ch.findAll();
	}

	public void save(Customer c) {
		ch.persist(c);
	}

	public Customer findById(int id) {
		return ch.findById(id);
	}
	
	public void update(Customer c){
		ch.attachDirty(c);
	}

	public List<Customer> findByQuery(String hql) {
		return ch.findByquery(hql);
	}
}
