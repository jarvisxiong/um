<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-project.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css" />
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
<style>
.search_div {
	background-color: #E4E7EC;
    border-bottom: 1px solid #8C8F94;
    color: #363636;
    font-size: 12px;
    height: 30px;
    line-height: 30px;
    width: 100%;
}
.table_input {
	width: 95%;
	height: 30px;
	line-height: 30px;
}
.table_input td{
	word-wrap: break-word;
	padding: 0 5px ;
}
.table_input input[type="file"], input[type="text"]  {
	width: 100%;
	height: 20px;
}
.table_input select {
	width: 100%;
	height: 100%;
}
.table_input input[readonly="readonly"] {
	background-color: #dddbdc;
}
.table_input textarea {
	width: 100%;
	font-size:12px;
}
.table_input .error {
	color: red;
	border: 2px solid red;
}
.table_input .required{
	border-left:2px solid red;;
}
</style>

<title>项目楼层管理</title>
</head>

<body>
	<div class="title_bar" >
		<div style="font-weight:bold;padding-left:8px;padding-right:8px; font-size:14px;float:left;">项目楼层管理</div>
	</div>
	<div class="search_div">
		<form action="${ctx}/bis/bis-floor!list.action" method="post" id="searchForm">
		<input type="hidden" id="filter_pageNo" name="page.pageNo" value="1"/>
		<table class="tb_search">
			<col width="45"/>
			<col width="120"/>
			<col width="80"/>
			<col width="100"/>
			<col />
			<tr>
				<td style="padding-left: 8px;" align="right">项目：</td>
				<td>
				<input type="text" id="bisProjectName" value="${bisProjectName}" style="width:100%; cursor: pointer; font-size: 12px; color: #ff0000;" />
				<input type="hidden" id="bisProjectId" name="bisProjectId" value="${bisProjectId}" />
				</td>
				<td align="right">楼宇类型：</td>
				<td>
				<s:select id="filter_floorType" name="filter_floorType" cssStyle="width:100%;" list="@com.hhz.ump.util.DictMapUtil@getMapContBigType()" listKey="key" listValue="value"></s:select>
				</td>
				<td style="padding-left: 20px;">
					<input type="button" class="btn_blue" style="width: 65px;" onclick="submitSearch();" value="搜索" /> 
					<input type="button" class="btn_blue" style="width: 65px;" onclick="doAddEntity();" value="新增"/>
				</td>
			</tr>
		</table>
		</form>
	</div>

	<div style="height:100%; margin-bottom:10px; padding-top:10px; border-bottom:1px solid #DDDBDC; background-color: #F7F7F7; display:none;" id="inputPanel" >
	
	</div>
	
	<div id="listPanel" style="clear:both; padding:10px 8px 0px; text-align:center;">
		<!--<div style="color: #6BAD42; font-size: 14px; font-weight: bold; text-align: center; line-height: 300px;">暂无记录！</div>-->
	</div>
	
<script type="text/javascript">
$(function(){
	$('#bisProjectName').onSelect({muti:false});
	submitSearch();
});
//翻页
function jumpPage(pageNo) {
	$('#filter_pageNo').val(pageNo);
	//if (selectedOrderBy){
	//	data.selectedOrderBy=selectedOrderBy;
	//	data.selectedOrder=selectedOrder;
	//}
	submitSearch();
}
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}
/**
 * 搜索
 */
function submitSearch() {
	TB_showMaskLayer("正在搜索...");
	$("#searchForm").ajaxSubmit(function(result) {
		$("#listPanel").html(result); 
		TB_removeMaskLayer();
	});
}
//新增
function doAddEntity() {
	var url = "${ctx}/bis/bis-floor!input.action";
	$.post(url, {bisProjectId:$("#bisProjectId").val()}, function(result) {
		$("#inputPanel").show();
		$("#inputPanel").html(result);
	});
}
//修改
function doEditEntity(id) {
	var url = "${ctx}/bis/bis-floor!input.action";
	$.post(url, {id:id}, function(result) {
		$("#inputPanel").show();
		$("#inputPanel").html(result);
	});
}
//保存
function doSaveEntity() {
	var pdv = new Validate("processForm");
	if (!pdv.validate()) {
		$("#processForm :input,textarea").filter("[validate=required]").filter("[value='']").eq(0).focus();
		return false;
	}
	TB_showMaskLayer("正在保存...");
	$("#processForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		submitSearch();
		doCancel();
	});
}
//取消
function doCancel() {
	$("#inputPanel").hide();
}
//删除
function doDeleteEntity(id) {
	ymPrompt.confirmInfo({message:'确认删除？',title:'删除',width:200,height:150,handler:function (tp){
		if (tp=='ok'){
			$.post(_ctx+"/bis/bis-floor!delete.action",{id:id},function(result) {
				if(result!="success") {
					alert(result);
				} else {
					submitSearch();
				}
			});
		}
	}});
}

</script>
</body>
</html>
