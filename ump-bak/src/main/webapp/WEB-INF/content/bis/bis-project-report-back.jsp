<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/custom_1/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisCont.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/cost-ctrl.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisReport.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-shop.css" />

<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/js/validate/PdValidate.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bisCont.js"></script>
<title>项目经营报表</title>
</head>

<body>
<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
<form id="mainFormSearch" action="${ctx}/bis/bis-project-report!load.action">
<input type="hidden" name="reportType" id="reportType" value="${reportType}"/>
<input type="hidden" name="chargeTypeCd" id="chargeTypeCd" value="${chargeTypeCd}"/>

<s:set var="module">2</s:set>
<%@ include file="bis-manage-header.jsp"%>

</form>

<div id="reportContent" style="clear:both; word-break:break-all; padding:10px 8px 0px; text-align:center;">
<div style="color: #6BAD42; font-size: 14px; font-weight: bold; text-align: center; line-height: 300px;">请选择搜索条件！</div>
</div>

<script type="text/javascript">
/**
 * 切换报表类别
 */
function changeReportType(id) {
	if(id=='li_rpt_1') {
		$("#bis_rpt li").removeClass("bis_rpt_click");
		$("#li_rpt_1").addClass("bis_rpt_click");
		$("#reportType").val("total");
		showChargeType(false);
		if(validateSearch()) {
			ajaxSearch();
		}
	} else if(id=='li_rpt_2') {
		$("#bis_rpt li").removeClass("bis_rpt_click");
		$("#li_rpt_2").addClass("bis_rpt_click");
		$("#reportType").val("store");
		showChargeType(true, 'store');
		if(validateSearch()) {
			ajaxSearch();
		}
	} else if(id=='li_rpt_6') {
		$("#bis_rpt li").removeClass("bis_rpt_click");
		$("#li_rpt_6").addClass("bis_rpt_click");
		$("#reportType").val("walkStreet");
		showChargeType(true, 'store');
		if(validateSearch()) {
			ajaxSearch();
		}
	} else if(id=='li_rpt_3') {
		$("#bis_rpt li").removeClass("bis_rpt_click");
		$("#li_rpt_3").addClass("bis_rpt_click");
		$("#reportType").val("flat");
		showChargeType(true, 'flat');
		if(validateSearch()) {
			ajaxSearch();
		}
	} else if(id=='li_rpt_4') {
		
		//alert("此功能暂未开放");
		//return;
		
		$("#bis_rpt li").removeClass("bis_rpt_click");
		$("#li_rpt_4").addClass("bis_rpt_click");
		$("#reportType").val("multi");
		// multi...
		showChargeType(true, 'multi');
		if(validateSearch()) {
			ajaxSearch();
		}
	} else if(id=='li_rpt_5') {
		$("#bis_rpt li").removeClass("bis_rpt_click");
		$("#li_rpt_5").addClass("bis_rpt_click");
		$("#reportType").val("manage");
		if(validateSearch()) {
			ajaxSearch();
		}
	}
}
/**
 * 选择费用类别
 */
function sltReportCharge(dom) {
	
	if($(dom).hasClass("bis_cgt_click")) {
		$(dom).addClass('bis_cgt_li');
		$(dom).removeClass('bis_cgt_click');
	} else {
		$(dom).removeClass('bis_cgt_li');
		$(dom).addClass('bis_cgt_click');
	}
	
}
/**
 * 显示费用类别
 */
function showChargeType(flag, type) {
	if(flag) {
		$("#main_search_div").css({height:"60px"});
		$("#reportContent").css("height",$(window).height()-27-30-30-12-2+"px");
		$("#div_title").css("height","60px");
		$("#trChargeType").show();
		if(type=='store') {
			$("#bis_cgt_store").show();
			$("#bis_cgt_flat").hide();
			$("#bis_cgt_multi").hide();
		} else if(type=='flat') {
			$("#bis_cgt_flat").show();
			$("#bis_cgt_store").hide();
			$("#bis_cgt_multi").hide();
		} else if(type=='multi') {
			$("#bis_cgt_multi").show();
			$("#bis_cgt_store").hide();
			$("#bis_cgt_flat").hide();
		}
	}else{
		$("#main_search_div").css({height:"30px"});
		$("#reportContent").css("height",$(window).height()-27-30-12+"px");
		$("#div_title").css("height","30px");
		$("#trChargeType").hide();
	}
}

