<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ include file="/common/global.jsp" %>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/css/desk/thickbox.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bisReport.css"  />
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
<title>商业管理</title>
</head>

<body>

<s:set var="curUserCd"><%=SpringSecurityUtils.getCurrentUiid()%></s:set>
<input type="hidden" id="reportTypeStr" value="${reportTypeStr}"/>

<div id="div_title" style="height: 30px;">
	<div class="bis_search" id="main_search_div" style=" background: #FFF; border-bottom: 0px;" >
	<table class="tb_search">
		<col width="60"/>
		<!--col width="80"/>
		<col width="30"/-->
		<col width="100"/>
		<col/>
		<tr>
			<td align="left">报表月份：</td>
			<td>
			    <input validate="required" class="Wdate" type="text" name="reportDate" id="reportDate" value="${reportDate}" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" style="height:16px;line-height: 16px;"/>
			</td>
			<!--td></td>
			<td>
				<ul class="bis_rpt" id="bis_rpt" style="float:left; width: 100%;">
					<li id="li_rpt_1" onclick="changeReportType('li_rpt_1');" <s:if test="reportTypeStr=='total'">class="bis_rpt_click"</s:if> >集团资金流量汇总表</li>
					<li id="li_rpt_2" onclick="changeReportType('li_rpt_2');" <s:if test="reportTypeStr=='manage'">class="bis_rpt_click"</s:if> >集团经营情况汇总表</li> 
				</ul>
			</td-->
			<td style="padding-left: 10px;">
				<input type="button" value="搜索" class="btn_blue" onclick="ajaxSearch();" style="height:22px;line-height: 22px;"/>
				<s:if test="#curUserCd=='baolm' || #curUserCd=='lujy' || #curUserCd=='shenjian' || #curUserCd=='jiaoxf' || #curUserCd=='dengyh'">
				&nbsp;&nbsp;<input type="button" class="btn_blue" value="更新数据" onclick="refreshData();" style="width:75px;height:22px;line-height: 22px;"/>
				</s:if>
			</td>
		</tr>
	</table>
	</div>
</div>

<div id="reportContent" style="clear:both;padding:0px; padding-top:10px;"></div>

<script type="text/javascript">
$(function(){
	//ajaxSearch();
    if(location.href.indexOf("cash")!=-1){
        changeReportType('li_rpt_1')
    }else if(location.href.indexOf("operate")!=-1){
        changeReportType('li_rpt_2');
    }
});

/**
 * 切换报表类别
 */
function changeReportType(id) {
	if(id=='li_rpt_1') {
		$("#bis_rpt li").removeClass("bis_rpt_click");
		$("#li_rpt_1").addClass("bis_rpt_click");
		$("#reportTypeStr").val("total");
	} else if(id=='li_rpt_2') {
		$("#bis_rpt li").removeClass("bis_rpt_click");
		$("#li_rpt_2").addClass("bis_rpt_click");
		$("#reportTypeStr").val("manage");
	}
	ajaxSearch();
}

/**
 * 搜索
 */
function ajaxSearch() {
	TB_showMaskLayer("正在搜索...");
	var url = "${ctx}/bis/bis-manage-report!total.action";
	var data = {
		reportTypeStr:$("#reportTypeStr").val(),
		reportDate:$("#reportDate").val()
	};
	$.post(url,data,function(result){
		$('#reportContent').html(result);
		TB_removeMaskLayer();
	});
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

</body>
</html>
