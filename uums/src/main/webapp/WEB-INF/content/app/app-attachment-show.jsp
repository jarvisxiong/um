<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/common/global.jsp" %>
<meta http-equiv="Content-Type" content="text/html" />
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

<s:if test="page.totalCount > 0">
<div >
	<fieldset>
	    <legend><s:text name="common.attachment" /></legend>
	    <div>
			<table width="100%">
		    <s:iterator value="page.result">
		    <tr><td width="80%">
		    <s:url id="down" action="download" namespace="/app">
		  	  <s:param name="id">${appAttachFileId}</s:param>
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
		<s:set var="haveImage">false</s:set>
		<fieldset id="imageView">
		    <legend>图片预览</legend>
		    <s:iterator value="page.result">
				<c:set var="split">.</c:set>
				<c:set var="suffix">${fn:substringAfter(fileName, split)}</c:set>
				<c:set var="suffix_lower">${fn:toLowerCase(suffix)}</c:set>
				<c:choose>
					<c:when test="${fn:contains(allImage, suffix_lower)}">
						<s:set var="haveImage">true</s:set>
						<a target="_blank" href="${ctx}/${filePath}/${fileName}" title="${realFileName}">
							<img src="${ctx}/${filePath}/${smallPicName}" height="100" width="133"	style="cursor: pointer;" alt="${realFileName}"/>
						</a>
					</c:when>
				</c:choose>
		    </s:iterator>
		 </fieldset>
	</div>
</div>
<div class="hengxian"></div>
<script language="javascript">
	var haveImage=eval('${haveImage}');
	if (!haveImage){
		$("#imageView").remove();
	}
</script>
</s:if>
