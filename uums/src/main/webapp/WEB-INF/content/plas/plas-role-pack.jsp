<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>打包管理</title>
	<%@ include file="/common/global.jsp" %>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css" >
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common/treePanel.css"/>

	<%--注意：jquery1.4以上，不适用eval构造json,直接(result)即可 --%>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery-lasted.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>

	<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/treePanel.js"></script>
	
</head>
<body class="easyui-layout">
<input id="plasRolePackId"/>
<%--顶部 --%>
<div class="easyui-panel" region="north" title="搜索" icon="icon-search" style="height:70px;padding-top:5px;overflow:hidden;" border="false">
	<form id="searchForm"  method="post">
		<table>
			<tr>
				<td>包名:</td>
				<td><input name="filter_LIKES_packName" type="text"></input></td>
				<td>是否可用:</td>
				<td><s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapEnableFlg()" name="filter_EQB_enableFlg" listKey="key" listValue="value"/></td>
				<td><a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="Convert.search('searchForm','tt');">搜索</a></td> 
			</tr>
		</table>
	</form>
</div>
<%-- 左边 --%>
<div class="easyui-panel" region="center" border="false">
	<table id="tt" fit="true"
			title="打包列表" singleSelect="true" rownumbers="true"
			idField="plasRolePackId" url="${ctx}/plas/plas-role-pack!list.action">
	</table>
</div>

<%-- 右边 --%>
<div class="easyui-panel" id="centerRegion" region="east" split="true" style="width:480px;">
	<div id="roleTreePanel" style="display:none;">
		<div class="panel-header" style="line-height: 22px;">
			<span id="packName"></span>角色树:
			<span id="successTip"></span>
			<a style="float:right;" href="#" id="btnSaveBatchRole" iconCls="icon-save" class="easyui-linkbutton" onclick="showPopConfirm('${plasSysPositionId}')" >保存配置</a>
		</div>
		<table align="left" style="width:100%;border:1px solid #99BBE8;">
			<tr>
				<td valign="top" style="width:100%;">
					<div style="width:100%;height:400px;">
						<div id="roleTree"></div>
					</div>
				</td>
				<td valign="middle" style="vertical-align:top;">
				</td>
			</tr>
		</table>
	</div>
</div>

<%-- 角色变化弹出窗 --%>
<div id="wSaveConfirm" title="角色变化如下:" class="easyui-window" closed="true" style="top:20px;width:350px;height:500px;padding:5px;background: #fafafa;">
		<div style="width: 100%;text-align: left;">
			<input type="button" style="cursor:pointer" class="buttom" onclick="savePopConfirm()" value="确定" />
			<input type="button" style="cursor:pointer" class="buttom" onclick="closePopConfirm()" value="取消" />
		</div>
		<div id="confirmDetailDiv" style="height:450px;overflow-y:auto;+position: relative;">
			
		</div>
</div>

<%-- 添加弹出窗 --%>
<div id="w" class="easyui-window" closed="true" style="width:650px;height:300px;padding:5px;background: #fafafa;">
</div>

