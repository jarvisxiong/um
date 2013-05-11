<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<ul class="layoutPanel" style="list-style:none; padding-left:8px;" >
	<s:iterator value="page.result" status="s">
	<li id="li_<s:property value="#s.index" />" style="float:left; padding-right:20px; color: #0693e3;"> 
		<s:url id="down" action="download" namespace="/app">
		<s:param name="fileName">${fileName}</s:param>
		<s:param name="realFileName">${realFileName}</s:param>
		<s:param name="bizModuleCd">${bizModuleCd}</s:param>
		<s:param name="filterType">${filterType}</s:param>
		<s:param name="id">${appAttachFileId}</s:param>
		</s:url>
		<a style="" href="${down}">${realFileName}&nbsp;↓</a>
	</li>
	</s:iterator>
</ul>

<script type="text/javascript">
$(".layoutPanel").width($("#overviewInfoDiv").width()-360-50);
</script>