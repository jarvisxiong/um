<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<style type="text/css">

table.showTable {
	table-layout: fixed;
	width: 100%;
	text-align: left;
	empty-cells:show;
	border-collapse:collapse;
}

table.showTable tr.header th {
	padding-left: 4px;
	font-size: 14px;
	color: #5B6B83;
	height: 20px;
	vertical-align: bottom;
	border-bottom: 1px solid #dbdbdb;
	background:url('../../pics/email/mail_title_line_temphight.jpg') no-repeat right bottom;
	text-align: left;
}

table.showTable tbody tr {
	padding-left: 4px;
	height: 35px;
	line-height: 35px;
	border-bottom: 1px solid #dbdbdb;
}
table.showTable tbody tr:hover {
	color:#0167a2;
}
</style>

<%--
<div>
	<table>
		<s:iterator value="historyList" status="sta">
		<tr>
			<td>申请人:</td>
			<td><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("applyUserCd")) %></td>
			<td>申请时间:</td>
			<td>
		     	<s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss"/>
		    </td>
			<td>延期时间:</td>
			<td>${delayTime}小时</td>
			<td>申请原因:</td>
			<td>${applyReason}</td>
		</tr>
		<tr>
			<td>审核人:</td>
			<td><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("approveUserCd")) %></td>
			<td>审核时间:</td>
			<td>
		     	<s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss"/>
		    </td> 
			<td>审核结果:</td>
			<td><s:if test="approveOptionCd == 0">不同意</s:if>
		     	<s:elseif test="approveOptionCd == 1">同意</s:elseif>
		     	<s:else>-</s:else>
			</td>
			<td>审核意见:</td>
			<td>${approveRemark}</td> 
		</tr>
		<tr>
			<td>确认人:</td>
			<td>企管部</td>
			<td>确认时间:</td>
			<td>
		     	<s:date name="createdDate" format="yyyy-MM-dd HH:mm:ss"/>
		    </td> 
			<td>确认结果:</td>
			<td><s:if test="approveOptionCd == 0">不同意</s:if>
		     	<s:elseif test="approveOptionCd == 1">同意</s:elseif>
		     	<s:else>-</s:else>
			</td>
			<td>确认意见:</td>
			<td>${confirmRemark}</td> 
		</tr>
		</s:iterator>
	</table>
</div>
 --%>

<div style="margin: 10px;overflow-x:hidden;">
	<table cellpadding="0" cellspacing="0" class="showTable" style="width:720px;line-height: 30px;">
		<tr class="header">
		     <th style="width:60px;padding-left:5px;">申请人</th>
		     <th style="width:60px;">申请延期</th>
		     <th style="width:80px;">发起时间</th>
		     <th style="width:80px;">审批人</th>
		     <th style="width:60px;">审核意见</th>
		     <th style="width:80px;">审核时间</th>
		     <th style="width:80px;">确认人</th>
		     <th style="width:60px;">确认意见</th>
		     <th style="width:80px;">确认时间</th>
		</tr>
		<s:iterator value="historyList" status="sta">
		<tr onmouseover="$(this).css({'background-color':'#EEEEEE'});this.style.cursor='pointer'" onmouseout="$(this).css({'background-color':'white'})">
			<td colspan="9">
			<table style="width:100%;">
			     <col style="width:60px;"/>
			     <col style="width:70px;"/>
			     <col style="width:80px;"/>
			     <col style="width:80px;"/>
			     <col style="width:60px;"/>
			     <col style="width:80px;"/>
			     <col style="width:80px;"/>
			     <col style="width:60px;"/>
			     <col style="width:80px;"/>
				<tr>
				     <td style="padding-left:5px;">
				     	<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("applyUserCd")) %>
				     </td>
				     <td>${delayTime}小时</td>
				     <td title="<s:property value="createdDate"/>">
				     	<s:date name="createdDate" format="yyyy/MM/dd"/>
				     </td>
				     <td>
				     	<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("approveUserCd")) %>
				     </td>
				     <td>
				     	<s:if test="approveOptionCd == 0">不同意</s:if>
				     	<s:elseif test="approveOptionCd == 1">同意</s:elseif>
				     	<s:else>-</s:else>
				     </td>
				     <td title="<s:property value="approveDate"/>">
				     	<s:date name="approveDate" format="yyyy/MM/dd"/>
				     </td>
				     <td>
				     	<%=CodeNameUtil.getUserNameByCd(JspUtil.findString("confirmUserCd")) %>
				     </td>
				     <td>
				     	<s:if test="confirmOptionCd == 0">不同意</s:if>
				     	<s:elseif test="confirmOptionCd == 1">同意</s:elseif>
				     	<s:else>-</s:else>
				     </td>
				     <td title="<s:property value="confirmDate"/>">
				     	<s:date name="confirmDate" format="yyyy-MM-dd"/>
				     </td>
				</tr>
				<tr>
					 <td valign="top">申请事由:</td>
					 <td colspan="8" valign="top">
				     	<div style="height:100%;width:100%;word-wrap:break-word;">${applyReason}</div>
				     </td>
				</tr>
				<s:if test="approveRemark != null && approveRemark != ''">
				<tr>
					 <td valign="top">审核意见:</td>
					 <td colspan="8" valign="top">
				     	<div style="height:100%;width:100%;word-wrap:break-word;">${approveRemark}</div>
				     </td>
				</tr>
				</s:if>
				<s:if test="confirmRemark != null && confirmRemark != ''">
				<tr>
					 <td valign="top">确认意见:</td>
					 <td colspan="8" valign="top">
				     	<div style="height:100%;width:100%;word-wrap:break-word;">${confirmRemark}</div>
				     </td>
				</tr>
				</s:if>
			</table>
			</td>
		</tr>
		</s:iterator>
		</tbody>
	</table>
</div>