function validateSearch() {
	if($("#bisProjectName").val()=='--选择项目--') {
		$("#bisProjectName").val('');
	}
	var pdv = new Validate("mainFormSearch");
	if (!pdv.validate()) {
		//alert("请填写完整");
		$("#mainFormSearch :input,textarea").filter("[validate=required]").filter("[value='']").eq(0).focus();
		if($("#bisProjectName").val()=='') {
			$("#bisProjectName").val('--选择项目--');
		}
		return false;
	}
	return true;
}
/**
 * 搜索
 */
function ajaxSearch() {
	
	if(!validateSearch()) {
		return false;
	}
	
	var reportType = $("#reportType").val();
	if(reportType=='total'||reportType=='manage') {
		$("#mainFormSearch").attr("action","${ctx}/bis/bis-project-report!load.action");
	} else if(reportType=='store' || reportType=='walkStreet') {
		var chargeTypeCd ="";
		$("#bis_cgt_store").find(".bis_cgt_click").each(function(i, dom) {
			chargeTypeCd += dom.value+",";
		});
		$("#chargeTypeCd").val(chargeTypeCd.substring(0,chargeTypeCd.length-1));
	} else if(reportType=='flat') {
		var chargeTypeCd ="";
		$("#bis_cgt_flat").find(".bis_cgt_click").each(function(i,dom){
			chargeTypeCd += dom.value+",";
		});
		$("#chargeTypeCd").val(chargeTypeCd.substring(0,chargeTypeCd.length-1));
	} else if(reportType=='multi') {
		var chargeTypeCd ="";
		$("#bis_cgt_multi").find(".bis_cgt_click").each(function(i,dom){
			chargeTypeCd += dom.value+",";
		});
		$("#chargeTypeCd").val(chargeTypeCd.substring(0,chargeTypeCd.length-1));
	} else {
		return;
	}
	
	TB_showMaskLayer("正在搜索...");
	$("#mainFormSearch").ajaxSubmit(function(result) {
		$("#reportContent").html(result); 
		TB_removeMaskLayer();
		if(reportType=='store' || reportType=='flat' || reportType=='multi'){
			var typeStr =(chargeTypeCd.split(",")).length-1;
			if(chargeTypeCd=="")
				typeStr=2;
			$("#rightTable").width(460*typeStr);
			$("#rightTopTable").width(460*typeStr);
			var contentWidth=$("#reportContent").width();
			$("#rightTopDiv").width(contentWidth-250); 
			$("#rightDiv").width(contentWidth-250); 
			//取高度
			var contentHeight=$("#reportContent").height();
			$("#rightDiv").height(contentHeight-90); 
			$("#leftDiv").height(contentHeight-90); 
			
			if ($("#reportContent").hasClass("scroll_y_show")) {
				$("#reportContent").removeClass("scroll_y_show");
			}
		} else {
			if (!$("#reportContent").hasClass("scroll_y_show")) {
				$("#reportContent").addClass("scroll_y_show");
			}
		}
	});

}

/**
 * 查看费用
 */
function viewFeeManage() {
	//window.location = "${ctx}/bis/bis-manage!layout.action?bisProjectId="+$("#bisProjectId").val()+"&module=3";
	var url = "${ctx}/bis/bis-manage!layout.action?module=3&bisProjectId="+$("#bisProjectId").val();
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("bisFee","收费明细",url,true);
}
/**
 * 查看合同
 */
function viewContManage() {
	//window.location = "${ctx}/bis/bis-manage!layout.action?bisProjectId="+$("#bisProjectId").val()+"&module=4";
	var url = "${ctx}/bis/bis-manage!layout.action?module=4&bisProjectId="+$("#bisProjectId").val();
	if(parent.TabUtils==null)
		window.open(url);
	else
		parent.TabUtils.newTab("bisCont","商业合同管理",url,true);
}

/**
 * 更新报表数据
 */
function refreshData() {
	TB_showMaskLayer("正在更新...");	
	var url = "${ctx}/bis/bis-project-report!refreshData.action";
	$.post(url, {reportDate: $("#reportDate").val()}, function(result) {
		TB_removeMaskLayer();
		TB_showMaskLayer("更新完成",1000);
	});
}

</script>

<script type="text/javascript">
	//chkReportType();
	$("#reportContent").height($(window).height()-27-30-12+"px");
	var reportType = $("#reportType").val();
	if(reportType == 'store') {
		changeReportType('li_rpt_2');
	}
</script>

<security:authorize ifAnyGranted="A_REPO_PROJ_QUERY,A_REPO_ARRE_QUERY">
<script type="text/javascript">
if($("#bisProjectId").val() && $("#reportDate").val() && $("#reportType").val()) {
	if(reportType=='total'||reportType=='manage') {
		ajaxSearch();
	}
}
</script>
</security:authorize>

</body>
</html>
