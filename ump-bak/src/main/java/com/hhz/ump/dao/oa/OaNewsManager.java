package com.hhz.ump.dao.oa;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.lob.ClobImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.entity.oa.OaNews;
import com.hhz.ump.entity.oa.OaNewsComment;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.Util;

@Service
@Transactional
public class OaNewsManager extends BaseService<OaNews, String> {

	private static final Logger logger = Logger.getLogger(OaNewsManager.class);

	public static final String NEWS_FLG_NEWS = "news";

	public static final String NEWS_FLG_EMPLOYEE = "employee";

	@Autowired
	private OaNewsDao oaNewsDao;

	@Autowired
	private OaNewsCommentManager oaNewsCommentManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	public void saveOaNews(OaNews oaNews) {
		PowerUtils.setEmptyStr2Null(oaNews);
		oaNewsDao.save(oaNews);
	}

	/**
	 * 
	 * @param id
	 */
	public void deleteOaNews(String id) {
		OaNews oaNews = this.getEntity(id);
		List<OaNewsComment> comments = oaNews.getOaNewsComments();
		if (comments.size() > 0) {
			oaNewsCommentManager.delete(comments);
		}
		oaNewsDao.delete(id);
	}

	/**
	 * 批量删除选中的新闻记录
	 * 
	 * @param ids
	 */
	public void deleteBatchOaNews(String[] ids) {
		for (String id : ids) {
			if (StringUtils.isNotBlank(id)) {
				this.deleteOaNews(id);
			}
		}
	}

