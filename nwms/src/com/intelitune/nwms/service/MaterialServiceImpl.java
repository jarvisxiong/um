package com.intelitune.nwms.service;

import java.util.List;

import com.intelitune.nwms.dao.MaterialHome;
import com.intelitune.nwms.model.Material;
import com.intelitune.nwms.model.MaterialState;
/**
 * 
 * @author Louis
 *
 */
public class MaterialServiceImpl implements MaterialService {
	private final static MaterialServiceImpl instance =new MaterialServiceImpl();
	
	private MaterialServiceImpl(){
		
	}
	
	public static final MaterialServiceImpl getInstance() {
		return instance;
	}
	public MaterialHome mh = MaterialHome.getInstance();

	/**
	 * @param Material material
	 * @return void
	 * 保存料件
	 */
	public void saveMaterial(Material material){
		mh.persist(material);
	}
	/**
	 * @param String hql
	 * @return List<Material>
	 * 使用hql查询
	 */
	public List<Material> findByHql(String hql){
		return mh.findByHql(hql);
	}
	/**
	 * @param String id
	 * @return Material 
	 * 根据material的Id来查询料件
	 */
	public Material findById(String id){
		return mh.findById(id);
	}
	

	/**
	 * @param Material material
	 * @return void
	 * 更新material
	 */
	public void updateMaterial(Material material) {
		mh.merge(material);
	}
	

	/**
	 * 
	 * @param Float value
	 * @param String inttClientDetailWSId
	 * @param String alias
	 * @param String code
	 * @return List<Material>
	 * 根据所传入的四个条件生成相应的Hql语句去查询
	 */
	public List<Material> searchMaterial(Float value,
			String inttClientDetailWSId, String alias, String code) {
		StringBuffer hql = new StringBuffer(" from Material i where 1=1");
		if(value!=null){
			hql.append(" and i.value=" + value);
		}
		if(inttClientDetailWSId!=null){
			hql.append(" and i.inttClientDetailWSId='" + inttClientDetailWSId + "'");
		}
		if(alias!=null){
			hql.append(" and i.alias like '%" + alias +"%'");
		}
		if(code!=null){
			hql.append(" and i.code like '%" + code + "%'");
		}
		return mh.findByHql(hql.toString());
	}
	/**
	 *	@author     wzz
	 *	@lastModify Jan 15, 2009  9:54:51 AM
	 * @param alias
	 * @return Material
	 */
	public Material findByAlias(String alias)
	{
	  List<Material> li=mh.findByHql("from Material m where m.alias='"+alias+"'");
		
		return li.get(0);
	}
	
	/**
	 * @param String code
	 * @return Material
	 * 根据传入的code值选出相应的Material
	 */
	public Material findMaterialByCode(String code,String inttClientDetailWSId){
		String hql = " from Material i where i.code='" + code + "' and i.state.code='" + MaterialState.ZHENGCHANG+"' and i.inttClientDetailWSId='" + inttClientDetailWSId +"'";
		List list = mh.findByHql(hql);
		if(list.size()!=0){
			return (Material) list.get(0);
		}else{
			return null;
		}
	}
	public List<Material> findNormalMaterialByOwner(String inttClientDetailWSId) {
		String hql = " from Material i where i.inttClientDetailWSId='" + inttClientDetailWSId + "' and i.state.code='" + MaterialState.ZHENGCHANG +"'";
		return mh.findByHql(hql);
	}


	
}
