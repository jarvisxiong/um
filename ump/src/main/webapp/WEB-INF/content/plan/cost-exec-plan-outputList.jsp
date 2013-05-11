<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>


<s:if test="planDetailOutput.size == 0">
	<div style="color: #6BAD42; font-weight: bold; font-size: 16px; height: 120px; line-height: 120px; text-align: center;">
		暂无输出成果
	</div>
</s:if>
<s:else>
	<table cellpadding="0" cellspacing="0" class="planDetailOutput">
		<s:iterator value="planDetailOutput">
			<tr>
				<td>
					<s:url id="downUrl" action="show" namespace="/app">
						<s:param name="fileName">${fileName}</s:param>
						<s:param name="realFileName">${fileName}</s:param>
						<s:param name="bizModuleCd">costPlan</s:param>
						<s:param name="operator">inline;</s:param>
						<s:param name="id">${appAttachFileId}</s:param>
					</s:url>
					<a style="text-decoration: underline; color: #5A5A5A;" 
						title="点击下载附件" onclick="window.open('${downUrl}'); return false;" 
						href="#">${realFileName}</a> &nbsp;&nbsp;
				</td>
				<s:if test="superAdmin">
					<td width="40" style="text-align: center;">
						<img style="cursor: pointer;" title="删除附件"  src="${ctx}/pics/note/note_del.gif"  
							onclick="deleteAttachment('${appAttachFileId}');" />
					</td>
				</s:if>
			</tr>
		</s:iterator>
	</table>
</s:else>
					