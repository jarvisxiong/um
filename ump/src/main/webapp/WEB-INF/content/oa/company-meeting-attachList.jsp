<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<table style="table-layout: fixed; width: 100%;">
	<s:iterator value="attachList">
		<tr>
			<td style="padding-left: 10px;border-bottom:1px solid #DDDBDC;">
				<s:url id="preview" action="show" namespace="/app">
					<s:param name="fileName">${fileName}</s:param>
					<s:param name="realFileName">${fileName}</s:param>
					<s:param name="bizModuleCd">oaMeeting</s:param>
					<s:param name="id">${appAttachFileId}</s:param>
					<s:param name="operator">inline;</s:param>
				</s:url>
				
				<a title="${realFileName}" onclick="window.open('${preview}'); return false;" href="#">${realFileName}</a> 
			</td>
			<td width="50" align="center" style="border-bottom:1px solid #DDDBDC;">
				<div class="btn_del_file" title="删除附件" onclick="deleteAttachment('${appAttachFileId}', '${bizEntityId}');"></div>
			</td>
		</tr>
	</s:iterator>
</table>