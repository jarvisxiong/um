<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<!-- 

 -->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisCont.css" />
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bisCont.js"></script>

<title>商业租户管理</title>
</head>
<body>
<div class="title_bar" >
	<div style="font-weight:bold;padding-left:8px;padding-right:8px; font-size:14px;float:left;">商业租户管理</div>
	
            <div style="float:right; height:26px; margin-right:5px; margin-top:6px;">
                <div class="btn_green" onclick="clkFullScreen('${bisProjectId}');">全屏</div>
            </div>
</div>
<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
<form action="${ctx}/bis/bis-tenant!list.action" id="SearchForm" method="post">

<div id="listPanel" style="clear:both; padding:10px 8px 0px; text-align:center;">
	<%@ include file="bis-tenant-listView.jsp" %>
	<!--<div style="color: #6BAD42; font-size: 14px; font-weight: bold; text-align: center; line-height: 300px;">暂无记录！</div>-->
</div>

</form>
	
<script type="text/javascript">
isEmpty = function (str) {
	return (typeof (str) === "undefined" || str === null || (str.length === 0));
};

$(function(){
	submitSearch();
});

//刷新当页
function refreshCurrPage() {
	jumpPage($("#pageNo").val());
}

function jumpPage(pageNo) {
	$("#pageNo").val(pageNo);
	
	TB_showMaskLayer("正在搜索...");
	$("#SearchForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$("#listPanel").html(result);
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
	$("#SearchForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$("#listPanel").html(result);
	});
}


//修改
function doEditTenant(id) {
	
	ymPrompt.win( {
		icoCls : "",
		message : "<div id='bisTenantViewDiv'><img align='absMiddle' src='"
			+ _ctx + "/images/loading.gif'></div>",
		width : 790,
		height : 230,
		title : "租户明细",
		afterShow : function() {
			var url = "${ctx}/bis/bis-tenant!input.action";
			$.post(url, {id:id}, function(result) {
				$("#bisTenantViewDiv").html(result);
			});
		},
		btn:[["关闭",'close']]
	});
}

//保存
function doSaveTenant() {
	if(isEmpty($("#bisShopId").val()) && isEmpty($("#owner").val())) {
		alert("品牌和业主至少填一个");
		return;
	}
	var pdv = new Validate("processForm");
	if (!pdv.validate()) {
		//$("#processForm :input,textarea").filter("[validate=required]").filter("[value='']").eq(0).focus();
		return false;
	}
	TB_showMaskLayer("正在保存...");
	$("#processForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$("#inputPanel").hide();
		refreshCurrPage();
	});
}

//删除
function doDeleteTenant(id) {
	ymPrompt.confirmInfo({message:'确认删除？',title:'删除',width:200,height:150,handler:function (tp){
		if (tp=='ok'){
			$.post(_ctx+"/bis/bis-tenant!delete.action",{id:id},function(result) {
				refreshCurrPage();
			});
		}
	}});
}

function toMergeTenant() {
	var choosedTenantId = $("#tbConItem tr").filter(".tr_click").attr("value");
	var checkbox_ids = new Array();
	var checkbox_chkIds = new Array();
	$("#tbConItem :input[type=checkbox]:checked").not(".chk_all").each(function(i, dom) {
		checkbox_ids.push($(dom).val());
		checkbox_chkIds.push("chkIds=" + $(dom).val());
	});
	if(checkbox_ids.length < 2){
		alert("至少勾选两项");
		return false;
	}
	var param = checkbox_chkIds.join("&");
	if(!isEmpty(choosedTenantId)) {
		param = param+"&standardTenantId="+choosedTenantId;
	}
	$.post("${ctx}/bis/bis-tenant!toMerge.action", param, function(result) {
		$("#inputPanel").show();
		$("#inputPanel").html(result);
		$("#tbConItem tr").removeClass("tr_click");
	});
}

function doMergeTenant() {
	
	if(isEmpty($("#bisShopId").val()) && isEmpty($("#owner").val())) {
		alert("品牌和经销商至少填一个");
		return;
	}
	var pdv = new Validate("processForm");
	if (!pdv.validate()) {
		//$("#processForm :input,textarea").filter("[validate=required]").filter("[value='']").eq(0).focus();
		return false;
	}
	TB_showMaskLayer("正在保存...");
	$("#processForm").ajaxSubmit(function(result) {
		TB_removeMaskLayer();
		$("#inputPanel").hide();
		refreshCurrPage();
	});
}

function doInvalidTenant(id) {
	$.post("${ctx}/bis/bis-tenant!invalid.action", {id:id}, function(result) {
		alert("操作成功");
		refreshCurrPage();
	});
}

function doValidTenant(id) {
	$.post("${ctx}/bis/bis-tenant!valid.action", {id:id}, function(result) {
		alert("操作成功");
		refreshCurrPage();
	});
}

$(".enterQuery").keydown(function(event){
	if(event.keyCode==13){
		submitSearch();
	}
});
</script>
</body>
</html>
