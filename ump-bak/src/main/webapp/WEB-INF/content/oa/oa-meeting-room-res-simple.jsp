<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>

<div style="margin:0px 2px;">
	<table class="showTable" border="1" bordercolor="#BFC4C8" id="resRoomInfoTable">
		<thead>
			<tr style="display: none">
				<th width="100px"></th>
				<th width="40px" ></th>
				<th width="40px"></th>
			</tr>
			<tr>
				<th align="center" style="padding-left:8px;">会议室</th>
				<th align="center">开始</th>
				<th align="center">结束</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="resMap" id="column">   
			<s:set name="total" value="#column.value.size"/>
			<s:if test="#total == 0">
				<tr class="mainTr">
					<td align="center">
						<label for="selectRoom_<s:property value='#column.key' />"><p:code2name mapCodeName="roomMap" value="#column.key"/></label>
					</td>
					<td colspan="2" align="center">空</td>
				</tr>
			</s:if>
			<s:iterator value="#column.value" status="s">
				<tr class="mainTr">   
					<s:if test="#s.first">
						<td align="center" rowspan="${total}">
						 	<label for="selectRoom_<s:property value='#column.key' />"><p:code2name mapCodeName="roomMap" value="#column.key"/></LABEL>	
						</td>
					</s:if>   
					<td align="center" style="color:red;"><s:date name="beginTime" format="HH:mm" /></td>   
					<td align="center" style="color:red;"><s:date name="endTime" format="HH:mm" /></td>
				</tr>   
			</s:iterator>   
		</s:iterator>
		</tbody>
	</table>
</div>

