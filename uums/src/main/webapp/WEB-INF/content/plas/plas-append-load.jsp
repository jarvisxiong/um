<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" import="com.hhz.uums.utils.DictContants;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>机构管理</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script><!--
	<script type="text/javascript" src="${ctx}/js/approve/plas-append.js"></script>
--></head>
<body class="easyui-layout" onload="showBaseInfo();">
<div region="west" split="true" title='
		<span id="user_tree_check">EAS菜单</span><span align="right"><a id="btn_toggle_view" href="${ctx}/plas/plas-append.action">返回树</a></span>
		'style="width:180px;padding1:1px;overflow:hidden;">
	<div class="easyui-accordion" fit="true" border="false">
		<div title="信息录入" style="overflow:auto;">
			<div>
			<p><a href="#" onclick="javascript:showBaseInfo();">
			<span class="icon icon-dict">&nbsp;</span>
			<span class="nav">基本信息</span>
			</a></p>
			<p><a href="#" onclick="javascript:showInfo(1);">
			<span class="icon icon-dict">&nbsp;</span>
			<span class="nav">任职信息</span>
			</a>
			<p><a href="#" onclick="javascript:showInfo(2);">
			<span class="icon icon-dict">&nbsp;</span>
			<span class="nav">教育情况</span>
			</a></p>
			<p><a href="#" onclick="javascript:showInfo(3);">
			<span class="icon icon-dict">&nbsp;</span>
			<span class="nav">工作情况</span>
			</a></p>
			<p><a href="#" onclick="javascript:showInfo(4);">
			<span class="icon icon-dict">&nbsp;</span>
			<span class="nav">社会关系</span>
			</a></p>
			</div>			
		</div>
	</div>
</div>

<div region="center" split="true" style="padding:5px;+position: relative;overflow-x:hidden;">
		<!--<div class="toolbar">
			<div>
				<div style="float:left;" id="conditionPanel">
					请选择视图:<s:select onchange="reloadOrgTreeDime()" list="@com.powerlong.plas.utils.DictMapUtil@getMapDimeType()" id="selDimeType" listKey="key" listValue="value"/>
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="editDime();" id="btnEditDime">编辑维度</a>
				</div>
				<div style="float:left;">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="appendOrg();" id="btnAppendOrg" >新增机构</a>
				</div>
				
				<div style="float: left;margin-left:10px;">
					<security:authorize ifAnyGranted="A_ADMIN">
						是否显示全部机构:<input type="checkbox" id="isAllFlg" name="isAllFlg" />
					</security:authorize>
					快捷搜索机构: 
					<input style="width:150px;" type="text" id="quickSearchField" name="quickSearchField" value="${quickSearchField}" title="模糊检索机构业务编号或机构名称(所有机构)"/>
					<input type="hidden" id="quickSearchFieldId" name="quickSearchFieldId" value="${quickSearchFieldId}"/>
					<%--
					<input type="button" onclick="cleanSearchField()" value="清空"></input>
					 --%>
				</div>
			</div>
		</div>
		-->
		<div id="rightContainer" style="min-height:400px;width:100%;clear:both;">
		
		</div>
	</div><!--

	<div id="eec">
	<div title="任职历史" id="baseInfo_2" style="display: none;">
	</div>
	<br/>
	<div title="借调信息" id="baseInfo_3" style="display: none;">
	</div>
	<br/>
	<div title="职等历史" id="baseInfo_4" style="display: none;">
	</div>
	<br/>
	<div title="员工状态变动历史" id="baseInfo_5" style="display: none;">
	</div>
-->

<script type="text/javascript">
function showBaseInfo(){
	$('#rightContainer').html('<div style="height:100px;width:100%;">&nbsp;</div>').mask('正在载入信息,请稍候...');
	var url = "/plas/plas/plas-append!input.action";
	$.post(url, {id: "1"},function(result) {
		//if(result){
			$("#rightContainer").html(result);
			//很重要：json加载的页面，需要经过下列方法渲染页面
			$.parser.parse('#rightContainer');	
		//}
	});	
}
function showInfo(index){
	$('#rightContainer').html('<div style="height:100%;width:100%;">&nbsp;</div>').mask('正在载入信息,请稍候...');
	var url = "/plas/plas/plas-append!list.action";
	$.post(url, {status: index},function(result) {
		//alert(result);
		//$("#rightContainer").html("");
		$("#rightContainer").html(result);
		//很重要：json加载的页面，需要经过下列方法渲染页面
		//$.parser.parse('#rightContainer');	
		//}
	});	
}
</script>
</body>
</html>