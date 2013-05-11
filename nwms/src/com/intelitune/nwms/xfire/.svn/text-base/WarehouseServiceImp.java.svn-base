package com.intelitune.nwms.xfire;

import java.util.List;

import org.hibernate.Transaction;

import com.intelitune.nwms.dao.WarehouseHome;
import com.intelitune.nwms.model.Warehouse;

public class WarehouseServiceImp implements WarehouseService {
	public WarehouseHome wh=WarehouseHome.getInstance();
	public Warehouse[] findWarehouseList() {
		Transaction tr = wh.getSessionFactory().getCurrentSession().beginTransaction();
		List<Warehouse> list= wh.findWarehouseList();
		tr.commit();
		Warehouse[] w= new Warehouse[list.size()];
		for(int i=0;i<list.size();i++){
			Warehouse w1=new Warehouse();
			w1.setId(list.get(i).getId());
			w1.setAlias(list.get(i).getAlias());
			w[i]=w1;
		}
		return w;
	}
	


	public Warehouse findWarehouseById(String id) {
		Transaction tr = wh.getSessionFactory().getCurrentSession().beginTransaction();
		Warehouse w= wh.findById(id);
		tr.commit();
		return w;
	}
}
