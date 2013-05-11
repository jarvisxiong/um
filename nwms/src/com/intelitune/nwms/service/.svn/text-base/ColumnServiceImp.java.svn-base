package com.intelitune.nwms.service;

import com.intelitune.nwms.dao.ColumnHome;
import com.intelitune.nwms.model.Column;
import com.intelitune.nwms.model.ColumnState;

public class ColumnServiceImp implements ColumnService {
	private final static ColumnServiceImp instance =new ColumnServiceImp();
	
	private ColumnServiceImp(){
		
	}
	
	public static final ColumnServiceImp getInstance() {
		return instance;
	}
	public ColumnHome ch=ColumnHome.getInstance();
	public ColumnStateService css=ColumnStateServiceImp.getInstance();
	public void addColumn(Column c) {
		ch.persist(c);
	}
	
	public Column findColumnByCode(String row_id,String column){
		return ch.findColumnByCode(row_id, column);
	}

	public void delColumnById(String id) {
		Column c=ch.findById(id);
		c.setState(css.findColumnStateByCode(ColumnState.DELETE));
		ch.attachDirty(c);
	}

	public int countColumnByRowId(String row_id) {
		return ch.countColumnByRowId(row_id);
	}

}
