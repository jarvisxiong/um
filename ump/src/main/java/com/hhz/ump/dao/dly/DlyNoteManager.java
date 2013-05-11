package com.hhz.ump.dao.dly;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.hhz.core.service.BaseService;
import com.hhz.core.utils.PowerUtils;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.entity.dly.DlyNote;
import com.hhz.uums.entity.ws.WsPlasAcct;

@Service
@Transactional
public class DlyNoteManager extends BaseService<DlyNote, String> {
	@Autowired
	private DlyNoteDao dlyNoteDao;

	public void saveDlyNote(DlyNote dlyNote) {
		PowerUtils.setEmptyStr2Null(dlyNote);
		dlyNoteDao.save(dlyNote);
	}

	public void deleteDlyNote(String id) {
		dlyNoteDao.delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public HibernateDao<DlyNote, String> getDao() {
		return dlyNoteDao;
	}

	public void initDlyNotes(String uiid) {
		Page<DlyNote> page = new Page<DlyNote>(16);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter filter = new PropertyFilter("EQS_creator", uiid);
		filters.add(filter);
		page.setOrderBy("createdDate");
		page.setOrder(Page.DESC);
		page.setPageNo(1);
		List<DlyNote> list = this.findPage(page, filters).getResult();
		DlyNote dlyNote;
		for (int i = 0; i < 16; i++) {
			if (i < list.size()) {
				dlyNote = list.get(i);
			} else {
				dlyNote = new DlyNote();
				dlyNote.setCreator(uiid);
			}
			dlyNote.setSeq(new Long(i));
			this.saveDlyNote(dlyNote);
		}
	}

	public void initAllNotes() {
		for (WsPlasAcct acct : PlasCache.getAcctList()) {
			String uiid = acct.getUiid();
			this.initDlyNotes(uiid);
		}
	}
}
