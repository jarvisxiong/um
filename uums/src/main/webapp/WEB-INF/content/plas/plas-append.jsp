<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>EAS用户管理</title>
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

	<div region="west" title='EAS用户树<span><a href="${ctx}/plas/plas-append!load.action?status=1">人事</a></span>' split="true"  style="width:240px;">
		<div id="leftPanel" style="height:100%;">
			<div id="leftTree"></div>
		</div>
	</div>
	<div region="center" split="true" style="+position: relative;overflow-x:hidden; padding:0 5px;">
		<!--<div style="padding:5px 0;">
			快捷搜索账号: 
			<input style="width:150px;" type="text" id="quickSearchField" name="quickSearchField" value="${quickSearchField}" title="支持账号或姓名或自定义名称搜索账号列表"/>
			<input type="hidden" id="quickSearchFieldId" name="quickSearchFieldId" value="${quickSearchFieldId}"/>
			<input type="button" onclick="reloadSearch()" value="搜索"></input>
			<%--
			<input type="button" onclick="cleanSearchField()" value="清空"></input>
			 --%>
		</div>
		--><div id="acctDetailPanel" >
		
		</div>
	</div>
	
	<div id="wConfigPos" title="配置职位" class="easyui-window" closed="true" style="width:350px;height:500px;padding:5px;">
		<div style="width: 100%;text-align: left; line-height: 30px; ">
			<input type="button" style="cursor:pointer" class="buttom" onclick="savePopPos()" value="保存" />
			<input type="button" style="cursor:pointer" class="buttom" onclick="closePopPos()" value="取消" />
			<input type="hidden" id="tmpPosIdPop" />
			
			<security:authorize ifAnyGranted="A_ADMIN">
			<span><input type=checkbox id="pos_all_flag" onchange="showPositionTreeByFlg()"/>全部</span>
			</security:authorize>
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
	
	<%-- 操作管理员 --%>
	<security:authorize ifAnyGranted="A_ADMIN">
	<input type="hidden" id="processPreProcessFlg" value="1"/>
	</security:authorize>

