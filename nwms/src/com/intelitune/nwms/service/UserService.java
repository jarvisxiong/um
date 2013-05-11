package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.InttUser;

public interface UserService {
	public void save(InttUser iu);
	
	public List<InttUser> findByQuery(String hql);
	
	public InttUser findById(int id);
	
	public void update(InttUser u);

}
