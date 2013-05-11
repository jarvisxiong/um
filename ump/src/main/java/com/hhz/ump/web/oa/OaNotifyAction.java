/**
 * 
 */
package com.hhz.ump.web.oa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.lob.ClobImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.ServiceException;
import com.hhz.core.utils.RandomUtils;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.cache.PlasCache;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.oa.OaNotifyManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.oa.OaNotify;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.OrgTreeUtil;
import com.hhz.uums.entity.ws.WsPlasOrg;

/**
 * 公告
 * 
 * @author wuzm 2010-1-7
 */
@Namespace("/oa")
@Results( {
		@Result(name = CrudActionSupport.RELOAD, location = "oa-notify.action", type = "redirect"),
		@Result(name = CrudActionSupport.RELOAD_SUB, location = "oa-notify!input.action", type = "redirect", params = { "id", "${id}" })
})
public class OaNotifyAction extends CrudActionSupport<OaNotify> {
	private static final long serialVersionUID = 8255399345919335946L;

	/**
	 * 新增记录时，附件上传使用的临时ID
	 */
	private String entityTmpId;

	public String getEntityTmpId() {
		return entityTmpId;
	}

	public void setEntityTmpId(String entityTmpId) {
		this.entityTmpId = entityTmpId;
	}

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private OaNotifyManager oaNotifyManager;

	@Autowired
	private AppAttachFileManager appAttachFileManager;

	private OaNotify entity;


	private String commentId;

	private String filter_EQS_typeCd;

	private String filter_LIKES_subject;
	
	private List<WsPlasOrg> wsUaapOrgs;
	
	// 用户构造机构数的JSON字符串
	private String orgTreeJSON;
	
	// 发机构名称
	private String toDeptNames;
	
	// 已选择的机构CD
	private String selectedDeptCds;
	
	// 用于上传附件
	private String bizEntityId;
	
	// 把创建者用户名转为中文名字
	private Map<String, String> mapCreatorName = new HashMap<String, String>();
	
	// 公告的附件，每条公告最多只能有一个PDF附件
	private AppAttachFile notifyAttachMent;
	
	// 发布范围中文名称
	private Map<String, String> mapToDeptNames = new HashMap<String, String>();

	@Override
	public String delete() throws Exception {
		try {
			oaNotifyManager.deleteOaNotify(getId());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}


	@Override
	public String deleteBatch() throws Exception {
		try {
			oaNotifyManager.deleteBatchOanotify(getIds());
			
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}

	@Override
	public String input() throws Exception { 
	    return INPUT;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		}
		page = oaNotifyManager.findPage(page, filters);
		
		buildCreatorNames(page.getResult());
		buildToDeptNames(page.getResult());
		
		return SUCCESS;
	}
	
	/**
	 * 把发布范围的机构CD转为中文名显示
	 * @param notifyList
	 */
	private void buildToDeptNames(List<OaNotify> notifyList) {
	    for (OaNotify notify : notifyList) {
		String toDeptCds = notify.getToDeptCds();
		if (StringUtils.isNotBlank(toDeptCds)) {
		    if ("all".equalsIgnoreCase(toDeptCds)) {
			mapToDeptNames.put(notify.getOaNotifyId(), "全部");
		    } else {
			String[] cds = toDeptCds.split(",");
			StringBuilder orgNames = new StringBuilder();
			for (int i = 1; i < cds.length; i ++) {
			    String cd = cds[i];
			    orgNames.append(CodeNameUtil.getDeptNameByCd(cd));
			    if (i != cds.length - 1) {
				orgNames.append(",");
			    }
			}
			mapToDeptNames.put(notify.getOaNotifyId(), orgNames.toString());
		    }
		} else {
		    mapToDeptNames.put(notify.getOaNotifyId(), "");
		}
	    }
	}
	
	/**
	 * 在公告列表中创建人要显示为中文姓名
	 * @param notifyList
	 */
	private void buildCreatorNames(List<OaNotify> notifyList) {
	    if (notifyList == null)
			return;
	    for (OaNotify notify : notifyList) {
		mapCreatorName.put(notify.getCreator(), CodeNameUtil.getUserNameByCd(notify.getCreator()));
	    }
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
		    entity = oaNotifyManager.getEntity(getId());
		    // 把发布范围的机构CD转为中文名称并在编辑页面显示
		    String toDeptsCds = entity.getToDeptCds();
		    if (StringUtils.isNotBlank(toDeptsCds)) {
			if ("all".equalsIgnoreCase(toDeptsCds)) {
			    toDeptNames = "全部";
			} else {
			    String[] cds = toDeptsCds.split(",");
			    toDeptNames = buildToOrgNames(cds);
			}
		    }
		} else {
		    entity = new OaNotify();
		    entityTmpId = RandomUtils.generateTmpEntityId();
		}
	}
	
	/**
	 * 把发布范围的部门CD显示为中文机构名
	 * @param orgCds
	 * @return
	 */
	private String buildToOrgNames(String[] orgCds) {
	    StringBuilder orgNames = new StringBuilder();
	    for (int i = 1; i < orgCds.length; i ++) {
		String cd = orgCds[i];
		orgNames.append(CodeNameUtil.getDeptNameByCd(cd));
		if (i != orgCds.length - 1) {
		    orgNames.append(",");
		}
	    }
	    return orgNames.toString();
	}

	@Override
	public String save() throws Exception {
		oaNotifyManager.saveOaNotify(entity);
		setId(entity.getOaNotifyId());
		appAttachFileManager.updateTmpFile(entityTmpId, entity.getOaNotifyId());
		addActionMessage(getText("common.success"));
		return RELOAD;
	}
	
