package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.UnitPackage;
/**
 * 
 * @author Louis
 *
 */
public interface UnitPackageService {
	/**
	 * 
	 * @return List<UnitPackage>
	 * 查询所有的UnitPackage
	 */
	List<UnitPackage> findAll();
	/**
	 * 
	 * @param String id
	 * @return UnitPackage
	 * 根据传入的UnitPackage的Id查询出相应的UnitPackage
	 */
	UnitPackage getUnitPackage(String id);
	/**
	 *	@author     wzz
	 *	@lastModify Jan 15, 2009  10:08:43 AM
	 * @param alias
	 * @return
	 */
	UnitPackage findByAlias(String alias);
	/**
	 * 
	 * @param UnitPackage unitPackage
	 * @return void
	 * 保存UnitPackage
	 */
	void saveUnitPackage(UnitPackage unitPackage);
	/**
	 * 
	 * @param UnitPackage unitPackage
	 * @return void
	 * 更新UnitPackage
	 */
	void updateUnitPackage(UnitPackage unitPackage);
	/**
	 * 
	 * @param String id
	 * @return List<UnitPackage>
	 * 根据传入的Material的Id查询出相应的UnitPackage
	 */
	List<UnitPackage> findByMateialId(String id);
	
	/**
	 * 
	 * @param String alias
	 * @return List<UnitPackage>
	 */
	List<UnitPackage> findByMaterialAlias(String alias); 
	List<UnitPackage> findByMateriaCode(String code);
}
