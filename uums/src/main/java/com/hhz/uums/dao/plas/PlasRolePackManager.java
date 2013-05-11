package com.hhz.uums.dao.plas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasRolePack;
import com.hhz.uums.entity.plas.PlasRolePackPosRel;
import com.hhz.uums.entity.plas.PlasRolePackRel;

@Service
@Transactional
public class PlasRolePackManager extends BaseService<PlasRolePack, String> {
	@Autowired
	private PlasRolePackDao plasRolePackDao;
	@Autowired
	private PlasRolePackRelDao plasRolePackRelDao;
	@Autowired
	private PlasRolePackPosRelDao plasRolePackPosRelDao;
	
	public void savePlasRolePack(PlasRolePack plasRolePack) {
		PowerUtils.setEmptyStr2Null(plasRolePack);
		plasRolePackDao.save(plasRolePack);
	}

	public void deletePlasRolePack(String id) {
		PlasRolePack rolePack = getEntity(id);
		if(rolePack.getPlasRolePackRels() != null && rolePack.getPlasRolePackRels().size()>0){
			for (PlasRolePackRel plasRolePackRel : rolePack.getPlasRolePackRels()) {
				plasRolePackRelDao.delete(plasRolePackRel);
			}
		}
		if(rolePack.getPlasRolePackPosRels() != null && rolePack.getPlasRolePackPosRels().size()>0){
			for (PlasRolePackPosRel plasRolePackPosRel: rolePack.getPlasRolePackPosRels()) {
				plasRolePackPosRelDao.delete(plasRolePackPosRel);
			}
		}
		plasRolePackDao.delete(id);
	}
	
	public List<PlasRolePack> getPlasRolePack() {
		String hql = " from PlasRolePack t where t.enableFlg = ? order by t.plasRolePackId desc";
		return getDao().createQuery(hql,"1").list();
	}
	
	/**
	 * 启用/停用角色包
	 * @param id
	 * @param status
	 */
	public void enableRolePack(String id,String status){
		PlasRolePack rolePack = getEntity(id);
		if(rolePack!=null){
			rolePack.setEnableFlg(status);
			savePlasRolePack(rolePack);
		}
	}
	
	@Override
	public HibernateDao<PlasRolePack, String> getDao() {
		return plasRolePackDao;
	}
	
}

