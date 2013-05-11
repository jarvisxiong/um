<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/nocache.jsp"%>

<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>
<table id="detail">
<tr align="left">
<s:iterator value="projects" status="project">
<td >
<table class="innerTable" cellpadding="0" cellspacing="0">
	<col width="50"/>
	<col width="50"/>
	<col width="50"/>
	<tr class="headTr">
		<td colspan="3"  style="border-left:0px;padding:0px;margin:0px;"><a  class="pHead" href=" bis-manage!layout.action?module=2&bisProjectId=${projectid}">${projectName }</a></td>
	</tr>
	<tr>
		<th>应收</th>
		<th style="border-left:0px;">实收</th>
		<th>%</th>
	</tr>	
	<s:iterator value="detailInlist" status="in">
		<tr class="mainTr" >	
			<td>${fy}</td>
			<td>${my}</td>
			<td>${ry}</td>
			
			
		</tr>
		</s:iterator>
	
	<tr >
		<th style="border-left:0px;">预算</th>
		<th>实际</th>
		<th>%</th>
	</tr>
	<s:iterator value="detailOutlist" status="out">
		<tr class="mainTr" >	
			<td>${fy}</td>
			<td>${my}</td>
			<td>${ry}</td>
			
		</tr>
	</s:iterator>
	<!-- 收入与支出差额 -->
	<s:iterator value="detaillist">
		<tr  >	
			<td class="bottomTd">${fy}</td>
			<td class="bottomTd">${my}</td>
			<td class="bottomTd">${ry}</td>
		</tr>
	</s:iterator>
</table>
</td>
</s:iterator>
</tr></table>
<script type="text/javascript" src="${ctx}/resources/js/jquery/jquery.js"></script>
<script>
$('.pHead').unbind().bind('mousemove',function(){
	$(this).css('color','#ffffff');
	$(this).addClass('pover');
}).bind('mouseout',function(){
	$(this).removeClass('pover');
	$(this).css('color','#006fb6');
})
</script>