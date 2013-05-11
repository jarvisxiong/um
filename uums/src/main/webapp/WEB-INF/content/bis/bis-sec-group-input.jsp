<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<fieldset>
		<form action="${ctx}/sec/sec-group!save.action" method="post" id="inputForm">
			<s:hidden name="id" id="id" key="secGroupId"></s:hidden>
			<s:hidden name="secGroupId" id="secGroupId" key="secGroupId"></s:hidden>
			<table style="text-align: left;">
				<tr>
					<td><s:text name="uaapSecGroup.tagTypeCd"/></td>
					<td valign="top"><s:select list="mapUaapTagType" listKey="key" listValue="value" name="tagTypeCd" id="tagTypeCd" value="tagTypeCd"/></td>
				</tr> 
				<tr>
					<td style="width:75px;"><s:text name="uaapSecGroup.groupName"/></td>
					<td style="width:120px;"><input style="width:95%" type="text" id="groupName"  name="groupName" value="${groupName}"/></td>
				</tr> 
				<tr>
					<td><s:text name="uaapSecGroup.groupBizCd"/></td>
					<td valign="top"><input style="width:95%" type="text" id="groupBizCd"  name="groupBizCd" value="${groupBizCd}"/></td>
				</tr> 
				<tr>
					<td><s:text name="uaapSecGroup.dispOrderNo"/></td>
					<td valign="top"><input style="width:95%" type="text" id="dispOrderNo"  name="dispOrderNo" value="${dispOrderNo}"/></td>
				</tr> 
				<tr>
					<td><s:text name="uaapSecGroup.fixedFlg"/></td>
					<td valign="top"><s:select list="mapFixedFlg" listKey="key" listValue="value" name="fixedFlg" id="mapFixedFlg" value="mapFixedFlg"/></td>
				</tr> 
				<tr>
					<td><s:text name="uaapSecGroup.remark"/></td>
					<td valign="top">
						<textarea style="with:60px;height:200px;" id="remark"  name="remark">${remark}</textarea>
					</td>
				</tr> 
			</table>
		</form>
		
	<div style="height:40px;">
		<button style="float:left;margin: 10px 0 0 10px;" onclick="mysubmit();">保存</button>
		<%--
		<button style="float:left;margin: 10px 0 0 10px;" onclick="del();">删除</button>
		 --%>
	</div>
</fieldset>