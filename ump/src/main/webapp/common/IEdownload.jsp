<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.springside.modules.utils.EncodeUtils"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/global.jsp" %>
<meta http-equiv="Content-Type" content="text/html" />
<script src="${ctx}/js/table.js" type="text/javascript"></script>
<title>IE8下载</title>
<script language="javascript">
</script>
</head>

<body>
<div >
	<fieldset>
	    <legend>请选择版本</legend>
	    <div>
		    <ul>
				    <s:url id="downXp" action="download" namespace="/app"  includeParams="none"  >
				  	  <s:param name="fileName">IE8-WindowsXP-x86-CHS.exe</s:param>
				  	  <s:param name="realFileName">IE8-WindowsXP-x86-CHS.exe</s:param>
				  	  <s:param name="bizModuleCd">public</s:param>
					</s:url>
					<li><a href="${downXp}">IE8-WindowsXP</a></li>
					
				    <s:url id="downIe9" action="download" namespace="/app"  includeParams="none"  >
				  	  <s:param name="fileName">BOIE9_ZHCN_WIN7.EXE</s:param>
				  	  <s:param name="realFileName">BOIE9_ZHCN_WIN7.EXE</s:param>
				  	  <s:param name="bizModuleCd">public</s:param>
					</s:url>
					<li><a href="${downIe9}">IE9-Windows7</a></li>
					
				    <s:url id="downFF" action="download" namespace="/app"  includeParams="none"  >
				  	  <s:param name="fileName">Firefox-setup.exe</s:param>
				  	  <s:param name="realFileName">Firefox-setup.exe</s:param>
				  	  <s:param name="bizModuleCd">public</s:param>
					</s:url>
					<li><a href="http://www.google.cn/chrome" target="_blank">Google浏览器</a></li>
					<li><a href="${downFF}">火狐浏览器</a></li>
			</ul>
		</div>
	</fieldset>
	<div>
	</div>
</div>
<div class="hengxian"></div>
</body>
</html>
