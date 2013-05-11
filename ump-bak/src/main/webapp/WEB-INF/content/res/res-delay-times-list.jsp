<%@page import="com.hhz.ump.util.JspUtil"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<table class="showTable" id="editTable" >
	<col />
	<thead>
		<tr class="header">
			<th style="padding-left:8px;">审批人</th>
			<th >日期</th>
			<th >过期次数</th>
		</tr>
	</thead>
	<tbody>
		<s:if test="page.result.size() > 0">
		<s:iterator id="id" value="page.result">
			<tr class="mainTr" name="${id[0]}">
				<s:set var="userName" ><%=CodeNameUtil.getUserNameByCd(JspUtil.findString("#id[0]")) %></s:set>
				<td style="padding-left:8px;" >${userName}</td>
				<td >
					${id[1]}
				</td>
				<td id="td_${id[0]}" >
					${id[2]}
				</td>
			</tr>
			<tr id="detail_${id[0]}" class="detailTr" style="display:none">
				<td colspan="3" >
					
				</td>
			</tr>
		</s:iterator>
		</s:if>
	</tbody>
</table>
<div class="table_pager" style="margin-top:5px;">
	<p:page />
</div>
<s:if test="page.result.size() == 0">
	<div align="center" style="font-weight: bold;color:#5B6B83;font-size: 14px; margin-top:10px;">
		没有相关的数据！
	</div>
</s:if>


