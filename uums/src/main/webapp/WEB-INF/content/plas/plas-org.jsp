<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" import="com.hhz.uums.utils.DictContants;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>机构管理</title>
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
	
	<script type="text/javascript" src="${ctx}/js/common/treePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.quickSearch.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/xheditor/xheditor-src-zh-cn.js"></script>
</head>
<body class="easyui-layout">
	<div region="west" title='
		<span><a id="dispViewDesc" href="javascript:void(0)" onclick="reloadOrgTree()"></a></span>
		<span><input type=checkbox id="load_auto_flag" checked="checked" onchange="reloadOrgTree()"/>刷新</span>
		<span><a href="javascript:void(0)" onclick="viewDisableOrg()">失效机构</a></span>
	' split="true"  style="width:240px;">
		<%--机构树 --%>
		<div id="orgTreePanel" style="min-height:100px;">
		</div>
		<%--弃用机构 --%>
		<div id="disablePanel"></div>
	</div>
	<div region="center" split="true" style="padding:5px;+position: relative;overflow-x:hidden; ">
		<div class="toolbar">
			<div>
				<div style="float:left;" id="conditionPanel">
					请选择视图:<s:select onchange="reloadOrgTreeDime()" list="@com.powerlong.plas.utils.DictMapUtil@getMapDimeType()" id="selDimeType" listKey="key" listValue="value"/>
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="editDime();" id="btnEditDime">编辑维度</a>
				</div>
				<div style="float:left;">
					<a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="appendOrg();" id="btnAppendOrg" >新增机构</a>
				</div>
				
				<div style="float: left;margin-left:10px;">
					<security:authorize ifAnyGranted="A_ADMIN">
						含失效机构:<input type="checkbox" id="isAllFlg" name="isAllFlg" />
					</security:authorize>
					快捷搜索机构: 
					<input style="width:150px;" type="text" id="quickSearchField" name="quickSearchField" value="${quickSearchField}" title="模糊检索机构业务编号或机构名称(所有机构)"/>
					<input type="hidden" id="quickSearchFieldId" name="quickSearchFieldId" value="${quickSearchFieldId}"/>
					<%--
					<input type="button" onclick="cleanSearchField()" value="清空"></input>
					 --%>
				</div>
			</div>
		</div>
		<div id="rightContainer" style="min-height:400px;width:100%;clear:both;">
		
		</div>
		
		<div id="wConfigPos" title="配置系统职位" class="easyui-window" closed="true" style="width:350px;height:480px;padding:5px;">
			<div id="posTreePanel"  style="height:430px;overflow-y:auto;+position: relative;">
			</div>
		</div>
		
	</div>

	<%-- 操作管理员 --%>
	<security:authorize ifAnyGranted="A_ADMIN">
		<input type="hidden" class="processPreProcessFlg"/>
	</security:authorize>

