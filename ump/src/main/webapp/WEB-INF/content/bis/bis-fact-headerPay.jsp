<%@page import="com.hhz.ump.util.DateUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>
<%@ page language="java" import="com.hhz.ump.util.DictContants;" %>

<title>缴费通知</title>
<script type="text/javascript">
var _ctx = '${ctx}';
var isProjectBusinessCompany = "${isProjectBusinessCompany}";
</script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-project.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis.fact.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"/>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/formValidator/formValidator.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/ConverUtil.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js" ></script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis-manage.css"  />
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
</head>
<body >

<form action="${ctx }/bis/bis-fact!toPrintPays.action" id="searchForm" name="searchForm" method="post">
<input type="hidden" name="pageNo" id="pageNo"/>
<div id="header">
<div class="title_bar" >

	<h2>商业收费明细</h2>
	<div class="left">
<!-- 	 <input type="button" class="btn_gray" style="width: 80px;" id="btnSeniorSearch" value="高级搜索"   onclick="showSeniorSearch();"/>
 -->	&nbsp;&nbsp;&nbsp;&nbsp;项目：
	 <input type="text" readonly="true" id="bisProjectName" name="bisProjectName" value="${bisProjectName}" style="cursor: pointer;color: #ff0000;"/>
		<input class="search" type="hidden" id="bisProjectIdFact" name="bisProjectId" value="${bisProjectId}"/>
	&nbsp;&nbsp;&nbsp;&nbsp;业态：	
			<s:select validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapContBigTypeNew()" 
		listKey="key" listValue="value" name="layOutCd" id="layOutCd" 
		onchange="changlayOutDetail1();" required="true"></s:select>
	</div>
	
	<%-- <div style="font-weight:bold;padding-left:8px;padding-right:8px; font-size:14px;float:left;">7商业收费明细</div>
	<div id="btnSeniorSearch" class="quickSenior" style="width:27px; padding:0 0 0 70px;float:left;" onclick="showSeniorSearch();">&nbsp;</div>
	<div style="font-size:12px;width: 37px; height:16px;margin-left: 8px; padding: 0pt; float: left;">项目：</div>
	<div style="width: 100px; margin-top: 4px; padding: 0pt ; float: left; 	">
		<input type="text" id="bisProjectName" name="bisProjectName" value="${bisProjectName}" style="  height: 16px;width:100%; cursor: pointer; font-size: 12px; color: #ff0000;"/>
		<input class="search" type="hidden" id="bisProjectIdFact" name="bisProjectId" value="${bisProjectId}"/>
	</div>
	<div style="font-size:12px;width: 37px; margin-left: 28px; padding: 0pt 0pt 0pt 0px; float: left;">业态：</div>
	<div style="width: 70px; margin-top: 4px;height:16px; padding: 0pt 0pt 0pt 0px; float: left; 	">
			<s:select cssStyle="width:100%;" style="height:19px;" 
			validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapContBigTypeNew()" 
			listKey="key" listValue="value" name="layOutCd" id="layOutCd" 
			onchange="changlayOutDetail1();" required="true"></s:select>
	</div> --%>
            <div style="float:right; height:26px; margin-right:5px; margin-top:6px;">
                <div class="btn_green" onclick="clkFullScreen('${bisProjectId}');">全屏</div>
            </div>
</div>

