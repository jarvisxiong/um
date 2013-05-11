/**
 * 
 */
package com.hhz.ump.web.oa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
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
import com.hhz.ump.cache.PlasCacheUtil;
import com.hhz.ump.dao.app.AppAttachFileManager;
import com.hhz.ump.dao.app.AppDictTypeManager;
import com.hhz.ump.dao.oa.OaNewsCommentManager;
import com.hhz.ump.dao.oa.OaNewsManager;
import com.hhz.ump.entity.app.AppAttachFile;
import com.hhz.ump.entity.oa.OaNews;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.DictContants;
import com.hhz.ump.util.OrgTreeUtil;
import com.hhz.uums.entity.ws.WsPlasOrg;

/**
 * 新闻
 * 
 * @author huangj 2010-1-5
 */
@Namespace("/oa")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "oa-news.action", type = "redirect"),
    	    @Result(name = "read", location = "oa-news!read.action", type = "redirect", params = { "id", "${id}" }),
		@Result(name = CrudActionSupport.RELOAD_SUB, location = "oa-news!input.action", type = "redirect", params = { "id", "${id}" }) })
public class OaNewsAction extends CrudActionSupport<OaNews> {
	private static final long serialVersionUID = 8255399345919335946L;

	/**
	 * 新增记录时，附件上传使用的临时ID
	 */
	private String entityTmpId;

	@Autowired
	private AppAttachFileManager appAttachFileManager;

	@Autowired
	private AppDictTypeManager appDictTypeManager;

	@Autowired
	private OaNewsManager oaNewsManager;

	@Autowired
	private OaNewsCommentManager oaNewsCommentManager;
	
	private Page<AppAttachFile> attachMentPage;
	
	// 用于上传附件
	private String bizEntityId;

	private OaNews entity;

	private String commentId;

	private String filter_EQS_typeCd;

	private String filter_LIKES_subject;
	
	private String orgTreeJSON;
	private String toDeptNames;
	private String selectedDeptCds;
	private String creatorName;
	
	private Map<String, String> mapToDeptNames = new HashMap<String, String>();

