<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>管理区域</title>
		<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/global.jsp" %>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/app/common.css" />
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/app/app.css" />
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	
	<!-- TreePanel机构树 -->
	<link   type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" />
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>

	<!-- jQuery类库/插件/扩展库/样式库 -->
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/js/formValidator/style/validator.css"></link>
	<script type="text/javascript" src="${ctx}/resources/js/formValidator/formValidator.js" charset="UTF-8" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/formValidator/formValidatorRegex.js" charset="UTF-8" ></script>
	
	<!-- 弹出框 -->
	<link rel="stylesheet" id='skin' type="text/css" href="${ctx}/resources/js/prompt/skin/qq/ymPrompt.css" />
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js" ></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
</head>

<body>

<table style="table-layout: fixed;width: 100%;margin:5px;" >
	<tr>
		<td style="width:200px;height: 470px;border: 1px solid #c5e3fc;" valign="top">
			<table style="margin: 5px;">
				<tr>
					<td style="font-weight: bolder;" valign="top">
						<table style="width:100%;">
						<tr>
							<td align="left" valign="middle">
								<s:text name="common.configFunction" />
							</td> 
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<div id="function_tree_content" style="overflow-x:hidden; overflow-y: auto; height: 450px;">
							<!-- 这里是ajax加载treepanel树 -->
							<div id="page_function_panel"></div>
						</div>
					</td>
				</tr>			
			</table>
		</td>
		<td valign="top" id="function_detail_area" ><!-- 这里是编辑的机构内容 -->
			<table  >
				<tr>
					<td align="left" valign="middle" style="font-weight: bolder;width:100px">功能定义表</td>
					<td align="right" valign="middle">
						<!-- 操作提示 -->
						<span id="operate_result_tip" style="display: inline;"></span>
						<input id="btn_funcAdd" type="button" class="buttom" value='新增功能' />
						
						<input id="btn_funcDelete" type="button" class="buttom" value='删除功能' onclick="functionDelete()"/>
						
					</td>
					
				</tr>
				<tr>
					<td colspan="2">
						<%@ include file="app-page-function-detail.jsp"%>
					</td>
				</tr>
			</table>

		</td>
	</tr>
</table>   

<script type="text/javascript"> 
$(function(){

	$("#btn_funcAdd").hide();
	$("#btn_funcDelete").hide();
	$("#page_function_panel").html('<div><image src="${ctx}/images/loading.gif"/>加载机构树...</div>');
	//加载页面功能树
	var treePanel;
	$.post("${ctx}/app/app-page-function!loadPageFunctionData.action", function(result) {
		$("#page_function_panel").html('');
		if (result) {
			treePanel = new TreePanel({
				renderTo: "page_function_panel",
				'root'  : eval('('+result+')'),
				'ctx'	:'${ctx}'
			}); 
			treePanel.render();
			treePanel.on(function(node){
				var id = node.attributes.entityId;
				if(node.isExpand){
					node.collapse();
				}else{
					node.expand();
				}

				//页面
				if(node.attributes.nodeType == "page"){
					$("#btn_funcAdd").show();
					loadFunc('',id);
				}else{
					$("#btn_funcAdd").hide();
					$("#btn_funcDelete").hide();
				}
				//功能
				if(node.attributes.nodeType == "function"){ 
					$("#btn_funcDelete").show();
					if( $.trim(id) == '' || $.trim(id)=='0'){
						return;
					}
					loadFunc(id);
				}
			});
		}
	});
});
function loadFunc(id,pageId){
	$("#function_detail_area").html('<div><image src="${ctx}/images/loading.gif"/>搜索功能中...</div>');
	var url = "${ctx}/app/app-page-function!input.action";
	var data = {id:id,appPageId:pageId};
	$.post(url,data, function(result) {
		$("#function_detail_area").html(result);
	});
}
</script>
</body>
</html>
