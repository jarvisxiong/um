<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script src="${ctx}/resources/js/common/TreePanel.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type=text/javascript src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
</head>
<body>
<div stype=" height:10px">
 <table width="100%">
   <tr class="tablehead2">
						<td align="left" height="10">
							<button name="add" title="新增"  onclick="javascript:showAdd();">新增</button>&nbsp;				
							<button name="save" title="保存" onclick="javascript:doSave();">保存</button>&nbsp;
							<button name="del" title="删除" onclick="javascript:doDelete();">删除</button>&nbsp;
						</td>
						<td align="center"><font color="red">${msg}</font>
						</td>
					</tr>
 </table>
</div>
<div style="float:left;height:100%;width:200px;margin-left:2px;">
<div id="itemDiv"></div>
</div>
<div id="itemRight" style="margin-left:140px;margin-right:10px;height:100%;padding: 0 3px;">
<form id="itemForm" method="post">
<table>
<tr><td>编号：&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield id="itemCd" name="finItemCd" /></td></tr>
<tr><td>项目名称：<s:textfield  id="itemName" name="finItemName" /> <s:hidden id="id" name="id" /> </td></tr>
<tr><td>上级项目：<input type="text" id="parentName" onclick="doParentItem();" readonly="readonly"/> <s:hidden id="parentCd" name="parentItemCd" /> 
<s:hidden id="itemType" name="finItemTypeCd"/>
</td></tr>
<tr><td>序号：&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield  id="orderNo" name="dispOrderNum" /></td></tr>
</table>
</form>
<table style="margin-left:140px;">
<tbody>
<tr>
<td>&nbsp;</td>
</tr>
<tr>
<td>
(注：不影响现金流量的项目  收入：1011；支出：2011；集团拨款净额（收入）：1004)
</td>
</tr>
</tbody>
</table>
</div>
<script type="text/javascript">
function getItemTree(itemDiv){
	$.post("${ctx}/fin/fin-item!getItemTree.action", function(result){
		var tree = new TreePanel({
			renderTo:itemDiv,
			'root' : eval('('+result+')'),
			'ctx':'${ctx}'
		});
		tree.on(function(node){
			$("#id").val(node.attributes.trueId);
			$("#itemName").val(node.attributes.text);
			$("#parentCd").val(node.attributes.parentId);
			$("#parentName").val(node.attributes.parentName);
			$("#orderNo").val(node.attributes.orderNo);
			$("#itemType").val(node.attributes.finType);
			$("#itemCd").val(node.attributes.finItemCd);
		});
		tree.addListener("check",function(node){
			//getUsersByOrg(node);
			//if($("#id").val()==node.attributes.id)return;
		});
		tree.render();
	});
}
getItemTree("itemDiv");
function showAdd(){
	$("#itemName").val("");
	$("#parentCd").val("");
	$("#parentName").val("");
	$("#orderNo").val("");
	$("#id").val("");
	$("#itemType").val("");
	$("#itemCd").val("");
}
function doSave(){
	if($("#itemCd").val()==""){
		alert("请输入编号");
		return;
	}
	if($("#itemName").val()==""){
		alert("请输入项目名称");
		return;
	}
	if($("#parentName").val()==""){
		alert("请选择上级项目");
		return;
	}
	$("#itemForm").attr("action","${ctx}/fin/fin-item!save.action");
	$("#itemForm").ajaxSubmit(function(result) {
		showAdd();
		$("#itemDiv").text("");
		getItemTree("itemDiv");
		
	});
}
function doParentItem(){
	var selectId ="";
	var selectName ="";
	var selectType ="";
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"现金流量项目",
		message:"<div id='parentDiv'></div>",
		useSlide:true,
		winPos:"c",
		width:300,
		height:400,
		allowRightMenu:true,
		afterShow:function(){
			$.post("${ctx}/fin/fin-item!getItemTree.action", function(result){
				var tree = new TreePanel({
					renderTo:"parentDiv",
					'root' : eval('('+result+')'),
					'ctx':'${ctx}'
				});
				tree.on(function(node){
					selectId =node.attributes.id;
					selectName =node.attributes.text;
					selectType =node.attributes.finType;
				});
				tree.render();
			});
	},
	handler:function(e){
		if("ok"==e){
			$("#parentCd").val(selectId);
			$("#parentName").val(selectName);
			$("#itemType").val(selectType);
		}
	}
	});
}
function doDelete(){
	if($("#id").val()==""){
		alert("请选择要删除的对象");
		return;
	}
	if (confirm("确定要删除此项目？")){
		$("#itemRight").addClass("waiting");
		var param = {id:$("#id").val()};
		$.post("${ctx}/fin/fin-item!delete.action",param, function(result) {
			showAdd();
			$("#itemDiv").text("");
			getItemTree("itemDiv");
		});
	}
	
}
</script>
</body>
</html>