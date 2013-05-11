<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/global.jsp" %>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>PowerDesk All News Page</title>
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/oa/oa-public-affair.css" />
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/table.js"></script>
		<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/desk/desk-oa.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			var newsContentDiv = $('#newsContentDiv');
			var pageNo = $('#idPageNo').val();
			if(!pageNo){
				pageNo = 1 ;
			}
			var availHeight = $(document).height() - newsContentDiv.offset().top;
			// 减去表头和表尾部翻页部分高度再计算行数 ( avail - th - tfoot - css margin)
			var lineCount = parseInt((availHeight-26*2-30-5)/26); 
			// 搜索参数
			var status = $('#filterNewsStatus').val();
			var type = $('#filterTypeCd').val();
			var timeStart = $('#filterStartNewsTime').val();
			var timeEnd = $('#filterEndNewsTime').val();
			var title = $('#filterSubject').val();
			
			$.post(
				'${ctx}/desk/desk-news!query.action',
				{
					'pageSize':lineCount,
					'pageNo':pageNo,
					'filterNewsStatus':status,
					'filterTypeCd':type,
					'filterSubject':title,
					'filterStartNewsTime':timeStart,
					'filterEndNewsTime':timeEnd
				},
				function(result){
					newsContentDiv.html(result);
					autoHeight();
				});			
		}); 
		</script>
	</head>
	<body>
		<div class="title_bar search_bar_title" style="font-weight:bolder;">新闻</div>
		<div class="pagebody_c_border" style="width:100%;">
			<s:form id="mainForm" action="desk-news.action" method="post">
				<s:hidden id="idPageNo" value="%{page.pageNo}"></s:hidden>
				<div class="search_bar search_bar_condition_all" >
					<div class="search_bar" style="float:left;height:100%;width:570px;overflow:hidden;">
						<div class="search_bar_condition_single">
							<span class="title">状态：</span><select id="filterNewsStatus" name="filterNewsStatus" onchange="$('#pageNo').val(''); document.getElementById('mainForm').submit();" style="width: 80px;font-size:12px;">
								<option value="all">全部新闻</option>
								<option value="new" <s:if test="%{filterNewsStatus == 'new'}">selected="selected"</s:if>>未读新闻</option>
							</select>&nbsp;
							<span style="height: 23px; line-height: 23px;"><s:text name="oaOaNews.typeCd" />：</span>
							<s:select list="mapNewsType" listKey="key" listValue="value" name="filterTypeCd" headerKey="" headerValue="全部类型" id="filterTypeCd" onchange="$('#pageNo').val(''); document.getElementById('mainForm').submit();" cssStyle="width: 80px;font-size:12px;" />
						</div>
						<div class="search_bar_condition_single">
							<span class="title"><s:text name="oaOaNews.newsTime" />：</span>
							<s:textfield id="filterStartNewsTime" name="filterStartNewsTime" onfocus="WdatePicker()" cssClass="Wdate" size="12" />
							<span class="title">至</span>
							<s:textfield id="filterEndNewsTime" name="filterEndNewsTime" onfocus="WdatePicker()" cssClass="Wdate" size="12" />
						</div>
						<div class="search_bar_condition_single">
							<span class="title">标题：</span><s:textfield name="filterSubject" id="filterSubject" />
						</div>				
					</div>
					<div class="search_bar" style="height:55px;padding:6px 0px;width:150px;float:left;">
						<input type="button" class="btn_blue_55_55" onclick="$('#pageNo').val(''); document.getElementById('mainForm').submit(); return false;" value="搜索" />
						<input type="button" class="btn_green_55_55" onclick="$('#mainForm input:text').val(''); return false;" value="清空" />
					</div>
				</div>
				<div id="newsContentDiv" style="float:left;width:100%;">
					<div class="loading">
						正在加载数据，请耐心等待。
					</div>
				</div>
			</s:form>
		</div>
	</body>
</html>


