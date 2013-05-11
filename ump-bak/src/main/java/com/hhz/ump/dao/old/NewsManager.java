package com.hhz.ump.dao.old;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.old.News;

@Service
@Transactional
public class NewsManager extends BaseService<News, String> {
	@Autowired
	private NewsDao newsDao;

	
	public void saveNews(News news) {
		PowerUtils.setEmptyStr2Null(news);
		newsDao.save(news);
	}

	public void deleteNews(String id) {
		newsDao.delete(id);
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public HibernateDao<News, String> getDao() {
		return newsDao;
	}
	
}

