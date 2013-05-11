package com.hhz.uums.dao.plas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasRolePack;
import com.hhz.uums.entity.plas.PlasRolePackPosRel;
import com.hhz.uums.entity.plas.PlasSysPosition;
import com.hhz.uums.utils.OperConst;

@Service
@Transactional
public class PlasRolePackPosRelManager extends BaseService<PlasRolePackPosRel, String> {
	@Autowired
	private PlasRolePackPosRelDao plasRolePackPosRelDao;
	@Autowired
	private PlasSysPositionManager plasSysPositionManager;
	@Autowired
	private PlasRolePackManager plasRolePackManager;
	@Autowired
	private PlasOperateLogManager plasOperateLogManager;
	
	public void savePlasRolePackPosRel(PlasRolePackPosRel plasRolePackPosRel) {
		PowerUtils.setEmptyStr2Null(plasRolePackPosRel);
		plasRolePackPosRelDao.save(plasRolePackPosRel);
	}

	/**
	 * 保存打包角色与系统职位关系
	 * @param plasSysPositionId 系统职位id
	 * @param addPackIds 增加的角色包id
	 * @param delPackIds 删除的角色包id
	 * @return
	 */
	
	public void saveBatch(String plasSysPositionId, String addPackIds, String delPackIds){
		String[] addArray = addPackIds.split(",");
		String[] delArray = delPackIds.split(",");

		PlasSysPosition plasSysPosition = plasSysPositionManager.getEntity(plasSysPositionId);
		
		//配置角色包与系统职位的对应关系
		PlasRolePackPosRel rolePackPosRel = null;
		if(StringUtils.isNotBlank(addPackIds)){
			StringBuffer sb = new StringBuffer();
			PlasRolePack plasRolePack = null;
			for(int i = 0;i < addArray.length; i++){
				rolePackPosRel = new PlasRolePackPosRel();
				plasRolePack = plasRolePackManager.getEntity(addArray[i]);
				rolePackPosRel.setPlasRolePack(plasRolePack);
				rolePackPosRel.setPlasSysPosition(plasSysPosition);
				savePlasRolePackPosRel(rolePackPosRel); //保存新选择的角色包
				sb.append(plasRolePack.getPackName()).append(",");
			}
			//操作日志
			plasOperateLogManager.savePlasOperateLog(SpringSecurityUtils.getCurrentUiid(), SpringSecurityUtils.getCurUserName(),
					OperConst.SYSPOS, OperConst.ADD,
					new StringBuffer("[").append(plasSysPosition.getSysPosCd()).append(",").append(plasSysPosition.getSysPosName()).append(
					"]授权系统职位的角色包如下：").append(sb.toString().substring(0,sb.toString().length()-1)).toString());
		}
		//删除角色包和系统职位的对应关系
		if(StringUtils.isNotBlank(delPackIds)){
			StringBuffer sb = new StringBuffer();
			PlasRolePack plasRolePack =null;
			for(int i = 0;i < delArray.length; i++){
				plasRolePack = plasRolePackManager.getEntity(delArray[i]);
				deletePlasRolePackPosRel(plasSysPositionId,delArray[i]); //删除去除的角色包
				sb.append(plasRolePack.getPackName()).append(",");
			}
			//操作日志
			plasOperateLogManager.savePlasOperateLog(SpringSecurityUtils.getCurrentUiid(), SpringSecurityUtils.getCurUserName(),
					OperConst.SYSPOS, OperConst.DEL,
					new StringBuffer("[").append(plasSysPosition.getSysPosCd()).append(",").append(plasSysPosition.getSysPosName()).append(
					"]收回系统职位的角色包如下：").append(sb.toString().substring(0,sb.toString().length()-1)).toString());
		}
	}

	public List<PlasRolePackPosRel> getRolePackPosRelBySysPositionId(String plasSysPositionId) {
		String hql = " from PlasRolePackPosRel t where t.plasSysPosition.plasSysPositionId = ? ";
		return getDao().createQuery(hql,plasSysPositionId).list();
	}
	
	/**
	 * 删除角色包与系统职位关系
	 * @param plasSysPositionId
	 * @param packId
	 * @return
	 */
	public boolean deletePlasRolePackPosRel(String plasSysPositionId ,String packId) {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("plasSysPositionId", plasSysPositionId);
		values.put("packId", packId);
		String hql = "delete from PlasRolePackPosRel t where t.plasSysPosition.plasSysPositionId = :plasSysPositionId and t.plasRolePack.plasRolePackId = :packId ";
		getDao().batchExecute(hql, values);
		return true;
	}
	
	@Override
	public HibernateDao<PlasRolePackPosRel, String> getDao() {
		return plasRolePackPosRelDao;
	}
	
}

