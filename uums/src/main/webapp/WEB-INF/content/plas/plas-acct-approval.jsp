<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java" import="com.hhz.uums.utils.DictContants;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>账号审核</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link rel="stylesheet" type="text/css" href="${ctx}/css/default.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/js/plugins/loadMask/jquery.loadmask.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/common/treePanel.css"/>
	
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-easyui/jquery.easyui.min.js" ></script>
	<script type="text/javascript" src="${ctx}/js/common/ConvertUtil.js"></script>
	<script type="text/javascript" src="${ctx}/js/plugins/loadMask/jquery.loadmask.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	
	<script type="text/javascript" src="${ctx}/js/common/treePanel.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery/jquery.quickSearch.js"></script>
</head>
<body class="easyui-layout">

	<div region="west" title='机构树' split="true"  style="width:200px;">
		<div id="leftPanel" style="height:100%;">
		<div id="searchApproveFix" style="border-bottom:1px solid #BFBFBF;">
			<table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
				<tr>
					<td>
						<input  value="搜索机构..." 
								type="text" 
								style="padding:2px;border:0;font-size: 12px;color: #CCCCCC;width:100%;"
								onkeyup="searchTreeNode(this)"
								onblur="resetSearchApproveInput(this);"
								onclick="clearSearchApproveInput(this);"
								id="inputSearchVal"
						/>
					</td>
					<td style="width:56px;">
						<div id="inputSearchOperate" class="searchNextNoActive" onclick="searchTreeNode(document.getElementById('inputSearchVal'))">&nbsp;</div>
					</td>
				</tr>
			</table>
		</div>
			<div id="leftTree"></div>
		</div>
	</div>
	<div region="center" split="true" style="+position: relative;overflow-x:hidden; padding:0 5px;">
		<div style="margin-top:5px;height:30px;">
			<form id="searchForm"  method="post">
					审核状态：
					<s:select list="@com.powerlong.plas.utils.DictMapUtil@getMapAcctAuditStatus()" id="statusCd" name="statusCd" listKey="key" listValue="value"/>
					
					<a href="#" id="btn" iconCls="icon-search" class="easyui-linkbutton" onclick="searchApprovalAcctList();">搜索</a>
					<span>说明:检查未经审核、未配置系统职位的账号</span>
		</form>
		</div>
		<div id="acctDetailPanel" style="height:450px;">
			<table id="tt" fit="true"	title="待审核账号列表" singleSelect="true" rownumbers="true"  ></table>
		</div>
	</div>
	
	<div id="wConfigPos" title="配置职位" class="easyui-window" closed="true" style="width:350px;height:500px;padding:5px;">
		<div style="width: 100%;text-align: left; line-height: 30px; ">
			<input type="button" style="cursor:pointer" class="buttom" onclick="savePopPos()" value="保存" />
			<input type="button" style="cursor:pointer" class="buttom" onclick="closePopPos()" value="取消" />
		</div>
		<div id="posTreePanel"  style="height:420px;overflow-y:auto;+position: relative;">
		</div>
	</div>
	
	<div id="wConfigPos" title="配置职位" class="easyui-window" closed="true" style="width:350px;height:500px;padding:5px;">
		<div style="width: 100%;text-align: left; line-height: 30px; ">
			<input type="button" style="cursor:pointer" class="buttom" onclick="savePopPos()" value="保存" />
			<input type="button" style="cursor:pointer" class="buttom" onclick="closePopPos()" value="取消" />
		</div>
		<div id="posTreePanel"  style="height:420px;overflow-y:auto;+position: relative;">
		</div>
	</div>
	<div id="wConfigPosConfirm" title="确认" class="easyui-window" closed="true" style="width:350px;height:500px;padding:5px;">
		<div style="width: 100%;text-align: left; line-height: 30px; ">
			<input type="button" style="cursor:pointer" class="buttom" onclick="savePopPosConfirm()" value="保存" />
			<input type="button" style="cursor:pointer" class="buttom" onclick="closePopPosConfirm()" value="取消" />
		</div>
		<div id="posRangePanelDiv"  style="height:420px;overflow-y:auto;+position: relative;">
		</div>
	</div>
