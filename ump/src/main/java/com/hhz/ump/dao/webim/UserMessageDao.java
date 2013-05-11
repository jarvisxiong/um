package com.hhz.ump.dao.webim;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.webim.UserMessage;

@Repository
public class UserMessageDao extends HibernateDao<UserMessage, String> {
}

