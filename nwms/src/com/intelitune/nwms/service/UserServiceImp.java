package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.InttUserHome;
import com.intelitune.nwms.model.InttUser;

public class UserServiceImp implements UserService {
	private final static UserServiceImp instance =new UserServiceImp();
	
	private UserServiceImp(){
		
	}
	
	public static final UserServiceImp getInstance() {
		return instance;
	}
	
	public  InttUserHome uh=InttUserHome.getInstance();
	
	public void save(InttUser iu){
		uh.persist(iu);
	}

	public List<InttUser> findByQuery(String hql) {
		return uh.findByQuery(hql);
	}

	public InttUser findById(int id) {
		return uh.findById(id);
	}

	public void update(InttUser u) {
		uh.attachDirty(u);
	}
	

}
