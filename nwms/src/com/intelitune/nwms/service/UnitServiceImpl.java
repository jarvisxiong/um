package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.UnitHome;
import com.intelitune.nwms.model.Unit;
/**
 * 
 * @author Louis
 *
 */
public class UnitServiceImpl implements UnitService {
	
	private final static UnitServiceImpl instance =new UnitServiceImpl();
	
	private UnitServiceImpl(){
		
	}
	
	public static final UnitServiceImpl getInstance() {
		return instance;
	}
	public UnitHome uH = UnitHome.getInstance();
	/**
	 * 
	 * @return List<Unit>
	 * 查询所有的Unit
	 */
	public List<Unit> findAll() {
		return uH.findAll();
	}
	/**
	 * 
	 * @param String id
	 * @return Unit
	 * 根据Unit的Id来返回Unit
	 */
	public Unit getUnit(String id) {
		return uH.getUnit(id);
	}
	/**
	 * 
	 * @param unit
	 * @return void
	 * 保存Unit
	 */
	public void saveUnit(Unit unit) {
		uH.saveUnit(unit);
	}
	/**
	 * 
	 * @param unit
	 * @return void
	 * 更新Unit
	 */
	public void updateUnit(Unit unit) {
		uH.updateUnit(unit);
	}
	
	public List<Unit> findUnitByCode(String code) {
		String hql = " from Unit i where i.code='" + code +"'";
		return uH.findByHql(hql);
	}

}
