<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>
<div style="height:40px;margin-left:50px;">
	<div class="btn_new btn_save_new" style="float:left;margin: 10px 0 0 10px;" onclick="save();">保存</div>
	<div class="btn_new btn_del_new" style="float:left;margin: 10px 0 0 10px;" onclick="del();">删除</div>
	<%--
	<div class="btn_green_55_20" style="float:left;margin: 10px 0 0 10px;" onclick="closeTab();">关闭</div>
	 --%>
</div>
<div style="margin-bottom: 10px;">
	<form action="${ctx}/oa/oa-email-group!save.action" method="post" id="groupForm">
		<s:hidden name="id" id="oaEmailGroupId"></s:hidden>
		<table>
			<tr style="height:30px;">
				<td width="60" align="center">组名</td>
				<td><input type="text" style="width:300px;" name="groupName" value="${groupName}"/></td>
			</tr>
			<tr style="height:30px;">
				<td width="60" align="center">排序</td>
				<td><input type="text" style="width:300px;" name="dispOrderNo" value="${dispOrderNo}"/></td>
			</tr>
			<tr>
				<td align="center">成员</td>
				<td>
					<textarea readonly="readonly" id="groupUserNames" name="groupMemberNames" style="cursor:pointer;border:none;font-size:12px;padding:5px;background-color:#E4E7EC; width:300px;height:200px;"><s:property value='groupMemberNames' /></textarea>
					<input type="hidden" id="groupUserCds" name="groupMemberIds" value="<s:property value='groupMemberIds' />"/>
				</td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
$('#groupUserNames').userSelect({
	muti:true
});
</script>