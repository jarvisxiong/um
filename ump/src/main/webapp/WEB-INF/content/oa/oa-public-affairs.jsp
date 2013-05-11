<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/global.jsp" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>PowerDesk All News Page</title>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
		<link  href="${ctx}/resources/css/oa/oa-public-affair.css"rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/table.js"></script>
		<script src="${ctx}/js/datePicker/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/js/desk/desk-oa.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/xheditor/xheditor-zh-cn.min.js"></script>
	</head>
	
	<body>
		<div class="title_bar">员工交流</div>
		<form action="${ctx}/oa/oa-public-affairs.action" method="post" id="mainForm">
		<div id="searchPanel" style="padding:5px;height:25px;line-height:25px;border-bottom: 1px solid #8C8F94;">
			<div style="float:left;">
				<span>状态:</span>
				<s:select list="#{'1':'正常','0':'已关闭'}" name="filter_EQS_enabledFlg" cssStyle="width:60px;margin-right:10px;"></s:select>
				<span>主题：</span>
				<s:textfield name="filter_LIKES_subject"></s:textfield>
				<input type="checkbox" name="myPost" style="height:10px;margin-left:10px;" value="1" 
					<s:if test="myPost == 1">checked="checked"</s:if>
				/>
				<span style="padding-right:10px;">我的帖子</span>
				
				<button class="btn_green_55_20" type="submit">搜索</button>
			</div>
			<div style="float:right;">
				<button class="btn_green_65_20" type="button" onclick="addPost()" >发表帖子</button>
			</div>
		</div>
		<div id="resultPanel">
			<s:if test="page.result.size == 0">
				<div class="noResult">未找到符合条件的记录, 请修改搜索条件重新检索！</div>
			</s:if>
			<s:else>
				<div style="float:left;margin:0px 2px;overflow: auto; overflow-x: hidden;width:100%;">
					<table class="content_table">
						<tr style="cursor: default;">
							<th align="left" style="padding-left:8px;background:none;">标题</th>
							<th width="60px" align="left">发布人</th>
							<th width="140px" align="left">发布时间</th>
							<th width="80px" align="left">查看次数</th>
							<th width="80px" align="left">评论</th>
						</tr>
						<s:iterator value="page.result">
						<tr class="mainTr" onclick="removeNewImg(this); window.open('${ctx}/oa/oa-public-affairs!detail.action?id=${oaPublicAffairsId}'); return false;">
							<td align="left" title="${subject}">
								<div class="ellipsisDiv_full" style="height:26px; display:inline;">
									<c:out value="${subject}" />
								</div>
								<c:set var="userName"><%=SpringSecurityUtils.getCurrentUiid()%></c:set>
								<c:set var="cReaders"><s:property value="readers" /></c:set>
								<c:set var="isReaded">${fn:indexOf(cReaders, userName)}</c:set>
								<c:if test="${isReaded==-1}"><img class="new_img" src="${ctx}/images/new.gif" /></c:if>
							</td>
							<td align="left"><div><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %></div></td>
							<td align="left"><div><s:date name="createdDate" format="yyyy-MM-dd hh:mm"/></div></td>
							<td align="left"><div><s:property value="clickCount" />&nbsp;</div></td>
							<td align="left"><div>共 <s:text name="%{oaPubAffaComments.size}" />&nbsp;条</div></td>
						</tr>
						</s:iterator>
					</table>
				</div>
				<div class="table_pager">
					<p:page />
				</div>
			</s:else>
		</div>
		</form>
		
		<script type="text/javascript">
			var postEditor; 
			function addPost(){
				ymPrompt.win({
					icoCls:"",
					title:"发表帖子",
					message:"<div id='addPostDiv'><img align='absMiddle' src='${ctx}/images/loading.gif'></div>",
					winPos:"c",
					width:600,
					height:290,
					showMask:false,
					afterShow:function(){
						$.get("${ctx}/oa/oa-public-affairs!input.action",function(result){
							$('#addPostDiv').html(result);
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
					alert('发表成功！');
					ymPrompt.close();
					location.href="${ctx}/oa/oa-public-affairs.action";
				});
			}
		</script>
	</body>
</html>


