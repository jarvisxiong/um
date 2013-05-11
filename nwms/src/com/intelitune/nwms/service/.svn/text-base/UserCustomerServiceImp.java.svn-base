package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.InttUserCustomerHome;
import com.intelitune.nwms.model.InttUserCustomer;

public class UserCustomerServiceImp implements UserCustomerService {
	private final static UserCustomerServiceImp instance =new UserCustomerServiceImp();
	
	private UserCustomerServiceImp(){
		
	}
	
	public static final UserCustomerServiceImp getInstance() {
		return instance;
	}

	public InttUserCustomerHome iuch=InttUserCustomerHome.getInstance();
	
	public void save(InttUserCustomer uc) {
		iuch.persist(uc);
	}

	public List<InttUserCustomer> findByQuery(String hql) {
		return iuch.findByQuery(hql);
	}

	public void delete(InttUserCustomer uc) {
		iuch.delete(uc);
	}
}
