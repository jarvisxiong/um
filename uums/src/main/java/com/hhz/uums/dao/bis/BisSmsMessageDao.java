package com.hhz.uums.dao.bis;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.uums.entity.bis.BisSmsMessage;

@Repository
public class BisSmsMessageDao extends HibernateDao<BisSmsMessage, String> {
}

