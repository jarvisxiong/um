<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/plan/cost-ctrl.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/TreePanel.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/css/desk/thickbox.css" />
<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/bis/bis-manage.css" />
<style type="text/css">
<!--
.addFirst {
	background-color: #6eb1ce;
	color: #fff;
}
.li_store_layoutcd{    list-style: none outside none;text-decoration:underline;cursor: pointer;}
area{
	cursor:inherit;
}
.cur_ul{text-align:center;margin:10px auto 0;width:360px;font-size:14px;}
.cur_ul li{float:left;list-style:none;width:120px;}
.cur_ul li span{height:15px;width:15px;display:block;float:left;}
.floorVirUl{text-align:left;margin:10px auto 0;font-size:14px;}
.floorVirli_click{
	background-color:#006FB6;	color:#fff;border:1px solid #006FB6;
}
.floorVirli{
	color: #363636;border:1px solid #006FB6;
}
.floorVirUl li{list-style:none;border:1px solid #ccc;}
.floorVirUl li:hover{
	background-color:#006FB6;	color:#fff;
}
.floorVirli_click,.floorVirUl li{
	float:left;
	height:22px;
	line-height:22px;
	padding:0 8px;
	margin: 4px 2.5px;
	cursor:pointer;}
-->
</style>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/userChoose.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/TreePanel.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis-project.js"></script>
<script type="text/javascript" src="${ctx}/js/desk/MaskLayer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bisCont.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/resources/js/jqueryplugin/mapper/mapper.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bisTenant.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.ad.tenant.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.project.select.js"></script>
<script type="text/javascript">
</script>

</head>
<body>
	<div class="title_bar"  style="width:100%;background-color:#e4e7ec;font-weight: normal;padding-left: 8px;">
		<font style="font-size: 14px;font-weight: bolder; color:#333333; padding-right: 10px;">广告位详细信息</font>
	</div>
	<div style="margin-left: 8px; margin-right: 8px;">
		
		<input type="hidden" id="bisMultiId" name="bisMultiId" value="${bisMultiId}"/>
		<input type="hidden" id="rentStartDate" name="rentStartDate" value="${rentStartDate}"/>
		<input type="hidden" id="rentEndDate" name="rentEndDate" value="${rentEndDate}"/>
		<div id="adPanel" >
			<%@ include file="bis-tenant-adImage.jsp" %>
		</div>
		<div id="adDetailPanel">
			<%@ include file="bis-tenant-adDetail.jsp" %>
		</div>
		<div style="height:28px;"></div>
		<div id="shopWidthDivs" style="border: 1px solid #cccccc;">
			<div class="title_bar"  style="background-color:#e4e7ec;font-weight: bolder; font-size: 12px;color: #464646; ">
				<span style="padding-left: 8px;">商家收支详情</span>
				年份：<select style="width: 55px;" name="factYear" id="factYear" onchange="loadMultiFeeSec();">
					<option value="2006" <s:if test="nowYear==2006">selected</s:if>>2006</option>
					<option value="2007" <s:if test="nowYear==2007">selected</s:if>>2007</option>
					<option value="2008" <s:if test="nowYear==2008">selected</s:if>>2008</option>
					<option value="2009" <s:if test="nowYear==2009">selected</s:if>>2009</option>
					<option value="2010" <s:if test="nowYear==2010">selected</s:if>>2010</option>
					<option value="2011" <s:if test="nowYear==2011">selected</s:if>>2011</option>
					<option value="2012" <s:if test="nowYear==2012">selected</s:if>>2012</option>
					<option value="2013" <s:if test="nowYear==2013">selected</s:if>>2013</option>
					<option value="2014" <s:if test="nowYear==2014">selected</s:if>>2014</option>
					<option value="2015" <s:if test="nowYear==2015">selected</s:if>>2015</option>
					<option value="2016" <s:if test="nowYear==2016">selected</s:if>>2016</option>
					<option value="2017" <s:if test="nowYear==2017">selected</s:if>>2017</option>
					<option value="2018" <s:if test="nowYear==2018">selected</s:if>>2018</option>
					<option value="2019" <s:if test="nowYear==2019">selected</s:if>>2019</option>
					<option value="2020" <s:if test="nowYear==2020">selected</s:if>>2020</option>
				</select>
				<input type="button" style="width: 90px;" value="查看费用明细" onclick="clickGGFeeDetail();" class="btn_green" />
			</div> 
		</div>
		<div id="bisAdFee" style="position:relative; width:100%; overflow:auto; height:230px;"></div>
		<div style="height:8px;"></div>
		<div style="border: 1px solid #cccccc;">
			<div class="title_bar"  style="background-color:#e4e7ec;font-weight:bold ; padding-left: 8px;font-size: 12px;color: #464646;">
				合同附件<input style="margin-top: 4px;margin-left: 8px;" type="button" value="新增" class="btn_blue" onclick="addBisCont('${bisTenantId}');" />
			</div>
			<div id="tenantContsPage"></div>
		</div>
		<div style="height:30px;"></div>
	</div>
</body></html>