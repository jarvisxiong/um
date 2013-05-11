<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp"%>
	<meta http-equiv="Content-Type" content="text/html" />
	<title>网上审批</title>
</head>

<body style="background-color:#fff;">
	<div id="searchApproveFix" style="width:182px;border-bottom:1px solid #BFBFBF;">
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%;">
			<tr>
				<td>
					<input  value="搜索表单..." 
							type="text" 
							style="padding:2px;border:0;font-size: 12px;color: #CCCCCC;width:100%;"
							onkeyup="searchTreeNode(this)"
							
							id="inputSearchVal"
					/>
				</td>
				<td style="width:56px;">
					<div id="inputSearchOperate" class="searchNextNoActive" onclick="searchTreeNode(document.getElementById('inputSearchVal'))">&nbsp;</div>
				</td>
			</tr>
		</table>
	</div>
	
	<div id="divTreeP" class="divTreeP" style="background-color: #e4e7ec;padding-top:5px;float:left;overflow-x:hidden;width:177px;border:none;">
		<div id ="search-div">
		</div>
		<div id ="tree-div">
		</div>
	</div>
	<script>
	$(function(){
		//载入机构树
		initTree();
		
	});
	
	var treePanel;
	var userSearchMgr;
	function initTree(){
		$("#tree-div").html('<div><image src="'+_ctx+'/resources/images/common/loading.gif"/>加载数据...</div>');
		$.post(_ctx+"/res/res-approve-info-tree!buildTree.action",{active:'1'}, function(result) {
			$("#tree-div").empty();
			if (result) {
				var arr=eval('('+result+')');
				root=arr;
				treePanel = new TreePanel({
					renderTo:'tree-div',
					'root' : root,
					'ctx':_ctx
				});
				treePanel.render();
				treePanel.isExpendSelect = true;
				treePanel.on(function(node){
					var tmp = node.attributes.entityCd;
					
					if("0" == tmp){
						$("#btnNewApply").attr("disabled","disabled");
						curAuthTypeCd='';
						$("#idCurAuthType").html('');
						return;
					}

						//模块
						if(node.attributes.nodeType == "module"){
							if(node['html-element']['child'].innerHTML==''){
								node['html-element']['child'].id=node.id;
								loadNodeTree(node.id,node);
							}
							if(node.isExpand){
								node.collapse();
							}else{
								if(node['html-element']['child'].style.display=='block'){
									node.collapse();
								}else{
									node['html-element']['child'].style.display='block';
									node.isExpand = true;
									node.expand();
									}
								
							}
						}

						//分类
						if(node.attributes.nodeType == "authType"){
							var itemCd=node.attributes.entityCd;
							var resAuthName=node.attributes.entityName;
							$('#idCurAuthType').html(resAuthName);
							var isSearch=true;
							var isCanSend=true;
							curAuthTypeCd=itemCd;
							if (curAuthTypeCd=='QTSW_WJSP'){
								//文件审批发起权限
								var hasPermission=$('#flg_lwsx_p').val();
								hasPermission=eval(hasPermission);
								if (!hasPermission){
									$("#btnNewApply").attr("disabled","disabled");
									isCanSend=false;
									ymPrompt.alert({message:'没有发起权限！',title:"提示",width:220,height:180});
								}
							}else if (curAuthTypeCd.indexOf('JSGL_SYGCGZ')!=-1){
								//商品工程改造
								//$("#btnNewApply").attr("disabled","disabled");
								//isCanSend=false;
								//ymPrompt.alert({message:'很抱歉，此项表单已暂停使用，请使用其他流程表或纸面文件，如有不明请咨询总裁办企管部',title:"提示",width:400,height:180});
							}
							$('#resAuthName').val(resAuthName);
							if (isSearch){
								$.post(_ctx+"/res/res-approve-info!load.action",
								{
									resAuthTypeCd:curAuthTypeCd,  //
									listMode:"0",  //
									qsCondition:getValidValue($("#qsCondition").val()),
									approveCdSrh:getValidValue($("#approveCd").val()),
									selectOpinion:getValidValue($("#selectOpinion").val()),
									filter_LIKES_userName:getValidValue($("#filter_LIKES_userName").val()),
									filter_LIKES_approveUserCd:getValidValue($("#filter_LIKES_approveUserCd").val()),
									filter_GED_startDate:getValidValue($("#mainFormSearch_filter_GED_startDate").val()),
									filter_LTD_startDate:getValidValue($("#mainFormSearch_filter_LTD_startDate").val()),
									filter_LIKES_statusCd:getValidValue($("#filter_LIKES_statusCd").val()),
									filter_LIKES_approveUserName:getValidValue($("#filter_LIKES_approveUserName").val()),
									filter_LIKES_landproject:getValidValue($("#filter_LIKES_landproject").val()),
									filter_LIKES_titlename:getValidValue($("#filter_LIKES_titlename").val()),
									filter_LIKES_randomNo:getValidValue($("#filter_LIKES_randomNo").val())
								},
								function(result) {
									$("#content").html(result);
									if (!isCanSend){
										$("#btnNewApply").attr("disabled","disabled");
									}
									$('#srh_listMode').val(getValidValue($("#listMode").val()));
									resetLeftPanel();
								});
							}
						}else{
							$("#btnNewApply").attr("disabled","disabled");
							curAuthTypeCd='';
							$("#idCurAuthType").html('');
						}
					
				});
				function getValidValue(value){
					if(typeof(value)=='undefined'){
						value='';
					}
					return value;
				} 
			}
		});
	}
	

	function loadNodeTree(orgId,node){
		var returnValue= {};
		var tNode ;
		$("#"+node.id).html('<div><image src="'+_ctx+'/resources/images/common/loading.gif"/>加载数据...</div>');
		$.post(_ctx+"/res/res-approve-info-tree!buildTree.action",{active:'1',orgId:orgId}, function(result) {
			if (result) {
				$("#"+orgId).empty();
				var arr=eval('('+result+')');
				tNode = arr.children;
				for(var i=0,j=tNode.length;i<j;i++){
					var p = new TreeNode(tNode[i]);
					node.appendChild(p);
				}
				node.paintChildren();
			}
		});
	}

	function searchTreeNode(srcElem) {
		var wabTreeElemId = "tree-div";
		var resultElemId = "search-div";
		if (userSearchMgr)
			clearTimeout(userSearchMgr);
		userSearchMgr = setTimeout(function() {
			var val = $.trim($(srcElem).val());
			if (val == "") {
				$("#" + wabTreeElemId).show();
				$("#" + resultElemId).hide();
				return;
			} else {
				$("#" + wabTreeElemId).hide();
				$("#" + resultElemId).show();
				$(srcElem).css( {
					color : "#5A5A5A"
				});
				$("#" + resultElemId).addClass("waiting");
				$.post(_ctx + "/res/res-approve-info-tree!searchTreeList.action", {
					value : val
				}, function(result) {
					$("#" + resultElemId).html(result).removeClass("waiting").height(320).show();// 300:计算排除的高度
				});
			}
		}, 300);
	}

	function openAuthType(moduleCd,authTypeName){
		var itemCd=moduleCd;
		var resAuthName=moduleName;
		$('#idCurAuthType').html(resAuthName);
		var isSearch=true;
		var isCanSend=true;
		curAuthTypeCd=itemCd;
		if (curAuthTypeCd=='QTSW_WJSP'){
			//文件审批发起权限
			var hasPermission=$('#flg_lwsx_p').val();
			hasPermission=eval(hasPermission);
			if (!hasPermission){
				$("#btnNewApply").attr("disabled","disabled");
				isCanSend=false;
				ymPrompt.alert({message:'没有发起权限！',title:"提示",width:220,height:180});
			}
		}else if (curAuthTypeCd.indexOf('JSGL_SYGCGZ')!=-1){
			//商品工程改造
			//$("#btnNewApply").attr("disabled","disabled");
			//isCanSend=false;
			//ymPrompt.alert({message:'很抱歉，此项表单已暂停使用，请使用其他流程表或纸面文件，如有不明请咨询总裁办企管部',title:"提示",width:400,height:180});
		}
		$('#resAuthName').val(resAuthName);
		if (isSearch){
			$.post(_ctx+"/res/res-approve-info!load.action",
			{
				resAuthTypeCd:curAuthTypeCd,  //
				listMode:"0",  //
				qsCondition:getValidValue($("#qsCondition").val()),
				approveCdSrh:getValidValue($("#approveCd").val()),
				selectOpinion:getValidValue($("#selectOpinion").val()),
				filter_LIKES_userName:getValidValue($("#filter_LIKES_userName").val()),
				filter_LIKES_approveUserCd:getValidValue($("#filter_LIKES_approveUserCd").val()),
				filter_GED_startDate:getValidValue($("#mainFormSearch_filter_GED_startDate").val()),
				filter_LTD_startDate:getValidValue($("#mainFormSearch_filter_LTD_startDate").val()),
				filter_LIKES_statusCd:getValidValue($("#filter_LIKES_statusCd").val()),
				filter_LIKES_approveUserName:getValidValue($("#filter_LIKES_approveUserName").val()),
				filter_LIKES_landproject:getValidValue($("#filter_LIKES_landproject").val()),
				filter_LIKES_titlename:getValidValue($("#filter_LIKES_titlename").val()),
				filter_LIKES_randomNo:getValidValue($("#filter_LIKES_randomNo").val())
			},
			function(result) {
				$("#content").html(result);
				$("#idCurAuthType").html(resAuthName).attr("title",node.attributes.text);
				if (!isCanSend){
					$("#btnNewApply").attr("disabled","disabled");
				}
				$('#srh_listMode').val(getValidValue($("#listMode").val()));
				resetLeftPanel();
			});
		}
	}
	</script>
</body>
</html>
