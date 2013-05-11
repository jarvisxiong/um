package com.hhz.ump.dao.assm;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.assm.AssmAccount;

@Repository
public class AssmAccountDao extends HibernateDao<AssmAccount, String> {
}

