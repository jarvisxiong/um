<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div>
<fieldset>
<s:if test="page.result.size() > 0">
		<table>
			<tr style="background-color:#DBDBDB">
				<th>标签组信息</th>
				<th style="text-align: right;">当前标签:<s:select list="mapUaapTagType" listKey="key" listValue="value" name="tagTypeCd" id="tagTypeCd" onchange="changeTagType(this.value)" value="tmpTagTypeCd"/></th>
			</tr>
			<tr>
				<td colspan="2">
					<table style="width:100%;">
						<tr>
							<th>
								<s:text name="uaapSecGroup.groupName"/>
							</th>
							<th style="text-align: left">
								<s:text name="common.operate"/>
							</th>
						</tr>
						<s:iterator value="page.result" >
						<tr>
							<td title="点击分配组员">
								<div style="cursor:pointer;" id="group_${secGroupId}" onclick="viewGroup('${secGroupId}','${groupName}');">${groupName}</div>
							</td>
							<td style="text-align: left">
								<a href="javascript:viewGroup('${secGroupId}','${groupName}');">分配</a>
								<a href="javascript:editGroup('${secGroupId}')"><s:text name="common.edit"/></a>
									<s:if test="fixedFlg == 1">
									(固化)
									</s:if>
									<s:else>
										<a href="javascript:deleteGroup('${secGroupId}')">
										<s:text name="common.delete"/>
										</a>
									</s:else>
							</td>
						</tr>
						</s:iterator>
					</table>
				</td>
			</tr>
		</table>
</s:if>
<s:else>
 	暂未配置标签
 	<a href="gotoHome()">返回</a>
</s:else>
</fieldset>
</div>
