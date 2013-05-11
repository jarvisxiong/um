<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/global.jsp" %>
		<%@ include file="/common/meta.jsp"%>
		<title>公告</title>
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/oa/oa-public-affair.css" />
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/table.js"></script>
		<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/desk/desk-oa.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			var notifyDiv = $('#notifyContentDiv');
			var pageNo = $('#idPageNo').val();
			if(!pageNo){
				pageNo = 1 ;
			}
			var status = $('#filterNotifyStatus').val();
			var timeStart = $('#filterStartSendTime').val();
			var timeEnd = $('#filterEndSendTime').val();
			var title = $('#filterSubject').val();
			
			$.post(
				'${ctx}/desk/desk-notify!query.action',
				{
					'pageSize':30,
					'pageNo':pageNo,
					'filterNotifyStatus':status,
					'filterSubject':title,
					'filterStartSendTime':timeStart,
					'filterEndSendTime':timeEnd
				},
				function(result){
					notifyDiv.html(result);
					autoHeight();
					});
			
		}); 
		</script>
	</head>
	
	<body>
		<div class="title_bar search_bar_title" style="font-weight:bolder;">公告</div>
		<div class="pagebody_c_border" style="width:100%;">
			<s:form id="mainForm" action="desk-notify.action" method="post">
			<s:hidden id="idPageNo" value="%{page.pageNo}"></s:hidden>
				<div class="search_bar search_bar_condition_all" >
					<div class="search_bar" style="float:left;margin-left:-1px;height:100%;width:450px;">
						<div class="search_bar_condition_single" style="margin-top:6px;">
								<span class="title">状态：</span><select id="filterNotifyStatus" style="width:80px;font-size:12px;" name="filterNotifyStatus" onchange="$('#pageNo').val(''); document.getElementById('mainForm').submit();">
								<option value="all">全部公告</option>
								<option value="new" <s:if test="%{filterNotifyStatus == 'new'}">selected="selected"</s:if>>未读公告</option>
							</select>
						</div><div class="search_bar_condition_single" style="margin-top:6px;float:right;">
							<span class="title"><s:text name="oaOaNews.newsTime" />：</span>
							<s:textfield id="filterStartSendTime" name="filterStartSendTime" onfocus="WdatePicker()" cssClass="Wdate" size="12" />
							<span class="title">至</span>
							<s:textfield id="filterEndSendTime" name="filterEndSendTime" onfocus="WdatePicker()" cssClass="Wdate" size="12" />
						</div><div class="search_bar_condition_single" style="margin-buttom:6px;width:100%;">
							<span class="title">标题：</span><s:textfield name="filterSubject" id="filterSubject" />
						</div>
					</div>
					<div class="search_bar" style="height:55px;padding:6px 20px;float:left;">
							<input type="button" class="btn_blue_55_55" onclick="$('#pageNo').val(''); document.getElementById('mainForm').submit(); return false;" value="搜索"/>
							<input type="button" class="btn_green_55_55" onclick="$('#mainForm input:text').val(''); return false;" value="清空" />
					</div>
				</div>
				<div id="notifyContentDiv" style="float:left;width:100%;">
					<div class="loading">
						正在加载数据，请耐心等待。
					</div>
				</div>
			</s:form>
		</div>
	</body>
</html>