<script type="text/javascript">
	
	function validateOperateRoot(){
		return ($('.processPreProcessFlg').length > 0);
	}

	$(function(){
		//载入机构树
		reloadOrgTree();
		//快速搜索(模糊匹配:orgBizCd,orgName,orgMgrId)
		$('#quickSearchField').quickSearch(
			'${ctx}/plas/plas-org!quickSearchOrgList.action',
			['orgBizCd','orgName','orgMgrId','activeFlgName','centerOrgName'],
			{plasOrgId:'quickSearchFieldId',orgName:'quickSearchField'},
			'',
			function(result){
				refreshOrgArea(result.attr('plasOrgId'));//plas-org.jsp
			},
			{dimeCd:'selDimeType',isAllFlag: 'isAllFlg'}
		);
		if(!validateOperateRoot()){
			$('#conditionPanel').hide();
			return ;
		}
	});
	//清空输入框
	function cleanSearchField(){
		$('#quickSearchField').val('');
		$('#quickSearchFieldId').val('');
	}
	
	function reloadOrgTreeDime(){
		//若不勾选,则自动勾选
		if(!$('#load_auto_flag').attr('checked')){
			$('#load_auto_flag').click();
		}
		reloadOrgTree();
	}
	
	//重载树
	function reloadOrgTree(){
		setCurrentDimeDesc();
 		processTree(getCurrentDimeCd());
	}
	//维度类型
	function getCurrentDimeCd(){
		return $('#selDimeType').val();
	}
	function setCurrentDimeDesc(){
		return $('#dispViewDesc').html($("#selDimeType option[value='"+getCurrentDimeCd()+"']").text());
	}
	
	//选中的树节点
	var gSelOrgNode;
	
	//机构树
	function processTree(dimeTypeCd){

		//若不勾选,不刷新
		if(!$('#load_auto_flag').attr('checked')){
			return;
		}
		
		$('#orgTreePanel').html('<div style="height:60px;width:100%;">&nbsp;</div>').mask('正在加载机构树..');
		$('#orgTreePanel').show().siblings().hide();
		var url = '${ctx}/plas/plas-org!loadOrgTree.action';
		$.post(url, {dimeTypeCd: getCurrentDimeCd()}, function(result) {
			$("#orgTreePanel").html('').unmask();
			if (result) {
				var tmpOrgTree = new TreePanel({
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
						if(!validateOperateRoot()){
							$('#btnAppendOrg').linkbutton('disable');
						}
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
						refreshOrgArea(node.attributes.entityId);
					}
				});
			}
		});
	}
	//查看机构明细(刷新)
	function refreshOrgArea(orgId){
		$('#rightContainer').html('<div style="height:60px;">&nbsp;</div>').mask('正在加载机构明细..');
		var url = "${ctx}/plas/plas-org!input.action";
		var data = {id: orgId,dimeTypeCd: getCurrentDimeCd()};
		$.post(url, data, function(result) {
			$("#rightContainer").html(result);
			$.parser.parse('#rightContainer');
			$('#orgPanel').css({"width":"100%"}).prev().css({"width":"100%"}).parent().css({"width":"100%"});
			if(orgId && orgId != ''){
				$('#btnAppendOrg').linkbutton('enable');
				$('#btnEmployees').linkbutton('enable');
			}
			if(!validateOperateRoot()){
				//$('#btnDeleteOrg').hide();
				return ;
			}
		});
	}
		
	//新增机构
	function appendOrg(){
		var tmpId = '';
		if(gSelOrgNode){
			tmpId = gSelOrgNode.attributes.entityId;
		}
		
		$('#rightContainer').html('<div style="height:60px;">&nbsp;</div>').mask('正在新增机构..');
		var url = "${ctx}/plas/plas-org!appendOrg.action";
		var data = {appendParentOrgId: tmpId};
		$.post(url, data, function(result) {
			$("#rightContainer").html(result);
			$.parser.parse('#rightContainer');
			$('#btnAppendOrg').linkbutton('disable');
			$('#btnEmployees').linkbutton('disable');
		});
	}


//机构管理
function saveOrg(){
	 
	var orgBizCd = $('#orgBizCd').val();
	var oldOrgBizCd = $('#oldOrgBizCd').val();
	
	//很重要:设置当前维度
	$('#formDimeTypeId').val(getCurrentDimeCd());
	
	//检查唯一性
	var urlBizCd = '${ctx}/plas/plas-org!isOrgBizCdExists.action';
	var dataBizCd = {orgBizCd: orgBizCd, oldOrgBizCd: oldOrgBizCd};
	$.post(urlBizCd, dataBizCd, function(result){
		if('true' == result){
			var dimeCd = getCurrentDimeCd();
			var orgTypeCd = $('select[name="orgTypeCd"]').val();
			if(orgTypeCd == ''){
				$('select[name="orgTypeCd"]').focus();
				return false;
			}
			$('#orgForm').form('submit', {
				url:'${ctx}/plas/plas-org!save.action',
				onSubmit: function(){
					var flag = $('#orgForm').form('validate');
					if(flag){
						$('#rightContainer').mask('保存机构明细中...');
						return true;
					}else{
						return false;
					}
				},
				success:function(data){
					var rtn = data.split(',');
					if(rtn && rtn.length == 2 && rtn[0] == 'success'){
						refreshOrgArea(rtn[1]);
						reloadOrgTree();
						$.messager.alert('提示','保存成功!');
						$('#rightContainer').unmask();
					}else{
						$.messager.alert('提示',data)
					} 
				}
			});
		}else{
			alert('机构业务编号 已存在');
			$('#orgBizCd').focus();
			return;
		}
	});
}
//校验机构编号是否存在
function validateOrgExists(orgBizCd, oldOrgBizCd){
	var orgBizCd = $.trim(orgBizCd);
	var url = '${ctx}/plas/plas-org!isOrgBizCdExists.action';
	var data = {orgBizCd: orgBizCd, oldOrgBizCd: oldOrgBizCd};
	$.post(url, data, function(result){
		if('true' == result){
			$('#bizCdExist').hide();
		}else{
			$('#bizCdExist').show();
		}
	});
}
//删除机构
function orgDelete(orgId){
	
	var url = '${ctx}/plas/plas-org!validateDeleteOrg.action';
	$.post(url,{orgId: orgId},function(result){
		if('success' == result){
			if(!window.confirm('提示:删除机构,同时将解除当前机构与上下级机构的关系,您是否继续?')){
				return;
			}
			$('body').mask('正在删除...');
			var url = '${ctx}/plas/plas-org!delete.action';
			$.post(url,{orgId: orgId,dimeTypeCd: getCurrentDimeCd()},function(result){
				$('body').unmask();
			 	if('success' == result){
			 		reloadOrgTree();
			 		refreshOrgArea(orgId);
			 		$('#rightContainer').empty();
			 	}
			});
		}else{
			//不允许删除
			alert(result+" ,不允许删除!");
		}
	});
}

