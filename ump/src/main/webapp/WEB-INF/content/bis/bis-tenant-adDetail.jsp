<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp" %>

<%@page import="com.hhz.ump.util.JspUtil"%>
<div style="width:770px; height:178px; ">
	<div style="float:left; height:178px; border-right: 1px solid #ccc; margin:10px; ">
		<table height="168px" border="0">
			<col width="80px"/>
			<col width="230px"/>
			<tr height="28px;">
				<td align="left" class="tdclass" style="font-weight: bold;color: #0167a2;padding-left: 8px;">广告位信息</td>
			</tr>
		    <tr height="28px;">
				<td nowrap="nowrap" align="left" class="tdclass" style="padding-left: 8px;">广告位编号：</td>
				<td nowrap="nowrap" id="td_multiName" class="tdclass">${bisMulti.multiName }</td>
			</tr>
		    <tr height="28px;">
				<td nowrap="nowrap" align="left" class="tdclass" style="padding-left: 8px;">面积：</td>
				<td nowrap="nowrap" id="td_square" class="tdclass">${bisMulti.square }</td>
			</tr>
		    <tr height="28px;">
				<td nowrap="nowrap" align="left" class="tdclass" style="padding-left: 8px;">经营项目：</td>
				<td nowrap="nowrap" id="td_operationProjectCd" class="tdclass">${ bisMulti.operationProjectCd}</td>
			</tr>
		    <tr height="28px;">
				<td nowrap="nowrap" align="left" class="tdclass" style="padding-left: 8px;">等级：</td>
				<td nowrap="nowrap" id="td_multiGrade" class="tdclass">${bisMulti.multiGrade }</td>
			</tr>
		    <tr height="28px;">
				<td nowrap="nowrap" align="left" class="tdclass" style="padding-left: 8px;">执行价格：</td>
				<td nowrap="nowrap" id="td_multiPrice" class="tdclass">${bisMulti.multiPrice }</td>
			</tr>
		</table>
	</div>
</div>
