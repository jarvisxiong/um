package com.hhz.ump.web.dly;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.app.AppSeqManager;
import com.hhz.ump.dao.dly.DlyNoteManager;
import com.hhz.ump.entity.dly.DlyNote;

/**
 */
@Namespace("/dly")
public class DlyNoteAction extends CrudActionSupport<DlyNote> {
	private static final long serialVersionUID = -3445152342227169047L;

	private Page<DlyNote> pageNote = new Page<DlyNote>(12);// 每页16条记录

	@Autowired
	private DlyNoteManager dlyNoteManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private AppSeqManager appSeqManager;

	private DlyNote entity;

	private String filter_LIKES_noteContent;
	private String filter_GED_updatedDate;
	private String filter_LED_updatedDate;

	public DlyNoteAction() {
	}

	public DlyNote getModel() {
		return entity;
	}

	public Page<DlyNote> getPageNote() {
		return pageNote;
	}

	@Override
	protected void prepareModel() throws Exception {
		String dlyNoteId = getId();
		if (StringUtils.isNotBlank(dlyNoteId)) {
			entity = dlyNoteManager.getEntity(dlyNoteId);
		} else {
			entity = new DlyNote();
		}
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());

		PropertyFilter newFilter = new PropertyFilter("EQS_creator", SpringSecurityUtils.getCurrentUiid());
		filters.add(newFilter);
		pageNote.setOrderBy("updatedDate");
		pageNote.setOrder(Page.DESC);
		pageNote.setPageSize(32);
		pageNote = dlyNoteManager.findPage(pageNote, filters);
		return SUCCESS;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	public String desk() throws Exception {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter newFilter = new PropertyFilter("EQS_creator", SpringSecurityUtils.getCurrentUiid());
		filters.add(newFilter);
		pageNote.setOrderBy("updatedDate");
		pageNote.setOrder(Page.DESC);
		pageNote = dlyNoteManager.findPage(pageNote, filters);
		return "desk";
	}

	@Override
	public String save() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String noteContent = Struts2Utils.getParameter("noteContent");
		String tipStartDate = Struts2Utils.getParameter("tipStartDate");
		if (StringUtils.isNotEmpty(tipStartDate)) {
			entity.setTipStartDate(format.parse(tipStartDate));
		}

		String noteTitle = substring(noteContent, 8, "...");
		entity.setNoteTitle(noteTitle);
		entity.setNoteContent(noteContent);
		dlyNoteManager.saveDlyNote(entity);

		List<String> list = this.getNoteJson(entity);
		Struts2Utils.renderJson(list);
		return null;
	}

	public String load() throws Exception {
		try {
			entity = dlyNoteManager.getEntity(getId());
			List<String> list = this.getNoteJson(entity);
			Struts2Utils.renderJson(list);
		} catch (ObjectNotFoundException e) {
			Struts2Utils.renderText("error");
		}
		return null;
	}

	@Override
	public String delete() throws Exception {

		String dlyNoteId = getId();
		dlyNoteManager.deleteDlyNote(dlyNoteId);
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		PropertyFilter newFilter = new PropertyFilter("EQS_creator", SpringSecurityUtils.getCurrentUiid());
		filters.add(newFilter);
		pageNote.setOrderBy("updatedDate");
		pageNote.setOrder(Page.DESC);
		pageNote = dlyNoteManager.findPage(pageNote, filters);
		return "desk";
	}

	public String initNotes() throws Exception {
		try {
			dlyNoteManager.initAllNotes();
			Struts2Utils.renderHtml("success");
		} catch (Exception e) {
			Struts2Utils.renderHtml("error");
			e.printStackTrace();
		}
		return null;
	}
	public String initNotesByUiid() throws Exception {
		try {
			String uiid = Struts2Utils.getParameter("uiid");
			dlyNoteManager.initDlyNotes(uiid);
			Struts2Utils.renderHtml("success");
		} catch (Exception e) {
			Struts2Utils.renderHtml("error");
			e.printStackTrace();
		}
		return null;
	}

	public String substring(String str, int toCount, String more) {
		int reInt = 0;
		String reStr = "";
		if (str == null)
			return "";
		char[] tempChar = str.toCharArray();
		for (int kk = 0; (kk < tempChar.length && toCount > reInt); kk++) {
			String s1 = str.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes();
			reInt += b.length;
			reStr += tempChar[kk];
		}
		if (toCount == reInt || (toCount == reInt - 1)) {
			reStr += more;
		}
		return reStr;
	}

	private List<String> getNoteJson(DlyNote dlyNote) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		List<String> list = new ArrayList<String>();
		list.add(dlyNote.getDlyNoteId());
		list.add(dlyNote.getNoteTitle());
		list.add(dlyNote.getNoteContent());
		list.add(format.format(dlyNote.getUpdatedDate()));
		if (dlyNote.getTipStartDate() == null) {
			list.add("");
		} else {
			list.add(format.format(dlyNote.getTipStartDate()));
		}
		return list;
	}

	@Override
	public String deleteBatch() throws Exception {
		dlyNoteManager.deleteBatch(getIds());
		return RELOAD;
	}

	public String getFilter_LIKES_noteContent() {
		return filter_LIKES_noteContent;
	}

	public String getFilter_GED_updatedDate() {
		return filter_GED_updatedDate;
	}

	public String getFilter_LED_updatedDate() {
		return filter_LED_updatedDate;
	}

	public void setFilter_LIKES_noteContent(String filterLIKESNoteContent) {
		filter_LIKES_noteContent = filterLIKESNoteContent;
	}

	public void setFilter_GED_updatedDate(String filterGEDUpdatedDate) {
		filter_GED_updatedDate = filterGEDUpdatedDate;
	}

	public void setFilter_LED_updatedDate(String filterLEDUpdatedDate) {
		filter_LED_updatedDate = filterLEDUpdatedDate;
	}

}