<script type="text/javascript">

	function validateOperateRoot(){
		return ($('#processPreProcessFlg').length > 1);
	}
	
	
	$(function() {

		loadOrgTree();
		loadApprovalAcctList();
	});
	
	
	//载入树
	var mytree;
	var gOrgId;
	var gOrgIds={};
	function loadOrgTree() {
		$('#leftTree').html('<div style="height:100px;width:100%;">&nbsp;</div>');
		$("#leftTree").mask('正在载入机构树,请稍候...');
		var url =  '${ctx}/plas/plas-acct!loadOrgWaiting.action';
		
		$.post(url, function(result) {
			$("#leftTree").unmask();
			$('#leftTree').html('');
			if (result) {
				mytree = new TreePanel({
					renderTo : "leftTree",
					'root' : eval('(' + result + ')'),
					'ctx' : '${ctx}'
				});
				//自定义
				mytree.isDelegateIcon = true;
				mytree.delegateGetDelegateIcon = function(node) {
					return node.iconPath;
				};
				mytree.delegateOnMouseOverNode = function(node) {
					return '';
				};
				
				//修饰所有节点
				for(var k in mytree.nodeHash){
					var node = mytree.nodeHash[k];
					var nodeType = node.attributes.orgOrUser;
					if( nodeType == "1"){
						if(node.isLeaf()){
							node.iconPath = _ctx + "/images/imgTree/page.gif";
						}else{
							node.iconPath = _ctx + "/images/imgTree/folder.gif"; 
						}
					}
				}
				mytree.render();
				//单击事件
				mytree.on(function(node) {
					var id = node.id;
					var orgIds = node.getAllChildren(node,'entityId');
					var parentId = node.parentNode.id;
					if ($.trim(id) == '' || $.trim(id) == '0') {
						return;
					}

					var nodeType = node.attributes.nodeType;
					if (nodeType == "1") {//机构
						if (node.isExpand) {
							node.collapse();
						} else {
							node.expand();
						}
						//根据机构加载该部门所有待审核账号
						reloadApprovalAcctList(id,orgIds);
					}
				});
			}
		});
	}
	
	
	//加载职位树
	var mytree;
	var gAcctId;
	function showPositionTree(acctId){
		if(!acctId){
			return;
		}
		gAcctId = acctId;
		$('#wConfigPos').window('open');
		$('#wConfigPos').mask('正在搜索职位...');
		$('#posTreePanel').html('');
		var url = '${ctx}/plas/plas-acct!loadPositionTree.action';
		var data = {acctId: acctId};
		$.post(url,data,function(result){
			$('#posTreePanel').html('');
			$('#wConfigPos').unmask();
			mytree = new TreePanel({
				renderTo : "posTreePanel",
				'root' : eval('(' + result + ')'),
				'ctx' : '${ctx}'
			});
			//自定义
			mytree.isDelegateIcon = true;
			mytree.delegateGetDelegateIcon = function(node) {
				return node.iconPath;
			};
			mytree.delegateOnMouseOverNode = function(node) {
				return '';
			};
			
			//修饰所有节点
			for ( var k in mytree.nodeHash) {
				var node = mytree.nodeHash[k];
				var nodeType = node.attributes.nodeType;
				if( nodeType == '0'){
					node.iconPath = _ctx+"/webim/male_online.jpg";
				}
				else if( nodeType == "1" && node.isLeaf()){
					node.iconPath = _ctx + "/images/imgTree/page.gif";
				}
			}
			mytree.render(); 
			//单击事件
			mytree.on(function(node) {
				var nodeType = node.attributes.nodeType;
				if (nodeType == "1") {//机构
					if (node.isExpand) {
						node.collapse();
					} else {
						node.expand();
					}
				} 
			});
	 		//treePanel动态生成,只能在这里赋值height:400px;overflow-y:auto;+position: relative;
	 		$('#posTreePanel').find('.TreePanel').css({"height":"400px", "overflow-y": "auto", "+position":"relative"});
	 		 
		});
	} 
	function savePopPos(){
		var addDels = mytree.getAddDeleteIds('3');//职位
	 	var addIds = addDels[0];
	 	var delIds = addDels[1];
	 	if((addIds == '') && (delIds == '') ){
	 		alert("未发生变化!");
	 		return;
	 	}
	 	$("#posRangePanelDiv").html(mytree.getPopDiv('3','授权职位','收回职位'));
		$('#wConfigPos').window('close');
	 	$("#wConfigPosConfirm").window('open');
	}
	function closePopPos(){
		$('#wConfigPos').window('close');
	}

	//确认保存
	function savePopPosConfirm(){
		var acctId = gAcctId;//
		var addDels = mytree.getAddDeleteIds('3');//职位
	 	var addIds = addDels[0];
	 	var delIds = addDels[1];
	 	if((addIds == '') && (delIds == '') ){
	 		alert("未发生变化!");
	 		return;
	 	}
	 	
		var url='${ctx}/plas/plas-acct!saveAcctPosRel.action';
		var data = {acctId : acctId, addIds: addIds,delIds: delIds};
		$.post(url, data, function(result) {
			if(result == 'success'){
				$('#wConfigPos').window('close');
				$('#wConfigPosConfirm').window('close');
				 $('#tt').datagrid('reload');
			}else{
				$.messager.alert('提示','授权系统职位失败!'+result);
			}
		});
	}
	function closePopPosConfirm(){
		$('#wConfigPos').window('open');
		$('#wConfigPosConfirm').window('close');
	}
