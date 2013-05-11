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
								<s:text name="common.configModuleMenuRel" />
							</td> 
						</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<div id="menu_tree_content" style="overflow-x:hidden; overflow-y: auto; height: 450px;">
							<!-- 这里是ajax加载treepanel树 -->
							<div id="module_menu_panel"></div>
						</div>
					</td>
				</tr>			
			</table>
		</td>
		<td valign="top" id="menu_detail_area" ><!-- 这里是编辑的机构内容 -->
			<%@ include file="app-module-menu-rel-detail.jsp"%>
		</td>
	</tr>
</table>   


<script type="text/javascript"> 
	
	$("#module_menu_panel").html('<div><image src="${ctx}/images/loading.gif"/>加载机构树...</div>');
	//加载页面功能树
	var treePanel;
	$.post("${ctx}/app/app-module-menu-rel!loadModuleMenuData.action", function(result) {
		$("#module_menu_panel").html('');
		if (result) {
			treePanel = new TreePanel({
				renderTo: "module_menu_panel",
				'root'  : eval('('+result+')'),
				'ctx'	:'${ctx}'
			}); 
			treePanel.render();
			treePanel.on(function(node){
	
				//模块
				if(node.attributes.nodeType == "module"){
					var id = node.attributes.entityId;
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
					$("#menu_detail_area").html('<div><image src="${ctx}/images/loading.gif"/>搜索模块中...</div>');
					var url = "${ctx}/app/app-module!input.action?id="+id;
					$.post(url, function(result) {
						$("#menu_detail_area").html(result);
					});
				}
				//菜单
				if(node.attributes.nodeType == "menu"){ 
					var id = node.attributes.entityId;
					if( $.trim(id) == '' || $.trim(id)=='0'){
						return;
					}
					$("#menu_detail_area").html('<div><image src="${ctx}/images/loading.gif"/>搜索菜单中...</div>');
					var url = "${ctx}/app/app-module-menu-rel!input.action?id="+id;
					$.post(url, function(result) {
						$("#menu_detail_area").html(result);
					});
				}
			});
		}
	});
</script>
</body>
</html>
