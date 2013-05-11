<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>合同类型设置</title>
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
</head>
<body>
	<div class="title_bar">
		<div class="banTitle fLeft">
			合同类型设置
		</div>
		<div class="fRight">
			<font color="red">${msg}</font>
			<button name="add" title="新增" class="btn_new btn_add_new"  onclick="javascript:showAdd();">新增</button>				
			<button name="save" title="保存" class="btn_new btn_save_new" onclick="javascript:doSave();">保存</button>
			<security:authorize ifAnyGranted="A_CONTRACT_ADMIN">
			<button name="del" title="删除" class="btn_new btn_del_new" onclick="javascript:doDelete();">删除</button>
			</security:authorize>
			<input type="button" class="btn_new btn_full_new"  onclick="window.open(location.href);" title="全屏" value="全屏"/>
			<input type="button" class="btn_new btn_refresh_new"  onclick="window.location.href= location.href;"  title="刷新" value="刷新"/>
		</div>
	</div>
	<div style="width:200px; height:85%; border:1px solid #80AB73; position:absolute;">
		<div id="itemDiv"  style="height: 100%; width: 200px; overflow: auto;"></div>
	</div>
	
<div id="itemRight" style="margin-left:230px;margin-right:10px;padding: 0 3px;">
	<form id="itemForm" method="post">
	<table cellpadding="1" cellspacing="1">
		<!-- 
		tr style="height:30px;"><td>编号：</td>
		<td><s:textfield id="itemCd" name="supItemCd" onblur="checkCd();"/></td>
		</tr 
		-->
		<tr style="height:30px;"><td>项目名称：</td>
			<td><s:textfield  id="itemName" name="typeName" /> <s:hidden style="width:180px;" id="id" name="id" /> 
				<s:hidden id="itemType" name="typeCd" />
				<s:hidden id="project" name="projectCd" />
			</td>
		</tr>
		<tr style="height:30px;"><td>上级项目：</td>
		<td>
			<input type="text" id="parentName" onclick="doParentItem();" readonly="readonly"/> <s:hidden id="parentCd" name="typeParentCd" />
		</td>
		</tr>
		<tr>
			<td>序号：</td>
			<td><s:textfield  id="orderNo" name="dispOrderNum" /></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
	</form>
</div>
<div style="margin-left:230px;margin-right:10px;padding: 0 3px;">
	<security:authorize ifAnyGranted="A_CONTRACT_ADMIN">
	<form id="projectForm" method="post">
	<input type="hidden" id="ledgerType" name="ledgerType" value="${ledgerType}"/>
	<table>
		<tbody>
		<tr>
			<td>新增项目</td>
		</tr>
		<tr>
			<td>项目名称</td>
			<td>
				<input type="text" id="batchName" class="orgSelect" style="width:85%;" readonly="readonly" name="projectName"/>
				<input type="hidden" name="projectCd" value="${projectCd}"/>
			</td>
			<td>序号</td>
			<td>
				<input type="text" id="batchOrder" name="orderNo" />
				&nbsp;
			</td>
			<td>字段类型(1：地产；2：商业)</td>
			<td>
				<input type="text" name="codeType" value="1"/>
				&nbsp;
			</td>
			<td><input type="button" class="btn_new btn_save_new" onclick="batchSubmit();" value="提交"/></td>
		</tr>
		</tbody>
	</table>
	</form>
	</security:authorize>
</div>
<script type="text/javascript">
$(function(){
	$(".orgSelect").ouSelect({type:'org'});
});
function getItemTree(itemDiv){
	$.post("${ctx}/cont/cont-contract-type!getItemTree.action", function(result){
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
			$("#project").val(node.attributes.finType);
			$("#itemType").val(node.attributes.finItemCd);
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
	$("#project").val("");
	$("#parentName").val("");
	$("#orderNo").val("");
	$("#id").val("");
	$("#itemType").val("");
}
function doSave(){
	if($("#itemName").val()==""){
		alert("请输入项目名称");
		return;
	}
	if($("#parentName").val()==""){
		alert("请选择上级项目");
		return;
	}
	$("#itemForm").attr("action","${ctx}/cont/cont-contract-type!save.action");
	$("#itemForm").ajaxSubmit(function(result) {
		showAdd();
		$("#itemDiv").text("");
		getItemTree("itemDiv");
		
	});
}
function batchSubmit(){
	if($("#batchName").val()==""){
		alert("请输入项目名称");
		return false;
	}
	if($("#batchOrder").val()==""){
		alert("请输入序号");
		return false;
	}
	$("#projectForm").attr("action","${ctx}/cont/cont-project-code!save.action");
	$("#projectForm").ajaxSubmit(function(result) {
		showAdd();
		$("#batchName").val("");
		$("#batchOrder").val("");
		$("#itemDiv").text("");
		getItemTree("itemDiv");
		
	});
	/* var url = "${ctx}/cont/cont-project-code!save.action";
	$.post(url,
			{
		        batchName:	$("#batchName").val(),
				batchOrder: $("#batchOrder").val()
			},
			function(result) {
				showAdd();
				$("#batchName").val("");
				$("#batchOrder").val("");
				$("#itemDiv").text("");
				getItemTree("itemDiv");
			}
		); */
}
function doParentItem(){
	var selectId ="";
	var selectName ="";
	var selectType ="";
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"合同类型",
		message:"<div id='parentDiv'></div>",
		useSlide:true,
		winPos:"c",
		width:300,
		height:400,
		allowRightMenu:true,
		afterShow:function(){
			$.post("${ctx}/cont/cont-contract-type!getParentTree.action", function(result){
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
			if(selectId=="0"){
				alert("不能选择合同类型");
				return false;
			}
			$("#parentCd").val(selectId);
			$("#project").val(selectType);
			$("#parentName").val(selectName);
			//$("#itemType").val(selectType);
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
		$.post("${ctx}/cont/cont-contract-type!delete.action",param, function(result) {
			showAdd();
			$("#itemDiv").text("");
			getItemTree("itemDiv");
		});
	}
}
function checkCd(status){
	var itemCd =$("#itemCd").val();
	var id =$("#id").val();
	if(itemCd!=""){
		var param = {itemCd:$("#itemCd").val(),id:id};
		$.post("${ctx}/sup/sup-item!query.action",param, function(result) {
			if(result==1){
				alert("该编号已存在,请重新输入");
				$("#itemCd").focus();
				return;
			}
		});
	}
}
function updateAppatch(){
	self.location="${ctx}/app/app-attachment!updateAppatch.action";
}
</script>
</body>
</html>