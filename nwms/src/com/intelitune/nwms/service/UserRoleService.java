package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.common.UserConst;
import com.intelitune.nwms.model.InttUserRole;

public interface UserRoleService {
	public void addUserRole(InttUserRole ur);
	
	public InttUserRole findById(int id);
	
	public void update(InttUserRole ur);
	
	public List getList() ;
	
	public List<InttUserRole> findByQuery(String hql);

}