<script type="text/javascript">
	var windowOption={
		title: '角色打包明细',
		modal: true,
		closed: true,
		collapsible : false,
		minimizable : false,
		cache: false,
		iconCls:"icon-save",
		onClose:function(){
			$('#tt').datagrid('reload');
		}
	};
	$(function(){
		$('#tt').datagrid({
			pagination:true,
			pageSize:50,
			pageList:[50,40,30,20,10],
			columns:[[
				{field:'packName',title:'包名',sortable:true, width:180,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'enableFlg',title:'是否可用',sortable: true, align: 'center', width:65,
					formatter:function(value,row,index){
						var enableFlg = value;
						if(enableFlg=='1'){
							return '是';
						}else{
							return '否';
						}
					}
				},
				{field:'createdDate',title:'创建时间',sortable: true, width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'updatedDate',title:'更新时间',sortable: true, width:80,
					formatter:function(value,row,index){
						return '<div style="overflow: hidden; white-space: nowrap; text-overflow:ellipsis;" title="'+value+'">'+value+'</div>';
					}
				},
				{field:'action',title:'操作',width:150,sortable: true, align:'center',
					formatter:function(value,row,index){
						var id =row.plasRolePackId;
						var e = '<a href="#" class="easyui-linkbutton" onclick=editrow("'+id+'") >编辑</a> ';
						var d = '<a href="#" class="easyui-linkbutton" onclick=deleterow('+index+',"'+id+'") >删除</a>';
						var q = '';
						if(row.enableFlg=='1'){
							q = '<a href="#" class="easyui-linkbutton" onclick=enablerow("0","'+id+'") >停用</a>';
						}else{
							q = '<a href="#" class="easyui-linkbutton" onclick=enablerow("1","'+id+'") >启用</a>';
						}
						//return e;
						return e+q;
						//return e+d+q;
					}
				}
			]],
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				handler:function(){
					editrow('');
				}
			},'-'],
			onLoadSuccess:function(){
				$('a.easyui-linkbutton').linkbutton();
			},
			//单击事件
			onClickRow:function(rowNum,record){
				var plasRolePackId = record.plasRolePackId;
				var packName = record.packName;
				loadRoleTree(plasRolePackId,packName);
			}
		});
		$('#w').window(windowOption);
	});

	//加载角色树
	var roleTree;
	var gRolePackId;
	function loadRoleTree(plasRolePackId,packName){
		if(plasRolePackId){
			gRolePackId = plasRolePackId;
		}
		$("#roleTree").html('<div style="height:60px;width:100%;">&nbsp;</div>').mask('正在载入角色树,请稍候...');
		var url = '${ctx}/plas/plas-role-pack!loadRoleTree.action';
		$.post(url,{id:plasRolePackId,isChecked:false}, function(result) {
			$("#packName").html(packName+"－");
			$("#roleTree").html('');
			if (result) {
				roleTree = new TreePanel({
					renderTo: "roleTree",
					'root'  : (result),
					'ctx'	:'${ctx}'
				}); 
				//自定义
				roleTree.isDelegateIcon = true;
				roleTree.delegateGetDelegateIcon = function(node){
					return node.iconPath;
				};
				roleTree.delegateOnMouseOverNode = function(node){
					return '';
				};
				//修饰所有节点
				for(var k in roleTree.nodeHash){
					var node = roleTree.nodeHash[k];
					var nodeType = node.attributes.nodeType;
					if( nodeType== "0"){
						node.iconPath = _ctx + "/images/webim/male_online.jpg"; 
					}
					else if( nodeType== "1" && node.isLeaf()){
						node.iconPath = _ctx + "/images/imgTree/page.gif";
					}
				}
				roleTree.render(); 
				//单击事件
				roleTree.on(function(node){
					var id  = node.id;
					var parentId = node.parentNode.id;
					if( $.trim(id) == '' || $.trim(id)=='0'){
						return;
					}
					var nodeType = node.attributes.nodeType;
					if( nodeType == "1"){//应用或角色组
						if(node.isExpand){
							node.collapse();
						}else{
							node.expand();
						}
					}
				});
			}
			//treePanel动态生成,只能在这里赋值height:400px;overflow-y:auto;+position: relative;
			$('#roleTree').find('.TreePanel').css({"height":"400px", "overflow-y": "auto", "+position":"relative"});
		 	$('#roleTreePanel').show();
		});
	}

	//保存配置
	function showPopConfirm(){
		var addDels = roleTree.getAddDeleteIds('0');//0-角色
	 	var addRoleIds = addDels[0];
	 	var delRoleIds = addDels[1];
	 	if((addRoleIds == '') && (delRoleIds == '') ){
	 		alert("未发生变化!");
	 		return;
	 	}
	 	$("#confirmDetailDiv").html(roleTree.getPopDiv('0','授权角色','收回角色'));
	 	$("#wSaveConfirm").window('open');
	 }
	 
	//保存变化内容
	function savePopConfirm(){
		var addDels = roleTree.getAddDeleteIds('0');//0-角色
		var addRoleIds = addDels[0];
		var delRoleIds = addDels[1];
		
		if((addRoleIds == '') && (delRoleIds == '') ){
			$.messager.alert('Info',"请选择增加或收回的角色!");
			return;
		}
		$("#roleTree").html('<span><image src="${ctx}/images/loading.gif"/>保存设置中...</span>');
		var url='${ctx}/plas/plas-role-pack!saveBatch.action';
		var data = {id : gRolePackId, addRoleIds: addRoleIds,delRoleIds:delRoleIds}
		$.post(url, data, function(result) {
			closePopConfirm();
			if(result == 'success'){
				loadRoleTree(gRolePackId);
				$('#successTip').html('配置角色成功!').show().fadeOut(10000);
			}else{
				$.messager.alert('提示','配置角色失败!');
			}
		});
	 }
	//关闭弹出窗口
	function closePopConfirm(){
	 	$('#wSaveConfirm').window('close');
	}
	 
	 // 删除列表数据
	function deleterow(index,id){
		$.messager.confirm('Confirm','确定要删除么?',function(r){
			if (r){
				$('#tt').datagrid('deleteRow', index);
				var nextSelect=index>0?index-1:0;
				$('#tt').datagrid('selectRow', nextSelect);
				if(id){
					$.post("${ctx}/plas/plas-role-pack!delete.action",{id:id} , function(result) {
						if(result='success'){
							$('#tt').datagrid('load');
						}else{
							$.messager.alert('提示','对不起,删除失败!');
						}
					});
				}else{
					$('#tt').datagrid('acceptChanges');
				}
			}
		});
	}
	// 编辑列表数据
	function editrow(id){
		var url="${ctx}/plas/plas-role-pack!input.action?id="+id;
		$('#w').window({href:url});
		$('#w').window("open");
	}

	//启用/停用
	function enablerow(status,id){
		$.post("${ctx}/plas/plas-role-pack!enableRolePack.action",{status:status,id:id} , function(result) {
			if(result!="success"){
				$.messager.alert('提示','操作失败!');
			}
			$("#tt").datagrid("reload");
		});
	}

</script>
</body>
</html>