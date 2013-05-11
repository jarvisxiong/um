package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.Row;
import com.intelitune.nwms.model.Zone;

public interface RowService {
	public List<Row> findRowListByFloor_id(String floor_id);
		
	public void addRow(Row r,int tier,int column,List<Zone> list);
	
	public void delRowById(String id);
	
	public Row findRowById(String id);
	
	public void modifyRow(Row r);
	
	public String findNameById(String id);
}