<script type="text/javascript">
function goHR(){
	alert();
	loaction.href="${ctx}/plas/plas-append!load.action?status=1";
}
	function validateOperateRoot(){
		return ($('#processPreProcessFlg').length > 1);
	}
	
	
	//显示树：1 显示正常账号 2 显示全部账号
	var CONST_ALL = '1';
	var CONST_ENABLE = '2';
	
	$(function() {
		//注册快速搜索(模糊匹配:uiid,userName)
		$('#quickSearchField').quickSearch(
			'${ctx}/plas/plas-acct!quickSearchAcctList.action',
			['uiid','userName','orgName','centerOrgName','statusCd'],
			{plasAcctId:'quickSearchFieldId',userName:'quickSearchField'},
			'',
			function(result){
				refreshAcctArea(result.attr('plasAcctId'));
			}
		);

		loadAcctTree(CONST_ENABLE);

		if(validateOperateRoot()){
			$('#btnChangeTree').toggle(
				function (){
					$(this).html('显示可用账号');
					loadAcctTree(CONST_ALL);
				},
				function (){
					$(this).html('显示全部账号');
					loadAcctTree(CONST_ENABLE);
				}
			); 
		}
	});
	
	var gbCurType = '';
	function getGbCurType(){
		return gbCurType;
	}
	function setGbCurType(curType){
		gbCurType = curType;
	}
	
	//重新加载树
	function reloadAcctTree(){
		loadAcctTree(getGbCurType());
	}
	
	//载入树
	function loadAcctTree(type) {
		$('#leftTree').html('<div style="height:100px;width:100%;">&nbsp;</div>');
		$("#leftTree").mask('正在载入机构账号树,请稍候...');
		var url = '';
		switch(type){
			case CONST_ALL: url = '${ctx}/plas/plas-acct!loadAcctTreeAll.action';break;
			case CONST_ENABLE: url = '${ctx}/plas/plas-acct!loadAcctTreeEnable.action';break;
			default: {
				alert('类型不对!');
				return;
			}
		}
		setGbCurType(type);
		
		$.post(url, function(result) {
			$("#leftTree").unmask();
			$('#leftTree').html('');
			if (result) {
				var mytree = new TreePanel({
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
				for ( var k in mytree.nodeHash) {
					var node = mytree.nodeHash[k];
					var tmpNodeType = node.attributes.nodeType;
					if( tmpNodeType== "0"){
						var sexCd = node.attributes.sexCd;
						//var statusCd = node.attributes.extParam.split(",")[1];
						var statusCd = node.attributes.entityStatusCd;
						switch(sexCd){
							case '1': switch(statusCd){
										case '1': node.iconPath = _ctx + "/images/webim/male_online.jpg"; break;
										case '2': node.iconPath = _ctx + "/images/webim/none_locked.gif"; break;
										case '3': node.iconPath = _ctx + "/images/webim/male_online.jpg"; break;
										case '4': node.iconPath = _ctx + "/images/webim/male_offline.jpg"; break;
										default : node.iconPath = _ctx + "/images/webim/male_online.jpg"; break;
									  }
							  		  break;
							case '2': switch(statusCd){
										case '1': node.iconPath = _ctx + "/images/webim/female_online.jpg"; break;
										case '2': node.iconPath = _ctx + "/images/webim/none_locked.gif"; break;
										case '3': node.iconPath = _ctx + "/images/webim/female_online.jpg"; break;
										case '4': node.iconPath = _ctx + "/images/webim/female_offline.jpg"; break;
										default : node.iconPath = _ctx + "/images/webim/female_online.jpg"; break;
									  } 
					  		  		  break; 
							default: switch(statusCd){
										case '1': node.iconPath = _ctx + "/images/webim/none_online.jpg"; break;
										case '2': node.iconPath = _ctx + "/images/webim/none_locked.gif"; break;
										case '3': node.iconPath = _ctx + "/images/webim/none_online.jpg"; break;
										case '4': node.iconPath = _ctx + "/images/webim/none_offline.jpg"; break;
										default : node.iconPath = _ctx + "/images/webim/none_online.jpg"; break;
									  }
					  		  		  break; 
						}
					}
					else if( tmpNodeType == "1" && node.isLeaf()){
						node.iconPath = _ctx + "/images/imgTree/page.gif";
					}
				}
				mytree.render();
				//单击事件
				mytree.on(function(node) {
					var id = node.id;
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
					}
					if (nodeType == "0") {//账号
						refreshAcctArea(id);
					}
				});
			}
		});
	}
	
	//刷新账号信息
	function refreshAcctArea(acctId) {
		var url = '${ctx}/plas/plas-append!load.action';
		/*$('#acctDetailPanel').html('<div style="height:60px;width:100%;">&nbsp;</div>').mask('正在载入账号信息,请稍候...');
		var url = '${ctx}/plas/plas-append!load.action';
		$.post(url, {id: acctId}, function(resul	t) {
			$("#acctDetailPanel").html(result);
			//很重要：json加载的页面，需要经过下列方法渲染页面
			$.parser.parse('#acctDetailPanel');
			//$('#tmpPosIdPop').val(acctId);
		});*/
		location.href=url;
	}
	//private
	function validateOpen(sysTypeCd, uiid){
		var url = '${ctx}/plas/plas-acct!validateOpen.action';
		var data = {sysTypeCd: sysTypeCd, uiid: uiid};
		$.post(url, data, function(result) {
	 		alert(result);
		});
	}
	//private
	function markOpen(funcName, uiid, plasAcctId){
		if(!window.confirm('请确认?')){
			return;
		}
		$('body').mask('处理中,请稍候...');
		var data = {funcName: funcName, uiid: uiid, plasAcctId: plasAcctId};
		$.post('${ctx}/plas/plas-acct!markOpen.action', data, function(result) {
			$('body').unmask();
			if('success' == result){
		 		alert('已关联,需要刷新!');
		 		refreshAcctArea(plasAcctId);//方法在 plas-user.jsp页面
			}else{
				alert(result);
			}
		});
	}
	//检查是否开通Eas
	function validateOpenEas(uiid){
		validateOpen('eas',uiid); 
	}
	//检查是否开通Mysoft
	function validateOpenMysoft(uiid, plasAcctId){
		validateOpen('mysoft', uiid, plasAcctId);
	}
	//检查是否开通email
	function validateOpenEmail(uiid, plasAcctId){
		validateOpen('email', uiid, plasAcctId);
	}
	//检查是否开通cmail
	function validateOpenCmail(uiid, plasAcctId){
		validateOpen('cmail', uiid, plasAcctId);
	}
	
	//手动设置关联EAS
	function markOpenEas(uiid, plasAcctId){
		markOpen('eas',uiid, plasAcctId);
	}
	//手动设置关联Mysoft
	function markOpenMysoft(uiid,plasAcctId){
		markOpen('mysoft', uiid, plasAcctId);
	}
	//手动设置关联cmail
	function markOpenCmail(uiid,plasAcctId){
		markOpen('cmail', uiid, plasAcctId);
	}
	//private 
	function commonProcess(plasAcctId,funcName,operateName){
		var otherTip = '';
		if('acctClose' == funcName){
			otherTip = '同时将职员设置为离职';
		}
		
		var val = '';
		if('acctResetPasswordHand' == funcName){
			val = $('#inputResetPwd').val();
			if($.trim(val) == ''){
				alert('请手动设置密码!');
				return;
			}
		}
		
		$.messager.confirm('确认', operateName + otherTip + ',您确定继续吗?',function(r){
			if(r){
				$('body').mask('处理中,请稍候...');
				var params = {funcName: funcName, plasAcctId: plasAcctId, newPwd: val};
				$('#tip_operate_result').hide();
				$.post('${ctx}/plas/plas-acct!commonProcess.action', params, function(result){
					$('body').unmask();
					var data = eval('(' + result + ')');
					if(data.success){
						$('#tip_operate_result').html('提示:'+operateName + '操作成功! ' + data.desc).show().fadeOut(5000);
						if('acctResetPasswordHand' == funcName){
							$('#handArea').hide();
						}else{
							//$.messager.alert('提示', operateName + '操作成功!<br/>' + data.desc);
							refreshAcctArea(plasAcctId);
							if('acctFreeze' == funcName || 'acctEnable' == funcName || 'acctClose' == funcName){
								reloadAcctTree();
							}
						}
					}else{
						$.messager.alert('提示',operateName + '操作失败!<br/>' + data.desc);
					}
				});
			}
		});
	}
	//账号(1-冻结/2-启用/3-注销/4-重置密码)[编号仅前台使用]
	function processAcct(plasAcctId,funcName,operateName){
		commonProcess(plasAcctId, funcName, operateName);
	}
	//eas(1-开通/2-禁用/3-启用/4-同步账号信息/5-同步密码)[编号仅前台使用]
	function processEas(plasAcctId,funcName,operateName){
		commonProcess(plasAcctId, funcName, operateName);
	}  
	//email(1-开通/2-禁用/3-启用/4-同步账号信息/5-同步密码)[编号仅前台使用]
	function processEmail(plasAcctId,funcName,operateName){
		commonProcess(plasAcctId, funcName, operateName);
	} 
	//cmail(1-开通/2-禁用/3-启用/4-同步账号信息/5-同步密码)[编号仅前台使用]
	function processCmail(plasAcctId,funcName,operateName){
		commonProcess(plasAcctId, funcName, operateName);
	} 
	//eas(1-开通/2-禁用/3-启用/4-同步账号信息/5-同步密码)[编号仅前台使用]
	function processMysoft(plasAcctId,funcName,operateName){
		commonProcess(plasAcctId, funcName, operateName);
	}  
	
	
	//重新搜索账号
	function reloadSearch(){
		var acctId = $.trim($('#quickSearchFieldId').val());
		if(acctId){
			refreshAcctArea(acctId)
		}
	}
	//清空输入框
	function cleanSearchField(){
		$('#quickSearchField').val('');
		$('#quickSearchFieldId').val('');
	}
	

	//查看职位列表
	function searchPositionList(acctId){
		$('#relPositionListDiv').mask('正在载入职位列表...');
		var url = '${ctx}/plas/plas-acct!searchPositionList.action';
		$.post(url, {acctId: acctId}, function(result){
			$('#relPositionListDiv').unmask();
			$('#relPositionListDiv').html(result);
		});
	}

	//不需要登录验证
	function ignoreLoginValidate(acctId){
		if(!acctId){
			return;
		}
		var url = '${ctx}/plas/plas-acct!ignoreLoginValidate.action';
		var data = {acctId: acctId};
		$.post(url,data,function(result){
			if('success' == result){
				$('#btnIgnoreLoginValidate').hide();
				$('#ignoreLoginValidateTip').html('已设置不需要登录验证!').show();
			}else{
				$.messager.alert('提示','保存失败!'+result);
			}
		})
	}

	//同步邮箱至通讯录
	function synEmailToContact(acctId){
		if(!acctId){
			return;
		}
		var url = '${ctx}/plas/plas-acct!synEmailToContact.action';
		var data = {acctId: acctId};
		$.post(url,data,function(result){
			if('success' == result){
				$('#btnSynEmailToContact').hide();
				$('#email_operate_tip').html('已同步至pd首页通讯录!').show();
			}else{
				$.messager.alert('提示','保存失败!'+result);
			}
		});
	}
	//加载职位树
	var gPosTree;
	
	function showPositionTreeByFlg(){
		showPositionTree($('#tmpPosIdPop').val());
	}
	function showPositionTree(acctId){
		if(!acctId){
			return;
		}
		$('#wConfigPos').window('open');
		$('#wConfigPos').mask('正在搜索职位...');
		$('#posTreePanel').html('');
		var url = '${ctx}/plas/plas-acct!loadPositionTree.action';
		var data = {acctId: acctId, isAllPosFlg: ($('#pos_all_flag').attr('checked'))};
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
			});
	 		//treePanel动态生成,只能在这里赋值height:400px;overflow-y:auto;+position: relative;
	 		$('#posTreePanel').find('.TreePanel').css({"height":"400px", "overflow-y": "auto", "+position":"relative"});
	 		 
		});
	} 
	function savePopPos(){
		var addDels = gPosTree.getAddDeleteIds('3');//职位
	 	var addIds = addDels[0];
	 	var delIds = addDels[1];
	 	if((addIds == '') && (delIds == '') ){
	 		alert("未发生变化!");
	 		return;
	 	}
	 	$("#posRangePanelDiv").html(gPosTree.getPopDiv('3','授权职位','收回职位'));
		$('#wConfigPos').window('close');
	 	$("#wConfigPosConfirm").window('open');
	}
	function closePopPos(){
		$('#wConfigPos').window('close');
	}

	//确认保存
	function savePopPosConfirm(){
		var acctId = $('#plasAcctId').val();//明细页面
		var addDels = gPosTree.getAddDeleteIds('3');//职位
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
				searchPositionList(acctId);
				$.messager.alert('提示','授权系统职位成功!');
			}else{
				$.messager.alert('提示','授权系统职位失败!'+result);
			}
		});
	}
	function closePopPosConfirm(){
		$('#wConfigPos').window('open');
		$('#wConfigPosConfirm').window('close');
	}
 
</script>
</body>
</html>