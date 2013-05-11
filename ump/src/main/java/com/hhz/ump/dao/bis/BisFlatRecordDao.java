package com.hhz.ump.dao.bis;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.bis.BisFlatRecord;

@Repository
public class BisFlatRecordDao extends HibernateDao<BisFlatRecord, String> {
}

