<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>附件管理</title>
	<%@ include file="/common/global.jsp" %>
	<meta http-equiv="Content-Type" content="text/html" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/content.css" />

</head>
<body>
	<div>
	    <table align="left" width="100%">
		    <s:iterator value="page.result">
			    <tr>
				    <td width="60%">
					    <s:url id="down" action="download" namespace="/app">
					  	  <s:param name="fileName">${fileName}</s:param>
					  	  <s:param name="realFileName">${realFileName}</s:param>
					  	  <s:param name="bizModuleCd">${bizModuleCd}</s:param>
					  	  <s:param name="filterType">${filterType}</s:param>
						  <s:param name="id">${appAttachFileId}</s:param>
						</s:url>
						<a href="${down}">${realFileName}</a>
				    </td>
			    <td width="20%">
		    		<s:property value="createdDate" />
			    </td>
			    <td width="20%">
		    		 <%= CodeNameUtil.getUserNameByCd(JspUtil.findString("creator"))%>
			    </td>
			    </tr>
		    </s:iterator>
	    </table>
	    <s:if test="page.result.size() == 0">
	    	<div style="font-size: 13px;font-weight: bold;color:red;" align="center">没有可以查看的附件</div>
	    </s:if>
	</div>
</body>
</html>