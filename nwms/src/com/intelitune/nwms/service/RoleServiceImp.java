package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.InttRoleHome;
import com.intelitune.nwms.model.InttRole;

public class RoleServiceImp implements RoleService {
	private final static RoleServiceImp instance =new RoleServiceImp();
	
	private RoleServiceImp(){
		
	}
	
	public static final RoleServiceImp getInstance() {
		return instance;
	}

	public InttRoleHome irh=InttRoleHome.getInstance();
	public List<InttRole> findAll() {
		return irh.findAll();
	}

	public void addRole(InttRole r) {
		irh.persist(r);
	}

	public InttRole findById(int id) {
		return irh.findById(id);
	}

	public void Update(InttRole r) {
		irh.attachDirty(r);
	}

	public List<InttRole> findByquery(String hql) {
		return irh.findByquery(hql);
	}
	
	
	
	
}
