package com.hhz.ump.dao.app;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.app.SmsMessage;

@Repository
public class SmsMessageDao extends HibernateDao<SmsMessage, String> {
}

