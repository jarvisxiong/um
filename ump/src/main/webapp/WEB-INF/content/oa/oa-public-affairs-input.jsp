<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<form action="${ctx}/oa/oa-public-affairs!save.action" id="postForm" method="post">
	<input type="hidden" name="id" value="${oaPublicAffairsId}"/>
	<input type="hidden" name="recordVersion" value="${recordVersion}"/>
	<table width="100%" >
		<tr style="height:30px;">
			<td width="50" align="center">主题</td>
			<td><input id="postSubject" name="subject" value="${subject}" type="text" style="width:530px;"/></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
			<s:textarea id="postEditorDiv" name="content" cssStyle="width:580px;height:180px;">
			</s:textarea>
		</tr>
		
		<tr>
			<td colspan="2" align="center">
				<button type="button" class="btn_green_55_20" style="margin-right:10px;" onclick="savePost()">保存</button>
				<button type="button" class="btn_green_55_20" onclick="ymPrompt.close();">关闭</button>
			</td>
		</tr>
	</table> 
</form>

