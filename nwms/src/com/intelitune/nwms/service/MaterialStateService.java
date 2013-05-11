package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.MaterialState;

/**
 * 
 * @author Louis
 *
 */
public interface MaterialStateService {
	
	/**
	 * @param String id
	 * @return MaterialState 
	 * 根据materialState的id去查询MaterialState
	 */
	MaterialState getMaterialState(String id);
	/**
	 * 
	 * @return List<MaterialState>
	 * 查询所有的materialState
	 */
	List<MaterialState> findAll();
	/**
	 * 
	 * @param MaterialState materialState
	 * @return void
	 * 保存MaterialState
	 */
	void saveMaterialState(MaterialState materialState);
	/**
	 * 
	 * @param MaterialState materialState
	 * @return void
	 * 更新MaterialState
	 */
	void updateMaterialState(MaterialState materialState);
	/**
	 * 
	 * @param String code
	 * @return MaterialState 
	 * 根据code来查询MaterialState
	 */
	MaterialState findMaterialState(String code);
}