//搜索未审核账号
function loadApprovalAcctList(orgId,orgIds){
	var url;
	if(orgId){
		gOrgId = orgId;
		url = '${ctx}/plas/plas-acct!loadApprovalAcctList.action?orgId='+orgId;
	}else {
		url = '${ctx}/plas/plas-acct!loadApprovalAcctList.action';
	}
	if(orgIds){
		gOrgIds= orgIds;
	};
	$('#tt').datagrid({
		pagination:true,
		pageSize:10,
		pageList:[50,40,30,20,10],
		url:url,
		columns:[[
			{field:'userName',title:'姓名',sortable:true, width:100 },
			//职位显示如:jiaoxf||决策层
			{field:'uiid',title:'登录账号',sortable: true, width:100 },
			//职位显示如:副总裁||决策层
			{field:'sysPosName',title:'申请系统职位',sortable: true, width:150 },
		//	{field:'orgName',title:'部门',sortable: true, width:100 },
			{field:'creator',title:'申请人',sortable: true, align: 'center', width:120 },
			{field:'createDate',title:'操作日期',sortable: true, align: 'center', width:200 },
			{field:'action',title:'操作',width:180,sortable: true, align:'center',
				formatter:function(value,row,index){
					var applyStatusCd = row.applyStatusCd;
					var plasAcctId = row.acctId;
					var d = '';
					var tip='';
					var tmp ='<a href="#" class="easyui-linkbuton l-btn l-btn-disabled" ><span class="l-btn-left" style="color:#444444">';
					var tmpEnd='</span>';	
					switch(applyStatusCd){
					case '<%=DictContants.PLAS_ACCT_AUDIT_NO%>':
						{
							d+= '<a href="#" class="easyui-linkbuton l-btn" onclick=approvalPass("'+plasAcctId+'")><span class="l-btn-left">通过</span>';
							d+='<a href="#" class="easyui-linkbuton l-btn" onclick=approvalReject("'+plasAcctId+'")><span class="l-btn-left">驳回</span>';
							break;
						}
						case '<%=DictContants.PLAS_ACCT_AUDIT_YES%>':
						{
							d= tmp+'已通过'+tmpEnd;
							//d+='<a href="#" style="margin-left:5px;" class="easyui-linkbuton l-btn" onclick=showPositionTree("'+plasAcctId+'") ><span class="l-btn-left">分配职位</span>';
							break;
						}
						case '<%=DictContants.PLAS_ACCT_AUDIT_REJECT%>':
							d=  tmp+'已驳回'+tmpEnd;break;
						default:{
							d=  tmp+'已通过'+tmpEnd;break;
						}
					}
					d+="</a>"
					return d;
				}
			}
		]]
	});
}
function searchApprovalAcctList(){
	var statusCd = $('#statusCd').val();
	reloadApprovalAcctList('','',statusCd);
}
 //重载待审核账号列表
 function reloadApprovalAcctList(orgId,orgIds,statusCd){
	if(orgId){
		gOrgId = orgId;
	}
	if(orgIds){
		gOrgIds= orgIds;
	};
	 var obj = {orgIds:gOrgIds.join(','),statusCd:statusCd};
	 $('#tt').datagrid('options').queryParams = obj;
	 $('#tt').datagrid('reload');
	 
 }
 //账号审核通过
 function approvalPass(acctId){
	 acctOperate('pass',acctId);
 }
 //账号审核驳回
 function approvalReject(acctId){
	 acctOperate('reject',acctId);
 }
 function acctOperate(type,acctId){
	 var url = '';
	 switch(type){
	 	case 'pass':url='${ctx}/plas/plas-acct!approvalPass.action';break;
	 	case 'reject':url='${ctx}/plas/plas-acct!approvalReject.action';break;
	 }
	 $.post(url,{id:acctId},function(result){
		 $('#tt').datagrid('reload');
	 });
 }
 
 
