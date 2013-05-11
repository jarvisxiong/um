<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/cont/cont.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" />
	
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
</head>
<body>
	<div class="title_bar">
		<div class="fLeft banTitle">供方类型设置</div>
		<div class="fLeft" style="margin-left:10px;">
			自动刷新 <input type='checkbox' id="chkAutoRefresh" />
		</div>
			 
		<div class="fRight">
			<input type="button" name="add"  title="新增" class="btn_new btn_add_new"    onclick="showAdd();" value="新增" />			
			<input type="button" name="save" title="保存" class="btn_new btn_save_new"   onclick="doSave();"  value="保存" />
			<input type="button" name="del"  title="删除" class="btn_new btn_del_new"    onclick="doDelete();"value="删除" />
			<input type="button" name="exp"  title="导出" class="btn_new btn_export_new"  onclick="doExportExcel();" value="导出">
			<input type="button" title="全屏" class="btn_new btn_full_new"  onclick="window.open(location.href);" value="全屏"/>
			<input type="button" title="刷新" class="btn_new btn_refresh_new"  onclick="window.location.href= location.href;" value="刷新"/>
			<font color="red">${msg}</font>
		</div>
	</div>
	
	<form id="searchForm" action="${ctx}/sup/sup-item!exportExcel.action"  method="post" >
	</form>

	<table style="width:100%;margin:5px;">
		<tr>
			<td valign="top" style="width:200px">
				<div style="min-height:400px; border:1px solid #80AB73;">
					<div id="itemDiv"  style="height: 100%; width: 200px; overflow: auto;margin:5px;">
						<!-- 这里是树 -->
					</div> 
				</div>
			</td>
			<td valign="top">
				<div id="itemRight" style="margin:5px 10px;">
					<form id="itemForm" method="post">
						<table cellpadding="1" cellspacing="1">
							<tr style="text-align: right;">
								<td>选中项目名称：</td>
								<td>
									<s:textfield  id="itemName" name="supItemName" /> 
									<s:hidden id="id" name="id" /> 
								</td>
							</tr>
							<tr style="height:30px;">
								<td style="text-align: right;">上级项目：</td>
								<td>
									<input type="text" id="parentName" onclick="doParentItem();" readonly="readonly"/> 
									<s:hidden id="parentCd" name="parentItemCd" /> 
									<s:hidden id="itemType" name="supItemTypeCd"/>
								</td>
							</tr>
							<tr style="height:30px;">
								<td style="text-align: right;">序号：</td><td><s:textfield  id="orderNo" name="dispOrderNum" /></td>
							</tr>
							<%--
							<tr style="height:30px;"><td>身份编号：</td>
							 <td><s:textfield id="itemCd" name="supItemCd"/></td>
							</tr> 
							--%>
						</table>
					</form>
					
					<%--
					<table style="margin-left:140px;">
						<tbody>
						<tr>
						<td>&nbsp;</td>
						</tr>
						</tbody>
					</table>
					 --%>
				</div>
			</td>
		</tr>
	</table>
<script type="text/javascript">

$(function(){
	getItemTree("itemDiv");
})

function getItemTree(itemDiv){
	$.post("${ctx}/sup/sup-item!getItemTree.action", function(result){
		var tree = new TreePanel({
			renderTo:itemDiv,
			'root' : eval('('+result+')'),
			'ctx':'${ctx}'
		});
		tree.addListener("check",function(node){
			//getUsersByOrg(node);
			//if($("#id").val()==node.attributes.id)return;
		});
		tree.on(function(node){
			$("#id").val(node.attributes.trueId);
			$("#itemName").val(node.attributes.text);
			$("#parentCd").val(node.attributes.parentId);
			$("#parentName").val(node.attributes.parentName);
			$("#orderNo").val(node.attributes.orderNo);
			$("#itemType").val(node.attributes.finType);
			$("#itemCd").val(node.attributes.finItemCd);
			
			if(node.isExpand){
				node.collapse();
			}else{
				node.expand();
			} 
		});
		tree.render();
	});
}
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
	//if($("#itemCd").val()==""){
	//		alert("请输入编号");
	//		return;
	//	}
	if($("#itemName").val()==""){
		alert("请输入项目名称");
		return;
	}
	if($("#parentName").val()==""){
		alert("请选择上级项目");
		return;
	}
	$("#itemForm").attr("action","${ctx}/sup/sup-item!save.action");
	$("#itemForm").ajaxSubmit(function(result) {
		showAdd();
		
		if(getAutoRefreshFlg()){
			$("#itemDiv").text("");
			getItemTree("itemDiv");
		}
	});
}
function doParentItem(){
	var selectId ="";
	var selectName ="";
	var selectType ="";
	ymPrompt.confirmInfo({
		icoCls:"",
		title:"供应类别",
		message:"<div id='parentDiv'></div>",
		useSlide:true,
		winPos:"c",
		width:300,
		height:400,
		allowRightMenu:true,
		afterShow:function(){
			$.post("${ctx}/sup/sup-item!getItemTree.action", function(result){
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
		var data = {id:$("#id").val()};
		$.post("${ctx}/sup/sup-item!delete.action",data, function(result) {
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
function doExportExcel(){
	if(!window.confirm('确认导出?')){
		return;
	}
	$("#searchForm").attr("action", "${ctx}/sup/sup-item!exportExcel.action");
	$("#searchForm").submit(); 
	//$.post("${ctx}/sup/sup-item!exportExcel.action",{}, function(result) {
		
	//});
}
function getAutoRefreshFlg(){
	var t = $('#chkAutoRefresh').attr('checked');
	return t;
}


function refresh(){
	window.location.href = '${ctx}/sup/sup-item!input.action';
}
</script>
</body>
</html>