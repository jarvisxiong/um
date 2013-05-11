/**
 * 
 */
package com.hhz.ump.web.oa;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.service.ServiceException;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.oa.OaNewsCommentManager;
import com.hhz.ump.dao.oa.OaNewsManager;
import com.hhz.ump.entity.oa.OaNews;
import com.hhz.ump.entity.oa.OaNewsComment;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Util;

/**
 * 新闻评论
 * 
 * @author huangj 2010-1-5
 */
@Namespace("/oa")
@Results( {
		@Result(name = "newsDetail", location = "oa-news-comment.action", type = "redirect", params = { "oaNewsId", "${oaNewsId}", "detailFlag",
				"${detailFlag}", "pageSize",
				"${pageSize}" }),
		@Result(name = CrudActionSupport.RELOAD, location = "oa-news-comment.action", type = "redirect", params = { "oaNewsId", "${oaNewsId}" }) })
public class OaNewsCommentAction extends CrudActionSupport<OaNewsComment> {
	private static final long serialVersionUID = 8255399345919335946L;

	@Autowired
	private OaNewsManager oaNewsManager;

	@Autowired
	private OaNewsCommentManager oaNewsCommentManager;

	private OaNewsComment entity;

	private String oaNewsId;

	private String detailFlag = "0";

	@Override
	public String delete() throws Exception {
		try {
			oaNewsCommentManager.deleteOaNewsComment(getId());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		if (detailFlag.equals("1"))
			return "newsDetail";
		else
			return RELOAD;
	}

	@Override
	public String deleteBatch() throws Exception {
		try {
			oaNewsCommentManager.deleteBatch(getIds());
			addActionMessage(getText("common.success"));
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage(getText("common.fail"));
		}
		if (detailFlag.equals("1"))
			return "newsDetail";
		else
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
			page.setOrderBy("createdDate");
			page.setOrder(Page.DESC);
		}
		PropertyFilter filter = new PropertyFilter("EQS_oaNews.oaNewsId", getOaNewsId());
		filters.add(filter);
		page = oaNewsCommentManager.findPage(page, filters);
		// queryTest();
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = oaNewsCommentManager.getEntity(getId());
		} else {
			entity = new OaNewsComment();
		}
	}

	@Override
	public String save() throws Exception {
		OaNews newsTmp = oaNewsManager.getEntity(getOaNewsId());
		entity.setOaNews(newsTmp);
		oaNewsCommentManager.saveOaNewsComment(entity);
		setId(entity.getOaNewsCommentId());
		addActionMessage(getText("common.success"));
		if (detailFlag.equals("1"))
			return "newsDetail";
		else
			return RELOAD;
	}
	
	/**
	 * 回复某条评论
	 * @return
	 * @throws Exception
	 */
	public String replyComment() throws Exception {
	    prepareModel();
	    if (entity == null)
			return null;
	    StringBuilder commentInfo = new StringBuilder();
	    commentInfo.append("\n\n【原帖内容】:");
	    commentInfo.append("\n原帖发布人：" + CodeNameUtil.getUserNameByCd(entity.getCreator()));
	    commentInfo.append("    ");
	    commentInfo.append("发布时间：");
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    commentInfo.append(sdf.format(entity.getCreatedDate()) + "\n");
	    commentInfo.append(Util.clob2String(entity.getContent()));
	    
	    Struts2Utils.renderText(commentInfo.toString());
	    return null;
	}

	public OaNewsComment getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	public String getOaNewsId() {
		return oaNewsId;
	}

	public void setOaNewsId(String oaNewsId) {
		this.oaNewsId = oaNewsId;
	}

	public String getDetailFlag() {
		return detailFlag;
	}

	public void setDetailFlag(String detailFlag) {
		this.detailFlag = detailFlag;
	}

}