<div class="bis_search" id="main_search_div"  >
<!-- 3实收-->
	<table class="tb_search" style="    margin-left: 5px;">
		<col />
		<tr class="no_advances_dime">
			
			<td align="right" class="flat_layout" style="display:none;" layout="flat" width="40">楼层：</td>
			<td layout="flat" class="flat_layout" style="display:none;" width="70">
				<input style="width:100px;height:16px;"  id="floorNum" name="floorNum"/>
			</td>
			<td align="right" id="detailLabel" width="40">商铺：</td>
			<td  width="100"  >
				<input style="width:100px;height:16px;color:#ccc" title="" id="storeNo" name="storeNo"  onfocus="clearInput(this);" value="搜索商铺"/>
			</td>
			<td align="right" id="detailLabel" width="40">商家：</td>
			<td  width="100"  >
				<input style="width:100px;height:16px;color:#ccc" title="" id="nameShop" name="nameShop" onfocus="clearInput(this);" value="搜索商家"/>
			</td>
			<td align="right" width="30">年：</td>
			<td width="60">
				<s:select   list="@com.hhz.ump.util.DictMapUtil@getMapYear()" listKey="key" listValue="value" name="factYear" id="year" ></s:select>
			</td>
			<td align="right" width="30">月：</td>
			<td width="50">
				<s:select cssStyle="width:100%;" validate="required" list="@com.hhz.ump.util.DictMapUtil@getMapMonth()" listKey="key" listValue="value" name="factMonth" id="month"></s:select>
			</td>
			<td style="padding-left: 5px;">
				<input id="btn_search" type="button" style="width:65px" class="btn_blue" onclick="submitPayNoti();" value="搜索"/>
				<input id="btn_export" type="button" style="width:65px" class="btn_green" onclick="exportPayNoti();" value="导出数据"/>
			</td>
			
		
		</tr>
	</table>
</div>
<div class="bis_search" id="main_search_div1" style="height:30px;background:white;border:0px;    margin-bottom: 9px;">
	<table style="width:100%">
		<tr>
			<td colspan="12">
				<ul id="bis_rpt"  style="margin-left: 4px;list-style-type:none;">
					<li class="bis_fact_unclick" value="2" id="must"  >应收明细</li>
					<li class="bis_fact_unclick" value="3" id="overdue"  >欠费明细</li>
					<li class="bis_fact_unclick" value="1" id="fact" >收费历史记录</li>
					<li class="bis_fact_unclick" value="4" id="advances" >预收明细</li>
					<!--<li   value="5" id="payNoti" >缴费通知</li>-->
					<li class="bis_fact_unclick" value="6" id="payIncome" >营业外收入</li>
					<input type="hidden" id="dimension" name="dimension" value="${dimension }"/>
				</ul>
							<span id="pageHtml" style="margin-left:10px;"></span>
				<span style="margin-top:8px;margin-left:20px;float:left ;color:red;display:none;line-height:20px;height:20px;" id="factoptip"></span>	
			</td>
		</tr>
	</table>
</div>

<div id="seniorSearchDiv" style="position:absolute; width:650px; height:150px; top:31px; left:8px; display:none; background-color:#fff; border:1px solid #000; padding:10px 20px 10px 0px; z-index:8;">
	<table class="tb_search" cellpadding="0" cellspacing="0">
		
		
	</table>
</div>


</div>
</form>
<div id="addContent" style="display:none;clear:both;height:150px;margin:0px;padding:5px;border-bottom:1px solid #dddbdc;background:#f7f7f7;">
</div>
			
<div id="welcom" style="clear:both;height:30px;width:80%">
	<div style="color:#6BAD42;font-size:16px;font-weight:bold;width:auto;margin-top:200px;text-align:center;">
		请选择搜索条件
	</div>
</div>
<div id="detailPanel" style="clear:both;width:100%">
	<div id="detailFor" style="clear:both;">
	</div>
</div>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.fact.js" ></script>
<script type="text/javascript">
var uiid = '<%=SpringSecurityUtils.getCurrentUiid()%>';
layOutCdStore='<%=DictContants.BIS_CONT_TYPE_STORE%>';
layOutCdFlat='<%=DictContants.BIS_CONT_TYPE_FLAT%>';
layOutCdMulti='<%=DictContants.BIS_CONT_TYPE_MULTI%>';
chargeType02='14';
chargeType03='12';
chargeType38='13';
var currProjectId = '${bisProjectId}';
var currProjectName = '${bisProjectName}';
var bisTenantId = '${bisTenantId}';
var bisTenantName = '${currDetailName}';
var dimension = '${dimension}';
var year = '${factYear}';
$(function(){
	var year = getCurDate().substr(0,4);
	var month = getCurDate().substr(5,2);
	$('#year').val(year);
	$('#month').val(month);
	$('#bisProjectId').val(currProjectId);
	$('#payNoti').addClass('bis_fact_click');
	init();
});
</script>
</body>
</html>