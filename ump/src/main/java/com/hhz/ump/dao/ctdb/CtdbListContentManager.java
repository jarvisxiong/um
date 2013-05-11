package com.hhz.ump.dao.ctdb;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.ctdb.CtdbListContent;

@Service
@Transactional
public class CtdbListContentManager extends BaseService<CtdbListContent, String> {
	@Autowired
	private CtdbListContentDao ctdbListContentDao;
	/**
	 * 暂存
	 */
	public static String NEW_STATUS="0";
	/**
	 * 提交
	 */
	public static String SAVE_STATUS="1";
	/**
	 * 已确认
	 */
	public static String COMMIT_STATUS="2";
	/**
	 * 驳回
	 */
	public static String REJECT_STATUS="3";
	/**
	 * 存在附件
	 */
	public static String ATTACHE_HAS="1";
	/**
	 * 不存在附件
	 */
	public static String ATTACHE_NONE="0";

	public void saveCtdbListContent(CtdbListContent ctdbListContent) {
		PowerUtils.setEmptyStr2Null(ctdbListContent);
		ctdbListContentDao.save(ctdbListContent);
	}

	public void deleteCtdbListContent(String id) {
		ctdbListContentDao.delete(id);
	}
	
	@Override
	public HibernateDao<CtdbListContent, String> getDao() {
		return ctdbListContentDao;
	}
	
	/**
	 * 批量保存清单数据列表
	 */
	public void batchSave(List<CtdbListContent> clcs) {	  		
		batchExecute(clcs);
	}
	
	/**
	 *批量保存 
	 * @param rowsData
	 */
	@Transactional
	public void batchExecute(List<CtdbListContent> rowsData) {
		Session session = getDao().getSession();
		CtdbListContent clc = null;
		for (int i = 0; i < rowsData.size(); i++) {
			clc = rowsData.get(i);
			saveCtdbListContent(clc);
			if (i % 50 == 0) {
				session.flush();
				session.clear();
			}
		}
	}
	
	/**
	 * 更新附件状态
	 * @param listId
	 * @param flg
	 */
	public void attacheFlgModify(String listId,String flg){
		CtdbListContent entity=ctdbListContentDao.get(listId);
		entity.setAttachFlg(flg);
		ctdbListContentDao.save(entity);
		
	}
	
}

