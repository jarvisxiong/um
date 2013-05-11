package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.MaterialStateHome;
import com.intelitune.nwms.model.MaterialState;

/**
 * 
 * @author Louis
 *
 */
public class MaterialStateServiceImpl implements MaterialStateService {
	private final static MaterialStateServiceImpl instance =new MaterialStateServiceImpl();
	
	private MaterialStateServiceImpl(){
		
	}
	
	public static final MaterialStateServiceImpl getInstance() {
		return instance;
	}
	public MaterialStateHome msh = MaterialStateHome.getInstance();
	/**
	 * @param String id
	 * @return MaterialState 
	 * 根据materialState的id去查询MaterialState
	 */
	public MaterialState findMaterialState(String id){
		return msh.findMaterialState(id);
	}
	/**
	 * 
	 * @return List<MaterialState>
	 * 查询所有的materialState
	 */
	public List<MaterialState> findAll(){
		return msh.findAll();
	}
	/**
	 * 
	 * @param MaterialState materialState
	 * @return void
	 * 保存MaterialState
	 */
	public void saveMaterialState(MaterialState materialState) {
		msh.persist(materialState);
	}
	/**
	 * 
	 * @param MaterialState materialState
	 * @return void
	 * 更新MaterialState
	 */
	public void updateMaterialState(MaterialState materialState) {
		msh.updateMaterialState(materialState);
	}
	/**
	 * 
	 * @param String code
	 * @return MaterialState 
	 * 根据code来查询MaterialState
	 */
	public MaterialState getMaterialState(String code) {
		return msh.getMaterialState(code);
	}


}
