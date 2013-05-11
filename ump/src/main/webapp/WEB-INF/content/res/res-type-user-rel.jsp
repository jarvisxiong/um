<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" href="${ctx}/css/desk/res-common.css" type="text/css" />
	<link rel="stylesheet" href="${ctx}/css/resApprove.css" type="text/css" />
	<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" /> 
	<script language="javascript" src="${ctx}/js/jquery.js"></script>
	<script language="javascript" src="${ctx}/js/table.js"></script>
	<script src="${ctx}/js/common.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>	
	<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
	
	<title>网批搜索权限配置</title>
</head>

<body>
	<div style="margin-left: 10px;">
	<input type="button" value="保存" class="btn_blue" onclick="save();" />
	<input type="hidden" id="relTypeCd" value="${relTypeCd}" />
	</div>
	<table>
	<tr>
		<td valign="top" rowspan="1" width="240">
		<div style="width: 230px; float:left;margin-left: 10px;overflow: hidden;border:1px solid #ccc">
			<div class="divTree"  id ="user-tree-div"></div>
		</div>
		</td>
		
		<td align="left" valign="top">
		<table>
		<tr>
			<td>
			<div id="searchApproveFix" style="float:left;margin-right: 10px;border-style:solid;border-width:1px; border-color:#BFBFBF;">
				<table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
					<tr>
						<td align="left">
							<input  value="搜索表单..." 
									type="text" 
									style="padding:2px;border:0;font-size: 12px;color: #CCCCCC;width:100%;"
									onkeyup="searchTreeNode(this)"
									onblur="resetSearchApproveInput(this);"
									onclick="clearSearchApproveInput(this);"
							/>
						</td>
						<td style="width:56px;">
							<div id="inputSearchOperate" class="searchNextNoActive">&nbsp;</div>
						</td>
					</tr>
				</table>
			</div>
			</td>
		</tr>
		<tr>
			<td valign="top">
			<div style="float:left;margin-right: 10px; overflow: hidden;border:0px solid #ccc">
				<table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
					<tr>
						<td align="left">
							<div class="divTree"  id ="tree-div"></div>
						</td>
					</tr>
				</table>
			</div>
			</td>
		</tr>
		</table>
		</td>
	</tr>
	</table>

<script type="text/javascript"> 
	var curUiid='';
	var treePanel_user;
	var treePanel;
	function loadResTree(){
		$("#tree-div").html('<div><image src="${ctx}/images/loading.gif"/>加载数据...</div>');
		//加载页面功能树
		$.post("${ctx}/res/res-type-user-rel!buildResTree.action",
			{
			isChecked:0,
			relTypeCd:$('#relTypeCd').val(),
			uiid:curUiid
			},
				function(result) {
			$("#tree-div").empty();
			if (result) {
				var arr=eval('('+result+')');
				root=arr;
				treePanel = new TreePanel({
					renderTo:'tree-div',
					'root' : root,
					'ctx':'${ctx}'
				});
				
				treePanel.render();
				treePanel.on(function(node){
					if(node.attributes.nodeType == "module"){
						//模块
						if(node.isExpand){
							node.collapse();
						}else{
							node.expand();
						}
					}
				});
			}
		});
	}

	function loadUserTree(){

		$("#user-tree-div").html('<div><image src="${ctx}/images/loading.gif"/>加载数据...</div>');
		$.post("${ctx}/res/res-type-user-rel!buildUserTree.action", function(result) {
			$("#user-tree-div").empty();
			if (result) {
				var arr=eval('('+result+')');
				root=arr;
				treePanel_user = new TreePanel({
					renderTo:'user-tree-div',
					'root' : root,
					'ctx':'${ctx}'
				});
				treePanel_user.render();
				treePanel_user.on(function(node){
					if(node.attributes.nodeType == "0"){
						//人员
						curUiid=node.attributes.extParam;
						loadResTree();
					}
					if(node.attributes.nodeType == "1"){
						//机构
						if(node.isExpand){
							node.collapse();
						}else{
							node.expand();
						}
					}
				});
			}
		});
		

	}
	$(function(){
		loadUserTree();
		loadResTree();
	});

	function save(){
		var chkIds=treePanel.getModified('entityCd','authType','entityId');
		var data={
				chkAuthTypeCds:chkIds[0],
				relIds:chkIds[2],
				relTypeCd:$('#relTypeCd').val(),
				uiid:curUiid
				};
		$.post("${ctx}/res/res-type-user-rel!save.action", data,function(result) {
			if (result=='success') {
				loadResTree();
			}
		});
	}

	
	//搜索定位表单
	var curVal = null;
	var curNode = null;

	var searchTreeManager;
	function searchTreeNode(dom){
		if(searchTreeManager)clearTimeout(searchTreeManager);
		searchTreeManager = setTimeout(function(){
			processSearch(dom);
		}, 300);
	}
	
	function processSearch(dom){
		if($(dom).val().trim() == ''){
			$('#inputSearchOperate').removeClass('searchNextActive').addClass('searchNextNoActive');
		}else{
			$('#inputSearchOperate').removeClass('searchNextNoActive').addClass('searchNextActive');
		}
		
		$(dom).css({color:"#5A5A5A"});
 		if($(dom).val().trim() == curVal){
			//NONE
		}else{
			curVal = $(dom).val().trim();
			curNode = null;
		}
 		curNode = treePanel.searchNode(curVal, curNode);
 		if(curNode){
			var nodes = curNode.getPathNodes();
			for(var i= 0; i < nodes.length; i++){
				nodes[i].expand();
			}
			treePanel.setFocusNode(curNode);
			var nodeDom = curNode['html-element']['text'];
			var toh = $('#tree-div').height();
			var top = $('#tree-div')[0].scrollTop;
			var noh = $(nodeDom).offset().top;
			$('#tree-div').animate({ scrollTop : top+noh-toh }, "normal");
		}else{
			$('#inputSearchOperate').removeClass('searchNextActive').addClass('searchNextNoActive');
		}
	}
	function resetSearchApproveInput(dom){
		if($(dom).val().trim() == ''){
			$(dom).val('搜索表单...');
			$(dom).css({color:"#E6E6E6"});
		}else{
			$(dom).css({color:"#5A5A5A"});
		}
	}
	function clearSearchApproveInput(dom){
		if( $(dom).val() == '搜索表单...'){
			$(dom).val('');
			$(dom).css({color:"#5A5A5A"});
		}
	}
</script>
</body>
</html>
