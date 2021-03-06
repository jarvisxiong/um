<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.DictContants"%>
<div style="margin:0px 2px; ">
	<table class="showTable" >
		<tr style="display: none">
			<th width="120px" ></th>
			<th width="80px"></th>
			<th width="80px"></th>
			<th width="80px"></th>
			<th></th>
			<th width="120px"></th>
			<th width="80px"></th>
			<th width="80px"></th>
		</tr>
		<tr class="header">
			<th style="width:120px;" width="120px" >对象</th>
			<th width="80px" >日期</th>
			<th width="80px" >开始时间</th>
			<th width="80px" >结束时间</th>
			<th align="left" style="padding:0 4px;">会议主题</th>
			<th width="120px" >申请时间</th>
			<th width="80px" >状态</th>
			<th width="80px" >操作</th>
		</tr>
		<s:if test="page.result.size == 0">
		<tr>
			<td colspan="8"><div class="noResult" style="padding: 10px 0;">没有预约总裁申请记录！</div></td>
		</tr>
		</s:if>
		<tbody>
		<s:iterator value="page.result" status="status">
			<tr class="mainTr">
			<td><p:code2name mapCodeName="chairmanTypeMap" value="chairmanType"/></td>
			<td ><s:date name="beginTime" format="yyyy-MM-dd"/></td>
			<td ><s:date name="beginTime" format="HH:mm"/></td>
			<td ><s:date name="endTime" format="HH:mm"/></td>
			<td align="left" style="padding:0 4px;">
			<div class="ellipsisDiv">${subject}</div>
			</td>
			<td><s:date name="createdDate" format="yyyy-MM-dd HH:mm"/></td>
			<td>
				<s:if test="status == 0">
					待审核
				</s:if>
				<s:if test="status == 1">
					已成功
				</s:if>
				<s:if test="status == 2">
					已删除
				</s:if>
				<s:if test="status == 3">
					已取消约会
				</s:if>
			
			</td>
			<td  style="text-decoration: underline;cursor: pointer;" onclick="reResMeetingRoom('${oaChairmanReserveId}')">重新申请</td>
		</tr>
		</s:iterator>
		</tbody>
	</table>
	<div align="right" class="table_pager"><p:page pageInfo="page"/></div>
</div>


