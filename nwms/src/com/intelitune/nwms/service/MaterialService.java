package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.model.Material;

/**
 * 
 * @author Louis
 *
 */
public interface MaterialService {

	/**
	 * @param Material material
	 * @return void
	 * 保存料件
	 */
	void saveMaterial(Material material);
	/**
	 * @param String hql
	 * @return List<Material>
	 * 使用hql查询
	 */
	List<Material> findByHql(String hql);
	/**
	 * @param String id
	 * @return Material 
	 * 根据material的Id来查询料件
	 */
	Material findById(String id);
	

	/**
	 *	@author     wzz
	 *	@lastModify Jan 15, 2009  9:54:29 AM
	 * @param alias
	 * @return Material
	 */
	Material findByAlias(String alias);
	
	/**
	 * @param Material material
	 * @return void
	 * 更新material
	 */
	void updateMaterial(Material material);
	/**
	 * 
	 * @param Float value
	 * @param String inttClientDetailWSId
	 * @param String alias
	 * @param String code
	 * @return List<Material>
	 * 根据所传入的四个条件生成相应的Hql语句去查询
	 */
	List<Material> searchMaterial(Float value,String inttClientDetailWSId,String alias,String code);
	
	/**
	 * 
	 * @param String code
	 * @return Material
	 * 根据传入的code值选出相应的料件
	 */
	Material findMaterialByCode(String code,String inttClientDetailWSId);
	
	List<Material> findNormalMaterialByOwner(String inttClientDetailWSId);
}