	/**
	 * 公告的附件管理页面，一条公告最多只有一个PDF附件
	 * @return
	 */
	public String attachment() {
	    List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
	    Page<AppAttachFile> attachMentPage = new Page<AppAttachFile>(1);
	    
	    filters.add(new PropertyFilter("EQS_bizEntityId", bizEntityId));
	    filters.add(new PropertyFilter("NEQS_statusCd", "0"));
	    page.setOrderBy("createdDate");
	    page.setOrder(Page.DESC);
	    
	    attachMentPage = appAttachFileManager.findPage(attachMentPage, filters);
	    
	    if (attachMentPage.getResult().size() == 1) {
		notifyAttachMent = attachMentPage.getResult().get(0);
	    }
	
	    return "attachment";
	}
	
	/**
	 * 公告发布范围弹出页面
	 * @return
	 */
	public String toDepts() {
	    return "toDepts";
	}
	
	/**
	 * 构造机构树
	 * @return
	 */
	public String buildDepts() {
	    orgTreeJSON = OrgTreeUtil.buildOrgTreeJSON(PlasCache.getOrgEnableList(), getSelectedDeptCds());
	    Struts2Utils.renderText(orgTreeJSON);
	    return null;
	}
	
	/**
	 * 构造机构树
	 * @return
	 */
	public String buildCenters() {
		orgTreeJSON = OrgTreeUtil.buildOrgTreeJSON(PlasCache.getOrgEnableList(), getSelectedDeptCds());
		Struts2Utils.renderText(orgTreeJSON);
		return null;
	}

	 /**
	  * 把置顶公告设置为已读
	  * @return
	  * @throws Exception
	  */
	 public String readNotify() throws Exception {
	     entity = oaNotifyManager.getEntity(getId());
	     addReader();
	     oaNotifyManager.saveOaNotify(entity);
	     
	     Struts2Utils.renderText("succ");
	     return null;
	 }
	 
	private void addReader() {
		boolean flag = false;
		String readers = null;
		try {
			if (entity.getReaders() != null) {
				readers = entity.getReaders().getSubString(1l,
						(int) entity.getReaders().length());
				flag = readers.contains(SpringSecurityUtils
.getCurrentUiid()
						+ ",");
			}
			if (!flag) {
				if (readers != null) {
					readers = readers + SpringSecurityUtils.getCurrentUiid() + ",";
				} else {
					readers = SpringSecurityUtils.getCurrentUiid() + ",";
				}
				entity.setReaders(new ClobImpl(readers));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 在save()前执行二次绑定.
	 */
	public void prepareDetail() throws Exception {
		prepareModel();
	}

	public OaNotify getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public Map<String, String> getMapEnabledFlg() {
	    Map<String, String> enabledFlagMap = appDictTypeManager.getDictDataByTypeCd(DictContants.ENABLED_FLG);
	    enabledFlagMap.remove("");
	    return enabledFlagMap;
	}

	public Map<String, String> getMapNewsType() {
		return appDictTypeManager.getDictDataByTypeCd(DictContants.OA_NEWS_TYPE);
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getFilter_EQS_typeCd() {
		return filter_EQS_typeCd;
	}

	public void setFilter_EQS_typeCd(String filterEQSTypeCd) {
		filter_EQS_typeCd = filterEQSTypeCd;
	}

	public String getFilter_LIKES_subject() {
		return filter_LIKES_subject;
	}

	public void setFilter_LIKES_subject(String filterLIKESSubject) {
		filter_LIKES_subject = filterLIKESSubject;
	}

	public List<WsPlasOrg> getWsPlasOrgs() {
	    return wsUaapOrgs;
	}

	public void setWsPlasOrgs(List<WsPlasOrg> wsUaapOrgs) {
	    this.wsUaapOrgs = wsUaapOrgs;
	}

	public String getOrgTreeJSON() {
	    return orgTreeJSON;
	}

	public void setOrgTreeJSON(String orgTreeJSON) {
	    this.orgTreeJSON = orgTreeJSON;
	}

	public String getToDeptNames() {
	    return toDeptNames;
	}

	public void setToDeptNames(String toDeptNames) {
	    this.toDeptNames = toDeptNames;
	}

	public String getSelectedDeptCds() {
	    return selectedDeptCds;
	}

	public void setSelectedDeptCds(String selectedDeptCds) {
	    this.selectedDeptCds = selectedDeptCds;
	}

	public Map<String, String> getMapCreatorName() {
	    return mapCreatorName;
	}

	public void setMapCreatorName(Map<String, String> mapCreatorName) {
	    this.mapCreatorName = mapCreatorName;
	}

	public String getBizEntityId() {
	    return bizEntityId;
	}

	public void setBizEntityId(String bizEntityId) {
	    this.bizEntityId = bizEntityId;
	}

	public AppAttachFile getNotifyAttachMent() {
	    return notifyAttachMent;
	}

	public void setNotifyAttachMent(AppAttachFile notifyAttachMent) {
	    this.notifyAttachMent = notifyAttachMent;
	}

	public Map<String, String> getMapToDeptNames() {
	    return mapToDeptNames;
	}

	public void setMapToDeptNames(Map<String, String> mapToDeptNames) {
	    this.mapToDeptNames = mapToDeptNames;
	}

	// public void setOaNewsComment(OaNewsComment oaNewsComment) {
	// this.oaNewsComment = oaNewsComment;
	// }
}
