<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<div style="border: 1px solid  #CCCCCC;margin-left: 5px;">
<table cellpadding="0" cellspacing="0" border="0" style="min-width:300px;margin: 5px 10px;">
	<col width="60px" />
	<col />
	<tr>
		<td style="text-align: center;">请选择</td>
		<td>机构名称</td>
	</tr>
	<s:iterator value="orgList" var="org">
		<tr>
			<td style="text-align: center;">
				<input type="checkbox" relId="${org.dictCd}" />				
			</td>
			<td style="text-align: left;padding-top:3px">
				${org.dictName}
			</td> 
		</tr>
	</s:iterator>
</table>
</div>
