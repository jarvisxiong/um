package com.hhz.ump.dao.biz;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.biz.BizBankAccount;

@Repository
public class BizBankAccountDao extends HibernateDao<BizBankAccount, String> {
}

