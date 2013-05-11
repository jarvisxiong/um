<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="mailBoxTitle">
	 <b>写邮件</b> 
</div> 
<table class="commonTable" style="width:99%">
	<tr>
		<td width="80">收件人</td>
		<td>
			<textarea class="mailTextarea"></textarea><input type="button" value="选择">
			<br/><a>添加抄送人</a>
		</td>
	</tr>
	<tr>
		<td>抄送人</td>
		<td><input type="text" style="width:500px"/><input type="button" value="选择"></td>
	</tr>
	<tr>
		<td>主题</td>
		<td><input type="text" style="width:500px"/></td>
	</tr>
	<tr>
		<td>邮件内容</td>
		<td><textarea style="width:98%;height:100px;"></textarea></td>
	</tr>
	<tr>
		<td>附件</td>
		<td><a>添加附件</a></td>
	</tr>
	<tr>
		<td align="center" colspan="2"><input type="button" value="立即发送">&nbsp;&nbsp;&nbsp;<input type="button" value="存入草稿箱"></td>
	</tr>
</table>