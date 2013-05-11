<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.utils.EncodeUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<meta http-equiv="Content-Type" content="text/html" />
<title>附件显示</title>
<script language="javascript">
	 function  isImage(fileName) {
		$.post("${ctx}/app/app-attachment!isImage.action", {fileName : fileName}, function(result) {
			if (result) {
				$("#main_" + dataId).remove();
				$("#detail_" + dataId).remove();
			}
		});
	}
	function download(dom){
		$(dom).parents('form').submit();
		//$('#downloadForm').submit();
	}
</script>
</head>

<body>
<s:if test="page.totalCount > 0">
<div >
	<fieldset>
	    <legend><s:text name="common.attachment" /></legend>
	    <div>
			<table width="100%">
		    <s:iterator value="page.result">
		    <tr><td width="80%">
			<s:url id="down" action="download" namespace="/app">
		  	  <s:param name="fileName">${fileName}</s:param>
		  	  <s:param name="id">${appAttachFileId}</s:param>
		  	  <s:param name="bizModuleCd">${bizModuleCd}</s:param>
		  	  <s:param name="filterType">${filterType}</s:param>
			</s:url>
			<a href="${down}" >${realFileName}</a>
		    </td>
		    <td width="20%"><s:property value="createdDate" /></td>
		    </tr>
		    </s:iterator>
		    </table>
		</div>
	</fieldset>
	<div>
	<fieldset>
	    <legend><s:text name="appAttachment.imageView" /></legend>
	    <s:iterator value="page.result">
			<c:set var="split">.</c:set>
			<c:set var="suffix">${fn:substringAfter(fileName, split)}</c:set>
			<c:set var="suffix_lower">${fn:toLowerCase(suffix)}</c:set>
			<c:choose>
				<c:when test="${fn:contains(allImage, suffix_lower)}">
					<s:url id="downSmall" action="show" namespace="/app">
						<s:param name="fileName">${smallPicName}</s:param>
						<s:param name="id">${appAttachFileId}</s:param>
						<s:param name="bizModuleCd">${bizModuleCd}</s:param>
						<s:param name="filterType">${filterType}</s:param>
					</s:url>
					<s:url id="down" action="show" namespace="/app">
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="id">${appAttachFileId}</s:param>
						<s:param name="bizModuleCd">${bizModuleCd}</s:param>
						<s:param name="filterType">${filterType}</s:param>
					</s:url>
					<div style="height:100px;width:140px;float:left;">
						<a target="_blank" href="${down}" title="${realFileName}" style="text-decoration: border: none;" >
							<img src="${downSmall}" title="${realFileName}" style="cursor: pointer;height:100px;width:133px;"  alt="${realFileName}" /> 
						</a>
					</div>
			</c:when>
			</c:choose>
	    </s:iterator>
	 </fieldset>
	</div>
</div>
<div class="hengxian"></div>
</s:if>
</body>
</html>
