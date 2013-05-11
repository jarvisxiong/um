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
		<link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/table.js"></script>
		<script src="${ctx}/js/datePicker/WdatePicker.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctx}/js/desk/desk-oa.js"></script>
		<link  href="${ctx}/resources/css/oa/oa-public-affair.css"rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		$(document).ready(function(){
			var activityContentDiv = $('#activityContentDiv');
			var pageNo = $('#idPageNo').val();
			if(!pageNo){
				pageNo = 1 ;
			}
			var availHeight = $(document).height() - activityContentDiv.offset().top;
			// 减去表头和表尾部翻页部分高度再计算行数 ( avail - th - tfoot - css margin)
			var lineCount = parseInt((availHeight-26-30-5)/26); 

			// 搜索参数
			var status = $('#filterStatus').val();
			var timeStart = $('#filterStartTime').val();
			var timeEnd = $('#filterEndTime').val();
			var title = $('#filterSubject').val();
			
			$.post(
				'${ctx}/desk/employee-activity!query.action',
				{
					'pageSize':lineCount,
					'pageNo':pageNo,
					'filterStatus':status,
					'filterSubject':title,
					'filterStartTime':timeStart,
					'filterEndTime':timeEnd
				},
				function(result){
					activityContentDiv.html(result);
				});
			
		}); 
		</script>
	</head>
	
	<body>
		<div class="title_bar search_bar_title">员工天地</div>
		<div class="pagebody_c_border" style="width:100%;">
			<s:form id="mainForm" action="employee-activity.action" method="post">
				<s:hidden id="idPageNo" value="%{page.pageNo}"></s:hidden>
				<div class="search_bar search_bar_condition_all" >
					<div class="search_bar" style="float:left;margin-left:-1px;height:100%;width:450px;">
						<div class="search_bar_condition_single" style="margin-top:6px;">
							状态：<select id="filterStatus" style="width:80px;font-size:12px;" name="filterStatus" onchange="$('#pageNo').val(''); document.getElementById('mainForm').submit();" style="width: 80px;">
								<option value="all">全部活动</option>
								<option value="new" <s:if test="%{filterStatus == 'new'}">selected="selected"</s:if>>未读活动</option>
							</select>
						</div><div class="search_bar_condition_single" style="margin-top:6px;float:right;">
							<span class="title"><s:text name="oaOaNews.newsTime" />：</span>
							<s:textfield id="filterStartTime" name="filterStartTime" onfocus="WdatePicker()" cssClass="Wdate" size="12" />
							<span class="title">至</span>
							<s:textfield id="filterEndTime" name="filterEndTime" onfocus="WdatePicker()" cssClass="Wdate" size="12" />
						</div><div class="search_bar_condition_single" style="margin-buttom:6px;width:100%;">
							<span class="title">标题：</span><s:textfield name="filterSubject" id="filterSubject" cssStyle="width:90%;"/>
						</div>
					</div>
					<div class="search_bar" style="height:55px;padding:6px 20px;float:left;">
						<input type="button" class="btn_blue_55_55" onclick="$('#pageNo').val(''); document.getElementById('mainForm').submit(); return false;" value="搜索"/>
						&nbsp;
						<input type="button" class="btn_green_35_55" onclick="$('#mainForm input:text').val(''); return false; " value="清空"/>
					</div>
				</div>
				<div id="activityContentDiv" style="float:left;width:100%;">
					<div class="loading">
						正在加载数据，请耐心等待。
					</div>
				</div>
			</s:form>
		</div>
	</body>
</html>