//搜索定位
 var curVal = null;
 var curNode = null;

 var searchTreeManager;
 function searchTreeNode(dom){
 	if(searchTreeManager)clearTimeout(searchTreeManager);
 	searchTreeManager = setTimeout(function(){
 		processSearch(dom);
 	}, 300);
 }
 function searchTreeNodeById(value){
 	curNode = mytree.searchNodeById(value, curNode);
 	if(curNode){
 		var nodes = curNode.getPathNodes();
 		for(var i= 0; i < nodes.length; i++){
 			nodes[i].expand();
 		}
 		mytree.setFocusNode(curNode);
 		var nodeDom = curNode['html-element']['text'];
 		var noh = $(nodeDom).offset().top;
 	}
 }
 function processSearch(dom){
 	processSearch(dom,mytree);
 }
 function processSearch(dom,mytree1){
 	
 	if($(dom).val().trim() == ''){
 		$('#inputSearchOperate').removeClass('searchNextActive').addClass('searchNextNoActive');
 	}else{
 		$('#inputSearchOperate').removeClass('searchNextNoActive').addClass('searchNextActive');
 	}
 	
 	$(dom).css({color:"red"});
 		if($(dom).val().trim() == curVal){
 		//NONE
 	}else{
 		curVal = $(dom).val().trim();
 		curNode = null;
 	}
 		curNode = mytree.searchNode(curVal, curNode);
 	if(curNode){
 		var nodes = curNode.getPathNodes();
 		for(var i= 0; i < nodes.length; i++){
 			nodes[i].expand();
 		}
 		mytree.setFocusNode(curNode);
 		var nodeDom = curNode['html-element']['text'];
 		var toh = $('#divTreeP').height();
 		var top = $('#divTreeP')[0].scrollTop;
 		var noh = $(nodeDom).offset().top;
 		$('#divTreeP').animate({ scrollTop : top+noh-toh }, "normal");
 	}else{
 		//$('#inputSearchOperate').removeClass('searchNextActive').addClass('searchNextNoActive');
 	}
 }
 function resetSearchApproveInput(dom){
 	if($(dom).val().trim() == ''){
 		$(dom).val('搜索机构...');
 		$(dom).css({color:"#cccccc"});
 	}else{
 		$(dom).css({color:"#5A5A5A"});
 	}
 }
 function clearSearchApproveInput(dom){
 	if( $(dom).val() == '搜索机构...'){
 		$(dom).val('');
 		$(dom).css({color:"#5A5A5A"});
 	}
 }
</script>
</body>
</html>