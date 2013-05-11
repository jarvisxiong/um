<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>


<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<fieldset>
<div style="height: 35px;line-height:35px;background-color: #eeeeee;padding-left: 10px;">
	<div style="float:left;font-weight: bold;margin-right: 10px;font-size: 14px;color:#316685;">已预约列表</div>
	<div style="float: left; display:inline;">
		<span style="margin-left:15px;margin-right: 5px;">预约日期</span><input class="date" type="text" style="color:red;width:120px;" id="currMeetingDay" value="${currDay}" onfocus="WdatePicker({onpicked:function(dp){changeMeetingDate(dp);}})"/>
	</div>
	<div style="float:left;font-weight: bold;padding-left: 30px;cursor: pointer;">
		<span onclick="refreshResInfo('${prevDay}')" class="arrow_l_gray">前一天</span>
	</div>
	<div style="float:left;width:30px;">
		&nbsp;
	</div>
	<div style="float:left;font-weight: bold;padding-right: 5px;cursor: pointer;">
		<span onclick="refreshResInfo('${nextDay}')" class="arrow_r_gray">后一天</span>
	</div>
</div>
<div style="margin:0px 2px;clear: both;">
	<table class="showTable"  id="resRoomInfoTable">
		<thead>
			<tr class="header">
				<th width="120px" style="padding-left:8px;">&nbsp;</th>
				<th width="120px">来源</th>
				<th width="80px">开始时间</th>
				<th width="80px">结束时间</th>
				<th align="left" style="padding:0 4px;">主题</th>
				<th width="80px">预约人</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="resMap" id="column" status="status">   
			<s:set name="total" value="#column.value.size"/>
			<s:if test="#total == 0">
				<s:if test='#status.odd==true'>
				<tr class="trGray">
				</s:if>
				<s:else>
				<tr class="mainTr">
				</s:else>
					<td  style="font-weight: bold;border-right:1px solid #dbdbdb;padding-left:8px; ">
						<label><p:code2name mapCodeName="chairmanTypeMap" value="#column.key"/></label>
					</td>
					<td colspan="5" >目前没有人预约</td>
				</tr>
			</s:if>
			<s:iterator value="#column.value" status="s">
				<s:if test='#status.odd==true'>
				<tr class="trGray">
				</s:if>
				<s:else>
				<tr class="mainTr">
				</s:else> 
					<s:if test="#s.first">
						<td  rowspan="${total}" style="font-weight: bold;border-right:1px solid #dbdbdb;padding-left:8px;">
						 	<label><p:code2name mapCodeName="chairmanTypeMap" value="#column.key"/></LABEL>	
						</td>
					</s:if>   
					<td>
						<s:if test="sourceType == 0"><font color="#000">预约</font></s:if>
						<s:else><font color="#316685">会议室预定</font></s:else>
					</td>
					<td  style="color:red;"><s:date name="beginTime" format="HH:mm" /></td>   
					<td  style="color:red;"><s:date name="endTime" format="HH:mm" /></td>
					<td align="left" style="padding:0 4px;cursor: pointer;" class="ellipsisDiv" >
					<!-- 预约总裁权限 -->
					<s:if test="sourceType == 0">
						<% if(JspUtil.hasCEOResRole(JspUtil.findString("participators"))){
						%>
							<span title="${subject}" style="text-decoration: underline;" onclick="showMeetingInfo('${dataId}')">${subject}</span>
						<%}else{%>
							<span class="red">该会议主题已经隐藏</span>
						<% }%>
					</s:if>
					<!-- 会议室预定权限 -->
					<s:if test="sourceType == 1">
						<s:if test="encryptFlg == 1">
							<% if(JspUtil.indexOf(JspUtil.findString("participators"))){
							%>
								<span title="${subject}" onclick="showMeetingRoomInfo('${dataId}')">${subject}</span>
							<%}else{%>
								<security:authorize ifAnyGranted="A_MEETING_ROOM_ADMIN">
									<span onclick="showMeetingRoomInfo('${dataId}')" title="${subject}">${subject}</span>
								</security:authorize>
								<security:authorize ifNotGranted="A_MEETING_ROOM_ADMIN">
									<span onclick="showMeetingRoomInfo('${dataId}')" class="red">该会议主题已经隐藏</span>
								</security:authorize>
							<% }%>
						</s:if>
						<s:else>
							<span onclick="showMeetingRoomInfo('${dataId}')" title="${subject}">${subject}</span>
						</s:else>
					</s:if>	
					</td>   
					<td ><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("applicant")) %></td>   
				</tr>   
			</s:iterator>   
		</s:iterator>
		</tbody>
	</table>
</div>
</fieldset>
