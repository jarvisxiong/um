<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<div style="margin:0px 2px; ">
	<div id="importTip" style="color:red;display: none;">*红色字体的会议申请请优先处理,如有其它会议冲突,请协调！</div>
	<table class="showTable" >
		<tr class="header">
			<th style="width:120px;" width="120px" >会议室</th>
			<th width="80px" >会议日期</th>
			<th width="80px" >开始时间</th>
			<th width="80px" >结束时间</th>
			<th align="left" style="padding:0 4px;">会议主题</th>
			<th width="80px" >申请人</th>
			<th width="120px" >申请时间</th>
			<th width="100px" >操作</th>
		</tr>
		<s:if test="page.result.size == 0">
		<tr>
			<td colspan="8"><div class="noResult" style="padding: 10px 0;">没有会议室预定申请记录！</div></td>
		</tr>
		</s:if>
		<tbody>
		<s:iterator value="page.result" status="status">
			<%if(JspUtil.isBossInMeeting(JspUtil.findString("compere"),JspUtil.findString("participators"),"0")||JspUtil.isBossInMeeting(JspUtil.findString("compere"),JspUtil.findString("participators"),"1")){ %>
				<tr class="mainTr" style="color:red;">
				<script type="text/javascript">
					$("#importTip").show();
				</script>
			<%}else{ %>
				<tr class="mainTr">
			<%} %>
			<td  class="ellipsisDiv" style="padding-left:8px;" title="<%=CodeNameUtil.getDictNameByCds(DictContants.OA_MEETING_ROOM,JspUtil.findString("roomId")) %><%=CodeNameUtil.getDictNameByCds(DictContants.OA_MEETING_ROOM_SHC,JspUtil.findString("roomId")) %>"> 
				<%=CodeNameUtil.getDictNameByCds(DictContants.OA_MEETING_ROOM,JspUtil.findString("roomId")) %>
				<%=CodeNameUtil.getDictNameByCds(DictContants.OA_MEETING_ROOM_SHC,JspUtil.findString("roomId")) %>
			</td>
			<td ><s:date name="beginTime" format="yyyy-MM-dd"/></td>
			<td ><s:date name="beginTime" format="HH:mm"/></td>
			<td ><s:date name="endTime" format="HH:mm"/></td>
			<td align="left" style="padding:0 4px;">
			<div class="ellipsisDiv">${subject}</div>
			</td>
			<td  ><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("creator")) %></td>
			<td  ><s:date name="createdDate" format="yyyy-MM-dd HH:mm"/></td>
			<td  style="text-decoration: underline;cursor: pointer;" >
				<span onclick="assign('${oaMeetingRoomResId}')" style="margin-right: 2px; ">分配会议室</span> 
				<span onclick="delMeeting('${oaMeetingRoomResId}')">删除</span> 
			</td>
		</tr>
		</s:iterator>
		</tbody>
	</table>
</div>


