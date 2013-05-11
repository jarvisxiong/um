<%@page import="com.hhz.ump.util.DateUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<title>批量减免</title>
<script type="text/javascript">
var _ctx = '${ctx}'; 
</script>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/common/common.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/cont/cont.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/customInput/customInput.css"  />
<link rel="stylesheet" type="text/css" href="${ctx}/js/prompt/skin/pd/ymPrompt.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/bis/bis.fact.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/js/loadMask/jquery.loadmask.css"/>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/bis/bis.fact.reduce.js"></script>
<script type="text/javascript" src="${ctx}/js/prompt/ymPrompt.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/ConvertUtil.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jqueryplugin/chilltip.js"></script>
<script type="text/javascript" src="${ctx}/js/datePicker/WdatePicker.js" ></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.pack.js"></script>
<script type="text/javascript" src="${ctx}/js/formValidator/formValidator.js" ></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.select.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/ConverUtil.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<script type="text/javascript" src="${ctx}/js/validate/formatUtil.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.quickSearch.js"></script>
<script type="text/javascript" charset="UTF-8" src="${ctx}/resources/js/loadMask/jquery.loadmask.min.js" ></script>

<style >
table.sup_table input{background-color: #DBE5F1;width:90%
}
table.sup_table select{background-color: #DBE5F1;width:90%
}
table.sup_table textarea{background-color: #DBE5F1;width:90%
}
table.sup_table input:focus{
	background-color: #ffffe1;
}
table.sup_table select:focus{
	background-color: #ffffe1;
}
table.sup_table textarea:focus{
	background-color: #ffffe1;
}
</style>
</head>
<body >
<div id="header">
	<div class="title_bar" >
		<div style="font-weight:bold;padding-left:8px;padding-right:8px; font-size:14px;float:left;">批量减免</div>
		<div id="btnSeniorSearch" class="quickSenior" style="width:27px; padding:0 0 0 70px;float:left;" onclick="showSeniorSearch();">&nbsp;</div>
		
            <div style="float:right; height:26px; margin-right:5px; margin-top:6px;">
                <div class="btn_green" onclick="clkFullScreen('${bisProjectId}');">全屏</div>
            </div>
	</div>
	<form action="${ctx }/bis/bis-fact-reduce!list.action" id="searchForm" method="post">
	<div class="title_bar" >
		<div style="font-size:12px;width: 37px; height:16px;margin-left: 8px; padding: 0pt; float: left;">项目：</div>
		<div style="width: 100px; margin-top: 4px; padding: 0pt ; float: left; 	">
			<input type="text" id="bisProjectName" name="bisProjectName" value="${bisProjectName}" style="  height: 16px;width:100%; cursor: pointer; font-size: 12px; color: #ff0000;"/>
			<input class="search" type="hidden" id="bisProjectIdFact" name="bisProjectId" value="${bisProjectId}"/>
		</div>
		<div style="font-size:12px;width: 37px; height:16px;margin-left: 8px; padding: 0pt; float: left;">商家：</div>
		<div style=" float: left; 	">
			<input  style="width: 60px; margin-top: 4px; padding: 0pt ;color:#ccc " type="text" <s:if test="filter_LIKES_shopName==null || filter_LIKES_shopName==''">value="搜索商家"</s:if><s:else>value="${filter_LIKES_shopName }"</s:else>  name="filter_LIKES_shopName" id="filter_LIKES_shopName" onfocus="clearInput(this,'搜索商家')" searchText="搜索商家"/>
		</div>
		<div style="font-size:12px;width: 37px; height:16px;margin-left: 8px; padding: 0pt; float: left;">网批：</div>
		<div style=" float: left; 	">
			<input  style="width: 60px; margin-top: 4px; padding: 0pt ;color:#ccc " type="text" <s:if test="resApproveInfoCdNo==null || resApproveInfoCdNo==''">value="搜索网批"</s:if><s:else>value="${resApproveInfoCdNo }"</s:else>  name="resApproveInfoCdNo" id="resApproveInfoCdNo" onfocus="clearInput(this,'搜索网批')" searchText="搜索网批"/>
		</div>
		<%-- <div style="font-size:12px;width: 37px; height:16px;margin-left: 8px; padding: 0pt; float: left;">合同：</div>
		<div style=" float: left; 	">
			<input  style="width: 60px; margin-top: 4px; padding: 0pt ;color:#ccc " type="text" value="搜索合同"  name="filter_LIKES_contNo" id="filter_LIKES_contNo" onfocus="clearInput(this,'搜索合同')"/>
		</div>--%>
		<div style=" float: left; ;margin-left: 5px; vertical-align: bottom;">
			<input  style="width: 65px; margin-left: -3px; padding: 0pt ;" type="button" value="搜索" class="btn_blue" onclick="searchReduce();" />
		</div>
		<div style=" float: left; ;margin-left: 8px;	">
		<input  style="width: 65px; margin-left: -3px; padding: 0pt ;" type="button" value="新增" class="btn_blue" onclick="newReduce();" />
		</div>
		<div id="tip" style="display:none"></div>
	</div>
	</form>
	
</div>
<div id="seniorSearchDiv"></div>
<div id="list_div"><%@ include file="bis-fact-reduce-list.jsp"%></div>
<div id="addContent" style="display:none"></div>
<div id="welcom" style="clear:both;height:30px;width:80%">
	<div style="color:#6BAD42;font-size:16px;font-weight:bold;width:auto;margin-top:200px;text-align:center;">
		请选择搜索条件
	</div>
</div>
	
<script type="text/javascript">

</script>
</body>
</html>
