package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.UnitStateHome;
import com.intelitune.nwms.model.UnitState;
/**
 * 
 * @author Louis
 *
 */
public class UnitStateServiceImpl implements UnitStateService {
	
	private final static UnitStateServiceImpl instance =new UnitStateServiceImpl();
	
	private UnitStateServiceImpl(){
		
	}
	
	public static final UnitStateServiceImpl getInstance() {
		return instance;
	}
	public UnitStateHome ush = UnitStateHome.getInstance();
	/**
	 * 
	 * @return List<UnitState>
	 * 查询所有的UnitState
	 */
	public List<UnitState> findAll() {		
		return ush.findAll();
	}
	/**
	 * 
	 * @param String code
	 * @return UnitState
	 * 根据UnitState的code查询对应的UnitState
	 */
	public UnitState getUnitState(String code) {
		return ush.getUnitState(code);
	}
	/**
	 * 
	 * @param UnitState unitState
	 * @return void
	 * 保存UnitState
	 */
	public void saveUnitState(UnitState unitState) {
		ush.saveUnitState(unitState);
	}
	/**
	 * 
	 * @param UnitState unitState
	 * @return void
	 * 更新UnitState
	 */
	public void updateUnitState(UnitState unitState) {
		ush.updateUnitState(unitState);
	}
	/**
	 * 
	 * @param String id
	 * @return UnitState
	 * 根据UnitState的Id查询对应的UnitState
	 */
	public UnitState findUnitState(String id) {
		return ush.findUnitState(id); 
	}

}
