<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<link rel="stylesheet" href="${ctx}/resources/css/common/common.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
<link rel="stylesheet" href="${ctx}/resources/css/biz/biz-rela.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
</head>
<body>
<form action="${ctx}/biz/biz-rela-lib!relaAuthSave.action" method="post" id="bizRelaAuthForm">
<div id="div_main_cont" style="margin-left: 0px;margin-top: 1px;">
	 <fieldset style="padding-left: 0px;padding-right: 0px; padding-top: 0px;">
	 <legend>&nbsp;&nbsp;
	    提交公司/中心 权限配置
	 </legend>
	 </fieldset>
</div>
<table>
<tr>
	<td valign="top" rowspan="1" width="240">
		<div style="width: 230px; float:left;margin-left: 10px;overflow: hidden;border:1px solid #ccc">
			<div class="divTree"  id ="user-tree-div"></div>
		</div>
	</td>
	<td align="left" valign="top">
		
		<div class="btn_blue" id="btnDispatchOrg" onclick="dispatchOrg();" style="cursor:pointer;">分配</div>
		<div style="float:left;" id ="org_div"></div>
		<div class="btn_blue" id="btnDispatchOrgSp" onclick="saveDispatchRel()" style="cursor:pointer;display: none;">保存</div>
	</td>
</tr>
</table>
</form>
<script type="text/javascript">
	$(function(){
		loadUserTree();
		$('#submitCenterName').orgSelect({});
		
		$('#userName').userSelect({
			muti:false
		});
	});
	function relaAuthSave(){
		$('#bizRelaAuthForm').ajaxSubmit(function(result) {
			if(result!=null){
				alert("保存成功");
			}
		});
	}
	
	var treePanel_user;
	function loadUserTree(){
		$("#user-tree-div").html('<div><image src="${ctx}/images/loading.gif"/>加载数据...</div>');
		$.post("${ctx}/biz/biz-rela-lib!buildUserTree.action", function(result) {
			$("#user-tree-div").empty();
			if (result) {
				var root =eval('('+result+')');
				treePanel_user = new TreePanel({
					renderTo:'user-tree-div',
					'root' : root,
					'ctx':'${ctx}'
				});
				treePanel_user.render(); 
				
				treePanel_user.on(function(node){
					//机构
					var nodeType = node.attributes.nodeType;
					var nodeId = node.attributes.entityId;
					if( nodeType == '1'){
						if(node.isExpand){
							node.collapse();
						}else{
							node.expand();
						}
					}
					//用户
					else if(nodeType == '0'){
						viewDispatchedOrgList(nodeId);
					}
				});
			}
		});
	}
	
	function dispatchOrg(){
		$.post("${ctx}/biz/biz-rela-lib!bizRelaOrgList.action", function(result) {
			if(result){
				$('#org_div').html(result);
			    $('#btnDispatchOrgSp').show();
			}
		});
	}
	
	function saveDispatchRel(){
		if(treePanel_user){
			
			if($('#org_div').find(':checkbox[checked=true]').length == 0){
				alert('请选择机构!');
				return;
			}

			var orgArr = new Array();
			$('#org_div').find(':checkbox[checked=true]').each(function(i,n){
				orgArr.push($(this).attr('relId'));
				orgArr.push(',');
			});
			var orgDictCds = orgArr.join('');
			
			var addDels = getAddDeleteIds();
			var addUserIds = addDels[0];
			var delUserIds = addDels[1];
			
			if((addUserIds == '') && (delUserIds == '') ){
				alert('请选择人员!');
				return;
			}

			var url='${ctx}/biz/biz-rela-lib!saveBatchUserOrgRel.action';
			var data = {orgDictCds: orgDictCds,addUserIds: addUserIds,delUserIds: delUserIds};
			$.post(url, data, function(result) {
				if(result == 'success'){
					alert('提示：授权成功!');
				}else{
					alert('提示：授权失败!');
				}
			});
		}
	}
	//获取授权或收回的用户IDs
	function getAddDeleteIds(){
		var modifyNodes = treePanel_user.getModifiedLeafNodes('0');//0-user
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
	
	function viewDispatchedOrgList(userId){
		$('#btnDispatchOrgSp').hide();
		var url = '${ctx}/biz/biz-rela-lib!dispatchedOrgList.action';
		var data = {userId: userId};
		$.post(url, data, function(result){
			$('#org_div').html(result);
		});
	}
	
	function removeRel(dom,dictCd,dictName,userCd){
		var url = '${ctx}/biz/biz-rela-lib!removeRel.action';
		var data = {orgCd: dictCd, dictName:dictName, userCd:userCd};
		$.post(url, data, function(result){
			if('success' == result){
				$(dom).parent().parent().remove();
				$('#tip_removeRel').fadeIn(2000).fadeOut(2000);
			}else{
				alert(result);
			}
		});
	}
</script>
</body>
</html>