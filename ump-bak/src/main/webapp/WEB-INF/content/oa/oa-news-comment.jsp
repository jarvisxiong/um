<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<div id="comments_container">
	<div class="content">
		<s:if test="page.result.size == 0">
			<div style="height: 50px; line-height: 50px; width: 100%; text-align: left; font-size: 16px; font-weight: bold;">
				暂 无 评 论
			</div>
		</s:if>
		<s:else>
			<s:if test="detailFlag != 1">
				<div id="comment_pager" class="pager">
					共有&nbsp;${page.totalCount}&nbsp;条记录&nbsp;&nbsp;&nbsp;&nbsp;
					 当前第 ${page.pageNo}/${page.totalPages}&nbsp;页
					 <c:if test="${page.hasPre}">
					 	<a href="#" onclick="jumpCommentPageTo('${oaNewsId}', 1); return false;">首页</a>
						<a href="#" onclick="jumpCommentPageTo('${oaNewsId}', '${page.prePage}'); return false;">上一页</a>
					 </c:if>
					 <c:if test="${page.hasNext}">
					 	<a href="#" onclick="jumpCommentPageTo('${oaNewsId}', '${page.totalPages}'); return false;">末页</a>
						<a href="#" onclick="jumpCommentPageTo('${oaNewsId}', '${page.nextPage}'); return false;">下一页</a>
					 </c:if>
				</div>
			</s:if>
			<s:elseif test="detailFlag==1">
				<div id="latest_comments_title">
					最近
					<c:if test="${page.totalCount > page.pageSize}">${page.pageSize}</c:if>
					<c:if test="${page.totalCount <= page.pageSize}">${page.totalCount}</c:if>
					 条评论
					 <div class="hengxian"></div>
				</div>
			
				<div id="comment_firstpage">
					<div style="float: left;"><s:text name="page.totalCount"><s:param>${page.totalCount}</s:param></s:text></div>
					<div style="float: right;">
						<c:if test="${page.totalCount > page.pageSize}">
							<a href="#" onclick="return showAllComments('${oaNewsId}');" style="font-size: 12px;"><strong>查看所有评论&gt;&gt;</strong></a>
						</c:if>
					</div>
				</div>
			</s:elseif>
			
			<div id="comment_list">
				<s:iterator value="page.result" var="comment">
					<div class="comment_info">
						<span style="float:left; margin-left: 5px;color:#0167A2;font-weight:bold;margin-right:10px;">
							<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %></span><span> 发表时间：<s:property value="createdDate"/>
						</span>
						<span style="float:right; margin-right: 20px;">
							<a class="replyLink" onclick="replyComment('${oaNewsCommentId}'); return false;" href="#"><s:text name="common.reply"/></a>
							
							<security:authorize ifAnyGranted="A_ADMIN" >
								<a href="#" onclick="deleteComment('${oaNewsCommentId}','${oaNewsId}','${detailFlag}','${pageSize}'); return false;">
									<s:text name="common.delete"/>
								</a>
							</security:authorize>
						</span>
					</div>
					<div class="comment_content">
						<pre><s:property value="content" escape="true" /></pre>
					</div>
				</s:iterator>
			</div>
		</s:else>
	</div>
	
	<div class="hengxian"></div>
	
	<div id="addComments">
		<s:form id="inputFromComment" action="oa-news-comment!save.action" method="post" cssClass="inputFrom">
			<input type="hidden" name="oaNewsId" value="${oaNewsId}" />
			<input type="hidden" name="pageSize" value="${pageSize}"/>
			<input type="hidden" name="detailFlag" value="${detailFlag}" />
			<input type="hidden" name="parentId" value="" />
			
			<div class="top">
				<table id="inputCommentDiv">
					<tr class="top">
						<th width="7%">发表评论</th>
						<td width="95%">&nbsp;</td>
					</tr>
					<tr class="middle">
						<td class="label">内容：</td>
						<td><s:textarea id="commentInputArea" name="content" cssClass="textarea"/></td>
					</tr>
					<tr class="bottom">
						<td class="label">署名：</td>
						<td><strong><%=SpringSecurityUtils.getCurrentUserName() %></strong></td>
					</tr>
				</table>
			</div>
			
			<div align="center">
				<table style="table-layout: fixed; width: 100%;margin-top: 10px;" cellpadding="0" cellspacing="0">
					<tr>
						<td align="right" style="padding-right: 10px;">
							<input type="button" class="btn_green" onclick="saveComment();" value="发表" />
						</td>
						<td align="left" style="padding-left: 10px;">
							<input type="button" class="btn_green" onclick="closeWindow();" value="关闭" />
						</td>
					</tr>
				</table>
			</div>
		</s:form>
	</div>
</div>
		
<script type="text/javascript">
	$(".anniu_lan_2").each(function() {
		$(this).mouseover(function() {
			$(this).removeClass("anniu_lan_2").addClass("anniu_lan_2_an");
		});
		$(this).mouseout(function() {
			$(this).removeClass("anniu_lan_2_an").addClass("anniu_lan_2");
		});
	});
</script>
