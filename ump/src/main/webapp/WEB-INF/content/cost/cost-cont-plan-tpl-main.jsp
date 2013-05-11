<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/nocache.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/thickbox.css"  />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cont/cont.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.css"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	
	<style type="text/css">
		.add_panel th{
			text-align: right;
			line-height: 25px;
			padding-left: 5px;
			width:60px;
			padding:3px;
		}
		.add_panel td{
			text-align: left;
			line-height: 25px;
			padding-left: 5px;
			width:150px;
			padding:3px;
		}
	</style>
</head>
<body>

	
	<div class="title_bar">
		<div class="fLeft banTitle">	
			合约规划模版
		</div>
		<div class="fRight"> 
			<input type="button" class="btn_new btn_full_new" title="新建模版" value="新建" onclick="newBuild();"></input>
			<input type="button" class="btn_new btn_full_new" onclick="window.open(location.href)" value="全屏" />
			<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href" value="刷新" />
		</div>
	</div>
	
	<div id="TableDiv" class="container">
		<form class="well form-inline clearfix" action='${ctx}/cost/cost-cont-plan-tpl!list.action' method="post" id="searchForm">
			<input type="hidden" name="pageNo" id="jumpPageNo" />
			<table class="add_panel">
				<tr>
					<th ><label>模板名称</label></th>
					<td><input type="text" id="tplName" name="tplName" class="input-medium"/></td>
					<th><label>版本号</label></th>
					<td><input type="text" id="tplVersion" name="tplVersion"  class="input-medium"/></td>
					<%--注意：这里若是<button/>会出错。 --%>
					<td><input type="button" class="btn_new btn_query_new" onclick="doQuery()" value="搜索"/></td>
				</tr> 
			</table>
		</form> 
	
 		<%-- 搜索结果列表 --%>
	 	<div id="tplDataList" style="width:100%;">	  
	 		<%@include  file="cost-cont-plan-tpl-list.jsp" %>
		</div>
	</div>


<script type="text/javascript">
 
function doQuery(){
	jumpPage(1);
}

//翻页搜索
function jumpPage(pageNo) {
	if(!pageNo){
		pageNo = 1;
	}
	
	$("#jumpPageNo").val(pageNo);
	$('#tplDataList').mask('正在搜索...');
	$("#searchForm").ajaxSubmit(function(result) {
		$('#tplDataList').unmask();
		$("#tplDataList").html(result);
	});
}
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
function newBuild(){
	window.location.href="${ctx}/cost/cost-cont-plan-tpl-detail!buildTabs.action?newBuild=0";
}
//刷新
function refreshMain(){
	$('body').mask('刷新中...');//<div class="progress progress-striped active"> <div class="bar" style="width: 40%;"></div>  </div>
	window.location.href="${ctx}/cost/cost-cont-plan-tpl!main.action";
} 
//合约模板明细
function viewPlanTplDetail(id){
	//showPageLink('${ctx}/cost/cost-cont-plan-tpl-detail.action?id=' + id,'合约模板明细','合约模板明细');
	showPageLink('${ctx}/cost/cost-cont-plan-tpl-detail!buildTabs.action?id=' + id,'合约模板明细','合约模板明细');
}
//解决弹出窗口的链接问题,改造 parent.showAll()
function showPageLink(url, typeCd, typeName){
	if(parent && parent.showAll){
		//parent.showAll(url, type);
		parent.TabUtils.newTab(typeCd,typeName, url, true);
	}else{
		window.open(url);
	}
}
</script>
</body>
</html>