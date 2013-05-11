package com.hhz.ump.dao.bis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.entity.bis.BisContAdditional;

@Service
@Transactional
public class BisContAdditionalManager extends BaseService<BisContAdditional, String> {
	@Autowired
	private BisContAdditionalDao bisContAdditionalDao;
	
	@Autowired
	private AppAttachFileManager appAttachFileManager;

	public void saveBisContAdditional(BisContAdditional bisContAdditional) {
		PowerUtils.setEmptyStr2Null(bisContAdditional);
		bisContAdditionalDao.save(bisContAdditional);
	}

	public void deleteBisContAdditional(String id) {
		bisContAdditionalDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisContAdditional, String> getDao() {
		return bisContAdditionalDao;
	}
	
	public void save(BisContAdditional bisContAdditional) {
		
		String tmpId = bisContAdditional.getEntityTmpId();
		bisContAdditional.setEntityTmpId(null);
		saveBisContAdditional(bisContAdditional);
		
		if (StringUtils.isNotEmpty(tmpId)) {
			appAttachFileManager.updateTmpFile(tmpId, BisContAdditional.class.getSimpleName(), bisContAdditional.getBisContAdditionalId());
		}
	}

	/**
	 * 删除某合同下不在主键数组ids之外的补充合同
	 */
	public void deleteLeaveBatch(String bisContId, Object[] excludeIds) {
		
		StringBuffer hql = new StringBuffer("from BisContAdditional where bisCont.bisContId=:bisContId ");
		if(excludeIds != null && excludeIds.length > 0) {
			hql.append("and bisContAdditionalId not in (:ids) ");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisContId", bisContId);
		param.put("ids", excludeIds);
		List<BisContAdditional> list = bisContAdditionalDao.find(hql.toString(), param);
		for(BisContAdditional bisContAdditional : list) {
			appAttachFileManager.deleteByBizEntityId(bisContAdditional.getBisContAdditionalId(), false);
			delete(bisContAdditional);
		}
	}
}

