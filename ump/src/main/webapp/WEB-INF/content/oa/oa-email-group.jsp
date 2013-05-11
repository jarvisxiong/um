<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>设置自定义组</title>
	
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/nocache.jsp" %>
	
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/prompt/skin/custom_1/ymPrompt.css" /> 
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/thickbox.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
	<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/email/email.css"  />
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/MaskLayer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
</head>

<body>
	<div class="mailTop">
		<div style="float:left;margin-right: 5px;">
			<img src="${ctx}/images/desk2/mail_new.jpg" style="margin-top:3px;"/>
		</div>
		<div style="float:left;">
			自定义用户组&nbsp;
		</div>
		<div id="div_msg" style="float:left;" class="color_red"></div>
	</div>
	<div>
		<div class="mailLeft" style="padding-top:5px;">
			<div class="leftMenu">
				<s:if test="oaEmailGroups.size()>0">
					<s:iterator value="oaEmailGroups">
						<div class="groupbox" id="${oaEmailGroupId}" onclick="load(this)">
							<span style="padding-left:10px;">${groupName}</span>
						</div>
					</s:iterator>
				</s:if>
				<div class="groupbox"  onclick="add(this);" style="font-weight: bold;">
				<span style="padding-left:10px;">添加新组</span>
				</div>
			</div>
		</div>
		<div class="mailRight" id="groupRight">
		</div>
	</div>
<script type="text/javascript">
	function load(dom){
		TB_showMaskLayer("正在加载...");
		var id = $(dom).attr("id");
		$(dom).addClass("menuClick").siblings().removeClass("menuClick");
		$.post("${ctx}/oa/oa-email-group!input.action",{id:id},function(result){
			$("#groupRight").html(result);
			TB_removeMaskLayer();
		});
	}
	function save(){
		$("#groupForm").ajaxSubmit(function(result){
			$("#div_msg").html("保存成功").show().fadeOut(1000);
			setTimeout("refreshPage()",1000);
		});
	}
	function del(){
		var id = $("#oaEmailGroupId").val();
		if(!confirm("确定要删除该收件人组吗？"))return;
		$.post("${ctx}/oa/oa-email-group!delete.action",{id:id},function(result){
			$("#div_msg").html("删除成功").show().fadeOut(1000);
			setTimeout("refreshPage()",1000);
		});
	}
	function closeTab(){
		var cfg = {};
		cfg.data = {tabId:'emailGroup'};
		window.parent.TabUtils.closeTab(cfg);
	}
	function add(dom){
		TB_showMaskLayer("正在加载...");
		$(dom).addClass("menuClick").siblings().removeClass("menuClick");
		$.post("${ctx}/oa/oa-email-group!input.action",function(result){
			$("#groupRight").html(result);
			TB_removeMaskLayer();
		});
	}
	$(function(){
		$('.mailLeft').height($(document).height()-28);
		$("div .groupbox:first").trigger("click");
	});
	function refreshPage(){
		window.location.href="${ctx}/oa/oa-email-group.action";
	}
</script>

</body>

</html>
