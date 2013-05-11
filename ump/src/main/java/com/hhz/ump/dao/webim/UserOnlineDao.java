package com.hhz.ump.dao.webim;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.webim.UserOnline;

@Repository
public class UserOnlineDao extends HibernateDao<UserOnline, String> {
}

