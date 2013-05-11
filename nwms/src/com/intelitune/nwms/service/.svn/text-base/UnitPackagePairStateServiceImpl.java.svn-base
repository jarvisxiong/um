package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.UnitPackagePairStateHome;
import com.intelitune.nwms.model.UnitPackagePairState;
/**
 * 
 * @author Louis
 *
 */
public class UnitPackagePairStateServiceImpl implements UnitPackagePairStateService {
	
	private final static UnitPackagePairStateServiceImpl instance =new UnitPackagePairStateServiceImpl();
	
	private UnitPackagePairStateServiceImpl(){
		
	}
	
	public static final UnitPackagePairStateServiceImpl getInstance() {
		return instance;
	}
	public UnitPackagePairStateHome uppsh = UnitPackagePairStateHome.getInstance();
	/**
	 * 
	 * @param String code
	 * @return UnitPackagePairState
	 * 根据UnitPackagePairState的code来查询相应的UnitPackagePairState
	 */
	public List<UnitPackagePairState> findALl() {
		return uppsh.findAll();
	}
	/**
	 * 
	 * @return List<UnitPackagePairState>
	 * 查询出所有的UnitPackagePairState
	 */
	public UnitPackagePairState getUnitPackagePairState(String code) {
		return uppsh.getUnitPackagePairState(code);

	}
	/**
	 * 
	 * @param UnitPackagePairState unitPackagePairState
	 * @return void
	 * 保存UnitPackagePairState
	 */
	public void saveUnitPackagePairState(
			UnitPackagePairState unitPackagePairStatet) {
		uppsh.saveUnitPackagePairState(unitPackagePairStatet);		
	}
	/**
	 * 
	 * @param UnitPackagePairState unitPackagePairState
	 * @return void
	 * 更新UnitPackagePairState
	 */
	public void updateUnitPackagePairState(
			UnitPackagePairState unitPackagePairState) {
		uppsh.updatePackagePairState(unitPackagePairState);
	}
	/**
	 * 
	 * @param String id
	 * @return UnitPackagePairState
	 * 根据传入的UnitPackagePairState的Id来查询相应的UnitPackagePairState
	 */
	public UnitPackagePairState findUnitPackagePairState(String id) {
		return uppsh.findUnitPackagePairState(id);
	}

}
