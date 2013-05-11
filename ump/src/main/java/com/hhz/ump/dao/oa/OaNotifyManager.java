package com.hhz.ump.dao.oa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.lob.ClobImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.entity.oa.OaNotify;
import com.hhz.ump.util.Util;

@Service
@Transactional
public class OaNotifyManager extends BaseService<OaNotify, String> {
    @Autowired
    private OaNotifyDao oaNotifyDao;

    public void saveOaNotify(OaNotify oaNotify) {
	PowerUtils.setEmptyStr2Null(oaNotify);
	oaNotifyDao.save(oaNotify);
    }

    public void deleteOaNotify(String id) {
	oaNotifyDao.delete(id);
    }

    /**
     * 批量删除公告记录
     * 
     * @param ids
     */
    public void deleteBatchOanotify(String[] ids) {
	for (String id : ids) {
	    if (StringUtils.isNotBlank(id)) {
		deleteOaNotify(id);
	    }
	}
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public HibernateDao<OaNotify, String> getDao() {
	return oaNotifyDao;
    }

    /**
     * 
     * @param orgCd
     * @param uiid
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public boolean setAllNotifiesAsReaded(String orgCd, String uiid) throws SQLException, IOException {
	// 找出所有的未读公告
	StringBuilder hql1 = new StringBuilder("from OaNotify where enabledFlg = '1'");
	Map<String, Object> params = new HashMap<String, Object>();
	
	hql1.append(" and (toDeptCds = 'all' or toDeptCds like :orgCd)");
	params.put("orgCd", "%," + orgCd + ",%");
	hql1.append(" and (readers is null or readers not like :uiid)");
	params.put("uiid", new ClobImpl("%" + uiid + ",%"));
	List<OaNotify> oaNotifyList = find(hql1.toString(), params);

	
	String hql2 = "update OaNotify set readers=:readers where oaNotifyId=:oaNotifyId";
	Map<String, Object> values = null;
	for (OaNotify oaNotify : oaNotifyList) {
	    StringBuilder readers = new StringBuilder();
	    String curReader = uiid + ",";

	    // 如果公告有别的阅读者，则把当前用户加在阅读者列表的最后，否则把当前用户插入readers字段
	    if (oaNotify.getReaders() != null) {
		String oriReaders = Util.clob2String(oaNotify.getReaders());
		readers.append(oriReaders).append(curReader);
	    } else {
		readers.append(curReader);
	    }
	    
	    values = new HashMap<String, Object>();
	    values.put("readers", new ClobImpl(readers.toString()));
	    values.put("oaNotifyId", oaNotify.getOaNotifyId());
	    
	    this.getDao().batchExecute(hql2, values);
	}
	
	return true;
    }
}