	/**
	 * 根据条件搜索新闻记录
	 * 
	 * @param orgCd
	 *            机构CD
	 * @param uiid
	 *            用户UIID
	 * @param status
	 *            是否未读
	 * @param typeCd
	 *            类型CD
	 * @param subject
	 *            标题
	 * @param startNewsTime
	 *            开始时间
	 * @param endNewsTime
	 *            结束时间
	 * @param flg
	 *            模块标志位
	 * @param pageNo
	 *            搜索结果第几页
	 * @return
	 * @throws ParseException
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<OaNews> getNewsByConditions(String orgCd, String uiid, String status, String typeCd, String subject, String startNewsTime,
			String endNewsTime, String flg, int pageNo, int pageSize) throws ParseException {

		Page<OaNews> result = new Page<OaNews>(pageSize);
		result.setPageNo(pageNo);

		StringBuilder hql = new StringBuilder("from OaNews where enabledFlg = '1' and (toDeptCds = 'all' or toDeptCds like :orgCd)");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgCd", "%," + orgCd + ",%");
		hql.append(" and newsTime <= :curDate");
		params.put("curDate", new Date());
		if (NEWS_FLG_NEWS.equalsIgnoreCase(flg)) {
			hql.append(" and typeCd != '4'");
		} else if (NEWS_FLG_EMPLOYEE.equalsIgnoreCase(flg)) {
			hql.append(" and typeCd = '4'");
		}

		if ("new".equals(status)) {
			hql.append(" and (readers is null or readers not like :uiid)");
			params.put("uiid", new ClobImpl("%" + uiid + ",%"));
		}

		if (StringUtils.isNotBlank(typeCd)) {
			hql.append(" and typeCd = :typeCd");
			params.put("typeCd", typeCd);
		}

		if (StringUtils.isNotBlank(subject)) {
			hql.append(" and subject like :subject");
			params.put("subject", "%"+subject+"%");
		}

		if (StringUtils.isNotBlank(startNewsTime) || StringUtils.isNotBlank(endNewsTime)) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			if (StringUtils.isNotBlank(startNewsTime)) {
				hql.append(" and newsTime >= :startNewsTime");
				params.put("startNewsTime", df.parse(startNewsTime));
			}

			if (StringUtils.isNotBlank(endNewsTime)) {
				hql.append(" and newsTime <= :endNewsTime");
				params.put("endNewsTime", df.parse(endNewsTime));
			}
		}

		hql.append(" order by newsTime desc, createdDate desc");

		result = this.findPage(result, hql.toString(), params);

		return result;
	}

	/**
	 * 构造在首页显示的新闻列表
	 * 
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Page<OaNews> getFrontPageNews(String orgCd, int pageNo, int pageSize) {
		Page<OaNews> result = new Page<OaNews>(pageSize);
		result.setPageNo(pageNo);

		String hql = "from OaNews where enabledFlg = '1' and typeCd != '4'" + " and (toDeptCds = 'all' or toDeptCds like :orgCd) "
				+ " order by newsTime desc, createdDate desc";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgCd", "%," + orgCd + ",%");
		result = this.findPage(result, hql, params);

		return result;
	}

	/**
	 * 构造新闻类型列表， 如果是新闻模块，则需要把员工天地挪出列表 如果是员工天地模块，则需要把新闻的类型挪出列表
	 * 
	 * @param flg
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Map<String, String> buildNewsTypes(String flg) {
		Map<String, String> types = appDictTypeManager.getDictDataByTypeCd(DictContants.OA_NEWS_TYPE);

		if (NEWS_FLG_NEWS.equalsIgnoreCase(flg)) {
			types.remove("4");
		} else if (NEWS_FLG_EMPLOYEE.equalsIgnoreCase(flg)) {
			types.remove("0");
			types.remove("1");
			types.remove("2");
			types.remove("3");
		}

		return types;
	}

	/**
	 * 构造发布人Map
	 * 
	 * @param oaNewsList
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public Map<String, String> buildCreatorNames(List<OaNews> oaNewsList) {
		Map<String, String> result = new HashMap<String, String>();

		String creator = null;
		for (OaNews oaNews : oaNewsList) {
			creator = oaNews.getCreator();
			if (StringUtils.isNotBlank(creator) && !result.containsKey(creator)) {
				result.put(creator, CodeNameUtil.getUserNameByCd(creator));
			}
		}

		return result;
	}

	/**
	 * 设置指定用户所有未读新闻为已读
	 * 
	 * @param orgCd
	 * @param uiid
	 * @param flg
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public boolean setAllNewsAsReaded(String orgCd, String uiid, String flg) throws SQLException, IOException {
		StringBuilder hql = new StringBuilder("from OaNews where enabledFlg = '1'");
		Map<String, Object> params = new HashMap<String, Object>();

		hql.append(" and (toDeptCds = 'all' or toDeptCds like :orgCd)");
		params.put("orgCd", "%," + orgCd + ",%");
		hql.append(" and (readers is null or readers not like :uiid)");
		params.put("uiid", new ClobImpl("%" + uiid + ",%"));

		if (NEWS_FLG_NEWS.equalsIgnoreCase(flg)) {
			hql.append(" and typeCd != '4'");
		} else if (NEWS_FLG_EMPLOYEE.equalsIgnoreCase(flg)) {
			hql.append(" and typeCd = '4'");
		}

		List<OaNews> oaNewsList = find(hql.toString(), params);

		String updateHql = "update OaNews set readers=:readers where oaNewsId=:oaNewsId";
		Map<String, Object> values = null;
		for (OaNews oaNews : oaNewsList) {
			StringBuilder readers = new StringBuilder();
			String curReader = uiid + ",";

			// 如果新闻有别的阅读者，则把当前用户加在阅读者列表的最后，
			// 如果新闻没有别的阅读者，把当前用户写入readers字段
			if (oaNews.getReaders() != null) {
				String oriReaders = Util.clob2String(oaNews.getReaders());
				readers.append(oriReaders).append(curReader);
			} else {
				readers.append(curReader);
			}

			values = new HashMap<String, Object>();
			values.put("readers", new ClobImpl(readers.toString()));
			values.put("oaNewsId", oaNews.getOaNewsId());

			this.getDao().batchExecute(updateHql, values);
		}

		return true;
	}

	/**
	 * 阅读新闻详情时要把当前用户加入到阅读人列表，同时增加点击次数
	 * 
	 * @param newsId
	 * @param userId
	 * @return
	 */
	public OaNews readNewsDetail(String newsId, String userId) {
		if (StringUtils.isBlank(newsId))
			throw new IllegalArgumentException("新闻ID不能为空.");
		if (StringUtils.isBlank(userId))
			throw new IllegalArgumentException("用户ID不能为空.");

		OaNews news = this.getEntity(newsId);
		String readers = "";
		if (news.getReaders() == null) {
			readers = userId + ",";
		} else {
			try {
				readers = Util.clob2String(news.getReaders());
				if (!readers.contains(userId+",")) {
					readers = readers + userId + ",";
				}
			} catch (Exception e) {
				logger.warn("获取用户阅读人列表失败", e);
				throw new RuntimeException(e);
			}
		}
		// news.setReaders(new ClobImpl(readers));

		long count = news.getClickCount() == null ? 0 : news.getClickCount();
		// news.setClickCount(count + 1);
		// saveOaNews(news);

		String hql = "update OaNews set clickCount=:clickCount, readers=:readers where oaNewsId=:oaNewsId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("clickCount", Long.valueOf(count + 1));
		params.put("readers", new ClobImpl(readers));
		params.put("oaNewsId", newsId);
		this.getDao().batchExecute(hql, params);

		return news;
	}

	@Override
	public HibernateDao<OaNews, String> getDao() {
		return oaNewsDao;
	}

}
