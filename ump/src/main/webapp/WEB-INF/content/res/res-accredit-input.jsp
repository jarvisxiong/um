<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page
	import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp"%>
<%@ include file="/common/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/common.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/res.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/resApprove.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/common/TreePanel.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/userChoose.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/res/fileSuffixes.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />

<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
<script type="text/javascript" src="${ctx}/js/res/resApprove_0220.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>

<script type="text/javascript" src="${ctx}/resources/js/res/resShow.js"></script>
<script type="text/javascript" src="${ctx}/js/userChoose.js"></script>
<script type="text/javascript" src="${ctx}/js/orgChoose.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>


<title>权责审批</title>
<script type="text/javascript">
	function addAssistant(){
		var idIsJbpm = $('#idIsJbpm').attr('checked');
		// var idIsFile = $('#idIsFile').attr('checked');
		var idIsRes = $('#idIsRes').attr('checked');
		var assistant = $('#accUserName').val();
		if(isEmpty(assistant)){
			alert('请选择助理!');
			return ;
		}
		if(!(idIsJbpm || idIsRes)){
			alert('请选择助理的权责!');
			return ;
		}
		//TB_showMaskLayer('正在执行...');
		$("#idAssistantForm").ajaxSubmit(function(result) {
			 $("#idAssistantList").html(result);
			 $("#idAssistantForm").resetForm();
			 toggleAdd();
			 //TB_removeMaskLayer();
		});	
	}

	$(document).ready(function(){
		var listDiv = $('#idAssistantList');	
		var url = '${ctx}/res/res-accredit!loadByUser.action';
		$.post(url,{},function(result){
				if(result){
					listDiv.html(result);
				}
			});
		
		});

	function toggleAdd(){
		$('#idAssistantInput').toggle();
		$('#idToolBar').toggle();
	}
	
	var searchTime;
	function showSearchUser(dom){
		var $currentDom = $(dom);
		var $next = $(dom).siblings();
		
		if(searchTime)clearTimeout(searchTime);
		searchTime = setTimeout(function(){
			var val = $.trim($currentDom.val());
			$next.val("");
			if(val == "") {
				$("#searchUserDiv").slideUp(200);
				return;
			}
			$.post("${ctx}/oa/oa-email!getUsersByFilter.action", {value:val,maxNum:10}, function(result) {
				result = eval(result);
				var $offset = $currentDom.offset();
				$("#searchUserDiv").css({left:$offset.left,top:$offset.top+$currentDom.height() + 5}).empty().show();
				$.each(result, function(i,n) {
					var html = "<div uiid='"+n.uiid+"'><span>"+n.userName +"</span>|<span>"+ n.orgName+"</span></div>";
					$("#searchUserDiv").append(html);
				});
			
				$("#searchUserDiv div").click(function() {
					var userName = $(this).children("span:first").text();
					var uiid = $(this).attr("uiid");
					$currentDom.val(userName);
					$next.val(uiid);
					$("#searchUserDiv").slideUp(200);
				});
			});
		}, 300);
		
		$(document).bind('click', function(event) {
			var event  = window.event || event;
		    var obj = event.srcElement ? event.srcElement : event.target;
		    if(obj != dom){
		    	$("#searchUserDiv").slideUp(200);
		    	if($next.val() == ''){
		    		$currentDom.val('');
		    	}
		    }
		    $(document).unbind('click');
		});
		return false;
	}

</script>

</head>

<body>

<div id="idAssistantList">
	<div class="loading">
		正在加载数据，请耐心等待。
	</div>
</div>
<div id="idToolBar" style="padding: 5px;">
	<input type="button" class="btn_blue_55_20" style="border: none;" value="新增" onclick="toggleAdd()" id="btnNewAssistant" />
</div>

<div id="idAssistantInput" style="padding: 5px; display: none;">
<s:form
	id="idAssistantForm" name="assistantForm" method="post"
	action="res-accredit!saveAssistant.action">
	<s:hidden name="activeFlg" value="1"/>
	<fieldset><legend>新增助理</legend>
	<table class="innerTable" width="100%">
		<tbody>
			<tr>
				<td align="right" width="100px" style="padding: 5px 25px;">
					助理选择:
				</td>
				<td style="padding:5px">
					<input id="accUserName" type="text" name="accUserName" onkeyup="showSearchUser(this)" />
					<s:hidden id="accUserCd" name="accUserCd" />
				</td>
			</tr>
			<tr>
				<td align="right" style="padding: 5px 25px;">
					权责分配:
				</td>
				<td  style="padding: 5px 0px; height: 26px; line-height: 26px;">
					<span style="padding-right:10px;"><s:checkbox id="idIsJbpm" name="isJbpm" ></s:checkbox>出差报销</span> 
					<span style="padding-right:10px;"><s:checkbox id="idIsRes" name="isRes" ></s:checkbox>网上审批</span> 
				</td>
			</tr>
			<tr>
				<td colspan="2" style="padding: 5px;" align="center">
					<input type="button" class="btn_green_55_20" style="border: none;" value="保存" onclick="addAssistant()" id="btnNewAssistant" /> 
					<input type="button" class="btn_green_55_20" style="border: none;" value="取消" onclick="toggleAdd();" id="btnCancel" />
				</td>
			</tr>
		</tbody>
	</table>
	</fieldset>
</s:form></div>
<div id="searchUserDiv" class="searchUserDiv"></div>
</body>
</html>

