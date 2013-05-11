package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.Unit;
/**
 * 
 * @author Louis
 *
 */
public interface UnitService {
	/**
	 * 
	 * @return List<Unit>
	 * 查询所有的Unit
	 */
	List<Unit> findAll();
	/**
	 * 
	 * @param String id
	 * @return Unit
	 * 根据Unit的Id来返回Unit
	 */
	Unit getUnit(String id);
	/**
	 * 
	 * @param unit
	 * @return void
	 * 保存Unit
	 */
	void saveUnit(Unit unit);
	/**
	 * 
	 * @param unit
	 * @return void
	 * 更新Unit
	 */
	void updateUnit(Unit unit);
	
	List<Unit> findUnitByCode(String code);

}
