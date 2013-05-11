<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<%@page import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<%@page import="com.hhz.ump.util.CodeNameUtil"%>
<%@page import="com.hhz.ump.util.JspUtil"%>

<table class="bidTable" style="border-collapse: separate;border: 0px;"  cellspacing="0" cellpadding="0" >
	<thead>
	<tr class="header">
		<th width="45"  class="thNoLine">序号</th>
		<th>项目名称</th>
		<th style="text-align: center;">计算单位 </th>			
		<th>金额(元)</th>
		<th>备注</th>
	</tr>
	</thead>
	<tbody>
	<s:iterator value="bidOtherItemRelRs.result"  var="bidOtherItemRel"  status="index">
	<tr class="commonTr">
		<td  align="center" style="text-align: center;" ><s:property value="#index.index+1"/></td>
		<td title='${itemName}'><div class="ellipsisDiv">${itemName}</div></td>
		<td title='${calcBasicDesc}' style="text-align: center;"><div class="ellipsisDiv">${calcBasicDesc}</div></td>
		<s:if test="bidLedger!=null&&bidLedger.bidStatusCd<3">
			<td title='***'><div class="ellipsisDiv">***</div></td>
			<td title='***'><div class="ellipsisDiv ">***</div></td>				
		</s:if>
		<s:else>
			<td title='${amt}'><div class="ellipsisDiv">${amt}</div></td>
			<td title='${remark}'><div class="ellipsisDiv">${remark}</div></td>
		</s:else>
	</tr>
	</s:iterator>
	</tbody>
</table>

<div>
	<div class="table_pager" style="margin-top:5px;" url="${ctx}/bid/bid-other-item-rel!list.action">
		<p:page />
	</div>
</div>
				