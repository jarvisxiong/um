<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-project.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css" />
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>

<title>项目资料管理</title>
</head>
<body>
	<s:set var="module">8</s:set>
	<%@ include file="bis-manage-header.jsp"%>

	<div style="height:100%; margin-bottom:10px; padding-top:10px; border-bottom:1px solid #DDDBDC; background-color: #F7F7F7; display:none;" id="inputPanel" >
	
	</div>
	
	<div id="listPanel" style="clear:both; padding:10px 8px 0px; text-align:center;">
		<!--<div style="color: #6BAD42; font-size: 14px; font-weight: bold; text-align: center; line-height: 300px;">暂无记录！</div>-->
	</div>
	
<script type="text/javascript">
$(function(){
	submitSearch();
});

function jumpPage(pageNo) {
	var data={
		bisProjectId : $("#bisProjectId").val(),
		'page.pageNo': pageNo
	};
	//if (selectedOrderBy){
	//	data.selectedOrderBy=selectedOrderBy;
	//	data.selectedOrder=selectedOrder;
	//}
	TB_showMaskLayer("正在搜索...");
	var url = "${ctx}/bis/bis-project-layout!list.action";
	$.post(url, data, function(result) {
		$("#listPanel").html(result);
		TB_removeMaskLayer();
	});
}
function jumpPageTo() {
	var index = $("#pageTo").val();
	index = parseInt(index);
	if (index > 0) {
		jumpPage(index);
	}
}

/**
 * 搜索
 */
function submitSearch() {
	$("#pageNo").val("1");
	TB_showMaskLayer("正在搜索...");
	var url = "${ctx}/bis/bis-project-layout!list.action";
	$.post(url, {bisProjectId:$("#bisProjectId").val()}, function(result) {
		$("#listPanel").html(result);
		TB_removeMaskLayer();
	});
}

//新增
function doAddLayout() {
	var url = "${ctx}/bis/bis-project-layout!input.action";
	$.post(url, {bisProjectId:$("#bisProjectId").val()}, function(result) {
		$("#inputPanel").show();
		$("#inputPanel").html(result);
	});
}

//修改
function doEditLayout(id) {
	var url = "${ctx}/bis/bis-project-layout!input.action";
	$.post(url, {id:id}, function(result) {
		$("#inputPanel").show();
		$("#inputPanel").html(result);
	});
}

//保存
function doSaveLayout() {
	var pdv = new Validate("processForm");
	if (!pdv.validate()) {
		$("#processForm :input,textarea").filter("[validate=required]").filter("[value='']").eq(0).focus();
		return false;
	}
	if($("#attachFlg").val() != '1') {
		alert("请上传附件");
		return false;
	}
	TB_showMaskLayer("正在保存...");
	$("#processForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		document.forms['processForm'].reset();
		//submitSearch();
		var url = "${ctx}/bis/bis-project-layout!list.action";
		$.post(url, {bisProjectId:$("#bisProjectIdNew").val()}, function(result) {
			$("#listPanel").html(result);
		});
	});
}

function doCancel() {
	$("#inputPanel").hide();
}

//删除
function doDeleteLayout(id) {
	ymPrompt.confirmInfo({message:'确认删除？',title:'删除',width:200,height:150,handler:function (tp){
		if (tp=='ok'){
			$.post(_ctx+"/bis/bis-project-layout!delete.action",{id:id},function(result) {
				submitSearch();
			});
		}
	}});
}

/**
 * 查看附件
 */
function showAttachment(entityId){
	ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=bisProjectLayout&filterType=image|office&onlyShow=true",
		width:500,
		height:300,
		title: '附件查看',
		afterShow:function(){},
		iframe:true,
		btn:[["完成",'close']]
	});
}
/**
 * 管理附件
 */
function openAttachment(title,entityId,domId,attachFlgId){
	ymPrompt.win({
		message:_ctx+"/app/app-attachment!list.action?bizEntityId="+entityId+"&bizModuleCd=bisProjectLayout&filterType=image|office",
		width:500,
		height:300,
		title:title,
		iframe:true,
		afterShow : function() {},
		handler : function(btn) {
			if(btn=='close') {
				showAttach(entityId,domId,attachFlgId);
			}
		},
		btn:[["完成",'close']]
	 });
}
/**
 * 判断是否有附件
 */
function showAttach(entityId,domId,attachFlgId) {
	$.post(_ctx+"/app/app-attachment!hasAttachment.action",
		{bizEntityId:entityId,bizModuleCd:'bisProjectLayout'},
		function(result){
			if(result == "true") {
				$("#"+domId).attr("src",_ctx+"/resources/images/common/atta_y.gif");
				$("#"+attachFlgId).val("1");
			} else {
				$("#"+domId).attr("src",_ctx+"/resources/images/common/atta.gif");
				$("#"+attachFlgId).val("0");
			}
	});
}
</script>
</body>
</html>
