package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.common.UserConst;
import com.intelitune.nwms.dao.InttUserRoleHome;
import com.intelitune.nwms.model.InttUserRole;

public class UserRoleServiceImp implements UserRoleService {
	private final static UserRoleServiceImp instance =new UserRoleServiceImp();
	
	private UserRoleServiceImp(){
		
	}
	
	public static final UserRoleServiceImp getInstance() {
		return instance;
	}

	public InttUserRoleHome ih=InttUserRoleHome.getInstance();
	
	public void addUserRole(InttUserRole ur) {
		ih.persist(ur);
	}

	public InttUserRole findById(int id) {
		return ih.findById(id);
	}

	public void update(InttUserRole ur) {
		ih.attachDirty(ur);
	}
	
	public List<InttUserRole> findByQuery(String hql){
		return ih.findByQuery(hql);
	}
	
	public List getList() {
		List<InttUserRole> l = null;
		l = this.findByQuery("from InttUserRole as iur order by iur.userRoleId");
		for (int i = 0; i < l.size(); i++) {
			if (l.get(i).getInttUser().getUserStatus() == UserConst.LOCKED) {
				l.get(i).setBkt_lockStatus("locked");
			} else if (l.get(i).getInttUser().getUserStatus() == UserConst.UNLOCKED) {
				l.get(i).setBkt_lockStatus("active");
			}
		}
		return l;
	}
}
