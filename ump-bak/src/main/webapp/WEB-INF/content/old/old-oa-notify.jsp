<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/global.jsp" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>旧OA公告</title>
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />		
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/table.js"></script>
		<script src="${ctx}/js/datePicker/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/js/desk/desk-oa.js"></script>
		<link  href="${ctx}/resources/css/oa/oa-public-affair.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		$(document).ready(function(){
			var notifyDiv = $('#notifyContentDiv');
			var pageNo = $('#idPageNo').val();
			if(!pageNo){
				pageNo = 1 ;
			}
			var availHeight = $(document).height() - notifyDiv.offset().top;
			// 减去表头和表尾部翻页部分高度再计算行数 ( avail - th - tfoot - css margin)
			var lineCount = parseInt((availHeight-26-30-5)/26); 

			// 搜索参数
			var title = $('#filterSubject').val();
			
			$.post(
				'${ctx}/old/old-oa!queryNotify.action',
				{
					'pageSize':lineCount,
					'pageNo':pageNo,
					'filter_LIKES_subjectme':title
				},
				function(result){
					notifyDiv.html(result);
				});
			
		}); 
		</script>
	</head>
	
	<body>
		<div class="title_bar search_bar_title">旧公告</div>
		<div class="pagebody_c_border" style="width:100%">
			<s:form id="mainForm" action="old-oa!notifys.action" method="post">
			<s:hidden id="idPageNo" value="%{pageNotifys.pageNo}"></s:hidden>
			<div class="search_bar search_bar_condition_all" >
				<div class="search_bar_condition_single" style="margin-buttom:6px;width:400px;padding:20px 10px;float:left;width:420px;">
					<span class="title">标题：</span><s:textfield name="filter_LIKES_subjectme" id="filterSubject" cssStyle="width:84.5%;" />
				</div>				
				<div class="search_bar" style="height:55px;padding:6px 5px;float:left;width:150px;">
					<input type="button" class="btn_blue_55_55" onclick="$('#pageNo').val(''); document.getElementById('mainForm').submit(); return false;" value="搜索"/>
					&nbsp;
					<input type="button" class="btn_green_35_55" onclick="$('#mainForm input:text').val(''); return false;" value="清空"/>
				</div>
			</div>
			<div id="notifyContentDiv" style="float:left;width:100%;">
				<div class="loading">
					正在加载数据，请耐心等待。
				</div>
			</div>
			</s:form>
		</div>
		<div>
		</div>
	</body>
</html>


