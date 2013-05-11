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
import com.hhz.ump.entity.bis.BisContCredit;

@Service
@Transactional
public class BisContCreditManager extends BaseService<BisContCredit, String> {
	@Autowired
	private BisContCreditDao bisContCreditDao;

	@Autowired
	private AppAttachFileManager appAttachFileManager;

	public void saveBisContCredit(BisContCredit bisContCredit) {
		PowerUtils.setEmptyStr2Null(bisContCredit);
		bisContCreditDao.save(bisContCredit);
	}

	public void deleteBisContCredit(String id) {
		bisContCreditDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisContCredit, String> getDao() {
		return bisContCreditDao;
	}
	
	public void save(BisContCredit bisContCredit) {
		
		String tmpId = bisContCredit.getEntityTmpId();
		bisContCredit.setEntityTmpId(null);
		saveBisContCredit(bisContCredit);
		
		if (StringUtils.isNotEmpty(tmpId)) {
			appAttachFileManager.updateTmpFile(tmpId, BisContCredit.class.getSimpleName(), bisContCredit.getBisContCreditId());
		}
	}

	/**
	 * 删除某合同下不在主键数组ids之外的资信资料
	 */
	public void deleteLeaveBatch(String bisContId, Object[] excludeIds) {
		
		StringBuffer hql = new StringBuffer("from BisContCredit where bisCont.bisContId=:bisContId ");
		if(excludeIds != null && excludeIds.length > 0) {
			hql.append("and bisContCreditId not in (:ids) ");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("bisContId", bisContId);
		param.put("ids", excludeIds);
		List<BisContCredit> list = bisContCreditDao.find(hql.toString(), param);
		for(BisContCredit bisContCredit : list) {
			appAttachFileManager.deleteByBizEntityId(bisContCredit.getBisContCreditId(), false);
			delete(bisContCredit);
		}
	}
	
}

