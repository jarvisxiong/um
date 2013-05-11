package com.hhz.ump.dao.dly;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.ump.entity.dly.DlyNote;

@Repository
public class DlyNoteDao extends HibernateDao<DlyNote, String> {
}

