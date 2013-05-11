<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>  
<s:if test="page.result.size() > 0">
	<table style="text-align: left;">
		<s:iterator value="page.result" >
		
		<tr>
			<td style="wdith:120px" valign="top">组名称:</td>
			<td>${dictName}</td>
		</tr> 
		<tr>
			<td valign="top">显示序号:</td>
			<td>${dictCd}</td>
		</tr>	
		<tr>
			<td valign="top">备注:</td>
			<td>${remark}</td>
		</tr>	
		<tr>
			<td valign="top">最近更新部门:</td>
			<td>${updatedDeptCd}</td>
		</tr>	
		<tr>
			<td valign="top">最近更新时间:</td>
			<td><s:date format="yyyy-MM-dd hh:mm:ss" name="updatedDate"/> </td>
		</tr>	
		</s:iterator>
	</table>
</s:if>
<s:else>
 	暂未配置标签
</s:else>
