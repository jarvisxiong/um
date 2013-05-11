<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>  

	<table style="text-align: left;">
		<tr>
			<td valign="top"><s:text name="uaapSecGroup.tagTypeCd"/>:</td>
			<td style="width:80px;"><p:code2name mapCodeName="mapUaapTagType" value="selectedGroup.tagTypeCd"/> </td>
		</tr> 
		<tr>
			<td valign="top"><s:text name="uaapSecGroup.groupName"/>:</td>
			<td style="width:80px;">${selectedGroup.groupName}</td>
		</tr> 
		<tr>
			<td valign="top"><s:text name="uaapSecGroup.groupBizCd"/>:</td>
			<td>${selectedGroup.groupBizCd}</td>
		</tr>
		<tr>
			<td valign="top"><s:text name="uaapSecGroup.dispOrderNo"/>:</td>
			<td>${selectedGroup.dispOrderNo}</td>
		</tr>	
		<tr>
			<td><s:text name="uaapSecGroup.fixedFlg"/>:</td>
			<td><p:code2name mapCodeName="mapFixedFlg" value="selectedGroup.fixedFlg==null?0:selectedGroup.fixedFlg"/></td>
		</tr> 
		<tr>
			<td valign="top"><s:text name="uaapSecGroup.remark"/>:</td>
			<td>${selectedGroup.remark}</td>
		</tr>	
		<tr>
			<td valign="top"><s:text name="uaapSecGroup.updator"/>:</td>
			<td>${selectedGroup.updator}</td>
		</tr>	
		<tr>
			<td valign="top"><s:text name="uaapSecGroup.updatedDate"/>:</td>
			<td><s:date format="yyyy-MM-dd hh:mm:ss" name="selectedGroup.updatedDate"/> </td>
		</tr>	
	</table>