package com.intelitune.nwms.service;

import com.intelitune.nwms.model.Column;

public interface ColumnService {
	public void addColumn(Column c);
	
	public Column findColumnByCode(String row_id,String column);
	
	public void delColumnById(String id);
	
	public int countColumnByRowId(String row_id);
}
