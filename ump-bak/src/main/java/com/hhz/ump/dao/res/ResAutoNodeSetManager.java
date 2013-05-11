package com.hhz.ump.dao.res;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.res.ResAuthType;
import com.hhz.ump.entity.res.ResAutoNodeSet;

@Service
@Transactional
public class ResAutoNodeSetManager extends BaseService<ResAutoNodeSet, String> {
	@Autowired
	private ResAutoNodeSetDao resAutoNodeSetDao;

	public void saveResAutoNodeSet(ResAutoNodeSet resAutoNodeSet) {
		PowerUtils.setEmptyStr2Null(resAutoNodeSet);
		resAutoNodeSetDao.save(resAutoNodeSet);
	}

	public void deleteResAutoNodeSet(String id) {
		resAutoNodeSetDao.delete(id);
	}

	public void saveResAutoNodeSets(List<ResAutoNodeSet> resAutoNodeSets, ResAuthType resAuthType) {
		for (ResAutoNodeSet resAutoNodeSet : resAutoNodeSets) {
			if (resAutoNodeSet.getResAuthType() == null) {
				resAutoNodeSet.setResAuthType(resAuthType);
				resAutoNodeSet.setActive(true);
			}
			this.saveResAutoNodeSet(resAutoNodeSet);
		}
	}

	public void deleteResAutoNodeSets(List<ResAutoNodeSet> resAutoNodeSets) {
		for (ResAutoNodeSet resAutoNodeSet : resAutoNodeSets) {
			resAutoNodeSet = this.getEntity(resAutoNodeSet.getResAutoNodeSetId());
			this.delete(resAutoNodeSet);
		}
	}

	public ResAutoNodeSet getResAutoNodeSetBy(String resAuthTypeId, String conditionCd) {
		ResAutoNodeSet autoNodeSet = null;
		Criterion c1 = Restrictions.eq("resAuthType.resAuthTypeId", resAuthTypeId);
		Criterion c2 = Restrictions.eq("conditionCd", conditionCd);
		List<ResAutoNodeSet> nodeSets = findBy(c1, c2);
		if (nodeSets.size() == 1) {
			autoNodeSet = nodeSets.get(0);
		}
		return autoNodeSet;
	}

	@Override
	public HibernateDao<ResAutoNodeSet, String> getDao() {
		return resAutoNodeSetDao;
	}

	public void saveOrDeleteResAutoNodeSets(List<ResAutoNodeSet> insertRecords, List<ResAutoNodeSet> updateRecords,
			List<ResAutoNodeSet> deleteRecords, ResAuthType resAuthType) {
		this.saveResAutoNodeSets(insertRecords, resAuthType);
		this.saveResAutoNodeSets(updateRecords, resAuthType);
		this.deleteResAutoNodeSets(deleteRecords);
	}
}
