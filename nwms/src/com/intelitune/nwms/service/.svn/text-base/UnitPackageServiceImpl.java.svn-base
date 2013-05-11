package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.UnitPackageHome;
import com.intelitune.nwms.model.UnitPackage;
/**
 * 
 * @author Louis
 *
 */
public class UnitPackageServiceImpl implements UnitPackageService {
	
	private final static UnitPackageServiceImpl instance =new UnitPackageServiceImpl();
	
	private UnitPackageServiceImpl(){
		
	}
	
	public static final UnitPackageServiceImpl getInstance() {
		return instance;
	}
	public UnitPackageHome uph = UnitPackageHome.getInstance();
	/**
	 * 
	 * @return List<UnitPackage>
	 * 查询所有的UnitPackage
	 */
	public List<UnitPackage> findAll() {
		return uph.findAll();
	}
	/**
	 * 
	 * @param String id
	 * @return UnitPackage
	 * 根据传入的UnitPackage的Id查询出相应的UnitPackage
	 */
	public UnitPackage getUnitPackage(String id) {
		return uph.getUnitPackage(id);
	}
	/**
	 *	@author     wzz
	 *	@lastModify Jan 15, 2009  10:10:46 AM
	 * @param alias
	 * @return
	 */
	public UnitPackage findByAlias(String alias)
	{
		List<UnitPackage> li= uph.findByHql("from UnitPackage u where u.alias="+alias);
		return li.get(0);
	}
	/**
	 * 
	 * @param UnitPackage unitPackage
	 * @return void
	 * 保存UnitPackage
	 */
	public void saveUnitPackage(UnitPackage unitPackage) {
		uph.saveUnitPackage(unitPackage);
	}
	/**
	 * 
	 * @param UnitPackage unitPackage
	 * @return void
	 * 更新UnitPackage
	 */
	public void updateUnitPackage(UnitPackage unitPackage) {
		uph.updateUnitPackage(unitPackage);
	}
	/**
	 * 
	 * @param String id
	 * @return List<UnitPackage>
	 * 根据传入的Material的Id查询出相应的UnitPackage
	 */
	public List<UnitPackage> findByMateialId(String id) {
		String hql = " from UnitPackage i where i.material.id='"+ id + "' order by i.indexId";
		return uph.findByHql(hql);
	}
	
	/**
	 * @param String alias
	 * @return List<UnitPackage>
	 */
	public List<UnitPackage> findByMaterialAlias(String alias) {
		String hql = "from UnitPackage i where i.material.alias='"+ alias + "' order by i.indexId";
		return uph.findByHql(hql);
	}
	public List<UnitPackage> findByMateriaCode(String code)
	{
		String hql = "from UnitPackage i where i.material.code='"+ code + "' order by i.indexId";
		return uph.findByHql(hql);
	}

}
