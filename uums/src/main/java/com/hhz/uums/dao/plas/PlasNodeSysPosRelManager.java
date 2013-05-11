package com.hhz.uums.dao.plas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.uums.entity.plas.PlasNodeSysPosRel;
import com.hhz.uums.vo.ws.WsPlasNodeSysPosRel;

@Service
@Transactional
public class PlasNodeSysPosRelManager extends BaseService<PlasNodeSysPosRel, String> {
	@Autowired
	private PlasNodeSysPosRelDao plasNodeSysPosRelDao;

	public void savePlasNodeSysPosRel(PlasNodeSysPosRel plasNodeSysPosRel) {
		PowerUtils.setEmptyStr2Null(plasNodeSysPosRel);
		plasNodeSysPosRelDao.save(plasNodeSysPosRel);
	}

	public void deletePlasNodeSysPosRel(String id) {
		plasNodeSysPosRelDao.delete(id);
	}
	
	@Override
	public HibernateDao<PlasNodeSysPosRel, String> getDao() {
		return plasNodeSysPosRelDao;
	}
	
	/**
	 * 保存配置角色与系统职位关系
	 * @param roleId
	 * @param addRoleIds
	 * @param delRoleIds
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public void saveBatchNodeSysPosRel(String nodeCd,String addRoleIds, String delRoleIds) {
		
		String[] addArray = addRoleIds.split(",");
		String[] delArray = delRoleIds.split(",");

		
		// 授权系统职位与角色的对应关系
		if (StringUtils.isNotBlank(addRoleIds)) {
			PlasNodeSysPosRel tmpRel = null;
			
			for (int i = 0; i < addArray.length; i++) {
				String sysPositionId=addArray[i];
				
				if(!isExists(sysPositionId, nodeCd)){
					tmpRel = new PlasNodeSysPosRel();
					tmpRel.setPlasSysPositionId(sysPositionId);
					tmpRel.setNodeCd(nodeCd);
					String certOrgCd=getCenterOrg(sysPositionId);
					tmpRel.setPlasOrgId(certOrgCd);//中心机构ID
					savePlasNodeSysPosRel(tmpRel);
				}
			}
			
		}
		// 收回系统职位和角色的对应关系
		if (StringUtils.isNotBlank(delRoleIds)) {
			for (int i = 0; i < delArray.length; i++) {
				deleteNodeSysPosRelById(delArray[i], nodeCd);
			}
			
		}
	}
	private String getCenterOrg(String plasSysPositionId){
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, plasSysPositionId);
		Object obj = executeFunction("{?= call fn_get_position_center(?)}", map, String.class);
		return obj.toString();
	}
	/**
	 * 删除角色与职位关系
	 * @param positionId
	 * @param roleId
	 * @return
	 */
	public boolean deleteNodeSysPosRelById(String positionId, String nodeCd){
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("positionId", positionId);
		values.put("nodeCd", nodeCd);
		String hql = "delete from PlasNodeSysPosRel t where t.plasSysPositionId = :positionId and t.nodeCd = :nodeCd ";
		getDao().batchExecute(hql, values);
		return true;
	}
	public List<String>  loadBySysPosId(String sysPosId){
		StringBuffer hql=new StringBuffer("select o.orgName from PlasOrg o , PlasNodeSysPosRel r where r.plasOrgId=o.plasOrgId and r.plasSysPositionId=?");
		return (List)find(hql.toString(), sysPosId);
	}
	/**
	 * 校验是否存在
	 * @param plasSysPosId
	 * @param plasRoleId
	 * @return
	 */
	private boolean isExists(String plasSysPosId, String nodeCd){

		if(StringUtils.isBlank(plasSysPosId) || StringUtils.isBlank(nodeCd))
			return true;
		
		String hql = " from PlasNodeSysPosRel t where t.plasSysPositionId = ? and t.nodeCd = ? ";
		List list = this.getDao().createQuery(hql, plasSysPosId, nodeCd).list();
		if(list == null || list.size() == 0 )
			return false;
		else
			return true;
	}
	
	public List<WsPlasNodeSysPosRel> getWsAll(){
		List<PlasNodeSysPosRel> sysPosRels=getAll();
		List<WsPlasNodeSysPosRel> sysPosRels2=new ArrayList<WsPlasNodeSysPosRel>();
		for (PlasNodeSysPosRel plasNodeSysPosRel : sysPosRels) {
			WsPlasNodeSysPosRel rel=new WsPlasNodeSysPosRel();
			rel.setActive(plasNodeSysPosRel.getActive());
			rel.setNodeCd(plasNodeSysPosRel.getNodeCd());
			rel.setPlasNodeSysPosRelId(plasNodeSysPosRel.getPlasNodeSysPosRelId());
			rel.setPlasOrgId(plasNodeSysPosRel.getPlasOrgId());
			rel.setPlasSysPositionId(plasNodeSysPosRel.getPlasSysPositionId());
			sysPosRels2.add(rel);
		}
		return sysPosRels2;
	}
}