var gPopOrgId;
var gPopOrgCd;
var gPopOrgBizCd;
var gPopOrgName;
var gDimeId;
var gDimeCd;

function setPopSelNode(id,cd,bizCd,name){
	gPopOrgId = id;
	gPopOrgCd = cd;
	gPopOrgBizCd = bizCd;
	gPopOrgName = name;
} 
function cleanPopSelNode(){
	setPopSelNode('','','','');
}
//关闭机构
function closePopOrg(){
	cleanPopSelNode();
	$('#wParentOrg').window('close');
}

//弹出窗口
var tmpPopTree;
function showPopOrg(dimeCd,dimeId){
	gDimeCd = dimeCd;
	gDimeId = dimeId;
	var url = '${ctx}/plas/plas-org!loadOrgTree.action';
	$.post(url, {dimeTypeCd: dimeCd}, function(result) {
		if (result) {
			$("#orgTreePopDiv").empty();
			tmpPopTree = new TreePanel({
				renderTo: "orgTreePopDiv",
				'root'  : eval('('+result+')'),
				'ctx'	:'${ctx}'
			}); 
			tmpPopTree.render();
			tmpPopTree.on(function(node){
				var id = node.attributes.entityId;
				if( $.trim(id) == '' || $.trim(id)=='0'){

					if(!validateOperateRoot()){
						return;
					}
					//alert('根节点y');
				} else{
					if(node.isExpand){
						node.collapse();
					}else{
						node.expand();
					}
				}
				setPopSelNode(
					node.attributes.entityId,
					node.attributes.entityCd,
					node.attributes.entityBizCd,
					node.attributes.entityName
				);
			});
			$('#wParentOrg').window('open');
		}
	});
}
//保存上级机构ID
function savePopOrg(plasOrgId){
	
	var curOrgId = $("#plasOrgId").val();
	if( curOrgId == ''){
		return;
	}
	if(gPopOrgId == ''){
		return;
	}
	var orgId 	= gPopOrgId;
	var orgCd 	= gPopOrgCd
	var orgBizCd= gPopOrgBizCd
	var orgName = gPopOrgName
	var dimeId  = gDimeId;

	//允许选择根节点(0-全部机构)
//	if($.trim(orgId)=='' ||$.trim(orgId)=='0'){
	if($.trim(orgId)=='' ){
		return;
	}
	if(curOrgId == orgId){
		alert("对不起,不能选择自身作为上级机构!");
		return ;
	}

	//必须控制新的父节点不能使当前节点的子孙节点
	var newParentNode = tmpPopTree.getNodeById(orgId);
	if(!validateOperateRoot()){
		if(newParentNode.id == '0'){
			return;
		}
	}
	var path = newParentNode.getPath();
	var pathOrgCds = path.split("/");//与TreePanel.js的pathSeparator值一致.
	
	var tId ;
	for(var i=0; i < pathOrgCds.length;i++){
		tId = pathOrgCds[i];
		if( tId == curOrgId){
			alert("对不起,不能选择下级机构作为父机构!");
			return;
		}
	}

	var url = '${ctx}/plas/plas-org!saveDimeOrgRel.action';
	$.post(url, {orgId: curOrgId, relParentId: gPopOrgId, dimeId: gDimeId}, function(result) {
		if('success' == result){
			$('#rel_vo_parentOrgName_'+gDimeId).html(gPopOrgName);
			
			//修改当前维度的机构
			if(gDimeCd == getCurrentDimeCd()){
				reloadOrgTree();
			}
			closePopOrg();
			
			refreshOrgArea(curOrgId);
			//$.messager.alert('提示','保存成功!');
		}else{
			$.messager.alert('异常',result);
		}
	});
}
//解除关系
function removeOrgRel(dimeCd,dimeId,parentId,relId){
	if(!window.confirm('您要解除当前机构与上级机构关系?')){
		return;
	}
	var curOrgId = $("#plasOrgId").val();
	if( curOrgId == ''){
		return;
	}
	var url = '${ctx}/plas/plas-org!removeOrgRel.action';
	var data = {orgId: curOrgId, relParentId: parentId, dimeId: dimeId, relId: relId};
	$.post(url, data, function(result) {
		if('success' == result){
			refreshOrgArea(curOrgId);
			if(dimeCd == getCurrentDimeCd()){
				reloadOrgTree();
			}
			$.messager.alert('提示','删除关系成功!');
		}else{
			$.messager.alert('异常',result);
		}
	});
}
//统计下属员工数
function totalEmployees(orgId){
	$('#totalEmplyeeNumTip').html('<div style="width:190px;height:40px;">&nbsp;</div>').mask('正在统计,请稍后...');
	var url = '${ctx}/plas/plas-org!getDescendantUsers.action';
	var data = {orgId: orgId, dimeCd: getCurrentDimeCd()};
	$.post(url, data, function(result){
		$('#totalEmplyeeNum').html('共' +result+'位员工');
		$('#totalEmplyeeNumTip').unmask().empty().hide();
	});
}
//更新关系列表
function reloadRelList(orgId){
	var url = '${ctx}/plas/plas-org!relList.action';
	var data = {orgId: orgId};
	$.post(url, data, function(result){
		$('#org_rel_list').html(result);
	});
}
//编辑维度
function editDime(){
	$('body').mask('正在搜索...');
	$.post('${ctx}/plas/plas-org-dime.action',{},function(result){
		$('#rightContainer').html(result);
		$('body').unmask();
	});
}
<%-- 
//刷新维度层级
function refrshDataTreeLevel(){
	$.messager.confirm('确认','您确定刷新吗?',function(r){
		if (r){
			$.post('${ctx}/plas/plas-org-dime!refrshDataTreeLevel.action',{dimeCd: getCurrentDimeCd()},function(result){
				result = eval(result);
				if(result.success){
					$.messager.alert('提示',result.success,'info');
					$('body').unmask();
				}
				if(result.failure){
					$('body').unmask();
					alert(result.failure);
				}
			});
		}
	});
}
--%>

