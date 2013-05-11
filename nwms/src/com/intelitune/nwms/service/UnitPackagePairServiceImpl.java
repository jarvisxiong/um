package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.UnitPackagePairHome;
import com.intelitune.nwms.model.UnitPackagePair;
/**
 * 
 * @author Louis
 *
 */
public class UnitPackagePairServiceImpl implements UnitPackagePairService {
	
	private final static UnitPackagePairServiceImpl instance =new UnitPackagePairServiceImpl();
	
	private UnitPackagePairServiceImpl(){
		
	}
	
	public static final UnitPackagePairServiceImpl getInstance() {
		return instance;
	}
	
	public UnitPackagePairHome upph = UnitPackagePairHome.getInstance();
	/**
	 * 
	 * @return List<UnitPackagePair>
	 * 查询所有的UnitPackagePair
	 */
	public List<UnitPackagePair> findAll() {
		return upph.findAll();
	}
	/**
	 * 
	 * @param Stringid
	 * @return UnitPackagePair
	 * 根据传入的UnitPackagePair的Id去查询对应的UnitPackagePair
	 */
	public UnitPackagePair getUnitPackagePair(String id) {
		return upph.getUnitpackagePair(id);
	}
	/**
	 * 
	 * @param UnitPackagePair
	 * @return void
	 * 保存UnitPackagePair
	 */
	public void saveUnitPackagePair(UnitPackagePair unitPackagePair) {
		upph.saveUnitPackagePair(unitPackagePair);
	}
	/**
	 * 
	 * @param UnitPackagePair
	 * @return void
	 * 更新UnitPackagePair
	 */
	public void updateUnitPackagePair(UnitPackagePair unitPackagePair) {
		upph.updatePackagePair(unitPackagePair);
	}
	/**
	 * 
	 * @param String id
	 * @return List<UnitPackagePair>
	 * 根据传入的UnitPackage的Id查询出对应的UnitPackagePair
	 */
	public List<UnitPackagePair> findByUnitPackageId(String id) {
		String hql = " from UnitPackagePair i where i.unitPackage.id='" + id + "'";
		return upph.findByHql(hql);
	}
	
	public void Lock(UnitPackagePair unitPackagePair){
		upph.attachDirty(unitPackagePair);
	}

	
}
