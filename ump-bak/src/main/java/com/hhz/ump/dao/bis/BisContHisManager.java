package com.hhz.ump.dao.bis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.bis.BisContHis;

@Service
@Transactional
public class BisContHisManager extends BaseService<BisContHis, String> {
	@Autowired
	private BisContHisDao bisContHisDao;

	public void saveBisContHis(BisContHis bisContHis) {
		PowerUtils.setEmptyStr2Null(bisContHis);
		bisContHisDao.save(bisContHis);
	}

	public void deleteBisContHis(String id) {
		bisContHisDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisContHis, String> getDao() {
		return bisContHisDao;
	}
	
	/**
	 * 使历史记录有效
	 */
	public void activeContHis(String bisContId) {
		String hql = "update BisContHis set activeBl=1 where bisContId=? and activeBl=0";
		getDao().batchExecute(hql, bisContId);
	}
	
	public List<BisContHis> getHisList(String bisContId) {
		
		String hql = "from BisContHis where bisContId=? and statusCd='3' order by updatedDate desc";
		return getDao().find(hql, bisContId);
		
	}
}

