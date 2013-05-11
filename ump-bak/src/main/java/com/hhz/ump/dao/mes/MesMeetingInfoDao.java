package com.hhz.ump.dao.mes;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.mes.MesMeetingInfo;

import java.math.BigDecimal;
import java.util.Map;

@Repository
public class MesMeetingInfoDao extends HibernateDao<MesMeetingInfo, String> {


    /**
     * 生成 年度 纪要 序号
     */
    public long generateSerialNo(final String sql, final Map<String, Object> values) {
        try {
            BigDecimal count = (BigDecimal) createSQLQuery(sql, values).uniqueResult();
            return count.longValue();
        } catch (Exception e) {
            throw new RuntimeException("hql can't be auto count, sql is:" + sql, e);
        }
    }
}

