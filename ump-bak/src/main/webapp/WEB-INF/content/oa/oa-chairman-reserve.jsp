<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<div style="margin:0px 2px; ">
	<table class="showTable" >
		<tr class="header">
			<th width="80px" >日期</th>
			<th width="80px" >开始时间</th>
			<th width="80px" >结束时间</th>
			<th align="left" style="padding:0 4px;">主题</th>
			<th width="80px" >申请人</th>
			<th width="120px" >申请时间</th>
			<th width="100px" >操作</th>
		</tr>
		<s:if test="page.result.size == 0 && pageMeetingRoom.result.size == 0 ">
		<tr>
			<td colspan="7"><div class="noResult" style="padding: 10px 0;">没有人提交预约申请！</div></td>
		</tr>
		</s:if>
		<tbody>
		<s:iterator value="page.result" status="status">
			<tr class="mainTr">
			<td ><s:date name="beginTime" format="yyyy-MM-dd"/></td>
			<td ><s:date name="beginTime" format="HH:mm"/></td>
			<td ><s:date name="endTime" format="HH:mm"/></td>
			<td align="left" style="padding:0 4px;">
			<div class="ellipsisDiv">${subject}</div>
			</td>
			<td  ><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %></td>
			<td  ><s:date name="createdDate" format="yyyy-MM-dd HH:mm"/></td>
			<td  style="text-decoration: underline;cursor: pointer;" >
				<span onclick="assign('${oaChairmanReserveId}')" style="margin-right: 2px; ">审核</span> 
				<span onclick="delMeeting('${oaChairmanReserveId}')">删除</span> 
			</td>
		</tr>
		</s:iterator>
		<s:iterator value="pageMeetingRoom.result" status="status">
			<tr class="mainTr" style="background-color: #F3F9FF">
			<td ><s:date name="beginTime" format="yyyy-MM-dd"/></td>
			<td ><s:date name="beginTime" format="HH:mm"/></td>
			<td ><s:date name="endTime" format="HH:mm"/></td>
			<td align="left" style="padding:0 4px;">
			<div class="ellipsisDiv">${subject}</div>
			</td>
			<td  ><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %></td>
			<td  ><s:date name="createdDate" format="yyyy-MM-dd HH:mm"/></td>
			<td  style="text-decoration: underline;cursor: pointer;" >
				<span onclick="assignMeetingRoom('${oaMeetingRoomResId}')" style="margin-right: 2px; ">审核</span> 
				<span onclick="delMeetingRoomRes('${oaMeetingRoomResId}')">删除</span> 
			</td>
		</tr>
		</s:iterator>
		</tbody>
	</table>
</div>


