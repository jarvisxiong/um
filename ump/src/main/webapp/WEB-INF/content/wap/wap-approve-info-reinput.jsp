<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>宝龙管理平台</title>
	<%@ include file="/common/global.jsp" %>
	<meta name="HandHeldFriendly" content="true">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=2.0" />
	<%-- wap网上审批样式 --%>
	<link rel="stylesheet" href="${ctx}/resources/css/wap/wap_approve_info.css" />
	<link rel="stylesheet" href="${ctx}/resources/css/wap/wap_approve_input.css" />

	<script src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script src="${ctx}/resources/js/wap/wap_approve_input.js"></script>
	<style>
	table.mainTable {
		width: 100%!important;
		border: 2px solid #cccccc;
	}
	</style>
</head>
<body>
	<div id="content" style="margin: 0;align:left;">
		<div id="div_logo">
		<span style="margin:auto 5px;font-size:16px;color: #333;">宝龙管理系统</span>
		<div class="btn_exit_home" style="float:right;" id="toExit" onclick="logout(this);">
		退出
		</div>
		<div style="clear: both;">
		</div>
		</div>
		<div id="div_main">
			<div id="div_channel">
				<div class="channel_title">
				手机审批
				</div>
		    </div>
		    <div style="padding: 5px; border-top: 1px solid #8C8F94;height: 1px;">
		    <div id="divContent" style="margin: 0;align:left;" >
		    </div>
			</div>
		</div>
	</div>
</body>
</html>
<script>
$(document).ready(function(){
	//加载详细页面
	init('${id}','${selectOpinion}','${page.pageNo}');
	openDetail();
	});
//重写返回按钮方法selectDealOpinion
function doReturn(){
	window.location.href=_ctx+'/wap/wap-approve-info.action?selectOpinion=${selectOpinion}&page.pageNo=${page.pageNo}&qsCondition=${qsCondition}';
}
function logout(doj){
	if(window.confirm("确认退出系统？")){
		$.post(_ctx+"/login!reduceUser.action", function(result) {
			// $("#divXialaMenu").html(result);;
		});
		window.location.href=_ctx+'/login!logout.action'; 
	}
}
</script>
