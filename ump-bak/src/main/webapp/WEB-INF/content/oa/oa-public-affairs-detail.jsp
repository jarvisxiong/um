<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>详细内容</title>
		<meta http-equiv="Content-Type" content="text/html" />
		<link rel="stylesheet" href="${ctx}/css/desk/article.css" type="text/css" />
		<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
		<script language="javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/xheditor/xheditor-zh-cn.min.js"></script>
	</head>

	<body>
		<div id="container">
			<div id="container_outer">
				<div class="border corner_top_left"></div>
				<div class="border corner_top_middle"></div>
				<div class="border corner_top_right"></div>
				<div class="border corner_middle_left"></div>
				<div class="border corner_middle_right"></div>
				<div class="border corner_bottom_right"></div>
				<div class="border corner_bottom_middle"></div>
				<div class="border corner_bottom_left"></div>
				
				<div id="container_inner">
					<div id="article_header">
						<h1>${subject}</h1>
						
						<div style="">
							<a href="#" id="hideArticle" style="position: absolute; top: 15px; right: 15px; text-decoration none; color: #000; font-weight: bold;" onclick="return hideArticle(200, this.id);">
								隐藏正文&gt;&gt;&gt;
							</a>
						</div>
					</div>
					
					<div id="_article">
						<div class="hengxian"></div>
						<div id="newsInfo" style="margin-top:5px;">
							发帖人：<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %>&nbsp;&nbsp;&nbsp;&nbsp;
							点击<s:property value="clickCount"/>次&nbsp;&nbsp;&nbsp;&nbsp;
							跟帖<s:property value="oaPubAffaComments.size"/>条&nbsp;&nbsp;&nbsp;&nbsp;
							发布于<s:property value="createdDate" />
						</div>
					
						<div id="article_content" style="line-height:24px;">
							<s:property value="content" escape="false" />
						</div>
						<% if(SpringSecurityUtils.hasRole("A_PUBLIC_AFFAIRS")||JspUtil.findString("creator").equals(SpringSecurityUtils.getCurrentUiid())){ %>
						<div style="padding:5px;">
							<button class="btn_green_65_20" type="button" style="margin-right:10px;" onclick="editPost('${oaPublicAffairsId}')" >编辑帖子</button>
							<button class="btn_green_65_20" type="button" style="margin-right:10px;" onclick="closePost('${oaPublicAffairsId}')" >关闭帖子</button>
							<button class="btn_green_65_20" type="button" onclick="deletePost('${oaPublicAffairsId}')" >删除帖子</button>
						</div>
						<%} %>
						<div class="hengxian"></div>
					</div>
					
					<div id="comments_container">
						<div class="content">
							<s:if test="oaPubAffaComments.size == 0">
								<div style="height: 50px; line-height: 50px; width: 100%; text-align: left; font-size: 16px; font-weight: bold;">
									暂 无 回 复
								</div>
							</s:if>
							<s:else>
								<div id="comment_list">
									<s:iterator value="oaPubAffaComments" >
										<div class="comment_info">
											<span style="float:left; margin-left: 5px;color:#0167A2;font-weight:bold;margin-right:10px;">
												<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %> </span><span>发表时间：<s:property value="createdDate"/>
											</span>
											<span style="float:right; margin-right: 20px;">
												<a class="replyLink" onclick="replyComment('${oaPubAffaCommentId}'); return false;" href="#"><s:text name="common.reply"/></a>
												<% if(SpringSecurityUtils.hasRole("A_PUBLIC_AFFAIRS")||JspUtil.findString("creator").equals(SpringSecurityUtils.getCurrentUiid())){ %>
												<a class="replyLink" onclick="editComment('${oaPubAffaCommentId}'); return false;" href="#">编辑</a>
												<a href="#" onclick="deleteComment('${oaPubAffaCommentId}',this); return false;">
													<s:text name="common.delete"/>
												</a>
												<%} %>
											</span>
										</div>
										<div class="comment_content" style="height:30px;line-height:30px;">
											<pre><s:property value="content"  /></pre>
										</div>
									</s:iterator>
								</div>
							</s:else>
						</div>
						
						<div class="hengxian"></div>
						
						<s:if test="enabledFlg == 0">
							<div align="center" style="font-size: 20px;color:#A0522D; font-weight: bold;padding-top:10px;">
								该贴已经关闭,不能进行回复！
							</div>
						</s:if>
						<s:else>
						<div id="addComments">
							<form id="inputFromComment" action="${ctx}/oa/oa-public-affairs!saveComment.action" method="post" class="inputFrom">
								<input type="hidden" name="id" value="${oaPublicAffairsId}" />
								<div class="top">
									<table id="inputCommentDiv">
										<tr class="top">
											<th width="7%">发表回复</th>
											<td width="95%">&nbsp;</td>
										</tr>
										<tr class="middle">
											<td class="label">内容：</td>
											<td><textarea id="commentInputArea" name="commentContent" class="textarea"></textarea></td>
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
												<div class="func_icon" onclick="saveComment();">
													发表
												</div>
											</td>
											<td align="left" style="padding-left: 10px;">
												<div class="func_icon_red" onclick="closeWindow();">
													关闭
												</div>
											</td>
										</tr>
									</table>
								</div>
							</form>
						</div>
						</s:else>
					</div>
				</div>
				<div style="height: 15px; position: absolute; top: -15px; left: 0px; width: 100%;"></div>
				<div style="width: 15px; position: absolute; right: -15px; top: 0px; height: 100%;"></div>
				<div style="height: 15px; position: absolute; bottom: -15px; left: 0px; width: 100%;"></div>
				<div style="width: 15px; position: absolute; left: -15px; top: 0px; height: 100%;"></div>
			</div>
		</div>
		
		<script type="text/javascript">
			$(function(){
				if('${deleteFlg}' == '1'){
					alert('该贴已经被删除！');
					window.close();
				}
			});
			function hideArticle(speed, srcEle) {
				if ($("#_article").css("display") == "none") {
					$("#_article").slideDown(speed);
					$("#" + srcEle).html("隐藏正文>>>");
				} else {
					$("#_article").slideUp(speed);
					$("#" + srcEle).html("显示正文>>>");
				}
			}
			function closeWindow() {
			  window.close();
			}
			function replyComment(commentId) {
				$.post("${ctx}/oa/oa-public-affairs!replyComment.action?commentId=" + commentId,
					function(result) {
						if (result) {
							$("#commentInputArea").val(result);
							$("#commentInputArea").focus();
						}
					}
				);
			}
			function saveComment(){
				$('#inputFromComment').ajaxSubmit(function(){
					window.location.reload();
				});
			}
			function deleteComment(commentId,dom){
				if(!window.confirm("确认要删除该回复吗？"))return;
				$.post("${ctx}/oa/oa-public-affairs!deleteComment.action?commentId=" + commentId,
						function() {
							var _p = $(dom).parents('div.comment_info');
							var _c = _p.next();
							_p.remove();
							_c.remove();
						}
					);
			}
			function editComment(commentId){
				ymPrompt.commentId = commentId;
				ymPrompt.confirmInfo({
					icoCls:"",
					title:"编辑",
					message:"<textarea id='editCommentDiv' style='height:150px;width:500px;'></textarea>",
					winPos:"c",
					width:505,
					height:210,
					showMask:false,
					handler:saveEditComment,
					afterShow:function(){
						$.get("${ctx}/oa/oa-public-affairs!comment.action",{commentId:commentId},function(result){
							$('#editCommentDiv').val(result);
						});
					}
				});
			
			}
			function saveEditComment(tp){
				var content = $('#editCommentDiv').val();
				if(tp=='ok'){
					$.post("${ctx}/oa/oa-public-affairs!saveComment.action",{commentContent:content,commentId:ymPrompt.commentId},function(){
						window.location.reload();
					});
				}
			}
			var postEditor; 
			function editPost(id){
				ymPrompt.win({
					icoCls:"",
					title:"编辑帖子",
					message:"<div id='editPostDiv'><img align='absMiddle' src='${ctx}/images/loading.gif'></div>",
					winPos:"c",
					width:600,
					height:290,
					showMask:false,
					afterShow:function(){
						$.get("${ctx}/oa/oa-public-affairs!input.action",{id:id},function(result){
							$('#editPostDiv').html(result);
							postEditor = $('#postEditorDiv').xheditor({
								tools:'simple',forcePtag:false,showBlocktag:false,html5Upload:false,upMultiple:'1',
								upImgUrl:"${ctx}/oa/oa-email!upload.action",upImgExt:"jpg,jpeg,gif,png"
							});
						});
					}
				});
			}
			
			function savePost(){
				var content = postEditor.getSource();
				var title = $('#postSubject').val();
				if($.trim(title) == ""){
					alert('主题不能为空');
					return false;
				}
				if($.trim(title) == ""){
					alert('内容不能为空');
					return false;
				}
				
				$('#postForm').ajaxSubmit(function(){
					alert('编辑成功！');
					ymPrompt.close();
					window.location.reload();
				});
			}
			function deletePost(id){
				$.post("${ctx}/oa/oa-public-affairs!delete.action",{'id':id},function(){
					window.close();
				});
			}
			function closePost(id){
				$.post("${ctx}/oa/oa-public-affairs!close.action",{'id':id},function(){
					window.location.reload();
				});
			}
		</script>
	</body>
</html>