function viewDisableOrg(){
	var url = _ctx+'/plas/plas-org!disableOrgList.action';
	$.post(url,{}, function(result){
		$('#disablePanel').html(result).show().siblings().hide();
	});
}

//显示系统职位树
var gPosTree;
function showPositionTree(){
	$('#wConfigPos').window('open');
	$('#wConfigPos').mask('正在搜索系统职位...');
	$('#posTreePanel').html('');
	var url = '${ctx}/plas/plas-org!loadPositionTree.action';
	var data = {};
	$.post(url,data,function(result){
		$('#posTreePanel').html('');
		$('#wConfigPos').unmask();
		gPosTree = new TreePanel({
			renderTo : "posTreePanel",
			'root' : eval('(' + result + ')'),
			'ctx' : '${ctx}'
		});
		//自定义
		gPosTree.isDelegateIcon = true;
		gPosTree.delegateGetDelegateIcon = function(node) {
			return node.iconPath;
		};
		gPosTree.delegateOnMouseOverNode = function(node) {
			return '';
		};
		
		//修饰所有节点
		for ( var k in gPosTree.nodeHash) {
			var node = gPosTree.nodeHash[k];
			var nodeType = node.attributes.nodeType;
			if( nodeType == '3'){//pos
				var statusCd = node.attributes.entityStatusCd;//是否有效 true-有效 false/null-无效
				if(statusCd == '1'){
					node.iconPath = _ctx+"/images/webim/male_online.jpg";
				}else{
					node.iconPath = _ctx+"/images/webim/male_offline.jpg";
				}
			}
			else if( nodeType == "1" && node.isLeaf()){
				node.iconPath = _ctx + "/images/imgTree/page.gif";
			}
		}
		gPosTree.render(); 
		//单击事件
		gPosTree.on(function(node) {
			var nodeType = node.attributes.nodeType;
			if (nodeType == "1") {//机构
				if (node.isExpand) {
					node.collapse();
				} else {
					node.expand();
				}
			}
			//系统职位
			else if( nodeType == "3"){
				var text = node.attributes.text;
				var sysPosId = node.id;
				$("#orgMgrSysPosId").val(sysPosId);
				$("#orgMgrSysPosName").val(text);
				getOrgMrgByPosId(sysPosId);
				$('#wConfigPos').window('close');
			}
		});
 		//treePanel动态生成,只能在这里赋值height:430px;overflow-y:auto;+position: relative;
 		$('#posTreePanel').find('.TreePanel').css({"height":"430px", "overflow-y": "auto","overflow-x": "hidden", "+position":"relative"});
 		 
	});
}

function getOrgMrgByPosId(positionId){
	var url='${ctx}/plas/plas-org!getSysPosition.action';
	$.post(url, {id: positionId}, function(result) {
		if(result!=""){
			var strs = result.split(",");
			$("#orgMgrId").val(strs[0]);
			$("#plasUserId").val(strs[0]);
			$("#orgMgrName").val(strs[1]);
			$("#sysPosOrgName").html(strs[2]);
		}
	});
}

</script>
</body>
</html>