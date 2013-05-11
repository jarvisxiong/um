<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cont/cont.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.resizable.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
	
	<style type="text/css">
		#search_condtion{
			padding:5px 10px;
			font-weight: normal;
			background-color:#D7DAD9;
			width:100%;
			padding:5px;
		}
		#search_condtion th{
			padding:2px 5px;
			text-align: right;
			font-weight: normal;
		}
		.mainTable td{
			padding-left:5px;
		}
	</style>
</head>
<body>
	<div id="mailMianContainer" >
	<div class="title_bar">
		<div class="fLeft banTitle">
			战略材料设备采购订单
		</div>
		<div class="fRight">
			<input type="button" class="btn_new btn_senior_new" onclick="doQuery();" value="高级搜索" style="width:80px;"/>
			<input type="button" class="btn_new btn_refresh_new" onclick="window.location.href=location.href;" value="刷新" />
		</div>
	</div>
	<!-- mailInfo end -->
	<div id="maiMainBottom" style="width:100%; height:100%;">
		<table style="width:100%;">
		<tr>
		<td id="rightPanel" valign="top">
			<form action="${ctx}/cost/cost-strage-mate!list.action" method="post" id="searchForm">
		    	<div style="height: 100%;" id="search_condtion">
					<table cellpadding="0" cellspacing="0" border="0" style="line-height: 22px;">
					<tr>
						<%--
						<th>标题</th>
						<td><input type="text" id="title" name="title" style="width:120px"/></td>
						 --%>
						<th>材料设备名称</th>
						<td><input type="text" id="mateName" name="mateName" style="width:120px" /></td>
						<td style="padding-left:10px;">
							<input type="button" class="btn_new btn_query_new" onclick="doQueryStrageMate();" value="搜索" />
							<input type="button" class="btn_new btn_clean_new" onclick="doClear();" value="清空条件" style="width:70px;"/>
						</td>
					</tr>
					</table>
				</div>
				<div id="costStrageMateList" style="padding-left: 5px;">
				
				</div>
			</form>
	    </td>
	    </tr>
    </table>
	</div>
</div>

<script type="text/javascript">
$(function(){
	doQueryStrageMate();
});
//搜索材料设备
function doQueryStrageMate(){
	var title = $("#title").val();
	var mateName = $("#mateName").val();
	TB_showMaskLayer("正在搜索,请稍候...");
	$('#searchForm').ajaxSubmit(function(result){
		TB_removeMaskLayer();
		if (result) {
			$("#costStrageMateList").html(result);
		}
	});
}

function getDetail(id){
	var url="${ctx}/cost/cost-strage-mate!input.action?id="+id;
	if(parent.TabUtils==null)
		window.open(url);
	else
		window.parent.TabUtils.newTab("cost-strage-mate-input","采购信息",url,true);
}

function doQuery(){
	if($("#search_condtion").is(":hidden")){
		$("#search_condtion").show();
	}else{
		$('#search_condtion').hide();
	}
}
function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	$("#searchForm").ajaxSubmit(function(result) {
		$("#costStrageMateList").html(result);
	});
}
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
function doClear(){
	$("#title").val('');
	$("#mateName").val('');
}
function refresh(){
	location.href="${ctx}/cost/cost-strage-mate!main.action";
}

</script>
</body>
</html>