	@Override
	public String delete() throws Exception {
		try {
			oaNewsManager.deleteOaNews(getId());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD;
	}

	public String deleteComment() throws Exception {
		try {
			oaNewsCommentManager.deleteOaNewsComment(getCommentId());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		return RELOAD_SUB;
	}

	@Override
	public String deleteBatch() throws Exception {
	    try {
		oaNewsManager.deleteBatchOaNews(getIds());
		addActionMessage(getText("common.success"));
	    } catch (ServiceException e) {
		logger.error(e.getMessage(), e);
		addActionMessage(getText("common.fail"));
	    }
	    return RELOAD;
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("newsTime");
			page.setOrder(Page.DESC);
		}
		page = oaNewsManager.findPage(page, filters);
		buildToDeptNames(page.getResult());
		return SUCCESS;
	}
	
	/**
	 * 把发布范围的机构CD转为中文名
	 * @param result
	 */
	private void buildToDeptNames(List<OaNews> oaNewsList) {
	    for (OaNews oaNews : oaNewsList) {
		String toDeptCds = oaNews.getToDeptCds();
		if (StringUtils.isBlank(toDeptCds)) {
		    mapToDeptNames.put(oaNews.getOaNewsId(), "无");
		} else {
		    if ("all".equalsIgnoreCase(toDeptCds)) {
			mapToDeptNames.put(oaNews.getOaNewsId(), "全部");
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
			mapToDeptNames.put(oaNews.getOaNewsId(), orgNames.toString());
		    }
		}
	    }
	}

	@Override
	protected void prepareModel() throws Exception {
	    if (getId() != null) {
		entity = oaNewsManager.getEntity(getId());

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
		entity = new OaNews();
		entityTmpId = RandomUtils.generateTmpEntityId();
	    }
	}

	/**
	 * 把发布范围的机构CD转为中文
	 * @param cds
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
		oaNewsManager.saveOaNews(entity);
		appAttachFileManager.updateTmpFile(entityTmpId, entity.getOaNewsId());
		setId(entity.getOaNewsId());
		addActionMessage(getText("common.success"));
		return RELOAD;
	}
	
	/**
	 * 获取新闻的附件列表
	 * @return
	 */
	public String attachment() {
	    List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
	    attachMentPage = new Page<AppAttachFile>(100);
	    
	    filters.add(new PropertyFilter("EQS_bizEntityId", bizEntityId));
	    filters.add(new PropertyFilter("NEQS_statusCd", "0"));
	    attachMentPage.setOrderBy("createdDate");
	    attachMentPage.setOrder(Page.DESC);
	    
	    attachMentPage = appAttachFileManager.findPage(attachMentPage, filters);
	    
	    return "attachment";
	}

	/**
	 * 发布范围弹出页面
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
		Map<String, List<WsPlasOrg>> map = PlasCache.getOrgOrgsMap(PlasCacheUtil.TREE_TYPE_PHYSICAL);
		List<WsPlasOrg> list = new ArrayList<WsPlasOrg>();
		for(String key : map.keySet()){
			list.addAll(map.get(key));
		}
		
	    orgTreeJSON = OrgTreeUtil.buildOrgTreeJSON(list, getSelectedDeptCds());
	    Struts2Utils.renderText(orgTreeJSON);
	    return null;
	}
	
	public String read() throws Exception {
	    creatorName = CodeNameUtil.getUserNameByCd(entity.getCreator());
	    return "detail";
	}
	
	/**
	 * 新闻详细页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String detail() throws Exception {
	    	oaNewsManager.readNewsDetail(getId(), SpringSecurityUtils.getCurrentUiid());
		return "read";
	}

//	/**
//	 * 增加阅读人
//	 */
//	private void addReader() {
//		boolean flag = false;
//		String readers = null;
//		try {
//			if (entity.getReaders() != null) {
//				readers = entity.getReaders().getSubString(1l, (int) entity.getReaders().length());
//				flag = readers.contains(SpringSecurityUtils.getCurrentUiid() + ",");
//			}
//			if (!flag) {
//				if (readers != null) {
//					readers = readers + SpringSecurityUtils.getCurrentUiid() + ",";
//				} else {
//					readers = SpringSecurityUtils.getCurrentUiid() + ",";
//				}
//				entity.setReaders(new ClobImpl(readers));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/**
	 * 在save()前执行二次绑定.
	 */
	public void prepareRead() throws Exception {
		prepareModel();
	}

	public OaNews getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public Map<String, String> getMapEnabledFlg() {
	    Map<String, String> enabledFlgMap = appDictTypeManager.getDictDataByTypeCd(DictContants.ENABLED_FLG);
	    enabledFlgMap.remove("");
	    return enabledFlgMap;
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

	public String getEntityTmpId() {
		return entityTmpId;
	}

	public void setEntityTmpId(String entityTmpId) {
		this.entityTmpId = entityTmpId;
	}

	public String getToDeptNames() {
	    return toDeptNames;
	}

	public void setToDeptNames(String toDeptNames) {
	    this.toDeptNames = toDeptNames;
	}

	public String getOrgTreeJSON() {
	    return orgTreeJSON;
	}

	public void setOrgTreeJSON(String orgTreeJSON) {
	    this.orgTreeJSON = orgTreeJSON;
	}

	public String getSelectedDeptCds() {
	    return selectedDeptCds;
	}

	public void setSelectedDeptCds(String selectedDeptCds) {
	    this.selectedDeptCds = selectedDeptCds;
	}

	public String getCreatorName() {
	    return creatorName;
	}

	public void setCreatorName(String creatorName) {
	    this.creatorName = creatorName;
	}

	public Map<String, String> getMapToDeptNames() {
	    return mapToDeptNames;
	}

	public void setMapToDeptNames(Map<String, String> mapToDeptNames) {
	    this.mapToDeptNames = mapToDeptNames;
	}

	public String getBizEntityId() {
	    return bizEntityId;
	}

	public void setBizEntityId(String bizEntityId) {
	    this.bizEntityId = bizEntityId;
	}

	public Page<AppAttachFile> getAttachMentPage() {
	    return attachMentPage;
	}

	public void setAttachMentPage(Page<AppAttachFile> attachMentPage) {
	    this.attachMentPage = attachMentPage;
	}
}
