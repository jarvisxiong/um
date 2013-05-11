package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.UnitPackagePair;
/**
 * 
 * @author Louis
 *
 */
public interface UnitPackagePairService {
	/**
	 * 
	 * @return List<UnitPackagePair>
	 * 查询所有的UnitPackagePair
	 */
	List<UnitPackagePair> findAll();
	/**
	 * 
	 * @param Stringid
	 * @return UnitPackagePair
	 * 根据传入的UnitPackagePair的Id去查询对应的UnitPackagePair
	 */
	UnitPackagePair getUnitPackagePair(String id);
	/**
	 * 
	 * @param UnitPackagePair
	 * @return void
	 * 保存UnitPackagePair
	 */
	void saveUnitPackagePair(UnitPackagePair unitPackagePair);
	/**
	 * 
	 * @param UnitPackagePair
	 * @return void
	 * 更新UnitPackagePair
	 */
	void updateUnitPackagePair(UnitPackagePair unitPackagePair);
	/**
	 * 
	 * @param String id
	 * @return List<UnitPackagePair>
	 * 根据传入的UnitPackage的Id查询出对应的UnitPackagePair
	 */
	List<UnitPackagePair> findByUnitPackageId(String id);
	void Lock(UnitPackagePair unitPackagePair);
}
