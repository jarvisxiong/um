package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.UnitPackageStateHome;
import com.intelitune.nwms.model.UnitPackageState;
/**
 * 
 * @author Louis
 *
 */
public class UnitPackageStateServiceImpl implements UnitPackageStateService {
	
	private final static UnitPackageStateServiceImpl instance =new UnitPackageStateServiceImpl();
	
	private UnitPackageStateServiceImpl(){
		
	}
	
	public static final UnitPackageStateServiceImpl getInstance() {
		return instance;
	}
	public UnitPackageStateHome upsh = UnitPackageStateHome.getInstance();
	/**
	 * 
	 * @param UnitPackage unitPackageState
	 * @return void
	 * 保存UnitPackageState
	 */
	public List<UnitPackageState> findAll() {	
		return upsh.findAll();
	}
	/**
	 * 
	 * @param UnitPackage unitPackageState
	 * @return void
	 * 更新UnitPackageState
	 */
	public UnitPackageState getUnitPackageState(String code) {	
		return upsh.getUnitPackageState(code);
	}
	/**
	 * 
	 * @param String code
	 * @return UnitPackageState
	 * 根据传入的UnitPackageState的code查询相应的UnitPackageState
	 */
	public void saveUnitPackageState(UnitPackageState unitPackageState) {
		upsh.saveUnitPackageState(unitPackageState);
	}
	/**
	 * 
	 * @return List<UnitPackageState>
	 * 查询所有的UnitPackageState
	 */
	public void updateUnitPackageState(UnitPackageState unitPackageState) {
		upsh.updateUnitPackageState(unitPackageState);
	}
	/**
	 * 
	 * @param String id
	 * @return UnitPackageState
	 * 根据传入的UnitPackageState的Id查询相应的UnitPackageState
	 */
	public UnitPackageState findUnitPackageState(String id) {
		return upsh.findUnitPackageState(id);
	}

}
