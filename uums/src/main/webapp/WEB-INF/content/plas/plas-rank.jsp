<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>职级管理</title>
	<%@ include file="/common/global.jsp" %>

	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common/treePanel.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js" ></script>
	<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/treePanel.js"  ></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js" ></script>
</head>

<body>
<table style="width:100%;background-color: white;min-height: 800px;">
	<tr>
		<td valign="top" style="width:200px;">
			<div id="group_list_panel"></div>
		</td>
		<td valign="top">
			<table style="width:100%;display: none;" id="groupDetailPanel">
				<tr>
					<td valign="top">
						<div id="group_detail_panel"></div>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<div class="panel-header" style="line-height:22px;">
							<a id="btnSaveBatchGroupUsers" disabled="true" href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="showPopConfirm();">保存职级用户</a>
						</div>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<!-- 组内成员 -->
						<div id="org_user_panel" style="border:1px solid #99BBE8; width: 100%; overflow:auto" style="+position: relative">
						</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>

<div id="wSaveConfirm" title="员工变化如下" class="easyui-window" closed="true" style="top:20px;width:350px;height:500px;padding:5px;background: #fafafa;">
	<div style="width: 100%;text-align: left;">
		<input type="button" style="cursor:pointer" class="buttom" onclick="savePopConfirm()" value="确定" />
		<input type="button" style="cursor:pointer" class="buttom" onclick="closePopConfirm()" value="取消" />
	</div>
	<div id="confirmDetailDiv">
		
	</div>
</div>

<script language="javascript">
	
	var gGroupId;
	var gGroupName;
	
	$(function(){
		loadGroupPanel();

		$("#wSaveConfirm").window({
			onOpen:function(){
				$('body').mask();
			},
			onClose:function(){
				$('body').unmask();
			}
		});
	});
	
	function getGroupId(){
		return gGroupId;
	}
	function setGroupId(secGroupId, secGroupName) {
		gGroupId = secGroupId;
		gGroupName = secGroupName;
	}

	//加载组列表
	function loadGroupPanel() {
		$("body").mask('载入职级列表中,请稍后...');
		var url = "${ctx}/plas/plas-rank-group!list.action";
		$.post(url, function(result) {
			$("body").unmask();
			if (result) {
				$("#group_list_panel").html(result).show();
			}
		});
	}
	//查看机构
	function viewGroup(dictCd, dictName, remark, updatedDeptCd, updatedDate) {
		setGroupId(dictCd, dictName);
		var url = "${ctx}/plas/plas-rank-group!getGroupDetail.action?dictCd="+ dictCd;
		/*
		$.post(url, function(result) {
			if (result) {
				$("#group_detail_panel").html(result).show();
			}
		});
		*/
		loadOrgUserTree(dictCd)
	}
	//加载机构用户树
	var gOrgUserTree;
	function loadOrgUserTree(dictCd) {
		$('#groupDetailPanel').show();
		$('#btnSaveBatchGroupUsers').linkbutton('disable');
		$("#org_user_panel").html('<div style="height:60px;width:100%;">&nbsp;</div>').mask('正在载入职级与人员关系,请稍候...');
		$.post('${ctx}/plas/plas-rank-tree!getRankTreeNode.action', {dictCd: dictCd}, function(result) {
			$("#org_user_panel").html('');
			if (result) {
				gOrgUserTree = new TreePanel({
					renderTo: "org_user_panel",
					'root'  : eval('('+result+')'),
					'ctx'	:'${ctx}'
				}); 
				gOrgUserTree.render();  
				gOrgUserTree.on(function(node){
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
				});
				$('#btnSaveBatchGroupUsers').linkbutton('enable');
			}
		});
	}
	//弹出提示
	function showPopConfirm(){
	 	var addDels = getAddDeleteIds();
	 	var addRoleIds = addDels[0];
	 	var delRoleIds = addDels[1];
	 	if((addRoleIds == '') && (delRoleIds == '') ){
	 		alert("未发生变化!");
	 		return;
	 	}
	 	$("#confirmDetailDiv").html(getPopDiv());
	 	$("#wSaveConfirm").window('open');
	 }
	 //关闭
	 function closePopConfirm(){
	 	$('#wSaveConfirm').window('close');
	 } 
	 
	 function savePopConfirm(){
		
		var dictCd = getGroupId();
		var addDels = getAddDeleteIds();
		var addUserIds = addDels[0];
		var delUserIds = addDels[1];
		
		if ((addUserIds == '') && (delUserIds == '')) {
			$.messager.alert('提示', "未发生变化!");
			return;
		}

		$("body").mask('保存设置中...');
		var url = '${ctx}/plas/plas-rank-tree!saveBatchGroupUsers.action';
		var data = {
			dictCd : dictCd,
			addUserIds : addUserIds,
			delUserIds : delUserIds
		}
		$.post(url, data, function(result) {
			$("body").unmask();
			if ('success' == result) {
				closePopConfirm();
				loadOrgUserTree(dictCd);
				$.messager.alert('提示', '保存职级配置成功!');
			} else {
				$.messager.alert('提示', '保存职级配置失败!');
			}
		});
	 }

	 //返回html片段
	 function getPopDiv(){
	 	var modifyNodes = gOrgUserTree.getModifiedLeafNodes('0');//0-用户
	 	var addIds   = modifyNodes[0];
	 	var addTexts = modifyNodes[1];
	 	var delIds 	 = modifyNodes[2];
	 	var delTexts = modifyNodes[3];

	 	//新增部分
	 	var addDiv   = new Array();
	 		addDiv.push("<table style='width:80px;'>");
	 	for(var i=0; i<addIds.length; i++){
	 		addDiv.push("<tr><td valign='top' id='"+addIds[i]+"'>"+addTexts[i]+"<span class='x_close' style='float:right;'></span></td></tr>");
	 	}
	 		addDiv.push("</table>");

	 	//删除部分
	 	var delDiv  = new Array();
	 		delDiv.push("<table style='width:80px;'>");
	 	for(var i=0; i<delIds.length; i++){
	 		delDiv.push("<tr><td valign='top' id='"+delIds[i]+"'>"+delTexts[i]+"<span class='x_close' style='float:right;'></span></td></tr>");
	 	}
	 		delDiv.push("</table>");

	 	//弹出框
	 	var popDiv = new Array();
	 	popDiv.push("<table style='width:300px;' >");
	 	popDiv.push("	<tr><th style='text-align:center;'>本次授权人员如下</th><th style='text-align:center;'>收回人员如下</th></tr>" );
	 	popDiv.push("	<tr><td valign='top'><div>"+ addDiv.join("")+"</div></td><td><div>"+ delDiv.join("")+"</div></td></tr>");
	 	popDiv.push("</table>");

	 	return popDiv.join("");
	 }

	 //获取授权或收回的用户IDs
	 function getAddDeleteIds(){
	 	var modifyNodes = gOrgUserTree.getModifiedLeafNodes('0');//0-角色
	 	var addIds   = modifyNodes[0];
	 	var delIds 	 = modifyNodes[2];

	 	var strAddIds = "";
	 	var strDelIds = "";
	 	
	 	for(var i=0; i<addIds.length; i++){
	 		if(i>0){
	 			strAddIds +=',';
	 		}
	 		strAddIds += addIds[i];
	 	}

	 	for(var i=0; i<delIds.length; i++){
	 		if(i>0){
	 			strDelIds +=',';
	 		}
	 		strDelIds += delIds[i];
	 	}
	 	return [strAddIds,strDelIds];
	 }
</script>
</body>
</html> 