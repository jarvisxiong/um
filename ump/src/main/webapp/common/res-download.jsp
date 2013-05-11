<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.utils.EncodeUtils"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<meta http-equiv="Content-Type" content="text/html" />
<script src="${ctx}/js/table.js" type="text/javascript"></script>
<title>权责手册下载</title>
<script language="javascript">
</script>

<style type="text/css">
	<!--
	ul li a {
		font-size: 14px;
		color: #0693e3;
		text-decoration: underline;
	}
	legend{
		font-size: 12px;
	}
	-->
	</style>
</head>

<body>
<div >
	<fieldset>
	    <legend>请选择</legend>
	    <div>
		    <ul>
			    <s:url id="downDc" action="download" namespace="/app"  includeParams="none"  >
			  	  <s:param name="fileName">201201201803346vev.xlsx</s:param>
			  	  <s:param name="realFileName">宝龙地产权责手册-20120118.xlsx</s:param>
			  	  <s:param name="bizModuleCd">companyFile</s:param>
				</s:url>
				<li><a href="${downDc}">宝龙地产权责手册</a></li>
				
			    <s:url id="downDc" action="download" namespace="/app"  includeParams="none"  >
			  	  <s:param name="fileName">2011072013193836rP.xlsx</s:param>
			  	  <s:param name="realFileName">宝龙商业权责手册-20110708.xlsx</s:param>
			  	  <s:param name="bizModuleCd">companyFile</s:param>
				</s:url>
				<li><a href="${downDc}">宝龙商业权责手册</a></li>
				
			    <s:url id="downDc" action="download" namespace="/app"  includeParams="none"  >
			  	  <s:param name="fileName">20101230093711du7n.xls</s:param>
			  	  <s:param name="realFileName">宝龙行业权责手册-20101221.xls</s:param>
			  	  <s:param name="bizModuleCd">companyFile</s:param>
				</s:url>
				<li><a href="${downDc}">宝龙行业权责手册</a></li>
				
			    <s:url id="downDc" action="download" namespace="/app"  includeParams="none"  >
			  	  <s:param name="fileName">201109081022379FhX.xlsx</s:param>
			  	  <s:param name="realFileName">宝龙酒店业务权责手册-20110830.xlsx</s:param>
			  	  <s:param name="bizModuleCd">companyFile</s:param>
				</s:url>
				<li><a href="${downDc}">宝龙酒店业务权责手册</a></li>
			</ul>
		</div>
	</fieldset>
	<div>
	</div>
</div>
<div class="hengxian"></div>
</body>
</html>
