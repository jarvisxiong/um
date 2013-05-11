<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" import="com.hhz.uums.utils.DictContants;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>通讯录导出管理</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common/treePanel.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js" ></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js" language="UTF-8"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	
	<script type="text/javascript" src="${ctx}/js/common/treePanel2.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.quickSearch.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/xheditor/xheditor-src-zh-cn.js"></script>
</head>
<body class="easyui-layout">
	
	<div region="west" title='<div id="dispViewDesc">请选择导出组织</div>' split="true"  style="width:240px;">
		<div id="orgTreePanel" style="min-height:100px;">
		</div>
	</div>
	
	<div id="rightPanel" region="center" split="true" style="padding:5px;+position: relative;overflow-x:hidden; ">
		<div id="barExport" class="toolbar" style="padding:5px;">
			<form action="plas-address-book-export!exportAddressBook.action" method="post" id="exportForm">
			<div>
				<input type="hidden" id="selDimeType" value="1"/>
				<%--<textarea class="inputBorder contentTextArea" id="select_test1" rows="20" cols="70">
				</textarea>
				--%>
				<div>
					<input validate="required" class="inputBorder" type="hidden" id="fileName" name="fileName" value=""/>
					<input type="button" id="btnExport" name="export" value="通讯录导出"/>
					<input type="hidden" id="hiddenNodeType" value="1"/>
					<input type="hidden" id="orgids" name="orgids" value=""/>
				</div>
				<span><input type="checkbox" name="enableEmailFlg" value="1"/>含邮箱地址</span>
			</div>
			</form>	
		</div>
		<div id="rightContainer" style="min-height:400px;width:100%;clear:both;">
		
		</div>
	
	</div>
	
		
	

<script type="text/javascript">

	$(function(){
		//载入机构树
		reloadOrgTree();
	});
	
	//重载树
	function reloadOrgTree(){
		processTree(getCurrentDimeCd());
	}
	//维度类型
	function getCurrentDimeCd(){
		return $('#selDimeType').val();
	}
	//选中的树节点
	var gSelOrgNode;

	//整个树节点
	var tmpOrgTree;
	
	//机构树
	function processTree(dimeTypeCd){
		$('#orgTreePanel').html('<div style="height:60px;width:100%;">&nbsp;</div>').mask('正在加载机构树..');
		var url = '${ctx}/plas/plas-address-book-export!loadOrgTree.action';
		
		$.post(url, {dimeTypeCd: getCurrentDimeCd()}, function(result) {
			$("#orgTreePanel").html('').unmask();
			if (result) {
				tmpOrgTree = new TreePanel({
					renderTo: "orgTreePanel",
					'root'  : eval('('+result+')'),
					'ctx'	:'${ctx}'
				}); 
				//自定义
				tmpOrgTree.isDelegateIcon = true;
				tmpOrgTree.delegateGetDelegateIcon = function(node){
					return node.iconPath;
				};
				tmpOrgTree.delegateOnMouseOverNode = function(node){
					return '';
				};

				//修饰所有节点
				for(var k in tmpOrgTree.nodeHash){
					var node = tmpOrgTree.nodeHash[k];
					var nodeType = node.attributes.orgOrUser;
					//显示节点checkbox  
					node.checked = 'true';
					if( nodeType == "1"){
						if(node.isLeaf()){
							node.iconPath = _ctx + "/images/imgTree/page.gif";
						}else{
							node.iconPath = _ctx + "/images/imgTree/folder.gif"; 
						}
					}
				}
				tmpOrgTree.render(); 
				//单击事件
				tmpOrgTree.on(function(node){
					gSelOrgNode = node;
					
					if('0' == node.attributes.entityId){
						$('#rightContainer').html('');
						return;
					}
					var nodeType = node.attributes.nodeType;
					if( nodeType == '1'){
						if(node.isExpand){
							node.collapse();
						}else{
							node.expand();
						}
						//将节点nodeType赋值到隐藏域中
						$('#hiddenNodeType').val(node.attributes.nodeType);
					}
				});

			}
		});
	}

	//查看机构明细(刷新)
	function refreshOrgArea(nodeType){
		//true不包括父节点,false包括父节点;不包括宝龙集团
		//$('#select_test1').val(tmpOrgTree.getModifiedAllNodes(nodeType,'fasle')[1]);
		$('#orgids').val(tmpOrgTree.getModifiedAllNodes(nodeType,'fasle')[0]);
		//alert(tmpOrgTree.getModifiedAllNodes(nodeType,'fasle')[1]);
	}
	
	$(function(){
		var tmpId;
		if(gSelOrgNode){
			tmpId = gSelOrgNode.attributes.entityId;
		}
	});	

	//导出
	$('#btnExport').click(function(){
		var nodeType = $('#hiddenNodeType').val();
		
		var arrNodes = tmpOrgTree.getModifiedAllNodes(nodeType,'false');
		var selNodes = arrNodes[0];
		if( selNodes == null || selNodes == ''|| selNodes.length == 0){
			alert('请选择！');
			return;
		}
		//先赋值
		$('#orgids').val(selNodes);
		$('#rightPanel').mask('正在执行...');
		
		setTimeout(removeMaskLayer,5000);
		
		$('#exportForm').submit();
	});

	function removeMaskLayer() {	
		$('#rightPanel').unmask();
		return false;
	}
</script>
</body>
</html>