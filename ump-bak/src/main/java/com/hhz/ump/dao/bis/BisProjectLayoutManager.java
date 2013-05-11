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
import com.hhz.ump.entity.bis.BisProjectLayout;

@Service
@Transactional
public class BisProjectLayoutManager extends BaseService<BisProjectLayout, String> {
	@Autowired
	private BisProjectLayoutDao bisProjectLayoutDao;

	public void saveBisProjectLayout(BisProjectLayout bisProjectLayout) {
		PowerUtils.setEmptyStr2Null(bisProjectLayout);
		bisProjectLayoutDao.save(bisProjectLayout);
	}

	public void deleteBisProjectLayout(String id) {
		bisProjectLayoutDao.delete(id);
	}
	
	@Override
	public HibernateDao<BisProjectLayout, String> getDao() {
		return bisProjectLayoutDao;
	}
	
	@Transactional(readOnly = true)
	public Map<String, String> getLatestLayout(String bisProjectId) {
		Map<String, String> result = new HashMap<String, String>();

		String hql = "from BisProjectLayout where bisProject.bisProjectId=? order by imgDate desc, updatedDate desc";
		List<BisProjectLayout> list = getDao().find(hql, bisProjectId);
		for(BisProjectLayout layout: list) {
			if(StringUtils.equals("1", layout.getImgType())) {
				if(result.get("layoutId") == null) {
					result.put("layoutId", layout.getBisProjectLayoutId());
				}
			} else if(StringUtils.equals("2", layout.getImgType())) {
				if(result.get("brandLoadId") == null) {
					result.put("brandLoadId", layout.getBisProjectLayoutId());
				}
			} else {
				if(result.get("priceApproveId") == null) {
					result.put("priceApproveId", layout.getBisProjectLayoutId());
				}
			}
		}
		
		return result;
	}

	
}

