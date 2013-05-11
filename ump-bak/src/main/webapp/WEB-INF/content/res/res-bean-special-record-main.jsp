<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
	<head>
		<title>特别费台账</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
		<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/resBeanSpecial.css"/>
		
		<%@ include file="/common/global.jsp" %>
		<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
		<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/res/resBeanSpecial.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
		<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
		<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	</head>
<body>
<div>
	<div class="title_bar">
		<div class="fLeft banTitle">	
			特别费用台账
		</div>
		<div class="fRight"> 
			<input type="button" class="btn_new btn_full_new" onclick="window.open(location.href)" value="全屏" />
			<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新" />
		</div>
	</div>
	
	<!-- 头部区域 -->
	<div id="head_panel">
		<div class="act_div">
			<form action="${ctx}/res/res-bean-special-record!list.action" method="post" id="searchForm">
				<input type="hidden" id="pageNo" name="page.pageNo">
				<span class="cond_span">申请机构：<input title="模糊查找" id="srh_applyOrgName" name="filter_LIKES_applyOrgName" class="input" value="${applyOrgName}" /></span>
				<span class="cond_span">申请人：<input title="精确查找" id="srh_userName" name="filter_EQS_userName" class="input" value="${userName}" /></span>
				<span class="cond_span">
					是否列入考核：
					<select name="filter_EQS_checkFlg" id="srh_checkFlg" class="input">
						<option value=""></option>
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</span>
				<span class="cond_span">
					<%--
					<s:select id="srh_statusCd" cssClass="input" list="@com.hhz.ump.util.DictMapUtil@getMapRecordStatus()" listKey="key" listValue="value" name="statusCd" />
					 --%>
					 状态：
					<select name="filter_EQS_statusCd" id="srh_statusCd" class="input">
					    <option value=""></option>
					    <option value="1">同意付款</option>
					    <option value="2">已付款</option>
					    <%--
					    <option value="0">立项未同意付款</option>
					     --%>
					</select>
				</span>
				<input type="button" class="btn btn_blue cond_btn" value="搜索" onclick="jumpPage(1);" title="搜索"/>
			</form>
		</div>
	</div>
	
	<!-- 内容列表显示区域 -->
	<div id="content_panel">
		<%@ include file="res-bean-special-record-list.jsp" %>
	</div>
</div>

<script type="text/javascript">
//翻页查询
function jumpPage(pageNo) {
	if(typeof(pageNo) == undefined){
		pageNo = 1;
	}
	$("#pageNo").val(pageNo);

	TB_showMaskLayer("正在搜索...");
	$("#searchForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$("#content_panel").html(result);
	});
}
//跳转至第几页
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
//打开超链接
function showLink(id, type){
	var url = '${ctx}/res/res-approve-info.action?id=' + id;
	showPageLink(url, 'resApprove');//resApprove:网批
}
//解决弹出窗口的链接问题,改造 parent.showAll()
function showPageLink(url, type){
	if(parent && parent.showAll){
		parent.showAll(url, type);
	}else{
		window.open(url,type);
	}
}
//删除记录
function deleteId(dom,id){
	
	if(!window.confirm('确认删除?')){
		return;
	}
	var url = '${ctx}/res/res-bean-special-record!delete.action';
	$.post(url, {id: id}, function(result){
		if('success' == result){
			alert('删除成功!');
			$(dom).parent().parent().remove();
			reloadPage();
		}else{
			alert(result);
		}
	});
}

//数字型
function formatNumberic(num){
	if(num == ''|| typeof(num) ==undefined){
		return 0;
	}else{
		return Number($.trim(num));
	}
}
//刷新当前分页
function reloadPage(){
	var current = $("#pageNo").val();
	jumpPage(current);
}
</script>
</body>
</html>