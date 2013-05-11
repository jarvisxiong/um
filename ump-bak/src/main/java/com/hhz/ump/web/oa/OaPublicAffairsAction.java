package com.hhz.ump.web.oa;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.lob.ClobImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateWebUtils;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.web.struts2.Struts2Utils;

import com.hhz.core.utils.CollectionHelper;
import com.hhz.core.web.CrudActionSupport;
import com.hhz.ump.dao.oa.OaPubAffaCommentManager;
import com.hhz.ump.dao.oa.OaPublicAffairsManager;
import com.hhz.ump.entity.oa.OaPubAffaComment;
import com.hhz.ump.entity.oa.OaPublicAffairs;
import com.hhz.ump.util.CodeNameUtil;
import com.hhz.ump.util.Util;

/**
 * @author shixy
 *
 * 2011-1-10
 */
@Namespace("/oa")
public class OaPublicAffairsAction extends CrudActionSupport<OaPublicAffairs> {
	
	private static final long serialVersionUID = -1138270408821468358L;
	
	private OaPublicAffairs entity;
	
	private String commentId;
	
	private String filter_EQS_enabledFlg;
	private String filter_LIKES_subject;
	private String myPost = "0";
	
	@Autowired
	private OaPublicAffairsManager oaPublicAffairsManager;
	
	@Autowired
	private OaPubAffaCommentManager affaCommentManager;
	
	public OaPublicAffairs getModel() {
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String deleteBatch() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

	/**
	 * 搜索所有的帖子
	 */
	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		
		page.setPageSize(20);
		page.setOrderBy("createdDate");
		page.setOrder(Page.DESC);
		
		if(filters.size() == 0){
			filters.add(new PropertyFilter("EQS_enabledFlg", "1"));
		}
		
		filters.add(new PropertyFilter("EQS_deleteFlg", "0"));
		
		if(myPost.equals("1")){
			filters.add(new PropertyFilter("EQS_creator", SpringSecurityUtils.getCurrentUiid()));
		}
		
		page = oaPublicAffairsManager.findPage(page, filters);
		
		return SUCCESS;
	}
	

	/**
	 * 发表新帖子
	 */
	@Override
	public String input() throws Exception {
		
		return INPUT;
	}

	/**
	 * 编辑帖子
	 */
	@Override
	public String save() throws Exception {
		oaPublicAffairsManager.saveOaPublicAffairs(entity);
		return null;
	}

	/**
	 * 删除帖子
	 */
	@Override
	public String delete() throws Exception {
		entity.setDeleteFlg("1");
		oaPublicAffairsManager.saveOaPublicAffairs(entity);
		return null;
	}
	
	/**
	 * 结贴
	 * @return
	 * @throws Exception
	 */
	public String close() throws Exception {
		entity.setEnabledFlg("0");
		entity.setSubject("【已关闭】"+entity.getSubject());
		oaPublicAffairsManager.saveOaPublicAffairs(entity);
		return null;
	}
	
	
	public String detail() throws Exception {
		oaPublicAffairsManager.readPostDetail(getId(), SpringSecurityUtils.getCurrentUiid());
		CollectionHelper.sortList(entity.getOaPubAffaComments(), "createdDate", false);
		return "detail";
	}
	
	public String comment() throws Exception {
		OaPubAffaComment affaComment = affaCommentManager.getEntity(commentId);
		Struts2Utils.renderText(Util.clob2String(affaComment.getContent()));
		return null;
	}
	
	/**
	 * 添加/修改 回复
	 * @return
	 * @throws Exception
	 */
	public String saveComment() throws Exception {
		String content = Struts2Utils.getParameter("commentContent");
		OaPubAffaComment affaComment;
		if (getCommentId() != null) {
			affaComment = affaCommentManager.getEntity(commentId);
		} else {
			affaComment = new OaPubAffaComment();
			affaComment.setOaPublicAffairs(entity);
		}
		affaComment.setContent(new ClobImpl(content));
		affaCommentManager.saveOaPubAffaComment(affaComment);
		return null;
	}
	/**
	 * 删除回复
	 * @return
	 * @throws Exception
	 */
	public String deleteComment() throws Exception {
		affaCommentManager.deleteOaPubAffaComment(commentId);
		return null;
	}
	
	public String replyComment() throws Exception {
		OaPubAffaComment comment = affaCommentManager.getEntity(commentId);
	    StringBuilder commentInfo = new StringBuilder();
	    commentInfo.append("\n\n【原帖内容】:");
	    commentInfo.append("\n原帖发布人：" + CodeNameUtil.getUserNameByCd(comment.getCreator()));
	    commentInfo.append("    ");
	    commentInfo.append("发布时间：");
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    commentInfo.append(sdf.format(comment.getCreatedDate()) + "\n");
	    commentInfo.append(Util.clob2String(comment.getContent()));
	    
	    Struts2Utils.renderText(commentInfo.toString());
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (getId() != null) {
			entity = oaPublicAffairsManager.getEntity(getId());
		} else {
			entity = new OaPublicAffairs();
		}

	}
	
	public void prepareDetail() throws Exception {
		prepareModel();
	}
	public void prepareDelete() throws Exception {
		prepareModel();
	}
	public void prepareSaveComment() throws Exception {
		prepareModel();
	}
	public void prepareClose() throws Exception {
		prepareModel();
	}

	public OaPublicAffairs getEntity() {
		return entity;
	}

	public void setEntity(OaPublicAffairs entity) {
		this.entity = entity;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getFilter_EQS_enabledFlg() {
		return filter_EQS_enabledFlg;
	}

	public void setFilter_EQS_enabledFlg(String filter_EQS_enabledFlg) {
		this.filter_EQS_enabledFlg = filter_EQS_enabledFlg;
	}

	public String getFilter_LIKES_subject() {
		return filter_LIKES_subject;
	}

	public void setFilter_LIKES_subject(String filter_LIKES_subject) {
		this.filter_LIKES_subject = filter_LIKES_subject;
	}

	public String getMyPost() {
		return myPost;
	}

	public void setMyPost(String myPost) {
		this.myPost = myPost;
	}


}
