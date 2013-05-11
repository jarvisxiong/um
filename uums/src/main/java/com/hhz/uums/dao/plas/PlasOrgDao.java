package com.hhz.uums.dao.plas;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.uums.entity.plas.PlasDimeOrgRel;
import com.hhz.uums.entity.plas.PlasOrg;
import com.hhz.uums.utils.DictContants;

@Repository
public class PlasOrgDao extends HibernateDao<PlasOrg, String> {
	/**
	 * 
	 * Description:冒泡查询物理上级机构(包括其自身)
	 */
	public List<PlasOrg> getBubbleOrgsPhysical(String plasOrgId) {
		return getBubbleOrgsByOrgId(plasOrgId, DictContants.TREE_DIME_PHYSICAL);
	}
	public List<String> getBubbleOrgIdsPhysical(String plasOrgId) {
		List<String> result = new ArrayList<String>();
		List<PlasOrg> orgList =  getBubbleOrgsByOrgId(plasOrgId, DictContants.TREE_DIME_PHYSICAL);
		PlasOrg org = null;
		for(int i =0 ; i<orgList.size();i++){
			org = orgList.get(i);
			result.add(org.getPlasOrgId());
		}
		return result;
	}
	
	public List<PlasOrg> getBubbleOrgsLogical(String plasOrgId) {
		return getBubbleOrgsByOrgId(plasOrgId, DictContants.TREE_DIME_LOGICAL);
	}
	
	/**
	 * 取祖先机构列表，逐级向上
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<PlasOrg> getBubbleOrgsByOrgId(String orgId,String treeDimeCd) {
		List<PlasOrg> orgList = new ArrayList<PlasOrg>();
		if (StringUtils.isNotBlank(orgId)&&!DictContants.ORG_ROOT_CD.equals(orgId)) {
			PlasOrg parentOrg = this.get(orgId);
		
			if (null != parentOrg ) {
				orgList.add(parentOrg);
				for (PlasDimeOrgRel appRel : parentOrg.getPlasDimeOrgRels()) {
					if(treeDimeCd.equals(appRel.getPlasOrgDime().getDimeCd())){

						orgList.addAll(getBubbleOrgsByOrgId(appRel.getParentId(),treeDimeCd));
					}
				}
			}
		}
		return orgList;
	} 
	/**
	 * 获取最近上一级父亲
	 * @return null
	 */
			
	@Transactional(propagation = Propagation.SUPPORTS)
	public PlasOrg getParentOrg(PlasOrg org,String dimeCd){
		for(PlasDimeOrgRel rel : org.getPlasDimeOrgRels()){
			if(dimeCd.equals(rel.getPlasOrgDime().getDimeCd()))
				return rel.getPlasOrg();
		}
		return null;
	}
}

